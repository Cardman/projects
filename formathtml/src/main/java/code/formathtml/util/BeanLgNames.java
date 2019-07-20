package code.formathtml.util;

import code.bean.translator.Translator;
import code.bean.validator.Validator;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.NumberInfos;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.formathtml.exec.RendDynOperationNode;
import code.sml.Element;
import code.util.CustList;
import code.util.Replacement;
import code.util.StringList;
import code.util.StringMap;
import code.util.ints.MathFactory;

public abstract class BeanLgNames extends LgNames {

    static final String VALIDATE = "validate";
    static final String SET_FORMS = "setForms";
    static final String GET_FORMS = "getForms";
    static final String SET_LANGUAGE = "setLanguage";
    static final String GET_LANGUAGE = "getLanguage";
    static final String SET_SCOPE = "setScope";
    static final String GET_SCOPE = "getScope";
    static final String SET_DATA_BASE = "setDataBase";
    static final String GET_DATA_BASE = "getDataBase";
    static final String BEFORE_DISPLAYING = "beforeDisplaying";
    static final String ALIAS_STRING_MAP_OBJECT = "code.util.StringMapObject";
    static final String BEAN = "code.bean.Bean";
    private static final int DEFAULT_RADIX = 10;
    private static final long MULTMIN_RADIX_TEN = Long.MIN_VALUE / DEFAULT_RADIX;
    private static final long N_MULTMAX_RADIX_TEN = -Long.MAX_VALUE / DEFAULT_RADIX;
    private static final String ON = "on";
    private final String validator = "code.bean.validator.Validator";

    private final String valueChangedEvent = "code.formathtml.util.ValueChangeEvent";

    private String errorEl = "$badEl";
    private String aliasRate;
    private String aliasDataBase;

    private final String custEntry = "$custentry";
    private final String custEntries = "$custentries";
    private String aliasDisplayable;
    private String aliasDisplay;
    private String custList = "$custlist";
    private String custMap = "$custmap";
    private String aliasSimpleIteratorType = "code.util.SimpleItr";
    private String aliasSimpleIterableType = "code.util.ints.SimpleIterable";
    private String aliasCountable = "code.util.ints.Countable";

    private String aliasGet;
    private String aliasSize;
    private String aliasSimpleIterator;

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

    public StringMap<String> getIterables() {
        return iterables;
    }

    public abstract void buildIterables(Configuration _context);

    public abstract String getIteratorVar();
    public abstract String getHasNextVar();
    public abstract String getNextVar();
    public abstract CustList<RendDynOperationNode> getExpsIterator();
    public abstract CustList<RendDynOperationNode> getExpsHasNext();
    public abstract CustList<RendDynOperationNode> getExpsNext();

    public abstract String processString(Argument _arg,Configuration _cont);

    public abstract String getIteratorTableVarCust();

    public abstract String getHasNextPairVarCust();

    public abstract String getNextPairVarCust();

    public abstract String getFirstVarCust();

    public abstract String getSecondVarCust();

    public abstract CustList<RendDynOperationNode> getExpsIteratorTableCust();

    public abstract CustList<RendDynOperationNode> getExpsHasNextPairCust();

    public abstract CustList<RendDynOperationNode> getExpsNextPairCust();

    public abstract CustList<RendDynOperationNode> getExpsFirstCust();

    public abstract CustList<RendDynOperationNode> getExpsSecondCust();
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
    public String getStdBeanStructClassName(Object _struct, ContextEl _context) {
        return getAliasObject();
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
            res_.setResult(new StringStruct(Long.toString(((NumberStruct)_instance).longStruct())));
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
                        adapt_[i_] = ((NumberStruct)s).byteStruct();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimShort())) {
                    short[] adapt_ = new short[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).shortStruct();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimInteger())) {
                    int[] adapt_ = new int[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).intStruct();
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
                        adapt_[i_] = ((NumberStruct)s).longStruct();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimFloat())) {
                    float[] adapt_ = new float[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).floatStruct();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimDouble())) {
                    double[] adapt_ = new double[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).doubleStruct();
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
                        args_[i] = ((NumberStruct) argStruct_).longStruct();
                    }
                } else if (argStruct_ instanceof IntStruct) {
                    args_[i] = ((NumberStruct) argStruct_).intStruct();
                } else if (argStruct_ instanceof DoubleStruct) {
                    args_[i] = ((DoubleStruct) argStruct_).doubleStruct();
                } else {
                    if (StringList.quickEq(pType_, _stds.getAliasPrimChar())) {
                        args_[i] = (char) ((NumberStruct) argStruct_).intStruct();
                    } else {
                        args_[i] = ((NumberStruct) argStruct_).longStruct();
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
                    adapt_[i_] = ((NumberStruct)s).byteStruct();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasPrimShort())) {
                short[] adapt_ = new short[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((NumberStruct)s).shortStruct();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasPrimInteger())) {
                int[] adapt_ = new int[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((NumberStruct)s).intStruct();
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
                    adapt_[i_] = ((NumberStruct)s).longStruct();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasPrimFloat())) {
                float[] adapt_ = new float[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((NumberStruct)s).floatStruct();
                    i_++;
                }
                return adapt_;
            }
            if (StringList.quickEq(compo_, _stds.getAliasPrimDouble())) {
                double[] adapt_ = new double[str_.length];
                int i_ = CustList.FIRST_INDEX;
                for (Struct s: str_) {
                    adapt_[i_] = ((NumberStruct)s).doubleStruct();
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
                return ((IntStruct) _args).intStruct();
            }
            return ((NumberStruct) _args).longStruct();
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
        return ALIAS_STRING_MAP_OBJECT;
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
        return BEAN;
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
