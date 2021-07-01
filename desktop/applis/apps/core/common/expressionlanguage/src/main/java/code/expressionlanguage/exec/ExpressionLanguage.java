package code.expressionlanguage.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecInnerElementBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExpressionLanguage {

    private final CustList<ExecOperationNode> operations;
    private final IdMap<ExecOperationNode,ArgumentsPair> arguments;
    private ExecOperationNode currentOper;
    private ArgumentsPair argument;
    private int index;

    public ExpressionLanguage(CustList<ExecOperationNode> _operations) {
        operations = _operations;
        arguments = buildArguments();
    }

    public static Argument tryToCalculate(ContextEl _conf, ExpressionLanguage _right, int _offset, StackCall _stackCall) {
        ArgumentsPair argumentsPair_ = tryToCalculatePair(_conf, _right, _offset, _stackCall);
        return getNullable(argumentsPair_);
    }

    public static ArgumentsPair tryToCalculatePair(ContextEl _conf, ExpressionLanguage _right, int _offset, StackCall _stackCall) {
        ArgumentsPair argument_ = _right.argument;
        if (argument_ != null) {
            return argument_;
        }
        IdMap<ExecOperationNode, ArgumentsPair> allRight_ = _right.getArguments();
        calculate(allRight_, _right, _conf, _offset, _stackCall);
        if (_conf.callsOrException(_stackCall)) {
            return null;
        }
        _right.argument = ExecHelper.getArgumentPair(_right.arguments,_right.arguments.size()-1);
        _stackCall.getLastPage().setTranslatedOffset(0);
        return _right.argument;
    }

    private static void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ExpressionLanguage _el, ContextEl _context, int _offset, StackCall _stackCall) {
        AbstractPageEl pageEl_ = _stackCall.getLastPage();
        pageEl_.setTranslatedOffset(_offset);
        int fr_ = _el.getIndex();
        int len_ = _nodes.size();
        while (fr_ < len_) {
            ExecOperationNode o = _nodes.getKey(fr_);
            o.setRelativeOffsetPossibleLastPage(_stackCall);
            ExecMethodOperation parent_ = o.getParent();
            if (parent_ instanceof ExecAbstractInstancingOperation && ((ExecAbstractInstancingOperation) parent_).isInitBefore() && o.getIndexChild() == 0) {
                ExecRootBlock type_ = ((ExecAbstractInstancingOperation) parent_).getInstancingCommonContent().getPair().getType();
                if (!(type_ instanceof ExecInnerElementBlock)&&type_.withoutInstance()&&_context.getExiting().hasToExit(_stackCall, type_)) {
                    processCalling(_el, pageEl_, o, _stackCall);
                    return;
                }
            }
            ArgumentsPair pair_ = _nodes.getValue(fr_);
            if (!(o instanceof AtomicExecCalculableOperation)) {
                Argument a_ = Argument.getNullableValue(o.getArgument());
                o.setConstantSimpleArgument(a_,_context,_nodes, _stackCall);
                fr_ = getNextIndex(len_,_nodes,o, fr_ + 1,_context,_stackCall,_el);
            } else if (pair_.getArgument() != null) {
                o.setConstantSimpleArgument(pair_.getArgument(),_context,_nodes, _stackCall);
                fr_ = getNextIndex(len_,_nodes,o, fr_ + 1,_context,_stackCall,_el);
            } else {
                AtomicExecCalculableOperation a_ = (AtomicExecCalculableOperation)o;
                a_.calculate(_nodes, _context, _stackCall);
                fr_ = getNextIndex(len_,_nodes,o, fr_ + 1,_context,_stackCall,_el);
            }
        }
    }

    private static void processCalling(ExpressionLanguage _el, AbstractPageEl _pageEl, ExecOperationNode _o, StackCall _stackCall) {
        _el.setCurrentOper(_o);
        restore(_pageEl, _stackCall);
    }

    private static void restore(AbstractPageEl _pageEl, StackCall _stackCall) {
        if (!_stackCall.calls()) {
            _pageEl.setTranslatedOffset(0);
        }
    }

    private IdMap<ExecOperationNode,ArgumentsPair> buildArguments() {
        IdMap<ExecOperationNode,ArgumentsPair> arguments_;
        arguments_ = new IdMap<ExecOperationNode,ArgumentsPair>();
        for (ExecOperationNode o: operations) {
            ArgumentsPair a_ = new ArgumentsPair();
            a_.setArgument(o.getArgument());
            arguments_.addEntry(o, a_);
        }
        return arguments_;
    }

    public Argument getArgument() {
        return getNullable(argument);
    }

    public static Argument getNullable(ArgumentsPair _argumentsPair) {
        if (_argumentsPair != null) {
            return Argument.getNullableValue(_argumentsPair.getArgument());
        }
        return Argument.createVoid();
    }

    public void setArgument(AbstractWrapper _wrapp, Argument _arg, ContextEl _cont, StackCall _stackCall) {
        if (_wrapp != null) {
            ExecHelper.getArgumentPair(arguments,currentOper).setWrapper(_wrapp);
        }
        setArgument(_arg,_cont, _stackCall);
    }

    public void setArgument(Argument _arg, ContextEl _cont, StackCall _stackCall) {
        ExecOperationNode currentOper_ = currentOper;
        int least_ = index + 1;
        if (currentOper_ == null) {
            return;
        }
        if (currentOper_ instanceof CallExecSimpleOperation) {
            ((CallExecSimpleOperation) currentOper_).endCalculate(_cont, arguments, _arg, _stackCall);
            getNextIndex(currentOper_, least_,_cont,_stackCall);
            return;
        }
        currentOper_.setSimpleArgument(_arg, _cont, arguments, _stackCall);
        getNextIndex(currentOper_, least_,_cont,_stackCall);
    }

    private void getNextIndex(ExecOperationNode _currentOper, int _least, ContextEl _context, StackCall _stackCall) {
        if (_context.callsOrException(_stackCall)) {
            return;
        }
        index = getNextIndex(arguments, _currentOper, _least);
    }

    private static int getNextIndex(int _max, IdMap<ExecOperationNode, ArgumentsPair> _args, ExecOperationNode _oper, int _least, ContextEl _context, StackCall _stackCall,ExpressionLanguage _exp) {
        if (_context.callsOrException(_stackCall)) {
            processCalling(_exp, _stackCall.getLastPage(), _oper, _stackCall);
            return _max;
        }
        return getNextIndex(_args,_oper,_least);
    }
    private static int getNextIndex(IdMap<ExecOperationNode, ArgumentsPair> _args, ExecOperationNode _oper, int _least) {
        ArgumentsPair value_ = ExecHelper.getArgumentPair(_args,_oper);
        Argument res_ = Argument.getNullableValue(value_.getArgument());
        Struct v_ = res_.getStruct();
        if (_oper.getNextSibling() != null&&value_.isArgumentTest()){
            ExecMethodOperation par_ = _oper.getParent();
            if (par_ instanceof ExecQuickOperation){
                v_ = ((ExecQuickOperation) par_).getAbsorbingValue();
            }
            if (par_ instanceof ExecCompoundAffectationOperation){
                ExecCompoundAffectationOperation p_ = (ExecCompoundAffectationOperation) par_;
                v_ = absCompound(p_,v_);
            }
        }
        return Math.max(_least, ExecOperationNode.getNextIndex(_oper, v_));
    }

    public static Struct absCompound(CompoundedOperator _comp,Struct _def) {
        Struct v_ = _def;
        if (ExecOperationNode.andEq(_comp)) {
            v_ = BooleanStruct.of(false);
        }
        if (ExecOperationNode.orEq(_comp)) {
            v_ = BooleanStruct.of(true);
        }
        return v_;
    }
    public IdMap<ExecOperationNode, ArgumentsPair> getArguments() {
        return arguments;
    }

    private void setCurrentOper(ExecOperationNode _currentOper) {
        currentOper = _currentOper;
        index = _currentOper.getOrder();
    }
    public int getIndex() {
        return index;
    }
}
