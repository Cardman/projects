package code.serialize;
import java.lang.reflect.Array;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import code.serialize.exceptions.ClassFoundException;
import code.serialize.exceptions.NoAttributeForSerializable;
import code.util.CustList;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.consts.ConstClasses;
import code.util.opers.CollectionsUtil;
import code.xml.XmlParser;

/**Integrity is checked at dynamic setting elements is the array.
A Integer[] array cannot have String elements.
"Null" value is accepted.*/
final class ArraySerial extends TemplateSerial {

//    private static final String ARRAY_END = ";";
//    private static final String MULT_ARRAY_BEGIN = "[[";
    private static final String ELEMENT_TYPE="type";
    private static final String ARRAY="array";

    private Object array;
    private NumberMap<Integer,Long> indexesRef;

    private ArraySerial(){}
    ArraySerial(Object _array) {
        array = _array;
    }

    /**@throws ClassFoundException
    @throws NoAttributeForSerializable*/
    ArraySerial(Element _node) {
        super(_node);
        if (!StringList.quickEq(_node.getNodeName(),ARRAY)) {
            throw new ClassFoundException(_node.getNodeName(), ARRAY);
        }
        NamedNodeMap map_ = _node.getAttributes();
//        if (map_ == null) {
//            throw new NoAttributeForSerializable(_node.getNodeName());
//        }
        Node className_ = map_.getNamedItem(CLASS);
        if (className_ != null) {
            setClassName(className_.getNodeValue());
        }
        Node typeName_ = map_.getNamedItem(ELEMENT_TYPE);
        if (typeName_ == null) {
            throw new NoAttributeForSerializable(ELEMENT_TYPE,_node.getNodeName());
        }
        Node field_ = map_.getNamedItem(FIELD);
        if (field_ != null) {
            setField(field_.getNodeValue());
        }
        Node keyOfMap_ = map_.getNamedItem(KEY);
        if (keyOfMap_ != null) {
            setKeyOfMap(true);
        }
        Node ref_ = map_.getNamedItem(REF);
        if (ref_ != null) {
            setRef(Long.parseLong(ref_.getNodeValue()));
            return;
        }
        Node id_ = map_.getNamedItem(ID);
        if (id_ != null) {
            setId(Long.parseLong(id_.getNodeValue()));
        }
        Class<?> class_;
        String typeValue_ = typeName_.getNodeValue();
        class_ = ConstClasses.classAliasForNameNotInit(typeValue_);
//        try {
//            class_ = Constants.classForName(typeValue_);
//        } catch (Exception e_) {
//            throw e_;
//            if (typeValue_.equalsIgnoreCase(long.class.getName())) {
//                class_ = long.class;
//            } else if (typeValue_.equalsIgnoreCase(int.class.getName())) {
//                class_ = int.class;
//            } else if (typeValue_.equalsIgnoreCase(short.class.getName())) {
//                class_ = short.class;
//            } else if (typeValue_.equalsIgnoreCase(byte.class.getName())) {
//                class_ = byte.class;
//            } else if (typeValue_.equalsIgnoreCase(char.class.getName())) {
//                class_ = char.class;
//            } else if (typeValue_.equalsIgnoreCase(float.class.getName())) {
//                class_ = float.class;
//            } else if (typeValue_.equalsIgnoreCase(double.class.getName())) {
//                class_ = double.class;
//            } else if (typeValue_.equalsIgnoreCase(boolean.class.getName())) {
//                class_ = boolean.class;
//            } else {
//                throw e_;
//            }
//        }
        array = Array.newInstance(class_, XmlParser.childrenElements(_node).size());
    }

    /**@throws ClassFoundException
    @throws NoAttributeForSerializable*/
    static ArraySerial newListSerial(Element _node) {
        if (!StringList.quickEq(_node.getNodeName(),ARRAY)) {
            throw new ClassFoundException(_node.getNodeName(), ARRAY);
        }
        NamedNodeMap map_ = _node.getAttributes();
//        if (map_ == null) {
//            throw new NoAttributeForSerializable(_node.getNodeName());
//        }
        Node className_ = map_.getNamedItem(CLASS);
        Node typeName_ = map_.getNamedItem(ELEMENT_TYPE);
        if (typeName_ == null) {
            throw new NoAttributeForSerializable(ELEMENT_TYPE,_node.getNodeName());
        }
        ArraySerial listSerial_ = new ArraySerial();
        listSerial_.setNode(_node);
        Class<?> class_;
        String typeValue_ = typeName_.getNodeValue();
        class_ = ConstClasses.classAliasForNameNotInit(typeValue_);
//        try {
//            class_ = Constants.classForName(typeValue_);
//        } catch (Exception e_) {
//            throw e_;
//            if (typeValue_.equalsIgnoreCase(long.class.getName())) {
//                class_ = long.class;
//            } else if (typeValue_.equalsIgnoreCase(int.class.getName())) {
//                class_ = int.class;
//            } else if (typeValue_.equalsIgnoreCase(short.class.getName())) {
//                class_ = short.class;
//            } else if (typeValue_.equalsIgnoreCase(byte.class.getName())) {
//                class_ = byte.class;
//            } else if (typeValue_.equalsIgnoreCase(char.class.getName())) {
//                class_ = char.class;
//            } else if (typeValue_.equalsIgnoreCase(float.class.getName())) {
//                class_ = float.class;
//            } else if (typeValue_.equalsIgnoreCase(double.class.getName())) {
//                class_ = double.class;
//            } else if (typeValue_.equalsIgnoreCase(boolean.class.getName())) {
//                class_ = boolean.class;
//            } else {
//                throw e_;
//            }
//        }
        listSerial_.array = Array.newInstance(class_, XmlParser.childrenElements(_node).size());
        Node field_ = map_.getNamedItem(FIELD);
        if (field_ != null) {
            listSerial_.setField(field_.getNodeValue());
        }
        Node keyOfMap_ = map_.getNamedItem(KEY);
        if (keyOfMap_ != null) {
            listSerial_.setKeyOfMap(true);
        }
        if (className_ != null) {
            listSerial_.setClassName(className_.getNodeValue());
        }
        return listSerial_;
    }

