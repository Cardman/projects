package code.formathtml.exec.blocks;

import code.util.IntTreeMap;
import code.util.StringMap;

public final class RendPackage extends RendParentBlock {

    public RendPackage() {
        setEscapedChars(new StringMap<IntTreeMap<Integer>>());
    }

}
