package code.expressionlanguage.stds;

public final class StandardField {

    private final String fieldName;

    private final String className;

    private final boolean staticField;

    private final boolean finalField;

    private final StandardType owner;

    public StandardField(String _fieldName, String _className,
            boolean _staticField, boolean _finalField, StandardType _owner) {
        fieldName = _fieldName;
        className = _className;
        staticField = _staticField;
        finalField = _finalField;
        owner = _owner;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getImportedClassName() {
        return className;
    }
}
