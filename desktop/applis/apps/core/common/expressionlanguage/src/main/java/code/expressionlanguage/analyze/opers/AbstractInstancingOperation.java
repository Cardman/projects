package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InterfacesPart;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.opers.AnaInstancingCommonContent;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.StandardConstructor;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class AbstractInstancingOperation extends InvokingOperation {

    private final AnaInstancingCommonContent instancingCommonContent;
    private final int deltaAnnot;
    private AnaTypeFct constructor;
    private MemberId memberId = new MemberId();
    private String typeInfer = EMPTY_STRING;
    private ResolvedInstance resolvedInstance = new ResolvedInstance();
//    private final CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private boolean newBefore = true;

    private StringList staticInitInterfaces = new StringList();
    private Ints staticInitInterfacesOffset = new Ints();

    protected AbstractInstancingOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        instancingCommonContent = new AnaInstancingCommonContent(_op.getFctName());
        deltaAnnot = _op.getDeltaAnnot();
    }

    protected String skip(String _input) {
        if (deltaAnnot > _input.length()) {
            return "";
        }
        return _input.substring(deltaAnnot);
    }
    void tryAnalyze(AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String afterNew_ = instancingCommonContent.getMethodName().trim().substring(newKeyWord_.length());
        int j_ = -1;
        int delta_ = 0;
        int offDelta_ = StringUtil.getFirstPrintableCharIndex(instancingCommonContent.getMethodName());
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offDelta_, _page);
        if (afterNew_.trim().startsWith("{")) {
            j_ =  afterNew_.indexOf('}',afterNew_.indexOf('{'));
        }
        if (j_ > -1) {
            afterNew_ = afterNew_.substring(j_+1);
            delta_ = j_+1;
            newBefore = false;
        }
        int local_ = StringUtil.getFirstPrintableCharIndex(afterNew_)+delta_;
        String className_ = afterNew_.trim();
        className_ = skip(className_);
        local_ += deltaAnnot;
        InterfacesPart ints_ = new InterfacesPart(className_,local_);
        ints_.parse(_page.getKeyWords(),"",0,newKeyWord_.length()+local_+ _page.getIndex());
        staticInitInterfaces = ints_.getStaticInitInterfaces();
        staticInitInterfacesOffset = ints_.getStaticInitInterfacesOffset();
        local_ = ints_.getLocIndex();
        className_ = ints_.getPart();
        AnaClassArgumentMatching arg_ = getPreviousResultClass();
        StringMap<String> vars_ = new StringMap<String>();
        String sup_ = "";
        String innType_ = "";
        RootBlock innTypeInf_ = null;
        if (isIntermediateDottedOperation()) {
            if (koIntermediate(arg_)) {
                return;
            }
        } else {
            setStaticAccess(_page.getStaticContext());
        }
        ParentInferring par_ = ParentInferring.getParentInferring(current());
        if (className_.trim().isEmpty()) {
            StdNewInferringEmptyType std_ = new StdNewInferringEmptyType(this);
            std_.infer(_page,par_);
            typeInfer = std_.getTypeInfer();
            return;
        }
        if (isIntermediateDottedOperation()) {
            StringMap<OwnerResultInfo> ownersMap_ = ownersMap(_page, arg_, className_);
            if (ownersMap_.size() != 1) {
                return;
            }
            OwnerResultInfo inf_ = ownersMap_.values().first();
            sup_ = inf_.getOwnerName();
            innTypeInf_ = inf_.getOwned();
            innType_ = inf_.getOwnedName();
            RootBlock root_ = inf_.getOwner();
            vars_ = AnaInherits.getVarTypes(root_,sup_);
        }
        String inferForm_ = AnaTemplates.getInferForm(className_);
        if (inferForm_ == null) {
            defResolver(_page, local_, className_, innType_, innTypeInf_);
            return;
        }
        StdNewInferringDiamondType std_ = new StdNewInferringDiamondType(this,inferForm_,local_,className_,sup_,innTypeInf_);
        std_.infer(_page,par_,vars_);
        resolvedInstance = std_.getResolvedInstance();
        typeInfer = std_.getTypeInfer();
    }

    private StringMap<OwnerResultInfo> ownersMap(AnalyzedPageEl _page, AnaClassArgumentMatching _arg, String _cl) {
        String idClass_ = StringExpUtil.getIdFromAllTypes(_cl).trim();
        StringMap<OwnerResultInfo> ownersMap_ = new StringMap<OwnerResultInfo>();
        for (String o: _arg.getNames()) {
            OwnerListResultInfo owners_ = AnaTypeUtil.getGenericOwners(o, idClass_, _page);
            if (owners_.onlyOneElt()) {
                ownersMap_.put(o, owners_.firstElt());
            }
        }
        return ownersMap_;
    }

    private boolean koIntermediate(AnaClassArgumentMatching _arg) {
        if (_arg.isArray()) {
            return true;
        }
        for (String o: _arg.getNames()) {
            if (StringExpUtil.isWildCard(o)) {
                return true;
            }
        }
        return false;
    }

    private void defResolver(AnalyzedPageEl _page, int _local, String _className, String _innType, RootBlock _innTypeInf) {
        int rc_ = _page.getIndex();
        KeyWords keyWords_ = _page.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String className_ = _className;
        if (!isIntermediateDottedOperation()) {
            AnaResultPartType resType_ = ResolvingTypes.resolveCorrectTypeWithoutErrorsExact(newKeyWord_.length() + _local, className_, _page);
            if (resType_.isOk()) {
                resolvedInstance = new ResolvedInstance(resType_);
                typeInfer = resType_.getResult();
            }
        } else {
            int offset_ = newKeyWord_.length()+ _local + StringUtil.getFirstPrintableCharIndex(className_);
            int begin_ = offset_;
            className_ = className_.trim();
            String idClass_ = StringExpUtil.getIdFromAllTypes(className_);
            offset_ += idClass_.length() + 1;
            CustList<AnaResultPartType> results_ = new CustList<AnaResultPartType>();
            StringList partsArgs_ = new StringList();
            for (String a: StringExpUtil.getAllTypes(className_).mid(1)) {
                int loc_ = StringUtil.getFirstPrintableCharIndex(a);
                AnaResultPartType resType_ = ResolvingTypes.resolveCorrectTypeWithoutErrorsExact(offset_ + loc_, a.trim(), _page);
                if (!resType_.isOk()) {
                    return;
                }
                results_.add(resType_);
                partsArgs_.add(resType_.getResult());
                offset_ += a.length() + 1;
            }
            StringMap<StringList> currVars_ = _page.getCurrentConstraints().getCurrentConstraints();
            String res_ = AnaInherits.tryGetAllInners(_innTypeInf, _innType, partsArgs_, currVars_, _page);
            if (!res_.isEmpty()) {
                FileBlock r_ = _page.getCurrentFile();
                resolvedInstance = new ResolvedInstance(PreLinkagePartTypeUtil.processAccessOkRootAnalyze(idClass_, _innTypeInf,StringExpUtil.getIdFromAllTypes(_innType),r_, rc_ +begin_, _page), results_);
                typeInfer = res_;
            }
        }
    }

    private OperationNode current() {
        OperationNode current_;
        if (!isIntermediateDottedOperation()) {
            current_ = this;
        } else {
            current_ = getParent();
        }
        return current_;
    }

    static String typeAff(AnalyzedPageEl _page, ParentInferring _par) {
        OperationNode m_ = _par.getOperation();
        String typeAff_ = EMPTY_STRING;
        if (m_ == null && _page.getCurrentBlock() instanceof ReturnMethod) {
            typeAff_ = tryGetRetType(_page);
        } else if (m_ == null && _page.getCurrentAnaBlockForEachLoop() != null) {
            ImportForEachLoop i_ = _page.getCurrentAnaBlockForEachLoop();
            typeAff_ = i_.getImportedClassName();
            if (!typeAff_.isEmpty()) {
                String iter_ = _page.getAliasIterable();
                typeAff_ = StringUtil.concat(iter_,StringExpUtil.TEMPLATE_BEGIN,typeAff_,StringExpUtil.TEMPLATE_END);
            }
        } else if (m_ == null && _page.getCurrentAnaBlockForEachTable() != null) {
            ImportForEachTable i_ = _page.getCurrentAnaBlockForEachTable();
            String typeAffOne_ = i_.getImportedClassNameFirst();
            String typeAffTwo_ = i_.getImportedClassNameSecond();
            if (!typeAffOne_.isEmpty() && !typeAffTwo_.isEmpty()) {
                String iter_ = _page.getAliasIterableTable();
                typeAff_ = StringUtil.concat(iter_,StringExpUtil.TEMPLATE_BEGIN,typeAffOne_,StringExpUtil.TEMPLATE_SEP,typeAffTwo_,StringExpUtil.TEMPLATE_END);
            }
        } else {
            typeAff_ = tryGetTypeAff(m_, _par.getOperationChild().getIndexChild());
        }
        return typeAff_;
    }

    protected static String tryParamFormatEmpInst(AbstractInstancingOperation _current,NameParametersFilter _filter, Parametrable _param, String _name, int _nbParentsInfer, AnalyzedPageEl _page) {
        if (!isValidNameIndex(_filter,_param,_name)) {
            return null;
        }
        int ind_ = StringUtil.indexOf(_param.getParametersNames(), _name);
        return tryFormatEmpInst(_current,_param, ind_, _nbParentsInfer, _page);
    }
    protected static String tryFormatEmpInst(AbstractInstancingOperation _current,Parametrable _param, int _indexChild, int _nbParentsInfer, AnalyzedPageEl _page) {
        String cp_ = tryGetParamDim(_param,_indexChild,_nbParentsInfer);
        if (cp_ == null) {
            return null;
        }
        return checkEmpInstComm(_current,cp_,_page);
    }
    protected static String tryFormatEmpInstRec(AbstractInstancingOperation _current, String _fieldType, int _nbParentsInfer, AnalyzedPageEl _page) {
        String cp_ = tryGetRecordDim(_fieldType,_nbParentsInfer);
        if (cp_ == null) {
            return null;
        }
        return checkEmpInstComm(_current,cp_,_page);
    }
    protected static String checkEmpInstComm(AbstractInstancingOperation _current, String _type, AnalyzedPageEl _page) {
        String base_ = StringExpUtil.getIdFromAllTypes(_type);
        AnaGeneType g_ = _page.getAnaGeneType(base_);
        if (g_ == null) {
            return null;
        }
        if (StringExpUtil.isWildCard(_type)) {
            return null;
        }
        String enumClassName_ = _page.getAliasEnumType();
        String enumParamClassName_ = _page.getAliasEnumParam();
        if (StringUtil.quickEq(enumParamClassName_, base_)) {
            return null;
        }
        if (StringUtil.quickEq(enumClassName_, base_)) {
            return null;
        }
        if (_current.koType(g_,_type,_page)) {
            return null;
        }
        return _type;
    }
    protected abstract boolean koType(AnaGeneType _type,String _realClassName,  AnalyzedPageEl _page);

    void checkInstancingType(String _realClassName, MethodAccessKind _staticAccess, AnalyzedPageEl _page) {
        String base_ = StringExpUtil.getIdFromAllTypes(_realClassName);
        AnaGeneType g_ = _page.getAnaGeneType(base_);
        if (g_ != null && !g_.withoutInstance()) {
            String glClass_ = _page.getGlobalClass();
            StringList parts_ = StringExpUtil.getAllPartInnerTypes(_realClassName);
            String outer_ = StringUtil.join(parts_.left(parts_.size() - 2),"");
            if (_staticAccess != MethodAccessKind.INSTANCE) {
                FoundErrorInterpret static_ = new FoundErrorInterpret();
                static_.setFile(_page.getCurrentFile());
                static_.setIndexFile(_page);
                //original type len
                static_.buildError(_page.getAnalysisMessages().getIllegalCtorUnknown(),
                        _realClassName);
                _page.getLocalizer().addError(static_);
                addErr(static_.getBuiltError());
            } else {
                StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                Mapping m_ = new Mapping();
                m_.setArg(glClass_);
                m_.setParam(outer_);
                m_.setMapping(vars_);
                if (!AnaInherits.isCorrectOrNumbers(m_, _page)){
                    FoundErrorInterpret static_ = new FoundErrorInterpret();
                    static_.setFile(_page.getCurrentFile());
                    static_.setIndexFile(_page);
                    //original type len
                    static_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            glClass_,
                            outer_);
                    _page.getLocalizer().addError(static_);
                    addErr(static_.getBuiltError());
                }
            }
        }
    }
    static boolean koInstancingType(String _realClassName, MethodAccessKind _staticAccess, AnalyzedPageEl _page, AnaGeneType _type, String _argOwner) {
        if (!_type.withoutInstance()) {
            StringList parts_ = StringExpUtil.getAllPartInnerTypes(_realClassName);
            String outer_ = StringUtil.join(parts_.left(parts_.size() - 2),"");
            if (_staticAccess != MethodAccessKind.INSTANCE) {
                return true;
            }
            StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
            Mapping m_ = new Mapping();
            m_.setArg(_argOwner);
            m_.setParam(outer_);
            m_.setMapping(vars_);
            return !AnaInherits.isCorrectOrNumbers(m_, _page);
        }
        return false;
    }

    protected void result(String _realClassName, AnaGeneType _g, ConstrustorIdVarArg _ctorRes) {
        setConstId(_ctorRes.getRealId());
        setConstructor(_ctorRes.getConstructor());
        setConstructor(_ctorRes.getPair());
        if (_g instanceof RootBlock) {
            setFormattedType(new AnaFormattedRootBlock((RootBlock) _g, _realClassName));
        }
        setMemberId(_ctorRes.getMemberId());
        AnaInstancingCommonContent instancingCommonContent_ = getInstancingCommonContent();
        if (_ctorRes.isVarArgToCall()) {
            int nbParams_ = instancingCommonContent_.getConstId().getParametersTypesLength();
            setNaturalVararg(nbParams_ - 1);
            setLastType(instancingCommonContent_.getConstId().getParametersType(nbParams_ - 1));
        }
    }


    public String getTypeInfer() {
        return typeInfer;
    }

    public String getMethodName() {
        return instancingCommonContent.getMethodName();
    }

    public void setConstId(ConstructorId _constId) {
        instancingCommonContent.setConstId(_constId);
    }

    public void setConstructor(StandardConstructor _constId) {
        instancingCommonContent.setConstructor(_constId);
    }

    public AnaTypeFct getConstructor() {
        return constructor;
    }

    public void setConstructor(AnaTypeFct _constructor) {
        this.constructor = _constructor;
    }

    public AnaFormattedRootBlock getFormattedType() {
        return instancingCommonContent.getFormattedType();
    }

    public void setFormattedType(AnaFormattedRootBlock _formattedType) {
        instancingCommonContent.setFormattedType(_formattedType);
    }

    public int getNaturalVararg() {
        return instancingCommonContent.getNaturalVararg();
    }

    public void setNaturalVararg(int _naturalVararg) {
        this.instancingCommonContent.setNaturalVararg(_naturalVararg);
    }

    public String getLastType() {
        return instancingCommonContent.getLastType();
    }

    public void setLastType(String _lastType) {
        this.instancingCommonContent.setLastType(_lastType);
    }

    public AnaInstancingCommonContent getInstancingCommonContent() {
        return instancingCommonContent;
    }

    public boolean isNewBefore() {
        return newBefore;
    }

    public void setNewBefore(boolean _newBefore) {
        this.newBefore = _newBefore;
    }

    public ResolvedInstance getResolvedInstance() {
        return resolvedInstance;
    }

    public void setResolvedInstance(ResolvedInstance _resolvedInstance) {
        resolvedInstance = _resolvedInstance;
    }

    public StringList getStaticInitInterfaces() {
        return staticInitInterfaces;
    }

    public Ints getStaticInitInterfacesOffset() {
        return staticInitInterfacesOffset;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberId _memberId) {
        this.memberId = _memberId;
    }

    public int getDeltaAnnot() {
        return deltaAnnot;
    }
}
