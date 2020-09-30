package code.expressionlanguage.stds;

import code.expressionlanguage.*;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.exec.ClassFieldStruct;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.structs.*;
import code.maths.montecarlo.AbstractGenerator;
import code.util.*;

public abstract class LgNames {

    public static final String DEFAULT_PKG = "DefaultPkg";
    public static final String FIELD_MAX_VALUE = "MAX_VALUE";
    public static final String FIELD_MIN_VALUE = "MIN_VALUE";
    public static final String FIELD_PLUS_INFINITY = "PLUS_INFINITY";
    public static final String FIELD_MINUS_INFINITY = "MINUS_INFINITY";
    public static final String FIELD_NAN = "NAN";
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
    public static final String REPLACE_STRING = "ReplaceString";
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
    public static final String META_INFO = "MetaInfo";
    public static final String INSTANCE = "Instance";
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
    public static final String TO_LOWER_CASE_CHAR = "ToLowerCaseChar";
    public static final String STRING_BUILDER = "StringBuilder";
    public static final String TO_UPPER_CASE = "ToUpperCase";
    public static final String TO_UPPER_CASE_CHAR = "ToUpperCaseChar";
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
    public static final String READ_RESOURCES_NAMES_LENGTH = "ReadResourcesNamesLength";
    public static final String INVOKE_TARGET = "InvokeTarget";
    public static final String GET_ANNOTATIONS = "GetAnnotations";
    public static final String GET_VARIABLE_OWNER = "GetVariableOwner";
    public static final String READ_RESOURCES = "ReadResources";
    public static final String READ_RESOURCES_INDEX = "ReadResourcesIndex";
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
    public static final String SEED_GENERATOR = "SeedGenerator";
    public static final String SEED_DOUBLE_GENERATOR = "SeedDoubleGenerator";
    public static final String SEED_GET = "SeedGet";
    public static final String PAIR_TYPE_VAR_SECOND = "PairTypeVarSecond";
    public static final String ANNOTATED = "Annotated";
    public static final String ITERABLE_VAR = "IterableVar";
    public static final String GET_DEFAULT_VALUE = "GetDefaultValue";
    public static final String MAKE_GENERIC = "MakeGeneric";
    public static final String GET_ALL_CLASSES = "GetAllClasses";
    public static final String GET_OPERATORS = "GetOperators";
    public static final String GET_DECLARED_EXPLICITS = "GetDeclaredExplicits";
    public static final String GET_DECLARED_IMPLICITS = "GetDeclaredImplicits";
    public static final String GET_DECLARED_TRUE_OPERATORS = "GetDeclaredTrueOperators";
    public static final String GET_DECLARED_FALSE_OPERATORS = "GetDeclaredFalseOperators";
    public static final String GET_DECLARED_METHODS = "GetDeclaredMethods";
    public static final String GET_DECLARED_STATIC_METHODS = "GetDeclaredStaticMethods";
    public static final String GET_DECLARED_CONSTRUCTORS = "GetDeclaredConstructors";
    public static final String GET_DECLARED_FIELDS = "GetDeclaredFields";
    public static final String GET_DECLARED_ANONYMOUS_TYPES = "GetDeclaredAnonymousTypes";
    public static final String GET_DECLARED_ANONYMOUS_LAMBDA = "GetDeclaredAnonymousLambda";
    public static final String GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS = "GetDeclaredAnonymousLambdaLocalVars";
    public static final String GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS = "GetDeclaredAnonymousLambdaLoopVars";
    public static final String GET_DECLARED_LOCAL_TYPES = "GetDeclaredLocalTypes";
    public static final String GET_DECLARED_BLOCKS = "GetDeclaredBlocks";
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
    public static final String SEED = "Seed";
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
    public static final String GET_PRETTY_SINGLE_NAME = "GetPrettySingleName";
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
    public static final String NULL_COVER_STRING = "NullCoverString";
    public static final String NOT_NULL_COVER_STRING = "NotNullCoverString";
    public static final String STATIC_STRING = "StaticString";
    public static final String STATIC_CALL_STRING = "StaticCallString";
    public static final String INFINITY = "Infinity";
    public static final String EXPONENT = "Exponent";
    public static final String NAN = "Nan";

    private LgNamesContent content = new LgNamesContent();

    private final AbstractGenerator generator;
    private AbstractExecConstantsCalculator calculator;

    protected LgNames(AbstractGenerator generator) {
        this.generator = generator;
        setCalculator(new DefaultExecConstantsCalculator(content.getNbAlias()));
    }

    /**Called after setters*/
    public void build() {
        content.getCoreNames().build(this);
        content.getNbAlias().build(this);
        content.getCharSeq().build(this);
        content.getReflect().build(this);
        content.getMathRef().build(this);
        content.getStackElt().build(this);
        content.getPrimTypes().buildPrimitiveTypes(this);
        buildOther();
    }

    public StringStruct getStringOfObject(ContextEl _cont, Struct _arg) {
        return ApplyCoreMethodUtil.getStringOfObjectBase(_cont, _arg);
    }

