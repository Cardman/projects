package code.serialize;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import code.serialize.exceptions.ClassFoundException;
import code.util.StringList;


final class NullSerial extends ElementsSerial {

    private static final String NULL_ATTR = "null";

    NullSerial(){
    }

    /**@throws ClassFoundException*/
    NullSerial(Element _node) {
        super(_node);
        if (!StringList.quickEq(_node.getNodeName(),NULL_ATTR)) {
            throw new ClassFoundException(_node.getNodeName(), NULL_ATTR);
        }
        NamedNodeMap map_ = _node.getAttributes();
//        if (map_ == null) {
//            throw new NoAttributeForSerializable(NULL_ATTR);
//        }
        Node field_ = map_.getNamedItem(FIELD);
        if (field_ != null) {
            setField(field_.getNodeValue());
        }
        Node className_ = map_.getNamedItem(CLASS);
        if (className_ != null) {
            setClassName(className_.getNodeValue());
        }
        Node keyOfMap_ = map_.getNamedItem(KEY);
        if (keyOfMap_ != null) {
            setKeyOfMap(true);
        }
    }
    @Override
    Object getValue() {
        return null;
    }

//    @Override
//    Class<?> getValueClass() {
//        return null;
//    }

    @Override
    Element serialize(Document _doc) {
        Element node_ = _doc.createElement(NULL_ATTR);
//        if (getField() != null && !getField().isEmpty()) {
//            node_.setAttribute(FIELD, getField());
//        }
        if (getField() != null) {
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
    @Override
    Element serializeWithoutRef(Document _doc) {
        Element node_ = _doc.createElement(NULL_ATTR);
//        if (getField() != null && !getField().isEmpty()) {
//            node_.setAttribute(FIELD, getField());
//        }
        if (getField() != null) {
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
}
