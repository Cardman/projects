package code.formathtml.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.options.ValidatorStandard;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.AdvancedSetOffset;
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
import code.expressionlanguage.DefaultFullStack;
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

    private DefaultBeanAliases beanAliases = new DefaultBeanAliases();

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

    private CustList<RendDynOperationNode> newCall(String _varPrevious, String _previous,
                                                   ClassMethodId _id,
                                                   String _res,
                                                   StringMap<String> _args, Classes _classes) {
        CustList<RendDynOperationNode> ops_ = new CustList<RendDynOperationNode>();
        ExecClassArgumentMatching clMatch_ = new ExecClassArgumentMatching(_res);
        RendDotOperation dot_ = new RendDotOperation(new ExecOperationContent(0, clMatch_, _args.size()+2));
        RendInternVariableOperation r_ = new RendInternVariableOperation(0,new ExecClassArgumentMatching(_previous),0,_varPrevious);
        ops_.add(r_);
        dot_.appendChild(r_);
        String id_ = StringExpUtil.getIdFromAllTypes(_id.getClassName());
        ExecRootBlock classBody_ = _classes.getClassBody(id_);
        CustList<ExecNamedFunctionBlock> list_ = ExecBlock.getMethodBodiesById(classBody_, _id.getConstraints());
        ExecNamedFunctionBlock fct_ = list_.first();
        RendFctOperation f_ = new RendFctOperation(fct_, classBody_, new ExecOperationContent(1, clMatch_, _args.size()+1), new ExecInstFctContent(_id), true);
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
        ExecRootBlock ex_ = _classes.getClassBody(aliasStringMapObject_);
        ExecClassArgumentMatching clMatch_ = new ExecClassArgumentMatching(aliasStringMapObject_);
        ConstructorId id_ = new ConstructorId(aliasStringMapObject_, new StringList(), false);
        AnaInstancingCommonContent cont_ = new AnaInstancingCommonContent(id_.getName());
        cont_.setConstId(id_);
        cont_.setClassName(id_.getName());
        opsMap.add(new RendStandardInstancingOperation(ex_, new ExecOperationContent(0, clMatch_, 0), new ExecInstancingCommonContent(cont_), new ExecInstancingStdContent(new AnaInstancingStdContent())));
    }

    private static String tr(StringList _list) {
        return ValidatorStandard.tr(_list);
    }

    @Override
    public ReportedMessages setupAll(Navigation _nav, Configuration _conf, StringMap<String> _files, DualAnalyzedContext _dual) {
        Forwards forwards_ = new Forwards();
        AnalyzedPageEl page_ = _dual.getAnalyzed();
        setupRendClasses(_files, page_, _dual.getContext().getFilesConfName());
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        analyzingDoc_.setContent(this);
        analyzingDoc_.setInputBuilder(new DefaultInputBuilder());
        _nav.initInstancesPattern(page_, analyzingDoc_);
        StringMap<AnaRendDocumentBlock> d_ = _nav.analyzedRenders(page_, this, analyzingDoc_, _dual.getContext());
        ReportedMessages messages_ = page_.getMessages();
        if (!messages_.isAllEmptyErrors()) {
            return messages_;
        }
        forwardAndClear(_conf, page_, analyzingDoc_, forwards_, d_, _dual.getContext().getContext());
        Options options_ = page_.getOptions();
        ContextEl context_ = _dual.getContext().getContext();
        context_.setFullStack(new DefaultFullStack(context_));
        Classes.tryInitStaticlyTypes(context_, options_);
        return messages_;
    }

    public void forwardAndClear(Configuration _conf, AnalyzedPageEl _page, AnalyzingDoc _anaDoc, Forwards _forward, StringMap<AnaRendDocumentBlock> _analyzed, ContextEl _context) {
        Classes.forwardAndClear(_context, _page, _forward);
        RendForwardInfos.buildExec(_anaDoc, _analyzed, _forward, _conf);
        buildIterables(_context.getClasses());
    }

    private void setupRendClasses(StringMap<String> _files, AnalyzedPageEl _page, String _filesConfName) {
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
        //!classFiles_.isEmpty()
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
    public void initBeans(Configuration _conf, String _language, Struct _db, ContextEl _ctx) {
        int index_ = 0;
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            BeanInfo info_ = e.getValue();
            _conf.addPage(new ImportingPage());
            Argument arg_ = RenderExpUtil.calculateReuse(info_.getExps(), _conf, this, _ctx);
            if (_ctx.callsOrException()) {
                _conf.removeLastPage();
                return;
            }
            Struct strBean_ = arg_.getStruct();
            String clName_ = strBean_.getClassName(_ctx);
            if (!ExecTemplates.isCorrectExecute(clName_, beanAliases.getAliasBean(), _ctx)) {
                _conf.removeLastPage();
                _conf.getBuiltBeans().setValue(index_,strBean_);
                index_++;
                continue;
            }
            Argument mapArg_ = RenderExpUtil.calculateReuse(opsMap, _conf, this, _ctx);
            ExecRootBlock rootBlock_ = _ctx.getClasses().getClassBody(beanAliases.getAliasBean());
            ExecTemplates.setInstanceField(rootBlock_, beanAliases.getAliasBean(), beanAliases.getAliasForms(), beanAliases.getAliasStringMapObject(),
                    new Argument(strBean_),mapArg_, _ctx);
            ExecTemplates.setInstanceField(rootBlock_, beanAliases.getAliasBean(), beanAliases.getAliasDataBaseField(), getAliasObject(),
                    new Argument(strBean_),new Argument(_db), _ctx);
            ExecTemplates.setInstanceField(rootBlock_, beanAliases.getAliasBean(), beanAliases.getAliasLanguage(), getAliasString(),
                    new Argument(strBean_),new Argument(new StringStruct(_language)), _ctx);
            ExecTemplates.setInstanceField(rootBlock_, beanAliases.getAliasBean(), beanAliases.getAliasScope(), getAliasString(),
                    new Argument(strBean_),new Argument(new StringStruct(info_.getScope())), _ctx);
            _conf.removeLastPage();
            _conf.getBuiltBeans().setValue(index_,strBean_);
            index_++;
        }
        index_ = 0;
        for (EntryCust<String, ValidatorInfo> e: _conf.getLateValidators().entryList()) {
            ValidatorInfo info_ = e.getValue();
            _conf.addPage(new ImportingPage());
            Argument arg_ = RenderExpUtil.calculateReuse(info_.getExps(), _conf, this, _ctx);
            _conf.removeLastPage();
            if (_ctx.callsOrException()) {
                return;
            }
            Struct strBean_ = arg_.getStruct();
            _conf.getBuiltValidators().setValue(index_,strBean_);
            index_++;
        }
    }

    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, Struct... _args) {
        return beanAliases.getOtherResult(_cont, _instance, _method, _args);
    }

    @Override
    public String processAfterInvoke(Configuration _conf, String _dest, String _beanName, Struct _bean, String _currentUrl, String _language, ContextEl _ctx) {
        ImportingPage ip_ = new ImportingPage();
        _conf.addPage(ip_);
        Struct forms_ = NullStruct.NULL_VALUE;
        if (!_beanName.isEmpty()) {
            forms_ = storeForms(_bean, _conf, _ctx);
        }
        if (_ctx.callsOrException()) {
            return "";
        }
        processInitBeans(_conf,_dest,_beanName, _currentUrl, _language, _ctx);
        if (_ctx.callsOrException()) {
            return "";
        }
        _conf.setCurrentUrl(_dest);
        String dest_ = StringUtil.getFirstToken(_dest, REF_TAG);
        String currentBeanName_;
        RendDocumentBlock rendDocumentBlock_ = _conf.getRenders().getVal(dest_);
        if (rendDocumentBlock_ == null) {
            return "";
        }
        currentBeanName_ = rendDocumentBlock_.getBeanName();
        Struct bean_ = getBeanOrNull(_conf,currentBeanName_);
        if (!_beanName.isEmpty()) {
            setStoredForms(bean_, _conf, forms_, _ctx);
        }
        if (_ctx.callsOrException()) {
            return "";
        }
        _conf.clearPages();
        return RendBlock.getRes(rendDocumentBlock_,_conf, this, _ctx);
    }
    private void processInitBeans(Configuration _conf, String _dest, String _beanName, String _currentUrl, String _language, ContextEl _ctx) {
        int s_ = _conf.getBuiltBeans().size();
        for (int i = 0; i < s_; i++) {
            String key_ = _conf.getBuiltBeans().getKey(i);
            boolean reinit_ = reinitRendBean(_conf,_dest, _beanName, key_, _currentUrl, _ctx);
            if (_ctx.callsOrException()) {
                break;
            }
            if (!reinit_) {
                continue;
            }
            Struct bean_ = _conf.getBuiltBeans().getValue(i);
            BeanInfo info_ = _conf.getBeansInfos().getValue(i);
            bean_ = newBean(_conf,_language, bean_,info_, _ctx);
            if (_ctx.callsOrException()) {
                break;
            }
            _conf.getBuiltBeans().setValue(i,bean_);
        }
    }
    private boolean reinitRendBean(Configuration _conf, String _dest, String _beanName, String _currentBean, String _currentUrl, ContextEl _ctx) {
        if (!StringUtil.quickEq(_currentBean,_beanName)) {
            return false;
        }
        Struct bean_ = getBeanOrNull(_conf,_currentBean);
        String scope_ = getScope(bean_,_conf, _ctx);
        if (_ctx.callsOrException()) {
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

    private Struct newBean(Configuration _conf, String _language, Struct _bean, BeanInfo _info, ContextEl _ctx) {
        Argument arg_ = RenderExpUtil.calculateReuse(_info.getExps(), _conf, this, _ctx);
        Struct strBean_ = arg_.getStruct();
        forwardDataBase(_bean,strBean_,_conf, _ctx);
        setStoredForms(strBean_, _conf, NullStruct.NULL_VALUE, _ctx);
        setLanguage(strBean_, _language,_conf, _ctx);
        if (_ctx.callsOrException()) {
            return NullStruct.NULL_VALUE;
        }
        String str_ = getScope(_bean,_conf, _ctx);
        if (_ctx.callsOrException()) {
            return NullStruct.NULL_VALUE;
        }
        setScope(strBean_, str_,_conf, _ctx);
        if (_ctx.callsOrException()) {
            return NullStruct.NULL_VALUE;
        }
        return strBean_;
    }

    private Struct getBeanOrNull(Configuration _conf,String _currentBeanName) {
        Struct bean_ = getBean(_conf,_currentBeanName);
        if (bean_ == null) {
            bean_ = NullStruct.NULL_VALUE;
        }
        return bean_;
    }

    private Struct getBean(Configuration _conf,String _beanName) {
        return _conf.getBuiltBeans().getVal(_beanName);
    }

    private void forwardDataBase(Struct _bean, Struct _to, Configuration _conf, ContextEl _ctx) {
        if (_ctx.callsOrException()) {
            return;
        }
        _conf.getLastPage().putInternVars(getDataBaseVar, _bean, _ctx);
        _conf.getLastPage().setEnabledOp(false);
        Argument argument_ = RenderExpUtil.calculateReuse(expsGetDataBase, _conf, this, _ctx);
        _conf.getLastPage().clearInternVars();
        if (_ctx.callsOrException()) {
            _conf.getLastPage().setEnabledOp(true);
            return;
        }
        _conf.getLastPage().putInternVars(setDataBaseVar, _to, _ctx);
        _conf.getLastPage().putInternVars(setDataBaseVarArg, argument_.getStruct(), _ctx);
        RenderExpUtil.calculateReuse(expsSetDataBase, _conf, this, _ctx);
        _conf.getLastPage().setEnabledOp(true);
        _conf.getLastPage().clearInternVars();
    }


    private Struct storeForms(Struct _bean, Configuration _conf, ContextEl _ctx) {
        Argument forms_ = getForms(_bean, _conf, _ctx);
        _conf.getLastPage().setEnabledOp(true);
        if (_ctx.callsOrException()) {
            return NullStruct.NULL_VALUE;
        }
        return forms_.getStruct();
    }

    private Argument getForms(Struct _bean, Configuration _conf, ContextEl _ctx) {
        String clName_ = _bean.getClassName(_ctx);
        _conf.getLastPage().setEnabledOp(false);
        if (!ExecTemplates.isCorrectExecute(clName_, beanAliases.getAliasBean(), _ctx)) {
            return RenderExpUtil.calculateReuse(opsMap, _conf, this, _ctx);
        }
        _conf.getLastPage().putInternVars(getFormsVar, _bean, _ctx);
        Argument argument_ = RenderExpUtil.calculateReuse(expsGetForms, _conf, this, _ctx);
        _conf.getLastPage().clearInternVars();
        if (_ctx.callsOrException()) {
            return argument_;
        }
        if (argument_.isNull()) {
            return RenderExpUtil.calculateReuse(opsMap, _conf, this, _ctx);
        }
        return argument_;
    }


    private void setStoredForms(Struct _bean, Configuration _conf, Struct _storedForms, ContextEl _ctx) {
        if (_ctx.callsOrException()) {
            return;
        }
        _conf.getLastPage().setEnabledOp(false);
        _conf.getLastPage().putInternVars(setFormsVar, _bean, _ctx);
        _conf.getLastPage().putInternVars(setFormsVarArg, _storedForms, _ctx);
        RenderExpUtil.calculateReuse(expsSetForms, _conf, this, _ctx);
        _conf.getLastPage().clearInternVars();
        _conf.getLastPage().setEnabledOp(true);
    }

    @Override
    protected void gearFw(Configuration _conf, Struct _mainBean, RendImport _node, boolean _keepField, Struct _bean, ContextEl _ctx) {
        Argument forms_ = getForms(_bean, _conf, _ctx);
        if (_ctx.callsOrException()) {
            _conf.getLastPage().setEnabledOp(true);
            return;
        }
        Argument formsMap_ = getForms(_mainBean,_conf, _ctx);
        if (_ctx.callsOrException()) {
            _conf.getLastPage().setEnabledOp(true);
            return;
        }
        if (_keepField) {
            for (RendBlock f_: RendBlock.getDirectChildren(_node)) {
                if (!(f_ instanceof RendImportForm)) {
                    continue;
                }
                String name_ = ((RendImportForm)f_).getName();
                forwardMap(formsMap_.getStruct(),forms_.getStruct(),new StringStruct(name_),_conf, _ctx);
                if (_ctx.callsOrException()) {
                    _conf.getLastPage().setEnabledOp(true);
                    return;
                }
            }
        } else {
            //add option for copying forms (default copy)
            putAllMap(forms_.getStruct(),formsMap_.getStruct(),_conf, _ctx);
        }
        _conf.getLastPage().setEnabledOp(true);
    }

    public DefaultBeanAliases getBeanAliases() {
        return beanAliases;
    }

    @Override
    public Argument getCommonArgument(RendSettableFieldOperation _rend, Argument _previous, Configuration _conf, ContextEl _context) {
        int off_ =_rend.getOff();
        ClassField fieldId_ = _rend.getClassField();
        String className_ = fieldId_.getClassName();
        String fieldName_ = fieldId_.getFieldName();
        boolean staticField_ = _rend.isStaticField();
        Argument previous_;
        if (!staticField_) {
            previous_ = new Argument(ExecTemplates.getParent(_rend.getAnc(), className_, _previous.getStruct(), _context));
        } else {
            previous_ = new Argument();
        }
        String fieldType_ = _rend.getRealType();
        return getField(_conf, off_, className_, fieldName_, staticField_, previous_, fieldType_, _context);
    }

    private static Argument getField(Configuration _conf, int _off, String _className, String _fieldName, boolean _staticField, Argument _previous, String _fieldType, ContextEl _context) {
        if (_context.callsOrException()) {
            return Argument.createVoid();
        }
        return ExecTemplates.getField(new AdvancedSetOffset(_conf), _context.getExiting(), _className, _fieldName, _staticField, _fieldType, _previous, _context, _off);
    }

    @Override
    public Argument getCommonSetting(RendSettableFieldOperation _rend, Argument _previous, Configuration _conf, Argument _right, ContextEl _context) {
        int off_ = _rend.getOff();
        String fieldType_ = _rend.getRealType();
        boolean isStatic_ = _rend.isStaticField();
        boolean isFinal_ = _rend.isFinalField();
        ClassField fieldId_ = _rend.getClassField();
        String className_ = fieldId_.getClassName();
        String fieldName_ = fieldId_.getFieldName();
        Argument previous_;
        if (!isStatic_) {
            previous_ = new Argument(ExecTemplates.getParent(_rend.getAnc(), className_, _previous.getStruct(), _context));
        } else {
            previous_ = new Argument();
        }
        //Come from code directly so constant static fields can be initialized here
        return setField(_conf, _right, off_, fieldType_, isStatic_, isFinal_, _rend.getRootBlock(), className_, fieldName_, previous_, _context);
    }

    private static Argument setField(Configuration _conf, Argument _right, int _off, String _fieldType, boolean _isStatic, boolean _isFinal, ExecRootBlock _root, String _className, String _fieldName, Argument _previous, ContextEl _context) {
        if (_context.callsOrException()) {
            return Argument.createVoid();
        }
        return ExecTemplates.setField(new AdvancedSetOffset(_conf), _context.getExiting(), _root,_className, _fieldName, _isStatic, _isFinal, false, _fieldType, _previous, _right, _context, _off);
    }

    @Override
    public Argument getCommonFctArgument(RendStdFctOperation _rend, Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, ContextEl _context) {
        int off_ = StringUtil.getFirstPrintableCharIndex(_rend.getMethodName());
        _rend.setRelativeOffsetPossibleLastPage(_rend.getIndexInEl()+off_, _conf);
        MethodId methodId_ = _rend.getClassMethodId().getConstraints();
        String lastType_ = _rend.getLastType();
        String classNameFound_;
        Argument prev_;
        if (!_rend.isStaticMethod()) {
            classNameFound_ = _rend.getClassMethodId().getClassName();
            Struct argPrev_ = _previous.getStruct();
            prev_ = new Argument(ExecTemplates.getParent(0, classNameFound_, argPrev_, _context));
            if (_context.callsOrException()) {
                return new Argument();
            }
        } else {
            prev_ = new Argument();
        }
        classNameFound_ = _rend.getClassMethodId().getClassName();
        CustList<RendDynOperationNode> chidren_ = _rend.getChildrenNodes();
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, chidren_).getArguments();
        CustList<Argument> firstArgs_ = RendInvokingOperation.listArguments(chidren_, _rend.getNaturalVararg(), lastType_, first_);
        return ExecInvokingOperation.callStd(_context.getExiting(), _context, classNameFound_, methodId_, prev_, firstArgs_);
    }

    private void forwardMap(Struct _map, Struct _to, Struct _key, Configuration _conf, ContextEl _ctx) {
        _conf.getLastPage().putInternVars(getValVar, _map, _ctx);
        _conf.getLastPage().putInternVars(getValVarArg, _key, _ctx);
        Argument argument_ = RenderExpUtil.calculateReuse(expsGetVal, _conf, this, _ctx);
        _conf.getLastPage().clearInternVars();
        if (_ctx.callsOrException()) {
            return;
        }
        _conf.getLastPage().putInternVars(putVarCust, _to, _ctx);
        _conf.getLastPage().putInternVars(putVarCustKey, _key, _ctx);
        _conf.getLastPage().putInternVars(putVarCustValue, argument_.getStruct(), _ctx);
        RenderExpUtil.calculateReuse(expsPut, _conf, this, _ctx);
        _conf.getLastPage().clearInternVars();
    }

    public void putAllMap(Struct _map, Struct _other, Configuration _conf, ContextEl _ctx) {
        _conf.getLastPage().putInternVars(putAllVarCust, _map, _ctx);
        _conf.getLastPage().putInternVars(putAllVarCustArg, _other, _ctx);
        RenderExpUtil.calculateReuse(expsPutAll, _conf, this, _ctx);
        _conf.getLastPage().clearInternVars();
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
    public Message validate(Configuration _conf, NodeContainer _cont, String _validatorId, ContextEl _ctx) {
        Struct validator_ = _conf.getBuiltValidators().getVal(_validatorId);
        if (validator_ == null) {
            return null;
        }
        return validate(_conf,_cont,validator_, _ctx);
    }

    private Message validate(Configuration _conf, NodeContainer _cont, Struct _validator, ContextEl _ctx) {
        LocalVariable locVar_;
        _conf.getLastPage().putInternVars(validateVar, _validator, _ctx);
        locVar_ = newLocVar(_cont);
        _conf.getLastPage().putInternVars(validateVarArgNewValue, locVar_);
        locVar_ = LocalVariable.newLocalVariable(_cont.getTypedStruct(), getAliasObject());
        _conf.getLastPage().putInternVars(validateVarArgOldValue, locVar_);
        locVar_ = LocalVariable.newLocalVariable(_cont.getBean(), getAliasObject());
        _conf.getLastPage().putInternVars(validateVarArgBean, locVar_);
        CustList<Struct> params_ = _cont.getStructParam();
        int size_ = params_.size();
        ArrayStruct arr_ = new ArrayStruct(size_ +1,StringExpUtil.getPrettyArrayType(getAliasObject()));
        arr_.set(0, _cont.getUpdated());
        for (int i = 0; i < size_; i++) {
            arr_.set(i+1, params_.get(i));
        }
        locVar_ = LocalVariable.newLocalVariable(arr_,StringExpUtil.getPrettyArrayType(getAliasObject()));
        _conf.getLastPage().putInternVars(validateVarArgForm, locVar_);
        _conf.getLastPage().putInternVars(validateVarArgClassField, new StringStruct(_cont.getIdFieldClass()), _ctx);
        _conf.getLastPage().putInternVars(vlidateVarArgNameField, new StringStruct(_cont.getIdFieldName()), _ctx);
        _conf.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(expsValidate, _conf, this, _ctx);
        _conf.getLastPage().setEnabledOp(true);
        _conf.getLastPage().clearInternVars();
        if (_ctx.callsOrException()) {
            return null;
        }
        if (arg_.isNull()) {
            return null;
        }
        return DefaultBeanAliases.getMessageStruct(arg_.getStruct(), beanAliases.getAliasMessage()).getInstance();
    }
    @Override
    public String getStringKey(Struct _instance, ContextEl _ctx) {
        if (_instance instanceof EnumerableStruct) {
            return ((EnumerableStruct) _instance).getName();
        }
        return processString(new Argument(_instance), _ctx);
    }

    @Override
    public void beforeDisplaying(Struct _arg, Configuration _cont, ContextEl _ctx) {
        String clName_ = _arg.getClassName(_ctx);
        if (!ExecTemplates.isCorrectExecute(clName_, beanAliases.getAliasBean(), _ctx)) {
            return;
        }
        String locName_ = getBeforeDisplayingVar();
        _cont.getLastPage().putInternVars(locName_, _arg, _ctx);
        _cont.getLastPage().setEnabledOp(false);
        RenderExpUtil.calculateReuse(expsBeforeDisplaying,_cont, this, _ctx);
        _cont.getLastPage().setEnabledOp(true);
        _cont.getLastPage().clearInternVars();
    }

    private String getScope(Struct _bean, Configuration _cont, ContextEl _ctx) {
        _cont.getLastPage().putInternVars(getScopeVar, _bean, _ctx);
        _cont.getLastPage().setEnabledOp(false);
        Argument argument_ = RenderExpUtil.calculateReuse(expsGetScope, _cont, this, _ctx);
        _cont.getLastPage().setEnabledOp(true);
        _cont.getLastPage().clearInternVars();
        if (_ctx.callsOrException() || argument_.isNull()) {
            return "";
        }
        return NumParsers.getString(argument_.getStruct()).getInstance();
    }
    private void setScope(Struct _bean, String _scope, Configuration _cont, ContextEl _ctx) {
        _cont.getLastPage().putInternVars(setScopeVar, _bean, _ctx);
        _cont.getLastPage().putInternVars(setScopeVarArg, new StringStruct(_scope), _ctx);
        _cont.getLastPage().setEnabledOp(false);
        RenderExpUtil.calculateReuse(expsSetScope, _cont, this, _ctx);
        _cont.getLastPage().setEnabledOp(true);
        _cont.getLastPage().clearInternVars();
    }
    private void setLanguage(Struct _bean, String _scope, Configuration _cont, ContextEl _ctx) {
        if (_ctx.callsOrException()) {
            return;
        }
        _cont.getLastPage().putInternVars(setLanguageVar, _bean, _ctx);
        _cont.getLastPage().putInternVars(setLanguageVarArg, new StringStruct(_scope), _ctx);
        _cont.getLastPage().setEnabledOp(false);
        RenderExpUtil.calculateReuse(expsSetLanguage, _cont, this, _ctx);
        _cont.getLastPage().setEnabledOp(true);
        _cont.getLastPage().clearInternVars();
    }

    @Override
    public Argument iteratorMultTable(Struct _arg, Configuration _cont, ContextEl _ctx) {
        String locName_ = getIteratorTableVarCust();
        _cont.getLastPage().putInternVars(locName_, _arg, _ctx);
        _cont.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsIteratorTableCust(), _cont, this, _ctx);
        _cont.getLastPage().clearInternVars();
        _cont.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument hasNextPair(Struct _arg, Configuration _conf, ContextEl _ctx) {
        String locName_ = getHasNextPairVarCust();
        _conf.getLastPage().putInternVars(locName_, _arg, _ctx);
        _conf.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsHasNextPairCust(),_conf, this, _ctx);
        _conf.getLastPage().clearInternVars();
        _conf.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument nextPair(Struct _arg, Configuration _conf, ContextEl _ctx) {
        String locName_ = getNextPairVarCust();
        _conf.getLastPage().putInternVars(locName_, _arg, _ctx);
        _conf.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsNextPairCust(), _conf, this, _ctx);
        _conf.getLastPage().clearInternVars();
        _conf.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument first(Struct _arg, Configuration _conf, ContextEl _ctx) {
        String locName_ = getFirstVarCust();
        _conf.getLastPage().putInternVars(locName_, _arg, _ctx);
        _conf.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsFirstCust(), _conf, this, _ctx);
        _conf.getLastPage().clearInternVars();
        _conf.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument second(Struct _arg, Configuration _conf, ContextEl _ctx) {
        String locName_ = getSecondVarCust();
        _conf.getLastPage().putInternVars(locName_, _arg, _ctx);
        _conf.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsSecondCust(), _conf, this, _ctx);
        _conf.getLastPage().clearInternVars();
        _conf.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument iterator(Struct _arg, Configuration _cont, ContextEl _ctx) {
        String locName_ = getIteratorVar();
        _cont.getLastPage().putInternVars(locName_, _arg, _ctx);
        _cont.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsIterator(), _cont, this, _ctx);
        _cont.getLastPage().clearInternVars();
        _cont.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument next(Struct _arg, Configuration _cont, ContextEl _ctx) {
        String locName_ = getNextVar();
        _cont.getLastPage().putInternVars(locName_, _arg, _ctx);
        _cont.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsNext(), _cont, this, _ctx);
        _cont.getLastPage().clearInternVars();
        _cont.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public Argument hasNext(Struct _arg, Configuration _cont, ContextEl _ctx) {
        String locName_ = getHasNextVar();
        _cont.getLastPage().putInternVars(locName_, _arg, _ctx);
        _cont.getLastPage().setEnabledOp(false);
        Argument arg_ = RenderExpUtil.calculateReuse(getExpsHasNext(), _cont, this, _ctx);
        _cont.getLastPage().clearInternVars();
        _cont.getLastPage().setEnabledOp(true);
        return arg_;
    }

    @Override
    public String processString(Argument _arg, ContextEl _ctx) {
        Argument arg_ = RendDynOperationNode.processString(_arg, _ctx);
        if (_ctx.callsOrException()) {
            return "";
        }
        return NumParsers.getString(arg_.getStruct()).getInstance();
    }


    public abstract void buildAliases(Element _elt, String _lg, RendKeyWords _rkw, KeyWords _kw, RendAnalysisMessages _rMess, AnalysisMessages _mess);
}
