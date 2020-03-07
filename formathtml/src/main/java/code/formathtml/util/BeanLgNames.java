package code.formathtml.util;

import code.bean.validator.Message;
import code.bean.validator.Validator;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.KeyValueMemberName;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.NumberInfos;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.Configuration;
import code.formathtml.RendImport;
import code.formathtml.RenderExpUtil;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.structs.RealInstanceStruct;
import code.formathtml.structs.StdStruct;
import code.sml.Element;
import code.util.*;

public abstract class BeanLgNames extends LgNames {

    static final String ATTRIBUTE_CLASS_NAME = "className";
    private static final String ATTRIBUTE_TYPE = "type";
    private static final String CHECKBOX = "checkbox";

    private static final String TEXT = "text";

    private static final String RANGE = "range";

    private static final String RADIO = "radio";

    private static final String NUMBER = "number";
    private static final String TEXT_AREA = "textarea";
    private static final String SELECT_TAG = "select";
    private static final String ATTRIBUTE_MULTIPLE = "multiple";
    private static final String FORMS = "forms";
    private static final String SET_FORMS = "setForms";
    private static final String GET_FORMS = "getForms";
    private static final String LANGUAGE = "language";
    private static final String SET_LANGUAGE = "setLanguage";
    private static final String GET_LANGUAGE = "getLanguage";
    private static final String SCOPE = "scope";
    private static final String SET_SCOPE = "setScope";
    private static final String GET_SCOPE = "getScope";
    private static final String DATA_BASE = "dataBase";
    private static final String SET_DATA_BASE = "setDataBase";
    private static final String GET_DATA_BASE = "getDataBase";
    private static final String BEFORE_DISPLAYING = "beforeDisplaying";

    private static final String ON = "on";
    private final String validator = "code.bean.validator.Validator";

    private final String valueChangedEvent = "code.formathtml.util.ValueChangeEvent";

    private String aliasDataBaseField=DATA_BASE;

    private final String custEntry = "$custentry";
    private final String custEntries = "$custentries";
    private String aliasDisplayable;
    private String custList = "$custlist";
    private String custMap = "$custmap";
    private String aliasSimpleIteratorType = "code.util.SimpleItr";
    private String aliasCountable = "code.util.ints.Countable";

    private String aliasGet;
    private String aliasSize;
    private String aliasForms=FORMS;
    private String aliasSetForms=SET_FORMS;
    private String aliasGetForms=GET_FORMS;
    private String aliasLanguage=LANGUAGE;
    private String aliasSetLanguage=SET_LANGUAGE;
    private String aliasGetLanguage=GET_LANGUAGE;
    private String aliasScope=SCOPE;
    private String aliasSetScope=SET_SCOPE;
    private String aliasGetScope=GET_SCOPE;
    private String aliasSetDataBase=SET_DATA_BASE;
    private String aliasGetDataBase=GET_DATA_BASE;
    private String aliasBeforeDisplaying=BEFORE_DISPLAYING;
    private String aliasBean = "code.bean.Bean";
    private String aliasStringMapObject="code.util.StringMapObject";
    private String aliasMessage="code.bean.Message";
    private String aliasNewMessage="newStandardMessage";
    private String aliasMessageFormat="format";
    private String aliasMessageGetArgs="getArgs";
    private String aliasMessageSetArgs="setArgs";


    private StringMap<String> iterables = new StringMap<String>();

    public static Double parseDouble(String _nb) {
        NumberInfos infos_ = NumParsers.trySplitDouble(_nb);
        if (infos_ == null) {
            return null;
        }
        return NumParsers.parseDouble(infos_);
    }

    public static int parseInt(String _string, int _def) {
        String value_ = _string.trim();
        if (value_.isEmpty()) {
            return _def;
        }
        return Numbers.parseInt(value_);
    }

    public static Long parseLong(String _string) {
        return NumParsers.parseLong(_string, 10);
    }

    public StringMap<String> getIterables() {
        return iterables;
    }

    public abstract void buildIterables(Configuration _context);

