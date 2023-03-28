package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringMap;

public final class AliasParamReflection {
    private static final String FCT_0_CALL_0="824";
    private static final String FCT_0_CALL_REF_0="824_";
    private static final String CLASS_TYPE_0_GET_CLASS_0="__________2009";
    private static final String CLASS_TYPE_0_FOR_NAME_0="__________2010";
    private static final String CLASS_TYPE_0_FOR_NAME_1="__________2011";
    private static final String CLASS_TYPE_1_FOR_NAME_0="__________2012";
    private static final String CLASS_TYPE_0_TRY_WRAP_0="__________2013";
    private static final String CLASS_TYPE_0_IS_INSTANCE_0="__________2014";
    private static final String CLASS_TYPE_0_IS_ASSIGNABLE_FROM_0="__________2015";
    private static final String CLASS_TYPE_0_DEFAULT_INSTANCE_0="__________2016";
    private static final String CLASS_TYPE_0_ENUM_VALUE_OF_0="__________2017";
    private static final String CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_0="__________2018";
    private static final String CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_1="__________2019";
    private static final String CLASS_TYPE_0_GET_DECLARED_FIELDS_0="__________2020";
    private static final String CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_0="__________2021";
    private static final String CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_1="__________2022";
    private static final String CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_2="__________2023";
    private static final String CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_3="__________2024";
    private static final String CLASS_TYPE_0_GET_DECLARED_METHODS_0="__________2025";
    private static final String CLASS_TYPE_0_GET_DECLARED_METHODS_1="__________2026";
    private static final String CLASS_TYPE_0_GET_DECLARED_METHODS_2="__________2027";
    private static final String CLASS_TYPE_0_GET_DECLARED_METHODS_3="__________2028";
    private static final String CLASS_TYPE_0_GET_DECLARED_EXPLICITS_0="__________2029";
    private static final String CLASS_TYPE_0_GET_DECLARED_IMPLICITS_0="__________2030";
    private static final String CLASS_TYPE_0_GET_DECLARED_BLOCKS_0="__________2031";
    private static final String CLASS_TYPE_0_GET_DECLARED_BLOCKS_1="__________2032";
    private static final String CLASS_TYPE_0_MAKE_GENERIC_0="__________2033";
    private static final String CLASS_TYPE_0_MAKE_REF_0="__________2034";
    private static final String CLASS_TYPE_0_MAKE_WILD_CARD_0="__________2035";
    private static final String CLASS_TYPE_0_GET_OPERATORS_0="__________2036";
    private static final String CLASS_TYPE_0_GET_OPERATORS_1="__________2037";
    private static final String CLASS_TYPE_0_GET_OPERATORS_2="__________2038";
    private static final String CLASS_TYPE_0_ARRAY_NEW_INSTANCE_0="__________2039";
    private static final String CLASS_TYPE_0_ARRAY_GET_LENGTH_0="__________2040";
    private static final String CLASS_TYPE_0_ARRAY_GET_0="__________2041";
    private static final String CLASS_TYPE_0_ARRAY_GET_1="__________2042";
    private static final String CLASS_TYPE_0_ARRAY_SET_0="__________2043";
    private static final String CLASS_TYPE_0_ARRAY_SET_1="__________2044";
    private static final String CLASS_TYPE_0_ARRAY_SET_2="__________2045";
    private static final String CONSTRUCTOR_0_NEW_INSTANCE_0="__________2046";
    private static final String CONSTRUCTOR_0_NEW_INSTANCE_REF_0="__________2046_";
    private static final String FIELD_0_GET_FIELD_0="__________2047";
    private static final String FIELD_0_SET_FIELD_0="__________2048";
    private static final String FIELD_0_SET_FIELD_1="__________2049";
    private static final String METHOD_0_INVOKE_0="__________2050";
    private static final String METHOD_0_INVOKE_1="__________2051";
    private static final String METHOD_0_INVOKE_DIRECT_0="__________2052";
    private static final String METHOD_0_INVOKE_DIRECT_1="__________2053";
    private static final String METHOD_0_INVOKE_REF_0="__________2050_";
    private static final String METHOD_0_INVOKE_REF_1="__________2051_";
    private static final String METHOD_0_INVOKE_DIRECT_REF_0="__________2052_";
    private static final String METHOD_0_INVOKE_DIRECT_REF_1="__________2053_";
    private static final String METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_NB_0="__________2054";
    private static final String METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0="__________2055";
    private static final String METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1="__________2056";
    private static final String METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_2="__________2057";
    private static final String METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0="__________2058";
    private static final String METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1="__________2059";
    private static final String METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0="__________2060";
    private static final String METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1="__________2061";
    private static final String METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0="__________2062";
    private static final String METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0="__________2063";
    private static final String METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1="__________2064";
    private static final String METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_2="__________2065";
    private static final String METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0="__________2066";
    private static final String METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1="__________2067";
    private static final String METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0="__________2068";
    private static final String METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1="__________2069";
    private static final String METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0="__________2070";
    private static final String ANNOTATION_TYPE_0_GET_STRING_0="__________2071";
    private static final String ANNOTATED_0_GET_ANNOTATIONS_0="__________2072";
    private static final String ANNOTATED_0_GET_ANNOTATIONS_SUPP_0="__________2073";
    private static final String ANNOTATED_0_GET_ANNOTATIONS_PARAMETERS_0="__________2074";
    private static final String ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_0="__________2075";
    private static final String ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_1="__________2076";
    private static final String ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_2="__________2077";
    private static final String ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_3="__________2078";
    private static final String ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_0="__________2079";
    private static final String ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_1="__________2080";
    private static final String ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_2="__________2081";
    private static final String ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_3="__________2082";

