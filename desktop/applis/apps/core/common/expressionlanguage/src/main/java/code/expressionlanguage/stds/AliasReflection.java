package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fcts.*;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class AliasReflection {
    private static final String ANNOTATED="__________1905";
    private static final String ANNOTATION_TYPE="__________1906";
    private static final String CLASS_TYPE="__________1907";
    private static final String CONSTRUCTOR="__________1908";
    private static final String FCT="822";
    private static final String FIELD="__________1909";
    private static final String METHOD="__________1910";
    private static final String CLASS_NOT_FOUND_ERROR="__________1911";
    private static final String INVOKE_TARGET="__________1912";
    private static final String GET_FILE_NAME="__________1904";
    private static final String GET_DECLARING_CLASS="__________1914";
    private static final String GET_DECLARED_ANONYMOUS_LAMBDA="__________1915";
    private static final String GET_DECLARED_SWITCH_METHODS="__________1916";
    private static final String GET_ANNOTATIONS="__________1917";
    private static final String GET_ANNOTATIONS_PARAMETERS="__________1918";
    private static final String GET_STRING="__________1919";
    private static final String DEFAULT_INSTANCE="__________1920";
    private static final String ENUM_VALUE_OF="__________1921";
    private static final String FOR_NAME="__________1922";
    private static final String ARRAY_GET="__________1923";
    private static final String GET_ACTUAL_TYPE_ARGUMENTS="__________1924";
    private static final String GET_ALL_CLASSES="__________1925";
    private static final String GET_BOUNDS="__________1926";
    private static final String GET_CLASS="__________1927";
    private static final String GET_COMPONENT_TYPE="__________1928";
    private static final String GET_DECLARED_CLASSES="__________1929";
    private static final String GET_DECLARED_CONSTRUCTORS="__________1930";
    private static final String GET_DECLARED_FIELDS="__________1931";
    private static final String GET_DECLARED_ANONYMOUS_TYPES="__________1932";
    private static final String GET_DECLARED_LOCAL_TYPES="__________1933";
    private static final String GET_DECLARED_BLOCKS="__________1934";
    private static final String GET_DECLARED_EXPLICITS="__________1935";
    private static final String GET_DECLARED_IMPLICITS="__________1936";
    private static final String GET_DECLARED_TRUE_OPERATORS="__________1937";
    private static final String GET_DECLARED_FALSE_OPERATORS="__________1938";
    private static final String GET_DECLARED_METHODS="__________1939";
    private static final String GET_DECLARED_STATIC_METHODS="__________1940";
    private static final String GET_ENCLOSING_TYPE="__________1941";
    private static final String GET_ENUM_CONSTANTS="__________1942";
    private static final String GET_GENERIC_BOUNDS="__________1943";
    private static final String GET_GENERIC_INTERFACES="__________1944";
    private static final String GET_GENERIC_SUPER_CLASS="__________1945";
    private static final String GET_GENERIC_VARIABLE_OWNER="__________1946";
    private static final String GET_INTERFACES="__________1947";
    private static final String ARRAY_GET_LENGTH="__________1948";
    private static final String GET_LOWER_BOUNDS="__________1949";
    private static final String GET_NAME="__________1950";
    private static final String GET_OPERATORS="__________1951";
    private static final String GET_PRETTY_NAME="__________1952";
    private static final String GET_PRETTY_SINGLE_NAME="__________1953";
    private static final String GET_SUPER_CLASS="__________1954";
    private static final String GET_TYPE_PARAMETERS="__________1955";
    private static final String GET_UPPER_BOUNDS="__________1956";
    private static final String GET_VARIABLE_OWNER="__________1957";
    private static final String INIT="__________1958";
    private static final String TRY_WRAP="__________1959";
    private static final String IS_ABSTRACT="__________1960";
    private static final String IS_ANNOTATION="__________1961";
    private static final String IS_ARRAY="__________1962";
    private static final String IS_ASSIGNABLE_FROM="__________1963";
    private static final String IS_SPE_CLASS="__________1964";
    private static final String IS_SPE_MU_CLASS="__________1965";
    private static final String IS_CLASS="__________1966";
    private static final String IS_ENUM="__________1967";
    private static final String IS_FINAL="__________1968";
    private static final String IS_TYPE_VARIABLE="__________1969";
    private static final String IS_VARIABLE="__________1970";
    private static final String IS_INSTANCE="__________1971";
    private static final String IS_INTERFACE="__________1972";
    private static final String IS_PACKAGE="__________1973";
    private static final String IS_PRIMITIVE="__________1974";
    private static final String IS_PRIVATE="__________1975";
    private static final String IS_PROTECTED="__________1976";
    private static final String IS_PUBLIC="__________1977";
    private static final String IS_STATIC="__________1978";
    private static final String IS_REF_TYPE="__________1979";
    private static final String IS_WILD_CARD="__________1980";
    private static final String MAKE_ARRAY="__________1981";
    private static final String MAKE_GENERIC="__________1982";
    private static final String MAKE_REF_TYPE="__________1983";
    private static final String MAKE_WILD_CARD="__________1984";
    private static final String ARRAY_NEW_INSTANCE="__________1985";
    private static final String ARRAY_SET="__________1986";
    private static final String GET_GENERIC_RETURN_TYPE="__________1987";
    private static final String GET_PARAMETER_NAMES="__________1988";
    private static final String GET_PARAMETER_TYPES="__________1989";
    private static final String GET_RETURN_TYPE="__________1990";
    private static final String IS_VARARGS="__________1991";
    private static final String NEW_INSTANCE="__________1992";
    private static final String NEW_INSTANCE_REF="__________1992_";
    private static final String NEW_INSTANCE_REF_AFTER="__________1992__";
    private static final String CALL="823";
    private static final String CALL_REF="823_";
    private static final String CALL_REF_AFTER="823__";
    private static final String META_INFO="__________1993";
    private static final String INSTANCE="__________1994";
    private static final String GET_FIELD="__________1995";
    private static final String GET_GENERIC_TYPE="__________1996";
    private static final String GET_TYPE="__________1997";
    private static final String SET_FIELD="__________1998";
    private static final String GET_ANNOTATIONS_SUPP="__________1999";
    private static final String GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS="__________2000";
    private static final String GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_NB="__________2001";
    private static final String GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS="__________2002";
    private static final String GET_DEFAULT_VALUE="__________2003";
    private static final String INVOKE="__________2004";
    private static final String INVOKE_REF="__________2004_";
    private static final String INVOKE_REF_AFTER="__________2004__";
    private static final String INVOKE_DIRECT="__________2005";
    private static final String INVOKE_DIRECT_REF="__________2005_";
    private static final String INVOKE_DIRECT_REF_AFTER="__________2005__";
    private static final String IS_NORMAL="__________2006";
    private static final String IS_STATIC_CALL="__________2007";
    private static final String IS_INSTANCE_METHOD="__________2008";
    private String aliasAnnotationType;
    private String aliasAnnotated;
    private String aliasGetDefaultValue;
    private String aliasGetAnnotations;
    private String aliasGetAnnotationsSupp;
    private String aliasGetAnnotationsParameters;
    private String aliasFct;
    private String aliasCall;
    private String aliasCallRef;
    private String aliasCallRefAfter;
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
    private String aliasInvokeRef;
    private String aliasInvokeDirectRef;
    private String aliasInvokeRefAfter;
    private String aliasInvokeDirectRefAfter;
    private String aliasNewInstance;
    private String aliasNewInstanceRef;
    private String aliasNewInstanceRefAfter;
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
    private StandardType fctType;
    private StandardMethod fctTypeInstance;
    private StandardMethod fctTypeMeta;
    private StandardMethod fctTypeCall;
    private StandardMethod fctTypeCallRef;
    private StandardMethod fctTypeCallRefAfter;
    private final AliasParamReflection params = new AliasParamReflection();
    private StandardClass annotType;

    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setAliasGetType(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_TYPE)));
        setAliasCall(LgNamesContent.get(_util,_cust,_mapping.getVal(CALL)));
        setAliasCallRef(LgNamesContent.get(_util,_cust,_mapping.getVal(CALL_REF)));
        setAliasCallRefAfter(LgNamesContent.get(_util,_cust,_mapping.getVal(CALL_REF_AFTER)));
        setAliasMetaInfo(LgNamesContent.get(_util,_cust,_mapping.getVal(META_INFO)));
        setAliasInstance(LgNamesContent.get(_util,_cust,_mapping.getVal(INSTANCE)));
        setAliasFct(LgNamesContent.get(_util,_cust,_mapping.getVal(FCT)));
        setAliasGetString(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_STRING)));
        setAliasGetAnnotationsParameters(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_ANNOTATIONS_PARAMETERS)));
        setAliasInvokeTarget(LgNamesContent.get(_util,_cust,_mapping.getVal(INVOKE_TARGET)));
        setAliasGetAnnotations(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_ANNOTATIONS)));
        setAliasGetAnnotationsSupp(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_ANNOTATIONS_SUPP)));
        setAliasGetVariableOwner(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_VARIABLE_OWNER)));
        setAliasClassNotFoundError(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_NOT_FOUND_ERROR)));
        setAliasClassType(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE)));
        setAliasAnnotationType(LgNamesContent.get(_util,_cust,_mapping.getVal(ANNOTATION_TYPE)));
        setAliasGetGenericVariableOwner(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_GENERIC_VARIABLE_OWNER)));
        setAliasAnnotated(LgNamesContent.get(_util,_cust,_mapping.getVal(ANNOTATED)));
        setAliasGetDefaultValue(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DEFAULT_VALUE)));
        setAliasMakeGeneric(LgNamesContent.get(_util,_cust,_mapping.getVal(MAKE_GENERIC)));
        setAliasGetAllClasses(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_ALL_CLASSES)));
        setAliasGetOperators(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_OPERATORS)));
        setAliasGetDeclaredExplicits(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DECLARED_EXPLICITS)));
        setAliasGetDeclaredImplicits(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DECLARED_IMPLICITS)));
        setAliasGetDeclaredTrueOperators(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DECLARED_TRUE_OPERATORS)));
        setAliasGetDeclaredFalseOperators(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DECLARED_FALSE_OPERATORS)));
        setAliasGetDeclaredMethods(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DECLARED_METHODS)));
        setAliasGetDeclaredStaticMethods(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DECLARED_STATIC_METHODS)));
        setAliasGetDeclaredConstructors(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DECLARED_CONSTRUCTORS)));
        setAliasGetDeclaredFields(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DECLARED_FIELDS)));
        setAliasGetDeclaredAnonymousTypes(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DECLARED_ANONYMOUS_TYPES)));
        setAliasGetDeclaredAnonymousLambda(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DECLARED_ANONYMOUS_LAMBDA)));
        setAliasGetDeclaredAnonymousLambdaLocalVars(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS)));
        setAliasGetDeclaredAnonymousLambdaLocalVarsNb(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_NB)));
        setAliasGetDeclaredAnonymousLambdaLoopVars(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS)));
        setAliasGetDeclaredBlocks(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DECLARED_BLOCKS)));
        setAliasGetDeclaredSwitchMethods(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DECLARED_SWITCH_METHODS)));
        setAliasGetDeclaredLocalTypes(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DECLARED_LOCAL_TYPES)));
        setAliasField(LgNamesContent.get(_util,_cust,_mapping.getVal(FIELD)));
        setAliasIsNormal(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_NORMAL)));
        setAliasIsPublic(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_PUBLIC)));
        setAliasIsArray(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_ARRAY)));
        setAliasArrayGet(LgNamesContent.get(_util,_cust,_mapping.getVal(ARRAY_GET)));
        setAliasMethod(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD)));
        setAliasGetField(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_FIELD)));
        setAliasInvoke(LgNamesContent.get(_util,_cust,_mapping.getVal(INVOKE)));
        setAliasInvokeRef(LgNamesContent.get(_util,_cust,_mapping.getVal(INVOKE_REF)));
        setAliasInvokeRefAfter(LgNamesContent.get(_util,_cust,_mapping.getVal(INVOKE_REF_AFTER)));
        setAliasIsEnum(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_ENUM)));
        setAliasInit(LgNamesContent.get(_util,_cust,_mapping.getVal(INIT)));
        setAliasTryWrap(LgNamesContent.get(_util,_cust,_mapping.getVal(TRY_WRAP)));
        setAliasForName(LgNamesContent.get(_util,_cust,_mapping.getVal(FOR_NAME)));
        setAliasIsStatic(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_STATIC)));
        setAliasIsStaticCall(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_STATIC_CALL)));
        setAliasIsInstanceMethod(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_INSTANCE_METHOD)));
        setAliasGetName(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_NAME)));
        setAliasIsClass(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_CLASS)));
        setAliasIsSpecialClass(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_SPE_CLASS)));
        setAliasIsSpecialMuClass(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_SPE_MU_CLASS)));
        setAliasSetField(LgNamesContent.get(_util,_cust,_mapping.getVal(SET_FIELD)));
        setAliasGetClass(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_CLASS)));
        setAliasIsFinal(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_FINAL)));
        setAliasArraySet(LgNamesContent.get(_util,_cust,_mapping.getVal(ARRAY_SET)));
        setAliasGetBounds(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_BOUNDS)));
        setAliasGetDeclaringClass(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DECLARING_CLASS)));
        setAliasEnumValueOf(LgNamesContent.get(_util,_cust,_mapping.getVal(ENUM_VALUE_OF)));
        setAliasArrayNewInstance(LgNamesContent.get(_util,_cust,_mapping.getVal(ARRAY_NEW_INSTANCE)));
        setAliasGetEnumConstants(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_ENUM_CONSTANTS)));
        setAliasArrayGetLength(LgNamesContent.get(_util,_cust,_mapping.getVal(ARRAY_GET_LENGTH)));
        setAliasGetGenericBounds(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_GENERIC_BOUNDS)));
        setAliasDefaultInstance(LgNamesContent.get(_util,_cust,_mapping.getVal(DEFAULT_INSTANCE)));
        setAliasGetParameterNames(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_PARAMETER_NAMES)));
        setAliasGetPrettyName(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_PRETTY_NAME)));
        setAliasGetPrettySingleName(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_PRETTY_SINGLE_NAME)));
        setAliasGetUpperBounds(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_UPPER_BOUNDS)));
        setAliasGetParameterTypes(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_PARAMETER_TYPES)));
        setAliasGetGenericReturnType(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_GENERIC_RETURN_TYPE)));
        setAliasInvokeDirect(LgNamesContent.get(_util,_cust,_mapping.getVal(INVOKE_DIRECT)));
        setAliasInvokeDirectRef(LgNamesContent.get(_util,_cust,_mapping.getVal(INVOKE_DIRECT_REF)));
        setAliasInvokeDirectRefAfter(LgNamesContent.get(_util,_cust,_mapping.getVal(INVOKE_DIRECT_REF_AFTER)));
        setAliasGetLowerBounds(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_LOWER_BOUNDS)));
        setAliasGetTypeParameters(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_TYPE_PARAMETERS)));
        setAliasConstructor(LgNamesContent.get(_util,_cust,_mapping.getVal(CONSTRUCTOR)));
        setAliasNewInstance(LgNamesContent.get(_util,_cust,_mapping.getVal(NEW_INSTANCE)));
        setAliasNewInstanceRef(LgNamesContent.get(_util,_cust,_mapping.getVal(NEW_INSTANCE_REF)));
        setAliasNewInstanceRefAfter(LgNamesContent.get(_util,_cust,_mapping.getVal(NEW_INSTANCE_REF_AFTER)));
        setAliasGetEnclosingType(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_ENCLOSING_TYPE)));
        setAliasGetInterfaces(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_INTERFACES)));
        setAliasGetDeclaredClasses(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DECLARED_CLASSES)));
        setAliasGetSuperClass(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_SUPER_CLASS)));
        setAliasGetComponentType(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_COMPONENT_TYPE)));
        setAliasGetFileName(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_FILE_NAME)));
        setAliasGetGenericSuperClass(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_GENERIC_SUPER_CLASS)));
        setAliasGetGenericInterfaces(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_GENERIC_INTERFACES)));
        setAliasIsAbstract(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_ABSTRACT)));
        setAliasMakeArray(LgNamesContent.get(_util,_cust,_mapping.getVal(MAKE_ARRAY)));
        setAliasIsInterface(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_INTERFACE)));
        setAliasMakeRef(LgNamesContent.get(_util,_cust,_mapping.getVal(MAKE_REF_TYPE)));
        setAliasMakeWildCard(LgNamesContent.get(_util,_cust,_mapping.getVal(MAKE_WILD_CARD)));
        setAliasIsTypeVariable(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_TYPE_VARIABLE)));
        setAliasIsPrivate(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_PRIVATE)));
        setAliasIsVarargs(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_VARARGS)));
        setAliasIsInstance(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_INSTANCE)));
        setAliasGetReturnType(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_RETURN_TYPE)));
        setAliasGetActualTypeArguments(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_ACTUAL_TYPE_ARGUMENTS)));
        setAliasIsProtected(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_PROTECTED)));
        setAliasIsPrimitive(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_PRIMITIVE)));
        setAliasIsRefType(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_REF_TYPE)));
        setAliasIsWildCard(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_WILD_CARD)));
        setAliasIsAnnotation(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_ANNOTATION)));
        setAliasGetGenericType(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_GENERIC_TYPE)));
        setAliasIsAssignableFrom(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_ASSIGNABLE_FROM)));
        setAliasIsVariable(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_VARIABLE)));
        setAliasIsPackage(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_PACKAGE)));
    }
    public static void en(TranslationsFile _en){
        _en.add(ANNOTATED,"Annotated=$core.Annotated");
        _en.add(ANNOTATION_TYPE,"AnnotationType=$core.Annotation");
        _en.add(CLASS_TYPE,"ClassType=$core.Class");
        _en.add(CONSTRUCTOR,"Constructor=$core.Constructor");
        _en.add(FCT,"Fct=$core.Fct");
        _en.add(FIELD,"Field=$core.Field");
        _en.add(METHOD,"Method=$core.Method");
        _en.add(CLASS_NOT_FOUND_ERROR,"ClassNotFoundError=$core.ClassNotFound");
        _en.add(INVOKE_TARGET,"InvokeTarget=$core.InvokeTarget");
        _en.add(GET_FILE_NAME,"GetFileName=getFileName");
        _en.add(GET_DECLARING_CLASS,"GetDeclaringClass=getDeclaringClass");
        _en.add(GET_DECLARED_ANONYMOUS_LAMBDA,"GetDeclaredAnonymousLambda=getDeclaredAnonymousLambda");
        _en.add(GET_DECLARED_SWITCH_METHODS,"GetDeclaredSwitchMethods=getDeclaredSwitchMethods");
        _en.add(GET_ANNOTATIONS,"GetAnnotations=getAnnotations");
        _en.add(GET_ANNOTATIONS_PARAMETERS,"GetAnnotationsParameters=getAnnotationsParameters");
        _en.add(GET_STRING,"GetString=getString");
        _en.add(DEFAULT_INSTANCE,"DefaultInstance=defaultInstance");
        _en.add(ENUM_VALUE_OF,"EnumValueOf=enumValueOf");
        _en.add(FOR_NAME,"ForName=forName");
        _en.add(ARRAY_GET,"ArrayGet=get");
        _en.add(GET_ACTUAL_TYPE_ARGUMENTS,"GetActualTypeArguments=getActualTypeArguments");
        _en.add(GET_ALL_CLASSES,"GetAllClasses=getAllClasses");
        _en.add(GET_BOUNDS,"GetBounds=getBounds");
        _en.add(GET_CLASS,"GetClass=getClass");
        _en.add(GET_COMPONENT_TYPE,"GetComponentType=getComponentType");
        _en.add(GET_DECLARED_CLASSES,"GetDeclaredClasses=getDeclaredClasses");
        _en.add(GET_DECLARED_CONSTRUCTORS,"GetDeclaredConstructors=getDeclaredConstructors");
        _en.add(GET_DECLARED_FIELDS,"GetDeclaredFields=getDeclaredFields");
        _en.add(GET_DECLARED_ANONYMOUS_TYPES,"GetDeclaredAnonymousTypes=getDeclaredAnonymousTypes");
        _en.add(GET_DECLARED_LOCAL_TYPES,"GetDeclaredLocalTypes=getDeclaredLocalTypes");
        _en.add(GET_DECLARED_BLOCKS,"GetDeclaredBlocks=getDeclaredBlocks");
        _en.add(GET_DECLARED_EXPLICITS,"GetDeclaredExplicits=getDeclaredExplicits");
        _en.add(GET_DECLARED_IMPLICITS,"GetDeclaredImplicits=getDeclaredImplicits");
        _en.add(GET_DECLARED_TRUE_OPERATORS,"GetDeclaredTrueOperators=getDeclaredTrueOperators");
        _en.add(GET_DECLARED_FALSE_OPERATORS,"GetDeclaredFalseOperators=getDeclaredFalseOperators");
        _en.add(GET_DECLARED_METHODS,"GetDeclaredMethods=getDeclaredMethods");
        _en.add(GET_DECLARED_STATIC_METHODS,"GetDeclaredStaticMethods=getDeclaredStaticMethods");
        _en.add(GET_ENCLOSING_TYPE,"GetEnclosingType=getEnclosingType");
        _en.add(GET_ENUM_CONSTANTS,"GetEnumConstants=getEnumConstants");
        _en.add(GET_GENERIC_BOUNDS,"GetGenericBounds=getGenericBounds");
        _en.add(GET_GENERIC_INTERFACES,"GetGenericInterfaces=getGenericInterfaces");
        _en.add(GET_GENERIC_SUPER_CLASS,"GetGenericSuperClass=getGenericSuperClass");
        _en.add(GET_GENERIC_VARIABLE_OWNER,"GetGenericVariableOwner=getGenericVariableOwner");
        _en.add(GET_INTERFACES,"GetInterfaces=getInterfaces");
        _en.add(ARRAY_GET_LENGTH,"ArrayGetLength=getLength");
        _en.add(GET_LOWER_BOUNDS,"GetLowerBounds=getLowerBounds");
        _en.add(GET_NAME,"GetName=getName");
        _en.add(GET_OPERATORS,"GetOperators=getOperators");
        _en.add(GET_PRETTY_NAME,"GetPrettyName=getPrettyName");
        _en.add(GET_PRETTY_SINGLE_NAME,"GetPrettySingleName=getPrettySingleName");
        _en.add(GET_SUPER_CLASS,"GetSuperClass=getSuperClass");
        _en.add(GET_TYPE_PARAMETERS,"GetTypeParameters=getTypeParameters");
        _en.add(GET_UPPER_BOUNDS,"GetUpperBounds=getUpperBounds");
        _en.add(GET_VARIABLE_OWNER,"GetVariableOwner=getVariableOwner");
        _en.add(INIT,"Init=init");
        _en.add(TRY_WRAP,"TryWrap=tryWrap");
        _en.add(IS_ABSTRACT,"IsAbstract=isAbstract");
        _en.add(IS_ANNOTATION,"IsAnnotation=isAnnotation");
        _en.add(IS_ARRAY,"IsArray=isArray");
        _en.add(IS_ASSIGNABLE_FROM,"IsAssignableFrom=isAssignableFrom");
        _en.add(IS_SPE_CLASS,"IsSpeClass=isSpeClass");
        _en.add(IS_SPE_MU_CLASS,"IsSpeMuClass=isSpeClassMu");
        _en.add(IS_CLASS,"IsClass=isClass");
        _en.add(IS_ENUM,"IsEnum=isEnum");
        _en.add(IS_FINAL,"IsFinal=isFinal");
        _en.add(IS_TYPE_VARIABLE,"IsTypeVariable=isTypeVariable");
        _en.add(IS_VARIABLE,"IsVariable=isVariable");
        _en.add(IS_INSTANCE,"IsInstance=isInstance");
        _en.add(IS_INTERFACE,"IsInterface=isInterface");
        _en.add(IS_PACKAGE,"IsPackage=isPackage");
        _en.add(IS_PRIMITIVE,"IsPrimitive=isPrimitive");
        _en.add(IS_PRIVATE,"IsPrivate=isPrivate");
        _en.add(IS_PROTECTED,"IsProtected=isProtected");
        _en.add(IS_PUBLIC,"IsPublic=isPublic");
        _en.add(IS_STATIC,"IsStatic=isStatic");
        _en.add(IS_REF_TYPE,"IsRefType=isRefType");
        _en.add(IS_WILD_CARD,"IsWildCard=isWildCard");
        _en.add(MAKE_ARRAY,"MakeArray=makeArray");
        _en.add(MAKE_GENERIC,"MakeGeneric=makeGeneric");
        _en.add(MAKE_REF_TYPE,"MakeRefType=makeRefType");
        _en.add(MAKE_WILD_CARD,"MakeWildCard=makeWildCard");
        _en.add(ARRAY_NEW_INSTANCE,"ArrayNewInstance=newArrayInstance");
        _en.add(ARRAY_SET,"ArraySet=set");
        _en.add(GET_GENERIC_RETURN_TYPE,"GetGenericReturnType=getGenericReturnType");
        _en.add(GET_PARAMETER_NAMES,"GetParameterNames=getGenericParameterTypes");
        _en.add(GET_PARAMETER_TYPES,"GetParameterTypes=getParameterTypes");
        _en.add(GET_RETURN_TYPE,"GetReturnType=getReturnType");
        _en.add(IS_VARARGS,"IsVarargs=isVarargs");
        _en.add(NEW_INSTANCE,"NewInstance=newInstance");
        _en.add(NEW_INSTANCE_REF,"NewInstanceRef=newInstanceRef");
        _en.add(NEW_INSTANCE_REF_AFTER,"NewInstanceRefAfter=newInstanceRefAfter");
        _en.add(CALL,"Call=call");
        _en.add(CALL_REF,"CallRef=callRef");
        _en.add(CALL_REF_AFTER,"CallRefAfter=callRefAfter");
        _en.add(META_INFO,"MetaInfo=metaInfo");
        _en.add(INSTANCE,"Instance=instance");
        _en.add(GET_FIELD,"GetField=get");
        _en.add(GET_GENERIC_TYPE,"GetGenericType=getGenericType");
        _en.add(GET_TYPE,"GetType=getType");
        _en.add(SET_FIELD,"SetField=set");
        _en.add(GET_ANNOTATIONS_SUPP,"GetAnnotationsSupp=getAnnotationsAddon");
        _en.add(GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS,"GetDeclaredAnonymousLambdaLocalVars=getDeclaredAnonymousLambdaLocVars");
        _en.add(GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_NB,"GetDeclaredAnonymousLambdaLocalVarsNb=getDeclaredAnonymousLambdaWrapVarsNb");
        _en.add(GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS,"GetDeclaredAnonymousLambdaLoopVars=getDeclaredAnonymousLambdaLoopVars");
        _en.add(GET_DEFAULT_VALUE,"GetDefaultValue=getDefaultValue");
        _en.add(INVOKE,"Invoke=invoke");
        _en.add(INVOKE_REF,"InvokeRef=invokeRef");
        _en.add(INVOKE_REF_AFTER,"InvokeRefAfter=invokeRefAfter");
        _en.add(INVOKE_DIRECT,"InvokeDirect=invokeDirect");
        _en.add(INVOKE_DIRECT_REF,"InvokeDirectRef=invokeDirectRef");
        _en.add(INVOKE_DIRECT_REF_AFTER,"InvokeDirectRefAfter=invokeDirectRefAfter");
        _en.add(IS_NORMAL,"IsNormal=isNormal");
        _en.add(IS_STATIC_CALL,"IsStaticCall=isStaticCall");
        _en.add(IS_INSTANCE_METHOD,"IsInstanceMethod=isInstanceMethod");
    }
    public static void fr(TranslationsFile _fr){
        _fr.add(ANNOTATED,"Annotated=$coeur.Annote");
        _fr.add(ANNOTATION_TYPE,"AnnotationType=$coeur.Annotation");
        _fr.add(CLASS_TYPE,"ClassType=$coeur.Classe");
        _fr.add(CONSTRUCTOR,"Constructor=$coeur.Constructeur");
        _fr.add(FCT,"Fct=$coeur.Fct");
        _fr.add(FIELD,"Field=$coeur.Champ");
        _fr.add(METHOD,"Method=$coeur.Methode");
        _fr.add(CLASS_NOT_FOUND_ERROR,"ClassNotFoundError=$coeur.ClasseNonTrouve");
        _fr.add(INVOKE_TARGET,"InvokeTarget=$coeur.InvoqueCible");
        _fr.add(GET_FILE_NAME,"GetFileName=valNomFichier");
        _fr.add(GET_DECLARING_CLASS,"GetDeclaringClass=valClasseDeclarante");
        _fr.add(GET_DECLARED_ANONYMOUS_LAMBDA,"GetDeclaredAnonymousLambda=valLambdaAnonymesDeclares");
        _fr.add(GET_DECLARED_SWITCH_METHODS,"GetDeclaredSwitchMethods=valMethodesSelon");
        _fr.add(GET_ANNOTATIONS,"GetAnnotations=valAnnotations");
        _fr.add(GET_ANNOTATIONS_PARAMETERS,"GetAnnotationsParameters=valAnnotationsParametrees");
        _fr.add(GET_STRING,"GetString=valChaine");
        _fr.add(DEFAULT_INSTANCE,"DefaultInstance=instanceParDefaut");
        _fr.add(ENUM_VALUE_OF,"EnumValueOf=enumValeurDe");
        _fr.add(FOR_NAME,"ForName=parNom");
        _fr.add(ARRAY_GET,"ArrayGet=obtenir");
        _fr.add(GET_ACTUAL_TYPE_ARGUMENTS,"GetActualTypeArguments=valCourantArgTypes");
        _fr.add(GET_ALL_CLASSES,"GetAllClasses=valClasses");
        _fr.add(GET_BOUNDS,"GetBounds=valContraintes");
        _fr.add(GET_CLASS,"GetClass=valClasse");
        _fr.add(GET_COMPONENT_TYPE,"GetComponentType=valTypeComposent");
        _fr.add(GET_DECLARED_CLASSES,"GetDeclaredClasses=valClassesDeclarees");
        _fr.add(GET_DECLARED_CONSTRUCTORS,"GetDeclaredConstructors=valConstructeursDeclares");
        _fr.add(GET_DECLARED_FIELDS,"GetDeclaredFields=valChampsDeclares");
        _fr.add(GET_DECLARED_ANONYMOUS_TYPES,"GetDeclaredAnonymousTypes=valTypesAnonymesDeclares");
        _fr.add(GET_DECLARED_LOCAL_TYPES,"GetDeclaredLocalTypes=valLocalTypesDeclares");
        _fr.add(GET_DECLARED_BLOCKS,"GetDeclaredBlocks=valBlocsDeclares");
        _fr.add(GET_DECLARED_EXPLICITS,"GetDeclaredExplicits=valExplicitesDeclares");
        _fr.add(GET_DECLARED_IMPLICITS,"GetDeclaredImplicits=valImplicitesDeclares");
        _fr.add(GET_DECLARED_TRUE_OPERATORS,"GetDeclaredTrueOperators=valOperateursVraiDeclares");
        _fr.add(GET_DECLARED_FALSE_OPERATORS,"GetDeclaredFalseOperators=valOperateursFauxDeclares");
        _fr.add(GET_DECLARED_METHODS,"GetDeclaredMethods=valMethodsDeclares");
        _fr.add(GET_DECLARED_STATIC_METHODS,"GetDeclaredStaticMethods=valMethodsStaticDeclares");
        _fr.add(GET_ENCLOSING_TYPE,"GetEnclosingType=valTypeContenant");
        _fr.add(GET_ENUM_CONSTANTS,"GetEnumConstants=valEnumConst");
        _fr.add(GET_GENERIC_BOUNDS,"GetGenericBounds=valGeneContraintes");
        _fr.add(GET_GENERIC_INTERFACES,"GetGenericInterfaces=valGeneInterfaces");
        _fr.add(GET_GENERIC_SUPER_CLASS,"GetGenericSuperClass=valGeneSuperClasse");
        _fr.add(GET_GENERIC_VARIABLE_OWNER,"GetGenericVariableOwner=valGeneVariablePoss");
        _fr.add(GET_INTERFACES,"GetInterfaces=valInterfaces");
        _fr.add(ARRAY_GET_LENGTH,"ArrayGetLength=valLongeur");
        _fr.add(GET_LOWER_BOUNDS,"GetLowerBounds=valSousTypesContraintes");
        _fr.add(GET_NAME,"GetName=valNom");
        _fr.add(GET_OPERATORS,"GetOperators=valOperateurs");
        _fr.add(GET_PRETTY_NAME,"GetPrettyName=valJoliNom");
        _fr.add(GET_PRETTY_SINGLE_NAME,"GetPrettySingleName=valJoliSimpleNom");
        _fr.add(GET_SUPER_CLASS,"GetSuperClass=valSuperClasse");
        _fr.add(GET_TYPE_PARAMETERS,"GetTypeParameters=valTypesParametres");
        _fr.add(GET_UPPER_BOUNDS,"GetUpperBounds=valSuperTypesContraintes");
        _fr.add(GET_VARIABLE_OWNER,"GetVariableOwner=valVariablePoss");
        _fr.add(INIT,"Init=init");
        _fr.add(TRY_WRAP,"TryWrap=essaiEnvel");
        _fr.add(IS_ABSTRACT,"IsAbstract=estAbstrait");
        _fr.add(IS_ANNOTATION,"IsAnnotation=estAnnotation");
        _fr.add(IS_ARRAY,"IsArray=estTableau");
        _fr.add(IS_ASSIGNABLE_FROM,"IsAssignableFrom=estAssignableDe");
        _fr.add(IS_SPE_CLASS,"IsSpeClass=estClasseSpe");
        _fr.add(IS_SPE_MU_CLASS,"IsSpeMuClass=estClasseSpeMu");
        _fr.add(IS_CLASS,"IsClass=estClasse");
        _fr.add(IS_ENUM,"IsEnum=estEnum");
        _fr.add(IS_FINAL,"IsFinal=estFinal");
        _fr.add(IS_TYPE_VARIABLE,"IsTypeVariable=estTypeVariable");
        _fr.add(IS_VARIABLE,"IsVariable=estVariable");
        _fr.add(IS_INSTANCE,"IsInstance=estInstance");
        _fr.add(IS_INTERFACE,"IsInterface=estInterface");
        _fr.add(IS_PACKAGE,"IsPackage=estPaquetage");
        _fr.add(IS_PRIMITIVE,"IsPrimitive=estPrimitif");
        _fr.add(IS_PRIVATE,"IsPrivate=estPrive");
        _fr.add(IS_PROTECTED,"IsProtected=estProtege");
        _fr.add(IS_PUBLIC,"IsPublic=estPublic");
        _fr.add(IS_STATIC,"IsStatic=estStatic");
        _fr.add(IS_REF_TYPE,"IsRefType=estTypeRef");
        _fr.add(IS_WILD_CARD,"IsWildCard=estSynthetique");
        _fr.add(MAKE_ARRAY,"MakeArray=rendreTableau");
        _fr.add(MAKE_GENERIC,"MakeGeneric=rendreGeneric");
        _fr.add(MAKE_REF_TYPE,"MakeRefType=rendreTypeRef");
        _fr.add(MAKE_WILD_CARD,"MakeWildCard=rendreSynthetique");
        _fr.add(ARRAY_NEW_INSTANCE,"ArrayNewInstance=nouvelleInstanceTableau");
        _fr.add(ARRAY_SET,"ArraySet=maj");
        _fr.add(GET_GENERIC_RETURN_TYPE,"GetGenericReturnType=valGeneTypeRetour");
        _fr.add(GET_PARAMETER_NAMES,"GetParameterNames=valGeneTypesParametres");
        _fr.add(GET_PARAMETER_TYPES,"GetParameterTypes=valTypesDeParametres");
        _fr.add(GET_RETURN_TYPE,"GetReturnType=valTypeRetour");
        _fr.add(IS_VARARGS,"IsVarargs=estVarargs");
        _fr.add(NEW_INSTANCE,"NewInstance=nouvelleInstance");
        _fr.add(NEW_INSTANCE_REF,"NewInstanceRef=nouvelleInstanceRef");
        _fr.add(NEW_INSTANCE_REF_AFTER,"NewInstanceRefAfter=nouvelleInstanceRefApres");
        _fr.add(CALL,"Call=appeler");
        _fr.add(CALL_REF,"CallRef=appelerRef");
        _fr.add(CALL_REF_AFTER,"CallRefAfter=appelerRefApres");
        _fr.add(META_INFO,"MetaInfo=metaInfo");
        _fr.add(INSTANCE,"Instance=instance");
        _fr.add(GET_FIELD,"GetField=obtenir");
        _fr.add(GET_GENERIC_TYPE,"GetGenericType=valGeneType");
        _fr.add(GET_TYPE,"GetType=valType");
        _fr.add(SET_FIELD,"SetField=maj");
        _fr.add(GET_ANNOTATIONS_SUPP,"GetAnnotationsSupp=valAnnotationsSupp");
        _fr.add(GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS,"GetDeclaredAnonymousLambdaLocalVars=valLocalVarsLambdaAnonymesDeclares");
        _fr.add(GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_NB,"GetDeclaredAnonymousLambdaLocalVarsNb=valEnvelVarsLambdaAnonymesDeclaresNb");
        _fr.add(GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS,"GetDeclaredAnonymousLambdaLoopVars=valBoucleVarsLambdaAnonymesDeclares");
        _fr.add(GET_DEFAULT_VALUE,"GetDefaultValue=valDefValeur");
        _fr.add(INVOKE,"Invoke=invoque");
        _fr.add(INVOKE_REF,"InvokeRef=invoqueRef");
        _fr.add(INVOKE_REF_AFTER,"InvokeRefAfter=invoqueRefApres");
        _fr.add(INVOKE_DIRECT,"InvokeDirect=invoqueDirect");
        _fr.add(INVOKE_DIRECT_REF,"InvokeDirectRef=invoqueDirectRef");
        _fr.add(INVOKE_DIRECT_REF_AFTER,"InvokeDirectRefAfter=invoqueDirectRefApres");
        _fr.add(IS_NORMAL,"IsNormal=estNormal");
        _fr.add(IS_STATIC_CALL,"IsStaticCall=estStaticAppel");
        _fr.add(IS_INSTANCE_METHOD,"IsInstanceMethod=estMethInstance");
    }
    public static void mapping(StringMap<String> _m){
        _m.addEntry(ANNOTATED,"Annotated");
        _m.addEntry(ANNOTATION_TYPE,"AnnotationType");
        _m.addEntry(CLASS_TYPE,"ClassType");
        _m.addEntry(CONSTRUCTOR,"Constructor");
        _m.addEntry(FCT,"Fct");
        _m.addEntry(FIELD,"Field");
        _m.addEntry(METHOD,"Method");
        _m.addEntry(CLASS_NOT_FOUND_ERROR,"ClassNotFoundError");
        _m.addEntry(INVOKE_TARGET,"InvokeTarget");
        _m.addEntry(GET_FILE_NAME,"GetFileName");
        _m.addEntry(GET_DECLARING_CLASS,"GetDeclaringClass");
        _m.addEntry(GET_DECLARED_ANONYMOUS_LAMBDA,"GetDeclaredAnonymousLambda");
        _m.addEntry(GET_DECLARED_SWITCH_METHODS,"GetDeclaredSwitchMethods");
        _m.addEntry(GET_ANNOTATIONS,"GetAnnotations");
        _m.addEntry(GET_ANNOTATIONS_PARAMETERS,"GetAnnotationsParameters");
        _m.addEntry(GET_STRING,"GetString");
        _m.addEntry(DEFAULT_INSTANCE,"DefaultInstance");
        _m.addEntry(ENUM_VALUE_OF,"EnumValueOf");
        _m.addEntry(FOR_NAME,"ForName");
        _m.addEntry(ARRAY_GET,"ArrayGet");
        _m.addEntry(GET_ACTUAL_TYPE_ARGUMENTS,"GetActualTypeArguments");
        _m.addEntry(GET_ALL_CLASSES,"GetAllClasses");
        _m.addEntry(GET_BOUNDS,"GetBounds");
        _m.addEntry(GET_CLASS,"GetClass");
        _m.addEntry(GET_COMPONENT_TYPE,"GetComponentType");
        _m.addEntry(GET_DECLARED_CLASSES,"GetDeclaredClasses");
        _m.addEntry(GET_DECLARED_CONSTRUCTORS,"GetDeclaredConstructors");
        _m.addEntry(GET_DECLARED_FIELDS,"GetDeclaredFields");
        _m.addEntry(GET_DECLARED_ANONYMOUS_TYPES,"GetDeclaredAnonymousTypes");
        _m.addEntry(GET_DECLARED_LOCAL_TYPES,"GetDeclaredLocalTypes");
        _m.addEntry(GET_DECLARED_BLOCKS,"GetDeclaredBlocks");
        _m.addEntry(GET_DECLARED_EXPLICITS,"GetDeclaredExplicits");
        _m.addEntry(GET_DECLARED_IMPLICITS,"GetDeclaredImplicits");
        _m.addEntry(GET_DECLARED_TRUE_OPERATORS,"GetDeclaredTrueOperators");
        _m.addEntry(GET_DECLARED_FALSE_OPERATORS,"GetDeclaredFalseOperators");
        _m.addEntry(GET_DECLARED_METHODS,"GetDeclaredMethods");
        _m.addEntry(GET_DECLARED_STATIC_METHODS,"GetDeclaredStaticMethods");
        _m.addEntry(GET_ENCLOSING_TYPE,"GetEnclosingType");
        _m.addEntry(GET_ENUM_CONSTANTS,"GetEnumConstants");
        _m.addEntry(GET_GENERIC_BOUNDS,"GetGenericBounds");
        _m.addEntry(GET_GENERIC_INTERFACES,"GetGenericInterfaces");
        _m.addEntry(GET_GENERIC_SUPER_CLASS,"GetGenericSuperClass");
        _m.addEntry(GET_GENERIC_VARIABLE_OWNER,"GetGenericVariableOwner");
        _m.addEntry(GET_INTERFACES,"GetInterfaces");
        _m.addEntry(ARRAY_GET_LENGTH,"ArrayGetLength");
        _m.addEntry(GET_LOWER_BOUNDS,"GetLowerBounds");
        _m.addEntry(GET_NAME,"GetName");
        _m.addEntry(GET_OPERATORS,"GetOperators");
        _m.addEntry(GET_PRETTY_NAME,"GetPrettyName");
        _m.addEntry(GET_PRETTY_SINGLE_NAME,"GetPrettySingleName");
        _m.addEntry(GET_SUPER_CLASS,"GetSuperClass");
        _m.addEntry(GET_TYPE_PARAMETERS,"GetTypeParameters");
        _m.addEntry(GET_UPPER_BOUNDS,"GetUpperBounds");
        _m.addEntry(GET_VARIABLE_OWNER,"GetVariableOwner");
        _m.addEntry(INIT,"Init");
        _m.addEntry(TRY_WRAP,"TryWrap");
        _m.addEntry(IS_ABSTRACT,"IsAbstract");
        _m.addEntry(IS_ANNOTATION,"IsAnnotation");
        _m.addEntry(IS_ARRAY,"IsArray");
        _m.addEntry(IS_ASSIGNABLE_FROM,"IsAssignableFrom");
        _m.addEntry(IS_SPE_CLASS,"IsSpeClass");
        _m.addEntry(IS_SPE_MU_CLASS,"IsSpeMuClass");
        _m.addEntry(IS_CLASS,"IsClass");
        _m.addEntry(IS_ENUM,"IsEnum");
        _m.addEntry(IS_FINAL,"IsFinal");
        _m.addEntry(IS_TYPE_VARIABLE,"IsTypeVariable");
        _m.addEntry(IS_VARIABLE,"IsVariable");
        _m.addEntry(IS_INSTANCE,"IsInstance");
        _m.addEntry(IS_INTERFACE,"IsInterface");
        _m.addEntry(IS_PACKAGE,"IsPackage");
        _m.addEntry(IS_PRIMITIVE,"IsPrimitive");
        _m.addEntry(IS_PRIVATE,"IsPrivate");
        _m.addEntry(IS_PROTECTED,"IsProtected");
        _m.addEntry(IS_PUBLIC,"IsPublic");
        _m.addEntry(IS_STATIC,"IsStatic");
        _m.addEntry(IS_REF_TYPE,"IsRefType");
        _m.addEntry(IS_WILD_CARD,"IsWildCard");
        _m.addEntry(MAKE_ARRAY,"MakeArray");
        _m.addEntry(MAKE_GENERIC,"MakeGeneric");
        _m.addEntry(MAKE_REF_TYPE,"MakeRefType");
        _m.addEntry(MAKE_WILD_CARD,"MakeWildCard");
        _m.addEntry(ARRAY_NEW_INSTANCE,"ArrayNewInstance");
        _m.addEntry(ARRAY_SET,"ArraySet");
        _m.addEntry(GET_GENERIC_RETURN_TYPE,"GetGenericReturnType");
        _m.addEntry(GET_PARAMETER_NAMES,"GetParameterNames");
        _m.addEntry(GET_PARAMETER_TYPES,"GetParameterTypes");
        _m.addEntry(GET_RETURN_TYPE,"GetReturnType");
        _m.addEntry(IS_VARARGS,"IsVarargs");
        _m.addEntry(NEW_INSTANCE,"NewInstance");
        _m.addEntry(NEW_INSTANCE_REF,"NewInstanceRef");
        _m.addEntry(NEW_INSTANCE_REF_AFTER,"NewInstanceRefAfter");
        _m.addEntry(CALL,"Call");
        _m.addEntry(CALL_REF,"CallRef");
        _m.addEntry(CALL_REF_AFTER,"CallRefAfter");
        _m.addEntry(META_INFO,"MetaInfo");
        _m.addEntry(INSTANCE,"Instance");
        _m.addEntry(GET_FIELD,"GetField");
        _m.addEntry(GET_GENERIC_TYPE,"GetGenericType");
        _m.addEntry(GET_TYPE,"GetType");
        _m.addEntry(SET_FIELD,"SetField");
        _m.addEntry(GET_ANNOTATIONS_SUPP,"GetAnnotationsSupp");
        _m.addEntry(GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS,"GetDeclaredAnonymousLambdaLocalVars");
        _m.addEntry(GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_NB,"GetDeclaredAnonymousLambdaLocalVarsNb");
        _m.addEntry(GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS,"GetDeclaredAnonymousLambdaLoopVars");
        _m.addEntry(GET_DEFAULT_VALUE,"GetDefaultValue");
        _m.addEntry(INVOKE,"Invoke");
        _m.addEntry(INVOKE_REF,"InvokeRef");
        _m.addEntry(INVOKE_REF_AFTER,"InvokeRefAfter");
        _m.addEntry(INVOKE_DIRECT,"InvokeDirect");
        _m.addEntry(INVOKE_DIRECT_REF,"InvokeDirectRef");
        _m.addEntry(INVOKE_DIRECT_REF_AFTER,"InvokeDirectRefAfter");
        _m.addEntry(IS_NORMAL,"IsNormal");
        _m.addEntry(IS_STATIC_CALL,"IsStaticCall");
        _m.addEntry(IS_INSTANCE_METHOD,"IsInstanceMethod");
    }
    public StringMap<String> allRefTypes(StringMap<String> _mapping) {
        StringMap<String> list_ = new StringMap<String>();
        list_.addEntry(_mapping.getVal(ANNOTATED), getAliasAnnotated());
        list_.addEntry(_mapping.getVal(ANNOTATION_TYPE), getAliasAnnotationType());
        list_.addEntry(_mapping.getVal(CLASS_TYPE), getAliasClassType());
        list_.addEntry(_mapping.getVal(CONSTRUCTOR), getAliasConstructor());
        list_.addEntry(_mapping.getVal(FCT), getAliasFct());
        list_.addEntry(_mapping.getVal(FIELD), getAliasField());
        list_.addEntry(_mapping.getVal(METHOD), getAliasMethod());
        list_.addEntry(_mapping.getVal(CLASS_NOT_FOUND_ERROR), getAliasClassNotFoundError());
        list_.addEntry(_mapping.getVal(INVOKE_TARGET), getAliasInvokeTarget());
        return list_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(getAliasAnnotated(), listAnnot(_mapping));
        map_.addEntry(getAliasAnnotationType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(GET_STRING), getAliasGetString())));
        map_.addEntry(getAliasClassType(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(GET_ANNOTATIONS), getAliasGetAnnotations()),
                new KeyValueMemberName(_mapping.getVal(GET_ANNOTATIONS_PARAMETERS), getAliasGetAnnotationsParameters()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARING_CLASS),getAliasGetDeclaringClass()),
                new KeyValueMemberName(_mapping.getVal(DEFAULT_INSTANCE), getAliasDefaultInstance()),
                new KeyValueMemberName(_mapping.getVal(ENUM_VALUE_OF), getAliasEnumValueOf()),
                new KeyValueMemberName(_mapping.getVal(FOR_NAME), getAliasForName()),
                new KeyValueMemberName(_mapping.getVal(ARRAY_GET), getAliasArrayGet()),
                new KeyValueMemberName(_mapping.getVal(GET_ACTUAL_TYPE_ARGUMENTS), getAliasGetActualTypeArguments()),
                new KeyValueMemberName(_mapping.getVal(GET_ALL_CLASSES), getAliasGetAllClasses()),
                new KeyValueMemberName(_mapping.getVal(GET_BOUNDS), getAliasGetBounds()),
                new KeyValueMemberName(_mapping.getVal(GET_CLASS), getAliasGetClass()),
                new KeyValueMemberName(_mapping.getVal(GET_COMPONENT_TYPE), getAliasGetComponentType()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_CLASSES), getAliasGetDeclaredClasses()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_CONSTRUCTORS), getAliasGetDeclaredConstructors()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_FIELDS), getAliasGetDeclaredFields()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_ANONYMOUS_TYPES), getAliasGetDeclaredAnonymousTypes()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_ANONYMOUS_LAMBDA), getAliasGetDeclaredAnonymousLambda()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_SWITCH_METHODS),getAliasGetDeclaredSwitchMethods()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_LOCAL_TYPES),getAliasGetDeclaredLocalTypes()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_BLOCKS),getAliasGetDeclaredBlocks()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_EXPLICITS),getAliasGetDeclaredExplicits()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_IMPLICITS),getAliasGetDeclaredImplicits()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_TRUE_OPERATORS),getAliasGetDeclaredTrueOperators()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_FALSE_OPERATORS),getAliasGetDeclaredFalseOperators()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_METHODS),getAliasGetDeclaredMethods()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_STATIC_METHODS),getAliasGetDeclaredStaticMethods()),
                new KeyValueMemberName(_mapping.getVal(GET_ENCLOSING_TYPE),getAliasGetEnclosingType()),
                new KeyValueMemberName(_mapping.getVal(GET_ENUM_CONSTANTS),getAliasGetEnumConstants()),
                new KeyValueMemberName(_mapping.getVal(GET_GENERIC_BOUNDS),getAliasGetGenericBounds()),
                new KeyValueMemberName(_mapping.getVal(GET_GENERIC_INTERFACES),getAliasGetGenericInterfaces()),
                new KeyValueMemberName(_mapping.getVal(GET_GENERIC_SUPER_CLASS),getAliasGetGenericSuperClass()),
                new KeyValueMemberName(_mapping.getVal(GET_GENERIC_VARIABLE_OWNER),getAliasGetGenericVariableOwner()),
                new KeyValueMemberName(_mapping.getVal(GET_INTERFACES),getAliasGetInterfaces()),
                new KeyValueMemberName(_mapping.getVal(ARRAY_GET_LENGTH),getAliasArrayGetLength()),
                new KeyValueMemberName(_mapping.getVal(GET_LOWER_BOUNDS),getAliasGetLowerBounds()),
                new KeyValueMemberName(_mapping.getVal(GET_FILE_NAME),getAliasGetFileName()),
                new KeyValueMemberName(_mapping.getVal(GET_NAME),getAliasGetName()),
                new KeyValueMemberName(_mapping.getVal(GET_OPERATORS),getAliasGetOperators()),
                new KeyValueMemberName(_mapping.getVal(GET_PRETTY_NAME),getAliasGetPrettyName()),
                new KeyValueMemberName(_mapping.getVal(GET_PRETTY_SINGLE_NAME),getAliasGetPrettySingleName()),
                new KeyValueMemberName(_mapping.getVal(GET_SUPER_CLASS),getAliasGetSuperClass()),
                new KeyValueMemberName(_mapping.getVal(GET_TYPE_PARAMETERS),getAliasGetTypeParameters()),
                new KeyValueMemberName(_mapping.getVal(GET_UPPER_BOUNDS),getAliasGetUpperBounds()),
                new KeyValueMemberName(_mapping.getVal(GET_VARIABLE_OWNER),getAliasGetVariableOwner()),
                new KeyValueMemberName(_mapping.getVal(INIT),getAliasInit()),
                new KeyValueMemberName(_mapping.getVal(TRY_WRAP),getAliasTryWrap()),
                new KeyValueMemberName(_mapping.getVal(IS_ABSTRACT),getAliasIsAbstract()),
                new KeyValueMemberName(_mapping.getVal(IS_ANNOTATION),getAliasIsAnnotation()),
                new KeyValueMemberName(_mapping.getVal(IS_ARRAY),getAliasIsArray()),
                new KeyValueMemberName(_mapping.getVal(IS_ASSIGNABLE_FROM),getAliasIsAssignableFrom()),
                new KeyValueMemberName(_mapping.getVal(IS_SPE_CLASS),getAliasIsSpecialClass()),
                new KeyValueMemberName(_mapping.getVal(IS_SPE_MU_CLASS),getAliasIsSpecialMuClass()),
                new KeyValueMemberName(_mapping.getVal(IS_CLASS),getAliasIsClass()),
                new KeyValueMemberName(_mapping.getVal(IS_ENUM),getAliasIsEnum()),
                new KeyValueMemberName(_mapping.getVal(IS_FINAL),getAliasIsFinal()),
                new KeyValueMemberName(_mapping.getVal(IS_TYPE_VARIABLE),getAliasIsTypeVariable()),
                new KeyValueMemberName(_mapping.getVal(IS_VARIABLE),getAliasIsVariable()),
                new KeyValueMemberName(_mapping.getVal(IS_INSTANCE),getAliasIsInstance()),
                new KeyValueMemberName(_mapping.getVal(IS_INTERFACE),getAliasIsInterface()),
                new KeyValueMemberName(_mapping.getVal(IS_PACKAGE),getAliasIsPackage()),
                new KeyValueMemberName(_mapping.getVal(IS_PRIMITIVE),getAliasIsPrimitive()),
                new KeyValueMemberName(_mapping.getVal(IS_PRIVATE),getAliasIsPrivate()),
                new KeyValueMemberName(_mapping.getVal(IS_PROTECTED),getAliasIsProtected()),
                new KeyValueMemberName(_mapping.getVal(IS_PUBLIC),getAliasIsPublic()),
                new KeyValueMemberName(_mapping.getVal(IS_STATIC),getAliasIsStatic()),
                new KeyValueMemberName(_mapping.getVal(IS_REF_TYPE),getAliasIsRefType()),
                new KeyValueMemberName(_mapping.getVal(IS_WILD_CARD),getAliasIsWildCard()),
                new KeyValueMemberName(_mapping.getVal(MAKE_ARRAY),getAliasMakeArray()),
                new KeyValueMemberName(_mapping.getVal(MAKE_GENERIC),getAliasMakeGeneric()),
                new KeyValueMemberName(_mapping.getVal(MAKE_REF_TYPE),getAliasMakeRef()),
                new KeyValueMemberName(_mapping.getVal(MAKE_WILD_CARD),getAliasMakeWildCard()),
                new KeyValueMemberName(_mapping.getVal(ARRAY_NEW_INSTANCE),getAliasArrayNewInstance()),
                new KeyValueMemberName(_mapping.getVal(ARRAY_SET),getAliasArraySet())));
        map_.addEntry(getAliasConstructor(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(GET_ANNOTATIONS),getAliasGetAnnotations()),
                new KeyValueMemberName(_mapping.getVal(GET_ANNOTATIONS_PARAMETERS),getAliasGetAnnotationsParameters()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_ANONYMOUS_TYPES),getAliasGetDeclaredAnonymousTypes()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_ANONYMOUS_LAMBDA),getAliasGetDeclaredAnonymousLambda()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_SWITCH_METHODS),getAliasGetDeclaredSwitchMethods()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARING_CLASS),getAliasGetDeclaringClass()),
                new KeyValueMemberName(_mapping.getVal(GET_GENERIC_RETURN_TYPE),getAliasGetGenericReturnType()),
                new KeyValueMemberName(_mapping.getVal(GET_FILE_NAME),getAliasGetFileName()),
                new KeyValueMemberName(_mapping.getVal(GET_NAME),getAliasGetName()),
                new KeyValueMemberName(_mapping.getVal(GET_PARAMETER_NAMES),getAliasGetParameterNames()),
                new KeyValueMemberName(_mapping.getVal(GET_PARAMETER_TYPES),getAliasGetParameterTypes()),
                new KeyValueMemberName(_mapping.getVal(GET_RETURN_TYPE),getAliasGetReturnType()),
                new KeyValueMemberName(_mapping.getVal(IS_PACKAGE),getAliasIsPackage()),
                new KeyValueMemberName(_mapping.getVal(IS_PRIVATE),getAliasIsPrivate()),
                new KeyValueMemberName(_mapping.getVal(IS_PROTECTED),getAliasIsProtected()),
                new KeyValueMemberName(_mapping.getVal(IS_PUBLIC),getAliasIsPublic()),
                new KeyValueMemberName(_mapping.getVal(IS_VARARGS),getAliasIsVarargs()),
                new KeyValueMemberName(_mapping.getVal(NEW_INSTANCE),getAliasNewInstance()),
                new KeyValueMemberName(_mapping.getVal(NEW_INSTANCE_REF),getAliasNewInstanceRef()),
                new KeyValueMemberName(_mapping.getVal(NEW_INSTANCE_REF_AFTER),getAliasNewInstanceRefAfter())));
        map_.addEntry(getAliasFct(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(CALL),getAliasCall()),
                new KeyValueMemberName(_mapping.getVal(CALL_REF),getAliasCallRef()),
                new KeyValueMemberName(_mapping.getVal(CALL_REF_AFTER),getAliasCallRefAfter()),
                new KeyValueMemberName(_mapping.getVal(META_INFO),getAliasMetaInfo()),
                new KeyValueMemberName(_mapping.getVal(INSTANCE),getAliasInstance())));
        map_.addEntry(getAliasField(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(GET_ANNOTATIONS),getAliasGetAnnotations()),
                new KeyValueMemberName(_mapping.getVal(GET_ANNOTATIONS_PARAMETERS),getAliasGetAnnotationsParameters()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_ANONYMOUS_TYPES),getAliasGetDeclaredAnonymousTypes()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_ANONYMOUS_LAMBDA),getAliasGetDeclaredAnonymousLambda()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_SWITCH_METHODS),getAliasGetDeclaredSwitchMethods()),
                new KeyValueMemberName(_mapping.getVal(GET_FIELD),getAliasGetField()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARING_CLASS),getAliasGetDeclaringClass()),
                new KeyValueMemberName(_mapping.getVal(GET_GENERIC_TYPE),getAliasGetGenericType()),
                new KeyValueMemberName(_mapping.getVal(GET_FILE_NAME),getAliasGetFileName()),
                new KeyValueMemberName(_mapping.getVal(GET_NAME),getAliasGetName()),
                new KeyValueMemberName(_mapping.getVal(GET_TYPE),getAliasGetType()),
                new KeyValueMemberName(_mapping.getVal(IS_FINAL),getAliasIsFinal()),
                new KeyValueMemberName(_mapping.getVal(IS_PACKAGE),getAliasIsPackage()),
                new KeyValueMemberName(_mapping.getVal(IS_PRIVATE),getAliasIsPrivate()),
                new KeyValueMemberName(_mapping.getVal(IS_PROTECTED),getAliasIsProtected()),
                new KeyValueMemberName(_mapping.getVal(IS_PUBLIC),getAliasIsPublic()),
                new KeyValueMemberName(_mapping.getVal(IS_STATIC),getAliasIsStatic()),
                new KeyValueMemberName(_mapping.getVal(SET_FIELD),getAliasSetField())));
        map_.addEntry(getAliasMethod(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(GET_ANNOTATIONS),getAliasGetAnnotations()),
                new KeyValueMemberName(_mapping.getVal(GET_ANNOTATIONS_SUPP),getAliasGetAnnotationsSupp()),
                new KeyValueMemberName(_mapping.getVal(GET_ANNOTATIONS_PARAMETERS),getAliasGetAnnotationsParameters()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_ANONYMOUS_TYPES),getAliasGetDeclaredAnonymousTypes()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_ANONYMOUS_LAMBDA),getAliasGetDeclaredAnonymousLambda()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_SWITCH_METHODS),getAliasGetDeclaredSwitchMethods()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS),getAliasGetDeclaredAnonymousLambdaLocalVars()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_NB),getAliasGetDeclaredAnonymousLambdaLocalVarsNb()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS),getAliasGetDeclaredAnonymousLambdaLoopVars()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARING_CLASS),getAliasGetDeclaringClass()),
                new KeyValueMemberName(_mapping.getVal(GET_DEFAULT_VALUE),getAliasGetDefaultValue()),
                new KeyValueMemberName(_mapping.getVal(GET_GENERIC_RETURN_TYPE),getAliasGetGenericReturnType()),
                new KeyValueMemberName(_mapping.getVal(GET_FILE_NAME),getAliasGetFileName()),
                new KeyValueMemberName(_mapping.getVal(GET_NAME),getAliasGetName()),
                new KeyValueMemberName(_mapping.getVal(GET_PARAMETER_NAMES),getAliasGetParameterNames()),
                new KeyValueMemberName(_mapping.getVal(GET_PARAMETER_TYPES),getAliasGetParameterTypes()),
                new KeyValueMemberName(_mapping.getVal(GET_RETURN_TYPE),getAliasGetReturnType()),
                new KeyValueMemberName(_mapping.getVal(INVOKE),getAliasInvoke()),
                new KeyValueMemberName(_mapping.getVal(INVOKE_DIRECT),getAliasInvokeDirect()),
                new KeyValueMemberName(_mapping.getVal(INVOKE_REF),getAliasInvokeRef()),
                new KeyValueMemberName(_mapping.getVal(INVOKE_DIRECT_REF),getAliasInvokeDirectRef()),
                new KeyValueMemberName(_mapping.getVal(INVOKE_REF_AFTER),getAliasInvokeRefAfter()),
                new KeyValueMemberName(_mapping.getVal(INVOKE_DIRECT_REF_AFTER),getAliasInvokeDirectRefAfter()),
                new KeyValueMemberName(_mapping.getVal(IS_ABSTRACT),getAliasIsAbstract()),
                new KeyValueMemberName(_mapping.getVal(IS_FINAL),getAliasIsFinal()),
                new KeyValueMemberName(_mapping.getVal(IS_NORMAL),getAliasIsNormal()),
                new KeyValueMemberName(_mapping.getVal(IS_PACKAGE),getAliasIsPackage()),
                new KeyValueMemberName(_mapping.getVal(IS_PRIVATE),getAliasIsPrivate()),
                new KeyValueMemberName(_mapping.getVal(IS_PROTECTED),getAliasIsProtected()),
                new KeyValueMemberName(_mapping.getVal(IS_PUBLIC),getAliasIsPublic()),
                new KeyValueMemberName(_mapping.getVal(IS_STATIC),getAliasIsStatic()),
                new KeyValueMemberName(_mapping.getVal(IS_STATIC_CALL),getAliasIsStaticCall()),
                new KeyValueMemberName(_mapping.getVal(IS_INSTANCE_METHOD),getAliasIsInstanceMethod()),
                new KeyValueMemberName(_mapping.getVal(IS_VARARGS),getAliasIsVarargs())));
        return map_;
    }

    public CustList<KeyValueMemberName> listAnnot(StringMap<String> _mapping) {
        return new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(GET_FILE_NAME), getAliasGetFileName()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARING_CLASS), getAliasGetDeclaringClass()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_ANONYMOUS_LAMBDA), getAliasGetDeclaredAnonymousLambda()),
                new KeyValueMemberName(_mapping.getVal(GET_DECLARED_SWITCH_METHODS), getAliasGetDeclaredSwitchMethods()),
                new KeyValueMemberName(_mapping.getVal(GET_ANNOTATIONS), getAliasGetAnnotations()),
                new KeyValueMemberName(_mapping.getVal(GET_ANNOTATIONS_PARAMETERS), getAliasGetAnnotationsParameters()));
    }
    private void buildAnnotated(LgNames _stds) {
        String aliasObject_ = _stds.getContent().getCoreNames().getAliasObject();
        String aliasString_ = _stds.getContent().getCharSeq().getAliasString();
        String aliasPrimBoolean_ = _stds.getContent().getPrimTypes().getAliasPrimBoolean();
        String aliasBoolean_ = _stds.getContent().getNbAlias().getAliasBoolean();
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        annotType = new StandardClass(aliasAnnotated, fields_, constructors_, methods_, aliasObject_, StdClassModifier.ABSTRACT);
        annotType.addSuperStdTypes(_stds.getCoreNames().getObjType());
        StringList params_ = new StringList();
        StandardMethod method_ = new StandardMethod(aliasGetAnnotations, params_, StringExpUtil.getPrettyArrayType(aliasAnnotationType), false, MethodModifier.FINAL,new FctAnnotatedGetAnnotations0());
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
        _stds.getStandards().addEntry(aliasAnnotated, annotType);
    }
    private void buildAnnotatedCl(LgNames _stds) {
        String aliasObject_ = _stds.getContent().getCoreNames().getAliasObject();
        String aliasString_ = _stds.getContent().getCharSeq().getAliasString();
        String aliasPrimBoolean_ = _stds.getContent().getPrimTypes().getAliasPrimBoolean();
        String aliasBoolean_ = _stds.getContent().getNbAlias().getAliasBoolean();
        String aliasPrimInt_ = _stds.getContent().getPrimTypes().getAliasPrimInteger();
        String aliasVoid_ = _stds.getContent().getCoreNames().getAliasVoid();
        String aliasEnum_ = _stds.getContent().getPredefTypes().getAliasEnumType();
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass stdcl_ = new StandardClass(aliasClassType, fields_, constructors_, methods_, aliasAnnotated , StdClassModifier.ABSTRACT, new DfClass());
        stdcl_.addSuperStdTypes(getAnnotType());
        stdcl_.addSuperStdTypes(_stds.getCoreNames().getObjType());
        StringList params_ = new StringList();
        StandardMethod method_ = new StandardMethod(aliasGetName, params_, aliasString_, false, MethodModifier.FINAL, new FctClassGetName());
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
    }
    private void buildAnnotatedCtor(LgNames _stds) {
        String aliasObject_ = _stds.getContent().getCoreNames().getAliasObject();
        String aliasString_ = _stds.getContent().getCharSeq().getAliasString();
        String aliasPrimBoolean_ = _stds.getContent().getPrimTypes().getAliasPrimBoolean();
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass stdcl_ = new StandardClass(aliasConstructor, fields_, constructors_, methods_, aliasAnnotated, StdClassModifier.ABSTRACT, new DfCtor());
        stdcl_.addSuperStdTypes(getAnnotType());
        stdcl_.addSuperStdTypes(_stds.getCoreNames().getObjType());
        StringList params_ = new StringList(aliasObject_);
        StandardMethod method_ = new StandardMethod(aliasNewInstance, params_, aliasObject_, true, MethodModifier.FINAL,new StringList(params.getAliasConstructor0NewInstance0()),new FctConstructorNewInstance(0));
        methods_.add( method_);
        params_ = new StringList(StringExpUtil.getPrettyArrayType(aliasObject_));
        method_ = new StandardMethod(aliasNewInstanceRef, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasConstructor0NewInstanceRef0()),new FctConstructorNewInstance(1));
        methods_.add( method_);
        params_ = new StringList(StringExpUtil.getPrettyArrayType(aliasObject_));
        method_ = new StandardMethod(aliasNewInstanceRefAfter, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasConstructor0NewInstanceRefAfter0()),new FctConstructorNewInstance(2));
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
    }
    private void buildAnnotatedField(LgNames _stds) {
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        String aliasObject_ = _stds.getContent().getCoreNames().getAliasObject();
        String aliasString_ = _stds.getContent().getCharSeq().getAliasString();
        String aliasPrimBoolean_ = _stds.getContent().getPrimTypes().getAliasPrimBoolean();
        String aliasVoid_ = _stds.getContent().getCoreNames().getAliasVoid();
        StandardClass stdcl_ = new StandardClass(aliasField, fields_, constructors_, methods_, aliasAnnotated, StdClassModifier.ABSTRACT, new DfField());
        stdcl_.addSuperStdTypes(getAnnotType());
        stdcl_.addSuperStdTypes(_stds.getCoreNames().getObjType());
        StringList params_ = new StringList(aliasObject_);
        StandardMethod method_ = new StandardMethod(aliasGetField, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasField0GetField0()),new FctFieldGetField());
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
    }
    private void buildAnnotatedMethod(LgNames _stds) {
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        String aliasObject_ = _stds.getContent().getCoreNames().getAliasObject();
        String aliasString_ = _stds.getContent().getCharSeq().getAliasString();
        String aliasPrimBoolean_ = _stds.getContent().getPrimTypes().getAliasPrimBoolean();
        String aliasVoid_ = _stds.getContent().getCoreNames().getAliasVoid();
        StandardClass stdcl_ = new StandardClass(aliasMethod, fields_, constructors_, methods_, aliasAnnotated, StdClassModifier.ABSTRACT, new DfMethod());
        stdcl_.addSuperStdTypes(getAnnotType());
        stdcl_.addSuperStdTypes(_stds.getCoreNames().getObjType());
        StringList params_ = new StringList(aliasObject_,aliasObject_);
        StandardMethod method_ = new StandardMethod(aliasInvoke, params_, aliasObject_, true, MethodModifier.FINAL,new StringList(params.getAliasMethod0Invoke0(),params.getAliasMethod0Invoke1()),new FctMethodInvoke(false, 0));
        methods_.add( method_);
        params_ = new StringList(aliasObject_,aliasObject_);
        method_ = new StandardMethod(aliasInvokeDirect, params_, aliasObject_, true, MethodModifier.FINAL,new StringList(params.getAliasMethod0InvokeDirect0(),params.getAliasMethod0InvokeDirect1()),new FctMethodInvoke(true, 0));
        methods_.add( method_);
        params_ = new StringList(aliasObject_,StringExpUtil.getPrettyArrayType(aliasObject_));
        method_ = new StandardMethod(aliasInvokeRef, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod0InvokeRef0(),params.getAliasMethod0InvokeRef1()),new FctMethodInvoke(false, 1));
        methods_.add( method_);
        params_ = new StringList(aliasObject_,StringExpUtil.getPrettyArrayType(aliasObject_));
        method_ = new StandardMethod(aliasInvokeDirectRef, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod0InvokeDirectRef0(),params.getAliasMethod0InvokeDirectRef1()),new FctMethodInvoke(true, 1));
        methods_.add( method_);
        params_ = new StringList(aliasObject_,StringExpUtil.getPrettyArrayType(aliasObject_));
        method_ = new StandardMethod(aliasInvokeRefAfter, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod0InvokeRef0(),params.getAliasMethod0InvokeRef1()),new FctMethodInvoke(false, 2));
        methods_.add( method_);
        params_ = new StringList(aliasObject_,StringExpUtil.getPrettyArrayType(aliasObject_));
        method_ = new StandardMethod(aliasInvokeDirectRefAfter, params_, aliasObject_, false, MethodModifier.FINAL,new StringList(params.getAliasMethod0InvokeDirectRef0(),params.getAliasMethod0InvokeDirectRef1()),new FctMethodInvoke(true, 2));
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
    }
    public void build(LgNames _stds) {
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        String aliasObject_ = _stds.getContent().getCoreNames().getAliasObject();
        String aliasString_ = _stds.getContent().getCharSeq().getAliasString();
        String aliasError_ = _stds.getContent().getCoreNames().getAliasError();
        StandardClass stdcl_ = new StandardClass(aliasFct, fields_, constructors_, methods_, aliasObject_, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(_stds.getCoreNames().getObjType());
        StringList params_ = new StringList(aliasObject_);
        StandardMethod method_ = new StandardMethod(aliasCall, params_, aliasObject_, true, MethodModifier.FINAL, new StringList(params.getAliasFct0Call0()), new FctLambdaCall(0));
        methods_.add( method_);
        fctTypeCall = method_;
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasCallRef, params_, aliasObject_, true, MethodModifier.FINAL, new StringList(params.getAliasFct0CallRef0()), new FctLambdaCall(1));
        methods_.add( method_);
        fctTypeCallRef = method_;
        params_ = new StringList(aliasObject_);
        method_ = new StandardMethod(aliasCallRefAfter, params_, aliasObject_, true, MethodModifier.FINAL, new StringList(params.getAliasFct0CallRefAfter0()), new FctLambdaCall(2));
        methods_.add( method_);
        fctTypeCallRefAfter = method_;
        params_ = new StringList();
        method_ = new StandardMethod(aliasMetaInfo, params_, aliasAnnotated, false, MethodModifier.FINAL, new FctLambdaMetaInfo());
        methods_.add( method_);
        fctTypeMeta = method_;
        params_ = new StringList();
        method_ = new StandardMethod(aliasInstance, params_, aliasObject_, false, MethodModifier.FINAL, new FctLambdaInstance());
        methods_.add( method_);
        fctTypeInstance = method_;
        fctType = stdcl_;
        _stds.getStandards().addEntry(aliasFct, stdcl_);
        buildAnnotated(_stds);
        buildAnnotatedCl(_stds);
        buildAnnotatedCtor(_stds);
        buildAnnotatedField(_stds);
        buildAnnotatedMethod(_stds);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasInvokeTarget, fields_, constructors_, methods_, aliasError_, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(_stds.getCoreNames().getErrType());
        stdcl_.addSuperStdTypes(_stds.getCoreNames().getObjType());
        _stds.getStandards().addEntry(aliasInvokeTarget, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasClassNotFoundError, fields_, constructors_, methods_, aliasError_, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(_stds.getCoreNames().getErrType());
        stdcl_.addSuperStdTypes(_stds.getCoreNames().getObjType());
        _stds.getStandards().addEntry(aliasClassNotFoundError, stdcl_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasAnnotationType, fields_, constructors_, methods_, aliasObject_, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(_stds.getCoreNames().getObjType());
        params_ = new StringList(aliasAnnotationType);
        method_ = new StandardMethod(aliasGetString, params_, aliasString_, false, MethodModifier.STATIC,new StringList(params.getAliasAnnotationType0GetString0()),new FctAnnotationToStr());
        methods_.add( method_);
        _stds.getStandards().addEntry(aliasAnnotationType, stdcl_);
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

    public String getAliasCallRef() {
        return aliasCallRef;
    }

    public void setAliasCallRef(String _v) {
        this.aliasCallRef = _v;
    }

    public String getAliasCallRefAfter() {
        return aliasCallRefAfter;
    }

    public void setAliasCallRefAfter(String _v) {
        this.aliasCallRefAfter = _v;
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

    public String getAliasInvokeRef() {
        return aliasInvokeRef;
    }

    public void setAliasInvokeRef(String _v) {
        this.aliasInvokeRef = _v;
    }

    public String getAliasInvokeDirectRef() {
        return aliasInvokeDirectRef;
    }

    public void setAliasInvokeDirectRef(String _v) {
        this.aliasInvokeDirectRef = _v;
    }

    public String getAliasInvokeRefAfter() {
        return aliasInvokeRefAfter;
    }

    public void setAliasInvokeRefAfter(String _v) {
        this.aliasInvokeRefAfter = _v;
    }

    public String getAliasInvokeDirectRefAfter() {
        return aliasInvokeDirectRefAfter;
    }

    public void setAliasInvokeDirectRefAfter(String _v) {
        this.aliasInvokeDirectRefAfter = _v;
    }

    public String getAliasNewInstanceRef() {
        return aliasNewInstanceRef;
    }

    public void setAliasNewInstanceRef(String _v) {
        this.aliasNewInstanceRef = _v;
    }

    public String getAliasNewInstanceRefAfter() {
        return aliasNewInstanceRefAfter;
    }

    public void setAliasNewInstanceRefAfter(String _v) {
        this.aliasNewInstanceRefAfter = _v;
    }

    public AliasParamReflection getParams() {
        return params;
    }

    public StandardClass getAnnotType() {
        return annotType;
    }

    public StandardType getFctType() {
        return fctType;
    }

    public StandardMethod getFctTypeInstance() {
        return fctTypeInstance;
    }

    public StandardMethod getFctTypeMeta() {
        return fctTypeMeta;
    }

    public StandardMethod getFctTypeCall() {
        return fctTypeCall;
    }

    public StandardMethod getFctTypeCallRef() {
        return fctTypeCallRef;
    }

    public StandardMethod getFctTypeCallRefAfter() {
        return fctTypeCallRefAfter;
    }
}
