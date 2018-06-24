package code.expressionlanguage.opers.util;
import code.util.ints.Displayable;

public final class MethodInfo implements Parametrable, Displayable {

    private static final String RIGHT_PAR = ")";

    private static final String LEFT_PAR = "(";

    private static final String DOT = ".";

    private MethodId constraints;

    private ParametersGroup parameters;

    private String className;

    private String returnType;

    private boolean staticMethod;

    private int imported;

    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(className);
        str_.append(DOT);
        str_.append(constraints.getSignature());
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
        return constraints.isVararg();
    }

    @Override
    public Identifiable getId() {
        return getConstraints();
    }

    @Override
    public int getImported() {
        return imported;
    }

    public void setImported(int _imported) {
        imported = _imported;
    }
}
