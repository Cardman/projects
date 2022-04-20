package code.bean.help.exec.blocks;

import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatRendReadWrite;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.blocks.NatExecTextPart;
import code.bean.nat.exec.blocks.NatParentBlock;
import code.bean.nat.exec.blocks.NatRendElement;
import code.bean.nat.exec.blocks.RendBlockHelp;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.RendElem;
import code.sml.Document;
import code.sml.Element;
import code.sml.Node;
import code.util.EntryCust;
import code.util.StringMap;

public abstract class HelpRendElement extends NatParentBlock implements RendElem {
    private final Element read;
    private final StringMap<NatExecTextPart> helpAttributes;

    protected HelpRendElement(Element _read, StringMap<NatExecTextPart> _execAttributes) {
        this.read = _read;
        this.helpAttributes = _execAttributes;
    }

    public final Element getRead() {
        return read;
    }

    @Override
    public void processEl(Configuration _cont, NatRendStackCall _rendStack) {
        NatImportingPage ip_ = _rendStack.getLastPage();
        NatRendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.matchStatement(this)) {
            HelpRendBlockHelp.processBlockAndRemove(_rendStack, this);
            return;
        }
        Document ownerDocument_ = rw_.getDocument();
        Element created_ = RendBlockHelp.appendChild(ownerDocument_, rw_, read);
        processExecAttr(_cont,created_,read, _rendStack);
        for (EntryCust<String, NatExecTextPart> e: helpAttributes.entryList()) {
            NatExecTextPart res_ = e.getValue();
            String txt_ = HelpRenderingText.render(res_);
            created_.setAttribute(e.getKey(),txt_);
        }
        NatRendElement.addEltStack(ip_,rw_,created_,this);
    }

    protected abstract void processExecAttr(Configuration _cont, Node _nextWrite, Element _read, NatRendStackCall _rendStack);

}
