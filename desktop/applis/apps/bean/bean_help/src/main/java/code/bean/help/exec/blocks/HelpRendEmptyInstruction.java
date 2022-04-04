package code.bean.help.exec.blocks;

import code.bean.nat.exec.NatRendStackCall;
import code.formathtml.Configuration;

public final class HelpRendEmptyInstruction extends HelpRendPossibleEmpty {
//    public HelpRendEmptyInstruction() {
//        setEscapedChars(new StringMap<IntTreeMap<Integer>>());
//    }

    @Override
    public void processEl(Configuration _cont, NatRendStackCall _rendStack) {
        HelpRendBlockHelp.processBlock(_rendStack, this);
    }
}
