package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.common.DisplayedStrings;
import code.util.core.StringUtil;

public final class SrcFileLocationCall extends AbsSrcFileLocation {
    private final String typeRef;

    public SrcFileLocationCall(String _t) {
        this.typeRef = _t;
    }

    @Override
    public FileBlockCursor cursor() {
        return new FileBlockCursor(null,0);
    }

    @Override
    public boolean match(SrcFileLocation _o) {
        return _o instanceof SrcFileLocationCall&& StringUtil.quickEq(typeRef,((SrcFileLocationCall)_o).typeRef);
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        FileBlockCursor cursor_ = cursor();
        return new RowSrcLocation(EnSrcLocation.REF,getTypeRef(),FileBlock.name(cursor_.getFile()), cursor_.getIndex());
    }

    public String getTypeRef() {
        return typeRef;
    }
}
