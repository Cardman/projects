package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.types.SimpleSegType;
import code.expressionlanguage.common.DisplayedStrings;

public final class SrcFileLocationTypeVar extends AbsSrcFileLocationType {
    private final String name;
    private final int index;
    private final FileBlock block;

    public SrcFileLocationTypeVar(SimpleSegType _o, String _n, int _i, FileBlock _fileName) {
        super(_o);
        this.name = _n;
        this.index = _i;
        this.block = _fileName;
    }

    @Override
    public FileBlock getFile() {
        return block;
    }

    @Override
    public String getFileName() {
        return FileBlock.name(getFile());
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        return new RowSrcLocation(EnSrcLocation.TYPE_VAR,getName(),getFileName(),getIndex());
    }

    public String getName() {
        return name;
    }

}
