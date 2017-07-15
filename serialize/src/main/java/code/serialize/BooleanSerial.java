package code.serialize;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import code.serialize.exceptions.ClassFoundException;
import code.serialize.exceptions.NoAttributeForSerializable;


final class BooleanSerial extends PrimitiveSerial {

    private static final String TRUE = "true";
    private boolean value;

    BooleanSerial(boolean _value) {
        value = _value;
    }

    /**@throws ClassFoundException
    @throws NoAttributeForSerializable*/
    BooleanSerial(Element _node) {
        super(_node);
        NamedNodeMap map_ = _node.getAttributes();
        String name_ = _node.getNodeName();
//        if (map_ == null) {
//            throw new NoAttributeForSerializable(name_);
//        }
        Node className_ = map_.getNamedItem(CLASS);
        if (className_ != null) {
            setClassName(className_.getNodeValue());
        }
        Node field_ = map_.getNamedItem(FIELD);
        if (field_ != null) {
            setField(field_.getNodeValue());
        }
        Node keyOfMap_ = map_.getNamedItem(KEY);
        if (keyOfMap_ != null) {
            setKeyOfMap(true);
        }
        if (!name_.equalsIgnoreCase(Boolean.class.getName())) {
            throw new ClassFoundException(name_, Boolean.class.getName());
        }
        Node value_ = map_.getNamedItem(VALUE);
        if (value_ != null) {
            value = value_.getNodeValue().equalsIgnoreCase(TRUE);
        } else {
            throw new NoAttributeForSerializable(VALUE, name_);
        }
    }
    @Override
    Boolean getValue() {
        return value;
    }
}
