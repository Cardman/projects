package code.formathtml;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.ClassesUtil;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.syntax.ResultExpression;
import code.expressionlanguage.analyze.types.AnaResultPartType;
import code.expressionlanguage.analyze.types.ResolvingTypes;
import code.expressionlanguage.analyze.variables.AnaLocalVariable;
import code.expressionlanguage.common.ConstType;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.formathtml.analyze.AnalyzingDoc;
import code.formathtml.analyze.RenderAnalysis;
import code.formathtml.analyze.VirtualImportingBlock;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.analyze.syntax.RendSplitExpressionUtil;
import code.formathtml.common.AdvFileEscapedCalc;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.ValidatorInfo;
import code.formathtml.util.DualConfigurationContext;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.DocumentResult;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class Configuration {

    private static final String SEP = ":";

    private static final String EMPTY_STRING = "";

    private static final int DEFAULT_TAB_WIDTH = 4;

    private String firstUrl = EMPTY_STRING;

    private StringMap<BeanInfo> beansInfos = new StringMap<BeanInfo>();

    private StringMap<StringMap<String>> navigation = new StringMap<StringMap<String>>();

    private int tabWidth = DEFAULT_TAB_WIDTH;

    private StringMap<ValidatorInfo> lateValidators = new StringMap<ValidatorInfo>();

    private String prefix = EMPTY_STRING;

    private String currentLanguage = "";
    private final RendKeyWords rendKeyWords = new RendKeyWords();
    private StringMap<String> files = new StringMap<String>();

    public void init(DualConfigurationContext _dual) {
        updatePref();
        _dual.getRenderFiles().removeAllString(firstUrl);
        _dual.getRenderFiles().add(firstUrl);
    }

    public void updatePref() {
        prefix = StringUtil.concat(prefix,SEP);
    }

    public void initInstancesPattern(AnalyzedPageEl _page, AnalyzingDoc _anaDoc, FileBlock _conf) {
        String keyWordNew_ = _page.getKeyWords().getKeyWordNew();
        _page.setImportingAcces(new VirtualImportingBlock());
        _page.getCurrentParts().clear();
        _page.getCurrentNumbers().clear();
        _page.getCurrentAnnotDelNew().clear();
        _page.getCurrentAnnotDelSwitch().clear();
        _page.setCurrentBlock(null);
        _page.setImporting(null);
        _page.setCurrentFile(_conf);
        _page.setAccessStaticContext(MethodAccessKind.STATIC);
        for (EntryCust<String, BeanInfo> e: getBeansInfos().entryList()) {
            BeanInfo info_ = e.getValue();
            AnaResultPartType type_ = ResolvingTypes.resolveCorrectTypeWithoutErrorsExact(0, info_.getClassName(), _page);
            String clName_ = type_.getResult(_page);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            lInfo_.setClassName(clName_);
            lInfo_.setConstType(ConstType.FIX_VAR);
            String keyWordVar_ = _page.getKeyWords().getKeyWordVar();
            _page.getInfosVars().put(keyWordVar_, lInfo_);
            ResultExpression res_ = new ResultExpression();
            res_.setAnalyzedString(StringUtil.concat(keyWordVar_+"="+keyWordNew_, "()"));
            OperationNode root_ = RenderAnalysis.getRootAnalyzedOperations(res_, 0, _anaDoc, _page);
            info_.setResolvedClassName(clName_);
            OperationNode nextSibling_ = root_.getFirstChild().getNextSibling();
            nextSibling_.setOrder(0);
            _anaDoc.getBeansInfos().addEntry(nextSibling_,info_);
            _page.getInfosVars().removeKey(keyWordVar_);
        }
        for (EntryCust<String,ValidatorInfo> e: getLateValidators().entryList()) {
            ValidatorInfo v_ = e.getValue();
            AnaResultPartType type_ = ResolvingTypes.resolveCorrectTypeWithoutErrorsExact(0, v_.getClassName(), _page);
            String clName_ = type_.getResult(_page);
            AnaLocalVariable lInfo_ = new AnaLocalVariable();
            lInfo_.setClassName(clName_);
            lInfo_.setConstType(ConstType.FIX_VAR);
            String keyWordVar_ = _page.getKeyWords().getKeyWordVar();
            _page.getInfosVars().put(keyWordVar_, lInfo_);
            ResultExpression res_ = new ResultExpression();
            res_.setAnalyzedString(StringUtil.concat(keyWordVar_+"="+keyWordNew_, "()"));
            OperationNode root_ = RenderAnalysis.getRootAnalyzedOperations(res_, 0, _anaDoc, _page);
            OperationNode nextSibling_ = root_.getFirstChild().getNextSibling();
            nextSibling_.setOrder(0);
            _anaDoc.getLateValidators().addEntry(nextSibling_,v_);
            _page.getInfosVars().removeKey(keyWordVar_);
        }
    }
    public StringMap<AnaRendDocumentBlock> analyzedRenders(StringMap<String> _files, AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, DualConfigurationContext _dual, FileBlock _confFile) {
        setFiles(_files);
        _analyzingDoc.setup(this, _dual.getProperties(), _dual.getMessagesFolder());
        AnalyzingDoc.setupInts(_page);


        StringMap<AnaRendDocumentBlock> d_ = new StringMap<AnaRendDocumentBlock>();
        for (String s: _dual.getRenderFiles()) {
            String file_ = _files.getVal(s);
            DocumentResult res_ = DocumentBuilder.parseSaxNotNullRowCol(file_);
            Document document_ = res_.getDocument();
            AdvFileEscapedCalc es_ = new AdvFileEscapedCalc(AnaRendBlock.getIndexesSpecChars(file_, _analyzingDoc.getEncoded()));
            FileBlock fileBl_ = new FileBlock(0, false, s, es_);
            fileBl_.metrics(file_);
            if (document_ == null) {
                FoundErrorInterpret badEl_ = new FoundErrorInterpret();
                badEl_.setFile(fileBl_);
                badEl_.setIndexFile(_page);
                badEl_.buildError(_analyzingDoc.getRendAnalysisMessages().getBadDocument(),
                        res_.getLocation().display());
                AnalyzingDoc.addError(badEl_, _page);
                continue;
            }
            AnaRendDocumentBlock anaDoc_ = AnaRendBlock.newRendDocumentBlock(document_, file_, _page, _analyzingDoc, es_, fileBl_);
            d_.addEntry(s,anaDoc_);
        }
        buildDocs(_analyzingDoc, _page, d_, _analyzingDoc.getBeansInfosBefore());
        String currentUrl_ = getFirstUrl();
        if (d_.getVal(currentUrl_) == null) {
            FoundErrorInterpret badEl_ = new FoundErrorInterpret();
            badEl_.setFile(_confFile);
            badEl_.setIndexFile(_page);
            badEl_.buildError(_analyzingDoc.getRendAnalysisMessages().getInexistantFile(),
                    currentUrl_);
            AnalyzingDoc.addError(badEl_, _page);
        }
        return d_;
    }

    private static void buildDocs(AnalyzingDoc _analyzingDoc, AnalyzedPageEl _page, StringMap<AnaRendDocumentBlock> _d, StringMap<BeanInfo> _beansInfosBefore) {
        for (AnaRendDocumentBlock v : _d.values()) {
            v.initMetrics(_analyzingDoc,_page,_beansInfosBefore);
        }
        _page.setNextResults(RendSplitExpressionUtil.getNextResults(_analyzingDoc,_page,_d.values()));
        for (AnaRendDocumentBlock v : _d.values()) {
            v.buildFctInstructions(_analyzingDoc, _page);
        }
        ClassesUtil.processAnonymous(_page);
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

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _tabWidth) {
        tabWidth = _tabWidth;
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


    public RendKeyWords getRendKeyWords() {
        return rendKeyWords;
    }

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(String _currentLanguage) {
        currentLanguage = _currentLanguage;
    }


    public void setFiles(StringMap<String> _files) {
        files = _files;
    }

    public StringMap<String> getFiles() {
        return files;
    }

}
