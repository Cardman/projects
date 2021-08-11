package code.bean.nat.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.exec.blocks.RendWithEl;
import code.formathtml.util.BeanLgNames;

public final class NatRendElseCondition extends RendParentBlock implements RendWithEl {

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        RendBlockHelp.processElse(_ctx,this, _rendStack);
    }

}
