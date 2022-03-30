package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatRendStackCall;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.opers.RendLeafOperation;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class NatInternGlobalOperation  extends RendLeafOperation implements NatRendCalculableOperation {
    public NatInternGlobalOperation(ExecOperationContent _content) {
        super(_content);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, NatRendStackCall _rendStack) {
        Struct struct_ = _rendStack.getInternGlobal();
        Argument arg_ = new Argument(struct_);
        calcArg(_nodes,arg_);
    }
}
