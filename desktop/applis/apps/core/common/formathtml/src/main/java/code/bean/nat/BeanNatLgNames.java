package code.bean.nat;

import code.bean.Bean;
import code.bean.BeanStruct;
import code.bean.RealInstanceStruct;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ClassesCommon;
import code.expressionlanguage.exec.DefaultInitializer;
import code.expressionlanguage.exec.DefaultLockingClass;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.Forwards;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendImport;
import code.formathtml.exec.blocks.RendImportForm;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.opers.RendInvokingOperation;
import code.formathtml.exec.opers.RendSettableFieldOperation;
import code.formathtml.exec.opers.RendStdFctOperation;
import code.formathtml.fwd.RendForwardInfos;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.Message;
import code.bean.validator.Validator;
import code.expressionlanguage.*;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.formathtml.*;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.NodeContainer;
import code.formathtml.util.NodeInformations;
import code.maths.montecarlo.DefaultGenerator;
import code.sml.Document;
import code.sml.Element;
import code.util.*;
import code.util.ints.*;

public abstract class BeanNatLgNames extends BeanLgNames {
    public static final String TYPE_LIST = "ls";
    public static final String TYPE_MAP = "lse";
    public static final String TYPE_DISPLAYABLE = "code.util.ints.Displayable";
    public static final String TYPE_VALIDATOR = "code.bean.validator.Validator";
    public static final String TYPE_BEAN = "code.bean.Bean";

    protected static final String TYPE_ENTRY = "$custentry";
    protected static final String TYPE_ITERATOR = "code.util.SimpleItr";
    protected static final String TYPE_COUNTABLE = "code.util.ints.Countable";
    private static final String TYPE_ENTRIES = "$custentries";
    private StringMapObject storedForms;
    private StringMap<String> iterables = new StringMap<String>();
    private Object dataBase;
    private final StringMap<Bean> beans = new StringMap<Bean>();
    private StringMap<Validator> validators = new StringMap<Validator>();

    public BeanNatLgNames() {
        super(new DefaultGenerator());
    }

    private static Object[] adaptedArgs(StringList _params, Struct... _args) {
        int len_ = _params.size();
        Object[] args_ = new Object[len_];
        for (int i = 0; i < len_; i++) {
            Struct argStruct_ = _args[i];
            if (argStruct_ instanceof IntStruct) {
                args_[i] = NumParsers.convertToNumber(argStruct_).intStruct();
            } else {
                args_[i] = NumParsers.convertToNumber(argStruct_).longStruct();
            }
        }
        return args_;
    }

    public static Object adaptedArg(Struct _args) {
        if (_args instanceof NumberStruct) {
            if (_args instanceof ShortStruct) {
                return ((ShortStruct) _args).shortStruct();
            }
            if (_args instanceof IntStruct) {
                return ((IntStruct) _args).intStruct();
            }
            return ((NumberStruct) _args).longStruct();
        }
        if (_args instanceof StringStruct) {
            return ((StringStruct)_args).getInstance();
        }
        if (_args instanceof BooleanStruct) {
            return BooleanStruct.isTrue(_args);
        }
        if (_args instanceof RealInstanceStruct) {
            return ((RealInstanceStruct) _args).getInstance();
        }
        return null;
    }

