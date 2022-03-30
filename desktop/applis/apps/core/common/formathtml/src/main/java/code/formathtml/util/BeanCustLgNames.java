package code.formathtml.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.ClassesUtil;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.ExecClassesUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecFieldTemplates;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.inherits.ExecTypeReturn;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.*;
import code.formathtml.*;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.errors.RendKeyWords;
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
import code.sml.Element;
import code.util.*;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public abstract class BeanCustLgNames extends BeanLgNames {

    private static final String REF_TAG = "#";

    private static final String PAGE = "page";

    private static final String SESSION = "session";

    private static final String RETURN_LINE = "\n";

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
    private CustList<RendDynOperationNode> opsMap;
    private String validateVar;
    private String validateVarArgNewValue;
    private String validateVarArgOldValue;
    private String validateVarArgBean;
    private String validateVarArgForm;
    private String validateVarArgClassField;
    private String vlidateVarArgNameField;
    private final RendExecutingBlocks rendExecutingBlocks = new RendExecutingBlocks();

    private StringMap<StringMap<String>> navigation = new StringMap<StringMap<String>>();

    protected BeanCustLgNames(AbstractGenerator _gene) {
        super(_gene);
    }

    public String getRendUrlDest(String _method, Struct _return, ContextEl _context, RendStackCall _stackCall) {
        String case_ = processString(new Argument(_return), _context, _stackCall);
        if (_context.callsOrException(_stackCall.getStackCall())) {
            return null;
        }
        return select(_method, case_, navigation);
    }

    public static String select(String _method, String _ca, StringMap<StringMap<String>> _navigation) {
        StringMap<String> casesList_ = _navigation.getVal(_method);
        if (casesList_ == null) {
            return null;
        }
        return casesList_.getVal(_ca);
    }

    @Override
    public void buildOther() {
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
        expsIterator= newCall(iteratorVar, StringUtil.concat(getContent().getPredefTypes().getAliasIterable(),"<?>"),
                new ClassMethodId(getContent().getPredefTypes().getAliasIterable(),new MethodId(MethodAccessKind.INSTANCE,simpleIterator_,new StringList(
                ))),
                StringUtil.concat(getContent().getPredefTypes().getAliasIteratorType(),"<?>"), args_, _classes);
        locName_ = tr(l_);
        hasNextVar = locName_;
        String hasNext_ = getContent().getPredefTypes().getAliasHasNext();
        expsHasNext= newCall(hasNextVar, StringUtil.concat(getContent().getPredefTypes().getAliasIteratorType(),"<?>"),
                new ClassMethodId(getContent().getPredefTypes().getAliasIteratorType(),new MethodId(MethodAccessKind.INSTANCE,hasNext_,new StringList(
                ))),
                getAliasPrimBoolean(), args_, _classes);
        locName_ = tr(l_);
        nextVar = locName_;
        String next_ = getContent().getPredefTypes().getAliasNext();
        expsNext= newCall(nextVar, StringUtil.concat(getContent().getPredefTypes().getAliasIteratorType(),"<?>"),
                new ClassMethodId(getContent().getPredefTypes().getAliasIteratorType(),new MethodId(MethodAccessKind.INSTANCE,next_,new StringList(
                ))),
                getAliasObject(), args_, _classes);

        String nextPair_ = getContent().getPredefTypes().getAliasNextPair();
        String hasNextPair_ = getContent().getPredefTypes().getAliasHasNextPair();
        iteratorTableVarCust= locName_;
        String iteratorTable_ = getContent().getPredefTypes().getAliasIteratorTable();
        expsIteratorTableCust= newCall(iteratorTableVarCust, StringUtil.concat(getContent().getPredefTypes().getAliasIterableTable(),"<?,?>"),
                new ClassMethodId(getContent().getPredefTypes().getAliasIterableTable(),new MethodId(MethodAccessKind.INSTANCE,iteratorTable_,new StringList(
                ))),
                StringUtil.concat(getContent().getPredefTypes().getAliasIteratorTableType(),"<?,?>"), args_, _classes);
        locName_ = tr(l_);
        hasNextPairVarCust= locName_;
        expsHasNextPairCust= newCall(hasNextPairVarCust, StringUtil.concat(getContent().getPredefTypes().getAliasIteratorTableType(),"<?,?>"),
                new ClassMethodId(getContent().getPredefTypes().getAliasIteratorTableType(),new MethodId(MethodAccessKind.INSTANCE,hasNextPair_,new StringList(
                ))),
                getAliasPrimBoolean(), args_, _classes);
        locName_ = tr(l_);
        nextPairVarCust= locName_;
        expsNextPairCust= newCall(nextPairVarCust, StringUtil.concat(getContent().getPredefTypes().getAliasIteratorTableType(),"<?,?>"),
                new ClassMethodId(getContent().getPredefTypes().getAliasIteratorTableType(),new MethodId(MethodAccessKind.INSTANCE,nextPair_,new StringList(
                ))),
                StringUtil.concat(getContent().getPredefTypes().getAliasPairType(),"<?,?>"), args_, _classes);
        locName_ = tr(l_);
        firstVarCust= locName_;
        String first_ = getContent().getPredefTypes().getAliasGetFirst();
        expsFirstCust= newCall(firstVarCust, StringUtil.concat(getContent().getPredefTypes().getAliasPairType(),"<?,?>"),
                new ClassMethodId(getContent().getPredefTypes().getAliasPairType(),new MethodId(MethodAccessKind.INSTANCE,first_,new StringList(
                ))),
                getAliasObject(), args_, _classes);
        locName_ = tr(l_);
        secondVarCust= locName_;
        String second_ = getContent().getPredefTypes().getAliasGetSecond();
        expsSecondCust= newCall(secondVarCust, StringUtil.concat(getContent().getPredefTypes().getAliasPairType(),"<?,?>"),
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
        vlidateVarArgNameField = locName_;

        String validate_ = beanAliases.getAliasValidate();
        args_ = new StringMap<String>();
        args_.addEntry(validateVarArgNewValue, getAliasObject());
        args_.addEntry(validateVarArgOldValue, getAliasObject());
        args_.addEntry(validateVarArgBean, getAliasObject());
        args_.addEntry(validateVarArgForm,StringExpUtil.getPrettyArrayType(getAliasObject()));
        args_.addEntry(validateVarArgClassField, getAliasString());
        args_.addEntry(vlidateVarArgNameField, getAliasString());
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

    public ContextEl setupAll(DualNavigationContext _dual) {
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
        setupRendClasses(files_, page_, confCont_.getFilesConfName(), confCont_.getAddedResources(), confCont_.getRenderFiles());
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        analyzingDoc_.setContent(this);
        session_.initInstancesPattern(page_, analyzingDoc_);
        preInitBeans(session_);
        analyzingDoc_.setRendAnalysisMessages(confCont_.getAnalysisMessages());
        analyzingDoc_.setLanguages(languages_);
        session_.setCurrentLanguage(language_);
        StringMap<AnaRendDocumentBlock> d_ = session_.analyzedRenders(files_, analyzingDoc_, page_, confCont_);
        Classes.postValidate(page_);
        if (!page_.isEmptyErrors()) {
            return null;
        }
        ForwardInfos.generalForward(page_, forwards_);
        StringMap<RendDocumentBlock> renders_ = new StringMap<RendDocumentBlock>();
        rendExecutingBlocks.setRendDocumentBlock(RendForwardInfos.buildExec(analyzingDoc_, d_, forwards_, session_, renders_));
        rendExecutingBlocks.getRenders().addAllEntries(renders_);
        Options options_ = forwards_.getOptions();
        ContextEl context_ = forwardAndClear(forwards_);
        ExecClassesUtil.tryInitStaticlyTypes(context_, options_);
        return context_;
    }

    public ContextEl forwardAndClear(Forwards _forward) {
        ContextEl ctx_ = _forward.generate();
        Classes.forwardAndClear(ctx_);
        buildIterables(ctx_.getClasses());
        return ctx_;
    }

    private static void setupRendClasses(StringMap<String> _files, AnalyzedPageEl _page, String _filesConfName, StringList _added, StringList _render) {
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
        _page.addResources(resFiles_);
        ClassesUtil.buildAllBracesBodies(classFiles_, _page);
    }
    @Override
    public void preInitBeans(Configuration _conf) {
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            getBuiltBeans().addEntry(e.getKey(),NullStruct.NULL_VALUE);
        }
        for (EntryCust<String, ValidatorInfo> e: _conf.getLateValidators().entryList()) {
            rendExecutingBlocks.getBuiltValidators().addEntry(e.getKey(),NullStruct.NULL_VALUE);
        }
    }

    public void initBeans(Configuration _conf, String _language, Struct _db, ContextEl _ctx, RendStackCall _rendStack) {
        int index_ = 0;
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            BeanInfo info_ = e.getValue();
            _rendStack.addPage(new ImportingPage());
            Argument arg_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(info_.getExps(), _ctx, _rendStack).lastValue().getArgument());
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                _rendStack.removeLastPage();
                return;
            }
            Struct strBean_ = arg_.getStruct();
            String clName_ = strBean_.getClassName(_ctx);
            if (!ExecInherits.isCorrectExecute(clName_, beanAliases.getAliasBean(), _ctx)) {
                _rendStack.removeLastPage();
                getBuiltBeans().setValue(index_,strBean_);
                index_++;
                continue;
            }
            Argument mapArg_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(opsMap, _ctx, _rendStack).lastValue().getArgument());
            ExecRootBlock rootBlock_ = _ctx.getClasses().getClassBody(beanAliases.getAliasBean());
            ExecFieldTemplates.setInstanceField(
                    new Argument(strBean_),mapArg_, _ctx, _rendStack.getStackCall(), new ClassField(beanAliases.getAliasBean(), beanAliases.getAliasForms()), new ExecTypeReturn(rootBlock_, beanAliases.getAliasStringMapObject()));
            ExecFieldTemplates.setInstanceField(
                    new Argument(strBean_),new Argument(_db), _ctx, _rendStack.getStackCall(), new ClassField(beanAliases.getAliasBean(), beanAliases.getAliasDataBaseField()), new ExecTypeReturn(rootBlock_, getAliasObject()));
            ExecFieldTemplates.setInstanceField(
                    new Argument(strBean_),new Argument(new StringStruct(_language)), _ctx, _rendStack.getStackCall(), new ClassField(beanAliases.getAliasBean(), beanAliases.getAliasLanguage()), new ExecTypeReturn(rootBlock_, getAliasString()));
            ExecFieldTemplates.setInstanceField(
                    new Argument(strBean_),new Argument(new StringStruct(info_.getScope())), _ctx, _rendStack.getStackCall(), new ClassField(beanAliases.getAliasBean(), beanAliases.getAliasScope()), new ExecTypeReturn(rootBlock_, getAliasString()));
            _rendStack.removeLastPage();
            getBuiltBeans().setValue(index_,strBean_);
            index_++;
        }
        index_ = 0;
        for (EntryCust<String, ValidatorInfo> e: _conf.getLateValidators().entryList()) {
            ValidatorInfo info_ = e.getValue();
            _rendStack.addPage(new ImportingPage());
            Argument arg_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(info_.getExps(), _ctx, _rendStack).lastValue().getArgument());
            _rendStack.removeLastPage();
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
            Struct strBean_ = arg_.getStruct();
            rendExecutingBlocks.getBuiltValidators().setValue(index_,strBean_);
            index_++;
        }
    }

    @Override
    public String initializeRendSessionDoc(ContextEl _ctx, String _language, Configuration _configuration, Struct _db, RendStackCall _rendStackCall) {
        if (rendExecutingBlocks.getRendDocumentBlock() == null) {
            return "";
        }
        _rendStackCall.init();
        initBeans(_configuration,_language,_db, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return "";
        }
        String currentUrl_ = _configuration.getFirstUrl();
        Struct bean_ = getBeanOrNull(getCurrentBeanName());
        _rendStackCall.clearPages();
        String res_ = processAfterInvoke(_configuration, currentUrl_, getCurrentBeanName(), bean_, _language, _ctx, _rendStackCall);
        setup(res_,_rendStackCall,currentUrl_);
        return res_;
    }

    @Override
    public String processRendAnchorRequest(Element _ancElt, String _language, Configuration _configuration, HtmlPage _htmlPage, ContextEl _ctx, RendStackCall _rendStack) {
        if (_ancElt == null) {
            return "";
        }
        String actionCommand_ = _ancElt.getAttribute(StringUtil.concat(_configuration.getPrefix(),_configuration.getRendKeyWords().getAttrCommand()));
        String sgn_ = _ancElt.getAttribute(StringUtil.concat(_configuration.getPrefix(),_configuration.getRendKeyWords().getAttrSgn()));
        if (!sgn_.isEmpty()) {
            _rendStack.clearPages();
            ImportingPage ip_ = new ImportingPage();
            _rendStack.addPage(ip_);
            int indexPoint_ = actionCommand_.indexOf(DOT);
            String beanName_ = actionCommand_
                    .substring(actionCommand_.indexOf(CALL_METHOD) + 1);
            Struct bean_ = getBeanOrNull(beanName_);
            ip_.setOffset(indexPoint_+1);
            setGlobalArgumentStruct(bean_,_ctx,_rendStack);
            Struct return_ = redirect(_htmlPage,bean_,_ctx,_rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return "";
            }
            String urlDest_ = getCurrentUrl();
            if (return_ != NullStruct.NULL_VALUE) {
                ip_.setOffset(actionCommand_.length());
                urlDest_ = getRendUrlDest(sgn_, return_, _ctx, _rendStack);
                if (_ctx.callsOrException(_rendStack.getStackCall())) {
                    return "";
                }
                if (urlDest_ == null) {
                    urlDest_ = getCurrentUrl();
                }
            }
            _rendStack.clearPages();
            String res_ = processAfterInvoke(_configuration, urlDest_, beanName_, bean_, _language, _ctx, _rendStack);
            setup(res_,_rendStack,urlDest_);
            return res_;
        }
        Struct bean_ = getBeanOrNull(getCurrentBeanName());
        _rendStack.clearPages();
        String res_ = processAfterInvoke(_configuration, actionCommand_,getCurrentBeanName(),bean_, _language, _ctx, _rendStack);
        setup(res_,_rendStack,actionCommand_);
        return res_;
    }

    private void setup(String _res, RendStackCall _rendStack, String _dest) {
        if (_res.isEmpty()) {
            return;
        }
        setCurrentBeanName(_rendStack.getBeanName());
        setCurrentUrl(_dest);
    }
    public String processAfterInvoke(Configuration _conf, String _dest, String _beanName, Struct _bean, String _language, ContextEl _ctx, RendStackCall _rendStack) {
        String curl_ = getCurrentUrl();
        ImportingPage ip_ = new ImportingPage();
        _rendStack.addPage(ip_);
        Struct forms_ = NullStruct.NULL_VALUE;
        if (!_beanName.isEmpty()) {
            forms_ = storeForms(_bean, _ctx, _rendStack);
        }
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return "";
        }
        processInitBeans(_conf,_dest,_beanName, curl_, _language, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return "";
        }
        String dest_ = StringUtil.getFirstToken(_dest, REF_TAG);
        String currentBeanName_;
        RendDocumentBlock rendDocumentBlock_ = rendExecutingBlocks.getRenders().getVal(dest_);
        if (rendDocumentBlock_ == null) {
            return "";
        }
        currentBeanName_ = rendDocumentBlock_.getBeanName();
        Struct bean_ = getBeanOrNull(currentBeanName_);
        if (!_beanName.isEmpty()) {
            setStoredForms(bean_, forms_, _ctx, _rendStack);
        }
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return "";
        }
        _rendStack.clearPages();
        return getRes(rendDocumentBlock_,_conf, this, _ctx, _rendStack);
    }

    public void setGlobalArgumentStruct(Struct _obj, ContextEl _ctx, RendStackCall _rendStackCall) {
        _rendStackCall.getLastPage().setGlobalArgumentStruct(_obj, _ctx);
    }

    public String getRes(RendDocumentBlock _rend, Configuration _conf, BeanCustLgNames _stds, ContextEl _ctx, RendStackCall _rendStackCall) {
        _rendStackCall.getFormParts().initForms();
        String beanName_ = _rend.getBeanName();
        Struct bean_ = getBuiltBeans().getVal(beanName_);
        _rendStackCall.setMainBean(bean_);
        _rendStackCall.addPage(new ImportingPage());
        RendImport.befDisp(_stds,_ctx, _rendStackCall, bean_);
        _rendStackCall.removeLastPage();
        return RendBlock.res(_rend, _conf, _stds, _ctx, _rendStackCall, beanName_, bean_);
    }

    public ResultErrorStd convert(NodeContainer _container, ContextEl _context, RendStackCall _rendStackCall) {
        CustList<RendDynOperationNode> ops_ = _container.getOpsConvert();
        if (!ops_.isEmpty()) {
            String varNameConvert_ = _container.getVarNameConvert();
            LocalVariable lv_ = newLocVar(_container);
            _rendStackCall.getLastPage().putValueVar(varNameConvert_, new VariableWrapper(lv_));
            setGlobalArgumentStruct(_container.getBean(),_context,_rendStackCall);
            Argument res_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(ops_, _context, _rendStackCall).lastValue().getArgument());
            _rendStackCall.getLastPage().removeRefVar(varNameConvert_);
            ResultErrorStd out_ = new ResultErrorStd();
            if (_context.callsOrException(_rendStackCall.getStackCall())) {
                return out_;
            }
            out_.setResult(res_.getStruct());
            return out_;
        }
        String className_ = _container.getNodeInformation().getInputClass();
        StringList values_ = _container.getValue();
        return getStructToBeValidated(values_, className_, _context, _rendStackCall);
    }
    private LocalVariable newLocVar(NodeContainer _container) {
        StringList values_ = _container.getValue();
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
    public ResultErrorStd getStructToBeValidated(StringList _values, String _className, ContextEl _ctx, RendStackCall _stack) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(_className, getAliasString())) {
            res_.setResult(wrapStd(_values));
            return res_;
        }
        if (_values.isEmpty() || _values.first().trim().isEmpty()) {
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return getStructToBeValidatedPrim(_values, _className, _ctx, _stack, res_);
    }

    private void processInitBeans(Configuration _conf, String _dest, String _beanName, String _currentUrl, String _language, ContextEl _ctx, RendStackCall _rendStackCall) {
        int s_ = getBuiltBeans().size();
        for (int i = 0; i < s_; i++) {
            String key_ = getBuiltBeans().getKey(i);
            boolean reinit_ = reinitRendBean(_dest, _beanName, key_, _currentUrl, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                break;
            }
            if (!reinit_) {
                continue;
            }
            Struct bean_ = getBuiltBeans().getValue(i);
            BeanInfo info_ = _conf.getBeansInfos().getValue(i);
            bean_ = newBean(_language, bean_,info_, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                break;
            }
            getBuiltBeans().setValue(i,bean_);
        }
    }
    private boolean reinitRendBean(String _dest, String _beanName, String _currentBean, String _currentUrl, ContextEl _ctx, RendStackCall _rendStackCall) {
        if (!StringUtil.quickEq(_currentBean,_beanName)) {
            return false;
        }
        Struct bean_ = getBeanOrNull(_currentBean);
        String scope_ = getScope(bean_, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return false;
        }
        if (StringUtil.quickEq(scope_,SESSION)) {
            return false;
        }
        if (StringUtil.quickEq(scope_,PAGE)) {
            return !StringUtil.quickEq(_currentUrl, StringUtil.getFirstToken(_dest, REF_TAG));
        }
        return true;
    }

    private Struct newBean(String _language, Struct _bean, BeanInfo _info, ContextEl _ctx, RendStackCall _rendStackCall) {
        Argument arg_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(_info.getExps(), _ctx, _rendStackCall).lastValue().getArgument());
        Struct strBean_ = arg_.getStruct();
        forwardDataBase(_bean,strBean_, _ctx, _rendStackCall);
        setStoredForms(strBean_, NullStruct.NULL_VALUE, _ctx, _rendStackCall);
        setLanguage(strBean_, _language, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return NullStruct.NULL_VALUE;
        }
        String str_ = getScope(_bean, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return NullStruct.NULL_VALUE;
        }
        setScope(strBean_, str_, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return NullStruct.NULL_VALUE;
        }
        return strBean_;
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

    private void forwardDataBase(Struct _bean, Struct _to, ContextEl _ctx, RendStackCall _rendStackCall) {
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        _rendStackCall.getLastPage().putInternVars(getDataBaseVar, _bean, _ctx);
        _rendStackCall.getLastPage().setEnabledOp(false);
        Argument argument_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(expsGetDataBase, _ctx, _rendStackCall).lastValue().getArgument());
        _rendStackCall.getLastPage().clearInternVars();
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            _rendStackCall.getLastPage().setEnabledOp(true);
            return;
        }
        _rendStackCall.getLastPage().putInternVars(setDataBaseVar, _to, _ctx);
        _rendStackCall.getLastPage().putInternVars(setDataBaseVarArg, argument_.getStruct(), _ctx);
        RenderExpUtil.getAllArgs(expsSetDataBase, _ctx, _rendStackCall).lastValue();
        _rendStackCall.getLastPage().setEnabledOp(true);
        _rendStackCall.getLastPage().clearInternVars();
    }


    private Struct storeForms(Struct _bean, ContextEl _ctx, RendStackCall _rendStackCall) {
        Argument forms_ = getForms(_bean, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().setEnabledOp(true);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return NullStruct.NULL_VALUE;
        }
        return forms_.getStruct();
    }

    private Argument getForms(Struct _bean, ContextEl _ctx, RendStackCall _rendStackCall) {
        String clName_ = _bean.getClassName(_ctx);
        _rendStackCall.getLastPage().setEnabledOp(false);
        if (!ExecInherits.isCorrectExecute(clName_, beanAliases.getAliasBean(), _ctx)) {
            return Argument.getNullableValue(RenderExpUtil.getAllArgs(opsMap, _ctx, _rendStackCall).lastValue().getArgument());
        }
        _rendStackCall.getLastPage().putInternVars(getFormsVar, _bean, _ctx);
        Argument argument_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(expsGetForms, _ctx, _rendStackCall).lastValue().getArgument());
        _rendStackCall.getLastPage().clearInternVars();
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return argument_;
        }
        if (argument_.isNull()) {
            return Argument.getNullableValue(RenderExpUtil.getAllArgs(opsMap, _ctx, _rendStackCall).lastValue().getArgument());
        }
        return argument_;
    }


    private void setStoredForms(Struct _bean, Struct _storedForms, ContextEl _ctx, RendStackCall _rendStackCall) {
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        _rendStackCall.getLastPage().setEnabledOp(false);
        _rendStackCall.getLastPage().putInternVars(setFormsVar, _bean, _ctx);
        _rendStackCall.getLastPage().putInternVars(setFormsVarArg, _storedForms, _ctx);
        RenderExpUtil.getAllArgs(expsSetForms, _ctx, _rendStackCall).lastValue();
        _rendStackCall.getLastPage().clearInternVars();
        _rendStackCall.getLastPage().setEnabledOp(true);
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
        boolean ok_ = gearFw(_mainBean, _node, _keepField, bean_, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            ok_ = false;
        }
        return ok_;
    }

    public StringMap<Struct> getBuiltBeans() {
        return rendExecutingBlocks.getBuiltBeans();
    }
    protected boolean gearFw(Struct _mainBean, RendImport _node, boolean _keepField, Struct _bean, ContextEl _ctx, RendStackCall _rendStack) {
        Argument forms_ = getForms(_bean, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            _rendStack.getLastPage().setEnabledOp(true);
            return false;
        }
        Argument formsMap_ = getForms(_mainBean, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            _rendStack.getLastPage().setEnabledOp(true);
            return false;
        }
        if (_keepField) {
            for (RendBlock f_: RendBlock.getDirectChildren(_node)) {
                if (!(f_ instanceof RendImportForm)) {
                    continue;
                }
                String name_ = ((RendImportForm)f_).getName();
                forwardMap(formsMap_.getStruct(),forms_.getStruct(),new StringStruct(name_), _ctx, _rendStack);
                if (_ctx.callsOrException(_rendStack.getStackCall())) {
                    _rendStack.getLastPage().setEnabledOp(true);
                    return false;
                }
            }
        } else {
            //add option for copying forms (default copy)
            putAllMap(forms_.getStruct(),formsMap_.getStruct(), _ctx, _rendStack);
        }
        _rendStack.getLastPage().setEnabledOp(true);
        return true;
    }

    public ResultErrorStd getStructToBeValidatedPrim(StringList _values, String _className, ContextEl _ctx, RendStackCall _stack, ResultErrorStd _res) {
        byte cast_ = ExecClassArgumentMatching.getPrimitiveWrapCast(_className, this);
        if (cast_ == PrimitiveTypes.BOOL_WRAP) {
            _res.setResult(BooleanStruct.of(StringUtil.quickEq(_values.first(),ON)));
            return _res;
        }
        if (cast_ == PrimitiveTypes.CHAR_WRAP) {
            _res.setResult(new CharStruct(_values.first().trim().charAt(0)));
            return _res;
        }
        if (cast_ > PrimitiveTypes.LONG_WRAP) {
            DoubleInfo doubleInfo_ = NumParsers.splitDouble(_values.first());
            if (!doubleInfo_.isValid()) {
                _stack.getStackCall().setCallingState(new CustomFoundExc(new ErrorStruct(_ctx, _values.first(), getContent().getCoreNames().getAliasNbFormat(), _stack.getStackCall())));
                return _res;
            }
            _res.setResult(NumParsers.convertToFloat(cast_,new DoubleStruct(doubleInfo_.getValue())));
            return _res;
        }
        LongInfo val_ = NumParsers.parseLong(_values.first(), 10);
        if (!val_.isValid()) {
            _stack.getStackCall().setCallingState(new CustomFoundExc(new ErrorStruct(_ctx, _values.first(), getContent().getCoreNames().getAliasNbFormat(), _stack.getStackCall())));
            return _res;
        }
        _res.setResult(NumParsers.convertToInt(cast_,new LongStruct(val_.getValue())));
        return _res;
    }
    public DefaultBeanAliases getBeanAliases() {
        return beanAliases;
    }

    private void forwardMap(Struct _map, Struct _to, Struct _key, ContextEl _ctx, RendStackCall _rendStackCall) {
        _rendStackCall.getLastPage().putInternVars(getValVar, _map, _ctx);
        _rendStackCall.getLastPage().putInternVars(getValVarArg, _key, _ctx);
        Argument argument_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(expsGetVal, _ctx, _rendStackCall).lastValue().getArgument());
        _rendStackCall.getLastPage().clearInternVars();
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        _rendStackCall.getLastPage().putInternVars(putVarCust, _to, _ctx);
        _rendStackCall.getLastPage().putInternVars(putVarCustKey, _key, _ctx);
        _rendStackCall.getLastPage().putInternVars(putVarCustValue, argument_.getStruct(), _ctx);
        RenderExpUtil.getAllArgs(expsPut, _ctx, _rendStackCall).lastValue();
        _rendStackCall.getLastPage().clearInternVars();
    }

    public void putAllMap(Struct _map, Struct _other, ContextEl _ctx, RendStackCall _rendStackCall) {
        _rendStackCall.getLastPage().putInternVars(putAllVarCust, _map, _ctx);
        _rendStackCall.getLastPage().putInternVars(putAllVarCustArg, _other, _ctx);
        RenderExpUtil.getAllArgs(expsPutAll, _ctx, _rendStackCall).lastValue();
        _rendStackCall.getLastPage().clearInternVars();
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

    @Override
    public StringMap<Message> validateAll(HtmlPage _htmlPage, Configuration _conf, ContextEl _ctx, RendStackCall _rendStack) {
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = _htmlPage.getContainers();
        long lg_ = _htmlPage.getUrl();
        StringMap<Message> map_ = new StringMap<Message>();
        for (EntryCust<Long, NodeContainer> e: containersMap_.getVal(lg_).entryList()) {
            NodeContainer nCont_ = e.getValue();
            NodeInformations nInfos_ = nCont_.getNodeInformation();
            String valId_ = nInfos_.getValidator();
            String id_ = nInfos_.getId();
            Message messageTr_ = validate(nCont_,valId_, _ctx, _rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return null;
            }
            if (messageTr_ != null) {
                map_.put(id_, messageTr_);
            }
        }
        return map_;
    }

    @Override
    public boolean updateRendBean(HtmlPage _htmlPage, ContextEl _ctx, RendStackCall _rendStackCall) {
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = _htmlPage.getContainers();
        long lg_ = _htmlPage.getUrl();
        LongTreeMap< NodeContainer> containers_ = containersMap_.getVal(lg_);
        for (EntryCust<Long, NodeContainer> e: containers_.entryList()) {
            NodeContainer nCont_ = e.getValue();
            if (!nCont_.isEnabled()) {
                continue;
            }
            Struct newObj_;
            ResultErrorStd res_ = convert(nCont_, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                return false;
            }
            newObj_ = res_.getResult();
            Struct procObj_ = e.getValue().getUpdated();
            setGlobalArgumentStruct(procObj_,_ctx,_rendStackCall);
            RendRequestUtil.setRendObject(e.getValue(), newObj_, this, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Struct redir(Argument _bean, StringList _varNames, CustList<RendDynOperationNode> _exps, StringList _args, ContextEl _context, RendStackCall _rendStackCall) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        int s_ = _varNames.size();
        for (int i = 0; i< s_; i++) {
            LocalVariable locVar_ = LocalVariable.newLocalVariable(new LongStruct(NumberUtil.parseLongZero(_args.get(i))), getAliasPrimLong());
            ip_.putValueVar(_varNames.get(i), new VariableWrapper(locVar_));
        }
        Argument globalArgument_ = _rendStackCall.getLastPage().getGlobalArgument();
        setGlobalArgumentStruct(_bean.getStruct(), _context, _rendStackCall);
        Argument argument_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(_exps, _context, _rendStackCall).lastValue().getArgument());
        setGlobalArgumentStruct(globalArgument_.getStruct(), _context, _rendStackCall);
        RendRequestUtil.removeVars(_varNames, ip_);
        if (_context.callsOrException(_rendStackCall.getStackCall())) {
            return NullStruct.NULL_VALUE;
        }
        return argument_.getStruct();
    }

    public Message validate(NodeContainer _cont, String _validatorId, ContextEl _ctx, RendStackCall _rendStack) {
        Struct validator_ = rendExecutingBlocks.getBuiltValidators().getVal(_validatorId);
        if (validator_ == null) {
            return null;
        }
        return validate(_cont,validator_, _ctx, _rendStack);
    }

    private Message validate(NodeContainer _cont, Struct _validator, ContextEl _ctx, RendStackCall _rendStackCall) {
        LocalVariable locVar_;
        _rendStackCall.getLastPage().putInternVars(validateVar, _validator, _ctx);
        locVar_ = newLocVar(_cont);
        _rendStackCall.getLastPage().putInternVars(validateVarArgNewValue, locVar_);
        locVar_ = LocalVariable.newLocalVariable(_cont.getTypedStruct(), getAliasObject());
        _rendStackCall.getLastPage().putInternVars(validateVarArgOldValue, locVar_);
        locVar_ = LocalVariable.newLocalVariable(_cont.getBean(), getAliasObject());
        _rendStackCall.getLastPage().putInternVars(validateVarArgBean, locVar_);
        CustList<Struct> params_ = _cont.getStructParam();
        int size_ = params_.size();
        ArrayStruct arr_ = new ArrayStruct(size_ +1,StringExpUtil.getPrettyArrayType(getAliasObject()));
        arr_.set(0, _cont.getUpdated());
        for (int i = 0; i < size_; i++) {
            arr_.set(i+1, params_.get(i));
        }
        locVar_ = LocalVariable.newLocalVariable(arr_,StringExpUtil.getPrettyArrayType(getAliasObject()));
        _rendStackCall.getLastPage().putInternVars(validateVarArgForm, locVar_);
        _rendStackCall.getLastPage().putInternVars(validateVarArgClassField, new StringStruct(_cont.getIdFieldClass()), _ctx);
        _rendStackCall.getLastPage().putInternVars(vlidateVarArgNameField, new StringStruct(_cont.getIdFieldName()), _ctx);
        _rendStackCall.getLastPage().setEnabledOp(false);
        Argument arg_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(expsValidate, _ctx, _rendStackCall).lastValue().getArgument());
        _rendStackCall.getLastPage().setEnabledOp(true);
        _rendStackCall.getLastPage().clearInternVars();
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return null;
        }
        if (arg_.isNull()) {
            return null;
        }
        return DefaultBeanAliases.getMessageStruct(arg_.getStruct(), beanAliases.getAliasMessage()).getInstance();
    }

    public void beforeDisplaying(Struct _arg, ContextEl _ctx, RendStackCall _rendStack) {
        String clName_ = _arg.getClassName(_ctx);
        if (!ExecInherits.isCorrectExecute(clName_, beanAliases.getAliasBean(), _ctx)) {
            return;
        }
        String locName_ = getBeforeDisplayingVar();
        _rendStack.getLastPage().putInternVars(locName_, _arg, _ctx);
        _rendStack.getLastPage().setEnabledOp(false);
        RenderExpUtil.getAllArgs(expsBeforeDisplaying, _ctx, _rendStack).lastValue();
        _rendStack.getLastPage().setEnabledOp(true);
        _rendStack.getLastPage().clearInternVars();
    }

    private String getScope(Struct _bean, ContextEl _ctx, RendStackCall _rendStackCall) {
        _rendStackCall.getLastPage().putInternVars(getScopeVar, _bean, _ctx);
        _rendStackCall.getLastPage().setEnabledOp(false);
        Argument argument_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(expsGetScope, _ctx, _rendStackCall).lastValue().getArgument());
        _rendStackCall.getLastPage().setEnabledOp(true);
        _rendStackCall.getLastPage().clearInternVars();
        if (_ctx.callsOrException(_rendStackCall.getStackCall()) || argument_.isNull()) {
            return "";
        }
        return NumParsers.getString(argument_.getStruct()).getInstance();
    }
    private void setScope(Struct _bean, String _scope, ContextEl _ctx, RendStackCall _rendStackCall) {
        _rendStackCall.getLastPage().putInternVars(setScopeVar, _bean, _ctx);
        _rendStackCall.getLastPage().putInternVars(setScopeVarArg, new StringStruct(_scope), _ctx);
        _rendStackCall.getLastPage().setEnabledOp(false);
        RenderExpUtil.getAllArgs(expsSetScope, _ctx, _rendStackCall).lastValue();
        _rendStackCall.getLastPage().setEnabledOp(true);
        _rendStackCall.getLastPage().clearInternVars();
    }
    private void setLanguage(Struct _bean, String _scope, ContextEl _ctx, RendStackCall _rendStackCall) {
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        _rendStackCall.getLastPage().putInternVars(setLanguageVar, _bean, _ctx);
        _rendStackCall.getLastPage().putInternVars(setLanguageVarArg, new StringStruct(_scope), _ctx);
        _rendStackCall.getLastPage().setEnabledOp(false);
        RenderExpUtil.getAllArgs(expsSetLanguage, _ctx, _rendStackCall).lastValue();
        _rendStackCall.getLastPage().setEnabledOp(true);
        _rendStackCall.getLastPage().clearInternVars();
    }

    public String processString(Argument _arg, ContextEl _ctx, RendStackCall _stack) {
        return processStr(_arg, _ctx, _stack);
    }

    public static String processStr(Argument _arg, ContextEl _ctx, RendStackCall _stack) {
        Argument arg_ = RendDynOperationNode.processString(_arg, _ctx, _stack);
        if (_ctx.callsOrException(_stack.getStackCall())) {
            return "";
        }
        return NumParsers.getString(arg_.getStruct()).getInstance();
    }


    public abstract void buildAliases(Element _elt, String _lg, RendKeyWords _rkw, KeyWords _kw, RendAnalysisMessages _rMess, AnalysisMessages _mess);
}
