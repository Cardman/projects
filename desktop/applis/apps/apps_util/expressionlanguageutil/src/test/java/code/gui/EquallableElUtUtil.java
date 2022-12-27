package code.gui;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.filenames.*;
import code.expressionlanguage.fwd.*;
import code.expressionlanguage.guicompos.*;
import code.expressionlanguage.options.*;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.gui.initialize.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.sml.Node;
import code.stream.core.*;
import code.util.*;
import code.util.core.*;
import org.junit.Assert;

public abstract class EquallableElUtUtil {
    public static void assertNull(byte[] _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(String _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(Struct _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(Runnable _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertNull(StringMap<ContentTime> _expected) {
        Assert.assertNull(_expected);
    }
    public static void assertEq(String _expected, Struct _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, ((CharSequenceStruct)_result).toStringInstance());
    }
    public static void assertEq(String _expected, String _result) {
        Assert.assertNotNull(_result);
        Assert.assertEquals(_expected, _result);
    }
    public static void assertSame(OutputType _expected, OutputType _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertSame(Struct _expected, Struct _result) {
        Assert.assertSame(_expected, _result);
    }
    public static void assertEq(long _expected, long _result) {
        Assert.assertEquals(_expected, _result);
    }

    public static void assertNull(Node _value) {
        Assert.assertNull(_value);
    }

    public static void assertTrue(Struct _value) {
        Assert.assertTrue(BooleanStruct.isTrue(_value));
    }

    public static void assertTrue(boolean _value) {
        Assert.assertTrue(_value);
    }

    public static void assertFalse(Struct _value) {
        Assert.assertTrue(BooleanStruct.isFalse(_value));
    }

    public static void assertFalse(boolean _value) {
        Assert.assertFalse(_value);
    }
    public static LgNamesGui newLgNamesGuiSample(AbstractLightProgramInfos _light, AbstractIssuer _issuer) {
        LgNamesGui stds_ = newLgNamesGui(_light, _issuer, "", "", with(_light, init(), "conf.txt", "content"));
        stds_.setExecutingOptions(new ExecutingOptions(new MockAtomicBoolean()));
        stds_.getCustAliases().build(new StringMap<String>(),new StringMap<String>());
        basicStandards(stds_);
        return stds_;
    }
    public static LgNamesGui newLgNamesGui(AbstractLightProgramInfos _light, AbstractIssuer _issuer, String _conf, String _src, StringMap<ContentTime> _files) {
        byte[] zipped_ = _light.getZipFact().zipBinFiles(_files);
        FileInfos infos_ = FileInfos.buildMemoryFromFile(_light, _light.getGenerator(), _light.getValidator(), _issuer, new MemInputFiles(StringUtil.encode(_conf), StringUtil.encode(_src), zipped_), _light.getZipFact(), _light.getThreadFactory());
        return new LgNamesGui(infos_, new MockInterceptor());
    }
    public static ArgumentListCall one(Struct _arg) {
        CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
        ls_.add(new ArgumentWrapper(_arg));
        return new ArgumentListCall(ls_);
    }
    public static ArgumentListCall two(Struct _first, Struct _second) {
        CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
        ls_.add(new ArgumentWrapper(_first));
        ls_.add(new ArgumentWrapper(_second));
        return new ArgumentListCall(ls_);
    }

    public static ArgumentListCall three(Struct _first, Struct _second, Struct _third) {
        CustList<ArgumentWrapper> ls_ = new CustList<ArgumentWrapper>();
        ls_.add(new ArgumentWrapper(_first));
        ls_.add(new ArgumentWrapper(_second));
        ls_.add(new ArgumentWrapper(_third));
        return new ArgumentListCall(ls_);
    }
    public static long toLong(Struct _str) {
        return NumParsers.convertToNumber(_str).longStruct();
    }
    public static Struct call(StdCaller _caller, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return value(_caller.call(_exit, _cont, _instance, _firstArgs, _stackCall));
    }

    public static Struct call(DfInstancer _caller, AbstractExiting _exit, ContextEl _cont, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return value(_caller.call(_exit, _cont, _firstArgs, _stackCall));
    }

    public static Struct value(ArgumentWrapper _a) {
        return _a.getValue().getStruct();
    }
    public static FileInfos newFileInfos(AbstractLightProgramInfos _light) {
        return FileInfos.buildMemoryFromFile(_light, _light.getGenerator(), _light.getValidator(), null, new MemInputFiles(new byte[0],new byte[0],new byte[0]), _light.getZipFact(), _light.getThreadFactory());
    }
    public static StackCall stack(Struct _sensible, InitPhase _phase) {
        StackCall st_ = new StackCall(_phase,new CustomSeedGene());
        st_.getInitializingTypeInfos().getSensibleFields().add(_sensible);
        return st_;
    }
    public static StackCall stack(ContextEl _phase) {
        return StackCall.newInstance(InitPhase.NOTHING,_phase);
    }
    public static StringMap<ContentTime> init() {
        return new StringMap<ContentTime>();
    }
    public static StringMap<ContentTime> with(AbstractLightProgramInfos _light, StringMap<ContentTime> _all, String _name, String _content) {
        _all.put(_name,new ContentTime(StringUtil.encode(_content),_light.getThreadFactory().millis()));
        return _all;
    }

    protected static Forwards getForwards(LgNamesGui _lgName, Options _opt) {
        GuiFileBuilder fileBuilder_ = new GuiFileBuilder(_lgName.getContent(), _lgName.getGuiAliases(), _lgName.getCustAliases());
        return new Forwards(_lgName,_lgName, fileBuilder_, _opt);
    }
    public static MockProgramInfos newMockProgramInfos(CustomSeedGene _s, MockFileSet _set) {
        return new MockProgramInfos("", "", new MockEventListIncr(_s,new int[0],new String[0],new TextAnswerValue[0]), _set);
    }
    public static MockProgramInfos newMockProgramInfos(MockEventListIncr _s, MockFileSet _set) {
        return new MockProgramInfos("", "", _s, _set);
    }
    public static MockFileSet fileSet(long _initMillis, long[] _incrs, String... _roots) {
        return new MockFileSet(_initMillis,_incrs,_roots);
    }
    public static void memoryFileSystem(LgNamesGui _stds,AbstractProgramInfos _pr, MockNameFile... _files) {
        AbstractFileSystem m_ = _stds.getCustAliases().getInfos().getFileSystem();
        m_.build("",StreamZipFile.getZippedBinFiles(_pr.getZipFact().zipBinFiles(MockZipFact.wrapText(_files)), _pr.getZipFact()));
    }
    public static double[] dbs(double... _args) {
        return _args;
    }
    public static long[] lgs(long... _args) {
        return _args;
    }
    public static byte[] wrapInts(int... _files) {
        return NumberUtil.wrapByteArray(MockZipFact.wrapInts(_files));
    }
    public static MockNameFile[] wrap(MockNameFile... _files) {
        return _files;
    }

    public static void basicStandards(LgNames _lgNames) {
        _lgNames.getContent().setDefaultPkg("java.lang");
        _lgNames.getContent().getCoreNames().setAliasObject("java.lang.Object");
        _lgNames.getContent().getCoreNames().setAliasVoid("$void");
        _lgNames.getContent().getCharSeq().setAliasCharSequence("java.lang.CharSequence");
        _lgNames.getContent().getCharSeq().setAliasCharSequenceCompareTo("compareTo");
        _lgNames.getContent().getCharSeq().setAliasStringCompare("compare");
        _lgNames.getContent().getCharSeq().setAliasCharSequenceEquals("equals");
        _lgNames.getContent().getNbAlias().setAliasCompareTo("compareTo");
        _lgNames.getContent().getNbAlias().setAliasCompare("compare");
        _lgNames.getContent().getNbAlias().setAliasEquals("equals");
        _lgNames.getContent().getNbAlias().setAliasToStringMethod("toString");
        _lgNames.getContent().getNbAlias().setAliasSignum("sgn");
        _lgNames.getContent().getNbAlias().setAliasToBinString("bin");
        _lgNames.getContent().getNbAlias().setAliasToOctString("oct");
        _lgNames.getContent().getNbAlias().setAliasToHexString("hex");
        _lgNames.getContent().getNbAlias().setAliasValueOfMethod("valueOf");
        _lgNames.getContent().getNbAlias().setAliasMaxValueField("MAX_VALUE");
        _lgNames.getContent().getNbAlias().setAliasMinValueField("MIN_VALUE");
        _lgNames.getContent().getNbAlias().setAliasPlusInfinityField("PLUS_INFINITY");
        _lgNames.getContent().getNbAlias().setAliasMinusInfinityField("MINUS_INFINITY");
        _lgNames.getContent().getNbAlias().setAliasNanField("NAN");
        _lgNames.getContent().getPredefTypes().setAliasIteratorType("java.lang.$iterator");
        _lgNames.getContent().getPredefTypes().setAliasIterator("iterator");
        _lgNames.getContent().getPredefTypes().setAliasIterable("java.lang.$iterable");
        _lgNames.getContent().getPredefTypes().setAliasEnumParam("java.lang.$Enum");
        _lgNames.getContent().getPredefTypes().setAliasEnumType("java.lang.$en");
        _lgNames.getContent().getCoreNames().setAliasEnums("java.lang.$enums");
        _lgNames.getContent().getCharSeq().setAliasReplacement("code.util.Replacement");
        //        _lgNames.setAliasStore("$badStore");
        _lgNames.getContent().getCoreNames().setAliasStore("code.expressionlanguage.exceptions.DynamicArrayStoreException");
        //        _lgNames.setAliasNullPe("$npe");
        _lgNames.getContent().getCoreNames().setAliasNullPe("code.util.exceptions.NullObjectException");
        _lgNames.getContent().getCoreNames().setAliasBadEncode("java.lang.$enc");
        //        _lgNames.setAliasBadIndex("$badIndex");
        _lgNames.getContent().getCoreNames().setAliasBadIndex("code.expressionlanguage.exceptions.BadIndexException");
        _lgNames.getContent().getCoreNames().setAliasBadArgNumber("code.expressionlanguage.exceptions.BadArgNumber");
        _lgNames.getContent().getCoreNames().setAliasAbstractTypeErr("code.expressionlanguage.exceptions.AbstractTypeErr");
        _lgNames.getContent().getCoreNames().setAliasIllegalType("code.expressionlanguage.exceptions.IllegalType");
        _lgNames.getContent().getCoreNames().setAliasNonInvokable("code.expressionlanguage.exceptions.NonInvokable");
        _lgNames.getContent().getCoreNames().setAliasIllegalArg("code.expressionlanguage.exceptions.IllegalArgument");
        //        _lgNames.setAliasBadSize("$badSize");
        _lgNames.getContent().getCoreNames().setAliasBadSize("code.expressionlanguage.exceptions.NegativeSizeException");
        //        _lgNames.setAliasError("$error");
        _lgNames.getContent().getCoreNames().setAliasError("java.lang.Exception");
        _lgNames.getContent().getCoreNames().setAliasErrorCurrentStack("current");
        _lgNames.getContent().getCoreNames().setAliasErrorToString("toString");
        _lgNames.getContent().getCoreNames().setAliasGetMessage("getMessage");
        _lgNames.getContent().getCoreNames().setAliasCastType("code.expressionlanguage.exceptions.DynamicCastClassException");
        //        _lgNames.setAliasDivisionZero("$divZero");
        _lgNames.getContent().getCoreNames().setAliasDivisionZero("code.expressionlanguage.exceptions.DivideZeroException");
        //_lgNames.setAliasSof("$sofe");
        _lgNames.getContent().getMathRef().setAliasMath("java.lang.$math");
        _lgNames.getContent().getMathRef().setAliasAbs("abs");
        _lgNames.getContent().getMathRef().setAliasMax("max");
        _lgNames.getContent().getMathRef().setAliasMin("min");
        _lgNames.getContent().getMathRef().setAliasMod("mod");
        _lgNames.getContent().getMathRef().setAliasQuot("quot");
        _lgNames.getContent().getCoreNames().setAliasSof("code.expressionlanguage.exceptions.StackOverFlow");
        _lgNames.getContent().getCoreNames().setAliasNbFormat("java.lang.badFormat");
        _lgNames.getContent().getPrimTypes().setAliasPrimBoolean("$boolean");
        _lgNames.getContent().getPrimTypes().setAliasPrimByte("$byte");
        _lgNames.getContent().getPrimTypes().setAliasPrimShort("$short");
        _lgNames.getContent().getPrimTypes().setAliasPrimChar("$char");
        _lgNames.getContent().getPrimTypes().setAliasPrimInteger("$int");
        _lgNames.getContent().getPrimTypes().setAliasPrimLong("$long");
        _lgNames.getContent().getPrimTypes().setAliasPrimFloat("$float");
        _lgNames.getContent().getPrimTypes().setAliasPrimDouble("$double");
        _lgNames.getContent().getNbAlias().setAliasBoolean("java.lang.Boolean");
        _lgNames.getContent().getNbAlias().setAliasByte("java.lang.Byte");
        _lgNames.getContent().getNbAlias().setAliasShort("java.lang.Short");
        _lgNames.getContent().getNbAlias().setAliasCharacter("java.lang.Character");
        _lgNames.getContent().getNbAlias().setAliasInteger("java.lang.Integer");
        _lgNames.getContent().getNbAlias().setAliasLong("java.lang.Long");
        _lgNames.getContent().getNbAlias().setAliasFloat("java.lang.Float");
        _lgNames.getContent().getNbAlias().setAliasDouble("java.lang.Double");
        _lgNames.getContent().getNbAlias().setAliasNumber("java.lang.Number");
        _lgNames.getContent().getNbAlias().setAliasParseBoolean("parseBoolean");
        _lgNames.getContent().getNbAlias().setAliasParseByte("parseByte");
        _lgNames.getContent().getNbAlias().setAliasParseShort("parseShort");
        _lgNames.getContent().getNbAlias().setAliasParseInt("parseInt");
        _lgNames.getContent().getNbAlias().setAliasParseLong("parseLong");
        _lgNames.getContent().getNbAlias().setAliasParseFloat("parseFloat");
        _lgNames.getContent().getNbAlias().setAliasParseDouble("parseDouble");
        _lgNames.getContent().getNbAlias().setAliasParseByteOrNull("parseByteOrNull");
        _lgNames.getContent().getNbAlias().setAliasParseShortOrNull("parseShortOrNull");
        _lgNames.getContent().getNbAlias().setAliasParseIntOrNull("parseIntOrNull");
        _lgNames.getContent().getNbAlias().setAliasParseLongOrNull("parseLongOrNull");
        _lgNames.getContent().getNbAlias().setAliasParseFloatOrNull("parseFloatOrNull");
        _lgNames.getContent().getNbAlias().setAliasParseDoubleOrNull("parseDoubleOrNull");
        _lgNames.getContent().getNbAlias().setAliasBooleanValue("booleanValue");
        _lgNames.getContent().getNbAlias().setAliasByteValue("byteValue");
        _lgNames.getContent().getNbAlias().setAliasShortValue("shortValue");
        _lgNames.getContent().getNbAlias().setAliasCharValue("charValue");
        _lgNames.getContent().getNbAlias().setAliasIntValue("intValue");
        _lgNames.getContent().getNbAlias().setAliasLongValue("longValue");
        _lgNames.getContent().getNbAlias().setAliasFloatValue("floatValue");
        _lgNames.getContent().getNbAlias().setAliasDoubleValue("doubleValue");
        _lgNames.getContent().getNbAlias().setAliasDigit("digit");
        _lgNames.getContent().getNbAlias().setAliasIsDigit("isDigit");
        _lgNames.getContent().getNbAlias().setAliasIsLetter("isLetter");
        _lgNames.getContent().getNbAlias().setAliasIsLetterOrDigit("isLetterOrDigit");
        _lgNames.getContent().getNbAlias().setAliasIsWordChar("isWordChar");
        _lgNames.getContent().getNbAlias().setAliasIsLowerCase("isLowerCase");
        _lgNames.getContent().getNbAlias().setAliasIsUpperCase("isUpperCase");
        _lgNames.getContent().getNbAlias().setAliasIsWhitespace("isWhitespace");
        _lgNames.getContent().getNbAlias().setAliasIsSpace("isSpace");
        _lgNames.getContent().getNbAlias().setAliasIsInfinite("isInfinite");
        _lgNames.getContent().getNbAlias().setAliasIsNan("isNan");
        _lgNames.getContent().getNbAlias().setAliasForDigit("forDigit");
        _lgNames.getContent().getNbAlias().setAliasGetDirectionality("getDirectionality");
        _lgNames.getContent().getReflect().setAliasGetType("getType");
        _lgNames.getContent().getNbAlias().setAliasGetCharType("getType");
        _lgNames.getContent().getCharSeq().setAliasString("java.lang.String");
        _lgNames.getContent().getCharSeq().setAliasStringValueOf("valueOf");
        _lgNames.getContent().getCharSeq().setAliasLength("length");
        _lgNames.getContent().getCharSeq().setAliasCharAt("charAt");
        _lgNames.getContent().getCharSeq().setAliasToCharArray("toCharArray");
        _lgNames.getContent().getCharSeq().setAliasCharSequenceToString("toString");
        _lgNames.getContent().getCharSeq().setAliasSplit("split");
        _lgNames.getContent().getCharSeq().setAliasSplitChars("splitChars");
        _lgNames.getContent().getCharSeq().setAliasSplitStrings("splitStrings");
        _lgNames.getContent().getCharSeq().setAliasReplace("replace");
        _lgNames.getContent().getCharSeq().setAliasReplaceString("replace");
        _lgNames.getContent().getCharSeq().setAliasReplaceMultiple("replaceMultiple");
        _lgNames.getContent().getCharSeq().setAliasEqualsIgnoreCase("equalsIgnoreCase");
        _lgNames.getContent().getCharSeq().setAliasCompareToIgnoreCase("compareToIgnoreCase");
        _lgNames.getContent().getCharSeq().setAliasContains("contains");
        _lgNames.getContent().getCharSeq().setAliasEndsWith("endsWith");
        _lgNames.getContent().getCharSeq().setAliasStartsWith("startsWith");
        _lgNames.getContent().getCharSeq().setAliasIndexOf("indexOf");
        _lgNames.getContent().getCharSeq().setAliasFormat("format");
        _lgNames.getContent().getCharSeq().setAliasGetBytes("getBytes");
        _lgNames.getContent().getCharSeq().setAliasIsEmpty("isEmpty");
        _lgNames.getContent().getCharSeq().setAliasLastIndexOf("lastIndexOf");
        _lgNames.getContent().getCharSeq().setAliasRegionMatches("regionMatches");
        _lgNames.getContent().getCharSeq().setAliasSubstring("substring");
        _lgNames.getContent().getCharSeq().setAliasSubSequence("subSequence");
        _lgNames.getContent().getCharSeq().setAliasToLowerCase("toLowerCase");
        _lgNames.getContent().getCharSeq().setAliasToUpperCase("toUpperCase");
        _lgNames.getContent().getNbAlias().setAliasToLowerCaseChar("toLowerCase");
        _lgNames.getContent().getNbAlias().setAliasToUpperCaseChar("toUpperCase");
        _lgNames.getContent().getCharSeq().setAliasTrim("trim");
        _lgNames.getContent().getCharSeq().setAliasStringBuilder("java.lang.StringBuilder");
        _lgNames.getContent().getCharSeq().setAliasAppend("append");
        _lgNames.getContent().getCharSeq().setAliasCapacity("capacity");
        _lgNames.getContent().getCharSeq().setAliasClear("clear");
        _lgNames.getContent().getCharSeq().setAliasDelete("delete");
        _lgNames.getContent().getCharSeq().setAliasDeleteCharAt("deleteCharAt");
        _lgNames.getContent().getCharSeq().setAliasEnsureCapacity("ensureCapacity");
        _lgNames.getContent().getCharSeq().setAliasInsert("insert");
        _lgNames.getContent().getCharSeq().setAliasReverse("reverse");
        _lgNames.getContent().getCharSeq().setAliasSetCharAt("setCharAt");
        _lgNames.getContent().getCharSeq().setAliasSetLength("setLength");
        _lgNames.getContent().getCharSeq().setAliasSame("same");
        _lgNames.getContent().getCharSeq().setAliasTrimToSize("trimToSize");
        _lgNames.getContent().getCoreNames().setAliasErrorInitClass("java.lang.$defErrorClass");
        _lgNames.getContent().getCoreNames().setAliasClone("clone");
        _lgNames.getContent().getCoreNames().setAliasReadResources("readContent");
        _lgNames.getContent().getCoreNames().setAliasReadResourcesIndex("index");
        _lgNames.getContent().getCoreNames().setAliasReadResourcesNames("readNames");
        _lgNames.getContent().getCoreNames().setAliasReadResourcesNamesLength("nbNames");
        _lgNames.getContent().getCoreNames().setAliasResources("java.lang.Resources");
        _lgNames.getContent().getCoreNames().setAliasArrayLength("length");
        _lgNames.getContent().getPredefTypes().setAliasEnumValues("values");
        _lgNames.getContent().getReflect().setAliasInvokeTarget("java.lang.$invokeTaget");
        _lgNames.getContent().getCoreNames().setAliasGetCause("getCause");
        _lgNames.getContent().getReflect().setAliasClassNotFoundError("java.lang.$classNotFound");
        _lgNames.getContent().getReflect().setAliasGetVariableOwner("getVariableOwner");
        _lgNames.getContent().getReflect().setAliasGetGenericVariableOwner("getGenericVariableOwner");
        _lgNames.getContent().getReflect().setAliasGetString("getString");
        _lgNames.getContent().getReflect().setAliasClassType("java.lang.$Class");
        _lgNames.getContent().getStackElt().setAliasStackTraceElement("java.lang.$stack");
        _lgNames.getContent().getStackElt().setAliasCurrentStack("current");
        _lgNames.getContent().getStackElt().setAliasCurrentFullStack("currentFull");
        _lgNames.getContent().getStackElt().setAliasStackTraceElementToString("toString");
        _lgNames.getContent().getReflect().setAliasFct("java.lang.$Fct");
        _lgNames.getContent().getReflect().setAliasCall("call");
        _lgNames.getContent().getReflect().setAliasMetaInfo("metaInfo");
        _lgNames.getContent().getReflect().setAliasInstance("instance");
        _lgNames.getContent().getReflect().setAliasAnnotationType("java.lang.$Annotation");
        _lgNames.getContent().getReflect().setAliasAnnotated("java.lang.$Annotated");
        _lgNames.getContent().getReflect().setAliasGetDefaultValue("getDefaultValue");
        _lgNames.getContent().getReflect().setAliasGetAnnotations("getAnnotations");
        _lgNames.getContent().getReflect().setAliasGetAnnotationsSupp("getAnnotationsSupp");
        _lgNames.getContent().getReflect().setAliasGetAnnotationsParameters("getAnnotationsParameters");
        _lgNames.getContent().getReflect().setAliasGetDeclaredConstructors("getDeclaredConstructors");
        _lgNames.getContent().getReflect().setAliasGetDeclaredFields("getDeclaredFields");
        _lgNames.getContent().getReflect().setAliasGetDeclaredAnonymousTypes("getDeclaredAnonymousTypes");
        _lgNames.getContent().getReflect().setAliasGetDeclaredAnonymousLambda("getDeclaredAnonymousLambda");
        _lgNames.getContent().getReflect().setAliasGetDeclaredAnonymousLambdaLocalVars("getDeclaredAnonymousLambdaLocVars");
        _lgNames.getContent().getReflect().setAliasGetDeclaredAnonymousLambdaLocalVarsNb("getDeclaredAnonymousLambdaLocVarsNb");
        _lgNames.getContent().getReflect().setAliasGetDeclaredAnonymousLambdaLoopVars("getDeclaredAnonymousLambdaLoopVars");
        _lgNames.getContent().getReflect().setAliasGetDeclaredBlocks("getDeclaredBlocks");
        _lgNames.getContent().getReflect().setAliasGetDeclaredSwitchMethods("getDeclaredSwitchMethods");
        _lgNames.getContent().getReflect().setAliasGetDeclaredLocalTypes("getDeclaredLocalTypes");
        _lgNames.getContent().getReflect().setAliasGetDeclaredMethods("getDeclaredMethods");
        _lgNames.getContent().getReflect().setAliasGetDeclaredExplicits("getDeclaredExplicits");
        _lgNames.getContent().getReflect().setAliasGetDeclaredImplicits("getDeclaredImplicits");
        _lgNames.getContent().getReflect().setAliasGetDeclaredTrueOperators("getDeclaredTrueOperators");
        _lgNames.getContent().getReflect().setAliasGetDeclaredFalseOperators("getDeclaredFalseOperators");
        _lgNames.getContent().getReflect().setAliasGetDeclaredStaticMethods("getDeclaredStaticMethods");
        _lgNames.getContent().getReflect().setAliasMakeGeneric("makeGeneric");
        _lgNames.getContent().getReflect().setAliasGetAllClasses("getAllClasses");
        _lgNames.getContent().getReflect().setAliasGetOperators("getOperators");
        _lgNames.getContent().getReflect().setAliasConstructor("java.lang.$Constructor");
        _lgNames.getContent().getReflect().setAliasField("java.lang.$Field");
        _lgNames.getContent().getReflect().setAliasMethod("java.lang.$Method");
        _lgNames.getContent().getReflect().setAliasInvoke("invoke");
        _lgNames.getContent().getReflect().setAliasInvokeDirect("invokeDirect");
        _lgNames.getContent().getReflect().setAliasNewInstance("newInstance");
        _lgNames.getContent().getReflect().setAliasIsAbstract("isAbstract");
        _lgNames.getContent().getReflect().setAliasGetFileName("getFileName");
        _lgNames.getContent().getReflect().setAliasGetName("getName");
        _lgNames.getContent().getReflect().setAliasGetPrettyName("getPrettyName");
        _lgNames.getContent().getReflect().setAliasGetPrettySingleName("getPrettySingleName");
        _lgNames.getContent().getReflect().setAliasGetField("get");
        _lgNames.getContent().getReflect().setAliasSetField("set");
        _lgNames.getContent().getReflect().setAliasGetClass("getClass");
        _lgNames.getContent().getReflect().setAliasGetEnclosingType("getEnclosingType");
        _lgNames.getContent().getReflect().setAliasGetDeclaredClasses("getDeclaredClasses");
        _lgNames.getContent().getReflect().setAliasForName("forName");
        _lgNames.getContent().getCoreNames().setAliasRange("java.lang.Range");
        _lgNames.getContent().getCoreNames().setAliasRangeLower("lower");
        _lgNames.getContent().getCoreNames().setAliasRangeUpper("upper");
        _lgNames.getContent().getCoreNames().setAliasRangeUnlimited("unlimited");
        _lgNames.getContent().getCoreNames().setAliasRangeUnlimitedStep("unlimitedStep");
        _lgNames.getContent().getCoreNames().setAliasObjectsUtil("java.lang.$ObjectsUtil");
        _lgNames.getContent().getCoreNames().setAliasStringUtil("java.lang.$StringUtil");
        _lgNames.getContent().getCoreNames().setAliasStringUtilValueOf("valueOf");
        _lgNames.getContent().getCoreNames().setAliasSameRef("eq");
        _lgNames.getContent().getCoreNames().setAliasGetParent("getParent");
        _lgNames.getContent().getCoreNames().setAliasSetParent("setParent");
        _lgNames.getContent().getCoreNames().setAliasGetFct("getFct");
        _lgNames.getContent().getPredefTypes().setAliasNext("next");
        _lgNames.getContent().getPredefTypes().setAliasHasNext("hasNext");
        _lgNames.getContent().getPredefTypes().setAliasIterableTable("java.lang.$iterableTable");
        _lgNames.getContent().getPredefTypes().setAliasIteratorTable("iteratorTable");
        _lgNames.getContent().getPredefTypes().setAliasIteratorTableType("java.lang.$iteratorTable");
        _lgNames.getContent().getPredefTypes().setAliasHasNextPair("hasNextPair");
        _lgNames.getContent().getPredefTypes().setAliasNextPair("nextPair");
        _lgNames.getContent().getPredefTypes().setAliasPairType("java.lang.$pair");
        _lgNames.getContent().getPredefTypes().setAliasGetFirst("getFirst");
        _lgNames.getContent().getPredefTypes().setAliasGetSecond("getSecond");
        _lgNames.getContent().getCoreNames().setAliasName("name");
        _lgNames.getContent().getCoreNames().setAliasOrdinal("ordinal");
        _lgNames.getContent().getPredefTypes().setAliasEnumName("$name");
        _lgNames.getContent().getPredefTypes().setAliasEnumOrdinal("$ordinal");
        _lgNames.getContent().getPredefTypes().setAliasEnumPredValueOf("valueOf");
        _lgNames.getContent().getPredefTypes().setAliasIterableVar("T");
        _lgNames.getContent().getPredefTypes().setAliasIteratorTypeVar("T");
        _lgNames.getContent().getPredefTypes().setAliasIterableTableVarFirst("T");
        _lgNames.getContent().getPredefTypes().setAliasIterableTableVarSecond("U");
        _lgNames.getContent().getPredefTypes().setAliasIteratorTableTypeVarFirst("T");
        _lgNames.getContent().getPredefTypes().setAliasIteratorTableTypeVarSecond("U");
        _lgNames.getContent().getPredefTypes().setAliasPairTypeVarFirst("T");
        _lgNames.getContent().getPredefTypes().setAliasPairTypeVarSecond("U");
        _lgNames.getContent().getPredefTypes().setAliasEnumParamVar("T");
        _lgNames.getContent().getPredefTypes().setAliasSeedGenerator("java.lang.Generator");
        _lgNames.getContent().getPredefTypes().setAliasSeedDoubleGenerator("java.lang.DoubleGenerator");
        _lgNames.getContent().getPredefTypes().setAliasSeedGet("get");
        _lgNames.getContent().getCharSeq().setAliasGetOldString("getOldString");
        _lgNames.getContent().getCharSeq().setAliasGetNewString("getNewString");
        _lgNames.getContent().getReflect().setAliasGetSuperClass("getSuperClass");
        _lgNames.getContent().getReflect().setAliasGetGenericSuperClass("getGenericSuperClass");
        _lgNames.getContent().getReflect().setAliasGetInterfaces("getInterfaces");
        _lgNames.getContent().getReflect().setAliasGetGenericInterfaces("getGenericInterfaces");
        _lgNames.getContent().getReflect().setAliasGetLowerBounds("getLowerBounds");
        _lgNames.getContent().getReflect().setAliasGetUpperBounds("getUpperBounds");
        _lgNames.getContent().getReflect().setAliasGetComponentType("getComponentType");
        _lgNames.getContent().getReflect().setAliasMakeArray("makeArray");
        _lgNames.getContent().getReflect().setAliasGetParameterTypes("getParameterTypes");
        _lgNames.getContent().getReflect().setAliasGetTypeParameters("getTypeParameters");
        _lgNames.getContent().getReflect().setAliasGetParameterNames("getGenericParameterTypes");
        _lgNames.getContent().getReflect().setAliasGetGenericReturnType("getGenericReturnType");
        _lgNames.getContent().getReflect().setAliasGetReturnType("getReturnType");
        _lgNames.getContent().getReflect().setAliasGetActualTypeArguments("getActualTypeArguments");
        _lgNames.getContent().getReflect().setAliasGetType("getType");
        _lgNames.getContent().getReflect().setAliasGetGenericType("getGenericType");
        _lgNames.getContent().getReflect().setAliasIsFinal("isFinal");
        _lgNames.getContent().getReflect().setAliasIsTypeVariable("isTypeVariable");
        _lgNames.getContent().getReflect().setAliasIsVariable("isVariable");
        _lgNames.getContent().getReflect().setAliasIsStatic("isStatic");
        _lgNames.getContent().getReflect().setAliasIsStaticCall("isStaticCall");
        _lgNames.getContent().getReflect().setAliasIsInstanceMethod("isInstanceMethod");
        _lgNames.getContent().getReflect().setAliasIsVarargs("isVarargs");
        _lgNames.getContent().getReflect().setAliasIsNormal("isNormal");
        _lgNames.getContent().getReflect().setAliasIsPublic("isPublic");
        _lgNames.getContent().getReflect().setAliasIsProtected("isProtected");
        _lgNames.getContent().getReflect().setAliasIsPackage("isPackage");
        _lgNames.getContent().getReflect().setAliasIsPrivate("isPrivate");
        _lgNames.getContent().getReflect().setAliasIsClass("isClass");
        _lgNames.getContent().getReflect().setAliasIsSpecialClass("isSpeClass");
        _lgNames.getContent().getReflect().setAliasIsSpecialMuClass("isSpeMuClass");
        _lgNames.getContent().getReflect().setAliasIsWildCard("isWildCard");
        _lgNames.getContent().getReflect().setAliasIsRefType("isRefType");
        _lgNames.getContent().getReflect().setAliasIsInterface("isInterface");
        _lgNames.getContent().getReflect().setAliasIsEnum("isEnum");
        _lgNames.getContent().getReflect().setAliasIsPrimitive("isPrimitive");
        _lgNames.getContent().getReflect().setAliasIsArray("isArray");
        _lgNames.getContent().getReflect().setAliasIsAnnotation("isAnnotation");
        _lgNames.getContent().getReflect().setAliasMakeWildCard("makeWildCard");
        _lgNames.getContent().getReflect().setAliasMakeRef("makeRefType");
        _lgNames.getContent().getReflect().setAliasIsInstance("isInstance");
        _lgNames.getContent().getReflect().setAliasIsAssignableFrom("isAssignableFrom");
        _lgNames.getContent().getReflect().setAliasInit("init");
        _lgNames.getContent().getReflect().setAliasTryWrap("tryWrap");
        _lgNames.getContent().getReflect().setAliasDefaultInstance("defaultInstance");
        _lgNames.getContent().getReflect().setAliasEnumValueOf("enumValueOf");
        _lgNames.getContent().getReflect().setAliasGetEnumConstants("getEnumConstants");
        _lgNames.getContent().getReflect().setAliasGetGenericBounds("getGenericBounds");
        _lgNames.getContent().getReflect().setAliasGetBounds("getBounds");
        _lgNames.getContent().getReflect().setAliasArrayNewInstance("newArrayInstance");
        _lgNames.getContent().getReflect().setAliasArrayGet("get");
        _lgNames.getContent().getReflect().setAliasArraySet("set");
        _lgNames.getContent().getReflect().setAliasArrayGetLength("getLength");
        _lgNames.getContent().getReflect().setAliasGetDeclaringClass("getDeclaringClass");
        _lgNames.getContent().getMathRef().setAliasBinQuot("binQuot");
        _lgNames.getContent().getMathRef().setAliasBinMod("binMod");
        _lgNames.getContent().getMathRef().setAliasPlus("plus");
        _lgNames.getContent().getMathRef().setAliasMinus("minus");
        _lgNames.getContent().getMathRef().setAliasMult("mult");
        _lgNames.getContent().getMathRef().setAliasAnd("and");
        _lgNames.getContent().getMathRef().setAliasOr("or");
        _lgNames.getContent().getMathRef().setAliasXor("xor");
        _lgNames.getContent().getMathRef().setAliasNegBin("negBin");
        _lgNames.getContent().getMathRef().setAliasNeg("neg");
        _lgNames.getContent().getMathRef().setAliasLt("lt");
        _lgNames.getContent().getMathRef().setAliasGt("gt");
        _lgNames.getContent().getMathRef().setAliasLe("le");
        _lgNames.getContent().getMathRef().setAliasGe("ge");
        _lgNames.getContent().getMathRef().setAliasShiftLeft("shiftLeft");
        _lgNames.getContent().getMathRef().setAliasShiftRight("shiftRight");
        _lgNames.getContent().getMathRef().setAliasBitShiftLeft("bitShiftLeft");
        _lgNames.getContent().getMathRef().setAliasBitShiftRight("bitShiftRight");
        _lgNames.getContent().getMathRef().setAliasRotateLeft("rotateLeft");
        _lgNames.getContent().getMathRef().setAliasRotateRight("rotateRight");
        _lgNames.getContent().getMathRef().setAliasRandom("random");
        _lgNames.getContent().getMathRef().setAliasNativeRandom("natRandom");
        _lgNames.getContent().getMathRef().setAliasEval("eval");
        _lgNames.getContent().getMathRef().setAliasSeed("seed");
        _lgNames.getContent().getMathRef().setAliasSeedSpecGenerator("seedGenerator");
        _lgNames.getContent().getMathRef().setAliasSeedSpecDoubleGenerator("seedDoubleGenerator");
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

    public static ResultContext build(Options _options, ExecutingOptions _exec, AnalysisMessages _mess, KeyWords _definedKw, LgNamesGui _definedLgNames, StringMap<String> _files) {
        _definedLgNames.getCustAliases().messages(_mess, "en", _exec.getMessages());
        _definedLgNames.getCustAliases().keyWord(_definedKw, "en", _exec.getKeyWords());
        _definedLgNames.getCustAliases().otherAlias(_definedLgNames.getContent(), "en", _exec.getAliases());
        _definedLgNames.getGuiAliases().otherAliasGui(_definedLgNames.addon("en"),_exec.getAliases());
        _definedLgNames.setExecutingOptions(_exec);
        _definedLgNames.getGuiExecutingBlocks().initApplicationParts(new StringList(), _exec.getLightProgramInfos(),_exec.getListGenerator());
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        GuiFileBuilder fileBuilder_ = new GuiFileBuilder(_definedLgNames.getContent(), _definedLgNames.getGuiAliases(), _definedLgNames.getCustAliases());
        Forwards forwards_ = new Forwards(_definedLgNames, _definedLgNames, fileBuilder_, _options);
        page_.setLogErr(forwards_);
        AnalysisMessages.validateMessageContents(_mess.allMessages(), page_);
        ContextFactory.validateStds(forwards_,_mess, _definedKw, _definedLgNames.getCustAliases().defComments(), _options, _definedLgNames.getContent(), page_);
        ContextEl reportedMessages_ = ContextFactory.addResourcesAndValidate(_files, _exec.getSrcFolder(), page_, forwards_);
        return new ResultContext(reportedMessages_, page_.getMessages());
    }
}
