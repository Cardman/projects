package code.formathtml.errors;

import code.expressionlanguage.exec.DefaultInitializer;
import code.expressionlanguage.exec.DefaultLockingClass;
import code.expressionlanguage.SingleContextEl;
import code.expressionlanguage.errors.AnalysisMessages;
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.stds.LgNames;
import code.formathtml.BeanCustLgNamesImpl;
import code.formathtml.Configuration;
import code.formathtml.InitializationLgNames;
import code.formathtml.util.BeanLgNames;
import code.util.StringMap;
import org.junit.Test;


import static org.junit.Assert.assertTrue;

public final class RendKeyWordsTest {

    @Test
    public void fail1() {
        RendAnalysisMessages def_ = new RendAnalysisMessages();
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        BeanLgNames lgName_ = new BeanCustLgNamesImpl();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        Configuration conf_ = new Configuration();
        conf_.setContext(s_);
        AnalysisMessages.validateMessageContents(s_,def_.allMessages());
        RendKeyWords r_ = new RendKeyWords();
        r_.setKeyWordBody("");
        r_.setKeyWordBreak("continue");
        r_.setKeyWordIf("-f");
        StringMap<String> tags_ = r_.allTags();
        r_.validateTagContents(conf_,tags_);
        r_.validateDuplicates(conf_,tags_);
        assertTrue(!conf_.isEmptyStdErrors());
    }

    @Test
    public void fail2() {
        RendAnalysisMessages def_ = new RendAnalysisMessages();
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        BeanLgNames lgName_ = new BeanCustLgNamesImpl();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        Configuration conf_ = new Configuration();
        conf_.setContext(s_);
        AnalysisMessages.validateMessageContents(s_,def_.allMessages());
        RendKeyWords r_ = new RendKeyWords();
        r_.setAttrAction("");
        r_.setAttrAlias("bean");
        r_.setAttrChecked("-f");
        r_.setAttrPrepare("param");
        StringMap<String> tags_ = r_.allAttrs();
        r_.validateAttrContents(conf_,tags_);
        r_.validateDuplicates(conf_,tags_);
        assertTrue(!conf_.isEmptyStdErrors());
    }
    @Test
    public void fail3() {
        RendAnalysisMessages def_ = new RendAnalysisMessages();
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        BeanLgNames lgName_ = new BeanCustLgNamesImpl();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        Configuration conf_ = new Configuration();
        conf_.setContext(s_);
        AnalysisMessages.validateMessageContents(s_,def_.allMessages());
        RendKeyWords r_ = new RendKeyWords();
        r_.setValueRadio("");
        StringMap<String> tags_ = r_.allValues();
        r_.validateValueContents(conf_,tags_);
        r_.validateDuplicates(conf_,tags_);
        assertTrue(!conf_.isEmptyStdErrors());
    }
    @Test
    public void fail4() {
        RendAnalysisMessages def_ = new RendAnalysisMessages();
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        BeanLgNames lgName_ = new BeanCustLgNamesImpl();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        Configuration conf_ = new Configuration();
        conf_.setContext(s_);
        AnalysisMessages.validateMessageContents(s_,def_.allMessages());
        RendKeyWords r_ = new RendKeyWords();
        r_.setStyleValueRgb("");
        r_.setStyleValueRed("-");
        StringMap<String> tags_ = r_.allStyleValues();
        r_.validateStyleValueContents(conf_,tags_);
        r_.validateDuplicates(conf_,tags_);
        assertTrue(!conf_.isEmptyStdErrors());
    }

    @Test
    public void fail5() {
        RendAnalysisMessages def_ = new RendAnalysisMessages();
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        BeanLgNames lgName_ = new BeanCustLgNamesImpl();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        Configuration conf_ = new Configuration();
        conf_.setContext(s_);
        AnalysisMessages.validateMessageContents(s_,def_.allMessages());
        RendKeyWords r_ = new RendKeyWords();
        r_.setStyleAttrBorder("");
        r_.setStyleAttrColor("/");
        StringMap<String> tags_ = r_.allStyleAttrs();
        r_.validateAttrContents(conf_,tags_);
        r_.validateDuplicates(conf_,tags_);
        assertTrue(!conf_.isEmptyStdErrors());
    }

