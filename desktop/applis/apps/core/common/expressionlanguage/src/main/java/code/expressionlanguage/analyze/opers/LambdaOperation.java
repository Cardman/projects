package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.InfoErrorDto;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.*;
import code.expressionlanguage.analyze.util.*;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.IdentifiableUtil;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.*;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.fwd.opers.AnaLambdaCommonContent;
import code.expressionlanguage.fwd.opers.AnaLambdaFieldContent;
import code.expressionlanguage.fwd.opers.AnaLambdaMethodContent;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.fwd.opers.AnaNamedFieldContent;
import code.expressionlanguage.linkage.ExportCst;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.util.*;
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
    private final CustList<AnaNamedFieldContent> namedFields = new CustList<AnaNamedFieldContent>();

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
        if (len_ < 2) {
            MethodOperation parent_ = getParent();
            if (parent_ instanceof DotOperation && getIndexChild() > 0 && !(parent_.getFirstChild() instanceof StaticAccessOperation)) {
                KeyWords keyWords_ = _page.getKeyWords();
                String new_ = keyWords_.getKeyWordNew();
                OperationNode o_ = parent_.getFirstChild();
                ParentInferring par_ = ParentInferring.getParentInferring(parent_);
                OperationNode m_ = par_.getOperation();
                int nbParentsInfer_ = par_.getNbParentsInfer();
                String typeAff_;
                AbsBk cur_ = _page.getCurrentBlock();
                if (m_ == null &&cur_ instanceof ReturnMethod) {
                    typeAff_ = InvokingOperation.tryGetRetType(_page);
                } else {
                    typeAff_ = InvokingOperation.tryGetTypeAff(m_, 1);
                }
                String keyWordVar_ = _page.getKeyWords().getKeyWordVar();
                String foundType_ = EMPTY_STRING;
                if (!InvokingOperation.isUndefined(typeAff_, keyWordVar_)) {
                    String cp_ = StringExpUtil.getQuickComponentType(typeAff_, nbParentsInfer_);
                    if (!InvokingOperation.isNotCorrectDim(cp_)){
                        foundType_ = cp_;
                    }
                }
                String type_ = _page.getAliasFct();
                StringMap<String> vars_ = new StringMap<String>();
                StringBuilder pattern_ = new StringBuilder(type_);
                StringList candidates_ = new StringList();
                if (!foundType_.isEmpty()) {
                    Mapping mapping_ = new Mapping();
                    mapping_.setMapping(_page.getCurrentConstraints().getCurrentConstraints());
                    mapping_.setParam(pattern_.toString());
                    mapping_.setArg(foundType_);
                    if (AnaInherits.isCorrectOrNumbers(mapping_, _page)) {
                        candidates_.add(foundType_);
                    } else {
                        StringList conv_ = InvokingOperation.tryInferOrImplicitFctRef(pattern_.toString(), new StringMap<String>(), _page, foundType_);
                        candidates_.addAllElts(conv_);
                    }
                }
                boolean list_ = false;
                if (m_ instanceof ArgumentListInstancing){
                    list_ = true;
                    m_ = m_.getParent().getParent();
                }
                if (m_ instanceof NamedArgumentOperation){
                    NamedArgumentOperation n_ = (NamedArgumentOperation) m_;
                    String inferRecord_ = n_.infer();
                    if (!inferRecord_.isEmpty()) {
                        StringList format_ = InvokingOperation.tryFormatFctRefRec(inferRecord_, nbParentsInfer_, type_, vars_, _page);
                        candidates_.addAllElts(format_);
                    }
                    String name_ = n_.getName();
                    MethodOperation call_ = n_.getParent();
                    if (call_ instanceof RetrieveMethod) {
                        RetrieveMethod f_ = (RetrieveMethod) call_;
                        NameParametersFilter filter_ = InvokingOperation.buildQuickFilter(_page,call_);
                        CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
                        int lenMet_ = methodInfos_.size();
                        for (int i = 0; i < lenMet_; i++) {
                            int gr_ = methodInfos_.get(i).size();
                            CustList<MethodInfo> newList_ = new CustList<MethodInfo>();
                            for (int j = 0; j < gr_; j++) {
                                MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                                if (InvokingOperation.isValidNameIndex(filter_,methodInfo_,name_)) {
                                    newList_.add(methodInfo_);
                                }
                                StringList format_ = InvokingOperation.tryParamFormatFctRef(filter_,methodInfo_, name_, nbParentsInfer_, type_, vars_, _page);
                                candidates_.addAllElts(format_);
                            }
                            methodInfos_.set(i,newList_);
                        }
                    }
                    if (call_ instanceof RetrieveConstructor) {
                        RetrieveConstructor f_ = (RetrieveConstructor) call_;
                        NameParametersFilter filter_ = InvokingOperation.buildQuickFilter(_page,call_);
                        CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
                        int lenMet_ = methodInfos_.size();
                        CustList<ConstructorInfo> newList_ = new CustList<ConstructorInfo>();
                        for (int i = 0; i < lenMet_; i++) {
                            ConstructorInfo methodInfo_ = methodInfos_.get(i);
                            if (InvokingOperation.isValidNameIndex(filter_,methodInfo_,name_)) {
                                newList_.add(methodInfo_);
                            }
                            StringList format_ = InvokingOperation.tryParamFormatFctRef(filter_,methodInfo_, name_, nbParentsInfer_, type_, vars_, _page);
                            candidates_.addAllElts(format_);
                        }
                        methodInfos_.clear();
                        methodInfos_.addAllElts(newList_);
                    }
                }
                if (m_ instanceof RetrieveMethod){
                    RetrieveMethod f_ = (RetrieveMethod) m_;
                    OperationNode firstChild_ = f_.getFirstChild();
                    int deltaCount_ = InvokingOperation.getDeltaCount(list_,firstChild_);
                    int indexChild_ = par_.getOperationChild().getIndexChild()-deltaCount_;
                    CustList<CustList<MethodInfo>> methodInfos_ = f_.getMethodInfos();
                    int lenMet_ = methodInfos_.size();
                    for (int i = 0; i < lenMet_; i++) {
                        int gr_ = methodInfos_.get(i).size();
                        for (int j = 0; j < gr_; j++) {
                            MethodInfo methodInfo_ = methodInfos_.get(i).get(j);
                            StringList format_ = InvokingOperation.tryFormatFctRef(methodInfo_, indexChild_, nbParentsInfer_, type_, vars_, _page);
                            candidates_.addAllElts(format_);
                        }
                    }
                }
                if (m_ instanceof RetrieveConstructor){
                    RetrieveConstructor f_ = (RetrieveConstructor) m_;
                    OperationNode firstChild_ = f_.getFirstChild();
                    int deltaCount_ = InvokingOperation.getDeltaCount(list_,firstChild_);
                    int indexChild_ = par_.getOperationChild().getIndexChild()-deltaCount_;
                    CustList<ConstructorInfo> methodInfos_ = f_.getCtors();
                    int lenMet_ = methodInfos_.size();
                    for (int i = 0; i < lenMet_; i++) {
                        ConstructorInfo methodInfo_ = methodInfos_.get(i);
                        StringList format_ = InvokingOperation.tryFormatFctRef(methodInfo_, indexChild_, nbParentsInfer_, type_, vars_, _page);
                        candidates_.addAllElts(format_);
                    }
                }
                StringList bounds_ = new StringList();
                for (String c: previousResultClass.getNames()) {
                    bounds_.addAllElts(InvokingOperation.getBounds(c, _page));
                }
                if (o_ instanceof StaticCallAccessOperation) {
                    StaticCallAccessOperation stCall_ = (StaticCallAccessOperation) o_;
                    if (StringUtil.quickEq("<>",stCall_.getStCall())) {
                        stCall_.check(_page);
                        FoundErrorInterpret badCall_ = new FoundErrorInterpret();
                        badCall_.setFile(_page.getCurrentFile());
                        badCall_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                        //last parenthesis
                        badCall_.buildError(_page.getAnalysisMessages().getSplitComa(),
                                Long.toString(2),
                                Long.toString(len_)
                        );
                        _page.getLocalizer().addError(badCall_);
                        int i_ = _page.getLocalizer().getCurrentLocationIndex()+className.lastIndexOf(')');
                        errPart(badCall_, i_, 1);
                        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                        return;
                    }
                    if (StringUtil.quickEq(args_.first().trim(),new_)) {
                        StringList a_ = cloneArrayBounds(bounds_);
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
                        stCall_.check(_page);
                        String id_ = StringExpUtil.getIdFromAllTypes(prev_);
                        AnaGeneType h_ = _page.getAnaGeneType(id_);
                        if (noDefCtor(h_)) {
                            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
                            badCall_.setFile(_page.getCurrentFile());
                            badCall_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                            //last parenthesis
                            badCall_.buildError(_page.getAnalysisMessages().getSplitComa(),
                                    Long.toString(2),
                                    Long.toString(len_)
                            );
                            _page.getLocalizer().addError(badCall_);
                            int i_ = _page.getLocalizer().getCurrentLocationIndex()+className.lastIndexOf(')');
                            errPart(badCall_, i_, 1);
                            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                            return;
                        }
                        StringMap<StringList> mapCtr_ = _page.getCurrentConstraints().getCurrentConstraints();
                        CustList<ConstrustorIdVarArg> ctors_ = new CustList<ConstrustorIdVarArg>();
                        CustList<ConstructorInfo> sgns_ = tryGetSignatures(h_, _page, prev_, stCall_.getStCall());

                        for (String s: candidates_) {
                            StringList allTypes_ = StringExpUtil.getAllTypes(s);
                            if (allTypes_.size() == 1) {
                                if (noCtor(h_)) {
                                    if (!stCall_.getStCall().isEmpty()) {
                                        continue;
                                    }
                                    ctors_.add(noCtorFound(prev_,h_));
                                    continue;
                                }
                                tryFilterAddCtor(sgns_,_page, h_, ctors_, null,stCall_.getStCall(),"");
                                continue;
                            }
                            StringList argsTypes_ = new StringList(allTypes_.mid(1,allTypes_.size()-2));
                            String ret_ = allTypes_.last();
                            if (ret_.startsWith("~")) {
                                continue;
                            }
                            if (!StringUtil.quickEq(ret_,"?")) {
                                if (stCall_.getStCall().isEmpty()) {
                                    Mapping map_ = new Mapping();
                                    map_.setArg(prev_);
                                    map_.getMapping().putAllMap(mapCtr_);
                                    map_.setParam(ret_);
                                    if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
                                        continue;
                                    }
                                } else {
                                    if (!h_.isSubTypeOf(StringExpUtil.getIdFromAllTypes(ret_),_page)) {
                                        continue;
                                    }
                                }
                            }
                            if (!argsTypes_.isEmpty()) {
                                if (AbstractInstancingOperation.koInstancingType(prev_,MethodAccessKind.INSTANCE,_page,h_,argsTypes_.first())) {
                                    continue;
                                }
                                if (!h_.withoutInstance()) {
                                    argsTypes_.remove(0);
                                }
                            }
                            if (noCtor(h_)) {
                                String real_ = tryInf(_page, stCall_, prev_, h_, ret_);
                                if (real_.isEmpty()) {
                                    continue;
                                }
                                if (argsTypes_.isEmpty()) {
                                    ctors_.add(noCtorFound(real_,h_));
                                    continue;
                                }
                            }
                            tryFilterAddCtor(sgns_,_page, h_, ctors_, argsTypes_,stCall_.getStCall(),ret_);
                        }
                        ctors_ = AnaTemplates.reduceCtors(ctors_);
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
                        FoundErrorInterpret badCall_ = new FoundErrorInterpret();
                        badCall_.setFile(_page.getCurrentFile());
                        badCall_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                        //last parenthesis
                        badCall_.buildError(_page.getAnalysisMessages().getSplitComa(),
                                Long.toString(2),
                                Long.toString(len_)
                        );
                        _page.getLocalizer().addError(badCall_);
                        int i_ = _page.getLocalizer().getCurrentLocationIndex()+className.lastIndexOf(')');
                        errPart(badCall_, i_, 1);
                        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                        return;
                    }
                    StringList a_ = cloneArrayBounds(bounds_);
                    if (a_.onlyOneElt()) {
                        found(a_.first());
                        String name_ = args_.first();
                        if (koArrayMethod(name_, _page)) {
                            tryCheck(_page);
                            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
                            badCall_.setFile(_page.getCurrentFile());
                            badCall_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                            //last parenthesis
                            badCall_.buildError(_page.getAnalysisMessages().getSplitComa(),
                                    Long.toString(2),
                                    Long.toString(len_)
                            );
                            _page.getLocalizer().addError(badCall_);
                            int i_ = _page.getLocalizer().getCurrentLocationIndex()+className.lastIndexOf(')');
                            errPart(badCall_, i_, 1);
                            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                            return;
                        }
                        initClonedMethod(_page, name_);
                        setResultClass(new AnaClassArgumentMatching(buildCloned(_page, a_.first(), true)));
                        return;
                    }
                    String pref_ = "";
                    String name_ = args_.first();
                    int indexDot_ = name_.indexOf('.');
                    if (indexDot_ > -1) {
                        pref_ = name_.substring(0,indexDot_).trim();
                    }
                    boolean polymorph_ = true;
                    if (StringUtil.quickEq(pref_,keyWords_.getKeyWordThat())) {
                        name_ = name_.substring(indexDot_+1).trim();
                        polymorph_ = false;
                    }
                    CustList<ClassMethodIdReturn> resList_ = new CustList<ClassMethodIdReturn>();
                    CustList<CustList<MethodInfo>> methodsInst_;
                    methodsInst_ = getDeclaredCustMethodByType(MethodAccessKind.INSTANCE, bounds_, name_, false, _page, new ScopeFilter(null, true, true, false, _page.getGlobalClass()), new FormattedFilter());
                    CustList<CustList<MethodInfo>> methodsSta_;
                    methodsSta_ = getDeclaredCustMethodByType(MethodAccessKind.STATIC_CALL, bounds_, name_, false, _page, new ScopeFilter(null, true, true, false, _page.getGlobalClass()), new FormattedFilter());
                    //use types of previous operation
                    for (String s: candidates_) {
                        StringList allTypes_ = StringExpUtil.getAllTypes(s);
                        if (allTypes_.size() == 1) {
                            tryAddMeth(methodsInst_,_page, name_, resList_, null, "", stCall_.getStCall());
                            continue;
                        }
                        StringList argsTypes_ = new StringList(allTypes_.mid(1,allTypes_.size()-2));
                        String ret_ = allTypes_.last();
                        tryAddMeth(methodsSta_,_page, name_, resList_, argsTypes_, ret_, stCall_.getStCall());
                        if (!argsTypes_.isEmpty()&&!argsTypes_.first().startsWith("~")) {
                            Mapping mapp_ = new Mapping();
                            mapp_.setArg(argsTypes_.first());
                            mapp_.setParam(new AnaClassArgumentMatching(bounds_));
                            mapp_.setMapping(_page.getCurrentConstraints().getCurrentConstraints());
                            if (AnaInherits.isCorrectOrNumbers(mapp_,_page)) {
                                argsTypes_.remove(0);
                                tryAddMeth(methodsInst_,_page, name_, resList_, argsTypes_, ret_, stCall_.getStCall());
                            }
                        }
                    }
                    resList_ = AnaTemplates.reduceMethods(resList_);
                    if (okList(resList_,!polymorph_)) {
                        ClassMethodIdReturn id_ = resList_.first();
                        trySetPoly(id_,polymorph_);
                        initIdMethod(id_);
                        String foundClass_ = id_.getRealClass();
                        if (!stCall_.getStCall().isEmpty()) {
                            stCall_.setPartOffsets(new ResolvedInstance(stCall_.getPartOffsets(),stCall_.getLt(), stCall_.getGt(), foundClass_));
                        }
                        String fct_ = formatReturn(_page, id_);
                        setResultClass(new AnaClassArgumentMatching(fct_));
                        return;
                    }
                } else {
                    StringList a_ = cloneArrayBounds(bounds_);
                    if (a_.onlyOneElt()) {
                        found(a_.first());
                        String name_ = args_.first();
                        if (koArrayMethod(name_, _page)) {
                            tryCheck(_page);
                            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
                            badCall_.setFile(_page.getCurrentFile());
                            badCall_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                            //last parenthesis
                            badCall_.buildError(_page.getAnalysisMessages().getSplitComa(),
                                    Long.toString(2),
                                    Long.toString(len_)
                            );
                            _page.getLocalizer().addError(badCall_);
                            int i_ = _page.getLocalizer().getCurrentLocationIndex()+className.lastIndexOf(')');
                            errPart(badCall_, i_, 1);
                            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                            return;
                        }
                        initClonedMethod(_page, name_);
                        setResultClass(new AnaClassArgumentMatching(buildCloned(_page, a_.first(), false)));
                        return;
                    }
                    String pref_ = "";
                    String name_ = args_.first();
                    int indexDot_ = name_.indexOf('.');
                    if (indexDot_ > -1) {
                        pref_ = name_.substring(0,indexDot_).trim();
                    }
                    boolean polymorph_ = true;
                    if (StringUtil.quickEq(pref_,keyWords_.getKeyWordThat())) {
                        name_ = name_.substring(indexDot_+1).trim();
                        polymorph_ = false;
                    }
                    CustList<ClassMethodIdReturn> resList_ = new CustList<ClassMethodIdReturn>();
                    CustList<CustList<MethodInfo>> methodsInst_;
                    methodsInst_ = getDeclaredCustMethodByType(MethodAccessKind.INSTANCE, bounds_, name_, false, _page, new ScopeFilter(null, true, true, false, _page.getGlobalClass()), new FormattedFilter());
                    for (String s: candidates_) {
                        StringList allTypes_ = StringExpUtil.getAllTypes(s);
                        if (allTypes_.size() == 1) {
                            tryAddMeth(methodsInst_,_page,name_,resList_,null,"","");
                            continue;
                        }
                        StringList argsTypes_ = new StringList(allTypes_.mid(1,allTypes_.size()-2));
                        String ret_ = allTypes_.last();
                        tryAddMeth(methodsInst_,_page,name_,resList_,argsTypes_,ret_,"");
                    }
                    resList_ = AnaTemplates.reduceMethods(resList_);
                    if (okList(resList_,!polymorph_)) {
                        ClassMethodIdReturn id_ = resList_.first();
                        trySetPoly(id_,polymorph_);
                        initIdMethod(id_);
                        String fct_ = formatReturnPrevious(_page, id_);
                        setResultClass(new AnaClassArgumentMatching(fct_));
                        return;
                    }
                }
            }
            tryCheck(_page);
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFile(_page.getCurrentFile());
            badCall_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //last parenthesis
            badCall_.buildError(_page.getAnalysisMessages().getSplitComa(),
                    Long.toString(2),
                    Long.toString(len_)
            );
            _page.getLocalizer().addError(badCall_);
            int i_ = _page.getLocalizer().getCurrentLocationIndex()+className.lastIndexOf(')');
            errPart(badCall_, i_, 1);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        tryCheck(_page);
        generalProcess(args_, _page);
    }

    private void errPart(FoundErrorInterpret _err, int _begin, int _length) {
        partOffsetsErrEnd = new InfoErrorDto(_err.getBuiltError(), _begin, _length);
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

    private static String tryInf(AnalyzedPageEl _page, StaticCallAccessOperation _stCall, String _prev, AnaGeneType _h, String _ret) {
        String real_;
        if (_stCall.getStCall().isEmpty()) {
            real_ = _prev;
        } else {
            real_ = AnaTemplates.tryInferMethod(-1, _prev, ConstructorId.to(new ConstructorId(_prev, new StringList(), false)),
                    _stCall.getStCall(), _page.getCurrentConstraints().getCurrentConstraints(),
                    new CustList<AnaClassArgumentMatching>(), _h.getGenericString(), _ret, _page);
        }
        return real_;
    }

    private static void tryAddMeth(CustList<CustList<MethodInfo>> _methods, AnalyzedPageEl _page, String _name, CustList<ClassMethodIdReturn> _resList, StringList _argsTypes, String _ret, String _stCall) {
        ClassMethodIdReturn resultOther_ = getCustResultLambdaInfer(_methods, _name, _page, _stCall, _argsTypes, _ret);
        if (resultOther_ != null) {
            _resList.add(resultOther_);
        }
    }

    private static void tryFilterAddCtor(CustList<ConstructorInfo> _list, AnalyzedPageEl _page, AnaGeneType _h, CustList<ConstrustorIdVarArg> _ctors, StringList _argsTypes, String _stCall, String _retType) {
        Parametrable constructorInfo_ = tryGetFilterSignaturesInfer(_list, _h, _page, _argsTypes,_stCall,_retType);
        if (constructorInfo_ instanceof ConstructorInfo) {
            //        ConstrustorIdVarArg out_;
//        out_ = new ConstrustorIdVarArg();
//        if (_cInfo.isVarArgWrap()) {
//            out_.setVarArgToCall(true);
//        }
//        setupContainer(_type,_cInfo.getClassName(),out_);
//        out_.setRealId(_cInfo.getConstraints());
//        out_.setPair(_cInfo.getPair());
//        out_.setConstId(_cInfo.getFormatted());
//        out_.setFileName(_cInfo.getFileName());
//        out_.setStandardType(_cInfo.getStandardType());
//        out_.setMemberId(_cInfo.getMemberId());
//        return out_;
            _ctors.add(buildCtorInfo(constructorInfo_.getClassName(), _h, (ConstructorInfo) constructorInfo_));
        }
    }

    private static boolean okList(CustList<ClassMethodIdReturn> _resList, boolean _staticChoice) {
        return _resList.size() == 1 && !errOwner(_resList.first().getRealClass(), _resList.first().getRealId()) && !isAbstract(_staticChoice,_resList.first());
    }

    private static void appendArgsCtor(ConstructorId _fid, StringList _parts) {
        int lenArg_ = _fid.getParametersTypesLength();
        for (int i = 0; i < lenArg_; i++) {
            String p_ = _fid.getParametersType(i);
            if (i + 1 == lenArg_ && _fid.isVararg()) {
                p_ = StringExpUtil.getPrettyArrayType(p_);
            }
            if (_fid.getParametersRef(i)) {
                _parts.add("~" + p_);
            } else {
                _parts.add(p_);
            }
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
        StringList str_;
        String name_ = _args.get(1).trim();
        if (StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordExplicit())
                || StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordCast())
                || StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordTrue())
                || StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordFalse())) {
            CustList<AnaClassArgumentMatching> methodTypes_ = new CustList<AnaClassArgumentMatching>();
            int i_ = 2;
            ClassMethodId feed_ = null;
            KeyWords keyWords_ = _page.getKeyWords();
            String keyWordId_ = keyWords_.getKeyWordId();
            int offset_ = className.indexOf('(')+1;
            offset_ += StringExpUtil.getOffset(_args.first());
            AnaResultPartType result_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_, _args.first().trim(), _page);
            String type_ = result_.getResult(_page);
            partOffsetsBegin.add(result_);
            if (StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordTrue())
                || StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordFalse())) {
                ClassMethodIdReturn resMethod_;
                if (StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordTrue())){
                    resMethod_ = tryGetDeclaredTests(type_, _page, _page.getTrues());
                } else {
                    resMethod_ = tryGetDeclaredTests(type_, _page, _page.getFalses());
                }
                if (resMethod_ == null) {
                    int rc_ = _page.getLocalizer().getCurrentLocationIndex() + offset_;
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(_page.getCurrentFile());
                    un_.setIndexFile(rc_);
                    //_in len
                    un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                            type_);
                    _page.getLocalizer().addError(un_);
                    addErr(un_.getBuiltError());
                    setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                    return;
                }
                lambdaMethodContent.setExpCast(true);
                initIdMethod(resMethod_);
                String fct_ = formatReturnPrevious(_page, resMethod_);
                setResultClass(new AnaClassArgumentMatching(fct_));
                return;
            }
            MethodId argsRes_;
            if (matchIdKeyWord(_args, _len, i_, keyWordId_)) {
                String cl_ = StringExpUtil.getIdFromAllTypes(type_);
                argsRes_ = resolveArguments(false,i_+1, cl_, MethodAccessKind.STATIC, _args, _page);
                if (argsRes_ == null) {
                    return;
                }
                boolean varargFct_ = argsRes_.isVararg();
                AnaGeneType geneType_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(type_));
                if (geneType_ == null) {
                    int rc_ = _page.getLocalizer().getCurrentLocationIndex() + offset_;
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFile(_page.getCurrentFile());
                    un_.setIndexFile(rc_);
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
                if (params_.size() < 2) {
                    params_.add(0, gene_);
                }
                feed_ = new ClassMethodId(type_, new MethodId(MethodAccessKind.STATIC, name_, params_, varargFct_));
                int nbParams_ = argsRes_.getParametersTypesLength();
                for (int i = 0; i < nbParams_; i++) {
                    String format_ = AnaInherits.wildCardFormatParam(type_, argsRes_.getParametersType(i), _page);
                    if (format_.isEmpty()) {
                        String fct_ = buildCast(_page, name_, type_, new MethodId(MethodAccessKind.STATIC, name_, new StringList(_page.getAliasObject())));
                        setResultClass(new AnaClassArgumentMatching(fct_));
                        return;
                    }
                    methodTypes_.add(new AnaClassArgumentMatching(format_));
                }
            } else {
                argsRes_ = resolveArguments(i_, _args, _page);
                if (argsRes_ == null) {
                    return;
                }
                int nbParams_ = argsRes_.getParametersTypesLength();
                for (int i = 0; i < nbParams_; i++) {
                    methodTypes_.add(new AnaClassArgumentMatching(argsRes_.getParametersType(i)));
                }
            }
            if (argsRes_.getParametersTypesLength() > 2) {
                FoundErrorInterpret static_ = new FoundErrorInterpret();
                static_.setFile(_page.getCurrentFile());
                static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //key word len
                static_.buildError(_page.getAnalysisMessages().getSplitComaLow(),
                        Long.toString(2),
                        Long.toString(argsRes_.getParametersTypesLength())
                );
                _page.getLocalizer().addError(static_);
                addErr(static_.getBuiltError());
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            if (argsRes_.getParametersTypesLength() > 1 && feed_ == null) {
                FoundErrorInterpret static_ = new FoundErrorInterpret();
                static_.setFile(_page.getCurrentFile());
                static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //key word len
                static_.buildError(_page.getAnalysisMessages().getSplitComaLow(),
                        Long.toString(1),
                        Long.toString(argsRes_.getParametersTypesLength())
                );
                _page.getLocalizer().addError(static_);
                addErr(static_.getBuiltError());
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            if (!StringExpUtil.customCast(type_)) {
                String fct_ = buildCast(_page, name_, type_, new MethodId(MethodAccessKind.STATIC, name_, new StringList(_page.getAliasObject())));
                setResultClass(new AnaClassArgumentMatching(fct_));
                return;
            }
            if (methodTypes_.size() < 2) {
                methodTypes_.add(0, new AnaClassArgumentMatching(type_));
            }
            ClassMethodIdReturn id_;
            if (StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordExplicit())){
                id_ = tryGetCast(type_, feed_, OperationNode.toArgArray(methodTypes_), _page, _page.getExplicitCastMethods(), _page.getExplicitIdCastMethods(), _page.getExplicitFromCastMethods());
            } else {
                id_ = tryGetCast(type_, feed_, OperationNode.toArgArray(methodTypes_), _page, _page.getImplicitCastMethods(), _page.getImplicitIdCastMethods(), _page.getImplicitFromCastMethods());
            }
            if (id_ == null) {
                MethodId idCast_;
                if (argsRes_.getParametersTypesLength() == 0) {
                    idCast_ = new MethodId(MethodAccessKind.STATIC, name_,new StringList(_page.getAliasObject()));
                } else {
                    idCast_ = new MethodId(MethodAccessKind.STATIC, name_,IdentifiableUtil.params(argsRes_));
                }
                String fct_ = buildCast(_page, name_, type_, idCast_);
                setResultClass(new AnaClassArgumentMatching(fct_));
                return;
            }
            lambdaMethodContent.setExpCast(true);
            initIdMethod(id_);
            String fct_ = formatReturnPrevious(_page, id_);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        StringList methodTypes_ = new StringList();
        if (!isIntermediateDottedOperation()) {
            if (_len == 3 && StringUtil.quickEq(name_, "=")) {
                int offset_ = className.indexOf('(')+1;
                offset_ += StringExpUtil.getOffset(_args.first());
                String type_ = resolveCorrectTypeAccessible(true, _args.first().trim(), _page, offset_,partOffsetsBegin);
                found(type_);
                String rightPart_ = _args.last();
                int secOffset_ = className.indexOf('(')+1+_args.get(0).length()+1+_args.get(1).length()+1+StringExpUtil.getOffset(rightPart_);
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
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFile(_page.getCurrentFile());
                    cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //key word len
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            arg_,
                            type_);
                    _page.getLocalizer().addError(cast_);
                    addErr(cast_.getBuiltError());
                }
                StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
                fct_.append(StringExpUtil.TEMPLATE_BEGIN);
                fct_.append('~');
                fct_.append(type_);
                fct_.append(StringExpUtil.TEMPLATE_SEP);
                fct_.append(arg_);
                fct_.append(StringExpUtil.TEMPLATE_SEP);
                fct_.append(arg_);
                fct_.append(StringExpUtil.TEMPLATE_END);
                lambdaCommonContent.setResult(fct_.toString());
                StringList params_ = new StringList();
                params_.add(type_);
                params_.add(arg_);
                MethodId id_ = new MethodId(MethodAccessKind.STATIC, "=", params_);
                method = new ClassMethodId(type_, id_);
                setResultClass(new AnaClassArgumentMatching(fct_.toString()));
                return;
            }
            if (_len == 2 && StringUtil.quickEq(name_, "=")) {
                int offset_ = className.indexOf('(')+1;
                offset_ += StringExpUtil.getOffset(_args.first());
                String type_ = resolveCorrectTypeAccessible(true, _args.first().trim(), _page, offset_,partOffsetsBegin);
                found(type_);
                StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
                fct_.append(StringExpUtil.TEMPLATE_BEGIN);
                fct_.append('~');
                fct_.append(type_);
                fct_.append(StringExpUtil.TEMPLATE_SEP);
                fct_.append(type_);
                fct_.append(StringExpUtil.TEMPLATE_SEP);
                fct_.append(type_);
                fct_.append(StringExpUtil.TEMPLATE_END);
                lambdaCommonContent.setResult(fct_.toString());
                StringList params_ = new StringList();
                params_.add(type_);
                params_.add(type_);
                MethodId id_ = new MethodId(MethodAccessKind.STATIC, "=", params_);
                method = new ClassMethodId(type_, id_);
                setResultClass(new AnaClassArgumentMatching(fct_.toString()));
                return;
            }
            int i_ = 2;
            boolean staticChoiceMethod_ = false;
            boolean baseAccess_ = true;
            boolean accessSuper_ = true;
            KeyWords keyWords_ = _page.getKeyWords();
            String keyWordSuper_ = keyWords_.getKeyWordSuper();
            String keyWordThat_ = keyWords_.getKeyWordThat();
            String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
            String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
            String keyWordId_ = keyWords_.getKeyWordId();
            if (i_ < _len && StringUtil.quickEq(name_, keyWordSuper_)) {
                name_ = _args.get(i_).trim();
                i_++;
                staticChoiceMethod_ = true;
                baseAccess_ = false;
            } else if (i_ < _len && StringUtil.quickEq(name_, keyWordThat_)) {
                name_ = _args.get(i_).trim();
                i_++;
                staticChoiceMethod_ = true;
            } else if (i_ < _len && StringUtil.quickEq(name_, keyWordClasschoice_)) {
                name_ = _args.get(i_).trim();
                i_++;
                staticChoiceMethod_ = true;
                accessSuper_ = false;
            } else if (i_ < _len && StringUtil.quickEq(name_, keyWordSuperaccess_)) {
                name_ = _args.get(i_).trim();
                i_++;
                staticChoiceMethod_ = true;
            } else {
                lambdaMethodContent.setPolymorph(true);
            }
            int vararg_ = -1;
            MethodId argsRes_;
            ClassMethodIdAncestor feed_ = null;
            if (matchIdKeyWord(_args, _len, i_, keyWordId_)) {
                MethodAccessId idUpdate_ = new MethodAccessId(i_);
                String keyWordStatic_ = _page.getKeyWords().getKeyWordStatic();
                String keyWordStaticCall_ = _page.getKeyWords().getKeyWordStaticCall();
                idUpdate_.setupInfosId(i_ + 1,_args,keyWordStatic_,keyWordStaticCall_);
                boolean retRef_ = idUpdate_.isRetRef();
                MethodAccessKind staticFlag_ = idUpdate_.getKind();
                i_ = idUpdate_.getIndex();
                int offset_ = className.indexOf('(')+1;
                offset_ += StringExpUtil.getOffset(_args.first());
                String type_ = resolveCorrectTypeAccessible(staticFlag_ != MethodAccessKind.STATIC, _args.first().trim(), _page, offset_,partOffsetsBegin);
                str_ = InvokingOperation.getBounds(type_, _page);
                String cl_ = StringExpUtil.getIdFromAllTypes(type_);
                argsRes_ = resolveArguments(retRef_,i_+1, cl_, staticFlag_, _args, _page);
                if (argsRes_ == null) {
                    return;
                }
                feed_ = new ClassMethodIdAncestor(new ClassMethodId(cl_, MethodId.to(staticFlag_, name_, argsRes_)),idUpdate_.getAncestor());
                ko(type_, argsRes_, methodTypes_, _page);
            } else {
                str_ = resolveCorrectTypesExact(_args, _page);
                argsRes_ = resolveArguments(i_, _args, _page);
                if (argsRes_ == null) {
                    return;
                }
                vararg_ = vararg(_len, methodTypes_, vararg_, argsRes_, i_);
            }
            for (String s: str_) {
                errOwner(s,argsRes_,_page);
            }
            StringList a_ = cloneArrayBounds(str_);
            if (a_.onlyOneElt()) {
                found(a_.first());
                if (isRangeAccess(name_)) {
                    if (methodTypes_.isEmpty()) {
                        initArrRange(_page, name_, a_.first());
                        setResultClass(new AnaClassArgumentMatching(buildFctRange(_page, name_, a_, true)));
                        return;
                    }
                    initArrRangeBound(_page, name_, a_.first());
                    setResultClass(new AnaClassArgumentMatching(buildFctRangeBound(_page, name_,a_, true)));
                    return;
                }
                if (isAccess(name_)) {
                    if (methodTypes_.isEmpty()) {
                        StringBuilder fct_ = buildFctLen(_page, name_, a_, true);
                        setResultClass(new AnaClassArgumentMatching(fct_.toString()));
                        return;
                    }
                    prAcc(_page, name_, methodTypes_, true, a_.first());
                    return;
                }
                if (!StringUtil.quickEq(name_, _page.getAliasClone())) {
                    addErrArray(str_, _page);
                    setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                    return;
                }
                initClonedMethod(_page, name_);
                setResultClass(new AnaClassArgumentMatching(buildCloned(_page, a_.first(), true)));
                return;
            }
            ClassMethodIdReturn id_ = tryGetDeclaredCustMethodLambda(vararg_, MethodAccessKind.INSTANCE, str_, name_, accessSuper_, baseAccess_, feed_, methodTypes_, _page);
            if (id_ == null) {
                buildErrNoRefMethod(MethodAccessKind.INSTANCE,name_,_page,methodTypes_);
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            initIdMethod(id_);
            String fct_ = formatReturn(_page, id_);
            setResultClass(new AnaClassArgumentMatching(fct_));
            processAbstract(staticChoiceMethod_, id_, _page);
            return;
        }
        if (isStaticAccess(_m)) {
            OperationNode o_ = _m.getFirstChild();
            boolean stAcc_ = o_ instanceof StaticAccessOperation;
            boolean stAccCall_ = o_ instanceof StaticCallAccessOperation;
            str_ = resolveCorrectTypes(stAccCall_, _args, _page);
            int vararg_ = -1;
            MethodId argsRes_;
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
                int offset_ = className.indexOf('(')+1+_args.first().length();
                offset_++;
                offset_ += _args.get(1).length();
                offset_++;
                offset_ += _args.get(2).length();
                offset_++;
                offset_ += StringExpUtil.getOffset(_args.get(3));
                String cl_;
                if (nameId_.isEmpty()) {
                    cl_ = previousResultClass.getSingleNameOrEmpty();
                } else {
                    ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(offset_, nameId_, _page);
                    cl_ = resolvedIdType_.getFullName();
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
                argsRes_ = resolveArguments(retRef_,ind_, cl_, kind_, _args, _page);
                if (argsRes_ == null) {
                    return;
                }
                String idFrom_ = StringExpUtil.getIdFromAllTypes(cl_);
                feed_ = new ClassMethodIdAncestor(new ClassMethodId(idFrom_, MethodId.to(kind_, name_, argsRes_)),acc_.getAncestor());
                ko(cl_, argsRes_, methodTypes_, _page);
            } else {
                argsRes_ = resolveArguments(2, _args, _page);
                if (argsRes_ == null) {
                    return;
                }
                vararg_ = vararg(_len, methodTypes_, vararg_, argsRes_, 2);
            }
            for (String s: str_) {
                errOwner(s,argsRes_,_page);
            }
            ClassMethodIdReturn id_ = tryGetDeclaredCustMethodLambda(vararg_, kind_, str_, name_, true, true, feed_, methodTypes_, _page);
            if (id_ == null) {
                buildErrNoRefMethod(kind_,name_,_page,methodTypes_);
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            initIdMethod(id_);
            String fct_ = formatReturnPrevious(_page, id_);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        MethodAccessKind stCtx_;
        int vararg_ = -1;
        int i_ = 2;
        boolean staticChoiceMethod_ = false;
        boolean baseAccess_ = true;
        boolean accessSuper_ = true;
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
        String keyWordId_ = keyWords_.getKeyWordId();
        if (i_ < _len && StringUtil.quickEq(name_, keyWordSuper_)) {
            name_ = _args.get(i_).trim();
            i_++;
            staticChoiceMethod_ = true;
            baseAccess_ = false;
        } else if (i_ < _len && StringUtil.quickEq(name_, keyWordThat_)) {
            name_ = _args.get(i_).trim();
            i_++;
            staticChoiceMethod_ = true;
        } else if (i_ < _len && StringUtil.quickEq(name_, keyWordClasschoice_)) {
            name_ = _args.get(i_).trim();
            i_++;
            staticChoiceMethod_ = true;
            accessSuper_ = false;
        } else if (i_ < _len && StringUtil.quickEq(name_, keyWordSuperaccess_)) {
            name_ = _args.get(i_).trim();
            i_++;
            staticChoiceMethod_ = true;
        } else {
            lambdaMethodContent.setPolymorph(true);
        }
        MethodId argsRes_;
        ClassMethodIdAncestor feed_ = null;
        if (matchIdKeyWord(_args, _len, i_, keyWordId_)) {
            MethodAccessId idUpdate_ = new MethodAccessId(i_);
            String keyWordStatic_ = _page.getKeyWords().getKeyWordStatic();
            String keyWordStaticCall_ = _page.getKeyWords().getKeyWordStaticCall();
            idUpdate_.setupInfosId(i_ + 1,_args,keyWordStatic_,keyWordStaticCall_);
            boolean retRef_ = idUpdate_.isRetRef();
            stCtx_ = idUpdate_.getKind();
            i_ = idUpdate_.getIndex();
            int offset_ = className.indexOf('(')+1;
            offset_ += StringExpUtil.getOffset(_args.first());
            String type_ = resolveCorrectTypeAccessible(stCtx_ != MethodAccessKind.STATIC, _args.first().trim(), _page, offset_,partOffsetsBegin);
            str_ = InvokingOperation.getBounds(type_, _page);
            String cl_ = StringExpUtil.getIdFromAllTypes(type_);
            argsRes_ = resolveArguments(retRef_,i_+1, cl_, stCtx_, _args, _page);
            if (argsRes_ == null) {
                return;
            }
            feed_ = new ClassMethodIdAncestor(new ClassMethodId(cl_, MethodId.to(stCtx_, name_, argsRes_)),idUpdate_.getAncestor());
            ko(type_, argsRes_, methodTypes_, _page);
        } else {
            stCtx_ = MethodAccessKind.INSTANCE;
            str_ = resolveCorrectTypesExact(_args, _page);
            argsRes_ = resolveArguments(i_, _args, _page);
            if (argsRes_ == null) {
                return;
            }
            vararg_ = vararg(_len, methodTypes_, vararg_, argsRes_, i_);
        }
        for (String s: str_) {
            errOwner(s,argsRes_,_page);
        }

        StringList bounds_ = new StringList();
        for (String c: previousResultClass.getNames()) {
            bounds_.addAllElts(InvokingOperation.getBounds(c, _page));
        }
        StringList a_ = cloneArrayBounds(bounds_);
        if (a_.onlyOneElt()) {
            found(a_.first());
            if (isRangeAccess(name_)) {
                if (methodTypes_.isEmpty()) {
                    initArrRange(_page, name_, a_.first());
                    setResultClass(new AnaClassArgumentMatching(buildFctRange(_page, name_, a_, false)));
                    return;
                }
                initArrRangeBound(_page, name_, a_.first());
                setResultClass(new AnaClassArgumentMatching(buildFctRangeBound(_page, name_,a_, false)));
                return;
            }
            if (isAccess(name_)) {
                if (methodTypes_.isEmpty()) {
                    StringBuilder fct_ = buildFctLen(_page, name_, a_, false);
                    setResultClass(new AnaClassArgumentMatching(fct_.toString()));
                    return;
                }
                prAcc(_page, name_, methodTypes_, false, a_.first());
                return;
            }
            if (!StringUtil.quickEq(name_, _page.getAliasClone())) {
                addErrArray(bounds_, _page);
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            initClonedMethod(_page, name_);
            setResultClass(new AnaClassArgumentMatching(buildCloned(_page, a_.first(), false)));
            return;
        }
        Mapping map_ = new Mapping();
        map_.setArg(new AnaClassArgumentMatching(bounds_));
        map_.setParam(new AnaClassArgumentMatching(str_));
        StringMap<StringList> maps_ = new StringMap<StringList>();
        getRefConstraints(maps_, _page);
        map_.setMapping(maps_);
        if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(bounds_,ExportCst.JOIN_TYPES),
                    StringUtil.join(str_,ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(cast_);
            addErr(cast_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        ClassMethodIdReturn id_ = tryGetDeclaredCustMethodLambda(vararg_, stCtx_, str_, name_, accessSuper_, baseAccess_, feed_, methodTypes_, _page);
        if (id_ == null) {
            buildErrNoRefMethod(stCtx_,name_,_page,methodTypes_);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        initIdMethod(id_);
        String fct_ = formatReturnPrevious(_page, id_);
        setResultClass(new AnaClassArgumentMatching(fct_));
        processAbstract(staticChoiceMethod_, id_, _page);
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
        fct_.append(_page.getAliasPrimInteger());
        fct_.append(StringExpUtil.TEMPLATE_SEP);
        if (StringUtil.quickEq("[:]=",_name)) {
            fct_.append(comp_);
            fct_.append(StringExpUtil.TEMPLATE_SEP);
        }
        fct_.append(comp_);
        fct_.append(StringExpUtil.TEMPLATE_END);
        lambdaCommonContent.setResult(fct_.toString());
        return fct_.toString();
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
        fct_.append(_page.getAliasPrimInteger());
        fct_.append(StringExpUtil.TEMPLATE_SEP);
        if (StringUtil.quickEq("[:]=",_name)) {
            fct_.append(comp_);
            fct_.append(StringExpUtil.TEMPLATE_SEP);
        }
        fct_.append(comp_);
        fct_.append(StringExpUtil.TEMPLATE_END);
        lambdaCommonContent.setResult(fct_.toString());
        return fct_.toString();
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
        String result_ = appendParts(_page, _type, _type, _idCast, _idCast, "", false);
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
                un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
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
                un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
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
        undefined_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
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

    private void ko(String _type, MethodId _id, StringList _formatted, AnalyzedPageEl _page) {
        if (errOwner(_type, _id, _page)) {
            return;
        }
        int nbParams_ = _id.getParametersTypesLength();
        for (int i = 0; i < nbParams_; i++) {
            String s_ = _id.getParametersType(i);
            String format_ = AnaInherits.wildCardFormatParam(_type, s_, _page);
            if (format_.isEmpty()) {
                FoundErrorInterpret static_ = new FoundErrorInterpret();
                static_.setFile(_page.getCurrentFile());
                static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //key word id len
                static_.buildError(_page.getAnalysisMessages().getBadParameTypeForId(),
                        s_);
                _page.getLocalizer().addError(static_);
                addErr(static_.getBuiltError());
                format_ = _page.getAliasObject();
            }
            String pref_ = "";
            if (_id.getParametersRef(i)) {
                pref_ = "~";
            }
            _formatted.add(pref_+format_);
        }
    }
    private boolean errOwner(String _type, MethodId _id, AnalyzedPageEl _page) {
        if (errOwner(_type,_id)) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFile(_page.getCurrentFile());
            static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word id len
            static_.buildError(_page.getAnalysisMessages().getBadParameTypeForId(),
                    _type);
            _page.getLocalizer().addError(static_);
            addErr(static_.getBuiltError());
            return true;
        }
        return false;
    }
    private static boolean errOwner(String _type, MethodId _id) {
        return _id.getKind() == MethodAccessKind.STATIC_CALL && StringExpUtil.isWildCard(_type);
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
        FoundErrorInterpret undefined_ = new FoundErrorInterpret();
        undefined_.setFile(_page.getCurrentFile());
        undefined_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
        //_name len
        undefined_.buildError(_page.getAnalysisMessages().getArrayCloneOnly(),
                _page.getAliasClone(),
                StringUtil.join(_str, ExportCst.JOIN_TYPES));
        _page.getLocalizer().addError(undefined_);
        addErr(undefined_.getBuiltError());
    }


    private static boolean koArrayMethod(String _name, AnalyzedPageEl _page) {
        return !StringUtil.quickEq(_name, _page.getAliasClone());
    }

    private void found(String _b) {
        lambdaCommonContent.setFoundFormatted(simpleFormatted(_b));
    }

    private void processAbstract(boolean _staticChoiceMethod, ClassMethodIdReturn _id, AnalyzedPageEl _page) {
        if (isAbstract(_staticChoiceMethod, _id)) {
            FoundErrorInterpret abs_ = new FoundErrorInterpret();
            abs_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            abs_.setFile(_page.getCurrentFile());
            //method name len
            abs_.buildError(
                    _page.getAnalysisMessages().getAbstractMethodRef(),
                    _id.getRealClass(),
                    _id.getRealId().getSignature(_page.getDisplayedStrings()));
            _page.getLocalizer().addError(abs_);
            addErr(abs_.getBuiltError());
        }
    }

    private static boolean isAbstract(boolean _staticChoiceMethod, ClassMethodIdReturn _id) {
        return _staticChoiceMethod && _id.isAbstractMethod();
    }

    private void processInstance(StringList _args, int _len, AnalyzedPageEl _page) {
        StringList methodTypes_ = new StringList();
        int vararg_ = -1;
        MethodId argsRes_;
        ConstructorId feed_ = null;
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordId_ = keyWords_.getKeyWordId();
        checkAccessStatic(_page);
        String clFrom_;
        AnaFormattedRootBlock form_;
        if (!isIntermediateDottedOperation()) {
            int offset_ = className.indexOf('(') + 1;
            offset_ += StringExpUtil.getOffset(_args.first());
            AnaResultPartType result_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_, _args.first().trim(), _page);
            partOffsetsBegin.add(result_);
            clFrom_ = result_.getResult(_page);
            if (clFrom_.startsWith(ARR)) {
                processArray(_args, _len, _page, clFrom_.substring(ARR.length()));
                return;
            }
            form_ = new AnaFormattedRootBlock(_page, clFrom_);
        } else {
            for (String o: previousResultClass.getNames()) {
                if (o.startsWith(ARR)) {
                    FoundErrorInterpret call_ = new FoundErrorInterpret();
                    call_.setFile(_page.getCurrentFile());
                    call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //key word len
                    call_.buildError(_page.getAnalysisMessages().getIllegalCtorArray(),
                            o);
                    _page.getLocalizer().addError(call_);
                    addErr(call_.getBuiltError());
                }
            }
            boolean wc_ = false;
            for (String o: previousResultClass.getNames()) {
                if (checkWildCards(o,_page)) {
                    wc_ = true;
                }
            }
            if (match(_args, _len, keyWordId_, 2)) {
                int offset_ = className.indexOf('(') + 1;
                offset_ += StringExpUtil.getOffset(_args.first());
                AnaResultPartType result_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_, _args.first().trim(), _page);
                partOffsetsBegin.add(result_);
                clFrom_ = result_.getResult(_page);
                form_ = new AnaFormattedRootBlock(_page, clFrom_);
            } else {
                StringMap<OwnerResultInfo> ownersMap_ = new StringMap<OwnerResultInfo>();
                String cl_ = _args.first().trim();
                String idClass_ = StringExpUtil.getIdFromAllTypes(cl_);
                if (!wc_) {
                    for (String o: previousResultClass.getNames()) {
                        OwnerListResultInfo owners_ = AnaTypeUtil.getGenericOwners(o, idClass_, _page);
                        if (owners_.onlyOneElt()) {
                            ownersMap_.put(o, owners_.firstElt());
                        }
                    }
                }
                if (ownersMap_.size() != 1) {
                    FoundErrorInterpret static_ = new FoundErrorInterpret();
                    static_.setFile(_page.getCurrentFile());
                    static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
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
        if (isIntermediateDottedOperation() && match(_args, _len, keyWordId_, 2)) {
            StringList innerParts_ = StringExpUtil.getAllPartInnerTypes(clFrom_);
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
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //key word len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(bounds_, ExportCst.JOIN_TYPES),
                        param_);
                _page.getLocalizer().addError(cast_);
                addErr(cast_.getBuiltError());
            }
        }
        boolean notint_ = !isIntermediateDottedOperation();
        int incr_;
        if (match(_args, _len, keyWordId_, 2)) {
            incr_ = 3;
        } else {
            incr_ = 2;
        }
        if (form_.getRootBlock() instanceof RecordBlock) {
            processRecord(notint_,incr_,_args, _len, _page, form_);
            return;
        }
        if (match(_args, _len, keyWordId_, 2)) {
            String cl_ = StringExpUtil.getIdFromAllTypes(clFrom_);
            argsRes_ = resolveArguments(false,incr_, cl_, MethodAccessKind.INSTANCE, _args, _page);
            if (argsRes_ == null) {
                return;
            }
            feed_ = MethodId.to(cl_, argsRes_);
            ko(clFrom_, argsRes_, methodTypes_, _page);
        } else {
            argsRes_ = resolveArguments(incr_, _args, _page);
            if (argsRes_ == null) {
                return;
            }
            vararg_ = vararg(_len, methodTypes_, vararg_, argsRes_, incr_);
        }
        processCtor(notint_,methodTypes_,vararg_,feed_, clFrom_, _page);

    }

    private void checkAccessStatic(AnalyzedPageEl _page) {
        if (isStaticAccess(getParent())) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
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
        int offsetArg_ = className.indexOf('(')+1-getClassNameOffset();
        for (int i = 0; i < _from; i++) {
            offsetArg_ += _args.get(i).length() + 1;
        }
        for (int i = _from; i < _len; i++) {
            String arg_ = _args.get(i);
            String name_ = arg_.trim();
            String fieldName_;
            ClassField idField_ = null;
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
            if (search_ != null) {
                RootBlock rootBlock_ = search_.getRootBlock();
                for (InfoBlock f: rootBlock_.getFieldsInstBlocks()) {
                    String imp_ = f.getImportedClassName();
                    String formImp_ = AnaInherits.quickFormat(search_, imp_);
                    String par_ = AnaInherits.quickFormat(h_, clFrom_, formImp_);
                    int index_ = AnaTypeUtil.getIndex(f,fieldName_);
                    if (index_ >= 0) {
                        idField_ = new ClassField(rootBlock_.getFullName(),fieldName_);
                        types_.add(par_);
                        offsets.add(e_);
                        refs.add(index_);
                        namedFields.add(new AnaNamedFieldContent(fieldName_,imp_,rootBlock_.getFullName(),rootBlock_));
                        break;
                    }
                }
            }

            if (idField_ == null) {
                offsets.add(e_);
                refs.add(-1);
                namedFields.add(new AnaNamedFieldContent(name_,"","",null));
                String id_ = StringExpUtil.getIdFromAllTypes(clFrom_);
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFile(_page.getCurrentFile());
                call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //_fromType len
                call_.buildError(_page.getAnalysisMessages().getIllegalCtorAbstract(),
                        id_);
                _page.getLocalizer().addError(call_);
                addErr(call_.getBuiltError());
            } else if (dupl(names_,idField_)) {
                String id_ = StringExpUtil.getIdFromAllTypes(clFrom_);
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFile(_page.getCurrentFile());
                call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //_fromType len
                call_.buildError(_page.getAnalysisMessages().getIllegalCtorAbstract(),
                        id_);
                _page.getLocalizer().addError(call_);
                addErr(call_.getBuiltError());
            } else {
                names_.add(idField_);
            }
            offsetArg_ += arg_.length()+1;
        }
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

    private String buildTypeName(String _arg, AnalyzedPageEl _page, String _cl, OwnerResultInfo _sup) {
        String idClass_ = StringExpUtil.getIdFromAllTypes(_cl);
        int offset_ = className.indexOf('(')+1;
        offset_ += StringExpUtil.getOffset(_arg);
        String inner_ = StringExpUtil.getIdFromAllTypes(_sup.getOwnedName());
        RootBlock root_ = _sup.getOwned();
        AccessedBlock r_ = _page.getImporting();
        partOffsetsPre.add(PreLinkagePartTypeUtil.processAccessOkRootAnalyze(idClass_,root_,inner_,r_, _page.getLocalizer().getCurrentLocationIndex()+offset_, _page));
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

    private static int vararg(int _len, StringList _methodTypes, int _vararg, MethodId _argsRes, int _i) {
        int vararg_ = _vararg;
        if (_argsRes.isVararg()) {
            vararg_ = _len - _i;
        }
        _methodTypes.addAllElts(IdentifiableUtil.params(_argsRes));
        return vararg_;
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
            call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
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
        undefined_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
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
        StringList str_;
        if (_len < 3) {
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFile(_page.getCurrentFile());
            badCall_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            badCall_.buildError(_page.getAnalysisMessages().getSplitComa(),
                    Long.toString(3),
                    Long.toString(_len)
            );
            _page.getLocalizer().addError(badCall_);
            int i_ = _page.getLocalizer().getCurrentLocationIndex();
            errPart(badCall_, i_, _page.getKeyWords().getKeyWordLambda().length());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        String fieldName_ = _args.get(2).trim();
        int sum_ = className.indexOf('(')+1;
        sum_ += _args.first().length() + 1;
        sum_ += _args.get(1).length() + 1;
        if (!isIntermediateDottedOperation()) {
            String resolved_ = resolveSingleTypeExact(_args, _page);
            str_ = InvokingOperation.getBounds(resolved_, _page);
            int i_ = 3;
            RootBlock root_ = _page.getAnaClassBody(resolved_);
            if (root_ != null) {
                lambdaMemberNumberContentId = new MemberId();
                lambdaMemberNumberContentId.setRootNumber(root_.getNumberAll());
            }
            boolean accessBase_ = true;
            boolean accessSuper_ = true;
            KeyWords keyWords_ = _page.getKeyWords();
            String keyWordThis_ = keyWords_.getKeyWordThis();
            String keyWordNull_ = keyWords_.getKeyWordNull();
            String keyWordSuper_ = keyWords_.getKeyWordSuper();
            String keyWordThat_ = keyWords_.getKeyWordThat();
            String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
            String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
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
            if (i_ < _len && StringUtil.quickEq(fieldName_, keyWordSuper_)) {
                fieldName_ = _args.get(i_).trim();
                sum_ += _args.get(i_-1).length() + 1;
                sum_ += StringExpUtil.getOffset(_args.get(i_));
                i_++;
                accessBase_ = false;
            } else if (i_ < _len && StringUtil.quickEq(fieldName_, keyWordThat_)) {
                fieldName_ = _args.get(i_).trim();
                sum_ += _args.get(i_-1).length() + 1;
                sum_ += StringExpUtil.getOffset(_args.get(i_));
                i_++;
            } else if (i_ < _len && StringUtil.quickEq(fieldName_, keyWordClasschoice_)) {
                fieldName_ = _args.get(i_).trim();
                sum_ += _args.get(i_-1).length() + 1;
                sum_ += StringExpUtil.getOffset(_args.get(i_));
                i_++;
                accessSuper_ = false;
            } else if (i_ < _len && StringUtil.quickEq(fieldName_, keyWordSuperaccess_)) {
                fieldName_ = _args.get(i_).trim();
                sum_ += _args.get(i_-1).length() + 1;
                sum_ += StringExpUtil.getOffset(_args.get(i_));
                i_++;
            } else {
                sum_ += StringExpUtil.getOffset(_args.get(2));
            }
            boolean aff_ = i_ < _len;
            AnaClassArgumentMatching fromCl_ = new AnaClassArgumentMatching(str_);
            ScopeFilter scope_ = new ScopeFilter(null, accessBase_, accessSuper_, false, _page.getGlobalClass());
            FieldResult r_ = resolveDeclaredCustField(false, fromCl_, fieldName_, false, aff_, _page, scope_);
            if (r_.getStatus() == SearchingMemberStatus.ZERO) {
                buildErrLambda(sum_,fromCl_,fieldName_,_page);
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            updateFieldInfos(aff_, r_);
            boolean static_ = r_.getContent().isStaticField();
            lambdaCommonContent.setShiftArgument(!static_);
            StringList params_ = buildParamsField(_args, _page, i_, aff_, r_);
            String fct_ = formatFieldReturn(static_, params_, r_, lambdaCommonContent.isShiftArgument(), _page);
            lambdaCommonContent.setResult(fct_);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        if (isStaticAccess(_m)) {
            str_ = resolveCorrectTypes(false, _args, _page);
            int i_ = 3;
            boolean aff_ = i_ < _len;
            AnaClassArgumentMatching fromCl_ = new AnaClassArgumentMatching(str_);
            sum_ += StringExpUtil.getOffset(_args.get(2));
            ScopeFilter scope_ = new ScopeFilter(null, true, true, false, _page.getGlobalClass());
            FieldResult r_ = resolveDeclaredCustField(true, fromCl_, fieldName_, false, aff_, _page, scope_);
            if (r_.getStatus() == SearchingMemberStatus.ZERO) {
                buildErrLambda(sum_,fromCl_,fieldName_,_page);
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            updateFieldInfos(aff_, r_);
            StringList params_ = buildParamsField(_args, _page, i_, aff_, r_);
            String fct_ = formatFieldReturn(true, params_, r_, false, _page);
            lambdaCommonContent.setResult(fct_);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        String resolved_ = resolveSingleTypeExact(_args, _page);
        str_ = InvokingOperation.getBounds(resolved_, _page);
        int i_ = 3;
        boolean accessBase_ = true;
        boolean accessSuper_ = true;
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordThis_ = keyWords_.getKeyWordThis();
        String keyWordNull_ = keyWords_.getKeyWordNull();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
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
            Mapping map_ = new Mapping();
            map_.setArg(new AnaClassArgumentMatching(bounds_));
            map_.setParam(new AnaClassArgumentMatching(str_));
            StringMap<StringList> maps_ = new StringMap<StringList>();
            getRefConstraints(maps_, _page);
            map_.setMapping(maps_);
            if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFile(_page.getCurrentFile());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //key word len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(bounds_,ExportCst.JOIN_TYPES),
                        StringUtil.join(str_,ExportCst.JOIN_TYPES));
                _page.getLocalizer().addError(cast_);
                addErr(cast_.getBuiltError());
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
        if (i_ < _len && StringUtil.quickEq(fieldName_, keyWordSuper_)) {
            fieldName_ = _args.get(i_).trim();
            sum_ += _args.get(i_-1).length() + 1;
            sum_ += StringExpUtil.getOffset(_args.get(i_));
            i_++;
            accessBase_ = false;
        } else if (i_ < _len && StringUtil.quickEq(fieldName_, keyWordThat_)) {
            fieldName_ = _args.get(i_).trim();
            sum_ += _args.get(i_-1).length() + 1;
            sum_ += StringExpUtil.getOffset(_args.get(i_));
            i_++;
        } else if (i_ < _len && StringUtil.quickEq(fieldName_, keyWordClasschoice_)) {
            fieldName_ = _args.get(i_).trim();
            sum_ += _args.get(i_-1).length() + 1;
            sum_ += StringExpUtil.getOffset(_args.get(i_));
            i_++;
            accessSuper_ = false;
        } else if (i_ < _len && StringUtil.quickEq(fieldName_, keyWordSuperaccess_)) {
            fieldName_ = _args.get(i_).trim();
            sum_ += _args.get(i_-1).length() + 1;
            sum_ += StringExpUtil.getOffset(_args.get(i_));
            i_++;
        } else {
            sum_ += StringExpUtil.getOffset(_args.get(2));
        }
        Mapping map_ = new Mapping();
        map_.setArg(new AnaClassArgumentMatching(bounds_));
        map_.setParam(new AnaClassArgumentMatching(str_));
        StringMap<StringList> maps_ = new StringMap<StringList>();
        getRefConstraints(maps_, _page);
        map_.setMapping(maps_);
        if (!AnaInherits.isCorrectOrNumbers(map_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(bounds_,ExportCst.JOIN_TYPES),
                    StringUtil.join(str_,ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(cast_);
            addErr(cast_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        boolean aff_ = i_ < _len;
        AnaClassArgumentMatching fromCl_ = new AnaClassArgumentMatching(str_);
        ScopeFilter scope_ = new ScopeFilter(null, accessBase_, accessSuper_, false, _page.getGlobalClass());
        FieldResult r_ = resolveDeclaredCustField(false, fromCl_, fieldName_, false, aff_, _page, scope_);
        if (r_.getStatus() == SearchingMemberStatus.ZERO) {
            buildErrLambda(sum_,fromCl_,fieldName_,_page);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        updateFieldInfos(aff_, r_);
        boolean static_ = r_.getContent().isStaticField();
        StringList params_ = buildParamsField(_args, _page, i_, aff_, r_);
        String fct_ = formatFieldReturn(static_, params_, r_, false, _page);
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
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFile(_page.getCurrentFile());
            cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //field name len
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    _arg,
                    _out);
            _page.getLocalizer().addError(cast_);
            addErr(cast_.getBuiltError());
        }
    }

    private int offset(StringList _args, int _i) {
        int offset_ = className.indexOf('(') + 1;
        for (int i = 0; i < _i; i++) {
            offset_ += _args.get(i).length();
            offset_++;
        }
        offset_ += StringExpUtil.getOffset(_args.get(_i));
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
        int i_ = _page.getLocalizer().getCurrentLocationIndex() + _offset;
        FoundErrorInterpret access_ = new FoundErrorInterpret();
        access_.setFile(_page.getCurrentFile());
        access_.setIndexFile(i_);
        //_name len
        access_.buildError(_page.getAnalysisMessages().getUndefinedAccessibleField(),
                _name,
                StringUtil.join(_class.getNames(),ExportCst.JOIN_TYPES));
        _page.getLocalizer().addError(access_);
        addBuiltErr(access_,i_,_name);
    }

    private void addBuiltErr(FoundErrorInterpret _err, int _i, String _name) {
        errPart(_err, _i, Math.max(1, _name.length()));
    }

    private static String getParentType(String _converted, AnalyzedPageEl _page) {
        if (_converted.startsWith(AnaTemplates.ARR_BEG_STRING)) {
            return _page.getAliasObject();
        }
        StringList rs_ = ParentInstanceOperation.getParentTypeList(new StringList(_converted), _page);
        return rs_.first();
    }
    private String resolveSingleTypeExact(StringList _args, AnalyzedPageEl _page) {
        int offset_ = className.indexOf('(')+1;
        offset_ += StringExpUtil.getOffset(_args.first());
        AnaResultPartType result_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_, _args.first().trim(), _page);
        String type_ = result_.getResult(_page);
        partOffsetsBegin.add(result_);
        return type_;
    }

    private void checkFinal(AnalyzedPageEl _page, boolean _finalField, ClassField _classField) {
        if (_finalField) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
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
        StringList methodTypes_ = new StringList();
        if (isIntermediateDottedOperation() && !previousResultClass.getNames().onlyOneElt()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    StringUtil.join(previousResultClass.getNames(),ExportCst.JOIN_TYPES));
            _page.getLocalizer().addError(un_);
            int i_ = _page.getLocalizer().getCurrentLocationIndex();
            errPart(un_, i_, _page.getKeyWords().getKeyWordLambda().length());
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
            int offset_ = sum_ + 1;
            offset_ += StringExpUtil.getOffset(_args.get(1));
            if (operator_.isEmpty()) {
                offset_--;
            }
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
            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
            badMeth_.setFile(_page.getCurrentFile());
            badMeth_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            badMeth_.buildError(_page.getAnalysisMessages().getBadOperatorName(),
                    operator_);
            _page.getLocalizer().addError(badMeth_);
            int j_ = _page.getLocalizer().getCurrentLocationIndex()+sum_;
            if (!operator_.isEmpty()&&displayErr_) {
                j_++;
            }
            int lenErr_= Math.max(1, operator_.length());
            if (!displayErr_) {
                lenErr_ = 1;
            }
            errPart(badMeth_, j_, lenErr_);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordId_ = keyWords_.getKeyWordId();
        int vararg_ = -1;
        MethodId argsRes_;
        ClassMethodId feed_ = null;
        int j_ = i_;
        MethodAccessKind staticFlag_ = MethodAccessKind.STATIC;
        if (match(_args, _len, keyWordId_, j_)) {
            i_++;
            MethodAccessId idUpdate_ = new MethodAccessId(i_);
            String keyWordStatic_ = _page.getKeyWords().getKeyWordStatic();
            String keyWordStaticCall_ = _page.getKeyWords().getKeyWordStaticCall();
            idUpdate_.setupInfosId(i_,_args,keyWordStatic_,keyWordStaticCall_);
            boolean retRef_ = idUpdate_.isRetRef();
            staticFlag_ = idUpdate_.getKind();
            int k_ = idUpdate_.getIndex();
            if (k_ == i_) {
                staticFlag_ = MethodAccessKind.STATIC;
            }
            i_ = idUpdate_.getIndex();
            argsRes_ = resolveArguments(retRef_,i_, from_,staticFlag_, _args, _page);
        } else {
            argsRes_ = resolveArguments(i_, _args, _page);
        }
        if (argsRes_ == null) {
            return;
        }
        checkAccessStatic(_page);
        if (isIntermediateDottedOperation()) {
            if (match(_args, _len, keyWordId_, j_)) {
                methodTypes_.add(previousResultClass.getName());
                feed_ = new ClassMethodId(from_, argsRes_.prepend(operator_, previousResultClass.getName(),false));
                ko(from_, argsRes_, methodTypes_, _page);
            } else {
                methodTypes_.add(previousResultClass.getName());
                if (argsRes_.isVararg()) {
                    vararg_ = _len- i_+1;
                }
                methodTypes_.addAllElts(IdentifiableUtil.params(argsRes_));
                errOwner(from_, argsRes_, _page);
            }
            ClassMethodIdReturn id_ = getOperator(from_,methodTypes_, operator_, vararg_, feed_, _page);
            if (id_ == null) {
                FoundErrorInterpret undefined_ = new FoundErrorInterpret();
                undefined_.setFile(_page.getCurrentFile());
                undefined_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //_name len
                undefined_.buildError(_page.getAnalysisMessages().getUndefinedMethod(),
                        new MethodId(MethodAccessKind.STATIC, "", methodTypes_).getSignature(_page.getDisplayedStrings()));
                _page.getLocalizer().addError(undefined_);
                int k_ = _page.getLocalizer().getCurrentLocationIndex()+sum_+1;
                partOffsetsErrMiddle = new InfoErrorDto(undefined_.getBuiltError(),k_,Math.max(1,operator_.length()));
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            initIdMethod(id_);
            String fct_ = formatReturnOperator(true, id_, _page);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        if (match(_args, _len, keyWordId_, j_)) {
            feed_ = new ClassMethodId(from_, MethodId.to(staticFlag_, operator_, argsRes_));
            ko(from_, argsRes_, methodTypes_, _page);
        } else {
            if (argsRes_.isVararg()) {
                vararg_ = _len- i_;
            }
            methodTypes_.addAllElts(IdentifiableUtil.params(argsRes_));
            errOwner(from_, argsRes_, _page);
        }
        ClassMethodIdReturn id_ = getOperator(from_,methodTypes_, operator_, vararg_, feed_, _page);
        if (id_ == null) {
            FoundErrorInterpret undefined_ = new FoundErrorInterpret();
            undefined_.setFile(_page.getCurrentFile());
            undefined_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //_name len
            undefined_.buildError(_page.getAnalysisMessages().getUndefinedMethod(),
                    new MethodId(MethodAccessKind.STATIC, "", methodTypes_).getSignature(_page.getDisplayedStrings()));
            _page.getLocalizer().addError(undefined_);
            int k_ = _page.getLocalizer().getCurrentLocationIndex()+sum_+1;
            partOffsetsErrMiddle = new InfoErrorDto(undefined_.getBuiltError(),k_,Math.max(1,operator_.length()));
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        initIdMethod(id_);
        String fct_ = formatReturnOperator(false, id_, _page);
        setResultClass(new AnaClassArgumentMatching(fct_));
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

    private static ClassMethodIdReturn getOperator(String _from, StringList _methodTypes, String _operator, int _vararg, ClassMethodId _feed, AnalyzedPageEl _page) {
        if (!_from.isEmpty()) {
            if (_feed == null) {
                return tryGetDeclaredCustMethodLambda(-1, MethodAccessKind.STATIC_CALL,
                        new StringList(_from), _operator, false, true, null,
                        _methodTypes, _page);
            }
            return tryGetDeclaredCustMethodLambda(-1, MethodAccessKind.STATIC_CALL,
                    new StringList(_from), _operator, false, true, new ClassMethodIdAncestor(_feed,0),
                    _methodTypes, _page);
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
                un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
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
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFile(_page.getCurrentFile());
            badCall_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            badCall_.buildError(_page.getAnalysisMessages().getSplitComa(),
                    Long.toString(3),
                    Long.toString(_len)
            );
            _page.getLocalizer().addError(badCall_);
            addErr(badCall_.getBuiltError());
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
    private MethodId resolveArguments(boolean _retRef,int _from, String _fromType, MethodAccessKind _static, StringList _params, AnalyzedPageEl _page){
        StringList out_ = new StringList();
        CustList<Boolean> ref_ = new CustList<Boolean>();
        int len_ = _params.size();
        int vararg_ = -1;
        int off_ = className.indexOf('(')+1;
        for (int i = 0; i < len_; i++) {
            if (i < _from) {
                off_ += _params.get(i).length() + 1;
                continue;
            }
            String full_ = _params.get(i);
            int loc_ = StringExpUtil.getOffset(full_);
            if (full_.trim().isEmpty()) {
                loc_--;
            }
            String arg_ = full_.trim();
            boolean refParam_ = false;
            if (arg_.startsWith("~")) {
                arg_ = arg_.substring(1);
                loc_ += StringUtil.getFirstPrintableCharIndex(arg_)+1;
                arg_ = arg_.trim();
                refParam_ = true;
            }
            String type_;
            if (arg_.endsWith(VARARG_SUFFIX)) {
                if (i + 1 != len_) {
                    //last type => error
                    FoundErrorInterpret varg_ = new FoundErrorInterpret();
                    varg_.setFile(_page.getCurrentFile());
                    int i_ = off_ + _page.getLocalizer().getCurrentLocationIndex() + full_.lastIndexOf("...");
                    varg_.setIndexFile(i_);
                    //three dots
                    varg_.buildError(_page.getAnalysisMessages().getUnexpectedVararg());
                    _page.getLocalizer().addError(varg_);
                    setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
//                    ResolvingTypes.errOff(_page,partOffsets,varg_,i_,3);
                    errPart(varg_,i_,3);
//                    ResolvingTypes.errOff(_page,partOffsets,varg_,i_,3);
//                    partOffsets.add(new PartOffset(ExportCst.anchorErr(varg_.getBuiltError()),i_));
//                    partOffsets.add(new PartOffset(ExportCst.END_ANCHOR,i_+3));
                    return null;
                }
                vararg_ = len_- _from;
                type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length()).trim();
            } else {
                type_ = arg_;
            }
            AnaResultPartType resolved_ = ResolvingTypes.resolveCorrectAccessibleType(off_ + loc_, type_, _fromType, _page);
            partOffsets.add(resolved_);
            arg_ = resolved_.getResult(_page);
            off_ += _params.get(i).length() + 1;
            out_.add(arg_);
            ref_.add(refParam_);
        }
        return new MethodId(_retRef,_static, EMPTY_STRING, out_,ref_, vararg_ != -1);
    }
    private MethodId resolveArguments(int _from, StringList _params, AnalyzedPageEl _page){
        StringList out_ = new StringList();
        CustList<Boolean> ref_ = new CustList<Boolean>();
        int len_ = _params.size();
        int vararg_ = -1;
        int offset_ = className.indexOf('(')+1;
        for (int i = 0; i < len_; i++) {
            if (i < _from) {
                offset_ += _params.get(i).length() + 1;
                continue;
            }
            String param_ = _params.get(i);
            int loc_ = StringExpUtil.getOffset(param_);
            if (param_.trim().isEmpty()) {
                loc_--;
            }
            String arg_ = param_.trim();
            boolean refParam_ = false;
            String pref_ = "";
            if (arg_.startsWith("~")) {
                arg_ = arg_.substring(1);
                loc_ += StringUtil.getFirstPrintableCharIndex(arg_)+1;
                arg_ = arg_.trim();
                refParam_ = true;
                pref_ = "~";
            }
            String type_;
            boolean wrap_ = false;
            if (arg_.endsWith(VARARG_SUFFIX)) {
                if (i + 1 != len_) {
                    //last type => error
                    FoundErrorInterpret varg_ = new FoundErrorInterpret();
                    varg_.setFile(_page.getCurrentFile());
                    int i_ = offset_ + _page.getLocalizer().getCurrentLocationIndex() + param_.lastIndexOf("...");
                    varg_.setIndexFile(i_);
                    //three dots
                    varg_.buildError(_page.getAnalysisMessages().getUnexpectedVararg());
                    _page.getLocalizer().addError(varg_);
                    setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                    errPart(varg_,i_,3);
//                    ResolvingTypes.errOff(_page,partOffsets,varg_,i_,3);
//                    partOffsets.add(new PartOffset(ExportCst.anchorErr(varg_.getBuiltError()),i_));
//                    partOffsets.add(new PartOffset(ExportCst.END_ANCHOR,i_+3));
                    return null;
                }
                wrap_ = true;
                vararg_ = len_- _from;
                type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length());
            } else {
                type_ = arg_;
            }
            AnaResultPartType resolved_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_ + loc_, type_, _page);
            partOffsets.add(resolved_);
            arg_ = resolved_.getResult(_page);
            if (wrap_) {
                arg_ = StringExpUtil.getPrettyArrayType(arg_);
            }
            offset_ += param_.length() + 1;
            out_.add(pref_+arg_);
            ref_.add(refParam_);
        }
        return new MethodId(false,MethodAccessKind.INSTANCE, EMPTY_STRING, out_,ref_, vararg_ != -1);
    }

    private StringList resolveCorrectTypes(boolean _exact, StringList _args, AnalyzedPageEl _page) {
        int offset_ = className.indexOf('(')+1;
        offset_ += StringExpUtil.getOffset(_args.first());
        String type_ = resolveCorrectTypeAccessible(_exact, _args.first().trim(), _page, offset_,partOffsetsBegin);
        return InvokingOperation.getBounds(type_, _page);
    }

    private static String resolveCorrectTypeAccessible(boolean _exact, String _type, AnalyzedPageEl _page, int _offset, CustList<AnaResultPartType> _dest) {
        if (_exact) {
            AnaResultPartType result_ = ResolvingTypes.resolveCorrectTypeAccessible(_offset, _type, _page);
            _dest.add(result_);
            return result_.getResult(_page);
        }
        ResolvedIdType resolvedIdType_ = ResolvingTypes.resolveAccessibleIdTypeBlock(_offset, _type, _page);
        _dest.add(resolvedIdType_.getDels());
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
        String result_ = appendParts(_page, _id.getReturnType(), _id.getRealClass(), _id.getRealId(), _id.getId().getConstraints(), _id.getId().getClassName(), _shift);
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

    static String appendParts(AnalyzedPageEl _page, String _returnType, String _realClass, MethodId _realId, MethodId _constraints, String _found, boolean _add) {
        StringList paramsReturn_ = beginReturn(_found, _add);
        return appendParts(_page, _returnType, _realClass, _realId, _constraints, paramsReturn_, 0);
    }

    private static String appendParts(AnalyzedPageEl _page, String _returnType, String _realClass, MethodId _realId, MethodId _constraints, StringList _paramsReturn, int _start) {
        IdentifiableUtil.appendLeftPart(_start, _paramsReturn, _constraints);
        appendRightPart(_paramsReturn, _page, _realClass, _realId);
        if (_constraints.isRetRef()) {
            _paramsReturn.add("~"+ _returnType);
        } else {
            _paramsReturn.add(_returnType);
        }
        String fctBase_ = _page.getAliasFct();
        return StringUtil.concat(fctBase_, StringExpUtil.TEMPLATE_BEGIN, StringUtil.join(_paramsReturn, StringExpUtil.TEMPLATE_SEP), StringExpUtil.TEMPLATE_END);
    }

    private static void appendRightPart(StringList _paramsReturn, AnalyzedPageEl _page, String _realClass, MethodId _realId) {
        if (StringUtil.quickEq(_realId.getName(),"[]=")) {
            CustList<NamedCalledFunctionBlock> getIndexers_ = new CustList<NamedCalledFunctionBlock>();
            String idCl_ = StringExpUtil.getIdFromAllTypes(_realClass);
            for (AbsBk b: ClassesUtil.getDirectChildren(_page.getAnaClassBody(idCl_))) {
                if (!AbsBk.isOverBlock(b)) {
                    continue;
                }
                NamedCalledFunctionBlock i_ = (NamedCalledFunctionBlock) b;
                if (i_.getKind() != MethodKind.GET_INDEX) {
                    continue;
                }
                if (!i_.getId().eqPartial(_realId)) {
                    continue;
                }
                getIndexers_.add(i_);
            }
            if (getIndexers_.size() == 1) {
                NamedCalledFunctionBlock matching_ = getIndexers_.first();
                String importedReturnType_ = matching_.getImportedReturnType();
                importedReturnType_ = AnaInherits.wildCardFormatReturn(_realClass, importedReturnType_, _page);
                _paramsReturn.add(importedReturnType_);
            }
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
        String result_ = appendParts(_page, returnType_, _id.getRealClass(), _id.getRealId(), id_, paramsReturn_, start_);
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

    public int getClassNameOffset() {
        return StringExpUtil.getOffset(className);
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
}
