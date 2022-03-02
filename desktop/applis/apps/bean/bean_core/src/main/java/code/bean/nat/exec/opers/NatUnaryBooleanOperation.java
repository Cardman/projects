package code.bean.nat.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendCalculableOperation;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.opers.RendMethodOperation;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class NatUnaryBooleanOperation extends RendMethodOperation implements RendCalculableOperation {
    public NatUnaryBooleanOperation(ExecOperationContent _content) {
        super(_content);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        Argument arg_ = getArgument(_nodes,getFirstNode(this));
        BooleanStruct o_ = NumParsers.convertToBoolean(arg_.getStruct());
        Argument a_ = new Argument(o_.neg());
        calcArg(_nodes,a_);
    }
}
