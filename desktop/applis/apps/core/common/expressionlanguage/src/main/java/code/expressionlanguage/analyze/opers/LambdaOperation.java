package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.analyze.util.*;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.fwd.opers.AnaLambdaCommonContent;
import code.expressionlanguage.fwd.opers.AnaLambdaFieldContent;
import code.expressionlanguage.fwd.opers.AnaLambdaMethodContent;
import code.expressionlanguage.fwd.opers.AnaNamedFieldContent;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.StringUtil;

public final class LambdaOperation extends LeafOperation implements PossibleIntermediateDotted {

    private AnaClassArgumentMatching previousResultClass;
    private final AnaLambdaCommonContent lambdaCommonContent;
    private MemberId lambdaMemberNumberContentId;

    private final String className;

    private ClassMethodId method;
    private final AnaLambdaMethodContent lambdaMethodContent;
    private ConstructorId realId;
    private final Ints offsets = new Ints();
    private final Ints refs = new Ints();
    private int recordType = -1;
    private RootBlock fieldType;
    private AnaTypeFct function;
    private ClassField fieldId;
    private final AnaLambdaFieldContent lambdaFieldContent;
    private int valueOffset;
    private final CustList<AnaResultPartType> partOffsetsPre = new CustList<AnaResultPartType>();
    private final CustList<AnaResultPartType> partOffsets = new CustList<AnaResultPartType>();
    private final CustList<AnaResultPartType> partOffsetsRec = new CustList<AnaResultPartType>();
    private final CustList<AnaResultPartType> partOffsetsBegin = new CustList<AnaResultPartType>();
    private InfoErrorDto partOffsetsErrMiddle = new InfoErrorDto("");
    private InfoErrorDto partOffsetsErrEnd = new InfoErrorDto("");
    private StandardMethod standardMethod;
    private StandardType standardType;
    private StandardConstructor standardConstructor;
    private AnaGeneType staticAccess;
    private final CustList<AnaNamedFieldContent> namedFields = new CustList<AnaNamedFieldContent>();
    private final CustList<AnaFormattedRootBlock> sups = new CustList<AnaFormattedRootBlock>();
    private final CustList<AnaResultPartType> partsInstInitInterfaces = new CustList<AnaResultPartType>();

    public LambdaOperation(int _indexInEl, int _indexChild, MethodOperation _m,
            OperationsSequence _op) {
        super(_indexInEl, _indexChild, _m, _op);
        lambdaCommonContent = new AnaLambdaCommonContent();
        lambdaMemberNumberContentId = new MemberId();
        lambdaFieldContent = new AnaLambdaFieldContent();
        lambdaMethodContent = new AnaLambdaMethodContent();
        className = _op.getValues().firstValue();
        previousResultClass = new AnaClassArgumentMatching(EMPTY_STRING);
    }