    public abstract void beforeDisplaying(Struct _arg,Configuration _cont);
    public abstract String getScope(Struct _bean, Configuration _cont);
    public abstract void setScope(Struct _bean, String _scope,Configuration _cont);
    public abstract void setLanguage(Struct _bean, String _scope,Configuration _cont);
    public abstract String processString(Argument _arg,Configuration _cont);

    public abstract Argument iteratorMultTable(Struct _arg, Configuration _cont);
    public abstract Argument hasNextPair(Struct _arg,Configuration _conf);
    public abstract Argument nextPair(Struct _arg,Configuration _conf);
    public abstract Argument first(Struct _arg,Configuration _conf);
    public abstract Argument second(Struct _arg,Configuration _conf);
    public abstract Argument iterator(Struct _arg,Configuration _cont);
    public abstract Argument hasNext(Struct _arg,Configuration _cont);
    public abstract Argument next(Struct _arg,Configuration _cont);

    public abstract String getStringKey(Configuration _conf, Struct _instance);

    public abstract void preInitBeans(Configuration _conf);
    public abstract void initBeans(Configuration _conf,String _language,Struct _db);

    @Override
    public void buildOther() {
        StringMap<StandardField> fields_;
        StringList params_;
        StandardMethod method_;
        StandardType std_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        std_ = new StandardClass(aliasMessage, fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasNewMessage, params_, aliasMessage, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasNewMessage, params_, aliasMessage, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMessageFormat, params_, getAliasString(), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMessageGetArgs, params_, PrimitiveTypeUtil.getPrettyArrayType(getAliasString()), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(aliasMessageSetArgs, params_, getAliasVoid(), true, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        getStandards().put(aliasMessage, std_);
    }

    @Override
    public StringMap<String> allRefTypes() {
        StringMap<String> types_ = super.allRefTypes();
        types_.addEntry("Message",getAliasMessage());
        return types_;
    }

    @Override
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> methods_ = super.allTableTypeMethodNames();
        methods_.put(getAliasMessage(),
                new CustList<KeyValueMemberName>(new KeyValueMemberName("NewMessage",getAliasNewMessage()),
                        new KeyValueMemberName("MessageFormat",getAliasMessageFormat()),
                        new KeyValueMemberName("MessageGetArgs",getAliasMessageGetArgs()),
                        new KeyValueMemberName("MessageSetArgs",getAliasMessageSetArgs())));
        return methods_;
    }

    public String getInputClass(Element _write, Configuration _conf) {
        String type_ = _write.getAttribute(ATTRIBUTE_TYPE);
        String class_ = _write.getAttribute(StringList.concat(_conf.getPrefix(),ATTRIBUTE_CLASS_NAME));
        if (StringList.quickEq(type_,NUMBER)) {
            class_= _conf.getStandards().getAliasLong();
        }
        if (StringList.quickEq(type_,RANGE)) {
            class_= _conf.getStandards().getAliasLong();
        }
        if (StringList.quickEq(type_,RADIO)) {
            class_= _conf.getStandards().getAliasLong();
        }
        if (StringList.quickEq(type_,TEXT)) {
            class_= _conf.getStandards().getAliasString();
        }
        if (StringList.quickEq(type_,CHECKBOX)) {
            class_= _conf.getStandards().getAliasBoolean();
        }
        if (StringList.quickEq(_write.getTagName(), SELECT_TAG)) {
            if (_write.hasAttribute(ATTRIBUTE_MULTIPLE)) {
                class_ = _conf.getAdvStandards().getCustList();
            } else {
                class_ = _conf.getStandards().getAliasString();
            }
        }
        if (StringList.quickEq(_write.getTagName(), TEXT_AREA)) {
            class_ = _conf.getStandards().getAliasString();
        }
        return class_;
    }
    public ResultErrorStd convert(NodeContainer _container, Configuration _conf) {
        CustList<RendDynOperationNode> ops_ = _container.getOpsConvert();
        if (!ops_.isEmpty()) {
            String varNameConvert_ = _container.getVarNameConvert();
            LocalVariable lv_ = newLocVar(_container,_conf);
            _conf.getLastPage().putLocalVar(varNameConvert_, lv_);
            _conf.getLastPage().setGlobalArgumentStruct(_container.getBean(), _conf);
            Argument res_ = RenderExpUtil.calculateReuse(ops_, _conf);
            ResultErrorStd out_ = new ResultErrorStd();
            if (_conf.getContext().hasException()) {
                return out_;
            }
            out_.setResult(res_.getStruct());
            return out_;
        }
        Struct obj_ = _container.getTypedStruct();
        String className_ = _container.getNodeInformation().getInputClass();
        if (obj_ != NullStruct.NULL_VALUE) {
            ContextEl context_ = _conf.getContext();
            className_ = context_.getStandards().getStructClassName(obj_, context_);
        }
        StringList values_ = _container.getValue();
        ResultErrorStd out_ = getStructToBeValidated(values_, className_, _conf.getContext());
        if (out_.getError() != null) {
            String err_ = out_.getError();
            _conf.getContext().setException(new ErrorStruct(_conf,err_));
        }
        return out_;
    }
    protected LocalVariable newLocVar(NodeContainer _container, Configuration _conf) {
        StringList values_ = _container.getValue();
        if (_container.isArrayConverter()) {
            int len_ = values_.size();
            ArrayStruct arr_ = new ArrayStruct(new Struct[len_],PrimitiveTypeUtil.getPrettyArrayType(getAliasString()));
            for (int i = 0; i < len_; i++) {
                arr_.getInstance()[i] = new StringStruct(values_.get(i));
            }
            return LocalVariable.newLocalVariable(arr_,_conf);
        }
        if (!values_.isEmpty()) {
            return LocalVariable.newLocalVariable(new StringStruct(values_.first()),_conf);
        }
        return LocalVariable.newLocalVariable(NullStruct.NULL_VALUE,getAliasString());
    }
    public ResultErrorStd getStructToBeValidated(StringList _values, String _className, ContextEl _context) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(_className, getAliasString())) {
            if (_values.isEmpty()) {
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(new StringStruct(_values.first()));
            return res_;
        }
        if (PrimitiveTypeUtil.isPrimitiveOrWrapper(_className,_context)) {
            if (_values.isEmpty() || _values.first().trim().isEmpty()) {
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            ClassArgumentMatching cl_ = new ClassArgumentMatching(_className);
            if (PrimitiveTypeUtil.toPrimitive(cl_,this).matchClass(getAliasPrimBoolean())) {
                res_.setResult(new BooleanStruct(StringList.quickEq(_values.first(),ON)));
                return res_;
            }
            if (PrimitiveTypeUtil.toPrimitive(cl_,this).matchClass(getAliasPrimChar())) {
                res_.setResult(new CharStruct(_values.first().trim().charAt(0)));
                return res_;
            }
            int order_ = PrimitiveTypeUtil.getIntOrderClass(cl_, _context);
            if (order_ == 0) {
                Double val_ = parseDouble(_values.first());
                if (val_ == null) {
                    res_.setError(getAliasCastType());
                    return res_;
                }
                res_.setResult(PrimitiveTypeUtil.convertObject(cl_,new DoubleStruct(val_),this));
                return res_;
            }
            Long val_ = parseLong(_values.first());
            if (val_ == null) {
                res_.setError(getAliasCastType());
                return res_;
            }
            res_.setResult(PrimitiveTypeUtil.convertObject(cl_,new LongStruct(val_),this));
            return res_;
        }
        return getOtherStructToBeValidated(_values, _className, _context);
    }

    public void setBeanForms(Configuration _conf, Struct _mainBean,
                             RendImport _node, boolean _keepField, String _beanName) {
        if (_mainBean == null) {
            return;
        }
        Struct bean_ = _conf.getBuiltBeans().getVal(_beanName);
        if (bean_ == null) {
            return;
        }
        gearFw(_conf, _mainBean, _node, _keepField, bean_);
    }

    protected abstract void gearFw(Configuration _conf, Struct _mainBean, RendImport _node, boolean _keepField, Struct _bean);

    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
                                         ClassMethodId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        StringList list_ = _method.getConstraints().getParametersTypes();
        String type_ = _method.getClassName();
        if (StringList.quickEq(type_, getAliasEnums())) {
            return super.getOtherResult(_cont,_instance,_method,_args);
        }
        String name_ = _method.getConstraints().getName();
        if (StringList.quickEq(name_, aliasNewMessage)) {
            if (list_.isEmpty()) {
                res_.setResult(StdStruct.newInstance(Message.newStandardMessage(),aliasMessage));
            } else {
                String value_ = ((StringStruct) _args[0]).getInstance();
                res_.setResult(StdStruct.newInstance(Message.newStandardMessage(value_),aliasMessage));
            }
            return res_;
        }
        Message instance_ = (Message) ((RealInstanceStruct)_instance).getInstance();
        if (StringList.quickEq(name_, aliasMessageFormat)) {
            res_.setResult(wrapStd(instance_.getMessage()));
            return res_;
        }
        if (StringList.quickEq(name_, aliasMessageGetArgs)) {
            StringList resArgs_ = instance_.getArgs();
            String arrStr_ = PrimitiveTypeUtil.getPrettyArrayType(getAliasString());
            int len_ = resArgs_.size();
            ArrayStruct arr_ = new ArrayStruct(new Struct[len_],arrStr_);
            for (int i = 0; i < len_; i++){
                arr_.getInstance()[i] = wrapStd(resArgs_.get(i));
            }
            res_.setResult(arr_);
            return res_;
        }
        Struct[] argsInst_ = ((ArrayStruct) _args[0]).getInstance();
        int len_ = argsInst_.length;
        String[] resArgs_ = new String[len_];
        for (int i = 0; i < len_; i++){
            Struct argInst_ = argsInst_[i];
            if (argInst_ instanceof StringStruct) {
                resArgs_[i] = ((StringStruct)argInst_).getInstance();
            }
        }
        instance_.setArgs(resArgs_);
        res_.setResult(NullStruct.NULL_VALUE);
        return res_;
    }
    private static Struct wrapStd(String _element) {
        if (_element == null) {
            return NullStruct.NULL_VALUE;
        }
        return new StringStruct(_element);
    }
    public abstract void forwardDataBase(Struct _bean, Struct _to, Configuration _conf);
    public abstract void storeForms(Struct _bean, Configuration _conf);

