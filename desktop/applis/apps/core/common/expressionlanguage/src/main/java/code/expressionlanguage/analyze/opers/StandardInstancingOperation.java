package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InterfacesPart;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.files.ParsedAnnotations;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.fwd.opers.AnaInstancingStdContent;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.fwd.opers.AnaNamedFieldContent;
import code.expressionlanguage.options.KeyWords;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class StandardInstancingOperation extends
        AbstractInstancingOperation implements PreAnalyzableOperation,RetrieveConstructor {

    private final AnaInstancingStdContent instancingStdContent;

    private final CustList<ConstructorInfo> ctors = new CustList<ConstructorInfo>();
    private InnerTypeOrElement innerElt;
    private StringList errorsFields = new StringList();

    public StandardInstancingOperation(int _index, int _indexChild,
            MethodOperation _m, OperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
        instancingStdContent = new AnaInstancingStdContent();
    }

    public void fieldName(Calculation _calc) {
        instancingStdContent.setFieldName(_calc.getFieldName());
        innerElt = _calc.getInnerElt();
        errorsFields = _calc.getFieldErrors();
    }

    @Override
    public void preAnalyze(AnalyzedPageEl _page) {
        tryAnalyze(_page);
        AnaGeneType anaGeneType_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(getTypeInfer()));
        if (anaGeneType_ == null) {
            return;
        }
        tryGetCtors(getTypeInfer(), ctors,_page, anaGeneType_);
    }



    @Override
    public void analyze(AnalyzedPageEl _page) {
        int off_ = StringUtil.getFirstPrintableCharIndex(getMethodName());
        KeyWords keyWords_ = _page.getKeyWords();
        String newKeyWord_ = keyWords_.getKeyWordNew();
        String realClassName_ = getMethodName().trim().substring(newKeyWord_.length());
        int j_ = -1;
        if (realClassName_.trim().startsWith("{")) {
            j_ =  realClassName_.indexOf('}',realClassName_.indexOf('{'));
        }
        if (j_ > -1) {
            realClassName_ = realClassName_.substring(j_+1);
            off_ += j_+1;
        }
        int local_ = StringUtil.getFirstPrintableCharIndex(realClassName_);
        if (realClassName_.trim().startsWith("@")) {
            ParsedAnnotations parse_ = new ParsedAnnotations(realClassName_.trim(),local_);
            parse_.parse();
            local_ = parse_.getIndex();
            realClassName_ = parse_.getAfter();
            local_ += StringExpUtil.getOffset(realClassName_);
        }
        realClassName_ = realClassName_.trim();
        InterfacesPart ints_ = new InterfacesPart(realClassName_,local_);
        ints_.parse(_page.getKeyWords(),"",0,newKeyWord_.length()+local_+ _page.getLocalizer().getCurrentLocationIndex());
        local_ = ints_.getLocIndex();
        realClassName_ = ints_.getPart();
        setRelativeOffsetPossibleAnalyzable(getIndexInEl()+off_+newKeyWord_.length()+local_, _page);
        CustList<OperationNode> filter_ =  getChildrenNodes();
        String varargParam_ = getVarargParam(filter_);
        if (!isIntermediateDottedOperation()) {
            setStaticAccess(_page.getStaticContext());
            if (!getTypeInfer().isEmpty()) {
                realClassName_ = getTypeInfer();
            } else if (innerElt == null) {
                AnaResultPartType result_ = ResolvingTypes.resolveCorrectType(0, realClassName_, _page);
                realClassName_ = result_.getResult(_page);
//                getPartOffsets().addAllElts(_page.getCurrentParts());
                setResolvedInstance(new ResolvedInstance(result_));
            } else {
                realClassName_ = realClassName_.trim();
            }
            checkInstancingType(realClassName_, isStaticAccess(), _page);
            analyzeCtor(realClassName_, varargParam_, _page);
            return;
        }
        if (!getTypeInfer().isEmpty()) {
            analyzeCtor(getTypeInfer(), varargParam_, _page);
            return;
        }
        realClassName_ = realClassName_.trim();
        AnaClassArgumentMatching arg_ = getPreviousResultClass();
        if (arg_.isArray()) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_page.getLocalizer().getCurrentFileName());
            static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            static_.buildError(_page.getAnalysisMessages().getIllegalCtorUnknown(),
                    realClassName_);
            _page.getLocalizer().addError(static_);
            addErr(static_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        StringMap<String> ownersMap_ = new StringMap<String>();
        String idClass_ = StringExpUtil.getIdFromAllTypes(realClassName_);
        int offset_ = idClass_.length() + 1;
        for (String o: arg_.getNames()) {
            boolean ok_ = true;
            for (String p: StringExpUtil.getWildCards(o)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(_page.getLocalizer().getCurrentFileName());
                call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //key word len
                call_.buildError(_page.getAnalysisMessages().getIllegalCtorBound(),
                        p,
                        o);
                _page.getLocalizer().addError(call_);
                addErr(call_.getBuiltError());
                ok_ = false;
            }
            if (!ok_) {
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
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
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_page.getLocalizer().getCurrentFileName());
            static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //idClass_ len
            static_.buildError(_page.getAnalysisMessages().getNotResolvedOwner(),
                    idClass_
            );
            _page.getLocalizer().addError(static_);
            addErr(static_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        String sup_ = ownersMap_.values().first();
        StringList partsArgs_ = new StringList();
        String inner_ = StringExpUtil.getIdFromAllTypes(StringUtil.concat(sup_, "..", idClass_));
        RootBlock root_ = _page.getAnaClassBody(inner_);
        CustList<AnaResultPartType> results_ = new CustList<AnaResultPartType>();
        for (String a: StringExpUtil.getAllTypes(realClassName_).mid(1)) {
            int loc_ = StringUtil.getFirstPrintableCharIndex(a);
            AnaResultPartType result_ = ResolvingTypes.resolveCorrectType(offset_ + loc_, a, _page);
            partsArgs_.add(result_.getResult(_page));
            results_.add(result_);
//            getPartOffsets().addAllElts(_page.getCurrentParts());
            offset_ += a.length() + 1;
        }
        StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
        AccessedBlock r_ = _page.getCurrentGlobalBlock().getCurrentGlobalBlock();
        setResolvedInstance(new ResolvedInstance(PreLinkagePartTypeUtil.processAccessOkRootAnalyze(idClass_,inner_,r_,_page.getLocalizer().getCurrentLocationIndex(),_page), results_));
        realClassName_ = check(root_,StringUtil.concat(sup_, "..", idClass_), partsArgs_, vars_, _page);
        analyzeCtor(realClassName_, varargParam_, _page);
    }

    @Override
    protected boolean koType(AnaGeneType _type,String _realClassName, AnalyzedPageEl _page) {
        if (ContextUtil.isAbstractType(_type)) {
            return true;
        }
        String arg_;
        MethodAccessKind ctx_;
        if (!isIntermediateDottedOperation()) {
            arg_ = _page.getGlobalClass();
            ctx_ = _page.getStaticContext();
        } else {
            arg_ = getPreviousResultClass().getSingleNameOrEmpty();
            ctx_ = isStaticAccess();
        }
        return koInstancingType(_realClassName, ctx_, _page, _type, arg_);
    }

    private void analyzeCtor(String _realClassName, String _paramVargArg, AnalyzedPageEl _page) {
        int varargOnly_ = lookOnlyForVarArg();
        ClassMethodIdAncestor idMethod_ = lookOnlyForId();
        String base_ = StringExpUtil.getIdFromAllTypes(_realClassName);
        AnaGeneType g_ = _page.getAnaGeneType(base_);
        if (g_ == null) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_page.getLocalizer().getCurrentFileName());
            call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //type len
            call_.buildError(_page.getAnalysisMessages().getIllegalCtorUnknown(),
                    _realClassName);
            _page.getLocalizer().addError(call_);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            addErr(call_.getBuiltError());
            return;
        }
        for (String p:StringExpUtil.getWildCards(_realClassName)){
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_page.getLocalizer().getCurrentFileName());
            call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //part type len
            call_.buildError(_page.getAnalysisMessages().getIllegalCtorBound(),
                    p,
                    _realClassName);
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
        }
        if (ContextUtil.isAbstractType(g_) && !ContextUtil.isEnumType(g_)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_page.getLocalizer().getCurrentFileName());
            call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //type len
            call_.buildError(_page.getAnalysisMessages().getIllegalCtorAbstract(),
                    base_);
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_realClassName));
            return;
        }
        instancingStdContent.setBlockIndex(getCurrentChildTypeIndex(g_, _realClassName, _page));
        if (instancingStdContent.getBlockIndex() < -1) {
            return;
        }
        ConstructorId feed_ = null;
        if (idMethod_ != null) {
            ClassMethodId id_ = idMethod_.getClassMethodId();
            String idClass_ = id_.getClassName();
            feed_ = MethodId.to(idClass_, id_.getConstraints());
        }
        if (g_ instanceof RecordBlock) {
            CustList<OperationNode> childrenNodes_ = getChildrenNodes();
            for (OperationNode o: childrenNodes_) {
                if (!(o instanceof NamedArgumentOperation) || o.getFirstChild() instanceof WrappOperation) {
                    FoundErrorInterpret call_ = new FoundErrorInterpret();
                    call_.setFileName(_page.getLocalizer().getCurrentFileName());
                    call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //type len
                    call_.buildError(_page.getAnalysisMessages().getIllegalCtorAbstract(),
                            base_);
                    _page.getLocalizer().addError(call_);
                    addErr(call_.getBuiltError());
                    setResultClass(new AnaClassArgumentMatching(_realClassName));
                    return;
                }
            }
            NameParametersFilter filter_ = buildQuickStrictFilter(_page,_realClassName,(RecordBlock) g_, this);
            for (NamedArgumentOperation o: filter_.getParameterFilterErr()) {
                String name_ = o.getName();
                o.setRelativeOffsetPossibleAnalyzable(o.getIndexInEl()+ o.getOffsetTr(), _page);
                FoundErrorInterpret b_;
                b_ = new FoundErrorInterpret();
                b_.setFileName(_page.getLocalizer().getCurrentFileName());
                b_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //param name len
                b_.buildError(_page.getAnalysisMessages().getDuplicatedParamName(),
                        name_);
                _page.getLocalizer().addError(b_);
                o.addErr(b_.getBuiltError());
            }
            if (!filter_.getParameterFilterErr().isEmpty()) {
                setResultClass(new AnaClassArgumentMatching(_realClassName));
                return;
            }
            getMemberId().setRootNumber(((RecordBlock)g_).getNumberAll());
            AnaTypeFct constructor_ = new AnaTypeFct();
            constructor_.setType((RecordBlock)g_);
            setConstructor(constructor_);
            setFormattedType(new AnaFormattedRootBlock((RootBlock) g_,_realClassName));
            setResultClass(new AnaClassArgumentMatching(_realClassName));
            return;
        }
        NameParametersFilter name_ = buildFilter(_page);
        if (!name_.getParameterFilterErr().isEmpty()) {
            setResultClass(new AnaClassArgumentMatching(_realClassName));
            return;
        }
        if (innerElt != null && innerElt.koType()) {
            setResultClass(new AnaClassArgumentMatching(_realClassName));
            return;
        }
        ConstrustorIdVarArg ctorRes_ = getDeclaredCustConstructor(varargOnly_, new AnaClassArgumentMatching(_realClassName), g_, feed_, _paramVargArg, name_, _page);
        if (ctorRes_ == null) {
            buildCtorError(name_,_page,_realClassName);
            setResultClass(new AnaClassArgumentMatching(_realClassName));
            return;
        }
        result(_page,_realClassName, g_, ctorRes_, name_);
        setResultClass(new AnaClassArgumentMatching(_realClassName));
    }
    private NameParametersFilter buildQuickStrictFilter(AnalyzedPageEl _page,String _real,RootBlock _root, MethodOperation _par) {
        StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
        NameParametersFilter out_ = new NameParametersFilter();
        CustList<OperationNode> childrenNodes_ = _par.getChildrenNodes();
//        CustList<NamedArgumentOperation> filter_ = out_.getParameterFilter();
        CustList<NamedArgumentOperation> filterErr_ = out_.getParameterFilterErr();
        CustList<ClassField> names_ = new CustList<ClassField>();
//        boolean ok_ = true;
        for (OperationNode o: childrenNodes_) {
            ClassField idField_ = ((NamedArgumentOperation) o).getIdField();
            String name_ = idField_.getFieldName();
            boolean contained_ = false;
            String par_ = ((NamedArgumentOperation) o).format(_real, _root);
            if (!par_.isEmpty()) {
                String importedClassName_ = ((NamedArgumentOperation) o).getImportedClassName();
                instancingStdContent.getNamedFields().add(new AnaNamedFieldContent(name_, importedClassName_,idField_.getClassName(),((NamedArgumentOperation) o).getField()));
                Mapping m_ = new Mapping();
                m_.setArg(o.getResultClass());
                m_.setParam(par_);
                m_.setMapping(vars_);
                if (!AnaInherits.isCorrectOrNumbers(m_, _page)){
                    ClassMethodIdReturn res_ = OperationNode.tryGetDeclaredImplicitCast(par_, o.getResultClass(), _page);
                    if (res_ != null) {
                        o.getResultClass().implicitInfos(res_);
                        contained_ = true;
                    }
                } else {
                    contained_ = true;
                }
            }
            if (dupl(names_,idField_) || !contained_) {
//                ok_ = false;
                filterErr_.add(((NamedArgumentOperation) o));
            }
            names_.add(idField_);
            out_.addNamed(((NamedArgumentOperation) o));
        }
//        out_.setOk(ok_);
        return out_;
    }
    private int getCurrentChildTypeIndex(AnaGeneType _type, String _realClassName, AnalyzedPageEl _page) {
        if (ContextUtil.isEnumType(_type)) {
            if (innerElt == null) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                String file_ = _page.getLocalizer().getCurrentFileName();
                int fileIndex_ = _page.getLocalizer().getCurrentLocationIndex();
                call_.setFileName(file_);
                call_.setIndexFile(fileIndex_);
                //type len
                call_.buildError(_page.getAnalysisMessages().getIllegalCtorEnum());
                _page.getLocalizer().addError(call_);
                setResultClass(new AnaClassArgumentMatching(_realClassName));
                addErr(call_.getBuiltError());
                return -2;
            }
            return innerElt.getFieldNumber();
        }
        return -1;
    }

    public StringList getErrorsFields() {
        return errorsFields;
    }

    public InnerTypeOrElement getInnerElt() {
        return innerElt;
    }

    public AnaInstancingStdContent getInstancingStdContent() {
        return instancingStdContent;
    }

    public CustList<ConstructorInfo> getCtors() {
        return ctors;
    }

}
