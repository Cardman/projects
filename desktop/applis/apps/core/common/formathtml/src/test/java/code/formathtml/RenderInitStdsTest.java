package code.formathtml;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbstractConstantsCalculator;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.exec.InitPhase;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.options.ValidatorStandard;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.opers.RendDimensionArrayInstancing;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.BeanFileBuilder;
import code.formathtml.util.BeanLgNames;
import code.sml.Element;
import code.util.CustList;
import code.util.IdMap;
import code.util.Ints;
import code.util.StringMap;
import org.junit.Test;

public final class RenderInitStdsTest extends CommonRender {
    @Test
    public void process1Test() {
        BeanCustLgNames b_ = new BeanCustLgNamesImpl();
        basicStandards(b_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords k_ = new KeyWords();
        assertTrue(contextEl(b_,a_,k_, null));
    }
    @Test
    public void process5Test() {
        BeanCustLgNames b_ = new BeanCustLgNamesImpl();
        basicStandards(b_);
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords k_ = new KeyWords();
        k_.setKeyWordIf("i");
        k_.setKeyWordElseif("m");
        k_.setKeyWordElse("indexe");
        assertTrue(contextEl(b_,a_,k_, null));
    }
    @Test
    public void process6Test() {
        BeanCustLgNames b_ = new BeanCustLgNamesImpl();
        basicStandards(b_);
        AnalysisMessages am_ = new AnalysisMessages();
        KeyWords k_ = new KeyWords();
        Configuration conf_ =  EquallableRenderUtil.newConfiguration();
        conf_.setPrefix("c");
        Options options_ = new Options();
        int tabWidth_ = 4;
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        BeanFileBuilder fileBuilder_ = BeanFileBuilder.newInstance(b_.getContent(), b_.getBeanAliases());
        Forwards forwards_ = new Forwards(b_, fileBuilder_, options_);
        ContextFactory.validatedStds(forwards_, am_, k_, new CustList<CommentDelimiters>(), options_, b_.getContent(), page_);
        b_.build();
        ValidatorStandard.setupOverrides(page_);
        AnalyzedTestContextRender an_ = new AnalyzedTestContextRender(options_,page_, forwards_, b_);
        ContextEl generate_ = an_.getForwards().generate(options_);
        AnalyzedTestConfiguration a_ = new AnalyzedTestConfiguration(conf_, an_, an_.getForwards(), b_);
        RendStackCall rendStackCall_ = new RendStackCall(InitPhase.NOTHING, generate_);
        BeanLgNames standards_ = (BeanLgNames) generate_.getStandards();
        CommonRender.getHeaders(new StringMap<String>(), a_);
        String err_ = RendDimensionArrayInstancing.newCustomArrayOrExc(new Ints(), "$int", new Ints(), generate_, rendStackCall_).getClassName(generate_);
        assertEq(standards_.getContent().getCoreNames().getAliasBadSize(),err_);
        err_ = RendDimensionArrayInstancing.newCustomArrayOrExc(new Ints(), "$int", new Ints(-1), generate_, rendStackCall_).getClassName(generate_);
        assertEq(standards_.getContent().getCoreNames().getAliasBadSize(),err_);
        new Navigation().initializeRendSessionDoc(null, null, null, rendStackCall_);
        rendStackCall_.addPage(new ImportingPage());
        RendBlock.processDo(a_.getConfiguration(),null,null,null, rendStackCall_);
        RendBlock.processElse(a_.getConfiguration(),null,null,null, rendStackCall_);
        RendBlock.processElseIf(a_.getConfiguration(),null,null,null, rendStackCall_);
        RendBlock.processFinally(a_.getConfiguration(),null,null,null, rendStackCall_);
        RendBlock.setVisited(rendStackCall_.getLastPage(),null);
        assertNull(RendBlock.hasBlockBreak(rendStackCall_.getLastPage(),""));
        assertNull(RendBlock.hasBlockContinue(a_.getConfiguration(),null,null, rendStackCall_.getLastPage(),"", rendStackCall_));
        assertNull(RendBlock.getParentNode((Element)null));
        RendDynOperationNode.getArgumentPair(new IdMap<RendDynOperationNode, ArgumentsPair>(),null);
        RendDynOperationNode.getFirstNode(null);
        assertNull(RendDynOperationNode.getParentOrNull(null));
        new ImportingPage().getSum();
    }
    private boolean contextEl(BeanCustLgNames _beanLgNames, AnalysisMessages _mess, KeyWords _kw, AbstractConstantsCalculator _calculator) {
        return contextEl(new StringMap<String>(),new Options(),_beanLgNames,_mess,_kw, _calculator);
    }
    private boolean contextEl(StringMap<String> _files, Options _opt, BeanCustLgNames _beanLgNames, AnalysisMessages _mess, KeyWords _kw, AbstractConstantsCalculator _calculator) {
        Configuration conf_ =  EquallableRenderUtil.newConfiguration();
        conf_.setPrefix("c");
        int tabWidth_ = 4;
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        BeanFileBuilder fileBuilder_ = BeanFileBuilder.newInstance(_beanLgNames.getContent(), _beanLgNames.getBeanAliases());
        Forwards forwards_ = new Forwards(_beanLgNames, fileBuilder_, _opt);
        ContextFactory.validatedStds(forwards_, _mess, _kw, new CustList<CommentDelimiters>(), _opt, _beanLgNames.getContent(), page_);
        _beanLgNames.build();
        ValidatorStandard.setupOverrides(page_);
        AnalyzedTestContextRender an_ = new AnalyzedTestContextRender(_opt,page_, forwards_, _beanLgNames);
        AnalyzedTestConfiguration a_ = new AnalyzedTestConfiguration(conf_, an_, an_.getForwards(), _beanLgNames);
        CommonRender.getHeaders(_files, a_);
        return isEmptyErrors(a_);
    }

    private static void basicStandards(BeanLgNames _lgNames) {
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
        _lgNames.getContent().getCoreNames().setAliasStore("code.expressionlanguage.exceptions.DynamicArrayStoreException");
        _lgNames.getContent().getCoreNames().setAliasNullPe("code.util.exceptions.NullObjectException");
        _lgNames.getContent().getCoreNames().setAliasBadEncode("java.lang.$enc");
        _lgNames.getContent().getCoreNames().setAliasBadIndex("code.expressionlanguage.exceptions.BadIndexException");
        _lgNames.getContent().getCoreNames().setAliasBadArgNumber("code.expressionlanguage.exceptions.BadArgNumber");
        _lgNames.getContent().getCoreNames().setAliasAbstractTypeErr("code.expressionlanguage.exceptions.AbstractTypeErr");
        _lgNames.getContent().getCoreNames().setAliasIllegalType("code.expressionlanguage.exceptions.IllegalType");
        _lgNames.getContent().getCoreNames().setAliasNonInvokable("code.expressionlanguage.exceptions.NonInvokable");
        _lgNames.getContent().getCoreNames().setAliasIllegalArg("code.expressionlanguage.exceptions.IllegalArgument");
        _lgNames.getContent().getCoreNames().setAliasBadSize("code.expressionlanguage.exceptions.NegativeSizeException");
        _lgNames.getContent().getCoreNames().setAliasError("java.lang.Exception");
        _lgNames.getContent().getCoreNames().setAliasErrorCurrentStack("current");
        _lgNames.getContent().getCoreNames().setAliasErrorToString("toString");
        _lgNames.getContent().getCoreNames().setAliasGetMessage("getMessage");
        _lgNames.getContent().getCoreNames().setAliasCastType("code.expressionlanguage.exceptions.DynamicCastClassException");
        _lgNames.getContent().getCoreNames().setAliasDivisionZero("code.expressionlanguage.exceptions.DivideZeroException");
        _lgNames.getContent().getMathRef().setAliasMath("java.lang.Math");
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
        _lgNames.getContent().getReflect().setAliasGetAnnotations("getAnnotations");
        _lgNames.getContent().getReflect().setAliasGetDefaultValue("getDefaultValue");
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
        _lgNames.getDisplayedStrings().setStaticCallString("staticCall");
        _lgNames.getDisplayedStrings().setStaticString("static");
        _lgNames.getDisplayedStrings().setInfinity("Infinity");
        _lgNames.getDisplayedStrings().setNan("Nan");
        _lgNames.getDisplayedStrings().setExponent("E");
    }
}
