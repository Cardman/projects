package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.types.SimpleSegType;
import code.expressionlanguage.common.DisplayedStrings;
import code.util.core.StringUtil;

public final class SrcFileLocationInferredType extends AbsSrcFileLocationType  {
    private final String type;
    private final FileBlock file;

    public SrcFileLocationInferredType(SimpleSegType _o, String _t, FileBlock _f) {
        super(_o);
        this.type = _t;
        this.file = _f;
    }

    @Override
    public FileBlockCursor cursor() {
        return new FileBlockCursor(file,begin());
    }

    @Override
    public boolean match(SrcFileLocation _o) {
        return _o instanceof SrcFileLocationInferredType && StringUtil.quickEq(type,((SrcFileLocationInferredType)_o).type);
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        FileBlockCursor cursor_ = cursor();
        return new RowSrcLocation(EnSrcLocation.INFERRED,getType(),FileBlock.name(cursor_.getFile()), cursor_.getIndex());
    }

    public String getType() {
        return type;
    }
}
