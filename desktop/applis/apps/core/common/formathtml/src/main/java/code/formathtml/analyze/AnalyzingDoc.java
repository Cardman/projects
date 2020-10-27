package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.errors.custom.FoundWarningInterpret;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.stds.LgNamesContent;
import code.formathtml.Configuration;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.analyze.blocks.AnaRendBlock;
import code.formathtml.analyze.blocks.AnaRendDocumentBlock;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.ValidatorInfo;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DualConfigurationContext;
import code.util.*;
import code.util.core.StringUtil;

public final class AnalyzingDoc {
    private StringList languages = new StringList();

    private AnaRendBlock currentBlock;
    private String internGlobalClass="";
    private String attribute="";
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
    private BeanLgNames standards;


    public static void addWarning(FoundWarningInterpret _warning, AnalyzingDoc _analyzingDoc, AnalyzedPageEl _analyzing) {
        _warning.setLocationFile(_analyzingDoc.getLocationFile(_warning.getFileName(),_warning.getIndexFile()));
        _analyzing.addWarning(_warning);
    }

    public static void addError(FoundErrorInterpret _error, AnalyzingDoc _analyzingDoc, AnalyzedPageEl _analyzing) {
        _error.setLocationFile(_analyzingDoc.getLocationFile(_error.getFileName(),_error.getIndexFile()));
        _analyzing.addError(_error);
    }

    public static int getCurrentLocationIndex(AnalyzedPageEl _analyzing, AnalyzingDoc _analyzingDoc) {
        int offset_ = _analyzing.getOffset();
        return _analyzingDoc.getSum(offset_)+ _analyzing.getTraceIndex()-offset_;
    }

    public void setup(Configuration _conf, BeanLgNames _standards, DualConfigurationContext _dual) {
        rendKeyWords = _conf.getRendKeyWords();
        prefix = _conf.getPrefix();
        properties = _dual.getProperties();
        messagesFolder = _dual.getMessagesFolder();
        files = _conf.getFiles();
        standards = _standards;
        beansInfosBefore = _conf.getBeansInfos();
    }
    public static void setupInts(AnalyzedPageEl _page, AnalyzingDoc _analyzingDoc) {
        _page.getMappingLocal().clear();
        _page.setCurrentAnaBlock(null);
        _page.setProcessKeyWord(new AdvancedProcessKeyWord(_page, _analyzingDoc));
        _page.setHiddenTypes(new AdvancedHiddenTypes(_page));
        _page.setCurrentGlobalBlock(new AdvancedCurrentGlobalBlock(_page, _analyzingDoc));
        _page.setCurrentConstraints(new AdvancedCurrentConstraints());
        _page.setAnnotationAnalysis(new AdvancedAnnotationAnalysis());
        _page.setLoopDeclaring(new AdvancedLoopDeclaring(_analyzingDoc));
        _page.setLocalDeclaring(new AdvancedLocalDeclaring(_analyzingDoc));
        _page.setBuildingConstraints(new AdvancedBuildingConstraints(_page));
        _page.setLocalizer(new AdvancedLocalizer(_page, _analyzingDoc));
        _page.setTokenValidation(new AdvancedTokenValidation(_page));
    }

    public String getLocationFile(String _fileName, int _sum) {
        return StringUtil.concat(Long.toString(_sum));
    }

    public static int getSum(int _offset, int _glOffset, RendBlock _currentBlock, String _attribute) {
        int delta_ = getDelta(_offset, _attribute, _currentBlock.getEscapedChars());
        return _glOffset+_offset+delta_;
    }

    public int getSum(int _offset) {
        if (currentBlock == null) {
            return 0;
        }
        int delta_ = getDelta(_offset, attribute, currentBlock.getEscapedChars());
        return _offset+delta_;
    }

    private static int getDelta(int _offset, String _attribute, StringMap<IntTreeMap<Integer>> _escapedChars) {
        int delta_ = 0;
        IntTreeMap< Integer> esc_ = getEscapedChars(_attribute, _escapedChars);
        if (esc_ != null) {
            int nbIndexes_ = getIndexesCount(esc_, _offset);
            for (int i = 0; i < nbIndexes_; i++) {
                delta_ += esc_.getValue(i);
            }
        }
        return delta_;
    }

    private static int getIndexesCount(IntTreeMap< Integer> _t, int _offset) {
        int delta_ = 0;
        int count_ = 0;
        for (EntryCust<Integer, Integer> e: _t.entryList()) {
            if (e.getKey() - delta_ >= _offset) {
                return count_;
            }
            delta_ += e.getValue();
            count_++;
        }
        return count_;
    }
    private static IntTreeMap<Integer> getEscapedChars(String _attribute, StringMap<IntTreeMap<Integer>> _escapedChars) {
        for (EntryCust<String, IntTreeMap< Integer>> t: _escapedChars.entryList()) {
            String c_ = t.getKey();
            if (!StringUtil.quickEq(c_, _attribute)) {
                continue;
            }
            return t.getValue();
        }
        return null;
    }

    public String getAliasCharSequence() {
        return content.getCharSeq().getAliasCharSequence();
    }

    public boolean isInternGlobal() {
        return !getInternGlobalClass().isEmpty();
    }

    public void setAttribute(String _attribute) {
        attribute = _attribute;
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
    public AnaRendBlock getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(AnaRendBlock _currentBlock) {
        this.currentBlock = _currentBlock;
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

    public AnaRendDocumentBlock getCurrentDoc() {
        return currentDoc;
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

    public BeanLgNames getStandards() {
        return standards;
    }

    public StringMap<BeanInfo> getBeansInfosBefore() {
        return beansInfosBefore;
    }
}
