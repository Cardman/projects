package code.formathtml.exec.blocks;

import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.sml.Element;
import code.util.CustList;

public final class RendDocumentBlock extends RendParentBlock {

    private ExecFileBlock fileBlock;
    private Element elt;

    private String beanName;
    private CustList<RendBlock> bodies = new CustList<RendBlock>();
    private ExecFormattedRootBlock decl;

    public RendDocumentBlock(ExecFileBlock _fileBlock, Element _elt, String _beanName, ExecFormattedRootBlock _d) {
        this.fileBlock = _fileBlock;
        this.elt = _elt;
        this.beanName = _beanName;
        decl = _d;
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

    public ExecFormattedRootBlock getDecl() {
        return decl;
    }
}
