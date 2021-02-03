package code.expressionlanguage.analyze.opers;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.analyze.opers.util.*;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.util.ClassMethodIdAncestor;
import code.expressionlanguage.analyze.util.ClassMethodIdReturn;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.MethodAccessId;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.*;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.fwd.opers.AnaLambdaCommonContent;
import code.expressionlanguage.fwd.opers.AnaLambdaFieldContent;
import code.expressionlanguage.fwd.opers.AnaLambdaMethodContent;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.expressionlanguage.options.KeyWords;
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
    private final StringMap<String> infos = new StringMap<String>();
    private final Ints offsets = new Ints();
    private final StringList named = new StringList();
    private final Ints refs = new Ints();
    private boolean recordType;
    private RootBlock fieldType;
    private AnaTypeFct function;
    private ClassField fieldId;
    private final AnaLambdaFieldContent lambdaFieldContent;
    private int valueOffset;
    private final CustList<PartOffset> partOffsets = new CustList<PartOffset>();
    private final CustList<PartOffset> partOffsetsEnd = new CustList<PartOffset>();
    private StandardMethod standardMethod;
    private StandardType standardType;

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
                Block cur_ = _page.getCurrentBlock();
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
                    if (AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
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
                        badCall_.setFileName(_page.getLocalizer().getCurrentFileName());
                        badCall_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                        //last parenthesis
                        badCall_.buildError(_page.getAnalysisMessages().getSplitComa(),
                                Long.toString(2),
                                Long.toString(len_)
                        );
                        _page.getLocalizer().addError(badCall_);
                        int i_ = _page.getLocalizer().getCurrentLocationIndex()+className.lastIndexOf(')');
                        partOffsetsEnd.add(new PartOffset("<a title=\""+LinkageUtil.transform(badCall_.getBuiltError()) +"\" class=\"e\">",i_));
                        partOffsetsEnd.add(new PartOffset("</a>",i_+1));
                        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                        return;
                    }
                    if (StringUtil.quickEq(args_.first().trim(),new_)) {
                        boolean cloneArray_ = cloneArray(bounds_);
                        StringList a_ = new StringList();
                        getArrayBounds(bounds_, a_);
                        String prev_ = previousResultClass.getSingleNameOrEmpty();
                        if (cloneArray_) {
                            StringList parts_ = new StringList();
                            lambdaCommonContent.setFoundClass(prev_);
                            realId = new ConstructorId(prev_, parts_, true);
                            parts_.add(_page.getAliasPrimInteger());
                            parts_.add(prev_);
                            StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
                            fct_.append(Templates.TEMPLATE_BEGIN);
                            fct_.append(StringUtil.join(parts_, Templates.TEMPLATE_SEP));
                            fct_.append(Templates.TEMPLATE_END);
                            setResultClass(new AnaClassArgumentMatching(fct_.toString()));
                            return;
                        }
                        stCall_.check(_page);
                        String id_ = StringExpUtil.getIdFromAllTypes(prev_);
                        AnaGeneType h_ = _page.getAnaGeneType(id_);
                        if (noDefCtor(h_)) {
                            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
                            badCall_.setFileName(_page.getLocalizer().getCurrentFileName());
                            badCall_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                            //last parenthesis
                            badCall_.buildError(_page.getAnalysisMessages().getSplitComa(),
                                    Long.toString(2),
                                    Long.toString(len_)
                            );
                            _page.getLocalizer().addError(badCall_);
                            int i_ = _page.getLocalizer().getCurrentLocationIndex()+className.lastIndexOf(')');
                            partOffsetsEnd.add(new PartOffset("<a title=\""+LinkageUtil.transform(badCall_.getBuiltError()) +"\" class=\"e\">",i_));
                            partOffsetsEnd.add(new PartOffset("</a>",i_+1));
                            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                            return;
                        }
                        StringMap<StringList> mapCtr_ = _page.getCurrentConstraints().getCurrentConstraints();
                        CustList<ConstrustorIdVarArg> ctors_ = new CustList<ConstrustorIdVarArg>();

                        for (String s: candidates_) {
                            StringList allTypes_ = StringExpUtil.getAllTypes(s);
                            if (allTypes_.size() == 1) {
                                if (noCtor(h_)) {
                                    if (!stCall_.getStCall().isEmpty()) {
                                        continue;
                                    }
                                    ConstrustorIdVarArg out_;
                                    out_ = new ConstrustorIdVarArg();
                                    out_.setRealId(new ConstructorId(prev_, new StringList(),false));
                                    out_.setConstId(out_.getRealId());
                                    setupContainer(h_, out_);
                                    ctors_.add(out_);
                                    continue;
                                }
                                tryAddCtor(_page, prev_, id_, h_, ctors_, null,stCall_.getStCall(),"");
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
                                    if (!AnaTemplates.isCorrectOrNumbers(map_, _page)) {
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
                                    ConstrustorIdVarArg out_;
                                    out_ = new ConstrustorIdVarArg();
                                    out_.setRealId(new ConstructorId(real_, new StringList(),false));
                                    out_.setConstId(out_.getRealId());
                                    setupContainer(h_, out_);
                                    ctors_.add(out_);
                                    continue;
                                }
                            }
                            tryAddCtor(_page, prev_, id_, h_, ctors_, argsTypes_,stCall_.getStCall(),ret_);
                        }
                        ctors_ = AnaTemplates.reduceCtors(ctors_);
                        if (ctors_.size() == 1) {
                            ConstrustorIdVarArg ctorRes_ = ctors_.first();
                            realId = ctorRes_.getRealId();
                            function = ctorRes_.getPair();
                            standardType = ctorRes_.getStandardType();
                            lambdaCommonContent.setFileName(ctorRes_.getFileName());
                            lambdaMemberNumberContentId = ctorRes_.getMemberId();
                            ConstructorId fid_ = ctorRes_.getConstId();
                            StringList parts_ = new StringList();
                            if (!h_.isStaticType()) {
                                //From analyze
                                StringList innerParts_ = StringExpUtil.getAllPartInnerTypes(fid_.getName());
                                parts_.add(StringUtil.join(innerParts_.left(innerParts_.size() - 2), ""));
                            }

                            appendArgsCtor(fid_, parts_);
                            lambdaCommonContent.setFoundClass(fid_.getName());
                            parts_.add(fid_.getName());
                            StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
                            fct_.append(Templates.TEMPLATE_BEGIN);
                            fct_.append(StringUtil.join(parts_, Templates.TEMPLATE_SEP));
                            fct_.append(Templates.TEMPLATE_END);
                            setResultClass(new AnaClassArgumentMatching(fct_.toString()));
                            return;
                        }
                        FoundErrorInterpret badCall_ = new FoundErrorInterpret();
                        badCall_.setFileName(_page.getLocalizer().getCurrentFileName());
                        badCall_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                        //last parenthesis
                        badCall_.buildError(_page.getAnalysisMessages().getSplitComa(),
                                Long.toString(2),
                                Long.toString(len_)
                        );
                        _page.getLocalizer().addError(badCall_);
                        int i_ = _page.getLocalizer().getCurrentLocationIndex()+className.lastIndexOf(')');
                        partOffsetsEnd.add(new PartOffset("<a title=\""+LinkageUtil.transform(badCall_.getBuiltError()) +"\" class=\"e\">",i_));
                        partOffsetsEnd.add(new PartOffset("</a>",i_+1));
                        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                        return;
                    }
                    boolean cloneArray_ = cloneArray(bounds_);
                    StringList a_ = new StringList();
                    getArrayBounds(bounds_, a_);
                    if (cloneArray_) {
                        String name_ = args_.first();
                        if (koArrayMethod(name_, _page)) {
                            tryCheck(_page);
                            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
                            badCall_.setFileName(_page.getLocalizer().getCurrentFileName());
                            badCall_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                            //last parenthesis
                            badCall_.buildError(_page.getAnalysisMessages().getSplitComa(),
                                    Long.toString(2),
                                    Long.toString(len_)
                            );
                            _page.getLocalizer().addError(badCall_);
                            int i_ = _page.getLocalizer().getCurrentLocationIndex()+className.lastIndexOf(')');
                            partOffsetsEnd.add(new PartOffset("<a title=\""+LinkageUtil.transform(badCall_.getBuiltError()) +"\" class=\"e\">",i_));
                            partOffsetsEnd.add(new PartOffset("</a>",i_+1));
                            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                            return;
                        }
                        lambdaMethodContent.setClonedMethod(true);
                        String foundClass_ = StringExpUtil.getPrettyArrayType(_page.getAliasObject());
                        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, name_, new StringList());
                        method = new ClassMethodId(foundClass_, id_);
                        lambdaCommonContent.setShiftArgument(true);
                        StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
                        fct_.append(Templates.TEMPLATE_BEGIN);
                        fct_.append(lambdaCommonContent.getFoundClass());
                        fct_.append(Templates.TEMPLATE_SEP);
                        fct_.append(lambdaCommonContent.getFoundClass());
                        fct_.append(Templates.TEMPLATE_END);
                        setResultClass(new AnaClassArgumentMatching(fct_.toString()));
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
                    //use types of previous operation
                    for (String s: candidates_) {
                        StringList allTypes_ = StringExpUtil.getAllTypes(s);
                        if (allTypes_.size() == 1) {
                            tryAddMeth(_page, name_, bounds_, resList_, null, "", MethodAccessKind.INSTANCE, stCall_.getStCall());
                            continue;
                        }
                        StringList argsTypes_ = new StringList(allTypes_.mid(1,allTypes_.size()-2));
                        String ret_ = allTypes_.last();
                        tryAddMeth(_page, name_, bounds_, resList_, argsTypes_, ret_, MethodAccessKind.STATIC_CALL, stCall_.getStCall());
                        if (!argsTypes_.isEmpty()&&!argsTypes_.first().startsWith("~")) {
                            Mapping mapp_ = new Mapping();
                            mapp_.setArg(argsTypes_.first());
                            mapp_.setParam(new AnaClassArgumentMatching(bounds_));
                            mapp_.setMapping(_page.getCurrentConstraints().getCurrentConstraints());
                            if (AnaTemplates.isCorrectOrNumbers(mapp_,_page)) {
                                argsTypes_.remove(0);
                                tryAddMeth(_page, name_, bounds_, resList_, argsTypes_, ret_, MethodAccessKind.INSTANCE, stCall_.getStCall());
                            }
                        }
                    }
                    resList_ = AnaTemplates.reduceMethods(resList_);
                    if (okList(resList_,!polymorph_)) {
                        ClassMethodIdReturn id_ = resList_.first();
                        standardMethod = id_.getStandardMethod();
                        trySetPoly(id_,polymorph_);
                        lambdaCommonContent.setReturnFieldType(id_.getOriginalReturnType());
                        lambdaCommonContent.setFileName(id_.getFileName());
                        lambdaMemberNumberContentId = id_.getMemberId();
                        function = id_.getPair();
                        String foundClass_ = id_.getRealClass();
                        if (!stCall_.getStCall().isEmpty()) {
                            ContextUtil.appendTitlePartsAbs(stCall_.getLt(), stCall_.getGt(), foundClass_, stCall_.getStCallSolved(), _page);
                        }
                        foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
                        lambdaCommonContent.setFoundClass(id_.getId().getClassName());
                        MethodId idCt_ = id_.getRealId();
                        method = new ClassMethodId(foundClass_, idCt_);
                        lambdaCommonContent.setAncestor(id_.getAncestor());
                        lambdaMethodContent.setAbstractMethod(id_.isAbstractMethod());
                        lambdaCommonContent.setShiftArgument(!id_.getRealId().isStaticMethod());
                        String fct_ = formatReturn(lambdaCommonContent.getFoundClass(), _page, id_.getReturnType(), id_.getRealClass(), id_.getRealId(), id_.getId().getConstraints());
                        setResultClass(new AnaClassArgumentMatching(fct_));
                        return;
                    }
                } else {
                    boolean cloneArray_ = cloneArray(bounds_);
                    StringList a_ = new StringList();
                    getArrayBounds(bounds_, a_);
                    if (cloneArray_) {
                        String name_ = args_.first();
                        if (koArrayMethod(name_, _page)) {
                            tryCheck(_page);
                            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
                            badCall_.setFileName(_page.getLocalizer().getCurrentFileName());
                            badCall_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                            //last parenthesis
                            badCall_.buildError(_page.getAnalysisMessages().getSplitComa(),
                                    Long.toString(2),
                                    Long.toString(len_)
                            );
                            _page.getLocalizer().addError(badCall_);
                            int i_ = _page.getLocalizer().getCurrentLocationIndex()+className.lastIndexOf(')');
                            partOffsetsEnd.add(new PartOffset("<a title=\""+LinkageUtil.transform(badCall_.getBuiltError()) +"\" class=\"e\">",i_));
                            partOffsetsEnd.add(new PartOffset("</a>",i_+1));
                            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                            return;
                        }
                        lambdaMethodContent.setClonedMethod(true);
                        String foundClass_ = StringExpUtil.getPrettyArrayType(_page.getAliasObject());
                        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, name_, new StringList());
                        method = new ClassMethodId(foundClass_, id_);
                        StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
                        fct_.append(Templates.TEMPLATE_BEGIN);
                        fct_.append(lambdaCommonContent.getFoundClass());
                        fct_.append(Templates.TEMPLATE_END);
                        setResultClass(new AnaClassArgumentMatching(fct_.toString()));
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
                    for (String s: candidates_) {
                        StringList allTypes_ = StringExpUtil.getAllTypes(s);
                        if (allTypes_.size() == 1) {
                            tryAddMeth(_page, name_, bounds_, resList_, null, "", MethodAccessKind.INSTANCE, "");
                            continue;
                        }
                        StringList argsTypes_ = new StringList(allTypes_.mid(1,allTypes_.size()-2));
                        String ret_ = allTypes_.last();
                        tryAddMeth(_page, name_, bounds_, resList_, argsTypes_, ret_, MethodAccessKind.INSTANCE, "");
                    }
                    resList_ = AnaTemplates.reduceMethods(resList_);
                    if (okList(resList_,!polymorph_)) {
                        ClassMethodIdReturn id_ = resList_.first();
                        trySetPoly(id_,polymorph_);
                        standardMethod = id_.getStandardMethod();
                        lambdaCommonContent.setReturnFieldType(id_.getOriginalReturnType());
                        lambdaCommonContent.setFileName(id_.getFileName());
                        lambdaMemberNumberContentId = id_.getMemberId();
                        function = id_.getPair();
                        String foundClass_ = id_.getRealClass();
                        foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
                        lambdaCommonContent.setFoundClass(id_.getId().getClassName());
                        lambdaMethodContent.setAbstractMethod(id_.isAbstractMethod());
                        MethodId idCt_ = id_.getRealId();
                        method = new ClassMethodId(foundClass_, idCt_);
                        lambdaCommonContent.setAncestor(id_.getAncestor());
                        String fct_ = formatReturn(_page, id_.getReturnType(), id_.getRealClass(), id_.getRealId(), id_.getId().getConstraints());
                        setResultClass(new AnaClassArgumentMatching(fct_));
                        return;
                    }
                }
            }
            tryCheck(_page);
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFileName(_page.getLocalizer().getCurrentFileName());
            badCall_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //last parenthesis
            badCall_.buildError(_page.getAnalysisMessages().getSplitComa(),
                    Long.toString(2),
                    Long.toString(len_)
            );
            _page.getLocalizer().addError(badCall_);
            int i_ = _page.getLocalizer().getCurrentLocationIndex()+className.lastIndexOf(')');
            partOffsetsEnd.add(new PartOffset("<a title=\""+LinkageUtil.transform(badCall_.getBuiltError()) +"\" class=\"e\">",i_));
            partOffsetsEnd.add(new PartOffset("</a>",i_+1));
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        tryCheck(_page);
        generalProcess(args_, _page);
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

    private static void tryAddMeth(AnalyzedPageEl _page, String _name, StringList _bounds, CustList<ClassMethodIdReturn> _resList, StringList _argsTypes, String _ret, MethodAccessKind _instance, String _stCall) {
        ClassMethodIdReturn resultOther_ = tryGetDeclaredCustMethodLambdaInfer(_instance, _bounds,
                _name, true, false, false, null,
                _stCall, _argsTypes, _page, _ret);
        if (resultOther_.isFoundMethod()) {
            _resList.add(resultOther_);
        }
    }

    private static void tryAddCtor(AnalyzedPageEl _page, String _prev, String _id, AnaGeneType _h, CustList<ConstrustorIdVarArg> _ctors, StringList _argsTypes, String _stCall, String _retType) {
        ConstructorInfo constructorInfo_ = tryGetSignaturesInfer(_id, _h, _page, _argsTypes, _prev,_stCall,_retType);
        if (constructorInfo_ != null) {
            _ctors.add(buildCtorInfo(constructorInfo_));
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
            processField(m_, _args, len_, fromType_, _page);
            return;
        }
        if (StringUtil.quickEq(name_, new_)) {
            processInstance(_args, len_, fromType_, _page);
            return;
        }
        processMethod(m_, _args, len_, fromType_,
                name_, _page);
    }

    private void processMethod(MethodOperation _m, StringList _args, int _len, String _fromType,
                               String _name, AnalyzedPageEl _page) {
        StringList str_;
        String name_ = _name;
        if (StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordExplicit())
                || StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordCast())
                || StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordTrue())
                || StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordFalse())) {
            CustList<AnaClassArgumentMatching> methodTypes_ = new CustList<AnaClassArgumentMatching>();
            String exp_;
            if (StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordExplicit())){
                exp_ = _page.getKeyWords().getKeyWordExplicit();
            } else if (StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordCast())) {
                exp_ = _page.getKeyWords().getKeyWordCast();
            } else if (StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordTrue())){
                exp_ = _page.getKeyWords().getKeyWordTrue();
            } else {
                exp_ = _page.getKeyWords().getKeyWordFalse();
            }
            int i_ = 2;
            ClassMethodId feed_ = null;
            KeyWords keyWords_ = _page.getKeyWords();
            String keyWordId_ = keyWords_.getKeyWordId();
            int offset_ = className.indexOf('(')+1;
            offset_ += StringExpUtil.getOffset(_args.first());
            if (StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordTrue())
                || StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordFalse())) {
                String type_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_,_fromType, _page);
                partOffsets.addAllElts(_page.getCurrentParts());
                ClassMethodIdReturn resMethod_;
                if (StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordTrue())){
                    resMethod_ = tryGetDeclaredTrue(type_, _page);
                } else {
                    resMethod_ = tryGetDeclaredFalse(type_, _page);
                }
                if (!resMethod_.isFoundMethod()) {
                    int rc_ = _page.getLocalizer().getCurrentLocationIndex() + offset_;
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_page.getLocalizer().getCurrentFileName());
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
                lambdaCommonContent.setReturnFieldType(resMethod_.getOriginalReturnType());
                lambdaCommonContent.setFileName(resMethod_.getFileName());
                lambdaMemberNumberContentId = resMethod_.getMemberId();
                function = resMethod_.getPair();
                String foundClass_ = resMethod_.getRealClass();
                foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
                lambdaCommonContent.setFoundClass(resMethod_.getId().getClassName());
                MethodId idCt_ = resMethod_.getRealId();
                method = new ClassMethodId(foundClass_, idCt_);
                lambdaCommonContent.setAncestor(resMethod_.getAncestor());
                lambdaMethodContent.setAbstractMethod(resMethod_.isAbstractMethod());
                lambdaCommonContent.setShiftArgument(false);
                String fct_ = formatReturn(_page, resMethod_.getReturnType(), resMethod_.getRealClass(), resMethod_.getRealId(), resMethod_.getId().getConstraints());
                setResultClass(new AnaClassArgumentMatching(fct_));
                return;
            }
            String type_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_,_fromType, _page);
            partOffsets.addAllElts(_page.getCurrentParts());
            MethodId argsRes_;
            if (matchIdKeyWord(_args, _len, i_, keyWordId_)) {
                String cl_ = StringExpUtil.getIdFromAllTypes(type_);
                argsRes_ = resolveArguments(false,i_+1, cl_, MethodAccessKind.STATIC, _args, _page);
                if (argsRes_ == null) {
                    return;
                }
                boolean varargFct_ = argsRes_.isVararg();
                StringList params_ = argsRes_.getParametersTypes();
                AnaGeneType geneType_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(type_));
                if (geneType_ == null) {
                    int rc_ = _page.getLocalizer().getCurrentLocationIndex() + offset_;
                    FoundErrorInterpret un_ = new FoundErrorInterpret();
                    un_.setFileName(_page.getLocalizer().getCurrentFileName());
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
                if (params_.size() < 2) {
                    params_.add(0, gene_);
                }
                feed_ = new ClassMethodId(type_, new MethodId(MethodAccessKind.STATIC, name_, params_, varargFct_));
                for (String s: argsRes_.getParametersTypes()) {
                    String format_ = AnaTemplates.wildCardFormatParam(type_, s, _page);
                    if (format_.isEmpty()) {
                        MethodId idCast_ = new MethodId(MethodAccessKind.STATIC,exp_,new StringList(_page.getAliasObject()));
                        lambdaCommonContent.setFoundClass(type_);
                        MethodId idCt_ = new MethodId(MethodAccessKind.STATIC,exp_,new StringList(_page.getAliasObject()));
                        method = new ClassMethodId(type_, idCt_);
                        lambdaCommonContent.setAncestor(0);
                        lambdaMethodContent.setAbstractMethod(false);
                        lambdaCommonContent.setShiftArgument(false);
                        lambdaMethodContent.setDirectCast(true);
                        String fct_ = formatReturn(_page, type_, type_, idCast_, idCast_);
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
                for (String s: argsRes_.getParametersTypes()) {
                    methodTypes_.add(new AnaClassArgumentMatching(s));
                }
            }
            if (argsRes_.getParametersTypes().size() > 2) {
                FoundErrorInterpret static_ = new FoundErrorInterpret();
                static_.setFileName(_page.getLocalizer().getCurrentFileName());
                static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //key word len
                static_.buildError(_page.getAnalysisMessages().getSplitComaLow(),
                        Long.toString(2),
                        Long.toString(argsRes_.getParametersTypes().size())
                );
                _page.getLocalizer().addError(static_);
                addErr(static_.getBuiltError());
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            if (argsRes_.getParametersTypes().size() > 1 && feed_ == null) {
                FoundErrorInterpret static_ = new FoundErrorInterpret();
                static_.setFileName(_page.getLocalizer().getCurrentFileName());
                static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //key word len
                static_.buildError(_page.getAnalysisMessages().getSplitComaLow(),
                        Long.toString(1),
                        Long.toString(argsRes_.getParametersTypes().size())
                );
                _page.getLocalizer().addError(static_);
                addErr(static_.getBuiltError());
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            if (!StringExpUtil.customCast(type_)) {
                MethodId idCast_ = new MethodId(MethodAccessKind.STATIC,exp_,new StringList(_page.getAliasObject()));
                lambdaCommonContent.setFoundClass(type_);
                MethodId idCt_ = new MethodId(MethodAccessKind.STATIC,exp_,new StringList(_page.getAliasObject()));
                method = new ClassMethodId(type_, idCt_);
                lambdaCommonContent.setAncestor(0);
                lambdaMethodContent.setAbstractMethod(false);
                lambdaCommonContent.setShiftArgument(false);
                lambdaMethodContent.setDirectCast(true);
                String fct_ = formatReturn(_page, type_, type_, idCast_, idCast_);
                setResultClass(new AnaClassArgumentMatching(fct_));
                return;
            }
            if (methodTypes_.size() < 2) {
                methodTypes_.add(0, new AnaClassArgumentMatching(type_));
            }
            ClassMethodIdReturn id_;
            if (StringUtil.quickEq(name_, _page.getKeyWords().getKeyWordExplicit())){
                id_ = tryGetDeclaredCast(type_, feed_, OperationNode.toArgArray(methodTypes_), _page);
            } else {
                id_ = tryGetDeclaredImplicitCast(type_, feed_, OperationNode.toArgArray(methodTypes_), _page);
            }
            if (!id_.isFoundMethod()) {
                MethodId idCast_;
                if (argsRes_.getParametersTypes().isEmpty()) {
                    idCast_ = new MethodId(MethodAccessKind.STATIC,exp_,new StringList(_page.getAliasObject()));
                } else {
                    idCast_ = new MethodId(MethodAccessKind.STATIC,exp_,new StringList(argsRes_.getParametersTypes()));
                }
                lambdaCommonContent.setFoundClass(type_);
                MethodId idCt_ = new MethodId(MethodAccessKind.STATIC,exp_,new StringList(_page.getAliasObject()));
                method = new ClassMethodId(type_, idCt_);
                lambdaCommonContent.setAncestor(0);
                lambdaMethodContent.setAbstractMethod(false);
                lambdaCommonContent.setShiftArgument(false);
                lambdaMethodContent.setDirectCast(true);
                String fct_ = formatReturn(_page, type_, type_, idCast_, idCast_);
                setResultClass(new AnaClassArgumentMatching(fct_));
                return;
            }
            lambdaMethodContent.setExpCast(true);
            lambdaCommonContent.setReturnFieldType(id_.getOriginalReturnType());
            lambdaCommonContent.setFileName(id_.getFileName());
            lambdaMemberNumberContentId = id_.getMemberId();
            function = id_.getPair();
            String foundClass_ = id_.getRealClass();
            foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
            lambdaCommonContent.setFoundClass(id_.getId().getClassName());
            MethodId idCt_ = id_.getRealId();
            method = new ClassMethodId(foundClass_, idCt_);
            lambdaCommonContent.setAncestor(id_.getAncestor());
            lambdaMethodContent.setAbstractMethod(id_.isAbstractMethod());
            lambdaCommonContent.setShiftArgument(false);
            String fct_ = formatReturn(_page, id_.getReturnType(), id_.getRealClass(), id_.getRealId(), id_.getId().getConstraints());
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        StringList methodTypes_ = new StringList();
        if (!isIntermediateDottedOperation()) {
            int i_ = 2;
            boolean staticChoiceMethod_ = false;
            boolean accessFromSuper_ = false;
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
                accessFromSuper_ = true;
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
                String type_ = resolveCorrectTypeAccessible(staticFlag_ != MethodAccessKind.STATIC, _fromType, _page, offset_);
                partOffsets.addAllElts(_page.getCurrentParts());
                str_ = InvokingOperation.getBounds(type_, _page);
                String cl_ = StringExpUtil.getIdFromAllTypes(type_);
                argsRes_ = resolveArguments(retRef_,i_+1, cl_, staticFlag_, _args, _page);
                if (argsRes_ == null) {
                    return;
                }
                feed_ = new ClassMethodIdAncestor(new ClassMethodId(cl_, MethodId.to(staticFlag_, name_, argsRes_)),idUpdate_.getAncestor());
                ko(type_, argsRes_, methodTypes_, _page);
            } else {
                str_ = resolveCorrectTypesExact(_fromType,_args, _page);
                argsRes_ = resolveArguments(i_, _args, _page);
                if (argsRes_ == null) {
                    return;
                }
                if (argsRes_.isVararg()) {
                    vararg_ = _len- i_;
                }
                for (String s: argsRes_.getParametersTypes()) {
                    methodTypes_.add(s);
                }
            }
            for (String s: str_) {
                errOwner(s,argsRes_,_page);
            }
            boolean cloneArray_ = cloneArray(str_);
            StringList a_ = new StringList();
            getArrayBounds(str_, a_);
            if (cloneArray_) {
                if (name_.startsWith("[]")) {
                    if (methodTypes_.isEmpty()) {
                        lambdaCommonContent.setShiftArgument(true);
                        String foundClass_ = StringExpUtil.getPrettyArrayType(_page.getAliasObject());
                        MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, name_, new StringList());
                        method = new ClassMethodId(foundClass_, id_);
                        StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
                        fct_.append(Templates.TEMPLATE_BEGIN);
                        String comp_ = lambdaCommonContent.getFoundClass();
                        fct_.append(comp_);
                        fct_.append(Templates.TEMPLATE_SEP);
                        fct_.append(_page.getAliasPrimInteger());
                        fct_.append(Templates.TEMPLATE_END);
                        setResultClass(new AnaClassArgumentMatching(fct_.toString()));
                        return;
                    }
                    String foundClass_ = StringExpUtil.getPrettyArrayType(_page.getAliasObject());
                    lambdaCommonContent.setShiftArgument(true);
                    StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
                    fct_.append(Templates.TEMPLATE_BEGIN);
                    String comp_ = lambdaCommonContent.getFoundClass();
                    fct_.append(comp_);
                    fct_.append(Templates.TEMPLATE_SEP);
                    boolean err_ = false;
                    StringList params_ = new StringList();
                    for (String p: methodTypes_) {
                        if (!AnaClassArgumentMatching.isNumericInt(p,_page)) {
                            FoundErrorInterpret un_ = new FoundErrorInterpret();
                            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                            un_.setFileName(_page.getLocalizer().getCurrentFileName());
                            //argument len
                            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                                    p);
                            _page.getLocalizer().addError(un_);
                            addErr(un_.getBuiltError());
                        }
                        String cp_ = comp_;
                        comp_ = StringExpUtil.getQuickComponentType(comp_);
                        if (comp_ == null) {
                            FoundErrorInterpret un_ = new FoundErrorInterpret();
                            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                            un_.setFileName(_page.getLocalizer().getCurrentFileName());
                            //argument len
                            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                                    cp_);
                            _page.getLocalizer().addError(un_);
                            addErr(un_.getBuiltError());
                            err_ = true;
                            break;
                        }
                        fct_.append(p);
                        params_.add(p);
                        fct_.append(Templates.TEMPLATE_SEP);
                    }
                    MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, name_, params_);
                    method = new ClassMethodId(foundClass_, id_);
                    if (err_) {
                        setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                        return;
                    }
                    if (StringUtil.quickEq(name_,"[]=")) {
                        fct_.append(comp_);
                        fct_.append(Templates.TEMPLATE_SEP);
                    }
                    fct_.append(comp_);
                    fct_.append(Templates.TEMPLATE_END);
                    setResultClass(new AnaClassArgumentMatching(fct_.toString()));
                    return;
                }
                if (checkArrayMethod(str_, name_, _page)) {
                    setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                    return;
                }
                lambdaMethodContent.setClonedMethod(true);
                String foundClass_ = StringExpUtil.getPrettyArrayType(_page.getAliasObject());
                MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, name_, new StringList());
                method = new ClassMethodId(foundClass_, id_);
                lambdaCommonContent.setShiftArgument(true);
                StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
                fct_.append(Templates.TEMPLATE_BEGIN);
                fct_.append(lambdaCommonContent.getFoundClass());
                fct_.append(Templates.TEMPLATE_SEP);
                fct_.append(lambdaCommonContent.getFoundClass());
                fct_.append(Templates.TEMPLATE_END);
                setResultClass(new AnaClassArgumentMatching(fct_.toString()));
                return;
            }
            ClassMethodIdReturn id_ = getDeclaredCustMethodLambda(this, vararg_,
                    MethodAccessKind.INSTANCE, str_, name_,
                    accessSuper_, accessFromSuper_, feed_, _page,
                    methodTypes_);
            if (!id_.isFoundMethod()) {
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            standardMethod = id_.getStandardMethod();
            lambdaCommonContent.setReturnFieldType(id_.getOriginalReturnType());
            lambdaCommonContent.setFileName(id_.getFileName());
            lambdaMemberNumberContentId = id_.getMemberId();
            function = id_.getPair();
            String foundClass_ = id_.getRealClass();
            foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
            lambdaCommonContent.setFoundClass(id_.getId().getClassName());
            MethodId idCt_ = id_.getRealId();
            method = new ClassMethodId(foundClass_, idCt_);
            lambdaCommonContent.setAncestor(id_.getAncestor());
            lambdaMethodContent.setAbstractMethod(id_.isAbstractMethod());
            lambdaCommonContent.setShiftArgument(!id_.getRealId().isStaticMethod());
            String fct_ = formatReturn(lambdaCommonContent.getFoundClass(), _page, id_.getReturnType(), id_.getRealClass(), id_.getRealId(), id_.getId().getConstraints());
            setResultClass(new AnaClassArgumentMatching(fct_));
            processAbstract(staticChoiceMethod_, id_, _page);
            return;
        }
        OperationNode o_ = _m.getFirstChild();
        boolean stAcc_ = o_ instanceof StaticAccessOperation;
        boolean stAccCall_ = o_ instanceof StaticCallAccessOperation;
        if (stAcc_ || stAccCall_) {
            str_ = resolveCorrectTypes(stAccCall_, _fromType,_args, _page);
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
                int offset_ = _args.first().length() + className.indexOf('(')+1;
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
                    cl_ = ResolvingTypes.resolveAccessibleIdType(offset_,nameId_, _page);
                }
                partOffsets.addAllElts(_page.getCurrentParts());
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
                if (argsRes_.isVararg()) {
                    vararg_ = _len- 2;
                }
                for (String s: argsRes_.getParametersTypes()) {
                    methodTypes_.add(s);
                }
            }
            for (String s: str_) {
                errOwner(s,argsRes_,_page);
            }
            ClassMethodIdReturn id_ = getDeclaredCustMethodLambda(this, vararg_, kind_, str_, name_, true, false, feed_, _page, methodTypes_);
            if (!id_.isFoundMethod()) {
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            standardMethod = id_.getStandardMethod();
            lambdaCommonContent.setReturnFieldType(id_.getOriginalReturnType());
            lambdaCommonContent.setFileName(id_.getFileName());
            lambdaMemberNumberContentId = id_.getMemberId();
            function = id_.getPair();
            String foundClass_ = id_.getRealClass();
            MethodId idCt_ = id_.getRealId();
            if (idCt_.getKind() != MethodAccessKind.STATIC_CALL) {
                foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
            }
            lambdaCommonContent.setFoundClass(id_.getId().getClassName());
            method = new ClassMethodId(foundClass_, idCt_);
            lambdaCommonContent.setAncestor(id_.getAncestor());
            String fct_ = formatReturn(_page, id_.getReturnType(), id_.getRealClass(), id_.getRealId(), id_.getId().getConstraints());
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        MethodAccessKind stCtx_;
        int vararg_ = -1;
        int i_ = 2;
        boolean staticChoiceMethod_ = false;
        boolean accessFromSuper_ = false;
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
            accessFromSuper_ = true;
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
            String type_ = resolveCorrectTypeAccessible(stCtx_ != MethodAccessKind.STATIC, _fromType, _page, offset_);
            partOffsets.addAllElts(_page.getCurrentParts());
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
            str_ = resolveCorrectTypesExact(_fromType,_args, _page);
            argsRes_ = resolveArguments(i_, _args, _page);
            if (argsRes_ == null) {
                return;
            }
            if (argsRes_.isVararg()) {
                vararg_ = _len- i_;
            }
            for (String s: argsRes_.getParametersTypes()) {
                methodTypes_.add(s);
            }
        }
        for (String s: str_) {
            errOwner(s,argsRes_,_page);
        }

        StringList bounds_ = new StringList();
        for (String c: previousResultClass.getNames()) {
            bounds_.addAllElts(InvokingOperation.getBounds(c, _page));
        }
        boolean cloneArray_ = cloneArray(bounds_);
        StringList a_ = new StringList();
        getArrayBounds(bounds_, a_);
        if (cloneArray_) {
            if (name_.startsWith("[]")) {
                if (methodTypes_.isEmpty()) {
                    String foundClass_ = StringExpUtil.getPrettyArrayType(_page.getAliasObject());
                    MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, name_, new StringList());
                    method = new ClassMethodId(foundClass_, id_);
                    StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
                    fct_.append(Templates.TEMPLATE_BEGIN);
                    fct_.append(_page.getAliasPrimInteger());
                    fct_.append(Templates.TEMPLATE_END);
                    setResultClass(new AnaClassArgumentMatching(fct_.toString()));
                    return;
                }
                String foundClass_ = StringExpUtil.getPrettyArrayType(_page.getAliasObject());
                StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
                fct_.append(Templates.TEMPLATE_BEGIN);
                String comp_ = lambdaCommonContent.getFoundClass();
                boolean err_ = false;
                StringList params_ = new StringList();
                for (String p: methodTypes_) {
                    if (!AnaClassArgumentMatching.isNumericInt(p,_page)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                        un_.setFileName(_page.getLocalizer().getCurrentFileName());
                        //arg len
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                                p);
                        _page.getLocalizer().addError(un_);
                        addErr(un_.getBuiltError());
                    }
                    String cp_ = comp_;
                    comp_ = StringExpUtil.getQuickComponentType(comp_);
                    if (comp_ == null) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                        un_.setFileName(_page.getLocalizer().getCurrentFileName());
                        //arg len
                        un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                                cp_);
                        _page.getLocalizer().addError(un_);
                        addErr(un_.getBuiltError());
                        err_ = true;
                        break;
                    }
                    params_.add(p);
                    fct_.append(p);
                    fct_.append(Templates.TEMPLATE_SEP);
                }
                MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, name_, params_);
                method = new ClassMethodId(foundClass_, id_);
                if (err_) {
                    setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                    return;
                }
                if (StringUtil.quickEq(name_,"[]=")) {
                    fct_.append(comp_);
                    fct_.append(Templates.TEMPLATE_SEP);
                }
                fct_.append(comp_);
                fct_.append(Templates.TEMPLATE_END);
                setResultClass(new AnaClassArgumentMatching(fct_.toString()));
                return;
            }
            if (checkArrayMethod(bounds_, name_, _page)) {
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            lambdaMethodContent.setClonedMethod(true);
            String foundClass_ = StringExpUtil.getPrettyArrayType(_page.getAliasObject());
            MethodId id_ = new MethodId(MethodAccessKind.INSTANCE, name_, new StringList());
            method = new ClassMethodId(foundClass_, id_);
            StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
            fct_.append(Templates.TEMPLATE_BEGIN);
            fct_.append(lambdaCommonContent.getFoundClass());
            fct_.append(Templates.TEMPLATE_END);
            setResultClass(new AnaClassArgumentMatching(fct_.toString()));
            return;
        }
        Mapping map_ = new Mapping();
        map_.setArg(new AnaClassArgumentMatching(bounds_));
        map_.setParam(new AnaClassArgumentMatching(str_));
        StringMap<StringList> maps_ = new StringMap<StringList>();
        getRefConstraints(maps_, _page);
        map_.setMapping(maps_);
        if (!AnaTemplates.isCorrectOrNumbers(map_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_page.getLocalizer().getCurrentFileName());
            cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(bounds_,"&"),
                    StringUtil.join(str_,"&"));
            _page.getLocalizer().addError(cast_);
            addErr(cast_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        ClassMethodIdReturn id_ = getDeclaredCustMethodLambda(this, vararg_, stCtx_, str_,
                name_, accessSuper_, accessFromSuper_,
                feed_, _page, methodTypes_);
        if (!id_.isFoundMethod()) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        standardMethod = id_.getStandardMethod();
        lambdaCommonContent.setReturnFieldType(id_.getOriginalReturnType());
        lambdaCommonContent.setFileName(id_.getFileName());
        lambdaMemberNumberContentId = id_.getMemberId();
        function = id_.getPair();
        String foundClass_ = id_.getRealClass();
        foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
        lambdaCommonContent.setFoundClass(id_.getId().getClassName());
        lambdaMethodContent.setAbstractMethod(id_.isAbstractMethod());
        MethodId idCt_ = id_.getRealId();
        method = new ClassMethodId(foundClass_, idCt_);
        lambdaCommonContent.setAncestor(id_.getAncestor());
        String fct_ = formatReturn(_page, id_.getReturnType(), id_.getRealClass(), id_.getRealId(), id_.getId().getConstraints());
        setResultClass(new AnaClassArgumentMatching(fct_));
        processAbstract(staticChoiceMethod_, id_, _page);
    }

    private void ko(String _type, MethodId _id, StringList _formatted, AnalyzedPageEl _page) {
        if (errOwner(_type, _id, _page)) {
            return;
        }
        int indexType_ = 0;
        for (String s: _id.getParametersTypes()) {
            String format_ = AnaTemplates.wildCardFormatParam(_type, s, _page);
            if (format_.isEmpty()) {
                FoundErrorInterpret static_ = new FoundErrorInterpret();
                static_.setFileName(_page.getLocalizer().getCurrentFileName());
                static_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //key word id len
                static_.buildError(_page.getAnalysisMessages().getBadParameTypeForId(),
                        s);
                _page.getLocalizer().addError(static_);
                addErr(static_.getBuiltError());
                format_ = _page.getAliasObject();
            }
            String pref_ = "";
            if (_id.getParametersRef(indexType_)) {
                pref_ = "~";
            }
            _formatted.add(pref_+format_);
            indexType_++;
        }
    }
    private boolean errOwner(String _type, MethodId _id, AnalyzedPageEl _page) {
        if (errOwner(_type,_id)) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_page.getLocalizer().getCurrentFileName());
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

    private static boolean cloneArray(StringList _bounds) {
        boolean cloneArray_ = false;
        for (String b: _bounds) {
            if (b.startsWith(StringExpUtil.ARR_CLASS)) {
                cloneArray_ = true;
                break;
            }
        }
        return cloneArray_;
    }

    private boolean checkArrayMethod(StringList _str, String _name, AnalyzedPageEl _page) {
        if (!StringUtil.quickEq(_name, _page.getAliasClone())) {
            FoundErrorInterpret undefined_ = new FoundErrorInterpret();
            undefined_.setFileName(_page.getLocalizer().getCurrentFileName());
            undefined_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //_name len
            undefined_.buildError(_page.getAnalysisMessages().getArrayCloneOnly(),
                    _page.getAliasClone(),
                    StringUtil.join(_str,"&"));
            _page.getLocalizer().addError(undefined_);
            addErr(undefined_.getBuiltError());
            return true;
        }
        return false;
    }


    private static boolean koArrayMethod(String _name, AnalyzedPageEl _page) {
        return !StringUtil.quickEq(_name, _page.getAliasClone());
    }

    private void getArrayBounds(StringList _bounds, StringList _a) {
        for (String b: _bounds) {
            if (b.startsWith(StringExpUtil.ARR_CLASS)) {
                _a.add(b);
                lambdaCommonContent.setFoundClass(b);
            }
        }
    }

    private void processAbstract(boolean _staticChoiceMethod, ClassMethodIdReturn _id, AnalyzedPageEl _page) {
        if (isAbstract(_staticChoiceMethod, _id)) {
            FoundErrorInterpret abs_ = new FoundErrorInterpret();
            abs_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            abs_.setFileName(_page.getLocalizer().getCurrentFileName());
            //method name len
            abs_.buildError(
                    _page.getAnalysisMessages().getAbstractMethodRef(),
                    _id.getRealClass(),
                    _id.getRealId().getSignature(_page));
            _page.getLocalizer().addError(abs_);
            addErr(abs_.getBuiltError());
        }
    }

    private static boolean isAbstract(boolean _staticChoiceMethod, ClassMethodIdReturn _id) {
        return _staticChoiceMethod && _id.isAbstractMethod();
    }

    private void processInstance(StringList _args, int _len, String _fromType, AnalyzedPageEl _page) {
        StringList methodTypes_ = new StringList();
        int offset_ = className.indexOf('(')+1;
        offset_ += StringExpUtil.getOffset(_args.first());
        String clFrom_ = EMPTY_STRING;
        if (!isIntermediateDottedOperation()) {
            clFrom_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_,_fromType, _page);
            partOffsets.addAllElts(_page.getCurrentParts());
            if (clFrom_.startsWith(ARR)) {
                processArray(_args, _len, clFrom_, _page);
                return;
            }
            String id_ = StringExpUtil.getIdFromAllTypes(clFrom_);
            AnaGeneType h_ = _page.getAnaGeneType(id_);
            if (h_ instanceof RecordBlock) {
                for (String p:StringExpUtil.getWildCards(clFrom_)){
                    FoundErrorInterpret call_ = new FoundErrorInterpret();
                    call_.setFileName(_page.getLocalizer().getCurrentFileName());
                    call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //_fromType len
                    call_.buildError(_page.getAnalysisMessages().getIllegalCtorBound(),
                            p,
                            clFrom_);
                    _page.getLocalizer().addError(call_);
                    addErr(call_.getBuiltError());
                }
                StringList names_ = new StringList();
                StringList types_ = new StringList();
                int offsetArg_ = className.indexOf('(')+1+_args.first().length()+1+_args.get(1).length()+1-StringExpUtil.getOffset(className);
                for (int i = 2; i < _len; i++) {
                    String arg_ = _args.get(i);
                    String name_ = arg_.trim();
                    boolean contained_ = false;
                    for (InfoBlock f: ((RecordBlock)h_).getFieldsBlocks()) {
                        String par_ = AnaTemplates.quickFormat(h_, clFrom_, f.getImportedClassName());
                        int index_ = AnaTypeUtil.getIndex(f,name_);
                        if (index_ >= 0) {
                            contained_ = true;
                            types_.add(par_);
                            offsets.add(offsetArg_+StringExpUtil.getOffset(arg_));
                            named.add(name_);
                            refs.add(index_);
                            infos.addEntry(name_,f.getImportedClassName());
                            break;
                        }
                    }
                    if (!contained_) {
                        offsets.add(offsetArg_+StringExpUtil.getOffset(arg_));
                        named.add(name_);
                        refs.add(-1);
                    }
                    if (StringUtil.contains(names_,name_) || !contained_) {
                        FoundErrorInterpret call_ = new FoundErrorInterpret();
                        call_.setFileName(_page.getLocalizer().getCurrentFileName());
                        call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                        //_fromType len
                        call_.buildError(_page.getAnalysisMessages().getIllegalCtorAbstract(),
                                id_);
                        _page.getLocalizer().addError(call_);
                        addErr(call_.getBuiltError());
                    }
                    names_.add(name_);
                    offsetArg_ += arg_.length()+1;
                }
                lambdaMemberNumberContentId = new MemberId();
                lambdaMemberNumberContentId.setRootNumber(((RecordBlock)h_).getNumberAll());
                lambdaCommonContent.setFoundClass(clFrom_);
                types_.add(clFrom_);
                fieldType = (RootBlock) h_;
                StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
                fct_.append(Templates.TEMPLATE_BEGIN);
                fct_.append(StringUtil.join(types_, Templates.TEMPLATE_SEP));
                fct_.append(Templates.TEMPLATE_END);
                setResultClass(new AnaClassArgumentMatching(fct_.toString()));
                recordType = true;
                return;
            }
        }
        int vararg_ = -1;
        MethodId argsRes_;
        ConstructorId feed_ = null;
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordId_ = keyWords_.getKeyWordId();
        boolean foundId_ = false;
        String type_ = EMPTY_STRING;
        if (_len > 2 && StringUtil.quickEq(_args.get(2).trim(), keyWordId_)) {
            if (isIntermediateDottedOperation()) {
                type_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_,_fromType, _page);
                partOffsets.addAllElts(_page.getCurrentParts());
            } else {
                type_ = clFrom_;
            }
            String cl_ = StringExpUtil.getIdFromAllTypes(type_);
            argsRes_ = resolveArguments(false,3, cl_, MethodAccessKind.INSTANCE, _args, _page);
            if (argsRes_ == null) {
                return;
            }
            feed_ = MethodId.to(cl_, argsRes_);
            ko(type_, argsRes_, methodTypes_, _page);
            foundId_ = true;
        } else {
            int i_ = 2;
            argsRes_ = resolveArguments(i_, _args, _page);
            if (argsRes_ == null) {
                return;
            }
            if (argsRes_.isVararg()) {
                vararg_ = _len- i_;
            }
            for (String s: argsRes_.getParametersTypes()) {
                methodTypes_.add(s);
            }
        }
        if (!isIntermediateDottedOperation()) {
            String id_ = StringExpUtil.getIdFromAllTypes(clFrom_);
            AnaGeneType h_ = _page.getAnaGeneType(id_);
            if (noDefCtor(h_)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(_page.getLocalizer().getCurrentFileName());
                call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //_fromType len
                call_.buildError(_page.getAnalysisMessages().getIllegalCtorAbstract(),
                        id_);
                _page.getLocalizer().addError(call_);
                addErr(call_.getBuiltError());
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            ConstrustorIdVarArg ctorRes_;
            ctorRes_ = getCheckedDeclaredCustConstructorLambda(vararg_, clFrom_,id_, h_,feed_, _page, methodTypes_);
            realId = ctorRes_.getRealId();
            if (realId == null) {
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            function = ctorRes_.getPair();
            standardType = ctorRes_.getStandardType();
            lambdaCommonContent.setFileName(ctorRes_.getFileName());
            lambdaMemberNumberContentId = ctorRes_.getMemberId();
            ConstructorId fid_ = ctorRes_.getConstId();
            StringList parts_ = new StringList();
            if (!h_.isStaticType()) {
                //From analyze
                StringList innerParts_ = StringExpUtil.getAllPartInnerTypes(fid_.getName());
                parts_.add(StringUtil.join(innerParts_.left(innerParts_.size() - 2), ""));
            }

            appendArgsCtor(fid_, parts_);
            lambdaCommonContent.setFoundClass(fid_.getName());
            parts_.add(fid_.getName());
            StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
            fct_.append(Templates.TEMPLATE_BEGIN);
            fct_.append(StringUtil.join(parts_, Templates.TEMPLATE_SEP));
            fct_.append(Templates.TEMPLATE_END);
            setResultClass(new AnaClassArgumentMatching(fct_.toString()));
            return;
        }
        if (foundId_) {
            processCtor(methodTypes_, vararg_, feed_, type_, _page);
            return;
        }
        String cl_ = _fromType.trim();
        String idClass_ = StringExpUtil.getIdFromAllTypes(cl_);
        StringMap<String> ownersMap_ = new StringMap<String>();
        boolean ok_ = true;
        for (String o: previousResultClass.getNames()) {
            if (o.startsWith(ARR)) {
                FoundErrorInterpret call_ = new FoundErrorInterpret();
                call_.setFileName(_page.getLocalizer().getCurrentFileName());
                call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //key word len
                call_.buildError(_page.getAnalysisMessages().getIllegalCtorArray(),
                        o);
                _page.getLocalizer().addError(call_);
                addErr(call_.getBuiltError());
                ok_ = false;
                continue;
            }
            for (String p:StringExpUtil.getWildCards(o)){
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
        }
        if (!ok_) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        for (String o: previousResultClass.getNames()) {
            StringList owners_ = AnaTypeUtil.getGenericOwners(o, idClass_, _page);
            if (owners_.onlyOneElt()) {
                ownersMap_.put(o, owners_.first());
            }
        }
        if (ownersMap_.size() != 1) {
            FoundErrorInterpret static_ = new FoundErrorInterpret();
            static_.setFileName(_page.getLocalizer().getCurrentFileName());
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
        String sup_ = ownersMap_.values().first();
        String idSup_ = StringExpUtil.getIdFromAllTypes(sup_);
        CustList<PartOffset> partOffsets_ = new CustList<PartOffset>();
        ContextUtil.appendParts(offset_,offset_+idClass_.length(), StringUtil.concat(idSup_,"..",idClass_),partOffsets_, _page);
        offset_ += idClass_.length() + 1;
        StringList partsArgs_ = new StringList();
        for (String a: StringExpUtil.getAllTypes(cl_).mid(1)) {
            int loc_ = StringExpUtil.getOffset(a);
            String res_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_+loc_,a, _page);
            partOffsets_.addAllElts(_page.getCurrentParts());
            partsArgs_.add(res_);
            offset_ += a.length() + 1;
        }
        partOffsets_.addAllElts(partOffsets);
        partOffsets.clear();
        partOffsets.addAllElts(partOffsets_);
        StringMap<StringList> vars_ = _page.getCurrentConstraints().getCurrentConstraints();
        cl_ = check(StringUtil.concat(sup_, "..", idClass_), partsArgs_, vars_, _page);
        processCtor(methodTypes_, vararg_, null, cl_, _page);
    }

    private void processCtor(StringList _methodTypes, int _vararg, ConstructorId _feed, String _cl, AnalyzedPageEl _page) {
        lambdaCommonContent.setFoundClass(_cl);
        lambdaCommonContent.setShiftArgument(true);
        String id_ = StringExpUtil.getIdFromAllTypes(_cl);
        AnaGeneType h_ = _page.getAnaGeneType(id_);
        if (noDefCtor(h_)) {
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_page.getLocalizer().getCurrentFileName());
            call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len or _fromType
            call_.buildError(_page.getAnalysisMessages().getIllegalCtorAbstract(),
                    id_);
            _page.getLocalizer().addError(call_);
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            addErr(call_.getBuiltError());
            return;
        }
        ConstrustorIdVarArg ctorRes_;
        ctorRes_ = getCheckedDeclaredCustConstructorLambda(_vararg, _cl,id_,h_, _feed, _page, _methodTypes);
        realId = ctorRes_.getRealId();
        if (realId == null) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        function = ctorRes_.getPair();
        standardType = ctorRes_.getStandardType();
        lambdaCommonContent.setFileName(ctorRes_.getFileName());
        lambdaMemberNumberContentId = ctorRes_.getMemberId();
        ConstructorId fid_ = ctorRes_.getConstId();
        StringList parts_ = new StringList();
        appendArgsCtor(fid_, parts_);
        parts_.add(fid_.getName());
        StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
        fct_.append(Templates.TEMPLATE_BEGIN);
        fct_.append(StringUtil.join(parts_, Templates.TEMPLATE_SEP));
        fct_.append(Templates.TEMPLATE_END);
        setResultClass(new AnaClassArgumentMatching(fct_.toString()));
    }
    ConstrustorIdVarArg getCheckedDeclaredCustConstructorLambda(int _varargOnly, String _class,
                                                                String _id, AnaGeneType _type,
                                                                ConstructorId _uniqueId, AnalyzedPageEl _page, StringList _args) {
        for (String p:StringExpUtil.getWildCards(_class)){
            FoundErrorInterpret call_ = new FoundErrorInterpret();
            call_.setFileName(_page.getLocalizer().getCurrentFileName());
            call_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //_fromType len
            call_.buildError(_page.getAnalysisMessages().getIllegalCtorBound(),
                    p,
                    _class);
            _page.getLocalizer().addError(call_);
            addErr(call_.getBuiltError());
        }
        return getDeclaredCustConstructorLambda(this, _varargOnly, new AnaClassArgumentMatching(_class),
                _id, _type,
                _uniqueId, _page, _args);
    }
    private static boolean noDefCtor(AnaGeneType _h) {
        return ContextUtil.isAbstractType(_h) || _h instanceof RecordBlock;
    }

    private void processField(MethodOperation _m, StringList _args, int _len, String _fromType, AnalyzedPageEl _page) {
        StringList str_;
        if (_len < 3) {
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFileName(_page.getLocalizer().getCurrentFileName());
            badCall_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            badCall_.buildError(_page.getAnalysisMessages().getSplitComa(),
                    Long.toString(3),
                    Long.toString(_len)
            );
            _page.getLocalizer().addError(badCall_);
            int i_ = _page.getLocalizer().getCurrentLocationIndex();
            partOffsetsEnd.add(new PartOffset("<a title=\""+LinkageUtil.transform(badCall_.getBuiltError()) +"\" class=\"e\">",i_));
            partOffsetsEnd.add(new PartOffset("</a>",i_+ _page.getKeyWords().getKeyWordLambda().length()));
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        String fieldName_ = _args.get(2).trim();
        int sum_ = className.indexOf('(')+1;
        sum_ += _args.first().length() + 1;
        sum_ += _args.get(1).length() + 1;
        if (!isIntermediateDottedOperation()) {
            String resolved_ = resolveSingleTypeExact(_args, _fromType, _page);
            str_ = InvokingOperation.getBounds(resolved_, _page);
            int i_ = 3;
            RootBlock root_ = _page.getAnaClassBody(resolved_);
            if (root_ != null) {
                lambdaMemberNumberContentId = new MemberId();
                lambdaMemberNumberContentId.setRootNumber(root_.getNumberAll());
            }
            boolean accessFromSuper_ = false;
            boolean accessSuper_ = true;
            KeyWords keyWords_ = _page.getKeyWords();
            String keyWordSuper_ = keyWords_.getKeyWordSuper();
            String keyWordThat_ = keyWords_.getKeyWordThat();
            String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
            String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
            String keyWordParent_ = keyWords_.getKeyWordParent();
            String keyWordInstanceof_ = keyWords_.getKeyWordInstanceof();
            if (StringUtil.quickEq(fieldName_, keyWordInstanceof_)) {
                lambdaFieldContent.setFinalField(true);
                lambdaCommonContent.setReturnFieldType(_page.getAliasPrimBoolean());
                lambdaCommonContent.setFoundClass(resolved_);
                lambdaCommonContent.setShiftArgument(true);
                String fct_ = StringUtil.concat(_page.getAliasFct(),"<",_page.getAliasObject(),",", lambdaCommonContent.getReturnFieldType(),">");
                setResultClass(new AnaClassArgumentMatching(fct_));
                return;
            }
            if (StringUtil.quickEq(fieldName_, keyWordParent_)) {
                String res_ = getParentType(resolved_, _page);
                lambdaFieldContent.setFinalField(true);
                lambdaCommonContent.setReturnFieldType(res_);
                lambdaCommonContent.setFoundClass(resolved_);
                lambdaCommonContent.setShiftArgument(true);
                StringList params_ = new StringList();
                String fct_ = formatFieldReturn(false, params_, res_, lambdaCommonContent.isShiftArgument(), _page);
                setResultClass(new AnaClassArgumentMatching(fct_));
                return;
            }
            if (i_ < _len && StringUtil.quickEq(fieldName_, keyWordSuper_)) {
                fieldName_ = _args.get(i_).trim();
                sum_ += _args.get(i_-1).length() + 1;
                sum_ += StringExpUtil.getOffset(_args.get(i_));
                i_++;
                accessFromSuper_ = true;
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
            FieldResult r_ = getDeclaredCustFieldLambda(sum_,partOffsets, fromCl_, !accessFromSuper_, accessSuper_, fieldName_, aff_, _page);
            if (r_.getStatus() == SearchingMemberStatus.ZERO) {
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            lambdaCommonContent.setFileName(r_.getFileName());
            lambdaMemberNumberContentId = r_.getMemberId();
            valueOffset = r_.getValOffset();
            lambdaFieldContent.setAffField(aff_);
            fieldId = r_.getClassField();
            fieldType = r_.getFieldType();
            lambdaFieldContent.setStaticField(r_.isStaticField());
            lambdaFieldContent.setFinalField(r_.isFinalField());
            String out_ = r_.getType();
            lambdaCommonContent.setReturnFieldType(out_);
            lambdaCommonContent.setFoundClass(r_.getDeclaringClass());
            lambdaCommonContent.setAncestor(r_.getAnc());
            boolean static_ = r_.isStaticField();
            lambdaCommonContent.setShiftArgument(!static_);
            StringList params_ = new StringList();
            if (aff_) {
                checkFinal(_page, r_.isFinalField(), r_.getClassField());
                int offset_ = className.indexOf('(')+1;
                for (int i = 0; i < i_; i++) {
                    offset_ += _args.get(i).length();
                    offset_++;
                }
                offset_ += StringExpUtil.getOffset(_args.get(i_));
                String type_ = _args.get(i_).trim();
                String arg_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_,type_, _page);
                partOffsets.addAllElts(_page.getCurrentParts());
                StringMap<StringList> map_ = new StringMap<StringList>();
                getRefConstraints(map_, _page);
                Mapping mapping_ = new Mapping();
                mapping_.setArg(arg_);
                mapping_.setParam(out_);
                mapping_.setMapping(map_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //field name len
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            arg_,
                            out_);
                    _page.getLocalizer().addError(cast_);
                    addErr(cast_.getBuiltError());
                }
                params_.add(arg_);
                //setter
            }
            String fct_ = formatFieldReturn(static_, params_, out_, lambdaCommonContent.isShiftArgument(), _page);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        OperationNode o_ = _m.getFirstChild();
        if (o_ instanceof StaticAccessOperation) {
            str_ = resolveCorrectTypes(false, _fromType,_args, _page);
            int i_ = 3;
            boolean aff_ = i_ < _len;
            AnaClassArgumentMatching fromCl_ = new AnaClassArgumentMatching(str_);
            sum_ += StringExpUtil.getOffset(_args.get(2));
            FieldResult r_ = getDeclaredCustFieldLambda(sum_,partOffsets, fromCl_, true, true, fieldName_, aff_, _page);
            if (r_.getStatus() == SearchingMemberStatus.ZERO) {
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            lambdaCommonContent.setFileName(r_.getFileName());
            lambdaMemberNumberContentId = r_.getMemberId();
            valueOffset = r_.getValOffset();
            lambdaFieldContent.setAffField(aff_);
            fieldId = r_.getClassField();
            fieldType = r_.getFieldType();
            lambdaFieldContent.setStaticField(r_.isStaticField());
            lambdaFieldContent.setFinalField(r_.isFinalField());
            String out_ = r_.getType();
            lambdaCommonContent.setReturnFieldType(out_);
            lambdaCommonContent.setFoundClass(r_.getDeclaringClass());
            lambdaCommonContent.setAncestor(r_.getAnc());
            StringList params_ = new StringList();
            if (aff_) {
                checkFinal(_page, r_.isFinalField(), r_.getClassField());
                int offset_ = className.indexOf('(')+1;
                for (int i = 0; i < i_; i++) {
                    offset_ += _args.get(i).length();
                    offset_++;
                }
                offset_ += StringExpUtil.getOffset(_args.get(i_));
                String type_ = _args.get(i_).trim();
                String arg_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_,type_, _page);
                partOffsets.addAllElts(_page.getCurrentParts());
                StringMap<StringList> map_ = new StringMap<StringList>();
                getRefConstraints(map_, _page);
                Mapping mapping_ = new Mapping();
                mapping_.setArg(arg_);
                mapping_.setParam(out_);
                mapping_.setMapping(map_);
                if (!AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
                    FoundErrorInterpret cast_ = new FoundErrorInterpret();
                    cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                    cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                    //field name len
                    cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                            arg_,
                            out_);
                    _page.getLocalizer().addError(cast_);
                    addErr(cast_.getBuiltError());
                }
                params_.add(arg_);
                //setter
            }
            String fct_ = formatFieldReturn(true, params_, out_, false, _page);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        String resolved_ = resolveSingleTypeExact(_args, _fromType, _page);
        str_ = InvokingOperation.getBounds(resolved_, _page);
        int i_ = 3;
        boolean accessFromSuper_ = false;
        boolean accessSuper_ = true;
        KeyWords keyWords_ = _page.getKeyWords();
        String keyWordSuper_ = keyWords_.getKeyWordSuper();
        String keyWordThat_ = keyWords_.getKeyWordThat();
        String keyWordClasschoice_ = keyWords_.getKeyWordClasschoice();
        String keyWordSuperaccess_ = keyWords_.getKeyWordSuperaccess();
        String keyWordParent_ = keyWords_.getKeyWordParent();
        String keyWordInstanceof_ = keyWords_.getKeyWordInstanceof();
        StringList bounds_ = new StringList();
        for (String c: previousResultClass.getNames()) {
            bounds_.addAllElts(InvokingOperation.getBounds(c, _page));
        }
        if (StringUtil.quickEq(fieldName_, keyWordInstanceof_)) {
            lambdaFieldContent.setFinalField(true);
            lambdaCommonContent.setReturnFieldType(_page.getAliasPrimBoolean());
            lambdaCommonContent.setFoundClass(resolved_);
            String fct_ = StringUtil.concat(_page.getAliasFct(),"<", lambdaCommonContent.getReturnFieldType(),">");
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
            if (!AnaTemplates.isCorrectOrNumbers(map_, _page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //key word len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        StringUtil.join(bounds_,"&"),
                        StringUtil.join(str_,"&"));
                _page.getLocalizer().addError(cast_);
                addErr(cast_.getBuiltError());
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            String res_ = getParentType(resolved_, _page);
            lambdaFieldContent.setFinalField(true);
            lambdaCommonContent.setReturnFieldType(res_);
            lambdaCommonContent.setFoundClass(resolved_);
            StringList params_ = new StringList();
            String fct_ = formatFieldReturn(false, params_, res_, false, _page);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        if (i_ < _len && StringUtil.quickEq(fieldName_, keyWordSuper_)) {
            fieldName_ = _args.get(i_).trim();
            sum_ += _args.get(i_-1).length() + 1;
            sum_ += StringExpUtil.getOffset(_args.get(i_));
            i_++;
            accessFromSuper_ = true;
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
        if (!AnaTemplates.isCorrectOrNumbers(map_, _page)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(_page.getLocalizer().getCurrentFileName());
            cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                    StringUtil.join(bounds_,"&"),
                    StringUtil.join(str_,"&"));
            _page.getLocalizer().addError(cast_);
            addErr(cast_.getBuiltError());
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        boolean aff_ = i_ < _len;
        AnaClassArgumentMatching fromCl_ = new AnaClassArgumentMatching(str_);
        FieldResult r_ = getDeclaredCustFieldLambda(sum_,partOffsets, fromCl_, !accessFromSuper_, accessSuper_, fieldName_, aff_, _page);
        if (r_.getStatus() == SearchingMemberStatus.ZERO) {
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        lambdaCommonContent.setFileName(r_.getFileName());
        lambdaMemberNumberContentId = r_.getMemberId();
        valueOffset = r_.getValOffset();
        lambdaFieldContent.setAffField(aff_);
        fieldId = r_.getClassField();
        fieldType = r_.getFieldType();
        lambdaFieldContent.setStaticField(r_.isStaticField());
        lambdaFieldContent.setFinalField(r_.isFinalField());
        String out_ = r_.getType();
        lambdaCommonContent.setReturnFieldType(out_);
        lambdaCommonContent.setFoundClass(r_.getDeclaringClass());
        lambdaCommonContent.setAncestor(r_.getAnc());
        boolean static_ = r_.isStaticField();
        StringList params_ = new StringList();
        if (aff_) {
            checkFinal(_page, r_.isFinalField(), r_.getClassField());
            int offset_ = className.indexOf('(')+1;
            for (int i = 0; i < i_; i++) {
                offset_ += _args.get(i).length();
                offset_++;
            }
            offset_ += StringExpUtil.getOffset(_args.get(i_));
            String type_ = _args.get(i_).trim();
            String arg_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_,type_, _page);
            partOffsets.addAllElts(_page.getCurrentParts());
            Mapping mapping_ = new Mapping();
            mapping_.setArg(arg_);
            mapping_.setParam(out_);
            mapping_.setMapping(maps_);
            if (!AnaTemplates.isCorrectOrNumbers(mapping_, _page)) {
                FoundErrorInterpret cast_ = new FoundErrorInterpret();
                cast_.setFileName(_page.getLocalizer().getCurrentFileName());
                cast_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //field name len
                cast_.buildError(_page.getAnalysisMessages().getBadImplicitCast(),
                        arg_,
                        out_);
                _page.getLocalizer().addError(cast_);
                addErr(cast_.getBuiltError());
            }
            params_.add(arg_);
            //setter
        }
        String fct_ = formatFieldReturn(static_, params_, out_, false, _page);
        setResultClass(new AnaClassArgumentMatching(fct_));
    }

    private static String getParentType(String _converted, AnalyzedPageEl _page) {
        if (_converted.startsWith(AnaTemplates.ARR_BEG_STRING)) {
            return _page.getAliasObject();
        }
        StringList rs_ = ParentInstanceOperation.getParentTypeList(new StringList(_converted), _page);
        return rs_.first();
    }
    private String resolveSingleTypeExact(StringList _args, String _fromType, AnalyzedPageEl _page) {
        int offset_ = className.indexOf('(')+1;
        offset_ += StringExpUtil.getOffset(_args.first());
        String type_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_, _fromType, _page);
        partOffsets.addAllElts(_page.getCurrentParts());
        return type_;
    }

    private void checkFinal(AnalyzedPageEl _page, boolean _finalField, ClassField _classField) {
        if (_finalField) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
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
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //key word len
            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                    StringUtil.join(previousResultClass.getNames(),"&"));
            _page.getLocalizer().addError(un_);
            int i_ = _page.getLocalizer().getCurrentLocationIndex();
            partOffsetsEnd.add(new PartOffset("<a title=\""+LinkageUtil.transform(un_.getBuiltError()) +"\" class=\"e\">",i_));
            partOffsetsEnd.add(new PartOffset("</a>",i_+ _page.getKeyWords().getKeyWordLambda().length()));
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
            sum_ += 1+_args.get(1).length();
            int offset_ = className.indexOf(',')+1;
            offset_ += StringExpUtil.getOffset(_args.get(1));
            if (operator_.isEmpty()) {
                offset_--;
            }
            String type_ = resolveCorrectTypeAccessible(true, operator_, _page, offset_);
            partOffsets.addAllElts(_page.getCurrentParts());
            from_ = type_;
            if (_len > i_) {
                operator_ = _args.get(i_).trim();
                sum_ += StringExpUtil.getOffset(_args.get(i_));
                displayErr_ = true;
            }
            i_++;
        } else {
            sum_ += StringExpUtil.getOffset(_args.get(1));
        }
        int count_ = partOffsets.size();
        if (!StringExpUtil.isOper(operator_)) {
            FoundErrorInterpret badMeth_ = new FoundErrorInterpret();
            badMeth_.setFileName(_page.getCurrentBlock().getFile().getFileName());
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
            partOffsetsEnd.add(new PartOffset("<a title=\"" + LinkageUtil.transform(badMeth_.getBuiltError()) + "\" class=\"e\">", j_));
            partOffsetsEnd.add(new PartOffset("</a>", j_ + lenErr_));
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
        if (_len > j_ && StringUtil.quickEq(_args.get(j_).trim(), keyWordId_)) {
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
        if (_len > j_ && StringUtil.quickEq(_args.get(j_).trim(), keyWordId_)) {
            if (isIntermediateDottedOperation()) {
                methodTypes_.add(previousResultClass.getName());
                feed_ = new ClassMethodId(from_, argsRes_.prepend(operator_, previousResultClass.getName(),false));
            } else {
                feed_ = new ClassMethodId(from_, MethodId.to(staticFlag_, operator_, argsRes_));
            }
            ko(from_, argsRes_, methodTypes_, _page);
        } else {
            if (isIntermediateDottedOperation()) {
                methodTypes_.add(previousResultClass.getName());
                if (argsRes_.isVararg()) {
                    vararg_ = _len- i_+1;
                }
            } else {
                if (argsRes_.isVararg()) {
                    vararg_ = _len- i_;
                }
            }
            for (String s: argsRes_.getParametersTypes()) {
                methodTypes_.add(s);
            }
            errOwner(from_, argsRes_, _page);
        }
        if (!isIntermediateDottedOperation()) {
            ClassMethodIdReturn id_ = getOperator(from_,methodTypes_, operator_, vararg_, feed_, _page);
            if (!id_.isFoundMethod()) {
                FoundErrorInterpret undefined_ = new FoundErrorInterpret();
                undefined_.setFileName(_page.getLocalizer().getCurrentFileName());
                undefined_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                //_name len
                undefined_.buildError(_page.getAnalysisMessages().getUndefinedMethod(),
                        new MethodId(MethodAccessKind.STATIC, "", methodTypes_).getSignature(_page));
                _page.getLocalizer().addError(undefined_);
                int k_ = _page.getLocalizer().getCurrentLocationIndex()+sum_+1;
                partOffsets.add(count_,new PartOffset("</a>",k_+Math.max(1,operator_.length())));
                partOffsets.add(count_,new PartOffset("<a title=\""+LinkageUtil.transform(undefined_.getBuiltError()) +"\" class=\"e\">",k_));
                setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                return;
            }
            lambdaCommonContent.setFileName(id_.getFileName());
            lambdaMemberNumberContentId = id_.getMemberId();
            function = id_.getPair();
            String foundClass_ = id_.getRealClass();
            foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
            lambdaCommonContent.setFoundClass(id_.getId().getClassName());
            MethodId idCt_ = id_.getRealId();
            method = new ClassMethodId(foundClass_, idCt_);
            lambdaCommonContent.setAncestor(id_.getAncestor());
            lambdaCommonContent.setReturnFieldType(id_.getReturnType());
            String fct_ = formatReturnOperator(false, id_, _page);
            setResultClass(new AnaClassArgumentMatching(fct_));
            return;
        }
        ClassMethodIdReturn id_ = getOperator(from_,methodTypes_, operator_, vararg_, feed_, _page);
        if (!id_.isFoundMethod()) {
            FoundErrorInterpret undefined_ = new FoundErrorInterpret();
            undefined_.setFileName(_page.getLocalizer().getCurrentFileName());
            undefined_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
            //_name len
            undefined_.buildError(_page.getAnalysisMessages().getUndefinedMethod(),
                    new MethodId(MethodAccessKind.STATIC, "", methodTypes_).getSignature(_page));
            _page.getLocalizer().addError(undefined_);
            int k_ = _page.getLocalizer().getCurrentLocationIndex()+sum_+1;
            partOffsets.add(count_,new PartOffset("</a>",k_+Math.max(1,operator_.length())));
            partOffsets.add(count_,new PartOffset("<a title=\""+LinkageUtil.transform(undefined_.getBuiltError()) +"\" class=\"e\">",k_));
            setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
            return;
        }
        lambdaCommonContent.setFileName(id_.getFileName());
        lambdaMemberNumberContentId = id_.getMemberId();
        function = id_.getPair();
        lambdaCommonContent.setReturnFieldType(id_.getOriginalReturnType());
        String foundClass_ = id_.getRealClass();
        foundClass_ = StringExpUtil.getIdFromAllTypes(foundClass_);
        lambdaCommonContent.setFoundClass(id_.getId().getClassName());
        MethodId idCt_ = id_.getRealId();
        method = new ClassMethodId(foundClass_, idCt_);
        lambdaCommonContent.setAncestor(id_.getAncestor());
        lambdaCommonContent.setShiftArgument(true);
        String fct_ = formatReturnOperator(true, id_, _page);
        setResultClass(new AnaClassArgumentMatching(fct_));
    }

    private static ClassMethodIdReturn getOperator(String _from, StringList _methodTypes, String _operator, int _vararg, ClassMethodId _feed, AnalyzedPageEl _page) {
        if (!_from.isEmpty()) {
            if (_feed == null) {
                return tryGetDeclaredCustMethodLambda(-1, MethodAccessKind.STATIC_CALL,
                        new StringList(_from), _operator, false, false, false, null,
                        _methodTypes, _page);
            }
            return tryGetDeclaredCustMethodLambda(-1, MethodAccessKind.STATIC_CALL,
                    new StringList(_from), _operator, false, false, false, new ClassMethodIdAncestor(_feed,0),
                    _methodTypes, _page);
        }
        return getOperatorLambda(_feed, _vararg, _operator, _page, _methodTypes);
    }

    private void processArray(StringList _args, int _len,
                              String _cl, AnalyzedPageEl _page) {
        StringList methodTypes_ = new StringList();
        int i_ = 2;
        StringList parts_ = new StringList();
        boolean err_ = false;
        for (int i = i_; i < _len; i++) {
            String arg_ = StringExpUtil.removeDottedSpaces(_args.get(i));
            parts_.add(arg_);
            if (!AnaClassArgumentMatching.isNumericInt(arg_,_page)) {
                FoundErrorInterpret un_ = new FoundErrorInterpret();
                un_.setIndexFile(_page.getLocalizer().getCurrentLocationIndex());
                un_.setFileName(_page.getLocalizer().getCurrentFileName());
                //arg_ len
                un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                        arg_);
                _page.getLocalizer().addError(un_);
                addErr(un_.getBuiltError());
                err_ = true;
            }
            methodTypes_.add(arg_);
        }
        if (methodTypes_.isEmpty()) {
            FoundErrorInterpret badCall_ = new FoundErrorInterpret();
            badCall_.setFileName(_page.getLocalizer().getCurrentFileName());
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
        String elt_ = _cl.substring(ARR.length());
        String out_ = StringExpUtil.getPrettyArrayType(elt_, methodTypes_.size());
        lambdaCommonContent.setFoundClass(out_);
        realId = new ConstructorId(out_, parts_, true);
        parts_.add(out_);
        StringBuilder fct_ = new StringBuilder(_page.getAliasFct());
        fct_.append(Templates.TEMPLATE_BEGIN);
        fct_.append(StringUtil.join(parts_, Templates.TEMPLATE_SEP));
        fct_.append(Templates.TEMPLATE_END);
        setResultClass(new AnaClassArgumentMatching(fct_.toString()));
    }
    private MethodId resolveArguments(boolean _retRef,int _from, String _fromType, MethodAccessKind _static, StringList _params, AnalyzedPageEl _page){
        StringList out_ = new StringList();
        BooleanList ref_ = new BooleanList();
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
                    varg_.setFileName(_page.getLocalizer().getCurrentFileName());
                    int i_ = off_ + _page.getLocalizer().getCurrentLocationIndex() + full_.lastIndexOf("...");
                    varg_.setIndexFile(i_);
                    //three dots
                    varg_.buildError(_page.getAnalysisMessages().getUnexpectedVararg());
                    _page.getLocalizer().addError(varg_);
                    setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                    partOffsets.add(new PartOffset("<a title=\""+LinkageUtil.transform(varg_.getBuiltError()) +"\" class=\"e\">",i_));
                    partOffsets.add(new PartOffset("</a>",i_+3));
                    return null;
                }
                vararg_ = len_- _from;
                type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length());
            } else {
                type_ = arg_;
            }
            arg_ = ResolvingTypes.resolveCorrectAccessibleType(off_ + loc_,type_, _fromType, _page);
            partOffsets.addAllElts(_page.getCurrentParts());
            off_ += _params.get(i).length() + 1;
            out_.add(arg_);
            ref_.add(refParam_);
        }
        return new MethodId(_retRef,_static, EMPTY_STRING, out_,ref_, vararg_ != -1);
    }
    private MethodId resolveArguments(int _from, StringList _params, AnalyzedPageEl _page){
        StringList out_ = new StringList();
        BooleanList ref_ = new BooleanList();
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
                    varg_.setFileName(_page.getLocalizer().getCurrentFileName());
                    int i_ = offset_ + _page.getLocalizer().getCurrentLocationIndex() + param_.lastIndexOf("...");
                    varg_.setIndexFile(i_);
                    //three dots
                    varg_.buildError(_page.getAnalysisMessages().getUnexpectedVararg());
                    _page.getLocalizer().addError(varg_);
                    setResultClass(new AnaClassArgumentMatching(_page.getAliasObject()));
                    partOffsets.add(new PartOffset("<a title=\""+LinkageUtil.transform(varg_.getBuiltError()) +"\" class=\"e\">",i_));
                    partOffsets.add(new PartOffset("</a>",i_+3));
                    return null;
                }
                wrap_ = true;
                vararg_ = len_- _from;
                type_ = arg_.substring(0, arg_.length()-VARARG_SUFFIX.length());
            } else {
                type_ = arg_;
            }
            arg_ = ResolvingTypes.resolveCorrectTypeAccessible(offset_+loc_,type_, _page);
            partOffsets.addAllElts(_page.getCurrentParts());
            if (wrap_) {
                arg_ = StringExpUtil.getPrettyArrayType(arg_);
            }
            offset_ += param_.length() + 1;
            out_.add(pref_+arg_);
            ref_.add(refParam_);
        }
        return new MethodId(false,MethodAccessKind.INSTANCE, EMPTY_STRING, out_,ref_, vararg_ != -1);
    }

    private StringList resolveCorrectTypes(boolean _exact, String _type, StringList _args, AnalyzedPageEl _page) {
        int offset_ = className.indexOf('(')+1;
        offset_ += StringExpUtil.getOffset(_args.first());
        String type_ = resolveCorrectTypeAccessible(_exact, _type, _page, offset_);
        partOffsets.addAllElts(_page.getCurrentParts());
        return InvokingOperation.getBounds(type_, _page);
    }

    private static String resolveCorrectTypeAccessible(boolean _exact, String _type, AnalyzedPageEl _page, int _offset) {
        if (_exact) {
            return ResolvingTypes.resolveCorrectTypeAccessible(_offset, _type, _page);
        }
        return ResolvingTypes.resolveAccessibleIdType(_offset, _type, _page);
    }

    private StringList resolveCorrectTypesExact(String _type, StringList _args, AnalyzedPageEl _page) {
        String type_ = resolveSingleTypeExact(_args, _type, _page);
        return InvokingOperation.getBounds(type_, _page);
    }
    static String formatReturn(AnalyzedPageEl _page, String _returnType, String _realClass, MethodId _realId, MethodId _constraints) {
        StringList paramsReturn_ = new StringList();
        return appendParts(_page, _returnType, _realClass, _realId, _constraints, paramsReturn_);
    }
    private static String formatReturn(String _foundClass, AnalyzedPageEl _page, String _returnType, String _realClass, MethodId _realId, MethodId _constraints) {
        StringList paramsReturn_ = new StringList();
        if (!_realId.isStaticMethod()) {
            paramsReturn_.add(_foundClass);
        }
        return appendParts(_page, _returnType, _realClass, _realId, _constraints, paramsReturn_);
    }

    private static String appendParts(AnalyzedPageEl _page, String _returnType, String _realClass, MethodId _realId, MethodId _constraints, StringList _paramsReturn) {
        IdentifiableUtil.appendLeftPart(_paramsReturn, _constraints);
        appendRightPart(_paramsReturn, _page, _realClass, _realId);
        if (_constraints.isRetRef()) {
            _paramsReturn.add("~"+_returnType);
        } else {
            _paramsReturn.add(_returnType);
        }
        String fctBase_ = _page.getAliasFct();
        return StringUtil.concat(fctBase_, Templates.TEMPLATE_BEGIN, StringUtil.join(_paramsReturn, Templates.TEMPLATE_SEP), Templates.TEMPLATE_END);
    }

    private static void appendRightPart(StringList _paramsReturn, AnalyzedPageEl _page, String _realClass, MethodId _realId) {
        if (StringUtil.quickEq(_realId.getName(),"[]=")) {
            CustList<OverridableBlock> getIndexers_ = new CustList<OverridableBlock>();
            String idCl_ = StringExpUtil.getIdFromAllTypes(_realClass);
            for (Block b: ClassesUtil.getDirectChildren(_page.getAnaClassBody(idCl_))) {
                if (!(b instanceof OverridableBlock)) {
                    continue;
                }
                OverridableBlock i_ = (OverridableBlock) b;
                if (i_.getKind() != MethodKind.GET_INDEX) {
                    continue;
                }
                if (!i_.getId().eqPartial(_realId)) {
                    continue;
                }
                getIndexers_.add(i_);
            }
            if (getIndexers_.size() == 1) {
                OverridableBlock matching_ = getIndexers_.first();
                String importedReturnType_ = matching_.getImportedReturnType();
                importedReturnType_ = AnaTemplates.wildCardFormatReturn(_realClass, importedReturnType_, _page);
                _paramsReturn.add(importedReturnType_);
            }
        }
    }

    private static String formatReturnOperator(boolean _op, ClassMethodIdReturn _id, AnalyzedPageEl _page) {
        String fctBase_ = _page.getAliasFct();
        String returnType_ = _id.getReturnType();
        StringList paramsReturn_ = new StringList();
        MethodId id_ = _id.getId().getConstraints();
        StringList params_ = id_.getParametersTypes();
        int start_;
        if (_op) {
            start_ = 1;
        } else {
            start_ = 0;
        }
        int j_ = 0;
        CustList<String> ext_ = params_.mid(start_);
        for (String p: ext_) {
            String p_ = p;
            if (id_.isVararg() && j_ + 1 == ext_.size()) {
                p_ = StringExpUtil.getPrettyArrayType(p_);
            }
            String pref_ = "";
            if (id_.getParametersRef(j_+start_)) {
                pref_ = "~";
            }
            paramsReturn_.add(pref_+p_);
            j_++;
        }
        if (id_.isRetRef()) {
            paramsReturn_.add("~"+returnType_);
        } else {
            paramsReturn_.add(returnType_);
        }
        return StringUtil.concat(fctBase_, Templates.TEMPLATE_BEGIN, StringUtil.join(paramsReturn_, Templates.TEMPLATE_SEP), Templates.TEMPLATE_END);
    }
    private String formatFieldReturn(boolean _static, StringList _params, String _returnType, boolean _demand, AnalyzedPageEl _page) {
        String fctBase_ = _page.getAliasFct();
        StringList paramsReturn_ = new StringList();
        if (!_static && _demand) {
            paramsReturn_.add(lambdaCommonContent.getFoundClass());
        }
        paramsReturn_.addAllElts(_params);
        paramsReturn_.add(_returnType);
        return StringUtil.concat(fctBase_, Templates.TEMPLATE_BEGIN, StringUtil.join(paramsReturn_, Templates.TEMPLATE_SEP), Templates.TEMPLATE_END);
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

    public CustList<PartOffset> getPartOffsets() {
        return partOffsets;
    }

    public int getClassNameOffset() {
        return StringExpUtil.getOffset(className);
    }

    public int getValueOffset() {
        return valueOffset;
    }

    public CustList<PartOffset> getPartOffsetsEnd() {
        return partOffsetsEnd;
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

    public boolean isRecordType() {
        return recordType;
    }

    public Ints getOffsets() {
        return offsets;
    }

    public StringList getNamed() {
        return named;
    }

    public Ints getRefs() {
        return refs;
    }

    public StringMap<String> getInfos() {
        return infos;
    }
}
