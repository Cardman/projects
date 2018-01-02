package code.expressionlanguage.opers.util;
import java.lang.reflect.Method;

import code.util.ints.Displayable;

public final class MethodInfo implements Parametrable, Displayable {

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
    public String display() {
        StringBuilder str_ = new StringBuilder();
        if (method == null) {
            str_.append(className);
            str_.append(DOT);
            str_.append(constraints.getSignature());
            str_.append(LEFT_PAR);
            str_.append(parameters.display());
            str_.append(RIGHT_PAR);
            return str_.toString();
        }
        str_.append(method.getDeclaringClass().getName());
        str_.append(DOT);
        str_.append(method.getName());
        str_.append(LEFT_PAR);
        str_.append(parameters.display());
        str_.append(RIGHT_PAR);
        return str_.toString();
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

    @Override
    public boolean isVararg() {
        if (method == null) {
            return constraints.isVararg();
        }
        return method.isVarArgs();
    }

    @Override
    public Identifiable getId() {
        return getConstraints();
    }
}
