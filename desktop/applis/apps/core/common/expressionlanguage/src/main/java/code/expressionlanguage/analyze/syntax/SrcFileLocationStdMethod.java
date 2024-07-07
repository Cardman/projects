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
    public FileBlockCursor cursor() {
        return new FileBlockCursor(null,0);
    }

    public StandardNamedFunction getStd() {
        return std;
    }

    @Override
    public boolean match(SrcFileLocation _o) {
        return _o instanceof SrcFileLocationStdMethod && std == ((SrcFileLocationStdMethod)_o).std;
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        FileBlockCursor cursor_ = cursor();
        return new RowSrcLocation(EnSrcLocation.STD_METHOD,type.getFullName()+"."+getStd().getSignature(_dis), FileBlock.name(cursor_.getFile()), cursor_.getIndex());
    }
}
