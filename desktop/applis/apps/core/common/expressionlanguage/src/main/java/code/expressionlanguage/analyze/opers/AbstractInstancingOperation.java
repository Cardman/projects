package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InterfacesPart;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.util.ConstructorInfo;
import code.expressionlanguage.analyze.opers.util.MethodInfo;
import code.expressionlanguage.analyze.opers.util.NameParametersFilter;
import code.expressionlanguage.analyze.opers.util.ParentInferring;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingImportTypes;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;

public abstract class AbstractInstancingOperation extends InvokingOperation {
    private String methodName;

    private ConstructorId constId;

    private String className;

    private int naturalVararg = -1;

    private String lastType = EMPTY_STRING;
    private String typeInfer = EMPTY_STRING;
    private CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private boolean newBefore = true;

    private StringList staticInitInterfaces = new StringList();
    private Ints staticInitInterfacesOffset = new Ints();

    public AbstractInstancingOperation(int _index, int _indexChild, MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        methodName = getOperations().getFctName();
    }

    void tryAnalyze(AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String afterNew_ = methodName.trim().substring(newKeyWord_.length());
        int j_ = afterNew_.indexOf("}");
        int delta_ = 0;
        int offDelta_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+offDelta_, _page);
        if (j_ > -1) {
            afterNew_ = afterNew_.substring(j_+1);
            delta_ = j_+1;
            newBefore = false;
        }
        int local_ = StringList.getFirstPrintableCharIndex(afterNew_)+delta_;
        String className_ = afterNew_.trim();
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
                boolean ok_ = true;
                for (String p: StringExpUtil.getAllTypes(o).mid(1)) {
                    if (p.startsWith(Templates.SUB_TYPE)) {
                        ok_ = false;
                    }
                    if (p.startsWith(Templates.SUP_TYPE)) {
                        ok_ = false;
                    }
                }
                if (!ok_) {
                    return;
                }
            }
            for (String o: arg_.getNames()) {
                StringList owners_ = AnaTypeUtil.getGenericOwners(o, idClass_, _page);
                if (owners_.onlyOneElt()) {
                    ownersMap_.put(o, owners_.first());
                }
            }
            if (ownersMap_.size() != 1) {
                return;
            }
            sup_ = ownersMap_.values().first();
            RootBlock root_ = _page.getAnaClassBody(StringExpUtil.getIdFromAllTypes(sup_));
            vars_ = AnaTemplates.getVarTypes(root_,sup_);
        } else {
            setStaticAccess(_page.getStaticContext());
        }
        String type_;
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
        AnalyzedBlock cur_ = _page.getCurrentAnaBlock();
        if (m_ == null && cur_ instanceof ReturnMethod) {
            typeAff_ = tryGetRetType(_page);
        } else if (m_ == null && cur_ instanceof ImportForEachLoop) {
            ImportForEachLoop i_ = (ImportForEachLoop) cur_;
            typeAff_ = i_.getImportedClassName();
            if (!typeAff_.isEmpty()) {
                String iter_ = _page.getStandards().getAliasIterable();
                typeAff_ = StringList.concat(iter_,Templates.TEMPLATE_BEGIN,typeAff_,Templates.TEMPLATE_END);
            }
        } else if (m_ == null && cur_ instanceof ImportForEachTable) {
            ImportForEachTable i_ = (ImportForEachTable) cur_;
            String typeAffOne_ = i_.getImportedClassNameFirst();
            String typeAffTwo_ = i_.getImportedClassNameSecond();
            if (!typeAffOne_.isEmpty() && !typeAffTwo_.isEmpty()) {
                String iter_ = _page.getStandards().getAliasIterableTable();
                typeAff_ = StringList.concat(iter_,Templates.TEMPLATE_BEGIN,typeAffOne_,Templates.TEMPLATE_SEP,typeAffTwo_,Templates.TEMPLATE_END);
            }
        } else {
            typeAff_ = tryGetTypeAff(m_);
        }
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (className_.trim().isEmpty()) {
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
                String res_ = ResolvingImportTypes.resolveCorrectTypeWithoutErrors(newKeyWord_.length()+local_,className_,true,partOffsets_, _page);
                if (!res_.isEmpty()) {
                    partOffsets.addAllElts(partOffsets_);
                    typeInfer = res_;
                }
            } else {
                int offset_ = newKeyWord_.length()+local_+StringList.getFirstPrintableCharIndex(className_);
                int begin_ = offset_;
                className_ = className_.trim();
                String idClass_ = StringExpUtil.getIdFromAllTypes(className_);
                ContextUtil.appendParts(begin_,begin_ + idClass_.length(),StringList.concat(sup_, "..", idClass_),partOffsets_, _page);
                offset_ += idClass_.length() + 1;
                StringList partsArgs_ = new StringList();
                for (String a: StringExpUtil.getAllTypes(className_).mid(1)) {
                    int loc_ = StringList.getFirstPrintableCharIndex(a);
                    partsArgs_.add(ResolvingImportTypes.resolveCorrectTypeWithoutErrors(offset_+loc_,a,true,partOffsets_, _page));
                    offset_ += a.length() + 1;
                }
                StringMap<StringList> currVars_ = _page.getCurrentConstraints().getCurrentConstraints();
                String res_ = AnaTemplates.tryGetAllInners(StringList.concat(sup_, "..", idClass_), partsArgs_, currVars_, _page);
                if (!res_.isEmpty()) {
                    partOffsets.addAllElts(partOffsets_);
                    typeInfer = res_;
                }
            }
            return;
        }
        CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
        if (!isIntermediateDottedOperation()) {
            int off_ = StringList.getFirstPrintableCharIndex(methodName);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
            type_ = ResolvingImportTypes.resolveAccessibleIdTypeWithoutError(newKeyWord_.length()+local_,inferForm_, _page);
            partOffsets_.addAllElts(_page.getCurrentParts());
            if (type_.isEmpty()) {
                return;
            }
        } else {
            int off_ = StringList.getFirstPrintableCharIndex(methodName);
            setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_, _page);
            String idClass_ = StringExpUtil.getIdFromAllTypes(className_).trim();
            String id_ = StringExpUtil.getIdFromAllTypes(sup_);
            type_ = StringList.concat(id_,"..",idClass_);
            int begin_ = newKeyWord_.length()+local_;
            ContextUtil.appendParts(begin_,begin_+inferForm_.length(),type_,partOffsets_, _page);
        }
        if (m_ instanceof NamedArgumentOperation){
            NamedArgumentOperation n_ = (NamedArgumentOperation) m_;
            String name_ = n_.getName();
            MethodOperation call_ = n_.getParent();
            if (call_ instanceof RetrieveMethod) {
                RetrieveMethod f_ = (RetrieveMethod) call_;
                NameParametersFilter filter_ = buildQuickFilter(call_);
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
                    int begin_ = newKeyWord_.length()+local_+className_.indexOf('<');
                    int end_ = newKeyWord_.length()+local_+className_.indexOf('>')+1;
                    ContextUtil.appendTitleParts(begin_,end_,infer_,partOffsets, _page);
                    typeInfer = infer_;
                }
            }
            if (call_ instanceof RetrieveConstructor) {
                RetrieveConstructor f_ = (RetrieveConstructor) call_;
                NameParametersFilter filter_ = buildQuickFilter(call_);
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
                    int begin_ = newKeyWord_.length()+local_+className_.indexOf('<');
                    int end_ = newKeyWord_.length()+local_+className_.indexOf('>')+1;
                    ContextUtil.appendTitleParts(begin_,end_,infer_,partOffsets, _page);
                    typeInfer = infer_;
                }
            }
            return;
        }
        if (m_ instanceof RetrieveMethod){
            RetrieveMethod f_ = (RetrieveMethod) m_;
            OperationNode firstChild_ = f_.getFirstChild();
            int deltaCount_ = getDeltaCount(firstChild_);
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
                int begin_ = newKeyWord_.length()+local_+className_.indexOf('<');
                int end_ = newKeyWord_.length()+local_+className_.indexOf('>')+1;
                ContextUtil.appendTitleParts(begin_,end_,infer_,partOffsets, _page);
                typeInfer = infer_;
            }
            return;
        }
        if (m_ instanceof RetrieveConstructor){
            RetrieveConstructor f_ = (RetrieveConstructor) m_;
            OperationNode firstChild_ = f_.getFirstChild();
            int deltaCount_ = getDeltaCount(firstChild_);
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
                int begin_ = newKeyWord_.length()+local_+className_.indexOf('<');
                int end_ = newKeyWord_.length()+local_+className_.indexOf('>')+1;
                ContextUtil.appendTitleParts(begin_,end_,infer_,partOffsets, _page);
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
        String infer_ = AnaTemplates.tryInfer(type_,vars_, cp_, _page);
        if (infer_ == null) {
            return;
        }
        partOffsets.addAllElts(partOffsets_);
        int begin_ = newKeyWord_.length()+local_+className_.indexOf('<');
        int end_ = newKeyWord_.length()+local_+className_.indexOf('>')+1;
        ContextUtil.appendTitleParts(begin_,end_,infer_,partOffsets, _page);
        typeInfer = infer_;
    }

    static void checkInstancingType(String _realClassName, MethodAccessKind _staticAccess, StringList _errs, AnalyzedPageEl _page) {
        String base_ = StringExpUtil.getIdFromAllTypes(_realClassName);
        AnaGeneType g_ = _page.getAnaGeneType(base_);
        if (g_ != null && !g_.withoutInstance()) {
            String glClass_ = _page.getGlobalClass();
            StringList parts_ = StringExpUtil.getAllPartInnerTypes(_realClassName);
            String outer_ = StringList.join(parts_.mid(0, parts_.size() - 2),"");
            if (_staticAccess != MethodAccessKind.INSTANCE) {
                FoundErrorInterpret static_ = new FoundErrorInterpret();
                static_.setFileName(_page.getLocalizer().getCurrentFileName());
                static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //original type len
                static_.buildError(_page.getAnalysisMessages().getIllegalCtorUnknown(),
                        _realClassName);
                _page.getLocalizer().addError(static_);
                _errs.add(static_.getBuiltError());
            } else {
                StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
                Mapping m_ = new Mapping();
                m_.setArg(glClass_);
                m_.setParam(outer_);
                m_.setMapping(vars_);
                if (!AnaTemplates.isCorrectOrNumbers(m_, _page)){
                    FoundErrorInterpret static_ = new FoundErrorInterpret();
                    static_.setFileName(_page.getLocalizer().getCurrentFileName());
                    static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //original type len
                    static_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            glClass_,
                            outer_);
                    _page.getLocalizer().addError(static_);
                    _errs.add(static_.getBuiltError());
                }
            }
        }
    }
    public String getTypeInfer() {
        return typeInfer;
    }

    public String getMethodName() {
        return methodName;
    }

    public ConstructorId getConstId() {
        return constId;
    }

    public void setConstId(ConstructorId _constId) {
        constId = _constId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public void setNaturalVararg(int naturalVararg) {
        this.naturalVararg = naturalVararg;
    }

    public String getLastType() {
        return lastType;
    }

    public void setLastType(String lastType) {
        this.lastType = lastType;
    }

    public boolean isNewBefore() {
        return newBefore;
    }

    public void setNewBefore(boolean newBefore) {
        this.newBefore = newBefore;
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
}
