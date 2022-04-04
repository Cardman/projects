package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatRendStackCall;
import code.formathtml.Configuration;

public final class NatRendElseCondition extends NatParentBlock {

    @Override
    public void processEl(Configuration _cont, NatRendStackCall _rendStack) {
        RendBlockHelp.processElse(this, _rendStack);
    }

}
