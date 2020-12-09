package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.common.StringExpUtil;
import code.util.CustList;
import code.util.StringMap;

public final class LgNamesContent {

    private static final String DEFAULT_PKG = "DefaultPkg";
    private static final String FIELD_MAX_VALUE = "MAX_VALUE";
    private static final String FIELD_MIN_VALUE = "MIN_VALUE";
    private static final String FIELD_PLUS_INFINITY = "PLUS_INFINITY";
    private static final String FIELD_MINUS_INFINITY = "MINUS_INFINITY";
    private static final String FIELD_NAN = "NAN";
    private static final String BAD_ENCODE = "BadEncode";
    private static final String DIVISION_ZERO = "DivisionZero";
    private static final String CHAR_SEQUENCE = "CharSequence";
    private static final String ITERATOR_TYPE = "IteratorType";
    private static final String ENUM_PARAM = "EnumParam";
    private static final String GET_MESSAGE = "GetMessage";
    private static final String ITERATOR_TABLE_TYPE_VAR_FIRST = "IteratorTableTypeVarFirst";
    private static final String ITERATOR_TABLE_TYPE_VAR_SECOND = "IteratorTableTypeVarSecond";
    private static final String EQUALS = "Equals";
    private static final String LONG = "Long";
    private static final String SHORT = "Short";
    private static final String PRIM_CHAR = "PrimChar";
    private static final String NUMBER = "Number";
    private static final String PARSE_INT = "ParseInt";
    private static final String COMPARE = "Compare";
    private static final String INT_VALUE = "IntValue";
    private static final String BOOLEAN = "Boolean";
    private static final String PRIM_LONG = "PrimLong";
    private static final String BYTE = "Byte";
    private static final String FLOAT = "Float";
    private static final String DOUBLE = "Double";
    private static final String INTEGER = "Integer";
    private static final String DIGIT = "Digit";
    private static final String IS_DIGIT = "IsDigit";
    private static final String MATH = "Math";
    private static final String BAD_SIZE = "BadSize";
    private static final String NULL_PE = "NullPe";
    private static final String OBJECT = "Object";
    private static final String ITERATOR = "Iterator";
    private static final String CAST_TYPE = "CastType";
    private static final String STORE = "Store";
    private static final String ENUM_TYPE = "EnumType";
    private static final String PRIM_BYTE = "PrimByte";
    private static final String ERROR = "Error";
    private static final String ERROR_CURRENT_STACK = "ErrorCurrentStack";
    private static final String ERROR_TO_STRING = "ErrorToString";
    private static final String VOID = "Void";
    private static final String GET_CAUSE = "GetCause";
    private static final String BAD_INDEX = "BadIndex";
    private static final String BAD_ARG_NUMBER = "BadArgNumber";
    private static final String ABSTRACT_TYPE_ERR = "AbstractTypeErr";
    private static final String ILLEGAL_TYPE = "IllegalType";
    private static final String NON_INVOKABLE = "NonInvokable";
    private static final String ENUMS = "Enums";
    private static final String ITERABLE = "Iterable";
    private static final String NB_FORMAT = "NbFormat";
    private static final String SOF = "Sof";
    private static final String PARSE_FLOAT = "ParseFloat";
    private static final String TO_STRING_METHOD = "ToStringMethod";
    private static final String SIGNUM = "Signum";
    private static final String BIN = "Bin";
    private static final String OCT = "Oct";
    private static final String HEX = "Hex";
    private static final String PARSE_LONG_OR_NULL = "ParseLongOrNull";
    private static final String PARSE_SHORT_OR_NULL = "ParseShortOrNull";
    private static final String PARSE_FLOAT_OR_NULL = "ParseFloatOrNull";
    private static final String PARSE_DOUBLE_OR_NULL = "ParseDoubleOrNull";
    private static final String BYTE_VALUE = "ByteValue";
    private static final String CHAR_VALUE = "CharValue";
    private static final String PRIM_BOOLEAN = "PrimBoolean";
    private static final String PARSE_INT_OR_NULL = "ParseIntOrNull";
    private static final String PRIM_SHORT = "PrimShort";
    private static final String PARSE_BOOLEAN = "ParseBoolean";
    private static final String PARSE_SHORT = "ParseShort";
    private static final String PRIM_FLOAT = "PrimFloat";
    private static final String COMPARE_TO = "CompareTo";
    private static final String CHARACTER = "Character";
    private static final String PARSE_LONG = "ParseLong";
    private static final String VALUE_OF_METHOD = "ValueOfMethod";
    private static final String PRIM_INTEGER = "PrimInteger";
    private static final String PARSE_BYTE_OR_NULL = "ParseByteOrNull";
    private static final String PRIM_DOUBLE = "PrimDouble";
    private static final String BOOLEAN_VALUE = "BooleanValue";
    private static final String SHORT_VALUE = "ShortValue";
    private static final String PARSE_DOUBLE = "ParseDouble";
    private static final String ILLEGAL_ARG = "IllegalArg";
    private static final String PARSE_BYTE = "ParseByte";
    private static final String IS_UPPER_CASE = "IsUpperCase";
    private static final String IS_WORD_CHAR = "IsWordChar";
    private static final String IS_WHITESPACE = "IsWhitespace";
    private static final String IS_LETTER_OR_DIGIT = "IsLetterOrDigit";
    private static final String FLOAT_VALUE = "FloatValue";
    private static final String DOUBLE_VALUE = "DoubleValue";
    private static final String LONG_VALUE = "LongValue";
    private static final String IS_LOWER_CASE = "IsLowerCase";
    private static final String INDEX_OF = "IndexOf";
    private static final String STRING = "String";
    private static final String STRING_COMPARE = "StringCompare";
    private static final String STRING_VALUE_OF = "StringValueOf";
    private static final String IS_EMPTY = "IsEmpty";
    private static final String TRIM = "Trim";
    private static final String GET_BYTES = "GetBytes";
    private static final String FOR_DIGIT = "ForDigit";
    private static final String IS_SPACE = "IsSpace";
    private static final String GET_TYPE = "GetType";
    private static final String CONTAINS = "Contains";
    private static final String REPLACE = "Replace";
    private static final String REPLACE_STRING = "ReplaceString";
    private static final String FORMAT = "Format";
    private static final String ENDS_WITH = "EndsWith";
    private static final String CAPACITY = "Capacity";
    private static final String SPLIT = "Split";
    private static final String APPEND = "Append";
    private static final String IS_LETTER = "IsLetter";
    private static final String IS_NAN = "IsNan";
    private static final String LENGTH = "Length";
    private static final String ARRAY_LENGTH = "ArrayLength";
    private static final String CHAR_AT = "CharAt";
    private static final String CLONE = "Clone";
    private static final String NAME = "Name";
    private static final String CALL = "Call";
    private static final String META_INFO = "MetaInfo";
    private static final String INSTANCE = "Instance";
    private static final String SAME = "Same";
    private static final String MOD = "Mod";
    private static final String REVERSE = "Reverse";
    private static final String INSERT = "Insert";
    private static final String ABS = "Abs";
    private static final String MAX = "Max";
    private static final String MIN = "Min";
    private static final String HAS_NEXT = "HasNext";
    private static final String PAIR_TYPE = "PairType";
    private static final String QUOT = "Quot";
    private static final String NEXT = "Next";
    private static final String ORDINAL = "Ordinal";
    private static final String GET_FIRST = "GetFirst";
    private static final String FCT = "Fct";
    private static final String DELETE = "Delete";
    private static final String CLEAR = "Clear";
    private static final String NEXT_PAIR = "NextPair";
    private static final String SUBSTRING = "Substring";
    private static final String SET_CHAR_AT = "SetCharAt";
    private static final String EQUALS_IGNORE_CASE = "EqualsIgnoreCase";
    private static final String ITERATOR_TABLE_TYPE = "IteratorTableType";
    private static final String DELETE_CHAR_AT = "DeleteCharAt";
    private static final String STARTS_WITH = "StartsWith";
    private static final String LAST_INDEX_OF = "LastIndexOf";
    private static final String REGION_MATCHES = "RegionMatches";
    private static final String ITERATOR_TABLE = "IteratorTable";
    private static final String ITERABLE_TABLE = "IterableTable";
    private static final String TO_LOWER_CASE = "ToLowerCase";
    private static final String TO_LOWER_CASE_CHAR = "ToLowerCaseChar";
    private static final String STRING_BUILDER = "StringBuilder";
    private static final String TO_UPPER_CASE = "ToUpperCase";
    private static final String TO_UPPER_CASE_CHAR = "ToUpperCaseChar";
    private static final String ENSURE_CAPACITY = "EnsureCapacity";
    private static final String SET_LENGTH = "SetLength";
    private static final String TRIM_TO_SIZE = "TrimToSize";
    private static final String HAS_NEXT_PAIR = "HasNextPair";
    private static final String REPLACEMENT = "Replacement";
    private static final String GET_OLD_STRING = "GetOldString";
    private static final String GET_NEW_STRING = "GetNewString";
    private static final String GET_SECOND = "GetSecond";
    private static final String SUB_SEQUENCE = "SubSequence";
    private static final String COMPARE_TO_IGNORE_CASE = "CompareToIgnoreCase";
    private static final String TO_CHAR_ARRAY = "ToCharArray";
    private static final String CHAR_SEQUENCE_TO_STRING = "CharSequenceToString";
    private static final String CHAR_SEQUENCE_EQUALS = "CharSequenceEquals";
    private static final String CHAR_SEQUENCE_COMPARE_TO = "CharSequenceCompareTo";
    private static final String REPLACE_MULTIPLE = "ReplaceMultiple";
    private static final String SPLIT_STRINGS = "SplitStrings";
    private static final String SPLIT_CHARS = "SplitChars";
    private static final String IS_INFINITE = "IsInfinite";
    private static final String GET_DIRECTIONALITY = "GetDirectionality";
    private static final String GET_CHAR_TYPE = "GetCharType";
    private static final String ITERABLE_TABLE_VAR_SECOND = "IterableTableVarSecond";
    private static final String GET_STRING = "GetString";
    private static final String GET_ANNOTATIONS_PARAMETERS = "GetAnnotationsParameters";
    private static final String READ_RESOURCES_NAMES = "ReadResourcesNames";
    private static final String READ_RESOURCES_NAMES_LENGTH = "ReadResourcesNamesLength";
    private static final String INVOKE_TARGET = "InvokeTarget";
    private static final String GET_ANNOTATIONS = "GetAnnotations";
    private static final String GET_VARIABLE_OWNER = "GetVariableOwner";
    private static final String READ_RESOURCES = "ReadResources";
    private static final String READ_RESOURCES_INDEX = "ReadResourcesIndex";
    private static final String RESOURCES = "Resources";
    private static final String CLASS_NOT_FOUND_ERROR = "ClassNotFoundError";
    private static final String ENUM_VALUES = "EnumValues";
    private static final String ENUM_PRED_VALUE_OF = "EnumPredValueOf";
    private static final String ITERATOR_TYPE_VAR = "IteratorTypeVar";
    private static final String CLASS_TYPE = "ClassType";
    private static final String ITERABLE_TABLE_VAR_FIRST = "IterableTableVarFirst";
    private static final String PAIR_TYPE_VAR_FIRST = "PairTypeVarFirst";
    private static final String ERROR_INIT_CLASS = "ErrorInitClass";
    private static final String ANNOTATION_TYPE = "AnnotationType";
    private static final String GET_GENERIC_VARIABLE_OWNER = "GetGenericVariableOwner";
    private static final String ENUM_PARAM_VAR = "EnumParamVar";
    private static final String SEED_GENERATOR = "SeedGenerator";
    private static final String SEED_DOUBLE_GENERATOR = "SeedDoubleGenerator";
    private static final String SEED_GET = "SeedGet";
    private static final String PAIR_TYPE_VAR_SECOND = "PairTypeVarSecond";
    private static final String ANNOTATED = "Annotated";
    private static final String ITERABLE_VAR = "IterableVar";
    private static final String GET_DEFAULT_VALUE = "GetDefaultValue";
    private static final String MAKE_GENERIC = "MakeGeneric";
    private static final String GET_ALL_CLASSES = "GetAllClasses";
    private static final String GET_OPERATORS = "GetOperators";
    private static final String GET_DECLARED_EXPLICITS = "GetDeclaredExplicits";
    private static final String GET_DECLARED_IMPLICITS = "GetDeclaredImplicits";
    private static final String GET_DECLARED_TRUE_OPERATORS = "GetDeclaredTrueOperators";
    private static final String GET_DECLARED_FALSE_OPERATORS = "GetDeclaredFalseOperators";
    private static final String GET_DECLARED_METHODS = "GetDeclaredMethods";
    private static final String GET_DECLARED_STATIC_METHODS = "GetDeclaredStaticMethods";
    private static final String GET_DECLARED_CONSTRUCTORS = "GetDeclaredConstructors";
    private static final String GET_DECLARED_FIELDS = "GetDeclaredFields";
    private static final String GET_DECLARED_ANONYMOUS_TYPES = "GetDeclaredAnonymousTypes";
    private static final String GET_DECLARED_ANONYMOUS_LAMBDA = "GetDeclaredAnonymousLambda";
    private static final String GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS = "GetDeclaredAnonymousLambdaLocalVars";
    private static final String GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS = "GetDeclaredAnonymousLambdaLoopVars";
    private static final String GET_DECLARED_LOCAL_TYPES = "GetDeclaredLocalTypes";
    private static final String GET_DECLARED_BLOCKS = "GetDeclaredBlocks";
    private static final String FIELD = "Field";
    private static final String IS_NORMAL = "IsNormal";
    private static final String SAME_REF = "SameRef";
    private static final String IS_PUBLIC = "IsPublic";
    private static final String IS_ARRAY = "IsArray";
    private static final String ARRAY_GET = "ArrayGet";
    private static final String METHOD = "Method";
    private static final String GET_FIELD = "GetField";
    private static final String INVOKE = "Invoke";
    private static final String IS_ENUM = "IsEnum";
    private static final String INIT = "Init";
    private static final String FOR_NAME = "ForName";
    private static final String IS_STATIC = "IsStatic";
    private static final String IS_STATIC_CALL = "IsStaticCall";
    private static final String IS_INSTANCE_METHOD = "IsInstanceMethod";
    private static final String GET_NAME = "GetName";
    private static final String IS_CLASS = "IsClass";
    private static final String IS_SPE_CLASS = "IsSpeClass";
    private static final String SET_FIELD = "SetField";
    private static final String GET_CLASS = "GetClass";
    private static final String IS_FINAL = "IsFinal";
    private static final String ARRAY_SET = "ArraySet";
    private static final String XOR = "Xor";
    private static final String MULT = "Mult";
    private static final String RANDOM = "Random";
    private static final String SEED = "Seed";
    private static final String NEG_BIN = "NegBin";
    private static final String MINUS = "Minus";
    private static final String ENUM_NAME = "EnumName";
    private static final String BIN_MOD = "BinMod";
    private static final String LT = "Lt";
    private static final String GT = "Gt";
    private static final String LE = "Le";
    private static final String GE = "Ge";
    private static final String AND = "And";
    private static final String OR = "Or";
    private static final String PLUS = "Plus";
    private static final String BIN_QUOT = "BinQuot";
    private static final String NEG = "Neg";
    private static final String ROTATE_LEFT = "RotateLeft";
    private static final String ENUM_ORDINAL = "EnumOrdinal";
    private static final String SHIFT_RIGHT = "ShiftRight";
    private static final String CURRENT_FULL_STACK = "CurrentFullStack";
    private static final String STACK_TRACE_ELEMENT_TO_STRING = "StackTraceElementToString";
    private static final String GET_BOUNDS = "GetBounds";
    private static final String GET_DECLARING_CLASS = "GetDeclaringClass";
    private static final String STACK_TRACE_ELEMENT = "StackTraceElement";
    private static final String ENUM_VALUE_OF = "EnumValueOf";
    private static final String ARRAY_NEW_INSTANCE = "ArrayNewInstance";
    private static final String GET_ENUM_CONSTANTS = "GetEnumConstants";
    private static final String ARRAY_GET_LENGTH = "ArrayGetLength";
    private static final String ROTATE_RIGHT = "RotateRight";
    private static final String GET_GENERIC_BOUNDS = "GetGenericBounds";
    private static final String BIT_SHIFT_LEFT = "BitShiftLeft";
    private static final String SHIFT_LEFT = "ShiftLeft";
    private static final String DEFAULT_INSTANCE = "DefaultInstance";
    private static final String CURRENT_STACK = "CurrentStack";
    private static final String BIT_SHIFT_RIGHT = "BitShiftRight";
    private static final String GET_PARAMETER_NAMES = "GetParameterNames";
    private static final String GET_PRETTY_NAME = "GetPrettyName";
    private static final String GET_PRETTY_SINGLE_NAME = "GetPrettySingleName";
    private static final String GET_UPPER_BOUNDS = "GetUpperBounds";
    private static final String GET_PARAMETER_TYPES = "GetParameterTypes";
    private static final String GET_GENERIC_RETURN_TYPE = "GetGenericReturnType";
    private static final String INVOKE_DIRECT = "InvokeDirect";
    private static final String STRING_UTIL = "StringUtil";
    private static final String STRING_UTIL_VALUE_OF = "StringUtilValueOf";
    private static final String GET_LOWER_BOUNDS = "GetLowerBounds";
    private static final String GET_TYPE_PARAMETERS = "GetTypeParameters";
    private static final String CONSTRUCTOR = "Constructor";
    private static final String SET_PARENT = "SetParent";
    private static final String NEW_INSTANCE = "NewInstance";
    private static final String GET_ENCLOSING_TYPE = "GetEnclosingType";
    private static final String GET_INTERFACES = "GetInterfaces";
    private static final String OBJECTS_UTIL = "ObjectsUtil";
    private static final String GET_DECLARED_CLASSES = "GetDeclaredClasses";
    private static final String GET_SUPER_CLASS = "GetSuperClass";
    private static final String GET_PARENT = "GetParent";
    private static final String GET_COMPONENT_TYPE = "GetComponentType";
    private static final String GET_FILE_NAME = "GetFileName";
    private static final String GET_GENERIC_SUPER_CLASS = "GetGenericSuperClass";
    private static final String GET_GENERIC_INTERFACES = "GetGenericInterfaces";
    private static final String IS_ABSTRACT = "IsAbstract";
    private static final String MAKE_ARRAY = "MakeArray";
    private static final String IS_INTERFACE = "IsInterface";
    private static final String MAKE_REF_TYPE = "MakeRefType";
    private static final String MAKE_WILD_CARD = "MakeWildCard";
    private static final String IS_TYPE_VARIABLE = "IsTypeVariable";
    private static final String IS_PRIVATE = "IsPrivate";
    private static final String IS_VARARGS = "IsVarargs";
    private static final String IS_INSTANCE = "IsInstance";
    private static final String GET_RETURN_TYPE = "GetReturnType";
    private static final String GET_ACTUAL_TYPE_ARGUMENTS = "GetActualTypeArguments";
    private static final String IS_PROTECTED = "IsProtected";
    private static final String IS_PRIMITIVE = "IsPrimitive";
    private static final String IS_REF_TYPE = "IsRefType";
    private static final String IS_WILD_CARD = "IsWildCard";
    private static final String IS_ANNOTATION = "IsAnnotation";
    private static final String GET_GENERIC_TYPE = "GetGenericType";
    private static final String IS_ASSIGNABLE_FROM = "IsAssignableFrom";
    private static final String IS_VARIABLE = "IsVariable";
    private static final String IS_PACKAGE = "IsPackage";
    private static final String FALSE_STRING = "FalseString";
    private static final String TRUE_STRING = "TrueString";
    private static final String NULL_STRING = "NullString";
    private static final String NULL_COVER_STRING = "NullCoverString";
    private static final String NOT_NULL_COVER_STRING = "NotNullCoverString";
    private static final String STATIC_STRING = "StaticString";
    private static final String STATIC_CALL_STRING = "StaticCallString";
    private static final String INFINITY = "Infinity";
    private static final String EXPONENT = "Exponent";
    private static final String NAN = "Nan";

    private StringMap<StandardType> standards = new StringMap<StandardType>();

    private AliasCore coreNames = new AliasCore();

