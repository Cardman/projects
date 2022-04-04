package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.fwd.opers.NatExecVariableContent;
import code.expressionlanguage.Argument;
import code.util.IdMap;

public final class NatFinalVariableOperation extends NatExecLeafOperation implements NatRendCalculableOperation {

    private final NatExecVariableContent variableContent;

    public NatFinalVariableOperation(int _o, NatExecVariableContent _variableContent) {
        super(_o);
        variableContent = _variableContent;
    }

    @Override
    public void calculate(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStack) {
        Argument arg_ = NatStdRefVariableOperation.getIndexLoop(variableContent, _rendStack.getLastPage().getVars());
        calcArg(_nodes, arg_);
    }

}
