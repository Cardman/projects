package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.DisplayedStrings;

public final class SrcFileLocationField extends AbsSrcFileLocation {
    private final ClassField cf;
    private final RootBlock declaring;
    private final int index;

    public SrcFileLocationField(ClassField _c, RootBlock _d, int _i) {
        cf = _c;
        declaring = _d;
        index = _i;
    }

    @Override
    public FileBlock getFile() {
        if (declaring != null) {
            return declaring.getFile();
        }
        return null;
    }

    @Override
    public String getFileName() {
        return FileBlock.name(getFile());
    }

    @Override
    public int getIndex() {
        if (declaring != null) {
            return index;
        }
        return 0;
    }

    @Override
    public boolean match(SrcFileLocation _o) {
        return _o instanceof SrcFileLocationField && getCf().eq(((SrcFileLocationField) _o).getCf());
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        return new RowSrcLocation(EnSrcLocation.FIELD,getCf().getClassName()+"."+getCf().getFieldName(),getFileName(),getIndex());
    }

    public ClassField getCf() {
        return cf;
    }
}
