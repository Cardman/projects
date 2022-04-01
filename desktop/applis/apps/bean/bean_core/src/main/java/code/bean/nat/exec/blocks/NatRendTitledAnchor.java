package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatRendStackCall;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class NatRendTitledAnchor extends NatRendElement {
    private final CustList<RendDynOperationNode> opExpAnch;
    private StringList varNames = new StringList();

    private final StringMap<String> preformatted;
    private final ExecTextPart textPart;

    public NatRendTitledAnchor(Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText,
                               CustList<RendDynOperationNode> _opExpAnch, StringList _varNames,
                               StringMap<String> _preformatted, ExecTextPart _textPart) {
        super(_read, _execAttributes, _execAttributesText);
        this.opExpAnch = _opExpAnch;
        this.varNames = _varNames;
        this.preformatted = _preformatted;
        this.textPart = _textPart;
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, NatRendStackCall _rendStack) {
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
//        ImportingPage ip_ = _cont.getLastPage();
//        ip_.setProcessingAttribute(ATTRIBUTE_VALUE_SUBMIT);
//        ip_.setOffset(var_.length()+1);
//        ip_.setLookForAttrValue(true);
        curWr_.removeAttribute(_cont.getRendKeyWords().getAttrValue());
        curWr_.removeAttribute(_cont.getRendKeyWords().getAttrEscapedAmp());
        StringList objects_ = new StringList();
        curWr_.setAttribute(_cont.getRendKeyWords().getAttrTitle(), StringUtil.simpleStringsFormat(preformatted.getVal(_cont.getCurrentLanguage()), objects_));
        ownerDocument_.renameNode(curWr_, _cont.getRendKeyWords().getKeyWordAnchor());
        RendBlockHelp.feed(varNames, opExpAnch, _rendStack);
        RendBlockHelp.processLink(_cont, curWr_, _read, textPart, _rendStack);
    }

}
