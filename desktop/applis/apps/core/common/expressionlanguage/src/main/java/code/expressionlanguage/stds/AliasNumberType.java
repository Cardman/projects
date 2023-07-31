package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.fcts.*;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class AliasNumberType {
    private static final String BOOLEAN="15";
    private static final String BYTE="16";
    private static final String CHARACTER="17";
    private static final String DOUBLE="18";
    private static final String FLOAT="19";
    private static final String INTEGER="20";
    private static final String LONG="21";
    private static final String NUMBER="22";
    private static final String SHORT="23";
    private static final String BOOLEAN_VALUE="317";
    private static final String COMPARE="318";
    private static final String COMPARE_TO="319";
    private static final String EQUALS="320";
    private static final String PARSE_BOOLEAN="321";
    private static final String TO_STRING_METHOD="322";
    private static final String VALUE_OF_METHOD="323";
    private static final String BYTE_VALUE="324";
    private static final String DOUBLE_VALUE="325";
    private static final String FLOAT_VALUE="326";
    private static final String INT_VALUE="327";
    private static final String LONG_VALUE="328";
    private static final String SHORT_VALUE="329";
    private static final String SIGNUM="330";
    private static final String BIN="331";
    private static final String OCT="332";
    private static final String HEX="333";
    private static final String PARSE_BYTE="334";
    private static final String PARSE_BYTE_OR_NULL="335";
    private static final String PARSE_INT="336";
    private static final String PARSE_INT_OR_NULL="337";
    private static final String CHAR_VALUE="338";
    private static final String DIGIT="339";
    private static final String FOR_DIGIT="340";
    private static final String GET_CHAR_TYPE="341";
    private static final String IS_DIGIT="342";
    private static final String GET_DIRECTIONALITY="343";
    private static final String IS_LETTER="344";
    private static final String IS_LETTER_OR_DIGIT="345";
    private static final String IS_LOWER_CASE="346";
    private static final String IS_SPACE="347";
    private static final String IS_UPPER_CASE="348";
    private static final String IS_WHITESPACE="349";
    private static final String IS_WORD_CHAR="350";
    private static final String TO_LOWER_CASE_CHAR="351";
    private static final String TO_UPPER_CASE_CHAR="352";
    private static final String IS_INFINITE="353";
    private static final String IS_NAN="354";
    private static final String PARSE_DOUBLE="355";
    private static final String PARSE_DOUBLE_OR_NULL="356";
    private static final String PARSE_FLOAT="357";
    private static final String PARSE_FLOAT_OR_NULL="358";
    private static final String PARSE_LONG="359";
    private static final String PARSE_LONG_OR_NULL="360";
    private static final String PARSE_SHORT="361";
    private static final String PARSE_SHORT_OR_NULL="362";
    private static final String FIELD_PLUS_INFINITY="810";
    private static final String FIELD_MINUS_INFINITY="811";
    private static final String FIELD_NAN="812";
    private static final String FIELD_MIN_VALUE="813";
    private static final String FIELD_MAX_VALUE="814";
    private String aliasCompareTo;
    private String aliasCompare;
    private String aliasEquals;
    private String aliasToStringMethod;
    private String aliasSignum;
    private String aliasValueOfMethod;
    private String aliasMaxValueField;
    private String aliasMinValueField;
    private String aliasPlusInfinityField;
    private String aliasMinusInfinityField;
    private String aliasNanField;
    private String aliasBoolean;
    private String aliasByte;
    private String aliasShort;
    private String aliasCharacter;
    private String aliasInteger;
    private String aliasLong;
    private String aliasFloat;
    private String aliasDouble;
    private String aliasNumber;
    private String aliasParseBoolean;
    private String aliasParseByte;
    private String aliasParseShort;
    private String aliasParseInt;
    private String aliasParseLong;
    private String aliasParseFloat;
    private String aliasParseDouble;
    private String aliasParseByteOrNull;
    private String aliasParseShortOrNull;
    private String aliasParseIntOrNull;
    private String aliasParseLongOrNull;
    private String aliasParseFloatOrNull;
    private String aliasParseDoubleOrNull;
    private String aliasToBinString;
    private String aliasToOctString;
    private String aliasToHexString;
    private String aliasBooleanValue;
    private String aliasByteValue;
    private String aliasShortValue;
    private String aliasCharValue;
    private String aliasIntValue;
    private String aliasLongValue;
    private String aliasFloatValue;
    private String aliasDoubleValue;
    private String aliasDigit;
    private String aliasIsDigit;
    private String aliasIsLetter;
    private String aliasIsLetterOrDigit;
    private String aliasIsWordChar;
    private String aliasIsLowerCase;
    private String aliasIsUpperCase;
    private String aliasIsWhitespace;
    private String aliasIsSpace;
    private String aliasIsInfinite;
    private String aliasIsNan;
    private String aliasForDigit;
    private String aliasGetDirectionality;
    private String aliasGetCharType;
    private String aliasToLowerCaseChar;
    private String aliasToUpperCaseChar;
    private final AliasParamNumber params = new AliasParamNumber();

    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setAliasMaxValueField(LgNamesContent.get(_util,_cust,_mapping.getVal(FIELD_MAX_VALUE)));
        setAliasMinValueField(LgNamesContent.get(_util,_cust,_mapping.getVal(FIELD_MIN_VALUE)));
        setAliasPlusInfinityField(LgNamesContent.get(_util,_cust,_mapping.getVal(FIELD_PLUS_INFINITY)));
        setAliasMinusInfinityField(LgNamesContent.get(_util,_cust,_mapping.getVal(FIELD_MINUS_INFINITY)));
        setAliasNanField(LgNamesContent.get(_util,_cust,_mapping.getVal(FIELD_NAN)));
        setAliasEquals(LgNamesContent.get(_util,_cust,_mapping.getVal(EQUALS)));
        setAliasLong(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG)));
        setAliasShort(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT)));
        setAliasNumber(LgNamesContent.get(_util,_cust,_mapping.getVal(NUMBER)));
        setAliasParseInt(LgNamesContent.get(_util,_cust,_mapping.getVal(PARSE_INT)));
        setAliasCompare(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPARE)));
        setAliasIntValue(LgNamesContent.get(_util,_cust,_mapping.getVal(INT_VALUE)));
        setAliasBoolean(LgNamesContent.get(_util,_cust,_mapping.getVal(BOOLEAN)));
        setAliasByte(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE)));
        setAliasFloat(LgNamesContent.get(_util,_cust,_mapping.getVal(FLOAT)));
        setAliasDouble(LgNamesContent.get(_util,_cust,_mapping.getVal(DOUBLE)));
        setAliasInteger(LgNamesContent.get(_util,_cust,_mapping.getVal(INTEGER)));
        setAliasDigit(LgNamesContent.get(_util,_cust,_mapping.getVal(DIGIT)));
        setAliasIsDigit(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_DIGIT)));
        setAliasParseFloat(LgNamesContent.get(_util,_cust,_mapping.getVal(PARSE_FLOAT)));
        setAliasToStringMethod(LgNamesContent.get(_util,_cust,_mapping.getVal(TO_STRING_METHOD)));
        setAliasSignum(LgNamesContent.get(_util,_cust,_mapping.getVal(SIGNUM)));
        setAliasToBinString(LgNamesContent.get(_util,_cust,_mapping.getVal(BIN)));
        setAliasToOctString(LgNamesContent.get(_util,_cust,_mapping.getVal(OCT)));
        setAliasToHexString(LgNamesContent.get(_util,_cust,_mapping.getVal(HEX)));
        setAliasParseLongOrNull(LgNamesContent.get(_util,_cust,_mapping.getVal(PARSE_LONG_OR_NULL)));
        setAliasParseShortOrNull(LgNamesContent.get(_util,_cust,_mapping.getVal(PARSE_SHORT_OR_NULL)));
        setAliasParseFloatOrNull(LgNamesContent.get(_util,_cust,_mapping.getVal(PARSE_FLOAT_OR_NULL)));
        setAliasParseDoubleOrNull(LgNamesContent.get(_util,_cust,_mapping.getVal(PARSE_DOUBLE_OR_NULL)));
        setAliasByteValue(LgNamesContent.get(_util,_cust,_mapping.getVal(BYTE_VALUE)));
        setAliasCharValue(LgNamesContent.get(_util,_cust,_mapping.getVal(CHAR_VALUE)));
        setAliasParseIntOrNull(LgNamesContent.get(_util,_cust,_mapping.getVal(PARSE_INT_OR_NULL)));
        setAliasParseBoolean(LgNamesContent.get(_util,_cust,_mapping.getVal(PARSE_BOOLEAN)));
        setAliasParseShort(LgNamesContent.get(_util,_cust,_mapping.getVal(PARSE_SHORT)));
        setAliasCompareTo(LgNamesContent.get(_util,_cust,_mapping.getVal(COMPARE_TO)));
        setAliasCharacter(LgNamesContent.get(_util,_cust,_mapping.getVal(CHARACTER)));
        setAliasParseLong(LgNamesContent.get(_util,_cust,_mapping.getVal(PARSE_LONG)));
        setAliasValueOfMethod(LgNamesContent.get(_util,_cust,_mapping.getVal(VALUE_OF_METHOD)));
        setAliasParseByteOrNull(LgNamesContent.get(_util,_cust,_mapping.getVal(PARSE_BYTE_OR_NULL)));
        setAliasBooleanValue(LgNamesContent.get(_util,_cust,_mapping.getVal(BOOLEAN_VALUE)));
        setAliasShortValue(LgNamesContent.get(_util,_cust,_mapping.getVal(SHORT_VALUE)));
        setAliasParseDouble(LgNamesContent.get(_util,_cust,_mapping.getVal(PARSE_DOUBLE)));
        setAliasParseByte(LgNamesContent.get(_util,_cust,_mapping.getVal(PARSE_BYTE)));
        setAliasIsUpperCase(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_UPPER_CASE)));
        setAliasIsWordChar(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_WORD_CHAR)));
        setAliasIsWhitespace(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_WHITESPACE)));
        setAliasIsLetterOrDigit(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_LETTER_OR_DIGIT)));
        setAliasFloatValue(LgNamesContent.get(_util,_cust,_mapping.getVal(FLOAT_VALUE)));
        setAliasDoubleValue(LgNamesContent.get(_util,_cust,_mapping.getVal(DOUBLE_VALUE)));
        setAliasLongValue(LgNamesContent.get(_util,_cust,_mapping.getVal(LONG_VALUE)));
        setAliasIsLowerCase(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_LOWER_CASE)));
        setAliasForDigit(LgNamesContent.get(_util,_cust,_mapping.getVal(FOR_DIGIT)));
        setAliasIsSpace(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_SPACE)));
        setAliasIsLetter(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_LETTER)));
        setAliasIsNan(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_NAN)));
        setAliasToLowerCaseChar(LgNamesContent.get(_util,_cust,_mapping.getVal(TO_LOWER_CASE_CHAR)));
        setAliasToUpperCaseChar(LgNamesContent.get(_util,_cust,_mapping.getVal(TO_UPPER_CASE_CHAR)));
        setAliasIsInfinite(LgNamesContent.get(_util,_cust,_mapping.getVal(IS_INFINITE)));
        setAliasGetDirectionality(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_DIRECTIONALITY)));
        setAliasGetCharType(LgNamesContent.get(_util,_cust,_mapping.getVal(GET_CHAR_TYPE)));
    }
    public static void en(TranslationsFile _en){
        _en.add(BOOLEAN,"Boolean=$core.Boolean");
        _en.add(BYTE,"Byte=$core.Byte");
        _en.add(CHARACTER,"Character=$core.Character");
        _en.add(DOUBLE,"Double=$core.Double");
        _en.add(FLOAT,"Float=$core.Float");
        _en.add(INTEGER,"Integer=$core.Integer");
        _en.add(LONG,"Long=$core.Long");
        _en.add(NUMBER,"Number=$core.Number");
        _en.add(SHORT,"Short=$core.Short");
        _en.add(BOOLEAN_VALUE,"BooleanValue=booleanValue");
        _en.add(COMPARE,"Compare=compare");
        _en.add(COMPARE_TO,"CompareTo=compareTo");
        _en.add(EQUALS,"Equals=equals");
        _en.add(PARSE_BOOLEAN,"ParseBoolean=parseBoolean");
        _en.add(TO_STRING_METHOD,"ToStringMethod=toString");
        _en.add(VALUE_OF_METHOD,"ValueOfMethod=valueOf");
        _en.add(BYTE_VALUE,"ByteValue=byteValue");
        _en.add(DOUBLE_VALUE,"DoubleValue=doubleValue");
        _en.add(FLOAT_VALUE,"FloatValue=floatValue");
        _en.add(INT_VALUE,"IntValue=intValue");
        _en.add(LONG_VALUE,"LongValue=longValue");
        _en.add(SHORT_VALUE,"ShortValue=shortValue");
        _en.add(SIGNUM,"Signum=sgn");
        _en.add(BIN,"Bin=bin");
        _en.add(OCT,"Oct=oct");
        _en.add(HEX,"Hex=hex");
        _en.add(PARSE_BYTE,"ParseByte=parseByte");
        _en.add(PARSE_BYTE_OR_NULL,"ParseByteOrNull=parseByteOrNull");
        _en.add(PARSE_INT,"ParseInt=parseInt");
        _en.add(PARSE_INT_OR_NULL,"ParseIntOrNull=parseIntOrNull");
        _en.add(CHAR_VALUE,"CharValue=charValue");
        _en.add(DIGIT,"Digit=digit");
        _en.add(FOR_DIGIT,"ForDigit=forDigit");
        _en.add(GET_CHAR_TYPE,"GetCharType=getType");
        _en.add(IS_DIGIT,"IsDigit=isDigit");
        _en.add(GET_DIRECTIONALITY,"GetDirectionality=getDirectionality");
        _en.add(IS_LETTER,"IsLetter=isLetter");
        _en.add(IS_LETTER_OR_DIGIT,"IsLetterOrDigit=isLetterOrDigit");
        _en.add(IS_LOWER_CASE,"IsLowerCase=isLowerCase");
        _en.add(IS_SPACE,"IsSpace=isSpace");
        _en.add(IS_UPPER_CASE,"IsUpperCase=isUpperCase");
        _en.add(IS_WHITESPACE,"IsWhitespace=isWhitespace");
        _en.add(IS_WORD_CHAR,"IsWordChar=isWordChar");
        _en.add(TO_LOWER_CASE_CHAR,"ToLowerCaseChar=toLowerCase");
        _en.add(TO_UPPER_CASE_CHAR,"ToUpperCaseChar=toUpperCase");
        _en.add(IS_INFINITE,"IsInfinite=isInfinite");
        _en.add(IS_NAN,"IsNan=isNan");
        _en.add(PARSE_DOUBLE,"ParseDouble=parseDouble");
        _en.add(PARSE_DOUBLE_OR_NULL,"ParseDoubleOrNull=parseDoubleOrNull");
        _en.add(PARSE_FLOAT,"ParseFloat=parseFloat");
        _en.add(PARSE_FLOAT_OR_NULL,"ParseFloatOrNull=parseFloatOrNull");
        _en.add(PARSE_LONG,"ParseLong=parseLong");
        _en.add(PARSE_LONG_OR_NULL,"ParseLongOrNull=parseLongOrNull");
        _en.add(PARSE_SHORT,"ParseShort=parseShort");
        _en.add(PARSE_SHORT_OR_NULL,"ParseShortOrNull=parseShortOrNull");
        _en.add(FIELD_PLUS_INFINITY,"PLUS_INFINITY=PLUS_INFINITY");
        _en.add(FIELD_MINUS_INFINITY,"MINUS_INFINITY=MINUS_INFINITY");
        _en.add(FIELD_NAN,"NAN=NAN");
        _en.add(FIELD_MIN_VALUE,"MIN_VALUE=MIN_VALUE");
        _en.add(FIELD_MAX_VALUE,"MAX_VALUE=MAX_VALUE");
    }
    public static void fr(TranslationsFile _fr){
        _fr.add(BOOLEAN,"Boolean=$coeur.Booleen");
        _fr.add(BYTE,"Byte=$coeur.Entier1");
        _fr.add(CHARACTER,"Character=$coeur.Caractere");
        _fr.add(DOUBLE,"Double=$coeur.Double");
        _fr.add(FLOAT,"Float=$coeur.Flottant");
        _fr.add(INTEGER,"Integer=$coeur.Entier4");
        _fr.add(LONG,"Long=$coeur.Entier8");
        _fr.add(NUMBER,"Number=$coeur.Nombre");
        _fr.add(SHORT,"Short=$coeur.Entier2");
        _fr.add(BOOLEAN_VALUE,"BooleanValue=booleenValue");
        _fr.add(COMPARE,"Compare=compare");
        _fr.add(COMPARE_TO,"CompareTo=comparer");
        _fr.add(EQUALS,"Equals=egal");
        _fr.add(PARSE_BOOLEAN,"ParseBoolean=parseBooleen");
        _fr.add(TO_STRING_METHOD,"ToStringMethod=chaine");
        _fr.add(VALUE_OF_METHOD,"ValueOfMethod=valeurDe");
        _fr.add(BYTE_VALUE,"ByteValue=valEntier1");
        _fr.add(DOUBLE_VALUE,"DoubleValue=valDouble");
        _fr.add(FLOAT_VALUE,"FloatValue=valFlottant");
        _fr.add(INT_VALUE,"IntValue=valEntier4");
        _fr.add(LONG_VALUE,"LongValue=valEntier8");
        _fr.add(SHORT_VALUE,"ShortValue=valEntier2");
        _fr.add(SIGNUM,"Signum=sgn");
        _fr.add(BIN,"Bin=bin");
        _fr.add(OCT,"Oct=oct");
        _fr.add(HEX,"Hex=hex");
        _fr.add(PARSE_BYTE,"ParseByte=parseEntier1");
        _fr.add(PARSE_BYTE_OR_NULL,"ParseByteOrNull=parseEntier1OuNul");
        _fr.add(PARSE_INT,"ParseInt=parseEntier4");
        _fr.add(PARSE_INT_OR_NULL,"ParseIntOrNull=parseEntier4OuNul");
        _fr.add(CHAR_VALUE,"CharValue=valCaractere");
        _fr.add(DIGIT,"Digit=chiffre");
        _fr.add(FOR_DIGIT,"ForDigit=convertir");
        _fr.add(GET_CHAR_TYPE,"GetCharType=valType");
        _fr.add(IS_DIGIT,"IsDigit=estChiffre");
        _fr.add(GET_DIRECTIONALITY,"GetDirectionality=valDirection");
        _fr.add(IS_LETTER,"IsLetter=estLettre");
        _fr.add(IS_LETTER_OR_DIGIT,"IsLetterOrDigit=estLettreOuChiffre");
        _fr.add(IS_LOWER_CASE,"IsLowerCase=estMinuscule");
        _fr.add(IS_SPACE,"IsSpace=estEspace");
        _fr.add(IS_UPPER_CASE,"IsUpperCase=estMajuscule");
        _fr.add(IS_WHITESPACE,"IsWhitespace=estEspaceBlanc");
        _fr.add(IS_WORD_CHAR,"IsWordChar=estCaractereMot");
        _fr.add(TO_LOWER_CASE_CHAR,"ToLowerCaseChar=versMinuscule");
        _fr.add(TO_UPPER_CASE_CHAR,"ToUpperCaseChar=versMajuscule");
        _fr.add(IS_INFINITE,"IsInfinite=estInfini");
        _fr.add(IS_NAN,"IsNan=estNbIndefini");
        _fr.add(PARSE_DOUBLE,"ParseDouble=parseDouble");
        _fr.add(PARSE_DOUBLE_OR_NULL,"ParseDoubleOrNull=parseDoubleOuNul");
        _fr.add(PARSE_FLOAT,"ParseFloat=parseFlottant");
        _fr.add(PARSE_FLOAT_OR_NULL,"ParseFloatOrNull=parseFlottantOuNul");
        _fr.add(PARSE_LONG,"ParseLong=parseEntier8");
        _fr.add(PARSE_LONG_OR_NULL,"ParseLongOrNull=parseEntier8OuNul");
        _fr.add(PARSE_SHORT,"ParseShort=parseEntier2");
        _fr.add(PARSE_SHORT_OR_NULL,"ParseShortOrNull=parseEntier2OuNul");
        _fr.add(FIELD_PLUS_INFINITY,"PLUS_INFINITY=PLUS_INFINI");
        _fr.add(FIELD_MINUS_INFINITY,"MINUS_INFINITY=MOINS_INFINI");
        _fr.add(FIELD_NAN,"NAN=PUN");
        _fr.add(FIELD_MIN_VALUE,"MIN_VALUE=MIN_VALEUR");
        _fr.add(FIELD_MAX_VALUE,"MAX_VALUE=MAX_VALEUR");
    }
    public static void mapping(StringMap<String> _m){
        _m.addEntry(BOOLEAN,"Boolean");
        _m.addEntry(BYTE,"Byte");
        _m.addEntry(CHARACTER,"Character");
        _m.addEntry(DOUBLE,"Double");
        _m.addEntry(FLOAT,"Float");
        _m.addEntry(INTEGER,"Integer");
        _m.addEntry(LONG,"Long");
        _m.addEntry(NUMBER,"Number");
        _m.addEntry(SHORT,"Short");
        _m.addEntry(BOOLEAN_VALUE,"BooleanValue");
        _m.addEntry(COMPARE,"Compare");
        _m.addEntry(COMPARE_TO,"CompareTo");
        _m.addEntry(EQUALS,"Equals");
        _m.addEntry(PARSE_BOOLEAN,"ParseBoolean");
        _m.addEntry(TO_STRING_METHOD,"ToStringMethod");
        _m.addEntry(VALUE_OF_METHOD,"ValueOfMethod");
        _m.addEntry(BYTE_VALUE,"ByteValue");
        _m.addEntry(DOUBLE_VALUE,"DoubleValue");
        _m.addEntry(FLOAT_VALUE,"FloatValue");
        _m.addEntry(INT_VALUE,"IntValue");
        _m.addEntry(LONG_VALUE,"LongValue");
        _m.addEntry(SHORT_VALUE,"ShortValue");
        _m.addEntry(SIGNUM,"Signum");
        _m.addEntry(BIN,"Bin");
        _m.addEntry(OCT,"Oct");
        _m.addEntry(HEX,"Hex");
        _m.addEntry(PARSE_BYTE,"ParseByte");
        _m.addEntry(PARSE_BYTE_OR_NULL,"ParseByteOrNull");
        _m.addEntry(PARSE_INT,"ParseInt");
        _m.addEntry(PARSE_INT_OR_NULL,"ParseIntOrNull");
        _m.addEntry(CHAR_VALUE,"CharValue");
        _m.addEntry(DIGIT,"Digit");
        _m.addEntry(FOR_DIGIT,"ForDigit");
        _m.addEntry(GET_CHAR_TYPE,"GetCharType");
        _m.addEntry(IS_DIGIT,"IsDigit");
        _m.addEntry(GET_DIRECTIONALITY,"GetDirectionality");
        _m.addEntry(IS_LETTER,"IsLetter");
        _m.addEntry(IS_LETTER_OR_DIGIT,"IsLetterOrDigit");
        _m.addEntry(IS_LOWER_CASE,"IsLowerCase");
        _m.addEntry(IS_SPACE,"IsSpace");
        _m.addEntry(IS_UPPER_CASE,"IsUpperCase");
        _m.addEntry(IS_WHITESPACE,"IsWhitespace");
        _m.addEntry(IS_WORD_CHAR,"IsWordChar");
        _m.addEntry(TO_LOWER_CASE_CHAR,"ToLowerCaseChar");
        _m.addEntry(TO_UPPER_CASE_CHAR,"ToUpperCaseChar");
        _m.addEntry(IS_INFINITE,"IsInfinite");
        _m.addEntry(IS_NAN,"IsNan");
        _m.addEntry(PARSE_DOUBLE,"ParseDouble");
        _m.addEntry(PARSE_DOUBLE_OR_NULL,"ParseDoubleOrNull");
        _m.addEntry(PARSE_FLOAT,"ParseFloat");
        _m.addEntry(PARSE_FLOAT_OR_NULL,"ParseFloatOrNull");
        _m.addEntry(PARSE_LONG,"ParseLong");
        _m.addEntry(PARSE_LONG_OR_NULL,"ParseLongOrNull");
        _m.addEntry(PARSE_SHORT,"ParseShort");
        _m.addEntry(PARSE_SHORT_OR_NULL,"ParseShortOrNull");
        _m.addEntry(FIELD_PLUS_INFINITY,"PLUS_INFINITY");
        _m.addEntry(FIELD_MINUS_INFINITY,"MINUS_INFINITY");
        _m.addEntry(FIELD_NAN,"NAN");
        _m.addEntry(FIELD_MIN_VALUE,"MIN_VALUE");
        _m.addEntry(FIELD_MAX_VALUE,"MAX_VALUE");
    }
    public StringMap<String> allRefTypes(StringMap<String> _mapping) {
        StringMap<String> list_ = new StringMap<String>();
        list_.addEntry(_mapping.getVal(BOOLEAN), getAliasBoolean());
        list_.addEntry(_mapping.getVal(BYTE), getAliasByte());
        list_.addEntry(_mapping.getVal(CHARACTER), getAliasCharacter());
        list_.addEntry(_mapping.getVal(DOUBLE), getAliasDouble());
        list_.addEntry(_mapping.getVal(FLOAT), getAliasFloat());
        list_.addEntry(_mapping.getVal(INTEGER), getAliasInteger());
        list_.addEntry(_mapping.getVal(LONG), getAliasLong());
        list_.addEntry(_mapping.getVal(NUMBER),getAliasNumber());
        list_.addEntry(_mapping.getVal(SHORT),getAliasShort());
        return list_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(getAliasDouble(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(FIELD_PLUS_INFINITY), getAliasPlusInfinityField()),
                new KeyValueMemberName(_mapping.getVal(FIELD_MINUS_INFINITY), getAliasMinusInfinityField()),
                new KeyValueMemberName(_mapping.getVal(FIELD_NAN), getAliasNanField()),
                new KeyValueMemberName(_mapping.getVal(FIELD_MIN_VALUE), getAliasMinValueField()),
                new KeyValueMemberName(_mapping.getVal(FIELD_MAX_VALUE), getAliasMaxValueField())));
        map_.addEntry(getAliasFloat(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(FIELD_PLUS_INFINITY), getAliasPlusInfinityField()),
                new KeyValueMemberName(_mapping.getVal(FIELD_MINUS_INFINITY), getAliasMinusInfinityField()),
                new KeyValueMemberName(_mapping.getVal(FIELD_NAN), getAliasNanField()),
                new KeyValueMemberName(_mapping.getVal(FIELD_MIN_VALUE), getAliasMinValueField()),
                new KeyValueMemberName(_mapping.getVal(FIELD_MAX_VALUE), getAliasMaxValueField())));
        map_.addEntry(getAliasLong(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(FIELD_MIN_VALUE), getAliasMinValueField()),
                new KeyValueMemberName(_mapping.getVal(FIELD_MAX_VALUE), getAliasMaxValueField())));
        map_.addEntry(getAliasInteger(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(FIELD_MIN_VALUE), getAliasMinValueField()),
                new KeyValueMemberName(_mapping.getVal(FIELD_MAX_VALUE), getAliasMaxValueField())));
        map_.addEntry(getAliasCharacter(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(FIELD_MIN_VALUE), getAliasMinValueField()),
                new KeyValueMemberName(_mapping.getVal(FIELD_MAX_VALUE), getAliasMaxValueField())));
        map_.addEntry(getAliasShort(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(FIELD_MIN_VALUE), getAliasMinValueField()),
                new KeyValueMemberName(_mapping.getVal(FIELD_MAX_VALUE), getAliasMaxValueField())));
        map_.addEntry(getAliasByte(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(FIELD_MIN_VALUE), getAliasMinValueField()),
                new KeyValueMemberName(_mapping.getVal(FIELD_MAX_VALUE), getAliasMaxValueField())));
        return map_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(getAliasBoolean(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(BOOLEAN_VALUE), getAliasBooleanValue()),
                new KeyValueMemberName(_mapping.getVal(COMPARE),getAliasCompare()),
                new KeyValueMemberName(_mapping.getVal(COMPARE_TO),getAliasCompareTo()),
                new KeyValueMemberName(_mapping.getVal(EQUALS),getAliasEquals()),
                new KeyValueMemberName(_mapping.getVal(PARSE_BOOLEAN),getAliasParseBoolean()),
                new KeyValueMemberName(_mapping.getVal(TO_STRING_METHOD),getAliasToStringMethod()),
                new KeyValueMemberName(_mapping.getVal(VALUE_OF_METHOD),getAliasValueOfMethod())));
        map_.addEntry(getAliasByte(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(BYTE_VALUE),getAliasByteValue()),
                new KeyValueMemberName(_mapping.getVal(COMPARE),getAliasCompare()),
                new KeyValueMemberName(_mapping.getVal(COMPARE_TO),getAliasCompareTo()),
                new KeyValueMemberName(_mapping.getVal(DOUBLE_VALUE),getAliasDoubleValue()),
                new KeyValueMemberName(_mapping.getVal(EQUALS),getAliasEquals()),
                new KeyValueMemberName(_mapping.getVal(FLOAT_VALUE),getAliasFloatValue()),
                new KeyValueMemberName(_mapping.getVal(INT_VALUE),getAliasIntValue()),
                new KeyValueMemberName(_mapping.getVal(LONG_VALUE),getAliasLongValue()),
                new KeyValueMemberName(_mapping.getVal(SHORT_VALUE),getAliasShortValue()),
                new KeyValueMemberName(_mapping.getVal(TO_STRING_METHOD),getAliasToStringMethod()),
                new KeyValueMemberName(_mapping.getVal(SIGNUM),getAliasSignum()),
                new KeyValueMemberName(_mapping.getVal(BIN),getAliasToBinString()),
                new KeyValueMemberName(_mapping.getVal(OCT),getAliasToOctString()),
                new KeyValueMemberName(_mapping.getVal(HEX),getAliasToHexString()),
                new KeyValueMemberName(_mapping.getVal(PARSE_BYTE),getAliasParseByte()),
                new KeyValueMemberName(_mapping.getVal(PARSE_BYTE_OR_NULL),getAliasParseByteOrNull())));
        map_.addEntry(getAliasCharacter(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(BYTE_VALUE),getAliasByteValue()),
                new KeyValueMemberName(_mapping.getVal(DOUBLE_VALUE),getAliasDoubleValue()),
                new KeyValueMemberName(_mapping.getVal(EQUALS),getAliasEquals()),
                new KeyValueMemberName(_mapping.getVal(FLOAT_VALUE),getAliasFloatValue()),
                new KeyValueMemberName(_mapping.getVal(INT_VALUE),getAliasIntValue()),
                new KeyValueMemberName(_mapping.getVal(LONG_VALUE),getAliasLongValue()),
                new KeyValueMemberName(_mapping.getVal(PARSE_INT),getAliasParseInt()),
                new KeyValueMemberName(_mapping.getVal(PARSE_INT_OR_NULL),getAliasParseIntOrNull()),
                new KeyValueMemberName(_mapping.getVal(SHORT_VALUE),getAliasShortValue()),
                new KeyValueMemberName(_mapping.getVal(CHAR_VALUE),getAliasCharValue()),
                new KeyValueMemberName(_mapping.getVal(COMPARE),getAliasCompare()),
                new KeyValueMemberName(_mapping.getVal(COMPARE_TO),getAliasCompareTo()),
                new KeyValueMemberName(_mapping.getVal(DIGIT),getAliasDigit()),
                new KeyValueMemberName(_mapping.getVal(FOR_DIGIT),getAliasForDigit()),
                new KeyValueMemberName(_mapping.getVal(GET_CHAR_TYPE),getAliasGetCharType()),
                new KeyValueMemberName(_mapping.getVal(IS_DIGIT),getAliasIsDigit()),
                new KeyValueMemberName(_mapping.getVal(GET_DIRECTIONALITY),getAliasGetDirectionality()),
                new KeyValueMemberName(_mapping.getVal(IS_LETTER),getAliasIsLetter()),
                new KeyValueMemberName(_mapping.getVal(IS_LETTER_OR_DIGIT),getAliasIsLetterOrDigit()),
                new KeyValueMemberName(_mapping.getVal(IS_LOWER_CASE),getAliasIsLowerCase()),
                new KeyValueMemberName(_mapping.getVal(IS_SPACE),getAliasIsSpace()),
                new KeyValueMemberName(_mapping.getVal(IS_UPPER_CASE), getAliasIsUpperCase()),
                new KeyValueMemberName(_mapping.getVal(IS_WHITESPACE),getAliasIsWhitespace()),
                new KeyValueMemberName(_mapping.getVal(IS_WORD_CHAR),getAliasIsWordChar()),
                new KeyValueMemberName(_mapping.getVal(TO_LOWER_CASE_CHAR),getAliasToLowerCaseChar()),
                new KeyValueMemberName(_mapping.getVal(TO_STRING_METHOD), getAliasToStringMethod()),
                new KeyValueMemberName(_mapping.getVal(SIGNUM),getAliasSignum()),
                new KeyValueMemberName(_mapping.getVal(BIN),getAliasToBinString()),
                new KeyValueMemberName(_mapping.getVal(OCT),getAliasToOctString()),
                new KeyValueMemberName(_mapping.getVal(HEX),getAliasToHexString()),
                new KeyValueMemberName(_mapping.getVal(TO_UPPER_CASE_CHAR),getAliasToUpperCaseChar())));
        map_.addEntry(getAliasDouble(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(BYTE_VALUE),getAliasByteValue()),
                new KeyValueMemberName(_mapping.getVal(COMPARE),getAliasCompare()),
                new KeyValueMemberName(_mapping.getVal(COMPARE_TO),getAliasCompareTo()),
                new KeyValueMemberName(_mapping.getVal(DOUBLE_VALUE),getAliasDoubleValue()),
                new KeyValueMemberName(_mapping.getVal(EQUALS),getAliasEquals()),
                new KeyValueMemberName(_mapping.getVal(FLOAT_VALUE),getAliasFloatValue()),
                new KeyValueMemberName(_mapping.getVal(INT_VALUE),getAliasIntValue()),
                new KeyValueMemberName(_mapping.getVal(LONG_VALUE),getAliasLongValue()),
                new KeyValueMemberName(_mapping.getVal(SHORT_VALUE),getAliasShortValue()),
                new KeyValueMemberName(_mapping.getVal(TO_STRING_METHOD),getAliasToStringMethod()),
                new KeyValueMemberName(_mapping.getVal(IS_INFINITE),getAliasIsInfinite()),
                new KeyValueMemberName(_mapping.getVal(IS_NAN),getAliasIsNan()),
                new KeyValueMemberName(_mapping.getVal(PARSE_DOUBLE),getAliasParseDouble()),
                new KeyValueMemberName(_mapping.getVal(PARSE_DOUBLE_OR_NULL),getAliasParseDoubleOrNull())));
        map_.addEntry(getAliasFloat(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(BYTE_VALUE),getAliasByteValue()),
                new KeyValueMemberName(_mapping.getVal(COMPARE),getAliasCompare()),
                new KeyValueMemberName(_mapping.getVal(COMPARE_TO),getAliasCompareTo()),
                new KeyValueMemberName(_mapping.getVal(DOUBLE_VALUE),getAliasDoubleValue()),
                new KeyValueMemberName(_mapping.getVal(EQUALS),getAliasEquals()),
                new KeyValueMemberName(_mapping.getVal(FLOAT_VALUE),getAliasFloatValue()),
                new KeyValueMemberName(_mapping.getVal(INT_VALUE),getAliasIntValue()),
                new KeyValueMemberName(_mapping.getVal(LONG_VALUE),getAliasLongValue()),
                new KeyValueMemberName(_mapping.getVal(SHORT_VALUE),getAliasShortValue()),
                new KeyValueMemberName(_mapping.getVal(TO_STRING_METHOD),getAliasToStringMethod()),
                new KeyValueMemberName(_mapping.getVal(IS_INFINITE),getAliasIsInfinite()),
                new KeyValueMemberName(_mapping.getVal(IS_NAN),getAliasIsNan()),
                new KeyValueMemberName(_mapping.getVal(PARSE_FLOAT),getAliasParseFloat()),
                new KeyValueMemberName(_mapping.getVal(PARSE_FLOAT_OR_NULL),getAliasParseFloatOrNull())));
        map_.addEntry(getAliasInteger(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(BYTE_VALUE),getAliasByteValue()),
                new KeyValueMemberName(_mapping.getVal(COMPARE),getAliasCompare()),
                new KeyValueMemberName(_mapping.getVal(COMPARE_TO),getAliasCompareTo()),
                new KeyValueMemberName(_mapping.getVal(DOUBLE_VALUE),getAliasDoubleValue()),
                new KeyValueMemberName(_mapping.getVal(EQUALS),getAliasEquals()),
                new KeyValueMemberName(_mapping.getVal(FLOAT_VALUE),getAliasFloatValue()),
                new KeyValueMemberName(_mapping.getVal(INT_VALUE),getAliasIntValue()),
                new KeyValueMemberName(_mapping.getVal(LONG_VALUE),getAliasLongValue()),
                new KeyValueMemberName(_mapping.getVal(SHORT_VALUE),getAliasShortValue()),
                new KeyValueMemberName(_mapping.getVal(TO_STRING_METHOD),getAliasToStringMethod()),
                new KeyValueMemberName(_mapping.getVal(SIGNUM),getAliasSignum()),
                new KeyValueMemberName(_mapping.getVal(BIN),getAliasToBinString()),
                new KeyValueMemberName(_mapping.getVal(OCT),getAliasToOctString()),
                new KeyValueMemberName(_mapping.getVal(HEX),getAliasToHexString()),
                new KeyValueMemberName(_mapping.getVal(PARSE_INT),getAliasParseInt()),
                new KeyValueMemberName(_mapping.getVal(PARSE_INT_OR_NULL),getAliasParseIntOrNull())));
        map_.addEntry(getAliasLong(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(BYTE_VALUE),getAliasByteValue()),
                new KeyValueMemberName(_mapping.getVal(COMPARE),getAliasCompare()),
                new KeyValueMemberName(_mapping.getVal(COMPARE_TO),getAliasCompareTo()),
                new KeyValueMemberName(_mapping.getVal(DOUBLE_VALUE),getAliasDoubleValue()),
                new KeyValueMemberName(_mapping.getVal(EQUALS),getAliasEquals()),
                new KeyValueMemberName(_mapping.getVal(FLOAT_VALUE),getAliasFloatValue()),
                new KeyValueMemberName(_mapping.getVal(INT_VALUE),getAliasIntValue()),
                new KeyValueMemberName(_mapping.getVal(LONG_VALUE),getAliasLongValue()),
                new KeyValueMemberName(_mapping.getVal(SHORT_VALUE),getAliasShortValue()),
                new KeyValueMemberName(_mapping.getVal(TO_STRING_METHOD),getAliasToStringMethod()),
                new KeyValueMemberName(_mapping.getVal(SIGNUM),getAliasSignum()),
                new KeyValueMemberName(_mapping.getVal(BIN),getAliasToBinString()),
                new KeyValueMemberName(_mapping.getVal(OCT),getAliasToOctString()),
                new KeyValueMemberName(_mapping.getVal(HEX),getAliasToHexString()),
                new KeyValueMemberName(_mapping.getVal(PARSE_LONG),getAliasParseLong()),
                new KeyValueMemberName(_mapping.getVal(PARSE_LONG_OR_NULL),getAliasParseLongOrNull())));
        map_.addEntry(getAliasNumber(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(BYTE_VALUE),getAliasByteValue()),
                new KeyValueMemberName(_mapping.getVal(COMPARE),getAliasCompare()),
                new KeyValueMemberName(_mapping.getVal(COMPARE_TO),getAliasCompareTo()),
                new KeyValueMemberName(_mapping.getVal(DOUBLE_VALUE),getAliasDoubleValue()),
                new KeyValueMemberName(_mapping.getVal(EQUALS),getAliasEquals()),
                new KeyValueMemberName(_mapping.getVal(FLOAT_VALUE),getAliasFloatValue()),
                new KeyValueMemberName(_mapping.getVal(INT_VALUE),getAliasIntValue()),
                new KeyValueMemberName(_mapping.getVal(LONG_VALUE),getAliasLongValue()),
                new KeyValueMemberName(_mapping.getVal(SHORT_VALUE),getAliasShortValue()),
                new KeyValueMemberName(_mapping.getVal(TO_STRING_METHOD),getAliasToStringMethod())));
        map_.addEntry(getAliasShort(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(BYTE_VALUE),getAliasByteValue()),
                new KeyValueMemberName(_mapping.getVal(COMPARE),getAliasCompare()),
                new KeyValueMemberName(_mapping.getVal(COMPARE_TO),getAliasCompareTo()),
                new KeyValueMemberName(_mapping.getVal(DOUBLE_VALUE),getAliasDoubleValue()),
                new KeyValueMemberName(_mapping.getVal(EQUALS),getAliasEquals()),
                new KeyValueMemberName(_mapping.getVal(FLOAT_VALUE),getAliasFloatValue()),
                new KeyValueMemberName(_mapping.getVal(INT_VALUE),getAliasIntValue()),
                new KeyValueMemberName(_mapping.getVal(LONG_VALUE),getAliasLongValue()),
                new KeyValueMemberName(_mapping.getVal(SHORT_VALUE),getAliasShortValue()),
                new KeyValueMemberName(_mapping.getVal(TO_STRING_METHOD),getAliasToStringMethod()),
                new KeyValueMemberName(_mapping.getVal(SIGNUM),getAliasSignum()),
                new KeyValueMemberName(_mapping.getVal(BIN),getAliasToBinString()),
                new KeyValueMemberName(_mapping.getVal(OCT),getAliasToOctString()),
                new KeyValueMemberName(_mapping.getVal(HEX),getAliasToHexString()),
                new KeyValueMemberName(_mapping.getVal(PARSE_SHORT),getAliasParseShort()),
                new KeyValueMemberName(_mapping.getVal(PARSE_SHORT_OR_NULL),getAliasParseShortOrNull())));
        return map_;
    }
    private StandardType nbType(LgNames _lgNames) {
        String aliasObject_ = _lgNames.getContent().getCoreNames().getAliasObject();
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass std_ = new StandardClass(aliasNumber, fields_, constructors_, methods_, aliasObject_, StdClassModifier.ABSTRACT);
        std_.addSuperStdTypes(_lgNames.getCoreNames().getObjType());
        numbersAbsMethods(_lgNames,methods_, aliasNumber);
        standards_.addEntry(aliasNumber, std_);
        return std_;
    }
    private StandardType longType(LgNames _lgNames, StandardType _type) {
        String aliasPrimLong_ = _lgNames.getContent().getPrimTypes().getAliasPrimLong();
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass std_ = new StandardClass(aliasLong, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL, new DfNb(aliasLong));
        std_.addSuperStdTypes(_type);
        std_.addSuperStdTypes(_lgNames.getCoreNames().getObjType());
        numbersConstructors(_lgNames,constructors_, aliasPrimLong_,new StringList(params.getAliasLong0Long0()),new StringList(params.getAliasLong1Long0()), new FctLong(new DefRadix()));
        methods_.add(addToStrMeth(_lgNames, aliasPrimLong_, new StringList(params.getAliasLong0ToStringMethod0()), new FctNbRelToStr0()));
        methods_.addAllElts(addParserMaths(_lgNames, aliasLong, aliasParseLong, new StringList(params.getAliasLong0ParseLong0()), new StringList(params.getAliasLong1ParseLong0(), params.getAliasLong1ParseLong1()), new FctLong(new DefRadix()), new FctLong(new ArgRadix())));
        methods_.addAllElts(addCompare(_lgNames, aliasPrimLong_, aliasLong, new StringList(params.getAliasLong0CompareTo0()), new StringList(params.getAliasLong0Compare0(),params.getAliasLong0Compare1()), new FctNbCompareToSpecRel(), new FctNbCompareSpecRel()));
        methods_.add(addSpecBaseStr(aliasToBinString, _lgNames, aliasPrimLong_, new StringList(params.getAliasLong0ToBinString0()),new FctLongStrBin()));
        methods_.add(addSpecBaseStr(aliasToOctString, _lgNames, aliasPrimLong_, new StringList(params.getAliasLong0ToOctString0()),new FctLongStrOct()));
        methods_.add(addSpecBaseStr(aliasToHexString, _lgNames, aliasPrimLong_, new StringList(params.getAliasLong0ToHexString0()),new FctLongStrHex()));
        methods_.addAllElts(addParserMaths(_lgNames, aliasLong, aliasParseLongOrNull, new StringList(params.getAliasLong0ParseLongOrNull0()), new StringList(params.getAliasLong1ParseLongOrNull0(),params.getAliasLong1ParseLongOrNull1()), new FctLongSafe(new DefRadix()), new FctLongSafe(new ArgRadix())));
        numbersValuesFields(fields_, aliasPrimLong_);
        StringList params_ = new StringList(aliasPrimLong_,_lgNames.getContent().getPrimTypes().getAliasPrimInteger());
        StandardMethod method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasLong1ToStringMethod0(),params.getAliasLong1ToStringMethod1()), new FctNbRelToStr1());
        methods_.add( method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasSignum, params_, _lgNames.getContent().getPrimTypes().getAliasPrimByte(), false, MethodModifier.STATIC,new StringList(params.getAliasLong0Signum0()),new FctNbRelSgn());
        methods_.add( method_);
        standards_.addEntry(aliasLong, std_);
        return std_;
    }
    private StandardType intType(LgNames _lgNames, StandardType _type, StandardType _lg) {
        String aliasPrimInteger_ = _lgNames.getContent().getPrimTypes().getAliasPrimInteger();
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass std_ = new StandardClass(aliasInteger, fields_, constructors_, methods_, aliasLong, MethodModifier.FINAL, new DfNb(aliasInteger));
        std_.addSuperStdTypes(_lg);
        std_.addSuperStdTypes(_type);
        std_.addSuperStdTypes(_lgNames.getCoreNames().getObjType());
        numbersConstructors(_lgNames,constructors_, aliasPrimInteger_,new StringList(params.getAliasInteger0Integer0()),new StringList(params.getAliasInteger1Integer0()), new FctInteger(new DefRadix()));
        methods_.add(addToStrMeth(_lgNames, aliasPrimInteger_, new StringList(params.getAliasInteger0ToStringMethod0()), new FctNbRelToStr0()));
        methods_.addAllElts(addParserMaths(_lgNames, aliasInteger, aliasParseInt, new StringList(params.getAliasInteger0ParseInt0()), new StringList(params.getAliasInteger1ParseInt0(), params.getAliasInteger1ParseInt1()), new FctInteger(new DefRadix()), new FctInteger(new ArgRadix())));
        methods_.addAllElts(addCompare(_lgNames, aliasPrimInteger_, aliasInteger, new StringList(params.getAliasInteger0CompareTo0()), new StringList(params.getAliasInteger0Compare0(),params.getAliasInteger0Compare1()), new FctNbCompareToSpecRel(), new FctNbCompareSpecRel()));
        methods_.add(addSpecBaseStr(aliasToBinString, _lgNames, aliasPrimInteger_, new StringList(params.getAliasInteger0ToBinString0()),new FctIntegerStrBin()));
        methods_.add(addSpecBaseStr(aliasToOctString, _lgNames, aliasPrimInteger_, new StringList(params.getAliasInteger0ToOctString0()),new FctIntegerStrOct()));
        methods_.add(addSpecBaseStr(aliasToHexString, _lgNames, aliasPrimInteger_, new StringList(params.getAliasInteger0ToHexString0()),new FctIntegerStrHex()));
        methods_.addAllElts(addParserMaths(_lgNames, aliasInteger, aliasParseIntOrNull, new StringList(params.getAliasInteger0ParseIntOrNull0()), new StringList(params.getAliasInteger1ParseIntOrNull0(),params.getAliasInteger1ParseIntOrNull1()), new FctIntegerSafe(new DefRadix()), new FctIntegerSafe(new ArgRadix())));
        numbersValuesFields(fields_, aliasPrimInteger_);
        standards_.addEntry(aliasInteger, std_);
        return std_;
    }
    private StandardType shortType(LgNames _lgNames, StandardType _type, StandardType _lg, StandardType _intType) {
        String aliasPrimShort_ = _lgNames.getContent().getPrimTypes().getAliasPrimShort();
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass std_ = new StandardClass(aliasShort, fields_, constructors_, methods_, aliasInteger, MethodModifier.FINAL, new DfNb(aliasShort));
        std_.addSuperStdTypes(_intType);
        std_.addSuperStdTypes(_lg);
        std_.addSuperStdTypes(_type);
        std_.addSuperStdTypes(_lgNames.getCoreNames().getObjType());
        numbersConstructors(_lgNames,constructors_, aliasPrimShort_,new StringList(params.getAliasShort0Short0()),new StringList(params.getAliasShort1Short0()), new FctShort(new DefRadix()));
        methods_.add(addToStrMeth(_lgNames, aliasPrimShort_, new StringList(params.getAliasShort0ToStringMethod0()), new FctNbRelToStr0()));
        methods_.addAllElts(addParserMaths(_lgNames, aliasShort, aliasParseShort, new StringList(params.getAliasShort0ParseShort0()), new StringList(params.getAliasShort1ParseShort0(), params.getAliasShort1ParseShort1()), new FctShort(new DefRadix()), new FctShort(new ArgRadix())));
        methods_.addAllElts(addCompare(_lgNames, aliasPrimShort_, aliasShort, new StringList(params.getAliasShort0CompareTo0()), new StringList(params.getAliasShort0Compare0(),params.getAliasShort0Compare1()), new FctNbCompareToSpecRel(), new FctNbCompareSpecRel()));
        methods_.add(addSpecBaseStr(aliasToBinString, _lgNames, aliasPrimShort_, new StringList(params.getAliasShort0ToBinString0()),new FctShortStrBin()));
        methods_.add(addSpecBaseStr(aliasToOctString, _lgNames, aliasPrimShort_, new StringList(params.getAliasShort0ToOctString0()),new FctShortStrOct()));
        methods_.add(addSpecBaseStr(aliasToHexString, _lgNames, aliasPrimShort_, new StringList(params.getAliasShort0ToHexString0()),new FctShortStrHex()));
        methods_.addAllElts(addParserMaths(_lgNames, aliasShort, aliasParseShortOrNull, new StringList(params.getAliasShort0ParseShortOrNull0()), new StringList(params.getAliasShort1ParseShortOrNull0(),params.getAliasShort1ParseShortOrNull1()), new FctShortSafe(new DefRadix()), new FctShortSafe(new ArgRadix())));
        numbersValuesFields(fields_, aliasPrimShort_);
        standards_.addEntry(aliasShort, std_);
        return std_;
    }
    private void byteType(LgNames _lgNames, StandardType _type, StandardType _lg, StandardType _intType, StandardType _shortType) {
        String aliasPrimByte_ = _lgNames.getContent().getPrimTypes().getAliasPrimByte();
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass std_ = new StandardClass(aliasByte, fields_, constructors_, methods_, aliasShort, MethodModifier.FINAL, new DfNb(aliasByte));
        std_.addSuperStdTypes(_shortType);
        std_.addSuperStdTypes(_intType);
        std_.addSuperStdTypes(_lg);
        std_.addSuperStdTypes(_type);
        std_.addSuperStdTypes(_lgNames.getCoreNames().getObjType());
        numbersConstructors(_lgNames,constructors_, aliasPrimByte_,new StringList(params.getAliasByte0Byte0()),new StringList(params.getAliasByte1Byte0()), new FctByte(new DefRadix()));
        methods_.add(addToStrMeth(_lgNames, aliasPrimByte_, new StringList(params.getAliasByte0ToStringMethod0()), new FctNbRelToStr0()));
        methods_.addAllElts(addParserMaths(_lgNames, aliasByte, aliasParseByte, new StringList(params.getAliasByte0ParseByte0()), new StringList(params.getAliasByte1ParseByte0(), params.getAliasByte1ParseByte1()), new FctByte(new DefRadix()), new FctByte(new ArgRadix())));
        methods_.addAllElts(addCompare(_lgNames, aliasPrimByte_, aliasByte, new StringList(params.getAliasByte0CompareTo0()), new StringList(params.getAliasByte0Compare0(),params.getAliasByte0Compare1()), new FctNbCompareToSpecRel(), new FctNbCompareSpecRel()));
        methods_.add(addSpecBaseStr(aliasToBinString, _lgNames, aliasPrimByte_, new StringList(params.getAliasByte0ToBinString0()),new FctByteStrBin()));
        methods_.add(addSpecBaseStr(aliasToOctString, _lgNames, aliasPrimByte_, new StringList(params.getAliasByte0ToOctString0()),new FctByteStrOct()));
        methods_.add(addSpecBaseStr(aliasToHexString, _lgNames, aliasPrimByte_, new StringList(params.getAliasByte0ToHexString0()),new FctByteStrHex()));
        methods_.addAllElts(addParserMaths(_lgNames, aliasByte, aliasParseByteOrNull, new StringList(params.getAliasByte0ParseByteOrNull0()), new StringList(params.getAliasByte1ParseByteOrNull0(),params.getAliasByte1ParseByteOrNull1()), new FctByteSafe(new DefRadix()), new FctByteSafe(new ArgRadix())));
        numbersValuesFields(fields_, aliasPrimByte_);
        standards_.addEntry(aliasByte, std_);
    }
    private void charType(LgNames _lgNames, StandardType _type, StandardType _lg, StandardType _intType) {
        String aliasPrimBoolean_ = _lgNames.getContent().getPrimTypes().getAliasPrimBoolean();
        String aliasPrimByte_ = _lgNames.getContent().getPrimTypes().getAliasPrimByte();
        String aliasPrimChar_ = _lgNames.getContent().getPrimTypes().getAliasPrimChar();
        String aliasPrimInteger_ = _lgNames.getContent().getPrimTypes().getAliasPrimInteger();
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass stdcl_ = new StandardClass(aliasCharacter, fields_, constructors_, methods_, aliasInteger, MethodModifier.FINAL, new DfNb(aliasCharacter));
        stdcl_.addSuperStdTypes(_intType);
        stdcl_.addSuperStdTypes(_lg);
        stdcl_.addSuperStdTypes(_type);
        stdcl_.addSuperStdTypes(_lgNames.getCoreNames().getObjType());
        StringList params_ = new StringList();
        StandardMethod method_ = new StandardMethod(aliasCharValue, params_, aliasPrimChar_, false, MethodModifier.NORMAL, new FctNbIdInst());
        methods_.add( method_);
        params_ = new StringList(aliasCharacter);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasCharacter0CompareTo0()),new FctNbCompareToSpecRelCheck());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_, aliasPrimChar_);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0Compare0(),params.getAliasCharacter0Compare1()),new FctNbCompareSpecRel());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasDigit, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0Digit0(),params.getAliasCharacter0Digit1()),new FctCharDigit());
        methods_.add( method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasForDigit, params_, aliasPrimChar_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0ForDigit0(),params.getAliasCharacter0ForDigit1()),new FctCharForDigit());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasGetDirectionality, params_, aliasPrimByte_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0GetDirectionality0()),new FctCharGetDirectionality());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasGetCharType, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0GetType0()), new FctCharGetCharType());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsDigit, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0IsDigit0()),new FctCharIsDigit());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsLetter, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0IsLetter0()),new FctCharIsLetter());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsLetterOrDigit, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0IsLetterOrDigit0()),new FctCharIsLetterOrDigit());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsWordChar, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0IsWordChar0()),new FctCharIsWordChar());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsWhitespace, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0IsWhitespace0()),new FctCharIsSpace());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsLowerCase, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0IsLowerCase0()),new FctCharIsLowerCase());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsUpperCase, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0IsUpperCase0()),new FctCharIsUpperCase());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsSpace, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0IsSpace0()),new FctCharIsSpace());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasToLowerCaseChar, params_, aliasPrimChar_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0ToLowerCaseChar0()),new FctCharToLowerCase());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasToUpperCaseChar, params_, aliasPrimChar_, false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0ToUpperCaseChar0()),new FctCharToUpperCase());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasCharacter0ToStringMethod0()),new FctCharToStr());
        methods_.add( method_);
        params_ = new StringList(aliasPrimChar_);
        StandardConstructor ctor_ = new StandardConstructor(params_, false,new StringList(params.getAliasCharacter0Character0()),new FctNbId());
        constructors_.add(ctor_);
        numbersValuesFields(fields_, aliasPrimChar_);
        standards_.addEntry(aliasCharacter, stdcl_);
    }
    private StandardType doubleType(LgNames _lgNames, StandardType _type) {
        String aliasPrimBoolean_ = _lgNames.getContent().getPrimTypes().getAliasPrimBoolean();
        String aliasPrimDouble_ = _lgNames.getContent().getPrimTypes().getAliasPrimDouble();
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass std_ = new StandardClass(aliasDouble, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL, new DfNb(aliasDouble));
        std_.addSuperStdTypes(_type);
        std_.addSuperStdTypes(_lgNames.getCoreNames().getObjType());
        numbersConstructors(_lgNames,constructors_, aliasPrimDouble_,new StringList(params.getAliasDouble0Double0()),new StringList(params.getAliasDouble1Double0()), new FctDouble());
        methods_.add(addToStrMeth(_lgNames, aliasPrimDouble_, new StringList(params.getAliasDouble0ToStringMethod0()), new FctNbDoubleToStr()));
        methods_.add(dbParser(_lgNames, aliasDouble, aliasParseDouble, new StringList(params.getAliasDouble0ParseDouble0()), new FctDouble()));
        methods_.addAllElts(addCompare(_lgNames, aliasPrimDouble_, aliasDouble, new StringList(params.getAliasDouble0CompareTo0()), new StringList(params.getAliasDouble0Compare0(),params.getAliasDouble0Compare1()), new FctNbCompareToSpecReal(), new FctNbCompareSpecReal()));
        numbersSafeParsersMethods(_lgNames,methods_, aliasDouble, aliasParseDoubleOrNull,new StringList(params.getAliasDouble0ParseDoubleOrNull0()), new FctDoubleSafe());
        StringList params_ = new StringList();
        StandardMethod method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, new FctDoubleIsInfinite0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasDouble0IsInfinite0()), new FctDoubleIsInfinite1());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsNan, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, new FctDoubleIsNan0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasIsNan, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasDouble0IsNan0()), new FctDoubleIsNan1());
        methods_.add( method_);
        numbersDotValuesFields(fields_, aliasPrimDouble_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasSignum, params_, aliasPrimDouble_, false, MethodModifier.STATIC,new StringList(params.getAliasDouble0Signum0()),new FctNbDoubleSgn());
        methods_.add( method_);
        standards_.addEntry(aliasDouble, std_);
        return std_;
    }
    private void floatType(LgNames _lgNames, StandardType _type, StandardType _db) {
        String aliasPrimFloat_ = _lgNames.getContent().getPrimTypes().getAliasPrimFloat();
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardClass std_ = new StandardClass(aliasFloat, fields_, constructors_, methods_, aliasDouble, MethodModifier.FINAL, new DfNb(aliasFloat));
        std_.addSuperStdTypes(_db);
        std_.addSuperStdTypes(_type);
        std_.addSuperStdTypes(_lgNames.getCoreNames().getObjType());
        numbersConstructors(_lgNames,constructors_, aliasPrimFloat_,new StringList(params.getAliasFloat0Float0()),new StringList(params.getAliasFloat1Float0()), new FctFloat());
        methods_.add(addToStrMeth(_lgNames, aliasPrimFloat_, new StringList(params.getAliasFloat0ToStringMethod0()), new FctNbFloatToStr()));
        methods_.add(dbParser(_lgNames, aliasFloat, aliasParseFloat, new StringList(params.getAliasFloat0ParseFloat0()), new FctFloat()));
        methods_.addAllElts(addCompare(_lgNames, aliasPrimFloat_, aliasFloat, new StringList(params.getAliasFloat0CompareTo0()), new StringList(params.getAliasFloat0Compare0(),params.getAliasFloat0Compare1()), new FctNbCompareToSpecReal(), new FctNbCompareSpecReal()));
        numbersSafeParsersMethods(_lgNames,methods_, aliasFloat, aliasParseFloatOrNull, new StringList(params.getAliasFloat0ParseFloatOrNull0()), new FctFloatSafe());
        numbersDotValuesFields(fields_, aliasPrimFloat_);
        standards_.addEntry(aliasFloat, std_);
    }
    public void build(LgNames _lgNames) {
        String aliasObject_ = _lgNames.getContent().getCoreNames().getAliasObject();
        String aliasPrimBoolean_ = _lgNames.getContent().getPrimTypes().getAliasPrimBoolean();
        String aliasPrimInteger_ = _lgNames.getContent().getPrimTypes().getAliasPrimInteger();
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        StandardType std_ = new StandardClass(aliasBoolean, fields_, constructors_, methods_, aliasObject_, MethodModifier.FINAL, new DfBool());
        std_.addSuperStdTypes(_lgNames.getCoreNames().getObjType());
        StringList params_ = new StringList();
        StandardMethod method_ = new StandardMethod(aliasBooleanValue, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, new FctNbIdInst());
        methods_.add( method_);
        params_ = new StringList(aliasPrimBoolean_, aliasPrimBoolean_);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasBoolean0Compare0(),params.getAliasBoolean0Compare1()),new FctNbCompareSpecBool());
        methods_.add( method_);
        params_ = new StringList(aliasBoolean);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasBoolean0CompareTo0()),new FctNbCompareToSpecBool());
        methods_.add( method_);
        params_ = new StringList(aliasBoolean);
        method_ = new StandardMethod(aliasEquals, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new StringList(params.getAliasBoolean0Equals0()),new FctBoolEquals());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasParseBoolean, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasBoolean0ParseBoolean0()), new FctBool());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.NORMAL, new FctNbBoolToStr0());
        methods_.add( method_);
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasBoolean0ToStringMethod0()), new FctNbBoolToStr1());
        methods_.add( method_);
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasValueOfMethod, params_, aliasBoolean, false, MethodModifier.STATIC,new StringList(params.getAliasBoolean0ValueOfMethod0()), new FctNbId());
        methods_.add( method_);
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString());
        method_ = new StandardMethod(aliasValueOfMethod, params_, aliasBoolean, false, MethodModifier.STATIC,new StringList(params.getAliasBoolean1ValueOfMethod0()), new FctBool());
        methods_.add( method_);
        StandardConstructor ctor_;
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,new StringList(params.getAliasBoolean0Boolean0()),new FctBool());
        constructors_.add(ctor_);
        params_ = new StringList(aliasBoolean);
        ctor_ = new StandardConstructor(params_,false,new StringList(params.getAliasBoolean1Boolean0()),new FctNbId());
        constructors_.add(ctor_);
        standards_.addEntry(aliasBoolean, std_);
        StandardType nbType_ = nbType(_lgNames);
        StandardType lgType_ = longType(_lgNames, nbType_);
        StandardType intType_ = intType(_lgNames, nbType_, lgType_);
        StandardType shortType_ = shortType(_lgNames, nbType_, lgType_, intType_);
        byteType(_lgNames, nbType_, lgType_, intType_,shortType_);
        charType(_lgNames,nbType_,lgType_,intType_);
        StandardType db_ = doubleType(_lgNames, nbType_);
        floatType(_lgNames,nbType_,db_);
    }

    private static CustList<StandardMethod> addParserMaths(LgNames _lgNames, String _aliasType, String _aliasParseOrNull, StringList _first, StringList _second, StdCaller _defCaller, StdCaller _radixCaller) {
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        String aliasPrimInteger_ = _lgNames.getContent().getPrimTypes().getAliasPrimInteger();
        StringList params_;
        StandardMethod method_;
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString());
        method_ = new StandardMethod(_aliasParseOrNull, params_, _aliasType, false, MethodModifier.STATIC, _first, _defCaller);
        methods_.add(method_);
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString(), aliasPrimInteger_);
        method_ = new StandardMethod(_aliasParseOrNull, params_, _aliasType, false, MethodModifier.STATIC, _second, _radixCaller);
        methods_.add(method_);
        return methods_;
    }

    private static StandardMethod addSpecBaseStr(String _name, LgNames _lgNames, String _primitive, StringList _paramsNames, StdCaller _caller) {
        StringList params_ = new StringList(_primitive);
        return new StandardMethod(_name, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC, _paramsNames, _caller);
    }

    private StandardMethod addToStrMeth(LgNames _lgNames, String _primitive, StringList _first, StdCaller _caller) {
        StringList params_ = new StringList(_primitive);
        return new StandardMethod(aliasToStringMethod, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC, _first, _caller);
    }

    private CustList<StandardMethod> addCompare(LgNames _lgNames, String _primitive, String _owner, StringList _fourth, StringList _fifth, StdCaller _instCaller, StdCaller _statCaller) {
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        String aliasPrimInteger_ = _lgNames.getContent().getPrimTypes().getAliasPrimInteger();
        StringList params_;
        StandardMethod method_;
        params_ = new StringList(_owner);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, _fourth, _instCaller);
        methods_.add( method_);
        params_ = new StringList(_primitive, _primitive);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger_, false, MethodModifier.STATIC, _fifth, _statCaller);
        methods_.add( method_);
        return methods_;
    }

    private static void numbersConstructors(LgNames _lgNames, CustList<StandardConstructor> _ctors, String _primitive, StringList _first, StringList _second, StdCaller _caller) {
        StringList params_;
        StandardConstructor ctor_;
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString());
        ctor_ = new StandardConstructor(params_,false,_first, _caller);
        _ctors.add(ctor_);
        params_ = new StringList(_primitive);
        ctor_ = new StandardConstructor(params_,false,_second, new FctNbId());
        _ctors.add(ctor_);
    }
    private void numbersDotValuesFields(CustList<CstFieldInfo> _fields, String _primitive) {
        CstFieldInfo field_ = new CstFieldInfo(aliasMinValueField, _primitive);
        _fields.add(field_);
        field_ = new CstFieldInfo(aliasMaxValueField, _primitive);
        _fields.add(field_);
        field_ = new CstFieldInfo(aliasMinusInfinityField, _primitive);
        _fields.add(field_);
        field_ = new CstFieldInfo(aliasPlusInfinityField, _primitive);
        _fields.add(field_);
        field_ = new CstFieldInfo(aliasNanField, _primitive);
        _fields.add(field_);
    }
    private void numbersValuesFields(CustList<CstFieldInfo> _fields, String _primitive) {
        CstFieldInfo field_ = new CstFieldInfo(aliasMinValueField, _primitive);
        _fields.add(field_);
        field_ = new CstFieldInfo(aliasMaxValueField, _primitive);
        _fields.add(field_);
    }

    private StandardMethod dbParser(LgNames _lgNames, String _owner, String _parserName, StringList _second, StdCaller _caller) {
        StringList params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString());
        return new StandardMethod(_parserName, params_, _owner, false, MethodModifier.STATIC, _second, _caller);
    }

    private static void numbersSafeParsersMethods(LgNames _lgNames, CustList<StandardMethod> _methods, String _owner, String _parserName, StringList _first, StdCaller _caller) {
        StringList params_;
        StandardMethod method_;
        params_ = new StringList(_lgNames.getContent().getCharSeq().getAliasString());
        method_ = new StandardMethod(_parserName, params_, _owner, false, MethodModifier.STATIC, _first, _caller);
        _methods.add(method_);

    }
    private void numbersAbsMethods(LgNames _lgNames, CustList<StandardMethod> _methods, String _owner) {
        String aliasPrimBoolean_ = _lgNames.getContent().getPrimTypes().getAliasPrimBoolean();
        String aliasPrimByte_ = _lgNames.getContent().getPrimTypes().getAliasPrimByte();
        String aliasPrimShort_ = _lgNames.getContent().getPrimTypes().getAliasPrimShort();
        String aliasPrimInteger_ = _lgNames.getContent().getPrimTypes().getAliasPrimInteger();
        String aliasPrimLong_ = _lgNames.getContent().getPrimTypes().getAliasPrimLong();
        String aliasPrimFloat_ = _lgNames.getContent().getPrimTypes().getAliasPrimFloat();
        String aliasPrimDouble_ = _lgNames.getContent().getPrimTypes().getAliasPrimDouble();
        StringList params_;
        StandardMethod method_;
        params_ = new StringList();
        method_ = new StandardMethod(aliasByteValue, params_, aliasPrimByte_, false, MethodModifier.NORMAL, new FctNbToByte());
        _methods.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasShortValue, params_, aliasPrimShort_, false, MethodModifier.NORMAL, new FctNbToShort());
        _methods.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIntValue, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, new FctNbToInteger());
        _methods.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasLongValue, params_, aliasPrimLong_, false, MethodModifier.NORMAL, new FctNbToLong());
        _methods.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFloatValue, params_, aliasPrimFloat_, false, MethodModifier.NORMAL, new FctNbToFloat());
        _methods.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDoubleValue, params_, aliasPrimDouble_, false, MethodModifier.NORMAL, new FctNbToDouble());
        _methods.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.NORMAL, new FctNbToStr0());
        _methods.add( method_);
        params_ = new StringList(_owner);
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasNumber0ToStringMethod0()), new FctNbToStr1());
        _methods.add( method_);
        params_ = new StringList(aliasNumber);
        method_ = new StandardMethod(aliasEquals, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new StringList(params.getAliasNumber0Equals0()),new FctNbEqualsGene0());
        _methods.add( method_);
        params_ = new StringList(aliasNumber,aliasNumber);
        method_ = new StandardMethod(aliasEquals, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,new StringList(params.getAliasNumber1Equals0(),params.getAliasNumber1Equals1()),new FctNbEqualsGene1());
        _methods.add( method_);
        params_ = new StringList(_owner);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,new StringList(params.getAliasNumber0CompareTo0()),new FctNbCompareToGene());
        _methods.add( method_);
        params_ = new StringList(_owner, _owner);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger_, false, MethodModifier.STATIC,new StringList(params.getAliasNumber0Compare0(),params.getAliasNumber0Compare1()),new FctNbCompareGene());
        _methods.add( method_);
    }
    public String getAliasCompareTo() {
        return aliasCompareTo;
    }
    public void setAliasCompareTo(String _aliasCompareTo) {
        aliasCompareTo = _aliasCompareTo;
    }
    public String getAliasCompare() {
        return aliasCompare;
    }
    public void setAliasCompare(String _aliasCompare) {
        aliasCompare = _aliasCompare;
    }
    public String getAliasEquals() {
        return aliasEquals;
    }
    public void setAliasEquals(String _aliasEquals) {
        aliasEquals = _aliasEquals;
    }
    public String getAliasToStringMethod() {
        return aliasToStringMethod;
    }
    public void setAliasToStringMethod(String _aliasToString) {
        aliasToStringMethod = _aliasToString;
    }

    public String getAliasToBinString() {
        return aliasToBinString;
    }

    public void setAliasToBinString(String _aliasToBinString) {
        this.aliasToBinString = _aliasToBinString;
    }

    public String getAliasToOctString() {
        return aliasToOctString;
    }

    public void setAliasToOctString(String _aliasToOctString) {
        this.aliasToOctString = _aliasToOctString;
    }

    public String getAliasToHexString() {
        return aliasToHexString;
    }

    public void setAliasToHexString(String _aliasToHexString) {
        this.aliasToHexString = _aliasToHexString;
    }

    public String getAliasSignum() {
        return aliasSignum;
    }
    public void setAliasSignum(String _aliasToString) {
        aliasSignum = _aliasToString;
    }
    public String getAliasValueOfMethod() {
        return aliasValueOfMethod;
    }
    public void setAliasValueOfMethod(String _aliasValueOf) {
        aliasValueOfMethod = _aliasValueOf;
    }
    public String getAliasMaxValueField() {
        return aliasMaxValueField;
    }
    public void setAliasMaxValueField(String _aliasMaxValueField) {
        aliasMaxValueField = _aliasMaxValueField;
    }
    public String getAliasMinValueField() {
        return aliasMinValueField;
    }
    public void setAliasMinValueField(String _aliasMinValueField) {
        aliasMinValueField = _aliasMinValueField;
    }

    public String getAliasPlusInfinityField() {
        return aliasPlusInfinityField;
    }

    public void setAliasPlusInfinityField(String _aliasPlusInfinityField) {
        this.aliasPlusInfinityField = _aliasPlusInfinityField;
    }

    public String getAliasMinusInfinityField() {
        return aliasMinusInfinityField;
    }

    public void setAliasMinusInfinityField(String _aliasMinusInfinityField) {
        this.aliasMinusInfinityField = _aliasMinusInfinityField;
    }

    public String getAliasNanField() {
        return aliasNanField;
    }

    public void setAliasNanField(String _aliasNanField) {
        this.aliasNanField = _aliasNanField;
    }

    public String getAliasBoolean() {
        return aliasBoolean;
    }
    public void setAliasBoolean(String _aliasBoolean) {
        aliasBoolean = _aliasBoolean;
    }
    public String getAliasByte() {
        return aliasByte;
    }
    public void setAliasByte(String _aliasByte) {
        aliasByte = _aliasByte;
    }
    public String getAliasShort() {
        return aliasShort;
    }
    public void setAliasShort(String _aliasShort) {
        aliasShort = _aliasShort;
    }
    public String getAliasCharacter() {
        return aliasCharacter;
    }
    public void setAliasCharacter(String _aliasCharacter) {
        aliasCharacter = _aliasCharacter;
    }
    public String getAliasInteger() {
        return aliasInteger;
    }
    public void setAliasInteger(String _aliasInteger) {
        aliasInteger = _aliasInteger;
    }
    public String getAliasLong() {
        return aliasLong;
    }
    public void setAliasLong(String _aliasLong) {
        aliasLong = _aliasLong;
    }
    public String getAliasFloat() {
        return aliasFloat;
    }
    public void setAliasFloat(String _aliasFloat) {
        aliasFloat = _aliasFloat;
    }
    public String getAliasDouble() {
        return aliasDouble;
    }
    public void setAliasDouble(String _aliasDouble) {
        aliasDouble = _aliasDouble;
    }
    public String getAliasNumber() {
        return aliasNumber;
    }
    public void setAliasNumber(String _aliasNumber) {
        aliasNumber = _aliasNumber;
    }
    public String getAliasParseBoolean() {
        return aliasParseBoolean;
    }
    public void setAliasParseBoolean(String _aliasParseBoolean) {
        aliasParseBoolean = _aliasParseBoolean;
    }
    public String getAliasParseByte() {
        return aliasParseByte;
    }
    public void setAliasParseByte(String _aliasParseByte) {
        aliasParseByte = _aliasParseByte;
    }
    public String getAliasParseShort() {
        return aliasParseShort;
    }
    public void setAliasParseShort(String _aliasParseShort) {
        aliasParseShort = _aliasParseShort;
    }
    public String getAliasParseInt() {
        return aliasParseInt;
    }
    public void setAliasParseInt(String _aliasParseInt) {
        aliasParseInt = _aliasParseInt;
    }
    public String getAliasParseLong() {
        return aliasParseLong;
    }
    public void setAliasParseLong(String _aliasParseLong) {
        aliasParseLong = _aliasParseLong;
    }
    public String getAliasParseFloat() {
        return aliasParseFloat;
    }
    public void setAliasParseFloat(String _aliasParseFloat) {
        aliasParseFloat = _aliasParseFloat;
    }
    public String getAliasParseDouble() {
        return aliasParseDouble;
    }
    public void setAliasParseDouble(String _aliasParseDouble) {
        aliasParseDouble = _aliasParseDouble;
    }
    public String getAliasParseByteOrNull() {
        return aliasParseByteOrNull;
    }
    public void setAliasParseByteOrNull(String _aliasParseByte) {
        aliasParseByteOrNull = _aliasParseByte;
    }
    public String getAliasParseShortOrNull() {
        return aliasParseShortOrNull;
    }
    public void setAliasParseShortOrNull(String _aliasParseShort) {
        aliasParseShortOrNull = _aliasParseShort;
    }
    public String getAliasParseIntOrNull() {
        return aliasParseIntOrNull;
    }
    public void setAliasParseIntOrNull(String _aliasParseInt) {
        aliasParseIntOrNull = _aliasParseInt;
    }
    public String getAliasParseLongOrNull() {
        return aliasParseLongOrNull;
    }
    public void setAliasParseLongOrNull(String _aliasParseLong) {
        aliasParseLongOrNull = _aliasParseLong;
    }
    public String getAliasParseFloatOrNull() {
        return aliasParseFloatOrNull;
    }
    public void setAliasParseFloatOrNull(String _aliasParseFloat) {
        aliasParseFloatOrNull = _aliasParseFloat;
    }
    public String getAliasParseDoubleOrNull() {
        return aliasParseDoubleOrNull;
    }
    public void setAliasParseDoubleOrNull(String _aliasParseDouble) {
        aliasParseDoubleOrNull = _aliasParseDouble;
    }
    public String getAliasBooleanValue() {
        return aliasBooleanValue;
    }
    public void setAliasBooleanValue(String _aliasBooleanValue) {
        aliasBooleanValue = _aliasBooleanValue;
    }
    public String getAliasByteValue() {
        return aliasByteValue;
    }
    public void setAliasByteValue(String _aliasByteValue) {
        aliasByteValue = _aliasByteValue;
    }
    public String getAliasShortValue() {
        return aliasShortValue;
    }
    public void setAliasShortValue(String _aliasShortValue) {
        aliasShortValue = _aliasShortValue;
    }
    public String getAliasCharValue() {
        return aliasCharValue;
    }
    public void setAliasCharValue(String _aliasCharValue) {
        aliasCharValue = _aliasCharValue;
    }
    public String getAliasIntValue() {
        return aliasIntValue;
    }
    public void setAliasIntValue(String _aliasIntValue) {
        aliasIntValue = _aliasIntValue;
    }
    public String getAliasLongValue() {
        return aliasLongValue;
    }
    public void setAliasLongValue(String _aliasLongValue) {
        aliasLongValue = _aliasLongValue;
    }
    public String getAliasFloatValue() {
        return aliasFloatValue;
    }
    public void setAliasFloatValue(String _aliasFloatValue) {
        aliasFloatValue = _aliasFloatValue;
    }
    public String getAliasDoubleValue() {
        return aliasDoubleValue;
    }
    public void setAliasDoubleValue(String _aliasDoubleValue) {
        aliasDoubleValue = _aliasDoubleValue;
    }
    public String getAliasDigit() {
        return aliasDigit;
    }
    public void setAliasDigit(String _aliasDigit) {
        aliasDigit = _aliasDigit;
    }
    public String getAliasIsDigit() {
        return aliasIsDigit;
    }
    public void setAliasIsDigit(String _aliasIsDigit) {
        aliasIsDigit = _aliasIsDigit;
    }
    public String getAliasIsLetter() {
        return aliasIsLetter;
    }
    public void setAliasIsLetter(String _aliasIsLetter) {
        aliasIsLetter = _aliasIsLetter;
    }
    public String getAliasIsLetterOrDigit() {
        return aliasIsLetterOrDigit;
    }
    public void setAliasIsLetterOrDigit(String _aliasIsLetterOrDigit) {
        aliasIsLetterOrDigit = _aliasIsLetterOrDigit;
    }
    public String getAliasIsWordChar() {
        return aliasIsWordChar;
    }
    public void setAliasIsWordChar(String _aliasIsWordChar) {
        aliasIsWordChar = _aliasIsWordChar;
    }
    public String getAliasIsLowerCase() {
        return aliasIsLowerCase;
    }
    public void setAliasIsLowerCase(String _aliasIsLowerCase) {
        aliasIsLowerCase = _aliasIsLowerCase;
    }
    public String getAliasIsUpperCase() {
        return aliasIsUpperCase;
    }
    public void setAliasIsUpperCase(String _aliasIsUpperCase) {
        aliasIsUpperCase = _aliasIsUpperCase;
    }
    public String getAliasIsWhitespace() {
        return aliasIsWhitespace;
    }
    public void setAliasIsWhitespace(String _aliasIsWhitespace) {
        aliasIsWhitespace = _aliasIsWhitespace;
    }
    public String getAliasIsSpace() {
        return aliasIsSpace;
    }
    public void setAliasIsSpace(String _aliasIsSpace) {
        aliasIsSpace = _aliasIsSpace;
    }
    public String getAliasIsInfinite() {
        return aliasIsInfinite;
    }
    public void setAliasIsInfinite(String _aliasIsInfinite) {
        aliasIsInfinite = _aliasIsInfinite;
    }
    public String getAliasIsNan() {
        return aliasIsNan;
    }
    public void setAliasIsNan(String _aliasIsNan) {
        aliasIsNan = _aliasIsNan;
    }
    public String getAliasForDigit() {
        return aliasForDigit;
    }
    public void setAliasForDigit(String _aliasForDigit) {
        aliasForDigit = _aliasForDigit;
    }
    public String getAliasGetDirectionality() {
        return aliasGetDirectionality;
    }
    public void setAliasGetDirectionality(String _aliasGetDirectionality) {
        aliasGetDirectionality = _aliasGetDirectionality;
    }
    public String getAliasGetCharType() {
        return aliasGetCharType;
    }
    public void setAliasGetCharType(String _aliasGetType) {
        aliasGetCharType = _aliasGetType;
    }

    public String getAliasToLowerCaseChar() {
        return aliasToLowerCaseChar;
    }

    public void setAliasToLowerCaseChar(String _aliasToLowerCaseChar) {
        this.aliasToLowerCaseChar = _aliasToLowerCaseChar;
    }

    public String getAliasToUpperCaseChar() {
        return aliasToUpperCaseChar;
    }

    public void setAliasToUpperCaseChar(String _aliasToUpperCaseChar) {
        this.aliasToUpperCaseChar = _aliasToUpperCaseChar;
    }

    public AliasParamNumber getParams() {
        return params;
    }
}
