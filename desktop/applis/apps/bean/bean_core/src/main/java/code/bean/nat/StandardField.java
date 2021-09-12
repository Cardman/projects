package code.bean.nat;

public final class StandardField {

    private final String fieldName;

    private final String className;

    private final boolean staticField;

    private final boolean finalField;

    public StandardField(String _fieldName, String _className,
                         boolean _staticField, boolean _finalField) {
        fieldName = _fieldName;
        className = _className;
        staticField = _staticField;
        finalField = _finalField;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getImportedClassName() {
        return className;
    }
}
