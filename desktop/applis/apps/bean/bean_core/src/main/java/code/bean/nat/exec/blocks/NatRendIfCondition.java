package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatRendStackCall;
import code.formathtml.Configuration;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class NatRendIfCondition extends NatRendCondition {

    private final String label;

    public NatRendIfCondition(CustList<RendDynOperationNode> _op, int _offset, String _label) {
        super(_op,_offset);
        label = _label;
    }

    @Override
    public void processEl(Configuration _cont, NatRendStackCall _rendStack) {
        RendBlockHelp.processIf(_rendStack, label, this);
    }

}
