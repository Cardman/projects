package code.formathtml.util;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.bean.validator.Message;
import code.bean.validator.Validator;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Templates;
import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.ByteStruct;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.DoubleStruct;
import code.expressionlanguage.opers.util.FloatStruct;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.LongStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.ShortStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.StringStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.sml.Element;
import code.util.CustList;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.StringMapObject;
import code.util.ints.MathFactory;
import code.util.ints.SimpleEntries;
import code.util.ints.SimpleEntry;
import code.util.ints.SimpleIterable;
import code.util.ints.SimpleList;
import code.util.pagination.SelectedBoolean;

public class BeanLgNames extends LgNames {

    private static final String VALIDATE = "validate";
    private static final String GET_INDEXES = "getIndexes";
    private static final String GET_OLD_VALUE = "getOldValue";
    private static final String GET_NEW_VALUE = "getNewValue";
    private static final String GET_VALUE = "getValue";
    private static final String GET_KEY = "getKey";
    private static final String ENTRIES = "entries";
    private static final String SET_FORMS = "setForms";
    private static final String GET_FORMS = "getForms";
    private static final String SET_LANGUAGE = "setLanguage";
    private static final String GET_LANGUAGE = "getLanguage";
    private static final String SET_SCOPE = "setScope";
    private static final String GET_SCOPE = "getScope";
    private static final String SET_DATA_BASE = "setDataBase";
    private static final String GET_DATA_BASE = "getDataBase";
    private static final String BEFORE_DISPLAYING = "beforeDisplaying";
    private static final String ON = "on";
    private final String aliasStringMapObject = "code.util.StringMapObject";
    private final String custEntry = "$custentry";
    private final String valueChangedEvent = "code.formathtml.util.ValueChangeEvent";
    private final String custEntries = "$custentries";
    private final String validator = "code.bean.validator.Validator";
    private final String bean = "code.bean.Bean";
    private String custList = "$custlist";
    private String custMap = "$custmap";
    private String errorEl = "$badEl";
    private String aliasRate;
    private String aliasDataBase;

