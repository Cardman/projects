package code.formathtml.util;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.bean.validator.Message;
import code.bean.validator.Validator;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.NumberInfos;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Element;
import code.util.CustList;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.Replacement;
import code.util.SimpleItr;
import code.util.StringList;
import code.util.StringMap;
import code.util.StringMapObject;
import code.util.ints.Countable;
import code.util.ints.Displayable;
import code.util.ints.MathFactory;
import code.util.ints.SimpleEntries;
import code.util.ints.SimpleEntry;
import code.util.ints.SimpleIterable;
import code.util.ints.SimpleList;

public abstract class BeanLgNames extends LgNames {

    private static final int DEFAULT_RADIX = 10;
    private static final long MULTMIN_RADIX_TEN = Long.MIN_VALUE / DEFAULT_RADIX;
    private static final long N_MULTMAX_RADIX_TEN = -Long.MAX_VALUE / DEFAULT_RADIX;
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

    private String aliasDisplayable;
    private String aliasDisplay;
    private String custList = "$custlist";
    private String custMap = "$custmap";
    private String errorEl = "$badEl";
    private String aliasRate;
    private String aliasDataBase;
    private String iteratorVar;
    private String hasNextVar;
    private String nextVar;
    private String aliasSimpleIteratorType = "code.util.SimpleItr";
    private String aliasSimpleIterableType = "code.util.ints.SimpleIterable";
    private String aliasCountable = "code.util.ints.Countable";

    private String aliasGet;
    private String aliasSize;
    private String aliasSimpleIterator;

    private CustList<ExecOperationNode> expsIterator;
    private CustList<ExecOperationNode> expsHasNext;
    private CustList<ExecOperationNode> expsNext;
    private StringMap<String> iterables = new StringMap<String>();

    public static Double parseDouble(String _nb) {
        NumberInfos infos_ = NumParsers.trySplitDouble(_nb);
        if (infos_ == null) {
            return null;
        }
        return NumParsers.parseDouble(infos_);
    }

    public static Float parseFloat(String _nb) {
        NumberInfos infos_ = NumParsers.trySplitDouble(_nb);
        if (infos_ == null) {
            return null;
        }
        double double_ = NumParsers.parseDouble(infos_);
        double abs_ = Math.abs(double_);
        if (abs_ > Float.MAX_VALUE) {
            return null;
        }
        return (float)double_;
    }

    public static Byte parseByte(String _string) {
        Long int_ = parseLong(_string);
        if (int_ == null) {
            return null;
        }
        if (int_ < Byte.MIN_VALUE) {
            return null;
        }
        if (int_ > Byte.MAX_VALUE) {
            return null;
        }
        return int_.byteValue();
    }

    public static Short parseShort(String _string) {
        Long int_ = parseLong(_string);
        if (int_ == null) {
            return null;
        }
        if (int_ < Short.MIN_VALUE) {
            return null;
        }
        if (int_ > Short.MAX_VALUE) {
            return null;
        }
        return int_.shortValue();
    }

    public static Integer parseInt(String _string) {
        Long int_ = parseLong(_string);
        if (int_ == null) {
            return null;
        }
        if (int_ < Integer.MIN_VALUE) {
            return null;
        }
        if (int_ > Integer.MAX_VALUE) {
            return null;
        }
        return int_.intValue();
    }

