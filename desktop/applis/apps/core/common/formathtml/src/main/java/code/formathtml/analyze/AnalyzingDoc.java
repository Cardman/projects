package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.stds.LgNamesContent;
import code.formathtml.Configuration;
import code.formathtml.errors.RendKeyWords;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.ValidatorInfo;
import code.formathtml.util.BeanLgNames;
import code.sml.DocumentBuilder;
import code.sml.EncodedChar;
import code.util.*;

public final class AnalyzingDoc {
    private StringList languages = new StringList();

    private String internGlobalClass="";
    private RendAnalysisMessages rendAnalysisMessages = new RendAnalysisMessages();
    private RendKeyWords rendKeyWords = new RendKeyWords();

    private int nextIndex;
    private final IdMap<OperationNode, BeanInfo> beansInfos = new IdMap<OperationNode, BeanInfo>();
    private final IdMap<OperationNode, ValidatorInfo> lateValidators = new IdMap<OperationNode, ValidatorInfo>();
    private LgNamesContent content;
    private String prefix = "";

    private StringMap<String> properties = new StringMap<String>();

    private String messagesFolder = "";
    private StringMap<String> files = new StringMap<String>();
    private StringMap<BeanInfo> beansInfosBefore = new StringMap<BeanInfo>();
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

    public static void addError(FoundErrorInterpret _error, AnalyzedPageEl _analyzing) {
        _error.setLocationFile(AnalyzedPageEl.getLocationFile(_error.getIndexFile(),_error.getFile(),_analyzing.getTabWidth()));
        _analyzing.addError(_error);
    }

    public void setup(Configuration _conf, StringMap<String> _properties, String _messagesFolder) {
        rendKeyWords = _conf.getRendKeyWords();
        prefix = _conf.getPrefix();
        setProperties(_properties);
        setMessagesFolder(_messagesFolder);
        files = _conf.getFiles();
        beansInfosBefore = _conf.getBeansInfos();
    }

    public void setProperties(StringMap<String> _p) {
        this.properties = _p;
    }

    public void setMessagesFolder(String _m) {
        this.messagesFolder = _m;
    }

    public static void setupInts(AnalyzedPageEl _page) {
        _page.getMappingLocal().clear();
        _page.setCurrentBlock(null);
        _page.setCurrentAnaBlockForEachLoop(null);
        _page.setCurrentAnaBlockForEachTable(null);
//        _page.setProcessKeyWord(new AdvancedProcessKeyWord(_page, _analyzingDoc));
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

}
