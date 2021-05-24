package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;

public final class RendElseCondition extends RendParentBlock implements RendWithEl {

    public RendElseCondition(int _offsetTrim) {
        super(_offsetTrim);
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        processElse(_cont,_stds,_ctx,this, _rendStack);
    }

}
