package code.bean.nat.exec.blocks;

import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.blocks.RendElem;
import code.formathtml.exec.blocks.RendElement;
import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.util.EntryCust;
import code.util.StringMap;

public abstract class NatRendElement extends RendParentBlock implements RendElem, NatRendWithEl {
    private final Element read;
    private final StringMap<ExecTextPart> natAttributes;
    private final StringMap<ExecTextPart> natAttributesText;

    protected NatRendElement(Element _read, StringMap<ExecTextPart> _execAttributes, StringMap<ExecTextPart> _execAttributesText) {
        this.read = _read;
        this.natAttributes = _execAttributes;
        this.natAttributesText = _execAttributesText;
    }

    public final Element getRead() {
        return read;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.matchStatement(this)) {
            RendBlockHelp.processBlockAndRemove(_rendStack, this);
            return;
        }
        Document ownerDocument_ = rw_.getDocument();
        Element created_ = appendChild(ownerDocument_, rw_, read);
        for (EntryCust<String, ExecTextPart> e: natAttributesText.entryList()) {
            ExecTextPart res_ = e.getValue();
            String txt_ = NatRenderingText.renderNat(res_, _stds, _rendStack);
            created_.setAttribute(e.getKey(),txt_);
        }
        processExecAttr(_cont,created_,read, _stds, _rendStack);
        for (EntryCust<String, ExecTextPart> e: natAttributes.entryList()) {
            ExecTextPart res_ = e.getValue();
            String txt_ = NatRenderingText.renderNat(res_, _stds, _rendStack);
            created_.setAttribute(e.getKey(),txt_);
        }
        RendElement.addEltStack(ip_,rw_,created_,this);
    }

    protected abstract void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, RendStackCall _rendStack);

}
