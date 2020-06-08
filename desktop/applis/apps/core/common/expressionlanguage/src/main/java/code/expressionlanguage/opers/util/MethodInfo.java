package code.expressionlanguage.opers.util;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.Templates;
import code.util.CustList;
import code.util.StringList;

public final class MethodInfo implements Parametrable {

    private MethodId constraints;
    private MethodId formatted;

    private ParametersGroup parameters;

    private String className;

    private String returnType;

    private MethodAccessKind staticMethod;

    private boolean finalMethod;

    private int ancestor;

    private boolean varArgWrap;
    private boolean abstractMethod;
    private InvocationMethod invocation;
    private CustList<CustList<ClassMethodId>> implicits = new CustList<CustList<ClassMethodId>>();

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
    public boolean sameParamsVararg(Parametrable _id) {
        return IdentifiableUtil.eqPartial(constraints,_id.getPartialId());
    }

    @Override
    public Identifiable getPartialId() {
        return constraints;
    }

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

    public void format(boolean _keepParams,ContextEl _an) {
        StringList params_ = new StringList();
        for (String p: constraints.getParametersTypes()) {
            params_.add(Templates.wildCardFormatParam(className,p,_an));
        }
        formatted = new MethodId(MethodId.getKind(_keepParams), constraints.getName(), params_, isVararg());
    }

    public void formatWithoutParams() {
        formatted = new MethodId(MethodId.getKind(false), constraints.getName(), new StringList());
    }

    @Override
    public Identifiable getGeneFormatted() {
        return getFoundFormatted();
    }

    public MethodId getFoundFormatted() {
        StringList params_ = new StringList();
        for (String p: formatted.getParametersTypes()) {
            params_.add(p);
        }
        return new MethodId(constraints.getKind(),formatted.getName(),formatted.getParametersTypes(),formatted.isVararg());
    }

    public boolean same(MethodId _id) {
        return formatted.eq(_id);
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

    @Override
    public CustList<CustList<ClassMethodId>> getImplicits() {
        return implicits;
    }
}
