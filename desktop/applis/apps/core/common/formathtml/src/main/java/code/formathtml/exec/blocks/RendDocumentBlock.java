package code.formathtml.exec.blocks;

import code.sml.Element;
import code.util.CustList;

public final class RendDocumentBlock extends RendParentBlock {

    private Element elt;

    private String file;
    private String fileName;
    private String beanName;
    private CustList<RendBlock> bodies = new CustList<RendBlock>();

    public RendDocumentBlock(Element _elt, String _file, String _fileName, String _beanName) {
        this.elt = _elt;
        this.file = _file;
        this.fileName = _fileName;
        this.beanName = _beanName;
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

    public String getFile() {
        return file;
    }

}
