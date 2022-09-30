package code.formathtml.analyze;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.stds.LgNamesContent;
import code.formathtml.Configuration;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.structs.BeanInfo;
import code.formathtml.structs.ValidatorInfo;
import code.formathtml.util.BeanLgNames;
import code.sml.DocumentBuilder;
import code.sml.EncodedChar;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringMap;

public final class AnalyzingDoc extends SetupableAnalyzingDoc {

    private RendAnalysisMessages rendAnalysisMessages = new RendAnalysisMessages();
    private final IdMap<OperationNode, BeanInfo> beansInfos = new IdMap<OperationNode, BeanInfo>();
    private final IdMap<OperationNode, ValidatorInfo> lateValidators = new IdMap<OperationNode, ValidatorInfo>();
    private LgNamesContent content;

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
        setupCommon(_conf, _properties, _messagesFolder);
        beansInfosBefore = _conf.getBeansInfos();
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

    public void setContent(BeanLgNames _adv) {
        content = _adv.getContent();
    }

    public RendAnalysisMessages getRendAnalysisMessages() {
        return rendAnalysisMessages;
    }

    public void setRendAnalysisMessages(RendAnalysisMessages _rendAnalysisMessages) {
        this.rendAnalysisMessages = _rendAnalysisMessages;
    }

    public IdMap<OperationNode, BeanInfo> getBeansInfos() {
        return beansInfos;
    }

    public IdMap<OperationNode, ValidatorInfo> getLateValidators() {
        return lateValidators;
    }

    public StringMap<BeanInfo> getBeansInfosBefore() {
        return beansInfosBefore;
    }

}
