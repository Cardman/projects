package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;

public final class RendDoBlock extends RendParentBlock implements RendWithEl {

    private final String label;

    public RendDoBlock(String _label) {
        label = _label;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        processDo(_cont, _stds, _ctx, _rendStack, label, this);
    }

}
