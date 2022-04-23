package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.util.*;
import code.util.core.StringUtil;

public final class RendTitledAnchor extends RendElement {
    private final CustList<RendDynOperationNode> opExpAnch;
    private StringList varNames = new StringList();

    private final StringMap<CustList<RendDynOperationNode>> opExpTitle;

    private final StringMap<String> preformatted;
    private final DefExecTextPart textPart;

    public RendTitledAnchor(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText,
                            CustList<RendDynOperationNode> _opExpAnch, StringList _varNames,
                            StringMap<CustList<RendDynOperationNode>> _opExpTitle, StringMap<String> _preformatted, DefExecTextPart _textPart) {
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
        for (EntryCust<String, CustList<RendDynOperationNode>> e:opExpTitle.entryList()) {
            IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(e.getValue(), _ctx, _rendStack);
            String txt_ = RendInput.idRad(args_,_ctx,_rendStack);
            objects_.add(txt_);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
            curWr_.removeAttribute(e.getKey());
        }
        curWr_.setAttribute(_cont.getRendKeyWords().getAttrTitle(), StringUtil.simpleStringsFormat(preformatted.getVal(_cont.getCurrentLanguage()), objects_));
        ownerDocument_.renameNode(curWr_, _cont.getRendKeyWords().getKeyWordAnchor());
        processLink(_cont,curWr_,_read,varNames,textPart, opExpAnch, _ctx, _rendStack);
    }

}
