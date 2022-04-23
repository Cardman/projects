package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.stacks.DefRendReadWrite;
import code.formathtml.exec.stacks.RendIfStack;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;

public abstract class RendElement extends RendParentBlock implements RendElem, RendWithEl {
    private final Element read;
    private final StringMap<CustList<RendDynOperationNode>> execAttributes;
    private final StringMap<CustList<RendDynOperationNode>> execAttributesText;
    private final boolean after;

    protected RendElement(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText) {
        this(_read,_execAttributes,_execAttributesText,false);
    }

    protected RendElement(Element _read, StringMap<CustList<RendDynOperationNode>> _execAttributes, StringMap<CustList<RendDynOperationNode>> _execAttributesText, boolean _af) {
        this.read = _read;
        this.execAttributes = _execAttributes;
        this.execAttributesText = _execAttributesText;
        after = _af;
    }

    public final Element getRead() {
        return read;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        DefRendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.matchStatement(this)) {
            processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
            return;
        }
        Document ownerDocument_ = rw_.getDocument();
        Element created_ = appendChild(ownerDocument_, read);
        for (EntryCust<String, CustList<RendDynOperationNode>> e: execAttributesText.entryList()) {
            IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(e.getValue(), _ctx, _rendStack);
            String txt_ = RendInput.idRad(args_,_ctx,_rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
            created_.setAttribute(e.getKey(),txt_);
        }
        for (EntryCust<String, CustList<RendDynOperationNode>> e: execAttributes.entryList()) {
            IdMap<RendDynOperationNode, ArgumentsPair> args_ = RenderExpUtil.getAllArgs(e.getValue(), _ctx, _rendStack);
            String txt_ = RendInput.idRad(args_,_ctx,_rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
            created_.setAttribute(e.getKey(),txt_);
        }
        processExecAttr(_cont,created_,read, _stds, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        if (!after) {
            simpleAppendChild(ownerDocument_, rw_, created_);
        }
        addEltStack(ip_, rw_, created_, this);
    }

    public static void addEltStack(ImportingPage _ip, DefRendReadWrite _rw, Element _created, RendParentBlock _block) {
        RendIfStack if_ = new RendIfStack();
        if_.setLabel("");
        if_.setLastBlock(_block);
        if_.setBlock(_block);
        if_.setCurrentVisitedBlock(_block);
        _ip.addBlock(if_);
        if_.setEntered(true);
        _rw.setRead(_block.getFirstChild());
        _rw.setWrite(_created);
    }

    protected abstract void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack);

}
