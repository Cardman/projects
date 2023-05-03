package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.CstFieldInfo;

public final class FieldInfoStd extends FieldInfo {
    private final CstFieldInfo cstFieldInfo;
    public FieldInfoStd(CstFieldInfo _cst, ClassField _id, String _type) {
        super(_id, _type, true, true);
        cstFieldInfo = _cst;
    }

    @Override
    public Accessed getAccessed() {
        return null;
    }

    @Override
    public int getValOffset() {
        return -1;
    }

    @Override
    public String getFileName() {
        return "";
    }

    @Override
    public MemberId getMemberId() {
        return new MemberId();
    }

    @Override
    public CstFieldInfo cst() {
        return cstFieldInfo;
    }
}
