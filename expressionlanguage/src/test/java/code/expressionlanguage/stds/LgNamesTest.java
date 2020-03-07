package code.expressionlanguage.stds;

import code.expressionlanguage.*;
import code.expressionlanguage.classes.CustLgNames;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.errors.KeyValueMemberName;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.options.ContextFactory;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

import static code.expressionlanguage.EquallableElUtil.assertEq;
import static org.junit.Assert.assertTrue;

public class LgNamesTest {
    @Test
    public void fail1Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordAbstract("");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail2Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordIntern("");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        assertTrue(!s_.getClasses().isEmptyStdError());
        assertTrue(kw_.getKeyWordIntern().isEmpty());
    }
    @Test
    public void fail3Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordAbstract("<");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail4Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordAbstract("1a");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail5Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordEscBound("");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail6Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordEscBound("<");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }

    private static SingleContextEl getCtx(DefaultLockingClass lk_, DefaultInitializer di_, KeyWords kw_, LgNames lgName_, Options opts_) {
        AnalysisMessages mess_ = new AnalysisMessages();
        SingleContextEl ctx_ = new SingleContextEl(-1, lk_, di_, opts_, mess_, kw_, lgName_, 4);
        mess_.validateMessageContents(ctx_,mess_.allMessages());
        assertTrue(ctx_.getClasses().isEmptyMessageError());
        return ctx_;
    }

    @Test
    public void fail7Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbHex("");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail8Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbHex("<");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail9Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbHex("_");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail10Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbBin("");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail11Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbBin("<");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail12Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbBin("_");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail13Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbBin("0a");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail14Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbExpBin("");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail15Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbExpBin("<");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail16Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbExpBin("_");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail17Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbExpBin("1");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail18Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbExpBin("a");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail19Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbExpBin("A");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail20Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbHex("");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail21Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbHex("<");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail22Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbHex("1");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail23Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbHex("a");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail24Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbHex("A");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail25Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasPrimInteger("");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail26Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasPrimInteger("<");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail27Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasPrimInteger("$if");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail28Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasPrimInteger("0a");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail29Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasInteger("$if");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail30Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasInteger("<");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail31Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasInteger("ab");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail32Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasInteger("0a");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail33Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasInteger("a..b");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail34Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasInteger("$int");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail35Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setDefaultPkg("pkg");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail36Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasInteger("java");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail37Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasLength("");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail38Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasLength("<");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail39Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasLength("0a");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail40Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasLength("$if");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail41Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasLength("$int");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail42Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasMaxValueField("");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail43Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasMaxValueField("<");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail44Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasMaxValueField("0a");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail45Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasMaxValueField("$int");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail46Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasMaxValueField("$if");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail47Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordCase("$abstract");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        kw_.validateKeyWordDuplicates(s_, keyWords_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail48Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordEscBound("n");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        kw_.validateKeyWordDuplicates(s_, keyWords_);
        kw_.validateEscapingsDuplicates(s_, escapings_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail49Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordEscBound("nou");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        kw_.validateKeyWordDuplicates(s_, keyWords_);
        kw_.validateEscapingsDuplicates(s_, escapings_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail50Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordEscUnicode("");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        kw_.validateKeyWordDuplicates(s_, keyWords_);
        kw_.validateEscapingsDuplicates(s_, escapings_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail51Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordEscUnicode("f");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        kw_.validateKeyWordDuplicates(s_, keyWords_);
        kw_.validateEscapingsDuplicates(s_, escapings_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail52Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordEscBound("");
        kw_.setKeyWordEscUnicode("f0");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        kw_.validateKeyWordDuplicates(s_, keyWords_);
        kw_.validateEscapingsDuplicates(s_, escapings_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail53Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordEscUnicode("f0");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        kw_.validateKeyWordDuplicates(s_, keyWords_);
        kw_.validateEscapingsDuplicates(s_, escapings_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail54Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbSufLong("B");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        kw_.validateKeyWordDuplicates(s_, keyWords_);
        kw_.validateEscapingsDuplicates(s_, escapings_);
        StringMap<String> nbWordsDec_ = kw_.allNbWords(kw_.allNbWordsDec());
        kw_.validateNbWordDuplicates(s_, nbWordsDec_);
        StringMap<String> nbWordsBin_ = kw_.allNbWords(kw_.allNbWordsBin());
        kw_.validateNbWordDuplicates(s_, nbWordsBin_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail55Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        kw_.setKeyWordNbBin("x");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        kw_.validateKeyWordDuplicates(s_, keyWords_);
        kw_.validateEscapingsDuplicates(s_, escapings_);
        StringMap<String> nbWordsDec_ = kw_.allNbWords(kw_.allNbWordsDec());
        kw_.validateNbWordDuplicates(s_, nbWordsDec_);
        StringMap<String> nbWordsBin_ = kw_.allNbWords(kw_.allNbWordsBin());
        kw_.validateNbWordDuplicates(s_, nbWordsBin_);
        kw_.validateStartsPrefixesDuplicates(s_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail56Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasPrimBoolean("$byte");
        lgName_.setAliasMaxValueField("MIN_VALUE");
        lgName_.setAliasLe("ge");
        lgName_.setAliasBoolean("java.lang.Byte");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        kw_.validateKeyWordDuplicates(s_, keyWords_);
        kw_.validateEscapingsDuplicates(s_, escapings_);
        StringMap<String> nbWordsDec_ = kw_.allNbWords(kw_.allNbWordsDec());
        kw_.validateNbWordDuplicates(s_, nbWordsDec_);
        StringMap<String> nbWordsBin_ = kw_.allNbWords(kw_.allNbWordsBin());
        kw_.validateNbWordDuplicates(s_, nbWordsBin_);
        kw_.validateStartsPrefixesDuplicates(s_);
        lgName_.validatePrimitiveDuplicates(s_, prims_);
        lgName_.validateRefTypeDuplicates(s_, refTypes_);
        lgName_.validateMethodsDuplicates(s_, methods_);
        lgName_.validateFieldsDuplicates(s_, fields_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail57Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasInteger("<");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail58Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasInteger("");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        assertTrue(s_.getClasses().displayStdErrors(),!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail59Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasIterableTableVarFirst(",E");
        lgName_.setAliasIterableTableVarSecond("0E");
        lgName_.setAliasEnumParamVar("");
        lgName_.setAliasIteratorTypeVar("$if");
        lgName_.setAliasIterableVar("$int");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        StringMap<CustList<KeyValueMemberName>> varTypes_ = lgName_.allTableTypeVarTypes();
        lgName_.validateVarTypesContents(s_, varTypes_, prims_);
        kw_.validateKeyWordDuplicates(s_, keyWords_);
        kw_.validateEscapingsDuplicates(s_, escapings_);
        StringMap<String> nbWordsDec_ = kw_.allNbWords(kw_.allNbWordsDec());
        kw_.validateNbWordDuplicates(s_, nbWordsDec_);
        StringMap<String> nbWordsBin_ = kw_.allNbWords(kw_.allNbWordsBin());
        kw_.validateNbWordDuplicates(s_, nbWordsBin_);
        kw_.validateStartsPrefixesDuplicates(s_);
        lgName_.validatePrimitiveDuplicates(s_, prims_);
        lgName_.validateRefTypeDuplicates(s_, refTypes_);
        lgName_.validateMethodsDuplicates(s_, methods_);
        lgName_.validateFieldsDuplicates(s_, fields_);
        lgName_.validateVarTypesDuplicates(s_, varTypes_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail60Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasIterableTableVarFirst("E");
        lgName_.setAliasIterableTableVarSecond("E");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        StringMap<CustList<KeyValueMemberName>> varTypes_ = lgName_.allTableTypeVarTypes();
        lgName_.validateVarTypesContents(s_, varTypes_, prims_);
        kw_.validateKeyWordDuplicates(s_, keyWords_);
        kw_.validateEscapingsDuplicates(s_, escapings_);
        StringMap<String> nbWordsDec_ = kw_.allNbWords(kw_.allNbWordsDec());
        kw_.validateNbWordDuplicates(s_, nbWordsDec_);
        StringMap<String> nbWordsBin_ = kw_.allNbWords(kw_.allNbWordsBin());
        kw_.validateNbWordDuplicates(s_, nbWordsBin_);
        kw_.validateStartsPrefixesDuplicates(s_);
        lgName_.validatePrimitiveDuplicates(s_, prims_);
        lgName_.validateRefTypeDuplicates(s_, refTypes_);
        lgName_.validateMethodsDuplicates(s_, methods_);
        lgName_.validateFieldsDuplicates(s_, fields_);
        lgName_.validateVarTypesDuplicates(s_, varTypes_);
        assertTrue(!s_.getClasses().isEmptyStdError());
    }
    @Test
    public void fail61Test() {
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        lgName_.setAliasHasNextPair("hasNext");
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        s_.setStandards(lgName_);
        StringMap<String> keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringMap<String> escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringMap<String> nbWords_ = kw_.allNbWords(kw_.allNbWordsBasic());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringMap<String> prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringMap<String> refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<CustList<KeyValueMemberName>> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<CustList<KeyValueMemberName>> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        StringMap<CustList<KeyValueMemberName>> varTypes_ = lgName_.allTableTypeVarTypes();
        lgName_.validateVarTypesContents(s_, varTypes_, prims_);
        kw_.validateKeyWordDuplicates(s_, keyWords_);
        kw_.validateEscapingsDuplicates(s_, escapings_);
        StringMap<String> nbWordsDec_ = kw_.allNbWords(kw_.allNbWordsDec());
        kw_.validateNbWordDuplicates(s_, nbWordsDec_);
        StringMap<String> nbWordsBin_ = kw_.allNbWords(kw_.allNbWordsBin());
        kw_.validateNbWordDuplicates(s_, nbWordsBin_);
        kw_.validateStartsPrefixesDuplicates(s_);
        lgName_.validatePrimitiveDuplicates(s_, prims_);
        lgName_.validateRefTypeDuplicates(s_, refTypes_);
        lgName_.validateMethodsDuplicates(s_, methods_);
        lgName_.validateFieldsDuplicates(s_, fields_);
        lgName_.validateVarTypesDuplicates(s_, varTypes_);
        CustList<CustList<KeyValueMemberName>> merge_ = lgName_.allMergeTableTypeMethodNames();
        lgName_.validateMergedDuplicates(s_, merge_);
        assertTrue(!s_.getClasses().isEmptyStdError());
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
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        srcFiles_.put("src/Ex", xml_.toString());
        StringMap<String> others_ = new StringMap<String>();
        others_.put("pkg/hello_res.txt", "content");
        StringMap<String> all_ = new StringMap<String>();
        all_.putAllMap(srcFiles_);
        all_.putAllMap(others_);
        ContextEl contextEl_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        ContextFactory.validate(contextEl_.getAnalysisMessages(),kw_,lgName_,all_,contextEl_,"src");
        assertTrue(contextEl_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId fct_ = new MethodId(MethodAccessKind.STATIC, "exmeth",new StringList());
        Argument argGlLoc_ = new Argument();
        Argument ret_ = ProcessMethod.calculateArgument(argGlLoc_, "pkg.Ex", fct_, args_, contextEl_,null);
        assertEq(2, ret_.getNumber());
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
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        AnalysisMessages a_ = new AnalysisMessages();
        KeyWords kw_ = new KeyWords();
        LgNames lgName_ = new CustLgNames();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        srcFiles_.put("src/Ex", xml_.toString());
        StringMap<String> others_ = new StringMap<String>();
        others_.put("pkg/hello_res.txt", "content");
        StringMap<String> all_ = new StringMap<String>();
        all_.putAllMap(srcFiles_);
        all_.putAllMap(others_);
        ContextEl contextEl_ =ContextFactory.build(-1, lk_, di_, opts_, a_,kw_, lgName_, all_,4,"src");
        assertTrue(contextEl_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId fct_ = new MethodId(MethodAccessKind.STATIC, "exmeth",new StringList());
        Argument argGlLoc_ = new Argument();
        Argument ret_ = ProcessMethod.calculateArgument(argGlLoc_, "pkg.Ex", fct_, args_, contextEl_,null);
        assertEq(2, ret_.getNumber());
    }
    @Test
    public void parseLineArg1Test() {
        StringList args_ = LgNames.parseLineArg("first_arg");
        assertEq(1, args_.size());
        assertEq("first_arg", args_.get(0));
    }
    @Test
    public void parseLineArg2Test() {
        StringList args_ = LgNames.parseLineArg("first_arg second_arg");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_arg", args_.get(1));
    }
    @Test
    public void parseLineArg3Test() {
        StringList args_ = LgNames.parseLineArg("first\\ arg second_arg");
        assertEq(2, args_.size());
        assertEq("first arg", args_.get(0));
        assertEq("second_arg", args_.get(1));
    }
    @Test
    public void parseLineArg4Test() {
        StringList args_ = LgNames.parseLineArg("first_arg second\\ arg");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second arg", args_.get(1));
    }
    @Test
    public void parseLineArg5Test() {
        StringList args_ = LgNames.parseLineArg("first\\\\arg second_arg");
        assertEq(2, args_.size());
        assertEq("first\\arg", args_.get(0));
        assertEq("second_arg", args_.get(1));
    }
    @Test
    public void parseLineArg6Test() {
        StringList args_ = LgNames.parseLineArg("first_arg second\\\\arg");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second\\arg", args_.get(1));
    }
    @Test
    public void parseLineArg7Test() {
        StringList args_ = LgNames.parseLineArg("first\\narg second_arg");
        assertEq(2, args_.size());
        assertEq("first\narg", args_.get(0));
        assertEq("second_arg", args_.get(1));
    }
    @Test
    public void parseLineArg8Test() {
        StringList args_ = LgNames.parseLineArg("first_arg second\\narg");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second\narg", args_.get(1));
    }
    @Test
    public void parseLineArg9Test() {
        StringList args_ = LgNames.parseLineArg("first\\earg second_arg");
        assertEq(2, args_.size());
        assertEq("first arg", args_.get(0));
        assertEq("second_arg", args_.get(1));
    }
    @Test
    public void parseLineArg10Test() {
        StringList args_ = LgNames.parseLineArg("first_arg second\\earg");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second arg", args_.get(1));
    }
    @Test
    public void parseLineArg11Test() {
        StringList args_ = LgNames.parseLineArg("first\\targ second_arg");
        assertEq(2, args_.size());
        assertEq("first\targ", args_.get(0));
        assertEq("second_arg", args_.get(1));
    }
    @Test
    public void parseLineArg12Test() {
        StringList args_ = LgNames.parseLineArg("first_arg second\\targ");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second\targ", args_.get(1));
    }
    @Test
    public void parseLineArg13Test() {
        StringList args_ = LgNames.parseLineArg("first_arg second_arg\\c");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_argc", args_.get(1));
    }
    @Test
    public void parseLineArg14Test() {
        StringList args_ = LgNames.parseLineArg("first_arg second_arg\\cgg");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_argcgg", args_.get(1));
    }
    @Test
    public void parseLineArg15Test() {
        StringList args_ = LgNames.parseLineArg("first_arg second_arg\\c0A");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_arg\n", args_.get(1));
    }
    @Test
    public void parseLineArg16Test() {
        StringList args_ = LgNames.parseLineArg("first_arg second_arg\\c20");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_argc20", args_.get(1));
    }
    @Test
    public void parseLineArg17Test() {
        StringList args_ = LgNames.parseLineArg("first_arg second_arg\\c0Av");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_arg\nv", args_.get(1));
    }
    @Test
    public void parseLineArg18Test() {
        StringList args_ = LgNames.parseLineArg("first_arg second_arg\\c-1");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_argc-1", args_.get(1));
    }
    @Test
    public void parseLineArg19Test() {
        StringList args_ = LgNames.parseLineArg("first_arg second_arg\\u");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_argu", args_.get(1));
    }
    @Test
    public void parseLineArg20Test() {
        StringList args_ = LgNames.parseLineArg("first_arg second_arg\\ugggg");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_argugggg", args_.get(1));
    }
    @Test
    public void parseLineArg21Test() {
        StringList args_ = LgNames.parseLineArg("first_arg second_arg\\u000A");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_arg\n", args_.get(1));
    }
    @Test
    public void parseLineArg22Test() {
        StringList args_ = LgNames.parseLineArg("first_arg second_arg\\u000Av");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_arg\nv", args_.get(1));
    }
    @Test
    public void parseLineArg24Test() {
        StringList args_ = LgNames.parseLineArg("first_arg second_arg\\u-111");
        assertEq(2, args_.size());
        assertEq("first_arg", args_.get(0));
        assertEq("second_argu-111", args_.get(1));
    }
    @Test
    public void parseValue1Test() {
        assertEq("first_arg", LgNames.parseValue("first_arg"));
    }
    @Test
    public void parseValue2Test() {
        assertEq("first_arg ", LgNames.parseValue("first_arg\\e"));
    }
    @Test
    public void parseValue3Test() {
        assertEq("first_arg after", LgNames.parseValue("first_arg\\eafter"));
    }
    @Test
    public void parseValue4Test() {
        assertEq("first_arg\t", LgNames.parseValue("first_arg\\t"));
    }
    @Test
    public void parseValue5Test() {
        assertEq("first_arg\tafter", LgNames.parseValue("first_arg\\tafter"));
    }
    @Test
    public void parseValue6Test() {
        assertEq("first_argc", LgNames.parseValue("first_arg\\c"));
    }
    @Test
    public void parseValue7Test() {
        assertEq("first_argc0", LgNames.parseValue("first_arg\\c0"));
    }
    @Test
    public void parseValue8Test() {
        assertEq("first_arg\n", LgNames.parseValue("first_arg\\c0A"));
    }
    @Test
    public void parseValue9Test() {
        assertEq("first_argc20", LgNames.parseValue("first_arg\\c20"));
    }
    @Test
    public void parseValue10Test() {
        assertEq("first_arg\nafter", LgNames.parseValue("first_arg\\c0Aafter"));
    }
    @Test
    public void parseValue11Test() {
        assertEq("first_argc-1", LgNames.parseValue("first_arg\\c-1"));
    }
    @Test
    public void parseValue12Test() {
        assertEq("first_argu", LgNames.parseValue("first_arg\\u"));
    }
    @Test
    public void parseValue13Test() {
        assertEq("first_argu0", LgNames.parseValue("first_arg\\u0"));
    }
    @Test
    public void parseValue14Test() {
        assertEq("first_arg\n", LgNames.parseValue("first_arg\\u000A"));
    }
    @Test
    public void parseValue15Test() {
        assertEq("first_arg\nafter", LgNames.parseValue("first_arg\\u000Aafter"));
    }
    @Test
    public void parseValue16Test() {
        assertEq("first_argu-111", LgNames.parseValue("first_arg\\u-111"));
    }
    @Test
    public void parseValue17Test() {
        assertEq("first_arg\\", LgNames.parseValue("first_arg\\\\"));
    }
    @Test
    public void parseValue18Test() {
        assertEq("first_arg\\after", LgNames.parseValue("first_arg\\\\after"));
    }
    @Test
    public void parseValue19Test() {
        assertEq("first_arg\n", LgNames.parseValue("first_arg\\n"));
    }
    @Test
    public void parseValue20Test() {
        assertEq("first_arg\nafter", LgNames.parseValue("first_arg\\nafter"));
    }
    @Test
    public void parseValue22Test() {
        assertEq("first_argcgg", LgNames.parseValue("first_arg\\cgg"));
    }
    @Test
    public void parseValue23Test() {
        assertEq("first_argugggg", LgNames.parseValue("first_arg\\ugggg"));
    }
}
