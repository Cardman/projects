package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.opers.util.ScopeFilter;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.AnaArrContent;
import code.expressionlanguage.fwd.opers.AnaCallFctContent;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.stds.StandardMethod;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class AbsFctOperation extends InvokingOperation implements PreAnalyzableOperation,RetrieveMethod,AbstractCallFctOperation,SettableElResult {

    private final AnaCallFctContent callFctContent;
    private final AnaArrContent arrContent;
    private boolean staticMethod;

    private boolean staticChoiceMethod;

    private int anc;

    private int lengthMethod;
    private int delta;
    private boolean clonedMethod;
    private boolean trueFalse;
    private String typeInfer = EMPTY_STRING;
    private String methodFound = EMPTY_STRING;
    private CustList<CustList<MethodInfo>> methodInfos = new CustList<CustList<MethodInfo>>();

    private AnaResultPartType partOffsets = new AnaResultPartType();
    private StandardMethod standardMethod;
    private boolean errLeftValue;
    private boolean importType;

    protected AbsFctOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        callFctContent = new AnaCallFctContent(_op.getFctName());
        arrContent = new AnaArrContent();
    }
    protected void init(AnalyzedPageEl _page) {
        importType = !isIntermediateDottedOperation();
        if (importType) {
            setStaticAccess(_page.getStaticContext());
        }
    }

    protected void preAna(AnalyzedPageEl _page, boolean _superClass) {
        init(_page);
        int off_ = StringUtil.getFirstPrintableCharIndex(getCallFctContent().getMethodName());
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
        String trimMeth_ = trimMethod();
        if (isTrueFalseKeyWord(trimMeth_, _page)) {
            return;
        }
        setMethodFound(trimMeth_);
        setMethodInfos(getDeclaredCustMethodByType(isStaticAccess(), getBounds(clName(_page), _page), trimMeth_, isImportType(), _page, new ScopeFilter(null, true, _superClass, isLvalue(), true, _page.getGlobalClass()), getFormattedFilter(_page, this)));
        filterByNameReturnType(_page, trimMeth_, getMethodInfos());
    }

    protected AnaClassArgumentMatching getPreviousResultClass(AnalyzedPageEl _page) {
        AnaClassArgumentMatching clCur_;
        if (isIntermediateDottedOperation()) {
            clCur_ = getPreviousResultClass();
        } else {
            clCur_ = new AnaClassArgumentMatching(_page.getGlobalClass());
        }
        return clCur_;
    }

    protected String clName(AnalyzedPageEl _page) {
        int off_ = StringUtil.getFirstPrintableCharIndex(getCallFctContent().getMethodName());
        String className_ = getCallFctContent().getMethodName().substring(0, getCallFctContent().getMethodName().lastIndexOf(PAR_RIGHT));
        int lenPref_ = getCallFctContent().getMethodName().indexOf(PAR_LEFT) + 1;
        className_ = className_.substring(lenPref_);
        int loc_ = StringUtil.getFirstPrintableCharIndex(className_)- off_;
        AnaResultPartType resType_ = ResolvingTypes.resolveCorrectTypeWithoutErrorsExact(lenPref_ + loc_, className_.trim(), _page);
        className_ = resType_.getResult();
        if (resType_.isOk()) {
            setPartOffsets(resType_);
            setTypeInfer(className_);
        }
        return className_;
    }

    protected String clNameAna(AnalyzedPageEl _page) {
        String methodName_ = getCallFctContent().getMethodName();
        int off_ = StringUtil.getFirstPrintableCharIndex(methodName_);
        String className_ = methodName_.substring(0, methodName_.lastIndexOf(PAR_RIGHT));
        int lenPref_ = methodName_.indexOf(PAR_LEFT) + 1;
        className_ = className_.substring(lenPref_);
        int loc_ = StringUtil.getFirstPrintableCharIndex(className_)- off_;
        if (getTypeInfer().isEmpty()) {
            setPartOffsets(ResolvingTypes.resolveCorrectType(lenPref_ + loc_, className_, _page));
            className_ = getPartOffsets().getResult(_page);
        } else {
            className_ = getTypeInfer();
        }
        return className_;
    }

    protected void checkOwner(AnalyzedPageEl _page, AnaClassArgumentMatching _clCur, String _clName) {
        Mapping map_ = new Mapping();
        map_.setParam(_clName);
        map_.setArg(_clCur);
        StringMap<StringList> mapping_ = _page.getCurrentConstraints().getCurrentConstraints();
        map_.setMapping(mapping_);
        if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setIndexFile(_page);
            cast_.setFile(_page.getCurrentFile());
            //type len
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(_clCur.getNames(), ExportCst.JOIN_TYPES),
                    _clName);
            _page.getLocalizer().addError(cast_);
            addErr(cast_.getBuiltError());
        }
    }

    protected String trimMethod() {
        int delta_ = getCallFctContent().getMethodName().lastIndexOf(PAR_RIGHT)+1;
        String mName_ = getCallFctContent().getMethodName().substring(delta_);
        return mName_.trim();
    }

    protected String delta(String _methName) {
        int d_ = _methName.lastIndexOf(PAR_RIGHT)+1;
        String mName_ = _methName.substring(d_);
        d_ += StringUtil.getFirstPrintableCharIndex(mName_);
        setDelta(d_);
        String tr_ = mName_.trim();
        setLengthMethod(tr_.length());
        return tr_;
    }

    protected void lookup(AnalyzedPageEl _page, String _methodName, StringList _bounds, boolean _superClass) {
        String trimMeth_ = delta(_methodName);
        ClassMethodIdAncestor feed_ = id(trimMeth_);
        NameParametersFilter name_ = buildFilter(_page);
        if (!name_.getParameterFilterErr().isEmpty()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (isTrueFalseKeyWord(trimMeth_, _page)) {
            trueFalse(_page,trimMeth_,true,feed_, _bounds,name_);
            return;
        }
        std(_page,trimMeth_, _bounds,name_, new ScopeFilter(feed_, true, _superClass, isLvalue(), true, _page.getGlobalClass()));
    }

    protected void trueFalse(AnalyzedPageEl _page, String _trMethod, boolean _staticChoiceMethod, ClassMethodIdAncestor _feedBase, StringList _bounds, NameParametersFilter _name) {
        errLeftValue = true;
        ClassMethodIdAncestor f_ = getTrueFalse(idBase(_feedBase), _page);
        ClassMethodIdReturn clMeth_;
        MethodAccessKind staticAccess_ = isStaticAccess();
        AnaClassArgumentMatching[] argsClass_ = OperationNode.getResultsFromArgs(_name.getAllOps());
        clMeth_ = tryGetDeclaredCustTrueFalse(staticAccess_, _bounds, _trMethod, f_, argsClass_, _page);
        if (clMeth_ == null) {
            buildErrNotFoundTrueFalse(staticAccess_, _trMethod, _page,argsClass_);
            setResultClass(voidToObject(new AnaClassArgumentMatching(_page.getAliasObject()), _page));
            return;
        }
        callFctContent.update(clMeth_);
        trueFalse = true;
        staticChoiceMethod = _staticChoiceMethod;
        staticMethod = true;
        setResultClass(voidToObject(new AnaClassArgumentMatching(clMeth_.getReturnType(), _page.getPrimitiveTypes()), _page));
    }

    protected void std(AnalyzedPageEl _page, String _trMethod, StringList _bounds, NameParametersFilter _name, ScopeFilter _sc) {
        int varargOnly_ = lookOnlyForVarArg();
        String varargParam_ = getVarargParam(getChildrenNodes());
        ClassMethodIdReturn clMeth_;
        clMeth_ = tryGet(varargOnly_, _trMethod, varargParam_, _name, _page, getDeclaredCustMethodByType(isStaticAccess(), _bounds, _trMethod, importType, _name, _page, _sc));
        if (clMeth_ == null) {
            ClassMethodIdReturn next_ = tryGet(varargOnly_, _trMethod, varargParam_, _name, _page, getDeclaredCustMethodByType(isStaticAccess(), _bounds, _trMethod, importType, _name, _page, new ScopeFilter(_sc.getId(), _sc.isBaseClass(), _sc.isSuperClass(), _sc.isRetRef(), _sc.getGlClass())));
            if (next_ != null) {
                callFctContent.update(next_);
                int off_ = StringUtil.getFirstPrintableCharIndex(callFctContent.getMethodName());
                setRelativeOffsetPossibleAnalyzable(getIndexInEl()+ off_, _page);
                FoundErrorInterpret abs_ = new FoundErrorInterpret();
                abs_.setIndexFile(_page);
                abs_.setFile(_page.getCurrentFile());
                //method name len
                abs_.buildError(
                        _page.getAnalysisMessages().getAbstractMethodRef(),
                        next_.getRealClass(),
                        next_.getRealId().getSignature(_page.getDisplayedStrings()));
                _page.getLocalizer().addError(abs_);
                addErr(abs_.getBuiltError());
                setResultClass(voidToObject(new AnaClassArgumentMatching(_page.getAliasObject()), _page));
                return;
            }
            buildErrNotFoundStd(isStaticAccess(), _trMethod, _name, _page);
            setResultClass(voidToObject(new AnaClassArgumentMatching(_page.getAliasObject()), _page));
            return;
        }
        anc = clMeth_.getAncestor();
        if (StringUtil.quickEq(_trMethod, _page.getKeyWords().getKeyWordNull())) {
            errLeftValue = true;
        }
        callFctContent.update(clMeth_);
        standardMethod = clMeth_.getStandardMethod();
        MethodId id_ = clMeth_.getRealId();
        staticChoiceMethod = _sc.isExcAbs();
        staticMethod = id_.getKind() != MethodAccessKind.INSTANCE;
        setResultClass(voidToObject(new AnaClassArgumentMatching(clMeth_.getReturnType(), _page.getPrimitiveTypes()), _page));
    }

    protected ClassMethodIdAncestor id(String _trMethod) {
        ClassMethodIdAncestor idMethod_ = lookOnlyForId();
        ClassMethodIdAncestor feed_ = null;
        if (idMethod_ != null) {
            ClassMethodId id_ = idMethod_.getClassMethodId();
            AnaGeneType gene_ = idMethod_.getGt();
            MethodId mid_ = id_.getConstraints();
            MethodAccessKind static_ = MethodId.getKind(isStaticAccess(), mid_.getKind());
            String idClass_ = id_.getClassName();
            ClassMethodId classMethodId_ = new ClassMethodId(idClass_, MethodId.to(static_, _trMethod, mid_));
            feed_ = new ClassMethodIdAncestor(gene_, classMethodId_,idMethod_.getAncestor());
        }
        return feed_;
    }
    protected ClassMethodIdAncestor idBase(ClassMethodIdAncestor _id) {
        if (_id == null) {
            return null;
        }
        return new ClassMethodIdAncestor(_id.getGt(), _id.getClassMethodId(),0);
    }
    public boolean isImportType() {
        return importType;
    }

    public ClassMethodId getClassMethodId() {
        return callFctContent.getClassMethodId();
    }

    public boolean isStaticMethod() {
        return staticMethod;
    }

    public boolean isStaticChoiceMethod() {
        return staticChoiceMethod;
    }

    public AnaCallFctContent getCallFctContent() {
        return callFctContent;
    }

    public int getAnc() {
        return anc;
    }

    public int getDelta() {
        return delta;
    }

    public int getLengthMethod() {
        return lengthMethod;
    }

    public AnaResultPartType getPartOffsets() {
        return partOffsets;
    }

    public void setPartOffsets(AnaResultPartType _parts) {
        this.partOffsets = _parts;
    }

    public boolean isClonedMethod() {
        return clonedMethod;
    }

    public boolean isTrueFalse() {
        return trueFalse;
    }

    public CustList<CustList<MethodInfo>> getMethodInfos() {
        return methodInfos;
    }

    public void setMethodInfos(CustList<CustList<MethodInfo>> _meth) {
        this.methodInfos = _meth;
    }

    public String getMethodFound() {
        return methodFound;
    }

    public void setMethodFound(String _meth) {
        this.methodFound = _meth;
    }

    public String getTypeInfer() {
        return typeInfer;
    }

    public void setTypeInfer(String _t) {
        this.typeInfer = _t;
    }

    public void setDelta(int _d) {
        this.delta = _d;
    }

    public void setLengthMethod(int _l) {
        this.lengthMethod = _l;
    }

    public void setErrLeftValue(boolean _er) {
        this.errLeftValue = _er;
    }

    public void setClonedMethod(boolean _cl) {
        this.clonedMethod = _cl;
    }

    @Override
    public StandardMethod getStandardMethod() {
        return standardMethod;
    }

    public AnaArrContent getArrContent() {
        return arrContent;
    }

    @Override
    public void setVariable(boolean _variable) {
        arrContent.setVariable(_variable);
    }

    public boolean isErrLeftValue() {
        return errLeftValue;
    }
}
