package code.bean.nat.exec.blocks;

import code.formathtml.exec.blocks.RendParentBlock;
import code.util.IntTreeMap;
import code.util.StringMap;

public final class NatRendPackage extends RendParentBlock {

    public NatRendPackage() {
        setEscapedChars(new StringMap<IntTreeMap<Integer>>());
    }

}
