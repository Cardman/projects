package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.common.DisplayedStrings;

public final class SrcFileLocationVariable extends AbsSrcFileLocation {
    private final int deep;
    private final String name;
    private final FileBlock file;
    private final int index;

    public SrcFileLocationVariable(int _d,String _n,FileBlock _f, int _i) {
        deep = _d;
        name = _n;
        this.file = _f;
        index = _i;
    }

    @Override
    public FileBlockCursor cursor() {
        return new FileBlockCursor(file,index);
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        FileBlockCursor cursor_ = cursor();
        if (getDeep() < 0) {
            return new RowSrcLocation(EnSrcLocation.VARIABLE,getName(),FileBlock.name(cursor_.getFile()), cursor_.getIndex());
        }
        return new RowSrcLocation(EnSrcLocation.VARIABLE,getDeep()+"/"+getName(),FileBlock.name(cursor_.getFile()), cursor_.getIndex());
    }

    public int getDeep() {
        return deep;
    }

    public String getName() {
        return name;
    }
}
