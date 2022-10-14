package code.bean.nat;

import code.bean.nat.analyze.NatAnalyzingDoc;
import code.bean.nat.analyze.blocks.AnaRendBlockHelp;
import code.bean.nat.analyze.blocks.NatAnaRendDocumentBlock;
import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.exec.NatArgumentsPair;
import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatNodeContainer;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.blocks.NatDocumentBlock;
import code.bean.nat.exec.blocks.NatRendImport;
import code.bean.nat.exec.blocks.RendBlockHelp;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.bean.nat.exec.variables.VariableWrapperNat;
import code.bean.nat.fwd.AbstractNatBlockBuilder;
import code.bean.nat.fwd.NatRendForwardInfos;
import code.bean.validator.Validator;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.formathtml.HtmlPage;
import code.formathtml.Navigation;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.Message;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.NodeContainer;
import code.formathtml.util.NodeInformations;
import code.formathtml.util.WithPageInfos;
import code.maths.Rate;
import code.sml.Document;
import code.sml.Element;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public abstract class BeanNatCommonLgNames implements WithPageInfos {
    public static final String TYPE_LIST = "ls";
    public static final String TYPE_MAP = "lse";
    public static final String TYPE_DISPLAYABLE = "code.util.ints.Displayable";
    public static final String TYPE_VALIDATOR = "code.bean.validator.Validator";
    public static final String TYPE_BEAN = "code.bean.Bean";

    public static final String TYPE_ENTRY = "$custentry";
    public static final String TYPE_ITERATOR = "code.util.SimpleItr";
    public static final String TYPE_COUNTABLE = "code.util.ints.Countable";
    public static final String TYPE_ENTRIES = "$custentries";

    public static final String OBJECT = "java.lang.Object";
    public static final String VOID = "$void";
    public static final String PRIM_BOOLEAN = "$boolean";
    public static final String PRIM_BYTE = "$byte";
    public static final String PRIM_INTEGER = "$int";
    public static final String PRIM_LONG = "$long";
    public static final String STRING = "java.lang.String";
    public static final String CST_NULL_STRING = "";
    public static final String TYPE_RATE = "r";
    public static final String TYPE_LG_INT = "li";
    public static final String IS_EMPTY = "isEmpty";
    protected static final char BEGIN_ARGS = '(';

    private static final String REF_TAG = "#";

    private final StringMap<String> iterables = new StringMap<String>();
    private final StringMap<Validator> validators = new StringMap<Validator>();
    private final StringMap<Struct> beansStruct = new StringMap<Struct>();

    private final StringMap<NatDocumentBlock> renders = new StringMap<NatDocumentBlock>();
    private final StringMap<SpecialNatClass> stds = new StringMap<SpecialNatClass>();

    private StringMap<StringMap<String>> navigation = new StringMap<StringMap<String>>();
    private final NatAnalyzedCode natCode = NatAnalyzedCode.setInnerAnalyzing();

    private NatHtmlPage natPage;
    protected BeanNatCommonLgNames() {
    }

    public static IntStruct sum(Struct[] _args) {
        int s_ = 0;
        for (Struct s: _args) {
            s_ += NumParsers.convertToNumber(s).intStruct();
        }
        return new IntStruct(s_);
    }

    public static String safe(String _ret, String _def, int _nb) {
        String ret_ = StringUtil.nullToEmpty(_ret);
        String res_;
        if (ret_.isEmpty()) {
            res_ = StringUtil.nullToEmpty(_def);
        } else {
            res_ = StringUtil.concatNbs(ret_, _nb);
        }
        return res_;
    }

    public void execute(boolean _form, Element _elt, Navigation _navigation) {
        if (_form) {
            processRendFormRequest(_navigation, _elt);
        } else {
            processRendAnchorRequest(_elt, _navigation);
        }
    }

    public String getRes(NatDocumentBlock _rend, Configuration _conf, NatRendStackCall _rendStackCall) {
        _rendStackCall.getFormParts().initFormsSpec();
        String beanName_ = _rend.getBeanName();
        Struct bean_ = beansStruct.getVal(beanName_);
        _rendStackCall.setMainBean(bean_);
        NatRendImport.beforeDisp(bean_);
        return RendBlockHelp.res(_rend, _conf, _rendStackCall, beanName_, bean_);
    }

    public void build() {
        buildBeans();
        buildOther();
    }

    public abstract void buildOther();

    public void preInitBeans(Configuration _conf) {
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            beansStruct.addEntry(e.getKey(), NullStruct.NULL_VALUE);
        }
    }

    @Override
    public HtmlPage getPage() {
        return getNatPage();
    }


    public NatHtmlPage getNatPage() {
        return natPage;
    }

    public void setNatPage(NatHtmlPage _nat) {
        natPage = _nat;
    }

    public abstract void initBeans(Configuration _conf, String _language);
