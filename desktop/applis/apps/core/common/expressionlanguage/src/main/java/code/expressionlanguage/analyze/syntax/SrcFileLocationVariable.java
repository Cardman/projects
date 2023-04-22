package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.common.DisplayedStrings;

public final class SrcFileLocationVariable implements SrcFileLocation {
    private final int deep;
    private final String name;
    private final String fileName;
    private final int index;

    public SrcFileLocationVariable(int _d,String _n,String _f, int _i) {
        deep = _d;
        name = _n;
        this.fileName = _f;
        index = _i;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        if (getDeep() < 0) {
            return new RowSrcLocation(EnSrcLocation.VARIABLE,getName(),getFileName(),getIndex());
        }
        return new RowSrcLocation(EnSrcLocation.VARIABLE,getDeep()+"/"+getName(),getFileName(),getIndex());
    }

    public int getDeep() {
        return deep;
    }

    public String getName() {
        return name;
    }
}
