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
    public FileBlockCursor cursor() {
        return new FileBlockCursor(getType().getFile(),getType().getIdRowCol());
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        FileBlockCursor cursor_ = cursor();
        return new RowSrcLocation(EnSrcLocation.TYPE,getType().getFullName(),FileBlock.name(cursor_.getFile()), cursor_.getIndex());
    }

    public RootBlock getType() {
        return type;
    }
}
