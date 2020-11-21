package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.opers.util.MemberId;

public final class ImportedField {
    private int imported;
    private String type;
    private boolean finalField;
    private int valueOffset;
    private String fileName;
    private RootBlock fieldType;
    private MemberId memberId = new MemberId();

    public ImportedField(int _imported, String _type, boolean _finalField, int _valueOffset) {
        this.imported = _imported;
        this.type = _type;
        this.finalField = _finalField;
        valueOffset = _valueOffset;
    }

    public int getImported() {
        return imported;
    }

    public String getType() {
        return type;
    }

    public boolean isFinalField() {
        return finalField;
    }

    public int getValueOffset() {
        return valueOffset;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        this.fileName = _fileName;
    }

    public MemberId getMemberId() {
        return memberId;
    }
    public void memberId(int _rootNumber,int _memberNumber) {
        memberId.setRootNumber(_rootNumber);
        memberId.setMemberNumber(_memberNumber);
    }

    public RootBlock getFieldType() {
        return fieldType;
    }

    public void setFieldType(RootBlock _fieldType) {
        fieldType = _fieldType;
    }
}
