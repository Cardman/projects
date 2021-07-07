package code.formathtml.nat;

import code.bean.nat.*;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.StandardInstancingOperation;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.analyze.instr.Delimiters;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.*;
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
import code.formathtml.exec.opers.RendSettableFieldOperation;
import code.formathtml.fwd.RendForwardInfos;
import code.formathtml.structs.BeanInfo;
import code.formathtml.util.DualAnalyzedContext;
import code.formathtml.util.DualConfigurationContext;
import code.formathtml.util.NodeContainer;
import code.util.*;
import code.util.core.StringUtil;

public abstract class BeanTestNatLgNames extends BeanNatCommonLgNames {
    public static final String TYPE_LIST = "ls";
    public static final String TYPE_MAP = "lse";
    public static final String TYPE_DISPLAYABLE = "code.util.ints.Displayable";
    public static final String TYPE_VALIDATOR = "code.bean.validator.Validator";
    public static final String TYPE_BEAN = "code.bean.Bean";

    protected static final String TYPE_ENTRY = "$custentry";
    protected static final String TYPE_ITERATOR = "code.util.SimpleItr";
    protected static final String TYPE_COUNTABLE = "code.util.ints.Countable";
    private static final String TYPE_ENTRIES = "$custentries";
    private final StringMap<BeanStruct> beansStructs = new StringMap<BeanStruct>();

