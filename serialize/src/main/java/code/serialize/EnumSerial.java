package code.serialize;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import code.serialize.exceptions.ClassFoundException;
import code.serialize.exceptions.InexistingValueForEnum;
import code.serialize.exceptions.NoAttributeForSerializable;
import code.util.StringList;
import code.util.consts.ConstClasses;
import code.util.exceptions.RuntimeClassNotFoundException;


//class EnumSerial extends ObjectSerial {
//
//}
final class EnumSerial extends PrimitiveSerial {

//    private static final String VALUE = "value";

    private Object value;

    EnumSerial(Enum<?> _enumConst) {
        value = _enumConst;
    }

    /**@throws InexistingValueForEnum
    @throws NoAttributeForSerializable
    @throws RuntimeClassNotFoundException
    @throws NullPointerException*/
    EnumSerial(Element _node) {
        super(_node);
        value = initialize(_node);
        NamedNodeMap map_ = _node.getAttributes();
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
    }
    /**@throws InexistingValueForEnum
    @throws NoAttributeForSerializable
    @throws RuntimeClassNotFoundException
    @throws ClassCastException*/
    private static Enum<?> initialize(Element _node) {
        NamedNodeMap map_ = _node.getAttributes();
        Class<?> class_ = ConstClasses.classAliasForObjectNameNotInit(_node.getNodeName()+_node.getAttribute(INTERN));
        if (!class_.isEnum()){
            throw new ClassFoundException(class_.getName(), Enum.class.getName());
        }
//        Class<?> subClass_ = class_.asSubclass(Enum.class);
        Node valueNode_ = map_.getNamedItem(VALUE);
        if (valueNode_ == null) {
            throw new NoAttributeForSerializable(VALUE, _node.getNodeName()+_node.getAttribute(INTERN));
        }
        String name_ = valueNode_.getNodeValue();
        for (Object s : class_.getEnumConstants()) {
            if (StringList.quickEq(((Enum<?>) s).name(),name_)) {
                return (Enum<?>) s;
            }
        }
        throw new InexistingValueForEnum(name_, class_.getName());
    }

    @Override
    Element serialize(Document _doc) {
        Element node_ = super.serialize(_doc);
        node_.setAttribute(VALUE, ((Enum<?>)getValue()).name());
        return node_;
    }
    @Override
    Element serializeWithoutRef(Document _doc) {
        Element node_ = super.serializeWithoutRef(_doc);
        node_.setAttribute(VALUE, ((Enum<?>)getValue()).name());
        return node_;
    }

//    @Override
//    Class<?> getValueClass() {
//        return value.getClass();
//    }

    @Override
    Object getValue() {
        return value;
    }
}
