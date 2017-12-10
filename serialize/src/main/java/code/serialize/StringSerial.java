package code.serialize;
import code.sml.Attr;
import code.sml.Element;
import code.sml.NamedNodeMap;

import code.serialize.exceptions.ClassFoundException;
import code.serialize.exceptions.NoAttributeForSerializable;


final class StringSerial extends PrimitiveSerial {

    private String value;

    StringSerial(String _value) {
        value = _value;
    }

    /**@throws ClassFoundException
    @throws NoAttributeForSerializable*/
    StringSerial(Element _node) {
        super(_node);
        NamedNodeMap map_ = _node.getAttributes();
        String name_ = _node.getTagName();
//        if (map_ == null) {
//            throw new NoAttributeForSerializable(_node.getNodeName());
//        }
        Attr className_ = map_.getNamedItem(CLASS);
        if (className_ != null) {
            setClassName(className_.getValue());
        }
        Attr field_ = map_.getNamedItem(FIELD);
        if (field_ != null) {
            setField(field_.getValue());
        }
        Attr keyOfMap_ = map_.getNamedItem(KEY);
        if (keyOfMap_ != null) {
            setKeyOfMap(true);
        }
        if (!name_.equalsIgnoreCase(String.class.getName())) {
            throw new ClassFoundException(name_, String.class.getName());
        }
        Attr value_ = map_.getNamedItem(VALUE);
        if (value_ != null) {
            value = value_.getValue();
        } else {
            throw new NoAttributeForSerializable(VALUE,name_);
        }
    }
    @Override
    String getValue() {
        return value;
    }
}