    public abstract void setStoredForms(Struct _bean, Configuration _conf);

    public abstract Message validate(Configuration _conf,NodeContainer _cont, String _validatorId);

    public Validator buildValidator(Element _element) {
        return null;
    }

    public abstract ResultErrorStd getOtherStructToBeValidated(StringList _values, String _className, ContextEl _context);



    public String getCustList() {
        return custList;
    }
    public void setCustList(String _custList) {
        custList = _custList;
    }
    public String getCustMap() {
        return custMap;
    }
    public void setCustMap(String _custMap) {
        custMap = _custMap;
    }
    public String getCustEntries() {
        return custEntries;
    }
    public String getValueChangedEvent() {
        return valueChangedEvent;
    }
    public String getBean() {
        return aliasBean;
    }
    public String getCustEntry() {
        return custEntry;
    }
    public String getValidator() {
        return validator;
    }

    public String getAliasSimpleIteratorType() {
        return aliasSimpleIteratorType;
    }

    public String getAliasCountable() {
        return aliasCountable;
    }

    public String getAliasDisplayable() {
        return aliasDisplayable;
    }
    public void setAliasDisplayable(String _aliasDisplayable) {
        aliasDisplayable = _aliasDisplayable;
    }

    public void setAliasGet(String _aliasGet) {
        aliasGet = _aliasGet;
    }

