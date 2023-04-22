package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.DisplayedStrings;

public final class SrcFileLocationField implements SrcFileLocation {
    private final ClassField cf;
    private final String fileName;
    private final int index;

    public SrcFileLocationField(ClassField _c,String _f, int _i) {
        cf = _c;
        this.fileName = _f;
        index = _i;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        return new RowSrcLocation(EnSrcLocation.FIELD,getCf().getClassName()+"."+getCf().getFieldName(),getFileName(),getIndex());
    }

    public ClassField getCf() {
        return cf;
    }
}
