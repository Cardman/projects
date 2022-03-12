package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.util.IdMap;
import code.util.StringList;

public final class ExecCompoundAffectationStringOperation extends ExecCompoundAffectationOperation {


    public ExecCompoundAffectationStringOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent, StringList _names) {
        super(_opCont, _operatorContent, null, _names, false);
    }


    @Override
    protected void calculateSpec(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument leftArg_ = getFirstArgument(_nodes,this);
        Argument rightArg_ = getLastArgument(_nodes,this);
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes,this);
        setRelOffsetPossibleLastPage(getOperatorContent().getOpOffset(), _stack);
        Argument res_ = ExecCatOperation.localSumDiff(leftArg_, rightArg_, _conf);
        Argument arg_ = calculateChSetting(_nodes,_conf,res_,_stack);
        pair_.setEndCalculate(true);
        pair_.setIndexer(_conf.callsOrException(_stack));
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

}