    public void setAliasSize(String _aliasSize) {
        aliasSize = _aliasSize;
    }

    public String getAliasBean() {
        return aliasBean;
    }

    public void setAliasBean(String _aliasBean) {
        aliasBean = _aliasBean;
    }

    public String getAliasBeforeDisplaying() {
        return aliasBeforeDisplaying;
    }

    public void setAliasBeforeDisplaying(String _aliasBeforeDisplaying) {
        aliasBeforeDisplaying = _aliasBeforeDisplaying;
    }

    public String getAliasDataBaseField() {
        return aliasDataBaseField;
    }

    public void setAliasDataBaseField(String _aliasDataBaseField) {
        aliasDataBaseField = _aliasDataBaseField;
    }

    public String getAliasGetDataBase() {
        return aliasGetDataBase;
    }

    public void setAliasGetDataBase(String _aliasGetDataBase) {
        aliasGetDataBase = _aliasGetDataBase;
    }

    public String getAliasSetDataBase() {
        return aliasSetDataBase;
    }

    public void setAliasSetDataBase(String _aliasSetDataBase) {
        aliasSetDataBase = _aliasSetDataBase;
    }

    public String getAliasForms() {
        return aliasForms;
    }

    public void setAliasForms(String _aliasForms) {
        aliasForms = _aliasForms;
    }

