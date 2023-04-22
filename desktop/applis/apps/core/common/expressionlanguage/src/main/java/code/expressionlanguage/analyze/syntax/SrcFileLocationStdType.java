package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.common.DisplayedStrings;

public final class SrcFileLocationStdType implements SrcFileLocation {
    private final String type;

    public SrcFileLocationStdType(String _t) {
        this.type = _t;
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
    public RowSrcLocation build(DisplayedStrings _dis) {
        return new RowSrcLocation(EnSrcLocation.STD_TYPE,getType(),getFileName(),getIndex());
    }

    public String getType() {
        return type;
    }
}
