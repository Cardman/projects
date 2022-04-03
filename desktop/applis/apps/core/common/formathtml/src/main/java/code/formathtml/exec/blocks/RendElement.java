package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.stacks.DefRendReadWrite;
import code.formathtml.exec.stacks.RendIfStack;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.util.EntryCust;
import code.util.StringMap;

public abstract class RendElement extends RendParentBlock implements RendElem, RendWithEl {
    private final Element read;
    private final StringMap<DefExecTextPart> execAttributes;
    private final StringMap<DefExecTextPart> execAttributesText;

    protected RendElement(Element _read, StringMap<DefExecTextPart> _execAttributes, StringMap<DefExecTextPart> _execAttributesText) {
        this.read = _read;
        this.execAttributes = _execAttributes;
        this.execAttributesText = _execAttributesText;
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
        Element created_ = appendChild(ownerDocument_, rw_, read);
        for (EntryCust<String, DefExecTextPart> e: execAttributesText.entryList()) {
            DefExecTextPart res_ = e.getValue();
            String txt_ = RenderingText.render(res_, _ctx, _rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
            created_.setAttribute(e.getKey(),txt_);
        }
        processExecAttr(_cont,created_,read, _stds, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        for (EntryCust<String, DefExecTextPart> e: execAttributes.entryList()) {
            DefExecTextPart res_ = e.getValue();
            String txt_ = RenderingText.render(res_, _ctx, _rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
            created_.setAttribute(e.getKey(),txt_);
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