    @Override
    public void preInitBeans(Configuration _conf) {
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            _conf.getBuiltBeans().addEntry(e.getKey(), NullStruct.NULL_VALUE);
        }
    }

    @Override
    public void initBeans(Configuration _conf,String _language,Struct _db) {
        int index_ = 0;
        for (EntryCust<String, BeanInfo> e: _conf.getBeansInfos().entryList()) {
            _conf.getBuiltBeans().setValue(index_, newSimpleBean(_language, e.getValue(),_conf));
            index_++;
        }
    }

    private Struct newSimpleBean(String _language, BeanInfo _bean, Configuration _conf) {
        ConstructorId id_ = new ConstructorId(_bean.getResolvedClassName(), new StringList(), false);
        ResultErrorStd res_ = ApplyCoreMethodUtil.newInstance(_conf.getContext(), id_, Argument.toArgArray(new CustList<Argument>()));
        Struct strBean_ = res_.getResult();
        BeanStruct str_ = (BeanStruct) strBean_;
        Bean bean_ = str_.getBean();
        bean_.setDataBase(dataBase);
        bean_.setForms(new StringMapObject());
        bean_.setLanguage(_language);
        bean_.setScope(_bean.getScope());
        return strBean_;
    }

    @Override
    public void forwardDataBase(Struct _bean, Struct _to, Configuration _conf) {
        ((BeanStruct)_to).getBean().setDataBase(((BeanStruct)_bean).getBean().getDataBase());
    }
    public void storeForms(Struct _bean, Configuration _conf) {
        storedForms = ((BeanStruct)_bean).getBean().getForms();
    }

    @Override
    public void setStoredForms(Struct _bean, Configuration _conf) {
        ((BeanStruct)_bean).getBean().setForms(storedForms);
    }

    @Override
    protected void gearFw(Configuration _conf, Struct _mainBean, RendImport _node, boolean _keepField, Struct _bean) {

        StringMapObject forms_ = ((BeanStruct)_bean).getBean().getForms();
        StringMapObject formsMap_ = ((BeanStruct)_mainBean).getBean().getForms();
        if (_keepField) {
            for (RendBlock f_: RendBlock.getDirectChildren(_node)) {
                if (!(f_ instanceof RendImportForm)) {
                    continue;
                }
                String name_ = ((RendImportForm)f_).getName();
                forms_.put(name_,formsMap_.getVal(name_));
            }
        } else {
            //add option for copying forms (default copy)
            forms_.putAllMap(formsMap_);
        }
    }

    @Override
    protected AnalyzedPageEl specificLoad(Configuration _configuration, String _lgCode, Document _document, RendAnalysisMessages _rend, AbstractFileBuilder _fileBuilder) {
        for (Element c: _document.getDocumentElement().getChildElements()) {
            String fieldName_ = c.getAttribute("field");
            if (StringList.quickEq(fieldName_, "validators")) {
                setValidators(loadValidator(c));
            }
        }
        return setupNative(_configuration);
    }

    @Override
    public Argument getCommonArgument(RendSettableFieldOperation _rend, Argument _previous, Configuration _conf) {
        ClassField fieldId_ = _rend.getClassField();
        Struct default_ = _previous.getStruct();
        ContextEl _cont = _conf.getContext();
        ResultErrorStd res_ = getOtherResult(_cont, fieldId_, default_);
        return new Argument(res_.getResult());
    }

    @Override
    public Argument getCommonSetting(RendSettableFieldOperation _rend, Argument _previous, Configuration _conf, Argument _right) {
        ClassField fieldId_ = _rend.getClassField();
        ContextEl _cont = _conf.getContext();
        Object value_ = adaptedArg(_right.getStruct());
        setOtherResult(_cont, fieldId_, _previous.getStruct(), value_);
        return _right;
    }

    @Override
    public Argument getCommonFctArgument(RendStdFctOperation _rend, Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = _rend.getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(_rend.getMethodName());
        _rend.setRelativeOffsetPossibleLastPage(_rend.getIndexInEl()+off_, _conf);
        CustList<Argument> firstArgs_;
        String lastType_ = _rend.getLastType();
        int naturalVararg_ = _rend.getNaturalVararg();
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, chidren_).getArguments();
        firstArgs_ = RendInvokingOperation.listArguments(chidren_, naturalVararg_, lastType_, first_);
        int i_ =0;
        ClassMethodId classMethodId_ = _rend.getClassMethodId();
        for (Argument a: firstArgs_) {
            byte cast_ = ExecClassArgumentMatching.getPrimitiveWrapCast(classMethodId_.getConstraints().getParametersTypes().get(i_), this);
            a.setStruct(NumParsers.convertToInt(cast_, NumParsers.convertToNumber(cast_,a.getStruct())));
            i_++;
        }
        ResultErrorStd res_ = LgNames.invokeMethod(_conf.getContext(), classMethodId_, _previous.getStruct(), null, Argument.toArgArray(firstArgs_));
        return new Argument(res_.getResult());
    }

    private StringMap<Validator> loadValidator(Element _elt) {
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
    @Override
    public Message validate(Configuration _conf, NodeContainer _cont, String _validatorId) {
        Validator validator_ = validators.getVal(_validatorId);
        if (validator_ == null) {
            return null;
        }
        StringList v_ = _cont.getValue();
        NodeInformations nInfos_ = _cont.getNodeInformation();
        String className_ = nInfos_.getInputClass();
        ResultErrorStd resError_ = getStructToBeValidated(v_, className_, _conf);
        if (_conf.getContext().hasException()) {
            return null;
        }
        Struct obj_ = resError_.getResult();
        Object ad_ = adaptedArg(obj_);
        return validator_.validate(ad_);
    }

    public abstract ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance);

    public abstract ResultErrorStd setOtherResult(ContextEl _cont, ClassField _classField, Struct _instance, Object _value);

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
        getIterables().put(TYPE_LIST, getAliasObject());
        getStandards().addEntry(TYPE_LIST, cl_);
        methods_ = new CustList<StandardMethod>();
        cl_ = new StandardClass(TYPE_MAP, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        cl_.getDirectInterfaces().add(TYPE_COUNTABLE);
        cl_.getDirectInterfaces().add(TYPE_ENTRIES);
        getIterables().put(TYPE_MAP, getAliasObject());
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
    public String getStringKey(Configuration _conf, Struct _instance) {
        ContextEl cont_ = _conf.getContext();
        ResultErrorStd res_ = getName(cont_, _instance);
        Struct str_ = res_.getResult();
        return processString(new Argument(str_),_conf);
    }

    public ResultErrorStd getName(ContextEl _cont, Struct _instance) {
        return getOtherName(_cont, _instance);
    }
    public abstract ResultErrorStd getOtherName(ContextEl _cont, Struct _instance);
    @Override
    public void beforeDisplaying(Struct _arg, Configuration _cont) {
        ((BeanStruct)_arg).getBean().beforeDisplaying();
    }

    @Override
    public String getScope(Struct _bean, Configuration _cont) {
        return ((BeanStruct)_bean).getBean().getScope();
    }

    @Override
    public void setScope(Struct _bean, String _scope, Configuration _cont) {
        ((BeanStruct)_bean).getBean().setScope(_scope);
    }

    public void setLanguage(Struct _bean, String _scope,Configuration _cont) {
        ((BeanStruct)_bean).getBean().setLanguage(_scope);
    }


    @Override
    public Argument iteratorMultTable(Struct _arg, Configuration _cont) {
        Object instance_ = ((RealInstanceStruct) _arg).getInstance();
        SimpleIterable db_ = ((SimpleEntries)instance_).entries();
        SimpleItr it_ = db_.simpleIterator();
        return new Argument(newId(it_, StringList.concat(TYPE_ITERATOR,Templates.TEMPLATE_BEGIN, TYPE_ENTRY,Templates.TEMPLATE_END)));
    }

    @Override
    public Argument hasNextPair(Struct _arg, Configuration _conf) {
        Object instance_ = ((RealInstanceStruct) _arg).getInstance();
        SimpleItr it_ = (SimpleItr) instance_;
        return new Argument(BooleanStruct.of(it_.hasNext()));
    }

    @Override
    public Argument nextPair(Struct _arg, Configuration _conf) {
        Object instance_ = ((RealInstanceStruct) _arg).getInstance();
        SimpleEntry resObj_ = (SimpleEntry) ((SimpleItr)instance_).next();
        return new Argument(newId(resObj_, TYPE_ENTRY));
    }

    @Override
    public Argument first(Struct _arg, Configuration _conf) {
        Object instance_ = ((RealInstanceStruct) _arg).getInstance();
        Object resObj_ = ((SimpleEntry)instance_).getSimpleKey();
        return new Argument(wrapStd(resObj_));
    }

    @Override
    public Argument second(Struct _arg, Configuration _conf) {
        Object instance_ = ((RealInstanceStruct) _arg).getInstance();
        Object resObj_ = ((SimpleEntry)instance_).getSimpleValue();
        return new Argument(wrapStd(resObj_));
    }

    @Override
    public Argument iterator(Struct _arg, Configuration _cont) {
        Object instance_ = ((RealInstanceStruct) _arg).getInstance();
        String typeInst_ = _arg.getClassName(_cont.getContext());
        String it_ = getIterables().getVal(typeInst_);
        return new Argument(newId(((SimpleIterable) instance_).simpleIterator(), StringList.concat(TYPE_ITERATOR,Templates.TEMPLATE_BEGIN,it_,Templates.TEMPLATE_END)));
    }

    @Override
    public Argument next(Struct _arg, Configuration _cont) {
        Object instance_ = ((RealInstanceStruct) _arg).getInstance();
        Object resObj_ = ((SimpleItr)instance_).next();
        return new Argument(wrapStd(resObj_));
    }

    @Override
    public Argument hasNext(Struct _arg, Configuration _cont) {
        Object instance_ = ((RealInstanceStruct) _arg).getInstance();
        SimpleItr it_ = (SimpleItr) instance_;
        return new Argument(BooleanStruct.of(it_.hasNext()));
    }

    protected abstract Struct newId(Object _obj, String _className);

    public ReportedMessages setupAll(Navigation _nav, Configuration _conf, StringMap<String> _files, AnalyzedPageEl _page, RendAnalysisMessages _rend) {
        AnalyzingDoc analyzingDoc_ = new AnalyzingDoc();
        _page.setForEachFetch(new NativeForEachFetch(this));
        _nav.initInstancesPattern(_page, analyzingDoc_);
        StringMap<AnaRendDocumentBlock> d_ = _nav.analyzedRenders(_page, this, _rend, analyzingDoc_);
        RendForwardInfos.buildExec(analyzingDoc_, d_, new Forwards(), _conf);
        return _page.getMessages();
    }
    public abstract Struct wrapStd(Object _element);

    @Override
    public String processString(Argument _arg, Configuration _cont) {
        Struct struct_ = _arg.getStruct();
        if (struct_ instanceof DisplayableStruct) {
            return ((DisplayableStruct)struct_).getDisplayedString(_cont.getContext()).getInstance();
        }
        if (struct_ instanceof RealInstanceStruct) {
            Object inst_ = ((RealInstanceStruct) struct_).getInstance();
            if (inst_ instanceof Displayable) {
                return ((Displayable)inst_).display();
            }
        }
        ContextEl context_ = _cont.getContext();
        return struct_.getClassName(context_);
    }

    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
                                         ConstructorId _method, Struct... _args) {
        StringList list_ = _method.getParametersTypes();
        Object[] argsObj_ = adaptedArgs(list_, _args);
        return getOtherResultBean(_cont, _method, argsObj_);
    }

    public abstract ResultErrorStd getOtherResultBean(ContextEl _cont,
                                             ConstructorId _method, Object... _args);

    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        StringList list_ = _method.getConstraints().getParametersTypes();
        Object[] argsObj_ = adaptedArgs(list_, _args);
        Object instance_ = ((RealInstanceStruct)_instance).getInstance();
        if (instance_ instanceof Countable) {
            res_.setResult(BooleanStruct.of(((Countable) instance_).isEmpty()));
            return res_;
        }
        return getOtherResultBean(_cont, _instance, _method, argsObj_);
    }
    public abstract ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance,
                                             ClassMethodId _method, Object... _args);

    protected StringMap<String> getIterables() {
        return iterables;
    }
    public Validator buildValidator(Element _element) {
        return null;
    }

    private AnalyzedPageEl setupNative(Configuration _conf) {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        Options _options = new Options();
        ClassesCommon com_ = new ClassesCommon();
        int tabWidth_ = 4;
        ContextEl contextEl_ = ContextFactory.simpleBuild(-1, lk_, di_, _options, this, tabWidth_, com_);
        AnalyzedPageEl page_ = ContextFactory.validateStds(a_, kw_, this, new CustList<CommentDelimiters>(), _options, com_, new DefaultConstantsCalculator(getNbAlias()), DefaultFileBuilder.newInstance(getContent()), getContent(),tabWidth_);
        _conf.setContext(contextEl_);
        return page_;
    }
    public void setDataBase(Object _dataBase){
        dataBase = _dataBase;
    }

    public void rendRefresh(Navigation _navigation) {
        for (Bean b: beans.values()) {
            b.setLanguage(_navigation.getLanguage());
        }
        _navigation.getSession().setCurrentLanguage(_navigation.getLanguage());
        _navigation.processRendAnchorRequest(_navigation.getCurrentUrl());
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
}
