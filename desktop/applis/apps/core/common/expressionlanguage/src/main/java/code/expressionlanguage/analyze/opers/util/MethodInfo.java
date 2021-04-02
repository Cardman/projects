package code.expressionlanguage.analyze.opers.util;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ImportedMethod;
import code.expressionlanguage.analyze.MethodHeaderInfo;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
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

    private String className = "";

    private String returnType = "";
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
    private final CustList<CustList<ImplicitInfos>> implicits = new CustList<CustList<ImplicitInfos>>();
    private StringList parametersNames = new StringList();
    private final Ints nameParametersFilterIndexes = new Ints();
    private final CustList<OperationNode> allOps = new CustList<OperationNode>();
    private final FormattedFilter formattedFilter = new FormattedFilter();

    public MethodId getConstraints() {
        return constraints;
    }

    public void setConstraints(MethodId _constraints) {
        constraints = _constraints;
    }

    public AnaTypeFct getPair() {
        return pair;
    }
    public void pairMemberId(ImportedMethod _m) {
        pair(_m.getType(),_m.getCustMethod());
        memberId = _m.getMemberId();
        setFileName(_m.getFileName());
        setReturnType(_m.getReturnType());
        setOriginalReturnType(_m.getReturnType());
        trySetParamNames(this, _m.getCustMethod());
        setStandardMethod(_m.getStandardMethod());
        setCustMethod(_m.getCustMethod());
    }
    public void pairMemberId(MethodHeaderInfo _m) {
        setFileName(_m.getRoot().getFile().getFileName());
        pair(_m.getRoot(),_m.getFunction());
        memberId(_m.getRootNumber(),_m.getNameNumber());
    }

    private static void trySetParamNames(MethodInfo _mloc, NamedFunctionBlock _custMethod) {
        if (_custMethod != null) {
            _mloc.setParametersNames(_custMethod.getParametersNames());
        }
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

    public void reformat(String _foundType,AnalyzedPageEl _page) {
        AnaGeneType type_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(_foundType));
        className = AnaInherits.getOverridingFullTypeByBases(type_,_foundType,className,_page);
        StringList params_ = new StringList();
        for (String p: constraints.getParametersTypes()) {
            params_.add(AnaInherits.wildCardFormatParam(className,p, _page));
        }
        formattedParams = params_;
        formatted = buildFormatted(MethodId.getKind(false), params_, constraints);
        returnType = AnaInherits.wildCardFormatReturn(className,originalReturnType,_page);
    }

    public void format(boolean _keepParams, AnalyzedPageEl _page) {
        if (!formattedFilter.getStCall().isEmpty()) {
            StringList params_ = new StringList();
            for (String p: constraints.getParametersTypes()) {
                if (p.contains("#")) {
                    params_.add("");
                } else {
                    params_.add(p);
                }
            }
            formattedParams = params_;
            formatted = buildFormatted(MethodId.getKind(_keepParams), params_, constraints);
            return;
        }
        StringList params_ = new StringList();
        for (String p: constraints.getParametersTypes()) {
            params_.add(AnaInherits.wildCardFormatParam(className,p, _page));
        }
        formattedParams = params_;
        formatted = buildFormatted(MethodId.getKind(_keepParams), params_, constraints);
    }

    public void format(MethodId _id) {
        StringList params_ = new StringList();
        for (String p: _id.getParametersTypes()) {
            params_.add(p);
        }
        formattedParams = params_;
        formatted = buildFormatted(_id.getKind(), params_, _id);
    }
    private static MethodId buildFormatted(MethodAccessKind _keepParams, StringList _params, MethodId _id) {
        return MethodId.to(_keepParams, _params, _id);
    }

    public void formatWithoutParams() {
        formattedParams = new StringList();
        formatted = new MethodId(MethodId.getKind(false), constraints.getName(), formattedParams);
    }

    @Override
    public Identifiable getGeneFormatted() {
        return getFormatted();
    }

    public MethodId getFormatted() {
        return formatted;
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

    public void setFormattedFilter(FormattedFilter _formattedFilter) {
        formattedFilter.setStCall(_formattedFilter.getStCall());
        formattedFilter.setReturnType(_formattedFilter.getReturnType());
    }

}
