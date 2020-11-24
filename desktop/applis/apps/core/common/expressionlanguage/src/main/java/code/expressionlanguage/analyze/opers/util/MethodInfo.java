package code.expressionlanguage.analyze.opers.util;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.stds.StandardMethod;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class MethodInfo implements Parametrable {

    private MethodId constraints;
    private AnaTypeFct pair = new AnaTypeFct();
    private MethodId formatted;

    private ParametersGroup parameters;

    private String className;

    private String returnType;
    private String originalReturnType = "";
    private String fileName = "";
    private MemberId memberId = new MemberId();
    private StringList formattedParams;

    private boolean finalMethod;

    private int ancestor;

    private boolean varArgWrap;
    private boolean abstractMethod;
    private InvocationMethod invocation;
    private StandardMethod standardMethod;
    private NamedFunctionBlock custMethod;
    private CustList<CustList<ImplicitInfos>> implicits = new CustList<CustList<ImplicitInfos>>();
    private StringList parametersNames = new StringList();
    private Ints nameParametersFilterIndexes = new Ints();
    private final CustList<OperationNode> allOps = new CustList<OperationNode>();

    public MethodId getConstraints() {
        return constraints;
    }

    public void setConstraints(MethodId _constraints) {
        constraints = _constraints;
    }

    public AnaTypeFct getPair() {
        return pair;
    }
    public void pair(RootBlock _root, NamedFunctionBlock _fct) {
        pair = new AnaTypeFct();
        pair.setType(_root);
        pair.setFunction(_fct);
    }

    @Override
    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String _returnType) {
        returnType = _returnType;
    }

    public String getOriginalReturnType() {
        return originalReturnType;
    }

    public void setOriginalReturnType(String _originalReturnType) {
        this.originalReturnType = _originalReturnType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        this.fileName = _fileName;
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

    public void format(boolean _keepParams, AnalyzedPageEl _page) {
        StringList params_ = new StringList();
        for (String p: constraints.getParametersTypes()) {
            params_.add(AnaTemplates.wildCardFormatParam(className,p, _page));
        }
        formattedParams = params_;
        formatted = MethodId.to(MethodId.getKind(_keepParams), params_, constraints);
    }

    public void formatWithoutParams() {
        formattedParams = new StringList();
        formatted = new MethodId(MethodId.getKind(false), constraints.getName(), formattedParams);
    }

    @Override
    public Identifiable getGeneFormatted() {
        return getFoundFormatted();
    }

    public MethodId getFoundFormatted() {
        return MethodId.to(formattedParams,formatted);
    }

    public StringList getFormattedParams() {
        return formattedParams;
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
    public CustList<CustList<ImplicitInfos>> getImplicits() {
        return implicits;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberId _memberId) {
        memberId = _memberId;
    }

    public void memberId(int _rootNumber, int _memberNumber) {
        memberId.setRootNumber(_rootNumber);
        memberId.setMemberNumber(_memberNumber);
    }

    public StandardMethod getStandardMethod() {
        return standardMethod;
    }

    public void setStandardMethod(StandardMethod _standardMethod) {
        this.standardMethod = _standardMethod;
    }

    public NamedFunctionBlock getCustMethod() {
        return custMethod;
    }

    public void setCustMethod(NamedFunctionBlock _custMethod) {
        this.custMethod = _custMethod;
    }

    public StringList getParametersNames() {
        return parametersNames;
    }

    public void setParametersNames(StringList _parametersNames) {
        this.parametersNames = _parametersNames;
    }

    public Ints getNameParametersFilterIndexes() {
        return nameParametersFilterIndexes;
    }

    public CustList<OperationNode> getAllOps() {
        return allOps;
    }
}
