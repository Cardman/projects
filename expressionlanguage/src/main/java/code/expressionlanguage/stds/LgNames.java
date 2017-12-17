package code.expressionlanguage.stds;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.AssignableFrom;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.DimComp;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public class LgNames {
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

    private String aliasIterator;
    private String aliasIterable;
    private String aliasEnumParam;
    private String aliasEnum;
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

    private StringMap<StandardType> standards = new StringMap<StandardType>();

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
        params_ = new StringList(aliasString, aliasString);
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
        _methods.put(new MethodId(MethodModifier.STATIC, aliasCompareTo, params_), method_);
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
        StringList types_ = new StringList();
        for (String i: _classNames) {
            boolean sub_ = true;
            if (StringList.quickEq(i, OperationNode.VOID_RETURN)) {
                for (String j: _classNames) {
                    if (!StringList.quickEq(i, j)) {
                        sub_ = false;
                        break;
                    }
                }
            } else {
                for (String j: _classNames) {
                    String baseSup_ = StringList.getAllTypes(i).first();
                    String baseSub_ = StringList.getAllTypes(j).first();
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
    public void setStandards(StringMap<StandardType> _standards) {
        standards = _standards;
    }
    public StandardMethod getMethodBodiesById(String _baseSuperType,
            MethodId _constraints) {
        return standards.getVal(_baseSuperType).getMethods().getVal(_constraints);
    }
}
