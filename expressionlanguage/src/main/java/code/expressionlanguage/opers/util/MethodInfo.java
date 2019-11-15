package code.expressionlanguage.opers.util;
import code.util.StringList;

public final class MethodInfo implements Parametrable {

    private MethodId constraints;
    private MethodId formatted;

    private ParametersGroup parameters;

    private String className;

    private String returnType;

    private MethodAccessKind staticMethod;

    private boolean finalMethod;

    private int imported;
    private int ancestor;

    private boolean varArgWrap;
    private boolean abstractMethod;
    private InvocationMethod invocation;

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
        return staticMethod == MethodAccessKind.STATIC;
    }

    public void setStaticMethod(MethodAccessKind _staticMethod) {
        staticMethod = _staticMethod;
    }

    public void setStatic(boolean _staticMethod) {
        staticMethod = MethodId.getKind(_staticMethod);
    }

    @Override
    public boolean isVararg() {
        return constraints.isVararg();
    }

    @Override
    public int getImported() {
        return imported;
    }

    public void setImported(int _imported) {
        imported = _imported;
    }

    @Override
    public int getAncestor() {
        return ancestor;
    }

    public void setAncestor(int _ancestor) {
        ancestor = _ancestor;
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
        formatted = new MethodId(staticMethod, constraints.getName(), _params, isVararg());
    }

    @Override
    public Identifiable getGeneFormatted() {
        return getFormatted();
    }

    public MethodId getFormatted() {
        return formatted;
    }

    public boolean same(Identifiable _id) {
        MethodId id_ = (MethodId) _id;
        return formatted.eq(id_);
    }
    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public void setAbstractMethod(boolean _abstractMethod) {
        abstractMethod = _abstractMethod;
    }

    public boolean isFinalMethod() {
        return finalMethod;
    }

    public void setFinalMethod(boolean _finalMethod) {
        finalMethod = _finalMethod;
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
