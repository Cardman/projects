package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class RendTitledAnchor extends RendElement {
    private final CustList<RendDynOperationNode> opExpAnch;
    private StringList varNames = new StringList();

    private final StringMap<ExecTextPart> opExpTitle;

    private final StringMap<String> preformatted;
    private final ExecTextPart textPart;

    public RendTitledAnchor(Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText,
                            CustList<RendDynOperationNode> _opExpAnch, StringList _varNames,
                            StringMap<ExecTextPart> _opExpTitle, StringMap<String> _preformatted, ExecTextPart _textPart) {
        super(_read, _execAttributes, _execAttributesText);
        this.opExpAnch = _opExpAnch;
        this.varNames = _varNames;
        this.opExpTitle = _opExpTitle;
        this.preformatted = _preformatted;
        this.textPart = _textPart;
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
//        ImportingPage ip_ = _cont.getLastPage();
//        ip_.setProcessingAttribute(ATTRIBUTE_VALUE_SUBMIT);
//        ip_.setOffset(var_.length()+1);
//        ip_.setLookForAttrValue(true);
        curWr_.removeAttribute(_cont.getRendKeyWords().getAttrValue());
        curWr_.removeAttribute(_cont.getRendKeyWords().getAttrEscapedAmp());
        StringList objects_ = new StringList();
        for (EntryCust<String, ExecTextPart> e:opExpTitle.entryList()) {
            ExecTextPart r_ = e.getValue();
            objects_.add(RenderingText.render(r_, _ctx, _rendStack));
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                incrAncNb(_cont, (Element) _nextWrite, _rendStack.getFormParts().getIndexes());
                return;
            }
            curWr_.removeAttribute(e.getKey());
        }
        curWr_.setAttribute(_cont.getRendKeyWords().getAttrTitle(), StringUtil.simpleStringsFormat(preformatted.getVal(_cont.getCurrentLanguage()), objects_));
        ownerDocument_.renameNode(curWr_, _cont.getRendKeyWords().getKeyWordAnchor());
        processLink(_cont,curWr_,_read,varNames,textPart, opExpAnch, _ctx, _rendStack);
    }

}
