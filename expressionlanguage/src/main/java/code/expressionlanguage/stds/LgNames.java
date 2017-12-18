package code.expressionlanguage.stds;

import java.nio.charset.Charset;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.StaticAccessException;
import code.expressionlanguage.exceptions.UnwrappingException;
import code.expressionlanguage.exceptions.VoidArgumentException;
import code.expressionlanguage.opers.util.ArgumentsGroup;
import code.expressionlanguage.opers.util.AssignableFrom;
import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.ByteStruct;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ClassMethodIdResult;
import code.expressionlanguage.opers.util.ClassMethodIdReturn;
import code.expressionlanguage.opers.util.ClassName;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.ConstructorInfo;
import code.expressionlanguage.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.opers.util.DimComp;
import code.expressionlanguage.opers.util.DoubleStruct;
import code.expressionlanguage.opers.util.Fcts;
import code.expressionlanguage.opers.util.FieldInfo;
import code.expressionlanguage.opers.util.FieldResult;
import code.expressionlanguage.opers.util.FloatStruct;
import code.expressionlanguage.opers.util.Identifiable;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.LongStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodInfo;
import code.expressionlanguage.opers.util.MethodMetaInfo;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.ParametersGroup;
import code.expressionlanguage.opers.util.Parametrable;
import code.expressionlanguage.opers.util.Parametrables;
import code.expressionlanguage.opers.util.SearchingMemberStatus;
import code.expressionlanguage.opers.util.ShortStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.serialize.exceptions.NoSuchDeclaredConstructorException;
import code.serialize.exceptions.NoSuchDeclaredMethodException;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.Numbers;
import code.util.ObjectMap;
import code.util.ObjectNotNullMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.comparators.ComparatorBoolean;

public class LgNames {
    protected static final String EMPTY_STRING = "";
    protected static final String RETURN_LINE = "\n";
    protected static final String SPACE = " ";
    protected static final String RETURN_TAB = RETURN_LINE+"\t";

    protected static final char PAR_LEFT = '(';
    protected static final char SEP_ARG = ',';
    protected static final char PAR_RIGHT = ')';
    protected static final String DOT = ".";
    protected static final String VARARG_SUFFIX = "...";
    private static final char DOT_VAR = '.';
    private static final char EXP = 'e';
    private static final char MINUS_CHAR = '-';

