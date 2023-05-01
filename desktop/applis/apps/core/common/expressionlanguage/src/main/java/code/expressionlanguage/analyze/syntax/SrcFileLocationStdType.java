package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.common.DisplayedStrings;

public final class SrcFileLocationStdType extends AbsSrcFileLocation  {
    private final String type;

    public SrcFileLocationStdType(String _t) {
        this.type = _t;
    }

    @Override
    public FileBlock getFile() {
        return null;
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
