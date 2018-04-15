package code.expressionlanguage.stds;

import java.io.UnsupportedEncodingException;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustBase;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.NumberInfos;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.AssignableFrom;
import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.ByteStruct;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.DimComp;
import code.expressionlanguage.opers.util.DoubleStruct;
import code.expressionlanguage.opers.util.FloatStruct;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.LongStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.ReplacementStruct;
import code.expressionlanguage.opers.util.ShortStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.StringBuilderStruct;
import code.expressionlanguage.opers.util.StringStruct;
import code.expressionlanguage.opers.util.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.Replacement;
import code.util.SimpleItr;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.ComparatorBoolean;
import code.util.ints.Countable;
import code.util.ints.Displayable;
import code.util.ints.SimpleIterable;
import code.util.pagination.SelectedBoolean;

public class LgNames {
    protected static final int DEFAULT_RADIX = 10;
    protected static final String EMPTY_STRING = "";
    protected static final String RETURN_LINE = "\n";
    protected static final String SPACE = " ";

    protected static final char PAR_LEFT = '(';
    protected static final char SEP_ARG = ',';
    protected static final char PAR_RIGHT = ')';
    protected static final String DOT = ".";
    protected static final String VARARG_SUFFIX = "...";
    private static final byte HEX_BASE = 16;
    private static final char DOT_VAR = '.';
    private static final char EXP = 'e';
    private static final char MINUS_CHAR = '-';

    private static final long MULTMIN_RADIX_TEN = Long.MIN_VALUE / DEFAULT_RADIX;
    private static final long N_MULTMAX_RADIX_TEN = -Long.MAX_VALUE / DEFAULT_RADIX;
    private static final byte THREE_ARGS = 3;
    private static final byte FOUR_ARGS = 4;
    private static final byte MAX_DIGITS_DOUBLE = 18;
    private ContextEl context;
    private String aliasObject;
    private String aliasVoid;
    private String aliasCharSequence;
    private String aliasDisplayable;
    private String aliasCompareTo;
    private String aliasCompare;
    private String aliasEquals;
    private String aliasToString;
    private String aliasAbs;
    private String aliasQuot;
    private String aliasMod;
    private String aliasValueOf;
    private String aliasMaxValueField;
    private String aliasMinValueField;

    private String aliasIteratorType;
    private String aliasIterator;
    private String aliasIterable;
    private String aliasEnumParam;
    private String aliasEnum;
    private String aliasReplacement;

    private String aliasError;
    private String aliasCustomError;
    private String aliasBadSize;
    private String aliasDivisionZero;
    private String aliasCast;
    private String aliasStore;
    private String aliasNullPe;
    private String aliasNbFormat;
    private String aliasBadEncode;
    private String aliasBadIndex;
    private String aliasSof;

    private String aliasPrimBoolean;
    private String aliasPrimByte;
    private String aliasPrimShort;
    private String aliasPrimChar;
    private String aliasPrimInteger;
    private String aliasPrimLong;
    private String aliasPrimFloat;
    private String aliasPrimDouble;
    private String aliasMath;
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
    private String aliasGetType;

    private String aliasString;
    private String aliasLength;
    private String aliasDisplay;
    private String aliasCharAt;
    private String aliasToCharArray;
    private String aliasSplit;
    private String aliasSplitStrings;
    private String aliasSplitChars;
    private String aliasReplace;
    private String aliasReplaceMultiple;
    private String aliasEqualsIgnoreCase;
    private String aliasCompareToIgnoreCase;
    private String aliasContains;
    private String aliasEndsWith;
    private String aliasStartsWith;
    private String aliasIndexOf;
    private String aliasFormat;
    private String aliasGetBytes;
    private String aliasIntern;
    private String aliasIsEmpty;
    private String aliasLastIndexOf;
    private String aliasRegionMatches;
    private String aliasSubstring;
    private String aliasSubSequence;
    private String aliasToLowerCase;
    private String aliasToUpperCase;
    private String aliasTrim;
    private String aliasStringBuilder;
    private String aliasAppend;
    private String aliasCapacity;
    private String aliasClear;
    private String aliasDelete;
    private String aliasDeleteCharAt;
    private String aliasEnsureCapacity;
    private String aliasInsert;
    private String aliasReverse;
    private String aliasSetCharAt;
    private String aliasSetLength;
    private String aliasTrimToSize;
    private String aliasCountable;
    private String aliasGet;
    private String aliasSize;
    private String aliasSimpleIterator;
    private String aliasNext;
    private String aliasHasNext;
    private String aliasName;
    private String aliasOrdinal;
    private String aliasGetOldString;
    private String aliasGetNewString;
    private String aliasSetOldString;
    private String aliasSetNewString;
    private String aliasSimpleIteratorType;
    private String aliasSimpleIterableType;

    private String selectedBoolean = "$sb";

    private StringMap<StandardType> standards = new StringMap<StandardType>();