//    public void initBeans(Configuration _conf, String _language) {
//        int index_ = 0;
//        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
//            beansStruct.setValue(index_, newSimpleBean(_language, e.getValue()));
//            index_++;
//        }
//    }

    public static Struct convert(NatNodeContainer _container) {
        String className_ = _container.getNodeInformation().getInputClass();
        StringList values_ = _container.getValue();
        return getStructToBeValidated(values_, className_);
    }

    public static Struct getStructToBeValidated(StringList _values, String _className) {
        if (StringUtil.quickEq(_className,TYPE_RATE)) {
            String value_ = BeanLgNames.oneElt(_values);
            return new RateStruct(RateStruct.convertToRate(str(value_)),TYPE_RATE);
        }
        if (StringUtil.quickEq(_className, STRING)) {
            return BeanLgNames.wrapStd(_values);
        }
        return getStructToBeValidatedPrim(_values, _className);
    }
    private static Struct str(String _value) {
        if (!Rate.isValid(_value)) {
            return NullStruct.NULL_VALUE;
        }
        return new RateStruct(new Rate(_value),TYPE_RATE);
    }

    protected Struct getBeanOrNull(String _currentBeanName) {
        return getBean(_currentBeanName);
    }

    private Struct getBean(String _beanName) {
        return beansStruct.getVal(_beanName);
    }

    public Navigation nav(StringList _languages, String _lg,AbstractNativeInit _init, StringMap<Document> _built, StringMap<String> _other, StringMap<String> _otherMessage,  String _rel) {
        Navigation nav_ = new Navigation();
        nav_.setSession(new Configuration());
        nav_.setLanguage(_lg);
        nav_.setLanguages(_languages);
        NativeConfigurationLoader nat_ = new NativeConfigurationLoader(this, _init);
        Configuration session_ = new Configuration();
        NatDualConfigurationContext d_ = nat_.getDualConfigurationContext(session_);
        nat_.getForwards();
        d_.init(session_);
        nav_.setSession(session_);
        StringMap<String> files_ = NatDualConfigurationContext.files(nav_,d_,_other,_otherMessage,_rel);
        String realFilePath_ = session_.getFirstUrl();
        StringMap<Document> docs_ = NatDualConfigurationContext.docs(_built,_rel);
        session_.setFirstUrl(realFilePath_);
        nav_.setFiles(files_);
        setupAll(docs_,nav_, nav_.getSession(), blockBuilder(), d_);
        return nav_;
    }
    protected abstract AbstractNatBlockBuilder blockBuilder();
    public void processRendFormRequest(Navigation _nav, Element _elt) {
        NatRendStackCall st_ = new NatRendStackCall();
        st_.clearPages();
        st_.setDocument(_nav.getDocument());
        NatImportingPage ip_ = new NatImportingPage();
        st_.addPage(ip_);
        long lg_ = natPage.getUrl();
        Document doc_ = _nav.getDocument();
        //retrieving form that is submitted
        natPage.setForm(true);

        //As soon as the form is retrieved, then process on it and exit from the loop

        StringMap<Message> map_ = validateAll(natPage);
        StringMap<String> errors_ = new StringMap<String>();
        StringMap<StringList> errorsArgs_ = new StringMap<StringList>();
        _nav.feedErr(map_, errors_, errorsArgs_);
        //begin deleting previous errors
        _nav.delPrevious(doc_, _elt);
        //end deleting previous errors
        if (!errors_.isEmpty()) {
            _nav.processRendFormErrors(_elt, lg_, errors_, errorsArgs_, getPage());
            st_.clearPages();
            return;
        }
        //Setting values for bean
        updateRendBean(natPage);
        st_.clearPages();

        //invoke application
        processRendAnchorRequest(_elt, _nav, st_);
    }

    public StringMap<Message> validateAll(NatHtmlPage _htmlPage) {
        LongMap<LongTreeMap<NatNodeContainer>> containersMap_;
        containersMap_ = _htmlPage.getContainers();
        long lg_ = _htmlPage.getUrl();
        StringMap<Message> map_ = new StringMap<Message>();
        for (EntryCust<Long, NatNodeContainer> e: containersMap_.getVal(lg_).entryList()) {
            NodeContainer nCont_ = e.getValue();
            NodeInformations nInfos_ = nCont_.getNodeInformation();
            String valId_ = nInfos_.getValidator();
            String id_ = nInfos_.getId();
            Message messageTr_ = validate(nCont_,valId_);
            if (messageTr_ != null) {
                map_.put(id_, messageTr_);
            }
        }
        return map_;
    }

    public static void updateRendBean(NatHtmlPage _htmlPage) {
        LongMap<LongTreeMap<NatNodeContainer>> containersMap_;
        containersMap_ = _htmlPage.getContainers();
        long lg_ = _htmlPage.getUrl();
        LongTreeMap< NatNodeContainer> containers_ = containersMap_.getVal(lg_);
        for (EntryCust<Long, NatNodeContainer> e: containers_.entryList()) {
            NatNodeContainer nCont_ = e.getValue();
            if (!nCont_.isEnabled()) {
                continue;
            }
            Struct res_ = convert(nCont_);
            setRendObject(e.getValue(), res_);
        }
    }


    public static void setRendObject(NatNodeContainer _nodeContainer,
                                     Struct _attribute) {
        Struct obj_ = _nodeContainer.getUpdated();
        NatCaller wr_ = _nodeContainer.getOpsWrite();
        wr_.re(obj_,new Struct[]{_attribute});
    }
    public static Struct redirect(NatHtmlPage _htmlPage, Struct _bean, NatRendStackCall _rendStack){
        Struct ret_;
        if (_htmlPage.isForm()) {
            int url_ = (int)_htmlPage.getUrl();
            StringList varNames_ = _htmlPage.getFormsVars().get(url_);
            CustList<NatExecOperationNode> exps_ = _htmlPage.getCallsFormExps().get(url_);
            StringList args_ = _htmlPage.getFormsArgs().get(url_);
            ret_ = redir(new Argument(_bean), varNames_, exps_, args_, _rendStack);
        } else {
            int url_ = (int)_htmlPage.getUrl();
            StringList varNames_ = _htmlPage.getAnchorsVars().get(url_);
            CustList<NatExecOperationNode> exps_ = _htmlPage.getCallsExps().get(url_);
            StringList args_ = _htmlPage.getAnchorsArgs().get(url_);
            ret_= redir(new Argument(_bean), varNames_, exps_, args_, _rendStack);
        }
        return ret_;
    }

    public static Struct redir(Argument _bean, StringList _varNames, CustList<NatExecOperationNode> _exps, StringList _args, NatRendStackCall _rendStackCall) {
        NatImportingPage ip_ = _rendStackCall.getLastPage();
        int s_ = _varNames.size();
        for (int i = 0; i< s_; i++) {
            ip_.putValueVar(_varNames.get(i), new VariableWrapperNat(new LongStruct(NumberUtil.parseLongZero(_args.get(i)))));
        }
        Argument globalArgument_ = _rendStackCall.getLastPage().getGlobalArgument();
        setGlobalArgumentStruct(_bean.getStruct(), _rendStackCall);
        Argument argument_ = Argument.getNullableValue(getAllArgs(_exps, _rendStackCall).lastValue().getArgument());
        setGlobalArgumentStruct(globalArgument_.getStruct(), _rendStackCall);
        for (String n: _varNames) {
            ip_.removeRefVar(n);
        }
        return argument_.getStruct();
    }

    public Message validate(NodeContainer _cont, String _validatorId) {
        Validator validator_ = validators.getVal(_validatorId);
        if (validator_ == null) {
            return null;
        }
        StringList v_ = _cont.getValue();
        NodeInformations nInfos_ = _cont.getNodeInformation();
        String className_ = nInfos_.getInputClass();
        Struct resError_ = getStructToBeValidated(v_, className_);
        return validator_.validate(resError_);
    }

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
    public static void setGlobalArgumentStruct(Struct _obj, NatRendStackCall _rendStackCall) {
        _rendStackCall.getLastPage().setGlobalArgumentStruct(_obj);
    }

    public void buildBeans() {
        CustList<StandardField> fields_;
        fields_ = new CustList<StandardField>();
        SpecialNatClass std_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        std_ = new SpecialNatClass(TYPE_BEAN, fields_, methods_, OBJECT);
        method_ = new SpecNatMethod(IS_EMPTY, PRIM_BOOLEAN, false, MethodModifier.ABSTRACT, new NatStringIsEmpty());
        methods_.add(method_);
        stds.addEntry(TYPE_BEAN, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_LIST, fields_, methods_, OBJECT);
        cl_.getDirectInterfaces().add(TYPE_COUNTABLE);
        method_ = new SpecNatMethod(IS_EMPTY, PRIM_BOOLEAN, false, MethodModifier.ABSTRACT, new NatArrayIsEmpty());
        methods_.add(method_);
        getIterables().put(TYPE_LIST, OBJECT);
        stds.addEntry(TYPE_LIST, cl_);
        methods_ = new CustList<SpecNatMethod>();
        cl_ = new SpecialNatClass(TYPE_MAP, fields_, methods_, OBJECT);
        method_ = new SpecNatMethod(IS_EMPTY, PRIM_BOOLEAN, false, MethodModifier.ABSTRACT, new NatArrayIsEmpty());
        methods_.add(method_);
        cl_.getDirectInterfaces().add(TYPE_COUNTABLE);
        cl_.getDirectInterfaces().add(TYPE_ENTRIES);
        getIterables().put(TYPE_MAP, OBJECT);
        stds.addEntry(TYPE_MAP, cl_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_ITERATOR, fields_, methods_, OBJECT);
        stds.addEntry(TYPE_ITERATOR, std_);
        methods_ = new CustList<SpecNatMethod>();
        cl_ = new SpecialNatClass(TYPE_VALIDATOR, fields_, methods_, OBJECT);
        stds.addEntry(TYPE_VALIDATOR, cl_);
    }


    public StringMap<SpecialNatClass> getStds() {
        return stds;
    }
    public void initializeRendSessionDoc(Navigation _nav) {
        NatRendStackCall rendStackCall_ = new NatRendStackCall();
        initializeRendSessionDoc(_nav, rendStackCall_);
    }
    public void initializeRendSessionDoc(Navigation _nav, NatRendStackCall _rendStackCall) {
        _rendStackCall.init();
        Configuration session_ = _nav.getSession();
        String lg_ = _nav.getLanguage();
        initBeans(session_,lg_);
        String currentUrl_ = session_.getFirstUrl();
        Struct bean_ = getBeanOrNull(_nav.getCurrentBeanName());
        proc(_nav, _rendStackCall, currentUrl_, bean_, _nav.getCurrentBeanName());
    }

    public void processRendAnchorRequest(Element _ancElt, Navigation _nav) {
        NatRendStackCall rendStackCall_ = new NatRendStackCall();
        processRendAnchorRequest(_ancElt, _nav, rendStackCall_);
    }
    public void processRendAnchorRequest(Element _ancElt, Navigation _nav, NatRendStackCall _rendStack) {
        if (_ancElt == null) {
            return;
        }
        Configuration session_ = _nav.getSession();
        String actionCommand_ = _ancElt.getAttribute(StringUtil.concat(session_.getPrefix(),session_.getRendKeyWords().getAttrCommand()));
        _rendStack.clearPages();
        NatImportingPage ip_ = new NatImportingPage();
        _rendStack.addPage(ip_);
        int indexPoint_ = actionCommand_.indexOf(BeanLgNames.DOT);
        String beanName_ = actionCommand_
                .substring(actionCommand_.indexOf(BeanLgNames.CALL_METHOD) + 1, indexPoint_);
        Struct bean_ = getBeanOrNull(beanName_);
        setGlobalArgumentStruct(bean_, _rendStack);
        Struct return_ = redirect(natPage,bean_, _rendStack);
        String urlDest_ = getString(return_, _nav.getCurrentUrl(), getNavigation(), actionCommand_
                .substring(actionCommand_.indexOf(BeanLgNames.CALL_METHOD) + 1));
        proc(_nav, _rendStack, urlDest_, bean_, beanName_);
    }

    private void proc(Navigation _nav, NatRendStackCall _rendStack, String _actionCommand, Struct _bean, String _currentBeanName) {
        _rendStack.clearPages();
        Configuration session_ = _nav.getSession();
        String lg_ = _nav.getLanguage();
        InvokedPageOutput res_ = processAfterInvoke(session_, _actionCommand,_nav.getCurrentUrl(), _currentBeanName, form(_bean), lg_, _rendStack);
        _nav.setCurrentBeanName(_rendStack.getBeanName());
        _nav.setCurrentUrl(res_.getDest());
        _nav.setupText(res_.getRes(), _rendStack.getDocument());
        setNatPage(_rendStack.getHtmlPage());
    }

    static StringMapObjectBase form(Struct _bean) {
        if (_bean instanceof BeanStruct) {
            return ((BeanStruct)_bean).getForms();
        }
        return new StringMapObjectBase();
    }

    public StringMap<StringMap<String>> getNavigation() {
        return navigation;
    }

    public void setNavigation(StringMap<StringMap<String>> _navigation) {
        navigation = _navigation;
    }

    public static String methName(String _action) {
        String methodName_;
        if (_action.indexOf(BEGIN_ARGS) == IndexConstants.INDEX_NOT_FOUND_ELT) {
            methodName_ = _action;
        } else {
            methodName_ = _action.substring(IndexConstants.FIRST_INDEX, _action.indexOf(BEGIN_ARGS));
        }
        return methodName_;
    }

    public static String getString(Struct _ret, String _currentUrl, StringMap<StringMap<String>> _navigation, String _concat) {
        String urlDest_ = _currentUrl;
        if (_ret != NullStruct.NULL_VALUE) {
            StringMap<String> cases_ = _navigation.getVal(_concat);
            String ca_ = BeanNatCommonLgNames.processString(_ret);
            if (cases_ == null) {
                return ca_;
            }
            urlDest_ = cases_.getVal(ca_);
            if (urlDest_ == null) {
                urlDest_ = _currentUrl;
            }
        }
        return urlDest_;
    }

    protected abstract InvokedPageOutput processAfterInvoke(Configuration _conf, String _dest, String _curUrl, String _beanName, StringMapObjectBase _bean, String _language, NatRendStackCall _rendStack);

