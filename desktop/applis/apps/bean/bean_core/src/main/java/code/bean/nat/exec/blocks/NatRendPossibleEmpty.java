package code.bean.nat.exec.blocks;

import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.NatRendStackCall;

public final class NatRendPossibleEmpty extends NatBlock {

    @Override
    public void processEl(NatConfigurationCore _cont, NatRendStackCall _rendStack) {
        RendBlockHelp.processBlock(_rendStack, this);
    }
}
