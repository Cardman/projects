package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatRendStackCall;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.util.BeanLgNames;

public final class NatRendElseCondition extends RendParentBlock implements NatRendWithEl {

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, NatRendStackCall _rendStack) {
        RendBlockHelp.processElse(this, _rendStack);
    }

}
