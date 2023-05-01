package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.common.DisplayedStrings;

public final class SrcFileLocationCall extends AbsSrcFileLocation {
    private final String typeRef;

    public SrcFileLocationCall(String _t) {
        this.typeRef = _t;
    }

    @Override
    public String getFileName() {
        return "";
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public FileBlock getFile() {
        return null;
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        return new RowSrcLocation(EnSrcLocation.REF,getTypeRef(),getFileName(),getIndex());
    }

    public String getTypeRef() {
        return typeRef;
    }
}
