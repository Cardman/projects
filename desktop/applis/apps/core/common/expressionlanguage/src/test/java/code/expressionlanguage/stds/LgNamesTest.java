package code.expressionlanguage.stds;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefContextGenerator;
import code.expressionlanguage.SingleInterruptedContextEl;
import code.expressionlanguage.analyze.AbstractConstantsCalculator;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.DefaultConstantsCalculator;
import code.expressionlanguage.analyze.DefaultFileBuilder;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.analyze.files.CommentDelimiters;
import code.expressionlanguage.common.ParseLinesArgUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecOverridableBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.CustomFoundMethod;
import code.expressionlanguage.exec.inherits.Parameters;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.Forwards;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.blocks.ForwardInfos;
import code.expressionlanguage.methods.ProcessMethodCommon;
import code.expressionlanguage.options.*;
import code.expressionlanguage.sample.CustLgNames;
import code.expressionlanguage.structs.NullStruct;
import code.threads.ConcreteBoolean;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public class LgNamesTest extends ProcessMethodCommon {
    @Test
    public void fail1Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordAbstract("");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail2Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordIntern("");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
        assertTrue(kw_.getKeyWordIntern().isEmpty());
    }
    @Test
    public void fail3Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordAbstract("<");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail4Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordAbstract("1a");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail5Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordEscBound("");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail6Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordEscBound("<");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }

    @Test
    public void fail7Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbHexEnd("");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail8Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbHexEnd("<");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail9Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbHexEnd("_");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail10Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbBin("");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail11Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbBin("<");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail12Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbBin("_");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail13Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbBin("0a");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail14Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbExpBin("");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail15Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbExpBin("<");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail16Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbExpBin("_");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail17Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbExpBin("1");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail18Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbExpBin("a");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail19Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbExpBin("A");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail20Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbHexEnd("");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail21Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbHexEnd("<");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail22Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbHexEnd("1");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail23Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbHexEnd("a");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail24Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbHexEnd("A");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail25Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getPrimTypes().setAliasPrimInteger("");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail26Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getPrimTypes().setAliasPrimInteger("<");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail27Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getPrimTypes().setAliasPrimInteger("$if");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail28Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getPrimTypes().setAliasPrimInteger("0a");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
        StringMap<String> keyWords_ = kw_.allKeyWords();
        validateKeyWordContents(kw_, s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        validateEscapingsContents(kw_, s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        validateNbWordContents(kw_, s_, nbWords_);
        validateBinarySeparators(kw_, s_);
        StringMap<String> prims_ = allPrimitives(s_);
        validatePrimitiveContents(s_, prims_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail29Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getNbAlias().setAliasInteger("$if");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail30Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getNbAlias().setAliasInteger("<");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail31Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getNbAlias().setAliasInteger("ab");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail32Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getNbAlias().setAliasInteger("0a");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail33Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getNbAlias().setAliasInteger("a..b");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail34Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getNbAlias().setAliasInteger("$int");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail35Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().setDefaultPkg("pkg");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail36Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getNbAlias().setAliasInteger("java");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail37Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getCharSeq().setAliasLength("");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail38Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getCharSeq().setAliasLength("<");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail39Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getCharSeq().setAliasLength("0a");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail40Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getCharSeq().setAliasLength("$if");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail41Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getCharSeq().setAliasLength("$int");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail42Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getNbAlias().setAliasMaxValueField("");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail43Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getNbAlias().setAliasMaxValueField("<");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail44Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getNbAlias().setAliasMaxValueField("0a");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail45Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getNbAlias().setAliasMaxValueField("$int");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail46Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getNbAlias().setAliasMaxValueField("$if");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail47Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordCase("$abstract");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail48Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordEscBound("n");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail49Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordEscBound("nou");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail50Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordEscUnicode("");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail51Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordEscUnicode("f");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail52Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordEscBound("");
        kw_.setKeyWordEscUnicode("f0");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail53Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordEscUnicode("f0");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail54Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbSufLong("Y");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail55Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        
        kw_.setKeyWordNbBin("x");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail56Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getPrimTypes().setAliasPrimBoolean("$byte");
        lgName_.getContent().getNbAlias().setAliasMaxValueField("MIN_VALUE");
        lgName_.getContent().getMathRef().setAliasLe("ge");
        lgName_.getContent().getNbAlias().setAliasBoolean("java.lang.Byte");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail57Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getNbAlias().setAliasInteger("<");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail58Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getNbAlias().setAliasInteger("");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail59Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getPredefTypes().setAliasIterableTableVarFirst(",E");
        lgName_.getContent().getPredefTypes().setAliasIterableTableVarSecond("0E");
        lgName_.getContent().getPredefTypes().setAliasEnumParamVar("");
        lgName_.getContent().getPredefTypes().setAliasIteratorTypeVar("$if");
        lgName_.getContent().getPredefTypes().setAliasIterableVar("$int");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail60Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getPredefTypes().setAliasIterableTableVarFirst("E");
        lgName_.getContent().getPredefTypes().setAliasIterableTableVarSecond("E");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail61Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getPredefTypes().getParams().setAliasSeedGenerator0Get0("");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail62Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getPredefTypes().getParams().setAliasSeedGenerator0Get0("<");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail63Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getPredefTypes().getParams().setAliasSeedGenerator0Get0("0a");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail64Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getPredefTypes().getParams().setAliasSeedGenerator0Get0("$if");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail65Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getPredefTypes().getParams().setAliasSeedGenerator0Get0("$int");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail66Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        lgName_.getContent().getPredefTypes().setAliasHasNextPair("hasNext");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        CustList<KeyValueMemberName> merge_ = allMergeTableTypeMethodNames(s_);
        validateMergedDuplicates(s_, merge_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail67Test() {
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        kw_.setKeyWordNbDig0("");
        kw_.setKeyWordNbDig1("0");
        kw_.setKeyWordNbDig2(",");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);

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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }
    @Test
    public void fail68Test() {
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        kw_.setKeyWordNbDig0("F");
        kw_.setKeyWordNbDig1("F");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);

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
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
    }

    @Test
    public void fail69Test() {
        
        
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
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
        lgName_.getNbAlias().getParams().setAliasDouble0Signum0("");
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
        lgName_.getReflect().getParams().setAliasFct0CallRef0("");
        lgName_.getReflect().getParams().setAliasFct0CallRefAfter0("");
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
        lgName_.getReflect().getParams().setAliasConstructor0NewInstanceRef0("");
        lgName_.getReflect().getParams().setAliasConstructor0NewInstanceRefAfter0("");
        lgName_.getReflect().getParams().setAliasField0GetField0("");
        lgName_.getReflect().getParams().setAliasField0SetField0("");
        lgName_.getReflect().getParams().setAliasField0SetField1("");
        lgName_.getReflect().getParams().setAliasMethod0Invoke0("");
        lgName_.getReflect().getParams().setAliasMethod0Invoke1("");
        lgName_.getReflect().getParams().setAliasMethod0InvokeDirect0("");
        lgName_.getReflect().getParams().setAliasMethod0InvokeDirect1("");
        lgName_.getReflect().getParams().setAliasMethod0InvokeRef0("");
        lgName_.getReflect().getParams().setAliasMethod0InvokeRef1("");
        lgName_.getReflect().getParams().setAliasMethod0InvokeDirectRef0("");
        lgName_.getReflect().getParams().setAliasMethod0InvokeDirectRef1("");
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
        lgName_.getReflect().getParams().setAliasAnnotated0GetAnnotationsSupp0("");
        lgName_.getReflect().getParams().setAliasAnnotated0GetAnnotationsParameters0("");
        lgName_.getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda0("");
        lgName_.getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda1("");
        lgName_.getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda2("");
        lgName_.getReflect().getParams().setAliasAnnotated0GetDeclaredAnonymousLambda3("");
        lgName_.getReflect().getParams().setAliasAnnotated0GetDeclaredSwitchMethods0("");
        lgName_.getReflect().getParams().setAliasAnnotated0GetDeclaredSwitchMethods1("");
        lgName_.getReflect().getParams().setAliasAnnotated0GetDeclaredSwitchMethods2("");
        lgName_.getReflect().getParams().setAliasAnnotated0GetDeclaredSwitchMethods3("");
        AnalyzedPageEl s_ = getCtx(kw_, lgName_);
        
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
        CustList<KeyValueMemberName> merge_ = allMergeTableTypeMethodNames(s_);
        validateMergedDuplicates(s_, merge_);
        assertFalse(s_.getMessages().displayStdErrors(),s_.isEmptyStdError());
        existErrors(s_);
    }

    @Test
    public void success0Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return Resources.readNames().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> srcFiles_ = new StringMap<String>();


        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        srcFiles_.put("src/Ex", xml_.toString());
        StringMap<String> others_ = new StringMap<String>();
        others_.put("pkg/hello_res.txt", "content");
        StringMap<String> all_ = new StringMap<String>();
        all_.putAllMap(srcFiles_);
        all_.putAllMap(others_);
        Options options_ = new Options();
        AbstractConstantsCalculator calculator_ = new DefaultConstantsCalculator(lgName_.getNbAlias());
        AnalysisMessages mess_ = new AnalysisMessages();
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setAnalysisMessages(mess_);
        page_.setKeyWords(kw_);
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        page_.setFileBuilder(fileBuilder_);
        page_.setStandards(lgName_.getContent());
        page_.setLogErr(new ListLoggableLgNames());
        page_.setCalculator(calculator_);
        page_.setMappingKeyWords(KeyWords.mapping());
        page_.setMappingAliases(LgNamesContent.mapping());
        AnalysisMessages.validateMessageContents(mess_.allMessages(), page_);
        assertTrue(page_.isEmptyMessageError());
        Forwards forwards_ = fwd(lgName_, fileBuilder_, options_);
        assertTrue(ContextFactory.validateStds(forwards_,page_.getAnalysisMessages(), kw_, new CustList<CommentDelimiters>(), options_, lgName_.getContent(), page_));
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
        CustLgNames lgName_ = getLgNames();
        
        srcFiles_.put("src/Ex", xml_.toString());
        StringMap<String> others_ = new StringMap<String>();
        others_.put("pkg/hello_res.txt", "content");
        StringMap<String> all_ = new StringMap<String>();
        all_.putAllMap(srcFiles_);
        all_.putAllMap(others_);
        Options options_ = new Options();
        AbstractConstantsCalculator calculator_ = new DefaultConstantsCalculator(lgName_.getNbAlias());
        AnalysisMessages mess_ = new AnalysisMessages();
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setAnalysisMessages(mess_);
        page_.setKeyWords(kw_);
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        page_.setFileBuilder(fileBuilder_);
        page_.setStandards(lgName_.getContent());
        page_.setLogErr(new ListLoggableLgNames());
        page_.setCalculator(calculator_);
//        page_.setMappingKeyWords(KeyWords.mapping());
//        page_.setMappingAliases(LgNamesContent.mapping());
        AnalysisMessages.validateMessageContents(mess_.allMessages(), page_);
        assertTrue(page_.isEmptyMessageError());
        ResultContext ctx_ = validate(options_,lgName_,kw_,all_);
        assertTrue(isEmptyErrors(ctx_.getPageEl()));
        MethodId fct_ = new MethodId(MethodAccessKind.STATIC, "exmeth",new StringList());
        Argument argGlLoc_ = new Argument();
        ExecRootBlock classBody_ = ctx_.getContext().getClasses().getClassBody("pkg.Ex");
        ExecOverridableBlock method_ = getDeepMethodBodiesById(ctx_.getContext(), "pkg.Ex", fct_).first();
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,ctx_.getContext());
        Argument ret_ = ProcessMethod.calculate(new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, "pkg.Ex"), new ExecTypeFunction(classBody_, method_), new Parameters()), ctx_.getContext(), stackCall_).getValue();
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
        CustLgNames lgName_ = getLgNames();
        
        srcFiles_.put("src/Ex", xml_.toString());
        StringMap<String> others_ = new StringMap<String>();
        others_.put("pkg/hello_res.txt", "content");
        StringMap<String> all_ = new StringMap<String>();
        all_.putAllMap(srcFiles_);
        all_.putAllMap(others_);
        Options options_ = new Options();
        AbstractConstantsCalculator calculator_ = new DefaultConstantsCalculator(lgName_.getNbAlias());
        AnalysisMessages mess_ = new AnalysisMessages();
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setAnalysisMessages(mess_);
        page_.setKeyWords(kw_);
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        page_.setFileBuilder(fileBuilder_);
        page_.setStandards(lgName_.getContent());
        page_.setLogErr(new ListLoggableLgNames());
        page_.setCalculator(calculator_);
