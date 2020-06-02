package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.variables.LocalVariable;
import code.util.IdMap;

public final class ExecInternVariableOperation extends ExecLeafOperation implements AtomicExecCalculableOperation {

    private String variableName;

    public ExecInternVariableOperation(int _indexChild, ClassArgumentMatching _res, int _order, String _varName) {
        super(_indexChild,_res,_order);
        variableName = _varName;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        LocalVariable locVar_ = ip_.getInternVars().getVal(variableName);
        Argument a_ = new Argument();
        a_.setStruct(locVar_.getStruct());
        setSimpleArgument(a_, _conf, _nodes);
    }

}
