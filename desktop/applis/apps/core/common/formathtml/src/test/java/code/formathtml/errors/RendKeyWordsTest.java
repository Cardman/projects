package code.formathtml.errors;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.options.KeyWords;
import code.formathtml.BeanCustLgNamesImpl;
import code.formathtml.EquallableRenderUtil;
import code.formathtml.InitializationLgNamesRender;
import code.formathtml.util.BeanCustLgNames;
import code.formathtml.util.DefaultBeanAliases;
import code.util.StringMap;
import org.junit.Test;


public final class RendKeyWordsTest extends EquallableRenderUtil {

    @Test
    public void fail1() {
        RendAnalysisMessages def_ = new RendAnalysisMessages();
        
        
        KeyWords kw_ = new KeyWords();
        BeanCustLgNames lgName_ = new BeanCustLgNamesImpl();
        InitializationLgNamesRender.basicStandards(lgName_);
        AnalyzedPageEl ac_ = build(kw_, lgName_);

        validateMess(def_, ac_);
        RendKeyWords r_ = new RendKeyWords();
        r_.setKeyWordBody("");
        r_.setKeyWordBreak("continue");
        r_.setKeyWordIf("-f");
        StringMap<String> tags_ = r_.allTags();
        validateTagContents(ac_, r_, tags_);
        validateDuplicates(ac_, r_, tags_);
        assertTrue(!ac_.isEmptyStdError());
    }

    private static void validateTagContents(AnalyzedPageEl _conf, RendKeyWords _r, StringMap<String> _tags) {
        _r.validateTagContents(_tags, _conf);
    }

    @Test
    public void fail2() {
        RendAnalysisMessages def_ = new RendAnalysisMessages();
        
        
        KeyWords kw_ = new KeyWords();
        BeanCustLgNames lgName_ = new BeanCustLgNamesImpl();
        InitializationLgNamesRender.basicStandards(lgName_);
        AnalyzedPageEl ac_ = build(kw_, lgName_);

        validateMess(def_, ac_);
        RendKeyWords r_ = new RendKeyWords();
        r_.setAttrAction("");
        r_.setAttrAlias("bean");
        r_.setAttrChecked("-f");
        r_.setAttrPrepare("param");
        StringMap<String> tags_ = r_.allAttrs();
        validateAttrContents(ac_, r_, tags_);
        validateDuplicates(ac_, r_, tags_);
        assertTrue(!ac_.isEmptyStdError());
    }
    @Test
    public void fail3() {
        RendAnalysisMessages def_ = new RendAnalysisMessages();
        
        
        KeyWords kw_ = new KeyWords();
        BeanCustLgNames lgName_ = new BeanCustLgNamesImpl();
        InitializationLgNamesRender.basicStandards(lgName_);
        AnalyzedPageEl ac_ = build(kw_, lgName_);

        validateMess(def_, ac_);
        RendKeyWords r_ = new RendKeyWords();
        r_.setValueRadio("");
        StringMap<String> tags_ = r_.allValues();
        validateValueContents(ac_, r_, tags_);
        validateDuplicates(ac_, r_, tags_);
        assertTrue(!ac_.isEmptyStdError());
    }

    private static void validateValueContents(AnalyzedPageEl _conf, RendKeyWords _r, StringMap<String> _tags) {
        _r.validateValueContents(_tags, _conf);
    }

    @Test
    public void fail4() {
        RendAnalysisMessages def_ = new RendAnalysisMessages();
        
        
        KeyWords kw_ = new KeyWords();
        BeanCustLgNames lgName_ = new BeanCustLgNamesImpl();
        InitializationLgNamesRender.basicStandards(lgName_);
        AnalyzedPageEl ac_ = build(kw_, lgName_);

        validateMess(def_, ac_);
        RendKeyWords r_ = new RendKeyWords();
        r_.setStyleValueRgb("");
        r_.setStyleValueRed("-");
        StringMap<String> tags_ = r_.allStyleValues();
        validateStyleValueContents(ac_, r_, tags_);
        validateDuplicates(ac_, r_, tags_);
        assertTrue(!ac_.isEmptyStdError());
    }

    private static void validateStyleValueContents(AnalyzedPageEl _conf, RendKeyWords _r, StringMap<String> _tags) {
        _r.validateStyleValueContents(_tags, _conf);
    }

