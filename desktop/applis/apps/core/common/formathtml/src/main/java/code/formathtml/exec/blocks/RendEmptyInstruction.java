package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.IntTreeMap;
import code.util.StringMap;

public final class RendEmptyInstruction extends RendLeaf implements RendWithEl,RendPossibleEmpty {
    public RendEmptyInstruction(int _offsetTrim) {
        super(_offsetTrim);
        setEscapedChars(new StringMap<IntTreeMap<Integer>>());
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx) {
        processBlock(_cont, _stds, _ctx);
    }
}
