package code.formathtml.exec.blocks;

import code.expressionlanguage.common.AbstractFileEscapedCalc;
import code.expressionlanguage.common.FileMetricsCore;
import code.expressionlanguage.exec.blocks.ExecFileBlockAbs;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.sml.Element;
import code.util.CustList;

public final class RendDocumentBlock extends ExecFileBlockAbs implements RendParentBlockInt {

    private String fileName;
    private Element elt;

    private String beanName;
    private CustList<RendBlock> bodies = new CustList<RendBlock>();
    private ExecFormattedRootBlock decl;
    private RendBlock docElt;

    public RendDocumentBlock(String _f, AbstractFileEscapedCalc _esc, FileMetricsCore _metrics, Element _elt, String _beanName, ExecFormattedRootBlock _d) {
        super(_metrics,_esc);
        this.elt = _elt;
        fileName = _f;
        this.beanName = _beanName;
        decl = _d;
    }

    public RendBlock getDocElt() {
        return docElt;
    }

    @Override
    public void appendChild(RendBlock _child) {
        docElt = _child;
    }

    @Override
    public RendParentBlock getParent() {
        return null;
    }

    public Element getElt() {
        return elt;
    }

    public String getFileName() {
        return fileName;
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
