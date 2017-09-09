package code.serialize;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

abstract class PrimitiveSerial extends ElementsSerial{

    static final String VALUE = "value";

    PrimitiveSerial() {
    }

    PrimitiveSerial(Element _node) {
        super(_node);
    }

    @Override
    Element serialize(Document _doc) {
        Element node_ = serializeMetaInfo(_doc);
        node_.setAttribute(VALUE, getValue().toString());
        return node_;
    }

    @Override
    Element serializeWithoutRef(Document _doc) {
        Element node_ = serializeMetaInfo(_doc);
        node_.setAttribute(VALUE, getValue().toString());
        return node_;
    }

    Element serializeMetaInfo(Document _doc) {
        Element node_ = createNode(_doc);
        if (getField() != null && !getField().isEmpty()) {
            node_.setAttribute(FIELD, getField());
        }
        if (getClassName() != null) {
            node_.setAttribute(CLASS, getClassName());
        }
        if (isKeyOfMap()) {
            node_.setAttribute(KEY, EMPTY_STRING);
        }
        return node_;
    }

    private Element createNode(Document _doc) {
        String name_ = getValueClass().getName();
        Element node_;
        int indexDollar_ = name_.indexOf(ElementsSerial.SEP_INTERN);
        if (indexDollar_ != -1) {
            node_ = _doc.createElement(name_.substring(0, indexDollar_));
            node_.setAttribute(ElementsSerial.INTERN, name_.substring(indexDollar_));
        } else {
            node_ = _doc.createElement(name_);
        }
        return node_;
    }
}
