package code.formathtml;

import code.sml.Element;
import code.util.CustList;

public final class RendDocumentBlock extends RendParentBlock {

    private Element elt;

    private String file;
    private String fileName;
    private String beanName;
    private CustList<RendBlock> bodies = new CustList<RendBlock>();

    public RendDocumentBlock(int _offsetTrim, Element elt, String file, String fileName, String beanName) {
        super(_offsetTrim);
        this.elt = elt;
        this.file = file;
        this.fileName = fileName;
        this.beanName = beanName;
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
