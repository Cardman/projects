package code.expressionlanguage.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.variables.TwoStepsArgumentsPair;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExpressionLanguage {

    private final CustList<ExecOperationNode> operations;
    private final IdMap<ExecOperationNode,ArgumentsPair> arguments;
    private ExecOperationNode currentOper;
    private Argument argument;
    private int index;

    public ExpressionLanguage(CustList<ExecOperationNode> _operations) {
        operations = _operations;
        arguments = buildArguments();
    }

    public static Argument tryToCalculate(ContextEl _conf, ExpressionLanguage _right, int _offset) {
        if (_right.isFinished()) {
            return _right.getArgument();
        }
        IdMap<ExecOperationNode, ArgumentsPair> allRight_ = _right.getArguments();
        calculate(allRight_, _right, _conf, _offset);
        if (_conf.callsOrException()) {
            return _right.getArgument();
        }
        _right.finish();
        return Argument.getNullableValue(_right.getArgument());
    }

    private static void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ExpressionLanguage _el, ContextEl _context, int _offset) {
        AbstractPageEl pageEl_ = _context.getLastPage();
        pageEl_.setTranslatedOffset(_offset);
        int fr_ = _el.getIndex();
        int len_ = _nodes.size();
        while (fr_ < len_) {
            ExecOperationNode o = _nodes.getKey(fr_);
            ArgumentsPair pair_ = _nodes.getValue(fr_);
            if (!(o instanceof AtomicExecCalculableOperation)) {
                Argument a_ = Argument.getNullableValue(o.getArgument());
                o.setConstantSimpleArgument(a_,_context,_nodes);
                if (_context.callsOrException()) {
                    processCalling(_el, _context, pageEl_, o);
                    return;
                }
                fr_ = getNextIndex(_nodes,o, fr_ + 1);
                continue;
            }
            if (pair_.getArgument() != null) {
                o.setConstantSimpleArgument(pair_.getArgument(),_context,_nodes);
                if (_context.callsOrException()) {
                    processCalling(_el, _context, pageEl_, o);
                    return;
                }
                fr_ = getNextIndex(_nodes,o, fr_ + 1);
                continue;
            }
            AtomicExecCalculableOperation a_ = (AtomicExecCalculableOperation)o;
            a_.calculate(_nodes, _context);
            if (_context.callsOrException()) {
                processCalling(_el, _context, pageEl_, o);
                return;
            }
            fr_ = getNextIndex(_nodes,o, fr_ + 1);
        }
        pageEl_.setTranslatedOffset(0);
    }

    private static void processCalling(ExpressionLanguage _el, ContextEl _context, AbstractPageEl _pageEl, ExecOperationNode _o) {
        _el.setCurrentOper(_o);
        if (!_context.calls()) {
            _pageEl.setTranslatedOffset(0);
        }
    }

    public static CustList<ExecOperationNode> getReducedNodes(ExecOperationNode _root) {
        CustList<ExecOperationNode> out_ = new CustList<ExecOperationNode>();
        ExecOperationNode current_ = _root;
        while (current_ != null) {
            ExecOperationNode op_ = current_.getFirstChild();
            if (op_ != null && current_.getArgument() == null) {
                current_ = op_;
                continue;
            }
            while (true) {
                current_.setOrder(out_.size());
                out_.add(current_);
                op_ = current_.getNextSibling();
                if (op_ != null) {
                    current_ = op_;
                    break;
                }
                op_ = current_.getParent();
                if (op_ == null) {
                    current_ = null;
                    break;
                }
                if (op_ == _root) {
                    op_.setOrder(out_.size());
                    out_.add(op_);
                    current_ = null;
                    break;
                }
                current_ = op_;
            }
        }
        return out_;
    }

    private IdMap<ExecOperationNode,ArgumentsPair> buildArguments() {
        IdMap<ExecOperationNode,ArgumentsPair> arguments_;
        arguments_ = new IdMap<ExecOperationNode,ArgumentsPair>();
        for (ExecOperationNode o: operations) {
            boolean std_ = true;
            if (o instanceof ExecCompoundAffectationOperation) {
                if (((ExecCompoundAffectationOperation) o).getSettable() instanceof ExecCustArrOperation) {
                    std_ = false;
                }
            }
            if (o instanceof ExecSemiAffectationOperation) {
                if (((ExecSemiAffectationOperation) o).getSettable() instanceof ExecCustArrOperation) {
                    std_ = false;
                }
            }
            ArgumentsPair a_;
            if (std_) {
                a_ = new ArgumentsPair();
            } else {
                a_ = new TwoStepsArgumentsPair();
            }
            a_.setImplicits(o.getImplicits());
            a_.setImplicitsTest(o.getImplicitsTest());
            if (o instanceof ExecCompoundAffectationOperation) {
                ImplicitMethods conv_ = ((ExecCompoundAffectationOperation) o).getConverter();
                if (conv_ != null) {
                    a_.setImplicitsCompound(conv_);
                }
            }
            if (o instanceof ExecQuickOperation) {
                ImplicitMethods conv_ = ((ExecQuickOperation) o).getConverter();
                if (conv_ != null) {
                    a_.setImplicitsCompound(conv_);
                }
            }
            if (o instanceof ExecSemiAffectationOperation) {
                ImplicitMethods conv_ = ((ExecSemiAffectationOperation) o).getConverterFrom();
                if (conv_ != null) {
                    a_.setImplicitsSemiFrom(conv_);
                }
                conv_ = ((ExecSemiAffectationOperation) o).getConverterTo();
                if (conv_ != null) {
                    a_.setImplicitsSemiTo(conv_);
                }
            }
            a_.setArgument(o.getArgument());
            arguments_.addEntry(o, a_);
        }
        return arguments_;
    }

    public Argument getArgument() {
        return argument;
    }

    public void setArgument(Argument _arg, ContextEl _cont) {
        ExecOperationNode currentOper_ = currentOper;
        int least_ = index + 1;
        if (currentOper_ == null) {
            return;
        }
        if (currentOper_ instanceof CallExecSimpleOperation) {
            ((CallExecSimpleOperation) currentOper_).endCalculate(_cont, arguments, _arg);
            if (_cont.callsOrException()) {
                return;
            }
            getNextIndex(currentOper_, least_);
            return;
        }
        currentOper_.setSimpleArgument(_arg, _cont, arguments);
        if (_cont.callsOrException()) {
            return;
        }
        getNextIndex(currentOper_, least_);
    }

    private void getNextIndex(ExecOperationNode _currentOper, int _least) {
        index = getNextIndex(arguments, _currentOper, _least);
    }

    private static int getNextIndex(IdMap<ExecOperationNode, ArgumentsPair> _args, ExecOperationNode _oper, int _least) {
        ArgumentsPair value_ = ExecTemplates.getArgumentPair(_args,_oper);
        Argument res_ = Argument.getNullableValue(value_.getArgument());
        Struct v_ = res_.getStruct();
        if (_oper.getNextSibling() != null&&value_.isArgumentTest()){
            ExecMethodOperation par_ = _oper.getParent();
            if (par_ instanceof ExecAndOperation){
                v_ = BooleanStruct.of(false);
            }
            if (par_ instanceof ExecOrOperation){
                v_ = BooleanStruct.of(true);
            }
            if (par_ instanceof ExecCompoundAffectationOperation){
                ExecCompoundAffectationOperation p_ = (ExecCompoundAffectationOperation) par_;
                if (StringUtil.quickEq(p_.getOper(),"&&=")) {
                    v_ = BooleanStruct.of(false);
                }
                if (StringUtil.quickEq(p_.getOper(),"||=")) {
                    v_ = BooleanStruct.of(true);
                }
            }
        }
        return Math.max(_least, ExecOperationNode.getNextIndex(_oper, v_));
    }
    public boolean isFinished() {
        return argument != null;
    }

    public void finish() {
        argument = arguments.lastValue().getArgument();
    }

    public IdMap<ExecOperationNode, ArgumentsPair> getArguments() {
        return arguments;
    }

    public void setCurrentOper(ExecOperationNode _currentOper) {
        currentOper = _currentOper;
        index = _currentOper.getOrder();
    }
    public int getIndex() {
        return index;
    }
}
