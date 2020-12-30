package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IntTreeMap;
import code.util.StringMap;

public final class RendEmptyInstruction extends RendPossibleEmpty {
    public RendEmptyInstruction(int _offsetTrim) {
        super(_offsetTrim);
        setEscapedChars(new StringMap<IntTreeMap<Integer>>());
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        processBlock(_cont, _stds, _ctx, _stack, _rendStack);
    }
}