//        page_.setMappingKeyWords(KeyWords.mapping());
//        page_.setMappingAliases(LgNamesContent.mapping());
        AnalysisMessages.validateMessageContents(mess_.allMessages(), page_);
        assertTrue(page_.isEmptyMessageError());
        ResultContext ctx_ = validate(options_,lgName_,kw_,all_);
        assertTrue(isEmptyErrors(ctx_.getPageEl()));
        MethodId fct_ = new MethodId(MethodAccessKind.STATIC, "exmeth",new StringList());
        Argument argGlLoc_ = new Argument();
        ExecRootBlock classBody_ = ctx_.getContext().getClasses().getClassBody("pkg.Ex");
        ExecOverridableBlock method_ = getDeepMethodBodiesById(ctx_.getContext(), "pkg.Ex", fct_).first();
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,ctx_.getContext());
        Argument ret_ = ProcessMethod.calculate(new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, "pkg.Ex"), new ExecTypeFunction(classBody_, method_), new Parameters()), ctx_.getContext(), stackCall_).getValue();
        assertNull(stackCall_.getCallingState());
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void success3Test() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append(" $public $static $int exmeth(){\n");
        xml_.append("  $return Resources.readNames().length;\n");
        xml_.append(" }\n");
        xml_.append("}\n");
        StringMap<String> srcFiles_ = new StringMap<String>();
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        srcFiles_.put("src/Ex", xml_.toString());
        StringMap<String> others_ = new StringMap<String>();
        others_.put("pkg/hello_res.txt", "content");
        StringMap<String> all_ = new StringMap<String>();
        all_.putAllMap(srcFiles_);
        all_.putAllMap(others_);
        Options options_ = new Options();
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        Forwards forwards_ = getForwards(options_, lgName_, kw_,page_, all_);
        ResultContext r_ = new ResultContext(page_, forwards_);
        ResultContext res_ = ResultContext.def(r_, all_, "src");
        ResultContext.fwd(res_, new DefContextGenerator());
        assertTrue(isEmptyErrors(res_.getPageEl()));
        MethodId fct_ = new MethodId(MethodAccessKind.STATIC, "exmeth",new StringList());
        Argument argGlLoc_ = new Argument();
        ExecRootBlock classBody_ = res_.getContext().getClasses().getClassBody("pkg.Ex");
        ExecOverridableBlock method_ = getDeepMethodBodiesById(res_.getContext(), "pkg.Ex", fct_).first();
        StackCall stackCall_ = StackCall.newInstance(InitPhase.NOTHING,res_.getContext());
        Argument ret_ = ExecClassesUtil.tryInitStaticlyTypes(res_.getContext(), res_.getForwards().getOptions(),stackCall_,new CustomFoundMethod(argGlLoc_, new ExecFormattedRootBlock(classBody_, "pkg.Ex"), new ExecTypeFunction(classBody_, method_), new Parameters()),StepDbgActionEnum.RUN,true).getRetValue().getValue();
        assertNull(stackCall_.getCallingState());
        assertEq(2, getNumber(ret_));
    }
    @Test
    public void stoppingTest() {
        StringBuilder xml_ = new StringBuilder();
        xml_.append("$public $class pkg.Ex {\n");
        xml_.append("}\n");
        StringMap<String> srcFiles_ = new StringMap<String>();


        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();

        srcFiles_.put("src/Ex", xml_.toString());
        StringMap<String> others_ = new StringMap<String>();
        others_.put("pkg/hello_res.txt", "content");
        StringMap<String> all_ = new StringMap<String>();
        all_.putAllMap(srcFiles_);
        all_.putAllMap(others_);
        Options options_ = new Options();
        AbstractConstantsCalculator calculator_ = new DefaultConstantsCalculator(lgName_.getNbAlias());
        AnalysisMessages mess_ = new AnalysisMessages();
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setAnalysisMessages(mess_);
        page_.setKeyWords(kw_);
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(lgName_.getContent());
        page_.setFileBuilder(fileBuilder_);
        page_.setStandards(lgName_.getContent());
        page_.setLogErr(new ListLoggableLgNames());
        page_.setCalculator(calculator_);
//        page_.setMappingKeyWords(KeyWords.mapping());
//        page_.setMappingAliases(LgNamesContent.mapping());
        AnalysisMessages.validateMessageContents(mess_.allMessages(), page_);
        assertTrue(page_.isEmptyMessageError());
        ResultContext ctx_ = validate(options_,lgName_,kw_,all_);
        ConcreteBoolean stop_ = new ConcreteBoolean();
        SingleInterruptedContextEl s_ = new SingleInterruptedContextEl(ctx_.getContext().getExecutionInfos(), stop_);
        StackCall st_ = StackCall.newInstance(InitPhase.NOTHING, s_);
        assertFalse(s_.callsOrException(st_));
        st_.setCallingState(new CustomFoundExc(NullStruct.NULL_VALUE));
        assertTrue(s_.callsOrException(st_));
        stop_.set(true);
        assertTrue(s_.callsOrException(st_));
        assertTrue(((DefaultInitializer)s_.getInit()).exitAfterCallInt(s_,st_));
    }

    @Test
    public void stoppingInitTest() {
        KeyWords kw_ = new KeyWords();
        CustLgNames lgName_ = getLgNames();
        StringMap<String> all_ = new StringMap<String>();
        Options options_ = new Options();
        AnalyzedPageEl sec_ = AnalyzedPageEl.setInnerAnalyzing();
        Forwards forwards_ = getForwards(options_, lgName_, kw_, sec_, all_);
        ResultContext r_ = new ResultContext(sec_, forwards_);
        ResultContext res_ = ResultContext.def(r_, all_, "src");
        ForwardInfos.generalForward(res_);
        Forwards f_ = res_.getForwards();
        CommonExecutionInfos ctxInfos_ = f_.getGenerator().newContextCommon(f_.getOptions(), f_);
        ConcreteBoolean stop_ = new ConcreteBoolean();
        stop_.set(true);
        ContextEl ctx_ = new SingleInterruptedContextEl(ctxInfos_, stop_);
        Classes.forwardAndClear(ctx_);
        res_.setContext(ctx_);
        StackCall st_ = Classes.tryInit(res_);
        assertTrue(ctx_.callsOrException(st_));
    }
    private static void validateParamtersDuplicates(AnalyzedPageEl _s, CustList<CustList<KeyValueMemberName>> _params) {
        ValidatorStandard.validateParamtersDuplicates(_params, _s);
    }

    private static void validateKeyWordContents(KeyWords _kw, AnalyzedPageEl _s, StringMap<String> _keyWords) {
        _kw.validateKeyWordContents(_keyWords, _s);
    }

    private static void validateNbWordContents(KeyWords _kw, AnalyzedPageEl _s, StringMap<String> _nbWords) {
        _kw.validateNbWordContents(_nbWords, _s);
    }

    private static void validatePrimitiveContents(AnalyzedPageEl _s, StringMap<String> _prims) {
        ValidatorStandard.validatePrimitiveContents(_prims, _s);
    }

    private static void validateRefTypeContents(AnalyzedPageEl _s, StringMap<String> _prims, StringMap<String> _refTypes) {
        ValidatorStandard.validateRefTypeContents(_refTypes, _prims, _s);
    }

    private static void validateParamtersContents(AnalyzedPageEl _s, StringMap<String> _prims, CustList<CustList<KeyValueMemberName>> _params) {
        ValidatorStandard.validateParamtersContents(_params, _prims, _s);
    }

    private static void validateVarTypesContents(AnalyzedPageEl _s, StringMap<String> _prims, StringMap<CustList<KeyValueMemberName>> _varTypes) {
        ValidatorStandard.validateVarTypesContents(_varTypes, _prims, _s);
    }

    private static void validateEscapingsDuplicates(KeyWords _kw, AnalyzedPageEl _s, StringMap<String> _escapings) {
        _kw.validateEscapingsDuplicates(_escapings, _s);
    }

    private static void validateStartsPrefixesDuplicates(KeyWords _kw, AnalyzedPageEl _s) {
        _kw.validateStartsPrefixesDuplicates(_s);
    }

    private static void validateRefTypeDuplicates(AnalyzedPageEl _s, StringMap<String> _refTypes) {
        ValidatorStandard.validateRefTypeDuplicates(_refTypes, _s);
    }

    private static void validateFieldsDuplicates(AnalyzedPageEl _s, StringMap<CustList<KeyValueMemberName>> _fields) {
        ValidatorStandard.validateFieldsDuplicates(_fields, _s);
    }

    private static void validateMergedDuplicates(AnalyzedPageEl _s, CustList<KeyValueMemberName> _merge) {
        ValidatorStandard.validateMergedDuplicates(_merge, _s);
    }

    private static void validateEscapingsContents(KeyWords _kw, AnalyzedPageEl _s, StringMap<String> _escapings) {
        _kw.validateEscapingsContents(_escapings, _s);
    }

    private static void validateBinarySeparators(KeyWords _kw, AnalyzedPageEl _s) {
        _kw.initSupplDigits();
        _kw.validateBinarySeparators(_s);
    }

    private static void validateMethodsContents(AnalyzedPageEl _s, StringMap<String> _prims, StringMap<CustList<KeyValueMemberName>> _methods) {
        ValidatorStandard.validateMethodsContents(_methods, _prims, _s);
    }

    private static void validateFieldsContents(AnalyzedPageEl _s, StringMap<String> _prims, StringMap<CustList<KeyValueMemberName>> _fields) {
        ValidatorStandard.validateFieldsContents(_fields, _prims, _s);
    }

    private static void validateKeyWordDuplicates(KeyWords _kw, AnalyzedPageEl _s, StringMap<String> _keyWords) {
        _kw.validateKeyWordDuplicates(_keyWords, _s);
    }

    private static void validateNbWordDuplicates(KeyWords _kw, AnalyzedPageEl _s, StringMap<String> _nbWordsBin) {
        _kw.validateNbWordDuplicates(_nbWordsBin, _s);
    }

    private static void validatePrimitiveDuplicates(AnalyzedPageEl _s, StringMap<String> _prims) {
        ValidatorStandard.validatePrimitiveDuplicates(_prims, _s);
    }

    private static void validateMethodsDuplicates(AnalyzedPageEl _s, StringMap<CustList<KeyValueMemberName>> _methods) {
        ValidatorStandard.validateMethodsDuplicates(_methods, _s);
    }

    private static void validateVarTypesDuplicates(AnalyzedPageEl _s, StringMap<CustList<KeyValueMemberName>> _varTypes) {
        ValidatorStandard.validateVarTypesDuplicates(_varTypes, _s);
    }
    private static StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(AnalyzedPageEl _lgName) {
        return _lgName.getFileBuilder().getDefaultAliasGroups().allTableTypeMethodNames(LgNamesContent.mapping());
    }

    private static CustList<CustList<KeyValueMemberName>> allTableTypeMethodParamNames(AnalyzedPageEl _lgName) {
        return _lgName.getFileBuilder().getDefaultAliasGroups().allTableTypeMethodParamNames(LgNamesContent.mapping());
    }

    private static StringMap<CustList<KeyValueMemberName>> allTableTypeVarTypes(AnalyzedPageEl _lgName) {
        return _lgName.getFileBuilder().getDefaultAliasGroups().allTableTypeVarTypes(LgNamesContent.mapping());
    }

    private static StringMap<String> allPrimitives(AnalyzedPageEl _lgName) {
        return _lgName.getFileBuilder().getDefaultAliasGroups().allPrimitives(LgNamesContent.mapping());
    }

    private static StringMap<String> allRefTypes(AnalyzedPageEl _lgName) {
        return _lgName.getFileBuilder().getDefaultAliasGroups().allRefTypes(LgNamesContent.mapping());
    }

    private static CustList<KeyValueMemberName> allMergeTableTypeMethodNames(AnalyzedPageEl _lgName) {
        return _lgName.getFileBuilder().getDefaultAliasGroups().allMergeTableTypeMethodNames(LgNamesContent.mapping());
    }

    private static StringMap<CustList<KeyValueMemberName>> allTableTypeFieldNames(AnalyzedPageEl _lgName) {
        return _lgName.getFileBuilder().getDefaultAliasGroups().allTableTypeFieldNames(LgNamesContent.mapping());
    }


    private static AnalyzedPageEl getCtx(KeyWords _kw, CustLgNames _lgName) {
        AnalysisMessages mess_ = new AnalysisMessages();
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setAnalysisMessages(mess_);
        page_.setKeyWords(_kw);
        DefaultFileBuilder fileBuilder_ = DefaultFileBuilder.newInstance(_lgName.getContent());
        page_.setFileBuilder(fileBuilder_);
        page_.setStandards(_lgName.getContent());
        page_.setLogErr(new ListLoggableLgNames());
        page_.setCalculator(null);
        AnalysisMessages.validateMessageContents(mess_.allMessages(), page_);
        assertTrue(page_.isEmptyMessageError());
        return page_;
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
        lgNamesContent_.build(def_, cust_,LgNamesContent.mapping());
        assertEq("",lgNamesContent_.getDefaultPkg());
    }
    @Test
    public void getAlias5() {
        StringMap<String> def_ = new StringMap<String>();
        def_.put("","value");
        StringMap<String> cust_ = new StringMap<String>();
        cust_.put("","value");
        KeyWords lgNamesContent_ = new KeyWords();
        lgNamesContent_.build(def_, cust_, KeyWords.mapping());
        assertEq("",lgNamesContent_.getKeyWordVar());
    }
    @Test
    public void getAlias6() {
        StringMap<String> def_ = new StringMap<String>();
        def_.put("","value");
        StringMap<String> cust_ = new StringMap<String>();
        cust_.put("","value");
        AnalysisMessages lgNamesContent_ = new AnalysisMessages();
        lgNamesContent_.build(def_, cust_, AnalysisMessages.mapping());
        assertEq("",lgNamesContent_.getEmptyPart());
    }
    @Test
    public void getAlias7() {
        WarningShow built_ = AnalysisMessages.build(new StringList("DeadCodeTernary", "UnusedParamStatic"), AnalysisMessages.mapping());
        assertTrue(built_.isTernary());
        assertTrue(built_.isUnusedParameterStaticMethod());
    }
    @Test
    public void getAlias8() {
        WarningShow built_ = AnalysisMessages.build(new StringList("UnusedParamStatic"), AnalysisMessages.mapping());
        assertTrue(!built_.isTernary());
        assertTrue(built_.isUnusedParameterStaticMethod());
    }
    @Test
    public void getAlias9() {
        WarningShow built_ = AnalysisMessages.build(new StringList("DeadCodeTernary"), AnalysisMessages.mapping());
        assertTrue(built_.isTernary());
        assertTrue(!built_.isUnusedParameterStaticMethod());
    }
    @Test
    public void getAlias10() {
        WarningShow built_ = AnalysisMessages.build(new StringList(), AnalysisMessages.mapping());
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
    @Test
    public void exportLineArg1Test() {
        assertEq("\\u0020", ParseLinesArgUtil.exportLineArg(new StringList(" ")));
    }
    @Test
    public void exportLineArg2Test() {
        assertEq("\\u005c", ParseLinesArgUtil.exportLineArg(new StringList("\\")));
    }
    @Test
    public void exportLineArg3Test() {
        assertEq("\\u000a", ParseLinesArgUtil.exportLineArg(new StringList("\n")));
    }
    @Test
    public void exportLineArg4Test() {
        assertEq("\\u0009", ParseLinesArgUtil.exportLineArg(new StringList("\t")));
    }
    @Test
    public void exportLineArg5Test() {
        assertEq("nt", ParseLinesArgUtil.exportLineArg(new StringList("nt")));
    }
    @Test
    public void exportLineArg6Test() {
        assertEq("n t", ParseLinesArgUtil.exportLineArg(new StringList("n","t")));
    }
    @Test
    public void exportMapLine1Test() {
        StringMap<String> map_ = new StringMap<String>();
        map_.addEntry("k","=");
        assertEq("k==", ParseLinesArgUtil.buildMapLine(map_));
    }
    @Test
    public void exportMapLine2Test() {
        StringMap<String> map_ = new StringMap<String>();
        map_.addEntry("k",",");
        assertEq("k=\\u002c", ParseLinesArgUtil.buildMapLine(map_));
    }
    @Test
    public void exportMapLine3Test() {
        StringMap<String> map_ = new StringMap<String>();
        map_.addEntry("k","1");
        map_.addEntry("l","2");
        assertEq("k=1,l=2", ParseLinesArgUtil.buildMapLine(map_));
    }
    @Test
    public void exportComment1Test() {
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        comments_.add(new CommentDelimiters("\\",new StringList("\n")));
        assertEq("\\u005c,\\u000a", ParseLinesArgUtil.buildCommentsLine(comments_));
    }
    @Test
    public void exportComment2Test() {
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        comments_.add(new CommentDelimiters("\\*",new StringList("*\\")));
        assertEq("\\u005c*,*\\u005c", ParseLinesArgUtil.buildCommentsLine(comments_));
    }
    @Test
    public void exportComment3Test() {
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        comments_.add(new CommentDelimiters("\\ ;",new StringList(", \\")));
        assertEq("\\u005c\\u0020\\u003b,\\u002c\\u0020\\u005c", ParseLinesArgUtil.buildCommentsLine(comments_));
    }
    @Test
    public void exportComment4Test() {
        CustList<CommentDelimiters> comments_ = new CustList<CommentDelimiters>();
        comments_.add(new CommentDelimiters("\\*",new StringList("*\\")));
        comments_.add(new CommentDelimiters("\\ ;",new StringList(", \\")));
        assertEq("\\u005c*,*\\u005c;\\u005c\\u0020\\u003b,\\u002c\\u0020\\u005c", ParseLinesArgUtil.buildCommentsLine(comments_));
    }
}
