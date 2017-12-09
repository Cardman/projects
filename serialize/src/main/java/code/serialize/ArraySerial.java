package code.serialize;
import java.lang.reflect.Array;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

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
    ArraySerial(Element _node, TemplateSerial _parent) {
        super(_node, _parent);
        if (!StringList.quickEq(_node.getTagName(),ARRAY)) {
            throw new ClassFoundException(_node.getTagName(), ARRAY);
        }
        NamedNodeMap map_ = _node.getAttributes();
//        if (map_ == null) {
//            throw new NoAttributeForSerializable(_node.getNodeName());
//        }
        Attr className_ = (Attr) map_.getNamedItem(CLASS);
        if (className_ != null) {
            setClassName(className_.getValue());
        }
        Attr typeName_ = (Attr) map_.getNamedItem(ELEMENT_TYPE);
        if (typeName_ == null) {
            throw new NoAttributeForSerializable(ELEMENT_TYPE,_node.getTagName());
        }
        Attr field_ = (Attr) map_.getNamedItem(FIELD);
        if (field_ != null) {
            setField(field_.getValue());
        }
        Attr keyOfMap_ = (Attr) map_.getNamedItem(KEY);
        if (keyOfMap_ != null) {
            setKeyOfMap(true);
        }
        Attr ref_ = (Attr) map_.getNamedItem(REF);
        if (ref_ != null) {
            setRef(Long.parseLong(ref_.getValue()));
            return;
        }
        Attr id_ = (Attr) map_.getNamedItem(ID);
        if (id_ != null) {
            setId(Long.parseLong(id_.getValue()));
        }
        Class<?> class_;
        String typeValue_ = typeName_.getValue();
        class_ = ConstClasses.classAliasForNameNotInit(typeValue_);
        array = Array.newInstance(class_, XmlParser.childrenElements(_node).size());
    }

    /**@throws ClassFoundException
    @throws NoAttributeForSerializable*/
    static ArraySerial newListSerial(Element _node) {
        if (!StringList.quickEq(_node.getTagName(),ARRAY)) {
            throw new ClassFoundException(_node.getTagName(), ARRAY);
        }
        NamedNodeMap map_ = _node.getAttributes();
//        if (map_ == null) {
//            throw new NoAttributeForSerializable(_node.getNodeName());
//        }
        Attr className_ = (Attr) map_.getNamedItem(CLASS);
        Attr typeName_ = (Attr) map_.getNamedItem(ELEMENT_TYPE);
        if (typeName_ == null) {
            throw new NoAttributeForSerializable(ELEMENT_TYPE,_node.getTagName());
        }
        ArraySerial listSerial_ = new ArraySerial();
        listSerial_.setNode(_node);
        Class<?> class_;
        String typeValue_ = typeName_.getValue();
        class_ = ConstClasses.classAliasForNameNotInit(typeValue_);
        listSerial_.array = Array.newInstance(class_, XmlParser.childrenElements(_node).size());
        Attr field_ = (Attr) map_.getNamedItem(FIELD);
        if (field_ != null) {
            listSerial_.setField(field_.getValue());
        }
        Attr keyOfMap_ = (Attr) map_.getNamedItem(KEY);
        if (keyOfMap_ != null) {
            listSerial_.setKeyOfMap(true);
        }
        if (className_ != null) {
            listSerial_.setClassName(className_.getValue());
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
            Array.set(array, i, o_);
        }
    }

    @Override
    Object getValue() {
        return array;
    }

}
