package code.formathtml.nat;

import code.bean.nat.*;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.exec.blocks.RendBlockHelp;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.analyze.instr.OperationsSequence;
import code.expressionlanguage.analyze.opers.StandardInstancingOperation;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.analyze.instr.Delimiters;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
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
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.fwd.RendForwardInfos;
import code.formathtml.structs.BeanInfo;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DualAnalyzedContext;
import code.formathtml.util.DualConfigurationContext;
import code.formathtml.util.NodeContainer;
import code.util.*;

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

    public ReportedMessages setupAll(Navigation _nav, Configuration _conf, StringMap<String> _files, DualAnalyzedContext _dual) {
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        analyzingDoc_.setContent(this);
        AnalyzedPageEl page_ = _dual.getAnalyzed();
        initInstancesPattern(_nav.getSession(),analyzingDoc_);
        DualConfigurationContext _dual1 = _dual.getContext();
        Configuration _session = _nav.getSession();
        ((BeanLgNames) this).preInitBeans(_session);
        analyzingDoc_.setRendAnalysisMessages(_dual1.getAnalysisMessages());
        analyzingDoc_.setLanguages(_nav.getLanguages());
        _session.setCurrentLanguage(_nav.getLanguage());
        StringMap<AnaRendDocumentBlock> d_ = _session.analyzedRenders(_nav.getFiles(), analyzingDoc_, page_, _dual1);
        RendForwardInfos.buildExec(analyzingDoc_, new CustList<ExecFileBlock>(), d_, _dual.getForwards(), _conf);
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


    private static void setStoredForms(BeanStruct _bean, StringMapObjectSample _storedForms) {
        _bean.setForms(_storedForms);
    }

    @Override
    public String processString(Argument _arg, ContextEl _ctx, RendStackCall _stack) {
        Struct struct_ = _arg.getStruct();
        if (struct_ instanceof DisplayableStruct) {
            return ((DisplayableStruct)struct_).getDisplayedString(_ctx).getInstance();
        }
        return struct_.getClassName(_ctx);
    }

    public Forwards setupNative(NatAnalyzedCode _page, DualConfigurationContext _context) {
        Options options_ = _context.getOptions();
        Forwards forwards_ = new Forwards(this, null, options_);
        CustList<CommentDelimiters> comments_ = options_.getComments();
        CommentsUtil.checkAndUpdateComments(comments_,new CustList<CommentDelimiters>());
        _page.setStandards(forwards_.getGenerator().getContent());
        _page.setStds(this);
        build();
        RendBlockHelp.setupOverrides(_page.getStds());
        return forwards_;
    }

    public String processAfterInvoke(Configuration _conf, String _dest, String _beanName, Struct _bean, String _currentUrl, String _language, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = new ImportingPage();
        _rendStack.addPage(ip_);
        BeanStruct val_ = beansStructs.getVal(_beanName);
        StringMapObjectSample stringMapObject_ = new StringMapObjectSample();
        RendDocumentBlock rendDocumentBlock_ = _conf.getRenders().getVal(_dest);
        String currentBeanName_ = rendDocumentBlock_.getBeanName();
        BeanStruct bean_ = getBeanOrNull(currentBeanName_);
        setStoredForms(bean_, stringMapObject_);
        _rendStack.clearPages();
        String res_ = BeanNatCommonLgNames.getRes(rendDocumentBlock_, _conf, this, _ctx, _rendStack);
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

}
