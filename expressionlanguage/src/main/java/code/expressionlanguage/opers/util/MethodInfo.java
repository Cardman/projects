package code.expressionlanguage.opers.util;
import java.lang.reflect.Method;

public final class MethodInfo implements Parametrable {

    private static final String RIGHT_PAR = ")";

    private static final String LEFT_PAR = "(";

    private static final String DOT = ".";

    private FctConstraints constraints;

    private Method method;

    private ParametersGroup parameters;

    private String className;

    @Override
    public String toString() {
        if (method == null) {
            return className+DOT+constraints.getSignature()+LEFT_PAR+parameters+RIGHT_PAR;
        }
        return method.getDeclaringClass()+DOT+method.getName()+LEFT_PAR+parameters+RIGHT_PAR;
    }

    public FctConstraints getConstraints() {
        return constraints;
    }

    public void setConstraints(FctConstraints _constraints) {
        constraints = _constraints;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method _method) {
        method = _method;
    }

    @Override
    public ParametersGroup getParameters() {
        return parameters;
    }

    public void setParameters(ParametersGroup _parameters) {
        parameters = _parameters;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        className = _className;
    }

    @Override
    public Class<?> getDeclaringClass() {
        if (method == null) {
            return null;
        }
        return method.getDeclaringClass();
    }
}
