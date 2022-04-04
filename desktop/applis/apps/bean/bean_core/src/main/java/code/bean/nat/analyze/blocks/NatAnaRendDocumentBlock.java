package code.bean.nat.analyze.blocks;

import code.sml.Element;

public final class NatAnaRendDocumentBlock extends NatAnaRendParentBlock {

    private final Element elt;

    private String beanName;
    public NatAnaRendDocumentBlock(Element _elt) {
        super();
        elt = _elt;
    }

    public Element getElt() {
        return elt;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String _beanName) {
        this.beanName = _beanName;
    }
}
