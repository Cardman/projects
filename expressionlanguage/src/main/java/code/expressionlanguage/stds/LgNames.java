package code.expressionlanguage.stds;

import code.expressionlanguage.*;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.KeyValueMemberName;
import code.expressionlanguage.errors.stds.ErrorCat;
import code.expressionlanguage.errors.stds.StdWordError;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.inherits.TypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.methods.ForEachLoop;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.PredefinedClasses;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.variables.LocalVariable;
import code.maths.montecarlo.AbMonteCarlo;
import code.util.*;

public abstract class LgNames {

    public static final String DEFAULT_PKG = "DefaultPkg";
    public static final String FIELD_MAX_VALUE = "MAX_VALUE";
    public static final String FIELD_MIN_VALUE = "MIN_VALUE";
    public static final String BAD_ENCODE = "BadEncode";
    public static final String DIVISION_ZERO = "DivisionZero";
    public static final String CHAR_SEQUENCE = "CharSequence";
    public static final String ITERATOR_TYPE = "IteratorType";
    public static final String ENUM_PARAM = "EnumParam";
    public static final String GET_MESSAGE = "GetMessage";
    public static final String ITERATOR_TABLE_TYPE_VAR_FIRST = "IteratorTableTypeVarFirst";
    public static final String ITERATOR_TABLE_TYPE_VAR_SECOND = "IteratorTableTypeVarSecond";
    public static final String EQUALS = "Equals";
    public static final String LONG = "Long";
    public static final String SHORT = "Short";
    public static final String PRIM_CHAR = "PrimChar";
    public static final String NUMBER = "Number";
    public static final String PARSE_INT = "ParseInt";
    public static final String COMPARE = "Compare";
    public static final String INT_VALUE = "IntValue";
    public static final String BOOLEAN = "Boolean";
    public static final String PRIM_LONG = "PrimLong";
    public static final String BYTE = "Byte";
    public static final String FLOAT = "Float";
    public static final String DOUBLE = "Double";
    public static final String INTEGER = "Integer";
    public static final String DIGIT = "Digit";
    public static final String IS_DIGIT = "IsDigit";
    public static final String MATH = "Math";
    public static final String BAD_SIZE = "BadSize";
    public static final String NULL_PE = "NullPe";
    public static final String OBJECT = "Object";
    public static final String ITERATOR = "Iterator";
    public static final String CAST_TYPE = "CastType";
    public static final String STORE = "Store";
    public static final String ENUM_TYPE = "EnumType";
    public static final String PRIM_BYTE = "PrimByte";
    public static final String ERROR = "Error";
    public static final String VOID = "Void";
    public static final String GET_CAUSE = "GetCause";
    public static final String BAD_INDEX = "BadIndex";
    public static final String ENUMS = "Enums";
    public static final String ITERABLE = "Iterable";
    public static final String NB_FORMAT = "NbFormat";
    public static final String SOF = "Sof";
    public static final String PARSE_FLOAT = "ParseFloat";
    public static final String TO_STRING_METHOD = "ToStringMethod";
    public static final String PARSE_LONG_OR_NULL = "ParseLongOrNull";
    public static final String PARSE_SHORT_OR_NULL = "ParseShortOrNull";
    public static final String PARSE_FLOAT_OR_NULL = "ParseFloatOrNull";
    public static final String PARSE_DOUBLE_OR_NULL = "ParseDoubleOrNull";
    public static final String BYTE_VALUE = "ByteValue";
    public static final String CHAR_VALUE = "CharValue";
    public static final String PRIM_BOOLEAN = "PrimBoolean";
    public static final String PARSE_INT_OR_NULL = "ParseIntOrNull";
    public static final String PRIM_SHORT = "PrimShort";
    public static final String PARSE_BOOLEAN = "ParseBoolean";
    public static final String PARSE_SHORT = "ParseShort";
    public static final String PRIM_FLOAT = "PrimFloat";
    public static final String COMPARE_TO = "CompareTo";
    public static final String CHARACTER = "Character";
    public static final String PARSE_LONG = "ParseLong";
    public static final String VALUE_OF_METHOD = "ValueOfMethod";
    public static final String PRIM_INTEGER = "PrimInteger";
    public static final String PARSE_BYTE_OR_NULL = "ParseByteOrNull";
    public static final String PRIM_DOUBLE = "PrimDouble";
    public static final String BOOLEAN_VALUE = "BooleanValue";
    public static final String SHORT_VALUE = "ShortValue";
    public static final String PARSE_DOUBLE = "ParseDouble";
    public static final String ILLEGAL_ARG = "IllegalArg";
    public static final String PARSE_BYTE = "ParseByte";
    public static final String IS_UPPER_CASE = "IsUpperCase";
    public static final String IS_WORD_CHAR = "IsWordChar";
    public static final String IS_WHITESPACE = "IsWhitespace";
    public static final String IS_LETTER_OR_DIGIT = "IsLetterOrDigit";
    public static final String FLOAT_VALUE = "FloatValue";
    public static final String DOUBLE_VALUE = "DoubleValue";
    public static final String LONG_VALUE = "LongValue";
    public static final String IS_LOWER_CASE = "IsLowerCase";
    public static final String INDEX_OF = "IndexOf";
    public static final String STRING = "String";
    public static final String IS_EMPTY = "IsEmpty";
    public static final String TRIM = "Trim";
    public static final String GET_BYTES = "GetBytes";
    public static final String FOR_DIGIT = "ForDigit";
    public static final String IS_SPACE = "IsSpace";
    public static final String GET_TYPE = "GetType";
    public static final String CONTAINS = "Contains";
    public static final String REPLACE = "Replace";
    public static final String FORMAT = "Format";
    public static final String ENDS_WITH = "EndsWith";
    public static final String CAPACITY = "Capacity";
    public static final String SPLIT = "Split";
    public static final String APPEND = "Append";
    public static final String IS_LETTER = "IsLetter";
    public static final String IS_NAN = "IsNan";
    public static final String LENGTH = "Length";
    public static final String CHAR_AT = "CharAt";
    public static final String CLONE = "Clone";
    public static final String NAME = "Name";
    public static final String CALL = "Call";
    public static final String SAME = "Same";
    public static final String MOD = "Mod";
    public static final String REVERSE = "Reverse";
    public static final String INSERT = "Insert";
    public static final String ABS = "Abs";
    public static final String HAS_NEXT = "HasNext";
    public static final String PAIR_TYPE = "PairType";
    public static final String QUOT = "Quot";
    public static final String NEXT = "Next";
    public static final String ORDINAL = "Ordinal";
    public static final String GET_FIRST = "GetFirst";
    public static final String FCT = "Fct";
    public static final String DELETE = "Delete";
    public static final String CLEAR = "Clear";
    public static final String NEXT_PAIR = "NextPair";
    public static final String SUBSTRING = "Substring";
    public static final String SET_CHAR_AT = "SetCharAt";
    public static final String EQUALS_IGNORE_CASE = "EqualsIgnoreCase";
    public static final String ITERATOR_TABLE_TYPE = "IteratorTableType";
    public static final String DELETE_CHAR_AT = "DeleteCharAt";
    public static final String STARTS_WITH = "StartsWith";
    public static final String LAST_INDEX_OF = "LastIndexOf";
    public static final String REGION_MATCHES = "RegionMatches";
    public static final String ITERATOR_TABLE = "IteratorTable";
    public static final String ITERABLE_TABLE = "IterableTable";
    public static final String TO_LOWER_CASE = "ToLowerCase";
    public static final String STRING_BUILDER = "StringBuilder";
    public static final String TO_UPPER_CASE = "ToUpperCase";
    public static final String ENSURE_CAPACITY = "EnsureCapacity";
    public static final String SET_LENGTH = "SetLength";
    public static final String TRIM_TO_SIZE = "TrimToSize";
    public static final String HAS_NEXT_PAIR = "HasNextPair";
    public static final String REPLACEMENT = "Replacement";
    public static final String GET_OLD_STRING = "GetOldString";
    public static final String GET_NEW_STRING = "GetNewString";
    public static final String GET_SECOND = "GetSecond";
    public static final String SUB_SEQUENCE = "SubSequence";
    public static final String COMPARE_TO_IGNORE_CASE = "CompareToIgnoreCase";
    public static final String TO_CHAR_ARRAY = "ToCharArray";
    public static final String REPLACE_MULTIPLE = "ReplaceMultiple";
    public static final String SPLIT_STRINGS = "SplitStrings";
    public static final String SPLIT_CHARS = "SplitChars";
    public static final String IS_INFINITE = "IsInfinite";
    public static final String GET_DIRECTIONALITY = "GetDirectionality";
    public static final String GET_CHAR_TYPE = "GetCharType";
    public static final String ITERABLE_TABLE_VAR_SECOND = "IterableTableVarSecond";
    public static final String GET_STRING = "GetString";
    public static final String GET_ANNOTATIONS_PARAMETERS = "GetAnnotationsParameters";
    public static final String READ_RESOURCES_NAMES = "ReadResourcesNames";
    public static final String INVOKE_TARGET = "InvokeTarget";
    public static final String GET_ANNOTATIONS = "GetAnnotations";
    public static final String GET_VARIABLE_OWNER = "GetVariableOwner";
    public static final String READ_RESOURCES = "ReadResources";
    public static final String RESOURCES = "Resources";
    public static final String CLASS_NOT_FOUND_ERROR = "ClassNotFoundError";
    public static final String ENUM_VALUES = "EnumValues";
    public static final String ENUM_PRED_VALUE_OF = "EnumPredValueOf";
    public static final String ITERATOR_TYPE_VAR = "IteratorTypeVar";
    public static final String CLASS_TYPE = "ClassType";
    public static final String ITERABLE_TABLE_VAR_FIRST = "IterableTableVarFirst";
    public static final String PAIR_TYPE_VAR_FIRST = "PairTypeVarFirst";
    public static final String ERROR_INIT_CLASS = "ErrorInitClass";
    public static final String ANNOTATION_TYPE = "AnnotationType";
    public static final String GET_GENERIC_VARIABLE_OWNER = "GetGenericVariableOwner";
    public static final String ENUM_PARAM_VAR = "EnumParamVar";
    public static final String PAIR_TYPE_VAR_SECOND = "PairTypeVarSecond";
    public static final String ANNOTATED = "Annotated";
    public static final String ITERABLE_VAR = "IterableVar";
    public static final String GET_DEFAULT_VALUE = "GetDefaultValue";
    public static final String MAKE_GENERIC = "MakeGeneric";
    public static final String GET_ALL_CLASSES = "GetAllClasses";
    public static final String GET_OPERATORS = "GetOperators";
    public static final String GET_DECLARED_METHODS = "GetDeclaredMethods";
    public static final String GET_DECLARED_STATIC_METHODS = "GetDeclaredStaticMethods";
    public static final String GET_DECLARED_CONSTRUCTORS = "GetDeclaredConstructors";
    public static final String GET_DECLARED_FIELDS = "GetDeclaredFields";
    public static final String FIELD = "Field";
    public static final String IS_NORMAL = "IsNormal";
    public static final String SAME_REF = "SameRef";
    public static final String IS_PUBLIC = "IsPublic";
    public static final String IS_ARRAY = "IsArray";
    public static final String ARRAY_GET = "ArrayGet";
    public static final String METHOD = "Method";
    public static final String GET_FIELD = "GetField";
    public static final String INVOKE = "Invoke";
    public static final String IS_ENUM = "IsEnum";
    public static final String INIT = "Init";
    public static final String FOR_NAME = "ForName";
    public static final String IS_STATIC = "IsStatic";
    public static final String IS_STATIC_CALL = "IsStaticCall";
    public static final String IS_INSTANCE_METHOD = "IsInstanceMethod";
    public static final String GET_NAME = "GetName";
    public static final String IS_CLASS = "IsClass";
    public static final String SET_FIELD = "SetField";
    public static final String GET_CLASS = "GetClass";
    public static final String IS_FINAL = "IsFinal";
    public static final String ARRAY_SET = "ArraySet";
    public static final String XOR = "Xor";
    public static final String MULT = "Mult";
    public static final String RANDOM = "Random";
    public static final String NEG_BIN = "NegBin";
    public static final String MINUS = "Minus";
    public static final String ENUM_NAME = "EnumName";
    public static final String BIN_MOD = "BinMod";
    public static final String LT = "Lt";
    public static final String GT = "Gt";
    public static final String LE = "Le";
    public static final String GE = "Ge";
    public static final String AND = "And";
    public static final String OR = "Or";
    public static final String PLUS = "Plus";
    public static final String BIN_QUOT = "BinQuot";
    public static final String NEG = "Neg";
    public static final String ROTATE_LEFT = "RotateLeft";
    public static final String ENUM_ORDINAL = "EnumOrdinal";
    public static final String SHIFT_RIGHT = "ShiftRight";
    public static final String CURRENT_FULL_STACK = "CurrentFullStack";
    public static final String GET_BOUNDS = "GetBounds";
    public static final String GET_DECLARING_CLASS = "GetDeclaringClass";
    public static final String STACK_TRACE_ELEMENT = "StackTraceElement";
    public static final String ENUM_VALUE_OF = "EnumValueOf";
    public static final String ARRAY_NEW_INSTANCE = "ArrayNewInstance";
    public static final String GET_ENUM_CONSTANTS = "GetEnumConstants";
    public static final String ARRAY_GET_LENGTH = "ArrayGetLength";
    public static final String ROTATE_RIGHT = "RotateRight";
    public static final String GET_GENERIC_BOUNDS = "GetGenericBounds";
    public static final String BIT_SHIFT_LEFT = "BitShiftLeft";
    public static final String SHIFT_LEFT = "ShiftLeft";
    public static final String DEFAULT_INSTANCE = "DefaultInstance";
    public static final String CURRENT_STACK = "CurrentStack";
    public static final String BIT_SHIFT_RIGHT = "BitShiftRight";
    public static final String GET_PARAMETER_NAMES = "GetParameterNames";
    public static final String GET_PRETTY_NAME = "GetPrettyName";
    public static final String GET_UPPER_BOUNDS = "GetUpperBounds";
    public static final String GET_PARAMETER_TYPES = "GetParameterTypes";
    public static final String GET_GENERIC_RETURN_TYPE = "GetGenericReturnType";
    public static final String INVOKE_DIRECT = "InvokeDirect";
    public static final String STRING_UTIL = "StringUtil";
    public static final String GET_LOWER_BOUNDS = "GetLowerBounds";
    public static final String GET_TYPE_PARAMETERS = "GetTypeParameters";
    public static final String CONSTRUCTOR = "Constructor";
    public static final String SET_PARENT = "SetParent";
    public static final String NEW_INSTANCE = "NewInstance";
    public static final String GET_ENCLOSING_TYPE = "GetEnclosingType";
    public static final String GET_INTERFACES = "GetInterfaces";
    public static final String OBJECTS_UTIL = "ObjectsUtil";
    public static final String GET_DECLARED_CLASSES = "GetDeclaredClasses";
    public static final String GET_SUPER_CLASS = "GetSuperClass";
    public static final String GET_PARENT = "GetParent";
    public static final String GET_COMPONENT_TYPE = "GetComponentType";
    public static final String GET_FILE_NAME = "GetFileName";
    public static final String GET_GENERIC_SUPER_CLASS = "GetGenericSuperClass";
    public static final String GET_GENERIC_INTERFACES = "GetGenericInterfaces";
    public static final String IS_ABSTRACT = "IsAbstract";
    public static final String MAKE_ARRAY = "MakeArray";
    public static final String IS_INTERFACE = "IsInterface";
    public static final String MAKE_WILD_CARD = "MakeWildCard";
    public static final String IS_TYPE_VARIABLE = "IsTypeVariable";
    public static final String IS_PRIVATE = "IsPrivate";
    public static final String IS_VARARGS = "IsVarargs";
    public static final String IS_INSTANCE = "IsInstance";
    public static final String GET_RETURN_TYPE = "GetReturnType";
    public static final String GET_ACTUAL_TYPE_ARGUMENTS = "GetActualTypeArguments";
    public static final String IS_PROTECTED = "IsProtected";
    public static final String IS_PRIMITIVE = "IsPrimitive";
    public static final String IS_WILD_CARD = "IsWildCard";
    public static final String IS_ANNOTATION = "IsAnnotation";
    public static final String GET_GENERIC_TYPE = "GetGenericType";
    public static final String IS_ASSIGNABLE_FROM = "IsAssignableFrom";
    public static final String IS_VARIABLE = "IsVariable";
    public static final String IS_PACKAGE = "IsPackage";
    public static final String FALSE_STRING = "FalseString";
    public static final String TRUE_STRING = "TrueString";
    public static final String NULL_STRING = "NullString";
    protected static final String LOC_VAR = ".";

    protected static final String PARS = "()";

    private StringMap<StandardType> standards = new StringMap<StandardType>();

    private StringList predefinedClasses = new StringList();
    private StringList predefinedInterfacesInitOrder = new StringList();

    private AliasCore coreNames = new AliasCore();

    private AliasCharSequence charSeq = new AliasCharSequence();
    private AliasReflection reflect = new AliasReflection();
    private AliasStackTraceElement stackElt = new AliasStackTraceElement();
    private AliasNumber nbAlias = new AliasNumber();
    private AliasMath mathRef = new AliasMath();
    private PrimitiveTypes primTypes = new PrimitiveTypes();
    private AliasPredefinedTypes predefTypes = new AliasPredefinedTypes();
    private String trueString;
    private String falseString;
    private String nullString;
    private String defaultPkg = "";
    /**Called after setters*/
    public void build() {
        coreNames.build(this);
        nbAlias.build(this);
        charSeq.build(this);
        reflect.build(this);
        mathRef.build(this);
        stackElt.build(this);
        primTypes.buildPrimitiveTypes(this);
        buildOther();
    }

