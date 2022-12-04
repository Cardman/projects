package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.*;
import code.util.CustList;
import code.util.IdMap;

public abstract class NatExecOperationNode implements NatRendCalculableOperation {
    private final int order;
    private NatExecMethodOperation par;
    private NatExecPossibleIntermediateDotted sibling;

    protected NatExecOperationNode(int _o) {
        this.order = _o;
    }
    public void setParent(NatExecMethodOperation _parent) {
        par = _parent;
    }


    public static CustList<NaSt> getArguments(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatExecMethodOperation _method) {
        CustList<NaSt> a_ = new CustList<NaSt>();
        for (NatExecOperationNode o: _method.getChildrenNodes()) {
            a_.add(getArgument(_nodes, o));
        }
        return a_;
    }
    protected static NaSt getArgument(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatExecOperationNode _node) {
        int order_ = _node.getOrder();
        return _nodes.getValue(order_).getArgument();
    }

    protected NaSt getPreviousArg(NatExecPossibleIntermediateDotted _possible, IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NatRendStackCall _rendStackCall) {
        NaSt previous_;
        if (_possible.isIntermediateDottedOperation()) {
            previous_ = _nodes.getValue(getOrder()).getPreviousArgument();
        } else {
            previous_ = _rendStackCall.getLastPage().getGlobalArgument();
        }
        return previous_;
    }

    public final NatExecMethodOperation getParent() {
        return par;
    }

    public final int getOrder() {
        return order;
    }

    protected void calcArg(IdMap<NatExecOperationNode, NatArgumentsPair> _nodes, NaSt _out) {
        NatExecPossibleIntermediateDotted n_ = getSiblingSet();
        if (n_ != null) {
            _nodes.getValue(n_.getOrder()).setPreviousArgument(_out);
        }
        _nodes.getValue(getOrder()).setArgument(_out);
    }

    public final NatExecPossibleIntermediateDotted getSiblingSet() {
        return sibling;
    }

    public final void setSiblingSet(NatExecPossibleIntermediateDotted _siblingSet) {
        sibling = _siblingSet;
    }

}
