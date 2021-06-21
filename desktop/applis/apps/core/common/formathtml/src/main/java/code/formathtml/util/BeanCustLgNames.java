package code.formathtml.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecClassesUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.options.ValidatorStandard;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.DefaultConverterCheck;
import code.formathtml.analyze.DefaultReducingOperations;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.exec.blocks.RendImport;
import code.formathtml.exec.blocks.RendImportForm;
import code.formathtml.exec.opers.*;
import code.formathtml.fwd.DefaultInputBuilder;
import code.formathtml.fwd.RendForwardInfos;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.Message;
import code.formathtml.structs.ValidatorInfo;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.exec.Classes;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.*;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.errors.RendKeyWords;
import code.maths.montecarlo.AbstractGenerator;
import code.sml.Element;
import code.util.*;
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

    protected BeanCustLgNames(AbstractGenerator _gene) {
        super(_gene);
    }

    @Override
    public void buildOther() {
        beanAliases.buildOther(getContent());
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
        opsMap.add(new RendStandardInstancingOperation(new ExecOperationContent(0, clMatch_, 0), new ExecInstancingCommonContent(cont_, formattedType_), new ExecInstancingStdContent(new AnaInstancingStdContent()), new ExecTypeFunction(formattedType_, null)));
    }

    private static String tr(StringList _list) {
        return ValidatorStandard.tr(_list);
    }

    public ReportedMessages setupAll(Navigation _nav, Configuration _conf, StringMap<String> _files, DualAnalyzedContext _dual) {
        Forwards forwards_ = new Forwards();
        AnalyzedPageEl page_ = _dual.getAnalyzed();
        setupRendClasses(_files, page_, _dual.getContext().getFilesConfName(), _dual.getContext().getAddedResources());
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        analyzingDoc_.setReducingOperations(new DefaultReducingOperations());
        analyzingDoc_.setContent(this);
        analyzingDoc_.setInputBuilder(new DefaultInputBuilder());
        analyzingDoc_.setConverterCheck(new DefaultConverterCheck(getContent().getPrimTypes().getPrimitiveTypes(), getContent().getCharSeq().getAliasString()));
        _nav.initInstancesPattern(page_, analyzingDoc_);
        StringMap<AnaRendDocumentBlock> d_ = _nav.analyzedRenders(page_, this, analyzingDoc_, _dual.getContext());
        ReportedMessages messages_ = page_.getMessages();
        if (!messages_.isAllEmptyErrors()) {
            return messages_;
        }
        forwardAndClear(_conf, page_, analyzingDoc_, forwards_, d_, _dual.getContext().getContext());
        Options options_ = page_.getOptions();
        ContextEl context_ = _dual.getContext().getContext();
        ExecClassesUtil.tryInitStaticlyTypes(context_, options_);
        return messages_;
    }

    public void forwardAndClear(Configuration _conf, AnalyzedPageEl _page, AnalyzingDoc _anaDoc, Forwards _forward, StringMap<AnaRendDocumentBlock> _analyzed, ContextEl _context) {
        Classes.forwardAndClear(_context, _page, _forward);
        RendForwardInfos.buildExec(_anaDoc, _analyzed, _forward, _conf);
        buildIterables(_context.getClasses());
    }

    private static void setupRendClasses(StringMap<String> _files, AnalyzedPageEl _page, String _filesConfName, StringList _added) {
        StringList content_ = new StringList();
        for (EntryCust<String, String> e: _files.entryList()) {
            if (StringUtil.quickEq(e.getKey(), _filesConfName)) {
                content_ = StringUtil.splitStrings(e.getValue(), RETURN_LINE);
                break;
            }
        }
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
        Classes.validateWithoutInit(classFiles_, _page);
    }
    @Override
    public void preInitBeans(Configuration _conf) {
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            _conf.getBuiltBeans().addEntry(e.getKey(),NullStruct.NULL_VALUE);
        }
        for (EntryCust<String, ValidatorInfo> e: _conf.getLateValidators().entryList()) {
            _conf.getBuiltValidators().addEntry(e.getKey(),NullStruct.NULL_VALUE);
        }
    }

    @Override
    public void initBeans(Configuration _conf, String _language, Struct _db, ContextEl _ctx, RendStackCall _rendStack) {
        int index_ = 0;
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            BeanInfo info_ = e.getValue();
            _rendStack.addPage(new ImportingPage());
            Argument arg_ = RenderExpUtil.calculateReuse(info_.getExps(), this, _ctx, _rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                _rendStack.removeLastPage();
                return;
            }
            Struct strBean_ = arg_.getStruct();
            String clName_ = strBean_.getClassName(_ctx);
            if (!ExecInherits.isCorrectExecute(clName_, beanAliases.getAliasBean(), _ctx)) {
                _rendStack.removeLastPage();
                _conf.getBuiltBeans().setValue(index_,strBean_);
                index_++;
                continue;
            }
            Argument mapArg_ = RenderExpUtil.calculateReuse(opsMap, this, _ctx, _rendStack);
            ExecRootBlock rootBlock_ = _ctx.getClasses().getClassBody(beanAliases.getAliasBean());
            ExecTemplates.setInstanceField(rootBlock_, beanAliases.getAliasStringMapObject(),
                    new Argument(strBean_),mapArg_, _ctx, _rendStack.getStackCall(), new ClassField(beanAliases.getAliasBean(), beanAliases.getAliasForms()));
            ExecTemplates.setInstanceField(rootBlock_, getAliasObject(),
                    new Argument(strBean_),new Argument(_db), _ctx, _rendStack.getStackCall(), new ClassField(beanAliases.getAliasBean(), beanAliases.getAliasDataBaseField()));
            ExecTemplates.setInstanceField(rootBlock_, getAliasString(),
                    new Argument(strBean_),new Argument(new StringStruct(_language)), _ctx, _rendStack.getStackCall(), new ClassField(beanAliases.getAliasBean(), beanAliases.getAliasLanguage()));
            ExecTemplates.setInstanceField(rootBlock_, getAliasString(),
                    new Argument(strBean_),new Argument(new StringStruct(info_.getScope())), _ctx, _rendStack.getStackCall(), new ClassField(beanAliases.getAliasBean(), beanAliases.getAliasScope()));
            _rendStack.removeLastPage();
            _conf.getBuiltBeans().setValue(index_,strBean_);
            index_++;
        }
        index_ = 0;
        for (EntryCust<String, ValidatorInfo> e: _conf.getLateValidators().entryList()) {
            ValidatorInfo info_ = e.getValue();
            _rendStack.addPage(new ImportingPage());
            Argument arg_ = RenderExpUtil.calculateReuse(info_.getExps(), this, _ctx, _rendStack);
            _rendStack.removeLastPage();
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
            Struct strBean_ = arg_.getStruct();
            _conf.getBuiltValidators().setValue(index_,strBean_);
            index_++;
        }
    }

    @Override
    public ResultErrorStd getOtherResult(StackCall _stack, ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, Struct... _args) {
        return beanAliases.getOtherResult(_cont, _instance, _method, _stack, _args);
    }

    @Override
    public String processAfterInvoke(Configuration _conf, String _dest, String _beanName, Struct _bean, String _currentUrl, String _language, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = new ImportingPage();
        _rendStack.addPage(ip_);
        Struct forms_ = NullStruct.NULL_VALUE;
        if (!_beanName.isEmpty()) {
            forms_ = storeForms(_bean, _ctx, _rendStack);
        }
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return "";
        }
        processInitBeans(_conf,_dest,_beanName, _currentUrl, _language, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return "";
        }
        String dest_ = StringUtil.getFirstToken(_dest, REF_TAG);
        String currentBeanName_;
        RendDocumentBlock rendDocumentBlock_ = _conf.getRenders().getVal(dest_);
        if (rendDocumentBlock_ == null) {
            return "";
        }
        currentBeanName_ = rendDocumentBlock_.getBeanName();
        Struct bean_ = getBeanOrNull(_conf,currentBeanName_);
        if (!_beanName.isEmpty()) {
            setStoredForms(bean_, forms_, _ctx, _rendStack);
        }
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return "";
        }
        _rendStack.clearPages();
        return RendBlock.getRes(rendDocumentBlock_,_conf, this, _ctx, _rendStack, _dest);
    }
    private void processInitBeans(Configuration _conf, String _dest, String _beanName, String _currentUrl, String _language, ContextEl _ctx, RendStackCall _rendStackCall) {
        int s_ = _conf.getBuiltBeans().size();
        for (int i = 0; i < s_; i++) {
            String key_ = _conf.getBuiltBeans().getKey(i);
            boolean reinit_ = reinitRendBean(_conf,_dest, _beanName, key_, _currentUrl, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                break;
            }
            if (!reinit_) {
                continue;
            }
            Struct bean_ = _conf.getBuiltBeans().getValue(i);
            BeanInfo info_ = _conf.getBeansInfos().getValue(i);
            bean_ = newBean(_language, bean_,info_, _ctx, _rendStackCall);
            if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
                break;
            }
            _conf.getBuiltBeans().setValue(i,bean_);
        }
    }
    private boolean reinitRendBean(Configuration _conf, String _dest, String _beanName, String _currentBean, String _currentUrl, ContextEl _ctx, RendStackCall _rendStackCall) {
        if (!StringUtil.quickEq(_currentBean,_beanName)) {
            return false;
        }
        Struct bean_ = getBeanOrNull(_conf,_currentBean);
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
        Argument arg_ = RenderExpUtil.calculateReuse(_info.getExps(), this, _ctx, _rendStackCall);
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

    private static Struct getBeanOrNull(Configuration _conf, String _currentBeanName) {
        Struct bean_ = getBean(_conf,_currentBeanName);
        if (bean_ == null) {
            bean_ = NullStruct.NULL_VALUE;
        }
        return bean_;
    }

    private static Struct getBean(Configuration _conf, String _beanName) {
        return _conf.getBuiltBeans().getVal(_beanName);
    }

    private void forwardDataBase(Struct _bean, Struct _to, ContextEl _ctx, RendStackCall _rendStackCall) {
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        _rendStackCall.getLastPage().putInternVars(getDataBaseVar, _bean, _ctx);
        _rendStackCall.getLastPage().setEnabledOp(false);
        Argument argument_ = RenderExpUtil.calculateReuse(expsGetDataBase, this, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().clearInternVars();
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            _rendStackCall.getLastPage().setEnabledOp(true);
            return;
        }
        _rendStackCall.getLastPage().putInternVars(setDataBaseVar, _to, _ctx);
        _rendStackCall.getLastPage().putInternVars(setDataBaseVarArg, argument_.getStruct(), _ctx);
        RenderExpUtil.calculateReuse(expsSetDataBase, this, _ctx, _rendStackCall);
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
            return RenderExpUtil.calculateReuse(opsMap, this, _ctx, _rendStackCall);
        }
        _rendStackCall.getLastPage().putInternVars(getFormsVar, _bean, _ctx);
        Argument argument_ = RenderExpUtil.calculateReuse(expsGetForms, this, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().clearInternVars();
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return argument_;
        }
        if (argument_.isNull()) {
            return RenderExpUtil.calculateReuse(opsMap, this, _ctx, _rendStackCall);
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
        RenderExpUtil.calculateReuse(expsSetForms, this, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().clearInternVars();
        _rendStackCall.getLastPage().setEnabledOp(true);
    }

    public boolean setBeanForms(Configuration _conf, Struct _mainBean,
                                RendImport _node, boolean _keepField, String _beanName, ContextEl _ctx, RendStackCall _rendStack) {
        if (_mainBean == null) {
            return true;
        }
        Struct bean_ = _conf.getBuiltBeans().getVal(_beanName);
        if (bean_ == null) {
            return true;
        }
        boolean ok_ = gearFw(_mainBean, _node, _keepField, bean_, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            ok_ = false;
        }
        return ok_;
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

    public DefaultBeanAliases getBeanAliases() {
        return beanAliases;
    }

    @Override
    public Argument getCommonArgument(RendSettableFieldOperation _rend, Argument _previous, ContextEl _context, RendStackCall _stack) {
        ClassField fieldId_ = _rend.getClassField();
        boolean staticField_ = _rend.isStaticField();
        Argument previous_;
        if (!staticField_) {
            previous_ = new Argument(ExecTemplates.getParent(_rend.getAnc(), _previous.getStruct(), _context, _stack.getStackCall()));
        } else {
            previous_ = new Argument();
        }
        String fieldType_ = _rend.getRealType();
        if (_context.callsOrException(_stack.getStackCall())) {
            return Argument.createVoid();
        }
        if (staticField_) {
            return ExecTemplates.getStaticField(_context.getExiting(), _rend.getRootBlock(), fieldType_, _context, _stack.getStackCall(), fieldId_);
        }
        return ExecTemplates.getInstanceField(previous_, _context, _stack.getStackCall(), fieldId_);
    }

    @Override
    public Argument getCommonSetting(RendSettableFieldOperation _rend, Argument _previous, Argument _right, ContextEl _context, RendStackCall _stack) {
        String fieldType_ = _rend.getRealType();
        boolean isStatic_ = _rend.isStaticField();
        ClassField fieldId_ = _rend.getClassField();
        Argument previous_;
        if (!isStatic_) {
            previous_ = new Argument(ExecTemplates.getParent(_rend.getAnc(), _previous.getStruct(), _context, _stack.getStackCall()));
        } else {
            previous_ = new Argument();
        }
        //Come from code directly so constant static fields can be initialized here
        if (_context.callsOrException(_stack.getStackCall())) {
            return Argument.createVoid();
        }
        if (isStatic_) {
            return ExecTemplates.setStaticField(_context.getExiting(), _rend.getRootBlock(), fieldType_, _right, _context, _stack.getStackCall(), fieldId_);
        }
        return ExecTemplates.setInstanceField(_rend.getRootBlock(), fieldType_, previous_, _right, _context, _stack.getStackCall(), fieldId_);
    }

    @Override
    public Argument getCommonFctArgument(RendStdFctOperation _rend, Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, ContextEl _context, RendStackCall _stack) {
        MethodId methodId_ = _rend.getClassMethodId().getConstraints();
        String lastType_ = _rend.getLastType();
        Argument prev_;
        if (!_rend.isStaticMethod()) {
            Struct argPrev_ = _previous.getStruct();
            prev_ = new Argument(ExecTemplates.getParent(0, argPrev_, _context, _stack.getStackCall()));
            if (_context.callsOrException(_stack.getStackCall())) {
                return new Argument();
            }
        } else {
            prev_ = new Argument();
        }
        String classNameFound_ = _rend.getClassMethodId().getClassName();
        int naturalVararg_ = _rend.getNaturalVararg();
        return ExecInvokingOperation.callStd(_context.getExiting(), _context, classNameFound_, methodId_, prev_, ExecInvokingOperation.fectchArgs(lastType_, naturalVararg_, null, _context, _stack.getStackCall(), _rend.buildInfos(_all)), _stack.getStackCall());
    }

    private void forwardMap(Struct _map, Struct _to, Struct _key, ContextEl _ctx, RendStackCall _rendStackCall) {
        _rendStackCall.getLastPage().putInternVars(getValVar, _map, _ctx);
        _rendStackCall.getLastPage().putInternVars(getValVarArg, _key, _ctx);
        Argument argument_ = RenderExpUtil.calculateReuse(expsGetVal, this, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().clearInternVars();
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        _rendStackCall.getLastPage().putInternVars(putVarCust, _to, _ctx);
        _rendStackCall.getLastPage().putInternVars(putVarCustKey, _key, _ctx);
        _rendStackCall.getLastPage().putInternVars(putVarCustValue, argument_.getStruct(), _ctx);
        RenderExpUtil.calculateReuse(expsPut, this, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().clearInternVars();
    }

    public void putAllMap(Struct _map, Struct _other, ContextEl _ctx, RendStackCall _rendStackCall) {
        _rendStackCall.getLastPage().putInternVars(putAllVarCust, _map, _ctx);
        _rendStackCall.getLastPage().putInternVars(putAllVarCustArg, _other, _ctx);
        RenderExpUtil.calculateReuse(expsPutAll, this, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().clearInternVars();
    }

    private String getIteratorVar() {
        return iteratorVar;
    }

    private String getHasNextVar() {
        return hasNextVar;
    }

    private String getNextVar() {
        return nextVar;
    }

    private CustList<RendDynOperationNode> getExpsIterator() {
        return expsIterator;
    }

    private CustList<RendDynOperationNode> getExpsHasNext() {
        return expsHasNext;
    }

    private CustList<RendDynOperationNode> getExpsNext() {
        return expsNext;
    }

    private String getIteratorTableVarCust() {
        return iteratorTableVarCust;
    }

    private String getHasNextPairVarCust() {
        return hasNextPairVarCust;
    }

    private String getNextPairVarCust() {
        return nextPairVarCust;
    }

    private String getFirstVarCust() {
        return firstVarCust;
    }

    private String getSecondVarCust() {
        return secondVarCust;
    }

    private String getBeforeDisplayingVar() {
        return beforeDisplayingVar;
    }

    private CustList<RendDynOperationNode> getExpsIteratorTableCust() {
        return expsIteratorTableCust;
    }

    private CustList<RendDynOperationNode> getExpsHasNextPairCust() {
        return expsHasNextPairCust;
    }

    private CustList<RendDynOperationNode> getExpsNextPairCust() {
        return expsNextPairCust;
    }

    private CustList<RendDynOperationNode> getExpsFirstCust() {
        return expsFirstCust;
    }

    private CustList<RendDynOperationNode> getExpsSecondCust() {
        return expsSecondCust;
    }

    @Override
    public Message validate(Configuration _conf, NodeContainer _cont, String _validatorId, ContextEl _ctx, RendStackCall _rendStack) {
        Struct validator_ = _conf.getBuiltValidators().getVal(_validatorId);
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
        Argument arg_ = RenderExpUtil.calculateReuse(expsValidate, this, _ctx, _rendStackCall);
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
    @Override
    public String getStringKey(Struct _instance, ContextEl _ctx, RendStackCall _stack) {
        if (_instance instanceof EnumerableStruct) {
            return ((EnumerableStruct) _instance).getName();
        }
        return processString(new Argument(_instance), _ctx, _stack);
    }

    @Override
    public void beforeDisplaying(Struct _arg, Configuration _cont, ContextEl _ctx, RendStackCall _rendStack) {
        String clName_ = _arg.getClassName(_ctx);
        if (!ExecInherits.isCorrectExecute(clName_, beanAliases.getAliasBean(), _ctx)) {
            return;
        }
        String locName_ = getBeforeDisplayingVar();
        _rendStack.getLastPage().putInternVars(locName_, _arg, _ctx);
        _rendStack.getLastPage().setEnabledOp(false);
        RenderExpUtil.calculateReuse(expsBeforeDisplaying, this, _ctx, _rendStack);
        _rendStack.getLastPage().setEnabledOp(true);
        _rendStack.getLastPage().clearInternVars();
    }

    private String getScope(Struct _bean, ContextEl _ctx, RendStackCall _rendStackCall) {
        _rendStackCall.getLastPage().putInternVars(getScopeVar, _bean, _ctx);
        _rendStackCall.getLastPage().setEnabledOp(false);
        Argument argument_ = RenderExpUtil.calculateReuse(expsGetScope, this, _ctx, _rendStackCall);
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
        RenderExpUtil.calculateReuse(expsSetScope, this, _ctx, _rendStackCall);
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
        RenderExpUtil.calculateReuse(expsSetLanguage, this, _ctx, _rendStackCall);
        _rendStackCall.getLastPage().setEnabledOp(true);
        _rendStackCall.getLastPage().clearInternVars();
    }

    @Override
    public Argument iteratorMultTable(Struct _arg, Configuration _cont, ContextEl _ctx, RendStackCall _rendStack) {
        String locName_ = getIteratorTableVarCust();
        _rendStack.getLastPage().putInternVars(locName_, _arg, _ctx);
        _rendStack.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsIteratorTableCust(), this, _ctx, _rendStack);
        _rendStack.getLastPage().clearInternVars();
        _rendStack.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument hasNextPair(Struct _arg, Configuration _conf, ContextEl _ctx, RendStackCall _rendStack) {
        String locName_ = getHasNextPairVarCust();
        _rendStack.getLastPage().putInternVars(locName_, _arg, _ctx);
        _rendStack.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsHasNextPairCust(), this, _ctx, _rendStack);
        _rendStack.getLastPage().clearInternVars();
        _rendStack.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument nextPair(Struct _arg, Configuration _conf, ContextEl _ctx, RendStackCall _rendStack) {
        String locName_ = getNextPairVarCust();
        _rendStack.getLastPage().putInternVars(locName_, _arg, _ctx);
        _rendStack.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsNextPairCust(), this, _ctx, _rendStack);
        _rendStack.getLastPage().clearInternVars();
        _rendStack.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument first(Struct _arg, Configuration _conf, ContextEl _ctx, RendStackCall _rendStack) {
        String locName_ = getFirstVarCust();
        _rendStack.getLastPage().putInternVars(locName_, _arg, _ctx);
        _rendStack.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsFirstCust(), this, _ctx, _rendStack);
        _rendStack.getLastPage().clearInternVars();
        _rendStack.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument second(Struct _arg, Configuration _conf, ContextEl _ctx, RendStackCall _rendStack) {
        String locName_ = getSecondVarCust();
        _rendStack.getLastPage().putInternVars(locName_, _arg, _ctx);
        _rendStack.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsSecondCust(), this, _ctx, _rendStack);
        _rendStack.getLastPage().clearInternVars();
        _rendStack.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument iteratorList(Struct _arg, Configuration _cont, ContextEl _ctx, RendStackCall _rendStack) {
        String locName_ = getIteratorVar();
        _rendStack.getLastPage().putInternVars(locName_, _arg, _ctx);
        _rendStack.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsIterator(), this, _ctx, _rendStack);
        _rendStack.getLastPage().clearInternVars();
        _rendStack.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument nextList(Struct _arg, Configuration _cont, ContextEl _ctx, RendStackCall _rendStack) {
        String locName_ = getNextVar();
        _rendStack.getLastPage().putInternVars(locName_, _arg, _ctx);
        _rendStack.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsNext(), this, _ctx, _rendStack);
        _rendStack.getLastPage().clearInternVars();
        _rendStack.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument hasNext(Struct _arg, Configuration _cont, ContextEl _ctx, RendStackCall _rendStack) {
        String locName_ = getHasNextVar();
        _rendStack.getLastPage().putInternVars(locName_, _arg, _ctx);
        _rendStack.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsHasNext(), this, _ctx, _rendStack);
        _rendStack.getLastPage().clearInternVars();
        _rendStack.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public String processString(Argument _arg, ContextEl _ctx, RendStackCall _stack) {
        Argument arg_ = RendDynOperationNode.processString(_arg, _ctx, _stack);
        if (_ctx.callsOrException(_stack.getStackCall())) {
            return "";
        }
        return NumParsers.getString(arg_.getStruct()).getInstance();
    }


    public abstract void buildAliases(Element _elt, String _lg, RendKeyWords _rkw, KeyWords _kw, RendAnalysisMessages _rMess, AnalysisMessages _mess);
}
