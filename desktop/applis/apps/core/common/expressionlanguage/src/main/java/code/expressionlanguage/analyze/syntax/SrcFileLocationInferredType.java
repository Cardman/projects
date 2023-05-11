package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.common.DisplayedStrings;
import code.util.core.StringUtil;

public final class SrcFileLocationInferredType extends AbsSrcFileLocationType  {
    private final String type;
    private final FileBlock file;

    public SrcFileLocationInferredType(int _o, String _t, FileBlock _f) {
        super(_o);
        this.type = _t;
        this.file = _f;
    }

    @Override
    public FileBlock getFile() {
        return file;
    }

    @Override
    public String getFileName() {
        return FileBlock.name(getFile());
    }

    @Override
    public int getIndex() {
        return getOffset();
    }

    @Override
    public boolean match(SrcFileLocation _o) {
        return _o instanceof SrcFileLocationInferredType && StringUtil.quickEq(type,((SrcFileLocationInferredType)_o).type);
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        return new RowSrcLocation(EnSrcLocation.INFERRED,getType(),getFileName(),getIndex());
    }

    public String getType() {
        return type;
    }
}
