package code.formathtml.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbsAliasFileBuilder;
import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.DefStackStopper;
import code.expressionlanguage.exec.ExecClassesUtil;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.inherits.ExecTypeReturn;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.AbsContextGenerator;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.ResultContext;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.*;
import code.formathtml.*;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.exec.AnchorCall;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.exec.blocks.RendImport;
import code.formathtml.exec.blocks.RendImportForm;
import code.formathtml.exec.opers.*;
import code.formathtml.fwd.RendForwardInfos;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.Message;
import code.formathtml.structs.ValidatorInfo;
import code.maths.montecarlo.AbstractGenerator;
import code.sml.*;
import code.util.*;
import code.util.core.StringUtil;

public abstract class BeanCustLgNames extends BeanLgNames implements WithPageInfos {

    private static final String REF_TAG = "#";

    private static final String RETURN_LINE = "\n";
    private static final String SIMPLE_WILD_CARD = "<?>";
    private static final String DOUBLE_WILD_CARD = "<?,?>";

    private final DefaultBeanAliases beanAliases = new DefaultBeanAliases();

    private String iteratorVar;
    private String hasNextVar;
    private String nextVar;


    private String iteratorTableVarCust;
    private String hasNextPairVarCust;
    private String nextPairVarCust;
    private String firstVarCust;
    private String secondVarCust;
    private String beforeDisplayingVar;

    private String putVarCust;
    private String putVarCustKey;
    private String putVarCustValue;
    private String putAllVarCust;
    private String putAllVarCustArg;
    private String getValVar;
    private String getValVarArg;
    private String getFormsVar;
    private String setFormsVarArg;
    private String setFormsVar;
    private String getDataBaseVar;
    private String setDataBaseVarArg;
    private String setDataBaseVar;
    private String getScopeVar;
    private String setScopeVarArg;
    private String setScopeVar;
    private String setLanguageVarArg;
    private String setLanguageVar;
    private CustList<RendDynOperationNode> expsIterator;
    private CustList<RendDynOperationNode> expsHasNext;
    private CustList<RendDynOperationNode> expsNext;
    private CustList<RendDynOperationNode> expsIteratorTableCust;
    private CustList<RendDynOperationNode> expsHasNextPairCust;
    private CustList<RendDynOperationNode> expsNextPairCust;
    private CustList<RendDynOperationNode> expsFirstCust;
    private CustList<RendDynOperationNode> expsSecondCust;
    private CustList<RendDynOperationNode> expsBeforeDisplaying;

    private CustList<RendDynOperationNode> expsPut;
    private CustList<RendDynOperationNode> expsPutAll;
    private CustList<RendDynOperationNode> expsGetVal;
    private CustList<RendDynOperationNode> expsGetForms;
    private CustList<RendDynOperationNode> expsSetForms;
    private CustList<RendDynOperationNode> expsGetDataBase;
    private CustList<RendDynOperationNode> expsSetDataBase;
    private CustList<RendDynOperationNode> expsGetScope;
    private CustList<RendDynOperationNode> expsSetScope;
    private CustList<RendDynOperationNode> expsSetLanguage;
    private CustList<RendDynOperationNode> expsValidate;
    private CustList<RendDynOperationNode> expsReinit;
    private CustList<RendDynOperationNode> opsMap;
    private String validateVar;

    private String validateVarArgNewValue;
    private String validateVarArgOldValue;
    private String validateVarArgBean;
    private String validateVarArgForm;
    private String validateVarArgClassField;
    private String validateVarArgNameField;
    private String reinitVar;
    private String reinitDest;
    private String reinitRef;
    private String reinitBeanName;
    private String reinitCurrentBean;
    private String reinitCurrentUrl;
    private String reinitIdReinit;
    private final RendExecutingBlocks rendExecutingBlocks = new RendExecutingBlocks();

    private StringMap<StringMap<String>> navigation = new StringMap<StringMap<String>>();

    private DefHtmlPage custPage;

    protected BeanCustLgNames(AbstractGenerator _gene) {
        super(_gene);
    }

