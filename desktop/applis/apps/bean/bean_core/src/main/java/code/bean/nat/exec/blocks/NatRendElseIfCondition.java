package code.bean.nat.exec.blocks;

import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public final class NatRendElseIfCondition extends NatRendCondition {

    public NatRendElseIfCondition(CustList<RendDynOperationNode> _op, int _offset) {
        super(_op,_offset);
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, RendStackCall _rendStack) {
        RendBlockHelp.processElseIf(_stds, this, _rendStack);
    }

}
