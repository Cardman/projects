package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.DisplayedStrings;

public abstract class SrcFileLocationField extends AbsSrcFileLocation {
    private final ClassField cf;

    protected SrcFileLocationField(ClassField _c) {
        cf = _c;
    }

    @Override
    public String getFileName() {
        return FileBlock.name(getFile());
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        return new RowSrcLocation(EnSrcLocation.FIELD,getCf().getClassName()+"."+getCf().getFieldName(),getFileName(),getIndex());
    }

    public ClassField getCf() {
        return cf;
    }
}
