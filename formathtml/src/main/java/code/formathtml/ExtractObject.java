package code.formathtml;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.exceptions.BadExpressionLanguageException;
import code.expressionlanguage.exceptions.InvokeRedinedMethException;
import code.expressionlanguage.exceptions.UndefinedVariableException;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.formathtml.exceptions.CharacterFormatException;
import code.formathtml.exceptions.InexistingTranslatorException;
import code.serialize.ConstClasses;
import code.serialize.exceptions.InvokingException;
import code.serialize.exceptions.RuntimeInstantiationException;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.util.CustList;
import code.util.SimpleItr;
import code.util.StringList;
import code.util.StringMap;
import code.util.StringMapObject;
import code.util.exceptions.NullObjectException;
import code.util.exceptions.RuntimeClassNotFoundException;
import code.util.ints.Displayable;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;
import code.util.ints.MathFactory;
import code.util.ints.SimpleEntries;
import code.util.ints.SimpleEntry;
import code.util.ints.SimpleIterable;

final class ExtractObject {

    static final String NAMESPACE_URI = "javahtml";
    static final String EMPTY_STRING = "";
    static final String RETURN_LINE = "\n";
    static final String RETURN_TAB = "\n\t";

    static final String SPACE = " ";
    static final String VAR_METHOD = "varMethod";
    static final String BEAN_ATTRIBUTE = "bean";
    static final String COMMA = ",";
    static final String DOT = ".";
    private static final String INSTANCE = "$new ";
    private static final char BEGIN_ARGS = '(';
    private static final char SEP_ARGS = ',';
    private static final char END_ARGS = ')';
    private static final String GET_LOC_VAR = ";.";
    private static final String NO_PARAM_METHOD = "()";
    private static final String CMP = "=";
    private static final char RIGHT_EL = '}';
    private static final char LEFT_EL = '{';

    private static final String LOOP_MESSAGE = "loop";
    private static final String LOCAL_MESSAGE = "local";

    private static final String ATTRIBUTE_QUOTED = "quoted";
    private static final String ATTRIBUTE_ESCAPED = "escaped";
    private static final char ESCAPED = '\\';
    private static final char MATH_INTERPRET = '`';
    private static final String CALL_METHOD = "$";
    private static final String ATTRIBUTE_VALUE = "value";
    private static final String ATTRIBUTE_ESCAPED_EAMP = "escapedamp";
    private static final char SEP_TR = ',';
    private static final char QUOTE = 39;
    private static final String QUOTE_DOUBLE = "\"";
    private static final String GET_STRING ="getString";
    private static final String NAME ="name";
    private static final String ITERATOR ="iterator";
    private static final String HAS_NEXT ="hasNext";
    private static final String NEXT ="next";
    private static final String ENTRY_LIST ="entries";
    private static final String GET_KEY ="getKey";
    private static final String GET_VALUE ="getValue";
    private static final String GET_SCOPE ="getScope";
    private static final String SET_SCOPE ="setScope";
    private static final String GET_FORMS ="getForms";
    private static final String SET_FORMS ="setForms";
    private static final String BEFORE_DISPLAYING ="beforeDisplaying";
    private static final String GET_DATA_BASE ="getDataBase";
    private static final String SET_DATA_BASE ="setDataBase";
    private static final String GET_LANGUAGE ="getLanguage";
    private static final String SET_LANGUAGE ="setLanguage";

