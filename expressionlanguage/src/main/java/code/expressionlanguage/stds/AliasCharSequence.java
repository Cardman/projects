package code.expressionlanguage.stds;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.CharSequenceStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.ReplacementStruct;
import code.expressionlanguage.structs.StringBuilderStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class AliasCharSequence {

    private String aliasCharSequence;
    private String aliasString;
    private String aliasLength;
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

    private String aliasReplacement;
    private String aliasGetOldString;
    private String aliasGetNewString;
    private String aliasSetOldString;
    private String aliasSetNewString;

    public void build(LgNames _lgNames) {
        StringMap<StandardType> standards_ = _lgNames.getStandards();
        StringList params_;
        StringList noTypes_ = new StringList();
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        CustList<StandardConstructor> constructors_;
        StringMap<StandardField> fields_;
        StandardMethod method_;
        StandardConstructor ctor_;
        StandardType std_;
        std_ = new StandardInterface(aliasCharSequence, methods_, noTypes_);
        String aliasPrimBoolean_ = _lgNames.getAliasPrimBoolean();
        String aliasPrimDouble_ = _lgNames.getAliasPrimDouble();
        String aliasPrimFloat_ = _lgNames.getAliasPrimFloat();
        String aliasPrimLong_ = _lgNames.getAliasPrimLong();
        String aliasPrimInteger_ = _lgNames.getAliasPrimInteger();
        String aliasPrimChar_ = _lgNames.getAliasPrimChar();
        String aliasPrimShort_ = _lgNames.getAliasPrimShort();
        String aliasPrimByte_ = _lgNames.getAliasPrimByte();
        String aliasVoid_ = _lgNames.getAliasVoid();
        String aliasToString_ = _lgNames.getAliasToString();
        String aliasCompareTo_ = _lgNames.getAliasCompareTo();
        String aliasObject_ = _lgNames.getAliasObject();
        String aliasValueOf_ = _lgNames.getAliasValueOf();
        params_ = new StringList(aliasPrimInteger_ );
        method_ = new StandardMethod(aliasCharAt, params_, aliasPrimChar_, false, MethodModifier.ABSTRACT,std_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasLength, noTypes_, aliasPrimInteger_, false, MethodModifier.ABSTRACT,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasSubSequence, params_, aliasString, false, MethodModifier.ABSTRACT,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasCharAt, params_, aliasPrimChar_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        method_ = new StandardMethod(aliasLength, noTypes_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasSubstring, params_, aliasString, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasSubstring, params_, aliasString, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasCompareTo_, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasContains, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasStartsWith, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence, aliasPrimInteger_);
        method_ = new StandardMethod(aliasStartsWith, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasEndsWith, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence, aliasPrimInteger_);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence, aliasPrimInteger_);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasLastIndexOf, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasIsEmpty, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToCharArray, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar_), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetBytes, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasPrimByte_), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasFormat, params_, aliasString, true, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasSplit, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence, aliasPrimInteger_);
        method_ = new StandardMethod(aliasSplit, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasSplit, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasSplit, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasCharSequence);
        method_ = new StandardMethod(aliasSplitStrings, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), true, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasCharSequence);
        method_ = new StandardMethod(aliasSplitStrings, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), true, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasSplitChars, params_, PrimitiveTypeUtil.getPrettyArrayType(aliasString), true, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasCharSequence, aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasRegionMatches, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTrim, params_, aliasString, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToString_, params_, aliasString, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        standards_.put(aliasCharSequence, std_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        std_ = new StandardClass(aliasString, fields_, constructors_, methods_, aliasObject_, MethodModifier.FINAL);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasEqualsIgnoreCase, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString, aliasString);
        method_ = new StandardMethod(_lgNames.getAliasCompare(), params_, aliasPrimInteger_, false, MethodModifier.STATIC, std_);
        methods_.put(new MethodId(MethodModifier.STATIC, _lgNames.getAliasCompare(), params_), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasCompareToIgnoreCase, params_, aliasPrimInteger_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString, aliasString);
        method_ = new StandardMethod(aliasReplace, params_, aliasString, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_, aliasPrimChar_);
        method_ = new StandardMethod(aliasReplace, params_, aliasString, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasReplacement);
        method_ = new StandardMethod(aliasReplaceMultiple, params_, aliasString, true, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean_, aliasPrimInteger_, aliasString, aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasRegionMatches, params_, aliasPrimBoolean_, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToLowerCase, params_, aliasString, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToUpperCase, params_, aliasString, false, MethodModifier.NORMAL, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimByte_);
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimShort_);
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat_);
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar_));
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar_),aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasValueOf_, params_, aliasString, false, MethodModifier.STATIC, std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_, false, std_);
        constructors_.add(ctor_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimByte_));
        ctor_ = new StandardConstructor(params_, false, std_);
        constructors_.add(ctor_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimByte_), aliasPrimInteger_, aliasPrimInteger_);
        ctor_ = new StandardConstructor(params_, false, std_);
        constructors_.add(ctor_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar_));
        ctor_ = new StandardConstructor(params_, false, std_);
        constructors_.add(ctor_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar_), aliasPrimInteger_, aliasPrimInteger_);
        ctor_ = new StandardConstructor(params_, false, std_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasStringBuilder);
        ctor_ = new StandardConstructor(params_, false, std_);
        constructors_.add(ctor_);
        std_.getDirectInterfaces().add(aliasCharSequence);
        standards_.put(aliasString, std_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass(aliasStringBuilder, fields_, constructors_, methods_, aliasObject_, MethodModifier.FINAL);
        params_ = new StringList(aliasPrimBoolean_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimByte_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimShort_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimChar_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimLong_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimFloat_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimDouble_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString,aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasStringBuilder);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasStringBuilder,aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar_));
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar_),aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasAppend, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCapacity, params_, aliasPrimInteger_, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasDelete, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasDeleteCharAt, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasClear, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimBoolean_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimByte_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimShort_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimChar_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimInteger_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimLong_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimFloat_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasPrimDouble_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasString);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasString,aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasStringBuilder);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, aliasStringBuilder,aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar_));
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_, PrimitiveTypeUtil.getPrettyArrayType(aliasPrimChar_),aliasPrimInteger_,aliasPrimInteger_);
        method_ = new StandardMethod(aliasInsert, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasReverse, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimInteger_,aliasCharSequence);
        method_ = new StandardMethod(aliasReplace, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_,aliasPrimChar_);
        method_ = new StandardMethod(aliasSetCharAt, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasSetLength, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasTrimToSize, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasPrimInteger_);
        method_ = new StandardMethod(aliasEnsureCapacity, params_, aliasStringBuilder, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_, false, std_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasStringBuilder);
        ctor_ = new StandardConstructor(params_, false, std_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasPrimInteger_);
        ctor_ = new StandardConstructor(params_, false, std_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasString);
        ctor_ = new StandardConstructor(params_, false, std_);
        constructors_.add(ctor_);
        std_.getDirectInterfaces().add(aliasCharSequence);
        standards_.put(aliasStringBuilder, std_);

        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass(aliasReplacement, fields_, constructors_, methods_, aliasObject_, MethodModifier.FINAL);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetOldString, params_, aliasString, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasGetNewString, params_, aliasString, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasSetOldString, params_, aliasVoid_, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasString);
        method_ = new StandardMethod(aliasSetNewString, params_, aliasVoid_, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        ctor_ = new StandardConstructor(params_, false, std_);
        constructors_.add(ctor_);
        params_ = new StringList(aliasCharSequence,aliasCharSequence);
        ctor_ = new StandardConstructor(params_, false, std_);
        constructors_.add(ctor_);
        standards_.put(aliasReplacement, std_);
    }

    public static ResultErrorStd invokeMethod(ContextEl _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        ResultErrorStd result_;
        result_ = invokeStdMethod(_cont, _method, _struct, _args);
        if (result_.getResult() != null) {
            return result_;
        }
        if (result_.getError() != null) {
            String errMessage_ = result_.getErrorMessage();
            if (errMessage_ != null) {
                _cont.setException(new ErrorStruct(_cont,errMessage_,result_.getError()));
            } else {
                _cont.setException(new ErrorStruct(_cont,result_.getError()));
            }
            return result_;
        }
        String type_ = _method.getClassName();
        LgNames lgNames_ = _cont.getStandards();
        String stringBuilderType_ = lgNames_.getAliasStringBuilder();
        String replType_ = lgNames_.getAliasReplacement();
        Struct[] args_ = LgNames.getObjects(_args);
        if (StringList.quickEq(type_, replType_)) {
            ReplacementStruct.calculate(_cont, result_, _method, _struct, args_);
            return result_;
        }
        if (StringList.quickEq(type_, stringBuilderType_)) {
            StringBuilderStruct.calculate(_cont, result_, _method, _struct, args_);
        }
        return result_;
    }

    static ResultErrorStd invokeStdMethod(Analyzable _cont, ClassMethodId _method, Struct _struct, Argument... _args) {
        ResultErrorStd result_ = new ResultErrorStd();
        Struct[] args_ = LgNames.getObjects(_args);
        LgNames lgNames_ = _cont.getStandards();
        String type_ = _method.getClassName();
        String stringType_ = lgNames_.getAliasString();
        String charSequenceType_ = lgNames_.getAliasCharSequence();
        if (StringList.quickEq(type_, stringType_)) {
            StringStruct.calculate(_cont, result_, _method, _struct, args_);
            return result_;
        }
        if (StringList.quickEq(type_, charSequenceType_)) {
            CharSequenceStruct.calculate(_cont, result_, _method, _struct, args_);
            return result_;
        }
        return result_;
    }

    public String getAliasCharSequence() {
        return aliasCharSequence;
    }

    public void setAliasCharSequence(String _aliasCharSequence) {
        aliasCharSequence = _aliasCharSequence;
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

}
