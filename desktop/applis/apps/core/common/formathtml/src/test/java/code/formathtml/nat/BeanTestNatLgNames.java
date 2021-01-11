package code.formathtml.nat;

import code.bean.Bean;
import code.bean.BeanStruct;
import code.bean.nat.NativeContextEl;
import code.bean.nat.NativeConverterCheck;
import code.bean.nat.NativeReducingOperations;
import code.bean.validator.Validator;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefaultConstantsCalculator;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.StandardInstancingOperation;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.Delimiters;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.coverage.Coverage;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.Navigation;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.exec.blocks.RendImport;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.opers.RendSettableFieldOperation;
import code.formathtml.exec.opers.RendStdFctOperation;
import code.formathtml.fwd.RendForwardInfos;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.Message;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DualAnalyzedContext;
import code.formathtml.util.NodeContainer;
import code.formathtml.util.NodeInformations;
import code.maths.montecarlo.DefaultGenerator;
import code.sml.Element;
import code.util.*;
import code.util.core.StringUtil;

public abstract class BeanTestNatLgNames extends BeanLgNames {
    public static final String TYPE_LIST = "ls";
    public static final String TYPE_MAP = "lse";
    public static final String TYPE_DISPLAYABLE = "code.util.ints.Displayable";
    public static final String TYPE_VALIDATOR = "code.bean.validator.Validator";
    public static final String TYPE_BEAN = "code.bean.Bean";

    protected static final String TYPE_ENTRY = "$custentry";
    protected static final String TYPE_ITERATOR = "code.util.SimpleItr";
    protected static final String TYPE_COUNTABLE = "code.util.ints.Countable";
    private static final String TYPE_ENTRIES = "$custentries";
    private final StringMap<String> iterables = new StringMap<String>();
    private final StringMap<Bean> beans = new StringMap<Bean>();
    private final StringMap<BeanStruct> beansStructs = new StringMap<BeanStruct>();
    private StringMap<Validator> validators = new StringMap<Validator>();

    public BeanTestNatLgNames() {
        super(new DefaultGenerator());
    }