    @Test
    public void fail6() {
        RendAnalysisMessages def_ = new RendAnalysisMessages();
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        BeanLgNames lgName_ = new BeanCustLgNamesImpl();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        Configuration conf_ = new Configuration();
        conf_.setContext(s_);
        AnalysisMessages.validateMessageContents(s_,def_.allMessages());
        RendKeyWords r_ = new RendKeyWords();
        r_.setStyleUnitEm("");
        r_.setStyleUnitPx("0");
        StringMap<String> tags_ = r_.allStyleUnits();
        r_.validateStyleUnitContents(conf_,tags_);
        assertTrue(!conf_.isEmptyStdErrors());
    }

    @Test
    public void fail7() {
        RendAnalysisMessages def_ = new RendAnalysisMessages();
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        BeanLgNames lgName_ = new BeanCustLgNamesImpl();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        Configuration conf_ = new Configuration();
        conf_.setContext(s_);
        AnalysisMessages.validateMessageContents(s_,def_.allMessages());
        RendKeyWords r_ = new RendKeyWords();
        r_.setStyleUnitEm("");
        r_.setStyleUnitPx("/");
        StringMap<String> tags_ = r_.allStyleUnits();
        r_.validateStyleUnitContents(conf_,tags_);
        assertTrue(!conf_.isEmptyStdErrors());
    }
    @Test
    public void fail8() {
        RendAnalysisMessages def_ = new RendAnalysisMessages();
        DefaultLockingClass lk_ = new DefaultLockingClass();
        DefaultInitializer di_ = new DefaultInitializer();
        KeyWords kw_ = new KeyWords();
        BeanLgNames lgName_ = new BeanCustLgNamesImpl();
        InitializationLgNames.basicStandards(lgName_);
        Options opts_ = new Options();
        SingleContextEl s_ = getCtx(lk_, di_, kw_, lgName_, opts_);
        Configuration conf_ = new Configuration();
        conf_.setContext(s_);
        AnalysisMessages.validateMessageContents(s_,def_.allMessages());
        RendKeyWords r_ = new RendKeyWords();
        r_.setStyleAttrBorder("");
        r_.setStyleAttrColor("");
        r_.setAttrAction("");
        r_.setKeyWordBody("");
        r_.setKeyWordIf("");
        r_.setKeyWordBreak("");
        r_.setAttrAlias("");
        r_.setAttrChecked("");
        r_.setStyleValueRed("");
        r_.setStyleValueRgb("");
        r_.setStyleUnitEm("");
        r_.setAttrPrepare("");
        r_.setValueRadio("");
        r_.setStyleUnitPx("");
        r_.setKeyWordWhile("");
        r_.setKeyWordReturn("");
        r_.setKeyWordCatch("");
        r_.setKeyWordClass("");
        r_.setKeyWordDo("");
        r_.setKeyWordSwitch("");
        r_.setKeyWordTry("");
        r_.setKeyWordFor("");
        r_.setKeyWordElse("");
        r_.setKeyWordElseif("");
        r_.setKeyWordCase("");
        r_.setKeyWordThrow("");
        r_.setKeyWordCaption("");
        r_.setAttrKeyClassName("");
        r_.setAttrVarClassName("");
        r_.setAttrIndexClassName("");
        r_.setAttrEscapedAmp("");
        r_.setAttrConvertValue("");
        r_.setAttrConvertFieldValue("");
        r_.setAttrValueMessage("");
        r_.setStyleAttrFontFam("");
        r_.setKeyWordTextarea("");
        r_.setStyleAttrFontSize("");
        r_.setKeyWordMessage("");
        r_.setStyleAttrBackground("");
        r_.setAttrConvertField("");
        r_.setAttrKeepFields("");
        r_.setStyleValueGreen("");
        r_.setStyleValueWhite("");
        r_.setStyleValueMagenta("");
        r_.setStyleValueYellow("");
        r_.setStyleValueCyan("");
        r_.setStyleValueBlack("");
        r_.setStyleUnitSolid("");
        r_.setStyleValueBlue("");
        r_.setStyleValueGrey("");
        r_.setKeyWordFinally("");
        r_.setKeyWordContinue("");
        r_.setKeyWordDefault("");
        r_.setKeyWordPackage("");
        r_.setKeyWordSet("");
        r_.setKeyWordImport("");
        r_.setKeyWordForm("");
        r_.setKeyWordSubmit("");
        r_.setKeyWordField("");
        r_.setKeyWordImg("");
        r_.setKeyWordSelect("");
        r_.setKeyWordAnchor("");
        r_.setKeyWordParam("");
        r_.setKeyWordInput("");
        r_.setKeyWordSpan("");
        r_.setKeyWordHead("");
        r_.setKeyWordPre("");
        r_.setKeyWordHFour("");
        r_.setAttrList("");
        r_.setKeyWordItalic("");
        r_.setKeyWordDiv("");
        r_.setKeyWordOl("");
        r_.setKeyWordHr("");
        r_.setKeyWordHTwo("");
        r_.setKeyWordHSix("");
        r_.setKeyWordLink("");
        r_.setKeyWordUl("");
        r_.setKeyWordBr("");
        r_.setKeyWordHOne("");
        r_.setKeyWordPar("");
        r_.setAttrMap("");
        r_.setKeyWordTh("");
        r_.setKeyWordTr("");
        r_.setKeyWordMap("");
        r_.setKeyWordHFive("");
        r_.setKeyWordTable("");
        r_.setKeyWordLi("");
        r_.setKeyWordBold("");
        r_.setKeyWordStyle("");
        r_.setKeyWordHThree("");
        r_.setAttrVar("");
        r_.setAttrKey("");
        r_.setAttrValue("");
        r_.setKeyWordTd("");
        r_.setAttrFrom("");
        r_.setKeyWordOption("");
        r_.setValueRange("");
        r_.setValueCheckbox("");
        r_.setAttrTo("");
        r_.setAttrInit("");
        r_.setAttrTitle("");
        r_.setAttrMessage("");
        r_.setValueSubmit("");
        r_.setAttrEq("");
        r_.setAttrNi("");
        r_.setAttrQuoted("");
        r_.setAttrClassName("");
        r_.setAttrDefault("");
        r_.setAttrBean("");
        r_.setAttrType("");
        r_.setAttrSelected("");
        r_.setAttrEscaped("");
        r_.setAttrParam("");
        r_.setValueText("");
        r_.setAttrStep("");
        r_.setAttrVarValue("");
        r_.setAttrCondition("");
        r_.setAttrHref("");
        r_.setAttrFor("");
        r_.setAttrForm("");
        r_.setAttrId("");
        r_.setAttrNa("");
        r_.setAttrConvert("");
        r_.setAttrLabel("");
        r_.setAttrName("");
        r_.setAttrNf("");
        r_.setAttrMultiple("");
        r_.setAttrCommand("");
        r_.setValueNumber("");
        r_.setValueLiMinLat("");
        r_.setAttrGroupId("");
        r_.setValueLiMinLet("");
        r_.setAttrValidator("");
        r_.setValueLiMajLet("");
        r_.setValueLiDisk("");
        r_.setAttrPage("");
        r_.setValueStyle("");
        r_.setValueLiRect("");
        r_.setValueLiMajLat("");
        r_.setValueLiCircle("");
        r_.setValueLiSquare("");
        r_.setValueLiNb("");
        r_.setAttrCols("");
        r_.setAttrRel("");
        r_.setAttrSrc("");
        r_.setAttrRows("");
        r_.setAttrClass("");
        r_.setAttrDelay("");
        r_.setAttrWidth("");
        StringMap<String> tags_ = r_.allStyleUnits();
        r_.validateStyleUnitContents(conf_,tags_);
        assertTrue(!conf_.isEmptyStdErrors());
    }
    private static SingleContextEl getCtx(DefaultLockingClass lk_, DefaultInitializer di_, KeyWords kw_, LgNames lgName_, Options opts_) {
        return new SingleContextEl(-1,lk_,di_,opts_,new AnalysisMessages(),kw_,lgName_,4);
    }
}
