package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.util.IdMap;
import code.util.StringList;
import code.util.core.StringUtil;

public abstract class ExecCompoundAffectationOperation extends ExecAbstractAffectOperation implements CallExecSimpleOperation,CompoundedOperator {

    private final ExecOperatorContent operatorContent;
    private final ImplicitMethods converter;
    private final boolean staticPostEltContent;

    protected ExecCompoundAffectationOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent, ImplicitMethods _converter, StringList _names, boolean _staticPostEltContent) {
        super(_opCont, _operatorContent.getOpOffset(), _names);
        operatorContent = _operatorContent;
        converter = _converter;
        staticPostEltContent = _staticPostEltContent;
    }

    @Override
    protected void calculateAffect(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_nodes, getSettableAnc());
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
        if (argumentPair_.isArgumentTest()){
            pair_.setIndexImplicitCompound(-1);
            Argument tow_ = Argument.getNullableValue(argumentPair_.getArgument());
            if (sh(operatorContent)) {
                pair_.setEndCalculate(true);
                setSimpleArgument(tow_, _conf, _nodes, _stack);
                return;
            }
            Argument arg_ = ExecAffectationOperation.calculateChSetting(getSettable(),_nodes, _conf, tow_, _stack);
            pair_.setEndCalculate(true);
            setSimpleArgument(arg_, _conf, _nodes, _stack);
            return;
        }
        calculateSpec(_nodes, _conf, _stack);
    }

    public static boolean sh(ExecOperatorContent _operatorContent) {
        return StringUtil.quickEq(_operatorContent.getOper(), AbsBk.AND_LOG_EQ_SHORT) || StringUtil.quickEq(_operatorContent.getOper(), AbsBk.OR_LOG_EQ_SHORT);
    }

    protected abstract void calculateSpec(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                          ContextEl _conf, StackCall _stack);

    @Override
    public void endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack) {
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
        ArgumentsPair pairSet_ = ExecHelper.getArgumentPair(_nodes, getSettableAnc());
        Argument stored_ = Argument.getNullableValue(pairSet_.getArgumentBeforeImpl());
        setRelOffsetPossibleLastPage(operatorContent.getOpOffset(), _stack);
        int indexImplicit_ = pair_.getIndexImplicitCompound();
        if (ImplicitMethods.isValidIndex(converter,indexImplicit_)) {
            pair_.setIndexImplicitCompound(processConverter(_conf, _right, converter, indexImplicit_, _stack));
            return;
        }
        if (!pair_.isEndCalculate()) {
            pair_.setEndCalculate(true);
            Argument arg_ = ExecSemiAffectationOperation.endCalculate(_conf,_nodes,_right,stored_,getSettable(), staticPostEltContent,_stack);
            setSimpleArgument(arg_, _conf, _nodes, _stack);
            return;
        }
        if (ExecSemiAffectationOperation.isIndexer(getSettable(),_nodes)) {
            Argument out_ = ExecSemiAffectationOperation.callIndexer(_right, pair_, stored_, staticPostEltContent);
            setSimpleArgument(out_, _conf, _nodes, _stack);
            return;
        }
        setSimpleArgument(_right,_conf,_nodes, _stack);
    }

    protected ExecOperatorContent getOperatorContent() {
        return operatorContent;
    }

    @Override
    public String getOper() {
        return operatorContent.getOper();
    }

    public ImplicitMethods getConverter() {
        return converter;
    }
}
