package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.InternVariableOperation;
import code.expressionlanguage.variables.LocalVariable;
import code.util.IdMap;

public final class ExecInternVariableOperation extends ExecLeafOperation implements AtomicExecCalculableOperation {

    private String variableName;

    public ExecInternVariableOperation(InternVariableOperation _i) {
        super(_i);
        variableName = _i.getVariableName();
    }

    @Override
    public void tryCalculateNode(Analyzable _conf) {
    }

    @Override
    public Argument calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        LocalVariable locVar_ = ip_.getInternVars().getVal(variableName);
        Argument a_ = new Argument();
        a_.setStruct(locVar_.getStruct());
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }

}
