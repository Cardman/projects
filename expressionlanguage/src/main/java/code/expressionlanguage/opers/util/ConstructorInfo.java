package code.expressionlanguage.opers.util;
import code.util.StringList;
import code.util.ints.Displayable;

public final class ConstructorInfo implements Parametrable {

    private ConstructorId constraints;
    private ConstructorId formatted;

    private String className;

    private ParametersGroup parameters;

    private boolean varArgWrap;
    private InvocationMethod invocation;

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
    public int getImported() {
        return 0;
    }
    @Override
    public int getAncestor() {
        return 0;
    }

    @Override
    public boolean isVarArgWrap() {
        return varArgWrap;
    }

    @Override
    public void setVarArgWrap(boolean _v) {
        varArgWrap = _v;
    }

    @Override
    public void format(StringList _params) {
        formatted = new ConstructorId(className, _params, isVararg());
    }

    @Override
    public ConstructorId getFormatted() {
        return formatted;
    }

    @Override
    public InvocationMethod getInvocation() {
        return invocation;
    }

    @Override
    public void setInvocation(InvocationMethod _invocation) {
        invocation = _invocation;
    }

}