    @Override
    public void analyze(AnalyzedPageEl _page) {
        setRelativeOffsetPossibleAnalyzable(getIndexInEl(), _page);
        lambdaCommonContent.setReturnFieldType(_page.getAliasObject());
        String extr_ = className.substring(className.indexOf('(')+1, className.lastIndexOf(')'));
        StringList args_ = StringExpUtil.getAllSepCommaTypes(extr_);
        int len_ = args_.size();
        if (len_ >= 2) {
            tryCheck(_page);
            generalProcess(args_, _page);
            return;
        }
        MethodOperation parent_ = getParent();
        if (!(parent_ instanceof DotOperation) || getIndexChild() <= 0 || parent_.getFirstChild() instanceof StaticAccessOperation) {
            tryCheck(_page);
            errInfer(_page, len_);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        KeyWords keyWords_ = _page.getKeyWords();
        String new_ = keyWords_.getKeyWordNew();
        OperationNode o_ = parent_.getFirstChild();
        ParentInferring par_ = ParentInferring.getParentInferring(parent_);
        OperationNode m_ = par_.getOperation();
        String foundType_ = foundType(_page, par_);
        StringList candidates_ = initCandidates(_page, foundType_);
        boolean list_ = false;
        if (m_ instanceof ArgumentListInstancing) {
            list_ = true;
            m_ = m_.getParent().getParent();
        }
        completeCandidate(_page, par_, m_, candidates_, list_);
        StringList bounds_ = new StringList();
        for (String c : previousResultClass.getNames()) {
            bounds_.addAllElts(InvokingOperation.getBounds(c, _page));
        }
        if (!(o_ instanceof StaticCallAccessOperation)) {
            simpleInfer(_page, args_, o_, candidates_, bounds_);
            return;
        }
        StaticCallAccessOperation stCall_ = (StaticCallAccessOperation) o_;
        if (StringUtil.quickEq("<>", stCall_.getStCall())) {
            stCall_.check(_page);
            errInfer(_page, len_);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (StringUtil.quickEq(args_.first().trim(), new_)) {
            ctorInfer(_page, len_, candidates_, bounds_, stCall_);
            return;
        }
        staticCallInger(_page, args_, o_, candidates_, bounds_, stCall_);
    }

    private void staticCallInger(AnalyzedPageEl _page, StringList _args, OperationNode _o, StringList _candidates, StringList _bounds, StaticCallAccessOperation _stCall) {
        int len_ = _args.size();
        StringList a_ = cloneArrayBounds(_bounds);
        if (a_.onlyOneElt()) {
            arrInfer(_page, _args, _o, a_);
            return;
        }
        boolean polymorph_ = poly(_page, _args);
        String name_ = extract(_args);
        CustList<ClassMethodIdReturn> resList_ = new CustList<ClassMethodIdReturn>();
        CustList<CustList<MethodInfo>> methodsInst_;
        methodsInst_ = getDeclaredCustMethodByType(MethodAccessKind.INSTANCE, _bounds, name_, false, _page, new ScopeFilter(null, true, true, false, !polymorph_, _page.getGlobalClass()), new FormattedFilter());
        CustList<CustList<MethodInfo>> methodsSta_;
        methodsSta_ = getDeclaredCustMethodByType(MethodAccessKind.STATIC_CALL, _bounds, name_, false, _page, new ScopeFilter(null, true, true, false, !polymorph_, _page.getGlobalClass()), new FormattedFilter());
        //use types of previous operation
        for (String s : _candidates) {
            StringList allTypes_ = StringExpUtil.getAllTypes(s);
            if (allTypes_.size() == 1) {
                tryAddMeth(methodsInst_, _page, name_, resList_, null, "", _stCall.getStCall());
                continue;
            }
            StringList argsTypes_ = new StringList(allTypes_.mid(1, allTypes_.size() - 2));
            String ret_ = allTypes_.last();
            tryAddMeth(methodsSta_, _page, name_, resList_, argsTypes_, ret_, _stCall.getStCall());
            if (!argsTypes_.isEmpty() && !argsTypes_.first().startsWith("~")) {
                Mapping mapp_ = new Mapping();
                mapp_.setArg(argsTypes_.first());
                mapp_.setParam(new AnaClassArgumentMatching(_bounds));
                mapp_.setMapping(_page.getCurrentConstraints().getCurrentConstraints());
                if (AnaInherits.isCorrectOrNumbers(mapp_, _page)) {
                    argsTypes_.remove(0);
                    tryAddMeth(methodsInst_, _page, name_, resList_, argsTypes_, ret_, _stCall.getStCall());
                }
            }
        }
        resList_ = AnaTemplates.reduceMethods(resList_);
        if (resList_.size() == 1) {
            ClassMethodIdReturn id_ = resList_.first();
            trySetPoly(id_, polymorph_);
            initIdMethod(id_);
            String foundClass_ = id_.getRealClass();
            if (!_stCall.getStCall().isEmpty()) {
                _stCall.setPartOffsets(new ResolvedInstance(_stCall, foundClass_));
            }
            String fct_ = formatReturn(_page, id_);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        tryCheck(_page);
        errInfer(_page, len_);
        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
    }

    private void ctorInfer(AnalyzedPageEl _page, int _len, StringList _candidates, StringList _bounds, StaticCallAccessOperation _stCall) {
        StringList a_ = cloneArrayBounds(_bounds);
        if (a_.onlyOneElt()) {
            String prev_ = a_.first();
            StringList parts_ = new StringList();
            lambdaCommonContent.setFoundFormatted(simpleFormatted(prev_.substring(1)));
            parts_.add(_page.getAliasPrimInteger());
            realId = new ConstructorId(prev_, parts_, true);
            parts_.add(prev_);
            StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
            fct_.append(StringExpUtil.TEMPLATE_BEGIN);
            fct_.append(StringUtil.join(parts_, StringExpUtil.TEMPLATE_SEP));
            fct_.append(StringExpUtil.TEMPLATE_END);
            lambdaCommonContent.setResult(fct_.toString());
            setResultClass(new AnaClassArgumentMatching(fct_.toString()));
            return;
        }
        String prev_ = previousResultClass.getSingleNameOrEmpty();
        _stCall.check(_page);
        String id_ = StringExpUtil.getIdFromAllTypes(prev_);
        AnaGeneType h_ = _page.getAnaGeneType(id_);
        if (noDefCtor(h_)) {
            errInfer(_page, _len);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        CustList<ConstrustorIdVarArg> ctors_ = ctorsInfer(_page, _candidates, _stCall, h_);
        if (ctors_.size() == 1) {
            ConstrustorIdVarArg ctorRes_ = ctors_.first();
            initIdCtor(ctorRes_);
            ConstructorId fid_ = ctorRes_.getConstId();
            StringList parts_ = new StringList();
            if (!h_.withoutInstance()) {
                //From analyze
                StringList innerParts_ = StringExpUtil.getAllPartInnerTypes(fid_.getName());
                parts_.add(StringUtil.join(innerParts_.left(innerParts_.size() - 2), ""));
            }

            appendArgsCtor(fid_, parts_);
            parts_.add(fid_.getName());
            StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
            fct_.append(StringExpUtil.TEMPLATE_BEGIN);
            fct_.append(StringUtil.join(parts_, StringExpUtil.TEMPLATE_SEP));
            fct_.append(StringExpUtil.TEMPLATE_END);
            lambdaCommonContent.setResult(fct_.toString());
            setResultClass(new AnaClassArgumentMatching(fct_.toString()));
            return;
        }
        errInfer(_page, _len);
        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
    }

    private void simpleInfer(AnalyzedPageEl _page, StringList _args, OperationNode _o, StringList _candidates, StringList _bounds) {
        int len_ = _args.size();
        StringList a_ = cloneArrayBounds(_bounds);
        if (a_.onlyOneElt()) {
            arrInfer(_page, _args, _o, a_);
            return;
        }
        boolean polymorph_ = poly(_page, _args);
        String name_ = extract(_args);
        CustList<ClassMethodIdReturn> resList_ = new CustList<ClassMethodIdReturn>();
        CustList<CustList<MethodInfo>> methodsInst_ = getDeclaredCustMethodByType(MethodAccessKind.INSTANCE, _bounds, name_, false, _page, new ScopeFilter(null, true, true, false, !polymorph_, _page.getGlobalClass()), new FormattedFilter());
        for (String s : _candidates) {
            StringList allTypes_ = StringExpUtil.getAllTypes(s);
            if (allTypes_.size() == 1) {
                tryAddMeth(methodsInst_, _page, name_, resList_, null, "", "");
                continue;
            }
            StringList argsTypes_ = new StringList(allTypes_.mid(1, allTypes_.size() - 2));
            String ret_ = allTypes_.last();
            tryAddMeth(methodsInst_, _page, name_, resList_, argsTypes_, ret_, "");
        }
        resList_ = AnaTemplates.reduceMethods(resList_);
        if (resList_.size() == 1) {
            ClassMethodIdReturn id_ = resList_.first();
            trySetPoly(id_, polymorph_);
            initIdMethod(id_);
            String fct_ = formatReturnPrevious(_page, id_);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        tryCheck(_page);
        errInfer(_page, len_);
        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
    }

    private void completeCandidate(AnalyzedPageEl _page, ParentInferring _par, OperationNode _m, StringList _candidates, boolean _list) {
        StringMap<String> vars_ = new StringMap<String>();
        int nbParentsInfer_ = _par.getNbParentsInfer();
        if (_m instanceof NamedArgumentOperation) {
            NamedArgumentOperation n_ = (NamedArgumentOperation) _m;
            String inferRecord_ = n_.infer();
            if (!inferRecord_.isEmpty()) {
                StringList format_ = InvokingOperation.tryFormatFctRefRec(inferRecord_, nbParentsInfer_, vars_, _page);
                _candidates.addAllElts(format_);
            }
            MethodOperation call_ = n_.getParent();
            if (call_ instanceof RetrieveMethod) {
                feedMethodCandidateIndirect(_page, _candidates,_par, n_);
            }
            if (call_ instanceof RetrieveConstructor) {
                feedCtorCandidateIndirect(_page, _candidates,_par, n_);
            }
        }
        if (_m instanceof RetrieveMethod) {
            feedMethodCandidateDirect(_page, _par, (RetrieveMethod) _m, _candidates, _list);
        }
        if (_m instanceof RetrieveConstructor) {
            feedCtorCandidateDirect(_page, _par, (RetrieveConstructor) _m, _candidates, _list);
        }
    }

    private void feedMethodCandidateIndirect(AnalyzedPageEl _page, StringList _candidates, ParentInferring _par, NamedArgumentOperation _n) {
        int nbParentsInfer_ = _par.getNbParentsInfer();
        String name_ = _n.getName();
        MethodOperation call_ = _n.getParent();
        StringMap<String> vars_ = new StringMap<String>();
        RetrieveMethod f_ = (RetrieveMethod) call_;
        NameParametersFilter filter_ = InvokingOperation.buildQuickFilter(_page, call_);
        CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
        int lenMet_ = methodInfos_.size();
        for (int i = 0; i < lenMet_; i++) {
            int gr_ = methodInfos_.get(i).size();
            CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
            for (int j = 0; j < gr_; j++) {
                MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                if (InvokingOperation.isValidNameIndex(filter_, methodInfo_, name_)) {
                    newList_.add(methodInfo_);
                }
                StringList format_ = InvokingOperation.tryParamFormatFctRef(filter_, methodInfo_, name_, nbParentsInfer_, vars_, _page);
                _candidates.addAllElts(format_);
            }
            methodInfos_.set(i, newList_);
        }
    }

    private void feedMethodCandidateDirect(AnalyzedPageEl _page, ParentInferring _par, RetrieveMethod _m, StringList _candidates, boolean _list) {
        StringMap<String> vars_ = new StringMap<String>();
        int nbParentsInfer_ = _par.getNbParentsInfer();
        OperationNode firstChild_ = _m.getFirstChild();
        int deltaCount_ = InvokingOperation.getDeltaCount(_list, firstChild_);
        int indexChild_ = _par.getOperationChild().getIndexChild() - deltaCount_;
        CustList<CustList<MethodInfo>> methodInfos_ = _m.getMethodInfos();
        int lenMet_ = methodInfos_.size();
        for (int i = 0; i < lenMet_; i++) {
            int gr_ = methodInfos_.get(i).size();
            for (int j = 0; j < gr_; j++) {
                MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                StringList format_ = InvokingOperation.tryFormatFctRef(methodInfo_, indexChild_, nbParentsInfer_, vars_, _page);
                _candidates.addAllElts(format_);
            }
        }
    }

    private void feedCtorCandidateIndirect(AnalyzedPageEl _page, StringList _candidates, ParentInferring _par, NamedArgumentOperation _n) {
        StringMap<String> vars_ = new StringMap<String>();
        int nbParentsInfer_ = _par.getNbParentsInfer();
        String name_ = _n.getName();
        MethodOperation call_ = _n.getParent();
        RetrieveConstructor f_ = (RetrieveConstructor) call_;
        NameParametersFilter filter_ = InvokingOperation.buildQuickFilter(_page, call_);
        CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
        int lenMet_ = methodInfos_.size();
        CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
        for (int i = 0; i < lenMet_; i++) {
            ConstructorInfo methodInfo_ = methodInfos_.get(i);
            if (InvokingOperation.isValidNameIndex(filter_, methodInfo_, name_)) {
                newList_.add(methodInfo_);
            }
            StringList format_ = InvokingOperation.tryParamFormatFctRef(filter_, methodInfo_, name_, nbParentsInfer_, vars_, _page);
            _candidates.addAllElts(format_);
        }
        methodInfos_.clear();
        methodInfos_.addAllElts(newList_);
    }

    private void feedCtorCandidateDirect(AnalyzedPageEl _page, ParentInferring _par, RetrieveConstructor _m, StringList _candidates, boolean _list) {
        StringMap<String> vars_ = new StringMap<String>();
        int nbParentsInfer_ = _par.getNbParentsInfer();
        OperationNode firstChild_ = _m.getFirstChild();
        int deltaCount_ = InvokingOperation.getDeltaCount(_list, firstChild_);
        int indexChild_ = _par.getOperationChild().getIndexChild() - deltaCount_;
        CustList<ConstructorInfo> methodInfos_ = _m.getCtors();
        int lenMet_ = methodInfos_.size();
        for (int i = 0; i < lenMet_; i++) {
            ConstructorInfo methodInfo_ = methodInfos_.get(i);
            StringList format_ = InvokingOperation.tryFormatFctRef(methodInfo_, indexChild_, nbParentsInfer_, vars_, _page);
            _candidates.addAllElts(format_);
        }
    }

    private StringList initCandidates(AnalyzedPageEl _page, String _foundType) {
        StringList candidates_ = new StringList();
        if (!_foundType.isEmpty()) {
            Mapping mapping_ = new Mapping();
            mapping_.setMapping(_page.getCurrentConstraints().getCurrentConstraints());
            mapping_.setParam(_page.getAliasFct());
            mapping_.setArg(_foundType);
            if (AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                candidates_.add(_foundType);
            } else {
                StringList conv_ = InvokingOperation.tryInferOrImplicitFctRef(new StringMap<String>(), _page, _foundType);
                candidates_.addAllElts(conv_);
            }
        }
        return candidates_;
    }

    private String foundType(AnalyzedPageEl _page, ParentInferring _par) {
        OperationNode m_ = _par.getOperation();
        int nbParentsInfer_ = _par.getNbParentsInfer();
        String typeAff_;
        AbsBk cur_ = _page.getCurrentBlock();
        if (m_ == null && cur_ instanceof ReturnMethod) {
            typeAff_ = InvokingOperation.tryGetRetType(_page);
        } else {
            typeAff_ = InvokingOperation.tryGetTypeAff(m_, 1);
        }
        String keyWordVar_ = _page.getKeyWords().getKeyWordVar();
        String foundType_ = EMPTY_STRING;
        if (!InvokingOperation.isUndefined(typeAff_, keyWordVar_)) {
            String cp_ = StringExpUtil.getQuickComponentType(typeAff_, nbParentsInfer_);
            if (!InvokingOperation.isNotCorrectDim(cp_)) {
                foundType_ = cp_;
            }
        }
        return foundType_;
    }

    private CustList<ConstrustorIdVarArg> ctorsInfer(AnalyzedPageEl _page, StringList _candidates, StaticCallAccessOperation _stCall, AnaGeneType _h) {
        String prev_ = previousResultClass.getSingleNameOrEmpty();
        CustList<ConstrustorIdVarArg> ctors_ = new CustList<ConstrustorIdVarArg>();
        CustList<ConstructorInfo> sgns_ = tryGetSignatures(_h, _page, prev_, _stCall.getStCall());
        for (String s : _candidates) {
            iterateCtorInfer(_page,_stCall,_h,sgns_,s,ctors_);
        }
        return AnaTemplates.reduceCtors(ctors_);
    }
    private void iterateCtorInfer(AnalyzedPageEl _page, StaticCallAccessOperation _stCall, AnaGeneType _h,CustList<ConstructorInfo> _sgns, String _s,CustList<ConstrustorIdVarArg> _ctors) {
        String prev_ = previousResultClass.getSingleNameOrEmpty();
        StringList allTypes_ = StringExpUtil.getAllTypes(_s);
        if (allTypes_.size() == 1) {
            tryFilterAddCtor(_sgns, _page, _h, _ctors, null, _stCall.getStCall(), "");
            return;
        }
        StringList argsTypes_ = new StringList(allTypes_.mid(1, allTypes_.size() - 2));
        String ret_ = allTypes_.last();
        if (koInheritsInfer(_page,_stCall,_h,ret_)) {
            return;
        }
        if (!argsTypes_.isEmpty()) {
            if (AbstractInstancingOperation.koInstancingType(prev_, MethodAccessKind.INSTANCE, _page, _h, argsTypes_.first())) {
                return;
            }
            if (!_h.withoutInstance()) {
                argsTypes_.remove(0);
            }
        }
        tryFilterAddCtor(_sgns, _page, _h, _ctors, argsTypes_, _stCall.getStCall(), ret_);
    }

    private boolean koInheritsInfer(AnalyzedPageEl _page, StaticCallAccessOperation _stCall, AnaGeneType _h, String _ret) {
        if (_ret.startsWith("~")) {
            return true;
        }
        if (!StringUtil.quickEq(_ret, "?")) {
            if (_stCall.getStCall().isEmpty()) {
                Mapping map_ = new Mapping();
                String prev_ = previousResultClass.getSingleNameOrEmpty();
                map_.setArg(prev_);
                StringMap<StringList> mapCtr_ = _page.getCurrentConstraints().getCurrentConstraints();
                map_.getMapping().putAllMap(mapCtr_);
                map_.setParam(_ret);
                return !AnaInherits.isCorrectOrNumbers(map_, _page);
            }
            return !_h.isSubTypeOf(StringExpUtil.getIdFromAllTypes(_ret), _page);
        }
        return false;
    }
    private boolean poly(AnalyzedPageEl _page, StringList _args) {
        KeyWords keyWords_ = _page.getKeyWords();
        String pref_ = "";
        String name_ = _args.first();
        int indexDot_ = name_.indexOf('.');
        if (indexDot_ > -1) {
            pref_ = name_.substring(0, indexDot_).trim();
        }
        return !StringUtil.quickEq(pref_, keyWords_.getKeyWordThat());
    }
    private String extract(StringList _args) {
        String name_ = _args.first();
        int indexDot_ = name_.indexOf('.');
        if (indexDot_ > -1) {
            name_ = name_.substring(indexDot_ + 1).trim();
        }
        return name_;
    }

    private void arrInfer(AnalyzedPageEl _page, StringList _args, OperationNode _o, StringList _a) {
        int len_ = _args.size();
        found(_a.first());
        String name_ = _args.first();
        if (koArrayMethod(name_, _page)) {
            tryCheck(_page);
            errInfer(_page, len_);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        initClonedMethod(_page, name_);
        setResultClass(new AnaClassArgumentMatching(buildCloned(_page, _a.first(), _o instanceof StaticCallAccessOperation)));
    }

    private void errInfer(AnalyzedPageEl _page, int _len) {
        FoundErrorInterpret badCall_ = new FoundErrorInterpret();
        badCall_.setFile(_page.getCurrentFile());
        badCall_.setIndexFile(_page);
        //last parenthesis
        badCall_.buildError(_page.getAnalysisMessages().getSplitComa(),
                Long.toString(2),
                Long.toString(_len)
        );
        _page.getLocalizer().addError(badCall_);
        int i_ = className.lastIndexOf(')');
        errPart(badCall_, _page, i_, 1);
    }

    private void errPart(FoundErrorInterpret _err, AnalyzedPageEl _page, int _begin, int _length) {
        partOffsetsErrEnd = new InfoErrorDto(_err.getBuiltError(),_page, _begin, _length);
    }

    private void initIdMethod(ClassMethodIdReturn _id) {
        initId(_id);
        setupFct();
    }

    private void initClonedMethod(AnalyzedPageEl _page, String _name) {
        lambdaMethodContent.setClonedMethod(true);
        prMethArr(_page, _name);
    }

    private void trySetPoly(ClassMethodIdReturn _id, boolean _poly) {
        if (!_id.getRealId().isStaticMethod()&&_poly) {
            lambdaMethodContent.setPolymorph(true);
        }
    }

    private void tryCheck(AnalyzedPageEl _page) {
        MethodOperation parent_ = getParent();
        if (parent_ != null) {
            OperationNode firstChild_ = parent_.getFirstChild();
            if (firstChild_ instanceof StaticCallAccessOperation) {
                ((StaticCallAccessOperation)firstChild_).check(_page);
            }
        }
    }

    private static void tryAddMeth(CustList<CustList<MethodInfo>> _methods, AnalyzedPageEl _page, String _name, CustList<ClassMethodIdReturn> _resList, StringList _argsTypes, String _ret, String _stCall) {
        ClassMethodIdReturn resultOther_ = getCustResultLambdaInfer(_methods, _name, _page, _stCall, _argsTypes, _ret);
        if (resultOther_ != null) {
            _resList.add(resultOther_);
        }
    }

    private static void tryFilterAddCtor(CustList<ConstructorInfo> _list, AnalyzedPageEl _page, AnaGeneType _h, CustList<ConstrustorIdVarArg> _ctors, StringList _argsTypes, String _stCall, String _retType) {
        Parametrable constructorInfo_ = tryGetFilterSignaturesInfer(_list, _page, _argsTypes,_stCall,_retType);
        if (constructorInfo_ instanceof ConstructorInfo) {
            _ctors.add(buildCtorInfo(constructorInfo_.getClassName(), _h, (ConstructorInfo) constructorInfo_));
        }
    }

    private static void appendArgsCtor(ConstructorId _fid, StringList _parts) {
        int lenArg_ = _fid.getParametersTypesLength();
        for (int i = 0; i < lenArg_; i++) {
            String p_ = _fid.getParametersType(i);
            if (i + 1 == lenArg_ && _fid.isVararg()) {
                p_ = StringExpUtil.getPrettyArrayType(p_);
            }
            _parts.add(pref(_fid.getParametersRef(i),p_));
        }
    }

    private void generalProcess(StringList _args, AnalyzedPageEl _page) {
        if (isIntermediateDottedOperation()&&getParent() instanceof SafeDotOperation) {
            lambdaCommonContent.setSafeInstance(true);
        }
        int len_ = _args.size();
        MethodOperation m_ = getParent();
        String fromType_ = _args.first().trim();
        KeyWords keyWords_ = _page.getKeyWords();
        String operator_ = keyWords_.getKeyWordOperator();
        String new_ = keyWords_.getKeyWordNew();
        if (StringUtil.quickEq(fromType_, operator_)) {
            processOperator(_args, len_, _page);
            return;
        }
        String name_ = _args.get(1).trim();
        if (name_.isEmpty()) {
            processField(m_, _args, len_, _page);
            return;
        }
        if (StringUtil.quickEq(name_, new_)) {
            processInstance(_args, len_, _page);
            return;
        }
        processMethod(m_, _args, len_,
                _page);
    }

    private void processMethod(MethodOperation _m, StringList _args, int _len,
                               AnalyzedPageEl _page) {
        String name_ = _args.get(1).trim();
        if (StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordExplicit())
                || StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordCast())
                || StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordTrue())
                || StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordFalse())) {
            castTrueFalse(_args, _len, _page, name_);
            return;
        }
        if (!isIntermediateDottedOperation()) {
            notIntermediateMethod(_args, _len, _page);
            return;
        }
        if (isStaticAccess(_m)) {
            staticIntermediateMethod(_m, _args, _len, _page);
            return;
        }
        intermediateMethod(_args, _len, _page);
    }

    private void intermediateMethod(StringList _args, int _len, AnalyzedPageEl _page) {
        String name_ = _args.get(1).trim();
        int vararg_ = -1;
        LambdaMethodChoice incr_ = new LambdaMethodChoice(name_,lambdaMethodContent);
        incr_.incr(_args, _len, _page);
        int i_ = incr_.getIndex();
        name_ = incr_.getName();
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordId_ = keyWords_.getKeyWordId();
        MethodId argsResModif_;
        ClassMethodIdAncestor feed_ = null;
        StringList str_;
        MethodAccessKind stCtx_;
        if (matchIdKeyWord(_args, _len, i_, keyWordId_)) {
            MethodAccessId idUpdate_ = new MethodAccessId(i_);
            String keyWordStatic_ = _page.getKeyWords().getKeyWordStatic();
            String keyWordStaticCall_ = _page.getKeyWords().getKeyWordStaticCall();
            idUpdate_.setupInfosId(i_ + 1, _args,keyWordStatic_,keyWordStaticCall_);
            boolean retRef_ = idUpdate_.isRetRef();
            stCtx_ = idUpdate_.getKind();
            i_ = idUpdate_.getIndex();
            int offset_ = offset(_args, 0);
            String type_ = resolveCorrectTypeAccessible(stCtx_ != MethodAccessKind.STATIC, _args.first().trim(), _page, offset_,partOffsetsBegin);
            str_ = InvokingOperation.getBounds(type_, _page);
            String cl_ = StringExpUtil.getIdFromAllTypes(type_);
            MethodId argsRes_ = resolveArguments(i_ + 1, _args, _page, new LambdaPartTypeId(className, partOffsets, cl_), retRef_, stCtx_);
            if (argsRes_ == null) {
                return;
            }
            feed_ = new ClassMethodIdAncestor(staticAccess,new ClassMethodId(cl_, MethodId.to(stCtx_, name_, argsRes_)),idUpdate_.getAncestor());
            argsResModif_ = tryFormat(type_, argsRes_, _page);
        } else {
            stCtx_ = MethodAccessKind.INSTANCE;
            str_ = resolveCorrectTypesExact(_args, _page);
            MethodId argsRes_ = resolveArguments(i_, _args, _page, new LambdaPartTypeStd(className, partOffsets), false, MethodAccessKind.INSTANCE);
            if (argsRes_ == null) {
                return;
            }
            argsResModif_ = argsRes_;
            vararg_ = vararg(_len, vararg_, argsRes_, i_);
        }
        StringList methodTypes_ = params(argsResModif_);
        StringList bounds_ = new StringList();
        for (String c: previousResultClass.getNames()) {
            bounds_.addAllElts(InvokingOperation.getBounds(c, _page));
        }
        StringList a_ = cloneArrayBounds(bounds_);
        if (a_.onlyOneElt()) {
            intermediateArr(_page, name_, methodTypes_, bounds_, a_);
            return;
        }
        Mapping map_ = buildMapping(_page,str_,bounds_);
        if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
            badCast(_page, str_, bounds_);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        intermadiatedTryFindMethod(_page,incr_, str_, methodTypes_, stCtx_, vararg_, feed_);
    }

    private void badCast(AnalyzedPageEl _page, StringList _str, StringList _bounds) {
        badCast(_page, _page.getAnalysisMessages().getBadImplicitCast(), StringUtil.join(_bounds, ExportCst.JOIN_TYPES), StringUtil.join(_str, ExportCst.JOIN_TYPES));
    }

    private void badCast(AnalyzedPageEl _page, String _message, String _argOne, String _argTwo) {
        FoundErrorInterpret cast_ = new FoundErrorInterpret();
        cast_.setFile(_page.getCurrentFile());
        cast_.setIndexFile(_page);
        //key word len
        cast_.buildError(_message,
                _argOne,
                _argTwo);
        _page.getLocalizer().addError(cast_);
        addErr(cast_.getBuiltError());
    }

    private void intermadiatedTryFindMethod(AnalyzedPageEl _page, LambdaMethodChoice _incr, StringList _str, StringList _methodTypes, MethodAccessKind _stCtx, int _vararg, ClassMethodIdAncestor _feed) {
        boolean staticChoiceMethod_ = _incr.isStaticChoiceMethod();
        boolean baseAccess_ = _incr.isBaseAccess();
        boolean accessSuper_ = _incr.isAccessSuper();
        String name_ = _incr.getName();
        ClassMethodIdReturn id_ = getCustResultLambda(fetchVarargOnly(_vararg, _feed), getDeclaredCustMethodByType(_stCtx, _str, name_, false, _page, new ScopeFilter(_feed, baseAccess_, accessSuper_, false, staticChoiceMethod_, _page.getGlobalClass()), new FormattedFilter()), name_, _page, _methodTypes);
        if (id_ == null) {
            ClassMethodIdReturn id2_ = getCustResultLambda(fetchVarargOnly(_vararg, _feed), getDeclaredCustMethodByType(_stCtx, _str, name_, false, _page, new ScopeFilter(_feed, baseAccess_, accessSuper_, false, _page.getGlobalClass()), new FormattedFilter()), name_, _page, _methodTypes);
            if (id2_ != null) {
                initIdMethod(id2_);
                String fct_ = formatReturnPrevious(_page, id2_);
                setResultClass(new AnaClassArgumentMatching(fct_));
                processAbstract(id2_, _page);
                return;
            }
            buildErrNoRefMethod(_stCtx, name_, _page, _methodTypes);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        initIdMethod(id_);
        String fct_ = formatReturnPrevious(_page, id_);
        setResultClass(new AnaClassArgumentMatching(fct_));
    }

    private void intermediateArr(AnalyzedPageEl _page, String _name, StringList _methodTypes, StringList _bounds, StringList _a) {
        found(_a.first());
        if (isRangeAccess(_name)) {
            if (_methodTypes.isEmpty()) {
                initArrRange(_page, _name, _a.first());
                setResultClass(new AnaClassArgumentMatching(buildFctRange(_page, _name, _a, false)));
                return;
            }
            initArrRangeBound(_page, _name, _a.first());
            setResultClass(new AnaClassArgumentMatching(buildFctRangeBound(_page, _name, _a, false)));
            return;
        }
        if (isAccess(_name)) {
            if (_methodTypes.isEmpty()) {
                StringBuilder fct_ = buildFctLen(_page, _name, _a, false);
                setResultClass(new AnaClassArgumentMatching(fct_.toString()));
                return;
            }
            prAcc(_page, _name, _methodTypes, false, _a.first());
            return;
        }
        if (!StringUtil.quickEq(_name, _page.getAliasClone())) {
            addErrArray(_bounds, _page);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        initClonedMethod(_page, _name);
        setResultClass(new AnaClassArgumentMatching(buildCloned(_page, _a.first(), false)));
    }

    private void staticIntermediateMethod(MethodOperation _m, StringList _args, int _len, AnalyzedPageEl _page) {
        String name_ = _args.get(1).trim();
        OperationNode o_ = _m.getFirstChild();
        boolean stAcc_ = o_ instanceof StaticAccessOperation;
        boolean stAccCall_ = o_ instanceof StaticCallAccessOperation;
        AnaGeneType accessType_ = getAnaGeneType(o_);
        StringList str_ = resolveCorrectTypes(stAccCall_, _args, _page);
        int vararg_ = -1;
        MethodId argsResModif_;
        ClassMethodIdAncestor feed_ = null;
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordId_ = keyWords_.getKeyWordId();
        MethodAccessKind kind_;
        if (stAcc_) {
            kind_ = MethodAccessKind.STATIC;
        } else {
            kind_ = MethodAccessKind.STATIC_CALL;
        }
        if (3 < _len && StringUtil.quickEq(_args.get(2).trim(), keyWordId_)) {
            String nameId_ = _args.get(3).trim();
            int offset_ = offset(_args,3);
            String cl_;
            if (nameId_.isEmpty()) {
                cl_ = previousResultClass.getSingleNameOrEmpty();
            } else {
                ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(offset_, nameId_, _page);
                cl_ = resolvedIdType_.getFullName();
                accessType_ = resolvedIdType_.getGeneType();
                partOffsetsBegin.add(resolvedIdType_.getDels());
            }
            if (cl_.isEmpty()) {
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            MethodAccessId acc_ = new MethodAccessId(4);
            acc_.setupAncestorId(_args,4);
            boolean retRef_ = acc_.isRetRef();
            int ind_ = acc_.getIndex();
            MethodId argsRes_ = resolveArguments(ind_, _args, _page, new LambdaPartTypeId(className, partOffsets, cl_), retRef_, kind_);
            if (argsRes_ == null) {
                return;
            }
            String idFrom_ = StringExpUtil.getIdFromAllTypes(cl_);
            feed_ = new ClassMethodIdAncestor(accessType_,new ClassMethodId(idFrom_, MethodId.to(kind_, name_, argsRes_)),acc_.getAncestor());
            argsResModif_ = tryFormat(cl_, argsRes_, _page);
        } else {
            MethodId argsRes_ = resolveArguments(2, _args, _page, new LambdaPartTypeStd(className, partOffsets), false, MethodAccessKind.INSTANCE);
            if (argsRes_ == null) {
                return;
            }
            argsResModif_ = argsRes_;
            vararg_ = vararg(_len, vararg_, argsRes_, 2);
        }
        StringList methodTypes_ = params(argsResModif_);
        ClassMethodIdReturn id_ = getCustResultLambda(fetchVarargOnly(vararg_, feed_), getDeclaredCustMethodByType(kind_, str_, name_, false, _page, new ScopeFilter(feed_, true, true, false, _page.getGlobalClass()), new FormattedFilter()), name_, _page, methodTypes_);
        if (id_ == null) {
            buildErrNoRefMethod(kind_, name_, _page, methodTypes_);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        initIdMethod(id_);
        String fct_ = formatReturnPrevious(_page, id_);
        setResultClass(new AnaClassArgumentMatching(fct_));
    }

    private void notIntermediateMethod(StringList _args, int _len, AnalyzedPageEl _page) {
        String name_ = _args.get(1).trim();
        if (_len == 3 && StringUtil.quickEq(name_, "=")) {
            int offset_ = offset(_args, 0);
            String type_ = resolveCorrectTypeAccessible(true, _args.first().trim(), _page, offset_,partOffsetsBegin);
            found(type_);
            String rightPart_ = _args.last();
            int secOffset_ = offset(_args,2);
            AnaResultPartType resolved_ = ResolvingTypes.resolveCorrectTypeAccessible(secOffset_, rightPart_, _page);
            partOffsets.add(resolved_);
            String arg_ = resolved_.getResult(_page);
            Mapping map_ = new Mapping();
            map_.setArg(arg_);
            map_.setParam(type_);
            StringMap<StringList> maps_ = new StringMap<StringList>();
            getRefConstraints(maps_, _page);
            map_.setMapping(maps_);
            if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
                badCast(_page, _page.getAnalysisMessages().getBadImplicitCast(), arg_, type_);
            }
            StringBuilder fct_ = fctVariableSet(_page, type_, arg_);
            lambdaCommonContent.setResult(fct_.toString());
            StringList params_ = new StringList();
            params_.add(type_);
            params_.add(arg_);
            MethodId id_ = new MethodId(MethodAccessKind.STATIC, "=", params_);
            method = new ClassMethodId(type_, id_);
            setResultClass(new AnaClassArgumentMatching(fct_.toString()));
            return;
        }
        if (_len == 2 && StringUtil.quickEq(name_, ":")) {
            int offset_ = offset(_args, 0);
            String type_ = resolveCorrectTypeAccessible(true, _args.first().trim(), _page, offset_,partOffsetsBegin);
            found(type_);
            StringBuilder fct_ = fctVariableGet(_page, type_);
            lambdaCommonContent.setResult(fct_.toString());
            StringList params_ = new StringList();
            params_.add(type_);
            MethodId id_ = new MethodId(MethodAccessKind.STATIC, ":", params_);
            method = new ClassMethodId(type_, id_);
            setResultClass(new AnaClassArgumentMatching(fct_.toString()));
            return;
        }
        if (_len == 2 && StringUtil.quickEq(name_, "=")) {
            int offset_ = offset(_args, 0);
            String type_ = resolveCorrectTypeAccessible(true, _args.first().trim(), _page, offset_,partOffsetsBegin);
            found(type_);
            StringBuilder fct_ = fctVariableSet(_page, type_, type_);
            lambdaCommonContent.setResult(fct_.toString());
            StringList params_ = new StringList();
            params_.add(type_);
            params_.add(type_);
            MethodId id_ = new MethodId(MethodAccessKind.STATIC, "=", params_);
            method = new ClassMethodId(type_, id_);
            setResultClass(new AnaClassArgumentMatching(fct_.toString()));
            return;
        }
        LambdaMethodChoice incr_ = new LambdaMethodChoice(name_,lambdaMethodContent);
        incr_.incr(_args, _len, _page);
        int i_ = incr_.getIndex();
        name_ = incr_.getName();
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordId_ = keyWords_.getKeyWordId();
        int vararg_ = -1;
        MethodId argsResModif_;
        ClassMethodIdAncestor feed_ = null;
        StringList str_;
        if (matchIdKeyWord(_args, _len, i_, keyWordId_)) {
            MethodAccessId idUpdate_ = new MethodAccessId(i_);
            String keyWordStatic_ = _page.getKeyWords().getKeyWordStatic();
            String keyWordStaticCall_ = _page.getKeyWords().getKeyWordStaticCall();
            idUpdate_.setupInfosId(i_ + 1, _args,keyWordStatic_,keyWordStaticCall_);
            boolean retRef_ = idUpdate_.isRetRef();
            MethodAccessKind staticFlag_ = idUpdate_.getKind();
            i_ = idUpdate_.getIndex();
            int offset_ = offset(_args, 0);
            String type_ = resolveCorrectTypeAccessible(staticFlag_ != MethodAccessKind.STATIC, _args.first().trim(), _page, offset_,partOffsetsBegin);
            str_ = InvokingOperation.getBounds(type_, _page);
            String cl_ = StringExpUtil.getIdFromAllTypes(type_);
            MethodId argsRes_ = resolveArguments(i_ + 1, _args, _page, new LambdaPartTypeId(className, partOffsets, cl_), retRef_, staticFlag_);
            if (argsRes_ == null) {
                return;
            }
            feed_ = new ClassMethodIdAncestor(staticAccess,new ClassMethodId(cl_, MethodId.to(staticFlag_, name_, argsRes_)),idUpdate_.getAncestor());
            argsResModif_ = tryFormat(type_, argsRes_, _page);
        } else {
            str_ = resolveCorrectTypesExact(_args, _page);
            MethodId argsRes_ = resolveArguments(i_, _args, _page, new LambdaPartTypeStd(className, partOffsets), false, MethodAccessKind.INSTANCE);
            if (argsRes_ == null) {
                return;
            }
            argsResModif_ = argsRes_;
            vararg_ = vararg(_len, vararg_, argsRes_, i_);
        }
        StringList methodTypes_ = params(argsResModif_);
        StringList a_ = cloneArrayBounds(str_);
        if (a_.onlyOneElt()) {
            notIntermadiatedArr(_page, name_, methodTypes_, str_, a_);
            return;
        }
        notIntermadiatedTryFindMethod(_page, methodTypes_, str_,incr_, vararg_, feed_);
    }

    private StringBuilder fctVariableSet(AnalyzedPageEl _page, String _variable, String _value) {
        StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
        fct_.append(StringExpUtil.TEMPLATE_BEGIN);
        fct_.append('~');
        fct_.append(_variable);
        fct_.append(StringExpUtil.TEMPLATE_SEP);
        fct_.append(_value);
        fct_.append(StringExpUtil.TEMPLATE_SEP);
        fct_.append(_value);
        fct_.append(StringExpUtil.TEMPLATE_END);
        return fct_;
    }

    private StringBuilder fctVariableGet(AnalyzedPageEl _page, String _type) {
        StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
        fct_.append(StringExpUtil.TEMPLATE_BEGIN);
        fct_.append('~');
        fct_.append(_type);
        fct_.append(StringExpUtil.TEMPLATE_SEP);
        fct_.append(_type);
        fct_.append(StringExpUtil.TEMPLATE_END);
        return fct_;
    }

    private void notIntermadiatedTryFindMethod(AnalyzedPageEl _page, StringList _methodTypes, StringList _str, LambdaMethodChoice _incr, int _vararg, ClassMethodIdAncestor _feed) {
        boolean staticChoiceMethod_ = _incr.isStaticChoiceMethod();
        boolean baseAccess_ = _incr.isBaseAccess();
        boolean accessSuper_ = _incr.isAccessSuper();
        String name_ = _incr.getName();
        ClassMethodIdReturn id_ = getCustResultLambda(fetchVarargOnly(_vararg, _feed), getDeclaredCustMethodByType(MethodAccessKind.INSTANCE, _str, name_, false, _page, new ScopeFilter(_feed, baseAccess_, accessSuper_, false, staticChoiceMethod_, _page.getGlobalClass()), new FormattedFilter()), name_, _page, _methodTypes);
        if (id_ == null) {
            ClassMethodIdReturn id2_ = getCustResultLambda(fetchVarargOnly(_vararg, _feed), getDeclaredCustMethodByType(MethodAccessKind.INSTANCE, _str, name_, false, _page, new ScopeFilter(_feed, baseAccess_, accessSuper_, false, _page.getGlobalClass()), new FormattedFilter()), name_, _page, _methodTypes);
            if (id2_ != null) {
                initIdMethod(id2_);
                String fct_ = formatReturn(_page, id2_);
                setResultClass(new AnaClassArgumentMatching(fct_));
                processAbstract(id2_, _page);
                return;
            }
            buildErrNoRefMethod(MethodAccessKind.INSTANCE, name_, _page, _methodTypes);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        initIdMethod(id_);
        String fct_ = formatReturn(_page, id_);
        setResultClass(new AnaClassArgumentMatching(fct_));
    }

    private void notIntermadiatedArr(AnalyzedPageEl _page, String _name, StringList _methodTypes, StringList _str, StringList _a) {
        found(_a.first());
        if (isRangeAccess(_name)) {
            if (_methodTypes.isEmpty()) {
                initArrRange(_page, _name, _a.first());
                setResultClass(new AnaClassArgumentMatching(buildFctRange(_page, _name, _a, true)));
                return;
            }
            initArrRangeBound(_page, _name, _a.first());
            setResultClass(new AnaClassArgumentMatching(buildFctRangeBound(_page, _name, _a, true)));
            return;
        }
        if (isAccess(_name)) {
            if (_methodTypes.isEmpty()) {
                StringBuilder fct_ = buildFctLen(_page, _name, _a, true);
                setResultClass(new AnaClassArgumentMatching(fct_.toString()));
                return;
            }
            prAcc(_page, _name, _methodTypes, true, _a.first());
            return;
        }
        if (!StringUtil.quickEq(_name, _page.getAliasClone())) {
            addErrArray(_str, _page);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        initClonedMethod(_page, _name);
        setResultClass(new AnaClassArgumentMatching(buildCloned(_page, _a.first(), true)));
    }

    private void castTrueFalse(StringList _args, int _len, AnalyzedPageEl _page, String _name) {
        CustList<AnaClassArgumentMatching> methodTypes_ = new CustList<AnaClassArgumentMatching>();
        int i_ = 2;
        ClassMethodIdAncestor feed_ = null;
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordId_ = keyWords_.getKeyWordId();
        int offset_ = offset(_args, 0);
        AnaResultPartType result_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_, _args.first().trim(), _page);
        String type_ = result_.getResult(_page);
        partOffsetsBegin.add(result_);
        if (StringUtil.quickEq(_name, _page.getKeyWords().getKeyWordTrue())
            || StringUtil.quickEq(_name, _page.getKeyWords().getKeyWordFalse())) {
            trueFalse(_page, _name, offset_, type_);
            return;
        }
        MethodId argsRes_ = argsResCast(_args, _len, _page, type_);
        if (argsRes_ == null) {
            return;
        }
        if (matchIdKeyWord(_args, _len, i_, keyWordId_)) {
            AnaGeneType geneType_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(type_));
            if (geneType_ == null) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setFile(_page.getCurrentFile());
                un_.setIndexFile(_page, offset_);
                //_in len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                        type_);
                _page.getLocalizer().addError(un_);
                addErr(un_.getBuiltError());
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            String gene_ = geneType_.getGenericString();
            StringList params_ = IdentifiableUtil.params(argsRes_);
            prepend(gene_, params_);
            boolean varargFct_ = argsRes_.isVararg();
            feed_ = new ClassMethodIdAncestor(geneType_,new ClassMethodId(type_, new MethodId(MethodAccessKind.STATIC, _name, params_, varargFct_)),0);
            int nbParams_ = argsRes_.getParametersTypesLength();
            for (int i = 0; i < nbParams_; i++) {
                String format_ = AnaInherits.wildCardFormatParam(type_, argsRes_.getParametersType(i), _page);
                if (format_.isEmpty()) {
                    String fct_ = buildCast(_page, _name, type_, new MethodId(MethodAccessKind.STATIC, _name, new StringList(_page.getAliasObject())));
                    setResultClass(new AnaClassArgumentMatching(fct_));
                    return;
                }
                methodTypes_.add(new AnaClassArgumentMatching(format_));
            }
        } else {
            feed(methodTypes_, argsRes_);
        }
        if (argsRes_.getParametersTypesLength() > 2) {
            badCast(_page, _page.getAnalysisMessages().getSplitComaLow(), Long.toString(2), Long.toString(argsRes_.getParametersTypesLength()));
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (argsRes_.getParametersTypesLength() > 1 && feed_ == null) {
            badCast(_page, _page.getAnalysisMessages().getSplitComaLow(), Long.toString(1), Long.toString(argsRes_.getParametersTypesLength()));
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        cast(_page, _name, methodTypes_, feed_, type_, argsRes_);
    }

    private void prepend(String _gene, StringList _params) {
        if (_params.size() < 2) {
            _params.add(0, _gene);
        }
    }

    private void feed(CustList<AnaClassArgumentMatching> _methodTypes, MethodId _argsRes) {
        int nbParams_ = _argsRes.getParametersTypesLength();
        for (int i = 0; i < nbParams_; i++) {
            _methodTypes.add(new AnaClassArgumentMatching(_argsRes.getParametersType(i)));
        }
    }

    private MethodId argsResCast(StringList _args, int _len, AnalyzedPageEl _page, String _type) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordId_ = keyWords_.getKeyWordId();
        MethodId argsRes_;
        if (matchIdKeyWord(_args, _len, 2, keyWordId_)) {
            String cl_ = StringExpUtil.getIdFromAllTypes(_type);
            argsRes_ = resolveArguments(2 + 1, _args, _page, new LambdaPartTypeId(className, partOffsets, cl_), false, MethodAccessKind.STATIC);
        } else {
            argsRes_ = resolveArguments(2, _args, _page, new LambdaPartTypeStd(className, partOffsets), false, MethodAccessKind.INSTANCE);
        }
        return argsRes_;
    }

    private void trueFalse(AnalyzedPageEl _page, String _name, int _offset, String _type) {
        ClassMethodIdReturn resMethod_;
        if (StringUtil.quickEq(_name, _page.getKeyWords().getKeyWordTrue())){
            resMethod_ = tryGetDeclaredTests(_type, _page, _page.getTrues());
        } else {
            resMethod_ = tryGetDeclaredTests(_type, _page, _page.getFalses());
        }
        if (resMethod_ == null) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page, _offset);
            //_in len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    _type);
            _page.getLocalizer().addError(un_);
            addErr(un_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        lambdaMethodContent.setExpCast(true);
        initIdMethod(resMethod_);
        String fct_ = formatReturnPrevious(_page, resMethod_);
        setResultClass(new AnaClassArgumentMatching(fct_));
    }

    private void cast(AnalyzedPageEl _page, String _name, CustList<AnaClassArgumentMatching> _methodTypes, ClassMethodIdAncestor _feed, String _type, MethodId _argsRes) {
        if (!StringExpUtil.customCast(_type)) {
            String fct_ = buildCast(_page, _name, _type, new MethodId(MethodAccessKind.STATIC, _name, new StringList(_page.getAliasObject())));
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        if (_methodTypes.size() < 2) {
            _methodTypes.add(0, new AnaClassArgumentMatching(_type));
        }
        ClassMethodIdReturn id_;
        if (StringUtil.quickEq(_name, _page.getKeyWords().getKeyWordExplicit())){
            id_ = tryGetCast(_type, _feed, OperationNode.toArgArray(_methodTypes), _page, _page.getExplicitCastMethods(), _page.getExplicitIdCastMethods(), _page.getExplicitFromCastMethods());
        } else {
            id_ = tryGetCast(_type, _feed, OperationNode.toArgArray(_methodTypes), _page, _page.getImplicitCastMethods(), _page.getImplicitIdCastMethods(), _page.getImplicitFromCastMethods());
        }
        if (id_ == null) {
            MethodId idCast_;
            if (_argsRes.getParametersTypesLength() == 0) {
                idCast_ = new MethodId(MethodAccessKind.STATIC, _name,new StringList(_page.getAliasObject()));
            } else {
                idCast_ = new MethodId(MethodAccessKind.STATIC, _name,IdentifiableUtil.params(_argsRes));
            }
            String fct_ = buildCast(_page, _name, _type, idCast_);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        lambdaMethodContent.setExpCast(true);
        initIdMethod(id_);
        String fct_ = formatReturnPrevious(_page, id_);
        setResultClass(new AnaClassArgumentMatching(fct_));
    }

    private AnaGeneType getAnaGeneType(OperationNode _o) {
        AnaGeneType ty_ = null;
        if (_o instanceof StaticAccessOperation) {
            ty_ = ((StaticAccessOperation) _o).getExtractStaticType();
        }
        if (_o instanceof StaticCallAccessOperation) {
            ty_ = ((StaticCallAccessOperation) _o).getExtractStaticType();
        }
        return ty_;
    }

    private static boolean isRangeAccess(String _name) {
        return StringUtil.quickEq(_name,"[:]")||StringUtil.quickEq(_name,"[:]=");
    }

    private StringBuilder buildFctLen(AnalyzedPageEl _page, String _name, StringList _list, boolean _shiftArgument) {
        prMethArr(_page, _name);
        lambdaCommonContent.setShiftArgument(_shiftArgument);
        StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
        fct_.append(StringExpUtil.TEMPLATE_BEGIN);
        String comp_ = _list.first();
        if (_shiftArgument) {
            fct_.append(comp_);
            fct_.append(StringExpUtil.TEMPLATE_SEP);
        }
        fct_.append(_page.getAliasPrimInteger());
        fct_.append(StringExpUtil.TEMPLATE_END);
        lambdaCommonContent.setResult(fct_.toString());
        return fct_;
    }

    private String buildFctRangeBound(AnalyzedPageEl _page, String _name, StringList _list, boolean _shiftArgument) {
        lambdaCommonContent.setShiftArgument(_shiftArgument);
        StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
        fct_.append(StringExpUtil.TEMPLATE_BEGIN);
        String comp_ = _list.first();
        if (_shiftArgument) {
            fct_.append(comp_);
            fct_.append(StringExpUtil.TEMPLATE_SEP);
        }
        fct_.append(_page.getAliasPrimInteger());
        fct_.append(StringExpUtil.TEMPLATE_SEP);
        return endRange(fct_, _page, _name, comp_);
    }

    private String buildFctRange(AnalyzedPageEl _page, String _name, StringList _list, boolean _shiftArgument) {
        lambdaCommonContent.setShiftArgument(_shiftArgument);
        StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
        fct_.append(StringExpUtil.TEMPLATE_BEGIN);
        String comp_ = _list.first();
        if (_shiftArgument) {
            fct_.append(comp_);
            fct_.append(StringExpUtil.TEMPLATE_SEP);
        }
        return endRange(fct_, _page, _name, comp_);
    }

    private String endRange(StringBuilder _fct, AnalyzedPageEl _page, String _name, String _comp) {
        _fct.append(_page.getAliasPrimInteger());
        _fct.append(StringExpUtil.TEMPLATE_SEP);
        if (StringUtil.quickEq("[:]=",_name)) {
            _fct.append(_comp);
            _fct.append(StringExpUtil.TEMPLATE_SEP);
        }
        _fct.append(_comp);
        _fct.append(StringExpUtil.TEMPLATE_END);
        lambdaCommonContent.setResult(_fct.toString());
        return _fct.toString();
    }

    private void initArrRangeBound(AnalyzedPageEl _page, String _name, String _right) {
        StringList params_ = new StringList();
        params_.add(_page.getAliasPrimInteger());
        params_.add(_page.getAliasPrimInteger());
        if (StringUtil.quickEq("[:]=",_name)) {
            params_.add(_right);
        }
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, _name, params_);
        String foundClass_ = StringExpUtil.getPrettyArrayType(_page.getAliasObject());
        method = new ClassMethodId(foundClass_, id_);
    }

    private void initArrRange(AnalyzedPageEl _page, String _name, String _right) {
        StringList params_ = new StringList();
        params_.add(_page.getAliasPrimInteger());
        if (StringUtil.quickEq("[:]=",_name)) {
            params_.add(_right);
        }
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, _name, params_);
        String foundClass_ = StringExpUtil.getPrettyArrayType(_page.getAliasObject());
        method = new ClassMethodId(foundClass_, id_);
    }

    private String buildCast(AnalyzedPageEl _page, String _name, String _type, MethodId _idCast) {
        lambdaCommonContent.setFoundFormatted(simpleFormatted(_type));
        MethodId idCt_ = new MethodId(MethodAccessKind.STATIC, _name, new StringList(_page.getAliasObject()));
        method = new ClassMethodId(_type, idCt_);
        lambdaCommonContent.setAncestor(0);
        lambdaMethodContent.setAbstractMethod(false);
        lambdaMethodContent.setDirectCast(true);
        String result_ = appendParts(_page, _type, _type, _idCast);
        lambdaCommonContent.setResult(result_);
        return result_;
    }

    private void prMethArr(AnalyzedPageEl _page, String _name) {
        String foundClass_ = StringExpUtil.getPrettyArrayType(_page.getAliasObject());
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, _name, new StringList());
        method = new ClassMethodId(foundClass_, id_);
    }

    private void prAcc(AnalyzedPageEl _page, String _name, StringList _methodTypes, boolean _shift, String _foundClass) {
        lambdaCommonContent.setShiftArgument(_shift);
        StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
        fct_.append(StringExpUtil.TEMPLATE_BEGIN);
        String comp_ = _foundClass;
        if (_shift) {
            fct_.append(comp_);
            fct_.append(StringExpUtil.TEMPLATE_SEP);
        }
        String foundClass_ = StringExpUtil.getPrettyArrayType(_page.getAliasObject());
        StringList params_ = new StringList();
        if (isSingleRange(_page, _methodTypes)) {
            fct_.append(_methodTypes.first());
            params_.add(_methodTypes.first());
            fct_.append(StringExpUtil.TEMPLATE_SEP);
        } else {
            checkNumTypes(_page, _methodTypes);
            String bk_ = tryGetArr(_methodTypes, comp_);
            if (bk_ == null) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setIndexFile(_page);
                un_.setFile(_page.getCurrentFile());
                //argument len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                        StringExpUtil.getQuickComponentBase(_foundClass));
                _page.getLocalizer().addError(un_);
                addErr(un_.getBuiltError());
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            comp_ = bk_;
            feedTypes(_methodTypes, fct_, params_);
        }
        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, _name, params_);
        method = new ClassMethodId(foundClass_, id_);
        if (StringUtil.quickEq(_name, "[]=")) {
            fct_.append(comp_);
            fct_.append(StringExpUtil.TEMPLATE_SEP);
        }
        fct_.append(comp_);
        fct_.append(StringExpUtil.TEMPLATE_END);
        lambdaCommonContent.setResult(fct_.toString());
        setResultClass(new AnaClassArgumentMatching(fct_.toString()));
    }

    private static void feedTypes(StringList _methodTypes, StringBuilder _fct, StringList _params) {
        for (String p : _methodTypes) {
            _fct.append(p);
            _params.add(p);
            _fct.append(StringExpUtil.TEMPLATE_SEP);
        }
    }

    private static String tryGetArr(StringList _methodTypes, String _comp) {
        String bk_ = _comp;
        int len_ = _methodTypes.size();
        for (int i = 0; i < len_; i++) {
            bk_ = StringExpUtil.getQuickComponentType(bk_);
            if (bk_ == null) {
                break;
            }
        }
        return bk_;
    }

    private void checkNumTypes(AnalyzedPageEl _page, StringList _methodTypes) {
        for (String p : _methodTypes) {
            if (!AnaClassArgumentMatching.isNumericInt(p, _page)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setIndexFile(_page);
                un_.setFile(_page.getCurrentFile());
                //arg len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                        p);
                _page.getLocalizer().addError(un_);
                addErr(un_.getBuiltError());
            }
        }
    }

    private String buildCloned(AnalyzedPageEl _page, String _foundClass, boolean _shiftArgument) {
        lambdaCommonContent.setShiftArgument(_shiftArgument);
        StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
        fct_.append(StringExpUtil.TEMPLATE_BEGIN);
        if (_shiftArgument) {
            fct_.append(_foundClass);
            fct_.append(StringExpUtil.TEMPLATE_SEP);
        }
        fct_.append(_foundClass);
        fct_.append(StringExpUtil.TEMPLATE_END);
        lambdaCommonContent.setResult(fct_.toString());
        return fct_.toString();
    }

    private void buildErrNoRefMethod(MethodAccessKind _staticContext, String _name, AnalyzedPageEl _page, StringList _argsClass) {
        FoundErrorInterpret undefined_ = new FoundErrorInterpret();
        undefined_.setFile(_page.getCurrentFile());
        undefined_.setIndexFile(_page);
        //_name len
        undefined_.buildError(_page.getAnalysisMessages().getUndefinedMethod(),
                new MethodId(_staticContext, _name, _argsClass).getSignature(_page.getDisplayedStrings()));
        _page.getLocalizer().addError(undefined_);
        addErr(undefined_.getBuiltError());
    }
    private static boolean isAccess(String _arrAccess) {
        return StringUtil.quickEq(_arrAccess,"[]") || StringUtil.quickEq(_arrAccess,"[]=");
    }
    private static boolean isSingleRange(AnalyzedPageEl _page, StringList _methodTypes) {
        return _methodTypes.size() == 1 && StringUtil.quickEq(_methodTypes.first(),_page.getAliasRange());
    }
    private MethodId tryFormat(String _type, MethodId _id, AnalyzedPageEl _page) {
        int nbParams_ = _id.getParametersTypesLength();
        StringList formatted_ = new StringList();
        for (int i = 0; i < nbParams_; i++) {
            String s_ = _id.getParametersType(i);
            String format_ = AnaInherits.wildCardFormatParam(_type, s_, _page);
            if (format_.isEmpty()) {
                FoundErrorInterpret static_ = new FoundErrorInterpret();
                static_.setFile(_page.getCurrentFile());
                static_.setIndexFile(_page);
                //key word id len
                static_.buildError(_page.getAnalysisMessages().getBadParameTypeForId(),
                        s_);
                _page.getLocalizer().addError(static_);
                addErr(static_.getBuiltError());
                format_ = _page.getAliasObject();
            }
            formatted_.add(format_);
        }
        return MethodId.to(_id.getKind(),formatted_,_id);
    }

    private static boolean matchIdKeyWord(StringList _args, int _len, int _i, String _keyWordId) {
        return _i < _len && StringUtil.quickEq(_args.get(_i).trim(), _keyWordId);
    }

    private static StringList cloneArrayBounds(StringList _bounds) {
        StringList filt_ = new StringList();
        for (String b: _bounds) {
            if (b.startsWith(StringExpUtil.ARR_CLASS)) {
                filt_.add(b);
            }
        }
        return filt_;
    }

    private void addErrArray(StringList _str, AnalyzedPageEl _page) {
        badCast(_page, _page.getAnalysisMessages().getArrayCloneOnly(), _page.getAliasClone(), StringUtil.join(_str, ExportCst.JOIN_TYPES));
    }


    private static boolean koArrayMethod(String _name, AnalyzedPageEl _page) {
        return !StringUtil.quickEq(_name, _page.getAliasClone());
    }

    private void found(String _b) {
        lambdaCommonContent.setFoundFormatted(simpleFormatted(_b));
    }

    private void processAbstract(ClassMethodIdReturn _id, AnalyzedPageEl _page) {
        FoundErrorInterpret abs_ = new FoundErrorInterpret();
        abs_.setIndexFile(_page);
        abs_.setFile(_page.getCurrentFile());
        //method name len
        abs_.buildError(
                _page.getAnalysisMessages().getAbstractMethodRef(),
                _id.getRealClass(),
                _id.getRealId().getSignature(_page.getDisplayedStrings()));
        _page.getLocalizer().addError(abs_);
        addErr(abs_.getBuiltError());
    }

    private void processInstance(StringList _args, int _len, AnalyzedPageEl _page) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordId_ = keyWords_.getKeyWordId();
        checkAccessStatic(_page);
        String clFrom_;
        AnaFormattedRootBlock form_;
        if (!isIntermediateDottedOperation()) {
            int offset_ = offset(_args,0);
            AnaResultPartType result_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_, _args.first().trim(), _page);
            partOffsetsBegin.add(result_);
            clFrom_ = result_.getResult(_page);
            if (clFrom_.startsWith(ARR)) {
                processArray(_args, _len, _page, clFrom_.substring(ARR.length()));
                return;
            }
            form_ = new AnaFormattedRootBlock(_page, clFrom_);
        } else {
            checkArray(_page);
            boolean wc_ = hasWildCard(_page);
            if (match(_args, _len, keyWordId_, 2)) {
                int offset_ = offset(_args,0);
                AnaResultPartType result_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_, _args.first().trim(), _page);
                partOffsetsBegin.add(result_);
                clFrom_ = result_.getResult(_page);
                form_ = new AnaFormattedRootBlock(_page, clFrom_);
            } else {
                String cl_ = _args.first().trim();
                String idClass_ = StringExpUtil.getIdFromAllTypes(cl_);
                StringMap<OwnerResultInfo> ownersMap_ = ownersMap(_page, wc_, idClass_);
                if (ownersMap_.size() != 1) {
                    FoundErrorInterpret static_ = new FoundErrorInterpret();
                    static_.setFile(_page.getCurrentFile());
                    static_.setIndexFile(_page);
                    //key word len
                    static_.buildError(_page.getAnalysisMessages().getNotResolvedOwner(),
                            idClass_
                    );
                    _page.getLocalizer().addError(static_);
                    addErr(static_.getBuiltError());
                    setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                    return;
                }
                OwnerResultInfo info_ = ownersMap_.values().first();
                clFrom_ = buildTypeName(_args.first(), _page, cl_, info_);
                form_ = new AnaFormattedRootBlock(info_.getOwned(), clFrom_);
            }
        }
        boolean wc_ = checkWildCards(clFrom_, _page);
        if (wc_) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        checkPreviousOperand(_args, _len, _page, clFrom_);
        tryGetCtor(_args, _len, _page, clFrom_, form_);

    }

    private StringMap<OwnerResultInfo> ownersMap(AnalyzedPageEl _page, boolean _wc, String _idClass) {
        StringMap<OwnerResultInfo> ownersMap_ = new StringMap<OwnerResultInfo>();
        if (!_wc) {
            for (String o: previousResultClass.getNames()) {
                OwnerListResultInfo owners_ = AnaTypeUtil.getGenericOwners(o, _idClass, _page);
                if (owners_.onlyOneElt()) {
                    ownersMap_.put(o, owners_.firstElt());
                }
            }
        }
        return ownersMap_;
    }

    private void checkArray(AnalyzedPageEl _page) {
        for (String o: previousResultClass.getNames()) {
            if (o.startsWith(ARR)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFile(_page.getCurrentFile());
                call_.setIndexFile(_page);
                //key word len
                call_.buildError(_page.getAnalysisMessages().getIllegalCtorArray(),
                        o);
                _page.getLocalizer().addError(call_);
                addErr(call_.getBuiltError());
            }
        }
    }

    private boolean hasWildCard(AnalyzedPageEl _page) {
        boolean wc_ = false;
        for (String o: previousResultClass.getNames()) {
            if (checkWildCards(o, _page)) {
                wc_ = true;
            }
        }
        return wc_;
    }

    private void tryGetCtor(StringList _args, int _len, AnalyzedPageEl _page, String _clFrom, AnaFormattedRootBlock _form) {
        int vararg_ = -1;
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordId_ = keyWords_.getKeyWordId();
        boolean notint_ = !isIntermediateDottedOperation();
        int incr_;
        if (match(_args, _len, keyWordId_, 2)) {
            incr_ = 3;
        } else {
            incr_ = 2;
        }
        if (_form.getRootBlock() instanceof RecordBlock) {
            processRecord(notint_,incr_, _args, _len, _page, _form);
            return;
        }
        ConstructorId feed_ = null;
        MethodId argsResModif_;
        if (match(_args, _len, keyWordId_, 2)) {
            String cl_ = StringExpUtil.getIdFromAllTypes(_clFrom);
            MethodId argsRes_ = resolveArguments(incr_, _args, _page, new LambdaPartTypeId(className, partOffsets, cl_), false, MethodAccessKind.INSTANCE);
            if (argsRes_ == null) {
                return;
            }
            feed_ = MethodId.to(cl_, argsRes_);
            argsResModif_ = tryFormat(_clFrom, argsRes_, _page);
        } else {
            MethodId argsRes_ = resolveArguments(incr_, _args, _page, new LambdaPartTypeStd(className, partOffsets), false, MethodAccessKind.INSTANCE);
            if (argsRes_ == null) {
                return;
            }
            argsResModif_ = argsRes_;
            vararg_ = vararg(_len, vararg_, argsRes_, incr_);
        }
        StringList methodTypes_ = params(argsResModif_);
        processCtor(notint_, methodTypes_, vararg_, feed_, _clFrom, _page);
    }

    private void checkPreviousOperand(StringList _args, int _len, AnalyzedPageEl _page, String _clFrom) {
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordId_ = keyWords_.getKeyWordId();
        if (isIntermediateDottedOperation() && match(_args, _len, keyWordId_, 2)) {
            StringList innerParts_ = StringExpUtil.getAllPartInnerTypes(_clFrom);
            String param_ = StringUtil.join(innerParts_.left(innerParts_.size() - 2), "");
            StringList bounds_ = new StringList();
            for (String c : previousResultClass.getNames()) {
                bounds_.addAllElts(InvokingOperation.getBounds(c, _page));
            }
            Mapping map_ = new Mapping();
            map_.setArg(new AnaClassArgumentMatching(bounds_));
            map_.setParam(param_);
            StringMap<StringList> maps_ = new StringMap<StringList>();
            getRefConstraints(maps_, _page);
            map_.setMapping(maps_);
            if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
                badCast(_page, _page.getAnalysisMessages().getBadImplicitCast(), StringUtil.join(bounds_, ExportCst.JOIN_TYPES), param_);
            }
        }
    }