    private AliasCharSequence charSeq = new AliasCharSequence();
    private AliasReflection reflect = new AliasReflection();
    private AliasStackTraceElement stackElt = new AliasStackTraceElement();
    private AliasNumber nbAlias = new AliasNumber();
    private AliasMath mathRef = new AliasMath();
    private PrimitiveTypes primTypes = new PrimitiveTypes();
    private AliasPredefinedTypes predefTypes = new AliasPredefinedTypes();
    private DisplayedStrings displayedStrings = new DisplayedStrings();
    private String defaultPkg = "";
    public void build(StringMap<String> _util, StringMap<String> _cust) {
        setDefaultPkg(get(_util,_cust, DEFAULT_PKG));
        getNbAlias().setAliasMaxValueField(get(_util,_cust, FIELD_MAX_VALUE));
        getNbAlias().setAliasMinValueField(get(_util,_cust, FIELD_MIN_VALUE));
        getNbAlias().setAliasPlusInfinityField(get(_util,_cust, FIELD_PLUS_INFINITY));
        getNbAlias().setAliasMinusInfinityField(get(_util,_cust, FIELD_MINUS_INFINITY));
        getNbAlias().setAliasNanField(get(_util,_cust, FIELD_NAN));
        getCoreNames().setAliasBadEncode(get(_util,_cust, BAD_ENCODE));
        getCoreNames().setAliasDivisionZero(get(_util,_cust, DIVISION_ZERO));
        getCharSeq().setAliasCharSequence(get(_util,_cust, CHAR_SEQUENCE));
        getPredefTypes().setAliasIteratorType(get(_util,_cust, ITERATOR_TYPE));
        getPredefTypes().setAliasEnumParam(get(_util,_cust, ENUM_PARAM));
        getCoreNames().setAliasGetMessage(get(_util,_cust, GET_MESSAGE));
        getPredefTypes().setAliasIteratorTableTypeVarFirst(get(_util,_cust, ITERATOR_TABLE_TYPE_VAR_FIRST));
        getPredefTypes().setAliasIteratorTableTypeVarSecond(get(_util,_cust, ITERATOR_TABLE_TYPE_VAR_SECOND));
        getNbAlias().setAliasEquals(get(_util,_cust, EQUALS));
        getNbAlias().setAliasLong(get(_util,_cust, LONG));
        getNbAlias().setAliasShort(get(_util,_cust, SHORT));
        getPrimTypes().setAliasPrimChar(get(_util,_cust, PRIM_CHAR));
        getNbAlias().setAliasNumber(get(_util,_cust, NUMBER));
        getNbAlias().setAliasParseInt(get(_util,_cust, PARSE_INT));
        getNbAlias().setAliasCompare(get(_util,_cust, COMPARE));
        getNbAlias().setAliasIntValue(get(_util,_cust, INT_VALUE));
        getNbAlias().setAliasBoolean(get(_util,_cust, BOOLEAN));
        getPrimTypes().setAliasPrimLong(get(_util,_cust, PRIM_LONG));
        getNbAlias().setAliasByte(get(_util,_cust, BYTE));
        getNbAlias().setAliasFloat(get(_util,_cust, FLOAT));
        getNbAlias().setAliasDouble(get(_util,_cust, DOUBLE));
        getNbAlias().setAliasInteger(get(_util,_cust, INTEGER));
        getNbAlias().setAliasDigit(get(_util,_cust, DIGIT));
        getNbAlias().setAliasIsDigit(get(_util,_cust, IS_DIGIT));
        getMathRef().setAliasMath(get(_util,_cust, MATH));
        getCoreNames().setAliasBadSize(get(_util,_cust, BAD_SIZE));
        getCoreNames().setAliasNullPe(get(_util,_cust, NULL_PE));
        getCoreNames().setAliasObject(get(_util,_cust, OBJECT));
        getPredefTypes().setAliasIterator(get(_util,_cust, ITERATOR));
        getCoreNames().setAliasCastType(get(_util,_cust, CAST_TYPE));
        getCoreNames().setAliasStore(get(_util,_cust, STORE));
        getPredefTypes().setAliasEnumType(get(_util,_cust, ENUM_TYPE));
        getPrimTypes().setAliasPrimByte(get(_util,_cust, PRIM_BYTE));
        getCoreNames().setAliasError(get(_util,_cust, ERROR));
        getCoreNames().setAliasErrorCurrentStack(get(_util,_cust, ERROR_CURRENT_STACK));
        getCoreNames().setAliasErrorToString(get(_util,_cust, ERROR_TO_STRING));
        getCoreNames().setAliasVoid(get(_util,_cust, VOID));
        getCoreNames().setAliasGetCause(get(_util,_cust, GET_CAUSE));
        getCoreNames().setAliasBadIndex(get(_util,_cust, BAD_INDEX));
        getCoreNames().setAliasBadArgNumber(get(_util,_cust, BAD_ARG_NUMBER));
        getCoreNames().setAliasIllegalType(get(_util,_cust, ILLEGAL_TYPE));
        getCoreNames().setAliasAbstractTypeErr(get(_util,_cust, ABSTRACT_TYPE_ERR));
        getCoreNames().setAliasNonInvokable(get(_util,_cust, NON_INVOKABLE));
        getCoreNames().setAliasEnums(get(_util,_cust, ENUMS));
        getPredefTypes().setAliasIterable(get(_util,_cust, ITERABLE));
        getCoreNames().setAliasNbFormat(get(_util,_cust, NB_FORMAT));
        getCoreNames().setAliasSof(get(_util,_cust, SOF));
        getNbAlias().setAliasParseFloat(get(_util,_cust, PARSE_FLOAT));
        getNbAlias().setAliasToStringMethod(get(_util,_cust, TO_STRING_METHOD));
        getNbAlias().setAliasSignum(get(_util,_cust, SIGNUM));
        getNbAlias().setAliasToBinString(get(_util,_cust, BIN));
        getNbAlias().setAliasToOctString(get(_util,_cust, OCT));
        getNbAlias().setAliasToHexString(get(_util,_cust, HEX));
        getNbAlias().setAliasParseLongOrNull(get(_util,_cust, PARSE_LONG_OR_NULL));
        getNbAlias().setAliasParseShortOrNull(get(_util,_cust, PARSE_SHORT_OR_NULL));
        getNbAlias().setAliasParseFloatOrNull(get(_util,_cust, PARSE_FLOAT_OR_NULL));
        getNbAlias().setAliasParseDoubleOrNull(get(_util,_cust, PARSE_DOUBLE_OR_NULL));
        getNbAlias().setAliasByteValue(get(_util,_cust, BYTE_VALUE));
        getNbAlias().setAliasCharValue(get(_util,_cust, CHAR_VALUE));
        getPrimTypes().setAliasPrimBoolean(get(_util,_cust, PRIM_BOOLEAN));
        getNbAlias().setAliasParseIntOrNull(get(_util,_cust, PARSE_INT_OR_NULL));
        getPrimTypes().setAliasPrimShort(get(_util,_cust, PRIM_SHORT));
        getNbAlias().setAliasParseBoolean(get(_util,_cust, PARSE_BOOLEAN));
        getNbAlias().setAliasParseShort(get(_util,_cust, PARSE_SHORT));
        getPrimTypes().setAliasPrimFloat(get(_util,_cust, PRIM_FLOAT));
        getNbAlias().setAliasCompareTo(get(_util,_cust, COMPARE_TO));
        getNbAlias().setAliasCharacter(get(_util,_cust, CHARACTER));
        getNbAlias().setAliasParseLong(get(_util,_cust, PARSE_LONG));
        getNbAlias().setAliasValueOfMethod(get(_util,_cust, VALUE_OF_METHOD));
        getPrimTypes().setAliasPrimInteger(get(_util,_cust, PRIM_INTEGER));
        getNbAlias().setAliasParseByteOrNull(get(_util,_cust, PARSE_BYTE_OR_NULL));
        getPrimTypes().setAliasPrimDouble(get(_util,_cust, PRIM_DOUBLE));
        getNbAlias().setAliasBooleanValue(get(_util,_cust, BOOLEAN_VALUE));
        getNbAlias().setAliasShortValue(get(_util,_cust, SHORT_VALUE));
        getNbAlias().setAliasParseDouble(get(_util,_cust, PARSE_DOUBLE));
        getCoreNames().setAliasIllegalArg(get(_util,_cust, ILLEGAL_ARG));
        getNbAlias().setAliasParseByte(get(_util,_cust, PARSE_BYTE));
        getNbAlias().setAliasIsUpperCase(get(_util,_cust, IS_UPPER_CASE));
        getNbAlias().setAliasIsWordChar(get(_util,_cust, IS_WORD_CHAR));
        getNbAlias().setAliasIsWhitespace(get(_util,_cust, IS_WHITESPACE));
        getNbAlias().setAliasIsLetterOrDigit(get(_util,_cust, IS_LETTER_OR_DIGIT));
        getNbAlias().setAliasFloatValue(get(_util,_cust, FLOAT_VALUE));
        getNbAlias().setAliasDoubleValue(get(_util,_cust, DOUBLE_VALUE));
        getNbAlias().setAliasLongValue(get(_util,_cust, LONG_VALUE));
        getNbAlias().setAliasIsLowerCase(get(_util,_cust, IS_LOWER_CASE));
        getCharSeq().setAliasIndexOf(get(_util,_cust, INDEX_OF));
        getCharSeq().setAliasString(get(_util,_cust, STRING));
        getCharSeq().setAliasStringValueOf(get(_util,_cust, STRING_VALUE_OF));
        getCharSeq().setAliasStringCompare(get(_util,_cust, STRING_COMPARE));
        getCharSeq().setAliasIsEmpty(get(_util,_cust, IS_EMPTY));
        getCharSeq().setAliasTrim(get(_util,_cust, TRIM));
        getCharSeq().setAliasGetBytes(get(_util,_cust, GET_BYTES));
        getNbAlias().setAliasForDigit(get(_util,_cust, FOR_DIGIT));
        getNbAlias().setAliasIsSpace(get(_util,_cust, IS_SPACE));
        getReflect().setAliasGetType(get(_util,_cust, GET_TYPE));
        getCharSeq().setAliasContains(get(_util,_cust, CONTAINS));
        getCharSeq().setAliasReplace(get(_util,_cust, REPLACE));
        getCharSeq().setAliasReplaceString(get(_util,_cust, REPLACE_STRING));
        getCharSeq().setAliasFormat(get(_util,_cust, FORMAT));
        getCharSeq().setAliasEndsWith(get(_util,_cust, ENDS_WITH));
        getCharSeq().setAliasCapacity(get(_util,_cust, CAPACITY));
        getCharSeq().setAliasSplit(get(_util,_cust, SPLIT));
        getCharSeq().setAliasAppend(get(_util,_cust, APPEND));
        getNbAlias().setAliasIsLetter(get(_util,_cust, IS_LETTER));
        getNbAlias().setAliasIsNan(get(_util,_cust, IS_NAN));
        getCharSeq().setAliasLength(get(_util,_cust, LENGTH));
        getCharSeq().setAliasCharAt(get(_util,_cust, CHAR_AT));
        getCoreNames().setAliasClone(get(_util,_cust, CLONE));
        getCoreNames().setAliasName(get(_util,_cust, NAME));
        getReflect().setAliasCall(get(_util,_cust, CALL));
        getReflect().setAliasMetaInfo(get(_util,_cust, META_INFO));
        getReflect().setAliasInstance(get(_util,_cust, INSTANCE));
        getCharSeq().setAliasSame(get(_util,_cust, SAME));
        getMathRef().setAliasMod(get(_util,_cust, MOD));
        getCharSeq().setAliasReverse(get(_util,_cust, REVERSE));
        getCharSeq().setAliasInsert(get(_util,_cust, INSERT));
        getMathRef().setAliasAbs(get(_util,_cust, ABS));
        getMathRef().setAliasMax(get(_util,_cust, MAX));
        getMathRef().setAliasMin(get(_util,_cust, MIN));
        getPredefTypes().setAliasHasNext(get(_util,_cust, HAS_NEXT));
        getPredefTypes().setAliasPairType(get(_util,_cust, PAIR_TYPE));
        getMathRef().setAliasQuot(get(_util,_cust, QUOT));
        getPredefTypes().setAliasNext(get(_util,_cust, NEXT));
        getCoreNames().setAliasOrdinal(get(_util,_cust, ORDINAL));
        getPredefTypes().setAliasGetFirst(get(_util,_cust, GET_FIRST));
        getReflect().setAliasFct(get(_util,_cust, FCT));
        getCharSeq().setAliasDelete(get(_util,_cust, DELETE));
        getCharSeq().setAliasClear(get(_util,_cust, CLEAR));
        getPredefTypes().setAliasNextPair(get(_util,_cust, NEXT_PAIR));
        getCharSeq().setAliasSubstring(get(_util,_cust, SUBSTRING));
        getCharSeq().setAliasSetCharAt(get(_util,_cust, SET_CHAR_AT));
        getCharSeq().setAliasEqualsIgnoreCase(get(_util,_cust, EQUALS_IGNORE_CASE));
        getPredefTypes().setAliasIteratorTableType(get(_util,_cust, ITERATOR_TABLE_TYPE));
        getCharSeq().setAliasDeleteCharAt(get(_util,_cust, DELETE_CHAR_AT));
        getCharSeq().setAliasStartsWith(get(_util,_cust, STARTS_WITH));
        getCharSeq().setAliasLastIndexOf(get(_util,_cust, LAST_INDEX_OF));
        getCharSeq().setAliasRegionMatches(get(_util,_cust, REGION_MATCHES));
        getPredefTypes().setAliasIteratorTable(get(_util,_cust, ITERATOR_TABLE));
        getPredefTypes().setAliasIterableTable(get(_util,_cust, ITERABLE_TABLE));
        getCharSeq().setAliasToLowerCase(get(_util,_cust, TO_LOWER_CASE));
        getNbAlias().setAliasToLowerCaseChar(get(_util,_cust, TO_LOWER_CASE_CHAR));
        getCharSeq().setAliasStringBuilder(get(_util,_cust, STRING_BUILDER));
        getCharSeq().setAliasToUpperCase(get(_util,_cust, TO_UPPER_CASE));
        getNbAlias().setAliasToUpperCaseChar(get(_util,_cust, TO_UPPER_CASE_CHAR));
        getCharSeq().setAliasEnsureCapacity(get(_util,_cust, ENSURE_CAPACITY));
        getCharSeq().setAliasSetLength(get(_util,_cust, SET_LENGTH));
        getCharSeq().setAliasTrimToSize(get(_util,_cust, TRIM_TO_SIZE));
        getPredefTypes().setAliasHasNextPair(get(_util,_cust, HAS_NEXT_PAIR));
        getCharSeq().setAliasReplacement(get(_util,_cust, REPLACEMENT));
        getCharSeq().setAliasGetOldString(get(_util,_cust, GET_OLD_STRING));
        getCharSeq().setAliasGetNewString(get(_util,_cust, GET_NEW_STRING));
        getPredefTypes().setAliasGetSecond(get(_util,_cust, GET_SECOND));
        getCharSeq().setAliasSubSequence(get(_util,_cust, SUB_SEQUENCE));
        getCharSeq().setAliasCompareToIgnoreCase(get(_util,_cust, COMPARE_TO_IGNORE_CASE));
        getCharSeq().setAliasToCharArray(get(_util,_cust, TO_CHAR_ARRAY));
        getCharSeq().setAliasCharSequenceToString(get(_util,_cust, CHAR_SEQUENCE_TO_STRING));
        getCharSeq().setAliasCharSequenceEquals(get(_util,_cust, CHAR_SEQUENCE_EQUALS));
        getCharSeq().setAliasCharSequenceCompareTo(get(_util,_cust, CHAR_SEQUENCE_COMPARE_TO));
        getCharSeq().setAliasReplaceMultiple(get(_util,_cust, REPLACE_MULTIPLE));
        getCharSeq().setAliasSplitStrings(get(_util,_cust, SPLIT_STRINGS));
        getCharSeq().setAliasSplitChars(get(_util,_cust, SPLIT_CHARS));
        getNbAlias().setAliasIsInfinite(get(_util,_cust, IS_INFINITE));
        getNbAlias().setAliasGetDirectionality(get(_util,_cust, GET_DIRECTIONALITY));
        getNbAlias().setAliasGetCharType(get(_util,_cust, GET_CHAR_TYPE));
        getPredefTypes().setAliasIterableTableVarSecond(get(_util,_cust, ITERABLE_TABLE_VAR_SECOND));
        getReflect().setAliasGetString(get(_util,_cust, GET_STRING));
        getReflect().setAliasGetAnnotationsParameters(get(_util,_cust, GET_ANNOTATIONS_PARAMETERS));
        getCoreNames().setAliasReadResourcesNames(get(_util,_cust, READ_RESOURCES_NAMES));
        getCoreNames().setAliasReadResourcesNamesLength(get(_util,_cust, READ_RESOURCES_NAMES_LENGTH));
        getReflect().setAliasInvokeTarget(get(_util,_cust, INVOKE_TARGET));
        getReflect().setAliasGetAnnotations(get(_util,_cust, GET_ANNOTATIONS));
        getReflect().setAliasGetVariableOwner(get(_util,_cust, GET_VARIABLE_OWNER));
        getCoreNames().setAliasReadResources(get(_util,_cust, READ_RESOURCES));
        getCoreNames().setAliasReadResourcesIndex(get(_util,_cust, READ_RESOURCES_INDEX));
        getCoreNames().setAliasResources(get(_util,_cust, RESOURCES));
        getCoreNames().setAliasArrayLength(get(_util,_cust,ARRAY_LENGTH));
        getReflect().setAliasClassNotFoundError(get(_util,_cust, CLASS_NOT_FOUND_ERROR));
        getPredefTypes().setAliasEnumValues(get(_util,_cust, ENUM_VALUES));
        getPredefTypes().setAliasEnumPredValueOf(get(_util,_cust, ENUM_PRED_VALUE_OF));
        getPredefTypes().setAliasIteratorTypeVar(get(_util,_cust, ITERATOR_TYPE_VAR));
        getReflect().setAliasClassType(get(_util,_cust, CLASS_TYPE));
        getPredefTypes().setAliasIterableTableVarFirst(get(_util,_cust, ITERABLE_TABLE_VAR_FIRST));
        getPredefTypes().setAliasPairTypeVarFirst(get(_util,_cust, PAIR_TYPE_VAR_FIRST));
        getCoreNames().setAliasErrorInitClass(get(_util,_cust, ERROR_INIT_CLASS));
        getReflect().setAliasAnnotationType(get(_util,_cust, ANNOTATION_TYPE));
        getReflect().setAliasGetGenericVariableOwner(get(_util,_cust, GET_GENERIC_VARIABLE_OWNER));
        getPredefTypes().setAliasEnumParamVar(get(_util,_cust, ENUM_PARAM_VAR));
        getPredefTypes().setAliasSeedGenerator(get(_util,_cust, SEED_GENERATOR));
        getPredefTypes().setAliasSeedDoubleGenerator(get(_util,_cust, SEED_DOUBLE_GENERATOR));
        getPredefTypes().setAliasSeedGet(get(_util,_cust, SEED_GET));
        getPredefTypes().setAliasPairTypeVarSecond(get(_util,_cust, PAIR_TYPE_VAR_SECOND));
        getReflect().setAliasAnnotated(get(_util,_cust, ANNOTATED));
        getPredefTypes().setAliasIterableVar(get(_util,_cust, ITERABLE_VAR));
        getReflect().setAliasGetDefaultValue(get(_util,_cust, GET_DEFAULT_VALUE));
        getReflect().setAliasMakeGeneric(get(_util,_cust, MAKE_GENERIC));
        getReflect().setAliasGetAllClasses(get(_util,_cust, GET_ALL_CLASSES));
        getReflect().setAliasGetOperators(get(_util,_cust, GET_OPERATORS));
        getReflect().setAliasGetDeclaredExplicits(get(_util,_cust, GET_DECLARED_EXPLICITS));
        getReflect().setAliasGetDeclaredImplicits(get(_util,_cust, GET_DECLARED_IMPLICITS));
        getReflect().setAliasGetDeclaredTrueOperators(get(_util,_cust, GET_DECLARED_TRUE_OPERATORS));
        getReflect().setAliasGetDeclaredFalseOperators(get(_util,_cust, GET_DECLARED_FALSE_OPERATORS));
        getReflect().setAliasGetDeclaredMethods(get(_util,_cust, GET_DECLARED_METHODS));
        getReflect().setAliasGetDeclaredStaticMethods(get(_util,_cust, GET_DECLARED_STATIC_METHODS));
        getReflect().setAliasGetDeclaredConstructors(get(_util,_cust, GET_DECLARED_CONSTRUCTORS));
        getReflect().setAliasGetDeclaredFields(get(_util,_cust, GET_DECLARED_FIELDS));
        getReflect().setAliasGetDeclaredAnonymousTypes(get(_util,_cust, GET_DECLARED_ANONYMOUS_TYPES));
        getReflect().setAliasGetDeclaredAnonymousLambda(get(_util,_cust, GET_DECLARED_ANONYMOUS_LAMBDA));
        getReflect().setAliasGetDeclaredAnonymousLambdaLocalVars(get(_util,_cust, GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS));
        getReflect().setAliasGetDeclaredAnonymousLambdaLoopVars(get(_util,_cust, GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS));
        getReflect().setAliasGetDeclaredBlocks(get(_util,_cust, GET_DECLARED_BLOCKS));
        getReflect().setAliasGetDeclaredLocalTypes(get(_util,_cust, GET_DECLARED_LOCAL_TYPES));
        getReflect().setAliasField(get(_util,_cust, FIELD));
        getReflect().setAliasIsNormal(get(_util,_cust, IS_NORMAL));
        getCoreNames().setAliasSameRef(get(_util,_cust, SAME_REF));
        getReflect().setAliasIsPublic(get(_util,_cust, IS_PUBLIC));
        getReflect().setAliasIsArray(get(_util,_cust, IS_ARRAY));
        getReflect().setAliasArrayGet(get(_util,_cust, ARRAY_GET));
        getReflect().setAliasMethod(get(_util,_cust, METHOD));
        getReflect().setAliasGetField(get(_util,_cust, GET_FIELD));
        getReflect().setAliasInvoke(get(_util,_cust, INVOKE));
        getReflect().setAliasIsEnum(get(_util,_cust, IS_ENUM));
        getReflect().setAliasInit(get(_util,_cust, INIT));
        getReflect().setAliasForName(get(_util,_cust, FOR_NAME));
        getReflect().setAliasIsStatic(get(_util,_cust, IS_STATIC));
        getReflect().setAliasIsStaticCall(get(_util,_cust, IS_STATIC_CALL));
        getReflect().setAliasIsInstanceMethod(get(_util,_cust, IS_INSTANCE_METHOD));
        getReflect().setAliasGetName(get(_util,_cust, GET_NAME));
        getReflect().setAliasIsClass(get(_util,_cust, IS_CLASS));
        getReflect().setAliasIsSpecialClass(get(_util,_cust, IS_SPE_CLASS));
        getReflect().setAliasSetField(get(_util,_cust, SET_FIELD));
        getReflect().setAliasGetClass(get(_util,_cust, GET_CLASS));
        getReflect().setAliasIsFinal(get(_util,_cust, IS_FINAL));
        getReflect().setAliasArraySet(get(_util,_cust, ARRAY_SET));
        getMathRef().setAliasXor(get(_util,_cust, XOR));
        getMathRef().setAliasMult(get(_util,_cust, MULT));
        getMathRef().setAliasRandom(get(_util,_cust, RANDOM));
        getMathRef().setAliasSeed(get(_util,_cust, SEED));
        getMathRef().setAliasNegBin(get(_util,_cust, NEG_BIN));
        getMathRef().setAliasMinus(get(_util,_cust, MINUS));
        getPredefTypes().setAliasEnumName(get(_util,_cust, ENUM_NAME));
        getMathRef().setAliasBinMod(get(_util,_cust, BIN_MOD));
        getMathRef().setAliasLt(get(_util,_cust, LT));
        getMathRef().setAliasGt(get(_util,_cust, GT));
        getMathRef().setAliasLe(get(_util,_cust, LE));
        getMathRef().setAliasGe(get(_util,_cust, GE));
        getMathRef().setAliasAnd(get(_util,_cust, AND));
        getMathRef().setAliasOr(get(_util,_cust, OR));
        getMathRef().setAliasPlus(get(_util,_cust, PLUS));
        getMathRef().setAliasBinQuot(get(_util,_cust, BIN_QUOT));
        getMathRef().setAliasNeg(get(_util,_cust, NEG));
        getMathRef().setAliasRotateLeft(get(_util,_cust, ROTATE_LEFT));
        getPredefTypes().setAliasEnumOrdinal(get(_util,_cust, ENUM_ORDINAL));
        getMathRef().setAliasShiftRight(get(_util,_cust, SHIFT_RIGHT));
        getStackElt().setAliasCurrentFullStack(get(_util,_cust, CURRENT_FULL_STACK));
        getStackElt().setAliasStackTraceElementToString(get(_util,_cust, STACK_TRACE_ELEMENT_TO_STRING));
        getReflect().setAliasGetBounds(get(_util,_cust, GET_BOUNDS));
        getReflect().setAliasGetDeclaringClass(get(_util,_cust, GET_DECLARING_CLASS));
        getStackElt().setAliasStackTraceElement(get(_util,_cust, STACK_TRACE_ELEMENT));
        getReflect().setAliasEnumValueOf(get(_util,_cust, ENUM_VALUE_OF));
        getReflect().setAliasArrayNewInstance(get(_util,_cust, ARRAY_NEW_INSTANCE));
        getReflect().setAliasGetEnumConstants(get(_util,_cust, GET_ENUM_CONSTANTS));
        getReflect().setAliasArrayGetLength(get(_util,_cust, ARRAY_GET_LENGTH));
        getMathRef().setAliasRotateRight(get(_util,_cust, ROTATE_RIGHT));
        getReflect().setAliasGetGenericBounds(get(_util,_cust, GET_GENERIC_BOUNDS));
        getMathRef().setAliasBitShiftLeft(get(_util,_cust, BIT_SHIFT_LEFT));
        getMathRef().setAliasShiftLeft(get(_util,_cust, SHIFT_LEFT));
        getReflect().setAliasDefaultInstance(get(_util,_cust, DEFAULT_INSTANCE));
        getStackElt().setAliasCurrentStack(get(_util,_cust, CURRENT_STACK));
        getMathRef().setAliasBitShiftRight(get(_util,_cust, BIT_SHIFT_RIGHT));
        getReflect().setAliasGetParameterNames(get(_util,_cust, GET_PARAMETER_NAMES));
        getReflect().setAliasGetPrettyName(get(_util,_cust, GET_PRETTY_NAME));
        getReflect().setAliasGetPrettySingleName(get(_util,_cust, GET_PRETTY_SINGLE_NAME));
        getReflect().setAliasGetUpperBounds(get(_util,_cust, GET_UPPER_BOUNDS));
        getReflect().setAliasGetParameterTypes(get(_util,_cust, GET_PARAMETER_TYPES));
        getReflect().setAliasGetGenericReturnType(get(_util,_cust, GET_GENERIC_RETURN_TYPE));
        getReflect().setAliasInvokeDirect(get(_util,_cust, INVOKE_DIRECT));
        getCoreNames().setAliasStringUtil(get(_util,_cust, STRING_UTIL));
        getCoreNames().setAliasStringUtilValueOf(get(_util,_cust, STRING_UTIL_VALUE_OF));
        getReflect().setAliasGetLowerBounds(get(_util,_cust, GET_LOWER_BOUNDS));
        getReflect().setAliasGetTypeParameters(get(_util,_cust, GET_TYPE_PARAMETERS));
        getReflect().setAliasConstructor(get(_util,_cust, CONSTRUCTOR));
        getCoreNames().setAliasSetParent(get(_util,_cust, SET_PARENT));
        getReflect().setAliasNewInstance(get(_util,_cust, NEW_INSTANCE));
        getReflect().setAliasGetEnclosingType(get(_util,_cust, GET_ENCLOSING_TYPE));
        getReflect().setAliasGetInterfaces(get(_util,_cust, GET_INTERFACES));
        getCoreNames().setAliasObjectsUtil(get(_util,_cust, OBJECTS_UTIL));
        getReflect().setAliasGetDeclaredClasses(get(_util,_cust, GET_DECLARED_CLASSES));
        getReflect().setAliasGetSuperClass(get(_util,_cust, GET_SUPER_CLASS));
        getCoreNames().setAliasGetParent(get(_util,_cust, GET_PARENT));
        getReflect().setAliasGetComponentType(get(_util,_cust, GET_COMPONENT_TYPE));
        getReflect().setAliasGetFileName(get(_util,_cust, GET_FILE_NAME));
        getReflect().setAliasGetGenericSuperClass(get(_util,_cust, GET_GENERIC_SUPER_CLASS));
        getReflect().setAliasGetGenericInterfaces(get(_util,_cust, GET_GENERIC_INTERFACES));
        getReflect().setAliasIsAbstract(get(_util,_cust, IS_ABSTRACT));
        getReflect().setAliasMakeArray(get(_util,_cust, MAKE_ARRAY));
        getReflect().setAliasIsInterface(get(_util,_cust, IS_INTERFACE));
        getReflect().setAliasMakeRef(get(_util,_cust, MAKE_REF_TYPE));
        getReflect().setAliasMakeWildCard(get(_util,_cust, MAKE_WILD_CARD));
        getReflect().setAliasIsTypeVariable(get(_util,_cust, IS_TYPE_VARIABLE));
        getReflect().setAliasIsPrivate(get(_util,_cust, IS_PRIVATE));
        getReflect().setAliasIsVarargs(get(_util,_cust, IS_VARARGS));
        getReflect().setAliasIsInstance(get(_util,_cust, IS_INSTANCE));
        getReflect().setAliasGetReturnType(get(_util,_cust, GET_RETURN_TYPE));
        getReflect().setAliasGetActualTypeArguments(get(_util,_cust, GET_ACTUAL_TYPE_ARGUMENTS));
        getReflect().setAliasIsProtected(get(_util,_cust, IS_PROTECTED));
        getReflect().setAliasIsPrimitive(get(_util,_cust, IS_PRIMITIVE));
        getReflect().setAliasIsRefType(get(_util,_cust, IS_REF_TYPE));
        getReflect().setAliasIsWildCard(get(_util,_cust, IS_WILD_CARD));
        getReflect().setAliasIsAnnotation(get(_util,_cust, IS_ANNOTATION));
        getReflect().setAliasGetGenericType(get(_util,_cust, GET_GENERIC_TYPE));
        getReflect().setAliasIsAssignableFrom(get(_util,_cust, IS_ASSIGNABLE_FROM));
        getReflect().setAliasIsVariable(get(_util,_cust, IS_VARIABLE));
        getReflect().setAliasIsPackage(get(_util,_cust, IS_PACKAGE));
        getDisplayedStrings().setFalseString(get(_util,_cust, FALSE_STRING));
        getDisplayedStrings().setTrueString(get(_util,_cust, TRUE_STRING));
        getDisplayedStrings().setNullString(get(_util,_cust, NULL_STRING));
        getDisplayedStrings().setNullCoverString(get(_util,_cust, NULL_COVER_STRING));
        getDisplayedStrings().setNotNullCoverString(get(_util,_cust, NOT_NULL_COVER_STRING));
        getDisplayedStrings().setStaticCallString(get(_util,_cust, STATIC_CALL_STRING));
        getDisplayedStrings().setStaticString(get(_util,_cust, STATIC_STRING));
        getDisplayedStrings().setInfinity(get(_util,_cust, INFINITY));
        getDisplayedStrings().setNan(get(_util,_cust, NAN));
        getDisplayedStrings().setExponent(get(_util,_cust, EXPONENT));
        getPredefTypes().getParams().setAliasSeedGenerator0Get0(get(_util,_cust,AliasParamPredefinedTypes.SEED_GENERATOR_0_GET_0));
        getCharSeq().getParams().setAliasCharSequence0SubSequence0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SUB_SEQUENCE_0));
        getCharSeq().getParams().setAliasCharSequence0SubSequence1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SUB_SEQUENCE_1));
        getCharSeq().getParams().setAliasCharSequence0CharAt0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_CHAR_AT_0));
        getCharSeq().getParams().setAliasCharSequence0Substring0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SUBSTRING_0));
        getCharSeq().getParams().setAliasCharSequence0Substring1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SUBSTRING_1));
        getCharSeq().getParams().setAliasCharSequence1Substring0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_SUBSTRING_0));
        getCharSeq().getParams().setAliasCharSequence0CompareTo0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_COMPARE_TO_0));
        getCharSeq().getParams().setAliasCharSequence0Contains0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_CONTAINS_0));
        getCharSeq().getParams().setAliasCharSequence0StartsWith0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_STARTS_WITH_0));
        getCharSeq().getParams().setAliasCharSequence1StartsWith0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_STARTS_WITH_0));
        getCharSeq().getParams().setAliasCharSequence1StartsWith1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_STARTS_WITH_1));
        getCharSeq().getParams().setAliasCharSequence0EndsWith0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_ENDS_WITH_0));
        getCharSeq().getParams().setAliasCharSequence0IndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_INDEX_OF_0));
        getCharSeq().getParams().setAliasCharSequence1IndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_INDEX_OF_0));
        getCharSeq().getParams().setAliasCharSequence1IndexOf1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_INDEX_OF_1));
        getCharSeq().getParams().setAliasCharSequence2IndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_2_INDEX_OF_0));
        getCharSeq().getParams().setAliasCharSequence3IndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_3_INDEX_OF_0));
        getCharSeq().getParams().setAliasCharSequence3IndexOf1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_3_INDEX_OF_1));
        getCharSeq().getParams().setAliasCharSequence0LastIndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_LAST_INDEX_OF_0));
        getCharSeq().getParams().setAliasCharSequence1LastIndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_LAST_INDEX_OF_0));
        getCharSeq().getParams().setAliasCharSequence1LastIndexOf1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_LAST_INDEX_OF_1));
        getCharSeq().getParams().setAliasCharSequence2LastIndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_2_LAST_INDEX_OF_0));
        getCharSeq().getParams().setAliasCharSequence3LastIndexOf0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_3_LAST_INDEX_OF_0));
        getCharSeq().getParams().setAliasCharSequence3LastIndexOf1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_3_LAST_INDEX_OF_1));
        getCharSeq().getParams().setAliasCharSequence0Format0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_FORMAT_0));
        getCharSeq().getParams().setAliasCharSequence0Split0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SPLIT_0));
        getCharSeq().getParams().setAliasCharSequence1Split0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_0));
        getCharSeq().getParams().setAliasCharSequence1Split1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_1));
        getCharSeq().getParams().setAliasCharSequence2Split0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_2_SPLIT_0));
        getCharSeq().getParams().setAliasCharSequence3Split0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_3_SPLIT_0));
        getCharSeq().getParams().setAliasCharSequence3Split1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_3_SPLIT_1));
        getCharSeq().getParams().setAliasCharSequence0SplitStrings0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SPLIT_STRINGS_0));
        getCharSeq().getParams().setAliasCharSequence1SplitStrings0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_STRINGS_0));
        getCharSeq().getParams().setAliasCharSequence1SplitStrings1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_STRINGS_1));
        getCharSeq().getParams().setAliasCharSequence0SplitChars0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_SPLIT_CHARS_0));
        getCharSeq().getParams().setAliasCharSequence0RegionMatches0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_0));
        getCharSeq().getParams().setAliasCharSequence0RegionMatches1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_1));
        getCharSeq().getParams().setAliasCharSequence0RegionMatches2(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_2));
        getCharSeq().getParams().setAliasCharSequence0RegionMatches3(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_3));
        getCharSeq().getParams().setAliasCharSequence0Equals0(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_EQUALS_0));
        getCharSeq().getParams().setAliasCharSequence0Equals1(get(_util,_cust, AliasParamCharSequence.CHAR_SEQUENCE_0_EQUALS_1));
        getCharSeq().getParams().setAliasString0EqualsIgnoreCase0(get(_util,_cust, AliasParamCharSequence.STRING_0_EQUALS_IGNORE_CASE_0));
        getCharSeq().getParams().setAliasString0Compare0(get(_util,_cust, AliasParamCharSequence.STRING_0_COMPARE_0));
        getCharSeq().getParams().setAliasString0Compare1(get(_util,_cust, AliasParamCharSequence.STRING_0_COMPARE_1));
        getCharSeq().getParams().setAliasString0CompareToIgnoreCase0(get(_util,_cust, AliasParamCharSequence.STRING_0_COMPARE_TO_IGNORE_CASE_0));
        getCharSeq().getParams().setAliasString0ReplaceString0(get(_util,_cust, AliasParamCharSequence.STRING_0_REPLACE_STRING_0));
        getCharSeq().getParams().setAliasString0ReplaceString1(get(_util,_cust, AliasParamCharSequence.STRING_0_REPLACE_STRING_1));
        getCharSeq().getParams().setAliasString1ReplaceString0(get(_util,_cust, AliasParamCharSequence.STRING_1_REPLACE_STRING_0));
        getCharSeq().getParams().setAliasString1ReplaceString1(get(_util,_cust, AliasParamCharSequence.STRING_1_REPLACE_STRING_1));
        getCharSeq().getParams().setAliasString0ReplaceMultiple0(get(_util,_cust, AliasParamCharSequence.STRING_0_REPLACE_MULTIPLE_0));
        getCharSeq().getParams().setAliasString0RegionMatches0(get(_util,_cust, AliasParamCharSequence.STRING_0_REGION_MATCHES_0));
        getCharSeq().getParams().setAliasString0RegionMatches1(get(_util,_cust, AliasParamCharSequence.STRING_0_REGION_MATCHES_1));
        getCharSeq().getParams().setAliasString0RegionMatches2(get(_util,_cust, AliasParamCharSequence.STRING_0_REGION_MATCHES_2));
        getCharSeq().getParams().setAliasString0RegionMatches3(get(_util,_cust, AliasParamCharSequence.STRING_0_REGION_MATCHES_3));
        getCharSeq().getParams().setAliasString0RegionMatches4(get(_util,_cust, AliasParamCharSequence.STRING_0_REGION_MATCHES_4));
        getCharSeq().getParams().setAliasString0ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_0_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString1ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_1_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString2ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_2_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString3ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_3_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString4ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_4_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString5ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_5_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString6ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_6_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString7ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_7_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString8ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_8_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString9ValueOfMethod0(get(_util,_cust, AliasParamCharSequence.STRING_9_VALUE_OF_METHOD_0));
        getCharSeq().getParams().setAliasString9ValueOfMethod1(get(_util,_cust, AliasParamCharSequence.STRING_9_VALUE_OF_METHOD_1));
        getCharSeq().getParams().setAliasString9ValueOfMethod2(get(_util,_cust, AliasParamCharSequence.STRING_9_VALUE_OF_METHOD_2));
        getCharSeq().getParams().setAliasString0String0(get(_util,_cust, AliasParamCharSequence.STRING_0_STRING_0));
        getCharSeq().getParams().setAliasString1String0(get(_util,_cust, AliasParamCharSequence.STRING_1_STRING_0));
        getCharSeq().getParams().setAliasString1String1(get(_util,_cust, AliasParamCharSequence.STRING_1_STRING_1));
        getCharSeq().getParams().setAliasString1String2(get(_util,_cust, AliasParamCharSequence.STRING_1_STRING_2));
        getCharSeq().getParams().setAliasString2String0(get(_util,_cust, AliasParamCharSequence.STRING_2_STRING_0));
        getCharSeq().getParams().setAliasString3String0(get(_util,_cust, AliasParamCharSequence.STRING_3_STRING_0));
        getCharSeq().getParams().setAliasString3String1(get(_util,_cust, AliasParamCharSequence.STRING_3_STRING_1));
        getCharSeq().getParams().setAliasString3String2(get(_util,_cust, AliasParamCharSequence.STRING_3_STRING_2));
        getCharSeq().getParams().setAliasString4String0(get(_util,_cust, AliasParamCharSequence.STRING_4_STRING_0));
        getCharSeq().getParams().setAliasStringBuilder0Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder1Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_1_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder2Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_2_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder3Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_3_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder4Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_4_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder5Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_5_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder6Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_6_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder7Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_7_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder8Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_8_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder9Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder9Append1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_APPEND_1));
        getCharSeq().getParams().setAliasStringBuilder9Append2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_APPEND_2));
        getCharSeq().getParams().setAliasStringBuilder10Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_10_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder11Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder11Append1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_APPEND_1));
        getCharSeq().getParams().setAliasStringBuilder11Append2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_APPEND_2));
        getCharSeq().getParams().setAliasStringBuilder12Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_12_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder13Append0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_APPEND_0));
        getCharSeq().getParams().setAliasStringBuilder13Append1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_APPEND_1));
        getCharSeq().getParams().setAliasStringBuilder13Append2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_APPEND_2));
        getCharSeq().getParams().setAliasStringBuilder0Delete0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_DELETE_0));
        getCharSeq().getParams().setAliasStringBuilder0Delete1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_DELETE_1));
        getCharSeq().getParams().setAliasStringBuilder0DeleteCharAt0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_DELETE_CHAR_AT_0));
        getCharSeq().getParams().setAliasStringBuilder0Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder0Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder1Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_1_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder1Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_1_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder2Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_2_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder2Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_2_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder3Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_3_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder3Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_3_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder4Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_4_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder4Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_4_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder5Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_5_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder5Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_5_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder6Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_6_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder6Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_6_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder7Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_7_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder7Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_7_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder8Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_8_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder8Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_8_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder9Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder9Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder9Insert2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_INSERT_2));
        getCharSeq().getParams().setAliasStringBuilder9Insert3(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_9_INSERT_3));
        getCharSeq().getParams().setAliasStringBuilder10Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_10_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder10Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_10_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder11Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder11Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder11Insert2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_INSERT_2));
        getCharSeq().getParams().setAliasStringBuilder11Insert3(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_11_INSERT_3));
        getCharSeq().getParams().setAliasStringBuilder12Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_12_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder12Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_12_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder13Insert0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_INSERT_0));
        getCharSeq().getParams().setAliasStringBuilder13Insert1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_INSERT_1));
        getCharSeq().getParams().setAliasStringBuilder13Insert2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_INSERT_2));
        getCharSeq().getParams().setAliasStringBuilder13Insert3(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_13_INSERT_3));
        getCharSeq().getParams().setAliasStringBuilder0Replace0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_REPLACE_0));
        getCharSeq().getParams().setAliasStringBuilder0Replace1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_REPLACE_1));
        getCharSeq().getParams().setAliasStringBuilder0Replace2(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_REPLACE_2));
        getCharSeq().getParams().setAliasStringBuilder0SetCharAt0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_SET_CHAR_AT_0));
        getCharSeq().getParams().setAliasStringBuilder0SetCharAt1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_SET_CHAR_AT_1));
        getCharSeq().getParams().setAliasStringBuilder0SetLength0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_SET_LENGTH_0));
        getCharSeq().getParams().setAliasStringBuilder0EnsureCapacity0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_ENSURE_CAPACITY_0));
        getCharSeq().getParams().setAliasStringBuilder0Same0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_SAME_0));
        getCharSeq().getParams().setAliasStringBuilder0Same1(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_SAME_1));
        getCharSeq().getParams().setAliasStringBuilder0StringBuilder0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_0_STRING_BUILDER_0));
        getCharSeq().getParams().setAliasStringBuilder1StringBuilder0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_1_STRING_BUILDER_0));
        getCharSeq().getParams().setAliasStringBuilder2StringBuilder0(get(_util,_cust, AliasParamCharSequence.STRING_BUILDER_2_STRING_BUILDER_0));
        getCharSeq().getParams().setAliasReplacement0Replacement0(get(_util,_cust, AliasParamCharSequence.REPLACEMENT_0_REPLACEMENT_0));
        getCharSeq().getParams().setAliasReplacement0Replacement1(get(_util,_cust, AliasParamCharSequence.REPLACEMENT_0_REPLACEMENT_1));
        getCoreNames().getParams().setAliasError0CurrentStack0(get(_util,_cust, AliasParamCore.ERROR_0_CURRENT_STACK_0));
        getCoreNames().getParams().setAliasError0ToStringMethod0(get(_util,_cust, AliasParamCore.ERROR_0_TO_STRING_METHOD_0));
        getCoreNames().getParams().setAliasEnums0Name0(get(_util,_cust, AliasParamCore.ENUMS_0_NAME_0));
        getCoreNames().getParams().setAliasEnums0Ordinal0(get(_util,_cust, AliasParamCore.ENUMS_0_ORDINAL_0));
        getCoreNames().getParams().setAliasObjectsUtil0SameRef0(get(_util,_cust, AliasParamCore.OBJECTS_UTIL_0_SAME_REF_0));
        getCoreNames().getParams().setAliasObjectsUtil0SameRef1(get(_util,_cust, AliasParamCore.OBJECTS_UTIL_0_SAME_REF_1));
        getCoreNames().getParams().setAliasObjectsUtil0GetParent0(get(_util,_cust, AliasParamCore.OBJECTS_UTIL_0_GET_PARENT_0));
        getCoreNames().getParams().setAliasObjectsUtil0SetParent0(get(_util,_cust, AliasParamCore.OBJECTS_UTIL_0_SET_PARENT_0));
        getCoreNames().getParams().setAliasObjectsUtil0SetParent1(get(_util,_cust, AliasParamCore.OBJECTS_UTIL_0_SET_PARENT_1));
        getCoreNames().getParams().setAliasStringUtil0ValueOfMethod0(get(_util,_cust, AliasParamCore.STRING_UTIL_0_VALUE_OF_METHOD_0));
        getCoreNames().getParams().setAliasResources0ReadResources0(get(_util,_cust, AliasParamCore.RESOURCES_0_READ_RESOURCES_0));
        getCoreNames().getParams().setAliasResources0ReadResourcesIndex0(get(_util,_cust, AliasParamCore.RESOURCES_0_READ_RESOURCES_INDEX_0));
        getMathRef().getParams().setAliasMath0Abs0(get(_util,_cust, AliasParamMath.MATH_0_ABS_0));
        getMathRef().getParams().setAliasMath1Abs0(get(_util,_cust, AliasParamMath.MATH_1_ABS_0));
        getMathRef().getParams().setAliasMath0Max0(get(_util,_cust, AliasParamMath.MATH_0_MAX_0));
        getMathRef().getParams().setAliasMath0Max1(get(_util,_cust, AliasParamMath.MATH_0_MAX_1));
        getMathRef().getParams().setAliasMath1Max0(get(_util,_cust, AliasParamMath.MATH_1_MAX_0));
        getMathRef().getParams().setAliasMath1Max1(get(_util,_cust, AliasParamMath.MATH_1_MAX_1));
        getMathRef().getParams().setAliasMath0Min0(get(_util,_cust, AliasParamMath.MATH_0_MIN_0));
        getMathRef().getParams().setAliasMath0Min1(get(_util,_cust, AliasParamMath.MATH_0_MIN_1));
        getMathRef().getParams().setAliasMath1Min0(get(_util,_cust, AliasParamMath.MATH_1_MIN_0));
        getMathRef().getParams().setAliasMath1Min1(get(_util,_cust, AliasParamMath.MATH_1_MIN_1));
        getMathRef().getParams().setAliasMath0Quot0(get(_util,_cust, AliasParamMath.MATH_0_QUOT_0));
        getMathRef().getParams().setAliasMath0Quot1(get(_util,_cust, AliasParamMath.MATH_0_QUOT_1));
        getMathRef().getParams().setAliasMath1Quot0(get(_util,_cust, AliasParamMath.MATH_1_QUOT_0));
        getMathRef().getParams().setAliasMath1Quot1(get(_util,_cust, AliasParamMath.MATH_1_QUOT_1));
        getMathRef().getParams().setAliasMath0Mod0(get(_util,_cust, AliasParamMath.MATH_0_MOD_0));
        getMathRef().getParams().setAliasMath0Mod1(get(_util,_cust, AliasParamMath.MATH_0_MOD_1));
        getMathRef().getParams().setAliasMath1Mod0(get(_util,_cust, AliasParamMath.MATH_1_MOD_0));
        getMathRef().getParams().setAliasMath1Mod1(get(_util,_cust, AliasParamMath.MATH_1_MOD_1));
        getMathRef().getParams().setAliasMath0Plus0(get(_util,_cust, AliasParamMath.MATH_0_PLUS_0));
        getMathRef().getParams().setAliasMath1Plus0(get(_util,_cust, AliasParamMath.MATH_1_PLUS_0));
        getMathRef().getParams().setAliasMath2Plus0(get(_util,_cust, AliasParamMath.MATH_2_PLUS_0));
        getMathRef().getParams().setAliasMath3Plus0(get(_util,_cust, AliasParamMath.MATH_3_PLUS_0));
        getMathRef().getParams().setAliasMath0Minus0(get(_util,_cust, AliasParamMath.MATH_0_MINUS_0));
        getMathRef().getParams().setAliasMath1Minus0(get(_util,_cust, AliasParamMath.MATH_1_MINUS_0));
        getMathRef().getParams().setAliasMath2Minus0(get(_util,_cust, AliasParamMath.MATH_2_MINUS_0));
        getMathRef().getParams().setAliasMath3Minus0(get(_util,_cust, AliasParamMath.MATH_3_MINUS_0));
        getMathRef().getParams().setAliasMath0Neg0(get(_util,_cust, AliasParamMath.MATH_0_NEG_0));
        getMathRef().getParams().setAliasMath0NegBin0(get(_util,_cust, AliasParamMath.MATH_0_NEG_BIN_0));
        getMathRef().getParams().setAliasMath1NegBin0(get(_util,_cust, AliasParamMath.MATH_1_NEG_BIN_0));
        getMathRef().getParams().setAliasMath4Plus0(get(_util,_cust, AliasParamMath.MATH_4_PLUS_0));
        getMathRef().getParams().setAliasMath4Plus1(get(_util,_cust, AliasParamMath.MATH_4_PLUS_1));
        getMathRef().getParams().setAliasMath5Plus0(get(_util,_cust, AliasParamMath.MATH_5_PLUS_0));
        getMathRef().getParams().setAliasMath5Plus1(get(_util,_cust, AliasParamMath.MATH_5_PLUS_1));
        getMathRef().getParams().setAliasMath6Plus0(get(_util,_cust, AliasParamMath.MATH_6_PLUS_0));
        getMathRef().getParams().setAliasMath6Plus1(get(_util,_cust, AliasParamMath.MATH_6_PLUS_1));
        getMathRef().getParams().setAliasMath7Plus0(get(_util,_cust, AliasParamMath.MATH_7_PLUS_0));
        getMathRef().getParams().setAliasMath7Plus1(get(_util,_cust, AliasParamMath.MATH_7_PLUS_1));
        getMathRef().getParams().setAliasMath4Minus0(get(_util,_cust, AliasParamMath.MATH_4_MINUS_0));
        getMathRef().getParams().setAliasMath4Minus1(get(_util,_cust, AliasParamMath.MATH_4_MINUS_1));
        getMathRef().getParams().setAliasMath5Minus0(get(_util,_cust, AliasParamMath.MATH_5_MINUS_0));
        getMathRef().getParams().setAliasMath5Minus1(get(_util,_cust, AliasParamMath.MATH_5_MINUS_1));
        getMathRef().getParams().setAliasMath6Minus0(get(_util,_cust, AliasParamMath.MATH_6_MINUS_0));
        getMathRef().getParams().setAliasMath6Minus1(get(_util,_cust, AliasParamMath.MATH_6_MINUS_1));
        getMathRef().getParams().setAliasMath7Minus0(get(_util,_cust, AliasParamMath.MATH_7_MINUS_0));
        getMathRef().getParams().setAliasMath7Minus1(get(_util,_cust, AliasParamMath.MATH_7_MINUS_1));
        getMathRef().getParams().setAliasMath0Mult0(get(_util,_cust, AliasParamMath.MATH_0_MULT_0));
        getMathRef().getParams().setAliasMath0Mult1(get(_util,_cust, AliasParamMath.MATH_0_MULT_1));
        getMathRef().getParams().setAliasMath1Mult0(get(_util,_cust, AliasParamMath.MATH_1_MULT_0));
        getMathRef().getParams().setAliasMath1Mult1(get(_util,_cust, AliasParamMath.MATH_1_MULT_1));
        getMathRef().getParams().setAliasMath2Mult0(get(_util,_cust, AliasParamMath.MATH_2_MULT_0));
        getMathRef().getParams().setAliasMath2Mult1(get(_util,_cust, AliasParamMath.MATH_2_MULT_1));
        getMathRef().getParams().setAliasMath3Mult0(get(_util,_cust, AliasParamMath.MATH_3_MULT_0));
        getMathRef().getParams().setAliasMath3Mult1(get(_util,_cust, AliasParamMath.MATH_3_MULT_1));
        getMathRef().getParams().setAliasMath0BinQuot0(get(_util,_cust, AliasParamMath.MATH_0_BIN_QUOT_0));
        getMathRef().getParams().setAliasMath0BinQuot1(get(_util,_cust, AliasParamMath.MATH_0_BIN_QUOT_1));
        getMathRef().getParams().setAliasMath1BinQuot0(get(_util,_cust, AliasParamMath.MATH_1_BIN_QUOT_0));
        getMathRef().getParams().setAliasMath1BinQuot1(get(_util,_cust, AliasParamMath.MATH_1_BIN_QUOT_1));
        getMathRef().getParams().setAliasMath2BinQuot0(get(_util,_cust, AliasParamMath.MATH_2_BIN_QUOT_0));
        getMathRef().getParams().setAliasMath2BinQuot1(get(_util,_cust, AliasParamMath.MATH_2_BIN_QUOT_1));
        getMathRef().getParams().setAliasMath3BinQuot0(get(_util,_cust, AliasParamMath.MATH_3_BIN_QUOT_0));
        getMathRef().getParams().setAliasMath3BinQuot1(get(_util,_cust, AliasParamMath.MATH_3_BIN_QUOT_1));
        getMathRef().getParams().setAliasMath0BinMod0(get(_util,_cust, AliasParamMath.MATH_0_BIN_MOD_0));
        getMathRef().getParams().setAliasMath0BinMod1(get(_util,_cust, AliasParamMath.MATH_0_BIN_MOD_1));
        getMathRef().getParams().setAliasMath1BinMod0(get(_util,_cust, AliasParamMath.MATH_1_BIN_MOD_0));
        getMathRef().getParams().setAliasMath1BinMod1(get(_util,_cust, AliasParamMath.MATH_1_BIN_MOD_1));
        getMathRef().getParams().setAliasMath2BinMod0(get(_util,_cust, AliasParamMath.MATH_2_BIN_MOD_0));
        getMathRef().getParams().setAliasMath2BinMod1(get(_util,_cust, AliasParamMath.MATH_2_BIN_MOD_1));
        getMathRef().getParams().setAliasMath3BinMod0(get(_util,_cust, AliasParamMath.MATH_3_BIN_MOD_0));
        getMathRef().getParams().setAliasMath3BinMod1(get(_util,_cust, AliasParamMath.MATH_3_BIN_MOD_1));
        getMathRef().getParams().setAliasMath0And0(get(_util,_cust, AliasParamMath.MATH_0_AND_0));
        getMathRef().getParams().setAliasMath0And1(get(_util,_cust, AliasParamMath.MATH_0_AND_1));
        getMathRef().getParams().setAliasMath1And0(get(_util,_cust, AliasParamMath.MATH_1_AND_0));
        getMathRef().getParams().setAliasMath1And1(get(_util,_cust, AliasParamMath.MATH_1_AND_1));
        getMathRef().getParams().setAliasMath2And0(get(_util,_cust, AliasParamMath.MATH_2_AND_0));
        getMathRef().getParams().setAliasMath2And1(get(_util,_cust, AliasParamMath.MATH_2_AND_1));
        getMathRef().getParams().setAliasMath0Or0(get(_util,_cust, AliasParamMath.MATH_0_OR_0));
        getMathRef().getParams().setAliasMath0Or1(get(_util,_cust, AliasParamMath.MATH_0_OR_1));
        getMathRef().getParams().setAliasMath1Or0(get(_util,_cust, AliasParamMath.MATH_1_OR_0));
        getMathRef().getParams().setAliasMath1Or1(get(_util,_cust, AliasParamMath.MATH_1_OR_1));
        getMathRef().getParams().setAliasMath2Or0(get(_util,_cust, AliasParamMath.MATH_2_OR_0));
        getMathRef().getParams().setAliasMath2Or1(get(_util,_cust, AliasParamMath.MATH_2_OR_1));
        getMathRef().getParams().setAliasMath0Xor0(get(_util,_cust, AliasParamMath.MATH_0_XOR_0));
        getMathRef().getParams().setAliasMath0Xor1(get(_util,_cust, AliasParamMath.MATH_0_XOR_1));
        getMathRef().getParams().setAliasMath1Xor0(get(_util,_cust, AliasParamMath.MATH_1_XOR_0));
        getMathRef().getParams().setAliasMath1Xor1(get(_util,_cust, AliasParamMath.MATH_1_XOR_1));
        getMathRef().getParams().setAliasMath2Xor0(get(_util,_cust, AliasParamMath.MATH_2_XOR_0));
        getMathRef().getParams().setAliasMath2Xor1(get(_util,_cust, AliasParamMath.MATH_2_XOR_1));
        getMathRef().getParams().setAliasMath0ShiftLeft0(get(_util,_cust, AliasParamMath.MATH_0_SHIFT_LEFT_0));
        getMathRef().getParams().setAliasMath0ShiftLeft1(get(_util,_cust, AliasParamMath.MATH_0_SHIFT_LEFT_1));
        getMathRef().getParams().setAliasMath1ShiftLeft0(get(_util,_cust, AliasParamMath.MATH_1_SHIFT_LEFT_0));
        getMathRef().getParams().setAliasMath1ShiftLeft1(get(_util,_cust, AliasParamMath.MATH_1_SHIFT_LEFT_1));
        getMathRef().getParams().setAliasMath0ShiftRight0(get(_util,_cust, AliasParamMath.MATH_0_SHIFT_RIGHT_0));
        getMathRef().getParams().setAliasMath0ShiftRight1(get(_util,_cust, AliasParamMath.MATH_0_SHIFT_RIGHT_1));
        getMathRef().getParams().setAliasMath1ShiftRight0(get(_util,_cust, AliasParamMath.MATH_1_SHIFT_RIGHT_0));
        getMathRef().getParams().setAliasMath1ShiftRight1(get(_util,_cust, AliasParamMath.MATH_1_SHIFT_RIGHT_1));
        getMathRef().getParams().setAliasMath0BitShiftLeft0(get(_util,_cust, AliasParamMath.MATH_0_BIT_SHIFT_LEFT_0));
        getMathRef().getParams().setAliasMath0BitShiftLeft1(get(_util,_cust, AliasParamMath.MATH_0_BIT_SHIFT_LEFT_1));
        getMathRef().getParams().setAliasMath1BitShiftLeft0(get(_util,_cust, AliasParamMath.MATH_1_BIT_SHIFT_LEFT_0));
        getMathRef().getParams().setAliasMath1BitShiftLeft1(get(_util,_cust, AliasParamMath.MATH_1_BIT_SHIFT_LEFT_1));
        getMathRef().getParams().setAliasMath0BitShiftRight0(get(_util,_cust, AliasParamMath.MATH_0_BIT_SHIFT_RIGHT_0));
        getMathRef().getParams().setAliasMath0BitShiftRight1(get(_util,_cust, AliasParamMath.MATH_0_BIT_SHIFT_RIGHT_1));
        getMathRef().getParams().setAliasMath1BitShiftRight0(get(_util,_cust, AliasParamMath.MATH_1_BIT_SHIFT_RIGHT_0));
        getMathRef().getParams().setAliasMath1BitShiftRight1(get(_util,_cust, AliasParamMath.MATH_1_BIT_SHIFT_RIGHT_1));
        getMathRef().getParams().setAliasMath0RotateLeft0(get(_util,_cust, AliasParamMath.MATH_0_ROTATE_LEFT_0));
        getMathRef().getParams().setAliasMath0RotateLeft1(get(_util,_cust, AliasParamMath.MATH_0_ROTATE_LEFT_1));
        getMathRef().getParams().setAliasMath1RotateLeft0(get(_util,_cust, AliasParamMath.MATH_1_ROTATE_LEFT_0));
        getMathRef().getParams().setAliasMath1RotateLeft1(get(_util,_cust, AliasParamMath.MATH_1_ROTATE_LEFT_1));
        getMathRef().getParams().setAliasMath0RotateRight0(get(_util,_cust, AliasParamMath.MATH_0_ROTATE_RIGHT_0));
        getMathRef().getParams().setAliasMath0RotateRight1(get(_util,_cust, AliasParamMath.MATH_0_ROTATE_RIGHT_1));
        getMathRef().getParams().setAliasMath1RotateRight0(get(_util,_cust, AliasParamMath.MATH_1_ROTATE_RIGHT_0));
        getMathRef().getParams().setAliasMath1RotateRight1(get(_util,_cust, AliasParamMath.MATH_1_ROTATE_RIGHT_1));
        getMathRef().getParams().setAliasMath0Le0(get(_util,_cust, AliasParamMath.MATH_0_LE_0));
        getMathRef().getParams().setAliasMath0Le1(get(_util,_cust, AliasParamMath.MATH_0_LE_1));
        getMathRef().getParams().setAliasMath0Ge0(get(_util,_cust, AliasParamMath.MATH_0_GE_0));
        getMathRef().getParams().setAliasMath0Ge1(get(_util,_cust, AliasParamMath.MATH_0_GE_1));
        getMathRef().getParams().setAliasMath0Lt0(get(_util,_cust, AliasParamMath.MATH_0_LT_0));
        getMathRef().getParams().setAliasMath0Lt1(get(_util,_cust, AliasParamMath.MATH_0_LT_1));
        getMathRef().getParams().setAliasMath0Gt0(get(_util,_cust, AliasParamMath.MATH_0_GT_0));
        getMathRef().getParams().setAliasMath0Gt1(get(_util,_cust, AliasParamMath.MATH_0_GT_1));
        getMathRef().getParams().setAliasMath1Le0(get(_util,_cust, AliasParamMath.MATH_1_LE_0));
        getMathRef().getParams().setAliasMath1Le1(get(_util,_cust, AliasParamMath.MATH_1_LE_1));
        getMathRef().getParams().setAliasMath1Ge0(get(_util,_cust, AliasParamMath.MATH_1_GE_0));
        getMathRef().getParams().setAliasMath1Ge1(get(_util,_cust, AliasParamMath.MATH_1_GE_1));
        getMathRef().getParams().setAliasMath1Lt0(get(_util,_cust, AliasParamMath.MATH_1_LT_0));
        getMathRef().getParams().setAliasMath1Lt1(get(_util,_cust, AliasParamMath.MATH_1_LT_1));
        getMathRef().getParams().setAliasMath1Gt0(get(_util,_cust, AliasParamMath.MATH_1_GT_0));
        getMathRef().getParams().setAliasMath1Gt1(get(_util,_cust, AliasParamMath.MATH_1_GT_1));
        getMathRef().getParams().setAliasMath0Random0(get(_util,_cust, AliasParamMath.MATH_0_RANDOM_0));
        getMathRef().getParams().setAliasMath0Seed0(get(_util,_cust, AliasParamMath.MATH_0_SEED_0));
        getNbAlias().getParams().setAliasBoolean0Compare0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_COMPARE_0));
        getNbAlias().getParams().setAliasBoolean0Compare1(get(_util,_cust, AliasParamNumber.BOOLEAN_0_COMPARE_1));
        getNbAlias().getParams().setAliasBoolean0CompareTo0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_COMPARE_TO_0));
        getNbAlias().getParams().setAliasBoolean0Equals0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_EQUALS_0));
        getNbAlias().getParams().setAliasBoolean0ParseBoolean0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_PARSE_BOOLEAN_0));
        getNbAlias().getParams().setAliasBoolean0ToStringMethod0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_TO_STRING_METHOD_0));
        getNbAlias().getParams().setAliasBoolean0ValueOfMethod0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_VALUE_OF_METHOD_0));
        getNbAlias().getParams().setAliasBoolean1ValueOfMethod0(get(_util,_cust, AliasParamNumber.BOOLEAN_1_VALUE_OF_METHOD_0));
        getNbAlias().getParams().setAliasBoolean0Boolean0(get(_util,_cust, AliasParamNumber.BOOLEAN_0_BOOLEAN_0));
        getNbAlias().getParams().setAliasBoolean1Boolean0(get(_util,_cust, AliasParamNumber.BOOLEAN_1_BOOLEAN_0));
        getNbAlias().getParams().setAliasByte0ToStringMethod0(get(_util,_cust, AliasParamNumber.BYTE_0_TO_STRING_METHOD_0));
        getNbAlias().getParams().setAliasByte0ParseByte0(get(_util,_cust, AliasParamNumber.BYTE_0_PARSE_BYTE_0));
        getNbAlias().getParams().setAliasByte1ParseByte0(get(_util,_cust, AliasParamNumber.BYTE_1_PARSE_BYTE_0));
        getNbAlias().getParams().setAliasByte1ParseByte1(get(_util,_cust, AliasParamNumber.BYTE_1_PARSE_BYTE_1));
        getNbAlias().getParams().setAliasByte0CompareTo0(get(_util,_cust, AliasParamNumber.BYTE_0_COMPARE_TO_0));
        getNbAlias().getParams().setAliasByte0Compare0(get(_util,_cust, AliasParamNumber.BYTE_0_COMPARE_0));
        getNbAlias().getParams().setAliasByte0Compare1(get(_util,_cust, AliasParamNumber.BYTE_0_COMPARE_1));
        getNbAlias().getParams().setAliasByte0ParseByteOrNull0(get(_util,_cust, AliasParamNumber.BYTE_0_PARSE_BYTE_OR_NULL_0));
        getNbAlias().getParams().setAliasByte1ParseByteOrNull0(get(_util,_cust, AliasParamNumber.BYTE_1_PARSE_BYTE_OR_NULL_0));
        getNbAlias().getParams().setAliasByte1ParseByteOrNull1(get(_util,_cust, AliasParamNumber.BYTE_1_PARSE_BYTE_OR_NULL_1));
        getNbAlias().getParams().setAliasByte0ToBinString0(get(_util,_cust, AliasParamNumber.BYTE_0_TO_BIN_STRING_0));
        getNbAlias().getParams().setAliasByte0ToOctString0(get(_util,_cust, AliasParamNumber.BYTE_0_TO_OCT_STRING_0));
        getNbAlias().getParams().setAliasByte0ToHexString0(get(_util,_cust, AliasParamNumber.BYTE_0_TO_HEX_STRING_0));
        getNbAlias().getParams().setAliasByte0Byte0(get(_util,_cust, AliasParamNumber.BYTE_0_BYTE_0));
        getNbAlias().getParams().setAliasByte1Byte0(get(_util,_cust, AliasParamNumber.BYTE_1_BYTE_0));
        getNbAlias().getParams().setAliasShort0ToStringMethod0(get(_util,_cust, AliasParamNumber.SHORT_0_TO_STRING_METHOD_0));
        getNbAlias().getParams().setAliasShort0ParseShort0(get(_util,_cust, AliasParamNumber.SHORT_0_PARSE_SHORT_0));
        getNbAlias().getParams().setAliasShort1ParseShort0(get(_util,_cust, AliasParamNumber.SHORT_1_PARSE_SHORT_0));
        getNbAlias().getParams().setAliasShort1ParseShort1(get(_util,_cust, AliasParamNumber.SHORT_1_PARSE_SHORT_1));
        getNbAlias().getParams().setAliasShort0CompareTo0(get(_util,_cust, AliasParamNumber.SHORT_0_COMPARE_TO_0));
        getNbAlias().getParams().setAliasShort0Compare0(get(_util,_cust, AliasParamNumber.SHORT_0_COMPARE_0));
        getNbAlias().getParams().setAliasShort0Compare1(get(_util,_cust, AliasParamNumber.SHORT_0_COMPARE_1));
        getNbAlias().getParams().setAliasShort0ParseShortOrNull0(get(_util,_cust, AliasParamNumber.SHORT_0_PARSE_SHORT_OR_NULL_0));
        getNbAlias().getParams().setAliasShort1ParseShortOrNull0(get(_util,_cust, AliasParamNumber.SHORT_1_PARSE_SHORT_OR_NULL_0));
        getNbAlias().getParams().setAliasShort1ParseShortOrNull1(get(_util,_cust, AliasParamNumber.SHORT_1_PARSE_SHORT_OR_NULL_1));
        getNbAlias().getParams().setAliasShort0ToBinString0(get(_util,_cust, AliasParamNumber.SHORT_0_TO_BIN_STRING_0));
        getNbAlias().getParams().setAliasShort0ToOctString0(get(_util,_cust, AliasParamNumber.SHORT_0_TO_OCT_STRING_0));
        getNbAlias().getParams().setAliasShort0ToHexString0(get(_util,_cust, AliasParamNumber.SHORT_0_TO_HEX_STRING_0));
        getNbAlias().getParams().setAliasShort0Short0(get(_util,_cust, AliasParamNumber.SHORT_0_SHORT_0));
        getNbAlias().getParams().setAliasShort1Short0(get(_util,_cust, AliasParamNumber.SHORT_1_SHORT_0));
        getNbAlias().getParams().setAliasInteger0ToStringMethod0(get(_util,_cust, AliasParamNumber.INTEGER_0_TO_STRING_METHOD_0));
        getNbAlias().getParams().setAliasInteger0ParseInt0(get(_util,_cust, AliasParamNumber.INTEGER_0_PARSE_INT_0));
        getNbAlias().getParams().setAliasInteger1ParseInt0(get(_util,_cust, AliasParamNumber.INTEGER_1_PARSE_INT_0));
        getNbAlias().getParams().setAliasInteger1ParseInt1(get(_util,_cust, AliasParamNumber.INTEGER_1_PARSE_INT_1));
        getNbAlias().getParams().setAliasInteger0CompareTo0(get(_util,_cust, AliasParamNumber.INTEGER_0_COMPARE_TO_0));
        getNbAlias().getParams().setAliasInteger0Compare0(get(_util,_cust, AliasParamNumber.INTEGER_0_COMPARE_0));
        getNbAlias().getParams().setAliasInteger0Compare1(get(_util,_cust, AliasParamNumber.INTEGER_0_COMPARE_1));
        getNbAlias().getParams().setAliasInteger0ParseIntOrNull0(get(_util,_cust, AliasParamNumber.INTEGER_0_PARSE_INT_OR_NULL_0));
        getNbAlias().getParams().setAliasInteger1ParseIntOrNull0(get(_util,_cust, AliasParamNumber.INTEGER_1_PARSE_INT_OR_NULL_0));
        getNbAlias().getParams().setAliasInteger1ParseIntOrNull1(get(_util,_cust, AliasParamNumber.INTEGER_1_PARSE_INT_OR_NULL_1));
        getNbAlias().getParams().setAliasInteger0ToBinString0(get(_util,_cust, AliasParamNumber.INTEGER_0_TO_BIN_STRING_0));
        getNbAlias().getParams().setAliasInteger0ToOctString0(get(_util,_cust, AliasParamNumber.INTEGER_0_TO_OCT_STRING_0));
        getNbAlias().getParams().setAliasInteger0ToHexString0(get(_util,_cust, AliasParamNumber.INTEGER_0_TO_HEX_STRING_0));
        getNbAlias().getParams().setAliasInteger0Integer0(get(_util,_cust, AliasParamNumber.INTEGER_0_INTEGER_0));
        getNbAlias().getParams().setAliasInteger1Integer0(get(_util,_cust, AliasParamNumber.INTEGER_1_INTEGER_0));
        getNbAlias().getParams().setAliasLong0ToStringMethod0(get(_util,_cust, AliasParamNumber.LONG_0_TO_STRING_METHOD_0));
        getNbAlias().getParams().setAliasLong1ToStringMethod0(get(_util,_cust, AliasParamNumber.LONG_1_TO_STRING_METHOD_0));
        getNbAlias().getParams().setAliasLong1ToStringMethod1(get(_util,_cust, AliasParamNumber.LONG_1_TO_STRING_METHOD_1));
        getNbAlias().getParams().setAliasLong0Signum0(get(_util,_cust, AliasParamNumber.LONG_0_SIGNUM_0));
        getNbAlias().getParams().setAliasLong0ParseLong0(get(_util,_cust, AliasParamNumber.LONG_0_PARSE_LONG_0));
        getNbAlias().getParams().setAliasLong1ParseLong0(get(_util,_cust, AliasParamNumber.LONG_1_PARSE_LONG_0));
        getNbAlias().getParams().setAliasLong1ParseLong1(get(_util,_cust, AliasParamNumber.LONG_1_PARSE_LONG_1));
        getNbAlias().getParams().setAliasLong0CompareTo0(get(_util,_cust, AliasParamNumber.LONG_0_COMPARE_TO_0));
        getNbAlias().getParams().setAliasLong0Compare0(get(_util,_cust, AliasParamNumber.LONG_0_COMPARE_0));
        getNbAlias().getParams().setAliasLong0Compare1(get(_util,_cust, AliasParamNumber.LONG_0_COMPARE_1));
        getNbAlias().getParams().setAliasLong0ParseLongOrNull0(get(_util,_cust, AliasParamNumber.LONG_0_PARSE_LONG_OR_NULL_0));
        getNbAlias().getParams().setAliasLong1ParseLongOrNull0(get(_util,_cust, AliasParamNumber.LONG_1_PARSE_LONG_OR_NULL_0));
        getNbAlias().getParams().setAliasLong1ParseLongOrNull1(get(_util,_cust, AliasParamNumber.LONG_1_PARSE_LONG_OR_NULL_1));
        getNbAlias().getParams().setAliasLong0ToBinString0(get(_util,_cust, AliasParamNumber.LONG_0_TO_BIN_STRING_0));
        getNbAlias().getParams().setAliasLong0ToOctString0(get(_util,_cust, AliasParamNumber.LONG_0_TO_OCT_STRING_0));
        getNbAlias().getParams().setAliasLong0ToHexString0(get(_util,_cust, AliasParamNumber.LONG_0_TO_HEX_STRING_0));
        getNbAlias().getParams().setAliasLong0Long0(get(_util,_cust, AliasParamNumber.LONG_0_LONG_0));
        getNbAlias().getParams().setAliasLong1Long0(get(_util,_cust, AliasParamNumber.LONG_1_LONG_0));
        getNbAlias().getParams().setAliasFloat0ToStringMethod0(get(_util,_cust, AliasParamNumber.FLOAT_0_TO_STRING_METHOD_0));
        getNbAlias().getParams().setAliasFloat0ParseFloat0(get(_util,_cust, AliasParamNumber.FLOAT_0_PARSE_FLOAT_0));
        getNbAlias().getParams().setAliasFloat0CompareTo0(get(_util,_cust, AliasParamNumber.FLOAT_0_COMPARE_TO_0));
        getNbAlias().getParams().setAliasFloat0Compare0(get(_util,_cust, AliasParamNumber.FLOAT_0_COMPARE_0));
        getNbAlias().getParams().setAliasFloat0Compare1(get(_util,_cust, AliasParamNumber.FLOAT_0_COMPARE_1));
        getNbAlias().getParams().setAliasFloat0ParseFloatOrNull0(get(_util,_cust, AliasParamNumber.FLOAT_0_PARSE_FLOAT_OR_NULL_0));
        getNbAlias().getParams().setAliasFloat0IsInfinite0(get(_util,_cust, AliasParamNumber.FLOAT_0_IS_INFINITE_0));
        getNbAlias().getParams().setAliasFloat0IsNan0(get(_util,_cust, AliasParamNumber.FLOAT_0_IS_NAN_0));
        getNbAlias().getParams().setAliasFloat0Float0(get(_util,_cust, AliasParamNumber.FLOAT_0_FLOAT_0));
        getNbAlias().getParams().setAliasFloat1Float0(get(_util,_cust, AliasParamNumber.FLOAT_1_FLOAT_0));
        getNbAlias().getParams().setAliasDouble0ToStringMethod0(get(_util,_cust, AliasParamNumber.DOUBLE_0_TO_STRING_METHOD_0));
        getNbAlias().getParams().setAliasDouble0ParseDouble0(get(_util,_cust, AliasParamNumber.DOUBLE_0_PARSE_DOUBLE_0));
        getNbAlias().getParams().setAliasDouble0CompareTo0(get(_util,_cust, AliasParamNumber.DOUBLE_0_COMPARE_TO_0));
        getNbAlias().getParams().setAliasDouble0Compare0(get(_util,_cust, AliasParamNumber.DOUBLE_0_COMPARE_0));
        getNbAlias().getParams().setAliasDouble0Compare1(get(_util,_cust, AliasParamNumber.DOUBLE_0_COMPARE_1));
        getNbAlias().getParams().setAliasDouble0ParseDoubleOrNull0(get(_util,_cust, AliasParamNumber.DOUBLE_0_PARSE_DOUBLE_OR_NULL_0));
        getNbAlias().getParams().setAliasDouble0IsInfinite0(get(_util,_cust, AliasParamNumber.DOUBLE_0_IS_INFINITE_0));
        getNbAlias().getParams().setAliasDouble0IsNan0(get(_util,_cust, AliasParamNumber.DOUBLE_0_IS_NAN_0));
        getNbAlias().getParams().setAliasDouble0Double0(get(_util,_cust, AliasParamNumber.DOUBLE_0_DOUBLE_0));
        getNbAlias().getParams().setAliasDouble1Double0(get(_util,_cust, AliasParamNumber.DOUBLE_1_DOUBLE_0));
        getNbAlias().getParams().setAliasNumber0ToStringMethod0(get(_util,_cust, AliasParamNumber.NUMBER_0_TO_STRING_METHOD_0));
        getNbAlias().getParams().setAliasNumber0Equals0(get(_util,_cust, AliasParamNumber.NUMBER_0_EQUALS_0));
        getNbAlias().getParams().setAliasNumber1Equals0(get(_util,_cust, AliasParamNumber.NUMBER_1_EQUALS_0));
        getNbAlias().getParams().setAliasNumber1Equals1(get(_util,_cust, AliasParamNumber.NUMBER_1_EQUALS_1));
        getNbAlias().getParams().setAliasNumber0CompareTo0(get(_util,_cust, AliasParamNumber.NUMBER_0_COMPARE_TO_0));
        getNbAlias().getParams().setAliasNumber0Compare0(get(_util,_cust, AliasParamNumber.NUMBER_0_COMPARE_0));
        getNbAlias().getParams().setAliasNumber0Compare1(get(_util,_cust, AliasParamNumber.NUMBER_0_COMPARE_1));
        getNbAlias().getParams().setAliasCharacter0CompareTo0(get(_util,_cust, AliasParamNumber.CHARACTER_0_COMPARE_TO_0));
        getNbAlias().getParams().setAliasCharacter0Compare0(get(_util,_cust, AliasParamNumber.CHARACTER_0_COMPARE_0));
        getNbAlias().getParams().setAliasCharacter0Compare1(get(_util,_cust, AliasParamNumber.CHARACTER_0_COMPARE_1));
        getNbAlias().getParams().setAliasCharacter0Digit0(get(_util,_cust, AliasParamNumber.CHARACTER_0_DIGIT_0));
        getNbAlias().getParams().setAliasCharacter0Digit1(get(_util,_cust, AliasParamNumber.CHARACTER_0_DIGIT_1));
        getNbAlias().getParams().setAliasCharacter0ForDigit0(get(_util,_cust, AliasParamNumber.CHARACTER_0_FOR_DIGIT_0));
        getNbAlias().getParams().setAliasCharacter0ForDigit1(get(_util,_cust, AliasParamNumber.CHARACTER_0_FOR_DIGIT_1));
        getNbAlias().getParams().setAliasCharacter0GetDirectionality0(get(_util,_cust, AliasParamNumber.CHARACTER_0_GET_DIRECTIONALITY_0));
        getNbAlias().getParams().setAliasCharacter0GetType0(get(_util,_cust, AliasParamNumber.CHARACTER_0_GET_TYPE_0));
        getNbAlias().getParams().setAliasCharacter0IsDigit0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_DIGIT_0));
        getNbAlias().getParams().setAliasCharacter0IsLetter0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_LETTER_0));
        getNbAlias().getParams().setAliasCharacter0IsLetterOrDigit0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_LETTER_OR_DIGIT_0));
        getNbAlias().getParams().setAliasCharacter0IsWordChar0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_WORD_CHAR_0));
        getNbAlias().getParams().setAliasCharacter0IsWhitespace0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_WHITESPACE_0));
        getNbAlias().getParams().setAliasCharacter0IsLowerCase0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_LOWER_CASE_0));
        getNbAlias().getParams().setAliasCharacter0IsUpperCase0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_UPPER_CASE_0));
        getNbAlias().getParams().setAliasCharacter0IsSpace0(get(_util,_cust, AliasParamNumber.CHARACTER_0_IS_SPACE_0));
        getNbAlias().getParams().setAliasCharacter0ToLowerCaseChar0(get(_util,_cust, AliasParamNumber.CHARACTER_0_TO_LOWER_CASE_CHAR_0));
        getNbAlias().getParams().setAliasCharacter0ToUpperCaseChar0(get(_util,_cust, AliasParamNumber.CHARACTER_0_TO_UPPER_CASE_CHAR_0));
        getNbAlias().getParams().setAliasCharacter0ToStringMethod0(get(_util,_cust, AliasParamNumber.CHARACTER_0_TO_STRING_METHOD_0));
        getNbAlias().getParams().setAliasCharacter0Character0(get(_util,_cust, AliasParamNumber.CHARACTER_0_CHARACTER_0));
        getReflect().getParams().setAliasFct0Call0(get(_util,_cust, AliasParamReflection.FCT_0_CALL_0));
        getReflect().getParams().setAliasClassType0GetClass0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_CLASS_0));
        getReflect().getParams().setAliasClassType0ForName0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_FOR_NAME_0));
        getReflect().getParams().setAliasClassType0ForName1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_FOR_NAME_1));
        getReflect().getParams().setAliasClassType1ForName0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_1_FOR_NAME_0));
        getReflect().getParams().setAliasClassType0IsInstance0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_IS_INSTANCE_0));
        getReflect().getParams().setAliasClassType0IsAssignableFrom0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_IS_ASSIGNABLE_FROM_0));
        getReflect().getParams().setAliasClassType0DefaultInstance0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_DEFAULT_INSTANCE_0));
        getReflect().getParams().setAliasClassType0EnumValueOf0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ENUM_VALUE_OF_0));
        getReflect().getParams().setAliasClassType0GetDeclaredConstructors0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_0));
        getReflect().getParams().setAliasClassType0GetDeclaredConstructors1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_1));
        getReflect().getParams().setAliasClassType0GetDeclaredFields0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_FIELDS_0));
        getReflect().getParams().setAliasClassType0GetDeclaredStaticMethods0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_0));
        getReflect().getParams().setAliasClassType0GetDeclaredStaticMethods1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_1));
        getReflect().getParams().setAliasClassType0GetDeclaredStaticMethods2(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_2));
        getReflect().getParams().setAliasClassType0GetDeclaredStaticMethods3(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_3));
        getReflect().getParams().setAliasClassType0GetDeclaredMethods0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_0));
        getReflect().getParams().setAliasClassType0GetDeclaredMethods1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_1));
        getReflect().getParams().setAliasClassType0GetDeclaredMethods2(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_2));
        getReflect().getParams().setAliasClassType0GetDeclaredMethods3(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_3));
        getReflect().getParams().setAliasClassType0GetDeclaredExplicits0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_EXPLICITS_0));
        getReflect().getParams().setAliasClassType0GetDeclaredImplicits0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_IMPLICITS_0));
        getReflect().getParams().setAliasClassType0GetDeclaredBlocks0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_BLOCKS_0));
        getReflect().getParams().setAliasClassType0GetDeclaredBlocks1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_BLOCKS_1));
        getReflect().getParams().setAliasClassType0MakeGeneric0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_MAKE_GENERIC_0));
        getReflect().getParams().setAliasClassType0MakeWildCard0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_MAKE_WILD_CARD_0));
        getReflect().getParams().setAliasClassType0GetOperators0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_OPERATORS_0));
        getReflect().getParams().setAliasClassType0GetOperators1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_OPERATORS_1));
        getReflect().getParams().setAliasClassType0GetOperators2(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_GET_OPERATORS_2));
        getReflect().getParams().setAliasClassType0ArrayNewInstance0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_NEW_INSTANCE_0));
        getReflect().getParams().setAliasClassType0ArrayGetLength0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_GET_LENGTH_0));
        getReflect().getParams().setAliasClassType0ArrayGet0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_GET_0));
        getReflect().getParams().setAliasClassType0ArrayGet1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_GET_1));
        getReflect().getParams().setAliasClassType0ArraySet0(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_SET_0));
        getReflect().getParams().setAliasClassType0ArraySet1(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_SET_1));
        getReflect().getParams().setAliasClassType0ArraySet2(get(_util,_cust, AliasParamReflection.CLASS_TYPE_0_ARRAY_SET_2));
        getReflect().getParams().setAliasConstructor0NewInstance0(get(_util,_cust, AliasParamReflection.CONSTRUCTOR_0_NEW_INSTANCE_0));
        getReflect().getParams().setAliasField0GetField0(get(_util,_cust, AliasParamReflection.FIELD_0_GET_FIELD_0));
        getReflect().getParams().setAliasField0SetField0(get(_util,_cust, AliasParamReflection.FIELD_0_SET_FIELD_0));
        getReflect().getParams().setAliasField0SetField1(get(_util,_cust, AliasParamReflection.FIELD_0_SET_FIELD_1));
        getReflect().getParams().setAliasMethod0Invoke0(get(_util,_cust, AliasParamReflection.METHOD_0_INVOKE_0));
        getReflect().getParams().setAliasMethod0Invoke1(get(_util,_cust, AliasParamReflection.METHOD_0_INVOKE_1));
        getReflect().getParams().setAliasMethod0InvokeDirect0(get(_util,_cust, AliasParamReflection.METHOD_0_INVOKE_DIRECT_0));
        getReflect().getParams().setAliasMethod0InvokeDirect1(get(_util,_cust, AliasParamReflection.METHOD_0_INVOKE_DIRECT_1));
        getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLocalVars0(get(_util,_cust, AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0));
        getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLocalVars1(get(_util,_cust, AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1));
        getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLocalVars2(get(_util,_cust, AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_2));
        getReflect().getParams().setAliasMethod1GetDeclaredAnonymousLambdaLocalVars0(get(_util,_cust, AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0));
        getReflect().getParams().setAliasMethod1GetDeclaredAnonymousLambdaLocalVars1(get(_util,_cust, AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1));
        getReflect().getParams().setAliasMethod2GetDeclaredAnonymousLambdaLocalVars0(get(_util,_cust, AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0));
        getReflect().getParams().setAliasMethod2GetDeclaredAnonymousLambdaLocalVars1(get(_util,_cust, AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1));
        getReflect().getParams().setAliasMethod3GetDeclaredAnonymousLambdaLocalVars0(get(_util,_cust, AliasParamReflection.METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0));
        getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLoopVars0(get(_util,_cust, AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0));
        getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLoopVars1(get(_util,_cust, AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1));
        getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLoopVars2(get(_util,_cust, AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_2));
        getReflect().getParams().setAliasMethod1GetDeclaredAnonymousLambdaLoopVars0(get(_util,_cust, AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0));
        getReflect().getParams().setAliasMethod1GetDeclaredAnonymousLambdaLoopVars1(get(_util,_cust, AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1));
        getReflect().getParams().setAliasMethod2GetDeclaredAnonymousLambdaLoopVars0(get(_util,_cust, AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0));
        getReflect().getParams().setAliasMethod2GetDeclaredAnonymousLambdaLoopVars1(get(_util,_cust, AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1));
        getReflect().getParams().setAliasMethod3GetDeclaredAnonymousLambdaLoopVars0(get(_util,_cust, AliasParamReflection.METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0));
        getReflect().getParams().setAliasAnnotationType0GetString0(get(_util,_cust, AliasParamReflection.ANNOTATION_TYPE_0_GET_STRING_0));
        getReflect().getParams().setAliasAnnotated0GetAnnotations0(get(_util,_cust, AliasParamReflection.ANNOTATED_0_GET_ANNOTATIONS_0));
        getReflect().getParams().setAliasAnnotated0GetAnnotationsParameters0(get(_util,_cust, AliasParamReflection.ANNOTATED_0_GET_ANNOTATIONS_PARAMETERS_0));
        getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda0(get(_util,_cust, AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_0));
        getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda1(get(_util,_cust, AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_1));
        getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda2(get(_util,_cust, AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_2));
        getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda3(get(_util,_cust, AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_3));
    }

    public StringMap<String> allPrimitives() {
        StringMap<String> list_ = new StringMap<String>();
        list_.addEntry(PRIM_BOOLEAN, getPrimTypes().getAliasPrimBoolean());
        list_.addEntry(PRIM_BYTE, getPrimTypes().getAliasPrimByte());
        list_.addEntry(PRIM_SHORT, getPrimTypes().getAliasPrimShort());
        list_.addEntry(PRIM_CHAR, getPrimTypes().getAliasPrimChar());
        list_.addEntry(PRIM_INTEGER, getPrimTypes().getAliasPrimInteger());
        list_.addEntry(PRIM_LONG, getPrimTypes().getAliasPrimLong());
        list_.addEntry(PRIM_FLOAT, getPrimTypes().getAliasPrimFloat());
        list_.addEntry(PRIM_DOUBLE, getPrimTypes().getAliasPrimDouble());
        list_.addEntry(VOID, getCoreNames().getAliasVoid());
        return list_;
    }
    public StringMap<String> allRefTypes() {
        StringMap<String> list_ = new StringMap<String>();
        list_.addEntry(ANNOTATED, getReflect().getAliasAnnotated());
        list_.addEntry(ANNOTATION_TYPE, getReflect().getAliasAnnotationType());
        list_.addEntry(CLASS_TYPE, getReflect().getAliasClassType());
        list_.addEntry(CONSTRUCTOR, getReflect().getAliasConstructor());
        list_.addEntry(FCT, getReflect().getAliasFct());
        list_.addEntry(FIELD, getReflect().getAliasField());
        list_.addEntry(METHOD, getReflect().getAliasMethod());
        list_.addEntry(OBJECTS_UTIL, getCoreNames().getAliasObjectsUtil());
        list_.addEntry(STRING_UTIL, getCoreNames().getAliasStringUtil());
        list_.addEntry(RESOURCES, getCoreNames().getAliasResources());
        list_.addEntry(CLASS_NOT_FOUND_ERROR, getReflect().getAliasClassNotFoundError());
        list_.addEntry(ERROR_INIT_CLASS, getCoreNames().getAliasErrorInitClass());
        list_.addEntry(INVOKE_TARGET, getReflect().getAliasInvokeTarget());
        list_.addEntry(ENUM_TYPE, getPredefTypes().getAliasEnumType());
        list_.addEntry(SEED_DOUBLE_GENERATOR, getPredefTypes().getAliasSeedDoubleGenerator());
        list_.addEntry(SEED_GENERATOR, getPredefTypes().getAliasSeedGenerator());
        list_.addEntry(ITERABLE, getPredefTypes().getAliasIterable());
        list_.addEntry(ITERATOR_TYPE, getPredefTypes().getAliasIteratorType());
        list_.addEntry(ENUM_PARAM, getPredefTypes().getAliasEnumParam());
        list_.addEntry(ENUMS, getCoreNames().getAliasEnums());
        list_.addEntry(ITERATOR_TABLE_TYPE, getPredefTypes().getAliasIteratorTableType());
        list_.addEntry(ITERABLE_TABLE, getPredefTypes().getAliasIterableTable());
        list_.addEntry(PAIR_TYPE, getPredefTypes().getAliasPairType());
        list_.addEntry(MATH, getMathRef().getAliasMath());
        list_.addEntry(STACK_TRACE_ELEMENT, getStackElt().getAliasStackTraceElement());
        list_.addEntry(BAD_ENCODE, getCoreNames().getAliasBadEncode());
        list_.addEntry(BAD_INDEX, getCoreNames().getAliasBadIndex());
        list_.addEntry(BAD_ARG_NUMBER, getCoreNames().getAliasBadArgNumber());
        list_.addEntry(ABSTRACT_TYPE_ERR, getCoreNames().getAliasAbstractTypeErr());
        list_.addEntry(ILLEGAL_TYPE, getCoreNames().getAliasIllegalType());
        list_.addEntry(NON_INVOKABLE, getCoreNames().getAliasNonInvokable());
        list_.addEntry(ILLEGAL_ARG, getCoreNames().getAliasIllegalArg());
        list_.addEntry(DIVISION_ZERO, getCoreNames().getAliasDivisionZero());
        list_.addEntry(STORE, getCoreNames().getAliasStore());
        list_.addEntry(CAST_TYPE, getCoreNames().getAliasCastType());
        list_.addEntry(BAD_SIZE, getCoreNames().getAliasBadSize());
        list_.addEntry(SOF, getCoreNames().getAliasSof());
        list_.addEntry(REPLACEMENT, getCharSeq().getAliasReplacement());
        list_.addEntry(NULL_PE, getCoreNames().getAliasNullPe());
        list_.addEntry(NB_FORMAT, getCoreNames().getAliasNbFormat());
        list_.addEntry(BOOLEAN, getNbAlias().getAliasBoolean());
        list_.addEntry(BYTE, getNbAlias().getAliasByte());
        list_.addEntry(CHAR_SEQUENCE, getCharSeq().getAliasCharSequence());
        list_.addEntry(CHARACTER, getNbAlias().getAliasCharacter());
        list_.addEntry(DOUBLE, getNbAlias().getAliasDouble());
        list_.addEntry(ERROR, getCoreNames().getAliasError());
        list_.addEntry(FLOAT, getNbAlias().getAliasFloat());
        list_.addEntry(INTEGER, getNbAlias().getAliasInteger());
        list_.addEntry(LONG, getNbAlias().getAliasLong());
        list_.addEntry(NUMBER,getNbAlias().getAliasNumber());
        list_.addEntry(OBJECT,getCoreNames().getAliasObject());
        list_.addEntry(SHORT,getNbAlias().getAliasShort());
        list_.addEntry(STRING,getCharSeq().getAliasString());
        list_.addEntry(STRING_BUILDER,getCharSeq().getAliasStringBuilder());
        return list_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(StringExpUtil.getPrettyArrayType(getCoreNames().getAliasObject()), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ARRAY_LENGTH, getCoreNames().getAliasArrayLength())));
        map_.addEntry(getNbAlias().getAliasDouble(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_PLUS_INFINITY, getNbAlias().getAliasPlusInfinityField()),
                new KeyValueMemberName(FIELD_MINUS_INFINITY, getNbAlias().getAliasMinusInfinityField()),
                new KeyValueMemberName(FIELD_NAN, getNbAlias().getAliasNanField()),
                new KeyValueMemberName(FIELD_MIN_VALUE, getNbAlias().getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE, getNbAlias().getAliasMaxValueField())));
        map_.addEntry(getNbAlias().getAliasFloat(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_PLUS_INFINITY, getNbAlias().getAliasPlusInfinityField()),
                new KeyValueMemberName(FIELD_MINUS_INFINITY, getNbAlias().getAliasMinusInfinityField()),
                new KeyValueMemberName(FIELD_NAN, getNbAlias().getAliasNanField()),
                new KeyValueMemberName(FIELD_MIN_VALUE, getNbAlias().getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE, getNbAlias().getAliasMaxValueField())));
        map_.addEntry(getNbAlias().getAliasLong(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_MIN_VALUE, getNbAlias().getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE, getNbAlias().getAliasMaxValueField())));
        map_.addEntry(getNbAlias().getAliasInteger(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_MIN_VALUE, getNbAlias().getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE, getNbAlias().getAliasMaxValueField())));
        map_.addEntry(getNbAlias().getAliasCharacter(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_MIN_VALUE, getNbAlias().getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE, getNbAlias().getAliasMaxValueField())));
        map_.addEntry(getNbAlias().getAliasShort(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_MIN_VALUE, getNbAlias().getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE, getNbAlias().getAliasMaxValueField())));
        map_.addEntry(getNbAlias().getAliasByte(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_MIN_VALUE, getNbAlias().getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE, getNbAlias().getAliasMaxValueField())));
        return map_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes() {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(getPredefTypes().getAliasEnumParam(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ENUM_PARAM_VAR,getPredefTypes().getAliasEnumParamVar())));
        map_.addEntry(getPredefTypes().getAliasIterable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERABLE_VAR,getPredefTypes().getAliasIterableVar())));
        map_.addEntry(getPredefTypes().getAliasIteratorType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERATOR_TYPE_VAR,getPredefTypes().getAliasIteratorTypeVar())));
        map_.addEntry(getPredefTypes().getAliasIterableTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERABLE_TABLE_VAR_FIRST,getPredefTypes().getAliasIterableTableVarFirst()),
                new KeyValueMemberName(ITERABLE_TABLE_VAR_SECOND,getPredefTypes().getAliasIterableTableVarSecond())));
        map_.addEntry(getPredefTypes().getAliasIteratorTableType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERATOR_TABLE_TYPE_VAR_FIRST,getPredefTypes().getAliasIteratorTableTypeVarFirst()),
                new KeyValueMemberName(ITERATOR_TABLE_TYPE_VAR_SECOND,getPredefTypes().getAliasIteratorTableTypeVarSecond())));
        map_.addEntry(getPredefTypes().getAliasPairType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(PAIR_TYPE_VAR_FIRST,getPredefTypes().getAliasPairTypeVarFirst()),
                new KeyValueMemberName(PAIR_TYPE_VAR_SECOND,getPredefTypes().getAliasPairTypeVarSecond())));
        return map_;
    }
    public CustList<CustList<KeyValueMemberName>> allMergeTableTypeMethodNames() {
        CustList<CustList<KeyValueMemberName>> list_ = new CustList<CustList<KeyValueMemberName>>();
        list_.add(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERATOR,getPredefTypes().getAliasIterator()),
                new KeyValueMemberName(HAS_NEXT,getPredefTypes().getAliasHasNext()),
                new KeyValueMemberName(NEXT,getPredefTypes().getAliasNext()),
                new KeyValueMemberName(ITERATOR_TABLE,getPredefTypes().getAliasIteratorTable()),
                new KeyValueMemberName(HAS_NEXT_PAIR,getPredefTypes().getAliasHasNextPair()),
                new KeyValueMemberName(NEXT_PAIR,getPredefTypes().getAliasNextPair()),
                new KeyValueMemberName(GET_FIRST,getPredefTypes().getAliasGetFirst()),
                new KeyValueMemberName(GET_SECOND,getPredefTypes().getAliasGetSecond()),
                new KeyValueMemberName(ENUM_ORDINAL,getPredefTypes().getAliasEnumOrdinal()),
                new KeyValueMemberName(ENUM_NAME,getPredefTypes().getAliasEnumName()),
                new KeyValueMemberName(SEED_GET,getPredefTypes().getAliasSeedGet())
        ));
        return list_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(StringExpUtil.getPrettyArrayType(getCoreNames().getAliasObject()), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(CLONE, getCoreNames().getAliasClone())));
        map_.addEntry(getCoreNames().getAliasError(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ERROR_CURRENT_STACK, getCoreNames().getAliasErrorCurrentStack()),
                new KeyValueMemberName(ERROR_TO_STRING, getCoreNames().getAliasErrorToString()),
                new KeyValueMemberName(GET_MESSAGE, getCoreNames().getAliasGetMessage()),
                new KeyValueMemberName(GET_CAUSE, getCoreNames().getAliasGetCause())));
        map_.addEntry(getReflect().getAliasAnnotated(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_FILE_NAME, getReflect().getAliasGetFileName()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA, getReflect().getAliasGetDeclaredAnonymousLambda()),
                new KeyValueMemberName(GET_ANNOTATIONS, getReflect().getAliasGetAnnotations()),
                new KeyValueMemberName(GET_ANNOTATIONS_PARAMETERS, getReflect().getAliasGetAnnotationsParameters())));
        map_.addEntry(getReflect().getAliasAnnotationType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_STRING, getReflect().getAliasGetString())));
        map_.addEntry(getReflect().getAliasClassType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_ANNOTATIONS, getReflect().getAliasGetAnnotations()),
                new KeyValueMemberName(GET_ANNOTATIONS_PARAMETERS, getReflect().getAliasGetAnnotationsParameters()),
                new KeyValueMemberName(DEFAULT_INSTANCE, getReflect().getAliasDefaultInstance()),
                new KeyValueMemberName(ENUM_VALUE_OF, getReflect().getAliasEnumValueOf()),
                new KeyValueMemberName(FOR_NAME, getReflect().getAliasForName()),
                new KeyValueMemberName(ARRAY_GET, getReflect().getAliasArrayGet()),
                new KeyValueMemberName(GET_ACTUAL_TYPE_ARGUMENTS, getReflect().getAliasGetActualTypeArguments()),
                new KeyValueMemberName(GET_ALL_CLASSES, getReflect().getAliasGetAllClasses()),
                new KeyValueMemberName(GET_BOUNDS, getReflect().getAliasGetBounds()),
                new KeyValueMemberName(GET_CLASS, getReflect().getAliasGetClass()),
                new KeyValueMemberName(GET_COMPONENT_TYPE, getReflect().getAliasGetComponentType()),
                new KeyValueMemberName(GET_DECLARED_CLASSES, getReflect().getAliasGetDeclaredClasses()),
                new KeyValueMemberName(GET_DECLARED_CONSTRUCTORS, getReflect().getAliasGetDeclaredConstructors()),
                new KeyValueMemberName(GET_DECLARED_FIELDS, getReflect().getAliasGetDeclaredFields()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_TYPES, getReflect().getAliasGetDeclaredAnonymousTypes()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA, getReflect().getAliasGetDeclaredAnonymousLambda()),
                new KeyValueMemberName(GET_DECLARED_LOCAL_TYPES,getReflect().getAliasGetDeclaredLocalTypes()),
                new KeyValueMemberName(GET_DECLARED_BLOCKS,getReflect().getAliasGetDeclaredBlocks()),
                new KeyValueMemberName(GET_DECLARED_EXPLICITS,getReflect().getAliasGetDeclaredExplicits()),
                new KeyValueMemberName(GET_DECLARED_IMPLICITS,getReflect().getAliasGetDeclaredImplicits()),
                new KeyValueMemberName(GET_DECLARED_TRUE_OPERATORS,getReflect().getAliasGetDeclaredTrueOperators()),
                new KeyValueMemberName(GET_DECLARED_FALSE_OPERATORS,getReflect().getAliasGetDeclaredFalseOperators()),
                new KeyValueMemberName(GET_DECLARED_METHODS,getReflect().getAliasGetDeclaredMethods()),
                new KeyValueMemberName(GET_DECLARED_STATIC_METHODS,getReflect().getAliasGetDeclaredStaticMethods()),
                new KeyValueMemberName(GET_ENCLOSING_TYPE,getReflect().getAliasGetEnclosingType()),
                new KeyValueMemberName(GET_ENUM_CONSTANTS,getReflect().getAliasGetEnumConstants()),
                new KeyValueMemberName(GET_GENERIC_BOUNDS,getReflect().getAliasGetGenericBounds()),
                new KeyValueMemberName(GET_GENERIC_INTERFACES,getReflect().getAliasGetGenericInterfaces()),
                new KeyValueMemberName(GET_GENERIC_SUPER_CLASS,getReflect().getAliasGetGenericSuperClass()),
                new KeyValueMemberName(GET_GENERIC_VARIABLE_OWNER,getReflect().getAliasGetGenericVariableOwner()),
                new KeyValueMemberName(GET_INTERFACES,getReflect().getAliasGetInterfaces()),
                new KeyValueMemberName(ARRAY_GET_LENGTH,getReflect().getAliasArrayGetLength()),
                new KeyValueMemberName(GET_LOWER_BOUNDS,getReflect().getAliasGetLowerBounds()),
                new KeyValueMemberName(GET_FILE_NAME,getReflect().getAliasGetFileName()),
                new KeyValueMemberName(GET_NAME,getReflect().getAliasGetName()),
                new KeyValueMemberName(GET_OPERATORS,getReflect().getAliasGetOperators()),
                new KeyValueMemberName(GET_PRETTY_NAME,getReflect().getAliasGetPrettyName()),
                new KeyValueMemberName(GET_PRETTY_SINGLE_NAME,getReflect().getAliasGetPrettySingleName()),
                new KeyValueMemberName(GET_SUPER_CLASS,getReflect().getAliasGetSuperClass()),
                new KeyValueMemberName(GET_TYPE_PARAMETERS,getReflect().getAliasGetTypeParameters()),
                new KeyValueMemberName(GET_UPPER_BOUNDS,getReflect().getAliasGetUpperBounds()),
                new KeyValueMemberName(GET_VARIABLE_OWNER,getReflect().getAliasGetVariableOwner()),
                new KeyValueMemberName(INIT,getReflect().getAliasInit()),
                new KeyValueMemberName(IS_ABSTRACT,getReflect().getAliasIsAbstract()),
                new KeyValueMemberName(IS_ANNOTATION,getReflect().getAliasIsAnnotation()),
                new KeyValueMemberName(IS_ARRAY,getReflect().getAliasIsArray()),
                new KeyValueMemberName(IS_ASSIGNABLE_FROM,getReflect().getAliasIsAssignableFrom()),
                new KeyValueMemberName(IS_SPE_CLASS,getReflect().getAliasIsSpecialClass()),
                new KeyValueMemberName(IS_CLASS,getReflect().getAliasIsClass()),
                new KeyValueMemberName(IS_ENUM,getReflect().getAliasIsEnum()),
                new KeyValueMemberName(IS_FINAL,getReflect().getAliasIsFinal()),
                new KeyValueMemberName(IS_TYPE_VARIABLE,getReflect().getAliasIsTypeVariable()),
                new KeyValueMemberName(IS_VARIABLE,getReflect().getAliasIsVariable()),
                new KeyValueMemberName(IS_INSTANCE,getReflect().getAliasIsInstance()),
                new KeyValueMemberName(IS_INTERFACE,getReflect().getAliasIsInterface()),
                new KeyValueMemberName(IS_PACKAGE,getReflect().getAliasIsPackage()),
                new KeyValueMemberName(IS_PRIMITIVE,getReflect().getAliasIsPrimitive()),
                new KeyValueMemberName(IS_PRIVATE,getReflect().getAliasIsPrivate()),
                new KeyValueMemberName(IS_PROTECTED,getReflect().getAliasIsProtected()),
                new KeyValueMemberName(IS_PUBLIC,getReflect().getAliasIsPublic()),
                new KeyValueMemberName(IS_STATIC,getReflect().getAliasIsStatic()),
                new KeyValueMemberName(IS_REF_TYPE,getReflect().getAliasIsRefType()),
                new KeyValueMemberName(IS_WILD_CARD,getReflect().getAliasIsWildCard()),
                new KeyValueMemberName(MAKE_ARRAY,getReflect().getAliasMakeArray()),
                new KeyValueMemberName(MAKE_GENERIC,getReflect().getAliasMakeGeneric()),
                new KeyValueMemberName(MAKE_REF_TYPE,getReflect().getAliasMakeRef()),
                new KeyValueMemberName(MAKE_WILD_CARD,getReflect().getAliasMakeWildCard()),
                new KeyValueMemberName(ARRAY_NEW_INSTANCE,getReflect().getAliasArrayNewInstance()),
                new KeyValueMemberName(ARRAY_SET,getReflect().getAliasArraySet())));
        map_.addEntry(getReflect().getAliasConstructor(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_ANNOTATIONS,getReflect().getAliasGetAnnotations()),
                new KeyValueMemberName(GET_ANNOTATIONS_PARAMETERS,getReflect().getAliasGetAnnotationsParameters()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_TYPES,getReflect().getAliasGetDeclaredAnonymousTypes()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA,getReflect().getAliasGetDeclaredAnonymousLambda()),
                new KeyValueMemberName(GET_DECLARING_CLASS,getReflect().getAliasGetDeclaringClass()),
                new KeyValueMemberName(GET_GENERIC_RETURN_TYPE,getReflect().getAliasGetGenericReturnType()),
                new KeyValueMemberName(GET_FILE_NAME,getReflect().getAliasGetFileName()),
                new KeyValueMemberName(GET_NAME,getReflect().getAliasGetName()),
                new KeyValueMemberName(GET_PARAMETER_NAMES,getReflect().getAliasGetParameterNames()),
                new KeyValueMemberName(GET_PARAMETER_TYPES,getReflect().getAliasGetParameterTypes()),
                new KeyValueMemberName(GET_RETURN_TYPE,getReflect().getAliasGetReturnType()),
                new KeyValueMemberName(IS_PACKAGE,getReflect().getAliasIsPackage()),
                new KeyValueMemberName(IS_PRIVATE,getReflect().getAliasIsPrivate()),
                new KeyValueMemberName(IS_PROTECTED,getReflect().getAliasIsProtected()),
                new KeyValueMemberName(IS_PUBLIC,getReflect().getAliasIsPublic()),
                new KeyValueMemberName(IS_VARARGS,getReflect().getAliasIsVarargs()),
                new KeyValueMemberName(NEW_INSTANCE,getReflect().getAliasNewInstance())));
        map_.addEntry(getReflect().getAliasFct(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(CALL,getReflect().getAliasCall()),
                new KeyValueMemberName(META_INFO,getReflect().getAliasMetaInfo()),
                new KeyValueMemberName(INSTANCE,getReflect().getAliasInstance())));
        map_.addEntry(getReflect().getAliasField(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_ANNOTATIONS,getReflect().getAliasGetAnnotations()),
                new KeyValueMemberName(GET_ANNOTATIONS_PARAMETERS,getReflect().getAliasGetAnnotationsParameters()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_TYPES,getReflect().getAliasGetDeclaredAnonymousTypes()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA,getReflect().getAliasGetDeclaredAnonymousLambda()),
                new KeyValueMemberName(GET_FIELD,getReflect().getAliasGetField()),
                new KeyValueMemberName(GET_DECLARING_CLASS,getReflect().getAliasGetDeclaringClass()),
                new KeyValueMemberName(GET_GENERIC_TYPE,getReflect().getAliasGetGenericType()),
                new KeyValueMemberName(GET_FILE_NAME,getReflect().getAliasGetFileName()),
                new KeyValueMemberName(GET_NAME,getReflect().getAliasGetName()),
                new KeyValueMemberName(GET_TYPE,getReflect().getAliasGetType()),
                new KeyValueMemberName(IS_FINAL,getReflect().getAliasIsFinal()),
                new KeyValueMemberName(IS_PACKAGE,getReflect().getAliasIsPackage()),
                new KeyValueMemberName(IS_PRIVATE,getReflect().getAliasIsPrivate()),
                new KeyValueMemberName(IS_PROTECTED,getReflect().getAliasIsProtected()),
                new KeyValueMemberName(IS_PUBLIC,getReflect().getAliasIsPublic()),
                new KeyValueMemberName(IS_STATIC,getReflect().getAliasIsStatic()),
                new KeyValueMemberName(SET_FIELD,getReflect().getAliasSetField())));
        map_.addEntry(getReflect().getAliasMethod(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_ANNOTATIONS,getReflect().getAliasGetAnnotations()),
                new KeyValueMemberName(GET_ANNOTATIONS_PARAMETERS,getReflect().getAliasGetAnnotationsParameters()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_TYPES,getReflect().getAliasGetDeclaredAnonymousTypes()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA,getReflect().getAliasGetDeclaredAnonymousLambda()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS,getReflect().getAliasGetDeclaredAnonymousLambdaLocalVars()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS,getReflect().getAliasGetDeclaredAnonymousLambdaLoopVars()),
                new KeyValueMemberName(GET_DECLARING_CLASS,getReflect().getAliasGetDeclaringClass()),
                new KeyValueMemberName(GET_DEFAULT_VALUE,getReflect().getAliasGetDefaultValue()),
                new KeyValueMemberName(GET_GENERIC_RETURN_TYPE,getReflect().getAliasGetGenericReturnType()),
                new KeyValueMemberName(GET_FILE_NAME,getReflect().getAliasGetFileName()),
                new KeyValueMemberName(GET_NAME,getReflect().getAliasGetName()),
                new KeyValueMemberName(GET_PARAMETER_NAMES,getReflect().getAliasGetParameterNames()),
                new KeyValueMemberName(GET_PARAMETER_TYPES,getReflect().getAliasGetParameterTypes()),
                new KeyValueMemberName(GET_RETURN_TYPE,getReflect().getAliasGetReturnType()),
                new KeyValueMemberName(INVOKE,getReflect().getAliasInvoke()),
                new KeyValueMemberName(INVOKE_DIRECT,getReflect().getAliasInvokeDirect()),
                new KeyValueMemberName(IS_ABSTRACT,getReflect().getAliasIsAbstract()),
                new KeyValueMemberName(IS_FINAL,getReflect().getAliasIsFinal()),
                new KeyValueMemberName(IS_NORMAL,getReflect().getAliasIsNormal()),
                new KeyValueMemberName(IS_PACKAGE,getReflect().getAliasIsPackage()),
                new KeyValueMemberName(IS_PRIVATE,getReflect().getAliasIsPrivate()),
                new KeyValueMemberName(IS_PROTECTED,getReflect().getAliasIsProtected()),
                new KeyValueMemberName(IS_PUBLIC,getReflect().getAliasIsPublic()),
                new KeyValueMemberName(IS_STATIC,getReflect().getAliasIsStatic()),
                new KeyValueMemberName(IS_STATIC_CALL,getReflect().getAliasIsStaticCall()),
                new KeyValueMemberName(IS_INSTANCE_METHOD,getReflect().getAliasIsInstanceMethod()),
                new KeyValueMemberName(IS_VARARGS,getReflect().getAliasIsVarargs())));
        map_.addEntry(getCoreNames().getAliasObjectsUtil(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(SAME_REF, getCoreNames().getAliasSameRef()),
                new KeyValueMemberName(GET_PARENT, getCoreNames().getAliasGetParent()),
                new KeyValueMemberName(SET_PARENT, getCoreNames().getAliasSetParent())));
        map_.addEntry(getCoreNames().getAliasStringUtil(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(STRING_UTIL_VALUE_OF, getCoreNames().getAliasStringUtilValueOf())));
        map_.addEntry(getCoreNames().getAliasResources(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(READ_RESOURCES_NAMES, getCoreNames().getAliasReadResourcesNames()),
                new KeyValueMemberName(READ_RESOURCES_NAMES_LENGTH, getCoreNames().getAliasReadResourcesNamesLength()),
                new KeyValueMemberName(READ_RESOURCES, getCoreNames().getAliasReadResources()),
                new KeyValueMemberName(READ_RESOURCES_INDEX, getCoreNames().getAliasReadResourcesIndex())));
        map_.addEntry(getPredefTypes().getAliasEnumType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ENUM_NAME, getPredefTypes().getAliasEnumName()),
                new KeyValueMemberName(ENUM_ORDINAL, getPredefTypes().getAliasEnumOrdinal()),
                new KeyValueMemberName(ENUM_PRED_VALUE_OF, getPredefTypes().getAliasEnumPredValueOf()),
                new KeyValueMemberName(ENUM_VALUES, getPredefTypes().getAliasEnumValues())));
        map_.addEntry(getPredefTypes().getAliasSeedDoubleGenerator(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(SEED_GET, getPredefTypes().getAliasSeedGet())));
        map_.addEntry(getPredefTypes().getAliasSeedGenerator(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(SEED_GET, getPredefTypes().getAliasSeedGet())));
        map_.addEntry(getCoreNames().getAliasEnums(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(NAME, getCoreNames().getAliasName()),
                new KeyValueMemberName(ORDINAL, getCoreNames().getAliasOrdinal())));
        map_.addEntry(getPredefTypes().getAliasIterable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERATOR,getPredefTypes().getAliasIterator())));
        map_.addEntry(getPredefTypes().getAliasIteratorType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(HAS_NEXT,getPredefTypes().getAliasHasNext()),
                new KeyValueMemberName(NEXT,getPredefTypes().getAliasNext())));
        map_.addEntry(getPredefTypes().getAliasIterableTable(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ITERATOR_TABLE,getPredefTypes().getAliasIteratorTable())));
        map_.addEntry(getPredefTypes().getAliasIteratorTableType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(HAS_NEXT_PAIR,getPredefTypes().getAliasHasNextPair()),
                new KeyValueMemberName(NEXT_PAIR,getPredefTypes().getAliasNextPair())));
        map_.addEntry(getPredefTypes().getAliasPairType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_FIRST,getPredefTypes().getAliasGetFirst()),
                new KeyValueMemberName(GET_SECOND,getPredefTypes().getAliasGetSecond())));
        map_.addEntry(getStackElt().getAliasStackTraceElement(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(CURRENT_STACK, getStackElt().getAliasCurrentStack()),
                new KeyValueMemberName(CURRENT_FULL_STACK,getStackElt().getAliasCurrentFullStack()),
                new KeyValueMemberName(STACK_TRACE_ELEMENT_TO_STRING, getStackElt().getAliasStackTraceElementToString())));
        map_.addEntry(getMathRef().getAliasMath(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ABS, getMathRef().getAliasAbs()),
                new KeyValueMemberName(MAX, getMathRef().getAliasMax()),
                new KeyValueMemberName(MIN, getMathRef().getAliasMin()),
                new KeyValueMemberName(MOD,getMathRef().getAliasMod()),
                new KeyValueMemberName(QUOT,getMathRef().getAliasQuot()),
                new KeyValueMemberName(BIN_MOD,getMathRef().getAliasBinMod()),
                new KeyValueMemberName(BIN_QUOT,getMathRef().getAliasBinQuot()),
                new KeyValueMemberName(PLUS,getMathRef().getAliasPlus()),
                new KeyValueMemberName(MINUS,getMathRef().getAliasMinus()),
                new KeyValueMemberName(MULT,getMathRef().getAliasMult()),
                new KeyValueMemberName(NEG_BIN,getMathRef().getAliasNegBin()),
                new KeyValueMemberName(NEG,getMathRef().getAliasNeg()),
                new KeyValueMemberName(AND,getMathRef().getAliasAnd()),
                new KeyValueMemberName(OR,getMathRef().getAliasOr()),
                new KeyValueMemberName(XOR,getMathRef().getAliasXor()),
                new KeyValueMemberName(LE,getMathRef().getAliasLe()),
                new KeyValueMemberName(GE,getMathRef().getAliasGe()),
                new KeyValueMemberName(LT,getMathRef().getAliasLt()),
                new KeyValueMemberName(GT,getMathRef().getAliasGt()),
                new KeyValueMemberName(SHIFT_LEFT,getMathRef().getAliasShiftLeft()),
                new KeyValueMemberName(SHIFT_RIGHT,getMathRef().getAliasShiftRight()),
                new KeyValueMemberName(BIT_SHIFT_LEFT,getMathRef().getAliasBitShiftLeft()),
                new KeyValueMemberName(BIT_SHIFT_RIGHT,getMathRef().getAliasBitShiftRight()),
                new KeyValueMemberName(ROTATE_LEFT,getMathRef().getAliasRotateLeft()),
                new KeyValueMemberName(ROTATE_RIGHT,getMathRef().getAliasRotateRight()),
                new KeyValueMemberName(RANDOM,getMathRef().getAliasRandom()),
                new KeyValueMemberName(SEED,getMathRef().getAliasSeed())));
        map_.addEntry(getCharSeq().getAliasReplacement(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_NEW_STRING, getCharSeq().getAliasGetNewString()),
                new KeyValueMemberName(GET_OLD_STRING,getCharSeq().getAliasGetOldString())));
        map_.addEntry(getNbAlias().getAliasBoolean(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BOOLEAN_VALUE, getNbAlias().getAliasBooleanValue()),
                new KeyValueMemberName(COMPARE,getNbAlias().getAliasCompare()),
                new KeyValueMemberName(COMPARE_TO,getNbAlias().getAliasCompareTo()),
                new KeyValueMemberName(EQUALS,getNbAlias().getAliasEquals()),
                new KeyValueMemberName(PARSE_BOOLEAN,getNbAlias().getAliasParseBoolean()),
                new KeyValueMemberName(TO_STRING_METHOD,getNbAlias().getAliasToStringMethod()),
                new KeyValueMemberName(VALUE_OF_METHOD,getNbAlias().getAliasValueOfMethod())));
        map_.addEntry(getNbAlias().getAliasByte(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BYTE_VALUE,getNbAlias().getAliasByteValue()),
                new KeyValueMemberName(COMPARE,getNbAlias().getAliasCompare()),
                new KeyValueMemberName(COMPARE_TO,getNbAlias().getAliasCompareTo()),
                new KeyValueMemberName(DOUBLE_VALUE,getNbAlias().getAliasDoubleValue()),
                new KeyValueMemberName(EQUALS,getNbAlias().getAliasEquals()),
                new KeyValueMemberName(FLOAT_VALUE,getNbAlias().getAliasFloatValue()),
                new KeyValueMemberName(INT_VALUE,getNbAlias().getAliasIntValue()),
                new KeyValueMemberName(LONG_VALUE,getNbAlias().getAliasLongValue()),
                new KeyValueMemberName(SHORT_VALUE,getNbAlias().getAliasShortValue()),
                new KeyValueMemberName(TO_STRING_METHOD,getNbAlias().getAliasToStringMethod()),
                new KeyValueMemberName(SIGNUM,getNbAlias().getAliasSignum()),
                new KeyValueMemberName(BIN,getNbAlias().getAliasToBinString()),
                new KeyValueMemberName(OCT,getNbAlias().getAliasToOctString()),
                new KeyValueMemberName(HEX,getNbAlias().getAliasToHexString()),
                new KeyValueMemberName(PARSE_BYTE,getNbAlias().getAliasParseByte()),
                new KeyValueMemberName(PARSE_BYTE_OR_NULL,getNbAlias().getAliasParseByteOrNull())));
        map_.addEntry(getCharSeq().getAliasCharSequence(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(CHAR_AT, getCharSeq().getAliasCharAt()),
                new KeyValueMemberName(CHAR_SEQUENCE_EQUALS, getCharSeq().getAliasCharSequenceEquals()),
                new KeyValueMemberName(CHAR_SEQUENCE_COMPARE_TO, getCharSeq().getAliasCharSequenceCompareTo()),
                new KeyValueMemberName(CONTAINS,getCharSeq().getAliasContains()),
                new KeyValueMemberName(ENDS_WITH,getCharSeq().getAliasEndsWith()),
                new KeyValueMemberName(FORMAT,getCharSeq().getAliasFormat()),
                new KeyValueMemberName(GET_BYTES,getCharSeq().getAliasGetBytes()),
                new KeyValueMemberName(INDEX_OF,getCharSeq().getAliasIndexOf()),
                new KeyValueMemberName(IS_EMPTY,getCharSeq().getAliasIsEmpty()),
                new KeyValueMemberName(LAST_INDEX_OF,getCharSeq().getAliasLastIndexOf()),
                new KeyValueMemberName(LENGTH,getCharSeq().getAliasLength()),
                new KeyValueMemberName(REGION_MATCHES,getCharSeq().getAliasRegionMatches()),
                new KeyValueMemberName(SPLIT,getCharSeq().getAliasSplit()),
                new KeyValueMemberName(SPLIT_CHARS,getCharSeq().getAliasSplitChars()),
                new KeyValueMemberName(SPLIT_STRINGS,getCharSeq().getAliasSplitStrings()),
                new KeyValueMemberName(STARTS_WITH,getCharSeq().getAliasStartsWith()),
                new KeyValueMemberName(SUB_SEQUENCE,getCharSeq().getAliasSubSequence()),
                new KeyValueMemberName(SUBSTRING,getCharSeq().getAliasSubstring()),
                new KeyValueMemberName(TO_CHAR_ARRAY,getCharSeq().getAliasToCharArray()),
                new KeyValueMemberName(CHAR_SEQUENCE_TO_STRING, getCharSeq().getAliasCharSequenceToString()),
                new KeyValueMemberName(TRIM,getCharSeq().getAliasTrim())));
        map_.addEntry(getNbAlias().getAliasCharacter(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BYTE_VALUE,getNbAlias().getAliasByteValue()),
                new KeyValueMemberName(DOUBLE_VALUE,getNbAlias().getAliasDoubleValue()),
                new KeyValueMemberName(EQUALS,getNbAlias().getAliasEquals()),
                new KeyValueMemberName(FLOAT_VALUE,getNbAlias().getAliasFloatValue()),
                new KeyValueMemberName(INT_VALUE,getNbAlias().getAliasIntValue()),
                new KeyValueMemberName(LONG_VALUE,getNbAlias().getAliasLongValue()),
                new KeyValueMemberName(PARSE_INT,getNbAlias().getAliasParseInt()),
                new KeyValueMemberName(PARSE_INT_OR_NULL,getNbAlias().getAliasParseIntOrNull()),
                new KeyValueMemberName(SHORT_VALUE,getNbAlias().getAliasShortValue()),
                new KeyValueMemberName(CHAR_VALUE,getNbAlias().getAliasCharValue()),
                new KeyValueMemberName(COMPARE,getNbAlias().getAliasCompare()),
                new KeyValueMemberName(COMPARE_TO,getNbAlias().getAliasCompareTo()),
                new KeyValueMemberName(DIGIT,getNbAlias().getAliasDigit()),
                new KeyValueMemberName(FOR_DIGIT,getNbAlias().getAliasForDigit()),
                new KeyValueMemberName(GET_CHAR_TYPE,getNbAlias().getAliasGetCharType()),
                new KeyValueMemberName(IS_DIGIT,getNbAlias().getAliasIsDigit()),
                new KeyValueMemberName(GET_DIRECTIONALITY,getNbAlias().getAliasGetDirectionality()),
                new KeyValueMemberName(IS_LETTER,getNbAlias().getAliasIsLetter()),
                new KeyValueMemberName(IS_LETTER_OR_DIGIT,getNbAlias().getAliasIsLetterOrDigit()),
                new KeyValueMemberName(IS_LOWER_CASE,getNbAlias().getAliasIsLowerCase()),
                new KeyValueMemberName(IS_SPACE,getNbAlias().getAliasIsSpace()),
                new KeyValueMemberName(IS_UPPER_CASE, getNbAlias().getAliasIsUpperCase()),
                new KeyValueMemberName(IS_WHITESPACE,getNbAlias().getAliasIsWhitespace()),
                new KeyValueMemberName(IS_WORD_CHAR,getNbAlias().getAliasIsWordChar()),
                new KeyValueMemberName(TO_LOWER_CASE_CHAR,getNbAlias().getAliasToLowerCaseChar()),
                new KeyValueMemberName(TO_STRING_METHOD, getNbAlias().getAliasToStringMethod()),
                new KeyValueMemberName(SIGNUM,getNbAlias().getAliasSignum()),
                new KeyValueMemberName(BIN,getNbAlias().getAliasToBinString()),
                new KeyValueMemberName(OCT,getNbAlias().getAliasToOctString()),
                new KeyValueMemberName(HEX,getNbAlias().getAliasToHexString()),
                new KeyValueMemberName(TO_UPPER_CASE_CHAR,getNbAlias().getAliasToUpperCaseChar())));
        map_.addEntry(getNbAlias().getAliasDouble(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BYTE_VALUE,getNbAlias().getAliasByteValue()),
                new KeyValueMemberName(COMPARE,getNbAlias().getAliasCompare()),
                new KeyValueMemberName(COMPARE_TO,getNbAlias().getAliasCompareTo()),
                new KeyValueMemberName(DOUBLE_VALUE,getNbAlias().getAliasDoubleValue()),
                new KeyValueMemberName(EQUALS,getNbAlias().getAliasEquals()),
                new KeyValueMemberName(FLOAT_VALUE,getNbAlias().getAliasFloatValue()),
                new KeyValueMemberName(INT_VALUE,getNbAlias().getAliasIntValue()),
                new KeyValueMemberName(LONG_VALUE,getNbAlias().getAliasLongValue()),
                new KeyValueMemberName(SHORT_VALUE,getNbAlias().getAliasShortValue()),
                new KeyValueMemberName(TO_STRING_METHOD,getNbAlias().getAliasToStringMethod()),
                new KeyValueMemberName(IS_INFINITE,getNbAlias().getAliasIsInfinite()),
                new KeyValueMemberName(IS_NAN,getNbAlias().getAliasIsNan()),
                new KeyValueMemberName(PARSE_DOUBLE,getNbAlias().getAliasParseDouble()),
                new KeyValueMemberName(PARSE_DOUBLE_OR_NULL,getNbAlias().getAliasParseDoubleOrNull())));
        map_.addEntry(getNbAlias().getAliasFloat(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BYTE_VALUE,getNbAlias().getAliasByteValue()),
                new KeyValueMemberName(COMPARE,getNbAlias().getAliasCompare()),
                new KeyValueMemberName(COMPARE_TO,getNbAlias().getAliasCompareTo()),
                new KeyValueMemberName(DOUBLE_VALUE,getNbAlias().getAliasDoubleValue()),
                new KeyValueMemberName(EQUALS,getNbAlias().getAliasEquals()),
                new KeyValueMemberName(FLOAT_VALUE,getNbAlias().getAliasFloatValue()),
                new KeyValueMemberName(INT_VALUE,getNbAlias().getAliasIntValue()),
                new KeyValueMemberName(LONG_VALUE,getNbAlias().getAliasLongValue()),
                new KeyValueMemberName(SHORT_VALUE,getNbAlias().getAliasShortValue()),
                new KeyValueMemberName(TO_STRING_METHOD,getNbAlias().getAliasToStringMethod()),
                new KeyValueMemberName(IS_INFINITE,getNbAlias().getAliasIsInfinite()),
                new KeyValueMemberName(IS_NAN,getNbAlias().getAliasIsNan()),
                new KeyValueMemberName(PARSE_FLOAT,getNbAlias().getAliasParseFloat()),
                new KeyValueMemberName(PARSE_FLOAT_OR_NULL,getNbAlias().getAliasParseFloatOrNull())));
        map_.addEntry(getNbAlias().getAliasInteger(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BYTE_VALUE,getNbAlias().getAliasByteValue()),
                new KeyValueMemberName(COMPARE,getNbAlias().getAliasCompare()),
                new KeyValueMemberName(COMPARE_TO,getNbAlias().getAliasCompareTo()),
                new KeyValueMemberName(DOUBLE_VALUE,getNbAlias().getAliasDoubleValue()),
                new KeyValueMemberName(EQUALS,getNbAlias().getAliasEquals()),
                new KeyValueMemberName(FLOAT_VALUE,getNbAlias().getAliasFloatValue()),
                new KeyValueMemberName(INT_VALUE,getNbAlias().getAliasIntValue()),
                new KeyValueMemberName(LONG_VALUE,getNbAlias().getAliasLongValue()),
                new KeyValueMemberName(SHORT_VALUE,getNbAlias().getAliasShortValue()),
                new KeyValueMemberName(TO_STRING_METHOD,getNbAlias().getAliasToStringMethod()),
                new KeyValueMemberName(SIGNUM,getNbAlias().getAliasSignum()),
                new KeyValueMemberName(BIN,getNbAlias().getAliasToBinString()),
                new KeyValueMemberName(OCT,getNbAlias().getAliasToOctString()),
                new KeyValueMemberName(HEX,getNbAlias().getAliasToHexString()),
                new KeyValueMemberName(PARSE_INT,getNbAlias().getAliasParseInt()),
                new KeyValueMemberName(PARSE_INT_OR_NULL,getNbAlias().getAliasParseIntOrNull())));
        map_.addEntry(getNbAlias().getAliasLong(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BYTE_VALUE,getNbAlias().getAliasByteValue()),
                new KeyValueMemberName(COMPARE,getNbAlias().getAliasCompare()),
                new KeyValueMemberName(COMPARE_TO,getNbAlias().getAliasCompareTo()),
                new KeyValueMemberName(DOUBLE_VALUE,getNbAlias().getAliasDoubleValue()),
                new KeyValueMemberName(EQUALS,getNbAlias().getAliasEquals()),
                new KeyValueMemberName(FLOAT_VALUE,getNbAlias().getAliasFloatValue()),
                new KeyValueMemberName(INT_VALUE,getNbAlias().getAliasIntValue()),
                new KeyValueMemberName(LONG_VALUE,getNbAlias().getAliasLongValue()),
                new KeyValueMemberName(SHORT_VALUE,getNbAlias().getAliasShortValue()),
                new KeyValueMemberName(TO_STRING_METHOD,getNbAlias().getAliasToStringMethod()),
                new KeyValueMemberName(SIGNUM,getNbAlias().getAliasSignum()),
                new KeyValueMemberName(BIN,getNbAlias().getAliasToBinString()),
                new KeyValueMemberName(OCT,getNbAlias().getAliasToOctString()),
                new KeyValueMemberName(HEX,getNbAlias().getAliasToHexString()),
                new KeyValueMemberName(PARSE_LONG,getNbAlias().getAliasParseLong()),
                new KeyValueMemberName(PARSE_LONG_OR_NULL,getNbAlias().getAliasParseLongOrNull())));
        map_.addEntry(getNbAlias().getAliasNumber(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BYTE_VALUE,getNbAlias().getAliasByteValue()),
                new KeyValueMemberName(COMPARE,getNbAlias().getAliasCompare()),
                new KeyValueMemberName(COMPARE_TO,getNbAlias().getAliasCompareTo()),
                new KeyValueMemberName(DOUBLE_VALUE,getNbAlias().getAliasDoubleValue()),
                new KeyValueMemberName(EQUALS,getNbAlias().getAliasEquals()),
                new KeyValueMemberName(FLOAT_VALUE,getNbAlias().getAliasFloatValue()),
                new KeyValueMemberName(INT_VALUE,getNbAlias().getAliasIntValue()),
                new KeyValueMemberName(LONG_VALUE,getNbAlias().getAliasLongValue()),
                new KeyValueMemberName(SHORT_VALUE,getNbAlias().getAliasShortValue()),
                new KeyValueMemberName(TO_STRING_METHOD,getNbAlias().getAliasToStringMethod())));
        map_.addEntry(getNbAlias().getAliasShort(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(BYTE_VALUE,getNbAlias().getAliasByteValue()),
                new KeyValueMemberName(COMPARE,getNbAlias().getAliasCompare()),
                new KeyValueMemberName(COMPARE_TO,getNbAlias().getAliasCompareTo()),
                new KeyValueMemberName(DOUBLE_VALUE,getNbAlias().getAliasDoubleValue()),
                new KeyValueMemberName(EQUALS,getNbAlias().getAliasEquals()),
                new KeyValueMemberName(FLOAT_VALUE,getNbAlias().getAliasFloatValue()),
                new KeyValueMemberName(INT_VALUE,getNbAlias().getAliasIntValue()),
                new KeyValueMemberName(LONG_VALUE,getNbAlias().getAliasLongValue()),
                new KeyValueMemberName(SHORT_VALUE,getNbAlias().getAliasShortValue()),
                new KeyValueMemberName(TO_STRING_METHOD,getNbAlias().getAliasToStringMethod()),
                new KeyValueMemberName(SIGNUM,getNbAlias().getAliasSignum()),
                new KeyValueMemberName(BIN,getNbAlias().getAliasToBinString()),
                new KeyValueMemberName(OCT,getNbAlias().getAliasToOctString()),
                new KeyValueMemberName(HEX,getNbAlias().getAliasToHexString()),
                new KeyValueMemberName(PARSE_SHORT,getNbAlias().getAliasParseShort()),
                new KeyValueMemberName(PARSE_SHORT_OR_NULL,getNbAlias().getAliasParseShortOrNull())));
        map_.addEntry(getCharSeq().getAliasString(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(CHAR_SEQUENCE_EQUALS,getCharSeq().getAliasCharSequenceEquals()),
                new KeyValueMemberName(CHAR_SEQUENCE_COMPARE_TO, getCharSeq().getAliasCharSequenceCompareTo()),
                new KeyValueMemberName(CHAR_AT, getCharSeq().getAliasCharAt()),
                new KeyValueMemberName(CONTAINS,getCharSeq().getAliasContains()),
                new KeyValueMemberName(ENDS_WITH,getCharSeq().getAliasEndsWith()),
                new KeyValueMemberName(FORMAT,getCharSeq().getAliasFormat()),
                new KeyValueMemberName(GET_BYTES,getCharSeq().getAliasGetBytes()),
                new KeyValueMemberName(INDEX_OF,getCharSeq().getAliasIndexOf()),
                new KeyValueMemberName(IS_EMPTY,getCharSeq().getAliasIsEmpty()),
                new KeyValueMemberName(LAST_INDEX_OF,getCharSeq().getAliasLastIndexOf()),
                new KeyValueMemberName(LENGTH,getCharSeq().getAliasLength()),
                new KeyValueMemberName(REGION_MATCHES,getCharSeq().getAliasRegionMatches()),
                new KeyValueMemberName(REPLACE_STRING,getCharSeq().getAliasReplaceString()),
                new KeyValueMemberName(SPLIT,getCharSeq().getAliasSplit()),
                new KeyValueMemberName(SPLIT_CHARS,getCharSeq().getAliasSplitChars()),
                new KeyValueMemberName(SPLIT_STRINGS,getCharSeq().getAliasSplitStrings()),
                new KeyValueMemberName(STARTS_WITH,getCharSeq().getAliasStartsWith()),
                new KeyValueMemberName(SUB_SEQUENCE,getCharSeq().getAliasSubSequence()),
                new KeyValueMemberName(SUBSTRING,getCharSeq().getAliasSubstring()),
                new KeyValueMemberName(TO_CHAR_ARRAY,getCharSeq().getAliasToCharArray()),
                new KeyValueMemberName(CHAR_SEQUENCE_TO_STRING, getCharSeq().getAliasCharSequenceToString()),
                new KeyValueMemberName(TRIM,getCharSeq().getAliasTrim()),
                new KeyValueMemberName(STRING_COMPARE, getCharSeq().getAliasStringCompare()),
                new KeyValueMemberName(COMPARE_TO_IGNORE_CASE,getCharSeq().getAliasCompareToIgnoreCase()),
                new KeyValueMemberName(EQUALS_IGNORE_CASE,getCharSeq().getAliasEqualsIgnoreCase()),
                new KeyValueMemberName(REPLACE_MULTIPLE,getCharSeq().getAliasReplaceMultiple()),
                new KeyValueMemberName(TO_LOWER_CASE,getCharSeq().getAliasToLowerCase()),
                new KeyValueMemberName(TO_UPPER_CASE,getCharSeq().getAliasToUpperCase()),
                new KeyValueMemberName(STRING_VALUE_OF, getCharSeq().getAliasStringValueOf())));
        map_.addEntry(getCharSeq().getAliasStringBuilder(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(CHAR_SEQUENCE_EQUALS, getCharSeq().getAliasCharSequenceEquals()),
                new KeyValueMemberName(CHAR_SEQUENCE_COMPARE_TO, getCharSeq().getAliasCharSequenceCompareTo()),
                new KeyValueMemberName(CHAR_AT,getCharSeq().getAliasCharAt()),
                new KeyValueMemberName(CONTAINS,getCharSeq().getAliasContains()),
                new KeyValueMemberName(ENDS_WITH,getCharSeq().getAliasEndsWith()),
                new KeyValueMemberName(FORMAT,getCharSeq().getAliasFormat()),
                new KeyValueMemberName(GET_BYTES,getCharSeq().getAliasGetBytes()),
                new KeyValueMemberName(INDEX_OF,getCharSeq().getAliasIndexOf()),
                new KeyValueMemberName(IS_EMPTY,getCharSeq().getAliasIsEmpty()),
                new KeyValueMemberName(LAST_INDEX_OF,getCharSeq().getAliasLastIndexOf()),
                new KeyValueMemberName(LENGTH,getCharSeq().getAliasLength()),
                new KeyValueMemberName(REGION_MATCHES,getCharSeq().getAliasRegionMatches()),
                new KeyValueMemberName(REPLACE,getCharSeq().getAliasReplace()),
                new KeyValueMemberName(SPLIT,getCharSeq().getAliasSplit()),
                new KeyValueMemberName(SPLIT_CHARS,getCharSeq().getAliasSplitChars()),
                new KeyValueMemberName(SPLIT_STRINGS,getCharSeq().getAliasSplitStrings()),
                new KeyValueMemberName(STARTS_WITH,getCharSeq().getAliasStartsWith()),
                new KeyValueMemberName(SUB_SEQUENCE,getCharSeq().getAliasSubSequence()),
                new KeyValueMemberName(SUBSTRING,getCharSeq().getAliasSubstring()),
                new KeyValueMemberName(TO_CHAR_ARRAY,getCharSeq().getAliasToCharArray()),
                new KeyValueMemberName(CHAR_SEQUENCE_TO_STRING, getCharSeq().getAliasCharSequenceToString()),
                new KeyValueMemberName(TRIM,getCharSeq().getAliasTrim()),
                new KeyValueMemberName(APPEND,getCharSeq().getAliasAppend()),
                new KeyValueMemberName(CAPACITY,getCharSeq().getAliasCapacity()),
                new KeyValueMemberName(CLEAR,getCharSeq().getAliasClear()),
                new KeyValueMemberName(DELETE,getCharSeq().getAliasDelete()),
                new KeyValueMemberName(DELETE_CHAR_AT,getCharSeq().getAliasDeleteCharAt()),
                new KeyValueMemberName(ENSURE_CAPACITY,getCharSeq().getAliasEnsureCapacity()),
                new KeyValueMemberName(INSERT,getCharSeq().getAliasInsert()),
                new KeyValueMemberName(REVERSE,getCharSeq().getAliasReverse()),
                new KeyValueMemberName(SET_CHAR_AT,getCharSeq().getAliasSetCharAt()),
                new KeyValueMemberName(SET_LENGTH,getCharSeq().getAliasSetLength()),
                new KeyValueMemberName(SAME,getCharSeq().getAliasSame()),
                new KeyValueMemberName(TRIM_TO_SIZE,getCharSeq().getAliasTrimToSize())));
        return map_;
    }
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames() {
        CustList<CustList<KeyValueMemberName>> map_ = new CustList<CustList<KeyValueMemberName>>();
        map_.add(new CustList<KeyValueMemberName>(
                        new KeyValueMemberName(AliasParamPredefinedTypes.SEED_GENERATOR_0_GET_0,getPredefTypes().getParams().getAliasSeedGenerator0Get0())
                )
        );
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_SUB_SEQUENCE_0, getCharSeq().getParams().getAliasCharSequence0SubSequence0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_SUB_SEQUENCE_1, getCharSeq().getParams().getAliasCharSequence0SubSequence1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_CHAR_AT_0, getCharSeq().getParams().getAliasCharSequence0CharAt0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_SUBSTRING_0, getCharSeq().getParams().getAliasCharSequence0Substring0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_SUBSTRING_1, getCharSeq().getParams().getAliasCharSequence0Substring1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_SUBSTRING_0, getCharSeq().getParams().getAliasCharSequence1Substring0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_COMPARE_TO_0, getCharSeq().getParams().getAliasCharSequence0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_CONTAINS_0, getCharSeq().getParams().getAliasCharSequence0Contains0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_STARTS_WITH_0, getCharSeq().getParams().getAliasCharSequence0StartsWith0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_STARTS_WITH_0, getCharSeq().getParams().getAliasCharSequence1StartsWith0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_STARTS_WITH_1, getCharSeq().getParams().getAliasCharSequence1StartsWith1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_ENDS_WITH_0, getCharSeq().getParams().getAliasCharSequence0EndsWith0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_INDEX_OF_0, getCharSeq().getParams().getAliasCharSequence0IndexOf0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_INDEX_OF_0, getCharSeq().getParams().getAliasCharSequence1IndexOf0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_INDEX_OF_1, getCharSeq().getParams().getAliasCharSequence1IndexOf1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_2_INDEX_OF_0, getCharSeq().getParams().getAliasCharSequence2IndexOf0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_3_INDEX_OF_0, getCharSeq().getParams().getAliasCharSequence3IndexOf0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_3_INDEX_OF_1, getCharSeq().getParams().getAliasCharSequence3IndexOf1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_LAST_INDEX_OF_0, getCharSeq().getParams().getAliasCharSequence0LastIndexOf0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_LAST_INDEX_OF_0, getCharSeq().getParams().getAliasCharSequence1LastIndexOf0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_LAST_INDEX_OF_1, getCharSeq().getParams().getAliasCharSequence1LastIndexOf1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_2_LAST_INDEX_OF_0, getCharSeq().getParams().getAliasCharSequence2LastIndexOf0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_3_LAST_INDEX_OF_0, getCharSeq().getParams().getAliasCharSequence3LastIndexOf0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_3_LAST_INDEX_OF_1, getCharSeq().getParams().getAliasCharSequence3LastIndexOf1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_FORMAT_0, getCharSeq().getParams().getAliasCharSequence0Format0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_SPLIT_0, getCharSeq().getParams().getAliasCharSequence0Split0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_0, getCharSeq().getParams().getAliasCharSequence1Split0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_1, getCharSeq().getParams().getAliasCharSequence1Split1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_2_SPLIT_0, getCharSeq().getParams().getAliasCharSequence2Split0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_3_SPLIT_0, getCharSeq().getParams().getAliasCharSequence3Split0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_3_SPLIT_1, getCharSeq().getParams().getAliasCharSequence3Split1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_SPLIT_STRINGS_0, getCharSeq().getParams().getAliasCharSequence0SplitStrings0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_STRINGS_0, getCharSeq().getParams().getAliasCharSequence1SplitStrings0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_STRINGS_1, getCharSeq().getParams().getAliasCharSequence1SplitStrings1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_SPLIT_CHARS_0, getCharSeq().getParams().getAliasCharSequence0SplitChars0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_0, getCharSeq().getParams().getAliasCharSequence0RegionMatches0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_1, getCharSeq().getParams().getAliasCharSequence0RegionMatches1()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_2, getCharSeq().getParams().getAliasCharSequence0RegionMatches2()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_3, getCharSeq().getParams().getAliasCharSequence0RegionMatches3())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_EQUALS_0, getCharSeq().getParams().getAliasCharSequence0Equals0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_EQUALS_1, getCharSeq().getParams().getAliasCharSequence0Equals1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_0_EQUALS_IGNORE_CASE_0, getCharSeq().getParams().getAliasString0EqualsIgnoreCase0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_0_COMPARE_0, getCharSeq().getParams().getAliasString0Compare0()),new KeyValueMemberName(AliasParamCharSequence.STRING_0_COMPARE_1, getCharSeq().getParams().getAliasString0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_0_COMPARE_TO_IGNORE_CASE_0, getCharSeq().getParams().getAliasString0CompareToIgnoreCase0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_0_REPLACE_STRING_0, getCharSeq().getParams().getAliasString0ReplaceString0()),new KeyValueMemberName(AliasParamCharSequence.STRING_0_REPLACE_STRING_1, getCharSeq().getParams().getAliasString0ReplaceString1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_1_REPLACE_STRING_0, getCharSeq().getParams().getAliasString1ReplaceString0()),new KeyValueMemberName(AliasParamCharSequence.STRING_1_REPLACE_STRING_1, getCharSeq().getParams().getAliasString1ReplaceString1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_0_REPLACE_MULTIPLE_0, getCharSeq().getParams().getAliasString0ReplaceMultiple0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_0_REGION_MATCHES_0, getCharSeq().getParams().getAliasString0RegionMatches0()),new KeyValueMemberName(AliasParamCharSequence.STRING_0_REGION_MATCHES_1, getCharSeq().getParams().getAliasString0RegionMatches1()),new KeyValueMemberName(AliasParamCharSequence.STRING_0_REGION_MATCHES_2, getCharSeq().getParams().getAliasString0RegionMatches2()),new KeyValueMemberName(AliasParamCharSequence.STRING_0_REGION_MATCHES_3, getCharSeq().getParams().getAliasString0RegionMatches3()),new KeyValueMemberName(AliasParamCharSequence.STRING_0_REGION_MATCHES_4, getCharSeq().getParams().getAliasString0RegionMatches4())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_0_VALUE_OF_METHOD_0, getCharSeq().getParams().getAliasString0ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_1_VALUE_OF_METHOD_0, getCharSeq().getParams().getAliasString1ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_2_VALUE_OF_METHOD_0, getCharSeq().getParams().getAliasString2ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_3_VALUE_OF_METHOD_0, getCharSeq().getParams().getAliasString3ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_4_VALUE_OF_METHOD_0, getCharSeq().getParams().getAliasString4ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_5_VALUE_OF_METHOD_0, getCharSeq().getParams().getAliasString5ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_6_VALUE_OF_METHOD_0, getCharSeq().getParams().getAliasString6ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_7_VALUE_OF_METHOD_0, getCharSeq().getParams().getAliasString7ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_8_VALUE_OF_METHOD_0, getCharSeq().getParams().getAliasString8ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_9_VALUE_OF_METHOD_0, getCharSeq().getParams().getAliasString9ValueOfMethod0()),new KeyValueMemberName(AliasParamCharSequence.STRING_9_VALUE_OF_METHOD_1, getCharSeq().getParams().getAliasString9ValueOfMethod1()),new KeyValueMemberName(AliasParamCharSequence.STRING_9_VALUE_OF_METHOD_2, getCharSeq().getParams().getAliasString9ValueOfMethod2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_0_STRING_0, getCharSeq().getParams().getAliasString0String0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_1_STRING_0, getCharSeq().getParams().getAliasString1String0()),new KeyValueMemberName(AliasParamCharSequence.STRING_1_STRING_1, getCharSeq().getParams().getAliasString1String1()),new KeyValueMemberName(AliasParamCharSequence.STRING_1_STRING_2, getCharSeq().getParams().getAliasString1String2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_2_STRING_0, getCharSeq().getParams().getAliasString2String0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_3_STRING_0, getCharSeq().getParams().getAliasString3String0()),new KeyValueMemberName(AliasParamCharSequence.STRING_3_STRING_1, getCharSeq().getParams().getAliasString3String1()),new KeyValueMemberName(AliasParamCharSequence.STRING_3_STRING_2, getCharSeq().getParams().getAliasString3String2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_4_STRING_0, getCharSeq().getParams().getAliasString4String0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_APPEND_0, getCharSeq().getParams().getAliasStringBuilder0Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_1_APPEND_0, getCharSeq().getParams().getAliasStringBuilder1Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_2_APPEND_0, getCharSeq().getParams().getAliasStringBuilder2Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_3_APPEND_0, getCharSeq().getParams().getAliasStringBuilder3Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_4_APPEND_0, getCharSeq().getParams().getAliasStringBuilder4Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_5_APPEND_0, getCharSeq().getParams().getAliasStringBuilder5Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_6_APPEND_0, getCharSeq().getParams().getAliasStringBuilder6Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_7_APPEND_0, getCharSeq().getParams().getAliasStringBuilder7Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_8_APPEND_0, getCharSeq().getParams().getAliasStringBuilder8Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_9_APPEND_0, getCharSeq().getParams().getAliasStringBuilder9Append0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_9_APPEND_1, getCharSeq().getParams().getAliasStringBuilder9Append1()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_9_APPEND_2, getCharSeq().getParams().getAliasStringBuilder9Append2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_10_APPEND_0, getCharSeq().getParams().getAliasStringBuilder10Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_11_APPEND_0, getCharSeq().getParams().getAliasStringBuilder11Append0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_11_APPEND_1, getCharSeq().getParams().getAliasStringBuilder11Append1()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_11_APPEND_2, getCharSeq().getParams().getAliasStringBuilder11Append2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_12_APPEND_0, getCharSeq().getParams().getAliasStringBuilder12Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_13_APPEND_0, getCharSeq().getParams().getAliasStringBuilder13Append0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_13_APPEND_1, getCharSeq().getParams().getAliasStringBuilder13Append1()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_13_APPEND_2, getCharSeq().getParams().getAliasStringBuilder13Append2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_DELETE_0, getCharSeq().getParams().getAliasStringBuilder0Delete0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_DELETE_1, getCharSeq().getParams().getAliasStringBuilder0Delete1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_DELETE_CHAR_AT_0, getCharSeq().getParams().getAliasStringBuilder0DeleteCharAt0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_INSERT_0, getCharSeq().getParams().getAliasStringBuilder0Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_INSERT_1, getCharSeq().getParams().getAliasStringBuilder0Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_1_INSERT_0, getCharSeq().getParams().getAliasStringBuilder1Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_1_INSERT_1, getCharSeq().getParams().getAliasStringBuilder1Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_2_INSERT_0, getCharSeq().getParams().getAliasStringBuilder2Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_2_INSERT_1, getCharSeq().getParams().getAliasStringBuilder2Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_3_INSERT_0, getCharSeq().getParams().getAliasStringBuilder3Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_3_INSERT_1, getCharSeq().getParams().getAliasStringBuilder3Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_4_INSERT_0, getCharSeq().getParams().getAliasStringBuilder4Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_4_INSERT_1, getCharSeq().getParams().getAliasStringBuilder4Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_5_INSERT_0, getCharSeq().getParams().getAliasStringBuilder5Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_5_INSERT_1, getCharSeq().getParams().getAliasStringBuilder5Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_6_INSERT_0, getCharSeq().getParams().getAliasStringBuilder6Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_6_INSERT_1, getCharSeq().getParams().getAliasStringBuilder6Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_7_INSERT_0, getCharSeq().getParams().getAliasStringBuilder7Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_7_INSERT_1, getCharSeq().getParams().getAliasStringBuilder7Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_8_INSERT_0, getCharSeq().getParams().getAliasStringBuilder8Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_8_INSERT_1, getCharSeq().getParams().getAliasStringBuilder8Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_9_INSERT_0, getCharSeq().getParams().getAliasStringBuilder9Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_9_INSERT_1, getCharSeq().getParams().getAliasStringBuilder9Insert1()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_9_INSERT_2, getCharSeq().getParams().getAliasStringBuilder9Insert2()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_9_INSERT_3, getCharSeq().getParams().getAliasStringBuilder9Insert3())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_10_INSERT_0, getCharSeq().getParams().getAliasStringBuilder10Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_10_INSERT_1, getCharSeq().getParams().getAliasStringBuilder10Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_11_INSERT_0, getCharSeq().getParams().getAliasStringBuilder11Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_11_INSERT_1, getCharSeq().getParams().getAliasStringBuilder11Insert1()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_11_INSERT_2, getCharSeq().getParams().getAliasStringBuilder11Insert2()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_11_INSERT_3, getCharSeq().getParams().getAliasStringBuilder11Insert3())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_12_INSERT_0, getCharSeq().getParams().getAliasStringBuilder12Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_12_INSERT_1, getCharSeq().getParams().getAliasStringBuilder12Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_13_INSERT_0, getCharSeq().getParams().getAliasStringBuilder13Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_13_INSERT_1, getCharSeq().getParams().getAliasStringBuilder13Insert1()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_13_INSERT_2, getCharSeq().getParams().getAliasStringBuilder13Insert2()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_13_INSERT_3, getCharSeq().getParams().getAliasStringBuilder13Insert3())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_REPLACE_0, getCharSeq().getParams().getAliasStringBuilder0Replace0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_REPLACE_1, getCharSeq().getParams().getAliasStringBuilder0Replace1()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_REPLACE_2, getCharSeq().getParams().getAliasStringBuilder0Replace2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_SET_CHAR_AT_0, getCharSeq().getParams().getAliasStringBuilder0SetCharAt0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_SET_CHAR_AT_1, getCharSeq().getParams().getAliasStringBuilder0SetCharAt1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_SET_LENGTH_0, getCharSeq().getParams().getAliasStringBuilder0SetLength0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_ENSURE_CAPACITY_0, getCharSeq().getParams().getAliasStringBuilder0EnsureCapacity0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_SAME_0, getCharSeq().getParams().getAliasStringBuilder0Same0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_SAME_1, getCharSeq().getParams().getAliasStringBuilder0Same1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_STRING_BUILDER_0, getCharSeq().getParams().getAliasStringBuilder0StringBuilder0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_1_STRING_BUILDER_0, getCharSeq().getParams().getAliasStringBuilder1StringBuilder0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_2_STRING_BUILDER_0, getCharSeq().getParams().getAliasStringBuilder2StringBuilder0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.REPLACEMENT_0_REPLACEMENT_0, getCharSeq().getParams().getAliasReplacement0Replacement0()),new KeyValueMemberName(AliasParamCharSequence.REPLACEMENT_0_REPLACEMENT_1, getCharSeq().getParams().getAliasReplacement0Replacement1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.ERROR_0_CURRENT_STACK_0, getCoreNames().getParams().getAliasError0CurrentStack0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.ERROR_0_TO_STRING_METHOD_0, getCoreNames().getParams().getAliasError0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.ENUMS_0_NAME_0, getCoreNames().getParams().getAliasEnums0Name0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.ENUMS_0_ORDINAL_0, getCoreNames().getParams().getAliasEnums0Ordinal0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.OBJECTS_UTIL_0_SAME_REF_0, getCoreNames().getParams().getAliasObjectsUtil0SameRef0()),new KeyValueMemberName(AliasParamCore.OBJECTS_UTIL_0_SAME_REF_1, getCoreNames().getParams().getAliasObjectsUtil0SameRef1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.OBJECTS_UTIL_0_GET_PARENT_0, getCoreNames().getParams().getAliasObjectsUtil0GetParent0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.OBJECTS_UTIL_0_SET_PARENT_0, getCoreNames().getParams().getAliasObjectsUtil0SetParent0()),new KeyValueMemberName(AliasParamCore.OBJECTS_UTIL_0_SET_PARENT_1, getCoreNames().getParams().getAliasObjectsUtil0SetParent1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.STRING_UTIL_0_VALUE_OF_METHOD_0, getCoreNames().getParams().getAliasStringUtil0ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.RESOURCES_0_READ_RESOURCES_0, getCoreNames().getParams().getAliasResources0ReadResources0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.RESOURCES_0_READ_RESOURCES_INDEX_0, getCoreNames().getParams().getAliasResources0ReadResourcesIndex0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_ABS_0, getMathRef().getParams().getAliasMath0Abs0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_ABS_0, getMathRef().getParams().getAliasMath1Abs0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_MAX_0, getMathRef().getParams().getAliasMath0Max0()),new KeyValueMemberName(AliasParamMath.MATH_0_MAX_1, getMathRef().getParams().getAliasMath0Max1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_MAX_0, getMathRef().getParams().getAliasMath1Max0()),new KeyValueMemberName(AliasParamMath.MATH_1_MAX_1, getMathRef().getParams().getAliasMath1Max1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_MIN_0, getMathRef().getParams().getAliasMath0Min0()),new KeyValueMemberName(AliasParamMath.MATH_0_MIN_1, getMathRef().getParams().getAliasMath0Min1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_MIN_0, getMathRef().getParams().getAliasMath1Min0()),new KeyValueMemberName(AliasParamMath.MATH_1_MIN_1, getMathRef().getParams().getAliasMath1Min1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_QUOT_0, getMathRef().getParams().getAliasMath0Quot0()),new KeyValueMemberName(AliasParamMath.MATH_0_QUOT_1, getMathRef().getParams().getAliasMath0Quot1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_QUOT_0, getMathRef().getParams().getAliasMath1Quot0()),new KeyValueMemberName(AliasParamMath.MATH_1_QUOT_1, getMathRef().getParams().getAliasMath1Quot1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_MOD_0, getMathRef().getParams().getAliasMath0Mod0()),new KeyValueMemberName(AliasParamMath.MATH_0_MOD_1, getMathRef().getParams().getAliasMath0Mod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_MOD_0, getMathRef().getParams().getAliasMath1Mod0()),new KeyValueMemberName(AliasParamMath.MATH_1_MOD_1, getMathRef().getParams().getAliasMath1Mod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_PLUS_0, getMathRef().getParams().getAliasMath0Plus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_PLUS_0, getMathRef().getParams().getAliasMath1Plus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_2_PLUS_0, getMathRef().getParams().getAliasMath2Plus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_3_PLUS_0, getMathRef().getParams().getAliasMath3Plus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_MINUS_0, getMathRef().getParams().getAliasMath0Minus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_MINUS_0, getMathRef().getParams().getAliasMath1Minus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_2_MINUS_0, getMathRef().getParams().getAliasMath2Minus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_3_MINUS_0, getMathRef().getParams().getAliasMath3Minus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_NEG_0, getMathRef().getParams().getAliasMath0Neg0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_NEG_BIN_0, getMathRef().getParams().getAliasMath0NegBin0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_NEG_BIN_0, getMathRef().getParams().getAliasMath1NegBin0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_4_PLUS_0, getMathRef().getParams().getAliasMath4Plus0()),new KeyValueMemberName(AliasParamMath.MATH_4_PLUS_1, getMathRef().getParams().getAliasMath4Plus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_5_PLUS_0, getMathRef().getParams().getAliasMath5Plus0()),new KeyValueMemberName(AliasParamMath.MATH_5_PLUS_1, getMathRef().getParams().getAliasMath5Plus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_6_PLUS_0, getMathRef().getParams().getAliasMath6Plus0()),new KeyValueMemberName(AliasParamMath.MATH_6_PLUS_1, getMathRef().getParams().getAliasMath6Plus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_7_PLUS_0, getMathRef().getParams().getAliasMath7Plus0()),new KeyValueMemberName(AliasParamMath.MATH_7_PLUS_1, getMathRef().getParams().getAliasMath7Plus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_4_MINUS_0, getMathRef().getParams().getAliasMath4Minus0()),new KeyValueMemberName(AliasParamMath.MATH_4_MINUS_1, getMathRef().getParams().getAliasMath4Minus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_5_MINUS_0, getMathRef().getParams().getAliasMath5Minus0()),new KeyValueMemberName(AliasParamMath.MATH_5_MINUS_1, getMathRef().getParams().getAliasMath5Minus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_6_MINUS_0, getMathRef().getParams().getAliasMath6Minus0()),new KeyValueMemberName(AliasParamMath.MATH_6_MINUS_1, getMathRef().getParams().getAliasMath6Minus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_7_MINUS_0, getMathRef().getParams().getAliasMath7Minus0()),new KeyValueMemberName(AliasParamMath.MATH_7_MINUS_1, getMathRef().getParams().getAliasMath7Minus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_MULT_0, getMathRef().getParams().getAliasMath0Mult0()),new KeyValueMemberName(AliasParamMath.MATH_0_MULT_1, getMathRef().getParams().getAliasMath0Mult1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_MULT_0, getMathRef().getParams().getAliasMath1Mult0()),new KeyValueMemberName(AliasParamMath.MATH_1_MULT_1, getMathRef().getParams().getAliasMath1Mult1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_2_MULT_0, getMathRef().getParams().getAliasMath2Mult0()),new KeyValueMemberName(AliasParamMath.MATH_2_MULT_1, getMathRef().getParams().getAliasMath2Mult1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_3_MULT_0, getMathRef().getParams().getAliasMath3Mult0()),new KeyValueMemberName(AliasParamMath.MATH_3_MULT_1, getMathRef().getParams().getAliasMath3Mult1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_BIN_QUOT_0, getMathRef().getParams().getAliasMath0BinQuot0()),new KeyValueMemberName(AliasParamMath.MATH_0_BIN_QUOT_1, getMathRef().getParams().getAliasMath0BinQuot1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_BIN_QUOT_0, getMathRef().getParams().getAliasMath1BinQuot0()),new KeyValueMemberName(AliasParamMath.MATH_1_BIN_QUOT_1, getMathRef().getParams().getAliasMath1BinQuot1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_2_BIN_QUOT_0, getMathRef().getParams().getAliasMath2BinQuot0()),new KeyValueMemberName(AliasParamMath.MATH_2_BIN_QUOT_1, getMathRef().getParams().getAliasMath2BinQuot1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_3_BIN_QUOT_0, getMathRef().getParams().getAliasMath3BinQuot0()),new KeyValueMemberName(AliasParamMath.MATH_3_BIN_QUOT_1, getMathRef().getParams().getAliasMath3BinQuot1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_BIN_MOD_0, getMathRef().getParams().getAliasMath0BinMod0()),new KeyValueMemberName(AliasParamMath.MATH_0_BIN_MOD_1, getMathRef().getParams().getAliasMath0BinMod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_BIN_MOD_0, getMathRef().getParams().getAliasMath1BinMod0()),new KeyValueMemberName(AliasParamMath.MATH_1_BIN_MOD_1, getMathRef().getParams().getAliasMath1BinMod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_2_BIN_MOD_0, getMathRef().getParams().getAliasMath2BinMod0()),new KeyValueMemberName(AliasParamMath.MATH_2_BIN_MOD_1, getMathRef().getParams().getAliasMath2BinMod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_3_BIN_MOD_0, getMathRef().getParams().getAliasMath3BinMod0()),new KeyValueMemberName(AliasParamMath.MATH_3_BIN_MOD_1, getMathRef().getParams().getAliasMath3BinMod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_AND_0, getMathRef().getParams().getAliasMath0And0()),new KeyValueMemberName(AliasParamMath.MATH_0_AND_1, getMathRef().getParams().getAliasMath0And1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_AND_0, getMathRef().getParams().getAliasMath1And0()),new KeyValueMemberName(AliasParamMath.MATH_1_AND_1, getMathRef().getParams().getAliasMath1And1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_2_AND_0, getMathRef().getParams().getAliasMath2And0()),new KeyValueMemberName(AliasParamMath.MATH_2_AND_1, getMathRef().getParams().getAliasMath2And1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_OR_0, getMathRef().getParams().getAliasMath0Or0()),new KeyValueMemberName(AliasParamMath.MATH_0_OR_1, getMathRef().getParams().getAliasMath0Or1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_OR_0, getMathRef().getParams().getAliasMath1Or0()),new KeyValueMemberName(AliasParamMath.MATH_1_OR_1, getMathRef().getParams().getAliasMath1Or1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_2_OR_0, getMathRef().getParams().getAliasMath2Or0()),new KeyValueMemberName(AliasParamMath.MATH_2_OR_1, getMathRef().getParams().getAliasMath2Or1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_XOR_0, getMathRef().getParams().getAliasMath0Xor0()),new KeyValueMemberName(AliasParamMath.MATH_0_XOR_1, getMathRef().getParams().getAliasMath0Xor1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_XOR_0, getMathRef().getParams().getAliasMath1Xor0()),new KeyValueMemberName(AliasParamMath.MATH_1_XOR_1, getMathRef().getParams().getAliasMath1Xor1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_2_XOR_0, getMathRef().getParams().getAliasMath2Xor0()),new KeyValueMemberName(AliasParamMath.MATH_2_XOR_1, getMathRef().getParams().getAliasMath2Xor1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_SHIFT_LEFT_0, getMathRef().getParams().getAliasMath0ShiftLeft0()),new KeyValueMemberName(AliasParamMath.MATH_0_SHIFT_LEFT_1, getMathRef().getParams().getAliasMath0ShiftLeft1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_SHIFT_LEFT_0, getMathRef().getParams().getAliasMath1ShiftLeft0()),new KeyValueMemberName(AliasParamMath.MATH_1_SHIFT_LEFT_1, getMathRef().getParams().getAliasMath1ShiftLeft1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_SHIFT_RIGHT_0, getMathRef().getParams().getAliasMath0ShiftRight0()),new KeyValueMemberName(AliasParamMath.MATH_0_SHIFT_RIGHT_1, getMathRef().getParams().getAliasMath0ShiftRight1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_SHIFT_RIGHT_0, getMathRef().getParams().getAliasMath1ShiftRight0()),new KeyValueMemberName(AliasParamMath.MATH_1_SHIFT_RIGHT_1, getMathRef().getParams().getAliasMath1ShiftRight1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_BIT_SHIFT_LEFT_0, getMathRef().getParams().getAliasMath0BitShiftLeft0()),new KeyValueMemberName(AliasParamMath.MATH_0_BIT_SHIFT_LEFT_1, getMathRef().getParams().getAliasMath0BitShiftLeft1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_BIT_SHIFT_LEFT_0, getMathRef().getParams().getAliasMath1BitShiftLeft0()),new KeyValueMemberName(AliasParamMath.MATH_1_BIT_SHIFT_LEFT_1, getMathRef().getParams().getAliasMath1BitShiftLeft1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_BIT_SHIFT_RIGHT_0, getMathRef().getParams().getAliasMath0BitShiftRight0()),new KeyValueMemberName(AliasParamMath.MATH_0_BIT_SHIFT_RIGHT_1, getMathRef().getParams().getAliasMath0BitShiftRight1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_BIT_SHIFT_RIGHT_0, getMathRef().getParams().getAliasMath1BitShiftRight0()),new KeyValueMemberName(AliasParamMath.MATH_1_BIT_SHIFT_RIGHT_1, getMathRef().getParams().getAliasMath1BitShiftRight1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_ROTATE_LEFT_0, getMathRef().getParams().getAliasMath0RotateLeft0()),new KeyValueMemberName(AliasParamMath.MATH_0_ROTATE_LEFT_1, getMathRef().getParams().getAliasMath0RotateLeft1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_ROTATE_LEFT_0, getMathRef().getParams().getAliasMath1RotateLeft0()),new KeyValueMemberName(AliasParamMath.MATH_1_ROTATE_LEFT_1, getMathRef().getParams().getAliasMath1RotateLeft1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_ROTATE_RIGHT_0, getMathRef().getParams().getAliasMath0RotateRight0()),new KeyValueMemberName(AliasParamMath.MATH_0_ROTATE_RIGHT_1, getMathRef().getParams().getAliasMath0RotateRight1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_ROTATE_RIGHT_0, getMathRef().getParams().getAliasMath1RotateRight0()),new KeyValueMemberName(AliasParamMath.MATH_1_ROTATE_RIGHT_1, getMathRef().getParams().getAliasMath1RotateRight1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_LE_0, getMathRef().getParams().getAliasMath0Le0()),new KeyValueMemberName(AliasParamMath.MATH_0_LE_1, getMathRef().getParams().getAliasMath0Le1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_GE_0, getMathRef().getParams().getAliasMath0Ge0()),new KeyValueMemberName(AliasParamMath.MATH_0_GE_1, getMathRef().getParams().getAliasMath0Ge1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_LT_0, getMathRef().getParams().getAliasMath0Lt0()),new KeyValueMemberName(AliasParamMath.MATH_0_LT_1, getMathRef().getParams().getAliasMath0Lt1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_GT_0, getMathRef().getParams().getAliasMath0Gt0()),new KeyValueMemberName(AliasParamMath.MATH_0_GT_1, getMathRef().getParams().getAliasMath0Gt1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_LE_0, getMathRef().getParams().getAliasMath1Le0()),new KeyValueMemberName(AliasParamMath.MATH_1_LE_1, getMathRef().getParams().getAliasMath1Le1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_GE_0, getMathRef().getParams().getAliasMath1Ge0()),new KeyValueMemberName(AliasParamMath.MATH_1_GE_1, getMathRef().getParams().getAliasMath1Ge1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_LT_0, getMathRef().getParams().getAliasMath1Lt0()),new KeyValueMemberName(AliasParamMath.MATH_1_LT_1, getMathRef().getParams().getAliasMath1Lt1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_GT_0, getMathRef().getParams().getAliasMath1Gt0()),new KeyValueMemberName(AliasParamMath.MATH_1_GT_1, getMathRef().getParams().getAliasMath1Gt1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_RANDOM_0, getMathRef().getParams().getAliasMath0Random0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_SEED_0, getMathRef().getParams().getAliasMath0Seed0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BOOLEAN_0_COMPARE_0, getNbAlias().getParams().getAliasBoolean0Compare0()),new KeyValueMemberName(AliasParamNumber.BOOLEAN_0_COMPARE_1, getNbAlias().getParams().getAliasBoolean0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BOOLEAN_0_COMPARE_TO_0, getNbAlias().getParams().getAliasBoolean0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BOOLEAN_0_EQUALS_0, getNbAlias().getParams().getAliasBoolean0Equals0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BOOLEAN_0_PARSE_BOOLEAN_0, getNbAlias().getParams().getAliasBoolean0ParseBoolean0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BOOLEAN_0_TO_STRING_METHOD_0, getNbAlias().getParams().getAliasBoolean0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BOOLEAN_0_VALUE_OF_METHOD_0, getNbAlias().getParams().getAliasBoolean0ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BOOLEAN_1_VALUE_OF_METHOD_0, getNbAlias().getParams().getAliasBoolean1ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BOOLEAN_0_BOOLEAN_0, getNbAlias().getParams().getAliasBoolean0Boolean0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BOOLEAN_1_BOOLEAN_0, getNbAlias().getParams().getAliasBoolean1Boolean0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_0_TO_STRING_METHOD_0, getNbAlias().getParams().getAliasByte0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_0_PARSE_BYTE_0, getNbAlias().getParams().getAliasByte0ParseByte0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_1_PARSE_BYTE_0, getNbAlias().getParams().getAliasByte1ParseByte0()),new KeyValueMemberName(AliasParamNumber.BYTE_1_PARSE_BYTE_1, getNbAlias().getParams().getAliasByte1ParseByte1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_0_COMPARE_TO_0, getNbAlias().getParams().getAliasByte0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_0_COMPARE_0, getNbAlias().getParams().getAliasByte0Compare0()),new KeyValueMemberName(AliasParamNumber.BYTE_0_COMPARE_1, getNbAlias().getParams().getAliasByte0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_0_PARSE_BYTE_OR_NULL_0, getNbAlias().getParams().getAliasByte0ParseByteOrNull0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_1_PARSE_BYTE_OR_NULL_0, getNbAlias().getParams().getAliasByte1ParseByteOrNull0()),new KeyValueMemberName(AliasParamNumber.BYTE_1_PARSE_BYTE_OR_NULL_1, getNbAlias().getParams().getAliasByte1ParseByteOrNull1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_0_TO_BIN_STRING_0, getNbAlias().getParams().getAliasByte0ToBinString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_0_TO_OCT_STRING_0, getNbAlias().getParams().getAliasByte0ToOctString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_0_TO_HEX_STRING_0, getNbAlias().getParams().getAliasByte0ToHexString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_0_BYTE_0, getNbAlias().getParams().getAliasByte0Byte0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_1_BYTE_0, getNbAlias().getParams().getAliasByte1Byte0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_0_TO_STRING_METHOD_0, getNbAlias().getParams().getAliasShort0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_0_PARSE_SHORT_0, getNbAlias().getParams().getAliasShort0ParseShort0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_1_PARSE_SHORT_0, getNbAlias().getParams().getAliasShort1ParseShort0()),new KeyValueMemberName(AliasParamNumber.SHORT_1_PARSE_SHORT_1, getNbAlias().getParams().getAliasShort1ParseShort1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_0_COMPARE_TO_0, getNbAlias().getParams().getAliasShort0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_0_COMPARE_0, getNbAlias().getParams().getAliasShort0Compare0()),new KeyValueMemberName(AliasParamNumber.SHORT_0_COMPARE_1, getNbAlias().getParams().getAliasShort0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_0_PARSE_SHORT_OR_NULL_0, getNbAlias().getParams().getAliasShort0ParseShortOrNull0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_1_PARSE_SHORT_OR_NULL_0, getNbAlias().getParams().getAliasShort1ParseShortOrNull0()),new KeyValueMemberName(AliasParamNumber.SHORT_1_PARSE_SHORT_OR_NULL_1, getNbAlias().getParams().getAliasShort1ParseShortOrNull1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_0_TO_BIN_STRING_0, getNbAlias().getParams().getAliasShort0ToBinString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_0_TO_OCT_STRING_0, getNbAlias().getParams().getAliasShort0ToOctString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_0_TO_HEX_STRING_0, getNbAlias().getParams().getAliasShort0ToHexString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_0_SHORT_0, getNbAlias().getParams().getAliasShort0Short0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_1_SHORT_0, getNbAlias().getParams().getAliasShort1Short0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_0_TO_STRING_METHOD_0, getNbAlias().getParams().getAliasInteger0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_0_PARSE_INT_0, getNbAlias().getParams().getAliasInteger0ParseInt0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_1_PARSE_INT_0, getNbAlias().getParams().getAliasInteger1ParseInt0()),new KeyValueMemberName(AliasParamNumber.INTEGER_1_PARSE_INT_1, getNbAlias().getParams().getAliasInteger1ParseInt1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_0_COMPARE_TO_0, getNbAlias().getParams().getAliasInteger0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_0_COMPARE_0, getNbAlias().getParams().getAliasInteger0Compare0()),new KeyValueMemberName(AliasParamNumber.INTEGER_0_COMPARE_1, getNbAlias().getParams().getAliasInteger0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_0_PARSE_INT_OR_NULL_0, getNbAlias().getParams().getAliasInteger0ParseIntOrNull0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_1_PARSE_INT_OR_NULL_0, getNbAlias().getParams().getAliasInteger1ParseIntOrNull0()),new KeyValueMemberName(AliasParamNumber.INTEGER_1_PARSE_INT_OR_NULL_1, getNbAlias().getParams().getAliasInteger1ParseIntOrNull1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_0_TO_BIN_STRING_0, getNbAlias().getParams().getAliasInteger0ToBinString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_0_TO_OCT_STRING_0, getNbAlias().getParams().getAliasInteger0ToOctString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_0_TO_HEX_STRING_0, getNbAlias().getParams().getAliasInteger0ToHexString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_0_INTEGER_0, getNbAlias().getParams().getAliasInteger0Integer0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_1_INTEGER_0, getNbAlias().getParams().getAliasInteger1Integer0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_0_TO_STRING_METHOD_0, getNbAlias().getParams().getAliasLong0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_1_TO_STRING_METHOD_0, getNbAlias().getParams().getAliasLong1ToStringMethod0()),new KeyValueMemberName(AliasParamNumber.LONG_1_TO_STRING_METHOD_1, getNbAlias().getParams().getAliasLong1ToStringMethod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_0_SIGNUM_0, getNbAlias().getParams().getAliasLong0Signum0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_0_PARSE_LONG_0, getNbAlias().getParams().getAliasLong0ParseLong0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_1_PARSE_LONG_0, getNbAlias().getParams().getAliasLong1ParseLong0()),new KeyValueMemberName(AliasParamNumber.LONG_1_PARSE_LONG_1, getNbAlias().getParams().getAliasLong1ParseLong1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_0_COMPARE_TO_0, getNbAlias().getParams().getAliasLong0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_0_COMPARE_0, getNbAlias().getParams().getAliasLong0Compare0()),new KeyValueMemberName(AliasParamNumber.LONG_0_COMPARE_1, getNbAlias().getParams().getAliasLong0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_0_PARSE_LONG_OR_NULL_0, getNbAlias().getParams().getAliasLong0ParseLongOrNull0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_1_PARSE_LONG_OR_NULL_0, getNbAlias().getParams().getAliasLong1ParseLongOrNull0()),new KeyValueMemberName(AliasParamNumber.LONG_1_PARSE_LONG_OR_NULL_1, getNbAlias().getParams().getAliasLong1ParseLongOrNull1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_0_TO_BIN_STRING_0, getNbAlias().getParams().getAliasLong0ToBinString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_0_TO_OCT_STRING_0, getNbAlias().getParams().getAliasLong0ToOctString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_0_TO_HEX_STRING_0, getNbAlias().getParams().getAliasLong0ToHexString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_0_LONG_0, getNbAlias().getParams().getAliasLong0Long0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_1_LONG_0, getNbAlias().getParams().getAliasLong1Long0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.FLOAT_0_TO_STRING_METHOD_0, getNbAlias().getParams().getAliasFloat0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.FLOAT_0_PARSE_FLOAT_0, getNbAlias().getParams().getAliasFloat0ParseFloat0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.FLOAT_0_COMPARE_TO_0, getNbAlias().getParams().getAliasFloat0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.FLOAT_0_COMPARE_0, getNbAlias().getParams().getAliasFloat0Compare0()),new KeyValueMemberName(AliasParamNumber.FLOAT_0_COMPARE_1, getNbAlias().getParams().getAliasFloat0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.FLOAT_0_PARSE_FLOAT_OR_NULL_0, getNbAlias().getParams().getAliasFloat0ParseFloatOrNull0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.FLOAT_0_IS_INFINITE_0, getNbAlias().getParams().getAliasFloat0IsInfinite0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.FLOAT_0_IS_NAN_0, getNbAlias().getParams().getAliasFloat0IsNan0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.FLOAT_0_FLOAT_0, getNbAlias().getParams().getAliasFloat0Float0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.FLOAT_1_FLOAT_0, getNbAlias().getParams().getAliasFloat1Float0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.DOUBLE_0_TO_STRING_METHOD_0, getNbAlias().getParams().getAliasDouble0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.DOUBLE_0_PARSE_DOUBLE_0, getNbAlias().getParams().getAliasDouble0ParseDouble0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.DOUBLE_0_COMPARE_TO_0, getNbAlias().getParams().getAliasDouble0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.DOUBLE_0_COMPARE_0, getNbAlias().getParams().getAliasDouble0Compare0()),new KeyValueMemberName(AliasParamNumber.DOUBLE_0_COMPARE_1, getNbAlias().getParams().getAliasDouble0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.DOUBLE_0_PARSE_DOUBLE_OR_NULL_0, getNbAlias().getParams().getAliasDouble0ParseDoubleOrNull0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.DOUBLE_0_IS_INFINITE_0, getNbAlias().getParams().getAliasDouble0IsInfinite0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.DOUBLE_0_IS_NAN_0, getNbAlias().getParams().getAliasDouble0IsNan0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.DOUBLE_0_DOUBLE_0, getNbAlias().getParams().getAliasDouble0Double0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.DOUBLE_1_DOUBLE_0, getNbAlias().getParams().getAliasDouble1Double0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.NUMBER_0_TO_STRING_METHOD_0, getNbAlias().getParams().getAliasNumber0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.NUMBER_0_EQUALS_0, getNbAlias().getParams().getAliasNumber0Equals0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.NUMBER_1_EQUALS_0, getNbAlias().getParams().getAliasNumber1Equals0()),new KeyValueMemberName(AliasParamNumber.NUMBER_1_EQUALS_1, getNbAlias().getParams().getAliasNumber1Equals1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.NUMBER_0_COMPARE_TO_0, getNbAlias().getParams().getAliasNumber0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.NUMBER_0_COMPARE_0, getNbAlias().getParams().getAliasNumber0Compare0()),new KeyValueMemberName(AliasParamNumber.NUMBER_0_COMPARE_1, getNbAlias().getParams().getAliasNumber0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_COMPARE_TO_0, getNbAlias().getParams().getAliasCharacter0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_COMPARE_0, getNbAlias().getParams().getAliasCharacter0Compare0()),new KeyValueMemberName(AliasParamNumber.CHARACTER_0_COMPARE_1, getNbAlias().getParams().getAliasCharacter0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_DIGIT_0, getNbAlias().getParams().getAliasCharacter0Digit0()),new KeyValueMemberName(AliasParamNumber.CHARACTER_0_DIGIT_1, getNbAlias().getParams().getAliasCharacter0Digit1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_FOR_DIGIT_0, getNbAlias().getParams().getAliasCharacter0ForDigit0()),new KeyValueMemberName(AliasParamNumber.CHARACTER_0_FOR_DIGIT_1, getNbAlias().getParams().getAliasCharacter0ForDigit1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_GET_DIRECTIONALITY_0, getNbAlias().getParams().getAliasCharacter0GetDirectionality0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_GET_TYPE_0, getNbAlias().getParams().getAliasCharacter0GetType0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_IS_DIGIT_0, getNbAlias().getParams().getAliasCharacter0IsDigit0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_IS_LETTER_0, getNbAlias().getParams().getAliasCharacter0IsLetter0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_IS_LETTER_OR_DIGIT_0, getNbAlias().getParams().getAliasCharacter0IsLetterOrDigit0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_IS_WORD_CHAR_0, getNbAlias().getParams().getAliasCharacter0IsWordChar0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_IS_WHITESPACE_0, getNbAlias().getParams().getAliasCharacter0IsWhitespace0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_IS_LOWER_CASE_0, getNbAlias().getParams().getAliasCharacter0IsLowerCase0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_IS_UPPER_CASE_0, getNbAlias().getParams().getAliasCharacter0IsUpperCase0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_IS_SPACE_0, getNbAlias().getParams().getAliasCharacter0IsSpace0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_TO_LOWER_CASE_CHAR_0, getNbAlias().getParams().getAliasCharacter0ToLowerCaseChar0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_TO_UPPER_CASE_CHAR_0, getNbAlias().getParams().getAliasCharacter0ToUpperCaseChar0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_TO_STRING_METHOD_0, getNbAlias().getParams().getAliasCharacter0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_CHARACTER_0, getNbAlias().getParams().getAliasCharacter0Character0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.FCT_0_CALL_0, getReflect().getParams().getAliasFct0Call0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_CLASS_0, getReflect().getParams().getAliasClassType0GetClass0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_FOR_NAME_0, getReflect().getParams().getAliasClassType0ForName0()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_FOR_NAME_1, getReflect().getParams().getAliasClassType0ForName1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_1_FOR_NAME_0, getReflect().getParams().getAliasClassType1ForName0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_IS_INSTANCE_0, getReflect().getParams().getAliasClassType0IsInstance0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_IS_ASSIGNABLE_FROM_0, getReflect().getParams().getAliasClassType0IsAssignableFrom0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_DEFAULT_INSTANCE_0, getReflect().getParams().getAliasClassType0DefaultInstance0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_ENUM_VALUE_OF_0, getReflect().getParams().getAliasClassType0EnumValueOf0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_0, getReflect().getParams().getAliasClassType0GetDeclaredConstructors0()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_1, getReflect().getParams().getAliasClassType0GetDeclaredConstructors1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_FIELDS_0, getReflect().getParams().getAliasClassType0GetDeclaredFields0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_0, getReflect().getParams().getAliasClassType0GetDeclaredStaticMethods0()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_1, getReflect().getParams().getAliasClassType0GetDeclaredStaticMethods1()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_2, getReflect().getParams().getAliasClassType0GetDeclaredStaticMethods2()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_3, getReflect().getParams().getAliasClassType0GetDeclaredStaticMethods3())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_0, getReflect().getParams().getAliasClassType0GetDeclaredMethods0()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_1, getReflect().getParams().getAliasClassType0GetDeclaredMethods1()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_2, getReflect().getParams().getAliasClassType0GetDeclaredMethods2()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_3, getReflect().getParams().getAliasClassType0GetDeclaredMethods3())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_EXPLICITS_0, getReflect().getParams().getAliasClassType0GetDeclaredExplicits0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_IMPLICITS_0, getReflect().getParams().getAliasClassType0GetDeclaredImplicits0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_BLOCKS_0, getReflect().getParams().getAliasClassType0GetDeclaredBlocks0()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_BLOCKS_1, getReflect().getParams().getAliasClassType0GetDeclaredBlocks1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_MAKE_GENERIC_0, getReflect().getParams().getAliasClassType0MakeGeneric0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_MAKE_WILD_CARD_0, getReflect().getParams().getAliasClassType0MakeWildCard0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_OPERATORS_0, getReflect().getParams().getAliasClassType0GetOperators0()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_OPERATORS_1, getReflect().getParams().getAliasClassType0GetOperators1()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_OPERATORS_2, getReflect().getParams().getAliasClassType0GetOperators2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_ARRAY_NEW_INSTANCE_0, getReflect().getParams().getAliasClassType0ArrayNewInstance0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_ARRAY_GET_LENGTH_0, getReflect().getParams().getAliasClassType0ArrayGetLength0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_ARRAY_GET_0, getReflect().getParams().getAliasClassType0ArrayGet0()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_ARRAY_GET_1, getReflect().getParams().getAliasClassType0ArrayGet1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_ARRAY_SET_0, getReflect().getParams().getAliasClassType0ArraySet0()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_ARRAY_SET_1, getReflect().getParams().getAliasClassType0ArraySet1()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_ARRAY_SET_2, getReflect().getParams().getAliasClassType0ArraySet2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CONSTRUCTOR_0_NEW_INSTANCE_0, getReflect().getParams().getAliasConstructor0NewInstance0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.FIELD_0_GET_FIELD_0, getReflect().getParams().getAliasField0GetField0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.FIELD_0_SET_FIELD_0, getReflect().getParams().getAliasField0SetField0()),new KeyValueMemberName(AliasParamReflection.FIELD_0_SET_FIELD_1, getReflect().getParams().getAliasField0SetField1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_0_INVOKE_0, getReflect().getParams().getAliasMethod0Invoke0()),new KeyValueMemberName(AliasParamReflection.METHOD_0_INVOKE_1, getReflect().getParams().getAliasMethod0Invoke1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_0_INVOKE_DIRECT_0, getReflect().getParams().getAliasMethod0InvokeDirect0()),new KeyValueMemberName(AliasParamReflection.METHOD_0_INVOKE_DIRECT_1, getReflect().getParams().getAliasMethod0InvokeDirect1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0, getReflect().getParams().getAliasMethod0GetDeclaredAnonymousLambdaLocalVars0()),new KeyValueMemberName(AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1, getReflect().getParams().getAliasMethod0GetDeclaredAnonymousLambdaLocalVars1()),new KeyValueMemberName(AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_2, getReflect().getParams().getAliasMethod0GetDeclaredAnonymousLambdaLocalVars2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0, getReflect().getParams().getAliasMethod1GetDeclaredAnonymousLambdaLocalVars0()),new KeyValueMemberName(AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1, getReflect().getParams().getAliasMethod1GetDeclaredAnonymousLambdaLocalVars1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0, getReflect().getParams().getAliasMethod2GetDeclaredAnonymousLambdaLocalVars0()),new KeyValueMemberName(AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1, getReflect().getParams().getAliasMethod2GetDeclaredAnonymousLambdaLocalVars1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0, getReflect().getParams().getAliasMethod3GetDeclaredAnonymousLambdaLocalVars0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0, getReflect().getParams().getAliasMethod0GetDeclaredAnonymousLambdaLoopVars0()),new KeyValueMemberName(AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1, getReflect().getParams().getAliasMethod0GetDeclaredAnonymousLambdaLoopVars1()),new KeyValueMemberName(AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_2, getReflect().getParams().getAliasMethod0GetDeclaredAnonymousLambdaLoopVars2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0, getReflect().getParams().getAliasMethod1GetDeclaredAnonymousLambdaLoopVars0()),new KeyValueMemberName(AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1, getReflect().getParams().getAliasMethod1GetDeclaredAnonymousLambdaLoopVars1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0, getReflect().getParams().getAliasMethod2GetDeclaredAnonymousLambdaLoopVars0()),new KeyValueMemberName(AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1, getReflect().getParams().getAliasMethod2GetDeclaredAnonymousLambdaLoopVars1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0, getReflect().getParams().getAliasMethod3GetDeclaredAnonymousLambdaLoopVars0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.ANNOTATION_TYPE_0_GET_STRING_0, getReflect().getParams().getAliasAnnotationType0GetString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.ANNOTATED_0_GET_ANNOTATIONS_0, getReflect().getParams().getAliasAnnotated0GetAnnotations0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.ANNOTATED_0_GET_ANNOTATIONS_PARAMETERS_0, getReflect().getParams().getAliasAnnotated0GetAnnotationsParameters0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_0, getReflect().getParams().getAliasAnnotated0GetDeclaredAnonymousLambda0()),new KeyValueMemberName(AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_1, getReflect().getParams().getAliasAnnotated0GetDeclaredAnonymousLambda1()),new KeyValueMemberName(AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_2, getReflect().getParams().getAliasAnnotated0GetDeclaredAnonymousLambda2()),new KeyValueMemberName(AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_3, getReflect().getParams().getAliasAnnotated0GetDeclaredAnonymousLambda3())));
        return map_;
    }
    public static String get(StringMap<String> _util, StringMap<String> _cust,String _key) {
        String val_ = _cust.getVal(_key);
        if (val_ == null) {
            String def_ = _util.getVal(_key);
            if (def_ == null) {
                return "";
            }
            return def_;
        }
        return val_;
    }
    public PrimitiveTypes getPrimTypes() {
        return primTypes;
    }

    public DisplayedStrings getDisplayedStrings() {
        return displayedStrings;
    }

    public AliasNumber getNbAlias() {
        return nbAlias;
    }

    public AliasCore getCoreNames() {
        return coreNames;
    }

    public AliasCharSequence getCharSeq() {
        return charSeq;
    }

    public AliasPredefinedTypes getPredefTypes() {
        return predefTypes;
    }

    public AliasMath getMathRef() {
        return mathRef;
    }

    public AliasReflection getReflect() {
        return reflect;
    }

    public AliasStackTraceElement getStackElt() {
        return stackElt;
    }

    public String getDefaultPkg() {
        return defaultPkg;
    }

    public void setDefaultPkg(String _defaultPkg) {
        this.defaultPkg = _defaultPkg;
    }

    public StringMap<StandardType> getStandards() {
        return standards;
    }
}
