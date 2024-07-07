package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.types.SimpleSegType;
import code.expressionlanguage.common.DisplayedStrings;
import code.util.core.StringUtil;

public final class SrcFileLocationStdType extends AbsSrcFileLocationType  {
    private final String type;

    public SrcFileLocationStdType(SimpleSegType _o, String _t) {
        super(_o);
        this.type = _t;
    }

    @Override
    public FileBlockCursor cursor() {
        return new FileBlockCursor(null,0);
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean match(SrcFileLocation _o) {
        return _o instanceof SrcFileLocationStdType && StringUtil.quickEq(type, ((SrcFileLocationStdType)_o).type);
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        FileBlockCursor cursor_ = cursor();
        return new RowSrcLocation(EnSrcLocation.STD_TYPE,getType(),FileBlock.name(cursor_.getFile()), cursor_.getIndex());
    }
}
