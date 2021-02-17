package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecVariableContent;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecRefParamOperation extends ExecLeafOperation implements
        AtomicExecCalculableOperation,ExecSettableElResult {

    private final ExecVariableContent variableContent;
    public ExecRefParamOperation(ExecOperationContent _l, ExecVariableContent _variableContent) {
        super(_l);
        variableContent = _variableContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        PageEl ip_ = _stack.getLastPage();
        AbstractWrapper val_ = ExecTemplates.getWrapper(variableContent.getVariableName(),variableContent.getDeep(), ip_.getCache(), _stack.getLastPage().getRefParams());
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
        pair_.setWrapper(val_);
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(ExecTemplates.getArgValue(val_,_conf, _stack), _conf, _nodes, _stack);
        } else {
            setSimpleArgument(ExecTemplates.getArgValue(val_,_conf, _stack), _conf, _nodes, _stack);
        }
    }


    public Argument calculateSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right, StackCall _stack) {
        return trySetArgument(_conf, _right, _stack);
    }


    public Argument calculateCompoundSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast, StackCall _stack) {
        Struct store_ = ExecTemplates.getWrapValue(_conf,variableContent.getVariableName(),variableContent.getDeep(), _stack.getLastPage().getCache(), _stack.getLastPage().getRefParams(), _stack).getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, variableContent.isCatString(), _cl.getNames(), _cast, _stack);
        return trySetArgument(_conf, res_, _stack);
    }


    public Argument calculateSemiSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, String _op, boolean _post, byte _cast, StackCall _stack) {
        Struct store_ = ExecTemplates.getWrapValue(_conf,variableContent.getVariableName(),variableContent.getDeep(), _stack.getLastPage().getCache(), _stack.getLastPage().getRefParams(), _stack).getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        trySetArgument(_conf, res_, _stack);
        return ExecSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    @Override
    public boolean resultCanBeSet() {
        return variableContent.isVariable();
    }


    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack) {
        return trySetArgument(_conf, _right, _stack);
    }


    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _post, Argument _stored, Argument _right, StackCall _stack) {
        trySetArgument(_conf, _right, _stack);
        return ExecSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    private Argument trySetArgument(ContextEl _conf, Argument _res, StackCall _stackCall) {
        return ExecTemplates.setWrapValue(_conf,variableContent.getVariableName(), _res,variableContent.getDeep(), _stackCall.getLastPage().getCache(), _stackCall.getLastPage().getRefParams(), _stackCall);
    }
}

