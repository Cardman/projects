package code.bean.help.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IntTreeMap;
import code.util.StringMap;

public final class HelpRendEmptyInstruction extends HelpRendPossibleEmpty {
    public HelpRendEmptyInstruction() {
        setEscapedChars(new StringMap<IntTreeMap<Integer>>());
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        HelpRendBlockHelp.processBlock(_rendStack, this);
    }
}