    private ExtractObject() {
    }
    static String formatNumVariables(String _pattern, Configuration _conf, ImportingPage _ip, StringMap<String> _files) {
        StringBuilder str_ = new StringBuilder();
        StringBuilder arg_ = new StringBuilder();
        int length_ = _pattern.length();
        boolean escaped_ = false;
        int i_ = CustList.FIRST_INDEX;
        while (i_ < length_) {
            char cur_ = _pattern.charAt(i_);
            if (cur_ == QUOTE) {
                escaped_ = !escaped_;
                if (i_ < length_ - 1) {
                    if (_pattern.charAt(i_ + 1) == QUOTE) {
                        str_.append(QUOTE);
                        i_++;
                        i_++;
                        escaped_ = false;
                        continue;
                    }
                }
                i_++;
                continue;
            }
            if (escaped_) {
                str_.append(cur_);
                i_++;
                continue;
            }
            if (cur_ == LEFT_EL) {
                StringBuilder tr_ = new StringBuilder();
                int indexSepTr_ = _pattern.indexOf(SEP_TR, i_ + 1);
                boolean processTr_ = false;
                if (i_ + 1 < length_ && indexSepTr_ != CustList.INDEX_NOT_FOUND_ELT) {
                    boolean allWord_ = true;
                    boolean existWord_ = false;
                    int j_ = i_;
                    while (true) {
                        if (j_ == indexSepTr_) {
                            break;
                        }
                        if (j_ > i_+1 && !StringList.isWordChar(_pattern.charAt(j_))) {
                            allWord_ = false;
                            break;
                        }
                        if (StringList.isWordChar(_pattern.charAt(j_))) {
                            existWord_ = true;
                        }
                        j_++;
                    }
                    if (!existWord_) {
                        _conf.getLastPage().setOffset(i_);
                        throw new BadExpressionLanguageException(arg_.toString()+RETURN_LINE+_conf.joinPages());
                    }
                    processTr_ = allWord_;
                }
                if (processTr_) {
                    int j_ = i_;
                    while (true) {
                        if (j_ == indexSepTr_) {
                            j_++;
                            i_ = j_;
                            break;
                        }
                        j_++;
                        tr_.append(_pattern.charAt(j_));
                    }
                    tr_.deleteCharAt(tr_.length()-1);
                } else {
                    i_++;
                }
                if (i_ >= length_ || _pattern.charAt(i_) == RIGHT_EL) {
                    _conf.getLastPage().setOffset(i_);
                    throw new BadExpressionLanguageException(arg_.toString()+RETURN_LINE+_conf.joinPages());
                }
                ContextEl context_ = _conf.toContextEl();
                Struct trloc_ = null;
                if (!tr_.toString().isEmpty()) {
                    try {
                        trloc_ = _conf.getBuiltTranslators().getVal(tr_.toString());
                        if (trloc_ == null) {
                            _conf.getLastPage().setOffset(i_);
                            throw new InexistingTranslatorException(tr_+RETURN_LINE+_conf.joinPages());
                        }
                    } catch (Throwable _0) {
                        _conf.getLastPage().setOffset(i_);
                        throw new InexistingTranslatorException(tr_+RETURN_LINE+_conf.joinPages());
                    }
                }
                _conf.getLastPage().setOffset(i_);
                Argument argloc_ = ElUtil.processEl(_pattern, context_, i_, LEFT_EL, RIGHT_EL);
                Struct s_ = argloc_.getStruct();
                String o_ = EMPTY_STRING;
                try {
                    if (trloc_ != null) {
                        if (trloc_.getInstance() instanceof Translator) {
                            Bean bean_ = (Bean) _ip.getGlobalArgument().getStruct().getInstance();
                            o_ = ((Translator)trloc_.getInstance()).getString(_pattern, _conf, _files, bean_, s_.getInstance());
                        } else {
                            Struct bean_ = _ip.getGlobalArgument().getStruct();
                            LocalVariable lv_ = new LocalVariable();
                            String valName_ = _ip.getNextTempVar();
                            lv_ = new LocalVariable();
                            lv_.setStruct(trloc_);
                            lv_.setClassName(trloc_.getClassName(_conf.toContextEl()));
                            _ip.getLocalVars().put(valName_, lv_);
                            String patName_ = _ip.getNextTempVar();
                            lv_ = new LocalVariable();
                            lv_.setElement(_pattern);
                            lv_.setClassName(String.class.getName());
                            _ip.getLocalVars().put(patName_, lv_);
                            String navName_ = _ip.getNextTempVar();
                            lv_ = new LocalVariable();
                            lv_.setElement(_conf);
                            lv_.setClassName(Object.class.getName());
                            _ip.getLocalVars().put(navName_, lv_);
                            String filName_ = _ip.getNextTempVar();
                            lv_ = new LocalVariable();
                            lv_.setElement(_files);
                            lv_.setClassName(StringList.concat(StringMap.class.getName(), Templates.TEMPLATE_BEGIN, String.class.getName(), Templates.TEMPLATE_END));
                            _ip.getLocalVars().put(filName_, lv_);
                            String beanName_ = _ip.getNextTempVar();
                            lv_ = new LocalVariable();
                            lv_.setStruct(bean_);
                            lv_.setClassName(bean_.getClassName(_conf.toContextEl()));
                            _ip.getLocalVars().put(beanName_, lv_);
                            String objName_ = _ip.getNextTempVar();
                            lv_ = new LocalVariable();
                            lv_.setStruct(s_);
                            lv_.setClassName(Object.class.getName());
                            _ip.getLocalVars().put(objName_, lv_);
                            String expression_ = valName_ + GET_LOC_VAR+GET_STRING+BEGIN_ARGS;
                            expression_ += patName_+GET_LOC_VAR + SEP_ARGS;
                            expression_ += navName_+GET_LOC_VAR + SEP_ARGS;
                            expression_ += filName_+GET_LOC_VAR + SEP_ARGS;
                            expression_ += beanName_+GET_LOC_VAR + SEP_ARGS;
                            expression_ += objName_+GET_LOC_VAR + END_ARGS;
                            o_ = (String) ElUtil.processEl(expression_, 0, context_).getObject();
                        }
                    } else {
                        o_ = valueOf(_conf, s_);
                    }
                } catch (Throwable _0) {
                    _conf.getLastPage().setOffset(context_.getNextIndex());
                    throw new InvokeRedinedMethException(_conf.joinPages(), new StdStruct(_0));
                }
                str_.append(o_);
                i_ = context_.getNextIndex();
                continue;
            }
            if (cur_ == RIGHT_EL){
                _conf.getLastPage().setOffset(i_);
                throw new BadExpressionLanguageException(arg_.toString()+RETURN_LINE+_conf.joinPages());
            }
            str_.append(cur_);
            i_++;
        }
        return str_.toString();
    }

