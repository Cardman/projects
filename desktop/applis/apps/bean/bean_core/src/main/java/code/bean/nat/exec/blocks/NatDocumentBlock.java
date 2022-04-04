package code.bean.nat.exec.blocks;

import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatRendReadWrite;
import code.bean.nat.exec.NatRendStackCall;
import code.formathtml.Configuration;
import code.sml.Element;

public final class NatDocumentBlock extends NatParentBlock {

    private final Element elt;

    private final String beanName;
    private NatParentBlock body;

    public NatDocumentBlock(Element _elt, String _beanName) {
        this.elt = _elt;
        this.beanName = _beanName;
    }

    @Override
    public void processEl(Configuration _cont, NatRendStackCall _rendStack) {
        NatImportingPage ip_ = _rendStack.getLastPage();
        NatRendReadWrite rw_ = ip_.getRendReadWrite();
        if (ip_.matchStatement(this)) {
            RendBlockHelp.processBlockAndRemove(_rendStack, this);
            return;
        }
        NatRendElement.addEltStack(ip_,rw_,null,this);
    }

    public Element getElt() {
        return elt;
    }

    public String getBeanName() {
        return beanName;
    }

    public NatParentBlock getBody() {
        return body;
    }

    public void setBody(NatParentBlock _b) {
        this.body = _b;
    }
}
