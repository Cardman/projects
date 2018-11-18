package code.expressionlanguage.stds;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.LgAdv;
import code.expressionlanguage.NumberInfos;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.AbstractForEachLoop;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ForEachLoop;
import code.expressionlanguage.methods.PredefinedClasses;
import code.expressionlanguage.opers.Calculation;
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
import code.expressionlanguage.opers.util.EnumerableStruct;
import code.expressionlanguage.opers.util.FloatStruct;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.LongStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.NumberStruct;
import code.expressionlanguage.opers.util.ReplacementStruct;
import code.expressionlanguage.opers.util.ShortStruct;
import code.expressionlanguage.opers.util.SimpleObjectStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.StringBuilderStruct;
import code.expressionlanguage.opers.util.StringStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.Replacement;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.ComparatorBoolean;
import code.util.ints.Displayable;

public abstract class LgNames {
    protected static final int DEFAULT_RADIX = 10;
    protected static final String EMPTY_STRING = "";
    protected static final String RETURN_LINE = "\n";
    protected static final String SPACE = " ";

    protected static final char PAR_LEFT = '(';
    protected static final char SEP_ARG = ',';
    protected static final char PAR_RIGHT = ')';
    protected static final String DOT = ".";
    protected static final String VARARG_SUFFIX = "...";
    protected static final String LOC_VAR = ".";

    protected static final String PARS = "()";
    private static final byte HEX_BASE = 16;
    private static final char DOT_VAR = '.';
    private static final char EXP_UPP = 'E';
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
    private String aliasValueOf;
    private String aliasMaxValueField;
    private String aliasMinValueField;

    private String aliasIteratorType;
    private String aliasIterator;
    private String aliasIterable;
    private String aliasEnumParam;
    private String aliasEnum;
    private String aliasEnums;
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
    private String aliasErrorInitClass;
    private String aliasClone;
    private String aliasValues;

    private StringMap<StandardType> standards = new StringMap<StandardType>();

    private StringList predefinedClasses = new StringList();
    private StringList predefinedInterfacesInitOrder = new StringList();

