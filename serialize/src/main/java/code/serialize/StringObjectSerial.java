package code.serialize;
import java.lang.reflect.Method;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

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
        Method method_ = null;
        String classNameInst_ = name_+_node.getAttribute(INTERN);
        Class<?> class_ = ConstClasses.classAliasForObjectNameNotInit(classNameInst_);
        Node value_ = map_.getNamedItem(VALUE);
        if (value_ == null) {
            throw new NoValueException(classNameInst_);
        }
        method_ = ConverterMethod.getFromStringMethod(class_);
        if (method_ == null) {
            throw new NoSuchDeclaredMethodException();
        }
        value = ConverterMethod.invokeMethod(method_, null, value_.getNodeValue());
    }

    @Override
    Element serialize(Document _doc) {
        Element node_ = super.serializeMetaInfo(_doc);
        Method method_ = ConverterMethod.getToStringMethod(getValueClass());
        Object ret_ = ConverterMethod.invokePublicMethod(method_, getValue());
        node_.setAttribute(VALUE, ret_.toString());
//        if (method_ != null) {
//            Object ret_ = ConverterMethod.invokePublicMethod(method_, getValue());
//            node_.setAttribute(VALUE, ret_.toString());
////            node_.setAttribute(VALUE, method_.invoke(getValue()).toString());
//        } else {
//            node_.setAttribute(VALUE, getValue().toString());
//        }
        return node_;
    }

    @Override
    Element serializeWithoutRef(Document _doc) {
        Element node_ = super.serializeMetaInfo(_doc);
        Method method_ = ConverterMethod.getToStringMethod(getValueClass());
        Object ret_ = ConverterMethod.invokePublicMethod(method_, getValue());
        node_.setAttribute(VALUE, ret_.toString());
//        if (method_ != null) {
//            Object ret_ = ConverterMethod.invokePublicMethod(method_, getValue());
//            node_.setAttribute(VALUE, ret_.toString());
////            node_.setAttribute(VALUE, method_.invoke(getValue()).toString());
//        } else {
//            node_.setAttribute(VALUE, getValue().toString());
//        }
        return node_;
    }

    @Override
    Object getValue() {
        return value;
    }

}
