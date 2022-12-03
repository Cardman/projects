package code.bean.nat.exec.blocks;

import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.util.CustList;

public final class NatRendIfCondition extends NatRendCondition {

    public NatRendIfCondition(CustList<NatExecOperationNode> _op) {
        super(_op);
    }

    @Override
    public void processEl(NatConfigurationCore _cont, NatRendStackCall _rendStack) {
        RendBlockHelp.processIf(_rendStack, this);
    }

}
