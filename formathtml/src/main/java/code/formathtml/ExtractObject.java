package code.formathtml;
import code.bean.Bean;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.UndefinedVariableError;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ByteStruct;
import code.expressionlanguage.structs.DisplayableStruct;
import code.expressionlanguage.structs.DoubleStruct;
import code.expressionlanguage.structs.EnumerableStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.FloatStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.formathtml.util.BadElRender;
import code.formathtml.util.BeanStruct;
import code.formathtml.util.StdStruct;
import code.formathtml.util.TranslatorStruct;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.MathFactory;

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

    private static final String ATTRIBUTE_QUOTED = "quoted";
    private static final String ATTRIBUTE_ESCAPED = "escaped";
    private static final char ESCAPED = '\\';
    private static final String CALL_METHOD = "$";
    private static final String ATTRIBUTE_VALUE = "value";
    private static final String ATTRIBUTE_ESCAPED_EAMP = "escapedamp";
    private static final char SEP_TR = ',';
    private static final char QUOTE = 39;
    private static final String QUOTE_DOUBLE = "\"";
    private static final String GET_STRING ="getString";
//    private static final String NAME ="name";
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
                        BadElRender badEl_ = new BadElRender();
                        badEl_.setErrors(_conf.getClasses().getErrorsDet());
                        badEl_.setFileName(_conf.getCurrentFileName());
                        badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                        _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                        return EMPTY_STRING;
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
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_conf.getClasses().getErrorsDet());
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                    return EMPTY_STRING;
                }
                Struct trloc_ = null;
                if (!tr_.toString().isEmpty()) {
                    trloc_ = _conf.getBuiltTranslators().getVal(tr_.toString());
                    if (trloc_ == null) {
                        _conf.getLastPage().setOffset(i_);
                        _conf.getContext().setException(new ErrorStruct(_conf, _conf.getStandards().getAliasNullPe()));
                        return EMPTY_STRING;
                    }
                }
                _conf.getLastPage().setOffset(i_);
                Argument argloc_ = ElRenderUtil.processEl(_pattern, _conf, i_, LEFT_EL, RIGHT_EL);
                if (_conf.getContext().getException() != null) {
                    return EMPTY_STRING;
                }
                if (!_conf.getClasses().getErrorsDet().isEmpty()) {
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_conf.getClasses().getErrorsDet());
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                    return EMPTY_STRING;
                }
                Struct s_ = argloc_.getStruct();
                String o_ = EMPTY_STRING;

                if (trloc_ != null) {
                    if (trloc_ instanceof TranslatorStruct) {
                        Bean bean_ = ((BeanStruct) _ip.getGlobalArgument().getStruct()).getInstance();
                        o_ = ((TranslatorStruct)trloc_).getInstance().getString(_pattern, _conf, bean_, valueOf(_conf, s_));
                    } else {
                        Struct bean_ = _ip.getGlobalArgument().getStruct();
                        LocalVariable lv_ = new LocalVariable();
                        String valName_ = _ip.getNextTempVar();
                        lv_ = new LocalVariable();
                        lv_.setStruct(trloc_);
                        lv_.setClassName(trloc_.getClassName(_conf.getContext()));
                        _ip.putLocalVar(valName_, lv_);
                        String patName_ = _ip.getNextTempVar();
                        lv_ = new LocalVariable();
                        lv_.setElement(_pattern);
                        lv_.setClassName(_conf.getStandards().getAliasString());
                        _ip.putLocalVar(patName_, lv_);
                        String navName_ = _ip.getNextTempVar();
                        lv_ = new LocalVariable();
                        lv_.setStruct(StdStruct.wrapStd(_conf, _conf.getContext()));
                        lv_.setClassName(_conf.getStandards().getAliasObject());
                        _ip.putLocalVar(navName_, lv_);
                        String beanName_ = _ip.getNextTempVar();
                        lv_ = new LocalVariable();
                        lv_.setStruct(bean_);
                        lv_.setClassName(bean_.getClassName(_conf.getContext()));
                        _ip.putLocalVar(beanName_, lv_);
                        String objName_ = _ip.getNextTempVar();
                        lv_ = new LocalVariable();
                        lv_.setStruct(s_);
                        lv_.setClassName(_conf.getStandards().getAliasObject());
                        _ip.putLocalVar(objName_, lv_);
                        StringBuilder expression_ = new StringBuilder(valName_).append(GET_LOC_VAR).append(GET_STRING).append(BEGIN_ARGS);
                        expression_.append(patName_).append(GET_LOC_VAR).append(SEP_ARGS);
                        expression_.append(navName_).append(GET_LOC_VAR).append(SEP_ARGS);
                        expression_.append(beanName_).append(GET_LOC_VAR).append(SEP_ARGS);
                        expression_.append(objName_).append(GET_LOC_VAR).append(END_ARGS);
                        Struct trStr_ = ElRenderUtil.processEl(expression_.toString(), 0, _conf).getStruct();
                        o_ = valueOf(_conf, trStr_);
                        if (_conf.getContext().getException() != null) {
                            return EMPTY_STRING;
                        }
                    }
                } else {
                    o_ = valueOf(_conf, s_);
                    if (_conf.getContext().getException() != null) {
                        return EMPTY_STRING;
                    }
                }
                str_.append(o_);
                i_ = _conf.getNextIndex();
                continue;
            }
            if (cur_ == RIGHT_EL){
                _conf.getLastPage().setOffset(i_);
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                return EMPTY_STRING;
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
                if (curChar_ == LEFT_EL) {
                    escaped_ = false;
                    calculateVariables_.append(LEFT_EL);
                    i_++;
                    continue;
                }
                if (curChar_ == RIGHT_EL) {
                    escaped_ = false;
                    calculateVariables_.append(RIGHT_EL);
                    i_++;
                    continue;
                }
                BadElRender badEl_ = new BadElRender();
                badEl_.setErrors(_conf.getClasses().getErrorsDet());
                badEl_.setFileName(_conf.getCurrentFileName());
                badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                return NullStruct.NULL_VALUE;
            }
            if (curChar_ == ESCAPED) {
                escaped_ = false;
                i_++;
                continue;
            }
            if (curChar_ == LEFT_EL) {
                _ip.setOffset(i_+1);
                Argument arg_ = ElRenderUtil.processEl(numExpr_, _conf, i_+1, LEFT_EL, RIGHT_EL);
                if (!_conf.getClasses().getErrorsDet().isEmpty()) {
                    BadElRender badEl_ = new BadElRender();
                    badEl_.setErrors(_conf.getClasses().getErrorsDet());
                    badEl_.setFileName(_conf.getCurrentFileName());
                    badEl_.setIndexFile(_conf.getCurrentLocationIndex());
                    _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
                    return NullStruct.NULL_VALUE;
                }
                calculateVariables_.append(toString(_conf, arg_.getStruct()));
                i_ = _conf.getNextIndex();
                continue;
            }
            calculateVariables_.append(curChar_);
            i_++;
        }
        numExpr_ = calculateVariables_.toString();
        if (_evalBool) {
            return new BooleanStruct(mathFact_.evaluateDirectlyBoolean(numExpr_));
        }
        return StdStruct.wrapStd(mathFact_.evaluateDirectlyRate(numExpr_), _conf.getContext(), rateClass_);
    }

    static Struct instanceByString(Configuration _conf, String _class, String _arg) {
        String name_ = _class;
        LgNames stds_ = _conf.getStandards();
        String doublePrim_ = stds_.getAliasPrimDouble();
        String floatPrim_ = stds_.getAliasPrimFloat();
        String longPrim_ = stds_.getAliasPrimLong();
        String intPrim_ = stds_.getAliasPrimInteger();
        String shortPrim_ = stds_.getAliasPrimShort();
        String bytePrim_ = stds_.getAliasPrimByte();
        String double_ = stds_.getAliasDouble();
        String float_ = stds_.getAliasFloat();
        String long_ = stds_.getAliasLong();
        String int_ = stds_.getAliasInteger();
        String short_ = stds_.getAliasShort();
        String byte_ = stds_.getAliasByte();
        String null_ = stds_.getAliasNullPe();
        if (StringList.quickEq(name_, int_) || StringList.quickEq(name_, intPrim_)) {
            Integer val_ = LgNames.parseInt(_arg);
            if (val_ == null) {
                _conf.getContext().setException(new ErrorStruct(_conf,_arg,null_));
                return NullStruct.NULL_VALUE;
            }
            return new IntStruct(val_);
        } else if (StringList.quickEq(name_, long_) || StringList.quickEq(name_, longPrim_)) {
            Long val_ = LgNames.parseLong(_arg);
            if (val_ == null) {
                _conf.getContext().setException(new ErrorStruct(_conf,_arg,null_));
                return NullStruct.NULL_VALUE;
            }
            return new LongStruct(val_);
        } else if (StringList.quickEq(name_, short_) || StringList.quickEq(name_, shortPrim_)) {
            Short val_ = LgNames.parseShort(_arg);
            if (val_ == null) {
                _conf.getContext().setException(new ErrorStruct(_conf,_arg,null_));
                return NullStruct.NULL_VALUE;
            }
            return new ShortStruct(val_);
        } else if (StringList.quickEq(name_, byte_) || StringList.quickEq(name_, bytePrim_)) {
            Byte val_ = LgNames.parseByte(_arg);
            if (val_ == null) {
                _conf.getContext().setException(new ErrorStruct(_conf,_arg,null_));
                return NullStruct.NULL_VALUE;
            }
            return new ByteStruct(val_);
        } else if (StringList.quickEq(name_, double_) || StringList.quickEq(name_, doublePrim_)) {
            Double val_ = LgNames.parseDouble(_arg);
            if (val_ == null) {
                _conf.getContext().setException(new ErrorStruct(_conf,_arg,null_));
                return NullStruct.NULL_VALUE;
            }
            return new DoubleStruct(val_);
        } else if (StringList.quickEq(name_, float_) || StringList.quickEq(name_, floatPrim_)) {
            Float val_ = LgNames.parseFloat(_arg);
            if (val_ == null) {
                _conf.getContext().setException(new ErrorStruct(_conf,_arg,null_));
                return NullStruct.NULL_VALUE;
            }
            return new FloatStruct(val_);
        } else {
            String escaped_ = StringList.replace(_arg, QUOTE_DOUBLE, StringList.concat(String.valueOf(ESCAPED),QUOTE_DOUBLE));
            String instanciation_ = StringList.concat(INSTANCE,_class,String.valueOf(BEGIN_ARGS),QUOTE_DOUBLE,escaped_,QUOTE_DOUBLE,String.valueOf(END_ARGS));
            Argument obj_ = ElRenderUtil.processEl(instanciation_, 0, _conf);
            if (_conf.getContext().getException() != null) {
                return NullStruct.NULL_VALUE;
            }
            if (obj_.isNull()) {
                _conf.getContext().setException(new ErrorStruct(_conf,_arg,null_));
                return NullStruct.NULL_VALUE;
            }
            return obj_.getStruct();
        }
    }
    static void beforeDiplaying(Configuration _conf, Struct _it, boolean _addpage) {
        if (_it == null) {
            return;
        }
        if (_it == NullStruct.NULL_VALUE) {
            return;
        }
        if (_addpage) {
            _conf.addPage(new ImportingPage(false));
        }
        getResult(_conf, 0, BEFORE_DISPLAYING, _it, _conf.getStandards().getBean());
        if (_addpage) {
            _conf.removeLastPage();
        }
    }

    static Struct getDataBase(Configuration _conf, Struct _it) {
        return getResult(_conf, 0, GET_DATA_BASE, _it, _conf.getStandards().getBean());
    }

    static void setDataBase(Configuration _conf, Struct _it, Struct _dataBase) {
        setBeanResult(_conf, 0, SET_DATA_BASE, _it, _dataBase, _conf.getStandards().getAliasObject());
    }

    static String getLanguage(Configuration _conf, Struct _it) {
        Struct str_ = getResult(_conf, 0, GET_LANGUAGE, _it, _conf.getStandards().getBean());
        if (str_ instanceof DisplayableStruct) {
            return ((DisplayableStruct) str_).getDisplayedString(_conf).getInstance();
        }
        return EMPTY_STRING;
    }

    static void setLanguage(Configuration _conf, Struct _it, String _scope) {
        setBeanResult(_conf, 0, SET_LANGUAGE, _it, new StringStruct(_scope), _conf.getStandards().getAliasString());
    }

    static String getScope(Configuration _conf, Struct _it) {
        Struct str_ = getResult(_conf, 0, GET_SCOPE, _it, _conf.getStandards().getBean());
        if (str_ instanceof DisplayableStruct) {
            return ((DisplayableStruct) str_).getDisplayedString(_conf).getInstance();
        }
        return EMPTY_STRING;
    }

    static void setScope(Configuration _conf, Struct _it, String _scope) {
        setBeanResult(_conf, 0, SET_SCOPE, _it, new StringStruct(_scope), _conf.getStandards().getAliasString());
    }

    static Struct getForms(Configuration _conf, Struct _it) {
        return getResult(_conf, 0, GET_FORMS, _it, _conf.getStandards().getBean());
    }

    static void setForms(Configuration _conf, Struct _it, Struct _forms) {
        setBeanResult(_conf, 0, SET_FORMS, _it, _forms, _conf.getStandards().getAliasStringMapObject());
    }

    static Struct getKey(Configuration _conf, Struct _it) {
        return getResult(_conf, 0, GET_KEY, _it, _conf.getStandards().getCustEntry());
    }

    static Struct getValue(Configuration _conf, Struct _it) {
        return getResult(_conf, 0, GET_VALUE, _it, _conf.getStandards().getCustEntry());
    }

    static char getChar(Configuration _conf, String _obj) {
        if (_obj.length() != CustList.ONE_ELEMENT) {
            _conf.getContext().setException(NullStruct.NULL_VALUE);
            return (char)0;
        }
        return _obj.charAt(CustList.FIRST_INDEX);
    }
    /**This method use the equal operator*/
    static boolean eq(Configuration _conf, Struct _objOne, Struct _objTwo) {
        ImportingPage ip_ = _conf.getLastPage();
        LocalVariable lvOne_ = new LocalVariable();
        lvOne_.setClassName(_conf.getStandards().getAliasObject());
        lvOne_.setStruct(_objOne);
        String nameOne_ = ip_.getNextTempVar();
        ip_.putLocalVar(nameOne_, lvOne_);
        LocalVariable lvTwo_ = new LocalVariable();
        lvTwo_.setClassName(_conf.getStandards().getAliasObject());
        lvTwo_.setStruct(_objTwo);
        String nameTwo_ = ip_.getNextTempVar();
        ip_.putLocalVar(nameTwo_, lvTwo_);
        String el_ = StringList.concat(nameOne_,GET_LOC_VAR,CMP,CMP,nameTwo_,GET_LOC_VAR);
        Argument arg_ = ElRenderUtil.processEl(el_, 0, _conf);
        ip_.removeLocalVar(nameOne_);
        ip_.removeLocalVar(nameTwo_);
        if (_conf.getContext().getException() != null) {
            return false;
        }
        BooleanStruct ret_ = (BooleanStruct)arg_.getStruct();
        return ret_.getInstance();
    }
    static void checkNullPointer(Configuration _conf, Struct _obj) {
        if (_obj == NullStruct.NULL_VALUE) {
            _conf.getContext().setException(new ErrorStruct(_conf, _conf.getStandards().getAliasNullPe()));
        }
    }
    static String valueOf(Configuration _conf, Struct _obj) {
        if (_obj == NullStruct.NULL_VALUE) {
            return _conf.getStandards().getNullString();
        }
        return toString(_conf, _obj);
    }
    static String toString(Configuration _conf, Struct _obj) {
        if (_obj instanceof DisplayableStruct) {
            return ((DisplayableStruct)_obj).getDisplayedString(_conf).getInstance();
        }
        ContextEl context_ = _conf.getContext();
        String method_;
        String param_ = _conf.getStandards().getAliasDisplayable();
        String arg_ = _conf.getStandards().getStructClassName(_obj, context_);
        if (Templates.isCorrectExecute(arg_, param_, context_)) {
            method_ = _conf.getStandards().getAliasDisplay();
        }  else {
            method_ = _conf.getStandards().getAliasToString();
        }
        Struct str_ = getResult(_conf, 0, method_, _obj, _conf.getStandards().getStructClassName(_obj, context_));
        if (str_ instanceof DisplayableStruct) {
            return ((DisplayableStruct) str_).getDisplayedString(_conf).getInstance();
        }
        return EMPTY_STRING;
    }
    static Struct iterator(Configuration _conf, Struct _it) {
        return getResult(_conf, 0, ITERATOR, _it, _conf.getStandards().getStructClassName(_it, _conf.getContext()));
    }
    static boolean hasNext(Configuration _conf, Struct _it) {
        Boolean bool_;
        if (_it instanceof StdStruct) {
            bool_ = ((BooleanStruct) getResult(_conf, 0, HAS_NEXT, _it, _conf.getStandards().getAliasSimpleIteratorType())).getInstance();
        } else {
            bool_ = ((BooleanStruct) getResult(_conf, 0, HAS_NEXT, _it, _conf.getStandards().getAliasIteratorType())).getInstance();
        }
        if (bool_ == null) {
            return false;
        }
        return bool_;
    }
    static Struct next(Configuration _conf, Struct _it) {
        if (_it instanceof StdStruct) {
            return getResult(_conf, 0, NEXT, _it, _conf.getStandards().getAliasSimpleIteratorType());
        }
        return getResult(_conf, 0, NEXT, _it, _conf.getStandards().getAliasIteratorType());
    }
    static Struct entryList(Configuration _conf, int _offsIndex, Struct _container) {
        return getResult(_conf, 0, ENTRY_LIST, _container, _conf.getStandards().getCustMap());
    }

    static String getStringKey(Configuration _conf, Struct _instance) {
        ContextEl cont_ = _conf.getContext();
        if (_instance instanceof EnumerableStruct) {
            return ((EnumerableStruct) _instance).getName();
        }
        ResultErrorStd res_ = _conf.getStandards().getName(cont_, _instance);
        if (res_.getError() != null) {
            cont_.setException(new ErrorStruct(_conf,res_.getError()));
            return EMPTY_STRING;
        }
        return toString(_conf, res_.getResult());
    }
    private static Struct getResult(Configuration _conf, int _offsIndex, String _methodName, Struct _instance, String _classVar) {
        ImportingPage ip_ = _conf.getLastPage();
        String varName_ = ip_.getNextTempVar();
        LocalVariable var_ = new LocalVariable();
        var_.setStruct(_instance);
        var_.setClassName(_classVar);
        ip_.putLocalVar(varName_, var_);
        String expression_ = StringList.concat(varName_,GET_LOC_VAR,_methodName,NO_PARAM_METHOD);
        Argument arg_ = ElRenderUtil.processEl(expression_, 0, _conf);
        ip_.removeLocalVar(varName_);
        if (arg_ == null) {
            return NullStruct.NULL_VALUE;
        }
        Struct str_ = arg_.getStruct();
        return str_;
    }
    private static void setBeanResult(Configuration _conf, int _offsIndex, String _methodName, Struct _instance, Struct _argument,
            String _argumentClassName) {
        ImportingPage ip_ = _conf.getLastPage();
        String varName_ = ip_.getNextTempVar();
        LocalVariable var_ = new LocalVariable();
        var_.setStruct(_instance);
        var_.setClassName(_conf.getStandards().getBean());
        ip_.putLocalVar(varName_, var_);
        String argName_ = ip_.getNextTempVar();
        var_ = new LocalVariable();
        var_.setStruct(_argument);
        var_.setClassName(_argumentClassName);
        ip_.putLocalVar(argName_, var_);
        String expression_ = StringList.concat(varName_,GET_LOC_VAR,_methodName,String.valueOf(BEGIN_ARGS),argName_,GET_LOC_VAR,String.valueOf(END_ARGS));
        ElRenderUtil.processEl(expression_, 0, _conf);
        ip_.removeLocalVar(varName_);
    }

    static LoopVariable getCurrentVariable(Configuration _conf, int _offset,StringMap<LoopVariable> _vars, String _candidate) {
        if (!_vars.contains(_candidate)) {
            _conf.getLastPage().addToOffset(_offset);
            UndefinedVariableError und_ = new UndefinedVariableError();
            und_.setId(_candidate);
            und_.setFileName(_conf.getCurrentFileName());
            und_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().getErrorsDet().add(und_);
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
            return new LoopVariable();
        }
        return _vars.getVal(_candidate);
    }

    static LocalVariable getCurrentLocVariable(Configuration _conf, int _offset,StringMap<LocalVariable> _vars, String _candidate) {
        if (!_vars.contains(_candidate)) {
            _conf.getLastPage().addToOffset(_offset);
            UndefinedVariableError und_ = new UndefinedVariableError();
            und_.setId(_candidate);
            und_.setFileName(_conf.getCurrentFileName());
            und_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getClasses().getErrorsDet().add(und_);
            BadElRender badEl_ = new BadElRender();
            badEl_.setErrors(_conf.getClasses().getErrorsDet());
            badEl_.setFileName(_conf.getCurrentFileName());
            badEl_.setIndexFile(_conf.getCurrentLocationIndex());
            _conf.getContext().setException(new ErrorStruct(_conf, badEl_.display(_conf.getClasses()), _conf.getStandards().getErrorEl()));
            return new LocalVariable();
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
            Struct str_ = ElRenderUtil.processEl(value_, 1, _conf).getStruct();
            if (_conf.getContext().getException() != null) {
                return EMPTY_STRING;
            }
            preformatted_ = toString(_conf, str_);
            if (_conf.getContext().getException() != null) {
                return EMPTY_STRING;
            }
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
            if (_conf.getContext().getException() != null) {
                return EMPTY_STRING;
            }
            StringMap<String> messages_ = ExtractFromResources.getInnerMessagesFromLocaleClass(_conf, _loc, fileName_, _files, _resourcesFolder);
            if (_conf.getContext().getException() != null) {
                return EMPTY_STRING;
            }
            preformatted_ = ExtractFromResources.getFormat(messages_, key_, _conf, _loc, fileName_);
            if (_conf.getContext().getException() != null) {
                return EMPTY_STRING;
            }
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
            Struct o_ = ElRenderUtil.processEl(attribute_, begin_, _conf).getStruct();
            if (_conf.getContext().getException() != null) {
                return EMPTY_STRING;
            }
            if (n.hasAttribute(ATTRIBUTE_ESCAPED)) {
                objects_.add(escapeParam(_conf,o_));
            } else {
                objects_.add(toString(_conf, o_));
            }
            if (_conf.getContext().getException() != null) {
                return EMPTY_STRING;
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
        if (_conf.getContext().getException() != null) {
            return EMPTY_STRING;
        }
        StringMap<String> rep_ = new StringMap<String>();
        String quote_ = String.valueOf(QUOTE);
        rep_.put(String.valueOf(LEFT_EL), StringList.concat(quote_,String.valueOf(LEFT_EL),quote_));
        rep_.put(String.valueOf(RIGHT_EL), StringList.concat(quote_,String.valueOf(RIGHT_EL),quote_));
        rep_.put(String.valueOf(QUOTE), StringList.concat(quote_,quote_));
        return StringList.replaceMultiple(str_, rep_);
    }

    static String getMessageFolder(Configuration _conf) {
        return _conf.getMessagesFolder();
    }
    private static MathFactory getMathFactory(Configuration _conf) {
        return _conf.getMathFactory();
    }
    static String getProperty(Configuration _conf, String _key) {
        return _conf.getProperties().getVal(_key);
    }
}