    public StringMap<String> allPrimitives() {
        StringMap<String> list_ = new StringMap<String>();
        list_.addEntry(PRIM_BOOLEAN, content.getPrimTypes().getAliasPrimBoolean());
        list_.addEntry(PRIM_BYTE, content.getPrimTypes().getAliasPrimByte());
        list_.addEntry(PRIM_SHORT, content.getPrimTypes().getAliasPrimShort());
        list_.addEntry(PRIM_CHAR, content.getPrimTypes().getAliasPrimChar());
        list_.addEntry(PRIM_INTEGER, content.getPrimTypes().getAliasPrimInteger());
        list_.addEntry(PRIM_LONG, content.getPrimTypes().getAliasPrimLong());
        list_.addEntry(PRIM_FLOAT, content.getPrimTypes().getAliasPrimFloat());
        list_.addEntry(PRIM_DOUBLE, content.getPrimTypes().getAliasPrimDouble());
        list_.addEntry(VOID, content.getCoreNames().getAliasVoid());
        return list_;
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
        list_.addEntry(SEED_DOUBLE_GENERATOR,getAliasSeedDoubleGenerator());
        list_.addEntry(SEED_GENERATOR,getAliasSeedGenerator());
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
                new KeyValueMemberName(ENUM_NAME,getAliasEnumName()),
                new KeyValueMemberName(SEED_GET,getAliasSeedGet())
        ));
        return list_;
    }
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames() {
        CustList<CustList<KeyValueMemberName>> map_ = new CustList<CustList<KeyValueMemberName>>();
        map_.add(new CustList<KeyValueMemberName>(
                new KeyValueMemberName(AliasParamPredefinedTypes.SEED_GENERATOR_0_GET_0,getPredefTypes().getParams().getAliasSeedGenerator0Get0())
                )
        );
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_SUB_SEQUENCE_0, content.getCharSeq().getParams().getAliasCharSequence0SubSequence0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_SUB_SEQUENCE_1, content.getCharSeq().getParams().getAliasCharSequence0SubSequence1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_CHAR_AT_0, content.getCharSeq().getParams().getAliasCharSequence0CharAt0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_SUBSTRING_0, content.getCharSeq().getParams().getAliasCharSequence0Substring0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_SUBSTRING_1, content.getCharSeq().getParams().getAliasCharSequence0Substring1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_SUBSTRING_0, content.getCharSeq().getParams().getAliasCharSequence1Substring0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_COMPARE_TO_0, content.getCharSeq().getParams().getAliasCharSequence0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_CONTAINS_0, content.getCharSeq().getParams().getAliasCharSequence0Contains0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_STARTS_WITH_0, content.getCharSeq().getParams().getAliasCharSequence0StartsWith0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_STARTS_WITH_0, content.getCharSeq().getParams().getAliasCharSequence1StartsWith0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_STARTS_WITH_1, content.getCharSeq().getParams().getAliasCharSequence1StartsWith1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_ENDS_WITH_0, content.getCharSeq().getParams().getAliasCharSequence0EndsWith0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_INDEX_OF_0, content.getCharSeq().getParams().getAliasCharSequence0IndexOf0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_INDEX_OF_0, content.getCharSeq().getParams().getAliasCharSequence1IndexOf0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_INDEX_OF_1, content.getCharSeq().getParams().getAliasCharSequence1IndexOf1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_2_INDEX_OF_0, content.getCharSeq().getParams().getAliasCharSequence2IndexOf0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_3_INDEX_OF_0, content.getCharSeq().getParams().getAliasCharSequence3IndexOf0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_3_INDEX_OF_1, content.getCharSeq().getParams().getAliasCharSequence3IndexOf1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_LAST_INDEX_OF_0, content.getCharSeq().getParams().getAliasCharSequence0LastIndexOf0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_LAST_INDEX_OF_0, content.getCharSeq().getParams().getAliasCharSequence1LastIndexOf0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_LAST_INDEX_OF_1, content.getCharSeq().getParams().getAliasCharSequence1LastIndexOf1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_2_LAST_INDEX_OF_0, content.getCharSeq().getParams().getAliasCharSequence2LastIndexOf0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_3_LAST_INDEX_OF_0, content.getCharSeq().getParams().getAliasCharSequence3LastIndexOf0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_3_LAST_INDEX_OF_1, content.getCharSeq().getParams().getAliasCharSequence3LastIndexOf1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_FORMAT_0, content.getCharSeq().getParams().getAliasCharSequence0Format0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_SPLIT_0, content.getCharSeq().getParams().getAliasCharSequence0Split0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_0, content.getCharSeq().getParams().getAliasCharSequence1Split0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_1, content.getCharSeq().getParams().getAliasCharSequence1Split1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_2_SPLIT_0, content.getCharSeq().getParams().getAliasCharSequence2Split0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_3_SPLIT_0, content.getCharSeq().getParams().getAliasCharSequence3Split0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_3_SPLIT_1, content.getCharSeq().getParams().getAliasCharSequence3Split1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_SPLIT_STRINGS_0, content.getCharSeq().getParams().getAliasCharSequence0SplitStrings0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_STRINGS_0, content.getCharSeq().getParams().getAliasCharSequence1SplitStrings0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_1_SPLIT_STRINGS_1, content.getCharSeq().getParams().getAliasCharSequence1SplitStrings1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_SPLIT_CHARS_0, content.getCharSeq().getParams().getAliasCharSequence0SplitChars0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_0, content.getCharSeq().getParams().getAliasCharSequence0RegionMatches0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_1, content.getCharSeq().getParams().getAliasCharSequence0RegionMatches1()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_2, content.getCharSeq().getParams().getAliasCharSequence0RegionMatches2()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_REGION_MATCHES_3, content.getCharSeq().getParams().getAliasCharSequence0RegionMatches3())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_EQUALS_0, content.getCharSeq().getParams().getAliasCharSequence0Equals0()),new KeyValueMemberName(AliasParamCharSequence.CHAR_SEQUENCE_0_EQUALS_1, content.getCharSeq().getParams().getAliasCharSequence0Equals1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_0_EQUALS_IGNORE_CASE_0, content.getCharSeq().getParams().getAliasString0EqualsIgnoreCase0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_0_COMPARE_0, content.getCharSeq().getParams().getAliasString0Compare0()),new KeyValueMemberName(AliasParamCharSequence.STRING_0_COMPARE_1, content.getCharSeq().getParams().getAliasString0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_0_COMPARE_TO_IGNORE_CASE_0, content.getCharSeq().getParams().getAliasString0CompareToIgnoreCase0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_0_REPLACE_STRING_0, content.getCharSeq().getParams().getAliasString0ReplaceString0()),new KeyValueMemberName(AliasParamCharSequence.STRING_0_REPLACE_STRING_1, content.getCharSeq().getParams().getAliasString0ReplaceString1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_1_REPLACE_STRING_0, content.getCharSeq().getParams().getAliasString1ReplaceString0()),new KeyValueMemberName(AliasParamCharSequence.STRING_1_REPLACE_STRING_1, content.getCharSeq().getParams().getAliasString1ReplaceString1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_0_REPLACE_MULTIPLE_0, content.getCharSeq().getParams().getAliasString0ReplaceMultiple0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_0_REGION_MATCHES_0, content.getCharSeq().getParams().getAliasString0RegionMatches0()),new KeyValueMemberName(AliasParamCharSequence.STRING_0_REGION_MATCHES_1, content.getCharSeq().getParams().getAliasString0RegionMatches1()),new KeyValueMemberName(AliasParamCharSequence.STRING_0_REGION_MATCHES_2, content.getCharSeq().getParams().getAliasString0RegionMatches2()),new KeyValueMemberName(AliasParamCharSequence.STRING_0_REGION_MATCHES_3, content.getCharSeq().getParams().getAliasString0RegionMatches3()),new KeyValueMemberName(AliasParamCharSequence.STRING_0_REGION_MATCHES_4, content.getCharSeq().getParams().getAliasString0RegionMatches4())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_0_VALUE_OF_METHOD_0, content.getCharSeq().getParams().getAliasString0ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_1_VALUE_OF_METHOD_0, content.getCharSeq().getParams().getAliasString1ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_2_VALUE_OF_METHOD_0, content.getCharSeq().getParams().getAliasString2ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_3_VALUE_OF_METHOD_0, content.getCharSeq().getParams().getAliasString3ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_4_VALUE_OF_METHOD_0, content.getCharSeq().getParams().getAliasString4ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_5_VALUE_OF_METHOD_0, content.getCharSeq().getParams().getAliasString5ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_6_VALUE_OF_METHOD_0, content.getCharSeq().getParams().getAliasString6ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_7_VALUE_OF_METHOD_0, content.getCharSeq().getParams().getAliasString7ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_8_VALUE_OF_METHOD_0, content.getCharSeq().getParams().getAliasString8ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_9_VALUE_OF_METHOD_0, content.getCharSeq().getParams().getAliasString9ValueOfMethod0()),new KeyValueMemberName(AliasParamCharSequence.STRING_9_VALUE_OF_METHOD_1, content.getCharSeq().getParams().getAliasString9ValueOfMethod1()),new KeyValueMemberName(AliasParamCharSequence.STRING_9_VALUE_OF_METHOD_2, content.getCharSeq().getParams().getAliasString9ValueOfMethod2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_0_STRING_0, content.getCharSeq().getParams().getAliasString0String0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_1_STRING_0, content.getCharSeq().getParams().getAliasString1String0()),new KeyValueMemberName(AliasParamCharSequence.STRING_1_STRING_1, content.getCharSeq().getParams().getAliasString1String1()),new KeyValueMemberName(AliasParamCharSequence.STRING_1_STRING_2, content.getCharSeq().getParams().getAliasString1String2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_2_STRING_0, content.getCharSeq().getParams().getAliasString2String0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_3_STRING_0, content.getCharSeq().getParams().getAliasString3String0()),new KeyValueMemberName(AliasParamCharSequence.STRING_3_STRING_1, content.getCharSeq().getParams().getAliasString3String1()),new KeyValueMemberName(AliasParamCharSequence.STRING_3_STRING_2, content.getCharSeq().getParams().getAliasString3String2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_4_STRING_0, content.getCharSeq().getParams().getAliasString4String0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_APPEND_0, content.getCharSeq().getParams().getAliasStringBuilder0Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_1_APPEND_0, content.getCharSeq().getParams().getAliasStringBuilder1Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_2_APPEND_0, content.getCharSeq().getParams().getAliasStringBuilder2Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_3_APPEND_0, content.getCharSeq().getParams().getAliasStringBuilder3Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_4_APPEND_0, content.getCharSeq().getParams().getAliasStringBuilder4Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_5_APPEND_0, content.getCharSeq().getParams().getAliasStringBuilder5Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_6_APPEND_0, content.getCharSeq().getParams().getAliasStringBuilder6Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_7_APPEND_0, content.getCharSeq().getParams().getAliasStringBuilder7Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_8_APPEND_0, content.getCharSeq().getParams().getAliasStringBuilder8Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_9_APPEND_0, content.getCharSeq().getParams().getAliasStringBuilder9Append0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_9_APPEND_1, content.getCharSeq().getParams().getAliasStringBuilder9Append1()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_9_APPEND_2, content.getCharSeq().getParams().getAliasStringBuilder9Append2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_10_APPEND_0, content.getCharSeq().getParams().getAliasStringBuilder10Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_11_APPEND_0, content.getCharSeq().getParams().getAliasStringBuilder11Append0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_11_APPEND_1, content.getCharSeq().getParams().getAliasStringBuilder11Append1()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_11_APPEND_2, content.getCharSeq().getParams().getAliasStringBuilder11Append2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_12_APPEND_0, content.getCharSeq().getParams().getAliasStringBuilder12Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_13_APPEND_0, content.getCharSeq().getParams().getAliasStringBuilder13Append0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_13_APPEND_1, content.getCharSeq().getParams().getAliasStringBuilder13Append1()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_13_APPEND_2, content.getCharSeq().getParams().getAliasStringBuilder13Append2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_DELETE_0, content.getCharSeq().getParams().getAliasStringBuilder0Delete0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_DELETE_1, content.getCharSeq().getParams().getAliasStringBuilder0Delete1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_DELETE_CHAR_AT_0, content.getCharSeq().getParams().getAliasStringBuilder0DeleteCharAt0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_INSERT_0, content.getCharSeq().getParams().getAliasStringBuilder0Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_INSERT_1, content.getCharSeq().getParams().getAliasStringBuilder0Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_1_INSERT_0, content.getCharSeq().getParams().getAliasStringBuilder1Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_1_INSERT_1, content.getCharSeq().getParams().getAliasStringBuilder1Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_2_INSERT_0, content.getCharSeq().getParams().getAliasStringBuilder2Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_2_INSERT_1, content.getCharSeq().getParams().getAliasStringBuilder2Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_3_INSERT_0, content.getCharSeq().getParams().getAliasStringBuilder3Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_3_INSERT_1, content.getCharSeq().getParams().getAliasStringBuilder3Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_4_INSERT_0, content.getCharSeq().getParams().getAliasStringBuilder4Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_4_INSERT_1, content.getCharSeq().getParams().getAliasStringBuilder4Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_5_INSERT_0, content.getCharSeq().getParams().getAliasStringBuilder5Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_5_INSERT_1, content.getCharSeq().getParams().getAliasStringBuilder5Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_6_INSERT_0, content.getCharSeq().getParams().getAliasStringBuilder6Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_6_INSERT_1, content.getCharSeq().getParams().getAliasStringBuilder6Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_7_INSERT_0, content.getCharSeq().getParams().getAliasStringBuilder7Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_7_INSERT_1, content.getCharSeq().getParams().getAliasStringBuilder7Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_8_INSERT_0, content.getCharSeq().getParams().getAliasStringBuilder8Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_8_INSERT_1, content.getCharSeq().getParams().getAliasStringBuilder8Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_9_INSERT_0, content.getCharSeq().getParams().getAliasStringBuilder9Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_9_INSERT_1, content.getCharSeq().getParams().getAliasStringBuilder9Insert1()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_9_INSERT_2, content.getCharSeq().getParams().getAliasStringBuilder9Insert2()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_9_INSERT_3, content.getCharSeq().getParams().getAliasStringBuilder9Insert3())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_10_INSERT_0, content.getCharSeq().getParams().getAliasStringBuilder10Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_10_INSERT_1, content.getCharSeq().getParams().getAliasStringBuilder10Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_11_INSERT_0, content.getCharSeq().getParams().getAliasStringBuilder11Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_11_INSERT_1, content.getCharSeq().getParams().getAliasStringBuilder11Insert1()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_11_INSERT_2, content.getCharSeq().getParams().getAliasStringBuilder11Insert2()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_11_INSERT_3, content.getCharSeq().getParams().getAliasStringBuilder11Insert3())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_12_INSERT_0, content.getCharSeq().getParams().getAliasStringBuilder12Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_12_INSERT_1, content.getCharSeq().getParams().getAliasStringBuilder12Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_13_INSERT_0, content.getCharSeq().getParams().getAliasStringBuilder13Insert0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_13_INSERT_1, content.getCharSeq().getParams().getAliasStringBuilder13Insert1()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_13_INSERT_2, content.getCharSeq().getParams().getAliasStringBuilder13Insert2()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_13_INSERT_3, content.getCharSeq().getParams().getAliasStringBuilder13Insert3())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_REPLACE_0, content.getCharSeq().getParams().getAliasStringBuilder0Replace0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_REPLACE_1, content.getCharSeq().getParams().getAliasStringBuilder0Replace1()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_REPLACE_2, content.getCharSeq().getParams().getAliasStringBuilder0Replace2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_SET_CHAR_AT_0, content.getCharSeq().getParams().getAliasStringBuilder0SetCharAt0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_SET_CHAR_AT_1, content.getCharSeq().getParams().getAliasStringBuilder0SetCharAt1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_SET_LENGTH_0, content.getCharSeq().getParams().getAliasStringBuilder0SetLength0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_ENSURE_CAPACITY_0, content.getCharSeq().getParams().getAliasStringBuilder0EnsureCapacity0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_SAME_0, content.getCharSeq().getParams().getAliasStringBuilder0Same0()),new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_SAME_1, content.getCharSeq().getParams().getAliasStringBuilder0Same1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_0_STRING_BUILDER_0, content.getCharSeq().getParams().getAliasStringBuilder0StringBuilder0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_1_STRING_BUILDER_0, content.getCharSeq().getParams().getAliasStringBuilder1StringBuilder0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.STRING_BUILDER_2_STRING_BUILDER_0, content.getCharSeq().getParams().getAliasStringBuilder2StringBuilder0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCharSequence.REPLACEMENT_0_REPLACEMENT_0, content.getCharSeq().getParams().getAliasReplacement0Replacement0()),new KeyValueMemberName(AliasParamCharSequence.REPLACEMENT_0_REPLACEMENT_1, content.getCharSeq().getParams().getAliasReplacement0Replacement1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.ERROR_0_CURRENT_STACK_0, content.getCoreNames().getParams().getAliasError0CurrentStack0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.ERROR_0_TO_STRING_METHOD_0, content.getCoreNames().getParams().getAliasError0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.ENUMS_0_NAME_0, content.getCoreNames().getParams().getAliasEnums0Name0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.ENUMS_0_ORDINAL_0, content.getCoreNames().getParams().getAliasEnums0Ordinal0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.OBJECTS_UTIL_0_SAME_REF_0, content.getCoreNames().getParams().getAliasObjectsUtil0SameRef0()),new KeyValueMemberName(AliasParamCore.OBJECTS_UTIL_0_SAME_REF_1, content.getCoreNames().getParams().getAliasObjectsUtil0SameRef1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.OBJECTS_UTIL_0_GET_PARENT_0, content.getCoreNames().getParams().getAliasObjectsUtil0GetParent0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.OBJECTS_UTIL_0_SET_PARENT_0, content.getCoreNames().getParams().getAliasObjectsUtil0SetParent0()),new KeyValueMemberName(AliasParamCore.OBJECTS_UTIL_0_SET_PARENT_1, content.getCoreNames().getParams().getAliasObjectsUtil0SetParent1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.STRING_UTIL_0_VALUE_OF_METHOD_0, content.getCoreNames().getParams().getAliasStringUtil0ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.RESOURCES_0_READ_RESOURCES_0, content.getCoreNames().getParams().getAliasResources0ReadResources0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamCore.RESOURCES_0_READ_RESOURCES_INDEX_0, content.getCoreNames().getParams().getAliasResources0ReadResourcesIndex0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_ABS_0, content.getMathRef().getParams().getAliasMath0Abs0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_ABS_0, content.getMathRef().getParams().getAliasMath1Abs0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_QUOT_0, content.getMathRef().getParams().getAliasMath0Quot0()),new KeyValueMemberName(AliasParamMath.MATH_0_QUOT_1, content.getMathRef().getParams().getAliasMath0Quot1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_QUOT_0, content.getMathRef().getParams().getAliasMath1Quot0()),new KeyValueMemberName(AliasParamMath.MATH_1_QUOT_1, content.getMathRef().getParams().getAliasMath1Quot1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_MOD_0, content.getMathRef().getParams().getAliasMath0Mod0()),new KeyValueMemberName(AliasParamMath.MATH_0_MOD_1, content.getMathRef().getParams().getAliasMath0Mod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_MOD_0, content.getMathRef().getParams().getAliasMath1Mod0()),new KeyValueMemberName(AliasParamMath.MATH_1_MOD_1, content.getMathRef().getParams().getAliasMath1Mod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_PLUS_0, content.getMathRef().getParams().getAliasMath0Plus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_PLUS_0, content.getMathRef().getParams().getAliasMath1Plus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_2_PLUS_0, content.getMathRef().getParams().getAliasMath2Plus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_3_PLUS_0, content.getMathRef().getParams().getAliasMath3Plus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_MINUS_0, content.getMathRef().getParams().getAliasMath0Minus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_MINUS_0, content.getMathRef().getParams().getAliasMath1Minus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_2_MINUS_0, content.getMathRef().getParams().getAliasMath2Minus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_3_MINUS_0, content.getMathRef().getParams().getAliasMath3Minus0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_NEG_0, content.getMathRef().getParams().getAliasMath0Neg0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_NEG_BIN_0, content.getMathRef().getParams().getAliasMath0NegBin0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_NEG_BIN_0, content.getMathRef().getParams().getAliasMath1NegBin0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_4_PLUS_0, content.getMathRef().getParams().getAliasMath4Plus0()),new KeyValueMemberName(AliasParamMath.MATH_4_PLUS_1, content.getMathRef().getParams().getAliasMath4Plus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_5_PLUS_0, content.getMathRef().getParams().getAliasMath5Plus0()),new KeyValueMemberName(AliasParamMath.MATH_5_PLUS_1, content.getMathRef().getParams().getAliasMath5Plus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_6_PLUS_0, content.getMathRef().getParams().getAliasMath6Plus0()),new KeyValueMemberName(AliasParamMath.MATH_6_PLUS_1, content.getMathRef().getParams().getAliasMath6Plus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_7_PLUS_0, content.getMathRef().getParams().getAliasMath7Plus0()),new KeyValueMemberName(AliasParamMath.MATH_7_PLUS_1, content.getMathRef().getParams().getAliasMath7Plus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_4_MINUS_0, content.getMathRef().getParams().getAliasMath4Minus0()),new KeyValueMemberName(AliasParamMath.MATH_4_MINUS_1, content.getMathRef().getParams().getAliasMath4Minus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_5_MINUS_0, content.getMathRef().getParams().getAliasMath5Minus0()),new KeyValueMemberName(AliasParamMath.MATH_5_MINUS_1, content.getMathRef().getParams().getAliasMath5Minus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_6_MINUS_0, content.getMathRef().getParams().getAliasMath6Minus0()),new KeyValueMemberName(AliasParamMath.MATH_6_MINUS_1, content.getMathRef().getParams().getAliasMath6Minus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_7_MINUS_0, content.getMathRef().getParams().getAliasMath7Minus0()),new KeyValueMemberName(AliasParamMath.MATH_7_MINUS_1, content.getMathRef().getParams().getAliasMath7Minus1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_MULT_0, content.getMathRef().getParams().getAliasMath0Mult0()),new KeyValueMemberName(AliasParamMath.MATH_0_MULT_1, content.getMathRef().getParams().getAliasMath0Mult1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_MULT_0, content.getMathRef().getParams().getAliasMath1Mult0()),new KeyValueMemberName(AliasParamMath.MATH_1_MULT_1, content.getMathRef().getParams().getAliasMath1Mult1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_2_MULT_0, content.getMathRef().getParams().getAliasMath2Mult0()),new KeyValueMemberName(AliasParamMath.MATH_2_MULT_1, content.getMathRef().getParams().getAliasMath2Mult1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_3_MULT_0, content.getMathRef().getParams().getAliasMath3Mult0()),new KeyValueMemberName(AliasParamMath.MATH_3_MULT_1, content.getMathRef().getParams().getAliasMath3Mult1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_BIN_QUOT_0, content.getMathRef().getParams().getAliasMath0BinQuot0()),new KeyValueMemberName(AliasParamMath.MATH_0_BIN_QUOT_1, content.getMathRef().getParams().getAliasMath0BinQuot1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_BIN_QUOT_0, content.getMathRef().getParams().getAliasMath1BinQuot0()),new KeyValueMemberName(AliasParamMath.MATH_1_BIN_QUOT_1, content.getMathRef().getParams().getAliasMath1BinQuot1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_2_BIN_QUOT_0, content.getMathRef().getParams().getAliasMath2BinQuot0()),new KeyValueMemberName(AliasParamMath.MATH_2_BIN_QUOT_1, content.getMathRef().getParams().getAliasMath2BinQuot1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_3_BIN_QUOT_0, content.getMathRef().getParams().getAliasMath3BinQuot0()),new KeyValueMemberName(AliasParamMath.MATH_3_BIN_QUOT_1, content.getMathRef().getParams().getAliasMath3BinQuot1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_BIN_MOD_0, content.getMathRef().getParams().getAliasMath0BinMod0()),new KeyValueMemberName(AliasParamMath.MATH_0_BIN_MOD_1, content.getMathRef().getParams().getAliasMath0BinMod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_BIN_MOD_0, content.getMathRef().getParams().getAliasMath1BinMod0()),new KeyValueMemberName(AliasParamMath.MATH_1_BIN_MOD_1, content.getMathRef().getParams().getAliasMath1BinMod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_2_BIN_MOD_0, content.getMathRef().getParams().getAliasMath2BinMod0()),new KeyValueMemberName(AliasParamMath.MATH_2_BIN_MOD_1, content.getMathRef().getParams().getAliasMath2BinMod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_3_BIN_MOD_0, content.getMathRef().getParams().getAliasMath3BinMod0()),new KeyValueMemberName(AliasParamMath.MATH_3_BIN_MOD_1, content.getMathRef().getParams().getAliasMath3BinMod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_AND_0, content.getMathRef().getParams().getAliasMath0And0()),new KeyValueMemberName(AliasParamMath.MATH_0_AND_1, content.getMathRef().getParams().getAliasMath0And1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_AND_0, content.getMathRef().getParams().getAliasMath1And0()),new KeyValueMemberName(AliasParamMath.MATH_1_AND_1, content.getMathRef().getParams().getAliasMath1And1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_2_AND_0, content.getMathRef().getParams().getAliasMath2And0()),new KeyValueMemberName(AliasParamMath.MATH_2_AND_1, content.getMathRef().getParams().getAliasMath2And1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_OR_0, content.getMathRef().getParams().getAliasMath0Or0()),new KeyValueMemberName(AliasParamMath.MATH_0_OR_1, content.getMathRef().getParams().getAliasMath0Or1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_OR_0, content.getMathRef().getParams().getAliasMath1Or0()),new KeyValueMemberName(AliasParamMath.MATH_1_OR_1, content.getMathRef().getParams().getAliasMath1Or1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_2_OR_0, content.getMathRef().getParams().getAliasMath2Or0()),new KeyValueMemberName(AliasParamMath.MATH_2_OR_1, content.getMathRef().getParams().getAliasMath2Or1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_XOR_0, content.getMathRef().getParams().getAliasMath0Xor0()),new KeyValueMemberName(AliasParamMath.MATH_0_XOR_1, content.getMathRef().getParams().getAliasMath0Xor1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_XOR_0, content.getMathRef().getParams().getAliasMath1Xor0()),new KeyValueMemberName(AliasParamMath.MATH_1_XOR_1, content.getMathRef().getParams().getAliasMath1Xor1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_2_XOR_0, content.getMathRef().getParams().getAliasMath2Xor0()),new KeyValueMemberName(AliasParamMath.MATH_2_XOR_1, content.getMathRef().getParams().getAliasMath2Xor1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_SHIFT_LEFT_0, content.getMathRef().getParams().getAliasMath0ShiftLeft0()),new KeyValueMemberName(AliasParamMath.MATH_0_SHIFT_LEFT_1, content.getMathRef().getParams().getAliasMath0ShiftLeft1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_SHIFT_LEFT_0, content.getMathRef().getParams().getAliasMath1ShiftLeft0()),new KeyValueMemberName(AliasParamMath.MATH_1_SHIFT_LEFT_1, content.getMathRef().getParams().getAliasMath1ShiftLeft1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_SHIFT_RIGHT_0, content.getMathRef().getParams().getAliasMath0ShiftRight0()),new KeyValueMemberName(AliasParamMath.MATH_0_SHIFT_RIGHT_1, content.getMathRef().getParams().getAliasMath0ShiftRight1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_SHIFT_RIGHT_0, content.getMathRef().getParams().getAliasMath1ShiftRight0()),new KeyValueMemberName(AliasParamMath.MATH_1_SHIFT_RIGHT_1, content.getMathRef().getParams().getAliasMath1ShiftRight1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_BIT_SHIFT_LEFT_0, content.getMathRef().getParams().getAliasMath0BitShiftLeft0()),new KeyValueMemberName(AliasParamMath.MATH_0_BIT_SHIFT_LEFT_1, content.getMathRef().getParams().getAliasMath0BitShiftLeft1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_BIT_SHIFT_LEFT_0, content.getMathRef().getParams().getAliasMath1BitShiftLeft0()),new KeyValueMemberName(AliasParamMath.MATH_1_BIT_SHIFT_LEFT_1, content.getMathRef().getParams().getAliasMath1BitShiftLeft1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_BIT_SHIFT_RIGHT_0, content.getMathRef().getParams().getAliasMath0BitShiftRight0()),new KeyValueMemberName(AliasParamMath.MATH_0_BIT_SHIFT_RIGHT_1, content.getMathRef().getParams().getAliasMath0BitShiftRight1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_BIT_SHIFT_RIGHT_0, content.getMathRef().getParams().getAliasMath1BitShiftRight0()),new KeyValueMemberName(AliasParamMath.MATH_1_BIT_SHIFT_RIGHT_1, content.getMathRef().getParams().getAliasMath1BitShiftRight1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_ROTATE_LEFT_0, content.getMathRef().getParams().getAliasMath0RotateLeft0()),new KeyValueMemberName(AliasParamMath.MATH_0_ROTATE_LEFT_1, content.getMathRef().getParams().getAliasMath0RotateLeft1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_ROTATE_LEFT_0, content.getMathRef().getParams().getAliasMath1RotateLeft0()),new KeyValueMemberName(AliasParamMath.MATH_1_ROTATE_LEFT_1, content.getMathRef().getParams().getAliasMath1RotateLeft1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_ROTATE_RIGHT_0, content.getMathRef().getParams().getAliasMath0RotateRight0()),new KeyValueMemberName(AliasParamMath.MATH_0_ROTATE_RIGHT_1, content.getMathRef().getParams().getAliasMath0RotateRight1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_ROTATE_RIGHT_0, content.getMathRef().getParams().getAliasMath1RotateRight0()),new KeyValueMemberName(AliasParamMath.MATH_1_ROTATE_RIGHT_1, content.getMathRef().getParams().getAliasMath1RotateRight1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_LE_0, content.getMathRef().getParams().getAliasMath0Le0()),new KeyValueMemberName(AliasParamMath.MATH_0_LE_1, content.getMathRef().getParams().getAliasMath0Le1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_GE_0, content.getMathRef().getParams().getAliasMath0Ge0()),new KeyValueMemberName(AliasParamMath.MATH_0_GE_1, content.getMathRef().getParams().getAliasMath0Ge1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_LT_0, content.getMathRef().getParams().getAliasMath0Lt0()),new KeyValueMemberName(AliasParamMath.MATH_0_LT_1, content.getMathRef().getParams().getAliasMath0Lt1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_GT_0, content.getMathRef().getParams().getAliasMath0Gt0()),new KeyValueMemberName(AliasParamMath.MATH_0_GT_1, content.getMathRef().getParams().getAliasMath0Gt1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_LE_0, content.getMathRef().getParams().getAliasMath1Le0()),new KeyValueMemberName(AliasParamMath.MATH_1_LE_1, content.getMathRef().getParams().getAliasMath1Le1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_GE_0, content.getMathRef().getParams().getAliasMath1Ge0()),new KeyValueMemberName(AliasParamMath.MATH_1_GE_1, content.getMathRef().getParams().getAliasMath1Ge1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_LT_0, content.getMathRef().getParams().getAliasMath1Lt0()),new KeyValueMemberName(AliasParamMath.MATH_1_LT_1, content.getMathRef().getParams().getAliasMath1Lt1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_1_GT_0, content.getMathRef().getParams().getAliasMath1Gt0()),new KeyValueMemberName(AliasParamMath.MATH_1_GT_1, content.getMathRef().getParams().getAliasMath1Gt1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_RANDOM_0, content.getMathRef().getParams().getAliasMath0Random0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamMath.MATH_0_SEED_0, content.getMathRef().getParams().getAliasMath0Seed0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BOOLEAN_0_COMPARE_0, content.getNbAlias().getParams().getAliasBoolean0Compare0()),new KeyValueMemberName(AliasParamNumber.BOOLEAN_0_COMPARE_1, content.getNbAlias().getParams().getAliasBoolean0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BOOLEAN_0_COMPARE_TO_0, content.getNbAlias().getParams().getAliasBoolean0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BOOLEAN_0_EQUALS_0, content.getNbAlias().getParams().getAliasBoolean0Equals0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BOOLEAN_0_PARSE_BOOLEAN_0, content.getNbAlias().getParams().getAliasBoolean0ParseBoolean0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BOOLEAN_0_TO_STRING_METHOD_0, content.getNbAlias().getParams().getAliasBoolean0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BOOLEAN_0_VALUE_OF_METHOD_0, content.getNbAlias().getParams().getAliasBoolean0ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BOOLEAN_1_VALUE_OF_METHOD_0, content.getNbAlias().getParams().getAliasBoolean1ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BOOLEAN_0_BOOLEAN_0, content.getNbAlias().getParams().getAliasBoolean0Boolean0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BOOLEAN_1_BOOLEAN_0, content.getNbAlias().getParams().getAliasBoolean1Boolean0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_0_TO_STRING_METHOD_0, content.getNbAlias().getParams().getAliasByte0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_0_PARSE_BYTE_0, content.getNbAlias().getParams().getAliasByte0ParseByte0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_1_PARSE_BYTE_0, content.getNbAlias().getParams().getAliasByte1ParseByte0()),new KeyValueMemberName(AliasParamNumber.BYTE_1_PARSE_BYTE_1, content.getNbAlias().getParams().getAliasByte1ParseByte1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_0_COMPARE_TO_0, content.getNbAlias().getParams().getAliasByte0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_0_COMPARE_0, content.getNbAlias().getParams().getAliasByte0Compare0()),new KeyValueMemberName(AliasParamNumber.BYTE_0_COMPARE_1, content.getNbAlias().getParams().getAliasByte0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_0_PARSE_BYTE_OR_NULL_0, content.getNbAlias().getParams().getAliasByte0ParseByteOrNull0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_1_PARSE_BYTE_OR_NULL_0, content.getNbAlias().getParams().getAliasByte1ParseByteOrNull0()),new KeyValueMemberName(AliasParamNumber.BYTE_1_PARSE_BYTE_OR_NULL_1, content.getNbAlias().getParams().getAliasByte1ParseByteOrNull1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_0_BYTE_0, content.getNbAlias().getParams().getAliasByte0Byte0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.BYTE_1_BYTE_0, content.getNbAlias().getParams().getAliasByte1Byte0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_0_TO_STRING_METHOD_0, content.getNbAlias().getParams().getAliasShort0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_0_PARSE_SHORT_0, content.getNbAlias().getParams().getAliasShort0ParseShort0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_1_PARSE_SHORT_0, content.getNbAlias().getParams().getAliasShort1ParseShort0()),new KeyValueMemberName(AliasParamNumber.SHORT_1_PARSE_SHORT_1, content.getNbAlias().getParams().getAliasShort1ParseShort1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_0_COMPARE_TO_0, content.getNbAlias().getParams().getAliasShort0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_0_COMPARE_0, content.getNbAlias().getParams().getAliasShort0Compare0()),new KeyValueMemberName(AliasParamNumber.SHORT_0_COMPARE_1, content.getNbAlias().getParams().getAliasShort0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_0_PARSE_SHORT_OR_NULL_0, content.getNbAlias().getParams().getAliasShort0ParseShortOrNull0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_1_PARSE_SHORT_OR_NULL_0, content.getNbAlias().getParams().getAliasShort1ParseShortOrNull0()),new KeyValueMemberName(AliasParamNumber.SHORT_1_PARSE_SHORT_OR_NULL_1, content.getNbAlias().getParams().getAliasShort1ParseShortOrNull1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_0_SHORT_0, content.getNbAlias().getParams().getAliasShort0Short0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.SHORT_1_SHORT_0, content.getNbAlias().getParams().getAliasShort1Short0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_0_TO_STRING_METHOD_0, content.getNbAlias().getParams().getAliasInteger0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_0_PARSE_INT_0, content.getNbAlias().getParams().getAliasInteger0ParseInt0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_1_PARSE_INT_0, content.getNbAlias().getParams().getAliasInteger1ParseInt0()),new KeyValueMemberName(AliasParamNumber.INTEGER_1_PARSE_INT_1, content.getNbAlias().getParams().getAliasInteger1ParseInt1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_0_COMPARE_TO_0, content.getNbAlias().getParams().getAliasInteger0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_0_COMPARE_0, content.getNbAlias().getParams().getAliasInteger0Compare0()),new KeyValueMemberName(AliasParamNumber.INTEGER_0_COMPARE_1, content.getNbAlias().getParams().getAliasInteger0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_0_PARSE_INT_OR_NULL_0, content.getNbAlias().getParams().getAliasInteger0ParseIntOrNull0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_1_PARSE_INT_OR_NULL_0, content.getNbAlias().getParams().getAliasInteger1ParseIntOrNull0()),new KeyValueMemberName(AliasParamNumber.INTEGER_1_PARSE_INT_OR_NULL_1, content.getNbAlias().getParams().getAliasInteger1ParseIntOrNull1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_0_INTEGER_0, content.getNbAlias().getParams().getAliasInteger0Integer0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.INTEGER_1_INTEGER_0, content.getNbAlias().getParams().getAliasInteger1Integer0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_0_TO_STRING_METHOD_0, content.getNbAlias().getParams().getAliasLong0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_0_PARSE_LONG_0, content.getNbAlias().getParams().getAliasLong0ParseLong0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_1_PARSE_LONG_0, content.getNbAlias().getParams().getAliasLong1ParseLong0()),new KeyValueMemberName(AliasParamNumber.LONG_1_PARSE_LONG_1, content.getNbAlias().getParams().getAliasLong1ParseLong1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_0_COMPARE_TO_0, content.getNbAlias().getParams().getAliasLong0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_0_COMPARE_0, content.getNbAlias().getParams().getAliasLong0Compare0()),new KeyValueMemberName(AliasParamNumber.LONG_0_COMPARE_1, content.getNbAlias().getParams().getAliasLong0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_0_PARSE_LONG_OR_NULL_0, content.getNbAlias().getParams().getAliasLong0ParseLongOrNull0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_1_PARSE_LONG_OR_NULL_0, content.getNbAlias().getParams().getAliasLong1ParseLongOrNull0()),new KeyValueMemberName(AliasParamNumber.LONG_1_PARSE_LONG_OR_NULL_1, content.getNbAlias().getParams().getAliasLong1ParseLongOrNull1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_0_LONG_0, content.getNbAlias().getParams().getAliasLong0Long0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.LONG_1_LONG_0, content.getNbAlias().getParams().getAliasLong1Long0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.FLOAT_0_TO_STRING_METHOD_0, content.getNbAlias().getParams().getAliasFloat0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.FLOAT_0_PARSE_FLOAT_0, content.getNbAlias().getParams().getAliasFloat0ParseFloat0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.FLOAT_0_COMPARE_TO_0, content.getNbAlias().getParams().getAliasFloat0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.FLOAT_0_COMPARE_0, content.getNbAlias().getParams().getAliasFloat0Compare0()),new KeyValueMemberName(AliasParamNumber.FLOAT_0_COMPARE_1, content.getNbAlias().getParams().getAliasFloat0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.FLOAT_0_PARSE_FLOAT_OR_NULL_0, content.getNbAlias().getParams().getAliasFloat0ParseFloatOrNull0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.FLOAT_0_IS_INFINITE_0, content.getNbAlias().getParams().getAliasFloat0IsInfinite0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.FLOAT_0_IS_NAN_0, content.getNbAlias().getParams().getAliasFloat0IsNan0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.FLOAT_0_FLOAT_0, content.getNbAlias().getParams().getAliasFloat0Float0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.FLOAT_1_FLOAT_0, content.getNbAlias().getParams().getAliasFloat1Float0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.DOUBLE_0_TO_STRING_METHOD_0, content.getNbAlias().getParams().getAliasDouble0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.DOUBLE_0_PARSE_DOUBLE_0, content.getNbAlias().getParams().getAliasDouble0ParseDouble0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.DOUBLE_0_COMPARE_TO_0, content.getNbAlias().getParams().getAliasDouble0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.DOUBLE_0_COMPARE_0, content.getNbAlias().getParams().getAliasDouble0Compare0()),new KeyValueMemberName(AliasParamNumber.DOUBLE_0_COMPARE_1, content.getNbAlias().getParams().getAliasDouble0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.DOUBLE_0_PARSE_DOUBLE_OR_NULL_0, content.getNbAlias().getParams().getAliasDouble0ParseDoubleOrNull0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.DOUBLE_0_IS_INFINITE_0, content.getNbAlias().getParams().getAliasDouble0IsInfinite0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.DOUBLE_0_IS_NAN_0, content.getNbAlias().getParams().getAliasDouble0IsNan0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.DOUBLE_0_DOUBLE_0, content.getNbAlias().getParams().getAliasDouble0Double0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.DOUBLE_1_DOUBLE_0, content.getNbAlias().getParams().getAliasDouble1Double0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.NUMBER_0_TO_STRING_METHOD_0, content.getNbAlias().getParams().getAliasNumber0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.NUMBER_0_EQUALS_0, content.getNbAlias().getParams().getAliasNumber0Equals0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.NUMBER_1_EQUALS_0, content.getNbAlias().getParams().getAliasNumber1Equals0()),new KeyValueMemberName(AliasParamNumber.NUMBER_1_EQUALS_1, content.getNbAlias().getParams().getAliasNumber1Equals1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.NUMBER_0_COMPARE_TO_0, content.getNbAlias().getParams().getAliasNumber0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.NUMBER_0_COMPARE_0, content.getNbAlias().getParams().getAliasNumber0Compare0()),new KeyValueMemberName(AliasParamNumber.NUMBER_0_COMPARE_1, content.getNbAlias().getParams().getAliasNumber0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_COMPARE_TO_0, content.getNbAlias().getParams().getAliasCharacter0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_COMPARE_0, content.getNbAlias().getParams().getAliasCharacter0Compare0()),new KeyValueMemberName(AliasParamNumber.CHARACTER_0_COMPARE_1, content.getNbAlias().getParams().getAliasCharacter0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_DIGIT_0, content.getNbAlias().getParams().getAliasCharacter0Digit0()),new KeyValueMemberName(AliasParamNumber.CHARACTER_0_DIGIT_1, content.getNbAlias().getParams().getAliasCharacter0Digit1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_FOR_DIGIT_0, content.getNbAlias().getParams().getAliasCharacter0ForDigit0()),new KeyValueMemberName(AliasParamNumber.CHARACTER_0_FOR_DIGIT_1, content.getNbAlias().getParams().getAliasCharacter0ForDigit1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_GET_DIRECTIONALITY_0, content.getNbAlias().getParams().getAliasCharacter0GetDirectionality0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_GET_TYPE_0, content.getNbAlias().getParams().getAliasCharacter0GetType0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_IS_DIGIT_0, content.getNbAlias().getParams().getAliasCharacter0IsDigit0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_IS_LETTER_0, content.getNbAlias().getParams().getAliasCharacter0IsLetter0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_IS_LETTER_OR_DIGIT_0, content.getNbAlias().getParams().getAliasCharacter0IsLetterOrDigit0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_IS_WORD_CHAR_0, content.getNbAlias().getParams().getAliasCharacter0IsWordChar0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_IS_WHITESPACE_0, content.getNbAlias().getParams().getAliasCharacter0IsWhitespace0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_IS_LOWER_CASE_0, content.getNbAlias().getParams().getAliasCharacter0IsLowerCase0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_IS_UPPER_CASE_0, content.getNbAlias().getParams().getAliasCharacter0IsUpperCase0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_IS_SPACE_0, content.getNbAlias().getParams().getAliasCharacter0IsSpace0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_TO_LOWER_CASE_CHAR_0, content.getNbAlias().getParams().getAliasCharacter0ToLowerCaseChar0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_TO_UPPER_CASE_CHAR_0, content.getNbAlias().getParams().getAliasCharacter0ToUpperCaseChar0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_TO_STRING_METHOD_0, content.getNbAlias().getParams().getAliasCharacter0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamNumber.CHARACTER_0_CHARACTER_0, content.getNbAlias().getParams().getAliasCharacter0Character0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.FCT_0_CALL_0, content.getReflect().getParams().getAliasFct0Call0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_CLASS_0, content.getReflect().getParams().getAliasClassType0GetClass0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_FOR_NAME_0, content.getReflect().getParams().getAliasClassType0ForName0()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_FOR_NAME_1, content.getReflect().getParams().getAliasClassType0ForName1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_1_FOR_NAME_0, content.getReflect().getParams().getAliasClassType1ForName0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_IS_INSTANCE_0, content.getReflect().getParams().getAliasClassType0IsInstance0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_IS_ASSIGNABLE_FROM_0, content.getReflect().getParams().getAliasClassType0IsAssignableFrom0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_DEFAULT_INSTANCE_0, content.getReflect().getParams().getAliasClassType0DefaultInstance0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_ENUM_VALUE_OF_0, content.getReflect().getParams().getAliasClassType0EnumValueOf0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_0, content.getReflect().getParams().getAliasClassType0GetDeclaredConstructors0()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_1, content.getReflect().getParams().getAliasClassType0GetDeclaredConstructors1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_FIELDS_0, content.getReflect().getParams().getAliasClassType0GetDeclaredFields0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_0, content.getReflect().getParams().getAliasClassType0GetDeclaredStaticMethods0()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_1, content.getReflect().getParams().getAliasClassType0GetDeclaredStaticMethods1()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_2, content.getReflect().getParams().getAliasClassType0GetDeclaredStaticMethods2()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_3, content.getReflect().getParams().getAliasClassType0GetDeclaredStaticMethods3())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_0, content.getReflect().getParams().getAliasClassType0GetDeclaredMethods0()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_1, content.getReflect().getParams().getAliasClassType0GetDeclaredMethods1()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_2, content.getReflect().getParams().getAliasClassType0GetDeclaredMethods2()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_METHODS_3, content.getReflect().getParams().getAliasClassType0GetDeclaredMethods3())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_EXPLICITS_0, content.getReflect().getParams().getAliasClassType0GetDeclaredExplicits0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_IMPLICITS_0, content.getReflect().getParams().getAliasClassType0GetDeclaredImplicits0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_BLOCKS_0, content.getReflect().getParams().getAliasClassType0GetDeclaredBlocks0()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_DECLARED_BLOCKS_1, content.getReflect().getParams().getAliasClassType0GetDeclaredBlocks1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_MAKE_GENERIC_0, content.getReflect().getParams().getAliasClassType0MakeGeneric0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_MAKE_WILD_CARD_0, content.getReflect().getParams().getAliasClassType0MakeWildCard0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_OPERATORS_0, content.getReflect().getParams().getAliasClassType0GetOperators0()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_OPERATORS_1, content.getReflect().getParams().getAliasClassType0GetOperators1()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_GET_OPERATORS_2, content.getReflect().getParams().getAliasClassType0GetOperators2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_ARRAY_NEW_INSTANCE_0, content.getReflect().getParams().getAliasClassType0ArrayNewInstance0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_ARRAY_GET_LENGTH_0, content.getReflect().getParams().getAliasClassType0ArrayGetLength0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_ARRAY_GET_0, content.getReflect().getParams().getAliasClassType0ArrayGet0()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_ARRAY_GET_1, content.getReflect().getParams().getAliasClassType0ArrayGet1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_ARRAY_SET_0, content.getReflect().getParams().getAliasClassType0ArraySet0()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_ARRAY_SET_1, content.getReflect().getParams().getAliasClassType0ArraySet1()),new KeyValueMemberName(AliasParamReflection.CLASS_TYPE_0_ARRAY_SET_2, content.getReflect().getParams().getAliasClassType0ArraySet2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.CONSTRUCTOR_0_NEW_INSTANCE_0, content.getReflect().getParams().getAliasConstructor0NewInstance0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.FIELD_0_GET_FIELD_0, content.getReflect().getParams().getAliasField0GetField0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.FIELD_0_SET_FIELD_0, content.getReflect().getParams().getAliasField0SetField0()),new KeyValueMemberName(AliasParamReflection.FIELD_0_SET_FIELD_1, content.getReflect().getParams().getAliasField0SetField1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_0_INVOKE_0, content.getReflect().getParams().getAliasMethod0Invoke0()),new KeyValueMemberName(AliasParamReflection.METHOD_0_INVOKE_1, content.getReflect().getParams().getAliasMethod0Invoke1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_0_INVOKE_DIRECT_0, content.getReflect().getParams().getAliasMethod0InvokeDirect0()),new KeyValueMemberName(AliasParamReflection.METHOD_0_INVOKE_DIRECT_1, content.getReflect().getParams().getAliasMethod0InvokeDirect1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0, content.getReflect().getParams().getAliasMethod0GetDeclaredAnonymousLambdaLocalVars0()),new KeyValueMemberName(AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1, content.getReflect().getParams().getAliasMethod0GetDeclaredAnonymousLambdaLocalVars1()),new KeyValueMemberName(AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_2, content.getReflect().getParams().getAliasMethod0GetDeclaredAnonymousLambdaLocalVars2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0, content.getReflect().getParams().getAliasMethod1GetDeclaredAnonymousLambdaLocalVars0()),new KeyValueMemberName(AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1, content.getReflect().getParams().getAliasMethod1GetDeclaredAnonymousLambdaLocalVars1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0, content.getReflect().getParams().getAliasMethod2GetDeclaredAnonymousLambdaLocalVars0()),new KeyValueMemberName(AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1, content.getReflect().getParams().getAliasMethod2GetDeclaredAnonymousLambdaLocalVars1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0, content.getReflect().getParams().getAliasMethod3GetDeclaredAnonymousLambdaLocalVars0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0, content.getReflect().getParams().getAliasMethod0GetDeclaredAnonymousLambdaLoopVars0()),new KeyValueMemberName(AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1, content.getReflect().getParams().getAliasMethod0GetDeclaredAnonymousLambdaLoopVars1()),new KeyValueMemberName(AliasParamReflection.METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_2, content.getReflect().getParams().getAliasMethod0GetDeclaredAnonymousLambdaLoopVars2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0, content.getReflect().getParams().getAliasMethod1GetDeclaredAnonymousLambdaLoopVars0()),new KeyValueMemberName(AliasParamReflection.METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1, content.getReflect().getParams().getAliasMethod1GetDeclaredAnonymousLambdaLoopVars1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0, content.getReflect().getParams().getAliasMethod2GetDeclaredAnonymousLambdaLoopVars0()),new KeyValueMemberName(AliasParamReflection.METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1, content.getReflect().getParams().getAliasMethod2GetDeclaredAnonymousLambdaLoopVars1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0, content.getReflect().getParams().getAliasMethod3GetDeclaredAnonymousLambdaLoopVars0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.ANNOTATION_TYPE_0_GET_STRING_0, content.getReflect().getParams().getAliasAnnotationType0GetString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.ANNOTATED_0_GET_ANNOTATIONS_0, content.getReflect().getParams().getAliasAnnotated0GetAnnotations0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.ANNOTATED_0_GET_ANNOTATIONS_PARAMETERS_0, content.getReflect().getParams().getAliasAnnotated0GetAnnotationsParameters0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_0, content.getReflect().getParams().getAliasAnnotated0GetDeclaredAnonymousLambda0()),new KeyValueMemberName(AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_1, content.getReflect().getParams().getAliasAnnotated0GetDeclaredAnonymousLambda1()),new KeyValueMemberName(AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_2, content.getReflect().getParams().getAliasAnnotated0GetDeclaredAnonymousLambda2()),new KeyValueMemberName(AliasParamReflection.ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_3, content.getReflect().getParams().getAliasAnnotated0GetDeclaredAnonymousLambda3())));
        return map_;
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
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA,getAliasGetDeclaredAnonymousLambda()),
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
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_TYPES,getAliasGetDeclaredAnonymousTypes()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA,getAliasGetDeclaredAnonymousLambda()),
                new KeyValueMemberName(GET_DECLARED_LOCAL_TYPES,getAliasGetDeclaredLocalTypes()),
                new KeyValueMemberName(GET_DECLARED_BLOCKS,getAliasGetDeclaredBlocks()),
                new KeyValueMemberName(GET_DECLARED_EXPLICITS,getAliasGetDeclaredExplicits()),
                new KeyValueMemberName(GET_DECLARED_IMPLICITS,getAliasGetDeclaredImplicits()),
                new KeyValueMemberName(GET_DECLARED_TRUE_OPERATORS,getAliasGetDeclaredTrueOperators()),
                new KeyValueMemberName(GET_DECLARED_FALSE_OPERATORS,getAliasGetDeclaredFalseOperators()),
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
                new KeyValueMemberName(GET_PRETTY_SINGLE_NAME,getAliasGetPrettySingleName()),
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
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_TYPES,getAliasGetDeclaredAnonymousTypes()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA,getAliasGetDeclaredAnonymousLambda()),
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
                new KeyValueMemberName(CALL,getAliasCall()),
                new KeyValueMemberName(META_INFO,getAliasMetaInfo()),
                new KeyValueMemberName(INSTANCE,getAliasInstance())));
        map_.addEntry(getAliasField(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_ANNOTATIONS,getAliasGetAnnotations()),
                new KeyValueMemberName(GET_ANNOTATIONS_PARAMETERS,getAliasGetAnnotationsParameters()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_TYPES,getAliasGetDeclaredAnonymousTypes()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA,getAliasGetDeclaredAnonymousLambda()),
                new KeyValueMemberName(GET_FIELD,getAliasGetField()),
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
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_TYPES,getAliasGetDeclaredAnonymousTypes()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA,getAliasGetDeclaredAnonymousLambda()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS,getAliasGetDeclaredAnonymousLambdaLocalVars()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS,getAliasGetDeclaredAnonymousLambdaLoopVars()),
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
                new KeyValueMemberName(READ_RESOURCES_NAMES_LENGTH,getAliasReadResourcesNamesLength()),
                new KeyValueMemberName(READ_RESOURCES,getAliasReadResources()),
                new KeyValueMemberName(READ_RESOURCES_INDEX,getAliasReadResourcesIndex())));
        map_.addEntry(getAliasEnumType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(ENUM_NAME,getAliasEnumName()),
                new KeyValueMemberName(ENUM_ORDINAL,getAliasEnumOrdinal()),
                new KeyValueMemberName(ENUM_PRED_VALUE_OF,getAliasEnumPredValueOf()),
                new KeyValueMemberName(ENUM_VALUES,getAliasEnumValues())));
        map_.addEntry(getAliasSeedDoubleGenerator(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(SEED_GET,getAliasSeedGet())));
        map_.addEntry(getAliasSeedGenerator(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(SEED_GET,getAliasSeedGet())));
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
                new KeyValueMemberName(RANDOM,getAliasRandom()),
                new KeyValueMemberName(SEED,getAliasSeed())));
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
                new KeyValueMemberName(TO_LOWER_CASE_CHAR,getAliasToLowerCaseChar()),
                new KeyValueMemberName(TO_STRING_METHOD,getAliasToStringMethod()),
                new KeyValueMemberName(TO_UPPER_CASE_CHAR,getAliasToUpperCaseChar())));
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
                new KeyValueMemberName(REPLACE_STRING,getAliasReplaceString()),
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

    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames() {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(content.getNbAlias().getAliasDouble(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_PLUS_INFINITY, content.getNbAlias().getAliasPlusInfinityField()),
                new KeyValueMemberName(FIELD_MINUS_INFINITY, content.getNbAlias().getAliasMinusInfinityField()),
                new KeyValueMemberName(FIELD_NAN, content.getNbAlias().getAliasNanField()),
                new KeyValueMemberName(FIELD_MIN_VALUE, content.getNbAlias().getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE, content.getNbAlias().getAliasMaxValueField())));
        map_.addEntry(content.getNbAlias().getAliasFloat(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_PLUS_INFINITY, content.getNbAlias().getAliasPlusInfinityField()),
                new KeyValueMemberName(FIELD_MINUS_INFINITY, content.getNbAlias().getAliasMinusInfinityField()),
                new KeyValueMemberName(FIELD_NAN, content.getNbAlias().getAliasNanField()),
                new KeyValueMemberName(FIELD_MIN_VALUE, content.getNbAlias().getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE, content.getNbAlias().getAliasMaxValueField())));
        map_.addEntry(content.getNbAlias().getAliasLong(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_MIN_VALUE, content.getNbAlias().getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE, content.getNbAlias().getAliasMaxValueField())));
        map_.addEntry(content.getNbAlias().getAliasInteger(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_MIN_VALUE, content.getNbAlias().getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE, content.getNbAlias().getAliasMaxValueField())));
        map_.addEntry(content.getNbAlias().getAliasCharacter(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_MIN_VALUE, content.getNbAlias().getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE, content.getNbAlias().getAliasMaxValueField())));
        map_.addEntry(content.getNbAlias().getAliasShort(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_MIN_VALUE, content.getNbAlias().getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE, content.getNbAlias().getAliasMaxValueField())));
        map_.addEntry(content.getNbAlias().getAliasByte(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(FIELD_MIN_VALUE, content.getNbAlias().getAliasMinValueField()),
                new KeyValueMemberName(FIELD_MAX_VALUE, content.getNbAlias().getAliasMaxValueField())));
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


    public abstract void buildOther();
    public static ResultErrorStd invokeMethod(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        LgNames lgNames_ = _cont.getStandards();
        return lgNames_.invoke(_cont,_method,_struct,_args);
    }

    protected ResultErrorStd invoke(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        return ApplyCoreMethodUtil.invokeBase(_cont, _method, _struct, _args);
    }

    /**@param  _instance l'instance*/
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        return ApplyCoreMethodUtil.getOtherResultBase(_cont, _method, _args);
    }

    public Argument defaultInstance(ContextEl _cont, String _id) {
        return new Argument(new SimpleObjectStruct());
    }

    protected ResultErrorStd instance(ContextEl _cont, ConstructorId _method, Argument... _args) {
        return ApplyCoreMethodUtil.instanceBase(_cont, _method, _args);
    }

    public ResultErrorStd getOtherResult(ContextEl _cont, ConstructorId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        res_.setResult(new SimpleObjectStruct());
        return res_;
    }

    public final Struct getSimpleResult(ClassField _classField) {
        return calculator.getInnerSimpleResult(_classField);
    }

    public AbstractFunctionalInstance newFunctionalInstance(String _className, ExecRootBlock _rootBock, LambdaStruct _functional, ContextEl _contextEl) {
        return new FunctionalInstance(_className,_functional);
    }
    public AbstractFunctionalInstance newFullFunctionalInstance(String _className, ExecRootBlock _rootBock, LambdaStruct _functional, ContextEl _contextEl) {
        CustList<ClassFieldStruct> fs_ = _contextEl.getInit().feedFields(_contextEl, _className,_rootBock);
        return new FullFunctionalInstance(_className,_functional,fs_);
    }
    public StringMap<StandardType> getStandards() {
        return content.getStandards();
    }
    public StringMap<PrimitiveType> getPrimitiveTypes() {
        return getPrimTypes().getPrimitiveTypes();
    }

    public PrimitiveTypes getPrimTypes() {
        return content.getPrimTypes();
    }

    public String getAliasObject() {
        return content.getCoreNames().getAliasObject();
    }
    public void setAliasObject(String _aliasObject) {
        content.getCoreNames().setAliasObject(_aliasObject);
    }
    public String getAliasVoid() {
        return content.getCoreNames().getAliasVoid();
    }
    public void setAliasVoid(String _aliasVoid) {
        content.getCoreNames().setAliasVoid(_aliasVoid);
    }
    public String getAliasCharSequence() {
        return content.getCharSeq().getAliasCharSequence();
    }
    public void setAliasCharSequence(String _aliasCharSequence) {
        content.getCharSeq().setAliasCharSequence(_aliasCharSequence);
    }

    public String getAliasIterator() {
        return content.getPredefTypes().getAliasIterator();
    }
    public void setAliasIterator(String _aliasIterator) {
        content.getPredefTypes().setAliasIterator(_aliasIterator);
    }
    public String getAliasIteratorType() {
        return content.getPredefTypes().getAliasIteratorType();
    }
    public void setAliasIteratorType(String _aliasIteratorType) {
        content.getPredefTypes().setAliasIteratorType(_aliasIteratorType);
    }
    public String getAliasIterable() {
        return content.getPredefTypes().getAliasIterable();
    }
    public void setAliasIterable(String _aliasIterable) {
        content.getPredefTypes().setAliasIterable(_aliasIterable);
    }
    public String getAliasEnumParam() {
        return content.getPredefTypes().getAliasEnumParam();
    }
    public void setAliasEnumParam(String _aliasEnumParam) {
        content.getPredefTypes().setAliasEnumParam(_aliasEnumParam);
    }
    public String getAliasEnumType() {
        return content.getPredefTypes().getAliasEnumType();
    }
    public void setAliasEnumType(String _aliasEnum) {
        content.getPredefTypes().setAliasEnumType(_aliasEnum);
    }
    public String getAliasEnums() {
        return content.getCoreNames().getAliasEnums();
    }
    public void setAliasEnums(String _aliasEnums) {
        content.getCoreNames().setAliasEnums(_aliasEnums);
    }
    public String getAliasError() {
        return content.getCoreNames().getAliasError();
    }
    public void setAliasError(String _aliasError) {
        content.getCoreNames().setAliasError(_aliasError);
    }
    public String getAliasGetMessage() {
        return content.getCoreNames().getAliasGetMessage();
    }
    public void setAliasGetMessage(String _aliasGetMessage) {
        content.getCoreNames().setAliasGetMessage(_aliasGetMessage);
    }

    public String getAliasGetCause() {
        return content.getCoreNames().getAliasGetCause();
    }

    public void setAliasGetCause(String _aliasGetCause) {
        content.getCoreNames().setAliasGetCause(_aliasGetCause);
    }
    public String getAliasBadSize() {
        return content.getCoreNames().getAliasBadSize();
    }
    public void setAliasBadSize(String _aliasBadSize) {
        content.getCoreNames().setAliasBadSize(_aliasBadSize);
    }
    public String getAliasDivisionZero() {
        return content.getCoreNames().getAliasDivisionZero();
    }
    public void setAliasDivisionZero(String _aliasDivisionZero) {
        content.getCoreNames().setAliasDivisionZero(_aliasDivisionZero);
    }
    public String getAliasCastType() {
        return content.getCoreNames().getAliasCastType();
    }
    public void setAliasCastType(String _aliasCast) {
        content.getCoreNames().setAliasCastType(_aliasCast);
    }
    public String getAliasStore() {
        return content.getCoreNames().getAliasStore();
    }
    public void setAliasStore(String _aliasStore) {
        content.getCoreNames().setAliasStore(_aliasStore);
    }
    public String getAliasNullPe() {
        return content.getCoreNames().getAliasNullPe();
    }
    public void setAliasNullPe(String _aliasNullPe) {
        content.getCoreNames().setAliasNullPe(_aliasNullPe);
    }
    public String getAliasNbFormat() {
        return content.getCoreNames().getAliasNbFormat();
    }
    public void setAliasNbFormat(String _aliasNbFormat) {
        content.getCoreNames().setAliasNbFormat(_aliasNbFormat);
    }
    public String getAliasBadEncode() {
        return content.getCoreNames().getAliasBadEncode();
    }
    public void setAliasBadEncode(String _aliasBadEncode) {
        content.getCoreNames().setAliasBadEncode(_aliasBadEncode);
    }
    public String getAliasBadIndex() {
        return content.getCoreNames().getAliasBadIndex();
    }
    public void setAliasBadIndex(String _aliasBadIndex) {
        content.getCoreNames().setAliasBadIndex(_aliasBadIndex);
    }

    public String getAliasIllegalArg() {
        return content.getCoreNames().getAliasIllegalArg();
    }

    public void setAliasIllegalArg(String _aliasIllegalArg) {
        content.getCoreNames().setAliasIllegalArg(_aliasIllegalArg);
    }
    public String getAliasSof() {
        return content.getCoreNames().getAliasSof();
    }
    public void setAliasSof(String _aliasSof) {
        content.getCoreNames().setAliasSof(_aliasSof);
    }
    public String getAliasPrimBoolean() {
        return content.getPrimTypes().getAliasPrimBoolean();
    }
    public void setAliasPrimBoolean(String _aliasPrimBoolean) {
        content.getPrimTypes().setAliasPrimBoolean(_aliasPrimBoolean);
    }
    public String getAliasMath() {
        return content.getMathRef().getAliasMath();
    }
    public void setAliasMath(String _aliasMath) {
        content.getMathRef().setAliasMath(_aliasMath);
    }
    public String getAliasPrimByte() {
        return content.getPrimTypes().getAliasPrimByte();
    }
    public void setAliasPrimByte(String _aliasPrimByte) {
        content.getPrimTypes().setAliasPrimByte(_aliasPrimByte);
    }
    public String getAliasPrimShort() {
        return content.getPrimTypes().getAliasPrimShort();
    }
    public void setAliasPrimShort(String _aliasPrimShort) {
        content.getPrimTypes().setAliasPrimShort(_aliasPrimShort);
    }
    public String getAliasPrimChar() {
        return content.getPrimTypes().getAliasPrimChar();
    }
    public void setAliasPrimChar(String _aliasPrimChar) {
        content.getPrimTypes().setAliasPrimChar(_aliasPrimChar);
    }
    public String getAliasPrimInteger() {
        return content.getPrimTypes().getAliasPrimInteger();
    }
    public void setAliasPrimInteger(String _aliasPrimInteger) {
        content.getPrimTypes().setAliasPrimInteger(_aliasPrimInteger);
    }
    public String getAliasPrimLong() {
        return content.getPrimTypes().getAliasPrimLong();
    }
    public void setAliasPrimLong(String _aliasPrimLong) {
        content.getPrimTypes().setAliasPrimLong(_aliasPrimLong);
    }
    public String getAliasPrimFloat() {
        return content.getPrimTypes().getAliasPrimFloat();
    }
    public void setAliasPrimFloat(String _aliasPrimFloat) {
        content.getPrimTypes().setAliasPrimFloat(_aliasPrimFloat);
    }
    public String getAliasPrimDouble() {
        return content.getPrimTypes().getAliasPrimDouble();
    }
    public void setAliasPrimDouble(String _aliasPrimDouble) {
        content.getPrimTypes().setAliasPrimDouble(_aliasPrimDouble);
    }
   
    public String getAliasCompareTo() {
        return content.getNbAlias().getAliasCompareTo();
    }
    public void setAliasCompareTo(String _aliasCompareTo) {
        content.getNbAlias().setAliasCompareTo(_aliasCompareTo);
    }
    public String getAliasCompare() {
        return content.getNbAlias().getAliasCompare();
    }
    public void setAliasCompare(String _aliasCompare) {
        content.getNbAlias().setAliasCompare(_aliasCompare);
    }
    public String getAliasEquals() {
        return content.getNbAlias().getAliasEquals();
    }
    public void setAliasEquals(String _aliasEquals) {
        content.getNbAlias().setAliasEquals(_aliasEquals);
    }
    public String getAliasToStringMethod() {
        return content.getNbAlias().getAliasToStringMethod();
    }
    public void setAliasToStringMethod(String _aliasToString) {
        content.getNbAlias().setAliasToStringMethod(_aliasToString);
    }
    public String getAliasValueOfMethod() {
        return content.getNbAlias().getAliasValueOfMethod();
    }
    public void setAliasValueOfMethod(String _aliasValueOf) {
        content.getNbAlias().setAliasValueOfMethod(_aliasValueOf);
    }

    public void setAliasMaxValueField(String _aliasMaxValueField) {
        content.getNbAlias().setAliasMaxValueField(_aliasMaxValueField);
    }

    public void setAliasMinValueField(String _aliasMinValueField) {
        content.getNbAlias().setAliasMinValueField(_aliasMinValueField);
    }

    public void setAliasPlusInfinityField(String _aliasMaxValueField) {
        content.getNbAlias().setAliasPlusInfinityField(_aliasMaxValueField);
    }

    public void setAliasMinusInfinityField(String _aliasMinValueField) {
        content.getNbAlias().setAliasMinusInfinityField(_aliasMinValueField);
    }

    public void setAliasNanField(String _aliasMinValueField) {
        content.getNbAlias().setAliasNanField(_aliasMinValueField);
    }
    public String getAliasBoolean() {
        return content.getNbAlias().getAliasBoolean();
    }
    public void setAliasBoolean(String _aliasBoolean) {
        content.getNbAlias().setAliasBoolean(_aliasBoolean);
    }
    public String getAliasByte() {
        return content.getNbAlias().getAliasByte();
    }
    public void setAliasByte(String _aliasByte) {
        content.getNbAlias().setAliasByte(_aliasByte);
    }
    public String getAliasShort() {
        return content.getNbAlias().getAliasShort();
    }
    public void setAliasShort(String _aliasShort) {
        content.getNbAlias().setAliasShort(_aliasShort);
    }
    public String getAliasCharacter() {
        return content.getNbAlias().getAliasCharacter();
    }
    public void setAliasCharacter(String _aliasCharacter) {
        content.getNbAlias().setAliasCharacter(_aliasCharacter);
    }
    public String getAliasInteger() {
        return content.getNbAlias().getAliasInteger();
    }
    public void setAliasInteger(String _aliasInteger) {
        content.getNbAlias().setAliasInteger(_aliasInteger);
    }
    public String getAliasLong() {
        return content.getNbAlias().getAliasLong();
    }
    public void setAliasLong(String _aliasLong) {
        content.getNbAlias().setAliasLong(_aliasLong);
    }
    public String getAliasFloat() {
        return content.getNbAlias().getAliasFloat();
    }
    public void setAliasFloat(String _aliasFloat) {
        content.getNbAlias().setAliasFloat(_aliasFloat);
    }
    public String getAliasDouble() {
        return content.getNbAlias().getAliasDouble();
    }
    public void setAliasDouble(String _aliasDouble) {
        content.getNbAlias().setAliasDouble(_aliasDouble);
    }
    public String getAliasNumber() {
        return content.getNbAlias().getAliasNumber();
    }
    public void setAliasNumber(String _aliasNumber) {
        content.getNbAlias().setAliasNumber(_aliasNumber);
    }
    public String getAliasParseBoolean() {
        return content.getNbAlias().getAliasParseBoolean();
    }
    public void setAliasParseBoolean(String _aliasParseBoolean) {
        content.getNbAlias().setAliasParseBoolean(_aliasParseBoolean);
    }
    public String getAliasParseByte() {
        return content.getNbAlias().getAliasParseByte();
    }
    public void setAliasParseByte(String _aliasParseByte) {
        content.getNbAlias().setAliasParseByte(_aliasParseByte);
    }
    public String getAliasParseShort() {
        return content.getNbAlias().getAliasParseShort();
    }
    public void setAliasParseShort(String _aliasParseShort) {
        content.getNbAlias().setAliasParseShort(_aliasParseShort);
    }
    public String getAliasParseInt() {
        return content.getNbAlias().getAliasParseInt();
    }
    public void setAliasParseInt(String _aliasParseInt) {
        content.getNbAlias().setAliasParseInt(_aliasParseInt);
    }
    public String getAliasParseLong() {
        return content.getNbAlias().getAliasParseLong();
    }
    public void setAliasParseLong(String _aliasParseLong) {
        content.getNbAlias().setAliasParseLong(_aliasParseLong);
    }
    public String getAliasParseFloat() {
        return content.getNbAlias().getAliasParseFloat();
    }
    public void setAliasParseFloat(String _aliasParseFloat) {
        content.getNbAlias().setAliasParseFloat(_aliasParseFloat);
    }
    public String getAliasParseDouble() {
        return content.getNbAlias().getAliasParseDouble();
    }
    public void setAliasParseDouble(String _aliasParseDouble) {
        content.getNbAlias().setAliasParseDouble(_aliasParseDouble);
    }
    public String getAliasParseByteOrNull() {
        return content.getNbAlias().getAliasParseByteOrNull();
    }
    public void setAliasParseByteOrNull(String _aliasParseByte) {
        content.getNbAlias().setAliasParseByteOrNull(_aliasParseByte);
    }
    public String getAliasParseShortOrNull() {
        return content.getNbAlias().getAliasParseShortOrNull();
    }
    public void setAliasParseShortOrNull(String _aliasParseShort) {
        content.getNbAlias().setAliasParseShortOrNull(_aliasParseShort);
    }
    public String getAliasParseIntOrNull() {
        return content.getNbAlias().getAliasParseIntOrNull();
    }
    public void setAliasParseIntOrNull(String _aliasParseInt) {
        content.getNbAlias().setAliasParseIntOrNull(_aliasParseInt);
    }
    public String getAliasParseLongOrNull() {
        return content.getNbAlias().getAliasParseLongOrNull();
    }
    public void setAliasParseLongOrNull(String _aliasParseLong) {
        content.getNbAlias().setAliasParseLongOrNull(_aliasParseLong);
    }
    public String getAliasParseFloatOrNull() {
        return content.getNbAlias().getAliasParseFloatOrNull();
    }
    public void setAliasParseFloatOrNull(String _aliasParseFloat) {
        content.getNbAlias().setAliasParseFloatOrNull(_aliasParseFloat);
    }
    public String getAliasParseDoubleOrNull() {
        return content.getNbAlias().getAliasParseDoubleOrNull();
    }
    public void setAliasParseDoubleOrNull(String _aliasParseDouble) {
        content.getNbAlias().setAliasParseDoubleOrNull(_aliasParseDouble);
    }

    public String getAliasBooleanValue() {
        return content.getNbAlias().getAliasBooleanValue();
    }
    public void setAliasBooleanValue(String _aliasBooleanValue) {
        content.getNbAlias().setAliasBooleanValue(_aliasBooleanValue);
    }
    public String getAliasByteValue() {
        return content.getNbAlias().getAliasByteValue();
    }
    public void setAliasByteValue(String _aliasByteValue) {
        content.getNbAlias().setAliasByteValue(_aliasByteValue);
    }
    public String getAliasShortValue() {
        return content.getNbAlias().getAliasShortValue();
    }
    public void setAliasShortValue(String _aliasShortValue) {
        content.getNbAlias().setAliasShortValue(_aliasShortValue);
    }
    public String getAliasCharValue() {
        return content.getNbAlias().getAliasCharValue();
    }
    public void setAliasCharValue(String _aliasCharValue) {
        content.getNbAlias().setAliasCharValue(_aliasCharValue);
    }
    public String getAliasIntValue() {
        return content.getNbAlias().getAliasIntValue();
    }
    public void setAliasIntValue(String _aliasIntValue) {
        content.getNbAlias().setAliasIntValue(_aliasIntValue);
    }
    public String getAliasLongValue() {
        return content.getNbAlias().getAliasLongValue();
    }
    public void setAliasLongValue(String _aliasLongValue) {
        content.getNbAlias().setAliasLongValue(_aliasLongValue);
    }
    public String getAliasFloatValue() {
        return content.getNbAlias().getAliasFloatValue();
    }
    public void setAliasFloatValue(String _aliasFloatValue) {
        content.getNbAlias().setAliasFloatValue(_aliasFloatValue);
    }
    public String getAliasDoubleValue() {
        return content.getNbAlias().getAliasDoubleValue();
    }
    public void setAliasDoubleValue(String _aliasDoubleValue) {
        content.getNbAlias().setAliasDoubleValue(_aliasDoubleValue);
    }
    public String getAliasDigit() {
        return content.getNbAlias().getAliasDigit();
    }
    public void setAliasDigit(String _aliasDigit) {
        content.getNbAlias().setAliasDigit(_aliasDigit);
    }
    public String getAliasIsDigit() {
        return content.getNbAlias().getAliasIsDigit();
    }
    public void setAliasIsDigit(String _aliasIsDigit) {
        content.getNbAlias().setAliasIsDigit(_aliasIsDigit);
    }
    public String getAliasIsLetter() {
        return content.getNbAlias().getAliasIsLetter();
    }
    public void setAliasIsLetter(String _aliasIsLetter) {
        content.getNbAlias().setAliasIsLetter(_aliasIsLetter);
    }
    public String getAliasIsLetterOrDigit() {
        return content.getNbAlias().getAliasIsLetterOrDigit();
    }
    public void setAliasIsLetterOrDigit(String _aliasIsLetterOrDigit) {
        content.getNbAlias().setAliasIsLetterOrDigit(_aliasIsLetterOrDigit);
    }
    public String getAliasIsWordChar() {
        return content.getNbAlias().getAliasIsWordChar();
    }
    public void setAliasIsWordChar(String _aliasIsWordChar) {
        content.getNbAlias().setAliasIsWordChar(_aliasIsWordChar);
    }
    public String getAliasIsLowerCase() {
        return content.getNbAlias().getAliasIsLowerCase();
    }
    public void setAliasIsLowerCase(String _aliasIsLowerCase) {
        content.getNbAlias().setAliasIsLowerCase(_aliasIsLowerCase);
    }
    public String getAliasIsUpperCase() {
        return content.getNbAlias().getAliasIsUpperCase();
    }
    public void setAliasIsUpperCase(String _aliasIsUpperCase) {
        content.getNbAlias().setAliasIsUpperCase(_aliasIsUpperCase);
    }
    public String getAliasIsWhitespace() {
        return content.getNbAlias().getAliasIsWhitespace();
    }
    public void setAliasIsWhitespace(String _aliasIsWhitespace) {
        content.getNbAlias().setAliasIsWhitespace(_aliasIsWhitespace);
    }
    public String getAliasIsSpace() {
        return content.getNbAlias().getAliasIsSpace();
    }
    public void setAliasIsSpace(String _aliasIsSpace) {
        content.getNbAlias().setAliasIsSpace(_aliasIsSpace);
    }
    public String getAliasIsInfinite() {
        return content.getNbAlias().getAliasIsInfinite();
    }
    public void setAliasIsInfinite(String _aliasIsInfinite) {
        content.getNbAlias().setAliasIsInfinite(_aliasIsInfinite);
    }
    public String getAliasIsNan() {
        return content.getNbAlias().getAliasIsNan();
    }
    public void setAliasIsNan(String _aliasIsNan) {
        content.getNbAlias().setAliasIsNan(_aliasIsNan);
    }
    public String getAliasForDigit() {
        return content.getNbAlias().getAliasForDigit();
    }
    public void setAliasForDigit(String _aliasForDigit) {
        content.getNbAlias().setAliasForDigit(_aliasForDigit);
    }
    public String getAliasGetDirectionality() {
        return content.getNbAlias().getAliasGetDirectionality();
    }
    public void setAliasGetDirectionality(String _aliasGetDirectionality) {
        content.getNbAlias().setAliasGetDirectionality(_aliasGetDirectionality);
    }
    public String getAliasGetType() {
        return content.getReflect().getAliasGetType();
    }
    public void setAliasGetType(String _aliasGetType) {
        content.getReflect().setAliasGetType(_aliasGetType);
    }
    public String getAliasGetCharType() {
        return content.getNbAlias().getAliasGetCharType();
    }
    public void setAliasGetCharType(String _aliasGetType) {
        content.getNbAlias().setAliasGetCharType(_aliasGetType);
    }

    public String getAliasToLowerCaseChar() {
        return content.getNbAlias().getAliasToLowerCaseChar();
    }

    public void setAliasToLowerCaseChar(String aliasToLowerCaseChar) {
        content.getNbAlias().setAliasToLowerCaseChar(aliasToLowerCaseChar);
    }

    public String getAliasToUpperCaseChar() {
        return content.getNbAlias().getAliasToUpperCaseChar();
    }

    public void setAliasToUpperCaseChar(String aliasToUpperCaseChar) {
        content.getNbAlias().setAliasToUpperCaseChar(aliasToUpperCaseChar);
    }
    public String getAliasString() {
        return content.getCharSeq().getAliasString();
    }
    public void setAliasString(String _aliasString) {
        content.getCharSeq().setAliasString(_aliasString);
    }
    public String getAliasLength() {
        return content.getCharSeq().getAliasLength();
    }
    public void setAliasLength(String _aliasLength) {
        content.getCharSeq().setAliasLength(_aliasLength);
    }
    public String getAliasCharAt() {
        return content.getCharSeq().getAliasCharAt();
    }
    public void setAliasCharAt(String _aliasCharAt) {
        content.getCharSeq().setAliasCharAt(_aliasCharAt);
    }
    public String getAliasToCharArray() {
        return content.getCharSeq().getAliasToCharArray();
    }
    public void setAliasToCharArray(String _aliasToCharArray) {
        content.getCharSeq().setAliasToCharArray(_aliasToCharArray);
    }
    public String getAliasSplit() {
        return content.getCharSeq().getAliasSplit();
    }
    public void setAliasSplit(String _aliasSplit) {
        content.getCharSeq().setAliasSplit(_aliasSplit);
    }
    public String getAliasSplitStrings() {
        return content.getCharSeq().getAliasSplitStrings();
    }
    public void setAliasSplitStrings(String _aliasSplitStrings) {
        content.getCharSeq().setAliasSplitStrings(_aliasSplitStrings);
    }
    public String getAliasSplitChars() {
        return content.getCharSeq().getAliasSplitChars();
    }
    public void setAliasSplitChars(String _aliasSplitChars) {
        content.getCharSeq().setAliasSplitChars(_aliasSplitChars);
    }
    public String getAliasReplace() {
        return content.getCharSeq().getAliasReplace();
    }
    public void setAliasReplace(String _aliasReplace) {
        content.getCharSeq().setAliasReplace(_aliasReplace);
    }
    public String getAliasReplaceString() {
        return content.getCharSeq().getAliasReplaceString();
    }
    public void setAliasReplaceString(String _aliasReplace) {
        content.getCharSeq().setAliasReplaceString(_aliasReplace);
    }
    public String getAliasReplaceMultiple() {
        return content.getCharSeq().getAliasReplaceMultiple();
    }
    public void setAliasReplaceMultiple(String _aliasReplaceMultiple) {
        content.getCharSeq().setAliasReplaceMultiple(_aliasReplaceMultiple);
    }
    public String getAliasEqualsIgnoreCase() {
        return content.getCharSeq().getAliasEqualsIgnoreCase();
    }
    public void setAliasEqualsIgnoreCase(String _aliasEqualsIgnoreCase) {
        content.getCharSeq().setAliasEqualsIgnoreCase(_aliasEqualsIgnoreCase);
    }
    public String getAliasCompareToIgnoreCase() {
        return content.getCharSeq().getAliasCompareToIgnoreCase();
    }
    public void setAliasCompareToIgnoreCase(String _aliasCompareToIgnoreCase) {
        content.getCharSeq().setAliasCompareToIgnoreCase(_aliasCompareToIgnoreCase);
    }
    public String getAliasContains() {
        return content.getCharSeq().getAliasContains();
    }
    public void setAliasContains(String _aliasContains) {
        content.getCharSeq().setAliasContains(_aliasContains);
    }
    public String getAliasEndsWith() {
        return content.getCharSeq().getAliasEndsWith();
    }
    public void setAliasEndsWith(String _aliasEndsWith) {
        content.getCharSeq().setAliasEndsWith(_aliasEndsWith);
    }
    public String getAliasStartsWith() {
        return content.getCharSeq().getAliasStartsWith();
    }
    public void setAliasStartsWith(String _aliasStartsWith) {
        content.getCharSeq().setAliasStartsWith(_aliasStartsWith);
    }
    public String getAliasIndexOf() {
        return content.getCharSeq().getAliasIndexOf();
    }
    public void setAliasIndexOf(String _aliasIndexOf) {
        content.getCharSeq().setAliasIndexOf(_aliasIndexOf);
    }
    public String getAliasFormat() {
        return content.getCharSeq().getAliasFormat();
    }
    public void setAliasFormat(String _aliasFormat) {
        content.getCharSeq().setAliasFormat(_aliasFormat);
    }
    public String getAliasGetBytes() {
        return content.getCharSeq().getAliasGetBytes();
    }
    public void setAliasGetBytes(String _aliasGetBytes) {
        content.getCharSeq().setAliasGetBytes(_aliasGetBytes);
    }
    public String getAliasIsEmpty() {
        return content.getCharSeq().getAliasIsEmpty();
    }
    public void setAliasIsEmpty(String _aliasIsEmpty) {
        content.getCharSeq().setAliasIsEmpty(_aliasIsEmpty);
    }
    public String getAliasLastIndexOf() {
        return content.getCharSeq().getAliasLastIndexOf();
    }
    public void setAliasLastIndexOf(String _aliasLastIndexOf) {
        content.getCharSeq().setAliasLastIndexOf(_aliasLastIndexOf);
    }
    public String getAliasRegionMatches() {
        return content.getCharSeq().getAliasRegionMatches();
    }
    public void setAliasRegionMatches(String _aliasRegionMatches) {
        content.getCharSeq().setAliasRegionMatches(_aliasRegionMatches);
    }
    public String getAliasSubstring() {
        return content.getCharSeq().getAliasSubstring();
    }
    public void setAliasSubstring(String _aliasSubstring) {
        content.getCharSeq().setAliasSubstring(_aliasSubstring);
    }
    public String getAliasSubSequence() {
        return content.getCharSeq().getAliasSubSequence();
    }
    public void setAliasSubSequence(String _aliasSubSequence) {
        content.getCharSeq().setAliasSubSequence(_aliasSubSequence);
    }
    public String getAliasToLowerCase() {
        return content.getCharSeq().getAliasToLowerCase();
    }
    public void setAliasToLowerCase(String _aliasToLowerCase) {
        content.getCharSeq().setAliasToLowerCase(_aliasToLowerCase);
    }
    public String getAliasToUpperCase() {
        return content.getCharSeq().getAliasToUpperCase();
    }
    public void setAliasToUpperCase(String _aliasToUpperCase) {
        content.getCharSeq().setAliasToUpperCase(_aliasToUpperCase);
    }
    public String getAliasTrim() {
        return content.getCharSeq().getAliasTrim();
    }
    public void setAliasTrim(String _aliasTrim) {
        content.getCharSeq().setAliasTrim(_aliasTrim);
    }
    public String getAliasStringBuilder() {
        return content.getCharSeq().getAliasStringBuilder();
    }
    public void setAliasStringBuilder(String _aliasStringBuilder) {
        content.getCharSeq().setAliasStringBuilder(_aliasStringBuilder);
    }
    public String getAliasAppend() {
        return content.getCharSeq().getAliasAppend();
    }
    public void setAliasAppend(String _aliasAppend) {
        content.getCharSeq().setAliasAppend(_aliasAppend);
    }
    public String getAliasCapacity() {
        return content.getCharSeq().getAliasCapacity();
    }
    public void setAliasCapacity(String _aliasCapacity) {
        content.getCharSeq().setAliasCapacity(_aliasCapacity);
    }
    public String getAliasClear() {
        return content.getCharSeq().getAliasClear();
    }
    public void setAliasClear(String _aliasClear) {
        content.getCharSeq().setAliasClear(_aliasClear);
    }
    public String getAliasDelete() {
        return content.getCharSeq().getAliasDelete();
    }
    public void setAliasDelete(String _aliasDelete) {
        content.getCharSeq().setAliasDelete(_aliasDelete);
    }
    public String getAliasDeleteCharAt() {
        return content.getCharSeq().getAliasDeleteCharAt();
    }
    public void setAliasDeleteCharAt(String _aliasDeleteCharAt) {
        content.getCharSeq().setAliasDeleteCharAt(_aliasDeleteCharAt);
    }
    public String getAliasEnsureCapacity() {
        return content.getCharSeq().getAliasEnsureCapacity();
    }
    public void setAliasEnsureCapacity(String _aliasEnsureCapacity) {
        content.getCharSeq().setAliasEnsureCapacity(_aliasEnsureCapacity);
    }
    public String getAliasInsert() {
        return content.getCharSeq().getAliasInsert();
    }
    public void setAliasInsert(String _aliasInsert) {
        content.getCharSeq().setAliasInsert(_aliasInsert);
    }
    public String getAliasReverse() {
        return content.getCharSeq().getAliasReverse();
    }
    public void setAliasReverse(String _aliasReverse) {
        content.getCharSeq().setAliasReverse(_aliasReverse);
    }
    public String getAliasSetCharAt() {
        return content.getCharSeq().getAliasSetCharAt();
    }
    public void setAliasSetCharAt(String _aliasSetCharAt) {
        content.getCharSeq().setAliasSetCharAt(_aliasSetCharAt);
    }
    public String getAliasSetLength() {
        return content.getCharSeq().getAliasSetLength();
    }
    public void setAliasSetLength(String _aliasSetLength) {
        content.getCharSeq().setAliasSetLength(_aliasSetLength);
    }
    public String getAliasSame() {
        return content.getCharSeq().getAliasSame();
    }
    public void setAliasSame(String _aliasSetLength) {
        content.getCharSeq().setAliasSame(_aliasSetLength);
    }
    public String getAliasTrimToSize() {
        return content.getCharSeq().getAliasTrimToSize();
    }
    public void setAliasTrimToSize(String _aliasTrimToSize) {
        content.getCharSeq().setAliasTrimToSize(_aliasTrimToSize);
    }

    public String getAliasNext() {
        return content.getPredefTypes().getAliasNext();
    }
    public void setAliasNext(String _aliasNext) {
        content.getPredefTypes().setAliasNext(_aliasNext);
    }
    public String getAliasHasNext() {
        return content.getPredefTypes().getAliasHasNext();
    }
    public void setAliasHasNext(String _aliasHasNext) {
        content.getPredefTypes().setAliasHasNext(_aliasHasNext);
    }
    
    public String getAliasIterableTable() {
        return content.getPredefTypes().getAliasIterableTable();
    }
    public void setAliasIterableTable(String _aliasIterableTable) {
        content.getPredefTypes().setAliasIterableTable(_aliasIterableTable);
    }
    public String getAliasIteratorTable() {
        return content.getPredefTypes().getAliasIteratorTable();
    }
    public void setAliasIteratorTable(String _aliasIteratorTable) {
        content.getPredefTypes().setAliasIteratorTable(_aliasIteratorTable);
    }
    public String getAliasIteratorTableType() {
        return content.getPredefTypes().getAliasIteratorTableType();
    }
    public void setAliasIteratorTableType(String _aliasIteratorTableType) {
        content.getPredefTypes().setAliasIteratorTableType(_aliasIteratorTableType);
    }
    public String getAliasHasNextPair() {
        return content.getPredefTypes().getAliasHasNextPair();
    }
    public void setAliasHasNextPair(String _aliasHasNextPair) {
        content.getPredefTypes().setAliasHasNextPair(_aliasHasNextPair);
    }
    public String getAliasNextPair() {
        return content.getPredefTypes().getAliasNextPair();
    }
    public void setAliasNextPair(String _aliasHasNextPair) {
        content.getPredefTypes().setAliasNextPair(_aliasHasNextPair);
    }
    public String getAliasPairType() {
        return content.getPredefTypes().getAliasPairType();
    }
    public void setAliasPairType(String _aliasPairType) {
        content.getPredefTypes().setAliasPairType(_aliasPairType);
    }
    public String getAliasGetFirst() {
        return content.getPredefTypes().getAliasGetFirst();
    }
    public void setAliasGetFirst(String _aliasGetFirst) {
        content.getPredefTypes().setAliasGetFirst(_aliasGetFirst);
    }
    public String getAliasGetSecond() {
        return content.getPredefTypes().getAliasGetSecond();
    }
    public void setAliasGetSecond(String _aliasGetSecond) {
        content.getPredefTypes().setAliasGetSecond(_aliasGetSecond);
    }
    public String getAliasName() {
        return content.getCoreNames().getAliasName();
    }
    public void setAliasName(String _aliasName) {
        content.getCoreNames().setAliasName(_aliasName);
    }
    public String getAliasOrdinal() {
        return content.getCoreNames().getAliasOrdinal();
    }
    public void setAliasOrdinal(String _aliasOrdinal) {
        content.getCoreNames().setAliasOrdinal(_aliasOrdinal);
    }
    public String getAliasReplacement() {
        return content.getCharSeq().getAliasReplacement();
    }
    public void setAliasReplacement(String _aliasReplacement) {
        content.getCharSeq().setAliasReplacement(_aliasReplacement);
    }
    public String getAliasGetOldString() {
        return content.getCharSeq().getAliasGetOldString();
    }
    public void setAliasGetOldString(String _aliasGetOldString) {
        content.getCharSeq().setAliasGetOldString(_aliasGetOldString);
    }
    public String getAliasGetNewString() {
        return content.getCharSeq().getAliasGetNewString();
    }
    public void setAliasGetNewString(String _aliasGetNewString) {
        content.getCharSeq().setAliasGetNewString(_aliasGetNewString);
    }
    public String getAliasAbs() {
        return content.getMathRef().getAliasAbs();
    }
    public void setAliasAbs(String _aliasAbs) {
        content.getMathRef().setAliasAbs(_aliasAbs);
    }
    public String getAliasQuot() {
        return content.getMathRef().getAliasQuot();
    }
    public void setAliasQuot(String _aliasQuot) {
        content.getMathRef().setAliasQuot(_aliasQuot);
    }
    public String getAliasMod() {
        return content.getMathRef().getAliasMod();
    }
    public void setAliasMod(String _aliasMod) {
        content.getMathRef().setAliasMod(_aliasMod);
    }
    public String getAliasErrorInitClass() {
        return content.getCoreNames().getAliasErrorInitClass();
    }
    public void setAliasErrorInitClass(String _aliasErrorInitClass) {
        content.getCoreNames().setAliasErrorInitClass(_aliasErrorInitClass);
    }
    public String getAliasClone() {
        return content.getCoreNames().getAliasClone();
    }
    public void setAliasClone(String _aliasClone) {
        content.getCoreNames().setAliasClone(_aliasClone);
    }
    public String getAliasReadResourcesNames() {
    	return content.getCoreNames().getAliasReadResourcesNames();
    }
    public void setAliasReadResourcesNames(String _aliasReadResourcesNames) {
    	content.getCoreNames().setAliasReadResourcesNames(_aliasReadResourcesNames);
    }
    public String getAliasReadResourcesNamesLength() {
        return content.getCoreNames().getAliasReadResourcesNamesLength();
    }
    public void setAliasReadResourcesNamesLength(String _aliasReadResourcesNames) {
        content.getCoreNames().setAliasReadResourcesNamesLength(_aliasReadResourcesNames);
    }
    public String getAliasReadResources() {
        return content.getCoreNames().getAliasReadResources();
    }
    public void setAliasReadResources(String _aliasReadResources) {
        content.getCoreNames().setAliasReadResources(_aliasReadResources);
    }
    public String getAliasReadResourcesIndex() {
        return content.getCoreNames().getAliasReadResourcesIndex();
    }
    public void setAliasReadResourcesIndex(String _aliasReadResources) {
        content.getCoreNames().setAliasReadResourcesIndex(_aliasReadResources);
    }
    public String getAliasResources() {
        return content.getCoreNames().getAliasResources();
    }
    public void setAliasResources(String _aliasResources) {
        content.getCoreNames().setAliasResources(_aliasResources);
    }
    public String getAliasEnumValues() {
        return content.getPredefTypes().getAliasEnumValues();
    }
    public void setAliasEnumValues(String _aliasValues) {
        content.getPredefTypes().setAliasEnumValues(_aliasValues);
    }
    public String getAliasEnumPredValueOf() {
        return content.getPredefTypes().getAliasEnumPredValueOf();
    }
    public void setAliasEnumPredValueOf(String _aliasValues) {
        content.getPredefTypes().setAliasEnumPredValueOf(_aliasValues);
    }

    public String getAliasIterableVar() {
        return content.getPredefTypes().getAliasIterableVar();
    }

    public void setAliasIterableVar(String aliasIterableVar) {
        content.getPredefTypes().setAliasIterableVar(aliasIterableVar);
    }

    public String getAliasIteratorTypeVar() {
        return content.getPredefTypes().getAliasIteratorTypeVar();
    }

    public void setAliasIteratorTypeVar(String aliasIteratorTypeVar) {
        content.getPredefTypes().setAliasIteratorTypeVar(aliasIteratorTypeVar);
    }

    public String getAliasIterableTableVarFirst() {
        return content.getPredefTypes().getAliasIterableTableVarFirst();
    }

    public void setAliasIterableTableVarFirst(String aliasIterableTableVarFirst) {
        content.getPredefTypes().setAliasIterableTableVarFirst(aliasIterableTableVarFirst);
    }

    public String getAliasIterableTableVarSecond() {
        return content.getPredefTypes().getAliasIterableTableVarSecond();
    }

    public void setAliasIterableTableVarSecond(String aliasIterableTableVarSecond) {
        content.getPredefTypes().setAliasIterableTableVarSecond(aliasIterableTableVarSecond);
    }

    public String getAliasIteratorTableTypeVarFirst() {
        return content.getPredefTypes().getAliasIteratorTableTypeVarFirst();
    }

    public void setAliasIteratorTableTypeVarFirst(String aliasIteratorTableTypeVarFirst) {
        content.getPredefTypes().setAliasIteratorTableTypeVarFirst(aliasIteratorTableTypeVarFirst);
    }

    public String getAliasIteratorTableTypeVarSecond() {
        return content.getPredefTypes().getAliasIteratorTableTypeVarSecond();
    }

    public void setAliasIteratorTableTypeVarSecond(String aliasIteratorTableTypeVarSecond) {
        content.getPredefTypes().setAliasIteratorTableTypeVarSecond(aliasIteratorTableTypeVarSecond);
    }

    public String getAliasPairTypeVarFirst() {
        return content.getPredefTypes().getAliasPairTypeVarFirst();
    }

    public void setAliasPairTypeVarFirst(String aliasPairTypeVarFirst) {
        content.getPredefTypes().setAliasPairTypeVarFirst(aliasPairTypeVarFirst);
    }

    public String getAliasPairTypeVarSecond() {
        return content.getPredefTypes().getAliasPairTypeVarSecond();
    }

    public void setAliasPairTypeVarSecond(String aliasPairTypeVarSecond) {
        content.getPredefTypes().setAliasPairTypeVarSecond(aliasPairTypeVarSecond);
    }

    public String getAliasEnumParamVar() {
        return content.getPredefTypes().getAliasEnumParamVar();
    }

    public void setAliasEnumParamVar(String aliasEnumParamVar) {
        content.getPredefTypes().setAliasEnumParamVar(aliasEnumParamVar);
    }

    public String getAliasSeedDoubleGenerator() {
        return content.getPredefTypes().getAliasSeedDoubleGenerator();
    }

    public void setAliasSeedDoubleGenerator(String aliasSeedDoubleGenerator) {
        content.getPredefTypes().setAliasSeedDoubleGenerator(aliasSeedDoubleGenerator);
    }

    public String getAliasSeedGenerator() {
        return content.getPredefTypes().getAliasSeedGenerator();
    }

    public void setAliasSeedGenerator(String aliasSeedGenerator) {
        content.getPredefTypes().setAliasSeedGenerator(aliasSeedGenerator);
    }

    public String getAliasSeedGet() {
        return content.getPredefTypes().getAliasSeedGet();
    }

    public void setAliasSeedGet(String aliasSeedGet) {
        content.getPredefTypes().setAliasSeedGet(aliasSeedGet);
    }

    public String getAliasInvokeTarget() {
        return content.getReflect().getAliasInvokeTarget();
    }
    public void setAliasInvokeTarget(String _aliasInvokeTarget) {
        content.getReflect().setAliasInvokeTarget(_aliasInvokeTarget);
    }
    public AliasReflection getReflect() {
        return content.getReflect();
    }
    public String getAliasClassNotFoundError() {
        return content.getReflect().getAliasClassNotFoundError();
    }
    public void setAliasClassNotFoundError(String _aliasClassNotFoundError) {
        content.getReflect().setAliasClassNotFoundError(_aliasClassNotFoundError);
    }

    public String getAliasGetVariableOwner() {
        return content.getReflect().getAliasGetVariableOwner();
    }
    public void setAliasGetVariableOwner(String _aliasTypeVariable) {
        content.getReflect().setAliasGetVariableOwner(_aliasTypeVariable);
    }

    public String getAliasGetGenericVariableOwner() {
        return content.getReflect().getAliasGetGenericVariableOwner();
    }
    public void setAliasGetGenericVariableOwner(String _aliasTypeVariable) {
        content.getReflect().setAliasGetGenericVariableOwner(_aliasTypeVariable);
    }

    public String getAliasGetString() {
        return content.getReflect().getAliasGetString();
    }
    public void setAliasGetString(String _aliasTypeVariable) {
        content.getReflect().setAliasGetString(_aliasTypeVariable);
    }

    public String getAliasClassType() {
        return content.getReflect().getAliasClassType();
    }
    public void setAliasClassType(String _aliasClass) {
        content.getReflect().setAliasClassType(_aliasClass);
    }
    public String getAliasFct() {
        return content.getReflect().getAliasFct();
    }
    public void setAliasFct(String _aliasFct) {
        content.getReflect().setAliasFct(_aliasFct);
    }
    public String getAliasCall() {
        return content.getReflect().getAliasCall();
    }
    public void setAliasCall(String _aliasCall) {
        content.getReflect().setAliasCall(_aliasCall);
    }

    public String getAliasInstance() {
        return content.getReflect().getAliasInstance();
    }

    public void setAliasInstance(String aliasInstance) {
        content.getReflect().setAliasInstance(aliasInstance);
    }

    public String getAliasMetaInfo() {
        return content.getReflect().getAliasMetaInfo();
    }

    public void setAliasMetaInfo(String aliasMetaInfo) {
        content.getReflect().setAliasMetaInfo(aliasMetaInfo);
    }
    public String getAliasAnnotationType() {
        return content.getReflect().getAliasAnnotationType();
    }
    public void setAliasAnnotationType(String _aliasAnnotation) {
        content.getReflect().setAliasAnnotationType(_aliasAnnotation);
    }
    public String getAliasAnnotated() {
        return content.getReflect().getAliasAnnotated();
    }
    public void setAliasAnnotated(String _aliasAnnotated) {
        content.getReflect().setAliasAnnotated(_aliasAnnotated);
    }
    public String getAliasGetAnnotations() {
        return content.getReflect().getAliasGetAnnotations();
    }
    public void setAliasGetAnnotations(String _aliasGetAnnotations) {
        content.getReflect().setAliasGetAnnotations(_aliasGetAnnotations);
    }
    public String getAliasGetDefaultValue() {
        return content.getReflect().getAliasGetDefaultValue();
    }
    public void setAliasGetDefaultValue(String _aliasGetDefaultValue) {
        content.getReflect().setAliasGetDefaultValue(_aliasGetDefaultValue);
    }
    public String getAliasGetAnnotationsParameters() {
        return content.getReflect().getAliasGetAnnotationsParameters();
    }
    public void setAliasGetAnnotationsParameters(String _aliasGetAnnotationsParameters) {
        content.getReflect().setAliasGetAnnotationsParameters(_aliasGetAnnotationsParameters);
    }

    public String getAliasGetDeclaredExplicits() {
        return content.getReflect().getAliasGetDeclaredExplicits();
    }

    public void setAliasGetDeclaredExplicits(String aliasGetDeclaredExplicits) {
        content.getReflect().setAliasGetDeclaredExplicits(aliasGetDeclaredExplicits);
    }

    public String getAliasGetDeclaredImplicits() {
        return content.getReflect().getAliasGetDeclaredImplicits();
    }

    public void setAliasGetDeclaredImplicits(String aliasGetDeclaredImplicits) {
        content.getReflect().setAliasGetDeclaredImplicits(aliasGetDeclaredImplicits);
    }

    public String getAliasGetDeclaredTrueOperators() {
        return content.getReflect().getAliasGetDeclaredTrueOperators();
    }

    public void setAliasGetDeclaredTrueOperators(String aliasGetDeclaredTrueOperators) {
        content.getReflect().setAliasGetDeclaredTrueOperators(aliasGetDeclaredTrueOperators);
    }

    public String getAliasGetDeclaredFalseOperators() {
        return content.getReflect().getAliasGetDeclaredFalseOperators();
    }

    public void setAliasGetDeclaredFalseOperators(String aliasGetDeclaredFalseOperators) {
        content.getReflect().setAliasGetDeclaredFalseOperators(aliasGetDeclaredFalseOperators);
    }

    public String getAliasGetDeclaredMethods() {
        return content.getReflect().getAliasGetDeclaredMethods();
    }
    public void setAliasGetDeclaredMethods(String _aliasGetDeclaredMethods) {
        content.getReflect().setAliasGetDeclaredMethods(_aliasGetDeclaredMethods);
    }

    public String getAliasGetDeclaredStaticMethods() {
        return content.getReflect().getAliasGetDeclaredStaticMethods();
    }

    public void setAliasGetDeclaredStaticMethods(String _aliasGetDeclaredStaticMethods) {
        content.getReflect().setAliasGetDeclaredStaticMethods(_aliasGetDeclaredStaticMethods);
    }

    public String getAliasGetDeclaredConstructors() {
        return content.getReflect().getAliasGetDeclaredConstructors();
    }
    public void setAliasGetDeclaredConstructors(String _aliasGetDeclaredConstructors) {
        content.getReflect().setAliasGetDeclaredConstructors(_aliasGetDeclaredConstructors);
    }
    public String getAliasGetDeclaredFields() {
        return content.getReflect().getAliasGetDeclaredFields();
    }
    public void setAliasGetDeclaredFields(String _aliasGetDeclaredFields) {
        content.getReflect().setAliasGetDeclaredFields(_aliasGetDeclaredFields);
    }

    public String getAliasGetDeclaredAnonymousTypes() {
        return content.getReflect().getAliasGetDeclaredAnonymousTypes();
    }

    public void setAliasGetDeclaredAnonymousTypes(String aliasGetDeclaredAnonymousTypes) {
        content.getReflect().setAliasGetDeclaredAnonymousTypes(aliasGetDeclaredAnonymousTypes);
    }

    public String getAliasGetDeclaredAnonymousLambda() {
        return content.getReflect().getAliasGetDeclaredAnonymousLambda();
    }

    public void setAliasGetDeclaredAnonymousLambda(String aliasGetDeclaredAnonymousLambda) {
        content.getReflect().setAliasGetDeclaredAnonymousLambda(aliasGetDeclaredAnonymousLambda);
    }

    public String getAliasGetDeclaredAnonymousLambdaLocalVars() {
        return content.getReflect().getAliasGetDeclaredAnonymousLambdaLocalVars();
    }

    public void setAliasGetDeclaredAnonymousLambdaLocalVars(String aliasGetDeclaredAnonymousLambdaLocalVars) {
        content.getReflect().setAliasGetDeclaredAnonymousLambdaLocalVars(aliasGetDeclaredAnonymousLambdaLocalVars);
    }

    public String getAliasGetDeclaredAnonymousLambdaLoopVars() {
        return content.getReflect().getAliasGetDeclaredAnonymousLambdaLoopVars();
    }

    public void setAliasGetDeclaredAnonymousLambdaLoopVars(String aliasGetDeclaredAnonymousLambdaLoopVars) {
        content.getReflect().setAliasGetDeclaredAnonymousLambdaLoopVars(aliasGetDeclaredAnonymousLambdaLoopVars);
    }
    public String getAliasGetDeclaredBlocks() {
        return content.getReflect().getAliasGetDeclaredBlocks();
    }

    public void setAliasGetDeclaredBlocks(String aliasGetDeclaredBlocks) {
        content.getReflect().setAliasGetDeclaredBlocks(aliasGetDeclaredBlocks);
    }

    public String getAliasGetDeclaredLocalTypes() {
        return content.getReflect().getAliasGetDeclaredLocalTypes();
    }

    public void setAliasGetDeclaredLocalTypes(String aliasGetDeclaredLocalTypes) {
        content.getReflect().setAliasGetDeclaredLocalTypes(aliasGetDeclaredLocalTypes);
    }
    public String getAliasMakeGeneric() {
        return content.getReflect().getAliasMakeGeneric();
    }
    public void setAliasMakeGeneric(String _aliasMakeGeneric) {
        content.getReflect().setAliasMakeGeneric(_aliasMakeGeneric);
    }
    public String getAliasGetAllClasses() {
        return content.getReflect().getAliasGetAllClasses();
    }
    public void setAliasGetAllClasses(String _aliasGetAllClasses) {
        content.getReflect().setAliasGetAllClasses(_aliasGetAllClasses);
    }
    public String getAliasGetOperators() {
        return content.getReflect().getAliasGetOperators();
    }
    public void setAliasGetOperators(String _aliasGetOperators) {
        content.getReflect().setAliasGetOperators(_aliasGetOperators);
    }
    public String getAliasConstructor() {
        return content.getReflect().getAliasConstructor();
    }
    public void setAliasConstructor(String _aliasConstructor) {
        content.getReflect().setAliasConstructor(_aliasConstructor);
    }
    public String getAliasField() {
        return content.getReflect().getAliasField();
    }
    public void setAliasField(String _aliasField) {
        content.getReflect().setAliasField(_aliasField);
    }
    public String getAliasMethod() {
        return content.getReflect().getAliasMethod();
    }
    public void setAliasMethod(String _aliasMethod) {
        content.getReflect().setAliasMethod(_aliasMethod);
    }
    public String getAliasInvoke() {
        return content.getReflect().getAliasInvoke();
    }
    public void setAliasInvoke(String _aliasInvoke) {
        content.getReflect().setAliasInvoke(_aliasInvoke);
    }
    public String getAliasInvokeDirect() {
        return content.getReflect().getAliasInvokeDirect();
    }
    public void setAliasInvokeDirect(String _aliasInvoke) {
        content.getReflect().setAliasInvokeDirect(_aliasInvoke);
    }
    public String getAliasNewInstance() {
        return content.getReflect().getAliasNewInstance();
    }
    public void setAliasNewInstance(String _aliasNewInstance) {
        content.getReflect().setAliasNewInstance(_aliasNewInstance);
    }

    public String getAliasIsAbstract() {
        return content.getReflect().getAliasIsAbstract();
    }
    public void setAliasIsAbstract(String _aliasIsAbstract) {
        content.getReflect().setAliasIsAbstract(_aliasIsAbstract);
    }
    public String getAliasGetFileName() {
        return content.getReflect().getAliasGetFileName();
    }
    public void setAliasGetFileName(String _aliasGetName) {
        content.getReflect().setAliasGetFileName(_aliasGetName);
    }
    public String getAliasGetName() {
        return content.getReflect().getAliasGetName();
    }
    public void setAliasGetName(String _aliasGetName) {
        content.getReflect().setAliasGetName(_aliasGetName);
    }
    public String getAliasGetPrettyName() {
        return content.getReflect().getAliasGetPrettyName();
    }
    public void setAliasGetPrettyName(String _aliasGetName) {
        content.getReflect().setAliasGetPrettyName(_aliasGetName);
    }
    public String getAliasGetPrettySingleName() {
        return content.getReflect().getAliasGetPrettySingleName();
    }
    public void setAliasGetPrettySingleName(String _aliasGetName) {
        content.getReflect().setAliasGetPrettySingleName(_aliasGetName);
    }
    public String getAliasGetField() {
        return content.getReflect().getAliasGetField();
    }
    public void setAliasGetField(String _aliasGetField) {
        content.getReflect().setAliasGetField(_aliasGetField);
    }
    public String getAliasSetField() {
        return content.getReflect().getAliasSetField();
    }
    public void setAliasSetField(String _aliasSetField) {
        content.getReflect().setAliasSetField(_aliasSetField);
    }
    public String getAliasGetClass() {
        return content.getReflect().getAliasGetClass();
    }
    public void setAliasGetClass(String _aliasGetClass) {
        content.getReflect().setAliasGetClass(_aliasGetClass);
    }
    public String getAliasGetEnclosingType() {
        return content.getReflect().getAliasGetEnclosingType();
    }
    public void setAliasGetEnclosingType(String _aliasGetEnclosingType) {
        content.getReflect().setAliasGetEnclosingType(_aliasGetEnclosingType);
    }
    public String getAliasGetDeclaredClasses() {
        return content.getReflect().getAliasGetDeclaredClasses();
    }
    public void setAliasGetDeclaredClasses(String _aliasGetDeclaredClasses) {
        content.getReflect().setAliasGetDeclaredClasses(_aliasGetDeclaredClasses);
    }
    public String getAliasForName() {
        return content.getReflect().getAliasForName();
    }
    public void setAliasForName(String _aliasForName) {
        content.getReflect().setAliasForName(_aliasForName);
    }
    public String getAliasObjectsUtil() {
        return content.getCoreNames().getAliasObjectsUtil();
    }
    public void setAliasObjectsUtil(String _aliasObjectsUtil) {
        content.getCoreNames().setAliasObjectsUtil(_aliasObjectsUtil);
    }
    public String getAliasStringUtil() {
        return content.getCoreNames().getAliasStringUtil();
    }
    public void setAliasStringUtil(String _aliasObjectsUtil) {
        content.getCoreNames().setAliasStringUtil(_aliasObjectsUtil);
    }
    public String getAliasSameRef() {
        return content.getCoreNames().getAliasSameRef();
    }
    public void setAliasSameRef(String _aliasSameRef) {
        content.getCoreNames().setAliasSameRef(_aliasSameRef);
    }
    public String getAliasGetParent() {
        return content.getCoreNames().getAliasGetParent();
    }
    public void setAliasGetParent(String _aliasGetParent) {
        content.getCoreNames().setAliasGetParent(_aliasGetParent);
    }
    public String getAliasSetParent() {
        return content.getCoreNames().getAliasSetParent();
    }
    public void setAliasSetParent(String _aliasGetParent) {
        content.getCoreNames().setAliasSetParent(_aliasGetParent);
    }

    public String getAliasGetSuperClass() {
        return content.getReflect().getAliasGetSuperClass();
    }
    public void setAliasGetSuperClass(String _aliasGetSuperClass) {
        content.getReflect().setAliasGetSuperClass(_aliasGetSuperClass);
    }
    public String getAliasGetGenericSuperClass() {
        return content.getReflect().getAliasGetGenericSuperClass();
    }
    public void setAliasGetGenericSuperClass(String _aliasGetGenericSuperClass) {
        content.getReflect().setAliasGetGenericSuperClass(_aliasGetGenericSuperClass);
    }
    public String getAliasGetInterfaces() {
        return content.getReflect().getAliasGetInterfaces();
    }
    public void setAliasGetInterfaces(String _aliasGetInterfaces) {
        content.getReflect().setAliasGetInterfaces(_aliasGetInterfaces);
    }
    public String getAliasGetGenericInterfaces() {
        return content.getReflect().getAliasGetGenericInterfaces();
    }
    public void setAliasGetGenericInterfaces(String _aliasGetGenericInterfaces) {
        content.getReflect().setAliasGetGenericInterfaces(_aliasGetGenericInterfaces);
    }

    public String getAliasGetLowerBounds() {
        return content.getReflect().getAliasGetLowerBounds();
    }
    public void setAliasGetLowerBounds(String _aliasGetLowerBounds) {
        content.getReflect().setAliasGetLowerBounds(_aliasGetLowerBounds);
    }
    public String getAliasGetUpperBounds() {
        return content.getReflect().getAliasGetUpperBounds();
    }
    public void setAliasGetUpperBounds(String _aliasGetUpperBounds) {
        content.getReflect().setAliasGetUpperBounds(_aliasGetUpperBounds);
    }
    public String getAliasGetComponentType() {
        return content.getReflect().getAliasGetComponentType();
    }
    public void setAliasGetComponentType(String _aliasGetComponentType) {
        content.getReflect().setAliasGetComponentType(_aliasGetComponentType);
    }

    public String getAliasMakeArray() {
        return content.getReflect().getAliasMakeArray();
    }
    public void setAliasMakeArray(String _aliasMakeArray) {
        content.getReflect().setAliasMakeArray(_aliasMakeArray);
    }
    public String getAliasGetParameterTypes() {
        return content.getReflect().getAliasGetParameterTypes();
    }
    public void setAliasGetParameterTypes(String _aliasGetParameterTypes) {
        content.getReflect().setAliasGetParameterTypes(_aliasGetParameterTypes);
    }
    public String getAliasGetTypeParameters() {
        return content.getReflect().getAliasGetTypeParameters();
    }
    public void setAliasGetTypeParameters(String _aliasGetTypeParameters) {
        content.getReflect().setAliasGetTypeParameters(_aliasGetTypeParameters);
    }
    public String getAliasGetParameterNames() {
        return content.getReflect().getAliasGetParameterNames();
    }
    public void setAliasGetParameterNames(String _aliasGetNameParameters) {
        content.getReflect().setAliasGetParameterNames(_aliasGetNameParameters);
    }
    public String getAliasGetGenericReturnType() {
        return content.getReflect().getAliasGetGenericReturnType();
    }
    public void setAliasGetGenericReturnType(String _aliasGetGenericReturnType) {
        content.getReflect().setAliasGetGenericReturnType(_aliasGetGenericReturnType);
    }
    public String getAliasGetReturnType() {
        return content.getReflect().getAliasGetReturnType();
    }
    public void setAliasGetReturnType(String _aliasGetReturnType) {
        content.getReflect().setAliasGetReturnType(_aliasGetReturnType);
    }

    public String getAliasGetActualTypeArguments() {
        return content.getReflect().getAliasGetActualTypeArguments();
    }
    public void setAliasGetActualTypeArguments(
            String _aliasGetActualTypeArguments) {
        content.getReflect().setAliasGetActualTypeArguments(_aliasGetActualTypeArguments);
    }

    public void setAliasGetFieldType(String _aliasGetGenericType) {
        content.getReflect().setAliasGetType(_aliasGetGenericType);
    }
    public String getAliasGetGenericType() {
        return content.getReflect().getAliasGetGenericType();
    }
    public void setAliasGetGenericType(String _aliasGetGenericType) {
        content.getReflect().setAliasGetGenericType(_aliasGetGenericType);
    }
    public String getAliasIsFinal() {
        return content.getReflect().getAliasIsFinal();
    }
    public void setAliasIsFinal(String _aliasIsFinal) {
        content.getReflect().setAliasIsFinal(_aliasIsFinal);
    }
    public String getAliasIsTypeVariable() {
        return content.getReflect().getAliasIsTypeVariable();
    }
    public void setAliasIsTypeVariable(String _aliasIsFinal) {
        content.getReflect().setAliasIsTypeVariable(_aliasIsFinal);
    }
    public String getAliasIsVariable() {
        return content.getReflect().getAliasIsVariable();
    }
    public void setAliasIsVariable(String _aliasIsFinal) {
        content.getReflect().setAliasIsVariable(_aliasIsFinal);
    }
    public String getAliasIsStatic() {
        return content.getReflect().getAliasIsStatic();
    }
    public void setAliasIsStatic(String _aliasIsStatic) {
        content.getReflect().setAliasIsStatic(_aliasIsStatic);
    }
    public String getAliasIsStaticCall() {
        return content.getReflect().getAliasIsStaticCall();
    }
    public void setAliasIsStaticCall(String _aliasIsStatic) {
        content.getReflect().setAliasIsStaticCall(_aliasIsStatic);
    }

    public String getAliasIsInstanceMethod() {
        return content.getReflect().getAliasIsInstanceMethod();
    }

    public void setAliasIsInstanceMethod(String _aliasIsInstanceMethod) {
        content.getReflect().setAliasIsInstanceMethod(_aliasIsInstanceMethod);
    }
    public String getAliasIsVarargs() {
        return content.getReflect().getAliasIsVarargs();
    }
    public void setAliasIsVarargs(String _aliasIsVarargs) {
        content.getReflect().setAliasIsVarargs(_aliasIsVarargs);
    }
    public String getAliasIsNormal() {
        return content.getReflect().getAliasIsNormal();
    }
    public void setAliasIsNormal(String _aliasIsNormal) {
        content.getReflect().setAliasIsNormal(_aliasIsNormal);
    }
    public String getAliasIsPublic() {
        return content.getReflect().getAliasIsPublic();
    }
    public void setAliasIsPublic(String _aliasIsPublic) {
        content.getReflect().setAliasIsPublic(_aliasIsPublic);
    }
    public String getAliasIsProtected() {
        return content.getReflect().getAliasIsProtected();
    }
    public void setAliasIsProtected(String _aliasIsProtected) {
        content.getReflect().setAliasIsProtected(_aliasIsProtected);
    }
    public String getAliasIsPackage() {
        return content.getReflect().getAliasIsPackage();
    }
    public void setAliasIsPackage(String _aliasIsPackage) {
        content.getReflect().setAliasIsPackage(_aliasIsPackage);
    }
    public String getAliasIsPrivate() {
        return content.getReflect().getAliasIsPrivate();
    }
    public void setAliasIsPrivate(String _aliasIsPrivate) {
        content.getReflect().setAliasIsPrivate(_aliasIsPrivate);
    }
    public String getAliasIsClass() {
        return content.getReflect().getAliasIsClass();
    }
    public void setAliasIsClass(String _aliasIsClass) {
        content.getReflect().setAliasIsClass(_aliasIsClass);
    }

    public String getAliasIsWildCard() {
        return content.getReflect().getAliasIsWildCard();
    }
    public void setAliasIsWildCard(String _aliasIsWildCard) {
        content.getReflect().setAliasIsWildCard(_aliasIsWildCard);
    }

    public String getAliasIsInterface() {
        return content.getReflect().getAliasIsInterface();
    }
    public void setAliasIsInterface(String _aliasIsInterface) {
        content.getReflect().setAliasIsInterface(_aliasIsInterface);
    }
    public String getAliasIsEnum() {
        return content.getReflect().getAliasIsEnum();
    }
    public void setAliasIsEnum(String _aliasIsEnum) {
        content.getReflect().setAliasIsEnum(_aliasIsEnum);
    }
    public String getAliasIsPrimitive() {
        return content.getReflect().getAliasIsPrimitive();
    }
    public void setAliasIsPrimitive(String _aliasIsPrimitive) {
        content.getReflect().setAliasIsPrimitive(_aliasIsPrimitive);
    }
    public String getAliasIsArray() {
        return content.getReflect().getAliasIsArray();
    }
    public void setAliasIsArray(String _aliasIsArray) {
        content.getReflect().setAliasIsArray(_aliasIsArray);
    }
    public String getAliasIsAnnotation() {
        return content.getReflect().getAliasIsAnnotation();
    }
    public void setAliasIsAnnotation(String _aliasIsAnnotation) {
        content.getReflect().setAliasIsAnnotation(_aliasIsAnnotation);
    }
    public String getAliasMakeWildCard() {
        return content.getReflect().getAliasMakeWildCard();
    }
    public void setAliasMakeWildCard(String _aliasMakeWildCard) {
        content.getReflect().setAliasMakeWildCard(_aliasMakeWildCard);
    }
    public String getAliasIsInstance() {
        return content.getReflect().getAliasIsInstance();
    }
    public void setAliasIsInstance(String _aliasIsInstance) {
        content.getReflect().setAliasIsInstance(_aliasIsInstance);
    }
    public String getAliasIsAssignableFrom() {
        return content.getReflect().getAliasIsAssignableFrom();
    }
    public void setAliasIsAssignableFrom(String _aliasIsAssignableFrom) {
        content.getReflect().setAliasIsAssignableFrom(_aliasIsAssignableFrom);
    }
    public String getAliasInit() {
        return content.getReflect().getAliasInit();
    }
    public void setAliasInit(String _aliasInit) {
        content.getReflect().setAliasInit(_aliasInit);
    }
    public String getAliasDefaultInstance() {
        return content.getReflect().getAliasDefaultInstance();
    }
    public void setAliasDefaultInstance(String _aliasDefaultInstance) {
        content.getReflect().setAliasDefaultInstance(_aliasDefaultInstance);
    }
    public String getAliasEnumValueOf() {
        return content.getReflect().getAliasEnumValueOf();
    }
    public void setAliasEnumValueOf(String _aliasEnumValueOf) {
        content.getReflect().setAliasEnumValueOf(_aliasEnumValueOf);
    }
    public String getAliasGetEnumConstants() {
        return content.getReflect().getAliasGetEnumConstants();
    }
    public void setAliasGetEnumConstants(String _aliasGetEnumConstants) {
        content.getReflect().setAliasGetEnumConstants(_aliasGetEnumConstants);
    }
    public String getAliasGetGenericBounds() {
        return content.getReflect().getAliasGetGenericBounds();
    }
    public void setAliasGetGenericBounds(String _aliasGetGenericBounds) {
        content.getReflect().setAliasGetGenericBounds(_aliasGetGenericBounds);
    }
    public String getAliasGetBounds() {
        return content.getReflect().getAliasGetBounds();
    }
    public void setAliasGetBounds(String _aliasGetBounds) {
        content.getReflect().setAliasGetBounds(_aliasGetBounds);
    }
    public String getAliasArrayNewInstance() {
        return content.getReflect().getAliasArrayNewInstance();
    }
    public void setAliasArrayNewInstance(String _aliasArrayNewInstance) {
        content.getReflect().setAliasArrayNewInstance(_aliasArrayNewInstance);
    }
    public String getAliasArrayGet() {
        return content.getReflect().getAliasArrayGet();
    }
    public void setAliasArrayGet(String _aliasArrayGet) {
        content.getReflect().setAliasArrayGet(_aliasArrayGet);
    }
    public String getAliasArraySet() {
        return content.getReflect().getAliasArraySet();
    }
    public void setAliasArraySet(String _aliasArraySet) {
        content.getReflect().setAliasArraySet(_aliasArraySet);
    }
    public String getAliasArrayGetLength() {
        return content.getReflect().getAliasArrayGetLength();
    }
    public void setAliasArrayGetLength(String _aliasArrayGetLength) {
        content.getReflect().setAliasArrayGetLength(_aliasArrayGetLength);
    }
    public String getAliasGetDeclaringClass() {
        return content.getReflect().getAliasGetDeclaringClass();
    }
    public void setAliasGetDeclaringClass(String _aliasGetDeclaringClass) {
        content.getReflect().setAliasGetDeclaringClass(_aliasGetDeclaringClass);
    }
    public AliasMath getMathRef() {
        return content.getMathRef();
    }

    public AliasCharSequence getCharSeq() {
        return content.getCharSeq();
    }

    public AliasCore getCoreNames() {
        return content.getCoreNames();
    }

    public AliasNumber getNbAlias() {
        return content.getNbAlias();
    }

    public String getAliasBinQuot() {
        return content.getMathRef().getAliasBinQuot();
    }
    public void setAliasBinQuot(String _aliasBinQuot) {
        content.getMathRef().setAliasBinQuot(_aliasBinQuot);
    }
    public String getAliasBinMod() {
        return content.getMathRef().getAliasBinMod();
    }
    public void setAliasBinMod(String _aliasBinMod) {
        content.getMathRef().setAliasBinMod(_aliasBinMod);
    }
    public String getAliasPlus() {
        return content.getMathRef().getAliasPlus();
    }
    public void setAliasPlus(String _aliasPlus) {
        content.getMathRef().setAliasPlus(_aliasPlus);
    }
    public String getAliasMinus() {
        return content.getMathRef().getAliasMinus();
    }
    public void setAliasMinus(String _aliasMinus) {
        content.getMathRef().setAliasMinus(_aliasMinus);
    }
    public String getAliasMult() {
        return content.getMathRef().getAliasMult();
    }
    public void setAliasMult(String _aliasMult) {
        content.getMathRef().setAliasMult(_aliasMult);
    }
    public String getAliasAnd() {
        return content.getMathRef().getAliasAnd();
    }
    public void setAliasAnd(String _aliasAnd) {
        content.getMathRef().setAliasAnd(_aliasAnd);
    }
    public String getAliasOr() {
        return content.getMathRef().getAliasOr();
    }
    public void setAliasOr(String _aliasOr) {
        content.getMathRef().setAliasOr(_aliasOr);
    }
    public String getAliasXor() {
        return content.getMathRef().getAliasXor();
    }
    public void setAliasXor(String _aliasXor) {
        content.getMathRef().setAliasXor(_aliasXor);
    }
    public String getAliasNegBin() {
        return content.getMathRef().getAliasNegBin();
    }
    public void setAliasNegBin(String _aliasNegBin) {
        content.getMathRef().setAliasNegBin(_aliasNegBin);
    }
    
    public String getAliasNeg() {
        return content.getMathRef().getAliasNeg();
    }
    public void setAliasNeg(String _aliasNeg) {
        content.getMathRef().setAliasNeg(_aliasNeg);
    }
    public String getAliasLt() {
        return content.getMathRef().getAliasLt();
    }
    public void setAliasLt(String _aliasLt) {
        content.getMathRef().setAliasLt(_aliasLt);
    }
    public String getAliasGt() {
        return content.getMathRef().getAliasGt();
    }
    public void setAliasGt(String _aliasGt) {
        content.getMathRef().setAliasGt(_aliasGt);
    }
    public String getAliasLe() {
        return content.getMathRef().getAliasLe();
    }
    public void setAliasLe(String _aliasLe) {
        content.getMathRef().setAliasLe(_aliasLe);
    }
    public String getAliasGe() {
        return content.getMathRef().getAliasGe();
    }
    public void setAliasGe(String _aliasGe) {
        content.getMathRef().setAliasGe(_aliasGe);
    }
    public String getAliasShiftLeft() {
        return content.getMathRef().getAliasShiftLeft();
    }
    public void setAliasShiftLeft(String _aliasShiftLeft) {
        content.getMathRef().setAliasShiftLeft(_aliasShiftLeft);
    }
    public String getAliasShiftRight() {
        return content.getMathRef().getAliasShiftRight();
    }
    public void setAliasShiftRight(String _aliasShiftRight) {
        content.getMathRef().setAliasShiftRight(_aliasShiftRight);
    }
    public String getAliasBitShiftLeft() {
        return content.getMathRef().getAliasBitShiftLeft();
    }
    public void setAliasBitShiftLeft(String _aliasBitShiftLeft) {
        content.getMathRef().setAliasBitShiftLeft(_aliasBitShiftLeft);
    }
    public String getAliasBitShiftRight() {
        return content.getMathRef().getAliasBitShiftRight();
    }
    public void setAliasBitShiftRight(String _aliasBitShiftRight) {
        content.getMathRef().setAliasBitShiftRight(_aliasBitShiftRight);
    }
    public String getAliasRotateLeft() {
        return content.getMathRef().getAliasRotateLeft();
    }
    public void setAliasRotateLeft(String _aliasRotateLeft) {
        content.getMathRef().setAliasRotateLeft(_aliasRotateLeft);
    }
    public String getAliasRotateRight() {
        return content.getMathRef().getAliasRotateRight();
    }
    public void setAliasRotateRight(String _aliasRotateRight) {
        content.getMathRef().setAliasRotateRight(_aliasRotateRight);
    }
    public String getAliasRandom() {
        return content.getMathRef().getAliasRandom();
    }
    public void setAliasRandom(String _aliasRotateRight) {
        content.getMathRef().setAliasRandom(_aliasRotateRight);
    }
    public String getAliasSeed() {
        return content.getMathRef().getAliasSeed();
    }
    public void setAliasSeed(String _aliasRotateRight) {
        content.getMathRef().setAliasSeed(_aliasRotateRight);
    }
    public String getAliasStackTraceElement() {
        return content.getStackElt().getAliasStackTraceElement();
    }
    public void setAliasStackTraceElement(String _aliasStackTraceElement) {
        content.getStackElt().setAliasStackTraceElement(_aliasStackTraceElement);
    }
    public String getAliasCurrentStack() {
        return content.getStackElt().getAliasCurrentStack();
    }
    public void setAliasCurrentStack(String _aliasCurrentStack) {
        content.getStackElt().setAliasCurrentStack(_aliasCurrentStack);
    }
    public String getAliasCurrentFullStack() {
        return content.getStackElt().getAliasCurrentFullStack();
    }
    public void setAliasCurrentFullStack(String _aliasCurrentStack) {
        content.getStackElt().setAliasCurrentFullStack(_aliasCurrentStack);
    }

    public String getAliasEnumName() {
        return content.getPredefTypes().getAliasEnumName();
    }

    public void setAliasEnumName(String _aliasEnumName) {
        content.getPredefTypes().setAliasEnumName(_aliasEnumName);
    }

    public String getAliasEnumOrdinal() {
        return content.getPredefTypes().getAliasEnumOrdinal();
    }

    public void setAliasEnumOrdinal(String _aliasEnumOrdinal) {
        content.getPredefTypes().setAliasEnumOrdinal(_aliasEnumOrdinal);
    }

    public AliasStackTraceElement getStackElt() {
        return content.getStackElt();
    }

    public DisplayedStrings getDisplayedStrings() {
        return content.getDisplayedStrings();
    }

    public void setDefaultPkg(String _defaultPkg) {
        content.setDefaultPkg(_defaultPkg);
    }

    public AbstractGenerator getGenerator() {
        return generator;
    }

    public AliasPredefinedTypes getPredefTypes() {
        return content.getPredefTypes();
    }

    public LgNamesContent getContent() {
        return content;
    }

    public void setCalculator(AbstractExecConstantsCalculator calculator) {
        this.calculator = calculator;
    }
}
