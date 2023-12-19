package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.types.SimpleSegType;
import code.expressionlanguage.common.DisplayedStrings;

public final class SrcFileLocationType extends AbsSrcFileLocationType  {
    private final RootBlock type;

    public SrcFileLocationType(SimpleSegType _o, RootBlock _t) {
        super(_o);
        this.type = _t;
    }

    @Override
    public FileBlock getFile() {
        return getType().getFile();
    }

    @Override
    public String getFileName() {
        return FileBlock.name(getFile());
    }

    @Override
    public int getIndex() {
        return getType().getIdRowCol();
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        return new RowSrcLocation(EnSrcLocation.TYPE,getType().getFullName(),getFileName(),getIndex());
    }

    public RootBlock getType() {
        return type;
    }
}
