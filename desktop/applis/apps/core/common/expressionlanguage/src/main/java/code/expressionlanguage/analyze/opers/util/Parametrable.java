package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.Identifiable;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.StringList;

public abstract class Parametrable {
    private final ParametrableContent parametrableContent = new ParametrableContent();

    private final ParametersGroup parameters = new ParametersGroup();

    private String className = "";
    private String originalReturnType = "";
    private final CustList<CustList<ClassMethodIdReturn>> implicits = new CustList<CustList<ClassMethodIdReturn>>();
    private StringList parametersNames = new StringList();
    private final CustList<NamedArgIndex> nameParametersFilterIndexes = new CustList<NamedArgIndex>();
    private final CustList<OperationNode> allOps = new CustList<OperationNode>();
    private NamedFunctionBlock cust;
    private InvocationMethod invocation;
    private StringList formattedParams = new StringList();
    private final FormattedFilter formattedFilter = new FormattedFilter();
    private StandardType standardType;
    protected Parametrable() {
        parametrableContent.setPair(new AnaTypeFct());
    }

    public AnaFormattedRootBlock buildFormatted() {
        return new AnaFormattedRootBlock(getParametrableContent().getPair().getType(), getClassName());
    }
    public void memberId(int _rootNumber, int _memberNumber) {
        getParametrableContent().getMemberId().setRootNumber(_rootNumber);
        getParametrableContent().getMemberId().setMemberNumber(_memberNumber);
    }

    public FormattedFilter getFormattedFilter() {
        return formattedFilter;
    }

    public CustList<CustList<ClassMethodIdReturn>> getImplicits() {
        return implicits;
    }
    public StringList getFormattedParams() {
        return formattedParams;
    }

    public void setFormattedParams(StringList _f) {
        this.formattedParams = _f;
    }

    public InvocationMethod getInvocation() {
        return invocation;
    }

    public void setInvocation(InvocationMethod _invocation) {
        invocation = _invocation;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String _cl) {
        this.className = _cl;
    }

    public StringList getParametersNames() {
        return parametersNames;
    }

    public void setParametersNames(StringList _parametersNames) {
        this.parametersNames = _parametersNames;
    }

    public CustList<NamedArgIndex> getNameParametersFilterIndexes() {
        return nameParametersFilterIndexes;
    }

    public CustList<OperationNode> getAllOps() {
        return allOps;
    }

    public ParametersGroup getParameters() {
        return parameters;
    }

    public String getOriginalReturnType() {
        return originalReturnType;
    }

    public void setOriginalReturnType(String _originalReturnType) {
        this.originalReturnType = _originalReturnType;
    }

    public ParametrableContent getParametrableContent() {
        return parametrableContent;
    }

    public NamedFunctionBlock getCust() {
        return cust;
    }

    public void setCust(NamedFunctionBlock _c) {
        this.cust = _c;
    }

    public StandardType getStandardType() {
        return standardType;
    }

    public void setStandardType(StandardType _standardType) {
        standardType = _standardType;
    }

    public abstract MethodId toId();
    public abstract String getReturnType();


    public abstract boolean isVararg();


    public abstract Identifiable getGeneFormatted();
    public abstract Identifiable getPartialId();

}
