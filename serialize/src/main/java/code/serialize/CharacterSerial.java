package code.serialize;
import code.xml.components.Attr;
import code.xml.components.Element;
import code.xml.components.NamedNodeMap;

import code.serialize.exceptions.ClassFoundException;
import code.serialize.exceptions.NoAttributeForSerializable;

final class CharacterSerial extends PrimitiveSerial {

    private char value;

    CharacterSerial(char _value) {
        value = _value;
    }

    /**@throws ClassFoundException
    @throws NoAttributeForSerializable*/
    CharacterSerial(Element _node) {
        super(_node);
        NamedNodeMap map_ = _node.getAttributes();
        String name_ = _node.getTagName();
//        if (map_ == null) {
//            throw new NoAttributeForSerializable(name_);
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
        if (!name_.equalsIgnoreCase(Character.class.getName())) {
            throw new ClassFoundException(name_, Character.class.getName());
        }
        Attr value_ = map_.getNamedItem(VALUE);
        if (value_ != null) {
            value = value_.getValue().charAt(0);
        } else {
            throw new NoAttributeForSerializable(VALUE,name_);
        }
    }
    @Override
    Character getValue() {
        return value;
    }
}
