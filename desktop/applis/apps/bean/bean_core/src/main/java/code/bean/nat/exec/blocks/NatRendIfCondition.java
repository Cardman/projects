package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatRendStackCall;
import code.formathtml.Configuration;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class NatRendIfCondition extends NatRendCondition {

    public NatRendIfCondition(CustList<RendDynOperationNode> _op, int _offset) {
        super(_op,_offset);
    }

    @Override
    public void processEl(Configuration _cont, NatRendStackCall _rendStack) {
        RendBlockHelp.processIf(_rendStack, this);
    }

}
