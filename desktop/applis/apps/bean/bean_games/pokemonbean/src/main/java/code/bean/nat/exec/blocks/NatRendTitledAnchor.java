package code.bean.nat.exec.blocks;

import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class NatRendTitledAnchor extends NatRendElementForm {
    private final CustList<NatExecOperationNode> opExpAnch;
    private final StringList varNames;

    private final StringMap<String> preformatted;
    private final NatExecTextPart textPart;

    public NatRendTitledAnchor(Element _read, StringMap<NatExecTextPart> _execAttributes, StringMap<NatExecTextPart> _execAttributesText,
                               CustList<NatExecOperationNode> _opExpAnch, StringList _varNames,
                               StringMap<String> _preformatted, NatExecTextPart _textPart) {
        super(_read, _execAttributes, _execAttributesText);
        this.opExpAnch = _opExpAnch;
        this.varNames = _varNames;
        this.preformatted = _preformatted;
        this.textPart = _textPart;
    }

    void titled(NatConfigurationCore _cont, Node _nextWrite, NatRendStackCall _rendStack) {
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
//        ImportingPage ip_ = _cont.getLastPage();
//        ip_.setProcessingAttribute(ATTRIBUTE_VALUE_SUBMIT);
//        ip_.setOffset(var_.length()+1);
//        ip_.setLookForAttrValue(true);
        curWr_.removeAttribute(_cont.getRendKeyWords().getKeyWordsAttrs().getAttrValue());
        curWr_.removeAttribute(_cont.getRendKeyWords().getKeyWordsAttrs().getAttrEscapedAmp());
        StringList objects_ = new StringList();
        curWr_.setAttribute(_cont.getRendKeyWords().getKeyWordsAttrs().getAttrTitle(), StringUtil.simpleStringsFormat(preformatted.getVal(_cont.getCurrentLanguage()), objects_));
        ownerDocument_.renameNode(curWr_, _cont.getRendKeyWords().getKeyWordsTags().getKeyWordAnchor());
        NatRendElementForm.feed(varNames, opExpAnch, _rendStack);
        NatRendElementForm.processLink(_cont, curWr_, textPart, _rendStack);
    }

}
