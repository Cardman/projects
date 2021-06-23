package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InterfacesPart;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.ParsedAnnotations;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.opers.AnaInstancingCommonContent;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public abstract class AbstractInstancingOperation extends InvokingOperation {

    private final AnaInstancingCommonContent instancingCommonContent;
    private AnaTypeFct constructor;
    private MemberId memberId = new MemberId();
    private String typeInfer = EMPTY_STRING;
    private final CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private boolean newBefore = true;

    private StringList staticInitInterfaces = new StringList();
    private Ints staticInitInterfacesOffset = new Ints();

    protected AbstractInstancingOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        instancingCommonContent = new AnaInstancingCommonContent(getOperations().getFctName());
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

        if (className_.startsWith("@")) {
            ParsedAnnotations parse_ = new ParsedAnnotations(className_,local_);
            parse_.parse();
            local_ = parse_.getIndex();
            className_ = parse_.getAfter();
            local_ += StringExpUtil.getOffset(className_);
            className_ = className_.trim();
        }

        InterfacesPart ints_ = new InterfacesPart(className_,local_);
        ints_.parse(_page.getKeyWords(),0,newKeyWord_.length()+local_+ _page.getLocalizer().getCurrentLocationIndex());
        staticInitInterfaces = ints_.getStaticInitInterfaces();
        staticInitInterfacesOffset = ints_.getStaticInitInterfacesOffset();
        local_ = ints_.getLocIndex();
        className_ = ints_.getPart();
        AnaClassArgumentMatching arg_ = getPreviousResultClass();
        StringMap<String> ownersMap_ = new StringMap<String>();
        StringMap<String> vars_ = new StringMap<String>();
        String sup_ = "";
        if (isIntermediateDottedOperation()) {
            if (arg_.isArray()) {
                return;
            }
            String idClass_ = StringExpUtil.getIdFromAllTypes(className_).trim();
            for (String o: arg_.getNames()) {
                if (StringExpUtil.isWildCard(o)) {
                    return;
                }
            }
            for (String o: arg_.getNames()) {
                StringList owners_ = AnaTypeUtil.getGenericOwners(o, idClass_, _page);
                if (owners_.onlyOneElt()) {
                    ownersMap_.put(o, owners_.first());
                }
            }
            if (!className_.trim().isEmpty()) {
                if (ownersMap_.size() != 1) {
                    return;
                }
                sup_ = ownersMap_.values().first();
                RootBlock root_ = _page.getAnaClassBody(StringExpUtil.getIdFromAllTypes(sup_));
                vars_ = AnaInherits.getVarTypes(root_,sup_);
            }
        } else {
            setStaticAccess(_page.getStaticContext());
        }
        OperationNode current_;
        if (!isIntermediateDottedOperation()) {
            current_ = this;
        } else {
            current_ = getParent();
        }
        ParentInferring par_ = ParentInferring.getParentInferring(current_);
        OperationNode m_ = par_.getOperation();
        int nbParentsInfer_ = par_.getNbParentsInfer();
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
            typeAff_ = tryGetTypeAff(m_, par_.getOperationChild().getIndexChild());
        }
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (className_.trim().isEmpty()) {
            boolean list_ = false;
            if (m_ instanceof ArgumentListInstancing){
                list_ = true;
                m_ = m_.getParent().getParent();
            }
            if (m_ instanceof NamedArgumentOperation){
                NamedArgumentOperation n_ = (NamedArgumentOperation) m_;
                String name_ = n_.getName();
                MethodOperation call_ = n_.getParent();
                if (call_ instanceof RetrieveMethod) {
                    RetrieveMethod f_ = (RetrieveMethod) call_;
                    NameParametersFilter filter_ = buildQuickFilter(_page,call_);
                    CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
                    int len_ = methodInfos_.size();
                    StringList candidates_ = new StringList();
                    for (int i = 0; i < len_; i++) {
                        int gr_ = methodInfos_.get(i).size();
                        CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
                        for (int j = 0; j < gr_; j++) {
                            MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                            String format_ = tryParamFormatEmpInst(this,filter_,methodInfo_, name_, nbParentsInfer_, _page);
                            if (format_ == null) {
                                continue;
                            }
                            candidates_.add(format_);
                            newList_.add(methodInfo_);
                        }
                        methodInfos_.set(i,newList_);
                    }
                    if (candidates_.onlyOneElt()) {
                        typeInfer = candidates_.first();
                    }
                }
                if (call_ instanceof RetrieveConstructor) {
                    RetrieveConstructor f_ = (RetrieveConstructor) call_;
                    NameParametersFilter filter_ = buildQuickFilter(_page,call_);
                    CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
                    int len_ = methodInfos_.size();
                    StringList candidates_ = new StringList();
                    CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
                    for (int i = 0; i < len_; i++) {
                        ConstructorInfo methodInfo_ = methodInfos_.get(i);
                        String format_ = tryParamFormatEmpInst(this,filter_,methodInfo_, name_, nbParentsInfer_, _page);
                        if (format_ == null) {
                            continue;
                        }
                        candidates_.add(format_);
                        newList_.add(methodInfo_);
                    }
                    methodInfos_.clear();
                    methodInfos_.addAllElts(newList_);
                    if (candidates_.onlyOneElt()) {
                        typeInfer = candidates_.first();
                    }
                }
                return;
            }
            if (m_ instanceof RetrieveMethod){
                RetrieveMethod f_ = (RetrieveMethod) m_;
                OperationNode firstChild_ = f_.getFirstChild();
                int deltaCount_ = getDeltaCount(list_,firstChild_);
                int indexChild_ = par_.getOperationChild().getIndexChild()-deltaCount_;
                CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
                int len_ = methodInfos_.size();
                StringList candidates_ = new StringList();
                for (int i = 0; i < len_; i++) {
                    int gr_ = methodInfos_.get(i).size();
                    CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
                    for (int j = 0; j < gr_; j++) {
                        MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                        String format_ = tryFormatEmpInst(this,methodInfo_, indexChild_, nbParentsInfer_, _page);
                        if (format_ == null) {
                            continue;
                        }
                        candidates_.add(format_);
                        newList_.add(methodInfo_);
                    }
                    methodInfos_.set(i,newList_);
                }
                if (candidates_.onlyOneElt()) {
                    typeInfer = candidates_.first();
                }
                return;
            }
            if (m_ instanceof RetrieveConstructor){
                RetrieveConstructor f_ = (RetrieveConstructor) m_;
                OperationNode firstChild_ = f_.getFirstChild();
                int deltaCount_ = getDeltaCount(list_,firstChild_);
                int indexChild_ = par_.getOperationChild().getIndexChild()-deltaCount_;
                CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
                int len_ = methodInfos_.size();
                StringList candidates_ = new StringList();
                CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
                for (int i = 0; i < len_; i++) {
                    ConstructorInfo methodInfo_ = methodInfos_.get(i);
                    String format_ = tryFormatEmpInst(this,methodInfo_, indexChild_, nbParentsInfer_, _page);
                    if (format_ == null) {
                        continue;
                    }
                    candidates_.add(format_);
                    newList_.add(methodInfo_);
                }
                methodInfos_.clear();
                methodInfos_.addAllElts(newList_);
                if (candidates_.onlyOneElt()) {
                    typeInfer = candidates_.first();
                }
                return;
            }
            if (isUndefined(typeAff_, keyWordVar_)) {
                return;
            }
            String cp_ = StringExpUtil.getQuickComponentType(typeAff_, nbParentsInfer_);
            if (isNotCorrectDim(cp_)) {
                return;
            }
            typeInfer = cp_;
            return;
        }
        String inferForm_ = AnaTemplates.getInferForm(className_);
        if (inferForm_ == null) {
            CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
            if (!isIntermediateDottedOperation()) {
                String res_ = ResolvingTypes.resolveCorrectTypeWithoutErrors(newKeyWord_.length()+local_,className_,true,partOffsets_, _page);
                if (!res_.isEmpty()) {
                    partOffsets.addAllElts(partOffsets_);
                    typeInfer = res_;
                }
            } else {
                int offset_ = newKeyWord_.length()+local_+ StringUtil.getFirstPrintableCharIndex(className_);
                int begin_ = offset_;
                className_ = className_.trim();
                String idClass_ = StringExpUtil.getIdFromAllTypes(className_);
                ContextUtil.appendParts(begin_,begin_ + idClass_.length(), StringUtil.concat(sup_, "..", idClass_),partOffsets_, _page);
                offset_ += idClass_.length() + 1;
                StringList partsArgs_ = new StringList();
                for (String a: StringExpUtil.getAllTypes(className_).mid(1)) {
                    int loc_ = StringUtil.getFirstPrintableCharIndex(a);
                    partsArgs_.add(ResolvingTypes.resolveCorrectTypeWithoutErrors(offset_+loc_,a.trim(),true,partOffsets_, _page));
                    offset_ += a.length() + 1;
                }
                StringMap<StringList> currVars_ = _page.getCurrentConstraints().getCurrentConstraints();
                String res_ = AnaInherits.tryGetAllInners(StringUtil.concat(sup_, "..", idClass_), partsArgs_, currVars_, _page);
                if (!res_.isEmpty()) {
                    partOffsets.addAllElts(partOffsets_);
                    typeInfer = res_;
                }
            }
            return;
        }
        CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
        String type_;
        if (!isIntermediateDottedOperation()) {
            type_ = ResolvingTypes.resolveAccessibleIdTypeWithoutError(newKeyWord_.length()+local_,inferForm_, _page);
            partOffsets_.addAllElts(_page.getCurrentParts());
            if (type_.isEmpty()) {
                return;
            }
        } else {
            String idClass_ = StringExpUtil.getIdFromAllTypes(className_).trim();
            String id_ = StringExpUtil.getIdFromAllTypes(sup_);
            type_ = StringUtil.concat(id_,"..",idClass_);
            int begin_ = newKeyWord_.length()+local_;
            ContextUtil.appendParts(begin_,begin_+inferForm_.length(),type_,partOffsets_, _page);
        }
        int lt_ = newKeyWord_.length() + local_ + className_.indexOf('<');
        int gt_ = newKeyWord_.length() + local_ + className_.indexOf('>') + 1;
        boolean list_ = false;
        if (m_ instanceof ArgumentListInstancing){
            list_ = true;
            m_ = m_.getParent().getParent();
        }
        if (m_ instanceof NamedArgumentOperation){
            NamedArgumentOperation n_ = (NamedArgumentOperation) m_;
            String name_ = n_.getName();
            MethodOperation call_ = n_.getParent();
            if (call_ instanceof RetrieveMethod) {
                RetrieveMethod f_ = (RetrieveMethod) call_;
                NameParametersFilter filter_ = buildQuickFilter(_page,call_);
                CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
                int len_ = methodInfos_.size();
                StringList candidates_ = new StringList();
                for (int i = 0; i < len_; i++) {
                    int gr_ = methodInfos_.get(i).size();
                    CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
                    for (int j = 0; j < gr_; j++) {
                        MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                        String format_ = tryParamFormat(filter_,methodInfo_, name_, nbParentsInfer_, type_, vars_, _page);
                        if (format_ == null) {
                            continue;
                        }
                        candidates_.add(format_);
                        newList_.add(methodInfo_);
                    }
                    methodInfos_.set(i,newList_);
                }
                if (candidates_.onlyOneElt()) {
                    String infer_ = candidates_.first();
                    partOffsets.addAllElts(partOffsets_);
                    ContextUtil.appendTitleParts(lt_, gt_,infer_,partOffsets, _page);
                    typeInfer = infer_;
                }
            }
            if (call_ instanceof RetrieveConstructor) {
                RetrieveConstructor f_ = (RetrieveConstructor) call_;
                NameParametersFilter filter_ = buildQuickFilter(_page,call_);
                CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
                int len_ = methodInfos_.size();
                StringList candidates_ = new StringList();
                CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
                for (int i = 0; i < len_; i++) {
                    ConstructorInfo methodInfo_ = methodInfos_.get(i);
                    String format_ = tryParamFormat(filter_,methodInfo_, name_, nbParentsInfer_, type_, vars_, _page);
                    if (format_ == null) {
                        continue;
                    }
                    candidates_.add(format_);
                    newList_.add(methodInfo_);
                }
                methodInfos_.clear();
                methodInfos_.addAllElts(newList_);
                if (candidates_.onlyOneElt()) {
                    String infer_ = candidates_.first();
                    partOffsets.addAllElts(partOffsets_);
                    ContextUtil.appendTitleParts(lt_, gt_,infer_,partOffsets, _page);
                    typeInfer = infer_;
                }
            }
            return;
        }
        if (m_ instanceof RetrieveMethod){
            RetrieveMethod f_ = (RetrieveMethod) m_;
            OperationNode firstChild_ = f_.getFirstChild();
            int deltaCount_ = getDeltaCount(list_,firstChild_);
            int indexChild_ = par_.getOperationChild().getIndexChild()-deltaCount_;
            CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
            int len_ = methodInfos_.size();
            StringList candidates_ = new StringList();
            for (int i = 0; i < len_; i++) {
                int gr_ = methodInfos_.get(i).size();
                CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
                for (int j = 0; j < gr_; j++) {
                    MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                    String format_ = tryFormat(methodInfo_, indexChild_, nbParentsInfer_, type_, vars_, _page);
                    if (format_ == null) {
                        continue;
                    }
                    candidates_.add(format_);
                    newList_.add(methodInfo_);
                }
                methodInfos_.set(i,newList_);
            }
            if (candidates_.onlyOneElt()) {
                String infer_ = candidates_.first();
                partOffsets.addAllElts(partOffsets_);
                ContextUtil.appendTitleParts(lt_, gt_,infer_,partOffsets, _page);
                typeInfer = infer_;
            }
            return;
        }
        if (m_ instanceof RetrieveConstructor){
            RetrieveConstructor f_ = (RetrieveConstructor) m_;
            OperationNode firstChild_ = f_.getFirstChild();
            int deltaCount_ = getDeltaCount(list_,firstChild_);
            int indexChild_ = par_.getOperationChild().getIndexChild()-deltaCount_;
            CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
            int len_ = methodInfos_.size();
            StringList candidates_ = new StringList();
            CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
            for (int i = 0; i < len_; i++) {
                ConstructorInfo methodInfo_ = methodInfos_.get(i);
                String format_ = tryFormat(methodInfo_, indexChild_, nbParentsInfer_, type_, vars_, _page);
                if (format_ == null) {
                    continue;
                }
                candidates_.add(format_);
                newList_.add(methodInfo_);
            }
            methodInfos_.clear();
            methodInfos_.addAllElts(newList_);
            if (candidates_.onlyOneElt()) {
                String infer_ = candidates_.first();
                partOffsets.addAllElts(partOffsets_);
                ContextUtil.appendTitleParts(lt_, gt_,infer_,partOffsets, _page);
                typeInfer = infer_;
            }
            return;
        }
        if (isUndefined(typeAff_, keyWordVar_)) {
            return;
        }
        String cp_ = StringExpUtil.getQuickComponentType(typeAff_, nbParentsInfer_);
        if (isNotCorrectDim(cp_)) {
            return;
        }
        String infer_ = tryInferOrImplicit(type_,vars_, _page, cp_);
        if (infer_ == null) {
            return;
        }
        partOffsets.addAllElts(partOffsets_);
        ContextUtil.appendTitleParts(lt_, gt_,infer_,partOffsets, _page);
        typeInfer = infer_;
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
        String base_ = StringExpUtil.getIdFromAllTypes(cp_);
        AnaGeneType g_ = _page.getAnaGeneType(base_);
        if (g_ == null) {
            return null;
        }
        if (StringExpUtil.isWildCard(cp_)) {
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
        if (_current.koType(g_,cp_,_page)) {
            return null;
        }
        return cp_;
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
                static_.setFileName(_page.getLocalizer().getCurrentFileName());
                static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
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
                    static_.setFileName(_page.getLocalizer().getCurrentFileName());
                    static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
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

    protected void result(AnalyzedPageEl _page,String _realClassName, AnaGeneType _g, ConstrustorIdVarArg _ctorRes, NameParametersFilter _name) {
        setConstId(_ctorRes.getRealId());
        setConstructor(_ctorRes.getPair());
        if (_g instanceof RootBlock) {
            setFormattedType(new AnaFormattedRootBlock((RootBlock) _g, _realClassName));
        } else {
            setClassName(_realClassName);
        }
        setMemberId(_ctorRes.getMemberId());
        if (_ctorRes.isVarArgToCall()) {
            int nbParams_ = getConstId().getParametersTypesLength();
            setNaturalVararg(nbParams_ - 1);
            setLastType(getConstId().getParametersType(nbParams_ - 1));
        }
        unwrapArgsFct(getConstId(), getNaturalVararg(), getLastType(), _name.getAll(), _page);
    }


    public String getTypeInfer() {
        return typeInfer;
    }

    public String getMethodName() {
        return instancingCommonContent.getMethodName();
    }

    public ConstructorId getConstId() {
        return instancingCommonContent.getConstId();
    }

    public void setConstId(ConstructorId _constId) {
        instancingCommonContent.setConstId(_constId);
    }

    public AnaTypeFct getConstructor() {
        return constructor;
    }

    public void setConstructor(AnaTypeFct _constructor) {
        this.constructor = _constructor;
    }

    public String getClassName() {
        return instancingCommonContent.getClassName();
    }

    public void setClassName(String _className) {
        this.instancingCommonContent.setClassName(_className);
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

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
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
}