    public void buildBeans() {
        StringMap<StandardField> fields_;
        fields_ = new StringMap<StandardField>();
        StandardClass std_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass(bean, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        StringList params_;
        params_ = new StringList();
        method_ = new StandardMethod(BEFORE_DISPLAYING, params_, getAliasVoid(), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_DATA_BASE, params_, getAliasObject(), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasObject());
        method_ = new StandardMethod(SET_DATA_BASE, params_, getAliasVoid(), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_SCOPE, params_, getAliasString(), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(SET_SCOPE, params_, getAliasVoid(), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_LANGUAGE, params_, getAliasString(), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod(SET_LANGUAGE, params_, getAliasVoid(), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_FORMS, params_, aliasStringMapObject, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasStringMapObject);
        method_ = new StandardMethod(SET_FORMS, params_, getAliasVoid(), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        getStandards().put(bean, std_);
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass(aliasStringMapObject, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        getStandards().put(aliasStringMapObject, std_);
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        StandardClass cl_;
        cl_ = new StandardClass(custList, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        cl_.getDirectInterfaces().add(getAliasCountable());
        cl_.getDirectInterfaces().add(getAliasSimpleIterableType());
        cl_.setIterative(getAliasObject());
        getStandards().put(custList, cl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        cl_ = new StandardClass(custMap, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(ENTRIES, params_, getAliasSimpleIterableType(), false, MethodModifier.NORMAL, cl_);
        methods_.put(method_.getId(), method_);
        cl_.getDirectInterfaces().add(getAliasCountable());
        cl_.getDirectInterfaces().add(custEntries);
        cl_.setIterative(getAliasObject());
        getStandards().put(custMap, cl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        cl_ = new StandardClass(custEntries, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        cl_.getDirectInterfaces().add(getAliasCountable());
        cl_.getDirectInterfaces().add(getAliasSimpleIterableType());
        cl_.setIterative(custEntry);
        getStandards().put(custEntries, cl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        cl_ = new StandardClass(custEntry, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(GET_KEY, params_, getAliasObject(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_VALUE, params_, getAliasObject(), false, MethodModifier.NORMAL,cl_);
        methods_.put(method_.getId(), method_);
        getStandards().put(custEntry, cl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        cl_ = new StandardClass(valueChangedEvent, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(GET_NEW_VALUE, params_, getAliasObject(), false, MethodModifier.NORMAL, cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_OLD_VALUE, params_, getAliasObject(), false, MethodModifier.NORMAL, cl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_INDEXES, params_, getCustList(), false, MethodModifier.NORMAL, cl_);
        methods_.put(method_.getId(), method_);
        getStandards().put(valueChangedEvent, cl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        cl_ = new StandardClass(validator, fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        params_ = new StringList(getAliasObject(),getAliasObject(),getAliasObject());
        method_ = new StandardMethod(VALIDATE, params_, getAliasObject(), false, MethodModifier.NORMAL, cl_);
        methods_.put(method_.getId(), method_);
        getStandards().put(validator, cl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        cl_ = new StandardClass(errorEl, fields_, constructors_, methods_, getAliasError(), MethodModifier.ABSTRACT);
        getStandards().put(errorEl, cl_);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (_instance.getInstance() instanceof Bean) {
            if (StringList.quickEq(_method.getConstraints().getName(), BEFORE_DISPLAYING)) {
                ((Bean)_instance.getInstance()).beforeDisplaying();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SET_DATA_BASE)) {
                ((Bean)_instance.getInstance()).setDataBase(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_DATA_BASE)) {
                Object db_ = ((Bean)_instance.getInstance()).getDataBase();
                if (getAliasDataBase() != null) {
                    res_.setResult(new StdStruct(db_, getAliasDataBase()));
                    return res_;
                }
                res_.setResult(new StdStruct(db_, getAliasObject()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_FORMS)) {
                StringMapObject resMap_ = ((Bean)_instance.getInstance()).getForms();
                res_.setResult(new StringMapObjectStruct(resMap_));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SET_FORMS)) {
                ((Bean)_instance.getInstance()).setForms((StringMapObject)_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_LANGUAGE)) {
                String resMap_ = ((Bean)_instance.getInstance()).getLanguage();
                res_.setResult(new StringStruct(resMap_));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SET_LANGUAGE)) {
                ((Bean)_instance.getInstance()).setLanguage((String)_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_SCOPE)) {
                String resMap_ = ((Bean)_instance.getInstance()).getScope();
                res_.setResult(new StringStruct(resMap_));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SET_SCOPE)) {
                ((Bean)_instance.getInstance()).setScope((String)_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (_instance.getInstance() instanceof SimpleEntries) {
            SimpleIterable db_ = ((SimpleEntries)_instance.getInstance()).entries();
            res_.setResult(new StdStruct(db_, custEntries));
            return res_;
        }
        if (_instance.getInstance() instanceof SimpleEntry) {
            SimpleEntry db_ = (SimpleEntry)_instance.getInstance();
            if (StringList.quickEq(_method.getConstraints().getName(), GET_KEY)) {
                Object key_ = db_.getKey();
                res_.setResult(StdStruct.wrapStd(key_, _cont));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_VALUE)) {
                Object value_ = db_.getValue();
                res_.setResult(StdStruct.wrapStd(value_, _cont));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof ValueChangeEvent) {
            ValueChangeEvent db_ = (ValueChangeEvent)_instance.getInstance();
            if (StringList.quickEq(_method.getConstraints().getName(), GET_NEW_VALUE)) {
                Object key_ = db_.getNewValue();
                res_.setResult(new StdStruct(key_, getStructClassName(key_, _cont)));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_OLD_VALUE)) {
                Object value_ = db_.getOldValue();
                res_.setResult(new StdStruct(value_, getStructClassName(value_, _cont)));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_INDEXES)) {
                Numbers<Long> value_ = db_.getIndexes();
                res_.setResult(new StdStruct(value_, StringList.concat(custList,Templates.TEMPLATE_BEGIN,getAliasLong(),Templates.TEMPLATE_END)));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof Validator) {
            Validator validator_ = (Validator) _instance.getInstance();
            if (StringList.quickEq(_method.getConstraints().getName(), VALIDATE)) {
                Message message_ = validator_.validate(_args[0], _args[1], _args[2]);
                if (message_ == null) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(new StdStruct(message_, getAliasObject()));
                return res_;
            }
        }
        return getOtherResultBean(_cont, _instance, _method, _args);
    }
    @Override
    public String getOtherStructClassName(Object _struct, ContextEl _context) {
        String cl_ = getOtherBeanStructClassName(_struct, _context);
        if (!StringList.quickEq(cl_, getAliasObject())) {
            return cl_;
        }
        if (_struct instanceof Bean) {
            return ((Bean)_struct).getClassName();
        }
        if (_struct instanceof Validator) {
            return ((Validator)_struct).getClassName();
        }
        if (_struct instanceof Translator) {
            return ((Translator)_struct).getClassName();
        }
        if (_struct instanceof SimpleList) {
            return getCustList();
        }
        if (_struct instanceof SimpleEntries) {
            return getCustMap();
        }
        if (_struct instanceof SimpleEntry) {
            return custEntry;
        }
        if (_struct instanceof ValueChangeEvent) {
            return getValueChangedEvent();
        }
        return cl_;
    }
    public ResultErrorStd getStructToBeValidated(StringList _values, String _className, ContextEl _context) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(_className, getAliasBoolean()) || StringList.quickEq(_className, getAliasPrimBoolean())) {
            if (_values.isEmpty() || _values.first().trim().isEmpty()) {
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(new BooleanStruct(StringList.quickEq(_values.first(),ON)));
            return res_;
        }
        if (StringList.quickEq(_className, getAliasString())) {
            if (_values.isEmpty()) {
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(new StringStruct(_values.first()));
            return res_;
        }
        if (StringList.quickEq(_className, getCustList())) {
            res_.setResult(new StdStruct(_values, _className));
            return res_;
        }
        if (StringList.quickEq(_className, getAliasDouble()) || StringList.quickEq(_className, getAliasPrimDouble())) {
            if (_values.isEmpty() || _values.first().trim().isEmpty()) {
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            Double val_ = LgNames.parseDouble(_values.first());
            if (val_ == null) {
                res_.setError(getAliasCast());
                return res_;
            }
            res_.setResult(new DoubleStruct(val_));
            return res_;
        }
        if (StringList.quickEq(_className, getAliasFloat()) || StringList.quickEq(_className, getAliasPrimFloat())) {
            if (_values.isEmpty() || _values.first().trim().isEmpty()) {
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            Float val_ = LgNames.parseFloat(_values.first());
            if (val_ == null) {
                res_.setError(getAliasCast());
                return res_;
            }
            res_.setResult(new FloatStruct(val_));
            return res_;
        }
        if (StringList.quickEq(_className, getAliasLong()) || StringList.quickEq(_className, getAliasPrimLong())) {
            if (_values.isEmpty() || _values.first().trim().isEmpty()) {
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            Long val_ = LgNames.parseLong(_values.first());
            if (val_ == null) {
                res_.setError(getAliasCast());
                return res_;
            }
            res_.setResult(new LongStruct(val_));
            return res_;
        }
        if (StringList.quickEq(_className, getAliasInteger()) || StringList.quickEq(_className, getAliasPrimInteger())) {
            if (_values.isEmpty() || _values.first().trim().isEmpty()) {
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            Integer val_ = LgNames.parseInt(_values.first());
            if (val_ == null) {
                res_.setError(getAliasCast());
                return res_;
            }
            res_.setResult(new IntStruct(val_));
            return res_;
        }
        if (StringList.quickEq(_className, getAliasShort()) || StringList.quickEq(_className, getAliasPrimShort())) {
            if (_values.isEmpty() || _values.first().trim().isEmpty()) {
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            Short val_ = LgNames.parseShort(_values.first());
            if (val_ == null) {
                res_.setError(getAliasCast());
                return res_;
            }
            res_.setResult(new ShortStruct(val_));
            return res_;
        }
        if (StringList.quickEq(_className, getAliasByte()) || StringList.quickEq(_className, getAliasPrimByte())) {
            if (_values.isEmpty() || _values.first().trim().isEmpty()) {
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            Byte val_ = LgNames.parseByte(_values.first());
            if (val_ == null) {
                res_.setError(getAliasCast());
                return res_;
            }
            res_.setResult(new ByteStruct(val_));
            return res_;
        }
        if (StringList.quickEq(_className, getAliasCharacter()) || StringList.quickEq(_className, getAliasPrimChar())) {
            if (_values.isEmpty() || _values.first().trim().isEmpty()) {
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            res_.setResult(new CharStruct(_values.first().charAt(0)));
            return res_;
        }
        if (StringList.quickEq(_className, getSelectedBoolean())) {
            SelectedBoolean en_ = SelectedBoolean.getBoolByName(_values.first());
            if (en_ == null) {
                res_.setError(getAliasError());
            } else {
                res_.setResult(new StdStruct(en_, _className));
            }
            return res_;
        }
        return getOtherStructToBeValidated(_values, _className, _context);
    }
    public Validator buildValidator(Element _element) {
        return null;
    }
    public Translator buildTranslator(Element _element) {
        return null;
    }
    public MathFactory buildMathFactory(Element _element) {
        return null;
    }
    public ResultErrorStd getOtherStructToBeValidated(StringList _values, String _className, ContextEl _context) {
        return new ResultErrorStd();
    }
    public String getOtherBeanStructClassName(Object _struct, ContextEl _context) {
        return getAliasObject();
    }
    public ResultErrorStd setElementAtIndex(Struct _struct, int _index, boolean _key, Struct _element, ContextEl _context) {
        ResultErrorStd res_ = new ResultErrorStd();
        res_.setResult(NullStruct.NULL_VALUE);
        if (_struct.isArray()) {
            setElement(_struct.getInstance(), _index, _element, _context);
            return res_;
        }
        return setOtherElementAtIndex(_struct, _index, _key, _element, _context);
    }
    public ResultErrorStd setOtherElementAtIndex(Struct _struct, int _index, boolean _key, Struct _element, ContextEl _context) {
        return new ResultErrorStd();
    }
    public StringList getDefaultValues(ContextEl _cont, String _className, String _value) {
        return new StringList();
    }
    public ResultErrorStd getName(ContextEl _cont, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (_instance.getInstance() instanceof SelectedBoolean) {
            res_.setResult(new StringStruct(((SelectedBoolean)_instance.getInstance()).name()));
            return res_;
        }
        if (_instance.getInstance() instanceof String) {
            res_.setResult(new StringStruct((String)_instance.getInstance()));
            return res_;
        }
        if (_instance.getInstance() instanceof Number) {
            res_.setResult(new StringStruct(_instance.getInstance().toString()));
            return res_;
        }
        return getOtherName(_cont, _instance);
    }
    public ResultErrorStd getOtherName(ContextEl _cont, Struct _instance) {
        return new ResultErrorStd();
    }
    public ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Object... _args) {
        return new ResultErrorStd();
    }
    public String getAliasRate() {
        return aliasRate;
    }

    public void setAliasRate(String _aliasRate) {
        aliasRate = _aliasRate;
    }

    public String getAliasDataBase() {
        return aliasDataBase;
    }

    public void setAliasDataBase(String _aliasDataBase) {
        aliasDataBase = _aliasDataBase;
    }

    public String getAliasStringMapObject() {
        return aliasStringMapObject;
    }
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
        return bean;
    }
    public String getCustEntry() {
        return custEntry;
    }
    public String getValidator() {
        return validator;
    }
    public String getErrorEl() {
        return errorEl;
    }
    public void setErrorEl(String _errorEl) {
        errorEl = _errorEl;
    }
}
