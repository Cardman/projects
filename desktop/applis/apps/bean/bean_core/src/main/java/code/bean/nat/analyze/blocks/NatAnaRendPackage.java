package code.bean.nat.analyze.blocks;

import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.formathtml.analyze.blocks.AnaRendParentBlock;

public final class NatAnaRendPackage extends AnaRendParentBlock {
    private final String name;
    NatAnaRendPackage(OffsetStringInfo _name, int _offset) {
        super(_offset);
        name = _name.getInfo();
    }

    public String getName() {
        return name;
    }
}
