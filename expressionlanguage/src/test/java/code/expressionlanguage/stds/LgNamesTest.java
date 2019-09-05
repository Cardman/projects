package code.expressionlanguage.stds;

import code.expressionlanguage.*;
import code.expressionlanguage.classes.CustLgNames;
import code.expressionlanguage.methods.ProcessMethod;
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        assertTrue(!s_.getClasses().isEmptyStdError());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<StringList> fields_ = lgName_.allTableTypeFieldNames();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<StringList> fields_ = lgName_.allTableTypeFieldNames();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<StringList> fields_ = lgName_.allTableTypeFieldNames();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<StringList> fields_ = lgName_.allTableTypeFieldNames();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<StringList> fields_ = lgName_.allTableTypeFieldNames();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<StringList> fields_ = lgName_.allTableTypeFieldNames();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<StringList> fields_ = lgName_.allTableTypeFieldNames();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<StringList> fields_ = lgName_.allTableTypeFieldNames();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<StringList> fields_ = lgName_.allTableTypeFieldNames();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<StringList> fields_ = lgName_.allTableTypeFieldNames();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<StringList> fields_ = lgName_.allTableTypeFieldNames();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<StringList> fields_ = lgName_.allTableTypeFieldNames();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<StringList> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        kw_.validateKeyWordDuplicates(s_, keyWords_);
        kw_.validateEscapingsDuplicates(s_, escapings_);
        StringList nbWordsDec_ = kw_.allNbWords(kw_.getKeyWordNbExpDec());
        kw_.validateNbWordDuplicates(s_, nbWordsDec_);
        StringList nbWordsBin_ = kw_.allNbWords(kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<StringList> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        kw_.validateKeyWordDuplicates(s_, keyWords_);
        kw_.validateEscapingsDuplicates(s_, escapings_);
        StringList nbWordsDec_ = kw_.allNbWords(kw_.getKeyWordNbExpDec());
        kw_.validateNbWordDuplicates(s_, nbWordsDec_);
        StringList nbWordsBin_ = kw_.allNbWords(kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        StringMap<StringList> methods_ = lgName_.allTableTypeMethodNames();
        lgName_.validateMethodsContents(s_, methods_, prims_);
        StringMap<StringList> fields_ = lgName_.allTableTypeFieldNames();
        lgName_.validateFieldsContents(s_, fields_, prims_);
        kw_.validateKeyWordDuplicates(s_, keyWords_);
        kw_.validateEscapingsDuplicates(s_, escapings_);
        StringList nbWordsDec_ = kw_.allNbWords(kw_.getKeyWordNbExpDec());
        kw_.validateNbWordDuplicates(s_, nbWordsDec_);
        StringList nbWordsBin_ = kw_.allNbWords(kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
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
        SingleContextEl s_ = new SingleContextEl(-1,lk_,di_,opts_,kw_,lgName_,4);
        s_.setStandards(lgName_);
        StringList keyWords_ = kw_.allKeyWords();
        kw_.validateKeyWordContents(s_, keyWords_);
        StringList escapings_ = kw_.allEscapings();
        kw_.validateEscapingsContents(s_, escapings_);
        StringList nbWords_ = kw_.allNbWords(kw_.getKeyWordNbExpDec(), kw_.getKeyWordNbExpBin(),kw_.getKeyWordNbHex());
        kw_.validateNbWordContents(s_, nbWords_);
        kw_.validateBinarySeparators(s_);
        StringList prims_ = lgName_.allPrimitives();
        lgName_.validatePrimitiveContents(s_, prims_);
        StringList refTypes_ = lgName_.allRefTypes();
        lgName_.validateRefTypeContents(s_, refTypes_, prims_);
        assertTrue(s_.getClasses().displayStdErrors(),!s_.getClasses().isEmptyStdError());
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
        ContextEl contextEl_ = new SingleContextEl(-1, lk_, di_, opts_, kw_, lgName_, 4);
        ContextFactory.validate(kw_,lgName_,all_,contextEl_,"src");
        assertTrue(contextEl_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId fct_ = new MethodId(true, "exmeth",new StringList());
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
        ContextEl contextEl_ =ContextFactory.build(-1, lk_, di_, opts_, kw_, lgName_, all_,4,"src");
        assertTrue(contextEl_.getClasses().isEmptyErrors());
        CustList<Argument> args_ = new CustList<Argument>();
        MethodId fct_ = new MethodId(true, "exmeth",new StringList());
        Argument argGlLoc_ = new Argument();
        Argument ret_ = ProcessMethod.calculateArgument(argGlLoc_, "pkg.Ex", fct_, args_, contextEl_,null);
        assertEq(2, ret_.getNumber());
    }
}