    /**Called after setters*/
    public void build() {
        StringMap<StandardField> fields_;
        StringList noTypes_ = new StringList();
        StringList params_;
        StandardMethod method_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        StandardType std_;
        StandardClass stdcl_;
        stdcl_ = new StandardClass(aliasObject, fields_, constructors_, methods_, EMPTY_STRING, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasObject, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasError, fields_, constructors_, methods_, aliasObject, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasError, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasNullPe, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasNullPe, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasDivisionZero, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasDivisionZero, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasCast, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasCast, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasStore, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasStore, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasBadSize, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasBadSize, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasNbFormat, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasNbFormat, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasBadIndex, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasBadIndex, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasSof, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasSof, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasBadEncode, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasBadEncode, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasCustomError, fields_, constructors_, methods_, aliasObject, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasCustomError, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardInterface(aliasCharSequence, methods_, noTypes_);
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasCharAt, params_, aliasPrimChar, false, MethodModifier.ABSTRACT,std_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasLength, noTypes_, aliasPrimInteger, false, MethodModifier.ABSTRACT,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasSubSequence, params_, aliasCharSequence, false, MethodModifier.ABSTRACT,std_);
        methods_.put(method_.getId(), method_);
        standards.put(aliasCharSequence, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardInterface(aliasDisplayable, methods_, noTypes_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDisplay, params_, aliasString, false, MethodModifier.ABSTRACT,std_);
        methods_.put(method_.getId(), method_);
        standards.put(aliasDisplayable, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardInterface(aliasSimpleIterableType, methods_, noTypes_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSimpleIterator, params_, aliasSimpleIteratorType, false, MethodModifier.ABSTRACT, std_);
        methods_.put(method_.getId(), method_);
        standards.put(aliasSimpleIterableType, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardInterface(aliasCountable, methods_, noTypes_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasSize, params_, aliasPrimInteger, false, MethodModifier.ABSTRACT,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsEmpty, params_, aliasPrimBoolean, false, MethodModifier.ABSTRACT,std_);
        methods_.put(method_.getId(), method_);
        standards.put(aliasCountable, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass(aliasBoolean, fields_, constructors_, methods_, aliasObject, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasBooleanValue, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean, aliasPrimBoolean);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasBoolean);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasBoolean);
        method_ = new StandardMethod(aliasEquals, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasParseBoolean, params_, aliasPrimBoolean, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToString, params_, aliasString, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean);
        method_ = new StandardMethod(aliasToString, params_, aliasString, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean);
        method_ = new StandardMethod(aliasValueOf, params_, aliasBoolean, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasValueOf, params_, aliasBoolean, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        StandardConstructor ctor_;
        params_ = new StringList(aliasString);
        ctor_ = new StandardConstructor(params_,false,std_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasBoolean);
        ctor_ = new StandardConstructor(params_,false, std_);
        constructors_.add(ctor_);
        standards.put(aliasBoolean, std_);
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(aliasMath, fields_, constructors_, methods_, aliasObject, MethodModifier.FINAL);
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasAbs, params_, aliasPrimInteger, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong);
        method_ = new StandardMethod(aliasAbs, params_, aliasPrimLong, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasQuot, params_, aliasPrimInteger, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong,aliasPrimLong);
        method_ = new StandardMethod(aliasQuot, params_, aliasPrimLong, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasMod, params_, aliasPrimInteger, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong,aliasPrimLong);
        method_ = new StandardMethod(aliasMod, params_, aliasPrimLong, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        standards.put(aliasMath, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        std_ = new StandardClass(aliasByte, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL);
        numbersConstructors(constructors_, aliasPrimByte, std_);
        numbersValuesMethods(methods_, aliasByte, aliasParseByte, aliasPrimByte, std_);
        numbersValuesFields(fields_, aliasPrimByte, std_);
        standards.put(aliasByte, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        std_ = new StandardClass(aliasShort, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL);
        numbersConstructors(constructors_, aliasPrimShort, std_);
        numbersValuesMethods(methods_, aliasShort, aliasParseShort, aliasPrimShort, std_);
        numbersValuesFields(fields_, aliasPrimShort, std_);
        standards.put(aliasShort, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        std_ = new StandardClass(aliasInteger, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL);
        numbersConstructors(constructors_, aliasPrimInteger, std_);
        numbersValuesMethods(methods_, aliasInteger, aliasParseInt, aliasPrimInteger, std_);
        numbersValuesFields(fields_, aliasPrimInteger, std_);
        standards.put(aliasInteger, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        std_ = new StandardClass(aliasLong, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL);
        numbersConstructors(constructors_, aliasPrimLong, std_);
        numbersValuesMethods(methods_, aliasLong, aliasParseLong, aliasPrimLong, std_);
        numbersValuesFields(fields_, aliasPrimLong, std_);
        standards.put(aliasLong, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        std_ = new StandardClass(aliasFloat, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL);
        numbersConstructors(constructors_, aliasPrimFloat, std_);
        numbersValuesMethods(methods_, aliasFloat, aliasParseFloat, aliasPrimFloat, std_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat);
        method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsNan, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat);
        method_ = new StandardMethod(aliasIsNan, params_, aliasPrimBoolean, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        numbersValuesFields(fields_, aliasPrimFloat, std_);
        standards.put(aliasFloat, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        std_ = new StandardClass(aliasDouble, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL);
        numbersConstructors(constructors_, aliasPrimDouble, std_);
        numbersValuesMethods(methods_, aliasDouble, aliasParseDouble, aliasPrimDouble, std_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble);
        method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsNan, params_, aliasPrimBoolean, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble);
        method_ = new StandardMethod(aliasIsNan, params_, aliasPrimBoolean, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        numbersValuesFields(fields_, aliasPrimDouble, std_);
        standards.put(aliasDouble, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        std_ = new StandardClass(aliasNumber, fields_, constructors_, methods_, aliasObject, MethodModifier.ABSTRACT);
        numbersAbsMethods(methods_, aliasNumber,std_);
        standards.put(aliasNumber, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasCharacter, fields_, constructors_, methods_, aliasObject, MethodModifier.FINAL);
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasCharAt, params_, aliasPrimChar, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasLength, noTypes_, aliasPrimInteger, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasSubSequence, params_, aliasCharacter, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCharValue, params_, aliasPrimChar, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharacter);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar, aliasPrimChar);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar, aliasPrimInteger);
        method_ = new StandardMethod(aliasDigit, params_, aliasPrimInteger, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasPrimInteger);
        method_ = new StandardMethod(aliasForDigit, params_, aliasPrimChar, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasGetDirectionality, params_, aliasPrimByte, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasGetType, params_, aliasPrimByte, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIsDigit, params_, aliasPrimBoolean, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIsLetter, params_, aliasPrimBoolean, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIsLetterOrDigit, params_, aliasPrimBoolean, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIsWordChar, params_, aliasPrimBoolean, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIsWhitespace, params_, aliasPrimBoolean, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIsLowerCase, params_, aliasPrimBoolean, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIsUpperCase, params_, aliasPrimBoolean, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIsSpace, params_, aliasPrimBoolean, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasToLowerCase, params_, aliasPrimChar, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasToUpperCase, params_, aliasPrimChar, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasToString, params_, aliasString, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToString, params_, aliasString, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        ctor_ = new StandardConstructor(params_, false, stdcl_);
        constructors_.add(ctor_);
        numbersValuesFields(fields_, aliasPrimChar, stdcl_);
        std_ = stdcl_;
        standards.put(aliasCharacter, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasString, fields_, constructors_, methods_, aliasObject, MethodModifier.FINAL);
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasCharAt, params_, aliasPrimChar, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasLength, noTypes_, aliasPrimInteger, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasSubSequence, params_, aliasString, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasSubstring, params_, aliasString, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasSubstring, params_, aliasString, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasEqualsIgnoreCase, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasCompareToIgnoreCase, params_, aliasPrimInteger, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasContains, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasStartsWith, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString, aliasPrimInteger);
        method_ = new StandardMethod(aliasStartsWith, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasEndsWith, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString, aliasPrimInteger);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar, aliasPrimInteger);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString, aliasPrimInteger);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar, aliasPrimInteger);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsEmpty, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToCharArray, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar), false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetBytes, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasPrimByte), false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasGetBytes, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasPrimByte), false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasFormat, params_, aliasString, true, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasSplit, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString, aliasPrimInteger);
        method_ = new StandardMethod(aliasSplit, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasSplit, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar, aliasPrimInteger);
        method_ = new StandardMethod(aliasSplit, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasSplitStrings, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), true, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasString);
        method_ = new StandardMethod(aliasSplitStrings, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), true, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasSplitChars, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), true, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString, aliasString);
        method_ = new StandardMethod(aliasReplace, params_, aliasString, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar, aliasPrimChar);
        method_ = new StandardMethod(aliasReplace, params_, aliasString, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasReplacement);
        method_ = new StandardMethod(aliasReplaceMultiple, params_, aliasString, true, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasString, aliasPrimInteger, aliasPrimInteger);
        method_ = new StandardMethod(aliasRegionMatches, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean, aliasPrimInteger, aliasString, aliasPrimInteger, aliasPrimInteger);
        method_ = new StandardMethod(aliasRegionMatches, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToLowerCase, params_, aliasString, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToUpperCase, params_, aliasString, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTrim, params_, aliasString, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIntern, params_, aliasString, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean);
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimByte);
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimShort);
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong);
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat);
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble);
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar));
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar),aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_, false, stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimByte));
        ctor_ = new StandardConstructor(params_, false, stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimByte), aliasString);
        ctor_ = new StandardConstructor(params_, false, stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimByte), aliasPrimInteger, aliasPrimInteger);
        ctor_ = new StandardConstructor(params_, false, stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimByte), aliasPrimInteger, aliasPrimInteger, aliasString);
        ctor_ = new StandardConstructor(params_, false, stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar));
        ctor_ = new StandardConstructor(params_, false, stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar), aliasPrimInteger, aliasPrimInteger);
        ctor_ = new StandardConstructor(params_, false, stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasStringBuilder);
        ctor_ = new StandardConstructor(params_, false, stdcl_);
        constructors_.add(ctor_);
        stdcl_.getDirectInterfaces().add(aliasCharSequence);
        std_ = stdcl_;
        standards.put(aliasString, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasReplacement, fields_, constructors_, methods_, aliasObject, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetOldString, params_, aliasString, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetNewString, params_, aliasString, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasSetOldString, params_, aliasVoid, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasSetNewString, params_, aliasVoid, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        standards.put(aliasReplacement, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasStringBuilder, fields_, constructors_, methods_, aliasObject, MethodModifier.FINAL);
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasCharAt, params_, aliasPrimChar, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasLength, noTypes_, aliasPrimInteger, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasSubSequence, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimByte);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimShort);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString,aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasStringBuilder);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasStringBuilder,aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar));
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar),aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCapacity, params_, aliasPrimInteger, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasDelete, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsEmpty, params_, aliasPrimBoolean, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasDeleteCharAt, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasClear, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString, aliasPrimInteger);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar, aliasPrimInteger);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString, aliasPrimInteger);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar, aliasPrimInteger);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasPrimBoolean);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasPrimByte);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasPrimShort);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasPrimChar);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasPrimInteger);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasPrimLong);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasPrimFloat);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasPrimDouble);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasString);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasString,aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasStringBuilder);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasStringBuilder,aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar));
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar),aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasReverse, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger,aliasPrimInteger,aliasString);
        method_ = new StandardMethod(aliasReplace, params_, aliasStringBuilder, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger,aliasPrimChar);
        method_ = new StandardMethod(aliasSetCharAt, params_, aliasVoid, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasSetLength, params_, aliasVoid, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToString, params_, aliasString, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTrimToSize, params_, aliasVoid, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasEnsureCapacity, params_, aliasVoid, false, MethodModifier.NORMAL,stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_, false, stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasStringBuilder);
        ctor_ = new StandardConstructor(params_, false, stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasPrimInteger);
        ctor_ = new StandardConstructor(params_, false, stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasString);
        ctor_ = new StandardConstructor(params_, false, stdcl_);
        constructors_.add(ctor_);
        stdcl_.getDirectInterfaces().add(aliasCharSequence);
        std_ = stdcl_;
        standards.put(aliasStringBuilder, std_);
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        stdcl_ = new StandardClass(aliasSimpleIteratorType, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(getAliasNext(), params_, getAliasObject(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(getAliasHasNext(), params_, getAliasPrimBoolean(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        standards.put(aliasSimpleIteratorType, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(selectedBoolean, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        getStandards().put(selectedBoolean, stdcl_);
        buildOther();
    }
    public void setupOverrides(ContextEl _cont) {
        StringList keys_ = standards.getKeys();
        TypeUtil.buildInherits(_cont, keys_, keys_);
        for (StandardType t: standards.values()) {
            TypeUtil.buildOverrides(t, _cont);
        }
    }

    private void numbersConstructors(CustList<StandardConstructor> _ctors, String _primitive, StandardType _type) {
        StringList params_;
        StandardConstructor ctor_;
        params_ = new StringList(aliasString);
        ctor_ = new StandardConstructor(params_,false, _type);
        _ctors.add(ctor_);
        params_ = new StringList(_primitive);
        ctor_ = new StandardConstructor(params_,false, _type);
        _ctors.add(ctor_);
    }
    private void numbersValuesFields(StringMap<StandardField> _fields, String _primitive, StandardType _type) {
        StandardField field_ = new StandardField(aliasMinValueField, _primitive, true, true, _type);
        _fields.put(aliasMinValueField, field_);
        field_ = new StandardField(aliasMaxValueField, _primitive, true, true, _type);
        _fields.put(aliasMaxValueField, field_);
    }
    private void numbersValuesMethods(ObjectMap<MethodId, StandardMethod> _methods, String _owner, String _parserName, String _primitive, StandardType _type) {
        StringList params_;
        StandardMethod method_;
        params_ = new StringList();
        method_ = new StandardMethod(aliasByteValue, params_, aliasPrimByte, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasByteValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasShortValue, params_, aliasPrimShort, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasShortValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIntValue, params_, aliasPrimInteger, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasIntValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasLongValue, params_, aliasPrimLong, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasLongValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFloatValue, params_, aliasPrimFloat, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasFloatValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDoubleValue, params_, aliasPrimDouble, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasDoubleValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToString, params_, aliasString, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasToString, params_), method_);
        params_ = new StringList(_primitive);
        method_ = new StandardMethod(aliasToString, params_, aliasString, false, MethodModifier.STATIC, _type);
        _methods.put(new MethodId(MethodModifier.STATIC, aliasToString, params_), method_);
        params_ = new StringList(aliasNumber);
        method_ = new StandardMethod(aliasEquals, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasEquals, params_), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(_parserName, params_, _owner, false, MethodModifier.STATIC, _type);
        _methods.put(new MethodId(MethodModifier.STATIC, _parserName, params_), method_);
        params_ = new StringList(aliasString, aliasPrimInteger);
        method_ = new StandardMethod(_parserName, params_, _owner, false, MethodModifier.STATIC, _type);
        _methods.put(new MethodId(MethodModifier.STATIC, _parserName, params_), method_);
        params_ = new StringList(_owner);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasCompareTo, params_), method_);
        params_ = new StringList(_primitive, _primitive);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger, false, MethodModifier.STATIC, _type);
        _methods.put(new MethodId(MethodModifier.STATIC, aliasCompare, params_), method_);
    }
    private void numbersAbsMethods(ObjectMap<MethodId, StandardMethod> _methods, String _owner, StandardType _type) {
        StringList params_;
        StandardMethod method_;
        params_ = new StringList();
        method_ = new StandardMethod(aliasByteValue, params_, aliasPrimByte, false, MethodModifier.ABSTRACT, _type);
        _methods.put(new MethodId(MethodModifier.ABSTRACT, aliasByteValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasShortValue, params_, aliasPrimShort, false, MethodModifier.ABSTRACT, _type);
        _methods.put(new MethodId(MethodModifier.ABSTRACT, aliasShortValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIntValue, params_, aliasPrimInteger, false, MethodModifier.ABSTRACT, _type);
        _methods.put(new MethodId(MethodModifier.ABSTRACT, aliasIntValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasLongValue, params_, aliasPrimLong, false, MethodModifier.ABSTRACT, _type);
        _methods.put(new MethodId(MethodModifier.ABSTRACT, aliasLongValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFloatValue, params_, aliasPrimFloat, false, MethodModifier.ABSTRACT, _type);
        _methods.put(new MethodId(MethodModifier.ABSTRACT, aliasFloatValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDoubleValue, params_, aliasPrimDouble, false, MethodModifier.ABSTRACT, _type);
        _methods.put(new MethodId(MethodModifier.ABSTRACT, aliasDoubleValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToString, params_, aliasString, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasToString, params_), method_);
        params_ = new StringList(_owner);
        method_ = new StandardMethod(aliasToString, params_, aliasString, false, MethodModifier.STATIC, _type);
        _methods.put(new MethodId(MethodModifier.STATIC, aliasToString, params_), method_);
        params_ = new StringList(aliasNumber);
        method_ = new StandardMethod(aliasEquals, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasEquals, params_), method_);
        params_ = new StringList(_owner);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasCompareTo, params_), method_);
        params_ = new StringList(_owner, _owner);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger, false, MethodModifier.STATIC, _type);
        _methods.put(new MethodId(MethodModifier.STATIC, aliasCompareTo, params_), method_);
    }
    public static boolean canBeUseAsArgument(String _param, String _arg, LgNames _context) {
        String aliasVoid_ = _context.getAliasVoid();
        if (StringList.quickEq(_param, aliasVoid_)) {
            return false;
        }
        ClassArgumentMatching param_ = new ClassArgumentMatching(_param);
        if (_arg == null) {
            if (param_.isPrimitive(_context)) {
                return false;
            }
            return true;
        }
        if (StringList.quickEq(_arg, aliasVoid_)) {
            return false;
        }
        AssignableFrom a_ = isAssignableFromCust(_param, _arg, _context);
        if (a_ == AssignableFrom.YES) {
            return true;
        }
        if (a_ == AssignableFrom.NO) {
            return false;
        }

        ClassArgumentMatching arg_ = new ClassArgumentMatching(_arg);
        DimComp paramComp_ = PrimitiveTypeUtil.getQuickComponentBaseType(param_.getName());
        DimComp argComp_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arg);
        String objAlias_ = _context.getAliasObject();
        if (StringList.quickEq(paramComp_.getComponent(), objAlias_)) {
            if (paramComp_.getDim() > argComp_.getDim()) {
                return false;
            }
            return true;
        }
        if (paramComp_.getDim() != argComp_.getDim()) {
            return false;
        }
        boolean array_ = paramComp_.getDim() > 0;
        param_ = new ClassArgumentMatching(paramComp_.getComponent());
        arg_ = new ClassArgumentMatching(argComp_.getComponent());
        if (StringList.quickEq(param_.getName(),arg_.getName())) {
            return true;
        }
        String aliasPrimBool_ = _context.getAliasPrimBoolean();
        String aliasNumber_ = _context.getAliasNumber();
        String typeNameArg_ = PrimitiveTypeUtil.toPrimitive(arg_, true, _context).getName();
        if (StringList.quickEq(typeNameArg_, aliasPrimBool_)) {
            String typeNameParam_ = PrimitiveTypeUtil.toPrimitive(param_, true, _context).getName();
            if (!StringList.quickEq(typeNameParam_, aliasPrimBool_)) {
                return false;
            }
            return true;
        }
        ClassArgumentMatching clMatch_ = PrimitiveTypeUtil.toPrimitive(arg_, true, _context);
        if (clMatch_.isPrimitive(_context)) {
            if (arg_.isPrimitive(_context)) {
                CustList<ClassArgumentMatching> gt_ = PrimitiveTypeUtil.getOrdersGreaterEqThan(clMatch_, _context);
                if (PrimitiveTypeUtil.isPureNumberClass(clMatch_, _context) && StringList.quickEq(_param, aliasNumber_)) {
                    return true;
                }
                ClassArgumentMatching prim_ = PrimitiveTypeUtil.toPrimitive(param_, true, _context);
                boolean contained_ = false;
                for (ClassArgumentMatching c: gt_) {
                    if (StringList.quickEq(c.getName(), prim_.getName())) {
                        contained_ = true;
                        break;
                    }
                }
                if (!contained_) {
                    return false;
                }
                return true;
            }
            if (!param_.isPrimitive(_context)) {
                return false;
            }
            if (!array_) {
                CustList<ClassArgumentMatching> gt_ = PrimitiveTypeUtil.getOrdersGreaterEqThan(clMatch_, _context);
                ClassArgumentMatching prim_ = param_;
                boolean contained_ = false;
                for (ClassArgumentMatching c: gt_) {
                    if (StringList.quickEq(c.getName(), prim_.getName())) {
                        contained_ = true;
                        break;
                    }
                }
                if (!contained_) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    static AssignableFrom isAssignableFromCust(String _param,String _arg, LgNames _context) {
        String aliasObject_ = _context.getAliasObject();
        if (StringList.quickEq(_param, aliasObject_)) {
            return AssignableFrom.YES;
        }
        DimComp dPar_ = PrimitiveTypeUtil.getQuickComponentBaseType(_param);
        String p_ = dPar_.getComponent();
        StandardType clParBl_ = _context.getStandards().getVal(p_);
        DimComp dArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arg);
        String a_ = dArg_.getComponent();
        StandardType clArgBl_ = _context.getStandards().getVal(a_);
        if (clArgBl_ != null) {
            if (clParBl_ != null) {
                if (dArg_.getDim() > 0 && dPar_.getDim() > 0) {
                    if (isArrayAssignable(_arg, _param,_context)) {
                        return AssignableFrom.YES;
                    }
                    return AssignableFrom.NO;
                }
                if (dArg_.getDim() != dPar_.getDim()) {
                    return AssignableFrom.NO;
                }
                String className_ = dPar_.getComponent();
                if (StringList.quickEq(className_, a_)) {
                    return AssignableFrom.YES;
                }
                if (clArgBl_.getAllSuperTypes(_context).containsObj(className_)) {
                    return AssignableFrom.YES;
                }
                return AssignableFrom.NO;
            }
        }
        return AssignableFrom.MAYBE;
    }
    static boolean isArrayAssignable(String _arrArg, String _arrParam, LgNames _context) {
        String aliasObject_ = _context.getAliasObject();
        DimComp dArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arrArg);
        String a_ = dArg_.getComponent();
        DimComp dPar_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arrParam);
        String className_ = dPar_.getComponent();
        if (StringList.quickEq(className_, aliasObject_)) {
            if (dPar_.getDim() > dArg_.getDim()) {
                return false;
            }
            return true;
        }
        if (dPar_.getDim() != dArg_.getDim()) {
            return false;
        }
        StandardType clArgBl_ = _context.getStandards().getVal(a_);
        if (clArgBl_.getAllSuperTypes(_context).containsObj(className_)) {
            return true;
        }
        if (StringList.quickEq(className_, a_)) {
            return true;
        }
        return false;
    }
    public void buildOther() {
    }
    public static ResultErrorStd invokeMethod(ContextEl _cont, boolean _natvararg, ClassMethodId _method, Struct _struct, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Object instance_ = _struct.getInstance();
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        Object[] argsObj_ = adaptedArgs(list_, _cont, _cont.getStandards(), args_);
        String mathType_ = lgNames_.getAliasMath();
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String nbType_ = lgNames_.getAliasNumber();
        String stringType_ = lgNames_.getAliasString();
        String stringBuilderType_ = lgNames_.getAliasStringBuilder();
        String replType_ = lgNames_.getAliasReplacement();
        String booleanPrimType_ = lgNames_.getAliasPrimBoolean();
        String divZero_ = lgNames_.getAliasDivisionZero();
        boolean null_ = false;
        if (!_method.getConstraints().isStaticMethod()) {
            if (instance_ == null) {
                result_.setError(lgNames_.getAliasNullPe());
                null_ = true;
            }
        }
        if (null_) {
            return result_;
        }
        if (instance_ instanceof Countable) {
            if (StringList.quickEq(name_, lgNames_.getAliasIsEmpty())) {
                result_.setResult(new BooleanStruct(((Countable) instance_).isEmpty()));
                return result_;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasSize())) {
                result_.setResult(new IntStruct(((Countable) instance_).size()));
                return result_;
            }
        }
        if (instance_ instanceof SimpleIterable) {
            if (StringList.quickEq(name_, lgNames_.getAliasSimpleIterator())) {
                String typeInst_ = lgNames_.getStructClassName(_struct, _cont);
                String it_ = lgNames_.getStandards().getVal(typeInst_).getIterative();
                result_.setResult(new StdStruct(((SimpleIterable) instance_).simpleIterator(), StringList.concat(lgNames_.getAliasSimpleIteratorType(),Templates.TEMPLATE_BEGIN,it_,Templates.TEMPLATE_END)));
                return result_;
            }
        }
        if (instance_ instanceof SimpleItr) {
            try {
                if (StringList.quickEq(name_, lgNames_.getAliasNext())) {
                    Object resObj_ = ((SimpleItr)instance_).next();
                    result_.setResult(StdStruct.wrapStd(resObj_, _cont));
                    return result_;
                }
                if (StringList.quickEq(name_, lgNames_.getAliasHasNext())) {
                    result_.setResult(new BooleanStruct(((SimpleItr)instance_).hasNext()));
                    return result_;
                }
            } catch (Throwable _0) {
                result_.setError(lgNames_.getAliasError());
                return result_;
            }
        }
        if (instance_ instanceof Displayable) {
            if (StringList.quickEq(name_, lgNames_.getAliasDisplay()) || StringList.quickEq(name_, lgNames_.getAliasToString())) {
                result_.setResult(new StringStruct(((Displayable)instance_).display()));
                return result_;
            }
        }
        if (StringList.quickEq(type_, mathType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasAbs())) {
                if (argsObj_[0] instanceof Long) {
                    result_.setResult(new LongStruct(Math.abs((Long) argsObj_[0])));
                } else {
                    result_.setResult(new IntStruct(Math.abs((Integer) argsObj_[0])));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasMod())) {
                if (argsObj_[0] instanceof Long) {
                    Long num_ = (Long) argsObj_[0];
                    Long den_ = (Long) argsObj_[1];
                    if (den_.longValue() == 0) {
                        result_.setError(divZero_);
                    } else {
                        result_.setResult(new LongStruct(Numbers.mod(num_, den_)));
                    }
                } else {
                    Integer num_ = (Integer) argsObj_[0];
                    Integer den_ = (Integer) argsObj_[1];
                    if (den_.longValue() == 0) {
                        result_.setError(divZero_);
                    } else {
                        result_.setResult(new IntStruct(Numbers.mod(num_, den_)));
                    }
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasQuot())) {
                if (argsObj_[0] instanceof Long) {
                    Long num_ = (Long) argsObj_[0];
                    Long den_ = (Long) argsObj_[1];
                    if (den_.longValue() == 0) {
                        result_.setError(divZero_);
                    } else {
                        result_.setResult(new LongStruct(Numbers.quot(num_, den_)));
                    }
                } else {
                    Integer num_ = (Integer) argsObj_[0];
                    Integer den_ = (Integer) argsObj_[1];
                    if (den_.longValue() == 0) {
                        result_.setError(divZero_);
                    } else {
                        result_.setResult(new IntStruct(Numbers.quot(num_, den_)));
                    }
                }
            }
        } else if (StringList.quickEq(type_, booleanType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasBooleanValue())) {
                result_.setResult(new BooleanStruct(((Boolean)instance_).booleanValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                result_.setResult(new IntStruct(ComparatorBoolean.cmp((Boolean)argsObj_[0],(Boolean) argsObj_[1])));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                result_.setResult(new IntStruct(ComparatorBoolean.cmp((Boolean)instance_,(Boolean) argsObj_[0])));
            } else if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                result_.setResult(new BooleanStruct(instance_ == argsObj_[0]));
            } else if (StringList.quickEq(name_, lgNames_.getAliasParseBoolean())) {
                result_.setResult(new BooleanStruct(Boolean.parseBoolean((String)argsObj_[0])));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                if (!list_.isEmpty()) {
                    result_.setResult(new StringStruct(Boolean.toString((Boolean)argsObj_[0])));
                } else {
                    result_.setResult(new StringStruct(Boolean.toString((Boolean)instance_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasValueOf())) {
                if (StringList.quickEq(list_.first(), booleanPrimType_)) {
                    result_.setResult(new BooleanStruct(Boolean.valueOf((Boolean)argsObj_[0])));
                } else {
                    result_.setResult(new BooleanStruct(Boolean.valueOf((String)argsObj_[0])));
                }
            }
        } else if (StringList.quickEq(type_, charType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasCharValue())) {
                result_.setResult(new CharStruct(((Character)instance_).charValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                Character one_ = (Character) argsObj_[0];
                Character two_ = (Character) argsObj_[1];
                result_.setResult(new IntStruct(one_.compareTo(two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                Character one_ = (Character) instance_;
                Character two_ = (Character) argsObj_[0];
                result_.setResult(new IntStruct(one_.compareTo(two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCharAt())) {
                Character one_ = (Character) instance_;
                Integer two_ = (Integer) argsObj_[0];
                if (two_.intValue() == 0) {
                    result_.setResult(new CharStruct(one_));
                } else {
                    result_.setError(lgNames_.getAliasBadIndex());
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasLength())) {
                result_.setResult(new IntStruct(1));
            } else if (StringList.quickEq(name_, lgNames_.getAliasSubSequence())) {
                Character one_ = (Character) instance_;
                Integer two_ = (Integer) argsObj_[0];
                Integer three_ = (Integer) argsObj_[1];
                if (two_.intValue() < 0 || three_.intValue() < 0) {
                    result_.setError(lgNames_.getAliasBadIndex());
                } else if (three_.intValue() > 1) {
                    result_.setError(lgNames_.getAliasBadIndex());
                } else if (two_.intValue() >= three_.intValue()) {
                    result_.setError(lgNames_.getAliasBadIndex());
                } else {
                    result_.setResult(new CharStruct(one_));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasDigit())) {
                Character one_ = (Character) argsObj_[0];
                Integer two_ = (Integer) argsObj_[1];
                result_.setResult(new IntStruct(Character.digit(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                Character one_ = (Character) instance_;
                Character two_ = (Character) argsObj_[0];
                result_.setResult(new BooleanStruct(one_.charValue() == two_.charValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasForDigit())) {
                Integer one_ = (Integer) argsObj_[0];
                Integer two_ = (Integer) argsObj_[1];
                result_.setResult(new CharStruct(Character.forDigit(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasGetDirectionality())) {
                Character one_ = (Character) argsObj_[0];
                result_.setResult(new ByteStruct(Character.getDirectionality(one_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasGetType())) {
                Character one_ = (Character) argsObj_[0];
                result_.setResult(new IntStruct(Character.getType(one_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasIsDigit())) {
                Character one_ = (Character) argsObj_[0];
                result_.setResult(new BooleanStruct(Character.isDigit(one_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasIsLetter())) {
                Character one_ = (Character) argsObj_[0];
                result_.setResult(new BooleanStruct(Character.isLetter(one_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasIsLetterOrDigit())) {
                Character one_ = (Character) argsObj_[0];
                result_.setResult(new BooleanStruct(Character.isLetterOrDigit(one_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasIsLowerCase())) {
                Character one_ = (Character) argsObj_[0];
                result_.setResult(new BooleanStruct(Character.isLowerCase(one_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasIsUpperCase())) {
                Character one_ = (Character) argsObj_[0];
                result_.setResult(new BooleanStruct(Character.isUpperCase(one_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasIsSpace())) {
                Character one_ = (Character) argsObj_[0];
                result_.setResult(new BooleanStruct(Character.isWhitespace(one_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasIsWhitespace())) {
                Character one_ = (Character) argsObj_[0];
                result_.setResult(new BooleanStruct(Character.isWhitespace(one_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasIsWordChar())) {
                Character one_ = (Character) argsObj_[0];
                result_.setResult(new BooleanStruct(StringList.isWordChar(one_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToLowerCase())) {
                Character one_ = (Character) argsObj_[0];
                result_.setResult(new CharStruct(Character.toLowerCase(one_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToUpperCase())) {
                Character one_ = (Character) argsObj_[0];
                result_.setResult(new CharStruct(Character.toUpperCase(one_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                if (list_.isEmpty()) {
                    Character one_ = (Character) instance_;
                    result_.setResult(new StringStruct(Character.toString(one_)));
                } else {
                    Character one_ = (Character) argsObj_[0];
                    result_.setResult(new StringStruct(Character.toString(one_)));
                }
            }
        } else if (PrimitiveTypeUtil.isPureNumberClass(new ClassArgumentMatching(type_), lgNames_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                Number one_ = (Number) argsObj_[0];
                Number two_ = (Number) argsObj_[1];
                result_.setResult(new IntStruct(Numbers.compare(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                Number one_ = (Number) instance_;
                Number two_ = (Number) argsObj_[0];
                result_.setResult(new IntStruct(Numbers.compare(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                Number one_ = (Number) instance_;
                Number two_ = (Number) argsObj_[0];
                result_.setResult(new BooleanStruct(Numbers.eq(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasByteValue())) {
                Number one_ = (Number) instance_;
                result_.setResult(new ByteStruct(one_.byteValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasShortValue())) {
                Number one_ = (Number) instance_;
                result_.setResult(new ShortStruct(one_.shortValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasIntValue())) {
                Number one_ = (Number) instance_;
                result_.setResult(new IntStruct(one_.intValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasLongValue())) {
                Number one_ = (Number) instance_;
                result_.setResult(new LongStruct(one_.longValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasFloatValue())) {
                Number one_ = (Number) instance_;
                result_.setResult(new FloatStruct(one_.floatValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasDoubleValue())) {
                Number one_ = (Number) instance_;
                result_.setResult(new DoubleStruct(one_.doubleValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToString()) && list_.isEmpty()) {
                Number one_ = (Number) instance_;
                result_.setResult(new StringStruct(one_.toString()));
            } else {
                String byteType_ = lgNames_.getAliasByte();
                String shortType_ = lgNames_.getAliasShort();
                String intType_ = lgNames_.getAliasInteger();
                String longType_ = lgNames_.getAliasLong();
                String floatType_ = lgNames_.getAliasFloat();
                if (StringList.quickEq(type_, byteType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasValueOf()) && !StringList.quickEq(list_.first(), stringType_)) {
                        Byte one_ = (Byte) argsObj_[0];
                        result_.setResult(new ByteStruct(one_));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                        Byte one_ = (Byte) argsObj_[0];
                        result_.setResult(new StringStruct(Integer.toString(one_)));
                    } else{
                        String one_ = (String) argsObj_[0];
                        Long lg_;
                        int radix_ = DEFAULT_RADIX;
                        if (list_.size() != 1) {
                            radix_ = (Integer) argsObj_[1];
                        }
                        lg_ = parseLong(one_, radix_);
                        if (lg_ == null || lg_.longValue() < Byte.MIN_VALUE || lg_.longValue() > Byte.MAX_VALUE) {
                            result_.setError(lgNames_.getAliasNbFormat());
                        } else {
                            result_.setResult(new ByteStruct(lg_.byteValue()));
                        }
                    }
                } else if (StringList.quickEq(type_, shortType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasValueOf()) && !StringList.quickEq(list_.first(), stringType_)) {
                        Short one_ = (Short) argsObj_[0];
                        result_.setResult(new ShortStruct(one_));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                        Short one_ = (Short) argsObj_[0];
                        result_.setResult(new StringStruct(Integer.toString(one_)));
                    } else {
                        String one_ = (String) argsObj_[0];
                        Long lg_;
                        int radix_ = DEFAULT_RADIX;
                        if (list_.size() != 1) {
                            radix_ = (Integer) argsObj_[1];
                        }
                        lg_ = parseLong(one_, radix_);
                        if (lg_ == null || lg_.longValue() < Short.MIN_VALUE || lg_.longValue() > Short.MAX_VALUE) {
                            result_.setError(lgNames_.getAliasNbFormat());
                        } else {
                            result_.setResult(new ShortStruct(lg_.shortValue()));
                        }
                    }
                } else if (StringList.quickEq(type_, intType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasValueOf()) && !StringList.quickEq(list_.first(), stringType_)) {
                        Integer one_ = (Integer) argsObj_[0];
                        result_.setResult(new IntStruct(one_));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                        Integer one_ = (Integer) argsObj_[0];
                        result_.setResult(new StringStruct(Integer.toString(one_)));
                    } else {
                        String one_ = (String) argsObj_[0];
                        Long lg_;
                        int radix_ = DEFAULT_RADIX;
                        if (list_.size() != 1) {
                            radix_ = (Integer) argsObj_[1];
                        }
                        lg_ = parseLong(one_, radix_);
                        if (lg_ == null || lg_.longValue() < Integer.MIN_VALUE || lg_.longValue() > Integer.MAX_VALUE) {
                            result_.setError(lgNames_.getAliasNbFormat());
                        } else {
                            result_.setResult(new IntStruct(lg_.intValue()));
                        }
                    }
                } else if (StringList.quickEq(type_, longType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasValueOf()) && !StringList.quickEq(list_.first(), stringType_)) {
                        Long one_ = (Long) argsObj_[0];
                        result_.setResult(new LongStruct(one_));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                        Long one_ = (Long) argsObj_[0];
                        result_.setResult(new StringStruct(Long.toString(one_)));
                    } else {
                        String one_ = (String) argsObj_[0];
                        Long lg_;
                        int radix_ = DEFAULT_RADIX;
                        if (list_.size() != 1) {
                            radix_ = (Integer) argsObj_[1];
                        }
                        lg_ = parseLong(one_, radix_);
                        if (lg_ == null) {
                            result_.setError(lgNames_.getAliasNbFormat());
                        } else {
                            result_.setResult(new LongStruct(lg_.longValue()));
                        }
                    }
                } else if (StringList.quickEq(type_, floatType_)) {
                    if (StringList.quickEq(name_, lgNames_.getAliasValueOf()) && !StringList.quickEq(list_.first(), stringType_)) {
                        Float one_ = (Float) argsObj_[0];
                        result_.setResult(new FloatStruct(one_));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasIsNan())) {
                        Float one_;
                        if (list_.isEmpty()) {
                            one_ = (Float) instance_;
                        } else {
                            one_ = (Float) argsObj_[0];
                        }
                        result_.setResult(new BooleanStruct(Double.isNaN(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                        Float one_;
                        if (list_.isEmpty()) {
                            one_ = (Float) instance_;
                        } else {
                            one_ = (Float) argsObj_[0];
                        }
                        result_.setResult(new BooleanStruct(Double.isInfinite(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                        Float one_ = (Float) argsObj_[0];
                        result_.setResult(new StringStruct(Float.toString(one_)));
                    } else {
                        String one_ = (String) argsObj_[0];
                        boolean valid_ = true;
                        NumberInfos infos_ = trySplitDouble(one_);
                        if (infos_ == null) {
                            valid_ = false;
                        }
                        Double d_ = null;
                        if (valid_) {
                            d_ = parseDouble(infos_);
                            double abs_ = Math.abs(d_);
                            if (abs_ > Float.MAX_VALUE) {
                                valid_ = false;
                            }
                        }
                        if (!valid_) {
                            result_.setError(lgNames_.getAliasNbFormat());
                        } else {
                            result_.setResult(new FloatStruct(d_.floatValue()));
                        }
                    }
                } else {
                    if (StringList.quickEq(name_, lgNames_.getAliasValueOf()) && !StringList.quickEq(list_.first(), stringType_)) {
                        Double one_ = (Double) argsObj_[0];
                        result_.setResult(new DoubleStruct(one_));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasIsNan())) {
                        Double one_;
                        if (list_.isEmpty()) {
                            one_ = (Double) instance_;
                        } else {
                            one_ = (Double) argsObj_[0];
                        }
                        result_.setResult(new BooleanStruct(Double.isNaN(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                        Double one_;
                        if (list_.isEmpty()) {
                            one_ = (Double) instance_;
                        } else {
                            one_ = (Double) argsObj_[0];
                        }
                        result_.setResult(new BooleanStruct(Double.isInfinite(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                        Double one_ = (Double) argsObj_[0];
                        result_.setResult(new StringStruct(Double.toString(one_)));
                    } else {
                        String one_ = (String) argsObj_[0];
                        boolean valid_ = true;
                        NumberInfos infos_ = trySplitDouble(one_);
                        if (infos_ == null) {
                            valid_ = false;
                        }
                        Double d_ = null;
                        if (valid_) {
                            d_ = parseDouble(infos_);
                        }
                        if (!valid_) {
                            result_.setError(lgNames_.getAliasNbFormat());
                        } else {
                            result_.setResult(new DoubleStruct(d_.doubleValue()));
                        }
                    }
                }
            }
        } else if (StringList.quickEq(type_, nbType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                Number one_ = (Number) argsObj_[0];
                Number two_ = (Number) argsObj_[1];
                result_.setResult(new IntStruct(Numbers.compare(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                Number one_ = (Number) instance_;
                Number two_ = (Number) argsObj_[0];
                result_.setResult(new IntStruct(Numbers.compare(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                Number one_ = (Number) instance_;
                Number two_ = (Number) argsObj_[0];
                result_.setResult(new BooleanStruct(Numbers.eq(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasByteValue())) {
                Number one_ = (Number) instance_;
                result_.setResult(new ByteStruct(one_.byteValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasShortValue())) {
                Number one_ = (Number) instance_;
                result_.setResult(new ShortStruct(one_.shortValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasIntValue())) {
                Number one_ = (Number) instance_;
                result_.setResult(new IntStruct(one_.intValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasLongValue())) {
                Number one_ = (Number) instance_;
                result_.setResult(new LongStruct(one_.longValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasFloatValue())) {
                Number one_ = (Number) instance_;
                result_.setResult(new FloatStruct(one_.floatValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasDoubleValue())) {
                Number one_ = (Number) instance_;
                result_.setResult(new DoubleStruct(one_.doubleValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToString()) && list_.isEmpty()) {
                Number one_ = (Number) instance_;
                result_.setResult(new StringStruct(one_.toString()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                Number one_ = (Number) argsObj_[0];
                result_.setResult(new StringStruct(one_.toString()));
            }
        } else if (StringList.quickEq(type_, replType_)) {
            Replacement one_ = (Replacement) instance_;
            if (StringList.quickEq(name_, lgNames_.getAliasGetNewString())) {
                result_.setResult(new StringStruct(one_.getNewString()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasGetOldString())) {
                result_.setResult(new StringStruct(one_.getOldString()));
            } else {
                String two_ = (String) argsObj_[0];
                if (StringList.quickEq(name_, lgNames_.getAliasSetNewString())) {
                    one_.setNewString(two_);
                    result_.setResult(NullStruct.NULL_VALUE);
                } else {
                    one_.setOldString(two_);
                    result_.setResult(NullStruct.NULL_VALUE);
                }
            }
        } else if (StringList.quickEq(type_, stringType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasCharAt())) {
                String one_ = (String) instance_;
                Integer two_ = (Integer) argsObj_[0];
                if (two_ < 0 || two_ >= one_.length()) {
                    result_.setError(lgNames_.getAliasBadIndex());
                } else {
                    result_.setResult(new CharStruct(one_.charAt(two_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                String one_ = (String) instance_;
                String two_ = (String) argsObj_[0];
                if (two_ == null) {
                    result_.setError(lgNames_.getAliasNullPe());
                } else {
                    result_.setResult(new IntStruct(one_.compareTo(two_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                String one_ = (String) argsObj_[0];
                String two_ = (String) argsObj_[1];
                if (one_ == null || two_ == null) {
                    result_.setError(lgNames_.getAliasNullPe());
                } else {
                    result_.setResult(new IntStruct(one_.compareTo(two_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareToIgnoreCase())) {
                String one_ = (String) instance_;
                String two_ = (String) argsObj_[0];
                if (two_ == null) {
                    result_.setError(lgNames_.getAliasNullPe());
                } else {
                    result_.setResult(new IntStruct(one_.compareToIgnoreCase(two_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasContains())) {
                String one_ = (String) instance_;
                CharSequence two_ = (CharSequence) argsObj_[0];
                if (two_ == null) {
                    result_.setError(lgNames_.getAliasNullPe());
                } else {
                    result_.setResult(new BooleanStruct(one_.contains(two_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasStartsWith())) {
                String one_ = (String) instance_;
                String two_ = (String) argsObj_[0];
                if (two_ == null) {
                    result_.setError(lgNames_.getAliasNullPe());
                } else if (list_.size() == 1) {
                    result_.setResult(new BooleanStruct(one_.startsWith(two_)));
                } else {
                    Integer three_ = (Integer) argsObj_[1];
                    result_.setResult(new BooleanStruct(one_.startsWith(two_, three_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasEndsWith())) {
                String one_ = (String) instance_;
                String two_ = (String) argsObj_[0];
                if (two_ == null) {
                    result_.setError(lgNames_.getAliasNullPe());
                } else if (list_.size() == 1) {
                    result_.setResult(new BooleanStruct(one_.endsWith(two_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                String one_ = (String) instance_;
                String two_ = (String) argsObj_[0];
                if (two_ == null) {
                    result_.setResult(new BooleanStruct(false));
                } else {
                    result_.setResult(new BooleanStruct(StringList.quickEq(one_, two_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasEqualsIgnoreCase())) {
                String one_ = (String) instance_;
                String two_ = (String) argsObj_[0];
                result_.setResult(new BooleanStruct(one_.equalsIgnoreCase(two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasFormat())) {
                String one_ = (String) instance_;
                String[] two_ = (String[]) argsObj_[0];
                result_.setResult(new StringStruct(StringList.simpleStringsFormat(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasGetBytes())) {
                String one_ = (String) instance_;
                if (list_.isEmpty()) {
                    byte[] bytes_ = one_.getBytes();
                    Struct[] wrap_ = new Struct[bytes_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (byte b: bytes_) {
                        wrap_[i_] = new ByteStruct(b);
                        i_++;
                    }
                    String ret_ = PrimitiveTypeUtil.getPrettyArrayType(lgNames_.getAliasPrimByte());
                    result_.setResult(new ArrayStruct(wrap_, ret_));
                } else {
                    String two_ = (String) argsObj_[0];
                    try {
                        byte[] bytes_ = one_.getBytes(two_);
                        Struct[] wrap_ = new Struct[bytes_.length];
                        int i_ = CustList.FIRST_INDEX;
                        for (byte b: bytes_) {
                            wrap_[i_] = new ByteStruct(b);
                            i_++;
                        }
                        String ret_ = PrimitiveTypeUtil.getPrettyArrayType(lgNames_.getAliasPrimByte());
                        result_.setResult(new ArrayStruct(wrap_, ret_));
                    } catch (UnsupportedEncodingException _0) {
                        result_.setError(lgNames_.getAliasBadEncode());
                    }
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasIndexOf())) {
                String one_ = (String) instance_;
                if (StringList.quickEq(list_.first(), stringType_) && argsObj_[0] == null) {
                    result_.setError(lgNames_.getAliasNullPe());
                } else {
                    if (list_.size() == 1 && StringList.quickEq(list_.first(), stringType_)) {
                        String two_ = (String) argsObj_[0];
                        result_.setResult(new IntStruct(one_.indexOf(two_)));
                    } else if (StringList.quickEq(list_.first(), stringType_)) {
                        String two_ = (String) argsObj_[0];
                        Integer three_ = (Integer) argsObj_[1];
                        result_.setResult(new IntStruct(one_.indexOf(two_, three_)));
                    } else if (list_.size() == 1) {
                        Character two_ = (Character) argsObj_[0];
                        result_.setResult(new IntStruct(one_.indexOf(two_)));
                    } else {
                        Character two_ = (Character) argsObj_[0];
                        Integer three_ = (Integer) argsObj_[1];
                        result_.setResult(new IntStruct(one_.indexOf(two_, three_)));
                    }
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasLastIndexOf())) {
                String one_ = (String) instance_;
                if (StringList.quickEq(list_.first(), stringType_) && argsObj_[0] == null) {
                    result_.setError(lgNames_.getAliasNullPe());
                } else {
                    if (list_.size() == 1 && StringList.quickEq(list_.first(), stringType_)) {
                        String two_ = (String) argsObj_[0];
                        result_.setResult(new IntStruct(one_.lastIndexOf(two_)));
                    } else if (StringList.quickEq(list_.first(), stringType_)) {
                        String two_ = (String) argsObj_[0];
                        Integer three_ = (Integer) argsObj_[1];
                        result_.setResult(new IntStruct(one_.lastIndexOf(two_, three_)));
                    } else if (list_.size() == 1) {
                        Character two_ = (Character) argsObj_[0];
                        result_.setResult(new IntStruct(one_.lastIndexOf(two_)));
                    } else {
                        Character two_ = (Character) argsObj_[0];
                        Integer three_ = (Integer) argsObj_[1];
                        result_.setResult(new IntStruct(one_.lastIndexOf(two_, three_)));
                    }
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasLength())) {
                String one_ = (String) instance_;
                result_.setResult(new IntStruct(one_.length()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasRegionMatches())) {
                String one_ = (String) instance_;
                if (list_.size() == FOUR_ARGS) {
                    Integer two_ = (Integer) argsObj_[0];
                    String three_ = (String) argsObj_[1];
                    Integer four_ = (Integer) argsObj_[2];
                    Integer five_ = (Integer) argsObj_[THREE_ARGS];
                    if (three_ == null) {
                        result_.setError(lgNames_.getAliasNullPe());
                    } else {
                        result_.setResult(new BooleanStruct(one_.regionMatches(two_, three_, four_, five_)));
                    }
                } else {
                    Boolean two_ = (Boolean) argsObj_[0];
                    Integer three_ = (Integer) argsObj_[1];
                    String four_ = (String) argsObj_[2];
                    Integer five_ = (Integer) argsObj_[THREE_ARGS];
                    Integer six_ = (Integer) argsObj_[FOUR_ARGS];
                    if (four_ == null) {
                        result_.setError(lgNames_.getAliasNullPe());
                    } else {
                        result_.setResult(new BooleanStruct(one_.regionMatches(two_, three_, four_, five_, six_)));
                    }
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasReplace())) {
                String one_ = (String) instance_;
                if (StringList.quickEq(list_.first(), stringType_)){
                    String two_ = (String) argsObj_[0];
                    String three_ = (String) argsObj_[1];
                    result_.setResult(new StringStruct(StringList.replace(one_, two_, three_)));
                } else {
                    Character two_ = (Character) argsObj_[0];
                    Character three_ = (Character) argsObj_[1];
                    result_.setResult(new StringStruct(one_.replace(two_, three_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasReplaceMultiple())) {
                String one_ = (String) instance_;
                Replacement[] two_ = (Replacement[]) argsObj_[0];
                result_.setResult(new StringStruct(StringList.replaceMult(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasSplit())) {
                String one_ = (String) instance_;
                StringList res_;
                if (StringList.quickEq(list_.first(), stringType_)){
                    String two_ = (String) argsObj_[0];
                    res_ = StringList.splitStrings(one_, two_);
                } else {
                    Character two_ = (Character) argsObj_[0];
                    res_ = StringList.splitChars(one_, two_);
                }
                Integer lim_ = -1;
                if (list_.size() == 2) {
                    lim_ = (Integer) argsObj_[1];
                    if (lim_ < -1) {
                        lim_ = -1;
                    }
                }
                Struct[] array_;
                if (lim_ == -1) {
                    array_ = new Struct[res_.size()];
                    int i_ = CustList.FIRST_INDEX;
                    for (String v: res_) {
                        array_[i_] = new StringStruct(v);
                        i_++;
                    }
                } else {
                    array_ = new Struct[lim_];
                    int i_ = CustList.FIRST_INDEX;
                    for (String v: res_) {
                        if (i_ == lim_) {
                            break;
                        }
                        array_[i_] = new StringStruct(v);
                        i_++;
                    }
                }
                result_.setResult(new ArrayStruct(array_, PrimitiveTypeUtil.getPrettyArrayType(stringType_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasSplitStrings())) {
                String one_ = (String) instance_;
                Integer lim_ = -1;
                StringList res_;
                String[] two_;
                if (StringList.quickEq(list_.first(), stringType_)){
                    two_ = (String[]) argsObj_[0];
                } else {
                    lim_ = (Integer) argsObj_[0];
                    if (lim_ < -1) {
                        lim_ = -1;
                    }
                    two_ = (String[]) argsObj_[1];
                }
                res_ = StringList.splitStrings(one_, two_);
                Struct[] array_;
                int i_ = CustList.FIRST_INDEX;
                if (lim_ == -1) {
                    array_ = new Struct[res_.size()];
                    for (String v: res_) {
                        array_[i_] = new StringStruct(v);
                        i_++;
                    }
                } else {
                    array_ = new Struct[lim_];
                    for (String v: res_) {
                        if (i_ == lim_) {
                            break;
                        }
                        array_[i_] = new StringStruct(v);
                        i_++;
                    }
                }
                result_.setResult(new ArrayStruct(array_, PrimitiveTypeUtil.getPrettyArrayType(stringType_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasSplitChars())) {
                String one_ = (String) instance_;
                StringList res_;
                char[] two_ = (char[]) argsObj_[0];
                res_ = StringList.splitCharsArr(one_, two_);
                Struct[] array_;
                int i_ = CustList.FIRST_INDEX;
                array_ = new Struct[res_.size()];
                for (String v: res_) {
                    array_[i_] = new StringStruct(v);
                    i_++;
                }
                result_.setResult(new ArrayStruct(array_, PrimitiveTypeUtil.getPrettyArrayType(stringType_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasSubSequence())) {
                String one_ = (String) instance_;
                Integer two_ = (Integer) argsObj_[0];
                Integer three_ = (Integer) argsObj_[1];
                if (two_ < 0 || three_ < 0 || three_ > one_.length() || two_ > three_) {
                    result_.setError(lgNames_.getAliasBadIndex());
                } else {
                    result_.setResult(new StringStruct(one_.substring(two_, three_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasSubstring())) {
                String one_ = (String) instance_;
                Integer two_ = (Integer) argsObj_[0];
                if (list_.size() == 2) {
                    Integer three_ = (Integer) argsObj_[1];
                    if (two_ < 0 || three_ < 0 || three_ > one_.length() || two_ > three_) {
                        result_.setError(lgNames_.getAliasBadIndex());
                    } else {
                        result_.setResult(new StringStruct(one_.substring(two_, three_)));
                    }
                } else {
                    if (two_ < 0 || two_ > one_.length()) {
                        result_.setError(lgNames_.getAliasBadIndex());
                    } else {
                        result_.setResult(new StringStruct(one_.substring(two_)));
                    }
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasToLowerCase())) {
                String one_ = (String) instance_;
                result_.setResult(new StringStruct(StringList.toLowerCase(one_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToUpperCase())) {
                String one_ = (String) instance_;
                result_.setResult(new StringStruct(StringList.toUpperCase(one_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToCharArray())) {
                String one_ = (String) instance_;
                char[] bytes_ = one_.toCharArray();
                Struct[] wrap_ = new Struct[bytes_.length];
                int i_ = CustList.FIRST_INDEX;
                for (char b: bytes_) {
                    wrap_[i_] = new CharStruct(b);
                    i_++;
                }
                String ret_ = PrimitiveTypeUtil.getPrettyArrayType(lgNames_.getAliasPrimChar());
                result_.setResult(new ArrayStruct(wrap_, ret_));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                String one_ = (String) instance_;
                result_.setResult(new StringStruct(one_));
            } else if (StringList.quickEq(name_, lgNames_.getAliasTrim())) {
                String one_ = (String) instance_;
                result_.setResult(new StringStruct(one_.trim()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasValueOf())) {
                if (StringList.quickEq(list_.first(), PrimitiveTypeUtil.getPrettyArrayType(lgNames_.getAliasPrimChar())) && list_.size() == 1) {
                    char[] chars_ = (char[]) argsObj_[0];
                    result_.setResult(new StringStruct(String.valueOf(chars_)));
                } else if (StringList.quickEq(list_.first(), PrimitiveTypeUtil.getPrettyArrayType(lgNames_.getAliasPrimChar()))) {
                    char[] chars_ = (char[]) argsObj_[0];
                    if (chars_ == null) {
                        result_.setError(lgNames_.getAliasNullPe());
                    } else {
                        Integer two_ = (Integer) argsObj_[1];
                        Integer three_ = (Integer) argsObj_[2];
                        if (two_ < 0 || three_ < 0 || two_ + three_ > chars_.length) {
                            result_.setError(lgNames_.getAliasBadIndex());
                        } else {
                            result_.setResult(new StringStruct(String.valueOf(chars_, two_, three_)));
                        }
                    }
                } else {
                    Object obj_ = argsObj_[0];
                    result_.setResult(new StringStruct(String.valueOf(obj_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasIsEmpty())) {
                String one_ = (String) instance_;
                result_.setResult(new BooleanStruct(one_.length() == 0));
            } else if (StringList.quickEq(name_, lgNames_.getAliasIntern())) {
                String one_ = (String) instance_;
                result_.setResult(new StringStruct(one_.intern()));
            }
        } else if (StringList.quickEq(type_, stringBuilderType_)) {
            StringBuilder one_ = (StringBuilder) instance_;
            if (StringList.quickEq(name_, lgNames_.getAliasAppend())) {
                if (list_.size() == 1 && StringList.quickEq(list_.first(), PrimitiveTypeUtil.getPrettyArrayType(lgNames_.getAliasPrimChar()))) {
                    char[] two_ = (char[]) argsObj_[0];
                    if (two_ == null) {
                        result_.setError(lgNames_.getAliasNullPe());
                    } else {
                        result_.setResult(new StringBuilderStruct(one_.append(two_)));
                    }
                } else if (list_.size() == 1) {
                    Object two_ = argsObj_[0];
                    result_.setResult(new StringBuilderStruct(one_.append(two_)));
                } else {
                    if (StringList.quickEq(list_.first(), PrimitiveTypeUtil.getPrettyArrayType(lgNames_.getAliasPrimChar()))) {
                        char[] two_ = (char[]) argsObj_[0];
                        if (two_ == null) {
                            result_.setError(lgNames_.getAliasNullPe());
                        } else {
                            Integer three_ = (Integer) argsObj_[1];
                            Integer four_ = (Integer) argsObj_[2];
                            if (three_ < 0 || four_ < 0 || three_ + four_ > two_.length) {
                                result_.setError(lgNames_.getAliasBadIndex());
                            } else {
                                result_.setResult(new StringBuilderStruct(one_.append(two_, three_, four_)));
                            }
                        }
                    } else {
                        CharSequence two_ = (CharSequence) argsObj_[0];
                        if (two_ == null) {
                            result_.setError(lgNames_.getAliasNullPe());
                        } else {
                            Integer three_ = (Integer) argsObj_[1];
                            Integer four_ = (Integer) argsObj_[2];
                            if (three_ < 0 || four_ < 0 || three_ > four_ || four_ > two_.length()) {
                                result_.setError(lgNames_.getAliasBadIndex());
                            } else {
                                result_.setResult(new StringBuilderStruct(one_.append(two_, three_, four_)));
                            }
                        }
                    }
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasCapacity())) {
                result_.setResult(new IntStruct(one_.capacity()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCharAt())) {
                Integer two_ = (Integer) argsObj_[0];
                if (two_ < 0 || two_ >= one_.length()) {
                    result_.setError(lgNames_.getAliasBadIndex());
                } else {
                    result_.setResult(new CharStruct(one_.charAt(two_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasClear())) {
                one_.delete(0, one_.length());
                result_.setResult(new StringBuilderStruct(one_));
            } else if (StringList.quickEq(name_, lgNames_.getAliasDelete())) {
                Integer two_ = (Integer) argsObj_[0];
                Integer three_ = (Integer) argsObj_[1];
                if (two_ < 0 || two_ > three_ || two_ > one_.length()) {
                    result_.setError(lgNames_.getAliasBadIndex());
                } else {
                    result_.setResult(new StringBuilderStruct(one_.delete(two_, three_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasDeleteCharAt())) {
                Integer two_ = (Integer) argsObj_[0];
                if (two_ < 0 || two_ >= one_.length()) {
                    result_.setError(lgNames_.getAliasBadIndex());
                } else {
                    result_.setResult(new StringBuilderStruct(one_.deleteCharAt(two_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasEnsureCapacity())) {
                Integer two_ = (Integer) argsObj_[0];
                one_.ensureCapacity(two_);
                result_.setResult(NullStruct.NULL_VALUE);
            } else if (StringList.quickEq(name_, lgNames_.getAliasIndexOf())) {
                Object two_ = argsObj_[0];
                if (list_.size() > 1) {
                    Integer three_ = (Integer) argsObj_[1];
                    result_.setResult(new IntStruct(one_.indexOf(String.valueOf(two_), three_)));
                } else {
                    result_.setResult(new IntStruct(one_.indexOf(String.valueOf(two_))));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasLastIndexOf())) {
                Object two_ = argsObj_[0];
                if (list_.size() > 1) {
                    Integer three_ = (Integer) argsObj_[1];
                    result_.setResult(new IntStruct(one_.lastIndexOf(String.valueOf(two_), three_)));
                } else {
                    result_.setResult(new IntStruct(one_.lastIndexOf(String.valueOf(two_))));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasLength())) {
                result_.setResult(new IntStruct(one_.length()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasInsert())) {
                Integer two_ = (Integer) argsObj_[0];
                if (list_.size() == 2 && StringList.quickEq(list_.get(1), PrimitiveTypeUtil.getPrettyArrayType(lgNames_.getAliasPrimChar()))) {
                    char[] three_ = (char[]) argsObj_[1];
                    if (two_ < 0 || two_ > one_.length()) {
                        result_.setError(lgNames_.getAliasBadIndex());
                    } else if (three_ == null) {
                        result_.setError(lgNames_.getAliasNullPe());
                    } else {
                        result_.setResult(new StringBuilderStruct(one_.insert(two_, three_)));
                    }
                } else if (list_.size() == 2) {
                    if (two_ < 0 || two_ > one_.length()) {
                        result_.setError(lgNames_.getAliasBadIndex());
                    } else {
                        Object three_ = argsObj_[1];
                        result_.setResult(new StringBuilderStruct(one_.insert(two_, three_)));
                    }
                } else {
                    if (StringList.quickEq(list_.get(1), PrimitiveTypeUtil.getPrettyArrayType(lgNames_.getAliasPrimChar()))) {
                        char[] three_ = (char[]) argsObj_[1];
                        if (three_ == null) {
                            result_.setError(lgNames_.getAliasNullPe());
                        } else {
                            Integer four_ = (Integer) argsObj_[2];
                            Integer five_ = (Integer) argsObj_[THREE_ARGS];
                            if (two_ < 0 || two_ > one_.length() || four_ < 0 || five_ < 0 || four_ + five_ > three_.length) {
                                result_.setError(lgNames_.getAliasBadIndex());
                            } else {
                                result_.setResult(new StringBuilderStruct(one_.insert(two_, three_, four_, five_)));
                            }
                        }
                    } else {
                        CharSequence three_ = (CharSequence) argsObj_[1];
                        if (three_ == null) {
                            result_.setError(lgNames_.getAliasNullPe());
                        } else {
                            Integer four_ = (Integer) argsObj_[2];
                            Integer five_ = (Integer) argsObj_[THREE_ARGS];
                            if (two_ < 0 || two_ > one_.length() || four_ < 0 || five_ < 0 || four_ > five_ || five_ > three_.length()) {
                                result_.setError(lgNames_.getAliasBadIndex());
                            } else {
                                result_.setResult(new StringBuilderStruct(one_.insert(two_, three_, four_, five_)));
                            }
                        }
                    }
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasIsEmpty())) {
                result_.setResult(new BooleanStruct(one_.length() == 0));
            } else if (StringList.quickEq(name_, lgNames_.getAliasReplace())) {
                Integer two_ = (Integer) argsObj_[0];
                Integer three_ = (Integer) argsObj_[1];
                String four_ = (String) argsObj_[2];
                if (two_ < 0 || two_ > one_.length() || two_ > three_) {
                    result_.setError(lgNames_.getAliasBadIndex());
                } else {
                    result_.setResult(new StringBuilderStruct(one_.replace(two_, three_, four_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasReverse())) {
                result_.setResult(new StringBuilderStruct(one_.reverse()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasSetCharAt())) {
                Integer two_ = (Integer) argsObj_[0];
                Character three_ = (Character) argsObj_[1];
                if (two_ < 0 || two_ > one_.length()) {
                    result_.setError(lgNames_.getAliasBadIndex());
                } else {
                    one_.setCharAt(two_, three_);
                    result_.setResult(NullStruct.NULL_VALUE);
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasSetLength())) {
                Integer two_ = (Integer) argsObj_[0];
                if (two_ < 0) {
                    result_.setError(lgNames_.getAliasBadIndex());
                } else {
                    one_.setLength(two_);
                    result_.setResult(NullStruct.NULL_VALUE);
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasSubstring())) {
                Integer two_ = (Integer) argsObj_[0];
                if (list_.size() > 1) {
                    Integer three_ = (Integer) argsObj_[1];
                    if (two_ < 0 || three_ < 0 || two_ > one_.length() || three_ > one_.length() || two_ > three_) {
                        result_.setError(lgNames_.getAliasBadIndex());
                    } else {
                        result_.setResult(new StringStruct(one_.substring(two_, three_)));
                    }
                } else {
                    if (two_ < 0 || two_ > one_.length()) {
                        result_.setError(lgNames_.getAliasBadIndex());
                    } else {
                        result_.setResult(new StringStruct(one_.substring(two_)));
                    }
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasSubSequence())) {
                Integer two_ = (Integer) argsObj_[0];
                Integer three_ = (Integer) argsObj_[1];
                if (two_ < 0 || three_ < 0 || two_ > one_.length() || three_ > one_.length() || two_ > three_) {
                    result_.setError(lgNames_.getAliasBadIndex());
                } else {
                    result_.setResult(new StringStruct(one_.substring(two_, three_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                result_.setResult(new StringStruct(one_.toString()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasTrimToSize())) {
                one_.trimToSize();
                result_.setResult(NullStruct.NULL_VALUE);
            }
        } else if (StringList.quickEq(type_, lgNames_.getAliasCharSequence())) {
            if (StringList.quickEq(name_, lgNames_.getAliasCharAt())) {
                CharSequence one_ = (CharSequence) instance_;
                Integer two_ = (Integer) argsObj_[0];
                if (two_ < 0 || two_ > one_.length()) {
                    result_.setError(lgNames_.getAliasBadIndex());
                } else {
                    result_.setResult(new CharStruct(one_.charAt(two_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasLength())) {
                CharSequence one_ = (CharSequence) instance_;
                result_.setResult(new IntStruct(one_.length()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasSubSequence())) {
                CharSequence one_ = (CharSequence) instance_;
                Integer two_ = (Integer) argsObj_[0];
                Integer three_ = (Integer) argsObj_[1];
                if (two_ < 0 || three_ < 0 || two_ > one_.length() || three_ > one_.length() || two_ > three_) {
                    result_.setError(lgNames_.getAliasBadIndex());
                } else {
                    if (one_ instanceof String) {
                        result_.setResult(new StringStruct(((String)one_).substring(two_, three_)));
                    } else if (one_ instanceof StringBuilder) {
                        result_.setResult(new StringStruct(((StringBuilder)one_).substring(two_, three_)));
                    } else {
                        result_ = lgNames_.getOtherResult(_cont, _struct, _method, argsObj_);
                    }
                }
            }
        } else {
            result_ = lgNames_.getOtherResult(_cont, _struct, _method, argsObj_);
        }
        return result_;
    }
    public static Double parseDouble(NumberInfos _nb) {
        StringBuilder int_ = new StringBuilder(_nb.getIntPart());
        while(int_.indexOf("_") >= 0) {
            int_.deleteCharAt(int_.indexOf("_"));
        }
        while(int_.indexOf(" ") >= 0) {
            int_.deleteCharAt(int_.indexOf(" "));
        }
        StringBuilder dec_ = new StringBuilder(_nb.getDecimalPart());
        while(dec_.indexOf("_") >= 0) {
            dec_.deleteCharAt(dec_.indexOf("_"));
        }
        while(dec_.indexOf(" ") >= 0) {
            dec_.deleteCharAt(dec_.indexOf(" "));
        }
        StringBuilder exp_ = new StringBuilder(_nb.getExponentialPart());
        while(exp_.indexOf("_") >= 0) {
            exp_.deleteCharAt(exp_.indexOf("_"));
        }
        while(exp_.indexOf(" ") >= 0) {
            exp_.deleteCharAt(exp_.indexOf(" "));
        }
        boolean positive_ = _nb.isPositive();
        Long expNb_;
        if (exp_.length() == 0) {
            expNb_ = 0l;
        } else {
            expNb_ = parseLongTen(exp_.toString());
        }
        if (expNb_ == null) {
            if (positive_) {
                if (exp_.charAt(0) == '-') {
                    return 0.0;
                }
                return Double.POSITIVE_INFINITY;
            }
            if (exp_.charAt(0) == '-') {
                return -0.0;
            }
            return Double.NEGATIVE_INFINITY;
        }
        if (dec_.length() == 0) {
            if (expNb_.longValue() == 0) {
                Long long_;
                if (int_.length() > MAX_DIGITS_DOUBLE) {
                    return processBigNumbers(int_, positive_, MAX_DIGITS_DOUBLE);
                }
                long_ = parseLongTen(int_.toString());
                if (!positive_) {
                    return -long_.doubleValue();
                }
                return long_.doubleValue();
            }
            long expNbLong_ = expNb_.longValue();
            Long long_;
            if (int_.length() > MAX_DIGITS_DOUBLE) {
                long_ = parseLongTen(int_.substring(0, MAX_DIGITS_DOUBLE + 1));
                expNbLong_ += int_.length() - MAX_DIGITS_DOUBLE - 1;
            } else {
                long_ = parseLongTen(int_.toString());
            }
            double power_ = 1;
            long absExp_ = Math.abs(expNbLong_);
            for (long i = 0; i < absExp_; i++) {
                power_ *= 10d;
            }
            if (!positive_) {
                if (expNbLong_ > 0) {
                    return -long_.doubleValue() * power_;
                }
                return -long_.doubleValue() / power_;
            }
            if (expNbLong_ > 0) {
                return long_.doubleValue() * power_;
            }
            return long_.doubleValue() / power_;
        }
        long expNbLong_ = expNb_.longValue();
        if (expNbLong_ >= dec_.length()) {
            //try to get "double" as int
            StringBuilder number_ = new StringBuilder(int_.length()+dec_.length());
            number_.append(int_);
            number_.append(dec_);
            int diff_ = (int)expNbLong_-dec_.length();
            for (long i = 0; i < diff_; i++) {
                number_.append("0");
            }
            if (number_.length() > MAX_DIGITS_DOUBLE) {
                return processBigNumbers(number_, positive_, MAX_DIGITS_DOUBLE);
            }
            Long long_ = parseLongTen(number_.toString());
            if (!positive_) {
                return -long_.doubleValue();
            }
            return long_.doubleValue();
        }
        if (-expNbLong_ >= int_.length()) {
            StringBuilder number_ = new StringBuilder(int_);
            number_.append(dec_);
            int nbLeadingZeros_ = 0;
            StringBuilder decCopy_ = new StringBuilder();
            int index_ = 0;
            while (index_ < number_.length()) {
                if (number_.charAt(index_) != '0') {
                    break;
                }
                index_++;
            }
            nbLeadingZeros_ = index_;
            decCopy_.append(number_.substring(nbLeadingZeros_));
            if (decCopy_.length() == 0) {
                if (!positive_) {
                    return -0.0;
                }
                return 0.0;
            }
            double value_;
            int diff_;
            if (decCopy_.length() > MAX_DIGITS_DOUBLE) {
                value_ = parseLongTen(decCopy_.substring(0, MAX_DIGITS_DOUBLE + 1)).doubleValue();
                diff_ = (int) (-expNbLong_ - int_.length() + MAX_DIGITS_DOUBLE + 1 + nbLeadingZeros_);
            } else {
                value_ = parseLongTen(decCopy_.toString()).doubleValue();
                diff_ = (int) (-expNbLong_ + dec_.length());
            }
            double power_ = 1;
            for (int i = 0; i < diff_; i++) {
                power_ *= 10d;
            }
            if (!positive_) {
                return -value_ / power_;
            }
            return value_ / power_;
        }
        StringBuilder numberInt_ = new StringBuilder();
        StringBuilder numberDec_ = new StringBuilder();
        if (expNbLong_ > 0) {
            //expNbLong_ < dec_.length() => dec_.length() > 0 => numberInt_.length() > 0
            //-expNbLong_ < int_.length()
            numberInt_.append(int_);
            numberInt_.append(dec_.substring(0, (int) expNbLong_));
            numberDec_.append(dec_.substring((int)expNbLong_));
        } else if (expNbLong_ == 0) {
            //expNbLong_ < dec_.length() => 0 < dec_.length()
            //-expNbLong_ < int_.length() => 0 < int_.length() => numberInt_.length() > 0
            numberInt_.append(int_);
            numberDec_.append(dec_);
        } else {
            //expNbLong_ < 0
            int del_ = int_.length() +(int)expNbLong_;
            //-expNbLong_ < int_.length() => 0 < -expNbLong_ < int_.length() => 0 < int_.length()
            //-expNbLong_ < int_.length() => 0 < expNbLong_ + int_.length() => numberInt_.length() > 0
            numberInt_.append(int_.substring(0, del_));
            numberDec_.append(int_.substring(del_));
            numberDec_.append(dec_);
        }
        if (numberInt_.length() > MAX_DIGITS_DOUBLE) {
            return processBigNumbers(numberInt_, positive_, MAX_DIGITS_DOUBLE);
        }
        Long longValue_ = parseLongTen(numberInt_.toString());
        StringBuilder decCopy_ = new StringBuilder();
        int nbLeadingZeros_ = 0;
        int index_ = 0;
        while (index_ < numberDec_.length()) {
            if (numberDec_.charAt(index_) != '0') {
                break;
            }
            index_++;
        }
        nbLeadingZeros_ = index_;
        decCopy_.append(numberDec_.substring(nbLeadingZeros_));
        decCopy_.delete(Math.min(MAX_DIGITS_DOUBLE + 1, decCopy_.length()), decCopy_.length());
        if (decCopy_.length() == 0) {
            if (!positive_) {
                return -longValue_.doubleValue();
            }
            return longValue_.doubleValue();
        }
        double decValue_ = parseLongTen(decCopy_.toString()).doubleValue();
        double power_ = 1;
        int logDec_ = numberDec_.length();
        for (int i = 0; i < logDec_; i++) {
            power_ *= 10d;
        }
        if (!positive_) {
            return -longValue_.doubleValue() - decValue_ / power_;
        }
        return longValue_.doubleValue() + decValue_ / power_;
    }
    private static Double processBigNumbers(StringBuilder _nb, boolean _positive, int _max) {
        Long long_ = parseLongTen(_nb.substring(0, _max + 1).toString());
        double power_ = 1;
        int logDec_ = _nb.length() - _max - 1;
        for (int i = 0; i < logDec_; i++) {
            power_ *= 10d;
        }
        double out_ = long_.doubleValue() * power_;
        if (_positive) {
            return out_;
        }
        return -out_;
    }
    //this long parser is very naive
    public static char parseCharSixteen(String _string) {
        int result_ = 0;
        int i_ = 0;
        int max_ = _string.length();
        int digit_;
        int ch_ = _string.charAt(i_);
        if (ch_ >= 'A' && ch_ <= 'F') {
            ch_ = ch_ - 'A' + 'a';
        }
        i_++;
        digit_ = Math.min(ch_ - '0', 10) + Math.max(ch_ - 'a', 0);
        result_ = -digit_;
        while (i_ < max_) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            ch_ = _string.charAt(i_);
            if (ch_ >= 'A' && ch_ <= 'F') {
                ch_ = ch_ - 'A' + 'a';
            }
            i_++;
            digit_ = Math.min(ch_ - '0', 10) + Math.max(ch_ - 'a', 0);
            result_ *= HEX_BASE;
            result_ -= digit_;
        }
        return (char)-result_;
    }
    //this long parser is very naive
    public static Long parseLongTen(String _string) {
        long result_ = 0;
        boolean negative_ = false;
        int i_ = 0;
        int max_ = _string.length();
        long limit_;
        long multmin_;
        int digit_;

        if (_string.charAt(0) == '-') {
            negative_ = true;
            limit_ = Long.MIN_VALUE;
            i_++;
        } else {
            limit_ = -Long.MAX_VALUE;
        }
        if (negative_) {
            multmin_ = MULTMIN_RADIX_TEN;
        } else {
            multmin_ = N_MULTMAX_RADIX_TEN;
        }
        int ch_ = _string.charAt(i_);
        i_++;
        digit_ = ch_ - '0';
        result_ = -digit_;
        while (i_ < max_) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            ch_ = _string.charAt(i_);
            i_++;
            digit_ = ch_ - '0';
            if (result_ < multmin_) {
                return null;
            }
            result_ *= DEFAULT_RADIX;
            if (result_ < limit_ + digit_) {
                return null;
            }
            result_ -= digit_;
        }
        if (negative_) {
            return result_;
        }
        return -result_;
    }
    public static Long parseLong(String _string, int _radix) {
        if (_string == null) {
            return null;
        }
        if (_radix < Character.MIN_RADIX) {
            return null;
        }
        if (_radix > Character.MAX_RADIX) {
            return null;
        }

        long result_ = 0;
        boolean negative_ = false;
        int i_ = 0;
        int max_ = _string.length();
        long limit_;
        long multmin_;
        int digit_;

        if (max_ > 0) {
            if (_string.charAt(0) == '-') {
                negative_ = true;
                limit_ = Long.MIN_VALUE;
                i_++;
            } else {
                limit_ = -Long.MAX_VALUE;
            }
            if (_radix == DEFAULT_RADIX) {
                if (negative_) {
                    multmin_ = MULTMIN_RADIX_TEN;
                } else {
                    multmin_ = N_MULTMAX_RADIX_TEN;
                }
            } else {
                multmin_ = limit_ / _radix;
            }
            if (i_ < max_) {
                int ch_ = _string.charAt(i_);
                if (ch_ >= 'A' && ch_ <= 'Z') {
                    ch_ = ch_ - 'A' + 'a';
                }
                i_++;
                int dig_ = Math.min(ch_ - '0', 10) + Math.max(ch_ - 'a', 0);
                if (dig_ < 0 || dig_ >= _radix) {
                    return null;
                }
                digit_ = dig_;
                result_ = -digit_;
            }
            while (i_ < max_) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                int ch_ = _string.charAt(i_);
                if (ch_ >= 'A' && ch_ <= 'Z') {
                    ch_ = ch_ - 'A' + 'a';
                }
                i_++;
                int dig_ = Math.min(ch_ - '0', 10) + Math.max(ch_ - 'a', 0);
                if (dig_ < 0 || dig_ >= _radix) {
                    return null;
                }
                digit_ = dig_;
                if (result_ < multmin_) {
                    return null;
                }
                result_ *= _radix;
                if (result_ < limit_ + digit_) {
                    return null;
                }
                result_ -= digit_;
            }
        } else {
            return null;
        }
        if (negative_) {
            if (i_ > 1) {
                return result_;
            } else {
                return null;
            }
        } else {
            return -result_;
        }
    }
    public static boolean isValidDouble(String _nb) {
        int to_ = _nb.length() - 1;
        int i_ = 0;
        if (!Character.isDigit(_nb.charAt(i_))) {
            if (_nb.charAt(i_) != MINUS_CHAR) {
                if (_nb.charAt(i_) != DOT_VAR) {
                    return false;
                }
            }
            i_++;
            if (_nb.charAt(i_) == DOT_VAR) {
                i_++;
            }
        }
        if (i_ <= to_) {
            if (!Character.isDigit(_nb.charAt(i_))) {
                return false;
            }
        } else {
            return false;
        }
        int nbDots_ = 0;
        boolean exp_ = false;
        while (i_ <= to_) {
            char cur_ = _nb.charAt(i_);
            if (!Character.isDigit(cur_)) {
                if (Character.isLetter(cur_)) {
                    if (Character.toLowerCase(cur_) == EXP) {
                        exp_ = true;
                        i_++;
                        continue;
                    }
                    return false;
                }
                if (cur_ == MINUS_CHAR && exp_) {
                    i_++;
                    continue;
                }
                if (cur_ != DOT_VAR || nbDots_ > 0) {
                    return false;
                }
                nbDots_++;
                i_++;
                continue;
            }
            i_++;
        }
        return true;
    }
    public static Double parseDouble(String _nb) {
        NumberInfos infos_ = trySplitDouble(_nb);
        if (infos_ == null) {
            return null;
        }
        return parseDouble(infos_);
    }
    public static Float parseFloat(String _nb) {
        NumberInfos infos_ = trySplitDouble(_nb);
        if (infos_ == null) {
            return null;
        }
        double double_ = parseDouble(infos_);
        double abs_ = Math.abs(double_);
        if (abs_ > Float.MAX_VALUE) {
            return null;
        }
        return (float)double_;
    }
    public static NumberInfos trySplitDouble(String _nb) {
        if (_nb == null) {
            return null;
        }
        if (_nb.isEmpty()) {
            return null;
        }
        NumberInfos infos_ = new NumberInfos();
        int i_ = 0;
        if (!Character.isDigit(_nb.charAt(i_))) {
            if (_nb.charAt(i_) != MINUS_CHAR) {
                if (_nb.charAt(i_) != DOT_VAR) {
                    return null;
                }
                infos_.setPositive(true);
            } else {
                infos_.setPositive(false);
                i_++;
            }
        } else {
            infos_.setPositive(true);
        }
        int len_ = _nb.length();
        StringBuilder intPart_ = new StringBuilder();
        infos_.setIntPart(intPart_);
        StringBuilder decimalPart_ = new StringBuilder();
        infos_.setDecimalPart(decimalPart_);
        StringBuilder exponentialPart_ = new StringBuilder();
        infos_.setExponentialPart(exponentialPart_);
        while (i_ < len_) {
            char cur_ = _nb.charAt(i_);
            if (!Character.isDigit(cur_)) {
                if (_nb.charAt(i_) != DOT_VAR) {
                    if (Character.toLowerCase(_nb.charAt(i_)) != EXP) {
                        return null;
                    }
                }
                break;
            }
            intPart_.append(cur_);
            i_++;
        }
        if (i_ >= len_) {
            return infos_;
        }
        if (_nb.charAt(i_) == DOT_VAR) {
            i_++;
            while (i_ < len_) {
                char cur_ = _nb.charAt(i_);
                if (!Character.isDigit(cur_)) {
                    if (Character.toLowerCase(_nb.charAt(i_)) != EXP) {
                        return null;
                    }
                    break;
                }
                decimalPart_.append(cur_);
                i_++;
            }
        }
        if (i_ >= len_) {
            return infos_;
        }
        if (Character.toLowerCase(_nb.charAt(i_)) == EXP) {
            i_++;
            if (i_ >= len_) {
                return null;
            }
            char cur_ = _nb.charAt(i_);
            if (!Character.isDigit(cur_) && cur_ != MINUS_CHAR) {
                return null;
            }
            i_++;
            exponentialPart_.append(cur_);
            while (i_ < len_) {
                cur_ = _nb.charAt(i_);
                if (!Character.isDigit(cur_)) {
                    return null;
                }
                exponentialPart_.append(cur_);
                i_++;
            }
        }
        return infos_;
    }
    public static Byte parseByte(String _string) {
        Long int_ = parseLong(_string);
        if (int_ == null) {
            return null;
        }
        if (int_.longValue() < Byte.MIN_VALUE) {
            return null;
        }
        if (int_.longValue() > Byte.MAX_VALUE) {
            return null;
        }
        return int_.byteValue();
    }
    public static Short parseShort(String _string) {
        Long int_ = parseLong(_string);
        if (int_ == null) {
            return null;
        }
        if (int_.longValue() < Short.MIN_VALUE) {
            return null;
        }
        if (int_.longValue() > Short.MAX_VALUE) {
            return null;
        }
        return int_.shortValue();
    }
    public static Integer parseInt(String _string) {
        Long int_ = parseLong(_string);
        if (int_ == null) {
            return null;
        }
        if (int_.longValue() < Integer.MIN_VALUE) {
            return null;
        }
        if (int_.longValue() > Integer.MAX_VALUE) {
            return null;
        }
        return int_.intValue();
    }
    public static Long parseLong(String _string) {
        if (_string == null) {
            return null;
        }
        long result_ = 0;
        boolean negative_ = false;
        int i_ = 0;
        int max_ = _string.length();
        long limit_;
        long multmin_;
        int digit_;

        if (max_ > 0) {
            if (_string.charAt(0) == '-') {
                negative_ = true;
                limit_ = Long.MIN_VALUE;
                i_++;
            } else {
                limit_ = -Long.MAX_VALUE;
            }
            if (negative_) {
                multmin_ = MULTMIN_RADIX_TEN;
            } else {
                multmin_ = N_MULTMAX_RADIX_TEN;
            }
            if (i_ < max_) {
                char ch_ = _string.charAt(i_);
                i_++;
                if (ch_ < '0' || ch_ > '9') {
                    return null;
                } else {
                    digit_ = ch_ - '0';
                    result_ = -digit_;
                }
            }
            while (i_ < max_) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                char ch_ = _string.charAt(i_);
                i_++;
                if (ch_ < '0' || ch_ > '9') {
                    return null;
                }
                digit_ = ch_ - '0';
                if (result_ < multmin_) {
                    return null;
                }
                result_ *= 10;
                if (result_ < limit_ + digit_) {
                    return null;
                }
                result_ -= digit_;
            }
        } else {
            return null;
        }
        if (negative_) {
            if (i_ > 1) {
                return result_;
            } else {
                return null;
            }
        } else {
            return -result_;
        }
    }

    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        return new ResultErrorStd();
    }
    public static ResultErrorStd newInstance(ContextEl _cont, boolean _natvararg, ConstructorId _method, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getName();
        StringList list_ = _method.getParametersTypes();
        checkArgumentsForInvoking(_cont, _natvararg, list_, args_);
        if (_cont.getException() != null) {
            return result_;
        }
        LgNames lgNames_ = _cont.getStandards();
        Object[] argsObj_ = adaptedArgs(list_, _cont, _cont.getStandards(), args_);
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String stringType_ = lgNames_.getAliasString();
        String stringBuilderType_ = lgNames_.getAliasStringBuilder();
        String objectType_ = lgNames_.getAliasObject();
        String replType_ = lgNames_.getAliasReplacement();
        String intPrimType_ = lgNames_.getAliasPrimInteger();
        String charPrimType_ = lgNames_.getAliasPrimChar();
        String bytePrimType_ = lgNames_.getAliasPrimByte();
        String byteType_ = lgNames_.getAliasByte();
        String shortType_ = lgNames_.getAliasShort();
        String intType_ = lgNames_.getAliasInteger();
        String longType_ = lgNames_.getAliasLong();
        String floatType_ = lgNames_.getAliasFloat();
        String doubleType_ = lgNames_.getAliasDouble();
        if (StringList.quickEq(type_, booleanType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                String one_ = (String)argsObj_[0];
                result_.setResult(new BooleanStruct(Boolean.parseBoolean(one_)));
            } else {
                Boolean one_ = (Boolean)argsObj_[0];
                result_.setResult(new BooleanStruct(one_));
            }
        } else if (StringList.quickEq(type_, charType_)) {
            Character one_ = (Character)argsObj_[0];
            result_.setResult(new CharStruct(new Character(one_)));
        } else if (StringList.quickEq(type_, byteType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                String one_ = (String) argsObj_[0];
                Long lg_;
                int radix_ = DEFAULT_RADIX;
                if (list_.size() != 1) {
                    radix_ = (Integer) argsObj_[1];
                }
                lg_ = parseLong(one_, radix_);
                if (lg_ == null || lg_.longValue() < Byte.MIN_VALUE || lg_.longValue() > Byte.MAX_VALUE) {
                    result_.setError(lgNames_.getAliasNbFormat());
                } else {
                    result_.setResult(new ByteStruct(lg_.byteValue()));
                }
            } else {
                Byte one_ = (Byte) argsObj_[0];
                result_.setResult(new ByteStruct(one_));
            }
        } else if (StringList.quickEq(type_, shortType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                String one_ = (String) argsObj_[0];
                Long lg_;
                int radix_ = DEFAULT_RADIX;
                if (list_.size() != 1) {
                    radix_ = (Integer) argsObj_[1];
                }
                lg_ = parseLong(one_, radix_);
                if (lg_ == null || lg_.longValue() < Short.MIN_VALUE || lg_.longValue() > Short.MAX_VALUE) {
                    result_.setError(lgNames_.getAliasNbFormat());
                } else {
                    result_.setResult(new ShortStruct(lg_.shortValue()));
                }
            } else {
                Short one_ = (Short) argsObj_[0];
                result_.setResult(new ShortStruct(one_));
            }
        } else if (StringList.quickEq(type_, intType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                String one_ = (String) argsObj_[0];
                Long lg_;
                int radix_ = DEFAULT_RADIX;
                if (list_.size() != 1) {
                    radix_ = (Integer) argsObj_[1];
                }
                lg_ = parseLong(one_, radix_);
                if (lg_ == null || lg_.longValue() < Integer.MIN_VALUE || lg_.longValue() > Integer.MAX_VALUE) {
                    result_.setError(lgNames_.getAliasNbFormat());
                } else {
                    result_.setResult(new IntStruct(lg_.intValue()));
                }
            } else {
                Integer one_ = (Integer) argsObj_[0];
                result_.setResult(new IntStruct(one_));
            }
        } else if (StringList.quickEq(type_, longType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                String one_ = (String) argsObj_[0];
                Long lg_;
                int radix_ = DEFAULT_RADIX;
                if (list_.size() != 1) {
                    radix_ = (Integer) argsObj_[1];
                }
                lg_ = parseLong(one_, radix_);
                if (lg_ == null) {
                    result_.setError(lgNames_.getAliasNbFormat());
                } else {
                    result_.setResult(new LongStruct(lg_.longValue()));
                }
            } else {
                Long one_ = (Long) argsObj_[0];
                result_.setResult(new LongStruct(one_));
            }
        } else if (StringList.quickEq(type_, floatType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                String one_ = (String) argsObj_[0];
                NumberInfos infos_ = trySplitDouble(one_);
                boolean valid_ = true;
                if (infos_ == null) {
                    valid_ = false;
                }
                Double d_ = null;
                if (valid_) {
                    d_ = parseDouble(infos_);
                    double abs_ = Math.abs(d_);
                    if (abs_ > Float.MAX_VALUE) {
                        valid_ = false;
                    }
                }
                if (!valid_) {
                    result_.setError(lgNames_.getAliasNbFormat());
                } else {
                    result_.setResult(new FloatStruct(d_.floatValue()));
                }
            } else {
                Float one_ = (Float) argsObj_[0];
                result_.setResult(new FloatStruct(one_));
            }
        } else if (StringList.quickEq(type_, doubleType_)) {
            if (StringList.quickEq(list_.first(), stringType_)) {
                String one_ = (String) argsObj_[0];
                boolean valid_ = true;
                NumberInfos infos_ = trySplitDouble(one_);
                if (infos_ == null) {
                    valid_ = false;
                }
                Double d_ = null;
                if (valid_) {
                    d_ = parseDouble(infos_);
                }
                if (!valid_) {
                    result_.setError(lgNames_.getAliasNbFormat());
                } else {
                    result_.setResult(new DoubleStruct(d_.doubleValue()));
                }
            } else {
                Double one_ = (Double) argsObj_[0];
                result_.setResult(new DoubleStruct(one_));
            }
        } else if (StringList.quickEq(type_, replType_)) {
            result_.setResult(new ReplacementStruct(new Replacement()));
        } else if (StringList.quickEq(type_, stringType_)) {
            if (list_.isEmpty()) {
                result_.setResult(new StringStruct(EMPTY_STRING));
            } else if (list_.size() == 1) {
                if (StringList.quickEq(list_.first(), PrimitiveTypeUtil.getPrettyArrayType(bytePrimType_))) {
                    byte[] one_ = (byte[]) argsObj_[0];
                    if (one_ == null) {
                        result_.setError(lgNames_.getAliasNullPe());
                    } else {
                        result_.setResult(new StringStruct(new String(one_)));
                    }
                } else if (StringList.quickEq(list_.first(), PrimitiveTypeUtil.getPrettyArrayType(charPrimType_))) {
                    char[] one_ = (char[]) argsObj_[0];
                    if (one_ == null) {
                        result_.setError(lgNames_.getAliasNullPe());
                    } else {
                        result_.setResult(new StringStruct(new String(one_)));
                    }
                } else if (StringList.quickEq(list_.first(), stringBuilderType_)) {
                    StringBuilder one_ = (StringBuilder) argsObj_[0];
                    if (one_ == null) {
                        result_.setError(lgNames_.getAliasNullPe());
                    } else {
                        result_.setResult(new StringStruct(new String(one_)));
                    }
                }
            } else if (list_.size() == 2) {
                byte[] two_ = (byte[]) argsObj_[0];
                if (two_ == null) {
                    result_.setError(lgNames_.getAliasNullPe());
                } else {
                    String three_ = (String) argsObj_[1];
                    try {
                        result_.setResult(new StringStruct(new String(two_, three_)));
                    } catch (UnsupportedEncodingException _0) {
                        result_.setError(lgNames_.getAliasBadEncode());
                    }
                }
            } else if (list_.size() == THREE_ARGS) {
                if (StringList.quickEq(list_.first(), PrimitiveTypeUtil.getPrettyArrayType(bytePrimType_))) {
                    byte[] two_ = (byte[]) argsObj_[0];
                    if (two_ == null) {
                        result_.setError(lgNames_.getAliasNullPe());
                    } else {
                        Integer three_ = (Integer) argsObj_[1];
                        Integer four_ = (Integer) argsObj_[2];
                        if (three_ < 0 || four_ < 0 || three_ > two_.length - four_) {
                            result_.setError(lgNames_.getAliasBadIndex());
                        } else {
                            result_.setResult(new StringStruct(new String(two_, three_, four_)));
                        }
                    }
                } else {
                    char[] two_ = (char[]) argsObj_[0];
                    if (two_ == null) {
                        result_.setError(lgNames_.getAliasNullPe());
                    } else {
                        Integer three_ = (Integer) argsObj_[1];
                        Integer four_ = (Integer) argsObj_[2];
                        if (three_ < 0 || four_ < 0 || three_ > two_.length - four_) {
                            result_.setError(lgNames_.getAliasBadIndex());
                        } else {
                            result_.setResult(new StringStruct(new String(two_, three_, four_)));
                        }
                    }
                }
            } else {
                byte[] two_ = (byte[]) argsObj_[0];
                if (two_ == null) {
                    result_.setError(lgNames_.getAliasNullPe());
                } else {
                    Integer three_ = (Integer) argsObj_[1];
                    Integer four_ = (Integer) argsObj_[2];
                    String five_ = (String) argsObj_[THREE_ARGS];
                    try {
                        result_.setResult(new StringStruct(new String(two_, three_, four_, five_)));
                    } catch (UnsupportedEncodingException _0) {
                        result_.setError(lgNames_.getAliasBadEncode());
                    }
                }
            }
        } else if (StringList.quickEq(type_, stringBuilderType_)) {
            if (list_.isEmpty()) {
                result_.setResult(new StringBuilderStruct(new StringBuilder()));
            } else if (StringList.quickEq(list_.first(), intPrimType_)) {
                Integer two_ = (Integer) argsObj_[0];
                if (two_ < 0) {
                    result_.setError(lgNames_.getAliasBadIndex());
                } else {
                    result_.setResult(new StringBuilderStruct(new StringBuilder(two_)));
                }
            } else if (StringList.quickEq(list_.first(), stringType_)) {
                String two_ = (String) argsObj_[0];
                if (two_ == null) {
                    result_.setError(lgNames_.getAliasNullPe());
                } else {
                    result_.setResult(new StringBuilderStruct(new StringBuilder(two_)));
                }
            } else {
                CharSequence two_ = (CharSequence) argsObj_[0];
                if (two_ == null) {
                    result_.setError(lgNames_.getAliasNullPe());
                } else {
                    result_.setResult(new StringBuilderStruct(new StringBuilder(two_)));
                }
            }
        } else if (StringList.quickEq(type_, objectType_)) {
            result_.setResult(new StdStruct(new CustBase(), objectType_));
        } else {
            result_ = lgNames_.getOtherResult(_cont, _method, argsObj_);
        }
        return result_;
    }
    public ResultErrorStd getOtherResult(ContextEl _cont, ConstructorId _method, Object... _args) {
        return new ResultErrorStd();
    }
    public static ResultErrorStd getField(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd result_ = new ResultErrorStd();
        String type_ = _classField.getClassName();
        String name_ = _classField.getFieldName();
        LgNames lgNames_ = _cont.getStandards();
        String charType_ = lgNames_.getAliasCharacter();
        String byteType_ = lgNames_.getAliasByte();
        String shortType_ = lgNames_.getAliasShort();
        String intType_ = lgNames_.getAliasInteger();
        String longType_ = lgNames_.getAliasLong();
        String floatType_ = lgNames_.getAliasFloat();
        String doubleType_ = lgNames_.getAliasDouble();
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
        } else if (StringList.quickEq(type_, doubleType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasMinValueField())) {
                result_.setResult(new DoubleStruct(Double.MIN_VALUE));
            } else {
                result_.setResult(new DoubleStruct(Double.MAX_VALUE));
            }
        } else {
            result_ = lgNames_.getOtherResult(_cont, _classField, _instance);
        }
        return result_;
    }
    public ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance) {
        return new ResultErrorStd();
    }
    public static ResultErrorStd setField(ContextEl _cont, ClassField _classField, Struct _instance, Struct _value) {
        LgNames lgNames_ = _cont.getStandards();
        return lgNames_.setOtherResult(_cont, _classField, _instance, _value);
    }
    public ResultErrorStd setOtherResult(ContextEl _cont, ClassField _classField, Struct _instance, Struct _value) {
        return new ResultErrorStd();
    }
    static void checkArgumentsForInvoking(ContextEl _cont,boolean _natvararg, StringList _params,Struct... _args) {
        int len_ = _params.size();
        if (_natvararg) {
            len_--;
        }
        StringList traces_ = new StringList();
        for (int i = 0; i < len_; i++) {
            if (PrimitiveTypeUtil.primitiveTypeNullObject(_params.get(i), _args[i], _cont)) {
                traces_.add(StringList.concat(Long.toString(i),RETURN_LINE,_params.get(i),RETURN_LINE,null));
            }
        }
        LgNames stds_ = _cont.getStandards();
        String null_ = stds_.getAliasNullPe();
        if (!traces_.isEmpty()) {
            _cont.setException(new StdStruct(new CustomError(StringList.concat(traces_.join(SEP_ARG),RETURN_LINE,_cont.joinPages())),null_));
        }
    }
    static Struct[] getObjects(Argument... _args) {
        int len_ = _args.length;
        Struct[] classes_ = new Struct[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args[i].getStruct();
        }
        return classes_;
    }
    static Object[] adaptedArgs(StringList _params,ContextEl _context,LgNames _stds,Struct... _args) {
        int len_ = _params.size();
        Object[] args_ = new Object[len_];
        for (int i = 0; i < len_; i++) {
            Struct argStruct_ = _args[i];
            if (argStruct_.isNull()) {
                continue;
            }
            if (argStruct_ instanceof ArrayStruct) {
                Struct[] str_ = ((ArrayStruct) argStruct_).getInstance();
                String compo_ = PrimitiveTypeUtil.getQuickComponentType(argStruct_.getClassName(_context));
                if (StringList.quickEq(compo_, _stds.getAliasPrimByte())) {
                    byte[] adapt_ = new byte[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = (Byte) s.getInstance();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimShort())) {
                    short[] adapt_ = new short[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = (Short) s.getInstance();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimInteger())) {
                    int[] adapt_ = new int[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = (Integer) s.getInstance();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimChar())) {
                    char[] adapt_ = new char[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = (Character) s.getInstance();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimLong())) {
                    long[] adapt_ = new long[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = (Character) s.getInstance();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimFloat())) {
                    float[] adapt_ = new float[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = (Float) s.getInstance();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimDouble())) {
                    double[] adapt_ = new double[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = (Double) s.getInstance();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasString())) {
                    String[] adapt_ = new String[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = (String) s.getInstance();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasReplacement())) {
                    Replacement[] adapt_ = new Replacement[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = (Replacement) s.getInstance();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimBoolean())) {
                    boolean[] adapt_ = new boolean[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = (Boolean) s.getInstance();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                args_[i] = _stds.getOtherArguments(str_, compo_);
                continue;
            }
            String p_ = _params.get(i);
            ClassArgumentMatching cl_ = new ClassArgumentMatching(p_);
            cl_ = PrimitiveTypeUtil.toPrimitive(cl_, true, _stds);
            Object a_ = argStruct_.getInstance();
            if (cl_.matchClass(_stds.getAliasPrimDouble())) {
                if (a_ instanceof Number) {
                    args_[i] = ((Number)a_).doubleValue();
                }
            } else if (cl_.matchClass(_stds.getAliasPrimFloat())) {
                if (a_ instanceof Number) {
                    args_[i] = ((Number)a_).floatValue();
                }
            } else if (cl_.matchClass(_stds.getAliasPrimLong())) {
                if (a_ instanceof Number) {
                    args_[i] = ((Number)a_).longValue();
                }
            } else if (cl_.matchClass(_stds.getAliasPrimInteger())) {
                if (a_ instanceof Number) {
                    args_[i] = ((Number)a_).intValue();
                }
            } else if (cl_.matchClass(_stds.getAliasPrimShort())) {
                if (a_ instanceof Number) {
                    args_[i] = ((Number)a_).shortValue();
                }
            } else if (cl_.matchClass(_stds.getAliasPrimByte())) {
                if (a_ instanceof Number) {
                    args_[i] = ((Number)a_).byteValue();
                }
            } else {
                args_[i] = a_;
            }
        }
        return args_;
    }
    public Object getOtherArguments(Struct[] _str, String _base) {
        return null;
    }
    public static Struct getElement(Object _array, int _index, ContextEl _context) {
        if (_array instanceof Struct[]) {
            return ((Struct[])_array)[_index];
        }
        if (_array instanceof double[]) {
            return new DoubleStruct(((double[])_array)[_index]);
        }
        if (_array instanceof float[]) {
            return new FloatStruct(((float[])_array)[_index]);
        }
        if (_array instanceof long[]) {
            return new LongStruct(((long[])_array)[_index]);
        }
        if (_array instanceof int[]) {
            return new IntStruct(((int[])_array)[_index]);
        }
        if (_array instanceof char[]) {
            return new CharStruct(((char[])_array)[_index]);
        }
        if (_array instanceof short[]) {
            return new ShortStruct(((short[])_array)[_index]);
        }
        if (_array instanceof byte[]) {
            return new ByteStruct(((byte[])_array)[_index]);
        }
        if (_array instanceof boolean[]) {
            return new BooleanStruct(((boolean[])_array)[_index]);
        }
        return _context.getStandards().getOtherElement(_array, _index);
    }
    public Struct getOtherElement(Object _array, int _index) {
        return NullStruct.NULL_VALUE;
    }
    public static int getLength(Object _array) {
        if (_array instanceof double[]) {
            return ((double[])_array).length;
        }
        if (_array instanceof float[]) {
            return ((float[])_array).length;
        }
        if (_array instanceof long[]) {
            return ((long[])_array).length;
        }
        if (_array instanceof int[]) {
            return ((int[])_array).length;
        }
        if (_array instanceof char[]) {
            return ((char[])_array).length;
        }
        if (_array instanceof short[]) {
            return ((short[])_array).length;
        }
        if (_array instanceof byte[]) {
            return ((byte[])_array).length;
        }
        if (_array instanceof boolean[]) {
            return ((boolean[])_array).length;
        }
        return ((Object[])_array).length;
    }
    public static void setElement(Object _array, int _index, Struct _element, ContextEl _context) {
        if (_array instanceof Struct[]) {
            ((Struct[])_array)[_index] = _element;
            return;
        }
        if (_array instanceof double[]) {
            ((double[])_array)[_index] = (Double) _element.getInstance();
            return;
        }
        if (_array instanceof float[]) {
            ((float[])_array)[_index] = (Float) _element.getInstance();
            return;
        }
        if (_array instanceof long[]) {
            ((long[])_array)[_index] = (Long) _element.getInstance();
            return;
        }
        if (_array instanceof int[]) {
            ((int[])_array)[_index] = (Integer) _element.getInstance();
            return;
        }
        if (_array instanceof char[]) {
            ((char[])_array)[_index] = (Character) _element.getInstance();
            return;
        }
        if (_array instanceof short[]) {
            ((short[])_array)[_index] = (Short) _element.getInstance();
            return;
        }
        if (_array instanceof byte[]) {
            ((byte[])_array)[_index] = (Byte) _element.getInstance();
            return;
        }
        if (_array instanceof boolean[]) {
            ((boolean[])_array)[_index] = (Boolean) _element.getInstance();
            return;
        }
        _context.getStandards().setOtherElement(_array, _index, _element);
    }
    public void setOtherElement(Object _array, int _index, Struct _element) {
    }
    public String getStructClassName(Struct _struct, ContextEl _context) {
        if (!(_struct instanceof StdStruct) || _struct.getInstance() instanceof CustomError) {
            return _struct.getClassName(_context);
        }
        return getStructClassName(_struct.getInstance(), _context);
    }
    public String getStructClassName(Object _struct, ContextEl _context) {
        if (_struct instanceof Double) {
            return getAliasDouble();
        }
        if (_struct instanceof Float) {
            return getAliasFloat();
        }
        if (_struct instanceof Long) {
            return getAliasLong();
        }
        if (_struct instanceof Integer) {
            return getAliasInteger();
        }
        if (_struct instanceof Character) {
            return getAliasCharacter();
        }
        if (_struct instanceof Short) {
            return getAliasShort();
        }
        if (_struct instanceof Byte) {
            return getAliasByte();
        }
        if (_struct instanceof String) {
            return getAliasString();
        }
        if (_struct instanceof StringBuilder) {
            return getAliasStringBuilder();
        }
        if (_struct instanceof SimpleItr) {
            return getAliasSimpleIteratorType();
        }
        if (_struct instanceof SelectedBoolean) {
            return getSelectedBoolean();
        }
        return getOtherStructClassName(_struct, _context);
    }
    public String getOtherStructClassName(Object _struct, ContextEl _context) {
        return getAliasObject();
    }
    public StringMap<StandardType> getStandards() {
        return standards;
    }
    public ContextEl getContext() {
        return context;
    }
    public void setContext(ContextEl _context) {
        context = _context;
    }
    public String getAliasObject() {
        return aliasObject;
    }
    public void setAliasObject(String _aliasObject) {
        aliasObject = _aliasObject;
    }
    public String getAliasVoid() {
        return aliasVoid;
    }
    public void setAliasVoid(String _aliasVoid) {
        aliasVoid = _aliasVoid;
    }
    public String getAliasCharSequence() {
        return aliasCharSequence;
    }
    public void setAliasCharSequence(String _aliasCharSequence) {
        aliasCharSequence = _aliasCharSequence;
    }
    public String getAliasDisplayable() {
        return aliasDisplayable;
    }
    public void setAliasDisplayable(String _aliasDisplayable) {
        aliasDisplayable = _aliasDisplayable;
    }
    public String getAliasDisplay() {
        return aliasDisplay;
    }
    public void setAliasDisplay(String _aliasDisplay) {
        aliasDisplay = _aliasDisplay;
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
    public String getAliasToString() {
        return aliasToString;
    }
    public void setAliasToString(String _aliasToString) {
        aliasToString = _aliasToString;
    }
    public String getAliasValueOf() {
        return aliasValueOf;
    }
    public void setAliasValueOf(String _aliasValueOf) {
        aliasValueOf = _aliasValueOf;
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
    public String getAliasIterator() {
        return aliasIterator;
    }
    public void setAliasIterator(String _aliasIterator) {
        aliasIterator = _aliasIterator;
    }
    public String getAliasIteratorType() {
        return aliasIteratorType;
    }
    public void setAliasIteratorType(String _aliasIteratorType) {
        aliasIteratorType = _aliasIteratorType;
    }
    public String getAliasIterable() {
        return aliasIterable;
    }
    public void setAliasIterable(String _aliasIterable) {
        aliasIterable = _aliasIterable;
    }
    public String getAliasEnumParam() {
        return aliasEnumParam;
    }
    public void setAliasEnumParam(String _aliasEnumParam) {
        aliasEnumParam = _aliasEnumParam;
    }
    public String getAliasEnum() {
        return aliasEnum;
    }
    public void setAliasEnum(String _aliasEnum) {
        aliasEnum = _aliasEnum;
    }
    public String getAliasError() {
        return aliasError;
    }
    public void setAliasError(String _aliasError) {
        aliasError = _aliasError;
    }
    public String getAliasCustomError() {
        return aliasCustomError;
    }
    public void setAliasCustomError(String _aliasCustomError) {
        aliasCustomError = _aliasCustomError;
    }
    public String getAliasBadSize() {
        return aliasBadSize;
    }
    public void setAliasBadSize(String _aliasBadSize) {
        aliasBadSize = _aliasBadSize;
    }
    public String getAliasDivisionZero() {
        return aliasDivisionZero;
    }
    public void setAliasDivisionZero(String _aliasDivisionZero) {
        aliasDivisionZero = _aliasDivisionZero;
    }
    public String getAliasCast() {
        return aliasCast;
    }
    public void setAliasCast(String _aliasCast) {
        aliasCast = _aliasCast;
    }
    public String getAliasStore() {
        return aliasStore;
    }
    public void setAliasStore(String _aliasStore) {
        aliasStore = _aliasStore;
    }
    public String getAliasNullPe() {
        return aliasNullPe;
    }
    public void setAliasNullPe(String _aliasNullPe) {
        aliasNullPe = _aliasNullPe;
    }
    public String getAliasNbFormat() {
        return aliasNbFormat;
    }
    public void setAliasNbFormat(String _aliasNbFormat) {
        aliasNbFormat = _aliasNbFormat;
    }
    public String getAliasBadEncode() {
        return aliasBadEncode;
    }
    public void setAliasBadEncode(String _aliasBadEncode) {
        aliasBadEncode = _aliasBadEncode;
    }
    public String getAliasBadIndex() {
        return aliasBadIndex;
    }
    public void setAliasBadIndex(String _aliasBadIndex) {
        aliasBadIndex = _aliasBadIndex;
    }
    public String getAliasSof() {
        return aliasSof;
    }
    public void setAliasSof(String _aliasSof) {
        aliasSof = _aliasSof;
    }
    public String getAliasPrimBoolean() {
        return aliasPrimBoolean;
    }
    public void setAliasPrimBoolean(String _aliasPrimBoolean) {
        aliasPrimBoolean = _aliasPrimBoolean;
    }
    public String getAliasMath() {
        return aliasMath;
    }
    public void setAliasMath(String _aliasMath) {
        aliasMath = _aliasMath;
    }
    public String getAliasPrimByte() {
        return aliasPrimByte;
    }
    public void setAliasPrimByte(String _aliasPrimByte) {
        aliasPrimByte = _aliasPrimByte;
    }
    public String getAliasPrimShort() {
        return aliasPrimShort;
    }
    public void setAliasPrimShort(String _aliasPrimShort) {
        aliasPrimShort = _aliasPrimShort;
    }
    public String getAliasPrimChar() {
        return aliasPrimChar;
    }
    public void setAliasPrimChar(String _aliasPrimChar) {
        aliasPrimChar = _aliasPrimChar;
    }
    public String getAliasPrimInteger() {
        return aliasPrimInteger;
    }
    public void setAliasPrimInteger(String _aliasPrimInteger) {
        aliasPrimInteger = _aliasPrimInteger;
    }
    public String getAliasPrimLong() {
        return aliasPrimLong;
    }
    public void setAliasPrimLong(String _aliasPrimLong) {
        aliasPrimLong = _aliasPrimLong;
    }
    public String getAliasPrimFloat() {
        return aliasPrimFloat;
    }
    public void setAliasPrimFloat(String _aliasPrimFloat) {
        aliasPrimFloat = _aliasPrimFloat;
    }
    public String getAliasPrimDouble() {
        return aliasPrimDouble;
    }
    public void setAliasPrimDouble(String _aliasPrimDouble) {
        aliasPrimDouble = _aliasPrimDouble;
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
    public String getAliasGetType() {
        return aliasGetType;
    }
    public void setAliasGetType(String _aliasGetType) {
        aliasGetType = _aliasGetType;
    }
    public String getAliasString() {
        return aliasString;
    }
    public void setAliasString(String _aliasString) {
        aliasString = _aliasString;
    }
    public String getAliasLength() {
        return aliasLength;
    }
    public void setAliasLength(String _aliasLength) {
        aliasLength = _aliasLength;
    }
    public String getAliasCharAt() {
        return aliasCharAt;
    }
    public void setAliasCharAt(String _aliasCharAt) {
        aliasCharAt = _aliasCharAt;
    }
    public String getAliasToCharArray() {
        return aliasToCharArray;
    }
    public void setAliasToCharArray(String _aliasToCharArray) {
        aliasToCharArray = _aliasToCharArray;
    }
    public String getAliasSplit() {
        return aliasSplit;
    }
    public void setAliasSplit(String _aliasSplit) {
        aliasSplit = _aliasSplit;
    }
    public String getAliasSplitStrings() {
        return aliasSplitStrings;
    }
    public void setAliasSplitStrings(String _aliasSplitStrings) {
        aliasSplitStrings = _aliasSplitStrings;
    }
    public String getAliasSplitChars() {
        return aliasSplitChars;
    }
    public void setAliasSplitChars(String _aliasSplitChars) {
        aliasSplitChars = _aliasSplitChars;
    }
    public String getAliasReplace() {
        return aliasReplace;
    }
    public void setAliasReplace(String _aliasReplace) {
        aliasReplace = _aliasReplace;
    }
    public String getAliasReplaceMultiple() {
        return aliasReplaceMultiple;
    }
    public void setAliasReplaceMultiple(String _aliasReplaceMultiple) {
        aliasReplaceMultiple = _aliasReplaceMultiple;
    }
    public String getAliasEqualsIgnoreCase() {
        return aliasEqualsIgnoreCase;
    }
    public void setAliasEqualsIgnoreCase(String _aliasEqualsIgnoreCase) {
        aliasEqualsIgnoreCase = _aliasEqualsIgnoreCase;
    }
    public String getAliasCompareToIgnoreCase() {
        return aliasCompareToIgnoreCase;
    }
    public void setAliasCompareToIgnoreCase(String _aliasCompareToIgnoreCase) {
        aliasCompareToIgnoreCase = _aliasCompareToIgnoreCase;
    }
    public String getAliasContains() {
        return aliasContains;
    }
    public void setAliasContains(String _aliasContains) {
        aliasContains = _aliasContains;
    }
    public String getAliasEndsWith() {
        return aliasEndsWith;
    }
    public void setAliasEndsWith(String _aliasEndsWith) {
        aliasEndsWith = _aliasEndsWith;
    }
    public String getAliasStartsWith() {
        return aliasStartsWith;
    }
    public void setAliasStartsWith(String _aliasStartsWith) {
        aliasStartsWith = _aliasStartsWith;
    }
    public String getAliasIndexOf() {
        return aliasIndexOf;
    }
    public void setAliasIndexOf(String _aliasIndexOf) {
        aliasIndexOf = _aliasIndexOf;
    }
    public String getAliasFormat() {
        return aliasFormat;
    }
    public void setAliasFormat(String _aliasFormat) {
        aliasFormat = _aliasFormat;
    }
    public String getAliasGetBytes() {
        return aliasGetBytes;
    }
    public void setAliasGetBytes(String _aliasGetBytes) {
        aliasGetBytes = _aliasGetBytes;
    }
    public String getAliasIntern() {
        return aliasIntern;
    }
    public void setAliasIntern(String _aliasIntern) {
        aliasIntern = _aliasIntern;
    }
    public String getAliasIsEmpty() {
        return aliasIsEmpty;
    }
    public void setAliasIsEmpty(String _aliasIsEmpty) {
        aliasIsEmpty = _aliasIsEmpty;
    }
    public String getAliasLastIndexOf() {
        return aliasLastIndexOf;
    }
    public void setAliasLastIndexOf(String _aliasLastIndexOf) {
        aliasLastIndexOf = _aliasLastIndexOf;
    }
    public String getAliasRegionMatches() {
        return aliasRegionMatches;
    }
    public void setAliasRegionMatches(String _aliasRegionMatches) {
        aliasRegionMatches = _aliasRegionMatches;
    }
    public String getAliasSubstring() {
        return aliasSubstring;
    }
    public void setAliasSubstring(String _aliasSubstring) {
        aliasSubstring = _aliasSubstring;
    }
    public String getAliasSubSequence() {
        return aliasSubSequence;
    }
    public void setAliasSubSequence(String _aliasSubSequence) {
        aliasSubSequence = _aliasSubSequence;
    }
    public String getAliasToLowerCase() {
        return aliasToLowerCase;
    }
    public void setAliasToLowerCase(String _aliasToLowerCase) {
        aliasToLowerCase = _aliasToLowerCase;
    }
    public String getAliasToUpperCase() {
        return aliasToUpperCase;
    }
    public void setAliasToUpperCase(String _aliasToUpperCase) {
        aliasToUpperCase = _aliasToUpperCase;
    }
    public String getAliasTrim() {
        return aliasTrim;
    }
    public void setAliasTrim(String _aliasTrim) {
        aliasTrim = _aliasTrim;
    }
    public String getAliasStringBuilder() {
        return aliasStringBuilder;
    }
    public void setAliasStringBuilder(String _aliasStringBuilder) {
        aliasStringBuilder = _aliasStringBuilder;
    }
    public String getAliasAppend() {
        return aliasAppend;
    }
    public void setAliasAppend(String _aliasAppend) {
        aliasAppend = _aliasAppend;
    }
    public String getAliasCapacity() {
        return aliasCapacity;
    }
    public void setAliasCapacity(String _aliasCapacity) {
        aliasCapacity = _aliasCapacity;
    }
    public String getAliasClear() {
        return aliasClear;
    }
    public void setAliasClear(String _aliasClear) {
        aliasClear = _aliasClear;
    }
    public String getAliasDelete() {
        return aliasDelete;
    }
    public void setAliasDelete(String _aliasDelete) {
        aliasDelete = _aliasDelete;
    }
    public String getAliasDeleteCharAt() {
        return aliasDeleteCharAt;
    }
    public void setAliasDeleteCharAt(String _aliasDeleteCharAt) {
        aliasDeleteCharAt = _aliasDeleteCharAt;
    }
    public String getAliasEnsureCapacity() {
        return aliasEnsureCapacity;
    }
    public void setAliasEnsureCapacity(String _aliasEnsureCapacity) {
        aliasEnsureCapacity = _aliasEnsureCapacity;
    }
    public String getAliasInsert() {
        return aliasInsert;
    }
    public void setAliasInsert(String _aliasInsert) {
        aliasInsert = _aliasInsert;
    }
    public String getAliasReverse() {
        return aliasReverse;
    }
    public void setAliasReverse(String _aliasReverse) {
        aliasReverse = _aliasReverse;
    }
    public String getAliasSetCharAt() {
        return aliasSetCharAt;
    }
    public void setAliasSetCharAt(String _aliasSetCharAt) {
        aliasSetCharAt = _aliasSetCharAt;
    }
    public String getAliasSetLength() {
        return aliasSetLength;
    }
    public void setAliasSetLength(String _aliasSetLength) {
        aliasSetLength = _aliasSetLength;
    }
    public String getAliasTrimToSize() {
        return aliasTrimToSize;
    }
    public void setAliasTrimToSize(String _aliasTrimToSize) {
        aliasTrimToSize = _aliasTrimToSize;
    }
    public String getAliasCountable() {
        return aliasCountable;
    }
    public void setAliasCountable(String _aliasCountable) {
        aliasCountable = _aliasCountable;
    }
    public String getAliasGet() {
        return aliasGet;
    }
    public void setAliasGet(String _aliasGet) {
        aliasGet = _aliasGet;
    }
    public String getAliasSize() {
        return aliasSize;
    }
    public void setAliasSize(String _aliasSize) {
        aliasSize = _aliasSize;
    }
    public String getAliasSimpleIterator() {
        return aliasSimpleIterator;
    }
    public void setAliasSimpleIterator(String _aliasSimpleIterator) {
        aliasSimpleIterator = _aliasSimpleIterator;
    }
    public String getAliasNext() {
        return aliasNext;
    }
    public void setAliasNext(String _aliasNext) {
        aliasNext = _aliasNext;
    }
    public String getAliasHasNext() {
        return aliasHasNext;
    }
    public void setAliasHasNext(String _aliasHasNext) {
        aliasHasNext = _aliasHasNext;
    }
    public String getAliasName() {
        return aliasName;
    }
    public void setAliasName(String _aliasName) {
        aliasName = _aliasName;
    }
    public String getAliasOrdinal() {
        return aliasOrdinal;
    }
    public void setAliasOrdinal(String _aliasOrdinal) {
        aliasOrdinal = _aliasOrdinal;
    }
    public String getAliasReplacement() {
        return aliasReplacement;
    }
    public void setAliasReplacement(String _aliasReplacement) {
        aliasReplacement = _aliasReplacement;
    }
    public String getAliasGetOldString() {
        return aliasGetOldString;
    }
    public void setAliasGetOldString(String _aliasGetOldString) {
        aliasGetOldString = _aliasGetOldString;
    }
    public String getAliasGetNewString() {
        return aliasGetNewString;
    }
    public void setAliasGetNewString(String _aliasGetNewString) {
        aliasGetNewString = _aliasGetNewString;
    }
    public String getAliasSetOldString() {
        return aliasSetOldString;
    }
    public void setAliasSetOldString(String _aliasSetOldString) {
        aliasSetOldString = _aliasSetOldString;
    }
    public String getAliasSetNewString() {
        return aliasSetNewString;
    }
    public void setAliasSetNewString(String _aliasSetNewString) {
        aliasSetNewString = _aliasSetNewString;
    }
    public String getAliasAbs() {
        return aliasAbs;
    }
    public void setAliasAbs(String _aliasAbs) {
        aliasAbs = _aliasAbs;
    }
    public String getAliasQuot() {
        return aliasQuot;
    }
    public void setAliasQuot(String _aliasQuot) {
        aliasQuot = _aliasQuot;
    }
    public String getAliasMod() {
        return aliasMod;
    }
    public void setAliasMod(String _aliasMod) {
        aliasMod = _aliasMod;
    }
    public String getAliasSimpleIteratorType() {
        return aliasSimpleIteratorType;
    }
    public void setAliasSimpleIteratorType(String _aliasSimpleIteratorType) {
        aliasSimpleIteratorType = _aliasSimpleIteratorType;
    }
    public String getAliasSimpleIterableType() {
        return aliasSimpleIterableType;
    }
    public void setAliasSimpleIterableType(String _aliasSimpleIterableType) {
        aliasSimpleIterableType = _aliasSimpleIterableType;
    }
    public void setStandards(StringMap<StandardType> _standards) {
        standards = _standards;
    }
    public String getSelectedBoolean() {
        return selectedBoolean;
    }
    public void setSelectedBoolean(String _selectedBoolean) {
        selectedBoolean = _selectedBoolean;
    }
    public String getPrettyString() {
        StringBuilder str_ = new StringBuilder();
        for (StandardType s: standards.values()) {
            str_.append(s.getPrettyString());
            str_.append("\n");
        }
        return str_.toString();
    }
    public String getOvPrettyString() {
        StringBuilder str_ = new StringBuilder();
        for (StandardType s: standards.values()) {
            str_.append(s.getName());
            str_.append("\n");
            for (EntryCust<MethodId, EqList<ClassMethodId>> e:s.getAllOverridingMethods().entryList()){
                str_.append(e.getKey().getSignature());
                str_.append(":");
                for (ClassMethodId v: e.getValue()) {
                    str_.append(v.getClassName());
                    str_.append(".");
                    str_.append(v.getConstraints().getSignature());
                    str_.append(",");
                }
                str_.append("\n");
            }
            str_.append("\n");
        }
        return str_.toString();
    }
}