    public RendStackCall execute(boolean _form, Element _elt, Navigation _navigation, ContextEl _ctx) {
        RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING,_ctx);
        if (_form) {
            processRendFormRequest(_navigation, _ctx, rendStackCall_, _elt);
        } else {
            processRendAnchorRequest(_elt, _navigation, _ctx, rendStackCall_, true);
        }
        return rendStackCall_;
    }

    public Struct getRendUrlDest(String _method, Struct _return, ContextEl _context, RendStackCall _stackCall) {
        Struct case_ = RendDynOperationNode.processString(_return, _context, _stackCall);
        return nullify(case_, select(_method, NumParsers.getString(case_).getInstance(), navigation));
    }

    public static Struct select(String _method, String _ca, StringMap<StringMap<String>> _navigation) {
        StringMap<String> casesList_ = _navigation.getVal(_method);
        if (casesList_ == null) {
            return NullStruct.NULL_VALUE;
        }
        return BeanLgNames.wrapStd(casesList_.getVal(_ca));
    }

    public void buildOtherBean() {
        beanAliases.buildOther(getContent(), getRendExecutingBlocks());
    }

    public RendExecutingBlocks getRendExecutingBlocks() {
        return rendExecutingBlocks;
    }

    private void buildIterables(Classes _classes) {
        StringMap<String> args_ = new StringMap<String>();
        StringList l_ = new StringList();
        String locName_ = tr(l_);
        iteratorVar = locName_;
        String simpleIterator_ = getContent().getPredefTypes().getAliasIterator();
        expsIterator= newCall(iteratorVar, StringUtil.concat(getContent().getPredefTypes().getAliasIterable(), SIMPLE_WILD_CARD),
                new ClassMethodId(getContent().getPredefTypes().getAliasIterable(),new MethodId(MethodAccessKind.INSTANCE,simpleIterator_,new StringList(
                ))),
                StringUtil.concat(getContent().getPredefTypes().getAliasIteratorType(), SIMPLE_WILD_CARD), args_, _classes);
        locName_ = tr(l_);
        hasNextVar = locName_;
        String hasNext_ = getContent().getPredefTypes().getAliasHasNext();
        expsHasNext= newCall(hasNextVar, StringUtil.concat(getContent().getPredefTypes().getAliasIteratorType(), SIMPLE_WILD_CARD),
                new ClassMethodId(getContent().getPredefTypes().getAliasIteratorType(),new MethodId(MethodAccessKind.INSTANCE,hasNext_,new StringList(
                ))),
                getAliasPrimBoolean(), args_, _classes);
        locName_ = tr(l_);
        nextVar = locName_;
        String next_ = getContent().getPredefTypes().getAliasNext();
        expsNext= newCall(nextVar, StringUtil.concat(getContent().getPredefTypes().getAliasIteratorType(), SIMPLE_WILD_CARD),
                new ClassMethodId(getContent().getPredefTypes().getAliasIteratorType(),new MethodId(MethodAccessKind.INSTANCE,next_,new StringList(
                ))),
                getAliasObject(), args_, _classes);

        String nextPair_ = getContent().getPredefTypes().getAliasNextPair();
        String hasNextPair_ = getContent().getPredefTypes().getAliasHasNextPair();
        iteratorTableVarCust= locName_;
        String iteratorTable_ = getContent().getPredefTypes().getAliasIteratorTable();
        expsIteratorTableCust= newCall(iteratorTableVarCust, StringUtil.concat(getContent().getPredefTypes().getAliasIterableTable(), DOUBLE_WILD_CARD),
                new ClassMethodId(getContent().getPredefTypes().getAliasIterableTable(),new MethodId(MethodAccessKind.INSTANCE,iteratorTable_,new StringList(
                ))),
                StringUtil.concat(getContent().getPredefTypes().getAliasIteratorTableType(), DOUBLE_WILD_CARD), args_, _classes);
        locName_ = tr(l_);
        hasNextPairVarCust= locName_;
        expsHasNextPairCust= newCall(hasNextPairVarCust, StringUtil.concat(getContent().getPredefTypes().getAliasIteratorTableType(), DOUBLE_WILD_CARD),
                new ClassMethodId(getContent().getPredefTypes().getAliasIteratorTableType(),new MethodId(MethodAccessKind.INSTANCE,hasNextPair_,new StringList(
                ))),
                getAliasPrimBoolean(), args_, _classes);
        locName_ = tr(l_);
        nextPairVarCust= locName_;
        expsNextPairCust= newCall(nextPairVarCust, StringUtil.concat(getContent().getPredefTypes().getAliasIteratorTableType(), DOUBLE_WILD_CARD),
                new ClassMethodId(getContent().getPredefTypes().getAliasIteratorTableType(),new MethodId(MethodAccessKind.INSTANCE,nextPair_,new StringList(
                ))),
                StringUtil.concat(getContent().getPredefTypes().getAliasPairType(), DOUBLE_WILD_CARD), args_, _classes);
        locName_ = tr(l_);
        firstVarCust= locName_;
        String first_ = getContent().getPredefTypes().getAliasGetFirst();
        expsFirstCust= newCall(firstVarCust, StringUtil.concat(getContent().getPredefTypes().getAliasPairType(), DOUBLE_WILD_CARD),
                new ClassMethodId(getContent().getPredefTypes().getAliasPairType(),new MethodId(MethodAccessKind.INSTANCE,first_,new StringList(
                ))),
                getAliasObject(), args_, _classes);
        locName_ = tr(l_);
        secondVarCust= locName_;
        String second_ = getContent().getPredefTypes().getAliasGetSecond();
        expsSecondCust= newCall(secondVarCust, StringUtil.concat(getContent().getPredefTypes().getAliasPairType(), DOUBLE_WILD_CARD),
                new ClassMethodId(getContent().getPredefTypes().getAliasPairType(),new MethodId(MethodAccessKind.INSTANCE,second_,new StringList(
                ))),
                getAliasObject(), args_, _classes);

        locName_ = tr(l_);
        beforeDisplayingVar = locName_;
        String beforeDisplaying_ = beanAliases.getAliasBeforeDisplaying();
        expsBeforeDisplaying= newCall(beforeDisplayingVar, beanAliases.getAliasBean(),
                new ClassMethodId(beanAliases.getAliasBean(),new MethodId(MethodAccessKind.INSTANCE,beforeDisplaying_,new StringList(
                ))),
                getAliasObject(), args_, _classes);

        locName_ = tr(l_);
        putVarCust = locName_;
        locName_ = tr(l_);
        putVarCustKey = locName_;
        locName_ = tr(l_);
        putVarCustValue = locName_;
        String put_ = beanAliases.getAliasMapPut();
        args_ = new StringMap<String>();
        args_.addEntry(putVarCustKey, getAliasString());
        args_.addEntry(putVarCustValue, getAliasObject());
        expsPut= newCall(putVarCust, beanAliases.getAliasStringMapObject(),
                new ClassMethodId(beanAliases.getAliasStringMapObject(),new MethodId(MethodAccessKind.INSTANCE,put_,new StringList(
                        getAliasString(),
                        getAliasObject()
                ))),
                getAliasObject(), args_, _classes);

        locName_ = tr(l_);
        putAllVarCust = locName_;
        locName_ = tr(l_);
        putAllVarCustArg = locName_;
        String putAll_ = beanAliases.getAliasMapPutAll();
        args_ = new StringMap<String>();
        args_.addEntry(putAllVarCustArg, beanAliases.getAliasStringMapObject());
        expsPutAll= newCall(putAllVarCust, beanAliases.getAliasStringMapObject(),
                new ClassMethodId(beanAliases.getAliasStringMapObject(),new MethodId(MethodAccessKind.INSTANCE,putAll_,new StringList(
                        beanAliases.getAliasStringMapObject()
                ))),
                getAliasObject(), args_, _classes);

        locName_ = tr(l_);
        getValVar = locName_;
        locName_ = tr(l_);
        getValVarArg = locName_;
        String getVal_ = beanAliases.getAliasMapGetVal();
        args_ = new StringMap<String>();
        args_.addEntry(getValVarArg, getAliasString());
        expsGetVal= newCall(getValVar, beanAliases.getAliasStringMapObject(),
                new ClassMethodId(beanAliases.getAliasStringMapObject(),new MethodId(MethodAccessKind.INSTANCE,getVal_,new StringList(
                        getAliasString()
                ))),
                getAliasObject(), args_, _classes);

        locName_ = tr(l_);
        setFormsVar = locName_;
        locName_ = tr(l_);
        setFormsVarArg = locName_;
        String setForms_ = beanAliases.getAliasSetForms();
        args_ = new StringMap<String>();
        args_.addEntry(setFormsVarArg, beanAliases.getAliasStringMapObject());
        expsSetForms= newCall(setFormsVar, beanAliases.getAliasBean(),
                new ClassMethodId(beanAliases.getAliasBean(),new MethodId(MethodAccessKind.INSTANCE,setForms_,new StringList(
                        beanAliases.getAliasStringMapObject()
                ))),
                getAliasObject(), args_, _classes);

        locName_ = tr(l_);
        getFormsVar = locName_;
        String getForms_ = beanAliases.getAliasGetForms();
        args_ = new StringMap<String>();
        expsGetForms= newCall(getFormsVar, beanAliases.getAliasBean(),
                new ClassMethodId(beanAliases.getAliasBean(),new MethodId(MethodAccessKind.INSTANCE,getForms_,new StringList(
                ))),
                beanAliases.getAliasStringMapObject(), args_, _classes);

        locName_ = tr(l_);
        setDataBaseVar = locName_;
        locName_ = tr(l_);
        setDataBaseVarArg = locName_;
        String setDataBase_ = beanAliases.getAliasSetDataBase();
        args_ = new StringMap<String>();
        args_.addEntry(setDataBaseVarArg, getAliasObject());
        expsSetDataBase= newCall(setDataBaseVar, beanAliases.getAliasBean(),
                new ClassMethodId(beanAliases.getAliasBean(),new MethodId(MethodAccessKind.INSTANCE,setDataBase_,new StringList(
                        getAliasObject()
                ))),
                getAliasObject(), args_, _classes);

        locName_ = tr(l_);
        getDataBaseVar = locName_;
        String getDataBase_ = beanAliases.getAliasGetDataBase();
        args_ = new StringMap<String>();
        expsGetDataBase= newCall(getDataBaseVar, beanAliases.getAliasBean(),
                new ClassMethodId(beanAliases.getAliasBean(),new MethodId(MethodAccessKind.INSTANCE,getDataBase_,new StringList(
                ))),
                getAliasObject(), args_, _classes);

        locName_ = tr(l_);
        setScopeVar = locName_;
        locName_ = tr(l_);
        setScopeVarArg = locName_;
        String setScope_ = beanAliases.getAliasSetScope();
        args_ = new StringMap<String>();
        args_.addEntry(setScopeVarArg, getAliasString());
        expsSetScope= newCall(setScopeVar, beanAliases.getAliasBean(),
                new ClassMethodId(beanAliases.getAliasBean(),new MethodId(MethodAccessKind.INSTANCE,setScope_,new StringList(
                        getAliasString()
                ))),
                getAliasObject(), args_, _classes);

        locName_ = tr(l_);
        getScopeVar = locName_;
        String getScope_ = beanAliases.getAliasGetScope();
        args_ = new StringMap<String>();
        expsGetScope= newCall(getScopeVar, beanAliases.getAliasBean(),
                new ClassMethodId(beanAliases.getAliasBean(),new MethodId(MethodAccessKind.INSTANCE,getScope_,new StringList(
                ))),
                getAliasString(), args_, _classes);

        locName_ = tr(l_);
        setLanguageVar = locName_;
        locName_ = tr(l_);
        setLanguageVarArg = locName_;
        String setLanguage_ = beanAliases.getAliasSetLanguage();
        args_ = new StringMap<String>();
        args_.addEntry(setLanguageVarArg, getAliasString());
        expsSetLanguage= newCall(setLanguageVar, beanAliases.getAliasBean(),
                new ClassMethodId(beanAliases.getAliasBean(),new MethodId(MethodAccessKind.INSTANCE,setLanguage_,new StringList(
                        getAliasString()
                ))),
                getAliasObject(), args_, _classes);

        locName_ = tr(l_);
        validateVar = locName_;
        locName_ = tr(l_);
        validateVarArgNewValue = locName_;
        locName_ = tr(l_);
        validateVarArgOldValue = locName_;
        locName_ = tr(l_);
        validateVarArgBean = locName_;
        locName_ = tr(l_);
        validateVarArgForm = locName_;
        locName_ = tr(l_);
        validateVarArgClassField = locName_;
        locName_ = tr(l_);
        validateVarArgNameField = locName_;

        String validate_ = beanAliases.getAliasValidate();
        args_ = new StringMap<String>();
        args_.addEntry(validateVarArgNewValue, getAliasObject());
        args_.addEntry(validateVarArgOldValue, getAliasObject());
        args_.addEntry(validateVarArgBean, getAliasObject());
        args_.addEntry(validateVarArgForm,StringExpUtil.getPrettyArrayType(getAliasObject()));
        args_.addEntry(validateVarArgClassField, getAliasString());
        args_.addEntry(validateVarArgNameField, getAliasString());
        expsValidate = newCall(validateVar, beanAliases.getAliasValidator(),
                new ClassMethodId(beanAliases.getAliasValidator(),new MethodId(MethodAccessKind.INSTANCE,validate_,new StringList(
                        getAliasObject(),
                        getAliasObject(),
                        getAliasObject(),
                        StringExpUtil.getPrettyArrayType(getAliasObject()),
                        getAliasString(),
                        getAliasString()
                ))),
                getAliasObject(), args_, _classes);
        reinitVar = tr(l_);
        reinitDest = tr(l_);
        reinitRef = tr(l_);
        reinitBeanName = tr(l_);
        reinitCurrentBean = tr(l_);
        reinitCurrentUrl = tr(l_);
        reinitIdReinit = tr(l_);
        args_ = new StringMap<String>();
        args_.addEntry(reinitDest, getAliasString());
        args_.addEntry(reinitRef, getAliasString());
        args_.addEntry(reinitBeanName, getAliasString());
        args_.addEntry(reinitCurrentBean,getAliasString());
        args_.addEntry(reinitCurrentUrl, getAliasString());
        args_.addEntry(reinitIdReinit, getAliasString());
        expsReinit = newCall(reinitVar, beanAliases.getAliasReinitInterface(),
                new ClassMethodId(beanAliases.getAliasReinitInterface(),new MethodId(MethodAccessKind.INSTANCE,beanAliases.getAliasReinitMethod(),new StringList(
                        getAliasString(),
                        getAliasString(),
                        getAliasString(),
                        getAliasString(),
                        getAliasString(),
                        getAliasString()
                ))),
                getAliasObject(), args_, _classes);

        newInstance(_classes);
    }

    private static CustList<RendDynOperationNode> newCall(String _varPrevious, String _previous,
                                                          ClassMethodId _id,
                                                          String _res,
                                                          StringMap<String> _args, Classes _classes) {
        CustList<RendDynOperationNode> ops_ = new CustList<RendDynOperationNode>();
        ExecClassArgumentMatching clMatch_ = new ExecClassArgumentMatching(_res);
        RendDotOperation dot_ = new RendDotOperation(new ExecOperationContent(0, clMatch_, _args.size()+2));
        RendInternVariableOperation r_ = new RendInternVariableOperation(0,new ExecClassArgumentMatching(_previous),0,_varPrevious);
        ops_.add(r_);
        dot_.appendChild(r_);
        ExecFormattedRootBlock formattedType_ = ExecFormattedRootBlock.build(_id.getClassName(),_classes);
        ExecRootBlock classBody_ = formattedType_.getRootBlock();
        ExecNamedFunctionBlock fct_ = ExecClassesUtil.getMethodBodiesById(classBody_, _id.getConstraints()).first();
        ExecTypeFunction p_ = new ExecTypeFunction(formattedType_,fct_);
        RendFctOperation f_ = new RendFctOperation(p_, new ExecOperationContent(1, clMatch_, _args.size()+1), new ExecInstFctContent(formattedType_), true, new ExecArrContent(false));
        int i_ = 1;
        for (EntryCust<String,String> e: _args.entryList()) {
            RendInternVariableOperation a_ = new RendInternVariableOperation(i_-1,new ExecClassArgumentMatching(e.getValue()),i_,e.getKey());
            f_.appendChild(a_);
            ops_.add(a_);
            i_++;
        }
        dot_.appendChild(f_);
        r_.setSiblingSet(f_);
        ops_.add(f_);
        ops_.add(dot_);
        return ops_;
    }
    private void newInstance(Classes _classes) {
        opsMap = new CustList<RendDynOperationNode>();
        String aliasStringMapObject_ = beanAliases.getAliasStringMapObject();
        ExecFormattedRootBlock formattedType_ = ExecFormattedRootBlock.build(aliasStringMapObject_,_classes);
        ExecClassArgumentMatching clMatch_ = new ExecClassArgumentMatching(aliasStringMapObject_);
        ConstructorId id_ = new ConstructorId(aliasStringMapObject_, new StringList(), false);
        AnaInstancingCommonContent cont_ = new AnaInstancingCommonContent(id_.getName());
        cont_.setConstId(id_);
        ExecTypeFunction pair_ = new ExecTypeFunction(formattedType_, null);
        opsMap.add(new RendStandardInstancingOperation(new ExecOperationContent(0, clMatch_, 0), new ExecInstancingCustContent(cont_,pair_, formattedType_), new ExecInstancingStdContent(new AnaInstancingStdContent(), new CustList<ExecNamedFieldContent>(), new CustList<ExecFormattedRootBlock>())));
    }

    private static String tr(StringList _list) {
        return ValidatorStandard.tr(_list);
    }

    public ResultContext setupAll(DualNavigationContext _dual, AbsContextGenerator _gene) {
        Navigation nav_ = _dual.getNavigation();
        Configuration session_ = nav_.getSession();
        navigation = session_.getNavigation();
        StringMap<String> files_ = nav_.getFiles();
        StringList languages_ = nav_.getLanguages();
        String language_ = nav_.getLanguage();
        DualAnalyzedContext ana_ = _dual.getDualAnalyzedContext();
        AnalyzedPageEl page_ = ana_.getAnalyzed();
        Forwards forwards_ = ana_.getForwards();
        DualConfigurationContext confCont_ = ana_.getContext();
        StringMap<String> copy_ = setupRendClasses(forwards_,files_, confCont_.getFilesConfName(), confCont_.getAddedResources(), confCont_.getRenderFiles());
        ResultContext r_ = new ResultContext(page_, forwards_);
        StringMap<String> all_ = forwards_.getResources();
        AnalyzedPageEl usAn_ = ResultContext.defFilterUser(page_, all_, copy_);
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        analyzingDoc_.setContent(this);
        FileBlock blockConf_ = ana_.getBlock();
        session_.initInstancesPattern(usAn_, analyzingDoc_,blockConf_);
        preInitBeans(session_);
        analyzingDoc_.setRendAnalysisMessages(confCont_.getAnalysisMessages());
        analyzingDoc_.setLanguages(languages_);
        session_.setCurrentLanguage(language_);
        StringMap<AnaRendDocumentBlock> d_ = session_.analyzedRenders(files_, analyzingDoc_, usAn_, confCont_, blockConf_);
        ResultContext.postValidate(usAn_);
        ResultContext user_ = ResultContext.afterDef(r_, all_, usAn_, new DefStackStopper());
        if (usAn_.notAllEmptyErrors()) {
            return user_;
        }
        ForwardInfos.generalForward(user_);
        StringMap<RendDocumentBlock> renders_ = new StringMap<RendDocumentBlock>();
        rendExecutingBlocks.setRendDocumentBlock(RendForwardInfos.buildExec(analyzingDoc_, d_, user_, session_, renders_));
        rendExecutingBlocks.getRenders().addAllEntries(renders_);
        ContextEl context_ = forwardAndClear(user_.getForwards(),_gene);
        user_.setContext(context_);
        Classes.tryInit(user_);
        return user_;
    }

    public ContextEl forwardAndClear(Forwards _forward, AbsContextGenerator _gene) {
        ContextEl ctx_ = _gene.gene(_forward);
        Classes.forwardAndClear(ctx_);
        buildIterables(ctx_.getClasses());
        return ctx_;
    }

    private static StringMap<String> setupRendClasses(Forwards _fwd, StringMap<String> _files, String _filesConfName, StringList _added, StringList _render) {
        StringList content_ = new StringList();
        for (EntryCust<String, String> e: _files.entryList()) {
            if (StringUtil.quickEq(e.getKey(), _filesConfName)) {
                content_ = StringUtil.splitStrings(e.getValue(), RETURN_LINE);
                break;
            }
        }
        StringList intersect_ = StringUtil.intersect(content_, _render);
        StringUtil.removeAllElements(content_,intersect_);
        StringUtil.removeObj(content_,_filesConfName);
        StringMap<String> classFiles_ = new StringMap<String>();
        for (String f: content_) {
            for (EntryCust<String, String> e: _files.entryList()) {
                if (StringUtil.quickEq(e.getKey(), f)) {
                    classFiles_.put(f, e.getValue());
                    break;
                }
            }
        }
        StringMap<String> resFiles_ = new StringMap<String>();
        for (EntryCust<String, String> e: _files.entryList()) {
            if (StringUtil.contains(_added,e.getKey())) {
                resFiles_.put(e.getKey(), e.getValue());
            }
        }
        //!classFiles_.isEmpty()
        _fwd.getResources().addAllEntries(resFiles_);
        return classFiles_;
    }

    public void preInitBeans(Configuration _conf) {
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            getBuiltBeans().addEntry(e.getKey(),NullStruct.NULL_VALUE);
        }
        for (EntryCust<String, ValidatorInfo> e: _conf.getLateValidators().entryList()) {
            rendExecutingBlocks.getBuiltValidators().addEntry(e.getKey(),NullStruct.NULL_VALUE);
        }
        for (EntryCust<String, ValidatorInfo> e: _conf.getLateReinits().entryList()) {
            rendExecutingBlocks.getBuiltReinit().addEntry(e.getKey(),NullStruct.NULL_VALUE);
        }
    }

    @Override
    public HtmlPageInt getPage() {
        return getCustPage();
    }

    public DefHtmlPage getCustPage() {
        return custPage;
    }

    public void setCustPage(DefHtmlPage _custPage) {
        this.custPage = _custPage;
    }

    public boolean initBeans(Configuration _conf, String _language, Struct _db, ContextEl _ctx, RendStackCall _rendStack) {
        int index_ = 0;
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            BeanInfo info_ = e.getValue();
            _rendStack.addPage(new ImportingPage());
            Struct arg_ = RenderExpUtil.getFinalArg(info_.getExps(), _ctx, _rendStack);
            if (arg_ == null) {
                _rendStack.removeLastPage();
                return false;
            }
            String clName_ = arg_.getClassName(_ctx);
            if (!ExecInherits.isCorrectExecute(clName_, beanAliases.getAliasBean(), _ctx)) {
                _rendStack.removeLastPage();
                getBuiltBeans().setValue(index_, arg_);
                index_++;
                continue;
            }
            Argument mapArg_ = new Argument(RenderExpUtil.getFinalArg(opsMap, _ctx, _rendStack));
            ExecRootBlock rootBlock_ = _ctx.getClasses().getClassBody(beanAliases.getAliasBean());
            ExecFieldTemplates.setInstanceField(
                    new Argument(arg_),mapArg_, _ctx, _rendStack.getStackCall(), new ClassField(beanAliases.getAliasBean(), beanAliases.getAliasForms()), new ExecTypeReturn(rootBlock_, beanAliases.getAliasStringMapObject()));
            ExecFieldTemplates.setInstanceField(
                    new Argument(arg_),new Argument(_db), _ctx, _rendStack.getStackCall(), new ClassField(beanAliases.getAliasBean(), beanAliases.getAliasDataBaseField()), new ExecTypeReturn(rootBlock_, getAliasObject()));
            ExecFieldTemplates.setInstanceField(
                    new Argument(arg_),new Argument(new StringStruct(_language)), _ctx, _rendStack.getStackCall(), new ClassField(beanAliases.getAliasBean(), beanAliases.getAliasLanguage()), new ExecTypeReturn(rootBlock_, getAliasString()));
            ExecFieldTemplates.setInstanceField(
                    new Argument(arg_),new Argument(new StringStruct(info_.getScope())), _ctx, _rendStack.getStackCall(), new ClassField(beanAliases.getAliasBean(), beanAliases.getAliasScope()), new ExecTypeReturn(rootBlock_, getAliasString()));
            _rendStack.removeLastPage();
            getBuiltBeans().setValue(index_, arg_);
            index_++;
        }
        index_ = 0;
        for (EntryCust<String, ValidatorInfo> e: _conf.getLateValidators().entryList()) {
            int i_ = init(_ctx, _rendStack, e, index_, rendExecutingBlocks.getBuiltValidators());
            if (i_ == index_) {
                return false;
            }
            index_ = i_;
        }
        index_ = 0;
        for (EntryCust<String, ValidatorInfo> e: _conf.getLateReinits().entryList()) {
            int i_ = init(_ctx, _rendStack, e, index_, rendExecutingBlocks.getBuiltReinit());
            if (i_ == index_) {
                return false;
            }
            index_ = i_;
        }
        return true;
    }
    private int init(ContextEl _ctx, RendStackCall _rendStack, EntryCust<String, ValidatorInfo> _e, int _index, StringMap<Struct> _builtValidators) {
        ValidatorInfo info_ = _e.getValue();
        _rendStack.addPage(new ImportingPage());
        Struct arg_ = RenderExpUtil.getFinalArg(info_.getExps(), _ctx, _rendStack);
        _rendStack.removeLastPage();
        if (arg_ == null) {
            return _index;
        }
        _builtValidators.setValue(_index,arg_);
        return _index+1;
    }

    public void initializeRendSessionDoc(ContextEl _ctx, Navigation _nav, RendStackCall _rendStackCall) {
        if (rendExecutingBlocks.getRendDocumentBlock() == null) {
            return;
        }
        _rendStackCall.init();
        String lg_ = _nav.getLanguage();
        Configuration session_ = _nav.getSession();
        if (!initBeans(session_,lg_,_nav.getDataBaseStruct(), _ctx, _rendStackCall)) {
            return;
        }
        String currentUrl_ = session_.getFirstUrl();
        Struct bean_ = getBeanOrNull(_nav.getCurrentBeanName());
        proc(_nav, _ctx, _rendStackCall, currentUrl_, bean_, _nav.getCurrentBeanName());
    }

    public void processRendAnchorRequest(Element _ancElt, Navigation _nav, ContextEl _ctx, RendStackCall _rendStack, boolean _anchor) {
        if (_ancElt == null) {
            return;
        }
        Configuration session_ = _nav.getSession();
        String actionCommand_ = _ancElt.getAttribute(StringUtil.concat(session_.getPrefix(),session_.getRendKeyWords().getAttrCommand()));
        String sgn_ = _ancElt.getAttribute(StringUtil.concat(session_.getPrefix(),session_.getRendKeyWords().getAttrSgn()));
        if (!sgn_.isEmpty()) {
            _rendStack.clearPages();
            ImportingPage ip_ = new ImportingPage();
            _rendStack.addPage(ip_);
            int indexPoint_ = actionCommand_.indexOf(DOT);
//                    .substring(actionCommand_.indexOf(CALL_METHOD) + 1);
            Struct bean_ = getBeanOrNull(actionCommand_);
            ip_.setOffset(indexPoint_+1);
            setGlobalArgumentStruct(bean_,_ctx,_rendStack);
            Struct return_ = redirect(custPage,bean_,_ctx,_rendStack);
            if (return_ == null) {
                return;
            }
            Struct urlDest_ = BeanLgNames.wrapStd(_nav.getCurrentUrl());
            if (return_ != NullStruct.NULL_VALUE) {
                ip_.setOffset(actionCommand_.length());
                urlDest_ = getRendUrlDest(sgn_, return_, _ctx, _rendStack);
                if (urlDest_ == null) {
                    return;
                }
                if (urlDest_ == NullStruct.NULL_VALUE) {
                    urlDest_ = BeanLgNames.wrapStd(_nav.getCurrentUrl());
                }
            }
            proc(_nav, _ctx, _rendStack, NumParsers.getString(urlDest_).getInstance(), bean_, actionCommand_);
            return;
        }
        Struct bean_ = getBeanOrNull(_nav.getCurrentBeanName());
        if (_anchor) {
            proc(_nav, _ctx, _rendStack, _ancElt.getAttribute(session_.getRendKeyWords().getAttrHref()), bean_, _nav.getCurrentBeanName());
        } else {
            proc(_nav, _ctx, _rendStack, _ancElt.getAttribute(session_.getRendKeyWords().getAttrAction()), bean_, _nav.getCurrentBeanName());
        }
    }

    private void proc(Navigation _nav, ContextEl _ctx, RendStackCall _rendStack, String _actionCommand, Struct _bean, String _currentBeanName) {
        _rendStack.clearPages();
        String res_ = processAfterInvoke(_nav, _actionCommand, _currentBeanName, _bean, _ctx, _rendStack);
        setup(_nav,res_, _rendStack, _actionCommand);
        if (!_nav.setupText(res_, _rendStack.getDocument())) {
            return;
        }
        setCustPage(_rendStack.getHtmlPage());
    }

    private void setup(Navigation _nav, String _res, RendStackCall _rendStack, String _dest) {
        if (_res.isEmpty()) {
            return;
        }
        _nav.setCurrentBeanName(_rendStack.getBeanName());
        _nav.setCurrentUrl(_dest);
    }
    public String processAfterInvoke(Navigation _nav, String _dest, String _beanName, Struct _bean, ContextEl _ctx, RendStackCall _rendStack) {
        String curl_ = _nav.getCurrentUrl();
        ImportingPage ip_ = new ImportingPage();
        _rendStack.addPage(ip_);
        Struct forms_ = NullStruct.NULL_VALUE;
        if (!_beanName.isEmpty()) {
            forms_ = storeForms(_bean, _ctx, _rendStack);
        }
        if (forms_ == null) {
            return "";
        }
        if (!processInitBeans(_nav, _dest,_beanName, curl_, _ctx, _rendStack)) {
            return "";
        }
        String dest_ = StringUtil.getFirstToken(_dest, REF_TAG);
        RendDocumentBlock rendDocumentBlock_ = rendExecutingBlocks.getRenders().getVal(dest_);
        if (rendDocumentBlock_ == null) {
            return "";
        }
        String currentBeanName_ = rendDocumentBlock_.getBeanName();
        Struct bean_ = getBeanOrNull(currentBeanName_);
        Struct formsBis_ = NullStruct.NULL_VALUE;
        if (!_beanName.isEmpty()) {
            formsBis_ = setStoredForms(bean_, forms_, _ctx, _rendStack);
        }
        if (formsBis_ == null) {
            return "";
        }
        _rendStack.clearPages();
        return getRes(rendDocumentBlock_,_nav.getSession(), this, _ctx, _rendStack);
    }

    public void setGlobalArgumentStruct(Struct _obj, ContextEl _ctx, RendStackCall _rendStackCall) {
        _rendStackCall.getLastPage().setGlobalArgumentStruct(_obj, _ctx);
    }

    public String getRes(RendDocumentBlock _rend, Configuration _conf, BeanCustLgNames _stds, ContextEl _ctx, RendStackCall _rendStackCall) {
//        _rendStackCall.getFormParts().initFormsSpec();
//        String beanName_ = _rend.getBeanName();
//        Struct bean_ = getBuiltBeans().getVal(beanName_);
//        _rendStackCall.setMainBean(bean_);
//        _rendStackCall.addPage(new ImportingPage());
//        RendImport.befDisp(_stds,_ctx, _rendStackCall, bean_);
//        _rendStackCall.removeLastPage();
        return RendBlock.res(_rend, _conf, _stds, _ctx, _rendStackCall);
    }

    public Struct convert(DefNodeContainer _container, ContextEl _context, RendStackCall _rendStackCall) {
        CustList<RendDynOperationNode> ops_ = _container.getOpsConvert();
        if (!ops_.isEmpty()) {
            LocalVariable lv_ = newLocVar(_container);
            _rendStackCall.getLastPage().putValueVar("0", new VariableWrapper(lv_));
            setGlobalArgumentStruct(_container.getBean(),_context,_rendStackCall);
            Struct res_ = RenderExpUtil.getFinalArg(ops_, _context, _rendStackCall);
            _rendStackCall.getLastPage().removeRefVar("0");
            return res_;
        }
        String className_ = _container.getNodeInformation().getInputClass();
        StringList values_ = _container.getNodeInformation().getValue();
        return getStructToBeValidated(values_, className_, _context, _rendStackCall);
    }
    private LocalVariable newLocVar(DefNodeContainer _container) {
        StringList values_ = _container.getNodeInformation().getValue();
        if (_container.isArrayConverter()) {
            int len_ = values_.size();
            ArrayStruct arr_ = new ArrayStruct(len_,StringExpUtil.getPrettyArrayType(getAliasString()));
            for (int i = 0; i < len_; i++) {
                arr_.set(i, new StringStruct(values_.get(i)));
            }
            return LocalVariable.newLocalVariable(arr_,StringExpUtil.getPrettyArrayType(getAliasString()));
        }
        if (!values_.isEmpty()) {
            return LocalVariable.newLocalVariable(new StringStruct(values_.first()), getAliasString());
        }
        return LocalVariable.newLocalVariable(NullStruct.NULL_VALUE, getAliasString());
    }
    public Struct getStructToBeValidated(StringList _values, String _className, ContextEl _ctx, RendStackCall _stack) {
        if (StringUtil.quickEq(_className, getAliasString())) {
            return wrapStd(_values);
        }
        if (_values.isEmpty() || _values.first().trim().isEmpty()) {
            return NullStruct.NULL_VALUE;
        }
        return getStructToBeValidatedPrim(_values, _className, _ctx, _stack);
    }

    private boolean processInitBeans(Navigation _nav, String _dest, String _beanName, String _currentUrl, ContextEl _ctx, RendStackCall _rendStackCall) {
        int s_ = getBuiltBeans().size();
        for (int i = 0; i < s_; i++) {
            if (exitInit(_nav, _dest,_beanName,_currentUrl, _ctx,_rendStackCall,i)) {
                return false;
            }
        }
        return true;
    }
    private boolean exitInit(Navigation _nav, String _dest, String _beanName, String _currentUrl, ContextEl _ctx, RendStackCall _rendStackCall, int _i) {
        Configuration session_ = _nav.getSession();
        String lg_ = _nav.getLanguage();
        String key_ = getBuiltBeans().getKey(_i);
        Struct reinit_ = reinitRendBean(_dest, _beanName, key_, _currentUrl, _ctx, _rendStackCall);
        if (reinit_ == null) {
            return true;
        }
        if (BooleanStruct.isFalse(reinit_)) {
            return false;
        }
        Struct bean_ = getBuiltBeans().getValue(_i);
        BeanInfo info_ = session_.getBeansInfos().getValue(_i);
        bean_ = newBean(lg_, bean_,info_, _ctx, _rendStackCall);
        if (bean_ == null) {
            return true;
        }
        getBuiltBeans().setValue(_i,bean_);
        return false;
    }
    private Struct reinitRendBean(String _dest, String _beanName, String _currentBean, String _currentUrl, ContextEl _ctx, RendStackCall _rendStackCall) {
        Struct bean_ = getBeanOrNull(_currentBean);
        if (!ExecInherits.isCorrectExecute(bean_.getClassName(_ctx), beanAliases.getAliasBean(), _ctx)) {
            return BooleanStruct.of(false);
        }
        Struct scope_ = getScope(bean_, _ctx, _rendStackCall);
        if (scope_ == null) {
            return null;
        }
        if (!(scope_ instanceof StringStruct)) {
            return BooleanStruct.of(false);
        }
        String scopeValue_ = ((StringStruct)scope_).getInstance();
        Struct reinit_ = rendExecutingBlocks.getBuiltReinit().getVal(scopeValue_);
        if (reinit_ != null) {
            _rendStackCall.getLastPage().putInternVars(reinitVar, reinit_, _ctx);
            String dest_ = StringUtil.getFirstToken(_dest, REF_TAG);
            _rendStackCall.getLastPage().putInternVars(reinitDest, LocalVariable.newLocalVariable(new StringStruct(dest_),getAliasString()));
            _rendStackCall.getLastPage().putInternVars(reinitRef, LocalVariable.newLocalVariable(new StringStruct(_dest.substring(dest_.length())), getAliasString()));
            _rendStackCall.getLastPage().putInternVars(reinitBeanName, LocalVariable.newLocalVariable(new StringStruct(_beanName), getAliasString()));
            _rendStackCall.getLastPage().putInternVars(reinitCurrentBean, LocalVariable.newLocalVariable(new StringStruct(_currentBean), getAliasString()));
            _rendStackCall.getLastPage().putInternVars(reinitCurrentUrl, LocalVariable.newLocalVariable(new StringStruct(_currentUrl), getAliasString()));
            _rendStackCall.getLastPage().putInternVars(reinitIdReinit, LocalVariable.newLocalVariable(scope_, getAliasString()));
            _rendStackCall.getLastPage().setEnabledOp(false);
            Struct arg_ = RenderExpUtil.getFinalArg(expsReinit, _ctx, _rendStackCall);
            _rendStackCall.getLastPage().setEnabledOp(true);
            _rendStackCall.getLastPage().clearInternVars();
            return arg_;
        }
        return BooleanStruct.of(false);
    }

    private Struct newBean(String _language, Struct _bean, BeanInfo _info, ContextEl _ctx, RendStackCall _rendStackCall) {
        Struct arg_ = RenderExpUtil.getFinalArg(_info.getExps(), _ctx, _rendStackCall);
        return forwardDataBase(_bean, arg_, _language, _ctx, _rendStackCall);
//        setStoredForms(arg_, NullStruct.NULL_VALUE, _ctx, _rendStackCall);
//        setLanguage(arg_, _language, _ctx, _rendStackCall);
//        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
//            return NullStruct.NULL_VALUE;
//        }
//        String str_ = getScope(_bean, _ctx, _rendStackCall);
//        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
//            return NullStruct.NULL_VALUE;
//        }
//        setScope(arg_, str_, _ctx, _rendStackCall);
//        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
//            return NullStruct.NULL_VALUE;
//        }
//        return arg_;
    }

    private Struct getBeanOrNull(String _currentBeanName) {
        Struct bean_ = getBean(_currentBeanName);
        if (bean_ == null) {
            bean_ = NullStruct.NULL_VALUE;
        }
        return bean_;
    }

    private Struct getBean(String _beanName) {
        return rendExecutingBlocks.getBuiltBeans().getVal(_beanName);
    }

    private Struct forwardDataBase(Struct _bean, Struct _to, String _language, ContextEl _ctx, RendStackCall _rendStackCall) {
//        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
//            return;
//        }
        _rendStackCall.getLastPage().putInternVars(getDataBaseVar, _bean, _ctx);
        _rendStackCall.getLastPage().setEnabledOp(false);
        Struct argument_ = RenderExpUtil.getFinalArg(expsGetDataBase, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().clearInternVars();
        if (argument_ == null) {
            _rendStackCall.getLastPage().setEnabledOp(true);
            return null;
        }
        _rendStackCall.getLastPage().putInternVars(setDataBaseVar, _to, _ctx);
        _rendStackCall.getLastPage().putInternVars(setDataBaseVarArg, argument_, _ctx);
        RenderExpUtil.getFinalArg(expsSetDataBase, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().setEnabledOp(true);
        _rendStackCall.getLastPage().clearInternVars();
        setStoredForms(_to, NullStruct.NULL_VALUE, _ctx, _rendStackCall);
        setLanguage(_to, _language, _ctx, _rendStackCall);
//        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
//            return NullStruct.NULL_VALUE;
//        }
        Struct str_ = getScope(_bean, _ctx, _rendStackCall);
//        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
//            return NullStruct.NULL_VALUE;
//        }
        return setScope(_to, str_, _ctx, _rendStackCall);
    }


    private Struct storeForms(Struct _bean, ContextEl _ctx, RendStackCall _rendStackCall) {
        Struct forms_ = getForms(_bean, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().setEnabledOp(true);
        return forms_;
    }

    private Struct getForms(Struct _bean, ContextEl _ctx, RendStackCall _rendStackCall) {
        String clName_ = _bean.getClassName(_ctx);
        _rendStackCall.getLastPage().setEnabledOp(false);
        if (!ExecInherits.isCorrectExecute(clName_, beanAliases.getAliasBean(), _ctx)) {
            return RenderExpUtil.getFinalArg(opsMap, _ctx, _rendStackCall);
        }
        _rendStackCall.getLastPage().putInternVars(getFormsVar, _bean, _ctx);
        Struct argument_ = RenderExpUtil.getFinalArg(expsGetForms, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().clearInternVars();
        if (argument_ == null) {
            return null;
        }
        if (argument_ == NullStruct.NULL_VALUE) {
            return RenderExpUtil.getFinalArg(opsMap, _ctx, _rendStackCall);
        }
        return argument_;
    }


    private Struct setStoredForms(Struct _bean, Struct _storedForms, ContextEl _ctx, RendStackCall _rendStackCall) {
//        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
//            return;
//        }
        _rendStackCall.getLastPage().setEnabledOp(false);
        _rendStackCall.getLastPage().putInternVars(setFormsVar, _bean, _ctx);
        _rendStackCall.getLastPage().putInternVars(setFormsVarArg, _storedForms, _ctx);
        Struct finalArg_ = RenderExpUtil.getFinalArg(expsSetForms, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().clearInternVars();
        _rendStackCall.getLastPage().setEnabledOp(true);
        return finalArg_;
    }

    public boolean setBeanForms(Struct _mainBean,
                                RendImport _node, boolean _keepField, String _beanName, ContextEl _ctx, RendStackCall _rendStack) {
        if (_mainBean == null) {
            return true;
        }
        Struct bean_ = getBuiltBeans().getVal(_beanName);
        if (bean_ == null) {
            return true;
        }
//        boolean ok_ = gearFw(_mainBean, _node, _keepField, bean_, _ctx, _rendStack) != null;
//        if (_ctx.callsOrException(_rendStack.getStackCall())) {
//            ok_ = false;
//        }
        return gearFw(_mainBean, _node, _keepField, bean_, _ctx, _rendStack) != null;
    }

    public StringMap<Struct> getBuiltBeans() {
        return rendExecutingBlocks.getBuiltBeans();
    }
    protected Struct gearFw(Struct _mainBean, RendImport _node, boolean _keepField, Struct _bean, ContextEl _ctx, RendStackCall _rendStack) {
        Struct forms_ = getForms(_bean, _ctx, _rendStack);
        if (forms_ == null) {
            _rendStack.getLastPage().setEnabledOp(true);
            return null;
        }
        Struct formsMap_ = getForms(_mainBean, _ctx, _rendStack);
        if (formsMap_ == null) {
            _rendStack.getLastPage().setEnabledOp(true);
            return null;
        }
        if (_keepField) {
            for (RendBlock f_: RendBlock.getDirectChildren(_node)) {
                if (!(f_ instanceof RendImportForm)) {
                    continue;
                }
                String name_ = ((RendImportForm)f_).getName();
                Struct arg_ = forwardMap(formsMap_, forms_, new StringStruct(name_), _ctx, _rendStack);
                if (arg_ == null) {
                    _rendStack.getLastPage().setEnabledOp(true);
                    return null;
                }
            }
        } else {
            //add option for copying forms (default copy)
            Struct res_ = putAllMap(forms_, formsMap_, _ctx, _rendStack);
            _rendStack.getLastPage().setEnabledOp(true);
            return res_;
        }
        _rendStack.getLastPage().setEnabledOp(true);
        return _bean;
    }

    public Struct getStructToBeValidatedPrim(StringList _values, String _className, ContextEl _ctx, RendStackCall _stack) {
        byte cast_ = ExecClassArgumentMatching.getPrimitiveWrapCast(_className, this);
        if (cast_ == PrimitiveTypes.BOOL_WRAP) {
            return BooleanStruct.of(StringUtil.quickEq(_values.first(), SetupableAnalyzingDoc.ON));
        }
        if (cast_ == PrimitiveTypes.CHAR_WRAP) {
            return new CharStruct(_values.first().trim().charAt(0));
        }
        if (cast_ > PrimitiveTypes.LONG_WRAP) {
            DoubleInfo doubleInfo_ = NumParsers.splitDouble(_values.first());
            if (!doubleInfo_.isValid()) {
                _stack.getStackCall().setCallingState(new CustomFoundExc(new ErrorStruct(_ctx, _values.first(), getContent().getCoreNames().getAliasNbFormat(), _stack.getStackCall())));
                return null;
            }
            return NumParsers.convertToFloat(cast_,new DoubleStruct(doubleInfo_.getValue()));
        }
        LongInfo val_ = NumParsers.parseLong(_values.first(), 10);
        if (!val_.isValid()) {
            _stack.getStackCall().setCallingState(new CustomFoundExc(new ErrorStruct(_ctx, _values.first(), getContent().getCoreNames().getAliasNbFormat(), _stack.getStackCall())));
            return null;
        }
        return NumParsers.convertToInt(cast_,new LongStruct(val_.getValue()));
    }
    public DefaultBeanAliases getBeanAliases() {
        return beanAliases;
    }

    private Struct forwardMap(Struct _map, Struct _to, Struct _key, ContextEl _ctx, RendStackCall _rendStackCall) {
        _rendStackCall.getLastPage().putInternVars(getValVar, _map, _ctx);
        _rendStackCall.getLastPage().putInternVars(getValVarArg, _key, _ctx);
        Struct argument_ = RenderExpUtil.getFinalArg(expsGetVal, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().clearInternVars();
//        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
//            return;
//        }
        _rendStackCall.getLastPage().putInternVars(putVarCust, _to, _ctx);
        _rendStackCall.getLastPage().putInternVars(putVarCustKey, _key, _ctx);
        _rendStackCall.getLastPage().putInternVars(putVarCustValue, argument_, _ctx);
        Struct finalArg_ = RenderExpUtil.getFinalArg(expsPut, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().clearInternVars();
        return finalArg_;
    }

    public Struct putAllMap(Struct _map, Struct _other, ContextEl _ctx, RendStackCall _rendStackCall) {
        _rendStackCall.getLastPage().putInternVars(putAllVarCust, _map, _ctx);
        _rendStackCall.getLastPage().putInternVars(putAllVarCustArg, _other, _ctx);
        Struct finalArg_ = RenderExpUtil.getFinalArg(expsPutAll, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().clearInternVars();
        return finalArg_;
    }

    public String getIteratorVar() {
        return iteratorVar;
    }

    public String getHasNextVar() {
        return hasNextVar;
    }

    public String getNextVar() {
        return nextVar;
    }

    public CustList<RendDynOperationNode> getExpsIterator() {
        return expsIterator;
    }

    public CustList<RendDynOperationNode> getExpsHasNext() {
        return expsHasNext;
    }

    public CustList<RendDynOperationNode> getExpsNext() {
        return expsNext;
    }

    public String getIteratorTableVarCust() {
        return iteratorTableVarCust;
    }

    public String getHasNextPairVarCust() {
        return hasNextPairVarCust;
    }

    public String getNextPairVarCust() {
        return nextPairVarCust;
    }

    public String getFirstVarCust() {
        return firstVarCust;
    }

    public String getSecondVarCust() {
        return secondVarCust;
    }

    private String getBeforeDisplayingVar() {
        return beforeDisplayingVar;
    }

    public CustList<RendDynOperationNode> getExpsIteratorTableCust() {
        return expsIteratorTableCust;
    }

    public CustList<RendDynOperationNode> getExpsHasNextPairCust() {
        return expsHasNextPairCust;
    }

    public CustList<RendDynOperationNode> getExpsNextPairCust() {
        return expsNextPairCust;
    }

    public CustList<RendDynOperationNode> getExpsFirstCust() {
        return expsFirstCust;
    }

    public CustList<RendDynOperationNode> getExpsSecondCust() {
        return expsSecondCust;
    }

    public void processRendFormRequest(Navigation _nav, ContextEl _ctx, RendStackCall _rendStackCall, Element _elt) {
        _rendStackCall.clearPages();
        _rendStackCall.setDocument(_nav.getDocument());
        ImportingPage ip_ = new ImportingPage();
        _rendStackCall.addPage(ip_);
        long lg_ = custPage.getUrl();
        Document doc_ = _nav.getDocument();
        //retrieving form that is submitted
        custPage.setForm(true);

        //As soon as the form is retrieved, then process on it and exit from the loop

        StringMap<Message> map_ = validateAll(custPage, _ctx, _rendStackCall);
        if (map_ == null) {
            return;
        }
        StringMap<String> errors_ = new StringMap<String>();
        StringMap<StringList> errorsArgs_ = new StringMap<StringList>();
        _nav.feedErr(map_, errors_, errorsArgs_);
        //begin deleting previous errors
        _nav.delPrevious(doc_, _elt);
        //end deleting previous errors
        if (!errors_.isEmpty()) {
            _nav.processRendFormErrors(_elt, lg_, errors_, errorsArgs_, getPage());
            _rendStackCall.clearPages();
            return;
        }
        //Setting values for bean
        boolean res_ = updateRendBean(custPage, _ctx, _rendStackCall);
        _rendStackCall.clearPages();
        if (!res_) {
            return;
        }

        //invoke application
        processRendAnchorRequest(_elt, _nav, _ctx, _rendStackCall, false);
    }

    public StringMap<Message> validateAll(DefHtmlPage _htmlPage, ContextEl _ctx, RendStackCall _rendStack) {
        LongMap<LongTreeMap<DefNodeContainer>> containersMap_;
        containersMap_ = _htmlPage.getContainers();
        long lg_ = _htmlPage.getUrl();
        StringMap<Message> map_ = new StringMap<Message>();
        for (EntryCust<Long, DefNodeContainer> e: containersMap_.getVal(lg_).entryList()) {
            DefNodeContainer nCont_ = e.getValue();
            NodeInformations nInfos_ = nCont_.getNodeInformation();
            String valId_ = nInfos_.getValidator();
            String id_ = nInfos_.getId();
            Struct validator_ = rendExecutingBlocks.getBuiltValidators().getVal(valId_);
            if (validator_ == null) {
                continue;
            }
            LocalVariable locVar_;
            _rendStack.getLastPage().putInternVars(validateVar, validator_, _ctx);
            locVar_ = newLocVar(nCont_);
            _rendStack.getLastPage().putInternVars(validateVarArgNewValue, locVar_);
            locVar_ = LocalVariable.newLocalVariable(nCont_.getTypedStruct(), getAliasObject());
            _rendStack.getLastPage().putInternVars(validateVarArgOldValue, locVar_);
            locVar_ = LocalVariable.newLocalVariable(nCont_.getBean(), getAliasObject());
            _rendStack.getLastPage().putInternVars(validateVarArgBean, locVar_);
            CustList<Struct> params_ = nCont_.getStructParam();
            int size_ = params_.size();
            ArrayStruct arr_ = new ArrayStruct(size_ +1,StringExpUtil.getPrettyArrayType(getAliasObject()));
            arr_.set(0, nCont_.getAllObject().first());
            for (int i = 0; i < size_; i++) {
                arr_.set(i+1, params_.get(i));
            }
            locVar_ = LocalVariable.newLocalVariable(arr_,StringExpUtil.getPrettyArrayType(getAliasObject()));
            _rendStack.getLastPage().putInternVars(validateVarArgForm, locVar_);
            _rendStack.getLastPage().putInternVars(validateVarArgClassField, new StringStruct(nCont_.getIdFieldClass()), _ctx);
            _rendStack.getLastPage().putInternVars(validateVarArgNameField, new StringStruct(nCont_.getIdFieldName()), _ctx);
            _rendStack.getLastPage().setEnabledOp(false);
            Struct arg_ = RenderExpUtil.getFinalArg(expsValidate, _ctx, _rendStack);
            _rendStack.getLastPage().setEnabledOp(true);
            _rendStack.getLastPage().clearInternVars();
            if (arg_ == null) {
                return null;
            }
            if (arg_ != NullStruct.NULL_VALUE) {
                Message messageTr_ = DefaultBeanAliases.getMessageStruct(arg_, beanAliases.getAliasMessage()).getInstance();
                map_.put(id_, messageTr_);
            }
        }
        return map_;
    }

    public boolean updateRendBean(DefHtmlPage _htmlPage, ContextEl _ctx, RendStackCall _rendStackCall) {
        LongMap<LongTreeMap<DefNodeContainer>> containersMap_;
        containersMap_ = _htmlPage.getContainers();
        long lg_ = _htmlPage.getUrl();
        LongTreeMap< DefNodeContainer> containers_ = containersMap_.getVal(lg_);
        for (EntryCust<Long, DefNodeContainer> e: containers_.entryList()) {
            DefNodeContainer nCont_ = e.getValue();
            if (!nCont_.getNodeInformation().isEnabled()) {
                continue;
            }
            Struct res_ = convert(nCont_, _ctx, _rendStackCall);
            if (res_ == null) {
                return false;
            }
            Struct procObj_ = e.getValue().getAllObject().first();
            setGlobalArgumentStruct(procObj_,_ctx,_rendStackCall);
            RendRequestUtil.setRendObject(e.getValue(), res_, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                return false;
            }
        }
        return true;
    }
    public Struct redirect(DefHtmlPage _htmlPage, Struct _bean, ContextEl _ctx, RendStackCall _rendStack){
        int url_ = (int)_htmlPage.getUrl();
        AnchorCall exps_;
        if (_htmlPage.isForm()) {
            exps_ = _htmlPage.getCallsFormExps().get(url_);
        } else {
            exps_ = _htmlPage.getCallsExps().get(url_);
        }
        return redir(new Argument(_bean), exps_, _ctx, _rendStack);
    }

    public Struct redir(Argument _bean, AnchorCall _exps, ContextEl _context, RendStackCall _rendStackCall) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        CustList<AbstractWrapper> args_ = _exps.getArgs();
        int s_ = args_.size();
        for (int i = 0; i< s_; i++) {
            ip_.putValueVar(Long.toString(i), args_.get(i));
        }
        Argument globalArgument_ = _rendStackCall.getLastPage().getGlobalArgument();
        setGlobalArgumentStruct(_bean.getStruct(), _context, _rendStackCall);
        Struct argument_ = RenderExpUtil.getFinalArg(_exps.getExps(), _context, _rendStackCall);
        setGlobalArgumentStruct(globalArgument_.getStruct(), _context, _rendStackCall);
        for (int i = 0; i< s_; i++) {
            ip_.removeRefVar(Long.toString(i));
        }
        return argument_;
    }

    public Struct beforeDisplaying(Struct _arg, ContextEl _ctx, RendStackCall _rendStack) {
        String clName_ = _arg.getClassName(_ctx);
        if (!ExecInherits.isCorrectExecute(clName_, beanAliases.getAliasBean(), _ctx)) {
            return NullStruct.NULL_VALUE;
        }
        String locName_ = getBeforeDisplayingVar();
        _rendStack.getLastPage().putInternVars(locName_, _arg, _ctx);
        _rendStack.getLastPage().setEnabledOp(false);
        Struct finalArg_ = RenderExpUtil.getFinalArg(expsBeforeDisplaying, _ctx, _rendStack);
        _rendStack.getLastPage().setEnabledOp(true);
        _rendStack.getLastPage().clearInternVars();
        return finalArg_;
    }

    private Struct getScope(Struct _bean, ContextEl _ctx, RendStackCall _rendStackCall) {
        _rendStackCall.getLastPage().putInternVars(getScopeVar, _bean, _ctx);
        _rendStackCall.getLastPage().setEnabledOp(false);
        Struct argument_ = RenderExpUtil.getFinalArg(expsGetScope, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().setEnabledOp(true);
        _rendStackCall.getLastPage().clearInternVars();
        return argument_;
    }
    private Struct setScope(Struct _bean, Struct _scope, ContextEl _ctx, RendStackCall _rendStackCall) {
        _rendStackCall.getLastPage().putInternVars(setScopeVar, _bean, _ctx);
        _rendStackCall.getLastPage().putInternVars(setScopeVarArg, _scope, _ctx);
        _rendStackCall.getLastPage().setEnabledOp(false);
        Struct finalArg_ = RenderExpUtil.getFinalArg(expsSetScope, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().setEnabledOp(true);
        _rendStackCall.getLastPage().clearInternVars();
        return nullify(finalArg_, _bean);
    }

    public static Struct nullify(Struct _arg, Struct _bean) {
        if (_arg == null) {
            return null;
        }
        return _bean;
    }

    private void setLanguage(Struct _bean, String _scope, ContextEl _ctx, RendStackCall _rendStackCall) {
//        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
//            return;
//        }
        _rendStackCall.getLastPage().putInternVars(setLanguageVar, _bean, _ctx);
        _rendStackCall.getLastPage().putInternVars(setLanguageVarArg, new StringStruct(_scope), _ctx);
        _rendStackCall.getLastPage().setEnabledOp(false);
        RenderExpUtil.getFinalArg(expsSetLanguage, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().setEnabledOp(true);
        _rendStackCall.getLastPage().clearInternVars();
    }

    public static String processString(Struct _arg, ContextEl _ctx, RendStackCall _stack) {
        Struct arg_ = RendDynOperationNode.processString(_arg, _ctx, _stack);
        if (arg_ == null) {
            return null;
        }
        return NumParsers.getString(arg_).getInstance();
//        if (_ctx.callsOrException(_stack.getStackCall())) {
//            return "";
//        }
//        return NumParsers.getString(arg_.getStruct()).getInstance();
    }
    public String getAliasPrimBoolean() {
        return getContent().getPrimTypes().getAliasPrimBoolean();
    }

    public String getAliasObject() {
        return getContent().getCoreNames().getAliasObject();
    }

    public String getAliasString() {
        return getContent().getCharSeq().getAliasString();
    }
    public abstract void buildAliases(Element _elt, String _lg, RendKeyWords _rkw, KeyWords _kw, RendAnalysisMessages _rMess, AnalysisMessages _mess);

    public abstract AbstractFileBuilder newFileBuilder();
    public abstract CustList<AbsAliasFileBuilder> newFileBuilders();
    public abstract StringMap<String> mappingMessages();
    public abstract StringMap<String> mappingKeywords();
    public abstract StringMap<String> mappingRendKeywords();
    public abstract StringMap<String> mappingAliases();
    public abstract StringMap<String> mappingAttrs();

    public abstract StringMap<String> mappingValues();

    public abstract StringMap<String> mappingRendMessages();

    public abstract StringMap<String> mappingStyleValues();
    public abstract StringMap<String> mappingStyleUnits();
    public abstract StringMap<String> mappingStyleAttrs();
}
