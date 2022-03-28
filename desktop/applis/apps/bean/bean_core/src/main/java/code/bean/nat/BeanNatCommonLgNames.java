package code.bean.nat;

import code.bean.nat.analyze.blocks.NatAnalyzedCode;
import code.bean.nat.exec.blocks.NatRendImport;
import code.bean.nat.exec.blocks.RendBlockHelp;
import code.bean.nat.exec.variables.VariableWrapperNat;
import code.bean.validator.Validator;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.LongInfo;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.CommonExecutionInfos;
import code.expressionlanguage.exec.CommonExecutionMetricsInfos;
import code.expressionlanguage.exec.DefaultInitializer;
import code.expressionlanguage.exec.DefaultLockingClass;
import code.expressionlanguage.exec.variables.AbstractWrapper;
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
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.Message;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DualConfigurationContext;
import code.formathtml.util.NodeContainer;
import code.formathtml.util.NodeInformations;
import code.maths.Rate;
import code.maths.montecarlo.DefaultGenerator;
import code.util.*;
import code.util.core.IndexConstants;
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
    protected static final char BEGIN_ARGS = '(';
    protected static final char SEP_ARGS = ',';
    protected static final char END_ARGS = ')';

    private static final String SEPARATOR_PATH = "/";
    private static final String IMPLICIT_LANGUAGE = "//";
    private final StringMap<String> iterables = new StringMap<String>();
    private final StringMap<Validator> validators = new StringMap<Validator>();

    private final StringMap<SpecialNatClass> stds = new StringMap<SpecialNatClass>();

    protected BeanNatCommonLgNames() {
        super(new DefaultGenerator());
    }

    public static String getRes(RendDocumentBlock _rend, Configuration _conf, BeanNatCommonLgNames _stds, ContextEl _ctx, RendStackCall _rendStackCall) {
        _rendStackCall.getFormParts().initForms();
        String beanName_ = _rend.getBeanName();
        Struct bean_ = _conf.getBuiltBeans().getVal(beanName_);
        _rendStackCall.setMainBean(bean_);
        NatRendImport.beforeDisp(bean_, _stds);
        return RendBlock.res(_rend, _conf, _stds, _ctx, _rendStackCall, beanName_, bean_);
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
            _conf.getBuiltBeans().addEntry(e.getKey(), NullStruct.NULL_VALUE);
        }
    }

    public void initBeans(Configuration _conf, String _language) {
        int index_ = 0;
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            _conf.getBuiltBeans().setValue(index_, newSimpleBean(_language, e.getValue()));
            index_++;
        }
    }

    @Override
    public ResultErrorStd convert(NodeContainer _container, ContextEl _context, RendStackCall _rendStackCall) {
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

    protected static Struct getBeanOrNull(Configuration _conf,String _currentBeanName) {
        return getBean(_conf,_currentBeanName);
    }

    private static Struct getBean(Configuration _conf,String _beanName) {
        return _conf.getBuiltBeans().getVal(_beanName);
    }
    @Override
    public Message validate(Configuration _conf, NodeContainer _cont, String _validatorId, ContextEl _ctx, RendStackCall _rendStack) {
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

    @Override
    public IdMap<RendDynOperationNode, ArgumentsPair> getAllArgs(CustList<RendDynOperationNode> _nodes, ContextEl _ctx, RendStackCall _rendStackCall) {
        return RenderExpUtil.getAllArgs(_nodes,this,_ctx,_rendStackCall);
    }

    @Override
    public void setGlobalArgumentStruct(Struct _obj, ContextEl _ctx, RendStackCall _rendStackCall) {
        _rendStackCall.getLastPage().getPageEl().setGlobalArgumentStruct(_obj);
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
        params_ = new StringList(STRING);
        method_ = new SpecNatMethod(getContent().getCharSeq().getAliasIsEmpty(), params_, PRIM_BOOLEAN, false, MethodModifier.ABSTRACT, new NatStringIsEmpty());
        methods_.add(method_);
        stds.addEntry(TYPE_BEAN, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        SpecialNatClass cl_;
        cl_ = new SpecialNatClass(TYPE_LIST, fields_, methods_, OBJECT);
        cl_.getDirectInterfaces().add(TYPE_COUNTABLE);
        params_ = new StringList();
        method_ = new SpecNatMethod(getContent().getCharSeq().getAliasIsEmpty(), params_, PRIM_BOOLEAN, false, MethodModifier.ABSTRACT);
        methods_.add(method_);
        getIterables().put(TYPE_LIST, OBJECT);
        stds.addEntry(TYPE_LIST, cl_);
        methods_ = new CustList<SpecNatMethod>();
        cl_ = new SpecialNatClass(TYPE_MAP, fields_, methods_, OBJECT);
        params_ = new StringList();
        method_ = new SpecNatMethod(getContent().getCharSeq().getAliasIsEmpty(), params_, PRIM_BOOLEAN, false, MethodModifier.ABSTRACT);
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
    public String initializeRendSessionDoc(ContextEl _ctx, String _language, Configuration _configuration, Struct _db, RendStackCall _rendStackCall) {
        _rendStackCall.init();
        initBeans(_configuration,_language);
        String currentUrl_ = _configuration.getFirstUrl();
        Struct bean_ = getBeanOrNull(_configuration,getCurrentBeanName());
        _rendStackCall.clearPages();
        String res_ = processAfterInvoke(_configuration, currentUrl_, getCurrentBeanName(), bean_, _language, _ctx, _rendStackCall);
        setCurrentBeanName(_rendStackCall.getBeanName());
        setCurrentUrl(currentUrl_);
        return res_;
    }
    @Override
    public String processRendAnchorRequest(String _anchorRef, String _language, Configuration _configuration, HtmlPage _htmlPage, ContextEl _ctx, RendStackCall _rendStack) {
        if (_anchorRef.contains(CALL_METHOD)) {
            _rendStack.clearPages();
            ImportingPage ip_ = new ImportingPage();
            _rendStack.addPage(ip_);
            int indexPoint_ = _anchorRef.indexOf(DOT);
            String action_ = _anchorRef
                    .substring(indexPoint_ + 1);
            String methodName_ = methName(action_);
            String suffix_ = suff(action_);
            String beanName_ = _anchorRef
                    .substring(_anchorRef.indexOf(CALL_METHOD) + 1, indexPoint_);
            Struct bean_ = getBeanOrNull(_configuration,beanName_);
            ip_.setOffset(indexPoint_+1);
            setGlobalArgumentStruct(bean_,_ctx,_rendStack);
            Struct return_ = redirect(_htmlPage,bean_,_ctx,_rendStack);
            String urlDest_ = getString(return_, getCurrentUrl(), _configuration.getNavigation(), StringUtil.concat(beanName_, DOT, methodName_, suffix_));
            _rendStack.clearPages();
            String res_ = processAfterInvoke(_configuration, urlDest_, beanName_, bean_, _language, _ctx, _rendStack);
            setCurrentBeanName(_rendStack.getBeanName());
            setCurrentUrl(urlDest_);
            return res_;
        }
        if (_anchorRef.isEmpty()) {
            return "";
        }
        Struct bean_ = getBeanOrNull(_configuration,getCurrentBeanName());
        _rendStack.clearPages();
        String res_ = processAfterInvoke(_configuration, _anchorRef, getCurrentBeanName(), bean_, _language, _ctx, _rendStack);
        setCurrentBeanName(_rendStack.getBeanName());
        setCurrentUrl(_anchorRef);
        return res_;
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

    protected abstract String processAfterInvoke(Configuration _conf, String _dest, String _beanName, Struct _bean, String _language, ContextEl _ctx, RendStackCall _rendStack);
    @Override
    public AbstractWrapper newWrapper(LocalVariable _local){
        return new VariableWrapperNat(_local);
    }
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
        return new NativeContextEl(new CommonExecutionInfos(null,new CommonExecutionMetricsInfos(_opt.getTabWidth(),_opt.getStack(),_opt.getSeedGene()),this, _options.getClasses(), _options.getCoverage(),new DefaultLockingClass(),new DefaultInitializer()));
    }

    public Forwards setupNative(NatAnalyzedCode _page, DualConfigurationContext _context) {
        Options options_ = _context.getOptions();
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
