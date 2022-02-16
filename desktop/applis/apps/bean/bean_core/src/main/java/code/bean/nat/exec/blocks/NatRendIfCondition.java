package code.bean.nat.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public final class NatRendIfCondition extends NatRendCondition {

    private final String label;

    public NatRendIfCondition(CustList<RendDynOperationNode> _op, int _offset, String _label) {
        super(_op,_offset);
        label = _label;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        RendBlockHelp.processIf(_stds, _ctx, _rendStack, label, this);
    }

}
