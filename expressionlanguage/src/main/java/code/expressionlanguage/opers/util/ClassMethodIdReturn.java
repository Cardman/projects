package code.expressionlanguage.opers.util;

public final class ClassMethodIdReturn {

    private ClassMethodId id;

    private String returnType;

    private boolean staticMethod;

    private boolean abstractMethod;

    public ClassMethodId getId() {
        return id;
    }

    public void setId(ClassMethodId _id) {
        id = _id;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String _returnType) {
        returnType = _returnType;
    }

    public boolean isStaticMethod() {
        return staticMethod;
    }

    public void setStaticMethod(boolean _staticMethod) {
        staticMethod = _staticMethod;
    }

    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public void setAbstractMethod(boolean _abstractMethod) {
        abstractMethod = _abstractMethod;
    }
}