    private void checkAccessStatic(AnalyzedPageEl _page) {
        if (isStaticAccess(getParent())) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page);
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    StringUtil.join(previousResultClass.getNames(),ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(un_);
            addErr(un_.getBuiltError());
        }
    }


    private void processRecord(boolean _notint, int _from,StringList _args, int _len, AnalyzedPageEl _page, AnaFormattedRootBlock _form) {
        if (!_notint) {
            lambdaCommonContent.setShiftArgument(true);
        }
        RootBlock h_ = _form.getRootBlock();
        String clFrom_ = _form.getFormatted();
        CustList<ClassField> names_ = new CustList<ClassField>();
        StringList types_ = new StringList();
        if (_notint&&!h_.withoutInstance()) {
            //From analyze
            StringList innerParts_ = StringExpUtil.getAllPartInnerTypes(clFrom_);
            types_.add(StringUtil.join(innerParts_.left(innerParts_.size() - 2), ""));
        }
        int offsetArg_ = offsetComma(_args,_from)-getOffset();
        int next_ = _from;
        for (int i = _from; i < _len; i++) {
            String arg_ = _args.get(i);
            String name_ = arg_.trim();
            if (StringUtil.quickEq(name_,_page.getKeyWords().getKeyWordInterfaces())) {
                offsetArg_ += arg_.length() + 1;
                next_ = i;
                break;
            }
            String fieldName_;
            int e_;
            int lastIndex_ = name_.lastIndexOf('.');
            AnaFormattedRootBlock search_;
            int offsetFirst_ = StringExpUtil.getOffset(arg_);
            int locFirst_ = offsetArg_ + offsetFirst_;
            if (lastIndex_ >= 0) {
                String pre_ = name_.substring(0, lastIndex_);
                String className_ = pre_.trim();
                ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(locFirst_, className_, _page);
                partOffsetsRec.add(resolvedIdType_.getDels());
                String substring_ = name_.substring(lastIndex_ + 1);
                e_ = locFirst_ +lastIndex_+1+ StringExpUtil.getOffset(substring_);
                fieldName_ = substring_.trim();
                search_ = AnaInherits.getFormattedOverridingFullTypeByBases(new AnaFormattedRootBlock(h_), resolvedIdType_.getGeneType());
            } else {
                partOffsetsRec.add(new AnaResultPartType());
                fieldName_ = name_;
                e_ = locFirst_;
                search_ = new AnaFormattedRootBlock(h_);
            }
            ClassField idField_ = idField(_form, types_, fieldName_, e_, search_);

            recordField(_page, clFrom_, names_, name_, idField_, e_);
            offsetArg_ += arg_.length()+1;
            next_ = i;
        }
        Ints offsets_ = new Ints();
        StringList supInts_ = new StringList();
        for (int i = next_ + 1; i < _len; i++) {
            String arg_ = _args.get(i);
            supInts_.add(arg_);
            int offsetFirst_ = StringExpUtil.getOffset(arg_);
            offsets_.add(offsetArg_ + offsetFirst_+_page.getIndex());
            offsetArg_ += arg_.length()+1;
        }
        CustList<AnaFormattedRootBlock> anaFormattedRootBlocks_ = StandardInstancingOperation.getAnaFormattedRootBlocks(_page, h_, supInts_, offsets_, partsInstInitInterfaces);
        getSups().addAllElts(anaFormattedRootBlocks_);
        lambdaMemberNumberContentId = new MemberId();
        recordType = h_.getNumberAll();
        lambdaMemberNumberContentId.setRootNumber(recordType);
        lambdaCommonContent.setFoundFormatted(_form);
        types_.add(clFrom_);
        fieldType = h_;
        lambdaCommonContent.setFileName(h_.getFile().getFileName());
        StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
        fct_.append(StringExpUtil.TEMPLATE_BEGIN);
        fct_.append(StringUtil.join(types_, StringExpUtil.TEMPLATE_SEP));
        fct_.append(StringExpUtil.TEMPLATE_END);
        lambdaCommonContent.setResult(fct_.toString());
        setResultClass(new AnaClassArgumentMatching(fct_.toString()));
    }

    private ClassField idField(AnaFormattedRootBlock _form, StringList _types, String _fieldName, int _e, AnaFormattedRootBlock _search) {
        RootBlock h_ = _form.getRootBlock();
        String clFrom_ = _form.getFormatted();
        ClassField idField_ = null;
        if (_search != null) {
            RootBlock rootBlock_ = _search.getRootBlock();
            for (InfoBlock f: rootBlock_.getFieldsInstBlocks()) {
                String imp_ = f.getImportedClassName();
                String formImp_ = AnaInherits.quickFormat(_search, imp_);
                String par_ = AnaInherits.quickFormat(h_, clFrom_, formImp_);
                int index_ = AnaTypeUtil.getIndex(f, _fieldName);
                if (index_ >= 0) {
                    idField_ = new ClassField(rootBlock_.getFullName(), _fieldName);
                    _types.add(par_);
                    offsets.add(_e);
                    refs.add(index_);
                    namedFields.add(new AnaNamedFieldContent(_fieldName,imp_,rootBlock_.getFullName(),rootBlock_));
                    break;
                }
            }
        }
        return idField_;
    }

    private void recordField(AnalyzedPageEl _page, String _clFrom, CustList<ClassField> _names, String _name, ClassField _idField, int _e) {
        if (_idField == null) {
            offsets.add(_e);
            refs.add(-1);
            namedFields.add(new AnaNamedFieldContent(_name,"","",null));
            String id_ = StringExpUtil.getIdFromAllTypes(_clFrom);
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFile(_page.getCurrentFile());
            call_.setIndexFile(_page);
            //_fromType len
            call_.buildError(_page.getAnalysisMessages().getIllegalCtorAbstract(),
                    id_);
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
        } else if (dupl(_names, _idField)) {
            String id_ = StringExpUtil.getIdFromAllTypes(_clFrom);
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFile(_page.getCurrentFile());
            call_.setIndexFile(_page);
            //_fromType len
            call_.buildError(_page.getAnalysisMessages().getIllegalCtorAbstract(),
                    id_);
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
        } else {
            _names.add(_idField);
        }
    }

    private String buildTypeName(String _arg, AnalyzedPageEl _page, String _cl, OwnerResultInfo _sup) {
        String idClass_ = StringExpUtil.getIdFromAllTypes(_cl);
        int offset_ = className.indexOf('(')+1;
        offset_ += StringExpUtil.getOffset(_arg);
        String inner_ = StringExpUtil.getIdFromAllTypes(_sup.getOwnedName());
        RootBlock root_ = _sup.getOwned();
        FileBlock r_ = _page.getCurrentFile();
        partOffsetsPre.add(PreLinkagePartTypeUtil.processAccessOkRootAnalyze(idClass_,root_,inner_,r_, _page.getIndex()+offset_, _page));
        offset_ += idClass_.length() + 1;
        StringList partsArgs_ = new StringList();
        for (String a: StringExpUtil.getAllTypes(_cl).mid(1)) {
            int loc_ = StringExpUtil.getOffset(a);
            AnaResultPartType result_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_ + loc_, a.trim(), _page);
            String res_ = result_.getResult(_page);
            partOffsetsPre.add(result_);
            partsArgs_.add(res_);
            offset_ += a.length() + 1;
        }
        StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
        return check(root_, _sup.getOwnedName(), partsArgs_, vars_, _page);
    }

    private static int vararg(int _len, int _vararg, MethodId _argsRes, int _i) {
        int vararg_ = _vararg;
        if (_argsRes.isVararg()) {
            vararg_ = _len - _i;
        }
        return vararg_;
    }

    private static StringList params(MethodId _argsRes) {
        StringList params_ = new StringList();
        int nbParams_ = _argsRes.getParametersTypesLength();
        for (int i = 0; i < nbParams_; i++) {
            params_.add(pref(_argsRes.getParametersRef(i),_argsRes.getParametersType(i)));
        }
        return params_;
    }
    private static String pref(BoolVal _ref, String _value) {
        if (_ref == BoolVal.TRUE) {
            return "~"+_value;
        }
        return _value;
    }

    private void initIdCtor(ConstrustorIdVarArg _ctorRes) {
        realId = _ctorRes.getRealId();
        lambdaCommonContent.setFoundFormatted(_ctorRes.getFormattedType());
        function = _ctorRes.getPair();
        setupFct();
        standardType = _ctorRes.getStandardType();
        standardConstructor = _ctorRes.getConstructor();
        lambdaCommonContent.setFileName(_ctorRes.getFileName());
        lambdaMemberNumberContentId = _ctorRes.getMemberId();
    }

    private void processCtor(boolean _notint, StringList _methodTypes, int _vararg, ConstructorId _feed, String _cl, AnalyzedPageEl _page) {
        if (!_notint) {
            lambdaCommonContent.setShiftArgument(true);
        }
        String id_ = StringExpUtil.getIdFromAllTypes(_cl);
        AnaGeneType h_ = _page.getAnaGeneType(id_);
        if (noDefCtor(h_)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFile(_page.getCurrentFile());
            call_.setIndexFile(_page);
            //key word len or _fromType
            call_.buildError(_page.getAnalysisMessages().getIllegalCtorAbstract(),
                    id_);
            _page.getLocalizer().addError(call_);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            addErr(call_.getBuiltError());
            return;
        }
        ConstrustorIdVarArg ctorRes_ = getDeclaredCustConstructorLambda(_vararg, _cl,
                h_,
                _feed, _page, _methodTypes);
        if (ctorRes_ == null) {
            buildLambdaCtorErr(_page,_cl,_methodTypes);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        initIdCtor(ctorRes_);
        ConstructorId fid_ = ctorRes_.getConstId();
        StringList parts_ = new StringList();
        if (_notint&&!h_.withoutInstance()) {
            //From analyze
            StringList innerParts_ = StringExpUtil.getAllPartInnerTypes(_cl);
            parts_.add(StringUtil.join(innerParts_.left(innerParts_.size() - 2), ""));
        }
        appendArgsCtor(fid_, parts_);
        parts_.add(_cl);
        StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
        fct_.append(StringExpUtil.TEMPLATE_BEGIN);
        fct_.append(StringUtil.join(parts_, StringExpUtil.TEMPLATE_SEP));
        fct_.append(StringExpUtil.TEMPLATE_END);
        lambdaCommonContent.setResult(fct_.toString());
        setResultClass(new AnaClassArgumentMatching(fct_.toString()));
    }

    private boolean checkWildCards(String _class, AnalyzedPageEl _page) {
        return StandardInstancingOperation.chWc(this,_class,_page);
    }

    private void buildLambdaCtorErr(AnalyzedPageEl _page, String _clCurName, StringList _args) {
        FoundErrorInterpret undefined_ = new FoundErrorInterpret();
        undefined_.setFile(_page.getCurrentFile());
        undefined_.setIndexFile(_page);
        //key word len
        undefined_.buildError(_page.getAnalysisMessages().getUndefinedCtor(),
                new ConstructorId(_clCurName, _args, false).getSignature(_page.getDisplayedStrings()));
        _page.getLocalizer().addError(undefined_);
        addErr(undefined_.getBuiltError());
    }
    private static boolean noDefCtor(AnaGeneType _h) {
        return ContextUtil.isAbstractType(_h) || _h instanceof RecordBlock;
    }

    private void processField(MethodOperation _m, StringList _args, int _len, AnalyzedPageEl _page) {
        if (_len < 3) {
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFile(_page.getCurrentFile());
            badCall_.setIndexFile(_page);
            //key word len
            badCall_.buildError(_page.getAnalysisMessages().getSplitComa(),
                    Long.toString(3),
                    Long.toString(_len)
            );
            _page.getLocalizer().addError(badCall_);
            errPart(badCall_, _page, 0, _page.getKeyWords().getKeyWordLambda().length());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (!isIntermediateDottedOperation()) {
            notIntermediatedField(_args, _len, _page);
            return;
        }
        if (isStaticAccess(_m)) {
            staticField(_args, _len, _page);
            return;
        }
        intermediatedField(_args, _len, _page);
    }

    private void staticField(StringList _args, int _len, AnalyzedPageEl _page) {
        StringList str_;
        String fieldName_ = _args.get(2).trim();
        int sum_ = offsetComma(_args, 2);
        str_ = resolveCorrectTypes(false, _args, _page);
        int i_ = 3;
        boolean aff_ = i_ < _len;
        AnaClassArgumentMatching fromCl_ = new AnaClassArgumentMatching(str_);
        sum_ += StringExpUtil.getOffset(_args.get(2));
        ScopeFilter scope_ = new ScopeFilter(null, true, true, false, _page.getGlobalClass());
        FieldResult r_ = resolveDeclaredCustField(true, fromCl_, fieldName_, false, aff_, _page, scope_);
        if (r_.getStatus() == SearchingMemberStatus.ZERO) {
            buildErrLambda(sum_,fromCl_,fieldName_, _page);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        updateFieldInfos(aff_, r_);
        StringList params_ = buildParamsField(_args, _page, i_, aff_, r_);
        String fct_ = formatFieldReturn(true, params_, r_, false, _page);
        lambdaCommonContent.setResult(fct_);
        setResultClass(new AnaClassArgumentMatching(fct_));
    }

    private void intermediatedField(StringList _args, int _len, AnalyzedPageEl _page) {
        String fieldName_ = _args.get(2).trim();
        int sum_ = offsetComma(_args, 2);
        StringList str_;
        String resolved_ = resolveSingleTypeExact(_args, _page);
        str_ = InvokingOperation.getBounds(resolved_, _page);
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordThis_ = keyWords_.getKeyWordThis();
        String keyWordNull_ = keyWords_.getKeyWordNull();
        String keyWordParent_ = keyWords_.getKeyWordParent();
        String keyWordInstanceof_ = keyWords_.getKeyWordInstanceof();
        StringList bounds_ = new StringList();
        for (String c: previousResultClass.getNames()) {
            bounds_.addAllElts(InvokingOperation.getBounds(c, _page));
        }
        if (StringUtil.quickEq(fieldName_, keyWordThis_)) {
            lambdaFieldContent.setFinalField(true);
            lambdaFieldContent.setToStrField(true);
            lambdaCommonContent.setReturnFieldType(_page.getAliasString());
            lambdaCommonContent.setFoundFormatted(simpleFormatted(resolved_));
            String fct_ = buildVirtualField(_page,false);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        if (StringUtil.quickEq(fieldName_, keyWordNull_)) {
            lambdaFieldContent.setFinalField(true);
            lambdaFieldContent.setRdCodField(true);
            lambdaCommonContent.setReturnFieldType(_page.getAliasPrimLong());
            lambdaCommonContent.setFoundFormatted(simpleFormatted(resolved_));
            String fct_ = buildVirtualField(_page,false);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        if (StringUtil.quickEq(fieldName_, keyWordInstanceof_)) {
            lambdaFieldContent.setFinalField(true);
            lambdaFieldContent.setInstanceField(true);
            lambdaCommonContent.setReturnFieldType(_page.getAliasPrimBoolean());
            lambdaCommonContent.setFoundFormatted(simpleFormatted(resolved_));
            String fct_ = buildVirtualField(_page,false);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        if (StringUtil.quickEq(fieldName_, keyWordParent_)) {
            Mapping map_ = buildMapping(_page, str_, bounds_);
            if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
                badCast(_page, str_,bounds_);
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            String res_ = getParentType(resolved_, _page);
            lambdaFieldContent.setFinalField(true);
            lambdaCommonContent.setReturnFieldType(res_);
            lambdaCommonContent.setFoundFormatted(simpleFormatted(resolved_));
            String fct_ = buildParentField(_page, resolved_, res_, false);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        LambdaFieldChoice ch_ = new LambdaFieldChoice(sum_, fieldName_);
        ch_.incr(_args, _len, _page);
        Mapping map_ = buildMapping(_page,str_,bounds_);
        if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
            badCast(_page, str_,bounds_);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        tryGetField(_args, _len, _page, str_, ch_);
    }

    private void notIntermediatedField(StringList _args, int _len, AnalyzedPageEl _page) {
        int sum_ = offsetComma(_args, 2);
        String fieldName_ = _args.get(2).trim();
        StringList str_;
        String resolved_ = resolveSingleTypeExact(_args, _page);
        str_ = InvokingOperation.getBounds(resolved_, _page);
        RootBlock root_ = _page.getAnaClassBody(resolved_);
        if (root_ != null) {
            lambdaMemberNumberContentId = new MemberId();
            lambdaMemberNumberContentId.setRootNumber(root_.getNumberAll());
        }
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordThis_ = keyWords_.getKeyWordThis();
        String keyWordNull_ = keyWords_.getKeyWordNull();
        String keyWordParent_ = keyWords_.getKeyWordParent();
        String keyWordInstanceof_ = keyWords_.getKeyWordInstanceof();
        if (StringUtil.quickEq(fieldName_, keyWordThis_)) {
            lambdaFieldContent.setFinalField(true);
            lambdaFieldContent.setToStrField(true);
            lambdaCommonContent.setReturnFieldType(_page.getAliasString());
            lambdaCommonContent.setFoundFormatted(simpleFormatted(resolved_));
            String fct_ = buildVirtualField(_page, true);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        if (StringUtil.quickEq(fieldName_, keyWordNull_)) {
            lambdaFieldContent.setFinalField(true);
            lambdaFieldContent.setRdCodField(true);
            lambdaCommonContent.setReturnFieldType(_page.getAliasPrimLong());
            lambdaCommonContent.setFoundFormatted(simpleFormatted(resolved_));
            String fct_ = buildVirtualField(_page, true);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        if (StringUtil.quickEq(fieldName_, keyWordInstanceof_)) {
            lambdaFieldContent.setFinalField(true);
            lambdaFieldContent.setInstanceField(true);
            lambdaCommonContent.setReturnFieldType(_page.getAliasPrimBoolean());
            lambdaCommonContent.setFoundFormatted(simpleFormatted(resolved_));
            String fct_ = buildVirtualField(_page, true);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        if (StringUtil.quickEq(fieldName_, keyWordParent_)) {
            String res_ = getParentType(resolved_, _page);
            lambdaFieldContent.setFinalField(true);
            lambdaCommonContent.setReturnFieldType(res_);
            lambdaCommonContent.setFoundFormatted(simpleFormatted(resolved_));
            String fct_ = buildParentField(_page, resolved_, res_, true);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        LambdaFieldChoice ch_ = new LambdaFieldChoice(sum_, fieldName_);
        ch_.incr(_args, _len, _page);
        tryGetField(_args, _len, _page, str_, ch_);
    }

    private static Mapping buildMapping(AnalyzedPageEl _page, StringList _str, StringList _bounds) {
        Mapping map_ = new Mapping();
        map_.setArg(new AnaClassArgumentMatching(_bounds));
        map_.setParam(new AnaClassArgumentMatching(_str));
        StringMap<StringList> maps_ = new StringMap<StringList>();
        getRefConstraints(maps_, _page);
        map_.setMapping(maps_);
        return map_;
    }

    private void tryGetField(StringList _args, int _len, AnalyzedPageEl _page, StringList _str, LambdaFieldChoice _ch) {
        int i_ = _ch.getIndex();
        boolean accessBase_ = _ch.isAccessBase();
        boolean accessSuper_ = _ch.isAccessSuper();
        int sum_ = _ch.getSum();
        String fieldName_ = _ch.getFieldName();
        boolean aff_ = i_ < _len;
        AnaClassArgumentMatching fromCl_ = new AnaClassArgumentMatching(_str);
        ScopeFilter scope_ = new ScopeFilter(null, accessBase_, accessSuper_, false, _page.getGlobalClass());
        FieldResult r_ = resolveDeclaredCustField(false, fromCl_, fieldName_, false, aff_, _page, scope_);
        if (r_.getStatus() == SearchingMemberStatus.ZERO) {
            buildErrLambda(sum_,fromCl_,fieldName_,_page);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        updateFieldInfos(aff_, r_);
        boolean static_ = r_.getContent().isStaticField();
        lambdaCommonContent.setShiftArgument(!isIntermediateDottedOperation()&&!static_);
        StringList params_ = buildParamsField(_args, _page, i_, aff_, r_);
        String fct_ = formatFieldReturn(static_, params_, r_, lambdaCommonContent.isShiftArgument(), _page);
        lambdaCommonContent.setResult(fct_);
        setResultClass(new AnaClassArgumentMatching(fct_));
    }

    private static boolean isStaticAccess(MethodOperation _m) {
        if (!(_m instanceof DotOperation)) {
            return false;
        }
        OperationNode o_ = _m.getFirstChild();
        boolean stAcc_ = o_ instanceof StaticAccessOperation;
        boolean stAccCall_ = o_ instanceof StaticCallAccessOperation;
        return stAcc_ || stAccCall_;
    }
    private void updateFieldInfos(boolean _aff, FieldResult _r) {
        lambdaCommonContent.setFileName(_r.getFileName());
        lambdaMemberNumberContentId = _r.getMemberId();
        valueOffset = _r.getValOffset();
        lambdaFieldContent.setAffField(_aff);
        fieldId = _r.getContent().getClassField();
        fieldType = _r.getFieldType();
        lambdaFieldContent.setStaticField(_r.getContent().isStaticField());
        lambdaFieldContent.setFinalField(_r.getContent().isFinalField());
        String out_ = _r.getType();
        lambdaCommonContent.setReturnFieldType(out_);
        lambdaCommonContent.setFoundFormatted(_r.getFormattedType());
        lambdaCommonContent.setAncestor(_r.getContent().getAnc());
    }

    private StringList buildParamsField(StringList _args, AnalyzedPageEl _page, int _i, boolean _aff, FieldResult _r) {
        String out_ = _r.getType();
        StringList params_ = new StringList();
        if (_aff) {
            checkFinal(_page, _r.getContent().isFinalField(), _r.getContent().getClassField());
            int offset_ = offset(_args, _i);
            String type_ = _args.get(_i).trim();
            AnaResultPartType result_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_, type_, _page);
            String arg_ = result_.getResult(_page);
            partOffsetsBegin.add(result_);
            StringMap<StringList> map_ = new StringMap<StringList>();
            getRefConstraints(map_, _page);
            checkTypes(_page, out_, arg_, map_);
            params_.add(arg_);
            //setter
        }
        return params_;
    }

    private void checkTypes(AnalyzedPageEl _page, String _out, String _arg, StringMap<StringList> _map) {
        Mapping mapping_ = new Mapping();
        mapping_.setArg(_arg);
        mapping_.setParam(_out);
        mapping_.setMapping(_map);
        if (!AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
            badCast(_page, _page.getAnalysisMessages().getBadImplicitCast(), _arg, _out);
        }
    }

    private int offset(StringList _args, int _i) {
        int offset_ = offsetComma(_args, _i);
        offset_ += StringExpUtil.getOffset(_args.get(_i));
        return offset_;
    }

    private int offsetComma(StringList _args, int _i) {
        int offset_ = className.indexOf('(') + 1;
        for (int i = 0; i < _i; i++) {
            offset_ += _args.get(i).length();
            offset_++;
        }
        return offset_;
    }

    private String buildParentField(AnalyzedPageEl _page, String _resolved, String _res, boolean _shiftArgument) {
        lambdaCommonContent.setShiftArgument(_shiftArgument);
        StringList params_ = new StringList();
        String result_ = formatFieldReturn(false, params_, _res, _shiftArgument, _page, _resolved);
        lambdaCommonContent.setResult(result_);
        return result_;
    }

    private String buildVirtualField(AnalyzedPageEl _page, boolean _shiftArgument) {
        lambdaCommonContent.setShiftArgument(_shiftArgument);
        String result_ = buildVirtualFieldTech(_page, _shiftArgument);
        lambdaCommonContent.setResult(result_);
        return result_;
    }

    private String buildVirtualFieldTech(AnalyzedPageEl _page, boolean _shiftArgument) {
        if (_shiftArgument) {
            return StringUtil.concat(_page.getAliasFct(), "<", _page.getAliasObject(), ",", lambdaCommonContent.getReturnFieldType(), ">");
        }
        return StringUtil.concat(_page.getAliasFct(), "<", lambdaCommonContent.getReturnFieldType(), ">");
    }

    private static AnaFormattedRootBlock simpleFormatted(String _resolved) {
        return new AnaFormattedRootBlock((RootBlock) null, _resolved);
    }

    private void buildErrLambda(int _offset,AnaClassArgumentMatching _class, String _name, AnalyzedPageEl _page) {
        FoundErrorInterpret access_ = new FoundErrorInterpret();
        access_.setFile(_page.getCurrentFile());
        access_.setIndexFile(_page,_offset);
        //_name len
        access_.buildError(_page.getAnalysisMessages().getUndefinedAccessibleField(),
                _name,
                StringUtil.join(_class.getNames(),ExportCst.JOIN_TYPES));
        _page.getLocalizer().addError(access_);
        addBuiltErr(access_,_page,_offset,_name);
    }

    private void addBuiltErr(FoundErrorInterpret _err, AnalyzedPageEl _page,int _i, String _name) {
        errPart(_err, _page, _i, Math.max(1, _name.length()));
    }

    private static String getParentType(String _converted, AnalyzedPageEl _page) {
        if (_converted.startsWith(AnaTemplates.ARR_BEG_STRING)) {
            return _page.getAliasObject();
        }
        StringList rs_ = ParentInstanceOperation.getParentTypeList(new StringList(_converted), _page);
        return rs_.first();
    }
    private String resolveSingleTypeExact(StringList _args, AnalyzedPageEl _page) {
        int offset_ = offset(_args, 0);
        AnaResultPartType result_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_, _args.first().trim(), _page);
        String type_ = result_.getResult(_page);
        partOffsetsBegin.add(result_);
        return type_;
    }

    private void checkFinal(AnalyzedPageEl _page, boolean _finalField, ClassField _classField) {
        if (_finalField) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page);
            //field name len
            un_.buildError(_page.getAnalysisMessages().getFinalField(),
                    _classField.getFieldName());
            _page.getLocalizer().addError(un_);
            addErr(un_.getBuiltError());
        }
    }

    private static void getRefConstraints(StringMap<StringList> _map, AnalyzedPageEl _page) {
        _map.putAllMap(_page.getCurrentConstraints().getCurrentConstraints());
    }

    private void processOperator(StringList _args, int _len, AnalyzedPageEl _page) {
        if (isIntermediateDottedOperation() && !previousResultClass.getNames().onlyOneElt()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page);
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    StringUtil.join(previousResultClass.getNames(),ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(un_);
            errPart(un_, _page, 0, _page.getKeyWords().getKeyWordLambda().length());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        int sum_ = className.indexOf('(')+1;
        sum_ += _args.first().length();
        String operator_ = _args.get(1).trim();
        int i_ = 2;
        String from_ = "";
        boolean displayErr_ = false;
        if (!StringExpUtil.isOper(operator_)) {
            int offset_ = offsetOperator(_args, sum_, operator_);
            sum_ += 1+_args.get(1).length();
            from_ = resolveCorrectTypeAccessible(true, operator_, _page, offset_,partOffsetsBegin);
            if (_len > i_) {
                operator_ = _args.get(i_).trim();
                sum_ += StringExpUtil.getOffset(_args.get(i_));
                displayErr_ = true;
            }
            i_++;
        } else {
            sum_ += StringExpUtil.getOffset(_args.get(1));
        }
        if (!StringExpUtil.isOper(operator_)) {
            unexpectedSymbol(_page, sum_, operator_, displayErr_);
            return;
        }
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordId_ = keyWords_.getKeyWordId();
        int vararg_ = -1;
        MethodId argsRes_;
        int j_ = i_;
        MethodAccessKind staticFlag_ = MethodAccessKind.STATIC;
        if (match(_args, _len, keyWordId_, j_)) {
            i_++;
            MethodAccessId idUpdate_ = new MethodAccessId(i_);
            String keyWordStatic_ = _page.getKeyWords().getKeyWordStatic();
            String keyWordStaticCall_ = _page.getKeyWords().getKeyWordStaticCall();
            idUpdate_.setupInfosId(i_,_args,keyWordStatic_,keyWordStaticCall_);
            boolean retRef_ = idUpdate_.isRetRef();
            staticFlag_ = staticFlag(i_, idUpdate_);
            i_ = idUpdate_.getIndex();
            argsRes_ = resolveArguments(i_, _args, _page, new LambdaPartTypeId(className, partOffsets, from_), retRef_, staticFlag_);
        } else {
            argsRes_ = resolveArguments(i_, _args, _page, new LambdaPartTypeStd(className, partOffsets), false, MethodAccessKind.INSTANCE);
        }
        if (argsRes_ == null) {
            return;
        }
        checkAccessStatic(_page);
        StringList methodTypes_ = new StringList();
        int deltaIndex_;
        MethodId const_;
        if (isIntermediateDottedOperation()) {
            methodTypes_.add(previousResultClass.getName());
            const_ = argsRes_.prepend(operator_, previousResultClass.getName(), BoolVal.FALSE);
            deltaIndex_ = 1;
        } else {
            const_ = MethodId.to(staticFlag_, operator_, argsRes_);
            deltaIndex_ = 0;
        }
        ClassMethodId feed_;
        MethodId argsResModif_;
        if (match(_args, _len, keyWordId_, j_)) {
            feed_ = new ClassMethodId(from_, const_);
            argsResModif_ = tryFormat(from_, argsRes_, _page);
        } else {
            feed_ = null;
            argsResModif_ = argsRes_;
            vararg(_len+deltaIndex_, vararg_, argsRes_, i_);
        }
        methodTypes_.addAllElts(params(argsResModif_));
        tryFindOperator(_page, methodTypes_, sum_, operator_, from_, vararg_, feed_);
    }

    private MethodAccessKind staticFlag(int _i, MethodAccessId _idUpdate) {
        MethodAccessKind staticFlag_ = MethodId.getKind(MethodAccessKind.STATIC_CALL, _idUpdate.getKind());
        int k_ = _idUpdate.getIndex();
        if (k_ == _i) {
            staticFlag_ = MethodAccessKind.STATIC;
        }
        return staticFlag_;
    }

    private int offsetOperator(StringList _args, int _sum, String _operator) {
        int offset_ = _sum + 1;
        offset_ += StringExpUtil.getOffset(_args.get(1));
        if (_operator.isEmpty()) {
            offset_--;
        }
        return offset_;
    }

    private void tryFindOperator(AnalyzedPageEl _page, StringList _methodTypes, int _sum, String _operator, String _from, int _vararg, ClassMethodId _feed) {
        ClassMethodIdReturn id_ = getOperator(_from, _methodTypes, _operator, _vararg, _feed, _page);
        if (id_ == null) {
            FoundErrorInterpret undefined_ = new FoundErrorInterpret();
            undefined_.setFile(_page.getCurrentFile());
            undefined_.setIndexFile(_page);
            //_name len
            undefined_.buildError(_page.getAnalysisMessages().getUndefinedMethod(),
                    new MethodId(MethodAccessKind.STATIC, "", _methodTypes).getSignature(_page.getDisplayedStrings()));
            _page.getLocalizer().addError(undefined_);
            int k_ = _sum +1;
            partOffsetsErrMiddle = new InfoErrorDto(undefined_.getBuiltError(), _page,k_,Math.max(1, _operator.length()));
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        initIdMethod(id_);
        String fct_ = formatReturnOperator(isIntermediateDottedOperation(), id_, _page);
        setResultClass(new AnaClassArgumentMatching(fct_));
    }

    private void unexpectedSymbol(AnalyzedPageEl _page, int _sum, String _operator, boolean _displayErr) {
        FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
        badMeth_.setFile(_page.getCurrentFile());
        badMeth_.setIndexFile(_page);
        //key word len
        badMeth_.buildError(_page.getAnalysisMessages().getBadOperatorName(),
                _operator);
        _page.getLocalizer().addError(badMeth_);
        int j_ = _sum;
        if (!_operator.isEmpty()&& _displayErr) {
            j_++;
        }
        int lenErr_= Math.max(1, _operator.length());
        if (!_displayErr) {
            lenErr_ = 1;
        }
        errPart(badMeth_, _page, j_, lenErr_);
        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
    }

    private static boolean match(StringList _args, int _len, String _keyWordId, int _j) {
        return _len > _j && StringUtil.quickEq(_args.get(_j).trim(), _keyWordId);
    }

    private void initId(ClassMethodIdReturn _id) {
        standardMethod = _id.getStandardMethod();
        lambdaMemberNumberContentId = _id.getMemberId();
        function = _id.getPair();
        String foundClass_ = _id.getRealClass();
        foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
        MethodId idCt_ = _id.getRealId();
        method = new ClassMethodId(foundClass_, idCt_);
        lambdaCommonContent.setReturnFieldType(_id.getOriginalReturnType());
        lambdaCommonContent.setFileName(_id.getFileName());
        lambdaCommonContent.setFoundFormatted(_id.getFormattedType());
        lambdaCommonContent.setAncestor(_id.getAncestor());
        lambdaMethodContent.setAbstractMethod(_id.isAbstractMethod());
    }

    private ClassMethodIdReturn getOperator(String _from, StringList _methodTypes, String _operator, int _vararg, ClassMethodId _feed, AnalyzedPageEl _page) {
        if (!_from.isEmpty()) {
            if (_feed == null) {
                return getCustResultLambda(fetchVarargOnly(-1, null), getDeclaredCustMethodByType(MethodAccessKind.STATIC_CALL, new StringList(_from), _operator, false, _page, new ScopeFilter(null, true, false, false, _page.getGlobalClass()), new FormattedFilter()), _operator, _page, _methodTypes);
            }
            ClassMethodIdAncestor uniqueId_ = new ClassMethodIdAncestor(staticAccess,_feed, 0);
            return getCustResultLambda(fetchVarargOnly(-1, uniqueId_), getDeclaredCustMethodByType(MethodAccessKind.STATIC_CALL, new StringList(_from), _operator, false, _page, new ScopeFilter(uniqueId_, true, false, false, _page.getGlobalClass()), new FormattedFilter()), _operator, _page, _methodTypes);
        }
        return getOperatorLambda(_feed, _vararg, _operator, _page, _methodTypes);
    }

    private void processArray(StringList _args, int _len,
                              AnalyzedPageEl _page, String _cl) {
        int i_ = 2;
        StringList parts_ = new StringList();
        boolean err_ = false;
        for (int i = i_; i < _len; i++) {
            String arg_ = StringExpUtil.removeDottedSpaces(_args.get(i));
            parts_.add(arg_);
            if (!AnaClassArgumentMatching.isNumericInt(arg_,_page)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setIndexFile(_page);
                un_.setFile(_page.getCurrentFile());
                //arg_ len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                        arg_);
                _page.getLocalizer().addError(un_);
                addErr(un_.getBuiltError());
                err_ = true;
            }
        }
        if (parts_.isEmpty()) {
            badCast(_page, _page.getAnalysisMessages().getSplitComa(), Long.toString(3), Long.toString(_len));
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        if (err_) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        String out_ = StringExpUtil.getPrettyArrayType(_cl, parts_.size());
        lambdaCommonContent.setFoundFormatted(simpleFormatted(_cl));
        realId = new ConstructorId(out_, parts_, true);
        parts_.add(out_);
        StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
        fct_.append(StringExpUtil.TEMPLATE_BEGIN);
        fct_.append(StringUtil.join(parts_, StringExpUtil.TEMPLATE_SEP));
        fct_.append(StringExpUtil.TEMPLATE_END);
        lambdaCommonContent.setResult(fct_.toString());
        setResultClass(new AnaClassArgumentMatching(fct_.toString()));
    }

    private MethodId resolveArguments(int _from, StringList _params, AnalyzedPageEl _page, AbsLambdaPartTypeImpl _abs, boolean _retRef, MethodAccessKind _static) {
        MethodId methodId_ = _abs.resolveArguments(_retRef, _from, _static, _params, _page);
        errorVararg(_page, _abs, methodId_);
        return methodId_;
    }

    private void errorVararg(AnalyzedPageEl _page, AbsLambdaPartTypeImpl _ana, MethodId _id) {
        if (_id == null) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            partOffsetsErrEnd = _ana.getPartOffsetsErrEnd();
        }
    }

    private StringList resolveCorrectTypes(boolean _exact, StringList _args, AnalyzedPageEl _page) {
        int offset_ = offset(_args, 0);
        String type_ = resolveCorrectTypeAccessible(_exact, _args.first().trim(), _page, offset_,partOffsetsBegin);
        return InvokingOperation.getBounds(type_, _page);
    }

    private String resolveCorrectTypeAccessible(boolean _exact, String _type, AnalyzedPageEl _page, int _offset, CustList<AnaResultPartType> _dest) {
        if (_exact) {
            AnaResultPartType result_ = ResolvingTypes.resolveCorrectTypeAccessible(_offset, _type, _page);
            _dest.add(result_);
            String res_ = result_.getResult(_page);
            staticAccess = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(res_));
            return res_;
        }
        ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(_offset, _type, _page);
        _dest.add(resolvedIdType_.getDels());
        staticAccess = resolvedIdType_.getGeneType();
        return resolvedIdType_.getFullName();
    }

    private StringList resolveCorrectTypesExact(StringList _args, AnalyzedPageEl _page) {
        String type_ = resolveSingleTypeExact(_args, _page);
        return InvokingOperation.getBounds(type_, _page);
    }

    private String formatReturnPrevious(AnalyzedPageEl _page, ClassMethodIdReturn _id) {
        return formatReturn(_page, _id, false);
    }

    private String formatReturn(AnalyzedPageEl _page, ClassMethodIdReturn _id) {
        return formatReturn(_page, _id, !_id.getRealId().isStaticMethod());
    }

    private String formatReturn(AnalyzedPageEl _page, ClassMethodIdReturn _id, boolean _shift) {
        lambdaCommonContent.setShiftArgument(_shift);
        StringList paramsReturn_ = beginReturn(_id.getId().getClassName(), _shift);
        String retType_ = _id.getReturnType();
        MethodId constraints_ = _id.getId().getConstraints();
        IdentifiableUtil.appendLeftPart(0, paramsReturn_, constraints_);

        appendRightPart(_id.getPair().getFunction(),paramsReturn_, _page, _id.getRealClass());
        appRe(retType_, constraints_, paramsReturn_);
        String fctBase_ = _page.getAliasFct();
        String result_ = StringUtil.concat(fctBase_, StringExpUtil.TEMPLATE_BEGIN, StringUtil.join(paramsReturn_, StringExpUtil.TEMPLATE_SEP), StringExpUtil.TEMPLATE_END);
        lambdaCommonContent.setResult(result_);
        return result_;
    }

    private static StringList beginReturn(String _foundClass, boolean _staticMeth) {
        StringList paramsReturn_ = new StringList();
        if (_staticMeth) {
            paramsReturn_.add(_foundClass);
        }
        return paramsReturn_;
    }

    static String appendParts(AnalyzedPageEl _page, String _returnType, String _realClass, MethodId _constraints) {
        StringList paramsReturn_ = beginReturn("", false);
        return appendParts(_page, _returnType, _realClass, _constraints, paramsReturn_, 0);
    }

    private static String appendParts(AnalyzedPageEl _page, String _returnType, String _realClass, MethodId _constraints, StringList _paramsReturn, int _start) {
        IdentifiableUtil.appendLeftPart(_start, _paramsReturn, _constraints);
        appendRightPart(null,_paramsReturn, _page, _realClass);
        appRe(_returnType, _constraints, _paramsReturn);
        String fctBase_ = _page.getAliasFct();
        return StringUtil.concat(fctBase_, StringExpUtil.TEMPLATE_BEGIN, StringUtil.join(_paramsReturn, StringExpUtil.TEMPLATE_SEP), StringExpUtil.TEMPLATE_END);
    }

    private static void appRe(String _returnType, MethodId _constraints, StringList _ret) {
        if (_constraints.isRetRef()) {
            _ret.add("~"+ _returnType);
        } else {
            _ret.add(_returnType);
        }
    }

    private static void appendRightPart(NamedFunctionBlock _fct, StringList _paramsReturn, AnalyzedPageEl _page, String _realClass) {
        if (_fct instanceof NamedCalledFunctionBlock && ((NamedCalledFunctionBlock)_fct).getKind() == MethodKind.SET_INDEX) {
            String importedReturnType_ = ((NamedCalledFunctionBlock)_fct).getReturnTypeGet();
            importedReturnType_ = AnaInherits.wildCardFormatReturn(_realClass, importedReturnType_, _page);
            _paramsReturn.add(importedReturnType_);
        }
    }

    private String formatReturnOperator(boolean _op, ClassMethodIdReturn _id, AnalyzedPageEl _page) {
        lambdaCommonContent.setShiftArgument(_op);
        String returnType_ = _id.getReturnType();
        StringList paramsReturn_ = new StringList();
        MethodId id_ = _id.getId().getConstraints();
        int start_;
        if (_op) {
            start_ = 1;
        } else {
            start_ = 0;
        }
        String result_ = appendParts(_page, returnType_, _id.getRealClass(), id_, paramsReturn_, start_);
        lambdaCommonContent.setResult(result_);
        return result_;
    }
    private static String formatFieldReturn(boolean _static, StringList _params, FieldResult _field, boolean _demand, AnalyzedPageEl _page) {
        return formatFieldReturn(_static,_params,_field.getType(),_demand,_page,_field.getDeclaringClass());
    }
    private static String formatFieldReturn(boolean _static, StringList _params, String _returnType, boolean _demand, AnalyzedPageEl _page, String _foundClass) {
        String fctBase_ = _page.getAliasFct();
        StringList paramsReturn_ = beginReturn(_foundClass, !_static && _demand);
        paramsReturn_.addAllElts(_params);
        paramsReturn_.add(_returnType);
        return StringUtil.concat(fctBase_, StringExpUtil.TEMPLATE_BEGIN, StringUtil.join(paramsReturn_, StringExpUtil.TEMPLATE_SEP), StringExpUtil.TEMPLATE_END);
    }

    @Override
    public void setIntermediateDotted() {
        lambdaCommonContent.setIntermediate(true);
    }
    @Override
    public boolean isIntermediateDottedOperation() {
        return lambdaCommonContent.isIntermediate();
    }

    @Override
    public void setPreviousResultClass(AnaClassArgumentMatching _previousResultClass, MethodAccessKind _staticAccess) {
        previousResultClass = _previousResultClass;
    }

    private void setupFct() {
        NamedFunctionBlock fct_ = null;
        if (function != null) {
            fct_ = function.getFunction();
        }
        if (fct_ != null) {
            fct_.setUsedRefMethod(true);
        }
    }
    public AnaTypeFct getFunction() {
        return function;
    }

    public ClassMethodId getMethod() {
        return method;
    }

    public AnaLambdaMethodContent getLambdaMethodContent() {
        return lambdaMethodContent;
    }

    public ConstructorId getRealId() {
        return realId;
    }

    public RootBlock getFieldType() {
        return fieldType;
    }

    public ClassField getFieldId() {
        return fieldId;
    }

    public AnaLambdaFieldContent getLambdaFieldContent() {
        return lambdaFieldContent;
    }

    public CustList<AnaResultPartType> getPartOffsets() {
        return partOffsets;
    }

    public CustList<AnaResultPartType> getPartOffsetsRec() {
        return partOffsetsRec;
    }

    public CustList<AnaResultPartType> getPartOffsetsPre() {
        return partOffsetsPre;
    }

    public CustList<AnaResultPartType> getPartOffsetsBegin() {
        return partOffsetsBegin;
    }

    public InfoErrorDto getPartOffsetsErrMiddle() {
        return partOffsetsErrMiddle;
    }

    public InfoErrorDto getPartOffsetsErrEnd() {
        return partOffsetsErrEnd;
    }

    public int getValueOffset() {
        return valueOffset;
    }

    public MemberId getLambdaMemberNumberContentId() {
        return lambdaMemberNumberContentId;
    }

    public AnaLambdaCommonContent getLambdaCommonContent() {
        return lambdaCommonContent;
    }

    public StandardMethod getStandardMethod() {
        return standardMethod;
    }

    public StandardType getStandardType() {
        return standardType;
    }

    public StandardConstructor getStandardConstructor() {
        return standardConstructor;
    }

    public int getRecordType() {
        return recordType;
    }

    public Ints getOffsets() {
        return offsets;
    }

    public Ints getRefs() {
        return refs;
    }

    public CustList<AnaNamedFieldContent> getNamedFields() {
        return namedFields;
    }

    public CustList<AnaFormattedRootBlock> getSups() {
        return sups;
    }

    public CustList<AnaResultPartType> getPartsInstInitInterfaces() {
        return partsInstInitInterfaces;
    }
}
