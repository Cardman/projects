package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.Identifiable;
import code.expressionlanguage.functionid.IdentifiableUtil;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;

public final class ConstructorInfo implements Parametrable {

    private ConstructorId constraints;
    private ConstructorId formatted;
    private StringList formattedParams;
    private final AnaTypeFct pair = new AnaTypeFct();

    private String className;

    private final ParametersGroup parameters = new ParametersGroup();

    private boolean varArgWrap;
    private InvocationMethod invocation;
    private final MemberId memberId = new MemberId();

    private StandardType standardType;
    private StandardConstructor constructor;
    private String fileName = "";
    private final CustList<CustList<ClassMethodIdReturn>> implicits = new CustList<CustList<ClassMethodIdReturn>>();
    private String originalReturnType = "";
    private StringList parametersNames = new StringList();
    private NamedFunctionBlock customCtor;
    private final Ints nameParametersFilterIndexes = new Ints();
    private final CustList<OperationNode> allOps = new CustList<OperationNode>();
    private String stCall = "";
    private boolean synthetic;

    public ConstructorId getConstraints() {
        return constraints;
    }

    public void constructorId(String _className,ConstructorId _constraints) {
        className = _className;
        constraints = _constraints;
    }

    public AnaTypeFct getPair() {
        return pair;
    }
    public void pair(RootBlock _root, NamedFunctionBlock _fct) {
        pair.setType(_root);
        pair.setFunction(_fct);
    }

    @Override
    public ParametersGroup getParameters() {
        return parameters;
    }

    @Override
    public String getClassName() {
        return className;
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
        return getConstraints();
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
            StringList params_ = IdentifiableUtil.incomplete(constraints);
            formatted = ConstructorId.to(className, params_, constraints);
            formattedParams = params_;
            return;
        }
        StringList params_ = AnaInherits.wildCardFormatParams(className, constraints, _page);
        formatted = ConstructorId.to(className, params_, constraints);
        formattedParams = params_;
    }

    public void reformat(String _foundType,AnalyzedPageEl _page) {
        className = AnaInherits.getOverridingFullTypeByBases(_foundType,className,_page);
        StringList params_ = AnaInherits.wildCardFormatParams(className, constraints, _page);
        formatted = ConstructorId.to(className, params_, constraints);
        formattedParams = params_;
    }

    public StringList getFormattedParams() {
        return formattedParams;
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
    public CustList<CustList<ClassMethodIdReturn>> getImplicits() {
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

    public StandardConstructor getConstructor() {
        return constructor;
    }

    public void setConstructor(StandardConstructor _constructor) {
        this.constructor = _constructor;
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

    public boolean isSynthetic() {
        return synthetic;
    }

    public void setSynthetic(boolean _s) {
        this.synthetic = _s;
    }

    public String getOriginalReturnType() {
        return originalReturnType;
    }

    public void setOriginalReturnType(String _or) {
        this.originalReturnType = _or;
    }
}