    static Object evaluateMathExpression(ImportingPage _ip, Configuration _conf, boolean _evalBool, String _mathExp) {
        MathFactory mathFact_ = getMathFactory(_conf);
        String numExpr_ = _mathExp;
        StringBuilder calculateVariables_ = new StringBuilder();
        int i_ = CustList.FIRST_INDEX;
        int len_ = numExpr_.length();
        boolean escaped_ = false;
        while (i_ < len_) {
            char curChar_ = numExpr_.charAt(i_);
            if (escaped_) {
                if (curChar_ == ESCAPED) {
                    escaped_ = false;
                    calculateVariables_.append(ESCAPED);
                    i_++;
                    continue;
                }
                if (curChar_ == MATH_INTERPRET) {
                    escaped_ = false;
                    calculateVariables_.append(MATH_INTERPRET);
                    i_++;
                    continue;
                }
                throw new BadExpressionLanguageException(numExpr_+RETURN_LINE+_conf.joinPages());
            }
            if (curChar_ == ESCAPED) {
                escaped_ = false;
                i_++;
                continue;
            }
            if (curChar_ == MATH_INTERPRET) {
                ContextEl context_ = _conf.toContextEl();
                _ip.setOffset(i_+1);
                Argument arg_ = ElUtil.processEl(numExpr_, context_, i_+1, MATH_INTERPRET, MATH_INTERPRET);
                try {
                    calculateVariables_.append(mathFact_.toString(arg_.getObject()));
                } catch (Throwable _0) {
                    throw new InvokeRedinedMethException(_conf.joinPages(), new StdStruct(_0));
                }
                i_ = context_.getNextIndex();
                continue;
            }
            calculateVariables_.append(curChar_);
            i_++;
        }
        numExpr_ = calculateVariables_.toString();
        try {
            Object ret_;
            if (_evalBool) {
                ret_ = mathFact_.evaluateDirectlyBoolean(numExpr_);
            } else {
                ret_ = mathFact_.evaluateDirectlyRate(numExpr_);
            }
            return ret_;
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new StdStruct(_0));
        }
    }

    static Object instanceByString(Configuration _conf, String _class, String _arg) {
        try {
            String name_ = _class;
            Object value_;
            LgNames stds_ = _conf.getStandards();
            String doublePrim_ = stds_.getAliasPrimDouble();
            String floatPrim_ = stds_.getAliasPrimFloat();
            String longPrim_ = stds_.getAliasPrimLong();
            String intPrim_ = stds_.getAliasPrimInteger();
            String shortPrim_ = stds_.getAliasPrimShort();
            String bytePrim_ = stds_.getAliasPrimByte();
            if (StringList.quickEq(name_, Integer.class.getName()) || StringList.quickEq(name_, intPrim_)) {
                value_ = Integer.parseInt(_arg);
            } else if (StringList.quickEq(name_, Long.class.getName()) || StringList.quickEq(name_, longPrim_)) {
                value_ = Long.parseLong(_arg);
            } else if (StringList.quickEq(name_, Short.class.getName()) || StringList.quickEq(name_, shortPrim_)) {
                value_ = Short.parseShort(_arg);
            } else if (StringList.quickEq(name_, Byte.class.getName()) || StringList.quickEq(name_, bytePrim_)) {
                value_ = Byte.parseByte(_arg);
            } else if (StringList.quickEq(name_, BigInteger.class.getName())) {
                value_ = new BigInteger(_arg);
            } else if (StringList.quickEq(name_, BigDecimal.class.getName())) {
                value_ = new BigDecimal(_arg);
            } else if (StringList.quickEq(name_, Double.class.getName()) || StringList.quickEq(name_, doublePrim_)) {
                value_ = Double.parseDouble(_arg);
            } else if (StringList.quickEq(name_, Float.class.getName()) || StringList.quickEq(name_, floatPrim_)) {
                value_ = Float.parseFloat(_arg);
            } else if (StringList.quickEq(name_, AtomicInteger.class.getName())) {
                value_ = new AtomicInteger(Integer.parseInt(_arg));
            } else if (StringList.quickEq(name_, AtomicLong.class.getName())) {
                value_ = new AtomicLong(Long.parseLong(_arg));
            } else {
                String escaped_ = StringList.replace(_arg, QUOTE_DOUBLE, ESCAPED+QUOTE_DOUBLE);
                String instanciation_ = INSTANCE+_class+BEGIN_ARGS+QUOTE_DOUBLE+escaped_+QUOTE_DOUBLE+END_ARGS;
                Object obj_ = ElUtil.processEl(instanciation_, 0, _conf.toContextEl()).getObject();
                if (obj_ == null) {
                    throw new RuntimeInstantiationException(EMPTY_STRING);
                }
                return obj_;
            }
            return value_;
        } catch (Throwable _0) {
            throw new InvokingException(_0, _conf.joinPages());
        }
    }
    static void checkClassNotEmptyName(Configuration _conf, int _offest, String _className) {
        if (_className.isEmpty()) {
            return;
        }
        classNameForName(_conf, _offest, _className);
    }
    static String classNameForName(Configuration _conf, int _offest, String _className) {
        if (_conf.getStandards().getStandards().contains(_className)) {
            return _className;
        }
        try {
            if (PrimitiveTypeUtil.isPrimitive(_className, _conf.toContextEl())) {
                if (!PrimitiveTypeUtil.isExistentPrimitive(_className, _conf.toContextEl())) {
                    throw new RuntimeClassNotFoundException(_className+RETURN_LINE+_conf.joinPages());
                }
                return _className;
            }
            if (StringList.quickEq(_className, ConstClasses.LISTABLE_ALIAS)) {
                return Listable.class.getName();
            }
            if (StringList.quickEq(_className, ConstClasses.LISTABLE_ENTRIES_ALIAS)) {
                return ListableEntries.class.getName();
            }
            String className_ = ConstClasses.getMapping(_className);
            if (className_ != null) {
                PrimitiveTypeUtil.getSingleNativeClass(className_);
                return className_;
            }
            PrimitiveTypeUtil.getSingleNativeClass(_className);
            return _className;
        } catch (Throwable _0) {
            _conf.getLastPage().addToOffset(_offest);
            throw new RuntimeClassNotFoundException(_className+RETURN_LINE+_conf.joinPages());
        }
    }
    static void beforeDiplaying(Configuration _conf, Struct _it, boolean _addpage) {
        if (_it == null) {
            return;
        }
        if (_it.isNull()) {
            return;
        }
        if (_addpage) {
            _conf.addPage(new ImportingPage(false));
        }
        getResult(_conf, 0, BEFORE_DISPLAYING, _it);
        if (_addpage) {
            _conf.removeLastPage();
        }
    }

    static Struct getDataBase(Configuration _conf, Struct _it) {
        Object instance_ = _it.getInstance();
        if (instance_ instanceof Bean) {
            Bean inst_ = (Bean) instance_;
            Struct out_ = StdStruct.wrapStd(inst_.getDataBase());
            return out_;
        }
        return getResult(_conf, 0, GET_DATA_BASE, _it);
    }

    static void setDataBase(Configuration _conf, Struct _it, Struct _dataBase) {
        Object instance_ = _it.getInstance();
        if (instance_ instanceof Bean) {
            Bean inst_ = (Bean) instance_;
            inst_.setDataBase(_dataBase.getInstance());
            return;
        }
        setResult(_conf, 0, SET_DATA_BASE, _it, _dataBase, Object.class.getName());
    }

    static String getLanguage(Configuration _conf, Struct _it) {
        Object instance_ = _it.getInstance();
        if (instance_ instanceof Bean) {
            Bean inst_ = (Bean) instance_;
            return inst_.getLanguage();
        }
        return (String) getResult(_conf, 0, GET_LANGUAGE, _it).getInstance();
    }

    static void setLanguage(Configuration _conf, Struct _it, String _scope) {
        Object instance_ = _it.getInstance();
        if (instance_ instanceof Bean) {
            Bean inst_ = (Bean) instance_;
            inst_.setLanguage(_scope);
            return;
        }
        setResult(_conf, 0, SET_LANGUAGE, _it, new StdStruct(_scope), String.class.getName());
    }

    static String getScope(Configuration _conf, Struct _it) {
        Object instance_ = _it.getInstance();
        if (instance_ instanceof Bean) {
            Bean inst_ = (Bean) instance_;
            return inst_.getScope();
        }
        return (String) getResult(_conf, 0, GET_SCOPE, _it).getInstance();
    }

    static void setScope(Configuration _conf, Struct _it, String _scope) {
        Object instance_ = _it.getInstance();
        if (instance_ instanceof Bean) {
            Bean inst_ = (Bean) instance_;
            inst_.setScope(_scope);
            return;
        }
        setResult(_conf, 0, SET_SCOPE, _it, new StdStruct(_scope), String.class.getName());
    }

    static Struct getForms(Configuration _conf, Struct _it) {
        Object instance_ = _it.getInstance();
        if (instance_ instanceof Bean) {
            Bean inst_ = (Bean) instance_;
            Struct out_ = StdStruct.wrapStd(inst_.getForms());
            return out_;
        }
        return getResult(_conf, 0, GET_FORMS, _it);
    }

    static void setForms(Configuration _conf, Struct _it, Struct _forms) {
        Object instance_ = _it.getInstance();
        if (instance_ instanceof Bean) {
            Bean inst_ = (Bean) instance_;
            inst_.setForms((StringMapObject) _forms.getInstance());
            return;
        }
        setResult(_conf, 0, SET_FORMS, _it, _forms, StringMapObject.class.getName());
    }

    static Struct getKey(Configuration _conf, Struct _it) {
        Object instance_ = _it.getInstance();
        if (instance_ instanceof SimpleEntry) {
            SimpleEntry inst_ = (SimpleEntry) instance_;
            Struct out_ = StdStruct.wrapStd(inst_.getKey());
            return out_;
        }
        return getResult(_conf, 0, GET_KEY, _it);
    }

    static Struct getValue(Configuration _conf, Struct _it) {
        Object instance_ = _it.getInstance();
        if (instance_ instanceof SimpleEntry) {
            SimpleEntry inst_ = (SimpleEntry) instance_;
            Struct out_ = StdStruct.wrapStd(inst_.getValue());
            return out_;
        }
        return getResult(_conf, 0, GET_VALUE, _it);
    }

    static char getChar(Configuration _conf, String _obj) {
        if (_obj.length() != CustList.ONE_ELEMENT) {
            throw new CharacterFormatException(String.valueOf(CustList.ONE_ELEMENT)+RETURN_LINE+_conf.joinPages());
        }
        return _obj.charAt(CustList.FIRST_INDEX);
    }
    /**This method use the equal operator*/
    static boolean eq(Configuration _conf, Struct _objOne, Struct _objTwo) {
        try {
            if (_objOne.isNull()) {
                if (_objTwo.isNull()) {
                    return true;
                }
                return false;
            }
            if (_objTwo.isNull()) {
                return false;
            }
            ImportingPage ip_ = _conf.getLastPage();
            LocalVariable lvOne_ = new LocalVariable();
            lvOne_.setClassName(ConstClasses.resolve(_objOne.getClassName(_conf.toContextEl())));
            lvOne_.setStruct(_objOne);
            String nameOne_ = ip_.getNextTempVar();
            ip_.getLocalVars().put(nameOne_, lvOne_);
            LocalVariable lvTwo_ = new LocalVariable();
            lvTwo_.setClassName(ConstClasses.resolve(_objTwo.getClassName(_conf.toContextEl())));
            lvTwo_.setStruct(_objTwo);
            String nameTwo_ = ip_.getNextTempVar();
            ip_.getLocalVars().put(nameTwo_, lvTwo_);
            Argument arg_ = ElUtil.processEl(nameOne_+GET_LOC_VAR+CMP+nameTwo_+GET_LOC_VAR, 0, _conf.toContextEl());
            Boolean ret_ = (Boolean)arg_.getObject();
            ip_.getLocalVars().removeKey(nameOne_);
            ip_.getLocalVars().removeKey(nameTwo_);
            return ret_;
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new StdStruct(_0));
        }
    }
    static void checkNullPointer(Configuration _conf, Object _obj) {
        if (_obj == null) {
            throw new NullObjectException(_conf.joinPages());
        }
    }
    static String valueOf(Configuration _conf, Struct _obj) {
        if (_obj.isNull()) {
            return String.valueOf(_obj.getInstance());
        }
        return toString(_conf, _obj);
    }
    static String toString(Configuration _conf, Struct _obj) {
        Object instance_ = _obj.getInstance();
        String method_;
        if (instance_ instanceof Displayable) {
            method_ = _conf.getStandards().getAliasDisplay();
        } else {
            method_ = _conf.getStandards().getAliasToString();
        }
        return (String) getResult(_conf, 0, method_, _obj).getInstance();
    }
    static Struct iterator(Configuration _conf, Struct _it) {
        Object instance_ = _it.getInstance();
        if (instance_ instanceof SimpleIterable) {
            SimpleIterable inst_ = (SimpleIterable) instance_;
            Struct out_ = StdStruct.wrapStd(inst_.simpleIterator());
            return out_;
        }
        return getResult(_conf, 0, ITERATOR, _it);
    }
    static boolean hasNext(Configuration _conf, Struct _it) {
        Object instance_ = _it.getInstance();
        if (instance_ instanceof SimpleItr) {
            SimpleItr inst_ = (SimpleItr) instance_;
            return inst_.hasNext();
        }
        return (Boolean) getResult(_conf, 0, HAS_NEXT, _it).getInstance();
    }
    static Struct next(Configuration _conf, Struct _it) {
        Object instance_ = _it.getInstance();
        if (instance_ instanceof SimpleItr) {
            SimpleItr inst_ = (SimpleItr) instance_;
            Struct out_ = StdStruct.wrapStd(inst_.next());
            return out_;
        }
        return getResult(_conf, 0, NEXT, _it);
    }
    static Struct entryList(Configuration _conf, int _offsIndex, Struct _container) {
        Object instance_ = _container.getInstance();
        if (instance_ instanceof SimpleEntries) {
            SimpleEntries inst_ = (SimpleEntries) _container.getInstance();
            Struct out_ = StdStruct.wrapStd(inst_.entries());
            return out_;
        }
        return getResult(_conf, 0, ENTRY_LIST, _container);
    }

    static String name(Configuration _conf, Struct _instance) {
        return (String) getResult(_conf, 0, NAME, _instance).getInstance();
    }

    private static Struct getResult(Configuration _conf, int _offsIndex, String _methodName, Struct _instance) {
        ImportingPage ip_ = _conf.getLastPage();
        String varName_ = ip_.getNextTempVar();
        LocalVariable var_ = new LocalVariable();
        var_.setStruct(_instance);
        var_.setClassName(_instance.getClassName(_conf.toContextEl()));
        ip_.getLocalVars().put(varName_, var_);
        String expression_ = varName_+GET_LOC_VAR+_methodName+NO_PARAM_METHOD;
        try {
            Struct str_ = ElUtil.processEl(expression_, 0, _conf.toContextEl()).getStruct();
            ip_.getLocalVars().removeKey(varName_);
            return str_;
        } catch (Throwable _0) {
            ip_.getLocalVars().removeKey(varName_);
            _conf.getLastPage().addToOffset(_offsIndex);
            throw new InvokeRedinedMethException(_conf.joinPages(), new StdStruct(_0));
        }
    }
    private static void setResult(Configuration _conf, int _offsIndex, String _methodName, Struct _instance, Struct _argument,
            String _argumentClassName) {
        ImportingPage ip_ = _conf.getLastPage();
        String varName_ = ip_.getNextTempVar();
        LocalVariable var_ = new LocalVariable();
        var_.setStruct(_instance);
        var_.setClassName(_instance.getClassName(_conf.toContextEl()));
        ip_.getLocalVars().put(varName_, var_);
        String argName_ = ip_.getNextTempVar();
        var_ = new LocalVariable();
        var_.setStruct(_argument);
        var_.setClassName(_argumentClassName);
        ip_.getLocalVars().put(argName_, var_);
        String expression_ = varName_+GET_LOC_VAR+_methodName+BEGIN_ARGS+argName_+GET_LOC_VAR+END_ARGS;
        try {
            ElUtil.processEl(expression_, 0, _conf.toContextEl()).getStruct();
            ip_.getLocalVars().removeKey(varName_);
        } catch (Throwable _0) {
            ip_.getLocalVars().removeKey(varName_);
            _conf.getLastPage().addToOffset(_offsIndex);
            throw new InvokeRedinedMethException(_conf.joinPages(), new StdStruct(_0));
        }
    }

    static LoopVariable getCurrentVariable(Configuration _conf, int _offset,StringMap<LoopVariable> _vars, String _candidate) {
        if (!_vars.contains(_candidate)) {
            _conf.getLastPage().addToOffset(_offset);
            throw new UndefinedVariableException(_conf.joinPages(), _candidate, LOOP_MESSAGE);
        }
        return _vars.getVal(_candidate);
    }

    static LocalVariable getCurrentLocVariable(Configuration _conf, int _offset,StringMap<LocalVariable> _vars, String _candidate) {
        if (!_vars.contains(_candidate)) {
            _conf.getLastPage().addToOffset(_offset);
            throw new UndefinedVariableException(_conf.joinPages(), _candidate, LOCAL_MESSAGE);
        }
        return _vars.getVal(_candidate);
    }

    static String formatMessage(Configuration _conf,String _loc, ImportingPage _ip, Element _element, StringMap<String> _files, String... _resourcesFolder) {
        String value_ = _element.getAttribute(ATTRIBUTE_VALUE);
        if (!_conf.noPages()) {
            _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VALUE);
            _conf.getLastPage().setLookForAttrValue(true);
        }
        String preformatted_;
        StringList objects_ = new StringList();
        if (value_.startsWith(CALL_METHOD)) {
            if (!_conf.noPages()) {
                _conf.getLastPage().setOffset(1);
            }
            preformatted_ = toString(_conf, ElUtil.processEl(value_, 1, _conf.toContextEl()).getStruct());
            if (!_conf.noPages()) {
                _conf.getLastPage().setKey(EMPTY_STRING);
                _conf.getLastPage().setMessageValue(preformatted_);
            }
        } else {
            StringList elts_ = StringList.splitStrings(value_, COMMA);
            String var_ = elts_.first();
            String fileName_ = getProperty(_conf, var_);
            if (fileName_ == null) {
                fileName_ = var_;
            }
            String key_ = elts_.last();
            if (!_conf.noPages()) {
                _conf.getLastPage().setOffset(var_.length()+COMMA.length());
            }
            key_ = formatNumVariables(key_, _conf, _ip, _files);
            StringMap<String> messages_ = ExtractFromResources.getInnerMessagesFromLocaleClass(_conf, _loc, fileName_, _files, _resourcesFolder);
            preformatted_ = ExtractFromResources.getFormat(messages_, key_, _conf, _loc, fileName_);
            if (!_conf.noPages()) {
                _conf.getLastPage().setKey(key_);
                _conf.getLastPage().setMessageValue(preformatted_);
            }
            preformatted_ = DocumentBuilder.transformSpecialChars(preformatted_, _element.hasAttribute(ATTRIBUTE_ESCAPED_EAMP));
        }
        for (Element n: _element.getChildElements()) {
            String attribute_ = n.getAttribute(ATTRIBUTE_VALUE);
            if (n.hasAttribute(ATTRIBUTE_QUOTED)) {
                if (n.hasAttribute(ATTRIBUTE_ESCAPED)) {
                    objects_.add(escapeParam(_conf, attribute_));
                } else {
                    objects_.add(attribute_);
                }
                continue;
            }
            if (!_conf.noPages()) {
                _conf.getLastPage().setProcessingNode(n);
                _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VALUE);
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(0);
            }
            int begin_ = 0;
            if (attribute_.startsWith(CALL_METHOD)) {
                begin_ = 1;
            }
            Struct o_ = ElUtil.processEl(attribute_, begin_, _conf.toContextEl()).getStruct();
            if (n.hasAttribute(ATTRIBUTE_ESCAPED)) {
                objects_.add(escapeParam(_conf,o_));
            } else {
                objects_.add(toString(_conf, o_));
            }
        }
        return StringList.simpleStringsFormat(preformatted_, objects_.toArray());
    }

    private static String escapeParam(Configuration _conf, String _arg) {
        StringMap<String> rep_ = new StringMap<String>();
        String quote_ = String.valueOf(QUOTE);
        rep_.put(String.valueOf(LEFT_EL), quote_+LEFT_EL+quote_);
        rep_.put(String.valueOf(RIGHT_EL), quote_+RIGHT_EL+quote_);
        rep_.put(String.valueOf(QUOTE), quote_+quote_);
        return StringList.replaceMultiple(_arg, rep_);
    }

    private static String escapeParam(Configuration _conf, Struct _arg) {
        String str_ = valueOf(_conf, _arg);
        StringMap<String> rep_ = new StringMap<String>();
        String quote_ = String.valueOf(QUOTE);
        rep_.put(String.valueOf(LEFT_EL), quote_+LEFT_EL+quote_);
        rep_.put(String.valueOf(RIGHT_EL), quote_+RIGHT_EL+quote_);
        rep_.put(String.valueOf(QUOTE), quote_+quote_);
        return StringList.replaceMultiple(str_, rep_);
    }

    static String getMessageFolder(Configuration _conf) {
        try {
            return _conf.getMessagesFolder();
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new StdStruct(_0));
        }
    }
    private static MathFactory getMathFactory(Configuration _conf) {
        try {
            return _conf.getMathFactory();
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new StdStruct(_0));
        }
    }
    static String getProperty(Configuration _conf, String _key) {
        try {
            return _conf.getProperties().getVal(_key);
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new StdStruct(_0));
        }
    }
}