    public String getAliasGetForms() {
        return aliasGetForms;
    }

    public void setAliasGetForms(String _aliasGetForms) {
        aliasGetForms = _aliasGetForms;
    }

    public String getAliasSetForms() {
        return aliasSetForms;
    }

    public void setAliasSetForms(String _aliasSetForms) {
        aliasSetForms = _aliasSetForms;
    }

    public String getAliasLanguage() {
        return aliasLanguage;
    }

    public void setAliasLanguage(String _aliasLanguage) {
        aliasLanguage = _aliasLanguage;
    }
    public String getAliasGetLanguage() {
        return aliasGetLanguage;
    }

    public void setAliasGetLanguage(String _aliasGetLanguage) {
        aliasGetLanguage = _aliasGetLanguage;
    }

    public String getAliasSetLanguage() {
        return aliasSetLanguage;
    }

    public void setAliasSetLanguage(String _aliasSetLanguage) {
        aliasSetLanguage = _aliasSetLanguage;
    }

    public String getAliasScope() {
        return aliasScope;
    }

    public void setAliasScope(String _aliasScope) {
        aliasScope = _aliasScope;
    }

    public String getAliasGetScope() {
        return aliasGetScope;
    }

    public void setAliasGetScope(String _aliasGetScope) {
        aliasGetScope = _aliasGetScope;
    }

    public String getAliasSetScope() {
        return aliasSetScope;
    }

    public void setAliasSetScope(String _aliasSetScope) {
        aliasSetScope = _aliasSetScope;
    }
    public String getAliasStringMapObject() {
        return aliasStringMapObject;
    }

    public void setAliasStringMapObject(String _aliasStringMapObject) {
        aliasStringMapObject = _aliasStringMapObject;
    }

    public String getAliasMessage() {
        return aliasMessage;
    }

    public void setAliasMessage(String _aliasMessage) {
        aliasMessage = _aliasMessage;
    }

    public String getAliasNewMessage() {
        return aliasNewMessage;
    }

    public void setAliasNewMessage(String _aliasNewMessage) {
        aliasNewMessage = _aliasNewMessage;
    }

    public String getAliasMessageFormat() {
        return aliasMessageFormat;
    }

    public void setAliasMessageFormat(String _aliasMessageFormat) {
        aliasMessageFormat = _aliasMessageFormat;
    }

    public String getAliasMessageGetArgs() {
        return aliasMessageGetArgs;
    }

    public void setAliasMessageGetArgs(String _aliasMessageGetArgs) {
        aliasMessageGetArgs = _aliasMessageGetArgs;
    }

    public String getAliasMessageSetArgs() {
        return aliasMessageSetArgs;
    }

    public void setAliasMessageSetArgs(String _aliasMessageSetArgs) {
        aliasMessageSetArgs = _aliasMessageSetArgs;
    }
}
