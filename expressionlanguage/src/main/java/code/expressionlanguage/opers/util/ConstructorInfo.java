package code.expressionlanguage.opers.util;
import java.lang.reflect.Constructor;

public final class ConstructorInfo implements Parametrable {

    private static final String RIGHT_PAR = ")";

    private static final String LEFT_PAR = "(";

    private ConstructorId constr;

    private FctConstraints constraints;

    private Constructor<?> constructor;

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

    public FctConstraints getConstraints() {
        return constraints;
    }

    public void setConstraints(FctConstraints _constraints) {
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
}
