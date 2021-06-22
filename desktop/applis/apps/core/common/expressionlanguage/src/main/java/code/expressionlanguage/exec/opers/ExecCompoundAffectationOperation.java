package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.structs.NullStruct;
import code.util.IdMap;
import code.util.StringList;
import code.util.core.StringUtil;

public abstract class ExecCompoundAffectationOperation extends ExecAbstractAffectOperation implements CallExecSimpleOperation,CompoundedOperator {

    private final ExecOperatorContent operatorContent;
    private final ImplicitMethods converter;
    private final StringList names;

    protected ExecCompoundAffectationOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent, ImplicitMethods _converter, StringList _names) {
        super(_opCont);
        operatorContent = _operatorContent;
        converter = _converter;
        names = _names;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        if (getSettableParent() instanceof ExecSafeDotOperation) {
            ExecOperationNode left_ = getSettableParent().getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
                pair_.setIndexImplicitCompound(-1);
                pair_.setEndCalculate(true);
                leftArg_ = new Argument(ExecClassArgumentMatching.convertFormatted(NullStruct.NULL_VALUE,_conf, names, _stack));
                setQuickConvertSimpleArgument(leftArg_, _conf, _nodes, _stack);
                return;
            }
        }
        Argument leftArg_ = getFirstArgument(_nodes,this);
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
        ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_nodes, getFirstChild());
        if (argumentPair_.isArgumentTest()){
            pair_.setIndexImplicitCompound(-1);
            if (StringUtil.quickEq(operatorContent.getOper(), AbsBk.AND_LOG_EQ_SHORT) || StringUtil.quickEq(operatorContent.getOper(), AbsBk.OR_LOG_EQ_SHORT)) {
                pair_.setEndCalculate(true);
                setSimpleArgument(leftArg_, _conf, _nodes, _stack);
                return;
            }
            setRelOffsetPossibleLastPage(operatorContent.getOpOffset(), _stack);
            Argument arg_ = ExecAffectationOperation.calculateChSetting(getSettable(),_nodes, _conf, leftArg_, _stack);
            pair_.setEndCalculate(true);
            setSimpleArgument(arg_, _conf, _nodes, _stack);
            return;
        }
        calculateSpec(_nodes, _conf, _stack);
    }



    protected abstract void calculateSpec(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                          ContextEl _conf, StackCall _stack);

    @Override
    public void endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack) {
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
        setRelOffsetPossibleLastPage(operatorContent.getOpOffset(), _stack);
        int indexImplicit_ = pair_.getIndexImplicitCompound();
        if (ImplicitMethods.isValidIndex(converter,indexImplicit_)) {
            pair_.setIndexImplicitCompound(processConverter(_conf, _right, converter, indexImplicit_, _stack));
            return;
        }
        if (!pair_.isEndCalculate()) {
            pair_.setEndCalculate(true);
            Argument arg_ = endCalculateCh(getSettable(), _nodes, _conf, _right, _stack);
            setSimpleArgument(arg_, _conf, _nodes, _stack);
            return;
        }
        setSimpleArgument(_right,_conf,_nodes, _stack);
    }
    private static Argument endCalculateCh(ExecOperationNode _set,
                                           IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right, StackCall _stackCall){
        Argument arg_ = null;
        if (_set instanceof ExecStdRefVariableOperation) {
            arg_ = ((ExecStdRefVariableOperation)_set).endCalculate(_conf, _nodes, _right, _stackCall);
        }
        if (_set instanceof ExecSettableFieldOperation) {
            arg_ = ((ExecSettableFieldOperation)_set).endCalculate(_conf, _nodes, _right, _stackCall);
        }
        if (_set instanceof ExecCustArrOperation) {
            arg_ = ((ExecCustArrOperation)_set).endCalculate(_conf, _nodes, _right, _stackCall);
        }
        if (_set instanceof ExecArrOperation) {
            arg_ = ((ExecArrOperation)_set).endCalculate(_conf, _nodes,_right, _stackCall);
        }
        if (_set instanceof ExecSettableCallFctOperation) {
            arg_ = ((ExecSettableCallFctOperation)_set).endCalculate(_conf, _nodes,_right, _stackCall);
        }
        return Argument.getNullableValue(arg_);
    }

    protected ExecOperatorContent getOperatorContent() {
        return operatorContent;
    }

    protected StringList getNames() {
        return names;
    }

    @Override
    public String getOper() {
        return operatorContent.getOper();
    }

    public ImplicitMethods getConverter() {
        return converter;
    }
}
