package code.bean.nat;

import code.bean.nat.analyze.*;
import code.bean.nat.analyze.blocks.*;
import code.bean.nat.exec.*;
import code.bean.nat.exec.blocks.*;
import code.bean.nat.exec.opers.*;
import code.bean.nat.fwd.*;
import code.sml.*;
import code.sml.util.TranslationsAppli;
import code.util.*;
import code.util.core.*;

public abstract class BeanNatCommonLgNames implements BeanNatCommonLgNamesInt, AbstractNatImpLgNames {
    public static final String TYPE_LIST = "ls";
    public static final String TYPE_MAP = "lse";
    public static final String TYPE_DISPLAYABLE = "code.util.ints.Displayable";
    public static final String TYPE_VALIDATOR = "code.bean.validator.Validator";
    public static final String TYPE_BEAN = "code.bean.Bean";

//    public static final String TYPE_ENTRY = "$custentry";
    public static final String TYPE_ITERATOR = "code.util.SimpleItr";
    public static final String TYPE_COUNTABLE = "code.util.ints.Countable";
    public static final String TYPE_ENTRIES = "$custentries";

    public static final String OBJECT = "java.lang.Object";
    public static final String VOID = "$void";
    public static final String PRIM_BOOLEAN = "$boolean";
//    public static final String PRIM_BYTE = "$byte";
    public static final String PRIM_INTEGER = "$int";
    public static final String PRIM_LONG = "$long";
    public static final String STRING = "java.lang.String";
    public static final String CST_NULL_STRING = "";
    public static final String TYPE_RATE = "r";
    public static final String TYPE_LG_INT = "li";
    public static final String IS_EMPTY = "isEmpty";
//    protected static final char BEGIN_ARGS = '(';

    private final StringMap<String> iterables = new StringMap<String>();
    private final StringMap<NaSt> beansStruct = new StringMap<NaSt>();

    private final StringMap<NatDocumentBlock> renders = new StringMap<NatDocumentBlock>();
    private final StringMap<SpecialNatClass> stds = new StringMap<SpecialNatClass>();

    private final NatAnalyzedCode natCode = NatAnalyzedCode.setInnerAnalyzing();
    protected BeanNatCommonLgNames() {
    }

    public String getRes(NatDocumentBlock _rend, NatConfigurationCore _conf, NatRendStackCall _rendStackCall,NatImportingPageAbs _pa) {
        String beanName_ = _rend.getBeanName();
        NaSt bean_ = beansStruct.getVal(beanName_);
        _rendStackCall.setMainBean(bean_);
        NatRendImport.beforeDisp(bean_);
        return RendBlockHelp.res(_rend, _conf, _rendStackCall, beanName_, bean_,_pa);
    }

    public void build() {
        buildBeans();
        buildOther();
    }

    public abstract void buildOther();

    public void preInitBeans(NatConfigurationCore _conf) {
        for (EntryCust<String, String> e: _conf.getBeansInfos().entryList()) {
            beansStruct.addEntry(e.getKey(), NaNu.NULL_VALUE);
        }
    }

    public abstract void initBeans(NatConfigurationCore _conf, String _language);
//    public void initBeans(Configuration _conf, String _language) {
//        int index_ = 0;
//        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
//            beansStruct.setValue(index_, newSimpleBean(_language, e.getValue()));
//            index_++;
//        }
//    }

    protected NaSt getBeanOrNull(String _currentBeanName) {
        return getBean(_currentBeanName);
    }

    private NaSt getBean(String _beanName) {
        return beansStruct.getVal(_beanName);
    }

    public NatNavigation nav(StringList _languages, String _lg, AbstractNativeInit _init, StringMap<Document> _built, StringMap<String> _other, StringMap<TranslationsAppli> _otherMessage, String _rel) {
        NatConfigurationCore session_ = new NatConfigurationCore();
        NatNavigation nav_ = new NatNavigation();
        nav_.setSession(session_);
        nav_.setLanguage(_lg);
        nav_.setLanguages(_languages);
        NativeConfigurationLoader nat_ = new NativeConfigurationLoader(this, _init);
        NatDualConfigurationContext d_ = nat_.getDualConfigurationContext(session_);
        nat_.getForwards();
        d_.init(session_);
        nav_.setSession(session_);
        StringMap<String> files_ = NatDualConfigurationContext.files(d_,_other, _rel);
        String realFilePath_ = session_.getFirstUrl();
        StringMap<Document> docs_ = NatDualConfigurationContext.docs(_built,_rel);
        session_.setFirstUrl(realFilePath_);
        nav_.setFiles(files_);
        nav_.getApplis().addAllEntries(_otherMessage);
        setupAll(docs_,nav_, nav_.getSession(), blockBuilder(), d_);
        return nav_;
    }
    protected abstract AbstractNatBlockBuilder blockBuilder();

