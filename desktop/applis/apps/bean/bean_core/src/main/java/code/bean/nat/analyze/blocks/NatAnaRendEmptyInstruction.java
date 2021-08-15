package code.bean.nat.analyze.blocks;

import code.formathtml.analyze.blocks.AnaRendLeaf;
import code.util.IntTreeMap;
import code.util.StringMap;

public final class NatAnaRendEmptyInstruction extends AnaRendLeaf {
    NatAnaRendEmptyInstruction(int _offset) {
        super(_offset);
        setEscapedChars(new StringMap<IntTreeMap<Integer>>());
    }

}
