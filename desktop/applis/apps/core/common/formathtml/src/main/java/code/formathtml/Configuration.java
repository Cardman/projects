package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.ValidatorInfo;
import code.expressionlanguage.exec.calls.PageEl;

import code.expressionlanguage.structs.Struct;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.util.*;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.util.*;
import code.util.core.StringUtil;

public final class Configuration {

    private static final String SEP = ":";

    private static final String EMPTY_STRING = "";

    private static final int DEFAULT_TAB_WIDTH = 4;
    private static final String SEPARATOR_PATH = "/";
    private static final String IMPLICIT_LANGUAGE = "//";

    private String firstUrl = EMPTY_STRING;

    private StringMap<BeanInfo> beansInfos = new StringMap<BeanInfo>();

    private StringMap<StringMap<String>> navigation = new StringMap<StringMap<String>>();

    private int tabWidth = DEFAULT_TAB_WIDTH;

    private StringMap<ValidatorInfo> lateValidators = new StringMap<ValidatorInfo>();

    private String prefix = EMPTY_STRING;

    private final StringMap<Struct> builtBeans = new StringMap<Struct>();
    private final StringMap<Struct> builtValidators = new StringMap<Struct>();

    private HtmlPage htmlPage = new HtmlPage();

    private Document document;

    private String beanName;
    private final CustList<ImportingPage> importing = new CustList<ImportingPage>();

    private String currentUrl = "";

    private StringMap<RendDocumentBlock> renders = new StringMap<RendDocumentBlock>();
    private FormParts formParts = new FormParts();

    private Struct mainBean;
    private String currentLanguage = "";
    private final RendKeyWords rendKeyWords = new RendKeyWords();
    private RendDocumentBlock rendDocumentBlock;
    private StringMap<String> files = new StringMap<String>();

    public static String getRealFilePath(String _lg, String _link) {
        return StringUtil.replace(_link, IMPLICIT_LANGUAGE, StringUtil.concat(SEPARATOR_PATH,_lg,SEPARATOR_PATH));
    }

    public void init(DualConfigurationContext _dual) {
        htmlPage = new HtmlPage();
        document = null;
        currentUrl = firstUrl;
        prefix = StringUtil.concat(prefix,SEP);
        _dual.getRenderFiles().removeAllString(firstUrl);
        _dual.getRenderFiles().add(firstUrl);
    }

    public StringMap<AnaRendDocumentBlock> analyzedRenders(StringMap<String> _files, AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, BeanLgNames _advStandards, DualConfigurationContext _dual) {
        renders.clear();
        setFiles(_files);
        _analyzingDoc.setup(this, _advStandards, _dual);
        AnalyzingDoc.setupInts(_page, _analyzingDoc);


        StringMap<AnaRendDocumentBlock> d_ = new StringMap<AnaRendDocumentBlock>();
        for (String s: _dual.getRenderFiles()) {
            String link_ = getRealFilePath(currentLanguage,s);
            String file_ = _files.getVal(link_);
            DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(file_);
            Document document_ = res_.getDocument();
            if (document_ == null) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFileName(_analyzingDoc.getFileName());
                badEl_.setIndexFile(AnalyzingDoc.getCurrentLocationIndex(_page, _analyzingDoc));
                badEl_.buildError(_analyzingDoc.getRendAnalysisMessages().getBadDocument(),
                        res_.getLocation().display());
                AnalyzingDoc.addError(badEl_, _analyzingDoc, _page);
                continue;
            }
            AnaRendDocumentBlock anaDoc_ = AnaRendDocumentBlock.newRendDocumentBlock(_analyzingDoc.getPrefix(), document_, file_, _page.getPrimTypes(), link_, _analyzingDoc.getRendKeyWords());
            d_.addEntry(link_,anaDoc_);
        }
        for (AnaRendDocumentBlock v: d_.values()) {
            v.buildFctInstructions(_analyzingDoc, _page);
        }
        String currentUrl_ = getFirstUrl();
        String realFilePath_ = getRealFilePath(currentLanguage, currentUrl_);
        if (d_.getVal(realFilePath_) == null) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFileName(_analyzingDoc.getFileName());
            badEl_.setIndexFile(AnalyzingDoc.getCurrentLocationIndex(_page, _analyzingDoc));
            badEl_.buildError(_analyzingDoc.getRendAnalysisMessages().getInexistantFile(),
                    realFilePath_);
            AnalyzingDoc.addError(badEl_, _analyzingDoc, _page);
        }
        return d_;
    }

    public String getFirstUrl() {
        return firstUrl;
    }

    public void setFirstUrl(String _firstUrl) {
        firstUrl = _firstUrl;
    }

    public StringMap<BeanInfo> getBeansInfos() {
        return beansInfos;
    }

    public void setBeansInfos(StringMap<BeanInfo> _beansInfos) {
        beansInfos = _beansInfos;
    }

    public StringMap<StringMap<String>> getNavigation() {
        return navigation;
    }

    public void setNavigation(StringMap<StringMap<String>> _navigation) {
        navigation = _navigation;
    }

    public HtmlPage getHtmlPage() {
        return htmlPage;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _tabWidth) {
        tabWidth = _tabWidth;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document _document) {
        document = _document;
    }


    public void clearPages() {
        importing.clear();
    }
    public void addPage(ImportingPage _page) {
        importing.add(_page);
    }
    public ImportingPage getLastPage() {
        return importing.last();
    }
    public void removeLastPage() {
        importing.removeQuicklyLast();
    }
    public CustList<ImportingPage> getImporting() {
        return importing;
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String _currentUrl) {
        currentUrl = _currentUrl;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String _prefix) {
        prefix = _prefix;
    }

    public StringMap<ValidatorInfo> getLateValidators() {
        return lateValidators;
    }

    public void setLateValidators(StringMap<ValidatorInfo> _lateValidators) {
        this.lateValidators = _lateValidators;
    }

    public StringMap<Struct> getBuiltBeans() {
        return builtBeans;
    }

    public StringMap<Struct> getBuiltValidators() {
        return builtValidators;
    }


    public RendKeyWords getRendKeyWords() {
        return rendKeyWords;
    }

    public void setOffset(int _offset) {
        getLastPage().setOffset(_offset);
    }
    public void setOpOffset(int _offset) {
        getLastPage().setOpOffset(_offset);
    }

    public PageEl getPageEl() {
        return importing.last().getPageEl();
    }

    public Struct getInternGlobal() {
        return getLastPage().getInternGlobal();
    }

    public boolean hasPages() {
        return !importing.isEmpty();
    }

    public StringMap<RendDocumentBlock> getRenders() {
        return renders;
    }

    public FormParts getFormParts() {
        return formParts;
    }

    public Struct getMainBean() {
        return mainBean;
    }

    public void setMainBean(Struct _mainBean) {
        mainBean = _mainBean;
    }

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(String _currentLanguage) {
        currentLanguage = _currentLanguage;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String _beanName) {
        beanName = _beanName;
    }


    public RendDocumentBlock getRendDocumentBlock() {
        return rendDocumentBlock;
    }

    public void setRendDocumentBlock(RendDocumentBlock _rendDocumentBlock) {
        this.rendDocumentBlock = _rendDocumentBlock;
    }

    public void setFiles(StringMap<String> _files) {
        files = _files;
    }

    public StringMap<String> getFiles() {
        return files;
    }

}
