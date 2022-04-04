package code.bean.nat.exec.blocks;

import code.sml.Element;

public final class NatDocumentBlock extends NatParentBlock {

    private final Element elt;

    private final String beanName;
    private NatBlock body;

    public NatDocumentBlock(Element _elt, String _beanName) {
        this.elt = _elt;
        this.beanName = _beanName;
    }

    public NatBlock getDocElt() {
        return getFirstChild();
    }

    public Element getElt() {
        return elt;
    }

    public String getBeanName() {
        return beanName;
    }

    public NatBlock getBody() {
        return body;
    }

    public void setBody(NatBlock _b) {
        this.body = _b;
    }
}
