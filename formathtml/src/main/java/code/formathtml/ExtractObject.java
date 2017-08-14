package code.formathtml;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.w3c.dom.Element;

import code.bean.Bean;
import code.bean.translator.Translator;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.BadExpressionLanguageException;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.exceptions.ErrorCausingException;
import code.expressionlanguage.exceptions.InvokeRedinedMethException;
import code.expressionlanguage.exceptions.UndefinedVariableException;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.formathtml.exceptions.CharacterFormatException;
import code.formathtml.exceptions.GettingKeysException;
import code.formathtml.exceptions.InexistingTranslatorException;
import code.serialize.exceptions.BadAccessException;
import code.serialize.exceptions.InvokingException;
import code.serialize.exceptions.NoSuchDeclaredMethodException;
import code.serialize.exceptions.RuntimeInstantiationException;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdList;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.ConstClasses;
import code.util.exceptions.NullObjectException;
import code.util.exceptions.RuntimeClassNotFoundException;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;
import code.util.ints.MathFactory;
import code.xml.XmlParser;

final class ExtractObject {

    static final String NAMESPACE_URI = "javahtml";
//    static final String ATTRIBUTE_CLASS_NAME = "className";
//    static final String ATTRIBUTE_INDEX_CLASS_NAME = "indexclassName";
    static final String EMPTY_STRING = "";
    static final String RETURN_LINE = "\n";
    static final String RETURN_TAB = "\n\t";

    //    static final String CLASS_NAME_ATTRIBUTE = ATTRIBUTE_CLASS_NAME;
    //    static final String NULL_METHOD = String.valueOf((Object)null);
//    static final String NULL_METHOD = "goto";
    //    static final String NULL_METHOD = "";
    static final String SPACE = " ";
    static final String VAR_METHOD = "varMethod";
    static final String BEAN_ATTRIBUTE = "bean";
//    static final String ATTRIBUTE_VALUE_CHANGE_EVENT = "valueChangeEvent";
    static final String COMMA = ",";
    static final String DOT = ".";
//    static final String GET_KEY = "!key";

//    private static final String TR = "|";
    //    private static final String DOT_END = "\\.$";
    //    private static final String ESCAPED_TR = "\\|";
    //    private static final String BOUNDS_EL = "\\{|\\}";
    private static final String GET_LOC_VAR = ";.";
    private static final String CMP = "=";
    private static final char RIGHT_EL = '}';
    private static final char LEFT_EL = '{';
//    private static final String CLASS_VAR_ARG = "?";
//    private static final char CLASS_VAR_ARG_CHAR = '?';
//    private static final String NULL_REF = null;
//
//    private static final String ARRAY_FIELD_LENGTH = "length";
//    private static final String SUP_MESSAGE = " >= ";

    private static final String LOOP_MESSAGE = "loop";
    private static final String LOCAL_MESSAGE = "local";

//    private static final char PATH_CLASS_SEPARATOR_CHAR = '^';
//    private static final char PATH_INTERN_CLASS_SEPARATOR_CHAR = '$';
//    private static final String PATH_CLASS_SEPARATOR = EMPTY_STRING+PATH_CLASS_SEPARATOR_CHAR;

    //    private static final String ESCAPED_RIGHT_EL = "\\}";
    //    private static final String ESCAPED_LEFT_EL = "\\{";
    //    private static final String NEXT_FIELDS = "([^,\\}]+(,\\w+){0,2})";
    //    private static final String FORMAT_VAR = "$1";
    //    private static final String NEXT_ARG = "([^,]+),,";
//    private static final String GET_ENTRY = "!";
//    private static final char COMMA_CHAR = ',';
//    private static final char RIGHT_PAR_CHAR = ')';
//    private static final String RIGHT_PAR = EMPTY_STRING+RIGHT_PAR_CHAR;
//    private static final char MINUS_CHAR = '-';
//    private static final char GET_LOC_VAR_CHAR = ';';
//    private static final char GET_LOC_VAR_CHAR_SEC = '.';
//    private static final char LEFT_PAR_CHAR = '(';
//    private static final String LEFT_PAR = EMPTY_STRING+LEFT_PAR_CHAR;
    private static final String ATTRIBUTE_QUOTED = "quoted";
    private static final String ATTRIBUTE_ESCAPED = "escaped";
    //    private static final String INT_REG_EXP = "[0-9]+";
//    private static final String GET_INDEX = ";;";
//    private static final String GET_LOC_VAR = ";.";
//    private static final String GET_VALUE = "!value";
//    private static final String RIGHT_GET_ARR = "]";
//    private static final char LEFT_GET_ARR_CHAR = '[';
//    private static final String LEFT_GET_ARR = "[";
//    private static final String GET_ATTRIBUTE = ";";
    private static final char ESCAPED = '\\';
    private static final char MATH_INTERPRET = '`';
//    private static final String GET_PARAM = ";.;";
    //    private static final String CALL_AFTER = ";(.+)";
    private static final String CALL_METHOD = "$";
//    private static final String NO_PARAM_METHOD = "()";
    private static final String ATTRIBUTE_VALUE = "value";
    private static final String ATTRIBUTE_ESCAPED_EAMP = "escapedamp";
//    private static final int ESCAPED_TOKEN = 2;
//    private static final int ESCAPED_TOKENS = 4;
    private static final char BEGIN_TR = '[';
    private static final char END_TR = ']';
//    private static final String STATIC_PREFIX = "static.";
//    private static final String IN_ARRAY ="in array";
//    private static final String IN_LIST ="in list";
    private static final char QUOTE = 39;
    private ExtractObject() {
    }