    private String aliasFct0Call0="a";
    private String aliasFct0CallRef0="a";
    private String aliasClassType0GetClass0="a";
    private String aliasClassType0ForName0="a";
    private String aliasClassType0ForName1="b";
    private String aliasClassType1ForName0="a";
    private String aliasClassType0TryWrap0="a";
    private String aliasClassType0IsInstance0="a";
    private String aliasClassType0IsAssignableFrom0="a";
    private String aliasClassType0DefaultInstance0="a";
    private String aliasClassType0EnumValueOf0="a";
    private String aliasClassType0GetDeclaredConstructors0="a";
    private String aliasClassType0GetDeclaredConstructors1="b";
    private String aliasClassType0GetDeclaredFields0="a";
    private String aliasClassType0GetDeclaredStaticMethods0="a";
    private String aliasClassType0GetDeclaredStaticMethods1="b";
    private String aliasClassType0GetDeclaredStaticMethods2="c";
    private String aliasClassType0GetDeclaredStaticMethods3="d";
    private String aliasClassType0GetDeclaredMethods0="a";
    private String aliasClassType0GetDeclaredMethods1="b";
    private String aliasClassType0GetDeclaredMethods2="c";
    private String aliasClassType0GetDeclaredMethods3="d";
    private String aliasClassType0GetDeclaredExplicits0="a";
    private String aliasClassType0GetDeclaredImplicits0="a";
    private String aliasClassType0GetDeclaredBlocks0="a";
    private String aliasClassType0GetDeclaredBlocks1="b";
    private String aliasClassType0MakeGeneric0="a";
    private String aliasClassType0MakeWildCard0="a";
    private String aliasClassType0MakeRef0="a";
    private String aliasClassType0GetOperators0="a";
    private String aliasClassType0GetOperators1="b";
    private String aliasClassType0GetOperators2="c";
    private String aliasClassType0ArrayNewInstance0="a";
    private String aliasClassType0ArrayGetLength0="a";
    private String aliasClassType0ArrayGet0="a";
    private String aliasClassType0ArrayGet1="b";
    private String aliasClassType0ArraySet0="a";
    private String aliasClassType0ArraySet1="b";
    private String aliasClassType0ArraySet2="c";
    private String aliasConstructor0NewInstance0="a";
    private String aliasConstructor0NewInstanceRef0="a";
    private String aliasField0GetField0="a";
    private String aliasField0SetField0="a";
    private String aliasField0SetField1="b";
    private String aliasMethod0Invoke0="a";
    private String aliasMethod0Invoke1="b";
    private String aliasMethod0InvokeDirect0="a";
    private String aliasMethod0InvokeDirect1="b";
    private String aliasMethod0InvokeRef0="a";
    private String aliasMethod0InvokeRef1="b";
    private String aliasMethod0InvokeDirectRef0="a";
    private String aliasMethod0InvokeDirectRef1="b";
    private String aliasMethod0GetDeclaredAnonymousLambdaLocalVarsNb0="a";
    private String aliasMethod0GetDeclaredAnonymousLambdaLocalVars0="a";
    private String aliasMethod0GetDeclaredAnonymousLambdaLocalVars1="b";
    private String aliasMethod0GetDeclaredAnonymousLambdaLocalVars2="c";
    private String aliasMethod1GetDeclaredAnonymousLambdaLocalVars0="a";
    private String aliasMethod1GetDeclaredAnonymousLambdaLocalVars1="b";
    private String aliasMethod2GetDeclaredAnonymousLambdaLocalVars0="a";
    private String aliasMethod2GetDeclaredAnonymousLambdaLocalVars1="b";
    private String aliasMethod3GetDeclaredAnonymousLambdaLocalVars0="a";
    private String aliasMethod0GetDeclaredAnonymousLambdaLoopVars0="a";
    private String aliasMethod0GetDeclaredAnonymousLambdaLoopVars1="b";
    private String aliasMethod0GetDeclaredAnonymousLambdaLoopVars2="c";
    private String aliasMethod1GetDeclaredAnonymousLambdaLoopVars0="a";
    private String aliasMethod1GetDeclaredAnonymousLambdaLoopVars1="b";
    private String aliasMethod2GetDeclaredAnonymousLambdaLoopVars0="a";
    private String aliasMethod2GetDeclaredAnonymousLambdaLoopVars1="b";
    private String aliasMethod3GetDeclaredAnonymousLambdaLoopVars0="a";
    private String aliasAnnotationType0GetString0="a";
    private String aliasAnnotated0GetAnnotations0="a";
    private String aliasAnnotated0GetAnnotationsSupp0="a";
    private String aliasAnnotated0GetAnnotationsParameters0="a";
    private String aliasAnnotated0GetDeclaredAnonymousLambda0="a";
    private String aliasAnnotated0GetDeclaredAnonymousLambda1="b";
    private String aliasAnnotated0GetDeclaredAnonymousLambda2="c";
    private String aliasAnnotated0GetDeclaredAnonymousLambda3="d";
    private String aliasAnnotated0GetDeclaredSwitchMethods0="a";
    private String aliasAnnotated0GetDeclaredSwitchMethods1="b";
    private String aliasAnnotated0GetDeclaredSwitchMethods2="c";
    private String aliasAnnotated0GetDeclaredSwitchMethods3="d";

    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setAliasFct0Call0(LgNamesContent.get(_util,_cust,_mapping.getVal(FCT_0_CALL_0)));
        setAliasFct0CallRef0(LgNamesContent.get(_util,_cust,_mapping.getVal(FCT_0_CALL_REF_0)));
        setAliasClassType0GetClass0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_GET_CLASS_0)));
        setAliasClassType0ForName0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_FOR_NAME_0)));
        setAliasClassType0ForName1(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_FOR_NAME_1)));
        setAliasClassType1ForName0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_1_FOR_NAME_0)));
        setAliasClassType0IsInstance0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_IS_INSTANCE_0)));
        setAliasClassType0TryWrap0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_TRY_WRAP_0)));
        setAliasClassType0IsAssignableFrom0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_IS_ASSIGNABLE_FROM_0)));
        setAliasClassType0DefaultInstance0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_DEFAULT_INSTANCE_0)));
        setAliasClassType0EnumValueOf0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_ENUM_VALUE_OF_0)));
        setAliasClassType0GetDeclaredConstructors0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_0)));
        setAliasClassType0GetDeclaredConstructors1(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_1)));
        setAliasClassType0GetDeclaredFields0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_FIELDS_0)));
        setAliasClassType0GetDeclaredStaticMethods0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_0)));
        setAliasClassType0GetDeclaredStaticMethods1(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_1)));
        setAliasClassType0GetDeclaredStaticMethods2(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_2)));
        setAliasClassType0GetDeclaredStaticMethods3(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_3)));
        setAliasClassType0GetDeclaredMethods0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_METHODS_0)));
        setAliasClassType0GetDeclaredMethods1(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_METHODS_1)));
        setAliasClassType0GetDeclaredMethods2(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_METHODS_2)));
        setAliasClassType0GetDeclaredMethods3(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_METHODS_3)));
        setAliasClassType0GetDeclaredExplicits0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_EXPLICITS_0)));
        setAliasClassType0GetDeclaredImplicits0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_IMPLICITS_0)));
        setAliasClassType0GetDeclaredBlocks0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_BLOCKS_0)));
        setAliasClassType0GetDeclaredBlocks1(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_BLOCKS_1)));
        setAliasClassType0MakeGeneric0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_MAKE_GENERIC_0)));
        setAliasClassType0MakeWildCard0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_MAKE_WILD_CARD_0)));
        setAliasClassType0MakeRef0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_MAKE_REF_0)));
        setAliasClassType0GetOperators0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_GET_OPERATORS_0)));
        setAliasClassType0GetOperators1(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_GET_OPERATORS_1)));
        setAliasClassType0GetOperators2(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_GET_OPERATORS_2)));
        setAliasClassType0ArrayNewInstance0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_ARRAY_NEW_INSTANCE_0)));
        setAliasClassType0ArrayGetLength0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_ARRAY_GET_LENGTH_0)));
        setAliasClassType0ArrayGet0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_ARRAY_GET_0)));
        setAliasClassType0ArrayGet1(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_ARRAY_GET_1)));
        setAliasClassType0ArraySet0(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_ARRAY_SET_0)));
        setAliasClassType0ArraySet1(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_ARRAY_SET_1)));
        setAliasClassType0ArraySet2(LgNamesContent.get(_util,_cust,_mapping.getVal(CLASS_TYPE_0_ARRAY_SET_2)));
        setAliasConstructor0NewInstance0(LgNamesContent.get(_util,_cust,_mapping.getVal(CONSTRUCTOR_0_NEW_INSTANCE_0)));
        setAliasConstructor0NewInstanceRef0(LgNamesContent.get(_util,_cust,_mapping.getVal(CONSTRUCTOR_0_NEW_INSTANCE_REF_0)));
        setAliasField0GetField0(LgNamesContent.get(_util,_cust,_mapping.getVal(FIELD_0_GET_FIELD_0)));
        setAliasField0SetField0(LgNamesContent.get(_util,_cust,_mapping.getVal(FIELD_0_SET_FIELD_0)));
        setAliasField0SetField1(LgNamesContent.get(_util,_cust,_mapping.getVal(FIELD_0_SET_FIELD_1)));
        setAliasMethod0Invoke0(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_0_INVOKE_0)));
        setAliasMethod0Invoke1(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_0_INVOKE_1)));
        setAliasMethod0InvokeDirect0(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_0_INVOKE_DIRECT_0)));
        setAliasMethod0InvokeDirect1(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_0_INVOKE_DIRECT_1)));
        setAliasMethod0InvokeRef0(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_0_INVOKE_REF_0)));
        setAliasMethod0InvokeRef1(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_0_INVOKE_REF_1)));
        setAliasMethod0InvokeDirectRef0(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_0_INVOKE_DIRECT_REF_0)));
        setAliasMethod0InvokeDirectRef1(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_0_INVOKE_DIRECT_REF_1)));
        setAliasMethod0GetDeclaredAnonymousLambdaLocalVarsNb0(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_NB_0)));
        setAliasMethod0GetDeclaredAnonymousLambdaLocalVars0(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0)));
        setAliasMethod0GetDeclaredAnonymousLambdaLocalVars1(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1)));
        setAliasMethod0GetDeclaredAnonymousLambdaLocalVars2(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_2)));
        setAliasMethod1GetDeclaredAnonymousLambdaLocalVars0(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0)));
        setAliasMethod1GetDeclaredAnonymousLambdaLocalVars1(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1)));
        setAliasMethod2GetDeclaredAnonymousLambdaLocalVars0(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0)));
        setAliasMethod2GetDeclaredAnonymousLambdaLocalVars1(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1)));
        setAliasMethod3GetDeclaredAnonymousLambdaLocalVars0(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0)));
        setAliasMethod0GetDeclaredAnonymousLambdaLoopVars0(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0)));
        setAliasMethod0GetDeclaredAnonymousLambdaLoopVars1(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1)));
        setAliasMethod0GetDeclaredAnonymousLambdaLoopVars2(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_2)));
        setAliasMethod1GetDeclaredAnonymousLambdaLoopVars0(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0)));
        setAliasMethod1GetDeclaredAnonymousLambdaLoopVars1(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1)));
        setAliasMethod2GetDeclaredAnonymousLambdaLoopVars0(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0)));
        setAliasMethod2GetDeclaredAnonymousLambdaLoopVars1(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1)));
        setAliasMethod3GetDeclaredAnonymousLambdaLoopVars0(LgNamesContent.get(_util,_cust,_mapping.getVal(METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0)));
        setAliasAnnotationType0GetString0(LgNamesContent.get(_util,_cust,_mapping.getVal(ANNOTATION_TYPE_0_GET_STRING_0)));
        setAliasAnnotated0GetAnnotations0(LgNamesContent.get(_util,_cust,_mapping.getVal(ANNOTATED_0_GET_ANNOTATIONS_0)));
        setAliasAnnotated0GetAnnotationsSupp0(LgNamesContent.get(_util,_cust,_mapping.getVal(ANNOTATED_0_GET_ANNOTATIONS_SUPP_0)));
        setAliasAnnotated0GetAnnotationsParameters0(LgNamesContent.get(_util,_cust,_mapping.getVal(ANNOTATED_0_GET_ANNOTATIONS_PARAMETERS_0)));
        setAliasAnnotated0GetDeclaredAnonymousLambda0(LgNamesContent.get(_util,_cust,_mapping.getVal(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_0)));
        setAliasAnnotated0GetDeclaredAnonymousLambda1(LgNamesContent.get(_util,_cust,_mapping.getVal(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_1)));
        setAliasAnnotated0GetDeclaredAnonymousLambda2(LgNamesContent.get(_util,_cust,_mapping.getVal(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_2)));
        setAliasAnnotated0GetDeclaredAnonymousLambda3(LgNamesContent.get(_util,_cust,_mapping.getVal(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_3)));
        setAliasAnnotated0GetDeclaredSwitchMethods0(LgNamesContent.get(_util,_cust,_mapping.getVal(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_0)));
        setAliasAnnotated0GetDeclaredSwitchMethods1(LgNamesContent.get(_util,_cust,_mapping.getVal(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_1)));
        setAliasAnnotated0GetDeclaredSwitchMethods2(LgNamesContent.get(_util,_cust,_mapping.getVal(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_2)));
        setAliasAnnotated0GetDeclaredSwitchMethods3(LgNamesContent.get(_util,_cust,_mapping.getVal(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_3)));
    }
    public static void en(TranslationsFile _en){
        _en.add(FCT_0_CALL_0,"Fct0Call0=a");
        _en.add(FCT_0_CALL_REF_0,"Fct0CallRef0=a");
        _en.add(CLASS_TYPE_0_GET_CLASS_0,"ClassType0GetClass0=a");
        _en.add(CLASS_TYPE_0_FOR_NAME_0,"ClassType0ForName0=a");
        _en.add(CLASS_TYPE_0_FOR_NAME_1,"ClassType0ForName1=b");
        _en.add(CLASS_TYPE_1_FOR_NAME_0,"ClassType1ForName0=a");
        _en.add(CLASS_TYPE_0_TRY_WRAP_0,"ClassType0TryWrap0=a");
        _en.add(CLASS_TYPE_0_IS_INSTANCE_0,"ClassType0IsInstance0=a");
        _en.add(CLASS_TYPE_0_IS_ASSIGNABLE_FROM_0,"ClassType0IsAssignableFrom0=a");
        _en.add(CLASS_TYPE_0_DEFAULT_INSTANCE_0,"ClassType0DefaultInstance0=a");
        _en.add(CLASS_TYPE_0_ENUM_VALUE_OF_0,"ClassType0EnumValueOf0=a");
        _en.add(CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_0,"ClassType0GetDeclaredConstructors0=a");
        _en.add(CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_1,"ClassType0GetDeclaredConstructors1=b");
        _en.add(CLASS_TYPE_0_GET_DECLARED_FIELDS_0,"ClassType0GetDeclaredFields0=a");
        _en.add(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_0,"ClassType0GetDeclaredStaticMethods0=a");
        _en.add(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_1,"ClassType0GetDeclaredStaticMethods1=b");
        _en.add(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_2,"ClassType0GetDeclaredStaticMethods2=c");
        _en.add(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_3,"ClassType0GetDeclaredStaticMethods3=d");
        _en.add(CLASS_TYPE_0_GET_DECLARED_METHODS_0,"ClassType0GetDeclaredMethods0=a");
        _en.add(CLASS_TYPE_0_GET_DECLARED_METHODS_1,"ClassType0GetDeclaredMethods1=b");
        _en.add(CLASS_TYPE_0_GET_DECLARED_METHODS_2,"ClassType0GetDeclaredMethods2=c");
        _en.add(CLASS_TYPE_0_GET_DECLARED_METHODS_3,"ClassType0GetDeclaredMethods3=d");
        _en.add(CLASS_TYPE_0_GET_DECLARED_EXPLICITS_0,"ClassType0GetDeclaredExplicits0=a");
        _en.add(CLASS_TYPE_0_GET_DECLARED_IMPLICITS_0,"ClassType0GetDeclaredImplicits0=a");
        _en.add(CLASS_TYPE_0_GET_DECLARED_BLOCKS_0,"ClassType0GetDeclaredBlocks0=a");
        _en.add(CLASS_TYPE_0_GET_DECLARED_BLOCKS_1,"ClassType0GetDeclaredBlocks1=b");
        _en.add(CLASS_TYPE_0_MAKE_GENERIC_0,"ClassType0MakeGeneric0=a");
        _en.add(CLASS_TYPE_0_MAKE_REF_0,"ClassType0MakeRef0=a");
        _en.add(CLASS_TYPE_0_MAKE_WILD_CARD_0,"ClassType0MakeWildCard0=a");
        _en.add(CLASS_TYPE_0_GET_OPERATORS_0,"ClassType0GetOperators0=a");
        _en.add(CLASS_TYPE_0_GET_OPERATORS_1,"ClassType0GetOperators1=b");
        _en.add(CLASS_TYPE_0_GET_OPERATORS_2,"ClassType0GetOperators2=c");
        _en.add(CLASS_TYPE_0_ARRAY_NEW_INSTANCE_0,"ClassType0ArrayNewInstance0=a");
        _en.add(CLASS_TYPE_0_ARRAY_GET_LENGTH_0,"ClassType0ArrayGetLength0=a");
        _en.add(CLASS_TYPE_0_ARRAY_GET_0,"ClassType0ArrayGet0=a");
        _en.add(CLASS_TYPE_0_ARRAY_GET_1,"ClassType0ArrayGet1=b");
        _en.add(CLASS_TYPE_0_ARRAY_SET_0,"ClassType0ArraySet0=a");
        _en.add(CLASS_TYPE_0_ARRAY_SET_1,"ClassType0ArraySet1=b");
        _en.add(CLASS_TYPE_0_ARRAY_SET_2,"ClassType0ArraySet2=c");
        _en.add(CONSTRUCTOR_0_NEW_INSTANCE_0,"Constructor0NewInstance0=a");
        _en.add(CONSTRUCTOR_0_NEW_INSTANCE_REF_0,"Constructor0NewInstanceRef0=a");
        _en.add(FIELD_0_GET_FIELD_0,"Field0GetField0=a");
        _en.add(FIELD_0_SET_FIELD_0,"Field0SetField0=a");
        _en.add(FIELD_0_SET_FIELD_1,"Field0SetField1=b");
        _en.add(METHOD_0_INVOKE_0,"Method0Invoke0=a");
        _en.add(METHOD_0_INVOKE_1,"Method0Invoke1=b");
        _en.add(METHOD_0_INVOKE_DIRECT_0,"Method0InvokeDirect0=a");
        _en.add(METHOD_0_INVOKE_DIRECT_1,"Method0InvokeDirect1=b");
        _en.add(METHOD_0_INVOKE_REF_0,"Method0InvokeRef0=a");
        _en.add(METHOD_0_INVOKE_REF_1,"Method0InvokeRef1=b");
        _en.add(METHOD_0_INVOKE_DIRECT_REF_0,"Method0InvokeDirectRef0=a");
        _en.add(METHOD_0_INVOKE_DIRECT_REF_1,"Method0InvokeDirectRef1=b");
        _en.add(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_NB_0,"Method0GetDeclaredAnonymousLambdaLocalVarsNb0=a");
        _en.add(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0,"Method0GetDeclaredAnonymousLambdaLocalVars0=a");
        _en.add(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1,"Method0GetDeclaredAnonymousLambdaLocalVars1=b");
        _en.add(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_2,"Method0GetDeclaredAnonymousLambdaLocalVars2=c");
        _en.add(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0,"Method1GetDeclaredAnonymousLambdaLocalVars0=a");
        _en.add(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1,"Method1GetDeclaredAnonymousLambdaLocalVars1=b");
        _en.add(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0,"Method2GetDeclaredAnonymousLambdaLocalVars0=a");
        _en.add(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1,"Method2GetDeclaredAnonymousLambdaLocalVars1=b");
        _en.add(METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0,"Method3GetDeclaredAnonymousLambdaLocalVars0=a");
        _en.add(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0,"Method0GetDeclaredAnonymousLambdaLoopVars0=a");
        _en.add(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1,"Method0GetDeclaredAnonymousLambdaLoopVars1=b");
        _en.add(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_2,"Method0GetDeclaredAnonymousLambdaLoopVars2=c");
        _en.add(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0,"Method1GetDeclaredAnonymousLambdaLoopVars0=a");
        _en.add(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1,"Method1GetDeclaredAnonymousLambdaLoopVars1=b");
        _en.add(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0,"Method2GetDeclaredAnonymousLambdaLoopVars0=a");
        _en.add(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1,"Method2GetDeclaredAnonymousLambdaLoopVars1=b");
        _en.add(METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0,"Method3GetDeclaredAnonymousLambdaLoopVars0=a");
        _en.add(ANNOTATION_TYPE_0_GET_STRING_0,"AnnotationType0GetString0=a");
        _en.add(ANNOTATED_0_GET_ANNOTATIONS_0,"Annotated0GetAnnotations0=a");
        _en.add(ANNOTATED_0_GET_ANNOTATIONS_SUPP_0,"Annotated0GetAnnotationsSupp0=a");
        _en.add(ANNOTATED_0_GET_ANNOTATIONS_PARAMETERS_0,"Annotated0GetAnnotationsParameters0=a");
        _en.add(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_0,"Annotated0GetDeclaredAnonymousLambda0=a");
        _en.add(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_1,"Annotated0GetDeclaredAnonymousLambda1=b");
        _en.add(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_2,"Annotated0GetDeclaredAnonymousLambda2=c");
        _en.add(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_3,"Annotated0GetDeclaredAnonymousLambda3=d");
        _en.add(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_0,"Annotated0GetDeclaredSwitchMethods0=a");
        _en.add(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_1,"Annotated0GetDeclaredSwitchMethods1=b");
        _en.add(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_2,"Annotated0GetDeclaredSwitchMethods2=c");
        _en.add(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_3,"Annotated0GetDeclaredSwitchMethods3=d");
    }
    public static void fr(TranslationsFile _fr){
        _fr.add(FCT_0_CALL_0,"Fct0Call0=a");
        _fr.add(FCT_0_CALL_REF_0,"Fct0CallRef0=a");
        _fr.add(CLASS_TYPE_0_GET_CLASS_0,"ClassType0GetClass0=a");
        _fr.add(CLASS_TYPE_0_FOR_NAME_0,"ClassType0ForName0=a");
        _fr.add(CLASS_TYPE_0_FOR_NAME_1,"ClassType0ForName1=b");
        _fr.add(CLASS_TYPE_1_FOR_NAME_0,"ClassType1ForName0=a");
        _fr.add(CLASS_TYPE_0_TRY_WRAP_0,"ClassType0TryWrap0=a");
        _fr.add(CLASS_TYPE_0_IS_INSTANCE_0,"ClassType0IsInstance0=a");
        _fr.add(CLASS_TYPE_0_IS_ASSIGNABLE_FROM_0,"ClassType0IsAssignableFrom0=a");
        _fr.add(CLASS_TYPE_0_DEFAULT_INSTANCE_0,"ClassType0DefaultInstance0=a");
        _fr.add(CLASS_TYPE_0_ENUM_VALUE_OF_0,"ClassType0EnumValueOf0=a");
        _fr.add(CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_0,"ClassType0GetDeclaredConstructors0=a");
        _fr.add(CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_1,"ClassType0GetDeclaredConstructors1=b");
        _fr.add(CLASS_TYPE_0_GET_DECLARED_FIELDS_0,"ClassType0GetDeclaredFields0=a");
        _fr.add(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_0,"ClassType0GetDeclaredStaticMethods0=a");
        _fr.add(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_1,"ClassType0GetDeclaredStaticMethods1=b");
        _fr.add(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_2,"ClassType0GetDeclaredStaticMethods2=c");
        _fr.add(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_3,"ClassType0GetDeclaredStaticMethods3=d");
        _fr.add(CLASS_TYPE_0_GET_DECLARED_METHODS_0,"ClassType0GetDeclaredMethods0=a");
        _fr.add(CLASS_TYPE_0_GET_DECLARED_METHODS_1,"ClassType0GetDeclaredMethods1=b");
        _fr.add(CLASS_TYPE_0_GET_DECLARED_METHODS_2,"ClassType0GetDeclaredMethods2=c");
        _fr.add(CLASS_TYPE_0_GET_DECLARED_METHODS_3,"ClassType0GetDeclaredMethods3=d");
        _fr.add(CLASS_TYPE_0_GET_DECLARED_EXPLICITS_0,"ClassType0GetDeclaredExplicits0=a");
        _fr.add(CLASS_TYPE_0_GET_DECLARED_IMPLICITS_0,"ClassType0GetDeclaredImplicits0=a");
        _fr.add(CLASS_TYPE_0_GET_DECLARED_BLOCKS_0,"ClassType0GetDeclaredBlocks0=a");
        _fr.add(CLASS_TYPE_0_GET_DECLARED_BLOCKS_1,"ClassType0GetDeclaredBlocks1=b");
        _fr.add(CLASS_TYPE_0_MAKE_GENERIC_0,"ClassType0MakeGeneric0=a");
        _fr.add(CLASS_TYPE_0_MAKE_REF_0,"ClassType0MakeRef0=a");
        _fr.add(CLASS_TYPE_0_MAKE_WILD_CARD_0,"ClassType0MakeWildCard0=a");
        _fr.add(CLASS_TYPE_0_GET_OPERATORS_0,"ClassType0GetOperators0=a");
        _fr.add(CLASS_TYPE_0_GET_OPERATORS_1,"ClassType0GetOperators1=b");
        _fr.add(CLASS_TYPE_0_GET_OPERATORS_2,"ClassType0GetOperators2=c");
        _fr.add(CLASS_TYPE_0_ARRAY_NEW_INSTANCE_0,"ClassType0ArrayNewInstance0=a");
        _fr.add(CLASS_TYPE_0_ARRAY_GET_LENGTH_0,"ClassType0ArrayGetLength0=a");
        _fr.add(CLASS_TYPE_0_ARRAY_GET_0,"ClassType0ArrayGet0=a");
        _fr.add(CLASS_TYPE_0_ARRAY_GET_1,"ClassType0ArrayGet1=b");
        _fr.add(CLASS_TYPE_0_ARRAY_SET_0,"ClassType0ArraySet0=a");
        _fr.add(CLASS_TYPE_0_ARRAY_SET_1,"ClassType0ArraySet1=b");
        _fr.add(CLASS_TYPE_0_ARRAY_SET_2,"ClassType0ArraySet2=c");
        _fr.add(CONSTRUCTOR_0_NEW_INSTANCE_0,"Constructor0NewInstance0=a");
        _fr.add(CONSTRUCTOR_0_NEW_INSTANCE_REF_0,"Constructor0NewInstanceRef0=a");
        _fr.add(FIELD_0_GET_FIELD_0,"Field0GetField0=a");
        _fr.add(FIELD_0_SET_FIELD_0,"Field0SetField0=a");
        _fr.add(FIELD_0_SET_FIELD_1,"Field0SetField1=b");
        _fr.add(METHOD_0_INVOKE_0,"Method0Invoke0=a");
        _fr.add(METHOD_0_INVOKE_1,"Method0Invoke1=b");
        _fr.add(METHOD_0_INVOKE_DIRECT_0,"Method0InvokeDirect0=a");
        _fr.add(METHOD_0_INVOKE_DIRECT_1,"Method0InvokeDirect1=b");
        _fr.add(METHOD_0_INVOKE_REF_0,"Method0InvokeRef0=a");
        _fr.add(METHOD_0_INVOKE_REF_1,"Method0InvokeRef1=b");
        _fr.add(METHOD_0_INVOKE_DIRECT_REF_0,"Method0InvokeDirectRef0=a");
        _fr.add(METHOD_0_INVOKE_DIRECT_REF_1,"Method0InvokeDirectRef1=b");
        _fr.add(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_NB_0,"Method0GetDeclaredAnonymousLambdaLocalVarsNb0=a");
        _fr.add(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0,"Method0GetDeclaredAnonymousLambdaLocalVars0=a");
        _fr.add(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1,"Method0GetDeclaredAnonymousLambdaLocalVars1=b");
        _fr.add(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_2,"Method0GetDeclaredAnonymousLambdaLocalVars2=c");
        _fr.add(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0,"Method1GetDeclaredAnonymousLambdaLocalVars0=a");
        _fr.add(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1,"Method1GetDeclaredAnonymousLambdaLocalVars1=b");
        _fr.add(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0,"Method2GetDeclaredAnonymousLambdaLocalVars0=a");
        _fr.add(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1,"Method2GetDeclaredAnonymousLambdaLocalVars1=b");
        _fr.add(METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0,"Method3GetDeclaredAnonymousLambdaLocalVars0=a");
        _fr.add(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0,"Method0GetDeclaredAnonymousLambdaLoopVars0=a");
        _fr.add(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1,"Method0GetDeclaredAnonymousLambdaLoopVars1=b");
        _fr.add(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_2,"Method0GetDeclaredAnonymousLambdaLoopVars2=c");
        _fr.add(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0,"Method1GetDeclaredAnonymousLambdaLoopVars0=a");
        _fr.add(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1,"Method1GetDeclaredAnonymousLambdaLoopVars1=b");
        _fr.add(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0,"Method2GetDeclaredAnonymousLambdaLoopVars0=a");
        _fr.add(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1,"Method2GetDeclaredAnonymousLambdaLoopVars1=b");
        _fr.add(METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0,"Method3GetDeclaredAnonymousLambdaLoopVars0=a");
        _fr.add(ANNOTATION_TYPE_0_GET_STRING_0,"AnnotationType0GetString0=a");
        _fr.add(ANNOTATED_0_GET_ANNOTATIONS_0,"Annotated0GetAnnotations0=a");
        _fr.add(ANNOTATED_0_GET_ANNOTATIONS_SUPP_0,"Annotated0GetAnnotationsSupp0=a");
        _fr.add(ANNOTATED_0_GET_ANNOTATIONS_PARAMETERS_0,"Annotated0GetAnnotationsParameters0=a");
        _fr.add(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_0,"Annotated0GetDeclaredAnonymousLambda0=a");
        _fr.add(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_1,"Annotated0GetDeclaredAnonymousLambda1=b");
        _fr.add(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_2,"Annotated0GetDeclaredAnonymousLambda2=c");
        _fr.add(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_3,"Annotated0GetDeclaredAnonymousLambda3=d");
        _fr.add(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_0,"Annotated0GetDeclaredSwitchMethods0=a");
        _fr.add(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_1,"Annotated0GetDeclaredSwitchMethods1=b");
        _fr.add(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_2,"Annotated0GetDeclaredSwitchMethods2=c");
        _fr.add(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_3,"Annotated0GetDeclaredSwitchMethods3=d");
    }
    public static void mapping(StringMap<String> _m){
        _m.addEntry(FCT_0_CALL_0,"Fct0Call0");
        _m.addEntry(FCT_0_CALL_REF_0,"Fct0CallRef0");
        _m.addEntry(CLASS_TYPE_0_GET_CLASS_0,"ClassType0GetClass0");
        _m.addEntry(CLASS_TYPE_0_FOR_NAME_0,"ClassType0ForName0");
        _m.addEntry(CLASS_TYPE_0_FOR_NAME_1,"ClassType0ForName1");
        _m.addEntry(CLASS_TYPE_1_FOR_NAME_0,"ClassType1ForName0");
        _m.addEntry(CLASS_TYPE_0_TRY_WRAP_0,"ClassType0TryWrap0");
        _m.addEntry(CLASS_TYPE_0_IS_INSTANCE_0,"ClassType0IsInstance0");
        _m.addEntry(CLASS_TYPE_0_IS_ASSIGNABLE_FROM_0,"ClassType0IsAssignableFrom0");
        _m.addEntry(CLASS_TYPE_0_DEFAULT_INSTANCE_0,"ClassType0DefaultInstance0");
        _m.addEntry(CLASS_TYPE_0_ENUM_VALUE_OF_0,"ClassType0EnumValueOf0");
        _m.addEntry(CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_0,"ClassType0GetDeclaredConstructors0");
        _m.addEntry(CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_1,"ClassType0GetDeclaredConstructors1");
        _m.addEntry(CLASS_TYPE_0_GET_DECLARED_FIELDS_0,"ClassType0GetDeclaredFields0");
        _m.addEntry(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_0,"ClassType0GetDeclaredStaticMethods0");
        _m.addEntry(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_1,"ClassType0GetDeclaredStaticMethods1");
        _m.addEntry(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_2,"ClassType0GetDeclaredStaticMethods2");
        _m.addEntry(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_3,"ClassType0GetDeclaredStaticMethods3");
        _m.addEntry(CLASS_TYPE_0_GET_DECLARED_METHODS_0,"ClassType0GetDeclaredMethods0");
        _m.addEntry(CLASS_TYPE_0_GET_DECLARED_METHODS_1,"ClassType0GetDeclaredMethods1");
        _m.addEntry(CLASS_TYPE_0_GET_DECLARED_METHODS_2,"ClassType0GetDeclaredMethods2");
        _m.addEntry(CLASS_TYPE_0_GET_DECLARED_METHODS_3,"ClassType0GetDeclaredMethods3");
        _m.addEntry(CLASS_TYPE_0_GET_DECLARED_EXPLICITS_0,"ClassType0GetDeclaredExplicits0");
        _m.addEntry(CLASS_TYPE_0_GET_DECLARED_IMPLICITS_0,"ClassType0GetDeclaredImplicits0");
        _m.addEntry(CLASS_TYPE_0_GET_DECLARED_BLOCKS_0,"ClassType0GetDeclaredBlocks0");
        _m.addEntry(CLASS_TYPE_0_GET_DECLARED_BLOCKS_1,"ClassType0GetDeclaredBlocks1");
        _m.addEntry(CLASS_TYPE_0_MAKE_GENERIC_0,"ClassType0MakeGeneric0");
        _m.addEntry(CLASS_TYPE_0_MAKE_REF_0,"ClassType0MakeRef0");
        _m.addEntry(CLASS_TYPE_0_MAKE_WILD_CARD_0,"ClassType0MakeWildCard0");
        _m.addEntry(CLASS_TYPE_0_GET_OPERATORS_0,"ClassType0GetOperators0");
        _m.addEntry(CLASS_TYPE_0_GET_OPERATORS_1,"ClassType0GetOperators1");
        _m.addEntry(CLASS_TYPE_0_GET_OPERATORS_2,"ClassType0GetOperators2");
        _m.addEntry(CLASS_TYPE_0_ARRAY_NEW_INSTANCE_0,"ClassType0ArrayNewInstance0");
        _m.addEntry(CLASS_TYPE_0_ARRAY_GET_LENGTH_0,"ClassType0ArrayGetLength0");
        _m.addEntry(CLASS_TYPE_0_ARRAY_GET_0,"ClassType0ArrayGet0");
        _m.addEntry(CLASS_TYPE_0_ARRAY_GET_1,"ClassType0ArrayGet1");
        _m.addEntry(CLASS_TYPE_0_ARRAY_SET_0,"ClassType0ArraySet0");
        _m.addEntry(CLASS_TYPE_0_ARRAY_SET_1,"ClassType0ArraySet1");
        _m.addEntry(CLASS_TYPE_0_ARRAY_SET_2,"ClassType0ArraySet2");
        _m.addEntry(CONSTRUCTOR_0_NEW_INSTANCE_0,"Constructor0NewInstance0");
        _m.addEntry(CONSTRUCTOR_0_NEW_INSTANCE_REF_0,"Constructor0NewInstanceRef0");
        _m.addEntry(FIELD_0_GET_FIELD_0,"Field0GetField0");
        _m.addEntry(FIELD_0_SET_FIELD_0,"Field0SetField0");
        _m.addEntry(FIELD_0_SET_FIELD_1,"Field0SetField1");
        _m.addEntry(METHOD_0_INVOKE_0,"Method0Invoke0");
        _m.addEntry(METHOD_0_INVOKE_1,"Method0Invoke1");
        _m.addEntry(METHOD_0_INVOKE_DIRECT_0,"Method0InvokeDirect0");
        _m.addEntry(METHOD_0_INVOKE_DIRECT_1,"Method0InvokeDirect1");
        _m.addEntry(METHOD_0_INVOKE_REF_0,"Method0InvokeRef0");
        _m.addEntry(METHOD_0_INVOKE_REF_1,"Method0InvokeRef1");
        _m.addEntry(METHOD_0_INVOKE_DIRECT_REF_0,"Method0InvokeDirectRef0");
        _m.addEntry(METHOD_0_INVOKE_DIRECT_REF_1,"Method0InvokeDirectRef1");
        _m.addEntry(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_NB_0,"Method0GetDeclaredAnonymousLambdaLocalVarsNb0");
        _m.addEntry(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0,"Method0GetDeclaredAnonymousLambdaLocalVars0");
        _m.addEntry(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1,"Method0GetDeclaredAnonymousLambdaLocalVars1");
        _m.addEntry(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_2,"Method0GetDeclaredAnonymousLambdaLocalVars2");
        _m.addEntry(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0,"Method1GetDeclaredAnonymousLambdaLocalVars0");
        _m.addEntry(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1,"Method1GetDeclaredAnonymousLambdaLocalVars1");
        _m.addEntry(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0,"Method2GetDeclaredAnonymousLambdaLocalVars0");
        _m.addEntry(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1,"Method2GetDeclaredAnonymousLambdaLocalVars1");
        _m.addEntry(METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0,"Method3GetDeclaredAnonymousLambdaLocalVars0");
        _m.addEntry(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0,"Method0GetDeclaredAnonymousLambdaLoopVars0");
        _m.addEntry(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1,"Method0GetDeclaredAnonymousLambdaLoopVars1");
        _m.addEntry(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_2,"Method0GetDeclaredAnonymousLambdaLoopVars2");
        _m.addEntry(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0,"Method1GetDeclaredAnonymousLambdaLoopVars0");
        _m.addEntry(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1,"Method1GetDeclaredAnonymousLambdaLoopVars1");
        _m.addEntry(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0,"Method2GetDeclaredAnonymousLambdaLoopVars0");
        _m.addEntry(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1,"Method2GetDeclaredAnonymousLambdaLoopVars1");
        _m.addEntry(METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0,"Method3GetDeclaredAnonymousLambdaLoopVars0");
        _m.addEntry(ANNOTATION_TYPE_0_GET_STRING_0,"AnnotationType0GetString0");
        _m.addEntry(ANNOTATED_0_GET_ANNOTATIONS_0,"Annotated0GetAnnotations0");
        _m.addEntry(ANNOTATED_0_GET_ANNOTATIONS_SUPP_0,"Annotated0GetAnnotationsSupp0");
        _m.addEntry(ANNOTATED_0_GET_ANNOTATIONS_PARAMETERS_0,"Annotated0GetAnnotationsParameters0");
        _m.addEntry(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_0,"Annotated0GetDeclaredAnonymousLambda0");
        _m.addEntry(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_1,"Annotated0GetDeclaredAnonymousLambda1");
        _m.addEntry(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_2,"Annotated0GetDeclaredAnonymousLambda2");
        _m.addEntry(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_3,"Annotated0GetDeclaredAnonymousLambda3");
        _m.addEntry(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_0,"Annotated0GetDeclaredSwitchMethods0");
        _m.addEntry(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_1,"Annotated0GetDeclaredSwitchMethods1");
        _m.addEntry(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_2,"Annotated0GetDeclaredSwitchMethods2");
        _m.addEntry(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_3,"Annotated0GetDeclaredSwitchMethods3");
    }
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(StringMap<String> _mapping) {
        CustList<CustList<KeyValueMemberName>> map_ = new CustList<CustList<KeyValueMemberName>>();
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(FCT_0_CALL_0), getAliasFct0Call0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(FCT_0_CALL_REF_0), getAliasFct0CallRef0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_GET_CLASS_0), getAliasClassType0GetClass0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_FOR_NAME_0), getAliasClassType0ForName0()),new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_FOR_NAME_1), getAliasClassType0ForName1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_1_FOR_NAME_0), getAliasClassType1ForName0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_TRY_WRAP_0), getAliasClassType0TryWrap0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_IS_INSTANCE_0), getAliasClassType0IsInstance0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_IS_ASSIGNABLE_FROM_0), getAliasClassType0IsAssignableFrom0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_DEFAULT_INSTANCE_0), getAliasClassType0DefaultInstance0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_ENUM_VALUE_OF_0), getAliasClassType0EnumValueOf0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_0), getAliasClassType0GetDeclaredConstructors0()),new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_CONSTRUCTORS_1), getAliasClassType0GetDeclaredConstructors1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_FIELDS_0), getAliasClassType0GetDeclaredFields0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_0), getAliasClassType0GetDeclaredStaticMethods0()),new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_1), getAliasClassType0GetDeclaredStaticMethods1()),new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_2), getAliasClassType0GetDeclaredStaticMethods2()),new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_STATIC_METHODS_3), getAliasClassType0GetDeclaredStaticMethods3())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_METHODS_0), getAliasClassType0GetDeclaredMethods0()),new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_METHODS_1), getAliasClassType0GetDeclaredMethods1()),new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_METHODS_2), getAliasClassType0GetDeclaredMethods2()),new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_METHODS_3), getAliasClassType0GetDeclaredMethods3())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_EXPLICITS_0), getAliasClassType0GetDeclaredExplicits0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_IMPLICITS_0), getAliasClassType0GetDeclaredImplicits0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_BLOCKS_0), getAliasClassType0GetDeclaredBlocks0()),new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_GET_DECLARED_BLOCKS_1), getAliasClassType0GetDeclaredBlocks1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_MAKE_GENERIC_0), getAliasClassType0MakeGeneric0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_MAKE_REF_0), getAliasClassType0MakeRef0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_MAKE_WILD_CARD_0), getAliasClassType0MakeWildCard0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_GET_OPERATORS_0), getAliasClassType0GetOperators0()),new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_GET_OPERATORS_1), getAliasClassType0GetOperators1()),new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_GET_OPERATORS_2), getAliasClassType0GetOperators2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_ARRAY_NEW_INSTANCE_0), getAliasClassType0ArrayNewInstance0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_ARRAY_GET_LENGTH_0), getAliasClassType0ArrayGetLength0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_ARRAY_GET_0), getAliasClassType0ArrayGet0()),new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_ARRAY_GET_1), getAliasClassType0ArrayGet1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_ARRAY_SET_0), getAliasClassType0ArraySet0()),new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_ARRAY_SET_1), getAliasClassType0ArraySet1()),new KeyValueMemberName(_mapping.getVal(CLASS_TYPE_0_ARRAY_SET_2), getAliasClassType0ArraySet2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CONSTRUCTOR_0_NEW_INSTANCE_0), getAliasConstructor0NewInstance0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CONSTRUCTOR_0_NEW_INSTANCE_REF_0), getAliasConstructor0NewInstanceRef0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(FIELD_0_GET_FIELD_0), getAliasField0GetField0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(FIELD_0_SET_FIELD_0), getAliasField0SetField0()),new KeyValueMemberName(_mapping.getVal(FIELD_0_SET_FIELD_1), getAliasField0SetField1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(METHOD_0_INVOKE_0), getAliasMethod0Invoke0()),new KeyValueMemberName(_mapping.getVal(METHOD_0_INVOKE_1), getAliasMethod0Invoke1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(METHOD_0_INVOKE_DIRECT_0), getAliasMethod0InvokeDirect0()),new KeyValueMemberName(_mapping.getVal(METHOD_0_INVOKE_DIRECT_1), getAliasMethod0InvokeDirect1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(METHOD_0_INVOKE_REF_0), getAliasMethod0InvokeRef0()),new KeyValueMemberName(_mapping.getVal(METHOD_0_INVOKE_REF_1), getAliasMethod0InvokeRef1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(METHOD_0_INVOKE_DIRECT_REF_0), getAliasMethod0InvokeDirectRef0()),new KeyValueMemberName(_mapping.getVal(METHOD_0_INVOKE_DIRECT_REF_1), getAliasMethod0InvokeDirectRef1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_NB_0), getAliasMethod0GetDeclaredAnonymousLambdaLocalVarsNb0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0), getAliasMethod0GetDeclaredAnonymousLambdaLocalVars0()),new KeyValueMemberName(_mapping.getVal(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1), getAliasMethod0GetDeclaredAnonymousLambdaLocalVars1()),new KeyValueMemberName(_mapping.getVal(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_2), getAliasMethod0GetDeclaredAnonymousLambdaLocalVars2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0), getAliasMethod1GetDeclaredAnonymousLambdaLocalVars0()),new KeyValueMemberName(_mapping.getVal(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1), getAliasMethod1GetDeclaredAnonymousLambdaLocalVars1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0), getAliasMethod2GetDeclaredAnonymousLambdaLocalVars0()),new KeyValueMemberName(_mapping.getVal(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_1), getAliasMethod2GetDeclaredAnonymousLambdaLocalVars1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOCAL_VARS_0), getAliasMethod3GetDeclaredAnonymousLambdaLocalVars0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0), getAliasMethod0GetDeclaredAnonymousLambdaLoopVars0()),new KeyValueMemberName(_mapping.getVal(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1), getAliasMethod0GetDeclaredAnonymousLambdaLoopVars1()),new KeyValueMemberName(_mapping.getVal(METHOD_0_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_2), getAliasMethod0GetDeclaredAnonymousLambdaLoopVars2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0), getAliasMethod1GetDeclaredAnonymousLambdaLoopVars0()),new KeyValueMemberName(_mapping.getVal(METHOD_1_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1), getAliasMethod1GetDeclaredAnonymousLambdaLoopVars1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0), getAliasMethod2GetDeclaredAnonymousLambdaLoopVars0()),new KeyValueMemberName(_mapping.getVal(METHOD_2_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_1), getAliasMethod2GetDeclaredAnonymousLambdaLoopVars1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(METHOD_3_GET_DECLARED_ANONYMOUS_LAMBDA_LOOP_VARS_0), getAliasMethod3GetDeclaredAnonymousLambdaLoopVars0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(ANNOTATION_TYPE_0_GET_STRING_0), getAliasAnnotationType0GetString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(ANNOTATED_0_GET_ANNOTATIONS_0), getAliasAnnotated0GetAnnotations0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(ANNOTATED_0_GET_ANNOTATIONS_SUPP_0), getAliasAnnotated0GetAnnotationsSupp0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(ANNOTATED_0_GET_ANNOTATIONS_PARAMETERS_0), getAliasAnnotated0GetAnnotationsParameters0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_0), getAliasAnnotated0GetDeclaredAnonymousLambda0()),new KeyValueMemberName(_mapping.getVal(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_1), getAliasAnnotated0GetDeclaredAnonymousLambda1()),new KeyValueMemberName(_mapping.getVal(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_2), getAliasAnnotated0GetDeclaredAnonymousLambda2()),new KeyValueMemberName(_mapping.getVal(ANNOTATED_0_GET_DECLARED_ANONYMOUS_LAMBDA_3), getAliasAnnotated0GetDeclaredAnonymousLambda3())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_0), getAliasAnnotated0GetDeclaredSwitchMethods0()),new KeyValueMemberName(_mapping.getVal(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_1), getAliasAnnotated0GetDeclaredSwitchMethods1()),new KeyValueMemberName(_mapping.getVal(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_2), getAliasAnnotated0GetDeclaredSwitchMethods2()),new KeyValueMemberName(_mapping.getVal(ANNOTATED_0_GET_DECLARED_SWITCH_METHODS_3), getAliasAnnotated0GetDeclaredSwitchMethods3())));
        return map_;
    }
    public String getAliasFct0Call0() {
        return aliasFct0Call0;
    }

    public void setAliasFct0Call0(String _v) {
        this.aliasFct0Call0 =_v;
    }

    public String getAliasFct0CallRef0() {
        return aliasFct0CallRef0;
    }

    public void setAliasFct0CallRef0(String _v) {
        this.aliasFct0CallRef0 = _v;
    }

    public String getAliasClassType0GetClass0() {
        return aliasClassType0GetClass0;
    }

    public void setAliasClassType0GetClass0(String _v) {
        this.aliasClassType0GetClass0 =_v;
    }

    public String getAliasClassType0ForName0() {
        return aliasClassType0ForName0;
    }

    public void setAliasClassType0ForName0(String _v) {
        this.aliasClassType0ForName0 =_v;
    }

    public String getAliasClassType0ForName1() {
        return aliasClassType0ForName1;
    }

    public void setAliasClassType0ForName1(String _v) {
        this.aliasClassType0ForName1 =_v;
    }

    public String getAliasClassType1ForName0() {
        return aliasClassType1ForName0;
    }

    public void setAliasClassType1ForName0(String _v) {
        this.aliasClassType1ForName0 =_v;
    }

    public String getAliasClassType0TryWrap0() {
        return aliasClassType0TryWrap0;
    }

    public void setAliasClassType0TryWrap0(String _v) {
        this.aliasClassType0TryWrap0 = _v;
    }

    public String getAliasClassType0IsInstance0() {
        return aliasClassType0IsInstance0;
    }

    public void setAliasClassType0IsInstance0(String _v) {
        this.aliasClassType0IsInstance0 =_v;
    }

    public String getAliasClassType0IsAssignableFrom0() {
        return aliasClassType0IsAssignableFrom0;
    }

    public void setAliasClassType0IsAssignableFrom0(String _v) {
        this.aliasClassType0IsAssignableFrom0 =_v;
    }

    public String getAliasClassType0DefaultInstance0() {
        return aliasClassType0DefaultInstance0;
    }

    public void setAliasClassType0DefaultInstance0(String _v) {
        this.aliasClassType0DefaultInstance0 =_v;
    }

    public String getAliasClassType0EnumValueOf0() {
        return aliasClassType0EnumValueOf0;
    }

    public void setAliasClassType0EnumValueOf0(String _v) {
        this.aliasClassType0EnumValueOf0 =_v;
    }

    public String getAliasClassType0GetDeclaredConstructors0() {
        return aliasClassType0GetDeclaredConstructors0;
    }

    public void setAliasClassType0GetDeclaredConstructors0(String _v) {
        this.aliasClassType0GetDeclaredConstructors0 =_v;
    }

    public String getAliasClassType0GetDeclaredConstructors1() {
        return aliasClassType0GetDeclaredConstructors1;
    }

    public void setAliasClassType0GetDeclaredConstructors1(String _v) {
        this.aliasClassType0GetDeclaredConstructors1 =_v;
    }

    public String getAliasClassType0GetDeclaredFields0() {
        return aliasClassType0GetDeclaredFields0;
    }

    public void setAliasClassType0GetDeclaredFields0(String _v) {
        this.aliasClassType0GetDeclaredFields0 =_v;
    }

    public String getAliasClassType0GetDeclaredStaticMethods0() {
        return aliasClassType0GetDeclaredStaticMethods0;
    }

    public void setAliasClassType0GetDeclaredStaticMethods0(String _v) {
        this.aliasClassType0GetDeclaredStaticMethods0 =_v;
    }

    public String getAliasClassType0GetDeclaredStaticMethods1() {
        return aliasClassType0GetDeclaredStaticMethods1;
    }

    public void setAliasClassType0GetDeclaredStaticMethods1(String _v) {
        this.aliasClassType0GetDeclaredStaticMethods1 =_v;
    }

    public String getAliasClassType0GetDeclaredStaticMethods2() {
        return aliasClassType0GetDeclaredStaticMethods2;
    }

    public void setAliasClassType0GetDeclaredStaticMethods2(String _v) {
        this.aliasClassType0GetDeclaredStaticMethods2 =_v;
    }

    public String getAliasClassType0GetDeclaredStaticMethods3() {
        return aliasClassType0GetDeclaredStaticMethods3;
    }

    public void setAliasClassType0GetDeclaredStaticMethods3(String _v) {
        this.aliasClassType0GetDeclaredStaticMethods3 =_v;
    }

    public String getAliasClassType0GetDeclaredMethods0() {
        return aliasClassType0GetDeclaredMethods0;
    }

    public void setAliasClassType0GetDeclaredMethods0(String _v) {
        this.aliasClassType0GetDeclaredMethods0 =_v;
    }

    public String getAliasClassType0GetDeclaredMethods1() {
        return aliasClassType0GetDeclaredMethods1;
    }

    public void setAliasClassType0GetDeclaredMethods1(String _v) {
        this.aliasClassType0GetDeclaredMethods1 =_v;
    }

    public String getAliasClassType0GetDeclaredMethods2() {
        return aliasClassType0GetDeclaredMethods2;
    }

    public void setAliasClassType0GetDeclaredMethods2(String _v) {
        this.aliasClassType0GetDeclaredMethods2 =_v;
    }

    public String getAliasClassType0GetDeclaredMethods3() {
        return aliasClassType0GetDeclaredMethods3;
    }

    public void setAliasClassType0GetDeclaredMethods3(String _v) {
        this.aliasClassType0GetDeclaredMethods3 =_v;
    }

    public String getAliasClassType0GetDeclaredExplicits0() {
        return aliasClassType0GetDeclaredExplicits0;
    }

    public void setAliasClassType0GetDeclaredExplicits0(String _v) {
        this.aliasClassType0GetDeclaredExplicits0 =_v;
    }

    public String getAliasClassType0GetDeclaredImplicits0() {
        return aliasClassType0GetDeclaredImplicits0;
    }

    public void setAliasClassType0GetDeclaredImplicits0(String _v) {
        this.aliasClassType0GetDeclaredImplicits0 =_v;
    }

    public String getAliasClassType0GetDeclaredBlocks0() {
        return aliasClassType0GetDeclaredBlocks0;
    }

    public void setAliasClassType0GetDeclaredBlocks0(String _v) {
        this.aliasClassType0GetDeclaredBlocks0 =_v;
    }

    public String getAliasClassType0GetDeclaredBlocks1() {
        return aliasClassType0GetDeclaredBlocks1;
    }

    public void setAliasClassType0GetDeclaredBlocks1(String _v) {
        this.aliasClassType0GetDeclaredBlocks1 =_v;
    }

    public String getAliasClassType0MakeGeneric0() {
        return aliasClassType0MakeGeneric0;
    }

    public void setAliasClassType0MakeGeneric0(String _v) {
        this.aliasClassType0MakeGeneric0 =_v;
    }

    public String getAliasClassType0MakeRef0() {
        return aliasClassType0MakeRef0;
    }

    public void setAliasClassType0MakeRef0(String _v) {
        this.aliasClassType0MakeRef0 =_v;
    }

    public String getAliasClassType0MakeWildCard0() {
        return aliasClassType0MakeWildCard0;
    }

    public void setAliasClassType0MakeWildCard0(String _v) {
        this.aliasClassType0MakeWildCard0 =_v;
    }

    public String getAliasClassType0GetOperators0() {
        return aliasClassType0GetOperators0;
    }

    public void setAliasClassType0GetOperators0(String _v) {
        this.aliasClassType0GetOperators0 =_v;
    }

    public String getAliasClassType0GetOperators1() {
        return aliasClassType0GetOperators1;
    }

    public void setAliasClassType0GetOperators1(String _v) {
        this.aliasClassType0GetOperators1 =_v;
    }

    public String getAliasClassType0GetOperators2() {
        return aliasClassType0GetOperators2;
    }

    public void setAliasClassType0GetOperators2(String _v) {
        this.aliasClassType0GetOperators2 =_v;
    }

    public String getAliasClassType0ArrayNewInstance0() {
        return aliasClassType0ArrayNewInstance0;
    }

    public void setAliasClassType0ArrayNewInstance0(String _v) {
        this.aliasClassType0ArrayNewInstance0 =_v;
    }

    public String getAliasClassType0ArrayGetLength0() {
        return aliasClassType0ArrayGetLength0;
    }

    public void setAliasClassType0ArrayGetLength0(String _v) {
        this.aliasClassType0ArrayGetLength0 =_v;
    }

    public String getAliasClassType0ArrayGet0() {
        return aliasClassType0ArrayGet0;
    }

    public void setAliasClassType0ArrayGet0(String _v) {
        this.aliasClassType0ArrayGet0 =_v;
    }

    public String getAliasClassType0ArrayGet1() {
        return aliasClassType0ArrayGet1;
    }

    public void setAliasClassType0ArrayGet1(String _v) {
        this.aliasClassType0ArrayGet1 =_v;
    }

    public String getAliasClassType0ArraySet0() {
        return aliasClassType0ArraySet0;
    }

    public void setAliasClassType0ArraySet0(String _v) {
        this.aliasClassType0ArraySet0 =_v;
    }

    public String getAliasClassType0ArraySet1() {
        return aliasClassType0ArraySet1;
    }

    public void setAliasClassType0ArraySet1(String _v) {
        this.aliasClassType0ArraySet1 =_v;
    }

    public String getAliasClassType0ArraySet2() {
        return aliasClassType0ArraySet2;
    }

    public void setAliasClassType0ArraySet2(String _v) {
        this.aliasClassType0ArraySet2 =_v;
    }

    public String getAliasConstructor0NewInstance0() {
        return aliasConstructor0NewInstance0;
    }

    public void setAliasConstructor0NewInstance0(String _v) {
        this.aliasConstructor0NewInstance0 =_v;
    }

    public String getAliasConstructor0NewInstanceRef0() {
        return aliasConstructor0NewInstanceRef0;
    }

    public void setAliasConstructor0NewInstanceRef0(String _v) {
        this.aliasConstructor0NewInstanceRef0 = _v;
    }

    public String getAliasField0GetField0() {
        return aliasField0GetField0;
    }

    public void setAliasField0GetField0(String _v) {
        this.aliasField0GetField0 =_v;
    }

    public String getAliasField0SetField0() {
        return aliasField0SetField0;
    }

    public void setAliasField0SetField0(String _v) {
        this.aliasField0SetField0 =_v;
    }

    public String getAliasField0SetField1() {
        return aliasField0SetField1;
    }

    public void setAliasField0SetField1(String _v) {
        this.aliasField0SetField1 =_v;
    }

    public String getAliasMethod0Invoke0() {
        return aliasMethod0Invoke0;
    }

    public void setAliasMethod0Invoke0(String _v) {
        this.aliasMethod0Invoke0 =_v;
    }

    public String getAliasMethod0Invoke1() {
        return aliasMethod0Invoke1;
    }

    public void setAliasMethod0Invoke1(String _v) {
        this.aliasMethod0Invoke1 =_v;
    }

    public String getAliasMethod0InvokeDirect0() {
        return aliasMethod0InvokeDirect0;
    }

    public void setAliasMethod0InvokeDirect0(String _v) {
        this.aliasMethod0InvokeDirect0 =_v;
    }

    public String getAliasMethod0InvokeDirect1() {
        return aliasMethod0InvokeDirect1;
    }

    public void setAliasMethod0InvokeDirect1(String _v) {
        this.aliasMethod0InvokeDirect1 =_v;
    }

    public String getAliasMethod0InvokeRef0() {
        return aliasMethod0InvokeRef0;
    }

    public void setAliasMethod0InvokeRef0(String _v) {
        this.aliasMethod0InvokeRef0 = _v;
    }

    public String getAliasMethod0InvokeRef1() {
        return aliasMethod0InvokeRef1;
    }

    public void setAliasMethod0InvokeRef1(String _v) {
        this.aliasMethod0InvokeRef1 = _v;
    }

    public String getAliasMethod0InvokeDirectRef0() {
        return aliasMethod0InvokeDirectRef0;
    }

    public void setAliasMethod0InvokeDirectRef0(String _v) {
        this.aliasMethod0InvokeDirectRef0 = _v;
    }

    public String getAliasMethod0InvokeDirectRef1() {
        return aliasMethod0InvokeDirectRef1;
    }

    public void setAliasMethod0InvokeDirectRef1(String _v) {
        this.aliasMethod0InvokeDirectRef1 = _v;
    }

    public String getAliasMethod0GetDeclaredAnonymousLambdaLocalVarsNb0() {
        return aliasMethod0GetDeclaredAnonymousLambdaLocalVarsNb0;
    }

    public void setAliasMethod0GetDeclaredAnonymousLambdaLocalVarsNb0(String _v) {
        this.aliasMethod0GetDeclaredAnonymousLambdaLocalVarsNb0 = _v;
    }

    public String getAliasMethod0GetDeclaredAnonymousLambdaLocalVars0() {
        return aliasMethod0GetDeclaredAnonymousLambdaLocalVars0;
    }

    public void setAliasMethod0GetDeclaredAnonymousLambdaLocalVars0(String _v) {
        this.aliasMethod0GetDeclaredAnonymousLambdaLocalVars0 =_v;
    }

    public String getAliasMethod0GetDeclaredAnonymousLambdaLocalVars1() {
        return aliasMethod0GetDeclaredAnonymousLambdaLocalVars1;
    }

    public void setAliasMethod0GetDeclaredAnonymousLambdaLocalVars1(String _v) {
        this.aliasMethod0GetDeclaredAnonymousLambdaLocalVars1 =_v;
    }

    public String getAliasMethod0GetDeclaredAnonymousLambdaLocalVars2() {
        return aliasMethod0GetDeclaredAnonymousLambdaLocalVars2;
    }

    public void setAliasMethod0GetDeclaredAnonymousLambdaLocalVars2(String _v) {
        this.aliasMethod0GetDeclaredAnonymousLambdaLocalVars2 =_v;
    }

    public String getAliasMethod1GetDeclaredAnonymousLambdaLocalVars0() {
        return aliasMethod1GetDeclaredAnonymousLambdaLocalVars0;
    }

    public void setAliasMethod1GetDeclaredAnonymousLambdaLocalVars0(String _v) {
        this.aliasMethod1GetDeclaredAnonymousLambdaLocalVars0 =_v;
    }

    public String getAliasMethod1GetDeclaredAnonymousLambdaLocalVars1() {
        return aliasMethod1GetDeclaredAnonymousLambdaLocalVars1;
    }

    public void setAliasMethod1GetDeclaredAnonymousLambdaLocalVars1(String _v) {
        this.aliasMethod1GetDeclaredAnonymousLambdaLocalVars1 =_v;
    }

    public String getAliasMethod2GetDeclaredAnonymousLambdaLocalVars0() {
        return aliasMethod2GetDeclaredAnonymousLambdaLocalVars0;
    }

    public void setAliasMethod2GetDeclaredAnonymousLambdaLocalVars0(String _v) {
        this.aliasMethod2GetDeclaredAnonymousLambdaLocalVars0 =_v;
    }

    public String getAliasMethod2GetDeclaredAnonymousLambdaLocalVars1() {
        return aliasMethod2GetDeclaredAnonymousLambdaLocalVars1;
    }

    public void setAliasMethod2GetDeclaredAnonymousLambdaLocalVars1(String _v) {
        this.aliasMethod2GetDeclaredAnonymousLambdaLocalVars1 =_v;
    }

    public String getAliasMethod3GetDeclaredAnonymousLambdaLocalVars0() {
        return aliasMethod3GetDeclaredAnonymousLambdaLocalVars0;
    }

    public void setAliasMethod3GetDeclaredAnonymousLambdaLocalVars0(String _v) {
        this.aliasMethod3GetDeclaredAnonymousLambdaLocalVars0 =_v;
    }

    public String getAliasMethod0GetDeclaredAnonymousLambdaLoopVars0() {
        return aliasMethod0GetDeclaredAnonymousLambdaLoopVars0;
    }

    public void setAliasMethod0GetDeclaredAnonymousLambdaLoopVars0(String _v) {
        this.aliasMethod0GetDeclaredAnonymousLambdaLoopVars0 =_v;
    }

    public String getAliasMethod0GetDeclaredAnonymousLambdaLoopVars1() {
        return aliasMethod0GetDeclaredAnonymousLambdaLoopVars1;
    }

    public void setAliasMethod0GetDeclaredAnonymousLambdaLoopVars1(String _v) {
        this.aliasMethod0GetDeclaredAnonymousLambdaLoopVars1 =_v;
    }

    public String getAliasMethod0GetDeclaredAnonymousLambdaLoopVars2() {
        return aliasMethod0GetDeclaredAnonymousLambdaLoopVars2;
    }

    public void setAliasMethod0GetDeclaredAnonymousLambdaLoopVars2(String _v) {
        this.aliasMethod0GetDeclaredAnonymousLambdaLoopVars2 =_v;
    }

    public String getAliasMethod1GetDeclaredAnonymousLambdaLoopVars0() {
        return aliasMethod1GetDeclaredAnonymousLambdaLoopVars0;
    }

    public void setAliasMethod1GetDeclaredAnonymousLambdaLoopVars0(String _v) {
        this.aliasMethod1GetDeclaredAnonymousLambdaLoopVars0 =_v;
    }

    public String getAliasMethod1GetDeclaredAnonymousLambdaLoopVars1() {
        return aliasMethod1GetDeclaredAnonymousLambdaLoopVars1;
    }

    public void setAliasMethod1GetDeclaredAnonymousLambdaLoopVars1(String _v) {
        this.aliasMethod1GetDeclaredAnonymousLambdaLoopVars1 =_v;
    }

    public String getAliasMethod2GetDeclaredAnonymousLambdaLoopVars0() {
        return aliasMethod2GetDeclaredAnonymousLambdaLoopVars0;
    }

    public void setAliasMethod2GetDeclaredAnonymousLambdaLoopVars0(String _v) {
        this.aliasMethod2GetDeclaredAnonymousLambdaLoopVars0 =_v;
    }

    public String getAliasMethod2GetDeclaredAnonymousLambdaLoopVars1() {
        return aliasMethod2GetDeclaredAnonymousLambdaLoopVars1;
    }

    public void setAliasMethod2GetDeclaredAnonymousLambdaLoopVars1(String _v) {
        this.aliasMethod2GetDeclaredAnonymousLambdaLoopVars1 =_v;
    }

    public String getAliasMethod3GetDeclaredAnonymousLambdaLoopVars0() {
        return aliasMethod3GetDeclaredAnonymousLambdaLoopVars0;
    }

    public void setAliasMethod3GetDeclaredAnonymousLambdaLoopVars0(String _v) {
        this.aliasMethod3GetDeclaredAnonymousLambdaLoopVars0 =_v;
    }

    public String getAliasAnnotationType0GetString0() {
        return aliasAnnotationType0GetString0;
    }

    public void setAliasAnnotationType0GetString0(String _v) {
        this.aliasAnnotationType0GetString0 =_v;
    }

    public String getAliasAnnotated0GetAnnotations0() {
        return aliasAnnotated0GetAnnotations0;
    }

    public void setAliasAnnotated0GetAnnotations0(String _v) {
        this.aliasAnnotated0GetAnnotations0 =_v;
    }

    public String getAliasAnnotated0GetAnnotationsParameters0() {
        return aliasAnnotated0GetAnnotationsParameters0;
    }

    public void setAliasAnnotated0GetAnnotationsParameters0(String _v) {
        this.aliasAnnotated0GetAnnotationsParameters0 =_v;
    }

    public String getAliasAnnotated0GetAnnotationsSupp0() {
        return aliasAnnotated0GetAnnotationsSupp0;
    }

    public void setAliasAnnotated0GetAnnotationsSupp0(String _v) {
        this.aliasAnnotated0GetAnnotationsSupp0 = _v;
    }

    public String getAliasAnnotated0GetDeclaredAnonymousLambda0() {
        return aliasAnnotated0GetDeclaredAnonymousLambda0;
    }

    public void setAliasAnnotated0GetDeclaredAnonymousLambda0(String _v) {
        this.aliasAnnotated0GetDeclaredAnonymousLambda0 =_v;
    }

    public String getAliasAnnotated0GetDeclaredAnonymousLambda1() {
        return aliasAnnotated0GetDeclaredAnonymousLambda1;
    }

    public void setAliasAnnotated0GetDeclaredAnonymousLambda1(String _v) {
        this.aliasAnnotated0GetDeclaredAnonymousLambda1 =_v;
    }

    public String getAliasAnnotated0GetDeclaredAnonymousLambda2() {
        return aliasAnnotated0GetDeclaredAnonymousLambda2;
    }

    public void setAliasAnnotated0GetDeclaredAnonymousLambda2(String _v) {
        this.aliasAnnotated0GetDeclaredAnonymousLambda2 =_v;
    }

    public String getAliasAnnotated0GetDeclaredAnonymousLambda3() {
        return aliasAnnotated0GetDeclaredAnonymousLambda3;
    }

    public void setAliasAnnotated0GetDeclaredAnonymousLambda3(String _v) {
        this.aliasAnnotated0GetDeclaredAnonymousLambda3 =_v;
    }

    public String getAliasAnnotated0GetDeclaredSwitchMethods0() {
        return aliasAnnotated0GetDeclaredSwitchMethods0;
    }

    public void setAliasAnnotated0GetDeclaredSwitchMethods0(String _v) {
        this.aliasAnnotated0GetDeclaredSwitchMethods0 = _v;
    }

    public String getAliasAnnotated0GetDeclaredSwitchMethods1() {
        return aliasAnnotated0GetDeclaredSwitchMethods1;
    }

    public void setAliasAnnotated0GetDeclaredSwitchMethods1(String _v) {
        this.aliasAnnotated0GetDeclaredSwitchMethods1 = _v;
    }

    public String getAliasAnnotated0GetDeclaredSwitchMethods2() {
        return aliasAnnotated0GetDeclaredSwitchMethods2;
    }

    public void setAliasAnnotated0GetDeclaredSwitchMethods2(String _v) {
        this.aliasAnnotated0GetDeclaredSwitchMethods2 = _v;
    }

    public String getAliasAnnotated0GetDeclaredSwitchMethods3() {
        return aliasAnnotated0GetDeclaredSwitchMethods3;
    }

    public void setAliasAnnotated0GetDeclaredSwitchMethods3(String _v) {
        this.aliasAnnotated0GetDeclaredSwitchMethods3 = _v;
    }
}
