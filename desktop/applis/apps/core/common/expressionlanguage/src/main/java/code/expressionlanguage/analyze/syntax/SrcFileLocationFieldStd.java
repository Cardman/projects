package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.common.ClassField;

public final class SrcFileLocationFieldStd extends SrcFileLocationField {

    public SrcFileLocationFieldStd(ClassField _c) {
        super(_c);
    }

    @Override
    public FileBlock getFile() {
        return null;
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public boolean match(SrcFileLocation _o) {
        return _o instanceof SrcFileLocationFieldStd && getCf().eq(((SrcFileLocationFieldStd) _o).getCf());
    }


}
