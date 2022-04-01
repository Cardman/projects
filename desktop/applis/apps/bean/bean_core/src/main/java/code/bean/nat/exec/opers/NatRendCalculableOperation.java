package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatRendStackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.IdMap;

public interface NatRendCalculableOperation {

    void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, NatRendStackCall _rendStack);

}
