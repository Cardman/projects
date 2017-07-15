package code.serialize;
import org.w3c.dom.Element;

import code.util.CustList;

abstract class TemplateSerial extends ElementsSerial {

//    static final String COMPARATOR = "comparator";

    static final String ID = "id";
    static final String REF = "ref";

    private Long id;

    private Long ref;

    TemplateSerial(){
    }

    TemplateSerial(Element _node) {
        super(_node);
    }

//    Element serialize(Document _doc) {
//        Element node_ = _doc.createElement(getValueClass().getName());
//        if (getId() != null) {
//            node_.setAttribute(ID, getId().toString());
//        }
//        if (getRef() != null) {
//            node_.setAttribute(REF, getRef().toString());
//        }
////        if (getField() != null && !getField().isEmpty()) {
////            node_.setAttribute(FIELD, getField());
////        }
//        if (getField() != null) {
//            node_.setAttribute(FIELD, getField());
//        }
//        if (getClassName() != null) {
//            node_.setAttribute(CLASS, getClassName());
//        }
//        if (isKeyOfMap()) {
//            node_.setAttribute(KEY, EMPTY_STRING);
//        }
//        return node_;
//    }
//    Element serializeWithoutRef(Document _doc) {
//        Element node_ = _doc.createElement(getValueClass().getName());
////        if (getField() != null && !getField().isEmpty()) {
////            node_.setAttribute(FIELD, getField());
////        }
//        if (getField() != null) {
//            node_.setAttribute(FIELD, getField());
//        }
//        if (getClassName() != null) {
//            node_.setAttribute(CLASS, getClassName());
//        }
//        if (isKeyOfMap()) {
//            node_.setAttribute(KEY, EMPTY_STRING);
//        }
//        return node_;
//    }
//    List<TemplateSerial> references(List<TemplateSerial> _list) {
//        List<TemplateSerial> list_ = new ArrayList<>();
//        if (id == null) {
//            return list_;
//        }
//        for(TemplateSerial e: _list) {
//            if (e.ref != id) {
//                continue;
//            }
//            //id != null && e.ref == id ==> e.ref != null
//            list_.add(e);
//        }
//        return list_;
//    }
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

//    @Override
//    public String toString() {
//        return id+COMMA+ref+COMMA+super.toString();
//    }

}