    public static Long parseLong(String _string) {
        if (_string == null) {
            return null;
        }
        long result_ = 0;
        boolean negative_ = false;
        int i_ = 0;
        int max_ = _string.length();
        long limit_;
        long multmin_;
        int digit_;

        if (max_ > 0) {
            if (_string.charAt(0) == '-') {
                negative_ = true;
                limit_ = Long.MIN_VALUE;
                i_++;
            } else {
                limit_ = -Long.MAX_VALUE;
            }
            if (negative_) {
                multmin_ = MULTMIN_RADIX_TEN;
            } else {
                multmin_ = N_MULTMAX_RADIX_TEN;
            }
            if (i_ < max_) {
                char ch_ = _string.charAt(i_);
                i_++;
                if (ch_ < '0' || ch_ > '9') {
                    return null;
                }
                digit_ = ch_ - '0';
                result_ = -digit_;
            }
            while (i_ < max_) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                char ch_ = _string.charAt(i_);
                i_++;
                if (ch_ < '0' || ch_ > '9') {
                    return null;
                }
                digit_ = ch_ - '0';
                if (result_ < multmin_) {
                    return null;
                }
                result_ *= 10;
                if (result_ < limit_ + digit_) {
                    return null;
                }
                result_ -= digit_;
            }
        } else {
            return null;
        }
        if (negative_) {
            if (i_ > 1) {
                return result_;
            }
            return null;
        }
        return -result_;
    }

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
        iterables.put(custList,getAliasObject());
        getStandards().put(custList, cl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        cl_ = new StandardClass(custMap, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(ENTRIES, params_, getAliasSimpleIterableType(), false, MethodModifier.NORMAL, cl_);
        methods_.put(method_.getId(), method_);
        cl_.getDirectInterfaces().add(getAliasCountable());
        cl_.getDirectInterfaces().add(custEntries);
        iterables.put(custMap,getAliasObject());
        getStandards().put(custMap, cl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        cl_ = new StandardClass(custEntries, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        cl_.getDirectInterfaces().add(getAliasCountable());
        cl_.getDirectInterfaces().add(getAliasSimpleIterableType());
        iterables.put(custEntries,custEntry);
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
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        params_ = new StringList();
        StandardInterface stdi_ = new StandardInterface(aliasSimpleIterableType, methods_, params_);
        params_ = new StringList();
        method_ = new StandardMethod(getAliasSimpleIterator(), params_, aliasSimpleIteratorType, false, MethodModifier.ABSTRACT, stdi_);
        methods_.put(method_.getId(), method_);
        getStandards().put(aliasSimpleIterableType, stdi_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        stdi_ = new StandardInterface(aliasCountable, methods_, params_);
        params_ = new StringList();
        method_ = new StandardMethod(getAliasSize(), params_, getAliasPrimInteger(), false, MethodModifier.ABSTRACT,stdi_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod(getAliasGet(), params_, getAliasObject(), false, MethodModifier.ABSTRACT,stdi_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(getAliasIsEmpty(), params_, getAliasPrimBoolean(), false, MethodModifier.ABSTRACT,stdi_);
        methods_.put(method_.getId(), method_);
        getStandards().put(aliasCountable, stdi_);
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardClass stdcl_ = new StandardClass(aliasSimpleIteratorType, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(getAliasNext(), params_, getAliasObject(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(getAliasHasNext(), params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasSimpleIteratorType, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        stdi_ = new StandardInterface(aliasDisplayable, methods_, new StringList());
        params_ = new StringList();
        method_ = new StandardMethod(aliasDisplay, params_, getAliasString(), false, MethodModifier.ABSTRACT,stdi_);
        methods_.put(method_.getId(), method_);
        getStandards().put(aliasDisplayable, stdi_);
    }

    public StringMap<String> getIterables() {
        return iterables;
    }

    @Override
    public void buildIterable(ContextEl _context) {
        super.buildIterable(_context);
        String locName_ = _context.getNextTempVar();
        String exp_;
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(getAliasSimpleIterableType());
        _context.getInternVars().put(locName_, locVar_);
        iteratorVar = locName_;
        String simpleIterator_ = getAliasSimpleIterator();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(simpleIterator_,PARS));
        expsIterator = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(getAliasSimpleIteratorType());
        _context.getInternVars().put(locName_, locVar_);
        hasNextVar = locName_;
        String hasNext_ = getAliasHasNext();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(hasNext_,PARS));
        expsHasNext = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(getAliasSimpleIteratorType());
        _context.getInternVars().put(locName_, locVar_);
        nextVar = locName_;
        String next_ = getAliasNext();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(next_,PARS));
        expsNext = ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true));
    }
    @Override
    public IterableAnalysisResult getCustomType(StringList _names, ContextEl _context) {
        StringList out_ = new StringList();
        Boolean nativeCmp_ = null;
        for (String f: _names) {
            String type_ = getIterableFullTypeByStds(f, _context);
            String iterable_ = getAliasIterable();
            if (type_ == null) {
                type_ = Templates.getFullTypeByBases(f, iterable_, _context);
                nativeCmp_ = false;
            } else {
                nativeCmp_ = true;
            }
            if (type_ != null) {
                out_.add(type_);
            }
        }
        out_.removeDuplicates();
        return new NativeIterableAnalysisResult(out_, nativeCmp_);
    }
    static String getIterableFullTypeByStds(String _subType, ContextEl _context) {
        String baseSubType_ = _subType;
        BeanLgNames lgNames_ = (BeanLgNames) _context.getStandards();
        String it_ = lgNames_.getIterables().getVal(baseSubType_);
        if (it_ == null) {
            return null;
        }
        return StringList.concat(lgNames_.getAliasIterable(),"<",it_,">");
    }
    public String getIteratorVar() {
        return iteratorVar;
    }
    public String getHasNextVar() {
        return hasNextVar;
    }
    public String getNextVar() {
        return nextVar;
    }
    public CustList<ExecOperationNode> getExpsIterator() {
        return expsIterator;
    }
    public CustList<ExecOperationNode> getExpsHasNext() {
        return expsHasNext;
    }
    public CustList<ExecOperationNode> getExpsNext() {
        return expsNext;
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
            ConstructorId _method, Struct... _args) {
        if (StringList.quickEq(_method.getName(),getAliasObject())) {
            return super.getOtherResult(_cont,_method,_args);
        }
        StringList list_ = _method.getParametersTypes();
        BeanLgNames b_ = (BeanLgNames) _cont.getStandards();
        Object[] argsObj_ = adaptedArgs(list_, b_, _args);
        return getOtherResultBean(_cont, _method, argsObj_);
    }

    public ResultErrorStd getOtherResultBean(ContextEl _cont,
                                             ConstructorId _method, Object... _args) {
        return new ResultErrorStd();
    }
    public static ResultErrorStd getField(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames lgNames_ = (BeanLgNames) _cont.getStandards();
        String type_ = _classField.getClassName();
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String byteType_ = lgNames_.getAliasByte();
        String shortType_ = lgNames_.getAliasShort();
        String intType_ = lgNames_.getAliasInteger();
        String longType_ = lgNames_.getAliasLong();
        String floatType_ = lgNames_.getAliasFloat();
        String doubleType_ = lgNames_.getAliasDouble();
        if (StringList.quickEq(type_, booleanType_)
                || StringList.quickEq(type_, charType_)
                || StringList.quickEq(type_, byteType_)
                || StringList.quickEq(type_, shortType_)
                || StringList.quickEq(type_, intType_)
                || StringList.quickEq(type_, longType_)
                || StringList.quickEq(type_, floatType_)
                || StringList.quickEq(type_, doubleType_)) {
            return lgNames_.getSimpleResult(_cont, _classField);
        }
        return lgNames_.getOtherResult(_cont, _classField, _instance);
    }
    public ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance) {
        return new ResultErrorStd();
    }

    public static ResultErrorStd setField(ContextEl _cont, ClassField _classField, Struct _instance, Struct _value) {
        BeanLgNames lgNames_ = (BeanLgNames) _cont.getStandards();
        Object value_ = adaptedArg(lgNames_, _value);
        return lgNames_.setOtherResult(_cont, _classField, _instance, value_);
    }
    public ResultErrorStd setOtherResult(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        return new ResultErrorStd();
    }

    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        StringList list_ = _method.getConstraints().getParametersTypes();
        String type_ = _method.getClassName();
        if (StringList.quickEq(type_, getAliasEnums())) {
            return super.getOtherResult(_cont,_instance,_method,_args);
        }
        BeanLgNames b_ = (BeanLgNames) _cont.getStandards();
        Object[] argsObj_ = adaptedArgs(list_, b_, _args);
        Object instance_ = null;
        if (!_method.getConstraints().isStaticMethod()) {
            instance_ = ((RealInstanceStruct)_instance).getInstance();
        }
        if (instance_ instanceof Displayable) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_, b_.getAliasDisplay()) || StringList.quickEq(name_, b_.getAliasToString())) {
                res_.setResult(new StringStruct(((Displayable)instance_).display()));
                return res_;
            }
        }
        if (instance_ instanceof Bean) {
            if (StringList.quickEq(_method.getConstraints().getName(), BEFORE_DISPLAYING)) {
                ((Bean)instance_).beforeDisplaying();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SET_DATA_BASE)) {
                ((Bean)instance_).setDataBase(argsObj_[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_DATA_BASE)) {
                Object db_ = ((Bean)instance_).getDataBase();
                if (getAliasDataBase() != null) {
                    res_.setResult(StdStruct.wrapStd(db_, _cont, getAliasDataBase()));
                    return res_;
                }
                res_.setResult(StdStruct.wrapStd(db_, _cont));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_FORMS)) {
                StringMapObject resMap_ = ((Bean)instance_).getForms();
                res_.setResult(new StringMapObjectStruct(resMap_));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SET_FORMS)) {
                ((Bean)instance_).setForms(((StringMapObjectStruct)_args[0]).getInstance());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_LANGUAGE)) {
                String resMap_ = ((Bean)instance_).getLanguage();
                res_.setResult(new StringStruct(resMap_));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SET_LANGUAGE)) {
                ((Bean)instance_).setLanguage(((StringStruct) _args[0]).getInstance());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_SCOPE)) {
                String resMap_ = ((Bean)instance_).getScope();
                res_.setResult(new StringStruct(resMap_));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SET_SCOPE)) {
                ((Bean)instance_).setScope(((StringStruct) _args[0]).getInstance());
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        if (instance_ instanceof Countable) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_, getAliasIsEmpty())) {
                res_.setResult(new BooleanStruct(((Countable) instance_).isEmpty()));
                return res_;
            }
            if (StringList.quickEq(name_, getAliasSize())) {
                res_.setResult(new IntStruct(((Countable) instance_).size()));
                return res_;
            }
            if (StringList.quickEq(name_, getAliasGet())) {
                res_.setResult(StdStruct.wrapStd(((Countable) instance_).get(((NumberStruct) _args[0]).intValue()), _cont));
                return res_;
            }
        }
        if (instance_ instanceof SimpleIterable) {
            String name_ = _method.getConstraints().getName();
            if (StringList.quickEq(name_, getAliasSimpleIterator())) {
                String typeInst_ = getStructClassName(_instance, _cont);
                String it_ = getIterables().getVal(typeInst_);
                res_.setResult(StdStruct.newInstance(((SimpleIterable) instance_).simpleIterator(), StringList.concat(getAliasSimpleIteratorType(),Templates.TEMPLATE_BEGIN,it_,Templates.TEMPLATE_END)));
                return res_;
            }
        }
        if (instance_ instanceof SimpleItr) {
            String name_ = _method.getConstraints().getName();
            return prIterator(_cont, name_, _instance);
        }
        if (instance_ instanceof SimpleEntries) {
            SimpleIterable db_ = ((SimpleEntries)instance_).entries();
            res_.setResult(new StdStruct(db_, custEntries));
            return res_;
        }
        if (instance_ instanceof SimpleEntry) {
            SimpleEntry db_ = (SimpleEntry)instance_;
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
        if (instance_ instanceof ValueChangeEvent) {
            ValueChangeEvent db_ = (ValueChangeEvent)instance_;
            if (StringList.quickEq(_method.getConstraints().getName(), GET_NEW_VALUE)) {
                res_.setResult(db_.getNewValue());
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_OLD_VALUE)) {
                res_.setResult(db_.getOldValue());
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_INDEXES)) {
                Numbers<Long> value_ = db_.getIndexes();
                res_.setResult(StdStruct.newListLong(value_, StringList.concat(custList,Templates.TEMPLATE_BEGIN,getAliasLong(),Templates.TEMPLATE_END)));
                return res_;
            }
        }
        if (instance_ instanceof Validator) {
            Validator validator_ = (Validator) instance_;
            if (StringList.quickEq(_method.getConstraints().getName(), VALIDATE)) {
                Message message_ = validator_.validate(argsObj_[0], argsObj_[1], argsObj_[2]);
                if (message_ == null) {
                    res_.setResult(NullStruct.NULL_VALUE);
                    return res_;
                }
                res_.setResult(StdStruct.wrapStd(message_, _cont));
                return res_;
            }
        }
        return getOtherResultBean(_cont, _instance, _method, argsObj_);
    }
    ResultErrorStd prIterator(ContextEl _cont, String _name, Struct _struct) {
        ResultErrorStd result_ = new ResultErrorStd();
        Object instance_ = ((StdStruct) _struct).getInstance();
        LgNames lgNames_ = _cont.getStandards();
        if (StringList.quickEq(_name, lgNames_.getAliasNext())) {
            Object resObj_ = ((SimpleItr)instance_).next();
            result_.setResult(StdStruct.wrapStd(resObj_, _cont));
            return result_;
        }
        result_.setResult(new BooleanStruct(((SimpleItr)instance_).hasNext()));
        return result_;
    }

    public final String getOtherStructClassName(Object _struct, ContextEl _context) {
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
        if (_struct instanceof SimpleIterable) {
            return aliasSimpleIterableType;
        }
        if (_struct instanceof SimpleItr) {
            return aliasSimpleIteratorType;
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
            Double val_ = parseDouble(_values.first());
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
            Float val_ = parseFloat(_values.first());
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
            Long val_ = parseLong(_values.first());
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
            Integer val_ = parseInt(_values.first());
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
            Short val_ = parseShort(_values.first());
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
            Byte val_ = parseByte(_values.first());
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
        if (_struct instanceof ArrayStruct) {
            ArrayStruct a_ = (ArrayStruct) _struct;
            a_.getInstance()[_index] = _element;
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
        if (_instance instanceof StringStruct) {
            res_.setResult(_instance);
            return res_;
        }
        if (_instance instanceof NumberStruct) {
            res_.setResult(new StringStruct(Long.toString(((NumberStruct)_instance).longValue())));
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
    static Object[] adaptedArgs(StringList _params,BeanLgNames _stds,Struct... _args) {
        int len_ = _params.size();
        Object[] args_ = new Object[len_];
        for (int i = 0; i < len_; i++) {
            Struct argStruct_ = _args[i];
            if (argStruct_ == NullStruct.NULL_VALUE) {
                continue;
            }
            if (argStruct_ instanceof ArrayStruct) {
                ArrayStruct arr_ = (ArrayStruct) argStruct_;
                Struct[] str_ = arr_.getInstance();
                String compo_ = PrimitiveTypeUtil.getQuickComponentType(arr_.getClassName());
                if (StringList.quickEq(compo_, _stds.getAliasPrimByte())) {
                    byte[] adapt_ = new byte[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).byteValue();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimShort())) {
                    short[] adapt_ = new short[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).shortValue();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimInteger())) {
                    int[] adapt_ = new int[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).intValue();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimChar())) {
                    char[] adapt_ = new char[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((CharStruct) s).getChar();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimLong())) {
                    long[] adapt_ = new long[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).longValue();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimFloat())) {
                    float[] adapt_ = new float[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).floatValue();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimDouble())) {
                    double[] adapt_ = new double[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).doubleValue();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasString())) {
                    String[] adapt_ = new String[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((CharSequenceStruct)s).toStringInstance();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasReplacement())) {
                    Replacement[] adapt_ = new Replacement[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((ReplacementStruct) s).getInstance();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimBoolean())) {
                    boolean[] adapt_ = new boolean[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((BooleanStruct) s).getInstance();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                args_[i] = _stds.getOtherArguments(str_, compo_);
                continue;
            }
            String p_ = _params.get(i);
            String pType_ = PrimitiveTypeUtil.toPrimitive(p_, _stds);
            if (argStruct_ instanceof NumberStruct) {
                if (argStruct_ instanceof CharStruct) {
                    if (StringList.quickEq(pType_, _stds.getAliasPrimChar())) {
                        args_[i] = ((CharStruct) argStruct_).getChar();
                    } else {
                        args_[i] = ((NumberStruct) argStruct_).longValue();
                    }
                } else if (argStruct_ instanceof IntStruct) {
                    args_[i] = ((NumberStruct) argStruct_).intValue();
                } else if (argStruct_ instanceof DoubleStruct) {
                    args_[i] = ((DoubleStruct) argStruct_).doubleValue();
                } else {
                    if (StringList.quickEq(pType_, _stds.getAliasPrimChar())) {
                        args_[i] = (char) ((NumberStruct) argStruct_).intValue();
                    } else {
                        args_[i] = ((NumberStruct) argStruct_).longValue();
                    }
                }
            } else if (argStruct_ instanceof StringStruct) {
                args_[i] = ((StringStruct)argStruct_).getInstance();
            } else if (argStruct_ instanceof StringBuilderStruct) {
                args_[i] = ((StringBuilderStruct)argStruct_).getInstance();
            } else if (argStruct_ instanceof BooleanStruct) {
                args_[i] = ((BooleanStruct)argStruct_).getInstance();
            } else {
                args_[i] = ((RealInstanceStruct)argStruct_).getInstance();
            }
        }
        return args_;
    }

    static Object adaptedArg(BeanLgNames _stds, Struct _args) {
        if (_args == NullStruct.NULL_VALUE) {
            return null;
        }
        if (_args instanceof ArrayStruct) {
            ArrayStruct arr_ = (ArrayStruct) _args;
            Struct[] str_ = arr_.getInstance();
            String compo_ = PrimitiveTypeUtil.getQuickComponentType(arr_.getClassName());
            if (StringList.quickEq(compo_, _stds.getAliasPrimByte())) {
                byte[] adapt_ = new byte[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((NumberStruct)s).byteValue();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasPrimShort())) {
                short[] adapt_ = new short[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((NumberStruct)s).shortValue();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasPrimInteger())) {
                int[] adapt_ = new int[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((NumberStruct)s).intValue();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasPrimChar())) {
                char[] adapt_ = new char[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((CharStruct) s).getChar();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasPrimLong())) {
                long[] adapt_ = new long[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((NumberStruct)s).longValue();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasPrimFloat())) {
                float[] adapt_ = new float[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((NumberStruct)s).floatValue();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasPrimDouble())) {
                double[] adapt_ = new double[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((NumberStruct)s).doubleValue();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasString())) {
                String[] adapt_ = new String[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((CharSequenceStruct)s).toStringInstance();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasReplacement())) {
                Replacement[] adapt_ = new Replacement[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((ReplacementStruct) s).getInstance();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasPrimBoolean())) {
                boolean[] adapt_ = new boolean[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((BooleanStruct) s).getInstance();
                    i_++;
                }
                return adapt_;
            }
            return _stds.getOtherArguments(str_, compo_);
        }
        if (_args instanceof NumberStruct) {
            if (_args instanceof CharStruct) {
                return ((CharStruct) _args).getChar();
            }
            if (_args instanceof IntStruct) {
                return ((IntStruct) _args).intValue();
            }
            return ((NumberStruct) _args).longValue();
        }
        if (_args instanceof StringStruct) {
            return ((StringStruct)_args).getInstance();
        }
        if (_args instanceof StringBuilderStruct) {
            return ((StringBuilderStruct)_args).getInstance();
        }
        if (_args instanceof BooleanStruct) {
            return ((BooleanStruct) _args).getInstance();
        }
        return ((RealInstanceStruct) _args).getInstance();
    }
    public Object getOtherArguments(Struct[] _str, String _base) {
        return null;
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
    public String getAliasSimpleIteratorType() {
        return aliasSimpleIteratorType;
    }
    public String getAliasSimpleIterableType() {
        return aliasSimpleIterableType;
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
    public String getAliasDisplay() {
        return aliasDisplay;
    }
    public void setAliasDisplay(String _aliasDisplay) {
        aliasDisplay = _aliasDisplay;
    }
    public String getAliasGet() {
        return aliasGet;
    }
    public void setAliasGet(String _aliasGet) {
        aliasGet = _aliasGet;
    }
    public String getAliasSize() {
        return aliasSize;
    }
    public void setAliasSize(String _aliasSize) {
        aliasSize = _aliasSize;
    }
    public String getAliasSimpleIterator() {
        return aliasSimpleIterator;
    }
    public void setAliasSimpleIterator(String _aliasSimpleIterator) {
        aliasSimpleIterator = _aliasSimpleIterator;
    }

}
