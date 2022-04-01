package code.bean.nat;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.blocks.NatRendImport;
import code.bean.nat.exec.blocks.RendBlockHelp;
import code.bean.nat.exec.opers.NatRendCalculableOperation;
import code.bean.nat.exec.variables.VariableWrapperNat;
import code.bean.validator.Validator;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.formathtml.HtmlPage;
import code.formathtml.Navigation;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.Message;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.NodeContainer;
import code.formathtml.util.NodeInformations;
import code.maths.Rate;
import code.maths.montecarlo.DefaultGenerator;
import code.sml.Document;
import code.sml.Element;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public abstract class BeanNatCommonLgNames extends BeanLgNames {
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
    protected static final char SEP_ARGS = ',';
    protected static final char END_ARGS = ')';

    private static final String SEPARATOR_PATH = "/";
    private static final String IMPLICIT_LANGUAGE = "//";
    private final StringMap<String> iterables = new StringMap<String>();
    private final StringMap<Validator> validators = new StringMap<Validator>();
    private final StringMap<Struct> beansStruct = new StringMap<Struct>();

    private final StringMap<RendDocumentBlock> renders = new StringMap<RendDocumentBlock>();
    private final StringMap<SpecialNatClass> stds = new StringMap<SpecialNatClass>();

    private StringMap<StringMap<String>> navigation = new StringMap<StringMap<String>>();

    protected BeanNatCommonLgNames() {
        super(new DefaultGenerator());
    }

    public String getRes(RendDocumentBlock _rend, Configuration _conf, NatRendStackCall _rendStackCall) {
        _rendStackCall.getFormParts().initForms();
        String beanName_ = _rend.getBeanName();
        Struct bean_ = beansStruct.getVal(beanName_);
        _rendStackCall.setMainBean(bean_);
        NatRendImport.beforeDisp(bean_, this);
        return RendBlockHelp.res(_rend, _conf, this, _rendStackCall, beanName_, bean_);
    }

    public static String getRealFilePath(String _lg, String _link) {
        return StringUtil.replace(_link, IMPLICIT_LANGUAGE, StringUtil.concat(SEPARATOR_PATH,_lg,SEPARATOR_PATH));
    }

    protected static boolean isPartOfArgument(char _char) {
        if (_char == SEP_ARGS) {
            return false;
        }
        if (_char == BEGIN_ARGS) {
            return false;
        }
        return _char != END_ARGS;
    }

    public abstract void beforeDisplaying(Struct _arg);
    @Override
    public void preInitBeans(Configuration _conf) {
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            beansStruct.addEntry(e.getKey(), NullStruct.NULL_VALUE);
        }
    }

    public void initBeans(Configuration _conf, String _language) {
        int index_ = 0;
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            beansStruct.setValue(index_, newSimpleBean(_language, e.getValue()));
            index_++;
        }
    }

    public ResultErrorStd convert(NodeContainer _container) {
        String className_ = _container.getNodeInformation().getInputClass();
        StringList values_ = _container.getValue();
        return getStructToBeValidated(values_, className_);
    }

    public ResultErrorStd getStructToBeValidated(StringList _values, String _className) {
        if (StringUtil.quickEq(_className,TYPE_RATE)) {
            ResultErrorStd res_ = new ResultErrorStd();
            String value_ = oneElt(_values);
            res_.setResult( new RateStruct(RateStruct.convertToRate(str(value_)),TYPE_RATE));
            return res_;
        }
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(_className, STRING)) {
            res_.setResult(wrapStd(_values));
            return res_;
        }
        return getStructToBeValidatedPrim(_values, _className, res_);
    }
    private Struct str(String _value) {
        if (!Rate.isValid(_value)) {
            return NullStruct.NULL_VALUE;
        }
        return new RateStruct(new Rate(_value),TYPE_RATE);
    }

    protected abstract Struct newSimpleBean(String _language, BeanInfo _bean);

    public Struct getOtherResultLoc(Struct _instance, ClassMethodId _method, Struct[] _args) {
        return getOtherResultBean(_instance, _method, _args).getResult();
    }
    public abstract ResultErrorStd getOtherResultBean(Struct _instance,
                                                      ClassMethodId _method, Struct... _args);

    protected Struct getBeanOrNull(String _currentBeanName) {
        return getBean(_currentBeanName);
    }

    private Struct getBean(String _beanName) {
        return beansStruct.getVal(_beanName);
    }

    public void processRendFormRequest(Navigation _nav, Element _elt) {
        NatRendStackCall st_ = new NatRendStackCall();
        st_.clearPages();
        st_.setDocument(_nav.getDocument());
        HtmlPage htmlPage_ = _nav.getHtmlPage();
        NatImportingPage ip_ = new NatImportingPage();
        st_.addPage(ip_);
        long lg_ = htmlPage_.getUrl();
        Document doc_ = _nav.getDocument();
        //retrieving form that is submitted
        htmlPage_.setForm(true);

        //As soon as the form is retrieved, then process on it and exit from the loop

        StringMap<Message> map_ = validateAll(htmlPage_);
        StringMap<String> errors_ = new StringMap<String>();
        StringMap<StringList> errorsArgs_ = new StringMap<StringList>();
        _nav.feedErr(map_, errors_, errorsArgs_);
        //begin deleting previous errors
        _nav.delPrevious(doc_, _elt);
        //end deleting previous errors
        if (!errors_.isEmpty()) {
            _nav.processRendFormErrors(this, _elt, lg_, errors_, errorsArgs_, st_.getDocument(), st_.getHtmlPage());
            st_.clearPages();
            return;
        }
        //Setting values for bean
        updateRendBean(htmlPage_, st_);
        st_.clearPages();

        //invoke application
        String res_ = processRendAnchorRequest(_elt, _nav, st_);
        _nav.setupText(res_, this, st_.getDocument(), st_.getHtmlPage());
    }

    public StringMap<Message> validateAll(HtmlPage _htmlPage) {
        LongMap<LongTreeMap<NodeContainer>> containersMap_;
        containersMap_ = _htmlPage.getContainers();
        long lg_ = _htmlPage.getUrl();
        StringMap<Message> map_ = new StringMap<Message>();
        for (EntryCust<Long, NodeContainer> e: containersMap_.getVal(lg_).entryList()) {
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

    public void updateRendBean(HtmlPage _htmlPage, NatRendStackCall _rendStackCall) {
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
            ResultErrorStd res_ = convert(nCont_);
            newObj_ = res_.getResult();
            Struct procObj_ = e.getValue().getUpdated();
            setGlobalArgumentStruct(procObj_, _rendStackCall);
            setRendObject(e.getValue(), newObj_, _rendStackCall);
        }
    }


    public void setRendObject(NodeContainer _nodeContainer,
                              Struct _attribute, NatRendStackCall _rendStackCall) {
        Struct obj_ = _nodeContainer.getUpdated();
        String attrName_ = _nodeContainer.getVarName();
        String prev_ = _nodeContainer.getVarPrevName();
        CustList<RendDynOperationNode> wr_ = _nodeContainer.getOpsWrite();
        NatImportingPage ip_ = _rendStackCall.getLastPage();
        LocalVariable lv_ = LocalVariable.newLocalVariable(obj_, _nodeContainer.getUpdatedClass());
        ip_.putValueVar(prev_, new VariableWrapperNat(lv_));
        String wrap_ = ExecInherits.toWrapper(_nodeContainer.getNodeInformation().getInputClass(),this);
        lv_ = LocalVariable.newLocalVariable(_attribute,wrap_);
        ip_.putValueVar(attrName_, new VariableWrapperNat(lv_));
        getAllArgs(wr_, _rendStackCall).lastValue();
        ip_.removeRefVar(prev_);
        ip_.removeRefVar(attrName_);
    }
    public Struct redirect(HtmlPage _htmlPage, Struct _bean, NatRendStackCall _rendStack){
        Struct ret_;
        if (_htmlPage.isForm()) {
            int _url = (int)_htmlPage.getUrl();
            StringList varNames_ = _htmlPage.getFormsVars().get(_url);
            CustList<RendDynOperationNode> exps_ = _htmlPage.getCallsFormExps().get(_url);
            StringList args_ = _htmlPage.getFormsArgs().get(_url);
            ret_ = redir(new Argument(_bean), varNames_, exps_, args_, _rendStack);
        } else {
            int _url = (int)_htmlPage.getUrl();
            StringList varNames_ = _htmlPage.getAnchorsVars().get(_url);
            CustList<RendDynOperationNode> exps_ = _htmlPage.getCallsExps().get(_url);
            StringList args_ = _htmlPage.getAnchorsArgs().get(_url);
            ret_= redir(new Argument(_bean), varNames_, exps_, args_, _rendStack);
        }
        return ret_;
    }

    public Struct redir(Argument _bean, StringList _varNames, CustList<RendDynOperationNode> _exps, StringList _args, NatRendStackCall _rendStackCall) {
        NatImportingPage ip_ = _rendStackCall.getLastPage();
        int s_ = _varNames.size();
        for (int i = 0; i< s_; i++) {
            LocalVariable locVar_ = LocalVariable.newLocalVariable(new LongStruct(NumberUtil.parseLongZero(_args.get(i))), PRIM_LONG);
            ip_.putValueVar(_varNames.get(i), new VariableWrapperNat(locVar_));
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
        ResultErrorStd resError_ = getStructToBeValidated(v_, className_);
        Struct obj_ = resError_.getResult();
        return validator_.validate(obj_);
    }

    public IdMap<RendDynOperationNode, ArgumentsPair> getAllArgs(CustList<RendDynOperationNode> _nodes, NatRendStackCall _rendStackCall) {
        return getAllArgs(_nodes,this, _rendStackCall);
    }


    public static IdMap<RendDynOperationNode,ArgumentsPair> getAllArgs(CustList<RendDynOperationNode> _nodes, BeanLgNames _advStandards, NatRendStackCall _rendStackCall) {
        IdMap<RendDynOperationNode,ArgumentsPair> arguments_;
        arguments_ = new IdMap<RendDynOperationNode,ArgumentsPair>();
        for (RendDynOperationNode o: _nodes) {
            ArgumentsPair a_ = new ArgumentsPair();
            arguments_.addEntry(o, a_);
        }
        int len_ = _nodes.size();
        for (int i = 0; i < len_; i++) {
            RendDynOperationNode o = arguments_.getKey(i);
            NatRendCalculableOperation a_ = (NatRendCalculableOperation)o;
            a_.calculate(arguments_, _advStandards, _rendStackCall);
        }
        return arguments_;
    }
    public void setGlobalArgumentStruct(Struct _obj, NatRendStackCall _rendStackCall) {
        _rendStackCall.getLastPage().setGlobalArgumentStruct(_obj);
    }

    public abstract ResultErrorStd getOtherResult(ClassField _classField, Struct _instance);

    public void buildBeans() {
        CustList<StandardField> fields_;
        fields_ = new CustList<StandardField>();
        SpecialNatClass std_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        StringList params_;
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
        params_ = new StringList();
        method_ = new SpecNatMethod(IS_EMPTY, params_, PRIM_BOOLEAN, false, MethodModifier.ABSTRACT);
        methods_.add(method_);
        getIterables().put(TYPE_LIST, OBJECT);
        stds.addEntry(TYPE_LIST, cl_);
        methods_ = new CustList<SpecNatMethod>();
        cl_ = new SpecialNatClass(TYPE_MAP, fields_, methods_, OBJECT);
        params_ = new StringList();
        method_ = new SpecNatMethod(IS_EMPTY, params_, PRIM_BOOLEAN, false, MethodModifier.ABSTRACT);
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
    public String initializeRendSessionDoc(Navigation _nav) {
        NatRendStackCall rendStackCall_ = new NatRendStackCall();
        String textToBeChanged_ = initializeRendSessionDoc(_nav, rendStackCall_);
        _nav.setupText(textToBeChanged_, this, rendStackCall_.getDocument(), rendStackCall_.getHtmlPage());
        return textToBeChanged_;
    }
    public String initializeRendSessionDoc(Navigation _nav, NatRendStackCall _rendStackCall) {
        _rendStackCall.init();
        Configuration session_ = _nav.getSession();
        String lg_ = _nav.getLanguage();
        initBeans(session_,lg_);
        String currentUrl_ = session_.getFirstUrl();
        Struct bean_ = getBeanOrNull(getCurrentBeanName());
        _rendStackCall.clearPages();
        String res_ = processAfterInvoke(session_, currentUrl_, getCurrentBeanName(), bean_, lg_, _rendStackCall);
        setCurrentBeanName(_rendStackCall.getBeanName());
        setCurrentUrl(currentUrl_);
        return res_;
    }

    public String processRendAnchorRequest(Element _ancElt, Navigation _nav) {
        NatRendStackCall rendStackCall_ = new NatRendStackCall();
        String res_ = processRendAnchorRequest(_ancElt, _nav, rendStackCall_);
        _nav.setupText(res_, this, rendStackCall_.getDocument(), rendStackCall_.getHtmlPage());
        return res_;
    }
    public String processRendAnchorRequest(Element _ancElt, Navigation _nav, NatRendStackCall _rendStack) {
        if (_ancElt == null) {
            return "";
        }
        Configuration session_ = _nav.getSession();
        String lg_ = _nav.getLanguage();
        String actionCommand_ = _ancElt.getAttribute(StringUtil.concat(session_.getPrefix(),session_.getRendKeyWords().getAttrCommand()));
        if (actionCommand_.contains(CALL_METHOD)) {
            _rendStack.clearPages();
            NatImportingPage ip_ = new NatImportingPage();
            _rendStack.addPage(ip_);
            int indexPoint_ = actionCommand_.indexOf(DOT);
            String action_ = actionCommand_
                    .substring(indexPoint_ + 1);
            String methodName_ = methName(action_);
            String suffix_ = suff(action_);
            String beanName_ = actionCommand_
                    .substring(actionCommand_.indexOf(CALL_METHOD) + 1, indexPoint_);
            Struct bean_ = getBeanOrNull(beanName_);
            setGlobalArgumentStruct(bean_, _rendStack);
            Struct return_ = redirect(_nav.getHtmlPage(),bean_, _rendStack);
            String urlDest_ = getString(return_, getCurrentUrl(), getNavigation(), StringUtil.concat(beanName_, DOT, methodName_, suffix_));
            _rendStack.clearPages();
            String res_ = processAfterInvoke(session_, urlDest_, beanName_, bean_, lg_, _rendStack);
            setCurrentBeanName(_rendStack.getBeanName());
            setCurrentUrl(urlDest_);
            return res_;
        }
        Struct bean_ = getBeanOrNull(getCurrentBeanName());
        _rendStack.clearPages();
        String res_ = processAfterInvoke(session_, actionCommand_, getCurrentBeanName(), bean_, lg_, _rendStack);
        setCurrentBeanName(_rendStack.getBeanName());
        setCurrentUrl(actionCommand_);
        return res_;
    }

    public StringMap<StringMap<String>> getNavigation() {
        return navigation;
    }

    public void setNavigation(StringMap<StringMap<String>> _navigation) {
        navigation = _navigation;
    }

    public static String suff(String _action) {
        String suffix_;
        if (_action.indexOf(BEGIN_ARGS) == IndexConstants.INDEX_NOT_FOUND_ELT) {
            suffix_ = EMPTY_STRING;
        } else {
            suffix_ = _action.substring(_action.indexOf(BEGIN_ARGS));
            StringBuilder str_ = getStringBuilder(suffix_);
            suffix_ = str_.toString();
        }
        return suffix_;
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
            String result_ = null;
            StringMap<String> cases_ = _navigation.getVal(_concat);
            if (cases_ != null) {
                String case_ = BeanNatLgNames.processString(new Argument(_ret));
                result_ = cases_.getVal(case_);
            }
            urlDest_ = result_;
            if (urlDest_ == null) {
                urlDest_ = _currentUrl;
            }
        }
        return urlDest_;
    }

    private static StringBuilder getStringBuilder(String _suf) {
        StringBuilder str_ = new StringBuilder();
        for (char c: _suf.toCharArray()) {
            if (isPartOfArgument(c)) {
                continue;
            }
            str_.append(c);
        }
        return str_;
    }

    protected abstract String processAfterInvoke(Configuration _conf, String _dest, String _beanName, Struct _bean, String _language, NatRendStackCall _rendStack);

    public ResultErrorStd getName(Struct _instance) {
        return getOtherName(_instance);
    }
    public abstract ResultErrorStd getOtherName(Struct _instance);
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

    public ResultErrorStd getStructToBeValidatedPrim(StringList _values, String _className, ResultErrorStd _res) {
        if (StringUtil.quickEq(_className,PRIM_BOOLEAN)) {
            _res.setResult(BooleanStruct.of(StringUtil.quickEq(_values.first(),ON)));
            return _res;
        }
        LongInfo val_ = NumParsers.parseLong(_values.first(), 10);
        _res.setResult(new LongStruct(val_.getValue()));
        return _res;
    }

    public static void initInstancesPattern(Configuration _conf, StringMap<BeanInfo> _beansInfos) {
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            BeanInfo info_ = e.getValue();
            info_.setResolvedClassName(info_.getClassName());
            _beansInfos.addEntry(e.getKey(),info_);
        }
    }

    public StringMap<String> getIterables() {
        return iterables;
    }

    @Override
    public ContextEl newContext(Options _opt,Forwards _options) {
        return null;
    }

    public Forwards setupNative(NatAnalyzedCode _page) {
        Options options_ = new Options();
        Forwards forwards_ = new Forwards(this, null, options_);
        _page.setStds(this);
        //

        //        standards_.addEntry(getCoreNames().OBJECT, std_);
        buildBeans();
        buildOther();

        RendBlockHelp.setupOverrides(getStds());
        return forwards_;
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

    public StringMap<RendDocumentBlock> getRenders() {
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

}
