package code.bean.nat.exec.blocks;

import code.formathtml.exec.blocks.RendBlock;
import code.formathtml.exec.blocks.RendParentBlock;
import code.sml.Element;
import code.util.CustList;

public final class NatDocumentBlock extends RendParentBlock {

    private final Element elt;

    private final String beanName;
    private final CustList<RendBlock> bodies = new CustList<RendBlock>();

    public NatDocumentBlock(Element _elt, String _beanName) {
        this.elt = _elt;
        this.beanName = _beanName;
    }

    public RendBlock getDocElt() {
        return getFirstChild();
    }

    public Element getElt() {
        return elt;
    }

    public String getBeanName() {
        return beanName;
    }

    public CustList<RendBlock> getBodies() {
        return bodies;
    }
}
