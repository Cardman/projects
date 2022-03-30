package code.bean.help.exec.blocks;

import code.bean.nat.exec.blocks.NatRendWithEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;

public final class HelpRendEmptyInstruction extends HelpRendPossibleEmpty implements NatRendWithEl {
//    public HelpRendEmptyInstruction() {
//        setEscapedChars(new StringMap<IntTreeMap<Integer>>());
//    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds,  RendStackCall _rendStack) {
        HelpRendBlockHelp.processBlock(_rendStack, this);
    }
}
