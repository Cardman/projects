package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fcts.*;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class AliasReflection {
    private static final String GET_TYPE = "GetType";
    private static final String CALL = "Call";
    private static final String META_INFO = "MetaInfo";
    private static final String INSTANCE = "Instance";
    private static final String FCT = "Fct";
    private static final String GET_STRING = "GetString";
    private static final String GET_ANNOTATIONS_PARAMETERS = "GetAnnotationsParameters";
    private static final String INVOKE_TARGET = "InvokeTarget";
    private static final String GET_ANNOTATIONS = "GetAnnotations";
    private static final String GET_ANNOTATIONS_SUPP = "GetAnnotationsSupp";
    private static final String GET_VARIABLE_OWNER = "GetVariableOwner";
    private static final String CLASS_NOT_FOUND_ERROR = "ClassNotFoundError";
    private static final String CLASS_TYPE = "ClassType";
    private static final String ANNOTATION_TYPE = "AnnotationType";
    private static final String GET_GENERIC_VARIABLE_OWNER = "GetGenericVariableOwner";
    private static final String ANNOTATED = "Annotated";
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
    private static final String GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_NB = "GetDeclaredAnonymousLambdaLocalVarsNb";
    private static final String GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS = "GetDeclaredAnonymousLambdaLoopVars";
    private static final String GET_DECLARED_LOCAL_TYPES = "GetDeclaredLocalTypes";
    private static final String GET_DECLARED_BLOCKS = "GetDeclaredBlocks";
    private static final String GET_DECLARED_SWITCH_METHODS = "GetDeclaredSwitchMethods";
    private static final String FIELD = "Field";
    private static final String IS_NORMAL = "IsNormal";
    private static final String IS_PUBLIC = "IsPublic";
    private static final String IS_ARRAY = "IsArray";
    private static final String ARRAY_GET = "ArrayGet";
    private static final String METHOD = "Method";
    private static final String GET_FIELD = "GetField";
    private static final String INVOKE = "Invoke";
    private static final String IS_ENUM = "IsEnum";
    private static final String INIT = "Init";
    private static final String TRY_WRAP = "TryWrap";
    private static final String FOR_NAME = "ForName";
    private static final String IS_STATIC = "IsStatic";
    private static final String IS_STATIC_CALL = "IsStaticCall";
    private static final String IS_INSTANCE_METHOD = "IsInstanceMethod";
    private static final String GET_NAME = "GetName";
    private static final String IS_CLASS = "IsClass";
    private static final String IS_SPE_CLASS = "IsSpeClass";
    private static final String IS_SPE_MU_CLASS = "IsSpeMuClass";
    private static final String SET_FIELD = "SetField";
    private static final String GET_CLASS = "GetClass";
    private static final String IS_FINAL = "IsFinal";
    private static final String ARRAY_SET = "ArraySet";
    private static final String GET_BOUNDS = "GetBounds";
    private static final String GET_DECLARING_CLASS = "GetDeclaringClass";
    private static final String ENUM_VALUE_OF = "EnumValueOf";
    private static final String ARRAY_NEW_INSTANCE = "ArrayNewInstance";
    private static final String GET_ENUM_CONSTANTS = "GetEnumConstants";
    private static final String ARRAY_GET_LENGTH = "ArrayGetLength";
    private static final String GET_GENERIC_BOUNDS = "GetGenericBounds";
    private static final String DEFAULT_INSTANCE = "DefaultInstance";
    private static final String GET_PARAMETER_NAMES = "GetParameterNames";
    private static final String GET_PRETTY_NAME = "GetPrettyName";
    private static final String GET_PRETTY_SINGLE_NAME = "GetPrettySingleName";
    private static final String GET_UPPER_BOUNDS = "GetUpperBounds";
    private static final String GET_PARAMETER_TYPES = "GetParameterTypes";
    private static final String GET_GENERIC_RETURN_TYPE = "GetGenericReturnType";
    private static final String INVOKE_DIRECT = "InvokeDirect";
    private static final String GET_LOWER_BOUNDS = "GetLowerBounds";
    private static final String GET_TYPE_PARAMETERS = "GetTypeParameters";
    private static final String CONSTRUCTOR = "Constructor";
    private static final String NEW_INSTANCE = "NewInstance";
    private static final String GET_ENCLOSING_TYPE = "GetEnclosingType";
    private static final String GET_INTERFACES = "GetInterfaces";
    private static final String GET_DECLARED_CLASSES = "GetDeclaredClasses";
    private static final String GET_SUPER_CLASS = "GetSuperClass";
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
    private String aliasAnnotationType;
    private String aliasAnnotated;
    private String aliasGetDefaultValue;
    private String aliasGetAnnotations;
    private String aliasGetAnnotationsSupp;
    private String aliasGetAnnotationsParameters;
    private String aliasFct;
    private String aliasCall;
    private String aliasMetaInfo;
    private String aliasInstance;
    private String aliasClassType;
    private String aliasGetClass;
    private String aliasGetEnclosingType;
    private String aliasGetDeclaredClasses;
    private String aliasGetDeclaredExplicits;
    private String aliasGetDeclaredImplicits;
    private String aliasGetDeclaredTrueOperators;
    private String aliasGetDeclaredFalseOperators;
    private String aliasGetDeclaredMethods;
    private String aliasGetDeclaredStaticMethods;
    private String aliasGetDeclaredConstructors;
    private String aliasGetDeclaredFields;
    private String aliasGetDeclaredBlocks;
    private String aliasGetDeclaredSwitchMethods;
    private String aliasGetDeclaredAnonymousLambda;
    private String aliasGetDeclaredAnonymousLambdaLocalVars;
    private String aliasGetDeclaredAnonymousLambdaLocalVarsNb;
    private String aliasGetDeclaredAnonymousLambdaLoopVars;
    private String aliasGetDeclaredLocalTypes;
    private String aliasGetDeclaredAnonymousTypes;
    private String aliasGetSuperClass;
    private String aliasGetGenericSuperClass;
    private String aliasGetInterfaces;
    private String aliasGetGenericInterfaces;
    private String aliasGetLowerBounds;
    private String aliasGetUpperBounds;
    private String aliasMakeArray;
    private String aliasMakeWildCard;
    private String aliasMakeRef;
    private String aliasGetComponentType;
    private String aliasGetTypeParameters;

    private String aliasGetParameterTypes;
    private String aliasGetParameterNames;
    private String aliasGetGenericReturnType;
    private String aliasGetReturnType;
    private String aliasGetActualTypeArguments;
    private String aliasIsFinal;
    private String aliasIsTypeVariable;
    private String aliasIsVariable;
    private String aliasIsStatic;
    private String aliasIsStaticCall;
    private String aliasIsInstanceMethod;
    private String aliasIsVarargs;
    private String aliasIsNormal;
    private String aliasIsPublic;
    private String aliasIsProtected;
    private String aliasIsPackage;
    private String aliasIsPrivate;
    private String aliasIsClass;
    private String aliasIsSpecialClass;
    private String aliasIsSpecialMuClass;
    private String aliasIsWildCard;
    private String aliasIsRefType;
    private String aliasIsInterface;
    private String aliasIsEnum;
    private String aliasIsPrimitive;
    private String aliasIsAnnotation;
    private String aliasIsArray;
    private String aliasIsInstance;
    private String aliasIsAssignableFrom;
    private String aliasInit;
    private String aliasTryWrap;
    private String aliasDefaultInstance;
    private String aliasEnumValueOf;
    private String aliasGetEnumConstants;
    private String aliasGetGenericBounds;
    private String aliasGetBounds;
    private String aliasArrayNewInstance;
    private String aliasArrayGet;
    private String aliasArraySet;
    private String aliasArrayGetLength;
    private String aliasMakeGeneric;
    private String aliasGetAllClasses;
    private String aliasGetOperators;
    private String aliasConstructor;
    private String aliasField;
    private String aliasMethod;
    private String aliasInvoke;
    private String aliasInvokeDirect;
    private String aliasNewInstance;
    private String aliasIsAbstract;
    private String aliasGetFileName;
    private String aliasGetName;
    private String aliasGetPrettyName;
    private String aliasGetPrettySingleName;
    private String aliasGetField;
    private String aliasSetField;
    private String aliasGetGenericType;
    private String aliasGetType;
    private String aliasForName;
    private String aliasGetDeclaringClass;
    private String aliasInvokeTarget;
    private String aliasClassNotFoundError;
    private String aliasGetVariableOwner;
    private String aliasGetGenericVariableOwner;
    private String aliasGetString;
    private final AliasParamReflection params = new AliasParamReflection();

    public void build(StringMap<String> _util, StringMap<String> _cust) {
        setAliasGetType(LgNamesContent.get(_util,_cust, GET_TYPE));
        setAliasCall(LgNamesContent.get(_util,_cust, CALL));
        setAliasMetaInfo(LgNamesContent.get(_util,_cust, META_INFO));
        setAliasInstance(LgNamesContent.get(_util,_cust, INSTANCE));
        setAliasFct(LgNamesContent.get(_util,_cust, FCT));
        setAliasGetString(LgNamesContent.get(_util,_cust, GET_STRING));
        setAliasGetAnnotationsParameters(LgNamesContent.get(_util,_cust, GET_ANNOTATIONS_PARAMETERS));
        setAliasInvokeTarget(LgNamesContent.get(_util,_cust, INVOKE_TARGET));
        setAliasGetAnnotations(LgNamesContent.get(_util,_cust, GET_ANNOTATIONS));
        setAliasGetAnnotationsSupp(LgNamesContent.get(_util,_cust, GET_ANNOTATIONS_SUPP));
        setAliasGetVariableOwner(LgNamesContent.get(_util,_cust, GET_VARIABLE_OWNER));
        setAliasClassNotFoundError(LgNamesContent.get(_util,_cust, CLASS_NOT_FOUND_ERROR));
        setAliasClassType(LgNamesContent.get(_util,_cust, CLASS_TYPE));
        setAliasAnnotationType(LgNamesContent.get(_util,_cust, ANNOTATION_TYPE));
        setAliasGetGenericVariableOwner(LgNamesContent.get(_util,_cust, GET_GENERIC_VARIABLE_OWNER));
        setAliasAnnotated(LgNamesContent.get(_util,_cust, ANNOTATED));
        setAliasGetDefaultValue(LgNamesContent.get(_util,_cust, GET_DEFAULT_VALUE));
        setAliasMakeGeneric(LgNamesContent.get(_util,_cust, MAKE_GENERIC));
        setAliasGetAllClasses(LgNamesContent.get(_util,_cust, GET_ALL_CLASSES));
        setAliasGetOperators(LgNamesContent.get(_util,_cust, GET_OPERATORS));
        setAliasGetDeclaredExplicits(LgNamesContent.get(_util,_cust, GET_DECLARED_EXPLICITS));
        setAliasGetDeclaredImplicits(LgNamesContent.get(_util,_cust, GET_DECLARED_IMPLICITS));
        setAliasGetDeclaredTrueOperators(LgNamesContent.get(_util,_cust, GET_DECLARED_TRUE_OPERATORS));
        setAliasGetDeclaredFalseOperators(LgNamesContent.get(_util,_cust, GET_DECLARED_FALSE_OPERATORS));
        setAliasGetDeclaredMethods(LgNamesContent.get(_util,_cust, GET_DECLARED_METHODS));
        setAliasGetDeclaredStaticMethods(LgNamesContent.get(_util,_cust, GET_DECLARED_STATIC_METHODS));
        setAliasGetDeclaredConstructors(LgNamesContent.get(_util,_cust, GET_DECLARED_CONSTRUCTORS));
        setAliasGetDeclaredFields(LgNamesContent.get(_util,_cust, GET_DECLARED_FIELDS));
        setAliasGetDeclaredAnonymousTypes(LgNamesContent.get(_util,_cust, GET_DECLARED_ANONYMOUS_TYPES));
        setAliasGetDeclaredAnonymousLambda(LgNamesContent.get(_util,_cust, GET_DECLARED_ANONYMOUS_LAMBDA));
        setAliasGetDeclaredAnonymousLambdaLocalVars(LgNamesContent.get(_util,_cust, GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS));
        setAliasGetDeclaredAnonymousLambdaLocalVarsNb(LgNamesContent.get(_util,_cust, GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_NB));
        setAliasGetDeclaredAnonymousLambdaLoopVars(LgNamesContent.get(_util,_cust, GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS));
        setAliasGetDeclaredBlocks(LgNamesContent.get(_util,_cust, GET_DECLARED_BLOCKS));
        setAliasGetDeclaredSwitchMethods(LgNamesContent.get(_util,_cust, GET_DECLARED_SWITCH_METHODS));
        setAliasGetDeclaredLocalTypes(LgNamesContent.get(_util,_cust, GET_DECLARED_LOCAL_TYPES));
        setAliasField(LgNamesContent.get(_util,_cust, FIELD));
        setAliasIsNormal(LgNamesContent.get(_util,_cust, IS_NORMAL));
        setAliasIsPublic(LgNamesContent.get(_util,_cust, IS_PUBLIC));
        setAliasIsArray(LgNamesContent.get(_util,_cust, IS_ARRAY));
        setAliasArrayGet(LgNamesContent.get(_util,_cust, ARRAY_GET));
        setAliasMethod(LgNamesContent.get(_util,_cust, METHOD));
        setAliasGetField(LgNamesContent.get(_util,_cust, GET_FIELD));
        setAliasInvoke(LgNamesContent.get(_util,_cust, INVOKE));
        setAliasIsEnum(LgNamesContent.get(_util,_cust, IS_ENUM));
        setAliasInit(LgNamesContent.get(_util,_cust, INIT));
        setAliasTryWrap(LgNamesContent.get(_util,_cust, TRY_WRAP));
        setAliasForName(LgNamesContent.get(_util,_cust, FOR_NAME));
        setAliasIsStatic(LgNamesContent.get(_util,_cust, IS_STATIC));
        setAliasIsStaticCall(LgNamesContent.get(_util,_cust, IS_STATIC_CALL));
        setAliasIsInstanceMethod(LgNamesContent.get(_util,_cust, IS_INSTANCE_METHOD));
        setAliasGetName(LgNamesContent.get(_util,_cust, GET_NAME));
        setAliasIsClass(LgNamesContent.get(_util,_cust, IS_CLASS));
        setAliasIsSpecialClass(LgNamesContent.get(_util,_cust, IS_SPE_CLASS));
        setAliasIsSpecialMuClass(LgNamesContent.get(_util,_cust, IS_SPE_MU_CLASS));
        setAliasSetField(LgNamesContent.get(_util,_cust, SET_FIELD));
        setAliasGetClass(LgNamesContent.get(_util,_cust, GET_CLASS));
        setAliasIsFinal(LgNamesContent.get(_util,_cust, IS_FINAL));
        setAliasArraySet(LgNamesContent.get(_util,_cust, ARRAY_SET));
        setAliasGetBounds(LgNamesContent.get(_util,_cust, GET_BOUNDS));
        setAliasGetDeclaringClass(LgNamesContent.get(_util,_cust, GET_DECLARING_CLASS));
        setAliasEnumValueOf(LgNamesContent.get(_util,_cust, ENUM_VALUE_OF));
        setAliasArrayNewInstance(LgNamesContent.get(_util,_cust, ARRAY_NEW_INSTANCE));
        setAliasGetEnumConstants(LgNamesContent.get(_util,_cust, GET_ENUM_CONSTANTS));
        setAliasArrayGetLength(LgNamesContent.get(_util,_cust, ARRAY_GET_LENGTH));
        setAliasGetGenericBounds(LgNamesContent.get(_util,_cust, GET_GENERIC_BOUNDS));
        setAliasDefaultInstance(LgNamesContent.get(_util,_cust, DEFAULT_INSTANCE));
        setAliasGetParameterNames(LgNamesContent.get(_util,_cust, GET_PARAMETER_NAMES));
        setAliasGetPrettyName(LgNamesContent.get(_util,_cust, GET_PRETTY_NAME));
        setAliasGetPrettySingleName(LgNamesContent.get(_util,_cust, GET_PRETTY_SINGLE_NAME));
        setAliasGetUpperBounds(LgNamesContent.get(_util,_cust, GET_UPPER_BOUNDS));
        setAliasGetParameterTypes(LgNamesContent.get(_util,_cust, GET_PARAMETER_TYPES));
        setAliasGetGenericReturnType(LgNamesContent.get(_util,_cust, GET_GENERIC_RETURN_TYPE));
        setAliasInvokeDirect(LgNamesContent.get(_util,_cust, INVOKE_DIRECT));
        setAliasGetLowerBounds(LgNamesContent.get(_util,_cust, GET_LOWER_BOUNDS));
        setAliasGetTypeParameters(LgNamesContent.get(_util,_cust, GET_TYPE_PARAMETERS));
        setAliasConstructor(LgNamesContent.get(_util,_cust, CONSTRUCTOR));
        setAliasNewInstance(LgNamesContent.get(_util,_cust, NEW_INSTANCE));
        setAliasGetEnclosingType(LgNamesContent.get(_util,_cust, GET_ENCLOSING_TYPE));
        setAliasGetInterfaces(LgNamesContent.get(_util,_cust, GET_INTERFACES));
        setAliasGetDeclaredClasses(LgNamesContent.get(_util,_cust, GET_DECLARED_CLASSES));
        setAliasGetSuperClass(LgNamesContent.get(_util,_cust, GET_SUPER_CLASS));
        setAliasGetComponentType(LgNamesContent.get(_util,_cust, GET_COMPONENT_TYPE));
        setAliasGetFileName(LgNamesContent.get(_util,_cust, GET_FILE_NAME));
        setAliasGetGenericSuperClass(LgNamesContent.get(_util,_cust, GET_GENERIC_SUPER_CLASS));
        setAliasGetGenericInterfaces(LgNamesContent.get(_util,_cust, GET_GENERIC_INTERFACES));
        setAliasIsAbstract(LgNamesContent.get(_util,_cust, IS_ABSTRACT));
        setAliasMakeArray(LgNamesContent.get(_util,_cust, MAKE_ARRAY));
        setAliasIsInterface(LgNamesContent.get(_util,_cust, IS_INTERFACE));
        setAliasMakeRef(LgNamesContent.get(_util,_cust, MAKE_REF_TYPE));
        setAliasMakeWildCard(LgNamesContent.get(_util,_cust, MAKE_WILD_CARD));
        setAliasIsTypeVariable(LgNamesContent.get(_util,_cust, IS_TYPE_VARIABLE));
        setAliasIsPrivate(LgNamesContent.get(_util,_cust, IS_PRIVATE));
        setAliasIsVarargs(LgNamesContent.get(_util,_cust, IS_VARARGS));
        setAliasIsInstance(LgNamesContent.get(_util,_cust, IS_INSTANCE));
        setAliasGetReturnType(LgNamesContent.get(_util,_cust, GET_RETURN_TYPE));
        setAliasGetActualTypeArguments(LgNamesContent.get(_util,_cust, GET_ACTUAL_TYPE_ARGUMENTS));
        setAliasIsProtected(LgNamesContent.get(_util,_cust, IS_PROTECTED));
        setAliasIsPrimitive(LgNamesContent.get(_util,_cust, IS_PRIMITIVE));
        setAliasIsRefType(LgNamesContent.get(_util,_cust, IS_REF_TYPE));
        setAliasIsWildCard(LgNamesContent.get(_util,_cust, IS_WILD_CARD));
        setAliasIsAnnotation(LgNamesContent.get(_util,_cust, IS_ANNOTATION));
        setAliasGetGenericType(LgNamesContent.get(_util,_cust, GET_GENERIC_TYPE));
        setAliasIsAssignableFrom(LgNamesContent.get(_util,_cust, IS_ASSIGNABLE_FROM));
        setAliasIsVariable(LgNamesContent.get(_util,_cust, IS_VARIABLE));
        setAliasIsPackage(LgNamesContent.get(_util,_cust, IS_PACKAGE));
    }
    public StringMap<String> allRefTypes() {
        StringMap<String> list_ = new StringMap<String>();
        list_.addEntry(ANNOTATED, getAliasAnnotated());
        list_.addEntry(ANNOTATION_TYPE, getAliasAnnotationType());
        list_.addEntry(CLASS_TYPE, getAliasClassType());
        list_.addEntry(CONSTRUCTOR, getAliasConstructor());
        list_.addEntry(FCT, getAliasFct());
        list_.addEntry(FIELD, getAliasField());
        list_.addEntry(METHOD, getAliasMethod());
        list_.addEntry(CLASS_NOT_FOUND_ERROR, getAliasClassNotFoundError());
        list_.addEntry(INVOKE_TARGET, getAliasInvokeTarget());
        return list_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(getAliasAnnotated(), listAnnot());
        map_.addEntry(getAliasAnnotationType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_STRING, getAliasGetString())));
        map_.addEntry(getAliasClassType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_ANNOTATIONS, getAliasGetAnnotations()),
                new KeyValueMemberName(GET_ANNOTATIONS_PARAMETERS, getAliasGetAnnotationsParameters()),
                new KeyValueMemberName(GET_DECLARING_CLASS,getAliasGetDeclaringClass()),
                new KeyValueMemberName(DEFAULT_INSTANCE, getAliasDefaultInstance()),
                new KeyValueMemberName(ENUM_VALUE_OF, getAliasEnumValueOf()),
                new KeyValueMemberName(FOR_NAME, getAliasForName()),
                new KeyValueMemberName(ARRAY_GET, getAliasArrayGet()),
                new KeyValueMemberName(GET_ACTUAL_TYPE_ARGUMENTS, getAliasGetActualTypeArguments()),
                new KeyValueMemberName(GET_ALL_CLASSES, getAliasGetAllClasses()),
                new KeyValueMemberName(GET_BOUNDS, getAliasGetBounds()),
                new KeyValueMemberName(GET_CLASS, getAliasGetClass()),
                new KeyValueMemberName(GET_COMPONENT_TYPE, getAliasGetComponentType()),
                new KeyValueMemberName(GET_DECLARED_CLASSES, getAliasGetDeclaredClasses()),
                new KeyValueMemberName(GET_DECLARED_CONSTRUCTORS, getAliasGetDeclaredConstructors()),
                new KeyValueMemberName(GET_DECLARED_FIELDS, getAliasGetDeclaredFields()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_TYPES, getAliasGetDeclaredAnonymousTypes()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA, getAliasGetDeclaredAnonymousLambda()),
                new KeyValueMemberName(GET_DECLARED_SWITCH_METHODS,getAliasGetDeclaredSwitchMethods()),
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
                new KeyValueMemberName(TRY_WRAP,getAliasTryWrap()),
                new KeyValueMemberName(IS_ABSTRACT,getAliasIsAbstract()),
                new KeyValueMemberName(IS_ANNOTATION,getAliasIsAnnotation()),
                new KeyValueMemberName(IS_ARRAY,getAliasIsArray()),
                new KeyValueMemberName(IS_ASSIGNABLE_FROM,getAliasIsAssignableFrom()),
                new KeyValueMemberName(IS_SPE_CLASS,getAliasIsSpecialClass()),
                new KeyValueMemberName(IS_SPE_MU_CLASS,getAliasIsSpecialMuClass()),
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
                new KeyValueMemberName(IS_REF_TYPE,getAliasIsRefType()),
                new KeyValueMemberName(IS_WILD_CARD,getAliasIsWildCard()),
                new KeyValueMemberName(MAKE_ARRAY,getAliasMakeArray()),
                new KeyValueMemberName(MAKE_GENERIC,getAliasMakeGeneric()),
                new KeyValueMemberName(MAKE_REF_TYPE,getAliasMakeRef()),
                new KeyValueMemberName(MAKE_WILD_CARD,getAliasMakeWildCard()),
                new KeyValueMemberName(ARRAY_NEW_INSTANCE,getAliasArrayNewInstance()),
                new KeyValueMemberName(ARRAY_SET,getAliasArraySet())));
        map_.addEntry(getAliasConstructor(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_ANNOTATIONS,getAliasGetAnnotations()),
                new KeyValueMemberName(GET_ANNOTATIONS_PARAMETERS,getAliasGetAnnotationsParameters()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_TYPES,getAliasGetDeclaredAnonymousTypes()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA,getAliasGetDeclaredAnonymousLambda()),
                new KeyValueMemberName(GET_DECLARED_SWITCH_METHODS,getAliasGetDeclaredSwitchMethods()),
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
                new KeyValueMemberName(GET_DECLARED_SWITCH_METHODS,getAliasGetDeclaredSwitchMethods()),
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
                new KeyValueMemberName(GET_ANNOTATIONS_SUPP,getAliasGetAnnotationsSupp()),
                new KeyValueMemberName(GET_ANNOTATIONS_PARAMETERS,getAliasGetAnnotationsParameters()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_TYPES,getAliasGetDeclaredAnonymousTypes()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA,getAliasGetDeclaredAnonymousLambda()),
                new KeyValueMemberName(GET_DECLARED_SWITCH_METHODS,getAliasGetDeclaredSwitchMethods()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS,getAliasGetDeclaredAnonymousLambdaLocalVars()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_NB,getAliasGetDeclaredAnonymousLambdaLocalVarsNb()),
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
        return map_;
    }

    public CustList<KeyValueMemberName> listAnnot() {
        return new CustList<KeyValueMemberName>(
                new KeyValueMemberName(GET_FILE_NAME, getAliasGetFileName()),
                new KeyValueMemberName(GET_DECLARING_CLASS, getAliasGetDeclaringClass()),
                new KeyValueMemberName(GET_DECLARED_ANONYMOUS_LAMBDA, getAliasGetDeclaredAnonymousLambda()),
                new KeyValueMemberName(GET_DECLARED_SWITCH_METHODS, getAliasGetDeclaredSwitchMethods()),
                new KeyValueMemberName(GET_ANNOTATIONS, getAliasGetAnnotations()),
                new KeyValueMemberName(GET_ANNOTATIONS_PARAMETERS, getAliasGetAnnotationsParameters()));
    }
    public void build(LgNames _stds) {
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        String aliasObject_ = _stds.getContent().getCoreNames().getAliasObject();
        String aliasString_ = _stds.getContent().getCharSeq().getAliasString();
        String aliasPrimBoolean_ = _stds.getContent().getPrimTypes().getAliasPrimBoolean();
        String aliasBoolean_ = _stds.getContent().getNbAlias().getAliasBoolean();
        String aliasPrimInt_ = _stds.getContent().getPrimTypes().getAliasPrimInteger();
        String aliasVoid_ = _stds.getContent().getCoreNames().getAliasVoid();
        String aliasError_ = _stds.getContent().getCoreNames().getAliasError();
        String aliasEnum_ = _stds.getContent().getPredefTypes().getAliasEnumType();
        StandardClass stdcl_ = new StandardClass(aliasFct, fields_, constructors_, methods_, aliasObject_, StdClassModifier.ABSTRACT);
        StringList params_ = new StringList(aliasObject_);
        StandardMethod method_ = new StandardMethod(aliasCall, params_, aliasObject_, true, MethodModifier.FINAL, new StringList(params.getAliasFct0Call0()), new FctLambdaCall());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMetaInfo, params_, aliasAnnotated, false, MethodModifier.FINAL, new FctLambdaMetaInfo());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasInstance, params_, aliasObject_, false, MethodModifier.FINAL, new FctLambdaInstance());
        methods_.add( method_);
        _stds.getStandards().addEntry(aliasFct, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasClassType, fields_, constructors_, methods_, aliasAnnotated , StdClassModifier.ABSTRACT, new DfClass());
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetName, params_, aliasString_, false, MethodModifier.FINAL, new FctClassGetName());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetPrettyName, params_, aliasString_, false, MethodModifier.FINAL, new FctClassGetPrettyName());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetPrettySingleName, params_, aliasString_, false, MethodModifier.FINAL,new FctClassGetPrettySingleName());
        methods_.add( method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasGetClass, params_, aliasClassType, false, MethodModifier.STATIC,new StringList(params.getAliasClassType0GetClass0()),new FctClassGetClass());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetEnclosingType, params_, aliasClassType, false, MethodModifier.FINAL, new FctClassGetEnclosingType());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredClasses, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, new FctClassGetDeclaredClasses());
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasPrimBoolean_);
        method_ = new StandardMethod(aliasForName, params_, aliasClassType, false, MethodModifier.STATIC,new StringList(params.getAliasClassType0ForName0(),params.getAliasClassType0ForName1()),new FctClassForName());
        methods_.add( method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasForName, params_, aliasClassType, false, MethodModifier.STATIC,new StringList(params.getAliasClassType1ForName0()),new FctClassForName());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasInit, params_, aliasVoid_, false, MethodModifier.FINAL,new FctClassInit());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsAnnotation, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsAnnotation());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsArray, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsArray());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsAbstract, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsAbstract());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsStatic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsStatic());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsClass, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsClass());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsSpecialClass, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsSpecialClass());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsSpecialMuClass, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsSpecialMuClass());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsRefType, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsRefType());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsWildCard, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsWildCard());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsEnum, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsEnum());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsFinal, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsFinal());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsVariable, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsVariable());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsTypeVariable, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsTypeVariable());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsInterface, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsInterface());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPrimitive, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctClassIsPrimitive());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetActualTypeArguments, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, new FctClassGetActualTypeArguments());
        methods_.add( method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasIsInstance, params_, aliasPrimBoolean_, false, MethodModifier.FINAL,new StringList(params.getAliasClassType0IsInstance0()),new FctClassIsInstance());
        methods_.add( method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasIsAssignableFrom, params_, aliasPrimBoolean_, false, MethodModifier.FINAL,new StringList(params.getAliasClassType0IsAssignableFrom0()),new FctClassIsAssignableFrom());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDefaultInstance, params_, aliasObject_, false, MethodModifier.FINAL, new FctClassDefaultInstance0());
        methods_.add( method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasDefaultInstance, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasClassType0DefaultInstance0()), new FctClassDefaultInstance1());
        methods_.add( method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasEnumValueOf, params_, aliasEnum_, false, MethodModifier.FINAL,new StringList(params.getAliasClassType0EnumValueOf0()),new FctClassEnumValueOf());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetEnumConstants, params_, StringExpUtil.getPrettyArrayType(aliasEnum_), false, MethodModifier.FINAL, new FctClassEnumValues());
        methods_.add( method_);
        params_ = new StringList(aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredConstructors, params_, StringExpUtil.getPrettyArrayType(aliasConstructor), true, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredConstructors0(),params.getAliasClassType0GetDeclaredConstructors1()),new FctClassGetDeclaredConstructors1());
        methods_.add( method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasGetDeclaredFields, params_, StringExpUtil.getPrettyArrayType(aliasField), false, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredFields0()), new FctClassGetDeclaredFields1());
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasBoolean_,aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredStaticMethods, params_, StringExpUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredStaticMethods0(),params.getAliasClassType0GetDeclaredStaticMethods1(),params.getAliasClassType0GetDeclaredStaticMethods2(),params.getAliasClassType0GetDeclaredStaticMethods3()),new FctClassGetDeclaredStaticMethods1());
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasBoolean_,aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredMethods, params_, StringExpUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredMethods0(),params.getAliasClassType0GetDeclaredMethods1(),params.getAliasClassType0GetDeclaredMethods2(),params.getAliasClassType0GetDeclaredMethods3()), new FctClassGetDeclaredMethods1());
        methods_.add( method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredExplicits, params_, StringExpUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredExplicits0()),new FctClassGetDeclaredExplicits1());
        methods_.add( method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredImplicits, params_, StringExpUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredImplicits0()),new FctClassGetDeclaredImplicits1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredConstructors, params_, StringExpUtil.getPrettyArrayType(aliasConstructor), false, MethodModifier.FINAL, new FctClassGetDeclaredConstructors0());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredFields, params_, StringExpUtil.getPrettyArrayType(aliasField), false, MethodModifier.FINAL, new FctClassGetDeclaredFields0());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredStaticMethods, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL, new FctClassGetDeclaredStaticMethods0());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredMethods, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL, new FctClassGetDeclaredMethods0());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredExplicits, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL, new FctClassGetDeclaredExplicits0());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredImplicits, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL, new FctClassGetDeclaredImplicits0());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredTrueOperators, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL, new FctClassGetDeclaredTrueOperators());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredFalseOperators, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL, new FctClassGetDeclaredFalseOperators());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredBlocks, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL, new FctClassGetDeclaredBlocks0());
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasBoolean_);
        method_ = new StandardMethod(aliasGetDeclaredBlocks, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL,new StringList(params.getAliasClassType0GetDeclaredBlocks0(),params.getAliasClassType0GetDeclaredBlocks1()), new FctClassGetDeclaredBlocks1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetSuperClass, params_, aliasClassType, false, MethodModifier.FINAL, new FctClassGetSuperClass());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericSuperClass, params_, aliasClassType, false, MethodModifier.FINAL, new FctClassGetGenericSuperClass());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetInterfaces, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL,new FctClassGetInterfaces());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericInterfaces, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, new FctClassGetGenericInterfaces());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetUpperBounds, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL,new FctClassGetUpperBounds());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetLowerBounds, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL,new FctClassGetLowerBounds());
        methods_.add( method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasMakeGeneric, params_, aliasClassType, true, MethodModifier.FINAL,new StringList(params.getAliasClassType0MakeGeneric0()),new FctClassMakeGeneric());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasMakeArray, params_, aliasClassType, false, MethodModifier.FINAL,new FctClassMakeArray());
        methods_.add( method_);
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasMakeRef, params_, aliasClassType, false, MethodModifier.FINAL,new StringList(params.getAliasClassType0MakeRef0()),new FctClassMakeRef());
        methods_.add( method_);
        params_ = new StringList(aliasBoolean_);
        method_ = new StandardMethod(aliasMakeWildCard, params_, aliasClassType, false, MethodModifier.FINAL,new StringList(params.getAliasClassType0MakeWildCard0()),new FctClassMakeWildCard());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetComponentType, params_, aliasClassType, false, MethodModifier.FINAL, new FctClassGetComponentType());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetVariableOwner, params_, aliasClassType, false, MethodModifier.FINAL,new FctClassGetVariableOwner());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericVariableOwner, params_, aliasClassType, false, MethodModifier.FINAL,new FctClassGetGenericVariableOwner());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetTypeParameters, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, new FctClassGetTypeParameters());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetBounds, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, new FctClassGetBounds());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericBounds, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, new FctClassGetGenericBounds());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAllClasses, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.STATIC, new FctClassGetAllClasses());
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetOperators, params_, StringExpUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.STATIC,new StringList(params.getAliasClassType0GetOperators0(),params.getAliasClassType0GetOperators1(),params.getAliasClassType0GetOperators2()),new FctClassGetDeclaredOperators1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetOperators, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.STATIC,new FctClassGetDeclaredOperators0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInt_);
        method_ = new StandardMethod(aliasArrayNewInstance, params_, StringExpUtil.getPrettyArrayType(aliasObject_), true, MethodModifier.FINAL,new StringList(params.getAliasClassType0ArrayNewInstance0()),new FctClassArrayNewInstance());
        methods_.add( method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasArrayGetLength, params_, aliasPrimInt_, false, MethodModifier.STATIC,new StringList(params.getAliasClassType0ArrayGetLength0()),new FctClassArrayGetLength());
        methods_.add( method_);
        params_ = new StringList(aliasObject_,aliasPrimInt_);
        method_ = new StandardMethod(aliasArrayGet, params_, aliasObject_, false, MethodModifier.STATIC,new StringList(params.getAliasClassType0ArrayGet0(),params.getAliasClassType0ArrayGet1()),new FctClassArrayGet());
        methods_.add( method_);
        params_ = new StringList(aliasObject_,aliasPrimInt_,aliasObject_);
        method_ = new StandardMethod(aliasArraySet, params_, aliasVoid_, false, MethodModifier.STATIC,new StringList(params.getAliasClassType0ArraySet0(),params.getAliasClassType0ArraySet1(),params.getAliasClassType0ArraySet2()),new FctClassArraySet());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredAnonymousTypes, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, new FctClassGetDeclaredAnonymousTypes());
        methods_.add( method_);
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasTryWrap, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasClassType0TryWrap0()),new FctClassTryWrap());
        methods_.add( method_);
        _stds.getStandards().addEntry(aliasClassType, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasConstructor, fields_, constructors_, methods_, aliasAnnotated, StdClassModifier.ABSTRACT, new DfCtor());
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasNewInstance, params_, aliasObject_, true, MethodModifier.FINAL,new StringList(params.getAliasConstructor0NewInstance0()),new FctConstructorNewInstance());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParameterTypes, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, new FctCallerGetParameterTypes());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParameterNames, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, new FctCallerGetParameterNames());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetName, params_, aliasString_, false, MethodModifier.FINAL, new FctConstructorGetName());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericReturnType, params_, aliasClassType, false, MethodModifier.FINAL, new FctAnnotatedGetGenericReturnType());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetReturnType, params_, aliasClassType, false, MethodModifier.FINAL, new FctAnnotatedGetReturnType());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsVarargs, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctCallerIsVararg());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredAnonymousTypes, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, new FctConstructorGetDeclaredAnonymousTypes());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredLocalTypes, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, new FctConstructorGetDeclaredLocalTypes());
        methods_.add( method_);
        _stds.getStandards().addEntry(aliasConstructor, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasField, fields_, constructors_, methods_, aliasAnnotated, StdClassModifier.ABSTRACT, new DfField());
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasGetField, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasField0GetField0()),new FctFieldGetField());
        methods_.add( method_);
        params_ = new StringList(aliasObject_,aliasObject_);
        method_ = new StandardMethod(aliasSetField, params_, aliasVoid_, false, MethodModifier.FINAL,new StringList(params.getAliasField0SetField0(),params.getAliasField0SetField1()),new FctFieldSetField());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetName, params_, aliasString_, false, MethodModifier.FINAL, new FctFieldGetName());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsStatic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctFieldIsStatic());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsFinal, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctFieldIsFinal());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetType, params_, aliasClassType, false, MethodModifier.FINAL, new FctAnnotatedGetReturnType());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericType, params_, aliasClassType, false, MethodModifier.FINAL, new FctAnnotatedGetGenericReturnType());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredAnonymousTypes, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, new FctFieldGetDeclaredAnonymousTypes());
        methods_.add( method_);
        _stds.getStandards().addEntry(aliasField, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasMethod, fields_, constructors_, methods_, aliasAnnotated, StdClassModifier.ABSTRACT, new DfMethod());
        params_ = new StringList(aliasObject_,aliasObject_);
        method_ = new StandardMethod(aliasInvoke, params_, aliasObject_, true, MethodModifier.FINAL,new StringList(params.getAliasMethod0Invoke0(),params.getAliasMethod0Invoke1()),new FctMethodInvoke(false));
        methods_.add( method_);
        params_ = new StringList(aliasObject_,aliasObject_);
        method_ = new StandardMethod(aliasInvokeDirect, params_, aliasObject_, true, MethodModifier.FINAL,new StringList(params.getAliasMethod0InvokeDirect0(),params.getAliasMethod0InvokeDirect1()),new FctMethodInvoke(true));
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsAbstract, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctMethodIsAbstract());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsNormal, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctMethodIsNormal());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsStatic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctMethodIsStatic());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsStaticCall, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctMethodIsStaticCall());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsInstanceMethod, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctMethodIsInstance());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsFinal, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctMethodIsFinal());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsVarargs, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctCallerIsVararg());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParameterTypes, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, new FctCallerGetParameterTypes());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetParameterNames, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, new FctCallerGetParameterNames());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDefaultValue, params_, aliasObject_, false, MethodModifier.FINAL, new FctMethodGetDefaultValue());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetName, params_, aliasString_, false, MethodModifier.FINAL, new FctMethodGetName());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetGenericReturnType, params_, aliasClassType, false, MethodModifier.FINAL, new FctAnnotatedGetGenericReturnType());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetReturnType, params_, aliasClassType, false, MethodModifier.FINAL, new FctAnnotatedGetReturnType());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredAnonymousTypes, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, new FctMethodGetDeclaredAnonymousTypes());
        methods_.add( method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLocalVarsNb, params_, _stds.getContent().getNbAlias().getAliasLong(), false, MethodModifier.FINAL,new StringList(params.getAliasMethod0GetDeclaredAnonymousLambdaLocalVarsNb0()),new FctMethodGetDeclaredAnonymousLambdaLocalVarsNb());
        methods_.add( method_);
        params_ = new StringList(aliasString_, _stds.getContent().getPrimTypes().getAliasPrimLong(),aliasObject_);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLocalVars, params_, aliasVoid_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod0GetDeclaredAnonymousLambdaLocalVars0(),params.getAliasMethod0GetDeclaredAnonymousLambdaLocalVars1(),params.getAliasMethod0GetDeclaredAnonymousLambdaLocalVars2()),new FctMethodGetDeclaredAnonymousLambdaLocalVars4());
        methods_.add( method_);
        params_ = new StringList(aliasString_, _stds.getContent().getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLocalVars, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod1GetDeclaredAnonymousLambdaLocalVars0(),params.getAliasMethod1GetDeclaredAnonymousLambdaLocalVars1()),new FctMethodGetDeclaredAnonymousLambdaLocalVars2());
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasObject_);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLocalVars, params_, aliasVoid_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod2GetDeclaredAnonymousLambdaLocalVars0(),params.getAliasMethod2GetDeclaredAnonymousLambdaLocalVars1()),new FctMethodGetDeclaredAnonymousLambdaLocalVars3());
        methods_.add( method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLocalVars, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod3GetDeclaredAnonymousLambdaLocalVars0()),new FctMethodGetDeclaredAnonymousLambdaLocalVars1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLocalVars, params_, StringExpUtil.getPrettyArrayType(aliasString_), false, MethodModifier.FINAL,new FctMethodGetDeclaredAnonymousLambdaLocalVars0());
        methods_.add( method_);
        params_ = new StringList(aliasString_, _stds.getContent().getPrimTypes().getAliasPrimLong(),aliasObject_);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLoopVars, params_, aliasVoid_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod0GetDeclaredAnonymousLambdaLoopVars0(),params.getAliasMethod0GetDeclaredAnonymousLambdaLoopVars1(),params.getAliasMethod0GetDeclaredAnonymousLambdaLoopVars2()),new FctMethodGetDeclaredAnonymousLambdaLoopVars4());
        methods_.add( method_);
        params_ = new StringList(aliasString_, _stds.getContent().getPrimTypes().getAliasPrimLong());
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLoopVars, params_, _stds.getContent().getNbAlias().getAliasLong(), false, MethodModifier.FINAL,new StringList(params.getAliasMethod1GetDeclaredAnonymousLambdaLoopVars0(),params.getAliasMethod1GetDeclaredAnonymousLambdaLoopVars1()),new FctMethodGetDeclaredAnonymousLambdaLoopVars2());
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasObject_);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLoopVars, params_, aliasVoid_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod2GetDeclaredAnonymousLambdaLoopVars0(),params.getAliasMethod2GetDeclaredAnonymousLambdaLoopVars1()),new FctMethodGetDeclaredAnonymousLambdaLoopVars3());
        methods_.add( method_);
        params_ = new StringList(aliasString_);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLoopVars, params_, _stds.getContent().getNbAlias().getAliasLong(), false, MethodModifier.FINAL,new StringList(params.getAliasMethod3GetDeclaredAnonymousLambdaLoopVars0()), new FctMethodGetDeclaredAnonymousLambdaLoopVars1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambdaLoopVars, params_, StringExpUtil.getPrettyArrayType(aliasString_), false, MethodModifier.FINAL, new FctMethodGetDeclaredAnonymousLambdaLoopVars0());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredLocalTypes, params_, StringExpUtil.getPrettyArrayType(aliasClassType), false, MethodModifier.FINAL, new FctMethodGetDeclaredLocalTypes());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAnnotationsSupp, params_, StringExpUtil.getPrettyArrayType(aliasAnnotationType), false, MethodModifier.FINAL,new FctAnnotatedGetAnnotationsSupp0());
        methods_.add( method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasGetAnnotationsSupp, params_,
                StringExpUtil.getPrettyArrayType(aliasAnnotationType), false, MethodModifier.FINAL,new StringList(params.getAliasAnnotated0GetAnnotationsSupp0()),new FctAnnotatedGetAnnotationsSupp1());
        methods_.add( method_);
        _stds.getStandards().addEntry(aliasMethod, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasInvokeTarget, fields_, constructors_, methods_, aliasError_, StdClassModifier.ABSTRACT);
        _stds.getStandards().addEntry(aliasInvokeTarget, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasClassNotFoundError, fields_, constructors_, methods_, aliasError_, StdClassModifier.ABSTRACT);
        _stds.getStandards().addEntry(aliasClassNotFoundError, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasAnnotationType, fields_, constructors_, methods_, aliasObject_, StdClassModifier.ABSTRACT);
        params_ = new StringList(aliasAnnotationType);
        method_ = new StandardMethod(aliasGetString, params_, aliasString_, false, MethodModifier.STATIC,new StringList(params.getAliasAnnotationType0GetString0()),new FctAnnotationToStr());
        methods_.add( method_);
        _stds.getStandards().addEntry(aliasAnnotationType, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasAnnotated, fields_, constructors_, methods_, aliasObject_, StdClassModifier.ABSTRACT);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAnnotations, params_, StringExpUtil.getPrettyArrayType(aliasAnnotationType), false, MethodModifier.FINAL,new FctAnnotatedGetAnnotations0());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetAnnotationsParameters, params_,
                StringExpUtil.getPrettyArrayType(StringExpUtil.getPrettyArrayType(aliasAnnotationType)), false,
                MethodModifier.FINAL,new FctAnnotatedGetAnnotationsParam0());
        methods_.add( method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasGetAnnotations, params_,
                StringExpUtil.getPrettyArrayType(aliasAnnotationType), false, MethodModifier.FINAL,new StringList(params.getAliasAnnotated0GetAnnotations0()),new FctAnnotatedGetAnnotations1());
        methods_.add( method_);
        params_ = new StringList(aliasClassType);
        method_ = new StandardMethod(aliasGetAnnotationsParameters, params_,
                StringExpUtil.getPrettyArrayType(StringExpUtil.getPrettyArrayType(aliasAnnotationType)),
                false, MethodModifier.FINAL,new StringList(params.getAliasAnnotated0GetAnnotationsParameters0()),new FctAnnotatedGetAnnotationsParam1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetFileName, params_, aliasString_, false, MethodModifier.FINAL,new FctAnnotatedGetFileName());
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasBoolean_,aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambda, params_, StringExpUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL,new StringList(params.getAliasAnnotated0GetDeclaredAnonymousLambda0(),params.getAliasAnnotated0GetDeclaredAnonymousLambda1(),params.getAliasAnnotated0GetDeclaredAnonymousLambda2(),params.getAliasAnnotated0GetDeclaredAnonymousLambda3()),new FctAnnotatedGetDeclaredAnonymousLambda1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredAnonymousLambda, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL,new FctAnnotatedGetDeclaredAnonymousLambda0());
        methods_.add( method_);
        params_ = new StringList(aliasString_,aliasBoolean_,aliasBoolean_, aliasClassType);
        method_ = new StandardMethod(aliasGetDeclaredSwitchMethods, params_, StringExpUtil.getPrettyArrayType(aliasMethod), true, MethodModifier.FINAL,new StringList(params.getAliasAnnotated0GetDeclaredSwitchMethods0(),params.getAliasAnnotated0GetDeclaredSwitchMethods1(),params.getAliasAnnotated0GetDeclaredSwitchMethods2(),params.getAliasAnnotated0GetDeclaredSwitchMethods3()),new FctAnnotatedGetDeclaredSwitchMethods1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaredSwitchMethods, params_, StringExpUtil.getPrettyArrayType(aliasMethod), false, MethodModifier.FINAL,new FctAnnotatedGetDeclaredSwitchMethods0());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetDeclaringClass, params_, aliasClassType, false, MethodModifier.FINAL,new FctAnnotatedGetDeclaring());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPackage, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctAnnotatedIsPackage());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPrivate, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctAnnotatedIsPrivate());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsProtected, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctAnnotatedIsProtected());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsPublic, params_, aliasPrimBoolean_, false, MethodModifier.FINAL, new FctAnnotatedIsPublic());
        methods_.add( method_);
        _stds.getStandards().addEntry(aliasAnnotated, stdcl_);
    }

    public String getAliasClassType() {
        return aliasClassType;
    }
    public void setAliasClassType(String _aliasClass) {
        aliasClassType = _aliasClass;
    }
    public String getAliasFct() {
        return aliasFct;
    }
    public void setAliasFct(String _aliasFct) {
        aliasFct = _aliasFct;
    }
    public String getAliasCall() {
        return aliasCall;
    }
    public void setAliasCall(String _aliasCall) {
        aliasCall = _aliasCall;
    }

    public String getAliasInstance() {
        return aliasInstance;
    }

    public void setAliasInstance(String _aliasInstance) {
        this.aliasInstance = _aliasInstance;
    }

    public String getAliasMetaInfo() {
        return aliasMetaInfo;
    }

    public void setAliasMetaInfo(String _aliasMetaInfo) {
        this.aliasMetaInfo = _aliasMetaInfo;
    }

    public String getAliasAnnotationType() {
        return aliasAnnotationType;
    }
    public void setAliasAnnotationType(String _aliasAnnotation) {
        aliasAnnotationType = _aliasAnnotation;
    }
    public String getAliasAnnotated() {
        return aliasAnnotated;
    }
    public void setAliasAnnotated(String _aliasAnnotated) {
        aliasAnnotated = _aliasAnnotated;
    }
    public String getAliasGetAnnotations() {
        return aliasGetAnnotations;
    }
    public void setAliasGetAnnotations(String _aliasGetAnnotations) {
        aliasGetAnnotations = _aliasGetAnnotations;
    }

    public String getAliasGetAnnotationsSupp() {
        return aliasGetAnnotationsSupp;
    }

    public void setAliasGetAnnotationsSupp(String _al) {
        this.aliasGetAnnotationsSupp = _al;
    }

    public String getAliasGetDefaultValue() {
        return aliasGetDefaultValue;
    }
    public void setAliasGetDefaultValue(String _aliasGetDefaultValue) {
        aliasGetDefaultValue = _aliasGetDefaultValue;
    }
    public String getAliasGetAnnotationsParameters() {
        return aliasGetAnnotationsParameters;
    }
    public void setAliasGetAnnotationsParameters(String _aliasGetAnnotationsParameters) {
        aliasGetAnnotationsParameters = _aliasGetAnnotationsParameters;
    }
    public String getAliasGetClass() {
        return aliasGetClass;
    }
    public void setAliasGetClass(String _aliasGetClass) {
        aliasGetClass = _aliasGetClass;
    }
    public String getAliasGetEnclosingType() {
        return aliasGetEnclosingType;
    }
    public void setAliasGetEnclosingType(String _aliasGetEnclosingType) {
        aliasGetEnclosingType = _aliasGetEnclosingType;
    }
    public String getAliasGetDeclaredClasses() {
        return aliasGetDeclaredClasses;
    }
    public void setAliasGetDeclaredClasses(String _aliasGetDeclaredClasses) {
        aliasGetDeclaredClasses = _aliasGetDeclaredClasses;
    }

    public String getAliasGetDeclaredExplicits() {
        return aliasGetDeclaredExplicits;
    }

    public void setAliasGetDeclaredExplicits(String _aliasGetDeclaredExplicits) {
        this.aliasGetDeclaredExplicits = _aliasGetDeclaredExplicits;
    }

    public String getAliasGetDeclaredImplicits() {
        return aliasGetDeclaredImplicits;
    }

    public void setAliasGetDeclaredImplicits(String _aliasGetDeclaredExplicits) {
        this.aliasGetDeclaredImplicits = _aliasGetDeclaredExplicits;
    }

    public String getAliasGetDeclaredTrueOperators() {
        return aliasGetDeclaredTrueOperators;
    }

    public void setAliasGetDeclaredTrueOperators(String _aliasGetDeclaredTrueOperators) {
        this.aliasGetDeclaredTrueOperators = _aliasGetDeclaredTrueOperators;
    }

    public String getAliasGetDeclaredFalseOperators() {
        return aliasGetDeclaredFalseOperators;
    }

    public void setAliasGetDeclaredFalseOperators(String _aliasGetDeclaredFalseOperators) {
        this.aliasGetDeclaredFalseOperators = _aliasGetDeclaredFalseOperators;
    }

    public String getAliasGetDeclaredMethods() {
        return aliasGetDeclaredMethods;
    }
    public void setAliasGetDeclaredMethods(String _aliasGetDeclaredMethods) {
        aliasGetDeclaredMethods = _aliasGetDeclaredMethods;
    }

    public String getAliasGetDeclaredStaticMethods() {
        return aliasGetDeclaredStaticMethods;
    }

    public void setAliasGetDeclaredStaticMethods(String _aliasGetDeclaredStaticMethods) {
        aliasGetDeclaredStaticMethods = _aliasGetDeclaredStaticMethods;
    }

    public String getAliasGetDeclaredConstructors() {
        return aliasGetDeclaredConstructors;
    }
    public void setAliasGetDeclaredConstructors(String _aliasGetDeclaredConstructors) {
        aliasGetDeclaredConstructors = _aliasGetDeclaredConstructors;
    }
    public String getAliasGetDeclaredFields() {
        return aliasGetDeclaredFields;
    }
    public void setAliasGetDeclaredFields(String _aliasGetDeclaredFields) {
        aliasGetDeclaredFields = _aliasGetDeclaredFields;
    }

    public String getAliasGetDeclaredAnonymousTypes() {
        return aliasGetDeclaredAnonymousTypes;
    }

    public void setAliasGetDeclaredAnonymousTypes(String _aliasGetDeclaredAnonymousTypes) {
        this.aliasGetDeclaredAnonymousTypes = _aliasGetDeclaredAnonymousTypes;
    }

    public String getAliasGetDeclaredAnonymousLambda() {
        return aliasGetDeclaredAnonymousLambda;
    }

    public void setAliasGetDeclaredAnonymousLambda(String _aliasGetDeclaredAnonymousLambda) {
        this.aliasGetDeclaredAnonymousLambda = _aliasGetDeclaredAnonymousLambda;
    }

    public String getAliasGetDeclaredAnonymousLambdaLocalVars() {
        return aliasGetDeclaredAnonymousLambdaLocalVars;
    }

    public void setAliasGetDeclaredAnonymousLambdaLocalVars(String _aliasGetDeclaredAnonymousLambdaLocalVars) {
        this.aliasGetDeclaredAnonymousLambdaLocalVars = _aliasGetDeclaredAnonymousLambdaLocalVars;
    }

    public String getAliasGetDeclaredAnonymousLambdaLocalVarsNb() {
        return aliasGetDeclaredAnonymousLambdaLocalVarsNb;
    }

    public void setAliasGetDeclaredAnonymousLambdaLocalVarsNb(String _aliasGetDeclaredAnonymousLambdaLocalVarsNb) {
        this.aliasGetDeclaredAnonymousLambdaLocalVarsNb = _aliasGetDeclaredAnonymousLambdaLocalVarsNb;
    }

    public String getAliasGetDeclaredAnonymousLambdaLoopVars() {
        return aliasGetDeclaredAnonymousLambdaLoopVars;
    }

    public void setAliasGetDeclaredAnonymousLambdaLoopVars(String _aliasGetDeclaredAnonymousLambdaLoopVars) {
        this.aliasGetDeclaredAnonymousLambdaLoopVars = _aliasGetDeclaredAnonymousLambdaLoopVars;
    }

    public String getAliasGetDeclaredBlocks() {
        return aliasGetDeclaredBlocks;
    }

    public void setAliasGetDeclaredBlocks(String _aliasGetDeclaredBlocks) {
        this.aliasGetDeclaredBlocks = _aliasGetDeclaredBlocks;
    }

    public String getAliasGetDeclaredSwitchMethods() {
        return aliasGetDeclaredSwitchMethods;
    }

    public void setAliasGetDeclaredSwitchMethods(String _aliasGetDeclaredSwitchMethods) {
        this.aliasGetDeclaredSwitchMethods = _aliasGetDeclaredSwitchMethods;
    }

    public String getAliasGetDeclaredLocalTypes() {
        return aliasGetDeclaredLocalTypes;
    }

    public void setAliasGetDeclaredLocalTypes(String _aliasGetDeclaredLocalTypes) {
        this.aliasGetDeclaredLocalTypes = _aliasGetDeclaredLocalTypes;
    }

    public String getAliasGetSuperClass() {
        return aliasGetSuperClass;
    }
    public void setAliasGetSuperClass(String _aliasGetSuperClass) {
        aliasGetSuperClass = _aliasGetSuperClass;
    }
    public String getAliasGetGenericSuperClass() {
        return aliasGetGenericSuperClass;
    }
    public void setAliasGetGenericSuperClass(String _aliasGetGenericSuperClass) {
        aliasGetGenericSuperClass = _aliasGetGenericSuperClass;
    }
    public String getAliasGetInterfaces() {
        return aliasGetInterfaces;
    }
    public void setAliasGetInterfaces(String _aliasGetInterfaces) {
        aliasGetInterfaces = _aliasGetInterfaces;
    }
    public String getAliasGetGenericInterfaces() {
        return aliasGetGenericInterfaces;
    }
    public void setAliasGetGenericInterfaces(String _aliasGetGenericInterfaces) {
        aliasGetGenericInterfaces = _aliasGetGenericInterfaces;
    }
    public String getAliasGetLowerBounds() {
        return aliasGetLowerBounds;
    }
    public void setAliasGetLowerBounds(String _aliasGetLowerBounds) {
        aliasGetLowerBounds = _aliasGetLowerBounds;
    }
    public String getAliasGetUpperBounds() {
        return aliasGetUpperBounds;
    }
    public void setAliasGetUpperBounds(String _aliasGetUpperBounds) {
        aliasGetUpperBounds = _aliasGetUpperBounds;
    }
    public String getAliasGetComponentType() {
        return aliasGetComponentType;
    }
    public void setAliasGetComponentType(String _aliasGetComponentType) {
        aliasGetComponentType = _aliasGetComponentType;
    }
    public String getAliasMakeArray() {
        return aliasMakeArray;
    }
    public void setAliasMakeArray(String _aliasMakeArray) {
        aliasMakeArray = _aliasMakeArray;
    }
    public String getAliasGetParameterTypes() {
        return aliasGetParameterTypes;
    }
    public void setAliasGetParameterTypes(String _aliasGetParameterTypes) {
        aliasGetParameterTypes = _aliasGetParameterTypes;
    }
    public String getAliasGetTypeParameters() {
        return aliasGetTypeParameters;
    }
    public void setAliasGetTypeParameters(String _aliasGetTypeParameters) {
        aliasGetTypeParameters = _aliasGetTypeParameters;
    }
    public String getAliasGetParameterNames() {
        return aliasGetParameterNames;
    }
    public void setAliasGetParameterNames(String _aliasGetParameterNames) {
        aliasGetParameterNames = _aliasGetParameterNames;
    }
    public String getAliasGetGenericReturnType() {
        return aliasGetGenericReturnType;
    }
    public void setAliasGetGenericReturnType(String _aliasGetGenericReturnType) {
        aliasGetGenericReturnType = _aliasGetGenericReturnType;
    }
    public String getAliasGetReturnType() {
        return aliasGetReturnType;
    }
    public void setAliasGetReturnType(String _aliasGetReturnType) {
        aliasGetReturnType = _aliasGetReturnType;
    }
    public String getAliasGetActualTypeArguments() {
        return aliasGetActualTypeArguments;
    }
    public void setAliasGetActualTypeArguments(String _aliasGetActualTypeArguments) {
        aliasGetActualTypeArguments = _aliasGetActualTypeArguments;
    }
    public String getAliasIsFinal() {
        return aliasIsFinal;
    }
    public void setAliasIsFinal(String _aliasIsFinal) {
        aliasIsFinal = _aliasIsFinal;
    }
    public String getAliasIsStatic() {
        return aliasIsStatic;
    }
    public void setAliasIsStatic(String _aliasIsStatic) {
        aliasIsStatic = _aliasIsStatic;
    }
    public String getAliasIsStaticCall() {
        return aliasIsStaticCall;
    }
    public void setAliasIsStaticCall(String _aliasIsStaticCall) {
        aliasIsStaticCall = _aliasIsStaticCall;
    }

    public String getAliasIsInstanceMethod() {
        return aliasIsInstanceMethod;
    }

    public void setAliasIsInstanceMethod(String _aliasIsInstanceMethod) {
        aliasIsInstanceMethod = _aliasIsInstanceMethod;
    }

    public String getAliasIsTypeVariable() {
        return aliasIsTypeVariable;
    }

    public void setAliasIsTypeVariable(String _aliasIsTypeVariable) {
        aliasIsTypeVariable = _aliasIsTypeVariable;
    }

    public String getAliasIsVariable() {
        return aliasIsVariable;
    }

    public void setAliasIsVariable(String _aliasIsVariable) {
        aliasIsVariable = _aliasIsVariable;
    }

    public String getAliasIsVarargs() {
        return aliasIsVarargs;
    }
    public void setAliasIsVarargs(String _aliasIsVarargs) {
        aliasIsVarargs = _aliasIsVarargs;
    }
    public String getAliasIsNormal() {
        return aliasIsNormal;
    }
    public void setAliasIsNormal(String _aliasIsNormal) {
        aliasIsNormal = _aliasIsNormal;
    }
    public String getAliasIsPublic() {
        return aliasIsPublic;
    }
    public void setAliasIsPublic(String _aliasIsPublic) {
        aliasIsPublic = _aliasIsPublic;
    }
    public String getAliasIsProtected() {
        return aliasIsProtected;
    }
    public void setAliasIsProtected(String _aliasIsProtected) {
        aliasIsProtected = _aliasIsProtected;
    }
    public String getAliasIsPackage() {
        return aliasIsPackage;
    }
    public void setAliasIsPackage(String _aliasIsPackage) {
        aliasIsPackage = _aliasIsPackage;
    }
    public String getAliasIsPrivate() {
        return aliasIsPrivate;
    }
    public void setAliasIsPrivate(String _aliasIsPrivate) {
        aliasIsPrivate = _aliasIsPrivate;
    }
    public String getAliasIsSpecialMuClass() {
        return aliasIsSpecialMuClass;
    }
    public void setAliasIsSpecialMuClass(String _aliasIsSpecialMuClass) {
        aliasIsSpecialMuClass = _aliasIsSpecialMuClass;
    }
    public String getAliasIsSpecialClass() {
        return aliasIsSpecialClass;
    }
    public void setAliasIsSpecialClass(String _aliasIsSpecialClass) {
        aliasIsSpecialClass = _aliasIsSpecialClass;
    }

    public String getAliasIsClass() {
        return aliasIsClass;
    }
    public void setAliasIsClass(String _aliasIsClass) {
        aliasIsClass = _aliasIsClass;
    }

    public String getAliasIsRefType() {
        return aliasIsRefType;
    }

    public void setAliasIsRefType(String _aliasIsRefType) {
        aliasIsRefType = _aliasIsRefType;
    }

    public String getAliasIsWildCard() {
        return aliasIsWildCard;
    }
    public void setAliasIsWildCard(String _aliasIsWildCard) {
        aliasIsWildCard = _aliasIsWildCard;
    }
    public String getAliasIsInterface() {
        return aliasIsInterface;
    }
    public void setAliasIsInterface(String _aliasIsInterface) {
        aliasIsInterface = _aliasIsInterface;
    }
    public String getAliasIsEnum() {
        return aliasIsEnum;
    }
    public void setAliasIsEnum(String _aliasIsEnum) {
        aliasIsEnum = _aliasIsEnum;
    }
    public String getAliasIsPrimitive() {
        return aliasIsPrimitive;
    }
    public void setAliasIsPrimitive(String _aliasIsPrimitive) {
        aliasIsPrimitive = _aliasIsPrimitive;
    }
    public String getAliasIsArray() {
        return aliasIsArray;
    }
    public void setAliasIsArray(String _aliasIsArray) {
        aliasIsArray = _aliasIsArray;
    }
    public String getAliasIsAnnotation() {
        return aliasIsAnnotation;
    }
    public void setAliasIsAnnotation(String _aliasIsAnnotation) {
        aliasIsAnnotation = _aliasIsAnnotation;
    }

    public String getAliasMakeRef() {
        return aliasMakeRef;
    }

    public void setAliasMakeRef(String _aliasMakeRef) {
        aliasMakeRef = _aliasMakeRef;
    }

    public String getAliasMakeWildCard() {
        return aliasMakeWildCard;
    }
    public void setAliasMakeWildCard(String _aliasMakeWildCard) {
        aliasMakeWildCard = _aliasMakeWildCard;
    }
    public String getAliasIsInstance() {
        return aliasIsInstance;
    }
    public void setAliasIsInstance(String _aliasIsInstance) {
        aliasIsInstance = _aliasIsInstance;
    }
    public String getAliasIsAssignableFrom() {
        return aliasIsAssignableFrom;
    }
    public void setAliasIsAssignableFrom(String _aliasIsAssignableFrom) {
        aliasIsAssignableFrom = _aliasIsAssignableFrom;
    }
    public String getAliasInit() {
        return aliasInit;
    }
    public void setAliasInit(String _aliasInit) {
        aliasInit = _aliasInit;
    }

    public String getAliasTryWrap() {
        return aliasTryWrap;
    }

    public void setAliasTryWrap(String _aliasTryWrap) {
        this.aliasTryWrap = _aliasTryWrap;
    }

    public String getAliasDefaultInstance() {
        return aliasDefaultInstance;
    }
    public void setAliasDefaultInstance(String _aliasDefaultInstance) {
        aliasDefaultInstance = _aliasDefaultInstance;
    }
    public String getAliasEnumValueOf() {
        return aliasEnumValueOf;
    }
    public void setAliasEnumValueOf(String _aliasEnumValueOf) {
        aliasEnumValueOf = _aliasEnumValueOf;
    }
    public String getAliasGetEnumConstants() {
        return aliasGetEnumConstants;
    }
    public void setAliasGetEnumConstants(String _aliasGetEnumConstants) {
        aliasGetEnumConstants = _aliasGetEnumConstants;
    }
    public String getAliasGetGenericBounds() {
        return aliasGetGenericBounds;
    }
    public void setAliasGetGenericBounds(String _aliasGetGenericBounds) {
        aliasGetGenericBounds = _aliasGetGenericBounds;
    }
    public String getAliasGetBounds() {
        return aliasGetBounds;
    }
    public void setAliasGetBounds(String _aliasGetBounds) {
        aliasGetBounds = _aliasGetBounds;
    }
    public String getAliasArrayNewInstance() {
        return aliasArrayNewInstance;
    }
    public void setAliasArrayNewInstance(String _aliasArrayNewInstance) {
        aliasArrayNewInstance = _aliasArrayNewInstance;
    }
    public String getAliasArrayGet() {
        return aliasArrayGet;
    }
    public void setAliasArrayGet(String _aliasArrayGet) {
        aliasArrayGet = _aliasArrayGet;
    }
    public String getAliasArraySet() {
        return aliasArraySet;
    }
    public void setAliasArraySet(String _aliasArraySet) {
        aliasArraySet = _aliasArraySet;
    }
    public String getAliasArrayGetLength() {
        return aliasArrayGetLength;
    }
    public void setAliasArrayGetLength(String _aliasArrayGetLength) {
        aliasArrayGetLength = _aliasArrayGetLength;
    }
    public String getAliasMakeGeneric() {
        return aliasMakeGeneric;
    }
    public void setAliasMakeGeneric(String _aliasMakeGeneric) {
        aliasMakeGeneric = _aliasMakeGeneric;
    }
    public String getAliasGetAllClasses() {
        return aliasGetAllClasses;
    }
    public void setAliasGetAllClasses(String _aliasGetAllClasses) {
        aliasGetAllClasses = _aliasGetAllClasses;
    }
    public String getAliasGetOperators() {
        return aliasGetOperators;
    }
    public void setAliasGetOperators(String _aliasGetOperators) {
        aliasGetOperators = _aliasGetOperators;
    }
    public String getAliasConstructor() {
        return aliasConstructor;
    }
    public void setAliasConstructor(String _aliasConstructor) {
        aliasConstructor = _aliasConstructor;
    }
    public String getAliasField() {
        return aliasField;
    }
    public void setAliasField(String _aliasField) {
        aliasField = _aliasField;
    }
    public String getAliasMethod() {
        return aliasMethod;
    }
    public void setAliasMethod(String _aliasMethod) {
        aliasMethod = _aliasMethod;
    }
    public String getAliasInvoke() {
        return aliasInvoke;
    }
    public void setAliasInvoke(String _aliasInvoke) {
        aliasInvoke = _aliasInvoke;
    }

    public String getAliasInvokeDirect() {
        return aliasInvokeDirect;
    }

    public void setAliasInvokeDirect(String _aliasInvokeDirect) {
        aliasInvokeDirect = _aliasInvokeDirect;
    }

    public String getAliasNewInstance() {
        return aliasNewInstance;
    }
    public void setAliasNewInstance(String _aliasNewInstance) {
        aliasNewInstance = _aliasNewInstance;
    }
    public String getAliasIsAbstract() {
        return aliasIsAbstract;
    }
    public void setAliasIsAbstract(String _aliasIsAbstract) {
        aliasIsAbstract = _aliasIsAbstract;
    }

    public String getAliasGetFileName() {
        return aliasGetFileName;
    }

    public void setAliasGetFileName(String _aliasGetFileName) {
        aliasGetFileName = _aliasGetFileName;
    }

    public String getAliasGetName() {
        return aliasGetName;
    }
    public void setAliasGetName(String _aliasGetName) {
        aliasGetName = _aliasGetName;
    }
    public String getAliasGetPrettyName() {
        return aliasGetPrettyName;
    }
    public void setAliasGetPrettyName(String _aliasGetPrettyName) {
        aliasGetPrettyName = _aliasGetPrettyName;
    }
    public String getAliasGetPrettySingleName() {
        return aliasGetPrettySingleName;
    }
    public void setAliasGetPrettySingleName(String _aliasGetPrettyName) {
        aliasGetPrettySingleName = _aliasGetPrettyName;
    }
    public String getAliasGetField() {
        return aliasGetField;
    }
    public void setAliasGetField(String _aliasGetField) {
        aliasGetField = _aliasGetField;
    }
    public String getAliasSetField() {
        return aliasSetField;
    }
    public void setAliasSetField(String _aliasSetField) {
        aliasSetField = _aliasSetField;
    }
    public String getAliasGetGenericType() {
        return aliasGetGenericType;
    }
    public void setAliasGetGenericType(String _aliasGetGenericType) {
        aliasGetGenericType = _aliasGetGenericType;
    }
    public String getAliasGetType() {
        return aliasGetType;
    }
    public void setAliasGetType(String _aliasGetType) {
        aliasGetType = _aliasGetType;
    }
    public String getAliasForName() {
        return aliasForName;
    }
    public void setAliasForName(String _aliasForName) {
        aliasForName = _aliasForName;
    }
    public String getAliasGetDeclaringClass() {
        return aliasGetDeclaringClass;
    }
    public void setAliasGetDeclaringClass(String _aliasGetDeclaringClass) {
        aliasGetDeclaringClass = _aliasGetDeclaringClass;
    }
    public String getAliasInvokeTarget() {
        return aliasInvokeTarget;
    }
    public void setAliasInvokeTarget(String _aliasInvokeTarget) {
        aliasInvokeTarget = _aliasInvokeTarget;
    }
    public String getAliasClassNotFoundError() {
        return aliasClassNotFoundError;
    }
    public void setAliasClassNotFoundError(String _aliasClassNotFoundError) {
        aliasClassNotFoundError = _aliasClassNotFoundError;
    }
    public String getAliasGetVariableOwner() {
        return aliasGetVariableOwner;
    }
    public void setAliasGetVariableOwner(String _aliasGetVariableOwner) {
        aliasGetVariableOwner = _aliasGetVariableOwner;
    }
    public String getAliasGetGenericVariableOwner() {
        return aliasGetGenericVariableOwner;
    }
    public void setAliasGetGenericVariableOwner(String _aliasGetGenericVariableOwner) {
        aliasGetGenericVariableOwner = _aliasGetGenericVariableOwner;
    }
    public String getAliasGetString() {
        return aliasGetString;
    }
    public void setAliasGetString(String _aliasGetString) {
        aliasGetString = _aliasGetString;
    }

    public AliasParamReflection getParams() {
        return params;
    }
}
