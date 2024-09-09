package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecForMutableIterativeLoop;
import code.expressionlanguage.exec.blocks.ExecInnerElementBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.util.ArgumentListCall;
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
            argument.setArgument(BooleanStruct.of(true));
        }
    }

    public static ArgumentsPair tryToCalculatePair(ContextEl _conf, ExpressionLanguage _right, int _offset, StackCall _stackCall) {
        ArgumentsPair argument_ = _right.argument;
        if (argument_ != null) {
            return argument_;
        }
        _right.calculate(_conf, _offset, _stackCall);
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

    private void calculate(ContextEl _context, int _offset, StackCall _stackCall) {
        AbstractPageEl pageEl_ = _stackCall.getLastPage();
        pageEl_.setTranslatedOffset(_offset);
        int fr_ = getIndex();
        int len_ = arguments.size();
        while (fr_ < len_) {
            ExecOperationNode o = arguments.getKey(fr_);
            o.setRelativeOffsetPossibleLastPage(_stackCall);
            currentOper = o;
            if (hasToExit(o, _context, _stackCall) || _stackCall.getStopper().isStopAtRef(_context, _stackCall)) {
                return;
            }
            ArgumentsPair pair_ = arguments.getValue(fr_);
            if (!(o instanceof AtomicExecCalculableOperation)) {
                Struct a_ = ArgumentListCall.getNull(o.getArgument());
                o.setConstantSimpleArgument(a_,_context,arguments, _stackCall);
                fr_ = getNextIndex(len_, o, fr_ + 1,_context,_stackCall);
            } else if (pair_.getArgument() != null) {
                o.setConstantSimpleArgument(pair_.getArgument(),_context,arguments, _stackCall);
                fr_ = getNextIndex(len_, o, fr_ + 1,_context,_stackCall);
            } else {
                AtomicExecCalculableOperation a_ = (AtomicExecCalculableOperation)o;
                a_.calculate(arguments, _context, _stackCall);
                if (_stackCall.getStopper().stopAt(_stackCall)) {
                    return;
                }
                fr_ = getNextIndex(len_, o, fr_ + 1,_context,_stackCall);
            }
        }
    }
    private static boolean hasToExit(ExecOperationNode _o, ContextEl _context, StackCall _stackCall) {
        ExecMethodOperation parent_ = _o.getParent();
        if (parent_ instanceof ExecAbstractInstancingOperation && ((ExecAbstractInstancingOperation) parent_).isInitBefore() && _o.getIndexChild() == 0) {
            ExecRootBlock type_ = ((ExecAbstractInstancingOperation) parent_).getInstancingCommonContent().getPair().getType();
            if (!(type_ instanceof ExecInnerElementBlock)&&_context.getExiting().hasToExit(_stackCall, type_)) {
                return true;
            }
        }
        if (_o instanceof ExecSettableFieldStatOperation && !((ExecSettableFieldStatOperation)_o).resultCanBeSet() && _context.getExiting().hasToExit(_stackCall, ((ExecSettableFieldStatOperation)_o).getRootBlock())) {
            _stackCall.setOffset(((ExecSettableFieldStatOperation)_o).getOff());
            return true;
        }
        if (_o instanceof ExecAffectationOperation) {
            ExecOperationNode s_ = ((ExecAbstractAffectOperation) _o).getSettable();
            if (s_ instanceof ExecSettableFieldStatOperation && _context.getExiting().hasToExit(_stackCall, ((ExecSettableFieldStatOperation)s_).getRootBlock())) {
                _stackCall.setOffset(((ExecSettableFieldStatOperation)s_).getOff());
                return true;
            }
        }
        return false;
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

    public Struct getArgument() {
        return getNullable(argument);
    }

    public static Struct getNullable(ArgumentsPair _argumentsPair) {
        if (_argumentsPair != null) {
            return ArgumentListCall.getNull(_argumentsPair.getArgument());
        }
        return NullStruct.NULL_VALUE;
    }

    public void setArgument(AbstractWrapper _wrapp, Struct _arg, ContextEl _cont, StackCall _stackCall) {
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
        getNextIndex(arguments.size(),_currentOper,_least,_context,_stackCall);
    }

    private int getNextIndex(int _max, ExecOperationNode _oper, int _least, ContextEl _context, StackCall _stackCall) {
        if (_context.callsOrException(_stackCall)) {
            return _max;
        }
        int res_ = getNextIndex(arguments, _oper, _least);
        index = res_;
        return res_;
    }
    private static int getNextIndex(IdMap<ExecOperationNode, ArgumentsPair> _args, ExecOperationNode _oper, int _least) {
        ArgumentsPair value_ = ExecHelper.getArgumentPair(_args,_oper);
        Struct res_ = ArgumentListCall.getNull(value_.getArgument());
        ExecMethodOperation par_ = _oper.getParent();
        if (isAncSettable(_oper) &&value_.isArgumentTest()){
            return NumberUtil.max(_least,par_.getOrder());
        }
        return NumberUtil.max(_least, getNextIndex(_oper, res_));
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

    public ExecOperationNode getCurrentOper() {
        return currentOper;
    }

    public int getIndex() {
        return index;
    }
}
