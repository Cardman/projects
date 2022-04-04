package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatRendStackCall;
import code.util.IdMap;

public interface NatRendCalculableOperation {

    void calculate(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStack);

}
