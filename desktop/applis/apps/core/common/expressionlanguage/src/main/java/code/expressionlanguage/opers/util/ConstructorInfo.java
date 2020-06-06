package code.expressionlanguage.opers.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.Templates;
import code.util.StringList;

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
    public boolean isVararg() {
        return constraints.isVararg();
    }

    @Override
    public boolean sameParamsVararg(Parametrable _id) {
        return IdentifiableUtil.eqPartial(constraints,_id.getPartialId());
    }

    @Override
    public Identifiable getPartialId() {
        return constraints;
    }

    @Override
    public boolean isVarArgWrap() {
        return varArgWrap;
    }

    @Override
    public void setVarArgWrap(boolean _v) {
        varArgWrap = _v;
    }

    public void format(ContextEl _an) {
        StringList params_ = new StringList();
        for (String p: constraints.getParametersTypes()) {
            params_.add(Templates.wildCardFormatParam(className,p,_an));
        }
        formatted = new ConstructorId(className, params_, isVararg());
    }

    @Override
    public Identifiable getGeneFormatted() {
        return getFormatted();
    }

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
