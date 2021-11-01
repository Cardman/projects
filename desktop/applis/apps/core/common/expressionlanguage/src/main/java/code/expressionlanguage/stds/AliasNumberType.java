package code.expressionlanguage.stds;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.fcts.*;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.expressionlanguage.structs.ErrorStruct;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class AliasNumberType {
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

    public static ErrorStruct getFormatError(ContextEl _context, String _text, StackCall _stackCall) {
        return new ErrorStruct(_context, _text, _context.getStandards().getContent().getCoreNames().getAliasNbFormat(), _stackCall);
    }

    public static String getNumberRadixMessage(String _nb, int _radix) {
        return StringUtil.concat(_nb, ",", Long.toString(_radix));
    }

    public void build(LgNames _lgNames) {
        CustList<CstFieldInfo> fields_;
        StringList params_;
        StandardMethod method_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardType std_;
        StandardClass stdcl_;
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<CstFieldInfo>();
        constructors_ = new CustList<StandardConstructor>();
        String aliasObject_ = _lgNames.getContent().getCoreNames().getAliasObject();
        String aliasPrimBoolean_ = _lgNames.getContent().getPrimTypes().getAliasPrimBoolean();
        String aliasPrimByte_ = _lgNames.getContent().getPrimTypes().getAliasPrimByte();
        String aliasPrimShort_ = _lgNames.getContent().getPrimTypes().getAliasPrimShort();
        String aliasPrimChar_ = _lgNames.getContent().getPrimTypes().getAliasPrimChar();
        String aliasPrimInteger_ = _lgNames.getContent().getPrimTypes().getAliasPrimInteger();
        String aliasPrimLong_ = _lgNames.getContent().getPrimTypes().getAliasPrimLong();
        String aliasPrimFloat_ = _lgNames.getContent().getPrimTypes().getAliasPrimFloat();
        String aliasPrimDouble_ = _lgNames.getContent().getPrimTypes().getAliasPrimDouble();
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        std_ = new StandardClass(aliasBoolean, fields_, constructors_, methods_, aliasObject_ , MethodModifier.FINAL, new DfBool());
        params_ = new StringList();
        method_ = new StandardMethod(aliasBooleanValue, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,new FctNbIdInst());
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
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<CstFieldInfo>();
        std_ = new StandardClass(aliasByte, fields_, constructors_, methods_, aliasShort, MethodModifier.FINAL, new DfNb(aliasByte));
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
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<CstFieldInfo>();
        std_ = new StandardClass(aliasShort, fields_, constructors_, methods_, aliasInteger, MethodModifier.FINAL, new DfNb(aliasShort));
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
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<CstFieldInfo>();
        std_ = new StandardClass(aliasInteger, fields_, constructors_, methods_, aliasLong, MethodModifier.FINAL, new DfNb(aliasInteger));
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
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<CstFieldInfo>();
        std_ = new StandardClass(aliasLong, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL, new DfNb(aliasLong));
        numbersConstructors(_lgNames,constructors_, aliasPrimLong_,new StringList(params.getAliasLong0Long0()),new StringList(params.getAliasLong1Long0()), new FctLong(new DefRadix()));
        methods_.add(addToStrMeth(_lgNames, aliasPrimLong_, new StringList(params.getAliasLong0ToStringMethod0()), new FctNbRelToStr0()));
        methods_.addAllElts(addParserMaths(_lgNames, aliasLong, aliasParseLong, new StringList(params.getAliasLong0ParseLong0()), new StringList(params.getAliasLong1ParseLong0(), params.getAliasLong1ParseLong1()), new FctLong(new DefRadix()), new FctLong(new ArgRadix())));
        methods_.addAllElts(addCompare(_lgNames, aliasPrimLong_, aliasLong, new StringList(params.getAliasLong0CompareTo0()), new StringList(params.getAliasLong0Compare0(),params.getAliasLong0Compare1()), new FctNbCompareToSpecRel(), new FctNbCompareSpecRel()));
        methods_.add(addSpecBaseStr(aliasToBinString, _lgNames, aliasPrimLong_, new StringList(params.getAliasLong0ToBinString0()),new FctLongStrBin()));
        methods_.add(addSpecBaseStr(aliasToOctString, _lgNames, aliasPrimLong_, new StringList(params.getAliasLong0ToOctString0()),new FctLongStrOct()));
        methods_.add(addSpecBaseStr(aliasToHexString, _lgNames, aliasPrimLong_, new StringList(params.getAliasLong0ToHexString0()),new FctLongStrHex()));
        methods_.addAllElts(addParserMaths(_lgNames, aliasLong, aliasParseLongOrNull, new StringList(params.getAliasLong0ParseLongOrNull0()), new StringList(params.getAliasLong1ParseLongOrNull0(),params.getAliasLong1ParseLongOrNull1()), new FctLongSafe(new DefRadix()), new FctLongSafe(new ArgRadix())));
        numbersValuesFields(fields_, aliasPrimLong_);
        params_ = new StringList(aliasPrimLong_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasToStringMethod, params_, _lgNames.getContent().getCharSeq().getAliasString(), false, MethodModifier.STATIC,new StringList(params.getAliasLong1ToStringMethod0(),params.getAliasLong1ToStringMethod1()), new FctNbRelToStr1());
        methods_.add( method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasSignum, params_, aliasPrimByte_, false, MethodModifier.STATIC,new StringList(params.getAliasLong0Signum0()),new FctNbRelSgn());
        methods_.add( method_);
        standards_.addEntry(aliasLong, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<CstFieldInfo>();
        std_ = new StandardClass(aliasFloat, fields_, constructors_, methods_, aliasDouble, MethodModifier.FINAL, new DfNb(aliasFloat));
        numbersConstructors(_lgNames,constructors_, aliasPrimFloat_,new StringList(params.getAliasFloat0Float0()),new StringList(params.getAliasFloat1Float0()), new FctFloat());
        methods_.add(addToStrMeth(_lgNames, aliasPrimFloat_, new StringList(params.getAliasFloat0ToStringMethod0()), new FctNbFloatToStr()));
        methods_.add(dbParser(_lgNames, aliasFloat, aliasParseFloat, new StringList(params.getAliasFloat0ParseFloat0()), new FctFloat()));
        methods_.addAllElts(addCompare(_lgNames, aliasPrimFloat_, aliasFloat, new StringList(params.getAliasFloat0CompareTo0()), new StringList(params.getAliasFloat0Compare0(),params.getAliasFloat0Compare1()), new FctNbCompareToSpecReal(), new FctNbCompareSpecReal()));
        numbersSafeParsersMethods(_lgNames,methods_, aliasFloat, aliasParseFloatOrNull, new StringList(params.getAliasFloat0ParseFloatOrNull0()), new FctFloatSafe());
        numbersDotValuesFields(fields_, aliasPrimFloat_);
        standards_.addEntry(aliasFloat, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new CustList<StandardMethod>();
        fields_ = new CustList<CstFieldInfo>();
        std_ = new StandardClass(aliasDouble, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL, new DfNb(aliasDouble));
        numbersConstructors(_lgNames,constructors_, aliasPrimDouble_,new StringList(params.getAliasDouble0Double0()),new StringList(params.getAliasDouble1Double0()), new FctDouble());
        methods_.add(addToStrMeth(_lgNames, aliasPrimDouble_, new StringList(params.getAliasDouble0ToStringMethod0()), new FctNbDoubleToStr()));
        methods_.add(dbParser(_lgNames, aliasDouble, aliasParseDouble, new StringList(params.getAliasDouble0ParseDouble0()), new FctDouble()));
        methods_.addAllElts(addCompare(_lgNames, aliasPrimDouble_, aliasDouble, new StringList(params.getAliasDouble0CompareTo0()), new StringList(params.getAliasDouble0Compare0(),params.getAliasDouble0Compare1()), new FctNbCompareToSpecReal(), new FctNbCompareSpecReal()));
        numbersSafeParsersMethods(_lgNames,methods_, aliasDouble, aliasParseDoubleOrNull,new StringList(params.getAliasDouble0ParseDoubleOrNull0()), new FctDoubleSafe());
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, new FctDoubleIsInfinite0());
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
        standards_.addEntry(aliasDouble, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        std_ = new StandardClass(aliasNumber, fields_, constructors_, methods_, aliasObject_, StdClassModifier.ABSTRACT);
        numbersAbsMethods(_lgNames,methods_, aliasNumber);
        standards_.addEntry(aliasNumber, std_);
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        stdcl_ = new StandardClass(aliasCharacter, fields_, constructors_, methods_, aliasInteger, MethodModifier.FINAL, new DfNb(aliasCharacter));
        params_ = new StringList();
        method_ = new StandardMethod(aliasCharValue, params_, aliasPrimChar_, false, MethodModifier.NORMAL, new FctNbIdInst());
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
        ctor_ = new StandardConstructor(params_, false,new StringList(params.getAliasCharacter0Character0()),new FctNbId());
        constructors_.add(ctor_);
        numbersValuesFields(fields_, aliasPrimChar_);
        std_ = stdcl_;
        standards_.addEntry(aliasCharacter, std_);
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
