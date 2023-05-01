package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.common.DisplayedStrings;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;

public final class SrcFileLocationStdMethod extends AbsSrcFileLocation  {
    private final StandardType type;
    private final StandardNamedFunction std;

    public SrcFileLocationStdMethod(StandardType _t, StandardNamedFunction _s) {
        type = _t;
        this.std = _s;
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
    public RowSrcLocation build(DisplayedStrings _dis) {
        return new RowSrcLocation(EnSrcLocation.STD_METHOD,type.getFullName()+"."+getStd().getSignature(_dis), getFileName(),getIndex());
    }
    public StandardNamedFunction getStd() {
        return std;
    }
}