    @Override
    public void preInitBeans(Configuration _conf) {
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            _conf.getBuiltBeans().addEntry(e.getKey(), NullStruct.NULL_VALUE);
        }
    }

    @Override
    public void initBeans(Configuration _conf, String _language, Struct _db, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        int index_ = 0;
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            BeanStruct beanStruct_ = newSimpleBean(_language, e.getValue(), _ctx);
            beansStructs.addEntry(e.getKey(),beanStruct_);
            _conf.getBuiltBeans().setValue(index_, beanStruct_);
            index_++;
        }
    }

    protected void buildBeans() {
        CustList<StandardField> fields_;
        fields_ = new CustList<StandardField>();
        StandardClass std_;
        CustList<StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        methods_ = new CustList<StandardMethod>();
        StringList params_;
        StandardMethod method_;
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass(TYPE_BEAN, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        getStandards().addEntry(TYPE_BEAN, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        StandardClass cl_;
        cl_ = new StandardClass(TYPE_LIST, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        cl_.getDirectInterfaces().add(TYPE_COUNTABLE);
        getStandards().addEntry(TYPE_LIST, cl_);
        methods_ = new CustList<StandardMethod>();
        cl_ = new StandardClass(TYPE_MAP, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        cl_.getDirectInterfaces().add(TYPE_COUNTABLE);
        cl_.getDirectInterfaces().add(TYPE_ENTRIES);
        getStandards().addEntry(TYPE_MAP, cl_);
        params_ = new StringList();
        methods_ = new CustList<StandardMethod>();
        StandardInterface stdi_ = new StandardInterface(TYPE_COUNTABLE, methods_, params_);
        params_ = new StringList();
        method_ = new StandardMethod(getContent().getCharSeq().getAliasIsEmpty(), params_, getAliasPrimBoolean(), false, MethodModifier.ABSTRACT);
        methods_.add(method_);
        getStandards().addEntry(TYPE_COUNTABLE, stdi_);
        methods_ = new CustList<StandardMethod>();
        stdi_ = new StandardInterface(TYPE_ENTRIES, methods_, new StringList());
        getStandards().addEntry(TYPE_ENTRIES, stdi_);
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<StandardMethod>();
        std_ = new StandardClass(TYPE_ITERATOR, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        getStandards().addEntry(TYPE_ITERATOR, std_);
        methods_ = new CustList<StandardMethod>();
        stdi_ = new StandardInterface(TYPE_DISPLAYABLE, methods_, new StringList());
        getStandards().addEntry(TYPE_DISPLAYABLE, stdi_);
        cl_ = new StandardClass(TYPE_VALIDATOR, fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        getStandards().addEntry(TYPE_VALIDATOR, cl_);
    }


    @Override
    public String getStringKey(Struct _instance, ContextEl _ctx, StackCall _stack) {
        ResultErrorStd res_ = getName(_ctx, _instance);
        Struct str_ = res_.getResult();
        return processString(new Argument(str_), _ctx, _stack);
    }

    public ResultErrorStd getName(ContextEl _cont, Struct _instance) {
        return getOtherName(_cont, _instance);
    }
    public abstract ResultErrorStd getOtherName(ContextEl _cont, Struct _instance);
    @Override
    public void beforeDisplaying(Struct _arg, Configuration _cont, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        ((BeanStruct)_arg).getBean().beforeDisplaying();
    }


    @Override
    public Argument iteratorMultTable(Struct _arg, Configuration _cont, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        ArrayStruct array_ = ExecArrayFieldOperation.getArray(_arg, _ctx);
        return new Argument(new SimpleItrStruct(StringUtil.concat(TYPE_ITERATOR,Templates.TEMPLATE_BEGIN, TYPE_ENTRY,Templates.TEMPLATE_BEGIN, "?,?",Templates.TEMPLATE_END,Templates.TEMPLATE_END),array_));
    }

    @Override
    public Argument hasNextPair(Struct _arg, Configuration _conf, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        SimpleItrStruct simpleItrStruct_ = getSimpleItrStruct(_arg, _ctx);
        return new Argument(BooleanStruct.of(simpleItrStruct_.hasNext()));
    }

    @Override
    public Argument nextPair(Struct _arg, Configuration _conf, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        SimpleItrStruct simpleItrStruct_ = getSimpleItrStruct(_arg, _ctx);
        Struct resObj_ = simpleItrStruct_.next();
        return new Argument(resObj_);
    }

    @Override
    public Argument first(Struct _arg, Configuration _conf, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        PairStruct pairStruct_ = getPairStruct(_arg, _ctx);
        Struct resObj_ = pairStruct_.getFirst();
        return new Argument(resObj_);
    }

    @Override
    public Argument second(Struct _arg, Configuration _conf, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        PairStruct pairStruct_ = getPairStruct(_arg, _ctx);
        Struct resObj_ = pairStruct_.getSecond();
        return new Argument(resObj_);
    }

    @Override
    public Argument iterator(Struct _arg, Configuration _cont, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        ArrayStruct array_ = ExecArrayFieldOperation.getArray(_arg, _ctx);
        return new Argument(new SimpleItrStruct(StringUtil.concat(TYPE_ITERATOR,Templates.TEMPLATE_BEGIN,"?",Templates.TEMPLATE_END),array_));
    }

    @Override
    public Argument next(Struct _arg, Configuration _cont, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        SimpleItrStruct simpleItrStruct_ = getSimpleItrStruct(_arg, _ctx);
        Struct resObj_ = simpleItrStruct_.next();
        return new Argument(resObj_);
    }

    @Override
    public Argument hasNext(Struct _arg, Configuration _cont, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        SimpleItrStruct simpleItrStruct_ = getSimpleItrStruct(_arg, _ctx);
        return new Argument(BooleanStruct.of(simpleItrStruct_.hasNext()));
    }

    public static PairStruct getPairStruct(Struct _arg, ContextEl _ctx) {
        if (_arg instanceof PairStruct) {
            return (PairStruct)_arg;
        }
        String typeInst_ = _ctx.getStandards().getCoreNames().getAliasObject();
        return new PairStruct(StringUtil.concat(TYPE_ENTRY,Templates.TEMPLATE_BEGIN,typeInst_,",",typeInst_,Templates.TEMPLATE_END),NullStruct.NULL_VALUE,NullStruct.NULL_VALUE);
    }

    public static SimpleItrStruct getSimpleItrStruct(Struct _arg, ContextEl _ctx) {
        if (_arg instanceof SimpleItrStruct) {
            return (SimpleItrStruct)_arg;
        }
        ArrayStruct array_ = ExecArrayFieldOperation.getArray(_arg, _ctx);
        String typeInst_ = StringUtil.nullToEmpty(StringExpUtil.getQuickComponentType(array_.getClassName(_ctx)));
        return new SimpleItrStruct(StringUtil.concat(TYPE_ITERATOR,Templates.TEMPLATE_BEGIN,typeInst_,Templates.TEMPLATE_END),array_);
    }

    public ReportedMessages setupAll(Navigation _nav, Configuration _conf, StringMap<String> _files, DualAnalyzedContext _dual) {
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        analyzingDoc_.setContent(this);
        analyzingDoc_.setReducingOperations(new NativeReducingOperations());
        analyzingDoc_.setInputBuilder(new NatInputBuilder());
        analyzingDoc_.setConverterCheck(new NativeConverterCheck());
        AnalyzedPageEl page_ = _dual.getAnalyzed();
        page_.setForEachFetch(new NativeTestForEachFetch(this));
        initInstancesPattern(_nav.getSession(),analyzingDoc_);
        StringMap<AnaRendDocumentBlock> d_ = _nav.analyzedRenders(page_, this, analyzingDoc_, _dual.getContext());
        RendForwardInfos.buildExec(analyzingDoc_, d_, new Forwards(), _conf);
        return page_.getMessages();
    }
    public static void initInstancesPattern(Configuration _conf, AnalyzingDoc _anaDoc) {
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            BeanInfo info_ = e.getValue();
            OperationsSequence seq_ = new OperationsSequence();
            seq_.setValue("",0);
            seq_.setDelimiter(new Delimiters());
            StandardInstancingOperation root_ = new StandardInstancingOperation(0,0,null,seq_);
            root_.setConstId(new ConstructorId(info_.getClassName(), new StringList(), false));
            root_.setClassName(info_.getClassName());
            info_.setResolvedClassName(info_.getClassName());
            _anaDoc.getBeansInfos().addEntry(root_,info_);
        }
    }
    public void setBeanForms(Configuration _conf, Struct _mainBean,
                             RendImport _node, boolean _keepField, String _beanName, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        if (!(_mainBean instanceof BeanStruct)) {
            return;
        }
        BeanStruct bean_ = beansStructs.getVal(_beanName);
        if (bean_ == null) {
            return;
        }
        StringMapObject forms_ = bean_.getBean().getForms();
        StringMapObject formsMap_ = ((BeanStruct)_mainBean).getBean().getForms();
        forms_.putAllMap(formsMap_);
        gearFw(_conf, _mainBean, _node, _keepField, bean_, _ctx, _stack, _rendStack);
    }

    @Override
    protected void gearFw(Configuration _conf, Struct _mainBean, RendImport _node, boolean _keepField, Struct _bean, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        //
    }

    protected abstract BeanStruct newSimpleBean(String _language, BeanInfo _bean, ContextEl _ctx);


    private static void setStoredForms(BeanStruct _bean, StringMapObject _storedForms) {
        _bean.getBean().setForms(_storedForms);
    }

    @Override
    public Argument getCommonArgument(RendSettableFieldOperation _rend, Argument _previous, Configuration _conf, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        ClassField fieldId_ = _rend.getClassField();
        Struct default_ = _previous.getStruct();
        ResultErrorStd res_ = getOtherResult(_context, fieldId_, default_);
        return new Argument(res_.getResult());
    }

    @Override
    public Argument getCommonSetting(RendSettableFieldOperation _rend, Argument _previous, Configuration _conf, Argument _right, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        ClassField fieldId_ = _rend.getClassField();
        setOtherResult(_context, fieldId_, _previous.getStruct(), _right.getStruct());
        return _right;
    }

    @Override
    public String processString(Argument _arg, ContextEl _ctx, StackCall _stack) {
        Struct struct_ = _arg.getStruct();
        if (struct_ instanceof DisplayableStruct) {
            return ((DisplayableStruct)struct_).getDisplayedString(_ctx).getInstance();
        }
        return struct_.getClassName(_ctx);
    }

    @Override
    public Argument getCommonFctArgument(RendStdFctOperation _rend, Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        int off_ = StringUtil.getFirstPrintableCharIndex(_rend.getMethodName());
        _rend.setRelativeOffsetPossibleLastPage(_rend.getIndexInEl()+off_, _rendStack);
        CustList<Argument> firstArgs_ = RendDynOperationNode.getArguments(_all,_rend);
        ClassMethodId classMethodId_ = _rend.getClassMethodId();
        ResultErrorStd res_ = LgNames.invokeMethod(_context, classMethodId_, _previous.getStruct(), null, _stack, Argument.toArgArray(firstArgs_));
        return new Argument(res_.getResult());
    }
    @Override
    public Message validate(Configuration _conf, NodeContainer _cont, String _validatorId, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        Validator validator_ = validators.getVal(_validatorId);
        if (validator_ == null) {
            return null;
        }
        StringList v_ = _cont.getValue();
        NodeInformations nInfos_ = _cont.getNodeInformation();
        String className_ = nInfos_.getInputClass();
        ResultErrorStd resError_ = getStructToBeValidated(v_, className_, _conf, _ctx, _stack);
        if (_ctx.callsOrException(_stack)) {
            return null;
        }
        Struct obj_ = resError_.getResult();
        return validator_.validate(obj_);
    }
    protected StringMap<String> getIterables() {
        return iterables;
    }
    public Validator buildValidator(Element _element) {
        return null;
    }

    ContextEl setupNative(AnalyzedPageEl _page) {
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        Options options_ = new Options();
        int tabWidth_ = 4;
        ContextEl contextEl_ = ContextFactory.simpleBuild(-1, options_, this, tabWidth_);
        ContextFactory.validateStds(a_, kw_, this, new CustList<CommentDelimiters>(), options_, contextEl_.getClasses().getCommon(), new DefaultConstantsCalculator(getNbAlias()), DefaultFileBuilder.newInstance(getContent()), getContent(),tabWidth_, _page, new NativeTestFieldFilter());
        return contextEl_;
    }

    @Override
    public ContextEl newContext(int _tabWidth, int _stack, Coverage _coverage) {
        return new NativeContextEl(new CommonExecutionInfos(_tabWidth,_stack,this,new Classes(new ClassesCommon()),_coverage,new DefaultLockingClass(),new DefaultInitializer()));
    }

    public void rendRefresh(Navigation _navigation, ContextEl _context) {
        for (Bean b: beans.values()) {
            b.setLanguage(_navigation.getLanguage());
        }
        _navigation.getSession().setCurrentLanguage(_navigation.getLanguage());
        _navigation.processRendAnchorRequest(_navigation.getCurrentUrl(), this, _context, null, null);
    }

    public StringMap<Bean> getBeans() {
        return beans;
    }

    public StringMap<Validator> getValidators() {
        return validators;
    }

    public void setValidators(StringMap<Validator> _validators) {
        validators = _validators;
    }

    StringMap<Validator> loadValidator(Element _elt) {
        StringMap<Validator> validators_ = new StringMap<Validator>();
        int i_ = 0;
        String key_ = "";
        for (Element c: _elt.getChildElements()) {
            if (i_ % 2 == 0) {
                key_ = c.getAttribute("value");
            } else {
                validators_.put(key_, buildValidator(c));
            }
            i_++;
        }
        return validators_;
    }

    public String processAfterInvoke(Configuration _conf, String _dest, String _beanName, Struct _bean, String _currentUrl, String _language, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        ImportingPage ip_ = new ImportingPage();
        _rendStack.addPage(ip_);
        StringMapObject stringMapObject_ = beansStructs.getVal(_beanName).getBean().getForms();
        _rendStack.setCurrentUrl(_dest);
        RendDocumentBlock rendDocumentBlock_ = _conf.getRenders().getVal(_dest);
        String currentBeanName_ = rendDocumentBlock_.getBeanName();
        BeanStruct bean_ = getBeanOrNull(currentBeanName_);
        setStoredForms(bean_, stringMapObject_);
        _rendStack.clearPages();
        return RendBlock.getRes(rendDocumentBlock_,_conf, this, _ctx, _stack, _rendStack);
    }
    private BeanStruct getBeanOrNull(String _currentBeanName) {
        return getBean(_currentBeanName);
    }

    BeanStruct getBean(String _beanName) {
        return beansStructs.getVal(_beanName);
    }
    public abstract BeanStruct getOtherResultBean(ContextEl _cont,
                                                      ConstructorId _method);

    public abstract ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance);

    public abstract void setOtherResult(ContextEl _cont, ClassField _classField, Struct _instance, Struct _value);

}
