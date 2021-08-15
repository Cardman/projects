package code.bean.help.analyze.blocks;

import code.formathtml.analyze.blocks.AnaRendLeaf;
import code.util.IntTreeMap;
import code.util.StringMap;

public final class HelpAnaRendEmptyInstruction extends AnaRendLeaf {
    HelpAnaRendEmptyInstruction(int _offset) {
        super(_offset);
        setEscapedChars(new StringMap<IntTreeMap<Integer>>());
    }

}
