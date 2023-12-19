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
    public boolean match(SrcFileLocation _o) {
        return _o instanceof SrcFileLocationStdType && StringUtil.quickEq(type, ((SrcFileLocationStdType)_o).type);
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        return new RowSrcLocation(EnSrcLocation.STD_TYPE,getType(),getFileName(),getIndex());
    }

    public String getType() {
        return type;
    }
}
