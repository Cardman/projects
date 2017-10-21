package code.expressionlanguage.opers.util;
import java.lang.reflect.Method;

public final class MethodInfo implements Parametrable {

    private static final String RIGHT_PAR = ")";

    private static final String LEFT_PAR = "(";

    private static final String DOT = ".";

    private MethodId constraints;

    private Method method;

    private ParametersGroup parameters;

    private String className;

    private String returnType;

    private boolean staticMethod;

    @Override
    public String toString() {
        if (method == null) {
            return className+DOT+constraints.getSignature()+LEFT_PAR+parameters+RIGHT_PAR;
        }
        return method.getDeclaringClass()+DOT+method.getName()+LEFT_PAR+parameters+RIGHT_PAR;
    }

    public MethodId getConstraints() {
        return constraints;
    }

    public void setConstraints(MethodId _constraints) {
        constraints = _constraints;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method _method) {
        method = _method;
    }

    @Override
    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String _returnType) {
        returnType = _returnType;
    }

    @Override
    public ParametersGroup getParameters() {
        return parameters;
    }

    public void setParameters(ParametersGroup _parameters) {
        parameters = _parameters;
    }

    @Override
    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        className = _className;
    }

    @Override
    public boolean isStatic() {
        return staticMethod;
    }

    public void setStatic(boolean _staticMethod) {
        staticMethod = _staticMethod;
    }
}
