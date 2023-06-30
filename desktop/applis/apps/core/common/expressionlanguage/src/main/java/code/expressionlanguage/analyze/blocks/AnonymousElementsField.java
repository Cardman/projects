package code.expressionlanguage.analyze.blocks;

import code.util.StringList;

public final class AnonymousElementsField {

    private final StringList fieldName = new StringList();

    private int fieldNumber;

    private String importedClassName;

    public StringList getFieldName() {
        return fieldName;
    }

    public int getFieldNumber() {
        return fieldNumber;
    }

    public void setFieldNumber(int _f) {
        this.fieldNumber = _f;
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    public void setImportedClassName(String _i) {
        this.importedClassName = _i;
    }
}
