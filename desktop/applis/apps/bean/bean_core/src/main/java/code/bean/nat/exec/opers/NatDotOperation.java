package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatRendStackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class NatDotOperation extends NatAbstractDotOperation {

    public NatDotOperation(ExecOperationContent _content) {
        super(_content);
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, NatRendStackCall _rendStack) {
        calculateDot(_nodes);
    }
}
