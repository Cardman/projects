package code.expressionlanguage.stds;

import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class AliasNumber {
    private String aliasCompareTo;
    private String aliasCompare;
    private String aliasEquals;
    private String aliasToString;
    private String aliasValueOf;
    private String aliasMaxValueField;
    private String aliasMinValueField;
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
    private String aliasGetCharType;
    public void build(LgNames _lgNames) {
        StringMap<StandardField> fields_;
        StringList params_;
        StandardMethod method_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        StandardType std_;
        StandardClass stdcl_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        String aliasObject_ = _lgNames.getAliasObject();
        String aliasPrimBoolean_ = _lgNames.getAliasPrimBoolean();
        String aliasPrimByte_ = _lgNames.getAliasPrimByte();
        String aliasPrimShort_ = _lgNames.getAliasPrimShort();
        String aliasPrimChar_ = _lgNames.getAliasPrimChar();
        String aliasPrimInteger_ = _lgNames.getAliasPrimInteger();
        String aliasPrimLong_ = _lgNames.getAliasPrimLong();
        String aliasPrimFloat_ = _lgNames.getAliasPrimFloat();
        String aliasPrimDouble_ = _lgNames.getAliasPrimDouble();
        StringMap<StandardType> standards = _lgNames.getStandards();
        std_ = new StandardClass(aliasBoolean, fields_, constructors_, methods_, aliasObject_ , MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasBooleanValue, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean_, aliasPrimBoolean_);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger_, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasBoolean);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasBoolean);
        method_ = new StandardMethod(aliasEquals, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_lgNames.getAliasString());
        method_ = new StandardMethod(aliasParseBoolean, params_, aliasPrimBoolean_, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToString, params_, _lgNames.getAliasString(), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasToString, params_, _lgNames.getAliasString(), false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasValueOf, params_, aliasBoolean, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_lgNames.getAliasString());
        method_ = new StandardMethod(aliasValueOf, params_, aliasBoolean, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        StandardConstructor ctor_;
        params_ = new StringList(_lgNames.getAliasString());
        ctor_ = new StandardConstructor(params_,false,std_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasBoolean);
        ctor_ = new StandardConstructor(params_,false, std_);
        constructors_.add(ctor_);
        standards.put(aliasBoolean, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        std_ = new StandardClass(aliasByte, fields_, constructors_, methods_, aliasShort, MethodModifier.FINAL);
        numbersConstructors(_lgNames,constructors_, aliasPrimByte_, std_);
        numbersValuesMethods(_lgNames,methods_, aliasByte, aliasParseByte, aliasPrimByte_, std_);
        numbersValuesFields(fields_, aliasPrimByte_, std_);
        standards.put(aliasByte, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        std_ = new StandardClass(aliasShort, fields_, constructors_, methods_, aliasInteger, MethodModifier.FINAL);
        numbersConstructors(_lgNames,constructors_, aliasPrimShort_, std_);
        numbersValuesMethods(_lgNames,methods_, aliasShort, aliasParseShort, aliasPrimShort_, std_);
        numbersValuesFields(fields_, aliasPrimShort_, std_);
        standards.put(aliasShort, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        std_ = new StandardClass(aliasInteger, fields_, constructors_, methods_, aliasLong, MethodModifier.FINAL);
        numbersConstructors(_lgNames,constructors_, aliasPrimInteger_, std_);
        numbersValuesMethods(_lgNames,methods_, aliasInteger, aliasParseInt, aliasPrimInteger_, std_);
        numbersValuesFields(fields_, aliasPrimInteger_, std_);
        standards.put(aliasInteger, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        std_ = new StandardClass(aliasLong, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL);
        numbersConstructors(_lgNames,constructors_, aliasPrimLong_, std_);
        numbersValuesMethods(_lgNames,methods_, aliasLong, aliasParseLong, aliasPrimLong_, std_);
        numbersValuesFields(fields_, aliasPrimLong_, std_);
        standards.put(aliasLong, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        std_ = new StandardClass(aliasFloat, fields_, constructors_, methods_, aliasDouble, MethodModifier.FINAL);
        numbersConstructors(_lgNames,constructors_, aliasPrimFloat_, std_);
        numbersValuesMethods(_lgNames,methods_, aliasFloat, aliasParseFloat, aliasPrimFloat_, std_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat_);
        method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean_, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsNan, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat_);
        method_ = new StandardMethod(aliasIsNan, params_, aliasPrimBoolean_, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        numbersValuesFields(fields_, aliasPrimFloat_, std_);
        standards.put(aliasFloat, std_);
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        std_ = new StandardClass(aliasDouble, fields_, constructors_, methods_, aliasNumber, MethodModifier.FINAL);
        numbersConstructors(_lgNames,constructors_, aliasPrimDouble_, std_);
        numbersValuesMethods(_lgNames,methods_, aliasDouble, aliasParseDouble, aliasPrimDouble_, std_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasIsInfinite, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsNan, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasIsNan, params_, aliasPrimBoolean_, false, MethodModifier.STATIC,std_);
        methods_.put(method_.getId(), method_);
        numbersValuesFields(fields_, aliasPrimDouble_, std_);
        standards.put(aliasDouble, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        std_ = new StandardClass(aliasNumber, fields_, constructors_, methods_, aliasObject_, MethodModifier.ABSTRACT);
        numbersAbsMethods(_lgNames,methods_, aliasNumber,std_);
        standards.put(aliasNumber, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        stdcl_ = new StandardClass(aliasCharacter, fields_, constructors_, methods_, aliasInteger, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCharValue, params_, aliasPrimChar_, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharacter);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_, aliasPrimChar_);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasDigit, params_, aliasPrimInteger_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasForDigit, params_, aliasPrimChar_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasGetDirectionality, params_, aliasPrimByte_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasGetCharType, params_, aliasPrimByte_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsDigit, params_, aliasPrimBoolean_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsLetter, params_, aliasPrimBoolean_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsLetterOrDigit, params_, aliasPrimBoolean_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsWordChar, params_, aliasPrimBoolean_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsWhitespace, params_, aliasPrimBoolean_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsLowerCase, params_, aliasPrimBoolean_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsUpperCase, params_, aliasPrimBoolean_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasIsSpace, params_, aliasPrimBoolean_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(_lgNames.getAliasToLowerCase(), params_, aliasPrimChar_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(_lgNames.getAliasToUpperCase(), params_, aliasPrimChar_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasToString, params_, _lgNames.getAliasString(), false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToString, params_, _lgNames.getAliasString(), false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        ctor_ = new StandardConstructor(params_, false, stdcl_);
        constructors_.add(ctor_);
        numbersValuesFields(fields_, aliasPrimChar_, stdcl_);
        std_ = stdcl_;
        standards.put(aliasCharacter, std_);
    }
    private void numbersConstructors(LgNames _lgNames,CustList<StandardConstructor> _ctors, String _primitive, StandardType _type) {
        StringList params_;
        StandardConstructor ctor_;
        params_ = new StringList(_lgNames.getAliasString());
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
    private void numbersValuesMethods(LgNames _lgNames,ObjectMap<MethodId, StandardMethod> _methods, String _owner, String _parserName, String _primitive, StandardType _type) {
        String aliasPrimInteger_ = _lgNames.getAliasPrimInteger();
        StringList params_;
        StandardMethod method_;
        params_ = new StringList(_primitive);
        method_ = new StandardMethod(aliasToString, params_, _lgNames.getAliasString(), false, MethodModifier.STATIC, _type);
        _methods.put(new MethodId(MethodModifier.STATIC, aliasToString, params_), method_);
        params_ = new StringList(_lgNames.getAliasString());
        method_ = new StandardMethod(_parserName, params_, _owner, false, MethodModifier.STATIC, _type);
        _methods.put(new MethodId(MethodModifier.STATIC, _parserName, params_), method_);
        params_ = new StringList(_lgNames.getAliasString(), aliasPrimInteger_);
        method_ = new StandardMethod(_parserName, params_, _owner, false, MethodModifier.STATIC, _type);
        _methods.put(new MethodId(MethodModifier.STATIC, _parserName, params_), method_);
        params_ = new StringList(_owner);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasCompareTo, params_), method_);
        params_ = new StringList(_primitive, _primitive);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger_, false, MethodModifier.STATIC, _type);
        _methods.put(new MethodId(MethodModifier.STATIC, aliasCompare, params_), method_);
    }
    private void numbersAbsMethods(LgNames _lgNames,ObjectMap<MethodId, StandardMethod> _methods, String _owner, StandardType _type) {
        String aliasPrimBoolean_ = _lgNames.getAliasPrimBoolean();
        String aliasPrimByte_ = _lgNames.getAliasPrimByte();
        String aliasPrimShort_ = _lgNames.getAliasPrimShort();
        String aliasPrimInteger_ = _lgNames.getAliasPrimInteger();
        String aliasPrimLong_ = _lgNames.getAliasPrimLong();
        String aliasPrimFloat_ = _lgNames.getAliasPrimFloat();
        String aliasPrimDouble_ = _lgNames.getAliasPrimDouble();
        StringList params_;
        StandardMethod method_;
        params_ = new StringList();
        method_ = new StandardMethod(aliasByteValue, params_, aliasPrimByte_, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.ABSTRACT, aliasByteValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasShortValue, params_, aliasPrimShort_, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.ABSTRACT, aliasShortValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIntValue, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.ABSTRACT, aliasIntValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasLongValue, params_, aliasPrimLong_, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.ABSTRACT, aliasLongValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasFloatValue, params_, aliasPrimFloat_, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.ABSTRACT, aliasFloatValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasDoubleValue, params_, aliasPrimDouble_, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.ABSTRACT, aliasDoubleValue, params_), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToString, params_, _lgNames.getAliasString(), false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasToString, params_), method_);
        params_ = new StringList(_owner);
        method_ = new StandardMethod(aliasToString, params_, _lgNames.getAliasString(), false, MethodModifier.STATIC, _type);
        _methods.put(new MethodId(MethodModifier.STATIC, aliasToString, params_), method_);
        params_ = new StringList(aliasNumber);
        method_ = new StandardMethod(aliasEquals, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasEquals, params_), method_);
        params_ = new StringList(_owner);
        method_ = new StandardMethod(aliasCompareTo, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, _type);
        _methods.put(new MethodId(MethodModifier.NORMAL, aliasCompareTo, params_), method_);
        params_ = new StringList(_owner, _owner);
        method_ = new StandardMethod(aliasCompare, params_, aliasPrimInteger_, false, MethodModifier.STATIC, _type);
        _methods.put(new MethodId(MethodModifier.STATIC, aliasCompareTo, params_), method_);
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
    public String getAliasGetCharType() {
        return aliasGetCharType;
    }
    public void setAliasGetCharType(String _aliasGetType) {
        aliasGetCharType = _aliasGetType;
    }
    
}
