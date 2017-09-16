package code.serialize;
import org.w3c.dom.Element;

import code.util.CustList;

abstract class TemplateSerial extends ElementsSerial {

    static final String ID = "id";
    static final String REF = "ref";

    private Long id;

    private Long ref;
    private TemplateSerial parent;

    TemplateSerial(){
    }

    TemplateSerial(Element _node, TemplateSerial _parent) {
        super(_node);
        parent = _parent;
    }

    public TemplateSerial getParent() {
        return parent;
    }

    abstract void appendElementSerialWithoutRef(String _xml, CustList<ElementsSerial> _elt);
    abstract void appendElementSerial(String _xml, CustList<ElementsSerial> _elt);
    abstract void setElementSerial(String _xml, ElementsSerial _e,ElementsSerial _newE);
    Long getId() {
        return id;
    }

    void setId(Long _id) {
        id = _id;
    }

    Long getRef() {
        return ref;
    }

    void setRef(Long _ref) {
        ref = _ref;
    }
}
