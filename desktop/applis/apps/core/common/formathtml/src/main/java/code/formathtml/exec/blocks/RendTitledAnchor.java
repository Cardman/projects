package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.fwd.RendGeneLinkTypes;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.util.*;
import code.util.core.StringUtil;

public final class RendTitledAnchor extends RendElement {
    private final RendGeneLinkTypes opExpAnch;

    private final StringMap<CustList<RendDynOperationNode>> opExpTitle;

    private final StringMap<String> preformatted;
    private final StringMap<CustList<RendDynOperationNode>> textPart;

    public RendTitledAnchor(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText,
                            RendGeneLinkTypes _opExpAnch,
                            StringMap<CustList<RendDynOperationNode>> _opExpTitle, StringMap<String> _preformatted, StringMap<CustList<RendDynOperationNode>> _textPart) {
        super(_read, _execAttributes, _execAttributesText);
        this.opExpAnch = _opExpAnch;
        this.opExpTitle = _opExpTitle;
        this.preformatted = _preformatted;
        this.textPart = _textPart;
    }

    @Override
    protected boolean processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        Element curWr_ = processTitle(_cont, (Element) _nextWrite, _ctx, _rendStack, opExpTitle, preformatted);
        if (curWr_ == null) {
            return true;
        }
        processLink(_cont,curWr_,_read, textPart, opExpAnch, _ctx, _rendStack);
        return _ctx.callsOrException(_rendStack.getStackCall());
    }

    public static Element processTitle(Configuration _cont, Element _nextWrite, ContextEl _ctx, RendStackCall _rendStack, StringMap<CustList<RendDynOperationNode>> _opExpTitle, StringMap<String> _preformatted) {
        Document ownerDocument_ = _nextWrite.getOwnerDocument();
//        ImportingPage ip_ = _cont.getLastPage();
//        ip_.setProcessingAttribute(ATTRIBUTE_VALUE_SUBMIT);
//        ip_.setOffset(var_.length()+1);
//        ip_.setLookForAttrValue(true);
        _nextWrite.removeAttribute(_cont.getRendKeyWords().getAttrValue());
        _nextWrite.removeAttribute(_cont.getRendKeyWords().getAttrEscapedAmp());
        StringList objects_ = new StringList();
        for (EntryCust<String, CustList<RendDynOperationNode>> e: _opExpTitle.entryList()) {
            IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(e.getValue(), _ctx, _rendStack);
            String txt_ = RendInput.idRad(args_,_ctx,_rendStack);
            objects_.add(txt_);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return null;
            }
            _nextWrite.removeAttribute(e.getKey());
        }
        _nextWrite.setAttribute(_cont.getRendKeyWords().getAttrTitle(), StringUtil.simpleStringsFormat(_preformatted.getVal(_cont.getCurrentLanguage()), objects_));
        ownerDocument_.renameNode(_nextWrite, _cont.getRendKeyWords().getKeyWordAnchor());
        return _nextWrite;
    }

}