    public Struct getStringOfObject(ContextEl _cont, Struct _arg) {
        if (_arg instanceof EnumerableStruct) {
            return new StringStruct(((EnumerableStruct)_arg).getName());
        }
        return new StringStruct(_arg.getClassName(_cont));
    }
    public static StringList parseLineArg(String _line) {
        StringList args_ = new StringList();
        StringBuilder arg_ = new StringBuilder();
        int len_ = _line.length();
        int i_ = 0;
        boolean escaped_ = false;
        while (i_ < len_) {
            char cur_ = _line.charAt(i_);
            if (escaped_) {
                escaped_ = false;
                if (cur_ == 'n') {
                    arg_.append('\n');
                    i_++;
                    continue;
                }
                if (cur_ == 'e') {
                    arg_.append(' ');
                    i_++;
                    continue;
                }
                if (cur_ == 't') {
                    arg_.append('\t');
                    i_++;
                    continue;
                }
                if (cur_ == 'c') {
                    if (i_ + 2 < len_) {
                        String sub_ = _line.substring(i_ + 1,i_ + 3);
                        LongInfo char_ = NumParsers.parseLong(sub_, 16);
                        if (char_.isValid()) {
                            long value_ = char_.getValue();
                            if (value_ >= 0 && value_ < ' ') {
                                char ch_ = (char) value_;
                                arg_.append(ch_);
                                i_ += 3;
                                continue;
                            }
                        }
                    }
                }
                if (cur_ == 'u') {
                    if (i_ + 4 < len_) {
                        String sub_ = _line.substring(i_ + 1,i_ + 5);
                        LongInfo char_ = NumParsers.parseLong(sub_, 16);
                        if (char_.isValid()) {
                            long value_ = char_.getValue();
                            if (value_ >= 0) {
                                char ch_ = (char) value_;
                                arg_.append(ch_);
                                i_ += 5;
                                continue;
                            }
                        }
                    }
                }
                arg_.append(cur_);
                i_++;
                continue;
            }
            if (cur_ == '\\') {
                escaped_ = true;
                i_++;
                continue;
            }
            if (cur_ == ' ') {
                args_.add(arg_.toString());
                arg_.delete(0,arg_.length());
                i_++;
                continue;
            }
            arg_.append(cur_);
            i_++;
        }
        args_.add(arg_.toString());
        return args_;
    }
    public static String parseValue(String _line) {
        StringBuilder arg_ = new StringBuilder();
        int len_ = _line.length();
        int i_ = 0;
        boolean escaped_ = false;
        while (i_ < len_) {
            char cur_ = _line.charAt(i_);
            if (escaped_) {
                escaped_ = false;
                if (cur_ == 'n') {
                    arg_.append('\n');
                    i_++;
                    continue;
                }
                if (cur_ == 'e') {
                    arg_.append(' ');
                    i_++;
                    continue;
                }
                if (cur_ == 't') {
                    arg_.append('\t');
                    i_++;
                    continue;
                }
                if (cur_ == 'c') {
                    if (i_ + 2 < len_) {
                        String sub_ = _line.substring(i_ + 1,i_ + 3);
                        LongInfo char_ = NumParsers.parseLong(sub_, 16);
                        if (char_.isValid()) {
                            long value_ = char_.getValue();
                            if (value_ >= 0 && value_ < ' ') {
                                char ch_ = (char) value_;
                                arg_.append(ch_);
                                i_ += 3;
                                continue;
                            }
                        }
                    }
                }
                if (cur_ == 'u') {
                    if (i_ + 4 < len_) {
                        String sub_ = _line.substring(i_ + 1,i_ + 5);
                        LongInfo char_ = NumParsers.parseLong(sub_, 16);
                        if (char_.isValid()) {
                            long value_ = char_.getValue();
                            if (value_ >= 0) {
                                char ch_ = (char) value_;
                                arg_.append(ch_);
                                i_ += 5;
                                continue;
                            }
                        }
                    }
                }
                arg_.append(cur_);
                i_++;
                continue;
            }
            if (cur_ == '\\') {
                escaped_ = true;
                i_++;
                continue;
            }
            arg_.append(cur_);
            i_++;
        }
        return arg_.toString();
    }
    public StringMap<String> allPrimitives() {
        StringMap<String> list_ = new StringMap<String>();
        list_.addEntry(PRIM_BOOLEAN,primTypes.getAliasPrimBoolean());
        list_.addEntry(PRIM_BYTE,primTypes.getAliasPrimByte());
        list_.addEntry(PRIM_SHORT,primTypes.getAliasPrimShort());
        list_.addEntry(PRIM_CHAR,primTypes.getAliasPrimChar());
        list_.addEntry(PRIM_INTEGER,primTypes.getAliasPrimInteger());
        list_.addEntry(PRIM_LONG,primTypes.getAliasPrimLong());
        list_.addEntry(PRIM_FLOAT,primTypes.getAliasPrimFloat());
        list_.addEntry(PRIM_DOUBLE,primTypes.getAliasPrimDouble());
        list_.addEntry(VOID,coreNames.getAliasVoid());
        return list_;
    }
    public void validatePrimitiveContents(ContextEl _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String value_ = e.getValue();
            if (value_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyPrimitive(),key_));
                err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
                _cont.getClasses().addStdError(err_);
                continue;
            }
            if (_cont.getKeyWords().isKeyWord(value_)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getPrimitiveKeyWord(),key_,value_));
                err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
                _cont.getClasses().addStdError(err_);
            }
            for (char c: value_.toCharArray()) {
                if (!StringList.isDollarWordChar(c)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharPrimitive(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
                    _cont.getClasses().addStdError(err_);
                    break;
                }
            }
            if (ContextEl.isDigit(value_.charAt(0))) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstPrimitive(),key_,value_));
                err_.setErrCat(ErrorCat.WRITE_PRIMITIVE_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
    }
    public void validatePrimitiveDuplicates(ContextEl _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        StringList keyWords_ = new StringList(_list.values());
        if (keyWords_.hasDuplicates()) {
            for (EntryCust<String,String> e: _list.entryList()) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicatePrimtive(),v_,e.getKey()));
                err_.setErrCat(ErrorCat.DUPLICATE_PRIMITIVE_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
    }
    public StringMap<String> allRefTypes() {
        StringMap<String> list_ = new StringMap<String>();
        list_.addEntry(ANNOTATED,getAliasAnnotated());
        list_.addEntry(ANNOTATION_TYPE,getAliasAnnotationType());
        list_.addEntry(CLASS_TYPE,getAliasClassType());
        list_.addEntry(CONSTRUCTOR,getAliasConstructor());
        list_.addEntry(FCT,getAliasFct());
        list_.addEntry(FIELD,getAliasField());
        list_.addEntry(METHOD,getAliasMethod());
        list_.addEntry(OBJECTS_UTIL,getAliasObjectsUtil());
        list_.addEntry(STRING_UTIL,getAliasStringUtil());
        list_.addEntry(RESOURCES,getAliasResources());
        list_.addEntry(CLASS_NOT_FOUND_ERROR,getAliasClassNotFoundError());
        list_.addEntry(ERROR_INIT_CLASS,getAliasErrorInitClass());
        list_.addEntry(INVOKE_TARGET,getAliasInvokeTarget());
        list_.addEntry(ENUM_TYPE,getAliasEnumType());
        list_.addEntry(ITERABLE,getAliasIterable());
        list_.addEntry(ITERATOR_TYPE,getAliasIteratorType());
        list_.addEntry(ENUM_PARAM,getAliasEnumParam());
        list_.addEntry(ENUMS,getAliasEnums());
        list_.addEntry(ITERATOR_TABLE_TYPE,getAliasIteratorTableType());
        list_.addEntry(ITERABLE_TABLE,getAliasIterableTable());
        list_.addEntry(PAIR_TYPE,getAliasPairType());
        list_.addEntry(MATH,getAliasMath());
        list_.addEntry(STACK_TRACE_ELEMENT,getAliasStackTraceElement());
        list_.addEntry(BAD_ENCODE,getAliasBadEncode());
        list_.addEntry(BAD_INDEX,getAliasBadIndex());
        list_.addEntry(ILLEGAL_ARG,getAliasIllegalArg());
        list_.addEntry(DIVISION_ZERO,getAliasDivisionZero());
        list_.addEntry(STORE,getAliasStore());
        list_.addEntry(CAST_TYPE,getAliasCastType());
        list_.addEntry(BAD_SIZE,getAliasBadSize());
        list_.addEntry(SOF,getAliasSof());
        list_.addEntry(REPLACEMENT,getAliasReplacement());
        list_.addEntry(NULL_PE,getAliasNullPe());
        list_.addEntry(NB_FORMAT,getAliasNbFormat());
        list_.addEntry(BOOLEAN,getAliasBoolean());
        list_.addEntry(BYTE,getAliasByte());
        list_.addEntry(CHAR_SEQUENCE,getAliasCharSequence());
        list_.addEntry(CHARACTER,getAliasCharacter());
        list_.addEntry(DOUBLE,getAliasDouble());
        list_.addEntry(ERROR,getAliasError());
        list_.addEntry(FLOAT,getAliasFloat());
        list_.addEntry(INTEGER,getAliasInteger());
        list_.addEntry(LONG,getAliasLong());
        list_.addEntry(NUMBER,getAliasNumber());
        list_.addEntry(OBJECT,getAliasObject());
        list_.addEntry(SHORT,getAliasShort());
        list_.addEntry(STRING,getAliasString());
        list_.addEntry(STRING_BUILDER,getAliasStringBuilder());
        return list_;
    }
    public void validateRefTypeContents(ContextEl _cont, StringMap<String> _list, StringMap<String> _prims) {
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        StringList allPkgs_ = new StringList();
        for (EntryCust<String,String> e: _list.entryList()) {
            String key_ = e.getKey();
            String value_ = e.getValue();
            if (value_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyRefType(),key_));
                err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                _cont.getClasses().addStdError(err_);
                continue;
            }
            for (String p : StringList.splitChars(value_, '.')) {
                if (p.isEmpty()) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyRefTypeIn(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                    continue;
                }
                if (StringList.contains(_prims.values(), p)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getRefTypePrimitive(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                if (_cont.getKeyWords().isKeyWord(p)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getRefTypeKeyWord(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                for (char c: p.toCharArray()) {
                    if (!StringList.isDollarWordChar(c)) {
                        StdWordError err_ = new StdWordError();
                        err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharRefType(),key_,value_));
                        err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                        _cont.getClasses().addStdError(err_);
                        break;
                    }
                }
                if (ContextEl.isDigit(p.charAt(0))) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstRefType(),key_,value_));
                    err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
            String pkg_ = StandardType.getPackagePart(value_);
            if (pkg_.isEmpty()) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyPkgRefType(),key_,value_));
                err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                _cont.getClasses().addStdError(err_);
            }
            allPkgs_.add(pkg_);
        }
        boolean exNonEmpty_ = false;
        for (String p: allPkgs_) {
            if (StringList.quickEq(defaultPkg, p)) {
                exNonEmpty_ = true;
            }
        }
        if (!exNonEmpty_) {
            //ERROR
            StdWordError err_ = new StdWordError();
            err_.setMessage(StringList.simpleStringsFormat(a_.getDefaultPkgNoMatch(),defaultPkg));
            err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
            _cont.getClasses().addStdError(err_);
        }
        for (String k: _list.values()) {
            if (defaultPkg.contains(k)) {
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDefaultPkgRefType(),k,defaultPkg));
                err_.setErrCat(ErrorCat.WRITE_TYPE_WORD);
                _cont.getClasses().addStdError(err_);
            }
        }
    }
    public boolean validateRefTypeDuplicates(ContextEl _cont, StringMap<String> _list) {
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        StringList keyWords_ = new StringList(_list.values());
        boolean dup_ = false;
        if (keyWords_.hasDuplicates()) {
            for (EntryCust<String,String> e: _list.entryList()) {
                String v_ = e.getValue();
                StdWordError err_ = new StdWordError();
                err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateRefType(),v_));
                err_.setErrCat(ErrorCat.DUPLICATE_TYPE_WORD);
                _cont.getClasses().addStdError(err_);
                dup_ = true;
            }
        }
        return dup_;
    }
    public CustList<CustList<KeyValueMemberName>> allMergeTableTypeMethodNames() {
        CustList<CustList<KeyValueMemberName>> list_ = new CustList<CustList<KeyValueMemberName>>();
        list_.add(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERATOR,getAliasIterator()),
                new KeyValueMemberName(HAS_NEXT,getAliasHasNext()),
                new KeyValueMemberName(NEXT,getAliasNext()),
                new KeyValueMemberName(ITERATOR_TABLE,getAliasIteratorTable()),
                new KeyValueMemberName(HAS_NEXT_PAIR,getAliasHasNextPair()),
                new KeyValueMemberName(NEXT_PAIR,getAliasNextPair()),
                new KeyValueMemberName(GET_FIRST,getAliasGetFirst()),
                new KeyValueMemberName(GET_SECOND,getAliasGetSecond()),
                new KeyValueMemberName(ENUM_ORDINAL,getAliasEnumOrdinal()),
                new KeyValueMemberName(ENUM_NAME,getAliasEnumName())
        ));
        return list_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(getAliasError(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(CURRENT_STACK,getAliasCurrentStack()),
                new KeyValueMemberName(TO_STRING_METHOD,getAliasToStringMethod()),
                new KeyValueMemberName(GET_MESSAGE,getAliasGetMessage()),
                new KeyValueMemberName(GET_CAUSE,getAliasGetCause())));
        map_.addEntry(getAliasAnnotated(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_FILE_NAME,getAliasGetFileName()),
                new KeyValueMemberName(GET_ANNOTATIONS,getAliasGetAnnotations()),
                new KeyValueMemberName(GET_ANNOTATIONS_PARAMETERS,getAliasGetAnnotationsParameters())));
        map_.addEntry(getAliasAnnotationType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_STRING,getAliasGetString())));
        map_.addEntry(getAliasClassType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_ANNOTATIONS,getAliasGetAnnotations()),
                new KeyValueMemberName(GET_ANNOTATIONS_PARAMETERS,getAliasGetAnnotationsParameters()),
                new KeyValueMemberName(DEFAULT_INSTANCE,getAliasDefaultInstance()),
                new KeyValueMemberName(ENUM_VALUE_OF,getAliasEnumValueOf()),
                new KeyValueMemberName(FOR_NAME,getAliasForName()),
                new KeyValueMemberName(ARRAY_GET,getAliasArrayGet()),
                new KeyValueMemberName(GET_ACTUAL_TYPE_ARGUMENTS,getAliasGetActualTypeArguments()),
                new KeyValueMemberName(GET_ALL_CLASSES,getAliasGetAllClasses()),
                new KeyValueMemberName(GET_BOUNDS,getAliasGetBounds()),
                new KeyValueMemberName(GET_CLASS,getAliasGetClass()),
                new KeyValueMemberName(GET_COMPONENT_TYPE,getAliasGetComponentType()),
                new KeyValueMemberName(GET_DECLARED_CLASSES,getAliasGetDeclaredClasses()),
                new KeyValueMemberName(GET_DECLARED_CONSTRUCTORS,getAliasGetDeclaredConstructors()),
                new KeyValueMemberName(GET_DECLARED_FIELDS,getAliasGetDeclaredFields()),
                new KeyValueMemberName(GET_DECLARED_METHODS,getAliasGetDeclaredMethods()),
                new KeyValueMemberName(GET_DECLARED_STATIC_METHODS,getAliasGetDeclaredStaticMethods()),
                new KeyValueMemberName(GET_ENCLOSING_TYPE,getAliasGetEnclosingType()),
                new KeyValueMemberName(GET_ENUM_CONSTANTS,getAliasGetEnumConstants()),
                new KeyValueMemberName(GET_GENERIC_BOUNDS,getAliasGetGenericBounds()),
                new KeyValueMemberName(GET_GENERIC_INTERFACES,getAliasGetGenericInterfaces()),
                new KeyValueMemberName(GET_GENERIC_SUPER_CLASS,getAliasGetGenericSuperClass()),
                new KeyValueMemberName(GET_GENERIC_VARIABLE_OWNER,getAliasGetGenericVariableOwner()),
                new KeyValueMemberName(GET_INTERFACES,getAliasGetInterfaces()),
                new KeyValueMemberName(ARRAY_GET_LENGTH,getAliasArrayGetLength()),
                new KeyValueMemberName(GET_LOWER_BOUNDS,getAliasGetLowerBounds()),
                new KeyValueMemberName(GET_FILE_NAME,getAliasGetFileName()),
                new KeyValueMemberName(GET_NAME,getAliasGetName()),
                new KeyValueMemberName(GET_OPERATORS,getAliasGetOperators()),
                new KeyValueMemberName(GET_PRETTY_NAME,getAliasGetPrettyName()),
                new KeyValueMemberName(GET_SUPER_CLASS,getAliasGetSuperClass()),
                new KeyValueMemberName(GET_TYPE_PARAMETERS,getAliasGetTypeParameters()),
                new KeyValueMemberName(GET_UPPER_BOUNDS,getAliasGetUpperBounds()),
                new KeyValueMemberName(GET_VARIABLE_OWNER,getAliasGetVariableOwner()),
                new KeyValueMemberName(INIT,getAliasInit()),
                new KeyValueMemberName(IS_ABSTRACT,getAliasIsAbstract()),
                new KeyValueMemberName(IS_ANNOTATION,getAliasIsAnnotation()),
                new KeyValueMemberName(IS_ARRAY,getAliasIsArray()),
                new KeyValueMemberName(IS_ASSIGNABLE_FROM,getAliasIsAssignableFrom()),
                new KeyValueMemberName(IS_CLASS,getAliasIsClass()),
                new KeyValueMemberName(IS_ENUM,getAliasIsEnum()),
                new KeyValueMemberName(IS_FINAL,getAliasIsFinal()),
                new KeyValueMemberName(IS_TYPE_VARIABLE,getAliasIsTypeVariable()),
                new KeyValueMemberName(IS_VARIABLE,getAliasIsVariable()),
                new KeyValueMemberName(IS_INSTANCE,getAliasIsInstance()),
                new KeyValueMemberName(IS_INTERFACE,getAliasIsInterface()),
                new KeyValueMemberName(IS_PACKAGE,getAliasIsPackage()),
                new KeyValueMemberName(IS_PRIMITIVE,getAliasIsPrimitive()),
                new KeyValueMemberName(IS_PRIVATE,getAliasIsPrivate()),
                new KeyValueMemberName(IS_PROTECTED,getAliasIsProtected()),
                new KeyValueMemberName(IS_PUBLIC,getAliasIsPublic()),
                new KeyValueMemberName(IS_STATIC,getAliasIsStatic()),
                new KeyValueMemberName(IS_WILD_CARD,getAliasIsWildCard()),
                new KeyValueMemberName(MAKE_ARRAY,getAliasMakeArray()),
                new KeyValueMemberName(MAKE_GENERIC,getAliasMakeGeneric()),
                new KeyValueMemberName(MAKE_WILD_CARD,getAliasMakeWildCard()),
                new KeyValueMemberName(ARRAY_NEW_INSTANCE,getAliasArrayNewInstance()),
                new KeyValueMemberName(ARRAY_SET,getAliasArraySet())));
        map_.addEntry(getAliasConstructor(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_ANNOTATIONS,getAliasGetAnnotations()),
                new KeyValueMemberName(GET_ANNOTATIONS_PARAMETERS,getAliasGetAnnotationsParameters()),
                new KeyValueMemberName(GET_DECLARING_CLASS,getAliasGetDeclaringClass()),
                new KeyValueMemberName(GET_GENERIC_RETURN_TYPE,getAliasGetGenericReturnType()),
                new KeyValueMemberName(GET_FILE_NAME,getAliasGetFileName()),
                new KeyValueMemberName(GET_NAME,getAliasGetName()),
                new KeyValueMemberName(GET_PARAMETER_NAMES,getAliasGetParameterNames()),
                new KeyValueMemberName(GET_PARAMETER_TYPES,getAliasGetParameterTypes()),
                new KeyValueMemberName(GET_RETURN_TYPE,getAliasGetReturnType()),
                new KeyValueMemberName(IS_PACKAGE,getAliasIsPackage()),
                new KeyValueMemberName(IS_PRIVATE,getAliasIsPrivate()),
                new KeyValueMemberName(IS_PROTECTED,getAliasIsProtected()),
                new KeyValueMemberName(IS_PUBLIC,getAliasIsPublic()),
                new KeyValueMemberName(IS_VARARGS,getAliasIsVarargs()),
                new KeyValueMemberName(NEW_INSTANCE,getAliasNewInstance())));
        map_.addEntry(getAliasFct(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(CALL,getAliasCall())));
        map_.addEntry(getAliasField(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_ANNOTATIONS,getAliasGetAnnotations()),
                new KeyValueMemberName(GET_ANNOTATIONS_PARAMETERS,getAliasGetAnnotationsParameters()),
                new KeyValueMemberName(ARRAY_GET,getAliasArrayGet()),
                new KeyValueMemberName(GET_DECLARING_CLASS,getAliasGetDeclaringClass()),
                new KeyValueMemberName(GET_GENERIC_TYPE,getAliasGetGenericType()),
                new KeyValueMemberName(GET_FILE_NAME,getAliasGetFileName()),
                new KeyValueMemberName(GET_NAME,getAliasGetName()),
                new KeyValueMemberName(GET_TYPE,getAliasGetType()),
                new KeyValueMemberName(IS_FINAL,getAliasIsFinal()),
                new KeyValueMemberName(IS_PACKAGE,getAliasIsPackage()),
                new KeyValueMemberName(IS_PRIVATE,getAliasIsPrivate()),
                new KeyValueMemberName(IS_PROTECTED,getAliasIsProtected()),
                new KeyValueMemberName(IS_PUBLIC,getAliasIsPublic()),
                new KeyValueMemberName(IS_STATIC,getAliasIsStatic()),
                new KeyValueMemberName(SET_FIELD,getAliasSetField())));
        map_.addEntry(getAliasMethod(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_ANNOTATIONS,getAliasGetAnnotations()),
                new KeyValueMemberName(GET_ANNOTATIONS_PARAMETERS,getAliasGetAnnotationsParameters()),
                new KeyValueMemberName(GET_DECLARING_CLASS,getAliasGetDeclaringClass()),
                new KeyValueMemberName(GET_DEFAULT_VALUE,getAliasGetDefaultValue()),
                new KeyValueMemberName(GET_GENERIC_RETURN_TYPE,getAliasGetGenericReturnType()),
                new KeyValueMemberName(GET_FILE_NAME,getAliasGetFileName()),
                new KeyValueMemberName(GET_NAME,getAliasGetName()),
                new KeyValueMemberName(GET_PARAMETER_NAMES,getAliasGetParameterNames()),
                new KeyValueMemberName(GET_PARAMETER_TYPES,getAliasGetParameterTypes()),
                new KeyValueMemberName(GET_RETURN_TYPE,getAliasGetReturnType()),
                new KeyValueMemberName(INVOKE,getAliasInvoke()),
                new KeyValueMemberName(INVOKE_DIRECT,getAliasInvokeDirect()),
                new KeyValueMemberName(IS_ABSTRACT,getAliasIsAbstract()),
                new KeyValueMemberName(IS_FINAL,getAliasIsFinal()),
                new KeyValueMemberName(IS_NORMAL,getAliasIsNormal()),
                new KeyValueMemberName(IS_PACKAGE,getAliasIsPackage()),
                new KeyValueMemberName(IS_PRIVATE,getAliasIsPrivate()),
                new KeyValueMemberName(IS_PROTECTED,getAliasIsProtected()),
                new KeyValueMemberName(IS_PUBLIC,getAliasIsPublic()),
                new KeyValueMemberName(IS_STATIC,getAliasIsStatic()),
                new KeyValueMemberName(IS_STATIC_CALL,getAliasIsStaticCall()),
                new KeyValueMemberName(IS_INSTANCE_METHOD,getAliasIsInstanceMethod()),
                new KeyValueMemberName(IS_VARARGS,getAliasIsVarargs())));
        map_.addEntry(getAliasObjectsUtil(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(SAME_REF,getAliasSameRef()),
                new KeyValueMemberName(GET_PARENT,getAliasGetParent()),
                new KeyValueMemberName(SET_PARENT,getAliasSetParent())));
        map_.addEntry(getAliasStringUtil(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(VALUE_OF_METHOD,getAliasValueOfMethod())));
        map_.addEntry(getAliasResources(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(READ_RESOURCES_NAMES,getAliasReadResourcesNames()),
                new KeyValueMemberName(READ_RESOURCES,getAliasReadResources())));
        map_.addEntry(getAliasEnumType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ENUM_NAME,getAliasEnumName()),
                new KeyValueMemberName(ENUM_ORDINAL,getAliasEnumOrdinal()),
                new KeyValueMemberName(ENUM_PRED_VALUE_OF,getAliasEnumPredValueOf()),
                new KeyValueMemberName(ENUM_VALUES,getAliasEnumValues())));
        map_.addEntry(getAliasEnums(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(NAME,getAliasName()),
                new KeyValueMemberName(ORDINAL,getAliasOrdinal())));
        map_.addEntry(getAliasIterable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERATOR,getAliasIterator())));
        map_.addEntry(getAliasIteratorType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(HAS_NEXT,getAliasHasNext()),
                new KeyValueMemberName(NEXT,getAliasNext())));
        map_.addEntry(getAliasIterableTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERATOR_TABLE,getAliasIteratorTable())));
        map_.addEntry(getAliasIteratorTableType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(HAS_NEXT_PAIR,getAliasHasNextPair()),
                new KeyValueMemberName(NEXT_PAIR,getAliasNextPair())));
        map_.addEntry(getAliasPairType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_FIRST,getAliasGetFirst()),
                new KeyValueMemberName(GET_SECOND,getAliasGetSecond())));
        map_.addEntry(getAliasStackTraceElement(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(CURRENT_STACK,getAliasCurrentStack()),
                new KeyValueMemberName(CURRENT_FULL_STACK,getAliasCurrentFullStack()),
                new KeyValueMemberName(TO_STRING_METHOD,getAliasToStringMethod())));
        map_.addEntry(getAliasMath(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ABS,getAliasAbs()),
                new KeyValueMemberName(MOD,getAliasMod()),
                new KeyValueMemberName(QUOT,getAliasQuot()),
                new KeyValueMemberName(BIN_MOD,getAliasBinMod()),
                new KeyValueMemberName(BIN_QUOT,getAliasBinQuot()),
                new KeyValueMemberName(PLUS,getAliasPlus()),
                new KeyValueMemberName(MINUS,getAliasMinus()),
                new KeyValueMemberName(MULT,getAliasMult()),
                new KeyValueMemberName(NEG_BIN,getAliasNegBin()),
                new KeyValueMemberName(NEG,getAliasNeg()),
                new KeyValueMemberName(AND,getAliasAnd()),
                new KeyValueMemberName(OR,getAliasOr()),
                new KeyValueMemberName(XOR,getAliasXor()),
                new KeyValueMemberName(LE,getAliasLe()),
                new KeyValueMemberName(GE,getAliasGe()),
                new KeyValueMemberName(LT,getAliasLt()),
                new KeyValueMemberName(GT,getAliasGt()),
                new KeyValueMemberName(SHIFT_LEFT,getAliasShiftLeft()),
                new KeyValueMemberName(SHIFT_RIGHT,getAliasShiftRight()),
                new KeyValueMemberName(BIT_SHIFT_LEFT,getAliasBitShiftLeft()),
                new KeyValueMemberName(BIT_SHIFT_RIGHT,getAliasBitShiftRight()),
                new KeyValueMemberName(ROTATE_LEFT,getAliasRotateLeft()),
                new KeyValueMemberName(ROTATE_RIGHT,getAliasRotateRight()),
                new KeyValueMemberName(RANDOM,getAliasRandom())));
        map_.addEntry(getAliasReplacement(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_NEW_STRING,getAliasGetNewString()),
                new KeyValueMemberName(GET_OLD_STRING,getAliasGetOldString())));
        map_.addEntry(getAliasBoolean(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BOOLEAN_VALUE,getAliasBooleanValue()),
                new KeyValueMemberName(COMPARE,getAliasCompare()),
                new KeyValueMemberName(COMPARE_TO,getAliasCompareTo()),
                new KeyValueMemberName(EQUALS,getAliasEquals()),
                new KeyValueMemberName(PARSE_BOOLEAN,getAliasParseBoolean()),
                new KeyValueMemberName(TO_STRING_METHOD,getAliasToStringMethod()),
                new KeyValueMemberName(VALUE_OF_METHOD,getAliasValueOfMethod())));
        map_.addEntry(getAliasByte(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BYTE_VALUE,getAliasByteValue()),
                new KeyValueMemberName(COMPARE,getAliasCompare()),
                new KeyValueMemberName(COMPARE_TO,getAliasCompareTo()),
                new KeyValueMemberName(DOUBLE_VALUE,getAliasDoubleValue()),
                new KeyValueMemberName(EQUALS,getAliasEquals()),
                new KeyValueMemberName(FLOAT_VALUE,getAliasFloatValue()),
                new KeyValueMemberName(INT_VALUE,getAliasIntValue()),
                new KeyValueMemberName(LONG_VALUE,getAliasLongValue()),
                new KeyValueMemberName(SHORT_VALUE,getAliasShortValue()),
                new KeyValueMemberName(TO_STRING_METHOD,getAliasToStringMethod()),
                new KeyValueMemberName(PARSE_BYTE,getAliasParseByte()),
                new KeyValueMemberName(PARSE_BYTE_OR_NULL,getAliasParseByteOrNull())));
        map_.addEntry(getAliasCharSequence(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(CHAR_AT,getAliasCharAt()),
                new KeyValueMemberName(EQUALS,getAliasEquals()),
                new KeyValueMemberName(COMPARE_TO,getAliasCompareTo()),
                new KeyValueMemberName(CONTAINS,getAliasContains()),
                new KeyValueMemberName(ENDS_WITH,getAliasEndsWith()),
                new KeyValueMemberName(FORMAT,getAliasFormat()),
                new KeyValueMemberName(GET_BYTES,getAliasGetBytes()),
                new KeyValueMemberName(INDEX_OF,getAliasIndexOf()),
                new KeyValueMemberName(IS_EMPTY,getAliasIsEmpty()),
                new KeyValueMemberName(LAST_INDEX_OF,getAliasLastIndexOf()),
                new KeyValueMemberName(LENGTH,getAliasLength()),
                new KeyValueMemberName(REGION_MATCHES,getAliasRegionMatches()),
                new KeyValueMemberName(REPLACE,getAliasReplace()),
                new KeyValueMemberName(SPLIT,getAliasSplit()),
                new KeyValueMemberName(SPLIT_CHARS,getAliasSplitChars()),
                new KeyValueMemberName(SPLIT_STRINGS,getAliasSplitStrings()),
                new KeyValueMemberName(STARTS_WITH,getAliasStartsWith()),
                new KeyValueMemberName(SUB_SEQUENCE,getAliasSubSequence()),
                new KeyValueMemberName(SUBSTRING,getAliasSubstring()),
                new KeyValueMemberName(TO_CHAR_ARRAY,getAliasToCharArray()),
                new KeyValueMemberName(TO_STRING_METHOD,getAliasToStringMethod()),
                new KeyValueMemberName(TRIM,getAliasTrim())));
        map_.addEntry(getAliasCharacter(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BYTE_VALUE,getAliasByteValue()),
                new KeyValueMemberName(DOUBLE_VALUE,getAliasDoubleValue()),
                new KeyValueMemberName(EQUALS,getAliasEquals()),
                new KeyValueMemberName(FLOAT_VALUE,getAliasFloatValue()),
                new KeyValueMemberName(INT_VALUE,getAliasIntValue()),
                new KeyValueMemberName(LONG_VALUE,getAliasLongValue()),
                new KeyValueMemberName(PARSE_INT,getAliasParseInt()),
                new KeyValueMemberName(PARSE_INT_OR_NULL,getAliasParseIntOrNull()),
                new KeyValueMemberName(SHORT_VALUE,getAliasShortValue()),
                new KeyValueMemberName(CHAR_AT,getAliasCharAt()),
                new KeyValueMemberName(CHAR_VALUE,getAliasCharValue()),
                new KeyValueMemberName(COMPARE,getAliasCompare()),
                new KeyValueMemberName(COMPARE_TO,getAliasCompareTo()),
                new KeyValueMemberName(DIGIT,getAliasDigit()),
                new KeyValueMemberName(FOR_DIGIT,getAliasForDigit()),
                new KeyValueMemberName(GET_CHAR_TYPE,getAliasGetCharType()),
                new KeyValueMemberName(IS_DIGIT,getAliasIsDigit()),
                new KeyValueMemberName(GET_DIRECTIONALITY,getAliasGetDirectionality()),
                new KeyValueMemberName(IS_LETTER,getAliasIsLetter()),
                new KeyValueMemberName(IS_LETTER_OR_DIGIT,getAliasIsLetterOrDigit()),
                new KeyValueMemberName(IS_LOWER_CASE,getAliasIsLowerCase()),
                new KeyValueMemberName(IS_SPACE,getAliasIsSpace()),
                new KeyValueMemberName(IS_UPPER_CASE,getAliasIsUpperCase()),
                new KeyValueMemberName(IS_WHITESPACE,getAliasIsWhitespace()),
                new KeyValueMemberName(IS_WORD_CHAR,getAliasIsWordChar()),
                new KeyValueMemberName(LENGTH,getAliasLength()),
                new KeyValueMemberName(SUB_SEQUENCE,getAliasSubSequence()),
                new KeyValueMemberName(TO_LOWER_CASE,getAliasToLowerCase()),
                new KeyValueMemberName(TO_STRING_METHOD,getAliasToStringMethod()),
                new KeyValueMemberName(TO_UPPER_CASE,getAliasToUpperCase())));
        map_.addEntry(getAliasDouble(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BYTE_VALUE,getAliasByteValue()),
                new KeyValueMemberName(COMPARE,getAliasCompare()),
                new KeyValueMemberName(COMPARE_TO,getAliasCompareTo()),
                new KeyValueMemberName(DOUBLE_VALUE,getAliasDoubleValue()),
                new KeyValueMemberName(EQUALS,getAliasEquals()),
                new KeyValueMemberName(FLOAT_VALUE,getAliasFloatValue()),
                new KeyValueMemberName(INT_VALUE,getAliasIntValue()),
                new KeyValueMemberName(LONG_VALUE,getAliasLongValue()),
                new KeyValueMemberName(SHORT_VALUE,getAliasShortValue()),
                new KeyValueMemberName(TO_STRING_METHOD,getAliasToStringMethod()),
                new KeyValueMemberName(IS_INFINITE,getAliasIsInfinite()),
                new KeyValueMemberName(IS_NAN,getAliasIsNan()),
                new KeyValueMemberName(PARSE_DOUBLE,getAliasParseDouble()),
                new KeyValueMemberName(PARSE_DOUBLE_OR_NULL,getAliasParseDoubleOrNull())));
        map_.addEntry(getAliasFloat(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BYTE_VALUE,getAliasByteValue()),
                new KeyValueMemberName(COMPARE,getAliasCompare()),
                new KeyValueMemberName(COMPARE_TO,getAliasCompareTo()),
                new KeyValueMemberName(DOUBLE_VALUE,getAliasDoubleValue()),
                new KeyValueMemberName(EQUALS,getAliasEquals()),
                new KeyValueMemberName(FLOAT_VALUE,getAliasFloatValue()),
                new KeyValueMemberName(INT_VALUE,getAliasIntValue()),
                new KeyValueMemberName(LONG_VALUE,getAliasLongValue()),
                new KeyValueMemberName(SHORT_VALUE,getAliasShortValue()),
                new KeyValueMemberName(TO_STRING_METHOD,getAliasToStringMethod()),
                new KeyValueMemberName(IS_INFINITE,getAliasIsInfinite()),
                new KeyValueMemberName(IS_NAN,getAliasIsNan()),
                new KeyValueMemberName(PARSE_FLOAT,getAliasParseFloat()),
                new KeyValueMemberName(PARSE_FLOAT_OR_NULL,getAliasParseFloatOrNull())));
        map_.addEntry(getAliasInteger(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BYTE_VALUE,getAliasByteValue()),
                new KeyValueMemberName(COMPARE,getAliasCompare()),
                new KeyValueMemberName(COMPARE_TO,getAliasCompareTo()),
                new KeyValueMemberName(DOUBLE_VALUE,getAliasDoubleValue()),
                new KeyValueMemberName(EQUALS,getAliasEquals()),
                new KeyValueMemberName(FLOAT_VALUE,getAliasFloatValue()),
                new KeyValueMemberName(INT_VALUE,getAliasIntValue()),
                new KeyValueMemberName(LONG_VALUE,getAliasLongValue()),
                new KeyValueMemberName(SHORT_VALUE,getAliasShortValue()),
                new KeyValueMemberName(TO_STRING_METHOD,getAliasToStringMethod()),
                new KeyValueMemberName(PARSE_INT,getAliasParseInt()),
                new KeyValueMemberName(PARSE_INT_OR_NULL,getAliasParseIntOrNull())));
        map_.addEntry(getAliasLong(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BYTE_VALUE,getAliasByteValue()),
                new KeyValueMemberName(COMPARE,getAliasCompare()),
                new KeyValueMemberName(COMPARE_TO,getAliasCompareTo()),
                new KeyValueMemberName(DOUBLE_VALUE,getAliasDoubleValue()),
                new KeyValueMemberName(EQUALS,getAliasEquals()),
                new KeyValueMemberName(FLOAT_VALUE,getAliasFloatValue()),
                new KeyValueMemberName(INT_VALUE,getAliasIntValue()),
                new KeyValueMemberName(LONG_VALUE,getAliasLongValue()),
                new KeyValueMemberName(SHORT_VALUE,getAliasShortValue()),
                new KeyValueMemberName(TO_STRING_METHOD,getAliasToStringMethod()),
                new KeyValueMemberName(PARSE_LONG,getAliasParseLong()),
                new KeyValueMemberName(PARSE_LONG_OR_NULL,getAliasParseLongOrNull())));
        map_.addEntry(getAliasNumber(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BYTE_VALUE,getAliasByteValue()),
                new KeyValueMemberName(COMPARE,getAliasCompare()),
                new KeyValueMemberName(COMPARE_TO,getAliasCompareTo()),
                new KeyValueMemberName(DOUBLE_VALUE,getAliasDoubleValue()),
                new KeyValueMemberName(EQUALS,getAliasEquals()),
                new KeyValueMemberName(FLOAT_VALUE,getAliasFloatValue()),
                new KeyValueMemberName(INT_VALUE,getAliasIntValue()),
                new KeyValueMemberName(LONG_VALUE,getAliasLongValue()),
                new KeyValueMemberName(SHORT_VALUE,getAliasShortValue()),
                new KeyValueMemberName(TO_STRING_METHOD,getAliasToStringMethod())));
        map_.addEntry(getAliasShort(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BYTE_VALUE,getAliasByteValue()),
                new KeyValueMemberName(COMPARE,getAliasCompare()),
                new KeyValueMemberName(COMPARE_TO,getAliasCompareTo()),
                new KeyValueMemberName(DOUBLE_VALUE,getAliasDoubleValue()),
                new KeyValueMemberName(EQUALS,getAliasEquals()),
                new KeyValueMemberName(FLOAT_VALUE,getAliasFloatValue()),
                new KeyValueMemberName(INT_VALUE,getAliasIntValue()),
                new KeyValueMemberName(LONG_VALUE,getAliasLongValue()),
                new KeyValueMemberName(SHORT_VALUE,getAliasShortValue()),
                new KeyValueMemberName(TO_STRING_METHOD,getAliasToStringMethod()),
                new KeyValueMemberName(PARSE_SHORT,getAliasParseShort()),
                new KeyValueMemberName(PARSE_SHORT_OR_NULL,getAliasParseShortOrNull())));
        map_.addEntry(getAliasString(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(EQUALS,getAliasEquals()),
                new KeyValueMemberName(COMPARE_TO,getAliasCompareTo()),
                new KeyValueMemberName(CHAR_AT,getAliasCharAt()),
                new KeyValueMemberName(CONTAINS,getAliasContains()),
                new KeyValueMemberName(ENDS_WITH,getAliasEndsWith()),
                new KeyValueMemberName(FORMAT,getAliasFormat()),
                new KeyValueMemberName(GET_BYTES,getAliasGetBytes()),
                new KeyValueMemberName(INDEX_OF,getAliasIndexOf()),
                new KeyValueMemberName(IS_EMPTY,getAliasIsEmpty()),
                new KeyValueMemberName(LAST_INDEX_OF,getAliasLastIndexOf()),
                new KeyValueMemberName(LENGTH,getAliasLength()),
                new KeyValueMemberName(REGION_MATCHES,getAliasRegionMatches()),
                new KeyValueMemberName(REPLACE,getAliasReplace()),
                new KeyValueMemberName(SPLIT,getAliasSplit()),
                new KeyValueMemberName(SPLIT_CHARS,getAliasSplitChars()),
                new KeyValueMemberName(SPLIT_STRINGS,getAliasSplitStrings()),
                new KeyValueMemberName(STARTS_WITH,getAliasStartsWith()),
                new KeyValueMemberName(SUB_SEQUENCE,getAliasSubSequence()),
                new KeyValueMemberName(SUBSTRING,getAliasSubstring()),
                new KeyValueMemberName(TO_CHAR_ARRAY,getAliasToCharArray()),
                new KeyValueMemberName(TO_STRING_METHOD,getAliasToStringMethod()),
                new KeyValueMemberName(TRIM,getAliasTrim()),
                new KeyValueMemberName(COMPARE,getAliasCompare()),
                new KeyValueMemberName(COMPARE_TO_IGNORE_CASE,getAliasCompareToIgnoreCase()),
                new KeyValueMemberName(EQUALS_IGNORE_CASE,getAliasEqualsIgnoreCase()),
                new KeyValueMemberName(REPLACE_MULTIPLE,getAliasReplaceMultiple()),
                new KeyValueMemberName(TO_LOWER_CASE,getAliasToLowerCase()),
                new KeyValueMemberName(TO_UPPER_CASE,getAliasToUpperCase()),
                new KeyValueMemberName(VALUE_OF_METHOD,getAliasValueOfMethod())));
        map_.addEntry(getAliasStringBuilder(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(EQUALS,getAliasEquals()),
                new KeyValueMemberName(COMPARE_TO,getAliasCompareTo()),
                new KeyValueMemberName(CHAR_AT,getAliasCharAt()),
                new KeyValueMemberName(CONTAINS,getAliasContains()),
                new KeyValueMemberName(ENDS_WITH,getAliasEndsWith()),
                new KeyValueMemberName(FORMAT,getAliasFormat()),
                new KeyValueMemberName(GET_BYTES,getAliasGetBytes()),
                new KeyValueMemberName(INDEX_OF,getAliasIndexOf()),
                new KeyValueMemberName(IS_EMPTY,getAliasIsEmpty()),
                new KeyValueMemberName(LAST_INDEX_OF,getAliasLastIndexOf()),
                new KeyValueMemberName(LENGTH,getAliasLength()),
                new KeyValueMemberName(REGION_MATCHES,getAliasRegionMatches()),
                new KeyValueMemberName(REPLACE,getAliasReplace()),
                new KeyValueMemberName(SPLIT,getAliasSplit()),
                new KeyValueMemberName(SPLIT_CHARS,getAliasSplitChars()),
                new KeyValueMemberName(SPLIT_STRINGS,getAliasSplitStrings()),
                new KeyValueMemberName(STARTS_WITH,getAliasStartsWith()),
                new KeyValueMemberName(SUB_SEQUENCE,getAliasSubSequence()),
                new KeyValueMemberName(SUBSTRING,getAliasSubstring()),
                new KeyValueMemberName(TO_CHAR_ARRAY,getAliasToCharArray()),
                new KeyValueMemberName(TO_STRING_METHOD,getAliasToStringMethod()),
                new KeyValueMemberName(TRIM,getAliasTrim()),
                new KeyValueMemberName(APPEND,getAliasAppend()),
                new KeyValueMemberName(CAPACITY,getAliasCapacity()),
                new KeyValueMemberName(CLEAR,getAliasClear()),
                new KeyValueMemberName(DELETE,getAliasDelete()),
                new KeyValueMemberName(DELETE_CHAR_AT,getAliasDeleteCharAt()),
                new KeyValueMemberName(ENSURE_CAPACITY,getAliasEnsureCapacity()),
                new KeyValueMemberName(INSERT,getAliasInsert()),
                new KeyValueMemberName(REVERSE,getAliasReverse()),
                new KeyValueMemberName(SET_CHAR_AT,getAliasSetCharAt()),
                new KeyValueMemberName(SET_LENGTH,getAliasSetLength()),
                new KeyValueMemberName(SAME,getAliasSame()),
                new KeyValueMemberName(TRIM_TO_SIZE,getAliasTrimToSize())));
        return map_;
    }
    public void validateMethodsContents(ContextEl _cont, StringMap<CustList<KeyValueMemberName>> _methods, StringMap<String> _prims){
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            for (KeyValueMemberName f: e.getValue()) {
                String key_ = f.getKey();
                String value_ = f.getValue();
                if (value_.isEmpty()) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyMethod(),key_,e.getKey()));
                    err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                    _cont.getClasses().addStdError(err_);
                    continue;
                }
                if (_cont.getKeyWords().isKeyWordNotVar(value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getMethodKeyWord(),value_,key_,e.getKey()));
                    err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                if (StringList.contains(_prims.values(), value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getMethodPrimitive(),value_,key_,e.getKey()));
                    err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                for (char c: value_.toCharArray()) {
                    if (!StringList.isDollarWordChar(c)) {
                        StdWordError err_ = new StdWordError();
                        err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharMethod(),value_,e.getKey(),Character.toString(c)));
                        err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                        _cont.getClasses().addStdError(err_);
                        break;
                    }
                }
                if (ContextEl.isDigit(value_.charAt(0))) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstMethod(),value_,e.getKey(),Character.toString(value_.charAt(0))));
                    err_.setErrCat(ErrorCat.WRITE_METHOD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
        }
    }
    public void validateMethodsDuplicates(ContextEl _cont, StringMap<CustList<KeyValueMemberName>> _methods){
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            StringList keyWords_ = new StringList();
            for (KeyValueMemberName f: e.getValue()) {
                keyWords_.add(f.getValue());
            }
            if (keyWords_.hasDuplicates()) {
                for (KeyValueMemberName f: e.getValue()) {
                    String v_ = f.getValue();
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateMethod(),v_));
                    err_.setErrCat(ErrorCat.DUPLICATE_METHOD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
        }
    }

    public void validateMergedDuplicates(ContextEl _cont, CustList<CustList<KeyValueMemberName>> _methods){
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        for (CustList<KeyValueMemberName> e: _methods) {
            StringList keyWords_ = new StringList();
            for (KeyValueMemberName f: e) {
                keyWords_.add(f.getValue());
            }
            if (keyWords_.hasDuplicates()) {
                for (KeyValueMemberName f: e) {
                    String v_ = f.getValue();
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateMergedMethod(),v_,f.getKey()));
                    err_.setErrCat(ErrorCat.DUPLICATE_METHOD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
        }
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(nbAlias.getAliasDouble(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_MIN_VALUE,nbAlias.getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE,nbAlias.getAliasMaxValueField())));
        map_.addEntry(nbAlias.getAliasFloat(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_MIN_VALUE,nbAlias.getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE,nbAlias.getAliasMaxValueField())));
        map_.addEntry(nbAlias.getAliasLong(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_MIN_VALUE,nbAlias.getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE,nbAlias.getAliasMaxValueField())));
        map_.addEntry(nbAlias.getAliasInteger(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_MIN_VALUE,nbAlias.getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE,nbAlias.getAliasMaxValueField())));
        map_.addEntry(nbAlias.getAliasCharacter(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_MIN_VALUE,nbAlias.getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE,nbAlias.getAliasMaxValueField())));
        map_.addEntry(nbAlias.getAliasShort(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_MIN_VALUE,nbAlias.getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE,nbAlias.getAliasMaxValueField())));
        map_.addEntry(nbAlias.getAliasByte(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_MIN_VALUE,nbAlias.getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE,nbAlias.getAliasMaxValueField())));
        return map_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes() {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(getAliasEnumParam(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ENUM_PARAM_VAR,getAliasEnumParamVar())));
        map_.addEntry(getAliasIterable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERABLE_VAR,getAliasIterableVar())));
        map_.addEntry(getAliasIteratorType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERATOR_TYPE_VAR,getAliasIteratorTypeVar())));
        map_.addEntry(getAliasIterableTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERABLE_TABLE_VAR_FIRST,getAliasIterableTableVarFirst()),
                new KeyValueMemberName(ITERABLE_TABLE_VAR_SECOND,getAliasIterableTableVarSecond())));
        map_.addEntry(getAliasIteratorTableType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERATOR_TABLE_TYPE_VAR_FIRST,getAliasIteratorTableTypeVarFirst()),
                new KeyValueMemberName(ITERATOR_TABLE_TYPE_VAR_SECOND,getAliasIteratorTableTypeVarSecond())));
        map_.addEntry(getAliasPairType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(PAIR_TYPE_VAR_FIRST,getAliasPairTypeVarFirst()),
                new KeyValueMemberName(PAIR_TYPE_VAR_SECOND,getAliasPairTypeVarSecond())));
        return map_;
    }
    public void validateFieldsContents(ContextEl _cont, StringMap<CustList<KeyValueMemberName>> _methods, StringMap<String> _prims){
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            for (KeyValueMemberName f: e.getValue()) {
                String key_ = f.getKey();
                String value_ = f.getValue();
                if (value_.isEmpty()) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyField(),key_,e.getKey()));
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _cont.getClasses().addStdError(err_);
                    continue;
                }
                if (_cont.getKeyWords().isKeyWord(value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getFieldKeyWord(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                if (StringList.contains(_prims.values(), value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getFieldPrimitive(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                for (char c: value_.toCharArray()) {
                    if (!StringList.isDollarWordChar(c)) {
                        StdWordError err_ = new StdWordError();
                        err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharField(),value_,e.getKey(),key_));
                        err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                        _cont.getClasses().addStdError(err_);
                        break;
                    }
                }
                if (ContextEl.isDigit(value_.charAt(0))) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstField(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_FIELD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
        }
    }
    public void validateFieldsDuplicates(ContextEl _cont, StringMap<CustList<KeyValueMemberName>> _methods){
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            StringList keyWords_ = new StringList();
            for (KeyValueMemberName f: e.getValue()) {
                keyWords_.add(f.getValue());
            }
            if (keyWords_.hasDuplicates()) {
                for (KeyValueMemberName f: e.getValue()) {
                    String v_ = f.getValue();
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateField(),v_,e.getKey()));
                    err_.setErrCat(ErrorCat.DUPLICATE_FIELD_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
        }
    }
    public void validateVarTypesContents(ContextEl _cont, StringMap<CustList<KeyValueMemberName>> _methods, StringMap<String> _prims){
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            for (KeyValueMemberName f: e.getValue()) {
                String key_ = f.getKey();
                String value_ = f.getValue();
                if (value_.isEmpty()) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getEmptyVarType(),key_,e.getKey()));
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                    continue;
                }
                if (_cont.getKeyWords().isKeyWord(value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getVarTypeKeyWord(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                if (StringList.contains(_prims.values(), value_)) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getVarTypePrimitive(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                }
                for (char c: value_.toCharArray()) {
                    if (!StringList.isDollarWordChar(c)) {
                        StdWordError err_ = new StdWordError();
                        err_.setMessage(StringList.simpleStringsFormat(a_.getNotWordCharVarType(),value_,e.getKey(),key_));
                        err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                        _cont.getClasses().addStdError(err_);
                        break;
                    }
                }
                if (ContextEl.isDigit(value_.charAt(0))) {
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDigitFirstVarType(),value_,e.getKey(),key_));
                    err_.setErrCat(ErrorCat.WRITE_VAR_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
        }
    }

    public void validateVarTypesDuplicates(ContextEl _cont, StringMap<CustList<KeyValueMemberName>> _methods){
        AnalysisMessages a_ = _cont.getAnalysisMessages();
        for (EntryCust<String, CustList<KeyValueMemberName>> e: _methods.entryList()) {
            StringList keyWords_ = new StringList();
            for (KeyValueMemberName f: e.getValue()) {
                keyWords_.add(f.getValue());
            }
            if (keyWords_.hasDuplicates()) {
                for (KeyValueMemberName f: e.getValue()) {
                    String v_ = f.getValue();
                    StdWordError err_ = new StdWordError();
                    err_.setMessage(StringList.simpleStringsFormat(a_.getDuplicateVarType(),v_,e.getKey()));
                    err_.setErrCat(ErrorCat.DUPLICATE_VAR_TYPE_WORD);
                    _cont.getClasses().addStdError(err_);
                }
            }
        }
    }
    public void setupOverrides(ContextEl _cont) {
        StringList pkgs_ = new StringList();
        for (StandardType r: standards.values()) {
            String pkg_ = r.getPackageName();
            StringBuilder id_ = new StringBuilder();
            for (String p: StringList.splitChars(pkg_, '.')) {
                id_.append(p);
                pkgs_.add(id_.toString());
                id_.append('.');
            }
        }
        pkgs_.removeDuplicates();
        _cont.getClasses().getPackagesFound().addAllElts(pkgs_);
        _cont.setAnalyzing();
        TypeUtil.buildInherits(_cont);
    }

    public abstract void buildOther();
    public static ResultErrorStd invokeMethod(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        LgNames lgNames_ = _cont.getStandards();
        return lgNames_.invoke(_cont,_method,_struct,_args);
    }

    protected ResultErrorStd invoke(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        ResultErrorStd result_;
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getClassName();
        LgNames lgNames_ = _cont.getStandards();
        String stringBuilderType_ = lgNames_.getAliasStringBuilder();
        result_ = invokeStdMethod(_cont, _method, _struct, _args);
        if (result_.getResult() != null) {
            return result_;
        }
        processError(_cont,result_);
        if (_cont.callsOrException()) {
            return result_;
        }
        if (StringList.quickEq(type_, lgNames_.getAliasObjectsUtil())) {
            Struct inst_ = args_[0];
            if (!(inst_ instanceof WithParentStruct)) {
                result_.setResult(NullStruct.NULL_VALUE);
                return result_;
            }
            WithParentStruct i_ = (WithParentStruct) inst_;
            Struct par_ = args_[1];
            if (!StringList.quickEq(i_.getParentClassName(),par_.getClassName(_cont))) {
                result_.setResult(NullStruct.NULL_VALUE);
                return result_;
            }
            if (_cont.isContainedSensibleFields(i_)) {
                _cont.failInitEnums();
                return result_;
            }
            i_.setParent(par_);
            result_.setResult(NullStruct.NULL_VALUE);
            return result_;
        }
        String mathType_ = lgNames_.getAliasMath();
        String stringUtil_ = lgNames_.getAliasStringUtil();
        if (StringList.quickEq(type_, stringUtil_)) {
            Argument a_ = new Argument(args_[0]);
            a_ = ExecOperationNode.processString(a_,_cont);
            if (_cont.getCallingState() == null) {
                result_.setResult(a_.getStruct());
            }
            return result_;
        }
        if (StringList.quickEq(type_, mathType_)) {
            if (_cont.isInitEnums()) {
                _cont.failInitEnums();
                return result_;
            }
            /** mathematics "random" calls in order to facilitate uses,
             * despite of the difference between the JAVA names and the user choice names (parameterized in a text file)*/
            StringList paramList_ = _method.getConstraints().getParametersTypes();
            if (paramList_.isEmpty()) {
                result_.setResult(new DoubleStruct(Math.random()));
            } else {
                result_.setResult(new LongStruct(AbMonteCarlo.randomLong(((NumberStruct) args_[0]).longStruct())));
            }
            return result_;
        }
        if (StringList.quickEq(type_, stringBuilderType_)) {
            result_ = AliasCharSequence.invokeMethod(_cont, _method, _struct, _args);
            processError(_cont, result_);
            return result_;
        }
        result_ = AliasReflection.invokeMethod(_cont, _method, _struct, _args);
        if (result_.getResult() != null) {
            return result_;
        }
        processError(_cont,result_);
        if (_cont.callsOrException()) {
            return result_;
        }
        result_ = lgNames_.getOtherResult(_cont, _struct, _method, args_);
        processError(_cont,result_);
        return result_;
    }
    public static ResultErrorStd invokeStdMethod(Analyzable _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        String mathType_ = lgNames_.getAliasMath();
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String nbType_ = lgNames_.getAliasNumber();
        String stringType_ = lgNames_.getAliasString();
        String replType_ = lgNames_.getAliasReplacement();
        if (StringList.quickEq(type_, lgNames_.getAliasResources())) {
        	if (StringList.quickEq(name_, lgNames_.getAliasReadResourcesNames())) {
        		result_.setResult(ResourcesStruct.getResourceNames(_cont));
        	} else {
        		result_.setResult(ResourcesStruct.getResource(_cont, (StringStruct) args_[0]));
        	}
        	return result_;
        }
        if (StringList.quickEq(type_, lgNames_.getAliasObjectsUtil())) {
            if (StringList.quickEq(name_, lgNames_.getAliasSameRef())) {
                result_.setResult(new BooleanStruct(args_[0].sameReference(args_[1])));
                return result_;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasGetParent())) {
                Struct arg_ = args_[0];
                Struct par_ = arg_.getParent();
                _cont.getContextEl().addSensibleField(arg_, par_);
                result_.setResult(par_);
                return result_;
            }
        }
        if (StringList.quickEq(type_, replType_)) {
            ReplacementStruct.calculate(_cont, result_, _method, _struct);
            return result_;
        }
        if (StringList.quickEq(type_, stringType_)
                || StringList.quickEq(type_, lgNames_.getAliasCharSequence())) {
            result_ = AliasCharSequence.invokeStdMethod(_cont, _method, _struct, _args);
            return result_;
        }
        if (StringList.quickEq(type_, lgNames_.getAliasStackTraceElement())) {
            ContextEl c_ = _cont.getContextEl();
            return AliasStackTraceElement.invokeMethod(c_, _method, _struct);
        }
        if (StringList.quickEq(type_, lgNames_.getAliasError())) {
            if (StringList.quickEq(name_, lgNames_.getAliasCurrentStack())) {
                ErroneousStruct err_;
                if (args_.length == 0) {
                    err_ = (ErroneousStruct) _struct;
                    result_.setResult(err_.getStack());
                    return result_;
                }
                if (args_[0] instanceof ErroneousStruct){
                    err_ = (ErroneousStruct) args_[0];
                    result_.setResult(err_.getFullStack());
                    return result_;
                }
                Struct[] arr_ = new Struct[0];
                String cl_ = lgNames_.getAliasStackTraceElement();
                cl_ = PrimitiveTypeUtil.getPrettyArrayType(cl_);
                result_.setResult(new ArrayStruct(arr_, cl_));
                return result_;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasGetMessage())) {
                ErroneousStruct err_ = (ErroneousStruct) _struct;
                result_.setResult(err_.getMessage());
                return result_;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasGetCause())) {
                ErroneousStruct err_ = (ErroneousStruct) _struct;
                result_.setResult(err_.getCause());
                return result_;
            }
            ErroneousStruct err_;
            if (args_.length == 0) {
                err_ = (ErroneousStruct) _struct;
                result_.setResult(err_.getDisplayedString(_cont));
                return result_;
            }
            if (args_[0] instanceof ErroneousStruct){
                err_ = (ErroneousStruct) args_[0];
                result_.setResult(new StringStruct(err_.getStringRep(_cont,err_.getFullStack().getInstance())));
                return result_;
            }
            result_.setResult(new StringStruct(""));
            return result_;
        }
        if (StringList.quickEq(type_, mathType_)) {
            return AliasMath.invokeStdMethod(_cont, _method, _args);
        }
        if (StringList.quickEq(type_, booleanType_)
                || StringList.quickEq(type_, charType_)
                || PrimitiveTypeUtil.isPureNumberClass(new ClassArgumentMatching(type_), lgNames_)
                || StringList.quickEq(type_, nbType_)) {
            NumberStruct.calculate(_cont, result_, _method, _struct, args_);
            return result_;
        }
        return result_;
    }

    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        ResultErrorStd result_ = new ResultErrorStd();

        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        if (StringList.quickEq(name_, lgNames_.getAliasName())) {
            Struct str_ = _args[0];
            if (!(str_ instanceof EnumerableStruct)) {
                result_.setError(lgNames_.getAliasNullPe());
            } else {
                EnumerableStruct en_ = (EnumerableStruct) str_;
                result_.setResult(new StringStruct(en_.getName()));
            }
        } else {
            Struct str_ = _args[0];
            if (!(str_ instanceof EnumerableStruct)) {
                result_.setError(lgNames_.getAliasNullPe());
            } else {
                EnumerableStruct en_ = (EnumerableStruct) str_;
                result_.setResult(new IntStruct(en_.getOrdinal()));
            }
        }
        return result_;
    }

    public Argument defaultInstance(ExecutableCode _cont, String _id) {
        return new Argument(new SimpleObjectStruct());
    }
    public static ResultErrorStd newInstance(ContextEl _cont, ConstructorId _method, Argument... _args) {
        LgNames lgNames_ = _cont.getStandards();
        return lgNames_.instance(_cont,_method,_args);
    }

    protected ResultErrorStd instance(ContextEl _cont, ConstructorId _method, Argument... _args) {
        ResultErrorStd result_;
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getName();
        LgNames lgNames_ = _cont.getStandards();
        String stringBuilderType_ = lgNames_.getAliasStringBuilder();
        result_ = newInstanceStd(_cont, _method, _args);
        if (result_.getResult() != null) {
            return result_;
        }
        if (result_.getError() != null) {
            processError(_cont, result_);
            return result_;
        }
        if (StringList.quickEq(type_, stringBuilderType_)) {
            StringBuilderStruct.instantiate(_cont, result_, _method, args_);
            processError(_cont, result_);
            return result_;
        }
        result_ = lgNames_.getOtherResult(_cont, _method, args_);
        processError(_cont,result_);
        return result_;
    }
    protected static void processError(ContextEl _cont, ResultErrorStd _result) {
        if (_result.getError() != null) {
            String errMessage_ = _result.getErrorMessage();
            if (errMessage_ != null) {
                _cont.setException(new ErrorStruct(_cont,errMessage_,_result.getError()));
            } else {
                _cont.setException(new ErrorStruct(_cont,_result.getError()));
            }
        }
    }

    public static ResultErrorStd newInstanceStd(Analyzable _cont, ConstructorId _method, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getName();
        LgNames lgNames_ = _cont.getStandards();
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String stringType_ = lgNames_.getAliasString();
        String byteType_ = lgNames_.getAliasByte();
        String shortType_ = lgNames_.getAliasShort();
        String intType_ = lgNames_.getAliasInteger();
        String longType_ = lgNames_.getAliasLong();
        String floatType_ = lgNames_.getAliasFloat();
        String doubleType_ = lgNames_.getAliasDouble();
        String replType_ = lgNames_.getAliasReplacement();
        if (StringList.quickEq(type_, replType_)) {
            ReplacementStruct.instantiate(result_, args_);
            return result_;
        }
        if (StringList.quickEq(type_, stringType_)) {
            StringStruct.instantiate(lgNames_, result_, _method, args_);
            return result_;
        }
        if (StringList.quickEq(type_, booleanType_)
                || StringList.quickEq(type_, charType_)
                || StringList.quickEq(type_, byteType_)
                || StringList.quickEq(type_, shortType_)
                || StringList.quickEq(type_, intType_)
                || StringList.quickEq(type_, longType_)
                || StringList.quickEq(type_, floatType_)
                || StringList.quickEq(type_, doubleType_)) {
            NumberStruct.instantiate(_cont, result_, _method, args_);
            return result_;
        }
        return result_;
    }
    public ResultErrorStd getOtherResult(ContextEl _cont, ConstructorId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        res_.setResult(new SimpleObjectStruct());
        return res_;
    }

    public ResultErrorStd getSimpleResult(Analyzable _conf, ClassField _classField) {
        ResultErrorStd result_ = new ResultErrorStd();
        String type_ = _classField.getClassName();
        String name_ = _classField.getFieldName();
        LgNames lgNames_ = _conf.getStandards();
        String charType_ = lgNames_.getAliasCharacter();
        String byteType_ = lgNames_.getAliasByte();
        String shortType_ = lgNames_.getAliasShort();
        String intType_ = lgNames_.getAliasInteger();
        String longType_ = lgNames_.getAliasLong();
        String floatType_ = lgNames_.getAliasFloat();
        if (StringList.quickEq(type_, charType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new CharStruct(Character.MIN_VALUE));
            } else {
                result_.setResult(new CharStruct(Character.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, byteType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new ByteStruct(Byte.MIN_VALUE));
            } else {
                result_.setResult(new ByteStruct(Byte.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, shortType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new ShortStruct(Short.MIN_VALUE));
            } else {
                result_.setResult(new ShortStruct(Short.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, intType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new IntStruct(Integer.MIN_VALUE));
            } else {
                result_.setResult(new IntStruct(Integer.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, longType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new LongStruct(Long.MIN_VALUE));
            } else {
                result_.setResult(new LongStruct(Long.MAX_VALUE));
            }
        } else if (StringList.quickEq(type_, floatType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new FloatStruct(Float.MIN_VALUE));
            } else {
                result_.setResult(new FloatStruct(Float.MAX_VALUE));
            }
        } else {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new DoubleStruct(Double.MIN_VALUE));
            } else {
                result_.setResult(new DoubleStruct(Double.MAX_VALUE));
            }
        }
        return result_;
    }

    static Struct[] getObjects(Argument... _args) {
        int len_ = _args.length;
        Struct[] classes_ = new Struct[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args[i].getStruct();
        }
        return classes_;
    }
    public void buildIterable(ContextEl _context) {
        //local names
        _context.getAnalyzing().setCurrentBlock(null);
        _context.getAnalyzing().setEnabledInternVars(true);
        Classes cl_ = _context.getClasses();
        String next_ = getAliasNext();
        String hasNext_ = getAliasHasNext();
        String nextPair_ = getAliasNextPair();
        String hasNextPair_ = getAliasHasNextPair();
        String locName_ = _context.getNextTempVar();
        String exp_;
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIterable(),"<?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setIteratorVarCust(locName_);
        String iterator_ = getAliasIterator();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(iterator_,PARS));
        cl_.setExpsIteratorCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIteratorType(),"<?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setHasNextVarCust(locName_);
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(hasNext_,PARS));
        cl_.setExpsHasNextCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIteratorType(),"<?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setNextVarCust(locName_);
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(next_,PARS));
        cl_.setExpsNextCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIterableTable(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setIteratorTableVarCust(locName_);
        String iteratorTable_ = getAliasIteratorTable();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(iteratorTable_,PARS));
        cl_.setExpsIteratorTableCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIteratorTableType(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setHasNextPairVarCust(locName_);
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(hasNextPair_,PARS));
        cl_.setExpsHasNextPairCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIteratorTableType(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setNextPairVarCust(locName_);
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(nextPair_,PARS));
        cl_.setExpsNextPairCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasPairType(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setFirstVarCust(locName_);
        String first_ = getAliasGetFirst();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(first_,PARS));
        cl_.setExpsFirstCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasPairType(),"<?,?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setSecondVarCust(locName_);
        String second_ = getAliasGetSecond();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(second_,PARS));
        cl_.setExpsSecondCust(ElUtil.getAnalyzedOperationsReadOnly(exp_, _context, Calculation.staticCalculation(MethodAccessKind.STATIC)));
    }
    public IterableAnalysisResult getCustomType(StringList _names, ContextEl _context) {
        StringList out_ = new StringList();
        StringMap<StringList> vars_ = _context.getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        for (String f: _names) {
            String iterable_ = getAliasIterable();
            String type_ = Templates.getGeneric(f,iterable_,_context,mapping_);
            if (type_ != null) {
                out_.add(type_);
            }
        }
        return new IterableAnalysisResult(out_);
    }

    public StringMap<String> buildFiles(ContextEl _context) {
        StringMap<String> files_ = new StringMap<String>();
        LgNames stds_ = _context.getStandards();
        String content_ = PredefinedClasses.getBracedIterableType(_context);
        String name_;
        name_ = stds_.getAliasIterable();
        predefinedClasses.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedIteratorType(_context);
        name_ = stds_.getAliasIteratorType();
        predefinedClasses.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedIterableTableType(_context);
        name_ = stds_.getAliasIterableTable();
        predefinedClasses.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedIteratorTableType(_context);
        name_ = stds_.getAliasIteratorTableType();
        predefinedClasses.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedPairType(_context);
        name_ = stds_.getAliasPairType();
        predefinedClasses.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedEnumType(_context);
        name_ = stds_.getAliasEnumType();
        predefinedClasses.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedEnumParamType(_context);
        name_ = stds_.getAliasEnumParam();
        predefinedClasses.add(name_);
        files_.put(name_, content_);
        predefinedInterfacesInitOrder.add(stds_.getAliasIterable());
        predefinedInterfacesInitOrder.add(stds_.getAliasIteratorType());
        predefinedInterfacesInitOrder.add(stds_.getAliasIterableTable());
        predefinedInterfacesInitOrder.add(stds_.getAliasIteratorTableType());
        predefinedInterfacesInitOrder.add(stds_.getAliasPairType());
        predefinedInterfacesInitOrder.add(stds_.getAliasEnumParam());
        predefinedInterfacesInitOrder.add(stds_.getAliasEnumType());
        return files_;
    }

    public AbstractFunctionalInstance newFunctionalInstance(String _className, ContextEl _contextEl) {
        return new FunctionalInstance(_className);
    }
    public String getStructClassName(Struct _struct, ContextEl _context) {
        return _struct.getClassName(_context);
    }
    
    public StringMap<StandardType> getStandards() {
        return standards;
    }
    public StringMap<PrimitiveType> getPrimitiveTypes() {
        return primTypes.getPrimitiveTypes();
    }
    public String getAliasObject() {
        return coreNames.getAliasObject();
    }
    public void setAliasObject(String _aliasObject) {
        coreNames.setAliasObject(_aliasObject);
    }
    public String getAliasVoid() {
        return coreNames.getAliasVoid();
    }
    public void setAliasVoid(String _aliasVoid) {
        coreNames.setAliasVoid(_aliasVoid);
    }
    public String getAliasCharSequence() {
        return charSeq.getAliasCharSequence();
    }
    public void setAliasCharSequence(String _aliasCharSequence) {
        charSeq.setAliasCharSequence(_aliasCharSequence);
    }

    public String getAliasIterator() {
        return predefTypes.getAliasIterator();
    }
    public void setAliasIterator(String _aliasIterator) {
        predefTypes.setAliasIterator(_aliasIterator);
    }
    public String getAliasIteratorType() {
        return predefTypes.getAliasIteratorType();
    }
    public void setAliasIteratorType(String _aliasIteratorType) {
        predefTypes.setAliasIteratorType(_aliasIteratorType);
    }
    public String getAliasIterable() {
        return predefTypes.getAliasIterable();
    }
    public void setAliasIterable(String _aliasIterable) {
        predefTypes.setAliasIterable(_aliasIterable);
    }
    public String getAliasEnumParam() {
        return predefTypes.getAliasEnumParam();
    }
    public void setAliasEnumParam(String _aliasEnumParam) {
        predefTypes.setAliasEnumParam(_aliasEnumParam);
    }
    public String getAliasEnumType() {
        return predefTypes.getAliasEnumType();
    }
    public void setAliasEnumType(String _aliasEnum) {
        predefTypes.setAliasEnumType(_aliasEnum);
    }
    public String getAliasEnums() {
        return coreNames.getAliasEnums();
    }
    public void setAliasEnums(String _aliasEnums) {
        coreNames.setAliasEnums(_aliasEnums);
    }
    public String getAliasError() {
        return coreNames.getAliasError();
    }
    public void setAliasError(String _aliasError) {
        coreNames.setAliasError(_aliasError);
    }
    public String getAliasGetMessage() {
        return coreNames.getAliasGetMessage();
    }
    public void setAliasGetMessage(String _aliasGetMessage) {
        coreNames.setAliasGetMessage(_aliasGetMessage);
    }

    public String getAliasGetCause() {
        return coreNames.getAliasGetCause();
    }

    public void setAliasGetCause(String _aliasGetCause) {
        coreNames.setAliasGetCause(_aliasGetCause);
    }
    public String getAliasBadSize() {
        return coreNames.getAliasBadSize();
    }
    public void setAliasBadSize(String _aliasBadSize) {
        coreNames.setAliasBadSize(_aliasBadSize);
    }
    public String getAliasDivisionZero() {
        return coreNames.getAliasDivisionZero();
    }
    public void setAliasDivisionZero(String _aliasDivisionZero) {
        coreNames.setAliasDivisionZero(_aliasDivisionZero);
    }
    public String getAliasCastType() {
        return coreNames.getAliasCastType();
    }
    public void setAliasCastType(String _aliasCast) {
        coreNames.setAliasCastType(_aliasCast);
    }
    public String getAliasStore() {
        return coreNames.getAliasStore();
    }
    public void setAliasStore(String _aliasStore) {
        coreNames.setAliasStore(_aliasStore);
    }
    public String getAliasNullPe() {
        return coreNames.getAliasNullPe();
    }
    public void setAliasNullPe(String _aliasNullPe) {
        coreNames.setAliasNullPe(_aliasNullPe);
    }
    public String getAliasNbFormat() {
        return coreNames.getAliasNbFormat();
    }
    public void setAliasNbFormat(String _aliasNbFormat) {
        coreNames.setAliasNbFormat(_aliasNbFormat);
    }
    public String getAliasBadEncode() {
        return coreNames.getAliasBadEncode();
    }
    public void setAliasBadEncode(String _aliasBadEncode) {
        coreNames.setAliasBadEncode(_aliasBadEncode);
    }
    public String getAliasBadIndex() {
        return coreNames.getAliasBadIndex();
    }
    public void setAliasBadIndex(String _aliasBadIndex) {
        coreNames.setAliasBadIndex(_aliasBadIndex);
    }

    public String getAliasIllegalArg() {
        return coreNames.getAliasIllegalArg();
    }

    public void setAliasIllegalArg(String _aliasIllegalArg) {
        coreNames.setAliasIllegalArg(_aliasIllegalArg);
    }
    public String getAliasSof() {
        return coreNames.getAliasSof();
    }
    public void setAliasSof(String _aliasSof) {
        coreNames.setAliasSof(_aliasSof);
    }
    public String getAliasPrimBoolean() {
        return primTypes.getAliasPrimBoolean();
    }
    public void setAliasPrimBoolean(String _aliasPrimBoolean) {
        primTypes.setAliasPrimBoolean(_aliasPrimBoolean);
    }
    public String getAliasMath() {
        return mathRef.getAliasMath();
    }
    public void setAliasMath(String _aliasMath) {
        mathRef.setAliasMath(_aliasMath);
    }
    public String getAliasPrimByte() {
        return primTypes.getAliasPrimByte();
    }
    public void setAliasPrimByte(String _aliasPrimByte) {
        primTypes.setAliasPrimByte(_aliasPrimByte);
    }
    public String getAliasPrimShort() {
        return primTypes.getAliasPrimShort();
    }
    public void setAliasPrimShort(String _aliasPrimShort) {
        primTypes.setAliasPrimShort(_aliasPrimShort);
    }
    public String getAliasPrimChar() {
        return primTypes.getAliasPrimChar();
    }
    public void setAliasPrimChar(String _aliasPrimChar) {
        primTypes.setAliasPrimChar(_aliasPrimChar);
    }
    public String getAliasPrimInteger() {
        return primTypes.getAliasPrimInteger();
    }
    public void setAliasPrimInteger(String _aliasPrimInteger) {
        primTypes.setAliasPrimInteger(_aliasPrimInteger);
    }
    public String getAliasPrimLong() {
        return primTypes.getAliasPrimLong();
    }
    public void setAliasPrimLong(String _aliasPrimLong) {
        primTypes.setAliasPrimLong(_aliasPrimLong);
    }
    public String getAliasPrimFloat() {
        return primTypes.getAliasPrimFloat();
    }
    public void setAliasPrimFloat(String _aliasPrimFloat) {
        primTypes.setAliasPrimFloat(_aliasPrimFloat);
    }
    public String getAliasPrimDouble() {
        return primTypes.getAliasPrimDouble();
    }
    public void setAliasPrimDouble(String _aliasPrimDouble) {
        primTypes.setAliasPrimDouble(_aliasPrimDouble);
    }
   
    public String getAliasCompareTo() {
        return nbAlias.getAliasCompareTo();
    }
    public void setAliasCompareTo(String _aliasCompareTo) {
        nbAlias.setAliasCompareTo(_aliasCompareTo);
    }
    public String getAliasCompare() {
        return nbAlias.getAliasCompare();
    }
    public void setAliasCompare(String _aliasCompare) {
        nbAlias.setAliasCompare(_aliasCompare);
    }
    public String getAliasEquals() {
        return nbAlias.getAliasEquals();
    }
    public void setAliasEquals(String _aliasEquals) {
        nbAlias.setAliasEquals(_aliasEquals);
    }
    public String getAliasToStringMethod() {
        return nbAlias.getAliasToStringMethod();
    }
    public void setAliasToStringMethod(String _aliasToString) {
        nbAlias.setAliasToStringMethod(_aliasToString);
    }
    public String getAliasValueOfMethod() {
        return nbAlias.getAliasValueOfMethod();
    }
    public void setAliasValueOfMethod(String _aliasValueOf) {
        nbAlias.setAliasValueOfMethod(_aliasValueOf);
    }

    public void setAliasMaxValueField(String _aliasMaxValueField) {
        nbAlias.setAliasMaxValueField(_aliasMaxValueField);
    }
    public String getAliasMinValueField() {
        return nbAlias.getAliasMinValueField();
    }
    public void setAliasMinValueField(String _aliasMinValueField) {
        nbAlias.setAliasMinValueField(_aliasMinValueField);
    }
    public String getAliasBoolean() {
        return nbAlias.getAliasBoolean();
    }
    public void setAliasBoolean(String _aliasBoolean) {
        nbAlias.setAliasBoolean(_aliasBoolean);
    }
    public String getAliasByte() {
        return nbAlias.getAliasByte();
    }
    public void setAliasByte(String _aliasByte) {
        nbAlias.setAliasByte(_aliasByte);
    }
    public String getAliasShort() {
        return nbAlias.getAliasShort();
    }
    public void setAliasShort(String _aliasShort) {
        nbAlias.setAliasShort(_aliasShort);
    }
    public String getAliasCharacter() {
        return nbAlias.getAliasCharacter();
    }
    public void setAliasCharacter(String _aliasCharacter) {
        nbAlias.setAliasCharacter(_aliasCharacter);
    }
    public String getAliasInteger() {
        return nbAlias.getAliasInteger();
    }
    public void setAliasInteger(String _aliasInteger) {
        nbAlias.setAliasInteger(_aliasInteger);
    }
    public String getAliasLong() {
        return nbAlias.getAliasLong();
    }
    public void setAliasLong(String _aliasLong) {
        nbAlias.setAliasLong(_aliasLong);
    }
    public String getAliasFloat() {
        return nbAlias.getAliasFloat();
    }
    public void setAliasFloat(String _aliasFloat) {
        nbAlias.setAliasFloat(_aliasFloat);
    }
    public String getAliasDouble() {
        return nbAlias.getAliasDouble();
    }
    public void setAliasDouble(String _aliasDouble) {
        nbAlias.setAliasDouble(_aliasDouble);
    }
    public String getAliasNumber() {
        return nbAlias.getAliasNumber();
    }
    public void setAliasNumber(String _aliasNumber) {
        nbAlias.setAliasNumber(_aliasNumber);
    }
    public String getAliasParseBoolean() {
        return nbAlias.getAliasParseBoolean();
    }
    public void setAliasParseBoolean(String _aliasParseBoolean) {
        nbAlias.setAliasParseBoolean(_aliasParseBoolean);
    }
    public String getAliasParseByte() {
        return nbAlias.getAliasParseByte();
    }
    public void setAliasParseByte(String _aliasParseByte) {
        nbAlias.setAliasParseByte(_aliasParseByte);
    }
    public String getAliasParseShort() {
        return nbAlias.getAliasParseShort();
    }
    public void setAliasParseShort(String _aliasParseShort) {
        nbAlias.setAliasParseShort(_aliasParseShort);
    }
    public String getAliasParseInt() {
        return nbAlias.getAliasParseInt();
    }
    public void setAliasParseInt(String _aliasParseInt) {
        nbAlias.setAliasParseInt(_aliasParseInt);
    }
    public String getAliasParseLong() {
        return nbAlias.getAliasParseLong();
    }
    public void setAliasParseLong(String _aliasParseLong) {
        nbAlias.setAliasParseLong(_aliasParseLong);
    }
    public String getAliasParseFloat() {
        return nbAlias.getAliasParseFloat();
    }
    public void setAliasParseFloat(String _aliasParseFloat) {
        nbAlias.setAliasParseFloat(_aliasParseFloat);
    }
    public String getAliasParseDouble() {
        return nbAlias.getAliasParseDouble();
    }
    public void setAliasParseDouble(String _aliasParseDouble) {
        nbAlias.setAliasParseDouble(_aliasParseDouble);
    }
    public String getAliasParseByteOrNull() {
        return nbAlias.getAliasParseByteOrNull();
    }
    public void setAliasParseByteOrNull(String _aliasParseByte) {
        nbAlias.setAliasParseByteOrNull(_aliasParseByte);
    }
    public String getAliasParseShortOrNull() {
        return nbAlias.getAliasParseShortOrNull();
    }
    public void setAliasParseShortOrNull(String _aliasParseShort) {
        nbAlias.setAliasParseShortOrNull(_aliasParseShort);
    }
    public String getAliasParseIntOrNull() {
        return nbAlias.getAliasParseIntOrNull();
    }
    public void setAliasParseIntOrNull(String _aliasParseInt) {
        nbAlias.setAliasParseIntOrNull(_aliasParseInt);
    }
    public String getAliasParseLongOrNull() {
        return nbAlias.getAliasParseLongOrNull();
    }
    public void setAliasParseLongOrNull(String _aliasParseLong) {
        nbAlias.setAliasParseLongOrNull(_aliasParseLong);
    }
    public String getAliasParseFloatOrNull() {
        return nbAlias.getAliasParseFloatOrNull();
    }
    public void setAliasParseFloatOrNull(String _aliasParseFloat) {
        nbAlias.setAliasParseFloatOrNull(_aliasParseFloat);
    }
    public String getAliasParseDoubleOrNull() {
        return nbAlias.getAliasParseDoubleOrNull();
    }
    public void setAliasParseDoubleOrNull(String _aliasParseDouble) {
        nbAlias.setAliasParseDoubleOrNull(_aliasParseDouble);
    }

    public String getAliasBooleanValue() {
        return nbAlias.getAliasBooleanValue();
    }
    public void setAliasBooleanValue(String _aliasBooleanValue) {
        nbAlias.setAliasBooleanValue(_aliasBooleanValue);
    }
    public String getAliasByteValue() {
        return nbAlias.getAliasByteValue();
    }
    public void setAliasByteValue(String _aliasByteValue) {
        nbAlias.setAliasByteValue(_aliasByteValue);
    }
    public String getAliasShortValue() {
        return nbAlias.getAliasShortValue();
    }
    public void setAliasShortValue(String _aliasShortValue) {
        nbAlias.setAliasShortValue(_aliasShortValue);
    }
    public String getAliasCharValue() {
        return nbAlias.getAliasCharValue();
    }
    public void setAliasCharValue(String _aliasCharValue) {
        nbAlias.setAliasCharValue(_aliasCharValue);
    }
    public String getAliasIntValue() {
        return nbAlias.getAliasIntValue();
    }
    public void setAliasIntValue(String _aliasIntValue) {
        nbAlias.setAliasIntValue(_aliasIntValue);
    }
    public String getAliasLongValue() {
        return nbAlias.getAliasLongValue();
    }
    public void setAliasLongValue(String _aliasLongValue) {
        nbAlias.setAliasLongValue(_aliasLongValue);
    }
    public String getAliasFloatValue() {
        return nbAlias.getAliasFloatValue();
    }
    public void setAliasFloatValue(String _aliasFloatValue) {
        nbAlias.setAliasFloatValue(_aliasFloatValue);
    }
    public String getAliasDoubleValue() {
        return nbAlias.getAliasDoubleValue();
    }
    public void setAliasDoubleValue(String _aliasDoubleValue) {
        nbAlias.setAliasDoubleValue(_aliasDoubleValue);
    }
    public String getAliasDigit() {
        return nbAlias.getAliasDigit();
    }
    public void setAliasDigit(String _aliasDigit) {
        nbAlias.setAliasDigit(_aliasDigit);
    }
    public String getAliasIsDigit() {
        return nbAlias.getAliasIsDigit();
    }
    public void setAliasIsDigit(String _aliasIsDigit) {
        nbAlias.setAliasIsDigit(_aliasIsDigit);
    }
    public String getAliasIsLetter() {
        return nbAlias.getAliasIsLetter();
    }
    public void setAliasIsLetter(String _aliasIsLetter) {
        nbAlias.setAliasIsLetter(_aliasIsLetter);
    }
    public String getAliasIsLetterOrDigit() {
        return nbAlias.getAliasIsLetterOrDigit();
    }
    public void setAliasIsLetterOrDigit(String _aliasIsLetterOrDigit) {
        nbAlias.setAliasIsLetterOrDigit(_aliasIsLetterOrDigit);
    }
    public String getAliasIsWordChar() {
        return nbAlias.getAliasIsWordChar();
    }
    public void setAliasIsWordChar(String _aliasIsWordChar) {
        nbAlias.setAliasIsWordChar(_aliasIsWordChar);
    }
    public String getAliasIsLowerCase() {
        return nbAlias.getAliasIsLowerCase();
    }
    public void setAliasIsLowerCase(String _aliasIsLowerCase) {
        nbAlias.setAliasIsLowerCase(_aliasIsLowerCase);
    }
    public String getAliasIsUpperCase() {
        return nbAlias.getAliasIsUpperCase();
    }
    public void setAliasIsUpperCase(String _aliasIsUpperCase) {
        nbAlias.setAliasIsUpperCase(_aliasIsUpperCase);
    }
    public String getAliasIsWhitespace() {
        return nbAlias.getAliasIsWhitespace();
    }
    public void setAliasIsWhitespace(String _aliasIsWhitespace) {
        nbAlias.setAliasIsWhitespace(_aliasIsWhitespace);
    }
    public String getAliasIsSpace() {
        return nbAlias.getAliasIsSpace();
    }
    public void setAliasIsSpace(String _aliasIsSpace) {
        nbAlias.setAliasIsSpace(_aliasIsSpace);
    }
    public String getAliasIsInfinite() {
        return nbAlias.getAliasIsInfinite();
    }
    public void setAliasIsInfinite(String _aliasIsInfinite) {
        nbAlias.setAliasIsInfinite(_aliasIsInfinite);
    }
    public String getAliasIsNan() {
        return nbAlias.getAliasIsNan();
    }
    public void setAliasIsNan(String _aliasIsNan) {
        nbAlias.setAliasIsNan(_aliasIsNan);
    }
    public String getAliasForDigit() {
        return nbAlias.getAliasForDigit();
    }
    public void setAliasForDigit(String _aliasForDigit) {
        nbAlias.setAliasForDigit(_aliasForDigit);
    }
    public String getAliasGetDirectionality() {
        return nbAlias.getAliasGetDirectionality();
    }
    public void setAliasGetDirectionality(String _aliasGetDirectionality) {
        nbAlias.setAliasGetDirectionality(_aliasGetDirectionality);
    }
    public String getAliasGetType() {
        return reflect.getAliasGetType();
    }
    public void setAliasGetType(String _aliasGetType) {
        reflect.setAliasGetType(_aliasGetType);
    }
    public String getAliasGetCharType() {
        return nbAlias.getAliasGetCharType();
    }
    public void setAliasGetCharType(String _aliasGetType) {
        nbAlias.setAliasGetCharType(_aliasGetType);
    }
    public String getAliasString() {
        return charSeq.getAliasString();
    }
    public void setAliasString(String _aliasString) {
        charSeq.setAliasString(_aliasString);
    }
    public String getAliasLength() {
        return charSeq.getAliasLength();
    }
    public void setAliasLength(String _aliasLength) {
        charSeq.setAliasLength(_aliasLength);
    }
    public String getAliasCharAt() {
        return charSeq.getAliasCharAt();
    }
    public void setAliasCharAt(String _aliasCharAt) {
        charSeq.setAliasCharAt(_aliasCharAt);
    }
    public String getAliasToCharArray() {
        return charSeq.getAliasToCharArray();
    }
    public void setAliasToCharArray(String _aliasToCharArray) {
        charSeq.setAliasToCharArray(_aliasToCharArray);
    }
    public String getAliasSplit() {
        return charSeq.getAliasSplit();
    }
    public void setAliasSplit(String _aliasSplit) {
        charSeq.setAliasSplit(_aliasSplit);
    }
    public String getAliasSplitStrings() {
        return charSeq.getAliasSplitStrings();
    }
    public void setAliasSplitStrings(String _aliasSplitStrings) {
        charSeq.setAliasSplitStrings(_aliasSplitStrings);
    }
    public String getAliasSplitChars() {
        return charSeq.getAliasSplitChars();
    }
    public void setAliasSplitChars(String _aliasSplitChars) {
        charSeq.setAliasSplitChars(_aliasSplitChars);
    }
    public String getAliasReplace() {
        return charSeq.getAliasReplace();
    }
    public void setAliasReplace(String _aliasReplace) {
        charSeq.setAliasReplace(_aliasReplace);
    }
    public String getAliasReplaceMultiple() {
        return charSeq.getAliasReplaceMultiple();
    }
    public void setAliasReplaceMultiple(String _aliasReplaceMultiple) {
        charSeq.setAliasReplaceMultiple(_aliasReplaceMultiple);
    }
    public String getAliasEqualsIgnoreCase() {
        return charSeq.getAliasEqualsIgnoreCase();
    }
    public void setAliasEqualsIgnoreCase(String _aliasEqualsIgnoreCase) {
        charSeq.setAliasEqualsIgnoreCase(_aliasEqualsIgnoreCase);
    }
    public String getAliasCompareToIgnoreCase() {
        return charSeq.getAliasCompareToIgnoreCase();
    }
    public void setAliasCompareToIgnoreCase(String _aliasCompareToIgnoreCase) {
        charSeq.setAliasCompareToIgnoreCase(_aliasCompareToIgnoreCase);
    }
    public String getAliasContains() {
        return charSeq.getAliasContains();
    }
    public void setAliasContains(String _aliasContains) {
        charSeq.setAliasContains(_aliasContains);
    }
    public String getAliasEndsWith() {
        return charSeq.getAliasEndsWith();
    }
    public void setAliasEndsWith(String _aliasEndsWith) {
        charSeq.setAliasEndsWith(_aliasEndsWith);
    }
    public String getAliasStartsWith() {
        return charSeq.getAliasStartsWith();
    }
    public void setAliasStartsWith(String _aliasStartsWith) {
        charSeq.setAliasStartsWith(_aliasStartsWith);
    }
    public String getAliasIndexOf() {
        return charSeq.getAliasIndexOf();
    }
    public void setAliasIndexOf(String _aliasIndexOf) {
        charSeq.setAliasIndexOf(_aliasIndexOf);
    }
    public String getAliasFormat() {
        return charSeq.getAliasFormat();
    }
    public void setAliasFormat(String _aliasFormat) {
        charSeq.setAliasFormat(_aliasFormat);
    }
    public String getAliasGetBytes() {
        return charSeq.getAliasGetBytes();
    }
    public void setAliasGetBytes(String _aliasGetBytes) {
        charSeq.setAliasGetBytes(_aliasGetBytes);
    }
    public String getAliasIsEmpty() {
        return charSeq.getAliasIsEmpty();
    }
    public void setAliasIsEmpty(String _aliasIsEmpty) {
        charSeq.setAliasIsEmpty(_aliasIsEmpty);
    }
    public String getAliasLastIndexOf() {
        return charSeq.getAliasLastIndexOf();
    }
    public void setAliasLastIndexOf(String _aliasLastIndexOf) {
        charSeq.setAliasLastIndexOf(_aliasLastIndexOf);
    }
    public String getAliasRegionMatches() {
        return charSeq.getAliasRegionMatches();
    }
    public void setAliasRegionMatches(String _aliasRegionMatches) {
        charSeq.setAliasRegionMatches(_aliasRegionMatches);
    }
    public String getAliasSubstring() {
        return charSeq.getAliasSubstring();
    }
    public void setAliasSubstring(String _aliasSubstring) {
        charSeq.setAliasSubstring(_aliasSubstring);
    }
    public String getAliasSubSequence() {
        return charSeq.getAliasSubSequence();
    }
    public void setAliasSubSequence(String _aliasSubSequence) {
        charSeq.setAliasSubSequence(_aliasSubSequence);
    }
    public String getAliasToLowerCase() {
        return charSeq.getAliasToLowerCase();
    }
    public void setAliasToLowerCase(String _aliasToLowerCase) {
        charSeq.setAliasToLowerCase(_aliasToLowerCase);
    }
    public String getAliasToUpperCase() {
        return charSeq.getAliasToUpperCase();
    }
    public void setAliasToUpperCase(String _aliasToUpperCase) {
        charSeq.setAliasToUpperCase(_aliasToUpperCase);
    }
    public String getAliasTrim() {
        return charSeq.getAliasTrim();
    }
    public void setAliasTrim(String _aliasTrim) {
        charSeq.setAliasTrim(_aliasTrim);
    }
    public String getAliasStringBuilder() {
        return charSeq.getAliasStringBuilder();
    }
    public void setAliasStringBuilder(String _aliasStringBuilder) {
        charSeq.setAliasStringBuilder(_aliasStringBuilder);
    }
    public String getAliasAppend() {
        return charSeq.getAliasAppend();
    }
    public void setAliasAppend(String _aliasAppend) {
        charSeq.setAliasAppend(_aliasAppend);
    }
    public String getAliasCapacity() {
        return charSeq.getAliasCapacity();
    }
    public void setAliasCapacity(String _aliasCapacity) {
        charSeq.setAliasCapacity(_aliasCapacity);
    }
    public String getAliasClear() {
        return charSeq.getAliasClear();
    }
    public void setAliasClear(String _aliasClear) {
        charSeq.setAliasClear(_aliasClear);
    }
    public String getAliasDelete() {
        return charSeq.getAliasDelete();
    }
    public void setAliasDelete(String _aliasDelete) {
        charSeq.setAliasDelete(_aliasDelete);
    }
    public String getAliasDeleteCharAt() {
        return charSeq.getAliasDeleteCharAt();
    }
    public void setAliasDeleteCharAt(String _aliasDeleteCharAt) {
        charSeq.setAliasDeleteCharAt(_aliasDeleteCharAt);
    }
    public String getAliasEnsureCapacity() {
        return charSeq.getAliasEnsureCapacity();
    }
    public void setAliasEnsureCapacity(String _aliasEnsureCapacity) {
        charSeq.setAliasEnsureCapacity(_aliasEnsureCapacity);
    }
    public String getAliasInsert() {
        return charSeq.getAliasInsert();
    }
    public void setAliasInsert(String _aliasInsert) {
        charSeq.setAliasInsert(_aliasInsert);
    }
    public String getAliasReverse() {
        return charSeq.getAliasReverse();
    }
    public void setAliasReverse(String _aliasReverse) {
        charSeq.setAliasReverse(_aliasReverse);
    }
    public String getAliasSetCharAt() {
        return charSeq.getAliasSetCharAt();
    }
    public void setAliasSetCharAt(String _aliasSetCharAt) {
        charSeq.setAliasSetCharAt(_aliasSetCharAt);
    }
    public String getAliasSetLength() {
        return charSeq.getAliasSetLength();
    }
    public void setAliasSetLength(String _aliasSetLength) {
        charSeq.setAliasSetLength(_aliasSetLength);
    }
    public String getAliasSame() {
        return charSeq.getAliasSame();
    }
    public void setAliasSame(String _aliasSetLength) {
        charSeq.setAliasSame(_aliasSetLength);
    }
    public String getAliasTrimToSize() {
        return charSeq.getAliasTrimToSize();
    }
    public void setAliasTrimToSize(String _aliasTrimToSize) {
        charSeq.setAliasTrimToSize(_aliasTrimToSize);
    }

    public String getAliasNext() {
        return predefTypes.getAliasNext();
    }
    public void setAliasNext(String _aliasNext) {
        predefTypes.setAliasNext(_aliasNext);
    }
    public String getAliasHasNext() {
        return predefTypes.getAliasHasNext();
    }
    public void setAliasHasNext(String _aliasHasNext) {
        predefTypes.setAliasHasNext(_aliasHasNext);
    }
    
    public String getAliasIterableTable() {
        return predefTypes.getAliasIterableTable();
    }
    public void setAliasIterableTable(String _aliasIterableTable) {
        predefTypes.setAliasIterableTable(_aliasIterableTable);
    }
    public String getAliasIteratorTable() {
        return predefTypes.getAliasIteratorTable();
    }
    public void setAliasIteratorTable(String _aliasIteratorTable) {
        predefTypes.setAliasIteratorTable(_aliasIteratorTable);
    }
    public String getAliasIteratorTableType() {
        return predefTypes.getAliasIteratorTableType();
    }
    public void setAliasIteratorTableType(String _aliasIteratorTableType) {
        predefTypes.setAliasIteratorTableType(_aliasIteratorTableType);
    }
    public String getAliasHasNextPair() {
        return predefTypes.getAliasHasNextPair();
    }
    public void setAliasHasNextPair(String _aliasHasNextPair) {
        predefTypes.setAliasHasNextPair(_aliasHasNextPair);
    }
    public String getAliasNextPair() {
        return predefTypes.getAliasNextPair();
    }
    public void setAliasNextPair(String _aliasHasNextPair) {
        predefTypes.setAliasNextPair(_aliasHasNextPair);
    }
    public String getAliasPairType() {
        return predefTypes.getAliasPairType();
    }
    public void setAliasPairType(String _aliasPairType) {
        predefTypes.setAliasPairType(_aliasPairType);
    }
    public String getAliasGetFirst() {
        return predefTypes.getAliasGetFirst();
    }
    public void setAliasGetFirst(String _aliasGetFirst) {
        predefTypes.setAliasGetFirst(_aliasGetFirst);
    }
    public String getAliasGetSecond() {
        return predefTypes.getAliasGetSecond();
    }
    public void setAliasGetSecond(String _aliasGetSecond) {
        predefTypes.setAliasGetSecond(_aliasGetSecond);
    }
    public String getAliasName() {
        return coreNames.getAliasName();
    }
    public void setAliasName(String _aliasName) {
        coreNames.setAliasName(_aliasName);
    }
    public String getAliasOrdinal() {
        return coreNames.getAliasOrdinal();
    }
    public void setAliasOrdinal(String _aliasOrdinal) {
        coreNames.setAliasOrdinal(_aliasOrdinal);
    }
    public String getAliasReplacement() {
        return charSeq.getAliasReplacement();
    }
    public void setAliasReplacement(String _aliasReplacement) {
        charSeq.setAliasReplacement(_aliasReplacement);
    }
    public String getAliasGetOldString() {
        return charSeq.getAliasGetOldString();
    }
    public void setAliasGetOldString(String _aliasGetOldString) {
        charSeq.setAliasGetOldString(_aliasGetOldString);
    }
    public String getAliasGetNewString() {
        return charSeq.getAliasGetNewString();
    }
    public void setAliasGetNewString(String _aliasGetNewString) {
        charSeq.setAliasGetNewString(_aliasGetNewString);
    }
    public String getAliasAbs() {
        return mathRef.getAliasAbs();
    }
    public void setAliasAbs(String _aliasAbs) {
        mathRef.setAliasAbs(_aliasAbs);
    }
    public String getAliasQuot() {
        return mathRef.getAliasQuot();
    }
    public void setAliasQuot(String _aliasQuot) {
        mathRef.setAliasQuot(_aliasQuot);
    }
    public String getAliasMod() {
        return mathRef.getAliasMod();
    }
    public void setAliasMod(String _aliasMod) {
        mathRef.setAliasMod(_aliasMod);
    }
    public String getAliasErrorInitClass() {
        return coreNames.getAliasErrorInitClass();
    }
    public void setAliasErrorInitClass(String _aliasErrorInitClass) {
        coreNames.setAliasErrorInitClass(_aliasErrorInitClass);
    }
    public String getAliasClone() {
        return coreNames.getAliasClone();
    }
    public void setAliasClone(String _aliasClone) {
        coreNames.setAliasClone(_aliasClone);
    }
    public String getAliasReadResourcesNames() {
    	return coreNames.getAliasReadResourcesNames();
    }
    public void setAliasReadResourcesNames(String _aliasReadResourcesNames) {
    	coreNames.setAliasReadResourcesNames(_aliasReadResourcesNames);
    }
    public String getAliasReadResources() {
        return coreNames.getAliasReadResources();
    }
    public void setAliasReadResources(String _aliasReadResources) {
        coreNames.setAliasReadResources(_aliasReadResources);
    }
    public String getAliasResources() {
        return coreNames.getAliasResources();
    }
    public void setAliasResources(String _aliasResources) {
        coreNames.setAliasResources(_aliasResources);
    }
    public String getAliasEnumValues() {
        return predefTypes.getAliasEnumValues();
    }
    public void setAliasEnumValues(String _aliasValues) {
        predefTypes.setAliasEnumValues(_aliasValues);
    }
    public String getAliasEnumPredValueOf() {
        return predefTypes.getAliasEnumPredValueOf();
    }
    public void setAliasEnumPredValueOf(String _aliasValues) {
        predefTypes.setAliasEnumPredValueOf(_aliasValues);
    }

    public String getAliasIterableVar() {
        return predefTypes.getAliasIterableVar();
    }

    public void setAliasIterableVar(String aliasIterableVar) {
        predefTypes.setAliasIterableVar(aliasIterableVar);
    }

    public String getAliasIteratorTypeVar() {
        return predefTypes.getAliasIteratorTypeVar();
    }

    public void setAliasIteratorTypeVar(String aliasIteratorTypeVar) {
        predefTypes.setAliasIteratorTypeVar(aliasIteratorTypeVar);
    }

    public String getAliasIterableTableVarFirst() {
        return predefTypes.getAliasIterableTableVarFirst();
    }

    public void setAliasIterableTableVarFirst(String aliasIterableTableVarFirst) {
        predefTypes.setAliasIterableTableVarFirst(aliasIterableTableVarFirst);
    }

    public String getAliasIterableTableVarSecond() {
        return predefTypes.getAliasIterableTableVarSecond();
    }

    public void setAliasIterableTableVarSecond(String aliasIterableTableVarSecond) {
        predefTypes.setAliasIterableTableVarSecond(aliasIterableTableVarSecond);
    }

    public String getAliasIteratorTableTypeVarFirst() {
        return predefTypes.getAliasIteratorTableTypeVarFirst();
    }

    public void setAliasIteratorTableTypeVarFirst(String aliasIteratorTableTypeVarFirst) {
        predefTypes.setAliasIteratorTableTypeVarFirst(aliasIteratorTableTypeVarFirst);
    }

    public String getAliasIteratorTableTypeVarSecond() {
        return predefTypes.getAliasIteratorTableTypeVarSecond();
    }

    public void setAliasIteratorTableTypeVarSecond(String aliasIteratorTableTypeVarSecond) {
        predefTypes.setAliasIteratorTableTypeVarSecond(aliasIteratorTableTypeVarSecond);
    }

    public String getAliasPairTypeVarFirst() {
        return predefTypes.getAliasPairTypeVarFirst();
    }

    public void setAliasPairTypeVarFirst(String aliasPairTypeVarFirst) {
        predefTypes.setAliasPairTypeVarFirst(aliasPairTypeVarFirst);
    }

    public String getAliasPairTypeVarSecond() {
        return predefTypes.getAliasPairTypeVarSecond();
    }

    public void setAliasPairTypeVarSecond(String aliasPairTypeVarSecond) {
        predefTypes.setAliasPairTypeVarSecond(aliasPairTypeVarSecond);
    }

    public String getAliasEnumParamVar() {
        return predefTypes.getAliasEnumParamVar();
    }

    public void setAliasEnumParamVar(String aliasEnumParamVar) {
        predefTypes.setAliasEnumParamVar(aliasEnumParamVar);
    }
    public String getAliasInvokeTarget() {
        return reflect.getAliasInvokeTarget();
    }
    public void setAliasInvokeTarget(String _aliasInvokeTarget) {
        reflect.setAliasInvokeTarget(_aliasInvokeTarget);
    }
    public AliasReflection getReflect() {
        return reflect;
    }
    public String getAliasClassNotFoundError() {
        return reflect.getAliasClassNotFoundError();
    }
    public void setAliasClassNotFoundError(String _aliasClassNotFoundError) {
        reflect.setAliasClassNotFoundError(_aliasClassNotFoundError);
    }

    public String getAliasGetVariableOwner() {
        return reflect.getAliasGetVariableOwner();
    }
    public void setAliasGetVariableOwner(String _aliasTypeVariable) {
        reflect.setAliasGetVariableOwner(_aliasTypeVariable);
    }

    public String getAliasGetGenericVariableOwner() {
        return reflect.getAliasGetGenericVariableOwner();
    }
    public void setAliasGetGenericVariableOwner(String _aliasTypeVariable) {
        reflect.setAliasGetGenericVariableOwner(_aliasTypeVariable);
    }

    public String getAliasGetString() {
        return reflect.getAliasGetString();
    }
    public void setAliasGetString(String _aliasTypeVariable) {
        reflect.setAliasGetString(_aliasTypeVariable);
    }

    public String getAliasClassType() {
        return reflect.getAliasClassType();
    }
    public void setAliasClassType(String _aliasClass) {
        reflect.setAliasClassType(_aliasClass);
    }
    public String getAliasFct() {
        return reflect.getAliasFct();
    }
    public void setAliasFct(String _aliasFct) {
        reflect.setAliasFct(_aliasFct);
    }
    public String getAliasCall() {
        return reflect.getAliasCall();
    }
    public void setAliasCall(String _aliasCall) {
        reflect.setAliasCall(_aliasCall);
    }
    public String getAliasAnnotationType() {
        return reflect.getAliasAnnotationType();
    }
    public void setAliasAnnotationType(String _aliasAnnotation) {
        reflect.setAliasAnnotationType(_aliasAnnotation);
    }
    public String getAliasAnnotated() {
        return reflect.getAliasAnnotated();
    }
    public void setAliasAnnotated(String _aliasAnnotated) {
        reflect.setAliasAnnotated(_aliasAnnotated);
    }
    public String getAliasGetAnnotations() {
        return reflect.getAliasGetAnnotations();
    }
    public void setAliasGetAnnotations(String _aliasGetAnnotations) {
        reflect.setAliasGetAnnotations(_aliasGetAnnotations);
    }
    public String getAliasGetDefaultValue() {
        return reflect.getAliasGetDefaultValue();
    }
    public void setAliasGetDefaultValue(String _aliasGetDefaultValue) {
        reflect.setAliasGetDefaultValue(_aliasGetDefaultValue);
    }
    public String getAliasGetAnnotationsParameters() {
        return reflect.getAliasGetAnnotationsParameters();
    }
    public void setAliasGetAnnotationsParameters(String _aliasGetAnnotationsParameters) {
        reflect.setAliasGetAnnotationsParameters(_aliasGetAnnotationsParameters);
    }
    public String getAliasGetDeclaredMethods() {
        return reflect.getAliasGetDeclaredMethods();
    }
    public void setAliasGetDeclaredMethods(String _aliasGetDeclaredMethods) {
        reflect.setAliasGetDeclaredMethods(_aliasGetDeclaredMethods);
    }

    public String getAliasGetDeclaredStaticMethods() {
        return reflect.getAliasGetDeclaredStaticMethods();
    }

    public void setAliasGetDeclaredStaticMethods(String _aliasGetDeclaredStaticMethods) {
        reflect.setAliasGetDeclaredStaticMethods(_aliasGetDeclaredStaticMethods);
    }

    public String getAliasGetDeclaredConstructors() {
        return reflect.getAliasGetDeclaredConstructors();
    }
    public void setAliasGetDeclaredConstructors(String _aliasGetDeclaredConstructors) {
        reflect.setAliasGetDeclaredConstructors(_aliasGetDeclaredConstructors);
    }
    public String getAliasGetDeclaredFields() {
        return reflect.getAliasGetDeclaredFields();
    }
    public void setAliasGetDeclaredFields(String _aliasGetDeclaredFields) {
        reflect.setAliasGetDeclaredFields(_aliasGetDeclaredFields);
    }
    public String getAliasMakeGeneric() {
        return reflect.getAliasMakeGeneric();
    }
    public void setAliasMakeGeneric(String _aliasMakeGeneric) {
        reflect.setAliasMakeGeneric(_aliasMakeGeneric);
    }
    public String getAliasGetAllClasses() {
        return reflect.getAliasGetAllClasses();
    }
    public void setAliasGetAllClasses(String _aliasGetAllClasses) {
        reflect.setAliasGetAllClasses(_aliasGetAllClasses);
    }
    public String getAliasGetOperators() {
        return reflect.getAliasGetOperators();
    }
    public void setAliasGetOperators(String _aliasGetOperators) {
        reflect.setAliasGetOperators(_aliasGetOperators);
    }
    public String getAliasConstructor() {
        return reflect.getAliasConstructor();
    }
    public void setAliasConstructor(String _aliasConstructor) {
        reflect.setAliasConstructor(_aliasConstructor);
    }
    public String getAliasField() {
        return reflect.getAliasField();
    }
    public void setAliasField(String _aliasField) {
        reflect.setAliasField(_aliasField);
    }
    public String getAliasMethod() {
        return reflect.getAliasMethod();
    }
    public void setAliasMethod(String _aliasMethod) {
        reflect.setAliasMethod(_aliasMethod);
    }
    public String getAliasInvoke() {
        return reflect.getAliasInvoke();
    }
    public void setAliasInvoke(String _aliasInvoke) {
        reflect.setAliasInvoke(_aliasInvoke);
    }
    public String getAliasInvokeDirect() {
        return reflect.getAliasInvokeDirect();
    }
    public void setAliasInvokeDirect(String _aliasInvoke) {
        reflect.setAliasInvokeDirect(_aliasInvoke);
    }
    public String getAliasNewInstance() {
        return reflect.getAliasNewInstance();
    }
    public void setAliasNewInstance(String _aliasNewInstance) {
        reflect.setAliasNewInstance(_aliasNewInstance);
    }

    public String getAliasIsAbstract() {
        return reflect.getAliasIsAbstract();
    }
    public void setAliasIsAbstract(String _aliasIsAbstract) {
        reflect.setAliasIsAbstract(_aliasIsAbstract);
    }
    public String getAliasGetFileName() {
        return reflect.getAliasGetFileName();
    }
    public void setAliasGetFileName(String _aliasGetName) {
        reflect.setAliasGetFileName(_aliasGetName);
    }
    public String getAliasGetName() {
        return reflect.getAliasGetName();
    }
    public void setAliasGetName(String _aliasGetName) {
        reflect.setAliasGetName(_aliasGetName);
    }
    public String getAliasGetPrettyName() {
        return reflect.getAliasGetPrettyName();
    }
    public void setAliasGetPrettyName(String _aliasGetName) {
        reflect.setAliasGetPrettyName(_aliasGetName);
    }
    public String getAliasGetField() {
        return reflect.getAliasGetField();
    }
    public void setAliasGetField(String _aliasGetField) {
        reflect.setAliasGetField(_aliasGetField);
    }
    public String getAliasSetField() {
        return reflect.getAliasSetField();
    }
    public void setAliasSetField(String _aliasSetField) {
        reflect.setAliasSetField(_aliasSetField);
    }
    public String getAliasGetClass() {
        return reflect.getAliasGetClass();
    }
    public void setAliasGetClass(String _aliasGetClass) {
        reflect.setAliasGetClass(_aliasGetClass);
    }
    public String getAliasGetEnclosingType() {
        return reflect.getAliasGetEnclosingType();
    }
    public void setAliasGetEnclosingType(String _aliasGetEnclosingType) {
        reflect.setAliasGetEnclosingType(_aliasGetEnclosingType);
    }
    public String getAliasGetDeclaredClasses() {
        return reflect.getAliasGetDeclaredClasses();
    }
    public void setAliasGetDeclaredClasses(String _aliasGetDeclaredClasses) {
        reflect.setAliasGetDeclaredClasses(_aliasGetDeclaredClasses);
    }
    public String getAliasForName() {
        return reflect.getAliasForName();
    }
    public void setAliasForName(String _aliasForName) {
        reflect.setAliasForName(_aliasForName);
    }
    public String getAliasObjectsUtil() {
        return coreNames.getAliasObjectsUtil();
    }
    public void setAliasObjectsUtil(String _aliasObjectsUtil) {
        coreNames.setAliasObjectsUtil(_aliasObjectsUtil);
    }
    public String getAliasStringUtil() {
        return coreNames.getAliasStringUtil();
    }
    public void setAliasStringUtil(String _aliasObjectsUtil) {
        coreNames.setAliasStringUtil(_aliasObjectsUtil);
    }
    public String getAliasSameRef() {
        return coreNames.getAliasSameRef();
    }
    public void setAliasSameRef(String _aliasSameRef) {
        coreNames.setAliasSameRef(_aliasSameRef);
    }
    public String getAliasGetParent() {
        return coreNames.getAliasGetParent();
    }
    public void setAliasGetParent(String _aliasGetParent) {
        coreNames.setAliasGetParent(_aliasGetParent);
    }
    public String getAliasSetParent() {
        return coreNames.getAliasSetParent();
    }
    public void setAliasSetParent(String _aliasGetParent) {
        coreNames.setAliasSetParent(_aliasGetParent);
    }

    public String getAliasGetSuperClass() {
        return reflect.getAliasGetSuperClass();
    }
    public void setAliasGetSuperClass(String _aliasGetSuperClass) {
        reflect.setAliasGetSuperClass(_aliasGetSuperClass);
    }
    public String getAliasGetGenericSuperClass() {
        return reflect.getAliasGetGenericSuperClass();
    }
    public void setAliasGetGenericSuperClass(String _aliasGetGenericSuperClass) {
        reflect.setAliasGetGenericSuperClass(_aliasGetGenericSuperClass);
    }
    public String getAliasGetInterfaces() {
        return reflect.getAliasGetInterfaces();
    }
    public void setAliasGetInterfaces(String _aliasGetInterfaces) {
        reflect.setAliasGetInterfaces(_aliasGetInterfaces);
    }
    public String getAliasGetGenericInterfaces() {
        return reflect.getAliasGetGenericInterfaces();
    }
    public void setAliasGetGenericInterfaces(String _aliasGetGenericInterfaces) {
        reflect.setAliasGetGenericInterfaces(_aliasGetGenericInterfaces);
    }

    public String getAliasGetLowerBounds() {
        return reflect.getAliasGetLowerBounds();
    }
    public void setAliasGetLowerBounds(String _aliasGetLowerBounds) {
        reflect.setAliasGetLowerBounds(_aliasGetLowerBounds);
    }
    public String getAliasGetUpperBounds() {
        return reflect.getAliasGetUpperBounds();
    }
    public void setAliasGetUpperBounds(String _aliasGetUpperBounds) {
        reflect.setAliasGetUpperBounds(_aliasGetUpperBounds);
    }
    public String getAliasGetComponentType() {
        return reflect.getAliasGetComponentType();
    }
    public void setAliasGetComponentType(String _aliasGetComponentType) {
        reflect.setAliasGetComponentType(_aliasGetComponentType);
    }

    public String getAliasMakeArray() {
        return reflect.getAliasMakeArray();
    }
    public void setAliasMakeArray(String _aliasMakeArray) {
        reflect.setAliasMakeArray(_aliasMakeArray);
    }
    public String getAliasGetParameterTypes() {
        return reflect.getAliasGetParameterTypes();
    }
    public void setAliasGetParameterTypes(String _aliasGetParameterTypes) {
        reflect.setAliasGetParameterTypes(_aliasGetParameterTypes);
    }
    public String getAliasGetTypeParameters() {
        return reflect.getAliasGetTypeParameters();
    }
    public void setAliasGetTypeParameters(String _aliasGetTypeParameters) {
        reflect.setAliasGetTypeParameters(_aliasGetTypeParameters);
    }
    public String getAliasGetParameterNames() {
        return reflect.getAliasGetParameterNames();
    }
    public void setAliasGetParameterNames(String _aliasGetNameParameters) {
        reflect.setAliasGetParameterNames(_aliasGetNameParameters);
    }
    public String getAliasGetGenericReturnType() {
        return reflect.getAliasGetGenericReturnType();
    }
    public void setAliasGetGenericReturnType(String _aliasGetGenericReturnType) {
        reflect.setAliasGetGenericReturnType(_aliasGetGenericReturnType);
    }
    public String getAliasGetReturnType() {
        return reflect.getAliasGetReturnType();
    }
    public void setAliasGetReturnType(String _aliasGetReturnType) {
        reflect.setAliasGetReturnType(_aliasGetReturnType);
    }

    public String getAliasGetActualTypeArguments() {
        return reflect.getAliasGetActualTypeArguments();
    }
    public void setAliasGetActualTypeArguments(
            String _aliasGetActualTypeArguments) {
        reflect.setAliasGetActualTypeArguments(_aliasGetActualTypeArguments);
    }

    public void setAliasGetFieldType(String _aliasGetGenericType) {
        reflect.setAliasGetType(_aliasGetGenericType);
    }
    public String getAliasGetGenericType() {
        return reflect.getAliasGetGenericType();
    }
    public void setAliasGetGenericType(String _aliasGetGenericType) {
        reflect.setAliasGetGenericType(_aliasGetGenericType);
    }
    public String getAliasIsFinal() {
        return reflect.getAliasIsFinal();
    }
    public void setAliasIsFinal(String _aliasIsFinal) {
        reflect.setAliasIsFinal(_aliasIsFinal);
    }
    public String getAliasIsTypeVariable() {
        return reflect.getAliasIsTypeVariable();
    }
    public void setAliasIsTypeVariable(String _aliasIsFinal) {
        reflect.setAliasIsTypeVariable(_aliasIsFinal);
    }
    public String getAliasIsVariable() {
        return reflect.getAliasIsVariable();
    }
    public void setAliasIsVariable(String _aliasIsFinal) {
        reflect.setAliasIsVariable(_aliasIsFinal);
    }
    public String getAliasIsStatic() {
        return reflect.getAliasIsStatic();
    }
    public void setAliasIsStatic(String _aliasIsStatic) {
        reflect.setAliasIsStatic(_aliasIsStatic);
    }
    public String getAliasIsStaticCall() {
        return reflect.getAliasIsStaticCall();
    }
    public void setAliasIsStaticCall(String _aliasIsStatic) {
        reflect.setAliasIsStaticCall(_aliasIsStatic);
    }

    public String getAliasIsInstanceMethod() {
        return reflect.getAliasIsInstanceMethod();
    }

    public void setAliasIsInstanceMethod(String _aliasIsInstanceMethod) {
        reflect.setAliasIsInstanceMethod(_aliasIsInstanceMethod);
    }
    public String getAliasIsVarargs() {
        return reflect.getAliasIsVarargs();
    }
    public void setAliasIsVarargs(String _aliasIsVarargs) {
        reflect.setAliasIsVarargs(_aliasIsVarargs);
    }
    public String getAliasIsNormal() {
        return reflect.getAliasIsNormal();
    }
    public void setAliasIsNormal(String _aliasIsNormal) {
        reflect.setAliasIsNormal(_aliasIsNormal);
    }
    public String getAliasIsPublic() {
        return reflect.getAliasIsPublic();
    }
    public void setAliasIsPublic(String _aliasIsPublic) {
        reflect.setAliasIsPublic(_aliasIsPublic);
    }
    public String getAliasIsProtected() {
        return reflect.getAliasIsProtected();
    }
    public void setAliasIsProtected(String _aliasIsProtected) {
        reflect.setAliasIsProtected(_aliasIsProtected);
    }
    public String getAliasIsPackage() {
        return reflect.getAliasIsPackage();
    }
    public void setAliasIsPackage(String _aliasIsPackage) {
        reflect.setAliasIsPackage(_aliasIsPackage);
    }
    public String getAliasIsPrivate() {
        return reflect.getAliasIsPrivate();
    }
    public void setAliasIsPrivate(String _aliasIsPrivate) {
        reflect.setAliasIsPrivate(_aliasIsPrivate);
    }
    public String getAliasIsClass() {
        return reflect.getAliasIsClass();
    }
    public void setAliasIsClass(String _aliasIsClass) {
        reflect.setAliasIsClass(_aliasIsClass);
    }

    public String getAliasIsWildCard() {
        return reflect.getAliasIsWildCard();
    }
    public void setAliasIsWildCard(String _aliasIsWildCard) {
        reflect.setAliasIsWildCard(_aliasIsWildCard);
    }

    public String getAliasIsInterface() {
        return reflect.getAliasIsInterface();
    }
    public void setAliasIsInterface(String _aliasIsInterface) {
        reflect.setAliasIsInterface(_aliasIsInterface);
    }
    public String getAliasIsEnum() {
        return reflect.getAliasIsEnum();
    }
    public void setAliasIsEnum(String _aliasIsEnum) {
        reflect.setAliasIsEnum(_aliasIsEnum);
    }
    public String getAliasIsPrimitive() {
        return reflect.getAliasIsPrimitive();
    }
    public void setAliasIsPrimitive(String _aliasIsPrimitive) {
        reflect.setAliasIsPrimitive(_aliasIsPrimitive);
    }
    public String getAliasIsArray() {
        return reflect.getAliasIsArray();
    }
    public void setAliasIsArray(String _aliasIsArray) {
        reflect.setAliasIsArray(_aliasIsArray);
    }
    public String getAliasIsAnnotation() {
        return reflect.getAliasIsAnnotation();
    }
    public void setAliasIsAnnotation(String _aliasIsAnnotation) {
        reflect.setAliasIsAnnotation(_aliasIsAnnotation);
    }
    public String getAliasMakeWildCard() {
        return reflect.getAliasMakeWildCard();
    }
    public void setAliasMakeWildCard(String _aliasMakeWildCard) {
        reflect.setAliasMakeWildCard(_aliasMakeWildCard);
    }
    public String getAliasIsInstance() {
        return reflect.getAliasIsInstance();
    }
    public void setAliasIsInstance(String _aliasIsInstance) {
        reflect.setAliasIsInstance(_aliasIsInstance);
    }
    public String getAliasIsAssignableFrom() {
        return reflect.getAliasIsAssignableFrom();
    }
    public void setAliasIsAssignableFrom(String _aliasIsAssignableFrom) {
        reflect.setAliasIsAssignableFrom(_aliasIsAssignableFrom);
    }
    public String getAliasInit() {
        return reflect.getAliasInit();
    }
    public void setAliasInit(String _aliasInit) {
        reflect.setAliasInit(_aliasInit);
    }
    public String getAliasDefaultInstance() {
        return reflect.getAliasDefaultInstance();
    }
    public void setAliasDefaultInstance(String _aliasDefaultInstance) {
        reflect.setAliasDefaultInstance(_aliasDefaultInstance);
    }
    public String getAliasEnumValueOf() {
        return reflect.getAliasEnumValueOf();
    }
    public void setAliasEnumValueOf(String _aliasEnumValueOf) {
        reflect.setAliasEnumValueOf(_aliasEnumValueOf);
    }
    public String getAliasGetEnumConstants() {
        return reflect.getAliasGetEnumConstants();
    }
    public void setAliasGetEnumConstants(String _aliasGetEnumConstants) {
        reflect.setAliasGetEnumConstants(_aliasGetEnumConstants);
    }
    public String getAliasGetGenericBounds() {
        return reflect.getAliasGetGenericBounds();
    }
    public void setAliasGetGenericBounds(String _aliasGetGenericBounds) {
        reflect.setAliasGetGenericBounds(_aliasGetGenericBounds);
    }
    public String getAliasGetBounds() {
        return reflect.getAliasGetBounds();
    }
    public void setAliasGetBounds(String _aliasGetBounds) {
        reflect.setAliasGetBounds(_aliasGetBounds);
    }
    public String getAliasArrayNewInstance() {
        return reflect.getAliasArrayNewInstance();
    }
    public void setAliasArrayNewInstance(String _aliasArrayNewInstance) {
        reflect.setAliasArrayNewInstance(_aliasArrayNewInstance);
    }
    public String getAliasArrayGet() {
        return reflect.getAliasArrayGet();
    }
    public void setAliasArrayGet(String _aliasArrayGet) {
        reflect.setAliasArrayGet(_aliasArrayGet);
    }
    public String getAliasArraySet() {
        return reflect.getAliasArraySet();
    }
    public void setAliasArraySet(String _aliasArraySet) {
        reflect.setAliasArraySet(_aliasArraySet);
    }
    public String getAliasArrayGetLength() {
        return reflect.getAliasArrayGetLength();
    }
    public void setAliasArrayGetLength(String _aliasArrayGetLength) {
        reflect.setAliasArrayGetLength(_aliasArrayGetLength);
    }
    public String getAliasGetDeclaringClass() {
        return reflect.getAliasGetDeclaringClass();
    }
    public void setAliasGetDeclaringClass(String _aliasGetDeclaringClass) {
        reflect.setAliasGetDeclaringClass(_aliasGetDeclaringClass);
    }
    public AliasMath getMathRef() {
        return mathRef;
    }

    public String getAliasBinQuot() {
        return mathRef.getAliasBinQuot();
    }
    public void setAliasBinQuot(String _aliasBinQuot) {
        mathRef.setAliasBinQuot(_aliasBinQuot);
    }
    public String getAliasBinMod() {
        return mathRef.getAliasBinMod();
    }
    public void setAliasBinMod(String _aliasBinMod) {
        mathRef.setAliasBinMod(_aliasBinMod);
    }
    public String getAliasPlus() {
        return mathRef.getAliasPlus();
    }
    public void setAliasPlus(String _aliasPlus) {
        mathRef.setAliasPlus(_aliasPlus);
    }
    public String getAliasMinus() {
        return mathRef.getAliasMinus();
    }
    public void setAliasMinus(String _aliasMinus) {
        mathRef.setAliasMinus(_aliasMinus);
    }
    public String getAliasMult() {
        return mathRef.getAliasMult();
    }
    public void setAliasMult(String _aliasMult) {
        mathRef.setAliasMult(_aliasMult);
    }
    public String getAliasAnd() {
        return mathRef.getAliasAnd();
    }
    public void setAliasAnd(String _aliasAnd) {
        mathRef.setAliasAnd(_aliasAnd);
    }
    public String getAliasOr() {
        return mathRef.getAliasOr();
    }
    public void setAliasOr(String _aliasOr) {
        mathRef.setAliasOr(_aliasOr);
    }
    public String getAliasXor() {
        return mathRef.getAliasXor();
    }
    public void setAliasXor(String _aliasXor) {
        mathRef.setAliasXor(_aliasXor);
    }
    public String getAliasNegBin() {
        return mathRef.getAliasNegBin();
    }
    public void setAliasNegBin(String _aliasNegBin) {
        mathRef.setAliasNegBin(_aliasNegBin);
    }
    
    public String getAliasNeg() {
        return mathRef.getAliasNeg();
    }
    public void setAliasNeg(String _aliasNeg) {
        mathRef.setAliasNeg(_aliasNeg);
    }
    public String getAliasLt() {
        return mathRef.getAliasLt();
    }
    public void setAliasLt(String _aliasLt) {
        mathRef.setAliasLt(_aliasLt);
    }
    public String getAliasGt() {
        return mathRef.getAliasGt();
    }
    public void setAliasGt(String _aliasGt) {
        mathRef.setAliasGt(_aliasGt);
    }
    public String getAliasLe() {
        return mathRef.getAliasLe();
    }
    public void setAliasLe(String _aliasLe) {
        mathRef.setAliasLe(_aliasLe);
    }
    public String getAliasGe() {
        return mathRef.getAliasGe();
    }
    public void setAliasGe(String _aliasGe) {
        mathRef.setAliasGe(_aliasGe);
    }
    public String getAliasShiftLeft() {
        return mathRef.getAliasShiftLeft();
    }
    public void setAliasShiftLeft(String _aliasShiftLeft) {
        mathRef.setAliasShiftLeft(_aliasShiftLeft);
    }
    public String getAliasShiftRight() {
        return mathRef.getAliasShiftRight();
    }
    public void setAliasShiftRight(String _aliasShiftRight) {
        mathRef.setAliasShiftRight(_aliasShiftRight);
    }
    public String getAliasBitShiftLeft() {
        return mathRef.getAliasBitShiftLeft();
    }
    public void setAliasBitShiftLeft(String _aliasBitShiftLeft) {
        mathRef.setAliasBitShiftLeft(_aliasBitShiftLeft);
    }
    public String getAliasBitShiftRight() {
        return mathRef.getAliasBitShiftRight();
    }
    public void setAliasBitShiftRight(String _aliasBitShiftRight) {
        mathRef.setAliasBitShiftRight(_aliasBitShiftRight);
    }
    public String getAliasRotateLeft() {
        return mathRef.getAliasRotateLeft();
    }
    public void setAliasRotateLeft(String _aliasRotateLeft) {
        mathRef.setAliasRotateLeft(_aliasRotateLeft);
    }
    public String getAliasRotateRight() {
        return mathRef.getAliasRotateRight();
    }
    public void setAliasRotateRight(String _aliasRotateRight) {
        mathRef.setAliasRotateRight(_aliasRotateRight);
    }
    public String getAliasRandom() {
        return mathRef.getAliasRandom();
    }
    public void setAliasRandom(String _aliasRotateRight) {
        mathRef.setAliasRandom(_aliasRotateRight);
    }
    public String getAliasStackTraceElement() {
        return stackElt.getAliasStackTraceElement();
    }
    public void setAliasStackTraceElement(String _aliasStackTraceElement) {
        stackElt.setAliasStackTraceElement(_aliasStackTraceElement);
    }
    public String getAliasCurrentStack() {
        return stackElt.getAliasCurrentStack();
    }
    public void setAliasCurrentStack(String _aliasCurrentStack) {
        stackElt.setAliasCurrentStack(_aliasCurrentStack);
    }
    public String getAliasCurrentFullStack() {
        return stackElt.getAliasCurrentFullStack();
    }
    public void setAliasCurrentFullStack(String _aliasCurrentStack) {
        stackElt.setAliasCurrentFullStack(_aliasCurrentStack);
    }

    public String getAliasEnumName() {
        return predefTypes.getAliasEnumName();
    }

    public void setAliasEnumName(String _aliasEnumName) {
        predefTypes.setAliasEnumName(_aliasEnumName);
    }

    public String getAliasEnumOrdinal() {
        return predefTypes.getAliasEnumOrdinal();
    }

    public void setAliasEnumOrdinal(String _aliasEnumOrdinal) {
        predefTypes.setAliasEnumOrdinal(_aliasEnumOrdinal);
    }

    public AliasStackTraceElement getStackElt() {
        return stackElt;
    }
    public StringList getPredefinedClasses() {
        return predefinedClasses;
    }

    public StringList getPredefinedInterfacesInitOrder() {
        return predefinedInterfacesInitOrder;
    }

    public String getTrueString() {
        return trueString;
    }
    public void setTrueString(String _trueString) {
        trueString = _trueString;
    }
    public String getFalseString() {
        return falseString;
    }
    public void setFalseString(String _falseString) {
        falseString = _falseString;
    }
    public String getNullString() {
        return nullString;
    }
    public void setNullString(String _nullString) {
        nullString = _nullString;
    }
    public String getDefaultPkg() {
        return defaultPkg;
    }
    public void setDefaultPkg(String _defaultPkg) {
        defaultPkg = _defaultPkg;
    }

}
