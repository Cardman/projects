package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.errors.custom.FoundWarningInterpret;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.common.FileMetrics;
import code.expressionlanguage.stds.LgNamesContent;
import code.formathtml.Configuration;
import code.formathtml.common.RendBlockUtil;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.ValidatorInfo;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DualConfigurationContext;
import code.sml.DocumentBuilder;
import code.sml.EncodedChar;
import code.util.*;
import code.util.core.StringUtil;

public final class AnalyzingDoc {
    private StringList languages = new StringList();

    private String internGlobalClass="";
    private String fileName="";
    private AnaRendDocumentBlock currentDoc;
    private RendAnalysisMessages rendAnalysisMessages = new RendAnalysisMessages();
    private RendKeyWords rendKeyWords = new RendKeyWords();

    private int nextIndex;
    private IdMap<OperationNode, BeanInfo> beansInfos = new IdMap<OperationNode, BeanInfo>();
    private IdMap<OperationNode, ValidatorInfo> lateValidators = new IdMap<OperationNode, ValidatorInfo>();
    private LgNamesContent content;
    private String prefix = "";

    private StringMap<String> properties = new StringMap<String>();

    private String messagesFolder = "";
    private StringMap<String> files = new StringMap<String>();
    private StringMap<BeanInfo> beansInfosBefore = new StringMap<BeanInfo>();
    private StringMap<AnaRendDocumentBlock> docs = new StringMap<AnaRendDocumentBlock>();
    private int tabWidth;
    private CustList<EncodedChar> encoded;
    public AnalyzingDoc() {
        setEncoded(DocumentBuilder.possibleEncodes());
    }

    public CustList<EncodedChar> getEncoded() {
        return encoded;
    }

    public void setEncoded(CustList<EncodedChar> _enc) {
        this.encoded = _enc;
    }

    public static void addWarning(FoundWarningInterpret _warning, AnalyzingDoc _analyzingDoc, AnalyzedPageEl _analyzing) {
        _warning.setLocationFile(_analyzingDoc.getLocationFile(_warning.getFileName(),_warning.getIndexFile()));
        _analyzing.addWarning(_warning);
    }

    public static void addError(FoundErrorInterpret _error, AnalyzingDoc _analyzingDoc, AnalyzedPageEl _analyzing) {
        _error.setLocationFile(_analyzingDoc.getLocationFile(_error.getFileName(),_error.getIndexFile()));
        _analyzing.addError(_error);
    }

    public static int getCurrentLocationIndex(AnalyzedPageEl _analyzing, AnalyzingDoc _analyzingDoc) {
        return _analyzingDoc.getSum(_analyzing.getTraceIndex());
    }

    public void setup(Configuration _conf, DualConfigurationContext _dual) {
        rendKeyWords = _conf.getRendKeyWords();
        prefix = _conf.getPrefix();
        properties = _dual.getProperties();
        messagesFolder = _dual.getMessagesFolder();
        files = _conf.getFiles();
        beansInfosBefore = _conf.getBeansInfos();
        tabWidth = _dual.getOptions().getTabWidth();
    }
    public static void setupInts(AnalyzedPageEl _page, AnalyzingDoc _analyzingDoc) {
        _page.getMappingLocal().clear();
        _page.setCurrentBlock(null);
        _page.setCurrentAnaBlockForEachLoop(null);
        _page.setCurrentAnaBlockForEachTable(null);
        _page.setProcessKeyWord(new AdvancedProcessKeyWord(_page, _analyzingDoc));
        _page.setLocalizer(new AdvancedLocalizer(_page, _analyzingDoc));
    }

    public String getLocationFile(String _fileName, int _sum) {
        AnaRendDocumentBlock val_ = getDocs().getVal(_fileName);
        if (val_ == null) {
            return "";
        }
        FileMetrics metrics_ = val_.getFileBlock().getMetrics(tabWidth);
        int su_ = getSum(_sum,val_);
        int r_ = metrics_.getRowFile(su_);
        int c_ = metrics_.getColFile(su_,r_);
        return StringUtil.concat( Long.toString(r_),",",Long.toString(c_),",",Long.toString(_sum));
    }

    public int getSum(int _offset) {
        if (currentDoc == null) {
            return 0;
        }
        return getSum(_offset, currentDoc);
    }

    private static int getSum(int _offset, AnaRendDocumentBlock _currentDoc) {
        return RendBlockUtil.retrieve(_offset, _currentDoc.getEscapedChar());
    }

    public String getAliasCharSequence() {
        return content.getCharSeq().getAliasCharSequence();
    }

    public boolean isInternGlobal() {
        return !getInternGlobalClass().isEmpty();
    }

    public StringList getLanguages() {
        return languages;
    }

    public void setLanguages(StringList _languages) {
        languages = _languages;
    }

    public void setContent(BeanLgNames _adv) {
        content = _adv.getContent();
    }

    public String getInternGlobalClass() {
        return internGlobalClass;
    }

    public void setInternGlobalClass(String _internGlobalClass) {
        internGlobalClass = _internGlobalClass;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        fileName = _fileName;
    }

    public void setCurrentDoc(AnaRendDocumentBlock _current) {
        this.currentDoc = _current;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int _nextIndex) {
        this.nextIndex = _nextIndex;
    }

    public RendAnalysisMessages getRendAnalysisMessages() {
        return rendAnalysisMessages;
    }

    public void setRendAnalysisMessages(RendAnalysisMessages _rendAnalysisMessages) {
        this.rendAnalysisMessages = _rendAnalysisMessages;
    }

    public RendKeyWords getRendKeyWords() {
        return rendKeyWords;
    }

    public IdMap<OperationNode, BeanInfo> getBeansInfos() {
        return beansInfos;
    }

    public IdMap<OperationNode, ValidatorInfo> getLateValidators() {
        return lateValidators;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getMessagesFolder() {
        return messagesFolder;
    }

    public StringMap<String> getProperties() {
        return properties;
    }

    public StringMap<String> getFiles() {
        return files;
    }

    public StringMap<BeanInfo> getBeansInfosBefore() {
        return beansInfosBefore;
    }

    public StringMap<AnaRendDocumentBlock> getDocs() {
        return docs;
    }

    public void setDocs(StringMap<AnaRendDocumentBlock> _docs) {
        this.docs = _docs;
    }
}
