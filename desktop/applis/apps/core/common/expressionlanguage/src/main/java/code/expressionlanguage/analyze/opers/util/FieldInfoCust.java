package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.InfoBlock;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.CstFieldInfo;

public final class FieldInfoCust extends FieldInfo {
    private final Accessed accessed;
    private final int valOffset;
    private final String fileName;
    private final MemberId memberId = new MemberId();
    public FieldInfoCust(ClassField _id, InfoBlock _i, Accessed _accessed, int _valOffset, String _fileName, int _rootNumber) {
        super(_id, _i.getImportedClassName(), _i.isStaticField(), _i.isFinalField());
        accessed = _accessed;
        valOffset = _valOffset;
        fileName = _fileName;
        memberId.setRootNumber(_rootNumber);
        memberId.setMemberNumber(_i.getElements().getFieldNumber());
    }

    public Accessed getAccessed() {
        return accessed;
    }

    public int getValOffset() {
        return valOffset;
    }

    public String getFileName() {
        return fileName;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    @Override
    public CstFieldInfo cst() {
        return null;
    }

}
