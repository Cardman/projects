package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.ClassField;

public final class SrcFileLocationFieldCust extends SrcFileLocationField {
    private final RootBlock declaring;
    private final int index;

    public SrcFileLocationFieldCust(ClassField _c, RootBlock _d, int _i) {
        super(_c);
        declaring = _d;
        index = _i;
    }

    @Override
    public FileBlock getFile() {
        return declaring.getFile();
    }

    @Override
    public int getIndex() {
        return index;
    }

}
