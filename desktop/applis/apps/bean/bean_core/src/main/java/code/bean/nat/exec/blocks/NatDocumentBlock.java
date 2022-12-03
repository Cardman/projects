package code.bean.nat.exec.blocks;

import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.*;
import code.bean.nat.exec.NatRendReadWrite;
import code.bean.nat.exec.NatRendStackCall;
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
    public void processEl(NatConfigurationCore _cont, NatRendStackCall _rendStack) {
        NatImportingPageAbs ip_ = _rendStack.getLastPage();
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
