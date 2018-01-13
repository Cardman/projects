package code.formathtml.util;

import code.bean.Bean;
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
import code.util.CustList;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.StringMapObject;
import code.util.ints.SimpleEntries;
import code.util.ints.SimpleEntry;
import code.util.ints.SimpleIterable;
import code.util.ints.SimpleList;

public class BeanLgNames extends LgNames {

    private static final String ON = "on";
    private final String aliasStringMapObject = "code.util.StringMapObject";
    private final String custList = "$custlist";
    private final String custEntry = "$custentry";
    private final String valueChangedEvent = "code.formathtml.util.ValueChangeEvent";
    private final String custMap = "$custmap";
    private final String custEntries = "$custentries";
    private final String validator = "code.bean.validator.Validator";
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
        StringList params_;
        params_ = new StringList();
        method_ = new StandardMethod("beforeDisplaying", params_, getAliasVoid(), false, MethodModifier.NORMAL, "code.bean.Bean");
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getDataBase", params_, getAliasObject(), false, MethodModifier.NORMAL, "code.bean.Bean");
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasObject());
        method_ = new StandardMethod("setDataBase", params_, getAliasVoid(), false, MethodModifier.NORMAL, "code.bean.Bean");
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getScope", params_, getAliasString(), false, MethodModifier.NORMAL, "code.bean.Bean");
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod("setScope", params_, getAliasVoid(), false, MethodModifier.NORMAL, "code.bean.Bean");
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getLanguage", params_, getAliasString(), false, MethodModifier.NORMAL, "code.bean.Bean");
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod("setLanguage", params_, getAliasVoid(), false, MethodModifier.NORMAL, "code.bean.Bean");
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getForms", params_, aliasStringMapObject, false, MethodModifier.NORMAL, "code.bean.Bean");
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasStringMapObject);
        method_ = new StandardMethod("setForms", params_, getAliasVoid(), false, MethodModifier.NORMAL, "code.bean.Bean");
        methods_.put(method_.getId(), method_);
        std_ = new StandardClass("code.bean.Bean", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        getStandards().put("code.bean.Bean", std_);
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
        params_ = new StringList();
        method_ = new StandardMethod("entries", params_, getAliasSimpleIterableType(), false, MethodModifier.NORMAL, custEntries);
        methods_.put(method_.getId(), method_);
        cl_ = new StandardClass(custMap, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
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
        params_ = new StringList();
        method_ = new StandardMethod("getKey", params_, getAliasObject(), false, MethodModifier.NORMAL, custEntry);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getValue", params_, getAliasObject(), false, MethodModifier.NORMAL, custEntry);
        methods_.put(method_.getId(), method_);
        cl_ = new StandardClass(custEntry, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        getStandards().put(custEntry, cl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        params_ = new StringList();
        method_ = new StandardMethod("getNewValue", params_, getAliasObject(), false, MethodModifier.NORMAL, valueChangedEvent);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getOldValue", params_, getAliasObject(), false, MethodModifier.NORMAL, valueChangedEvent);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getIndexes", params_, getCustList(), false, MethodModifier.NORMAL, valueChangedEvent);
        methods_.put(method_.getId(), method_);
        cl_ = new StandardClass(valueChangedEvent, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        getStandards().put(valueChangedEvent, cl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        params_ = new StringList(getAliasObject(),getAliasObject(),getAliasObject());
        method_ = new StandardMethod("validate", params_, getAliasObject(), false, MethodModifier.NORMAL, validator);
        methods_.put(method_.getId(), method_);
        cl_ = new StandardClass(validator, fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        getStandards().put(validator, cl_);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (_instance.getInstance() instanceof Bean) {
            if (StringList.quickEq(_method.getConstraints().getName(), "beforeDisplaying")) {
                ((Bean)_instance.getInstance()).beforeDisplaying();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "setDataBase")) {
                ((Bean)_instance.getInstance()).setDataBase(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "getDataBase")) {
                Object db_ = ((Bean)_instance.getInstance()).getDataBase();
                res_.setResult(new StdStruct(db_, getAliasObject()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "getForms")) {
                StringMapObject resMap_ = ((Bean)_instance.getInstance()).getForms();
                res_.setResult(new StringMapObjectStruct(resMap_));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "setForms")) {
                ((Bean)_instance.getInstance()).setForms((StringMapObject)_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "getLanguage")) {
                String resMap_ = ((Bean)_instance.getInstance()).getLanguage();
                res_.setResult(new StringStruct(resMap_));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "setLanguage")) {
                ((Bean)_instance.getInstance()).setLanguage((String)_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "getScope")) {
                String resMap_ = ((Bean)_instance.getInstance()).getScope();
                res_.setResult(new StringStruct(resMap_));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "setScope")) {
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
            if (StringList.quickEq(_method.getConstraints().getName(), "getKey")) {
                Object key_ = db_.getKey();
                res_.setResult(new StdStruct(key_, getStructClassName(key_, _cont)));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "getValue")) {
                Object value_ = db_.getValue();
                res_.setResult(new StdStruct(value_, getStructClassName(value_, _cont)));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof ValueChangeEvent) {
            ValueChangeEvent db_ = (ValueChangeEvent)_instance.getInstance();
            if (StringList.quickEq(_method.getConstraints().getName(), "getNewValue")) {
                Object key_ = db_.getNewValue();
                res_.setResult(new StdStruct(key_, getStructClassName(key_, _cont)));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "getOldValue")) {
                Object value_ = db_.getOldValue();
                res_.setResult(new StdStruct(value_, getStructClassName(value_, _cont)));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "getIndexes")) {
                Numbers<Long> value_ = db_.getIndexes();
                res_.setResult(new StdStruct(value_, StringList.concat(custList,Templates.TEMPLATE_BEGIN,getAliasLong(),Templates.TEMPLATE_END)));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof Validator) {
            Validator validator_ = (Validator) _instance.getInstance();
            if (StringList.quickEq(_method.getConstraints().getName(), "validate")) {
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
        try {
            if (StringList.quickEq(_className, getAliasDouble()) || StringList.quickEq(_className, getAliasPrimDouble())) {
                if (_values.isEmpty() || _values.first().trim().isEmpty()) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(new DoubleStruct(Double.parseDouble(_values.first())));
                return res_;
            }
            if (StringList.quickEq(_className, getAliasFloat()) || StringList.quickEq(_className, getAliasPrimFloat())) {
                if (_values.isEmpty() || _values.first().trim().isEmpty()) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(new FloatStruct(Float.parseFloat(_values.first())));
                return res_;
            }
            if (StringList.quickEq(_className, getAliasLong()) || StringList.quickEq(_className, getAliasPrimLong())) {
                if (_values.isEmpty() || _values.first().trim().isEmpty()) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(new LongStruct(Long.parseLong(_values.first())));
                return res_;
            }
            if (StringList.quickEq(_className, getAliasInteger()) || StringList.quickEq(_className, getAliasPrimInteger())) {
                if (_values.isEmpty() || _values.first().trim().isEmpty()) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(new IntStruct(Integer.parseInt(_values.first())));
                return res_;
            }
            if (StringList.quickEq(_className, getAliasShort()) || StringList.quickEq(_className, getAliasPrimShort())) {
                if (_values.isEmpty() || _values.first().trim().isEmpty()) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(new ShortStruct(Short.parseShort(_values.first())));
                return res_;
            }
            if (StringList.quickEq(_className, getAliasByte()) || StringList.quickEq(_className, getAliasPrimByte())) {
                if (_values.isEmpty() || _values.first().trim().isEmpty()) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(new ByteStruct(Byte.parseByte(_values.first())));
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
        } catch (Throwable _0) {
            res_.setError(getAliasCast());
            return res_;
        }
        return getOtherStructToBeValidated(_values, _className, _context);
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
    public String getCustMap() {
        return custMap;
    }
    public String getCustEntries() {
        return custEntries;
    }
    public String getValueChangedEvent() {
        return valueChangedEvent;
    }
}
