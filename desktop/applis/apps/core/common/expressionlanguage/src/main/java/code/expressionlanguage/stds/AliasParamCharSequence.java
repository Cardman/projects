package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringMap;

public final class AliasParamCharSequence {
    private static final String CHAR_SEQUENCE_0_SUB_SEQUENCE_0="170";
    private static final String CHAR_SEQUENCE_0_SUB_SEQUENCE_1="171";
    private static final String CHAR_SEQUENCE_0_CHAR_AT_0="172";
    private static final String CHAR_SEQUENCE_0_SUBSTRING_0="173";
    private static final String CHAR_SEQUENCE_0_SUBSTRING_1="174";
    private static final String CHAR_SEQUENCE_1_SUBSTRING_0="175";
    private static final String CHAR_SEQUENCE_0_COMPARE_TO_0="176";
    private static final String CHAR_SEQUENCE_0_CONTAINS_0="177";
    private static final String CHAR_SEQUENCE_0_STARTS_WITH_0="178";
    private static final String CHAR_SEQUENCE_1_STARTS_WITH_0="179";
    private static final String CHAR_SEQUENCE_1_STARTS_WITH_1="180";
    private static final String CHAR_SEQUENCE_0_ENDS_WITH_0="181";
    private static final String CHAR_SEQUENCE_0_INDEX_OF_0="182";
    private static final String CHAR_SEQUENCE_1_INDEX_OF_0="183";
    private static final String CHAR_SEQUENCE_1_INDEX_OF_1="184";
    private static final String CHAR_SEQUENCE_2_INDEX_OF_0="185";
    private static final String CHAR_SEQUENCE_3_INDEX_OF_0="186";
    private static final String CHAR_SEQUENCE_3_INDEX_OF_1="187";
    private static final String CHAR_SEQUENCE_0_LAST_INDEX_OF_0="188";
    private static final String CHAR_SEQUENCE_1_LAST_INDEX_OF_0="189";
    private static final String CHAR_SEQUENCE_1_LAST_INDEX_OF_1="190";
    private static final String CHAR_SEQUENCE_2_LAST_INDEX_OF_0="191";
    private static final String CHAR_SEQUENCE_3_LAST_INDEX_OF_0="192";
    private static final String CHAR_SEQUENCE_3_LAST_INDEX_OF_1="193";
    private static final String CHAR_SEQUENCE_0_FORMAT_0="194";
    private static final String CHAR_SEQUENCE_0_SPLIT_0="195";
    private static final String CHAR_SEQUENCE_1_SPLIT_0="196";
    private static final String CHAR_SEQUENCE_1_SPLIT_1="197";
    private static final String CHAR_SEQUENCE_2_SPLIT_0="198";
    private static final String CHAR_SEQUENCE_3_SPLIT_0="199";
    private static final String CHAR_SEQUENCE_3_SPLIT_1="200";
    private static final String CHAR_SEQUENCE_0_SPLIT_STRINGS_0="201";
    private static final String CHAR_SEQUENCE_1_SPLIT_STRINGS_0="202";
    private static final String CHAR_SEQUENCE_1_SPLIT_STRINGS_1="203";
    private static final String CHAR_SEQUENCE_0_SPLIT_CHARS_0="204";
    private static final String CHAR_SEQUENCE_0_REGION_MATCHES_0="205";
    private static final String CHAR_SEQUENCE_0_REGION_MATCHES_1="206";
    private static final String CHAR_SEQUENCE_0_REGION_MATCHES_2="207";
    private static final String CHAR_SEQUENCE_0_REGION_MATCHES_3="208";
    private static final String CHAR_SEQUENCE_0_EQUALS_0="209";
    private static final String CHAR_SEQUENCE_0_EQUALS_1="210";
    private static final String STRING_0_EQUALS_IGNORE_CASE_0="211";
    private static final String STRING_0_COMPARE_0="212";
    private static final String STRING_0_COMPARE_1="213";
    private static final String STRING_0_COMPARE_TO_IGNORE_CASE_0="214";
    private static final String STRING_0_REPLACE_STRING_0="215";
    private static final String STRING_0_REPLACE_STRING_1="216";
    private static final String STRING_1_REPLACE_STRING_0="217";
    private static final String STRING_1_REPLACE_STRING_1="218";
    private static final String STRING_0_REPLACE_MULTIPLE_0="219";
    private static final String STRING_0_REGION_MATCHES_0="220";
    private static final String STRING_0_REGION_MATCHES_1="221";
    private static final String STRING_0_REGION_MATCHES_2="222";
    private static final String STRING_0_REGION_MATCHES_3="223";
    private static final String STRING_0_REGION_MATCHES_4="224";
    private static final String STRING_0_VALUE_OF_METHOD_0="225";
    private static final String STRING_1_VALUE_OF_METHOD_0="226";
    private static final String STRING_2_VALUE_OF_METHOD_0="227";
    private static final String STRING_3_VALUE_OF_METHOD_0="228";
    private static final String STRING_4_VALUE_OF_METHOD_0="229";
    private static final String STRING_5_VALUE_OF_METHOD_0="230";
    private static final String STRING_6_VALUE_OF_METHOD_0="231";
    private static final String STRING_7_VALUE_OF_METHOD_0="232";
    private static final String STRING_8_VALUE_OF_METHOD_0="233";
    private static final String STRING_9_VALUE_OF_METHOD_0="234";
    private static final String STRING_9_VALUE_OF_METHOD_1="235";
    private static final String STRING_9_VALUE_OF_METHOD_2="236";
    private static final String STRING_0_STRING_0="237";
    private static final String STRING_1_STRING_0="238";
    private static final String STRING_1_STRING_1="239";
    private static final String STRING_1_STRING_2="240";
    private static final String STRING_2_STRING_0="241";
    private static final String STRING_3_STRING_0="242";
    private static final String STRING_3_STRING_1="243";
    private static final String STRING_3_STRING_2="244";
    private static final String STRING_4_STRING_0="245";
    private static final String STRING_BUILDER_0_APPEND_0="246";
    private static final String STRING_BUILDER_1_APPEND_0="247";
    private static final String STRING_BUILDER_2_APPEND_0="248";
    private static final String STRING_BUILDER_3_APPEND_0="249";
    private static final String STRING_BUILDER_4_APPEND_0="250";
    private static final String STRING_BUILDER_5_APPEND_0="251";
    private static final String STRING_BUILDER_6_APPEND_0="252";
    private static final String STRING_BUILDER_7_APPEND_0="253";
    private static final String STRING_BUILDER_8_APPEND_0="254";
    private static final String STRING_BUILDER_9_APPEND_0="255";
    private static final String STRING_BUILDER_9_APPEND_1="256";
    private static final String STRING_BUILDER_9_APPEND_2="257";
    private static final String STRING_BUILDER_10_APPEND_0="258";
    private static final String STRING_BUILDER_11_APPEND_0="259";
    private static final String STRING_BUILDER_11_APPEND_1="260";
    private static final String STRING_BUILDER_11_APPEND_2="261";
    private static final String STRING_BUILDER_12_APPEND_0="262";
    private static final String STRING_BUILDER_13_APPEND_0="263";
    private static final String STRING_BUILDER_13_APPEND_1="264";
    private static final String STRING_BUILDER_13_APPEND_2="265";
    private static final String STRING_BUILDER_0_DELETE_0="266";
    private static final String STRING_BUILDER_0_DELETE_1="267";
    private static final String STRING_BUILDER_0_DELETE_CHAR_AT_0="268";
    private static final String STRING_BUILDER_0_INSERT_0="269";
    private static final String STRING_BUILDER_0_INSERT_1="270";
    private static final String STRING_BUILDER_1_INSERT_0="271";
    private static final String STRING_BUILDER_1_INSERT_1="272";
    private static final String STRING_BUILDER_2_INSERT_0="273";
    private static final String STRING_BUILDER_2_INSERT_1="274";
    private static final String STRING_BUILDER_3_INSERT_0="275";
    private static final String STRING_BUILDER_3_INSERT_1="276";
    private static final String STRING_BUILDER_4_INSERT_0="277";
    private static final String STRING_BUILDER_4_INSERT_1="278";
    private static final String STRING_BUILDER_5_INSERT_0="279";
    private static final String STRING_BUILDER_5_INSERT_1="280";
    private static final String STRING_BUILDER_6_INSERT_0="281";
    private static final String STRING_BUILDER_6_INSERT_1="282";
    private static final String STRING_BUILDER_7_INSERT_0="283";
    private static final String STRING_BUILDER_7_INSERT_1="284";
    private static final String STRING_BUILDER_8_INSERT_0="285";
    private static final String STRING_BUILDER_8_INSERT_1="286";
    private static final String STRING_BUILDER_9_INSERT_0="287";
    private static final String STRING_BUILDER_9_INSERT_1="288";
    private static final String STRING_BUILDER_9_INSERT_2="289";
    private static final String STRING_BUILDER_9_INSERT_3="290";
    private static final String STRING_BUILDER_10_INSERT_0="291";
    private static final String STRING_BUILDER_10_INSERT_1="292";
    private static final String STRING_BUILDER_11_INSERT_0="293";
    private static final String STRING_BUILDER_11_INSERT_1="294";
    private static final String STRING_BUILDER_11_INSERT_2="295";
    private static final String STRING_BUILDER_11_INSERT_3="296";
    private static final String STRING_BUILDER_12_INSERT_0="297";
    private static final String STRING_BUILDER_12_INSERT_1="298";
    private static final String STRING_BUILDER_13_INSERT_0="299";
    private static final String STRING_BUILDER_13_INSERT_1="300";
    private static final String STRING_BUILDER_13_INSERT_2="301";
    private static final String STRING_BUILDER_13_INSERT_3="302";
    private static final String STRING_BUILDER_0_REPLACE_0="303";
    private static final String STRING_BUILDER_0_REPLACE_1="304";
    private static final String STRING_BUILDER_0_REPLACE_2="305";
    private static final String STRING_BUILDER_0_SET_CHAR_AT_0="306";
    private static final String STRING_BUILDER_0_SET_CHAR_AT_1="307";
    private static final String STRING_BUILDER_0_SET_LENGTH_0="308";
    private static final String STRING_BUILDER_0_ENSURE_CAPACITY_0="309";
    private static final String STRING_BUILDER_0_SAME_0="310";
    private static final String STRING_BUILDER_0_SAME_1="311";
    private static final String STRING_BUILDER_0_STRING_BUILDER_0="312";
    private static final String STRING_BUILDER_1_STRING_BUILDER_0="313";
    private static final String STRING_BUILDER_2_STRING_BUILDER_0="314";
    private static final String REPLACEMENT_0_REPLACEMENT_0="315";
    private static final String REPLACEMENT_0_REPLACEMENT_1="316";
    private String aliasCharSequence0SubSequence0="a";
    private String aliasCharSequence0SubSequence1="b";
    private String aliasCharSequence0CharAt0="a";
    private String aliasCharSequence0Substring0="a";
    private String aliasCharSequence0Substring1="b";
    private String aliasCharSequence1Substring0="a";
    private String aliasCharSequence0CompareTo0="a";
    private String aliasCharSequence0Contains0="a";
    private String aliasCharSequence0StartsWith0="a";
    private String aliasCharSequence1StartsWith0="a";
    private String aliasCharSequence1StartsWith1="b";
    private String aliasCharSequence0EndsWith0="a";
    private String aliasCharSequence0IndexOf0="a";
    private String aliasCharSequence1IndexOf0="a";
    private String aliasCharSequence1IndexOf1="b";
    private String aliasCharSequence2IndexOf0="a";
    private String aliasCharSequence3IndexOf0="a";
    private String aliasCharSequence3IndexOf1="b";
    private String aliasCharSequence0LastIndexOf0="a";
    private String aliasCharSequence1LastIndexOf0="a";
    private String aliasCharSequence1LastIndexOf1="b";
    private String aliasCharSequence2LastIndexOf0="a";
    private String aliasCharSequence3LastIndexOf0="a";
    private String aliasCharSequence3LastIndexOf1="b";
    private String aliasCharSequence0Format0="a";
    private String aliasCharSequence0Split0="a";
    private String aliasCharSequence1Split0="a";
    private String aliasCharSequence1Split1="b";
    private String aliasCharSequence2Split0="a";
    private String aliasCharSequence3Split0="a";
    private String aliasCharSequence3Split1="b";
    private String aliasCharSequence0SplitStrings0="a";
    private String aliasCharSequence1SplitStrings0="a";
    private String aliasCharSequence1SplitStrings1="b";
    private String aliasCharSequence0SplitChars0="a";
    private String aliasCharSequence0RegionMatches0="a";
    private String aliasCharSequence0RegionMatches1="b";
    private String aliasCharSequence0RegionMatches2="c";
    private String aliasCharSequence0RegionMatches3="d";
    private String aliasCharSequence0Equals0="a";
    private String aliasCharSequence0Equals1="b";
    private String aliasString0EqualsIgnoreCase0="a";
    private String aliasString0Compare0="a";
    private String aliasString0Compare1="b";
    private String aliasString0CompareToIgnoreCase0="a";
    private String aliasString0ReplaceString0="a";
    private String aliasString0ReplaceString1="b";
    private String aliasString1ReplaceString0="a";
    private String aliasString1ReplaceString1="b";
    private String aliasString0ReplaceMultiple0="a";
    private String aliasString0RegionMatches0="a";
    private String aliasString0RegionMatches1="b";
    private String aliasString0RegionMatches2="c";
    private String aliasString0RegionMatches3="d";
    private String aliasString0RegionMatches4="e";
    private String aliasString0ValueOfMethod0="a";
    private String aliasString1ValueOfMethod0="a";
    private String aliasString2ValueOfMethod0="a";
    private String aliasString3ValueOfMethod0="a";
    private String aliasString4ValueOfMethod0="a";
    private String aliasString5ValueOfMethod0="a";
    private String aliasString6ValueOfMethod0="a";
    private String aliasString7ValueOfMethod0="a";
    private String aliasString8ValueOfMethod0="a";
    private String aliasString9ValueOfMethod0="a";
    private String aliasString9ValueOfMethod1="b";
    private String aliasString9ValueOfMethod2="c";
    private String aliasString0String0="a";
    private String aliasString1String0="a";
    private String aliasString1String1="b";
    private String aliasString1String2="c";
    private String aliasString2String0="a";
    private String aliasString3String0="a";
    private String aliasString3String1="b";
    private String aliasString3String2="c";
    private String aliasString4String0="a";
    private String aliasStringBuilder0Append0="a";
    private String aliasStringBuilder1Append0="a";
    private String aliasStringBuilder2Append0="a";
    private String aliasStringBuilder3Append0="a";
    private String aliasStringBuilder4Append0="a";
    private String aliasStringBuilder5Append0="a";
    private String aliasStringBuilder6Append0="a";
    private String aliasStringBuilder7Append0="a";
    private String aliasStringBuilder8Append0="a";
    private String aliasStringBuilder9Append0="a";
    private String aliasStringBuilder9Append1="b";
    private String aliasStringBuilder9Append2="c";
    private String aliasStringBuilder10Append0="a";
    private String aliasStringBuilder11Append0="a";
    private String aliasStringBuilder11Append1="b";
    private String aliasStringBuilder11Append2="c";
    private String aliasStringBuilder12Append0="a";
    private String aliasStringBuilder13Append0="a";
    private String aliasStringBuilder13Append1="b";
    private String aliasStringBuilder13Append2="c";
    private String aliasStringBuilder0Delete0="a";
    private String aliasStringBuilder0Delete1="b";
    private String aliasStringBuilder0DeleteCharAt0="a";
    private String aliasStringBuilder0Insert0="a";
    private String aliasStringBuilder0Insert1="b";
    private String aliasStringBuilder1Insert0="a";
    private String aliasStringBuilder1Insert1="b";
    private String aliasStringBuilder2Insert0="a";
    private String aliasStringBuilder2Insert1="b";
    private String aliasStringBuilder3Insert0="a";
    private String aliasStringBuilder3Insert1="b";
    private String aliasStringBuilder4Insert0="a";
    private String aliasStringBuilder4Insert1="b";
    private String aliasStringBuilder5Insert0="a";
    private String aliasStringBuilder5Insert1="b";
    private String aliasStringBuilder6Insert0="a";
    private String aliasStringBuilder6Insert1="b";
    private String aliasStringBuilder7Insert0="a";
    private String aliasStringBuilder7Insert1="b";
    private String aliasStringBuilder8Insert0="a";
    private String aliasStringBuilder8Insert1="b";
    private String aliasStringBuilder9Insert0="a";
    private String aliasStringBuilder9Insert1="b";
    private String aliasStringBuilder9Insert2="c";
    private String aliasStringBuilder9Insert3="d";
    private String aliasStringBuilder10Insert0="a";
    private String aliasStringBuilder10Insert1="b";
    private String aliasStringBuilder11Insert0="a";
    private String aliasStringBuilder11Insert1="b";
    private String aliasStringBuilder11Insert2="c";
    private String aliasStringBuilder11Insert3="d";
    private String aliasStringBuilder12Insert0="a";
    private String aliasStringBuilder12Insert1="b";
    private String aliasStringBuilder13Insert0="a";
    private String aliasStringBuilder13Insert1="b";
    private String aliasStringBuilder13Insert2="c";
    private String aliasStringBuilder13Insert3="d";
    private String aliasStringBuilder0Replace0="a";
    private String aliasStringBuilder0Replace1="b";
    private String aliasStringBuilder0Replace2="c";
    private String aliasStringBuilder0SetCharAt0="a";
    private String aliasStringBuilder0SetCharAt1="b";
    private String aliasStringBuilder0SetLength0="a";
    private String aliasStringBuilder0EnsureCapacity0="a";
    private String aliasStringBuilder0Same0="a";
    private String aliasStringBuilder0Same1="b";
    private String aliasStringBuilder0StringBuilder0="a";
    private String aliasStringBuilder1StringBuilder0="a";
    private String aliasStringBuilder2StringBuilder0="a";
    private String aliasReplacement0Replacement0="a";
    private String aliasReplacement0Replacement1="b";

    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setAliasCharSequence0SubSequence0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_SUB_SEQUENCE_0)));
        setAliasCharSequence0SubSequence1(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_SUB_SEQUENCE_1)));
        setAliasCharSequence0CharAt0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_CHAR_AT_0)));
        setAliasCharSequence0Substring0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_SUBSTRING_0)));
        setAliasCharSequence0Substring1(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_SUBSTRING_1)));
        setAliasCharSequence1Substring0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_1_SUBSTRING_0)));
        setAliasCharSequence0CompareTo0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_COMPARE_TO_0)));
        setAliasCharSequence0Contains0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_CONTAINS_0)));
        setAliasCharSequence0StartsWith0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_STARTS_WITH_0)));
        setAliasCharSequence1StartsWith0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_1_STARTS_WITH_0)));
        setAliasCharSequence1StartsWith1(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_1_STARTS_WITH_1)));
        setAliasCharSequence0EndsWith0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_ENDS_WITH_0)));
        setAliasCharSequence0IndexOf0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_INDEX_OF_0)));
        setAliasCharSequence1IndexOf0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_1_INDEX_OF_0)));
        setAliasCharSequence1IndexOf1(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_1_INDEX_OF_1)));
        setAliasCharSequence2IndexOf0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_2_INDEX_OF_0)));
        setAliasCharSequence3IndexOf0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_3_INDEX_OF_0)));
        setAliasCharSequence3IndexOf1(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_3_INDEX_OF_1)));
        setAliasCharSequence0LastIndexOf0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_LAST_INDEX_OF_0)));
        setAliasCharSequence1LastIndexOf0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_1_LAST_INDEX_OF_0)));
        setAliasCharSequence1LastIndexOf1(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_1_LAST_INDEX_OF_1)));
        setAliasCharSequence2LastIndexOf0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_2_LAST_INDEX_OF_0)));
        setAliasCharSequence3LastIndexOf0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_3_LAST_INDEX_OF_0)));
        setAliasCharSequence3LastIndexOf1(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_3_LAST_INDEX_OF_1)));
        setAliasCharSequence0Format0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_FORMAT_0)));
        setAliasCharSequence0Split0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_SPLIT_0)));
        setAliasCharSequence1Split0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_1_SPLIT_0)));
        setAliasCharSequence1Split1(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_1_SPLIT_1)));
        setAliasCharSequence2Split0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_2_SPLIT_0)));
        setAliasCharSequence3Split0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_3_SPLIT_0)));
        setAliasCharSequence3Split1(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_3_SPLIT_1)));
        setAliasCharSequence0SplitStrings0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_SPLIT_STRINGS_0)));
        setAliasCharSequence1SplitStrings0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_1_SPLIT_STRINGS_0)));
        setAliasCharSequence1SplitStrings1(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_1_SPLIT_STRINGS_1)));
        setAliasCharSequence0SplitChars0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_SPLIT_CHARS_0)));
        setAliasCharSequence0RegionMatches0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_REGION_MATCHES_0)));
        setAliasCharSequence0RegionMatches1(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_REGION_MATCHES_1)));
        setAliasCharSequence0RegionMatches2(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_REGION_MATCHES_2)));
        setAliasCharSequence0RegionMatches3(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_REGION_MATCHES_3)));
        setAliasCharSequence0Equals0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_EQUALS_0)));
        setAliasCharSequence0Equals1(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_SEQUENCE_0_EQUALS_1)));
        setAliasString0EqualsIgnoreCase0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_0_EQUALS_IGNORE_CASE_0)));
        setAliasString0Compare0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_0_COMPARE_0)));
        setAliasString0Compare1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_0_COMPARE_1)));
        setAliasString0CompareToIgnoreCase0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_0_COMPARE_TO_IGNORE_CASE_0)));
        setAliasString0ReplaceString0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_0_REPLACE_STRING_0)));
        setAliasString0ReplaceString1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_0_REPLACE_STRING_1)));
        setAliasString1ReplaceString0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_1_REPLACE_STRING_0)));
        setAliasString1ReplaceString1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_1_REPLACE_STRING_1)));
        setAliasString0ReplaceMultiple0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_0_REPLACE_MULTIPLE_0)));
        setAliasString0RegionMatches0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_0_REGION_MATCHES_0)));
        setAliasString0RegionMatches1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_0_REGION_MATCHES_1)));
        setAliasString0RegionMatches2(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_0_REGION_MATCHES_2)));
        setAliasString0RegionMatches3(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_0_REGION_MATCHES_3)));
        setAliasString0RegionMatches4(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_0_REGION_MATCHES_4)));
        setAliasString0ValueOfMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_0_VALUE_OF_METHOD_0)));
        setAliasString1ValueOfMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_1_VALUE_OF_METHOD_0)));
        setAliasString2ValueOfMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_2_VALUE_OF_METHOD_0)));
        setAliasString3ValueOfMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_3_VALUE_OF_METHOD_0)));
        setAliasString4ValueOfMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_4_VALUE_OF_METHOD_0)));
        setAliasString5ValueOfMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_5_VALUE_OF_METHOD_0)));
        setAliasString6ValueOfMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_6_VALUE_OF_METHOD_0)));
        setAliasString7ValueOfMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_7_VALUE_OF_METHOD_0)));
        setAliasString8ValueOfMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_8_VALUE_OF_METHOD_0)));
        setAliasString9ValueOfMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_9_VALUE_OF_METHOD_0)));
        setAliasString9ValueOfMethod1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_9_VALUE_OF_METHOD_1)));
        setAliasString9ValueOfMethod2(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_9_VALUE_OF_METHOD_2)));
        setAliasString0String0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_0_STRING_0)));
        setAliasString1String0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_1_STRING_0)));
        setAliasString1String1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_1_STRING_1)));
        setAliasString1String2(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_1_STRING_2)));
        setAliasString2String0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_2_STRING_0)));
        setAliasString3String0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_3_STRING_0)));
        setAliasString3String1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_3_STRING_1)));
        setAliasString3String2(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_3_STRING_2)));
        setAliasString4String0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_4_STRING_0)));
        setAliasStringBuilder0Append0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_0_APPEND_0)));
        setAliasStringBuilder1Append0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_1_APPEND_0)));
        setAliasStringBuilder2Append0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_2_APPEND_0)));
        setAliasStringBuilder3Append0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_3_APPEND_0)));
        setAliasStringBuilder4Append0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_4_APPEND_0)));
        setAliasStringBuilder5Append0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_5_APPEND_0)));
        setAliasStringBuilder6Append0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_6_APPEND_0)));
        setAliasStringBuilder7Append0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_7_APPEND_0)));
        setAliasStringBuilder8Append0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_8_APPEND_0)));
        setAliasStringBuilder9Append0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_9_APPEND_0)));
        setAliasStringBuilder9Append1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_9_APPEND_1)));
        setAliasStringBuilder9Append2(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_9_APPEND_2)));
        setAliasStringBuilder10Append0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_10_APPEND_0)));
        setAliasStringBuilder11Append0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_11_APPEND_0)));
        setAliasStringBuilder11Append1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_11_APPEND_1)));
        setAliasStringBuilder11Append2(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_11_APPEND_2)));
        setAliasStringBuilder12Append0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_12_APPEND_0)));
        setAliasStringBuilder13Append0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_13_APPEND_0)));
        setAliasStringBuilder13Append1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_13_APPEND_1)));
        setAliasStringBuilder13Append2(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_13_APPEND_2)));
        setAliasStringBuilder0Delete0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_0_DELETE_0)));
        setAliasStringBuilder0Delete1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_0_DELETE_1)));
        setAliasStringBuilder0DeleteCharAt0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_0_DELETE_CHAR_AT_0)));
        setAliasStringBuilder0Insert0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_0_INSERT_0)));
        setAliasStringBuilder0Insert1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_0_INSERT_1)));
        setAliasStringBuilder1Insert0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_1_INSERT_0)));
        setAliasStringBuilder1Insert1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_1_INSERT_1)));
        setAliasStringBuilder2Insert0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_2_INSERT_0)));
        setAliasStringBuilder2Insert1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_2_INSERT_1)));
        setAliasStringBuilder3Insert0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_3_INSERT_0)));
        setAliasStringBuilder3Insert1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_3_INSERT_1)));
        setAliasStringBuilder4Insert0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_4_INSERT_0)));
        setAliasStringBuilder4Insert1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_4_INSERT_1)));
        setAliasStringBuilder5Insert0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_5_INSERT_0)));
        setAliasStringBuilder5Insert1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_5_INSERT_1)));
        setAliasStringBuilder6Insert0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_6_INSERT_0)));
        setAliasStringBuilder6Insert1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_6_INSERT_1)));
        setAliasStringBuilder7Insert0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_7_INSERT_0)));
        setAliasStringBuilder7Insert1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_7_INSERT_1)));
        setAliasStringBuilder8Insert0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_8_INSERT_0)));
        setAliasStringBuilder8Insert1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_8_INSERT_1)));
        setAliasStringBuilder9Insert0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_9_INSERT_0)));
        setAliasStringBuilder9Insert1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_9_INSERT_1)));
        setAliasStringBuilder9Insert2(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_9_INSERT_2)));
        setAliasStringBuilder9Insert3(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_9_INSERT_3)));
        setAliasStringBuilder10Insert0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_10_INSERT_0)));
        setAliasStringBuilder10Insert1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_10_INSERT_1)));
        setAliasStringBuilder11Insert0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_11_INSERT_0)));
        setAliasStringBuilder11Insert1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_11_INSERT_1)));
        setAliasStringBuilder11Insert2(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_11_INSERT_2)));
        setAliasStringBuilder11Insert3(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_11_INSERT_3)));
        setAliasStringBuilder12Insert0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_12_INSERT_0)));
        setAliasStringBuilder12Insert1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_12_INSERT_1)));
        setAliasStringBuilder13Insert0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_13_INSERT_0)));
        setAliasStringBuilder13Insert1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_13_INSERT_1)));
        setAliasStringBuilder13Insert2(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_13_INSERT_2)));
        setAliasStringBuilder13Insert3(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_13_INSERT_3)));
        setAliasStringBuilder0Replace0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_0_REPLACE_0)));
        setAliasStringBuilder0Replace1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_0_REPLACE_1)));
        setAliasStringBuilder0Replace2(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_0_REPLACE_2)));
        setAliasStringBuilder0SetCharAt0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_0_SET_CHAR_AT_0)));
        setAliasStringBuilder0SetCharAt1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_0_SET_CHAR_AT_1)));
        setAliasStringBuilder0SetLength0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_0_SET_LENGTH_0)));
        setAliasStringBuilder0EnsureCapacity0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_0_ENSURE_CAPACITY_0)));
        setAliasStringBuilder0Same0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_0_SAME_0)));
        setAliasStringBuilder0Same1(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_0_SAME_1)));
        setAliasStringBuilder0StringBuilder0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_0_STRING_BUILDER_0)));
        setAliasStringBuilder1StringBuilder0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_1_STRING_BUILDER_0)));
        setAliasStringBuilder2StringBuilder0(LgNamesContent.get(_util,_cust,_mapping.getVal(STRING_BUILDER_2_STRING_BUILDER_0)));
        setAliasReplacement0Replacement0(LgNamesContent.get(_util,_cust,_mapping.getVal(REPLACEMENT_0_REPLACEMENT_0)));
        setAliasReplacement0Replacement1(LgNamesContent.get(_util,_cust,_mapping.getVal(REPLACEMENT_0_REPLACEMENT_1)));
    }
    public static void en(TranslationsFile _en){
        _en.add(CHAR_SEQUENCE_0_SUB_SEQUENCE_0,"CharSequence0SubSequence0=a");
        _en.add(CHAR_SEQUENCE_0_SUB_SEQUENCE_1,"CharSequence0SubSequence1=b");
        _en.add(CHAR_SEQUENCE_0_CHAR_AT_0,"CharSequence0CharAt0=a");
        _en.add(CHAR_SEQUENCE_0_SUBSTRING_0,"CharSequence0Substring0=a");
        _en.add(CHAR_SEQUENCE_0_SUBSTRING_1,"CharSequence0Substring1=b");
        _en.add(CHAR_SEQUENCE_1_SUBSTRING_0,"CharSequence1Substring0=a");
        _en.add(CHAR_SEQUENCE_0_COMPARE_TO_0,"CharSequence0CompareTo0=a");
        _en.add(CHAR_SEQUENCE_0_CONTAINS_0,"CharSequence0Contains0=a");
        _en.add(CHAR_SEQUENCE_0_STARTS_WITH_0,"CharSequence0StartsWith0=a");
        _en.add(CHAR_SEQUENCE_1_STARTS_WITH_0,"CharSequence1StartsWith0=a");
        _en.add(CHAR_SEQUENCE_1_STARTS_WITH_1,"CharSequence1StartsWith1=b");
        _en.add(CHAR_SEQUENCE_0_ENDS_WITH_0,"CharSequence0EndsWith0=a");
        _en.add(CHAR_SEQUENCE_0_INDEX_OF_0,"CharSequence0IndexOf0=a");
        _en.add(CHAR_SEQUENCE_1_INDEX_OF_0,"CharSequence1IndexOf0=a");
        _en.add(CHAR_SEQUENCE_1_INDEX_OF_1,"CharSequence1IndexOf1=b");
        _en.add(CHAR_SEQUENCE_2_INDEX_OF_0,"CharSequence2IndexOf0=a");
        _en.add(CHAR_SEQUENCE_3_INDEX_OF_0,"CharSequence3IndexOf0=a");
        _en.add(CHAR_SEQUENCE_3_INDEX_OF_1,"CharSequence3IndexOf1=b");
        _en.add(CHAR_SEQUENCE_0_LAST_INDEX_OF_0,"CharSequence0LastIndexOf0=a");
        _en.add(CHAR_SEQUENCE_1_LAST_INDEX_OF_0,"CharSequence1LastIndexOf0=a");
        _en.add(CHAR_SEQUENCE_1_LAST_INDEX_OF_1,"CharSequence1LastIndexOf1=b");
        _en.add(CHAR_SEQUENCE_2_LAST_INDEX_OF_0,"CharSequence2LastIndexOf0=a");
        _en.add(CHAR_SEQUENCE_3_LAST_INDEX_OF_0,"CharSequence3LastIndexOf0=a");
        _en.add(CHAR_SEQUENCE_3_LAST_INDEX_OF_1,"CharSequence3LastIndexOf1=b");
        _en.add(CHAR_SEQUENCE_0_FORMAT_0,"CharSequence0Format0=a");
        _en.add(CHAR_SEQUENCE_0_SPLIT_0,"CharSequence0Split0=a");
        _en.add(CHAR_SEQUENCE_1_SPLIT_0,"CharSequence1Split0=a");
        _en.add(CHAR_SEQUENCE_1_SPLIT_1,"CharSequence1Split1=b");
        _en.add(CHAR_SEQUENCE_2_SPLIT_0,"CharSequence2Split0=a");
        _en.add(CHAR_SEQUENCE_3_SPLIT_0,"CharSequence3Split0=a");
        _en.add(CHAR_SEQUENCE_3_SPLIT_1,"CharSequence3Split1=b");
        _en.add(CHAR_SEQUENCE_0_SPLIT_STRINGS_0,"CharSequence0SplitStrings0=a");
        _en.add(CHAR_SEQUENCE_1_SPLIT_STRINGS_0,"CharSequence1SplitStrings0=a");
        _en.add(CHAR_SEQUENCE_1_SPLIT_STRINGS_1,"CharSequence1SplitStrings1=b");
        _en.add(CHAR_SEQUENCE_0_SPLIT_CHARS_0,"CharSequence0SplitChars0=a");
        _en.add(CHAR_SEQUENCE_0_REGION_MATCHES_0,"CharSequence0RegionMatches0=a");
        _en.add(CHAR_SEQUENCE_0_REGION_MATCHES_1,"CharSequence0RegionMatches1=b");
        _en.add(CHAR_SEQUENCE_0_REGION_MATCHES_2,"CharSequence0RegionMatches2=c");
        _en.add(CHAR_SEQUENCE_0_REGION_MATCHES_3,"CharSequence0RegionMatches3=d");
        _en.add(CHAR_SEQUENCE_0_EQUALS_0,"CharSequence0Equals0=a");
        _en.add(CHAR_SEQUENCE_0_EQUALS_1,"CharSequence0Equals1=b");
        _en.add(STRING_0_EQUALS_IGNORE_CASE_0,"String0EqualsIgnoreCase0=a");
        _en.add(STRING_0_COMPARE_0,"String0Compare0=a");
        _en.add(STRING_0_COMPARE_1,"String0Compare1=b");
        _en.add(STRING_0_COMPARE_TO_IGNORE_CASE_0,"String0CompareToIgnoreCase0=a");
        _en.add(STRING_0_REPLACE_STRING_0,"String0ReplaceString0=a");
        _en.add(STRING_0_REPLACE_STRING_1,"String0ReplaceString1=b");
        _en.add(STRING_1_REPLACE_STRING_0,"String1ReplaceString0=a");
        _en.add(STRING_1_REPLACE_STRING_1,"String1ReplaceString1=b");
        _en.add(STRING_0_REPLACE_MULTIPLE_0,"String0ReplaceMultiple0=a");
        _en.add(STRING_0_REGION_MATCHES_0,"String0RegionMatches0=a");
        _en.add(STRING_0_REGION_MATCHES_1,"String0RegionMatches1=b");
        _en.add(STRING_0_REGION_MATCHES_2,"String0RegionMatches2=c");
        _en.add(STRING_0_REGION_MATCHES_3,"String0RegionMatches3=d");
        _en.add(STRING_0_REGION_MATCHES_4,"String0RegionMatches4=e");
        _en.add(STRING_0_VALUE_OF_METHOD_0,"String0ValueOfMethod0=a");
        _en.add(STRING_1_VALUE_OF_METHOD_0,"String1ValueOfMethod0=a");
        _en.add(STRING_2_VALUE_OF_METHOD_0,"String2ValueOfMethod0=a");
        _en.add(STRING_3_VALUE_OF_METHOD_0,"String3ValueOfMethod0=a");
        _en.add(STRING_4_VALUE_OF_METHOD_0,"String4ValueOfMethod0=a");
        _en.add(STRING_5_VALUE_OF_METHOD_0,"String5ValueOfMethod0=a");
        _en.add(STRING_6_VALUE_OF_METHOD_0,"String6ValueOfMethod0=a");
        _en.add(STRING_7_VALUE_OF_METHOD_0,"String7ValueOfMethod0=a");
        _en.add(STRING_8_VALUE_OF_METHOD_0,"String8ValueOfMethod0=a");
        _en.add(STRING_9_VALUE_OF_METHOD_0,"String9ValueOfMethod0=a");
        _en.add(STRING_9_VALUE_OF_METHOD_1,"String9ValueOfMethod1=b");
        _en.add(STRING_9_VALUE_OF_METHOD_2,"String9ValueOfMethod2=c");
        _en.add(STRING_0_STRING_0,"String0String0=a");
        _en.add(STRING_1_STRING_0,"String1String0=a");
        _en.add(STRING_1_STRING_1,"String1String1=b");
        _en.add(STRING_1_STRING_2,"String1String2=c");
        _en.add(STRING_2_STRING_0,"String2String0=a");
        _en.add(STRING_3_STRING_0,"String3String0=a");
        _en.add(STRING_3_STRING_1,"String3String1=b");
        _en.add(STRING_3_STRING_2,"String3String2=c");
        _en.add(STRING_4_STRING_0,"String4String0=a");
        _en.add(STRING_BUILDER_0_APPEND_0,"StringBuilder0Append0=a");
        _en.add(STRING_BUILDER_1_APPEND_0,"StringBuilder1Append0=a");
        _en.add(STRING_BUILDER_2_APPEND_0,"StringBuilder2Append0=a");
        _en.add(STRING_BUILDER_3_APPEND_0,"StringBuilder3Append0=a");
        _en.add(STRING_BUILDER_4_APPEND_0,"StringBuilder4Append0=a");
        _en.add(STRING_BUILDER_5_APPEND_0,"StringBuilder5Append0=a");
        _en.add(STRING_BUILDER_6_APPEND_0,"StringBuilder6Append0=a");
        _en.add(STRING_BUILDER_7_APPEND_0,"StringBuilder7Append0=a");
        _en.add(STRING_BUILDER_8_APPEND_0,"StringBuilder8Append0=a");
        _en.add(STRING_BUILDER_9_APPEND_0,"StringBuilder9Append0=a");
        _en.add(STRING_BUILDER_9_APPEND_1,"StringBuilder9Append1=b");
        _en.add(STRING_BUILDER_9_APPEND_2,"StringBuilder9Append2=c");
        _en.add(STRING_BUILDER_10_APPEND_0,"StringBuilder10Append0=a");
        _en.add(STRING_BUILDER_11_APPEND_0,"StringBuilder11Append0=a");
        _en.add(STRING_BUILDER_11_APPEND_1,"StringBuilder11Append1=b");
        _en.add(STRING_BUILDER_11_APPEND_2,"StringBuilder11Append2=c");
        _en.add(STRING_BUILDER_12_APPEND_0,"StringBuilder12Append0=a");
        _en.add(STRING_BUILDER_13_APPEND_0,"StringBuilder13Append0=a");
        _en.add(STRING_BUILDER_13_APPEND_1,"StringBuilder13Append1=b");
        _en.add(STRING_BUILDER_13_APPEND_2,"StringBuilder13Append2=c");
        _en.add(STRING_BUILDER_0_DELETE_0,"StringBuilder0Delete0=a");
        _en.add(STRING_BUILDER_0_DELETE_1,"StringBuilder0Delete1=b");
        _en.add(STRING_BUILDER_0_DELETE_CHAR_AT_0,"StringBuilder0DeleteCharAt0=a");
        _en.add(STRING_BUILDER_0_INSERT_0,"StringBuilder0Insert0=a");
        _en.add(STRING_BUILDER_0_INSERT_1,"StringBuilder0Insert1=b");
        _en.add(STRING_BUILDER_1_INSERT_0,"StringBuilder1Insert0=a");
        _en.add(STRING_BUILDER_1_INSERT_1,"StringBuilder1Insert1=b");
        _en.add(STRING_BUILDER_2_INSERT_0,"StringBuilder2Insert0=a");
        _en.add(STRING_BUILDER_2_INSERT_1,"StringBuilder2Insert1=b");
        _en.add(STRING_BUILDER_3_INSERT_0,"StringBuilder3Insert0=a");
        _en.add(STRING_BUILDER_3_INSERT_1,"StringBuilder3Insert1=b");
        _en.add(STRING_BUILDER_4_INSERT_0,"StringBuilder4Insert0=a");
        _en.add(STRING_BUILDER_4_INSERT_1,"StringBuilder4Insert1=b");
        _en.add(STRING_BUILDER_5_INSERT_0,"StringBuilder5Insert0=a");
        _en.add(STRING_BUILDER_5_INSERT_1,"StringBuilder5Insert1=b");
        _en.add(STRING_BUILDER_6_INSERT_0,"StringBuilder6Insert0=a");
        _en.add(STRING_BUILDER_6_INSERT_1,"StringBuilder6Insert1=b");
        _en.add(STRING_BUILDER_7_INSERT_0,"StringBuilder7Insert0=a");
        _en.add(STRING_BUILDER_7_INSERT_1,"StringBuilder7Insert1=b");
        _en.add(STRING_BUILDER_8_INSERT_0,"StringBuilder8Insert0=a");
        _en.add(STRING_BUILDER_8_INSERT_1,"StringBuilder8Insert1=b");
        _en.add(STRING_BUILDER_9_INSERT_0,"StringBuilder9Insert0=a");
        _en.add(STRING_BUILDER_9_INSERT_1,"StringBuilder9Insert1=b");
        _en.add(STRING_BUILDER_9_INSERT_2,"StringBuilder9Insert2=c");
        _en.add(STRING_BUILDER_9_INSERT_3,"StringBuilder9Insert3=d");
        _en.add(STRING_BUILDER_10_INSERT_0,"StringBuilder10Insert0=a");
        _en.add(STRING_BUILDER_10_INSERT_1,"StringBuilder10Insert1=b");
        _en.add(STRING_BUILDER_11_INSERT_0,"StringBuilder11Insert0=a");
        _en.add(STRING_BUILDER_11_INSERT_1,"StringBuilder11Insert1=b");
        _en.add(STRING_BUILDER_11_INSERT_2,"StringBuilder11Insert2=c");
        _en.add(STRING_BUILDER_11_INSERT_3,"StringBuilder11Insert3=d");
        _en.add(STRING_BUILDER_12_INSERT_0,"StringBuilder12Insert0=a");
        _en.add(STRING_BUILDER_12_INSERT_1,"StringBuilder12Insert1=b");
        _en.add(STRING_BUILDER_13_INSERT_0,"StringBuilder13Insert0=a");
        _en.add(STRING_BUILDER_13_INSERT_1,"StringBuilder13Insert1=b");
        _en.add(STRING_BUILDER_13_INSERT_2,"StringBuilder13Insert2=c");
        _en.add(STRING_BUILDER_13_INSERT_3,"StringBuilder13Insert3=d");
        _en.add(STRING_BUILDER_0_REPLACE_0,"StringBuilder0Replace0=a");
        _en.add(STRING_BUILDER_0_REPLACE_1,"StringBuilder0Replace1=b");
        _en.add(STRING_BUILDER_0_REPLACE_2,"StringBuilder0Replace2=c");
        _en.add(STRING_BUILDER_0_SET_CHAR_AT_0,"StringBuilder0SetCharAt0=a");
        _en.add(STRING_BUILDER_0_SET_CHAR_AT_1,"StringBuilder0SetCharAt1=b");
        _en.add(STRING_BUILDER_0_SET_LENGTH_0,"StringBuilder0SetLength0=a");
        _en.add(STRING_BUILDER_0_ENSURE_CAPACITY_0,"StringBuilder0EnsureCapacity0=a");
        _en.add(STRING_BUILDER_0_SAME_0,"StringBuilder0Same0=a");
        _en.add(STRING_BUILDER_0_SAME_1,"StringBuilder0Same1=b");
        _en.add(STRING_BUILDER_0_STRING_BUILDER_0,"StringBuilder0StringBuilder0=a");
        _en.add(STRING_BUILDER_1_STRING_BUILDER_0,"StringBuilder1StringBuilder0=a");
        _en.add(STRING_BUILDER_2_STRING_BUILDER_0,"StringBuilder2StringBuilder0=a");
        _en.add(REPLACEMENT_0_REPLACEMENT_0,"Replacement0Replacement0=a");
        _en.add(REPLACEMENT_0_REPLACEMENT_1,"Replacement0Replacement1=b");
    }
    public static void fr(TranslationsFile _fr){
        _fr.add(CHAR_SEQUENCE_0_SUB_SEQUENCE_0,"CharSequence0SubSequence0=a");
        _fr.add(CHAR_SEQUENCE_0_SUB_SEQUENCE_1,"CharSequence0SubSequence1=b");
        _fr.add(CHAR_SEQUENCE_0_CHAR_AT_0,"CharSequence0CharAt0=a");
        _fr.add(CHAR_SEQUENCE_0_SUBSTRING_0,"CharSequence0Substring0=a");
        _fr.add(CHAR_SEQUENCE_0_SUBSTRING_1,"CharSequence0Substring1=b");
        _fr.add(CHAR_SEQUENCE_1_SUBSTRING_0,"CharSequence1Substring0=a");
        _fr.add(CHAR_SEQUENCE_0_COMPARE_TO_0,"CharSequence0CompareTo0=a");
        _fr.add(CHAR_SEQUENCE_0_CONTAINS_0,"CharSequence0Contains0=a");
        _fr.add(CHAR_SEQUENCE_0_STARTS_WITH_0,"CharSequence0StartsWith0=a");
        _fr.add(CHAR_SEQUENCE_1_STARTS_WITH_0,"CharSequence1StartsWith0=a");
        _fr.add(CHAR_SEQUENCE_1_STARTS_WITH_1,"CharSequence1StartsWith1=b");
        _fr.add(CHAR_SEQUENCE_0_ENDS_WITH_0,"CharSequence0EndsWith0=a");
        _fr.add(CHAR_SEQUENCE_0_INDEX_OF_0,"CharSequence0IndexOf0=a");
        _fr.add(CHAR_SEQUENCE_1_INDEX_OF_0,"CharSequence1IndexOf0=a");
        _fr.add(CHAR_SEQUENCE_1_INDEX_OF_1,"CharSequence1IndexOf1=b");
        _fr.add(CHAR_SEQUENCE_2_INDEX_OF_0,"CharSequence2IndexOf0=a");
        _fr.add(CHAR_SEQUENCE_3_INDEX_OF_0,"CharSequence3IndexOf0=a");
        _fr.add(CHAR_SEQUENCE_3_INDEX_OF_1,"CharSequence3IndexOf1=b");
        _fr.add(CHAR_SEQUENCE_0_LAST_INDEX_OF_0,"CharSequence0LastIndexOf0=a");
        _fr.add(CHAR_SEQUENCE_1_LAST_INDEX_OF_0,"CharSequence1LastIndexOf0=a");
        _fr.add(CHAR_SEQUENCE_1_LAST_INDEX_OF_1,"CharSequence1LastIndexOf1=b");
        _fr.add(CHAR_SEQUENCE_2_LAST_INDEX_OF_0,"CharSequence2LastIndexOf0=a");
        _fr.add(CHAR_SEQUENCE_3_LAST_INDEX_OF_0,"CharSequence3LastIndexOf0=a");
        _fr.add(CHAR_SEQUENCE_3_LAST_INDEX_OF_1,"CharSequence3LastIndexOf1=b");
        _fr.add(CHAR_SEQUENCE_0_FORMAT_0,"CharSequence0Format0=a");
        _fr.add(CHAR_SEQUENCE_0_SPLIT_0,"CharSequence0Split0=a");
        _fr.add(CHAR_SEQUENCE_1_SPLIT_0,"CharSequence1Split0=a");
        _fr.add(CHAR_SEQUENCE_1_SPLIT_1,"CharSequence1Split1=b");
        _fr.add(CHAR_SEQUENCE_2_SPLIT_0,"CharSequence2Split0=a");
        _fr.add(CHAR_SEQUENCE_3_SPLIT_0,"CharSequence3Split0=a");
        _fr.add(CHAR_SEQUENCE_3_SPLIT_1,"CharSequence3Split1=b");
        _fr.add(CHAR_SEQUENCE_0_SPLIT_STRINGS_0,"CharSequence0SplitStrings0=a");
        _fr.add(CHAR_SEQUENCE_1_SPLIT_STRINGS_0,"CharSequence1SplitStrings0=a");
        _fr.add(CHAR_SEQUENCE_1_SPLIT_STRINGS_1,"CharSequence1SplitStrings1=b");
        _fr.add(CHAR_SEQUENCE_0_SPLIT_CHARS_0,"CharSequence0SplitChars0=a");
        _fr.add(CHAR_SEQUENCE_0_REGION_MATCHES_0,"CharSequence0RegionMatches0=a");
        _fr.add(CHAR_SEQUENCE_0_REGION_MATCHES_1,"CharSequence0RegionMatches1=b");
        _fr.add(CHAR_SEQUENCE_0_REGION_MATCHES_2,"CharSequence0RegionMatches2=c");
        _fr.add(CHAR_SEQUENCE_0_REGION_MATCHES_3,"CharSequence0RegionMatches3=d");
        _fr.add(CHAR_SEQUENCE_0_EQUALS_0,"CharSequence0Equals0=a");
        _fr.add(CHAR_SEQUENCE_0_EQUALS_1,"CharSequence0Equals1=b");
        _fr.add(STRING_0_EQUALS_IGNORE_CASE_0,"String0EqualsIgnoreCase0=a");
        _fr.add(STRING_0_COMPARE_0,"String0Compare0=a");
        _fr.add(STRING_0_COMPARE_1,"String0Compare1=b");
        _fr.add(STRING_0_COMPARE_TO_IGNORE_CASE_0,"String0CompareToIgnoreCase0=a");
        _fr.add(STRING_0_REPLACE_STRING_0,"String0ReplaceString0=a");
        _fr.add(STRING_0_REPLACE_STRING_1,"String0ReplaceString1=b");
        _fr.add(STRING_1_REPLACE_STRING_0,"String1ReplaceString0=a");
        _fr.add(STRING_1_REPLACE_STRING_1,"String1ReplaceString1=b");
        _fr.add(STRING_0_REPLACE_MULTIPLE_0,"String0ReplaceMultiple0=a");
        _fr.add(STRING_0_REGION_MATCHES_0,"String0RegionMatches0=a");
        _fr.add(STRING_0_REGION_MATCHES_1,"String0RegionMatches1=b");
        _fr.add(STRING_0_REGION_MATCHES_2,"String0RegionMatches2=c");
        _fr.add(STRING_0_REGION_MATCHES_3,"String0RegionMatches3=d");
        _fr.add(STRING_0_REGION_MATCHES_4,"String0RegionMatches4=e");
        _fr.add(STRING_0_VALUE_OF_METHOD_0,"String0ValueOfMethod0=a");
        _fr.add(STRING_1_VALUE_OF_METHOD_0,"String1ValueOfMethod0=a");
        _fr.add(STRING_2_VALUE_OF_METHOD_0,"String2ValueOfMethod0=a");
        _fr.add(STRING_3_VALUE_OF_METHOD_0,"String3ValueOfMethod0=a");
        _fr.add(STRING_4_VALUE_OF_METHOD_0,"String4ValueOfMethod0=a");
        _fr.add(STRING_5_VALUE_OF_METHOD_0,"String5ValueOfMethod0=a");
        _fr.add(STRING_6_VALUE_OF_METHOD_0,"String6ValueOfMethod0=a");
        _fr.add(STRING_7_VALUE_OF_METHOD_0,"String7ValueOfMethod0=a");
        _fr.add(STRING_8_VALUE_OF_METHOD_0,"String8ValueOfMethod0=a");
        _fr.add(STRING_9_VALUE_OF_METHOD_0,"String9ValueOfMethod0=a");
        _fr.add(STRING_9_VALUE_OF_METHOD_1,"String9ValueOfMethod1=b");
        _fr.add(STRING_9_VALUE_OF_METHOD_2,"String9ValueOfMethod2=c");
        _fr.add(STRING_0_STRING_0,"String0String0=a");
        _fr.add(STRING_1_STRING_0,"String1String0=a");
        _fr.add(STRING_1_STRING_1,"String1String1=b");
        _fr.add(STRING_1_STRING_2,"String1String2=c");
        _fr.add(STRING_2_STRING_0,"String2String0=a");
        _fr.add(STRING_3_STRING_0,"String3String0=a");
        _fr.add(STRING_3_STRING_1,"String3String1=b");
        _fr.add(STRING_3_STRING_2,"String3String2=c");
        _fr.add(STRING_4_STRING_0,"String4String0=a");
        _fr.add(STRING_BUILDER_0_APPEND_0,"StringBuilder0Append0=a");
        _fr.add(STRING_BUILDER_1_APPEND_0,"StringBuilder1Append0=a");
        _fr.add(STRING_BUILDER_2_APPEND_0,"StringBuilder2Append0=a");
        _fr.add(STRING_BUILDER_3_APPEND_0,"StringBuilder3Append0=a");
        _fr.add(STRING_BUILDER_4_APPEND_0,"StringBuilder4Append0=a");
        _fr.add(STRING_BUILDER_5_APPEND_0,"StringBuilder5Append0=a");
        _fr.add(STRING_BUILDER_6_APPEND_0,"StringBuilder6Append0=a");
        _fr.add(STRING_BUILDER_7_APPEND_0,"StringBuilder7Append0=a");
        _fr.add(STRING_BUILDER_8_APPEND_0,"StringBuilder8Append0=a");
        _fr.add(STRING_BUILDER_9_APPEND_0,"StringBuilder9Append0=a");
        _fr.add(STRING_BUILDER_9_APPEND_1,"StringBuilder9Append1=b");
        _fr.add(STRING_BUILDER_9_APPEND_2,"StringBuilder9Append2=c");
        _fr.add(STRING_BUILDER_10_APPEND_0,"StringBuilder10Append0=a");
        _fr.add(STRING_BUILDER_11_APPEND_0,"StringBuilder11Append0=a");
        _fr.add(STRING_BUILDER_11_APPEND_1,"StringBuilder11Append1=b");
        _fr.add(STRING_BUILDER_11_APPEND_2,"StringBuilder11Append2=c");
        _fr.add(STRING_BUILDER_12_APPEND_0,"StringBuilder12Append0=a");
        _fr.add(STRING_BUILDER_13_APPEND_0,"StringBuilder13Append0=a");
        _fr.add(STRING_BUILDER_13_APPEND_1,"StringBuilder13Append1=b");
        _fr.add(STRING_BUILDER_13_APPEND_2,"StringBuilder13Append2=c");
        _fr.add(STRING_BUILDER_0_DELETE_0,"StringBuilder0Delete0=a");
        _fr.add(STRING_BUILDER_0_DELETE_1,"StringBuilder0Delete1=b");
        _fr.add(STRING_BUILDER_0_DELETE_CHAR_AT_0,"StringBuilder0DeleteCharAt0=a");
        _fr.add(STRING_BUILDER_0_INSERT_0,"StringBuilder0Insert0=a");
        _fr.add(STRING_BUILDER_0_INSERT_1,"StringBuilder0Insert1=b");
        _fr.add(STRING_BUILDER_1_INSERT_0,"StringBuilder1Insert0=a");
        _fr.add(STRING_BUILDER_1_INSERT_1,"StringBuilder1Insert1=b");
        _fr.add(STRING_BUILDER_2_INSERT_0,"StringBuilder2Insert0=a");
        _fr.add(STRING_BUILDER_2_INSERT_1,"StringBuilder2Insert1=b");
        _fr.add(STRING_BUILDER_3_INSERT_0,"StringBuilder3Insert0=a");
        _fr.add(STRING_BUILDER_3_INSERT_1,"StringBuilder3Insert1=b");
        _fr.add(STRING_BUILDER_4_INSERT_0,"StringBuilder4Insert0=a");
        _fr.add(STRING_BUILDER_4_INSERT_1,"StringBuilder4Insert1=b");
        _fr.add(STRING_BUILDER_5_INSERT_0,"StringBuilder5Insert0=a");
        _fr.add(STRING_BUILDER_5_INSERT_1,"StringBuilder5Insert1=b");
        _fr.add(STRING_BUILDER_6_INSERT_0,"StringBuilder6Insert0=a");
        _fr.add(STRING_BUILDER_6_INSERT_1,"StringBuilder6Insert1=b");
        _fr.add(STRING_BUILDER_7_INSERT_0,"StringBuilder7Insert0=a");
        _fr.add(STRING_BUILDER_7_INSERT_1,"StringBuilder7Insert1=b");
        _fr.add(STRING_BUILDER_8_INSERT_0,"StringBuilder8Insert0=a");
        _fr.add(STRING_BUILDER_8_INSERT_1,"StringBuilder8Insert1=b");
        _fr.add(STRING_BUILDER_9_INSERT_0,"StringBuilder9Insert0=a");
        _fr.add(STRING_BUILDER_9_INSERT_1,"StringBuilder9Insert1=b");
        _fr.add(STRING_BUILDER_9_INSERT_2,"StringBuilder9Insert2=c");
        _fr.add(STRING_BUILDER_9_INSERT_3,"StringBuilder9Insert3=d");
        _fr.add(STRING_BUILDER_10_INSERT_0,"StringBuilder10Insert0=a");
        _fr.add(STRING_BUILDER_10_INSERT_1,"StringBuilder10Insert1=b");
        _fr.add(STRING_BUILDER_11_INSERT_0,"StringBuilder11Insert0=a");
        _fr.add(STRING_BUILDER_11_INSERT_1,"StringBuilder11Insert1=b");
        _fr.add(STRING_BUILDER_11_INSERT_2,"StringBuilder11Insert2=c");
        _fr.add(STRING_BUILDER_11_INSERT_3,"StringBuilder11Insert3=d");
        _fr.add(STRING_BUILDER_12_INSERT_0,"StringBuilder12Insert0=a");
        _fr.add(STRING_BUILDER_12_INSERT_1,"StringBuilder12Insert1=b");
        _fr.add(STRING_BUILDER_13_INSERT_0,"StringBuilder13Insert0=a");
        _fr.add(STRING_BUILDER_13_INSERT_1,"StringBuilder13Insert1=b");
        _fr.add(STRING_BUILDER_13_INSERT_2,"StringBuilder13Insert2=c");
        _fr.add(STRING_BUILDER_13_INSERT_3,"StringBuilder13Insert3=d");
        _fr.add(STRING_BUILDER_0_REPLACE_0,"StringBuilder0Replace0=a");
        _fr.add(STRING_BUILDER_0_REPLACE_1,"StringBuilder0Replace1=b");
        _fr.add(STRING_BUILDER_0_REPLACE_2,"StringBuilder0Replace2=c");
        _fr.add(STRING_BUILDER_0_SET_CHAR_AT_0,"StringBuilder0SetCharAt0=a");
        _fr.add(STRING_BUILDER_0_SET_CHAR_AT_1,"StringBuilder0SetCharAt1=b");
        _fr.add(STRING_BUILDER_0_SET_LENGTH_0,"StringBuilder0SetLength0=a");
        _fr.add(STRING_BUILDER_0_ENSURE_CAPACITY_0,"StringBuilder0EnsureCapacity0=a");
        _fr.add(STRING_BUILDER_0_SAME_0,"StringBuilder0Same0=a");
        _fr.add(STRING_BUILDER_0_SAME_1,"StringBuilder0Same1=b");
        _fr.add(STRING_BUILDER_0_STRING_BUILDER_0,"StringBuilder0StringBuilder0=a");
        _fr.add(STRING_BUILDER_1_STRING_BUILDER_0,"StringBuilder1StringBuilder0=a");
        _fr.add(STRING_BUILDER_2_STRING_BUILDER_0,"StringBuilder2StringBuilder0=a");
        _fr.add(REPLACEMENT_0_REPLACEMENT_0,"Replacement0Replacement0=a");
        _fr.add(REPLACEMENT_0_REPLACEMENT_1,"Replacement0Replacement1=b");
    }
    public static void mapping(StringMap<String> _m){
        _m.addEntry(CHAR_SEQUENCE_0_SUB_SEQUENCE_0,"CharSequence0SubSequence0");
        _m.addEntry(CHAR_SEQUENCE_0_SUB_SEQUENCE_1,"CharSequence0SubSequence1");
        _m.addEntry(CHAR_SEQUENCE_0_CHAR_AT_0,"CharSequence0CharAt0");
        _m.addEntry(CHAR_SEQUENCE_0_SUBSTRING_0,"CharSequence0Substring0");
        _m.addEntry(CHAR_SEQUENCE_0_SUBSTRING_1,"CharSequence0Substring1");
        _m.addEntry(CHAR_SEQUENCE_1_SUBSTRING_0,"CharSequence1Substring0");
        _m.addEntry(CHAR_SEQUENCE_0_COMPARE_TO_0,"CharSequence0CompareTo0");
        _m.addEntry(CHAR_SEQUENCE_0_CONTAINS_0,"CharSequence0Contains0");
        _m.addEntry(CHAR_SEQUENCE_0_STARTS_WITH_0,"CharSequence0StartsWith0");
        _m.addEntry(CHAR_SEQUENCE_1_STARTS_WITH_0,"CharSequence1StartsWith0");
        _m.addEntry(CHAR_SEQUENCE_1_STARTS_WITH_1,"CharSequence1StartsWith1");
        _m.addEntry(CHAR_SEQUENCE_0_ENDS_WITH_0,"CharSequence0EndsWith0");
        _m.addEntry(CHAR_SEQUENCE_0_INDEX_OF_0,"CharSequence0IndexOf0");
        _m.addEntry(CHAR_SEQUENCE_1_INDEX_OF_0,"CharSequence1IndexOf0");
        _m.addEntry(CHAR_SEQUENCE_1_INDEX_OF_1,"CharSequence1IndexOf1");
        _m.addEntry(CHAR_SEQUENCE_2_INDEX_OF_0,"CharSequence2IndexOf0");
        _m.addEntry(CHAR_SEQUENCE_3_INDEX_OF_0,"CharSequence3IndexOf0");
        _m.addEntry(CHAR_SEQUENCE_3_INDEX_OF_1,"CharSequence3IndexOf1");
        _m.addEntry(CHAR_SEQUENCE_0_LAST_INDEX_OF_0,"CharSequence0LastIndexOf0");
        _m.addEntry(CHAR_SEQUENCE_1_LAST_INDEX_OF_0,"CharSequence1LastIndexOf0");
        _m.addEntry(CHAR_SEQUENCE_1_LAST_INDEX_OF_1,"CharSequence1LastIndexOf1");
        _m.addEntry(CHAR_SEQUENCE_2_LAST_INDEX_OF_0,"CharSequence2LastIndexOf0");
        _m.addEntry(CHAR_SEQUENCE_3_LAST_INDEX_OF_0,"CharSequence3LastIndexOf0");
        _m.addEntry(CHAR_SEQUENCE_3_LAST_INDEX_OF_1,"CharSequence3LastIndexOf1");
        _m.addEntry(CHAR_SEQUENCE_0_FORMAT_0,"CharSequence0Format0");
        _m.addEntry(CHAR_SEQUENCE_0_SPLIT_0,"CharSequence0Split0");
        _m.addEntry(CHAR_SEQUENCE_1_SPLIT_0,"CharSequence1Split0");
        _m.addEntry(CHAR_SEQUENCE_1_SPLIT_1,"CharSequence1Split1");
        _m.addEntry(CHAR_SEQUENCE_2_SPLIT_0,"CharSequence2Split0");
        _m.addEntry(CHAR_SEQUENCE_3_SPLIT_0,"CharSequence3Split0");
        _m.addEntry(CHAR_SEQUENCE_3_SPLIT_1,"CharSequence3Split1");
        _m.addEntry(CHAR_SEQUENCE_0_SPLIT_STRINGS_0,"CharSequence0SplitStrings0");
        _m.addEntry(CHAR_SEQUENCE_1_SPLIT_STRINGS_0,"CharSequence1SplitStrings0");
        _m.addEntry(CHAR_SEQUENCE_1_SPLIT_STRINGS_1,"CharSequence1SplitStrings1");
        _m.addEntry(CHAR_SEQUENCE_0_SPLIT_CHARS_0,"CharSequence0SplitChars0");
        _m.addEntry(CHAR_SEQUENCE_0_REGION_MATCHES_0,"CharSequence0RegionMatches0");
        _m.addEntry(CHAR_SEQUENCE_0_REGION_MATCHES_1,"CharSequence0RegionMatches1");
        _m.addEntry(CHAR_SEQUENCE_0_REGION_MATCHES_2,"CharSequence0RegionMatches2");
        _m.addEntry(CHAR_SEQUENCE_0_REGION_MATCHES_3,"CharSequence0RegionMatches3");
        _m.addEntry(CHAR_SEQUENCE_0_EQUALS_0,"CharSequence0Equals0");
        _m.addEntry(CHAR_SEQUENCE_0_EQUALS_1,"CharSequence0Equals1");
        _m.addEntry(STRING_0_EQUALS_IGNORE_CASE_0,"String0EqualsIgnoreCase0");
        _m.addEntry(STRING_0_COMPARE_0,"String0Compare0");
        _m.addEntry(STRING_0_COMPARE_1,"String0Compare1");
        _m.addEntry(STRING_0_COMPARE_TO_IGNORE_CASE_0,"String0CompareToIgnoreCase0");
        _m.addEntry(STRING_0_REPLACE_STRING_0,"String0ReplaceString0");
        _m.addEntry(STRING_0_REPLACE_STRING_1,"String0ReplaceString1");
        _m.addEntry(STRING_1_REPLACE_STRING_0,"String1ReplaceString0");
        _m.addEntry(STRING_1_REPLACE_STRING_1,"String1ReplaceString1");
        _m.addEntry(STRING_0_REPLACE_MULTIPLE_0,"String0ReplaceMultiple0");
        _m.addEntry(STRING_0_REGION_MATCHES_0,"String0RegionMatches0");
        _m.addEntry(STRING_0_REGION_MATCHES_1,"String0RegionMatches1");
        _m.addEntry(STRING_0_REGION_MATCHES_2,"String0RegionMatches2");
        _m.addEntry(STRING_0_REGION_MATCHES_3,"String0RegionMatches3");
        _m.addEntry(STRING_0_REGION_MATCHES_4,"String0RegionMatches4");
        _m.addEntry(STRING_0_VALUE_OF_METHOD_0,"String0ValueOfMethod0");
        _m.addEntry(STRING_1_VALUE_OF_METHOD_0,"String1ValueOfMethod0");
        _m.addEntry(STRING_2_VALUE_OF_METHOD_0,"String2ValueOfMethod0");
        _m.addEntry(STRING_3_VALUE_OF_METHOD_0,"String3ValueOfMethod0");
        _m.addEntry(STRING_4_VALUE_OF_METHOD_0,"String4ValueOfMethod0");
        _m.addEntry(STRING_5_VALUE_OF_METHOD_0,"String5ValueOfMethod0");
        _m.addEntry(STRING_6_VALUE_OF_METHOD_0,"String6ValueOfMethod0");
        _m.addEntry(STRING_7_VALUE_OF_METHOD_0,"String7ValueOfMethod0");
        _m.addEntry(STRING_8_VALUE_OF_METHOD_0,"String8ValueOfMethod0");
        _m.addEntry(STRING_9_VALUE_OF_METHOD_0,"String9ValueOfMethod0");
        _m.addEntry(STRING_9_VALUE_OF_METHOD_1,"String9ValueOfMethod1");
        _m.addEntry(STRING_9_VALUE_OF_METHOD_2,"String9ValueOfMethod2");
        _m.addEntry(STRING_0_STRING_0,"String0String0");
        _m.addEntry(STRING_1_STRING_0,"String1String0");
        _m.addEntry(STRING_1_STRING_1,"String1String1");
        _m.addEntry(STRING_1_STRING_2,"String1String2");
        _m.addEntry(STRING_2_STRING_0,"String2String0");
        _m.addEntry(STRING_3_STRING_0,"String3String0");
        _m.addEntry(STRING_3_STRING_1,"String3String1");
        _m.addEntry(STRING_3_STRING_2,"String3String2");
        _m.addEntry(STRING_4_STRING_0,"String4String0");
        _m.addEntry(STRING_BUILDER_0_APPEND_0,"StringBuilder0Append0");
        _m.addEntry(STRING_BUILDER_1_APPEND_0,"StringBuilder1Append0");
        _m.addEntry(STRING_BUILDER_2_APPEND_0,"StringBuilder2Append0");
        _m.addEntry(STRING_BUILDER_3_APPEND_0,"StringBuilder3Append0");
        _m.addEntry(STRING_BUILDER_4_APPEND_0,"StringBuilder4Append0");
        _m.addEntry(STRING_BUILDER_5_APPEND_0,"StringBuilder5Append0");
        _m.addEntry(STRING_BUILDER_6_APPEND_0,"StringBuilder6Append0");
        _m.addEntry(STRING_BUILDER_7_APPEND_0,"StringBuilder7Append0");
        _m.addEntry(STRING_BUILDER_8_APPEND_0,"StringBuilder8Append0");
        _m.addEntry(STRING_BUILDER_9_APPEND_0,"StringBuilder9Append0");
        _m.addEntry(STRING_BUILDER_9_APPEND_1,"StringBuilder9Append1");
        _m.addEntry(STRING_BUILDER_9_APPEND_2,"StringBuilder9Append2");
        _m.addEntry(STRING_BUILDER_10_APPEND_0,"StringBuilder10Append0");
        _m.addEntry(STRING_BUILDER_11_APPEND_0,"StringBuilder11Append0");
        _m.addEntry(STRING_BUILDER_11_APPEND_1,"StringBuilder11Append1");
        _m.addEntry(STRING_BUILDER_11_APPEND_2,"StringBuilder11Append2");
        _m.addEntry(STRING_BUILDER_12_APPEND_0,"StringBuilder12Append0");
        _m.addEntry(STRING_BUILDER_13_APPEND_0,"StringBuilder13Append0");
        _m.addEntry(STRING_BUILDER_13_APPEND_1,"StringBuilder13Append1");
        _m.addEntry(STRING_BUILDER_13_APPEND_2,"StringBuilder13Append2");
        _m.addEntry(STRING_BUILDER_0_DELETE_0,"StringBuilder0Delete0");
        _m.addEntry(STRING_BUILDER_0_DELETE_1,"StringBuilder0Delete1");
        _m.addEntry(STRING_BUILDER_0_DELETE_CHAR_AT_0,"StringBuilder0DeleteCharAt0");
        _m.addEntry(STRING_BUILDER_0_INSERT_0,"StringBuilder0Insert0");
        _m.addEntry(STRING_BUILDER_0_INSERT_1,"StringBuilder0Insert1");
        _m.addEntry(STRING_BUILDER_1_INSERT_0,"StringBuilder1Insert0");
        _m.addEntry(STRING_BUILDER_1_INSERT_1,"StringBuilder1Insert1");
        _m.addEntry(STRING_BUILDER_2_INSERT_0,"StringBuilder2Insert0");
        _m.addEntry(STRING_BUILDER_2_INSERT_1,"StringBuilder2Insert1");
        _m.addEntry(STRING_BUILDER_3_INSERT_0,"StringBuilder3Insert0");
        _m.addEntry(STRING_BUILDER_3_INSERT_1,"StringBuilder3Insert1");
        _m.addEntry(STRING_BUILDER_4_INSERT_0,"StringBuilder4Insert0");
        _m.addEntry(STRING_BUILDER_4_INSERT_1,"StringBuilder4Insert1");
        _m.addEntry(STRING_BUILDER_5_INSERT_0,"StringBuilder5Insert0");
        _m.addEntry(STRING_BUILDER_5_INSERT_1,"StringBuilder5Insert1");
        _m.addEntry(STRING_BUILDER_6_INSERT_0,"StringBuilder6Insert0");
        _m.addEntry(STRING_BUILDER_6_INSERT_1,"StringBuilder6Insert1");
        _m.addEntry(STRING_BUILDER_7_INSERT_0,"StringBuilder7Insert0");
        _m.addEntry(STRING_BUILDER_7_INSERT_1,"StringBuilder7Insert1");
        _m.addEntry(STRING_BUILDER_8_INSERT_0,"StringBuilder8Insert0");
        _m.addEntry(STRING_BUILDER_8_INSERT_1,"StringBuilder8Insert1");
        _m.addEntry(STRING_BUILDER_9_INSERT_0,"StringBuilder9Insert0");
        _m.addEntry(STRING_BUILDER_9_INSERT_1,"StringBuilder9Insert1");
        _m.addEntry(STRING_BUILDER_9_INSERT_2,"StringBuilder9Insert2");
        _m.addEntry(STRING_BUILDER_9_INSERT_3,"StringBuilder9Insert3");
        _m.addEntry(STRING_BUILDER_10_INSERT_0,"StringBuilder10Insert0");
        _m.addEntry(STRING_BUILDER_10_INSERT_1,"StringBuilder10Insert1");
        _m.addEntry(STRING_BUILDER_11_INSERT_0,"StringBuilder11Insert0");
        _m.addEntry(STRING_BUILDER_11_INSERT_1,"StringBuilder11Insert1");
        _m.addEntry(STRING_BUILDER_11_INSERT_2,"StringBuilder11Insert2");
        _m.addEntry(STRING_BUILDER_11_INSERT_3,"StringBuilder11Insert3");
        _m.addEntry(STRING_BUILDER_12_INSERT_0,"StringBuilder12Insert0");
        _m.addEntry(STRING_BUILDER_12_INSERT_1,"StringBuilder12Insert1");
        _m.addEntry(STRING_BUILDER_13_INSERT_0,"StringBuilder13Insert0");
        _m.addEntry(STRING_BUILDER_13_INSERT_1,"StringBuilder13Insert1");
        _m.addEntry(STRING_BUILDER_13_INSERT_2,"StringBuilder13Insert2");
        _m.addEntry(STRING_BUILDER_13_INSERT_3,"StringBuilder13Insert3");
        _m.addEntry(STRING_BUILDER_0_REPLACE_0,"StringBuilder0Replace0");
        _m.addEntry(STRING_BUILDER_0_REPLACE_1,"StringBuilder0Replace1");
        _m.addEntry(STRING_BUILDER_0_REPLACE_2,"StringBuilder0Replace2");
        _m.addEntry(STRING_BUILDER_0_SET_CHAR_AT_0,"StringBuilder0SetCharAt0");
        _m.addEntry(STRING_BUILDER_0_SET_CHAR_AT_1,"StringBuilder0SetCharAt1");
        _m.addEntry(STRING_BUILDER_0_SET_LENGTH_0,"StringBuilder0SetLength0");
        _m.addEntry(STRING_BUILDER_0_ENSURE_CAPACITY_0,"StringBuilder0EnsureCapacity0");
        _m.addEntry(STRING_BUILDER_0_SAME_0,"StringBuilder0Same0");
        _m.addEntry(STRING_BUILDER_0_SAME_1,"StringBuilder0Same1");
        _m.addEntry(STRING_BUILDER_0_STRING_BUILDER_0,"StringBuilder0StringBuilder0");
        _m.addEntry(STRING_BUILDER_1_STRING_BUILDER_0,"StringBuilder1StringBuilder0");
        _m.addEntry(STRING_BUILDER_2_STRING_BUILDER_0,"StringBuilder2StringBuilder0");
        _m.addEntry(REPLACEMENT_0_REPLACEMENT_0,"Replacement0Replacement0");
        _m.addEntry(REPLACEMENT_0_REPLACEMENT_1,"Replacement0Replacement1");
    }
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(StringMap<String> _mapping) {
        CustList<CustList<KeyValueMemberName>> map_ = new CustList<CustList<KeyValueMemberName>>();
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_SUB_SEQUENCE_0), getAliasCharSequence0SubSequence0()),new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_SUB_SEQUENCE_1), getAliasCharSequence0SubSequence1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_CHAR_AT_0), getAliasCharSequence0CharAt0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_SUBSTRING_0), getAliasCharSequence0Substring0()),new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_SUBSTRING_1), getAliasCharSequence0Substring1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_1_SUBSTRING_0), getAliasCharSequence1Substring0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_COMPARE_TO_0), getAliasCharSequence0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_CONTAINS_0), getAliasCharSequence0Contains0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_STARTS_WITH_0), getAliasCharSequence0StartsWith0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_1_STARTS_WITH_0), getAliasCharSequence1StartsWith0()),new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_1_STARTS_WITH_1), getAliasCharSequence1StartsWith1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_ENDS_WITH_0), getAliasCharSequence0EndsWith0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_INDEX_OF_0), getAliasCharSequence0IndexOf0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_1_INDEX_OF_0), getAliasCharSequence1IndexOf0()),new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_1_INDEX_OF_1), getAliasCharSequence1IndexOf1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_2_INDEX_OF_0), getAliasCharSequence2IndexOf0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_3_INDEX_OF_0), getAliasCharSequence3IndexOf0()),new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_3_INDEX_OF_1), getAliasCharSequence3IndexOf1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_LAST_INDEX_OF_0), getAliasCharSequence0LastIndexOf0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_1_LAST_INDEX_OF_0), getAliasCharSequence1LastIndexOf0()),new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_1_LAST_INDEX_OF_1), getAliasCharSequence1LastIndexOf1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_2_LAST_INDEX_OF_0), getAliasCharSequence2LastIndexOf0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_3_LAST_INDEX_OF_0), getAliasCharSequence3LastIndexOf0()),new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_3_LAST_INDEX_OF_1), getAliasCharSequence3LastIndexOf1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_FORMAT_0), getAliasCharSequence0Format0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_SPLIT_0), getAliasCharSequence0Split0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_1_SPLIT_0), getAliasCharSequence1Split0()),new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_1_SPLIT_1), getAliasCharSequence1Split1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_2_SPLIT_0), getAliasCharSequence2Split0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_3_SPLIT_0), getAliasCharSequence3Split0()),new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_3_SPLIT_1), getAliasCharSequence3Split1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_SPLIT_STRINGS_0), getAliasCharSequence0SplitStrings0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_1_SPLIT_STRINGS_0), getAliasCharSequence1SplitStrings0()),new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_1_SPLIT_STRINGS_1), getAliasCharSequence1SplitStrings1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_SPLIT_CHARS_0), getAliasCharSequence0SplitChars0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_REGION_MATCHES_0), getAliasCharSequence0RegionMatches0()),new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_REGION_MATCHES_1), getAliasCharSequence0RegionMatches1()),new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_REGION_MATCHES_2), getAliasCharSequence0RegionMatches2()),new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_REGION_MATCHES_3), getAliasCharSequence0RegionMatches3())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_EQUALS_0), getAliasCharSequence0Equals0()),new KeyValueMemberName(_mapping.getVal(CHAR_SEQUENCE_0_EQUALS_1), getAliasCharSequence0Equals1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_0_EQUALS_IGNORE_CASE_0), getAliasString0EqualsIgnoreCase0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_0_COMPARE_0), getAliasString0Compare0()),new KeyValueMemberName(_mapping.getVal(STRING_0_COMPARE_1), getAliasString0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_0_COMPARE_TO_IGNORE_CASE_0), getAliasString0CompareToIgnoreCase0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_0_REPLACE_STRING_0), getAliasString0ReplaceString0()),new KeyValueMemberName(_mapping.getVal(STRING_0_REPLACE_STRING_1), getAliasString0ReplaceString1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_1_REPLACE_STRING_0), getAliasString1ReplaceString0()),new KeyValueMemberName(_mapping.getVal(STRING_1_REPLACE_STRING_1), getAliasString1ReplaceString1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_0_REPLACE_MULTIPLE_0), getAliasString0ReplaceMultiple0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_0_REGION_MATCHES_0), getAliasString0RegionMatches0()),new KeyValueMemberName(_mapping.getVal(STRING_0_REGION_MATCHES_1), getAliasString0RegionMatches1()),new KeyValueMemberName(_mapping.getVal(STRING_0_REGION_MATCHES_2), getAliasString0RegionMatches2()),new KeyValueMemberName(_mapping.getVal(STRING_0_REGION_MATCHES_3), getAliasString0RegionMatches3()),new KeyValueMemberName(_mapping.getVal(STRING_0_REGION_MATCHES_4), getAliasString0RegionMatches4())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_0_VALUE_OF_METHOD_0), getAliasString0ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_1_VALUE_OF_METHOD_0), getAliasString1ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_2_VALUE_OF_METHOD_0), getAliasString2ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_3_VALUE_OF_METHOD_0), getAliasString3ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_4_VALUE_OF_METHOD_0), getAliasString4ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_5_VALUE_OF_METHOD_0), getAliasString5ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_6_VALUE_OF_METHOD_0), getAliasString6ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_7_VALUE_OF_METHOD_0), getAliasString7ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_8_VALUE_OF_METHOD_0), getAliasString8ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_9_VALUE_OF_METHOD_0), getAliasString9ValueOfMethod0()),new KeyValueMemberName(_mapping.getVal(STRING_9_VALUE_OF_METHOD_1), getAliasString9ValueOfMethod1()),new KeyValueMemberName(_mapping.getVal(STRING_9_VALUE_OF_METHOD_2), getAliasString9ValueOfMethod2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_0_STRING_0), getAliasString0String0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_1_STRING_0), getAliasString1String0()),new KeyValueMemberName(_mapping.getVal(STRING_1_STRING_1), getAliasString1String1()),new KeyValueMemberName(_mapping.getVal(STRING_1_STRING_2), getAliasString1String2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_2_STRING_0), getAliasString2String0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_3_STRING_0), getAliasString3String0()),new KeyValueMemberName(_mapping.getVal(STRING_3_STRING_1), getAliasString3String1()),new KeyValueMemberName(_mapping.getVal(STRING_3_STRING_2), getAliasString3String2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_4_STRING_0), getAliasString4String0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_0_APPEND_0), getAliasStringBuilder0Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_1_APPEND_0), getAliasStringBuilder1Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_2_APPEND_0), getAliasStringBuilder2Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_3_APPEND_0), getAliasStringBuilder3Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_4_APPEND_0), getAliasStringBuilder4Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_5_APPEND_0), getAliasStringBuilder5Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_6_APPEND_0), getAliasStringBuilder6Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_7_APPEND_0), getAliasStringBuilder7Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_8_APPEND_0), getAliasStringBuilder8Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_9_APPEND_0), getAliasStringBuilder9Append0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_9_APPEND_1), getAliasStringBuilder9Append1()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_9_APPEND_2), getAliasStringBuilder9Append2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_10_APPEND_0), getAliasStringBuilder10Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_11_APPEND_0), getAliasStringBuilder11Append0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_11_APPEND_1), getAliasStringBuilder11Append1()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_11_APPEND_2), getAliasStringBuilder11Append2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_12_APPEND_0), getAliasStringBuilder12Append0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_13_APPEND_0), getAliasStringBuilder13Append0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_13_APPEND_1), getAliasStringBuilder13Append1()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_13_APPEND_2), getAliasStringBuilder13Append2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_0_DELETE_0), getAliasStringBuilder0Delete0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_0_DELETE_1), getAliasStringBuilder0Delete1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_0_DELETE_CHAR_AT_0), getAliasStringBuilder0DeleteCharAt0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_0_INSERT_0), getAliasStringBuilder0Insert0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_0_INSERT_1), getAliasStringBuilder0Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_1_INSERT_0), getAliasStringBuilder1Insert0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_1_INSERT_1), getAliasStringBuilder1Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_2_INSERT_0), getAliasStringBuilder2Insert0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_2_INSERT_1), getAliasStringBuilder2Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_3_INSERT_0), getAliasStringBuilder3Insert0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_3_INSERT_1), getAliasStringBuilder3Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_4_INSERT_0), getAliasStringBuilder4Insert0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_4_INSERT_1), getAliasStringBuilder4Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_5_INSERT_0), getAliasStringBuilder5Insert0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_5_INSERT_1), getAliasStringBuilder5Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_6_INSERT_0), getAliasStringBuilder6Insert0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_6_INSERT_1), getAliasStringBuilder6Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_7_INSERT_0), getAliasStringBuilder7Insert0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_7_INSERT_1), getAliasStringBuilder7Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_8_INSERT_0), getAliasStringBuilder8Insert0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_8_INSERT_1), getAliasStringBuilder8Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_9_INSERT_0), getAliasStringBuilder9Insert0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_9_INSERT_1), getAliasStringBuilder9Insert1()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_9_INSERT_2), getAliasStringBuilder9Insert2()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_9_INSERT_3), getAliasStringBuilder9Insert3())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_10_INSERT_0), getAliasStringBuilder10Insert0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_10_INSERT_1), getAliasStringBuilder10Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_11_INSERT_0), getAliasStringBuilder11Insert0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_11_INSERT_1), getAliasStringBuilder11Insert1()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_11_INSERT_2), getAliasStringBuilder11Insert2()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_11_INSERT_3), getAliasStringBuilder11Insert3())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_12_INSERT_0), getAliasStringBuilder12Insert0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_12_INSERT_1), getAliasStringBuilder12Insert1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_13_INSERT_0), getAliasStringBuilder13Insert0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_13_INSERT_1), getAliasStringBuilder13Insert1()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_13_INSERT_2), getAliasStringBuilder13Insert2()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_13_INSERT_3), getAliasStringBuilder13Insert3())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_0_REPLACE_0), getAliasStringBuilder0Replace0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_0_REPLACE_1), getAliasStringBuilder0Replace1()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_0_REPLACE_2), getAliasStringBuilder0Replace2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_0_SET_CHAR_AT_0), getAliasStringBuilder0SetCharAt0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_0_SET_CHAR_AT_1), getAliasStringBuilder0SetCharAt1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_0_SET_LENGTH_0), getAliasStringBuilder0SetLength0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_0_ENSURE_CAPACITY_0), getAliasStringBuilder0EnsureCapacity0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_0_SAME_0), getAliasStringBuilder0Same0()),new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_0_SAME_1), getAliasStringBuilder0Same1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_0_STRING_BUILDER_0), getAliasStringBuilder0StringBuilder0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_1_STRING_BUILDER_0), getAliasStringBuilder1StringBuilder0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(STRING_BUILDER_2_STRING_BUILDER_0), getAliasStringBuilder2StringBuilder0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(REPLACEMENT_0_REPLACEMENT_0), getAliasReplacement0Replacement0()),new KeyValueMemberName(_mapping.getVal(REPLACEMENT_0_REPLACEMENT_1), getAliasReplacement0Replacement1())));
        return map_;
    }
    public String getAliasCharSequence0SubSequence0() {
        return aliasCharSequence0SubSequence0;
    }

    public void setAliasCharSequence0SubSequence0(String _v) {
        this.aliasCharSequence0SubSequence0 =_v;
    }

    public String getAliasCharSequence0SubSequence1() {
        return aliasCharSequence0SubSequence1;
    }

    public void setAliasCharSequence0SubSequence1(String _v) {
        this.aliasCharSequence0SubSequence1 =_v;
    }

    public String getAliasCharSequence0CharAt0() {
        return aliasCharSequence0CharAt0;
    }

    public void setAliasCharSequence0CharAt0(String _v) {
        this.aliasCharSequence0CharAt0 =_v;
    }

    public String getAliasCharSequence0Substring0() {
        return aliasCharSequence0Substring0;
    }

    public void setAliasCharSequence0Substring0(String _v) {
        this.aliasCharSequence0Substring0 =_v;
    }

    public String getAliasCharSequence0Substring1() {
        return aliasCharSequence0Substring1;
    }

    public void setAliasCharSequence0Substring1(String _v) {
        this.aliasCharSequence0Substring1 =_v;
    }

    public String getAliasCharSequence1Substring0() {
        return aliasCharSequence1Substring0;
    }

    public void setAliasCharSequence1Substring0(String _v) {
        this.aliasCharSequence1Substring0 =_v;
    }

    public String getAliasCharSequence0CompareTo0() {
        return aliasCharSequence0CompareTo0;
    }

    public void setAliasCharSequence0CompareTo0(String _v) {
        this.aliasCharSequence0CompareTo0 =_v;
    }

    public String getAliasCharSequence0Contains0() {
        return aliasCharSequence0Contains0;
    }

    public void setAliasCharSequence0Contains0(String _v) {
        this.aliasCharSequence0Contains0 =_v;
    }

    public String getAliasCharSequence0StartsWith0() {
        return aliasCharSequence0StartsWith0;
    }

    public void setAliasCharSequence0StartsWith0(String _v) {
        this.aliasCharSequence0StartsWith0 =_v;
    }

    public String getAliasCharSequence1StartsWith0() {
        return aliasCharSequence1StartsWith0;
    }

    public void setAliasCharSequence1StartsWith0(String _v) {
        this.aliasCharSequence1StartsWith0 =_v;
    }

    public String getAliasCharSequence1StartsWith1() {
        return aliasCharSequence1StartsWith1;
    }

    public void setAliasCharSequence1StartsWith1(String _v) {
        this.aliasCharSequence1StartsWith1 =_v;
    }

    public String getAliasCharSequence0EndsWith0() {
        return aliasCharSequence0EndsWith0;
    }

    public void setAliasCharSequence0EndsWith0(String _v) {
        this.aliasCharSequence0EndsWith0 =_v;
    }

    public String getAliasCharSequence0IndexOf0() {
        return aliasCharSequence0IndexOf0;
    }

    public void setAliasCharSequence0IndexOf0(String _v) {
        this.aliasCharSequence0IndexOf0 =_v;
    }

    public String getAliasCharSequence1IndexOf0() {
        return aliasCharSequence1IndexOf0;
    }

    public void setAliasCharSequence1IndexOf0(String _v) {
        this.aliasCharSequence1IndexOf0 =_v;
    }

    public String getAliasCharSequence1IndexOf1() {
        return aliasCharSequence1IndexOf1;
    }

    public void setAliasCharSequence1IndexOf1(String _v) {
        this.aliasCharSequence1IndexOf1 =_v;
    }

    public String getAliasCharSequence2IndexOf0() {
        return aliasCharSequence2IndexOf0;
    }

    public void setAliasCharSequence2IndexOf0(String _v) {
        this.aliasCharSequence2IndexOf0 =_v;
    }

    public String getAliasCharSequence3IndexOf0() {
        return aliasCharSequence3IndexOf0;
    }

    public void setAliasCharSequence3IndexOf0(String _v) {
        this.aliasCharSequence3IndexOf0 =_v;
    }

    public String getAliasCharSequence3IndexOf1() {
        return aliasCharSequence3IndexOf1;
    }

    public void setAliasCharSequence3IndexOf1(String _v) {
        this.aliasCharSequence3IndexOf1 =_v;
    }

    public String getAliasCharSequence0LastIndexOf0() {
        return aliasCharSequence0LastIndexOf0;
    }

    public void setAliasCharSequence0LastIndexOf0(String _v) {
        this.aliasCharSequence0LastIndexOf0 =_v;
    }

    public String getAliasCharSequence1LastIndexOf0() {
        return aliasCharSequence1LastIndexOf0;
    }

    public void setAliasCharSequence1LastIndexOf0(String _v) {
        this.aliasCharSequence1LastIndexOf0 =_v;
    }

    public String getAliasCharSequence1LastIndexOf1() {
        return aliasCharSequence1LastIndexOf1;
    }

    public void setAliasCharSequence1LastIndexOf1(String _v) {
        this.aliasCharSequence1LastIndexOf1 =_v;
    }

    public String getAliasCharSequence2LastIndexOf0() {
        return aliasCharSequence2LastIndexOf0;
    }

    public void setAliasCharSequence2LastIndexOf0(String _v) {
        this.aliasCharSequence2LastIndexOf0 =_v;
    }

    public String getAliasCharSequence3LastIndexOf0() {
        return aliasCharSequence3LastIndexOf0;
    }

    public void setAliasCharSequence3LastIndexOf0(String _v) {
        this.aliasCharSequence3LastIndexOf0 =_v;
    }

    public String getAliasCharSequence3LastIndexOf1() {
        return aliasCharSequence3LastIndexOf1;
    }

    public void setAliasCharSequence3LastIndexOf1(String _v) {
        this.aliasCharSequence3LastIndexOf1 =_v;
    }

    public String getAliasCharSequence0Format0() {
        return aliasCharSequence0Format0;
    }

    public void setAliasCharSequence0Format0(String _v) {
        this.aliasCharSequence0Format0 =_v;
    }

    public String getAliasCharSequence0Split0() {
        return aliasCharSequence0Split0;
    }

    public void setAliasCharSequence0Split0(String _v) {
        this.aliasCharSequence0Split0 =_v;
    }

    public String getAliasCharSequence1Split0() {
        return aliasCharSequence1Split0;
    }

    public void setAliasCharSequence1Split0(String _v) {
        this.aliasCharSequence1Split0 =_v;
    }

    public String getAliasCharSequence1Split1() {
        return aliasCharSequence1Split1;
    }

    public void setAliasCharSequence1Split1(String _v) {
        this.aliasCharSequence1Split1 =_v;
    }

    public String getAliasCharSequence2Split0() {
        return aliasCharSequence2Split0;
    }

    public void setAliasCharSequence2Split0(String _v) {
        this.aliasCharSequence2Split0 =_v;
    }

    public String getAliasCharSequence3Split0() {
        return aliasCharSequence3Split0;
    }

    public void setAliasCharSequence3Split0(String _v) {
        this.aliasCharSequence3Split0 =_v;
    }

    public String getAliasCharSequence3Split1() {
        return aliasCharSequence3Split1;
    }

    public void setAliasCharSequence3Split1(String _v) {
        this.aliasCharSequence3Split1 =_v;
    }

    public String getAliasCharSequence0SplitStrings0() {
        return aliasCharSequence0SplitStrings0;
    }

    public void setAliasCharSequence0SplitStrings0(String _v) {
        this.aliasCharSequence0SplitStrings0 =_v;
    }

    public String getAliasCharSequence1SplitStrings0() {
        return aliasCharSequence1SplitStrings0;
    }

    public void setAliasCharSequence1SplitStrings0(String _v) {
        this.aliasCharSequence1SplitStrings0 =_v;
    }

    public String getAliasCharSequence1SplitStrings1() {
        return aliasCharSequence1SplitStrings1;
    }

    public void setAliasCharSequence1SplitStrings1(String _v) {
        this.aliasCharSequence1SplitStrings1 =_v;
    }

    public String getAliasCharSequence0SplitChars0() {
        return aliasCharSequence0SplitChars0;
    }

    public void setAliasCharSequence0SplitChars0(String _v) {
        this.aliasCharSequence0SplitChars0 =_v;
    }

    public String getAliasCharSequence0RegionMatches0() {
        return aliasCharSequence0RegionMatches0;
    }

    public void setAliasCharSequence0RegionMatches0(String _v) {
        this.aliasCharSequence0RegionMatches0 =_v;
    }

    public String getAliasCharSequence0RegionMatches1() {
        return aliasCharSequence0RegionMatches1;
    }

    public void setAliasCharSequence0RegionMatches1(String _v) {
        this.aliasCharSequence0RegionMatches1 =_v;
    }

    public String getAliasCharSequence0RegionMatches2() {
        return aliasCharSequence0RegionMatches2;
    }

    public void setAliasCharSequence0RegionMatches2(String _v) {
        this.aliasCharSequence0RegionMatches2 =_v;
    }

    public String getAliasCharSequence0RegionMatches3() {
        return aliasCharSequence0RegionMatches3;
    }

    public void setAliasCharSequence0RegionMatches3(String _v) {
        this.aliasCharSequence0RegionMatches3 =_v;
    }

    public String getAliasCharSequence0Equals0() {
        return aliasCharSequence0Equals0;
    }

    public void setAliasCharSequence0Equals0(String _v) {
        this.aliasCharSequence0Equals0 =_v;
    }

    public String getAliasCharSequence0Equals1() {
        return aliasCharSequence0Equals1;
    }

    public void setAliasCharSequence0Equals1(String _v) {
        this.aliasCharSequence0Equals1 =_v;
    }

    public String getAliasString0EqualsIgnoreCase0() {
        return aliasString0EqualsIgnoreCase0;
    }

    public void setAliasString0EqualsIgnoreCase0(String _v) {
        this.aliasString0EqualsIgnoreCase0 =_v;
    }

    public String getAliasString0Compare0() {
        return aliasString0Compare0;
    }

    public void setAliasString0Compare0(String _v) {
        this.aliasString0Compare0 =_v;
    }

    public String getAliasString0Compare1() {
        return aliasString0Compare1;
    }

    public void setAliasString0Compare1(String _v) {
        this.aliasString0Compare1 =_v;
    }

    public String getAliasString0CompareToIgnoreCase0() {
        return aliasString0CompareToIgnoreCase0;
    }

    public void setAliasString0CompareToIgnoreCase0(String _v) {
        this.aliasString0CompareToIgnoreCase0 =_v;
    }

    public String getAliasString0ReplaceString0() {
        return aliasString0ReplaceString0;
    }

    public void setAliasString0ReplaceString0(String _v) {
        this.aliasString0ReplaceString0 =_v;
    }

    public String getAliasString0ReplaceString1() {
        return aliasString0ReplaceString1;
    }

    public void setAliasString0ReplaceString1(String _v) {
        this.aliasString0ReplaceString1 =_v;
    }

    public String getAliasString1ReplaceString0() {
        return aliasString1ReplaceString0;
    }

    public void setAliasString1ReplaceString0(String _v) {
        this.aliasString1ReplaceString0 =_v;
    }

    public String getAliasString1ReplaceString1() {
        return aliasString1ReplaceString1;
    }

    public void setAliasString1ReplaceString1(String _v) {
        this.aliasString1ReplaceString1 =_v;
    }

    public String getAliasString0ReplaceMultiple0() {
        return aliasString0ReplaceMultiple0;
    }

    public void setAliasString0ReplaceMultiple0(String _v) {
        this.aliasString0ReplaceMultiple0 =_v;
    }

    public String getAliasString0RegionMatches0() {
        return aliasString0RegionMatches0;
    }

    public void setAliasString0RegionMatches0(String _v) {
        this.aliasString0RegionMatches0 =_v;
    }

    public String getAliasString0RegionMatches1() {
        return aliasString0RegionMatches1;
    }

    public void setAliasString0RegionMatches1(String _v) {
        this.aliasString0RegionMatches1 =_v;
    }

    public String getAliasString0RegionMatches2() {
        return aliasString0RegionMatches2;
    }

    public void setAliasString0RegionMatches2(String _v) {
        this.aliasString0RegionMatches2 =_v;
    }

    public String getAliasString0RegionMatches3() {
        return aliasString0RegionMatches3;
    }

    public void setAliasString0RegionMatches3(String _v) {
        this.aliasString0RegionMatches3 =_v;
    }

    public String getAliasString0RegionMatches4() {
        return aliasString0RegionMatches4;
    }

    public void setAliasString0RegionMatches4(String _v) {
        this.aliasString0RegionMatches4 =_v;
    }

    public String getAliasString0ValueOfMethod0() {
        return aliasString0ValueOfMethod0;
    }

    public void setAliasString0ValueOfMethod0(String _v) {
        this.aliasString0ValueOfMethod0 =_v;
    }

    public String getAliasString1ValueOfMethod0() {
        return aliasString1ValueOfMethod0;
    }

    public void setAliasString1ValueOfMethod0(String _v) {
        this.aliasString1ValueOfMethod0 =_v;
    }

    public String getAliasString2ValueOfMethod0() {
        return aliasString2ValueOfMethod0;
    }

    public void setAliasString2ValueOfMethod0(String _v) {
        this.aliasString2ValueOfMethod0 =_v;
    }

    public String getAliasString3ValueOfMethod0() {
        return aliasString3ValueOfMethod0;
    }

    public void setAliasString3ValueOfMethod0(String _v) {
        this.aliasString3ValueOfMethod0 =_v;
    }

    public String getAliasString4ValueOfMethod0() {
        return aliasString4ValueOfMethod0;
    }

    public void setAliasString4ValueOfMethod0(String _v) {
        this.aliasString4ValueOfMethod0 =_v;
    }

    public String getAliasString5ValueOfMethod0() {
        return aliasString5ValueOfMethod0;
    }

    public void setAliasString5ValueOfMethod0(String _v) {
        this.aliasString5ValueOfMethod0 =_v;
    }

    public String getAliasString6ValueOfMethod0() {
        return aliasString6ValueOfMethod0;
    }

    public void setAliasString6ValueOfMethod0(String _v) {
        this.aliasString6ValueOfMethod0 =_v;
    }

    public String getAliasString7ValueOfMethod0() {
        return aliasString7ValueOfMethod0;
    }

    public void setAliasString7ValueOfMethod0(String _v) {
        this.aliasString7ValueOfMethod0 =_v;
    }

    public String getAliasString8ValueOfMethod0() {
        return aliasString8ValueOfMethod0;
    }

    public void setAliasString8ValueOfMethod0(String _v) {
        this.aliasString8ValueOfMethod0 =_v;
    }

    public String getAliasString9ValueOfMethod0() {
        return aliasString9ValueOfMethod0;
    }

    public void setAliasString9ValueOfMethod0(String _v) {
        this.aliasString9ValueOfMethod0 =_v;
    }

    public String getAliasString9ValueOfMethod1() {
        return aliasString9ValueOfMethod1;
    }

    public void setAliasString9ValueOfMethod1(String _v) {
        this.aliasString9ValueOfMethod1 =_v;
    }

    public String getAliasString9ValueOfMethod2() {
        return aliasString9ValueOfMethod2;
    }

    public void setAliasString9ValueOfMethod2(String _v) {
        this.aliasString9ValueOfMethod2 =_v;
    }

    public String getAliasString0String0() {
        return aliasString0String0;
    }

    public void setAliasString0String0(String _v) {
        this.aliasString0String0 =_v;
    }

    public String getAliasString1String0() {
        return aliasString1String0;
    }

    public void setAliasString1String0(String _v) {
        this.aliasString1String0 =_v;
    }

    public String getAliasString1String1() {
        return aliasString1String1;
    }

    public void setAliasString1String1(String _v) {
        this.aliasString1String1 =_v;
    }

    public String getAliasString1String2() {
        return aliasString1String2;
    }

    public void setAliasString1String2(String _v) {
        this.aliasString1String2 =_v;
    }

    public String getAliasString2String0() {
        return aliasString2String0;
    }

    public void setAliasString2String0(String _v) {
        this.aliasString2String0 =_v;
    }

    public String getAliasString3String0() {
        return aliasString3String0;
    }

    public void setAliasString3String0(String _v) {
        this.aliasString3String0 =_v;
    }

    public String getAliasString3String1() {
        return aliasString3String1;
    }

    public void setAliasString3String1(String _v) {
        this.aliasString3String1 =_v;
    }

    public String getAliasString3String2() {
        return aliasString3String2;
    }

    public void setAliasString3String2(String _v) {
        this.aliasString3String2 =_v;
    }

    public String getAliasString4String0() {
        return aliasString4String0;
    }

    public void setAliasString4String0(String _v) {
        this.aliasString4String0 =_v;
    }

    public String getAliasStringBuilder0Append0() {
        return aliasStringBuilder0Append0;
    }

    public void setAliasStringBuilder0Append0(String _v) {
        this.aliasStringBuilder0Append0 =_v;
    }

    public String getAliasStringBuilder1Append0() {
        return aliasStringBuilder1Append0;
    }

    public void setAliasStringBuilder1Append0(String _v) {
        this.aliasStringBuilder1Append0 =_v;
    }

    public String getAliasStringBuilder2Append0() {
        return aliasStringBuilder2Append0;
    }

    public void setAliasStringBuilder2Append0(String _v) {
        this.aliasStringBuilder2Append0 =_v;
    }

    public String getAliasStringBuilder3Append0() {
        return aliasStringBuilder3Append0;
    }

    public void setAliasStringBuilder3Append0(String _v) {
        this.aliasStringBuilder3Append0 =_v;
    }

    public String getAliasStringBuilder4Append0() {
        return aliasStringBuilder4Append0;
    }

    public void setAliasStringBuilder4Append0(String _v) {
        this.aliasStringBuilder4Append0 =_v;
    }

    public String getAliasStringBuilder5Append0() {
        return aliasStringBuilder5Append0;
    }

    public void setAliasStringBuilder5Append0(String _v) {
        this.aliasStringBuilder5Append0 =_v;
    }

    public String getAliasStringBuilder6Append0() {
        return aliasStringBuilder6Append0;
    }

    public void setAliasStringBuilder6Append0(String _v) {
        this.aliasStringBuilder6Append0 =_v;
    }

    public String getAliasStringBuilder7Append0() {
        return aliasStringBuilder7Append0;
    }

    public void setAliasStringBuilder7Append0(String _v) {
        this.aliasStringBuilder7Append0 =_v;
    }

    public String getAliasStringBuilder8Append0() {
        return aliasStringBuilder8Append0;
    }

    public void setAliasStringBuilder8Append0(String _v) {
        this.aliasStringBuilder8Append0 =_v;
    }

    public String getAliasStringBuilder9Append0() {
        return aliasStringBuilder9Append0;
    }

    public void setAliasStringBuilder9Append0(String _v) {
        this.aliasStringBuilder9Append0 =_v;
    }

    public String getAliasStringBuilder9Append1() {
        return aliasStringBuilder9Append1;
    }

    public void setAliasStringBuilder9Append1(String _v) {
        this.aliasStringBuilder9Append1 =_v;
    }

    public String getAliasStringBuilder9Append2() {
        return aliasStringBuilder9Append2;
    }

    public void setAliasStringBuilder9Append2(String _v) {
        this.aliasStringBuilder9Append2 =_v;
    }

    public String getAliasStringBuilder10Append0() {
        return aliasStringBuilder10Append0;
    }

    public void setAliasStringBuilder10Append0(String _v) {
        this.aliasStringBuilder10Append0 =_v;
    }

    public String getAliasStringBuilder11Append0() {
        return aliasStringBuilder11Append0;
    }

    public void setAliasStringBuilder11Append0(String _v) {
        this.aliasStringBuilder11Append0 =_v;
    }

    public String getAliasStringBuilder11Append1() {
        return aliasStringBuilder11Append1;
    }

    public void setAliasStringBuilder11Append1(String _v) {
        this.aliasStringBuilder11Append1 =_v;
    }

    public String getAliasStringBuilder11Append2() {
        return aliasStringBuilder11Append2;
    }

    public void setAliasStringBuilder11Append2(String _v) {
        this.aliasStringBuilder11Append2 =_v;
    }

    public String getAliasStringBuilder12Append0() {
        return aliasStringBuilder12Append0;
    }

    public void setAliasStringBuilder12Append0(String _v) {
        this.aliasStringBuilder12Append0 =_v;
    }

    public String getAliasStringBuilder13Append0() {
        return aliasStringBuilder13Append0;
    }

    public void setAliasStringBuilder13Append0(String _v) {
        this.aliasStringBuilder13Append0 =_v;
    }

    public String getAliasStringBuilder13Append1() {
        return aliasStringBuilder13Append1;
    }

    public void setAliasStringBuilder13Append1(String _v) {
        this.aliasStringBuilder13Append1 =_v;
    }

    public String getAliasStringBuilder13Append2() {
        return aliasStringBuilder13Append2;
    }

    public void setAliasStringBuilder13Append2(String _v) {
        this.aliasStringBuilder13Append2 =_v;
    }

    public String getAliasStringBuilder0Delete0() {
        return aliasStringBuilder0Delete0;
    }

    public void setAliasStringBuilder0Delete0(String _v) {
        this.aliasStringBuilder0Delete0 =_v;
    }

    public String getAliasStringBuilder0Delete1() {
        return aliasStringBuilder0Delete1;
    }

    public void setAliasStringBuilder0Delete1(String _v) {
        this.aliasStringBuilder0Delete1 =_v;
    }

    public String getAliasStringBuilder0DeleteCharAt0() {
        return aliasStringBuilder0DeleteCharAt0;
    }

    public void setAliasStringBuilder0DeleteCharAt0(String _v) {
        this.aliasStringBuilder0DeleteCharAt0 =_v;
    }

    public String getAliasStringBuilder0Insert0() {
        return aliasStringBuilder0Insert0;
    }

    public void setAliasStringBuilder0Insert0(String _v) {
        this.aliasStringBuilder0Insert0 =_v;
    }

    public String getAliasStringBuilder0Insert1() {
        return aliasStringBuilder0Insert1;
    }

    public void setAliasStringBuilder0Insert1(String _v) {
        this.aliasStringBuilder0Insert1 =_v;
    }

    public String getAliasStringBuilder1Insert0() {
        return aliasStringBuilder1Insert0;
    }

    public void setAliasStringBuilder1Insert0(String _v) {
        this.aliasStringBuilder1Insert0 =_v;
    }

    public String getAliasStringBuilder1Insert1() {
        return aliasStringBuilder1Insert1;
    }

    public void setAliasStringBuilder1Insert1(String _v) {
        this.aliasStringBuilder1Insert1 =_v;
    }

    public String getAliasStringBuilder2Insert0() {
        return aliasStringBuilder2Insert0;
    }

    public void setAliasStringBuilder2Insert0(String _v) {
        this.aliasStringBuilder2Insert0 =_v;
    }

    public String getAliasStringBuilder2Insert1() {
        return aliasStringBuilder2Insert1;
    }

    public void setAliasStringBuilder2Insert1(String _v) {
        this.aliasStringBuilder2Insert1 =_v;
    }

    public String getAliasStringBuilder3Insert0() {
        return aliasStringBuilder3Insert0;
    }

    public void setAliasStringBuilder3Insert0(String _v) {
        this.aliasStringBuilder3Insert0 =_v;
    }

    public String getAliasStringBuilder3Insert1() {
        return aliasStringBuilder3Insert1;
    }

    public void setAliasStringBuilder3Insert1(String _v) {
        this.aliasStringBuilder3Insert1 =_v;
    }

    public String getAliasStringBuilder4Insert0() {
        return aliasStringBuilder4Insert0;
    }

    public void setAliasStringBuilder4Insert0(String _v) {
        this.aliasStringBuilder4Insert0 =_v;
    }

    public String getAliasStringBuilder4Insert1() {
        return aliasStringBuilder4Insert1;
    }

    public void setAliasStringBuilder4Insert1(String _v) {
        this.aliasStringBuilder4Insert1 =_v;
    }

    public String getAliasStringBuilder5Insert0() {
        return aliasStringBuilder5Insert0;
    }

    public void setAliasStringBuilder5Insert0(String _v) {
        this.aliasStringBuilder5Insert0 =_v;
    }

    public String getAliasStringBuilder5Insert1() {
        return aliasStringBuilder5Insert1;
    }

    public void setAliasStringBuilder5Insert1(String _v) {
        this.aliasStringBuilder5Insert1 =_v;
    }

    public String getAliasStringBuilder6Insert0() {
        return aliasStringBuilder6Insert0;
    }

    public void setAliasStringBuilder6Insert0(String _v) {
        this.aliasStringBuilder6Insert0 =_v;
    }

    public String getAliasStringBuilder6Insert1() {
        return aliasStringBuilder6Insert1;
    }

    public void setAliasStringBuilder6Insert1(String _v) {
        this.aliasStringBuilder6Insert1 =_v;
    }

    public String getAliasStringBuilder7Insert0() {
        return aliasStringBuilder7Insert0;
    }

    public void setAliasStringBuilder7Insert0(String _v) {
        this.aliasStringBuilder7Insert0 =_v;
    }

    public String getAliasStringBuilder7Insert1() {
        return aliasStringBuilder7Insert1;
    }

    public void setAliasStringBuilder7Insert1(String _v) {
        this.aliasStringBuilder7Insert1 =_v;
    }

    public String getAliasStringBuilder8Insert0() {
        return aliasStringBuilder8Insert0;
    }

    public void setAliasStringBuilder8Insert0(String _v) {
        this.aliasStringBuilder8Insert0 =_v;
    }

    public String getAliasStringBuilder8Insert1() {
        return aliasStringBuilder8Insert1;
    }

    public void setAliasStringBuilder8Insert1(String _v) {
        this.aliasStringBuilder8Insert1 =_v;
    }

    public String getAliasStringBuilder9Insert0() {
        return aliasStringBuilder9Insert0;
    }

    public void setAliasStringBuilder9Insert0(String _v) {
        this.aliasStringBuilder9Insert0 =_v;
    }

    public String getAliasStringBuilder9Insert1() {
        return aliasStringBuilder9Insert1;
    }

    public void setAliasStringBuilder9Insert1(String _v) {
        this.aliasStringBuilder9Insert1 =_v;
    }

    public String getAliasStringBuilder9Insert2() {
        return aliasStringBuilder9Insert2;
    }

    public void setAliasStringBuilder9Insert2(String _v) {
        this.aliasStringBuilder9Insert2 =_v;
    }

    public String getAliasStringBuilder9Insert3() {
        return aliasStringBuilder9Insert3;
    }

    public void setAliasStringBuilder9Insert3(String _v) {
        this.aliasStringBuilder9Insert3 =_v;
    }

    public String getAliasStringBuilder10Insert0() {
        return aliasStringBuilder10Insert0;
    }

    public void setAliasStringBuilder10Insert0(String _v) {
        this.aliasStringBuilder10Insert0 =_v;
    }

    public String getAliasStringBuilder10Insert1() {
        return aliasStringBuilder10Insert1;
    }

    public void setAliasStringBuilder10Insert1(String _v) {
        this.aliasStringBuilder10Insert1 =_v;
    }

    public String getAliasStringBuilder11Insert0() {
        return aliasStringBuilder11Insert0;
    }

    public void setAliasStringBuilder11Insert0(String _v) {
        this.aliasStringBuilder11Insert0 =_v;
    }

    public String getAliasStringBuilder11Insert1() {
        return aliasStringBuilder11Insert1;
    }

    public void setAliasStringBuilder11Insert1(String _v) {
        this.aliasStringBuilder11Insert1 =_v;
    }

    public String getAliasStringBuilder11Insert2() {
        return aliasStringBuilder11Insert2;
    }

    public void setAliasStringBuilder11Insert2(String _v) {
        this.aliasStringBuilder11Insert2 =_v;
    }

    public String getAliasStringBuilder11Insert3() {
        return aliasStringBuilder11Insert3;
    }

    public void setAliasStringBuilder11Insert3(String _v) {
        this.aliasStringBuilder11Insert3 =_v;
    }

    public String getAliasStringBuilder12Insert0() {
        return aliasStringBuilder12Insert0;
    }

    public void setAliasStringBuilder12Insert0(String _v) {
        this.aliasStringBuilder12Insert0 =_v;
    }

    public String getAliasStringBuilder12Insert1() {
        return aliasStringBuilder12Insert1;
    }

    public void setAliasStringBuilder12Insert1(String _v) {
        this.aliasStringBuilder12Insert1 =_v;
    }

    public String getAliasStringBuilder13Insert0() {
        return aliasStringBuilder13Insert0;
    }

    public void setAliasStringBuilder13Insert0(String _v) {
        this.aliasStringBuilder13Insert0 =_v;
    }

    public String getAliasStringBuilder13Insert1() {
        return aliasStringBuilder13Insert1;
    }

    public void setAliasStringBuilder13Insert1(String _v) {
        this.aliasStringBuilder13Insert1 =_v;
    }

    public String getAliasStringBuilder13Insert2() {
        return aliasStringBuilder13Insert2;
    }

    public void setAliasStringBuilder13Insert2(String _v) {
        this.aliasStringBuilder13Insert2 =_v;
    }

    public String getAliasStringBuilder13Insert3() {
        return aliasStringBuilder13Insert3;
    }

    public void setAliasStringBuilder13Insert3(String _v) {
        this.aliasStringBuilder13Insert3 =_v;
    }

    public String getAliasStringBuilder0Replace0() {
        return aliasStringBuilder0Replace0;
    }

    public void setAliasStringBuilder0Replace0(String _v) {
        this.aliasStringBuilder0Replace0 =_v;
    }

    public String getAliasStringBuilder0Replace1() {
        return aliasStringBuilder0Replace1;
    }

    public void setAliasStringBuilder0Replace1(String _v) {
        this.aliasStringBuilder0Replace1 =_v;
    }

    public String getAliasStringBuilder0Replace2() {
        return aliasStringBuilder0Replace2;
    }

    public void setAliasStringBuilder0Replace2(String _v) {
        this.aliasStringBuilder0Replace2 =_v;
    }

    public String getAliasStringBuilder0SetCharAt0() {
        return aliasStringBuilder0SetCharAt0;
    }

    public void setAliasStringBuilder0SetCharAt0(String _v) {
        this.aliasStringBuilder0SetCharAt0 =_v;
    }

    public String getAliasStringBuilder0SetCharAt1() {
        return aliasStringBuilder0SetCharAt1;
    }

    public void setAliasStringBuilder0SetCharAt1(String _v) {
        this.aliasStringBuilder0SetCharAt1 =_v;
    }

    public String getAliasStringBuilder0SetLength0() {
        return aliasStringBuilder0SetLength0;
    }

    public void setAliasStringBuilder0SetLength0(String _v) {
        this.aliasStringBuilder0SetLength0 =_v;
    }

    public String getAliasStringBuilder0EnsureCapacity0() {
        return aliasStringBuilder0EnsureCapacity0;
    }

    public void setAliasStringBuilder0EnsureCapacity0(String _v) {
        this.aliasStringBuilder0EnsureCapacity0 =_v;
    }

    public String getAliasStringBuilder0Same0() {
        return aliasStringBuilder0Same0;
    }

    public void setAliasStringBuilder0Same0(String _v) {
        this.aliasStringBuilder0Same0 =_v;
    }

    public String getAliasStringBuilder0Same1() {
        return aliasStringBuilder0Same1;
    }

    public void setAliasStringBuilder0Same1(String _v) {
        this.aliasStringBuilder0Same1 =_v;
    }

    public String getAliasStringBuilder0StringBuilder0() {
        return aliasStringBuilder0StringBuilder0;
    }

    public void setAliasStringBuilder0StringBuilder0(String _v) {
        this.aliasStringBuilder0StringBuilder0 =_v;
    }

    public String getAliasStringBuilder1StringBuilder0() {
        return aliasStringBuilder1StringBuilder0;
    }

    public void setAliasStringBuilder1StringBuilder0(String _v) {
        this.aliasStringBuilder1StringBuilder0 =_v;
    }

    public String getAliasStringBuilder2StringBuilder0() {
        return aliasStringBuilder2StringBuilder0;
    }

    public void setAliasStringBuilder2StringBuilder0(String _v) {
        this.aliasStringBuilder2StringBuilder0 =_v;
    }

    public String getAliasReplacement0Replacement0() {
        return aliasReplacement0Replacement0;
    }

    public void setAliasReplacement0Replacement0(String _v) {
        this.aliasReplacement0Replacement0 =_v;
    }

    public String getAliasReplacement0Replacement1() {
        return aliasReplacement0Replacement1;
    }

    public void setAliasReplacement0Replacement1(String _v) {
        this.aliasReplacement0Replacement1 =_v;
    }
}