    static String formatNumVariables(String _pattern, Configuration _conf, ImportingPage _ip, StringMap<String> _files) {
        //      Map<String,String> fields_ = new Map<String,String>();
        //      for (String k: tokens_) {}
//        Map<String,LoopVariable> vars_ = _ip.getVars();
//        Map<String,LocalVariable> locVars_ = _ip.getLocalVars();
//        Bean bean_ = (Bean) _ip.getVars().getVal(EMPTY_STRING).getElement();
        StringBuilder str_ = new StringBuilder();
        StringBuilder arg_ = new StringBuilder();
        int length_ = _pattern.length();
        boolean escaped_ = false;
//        boolean inside_ = false;
        int i_ = CustList.FIRST_INDEX;
        while (i_ < length_) {
            char cur_ = _pattern.charAt(i_);
//            if (cur_ == QUOTE && !inside_)
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
                if (i_ + 1 < length_ && _pattern.charAt(i_ + 1) == BEGIN_TR) {
                    int j_ = i_ + 1;
                    boolean ok_ = false;
                    while (j_ < length_) {
                        if (_pattern.charAt(j_) == END_TR) {
                            j_++;
                            i_ = j_;
                            ok_ = true;
                            break;
                        }
                        if (j_ > i_+1 && !StringList.isWordChar(_pattern.charAt(j_))) {
                            _conf.getLastPage().setOffset(i_);
                            throw new BadExpressionLanguageException(arg_.toString()+RETURN_LINE+_conf.joinPages());
                        }
                        j_++;
                        tr_.append(_pattern.charAt(j_));
                    }
                    if (!ok_) {
                        _conf.getLastPage().setOffset(i_);
                        throw new BadExpressionLanguageException(arg_.toString()+RETURN_LINE+_conf.joinPages());
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
                Translator trloc_ = null;
                if (!tr_.toString().isEmpty()) {
                    try {
                        trloc_ = _conf.getTranslators().getVal(tr_.toString());
                        if (trloc_ == null) {
                            _conf.getLastPage().setOffset(i_);
                            throw new InexistingTranslatorException(tr_+RETURN_LINE+_conf.joinPages());
                        }
                    } catch (VirtualMachineError _0) {
                        _conf.getLastPage().setOffset(i_);
                        throw new InexistingTranslatorException(tr_+RETURN_LINE+_conf.joinPages());
                    } catch (RuntimeException _0) {
                        _conf.getLastPage().setOffset(i_);
                        throw new InexistingTranslatorException(tr_+RETURN_LINE+_conf.joinPages());
                    }
                }
                _conf.getLastPage().setOffset(i_);
                Argument argloc_ = ElUtil.processEl(_pattern, context_, i_, LEFT_EL, RIGHT_EL);
                Object o_ = argloc_.getObject();
                try {
                    if (trloc_ != null) {
                        Bean bean_ = (Bean) _ip.getGlobalArgument().getObject();
                        o_ = trloc_.getString(_pattern, _conf, _files, bean_, o_);
                    }
                } catch (VirtualMachineError _0) {
                    _conf.getLastPage().setOffset(context_.getNextIndex());
                    throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
                } catch (RuntimeException _0) {
                    _conf.getLastPage().setOffset(context_.getNextIndex());
                    throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
                }
                str_.append(ExtractObject.valueOf(_conf, o_));
                i_ = context_.getNextIndex();
                continue;
//                if (inside_) {
//                    _conf.getLastPage().setOffset(i_);
//                    throw new BadExpressionLanguageException(arg_.toString()+RETURN_LINE+_conf.joinPages(RETURN_LINE));
//                }
//                arg_ = new StringBuilder();
//                inside_ = true;
//            } else if (cur_ == RIGHT_EL) {
//                if (!inside_) {
//                    _conf.getLastPage().setOffset(i_);
//                    throw new BadExpressionLanguageException(arg_.toString()+RETURN_LINE+_conf.joinPages(RETURN_LINE));
//                }
//                inside_ = false;
//                String command_ = arg_.toString();
//                Object o_;
//                _conf.getLastPage().setOffset(i_ - command_.length());
//                if (!command_.contains(TR)) {
//                    o_ = ExtractObject.improvedExtractObject(_conf, command_);
//                } else {
//                    StringList infos_ = StringList.splitStrings(command_, TR);
//                    o_ = ExtractObject.improvedExtractObject(_conf, StringList.replaceEnd(infos_.first(),DOT));
//                    Translator tr_;
//                    try {
//                        tr_ = _conf.getTranslators().getVal(infos_.last());
//                        if (tr_ == null) {
//                            _conf.getLastPage().setOffset(i_ - command_.length()+command_.lastIndexOf(TR));
//                            throw new InexistingTranslatorException(infos_.last()+RETURN_LINE+_conf.joinPages(RETURN_LINE));
//                        }
//                    } catch (VirtualMachineError _0) {
//                        _conf.getLastPage().setOffset(i_ - command_.length()+command_.lastIndexOf(TR));
//                        throw new InexistingTranslatorException(infos_.last()+RETURN_LINE+_conf.joinPages(RETURN_LINE));
//                    } catch (RuntimeException _0) {
//                        _conf.getLastPage().setOffset(i_ - command_.length()+command_.lastIndexOf(TR));
//                        throw new InexistingTranslatorException(infos_.last()+RETURN_LINE+_conf.joinPages(RETURN_LINE));
//                    }
//                    try {
//                        Bean bean_ = (Bean) _ip.getGlobalArgument().getObject();
//                        o_ = tr_.getString(_pattern, _conf, _files, bean_, o_);
//                    } catch (VirtualMachineError _0) {
//                        _conf.getLastPage().setOffset(i_ - command_.length()+command_.lastIndexOf(TR));
//                        throw new ErrorCausingException(_conf.joinPages(RETURN_LINE), _0);
//                    } catch (RuntimeException _0) {
//                        _conf.getLastPage().setOffset(i_ - command_.length()+command_.lastIndexOf(TR));
//                        throw new InvokeRedinedMethException(_conf.joinPages(RETURN_LINE), _0);
//                    }
//                }
//                str_.append(ExtractObject.valueOf(_conf, o_));
//            } else if (inside_) {
//                arg_.append(cur_);
            } else if (cur_ == RIGHT_EL){
                _conf.getLastPage().setOffset(i_);
                throw new BadExpressionLanguageException(arg_.toString()+RETURN_LINE+_conf.joinPages());
            } else {
                str_.append(cur_);
            }
            i_++;
        }
//        if (inside_) {
//            _conf.getLastPage().setOffset(_pattern.length());
//            throw new BadExpressionLanguageException(arg_.toString()+RETURN_LINE+_conf.joinPages(RETURN_LINE));
//        }
        return str_.toString();
        //      for (String k: StringList.getFields(_pattern)) {
            //          if (!k.contains(TR)) {
                //              Object o_ = improvedExtractObject(_locVars, _vars, k);
                ////              fields_.put(LEFT_EL+k+RIGHT_EL, encodeXml(o_));
                //              fields_.put(LEFT_EL+k+RIGHT_EL, String.valueOf(o_));
                //          } else {
                    //              StringList infos_ = StringList.splitStrings(k, TR);
                    //              Object o_ = improvedExtractObject(_locVars, _vars, StringList.replaceEnd(infos_.first(),DOT));
                    //              o_ = _conf.getTranslators().getVal(infos_.last()).getString(_pattern, _conf, _files, bean_, o_);
                    ////              fields_.put(LEFT_EL+k+RIGHT_EL, encodeXml(o_));
                    //              fields_.put(LEFT_EL+k+RIGHT_EL, String.valueOf(o_));
        //          }
        //      }
        //      return StringList.formatQuote(_pattern, fields_);
    }

    static Object evaluateMathExpression(ImportingPage _ip, Configuration _conf, boolean _evalBool, String _mathExp) {
        MathFactory<?> mathFact_ = getMathFactory(_conf);
//        Map<String,LoopVariable> vars_ = _ip.getVars();
//        Map<String,LocalVariable> locVars_ = _ip.getLocalVars();
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
                } catch (VirtualMachineError _0) {
                    throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
                } catch (RuntimeException _0) {
                    throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
                }
                i_ = context_.getNextIndex();
                continue;
            }
            calculateVariables_.append(curChar_);
            i_++;
        }
