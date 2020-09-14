package code.expressionlanguage;

import code.expressionlanguage.classes.CustLgNames;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.exec.DefaultInitializer;
import code.expressionlanguage.exec.DefaultLockingClass;
import code.expressionlanguage.exec.Initializer;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.KeyWordsMap;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.StringList;
import org.junit.Assert;

public final class InitializationLgNames {

    public static ContextEl buildStdOne(Options _opt) {
        LgNames lgName_ = new CustLgNames();
        basicStandards(lgName_);
        return build(CustList.INDEX_NOT_FOUND_ELT,lgName_, _opt);
    }
    public static ContextEl buildStdEnums(Options _opt) {
        LgNames lgName_ = new CustLgNames();
        basicStandards(lgName_);
        lgName_.setAliasEnumName("name");
        lgName_.setAliasEnumOrdinal("ordinal");
        return build(CustList.INDEX_NOT_FOUND_ELT,lgName_, _opt);
    }
    public static ContextEl buildStdToString(Options _opt) {
        LgNames lgName_ = new CustLgNames();
        basicStandards(lgName_);
        return buildToString(CustList.INDEX_NOT_FOUND_ELT,lgName_, _opt);
    }
    public static ContextEl buildStdExp(Options _opt) {
        LgNames lgName_ = new CustLgNames();
        basicStandards(lgName_);
        return builExp(lgName_, _opt);
    }
    public static ContextEl buildStdOne(String _lg,Options _opt) {
        LgNames lgName_ = new CustLgNames();
        basicStandards(lgName_);
        return buildLg(_lg, lgName_, _opt);
    }
    public static ContextEl buildStdOne(int _stack,Options _opt) {
        LgNames lgName_ = new CustLgNames();
        basicStandards(lgName_);
        return build(_stack,lgName_, _opt);
    }
    public static ContextEl build(int _stack, LgNames _lgNames, Options _opt) {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        ContextEl out_ = ContextFactory.build(_stack,lk_, di_, _opt, a_, kw_, _lgNames,4);
        Assert.assertTrue(out_.getAnalyzing().isEmptyStdError());
        return out_;
    }
    public static ContextEl buildToString(int _stack, LgNames _lgNames, Options _opt) {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        kw_.setKeyWordToString("toSpecString");
        ContextEl out_ = ContextFactory.build(_stack,lk_, di_, _opt, a_, kw_, _lgNames,4);
        Assert.assertTrue(out_.getAnalyzing().isEmptyStdError());
        return out_;
    }
    private static ContextEl buildLg(String _lang, LgNames _lgNames, Options _opt) {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        ContextEl out_ = buildDefKw(_lang, lk_, di_, _opt, _lgNames,4);
        Assert.assertTrue(out_.getAnalyzing().isEmptyStdError());
        return out_;
    }
    public static ContextEl builExp(LgNames _lgNames, Options _opt) {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        kw_.setKeyWordNbExpBin("power");
        kw_.setKeyWordNbExpDec("exp");
        ContextEl out_ = ContextFactory.build(CustList.INDEX_NOT_FOUND_ELT,lk_, di_, _opt, a_, kw_, _lgNames,4);
        Assert.assertTrue(out_.getAnalyzing().isEmptyStdError());
        return out_;
    }
    public static void basicStandards(LgNames _lgNames) {
        _lgNames.setDefaultPkg("java.lang");
        _lgNames.setAliasObject("java.lang.Object");
        _lgNames.setAliasVoid("$void");
        _lgNames.setAliasCharSequence("java.lang.CharSequence");
        _lgNames.setAliasCompareTo("compareTo");
        _lgNames.setAliasCompare("compare");
        _lgNames.setAliasEquals("equals");
        _lgNames.setAliasToStringMethod("toString");
        _lgNames.setAliasValueOfMethod("valueOf");
        _lgNames.setAliasMaxValueField("MAX_VALUE");
        _lgNames.setAliasMinValueField("MIN_VALUE");
        _lgNames.setAliasPlusInfinityField("PLUS_INFINITY");
        _lgNames.setAliasMinusInfinityField("MINUS_INFINITY");
        _lgNames.setAliasNanField("NAN");
        _lgNames.setAliasIteratorType("java.lang.$iterator");
        _lgNames.setAliasIterator("iterator");
        _lgNames.setAliasIterable("java.lang.$iterable");
        _lgNames.setAliasEnumParam("java.lang.$Enum");
        _lgNames.setAliasEnumType("java.lang.$en");
        _lgNames.setAliasEnums("java.lang.$enums");
        _lgNames.setAliasReplacement("code.util.Replacement");
//        _lgNames.setAliasStore("$badStore");
        _lgNames.setAliasStore("code.expressionlanguage.exceptions.DynamicArrayStoreException");
//        _lgNames.setAliasNullPe("$npe");
        _lgNames.setAliasNullPe("code.util.exceptions.NullObjectException");
        _lgNames.setAliasBadEncode("java.lang.$enc");
//        _lgNames.setAliasBadIndex("$badIndex");
        _lgNames.setAliasBadIndex("code.expressionlanguage.exceptions.BadIndexException");
        _lgNames.setAliasIllegalArg("code.expressionlanguage.exceptions.IllegalArgument");
//        _lgNames.setAliasBadSize("$badSize");
        _lgNames.setAliasBadSize("code.expressionlanguage.exceptions.NegativeSizeException");
//        _lgNames.setAliasError("$error");
        _lgNames.setAliasError("java.lang.Exception");
        _lgNames.setAliasGetMessage("getMessage");
        _lgNames.setAliasCastType("code.expressionlanguage.exceptions.DynamicCastClassException");
//        _lgNames.setAliasDivisionZero("$divZero");
        _lgNames.setAliasDivisionZero("code.expressionlanguage.exceptions.DivideZeroException");
        //_lgNames.setAliasSof("$sofe");
        _lgNames.setAliasMath("java.lang.$math");
        _lgNames.setAliasAbs("abs");
        _lgNames.setAliasMod("mod");
        _lgNames.setAliasQuot("quot");
        _lgNames.setAliasSof("code.expressionlanguage.exceptions.StackOverFlow");
        _lgNames.setAliasNbFormat("java.lang.badFormat");
        _lgNames.setAliasPrimBoolean("$boolean");
        _lgNames.setAliasPrimByte("$byte");
        _lgNames.setAliasPrimShort("$short");
        _lgNames.setAliasPrimChar("$char");
        _lgNames.setAliasPrimInteger("$int");
        _lgNames.setAliasPrimLong("$long");
        _lgNames.setAliasPrimFloat("$float");
        _lgNames.setAliasPrimDouble("$double");
        _lgNames.setAliasBoolean("java.lang.Boolean");
        _lgNames.setAliasByte("java.lang.Byte");
        _lgNames.setAliasShort("java.lang.Short");
        _lgNames.setAliasCharacter("java.lang.Character");
        _lgNames.setAliasInteger("java.lang.Integer");
        _lgNames.setAliasLong("java.lang.Long");
        _lgNames.setAliasFloat("java.lang.Float");
        _lgNames.setAliasDouble("java.lang.Double");
        _lgNames.setAliasNumber("java.lang.Number");
        _lgNames.setAliasParseBoolean("parseBoolean");
        _lgNames.setAliasParseByte("parseByte");
        _lgNames.setAliasParseShort("parseShort");
        _lgNames.setAliasParseInt("parseInt");
        _lgNames.setAliasParseLong("parseLong");
        _lgNames.setAliasParseFloat("parseFloat");
        _lgNames.setAliasParseDouble("parseDouble");
        _lgNames.setAliasParseByteOrNull("parseByteOrNull");
        _lgNames.setAliasParseShortOrNull("parseShortOrNull");
        _lgNames.setAliasParseIntOrNull("parseIntOrNull");
        _lgNames.setAliasParseLongOrNull("parseLongOrNull");
        _lgNames.setAliasParseFloatOrNull("parseFloatOrNull");
        _lgNames.setAliasParseDoubleOrNull("parseDoubleOrNull");
        _lgNames.setAliasBooleanValue("booleanValue");
        _lgNames.setAliasByteValue("byteValue");
        _lgNames.setAliasShortValue("shortValue");
        _lgNames.setAliasCharValue("charValue");
        _lgNames.setAliasIntValue("intValue");
        _lgNames.setAliasLongValue("longValue");
        _lgNames.setAliasFloatValue("floatValue");
        _lgNames.setAliasDoubleValue("doubleValue");
        _lgNames.setAliasDigit("digit");
        _lgNames.setAliasIsDigit("isDigit");
        _lgNames.setAliasIsLetter("isLetter");
        _lgNames.setAliasIsLetterOrDigit("isLetterOrDigit");
        _lgNames.setAliasIsWordChar("isWordChar");
        _lgNames.setAliasIsLowerCase("isLowerCase");
        _lgNames.setAliasIsUpperCase("isUpperCase");
        _lgNames.setAliasIsWhitespace("isWhitespace");
        _lgNames.setAliasIsSpace("isSpace");
        _lgNames.setAliasIsInfinite("isInfinite");
        _lgNames.setAliasIsNan("isNan");
        _lgNames.setAliasForDigit("forDigit");
        _lgNames.setAliasGetDirectionality("getDirectionality");
        _lgNames.setAliasGetType("getType");
        _lgNames.setAliasGetCharType("getType");
        _lgNames.setAliasString("java.lang.String");
        _lgNames.setAliasLength("length");
        _lgNames.setAliasCharAt("charAt");
        _lgNames.setAliasToCharArray("toCharArray");
        _lgNames.setAliasSplit("split");
        _lgNames.setAliasSplitChars("splitChars");
        _lgNames.setAliasSplitStrings("splitStrings");
        _lgNames.setAliasReplace("replace");
        _lgNames.setAliasReplaceString("replace");
        _lgNames.setAliasReplaceMultiple("replaceMultiple");
        _lgNames.setAliasEqualsIgnoreCase("equalsIgnoreCase");
        _lgNames.setAliasCompareToIgnoreCase("compareToIgnoreCase");
        _lgNames.setAliasContains("contains");
        _lgNames.setAliasEndsWith("endsWith");
        _lgNames.setAliasStartsWith("startsWith");
        _lgNames.setAliasIndexOf("indexOf");
        _lgNames.setAliasFormat("format");
        _lgNames.setAliasGetBytes("getBytes");
        _lgNames.setAliasIsEmpty("isEmpty");
        _lgNames.setAliasLastIndexOf("lastIndexOf");
        _lgNames.setAliasRegionMatches("regionMatches");
        _lgNames.setAliasSubstring("substring");
        _lgNames.setAliasSubSequence("subSequence");
        _lgNames.setAliasToLowerCase("toLowerCase");
        _lgNames.setAliasToUpperCase("toUpperCase");
        _lgNames.setAliasToLowerCaseChar("toLowerCase");
        _lgNames.setAliasToUpperCaseChar("toUpperCase");
        _lgNames.setAliasTrim("trim");
        _lgNames.setAliasStringBuilder("java.lang.StringBuilder");
        _lgNames.setAliasAppend("append");
        _lgNames.setAliasCapacity("capacity");
        _lgNames.setAliasClear("clear");
        _lgNames.setAliasDelete("delete");
        _lgNames.setAliasDeleteCharAt("deleteCharAt");
        _lgNames.setAliasEnsureCapacity("ensureCapacity");
        _lgNames.setAliasInsert("insert");
        _lgNames.setAliasReverse("reverse");
        _lgNames.setAliasSetCharAt("setCharAt");
        _lgNames.setAliasSetLength("setLength");
        _lgNames.setAliasSame("same");
        _lgNames.setAliasTrimToSize("trimToSize");
        _lgNames.setAliasErrorInitClass("java.lang.$defErrorClass");
        _lgNames.setAliasClone("clone");
        _lgNames.setAliasReadResources("readContent");
        _lgNames.setAliasReadResourcesIndex("index");
        _lgNames.setAliasReadResourcesNames("readNames");
        _lgNames.setAliasReadResourcesNamesLength("nbNames");
        _lgNames.setAliasResources("java.lang.Resources");
        _lgNames.setAliasEnumValues("values");
        _lgNames.setAliasInvokeTarget("java.lang.$invokeTaget");
        _lgNames.setAliasGetCause("getCause");
        _lgNames.setAliasClassNotFoundError("java.lang.$classNotFound");
        _lgNames.setAliasGetVariableOwner("getVariableOwner");
        _lgNames.setAliasGetGenericVariableOwner("getGenericVariableOwner");
        _lgNames.setAliasGetString("getString");
        _lgNames.setAliasClassType("java.lang.$Class");
        _lgNames.setAliasStackTraceElement("java.lang.$stack");
        _lgNames.setAliasCurrentStack("current");
        _lgNames.setAliasCurrentFullStack("currentFull");
        _lgNames.setAliasFct("java.lang.$Fct");
        _lgNames.setAliasCall("call");
        _lgNames.setAliasMetaInfo("metaInfo");
        _lgNames.setAliasInstance("instance");
        _lgNames.setAliasAnnotationType("java.lang.$Annotation");
        _lgNames.setAliasAnnotated("java.lang.$Annotated");
        _lgNames.setAliasGetDefaultValue("getDefaultValue");
        _lgNames.setAliasGetAnnotations("getAnnotations");
        _lgNames.setAliasGetAnnotationsParameters("getAnnotationsParameters");
        _lgNames.setAliasGetDeclaredConstructors("getDeclaredConstructors");
        _lgNames.setAliasGetDeclaredFields("getDeclaredFields");
        _lgNames.setAliasGetDeclaredAnonymousTypes("getDeclaredAnonymousTypes");
        _lgNames.setAliasGetDeclaredAnonymousLambda("getDeclaredAnonymousLambda");
        _lgNames.setAliasGetDeclaredAnonymousLambdaLocalVars("getDeclaredAnonymousLambdaLocVars");
        _lgNames.setAliasGetDeclaredAnonymousLambdaLoopVars("getDeclaredAnonymousLambdaLoopVars");
        _lgNames.setAliasGetDeclaredBlocks("getDeclaredBlocks");
        _lgNames.setAliasGetDeclaredLocalTypes("getDeclaredLocalTypes");
        _lgNames.setAliasGetDeclaredMethods("getDeclaredMethods");
        _lgNames.setAliasGetDeclaredExplicits("getDeclaredExplicits");
        _lgNames.setAliasGetDeclaredImplicits("getDeclaredImplicits");
        _lgNames.setAliasGetDeclaredTrueOperators("getDeclaredTrueOperators");
        _lgNames.setAliasGetDeclaredFalseOperators("getDeclaredFalseOperators");
        _lgNames.setAliasGetDeclaredStaticMethods("getDeclaredStaticMethods");
        _lgNames.setAliasMakeGeneric("makeGeneric");
        _lgNames.setAliasGetAllClasses("getAllClasses");
        _lgNames.setAliasGetOperators("getOperators");
        _lgNames.setAliasConstructor("java.lang.$Constructor");
        _lgNames.setAliasField("java.lang.$Field");
        _lgNames.setAliasMethod("java.lang.$Method");
        _lgNames.setAliasInvoke("invoke");
        _lgNames.setAliasInvokeDirect("invokeDirect");
        _lgNames.setAliasNewInstance("newInstance");
        _lgNames.setAliasIsAbstract("isAbstract");
        _lgNames.setAliasGetFileName("getFileName");
        _lgNames.setAliasGetName("getName");
        _lgNames.setAliasGetPrettyName("getPrettyName");
        _lgNames.setAliasGetPrettySingleName("getPrettySingleName");
        _lgNames.setAliasGetField("get");
        _lgNames.setAliasSetField("set");
        _lgNames.setAliasGetClass("getClass");
        _lgNames.setAliasGetEnclosingType("getEnclosingType");
        _lgNames.setAliasGetDeclaredClasses("getDeclaredClasses");
        _lgNames.setAliasForName("forName");
        _lgNames.setAliasObjectsUtil("java.lang.$ObjectsUtil");
        _lgNames.setAliasStringUtil("java.lang.$StringUtil");
        _lgNames.setAliasSameRef("eq");
        _lgNames.setAliasGetParent("getParent");
        _lgNames.setAliasSetParent("setParent");
        _lgNames.setAliasNext("next");
        _lgNames.setAliasHasNext("hasNext");
        _lgNames.setAliasIterableTable("java.lang.$iterableTable");
        _lgNames.setAliasIteratorTable("iteratorTable");
        _lgNames.setAliasIteratorTableType("java.lang.$iteratorTable");
        _lgNames.setAliasHasNextPair("hasNextPair");
        _lgNames.setAliasNextPair("nextPair");
        _lgNames.setAliasPairType("java.lang.$pair");
        _lgNames.setAliasGetFirst("getFirst");
        _lgNames.setAliasGetSecond("getSecond");
        _lgNames.setAliasName("name");
        _lgNames.setAliasOrdinal("ordinal");
        _lgNames.setAliasEnumName("$name");
        _lgNames.setAliasEnumOrdinal("$ordinal");
        _lgNames.setAliasEnumPredValueOf("valueOf");
        _lgNames.setAliasIterableVar("T");
        _lgNames.setAliasIteratorTypeVar("T");
        _lgNames.setAliasIterableTableVarFirst("T");
        _lgNames.setAliasIterableTableVarSecond("U");
        _lgNames.setAliasIteratorTableTypeVarFirst("T");
        _lgNames.setAliasIteratorTableTypeVarSecond("U");
        _lgNames.setAliasPairTypeVarFirst("T");
        _lgNames.setAliasPairTypeVarSecond("U");
        _lgNames.setAliasEnumParamVar("T");
        _lgNames.setAliasSeedGenerator("java.lang.Generator");
        _lgNames.setAliasSeedDoubleGenerator("java.lang.DoubleGenerator");
        _lgNames.setAliasSeedGet("get");
        _lgNames.setAliasGetOldString("getOldString");
        _lgNames.setAliasGetNewString("getNewString");
        _lgNames.setAliasGetSuperClass("getSuperClass");
        _lgNames.setAliasGetGenericSuperClass("getGenericSuperClass");
        _lgNames.setAliasGetInterfaces("getInterfaces");
        _lgNames.setAliasGetGenericInterfaces("getGenericInterfaces");
        _lgNames.setAliasGetLowerBounds("getLowerBounds");
        _lgNames.setAliasGetUpperBounds("getUpperBounds");
        _lgNames.setAliasGetComponentType("getComponentType");
        _lgNames.setAliasMakeArray("makeArray");
        _lgNames.setAliasGetParameterTypes("getParameterTypes");
        _lgNames.setAliasGetTypeParameters("getTypeParameters");
        _lgNames.setAliasGetParameterNames("getGenericParameterTypes");
        _lgNames.setAliasGetGenericReturnType("getGenericReturnType");
        _lgNames.setAliasGetReturnType("getReturnType");
        _lgNames.setAliasGetActualTypeArguments("getActualTypeArguments");
        _lgNames.setAliasGetFieldType("getType");
        _lgNames.setAliasGetGenericType("getGenericType");
        _lgNames.setAliasIsFinal("isFinal");
        _lgNames.setAliasIsTypeVariable("isTypeVariable");
        _lgNames.setAliasIsVariable("isVariable");
        _lgNames.setAliasIsStatic("isStatic");
        _lgNames.setAliasIsStaticCall("isStaticCall");
        _lgNames.setAliasIsInstanceMethod("isInstanceMethod");
        _lgNames.setAliasIsVarargs("isVarargs");
        _lgNames.setAliasIsNormal("isNormal");
        _lgNames.setAliasIsPublic("isPublic");
        _lgNames.setAliasIsProtected("isProtected");
        _lgNames.setAliasIsPackage("isPackage");
        _lgNames.setAliasIsPrivate("isPrivate");
        _lgNames.setAliasIsClass("isClass");
        _lgNames.setAliasIsWildCard("isWildCard");
        _lgNames.setAliasIsInterface("isInterface");
        _lgNames.setAliasIsEnum("isEnum");
        _lgNames.setAliasIsPrimitive("isPrimitive");
        _lgNames.setAliasIsArray("isArray");
        _lgNames.setAliasIsAnnotation("isAnnotation");
        _lgNames.setAliasMakeWildCard("makeWildCard");
        _lgNames.setAliasIsInstance("isInstance");
        _lgNames.setAliasIsAssignableFrom("isAssignableFrom");
        _lgNames.setAliasInit("init");
        _lgNames.setAliasDefaultInstance("defaultInstance");
        _lgNames.setAliasEnumValueOf("enumValueOf");
        _lgNames.setAliasGetEnumConstants("getEnumConstants");
        _lgNames.setAliasGetGenericBounds("getGenericBounds");
        _lgNames.setAliasGetBounds("getBounds");
        _lgNames.setAliasArrayNewInstance("newArrayInstance");
        _lgNames.setAliasArrayGet("get");
        _lgNames.setAliasArraySet("set");
        _lgNames.setAliasArrayGetLength("getLength");
        _lgNames.setAliasGetDeclaringClass("getDeclaringClass");
        _lgNames.setAliasBinQuot("binQuot");
        _lgNames.setAliasBinMod("binMod");
        _lgNames.setAliasPlus("plus");
        _lgNames.setAliasMinus("minus");
        _lgNames.setAliasMult("mult");
        _lgNames.setAliasAnd("and");
        _lgNames.setAliasOr("or");
        _lgNames.setAliasXor("xor");
        _lgNames.setAliasNegBin("negBin");
        _lgNames.setAliasNeg("neg");
        _lgNames.setAliasLt("lt");
        _lgNames.setAliasGt("gt");
        _lgNames.setAliasLe("le");
        _lgNames.setAliasGe("ge");
        _lgNames.setAliasShiftLeft("shiftLeft");
        _lgNames.setAliasShiftRight("shiftRight");
        _lgNames.setAliasBitShiftLeft("bitShiftLeft");
        _lgNames.setAliasBitShiftRight("bitShiftRight");
        _lgNames.setAliasRotateLeft("rotateLeft");
        _lgNames.setAliasRotateRight("rotateRight");
        _lgNames.setAliasRandom("random");
        _lgNames.setAliasSeed("seed");
        _lgNames.getDisplayedStrings().setFalseString("false");
        _lgNames.getDisplayedStrings().setTrueString("true");
        _lgNames.getDisplayedStrings().setNullString("");
        _lgNames.getDisplayedStrings().setNotNullCoverString("not null");
        _lgNames.getDisplayedStrings().setNullCoverString("null");
        _lgNames.getDisplayedStrings().setStaticCallString("$staticCall");
        _lgNames.getDisplayedStrings().setStaticString("$static");
        _lgNames.getDisplayedStrings().setInfinity("Infinity");
        _lgNames.getDisplayedStrings().setNan("Nan");
        _lgNames.getDisplayedStrings().setExponent("E");
        _lgNames.getPredefTypes().getParams().setAliasSeedGenerator0Get0("a");
    }

    public static ContextEl buildDefKw(String _lang, DefaultLockingClass _lock, Initializer _init,
                                       Options _options, LgNames _undefinedLgNames, int _tabWidth) {
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWordsMap km_ = new KeyWordsMap();
        KeyWords kwl_ = km_.getKeyWords(_lang);
        if (StringList.quickEq(_lang, "en")) {
            km_.initEnStds(_undefinedLgNames);
        } else {
            km_.initFrStds(_undefinedLgNames);
        }
        return ContextFactory.build(CustList.INDEX_NOT_FOUND_ELT,_lock, _init, _options, a_,kwl_, _undefinedLgNames, _tabWidth);
    }
}
