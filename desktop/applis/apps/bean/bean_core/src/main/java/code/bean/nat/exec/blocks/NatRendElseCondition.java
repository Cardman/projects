package code.bean.nat.exec.blocks;

import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.NatRendStackCall;

public final class NatRendElseCondition extends NatParentBlock {

    @Override
    public void processEl(NatConfigurationCore _cont, NatRendStackCall _rendStack) {
        RendBlockHelp.processElse(this, _rendStack);
    }

}
