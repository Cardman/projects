package code.bean.nat.exec.blocks;

import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.util.BeanLgNames;

public final class NatRendElseCondition extends RendParentBlock implements NatRendWithEl {

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, RendStackCall _rendStack) {
        RendBlockHelp.processElse(this, _rendStack);
    }

}
