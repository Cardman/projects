package code.formathtml;

import code.util.IntTreeMap;
import code.util.StringMap;

public final class RendEmptyInstruction extends RendLeaf implements RendWithEl,RendPossibleEmpty {
    public RendEmptyInstruction(int _offsetTrim) {
        super(_offsetTrim);
        setEscapedChars(new StringMap<IntTreeMap<Integer>>());
    }

    @Override
    public void processEl(Configuration _cont) {
        processBlock(_cont);
    }
}
