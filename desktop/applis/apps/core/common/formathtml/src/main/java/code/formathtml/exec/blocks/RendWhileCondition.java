package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public final class RendWhileCondition extends RendCondition {

    private final String label;

    public RendWhileCondition(CustList<RendDynOperationNode> _op, int _offset, String _label) {
        super(_op,_offset);
        label = _label;
    }


    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        processWhile(_cont, _stds, _ctx, _rendStack, label, this);
    }

}
