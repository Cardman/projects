package code.expressionlanguage.stds;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.*;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.options.*;
import code.expressionlanguage.sample.CustLgNames;
import code.expressionlanguage.common.ParseLinesArgUtil;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public class LgNamesTest extends ProcessMethodCommon {
    @Test
    public void fail1Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordAbstract("");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail2Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordIntern("");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
        assertTrue(kw_.getKeyWordIntern().isEmpty());
    }
    @Test
    public void fail3Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordAbstract("<");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail4Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordAbstract("1a");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail5Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordEscBound("");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail6Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordEscBound("<");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }

    @Test
    public void fail7Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbHexEnd("");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail8Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbHexEnd("<");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail9Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbHexEnd("_");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail10Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbBin("");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail11Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbBin("<");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail12Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbBin("_");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail13Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbBin("0a");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail14Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbExpBin("");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail15Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbExpBin("<");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail16Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbExpBin("_");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail17Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbExpBin("1");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail18Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbExpBin("a");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail19Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbExpBin("A");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail20Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbHexEnd("");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail21Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbHexEnd("<");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail22Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbHexEnd("1");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail23Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbHexEnd("a");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail24Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbHexEnd("A");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail25Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getPrimTypes().setAliasPrimInteger("");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail26Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getPrimTypes().setAliasPrimInteger("<");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail27Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getPrimTypes().setAliasPrimInteger("$if");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail28Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getPrimTypes().setAliasPrimInteger("0a");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail29Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getNbAlias().setAliasInteger("$if");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail30Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getNbAlias().setAliasInteger("<");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail31Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getNbAlias().setAliasInteger("ab");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail32Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getNbAlias().setAliasInteger("0a");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail33Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getNbAlias().setAliasInteger("a..b");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail34Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getNbAlias().setAliasInteger("$int");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail35Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().setDefaultPkg("pkg");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail36Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getNbAlias().setAliasInteger("java");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail37Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getCharSeq().setAliasLength("");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail38Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getCharSeq().setAliasLength("<");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail39Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getCharSeq().setAliasLength("0a");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail40Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getCharSeq().setAliasLength("$if");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail41Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getCharSeq().setAliasLength("$int");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail42Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getNbAlias().setAliasMaxValueField("");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail43Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getNbAlias().setAliasMaxValueField("<");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail44Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getNbAlias().setAliasMaxValueField("0a");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail45Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getNbAlias().setAliasMaxValueField("$int");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail46Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getNbAlias().setAliasMaxValueField("$if");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail47Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordCase("$abstract");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        validateKeyWordDuplicates(kw_, s_, keyWords_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail48Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordEscBound("n");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        validateKeyWordDuplicates(kw_, s_, keyWords_);
        validateEscapingsDuplicates(kw_, s_, escapings_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail49Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordEscBound("nou");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        validateKeyWordDuplicates(kw_, s_, keyWords_);
        validateEscapingsDuplicates(kw_, s_, escapings_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail50Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordEscUnicode("");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        validateKeyWordDuplicates(kw_, s_, keyWords_);
        validateEscapingsDuplicates(kw_, s_, escapings_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail51Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordEscUnicode("f");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        validateKeyWordDuplicates(kw_, s_, keyWords_);
        validateEscapingsDuplicates(kw_, s_, escapings_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail52Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordEscBound("");
        kw_.setKeyWordEscUnicode("f0");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        validateKeyWordDuplicates(kw_, s_, keyWords_);
        validateEscapingsDuplicates(kw_, s_, escapings_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail53Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordEscUnicode("f0");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        validateKeyWordDuplicates(kw_, s_, keyWords_);
        validateEscapingsDuplicates(kw_, s_, escapings_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail54Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbSufLong("Y");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        validateKeyWordDuplicates(kw_, s_, keyWords_);
        validateEscapingsDuplicates(kw_, s_, escapings_);
        StringMap<String> nbWordsDec_ = kw_.allNbWords(kw_.allNbWordsDec());
        validateNbWordDuplicates(kw_, s_, nbWordsDec_);
        StringMap<String> nbWordsBin_ = kw_.allNbWords(kw_.allNbWordsBin());
        validateNbWordDuplicates(kw_, s_, nbWordsBin_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail55Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        kw_.setKeyWordNbBin("x");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        validateKeyWordDuplicates(kw_, s_, keyWords_);
        validateEscapingsDuplicates(kw_, s_, escapings_);
        StringMap<String> nbWordsDec_ = kw_.allNbWords(kw_.allNbWordsDec());
        validateNbWordDuplicates(kw_, s_, nbWordsDec_);
        StringMap<String> nbWordsBin_ = kw_.allNbWords(kw_.allNbWordsBin());
        validateNbWordDuplicates(kw_, s_, nbWordsBin_);
        validateStartsPrefixesDuplicates(kw_, s_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail56Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getPrimTypes().setAliasPrimBoolean("$byte");
        lgName_.getContent().getNbAlias().setAliasMaxValueField("MIN_VALUE");
        lgName_.getContent().getMathRef().setAliasLe("ge");
        lgName_.getContent().getNbAlias().setAliasBoolean("java.lang.Byte");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        validateKeyWordDuplicates(kw_, s_, keyWords_);
        validateEscapingsDuplicates(kw_, s_, escapings_);
        StringMap<String> nbWordsDec_ = kw_.allNbWords(kw_.allNbWordsDec());
        validateNbWordDuplicates(kw_, s_, nbWordsDec_);
        StringMap<String> nbWordsBin_ = kw_.allNbWords(kw_.allNbWordsBin());
        validateNbWordDuplicates(kw_, s_, nbWordsBin_);
        validateStartsPrefixesDuplicates(kw_, s_);
        validatePrimitiveDuplicates(s_, prims_);
        validateRefTypeDuplicates(s_, refTypes_);
        validateMethodsDuplicates(s_, methods_);
        validateFieldsDuplicates(s_, fields_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail57Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getNbAlias().setAliasInteger("<");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail58Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getNbAlias().setAliasInteger("");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail59Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getPredefTypes().setAliasIterableTableVarFirst(",E");
        lgName_.getContent().getPredefTypes().setAliasIterableTableVarSecond("0E");
        lgName_.getContent().getPredefTypes().setAliasEnumParamVar("");
        lgName_.getContent().getPredefTypes().setAliasIteratorTypeVar("$if");
        lgName_.getContent().getPredefTypes().setAliasIterableVar("$int");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        StringMap<CustList<KeyValueMemberName>> varTypes_ = allTableTypeVarTypes(s_);
        validateVarTypesContents(s_, prims_, varTypes_);
        validateKeyWordDuplicates(kw_, s_, keyWords_);
        validateEscapingsDuplicates(kw_, s_, escapings_);
        StringMap<String> nbWordsDec_ = kw_.allNbWords(kw_.allNbWordsDec());
        validateNbWordDuplicates(kw_, s_, nbWordsDec_);
        StringMap<String> nbWordsBin_ = kw_.allNbWords(kw_.allNbWordsBin());
        validateNbWordDuplicates(kw_, s_, nbWordsBin_);
        validateStartsPrefixesDuplicates(kw_, s_);
        validatePrimitiveDuplicates(s_, prims_);
        validateRefTypeDuplicates(s_, refTypes_);
        validateMethodsDuplicates(s_, methods_);
        validateFieldsDuplicates(s_, fields_);
        validateVarTypesDuplicates(s_, varTypes_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail60Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getPredefTypes().setAliasIterableTableVarFirst("E");
        lgName_.getContent().getPredefTypes().setAliasIterableTableVarSecond("E");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        StringMap<CustList<KeyValueMemberName>> varTypes_ = allTableTypeVarTypes(s_);
        validateVarTypesContents(s_, prims_, varTypes_);
        validateKeyWordDuplicates(kw_, s_, keyWords_);
        validateEscapingsDuplicates(kw_, s_, escapings_);
        StringMap<String> nbWordsDec_ = kw_.allNbWords(kw_.allNbWordsDec());
        validateNbWordDuplicates(kw_, s_, nbWordsDec_);
        StringMap<String> nbWordsBin_ = kw_.allNbWords(kw_.allNbWordsBin());
        validateNbWordDuplicates(kw_, s_, nbWordsBin_);
        validateStartsPrefixesDuplicates(kw_, s_);
        validatePrimitiveDuplicates(s_, prims_);
        validateRefTypeDuplicates(s_, refTypes_);
        validateMethodsDuplicates(s_, methods_);
        validateFieldsDuplicates(s_, fields_);
        validateVarTypesDuplicates(s_, varTypes_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail61Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getPredefTypes().getParams().setAliasSeedGenerator0Get0("");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        CustList<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodParamNames(s_);
        validateParamtersContents(s_, prims_, methods_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail62Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getPredefTypes().getParams().setAliasSeedGenerator0Get0("<");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        CustList<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodParamNames(s_);
        validateParamtersContents(s_, prims_, methods_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail63Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getPredefTypes().getParams().setAliasSeedGenerator0Get0("0a");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        CustList<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodParamNames(s_);
        validateParamtersContents(s_, prims_, methods_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail64Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getPredefTypes().getParams().setAliasSeedGenerator0Get0("$if");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        CustList<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodParamNames(s_);
        validateParamtersContents(s_, prims_, methods_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail65Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getPredefTypes().getParams().setAliasSeedGenerator0Get0("$int");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        CustList<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodParamNames(s_);
        validateParamtersContents(s_, prims_, methods_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail66Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        lgName_.getContent().getPredefTypes().setAliasHasNextPair("hasNext");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        StringMap<CustList<KeyValueMemberName>> varTypes_ = allTableTypeVarTypes(s_);
        validateVarTypesContents(s_, prims_, varTypes_);
        validateKeyWordDuplicates(kw_, s_, keyWords_);
        validateEscapingsDuplicates(kw_, s_, escapings_);
        StringMap<String> nbWordsDec_ = kw_.allNbWords(kw_.allNbWordsDec());
        validateNbWordDuplicates(kw_, s_, nbWordsDec_);
        StringMap<String> nbWordsBin_ = kw_.allNbWords(kw_.allNbWordsBin());
        validateNbWordDuplicates(kw_, s_, nbWordsBin_);
        validateStartsPrefixesDuplicates(kw_, s_);
        validatePrimitiveDuplicates(s_, prims_);
        validateRefTypeDuplicates(s_, refTypes_);
        validateMethodsDuplicates(s_, methods_);
        validateFieldsDuplicates(s_, fields_);
        validateVarTypesDuplicates(s_, varTypes_);
        CustList<CustList<KeyValueMemberName>> merge_ = allMergeTableTypeMethodNames(s_);
        validateMergedDuplicates(s_, merge_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail67Test() {
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        kw_.setKeyWordNbDig0("");
        kw_.setKeyWordNbDig1("0");
        kw_.setKeyWordNbDig2(",");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);

        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        validateKeyWordDuplicates(kw_, s_, keyWords_);
        validateEscapingsDuplicates(kw_, s_, escapings_);
        StringMap<String> nbWordsDec_ = kw_.allNbWords(kw_.allNbWordsDec());
        validateNbWordDuplicates(kw_, s_, nbWordsDec_);
        StringMap<String> nbWordsBin_ = kw_.allNbWords(kw_.allNbWordsBin());
        validateNbWordDuplicates(kw_, s_, nbWordsBin_);
        validateStartsPrefixesDuplicates(kw_, s_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    @Test
    public void fail68Test() {
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);

        kw_.setKeyWordNbDig0("F");
        kw_.setKeyWordNbDig1("F");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);

        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        validateKeyWordDuplicates(kw_, s_, keyWords_);
        validateEscapingsDuplicates(kw_, s_, escapings_);
        StringMap<String> nbWordsDec_ = kw_.allNbWords(kw_.allNbWordsDec());
        validateNbWordDuplicates(kw_, s_, nbWordsDec_);
        StringMap<String> nbWordsBin_ = kw_.allNbWords(kw_.allNbWordsBin());
        validateNbWordDuplicates(kw_, s_, nbWordsBin_);
        validateStartsPrefixesDuplicates(kw_, s_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }
    private static void validateEscapingsContents(KeyWords _kw, AnalyzedTestContext _s, StringMap<String> _escapings) {
        _kw.validateEscapingsContents(_escapings, _s.getAnalyzing());
    }

    private static void validateBinarySeparators(KeyWords _kw, AnalyzedTestContext _s) {
        _kw.validateBinarySeparators(_s.getAnalyzing());
    }

    private static void validateMethodsContents(AnalyzedTestContext _s, StringMap<String> _prims, StringMap<CustList<KeyValueMemberName>> _methods) {
        ValidatorStandard.validateMethodsContents(_methods, _prims, _s.getAnalyzing());
    }

    private static void validateFieldsContents(AnalyzedTestContext _s, StringMap<String> _prims, StringMap<CustList<KeyValueMemberName>> _fields) {
        ValidatorStandard.validateFieldsContents(_fields, _prims, _s.getAnalyzing());
    }

    private static void validateKeyWordDuplicates(KeyWords _kw, AnalyzedTestContext _s, StringMap<String> _keyWords) {
        _kw.validateKeyWordDuplicates(_keyWords, _s.getAnalyzing());
    }

    private static void validateNbWordDuplicates(KeyWords _kw, AnalyzedTestContext _s, StringMap<String> _nbWordsBin) {
        _kw.validateNbWordDuplicates(_nbWordsBin, _s.getAnalyzing());
    }

    private static void validatePrimitiveDuplicates(AnalyzedTestContext _s, StringMap<String> _prims) {
        ValidatorStandard.validatePrimitiveDuplicates(_prims, _s.getAnalyzing());
    }

    private static void validateMethodsDuplicates(AnalyzedTestContext _s, StringMap<CustList<KeyValueMemberName>> _methods) {
        ValidatorStandard.validateMethodsDuplicates(_methods, _s.getAnalyzing());
    }

    private static void validateVarTypesDuplicates(AnalyzedTestContext _s, StringMap<CustList<KeyValueMemberName>> _varTypes) {
        ValidatorStandard.validateVarTypesDuplicates(_varTypes, _s.getAnalyzing());
    }

    @Test
    public void fail69Test() {
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        lgName_.getCharSeq().getParams().setAliasCharSequence0SubSequence0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0SubSequence1("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0CharAt0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0Substring0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0Substring1("");
        lgName_.getCharSeq().getParams().setAliasCharSequence1Substring0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0CompareTo0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0Contains0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0StartsWith0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence1StartsWith0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence1StartsWith1("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0EndsWith0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0IndexOf0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence1IndexOf0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence1IndexOf1("");
        lgName_.getCharSeq().getParams().setAliasCharSequence2IndexOf0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence3IndexOf0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence3IndexOf1("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0LastIndexOf0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence1LastIndexOf0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence1LastIndexOf1("");
        lgName_.getCharSeq().getParams().setAliasCharSequence2LastIndexOf0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence3LastIndexOf0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence3LastIndexOf1("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0Format0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0Split0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence1Split0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence1Split1("");
        lgName_.getCharSeq().getParams().setAliasCharSequence2Split0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence3Split0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence3Split1("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0SplitStrings0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence1SplitStrings0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence1SplitStrings1("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0SplitChars0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0RegionMatches0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0RegionMatches1("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0RegionMatches2("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0RegionMatches3("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0Equals0("");
        lgName_.getCharSeq().getParams().setAliasCharSequence0Equals1("");
        lgName_.getCharSeq().getParams().setAliasString0EqualsIgnoreCase0("");
        lgName_.getCharSeq().getParams().setAliasString0Compare0("");
        lgName_.getCharSeq().getParams().setAliasString0Compare1("");
        lgName_.getCharSeq().getParams().setAliasString0CompareToIgnoreCase0("");
        lgName_.getCharSeq().getParams().setAliasString0ReplaceString0("");
        lgName_.getCharSeq().getParams().setAliasString0ReplaceString1("");
        lgName_.getCharSeq().getParams().setAliasString1ReplaceString0("");
        lgName_.getCharSeq().getParams().setAliasString1ReplaceString1("");
        lgName_.getCharSeq().getParams().setAliasString0ReplaceMultiple0("");
        lgName_.getCharSeq().getParams().setAliasString0RegionMatches0("");
        lgName_.getCharSeq().getParams().setAliasString0RegionMatches1("");
        lgName_.getCharSeq().getParams().setAliasString0RegionMatches2("");
        lgName_.getCharSeq().getParams().setAliasString0RegionMatches3("");
        lgName_.getCharSeq().getParams().setAliasString0RegionMatches4("");
        lgName_.getCharSeq().getParams().setAliasString0ValueOfMethod0("");
        lgName_.getCharSeq().getParams().setAliasString1ValueOfMethod0("");
        lgName_.getCharSeq().getParams().setAliasString2ValueOfMethod0("");
        lgName_.getCharSeq().getParams().setAliasString3ValueOfMethod0("");
        lgName_.getCharSeq().getParams().setAliasString4ValueOfMethod0("");
        lgName_.getCharSeq().getParams().setAliasString5ValueOfMethod0("");
        lgName_.getCharSeq().getParams().setAliasString6ValueOfMethod0("");
        lgName_.getCharSeq().getParams().setAliasString7ValueOfMethod0("");
        lgName_.getCharSeq().getParams().setAliasString8ValueOfMethod0("");
        lgName_.getCharSeq().getParams().setAliasString9ValueOfMethod0("");
        lgName_.getCharSeq().getParams().setAliasString9ValueOfMethod1("");
        lgName_.getCharSeq().getParams().setAliasString9ValueOfMethod2("");
        lgName_.getCharSeq().getParams().setAliasString0String0("");
        lgName_.getCharSeq().getParams().setAliasString1String0("");
        lgName_.getCharSeq().getParams().setAliasString1String1("");
        lgName_.getCharSeq().getParams().setAliasString1String2("");
        lgName_.getCharSeq().getParams().setAliasString2String0("");
        lgName_.getCharSeq().getParams().setAliasString3String0("");
        lgName_.getCharSeq().getParams().setAliasString3String1("");
        lgName_.getCharSeq().getParams().setAliasString3String2("");
        lgName_.getCharSeq().getParams().setAliasString4String0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder0Append0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder1Append0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder2Append0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder3Append0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder4Append0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder5Append0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder6Append0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder7Append0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder8Append0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder9Append0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder9Append1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder9Append2("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder10Append0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder11Append0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder11Append1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder11Append2("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder12Append0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder13Append0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder13Append1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder13Append2("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder0Delete0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder0Delete1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder0DeleteCharAt0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder0Insert0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder0Insert1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder1Insert0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder1Insert1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder2Insert0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder2Insert1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder3Insert0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder3Insert1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder4Insert0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder4Insert1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder5Insert0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder5Insert1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder6Insert0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder6Insert1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder7Insert0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder7Insert1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder8Insert0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder8Insert1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder9Insert0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder9Insert1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder9Insert2("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder9Insert3("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder10Insert0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder10Insert1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder11Insert0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder11Insert1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder11Insert2("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder11Insert3("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder12Insert0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder12Insert1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder13Insert0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder13Insert1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder13Insert2("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder13Insert3("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder0Replace0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder0Replace1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder0Replace2("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder0SetCharAt0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder0SetCharAt1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder0SetLength0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder0EnsureCapacity0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder0Same0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder0Same1("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder0StringBuilder0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder1StringBuilder0("");
        lgName_.getCharSeq().getParams().setAliasStringBuilder2StringBuilder0("");
        lgName_.getCharSeq().getParams().setAliasReplacement0Replacement0("");
        lgName_.getCharSeq().getParams().setAliasReplacement0Replacement1("");

        lgName_.getCoreNames().getParams().setAliasError0CurrentStack0("");
        lgName_.getCoreNames().getParams().setAliasError0ToStringMethod0("");
        lgName_.getCoreNames().getParams().setAliasEnums0Name0("");
        lgName_.getCoreNames().getParams().setAliasEnums0Ordinal0("");
        lgName_.getCoreNames().getParams().setAliasRange0Range0("");
        lgName_.getCoreNames().getParams().setAliasRange0Range1("");
        lgName_.getCoreNames().getParams().setAliasRange0UnlimitedStep0("");
        lgName_.getCoreNames().getParams().setAliasRange0UnlimitedStep1("");
        lgName_.getCoreNames().getParams().setAliasRange1Range0("");
        lgName_.getCoreNames().getParams().setAliasRange2Range0("");
        lgName_.getCoreNames().getParams().setAliasRange2Range1("");
        lgName_.getCoreNames().getParams().setAliasRange2Range2("");
        lgName_.getCoreNames().getParams().setAliasObjectsUtil0SameRef0("");
        lgName_.getCoreNames().getParams().setAliasObjectsUtil0SameRef1("");
        lgName_.getCoreNames().getParams().setAliasObjectsUtil0GetParent0("");
        lgName_.getCoreNames().getParams().setAliasObjectsUtil0GetFct0("");
        lgName_.getCoreNames().getParams().setAliasObjectsUtil0SetParent0("");
        lgName_.getCoreNames().getParams().setAliasObjectsUtil0SetParent1("");
        lgName_.getCoreNames().getParams().setAliasStringUtil0ValueOfMethod0("");
        lgName_.getCoreNames().getParams().setAliasResources0ReadResources0("");
        lgName_.getCoreNames().getParams().setAliasResources0ReadResourcesIndex0("");

        lgName_.getMathRef().getParams().setAliasMath0Abs0("");
        lgName_.getMathRef().getParams().setAliasMath1Abs0("");
        lgName_.getMathRef().getParams().setAliasMath2Abs0("");
        lgName_.getMathRef().getParams().setAliasMath3Abs0("");
        lgName_.getMathRef().getParams().setAliasMath0Max0("");
        lgName_.getMathRef().getParams().setAliasMath0Max1("");
        lgName_.getMathRef().getParams().setAliasMath1Max0("");
        lgName_.getMathRef().getParams().setAliasMath1Max1("");
        lgName_.getMathRef().getParams().setAliasMath0Min0("");
        lgName_.getMathRef().getParams().setAliasMath0Min1("");
        lgName_.getMathRef().getParams().setAliasMath1Min0("");
        lgName_.getMathRef().getParams().setAliasMath1Min1("");
        lgName_.getMathRef().getParams().setAliasMath2Max0("");
        lgName_.getMathRef().getParams().setAliasMath2Max1("");
        lgName_.getMathRef().getParams().setAliasMath3Max0("");
        lgName_.getMathRef().getParams().setAliasMath3Max1("");
        lgName_.getMathRef().getParams().setAliasMath2Min0("");
        lgName_.getMathRef().getParams().setAliasMath2Min1("");
        lgName_.getMathRef().getParams().setAliasMath3Min0("");
        lgName_.getMathRef().getParams().setAliasMath3Min1("");
        lgName_.getMathRef().getParams().setAliasMath0Quot0("");
        lgName_.getMathRef().getParams().setAliasMath0Quot1("");
        lgName_.getMathRef().getParams().setAliasMath1Quot0("");
        lgName_.getMathRef().getParams().setAliasMath1Quot1("");
        lgName_.getMathRef().getParams().setAliasMath0Mod0("");
        lgName_.getMathRef().getParams().setAliasMath0Mod1("");
        lgName_.getMathRef().getParams().setAliasMath1Mod0("");
        lgName_.getMathRef().getParams().setAliasMath1Mod1("");
        lgName_.getMathRef().getParams().setAliasMath0Plus0("");
        lgName_.getMathRef().getParams().setAliasMath1Plus0("");
        lgName_.getMathRef().getParams().setAliasMath2Plus0("");
        lgName_.getMathRef().getParams().setAliasMath3Plus0("");
        lgName_.getMathRef().getParams().setAliasMath0Minus0("");
        lgName_.getMathRef().getParams().setAliasMath1Minus0("");
        lgName_.getMathRef().getParams().setAliasMath2Minus0("");
        lgName_.getMathRef().getParams().setAliasMath3Minus0("");
        lgName_.getMathRef().getParams().setAliasMath0Neg0("");
        lgName_.getMathRef().getParams().setAliasMath0NegBin0("");
        lgName_.getMathRef().getParams().setAliasMath1NegBin0("");
        lgName_.getMathRef().getParams().setAliasMath4Plus0("");
        lgName_.getMathRef().getParams().setAliasMath4Plus1("");
        lgName_.getMathRef().getParams().setAliasMath5Plus0("");
        lgName_.getMathRef().getParams().setAliasMath5Plus1("");
        lgName_.getMathRef().getParams().setAliasMath6Plus0("");
        lgName_.getMathRef().getParams().setAliasMath6Plus1("");
        lgName_.getMathRef().getParams().setAliasMath7Plus0("");
        lgName_.getMathRef().getParams().setAliasMath7Plus1("");
        lgName_.getMathRef().getParams().setAliasMath4Minus0("");
        lgName_.getMathRef().getParams().setAliasMath4Minus1("");
        lgName_.getMathRef().getParams().setAliasMath5Minus0("");
        lgName_.getMathRef().getParams().setAliasMath5Minus1("");
        lgName_.getMathRef().getParams().setAliasMath6Minus0("");
        lgName_.getMathRef().getParams().setAliasMath6Minus1("");
        lgName_.getMathRef().getParams().setAliasMath7Minus0("");
        lgName_.getMathRef().getParams().setAliasMath7Minus1("");
        lgName_.getMathRef().getParams().setAliasMath0Mult0("");
        lgName_.getMathRef().getParams().setAliasMath0Mult1("");
        lgName_.getMathRef().getParams().setAliasMath1Mult0("");
        lgName_.getMathRef().getParams().setAliasMath1Mult1("");
        lgName_.getMathRef().getParams().setAliasMath2Mult0("");
        lgName_.getMathRef().getParams().setAliasMath2Mult1("");
        lgName_.getMathRef().getParams().setAliasMath3Mult0("");
        lgName_.getMathRef().getParams().setAliasMath3Mult1("");
        lgName_.getMathRef().getParams().setAliasMath0BinQuot0("");
        lgName_.getMathRef().getParams().setAliasMath0BinQuot1("");
        lgName_.getMathRef().getParams().setAliasMath1BinQuot0("");
        lgName_.getMathRef().getParams().setAliasMath1BinQuot1("");
        lgName_.getMathRef().getParams().setAliasMath2BinQuot0("");
        lgName_.getMathRef().getParams().setAliasMath2BinQuot1("");
        lgName_.getMathRef().getParams().setAliasMath3BinQuot0("");
        lgName_.getMathRef().getParams().setAliasMath3BinQuot1("");
        lgName_.getMathRef().getParams().setAliasMath0BinMod0("");
        lgName_.getMathRef().getParams().setAliasMath0BinMod1("");
        lgName_.getMathRef().getParams().setAliasMath1BinMod0("");
        lgName_.getMathRef().getParams().setAliasMath1BinMod1("");
        lgName_.getMathRef().getParams().setAliasMath2BinMod0("");
        lgName_.getMathRef().getParams().setAliasMath2BinMod1("");
        lgName_.getMathRef().getParams().setAliasMath3BinMod0("");
        lgName_.getMathRef().getParams().setAliasMath3BinMod1("");
        lgName_.getMathRef().getParams().setAliasMath0And0("");
        lgName_.getMathRef().getParams().setAliasMath0And1("");
        lgName_.getMathRef().getParams().setAliasMath1And0("");
        lgName_.getMathRef().getParams().setAliasMath1And1("");
        lgName_.getMathRef().getParams().setAliasMath2And0("");
        lgName_.getMathRef().getParams().setAliasMath2And1("");
        lgName_.getMathRef().getParams().setAliasMath0Or0("");
        lgName_.getMathRef().getParams().setAliasMath0Or1("");
        lgName_.getMathRef().getParams().setAliasMath1Or0("");
        lgName_.getMathRef().getParams().setAliasMath1Or1("");
        lgName_.getMathRef().getParams().setAliasMath2Or0("");
        lgName_.getMathRef().getParams().setAliasMath2Or1("");
        lgName_.getMathRef().getParams().setAliasMath0Xor0("");
        lgName_.getMathRef().getParams().setAliasMath0Xor1("");
        lgName_.getMathRef().getParams().setAliasMath1Xor0("");
        lgName_.getMathRef().getParams().setAliasMath1Xor1("");
        lgName_.getMathRef().getParams().setAliasMath2Xor0("");
        lgName_.getMathRef().getParams().setAliasMath2Xor1("");
        lgName_.getMathRef().getParams().setAliasMath0ShiftLeft0("");
        lgName_.getMathRef().getParams().setAliasMath0ShiftLeft1("");
        lgName_.getMathRef().getParams().setAliasMath1ShiftLeft0("");
        lgName_.getMathRef().getParams().setAliasMath1ShiftLeft1("");
        lgName_.getMathRef().getParams().setAliasMath0ShiftRight0("");
        lgName_.getMathRef().getParams().setAliasMath0ShiftRight1("");
        lgName_.getMathRef().getParams().setAliasMath1ShiftRight0("");
        lgName_.getMathRef().getParams().setAliasMath1ShiftRight1("");
        lgName_.getMathRef().getParams().setAliasMath0BitShiftLeft0("");
        lgName_.getMathRef().getParams().setAliasMath0BitShiftLeft1("");
        lgName_.getMathRef().getParams().setAliasMath1BitShiftLeft0("");
        lgName_.getMathRef().getParams().setAliasMath1BitShiftLeft1("");
        lgName_.getMathRef().getParams().setAliasMath0BitShiftRight0("");
        lgName_.getMathRef().getParams().setAliasMath0BitShiftRight1("");
        lgName_.getMathRef().getParams().setAliasMath1BitShiftRight0("");
        lgName_.getMathRef().getParams().setAliasMath1BitShiftRight1("");
        lgName_.getMathRef().getParams().setAliasMath0RotateLeft0("");
        lgName_.getMathRef().getParams().setAliasMath0RotateLeft1("");
        lgName_.getMathRef().getParams().setAliasMath1RotateLeft0("");
        lgName_.getMathRef().getParams().setAliasMath1RotateLeft1("");
        lgName_.getMathRef().getParams().setAliasMath0RotateRight0("");
        lgName_.getMathRef().getParams().setAliasMath0RotateRight1("");
        lgName_.getMathRef().getParams().setAliasMath1RotateRight0("");
        lgName_.getMathRef().getParams().setAliasMath1RotateRight1("");
        lgName_.getMathRef().getParams().setAliasMath0Le0("");
        lgName_.getMathRef().getParams().setAliasMath0Le1("");
        lgName_.getMathRef().getParams().setAliasMath0Ge0("");
        lgName_.getMathRef().getParams().setAliasMath0Ge1("");
        lgName_.getMathRef().getParams().setAliasMath0Lt0("");
        lgName_.getMathRef().getParams().setAliasMath0Lt1("");
        lgName_.getMathRef().getParams().setAliasMath0Gt0("");
        lgName_.getMathRef().getParams().setAliasMath0Gt1("");
        lgName_.getMathRef().getParams().setAliasMath1Le0("");
        lgName_.getMathRef().getParams().setAliasMath1Le1("");
        lgName_.getMathRef().getParams().setAliasMath1Ge0("");
        lgName_.getMathRef().getParams().setAliasMath1Ge1("");
        lgName_.getMathRef().getParams().setAliasMath1Lt0("");
        lgName_.getMathRef().getParams().setAliasMath1Lt1("");
        lgName_.getMathRef().getParams().setAliasMath1Gt0("");
        lgName_.getMathRef().getParams().setAliasMath1Gt1("");
        lgName_.getMathRef().getParams().setAliasMath0Random0("");
        lgName_.getMathRef().getParams().setAliasMath0NativeRandom0("");
        lgName_.getMathRef().getParams().setAliasMath0Seed0("");
        lgName_.getMathRef().getParams().setAliasMath0SeedSpecGenerator0("");
        lgName_.getMathRef().getParams().setAliasMath0SeedSpecDoubleGenerator0("");
        lgName_.getMathRef().getParams().setAliasMath0Eval0("");
        lgName_.getMathRef().getParams().setAliasMath0Eval1("");

        lgName_.getNbAlias().getParams().setAliasBoolean0Compare0("");
        lgName_.getNbAlias().getParams().setAliasBoolean0Compare1("");
        lgName_.getNbAlias().getParams().setAliasBoolean0CompareTo0("");
        lgName_.getNbAlias().getParams().setAliasBoolean0Equals0("");
        lgName_.getNbAlias().getParams().setAliasBoolean0ParseBoolean0("");
        lgName_.getNbAlias().getParams().setAliasBoolean0ToStringMethod0("");
        lgName_.getNbAlias().getParams().setAliasBoolean0ValueOfMethod0("");
        lgName_.getNbAlias().getParams().setAliasBoolean1ValueOfMethod0("");
        lgName_.getNbAlias().getParams().setAliasBoolean0Boolean0("");
        lgName_.getNbAlias().getParams().setAliasBoolean1Boolean0("");
        lgName_.getNbAlias().getParams().setAliasByte0ToStringMethod0("");
        lgName_.getNbAlias().getParams().setAliasByte0ParseByte0("");
        lgName_.getNbAlias().getParams().setAliasByte1ParseByte0("");
        lgName_.getNbAlias().getParams().setAliasByte1ParseByte1("");
        lgName_.getNbAlias().getParams().setAliasByte0CompareTo0("");
        lgName_.getNbAlias().getParams().setAliasByte0Compare0("");
        lgName_.getNbAlias().getParams().setAliasByte0Compare1("");
        lgName_.getNbAlias().getParams().setAliasByte0ParseByteOrNull0("");
        lgName_.getNbAlias().getParams().setAliasByte1ParseByteOrNull0("");
        lgName_.getNbAlias().getParams().setAliasByte1ParseByteOrNull1("");
        lgName_.getNbAlias().getParams().setAliasByte0Byte0("");
        lgName_.getNbAlias().getParams().setAliasByte1Byte0("");
        lgName_.getNbAlias().getParams().setAliasShort0ToStringMethod0("");
        lgName_.getNbAlias().getParams().setAliasShort0ParseShort0("");
        lgName_.getNbAlias().getParams().setAliasShort1ParseShort0("");
        lgName_.getNbAlias().getParams().setAliasShort1ParseShort1("");
        lgName_.getNbAlias().getParams().setAliasShort0CompareTo0("");
        lgName_.getNbAlias().getParams().setAliasShort0Compare0("");
        lgName_.getNbAlias().getParams().setAliasShort0Compare1("");
        lgName_.getNbAlias().getParams().setAliasShort0ParseShortOrNull0("");
        lgName_.getNbAlias().getParams().setAliasShort1ParseShortOrNull0("");
        lgName_.getNbAlias().getParams().setAliasShort1ParseShortOrNull1("");
        lgName_.getNbAlias().getParams().setAliasShort0Short0("");
        lgName_.getNbAlias().getParams().setAliasShort1Short0("");
        lgName_.getNbAlias().getParams().setAliasInteger0ToStringMethod0("");
        lgName_.getNbAlias().getParams().setAliasInteger0ParseInt0("");
        lgName_.getNbAlias().getParams().setAliasInteger1ParseInt0("");
        lgName_.getNbAlias().getParams().setAliasInteger1ParseInt1("");
        lgName_.getNbAlias().getParams().setAliasInteger0CompareTo0("");
        lgName_.getNbAlias().getParams().setAliasInteger0Compare0("");
        lgName_.getNbAlias().getParams().setAliasInteger0Compare1("");
        lgName_.getNbAlias().getParams().setAliasInteger0ParseIntOrNull0("");
        lgName_.getNbAlias().getParams().setAliasInteger1ParseIntOrNull0("");
        lgName_.getNbAlias().getParams().setAliasInteger1ParseIntOrNull1("");
        lgName_.getNbAlias().getParams().setAliasInteger0Integer0("");
        lgName_.getNbAlias().getParams().setAliasInteger1Integer0("");
        lgName_.getNbAlias().getParams().setAliasLong0ToStringMethod0("");
        lgName_.getNbAlias().getParams().setAliasLong1ToStringMethod0("");
        lgName_.getNbAlias().getParams().setAliasLong1ToStringMethod1("");
        lgName_.getNbAlias().getParams().setAliasLong0Signum0("");
        lgName_.getNbAlias().getParams().setAliasLong0ParseLong0("");
        lgName_.getNbAlias().getParams().setAliasLong1ParseLong0("");
        lgName_.getNbAlias().getParams().setAliasLong1ParseLong1("");
        lgName_.getNbAlias().getParams().setAliasLong0CompareTo0("");
        lgName_.getNbAlias().getParams().setAliasLong0Compare0("");
        lgName_.getNbAlias().getParams().setAliasLong0Compare1("");
        lgName_.getNbAlias().getParams().setAliasLong0ParseLongOrNull0("");
        lgName_.getNbAlias().getParams().setAliasLong1ParseLongOrNull0("");
        lgName_.getNbAlias().getParams().setAliasLong1ParseLongOrNull1("");
        lgName_.getNbAlias().getParams().setAliasByte0ToBinString0("");
        lgName_.getNbAlias().getParams().setAliasByte0ToOctString0("");
        lgName_.getNbAlias().getParams().setAliasByte0ToHexString0("");
        lgName_.getNbAlias().getParams().setAliasShort0ToBinString0("");
        lgName_.getNbAlias().getParams().setAliasShort0ToOctString0("");
        lgName_.getNbAlias().getParams().setAliasShort0ToHexString0("");
        lgName_.getNbAlias().getParams().setAliasInteger0ToBinString0("");
        lgName_.getNbAlias().getParams().setAliasInteger0ToOctString0("");
        lgName_.getNbAlias().getParams().setAliasInteger0ToHexString0("");
        lgName_.getNbAlias().getParams().setAliasLong0ToBinString0("");
        lgName_.getNbAlias().getParams().setAliasLong0ToOctString0("");
        lgName_.getNbAlias().getParams().setAliasLong0ToHexString0("");
        lgName_.getNbAlias().getParams().setAliasLong0Long0("");
        lgName_.getNbAlias().getParams().setAliasLong1Long0("");
        lgName_.getNbAlias().getParams().setAliasFloat0ToStringMethod0("");
        lgName_.getNbAlias().getParams().setAliasFloat0ParseFloat0("");
        lgName_.getNbAlias().getParams().setAliasFloat0CompareTo0("");
        lgName_.getNbAlias().getParams().setAliasFloat0Compare0("");
        lgName_.getNbAlias().getParams().setAliasFloat0Compare1("");
        lgName_.getNbAlias().getParams().setAliasFloat0ParseFloatOrNull0("");
        lgName_.getNbAlias().getParams().setAliasFloat0IsInfinite0("");
        lgName_.getNbAlias().getParams().setAliasFloat0IsNan0("");
        lgName_.getNbAlias().getParams().setAliasFloat0Float0("");
        lgName_.getNbAlias().getParams().setAliasFloat1Float0("");
        lgName_.getNbAlias().getParams().setAliasDouble0ToStringMethod0("");
        lgName_.getNbAlias().getParams().setAliasDouble0ParseDouble0("");
        lgName_.getNbAlias().getParams().setAliasDouble0CompareTo0("");
        lgName_.getNbAlias().getParams().setAliasDouble0Compare0("");
        lgName_.getNbAlias().getParams().setAliasDouble0Compare1("");
        lgName_.getNbAlias().getParams().setAliasDouble0ParseDoubleOrNull0("");
        lgName_.getNbAlias().getParams().setAliasDouble0IsInfinite0("");
        lgName_.getNbAlias().getParams().setAliasDouble0IsNan0("");
        lgName_.getNbAlias().getParams().setAliasDouble0Double0("");
        lgName_.getNbAlias().getParams().setAliasDouble1Double0("");
        lgName_.getNbAlias().getParams().setAliasNumber0ToStringMethod0("");
        lgName_.getNbAlias().getParams().setAliasNumber0Equals0("");
        lgName_.getNbAlias().getParams().setAliasNumber1Equals0("");
        lgName_.getNbAlias().getParams().setAliasNumber1Equals1("");
        lgName_.getNbAlias().getParams().setAliasNumber0CompareTo0("");
        lgName_.getNbAlias().getParams().setAliasNumber0Compare0("");
        lgName_.getNbAlias().getParams().setAliasNumber0Compare1("");
        lgName_.getNbAlias().getParams().setAliasCharacter0CompareTo0("");
        lgName_.getNbAlias().getParams().setAliasCharacter0Compare0("");
        lgName_.getNbAlias().getParams().setAliasCharacter0Compare1("");
        lgName_.getNbAlias().getParams().setAliasCharacter0Digit0("");
        lgName_.getNbAlias().getParams().setAliasCharacter0Digit1("");
        lgName_.getNbAlias().getParams().setAliasCharacter0ForDigit0("");
        lgName_.getNbAlias().getParams().setAliasCharacter0ForDigit1("");
        lgName_.getNbAlias().getParams().setAliasCharacter0GetDirectionality0("");
        lgName_.getNbAlias().getParams().setAliasCharacter0GetType0("");
        lgName_.getNbAlias().getParams().setAliasCharacter0IsDigit0("");
        lgName_.getNbAlias().getParams().setAliasCharacter0IsLetter0("");
        lgName_.getNbAlias().getParams().setAliasCharacter0IsLetterOrDigit0("");
        lgName_.getNbAlias().getParams().setAliasCharacter0IsWordChar0("");
        lgName_.getNbAlias().getParams().setAliasCharacter0IsWhitespace0("");
        lgName_.getNbAlias().getParams().setAliasCharacter0IsLowerCase0("");
        lgName_.getNbAlias().getParams().setAliasCharacter0IsUpperCase0("");
        lgName_.getNbAlias().getParams().setAliasCharacter0IsSpace0("");
        lgName_.getNbAlias().getParams().setAliasCharacter0ToLowerCaseChar0("");
        lgName_.getNbAlias().getParams().setAliasCharacter0ToUpperCaseChar0("");
        lgName_.getNbAlias().getParams().setAliasCharacter0ToStringMethod0("");
        lgName_.getNbAlias().getParams().setAliasCharacter0Character0("");


        lgName_.getReflect().getParams().setAliasFct0Call0("");
        lgName_.getReflect().getParams().setAliasClassType0GetClass0("");
        lgName_.getReflect().getParams().setAliasClassType0ForName0("");
        lgName_.getReflect().getParams().setAliasClassType0ForName1("");
        lgName_.getReflect().getParams().setAliasClassType1ForName0("");
        lgName_.getReflect().getParams().setAliasClassType0IsInstance0("");
        lgName_.getReflect().getParams().setAliasClassType0TryWrap0("");
        lgName_.getReflect().getParams().setAliasClassType0IsAssignableFrom0("");
        lgName_.getReflect().getParams().setAliasClassType0DefaultInstance0("");
        lgName_.getReflect().getParams().setAliasClassType0EnumValueOf0("");
        lgName_.getReflect().getParams().setAliasClassType0GetDeclaredConstructors0("");
        lgName_.getReflect().getParams().setAliasClassType0GetDeclaredConstructors1("");
        lgName_.getReflect().getParams().setAliasClassType0GetDeclaredFields0("");
        lgName_.getReflect().getParams().setAliasClassType0GetDeclaredStaticMethods0("");
        lgName_.getReflect().getParams().setAliasClassType0GetDeclaredStaticMethods1("");
        lgName_.getReflect().getParams().setAliasClassType0GetDeclaredStaticMethods2("");
        lgName_.getReflect().getParams().setAliasClassType0GetDeclaredStaticMethods3("");
        lgName_.getReflect().getParams().setAliasClassType0GetDeclaredMethods0("");
        lgName_.getReflect().getParams().setAliasClassType0GetDeclaredMethods1("");
        lgName_.getReflect().getParams().setAliasClassType0GetDeclaredMethods2("");
        lgName_.getReflect().getParams().setAliasClassType0GetDeclaredMethods3("");
        lgName_.getReflect().getParams().setAliasClassType0GetDeclaredExplicits0("");
        lgName_.getReflect().getParams().setAliasClassType0GetDeclaredImplicits0("");
        lgName_.getReflect().getParams().setAliasClassType0GetDeclaredBlocks0("");
        lgName_.getReflect().getParams().setAliasClassType0GetDeclaredBlocks1("");
        lgName_.getReflect().getParams().setAliasClassType0MakeGeneric0("");
        lgName_.getReflect().getParams().setAliasClassType0MakeWildCard0("");
        lgName_.getReflect().getParams().setAliasClassType0MakeRef0("");
        lgName_.getReflect().getParams().setAliasClassType0GetOperators0("");
        lgName_.getReflect().getParams().setAliasClassType0GetOperators1("");
        lgName_.getReflect().getParams().setAliasClassType0GetOperators2("");
        lgName_.getReflect().getParams().setAliasClassType0ArrayNewInstance0("");
        lgName_.getReflect().getParams().setAliasClassType0ArrayGetLength0("");
        lgName_.getReflect().getParams().setAliasClassType0ArrayGet0("");
        lgName_.getReflect().getParams().setAliasClassType0ArrayGet1("");
        lgName_.getReflect().getParams().setAliasClassType0ArraySet0("");
        lgName_.getReflect().getParams().setAliasClassType0ArraySet1("");
        lgName_.getReflect().getParams().setAliasClassType0ArraySet2("");
        lgName_.getReflect().getParams().setAliasConstructor0NewInstance0("");
        lgName_.getReflect().getParams().setAliasField0GetField0("");
        lgName_.getReflect().getParams().setAliasField0SetField0("");
        lgName_.getReflect().getParams().setAliasField0SetField1("");
        lgName_.getReflect().getParams().setAliasMethod0Invoke0("");
        lgName_.getReflect().getParams().setAliasMethod0Invoke1("");
        lgName_.getReflect().getParams().setAliasMethod0InvokeDirect0("");
        lgName_.getReflect().getParams().setAliasMethod0InvokeDirect1("");
        lgName_.getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLocalVarsNb0("");
        lgName_.getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLocalVars0("");
        lgName_.getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLocalVars1("");
        lgName_.getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLocalVars2("");
        lgName_.getReflect().getParams().setAliasMethod1GetDeclaredAnonymousLambdaLocalVars0("");
        lgName_.getReflect().getParams().setAliasMethod1GetDeclaredAnonymousLambdaLocalVars1("");
        lgName_.getReflect().getParams().setAliasMethod2GetDeclaredAnonymousLambdaLocalVars0("");
        lgName_.getReflect().getParams().setAliasMethod2GetDeclaredAnonymousLambdaLocalVars1("");
        lgName_.getReflect().getParams().setAliasMethod3GetDeclaredAnonymousLambdaLocalVars0("");
        lgName_.getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLoopVars0("");
        lgName_.getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLoopVars1("");
        lgName_.getReflect().getParams().setAliasMethod0GetDeclaredAnonymousLambdaLoopVars2("");
        lgName_.getReflect().getParams().setAliasMethod1GetDeclaredAnonymousLambdaLoopVars0("");
        lgName_.getReflect().getParams().setAliasMethod1GetDeclaredAnonymousLambdaLoopVars1("");
        lgName_.getReflect().getParams().setAliasMethod2GetDeclaredAnonymousLambdaLoopVars0("");
        lgName_.getReflect().getParams().setAliasMethod2GetDeclaredAnonymousLambdaLoopVars1("");
        lgName_.getReflect().getParams().setAliasMethod3GetDeclaredAnonymousLambdaLoopVars0("");
        lgName_.getReflect().getParams().setAliasAnnotationType0GetString0("");
        lgName_.getReflect().getParams().setAliasAnnotated0GetAnnotations0("");
        lgName_.getReflect().getParams().setAliasAnnotated0GetAnnotationsParameters0("");
        lgName_.getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda0("");
        lgName_.getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda1("");
        lgName_.getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda2("");
        lgName_.getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda3("");
        lgName_.getReflect().getParams().setAliasAnnotated0GetDeclaredSwitchMethods0("");
        lgName_.getReflect().getParams().setAliasAnnotated0GetDeclaredSwitchMethods1("");
        lgName_.getReflect().getParams().setAliasAnnotated0GetDeclaredSwitchMethods2("");
        lgName_.getReflect().getParams().setAliasAnnotated0GetDeclaredSwitchMethods3("");
        AnalyzedTestContext s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = allRefTypes(s_);
        validateRefTypeContents(s_, prims_, refTypes_);
        StringMap<CustList<KeyValueMemberName>> methods_ = allTableTypeMethodNames(s_);
        validateMethodsContents(s_, prims_, methods_);
        CustList<CustList<KeyValueMemberName>> params_ = allTableTypeMethodParamNames(s_);
        validateParamtersContents(s_, prims_, params_);
        StringMap<CustList<KeyValueMemberName>> fields_ = allTableTypeFieldNames(s_);
        validateFieldsContents(s_, prims_, fields_);
        StringMap<CustList<KeyValueMemberName>> varTypes_ = allTableTypeVarTypes(s_);
        validateVarTypesContents(s_, prims_, varTypes_);
        validateKeyWordDuplicates(kw_, s_, keyWords_);
        validateEscapingsDuplicates(kw_, s_, escapings_);
        StringMap<String> nbWordsDec_ = kw_.allNbWords(kw_.allNbWordsDec());
        validateNbWordDuplicates(kw_, s_, nbWordsDec_);
        StringMap<String> nbWordsBin_ = kw_.allNbWords(kw_.allNbWordsBin());
        validateNbWordDuplicates(kw_, s_, nbWordsBin_);
        validateStartsPrefixesDuplicates(kw_, s_);
        validatePrimitiveDuplicates(s_, prims_);
        validateRefTypeDuplicates(s_, refTypes_);
        validateMethodsDuplicates(s_, methods_);
        validateParamtersDuplicates(s_, params_);
        validateFieldsDuplicates(s_, fields_);
        validateVarTypesDuplicates(s_, varTypes_);
        CustList<CustList<KeyValueMemberName>> merge_ = allMergeTableTypeMethodNames(s_);
        validateMergedDuplicates(s_, merge_);
        assertTrue(s_.getAnalyzing().getMessages().displayStdErrors(),!s_.getAnalyzing().isEmptyStdError());
    }

    private static void validateParamtersDuplicates(AnalyzedTestContext _s, CustList<CustList<KeyValueMemberName>> _params) {
        ValidatorStandard.validateParamtersDuplicates(_params, _s.getAnalyzing());
    }

    private static void validateKeyWordContents(KeyWords _kw, AnalyzedTestContext _s, StringMap<String> _keyWords) {
        _kw.validateKeyWordContents(_keyWords, _s.getAnalyzing());
    }

    private static void validateNbWordContents(KeyWords _kw, AnalyzedTestContext _s, StringMap<String> _nbWords) {
        _kw.validateNbWordContents(_nbWords, _s.getAnalyzing());
    }

    private static void validatePrimitiveContents(AnalyzedTestContext _s, StringMap<String> _prims) {
        ValidatorStandard.validatePrimitiveContents(_prims, _s.getAnalyzing());
    }

    private static void validateRefTypeContents(AnalyzedTestContext _s, StringMap<String> _prims, StringMap<String> _refTypes) {
        ValidatorStandard.validateRefTypeContents(_refTypes, _prims, _s.getAnalyzing());
    }

    private static void validateParamtersContents(AnalyzedTestContext _s, StringMap<String> _prims, CustList<CustList<KeyValueMemberName>> _params) {
        ValidatorStandard.validateParamtersContents(_params, _prims, _s.getAnalyzing());
    }

    private static void validateVarTypesContents(AnalyzedTestContext _s, StringMap<String> _prims, StringMap<CustList<KeyValueMemberName>> _varTypes) {
        ValidatorStandard.validateVarTypesContents(_varTypes, _prims, _s.getAnalyzing());
    }

    private static void validateEscapingsDuplicates(KeyWords _kw, AnalyzedTestContext _s, StringMap<String> _escapings) {
        _kw.validateEscapingsDuplicates(_escapings, _s.getAnalyzing());
    }

    private static void validateStartsPrefixesDuplicates(KeyWords _kw, AnalyzedTestContext _s) {
        _kw.validateStartsPrefixesDuplicates(_s.getAnalyzing());
    }

    private static void validateRefTypeDuplicates(AnalyzedTestContext _s, StringMap<String> _refTypes) {
        ValidatorStandard.validateRefTypeDuplicates(_refTypes, _s.getAnalyzing());
    }

    private static void validateFieldsDuplicates(AnalyzedTestContext _s, StringMap<CustList<KeyValueMemberName>> _fields) {
        ValidatorStandard.validateFieldsDuplicates(_fields, _s.getAnalyzing());
    }

    private static void validateMergedDuplicates(AnalyzedTestContext _s, CustList<CustList<KeyValueMemberName>> _merge) {
        ValidatorStandard.validateMergedDuplicates(_merge, _s.getAnalyzing());
    }
    @Test
    public void success1Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return Resources.readNames().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> srcFiles_ = new StringMap<String>();
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        srcFiles_.put("src/Ex", xml_.toString());
        StringMap<String> others_ = new StringMap<String>();
        others_.put("pkg/hello_res.txt", "content");
        StringMap<String> all_ = new StringMap<String>();
        all_.putAllMap(srcFiles_);
        all_.putAllMap(others_);
        Options options_ = new Options();
        AbstractConstantsCalculator calculator_ = new DefaultConstantsCalculator(lgName_.getNbAlias());
        AnalyzedTestContext contextEl_ = getCtx(kw_, lgName_, options_, calculator_);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        Forwards forwards_ = contextEl_.getForwards();
        ContextFactory.validateStds(forwards_,contextEl_.getAnalyzing().getAnalysisMessages(), kw_, new CustList<CommentDelimiters>(), options_, lgName_.getContent(), page_);
        ContextEl ctx_ = ContextFactory.addResourcesAndValidate(all_, "src", page_, forwards_);
        assertTrue(isEmptyErrors(contextEl_));
        MethodId fct_ = new MethodId(MethodAccessKind.STATIC, "exmeth",new StringList());
        Argument argGlLoc_ = new Argument();
        ExecRootBlock classBody_ = ctx_.getClasses().getClassBody("pkg.Ex");
        ExecOverridableBlock method_ = getDeepMethodBodiesById(ctx_, "pkg.Ex", fct_).first();
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,ctx_);
        Argument ret_ = ProcessMethod.calculate(new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, "pkg.Ex"), new ExecTypeFunction(classBody_, method_), new Parameters()), ctx_, stackCall_).getValue();
        assertNull(stackCall_.getCallingState());
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void success2Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return Resources.readNames().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> srcFiles_ = new StringMap<String>();
        
        
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        
        srcFiles_.put("src/Ex", xml_.toString());
        StringMap<String> others_ = new StringMap<String>();
        others_.put("pkg/hello_res.txt", "content");
        StringMap<String> all_ = new StringMap<String>();
        all_.putAllMap(srcFiles_);
        all_.putAllMap(others_);
        Options options_ = new Options();
        AbstractConstantsCalculator calculator_ = new DefaultConstantsCalculator(lgName_.getNbAlias());
        AnalyzedTestContext contextEl_ = getCtx(kw_, lgName_, options_, calculator_);
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        Forwards forwards_ = contextEl_.getForwards();
        ContextFactory.validateStds(forwards_,contextEl_.getAnalyzing().getAnalysisMessages(), kw_, new CustList<CommentDelimiters>(), options_, lgName_.getContent(), page_);
        ContextEl ctx_ = ContextFactory.addResourcesAndValidate(all_, "src", page_, forwards_);
        assertTrue(isEmptyErrors(contextEl_));
        MethodId fct_ = new MethodId(MethodAccessKind.STATIC, "exmeth",new StringList());
        Argument argGlLoc_ = new Argument();
        ExecRootBlock classBody_ = ctx_.getClasses().getClassBody("pkg.Ex");
        ExecOverridableBlock method_ = getDeepMethodBodiesById(ctx_, "pkg.Ex", fct_).first();
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,ctx_);
        Argument ret_ = ProcessMethod.calculate(new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, "pkg.Ex"), new ExecTypeFunction(classBody_, method_), new Parameters()), ctx_, stackCall_).getValue();
        assertNull(stackCall_.getCallingState());
        assertEq(2, getNumber(ret_));
    }

    private static AnalyzedTestContext getCtx(KeyWords _kw, LgNames _lgName, Options _options, AbstractConstantsCalculator _calculator) {
        AnalysisMessages mess_ = new AnalysisMessages();
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setAnalysisMessages(mess_);
        page_.setKeyWords(_kw);
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(_lgName.getContent());
        page_.setFileBuilder(fileBuilder_);
        page_.setStandards(_lgName.getContent());
        page_.setLogErr(_lgName);
        page_.setCalculator(_calculator);
        AnalysisMessages.validateMessageContents(mess_.allMessages(), page_);
        assertTrue(page_.isEmptyMessageError());
        return new AnalyzedTestContext(page_, new Forwards(_lgName,fileBuilder_, _options),_lgName);
    }

    private static StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(AnalyzedTestContext _lgName) {
        return _lgName.getAnalyzing().getFileBuilder().getDefaultAliasGroups().allTableTypeMethodNames();
    }

    private static CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(AnalyzedTestContext _lgName) {
        return _lgName.getAnalyzing().getFileBuilder().getDefaultAliasGroups().allTableTypeMethodParamNames();
    }

    private static StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes(AnalyzedTestContext _lgName) {
        return _lgName.getAnalyzing().getFileBuilder().getDefaultAliasGroups().allTableTypeVarTypes();
    }

    private static StringMap<String> allPrimitives(AnalyzedTestContext _lgName) {
        return _lgName.getAnalyzing().getFileBuilder().getDefaultAliasGroups().allPrimitives();
    }

    private static StringMap<String> allRefTypes(AnalyzedTestContext _lgName) {
        return _lgName.getAnalyzing().getFileBuilder().getDefaultAliasGroups().allRefTypes();
    }

    private static CustList<CustList<KeyValueMemberName>> allMergeTableTypeMethodNames(AnalyzedTestContext _lgName) {
        return _lgName.getAnalyzing().getFileBuilder().getDefaultAliasGroups().allMergeTableTypeMethodNames();
    }

    private static StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames(AnalyzedTestContext _lgName) {
        return _lgName.getAnalyzing().getFileBuilder().getDefaultAliasGroups().allTableTypeFieldNames();
    }


    private static AnalyzedTestContext getCtx(KeyWords _kw, LgNames _lgName) {
        return getCtx(_kw, _lgName, new Options(), null);
    }
    @Test
    public void getAlias1() {
        StringMap<String> def_ = new StringMap<String>();
        def_.put("key","value");
        assertEq("value",LgNamesContent.get(def_,new StringMap<String>(),"key"));
    }
    @Test
    public void getAlias2() {
        StringMap<String> def_ = new StringMap<String>();
        def_.put("key","value");
        StringMap<String> cust_ = new StringMap<String>();
        cust_.put("key","value2");
        assertEq("value2",LgNamesContent.get(def_, cust_,"key"));
    }
    @Test
    public void getAlias3() {
        StringMap<String> def_ = new StringMap<String>();
        StringMap<String> cust_ = new StringMap<String>();
        assertEq("",LgNamesContent.get(def_, cust_,"key"));
    }
    @Test
    public void getAlias4() {
        StringMap<String> def_ = new StringMap<String>();
        def_.put("","value");
        StringMap<String> cust_ = new StringMap<String>();
        cust_.put("","value");
        LgNamesContent lgNamesContent_ = new LgNamesContent();
        lgNamesContent_.build(def_, cust_);
        assertEq("",lgNamesContent_.getDefaultPkg());
    }
    @Test
    public void getAlias5() {
        StringMap<String> def_ = new StringMap<String>();
        def_.put("","value");
        StringMap<String> cust_ = new StringMap<String>();
        cust_.put("","value");
        KeyWords lgNamesContent_ = new KeyWords();
        lgNamesContent_.build(def_, cust_);
        assertEq("",lgNamesContent_.getKeyWordVar());
    }
    @Test
    public void getAlias6() {
        StringMap<String> def_ = new StringMap<String>();
        def_.put("","value");
        StringMap<String> cust_ = new StringMap<String>();
        cust_.put("","value");
        AnalysisMessages lgNamesContent_ = new AnalysisMessages();
        lgNamesContent_.build(def_, cust_);
        assertEq("",lgNamesContent_.getEmptyPart());
    }
    @Test
    public void getAlias7() {
        WarningShow built_ = AnalysisMessages.build(new StringList("DeadCodeTernary", "UnusedParamStatic"));
        assertTrue(built_.isTernary());
        assertTrue(built_.isUnusedParameterStaticMethod());
    }
    @Test
    public void getAlias8() {
        WarningShow built_ = AnalysisMessages.build(new StringList("UnusedParamStatic"));
        assertTrue(!built_.isTernary());
        assertTrue(built_.isUnusedParameterStaticMethod());
    }
    @Test
    public void getAlias9() {
        WarningShow built_ = AnalysisMessages.build(new StringList("DeadCodeTernary"));
        assertTrue(built_.isTernary());
        assertTrue(!built_.isUnusedParameterStaticMethod());
    }
    @Test
    public void getAlias10() {
        WarningShow built_ = AnalysisMessages.build(new StringList());
        assertTrue(!built_.isTernary());
        assertTrue(!built_.isUnusedParameterStaticMethod());
    }
    @Test
    public void parseLineArg1Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first_arg");
        assertEq(1, args_.size());
        assertEq("first_arg", args_.get(0));
    }
    @Test
    public void parseLineArg2Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first_arg second_arg");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_arg", args_.get(1));
    }
    @Test
    public void parseLineArg3Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first\\ arg second_arg");
        assertEq(2, args_.size());
        assertEq("first arg", args_.get(0));
        assertEq("second_arg", args_.get(1));
    }
    @Test
    public void parseLineArg4Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first_arg second\\ arg");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second arg", args_.get(1));
    }
    @Test
    public void parseLineArg5Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first\\\\arg second_arg");
        assertEq(2, args_.size());
        assertEq("first\\arg", args_.get(0));
        assertEq("second_arg", args_.get(1));
    }
    @Test
    public void parseLineArg6Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first_arg second\\\\arg");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second\\arg", args_.get(1));
    }
    @Test
    public void parseLineArg7Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first\\narg second_arg");
        assertEq(2, args_.size());
        assertEq("first\narg", args_.get(0));
        assertEq("second_arg", args_.get(1));
    }
    @Test
    public void parseLineArg8Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first_arg second\\narg");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second\narg", args_.get(1));
    }
    @Test
    public void parseLineArg9Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first\\earg second_arg");
        assertEq(2, args_.size());
        assertEq("first arg", args_.get(0));
        assertEq("second_arg", args_.get(1));
    }
    @Test
    public void parseLineArg10Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first_arg second\\earg");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second arg", args_.get(1));
    }
    @Test
    public void parseLineArg11Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first\\targ second_arg");
        assertEq(2, args_.size());
        assertEq("first\targ", args_.get(0));
        assertEq("second_arg", args_.get(1));
    }
    @Test
    public void parseLineArg12Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first_arg second\\targ");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second\targ", args_.get(1));
    }
    @Test
    public void parseLineArg13Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first_arg second_arg\\c");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_argc", args_.get(1));
    }
    @Test
    public void parseLineArg14Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first_arg second_arg\\cgg");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_argcgg", args_.get(1));
    }
    @Test
    public void parseLineArg15Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first_arg second_arg\\c0A");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_arg\n", args_.get(1));
    }
    @Test
    public void parseLineArg16Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first_arg second_arg\\c20");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_argc20", args_.get(1));
    }
    @Test
    public void parseLineArg17Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first_arg second_arg\\c0Av");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_arg\nv", args_.get(1));
    }
    @Test
    public void parseLineArg18Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first_arg second_arg\\c-1");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_argc-1", args_.get(1));
    }
    @Test
    public void parseLineArg19Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first_arg second_arg\\u");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_argu", args_.get(1));
    }
    @Test
    public void parseLineArg20Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first_arg second_arg\\ugggg");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_argugggg", args_.get(1));
    }
    @Test
    public void parseLineArg21Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first_arg second_arg\\u000A");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_arg\n", args_.get(1));
    }
    @Test
    public void parseLineArg22Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first_arg second_arg\\u000Av");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_arg\nv", args_.get(1));
    }
    @Test
    public void parseLineArg24Test() {
        StringList args_ = ParseLinesArgUtil.parseLineArg("first_arg second_arg\\u-111");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_argu-111", args_.get(1));
    }
    @Test
    public void parseValue1Test() {
        assertEq("first_arg", ParseLinesArgUtil.parseValue("first_arg"));
    }
    @Test
    public void parseValue2Test() {
        assertEq("first_arg ", ParseLinesArgUtil.parseValue("first_arg\\e"));
    }
    @Test
    public void parseValue3Test() {
        assertEq("first_arg after", ParseLinesArgUtil.parseValue("first_arg\\eafter"));
    }
    @Test
    public void parseValue4Test() {
        assertEq("first_arg\t", ParseLinesArgUtil.parseValue("first_arg\\t"));
    }
    @Test
    public void parseValue5Test() {
        assertEq("first_arg\tafter", ParseLinesArgUtil.parseValue("first_arg\\tafter"));
    }
    @Test
    public void parseValue6Test() {
        assertEq("first_argc", ParseLinesArgUtil.parseValue("first_arg\\c"));
    }
    @Test
    public void parseValue7Test() {
        assertEq("first_argc0", ParseLinesArgUtil.parseValue("first_arg\\c0"));
    }
    @Test
    public void parseValue8Test() {
        assertEq("first_arg\n", ParseLinesArgUtil.parseValue("first_arg\\c0A"));
    }
    @Test
    public void parseValue9Test() {
        assertEq("first_argc20", ParseLinesArgUtil.parseValue("first_arg\\c20"));
    }
    @Test
    public void parseValue10Test() {
        assertEq("first_arg\nafter", ParseLinesArgUtil.parseValue("first_arg\\c0Aafter"));
    }
    @Test
    public void parseValue11Test() {
        assertEq("first_argc-1", ParseLinesArgUtil.parseValue("first_arg\\c-1"));
    }
    @Test
    public void parseValue12Test() {
        assertEq("first_argu", ParseLinesArgUtil.parseValue("first_arg\\u"));
    }
    @Test
    public void parseValue13Test() {
        assertEq("first_argu0", ParseLinesArgUtil.parseValue("first_arg\\u0"));
    }
    @Test
    public void parseValue14Test() {
        assertEq("first_arg\n", ParseLinesArgUtil.parseValue("first_arg\\u000A"));
    }
    @Test
    public void parseValue15Test() {
        assertEq("first_arg\nafter", ParseLinesArgUtil.parseValue("first_arg\\u000Aafter"));
    }
    @Test
    public void parseValue16Test() {
        assertEq("first_argu-111", ParseLinesArgUtil.parseValue("first_arg\\u-111"));
    }
    @Test
    public void parseValue17Test() {
        assertEq("first_arg\\", ParseLinesArgUtil.parseValue("first_arg\\\\"));
    }
    @Test
    public void parseValue18Test() {
        assertEq("first_arg\\after", ParseLinesArgUtil.parseValue("first_arg\\\\after"));
    }
    @Test
    public void parseValue19Test() {
        assertEq("first_arg\n", ParseLinesArgUtil.parseValue("first_arg\\n"));
    }
    @Test
    public void parseValue20Test() {
        assertEq("first_arg\nafter", ParseLinesArgUtil.parseValue("first_arg\\nafter"));
    }
    @Test
    public void parseValue22Test() {
        assertEq("first_argcgg", ParseLinesArgUtil.parseValue("first_arg\\cgg"));
    }
    @Test
    public void parseValue23Test() {
        assertEq("first_argugggg", ParseLinesArgUtil.parseValue("first_arg\\ugggg"));
    }
    @Test
    public void buildList1Test() {
        StringList map_ = new StringList();
        ParseLinesArgUtil.buildList(new StringBuilder(), map_);
        assertEq(0, map_.size());
    }
    @Test
    public void buildList2Test() {
        StringList map_ = new StringList();
        ParseLinesArgUtil.buildList(new StringBuilder("key"), map_);
        assertEq(1, map_.size());
        assertEq("key", map_.get(0));
    }
    @Test
    public void buildList3Test() {
        StringList map_ = new StringList();
        ParseLinesArgUtil.buildList(new StringBuilder("key1,,key2"), map_);
        assertEq(2, map_.size());
        assertEq("key1", map_.get(0));
        assertEq("key2", map_.get(1));
    }
    @Test
    public void buildMap1Test() {
        StringMap<String> map_ = new StringMap<String>();
        ParseLinesArgUtil.buildMap(new StringBuilder(), map_);
        assertEq(0, map_.size());
    }
    @Test
    public void buildMap2Test() {
        StringMap<String> map_ = new StringMap<String>();
        ParseLinesArgUtil.buildMap(new StringBuilder("key"), map_);
        assertEq(0, map_.size());
    }
    @Test
    public void buildMap3Test() {
        StringMap<String> map_ = new StringMap<String>();
        ParseLinesArgUtil.buildMap(new StringBuilder("key=value"), map_);
        assertEq(1, map_.size());
        assertEq("key", map_.firstKey());
        assertEq("value", map_.firstValue());
    }
    @Test
    public void buildMap4Test() {
        StringMap<String> map_ = new StringMap<String>();
        ParseLinesArgUtil.buildMap(new StringBuilder("key=value,key2=value2"), map_);
        assertEq(2, map_.size());
        assertEq("key", map_.firstKey());
        assertEq("value", map_.firstValue());
        assertEq("key2", map_.lastKey());
        assertEq("value2", map_.lastValue());
    }
    @Test
    public void buildComments1Test() {
        CustList<CommentDelimiters> commentDelimiters_ = ParseLinesArgUtil.buildComments("");
        assertEq(1, commentDelimiters_.size());
        assertEq(" ", commentDelimiters_.first().getBegin());
        assertEq(1, commentDelimiters_.first().getEnd().size());
        assertEq(" ", commentDelimiters_.first().getEnd().first());
    }
    @Test
    public void buildComments2Test() {
        CustList<CommentDelimiters> commentDelimiters_ = ParseLinesArgUtil.buildComments("\\\\*,,*\\\\");
        assertEq(1, commentDelimiters_.size());
        assertEq("\\*", commentDelimiters_.first().getBegin());
        assertEq(1, commentDelimiters_.first().getEnd().size());
        assertEq("*\\", commentDelimiters_.first().getEnd().first());
    }
    @Test
    public void buildComments3Test() {
        CustList<CommentDelimiters> commentDelimiters_ = ParseLinesArgUtil.buildComments("\\\\*,,*\\\\;\\\\<,,>\\\\");
        assertEq(2, commentDelimiters_.size());
        assertEq("\\*", commentDelimiters_.first().getBegin());
        assertEq(1, commentDelimiters_.first().getEnd().size());
        assertEq("*\\", commentDelimiters_.first().getEnd().first());
        assertEq("\\<", commentDelimiters_.last().getBegin());
        assertEq(1, commentDelimiters_.last().getEnd().size());
        assertEq(">\\", commentDelimiters_.last().getEnd().first());
    }
}