    @Override
    public void initBeans(Configuration _conf, String _language, Struct _db, ContextEl _ctx, RendStackCall _rendStack) {
        int index_ = 0;
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            BeanStruct beanStruct_ = newSimpleBean(_language, e.getValue(), _ctx);
            beansStructs.addEntry(e.getKey(),beanStruct_);
            _conf.getBuiltBeans().setValue(index_, beanStruct_);
            index_++;
        }
    }

    @Override
    public Argument iteratorMultTable(Struct _arg, Configuration _cont, ContextEl _ctx, RendStackCall _rendStack) {
        ArrayStruct array_ = ExecArrayFieldOperation.getArray(_arg, _ctx);
        return new Argument(new SimpleItrStruct(StringUtil.concat(TYPE_ITERATOR,StringExpUtil.TEMPLATE_BEGIN, TYPE_ENTRY,StringExpUtil.TEMPLATE_BEGIN, "?,?",StringExpUtil.TEMPLATE_END,StringExpUtil.TEMPLATE_END),array_));
    }

    @Override
    public Argument hasNextPair(Struct _arg, Configuration _conf, ContextEl _ctx, RendStackCall _rendStack) {
        SimpleItrStruct simpleItrStruct_ = getSimpleItrStruct(_arg, _ctx);
        return new Argument(BooleanStruct.of(simpleItrStruct_.hasNext()));
    }

    @Override
    public Argument nextPair(Struct _arg, Configuration _conf, ContextEl _ctx, RendStackCall _rendStack) {
        SimpleItrStruct simpleItrStruct_ = getSimpleItrStruct(_arg, _ctx);
        Struct resObj_ = simpleItrStruct_.next();
        return new Argument(resObj_);
    }

    @Override
    public Argument first(Struct _arg, Configuration _conf, ContextEl _ctx, RendStackCall _rendStack) {
        PairStruct pairStruct_ = getPairStruct(_arg, _ctx);
        Struct resObj_ = pairStruct_.getFirst();
        return new Argument(resObj_);
    }

    @Override
    public Argument second(Struct _arg, Configuration _conf, ContextEl _ctx, RendStackCall _rendStack) {
        PairStruct pairStruct_ = getPairStruct(_arg, _ctx);
        Struct resObj_ = pairStruct_.getSecond();
        return new Argument(resObj_);
    }

    @Override
    public Argument iteratorList(Struct _arg, Configuration _cont, ContextEl _ctx, RendStackCall _rendStack) {
        ArrayStruct array_ = ExecArrayFieldOperation.getArray(_arg, _ctx);
        return new Argument(new SimpleItrStruct(StringUtil.concat(TYPE_ITERATOR, StringExpUtil.TEMPLATE_BEGIN,"?",StringExpUtil.TEMPLATE_END),array_));
    }

    @Override
    public Argument nextList(Struct _arg, Configuration _cont, ContextEl _ctx, RendStackCall _rendStack) {
        SimpleItrStruct simpleItrStruct_ = getSimpleItrStruct(_arg, _ctx);
        Struct resObj_ = simpleItrStruct_.next();
        return new Argument(resObj_);
    }

    @Override
    public Argument hasNext(Struct _arg, Configuration _cont, ContextEl _ctx, RendStackCall _rendStack) {
        SimpleItrStruct simpleItrStruct_ = getSimpleItrStruct(_arg, _ctx);
        return new Argument(BooleanStruct.of(simpleItrStruct_.hasNext()));
    }

    public ReportedMessages setupAll(Navigation _nav, Configuration _conf, StringMap<String> _files, DualAnalyzedContext _dual) {
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        analyzingDoc_.setContent(this);
        analyzingDoc_.setReducingOperations(new NativeReducingOperations());
        analyzingDoc_.setInputBuilder(new NatInputBuilder());
        analyzingDoc_.setConverterCheck(new NativeConverterCheck(getAliasObject()));
        AnalyzedPageEl page_ = _dual.getAnalyzed();
        page_.setForEachFetch(new NativeTestForEachFetch(this));
        initInstancesPattern(_nav.getSession(),analyzingDoc_);
        StringMap<AnaRendDocumentBlock> d_ = _nav.analyzedRenders(page_, this, analyzingDoc_, _dual.getContext());
        RendForwardInfos.buildExec(analyzingDoc_, d_, _dual.getForwards(), _conf);
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
            info_.setResolvedClassName(info_.getClassName());
            _anaDoc.getBeansInfos().addEntry(root_,info_);
        }
    }

    protected abstract BeanStruct newSimpleBean(String _language, BeanInfo _bean, ContextEl _ctx);


    private static void setStoredForms(BeanStruct _bean, StringMapObject _storedForms) {
        _bean.setForms(_storedForms);
    }

    @Override
    public Argument getCommonSetting(RendSettableFieldOperation _rend, Argument _previous, Argument _right, ContextEl _context, RendStackCall _stack) {
        ClassField fieldId_ = _rend.getClassField();
        setOtherResult(_context, fieldId_, _previous.getStruct(), _right.getStruct());
        return _right;
    }

    @Override
    public String processString(Argument _arg, ContextEl _ctx, RendStackCall _stack) {
        Struct struct_ = _arg.getStruct();
        if (struct_ instanceof DisplayableStruct) {
            return ((DisplayableStruct)struct_).getDisplayedString(_ctx).getInstance();
        }
        return struct_.getClassName(_ctx);
    }

    public Forwards setupNative(AnalyzedPageEl _page, DualConfigurationContext _context) {
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        int tabWidth_ = 4;
        Options options_ = _context.getOptions();
        Forwards forwards_ = new Forwards(this, _page.getFileBuilder(), options_);
        _page.setLogErr(forwards_.getGenerator());
        _page.setOptions(options_);
        CustList<CommentDelimiters> comments_ = options_.getComments();
        CommentsUtil.checkAndUpdateComments(comments_,new CustList<CommentDelimiters>());
        _page.setComments(comments_);
        _page.setDefaultAccess(options_.getDefaultAccess());
        _page.setAnalysisMessages(new AnalysisMessages());
        _page.setKeyWords(new KeyWords());
        _page.setStandards(forwards_.getGenerator().getContent());
        _page.setCalculator(forwards_.getConstantsCalculator());
        _page.setFieldFilter(forwards_.getFieldFilter());
        _page.setFileBuilder(forwards_.getFileBuilder());
        _page.setResources(forwards_.getClasses().getResources());
        _page.setStaticFields(forwards_.getClasses().getStaticFields());
        _page.setTabWidth(options_.getTabWidth());
        _page.setGettingErrors(options_.isGettingErrors());
        build();
        ValidatorStandard.setupOverrides(_page);
        return forwards_;
    }

    public String processAfterInvoke(Configuration _conf, String _dest, String _beanName, Struct _bean, String _currentUrl, String _language, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = new ImportingPage();
        _rendStack.addPage(ip_);
        BeanStruct val_ = beansStructs.getVal(_beanName);
        StringMapObject stringMapObject_ = new StringMapObject();
        RendDocumentBlock rendDocumentBlock_ = _conf.getRenders().getVal(_dest);
        String currentBeanName_ = rendDocumentBlock_.getBeanName();
        BeanStruct bean_ = getBeanOrNull(currentBeanName_);
        setStoredForms(bean_, stringMapObject_);
        _rendStack.clearPages();
        String res_ = RendBlock.getRes(rendDocumentBlock_, _conf, this, _ctx, _rendStack, _dest);
        for (EntryCust<Long, LongTreeMap<NodeContainer>> e: _rendStack.getHtmlPage().getContainers().entryList()) {
            for (EntryCust<Long, NodeContainer> f: e.getValue().entryList()) {
                if (f.getValue().getUpdated() == NullStruct.NULL_VALUE){
                    CustList<Struct> struct_ = new CustList<Struct>();
                    struct_.add(bean_);
                    struct_.addAllElts(f.getValue().getStructParam());
                    f.getValue().setStruct(struct_);
                }
            }
        }
        return res_;
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

    @Override
    public AbstractFieldFilter newFieldFilter() {
        return new NativeTestFieldFilter();
    }
}
