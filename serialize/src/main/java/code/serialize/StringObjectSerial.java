package code.serialize;
import java.lang.reflect.Method;

import code.sml.Attr;
import code.sml.Document;
import code.sml.Element;
import code.sml.NamedNodeMap;

import code.serialize.exceptions.InvokingException;
import code.serialize.exceptions.NoSuchDeclaredMethodException;
import code.serialize.exceptions.NoValueException;
import code.serialize.exceptions.RuntimeInstantiationException;
import code.util.consts.ConstClasses;
import code.util.exceptions.RuntimeClassNotFoundException;

final class StringObjectSerial extends PrimitiveSerial {

    private Object value;

    StringObjectSerial(Object _value) {
        value = _value;
    }

    /**@throws NoValueException
    @throws SecurityException
    @throws InvokingException
    @throws RuntimeInstantiationException
    @throws NoSuchDeclaredMethodException
    @throws RuntimeClassNotFoundException*/
    StringObjectSerial(Element _node) {
        super(_node);
        //
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
        Method method_ = null;
        String classNameInst_ = name_+_node.getAttribute(INTERN);
        Class<?> class_ = ConstClasses.classAliasForObjectNameNotInit(classNameInst_);
        Attr value_ = map_.getNamedItem(VALUE);
        if (value_ == null) {
            throw new NoValueException(classNameInst_);
        }
        method_ = ConverterMethod.getFromStringMethod(class_);
        if (method_ == null) {
            throw new NoSuchDeclaredMethodException();
        }
        value = ConverterMethod.invokeMethod(method_, null, value_.getValue());
        if (value == null) {
            throw new NoValueException(classNameInst_);
        }
    }

    @Override
    Element serialize(Document _doc) {
        Element node_ = super.serializeMetaInfo(_doc);
        Method method_ = ConverterMethod.getToStringMethod(getValueClass());
        Object ret_ = ConverterMethod.invokeMethod(method_, getValue());
        node_.setAttribute(VALUE, ret_.toString());
        return node_;
    }

    @Override
    Element serializeWithoutRef(Document _doc) {
        Element node_ = super.serializeMetaInfo(_doc);
        Method method_ = ConverterMethod.getToStringMethod(getValueClass());
        Object ret_ = ConverterMethod.invokeMethod(method_, getValue());
        node_.setAttribute(VALUE, ret_.toString());
        return node_;
    }

    @Override
    Object getValue() {
        return value;
    }

}
