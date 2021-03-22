package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
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
        Argument arg_;
        if (resultCanBeSet()) {
            arg_ = Argument.createVoid();
        } else {
            arg_ = ExecTemplates.getWrapArgument(_conf, variableContent, _stack);
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
        return ExecTemplates.setWrapValue(_conf,variableContent,_right,_stack);
    }

    @Override
    public Argument calculateCompoundSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast, StackCall _stack) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, variableContent.isCatString(), _cl.getNames(), _cast, _stack);
        return ExecTemplates.setWrapValue(_conf,variableContent,res_,_stack);
    }

    @Override
    public Argument calculateSemiSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post, byte _cast, StackCall _stack) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        ExecTemplates.setWrapValue(_conf,variableContent,res_,_stack);
        return ExecSemiAffectationOperation.getPrePost(_post, left_, res_);
    }
    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right, StackCall _stack) {
        return ExecTemplates.setWrapValue(_conf,variableContent,_right,_stack);
    }

    @Override
    public Argument endCalculate(ContextEl _conf,
                                 IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _post,
                                 Argument _stored, Argument _right, StackCall _stack) {
        ExecTemplates.setWrapValue(_conf,variableContent,_right,_stack);
        return ExecSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    public ExecVariableContent getVariableContent() {
        return variableContent;
    }
}
