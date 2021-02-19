package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecVariableContent;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.util.IdMap;

public final class ExecStdVariableOperation extends ExecLeafOperation implements
        AtomicExecCalculableOperation,ExecSettableElResult {

    private final ExecVariableContent variableContent;

    public ExecStdVariableOperation(ExecOperationContent _opCont, ExecVariableContent _variableContent) {
        super(_opCont);
        variableContent = _variableContent;
    }

    public boolean resultCanBeSet() {
        return variableContent.isVariable();
    }


    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(variableContent.getOff(), _stack);
        AbstractPageEl ip_ = _stack.getLastPage();
        Argument arg_;
        if (resultCanBeSet()) {
            arg_ = Argument.createVoid();
        } else {
            arg_ = ExecTemplates.getWrapValue(_conf, variableContent.getVariableName(), variableContent.getDeep(), ip_.getCache(), ip_.getRefParams(), _stack);
        }
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(arg_, _conf, _nodes, _stack);
        } else {
            setSimpleArgument(arg_, _conf, _nodes, _stack);
        }
    }

    @Override
    public Argument calculateSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            Argument _right, StackCall _stack) {
        return setVar(_conf, variableContent.getVariableName(), _right, variableContent.getDeep(), _stack);
    }

    @Override
    public Argument calculateCompoundSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast, StackCall _stack) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, variableContent.isCatString(), _cl.getNames(), _cast, _stack);
        return setVar(_conf, variableContent.getVariableName(), res_, variableContent.getDeep(), _stack);
    }

    @Override
    public Argument calculateSemiSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post, byte _cast, StackCall _stack) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        setVar(_conf, variableContent.getVariableName(), res_, variableContent.getDeep(), _stack);
        return ExecSemiAffectationOperation.getPrePost(_post, left_, res_);
    }
    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack) {
        return setVar(_conf, variableContent.getVariableName(), _right, variableContent.getDeep(), _stack);
    }

    @Override
    public Argument endCalculate(ContextEl _conf,
                                 IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _post,
                                 Argument _stored, Argument _right, StackCall _stack) {
        setVar(_conf, variableContent.getVariableName(), _right, variableContent.getDeep(), _stack);
        return ExecSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    private static Argument setVar(ContextEl _conf, String _variableName, Argument _value, int _deep, StackCall _stackCall) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        return ExecTemplates.setWrapValue(_conf,_variableName, _value,_deep, ip_.getCache(), ip_.getRefParams(), _stackCall);
    }

    public ExecVariableContent getVariableContent() {
        return variableContent;
    }
}