    @Override
    Element serialize(Document _doc) {
        Element node_ = _doc.createElement(ARRAY);
        if (getId() != null) {
            node_.setAttribute(ID, getId().toString());
        }
        if (getRef() != null) {
            node_.setAttribute(REF, getRef().toString());
        }
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
        node_.setAttribute(ELEMENT_TYPE, array.getClass().getComponentType().getName());
//        if (array instanceof long[]) {
//            node_.setAttribute(ELEMENT_TYPE, long.class.getName());
//        } else if (array instanceof int[]) {
//            node_.setAttribute(ELEMENT_TYPE, int.class.getName());
//        } else if (array instanceof short[]) {
//            node_.setAttribute(ELEMENT_TYPE, short.class.getName());
//        } else if (array instanceof byte[]) {
//            node_.setAttribute(ELEMENT_TYPE, byte.class.getName());
//        } else if (array instanceof float[]) {
//            node_.setAttribute(ELEMENT_TYPE, float.class.getName());
//        } else if (array instanceof double[]) {
//            node_.setAttribute(ELEMENT_TYPE, double.class.getName());
//        } else if (array instanceof char[]) {
//            node_.setAttribute(ELEMENT_TYPE, char.class.getName());
//        } else if (array instanceof boolean[]) {
//            node_.setAttribute(ELEMENT_TYPE, boolean.class.getName());
//        } else {
//            String type_ = array.getClass().getName();
//            if (type_.startsWith(MULT_ARRAY_BEGIN)) {
//                //array with some dimensions
//                node_.setAttribute(ELEMENT_TYPE, type_.substring(1));
//            } else {
//                //array with one dimension
//                node_.setAttribute(ELEMENT_TYPE, StringList.removeStrings(type_.substring(2), ARRAY_END));
//            }
//        }
        return node_;

    }

    @Override
    Element serializeWithoutRef(Document _doc) {
        Element node_ = _doc.createElement(ARRAY);
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
        node_.setAttribute(ELEMENT_TYPE, array.getClass().getComponentType().getName());
//        if (array instanceof long[]) {
//            node_.setAttribute(ELEMENT_TYPE, long.class.getName());
//        } else if (array instanceof int[]) {
//            node_.setAttribute(ELEMENT_TYPE, int.class.getName());
//        } else if (array instanceof short[]) {
//            node_.setAttribute(ELEMENT_TYPE, short.class.getName());
//        } else if (array instanceof byte[]) {
//            node_.setAttribute(ELEMENT_TYPE, byte.class.getName());
//        } else if (array instanceof float[]) {
//            node_.setAttribute(ELEMENT_TYPE, float.class.getName());
//        } else if (array instanceof double[]) {
//            node_.setAttribute(ELEMENT_TYPE, double.class.getName());
//        } else if (array instanceof char[]) {
//            node_.setAttribute(ELEMENT_TYPE, char.class.getName());
//        } else if (array instanceof boolean[]) {
//            node_.setAttribute(ELEMENT_TYPE, boolean.class.getName());
//        } else {
//            String type_ = array.getClass().getName();
//            if (type_.startsWith(MULT_ARRAY_BEGIN)) {
//                //array with some dimensions
//                node_.setAttribute(ELEMENT_TYPE, type_.substring(1));
//            } else {
//                //array with one dimension
//                node_.setAttribute(ELEMENT_TYPE, StringList.removeStrings(type_.substring(2), ARRAY_END));
//            }
//        }
        return node_;
    }

    @Override
    void appendElementSerialWithoutRef(String _xml, CustList<ElementsSerial> _elt) {
        int index_ = 0;
        for (ElementsSerial e: _elt) {
            Object o_ = e.getValue();
//            if (o_ != null) {
//                if (!classElements.isInstance(o_)) {
//                    throw new IllegalArgumentException();
//                }
//            }
            Array.set(array, index_, o_);
            index_++;
        }
    }

    @Override
    void appendElementSerial(String _xml, CustList<ElementsSerial> _elt) {
        indexesRef = new NumberMap<Integer,Long>();
        int i_ = 0;
        for (ElementsSerial e: _elt) {
            if (e instanceof TemplateSerial) {
                if (((TemplateSerial)e).getRef() != null) {
                    indexesRef.put(i_, ((TemplateSerial)e).getRef());
                }
            }
            Object o_ = e.getValue();
//            if (o_ != null) {
//                if (!classElements.isInstance(o_)) {
//                    throw new IllegalArgumentException();
//                }
//            }
            Array.set(array, i_, o_);
            i_++;
        }
    }

    @Override
    void setElementSerial(String _xml, ElementsSerial _e, ElementsSerial _newE) {
        int l_ = Array.getLength(array);
        for (int i = CollectionsUtil.getFirstIndex();i<l_;i++) {
            if (!indexesRef.contains(i)) {
                continue;
            }
            if (!Numbers.eq(indexesRef.getVal(i), ((TemplateSerial)_e).getRef())) {
                continue;
            }
            Object o_ = _newE.getValue();
//            if (o_ != null) {
//                if (!classElements.isInstance(o_)) {
//                    throw new IllegalArgumentException();
//                }
//            }
            Array.set(array, i, o_);
        }
    }

    @Override
    Object getValue() {
        return array;
    }

//    @Override
//    Class<?> getValueClass() {
//        return array.getClass();
//    }

}
