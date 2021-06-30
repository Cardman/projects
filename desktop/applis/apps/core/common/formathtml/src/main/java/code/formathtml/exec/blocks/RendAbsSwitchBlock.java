package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public abstract class RendAbsSwitchBlock extends RendParentBlock implements RendWithEl {

    private final String label;
    private final RendOperationNodeListOff value;

    private final String instanceTest;

    protected RendAbsSwitchBlock(String _label, int _valueOffset, CustList<RendDynOperationNode> _opValue, String _instanceTest) {
        this.label = _label;
        value = new RendOperationNodeListOff(_opValue,_valueOffset);
        this.instanceTest = _instanceTest;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        processSwitch(_cont, _stds, _ctx, _rendStack, label, value, this);
    }

    public String getInstanceTest() {
        return instanceTest;
    }

}
