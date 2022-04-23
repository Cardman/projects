package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.stacks.DefRendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.sml.*;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class RendLink extends RendElement {
    private final String content;
    private final StringMap<CustList<RendDynOperationNode>> execOpExpTitle;

    public RendLink(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText, String _content, StringMap<CustList<RendDynOperationNode>> _execOpExpTitle) {
        super(_read, _execAttributes, _execAttributesText,true);
        this.content = _content;
        this.execOpExpTitle = _execOpExpTitle;
    }

    @Override
    protected void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        String fileContent_ = content;
        Element curWr_ = (Element) _nextWrite;
        Document ownerDocument_ = curWr_.getOwnerDocument();
        if (!execOpExpTitle.isEmpty()) {
            StringList objects_ = new StringList();
            for (EntryCust<String, CustList<RendDynOperationNode>> e:execOpExpTitle.entryList()) {
                IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(e.getValue(), _ctx, _rendStack);
                String txt_ = RendInput.idRad(args_,_ctx,_rendStack);
                objects_.add(txt_);
                if (_ctx.callsOrException(_rendStack.getStackCall())) {
                    return;
                }
                curWr_.removeAttribute(e.getKey());
            }
            fileContent_ = StringUtil.simpleStringsFormat(fileContent_, objects_);
        }
        ImportingPage ip_ = _rendStack.getLastPage();
        DefRendReadWrite rw_ = ip_.getRendReadWrite();
        simpleAppendChild(ownerDocument_, rw_, _nextWrite);
        procLink(_cont, fileContent_, ownerDocument_);
    }

    public static void procLink(Configuration _cont, String _fileContent, Document _ownerDocument) {
        ElementList heads_ = _ownerDocument.getElementsByTagName(_cont.getRendKeyWords().getKeyWordHead());
        if (_fileContent != null && heads_.getLength() == IndexConstants.ONE_ELEMENT) {
            Element head_ = heads_.item(IndexConstants.FIRST_INDEX);
            CustList<Element> children_ = new CustList<Element>();
            for (Element c: head_.getChildElements()) {
                if (!StringUtil.quickEq(c.getTagName(), _cont.getRendKeyWords().getKeyWordStyle())) {
                    continue;
                }
                children_.add(c);
            }
            boolean successAdd_ = children_.isEmpty();
            if (!successAdd_) {
                Element eltStyle_ = children_.last();
                appendText(_fileContent, _ownerDocument, eltStyle_);
            } else {
                Element eltStyle_ = _ownerDocument.createElement(_cont.getRendKeyWords().getKeyWordStyle());
                Text text_ = _ownerDocument.createTextNode(_fileContent);
                eltStyle_.appendChild(text_);
                head_.appendChild(eltStyle_);
            }
        }
    }

}
