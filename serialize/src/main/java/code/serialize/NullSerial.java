package code.serialize;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import code.serialize.exceptions.ClassFoundException;
import code.util.StringList;


final class NullSerial extends ElementsSerial {

    private static final String NULL_ATTR = "null";

    NullSerial(){
    }

    /**@throws ClassFoundException*/
    NullSerial(Element _node) {
        super(_node);
        if (!StringList.quickEq(_node.getTagName(),NULL_ATTR)) {
            throw new ClassFoundException(_node.getTagName(), NULL_ATTR);
        }
        NamedNodeMap map_ = _node.getAttributes();
//        if (map_ == null) {
//            throw new NoAttributeForSerializable(NULL_ATTR);
//        }
        Attr field_ = (Attr) map_.getNamedItem(FIELD);
        if (field_ != null) {
            setField(field_.getValue());
        }
        Attr className_ = (Attr) map_.getNamedItem(CLASS);
        if (className_ != null) {
            setClassName(className_.getValue());
        }
        Attr keyOfMap_ = (Attr) map_.getNamedItem(KEY);
        if (keyOfMap_ != null) {
            setKeyOfMap(true);
        }
    }
    @Override
    Object getValue() {
        return null;
    }

    @Override
    Element serialize(Document _doc) {
        Element node_ = _doc.createElement(NULL_ATTR);
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
