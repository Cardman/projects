package code.datacheck;

public final class ObjectClassName {

    private final Object object;

    private final String className;

    public ObjectClassName(Object _object, String _className) {
        object = _object;
        className = _className;
    }

    public Object getObject() {
        return object;
    }

    public String getClassName() {
        return className;
    }
}
