package code.bean.nat;

public final class StandardField {

    private final String fieldName;

    private final String className;

    private final boolean staticField;

    private final boolean finalField;

    private final NatCaller callerGet;
    private final NatCaller callerSet;
    public StandardField(String _fieldName, String _className,
                         boolean _staticField, boolean _finalField) {
        this(_fieldName,_className,_staticField,_finalField,null,null);
    }
    public StandardField(String _fieldName, String _className,
                         boolean _staticField, boolean _finalField, NatCaller _callerGet, NatCaller _callerSet) {
        fieldName = _fieldName;
        className = _className;
        staticField = _staticField;
        finalField = _finalField;
        callerGet = _callerGet;
        callerSet = _callerSet;
    }

    public NatCaller getCallerGet() {
        return callerGet;
    }

    public NatCaller getCallerSet() {
        return callerSet;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getImportedClassName() {
        return className;
    }
}
