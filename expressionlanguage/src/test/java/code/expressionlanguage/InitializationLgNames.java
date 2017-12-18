package code.expressionlanguage;

import code.expressionlanguage.stds.LgNames;

public final class InitializationLgNames {

    public static LgNames initStandards(ContextEl _context) {
        LgNames lgNames_ = new LgNames();
        lgNames_.setAliasObject("java.lang.Object");
        lgNames_.setAliasVoid("$void");
        lgNames_.setAliasCharSequence("java.lang.CharSequence");
        lgNames_.setAliasCompareTo("compareTo");
        lgNames_.setAliasCompare("compare");
        lgNames_.setAliasEquals("equals");
        lgNames_.setAliasToString("toString");
        lgNames_.setAliasValueOf("valueOf");
        lgNames_.setAliasMaxValueField("MAX_VALUE");
        lgNames_.setAliasMinValueField("MIN_VALUE");
        lgNames_.setAliasIteratorType("$iterator");
        lgNames_.setAliasIterator("iterator");
        lgNames_.setAliasIterable("$iterable");
        lgNames_.setAliasEnumParam("$Enum");
        lgNames_.setAliasEnum("$enum");
        lgNames_.setAliasReplacement("code.util.Replacement");
        lgNames_.setAliasNull("null");
        lgNames_.setAliasNbFormat("badFormat");
        lgNames_.setAliasBadEncode("badEncode");
        lgNames_.setAliasBadIndex("badIndex");
        lgNames_.setAliasPrimBoolean("$boolean");
        lgNames_.setAliasPrimByte("$byte");
        lgNames_.setAliasPrimShort("$short");
        lgNames_.setAliasPrimChar("$char");
        lgNames_.setAliasPrimInteger("$int");
        lgNames_.setAliasPrimLong("$long");
        lgNames_.setAliasPrimFloat("$float");
        lgNames_.setAliasPrimDouble("$double");
        lgNames_.setAliasBoolean("java.lang.Boolean");
        lgNames_.setAliasByte("java.lang.Byte");
        lgNames_.setAliasShort("java.lang.Short");
        lgNames_.setAliasCharacter("java.lang.Character");
        lgNames_.setAliasInteger("java.lang.Integer");
        lgNames_.setAliasLong("java.lang.Long");
        lgNames_.setAliasFloat("java.lang.Float");
        lgNames_.setAliasDouble("java.lang.Double");
        lgNames_.setAliasNumber("java.lang.Number");
        lgNames_.setAliasParseBoolean("parseBoolean");
        lgNames_.setAliasParseByte("parseByte");
        lgNames_.setAliasParseShort("parseShort");
        lgNames_.setAliasParseInt("parseInt");
        lgNames_.setAliasParseLong("parseLong");
        lgNames_.setAliasParseFloat("parseFloat");
        lgNames_.setAliasParseDouble("parseDouble");
        lgNames_.setAliasBooleanValue("booleanValue");
        lgNames_.setAliasByteValue("byteValue");
        lgNames_.setAliasShortValue("shortValue");
        lgNames_.setAliasCharValue("charValue");
        lgNames_.setAliasIntValue("intValue");
        lgNames_.setAliasLongValue("longValue");
        lgNames_.setAliasFloatValue("floatValue");
        lgNames_.setAliasDoubleValue("doubleValue");
        lgNames_.setAliasDigit("digit");
        lgNames_.setAliasIsDigit("isDigit");
        lgNames_.setAliasIsLetter("isLetter");
        lgNames_.setAliasIsLetterOrDigit("isLetterOrDigit");
        lgNames_.setAliasIsWordChar("isWordChar");
        lgNames_.setAliasIsLowerCase("isLowerCase");
        lgNames_.setAliasIsUpperCase("isUpperCase");
        lgNames_.setAliasIsWhitespace("isWhitespace");
        lgNames_.setAliasIsSpace("isSpace");
        lgNames_.setAliasIsInfinite("isInfinite");
        lgNames_.setAliasIsNan("isNan");
        lgNames_.setAliasForDigit("isForDigit");
        lgNames_.setAliasGetDirectionality("isGetDirectionality");
        lgNames_.setAliasGetType("getType");
        lgNames_.setAliasString("java.lang.String");
        lgNames_.setAliasLength("length");
        lgNames_.setAliasCharAt("charAt");
        lgNames_.setAliasToCharArray("toCharArray");
        lgNames_.setAliasSplit("split");
        lgNames_.setAliasSplitChars("splitChars");
        lgNames_.setAliasSplitStrings("splitStrings");
        lgNames_.setAliasReplace("replace");
        lgNames_.setAliasReplaceMultiple("replaceMultiple");
        lgNames_.setAliasEqualsIgnoreCase("equalsIgnoreCase");
        lgNames_.setAliasCompareToIgnoreCase("compareToIgnoreCase");
        lgNames_.setAliasContains("contains");
        lgNames_.setAliasEndsWith("endsWith");
        lgNames_.setAliasStartsWith("startsWith");
        lgNames_.setAliasIndexOf("indexOf");
        lgNames_.setAliasFormat("format");
        lgNames_.setAliasGetBytes("getBytes");
        lgNames_.setAliasIntern("intern");
        lgNames_.setAliasIsEmpty("isEmpty");
        lgNames_.setAliasLastIndexOf("lastIndexOf");
        lgNames_.setAliasRegionMatches("regionMatches");
        lgNames_.setAliasSubstring("substring");
        lgNames_.setAliasSubSequence("subSequence");
        lgNames_.setAliasToLowerCase("toLowerCase");
        lgNames_.setAliasToUpperCase("toUpperCase");
        lgNames_.setAliasTrim("trim");
        lgNames_.setAliasStringBuilder("java.lang.StringBuilder");
        lgNames_.setAliasAppend("append");
        lgNames_.setAliasCapacity("capacity");
        lgNames_.setAliasClear("clear");
        lgNames_.setAliasDelete("delete");
        lgNames_.setAliasDeleteCharAt("charAt");
        lgNames_.setAliasEnsureCapacity("ensureCapacity");
        lgNames_.setAliasInsert("insert");
        lgNames_.setAliasReverse("reverse");
        lgNames_.setAliasSetCharAt("setCharAt");
        lgNames_.setAliasSetLength("setLength");
        lgNames_.setAliasTrimToSize("trimToSize");
        lgNames_.setAliasCountable("code.util.ints.Countable");
        lgNames_.setAliasGet("get");
        lgNames_.setAliasSize("size");
        lgNames_.setAliasSimpleIterator("simpleIterator");
        lgNames_.setAliasNext("next");
        lgNames_.setAliasHasNext("hasNext");
        lgNames_.setAliasName("name");
        lgNames_.setAliasOrdinal("ordinal");
        lgNames_.setAliasGetOldString("getOldString");
        lgNames_.setAliasGetNewString("getNewString");
        lgNames_.setAliasSetOldString("setOldString");
        lgNames_.setAliasSetNewString("setNewString");
        lgNames_.build();
        _context.setStandards(lgNames_);
        lgNames_.setupOverrides(_context);
        return lgNames_;
    }
}
