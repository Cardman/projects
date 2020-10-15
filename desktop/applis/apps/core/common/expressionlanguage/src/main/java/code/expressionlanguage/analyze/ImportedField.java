package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.accessing.Accessed;

public final class ImportedField {
    private int imported;
    private Accessed geneField;
    private String type;
    private boolean finalField;
    private int valueOffset;
    private String fileName;
    private int rootNumber;
    private int memberNumber;

    public ImportedField(int _imported, Accessed _geneField, String _type, boolean _finalField, int _valueOffset) {
        this.imported = _imported;
        this.geneField = _geneField;
        this.type = _type;
        this.finalField = _finalField;
        valueOffset = _valueOffset;
    }

    public int getImported() {
        return imported;
    }

    public Accessed getReturnType() {
        return geneField;
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

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int _memberNumber) {
        this.memberNumber = _memberNumber;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public void setRootNumber(int _rootNumber) {
        this.rootNumber = _rootNumber;
    }
}
