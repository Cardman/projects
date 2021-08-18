package code.bean.help.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.ExecTextPart;
import code.formathtml.exec.blocks.RendElem;
import code.formathtml.exec.blocks.RendElement;
import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.sml.*;
import code.util.EntryCust;
import code.util.StringMap;

public abstract class HelpRendElement extends RendParentBlock implements RendElem {
    private final Element read;
    private final StringMap<ExecTextPart> helpAttributes;

    protected HelpRendElement(Element _read, StringMap<ExecTextPart> _execAttributes) {
        this.read = _read;
        this.helpAttributes = _execAttributes;
    }

    public final Element getRead() {
        return read;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.matchStatement(this)) {
            HelpRendBlockHelp.processBlockAndRemove(_rendStack, this);
            return;
        }
        Document ownerDocument_ = rw_.getDocument();
        Element created_ = appendChild(ownerDocument_, rw_, read);
        processExecAttr(_cont,created_,read, _stds, _ctx, _rendStack);
        for (EntryCust<String, ExecTextPart> e: helpAttributes.entryList()) {
            ExecTextPart res_ = e.getValue();
            String txt_ = HelpRenderingText.render(res_);
            created_.setAttribute(e.getKey(),txt_);
        }
        RendElement.addEltStack(ip_,rw_,created_,this);
    }

    protected abstract void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack);

}