    public static IdMap<NatExecOperationNode, NatArgumentsPair> getAllArgs(CustList<NatExecOperationNode> _nodes, NatRendStackCall _rendStackCall) {
        IdMap<NatExecOperationNode, NatArgumentsPair> arguments_;
        arguments_ = new IdMap<NatExecOperationNode,NatArgumentsPair>();
        for (NatExecOperationNode o: _nodes) {
            NatArgumentsPair a_ = new NatArgumentsPair();
            arguments_.addEntry(o, a_);
        }
        int len_ = _nodes.size();
        for (int i = 0; i < len_; i++) {
            NatExecOperationNode o = arguments_.getKey(i);
            o.calculate(arguments_, _rendStackCall);
        }
        return arguments_;
    }

    public void buildBeans() {
        CustList<StandardField> fields_;
        fields_ = new CustList<StandardField>();
        SpecialNatClass std_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        std_ = new SpecialNatClass(fields_, methods_, OBJECT);
        method_ = new SpecNatMethod(IS_EMPTY, PRIM_BOOLEAN, new NatStringIsEmpty());
        methods_.add(method_);
        stds.addEntry(TYPE_BEAN, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(fields_, methods_, OBJECT);
        cl_.getDirectInterfaces().add(TYPE_COUNTABLE);
        method_ = new SpecNatMethod(IS_EMPTY, PRIM_BOOLEAN, new NatArrayIsEmpty());
        methods_.add(method_);
        getIterables().put(TYPE_LIST, OBJECT);
        stds.addEntry(TYPE_LIST, cl_);
        methods_ = new CustList<SpecNatMethod>();
        cl_ = new SpecialNatClass(fields_, methods_, OBJECT);
        method_ = new SpecNatMethod(IS_EMPTY, PRIM_BOOLEAN, new NatArrayIsEmpty());
        methods_.add(method_);
        cl_.getDirectInterfaces().add(TYPE_COUNTABLE);
        cl_.getDirectInterfaces().add(TYPE_ENTRIES);
        getIterables().put(TYPE_MAP, OBJECT);
        stds.addEntry(TYPE_MAP, cl_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(fields_, methods_, OBJECT);
        stds.addEntry(TYPE_ITERATOR, std_);
        methods_ = new CustList<SpecNatMethod>();
        cl_ = new SpecialNatClass(fields_, methods_, OBJECT);
        stds.addEntry(TYPE_VALIDATOR, cl_);
    }


    public StringMap<SpecialNatClass> getStds() {
        return stds;
    }
    public void initializeRendSessionDoc(NatNavigation _nav) {
        NatRendStackCall rendStackCall_ = newNatRendStackCall();
        initializeRendSessionDoc(_nav, rendStackCall_);
    }
    public void initializeRendSessionDoc(NatNavigation _nav, NatRendStackCall _rendStackCall) {
        _rendStackCall.init();
        NatConfigurationCore session_ = _nav.getSession();
        String lg_ = _nav.getLanguage();
        initBeans(session_,lg_);
        String currentUrl_ = session_.getFirstUrl();
        NaSt bean_ = getBeanOrNull(_nav.getCurrentBeanName());
        proc(_nav, _rendStackCall, currentUrl_, bean_);
    }
    protected abstract NatRendStackCall newNatRendStackCall();

    protected void proc(NatNavigation _nav, NatRendStackCall _rendStack, String _actionCommand, NaSt _bean) {
        _rendStack.clearPages();
        NatConfigurationCore session_ = _nav.getSession();
        String lg_ = _nav.getLanguage();
        InvokedPageOutput res_ = processAfterInvoke(session_, _actionCommand,_nav.getCurrentUrl(), _bean, lg_, _rendStack);
        _nav.setCurrentBeanName(_rendStack.getBeanName());
        _nav.setCurrentUrl(res_.getDest());
        _nav.setupText(res_.getRes(), _rendStack.getDocument());
    }

//    public static String methName(String _action) {
//        String methodName_;
//        if (_action.indexOf(BEGIN_ARGS) == IndexConstants.INDEX_NOT_FOUND_ELT) {
//            methodName_ = _action;
//        } else {
//            methodName_ = _action.substring(IndexConstants.FIRST_INDEX, _action.indexOf(BEGIN_ARGS));
//        }
//        return methodName_;
//    }

    protected abstract InvokedPageOutput processAfterInvoke(NatConfigurationCore _conf, String _dest, String _curUrl, NaSt _bean, String _language, NatRendStackCall _rendStack);

//    @Override
//    public void beforeDisplaying(Struct _arg, Configuration _cont, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
//        ((BeanStruct)_arg).getBean().beforeDisplaying();
//    }


    public static NatArrayStruct getStringArray(CustList<String> _ls) {
        return getArray(_ls);
    }

    public static NatArrayStruct getLongsArray(CustList<Longs> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (Longs s:_ls) {
            arr_.set(j_,getLongArray(s));
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getLongArray(Longs _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (Long s:_ls) {
            arr_.set(j_,new NaNbSt(s));
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getArray(CustList<String> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (String s:_ls) {
            arr_.set(j_,new NaStSt(StringUtil.nullToEmpty(s)));
            j_++;
        }
        return arr_;
    }
    public static NatArrayStruct getStrInteger(AbsMap<String, Integer> _map) {
        NatArrayStruct arr_ = new NatArrayStruct(_map.size());
        int i_ = 0;
        for (EntryCust<String,Integer> e: _map.entryList()){
            PairStruct p_ = new PairStruct(new NaStSt(StringUtil.nullToEmpty(e.getKey())),new NaNbSt(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }

    public StringMap<String> getIterables() {
        return iterables;
    }

    @Override
    public BeanNatCommonLgNames setBeanForms(NaSt _mainBean, NaSt _called){
        return this;
    }
    public void setupNative(NatAnalyzedCode _page) {
        _page.setStds(this);
        //

        //        standards_.addEntry(getCoreNames().OBJECT, std_);
        build();

        RendBlockHelp.setupOverrides(getStds());
    }
//    public void rendRefresh(Navigation _navigation, ContextEl _context, RendStackCall _rendStack) {
//        for (Bean b: beans.values()) {
//            b.setLanguage(_navigation.getLanguage());
//        }
//        _navigation.getSession().setCurrentLanguage(_navigation.getLanguage());
//        _navigation.processRendAnchorRequest(_navigation.getCurrentUrl(), this, _context, _rendStack);
//    }


    public StringMap<NaSt> getBeansStruct() {
        return beansStruct;
    }

    public StringMap<NatDocumentBlock> getRenders() {
        return renders;
    }

    public static PairStruct getPairStruct(NaSt _arg) {
        if (_arg instanceof PairStruct) {
            return (PairStruct)_arg;
        }
        return new PairStruct(NaNu.NULL_VALUE,NaNu.NULL_VALUE);
    }

    public static SimpleItrStruct getSimpleItrStruct(NaSt _arg) {
        if (_arg instanceof SimpleItrStruct) {
            return (SimpleItrStruct)_arg;
        }
        NatArrayStruct array_ = getArray(_arg);
        return new SimpleItrStruct(array_);
    }

    public static NatArrayStruct getArray(NaSt _arg) {
        NatArrayStruct array_;
        if (_arg instanceof NatArrayStruct) {
            array_ = (NatArrayStruct) _arg;
        } else {
            array_ = new NatArrayStruct(0);
        }
        return array_;
    }
    public void setupAll(StringMap<Document> _docs, NatNavigation _nav, NatConfigurationCore _conf, AbstractNatBlockBuilder _builder, NatDualConfigurationContext _context) {
        NatAnalyzingDoc analyzingDoc_ = new NatAnalyzingDoc();
        StringMap<String> beansInfos_ = _conf.getBeansInfos();
        preInitBeans(_nav.getSession());
        analyzingDoc_.setLanguages(_nav.getLanguages());
        _nav.getSession().setCurrentLanguage(_nav.getLanguage());

        getRenders().clear();
        _nav.getSession().setFiles(_nav.getFiles());
        analyzingDoc_.getApplis().addAllEntries(_nav.getApplis());
        NatConfigurationCore conf_ = _nav.getSession();
        analyzingDoc_.setRendKeyWords(conf_.getRendKeyWords());
        analyzingDoc_.setupCommon(conf_.getNat(), _context.getProperties(), _context.getMessagesFolder());


        natCode.setStds(this);
        StringMap<NatAnaRendDocumentBlock> d_ = new StringMap<NatAnaRendDocumentBlock>();
        for (EntryCust<String, Document> s: _docs.entryList()) {
            String link_ = s.getKey();
            Document document_ = s.getValue();
            NatAnaRendDocumentBlock anaDoc_ = AnaRendBlockHelp.newRendDocumentBlock(analyzingDoc_.getPrefix(), document_, analyzingDoc_.getRendKeyWords(), this, _builder);
            d_.addEntry(link_,anaDoc_);
        }
        for (NatAnaRendDocumentBlock v : d_.values()) {
            AnaRendBlockHelp.buildFctInstructions(v,analyzingDoc_, natCode, beansInfos_);
        }
//        StringMap<AnaRendDocumentBlock> d_ = _nav.analyzedDocs(_docs,page_, this, analyzingDoc_, _dual.getContext());
        NatRendForwardInfos.buildExec(analyzingDoc_, d_, getRenders(),_builder);
    }

    public static String processString(NaSt _struct) {
//        if (_struct instanceof NaNbSt) {
//            return Long.toString(((NaNbSt) _struct).longStruct());
//        }
//        if (_struct instanceof NaStSt) {
//            return ((NaStSt) _struct).getInstance();
//        }
        if (_struct instanceof NatDisplayableStruct) {
            return ((NatDisplayableStruct) _struct).getDisplayedString().getInstance();
        }
        return CST_NULL_STRING;
    }

    public NatAnalyzedCode getNatCode() {
        return natCode;
    }
}
