package code.expressionlanguage.opers.util;
import code.util.ints.Displayable;

public final class ConstructorInfo implements Parametrable, Displayable {

    private ConstructorId constraints;

    private String className;

    private ParametersGroup parameters;

    @Override
    public String display() {
        return constraints.getSignature();
    }

    public ConstructorId getConstraints() {
        return constraints;
    }

    public void setConstraints(ConstructorId _constraints) {
        constraints = _constraints;
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
        return constraints.getName();
    }

    @Override
    public boolean isStatic() {
        return false;
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
        return 0;
    }
    @Override
    public int getAncestor() {
        return 0;
    }

}
