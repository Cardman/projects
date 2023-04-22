package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.common.DisplayedStrings;

public final class SrcFileLocationLabel implements SrcFileLocation {
    private final String label;
    private final String fileName;
    private final int index;

    public SrcFileLocationLabel(String _n, String _f, int _i) {
        label = _n;
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
        return new RowSrcLocation(EnSrcLocation.LABEL,getLabel(),getFileName(),getIndex());
    }

    public String getLabel() {
        return label;
    }
}
