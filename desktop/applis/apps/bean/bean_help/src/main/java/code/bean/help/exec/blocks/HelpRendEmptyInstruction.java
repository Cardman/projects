package code.bean.help.exec.blocks;

import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.NatRendStackCall;

public final class HelpRendEmptyInstruction extends HelpRendPossibleEmpty {
//    public HelpRendEmptyInstruction() {
//        setEscapedChars(new StringMap<IntTreeMap<Integer>>());
//    }

    @Override
    public void processEl(NatConfigurationCore _cont, NatRendStackCall _rendStack) {
        HelpRendBlockHelp.processBlock(_rendStack, this);
    }
}
