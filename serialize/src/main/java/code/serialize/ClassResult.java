package code.serialize;

public final class ClassResult {

    private Object object;

    private boolean success;

    public Object getObject() {
        return object;
    }

    public void setObject(Object _object) {
        object = _object;
        success = _object != null;
    }

    public boolean isSuccess() {
        return success;
    }
}
