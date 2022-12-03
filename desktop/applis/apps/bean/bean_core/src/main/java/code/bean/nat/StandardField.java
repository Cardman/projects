package code.bean.nat;

public final class StandardField {

    private final String fieldName;

    private final String className;

    private final NatCaller callerGet;
    private final NatCaller callerSet;
    public StandardField(String _fieldName, String _className,
                         NatCaller _callerGet, NatCaller _callerSet) {
        fieldName = _fieldName;
        className = _className;
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
