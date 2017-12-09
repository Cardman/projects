package code.serialize;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

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

    EnumSerial(Object _enumConst) {
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
        Attr className_ = (Attr) map_.getNamedItem(CLASS);
        if (className_ != null) {
            setClassName(className_.getValue());
        }
        Attr field_ = (Attr) map_.getNamedItem(FIELD);
        if (field_ != null) {
            setField(field_.getValue());
        }
        Attr keyOfMap_ = (Attr) map_.getNamedItem(KEY);
        if (keyOfMap_ != null) {
            setKeyOfMap(true);
        }
    }
    /**@throws InexistingValueForEnum
    @throws NoAttributeForSerializable
    @throws RuntimeClassNotFoundException
    @throws ClassCastException*/
    private static Object initialize(Element _node) {
        NamedNodeMap map_ = _node.getAttributes();
        Class<?> class_ = ConstClasses.classAliasForObjectNameNotInit(_node.getTagName()+_node.getAttribute(INTERN));
        if (!class_.isEnum()){
            throw new ClassFoundException(class_.getName());
        }
        Attr valueNode_ = (Attr) map_.getNamedItem(VALUE);
        if (valueNode_ == null) {
            throw new NoAttributeForSerializable(VALUE, _node.getTagName()+_node.getAttribute(INTERN));
        }
        String name_ = valueNode_.getValue();
        for (Object s : class_.getEnumConstants()) {
            if (StringList.quickEq(ConverterMethod.getName(s),name_)) {
                return s;
            }
        }
        throw new InexistingValueForEnum(name_, class_.getName());
    }

    @Override
    Element serialize(Document _doc) {
        Element node_ = super.serialize(_doc);
        node_.setAttribute(VALUE, ConverterMethod.getName(getValue()));
        return node_;
    }
    @Override
    Element serializeWithoutRef(Document _doc) {
        Element node_ = super.serializeWithoutRef(_doc);
        node_.setAttribute(VALUE, ConverterMethod.getName(getValue()));
        return node_;
    }

    @Override
    Object getValue() {
        return value;
    }
}