//    @Override
//    public void beforeDisplaying(Struct _arg, Configuration _cont, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
//        ((BeanStruct)_arg).getBean().beforeDisplaying();
//    }


    public static ArrayStruct getStringArray(StringList _ls) {
        return getArray(_ls,STRING);
    }

    public static ArrayStruct getLongsArray(CustList<Longs> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_LIST));
        int j_ = 0;
        for (Longs s:_ls) {
            arr_.set(j_,getLongArray(s));
            j_++;
        }
        return arr_;
    }

    public static ArrayStruct getLongArray(Longs _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(PRIM_LONG));
        int j_ = 0;
        for (Long s:_ls) {
            arr_.set(j_,new LongStruct(s));
            j_++;
        }
        return arr_;
    }

    public static ArrayStruct getArray(StringList _ls, String _cl) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(_cl));
        int j_ = 0;
        for (String s:_ls) {
            arr_.set(j_,new StringStruct(StringUtil.nullToEmpty(s)));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrInteger(AbsMap<String, Integer> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<String,Integer> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(StringUtil.nullToEmpty(e.getKey())),new IntStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static Struct getStructToBeValidatedPrim(StringList _values, String _className) {
        if (StringUtil.quickEq(_className,PRIM_BOOLEAN)) {
            return BooleanStruct.of(StringUtil.quickEq(_values.first(),BeanLgNames.ON));
        }
        LongInfo val_ = NumParsers.parseLong(_values.first(), 10);
        return new LongStruct(val_.getValue());
    }

    public static void initInstancesPattern(Configuration _conf, StringMap<BeanInfo> _beansInfos) {
        _beansInfos.addAllEntries(_conf.getBeansInfos());
    }

    public StringMap<String> getIterables() {
        return iterables;
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


    public StringMap<Struct> getBeansStruct() {
        return beansStruct;
    }
    public NatDocumentBlock getRender(String _one, String _two) {
        NatDocumentBlock rendDocumentBlock_ = getRenders().getVal(StringUtil.getFirstToken(_one, REF_TAG));
        if (rendDocumentBlock_ != null) {
            return rendDocumentBlock_;
        }
        return getRenders().getVal(_two);
    }
    public String getDest(String _one, String _two) {
        NatDocumentBlock rendDocumentBlock_ = getRenders().getVal(StringUtil.getFirstToken(_one, REF_TAG));
        if (rendDocumentBlock_ != null) {
            return _one;
        }
        return _two;
    }

    public StringMap<NatDocumentBlock> getRenders() {
        return renders;
    }

    public StringMap<Validator> getValidators() {
        return validators;
    }

    public static PairStruct getPairStruct(Struct _arg) {
        if (_arg instanceof PairStruct) {
            return (PairStruct)_arg;
        }
        return new PairStruct(StringUtil.concat(TYPE_ENTRY,StringExpUtil.TEMPLATE_BEGIN,OBJECT,",",OBJECT,StringExpUtil.TEMPLATE_END),NullStruct.NULL_VALUE,NullStruct.NULL_VALUE);
    }

    public static SimpleItrStruct getSimpleItrStruct(Struct _arg) {
        if (_arg instanceof SimpleItrStruct) {
            return (SimpleItrStruct)_arg;
        }
        ArrayStruct array_ = getArray(_arg);
        return new SimpleItrStruct(StringUtil.concat(TYPE_ITERATOR,StringExpUtil.TEMPLATE_BEGIN, OBJECT,StringExpUtil.TEMPLATE_END),array_);
    }

    public static ArrayStruct getArray(Struct _arg) {
        ArrayStruct array_;
        if (_arg instanceof ArrayStruct) {
            array_ = (ArrayStruct) _arg;
        } else {
            String arr_ = StringExpUtil.getPrettyArrayType(OBJECT);
            array_ = new ArrayStruct(0, arr_);
        }
        return array_;
    }
    public void setupAll(StringMap<Document> _docs, Navigation _nav, Configuration _conf, AbstractNatBlockBuilder _builder, NatDualConfigurationContext _context) {
        setNavigation(_context.getNavigation());
        NatAnalyzingDoc analyzingDoc_ = new NatAnalyzingDoc();
        StringMap<BeanInfo> beansInfos_ = _conf.getBeansInfos();
        preInitBeans(_nav.getSession());
        analyzingDoc_.setLanguages(_nav.getLanguages());
        _nav.getSession().setCurrentLanguage(_nav.getLanguage());

        getRenders().clear();
        _nav.getSession().setFiles(_nav.getFiles());
        analyzingDoc_.setup(_nav.getSession(), _context.getProperties(), _context.getMessagesFolder());


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
        NatRendForwardInfos.buildExec(analyzingDoc_, d_, getRenders());
    }

    public static String processString(Argument _arg) {
        Struct struct_ = _arg.getStruct();
        return processString(struct_);
    }

    public static String processString(Struct _struct) {
        if (_struct instanceof NumberStruct) {
            return Long.toString(((NumberStruct) _struct).longStruct());
        }
        if (_struct instanceof StringStruct) {
            return ((StringStruct) _struct).getInstance();
        }
        if (_struct instanceof NatDisplayableStruct) {
            return ((NatDisplayableStruct) _struct).getDisplayedString().getInstance();
        }
        return CST_NULL_STRING;
    }

    public NatAnalyzedCode getNatCode() {
        return natCode;
    }
}