    private String aliasObjectsUtil;
    private String aliasSameRef;
    private String aliasGetParent;
    private AliasReflection reflect = new AliasReflection();
    private AliasMath mathRef = new AliasMath();
    private StringMap<PrimitiveType> primitiveTypes = new StringMap<PrimitiveType>();
    private String trueString;
    private String falseString;
    private String nullString;
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
        stdcl_ = new StandardClass(aliasErrorInitClass, fields_, constructors_, methods_, aliasError, MethodModifier.NORMAL);
        std_ = stdcl_;
        standards.put(aliasErrorInitClass, std_);
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
        stdcl_ = new StandardClass(aliasCharacter, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL);
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
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimByte), aliasPrimInteger, aliasPrimInteger);
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
        
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasEnums, fields_, constructors_, methods_, aliasObject, MethodModifier.FINAL);
        params_ = new StringList(aliasEnum);
        method_ = new StandardMethod(aliasName, params_, aliasString, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasEnum);
        method_ = new StandardMethod(aliasOrdinal, params_, aliasPrimInteger, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        getStandards().put(aliasEnums, stdcl_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasObjectsUtil, fields_, constructors_, methods_, aliasObject, MethodModifier.ABSTRACT);
        params_ = new StringList(aliasObject,aliasObject);
        method_ = new StandardMethod(aliasSameRef, params_, aliasPrimBoolean, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasObject);
        method_ = new StandardMethod(aliasGetParent, params_, aliasObject, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        getStandards().put(aliasObjectsUtil, stdcl_);
        reflect.build(this);
        mathRef.build(this);
        buildOther();
        buildPrimitiveTypes();
    }
    private void buildPrimitiveTypes() {
        primitiveTypes.put(aliasPrimBoolean, new PrimitiveType(aliasPrimBoolean, aliasBoolean, EMPTY_STRING,false));
        primitiveTypes.put(aliasPrimChar, new PrimitiveType(aliasPrimChar, aliasCharacter, aliasPrimInteger,true));
        primitiveTypes.put(aliasPrimByte, new PrimitiveType(aliasPrimByte, aliasByte, aliasPrimShort,true));
        primitiveTypes.put(aliasPrimShort, new PrimitiveType(aliasPrimShort, aliasShort, aliasPrimInteger,true));
        primitiveTypes.put(aliasPrimInteger, new PrimitiveType(aliasPrimInteger, aliasInteger, aliasPrimLong,true));
        primitiveTypes.put(aliasPrimLong, new PrimitiveType(aliasPrimLong, aliasLong, aliasPrimFloat,true));
        primitiveTypes.put(aliasPrimFloat, new PrimitiveType(aliasPrimFloat, aliasFloat, aliasPrimDouble,true));
        primitiveTypes.put(aliasPrimDouble, new PrimitiveType(aliasPrimDouble, aliasDouble, EMPTY_STRING,true));
    }
    public String toWrapper(String _type) {
        if (primitiveTypes.contains(_type)) {
            return primitiveTypes.getVal(_type).getWrapper();
        }
        return _type;
    }
    public String toPrimitive(String _type) {
        for (EntryCust<String, PrimitiveType> e: primitiveTypes.entryList()) {
            if (StringList.quickEq(e.getValue().getWrapper(), _type)) {
                return e.getKey();
            }
        }
        return _type;
    }
    public void validateStandards() {
        StringList list_ = new StringList();
        list_.add(aliasPrimBoolean);
        list_.add(aliasPrimByte);
        list_.add(aliasPrimShort);
        list_.add(aliasPrimChar);
        list_.add(aliasPrimInteger);
        list_.add(aliasPrimLong);
        list_.add(aliasPrimFloat);
        list_.add(aliasPrimDouble);
        list_.add(aliasVoid);
        list_.add(reflect.getAliasAnnotated());
        list_.add(reflect.getAliasAnnotation());
        list_.add(reflect.getAliasClass());
        list_.add(reflect.getAliasConstructor());
        list_.add(aliasEnumParam);
        list_.add(reflect.getAliasFct());
        list_.add(reflect.getAliasField());
        list_.add(reflect.getAliasMethod());
        list_.add(aliasObjectsUtil);
        list_.add(reflect.getAliasClassNotFoundError());
        list_.add(aliasCustomError);
        list_.add(aliasErrorInitClass);
        list_.add(aliasEnum);
        list_.add(aliasEnums);
        list_.add(reflect.getAliasInvokeTarget());
        list_.add(aliasIterable);
        list_.add(aliasIterator);
        list_.add(aliasMath);
        list_.add(aliasBadEncode);
        list_.add(aliasBadIndex);
        list_.add(aliasDivisionZero);
        list_.add(aliasStore);
        list_.add(aliasCast);
        list_.add(aliasBadSize);
        list_.add(aliasSof);
        list_.add(aliasReplacement);
        list_.add(aliasNullPe);
        list_.add(aliasDisplayable);
        list_.add(aliasBoolean);
        list_.add(aliasByte);
        list_.add(aliasCharSequence);
        list_.add(aliasCharacter);
        list_.add(aliasDouble);
        list_.add(aliasError);
        list_.add(aliasFloat);
        list_.add(aliasInteger);
        list_.add(aliasLong);
        list_.add(aliasNumber);
        list_.add(aliasObject);
        list_.add(aliasShort);
        list_.add(aliasString);
        list_.add(aliasStringBuilder);
        int size_ = list_.size();
        list_.removeDuplicates();
        if (size_ != list_.size()) {
            return;
        }
        StringMap<StringList> map_ = new StringMap<StringList>();
        map_.put(reflect.getAliasAnnotated(), new StringList(
                reflect.getAliasGetAnnotations(),
                reflect.getAliasGetAnnotationsParameters()));
        map_.put(reflect.getAliasAnnotation(), new StringList(reflect.getAliasGetString()));
        map_.put(reflect.getAliasClass(), new StringList(
                reflect.getAliasDefaultInstance(),
                reflect.getAliasEnumValueOf(),
                reflect.getAliasForName(),
                reflect.getAliasArrayGet(),
                reflect.getAliasGetActualTypeArguments(),
                reflect.getAliasGetAllClasses(),
                reflect.getAliasGetBounds(),
                reflect.getAliasGetClass(),
                reflect.getAliasGetComponentType(),
                reflect.getAliasGetDeclaredClasses(),
                reflect.getAliasGetDeclaredConstructors(),
                reflect.getAliasGetDeclaredFields(),
                reflect.getAliasGetDeclaredMethods(),
                reflect.getAliasGetEnclosingType(),
                reflect.getAliasGetEnumConstants(),
                reflect.getAliasGetGenericBounds(),
                reflect.getAliasGetGenericInterfaces(),
                reflect.getAliasGetGenericSuperClass(),
                reflect.getAliasGetGenericTypeArguments(),
                reflect.getAliasGetGenericVariableOwner(),
                reflect.getAliasGetInterfaces(),
                reflect.getAliasArrayGetLength(),
                reflect.getAliasGetLowerBounds(),
                reflect.getAliasGetName(),
                reflect.getAliasGetOperators(),
                reflect.getAliasGetPrettyName(),
                reflect.getAliasGetSuperClass(),
                reflect.getAliasGetTypeParameters(),
                reflect.getAliasGetUpperBounds(),
                reflect.getAliasGetVariableOwner(),
                reflect.getAliasInit(),
                reflect.getAliasIsAbstract(),
                reflect.getAliasIsAnnotation(),
                reflect.getAliasIsArray(),
                reflect.getAliasIsAssignableFrom(),
                reflect.getAliasIsClass(),
                reflect.getAliasIsEnum(),
                reflect.getAliasIsFinal(),
                reflect.getAliasIsInstance(),
                reflect.getAliasIsInterface(),
                reflect.getAliasIsPackage(),
                reflect.getAliasIsPrimitive(),
                reflect.getAliasIsPrivate(),
                reflect.getAliasIsProtected(),
                reflect.getAliasIsPublic(),
                reflect.getAliasIsStatic(),
                reflect.getAliasIsWildCard(),
                reflect.getAliasMakeArray(),
                reflect.getAliasMakeGeneric(),
                reflect.getAliasMakeWildCard(),
                reflect.getAliasArrayNewInstance(),
                reflect.getAliasArraySet()));
        map_.put(reflect.getAliasConstructor(), new StringList(
                reflect.getAliasGetDeclaringClass(),
                reflect.getAliasGetGenericReturnType(),
                reflect.getAliasGetName(),
                reflect.getAliasGetParameterNames(),
                reflect.getAliasGetParameterTypes(),
                reflect.getAliasGetReturnType(),
                reflect.getAliasIsPackage(),
                reflect.getAliasIsPrivate(),
                reflect.getAliasIsProtected(),
                reflect.getAliasIsPublic(),
                reflect.getAliasIsVarargs(),
                reflect.getAliasNewInstance()));
        map_.put(reflect.getAliasFct(), new StringList(reflect.getAliasCall()));
        map_.put(reflect.getAliasField(), new StringList(
                reflect.getAliasArrayGet(),
                reflect.getAliasGetDeclaringClass(),
                reflect.getAliasGetGenericType(),
                reflect.getAliasGetName(),
                reflect.getAliasGetType(),
                reflect.getAliasIsFinal(),
                reflect.getAliasIsPackage(),
                reflect.getAliasIsPrivate(),
                reflect.getAliasIsProtected(),
                reflect.getAliasIsPublic(),
                reflect.getAliasIsStatic(),
                reflect.getAliasSetField()));
        map_.put(reflect.getAliasMethod(), new StringList(
                reflect.getAliasGetDeclaringClass(),
                reflect.getAliasGetDefaultValue(),
                reflect.getAliasGetGenericReturnType(),
                reflect.getAliasGetName(),
                reflect.getAliasGetParameterNames(),
                reflect.getAliasGetParameterTypes(),
                reflect.getAliasGetReturnType(),
                reflect.getAliasInvoke(),
                reflect.getAliasIsAbstract(),
                reflect.getAliasIsFinal(),
                reflect.getAliasIsNormal(),
                reflect.getAliasIsPackage(),
                reflect.getAliasIsPolymorph(),
                reflect.getAliasIsPrivate(),
                reflect.getAliasIsProtected(),
                reflect.getAliasIsPublic(),
                reflect.getAliasIsStatic(),
                reflect.getAliasIsVarargs(),
                reflect.getAliasSetPolymorph()));
        map_.put(aliasObjectsUtil, new StringList(
                aliasSameRef,
                aliasGetParent));
        map_.put(aliasEnum, new StringList(
                aliasName,
                aliasOrdinal));
        map_.put(aliasEnums, new StringList(
                aliasName,
                aliasOrdinal));
        map_.put(aliasIterable, new StringList(
                aliasIterator));
        map_.put(aliasIterator, new StringList(
                aliasHasNext,
                aliasNext));
        map_.put(mathRef.getAliasMath(), new StringList(
                mathRef.getAliasAbs(),
                mathRef.getAliasMod(),
                mathRef.getAliasQuot(),
                mathRef.getAliasBinMod(),
                mathRef.getAliasBinQuot(),
                mathRef.getAliasPlus(),
                mathRef.getAliasMinus(),
                mathRef.getAliasMult(),
                mathRef.getAliasNegBin(),
                mathRef.getAliasAnd(),
                mathRef.getAliasOr(),
                mathRef.getAliasXor(),
                mathRef.getAliasLe(),
                mathRef.getAliasGe(),
                mathRef.getAliasLt(),
                mathRef.getAliasGt(),
                mathRef.getAliasShiftLeft(),
                mathRef.getAliasShiftRight(),
                mathRef.getAliasBitShiftLeft(),
                mathRef.getAliasBitShiftRight(),
                mathRef.getAliasRotateLeft(),
                mathRef.getAliasRotateRight()));
        map_.put(aliasReplacement, new StringList(
                aliasGetNewString,
                aliasGetOldString,
                aliasSetNewString,
                aliasSetOldString));
        map_.put(aliasDisplayable, new StringList(
                aliasDisplay));
        map_.put(aliasBoolean, new StringList(
                aliasBooleanValue,
                aliasCompare,
                aliasCompareTo,
                aliasEquals,
                aliasParseBoolean,
                aliasToString,
                aliasValueOf));
        map_.put(aliasByte, new StringList(
                aliasByteValue,
                aliasCompare,
                aliasCompareTo,
                aliasDoubleValue,
                aliasEquals,
                aliasFloatValue,
                aliasIntValue,
                aliasLongValue,
                aliasParseByte,
                aliasShortValue,
                aliasToString));
        map_.put(aliasCharSequence, new StringList(
                aliasCharAt,
                aliasLength,
                aliasSubSequence));
        map_.put(aliasCharacter, new StringList(
                aliasCharAt,
                aliasCharValue,
                aliasCompare,
                aliasCompareTo,
                aliasDigit,
                aliasForDigit,
                aliasGetType,
                aliasIsDigit,
                aliasGetDirectionality,
                aliasIsLetter,
                aliasIsLetterOrDigit,
                aliasIsLowerCase,
                aliasIsSpace,
                aliasIsUpperCase,
                aliasIsWhitespace,
                aliasIsWordChar,
                aliasLength,
                aliasSubSequence,
                aliasToLowerCase,
                aliasToString,
                aliasToUpperCase));
        map_.put(aliasDouble, new StringList(
                aliasByteValue,
                aliasCompare,
                aliasCompareTo,
                aliasDoubleValue,
                aliasEquals,
                aliasFloatValue,
                aliasIntValue,
                aliasIsInfinite,
                aliasIsNan,
                aliasIsNan,
                aliasLongValue,
                aliasParseDouble,
                aliasShortValue,
                aliasToString));
        map_.put(aliasFloat, new StringList(
                aliasByteValue,
                aliasCompare,
                aliasCompareTo,
                aliasDoubleValue,
                aliasEquals,
                aliasFloatValue,
                aliasIntValue,
                aliasIsInfinite,
                aliasIsNan,
                aliasIsNan,
                aliasLongValue,
                aliasParseFloat,
                aliasShortValue,
                aliasToString));
        map_.put(aliasInteger, new StringList(
                aliasByteValue,
                aliasCompare,
                aliasCompareTo,
                aliasDoubleValue,
                aliasEquals,
                aliasFloatValue,
                aliasIntValue,
                aliasLongValue,
                aliasParseInt,
                aliasShortValue,
                aliasToString));
        map_.put(aliasLong, new StringList(
                aliasByteValue,
                aliasCompare,
                aliasCompareTo,
                aliasDoubleValue,
                aliasEquals,
                aliasFloatValue,
                aliasIntValue,
                aliasLongValue,
                aliasParseLong,
                aliasShortValue,
                aliasToString));
        map_.put(aliasNumber, new StringList(
                aliasByteValue,
                aliasCompare,
                aliasCompareTo,
                aliasDoubleValue,
                aliasEquals,
                aliasFloatValue,
                aliasIntValue,
                aliasLongValue,
                aliasShortValue,
                aliasToString));
        map_.put(aliasShort, new StringList(
                aliasByteValue,
                aliasCompare,
                aliasCompareTo,
                aliasDoubleValue,
                aliasEquals,
                aliasFloatValue,
                aliasIntValue,
                aliasLongValue,
                aliasParseShort,
                aliasShortValue,
                aliasToString));
        map_.put(aliasString, new StringList(
                aliasCharAt,
                aliasCompareTo,
                aliasCompareToIgnoreCase,
                aliasContains,
                aliasEndsWith,
                aliasEqualsIgnoreCase,
                aliasFormat,
                aliasGetBytes,
                aliasIndexOf,
                aliasIsEmpty,
                aliasLastIndexOf,
                aliasLength,
                aliasRegionMatches,
                aliasReplace,
                aliasReplaceMultiple,
                aliasSplit,
                aliasSplitChars,
                aliasSplitStrings,
                aliasStartsWith,
                aliasSubSequence,
                aliasSubstring,
                aliasToCharArray,
                aliasToLowerCase,
                aliasToUpperCase,
                aliasTrim,
                aliasValueOf));
        map_.put(aliasStringBuilder, new StringList(
                aliasAppend,
                aliasCapacity,
                aliasCharAt,
                aliasClear,
                aliasDelete,
                aliasDeleteCharAt,
                aliasEnsureCapacity,
                aliasIndexOf,
                aliasInsert,
                aliasIsEmpty,
                aliasLastIndexOf,
                aliasLength,
                aliasReplace,
                aliasReverse,
                aliasSetCharAt,
                aliasSetLength,
                aliasSubSequence,
                aliasToString,
                aliasTrimToSize));
        for (StringList v: map_.values()) {
            int s_ = v.size();
            v.removeDuplicates();
            if (s_ != v.size()) {
                return;
            }
        }
    }

    public void setupOverrides(ContextEl _cont) {
        _cont.setAnalyzing(new AnalyzedPageEl());
        TypeUtil.buildInherits(_cont);
        for (StandardType t: standards.values()) {
            TypeUtil.buildOverrides(t, _cont);
        }
        _cont.setAnalyzing(null);
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
    public static boolean canBeUseAsArgument(boolean _exec, String _param, String _arg, Analyzable _context) {
        LgNames stds_ = _context.getStandards();
        String aliasVoid_ = stds_.getAliasVoid();
        if (StringList.quickEq(_param, aliasVoid_)) {
            return false;
        }
        ClassArgumentMatching param_ = new ClassArgumentMatching(_param);
        if (_arg == null || _arg.isEmpty()) {
            if (param_.isPrimitive(_context)) {
                return false;
            }
            return true;
        }
        if (StringList.quickEq(_arg, aliasVoid_)) {
            return false;
        }
        AssignableFrom a_ = isAssignableFromCust(_param, _arg, stds_);
        if (a_ == AssignableFrom.YES) {
            return true;
        }
        if (a_ == AssignableFrom.NO) {
            return false;
        }

        ClassArgumentMatching arg_ = new ClassArgumentMatching(_arg);
        DimComp paramComp_ = PrimitiveTypeUtil.getQuickComponentBaseType(_param);
        DimComp argComp_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arg);
        String objAlias_ = stds_.getAliasObject();
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
        String paramName_ = paramComp_.getComponent();
        String argName_ = argComp_.getComponent();
        param_ = new ClassArgumentMatching(paramComp_.getComponent());
        arg_ = new ClassArgumentMatching(argComp_.getComponent());
        if (StringList.quickEq(paramComp_.getComponent(),argComp_.getComponent())) {
            return true;
        }
        String aliasPrimBool_ = stds_.getAliasPrimBoolean();
        if (!PrimitiveTypeUtil.isPrimitiveOrWrapper(arg_, _context)) {
            return false;
        }
        if (arg_.isPrimitive(_context)) {
            String pName_ = paramComp_.getComponent();
            String name_ = argComp_.getComponent();
            PrimitiveType pr_ = stds_.getPrimitiveTypes().getVal(name_);
            if (pr_.getAllSuperType(_context).containsStr(pName_)) {
                return true;
            }
            return false;
        }
        if (_exec || !param_.isPrimitive(_context)) {
            return false;
        }
        if (!array_) {
            if (StringList.quickEq(argName_, aliasPrimBool_)) {
                String typeNameParam_ = PrimitiveTypeUtil.toPrimitive(paramName_, true, stds_);
                if (!StringList.quickEq(typeNameParam_, aliasPrimBool_)) {
                    return false;
                }
                return true;
            }
            String pName_ = paramComp_.getComponent();
            String name_ = argComp_.getComponent();
            name_ = PrimitiveTypeUtil.toPrimitive(name_, true, stds_);
            PrimitiveType pr_ = stds_.getPrimitiveTypes().getVal(name_);
            if (pr_.getAllSuperType(_context).containsStr(pName_)) {
                return true;
            }
            return false;
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
    public static ResultErrorStd invokeMethod(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Object instance_ = _struct.getInstance();
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        Object[] argsObj_ = adaptedArgs(list_, _cont.getStandards(), args_);
        String stringBuilderType_ = lgNames_.getAliasStringBuilder();
        String replType_ = lgNames_.getAliasReplacement();
        result_ = invokeStdMethod(_cont, _method, _struct, _args);
        if (result_.getResult() != null) {
            return result_;
        }
        if (result_.getError() != null) {
            _cont.setException(new StdStruct(new CustomError(_cont.joinPages()),result_.getError()));
            return result_;
        }
        if (StringList.quickEq(type_, replType_)) {
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
                    if (two_ == null) {
                        result_.setResult(new StringBuilderStruct(one_.append(lgNames_.getNullString())));
                    } else if (two_ instanceof Boolean) {
                        if ((Boolean) two_) {
                            result_.setResult(new StringBuilderStruct(one_.append(lgNames_.getTrueString())));
                        } else {
                            result_.setResult(new StringBuilderStruct(one_.append(lgNames_.getFalseString())));
                        }
                    } else if (two_ instanceof Character) {
                        result_.setResult(new StringBuilderStruct(one_.append(((Character)two_).charValue())));
                    } else if (two_ instanceof Number) {
                        result_.setResult(new StringBuilderStruct(one_.append(Numbers.toString((Number) two_))));
                    } else {
                        result_.setResult(new StringBuilderStruct(one_.append((CharSequence)two_)));
                    }
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
                String two_ = (String) argsObj_[0];
                if (list_.size() > 1) {
                    Integer three_ = (Integer) argsObj_[1];
                    result_.setResult(new IntStruct(one_.indexOf(two_, three_)));
                } else {
                    result_.setResult(new IntStruct(one_.indexOf(two_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasLastIndexOf())) {
                String two_ = (String) argsObj_[0];
                if (list_.size() > 1) {
                    Integer three_ = (Integer) argsObj_[1];
                    result_.setResult(new IntStruct(one_.lastIndexOf(two_, three_)));
                } else {
                    result_.setResult(new IntStruct(one_.lastIndexOf(two_)));
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
        } else if (StringList.quickEq(type_, lgNames_.getAliasEnums())) {
            if (StringList.quickEq(name_, lgNames_.getAliasName())) {
                Struct str_ = args_[0];
                if (!(str_ instanceof EnumerableStruct)) {
                    result_.setError(lgNames_.getAliasNullPe());
                } else {
                    EnumerableStruct en_ = (EnumerableStruct) str_;
                    result_.setResult(new StringStruct(en_.getName()));
                }
            } else {
                Struct str_ = args_[0];
                if (!(str_ instanceof EnumerableStruct)) {
                    result_.setError(lgNames_.getAliasNullPe());
                } else {
                    EnumerableStruct en_ = (EnumerableStruct) str_;
                    result_.setResult(new IntStruct(en_.getOrdinal()));
                }
            }
        }
        if (result_.getResult() != null) {
            return result_;
        }
        if (result_.getError() != null) {
            _cont.setException(new StdStruct(new CustomError(_cont.joinPages()),result_.getError()));
            return result_;
        }
        result_ = AliasReflection.invokeMethod(_cont, _method, _struct, _args);
        if (result_.getResult() != null) {
            return result_;
        }
        if (result_.getError() != null) {
            _cont.setException(new StdStruct(new CustomError(_cont.joinPages()),result_.getError()));
            return result_;
        }
        if(lgNames_ instanceof LgAdv) {
            result_ = ((LgAdv)lgNames_).getOtherResult(_cont, _struct, _method, args_);
        } else {
            result_ = lgNames_.getOtherResult(_cont, _struct, _method, argsObj_);
        }
        if (result_.getError() != null) {
            _cont.setException(new StdStruct(new CustomError(_cont.joinPages()),result_.getError()));
            return result_;
        }
        return result_;
    }

    public static ResultErrorStd invokeStdMethod(Analyzable _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Object instance_ = _struct.getInstance();
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        Object[] argsObj_ = adaptedArgs(list_, _cont.getStandards(), args_);
        String mathType_ = lgNames_.getAliasMath();
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String nbType_ = lgNames_.getAliasNumber();
        String stringType_ = lgNames_.getAliasString();
        String booleanPrimType_ = lgNames_.getAliasPrimBoolean();
        if (instance_ instanceof Displayable) {
            if (StringList.quickEq(name_, lgNames_.getAliasDisplay()) || StringList.quickEq(name_, lgNames_.getAliasToString())) {
                result_.setResult(new StringStruct(((Displayable)instance_).display()));
                return result_;
            }
        }
        if (StringList.quickEq(type_, lgNames_.getAliasObjectsUtil())) {
            if (StringList.quickEq(name_, lgNames_.getAliasSameRef())) {
                result_.setResult(new BooleanStruct(args_[0].sameReference(args_[1])));
                return result_;
            }
            if (StringList.quickEq(name_, lgNames_.getAliasGetParent())) {
                result_.setResult(args_[0].getParent());
                return result_;
            }
        }
        if (StringList.quickEq(type_, mathType_)) {
            return AliasMath.invokeStdMethod(_cont, _method, _struct, _args);
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
                    result_.setResult(new BooleanStruct((Boolean)argsObj_[0]));
                } else {
                    result_.setResult(new BooleanStruct(Boolean.valueOf((String)argsObj_[0])));
                }
            }
        } else if (StringList.quickEq(type_, charType_)) {
            CharStruct ch_ = (CharStruct) _struct;
            if (StringList.quickEq(name_, lgNames_.getAliasCharValue())) {
                result_.setResult(new CharStruct(ch_.getChar()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                Character one_ = (Character) argsObj_[0];
                Character two_ = (Character) argsObj_[1];
                result_.setResult(new IntStruct(one_.compareTo(two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                Character one_ = ch_.getChar();
                Character two_ = (Character) argsObj_[0];
                result_.setResult(new IntStruct(one_.compareTo(two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCharAt())) {
                Character one_ = ch_.getChar();
                Integer two_ = (Integer) argsObj_[0];
                if (two_.intValue() == 0) {
                    result_.setResult(new CharStruct(one_));
                } else {
                    result_.setError(lgNames_.getAliasBadIndex());
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasLength())) {
                result_.setResult(new IntStruct(1));
            } else if (StringList.quickEq(name_, lgNames_.getAliasSubSequence())) {
                Character one_ = ch_.getChar();
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
                Character one_ = ch_.getChar();
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
                result_.setResult(new BooleanStruct(ContextEl.isDigit(one_)));
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
                    Character one_ = ch_.getChar();
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
                result_.setResult(new StringStruct(Numbers.toString(one_)));
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
                result_.setResult(new StringStruct(Numbers.toString(one_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                Number one_ = (Number) argsObj_[0];
                if (one_ == null) {
                    result_.setResult(new StringStruct(""));
                } else {
                    result_.setResult(new StringStruct(Numbers.toString(one_)));
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
                byte[] bytes_ = StringList.encode(one_);
                Struct[] wrap_ = new Struct[bytes_.length];
                int i_ = CustList.FIRST_INDEX;
                for (byte b: bytes_) {
                    wrap_[i_] = new ByteStruct(b);
                    i_++;
                }
                String ret_ = PrimitiveTypeUtil.getPrettyArrayType(lgNames_.getAliasPrimByte());
                result_.setResult(new ArrayStruct(wrap_, ret_));
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
                    if (obj_ == null) {
                        result_.setResult(new StringStruct(lgNames_.getNullString()));
                    } else if (obj_ instanceof Boolean) {
                        if ((Boolean)obj_) {
                            result_.setResult(new StringStruct(lgNames_.getTrueString()));
                        } else {
                            result_.setResult(new StringStruct(lgNames_.getFalseString()));
                        }
                    } else if (obj_ instanceof Character) {
                        result_.setResult(new StringStruct(Character.toString((Character) obj_)));
                    } else {
                        result_.setResult(new StringStruct(Numbers.toString((Number) obj_)));
                    }
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasIsEmpty())) {
                String one_ = (String) instance_;
                result_.setResult(new BooleanStruct(one_.length() == 0));
            }
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
        long expNbLong_ = expNb_.longValue();
        if (_nb.getBase() == 16) {
            StringBuilder merged_ = new StringBuilder(int_.length()+dec_.length());
            merged_.append(int_);
            merged_.append(dec_);
            double parsed_ = parseDoubleSixteen(merged_.toString());
            long delta_ = expNbLong_;
            delta_ -= 4 * dec_.length();
            double p_ = 1.0;
            long absExpNbLong_ = Math.abs(delta_);
            for (int i = 0; i < absExpNbLong_; i++) {
                p_ *= 2;
            }
            if (delta_ > 0) {
                return parsed_ * p_;
            }
            return parsed_ / p_;
        }
        if (_nb.getBase() == 2) {
            StringBuilder merged_ = new StringBuilder(int_.length()+dec_.length());
            merged_.append(int_);
            merged_.append(dec_);
            double parsed_ = parseDoubleBinary(merged_.toString());
            long delta_ = expNbLong_;
            delta_ -= dec_.length();
            double p_ = 1.0;
            long absExpNbLong_ = Math.abs(delta_);
            for (int i = 0; i < absExpNbLong_; i++) {
                p_ *= 2;
            }
            if (delta_ > 0) {
                return parsed_ * p_;
            }
            return parsed_ / p_;
        }
        if (_nb.getBase() == 8) {
            StringBuilder merged_ = new StringBuilder(int_.length()+dec_.length());
            merged_.append(int_);
            merged_.append(dec_);
            double parsed_ = parseDoubleOctal(merged_.toString());
            long delta_ = expNbLong_;
            delta_ -= 3*dec_.length();
            double p_ = 1.0;
            long absExpNbLong_ = Math.abs(delta_);
            for (int i = 0; i < absExpNbLong_; i++) {
                p_ *= 2;
            }
            if (delta_ > 0) {
                return parsed_ * p_;
            }
            return parsed_ / p_;
        }
        if (dec_.length() == 0) {
            if (expNbLong_ == 0) {
                double long_;
                if (int_.length() > MAX_DIGITS_DOUBLE) {
                    return processBigNumbers(int_, positive_, MAX_DIGITS_DOUBLE);
                }
                long_ = parseQuickLongTen(int_.toString());
                if (!positive_) {
                    return -long_;
                }
                return long_;
            }
            double long_;
            if (int_.length() > MAX_DIGITS_DOUBLE) {
                long_ = parseQuickLongTen(int_.substring(0, MAX_DIGITS_DOUBLE + 1));
                expNbLong_ += int_.length() - MAX_DIGITS_DOUBLE - 1;
            } else {
                long_ = parseQuickLongTen(int_.toString());
            }
            double power_ = 1;
            long absExp_ = Math.abs(expNbLong_);
            for (long i = 0; i < absExp_; i++) {
                power_ *= 10d;
            }
            if (!positive_) {
                if (expNbLong_ > 0) {
                    return -long_ * power_;
                }
                return -long_ / power_;
            }
            if (expNbLong_ > 0) {
                return long_ * power_;
            }
            return long_ / power_;
        }
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
                    double long_ = parseQuickLongTen(number_.toString());
                    if (!positive_) {
                        return -long_;
                    }
                    return long_;
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
                value_ = parseQuickLongTen(decCopy_.substring(0, MAX_DIGITS_DOUBLE + 1));
                diff_ = (int) (-expNbLong_ - int_.length() + MAX_DIGITS_DOUBLE + 1 + nbLeadingZeros_);
            } else {
                value_ = parseQuickLongTen(decCopy_.toString());
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
        double longValue_ = parseQuickLongTen(numberInt_.toString());
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
                return -longValue_;
            }
            return longValue_;
        }
        double decValue_ = parseQuickLongTen(decCopy_.toString());
        double power_ = 1;
        int logDec_ = numberDec_.length();
        for (int i = 0; i < logDec_; i++) {
            power_ *= 10d;
        }
        if (!positive_) {
            return -longValue_ - decValue_ / power_;
        }
        return longValue_ + decValue_ / power_;
    }
    private static Double processBigNumbers(StringBuilder _nb, boolean _positive, int _max) {
        Long long_ = parseQuickLongTen(_nb.substring(0, _max + 1));
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
        while (i_ < max_) {
            int ch_ = _string.charAt(i_);
            if (ch_ >= 'A' && ch_ <= 'F') {
                ch_ = ch_ - 'A' + 'a';
            }
            i_++;
            int digit_ = Math.min(ch_ - '0', 10) + Math.max(ch_ - 'a', 0);
            result_ *= HEX_BASE;
            result_ += digit_;
        }
        return (char)result_;
    }
    public static boolean[] toBits(long _l) {
        boolean[] bits_ = new boolean[64];
        long t_ = _l;
        if (_l < 0) {
            bits_[0] = true;
            t_ = Long.MAX_VALUE + _l + 1;
        }
        int k_ = 63;
        for (int i = 0; i < 63; i++) {
            if (t_ % 2 == 1) {
                bits_[k_] = true;
            }
            k_--;
            t_ /= 2;
        }
        return bits_;
    }
    public static boolean[] toBits(int _l) {
        boolean[] bits_ = new boolean[32];
        long t_ = _l;
        if (_l < 0) {
            bits_[0] = true;
            t_ = Integer.MAX_VALUE + _l + 1;
        }
        int k_ = 31;
        for (int i = 0; i < 31; i++) {
            if (t_ % 2 == 1) {
                bits_[k_] = true;
            }
            k_--;
            t_ /= 2;
        }
        return bits_;
    }
    public static long toLong(boolean[] _bits) {
        long s_ = 0;
        for (int i = 1; i < 64; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        if (_bits[0]) {
            return s_ - Long.MAX_VALUE - 1;
        }
        return s_;
    }
    public static int toInt(boolean[] _bits) {
        int s_ = 0;
        for (int i = 1; i < 32; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        if (_bits[0]) {
            return s_ - Integer.MAX_VALUE - 1;
        }
        return s_;
    }
    public static int extractInt(boolean[] _bits) {
        int s_ = 0;
        for (int i = 33; i < 64; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        if (_bits[32]) {
            return s_ - Integer.MAX_VALUE - 1;
        }
        return s_;
    }
    public static short extractShort(boolean[] _bits) {
        int s_ = 0;
        for (int i = 49; i < 64; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        if (_bits[48]) {
            return (short) (s_ - Short.MAX_VALUE - 1);
        }
        return (short) s_;
    }
    public static byte extractByte(boolean[] _bits) {
        int s_ = 0;
        for (int i = 57; i < 64; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        if (_bits[56]) {
            return (byte) (s_ - Byte.MAX_VALUE - 1);
        }
        return (byte) s_;
    }
    public static int toUnsignedInt(boolean[] _bits, int _max) {
        int s_ = 0;
        for (int i = 32 - _max+1; i < 32; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        return s_;
    }
    public static long toUnsignedLong(boolean[] _bits, int _max) {
        int s_ = 0;
        for (int i = 64 - _max +1; i < 64; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        return s_;
    }
    public static long toLong(boolean[] _bits, long _max, boolean _strNeg) {
        long s_ = 0;
        for (int i = 1; i < 64; i++) {
            s_ *= 2;
            if (_bits[i]) {
                s_++;
            }
        }
        if (_strNeg) {
            return s_ - _max - 1;
        }
        return s_;
    }

    public static boolean[] parseLongSixteenToBits(String _string) {
        StringBuilder str_;
        if (_string.length() < 16) {
            str_ = new StringBuilder();
            int add_ = 16 - _string.length();
            for (int i = 0; i < add_; i++) {
                str_.append("0");
            }
            for (int i = add_; i < 16; i++) {
                str_.append(_string.charAt(i - add_));
            }
        } else {
            str_ = new StringBuilder(_string);
        }
        boolean[] out_ = new boolean[str_.length() * 4];
        int i_ = 0;
        int j_ = 0;
        int max_ = str_.length();
        while (i_ < max_) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            int ch_ = str_.charAt(i_);
            if (ch_ >= 'A' && ch_ <= 'F') {
                ch_ = ch_ - 'A' + 'a';
            }
            i_++;
            int digit_ = Math.min(ch_ - '0', 10) + Math.max(ch_ - 'a', 0);
            int t_ = digit_;
            int k_ = 3;
            for (int j = 0; j < 4; j++) {
                if (t_ % 2 == 1) {
                    out_[j_ + k_] = true;
                }
                k_--;
                t_ /= 2;
            }
            j_ += 4;
        }
        return out_;
    }
    public static boolean[] parseLongOctalToBits(String _string) {
        StringBuilder str_;
        if (_string.length() < 21) {
            str_ = new StringBuilder();
            int add_ = 21 - _string.length();
            for (int i = 0; i < add_; i++) {
                str_.append("0");
            }
            for (int i = add_; i < 21; i++) {
                str_.append(_string.charAt(i - add_));
            }
        } else {
            str_ = new StringBuilder(_string);
        }
        int j_ = 0;
        boolean[] out_ = new boolean[str_.length()*3];
        int i_ = 0;
        int max_ = str_.length();
        while (i_ < max_) {
            int ch_ = str_.charAt(i_);
            i_++;
            int digit_ = ch_ - '0';
            int t_ = digit_;
            int k_ = 2;
            for (int j = 0; j < 3; j++) {
                if (t_ % 2 == 1) {
                    out_[j_ + k_] = true;
                }
                k_--;
                t_ /= 2;
            }
            j_ += 3;
        }
        return out_;
    }
    public static boolean[] parseLongBinaryToBits(String _string) {
        StringBuilder str_;
        if (_string.length() < 64) {
            str_ = new StringBuilder();
            int add_ = 64 - _string.length();
            for (int i = 0; i < add_; i++) {
                str_.append("0");
            }
            for (int i = add_; i < 64; i++) {
                str_.append(_string.charAt(i - add_));
            }
        } else {
            str_ = new StringBuilder(_string);
        }
        boolean[] out_ = new boolean[str_.length()];
        int i_ = 0;
        int max_ = str_.length();
        while (i_ < max_) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            int ch_ = str_.charAt(i_);
            if (ch_ == '1') {
                out_[i_] = true;
            }
            i_++;
        }
        return out_;
    }
    public static double parseDoubleSixteen(String _string) {
        double result_ = 0;
        int i_ = 0;
        int max_ = _string.length();
        while (i_ < max_) {
            int ch_ = _string.charAt(i_);
            if (ch_ >= 'A' && ch_ <= 'F') {
                ch_ = ch_ - 'A' + 'a';
            }
            i_++;
            int digit_ = Math.min(ch_ - '0', 10) + Math.max(ch_ - 'a', 0);
            result_ *= HEX_BASE;
            result_ += digit_;
        }
        return result_;
    }
    public static double parseDoubleOctal(String _string) {
        double result_ = 0;
        int i_ = 0;
        int max_ = _string.length();
        while (i_ < max_) {
            int ch_ = _string.charAt(i_);
            i_++;
            int digit_ = ch_ - '0';
            result_ *= 8;
            result_ += digit_;
        }
        return result_;
    }
    public static double parseDoubleBinary(String _string) {
        double result_ = 0;
        int i_ = 0;
        int max_ = _string.length();
        while (i_ < max_) {
            int ch_ = _string.charAt(i_);
            i_++;
            int digit_ = ch_ - '0';
            result_ *= 2;
            result_ += digit_;
        }
        return result_;
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
    public static long parseQuickLongTen(String _string) {
        long result_ = 0;
        boolean negative_ = false;
        int i_ = 0;
        int max_ = _string.length();
        int digit_;

        if (_string.charAt(0) == '-') {
            negative_ = true;
            i_++;
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
            result_ *= DEFAULT_RADIX;
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
            }
            return null;
        }
        return -result_;
    }
    public static boolean isValidDouble(String _nb) {
        int to_ = _nb.length() - 1;
        int i_ = 0;
        if (!ContextEl.isDigit(_nb.charAt(i_))) {
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
            if (!ContextEl.isDigit(_nb.charAt(i_))) {
                return false;
            }
        } else {
            return false;
        }
        int nbDots_ = 0;
        boolean exp_ = false;
        while (i_ <= to_) {
            char cur_ = _nb.charAt(i_);
            if (!ContextEl.isDigit(cur_)) {
                if (Character.isLetter(cur_)) {
                    if (cur_ == EXP || cur_ == EXP_UPP) {
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
        if (!ContextEl.isDigit(_nb.charAt(i_))) {
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
            if (!ContextEl.isDigit(cur_)) {
                if (cur_ != DOT_VAR) {
                    if (cur_ != EXP && cur_ != EXP_UPP) {
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
                if (!ContextEl.isDigit(cur_)) {
                    if (cur_ != EXP && cur_ != EXP_UPP) {
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
        char n_ = _nb.charAt(i_);
        if (n_ == EXP || n_ == EXP_UPP) {
            i_++;
            if (i_ >= len_) {
                return null;
            }
            char cur_ = _nb.charAt(i_);
            if (!ContextEl.isDigit(cur_) && cur_ != MINUS_CHAR) {
                return null;
            }
            i_++;
            exponentialPart_.append(cur_);
            while (i_ < len_) {
                cur_ = _nb.charAt(i_);
                if (!ContextEl.isDigit(cur_)) {
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
                }
                digit_ = ch_ - '0';
                result_ = -digit_;
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
            }
            return null;
        }
        return -result_;
    }

    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        return new ResultErrorStd();
    }
    public static ResultErrorStd newInstance(ContextEl _cont, ConstructorId _method, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getName();
        StringList list_ = _method.getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        Object[] argsObj_ = adaptedArgs(list_, _cont.getStandards(), args_);
        String stringType_ = lgNames_.getAliasString();
        String stringBuilderType_ = lgNames_.getAliasStringBuilder();
        String objectType_ = lgNames_.getAliasObject();
        String replType_ = lgNames_.getAliasReplacement();
        String intPrimType_ = lgNames_.getAliasPrimInteger();
        result_ = newInstanceStd(_cont, _method, _args);
        if (result_.getResult() != null) {
            return result_;
        }
        if (result_.getError() != null) {
            _cont.setException(new StdStruct(new CustomError(_cont.joinPages()),result_.getError()));
            return result_;
        }
        if (StringList.quickEq(type_, replType_)) {
            result_.setResult(new ReplacementStruct(new Replacement()));
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
            result_.setResult(new SimpleObjectStruct());
        } else if (lgNames_ instanceof LgAdv) {
            result_ = ((LgAdv)lgNames_).getOtherResult(_cont, _method, args_);
        } else {
            result_ = lgNames_.getOtherResult(_cont, _method, argsObj_);
        }
        if (result_.getError() != null) {
            _cont.setException(new StdStruct(new CustomError(_cont.joinPages()),result_.getError()));
        }
        return result_;
    }
    public static ResultErrorStd newInstanceStd(Analyzable _cont, ConstructorId _method, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getName();
        StringList list_ = _method.getParametersTypes();
        LgNames lgNames_ = _cont.getStandards();
        Object[] argsObj_ = adaptedArgs(list_, _cont.getStandards(), args_);
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String stringType_ = lgNames_.getAliasString();
        String stringBuilderType_ = lgNames_.getAliasStringBuilder();
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
        } else if (StringList.quickEq(type_, stringType_)) {
            if (list_.isEmpty()) {
                result_.setResult(new StringStruct(EMPTY_STRING));
            } else if (list_.size() == 1) {
                if (StringList.quickEq(list_.first(), PrimitiveTypeUtil.getPrettyArrayType(bytePrimType_))) {
                    byte[] one_ = (byte[]) argsObj_[0];
                    if (one_ == null) {
                        result_.setError(lgNames_.getAliasNullPe());
                    } else {
                        String dec_ = StringList.decode(one_);
                        if (dec_ != null) {
                            result_.setResult(new StringStruct(dec_));
                        } else {
                            result_.setError(lgNames_.getAliasBadIndex());
                        }
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
            } else {
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
                            String dec_ = StringList.decode(two_, three_, four_);
                            if (dec_ != null) {
                                result_.setResult(new StringStruct(dec_));
                            } else {
                                result_.setError(lgNames_.getAliasBadIndex());
                            }
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
            }
        }
        return result_;
    }
    public ResultErrorStd getOtherResult(ContextEl _cont, ConstructorId _method, Object... _args) {
        return new ResultErrorStd();
    }
    public static ResultErrorStd getField(ContextEl _cont, ClassField _classField, Struct _instance) {
        LgNames lgNames_ = _cont.getStandards();
        ResultErrorStd result_ = lgNames_.getSimpleResult(_cont, _classField);
        if (result_.getResult() != null) {
            return result_;
        }
        result_ = lgNames_.getOtherResult(_cont, _classField, _instance);
        return result_;
    }
    public ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance) {
        return new ResultErrorStd();
    }
    public ResultErrorStd getSimpleResult(Analyzable _conf, ClassField _classField) {
        ResultErrorStd result_ = new ResultErrorStd();
        String type_ = _classField.getClassName();
        String name_ = _classField.getFieldName();
        LgNames lgNames_ = _conf.getStandards();
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
        }
        return result_;
    }
    public static ResultErrorStd setField(ContextEl _cont, ClassField _classField, Struct _instance, Struct _value) {
        LgNames lgNames_ = _cont.getStandards();
        return lgNames_.setOtherResult(_cont, _classField, _instance, _value);
    }
    public ResultErrorStd setOtherResult(ContextEl _cont, ClassField _classField, Struct _instance, Struct _value) {
        return new ResultErrorStd();
    }
    static Struct[] getObjects(Argument... _args) {
        int len_ = _args.length;
        Struct[] classes_ = new Struct[len_];
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args[i].getStruct();
        }
        return classes_;
    }
    static Object[] adaptedArgs(StringList _params,LgNames _stds,Struct... _args) {
        int len_ = _params.size();
        Object[] args_ = new Object[len_];
        for (int i = 0; i < len_; i++) {
            Struct argStruct_ = _args[i];
            if (argStruct_.isNull()) {
                continue;
            }
            if (argStruct_ instanceof ArrayStruct) {
                ArrayStruct arr_ = (ArrayStruct) argStruct_;
                Struct[] str_ = arr_.getInstance();
                String compo_ = PrimitiveTypeUtil.getQuickComponentType(arr_.getClassName());
                if (StringList.quickEq(compo_, _stds.getAliasPrimByte())) {
                    byte[] adapt_ = new byte[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).getInstance().byteValue();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimShort())) {
                    short[] adapt_ = new short[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).getInstance().shortValue();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimInteger())) {
                    int[] adapt_ = new int[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).getInstance().intValue();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimChar())) {
                    char[] adapt_ = new char[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((CharStruct) s).getChar();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimLong())) {
                    long[] adapt_ = new long[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).getInstance().longValue();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimFloat())) {
                    float[] adapt_ = new float[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).getInstance().floatValue();
                        i_++;
                    }
                    args_[i] = adapt_;
                    continue;
                }
                if (StringList.quickEq(compo_, _stds.getAliasPrimDouble())) {
                    double[] adapt_ = new double[str_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (Struct s: str_) {
                        adapt_[i_] = ((NumberStruct)s).getInstance().doubleValue();
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
            if (argStruct_ instanceof NumberStruct) {
                if (argStruct_ instanceof CharStruct) {
                    if (cl_.matchClass(_stds.getAliasPrimChar())) {
                        args_[i] = ((CharStruct) argStruct_).getChar();
                    } else {
                        args_[i] = ((NumberStruct) argStruct_).getInstance();
                    }
                } else {
                    if (cl_.matchClass(_stds.getAliasPrimChar())) {
                        args_[i] = (char)((NumberStruct) argStruct_).getInstance().intValue();
                    } else {
                        args_[i] = ((NumberStruct) argStruct_).getInstance();
                    }
                }
            } else {
                args_[i] = argStruct_.getInstance();
            }
        }
        return args_;
    }

    public void buildIterable(ContextEl _context) {
        //local names
        _context.getAnalyzing().setCurrentBlock(null);
        _context.getAnalyzing().setEnabledInternVars(true);
        Classes cl_ = _context.getClasses();
        String next_ = getAliasNext();
        String hasNext_ = getAliasHasNext();
        String locName_ = _context.getNextTempVar();
        String exp_;
        LocalVariable locVar_ = new LocalVariable();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIterable(),"<?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setIteratorVarCust(locName_);
        String iterator_ = getAliasSimpleIterator();
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(iterator_,PARS));
        cl_.setExpsIteratorCust(ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIteratorType(),"<?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setHasNextVarCust(locName_);
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(hasNext_,PARS));
        cl_.setExpsHasNextCust(ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true)));
        locName_ = _context.getNextTempVar();
        locVar_ = new LocalVariable();
        locVar_.setClassName(StringList.concat(getAliasIteratorType(),"<?>"));
        _context.getInternVars().put(locName_, locVar_);
        cl_.setNextVarCust(locName_);
        exp_ = StringList.concat(locName_, LOC_VAR, StringList.concat(next_,PARS));
        cl_.setExpsNextCust(ElUtil.getAnalyzedOperations(exp_, _context, Calculation.staticCalculation(true)));
    }
    public IterableAnalysisResult getCustomType(StringList _names, ContextEl _context) {
        StringList out_ = new StringList();
        for (String f: _names) {
            String iterable_ = getAliasIterable();
            String type_ = Templates.getFullTypeByBases(f, iterable_, _context);
            if (type_ != null) {
                out_.add(type_);
            }
        }
        out_.removeDuplicates();
        return new IterableAnalysisResult(out_);
    }
    public AbstractForEachLoop newForeachLoop(ContextEl _importingPage,
            BracedBlock _m,
            OffsetStringInfo _className, OffsetStringInfo _variable,
            OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        return new ForEachLoop(_importingPage, _m, _className, _variable, _expression, _classIndex, _label, _offset);
    }

    public StringMap<String> buildFiles(ContextEl _context) {
        StringMap<String> files_ = new StringMap<String>();
        LgNames stds_ = _context.getStandards();
        String content_ = PredefinedClasses.getBracedIterableType(_context);
        String name_;
        name_ = stds_.getAliasIterable();
        predefinedClasses.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedIteratorType(_context);
        name_ = stds_.getAliasIteratorType();
        predefinedClasses.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedEnumType(_context);
        name_ = stds_.getAliasEnum();
        predefinedClasses.add(name_);
        files_.put(name_, content_);
        content_ = PredefinedClasses.getBracedEnumParamType(_context);
        name_ = stds_.getAliasEnumParam();
        predefinedClasses.add(name_);
        files_.put(name_, content_);
        predefinedInterfacesInitOrder.add(stds_.getAliasIterable());
        predefinedInterfacesInitOrder.add(stds_.getAliasIteratorType());
        predefinedInterfacesInitOrder.add(stds_.getAliasEnumParam());
        predefinedInterfacesInitOrder.add(stds_.getAliasEnum());
        return files_;
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
            String w_ = _struct.getClassName(_context);
            w_ = _context.getStandards().toWrapper(w_);
            return w_;
        }
        return getStructClassName(_struct.getInstance(), _context);
    }
    public String getStructClassName(Object _struct, ContextEl _context) {
        String cl_ = getSimpleStructClassName(_struct);
        if (!StringList.quickEq(cl_, getAliasObject())) {
            return cl_;
        }
        return getOtherStructClassName(_struct, _context);
    }
    public String getOtherStructClassName(Object _struct, ContextEl _context) {
        return getAliasObject();
    }
    public final String getSimpleStructClassName(Object _struct) {
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
        return getAliasObject();
    }
    public StringMap<StandardType> getStandards() {
        return standards;
    }
    public StringMap<PrimitiveType> getPrimitiveTypes() {
        return primitiveTypes;
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
    public String getAliasEnums() {
        return aliasEnums;
    }
    public void setAliasEnums(String _aliasEnums) {
        aliasEnums = _aliasEnums;
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
        return mathRef.getAliasMath();
    }
    public void setAliasMath(String _aliasMath) {
        mathRef.setAliasMath(_aliasMath);
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
        return mathRef.getAliasAbs();
    }
    public void setAliasAbs(String _aliasAbs) {
        mathRef.setAliasAbs(_aliasAbs);
    }
    public String getAliasQuot() {
        return mathRef.getAliasQuot();
    }
    public void setAliasQuot(String _aliasQuot) {
        mathRef.setAliasQuot(_aliasQuot);
    }
    public String getAliasMod() {
        return mathRef.getAliasMod();
    }
    public void setAliasMod(String _aliasMod) {
        mathRef.setAliasMod(_aliasMod);
    }
    public String getAliasErrorInitClass() {
        return aliasErrorInitClass;
    }
    public void setAliasErrorInitClass(String _aliasErrorInitClass) {
        aliasErrorInitClass = _aliasErrorInitClass;
    }
    public String getAliasClone() {
        return aliasClone;
    }
    public void setAliasClone(String _aliasClone) {
        aliasClone = _aliasClone;
    }
    public String getAliasValues() {
        return aliasValues;
    }
    public void setAliasValues(String _aliasValues) {
        aliasValues = _aliasValues;
    }
    public String getAliasInvokeTarget() {
        return reflect.getAliasInvokeTarget();
    }
    public void setAliasInvokeTarget(String _aliasInvokeTarget) {
        reflect.setAliasInvokeTarget(_aliasInvokeTarget);
    }
    public AliasReflection getReflect() {
        return reflect;
    }
    public String getAliasClassNotFoundError() {
        return reflect.getAliasClassNotFoundError();
    }
    public void setAliasClassNotFoundError(String _aliasClassNotFoundError) {
        reflect.setAliasClassNotFoundError(_aliasClassNotFoundError);
    }

    public String getAliasGetVariableOwner() {
        return reflect.getAliasGetVariableOwner();
    }
    public void setAliasGetVariableOwner(String _aliasTypeVariable) {
        reflect.setAliasGetVariableOwner(_aliasTypeVariable);
    }

    public String getAliasGetGenericVariableOwner() {
        return reflect.getAliasGetGenericVariableOwner();
    }
    public void setAliasGetGenericVariableOwner(String _aliasTypeVariable) {
        reflect.setAliasGetGenericVariableOwner(_aliasTypeVariable);
    }

    public String getAliasGetString() {
        return reflect.getAliasGetString();
    }
    public void setAliasGetString(String _aliasTypeVariable) {
        reflect.setAliasGetString(_aliasTypeVariable);
    }

    public String getAliasClass() {
        return reflect.getAliasClass();
    }
    public void setAliasClass(String _aliasClass) {
        reflect.setAliasClass(_aliasClass);
    }
    public String getAliasFct() {
        return reflect.getAliasFct();
    }
    public void setAliasFct(String _aliasFct) {
        reflect.setAliasFct(_aliasFct);
    }
    public String getAliasCall() {
        return reflect.getAliasCall();
    }
    public void setAliasCall(String _aliasCall) {
        reflect.setAliasCall(_aliasCall);
    }
    public String getAliasAnnotation() {
        return reflect.getAliasAnnotation();
    }
    public void setAliasAnnotation(String _aliasAnnotation) {
        reflect.setAliasAnnotation(_aliasAnnotation);
    }
    public String getAliasAnnotated() {
        return reflect.getAliasAnnotated();
    }
    public void setAliasAnnotated(String _aliasAnnotated) {
        reflect.setAliasAnnotated(_aliasAnnotated);
    }
    public String getAliasGetAnnotations() {
        return reflect.getAliasGetAnnotations();
    }
    public void setAliasGetAnnotations(String _aliasGetAnnotations) {
        reflect.setAliasGetAnnotations(_aliasGetAnnotations);
    }
    public String getAliasGetDefaultValue() {
        return reflect.getAliasGetDefaultValue();
    }
    public void setAliasGetDefaultValue(String _aliasGetDefaultValue) {
        reflect.setAliasGetDefaultValue(_aliasGetDefaultValue);
    }
    public String getAliasGetAnnotationsParameters() {
        return reflect.getAliasGetAnnotationsParameters();
    }
    public void setAliasGetAnnotationsParameters(String _aliasGetAnnotationsParameters) {
        reflect.setAliasGetAnnotationsParameters(_aliasGetAnnotationsParameters);
    }
    public String getAliasGetDeclaredMethods() {
        return reflect.getAliasGetDeclaredMethods();
    }
    public void setAliasGetDeclaredMethods(String _aliasGetDeclaredMethods) {
        reflect.setAliasGetDeclaredMethods(_aliasGetDeclaredMethods);
    }
    public String getAliasGetDeclaredConstructors() {
        return reflect.getAliasGetDeclaredConstructors();
    }
    public void setAliasGetDeclaredConstructors(String _aliasGetDeclaredConstructors) {
        reflect.setAliasGetDeclaredConstructors(_aliasGetDeclaredConstructors);
    }
    public String getAliasGetDeclaredFields() {
        return reflect.getAliasGetDeclaredFields();
    }
    public void setAliasGetDeclaredFields(String _aliasGetDeclaredFields) {
        reflect.setAliasGetDeclaredFields(_aliasGetDeclaredFields);
    }
    public String getAliasMakeGeneric() {
        return reflect.getAliasMakeGeneric();
    }
    public void setAliasMakeGeneric(String _aliasMakeGeneric) {
        reflect.setAliasMakeGeneric(_aliasMakeGeneric);
    }
    public String getAliasGetAllClasses() {
        return reflect.getAliasGetAllClasses();
    }
    public void setAliasGetAllClasses(String _aliasGetAllClasses) {
        reflect.setAliasGetAllClasses(_aliasGetAllClasses);
    }
    public String getAliasGetOperators() {
        return reflect.getAliasGetOperators();
    }
    public void setAliasGetOperators(String _aliasGetOperators) {
        reflect.setAliasGetOperators(_aliasGetOperators);
    }
    public String getAliasConstructor() {
        return reflect.getAliasConstructor();
    }
    public void setAliasConstructor(String _aliasConstructor) {
        reflect.setAliasConstructor(_aliasConstructor);
    }
    public String getAliasField() {
        return reflect.getAliasField();
    }
    public void setAliasField(String _aliasField) {
        reflect.setAliasField(_aliasField);
    }
    public String getAliasMethod() {
        return reflect.getAliasMethod();
    }
    public void setAliasMethod(String _aliasMethod) {
        reflect.setAliasMethod(_aliasMethod);
    }
    public String getAliasInvoke() {
        return reflect.getAliasInvoke();
    }
    public void setAliasInvoke(String _aliasInvoke) {
        reflect.setAliasInvoke(_aliasInvoke);
    }
    public String getAliasNewInstance() {
        return reflect.getAliasNewInstance();
    }
    public void setAliasNewInstance(String _aliasNewInstance) {
        reflect.setAliasNewInstance(_aliasNewInstance);
    }
    public String getAliasIsPolymorph() {
        return reflect.getAliasIsPolymorph();
    }
    public void setAliasIsPolymorph(String _aliasIsPolymorph) {
        reflect.setAliasIsPolymorph(_aliasIsPolymorph);
    }
    public String getAliasSetPolymorph() {
        return reflect.getAliasSetPolymorph();
    }
    public void setAliasSetPolymorph(String _aliasSetPolymorph) {
        reflect.setAliasSetPolymorph(_aliasSetPolymorph);
    }
    public String getAliasIsAbstract() {
        return reflect.getAliasIsAbstract();
    }
    public void setAliasIsAbstract(String _aliasIsAbstract) {
        reflect.setAliasIsAbstract(_aliasIsAbstract);
    }
    public String getAliasGetName() {
        return reflect.getAliasGetName();
    }
    public void setAliasGetName(String _aliasGetName) {
        reflect.setAliasGetName(_aliasGetName);
    }
    public String getAliasGetPrettyName() {
        return reflect.getAliasGetPrettyName();
    }
    public void setAliasGetPrettyName(String _aliasGetName) {
        reflect.setAliasGetPrettyName(_aliasGetName);
    }
    public String getAliasGetField() {
        return reflect.getAliasGetField();
    }
    public void setAliasGetField(String _aliasGetField) {
        reflect.setAliasGetField(_aliasGetField);
    }
    public String getAliasSetField() {
        return reflect.getAliasSetField();
    }
    public void setAliasSetField(String _aliasSetField) {
        reflect.setAliasSetField(_aliasSetField);
    }
    public String getAliasGetClass() {
        return reflect.getAliasGetClass();
    }
    public void setAliasGetClass(String _aliasGetClass) {
        reflect.setAliasGetClass(_aliasGetClass);
    }
    public String getAliasGetEnclosingType() {
        return reflect.getAliasGetEnclosingType();
    }
    public void setAliasGetEnclosingType(String _aliasGetEnclosingType) {
        reflect.setAliasGetEnclosingType(_aliasGetEnclosingType);
    }
    public String getAliasGetDeclaredClasses() {
        return reflect.getAliasGetDeclaredClasses();
    }
    public void setAliasGetDeclaredClasses(String _aliasGetDeclaredClasses) {
        reflect.setAliasGetDeclaredClasses(_aliasGetDeclaredClasses);
    }
    public String getAliasForName() {
        return reflect.getAliasForName();
    }
    public void setAliasForName(String _aliasForName) {
        reflect.setAliasForName(_aliasForName);
    }
    public String getAliasObjectsUtil() {
        return aliasObjectsUtil;
    }
    public void setAliasObjectsUtil(String _aliasObjectsUtil) {
        aliasObjectsUtil = _aliasObjectsUtil;
    }
    public String getAliasSameRef() {
        return aliasSameRef;
    }
    public void setAliasSameRef(String _aliasSameRef) {
        aliasSameRef = _aliasSameRef;
    }
    public String getAliasGetParent() {
        return aliasGetParent;
    }
    public void setAliasGetParent(String _aliasGetParent) {
        aliasGetParent = _aliasGetParent;
    }
    public void setStandards(StringMap<StandardType> _standards) {
        standards = _standards;
    }

    public String getAliasGetSuperClass() {
        return reflect.getAliasGetSuperClass();
    }
    public void setAliasGetSuperClass(String _aliasGetSuperClass) {
        reflect.setAliasGetSuperClass(_aliasGetSuperClass);
    }
    public String getAliasGetGenericSuperClass() {
        return reflect.getAliasGetGenericSuperClass();
    }
    public void setAliasGetGenericSuperClass(String _aliasGetGenericSuperClass) {
        reflect.setAliasGetGenericSuperClass(_aliasGetGenericSuperClass);
    }
    public String getAliasGetInterfaces() {
        return reflect.getAliasGetInterfaces();
    }
    public void setAliasGetInterfaces(String _aliasGetInterfaces) {
        reflect.setAliasGetInterfaces(_aliasGetInterfaces);
    }
    public String getAliasGetGenericInterfaces() {
        return reflect.getAliasGetGenericInterfaces();
    }
    public void setAliasGetGenericInterfaces(String _aliasGetGenericInterfaces) {
        reflect.setAliasGetGenericInterfaces(_aliasGetGenericInterfaces);
    }

    public String getAliasGetLowerBounds() {
        return reflect.getAliasGetLowerBounds();
    }
    public void setAliasGetLowerBounds(String _aliasGetLowerBounds) {
        reflect.setAliasGetLowerBounds(_aliasGetLowerBounds);
    }
    public String getAliasGetUpperBounds() {
        return reflect.getAliasGetUpperBounds();
    }
    public void setAliasGetUpperBounds(String _aliasGetUpperBounds) {
        reflect.setAliasGetUpperBounds(_aliasGetUpperBounds);
    }
    public String getAliasGetComponentType() {
        return reflect.getAliasGetComponentType();
    }
    public void setAliasGetComponentType(String _aliasGetComponentType) {
        reflect.setAliasGetComponentType(_aliasGetComponentType);
    }

    public String getAliasMakeArray() {
        return reflect.getAliasMakeArray();
    }
    public void setAliasMakeArray(String _aliasMakeArray) {
        reflect.setAliasMakeArray(_aliasMakeArray);
    }
    public String getAliasGetParameterTypes() {
        return reflect.getAliasGetParameterTypes();
    }
    public void setAliasGetParameterTypes(String _aliasGetParameterTypes) {
        reflect.setAliasGetParameterTypes(_aliasGetParameterTypes);
    }
    public String getAliasGetTypeParameters() {
        return reflect.getAliasGetTypeParameters();
    }
    public void setAliasGetTypeParameters(String _aliasGetTypeParameters) {
        reflect.setAliasGetTypeParameters(_aliasGetTypeParameters);
    }
    public String getAliasGetParameterNames() {
        return reflect.getAliasGetParameterNames();
    }
    public void setAliasGetParameterNames(String _aliasGetNameParameters) {
        reflect.setAliasGetParameterNames(_aliasGetNameParameters);
    }
    public String getAliasGetGenericReturnType() {
        return reflect.getAliasGetGenericReturnType();
    }
    public void setAliasGetGenericReturnType(String _aliasGetGenericReturnType) {
        reflect.setAliasGetGenericReturnType(_aliasGetGenericReturnType);
    }
    public String getAliasGetReturnType() {
        return reflect.getAliasGetReturnType();
    }
    public void setAliasGetReturnType(String _aliasGetReturnType) {
        reflect.setAliasGetReturnType(_aliasGetReturnType);
    }

    public String getAliasGetActualTypeArguments() {
        return reflect.getAliasGetActualTypeArguments();
    }
    public void setAliasGetActualTypeArguments(
            String _aliasGetActualTypeArguments) {
        reflect.setAliasGetActualTypeArguments(_aliasGetActualTypeArguments);
    }
    public String getAliasGetGenericTypeArguments() {
        return reflect.getAliasGetGenericTypeArguments();
    }
    public void setAliasGetGenericTypeArguments(
            String _aliasGetGenericTypeArguments) {
        reflect.setAliasGetGenericTypeArguments(_aliasGetGenericTypeArguments);
    }
    public String getAliasGetFieldType() {
        return reflect.getAliasGetType();
    }
    public void setAliasGetFieldType(String _aliasGetGenericType) {
        reflect.setAliasGetType(_aliasGetGenericType);
    }
    public String getAliasGetGenericType() {
        return reflect.getAliasGetGenericType();
    }
    public void setAliasGetGenericType(String _aliasGetGenericType) {
        reflect.setAliasGetGenericType(_aliasGetGenericType);
    }
    public String getAliasIsFinal() {
        return reflect.getAliasIsFinal();
    }
    public void setAliasIsFinal(String _aliasIsFinal) {
        reflect.setAliasIsFinal(_aliasIsFinal);
    }
    public String getAliasIsStatic() {
        return reflect.getAliasIsStatic();
    }
    public void setAliasIsStatic(String _aliasIsStatic) {
        reflect.setAliasIsStatic(_aliasIsStatic);
    }
    public String getAliasIsVarargs() {
        return reflect.getAliasIsVarargs();
    }
    public void setAliasIsVarargs(String _aliasIsVarargs) {
        reflect.setAliasIsVarargs(_aliasIsVarargs);
    }
    public String getAliasIsNormal() {
        return reflect.getAliasIsNormal();
    }
    public void setAliasIsNormal(String _aliasIsNormal) {
        reflect.setAliasIsNormal(_aliasIsNormal);
    }
    public String getAliasIsPublic() {
        return reflect.getAliasIsPublic();
    }
    public void setAliasIsPublic(String _aliasIsPublic) {
        reflect.setAliasIsPublic(_aliasIsPublic);
    }
    public String getAliasIsProtected() {
        return reflect.getAliasIsProtected();
    }
    public void setAliasIsProtected(String _aliasIsProtected) {
        reflect.setAliasIsProtected(_aliasIsProtected);
    }
    public String getAliasIsPackage() {
        return reflect.getAliasIsPackage();
    }
    public void setAliasIsPackage(String _aliasIsPackage) {
        reflect.setAliasIsPackage(_aliasIsPackage);
    }
    public String getAliasIsPrivate() {
        return reflect.getAliasIsPrivate();
    }
    public void setAliasIsPrivate(String _aliasIsPrivate) {
        reflect.setAliasIsPrivate(_aliasIsPrivate);
    }
    public String getAliasIsClass() {
        return reflect.getAliasIsClass();
    }
    public void setAliasIsClass(String _aliasIsClass) {
        reflect.setAliasIsClass(_aliasIsClass);
    }

    public String getAliasIsWildCard() {
        return reflect.getAliasIsWildCard();
    }
    public void setAliasIsWildCard(String _aliasIsWildCard) {
        reflect.setAliasIsWildCard(_aliasIsWildCard);
    }

    public String getAliasIsInterface() {
        return reflect.getAliasIsInterface();
    }
    public void setAliasIsInterface(String _aliasIsInterface) {
        reflect.setAliasIsInterface(_aliasIsInterface);
    }
    public String getAliasIsEnum() {
        return reflect.getAliasIsEnum();
    }
    public void setAliasIsEnum(String _aliasIsEnum) {
        reflect.setAliasIsEnum(_aliasIsEnum);
    }
    public String getAliasIsPrimitive() {
        return reflect.getAliasIsPrimitive();
    }
    public void setAliasIsPrimitive(String _aliasIsPrimitive) {
        reflect.setAliasIsPrimitive(_aliasIsPrimitive);
    }
    public String getAliasIsArray() {
        return reflect.getAliasIsArray();
    }
    public void setAliasIsArray(String _aliasIsArray) {
        reflect.setAliasIsArray(_aliasIsArray);
    }
    public String getAliasIsAnnotation() {
        return reflect.getAliasIsAnnotation();
    }
    public void setAliasIsAnnotation(String _aliasIsAnnotation) {
        reflect.setAliasIsAnnotation(_aliasIsAnnotation);
    }
    public String getAliasMakeWildCard() {
        return reflect.getAliasMakeWildCard();
    }
    public void setAliasMakeWildCard(String _aliasMakeWildCard) {
        reflect.setAliasMakeWildCard(_aliasMakeWildCard);
    }
    public String getAliasIsInstance() {
        return reflect.getAliasIsInstance();
    }
    public void setAliasIsInstance(String _aliasIsInstance) {
        reflect.setAliasIsInstance(_aliasIsInstance);
    }
    public String getAliasIsAssignableFrom() {
        return reflect.getAliasIsAssignableFrom();
    }
    public void setAliasIsAssignableFrom(String _aliasIsAssignableFrom) {
        reflect.setAliasIsAssignableFrom(_aliasIsAssignableFrom);
    }
    public String getAliasInit() {
        return reflect.getAliasInit();
    }
    public void setAliasInit(String _aliasInit) {
        reflect.setAliasInit(_aliasInit);
    }
    public String getAliasDefaultInstance() {
        return reflect.getAliasDefaultInstance();
    }
    public void setAliasDefaultInstance(String _aliasDefaultInstance) {
        reflect.setAliasDefaultInstance(_aliasDefaultInstance);
    }
    public String getAliasEnumValueOf() {
        return reflect.getAliasEnumValueOf();
    }
    public void setAliasEnumValueOf(String _aliasEnumValueOf) {
        reflect.setAliasEnumValueOf(_aliasEnumValueOf);
    }
    public String getAliasGetEnumConstants() {
        return reflect.getAliasGetEnumConstants();
    }
    public void setAliasGetEnumConstants(String _aliasGetEnumConstants) {
        reflect.setAliasGetEnumConstants(_aliasGetEnumConstants);
    }
    public String getAliasGetGenericBounds() {
        return reflect.getAliasGetGenericBounds();
    }
    public void setAliasGetGenericBounds(String _aliasGetGenericBounds) {
        reflect.setAliasGetGenericBounds(_aliasGetGenericBounds);
    }
    public String getAliasGetBounds() {
        return reflect.getAliasGetBounds();
    }
    public void setAliasGetBounds(String _aliasGetBounds) {
        reflect.setAliasGetBounds(_aliasGetBounds);
    }
    public String getAliasArrayNewInstance() {
        return reflect.getAliasArrayNewInstance();
    }
    public void setAliasArrayNewInstance(String _aliasArrayNewInstance) {
        reflect.setAliasArrayNewInstance(_aliasArrayNewInstance);
    }
    public String getAliasArrayGet() {
        return reflect.getAliasArrayGet();
    }
    public void setAliasArrayGet(String _aliasArrayGet) {
        reflect.setAliasArrayGet(_aliasArrayGet);
    }
    public String getAliasArraySet() {
        return reflect.getAliasArraySet();
    }
    public void setAliasArraySet(String _aliasArraySet) {
        reflect.setAliasArraySet(_aliasArraySet);
    }
    public String getAliasArrayGetLength() {
        return reflect.getAliasArrayGetLength();
    }
    public void setAliasArrayGetLength(String _aliasArrayGetLength) {
        reflect.setAliasArrayGetLength(_aliasArrayGetLength);
    }
    public String getAliasGetDeclaringClass() {
        return reflect.getAliasGetDeclaringClass();
    }
    public void setAliasGetDeclaringClass(String _aliasGetDeclaringClass) {
        reflect.setAliasGetDeclaringClass(_aliasGetDeclaringClass);
    }
    public AliasMath getMathRef() {
        return mathRef;
    }

    public void build(LgNames _stds) {
        mathRef.build(_stds);
    }
    public String getAliasBinQuot() {
        return mathRef.getAliasBinQuot();
    }
    public void setAliasBinQuot(String _aliasBinQuot) {
        mathRef.setAliasBinQuot(_aliasBinQuot);
    }
    public String getAliasBinMod() {
        return mathRef.getAliasBinMod();
    }
    public void setAliasBinMod(String _aliasBinMod) {
        mathRef.setAliasBinMod(_aliasBinMod);
    }
    public String getAliasPlus() {
        return mathRef.getAliasPlus();
    }
    public void setAliasPlus(String _aliasPlus) {
        mathRef.setAliasPlus(_aliasPlus);
    }
    public String getAliasMinus() {
        return mathRef.getAliasMinus();
    }
    public void setAliasMinus(String _aliasMinus) {
        mathRef.setAliasMinus(_aliasMinus);
    }
    public String getAliasMult() {
        return mathRef.getAliasMult();
    }
    public void setAliasMult(String _aliasMult) {
        mathRef.setAliasMult(_aliasMult);
    }
    public String getAliasAnd() {
        return mathRef.getAliasAnd();
    }
    public void setAliasAnd(String _aliasAnd) {
        mathRef.setAliasAnd(_aliasAnd);
    }
    public String getAliasOr() {
        return mathRef.getAliasOr();
    }
    public void setAliasOr(String _aliasOr) {
        mathRef.setAliasOr(_aliasOr);
    }
    public String getAliasXor() {
        return mathRef.getAliasXor();
    }
    public void setAliasXor(String _aliasXor) {
        mathRef.setAliasXor(_aliasXor);
    }
    public String getAliasNegBin() {
        return mathRef.getAliasNegBin();
    }
    public void setAliasNegBin(String _aliasNegBin) {
        mathRef.setAliasNegBin(_aliasNegBin);
    }
    public String getAliasLt() {
        return mathRef.getAliasLt();
    }
    public void setAliasLt(String _aliasLt) {
        mathRef.setAliasLt(_aliasLt);
    }
    public String getAliasGt() {
        return mathRef.getAliasGt();
    }
    public void setAliasGt(String _aliasGt) {
        mathRef.setAliasGt(_aliasGt);
    }
    public String getAliasLe() {
        return mathRef.getAliasLe();
    }
    public void setAliasLe(String _aliasLe) {
        mathRef.setAliasLe(_aliasLe);
    }
    public String getAliasGe() {
        return mathRef.getAliasGe();
    }
    public void setAliasGe(String _aliasGe) {
        mathRef.setAliasGe(_aliasGe);
    }
    public String getAliasShiftLeft() {
        return mathRef.getAliasShiftLeft();
    }
    public void setAliasShiftLeft(String _aliasShiftLeft) {
        mathRef.setAliasShiftLeft(_aliasShiftLeft);
    }
    public String getAliasShiftRight() {
        return mathRef.getAliasShiftRight();
    }
    public void setAliasShiftRight(String _aliasShiftRight) {
        mathRef.setAliasShiftRight(_aliasShiftRight);
    }
    public String getAliasBitShiftLeft() {
        return mathRef.getAliasBitShiftLeft();
    }
    public void setAliasBitShiftLeft(String _aliasBitShiftLeft) {
        mathRef.setAliasBitShiftLeft(_aliasBitShiftLeft);
    }
    public String getAliasBitShiftRight() {
        return mathRef.getAliasBitShiftRight();
    }
    public void setAliasBitShiftRight(String _aliasBitShiftRight) {
        mathRef.setAliasBitShiftRight(_aliasBitShiftRight);
    }
    public String getAliasRotateLeft() {
        return mathRef.getAliasRotateLeft();
    }
    public void setAliasRotateLeft(String _aliasRotateLeft) {
        mathRef.setAliasRotateLeft(_aliasRotateLeft);
    }
    public String getAliasRotateRight() {
        return mathRef.getAliasRotateRight();
    }
    public void setAliasRotateRight(String _aliasRotateRight) {
        mathRef.setAliasRotateRight(_aliasRotateRight);
    }
    public StringList getPredefinedClasses() {
        return predefinedClasses;
    }
    public void setPredefinedClasses(StringList _predefinedClasses) {
        predefinedClasses = _predefinedClasses;
    }
    public StringList getPredefinedInterfacesInitOrder() {
        return predefinedInterfacesInitOrder;
    }
    public void setPredefinedInterfacesInitOrder(
            StringList _predefinedInterfacesInitOrder) {
        predefinedInterfacesInitOrder = _predefinedInterfacesInitOrder;
    }
    
    public String getTrueString() {
        return trueString;
    }
    public void setTrueString(String _trueString) {
        trueString = _trueString;
    }
    public String getFalseString() {
        return falseString;
    }
    public void setFalseString(String _falseString) {
        falseString = _falseString;
    }
    public String getNullString() {
        return nullString;
    }
    public void setNullString(String _nullString) {
        nullString = _nullString;
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
