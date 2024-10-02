package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringMap;

public final class AliasParamNumber {
    private static final String BOOLEAN_0_COMPARE_0="363";
    private static final String BOOLEAN_0_COMPARE_1="364";
    private static final String BOOLEAN_0_COMPARE_TO_0="365";
    private static final String BOOLEAN_0_EQUALS_0="366";
    private static final String BOOLEAN_0_PARSE_BOOLEAN_0="367";
    private static final String BOOLEAN_0_TO_STRING_METHOD_0="368";
    private static final String BOOLEAN_0_VALUE_OF_METHOD_0="369";
    private static final String BOOLEAN_1_VALUE_OF_METHOD_0="370";
    private static final String BOOLEAN_0_BOOLEAN_0="371";
    private static final String BOOLEAN_1_BOOLEAN_0="372";
    private static final String BYTE_0_TO_STRING_METHOD_0="373";
    private static final String BYTE_0_PARSE_BYTE_0="374";
    private static final String BYTE_1_PARSE_BYTE_0="375";
    private static final String BYTE_1_PARSE_BYTE_1="376";
    private static final String BYTE_2_PARSE_BYTE_0="377_";
    private static final String BYTE_2_PARSE_BYTE_1="377__";
    private static final String BYTE_2_PARSE_BYTE_2="377___";
    private static final String BYTE_0_COMPARE_TO_0="377";
    private static final String BYTE_0_COMPARE_0="378";
    private static final String BYTE_0_COMPARE_1="379";
    private static final String BYTE_0_PARSE_BYTE_OR_NULL_0="380";
    private static final String BYTE_1_PARSE_BYTE_OR_NULL_0="381";
    private static final String BYTE_1_PARSE_BYTE_OR_NULL_1="382";
    private static final String BYTE_2_PARSE_BYTE_OR_NULL_0="383_";
    private static final String BYTE_2_PARSE_BYTE_OR_NULL_1="383__";
    private static final String BYTE_2_PARSE_BYTE_OR_NULL_2="383___";
    private static final String BYTE_0_TO_BIN_STRING_0="383";
    private static final String BYTE_0_TO_OCT_STRING_0="384";
    private static final String BYTE_0_TO_HEX_STRING_0="385";
    private static final String BYTE_1_TO_HEX_STRING_0="386_";
    private static final String BYTE_1_TO_HEX_STRING_1="386__";
    private static final String BYTE_0_BYTE_0="386";
    private static final String BYTE_1_BYTE_0="387";
    private static final String SHORT_0_TO_STRING_METHOD_0="388";
    private static final String SHORT_0_PARSE_SHORT_0="389";
    private static final String SHORT_1_PARSE_SHORT_0="390";
    private static final String SHORT_1_PARSE_SHORT_1="391";
    private static final String SHORT_2_PARSE_SHORT_0="392_";
    private static final String SHORT_2_PARSE_SHORT_1="392__";
    private static final String SHORT_2_PARSE_SHORT_2="392___";
    private static final String SHORT_0_COMPARE_TO_0="392";
    private static final String SHORT_0_COMPARE_0="393";
    private static final String SHORT_0_COMPARE_1="394";
    private static final String SHORT_0_PARSE_SHORT_OR_NULL_0="395";
    private static final String SHORT_1_PARSE_SHORT_OR_NULL_0="396";
    private static final String SHORT_1_PARSE_SHORT_OR_NULL_1="397";
    private static final String SHORT_2_PARSE_SHORT_OR_NULL_0="398_";
    private static final String SHORT_2_PARSE_SHORT_OR_NULL_1="398__";
    private static final String SHORT_2_PARSE_SHORT_OR_NULL_2="398___";
    private static final String SHORT_0_TO_BIN_STRING_0="398";
    private static final String SHORT_0_TO_OCT_STRING_0="399";
    private static final String SHORT_0_TO_HEX_STRING_0="400";
    private static final String SHORT_1_TO_HEX_STRING_0="401_";
    private static final String SHORT_1_TO_HEX_STRING_1="401__";
    private static final String SHORT_0_SHORT_0="401";
    private static final String SHORT_1_SHORT_0="402";
    private static final String INTEGER_0_TO_STRING_METHOD_0="403";
    private static final String INTEGER_0_PARSE_INT_0="404";
    private static final String INTEGER_1_PARSE_INT_0="405";
    private static final String INTEGER_1_PARSE_INT_1="406";
    private static final String INTEGER_2_PARSE_INT_0="407_";
    private static final String INTEGER_2_PARSE_INT_1="407__";
    private static final String INTEGER_2_PARSE_INT_2="407___";
    private static final String INTEGER_0_COMPARE_TO_0="407";
    private static final String INTEGER_0_COMPARE_0="408";
    private static final String INTEGER_0_COMPARE_1="409";
    private static final String INTEGER_0_PARSE_INT_OR_NULL_0="410";
    private static final String INTEGER_1_PARSE_INT_OR_NULL_0="411";
    private static final String INTEGER_1_PARSE_INT_OR_NULL_1="412";
    private static final String INTEGER_2_PARSE_INT_OR_NULL_0="413_";
    private static final String INTEGER_2_PARSE_INT_OR_NULL_1="413__";
    private static final String INTEGER_2_PARSE_INT_OR_NULL_2="413___";
    private static final String INTEGER_0_TO_BIN_STRING_0="413";
    private static final String INTEGER_0_TO_OCT_STRING_0="414";
    private static final String INTEGER_0_TO_HEX_STRING_0="415";
    private static final String INTEGER_1_TO_HEX_STRING_0="416_";
    private static final String INTEGER_1_TO_HEX_STRING_1="416__";
    private static final String INTEGER_0_INTEGER_0="416";
    private static final String INTEGER_1_INTEGER_0="417";
    private static final String LONG_0_TO_STRING_METHOD_0="418";
    private static final String LONG_1_TO_STRING_METHOD_0="419";
    private static final String LONG_1_TO_STRING_METHOD_1="420";
    private static final String LONG_2_TO_STRING_METHOD_0="421_";
    private static final String LONG_2_TO_STRING_METHOD_1="421__";
    private static final String LONG_2_TO_STRING_METHOD_2="421___";
    private static final String LONG_0_SIGNUM_0="421";
    private static final String DOUBLE_0_SIGNUM_0="422";
    private static final String LONG_0_PARSE_LONG_0="423";
    private static final String LONG_1_PARSE_LONG_0="424";
    private static final String LONG_1_PARSE_LONG_1="425";
    private static final String LONG_2_PARSE_LONG_0="426_";
    private static final String LONG_2_PARSE_LONG_1="426__";
    private static final String LONG_2_PARSE_LONG_2="426___";
    private static final String LONG_0_COMPARE_TO_0="426";
    private static final String LONG_0_COMPARE_0="427";
    private static final String LONG_0_COMPARE_1="428";
    private static final String LONG_0_PARSE_LONG_OR_NULL_0="429";
    private static final String LONG_1_PARSE_LONG_OR_NULL_0="430";
    private static final String LONG_1_PARSE_LONG_OR_NULL_1="431";
    private static final String LONG_2_PARSE_LONG_OR_NULL_0="432_";
    private static final String LONG_2_PARSE_LONG_OR_NULL_1="432__";
    private static final String LONG_2_PARSE_LONG_OR_NULL_2="432___";
    private static final String LONG_0_TO_BIN_STRING_0="432";
    private static final String LONG_0_TO_OCT_STRING_0="433";
    private static final String LONG_0_TO_HEX_STRING_0="434";
    private static final String LONG_1_TO_HEX_STRING_0="435_";
    private static final String LONG_1_TO_HEX_STRING_1="435__";
    private static final String LONG_0_LONG_0="435";
    private static final String LONG_1_LONG_0="436";
    private static final String FLOAT_0_TO_STRING_METHOD_0="437";
    private static final String FLOAT_0_PARSE_FLOAT_0="438";
    private static final String FLOAT_0_COMPARE_TO_0="439";
    private static final String FLOAT_0_COMPARE_0="440";
    private static final String FLOAT_0_COMPARE_1="441";
    private static final String FLOAT_0_PARSE_FLOAT_OR_NULL_0="442";
    private static final String FLOAT_0_IS_INFINITE_0="443";
    private static final String FLOAT_0_IS_NAN_0="444";
    private static final String FLOAT_0_FLOAT_0="445";
    private static final String FLOAT_1_FLOAT_0="446";
    private static final String DOUBLE_0_TO_STRING_METHOD_0="447";
    private static final String DOUBLE_0_PARSE_DOUBLE_0="448";
    private static final String DOUBLE_0_COMPARE_TO_0="449";
    private static final String DOUBLE_0_COMPARE_0="450";
    private static final String DOUBLE_0_COMPARE_1="451";
    private static final String DOUBLE_0_PARSE_DOUBLE_OR_NULL_0="452";
    private static final String DOUBLE_0_IS_INFINITE_0="453";
    private static final String DOUBLE_0_IS_NAN_0="454";
    private static final String DOUBLE_0_DOUBLE_0="455";
    private static final String DOUBLE_1_DOUBLE_0="456";
    private static final String NUMBER_0_TO_STRING_METHOD_0="457";
    private static final String NUMBER_0_EQUALS_0="458";
    private static final String NUMBER_1_EQUALS_0="459";
    private static final String NUMBER_1_EQUALS_1="460";
    private static final String NUMBER_0_COMPARE_TO_0="461";
    private static final String NUMBER_0_COMPARE_0="462";
    private static final String NUMBER_0_COMPARE_1="463";
    private static final String CHARACTER_0_COMPARE_TO_0="464";
    private static final String CHARACTER_0_COMPARE_0="465";
    private static final String CHARACTER_0_COMPARE_1="466";
    private static final String CHARACTER_0_DIGIT_0="467";
    private static final String CHARACTER_0_DIGIT_1="468";
    private static final String CHARACTER_1_DIGIT_0="468_";
    private static final String CHARACTER_1_DIGIT_1="468__";
    private static final String CHARACTER_1_DIGIT_2="468___";
    private static final String CHARACTER_0_FOR_DIGIT_0="469";
    private static final String CHARACTER_0_FOR_DIGIT_1="470";
    private static final String CHARACTER_1_FOR_DIGIT_0="470_";
    private static final String CHARACTER_1_FOR_DIGIT_1="470__";
    private static final String CHARACTER_1_FOR_DIGIT_2="470___";
    private static final String CHARACTER_0_GET_DIRECTIONALITY_0="471";
    private static final String CHARACTER_0_GET_TYPE_0="472";
    private static final String CHARACTER_0_IS_DIGIT_0="473";
    private static final String CHARACTER_0_IS_LETTER_0="474";
    private static final String CHARACTER_0_IS_LETTER_OR_DIGIT_0="475";
    private static final String CHARACTER_0_IS_WORD_CHAR_0="476";
    private static final String CHARACTER_0_IS_WHITESPACE_0="477";
    private static final String CHARACTER_0_IS_LOWER_CASE_0="478";
    private static final String CHARACTER_0_IS_UPPER_CASE_0="479";
    private static final String CHARACTER_0_IS_SPACE_0="480";
    private static final String CHARACTER_0_TO_LOWER_CASE_CHAR_0="481";
    private static final String CHARACTER_0_TO_UPPER_CASE_CHAR_0="482";
    private static final String CHARACTER_0_TO_STRING_METHOD_0="483";
    private static final String CHARACTER_0_CHARACTER_0="484";
    private String aliasBoolean0Compare0="a";
    private String aliasBoolean0Compare1="b";
    private String aliasBoolean0CompareTo0="a";
    private String aliasBoolean0Equals0="a";
    private String aliasBoolean0ParseBoolean0="a";
    private String aliasBoolean0ToStringMethod0="a";
    private String aliasBoolean0ValueOfMethod0="a";
    private String aliasBoolean1ValueOfMethod0="a";
    private String aliasBoolean0Boolean0="a";
    private String aliasBoolean1Boolean0="a";
    private String aliasByte0ToStringMethod0="a";
    private String aliasByte0ParseByte0="a";
    private String aliasByte1ParseByte0="a";
    private String aliasByte1ParseByte1="b";
    private String aliasByte2ParseByte0="a";
    private String aliasByte2ParseByte1="b";
    private String aliasByte2ParseByte2="c";
    private String aliasByte0CompareTo0="a";
    private String aliasByte0Compare0="a";
    private String aliasByte0Compare1="b";
    private String aliasByte0ParseByteOrNull0="a";
    private String aliasByte1ParseByteOrNull0="a";
    private String aliasByte1ParseByteOrNull1="b";
    private String aliasByte2ParseByteOrNull0="a";
    private String aliasByte2ParseByteOrNull1="b";
    private String aliasByte2ParseByteOrNull2="c";
    private String aliasByte0ToBinString0="a";
    private String aliasByte0ToOctString0="a";
    private String aliasByte0ToHexString0="a";
    private String aliasByte1ToHexString0="a";
    private String aliasByte1ToHexString1="b";
    private String aliasByte0Byte0="a";
    private String aliasByte1Byte0="a";
    private String aliasShort0ToStringMethod0="a";
    private String aliasShort0ParseShort0="a";
    private String aliasShort1ParseShort0="a";
    private String aliasShort1ParseShort1="b";
    private String aliasShort2ParseShort0="a";
    private String aliasShort2ParseShort1="b";
    private String aliasShort2ParseShort2="c";
    private String aliasShort0CompareTo0="a";
    private String aliasShort0Compare0="a";
    private String aliasShort0Compare1="b";
    private String aliasShort0ParseShortOrNull0="a";
    private String aliasShort1ParseShortOrNull0="a";
    private String aliasShort1ParseShortOrNull1="b";
    private String aliasShort2ParseShortOrNull0="a";
    private String aliasShort2ParseShortOrNull1="b";
    private String aliasShort2ParseShortOrNull2="c";
    private String aliasShort0ToBinString0="a";
    private String aliasShort0ToOctString0="a";
    private String aliasShort0ToHexString0="a";
    private String aliasShort1ToHexString0="a";
    private String aliasShort1ToHexString1="b";
    private String aliasShort0Short0="a";
    private String aliasShort1Short0="a";
    private String aliasInteger0ToStringMethod0="a";
    private String aliasInteger0ParseInt0="a";
    private String aliasInteger1ParseInt0="a";
    private String aliasInteger1ParseInt1="b";
    private String aliasInteger2ParseInt0="a";
    private String aliasInteger2ParseInt1="b";
    private String aliasInteger2ParseInt2="c";
    private String aliasInteger0CompareTo0="a";
    private String aliasInteger0Compare0="a";
    private String aliasInteger0Compare1="b";
    private String aliasInteger0ParseIntOrNull0="a";
    private String aliasInteger1ParseIntOrNull0="a";
    private String aliasInteger1ParseIntOrNull1="b";
    private String aliasInteger2ParseIntOrNull0="a";
    private String aliasInteger2ParseIntOrNull1="b";
    private String aliasInteger2ParseIntOrNull2="c";
    private String aliasInteger0ToBinString0="a";
    private String aliasInteger0ToOctString0="a";
    private String aliasInteger0ToHexString0="a";
    private String aliasInteger1ToHexString0="a";
    private String aliasInteger1ToHexString1="b";
    private String aliasInteger0Integer0="a";
    private String aliasInteger1Integer0="a";
    private String aliasLong0ToStringMethod0="a";
    private String aliasLong1ToStringMethod0="a";
    private String aliasLong1ToStringMethod1="b";
    private String aliasLong2ToStringMethod0="a";
    private String aliasLong2ToStringMethod1="b";
    private String aliasLong2ToStringMethod2="c";
    private String aliasLong0Signum0="a";
    private String aliasDouble0Signum0="a";
    private String aliasLong0ParseLong0="a";
    private String aliasLong1ParseLong0="a";
    private String aliasLong1ParseLong1="b";
    private String aliasLong2ParseLong0="a";
    private String aliasLong2ParseLong1="b";
    private String aliasLong2ParseLong2="c";
    private String aliasLong0CompareTo0="a";
    private String aliasLong0Compare0="a";
    private String aliasLong0Compare1="b";
    private String aliasLong0ParseLongOrNull0="a";
    private String aliasLong1ParseLongOrNull0="a";
    private String aliasLong1ParseLongOrNull1="b";
    private String aliasLong2ParseLongOrNull0="a";
    private String aliasLong2ParseLongOrNull1="b";
    private String aliasLong2ParseLongOrNull2="c";
    private String aliasLong0ToBinString0="a";
    private String aliasLong0ToOctString0="a";
    private String aliasLong0ToHexString0="a";
    private String aliasLong1ToHexString0="a";
    private String aliasLong1ToHexString1="b";
    private String aliasLong0Long0="a";
    private String aliasLong1Long0="a";
    private String aliasFloat0ToStringMethod0="a";
    private String aliasFloat0ParseFloat0="a";
    private String aliasFloat0CompareTo0="a";
    private String aliasFloat0Compare0="a";
    private String aliasFloat0Compare1="b";
    private String aliasFloat0ParseFloatOrNull0="a";
    private String aliasFloat0IsInfinite0="a";
    private String aliasFloat0IsNan0="a";
    private String aliasFloat0Float0="a";
    private String aliasFloat1Float0="a";
    private String aliasDouble0ToStringMethod0="a";
    private String aliasDouble0ParseDouble0="a";
    private String aliasDouble0CompareTo0="a";
    private String aliasDouble0Compare0="a";
    private String aliasDouble0Compare1="b";
    private String aliasDouble0ParseDoubleOrNull0="a";
    private String aliasDouble0IsInfinite0="a";
    private String aliasDouble0IsNan0="a";
    private String aliasDouble0Double0="a";
    private String aliasDouble1Double0="a";
    private String aliasNumber0ToStringMethod0="a";
    private String aliasNumber0Equals0="a";
    private String aliasNumber1Equals0="a";
    private String aliasNumber1Equals1="b";
    private String aliasNumber0CompareTo0="a";
    private String aliasNumber0Compare0="a";
    private String aliasNumber0Compare1="b";
    private String aliasCharacter0CompareTo0="a";
    private String aliasCharacter0Compare0="a";
    private String aliasCharacter0Compare1="b";
    private String aliasCharacter0Digit0="a";
    private String aliasCharacter0Digit1="b";
    private String aliasCharacter1Digit0="a";
    private String aliasCharacter1Digit1="b";
    private String aliasCharacter1Digit2="c";
    private String aliasCharacter0ForDigit0="a";
    private String aliasCharacter0ForDigit1="b";
    private String aliasCharacter1ForDigit0="a";
    private String aliasCharacter1ForDigit1="b";
    private String aliasCharacter1ForDigit2="c";
    private String aliasCharacter0GetDirectionality0="a";
    private String aliasCharacter0GetType0="a";
    private String aliasCharacter0IsDigit0="a";
    private String aliasCharacter0IsLetter0="a";
    private String aliasCharacter0IsLetterOrDigit0="a";
    private String aliasCharacter0IsWordChar0="a";
    private String aliasCharacter0IsWhitespace0="a";
    private String aliasCharacter0IsLowerCase0="a";
    private String aliasCharacter0IsUpperCase0="a";
    private String aliasCharacter0IsSpace0="a";
    private String aliasCharacter0ToLowerCaseChar0="a";
    private String aliasCharacter0ToUpperCaseChar0="a";
    private String aliasCharacter0ToStringMethod0="a";
    private String aliasCharacter0Character0="a";

    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setAliasBoolean0Compare0(LgNamesContent.get(_util,_cust,_mapping.getVal(BOOLEAN_0_COMPARE_0)));
        setAliasBoolean0Compare1(LgNamesContent.get(_util,_cust,_mapping.getVal(BOOLEAN_0_COMPARE_1)));
        setAliasBoolean0CompareTo0(LgNamesContent.get(_util,_cust,_mapping.getVal(BOOLEAN_0_COMPARE_TO_0)));
        setAliasBoolean0Equals0(LgNamesContent.get(_util,_cust,_mapping.getVal(BOOLEAN_0_EQUALS_0)));
        setAliasBoolean0ParseBoolean0(LgNamesContent.get(_util,_cust,_mapping.getVal(BOOLEAN_0_PARSE_BOOLEAN_0)));
        setAliasBoolean0ToStringMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(BOOLEAN_0_TO_STRING_METHOD_0)));
        setAliasBoolean0ValueOfMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(BOOLEAN_0_VALUE_OF_METHOD_0)));
        setAliasBoolean1ValueOfMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(BOOLEAN_1_VALUE_OF_METHOD_0)));
        setAliasBoolean0Boolean0(LgNamesContent.get(_util,_cust,_mapping.getVal(BOOLEAN_0_BOOLEAN_0)));
        setAliasBoolean1Boolean0(LgNamesContent.get(_util,_cust,_mapping.getVal(BOOLEAN_1_BOOLEAN_0)));
        setAliasByte0ToStringMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_0_TO_STRING_METHOD_0)));
        setAliasByte0ParseByte0(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_0_PARSE_BYTE_0)));
        setAliasByte1ParseByte0(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_1_PARSE_BYTE_0)));
        setAliasByte1ParseByte1(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_1_PARSE_BYTE_1)));
        setAliasByte2ParseByte0(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_2_PARSE_BYTE_0)));
        setAliasByte2ParseByte1(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_2_PARSE_BYTE_1)));
        setAliasByte2ParseByte2(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_2_PARSE_BYTE_2)));
        setAliasByte0CompareTo0(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_0_COMPARE_TO_0)));
        setAliasByte0Compare0(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_0_COMPARE_0)));
        setAliasByte0Compare1(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_0_COMPARE_1)));
        setAliasByte0ParseByteOrNull0(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_0_PARSE_BYTE_OR_NULL_0)));
        setAliasByte1ParseByteOrNull0(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_1_PARSE_BYTE_OR_NULL_0)));
        setAliasByte1ParseByteOrNull1(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_1_PARSE_BYTE_OR_NULL_1)));
        setAliasByte2ParseByteOrNull0(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_2_PARSE_BYTE_OR_NULL_0)));
        setAliasByte2ParseByteOrNull1(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_2_PARSE_BYTE_OR_NULL_1)));
        setAliasByte2ParseByteOrNull2(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_2_PARSE_BYTE_OR_NULL_2)));
        setAliasByte0ToBinString0(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_0_TO_BIN_STRING_0)));
        setAliasByte0ToOctString0(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_0_TO_OCT_STRING_0)));
        setAliasByte0ToHexString0(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_0_TO_HEX_STRING_0)));
        setAliasByte1ToHexString0(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_1_TO_HEX_STRING_0)));
        setAliasByte1ToHexString1(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_1_TO_HEX_STRING_1)));
        setAliasByte0Byte0(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_0_BYTE_0)));
        setAliasByte1Byte0(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_1_BYTE_0)));
        setAliasShort0ToStringMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_0_TO_STRING_METHOD_0)));
        setAliasShort0ParseShort0(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_0_PARSE_SHORT_0)));
        setAliasShort1ParseShort0(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_1_PARSE_SHORT_0)));
        setAliasShort1ParseShort1(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_1_PARSE_SHORT_1)));
        setAliasShort2ParseShort0(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_2_PARSE_SHORT_0)));
        setAliasShort2ParseShort1(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_2_PARSE_SHORT_1)));
        setAliasShort2ParseShort2(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_2_PARSE_SHORT_2)));
        setAliasShort0CompareTo0(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_0_COMPARE_TO_0)));
        setAliasShort0Compare0(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_0_COMPARE_0)));
        setAliasShort0Compare1(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_0_COMPARE_1)));
        setAliasShort0ParseShortOrNull0(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_0_PARSE_SHORT_OR_NULL_0)));
        setAliasShort1ParseShortOrNull0(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_1_PARSE_SHORT_OR_NULL_0)));
        setAliasShort1ParseShortOrNull1(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_1_PARSE_SHORT_OR_NULL_1)));
        setAliasShort2ParseShortOrNull0(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_2_PARSE_SHORT_OR_NULL_0)));
        setAliasShort2ParseShortOrNull1(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_2_PARSE_SHORT_OR_NULL_1)));
        setAliasShort2ParseShortOrNull2(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_2_PARSE_SHORT_OR_NULL_2)));
        setAliasShort0ToBinString0(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_0_TO_BIN_STRING_0)));
        setAliasShort0ToOctString0(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_0_TO_OCT_STRING_0)));
        setAliasShort0ToHexString0(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_0_TO_HEX_STRING_0)));
        setAliasShort1ToHexString0(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_1_TO_HEX_STRING_0)));
        setAliasShort1ToHexString1(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_1_TO_HEX_STRING_1)));
        setAliasShort0Short0(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_0_SHORT_0)));
        setAliasShort1Short0(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_1_SHORT_0)));
        setAliasInteger0ToStringMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_0_TO_STRING_METHOD_0)));
        setAliasInteger0ParseInt0(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_0_PARSE_INT_0)));
        setAliasInteger1ParseInt0(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_1_PARSE_INT_0)));
        setAliasInteger1ParseInt1(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_1_PARSE_INT_1)));
        setAliasInteger2ParseInt0(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_2_PARSE_INT_0)));
        setAliasInteger2ParseInt1(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_2_PARSE_INT_1)));
        setAliasInteger2ParseInt2(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_2_PARSE_INT_2)));
        setAliasInteger0CompareTo0(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_0_COMPARE_TO_0)));
        setAliasInteger0Compare0(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_0_COMPARE_0)));
        setAliasInteger0Compare1(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_0_COMPARE_1)));
        setAliasInteger0ParseIntOrNull0(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_0_PARSE_INT_OR_NULL_0)));
        setAliasInteger1ParseIntOrNull0(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_1_PARSE_INT_OR_NULL_0)));
        setAliasInteger1ParseIntOrNull1(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_1_PARSE_INT_OR_NULL_1)));
        setAliasInteger2ParseIntOrNull0(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_2_PARSE_INT_OR_NULL_0)));
        setAliasInteger2ParseIntOrNull1(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_2_PARSE_INT_OR_NULL_1)));
        setAliasInteger2ParseIntOrNull2(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_2_PARSE_INT_OR_NULL_2)));
        setAliasInteger0ToBinString0(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_0_TO_BIN_STRING_0)));
        setAliasInteger0ToOctString0(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_0_TO_OCT_STRING_0)));
        setAliasInteger0ToHexString0(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_0_TO_HEX_STRING_0)));
        setAliasInteger1ToHexString0(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_1_TO_HEX_STRING_0)));
        setAliasInteger1ToHexString1(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_1_TO_HEX_STRING_1)));
        setAliasInteger0Integer0(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_0_INTEGER_0)));
        setAliasInteger1Integer0(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER_1_INTEGER_0)));
        setAliasLong0ToStringMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_0_TO_STRING_METHOD_0)));
        setAliasLong1ToStringMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_1_TO_STRING_METHOD_0)));
        setAliasLong1ToStringMethod1(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_1_TO_STRING_METHOD_1)));
        setAliasLong2ToStringMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_2_TO_STRING_METHOD_0)));
        setAliasLong2ToStringMethod1(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_2_TO_STRING_METHOD_1)));
        setAliasLong2ToStringMethod2(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_2_TO_STRING_METHOD_2)));
        setAliasLong0Signum0(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_0_SIGNUM_0)));
        setAliasDouble0Signum0(LgNamesContent.get(_util,_cust,_mapping.getVal(DOUBLE_0_SIGNUM_0)));
        setAliasLong0ParseLong0(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_0_PARSE_LONG_0)));
        setAliasLong1ParseLong0(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_1_PARSE_LONG_0)));
        setAliasLong1ParseLong1(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_1_PARSE_LONG_1)));
        setAliasLong2ParseLong0(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_2_PARSE_LONG_0)));
        setAliasLong2ParseLong1(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_2_PARSE_LONG_1)));
        setAliasLong2ParseLong2(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_2_PARSE_LONG_2)));
        setAliasLong0CompareTo0(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_0_COMPARE_TO_0)));
        setAliasLong0Compare0(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_0_COMPARE_0)));
        setAliasLong0Compare1(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_0_COMPARE_1)));
        setAliasLong0ParseLongOrNull0(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_0_PARSE_LONG_OR_NULL_0)));
        setAliasLong1ParseLongOrNull0(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_1_PARSE_LONG_OR_NULL_0)));
        setAliasLong1ParseLongOrNull1(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_1_PARSE_LONG_OR_NULL_1)));
        setAliasLong2ParseLongOrNull0(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_2_PARSE_LONG_OR_NULL_0)));
        setAliasLong2ParseLongOrNull1(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_2_PARSE_LONG_OR_NULL_1)));
        setAliasLong2ParseLongOrNull2(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_2_PARSE_LONG_OR_NULL_2)));
        setAliasLong0ToBinString0(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_0_TO_BIN_STRING_0)));
        setAliasLong0ToOctString0(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_0_TO_OCT_STRING_0)));
        setAliasLong0ToHexString0(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_0_TO_HEX_STRING_0)));
        setAliasLong1ToHexString0(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_1_TO_HEX_STRING_0)));
        setAliasLong1ToHexString1(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_1_TO_HEX_STRING_1)));
        setAliasLong0Long0(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_0_LONG_0)));
        setAliasLong1Long0(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_1_LONG_0)));
        setAliasFloat0ToStringMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(FLOAT_0_TO_STRING_METHOD_0)));
        setAliasFloat0ParseFloat0(LgNamesContent.get(_util,_cust,_mapping.getVal(FLOAT_0_PARSE_FLOAT_0)));
        setAliasFloat0CompareTo0(LgNamesContent.get(_util,_cust,_mapping.getVal(FLOAT_0_COMPARE_TO_0)));
        setAliasFloat0Compare0(LgNamesContent.get(_util,_cust,_mapping.getVal(FLOAT_0_COMPARE_0)));
        setAliasFloat0Compare1(LgNamesContent.get(_util,_cust,_mapping.getVal(FLOAT_0_COMPARE_1)));
        setAliasFloat0ParseFloatOrNull0(LgNamesContent.get(_util,_cust,_mapping.getVal(FLOAT_0_PARSE_FLOAT_OR_NULL_0)));
        setAliasFloat0IsInfinite0(LgNamesContent.get(_util,_cust,_mapping.getVal(FLOAT_0_IS_INFINITE_0)));
        setAliasFloat0IsNan0(LgNamesContent.get(_util,_cust,_mapping.getVal(FLOAT_0_IS_NAN_0)));
        setAliasFloat0Float0(LgNamesContent.get(_util,_cust,_mapping.getVal(FLOAT_0_FLOAT_0)));
        setAliasFloat1Float0(LgNamesContent.get(_util,_cust,_mapping.getVal(FLOAT_1_FLOAT_0)));
        setAliasDouble0ToStringMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(DOUBLE_0_TO_STRING_METHOD_0)));
        setAliasDouble0ParseDouble0(LgNamesContent.get(_util,_cust,_mapping.getVal(DOUBLE_0_PARSE_DOUBLE_0)));
        setAliasDouble0CompareTo0(LgNamesContent.get(_util,_cust,_mapping.getVal(DOUBLE_0_COMPARE_TO_0)));
        setAliasDouble0Compare0(LgNamesContent.get(_util,_cust,_mapping.getVal(DOUBLE_0_COMPARE_0)));
        setAliasDouble0Compare1(LgNamesContent.get(_util,_cust,_mapping.getVal(DOUBLE_0_COMPARE_1)));
        setAliasDouble0ParseDoubleOrNull0(LgNamesContent.get(_util,_cust,_mapping.getVal(DOUBLE_0_PARSE_DOUBLE_OR_NULL_0)));
        setAliasDouble0IsInfinite0(LgNamesContent.get(_util,_cust,_mapping.getVal(DOUBLE_0_IS_INFINITE_0)));
        setAliasDouble0IsNan0(LgNamesContent.get(_util,_cust,_mapping.getVal(DOUBLE_0_IS_NAN_0)));
        setAliasDouble0Double0(LgNamesContent.get(_util,_cust,_mapping.getVal(DOUBLE_0_DOUBLE_0)));
        setAliasDouble1Double0(LgNamesContent.get(_util,_cust,_mapping.getVal(DOUBLE_1_DOUBLE_0)));
        setAliasNumber0ToStringMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(NUMBER_0_TO_STRING_METHOD_0)));
        setAliasNumber0Equals0(LgNamesContent.get(_util,_cust,_mapping.getVal(NUMBER_0_EQUALS_0)));
        setAliasNumber1Equals0(LgNamesContent.get(_util,_cust,_mapping.getVal(NUMBER_1_EQUALS_0)));
        setAliasNumber1Equals1(LgNamesContent.get(_util,_cust,_mapping.getVal(NUMBER_1_EQUALS_1)));
        setAliasNumber0CompareTo0(LgNamesContent.get(_util,_cust,_mapping.getVal(NUMBER_0_COMPARE_TO_0)));
        setAliasNumber0Compare0(LgNamesContent.get(_util,_cust,_mapping.getVal(NUMBER_0_COMPARE_0)));
        setAliasNumber0Compare1(LgNamesContent.get(_util,_cust,_mapping.getVal(NUMBER_0_COMPARE_1)));
        setAliasCharacter0CompareTo0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_COMPARE_TO_0)));
        setAliasCharacter0Compare0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_COMPARE_0)));
        setAliasCharacter0Compare1(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_COMPARE_1)));
        setAliasCharacter0Digit0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_DIGIT_0)));
        setAliasCharacter0Digit1(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_DIGIT_1)));
        setAliasCharacter1Digit0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_1_DIGIT_0)));
        setAliasCharacter1Digit1(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_1_DIGIT_1)));
        setAliasCharacter1Digit2(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_1_DIGIT_2)));
        setAliasCharacter0ForDigit0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_FOR_DIGIT_0)));
        setAliasCharacter0ForDigit1(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_FOR_DIGIT_1)));
        setAliasCharacter1ForDigit0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_1_FOR_DIGIT_0)));
        setAliasCharacter1ForDigit1(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_1_FOR_DIGIT_1)));
        setAliasCharacter1ForDigit2(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_1_FOR_DIGIT_2)));
        setAliasCharacter0GetDirectionality0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_GET_DIRECTIONALITY_0)));
        setAliasCharacter0GetType0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_GET_TYPE_0)));
        setAliasCharacter0IsDigit0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_IS_DIGIT_0)));
        setAliasCharacter0IsLetter0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_IS_LETTER_0)));
        setAliasCharacter0IsLetterOrDigit0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_IS_LETTER_OR_DIGIT_0)));
        setAliasCharacter0IsWordChar0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_IS_WORD_CHAR_0)));
        setAliasCharacter0IsWhitespace0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_IS_WHITESPACE_0)));
        setAliasCharacter0IsLowerCase0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_IS_LOWER_CASE_0)));
        setAliasCharacter0IsUpperCase0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_IS_UPPER_CASE_0)));
        setAliasCharacter0IsSpace0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_IS_SPACE_0)));
        setAliasCharacter0ToLowerCaseChar0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_TO_LOWER_CASE_CHAR_0)));
        setAliasCharacter0ToUpperCaseChar0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_TO_UPPER_CASE_CHAR_0)));
        setAliasCharacter0ToStringMethod0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_TO_STRING_METHOD_0)));
        setAliasCharacter0Character0(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER_0_CHARACTER_0)));
    }
    public static void en(TranslationsFile _en){
        _en.add(BOOLEAN_0_COMPARE_0,"Boolean0Compare0=a");
        _en.add(BOOLEAN_0_COMPARE_1,"Boolean0Compare1=b");
        _en.add(BOOLEAN_0_COMPARE_TO_0,"Boolean0CompareTo0=a");
        _en.add(BOOLEAN_0_EQUALS_0,"Boolean0Equals0=a");
        _en.add(BOOLEAN_0_PARSE_BOOLEAN_0,"Boolean0ParseBoolean0=a");
        _en.add(BOOLEAN_0_TO_STRING_METHOD_0,"Boolean0ToStringMethod0=a");
        _en.add(BOOLEAN_0_VALUE_OF_METHOD_0,"Boolean0ValueOfMethod0=a");
        _en.add(BOOLEAN_1_VALUE_OF_METHOD_0,"Boolean1ValueOfMethod0=a");
        _en.add(BOOLEAN_0_BOOLEAN_0,"Boolean0Boolean0=a");
        _en.add(BOOLEAN_1_BOOLEAN_0,"Boolean1Boolean0=a");
        _en.add(BYTE_0_TO_STRING_METHOD_0,"Byte0ToStringMethod0=a");
        _en.add(BYTE_0_PARSE_BYTE_0,"Byte0ParseByte0=a");
        _en.add(BYTE_1_PARSE_BYTE_0,"Byte1ParseByte0=a");
        _en.add(BYTE_1_PARSE_BYTE_1,"Byte1ParseByte1=b");
        _en.add(BYTE_2_PARSE_BYTE_0,"Byte2ParseByte0=a");
        _en.add(BYTE_2_PARSE_BYTE_1,"Byte2ParseByte1=b");
        _en.add(BYTE_2_PARSE_BYTE_2,"Byte2ParseByte2=c");
        _en.add(BYTE_0_COMPARE_TO_0,"Byte0CompareTo0=a");
        _en.add(BYTE_0_COMPARE_0,"Byte0Compare0=a");
        _en.add(BYTE_0_COMPARE_1,"Byte0Compare1=b");
        _en.add(BYTE_0_PARSE_BYTE_OR_NULL_0,"Byte0ParseByteOrNull0=a");
        _en.add(BYTE_1_PARSE_BYTE_OR_NULL_0,"Byte1ParseByteOrNull0=a");
        _en.add(BYTE_1_PARSE_BYTE_OR_NULL_1,"Byte1ParseByteOrNull1=b");
        _en.add(BYTE_2_PARSE_BYTE_OR_NULL_0,"Byte2ParseByteOrNull0=a");
        _en.add(BYTE_2_PARSE_BYTE_OR_NULL_1,"Byte2ParseByteOrNull1=b");
        _en.add(BYTE_2_PARSE_BYTE_OR_NULL_2,"Byte2ParseByteOrNull2=c");
        _en.add(BYTE_0_TO_BIN_STRING_0,"Byte0ToBinString0=a");
        _en.add(BYTE_0_TO_OCT_STRING_0,"Byte0ToOctString0=a");
        _en.add(BYTE_0_TO_HEX_STRING_0,"Byte0ToHexString0=a");
        _en.add(BYTE_1_TO_HEX_STRING_0,"Byte1ToHexString0=a");
        _en.add(BYTE_1_TO_HEX_STRING_1,"Byte1ToHexString1=b");
        _en.add(BYTE_0_BYTE_0,"Byte0Byte0=a");
        _en.add(BYTE_1_BYTE_0,"Byte1Byte0=a");
        _en.add(SHORT_0_TO_STRING_METHOD_0,"Short0ToStringMethod0=a");
        _en.add(SHORT_0_PARSE_SHORT_0,"Short0ParseShort0=a");
        _en.add(SHORT_1_PARSE_SHORT_0,"Short1ParseShort0=a");
        _en.add(SHORT_1_PARSE_SHORT_1,"Short1ParseShort1=b");
        _en.add(SHORT_2_PARSE_SHORT_0,"Short2ParseShort0=a");
        _en.add(SHORT_2_PARSE_SHORT_1,"Short2ParseShort1=b");
        _en.add(SHORT_2_PARSE_SHORT_2,"Short2ParseShort2=c");
        _en.add(SHORT_0_COMPARE_TO_0,"Short0CompareTo0=a");
        _en.add(SHORT_0_COMPARE_0,"Short0Compare0=a");
        _en.add(SHORT_0_COMPARE_1,"Short0Compare1=b");
        _en.add(SHORT_0_PARSE_SHORT_OR_NULL_0,"Short0ParseShortOrNull0=a");
        _en.add(SHORT_1_PARSE_SHORT_OR_NULL_0,"Short1ParseShortOrNull0=a");
        _en.add(SHORT_1_PARSE_SHORT_OR_NULL_1,"Short1ParseShortOrNull1=b");
        _en.add(SHORT_2_PARSE_SHORT_OR_NULL_0,"Short2ParseShortOrNull0=a");
        _en.add(SHORT_2_PARSE_SHORT_OR_NULL_1,"Short2ParseShortOrNull1=b");
        _en.add(SHORT_2_PARSE_SHORT_OR_NULL_2,"Short2ParseShortOrNull2=c");
        _en.add(SHORT_0_TO_BIN_STRING_0,"Short0ToBinString0=a");
        _en.add(SHORT_0_TO_OCT_STRING_0,"Short0ToOctString0=a");
        _en.add(SHORT_0_TO_HEX_STRING_0,"Short0ToHexString0=a");
        _en.add(SHORT_1_TO_HEX_STRING_0,"Short1ToHexString0=a");
        _en.add(SHORT_1_TO_HEX_STRING_1,"Short1ToHexString1=b");
        _en.add(SHORT_0_SHORT_0,"Short0Short0=a");
        _en.add(SHORT_1_SHORT_0,"Short1Short0=a");
        _en.add(INTEGER_0_TO_STRING_METHOD_0,"Integer0ToStringMethod0=a");
        _en.add(INTEGER_0_PARSE_INT_0,"Integer0ParseInt0=a");
        _en.add(INTEGER_1_PARSE_INT_0,"Integer1ParseInt0=a");
        _en.add(INTEGER_1_PARSE_INT_1,"Integer1ParseInt1=b");
        _en.add(INTEGER_2_PARSE_INT_0,"Integer2ParseInt0=a");
        _en.add(INTEGER_2_PARSE_INT_1,"Integer2ParseInt1=b");
        _en.add(INTEGER_2_PARSE_INT_2,"Integer2ParseInt2=c");
        _en.add(INTEGER_0_COMPARE_TO_0,"Integer0CompareTo0=a");
        _en.add(INTEGER_0_COMPARE_0,"Integer0Compare0=a");
        _en.add(INTEGER_0_COMPARE_1,"Integer0Compare1=b");
        _en.add(INTEGER_0_PARSE_INT_OR_NULL_0,"Integer0ParseIntOrNull0=a");
        _en.add(INTEGER_1_PARSE_INT_OR_NULL_0,"Integer1ParseIntOrNull0=a");
        _en.add(INTEGER_1_PARSE_INT_OR_NULL_1,"Integer1ParseIntOrNull1=b");
        _en.add(INTEGER_2_PARSE_INT_OR_NULL_0,"Integer2ParseIntOrNull0=a");
        _en.add(INTEGER_2_PARSE_INT_OR_NULL_1,"Integer2ParseIntOrNull1=b");
        _en.add(INTEGER_2_PARSE_INT_OR_NULL_2,"Integer2ParseIntOrNull2=c");
        _en.add(INTEGER_0_TO_BIN_STRING_0,"Integer0ToBinString0=a");
        _en.add(INTEGER_0_TO_OCT_STRING_0,"Integer0ToOctString0=a");
        _en.add(INTEGER_0_TO_HEX_STRING_0,"Integer0ToHexString0=a");
        _en.add(INTEGER_1_TO_HEX_STRING_0,"Integer1ToHexString0=a");
        _en.add(INTEGER_1_TO_HEX_STRING_1,"Integer1ToHexString1=b");
        _en.add(INTEGER_0_INTEGER_0,"Integer0Integer0=a");
        _en.add(INTEGER_1_INTEGER_0,"Integer1Integer0=a");
        _en.add(LONG_0_TO_STRING_METHOD_0,"Long0ToStringMethod0=a");
        _en.add(LONG_1_TO_STRING_METHOD_0,"Long1ToStringMethod0=a");
        _en.add(LONG_1_TO_STRING_METHOD_1,"Long1ToStringMethod1=b");
        _en.add(LONG_2_TO_STRING_METHOD_0,"Long2ToStringMethod0=a");
        _en.add(LONG_2_TO_STRING_METHOD_1,"Long2ToStringMethod1=b");
        _en.add(LONG_2_TO_STRING_METHOD_2,"Long2ToStringMethod2=c");
        _en.add(LONG_0_SIGNUM_0,"Long0Signum0=a");
        _en.add(DOUBLE_0_SIGNUM_0,"Double0Signum0=a");
        _en.add(LONG_0_PARSE_LONG_0,"Long0ParseLong0=a");
        _en.add(LONG_1_PARSE_LONG_0,"Long1ParseLong0=a");
        _en.add(LONG_1_PARSE_LONG_1,"Long1ParseLong1=b");
        _en.add(LONG_2_PARSE_LONG_0,"Long2ParseLong0=a");
        _en.add(LONG_2_PARSE_LONG_1,"Long2ParseLong1=b");
        _en.add(LONG_2_PARSE_LONG_2,"Long2ParseLong2=c");
        _en.add(LONG_0_COMPARE_TO_0,"Long0CompareTo0=a");
        _en.add(LONG_0_COMPARE_0,"Long0Compare0=a");
        _en.add(LONG_0_COMPARE_1,"Long0Compare1=b");
        _en.add(LONG_0_PARSE_LONG_OR_NULL_0,"Long0ParseLongOrNull0=a");
        _en.add(LONG_1_PARSE_LONG_OR_NULL_0,"Long1ParseLongOrNull0=a");
        _en.add(LONG_1_PARSE_LONG_OR_NULL_1,"Long1ParseLongOrNull1=b");
        _en.add(LONG_2_PARSE_LONG_OR_NULL_0,"Long2ParseLongOrNull0=a");
        _en.add(LONG_2_PARSE_LONG_OR_NULL_1,"Long2ParseLongOrNull1=b");
        _en.add(LONG_2_PARSE_LONG_OR_NULL_2,"Long2ParseLongOrNull2=c");
        _en.add(LONG_0_TO_BIN_STRING_0,"Long0ToBinString0=a");
        _en.add(LONG_0_TO_OCT_STRING_0,"Long0ToOctString0=a");
        _en.add(LONG_0_TO_HEX_STRING_0,"Long0ToHexString0=a");
        _en.add(LONG_1_TO_HEX_STRING_0,"Long1ToHexString0=a");
        _en.add(LONG_1_TO_HEX_STRING_1,"Long1ToHexString1=b");
        _en.add(LONG_0_LONG_0,"Long0Long0=a");
        _en.add(LONG_1_LONG_0,"Long1Long0=a");
        _en.add(FLOAT_0_TO_STRING_METHOD_0,"Float0ToStringMethod0=a");
        _en.add(FLOAT_0_PARSE_FLOAT_0,"Float0ParseFloat0=a");
        _en.add(FLOAT_0_COMPARE_TO_0,"Float0CompareTo0=a");
        _en.add(FLOAT_0_COMPARE_0,"Float0Compare0=a");
        _en.add(FLOAT_0_COMPARE_1,"Float0Compare1=b");
        _en.add(FLOAT_0_PARSE_FLOAT_OR_NULL_0,"Float0ParseFloatOrNull0=a");
        _en.add(FLOAT_0_IS_INFINITE_0,"Float0IsInfinite0=a");
        _en.add(FLOAT_0_IS_NAN_0,"Float0IsNan0=a");
        _en.add(FLOAT_0_FLOAT_0,"Float0Float0=a");
        _en.add(FLOAT_1_FLOAT_0,"Float1Float0=a");
        _en.add(DOUBLE_0_TO_STRING_METHOD_0,"Double0ToStringMethod0=a");
        _en.add(DOUBLE_0_PARSE_DOUBLE_0,"Double0ParseDouble0=a");
        _en.add(DOUBLE_0_COMPARE_TO_0,"Double0CompareTo0=a");
        _en.add(DOUBLE_0_COMPARE_0,"Double0Compare0=a");
        _en.add(DOUBLE_0_COMPARE_1,"Double0Compare1=b");
        _en.add(DOUBLE_0_PARSE_DOUBLE_OR_NULL_0,"Double0ParseDoubleOrNull0=a");
        _en.add(DOUBLE_0_IS_INFINITE_0,"Double0IsInfinite0=a");
        _en.add(DOUBLE_0_IS_NAN_0,"Double0IsNan0=a");
        _en.add(DOUBLE_0_DOUBLE_0,"Double0Double0=a");
        _en.add(DOUBLE_1_DOUBLE_0,"Double1Double0=a");
        _en.add(NUMBER_0_TO_STRING_METHOD_0,"Number0ToStringMethod0=a");
        _en.add(NUMBER_0_EQUALS_0,"Number0Equals0=a");
        _en.add(NUMBER_1_EQUALS_0,"Number1Equals0=a");
        _en.add(NUMBER_1_EQUALS_1,"Number1Equals1=b");
        _en.add(NUMBER_0_COMPARE_TO_0,"Number0CompareTo0=a");
        _en.add(NUMBER_0_COMPARE_0,"Number0Compare0=a");
        _en.add(NUMBER_0_COMPARE_1,"Number0Compare1=b");
        _en.add(CHARACTER_0_COMPARE_TO_0,"Character0CompareTo0=a");
        _en.add(CHARACTER_0_COMPARE_0,"Character0Compare0=a");
        _en.add(CHARACTER_0_COMPARE_1,"Character0Compare1=b");
        _en.add(CHARACTER_0_DIGIT_0,"Character0Digit0=a");
        _en.add(CHARACTER_0_DIGIT_1,"Character0Digit1=b");
        _en.add(CHARACTER_1_DIGIT_0,"Character1Digit0=a");
        _en.add(CHARACTER_1_DIGIT_1,"Character1Digit1=b");
        _en.add(CHARACTER_1_DIGIT_2,"Character1Digit2=c");
        _en.add(CHARACTER_0_FOR_DIGIT_0,"Character0ForDigit0=a");
        _en.add(CHARACTER_0_FOR_DIGIT_1,"Character0ForDigit1=b");
        _en.add(CHARACTER_1_FOR_DIGIT_0,"Character1ForDigit0=a");
        _en.add(CHARACTER_1_FOR_DIGIT_1,"Character1ForDigit1=b");
        _en.add(CHARACTER_1_FOR_DIGIT_2,"Character1ForDigit2=c");
        _en.add(CHARACTER_0_GET_DIRECTIONALITY_0,"Character0GetDirectionality0=a");
        _en.add(CHARACTER_0_GET_TYPE_0,"Character0GetType0=a");
        _en.add(CHARACTER_0_IS_DIGIT_0,"Character0IsDigit0=a");
        _en.add(CHARACTER_0_IS_LETTER_0,"Character0IsLetter0=a");
        _en.add(CHARACTER_0_IS_LETTER_OR_DIGIT_0,"Character0IsLetterOrDigit0=a");
        _en.add(CHARACTER_0_IS_WORD_CHAR_0,"Character0IsWordChar0=a");
        _en.add(CHARACTER_0_IS_WHITESPACE_0,"Character0IsWhitespace0=a");
        _en.add(CHARACTER_0_IS_LOWER_CASE_0,"Character0IsLowerCase0=a");
        _en.add(CHARACTER_0_IS_UPPER_CASE_0,"Character0IsUpperCase0=a");
        _en.add(CHARACTER_0_IS_SPACE_0,"Character0IsSpace0=a");
        _en.add(CHARACTER_0_TO_LOWER_CASE_CHAR_0,"Character0ToLowerCaseChar0=a");
        _en.add(CHARACTER_0_TO_UPPER_CASE_CHAR_0,"Character0ToUpperCaseChar0=a");
        _en.add(CHARACTER_0_TO_STRING_METHOD_0,"Character0ToStringMethod0=a");
        _en.add(CHARACTER_0_CHARACTER_0,"Character0Character0=a");
    }
    public static void fr(TranslationsFile _fr){
        _fr.add(BOOLEAN_0_COMPARE_0,"Boolean0Compare0=a");
        _fr.add(BOOLEAN_0_COMPARE_1,"Boolean0Compare1=b");
        _fr.add(BOOLEAN_0_COMPARE_TO_0,"Boolean0CompareTo0=a");
        _fr.add(BOOLEAN_0_EQUALS_0,"Boolean0Equals0=a");
        _fr.add(BOOLEAN_0_PARSE_BOOLEAN_0,"Boolean0ParseBoolean0=a");
        _fr.add(BOOLEAN_0_TO_STRING_METHOD_0,"Boolean0ToStringMethod0=a");
        _fr.add(BOOLEAN_0_VALUE_OF_METHOD_0,"Boolean0ValueOfMethod0=a");
        _fr.add(BOOLEAN_1_VALUE_OF_METHOD_0,"Boolean1ValueOfMethod0=a");
        _fr.add(BOOLEAN_0_BOOLEAN_0,"Boolean0Boolean0=a");
        _fr.add(BOOLEAN_1_BOOLEAN_0,"Boolean1Boolean0=a");
        _fr.add(BYTE_0_TO_STRING_METHOD_0,"Byte0ToStringMethod0=a");
        _fr.add(BYTE_0_PARSE_BYTE_0,"Byte0ParseByte0=a");
        _fr.add(BYTE_1_PARSE_BYTE_0,"Byte1ParseByte0=a");
        _fr.add(BYTE_1_PARSE_BYTE_1,"Byte1ParseByte1=b");
        _fr.add(BYTE_2_PARSE_BYTE_0,"Byte2ParseByte0=a");
        _fr.add(BYTE_2_PARSE_BYTE_1,"Byte2ParseByte1=b");
        _fr.add(BYTE_2_PARSE_BYTE_2,"Byte2ParseByte2=c");
        _fr.add(BYTE_0_COMPARE_TO_0,"Byte0CompareTo0=a");
        _fr.add(BYTE_0_COMPARE_0,"Byte0Compare0=a");
        _fr.add(BYTE_0_COMPARE_1,"Byte0Compare1=b");
        _fr.add(BYTE_0_PARSE_BYTE_OR_NULL_0,"Byte0ParseByteOrNull0=a");
        _fr.add(BYTE_1_PARSE_BYTE_OR_NULL_0,"Byte1ParseByteOrNull0=a");
        _fr.add(BYTE_1_PARSE_BYTE_OR_NULL_1,"Byte1ParseByteOrNull1=b");
        _fr.add(BYTE_2_PARSE_BYTE_OR_NULL_0,"Byte2ParseByteOrNull0=a");
        _fr.add(BYTE_2_PARSE_BYTE_OR_NULL_1,"Byte2ParseByteOrNull1=b");
        _fr.add(BYTE_2_PARSE_BYTE_OR_NULL_2,"Byte2ParseByteOrNull2=c");
        _fr.add(BYTE_0_TO_BIN_STRING_0,"Byte0ToBinString0=a");
        _fr.add(BYTE_0_TO_OCT_STRING_0,"Byte0ToOctString0=a");
        _fr.add(BYTE_0_TO_HEX_STRING_0,"Byte0ToHexString0=a");
        _fr.add(BYTE_1_TO_HEX_STRING_0,"Byte1ToHexString0=a");
        _fr.add(BYTE_1_TO_HEX_STRING_1,"Byte1ToHexString1=b");
        _fr.add(BYTE_0_BYTE_0,"Byte0Byte0=a");
        _fr.add(BYTE_1_BYTE_0,"Byte1Byte0=a");
        _fr.add(SHORT_0_TO_STRING_METHOD_0,"Short0ToStringMethod0=a");
        _fr.add(SHORT_0_PARSE_SHORT_0,"Short0ParseShort0=a");
        _fr.add(SHORT_1_PARSE_SHORT_0,"Short1ParseShort0=a");
        _fr.add(SHORT_1_PARSE_SHORT_1,"Short1ParseShort1=b");
        _fr.add(SHORT_2_PARSE_SHORT_0,"Short2ParseShort0=a");
        _fr.add(SHORT_2_PARSE_SHORT_1,"Short2ParseShort1=b");
        _fr.add(SHORT_2_PARSE_SHORT_2,"Short2ParseShort2=c");
        _fr.add(SHORT_0_COMPARE_TO_0,"Short0CompareTo0=a");
        _fr.add(SHORT_0_COMPARE_0,"Short0Compare0=a");
        _fr.add(SHORT_0_COMPARE_1,"Short0Compare1=b");
        _fr.add(SHORT_0_PARSE_SHORT_OR_NULL_0,"Short0ParseShortOrNull0=a");
        _fr.add(SHORT_1_PARSE_SHORT_OR_NULL_0,"Short1ParseShortOrNull0=a");
        _fr.add(SHORT_1_PARSE_SHORT_OR_NULL_1,"Short1ParseShortOrNull1=b");
        _fr.add(SHORT_2_PARSE_SHORT_OR_NULL_0,"Short2ParseShortOrNull0=a");
        _fr.add(SHORT_2_PARSE_SHORT_OR_NULL_1,"Short2ParseShortOrNull1=b");
        _fr.add(SHORT_2_PARSE_SHORT_OR_NULL_2,"Short2ParseShortOrNull2=c");
        _fr.add(SHORT_0_TO_BIN_STRING_0,"Short0ToBinString0=a");
        _fr.add(SHORT_0_TO_OCT_STRING_0,"Short0ToOctString0=a");
        _fr.add(SHORT_0_TO_HEX_STRING_0,"Short0ToHexString0=a");
        _fr.add(SHORT_1_TO_HEX_STRING_0,"Short1ToHexString0=a");
        _fr.add(SHORT_1_TO_HEX_STRING_1,"Short1ToHexString1=b");
        _fr.add(SHORT_0_SHORT_0,"Short0Short0=a");
        _fr.add(SHORT_1_SHORT_0,"Short1Short0=a");
        _fr.add(INTEGER_0_TO_STRING_METHOD_0,"Integer0ToStringMethod0=a");
        _fr.add(INTEGER_0_PARSE_INT_0,"Integer0ParseInt0=a");
        _fr.add(INTEGER_1_PARSE_INT_0,"Integer1ParseInt0=a");
        _fr.add(INTEGER_1_PARSE_INT_1,"Integer1ParseInt1=b");
        _fr.add(INTEGER_2_PARSE_INT_0,"Integer2ParseInt0=a");
        _fr.add(INTEGER_2_PARSE_INT_1,"Integer2ParseInt1=b");
        _fr.add(INTEGER_2_PARSE_INT_2,"Integer2ParseInt2=c");
        _fr.add(INTEGER_0_COMPARE_TO_0,"Integer0CompareTo0=a");
        _fr.add(INTEGER_0_COMPARE_0,"Integer0Compare0=a");
        _fr.add(INTEGER_0_COMPARE_1,"Integer0Compare1=b");
        _fr.add(INTEGER_0_PARSE_INT_OR_NULL_0,"Integer0ParseIntOrNull0=a");
        _fr.add(INTEGER_1_PARSE_INT_OR_NULL_0,"Integer1ParseIntOrNull0=a");
        _fr.add(INTEGER_1_PARSE_INT_OR_NULL_1,"Integer1ParseIntOrNull1=b");
        _fr.add(INTEGER_2_PARSE_INT_OR_NULL_0,"Integer2ParseIntOrNull0=a");
        _fr.add(INTEGER_2_PARSE_INT_OR_NULL_1,"Integer2ParseIntOrNull1=b");
        _fr.add(INTEGER_2_PARSE_INT_OR_NULL_2,"Integer2ParseIntOrNull2=c");
        _fr.add(INTEGER_0_TO_BIN_STRING_0,"Integer0ToBinString0=a");
        _fr.add(INTEGER_0_TO_OCT_STRING_0,"Integer0ToOctString0=a");
        _fr.add(INTEGER_0_TO_HEX_STRING_0,"Integer0ToHexString0=a");
        _fr.add(INTEGER_1_TO_HEX_STRING_0,"Integer1ToHexString0=a");
        _fr.add(INTEGER_1_TO_HEX_STRING_1,"Integer1ToHexString1=b");
        _fr.add(INTEGER_0_INTEGER_0,"Integer0Integer0=a");
        _fr.add(INTEGER_1_INTEGER_0,"Integer1Integer0=a");
        _fr.add(LONG_0_TO_STRING_METHOD_0,"Long0ToStringMethod0=a");
        _fr.add(LONG_1_TO_STRING_METHOD_0,"Long1ToStringMethod0=a");
        _fr.add(LONG_1_TO_STRING_METHOD_1,"Long1ToStringMethod1=b");
        _fr.add(LONG_2_TO_STRING_METHOD_0,"Long2ToStringMethod0=a");
        _fr.add(LONG_2_TO_STRING_METHOD_1,"Long2ToStringMethod1=b");
        _fr.add(LONG_2_TO_STRING_METHOD_2,"Long2ToStringMethod2=c");
        _fr.add(LONG_0_SIGNUM_0,"Long0Signum0=a");
        _fr.add(DOUBLE_0_SIGNUM_0,"Double0Signum0=a");
        _fr.add(LONG_0_PARSE_LONG_0,"Long0ParseLong0=a");
        _fr.add(LONG_1_PARSE_LONG_0,"Long1ParseLong0=a");
        _fr.add(LONG_1_PARSE_LONG_1,"Long1ParseLong1=b");
        _fr.add(LONG_2_PARSE_LONG_0,"Long2ParseLong0=a");
        _fr.add(LONG_2_PARSE_LONG_1,"Long2ParseLong1=b");
        _fr.add(LONG_2_PARSE_LONG_2,"Long2ParseLong2=c");
        _fr.add(LONG_0_COMPARE_TO_0,"Long0CompareTo0=a");
        _fr.add(LONG_0_COMPARE_0,"Long0Compare0=a");
        _fr.add(LONG_0_COMPARE_1,"Long0Compare1=b");
        _fr.add(LONG_0_PARSE_LONG_OR_NULL_0,"Long0ParseLongOrNull0=a");
        _fr.add(LONG_1_PARSE_LONG_OR_NULL_0,"Long1ParseLongOrNull0=a");
        _fr.add(LONG_1_PARSE_LONG_OR_NULL_1,"Long1ParseLongOrNull1=b");
        _fr.add(LONG_2_PARSE_LONG_OR_NULL_0,"Long2ParseLongOrNull0=a");
        _fr.add(LONG_2_PARSE_LONG_OR_NULL_1,"Long2ParseLongOrNull1=b");
        _fr.add(LONG_2_PARSE_LONG_OR_NULL_2,"Long2ParseLongOrNull2=c");
        _fr.add(LONG_0_TO_BIN_STRING_0,"Long0ToBinString0=a");
        _fr.add(LONG_0_TO_OCT_STRING_0,"Long0ToOctString0=a");
        _fr.add(LONG_0_TO_HEX_STRING_0,"Long0ToHexString0=a");
        _fr.add(LONG_1_TO_HEX_STRING_0,"Long1ToHexString0=a");
        _fr.add(LONG_1_TO_HEX_STRING_1,"Long1ToHexString1=b");
        _fr.add(LONG_0_LONG_0,"Long0Long0=a");
        _fr.add(LONG_1_LONG_0,"Long1Long0=a");
        _fr.add(FLOAT_0_TO_STRING_METHOD_0,"Float0ToStringMethod0=a");
        _fr.add(FLOAT_0_PARSE_FLOAT_0,"Float0ParseFloat0=a");
        _fr.add(FLOAT_0_COMPARE_TO_0,"Float0CompareTo0=a");
        _fr.add(FLOAT_0_COMPARE_0,"Float0Compare0=a");
        _fr.add(FLOAT_0_COMPARE_1,"Float0Compare1=b");
        _fr.add(FLOAT_0_PARSE_FLOAT_OR_NULL_0,"Float0ParseFloatOrNull0=a");
        _fr.add(FLOAT_0_IS_INFINITE_0,"Float0IsInfinite0=a");
        _fr.add(FLOAT_0_IS_NAN_0,"Float0IsNan0=a");
        _fr.add(FLOAT_0_FLOAT_0,"Float0Float0=a");
        _fr.add(FLOAT_1_FLOAT_0,"Float1Float0=a");
        _fr.add(DOUBLE_0_TO_STRING_METHOD_0,"Double0ToStringMethod0=a");
        _fr.add(DOUBLE_0_PARSE_DOUBLE_0,"Double0ParseDouble0=a");
        _fr.add(DOUBLE_0_COMPARE_TO_0,"Double0CompareTo0=a");
        _fr.add(DOUBLE_0_COMPARE_0,"Double0Compare0=a");
        _fr.add(DOUBLE_0_COMPARE_1,"Double0Compare1=b");
        _fr.add(DOUBLE_0_PARSE_DOUBLE_OR_NULL_0,"Double0ParseDoubleOrNull0=a");
        _fr.add(DOUBLE_0_IS_INFINITE_0,"Double0IsInfinite0=a");
        _fr.add(DOUBLE_0_IS_NAN_0,"Double0IsNan0=a");
        _fr.add(DOUBLE_0_DOUBLE_0,"Double0Double0=a");
        _fr.add(DOUBLE_1_DOUBLE_0,"Double1Double0=a");
        _fr.add(NUMBER_0_TO_STRING_METHOD_0,"Number0ToStringMethod0=a");
        _fr.add(NUMBER_0_EQUALS_0,"Number0Equals0=a");
        _fr.add(NUMBER_1_EQUALS_0,"Number1Equals0=a");
        _fr.add(NUMBER_1_EQUALS_1,"Number1Equals1=b");
        _fr.add(NUMBER_0_COMPARE_TO_0,"Number0CompareTo0=a");
        _fr.add(NUMBER_0_COMPARE_0,"Number0Compare0=a");
        _fr.add(NUMBER_0_COMPARE_1,"Number0Compare1=b");
        _fr.add(CHARACTER_0_COMPARE_TO_0,"Character0CompareTo0=a");
        _fr.add(CHARACTER_0_COMPARE_0,"Character0Compare0=a");
        _fr.add(CHARACTER_0_COMPARE_1,"Character0Compare1=b");
        _fr.add(CHARACTER_0_DIGIT_0,"Character0Digit0=a");
        _fr.add(CHARACTER_0_DIGIT_1,"Character0Digit1=b");
        _fr.add(CHARACTER_1_DIGIT_0,"Character1Digit0=a");
        _fr.add(CHARACTER_1_DIGIT_1,"Character1Digit1=b");
        _fr.add(CHARACTER_1_DIGIT_2,"Character1Digit2=c");
        _fr.add(CHARACTER_0_FOR_DIGIT_0,"Character0ForDigit0=a");
        _fr.add(CHARACTER_0_FOR_DIGIT_1,"Character0ForDigit1=b");
        _fr.add(CHARACTER_1_FOR_DIGIT_0,"Character1ForDigit0=a");
        _fr.add(CHARACTER_1_FOR_DIGIT_1,"Character1ForDigit1=b");
        _fr.add(CHARACTER_1_FOR_DIGIT_2,"Character1ForDigit2=c");
        _fr.add(CHARACTER_0_GET_DIRECTIONALITY_0,"Character0GetDirectionality0=a");
        _fr.add(CHARACTER_0_GET_TYPE_0,"Character0GetType0=a");
        _fr.add(CHARACTER_0_IS_DIGIT_0,"Character0IsDigit0=a");
        _fr.add(CHARACTER_0_IS_LETTER_0,"Character0IsLetter0=a");
        _fr.add(CHARACTER_0_IS_LETTER_OR_DIGIT_0,"Character0IsLetterOrDigit0=a");
        _fr.add(CHARACTER_0_IS_WORD_CHAR_0,"Character0IsWordChar0=a");
        _fr.add(CHARACTER_0_IS_WHITESPACE_0,"Character0IsWhitespace0=a");
        _fr.add(CHARACTER_0_IS_LOWER_CASE_0,"Character0IsLowerCase0=a");
        _fr.add(CHARACTER_0_IS_UPPER_CASE_0,"Character0IsUpperCase0=a");
        _fr.add(CHARACTER_0_IS_SPACE_0,"Character0IsSpace0=a");
        _fr.add(CHARACTER_0_TO_LOWER_CASE_CHAR_0,"Character0ToLowerCaseChar0=a");
        _fr.add(CHARACTER_0_TO_UPPER_CASE_CHAR_0,"Character0ToUpperCaseChar0=a");
        _fr.add(CHARACTER_0_TO_STRING_METHOD_0,"Character0ToStringMethod0=a");
        _fr.add(CHARACTER_0_CHARACTER_0,"Character0Character0=a");
    }
    public static void mapping(StringMap<String> _m){
        _m.addEntry(BOOLEAN_0_COMPARE_0,"Boolean0Compare0");
        _m.addEntry(BOOLEAN_0_COMPARE_1,"Boolean0Compare1");
        _m.addEntry(BOOLEAN_0_COMPARE_TO_0,"Boolean0CompareTo0");
        _m.addEntry(BOOLEAN_0_EQUALS_0,"Boolean0Equals0");
        _m.addEntry(BOOLEAN_0_PARSE_BOOLEAN_0,"Boolean0ParseBoolean0");
        _m.addEntry(BOOLEAN_0_TO_STRING_METHOD_0,"Boolean0ToStringMethod0");
        _m.addEntry(BOOLEAN_0_VALUE_OF_METHOD_0,"Boolean0ValueOfMethod0");
        _m.addEntry(BOOLEAN_1_VALUE_OF_METHOD_0,"Boolean1ValueOfMethod0");
        _m.addEntry(BOOLEAN_0_BOOLEAN_0,"Boolean0Boolean0");
        _m.addEntry(BOOLEAN_1_BOOLEAN_0,"Boolean1Boolean0");
        _m.addEntry(BYTE_0_TO_STRING_METHOD_0,"Byte0ToStringMethod0");
        _m.addEntry(BYTE_0_PARSE_BYTE_0,"Byte0ParseByte0");
        _m.addEntry(BYTE_1_PARSE_BYTE_0,"Byte1ParseByte0");
        _m.addEntry(BYTE_1_PARSE_BYTE_1,"Byte1ParseByte1");
        _m.addEntry(BYTE_2_PARSE_BYTE_0,"Byte2ParseByte0");
        _m.addEntry(BYTE_2_PARSE_BYTE_1,"Byte2ParseByte1");
        _m.addEntry(BYTE_2_PARSE_BYTE_2,"Byte2ParseByte2");
        _m.addEntry(BYTE_0_COMPARE_TO_0,"Byte0CompareTo0");
        _m.addEntry(BYTE_0_COMPARE_0,"Byte0Compare0");
        _m.addEntry(BYTE_0_COMPARE_1,"Byte0Compare1");
        _m.addEntry(BYTE_0_PARSE_BYTE_OR_NULL_0,"Byte0ParseByteOrNull0");
        _m.addEntry(BYTE_1_PARSE_BYTE_OR_NULL_0,"Byte1ParseByteOrNull0");
        _m.addEntry(BYTE_1_PARSE_BYTE_OR_NULL_1,"Byte1ParseByteOrNull1");
        _m.addEntry(BYTE_2_PARSE_BYTE_OR_NULL_0,"Byte2ParseByteOrNull0");
        _m.addEntry(BYTE_2_PARSE_BYTE_OR_NULL_1,"Byte2ParseByteOrNull1");
        _m.addEntry(BYTE_2_PARSE_BYTE_OR_NULL_2,"Byte2ParseByteOrNull2");
        _m.addEntry(BYTE_0_TO_BIN_STRING_0,"Byte0ToBinString0");
        _m.addEntry(BYTE_0_TO_OCT_STRING_0,"Byte0ToOctString0");
        _m.addEntry(BYTE_0_TO_HEX_STRING_0,"Byte0ToHexString0");
        _m.addEntry(BYTE_1_TO_HEX_STRING_0,"Byte1ToHexString0");
        _m.addEntry(BYTE_1_TO_HEX_STRING_1,"Byte1ToHexString1");
        _m.addEntry(BYTE_0_BYTE_0,"Byte0Byte0");
        _m.addEntry(BYTE_1_BYTE_0,"Byte1Byte0");
        _m.addEntry(SHORT_0_TO_STRING_METHOD_0,"Short0ToStringMethod0");
        _m.addEntry(SHORT_0_PARSE_SHORT_0,"Short0ParseShort0");
        _m.addEntry(SHORT_1_PARSE_SHORT_0,"Short1ParseShort0");
        _m.addEntry(SHORT_1_PARSE_SHORT_1,"Short1ParseShort1");
        _m.addEntry(SHORT_2_PARSE_SHORT_0,"Short2ParseShort0");
        _m.addEntry(SHORT_2_PARSE_SHORT_1,"Short2ParseShort1");
        _m.addEntry(SHORT_2_PARSE_SHORT_2,"Short2ParseShort2");
        _m.addEntry(SHORT_0_COMPARE_TO_0,"Short0CompareTo0");
        _m.addEntry(SHORT_0_COMPARE_0,"Short0Compare0");
        _m.addEntry(SHORT_0_COMPARE_1,"Short0Compare1");
        _m.addEntry(SHORT_0_PARSE_SHORT_OR_NULL_0,"Short0ParseShortOrNull0");
        _m.addEntry(SHORT_1_PARSE_SHORT_OR_NULL_0,"Short1ParseShortOrNull0");
        _m.addEntry(SHORT_1_PARSE_SHORT_OR_NULL_1,"Short1ParseShortOrNull1");
        _m.addEntry(SHORT_2_PARSE_SHORT_OR_NULL_0,"Short2ParseShortOrNull0");
        _m.addEntry(SHORT_2_PARSE_SHORT_OR_NULL_1,"Short2ParseShortOrNull1");
        _m.addEntry(SHORT_2_PARSE_SHORT_OR_NULL_2,"Short2ParseShortOrNull2");
        _m.addEntry(SHORT_0_TO_BIN_STRING_0,"Short0ToBinString0");
        _m.addEntry(SHORT_0_TO_OCT_STRING_0,"Short0ToOctString0");
        _m.addEntry(SHORT_0_TO_HEX_STRING_0,"Short0ToHexString0");
        _m.addEntry(SHORT_1_TO_HEX_STRING_0,"Short1ToHexString0");
        _m.addEntry(SHORT_1_TO_HEX_STRING_1,"Short1ToHexString1");
        _m.addEntry(SHORT_0_SHORT_0,"Short0Short0");
        _m.addEntry(SHORT_1_SHORT_0,"Short1Short0");
        _m.addEntry(INTEGER_0_TO_STRING_METHOD_0,"Integer0ToStringMethod0");
        _m.addEntry(INTEGER_0_PARSE_INT_0,"Integer0ParseInt0");
        _m.addEntry(INTEGER_1_PARSE_INT_0,"Integer1ParseInt0");
        _m.addEntry(INTEGER_1_PARSE_INT_1,"Integer1ParseInt1");
        _m.addEntry(INTEGER_2_PARSE_INT_0,"Integer2ParseInt0");
        _m.addEntry(INTEGER_2_PARSE_INT_1,"Integer2ParseInt1");
        _m.addEntry(INTEGER_2_PARSE_INT_2,"Integer2ParseInt2");
        _m.addEntry(INTEGER_0_COMPARE_TO_0,"Integer0CompareTo0");
        _m.addEntry(INTEGER_0_COMPARE_0,"Integer0Compare0");
        _m.addEntry(INTEGER_0_COMPARE_1,"Integer0Compare1");
        _m.addEntry(INTEGER_0_PARSE_INT_OR_NULL_0,"Integer0ParseIntOrNull0");
        _m.addEntry(INTEGER_1_PARSE_INT_OR_NULL_0,"Integer1ParseIntOrNull0");
        _m.addEntry(INTEGER_1_PARSE_INT_OR_NULL_1,"Integer1ParseIntOrNull1");
        _m.addEntry(INTEGER_2_PARSE_INT_OR_NULL_0,"Integer2ParseIntOrNull0");
        _m.addEntry(INTEGER_2_PARSE_INT_OR_NULL_1,"Integer2ParseIntOrNull1");
        _m.addEntry(INTEGER_2_PARSE_INT_OR_NULL_2,"Integer2ParseIntOrNull2");
        _m.addEntry(INTEGER_0_TO_BIN_STRING_0,"Integer0ToBinString0");
        _m.addEntry(INTEGER_0_TO_OCT_STRING_0,"Integer0ToOctString0");
        _m.addEntry(INTEGER_0_TO_HEX_STRING_0,"Integer0ToHexString0");
        _m.addEntry(INTEGER_1_TO_HEX_STRING_0,"Integer1ToHexString0");
        _m.addEntry(INTEGER_1_TO_HEX_STRING_1,"Integer1ToHexString1");
        _m.addEntry(INTEGER_0_INTEGER_0,"Integer0Integer0");
        _m.addEntry(INTEGER_1_INTEGER_0,"Integer1Integer0");
        _m.addEntry(LONG_0_TO_STRING_METHOD_0,"Long0ToStringMethod0");
        _m.addEntry(LONG_1_TO_STRING_METHOD_0,"Long1ToStringMethod0");
        _m.addEntry(LONG_1_TO_STRING_METHOD_1,"Long1ToStringMethod1");
        _m.addEntry(LONG_2_TO_STRING_METHOD_0,"Long2ToStringMethod0");
        _m.addEntry(LONG_2_TO_STRING_METHOD_1,"Long2ToStringMethod1");
        _m.addEntry(LONG_2_TO_STRING_METHOD_2,"Long2ToStringMethod2");
        _m.addEntry(LONG_0_SIGNUM_0,"Long0Signum0");
        _m.addEntry(DOUBLE_0_SIGNUM_0,"Double0Signum0");
        _m.addEntry(LONG_0_PARSE_LONG_0,"Long0ParseLong0");
        _m.addEntry(LONG_1_PARSE_LONG_0,"Long1ParseLong0");
        _m.addEntry(LONG_1_PARSE_LONG_1,"Long1ParseLong1");
        _m.addEntry(LONG_2_PARSE_LONG_0,"Long2ParseLong0");
        _m.addEntry(LONG_2_PARSE_LONG_1,"Long2ParseLong1");
        _m.addEntry(LONG_2_PARSE_LONG_2,"Long2ParseLong2");
        _m.addEntry(LONG_0_COMPARE_TO_0,"Long0CompareTo0");
        _m.addEntry(LONG_0_COMPARE_0,"Long0Compare0");
        _m.addEntry(LONG_0_COMPARE_1,"Long0Compare1");
        _m.addEntry(LONG_0_PARSE_LONG_OR_NULL_0,"Long0ParseLongOrNull0");
        _m.addEntry(LONG_1_PARSE_LONG_OR_NULL_0,"Long1ParseLongOrNull0");
        _m.addEntry(LONG_1_PARSE_LONG_OR_NULL_1,"Long1ParseLongOrNull1");
        _m.addEntry(LONG_2_PARSE_LONG_OR_NULL_0,"Long2ParseLongOrNull0");
        _m.addEntry(LONG_2_PARSE_LONG_OR_NULL_1,"Long2ParseLongOrNull1");
        _m.addEntry(LONG_2_PARSE_LONG_OR_NULL_2,"Long2ParseLongOrNull2");
        _m.addEntry(LONG_0_TO_BIN_STRING_0,"Long0ToBinString0");
        _m.addEntry(LONG_0_TO_OCT_STRING_0,"Long0ToOctString0");
        _m.addEntry(LONG_0_TO_HEX_STRING_0,"Long0ToHexString0");
        _m.addEntry(LONG_1_TO_HEX_STRING_0,"Long1ToHexString0");
        _m.addEntry(LONG_1_TO_HEX_STRING_1,"Long1ToHexString1");
        _m.addEntry(LONG_0_LONG_0,"Long0Long0");
        _m.addEntry(LONG_1_LONG_0,"Long1Long0");
        _m.addEntry(FLOAT_0_TO_STRING_METHOD_0,"Float0ToStringMethod0");
        _m.addEntry(FLOAT_0_PARSE_FLOAT_0,"Float0ParseFloat0");
        _m.addEntry(FLOAT_0_COMPARE_TO_0,"Float0CompareTo0");
        _m.addEntry(FLOAT_0_COMPARE_0,"Float0Compare0");
        _m.addEntry(FLOAT_0_COMPARE_1,"Float0Compare1");
        _m.addEntry(FLOAT_0_PARSE_FLOAT_OR_NULL_0,"Float0ParseFloatOrNull0");
        _m.addEntry(FLOAT_0_IS_INFINITE_0,"Float0IsInfinite0");
        _m.addEntry(FLOAT_0_IS_NAN_0,"Float0IsNan0");
        _m.addEntry(FLOAT_0_FLOAT_0,"Float0Float0");
        _m.addEntry(FLOAT_1_FLOAT_0,"Float1Float0");
        _m.addEntry(DOUBLE_0_TO_STRING_METHOD_0,"Double0ToStringMethod0");
        _m.addEntry(DOUBLE_0_PARSE_DOUBLE_0,"Double0ParseDouble0");
        _m.addEntry(DOUBLE_0_COMPARE_TO_0,"Double0CompareTo0");
        _m.addEntry(DOUBLE_0_COMPARE_0,"Double0Compare0");
        _m.addEntry(DOUBLE_0_COMPARE_1,"Double0Compare1");
        _m.addEntry(DOUBLE_0_PARSE_DOUBLE_OR_NULL_0,"Double0ParseDoubleOrNull0");
        _m.addEntry(DOUBLE_0_IS_INFINITE_0,"Double0IsInfinite0");
        _m.addEntry(DOUBLE_0_IS_NAN_0,"Double0IsNan0");
        _m.addEntry(DOUBLE_0_DOUBLE_0,"Double0Double0");
        _m.addEntry(DOUBLE_1_DOUBLE_0,"Double1Double0");
        _m.addEntry(NUMBER_0_TO_STRING_METHOD_0,"Number0ToStringMethod0");
        _m.addEntry(NUMBER_0_EQUALS_0,"Number0Equals0");
        _m.addEntry(NUMBER_1_EQUALS_0,"Number1Equals0");
        _m.addEntry(NUMBER_1_EQUALS_1,"Number1Equals1");
        _m.addEntry(NUMBER_0_COMPARE_TO_0,"Number0CompareTo0");
        _m.addEntry(NUMBER_0_COMPARE_0,"Number0Compare0");
        _m.addEntry(NUMBER_0_COMPARE_1,"Number0Compare1");
        _m.addEntry(CHARACTER_0_COMPARE_TO_0,"Character0CompareTo0");
        _m.addEntry(CHARACTER_0_COMPARE_0,"Character0Compare0");
        _m.addEntry(CHARACTER_0_COMPARE_1,"Character0Compare1");
        _m.addEntry(CHARACTER_0_DIGIT_0,"Character0Digit0");
        _m.addEntry(CHARACTER_0_DIGIT_1,"Character0Digit1");
        _m.addEntry(CHARACTER_1_DIGIT_0,"Character1Digit0");
        _m.addEntry(CHARACTER_1_DIGIT_1,"Character1Digit1");
        _m.addEntry(CHARACTER_1_DIGIT_2,"Character1Digit2");
        _m.addEntry(CHARACTER_0_FOR_DIGIT_0,"Character0ForDigit0");
        _m.addEntry(CHARACTER_0_FOR_DIGIT_1,"Character0ForDigit1");
        _m.addEntry(CHARACTER_1_FOR_DIGIT_0,"Character1ForDigit0");
        _m.addEntry(CHARACTER_1_FOR_DIGIT_1,"Character1ForDigit1");
        _m.addEntry(CHARACTER_1_FOR_DIGIT_2,"Character1ForDigit2");
        _m.addEntry(CHARACTER_0_GET_DIRECTIONALITY_0,"Character0GetDirectionality0");
        _m.addEntry(CHARACTER_0_GET_TYPE_0,"Character0GetType0");
        _m.addEntry(CHARACTER_0_IS_DIGIT_0,"Character0IsDigit0");
        _m.addEntry(CHARACTER_0_IS_LETTER_0,"Character0IsLetter0");
        _m.addEntry(CHARACTER_0_IS_LETTER_OR_DIGIT_0,"Character0IsLetterOrDigit0");
        _m.addEntry(CHARACTER_0_IS_WORD_CHAR_0,"Character0IsWordChar0");
        _m.addEntry(CHARACTER_0_IS_WHITESPACE_0,"Character0IsWhitespace0");
        _m.addEntry(CHARACTER_0_IS_LOWER_CASE_0,"Character0IsLowerCase0");
        _m.addEntry(CHARACTER_0_IS_UPPER_CASE_0,"Character0IsUpperCase0");
        _m.addEntry(CHARACTER_0_IS_SPACE_0,"Character0IsSpace0");
        _m.addEntry(CHARACTER_0_TO_LOWER_CASE_CHAR_0,"Character0ToLowerCaseChar0");
        _m.addEntry(CHARACTER_0_TO_UPPER_CASE_CHAR_0,"Character0ToUpperCaseChar0");
        _m.addEntry(CHARACTER_0_TO_STRING_METHOD_0,"Character0ToStringMethod0");
        _m.addEntry(CHARACTER_0_CHARACTER_0,"Character0Character0");
    }
    public CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(StringMap<String> _mapping) {
        CustList<CustList<KeyValueMemberName>> map_ = new CustList<CustList<KeyValueMemberName>>();
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BOOLEAN_0_COMPARE_0), getAliasBoolean0Compare0()),new KeyValueMemberName(_mapping.getVal(BOOLEAN_0_COMPARE_1), getAliasBoolean0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BOOLEAN_0_COMPARE_TO_0), getAliasBoolean0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BOOLEAN_0_EQUALS_0), getAliasBoolean0Equals0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BOOLEAN_0_PARSE_BOOLEAN_0), getAliasBoolean0ParseBoolean0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BOOLEAN_0_TO_STRING_METHOD_0), getAliasBoolean0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BOOLEAN_0_VALUE_OF_METHOD_0), getAliasBoolean0ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BOOLEAN_1_VALUE_OF_METHOD_0), getAliasBoolean1ValueOfMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BOOLEAN_0_BOOLEAN_0), getAliasBoolean0Boolean0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BOOLEAN_1_BOOLEAN_0), getAliasBoolean1Boolean0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BYTE_0_TO_STRING_METHOD_0), getAliasByte0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BYTE_0_PARSE_BYTE_0), getAliasByte0ParseByte0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BYTE_1_PARSE_BYTE_0), getAliasByte1ParseByte0()),new KeyValueMemberName(_mapping.getVal(BYTE_1_PARSE_BYTE_1), getAliasByte1ParseByte1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BYTE_2_PARSE_BYTE_0), getAliasByte2ParseByte0()),new KeyValueMemberName(_mapping.getVal(BYTE_2_PARSE_BYTE_1), getAliasByte2ParseByte1()),new KeyValueMemberName(_mapping.getVal(BYTE_2_PARSE_BYTE_2), getAliasByte2ParseByte2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BYTE_0_COMPARE_TO_0), getAliasByte0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BYTE_0_COMPARE_0), getAliasByte0Compare0()),new KeyValueMemberName(_mapping.getVal(BYTE_0_COMPARE_1), getAliasByte0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BYTE_0_PARSE_BYTE_OR_NULL_0), getAliasByte0ParseByteOrNull0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BYTE_1_PARSE_BYTE_OR_NULL_0), getAliasByte1ParseByteOrNull0()),new KeyValueMemberName(_mapping.getVal(BYTE_1_PARSE_BYTE_OR_NULL_1), getAliasByte1ParseByteOrNull1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BYTE_2_PARSE_BYTE_OR_NULL_0), getAliasByte2ParseByteOrNull0()),new KeyValueMemberName(_mapping.getVal(BYTE_2_PARSE_BYTE_OR_NULL_1), getAliasByte2ParseByteOrNull1()),new KeyValueMemberName(_mapping.getVal(BYTE_2_PARSE_BYTE_OR_NULL_2), getAliasByte2ParseByteOrNull2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BYTE_0_TO_BIN_STRING_0), getAliasByte0ToBinString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BYTE_0_TO_OCT_STRING_0), getAliasByte0ToOctString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BYTE_0_TO_HEX_STRING_0), getAliasByte0ToHexString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BYTE_1_TO_HEX_STRING_0), getAliasByte1ToHexString0()),new KeyValueMemberName(_mapping.getVal(BYTE_1_TO_HEX_STRING_1), getAliasByte1ToHexString1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BYTE_0_BYTE_0), getAliasByte0Byte0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(BYTE_1_BYTE_0), getAliasByte1Byte0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SHORT_0_TO_STRING_METHOD_0), getAliasShort0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SHORT_0_PARSE_SHORT_0), getAliasShort0ParseShort0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SHORT_1_PARSE_SHORT_0), getAliasShort1ParseShort0()),new KeyValueMemberName(_mapping.getVal(SHORT_1_PARSE_SHORT_1), getAliasShort1ParseShort1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SHORT_2_PARSE_SHORT_0), getAliasShort2ParseShort0()),new KeyValueMemberName(_mapping.getVal(SHORT_2_PARSE_SHORT_1), getAliasShort2ParseShort1()),new KeyValueMemberName(_mapping.getVal(SHORT_2_PARSE_SHORT_2), getAliasShort2ParseShort2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SHORT_0_COMPARE_TO_0), getAliasShort0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SHORT_0_COMPARE_0), getAliasShort0Compare0()),new KeyValueMemberName(_mapping.getVal(SHORT_0_COMPARE_1), getAliasShort0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SHORT_0_PARSE_SHORT_OR_NULL_0), getAliasShort0ParseShortOrNull0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SHORT_1_PARSE_SHORT_OR_NULL_0), getAliasShort1ParseShortOrNull0()),new KeyValueMemberName(_mapping.getVal(SHORT_1_PARSE_SHORT_OR_NULL_1), getAliasShort1ParseShortOrNull1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SHORT_2_PARSE_SHORT_OR_NULL_0), getAliasShort2ParseShortOrNull0()),new KeyValueMemberName(_mapping.getVal(SHORT_2_PARSE_SHORT_OR_NULL_1), getAliasShort2ParseShortOrNull1()),new KeyValueMemberName(_mapping.getVal(SHORT_2_PARSE_SHORT_OR_NULL_2), getAliasShort2ParseShortOrNull2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SHORT_0_TO_BIN_STRING_0), getAliasShort0ToBinString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SHORT_0_TO_OCT_STRING_0), getAliasShort0ToOctString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SHORT_0_TO_HEX_STRING_0), getAliasShort0ToHexString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SHORT_1_TO_HEX_STRING_0), getAliasShort1ToHexString0()),new KeyValueMemberName(_mapping.getVal(SHORT_1_TO_HEX_STRING_1), getAliasShort1ToHexString1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SHORT_0_SHORT_0), getAliasShort0Short0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(SHORT_1_SHORT_0), getAliasShort1Short0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(INTEGER_0_TO_STRING_METHOD_0), getAliasInteger0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(INTEGER_0_PARSE_INT_0), getAliasInteger0ParseInt0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(INTEGER_1_PARSE_INT_0), getAliasInteger1ParseInt0()),new KeyValueMemberName(_mapping.getVal(INTEGER_1_PARSE_INT_1), getAliasInteger1ParseInt1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(INTEGER_2_PARSE_INT_0), getAliasInteger2ParseInt0()),new KeyValueMemberName(_mapping.getVal(INTEGER_2_PARSE_INT_1), getAliasInteger2ParseInt1()),new KeyValueMemberName(_mapping.getVal(INTEGER_2_PARSE_INT_2), getAliasInteger2ParseInt2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(INTEGER_0_COMPARE_TO_0), getAliasInteger0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(INTEGER_0_COMPARE_0), getAliasInteger0Compare0()),new KeyValueMemberName(_mapping.getVal(INTEGER_0_COMPARE_1), getAliasInteger0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(INTEGER_0_PARSE_INT_OR_NULL_0), getAliasInteger0ParseIntOrNull0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(INTEGER_1_PARSE_INT_OR_NULL_0), getAliasInteger1ParseIntOrNull0()),new KeyValueMemberName(_mapping.getVal(INTEGER_1_PARSE_INT_OR_NULL_1), getAliasInteger1ParseIntOrNull1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(INTEGER_2_PARSE_INT_OR_NULL_0), getAliasInteger2ParseIntOrNull0()),new KeyValueMemberName(_mapping.getVal(INTEGER_2_PARSE_INT_OR_NULL_1), getAliasInteger2ParseIntOrNull1()),new KeyValueMemberName(_mapping.getVal(INTEGER_2_PARSE_INT_OR_NULL_2), getAliasInteger2ParseIntOrNull2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(INTEGER_0_TO_BIN_STRING_0), getAliasInteger0ToBinString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(INTEGER_0_TO_OCT_STRING_0), getAliasInteger0ToOctString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(INTEGER_0_TO_HEX_STRING_0), getAliasInteger0ToHexString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(INTEGER_1_TO_HEX_STRING_0), getAliasInteger1ToHexString0()),new KeyValueMemberName(_mapping.getVal(INTEGER_1_TO_HEX_STRING_1), getAliasInteger1ToHexString1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(INTEGER_0_INTEGER_0), getAliasInteger0Integer0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(INTEGER_1_INTEGER_0), getAliasInteger1Integer0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LONG_0_TO_STRING_METHOD_0), getAliasLong0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LONG_1_TO_STRING_METHOD_0), getAliasLong1ToStringMethod0()),new KeyValueMemberName(_mapping.getVal(LONG_1_TO_STRING_METHOD_1), getAliasLong1ToStringMethod1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LONG_2_TO_STRING_METHOD_0), getAliasLong2ToStringMethod0()),new KeyValueMemberName(_mapping.getVal(LONG_2_TO_STRING_METHOD_1), getAliasLong2ToStringMethod1()),new KeyValueMemberName(_mapping.getVal(LONG_2_TO_STRING_METHOD_2), getAliasLong2ToStringMethod2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LONG_0_SIGNUM_0), getAliasLong0Signum0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(DOUBLE_0_SIGNUM_0), getAliasDouble0Signum0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LONG_0_PARSE_LONG_0), getAliasLong0ParseLong0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LONG_1_PARSE_LONG_0), getAliasLong1ParseLong0()),new KeyValueMemberName(_mapping.getVal(LONG_1_PARSE_LONG_1), getAliasLong1ParseLong1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LONG_2_PARSE_LONG_0), getAliasLong2ParseLong0()),new KeyValueMemberName(_mapping.getVal(LONG_2_PARSE_LONG_1), getAliasLong2ParseLong1()),new KeyValueMemberName(_mapping.getVal(LONG_2_PARSE_LONG_2), getAliasLong2ParseLong2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LONG_0_COMPARE_TO_0), getAliasLong0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LONG_0_COMPARE_0), getAliasLong0Compare0()),new KeyValueMemberName(_mapping.getVal(LONG_0_COMPARE_1), getAliasLong0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LONG_0_PARSE_LONG_OR_NULL_0), getAliasLong0ParseLongOrNull0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LONG_1_PARSE_LONG_OR_NULL_0), getAliasLong1ParseLongOrNull0()),new KeyValueMemberName(_mapping.getVal(LONG_1_PARSE_LONG_OR_NULL_1), getAliasLong1ParseLongOrNull1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LONG_2_PARSE_LONG_OR_NULL_0), getAliasLong2ParseLongOrNull0()),new KeyValueMemberName(_mapping.getVal(LONG_2_PARSE_LONG_OR_NULL_1), getAliasLong2ParseLongOrNull1()),new KeyValueMemberName(_mapping.getVal(LONG_2_PARSE_LONG_OR_NULL_2), getAliasLong2ParseLongOrNull2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LONG_0_TO_BIN_STRING_0), getAliasLong0ToBinString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LONG_0_TO_OCT_STRING_0), getAliasLong0ToOctString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LONG_0_TO_HEX_STRING_0), getAliasLong0ToHexString0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LONG_1_TO_HEX_STRING_0), getAliasLong1ToHexString0()),new KeyValueMemberName(_mapping.getVal(LONG_1_TO_HEX_STRING_1), getAliasLong1ToHexString1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LONG_0_LONG_0), getAliasLong0Long0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(LONG_1_LONG_0), getAliasLong1Long0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(FLOAT_0_TO_STRING_METHOD_0), getAliasFloat0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(FLOAT_0_PARSE_FLOAT_0), getAliasFloat0ParseFloat0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(FLOAT_0_COMPARE_TO_0), getAliasFloat0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(FLOAT_0_COMPARE_0), getAliasFloat0Compare0()),new KeyValueMemberName(_mapping.getVal(FLOAT_0_COMPARE_1), getAliasFloat0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(FLOAT_0_PARSE_FLOAT_OR_NULL_0), getAliasFloat0ParseFloatOrNull0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(FLOAT_0_IS_INFINITE_0), getAliasFloat0IsInfinite0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(FLOAT_0_IS_NAN_0), getAliasFloat0IsNan0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(FLOAT_0_FLOAT_0), getAliasFloat0Float0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(FLOAT_1_FLOAT_0), getAliasFloat1Float0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(DOUBLE_0_TO_STRING_METHOD_0), getAliasDouble0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(DOUBLE_0_PARSE_DOUBLE_0), getAliasDouble0ParseDouble0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(DOUBLE_0_COMPARE_TO_0), getAliasDouble0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(DOUBLE_0_COMPARE_0), getAliasDouble0Compare0()),new KeyValueMemberName(_mapping.getVal(DOUBLE_0_COMPARE_1), getAliasDouble0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(DOUBLE_0_PARSE_DOUBLE_OR_NULL_0), getAliasDouble0ParseDoubleOrNull0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(DOUBLE_0_IS_INFINITE_0), getAliasDouble0IsInfinite0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(DOUBLE_0_IS_NAN_0), getAliasDouble0IsNan0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(DOUBLE_0_DOUBLE_0), getAliasDouble0Double0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(DOUBLE_1_DOUBLE_0), getAliasDouble1Double0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(NUMBER_0_TO_STRING_METHOD_0), getAliasNumber0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(NUMBER_0_EQUALS_0), getAliasNumber0Equals0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(NUMBER_1_EQUALS_0), getAliasNumber1Equals0()),new KeyValueMemberName(_mapping.getVal(NUMBER_1_EQUALS_1), getAliasNumber1Equals1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(NUMBER_0_COMPARE_TO_0), getAliasNumber0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(NUMBER_0_COMPARE_0), getAliasNumber0Compare0()),new KeyValueMemberName(_mapping.getVal(NUMBER_0_COMPARE_1), getAliasNumber0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_0_COMPARE_TO_0), getAliasCharacter0CompareTo0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_0_COMPARE_0), getAliasCharacter0Compare0()),new KeyValueMemberName(_mapping.getVal(CHARACTER_0_COMPARE_1), getAliasCharacter0Compare1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_0_DIGIT_0), getAliasCharacter0Digit0()),new KeyValueMemberName(_mapping.getVal(CHARACTER_0_DIGIT_1), getAliasCharacter0Digit1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_1_DIGIT_1), getAliasCharacter1Digit0()),new KeyValueMemberName(_mapping.getVal(CHARACTER_1_DIGIT_1), getAliasCharacter1Digit1()),new KeyValueMemberName(_mapping.getVal(CHARACTER_1_DIGIT_2), getAliasCharacter1Digit2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_0_FOR_DIGIT_0), getAliasCharacter0ForDigit0()),new KeyValueMemberName(_mapping.getVal(CHARACTER_0_FOR_DIGIT_1), getAliasCharacter0ForDigit1())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_1_FOR_DIGIT_0), getAliasCharacter1ForDigit0()),new KeyValueMemberName(_mapping.getVal(CHARACTER_1_FOR_DIGIT_1), getAliasCharacter1ForDigit1()),new KeyValueMemberName(_mapping.getVal(CHARACTER_1_FOR_DIGIT_2), getAliasCharacter1ForDigit2())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_0_GET_DIRECTIONALITY_0), getAliasCharacter0GetDirectionality0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_0_GET_TYPE_0), getAliasCharacter0GetType0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_0_IS_DIGIT_0), getAliasCharacter0IsDigit0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_0_IS_LETTER_0), getAliasCharacter0IsLetter0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_0_IS_LETTER_OR_DIGIT_0), getAliasCharacter0IsLetterOrDigit0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_0_IS_WORD_CHAR_0), getAliasCharacter0IsWordChar0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_0_IS_WHITESPACE_0), getAliasCharacter0IsWhitespace0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_0_IS_LOWER_CASE_0), getAliasCharacter0IsLowerCase0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_0_IS_UPPER_CASE_0), getAliasCharacter0IsUpperCase0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_0_IS_SPACE_0), getAliasCharacter0IsSpace0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_0_TO_LOWER_CASE_CHAR_0), getAliasCharacter0ToLowerCaseChar0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_0_TO_UPPER_CASE_CHAR_0), getAliasCharacter0ToUpperCaseChar0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_0_TO_STRING_METHOD_0), getAliasCharacter0ToStringMethod0())));
        map_.add(new CustList<KeyValueMemberName>(new KeyValueMemberName(_mapping.getVal(CHARACTER_0_CHARACTER_0), getAliasCharacter0Character0())));
        return map_;
    }
    public String getAliasBoolean0Compare0() {
        return aliasBoolean0Compare0;
    }

    public void setAliasBoolean0Compare0(String _v) {
        this.aliasBoolean0Compare0 =_v;
    }

    public String getAliasBoolean0Compare1() {
        return aliasBoolean0Compare1;
    }

    public void setAliasBoolean0Compare1(String _v) {
        this.aliasBoolean0Compare1 =_v;
    }

    public String getAliasBoolean0CompareTo0() {
        return aliasBoolean0CompareTo0;
    }

    public void setAliasBoolean0CompareTo0(String _v) {
        this.aliasBoolean0CompareTo0 =_v;
    }

    public String getAliasBoolean0Equals0() {
        return aliasBoolean0Equals0;
    }

    public void setAliasBoolean0Equals0(String _v) {
        this.aliasBoolean0Equals0 =_v;
    }

    public String getAliasBoolean0ParseBoolean0() {
        return aliasBoolean0ParseBoolean0;
    }

    public void setAliasBoolean0ParseBoolean0(String _v) {
        this.aliasBoolean0ParseBoolean0 =_v;
    }

    public String getAliasBoolean0ToStringMethod0() {
        return aliasBoolean0ToStringMethod0;
    }

    public void setAliasBoolean0ToStringMethod0(String _v) {
        this.aliasBoolean0ToStringMethod0 =_v;
    }

    public String getAliasBoolean0ValueOfMethod0() {
        return aliasBoolean0ValueOfMethod0;
    }

    public void setAliasBoolean0ValueOfMethod0(String _v) {
        this.aliasBoolean0ValueOfMethod0 =_v;
    }

    public String getAliasBoolean1ValueOfMethod0() {
        return aliasBoolean1ValueOfMethod0;
    }

    public void setAliasBoolean1ValueOfMethod0(String _v) {
        this.aliasBoolean1ValueOfMethod0 =_v;
    }

    public String getAliasBoolean0Boolean0() {
        return aliasBoolean0Boolean0;
    }

    public void setAliasBoolean0Boolean0(String _v) {
        this.aliasBoolean0Boolean0 =_v;
    }

    public String getAliasBoolean1Boolean0() {
        return aliasBoolean1Boolean0;
    }

    public void setAliasBoolean1Boolean0(String _v) {
        this.aliasBoolean1Boolean0 =_v;
    }

    public String getAliasByte0ToStringMethod0() {
        return aliasByte0ToStringMethod0;
    }

    public void setAliasByte0ToStringMethod0(String _v) {
        this.aliasByte0ToStringMethod0 =_v;
    }

    public String getAliasByte0ParseByte0() {
        return aliasByte0ParseByte0;
    }

    public void setAliasByte0ParseByte0(String _v) {
        this.aliasByte0ParseByte0 =_v;
    }

    public String getAliasByte1ParseByte0() {
        return aliasByte1ParseByte0;
    }

    public void setAliasByte1ParseByte0(String _v) {
        this.aliasByte1ParseByte0 =_v;
    }

    public String getAliasByte1ParseByte1() {
        return aliasByte1ParseByte1;
    }

    public void setAliasByte1ParseByte1(String _v) {
        this.aliasByte1ParseByte1 =_v;
    }

    public String getAliasByte2ParseByte0() {
        return aliasByte2ParseByte0;
    }

    public void setAliasByte2ParseByte0(String _v) {
        this.aliasByte2ParseByte0 = _v;
    }

    public String getAliasByte2ParseByte1() {
        return aliasByte2ParseByte1;
    }

    public void setAliasByte2ParseByte1(String _v) {
        this.aliasByte2ParseByte1 = _v;
    }

    public String getAliasByte2ParseByte2() {
        return aliasByte2ParseByte2;
    }

    public void setAliasByte2ParseByte2(String _v) {
        this.aliasByte2ParseByte2 = _v;
    }

    public String getAliasByte0CompareTo0() {
        return aliasByte0CompareTo0;
    }

    public void setAliasByte0CompareTo0(String _v) {
        this.aliasByte0CompareTo0 =_v;
    }

    public String getAliasByte0Compare0() {
        return aliasByte0Compare0;
    }

    public void setAliasByte0Compare0(String _v) {
        this.aliasByte0Compare0 =_v;
    }

    public String getAliasByte0Compare1() {
        return aliasByte0Compare1;
    }

    public void setAliasByte0Compare1(String _v) {
        this.aliasByte0Compare1 =_v;
    }

    public String getAliasByte0ParseByteOrNull0() {
        return aliasByte0ParseByteOrNull0;
    }

    public void setAliasByte0ParseByteOrNull0(String _v) {
        this.aliasByte0ParseByteOrNull0 =_v;
    }

    public String getAliasByte1ParseByteOrNull0() {
        return aliasByte1ParseByteOrNull0;
    }

    public void setAliasByte1ParseByteOrNull0(String _v) {
        this.aliasByte1ParseByteOrNull0 =_v;
    }

    public String getAliasByte1ParseByteOrNull1() {
        return aliasByte1ParseByteOrNull1;
    }

    public void setAliasByte1ParseByteOrNull1(String _v) {
        this.aliasByte1ParseByteOrNull1 =_v;
    }

    public String getAliasByte2ParseByteOrNull0() {
        return aliasByte2ParseByteOrNull0;
    }

    public void setAliasByte2ParseByteOrNull0(String _v) {
        this.aliasByte2ParseByteOrNull0 = _v;
    }

    public String getAliasByte2ParseByteOrNull1() {
        return aliasByte2ParseByteOrNull1;
    }

    public void setAliasByte2ParseByteOrNull1(String _v) {
        this.aliasByte2ParseByteOrNull1 = _v;
    }

    public String getAliasByte2ParseByteOrNull2() {
        return aliasByte2ParseByteOrNull2;
    }

    public void setAliasByte2ParseByteOrNull2(String _v) {
        this.aliasByte2ParseByteOrNull2 = _v;
    }

    public String getAliasByte0ToBinString0() {
        return aliasByte0ToBinString0;
    }

    public void setAliasByte0ToBinString0(String _v) {
        this.aliasByte0ToBinString0 = _v;
    }

    public String getAliasByte0ToOctString0() {
        return aliasByte0ToOctString0;
    }

    public void setAliasByte0ToOctString0(String _v) {
        this.aliasByte0ToOctString0 = _v;
    }

    public String getAliasByte0ToHexString0() {
        return aliasByte0ToHexString0;
    }

    public void setAliasByte0ToHexString0(String _v) {
        this.aliasByte0ToHexString0 = _v;
    }

    public String getAliasByte1ToHexString0() {
        return aliasByte1ToHexString0;
    }

    public void setAliasByte1ToHexString0(String _v) {
        this.aliasByte1ToHexString0 = _v;
    }

    public String getAliasByte1ToHexString1() {
        return aliasByte1ToHexString1;
    }

    public void setAliasByte1ToHexString1(String _v) {
        this.aliasByte1ToHexString1 = _v;
    }

    public String getAliasByte0Byte0() {
        return aliasByte0Byte0;
    }

    public void setAliasByte0Byte0(String _v) {
        this.aliasByte0Byte0 =_v;
    }

    public String getAliasByte1Byte0() {
        return aliasByte1Byte0;
    }

    public void setAliasByte1Byte0(String _v) {
        this.aliasByte1Byte0 =_v;
    }

    public String getAliasShort0ToStringMethod0() {
        return aliasShort0ToStringMethod0;
    }

    public void setAliasShort0ToStringMethod0(String _v) {
        this.aliasShort0ToStringMethod0 =_v;
    }

    public String getAliasShort0ParseShort0() {
        return aliasShort0ParseShort0;
    }

    public void setAliasShort0ParseShort0(String _v) {
        this.aliasShort0ParseShort0 =_v;
    }

    public String getAliasShort1ParseShort0() {
        return aliasShort1ParseShort0;
    }

    public void setAliasShort1ParseShort0(String _v) {
        this.aliasShort1ParseShort0 =_v;
    }

    public String getAliasShort1ParseShort1() {
        return aliasShort1ParseShort1;
    }

    public void setAliasShort1ParseShort1(String _v) {
        this.aliasShort1ParseShort1 =_v;
    }

    public String getAliasShort2ParseShort0() {
        return aliasShort2ParseShort0;
    }

    public void setAliasShort2ParseShort0(String _v) {
        this.aliasShort2ParseShort0 = _v;
    }

    public String getAliasShort2ParseShort1() {
        return aliasShort2ParseShort1;
    }

    public void setAliasShort2ParseShort1(String _v) {
        this.aliasShort2ParseShort1 = _v;
    }

    public String getAliasShort2ParseShort2() {
        return aliasShort2ParseShort2;
    }

    public void setAliasShort2ParseShort2(String _v) {
        this.aliasShort2ParseShort2 = _v;
    }

    public String getAliasShort0CompareTo0() {
        return aliasShort0CompareTo0;
    }

    public void setAliasShort0CompareTo0(String _v) {
        this.aliasShort0CompareTo0 =_v;
    }

    public String getAliasShort0Compare0() {
        return aliasShort0Compare0;
    }

    public void setAliasShort0Compare0(String _v) {
        this.aliasShort0Compare0 =_v;
    }

    public String getAliasShort0Compare1() {
        return aliasShort0Compare1;
    }

    public void setAliasShort0Compare1(String _v) {
        this.aliasShort0Compare1 =_v;
    }

    public String getAliasShort0ParseShortOrNull0() {
        return aliasShort0ParseShortOrNull0;
    }

    public void setAliasShort0ParseShortOrNull0(String _v) {
        this.aliasShort0ParseShortOrNull0 =_v;
    }

    public String getAliasShort1ParseShortOrNull0() {
        return aliasShort1ParseShortOrNull0;
    }

    public void setAliasShort1ParseShortOrNull0(String _v) {
        this.aliasShort1ParseShortOrNull0 =_v;
    }

    public String getAliasShort1ParseShortOrNull1() {
        return aliasShort1ParseShortOrNull1;
    }

    public void setAliasShort1ParseShortOrNull1(String _v) {
        this.aliasShort1ParseShortOrNull1 =_v;
    }

    public String getAliasShort2ParseShortOrNull0() {
        return aliasShort2ParseShortOrNull0;
    }

    public void setAliasShort2ParseShortOrNull0(String _v) {
        this.aliasShort2ParseShortOrNull0 = _v;
    }

    public String getAliasShort2ParseShortOrNull1() {
        return aliasShort2ParseShortOrNull1;
    }

    public void setAliasShort2ParseShortOrNull1(String _v) {
        this.aliasShort2ParseShortOrNull1 = _v;
    }

    public String getAliasShort2ParseShortOrNull2() {
        return aliasShort2ParseShortOrNull2;
    }

    public void setAliasShort2ParseShortOrNull2(String _v) {
        this.aliasShort2ParseShortOrNull2 = _v;
    }

    public String getAliasShort0ToBinString0() {
        return aliasShort0ToBinString0;
    }

    public void setAliasShort0ToBinString0(String _v) {
        this.aliasShort0ToBinString0 = _v;
    }

    public String getAliasShort0ToOctString0() {
        return aliasShort0ToOctString0;
    }

    public void setAliasShort0ToOctString0(String _v) {
        this.aliasShort0ToOctString0 = _v;
    }

    public String getAliasShort0ToHexString0() {
        return aliasShort0ToHexString0;
    }

    public void setAliasShort0ToHexString0(String _v) {
        this.aliasShort0ToHexString0 = _v;
    }

    public String getAliasShort1ToHexString0() {
        return aliasShort1ToHexString0;
    }

    public void setAliasShort1ToHexString0(String _v) {
        this.aliasShort1ToHexString0 = _v;
    }

    public String getAliasShort1ToHexString1() {
        return aliasShort1ToHexString1;
    }

    public void setAliasShort1ToHexString1(String _v) {
        this.aliasShort1ToHexString1 = _v;
    }

    public String getAliasShort0Short0() {
        return aliasShort0Short0;
    }

    public void setAliasShort0Short0(String _v) {
        this.aliasShort0Short0 =_v;
    }

    public String getAliasShort1Short0() {
        return aliasShort1Short0;
    }

    public void setAliasShort1Short0(String _v) {
        this.aliasShort1Short0 =_v;
    }

    public String getAliasInteger0ToStringMethod0() {
        return aliasInteger0ToStringMethod0;
    }

    public void setAliasInteger0ToStringMethod0(String _v) {
        this.aliasInteger0ToStringMethod0 =_v;
    }

    public String getAliasInteger0ParseInt0() {
        return aliasInteger0ParseInt0;
    }

    public void setAliasInteger0ParseInt0(String _v) {
        this.aliasInteger0ParseInt0 =_v;
    }

    public String getAliasInteger1ParseInt0() {
        return aliasInteger1ParseInt0;
    }

    public void setAliasInteger1ParseInt0(String _v) {
        this.aliasInteger1ParseInt0 =_v;
    }

    public String getAliasInteger1ParseInt1() {
        return aliasInteger1ParseInt1;
    }

    public void setAliasInteger1ParseInt1(String _v) {
        this.aliasInteger1ParseInt1 =_v;
    }

    public String getAliasInteger2ParseInt0() {
        return aliasInteger2ParseInt0;
    }

    public void setAliasInteger2ParseInt0(String _v) {
        this.aliasInteger2ParseInt0 = _v;
    }

    public String getAliasInteger2ParseInt1() {
        return aliasInteger2ParseInt1;
    }

    public void setAliasInteger2ParseInt1(String _v) {
        this.aliasInteger2ParseInt1 = _v;
    }

    public String getAliasInteger2ParseInt2() {
        return aliasInteger2ParseInt2;
    }

    public void setAliasInteger2ParseInt2(String _v) {
        this.aliasInteger2ParseInt2 = _v;
    }

    public String getAliasInteger0CompareTo0() {
        return aliasInteger0CompareTo0;
    }

    public void setAliasInteger0CompareTo0(String _v) {
        this.aliasInteger0CompareTo0 =_v;
    }

    public String getAliasInteger0Compare0() {
        return aliasInteger0Compare0;
    }

    public void setAliasInteger0Compare0(String _v) {
        this.aliasInteger0Compare0 =_v;
    }

    public String getAliasInteger0Compare1() {
        return aliasInteger0Compare1;
    }

    public void setAliasInteger0Compare1(String _v) {
        this.aliasInteger0Compare1 =_v;
    }

    public String getAliasInteger0ParseIntOrNull0() {
        return aliasInteger0ParseIntOrNull0;
    }

    public void setAliasInteger0ParseIntOrNull0(String _v) {
        this.aliasInteger0ParseIntOrNull0 =_v;
    }

    public String getAliasInteger1ParseIntOrNull0() {
        return aliasInteger1ParseIntOrNull0;
    }

    public void setAliasInteger1ParseIntOrNull0(String _v) {
        this.aliasInteger1ParseIntOrNull0 =_v;
    }

    public String getAliasInteger1ParseIntOrNull1() {
        return aliasInteger1ParseIntOrNull1;
    }

    public void setAliasInteger1ParseIntOrNull1(String _v) {
        this.aliasInteger1ParseIntOrNull1 =_v;
    }

    public String getAliasInteger2ParseIntOrNull0() {
        return aliasInteger2ParseIntOrNull0;
    }

    public void setAliasInteger2ParseIntOrNull0(String _v) {
        this.aliasInteger2ParseIntOrNull0 = _v;
    }

    public String getAliasInteger2ParseIntOrNull1() {
        return aliasInteger2ParseIntOrNull1;
    }

    public void setAliasInteger2ParseIntOrNull1(String _v) {
        this.aliasInteger2ParseIntOrNull1 = _v;
    }

    public String getAliasInteger2ParseIntOrNull2() {
        return aliasInteger2ParseIntOrNull2;
    }

    public void setAliasInteger2ParseIntOrNull2(String _v) {
        this.aliasInteger2ParseIntOrNull2 = _v;
    }

    public String getAliasInteger0ToBinString0() {
        return aliasInteger0ToBinString0;
    }

    public void setAliasInteger0ToBinString0(String _v) {
        this.aliasInteger0ToBinString0 = _v;
    }

    public String getAliasInteger0ToOctString0() {
        return aliasInteger0ToOctString0;
    }

    public void setAliasInteger0ToOctString0(String _v) {
        this.aliasInteger0ToOctString0 = _v;
    }

    public String getAliasInteger0ToHexString0() {
        return aliasInteger0ToHexString0;
    }

    public void setAliasInteger0ToHexString0(String _v) {
        this.aliasInteger0ToHexString0 = _v;
    }

    public String getAliasInteger1ToHexString0() {
        return aliasInteger1ToHexString0;
    }

    public void setAliasInteger1ToHexString0(String _v) {
        this.aliasInteger1ToHexString0 = _v;
    }

    public String getAliasInteger1ToHexString1() {
        return aliasInteger1ToHexString1;
    }

    public void setAliasInteger1ToHexString1(String _v) {
        this.aliasInteger1ToHexString1 = _v;
    }

    public String getAliasInteger0Integer0() {
        return aliasInteger0Integer0;
    }

    public void setAliasInteger0Integer0(String _v) {
        this.aliasInteger0Integer0 =_v;
    }

    public String getAliasInteger1Integer0() {
        return aliasInteger1Integer0;
    }

    public void setAliasInteger1Integer0(String _v) {
        this.aliasInteger1Integer0 =_v;
    }

    public String getAliasLong0ToStringMethod0() {
        return aliasLong0ToStringMethod0;
    }

    public void setAliasLong0ToStringMethod0(String _v) {
        this.aliasLong0ToStringMethod0 =_v;
    }

    public String getAliasLong1ToStringMethod0() {
        return aliasLong1ToStringMethod0;
    }

    public void setAliasLong1ToStringMethod0(String _v) {
        this.aliasLong1ToStringMethod0 =_v;
    }

    public String getAliasLong1ToStringMethod1() {
        return aliasLong1ToStringMethod1;
    }

    public void setAliasLong1ToStringMethod1(String _v) {
        this.aliasLong1ToStringMethod1 =_v;
    }

    public String getAliasLong2ToStringMethod0() {
        return aliasLong2ToStringMethod0;
    }

    public void setAliasLong2ToStringMethod0(String _v) {
        this.aliasLong2ToStringMethod0 = _v;
    }

    public String getAliasLong2ToStringMethod1() {
        return aliasLong2ToStringMethod1;
    }

    public void setAliasLong2ToStringMethod1(String _v) {
        this.aliasLong2ToStringMethod1 = _v;
    }

    public String getAliasLong2ToStringMethod2() {
        return aliasLong2ToStringMethod2;
    }

    public void setAliasLong2ToStringMethod2(String _v) {
        this.aliasLong2ToStringMethod2 = _v;
    }

    public String getAliasLong0Signum0() {
        return aliasLong0Signum0;
    }

    public void setAliasLong0Signum0(String _v) {
        this.aliasLong0Signum0 = _v;
    }

    public String getAliasDouble0Signum0() {
        return aliasDouble0Signum0;
    }

    public void setAliasDouble0Signum0(String _v) {
        this.aliasDouble0Signum0 = _v;
    }

    public String getAliasLong0ParseLong0() {
        return aliasLong0ParseLong0;
    }

    public void setAliasLong0ParseLong0(String _v) {
        this.aliasLong0ParseLong0 =_v;
    }

    public String getAliasLong1ParseLong0() {
        return aliasLong1ParseLong0;
    }

    public void setAliasLong1ParseLong0(String _v) {
        this.aliasLong1ParseLong0 =_v;
    }

    public String getAliasLong1ParseLong1() {
        return aliasLong1ParseLong1;
    }

    public void setAliasLong1ParseLong1(String _v) {
        this.aliasLong1ParseLong1 =_v;
    }

    public String getAliasLong2ParseLong0() {
        return aliasLong2ParseLong0;
    }

    public void setAliasLong2ParseLong0(String _v) {
        this.aliasLong2ParseLong0 = _v;
    }

    public String getAliasLong2ParseLong1() {
        return aliasLong2ParseLong1;
    }

    public void setAliasLong2ParseLong1(String _v) {
        this.aliasLong2ParseLong1 = _v;
    }

    public String getAliasLong2ParseLong2() {
        return aliasLong2ParseLong2;
    }

    public void setAliasLong2ParseLong2(String _v) {
        this.aliasLong2ParseLong2 = _v;
    }

    public String getAliasLong0CompareTo0() {
        return aliasLong0CompareTo0;
    }

    public void setAliasLong0CompareTo0(String _v) {
        this.aliasLong0CompareTo0 =_v;
    }

    public String getAliasLong0Compare0() {
        return aliasLong0Compare0;
    }

    public void setAliasLong0Compare0(String _v) {
        this.aliasLong0Compare0 =_v;
    }

    public String getAliasLong0Compare1() {
        return aliasLong0Compare1;
    }

    public void setAliasLong0Compare1(String _v) {
        this.aliasLong0Compare1 =_v;
    }

    public String getAliasLong0ParseLongOrNull0() {
        return aliasLong0ParseLongOrNull0;
    }

    public void setAliasLong0ParseLongOrNull0(String _v) {
        this.aliasLong0ParseLongOrNull0 =_v;
    }

    public String getAliasLong1ParseLongOrNull0() {
        return aliasLong1ParseLongOrNull0;
    }

    public void setAliasLong1ParseLongOrNull0(String _v) {
        this.aliasLong1ParseLongOrNull0 =_v;
    }

    public String getAliasLong1ParseLongOrNull1() {
        return aliasLong1ParseLongOrNull1;
    }

    public void setAliasLong1ParseLongOrNull1(String _v) {
        this.aliasLong1ParseLongOrNull1 =_v;
    }

    public String getAliasLong2ParseLongOrNull0() {
        return aliasLong2ParseLongOrNull0;
    }

    public void setAliasLong2ParseLongOrNull0(String _v) {
        this.aliasLong2ParseLongOrNull0 = _v;
    }

    public String getAliasLong2ParseLongOrNull1() {
        return aliasLong2ParseLongOrNull1;
    }

    public void setAliasLong2ParseLongOrNull1(String _v) {
        this.aliasLong2ParseLongOrNull1 = _v;
    }

    public String getAliasLong2ParseLongOrNull2() {
        return aliasLong2ParseLongOrNull2;
    }

    public void setAliasLong2ParseLongOrNull2(String _v) {
        this.aliasLong2ParseLongOrNull2 = _v;
    }

    public String getAliasLong0ToBinString0() {
        return aliasLong0ToBinString0;
    }

    public void setAliasLong0ToBinString0(String _v) {
        this.aliasLong0ToBinString0 = _v;
    }

    public String getAliasLong0ToOctString0() {
        return aliasLong0ToOctString0;
    }

    public void setAliasLong0ToOctString0(String _v) {
        this.aliasLong0ToOctString0 = _v;
    }

    public String getAliasLong0ToHexString0() {
        return aliasLong0ToHexString0;
    }

    public void setAliasLong0ToHexString0(String _v) {
        this.aliasLong0ToHexString0 = _v;
    }

    public String getAliasLong1ToHexString0() {
        return aliasLong1ToHexString0;
    }

    public void setAliasLong1ToHexString0(String _v) {
        this.aliasLong1ToHexString0 = _v;
    }

    public String getAliasLong1ToHexString1() {
        return aliasLong1ToHexString1;
    }

    public void setAliasLong1ToHexString1(String _v) {
        this.aliasLong1ToHexString1 = _v;
    }

    public String getAliasLong0Long0() {
        return aliasLong0Long0;
    }

    public void setAliasLong0Long0(String _v) {
        this.aliasLong0Long0 =_v;
    }

    public String getAliasLong1Long0() {
        return aliasLong1Long0;
    }

    public void setAliasLong1Long0(String _v) {
        this.aliasLong1Long0 =_v;
    }

    public String getAliasFloat0ToStringMethod0() {
        return aliasFloat0ToStringMethod0;
    }

    public void setAliasFloat0ToStringMethod0(String _v) {
        this.aliasFloat0ToStringMethod0 =_v;
    }

    public String getAliasFloat0ParseFloat0() {
        return aliasFloat0ParseFloat0;
    }

    public void setAliasFloat0ParseFloat0(String _v) {
        this.aliasFloat0ParseFloat0 =_v;
    }

    public String getAliasFloat0CompareTo0() {
        return aliasFloat0CompareTo0;
    }

    public void setAliasFloat0CompareTo0(String _v) {
        this.aliasFloat0CompareTo0 =_v;
    }

    public String getAliasFloat0Compare0() {
        return aliasFloat0Compare0;
    }

    public void setAliasFloat0Compare0(String _v) {
        this.aliasFloat0Compare0 =_v;
    }

    public String getAliasFloat0Compare1() {
        return aliasFloat0Compare1;
    }

    public void setAliasFloat0Compare1(String _v) {
        this.aliasFloat0Compare1 =_v;
    }

    public String getAliasFloat0ParseFloatOrNull0() {
        return aliasFloat0ParseFloatOrNull0;
    }

    public void setAliasFloat0ParseFloatOrNull0(String _v) {
        this.aliasFloat0ParseFloatOrNull0 =_v;
    }

    public String getAliasFloat0IsInfinite0() {
        return aliasFloat0IsInfinite0;
    }

    public void setAliasFloat0IsInfinite0(String _v) {
        this.aliasFloat0IsInfinite0 =_v;
    }

    public String getAliasFloat0IsNan0() {
        return aliasFloat0IsNan0;
    }

    public void setAliasFloat0IsNan0(String _v) {
        this.aliasFloat0IsNan0 =_v;
    }

    public String getAliasFloat0Float0() {
        return aliasFloat0Float0;
    }

    public void setAliasFloat0Float0(String _v) {
        this.aliasFloat0Float0 =_v;
    }

    public String getAliasFloat1Float0() {
        return aliasFloat1Float0;
    }

    public void setAliasFloat1Float0(String _v) {
        this.aliasFloat1Float0 =_v;
    }

    public String getAliasDouble0ToStringMethod0() {
        return aliasDouble0ToStringMethod0;
    }

    public void setAliasDouble0ToStringMethod0(String _v) {
        this.aliasDouble0ToStringMethod0 =_v;
    }

    public String getAliasDouble0ParseDouble0() {
        return aliasDouble0ParseDouble0;
    }

    public void setAliasDouble0ParseDouble0(String _v) {
        this.aliasDouble0ParseDouble0 =_v;
    }

    public String getAliasDouble0CompareTo0() {
        return aliasDouble0CompareTo0;
    }

    public void setAliasDouble0CompareTo0(String _v) {
        this.aliasDouble0CompareTo0 =_v;
    }

    public String getAliasDouble0Compare0() {
        return aliasDouble0Compare0;
    }

    public void setAliasDouble0Compare0(String _v) {
        this.aliasDouble0Compare0 =_v;
    }

    public String getAliasDouble0Compare1() {
        return aliasDouble0Compare1;
    }

    public void setAliasDouble0Compare1(String _v) {
        this.aliasDouble0Compare1 =_v;
    }

    public String getAliasDouble0ParseDoubleOrNull0() {
        return aliasDouble0ParseDoubleOrNull0;
    }

    public void setAliasDouble0ParseDoubleOrNull0(String _v) {
        this.aliasDouble0ParseDoubleOrNull0 =_v;
    }

    public String getAliasDouble0IsInfinite0() {
        return aliasDouble0IsInfinite0;
    }

    public void setAliasDouble0IsInfinite0(String _v) {
        this.aliasDouble0IsInfinite0 =_v;
    }

    public String getAliasDouble0IsNan0() {
        return aliasDouble0IsNan0;
    }

    public void setAliasDouble0IsNan0(String _v) {
        this.aliasDouble0IsNan0 =_v;
    }

    public String getAliasDouble0Double0() {
        return aliasDouble0Double0;
    }

    public void setAliasDouble0Double0(String _v) {
        this.aliasDouble0Double0 =_v;
    }

    public String getAliasDouble1Double0() {
        return aliasDouble1Double0;
    }

    public void setAliasDouble1Double0(String _v) {
        this.aliasDouble1Double0 =_v;
    }

    public String getAliasNumber0ToStringMethod0() {
        return aliasNumber0ToStringMethod0;
    }

    public void setAliasNumber0ToStringMethod0(String _v) {
        this.aliasNumber0ToStringMethod0 =_v;
    }

    public String getAliasNumber0Equals0() {
        return aliasNumber0Equals0;
    }

    public void setAliasNumber0Equals0(String _v) {
        this.aliasNumber0Equals0 =_v;
    }

    public String getAliasNumber1Equals0() {
        return aliasNumber1Equals0;
    }

    public void setAliasNumber1Equals0(String _v) {
        this.aliasNumber1Equals0 =_v;
    }

    public String getAliasNumber1Equals1() {
        return aliasNumber1Equals1;
    }

    public void setAliasNumber1Equals1(String _v) {
        this.aliasNumber1Equals1 =_v;
    }

    public String getAliasNumber0CompareTo0() {
        return aliasNumber0CompareTo0;
    }

    public void setAliasNumber0CompareTo0(String _v) {
        this.aliasNumber0CompareTo0 =_v;
    }

    public String getAliasNumber0Compare0() {
        return aliasNumber0Compare0;
    }

    public void setAliasNumber0Compare0(String _v) {
        this.aliasNumber0Compare0 =_v;
    }

    public String getAliasNumber0Compare1() {
        return aliasNumber0Compare1;
    }

    public void setAliasNumber0Compare1(String _v) {
        this.aliasNumber0Compare1 =_v;
    }

    public String getAliasCharacter0CompareTo0() {
        return aliasCharacter0CompareTo0;
    }

    public void setAliasCharacter0CompareTo0(String _v) {
        this.aliasCharacter0CompareTo0 =_v;
    }

    public String getAliasCharacter0Compare0() {
        return aliasCharacter0Compare0;
    }

    public void setAliasCharacter0Compare0(String _v) {
        this.aliasCharacter0Compare0 =_v;
    }

    public String getAliasCharacter0Compare1() {
        return aliasCharacter0Compare1;
    }

    public void setAliasCharacter0Compare1(String _v) {
        this.aliasCharacter0Compare1 =_v;
    }

    public String getAliasCharacter0Digit0() {
        return aliasCharacter0Digit0;
    }

    public void setAliasCharacter0Digit0(String _v) {
        this.aliasCharacter0Digit0 =_v;
    }

    public String getAliasCharacter0Digit1() {
        return aliasCharacter0Digit1;
    }

    public void setAliasCharacter0Digit1(String _v) {
        this.aliasCharacter0Digit1 =_v;
    }

    public String getAliasCharacter1Digit0() {
        return aliasCharacter1Digit0;
    }

    public void setAliasCharacter1Digit0(String _v) {
        this.aliasCharacter1Digit0 = _v;
    }

    public String getAliasCharacter1Digit1() {
        return aliasCharacter1Digit1;
    }

    public void setAliasCharacter1Digit1(String _v) {
        this.aliasCharacter1Digit1 = _v;
    }

    public String getAliasCharacter1Digit2() {
        return aliasCharacter1Digit2;
    }

    public void setAliasCharacter1Digit2(String _v) {
        this.aliasCharacter1Digit2 = _v;
    }

    public String getAliasCharacter0ForDigit0() {
        return aliasCharacter0ForDigit0;
    }

    public void setAliasCharacter0ForDigit0(String _v) {
        this.aliasCharacter0ForDigit0 =_v;
    }

    public String getAliasCharacter0ForDigit1() {
        return aliasCharacter0ForDigit1;
    }

    public void setAliasCharacter0ForDigit1(String _v) {
        this.aliasCharacter0ForDigit1 =_v;
    }

    public String getAliasCharacter1ForDigit0() {
        return aliasCharacter1ForDigit0;
    }

    public void setAliasCharacter1ForDigit0(String _v) {
        this.aliasCharacter1ForDigit0 = _v;
    }

    public String getAliasCharacter1ForDigit1() {
        return aliasCharacter1ForDigit1;
    }

    public void setAliasCharacter1ForDigit1(String _v) {
        this.aliasCharacter1ForDigit1 = _v;
    }

    public String getAliasCharacter1ForDigit2() {
        return aliasCharacter1ForDigit2;
    }

    public void setAliasCharacter1ForDigit2(String _v) {
        this.aliasCharacter1ForDigit2 = _v;
    }

    public String getAliasCharacter0GetDirectionality0() {
        return aliasCharacter0GetDirectionality0;
    }

    public void setAliasCharacter0GetDirectionality0(String _v) {
        this.aliasCharacter0GetDirectionality0 =_v;
    }

    public String getAliasCharacter0GetType0() {
        return aliasCharacter0GetType0;
    }

    public void setAliasCharacter0GetType0(String _v) {
        this.aliasCharacter0GetType0 =_v;
    }

    public String getAliasCharacter0IsDigit0() {
        return aliasCharacter0IsDigit0;
    }

    public void setAliasCharacter0IsDigit0(String _v) {
        this.aliasCharacter0IsDigit0 =_v;
    }

    public String getAliasCharacter0IsLetter0() {
        return aliasCharacter0IsLetter0;
    }

    public void setAliasCharacter0IsLetter0(String _v) {
        this.aliasCharacter0IsLetter0 =_v;
    }

    public String getAliasCharacter0IsLetterOrDigit0() {
        return aliasCharacter0IsLetterOrDigit0;
    }

    public void setAliasCharacter0IsLetterOrDigit0(String _v) {
        this.aliasCharacter0IsLetterOrDigit0 =_v;
    }

    public String getAliasCharacter0IsWordChar0() {
        return aliasCharacter0IsWordChar0;
    }

    public void setAliasCharacter0IsWordChar0(String _v) {
        this.aliasCharacter0IsWordChar0 =_v;
    }

    public String getAliasCharacter0IsWhitespace0() {
        return aliasCharacter0IsWhitespace0;
    }

    public void setAliasCharacter0IsWhitespace0(String _v) {
        this.aliasCharacter0IsWhitespace0 =_v;
    }

    public String getAliasCharacter0IsLowerCase0() {
        return aliasCharacter0IsLowerCase0;
    }

    public void setAliasCharacter0IsLowerCase0(String _v) {
        this.aliasCharacter0IsLowerCase0 =_v;
    }

    public String getAliasCharacter0IsUpperCase0() {
        return aliasCharacter0IsUpperCase0;
    }

    public void setAliasCharacter0IsUpperCase0(String _v) {
        this.aliasCharacter0IsUpperCase0 =_v;
    }

    public String getAliasCharacter0IsSpace0() {
        return aliasCharacter0IsSpace0;
    }

    public void setAliasCharacter0IsSpace0(String _v) {
        this.aliasCharacter0IsSpace0 =_v;
    }

    public String getAliasCharacter0ToLowerCaseChar0() {
        return aliasCharacter0ToLowerCaseChar0;
    }

    public void setAliasCharacter0ToLowerCaseChar0(String _v) {
        this.aliasCharacter0ToLowerCaseChar0 =_v;
    }

    public String getAliasCharacter0ToUpperCaseChar0() {
        return aliasCharacter0ToUpperCaseChar0;
    }

    public void setAliasCharacter0ToUpperCaseChar0(String _v) {
        this.aliasCharacter0ToUpperCaseChar0 =_v;
    }

    public String getAliasCharacter0ToStringMethod0() {
        return aliasCharacter0ToStringMethod0;
    }

    public void setAliasCharacter0ToStringMethod0(String _v) {
        this.aliasCharacter0ToStringMethod0 =_v;
    }

    public String getAliasCharacter0Character0() {
        return aliasCharacter0Character0;
    }

    public void setAliasCharacter0Character0(String _v) {
        this.aliasCharacter0Character0 =_v;
    }
}
