package code.formathtml.analyze.blocks;

import code.util.IntTreeMap;
import code.util.StringMap;

public final class AnaRendEmptyInstruction extends AnaRendLeaf {
    AnaRendEmptyInstruction(int _offset) {
        super(_offset);
        setEscapedChars(new StringMap<IntTreeMap<Integer>>());
    }

}
