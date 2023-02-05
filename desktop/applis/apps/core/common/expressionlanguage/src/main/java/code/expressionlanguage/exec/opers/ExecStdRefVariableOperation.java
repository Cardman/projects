package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecVariableTemplates;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecVariableContent;
import code.util.IdMap;

public final class ExecStdRefVariableOperation extends ExecLeafOperation implements
        AtomicExecCalculableOperation,ExecSettableElResult {
    private final ExecVariableContent variableContent;
    private final boolean declare;
    public ExecStdRefVariableOperation(ExecOperationContent _l, ExecVariableContent _variableContent, boolean _declare) {
        super(_l);
        variableContent = _variableContent;
        declare = _declare;
    }
    public ExecStdRefVariableOperation(ExecOperationContent _l, ExecVariableContent _variableContent) {
        super(_l);
        variableContent = _variableContent;
        declare = false;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(variableContent.getOff(), _stack);
        AbstractWrapper val_ = ExecVariableTemplates.getWrapper(variableContent, _stack);
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
        if (resultCanBeSet() && val_ instanceof VariableWrapper && !variableContent.isRef() && variableContent.getDeep() < 0) {
            pair_.setWrapper(new VariableWrapper(LocalVariable.newLocalVariable(val_.getValue(_stack, _conf), val_.getClassName(_stack,_conf))));
            _stack.getLastPage().getRefParams().put(variableContent.getVariableName(), pair_.getWrapper());
        } else {
            pair_.setWrapper(val_);
        }
        Argument arg_ = ExecVariableTemplates.getArgValue(val_, _conf, _stack);
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(arg_, _conf, _nodes, _stack);
        } else {
            setSimpleArgument(arg_, _conf, _nodes, _stack);
        }

    }

    public String getVariableName() {
        return variableContent.getVariableName();
    }

    @Override
    public Argument calculateSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right, StackCall _stack) {
        return trySetArgument(_nodes,_conf, _right, _stack);
    }

    @Override
    public boolean resultCanBeSet() {
        return variableContent.isVariable();
    }

    private Argument trySetArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _res, StackCall _stackCall) {
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, this);
        return ExecVariableTemplates.trySetArgument(_conf, _res, pair_, _stackCall);
    }
    public boolean isDeclare() {
        return declare;
    }
}
