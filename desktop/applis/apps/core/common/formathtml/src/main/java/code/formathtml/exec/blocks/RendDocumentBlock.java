package code.formathtml.exec.blocks;

import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.sml.Element;
import code.util.CustList;

public final class RendDocumentBlock extends RendParentBlock {

    private ExecFileBlock fileBlock;
    private Element elt;

    private String beanName;
    private CustList<RendBlock> bodies = new CustList<RendBlock>();

    public RendDocumentBlock(ExecFileBlock _fileBlock, Element _elt, String _beanName) {
        this.fileBlock = _fileBlock;
        this.elt = _elt;
        this.beanName = _beanName;
    }

    public ExecFileBlock getFileBlock() {
        return fileBlock;
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
