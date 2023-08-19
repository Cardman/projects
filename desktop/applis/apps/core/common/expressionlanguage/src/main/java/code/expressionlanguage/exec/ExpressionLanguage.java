package code.expressionlanguage.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecForMutableIterativeLoop;
import code.expressionlanguage.exec.blocks.ExecInnerElementBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.NumberUtil;

public final class ExpressionLanguage {

    private final IdMap<ExecOperationNode,ArgumentsPair> arguments;
    private ExecOperationNode currentOper;
    private ArgumentsPair argument;
    private int index;
    private final ExecBlock coveredBlock;

    public ExpressionLanguage(CustList<ExecOperationNode> _operations, ExecBlock _coveredBlock) {
        coveredBlock = _coveredBlock;
        arguments = buildArguments(_operations);
        if (_operations.isEmpty() && _coveredBlock instanceof ExecForMutableIterativeLoop) {
            argument = new ArgumentsPair();
            argument.setArgument(new Argument(BooleanStruct.of(true)));
        }
    }

    public static ArgumentsPair tryToCalculatePair(ContextEl _conf, ExpressionLanguage _right, int _offset, StackCall _stackCall) {
        ArgumentsPair argument_ = _right.argument;
        if (argument_ != null) {
            return argument_;
        }
        IdMap<ExecOperationNode, ArgumentsPair> allRight_ = _right.getArguments();
        calculate(allRight_, _right, _conf, _offset, _stackCall);
        if (_stackCall.stopAt(_conf)) {
            return null;
        }
        if (_right.arguments.isEmpty()) {
            return null;
        }
        _right.argument = _right.arguments.lastValue();
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
            if (hasToExit(o, _el, _context, _stackCall) || _stackCall.getStopper().isStopAt(_el, o, _context, _stackCall)) {
                return;
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
    private static boolean hasToExit(ExecOperationNode _o, ExpressionLanguage _el, ContextEl _context, StackCall _stackCall) {
        ExecMethodOperation parent_ = _o.getParent();
        if (parent_ instanceof ExecAbstractInstancingOperation && ((ExecAbstractInstancingOperation) parent_).isInitBefore() && _o.getIndexChild() == 0) {
            ExecRootBlock type_ = ((ExecAbstractInstancingOperation) parent_).getInstancingCommonContent().getPair().getType();
            if (!(type_ instanceof ExecInnerElementBlock)&&_context.getExiting().hasToExit(_stackCall, type_)) {
                processCalling(_el, _o);
                return true;
            }
        }
        if (_o instanceof ExecSettableFieldStatOperation && !((ExecSettableFieldStatOperation)_o).resultCanBeSet() && _context.getExiting().hasToExit(_stackCall, ((ExecSettableFieldStatOperation)_o).getRootBlock())) {
            _stackCall.setOffset(((ExecSettableFieldStatOperation)_o).getOff());
            processCalling(_el, _o);
            return true;
        }
        if (_o instanceof ExecAffectationOperation) {
            ExecOperationNode s_ = ((ExecAbstractAffectOperation) _o).getSettable();
            if (s_ instanceof ExecSettableFieldStatOperation && _context.getExiting().hasToExit(_stackCall, ((ExecSettableFieldStatOperation)s_).getRootBlock())) {
                _stackCall.setOffset(((ExecSettableFieldStatOperation)s_).getOff());
                processCalling(_el, _o);
                return true;
            }
        }
        return false;
    }

    private static void processCalling(ExpressionLanguage _el, ExecOperationNode _o) {
        _el.setCurrentOper(_o);
    }

    private static IdMap<ExecOperationNode,ArgumentsPair> buildArguments(CustList<ExecOperationNode> _operations) {
        IdMap<ExecOperationNode,ArgumentsPair> arguments_;
        arguments_ = new IdMap<ExecOperationNode,ArgumentsPair>();
        for (ExecOperationNode o: _operations) {
            ArgumentsPair a_ = new ArgumentsPair();
            a_.setArgument(o.getArgument());
            arguments_.addEntry(o, a_);
        }
        return arguments_;
    }

    public ExecBlock getCoveredBlock() {
        return coveredBlock;
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
        ExecOperationNode currentOper_ = currentOper;
        int least_ = index + 1;
        if (currentOper_ == null) {
            return;
        }
        if (_wrapp != null&& !(currentOper_ instanceof ExecStdRefVariableOperation)) {
            ExecHelper.getArgumentPair(arguments,currentOper_).setWrapper(_wrapp);
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
        _stackCall.getBreakPointInfo().getStackState().visitedNone();
        index = getNextIndex(arguments, _currentOper, _least);
    }

    private static int getNextIndex(int _max, IdMap<ExecOperationNode, ArgumentsPair> _args, ExecOperationNode _oper, int _least, ContextEl _context, StackCall _stackCall,ExpressionLanguage _exp) {
        if (_context.callsOrException(_stackCall)) {
            processCalling(_exp, _oper);
            return _max;
        }
        _stackCall.getBreakPointInfo().getStackState().visitedNone();
        return getNextIndex(_args,_oper,_least);
    }
    private static int getNextIndex(IdMap<ExecOperationNode, ArgumentsPair> _args, ExecOperationNode _oper, int _least) {
        ArgumentsPair value_ = ExecHelper.getArgumentPair(_args,_oper);
        Argument res_ = Argument.getNullableValue(value_.getArgument());
        Struct v_ = res_.getStruct();
        ExecMethodOperation par_ = _oper.getParent();
        if (isAncSettable(_oper) &&value_.isArgumentTest()){
            return NumberUtil.max(_least,par_.getOrder());
        }
        return NumberUtil.max(_least, getNextIndex(_oper, v_));
    }

    public static boolean isAncSettable(ExecOperationNode _oper) {
        ExecMethodOperation par_ = _oper.getParent();
        if (par_ instanceof CompoundedOperator&&!(par_ instanceof ExecCompoundAffectationOperation)){
            return par_.getFirstChild() == _oper;
        }
        return par_ instanceof ExecCompoundAffectationOperation && _oper == ((ExecAbstractAffectOperation) par_).getSettableAnc();
    }

    private static int getNextIndex(ExecOperationNode _operation, Struct _value) {
        int index_ = _operation.getIndexChild();
        ExecMethodOperation par_ = _operation.getParent();
        if (safeDotShort(_value, index_, par_ instanceof ExecSafeDotOperation)) {
            ExecOperationNode last_ = ExecHelper.getLastNode(par_);
            if (!(last_ instanceof ExecAbstractLambdaOperation)) {
                return shortCutNul(par_, last_, par_.getOrder());
            }
        }
        if (par_ instanceof ExecRefTernaryOperation) {
            if (index_ == 1) {
                return par_.getOrder();
            }
            if (index_ == 0 && BooleanStruct.isFalse(_value)) {
                return ExecHelper.getOrder(_operation.getNextSibling()) + 1;
            }
        }
        return _operation.getOrder() + 1;
    }

    public static boolean safeDotShort(Struct _value, int _index, boolean _inst) {
        return _index == 0 && safeDotShort(_value, _inst);
    }

    private static boolean safeDotShort(Struct _value, boolean _inst) {
        return _inst && _value == NullStruct.NULL_VALUE;
    }

    private static int shortCutNul(ExecMethodOperation _par, ExecOperationNode _last, int _order) {
        ExecMethodOperation p_ = _par;
        while (p_ != null) {
            ExecOperationNode set_ = null;
            if (p_ instanceof ExecAbstractAffectOperation) {
                set_ = ((ExecAbstractAffectOperation) p_).getSettable();
            }
            if (set_ == _last) {
                return p_.getOrder();
            }
            p_ = p_.getParent();
        }
        return _order;
    }

    public IdMap<ExecOperationNode, ArgumentsPair> getArguments() {
        return arguments;
    }

    public void currentOper(ExecOperationNode _currentOper) {
        setCurrentOper(_currentOper);
    }
    private void setCurrentOper(ExecOperationNode _currentOper) {
        currentOper = _currentOper;
        index = _currentOper.getOrder();
    }

    public ExecOperationNode getCurrentOper() {
        return currentOper;
    }

    public int getIndex() {
        return index;
    }
}