    @Test
    public void fail5() {
        RendAnalysisMessages def_ = new RendAnalysisMessages();
        
        
        KeyWords kw_ = new KeyWords();
        BeanCustLgNames lgName_ = new BeanCustLgNamesImpl();
        InitializationLgNamesRender.basicStandards(lgName_);
        AnalyzedPageEl ac_ = build(kw_, lgName_);

        validateMess(def_, ac_);
        RendKeyWords r_ = new RendKeyWords();
        r_.setStyleAttrBorder("");
        r_.setStyleAttrColor("/");
        StringMap<String> tags_ = r_.allStyleAttrs();
        validateAttrContents(ac_, r_, tags_);
        validateDuplicates(ac_, r_, tags_);
        assertTrue(!ac_.isEmptyStdError());
    }

    @Test
    public void fail6() {
        RendAnalysisMessages def_ = new RendAnalysisMessages();
        
        
        KeyWords kw_ = new KeyWords();
        BeanCustLgNames lgName_ = new BeanCustLgNamesImpl();
        InitializationLgNamesRender.basicStandards(lgName_);
        AnalyzedPageEl ac_ = build(kw_, lgName_);

        validateMess(def_, ac_);
        RendKeyWords r_ = new RendKeyWords();
        r_.setStyleUnitEm("");
        r_.setStyleUnitPx("0");
        StringMap<String> tags_ = r_.allStyleUnits();
        validateStyle(ac_, r_, tags_);
        assertTrue(!ac_.isEmptyStdError());
    }

    @Test
    public void fail7() {
        RendAnalysisMessages def_ = new RendAnalysisMessages();
        
        
        KeyWords kw_ = new KeyWords();
        BeanCustLgNames lgName_ = new BeanCustLgNamesImpl();
        InitializationLgNamesRender.basicStandards(lgName_);
        AnalyzedPageEl ac_ = build(kw_, lgName_);

        validateMess(def_, ac_);
        RendKeyWords r_ = new RendKeyWords();
        r_.setStyleUnitEm("");
        r_.setStyleUnitPx("/");
        StringMap<String> tags_ = r_.allStyleUnits();
        validateStyle(ac_, r_, tags_);
        assertTrue(!ac_.isEmptyStdError());
    }
    @Test
    public void fail8() {
        RendAnalysisMessages def_ = new RendAnalysisMessages();
        
        
        KeyWords kw_ = new KeyWords();
        BeanCustLgNames lgName_ = new BeanCustLgNamesImpl();
        InitializationLgNamesRender.basicStandards(lgName_);
        AnalyzedPageEl ac_ = build(kw_, lgName_);

        validateMess(def_, ac_);
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
        r_.setAttrSgn("");
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
        validateStyle(ac_, r_, tags_);
        assertTrue(!ac_.isEmptyStdError());
    }

    private static void validateStyle(AnalyzedPageEl _conf, RendKeyWords _r, StringMap<String> _tags) {
        _r.validateStyleUnitContents(_tags, _conf);
    }

    private static void validateMess(RendAnalysisMessages _def, AnalyzedPageEl _s) {
        AnalysisMessages.validateMessageContents(_def.allMessages(), _s);
    }

    private static AnalyzedPageEl build(KeyWords _kw, BeanCustLgNames _lgName) {
        AnalyzedPageEl page_ = AnalyzedPageEl.setInnerAnalyzing();
        page_.setLogErr(_lgName);
        page_.setAnalysisMessages(new AnalysisMessages());
        page_.setKeyWords(_kw);
        return page_;
    }

    private static void validateAttrContents(AnalyzedPageEl _conf, RendKeyWords _r, StringMap<String> _tags) {
        _r.validateAttrContents(_tags, _conf);
    }

    private static void validateDuplicates(AnalyzedPageEl _conf, RendKeyWords _r, StringMap<String> _tags) {
        _r.validateDuplicates(_tags, _conf);
    }

    @Test
    public void getAlias4() {
        StringMap<String> def_ = new StringMap<String>();
        def_.put("","value");
        StringMap<String> cust_ = new StringMap<String>();
        cust_.put("","value");
        DefaultBeanAliases lgNamesContent_ = new DefaultBeanAliases();
        lgNamesContent_.build(def_, cust_);
        assertEq("",lgNamesContent_.getAliasBean());
    }

    @Test
    public void getAlias5() {
        StringMap<String> def_ = new StringMap<String>();
        def_.put("","value");
        StringMap<String> cust_ = new StringMap<String>();
        cust_.put("","value");
        RendKeyWords lgNamesContent_ = new RendKeyWords();
        lgNamesContent_.otherStyleUnits(def_, cust_);
        lgNamesContent_.otherTags(def_, cust_);
        lgNamesContent_.otherAttrs(def_, cust_);
        lgNamesContent_.otherStyleAttrs(def_, cust_);
        lgNamesContent_.otherStyleValues(def_, cust_);
        lgNamesContent_.otherValues(def_, cust_);
        assertEq("",lgNamesContent_.getKeyWordAnchor());
    }
}
