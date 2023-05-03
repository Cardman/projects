package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.CstFieldInfo;

public final class SrcFileLocationFieldStd extends SrcFileLocationField {

    private final CstFieldInfo cstFieldInfo;
    public SrcFileLocationFieldStd(ClassField _c, CstFieldInfo _cst) {
        super(_c);
        cstFieldInfo = _cst;
    }

    @Override
    public FileBlock getFile() {
        return null;
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public boolean match(SrcFileLocation _o) {
        return _o instanceof SrcFileLocationFieldStd && cstFieldInfo == ((SrcFileLocationFieldStd)_o).cstFieldInfo;
    }


}
