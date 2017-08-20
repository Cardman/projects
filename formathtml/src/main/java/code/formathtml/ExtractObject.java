package code.formathtml;
import java.lang.reflect.Constructor;
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
import code.expressionlanguage.exceptions.InvokeRedinedMethException;
import code.expressionlanguage.exceptions.UndefinedVariableException;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.formathtml.exceptions.CharacterFormatException;
import code.formathtml.exceptions.GettingKeysException;
import code.formathtml.exceptions.InexistingTranslatorException;
import code.serialize.ConverterMethod;
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
    static final String EMPTY_STRING = "";
    static final String RETURN_LINE = "\n";
    static final String RETURN_TAB = "\n\t";

    static final String SPACE = " ";
    static final String VAR_METHOD = "varMethod";
    static final String BEAN_ATTRIBUTE = "bean";
    static final String COMMA = ",";
    static final String DOT = ".";
    private static final String GET_LOC_VAR = ";.";
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
    private static final char BEGIN_TR = '[';
    private static final char END_TR = ']';
    private static final char QUOTE = 39;
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
                } catch (Throwable _0) {
                    _conf.getLastPage().setOffset(context_.getNextIndex());
                    throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
                }
                str_.append(ExtractObject.valueOf(_conf, o_));
                i_ = context_.getNextIndex();
                continue;
            } else if (cur_ == RIGHT_EL){
                _conf.getLastPage().setOffset(i_);
                throw new BadExpressionLanguageException(arg_.toString()+RETURN_LINE+_conf.joinPages());
            } else {
                str_.append(cur_);
            }
            i_++;
        }
        return str_.toString();
    }

    static Object evaluateMathExpression(ImportingPage _ip, Configuration _conf, boolean _evalBool, String _mathExp) {
        MathFactory<?> mathFact_ = getMathFactory(_conf);
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
                    throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
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
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }

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
                return ConverterMethod.newInstance(const_, _arg);
            }
            return value_;
        } catch (RuntimeInstantiationException _0) {
            throw new RuntimeInstantiationException(_0, _conf.joinPages());
        } catch (NoSuchMethodException _0) {
            throw new NoSuchDeclaredMethodException(_class.getName()+RETURN_LINE+_conf.joinPages());
        } catch (Throwable _0) {
            throw new InvokingException(_0, _conf.joinPages());
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
                return ConstClasses.classForNameNotInit(className_);
            }
            return ConstClasses.classForNameNotInit(PrimitiveTypeUtil.getArrayClass(_className));
        } catch (Throwable _0) {
            _conf.getLastPage().addToOffset(_offest);
            throw new RuntimeClassNotFoundException(_className+RETURN_LINE+_conf.joinPages());
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
            _conf.getLastPage().addToOffset(_off);
            throw new DynamicCastClassException(beginMess_+RETURN_LINE+_conf.joinPages());
        }
    }

    static Iterable<?> castIterable(Configuration _conf, int _off, Object _obj) {
        try {
            return (Iterable<?>) _obj;
        } catch (ClassCastException _0) {
            String beginMess_ = _obj.getClass().getName()+SPACE+Iterable.class.getName();
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
            _conf.getLastPage().addToOffset(_off);
            throw new DynamicCastClassException(beginMess_+RETURN_LINE+_conf.joinPages());
        }
    }

    static Iterator<?> iterator(Configuration _conf, Iterable<?> _it) {
        try {
            return _it.iterator();
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static boolean hasNext(Configuration _conf, Iterator<?> _it) {
        try {
            return _it.hasNext();
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static Object next(Configuration _conf, Iterator<?> _it) {
        try {
            return _it.next();
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static EntryCust<?, ?> nextEntry(Configuration _conf, Iterator<?> _it) {
        try {
            return (EntryCust<?, ?>)_it.next();
        } catch (Throwable _0) {
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
        } catch (Throwable _0) {
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
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static String toString(Configuration _conf, Object _obj) {
        try {
            return _obj.toString();
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static Listable<?> entryList(Configuration _conf, int _offsIndex, ListableEntries<?,?> _container) {
        try {
            return _container.entryList();
        } catch (Throwable _0) {
            _conf.getLastPage().addToOffset(_offsIndex);
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static Listable<?> getKeys(Configuration _conf, boolean _callSort, int _offsIndex, ListableEntries<?,?> _container) {
        try {
            return _container.getKeys();
        } catch (Throwable _0) {
            _conf.getLastPage().addToOffset(_offsIndex);
            throw new GettingKeysException(_conf.joinPages(), new Struct(_0));
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
        IdList<Object> objects_ = new IdList<Object>();
        if (value_.startsWith(CALL_METHOD)) {
            if (!_conf.noPages()) {
                _conf.getLastPage().setOffset(1);
            }
            preformatted_ = toString(_conf, ElUtil.processEl(value_, 1, _conf.toContextEl()).getObject());
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
            preformatted_ = XmlParser.transformSpecialChars(preformatted_, _element.hasAttribute(ATTRIBUTE_ESCAPED_EAMP));
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
            Object o_ = ElUtil.processEl(attribute_, begin_, _conf.toContextEl()).getObject();
            if (n.hasAttribute(ATTRIBUTE_ESCAPED)) {
                objects_.add(escapeParam(_conf,o_));
            } else {
                objects_.add(o_);
            }
        }
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
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    private static MathFactory<?> getMathFactory(Configuration _conf) {
        try {
            return _conf.getMathFactory();
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
    static String getProperty(Configuration _conf, String _key) {
        try {
            return _conf.getProperties().getVal(_key);
        } catch (Throwable _0) {
            throw new InvokeRedinedMethException(_conf.joinPages(), new Struct(_0));
        }
    }
}
