package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatRendStackCall;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;

public final class NatRendEmptyInstruction extends NatRendPossibleEmpty implements NatRendWithEl {
//    public NatRendEmptyInstruction() {
//        setEscapedChars(new StringMap<IntTreeMap<Integer>>());
//    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, NatRendStackCall _rendStack) {
        RendBlockHelp.processBlock(_rendStack, this);
    }
}
