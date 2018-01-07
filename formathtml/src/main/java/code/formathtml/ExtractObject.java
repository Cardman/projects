package code.formathtml;
import code.bean.Bean;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.exceptions.BadExpressionLanguageException;
import code.expressionlanguage.exceptions.InvokeRedinedMethException;
import code.expressionlanguage.exceptions.UndefinedVariableException;
import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.ByteStruct;
import code.expressionlanguage.opers.util.DoubleStruct;
import code.expressionlanguage.opers.util.EnumStruct;
import code.expressionlanguage.opers.util.FloatStruct;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.LongStruct;
import code.expressionlanguage.opers.util.ShortStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.StringStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.formathtml.exceptions.CharacterFormatException;
import code.formathtml.exceptions.InexistingTranslatorException;
import code.formathtml.util.StringMapObjectStruct;
import code.formathtml.util.TranslatorStruct;
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
    static String formatNumVariables(String _pattern, Configuration _conf, ImportingPage _ip) {
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
                        throw new BadExpressionLanguageException(StringList.concat(arg_.toString(),RETURN_LINE,_conf.joinPages()));
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
                    throw new BadExpressionLanguageException(StringList.concat(arg_.toString(),RETURN_LINE,_conf.joinPages()));
                }
                ContextEl context_ = _conf.toContextEl();
                Struct trloc_ = null;
                if (!tr_.toString().isEmpty()) {
                    try {
                        trloc_ = _conf.getBuiltTranslators().getVal(tr_.toString());
                        if (trloc_ == null) {
                            _conf.getLastPage().setOffset(i_);
                            throw new InexistingTranslatorException(StringList.concat(tr_,RETURN_LINE,_conf.joinPages()));
                        }
                    } catch (Throwable _0) {
                        _conf.getLastPage().setOffset(i_);
                        throw new InexistingTranslatorException(StringList.concat(tr_,RETURN_LINE,_conf.joinPages()));
                    }
                }
                _conf.getLastPage().setOffset(i_);
                Argument argloc_ = ElUtil.processEl(_pattern, context_, i_, LEFT_EL, RIGHT_EL);
                Struct s_ = argloc_.getStruct();
                String o_ = EMPTY_STRING;
                try {
                    if (trloc_ != null) {
                        if (trloc_ instanceof TranslatorStruct) {
                            Bean bean_ = (Bean) _ip.getGlobalArgument().getStruct().getInstance();
                            o_ = ((TranslatorStruct)trloc_).getInstance().getString(_pattern, _conf, bean_, s_.getInstance());
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
                            lv_.setElement(_conf, Object.class.getName());
                            lv_.setClassName(Object.class.getName());
                            _ip.getLocalVars().put(navName_, lv_);
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
                            StringBuilder expression_ = new StringBuilder(valName_).append(GET_LOC_VAR).append(GET_STRING).append(BEGIN_ARGS);
                            expression_.append(patName_).append(GET_LOC_VAR).append(SEP_ARGS);
                            expression_.append(navName_).append(GET_LOC_VAR).append(SEP_ARGS);
                            expression_.append(beanName_).append(GET_LOC_VAR).append(SEP_ARGS);
                            expression_.append(objName_).append(GET_LOC_VAR).append(END_ARGS);
                            o_ = (String) ElUtil.processEl(expression_.toString(), 0, context_).getObject();
                        }
                    } else {
                        o_ = valueOf(_conf, s_);
                    }
                } catch (Throwable _0) {
                    _conf.getLastPage().setOffset(context_.getNextIndex());
                    String err_ = _conf.getStandards().getAliasError();
                    throw new InvokeRedinedMethException(_conf.joinPages(), new StdStruct(new CustomError(_conf.joinPages()),err_));
                }
                str_.append(o_);
                i_ = context_.getNextIndex();
                continue;
            }
            if (cur_ == RIGHT_EL){
                _conf.getLastPage().setOffset(i_);
                throw new BadExpressionLanguageException(StringList.concat(arg_.toString(),RETURN_LINE,_conf.joinPages()));
            }
            str_.append(cur_);
            i_++;
        }
        return str_.toString();
    }

    static Struct evaluateMathExpression(ImportingPage _ip, Configuration _conf, boolean _evalBool, String _mathExp) {
        MathFactory mathFact_ = getMathFactory(_conf);
        String rateClass_ = _conf.getStandards().getAliasRate();
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
                throw new BadExpressionLanguageException(StringList.concat(numExpr_,RETURN_LINE,_conf.joinPages()));
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
                    String err_ = _conf.getStandards().getAliasError();
                    throw new InvokeRedinedMethException(_conf.joinPages(), new StdStruct(new CustomError(_conf.joinPages()),err_));
                }
                i_ = context_.getNextIndex();
                continue;
            }
            calculateVariables_.append(curChar_);
            i_++;
        }
        numExpr_ = calculateVariables_.toString();
        try {
            if (_evalBool) {
                return new BooleanStruct(mathFact_.evaluateDirectlyBoolean(numExpr_));
            } else {
                return new StdStruct(mathFact_.evaluateDirectlyRate(numExpr_), rateClass_);
            }
        } catch (Throwable _0) {
            String err_ = _conf.getStandards().getAliasError();
            throw new InvokeRedinedMethException(_conf.joinPages(), new StdStruct(new CustomError(_conf.joinPages()),err_));
        }
    }

    static Struct instanceByString(Configuration _conf, String _class, String _arg) {
        try {
            String name_ = _class;
            LgNames stds_ = _conf.getStandards();
            String doublePrim_ = stds_.getAliasPrimDouble();
            String floatPrim_ = stds_.getAliasPrimFloat();
            String longPrim_ = stds_.getAliasPrimLong();
            String intPrim_ = stds_.getAliasPrimInteger();
            String shortPrim_ = stds_.getAliasPrimShort();
            String bytePrim_ = stds_.getAliasPrimByte();
            if (StringList.quickEq(name_, Integer.class.getName()) || StringList.quickEq(name_, intPrim_)) {
                return new IntStruct(Integer.parseInt(_arg));
            } else if (StringList.quickEq(name_, Long.class.getName()) || StringList.quickEq(name_, longPrim_)) {
                return new LongStruct(Long.parseLong(_arg));
            } else if (StringList.quickEq(name_, Short.class.getName()) || StringList.quickEq(name_, shortPrim_)) {
                return new ShortStruct(Short.parseShort(_arg));
            } else if (StringList.quickEq(name_, Byte.class.getName()) || StringList.quickEq(name_, bytePrim_)) {
                return new ByteStruct(Byte.parseByte(_arg));
            } else if (StringList.quickEq(name_, Double.class.getName()) || StringList.quickEq(name_, doublePrim_)) {
                return new DoubleStruct(Double.parseDouble(_arg));
            } else if (StringList.quickEq(name_, Float.class.getName()) || StringList.quickEq(name_, floatPrim_)) {
                return new FloatStruct(Float.parseFloat(_arg));
            } else {
                String escaped_ = StringList.replace(_arg, QUOTE_DOUBLE, StringList.concat(String.valueOf(ESCAPED),QUOTE_DOUBLE));
                String instanciation_ = StringList.concat(INSTANCE,_class,String.valueOf(BEGIN_ARGS),QUOTE_DOUBLE,escaped_,QUOTE_DOUBLE,String.valueOf(END_ARGS));
                Argument obj_ = ElUtil.processEl(instanciation_, 0, _conf.toContextEl());
                if (obj_.isNull()) {
                    throw new RuntimeInstantiationException(EMPTY_STRING);
                }
                return obj_.getStruct();
            }
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
    static void classNameForName(Configuration _conf, int _offest, String _className) {
        if (_conf.getStandards().getStandards().contains(_className)) {
            return;
        }
        try {
            if (PrimitiveTypeUtil.isPrimitive(_className, _conf.toContextEl())) {
                if (!PrimitiveTypeUtil.isExistentPrimitive(_className, _conf.toContextEl())) {
                    throw new RuntimeClassNotFoundException(StringList.concat(_className,RETURN_LINE,_conf.joinPages()));
                }
                return;
            }
            if (StringList.quickEq(_className, ConstClasses.LISTABLE_ALIAS)) {
                return;
            }
            if (StringList.quickEq(_className, ConstClasses.LISTABLE_ENTRIES_ALIAS)) {
                return;
            }
            String className_ = ConstClasses.getMapping(_className);
            if (className_ != null) {
                PrimitiveTypeUtil.getSingleNativeClass(className_);
                return;
            }
            PrimitiveTypeUtil.getSingleNativeClass(_className);
            return;
        } catch (Throwable _0) {
            _conf.getLastPage().addToOffset(_offest);
            throw new RuntimeClassNotFoundException(StringList.concat(_className,RETURN_LINE,_conf.joinPages()));
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
        if (_conf.toContextEl().getClasses() == null) {
            Object instance_ = _it.getInstance();
            Bean inst_ = (Bean) instance_;
            Struct out_ = new StdStruct(inst_.getDataBase(), _conf.getStandards().getAliasObject());
            return out_;
        }
        return getResult(_conf, 0, GET_DATA_BASE, _it);
    }

    static void setDataBase(Configuration _conf, Struct _it, Struct _dataBase) {
        if (_conf.toContextEl().getClasses() == null) {
            Object instance_ = _it.getInstance();
            Bean inst_ = (Bean) instance_;
            inst_.setDataBase(_dataBase.getInstance());
            return;
        }
        setResult(_conf, 0, SET_DATA_BASE, _it, _dataBase, Object.class.getName());
    }

    static String getLanguage(Configuration _conf, Struct _it) {
        if (_conf.toContextEl().getClasses() == null) {
            Object instance_ = _it.getInstance();
            Bean inst_ = (Bean) instance_;
            return inst_.getLanguage();
        }
        return (String) getResult(_conf, 0, GET_LANGUAGE, _it).getInstance();
    }

    static void setLanguage(Configuration _conf, Struct _it, String _scope) {
        if (_conf.toContextEl().getClasses() == null) {
            Object instance_ = _it.getInstance();
            Bean inst_ = (Bean) instance_;
            inst_.setLanguage(_scope);
            return;
        }
        setResult(_conf, 0, SET_LANGUAGE, _it, new StringStruct(_scope), String.class.getName());
    }

    static String getScope(Configuration _conf, Struct _it) {
        if (_conf.toContextEl().getClasses() == null) {
            Object instance_ = _it.getInstance();
            Bean inst_ = (Bean) instance_;
            return inst_.getScope();
        }
        return (String) getResult(_conf, 0, GET_SCOPE, _it).getInstance();
    }

    static void setScope(Configuration _conf, Struct _it, String _scope) {
        if (_conf.toContextEl().getClasses() == null) {
            Object instance_ = _it.getInstance();
            Bean inst_ = (Bean) instance_;
            inst_.setScope(_scope);
            return;
        }
        setResult(_conf, 0, SET_SCOPE, _it, new StringStruct(_scope), String.class.getName());
    }

    static Struct getForms(Configuration _conf, Struct _it) {
        if (_conf.toContextEl().getClasses() == null) {
            Object instance_ = _it.getInstance();
            Bean inst_ = (Bean) instance_;
            return new StringMapObjectStruct(inst_.getForms());
        }
        return getResult(_conf, 0, GET_FORMS, _it);
    }

    static void setForms(Configuration _conf, Struct _it, Struct _forms) {
        if (_conf.toContextEl().getClasses() == null) {
            Object instance_ = _it.getInstance();
            Bean inst_ = (Bean) instance_;
            inst_.setForms((StringMapObject) _forms.getInstance());
            return;
        }
        setResult(_conf, 0, SET_FORMS, _it, _forms, StringMapObject.class.getName());
    }

    static Struct getKey(Configuration _conf, Struct _it) {
        if (_conf.toContextEl().getClasses() == null) {
            Object instance_ = _it.getInstance();
            SimpleEntry inst_ = (SimpleEntry) instance_;
            Struct out_ = StdStruct.wrapStd(inst_.getKey());
            return out_;
        }
        return getResult(_conf, 0, GET_KEY, _it);
    }

    static Struct getValue(Configuration _conf, Struct _it) {
        if (_conf.toContextEl().getClasses() == null) {
            Object instance_ = _it.getInstance();
            SimpleEntry inst_ = (SimpleEntry) instance_;
            Struct out_ = StdStruct.wrapStd(inst_.getValue());
            return out_;
        }
        return getResult(_conf, 0, GET_VALUE, _it);
    }

    static char getChar(Configuration _conf, String _obj) {
        if (_obj.length() != CustList.ONE_ELEMENT) {
            throw new CharacterFormatException(StringList.concat(String.valueOf(CustList.ONE_ELEMENT),RETURN_LINE,_conf.joinPages()));
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
            Argument arg_ = ElUtil.processEl(StringList.concat(nameOne_,GET_LOC_VAR,CMP,nameTwo_,GET_LOC_VAR), 0, _conf.toContextEl());
            Boolean ret_ = (Boolean)arg_.getObject();
            ip_.getLocalVars().removeKey(nameOne_);
            ip_.getLocalVars().removeKey(nameTwo_);
            return ret_;
        } catch (Throwable _0) {
            String err_ = _conf.getStandards().getAliasError();
            throw new InvokeRedinedMethException(_conf.joinPages(), new StdStruct(new CustomError(_conf.joinPages()),err_));
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
        if (_obj.getInstance() instanceof String) {
            return (String) _obj.getInstance();
        }
        ContextEl context_ = _conf.toContextEl();
        String method_;
        if (context_.getClasses() != null) {
            String param_ = context_.getStandards().getAliasDisplayable();
            String arg_ = _obj.getClassName(context_);
            Mapping map_ = new Mapping();
            map_.setArg(arg_);
            map_.setParam(param_);
            if (Templates.isCorrect(map_, context_)) {
                method_ = _conf.getStandards().getAliasDisplay();
            }  else {
                method_ = _conf.getStandards().getAliasToString();
            }
        } else {
            Object instance_ = _obj.getInstance();
            if (instance_ instanceof Displayable) {
                method_ = _conf.getStandards().getAliasDisplay();
            } else {
                method_ = _conf.getStandards().getAliasToString();
            }
        }
        return (String) getResult(_conf, 0, method_, _obj).getInstance();
    }
    static Struct iterator(Configuration _conf, Struct _it) {
        if (_conf.toContextEl().getClasses() == null) {
            Object instance_ = _it.getInstance();
            SimpleIterable inst_ = (SimpleIterable) instance_;
            Struct out_ = StdStruct.wrapStd(inst_.simpleIterator(), _conf.getStandards().getAliasIteratorType());
            return out_;
        }
        return getResult(_conf, 0, ITERATOR, _it);
    }
    static boolean hasNext(Configuration _conf, Struct _it) {
        if (_conf.toContextEl().getClasses() == null) {
            Object instance_ = _it.getInstance();
            SimpleItr inst_ = (SimpleItr) instance_;
            return inst_.hasNext();
        }
        return (Boolean) getResult(_conf, 0, HAS_NEXT, _it).getInstance();
    }
    static Struct next(Configuration _conf, Struct _it) {
        if (_conf.toContextEl().getClasses() == null) {
            Object instance_ = _it.getInstance();
            SimpleItr inst_ = (SimpleItr) instance_;
            Struct out_ = StdStruct.wrapStd(inst_.next());
            return out_;
        }
        return getResult(_conf, 0, NEXT, _it);
    }
    static Struct entryList(Configuration _conf, int _offsIndex, Struct _container) {
        if (_conf.toContextEl().getClasses() == null) {
            SimpleEntries inst_ = (SimpleEntries) _container.getInstance();
            Struct out_ = StdStruct.wrapStd(inst_.entries(), _conf.getStandards().getCustEntries());
            return out_;
        }
        return getResult(_conf, 0, ENTRY_LIST, _container);
    }

    static String getStringKey(Configuration _conf, Struct _instance) {
        ContextEl cont_ = _conf.toContextEl();
        if (cont_.getClasses() == null) {
            if (_instance.getInstance().getClass().isEnum()) {
                return (String) getResult(_conf, 0, NAME, _instance).getInstance();
            }
            return toString(_conf, _instance);
        }
        if (_instance instanceof EnumStruct) {
            return ((EnumStruct) _instance).getName();
        }
        ResultErrorStd res_ = _conf.getStandards().getName(cont_, _instance);
        if (res_.getError() != null) {
            throw new InvokeRedinedMethException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
        }
        return toString(_conf, res_.getResult());
    }
    private static Struct getResult(Configuration _conf, int _offsIndex, String _methodName, Struct _instance) {
        ImportingPage ip_ = _conf.getLastPage();
        String varName_ = ip_.getNextTempVar();
        LocalVariable var_ = new LocalVariable();
        var_.setStruct(_instance);
        var_.setClassName(_instance.getClassName(_conf.toContextEl()));
        ip_.getLocalVars().put(varName_, var_);
        String expression_ = StringList.concat(varName_,GET_LOC_VAR,_methodName,NO_PARAM_METHOD);
        try {
            Struct str_ = ElUtil.processEl(expression_, 0, _conf.toContextEl()).getStruct();
            ip_.getLocalVars().removeKey(varName_);
            return str_;
        } catch (Throwable _0) {
            ip_.getLocalVars().removeKey(varName_);
            _conf.getLastPage().addToOffset(_offsIndex);
            String err_ = _conf.getStandards().getAliasError();
            throw new InvokeRedinedMethException(_conf.joinPages(), new StdStruct(new CustomError(_conf.joinPages()),err_));
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
        String expression_ = StringList.concat(varName_,GET_LOC_VAR,_methodName,String.valueOf(BEGIN_ARGS),argName_,GET_LOC_VAR,String.valueOf(END_ARGS));
        try {
            ElUtil.processEl(expression_, 0, _conf.toContextEl()).getStruct();
            ip_.getLocalVars().removeKey(varName_);
        } catch (Throwable _0) {
            ip_.getLocalVars().removeKey(varName_);
            _conf.getLastPage().addToOffset(_offsIndex);
            String err_ = _conf.getStandards().getAliasError();
            throw new InvokeRedinedMethException(_conf.joinPages(), new StdStruct(new CustomError(_conf.joinPages()),err_));
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
            key_ = formatNumVariables(key_, _conf, _ip);
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
        rep_.put(String.valueOf(LEFT_EL), StringList.concat(quote_,String.valueOf(LEFT_EL),quote_));
        rep_.put(String.valueOf(RIGHT_EL), StringList.concat(quote_,String.valueOf(RIGHT_EL),quote_));
        rep_.put(String.valueOf(QUOTE), StringList.concat(quote_,quote_));
        return StringList.replaceMultiple(_arg, rep_);
    }

    private static String escapeParam(Configuration _conf, Struct _arg) {
        String str_ = valueOf(_conf, _arg);
        StringMap<String> rep_ = new StringMap<String>();
        String quote_ = String.valueOf(QUOTE);
        rep_.put(String.valueOf(LEFT_EL), StringList.concat(quote_,String.valueOf(LEFT_EL),quote_));
        rep_.put(String.valueOf(RIGHT_EL), StringList.concat(quote_,String.valueOf(RIGHT_EL),quote_));
        rep_.put(String.valueOf(QUOTE), StringList.concat(quote_,quote_));
        return StringList.replaceMultiple(str_, rep_);
    }

    static String getMessageFolder(Configuration _conf) {
        try {
            return _conf.getMessagesFolder();
        } catch (Throwable _0) {
            String err_ = _conf.getStandards().getAliasError();
            throw new InvokeRedinedMethException(_conf.joinPages(), new StdStruct(new CustomError(_conf.joinPages()),err_));
        }
    }
    private static MathFactory getMathFactory(Configuration _conf) {
        try {
            return _conf.getMathFactory();
        } catch (Throwable _0) {
            String err_ = _conf.getStandards().getAliasError();
            throw new InvokeRedinedMethException(_conf.joinPages(), new StdStruct(new CustomError(_conf.joinPages()),err_));
        }
    }
    static String getProperty(Configuration _conf, String _key) {
        try {
            return _conf.getProperties().getVal(_key);
        } catch (Throwable _0) {
            String err_ = _conf.getStandards().getAliasError();
            throw new InvokeRedinedMethException(_conf.joinPages(), new StdStruct(new CustomError(_conf.joinPages()),err_));
        }
    }
}