    private static final long   MULTMIN_RADIX_TEN =  Long.MIN_VALUE / 10;
    private static final long N_MULTMAX_RADIX_TEN = -Long.MAX_VALUE / 10;
    private String aliasObject;
    private String aliasVoid;
    private String aliasAppendable;
    private String aliasCharSequence;
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
    private String aliasNull;
    private String aliasNbFormat;
    private String aliasBadEncode;
    private String aliasBadIndex;
    private String aliasPrimBoolean;
    private String aliasPrimByte;
    private String aliasPrimShort;
    private String aliasPrimChar;
    private String aliasPrimInteger;
    private String aliasPrimLong;
    private String aliasPrimFloat;
    private String aliasPrimDouble;
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
    private String aliasCharAt;
    private String aliasToCharArray;
    private String aliasSplit;
    private String aliasSplits;
    private String aliasReplace;
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
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasCharAt, params_, aliasPrimChar, false, MethodModifier.ABSTRACT, aliasCharSequence);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasLength, noTypes_, aliasPrimInteger, false, MethodModifier.ABSTRACT, aliasCharSequence);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasSubSequence, params_, aliasCharSequence, false, MethodModifier.ABSTRACT, aliasCharSequence);
        methods_.put(method_.getId(), method_);
        StandardType std_;
        std_ = new StandardInterface(aliasCharSequence, methods_, noTypes_);
        standards.put(aliasCharSequence, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasAppend, params_, aliasAppendable, false, MethodModifier.ABSTRACT, aliasAppendable);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasAppend, params_, aliasAppendable, false, MethodModifier.ABSTRACT, aliasAppendable);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence, aliasPrimInteger, aliasPrimInteger);
        method_ = new StandardMethod(aliasAppend, params_, aliasAppendable, false, MethodModifier.ABSTRACT, aliasAppendable);
        methods_.put(method_.getId(), method_);
        std_ = new StandardInterface(aliasAppendable, methods_, noTypes_);
        standards.put(aliasAppendable, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        params_ = new StringList();
        method_ = new StandardMethod(aliasBooleanValue, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, aliasBoolean);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean, aliasPrimBoolean);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger, false, MethodModifier.STATIC, aliasBoolean);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasBoolean);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasBoolean);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasBoolean);
        method_ = new StandardMethod(aliasEquals, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, aliasBoolean);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasParseBoolean, params_, aliasPrimBoolean, false, MethodModifier.STATIC, aliasBoolean);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToString, params_, aliasString, false, MethodModifier.NORMAL, aliasBoolean);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean);
        method_ = new StandardMethod(aliasToString, params_, aliasString, false, MethodModifier.STATIC, aliasBoolean);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean);
        method_ = new StandardMethod(aliasValueOf, params_, aliasBoolean, false, MethodModifier.STATIC, aliasBoolean);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasValueOf, params_, aliasBoolean, false, MethodModifier.STATIC, aliasBoolean);
        methods_.put(method_.getId(), method_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        StandardConstructor ctor_;
        params_ = new StringList(aliasString);
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        params_ = new StringList(aliasBoolean);
        ctor_ = new StandardConstructor(params_,false);
        constructors_.add(ctor_);
        std_ = new StandardClass(aliasBoolean, fields_, constructors_, methods_, aliasObject, MethodModifier.FINAL);
        standards.put(aliasBoolean, std_);
        constructors_ = new CustList<StandardConstructor>();
        numbersConstructors(constructors_, aliasPrimByte);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        numbersValuesMethods(methods_, aliasByte, aliasParseByte, aliasPrimByte);
        fields_ = new StringMap<StandardField>();
        numbersValuesFields(fields_, aliasPrimByte);
        std_ = new StandardClass(aliasByte, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL);
        standards.put(aliasByte, std_);
        constructors_ = new CustList<StandardConstructor>();
        numbersConstructors(constructors_, aliasPrimShort);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        numbersValuesMethods(methods_, aliasShort, aliasParseShort, aliasPrimShort);
        fields_ = new StringMap<StandardField>();
        numbersValuesFields(fields_, aliasPrimShort);
        std_ = new StandardClass(aliasShort, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL);
        standards.put(aliasShort, std_);
        constructors_ = new CustList<StandardConstructor>();
        numbersConstructors(constructors_, aliasPrimInteger);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        numbersValuesMethods(methods_, aliasInteger, aliasParseInt, aliasPrimInteger);
        fields_ = new StringMap<StandardField>();
        numbersValuesFields(fields_, aliasPrimInteger);
        std_ = new StandardClass(aliasInteger, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL);
        standards.put(aliasInteger, std_);
        constructors_ = new CustList<StandardConstructor>();
        numbersConstructors(constructors_, aliasPrimLong);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        numbersValuesMethods(methods_, aliasLong, aliasParseLong, aliasPrimLong);
        fields_ = new StringMap<StandardField>();
        numbersValuesFields(fields_, aliasPrimLong);
        std_ = new StandardClass(aliasLong, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL);
        standards.put(aliasLong, std_);
        constructors_ = new CustList<StandardConstructor>();
        numbersConstructors(constructors_, aliasPrimFloat);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        numbersValuesMethods(methods_, aliasFloat, aliasParseFloat, aliasPrimFloat);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, aliasFloat);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat);
        method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean, false, MethodModifier.STATIC, aliasFloat);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsNan, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, aliasFloat);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat);
        method_ = new StandardMethod(aliasIsNan, params_, aliasPrimBoolean, false, MethodModifier.STATIC, aliasFloat);
        methods_.put(method_.getId(), method_);
        fields_ = new StringMap<StandardField>();
        numbersValuesFields(fields_, aliasPrimFloat);
        std_ = new StandardClass(aliasFloat, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL);
        standards.put(aliasFloat, std_);
        constructors_ = new CustList<StandardConstructor>();
        numbersConstructors(constructors_, aliasPrimDouble);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        numbersValuesMethods(methods_, aliasDouble, aliasParseDouble, aliasPrimDouble);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, aliasDouble);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble);
        method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean, false, MethodModifier.STATIC, aliasDouble);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsNan, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, aliasDouble);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble);
        method_ = new StandardMethod(aliasIsNan, params_, aliasPrimBoolean, false, MethodModifier.STATIC, aliasDouble);
        methods_.put(method_.getId(), method_);
        fields_ = new StringMap<StandardField>();
        numbersValuesFields(fields_, aliasPrimDouble);
        std_ = new StandardClass(aliasDouble, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL);
        standards.put(aliasDouble, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        numbersAbsMethods(methods_, aliasNumber);
        std_ = new StandardClass(aliasNumber, fields_, constructors_, methods_, aliasObject, MethodModifier.ABSTRACT);
        standards.put(aliasNumber, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasCharAt, params_, aliasPrimChar, false, MethodModifier.NORMAL, aliasCharacter);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasLength, noTypes_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasSubSequence, params_, aliasCharacter, false, MethodModifier.NORMAL, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCharValue, params_, aliasPrimChar, false, MethodModifier.NORMAL, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharacter);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar, aliasPrimChar);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger, false, MethodModifier.STATIC, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar, aliasPrimInteger);
        method_ = new StandardMethod(aliasDigit, params_, aliasPrimInteger, false, MethodModifier.STATIC, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasPrimInteger);
        method_ = new StandardMethod(aliasForDigit, params_, aliasPrimChar, false, MethodModifier.STATIC, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasGetDirectionality, params_, aliasPrimByte, false, MethodModifier.STATIC, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasGetType, params_, aliasPrimByte, false, MethodModifier.STATIC, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIsDigit, params_, aliasPrimBoolean, false, MethodModifier.STATIC, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIsLetter, params_, aliasPrimBoolean, false, MethodModifier.STATIC, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIsLetterOrDigit, params_, aliasPrimBoolean, false, MethodModifier.STATIC, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIsWordChar, params_, aliasPrimBoolean, false, MethodModifier.STATIC, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIsWhitespace, params_, aliasPrimBoolean, false, MethodModifier.STATIC, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIsLowerCase, params_, aliasPrimBoolean, false, MethodModifier.STATIC, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIsUpperCase, params_, aliasPrimBoolean, false, MethodModifier.STATIC, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIsSpace, params_, aliasPrimBoolean, false, MethodModifier.STATIC, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasToLowerCase, params_, aliasPrimChar, false, MethodModifier.STATIC, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasToUpperCase, params_, aliasPrimChar, false, MethodModifier.STATIC, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasToString, params_, aliasString, false, MethodModifier.STATIC, aliasCharacter);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToString, params_, aliasString, false, MethodModifier.NORMAL, aliasCharacter);
        methods_.put(method_.getId(), method_);
        StandardClass stdcl_;
        constructors_ = new CustList<StandardConstructor>();
        params_ = new StringList(aliasPrimChar);
        ctor_ = new StandardConstructor(params_, false);
        constructors_.add(ctor_);
        stdcl_ = new StandardClass(aliasCharacter, fields_, constructors_, methods_, aliasObject, MethodModifier.FINAL);
        stdcl_.getDirectInterfaces().add(aliasCharSequence);
        std_ = stdcl_;
        standards.put(aliasCharacter, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasCharAt, params_, aliasPrimChar, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasLength, noTypes_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasSubSequence, params_, aliasString, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasSubstring, params_, aliasString, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasSubstring, params_, aliasString, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasEqualsIgnoreCase, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasCompareToIgnoreCase, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasContains, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasStartsWith, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString, aliasPrimInteger);
        method_ = new StandardMethod(aliasStartsWith, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasEndsWith, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString, aliasPrimInteger);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar, aliasPrimInteger);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString, aliasPrimInteger);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar, aliasPrimInteger);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsEmpty, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToCharArray, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar), false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetBytes, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasPrimByte), false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasGetBytes, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasPrimByte), false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasFormat, params_, aliasString, true, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasSplit, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString, aliasPrimInteger);
        method_ = new StandardMethod(aliasSplit, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasSplit, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar, aliasPrimInteger);
        method_ = new StandardMethod(aliasSplit, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasSplits, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), true, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString, aliasPrimInteger);
        method_ = new StandardMethod(aliasSplits, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), true, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasSplits, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), true, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar, aliasPrimInteger);
        method_ = new StandardMethod(aliasSplits, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), true, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString, aliasString);
        method_ = new StandardMethod(aliasReplace, params_, aliasString, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar, aliasPrimChar);
        method_ = new StandardMethod(aliasReplace, params_, aliasString, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasString, aliasPrimInteger, aliasPrimInteger);
        method_ = new StandardMethod(aliasRegionMatches, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean, aliasPrimInteger, aliasString, aliasPrimInteger, aliasPrimInteger);
        method_ = new StandardMethod(aliasRegionMatches, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToLowerCase, params_, aliasString, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToUpperCase, params_, aliasString, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTrim, params_, aliasString, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIntern, params_, aliasString, false, MethodModifier.NORMAL, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean);
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimByte);
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimShort);
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong);
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat);
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble);
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar));
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, aliasString);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar),aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasValueOf, params_, aliasString, false, MethodModifier.STATIC, aliasString);
        methods_.put(method_.getId(), method_);
        constructors_ = new CustList<StandardConstructor>();
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_, false);
        constructors_.add(ctor_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimByte));
        ctor_ = new StandardConstructor(params_, false);
        constructors_.add(ctor_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimByte), aliasString);
        ctor_ = new StandardConstructor(params_, false);
        constructors_.add(ctor_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimByte), aliasPrimInteger, aliasPrimInteger);
        ctor_ = new StandardConstructor(params_, false);
        constructors_.add(ctor_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimByte), aliasPrimInteger, aliasPrimInteger, aliasString);
        ctor_ = new StandardConstructor(params_, false);
        constructors_.add(ctor_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar));
        ctor_ = new StandardConstructor(params_, false);
        constructors_.add(ctor_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar), aliasPrimInteger, aliasPrimInteger);
        ctor_ = new StandardConstructor(params_, false);
        constructors_.add(ctor_);
        params_ = new StringList(aliasStringBuilder);
        ctor_ = new StandardConstructor(params_, false);
        constructors_.add(ctor_);
        stdcl_ = new StandardClass(aliasString, fields_, constructors_, methods_, aliasObject, MethodModifier.FINAL);
        stdcl_.getDirectInterfaces().add(aliasCharSequence);
        std_ = stdcl_;
        standards.put(aliasString, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasCharAt, params_, aliasPrimChar, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasLength, noTypes_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasSubSequence, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimByte);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimShort);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence,aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar));
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar),aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCapacity, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasDelete, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasDeleteCharAt, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasClear, params_, aliasVoid, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString, aliasPrimInteger);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar, aliasPrimInteger);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString, aliasPrimInteger);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar, aliasPrimInteger);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasPrimBoolean);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasPrimByte);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasPrimShort);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasPrimChar);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasPrimInteger);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasPrimLong);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasPrimFloat);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasPrimDouble);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasCharSequence);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasCharSequence,aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, aliasString);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar));
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger, PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar),aliasPrimInteger,aliasPrimInteger);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasReverse, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger,aliasPrimInteger,aliasString);
        method_ = new StandardMethod(aliasReplace, params_, aliasStringBuilder, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger,aliasPrimChar);
        method_ = new StandardMethod(aliasSetCharAt, params_, aliasVoid, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasSetLength, params_, aliasVoid, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToString, params_, aliasString, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTrimToSize, params_, aliasVoid, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger);
        method_ = new StandardMethod(aliasEnsureCapacity, params_, aliasVoid, false, MethodModifier.NORMAL, aliasStringBuilder);
        methods_.put(method_.getId(), method_);
        buildOther();
    }
    public void setupOverrides(ContextEl _context) {
        for (StandardType t: standards.values()) {
            t.buildOverridingMethods(_context);
        }
    }
    public static FieldResult getDeclaredCustField(ContextEl _cont, boolean _staticContext, ClassArgumentMatching _class, boolean _superClass, String _name) {
        FieldResult resIns_ = getDeclaredCustFieldByContext(_cont, false, _class, _superClass, _name);
        FieldResult resSt_ = getDeclaredCustFieldByContext(_cont, true, _class, _superClass, _name);
        if (resIns_.getStatus() == SearchingMemberStatus.UNIQ) {
            if (!_staticContext) {
                return resIns_;
            }
            throw new StaticAccessException(_cont.joinPages());
        }
        return resSt_;
    }
    private static FieldResult getDeclaredCustFieldByContext(ContextEl _cont, boolean _static, ClassArgumentMatching _class, boolean _superClass, String _name) {
        String clCurName_ = _class.getName();
        String base_ = clCurName_;
        LgNames classes_ = _cont.getStandards();
        StandardType root_ = classes_.getStandards().getVal(base_);
        StringList classeNames_ = new StringList();
        classeNames_.add(root_.getName());
        if (root_ instanceof StandardClass) {
            classeNames_.addAllElts(((StandardClass) root_).getAllSuperClasses(_cont));
        }
        for (String s: classeNames_) {
            if (StringList.quickEq(s, classes_.getAliasObject())) {
                continue;
            }
            String formatted_ = s;
            StandardType custClass_;
            custClass_ = classes_.getStandards().getVal(s);
            for (EntryCust<String, StandardField> e: custClass_.getFields().entryList()) {
                if (!StringList.quickEq(e.getKey(), _name)) {
                    continue;
                }
                if (_static) {
                    if (!e.getValue().isStaticField()) {
                        break;
                    }
                } else {
                    if (e.getValue().isStaticField()) {
                        break;
                    }
                }
                FieldResult r_ = new FieldResult();
                String formattedType_ = e.getValue().getClassName();
                String realType_ = formattedType_;
                FieldInfo f_ = new FieldInfo(_name, formatted_, formattedType_, realType_, _static, e.getValue().isFinalField());
                r_.setId(f_);
                r_.setStatus(SearchingMemberStatus.UNIQ);
                return r_;
            }
            if (!_superClass) {
                FieldResult r_ = new FieldResult();
                r_.setStatus(SearchingMemberStatus.ZERO);
                return r_;
            }
        }
        FieldResult r_ = new FieldResult();
        r_.setStatus(SearchingMemberStatus.ZERO);
        return r_;
    }
    public static ClassMethodIdReturn getDeclaredCustMethod(boolean _failIfError, ContextEl _conf, int _varargOnly, boolean _staticContext, ClassArgumentMatching _class, String _name, boolean _superClass, boolean _accessFromSuper, ClassArgumentMatching... _argsClass) {
        LgNames classes_ = _conf.getStandards();
        String clCurName_ = _class.getName();
        String baseClass_ = clCurName_;
        for (ClassArgumentMatching c:_argsClass) {
            if (c.matchVoid(_conf)) {
                throw new VoidArgumentException(clCurName_+DOT+_name+RETURN_LINE+_conf.joinPages());
            }
        }
        if (classes_.getStandards().getVal(baseClass_) instanceof StandardInterface) {
            ClassMethodIdResult resInst_ = getDeclaredCustMethodByInterfaceInherit(_conf, _accessFromSuper, _varargOnly, false, _class, _name, _superClass, _argsClass);
            boolean foundInst_ = false;
            if (!_staticContext) {
                if (resInst_.getStatus() == SearchingMemberStatus.UNIQ) {
                    foundInst_ = true;
                }
            }
            ClassMethodIdResult resStatic_ = getDeclaredCustMethodByInterfaceInherit(_conf, _accessFromSuper, _varargOnly, true, _class, _name, _superClass, _argsClass);
            if (foundInst_) {
                return toFoundMethod(_conf, resInst_);
            }
            if (!_staticContext && _conf.isAmbigous() && _failIfError) {
                String trace_ = clCurName_+DOT+_name+PAR_LEFT;
                StringList classesNames_ = new StringList();
                for (ClassArgumentMatching c: _argsClass) {
                    classesNames_.add(c.getName());
                }
                trace_ += classesNames_.join(SEP_ARG);
                trace_ += PAR_RIGHT;
                throw new NoSuchDeclaredMethodException(trace_+RETURN_LINE+_conf.joinPages());
            }
            if (resStatic_.getStatus() == SearchingMemberStatus.UNIQ) {
                return toFoundMethod(_conf, resStatic_);
            }
            if (_staticContext && resInst_.getStatus() == SearchingMemberStatus.UNIQ && _failIfError) {
                //static access
                throw new StaticAccessException(_conf.joinPages());
            }
            String trace_ = clCurName_+DOT+_name+PAR_LEFT;
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _argsClass) {
                classesNames_.add(c.getName());
            }
            trace_ += classesNames_.join(SEP_ARG);
            trace_ += PAR_RIGHT;
            throw new NoSuchDeclaredMethodException(trace_+RETURN_LINE+_conf.joinPages());
        }
        ClassMethodIdResult resInst_ = getDeclaredCustMethodByClassInherit(_conf, _accessFromSuper, _varargOnly, false, _class, _name, _superClass, _argsClass);
        boolean foundInst_ = false;
        if (!_staticContext) {
            if (resInst_.getStatus() == SearchingMemberStatus.UNIQ) {
                foundInst_ = true;
            }
        }
        ClassMethodIdResult resStatic_ = getDeclaredCustMethodByClassInherit(_conf, _accessFromSuper, _varargOnly, true, _class, _name, _superClass, _argsClass);
        if (foundInst_) {
            return toFoundMethod(_conf, resInst_);
        }
        if (!_staticContext && _conf.isAmbigous()) {
            clCurName_ = _class.getName();
            String trace_ = clCurName_+DOT+_name+PAR_LEFT;
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _argsClass) {
                classesNames_.add(c.getName());
            }
            trace_ += classesNames_.join(SEP_ARG);
            trace_ += PAR_RIGHT;
            throw new NoSuchDeclaredMethodException(trace_+RETURN_LINE+_conf.joinPages());
        }
        if (resStatic_.getStatus() == SearchingMemberStatus.UNIQ) {
            return toFoundMethod(_conf, resStatic_);
        }
        if (!_failIfError) {
            return new ClassMethodIdReturn(false);
        }
        if (resInst_.getStatus() == SearchingMemberStatus.UNIQ) {
            //static access
            throw new StaticAccessException(_conf.joinPages());
        }
        String trace_ = clCurName_+DOT+_name+PAR_LEFT;
        StringList classesNames_ = new StringList();
        for (ClassArgumentMatching c: _argsClass) {
            classesNames_.add(c.getName());
        }
        trace_ += classesNames_.join(SEP_ARG);
        trace_ += PAR_RIGHT;
        throw new NoSuchDeclaredMethodException(trace_+RETURN_LINE+_conf.joinPages());
    }
    public static ConstrustorIdVarArg getDeclaredCustConstructor(ContextEl _conf, int _varargOnly, ClassArgumentMatching _class, ClassArgumentMatching..._args) {
        LgNames classes_ = _conf.getStandards();
        StandardClass custClass_ = null;
        String clCurName_ = _class.getName();
        custClass_ = (StandardClass) classes_.getStandards().getVal(clCurName_);
        if (custClass_ == null) {
            return null;
        }
        String glClass_ = _conf.getLastPage().getGlobalClass();
        CustList<ConstructorId> possibleMethods_ = new CustList<ConstructorId>();
        for (ClassArgumentMatching c:_args) {
            if (c.matchVoid(_conf)) {
                throw new VoidArgumentException(clCurName_+RETURN_LINE+_conf.joinPages());
            }
        }
        CustList<StandardConstructor> constructors_;
        constructors_ = custClass_.getConstructors();
        if (constructors_.isEmpty()) {
            if (_args.length == 0) {
                ConstrustorIdVarArg out_;
                out_ = new ConstrustorIdVarArg();
                out_.setRealId(new ConstructorId(clCurName_, new EqList<ClassName>()));
                out_.setConstId(out_.getRealId());
                return out_;
            }
        }
        for (StandardConstructor e: constructors_) {
            ConstructorId ctor_ = e.getId(clCurName_);
            if (_varargOnly > -1) {
                if (!ctor_.isVararg()) {
                    continue;
                }
            }
            ClassMatching[] p_ = getParameters(ctor_);
            if (!isPossibleMethod(_conf, clCurName_, _varargOnly, ctor_.isVararg(), p_, _args)) {
                continue;
            }
            possibleMethods_.add(ctor_);
        }
        if (possibleMethods_.isEmpty()) {
            String trace_ = clCurName_+PAR_LEFT;
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _args) {
                classesNames_.add(c.getName());
            }
            trace_ += classesNames_.join(SEP_ARG);
            trace_ += PAR_RIGHT;
            throw new NoSuchDeclaredConstructorException(trace_+RETURN_LINE+_conf.joinPages());
        }
        StringMap<StringList> map_;
        map_ = new StringMap<StringList>();
        ArgumentsGroup gr_ = new ArgumentsGroup(_conf, map_, _args);
        gr_.setGlobalClass(glClass_);
        Parametrables<ConstructorInfo> signatures_ = new Parametrables<ConstructorInfo>();
        for (ConstructorId m: possibleMethods_) {
            ParametersGroup p_ = new ParametersGroup();
            for (String c: m.getParametersTypes()) {
                p_.add(new ClassMatching(c));
            }
            ConstructorInfo mloc_ = new ConstructorInfo();
            StandardConstructor ctr_ = classes_.getConstructorBodiesById(clCurName_, m).first();
            mloc_.setConstr(ctr_.getId(clCurName_));
            mloc_.setConstraints(m);
            mloc_.setParameters(p_);
            mloc_.setClassName(clCurName_);
            signatures_.add(mloc_);
        }
        sortCtors(signatures_, gr_);
        if (gr_.isAmbigous()) {
            String trace_ = clCurName_+PAR_LEFT;
            StringList classesNames_ = new StringList();
            for (ClassArgumentMatching c: _args) {
                classesNames_.add(c.getName());
            }
            trace_ += classesNames_.join(SEP_ARG);
            trace_ += PAR_RIGHT;
            throw new NoSuchDeclaredConstructorException(trace_+RETURN_LINE+_conf.joinPages());
        }
        ConstructorId ctor_ = signatures_.first().getConstraints();
        ConstrustorIdVarArg out_;
        out_ = new ConstrustorIdVarArg();
        if (ctor_.isVararg() && _varargOnly == -1) {
            if (varArgWrap(_conf, glClass_, clCurName_, ctor_, _args)) {
                out_.setVarArgToCall(true);
            }
        }
        out_.setRealId(ctor_);
        out_.setConstId(ctor_);
        return out_;
    }
    private static ClassMethodIdReturn toFoundMethod(ContextEl _conf, ClassMethodIdResult _res){
        LgNames classes_ = _conf.getStandards();
        ClassMethodIdReturn idRet_ = new ClassMethodIdReturn(true);
        ClassMethodId idCl_ = _res.getId();
        String clCurName_ = idCl_.getClassName();
        MethodId id_ = idCl_.getConstraints();
        MethodId realId_ = _res.getRealId();
        idRet_.setRealId(realId_);
        String realClass_ = _res.getRealClass();
        idRet_.setRealClass(realClass_);
        idRet_.setId(new ClassMethodId(clCurName_, id_));
        idRet_.setVarArgToCall(_res.isVarArgToCall());
        StandardMethod methods_ = classes_.getMethodBodiesById(realClass_, realId_);
        StandardMethod m_ = methods_;
        idRet_.setReturnType(_res.getReturnType());
        idRet_.setStaticMethod(m_.isStaticMethod());
        idRet_.setAbstractMethod(m_.isAbstractMethod());
        return idRet_;
    }
    private static ClassMethodIdResult getDeclaredCustMethodByClassInherit(ContextEl _conf, boolean _accessFromSuper, int _varargOnly, boolean _static, ClassArgumentMatching _class, String _name, boolean _superClass, ClassArgumentMatching... _argsClass) {
        LgNames classes_ = _conf.getStandards();
        String clCurName_ = _class.getName();
        String base_ = clCurName_;
        StandardType r_ = classes_.getStandards().getVal(base_);
        if (_static) {
            StringList classeNames_ = new StringList();
            classeNames_.add(r_.getName());
            if (r_ instanceof StandardClass) {
                classeNames_.addAllElts(((StandardClass) r_).getAllSuperClasses(_conf));
            }
            for (String s: classeNames_) {
                if (StringList.quickEq(s, classes_.getAliasObject())) {
                    continue;
                }
                String formatted_ = s;
                ObjectNotNullMap<ClassMethodId, MethodMetaInfo> methods_;
                methods_ = getDeclaredCustMethodByType(_conf, _varargOnly, _accessFromSuper, _static, _superClass, clCurName_, new ClassArgumentMatching(formatted_), _name, _argsClass);
                ClassMethodIdResult res_ = getCustResult(_conf, _varargOnly, methods_, _name, _argsClass);
                if (res_.getStatus() == SearchingMemberStatus.ZERO) {
                    if (!_superClass) {
                        return res_;
                    }
                    continue;
                }
                return res_;
            }
            ClassMethodIdResult res_ = new ClassMethodIdResult();
            res_.setStatus(SearchingMemberStatus.ZERO);
            return res_;
        }
        ObjectNotNullMap<ClassMethodId, MethodMetaInfo> methods_;
        methods_ = getDeclaredCustMethodByType(_conf, _varargOnly, _accessFromSuper, _static, _superClass, clCurName_, new ClassArgumentMatching(clCurName_), _name, _argsClass);
        return getCustResult(_conf, _varargOnly, methods_, _name, _argsClass);
    }
    private static ClassMethodIdResult getDeclaredCustMethodByInterfaceInherit(ContextEl _conf, boolean _accessFromSuper, int _varargOnly, boolean _static, ClassArgumentMatching _class, String _name, boolean _superClass, ClassArgumentMatching... _argsClass) {
        String clCurName_ = _class.getName();
        if (_static) {
            ObjectNotNullMap<ClassMethodId, MethodMetaInfo> methods_;
            methods_ = getDeclaredCustMethodByType(_conf, _varargOnly, _accessFromSuper, _static, _superClass, clCurName_, new ClassArgumentMatching(clCurName_), _name, _argsClass);
            return getCustResult(_conf, _varargOnly, methods_, _name, _argsClass);
        }
        ObjectNotNullMap<ClassMethodId, MethodMetaInfo> methods_;
        methods_ = getDeclaredCustMethodByType(_conf, _varargOnly, _accessFromSuper, _static, _superClass, clCurName_, new ClassArgumentMatching(clCurName_), _name, _argsClass);
        return getCustResult(_conf, _varargOnly, methods_, _name, _argsClass);
    }
    private static ObjectNotNullMap<ClassMethodId, MethodMetaInfo>
    getDeclaredCustMethodByType(ContextEl _conf, int _varargOnly, boolean _accessFromSuper,
            boolean _static, boolean _superClass, String _fromClass, ClassArgumentMatching _class, String _name, ClassArgumentMatching... _argsClass) {
        LgNames classes_ = _conf.getStandards();
        String clCurName_ = _class.getName();
        String baseCurName_ = clCurName_;
        StandardType root_ = classes_.getStandards().getVal(baseCurName_);
        ObjectNotNullMap<ClassMethodId, MethodMetaInfo> methods_;
        methods_ = new ObjectNotNullMap<ClassMethodId, MethodMetaInfo>();
        if (_static) {
            for (StandardMethod e: root_.getMethods().values()) {
                if (e.isStaticMethod()) {
                    MethodId id_ = e.getId();
                    String returnType_ = e.getReturnType();
                    MethodMetaInfo info_ = new MethodMetaInfo(clCurName_, id_, MethodModifier.STATIC, returnType_);
                    ClassMethodId clId_ = new ClassMethodId(clCurName_, id_);
                    methods_.put(clId_, info_);
                }
            }
        } else {
            for (EntryCust<MethodId, EqList<ClassMethodId>> e: root_.getAllOverridingMethods().entryList()) {
                for (ClassMethodId s: e.getValue()) {
                    String name_ = s.getClassName();
                    if (_accessFromSuper) {
                        String base_ = name_;
                        if (StringList.quickEq(base_, root_.getName())) {
                            continue;
                        }
                    }
                    String formattedClass_;
                    if (_superClass) {
                        formattedClass_ = name_;
                    } else {
                        String base_ = name_;
                        if (!StringList.quickEq(base_, root_.getName())) {
                            continue;
                        }
                        formattedClass_ = clCurName_;
                    }
                    MethodId id_ = s.getConstraints();
                    StandardMethod sup_ = classes_.getMethodBodiesById(name_, id_);
                    String ret_ = sup_.getReturnType();
                    MethodMetaInfo info_ = new MethodMetaInfo(formattedClass_, id_, MethodModifier.NORMAL, ret_);
                    ClassMethodId clId_ = new ClassMethodId(formattedClass_, id_);
                    methods_.put(clId_, info_);
                }
            }
        }
        return methods_;
    }
    private static ClassMethodIdResult getCustResult(ContextEl _conf, int _varargOnly,
            ObjectNotNullMap<ClassMethodId, MethodMetaInfo> _methods,
            String _name, ClassArgumentMatching... _argsClass) {
        CustList<ClassMethodId> possibleMethods_ = new CustList<ClassMethodId>();
        String glClass_ = _conf.getLastPage().getGlobalClass();
        for (EntryCust<ClassMethodId, MethodMetaInfo> e: _methods.entryList()) {
            ClassMethodId key_ = e.getKey();
            MethodId id_ = key_.getConstraints();
            if (_varargOnly > -1) {
                if (!id_.isVararg()) {
                    continue;
                }
            }
            if (!StringList.quickEq(id_.getName(), _name)) {
                continue;
            }
            ClassMatching[] p_ = getParameters(id_);
            String formattedType_ = e.getValue().getClassName();
            if (!isPossibleMethod(_conf, formattedType_, _varargOnly, id_.isVararg(), p_, _argsClass)) {
                continue;
            }
            possibleMethods_.add(key_);
        }
        if (possibleMethods_.isEmpty()) {
            ClassMethodIdResult res_ = new ClassMethodIdResult();
            res_.setStatus(SearchingMemberStatus.ZERO);
            return res_;
        }
        StringMap<StringList> map_;
        map_ = new StringMap<StringList>();
        ArgumentsGroup gr_ = new ArgumentsGroup(_conf, map_, _argsClass);
        gr_.setGlobalClass(glClass_);
        Parametrables<MethodInfo> signatures_ = new Parametrables<MethodInfo>();
        for (ClassMethodId m: possibleMethods_) {
            ParametersGroup p_ = new ParametersGroup();
            MethodMetaInfo info_ = _methods.getVal(m);
            MethodId realId_ = info_.getRealId();
            for (String c: realId_.getParametersTypes()) {
                p_.add(new ClassMatching(c));
            }
            MethodInfo mloc_ = new MethodInfo();
            String formattedType_ = info_.getClassName();
            mloc_.setClassName(formattedType_);
            mloc_.setStatic(info_.getModifier() == MethodModifier.STATIC);
            mloc_.setConstraints(realId_);
            mloc_.setParameters(p_);
            mloc_.setReturnType(info_.getReturnType());
            signatures_.add(mloc_);
        }
        _conf.setAmbigous(false);
        sortFct(signatures_, gr_);
        if (gr_.isAmbigous()) {
            _conf.setAmbigous(true);
            ClassMethodIdResult res_ = new ClassMethodIdResult();
            res_.setStatus(SearchingMemberStatus.ZERO);
            return res_;
        }
        MethodId constraints_ = signatures_.first().getConstraints();
        MethodId realId_ = constraints_;
        String className_ = signatures_.first().getClassName();
        MethodMetaInfo info_ = _methods.getVal(new ClassMethodId(className_, realId_));
        String baseClassName_ = info_.getClassName();
        ClassMethodIdResult res_ = new ClassMethodIdResult();
        MethodId id_;
        String realClass_;
        realClass_ = baseClassName_;
        id_ = constraints_;
        res_.setCorrectTemplated(true);
        res_.setId(new ClassMethodId(realClass_, id_));
        res_.setStatus(SearchingMemberStatus.UNIQ);
        if (_varargOnly == -1 && varArgWrap(_conf, glClass_, realClass_, constraints_, _argsClass)) {
            res_.setVarArgToCall(true);
        }
        res_.setRealId(constraints_);
        res_.setRealClass(baseClassName_);
        res_.setReturnType(info_.getReturnType());
        return res_;
    }
    static boolean isPossibleMethod(ContextEl _context, String _class, int _varargOnly, boolean _vararg, ClassMatching[] _params, ClassArgumentMatching..._argsClass) {
        int startOpt_ = _argsClass.length;
        boolean checkOnlyDem_ = true;
        int nbDem_ = _params.length;
        if (!_vararg) {
            if (_params.length != _argsClass.length) {
                return false;
            }
        } else {
            if (_params.length > _argsClass.length + 1) {
                return false;
            }
            if (_varargOnly != 0) {
                checkOnlyDem_ = false;
                nbDem_--;
                startOpt_ = _params.length - 1;
            }
            if (_varargOnly > 0) {
                if (startOpt_ != _varargOnly - 1) {
                    return false;
                }
            }
        }
        int len_ = nbDem_;
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            if (_argsClass[i].isVariable()) {
                if (_params[i].isPrimitive(_context)) {
                    return false;
                }
                continue;
            }
            if (!canBeUseAsArgument(_params[i].getClassName(), _argsClass[i].getName(), _context)) {
                return false;
            }
        }
        if (checkOnlyDem_) {
            return true;
        }
        if (_params.length == _argsClass.length) {
            int last_ = _params.length - 1;
            String param_ = _params[last_].getClassName();
            if (canBeUseAsArgument(param_, _argsClass[last_].getName(), _context)) {
                return true;
            }
            param_ = PrimitiveTypeUtil.getQuickComponentType(param_);
            return canBeUseAsArgument(param_, _argsClass[last_].getName(), _context);
        }
        len_ = _argsClass.length;
        int last_ = _params.length - 1;
        String param_ = _params[last_].getClassName();
        param_ = PrimitiveTypeUtil.getQuickComponentType(param_);
        for (int i = startOpt_; i < len_; i++) {
            if (!canBeUseAsArgument(param_, _argsClass[i].getName(), _context)) {
                return false;
            }
        }
        return true;
    }
    static boolean varArgWrap(ContextEl _context, String _globalClass, String _class, Identifiable _id, ClassArgumentMatching..._argsClass) {
        if (!_id.isVararg()) {
            return false;
        }
        ClassMatching[] p_ = getParameters(_id);
        if (p_.length != _argsClass.length) {
            return true;
        }
        int last_ = p_.length - 1;
        String param_ = p_[last_].getClassName();
        return !canBeUseAsArgument(param_, _argsClass[last_].getName(), _context);
    }
    static ClassMatching[] getParameters(Identifiable _id) {
        StringList params_ = _id.getParametersTypes();
        int nbParams_ = params_.size();
        ClassMatching[] p_ = new ClassMatching[nbParams_];
        int i_ = CustList.FIRST_INDEX;
        if (!_id.isVararg()) {
            for (String c: params_) {
                p_[i_] = new ClassMatching(c);
                i_++;
            }
        } else {
            for (String c: params_) {
                if (i_ == nbParams_ - 1) {
                    String c_ = StringList.replace(c, VARARG_SUFFIX, EMPTY_STRING);
                    c_ = PrimitiveTypeUtil.getPrettyArrayType(c_);
                    p_[i_] = new ClassMatching(c_);
                } else {
                    p_[i_] = new ClassMatching(c);
                }
                i_++;
            }
        }
        return p_;
    }
    static void sortFct(Parametrables<MethodInfo> _fct, ArgumentsGroup _context) {
        int len_ = _fct.size();
        for (int i = CustList.SECOND_INDEX; i < len_; i++) {
            process(_fct, i, _context);
        }
        if (_fct.first().getParameters().isError()) {
            _context.setAmbigous(true);
        }
    }
    static void sortCtors(Parametrables<ConstructorInfo> _fct, ArgumentsGroup _context) {
        int len_ = _fct.size();
        for (int i = CustList.SECOND_INDEX; i < len_; i++) {
            process(_fct, i, _context);
        }
        if (_fct.first().getParameters().isError()) {
            _context.setAmbigous(true);
        }
    }
    static void process(Fcts _list, int _i, ArgumentsGroup _context) {
        Parametrable pFirst_ = _list.first();
        Parametrable pCurrent_ = _list.get(_i);
        int res_ = compare(_context, pFirst_, pCurrent_);
        if (res_ == CustList.SWAP_SORT) {
            _list.swapIndexes(CustList.FIRST_INDEX, _i);
        }
    }
    static int compare(ArgumentsGroup _context, Parametrable _o1, Parametrable _o2) {
        int len_ = _o1.getParameters().size();
        ContextEl context_ = _context.getContext();
        String glClass_ = _context.getGlobalClass();
        String glClassOne_ = _o1.getClassName();
        String glClassTwo_ = _o2.getClassName();
        if (_o1.isVararg()) {
            if (!_o2.isVararg()) {
                return CustList.SWAP_SORT;
            }
        }
        if (!_o1.isVararg()) {
            if (_o2.isVararg()) {
                return CustList.NO_SWAP_SORT;
            }
        }
        if (_o1.isVararg()) {
            if (_o2.isVararg()) {
                if (len_ < _o2.getParameters().size()) {
                    return CustList.SWAP_SORT;
                }
                if (len_ > _o2.getParameters().size()) {
                    return CustList.NO_SWAP_SORT;
                }
                boolean varOne_ = varArgWrap(context_, glClass_, glClassOne_, _o1.getId(), _context.getArgumentsArray());
                boolean varTwo_ = varArgWrap(context_, glClass_, glClassTwo_, _o2.getId(), _context.getArgumentsArray());
                if (varOne_ && !varTwo_) {
                    return CustList.SWAP_SORT;
                }
                if (!varOne_ && varTwo_) {
                    return CustList.NO_SWAP_SORT;
                }
            }
        }
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            ClassArgumentMatching selected_ = _context.get(i);
            ClassMatching one_ = _o1.getParameters().get(i);
            String paramOne_ = one_.getClassName();
            if (paramOne_.endsWith(VARARG_SUFFIX)) {
                paramOne_ = StringList.replace(paramOne_, VARARG_SUFFIX, EMPTY_STRING);
                paramOne_ = PrimitiveTypeUtil.getPrettyArrayType(paramOne_);
            }
            ClassMatching two_ = _o2.getParameters().get(i);
            String paramTwo_ = two_.getClassName();
            if (paramTwo_.endsWith(VARARG_SUFFIX)) {
                paramTwo_ = StringList.replace(paramTwo_, VARARG_SUFFIX, EMPTY_STRING);
                paramTwo_ = PrimitiveTypeUtil.getPrettyArrayType(paramTwo_);
            }
            one_ = new ClassMatching(paramOne_);
            two_ = new ClassMatching(paramTwo_);
            if (one_.matchClass(two_)) {
                continue;
            }
            if (selected_.isVariable()) {
                if (one_.isAssignableFrom(two_, context_)) {
                    return CustList.SWAP_SORT;
                }
                if (two_.isAssignableFrom(one_, context_)) {
                    return CustList.NO_SWAP_SORT;
                }
                _o1.getParameters().setError(true);
                _o2.getParameters().setError(true);
                return CustList.NO_SWAP_SORT;
            }
            ClassMatching toPrOne_ = one_;
            ClassMatching toPrTwo_ = two_;
            boolean onePrimExcl_ = false;
            boolean twoPrimExcl_ = false;
            if (one_.isPrimitive(context_) && !two_.isPrimitive(context_)) {
                onePrimExcl_ = true;
            }
            if (!one_.isPrimitive(context_) && two_.isPrimitive(context_)) {
                twoPrimExcl_ = true;
            }
            if (selected_.isPrimitive(context_)) {
                if (onePrimExcl_) {
                    return CustList.NO_SWAP_SORT;
                }
                if (twoPrimExcl_) {
                    return CustList.SWAP_SORT;
                }
                toPrOne_ = PrimitiveTypeUtil.toPrimitive(one_, context_);
                toPrTwo_ = PrimitiveTypeUtil.toPrimitive(two_, context_);
            } else {
                ClassArgumentMatching clMatch_ = PrimitiveTypeUtil.toPrimitive(selected_, true, context_);
                if (clMatch_.isPrimitive(context_)) {
                    if (onePrimExcl_) {
                        return CustList.SWAP_SORT;
                    }
                    if (twoPrimExcl_) {
                        return CustList.NO_SWAP_SORT;
                    }
                    toPrOne_ = PrimitiveTypeUtil.toPrimitive(one_, context_);
                    toPrTwo_ = PrimitiveTypeUtil.toPrimitive(two_, context_);
                }
            }
            if (toPrOne_.isAssignableFrom(toPrTwo_, context_)) {
                return CustList.SWAP_SORT;
            }
            if (toPrTwo_.isAssignableFrom(toPrOne_, context_)) {
                return CustList.NO_SWAP_SORT;
            }
            _o1.getParameters().setError(true);
            _o2.getParameters().setError(true);
            return CustList.NO_SWAP_SORT;
        }
        if (StringList.quickEq(_o2.getReturnType(), _o1.getReturnType())) {
            String baseOne_ = glClassOne_;
            String baseTwo_ = glClassTwo_;
            if (!canBeUseAsArgument(baseTwo_, baseOne_, context_)) {
                return CustList.SWAP_SORT;
            }
            return CustList.NO_SWAP_SORT;
        }
        if (canBeUseAsArgument(_o1.getReturnType(), _o2.getReturnType(), context_)) {
            return CustList.SWAP_SORT;
        }
        if (canBeUseAsArgument(_o2.getReturnType(), _o1.getReturnType(), context_)) {
            return CustList.SWAP_SORT;
        }
        _o1.getParameters().setError(true);
        _o2.getParameters().setError(true);
        return CustList.NO_SWAP_SORT;
    }
    //public ConstructorId getConstructor(String _name, ClassArgumentMatching... _arguments){}
    //public ConstructorId getMethod(ClassArgumentMatching _previous,String _name, ClassArgumentMatching... _arguments){}
    //    public Struct invokeConstructor(ConstructorId _id, Object...args) {//+error in result
        //        
        //    }
    //    public Struct invokeMethod(Object _instance,ClassMethodId _id, Argument...args) {//+error in result
        //        
        //    }
    private void numbersConstructors(CustList<StandardConstructor> _ctors, String _primitive) {
        StringList params_;
        StandardConstructor ctor_;
        params_ = new StringList(aliasString);
        ctor_ = new StandardConstructor(params_,false);
        _ctors.add(ctor_);
        params_ = new StringList(_primitive);
        ctor_ = new StandardConstructor(params_,false);
        _ctors.add(ctor_);
    }
    private void numbersValuesFields(StringMap<StandardField> _fields, String _primitive) {
        StandardField field_ = new StandardField(aliasMinValueField, _primitive, true, true);
        _fields.put(aliasMinValueField, field_);
        field_ = new StandardField(aliasMaxValueField, _primitive, true, true);
        _fields.put(aliasMinValueField, field_);
    }
    private void numbersValuesMethods(ObjectMap<MethodId, StandardMethod> _methods, String _owner, String _parserName, String _primitive) {
        StringList params_;
        StandardMethod method_;
        params_ = new StringList();
        method_ = new StandardMethod(aliasByteValue, params_, aliasPrimByte, false, MethodModifier.NORMAL, _owner);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasByteValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasShortValue, params_, aliasPrimShort, false, MethodModifier.NORMAL, _owner);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasShortValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIntValue, params_, aliasPrimInteger, false, MethodModifier.NORMAL, _owner);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasIntValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasLongValue, params_, aliasPrimLong, false, MethodModifier.NORMAL, _owner);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasLongValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFloatValue, params_, aliasPrimFloat, false, MethodModifier.NORMAL, _owner);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasFloatValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDoubleValue, params_, aliasPrimDouble, false, MethodModifier.NORMAL, _owner);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasDoubleValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToString, params_, aliasString, false, MethodModifier.NORMAL, _owner);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasToString, params_), method_);
        params_ = new StringList(_primitive);
        method_ = new StandardMethod(aliasToString, params_, aliasString, false, MethodModifier.STATIC, _owner);
        _methods.put(new MethodId(MethodModifier.STATIC, aliasToString, params_), method_);
        params_ = new StringList(aliasNumber);
        method_ = new StandardMethod(aliasEquals, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, _owner);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasEquals, params_), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(_parserName, params_, _owner, false, MethodModifier.STATIC, _owner);
        _methods.put(new MethodId(MethodModifier.STATIC, _parserName, params_), method_);
        params_ = new StringList(aliasString, aliasPrimInteger);
        method_ = new StandardMethod(_parserName, params_, _owner, false, MethodModifier.STATIC, _owner);
        _methods.put(new MethodId(MethodModifier.STATIC, _parserName, params_), method_);
        params_ = new StringList(_owner);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger, false, MethodModifier.NORMAL, _owner);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasCompareTo, params_), method_);
        params_ = new StringList(_primitive, _primitive);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger, false, MethodModifier.STATIC, _owner);
        _methods.put(new MethodId(MethodModifier.STATIC, aliasCompare, params_), method_);
    }
    private void numbersAbsMethods(ObjectMap<MethodId, StandardMethod> _methods, String _owner) {
        StringList params_;
        StandardMethod method_;
        params_ = new StringList();
        method_ = new StandardMethod(aliasByteValue, params_, aliasPrimByte, false, MethodModifier.ABSTRACT, _owner);
        _methods.put(new MethodId(MethodModifier.ABSTRACT, aliasByteValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasShortValue, params_, aliasPrimShort, false, MethodModifier.ABSTRACT, _owner);
        _methods.put(new MethodId(MethodModifier.ABSTRACT, aliasShortValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIntValue, params_, aliasPrimInteger, false, MethodModifier.ABSTRACT, _owner);
        _methods.put(new MethodId(MethodModifier.ABSTRACT, aliasIntValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasLongValue, params_, aliasPrimLong, false, MethodModifier.ABSTRACT, _owner);
        _methods.put(new MethodId(MethodModifier.ABSTRACT, aliasLongValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFloatValue, params_, aliasPrimFloat, false, MethodModifier.ABSTRACT, _owner);
        _methods.put(new MethodId(MethodModifier.ABSTRACT, aliasFloatValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDoubleValue, params_, aliasPrimDouble, false, MethodModifier.ABSTRACT, _owner);
        _methods.put(new MethodId(MethodModifier.ABSTRACT, aliasDoubleValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToString, params_, aliasString, false, MethodModifier.NORMAL, _owner);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasToString, params_), method_);
        params_ = new StringList(_owner);
        method_ = new StandardMethod(aliasToString, params_, aliasString, false, MethodModifier.STATIC, _owner);
        _methods.put(new MethodId(MethodModifier.STATIC, aliasToString, params_), method_);
        params_ = new StringList(aliasNumber);
        method_ = new StandardMethod(aliasEquals, params_, aliasPrimBoolean, false, MethodModifier.NORMAL, _owner);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasEquals, params_), method_);
        params_ = new StringList(_owner);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger, false, MethodModifier.NORMAL, _owner);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasCompareTo, params_), method_);
        params_ = new StringList(_owner, _owner);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger, false, MethodModifier.STATIC, _owner);
        _methods.put(new MethodId(MethodModifier.STATIC, aliasCompareTo, params_), method_);
    }
    public static boolean canBeUseAsArgument(String _param, String _arg, ContextEl _context) {
        String aliasVoid_ = _context.getStandards().getAliasVoid();
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
        if (paramComp_.getDim() != argComp_.getDim()) {
            return false;
        }
        param_ = new ClassArgumentMatching(paramComp_.getComponent());
        arg_ = new ClassArgumentMatching(argComp_.getComponent());
        if (StringList.quickEq(param_.getName(),arg_.getName())) {
            return true;
        }
        String aliasPrimBool_ = _context.getStandards().getAliasPrimBoolean();
        String aliasNumber_ = _context.getStandards().getAliasNumber();
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
        return false;
    }
    /** Only "object" classes are used as arguments */
    public static StringList getSubclasses(StringList _classNames, ContextEl _classes) {
        String aliasVoid_ = _classes.getStandards().getAliasVoid();
        StringList types_ = new StringList();
        for (String i: _classNames) {
            boolean sub_ = true;
            if (StringList.quickEq(i, aliasVoid_)) {
                for (String j: _classNames) {
                    if (!StringList.quickEq(i, j)) {
                        sub_ = false;
                        break;
                    }
                }
            } else {
                for (String j: _classNames) {
                    String baseSup_ = i;
                    String baseSub_ = j;
                    if (StringList.quickEq(baseSup_, baseSub_)) {
                        continue;
                    }
                    if (canBeUseAsArgument(baseSup_, baseSub_, _classes)) {
                        sub_ = false;
                        break;
                    }
                }
            }
            if (!sub_) {
                continue;
            }
            types_.add(i);
        }
        types_.removeDuplicates();
        return types_;
    }
    static AssignableFrom isAssignableFromCust(String _param,String _arg, ContextEl _context) {
        String aliasObject_ = _context.getStandards().getAliasObject();
        if (StringList.quickEq(_param, aliasObject_)) {
            return AssignableFrom.YES;
        }
        DimComp dPar_ = PrimitiveTypeUtil.getQuickComponentBaseType(_param);
        String p_ = dPar_.getComponent();
        StandardType clParBl_ = _context.getStandards().getStandards().getVal(p_);
        DimComp dArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arg);
        String a_ = dArg_.getComponent();
        StandardType clArgBl_ = _context.getStandards().getStandards().getVal(a_);
        if (clArgBl_ != null) {
            if (clParBl_ == null && !StringList.quickEq(p_, aliasObject_)) {
                return AssignableFrom.NO;
            }
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
        if (clParBl_ != null) {
            return AssignableFrom.NO;
        }
        return AssignableFrom.MAYBE;
    }
    static boolean isArrayAssignable(String _arrArg, String _arrParam, ContextEl _context) {
        String aliasObject_ = _context.getStandards().getAliasObject();
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
        StandardType clArgBl_ = _context.getStandards().getStandards().getVal(a_);
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
    public static ResultErrorStd invokeMethod(ContextEl _cont, boolean _natvararg, ClassMethodId _method, Object _instance, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getClassName();
        String name_ = _method.getConstraints().getName();
        StringList list_ = _method.getConstraints().getParametersTypes();
        checkArgumentsForInvoking(_cont, _natvararg, list_, args_);
        LgNames lgNames_ = _cont.getStandards();
        Object[] argsObj_ = adaptedArgs(list_, _cont, _cont.getStandards(), args_);
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasCharacter();
        String nbType_ = lgNames_.getAliasNumber();
        String stringType_ = lgNames_.getAliasString();
        String booleanPrimType_ = lgNames_.getAliasPrimBoolean();
        boolean null_ = false;
        if (!_method.getConstraints().isStaticMethod()) {
            if (_instance == null) {
                result_.setError(lgNames_.getAliasNull());
                null_ = true;
            }
        }
        if (null_) {
            return result_;
        }
        if (StringList.quickEq(type_, booleanType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasBooleanValue())) {
                result_.setResult(new BooleanStruct(((Boolean)_instance).booleanValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                result_.setResult(new IntStruct(ComparatorBoolean.cmp((Boolean)argsObj_[0],(Boolean) argsObj_[1])));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                result_.setResult(new IntStruct(ComparatorBoolean.cmp((Boolean)_instance,(Boolean) argsObj_[0])));
            } else if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                result_.setResult(new BooleanStruct(_instance == argsObj_[0]));
            } else if (StringList.quickEq(name_, lgNames_.getAliasParseBoolean())) {
                result_.setResult(new BooleanStruct(Boolean.parseBoolean((String)argsObj_[0])));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                if (!list_.isEmpty()) {
                    result_.setResult(new StdStruct(Boolean.toString((Boolean)argsObj_[0]), stringType_));
                } else {
                    result_.setResult(new StdStruct(Boolean.toString((Boolean)_instance), stringType_));
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
                result_.setResult(new CharStruct(((Character)_instance).charValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                Character one_ = (Character) argsObj_[0];
                Character two_ = (Character) argsObj_[1];
                result_.setResult(new IntStruct(one_.compareTo(two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                Character one_ = (Character) _instance;
                Character two_ = (Character) argsObj_[0];
                result_.setResult(new IntStruct(one_.compareTo(two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCharAt())) {
                Character one_ = (Character) _instance;
                Integer two_ = (Integer) argsObj_[0];
                if (two_.intValue() == 0) {
                    result_.setResult(new CharStruct(one_));
                } else {
                    result_.setError(lgNames_.getAliasBadIndex());
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasLength())) {
                result_.setResult(new IntStruct(1));
            } else if (StringList.quickEq(name_, lgNames_.getAliasSubSequence())) {
                Character one_ = (Character) _instance;
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
                Character one_ = (Character) _instance;
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
                    Character one_ = (Character) _instance;
                    result_.setResult(new StdStruct(Character.toString(one_), stringType_));
                } else {
                    Character one_ = (Character) argsObj_[0];
                    result_.setResult(new StdStruct(Character.toString(one_), stringType_));
                }
            }
        } else if (PrimitiveTypeUtil.isPureNumberClass(new ClassArgumentMatching(type_), lgNames_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                Number one_ = (Number) argsObj_[0];
                Number two_ = (Number) argsObj_[1];
                result_.setResult(new IntStruct(Numbers.compare(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                Number one_ = (Number) _instance;
                Number two_ = (Number) argsObj_[0];
                result_.setResult(new IntStruct(Numbers.compare(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                Number one_ = (Number) _instance;
                Number two_ = (Number) argsObj_[0];
                result_.setResult(new BooleanStruct(Numbers.eq(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasByteValue())) {
                Number one_ = (Number) _instance;
                result_.setResult(new ByteStruct(one_.byteValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasShortValue())) {
                Number one_ = (Number) _instance;
                result_.setResult(new ShortStruct(one_.shortValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasIntValue())) {
                Number one_ = (Number) _instance;
                result_.setResult(new IntStruct(one_.intValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasLongValue())) {
                Number one_ = (Number) _instance;
                result_.setResult(new LongStruct(one_.longValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasFloatValue())) {
                Number one_ = (Number) _instance;
                result_.setResult(new FloatStruct(one_.floatValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasDoubleValue())) {
                Number one_ = (Number) _instance;
                result_.setResult(new DoubleStruct(one_.doubleValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToString()) && list_.isEmpty()) {
                Number one_ = (Number) _instance;
                result_.setResult(new StdStruct(one_.toString(), stringType_));
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
                        result_.setResult(new StdStruct(Integer.toString(one_), stringType_));
                    } else{
                        String one_ = (String) argsObj_[0];
                        Long lg_;
                        int radix_ = 10;
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
                        result_.setResult(new StdStruct(Integer.toString(one_), stringType_));
                    } else {
                        String one_ = (String) argsObj_[0];
                        Long lg_;
                        int radix_ = 10;
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
                        result_.setResult(new StdStruct(Integer.toString(one_), stringType_));
                    } else {
                        String one_ = (String) argsObj_[0];
                        Long lg_;
                        int radix_ = 10;
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
                        result_.setResult(new StdStruct(Long.toString(one_), stringType_));
                    } else {
                        String one_ = (String) argsObj_[0];
                        Long lg_;
                        int radix_ = 10;
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
                        Float one_ = (Float) argsObj_[0];
                        result_.setResult(new BooleanStruct(Double.isNaN(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                        Float one_ = (Float) argsObj_[0];
                        result_.setResult(new BooleanStruct(Double.isInfinite(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                        Float one_ = (Float) argsObj_[0];
                        result_.setResult(new StdStruct(Float.toString(one_), stringType_));
                    } else {
                        String one_ = (String) argsObj_[0];
                        boolean valid_ = true;
                        if (!isValidDouble(one_)) {
                            valid_ = false;
                        }
                        Double d_ = null;
                        if (valid_) {
                            d_ = Double.parseDouble(one_);
                            if (d_ < Float.MIN_VALUE) {
                                valid_ = false;
                            } else if (d_ > Float.MAX_VALUE) {
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
                        Double one_ = (Double) argsObj_[0];
                        result_.setResult(new BooleanStruct(Double.isNaN(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasIsInfinite())) {
                        Double one_ = (Double) argsObj_[0];
                        result_.setResult(new BooleanStruct(Double.isInfinite(one_)));
                    } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                        Double one_ = (Double) argsObj_[0];
                        result_.setResult(new StdStruct(Double.toString(one_), stringType_));
                    } else {
                        String one_ = (String) argsObj_[0];
                        boolean valid_ = true;
                        if (!isValidDouble(one_)) {
                            valid_ = false;
                        }
                        Double d_ = null;
                        if (valid_) {
                            d_ = Double.parseDouble(one_);
                            if (Double.isInfinite(d_)) {
                                valid_ = false;
                            } else if (Double.isNaN(d_)) {
                                valid_ = false;
                            }
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
                Number one_ = (Number) _instance;
                Number two_ = (Number) argsObj_[0];
                result_.setResult(new IntStruct(Numbers.compare(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                Number one_ = (Number) _instance;
                Number two_ = (Number) argsObj_[0];
                result_.setResult(new BooleanStruct(Numbers.eq(one_, two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasByteValue())) {
                Number one_ = (Number) _instance;
                result_.setResult(new ByteStruct(one_.byteValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasShortValue())) {
                Number one_ = (Number) _instance;
                result_.setResult(new ShortStruct(one_.shortValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasIntValue())) {
                Number one_ = (Number) _instance;
                result_.setResult(new IntStruct(one_.intValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasLongValue())) {
                Number one_ = (Number) _instance;
                result_.setResult(new LongStruct(one_.longValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasFloatValue())) {
                Number one_ = (Number) _instance;
                result_.setResult(new FloatStruct(one_.floatValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasDoubleValue())) {
                Number one_ = (Number) _instance;
                result_.setResult(new DoubleStruct(one_.doubleValue()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToString()) && list_.isEmpty()) {
                Number one_ = (Number) _instance;
                result_.setResult(new StdStruct(one_.toString(), stringType_));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                Number one_ = (Number) argsObj_[0];
                result_.setResult(new StdStruct(one_.toString(), stringType_));
            }
        } else if (StringList.quickEq(type_, stringType_)) {
            if (StringList.quickEq(name_, lgNames_.getAliasCharAt())) {
                String one_ = (String) _instance;
                Integer two_ = (Integer) argsObj_[0];
                if (two_ < 0 || two_ >= one_.length()) {
                    result_.setError(lgNames_.getAliasBadIndex());
                } else {
                    result_.setResult(new CharStruct(one_.charAt(two_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareTo())) {
                String one_ = (String) _instance;
                String two_ = (String) argsObj_[0];
                if (two_ == null) {
                    result_.setError(lgNames_.getAliasNull());
                } else {
                    result_.setResult(new IntStruct(one_.compareTo(two_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompare())) {
                String one_ = (String) argsObj_[0];
                String two_ = (String) argsObj_[1];
                if (one_ == null || two_ == null) {
                    result_.setError(lgNames_.getAliasNull());
                } else {
                    result_.setResult(new IntStruct(one_.compareTo(two_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasCompareToIgnoreCase())) {
                String one_ = (String) _instance;
                String two_ = (String) argsObj_[0];
                if (two_ == null) {
                    result_.setError(lgNames_.getAliasNull());
                } else {
                    result_.setResult(new IntStruct(one_.compareToIgnoreCase(two_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasContains())) {
                String one_ = (String) _instance;
                CharSequence two_ = (CharSequence) argsObj_[0];
                if (two_ == null) {
                    result_.setError(lgNames_.getAliasNull());
                } else {
                    result_.setResult(new BooleanStruct(one_.contains(two_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasStartsWith())) {
                String one_ = (String) _instance;
                String two_ = (String) argsObj_[0];
                if (two_ == null) {
                    result_.setError(lgNames_.getAliasNull());
                } else if (list_.size() == 1) {
                    result_.setResult(new BooleanStruct(one_.startsWith(two_)));
                } else {
                    Integer three_ = (Integer) argsObj_[1];
                    result_.setResult(new BooleanStruct(one_.startsWith(two_, three_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasEndsWith())) {
                String one_ = (String) _instance;
                String two_ = (String) argsObj_[0];
                if (two_ == null) {
                    result_.setError(lgNames_.getAliasNull());
                } else if (list_.size() == 1) {
                    result_.setResult(new BooleanStruct(one_.endsWith(two_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasEquals())) {
                String one_ = (String) _instance;
                String two_ = (String) argsObj_[0];
                if (two_ == null) {
                    result_.setResult(new BooleanStruct(false));
                } else {
                    result_.setResult(new BooleanStruct(StringList.quickEq(one_, two_)));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasEqualsIgnoreCase())) {
                String one_ = (String) _instance;
                String two_ = (String) argsObj_[0];
                result_.setResult(new BooleanStruct(one_.equalsIgnoreCase(two_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasFormat())) {
                String one_ = (String) _instance;
                String[] two_ = (String[]) argsObj_[0];
                result_.setResult(new StdStruct(StringList.simpleStringsFormat(one_, two_), stringType_));
            } else if (StringList.quickEq(name_, lgNames_.getAliasGetBytes())) {
                String one_ = (String) _instance;
                if (list_.isEmpty()) {
                    byte[] bytes_ = one_.getBytes();
                    Struct[] wrap_ = new Struct[bytes_.length];
                    int i_ = CustList.FIRST_INDEX;
                    for (byte b: bytes_) {
                        wrap_[i_] = new ByteStruct(b);
                        i_++;
                    }
                    String ret_ = PrimitiveTypeUtil.getPrettyArrayType(lgNames_.getAliasPrimByte());
                    result_.setResult(new StdStruct(wrap_, ret_));
                } else {
                    String two_ = (String) argsObj_[0];
                    Charset cset_ = null;
                    boolean do_ = false;
                    try {
                        cset_ = Charset.forName(two_);
                        do_ = true;
                    } catch (Exception _0) {
                    }
                    if (!do_) {
                        result_.setError(lgNames_.getAliasBadEncode());
                    } else {
                        byte[] bytes_ = one_.getBytes(cset_);
                        Struct[] wrap_ = new Struct[bytes_.length];
                        int i_ = CustList.FIRST_INDEX;
                        for (byte b: bytes_) {
                            wrap_[i_] = new ByteStruct(b);
                            i_++;
                        }
                        String ret_ = PrimitiveTypeUtil.getPrettyArrayType(lgNames_.getAliasPrimByte());
                        result_.setResult(new StdStruct(wrap_, ret_));
                    }
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasIndexOf())) {
                String one_ = (String) _instance;
                if (StringList.quickEq(list_.first(), stringType_) && argsObj_[0] == null) {
                    result_.setError(lgNames_.getAliasNull());
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
                String one_ = (String) _instance;
                if (StringList.quickEq(list_.first(), stringType_) && argsObj_[0] == null) {
                    result_.setError(lgNames_.getAliasNull());
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
                String one_ = (String) _instance;
                result_.setResult(new IntStruct(one_.length()));
            } else if (StringList.quickEq(name_, lgNames_.getAliasRegionMatches())) {
                String one_ = (String) _instance;
                if (list_.size() == 4) {
                    Integer two_ = (Integer) argsObj_[0];
                    String three_ = (String) argsObj_[1];
                    Integer four_ = (Integer) argsObj_[2];
                    Integer five_ = (Integer) argsObj_[3];
                    if (three_ == null) {
                        result_.setError(lgNames_.getAliasNull());
                    } else {
                        result_.setResult(new StdStruct(one_.regionMatches(two_, three_, four_, five_), stringType_));
                    }
                } else {
                    Boolean two_ = (Boolean) argsObj_[1];
                    Integer three_ = (Integer) argsObj_[2];
                    String four_ = (String) argsObj_[3];
                    Integer five_ = (Integer) argsObj_[4];
                    Integer six_ = (Integer) argsObj_[5];
                    if (four_ == null) {
                        result_.setError(lgNames_.getAliasNull());
                    } else {
                        result_.setResult(new StdStruct(one_.regionMatches(two_, three_, four_, five_, six_), stringType_));
                    }
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasReplace())) {
                String one_ = (String) _instance;
                if (StringList.quickEq(list_.first(), stringType_)){
                    String two_ = (String) argsObj_[0];
                    String three_ = (String) argsObj_[1];
                    result_.setResult(new StdStruct(StringList.replace(one_, two_, three_), stringType_));
                } else {
                    Character two_ = (Character) argsObj_[0];
                    Character three_ = (Character) argsObj_[1];
                    result_.setResult(new StdStruct(one_.replace(two_, three_), stringType_));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasSplit())) {
                String one_ = (String) _instance;
                StringList res_;
                if (StringList.quickEq(list_.first(), stringType_)){
                    String two_ = (String) argsObj_[0];
                    res_ = StringList.splitStrings(one_, two_);
                } else {
                    Character two_ = (Character) argsObj_[0];
                    res_ = StringList.splitChars(one_, two_);
                }
                Struct[] array_ = new Struct[res_.size()];
                int i_ = CustList.FIRST_INDEX;
                for (String v: res_) {
                    array_[i_] = new StdStruct(v, stringType_);
                }
                result_.setResult(new StdStruct(array_, PrimitiveTypeUtil.getPrettyArrayType(stringType_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasSplits())) {
                String one_ = (String) _instance;
                StringList res_;
                if (StringList.quickEq(list_.first(), stringType_)){
                    String[] two_ = (String[]) argsObj_[0];
                    res_ = StringList.splitStrings(one_, two_);
                } else {
                    Character[] two_ = (Character[]) argsObj_[0];
                    res_ = StringList.splitChars(one_, two_);
                }
                Struct[] array_ = new Struct[res_.size()];
                int i_ = CustList.FIRST_INDEX;
                for (String v: res_) {
                    array_[i_] = new StdStruct(v, stringType_);
                }
                result_.setResult(new StdStruct(array_, PrimitiveTypeUtil.getPrettyArrayType(stringType_)));
            } else if (StringList.quickEq(name_, lgNames_.getAliasSubSequence())) {
                String one_ = (String) _instance;
                Integer two_ = (Integer) argsObj_[0];
                Integer three_ = (Integer) argsObj_[1];
                if (two_ < 0 || three_ < 0 || three_ > one_.length() || two_ > three_) {
                    result_.setError(lgNames_.getAliasBadIndex());
                } else {
                    result_.setResult(new StdStruct(one_.subSequence(two_, three_),stringType_));
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasSubstring())) {
                String one_ = (String) _instance;
                Integer two_ = (Integer) argsObj_[0];
                if (list_.size() == 2) {
                    Integer three_ = (Integer) argsObj_[1];
                    if (two_ < 0 || three_ < 0 || three_ > one_.length() || two_ > three_) {
                        result_.setError(lgNames_.getAliasBadIndex());
                    } else {
                        result_.setResult(new StdStruct(one_.subSequence(two_, three_),stringType_));
                    }
                } else {
                    if (two_ < 0 || two_ > one_.length()) {
                        result_.setError(lgNames_.getAliasBadIndex());
                    } else {
                        result_.setResult(new StdStruct(one_.substring(two_),stringType_));
                    }
                }
            } else if (StringList.quickEq(name_, lgNames_.getAliasToLowerCase())) {
                String one_ = (String) _instance;
                int len_ = one_.length();
                StringBuilder str_ = new StringBuilder(len_);
                for (int i = CustList.FIRST_INDEX; i <len_; i++) {
                    str_.append(Character.toLowerCase(one_.charAt(i)));
                }
                result_.setResult(new StdStruct(str_.toString(),stringType_));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToUpperCase())) {
                String one_ = (String) _instance;
                int len_ = one_.length();
                StringBuilder str_ = new StringBuilder(len_);
                for (int i = CustList.FIRST_INDEX; i <len_; i++) {
                    str_.append(Character.toUpperCase(one_.charAt(i)));
                }
                result_.setResult(new StdStruct(str_.toString(),stringType_));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToCharArray())) {
                String one_ = (String) _instance;
                char[] bytes_ = one_.toCharArray();
                Struct[] wrap_ = new Struct[bytes_.length];
                int i_ = CustList.FIRST_INDEX;
                for (char b: bytes_) {
                    wrap_[i_] = new CharStruct(b);
                    i_++;
                }
                String ret_ = PrimitiveTypeUtil.getPrettyArrayType(lgNames_.getAliasPrimChar());
                result_.setResult(new StdStruct(wrap_, ret_));
            } else if (StringList.quickEq(name_, lgNames_.getAliasToString())) {
                String one_ = (String) _instance;
                result_.setResult(new StdStruct(one_, stringType_));
            } else if (StringList.quickEq(name_, lgNames_.getAliasTrim())) {
                String one_ = (String) _instance;
                result_.setResult(new StdStruct(one_.trim(), stringType_));
            } else if (StringList.quickEq(name_, lgNames_.getAliasValueOf())) {
                if (StringList.quickEq(list_.first(), PrimitiveTypeUtil.getPrettyArrayType(lgNames_.getAliasPrimChar())) && list_.size() == 1) {
                    char[] chars_ = (char[]) argsObj_[0];
                    result_.setResult(new StdStruct(String.valueOf(chars_), stringType_));
                } else if (StringList.quickEq(list_.first(), PrimitiveTypeUtil.getPrettyArrayType(lgNames_.getAliasPrimChar()))) {
                    char[] chars_ = (char[]) argsObj_[0];
                    Integer two_ = (Integer) argsObj_[1];
                    Integer three_ = (Integer) argsObj_[2];
                    if (two_ < 0 || three_ < 0 || two_ + three_ > chars_.length) {
                        result_.setError(lgNames_.getAliasBadIndex());
                    } else {
                        result_.setResult(new StdStruct(String.valueOf(chars_, two_, three_), stringType_));
                    }
                } else {
                    Object obj_ = argsObj_[0];
                    result_.setResult(new StdStruct(String.valueOf(obj_), stringType_));
                }
            }
        } else {
            result_ = lgNames_.getOtherResult(_cont, _instance, _method, argsObj_);
        }
        return result_;
    }
    public static boolean isOkLongTen(String _string) {
        if (_string == null) {
            return false;
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
                digit_ = Character.digit(ch_,10);
                if (digit_ < 0) {
                    return false;
                } else {
                    result_ = -digit_;
                }
            }
            while (i_ < max_) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                char ch_ = _string.charAt(i_);
                i_++;
                digit_ = Character.digit(ch_,10);
                if (digit_ < 0) {
                    return false;
                }
                if (result_ < multmin_) {
                    return false;
                }
                result_ *= 10;
                if (result_ < limit_ + digit_) {
                    return false;
                }
                result_ -= digit_;
            }
        } else {
            return false;
        }
        if (negative_) {
            if (i_ > 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
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
            if (_radix == 10) {
                if (negative_) {
                    multmin_ = MULTMIN_RADIX_TEN;
                } else {
                    multmin_ = N_MULTMAX_RADIX_TEN;
                }
            } else {
                multmin_ = limit_ / _radix;
            }
            if (i_ < max_) {
                char ch_ = _string.charAt(i_);
                i_++;
                digit_ = Character.digit(ch_,_radix);
                if (digit_ < 0) {
                    return null;
                } else {
                    result_ = -digit_;
                }
            }
            while (i_ < max_) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                char ch_ = _string.charAt(i_);
                i_++;
                digit_ = Character.digit(ch_,_radix);
                if (digit_ < 0) {
                    return null;
                }
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
    public ResultErrorStd getOtherResult(ContextEl _cont, Object _instance, ClassMethodId _method, Object... _args) {
        return new ResultErrorStd();
    }
    static ResultErrorStd newInstance(ContextEl _cont, boolean _natvararg, ConstructorId _method, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = getObjects(_args);
        String type_ = _method.getName();
        String name_ = _method.getName();
        StringList list_ = _method.getParametersTypes();
        checkArgumentsForInvoking(_cont, _natvararg, list_, args_);
        LgNames lgNames_ = _cont.getStandards();
        Object[] argsObj_ = adaptedArgs(list_, _cont, _cont.getStandards(), args_);
        String booleanType_ = lgNames_.getAliasBoolean();
        String charType_ = lgNames_.getAliasBoolean();
        String booleanPrimType_ = lgNames_.getAliasPrimBoolean();
        return lgNames_.getOtherResult(_cont, _method, argsObj_);
    }
    public ResultErrorStd getOtherResult(ContextEl _cont, ConstructorId _method, Object... _args) {
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
                traces_.add(i+RETURN_LINE+_params.get(i)+RETURN_LINE+null);
            }
        }
        if (!traces_.isEmpty()) {
            throw new UnwrappingException(traces_.join(SEP_ARG)+RETURN_LINE+_cont.joinPages());
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
            Object a_ = argStruct_.getInstance();
            if (a_ instanceof Struct[]) {
                Struct[] str_ = (Struct[]) a_;
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
    public StringMap<StandardType> getStandards() {
        return standards;
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
    public String getAliasAppendable() {
        return aliasAppendable;
    }
    public void setAliasAppendable(String _aliasAppendable) {
        aliasAppendable = _aliasAppendable;
    }
    public String getAliasCharSequence() {
        return aliasCharSequence;
    }
    public void setAliasCharSequence(String _aliasCharSequence) {
        aliasCharSequence = _aliasCharSequence;
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
    public String getAliasNull() {
        return aliasNull;
    }
    public void setAliasNull(String _aliasNull) {
        aliasNull = _aliasNull;
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
    public String getAliasPrimBoolean() {
        return aliasPrimBoolean;
    }
    public void setAliasPrimBoolean(String _aliasPrimBoolean) {
        aliasPrimBoolean = _aliasPrimBoolean;
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
    public String getAliasSplits() {
        return aliasSplits;
    }
    public void setAliasSplits(String _aliasSplits) {
        aliasSplits = _aliasSplits;
    }
    public String getAliasReplace() {
        return aliasReplace;
    }
    public void setAliasReplace(String _aliasReplace) {
        aliasReplace = _aliasReplace;
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
    public void setStandards(StringMap<StandardType> _standards) {
        standards = _standards;
    }
    public StandardMethod getMethodBodiesById(String _baseSuperType,
            MethodId _constraints) {
        return standards.getVal(_baseSuperType).getMethods().getVal(_constraints);
    }
    private CustList<StandardConstructor> getConstructorBodiesById(
            String _clCurName, ConstructorId _m) {
        for (StandardConstructor s: standards.getVal(_clCurName).getConstructors()) {
            if (s.getId(_clCurName).eq(_m)) {
                return new CustList<StandardConstructor>(s);
            }
        }
        return new CustList<StandardConstructor>();
    }
}
