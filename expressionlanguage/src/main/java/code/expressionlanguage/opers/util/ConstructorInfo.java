package code.expressionlanguage.opers.util;
import java.lang.reflect.Constructor;

import code.expressionlanguage.PrimitiveTypeUtil;

public final class ConstructorInfo implements Parametrable {

    private static final String RIGHT_PAR = ")";

    private static final String LEFT_PAR = "(";

    private ConstructorId constr;

    private ConstructorId constraints;

    private Constructor<?> constructor;

    private String className;

    private ParametersGroup parameters;

    @Override
    public String toString() {
        if (constructor == null) {
            return constr.getSignature();
        }
        return constructor.getDeclaringClass()+LEFT_PAR+parameters+RIGHT_PAR;
    }

    public ConstructorId getConstr() {
        return constr;
    }

    public void setConstr(ConstructorId _constr) {
        constr = _constr;
    }

    public ConstructorId getConstraints() {
        return constraints;
    }

    public void setConstraints(ConstructorId _constraints) {
        constraints = _constraints;
    }

    public Constructor<?> getMethod() {
        return constructor;
    }

    public void setMethod(Constructor<?> _method) {
        constructor = _method;
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
    public String getReturnType() {
        if (constructor != null) {
            return PrimitiveTypeUtil.getAliasArrayClass(constructor.getDeclaringClass());
        }
        return constraints.getName();
    }

    @Override
    public boolean isStatic() {
        return false;
    }

    @Override
    public boolean isVararg() {
        if (constructor == null) {
            return constraints.isVararg();
        }
        return constructor.isVarArgs();
    }

    @Override
    public Identifiable getId() {
        return getConstraints();
    }

}
