package code.bean.nat.exec.opers;

import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public interface NatRendCalculableOperation {

    void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, RendStackCall _rendStack);

}