//        int index_ = 0;
//        for (String p: StringList.splitChars(numExpr_, MATH_INTERPRET)) {
//            if (index_ % 2 == 1) {
//                Object arg_ = ExtractObject.improvedExtractObject(_conf, p);
//                try {
//                    calculateVariables_.append(mathFact_.toString(arg_));
//                } catch (VirtualMachineError _0) {
//                    throw new ErrorCausingException(_conf.joinPages(RETURN_LINE), _0);
//                } catch (RuntimeException _0) {
//                    throw new InvokeRedinedMethException(_conf.joinPages(RETURN_LINE), _0);
//                }
//            } else {
//                calculateVariables_.append(p);
//            }
//            _ip.addToOffset(p.length()+1);
//            index_++;
//        }
        numExpr_ = calculateVariables_.toString();
        try {
            Object ret_;
            if (_evalBool) {
                ret_ = mathFact_.evaluateDirectlyBoolean(numExpr_);
            } else {
                ret_ = mathFact_.evaluateDirectlyRate(numExpr_);
            }
            return ret_;
        } catch (VirtualMachineError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (RuntimeException _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    /*
    @Deprecated
    static Argument getMethodThenInvoke(Configuration _conf, int _offsetIncr, Object _instance, Class<?> _class,String _name, Argument... _args) {
        Method m_;
        try {
            m_ = SerializeXmlObject.getDeclaredMethod(_class, _name, getClasses(_args));
        } catch (NoSuchDeclaredMethodException _0) {
            //            if (!_useNode) {
            //                throw _0;
            //            }
            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new NoSuchDeclaredMethodException(_0.getMessage()+RETURN_LINE+_conf.joinPages(RETURN_LINE));
        }
        setAccess(m_, _conf.toContextEl());
        Argument a_ = new Argument();
        a_.setArgClass(m_.getReturnType());
        a_.setObject(invokeMethod(_conf, _offsetIncr, _class, m_, _instance, getObjects(_args)));
        return a_;
    }
    @Deprecated
    static Object invokeMethod(Configuration _conf, int _offsetIncr, Class<?> _class,Method _method, Object _instance, Object... _args) {
        try {
            return _method.invoke(_instance, _args);
        } catch (IllegalAccessException _0) {
            //            if (!_useNode) {
            //                throw new BadAccessException(_0, _method.toString());
            //            }
            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new BadAccessException(_0, _method.toString()+RETURN_LINE+_conf.joinPages(RETURN_LINE));
        } catch (IllegalArgumentException _0) {
            //            if (!_useNode) {
            //                throw new ClassCastException(_instance.getClass().getName()+SPACE+_class.getName());
            //            }
            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new DynamicCastClassException(_instance.getClass().getName()+SPACE+_class.getName()+RETURN_LINE+_conf.joinPages(RETURN_LINE));
        } catch (InvocationTargetException _0) {
            //            if (!_useNode) {
            //                throw new InvokeException(_0.getTargetException());
            //            }
            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new InvokeException(_conf.joinPages(RETURN_LINE), _0.getTargetException());
        } catch (VirtualMachineError _0) {
            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new ErrorCausingException(_conf.joinPages(RETURN_LINE), _0);
        } catch (ExceptionInInitializerError _0) {
            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new ErrorCausingException(_conf.joinPages(RETURN_LINE), _0);
        }
    }

    @Deprecated
    static Object instantiate(Configuration _conf, int _offsetIncr, String _expression) {
        if (_expression.endsWith(NO_PARAM_METHOD)) {
            String name_ = _expression.substring(0, _expression.length() - NO_PARAM_METHOD.length());
            Class<?> class_ = classForName(_conf, _offsetIncr, name_, true);
            //            try {
            //                class_ = ConstClasses.classForName(name_);
            //            } catch (Exception _0) {
            //                _conf.getLastPage().setOffset(_offsetIncr);
            //                throw new RuntimeClassNotFoundException(name_+RETURN_LINE+_conf.joinPages(RETURN_LINE));
            //            }
            return getConstructorThenInvoke(_conf, _offsetIncr + name_.length(), class_);
        }
        if (_expression.indexOf(LEFT_PAR) == CustList.INDEX_NOT_FOUND_ELT || !_expression.endsWith(RIGHT_PAR)) {
            throw new BadParenthesesException(_conf.joinPages(RETURN_LINE));
        }
        String mName_ = _expression.substring(CustList.FIRST_INDEX, _expression.indexOf(LEFT_PAR));
        Class<?> class_ = classForName(_conf, _offsetIncr, mName_, true);
        //        try {
        //            class_ = ConstClasses.classForName(mName_);
        //        } catch (Exception _0) {
        //            _conf.getLastPage().setOffset(_offsetIncr);
        //            throw new RuntimeClassNotFoundException(mName_+RETURN_LINE+_conf.joinPages(RETURN_LINE));
        //        }
        //        Map<String,LoopVariable> vars_ = _conf.getLastPage().getVars();
        //        Map<String,LocalVariable> localVars_ = _conf.getLastPage().getLocalVars();
        String args_ = _expression.substring(_expression.indexOf(LEFT_PAR) + 1);
        args_ = args_.substring(CustList.FIRST_INDEX, args_.length() - 1);
        int lenArgs_ = mName_.length()+1+_offsetIncr;
        CustList<Argument> arguments_ = getArguments(_conf, lenArgs_, args_);
        //        for (String l: StringList.splitStrings(args_, COMMA)) {
        //            try {
        ////                a_.setObject(Long.parseLong(l));
        ////                a_.setArgClass(Long.class);
        //                arguments_.add(Argument.numberToArgument(l));
        //            } catch (Exception _0) {
        //                Argument a_ = new Argument();
        //                if (l.endsWith(GET_INDEX)) {
        //                    String l_ = l.substring(CustList.FIRST_INDEX, l.length() - GET_INDEX.length());
        //                    LoopVariable lv_ = getCurrentVariable(_conf, lenArgs_, vars_, l_);
        //                    a_.setObject(lv_.getIndex());
        //                    a_.setArgClass(Long.class);
        //                    arguments_.add(a_);
        //                    continue;
        //                }
        //                if (l.endsWith(GET_ATTRIBUTE)) {
        //                    String l_ = l.substring(CustList.FIRST_INDEX, l.length() - GET_ATTRIBUTE.length());
        //                    LoopVariable lv_ = getCurrentVariable(_conf, lenArgs_, vars_, l_);
        //                    a_.setObject(lv_.getElement());
        //                    a_.setArgClass(ConstClasses.classForName(lv_.getClassName()));
        //                    arguments_.add(a_);
        //                    continue;
        //                }
        //                String l_ = l.substring(CustList.FIRST_INDEX, l.length() - GET_LOC_VAR.length());
        //                LocalVariable lv_ = getCurrentLocVariable(_conf, lenArgs_, localVars_, l_);
        //                a_.setObject(lv_.getElement());
        //                a_.setArgClass(ConstClasses.classForName(lv_.getClassName()));
        //                arguments_.add(a_);
        //            }
        //            lenArgs_ += l.length() + 1;
        //        }
        return getConstructorThenInvoke(_conf, args_.length(), class_, arguments_.toArray(new Argument[arguments_.size()]));
        //        object_ = getMethodThenInvoke(_conf, lenBef_, _useNode, object_, class_, mName_, arguments_.toArray(new Argument[arguments_.size()]));
    }
    @Deprecated
    static Object getConstructorThenInvoke(Configuration _conf, int _offsetIncr, Class<?> _class, Argument... _args) {
        Constructor m_;
        try {
            m_ = _class.getDeclaredConstructor(getClasses(_args));
        } catch (NoSuchMethodException _0) {
            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new NoSuchDeclaredConstructorException(_0.getMessage()+RETURN_LINE+_conf.joinPages(RETURN_LINE));
        }
        setAccess(m_, _conf.toContextEl());
        try {
            return m_.newInstance(getObjects(_args));
        } catch (InstantiationException _0) {
            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new RuntimeInstantiationException(_0, m_.toString()+RETURN_LINE+_conf.joinPages(RETURN_LINE));
        } catch (IllegalAccessException _0) {
            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new BadAccessException(_0, m_.toString()+RETURN_LINE+_conf.joinPages(RETURN_LINE));
        } catch (IllegalArgumentException _0) {
            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new DynamicCastClassException(_class.getName()+RETURN_LINE+_conf.joinPages(RETURN_LINE));
        } catch (InvocationTargetException _0) {
            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new InvokeException(_conf.joinPages(RETURN_LINE), _0.getTargetException());
        } catch (VirtualMachineError _0) {
            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new ErrorCausingException(_conf.joinPages(RETURN_LINE), _0);
        } catch (ExceptionInInitializerError _0) {
            _conf.getLastPage().addToOffset(_offsetIncr);
            throw new ErrorCausingException(_conf.joinPages(RETURN_LINE), _0);
        }
    }

    @Deprecated
    static Object instantiateArray(Configuration _conf, int _offsetIncr, String _expression) {
        if (_expression.indexOf(LEFT_GET_ARR) == CustList.INDEX_NOT_FOUND_ELT || !_expression.endsWith(RIGHT_GET_ARR)) {
            throw new BadParenthesesException(_conf.joinPages(RETURN_LINE));
        }
        String name_ = _expression.substring(0, _expression.lastIndexOf(LEFT_GET_ARR));
        String nbElts_ = _expression.substring(_expression.lastIndexOf(LEFT_GET_ARR)+1, _expression.length() - 1);
        Class<?> class_ = classForName(_conf, _offsetIncr, name_, true);
        //        try {
            //            class_ = ConstClasses.classForName(name_);
        //        } catch (Exception _0) {
        //            _conf.getLastPage().setOffset(_offsetIncr);
        //            throw new RuntimeClassNotFoundException(name_+RETURN_LINE+_conf.joinPages(RETURN_LINE));
        //        }
        if (StringList.isPositiveNumber(nbElts_)) {
            int nb_ = Integer.parseInt(nbElts_);
            return Array.newInstance(class_, nb_);
        }
//        Map<String,LoopVariable> vars_ = _conf.getLastPage().getVars();
//        Map<String,LocalVariable> localVars_ = _conf.getLastPage().getLocalVars();
        int nb_ = intValue(_conf, 0, ExtractObject.improvedExtractObject(_conf, nbElts_));
        //        try {
        //            nb_ = Integer.parseInt(nbElts_);
        //            if (nb_ < CustList.SIZE_EMPTY) {
        //                throw new NumberFormatException(nbElts_);
        //            }
        //        } catch (NumberFormatException _0) {
        //
        //            _conf.getLastPage().setOffset(_offsetIncr+name_.length());
        //            throw new NumberFormatException(nbElts_+RETURN_LINE+_conf.joinPages(RETURN_LINE));
        //        }
        return Array.newInstance(class_, nb_);
    }
    //    static Object getField(Field _field, Object _instance) {
    //        try {
    //            return _field.get(_instance);
    //        } catch (IllegalAccessException _0) {
    //            throw new BadAccessException(_0);
    //        }
    //    }

    //    static void setField(Field _field, Object _instance, Object _ref) {
    //        try {
    //            _field.set(_instance, _ref);
    //        } catch (IllegalAccessException _0) {
    //            throw new BadAccessException(_0);
    //        }
    //    }

    //    private static StringList positiveNumbers(String _string) {
    //        StringList tokens_ = StringList.getWordsSeparators(_string);
    //        StringList newList_ = new StringList();
    //        for (String t: tokens_) {
    //            if (StringList.isPositiveNumber(t)) {
    //                newList_.add(t);
    //            }
    //        }
    //        return newList_;
    //    }

    //    private static StringList mapMethod(String _string) {
    //        StringList tokens_ = StringList.getWordsSeparators(_string);
    //        StringList newList_ = new StringList();
    //        int i_ = CustList.FIRST_INDEX;
    //        while (true) {
    //            String t_ = tokens_.get(i_);
    //            if (StringList.isWord(t_)) {
    //                if (i_ + 1 == tokens_.size()) {
    //                    break;
    //                }
    //                if (!tokens_.get(i_ + 1).startsWith(GET_ENTRY)) {
    //                    i_ ++;
    //                    continue;
    //                }
    //                newList_.add(t_+GET_ENTRY);
    //            }
    //            i_ ++;
    //        }
    //        return newList_;
    //    }

    static void setAccess(AccessibleObject _accessible, ContextEl _context) {
        _context.getAccessValue().setAccess(_accessible, _context);
    }
//    static boolean access(Constructor<?> _method) {
//        return _method.getAnnotation(Accessible.class) != null;
//    }
//
//    static boolean access(Method _method) {
//        return _method.getAnnotation(Accessible.class) != null;
//    }
//
//    static boolean access(Field _field) {
//        return _field.getAnnotation(Accessible.class) != null;
//    }

    //    public static <T extends Comparable<T>> SortableList<T> orderedKeySet(java.util.Map<T,?> _map) {
    //        SortableList<T> list_ = new SortableList<>(_map.keySet());
    //        list_.sort();
    //        return list_;
    //    }*/

//    private static CustList<?> orderedList(Listable<?> _map) {
//        CustList<Object> list_ = new CustList<Object>(_map);
//        list_.sortElts(new);
//        return list_;
//    }

    /*@Deprecated
    @Deprecated
    private static String getVariableFromCommand(Configuration _conf, String _command) {
        int index_ = CustList.FIRST_INDEX;
        int len_ = _command.length();
        StringBuilder str_ = new StringBuilder();
        while (index_ < len_) {
            char ch_ = _command.charAt(index_);
            if (!StringList.isWordChar(ch_)) {
                if (ch_ == GET_LOC_VAR_CHAR) {
                    if (index_ == CustList.FIRST_INDEX) {
                        new BadExpressionLanguageException(_conf.joinPages(RETURN_LINE));
                    }
                    str_.append(ch_);
                    index_++;
                    while (index_ < len_) {
                        ch_ = _command.charAt(index_);
                        if (ch_ == GET_LOC_VAR_CHAR) {
                            str_.append(ch_);
                            index_++;
                            continue;
                        }
                        if (ch_ == GET_LOC_VAR_CHAR_SEC) {
                            str_.append(ch_);
                            index_++;
                            continue;
                        }
                        break;
                    }
                    return str_.toString();
                }
                return EMPTY_STRING;
            }
            str_.append(ch_);
            index_++;
        }
        return EMPTY_STRING;
    }

//    private static boolean isGetFromBean(String _command) {
//        int index_ = _command.indexOf(GET_ATTRIBUTE);
//        int indexArr_ = _command.indexOf(LEFT_GET_ARR);
//        int indexPar_ = _command.indexOf(LEFT_PAR);
//        if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
//            return true;
//        }
//        if (indexArr_ == CustList.INDEX_NOT_FOUND_ELT) {
//            if (indexPar_ == CustList.INDEX_NOT_FOUND_ELT) {
//                return false;
//            }
//            if (index_ < indexPar_) {
//                return false;
//            }
//            return true;
//        }
//        if (indexPar_ == CustList.INDEX_NOT_FOUND_ELT) {
//            if (index_ < indexArr_) {
//                return false;
//            }
//            return true;
//        }
//        if (index_ < Math.min(indexArr_, indexPar_)) {
//            return false;
//        }
//        return true;
//    }
//
//    private static boolean isGetFromLocalVar(String _command) {
//        int index_ = _command.indexOf(GET_LOC_VAR);
//        int indexArr_ = _command.indexOf(LEFT_GET_ARR);
//        int indexPar_ = _command.indexOf(LEFT_PAR);
//        if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
//            return false;
//        }
//        Numbers<Integer> indexes_ = new Numbers<Integer>();
//        //        if (indexArr_ != CustList.INDEX_NOT_FOUND_ELT) {
//        //            indexes_.add(indexArr_);
//        //        }
//        //        if (indexPar_ != CustList.INDEX_NOT_FOUND_ELT) {
//        //            indexes_.add(indexPar_);
//        //        }
//        indexes_.add(indexArr_);
//        indexes_.add(indexPar_);
//        indexes_.removeAllNb(CustList.INDEX_NOT_FOUND_ELT);
//        if (indexes_.isEmpty()) {
//            return true;
//        }
//        return index_ < indexes_.getMinimum();
//        //        if (indexArr_ == CustList.INDEX_NOT_FOUND_ELT) {
//        //            if (indexPar_ == CustList.INDEX_NOT_FOUND_ELT) {
//        //                return true;
//        //            }
//        //            if (index_ < indexPar_) {
//        //                return true;
//        //            }
//        //            return false;
//        //        }
//        //        if (indexPar_ == CustList.INDEX_NOT_FOUND_ELT) {
//        //            if (index_ < indexArr_) {
//        //                return true;
//        //            }
//        //            return false;
//        //        }
//        //        if (index_ < Math.min(indexArr_, indexPar_)) {
//        //            return true;
//        //        }
//        //        return false;
//    }

    //  static Object extractObject(Configuration _conf, String _command, Object _container) {
    //  return extractObject(_conf, _conf.getLastPage().isRendering(), _command, _container);
    //}
    @Deprecated
    static Object extractObject(Configuration _conf, String _command, Object _container) {
        Map<String,LocalVariable> localVars_ = new Map<String,LocalVariable>();
        Map<String,LocalVariable> params_ = new Map<String,LocalVariable>();
        Map<String,LoopVariable> vars_ = new Map<String,LoopVariable>();
        return extractObject(_command, _conf, localVars_, vars_, params_, _container);
    }
    @Deprecated
    static Object extractObject(String _command,Configuration _conf, Map<String,LocalVariable> _locVars, Map<String,LoopVariable> _vars, Map<String,LocalVariable> _params, Object _container) {
        return extractObject(_command, _conf, _locVars, _vars, _params, _container, null);
    }
    @Deprecated

    @Deprecated
    private static CustList<Argument> getArguments(Configuration _conf, int _offs,String _args) {
        int indexFirstArg_ = CustList.INDEX_NOT_FOUND_ELT;
        if (_args.startsWith(CLASS_VAR_ARG)) {
            int indexCommma_ = _args.indexOf(COMMA);
            if (indexCommma_ > CustList.INDEX_NOT_FOUND_ELT) {
                indexFirstArg_ = indexCommma_;
            }
        } else {
            indexFirstArg_ = CustList.FIRST_INDEX;
        }
        if (indexFirstArg_ == CustList.INDEX_NOT_FOUND_ELT) {
            Class<?> class_ = classForName(_conf, 0, _args.substring(CLASS_VAR_ARG.length()), true);
            Object array_ = Array.newInstance(class_, 0);
            Argument a_ = new Argument();
            a_.setArgClass(array_.getClass());
            a_.setObject(array_);
            return new CustList<Argument>(a_);
        }
        Object globalObject_ = _conf.getLastPage().getGlobalArgument().getObject();
        Map<String,LoopVariable> vars_ = _conf.getLastPage().getVars();
        Map<String,LocalVariable> localVars_ = _conf.getLastPage().getLocalVars();
        Map<String,LocalVariable> params_ = _conf.getLastPage().getParameters();
        CustList<Argument> arguments_ = new CustList<Argument>();
        CustList<Object> argumentsOpt_ = new CustList<Object>();
        int lenArgs_ = _offs;
        String args_;
        Class<?> classArr_ = null;
        if (indexFirstArg_ > CustList.FIRST_INDEX) {
            args_ = _args.substring(indexFirstArg_+1);
            classArr_ = classForName(_conf, 0, _args.substring(CustList.SECOND_INDEX, indexFirstArg_), true);
        } else {
            args_ = _args;
        }
        for (String l: StringList.splitStrings(args_, COMMA)) {
            boolean opt_ = false;
            String var_ = l;
            try {
                if (l.endsWith(CLASS_VAR_ARG)) {
                    opt_ = true;
                    var_ = l.substring(0, l.length()-1);
                    argumentsOpt_.add(Argument.parseNumber(var_));
                } else if (!argumentsOpt_.isEmpty()) {
                    opt_ = true;
                    argumentsOpt_.add(Argument.parseNumber(var_));
                } else {
                    arguments_.add(Argument.numberToArgument(l));
                }
            } catch (RuntimeException _0) {
                Argument a_ = new Argument();
                if (var_.endsWith(GET_INDEX)) {
                    String l_ = var_.substring(CustList.FIRST_INDEX, var_.length() - GET_INDEX.length());
                    LoopVariable lv_ = getCurrentVariable(_conf, lenArgs_, vars_, l_);
                    if (opt_) {
                        argumentsOpt_.add(lv_.getIndex());
                        continue;
                    }
                    a_.setObject(lv_.getIndex());
                    //                    a_.setArgClass(Long.class);
                    a_.setArgClass(ConstClasses.classForName(lv_.getIndexClassName()));
                    arguments_.add(a_);
                    continue;
                }
                if (var_.endsWith(GET_PARAM)) {
                    String l_ = var_.substring(CustList.FIRST_INDEX, var_.length() - GET_PARAM.length());
                    LocalVariable lv_ = getCurrentLocVariable(_conf, lenArgs_, params_, l_);
                    if (opt_) {
                        argumentsOpt_.add(lv_.getElement());
                        continue;
                    }
                    a_.setObject(lv_.getElement());
                    a_.setArgClass(ConstClasses.classForName(lv_.getClassName()));
                    arguments_.add(a_);
                    continue;
                }
                if (var_.endsWith(GET_ATTRIBUTE)) {
                    String l_ = var_.substring(CustList.FIRST_INDEX, var_.length() - GET_ATTRIBUTE.length());
                    LoopVariable lv_ = getCurrentVariable(_conf, lenArgs_, vars_, l_);
                    if (opt_) {
                        argumentsOpt_.add(lv_.getElement());
                        continue;
                    }
                    a_.setObject(lv_.getElement());
                    a_.setArgClass(ConstClasses.classForName(lv_.getClassName()));
                    arguments_.add(a_);
                    continue;
                }
                if (var_.endsWith(GET_LOC_VAR)) {
                    String l_ = var_.substring(CustList.FIRST_INDEX, var_.length() - GET_LOC_VAR.length());
                    LocalVariable lv_ = getCurrentLocVariable(_conf, lenArgs_, localVars_, l_);
                    if (opt_) {
                        argumentsOpt_.add(lv_.getElement());
                        continue;
                    }
                    a_.setObject(lv_.getElement());
                    a_.setArgClass(ConstClasses.classForName(lv_.getClassName()));
                    arguments_.add(a_);
                    continue;
                }
                Object b_ = null;
                Class<?> cl_ = null;
                String field_ = l;
                if (l.startsWith(STATIC_PREFIX)) {
                    String next_ = l.substring(STATIC_PREFIX.length());
                    int firstDot_ = next_.indexOf(DOT);
                    if (firstDot_ > CustList.INDEX_NOT_FOUND_ELT) {
                        String className_ = next_.substring(CustList.FIRST_INDEX, firstDot_);
                        className_ = StringList.replace(className_, PATH_CLASS_SEPARATOR, DOT);
                        cl_ = classForName(_conf, STATIC_PREFIX.length(), className_, true);
                        //                        try {
                            //                            cl_ = ConstClasses.classForName(className_);
                            //                        } catch (Exception _1) {
                                //                            _conf.getLastPage().addToOffset(STATIC_PREFIX.length());
                        //                            throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.joinPages(RETURN_LINE));
                        //                        }
                        _conf.getLastPage().addToOffset(firstDot_);
                        field_ = next_.substring(firstDot_+1);
                    } else {
                        _conf.getLastPage().addToOffset(STATIC_PREFIX.length());
                        throw new GettingStaticException(_conf.joinPages(RETURN_LINE));
                    }
                } else {
//                    b_ = vars_.getVal(EMPTY_STRING).getElement();
                    b_ = globalObject_;
                    cl_ = b_.getClass();
                }
                Field f_;
                try {
                    f_ = SerializeXmlObject.getDeclaredField(cl_, field_);
                } catch (NoSuchDeclaredFieldException _1) {
                    throw new NoSuchDeclaredFieldException(_1.getMessage()+RETURN_LINE+_conf.joinPages(RETURN_LINE));
                }
                setAccess(f_, _conf.toContextEl());
                try {
                    Object o_ = ConverterMethod.getField(f_, b_);
                    if (opt_) {
                        argumentsOpt_.add(o_);
                    } else {
                        a_.setObject(o_);
                        a_.setArgClass(f_.getType());
                        arguments_.add(a_);
                    }
                } catch (RuntimeException _1) {
                    throw new GettingFieldException(_conf.joinPages(RETURN_LINE), _1);
                } catch (ExceptionInInitializerError _1) {
                    throw new ErrorCausingException(_conf.joinPages(RETURN_LINE), _1);
                }
            }
            lenArgs_ += l.length() + 1;
        }
        if (classArr_ != null) {
            Argument a_ = new Argument();
            int lenOpt_ = argumentsOpt_.size();
            Object array_ = Array.newInstance(classArr_, lenOpt_);
            for (int i = 0; i < lenOpt_; i++) {
                try {
                    Array.set(array_, i, argumentsOpt_.get(i));
                } catch (IllegalArgumentException _0) {
                    throw new DynamicCastClassException(classArr_.getName()+RETURN_LINE+_conf.joinPages(RETURN_LINE));
                }
            }
            a_.setObject(array_);
            a_.setArgClass(array_.getClass());
            arguments_.add(a_);
        }
        return arguments_;
    }
    @Deprecated
    private static Class<?>[] getClasses(Argument... _args) {
        int len_ = _args.length;
        Class<?>[] classes_ = new Class<?>[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args[i].getArgClass();
        }
        return classes_;
    }
    @Deprecated
    private static Object[] getObjects(Argument... _args) {
        int len_ = _args.length;
        Object[] classes_ = new Object[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args[i].getObject();
        }
        return classes_;
    }
    //@throws SuperClassNotFoundException
    @Deprecated
    static Class<?> getSuperClass(Class<?> _class, String _name) {
        boolean found_ = false;
        Class<?> class_ = _class;
        while (true) {
            if (class_.getName().equalsIgnoreCase(_name)) {
                found_ = true;
                break;
            }
            if (class_ == Object.class) {
                break;
            }
            class_ = class_.getSuperclass();
        }
        if (found_) {
            return class_;
        }
        throw new SuperClassNotFoundException(class_.getName(), _name);
    }
    @Deprecated
    @Deprecated
    static boolean isClassPathSeparator(char _char) {
        if (_char == PATH_CLASS_SEPARATOR_CHAR) {
            return true;
        }
        if (_char == PATH_INTERN_CLASS_SEPARATOR_CHAR) {
            return true;
        }
        return false;
    }
    @Deprecated
    static boolean isConstOrVarChar(char _char) {
        if (StringList.isWordChar(_char)) {
            return true;
        }
        if (_char == MINUS_CHAR) {
            return true;
        }
        if (_char == GET_LOC_VAR_CHAR) {
            return true;
        }
        return _char == GET_LOC_VAR_CHAR_SEC;
    }*/

    static Object instanceByString(Configuration _conf, Class<?> _class, String _arg) {
        Constructor<?> const_ = null;
        try {
            String name_ = _class.getName();
            Object value_;
            if (StringList.quickEq(name_, Integer.class.getName()) || StringList.quickEq(name_, int.class.getName())) {
                value_ = Integer.parseInt(_arg);
            } else if (StringList.quickEq(name_, Long.class.getName()) || StringList.quickEq(name_, long.class.getName())) {
                value_ = Long.parseLong(_arg);
            } else if (StringList.quickEq(name_, Short.class.getName()) || StringList.quickEq(name_, short.class.getName())) {
                value_ = Short.parseShort(_arg);
            } else if (StringList.quickEq(name_, Byte.class.getName()) || StringList.quickEq(name_, byte.class.getName())) {
                value_ = Byte.parseByte(_arg);
            } else if (StringList.quickEq(name_, BigInteger.class.getName())) {
                value_ = new BigInteger(_arg);
            } else if (StringList.quickEq(name_, BigDecimal.class.getName())) {
                value_ = new BigDecimal(_arg);
            } else if (StringList.quickEq(name_, Double.class.getName()) || StringList.quickEq(name_, double.class.getName())) {
                value_ = Double.parseDouble(_arg);
            } else if (StringList.quickEq(name_, Float.class.getName()) || StringList.quickEq(name_, float.class.getName())) {
                value_ = Float.parseFloat(_arg);
            } else if (StringList.quickEq(name_, AtomicInteger.class.getName())) {
                value_ = new AtomicInteger(Integer.parseInt(_arg));
            } else if (StringList.quickEq(name_, AtomicLong.class.getName())) {
                value_ = new AtomicLong(Long.parseLong(_arg));
            } else {
                const_ = _class.getConstructor(String.class);
                return const_.newInstance(_arg);
            }
            return value_;
        } catch (InstantiationException _0) {
            throw new RuntimeInstantiationException(_0, _conf.joinPages());
        } catch (NoSuchMethodException _0) {
            throw new NoSuchDeclaredMethodException(_class.getName()+RETURN_LINE+_conf.joinPages());
        } catch (InvocationTargetException _0) {
            throw new InvokingException(_0, _conf.joinPages());
        } catch (IllegalAccessException _0) {
            throw new BadAccessException(_0, const_.toString());
        } catch (VirtualMachineError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (ExceptionInInitializerError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        }
    }
    static void checkClassNotEmptyName(Configuration _conf, int _offest, String _className) {
        if (_className.isEmpty()) {
            return;
        }
        classForName(_conf, _offest, _className);
    }
    static Class<?> classForName(Configuration _conf, int _offest, String _className) {
        try {
            if (_className.startsWith(PrimitiveTypeUtil.PRIM)) {
                Class<?> cl_ = ConstClasses.getPrimitiveClass(_className.substring(1));
                if (cl_ == null) {
                    throw new RuntimeClassNotFoundException(_className+RETURN_LINE+_conf.joinPages());
                }
                return cl_;
            }
            if (StringList.quickEq(_className, ConstClasses.LISTABLE_ALIAS)) {
                return Listable.class;
            }
            if (StringList.quickEq(_className, ConstClasses.LISTABLE_ENTRIES_ALIAS)) {
                return ListableEntries.class;
            }
            String className_ = ConstClasses.getMapping(_className);
            if (className_ != null) {
                return ConstClasses.classAliasForNameNotInit(className_);
            }
            return ConstClasses.classAliasForNameNotInit(PrimitiveTypeUtil.getArrayClass(_className));
        } catch (RuntimeException _0) {
            _conf.getLastPage().addToOffset(_offest);
            throw new RuntimeClassNotFoundException(_className+RETURN_LINE+_conf.joinPages());
        } catch (VirtualMachineError _0) {
            _conf.getLastPage().addToOffset(_offest);
            throw new ErrorCausingException(_className+RETURN_LINE+_conf.joinPages(), new Struct(_0));
        } catch (ExceptionInInitializerError _0) {
            _conf.getLastPage().addToOffset(_offest);
            throw new ErrorCausingException(_className+RETURN_LINE+_conf.joinPages(), new Struct(_0));
        }
    }
    static Listable<?> castListable(Configuration _conf, int _off, Object _obj) {
        try {
            if (_obj == null) {
                String beginMess_ = _obj+SPACE+Listable.class.getName();
                _conf.getLastPage().addToOffset(_off);
                throw new NullObjectException(beginMess_+RETURN_LINE+_conf.joinPages());
            }
            return (Listable<?>) _obj;
        } catch (ClassCastException _0) {
            String beginMess_ = _obj.getClass().getName()+SPACE+Listable.class.getName();
            //            if (!_useNode) {
                //                throw new ClassCastException(beginMess_);
                //            }
            _conf.getLastPage().addToOffset(_off);
            throw new DynamicCastClassException(beginMess_+RETURN_LINE+_conf.joinPages());
        }
    }
    
    static EntryCust<?,?> castEntryCust(Configuration _conf, int _off, Object _obj) {
        try {
            if (_obj == null) {
                throw new NullObjectException(_conf.joinPages());
            }
            return (EntryCust<?,?>) _obj;
        } catch (ClassCastException _0) {
            String beginMess_ = _obj.getClass().getName()+SPACE+Iterable.class.getName();
            //            if (!_useNode) {
            //                throw new ClassCastException(beginMess_);
            //            }
            _conf.getLastPage().addToOffset(_off);
            throw new DynamicCastClassException(beginMess_+RETURN_LINE+_conf.joinPages());
        }
    }

    static Iterable<?> castIterable(Configuration _conf, int _off, Object _obj) {
        try {
            return (Iterable<?>) _obj;
        } catch (ClassCastException _0) {
            String beginMess_ = _obj.getClass().getName()+SPACE+Iterable.class.getName();
            //            if (!_useNode) {
            //                throw new ClassCastException(beginMess_);
            //            }
            _conf.getLastPage().addToOffset(_off);
            throw new DynamicCastClassException(beginMess_+RETURN_LINE+_conf.joinPages());
        }
    }

    static ListableEntries<?,?> castListableEntries(Configuration _conf, int _off, Object _obj) {
        try {
            if (_obj == null) {
                String beginMess_ = _obj+SPACE+ListableEntries.class.getName();
                _conf.getLastPage().addToOffset(_off);
                throw new NullObjectException(beginMess_+RETURN_LINE+_conf.joinPages());
            }
            return (ListableEntries<?,?>) _obj;
        } catch (ClassCastException _0) {
            String beginMess_ = _obj.getClass().getName()+SPACE+ListableEntries.class.getName();
            //            if (!_useNode) {
            //                throw new ClassCastException(beginMess_);
            //            }
            _conf.getLastPage().addToOffset(_off);
            throw new DynamicCastClassException(beginMess_+RETURN_LINE+_conf.joinPages());
        }
    }

//    static Object getValue(Configuration _conf, int _offsIndex, ListableEntries<?,?> _container, Object _key) {
//        try {
//            return _container.get(_key);
//        } catch (VirtualMachineError _0) {
//            //            if (!_useNode) {
//            //                throw new RuntimeException(_0);
//            //            }
//            _conf.getLastPage().addToOffset(_offsIndex);
//            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
//        } catch (RuntimeException _0) {
//            //            if (!_useNode) {
//            //                throw new RuntimeException(_0);
//            //            }
//            _conf.getLastPage().addToOffset(_offsIndex);
//            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
//        }
//    }


    static Iterator<?> iterator(Configuration _conf, Iterable<?> _it) {
        try {
            return _it.iterator();
        } catch (VirtualMachineError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (RuntimeException _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static boolean hasNext(Configuration _conf, Iterator<?> _it) {
        try {
            return _it.hasNext();
        } catch (VirtualMachineError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (RuntimeException _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static Object next(Configuration _conf, Iterator<?> _it) {
        try {
            return _it.next();
        } catch (VirtualMachineError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (RuntimeException _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static EntryCust<?, ?> nextEntry(Configuration _conf, Iterator<?> _it) {
        try {
            return (EntryCust<?, ?>)_it.next();
        } catch (VirtualMachineError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (RuntimeException _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static char getChar(Configuration _conf, String _obj) {
        if (_obj.length() != CustList.ONE_ELEMENT) {
            throw new CharacterFormatException(String.valueOf(CustList.ONE_ELEMENT)+RETURN_LINE+_conf.joinPages());
        }
        return _obj.charAt(CustList.FIRST_INDEX);
    }
    /**This method use the equal operator*/
    static boolean eq(Configuration _conf, Object _objOne, Object _objTwo) {
        try {
            if (_objOne == null) {
                if (_objTwo == null) {
                    return true;
                }
                return false;
            }
            if (_objTwo == null) {
                return false;
            }
            ImportingPage ip_ = _conf.getLastPage();
            String tmp_ = FormatHtml.TMP_VAR;
            int i_ = CustList.FIRST_INDEX;
            while (ip_.getLocalVars().contains(tmp_+i_)) {
                i_++;
            }
            Struct srtOne_ = new Struct(_objOne);
            LocalVariable lvOne_ = new LocalVariable();
            lvOne_.setClassName(ConstClasses.resolve(srtOne_.getClassName()));
            lvOne_.setStruct(srtOne_);
            ip_.getLocalVars().put(tmp_+i_, lvOne_);
            String nameOne_ = tmp_+i_;
            i_++;
            while (ip_.getLocalVars().contains(tmp_+i_)) {
                i_++;
            }
            Struct srtTwo_ = new Struct(_objTwo);
            LocalVariable lvTwo_ = new LocalVariable();
            lvTwo_.setClassName(ConstClasses.resolve(srtTwo_.getClassName()));
            lvTwo_.setStruct(srtTwo_);
            ip_.getLocalVars().put(tmp_+i_, lvTwo_);
            String nameTwo_ = tmp_+i_;
            Argument arg_ = ElUtil.processEl(nameOne_+GET_LOC_VAR+CMP+nameTwo_+GET_LOC_VAR, 0, _conf.toContextEl());
            Boolean ret_ = (Boolean)arg_.getObject();
            ip_.getLocalVars().removeKey(nameOne_);
            ip_.getLocalVars().removeKey(nameTwo_);
            return ret_;
        } catch (VirtualMachineError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static void checkNullPointer(Configuration _conf, Object _obj) {
        try {
            _obj.getClass();
        } catch (NullPointerException _0) {
            throw new NullObjectException(_conf.joinPages());
        }
    }
    static String valueOf(Configuration _conf, Object _obj) {
        try {
            return String.valueOf(_obj);
        } catch (VirtualMachineError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (RuntimeException _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static String toString(Configuration _conf, Object _obj) {
        try {
            return _obj.toString();
        } catch (VirtualMachineError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (RuntimeException _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static Listable<?> entryList(Configuration _conf, int _offsIndex, ListableEntries<?,?> _container) {
        try {
            return _container.entryList();
        } catch (VirtualMachineError _0) {
            //            if (!_useNode) {
                //                throw new RuntimeException(_0);
                //            }
            _conf.getLastPage().addToOffset(_offsIndex);
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (RuntimeException _0) {
            //            if (!_useNode) {
                //                throw new RuntimeException(_0);
                //            }
            _conf.getLastPage().addToOffset(_offsIndex);
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static Listable<?> getKeys(Configuration _conf, boolean _callSort, int _offsIndex, ListableEntries<?,?> _container) {
        try {
//            Listable<?> keys_ = _container.getKeys();
//            if (!(_container instanceof SortableMap) && _callSort) {
//                keys_ = orderedList(keys_);
//            }
            return _container.getKeys();
        } catch (VirtualMachineError _0) {
            //            if (!_useNode) {
                //                throw new RuntimeException(_0);
            //            }
            _conf.getLastPage().addToOffset(_offsIndex);
            throw new GettingKeysException(_conf.joinPages(), new Struct(_0));
        } catch (RuntimeException _0) {
            //            if (!_useNode) {
            //                throw new RuntimeException(_0);
            //            }
            _conf.getLastPage().addToOffset(_offsIndex);
            throw new GettingKeysException(_conf.joinPages(), new Struct(_0));
        }
    }

    /*private static int intValue(Configuration _conf, int _offsIndex, Object _object) {
        try {
            return ((Number)_object).intValue();
        } catch (VirtualMachineError _0) {
            //            if (!_useNode) {
            //                throw new RuntimeException(_0);
            //            }
            _conf.getLastPage().addToOffset(_offsIndex);
            throw new ErrorCausingException(_conf.joinPages(RETURN_LINE), _0);
        } catch (RuntimeException _0) {
            //            if (!_useNode) {
            //                throw new RuntimeException(_0);
            //            }
            _conf.getLastPage().addToOffset(_offsIndex);
            throw new InvokeRedinedMethException(_conf.joinPages(RETURN_LINE), _0);
        }
    }
    @Deprecated
    private static Object getArrayElement(Configuration _conf, int _offsIndex, Object _container, int _index) {
        try {
            return Array.get(_container, _index);
        } catch (ArrayIndexOutOfBoundsException _0) {
            int size_ = Array.getLength(_container);
            String beginMess_ = EMPTY_STRING;
            if (_index < 0) {
                beginMess_ += _index;
            } else {
                beginMess_ += _index+SUP_MESSAGE+size_;
            }
            //            if (!_useNode) {
                //                throw new IndexOutOfBoundsException(beginMess_);
            //            }
            _conf.getLastPage().addToOffset(_offsIndex);
            throw new BadIndexException(IN_ARRAY, beginMess_+RETURN_LINE+_conf.joinPages(RETURN_LINE));
//            throw new ArrayIndexOutOfBoundsException(beginMess_+RETURN_LINE+_conf.joinPages(RETURN_LINE));
            //        } catch (RuntimeException _0) {
            ////            if (!_useNode) {
            ////                throw new RuntimeException(_0);
            ////            }
            //            _conf.getLastPage().addToOffset(_offsIndex);
            //            throw new RuntimeException(_conf.joinPages(RETURN_LINE), _0);
        }
    }
    @Deprecated
    private static Object getElement(Configuration _conf, int _offsCast, int _offsIndex, Object _container, int _index) {
        try {
            if (_container.getClass().isArray()) {
                return getArrayElement(_conf, _offsIndex, _container, _index);
            }
            //            return ((Listable<?>) _container).get(_index);
            return getElement(_conf, _offsIndex, (Listable<?>) _container, _index);
            //        } catch (IndexOutOfBoundsException _0) {
            //            int size_ = ((Listable<?>) _container).size();
            //            String beginMess_ = EMPTY_STRING;
            //            if (_index < 0) {
            //                beginMess_ += _index;
            //            } else {
            //                beginMess_ += _index+" >= "+size_;
            //            }
            //            if (_conf == null || _useNode) {
            //                throw new IndexOutOfBoundsException(beginMess_);
            //            }
            //            _conf.getLastPage().addToOffset(_offsIndex);
            //            throw new IndexOutOfBoundsException(beginMess_+RETURN_LINE+_conf.joinPages(RETURN_LINE));
        } catch (ClassCastException _0) {
            String beginMess_ = _container.getClass().getName()+SPACE+Listable.class.getName();
            //            if (!_useNode) {
            //                throw new ClassCastException(beginMess_);
            //            }
            _conf.getLastPage().addToOffset(_offsCast);
            throw new DynamicCastClassException(beginMess_+RETURN_LINE+_conf.joinPages(RETURN_LINE));
        }
    }
    @Deprecated
    private static Object getElement(Configuration _conf, int _offsIndex, Listable<?> _container, int _index) {
        try {
            return _container.get(_index);
        } catch (IndexOutOfBoundsException _0) {
            int size_ = _container.size();
            String beginMess_ = EMPTY_STRING;
            if (_index < 0) {
                beginMess_ += _index;
            } else {
                beginMess_ += _index+SUP_MESSAGE+size_;
            }
            //            if (!_useNode) {
                //                throw new IndexOutOfBoundsException(beginMess_);
            //            }
            _conf.getLastPage().addToOffset(_offsIndex);
            throw new BadIndexException(IN_LIST, beginMess_+RETURN_LINE+_conf.joinPages(RETURN_LINE));
//            throw new IndexOutOfBoundsException(beginMess_+RETURN_LINE+_conf.joinPages(RETURN_LINE));
        } catch (VirtualMachineError _0) {
            //            if (!_useNode) {
            //                throw new RuntimeException(_0);
            //            }
            _conf.getLastPage().addToOffset(_offsIndex);
            throw new ErrorCausingException(_conf.joinPages(RETURN_LINE), _0);
        } catch (RuntimeException _0) {
            //            if (!_useNode) {
            //                throw new RuntimeException(_0);
            //            }
            _conf.getLastPage().addToOffset(_offsIndex);
            throw new InvokeRedinedMethException(_conf.joinPages(RETURN_LINE), _0);
        }
    }

    @Deprecated
    private static int getIndex(Configuration _conf, int _offset,String _info, Object _globalObject,
            Map<String,LocalVariable> _locVars, Map<String,LoopVariable> _vars, Map<String,LocalVariable> _params) {
        int index_;
        try {
            index_ = Integer.parseInt(_info);
        } catch (RuntimeException _0) {
            if (_info.endsWith(GET_INDEX)) {
                String l_ = _info.substring(CustList.FIRST_INDEX, _info.length() - GET_INDEX.length());
                //                LoopVariable lv_ = _vars.getVal(l_);
                LoopVariable lv_ = getCurrentVariable(_conf, _offset, _vars, l_);
                index_ = (int) lv_.getIndex();
            } else if (_info.endsWith(GET_PARAM)) {
                String l_ = _info.substring(CustList.FIRST_INDEX, _info.length() - GET_PARAM.length());
                //                LoopVariable lv_ = _vars.getVal(l_);
                LocalVariable lv_ = getCurrentLocVariable(_conf, _offset, _params, l_);
                //                index_ = ((Number) lv_.getElement()).intValue();
                index_ = intValue(_conf, _offset, lv_.getElement());
            } else if (_info.endsWith(GET_ATTRIBUTE)) {
                String l_ = _info.substring(CustList.FIRST_INDEX, _info.length() - GET_ATTRIBUTE.length());
                //                LoopVariable lv_ = _vars.getVal(l_);
                LoopVariable lv_ = getCurrentVariable(_conf, _offset, _vars, l_);
                //                index_ = ((Number) lv_.getElement()).intValue();
                index_ = intValue(_conf, _offset, lv_.getElement());
            } else if (_info.endsWith(GET_LOC_VAR)) {
                String l_ = _info.substring(CustList.FIRST_INDEX, _info.length() - GET_LOC_VAR.length());
                //                LocalVariable lv_ = _locVars.getVal(l_);
                LocalVariable lv_ = getCurrentLocVariable(_conf, _offset, _locVars, l_);
                //                index_ = ((Number) lv_.getElement()).intValue();
                index_ = intValue(_conf, _offset, lv_.getElement());
            } else {
                Object b_ = null;
                Class<?> cl_ = null;
                String field_ = _info;
                if (_info.startsWith(STATIC_PREFIX)) {
                    String next_ = _info.substring(STATIC_PREFIX.length());
                    int firstDot_ = next_.indexOf(DOT);
                    if (firstDot_ > CustList.INDEX_NOT_FOUND_ELT) {
                        String className_ = next_.substring(CustList.FIRST_INDEX, firstDot_);
                        className_ = StringList.replace(className_, PATH_CLASS_SEPARATOR, DOT);
                        cl_ = classForName(_conf, STATIC_PREFIX.length(), className_, true);
                        //                        try {
                            //                            cl_ = ConstClasses.classForName(className_);
                            //                        } catch (Exception _1) {
                        //                            _conf.getLastPage().addToOffset(STATIC_PREFIX.length());
                        //                            throw new RuntimeClassNotFoundException(className_+RETURN_LINE+_conf.joinPages(RETURN_LINE));
                        //                        }
                        _conf.getLastPage().addToOffset(firstDot_);
                        field_ = next_.substring(firstDot_+1);
                    } else {
                        _conf.getLastPage().addToOffset(STATIC_PREFIX.length());
                        throw new GettingStaticException(_conf.joinPages(RETURN_LINE));
                    }
                } else {
//                    Map<String,LoopVariable> vars_ = _conf.getLastPage().getVars();
//                    b_ = vars_.getVal(EMPTY_STRING).getElement();
                    b_ = _globalObject;
                    cl_ = b_.getClass();
                }
                Field f_;
                try {
                    f_ = SerializeXmlObject.getDeclaredField(cl_, field_);
                } catch (NoSuchDeclaredFieldException _1) {
                    throw new NoSuchDeclaredFieldException(_1.getMessage()+RETURN_LINE+_conf.joinPages(RETURN_LINE));
                }
                ContextEl context_ = _conf.toContextEl();
                context_.getAccessValue().setAccess(f_, context_);
//                setAccess(f_, _conf.toContextEl());
                try {
                    Object o_ = ConverterMethod.getField(f_, b_);
                    index_ = intValue(_conf, _offset, o_);
                } catch (ExceptionInInitializerError _1) {
                    throw new ErrorCausingException(_conf.joinPages(RETURN_LINE), _1);
                } catch (VirtualMachineError _1) {
                    throw new ErrorCausingException(_conf.joinPages(RETURN_LINE), _1);
                } catch (RuntimeException _1) {
                    throw new ErrorCausingException(_conf.joinPages(RETURN_LINE), _1);
                }
            }
        }
        return index_;
    }
    @Deprecated
    private static long getCurrentIndex(Configuration _conf, int _offset,Map<String,LoopVariable> _vars, String _candidate) {
        if (!_vars.contains(_candidate)) {
            _conf.getLastPage().addToOffset(_offset);
            throw new UndefinedVariableException(_conf.joinPages(RETURN_LINE), _candidate, LOOP_MESSAGE);
        }
        return _vars.getVal(_candidate).getIndex();
    }
    @Deprecated
    private static Object getCurrentElement(Configuration _conf, int _offset, Map<String,LoopVariable> _vars, String _candidate) {
        if (!_vars.contains(_candidate)) {
            _conf.getLastPage().addToOffset(_offset);
            throw new UndefinedVariableException(_conf.joinPages(RETURN_LINE), _candidate, LOOP_MESSAGE);
        }
        return _vars.getVal(_candidate).getElement();
    }*/

    static LoopVariable getCurrentVariable(Configuration _conf, int _offset,StringMap<LoopVariable> _vars, String _candidate) {
        if (!_vars.contains(_candidate)) {
            _conf.getLastPage().addToOffset(_offset);
            throw new UndefinedVariableException(_conf.joinPages(), _candidate, LOOP_MESSAGE);
        }
        return _vars.getVal(_candidate);
    }
    /*@Deprecated
    static Object getCurrentLocElement(Configuration _conf, int _offset,StringMap<LocalVariable> _vars, String _candidate) {
        if (!_vars.contains(_candidate)) {
            _conf.getLastPage().addToOffset(_offset);
            throw new UndefinedVariableException(_conf.joinPages(RETURN_LINE), _candidate, LOCAL_MESSAGE);
        }
        return _vars.getVal(_candidate).getElement();
    }*/

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
        //        boolean quoted_ = _element.hasAttribute(ATTRIBUTE_QUOTED);
        String preformatted_;
        IdList<Object> objects_ = new IdList<Object>();
        if (value_.startsWith(CALL_METHOD)) {
            if (!_conf.noPages()) {
                _conf.getLastPage().setOffset(1);
            }
//            preformatted_ = toString(_conf, improvedExtractObject(_conf, value_.substring(1)));
            preformatted_ = toString(_conf, ElUtil.processEl(value_, 1, _conf.toContextEl()).getObject());
            if (!_conf.noPages()) {
                _conf.getLastPage().setKey(EMPTY_STRING);
                _conf.getLastPage().setMessageValue(preformatted_);
            }
            //            for (Element n: XmlParser.childrenElements(_element)) {
            //                String attribute_ = n.getAttribute(ATTRIBUTE_VALUE);
            //                if (quoted_) {
            //                    attribute_ = StringList.replace(attribute_, FORMAT_QUOTE, ESCAPED_FORMAT_QUOTE);
            //                }
            //                attribute_ = formatNumVariables(attribute_, _conf, _vars, _files);
            //                if (attribute_.startsWith(CALL_METHOD)) {
            //                    objects_.add(improvedExtractObject(_vars, attribute_.substring(1)));
            //                } else {
            //                    objects_.add(attribute_);
            //                }
            //            }
        } else {
            StringList elts_ = StringList.splitStrings(value_, COMMA);
            //        String var_ = value_.split(COMMA)[0];
            String var_ = elts_.first();
            String fileName_ = getProperty(_conf, var_);
            if (fileName_ == null) {
                fileName_ = var_;
            }
            String key_ = elts_.last();
            if (!_conf.noPages()) {
                _conf.getLastPage().setOffset(var_.length()+COMMA.length());
            }
            //            key_ = formatIndexVariables(key_, _locVars, _vars);
            key_ = formatNumVariables(key_, _conf, _ip, _files);
            StringMap<String> messages_ = ExtractFromResources.getInnerMessagesFromLocaleClass(_conf, _loc, fileName_, _files, _resourcesFolder);
            //        String preformatted_ = messages_.getVal(value_.split(COMMA)[1]);
            //            if (!messages_.contains(key_)) {
            //                //check if key_ is in messages_ from fileNamePath_
            //                String fileNamePath_ = StreamTextFile.getPropertiesPath(_conf.getMessagesFolder(),_loc,fileName_);
            //                throw new MessageKeyNotFoundException(key_, fileNamePath_);
            //            }
            //            preformatted_ = messages_.getVal(key_);
            preformatted_ = ExtractFromResources.getFormat(messages_, key_, _conf, _loc, fileName_);
            if (!_conf.noPages()) {
                _conf.getLastPage().setKey(key_);
                _conf.getLastPage().setMessageValue(preformatted_);
            }
            preformatted_ = XmlParser.transformSpecialChars(preformatted_, _element.hasAttribute(ATTRIBUTE_ESCAPED_EAMP));
            //            for (Element n: XmlParser.childrenElements(_element)) {
            //                String attribute_ = n.getAttribute(ATTRIBUTE_VALUE);
            //                if (quoted_) {
            //                    attribute_ = StringList.replace(attribute_, FORMAT_QUOTE, ESCAPED_FORMAT_QUOTE);
            //                }
            //                attribute_ = formatNumVariables(attribute_, _conf, _vars, _files);
            //                if (attribute_.startsWith(CALL_METHOD)) {
            //                    objects_.add(improvedExtractObject(_vars, attribute_.substring(1)));
            //                } else {
            //                    objects_.add(attribute_);
            //                }
            //            }
        }
        for (Element n: XmlParser.childrenElements(_element)) {
            String attribute_ = n.getAttribute(ATTRIBUTE_VALUE);
            if (n.hasAttribute(ATTRIBUTE_QUOTED)) {
                if (n.hasAttribute(ATTRIBUTE_ESCAPED)) {
                    objects_.add(escapeParam(_conf, attribute_));
                } else {
                    objects_.add(attribute_);
                }
                continue;
            }
            //            if (quoted_) {
                //                attribute_ = StringList.replace(attribute_, FORMAT_QUOTE, ESCAPED_FORMAT_QUOTE);
            //            }
            //            attribute_ = formatNumVariables(attribute_, _conf, _locVars, _vars, _files);
            //            if (attribute_.startsWith(CALL_METHOD)) {
            //                objects_.add(improvedExtractObject(_locVars, _vars, attribute_.substring(1)));
            //            } else {
            //                objects_.add(attribute_);
            //            }
            if (!_conf.noPages()) {
                _conf.getLastPage().setProcessingNode(n);
                _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VALUE);
                _conf.getLastPage().setLookForAttrValue(true);
                _conf.getLastPage().setOffset(0);
            }
//            Object o_ = improvedExtractObject(_conf, attribute_);
            int begin_ = 0;
            if (attribute_.startsWith(CALL_METHOD)) {
                begin_ = 1;
            }
            Object o_ = ElUtil.processEl(attribute_, begin_, _conf.toContextEl()).getObject();
            if (n.hasAttribute(ATTRIBUTE_ESCAPED)) {
                objects_.add(escapeParam(_conf,o_));
            } else {
                objects_.add(o_);
            }
//            objects_.add(improvedExtractObject(_conf, _locVars, _vars, attribute_));
        }
        //        if (value_.startsWith(CALL_METHOD)) {
        //            preformatted_ = improvedExtractObject(_vars, value_.substring(1)).toString();
        //            for (Element n: XmlParser.childrenElements(_element)) {
        //                String attribute_ = n.getAttribute(ATTRIBUTE_VALUE);
        //                if (attribute_.startsWith(CALL_METHOD)) {
        //                    objects_.add(improvedExtractObject(_vars, attribute_.substring(1)));
        //                } else {
        //                    objects_.add(attribute_);
        //                }
        //            }
        //            return StringList.simpleFormat(preformatted_, objects_.toArray());
        //        }
        //        StringList elts_ = StringList.splitStrings(value_, COMMA);
        //        //        String var_ = value_.split(COMMA)[0];
        //        String var_ = elts_.first();
        //        String fileName_ = _conf.getProperties().getVal(var_);
        //        if (fileName_ == null) {
        //            fileName_ = var_;
        //        }
        //        StringMap<String> messages_ = getInnerMessagesFromLocaleClass(_conf.getMessagesFolder(), _loc, fileName_, _files, _resourcesFolder);
        //        //        String preformatted_ = messages_.getVal(value_.split(COMMA)[1]);
        //        preformatted_ = messages_.getVal(elts_.last());
        //        preformatted_ = XmlParser.transformSpecialChars(preformatted_);
        //        for (Element n: XmlParser.childrenElements(_element)) {
        //            String attribute_ = n.getAttribute(ATTRIBUTE_VALUE);
        //            if (attribute_.startsWith(CALL_METHOD)) {
        //                objects_.add(improvedExtractObject(_vars, attribute_.substring(1)));
        //            } else {
        //                objects_.add(attribute_);
        //            }
        //        }
        return StringList.simpleFormat(preformatted_, objects_.toArray());
    }

    private static String escapeParam(Configuration _conf, Object _arg) {
        String str_ = valueOf(_conf, _arg);
        StringMap<String> rep_ = new StringMap<String>();
        String quote_ = String.valueOf(QUOTE);
        rep_.put(String.valueOf(LEFT_EL), quote_+LEFT_EL+quote_);
        rep_.put(String.valueOf(RIGHT_EL), quote_+RIGHT_EL+quote_);
        rep_.put(String.valueOf(QUOTE), quote_+quote_);
        return StringList.replace(str_, rep_);
    }

    static String getMessageFolder(Configuration _conf) {
        try {
            return _conf.getMessagesFolder();
        } catch (VirtualMachineError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (RuntimeException _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    private static MathFactory<?> getMathFactory(Configuration _conf) {
        try {
            return _conf.getMathFactory();
        } catch (VirtualMachineError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (RuntimeException _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static String getProperty(Configuration _conf, String _key) {
        try {
            return _conf.getProperties().getVal(_key);
        } catch (VirtualMachineError _0) {
            throw new ErrorCausingException(_conf.joinPages(), new Struct(_0));
        } catch (RuntimeException _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
}
