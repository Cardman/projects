package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.Identifiable;
import code.expressionlanguage.functionid.IdentifiableUtil;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class ConstructorInfo implements Parametrable {

    private ConstructorId constraints;
    private ConstructorId formatted;
    private AnaTypeFct pair = new AnaTypeFct();

    private String className;

    private ParametersGroup parameters;

    private boolean varArgWrap;
    private InvocationMethod invocation;
    private final MemberId memberId = new MemberId();

    private StandardType standardType;
    private String fileName = "";
    private final CustList<CustList<ImplicitInfos>> implicits = new CustList<CustList<ImplicitInfos>>();
    private StringList parametersNames = new StringList();
    private NamedFunctionBlock customCtor;
    private final Ints nameParametersFilterIndexes = new Ints();
    private final CustList<OperationNode> allOps = new CustList<OperationNode>();
    private String stCall = "";

    public ConstructorId getConstraints() {
        return constraints;
    }

    public void setConstraints(ConstructorId _constraints) {
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

    public void format(AnalyzedPageEl _page) {
        if (!stCall.isEmpty()) {
            StringList params_ = new StringList();
            for (String p: constraints.getParametersTypes()) {
                if (p.contains("#")) {
                    params_.add("");
                } else {
                    params_.add(p);
                }
            }
            formatted = ConstructorId.to(className, params_, constraints);
            return;
        }
        StringList params_ = new StringList();
        for (String p: constraints.getParametersTypes()) {
            params_.add(AnaTemplates.wildCardFormatParam(className,p, _page));
        }
        formatted = ConstructorId.to(className, params_, constraints);
    }

    public void reformat(String _foundType,AnalyzedPageEl _page) {
        AnaGeneType type_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(_foundType));
        className = AnaTemplates.getOverridingFullTypeByBases(type_,_foundType,className,_page);
        StringList params_ = new StringList();
        for (String p: constraints.getParametersTypes()) {
            params_.add(AnaTemplates.wildCardFormatParam(className,p, _page));
        }
        formatted = ConstructorId.to(className, params_, constraints);
    }

    public StringList getFormattedParams() {
        return formatted.getParametersTypes();
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

    @Override
    public CustList<CustList<ImplicitInfos>> getImplicits() {
        return implicits;
    }

    public MemberId getMemberId() {
        return memberId;
    }
    public void memberId(int _rootNumber,int _memberNumber) {
        memberId.setRootNumber(_rootNumber);
        memberId.setMemberNumber(_memberNumber);
    }

    public StandardType getStandardType() {
        return standardType;
    }

    public void setStandardType(StandardType _standardType) {
        standardType = _standardType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        fileName = _fileName;
    }

    public StringList getParametersNames() {
        return parametersNames;
    }

    public void setParametersNames(StringList _parametersNames) {
        this.parametersNames = _parametersNames;
    }

    public NamedFunctionBlock getCustomCtor() {
        return customCtor;
    }

    public void setCustomCtor(NamedFunctionBlock _customCtor) {
        this.customCtor = _customCtor;
    }

    public Ints getNameParametersFilterIndexes() {
        return nameParametersFilterIndexes;
    }

    public CustList<OperationNode> getAllOps() {
        return allOps;
    }

    public void setStCall(String _stCall) {
        this.stCall = _stCall;
    }
}
