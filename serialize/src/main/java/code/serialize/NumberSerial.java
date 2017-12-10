package code.serialize;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import code.sml.Attr;
import code.sml.Element;
import code.sml.NamedNodeMap;

import code.serialize.exceptions.ClassFoundException;
import code.serialize.exceptions.NoAttributeForSerializable;


/**A serializable not native class inheriting from Number
must have the public constructor with a String parameter
like native class inheriting from Number (Integer, Long, ...)*/
final class NumberSerial extends PrimitiveSerial {

    private Number value;

    NumberSerial(Number _value) {
        value = _value;
    }
    /**@throws ClassFoundException
    @throws NoAttributeForSerializable
    @throws NumberFormatException*/
    NumberSerial(Element _node) {
        super(_node);
        NamedNodeMap map_ = _node.getAttributes();
        String name_ = _node.getTagName();
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
        try {
            //class_ can be a native class inheriting from Number
            //and is found by comparing, in a case insensitive way, its name
            //with the name of a native class inheriting from Number
            String value_ = _node.getAttribute(VALUE);
            if (name_.equalsIgnoreCase(Integer.class.getName())) {
                checkValue(_node, name_);
                value = Integer.parseInt(value_);
            } else if (name_.equalsIgnoreCase(Long.class.getName())) {
                checkValue(_node, name_);
                value = Long.parseLong(value_);
            } else if (name_.equalsIgnoreCase(Short.class.getName())) {
                checkValue(_node, name_);
                value = Short.parseShort(value_);
            } else if (name_.equalsIgnoreCase(Byte.class.getName())) {
                checkValue(_node, name_);
                value = Byte.parseByte(value_);
            } else if (name_.equalsIgnoreCase(BigInteger.class.getName())) {
                checkValue(_node, name_);
                value = new BigInteger(value_);
            } else if (name_.equalsIgnoreCase(BigDecimal.class.getName())) {
                checkValue(_node, name_);
                value = new BigDecimal(value_);
            } else if (name_.equalsIgnoreCase(Double.class.getName())) {
                checkValue(_node, name_);
                value = Double.parseDouble(value_);
            } else if (name_.equalsIgnoreCase(Float.class.getName())) {
                checkValue(_node, name_);
                value = Float.parseFloat(value_);
            } else if (name_.equalsIgnoreCase(AtomicInteger.class.getName())) {
                checkValue(_node, name_);
                value = new AtomicInteger(Integer.parseInt(value_));
            } else if (name_.equalsIgnoreCase(AtomicLong.class.getName())) {
                checkValue(_node, name_);
                value = new AtomicLong(Long.parseLong(value_));
            } else {
                //class_ does not inherit from Number
                throw new ClassFoundException(true, name_, Number.class.getName());
            }
        } catch (NullPointerException _0) {
            throw new NoAttributeForSerializable(VALUE, name_);
        }

    }
    private static void checkValue(Element _node, String _name) {
        if (!_node.hasAttribute(VALUE)) {
            throw new NoAttributeForSerializable(VALUE,_name);
        }
    }
    @Override
    Number getValue() {
        return value;
    }

}
