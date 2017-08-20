package code.serialize;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import code.serialize.exceptions.InvokingException;
import code.serialize.exceptions.NoSuchDeclaredMethodException;
import code.serialize.exceptions.RuntimeInstantiationException;
import code.util.AbsMap;
import code.util.CustList;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.annot.RwXml;
import code.util.consts.ConstClasses;
import code.util.exceptions.RuntimeClassNotFoundException;
import code.util.ints.Listable;
import code.util.ints.ListableEntries;
import code.util.opers.CollectionsUtil;
import code.xml.XmlParser;

final class ObjectSerial extends TemplateSerial {

    private static final int TAB_WIDTH = 4;

//    private static final int NO_DUPLICATE = 1;

//    private static final String ENUM ="enum";
//    private static final String ADD ="add";
//    private static final String SET ="set";
//    private static final String PUT ="put";

//    private static final Method PUT_METHOD;
//    private static final Method ADD_METHOD;
//    private static final Method SET_METHOD;
//    private static final Field LIST_FIELD = PairUtil.getMapListField();

//    static {
//        PUT_METHOD = putMethod();
//        ADD_METHOD = addMethod();
//        SET_METHOD = setMethod();
//    }

    private Object value;
    private CustList<Object> keys;
    private CustList<Object> values;
    private NumberMap<Integer,Long> indexesRef;
    private NumberMap<Integer,Long> keysIndexesRef;
    private NumberMap<Integer,Long> valuesIndexesRef;
//    private ComparatorSerial cmpSerial;

//    private ObjectSerial(){}
    private ObjectSerial(Element _node){
        super(_node);
    }

    ObjectSerial(Object _value) {
        value = _value;
    }
    /**@throws SecurityException
    @throws RuntimeInstantiationException
    @throws InvokingException
    @throws NoSuchDeclaredMethodException
    @throws RuntimeClassNotFoundException*/
    ObjectSerial(Element _node, boolean _requiredClass) {
        super(_node);
        NamedNodeMap map_ = _node.getAttributes();
        if(_requiredClass) {
            Node className_ = map_.getNamedItem(CLASS);
            if (className_ != null) {
                setClassName(className_.getNodeValue());
            }
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
        value = newInstance(_node);
//        try {
//            Class<?> class_ = Constants.classForName(_node.getNodeName());
//            Constructor<?> constr_ = class_.getDeclaredConstructor();
////            constr_.setAccessible(class_.getAnnotation(RwXml.class)!=null);
//            constr_.setAccessible(constr_.getAnnotation(RwXml.class)!=null);
//            value = constr_.newInstance();
//        } catch (InstantiationException _0) {
//            throw new RuntimeInstantiationException(_0);
//        } catch (NoSuchMethodException _0) {
//            throw new NoSuchDeclaredMethodException(_0);
//        } catch (InvocationTargetException _0) {
//            throw new InvokingException(_0);
//        } catch (IllegalArgumentException _0) {
//        } catch (IllegalAccessException _0) {
////            _0.printStackTrace();
//            throw new BadAccessException(_0);
//        }
    }

    /**@throws SecurityException
    @throws RuntimeInstantiationException
    @throws InvokingException
    @throws NoSuchDeclaredMethodException
    @throws RuntimeClassNotFoundException*/
    static ObjectSerial newSerial(Element _node, boolean _requiredClass) {
        NamedNodeMap map_ = _node.getAttributes();
        ObjectSerial serial_ = new ObjectSerial(_node);
        if(_requiredClass) {
            Node className_ = map_.getNamedItem(CLASS);
            if (className_ != null) {
                serial_.setClassName(className_.getNodeValue());
            }
        }
        Node field_ = map_.getNamedItem(FIELD);
        if (field_ != null) {
            serial_.setField(field_.getNodeValue());
        }
        Node keyOfMap_ = map_.getNamedItem(KEY);
        if (keyOfMap_ != null) {
            serial_.setKeyOfMap(true);
        }
        serial_.value = newInstance(_node);
//        try {
//            Class<?> class_ = Constants.classForName(_node.getNodeName());
//            Constructor<?> constr_ = class_.getDeclaredConstructor();
//    //        constr_.setAccessible(class_.getAnnotation(RwXml.class)!=null);
//            constr_.setAccessible(constr_.getAnnotation(RwXml.class)!=null);
//            serial_.value = constr_.newInstance();
//        } catch (InstantiationException _0) {
//            throw new RuntimeInstantiationException(_0);
//        } catch (NoSuchMethodException _0) {
//            throw new NoSuchDeclaredMethodException(_0);
//        } catch (InvocationTargetException _0) {
//            throw new InvokingException(_0);
//        } catch (IllegalArgumentException _0) {
//        } catch (IllegalAccessException _0) {
////            _0.printStackTrace();
//            throw new BadAccessException(_0);
////        } catch (InstantiationException e) {
////            throw e;
////        } catch (InvocationTargetException e) {
////            throw e;
//        }
        return serial_;
    }

    private static Object newInstance(Element _node) {
        Constructor<?> constr_ = null;
        try {
            Class<?> class_ = ConstClasses.classAliasForObjectNameNotInit(_node.getNodeName()+_node.getAttribute(INTERN));
            if (class_.isMemberClass() && !Modifier.isStatic(class_.getModifiers())) {
                Class<?> curClass_ = class_;
                CustList<Class<?>> classes_ = new CustList<Class<?>>();
                classes_.add(curClass_);
                while (curClass_.isMemberClass() && !Modifier.isStatic(curClass_.getModifiers())) {
                    curClass_ = curClass_.getDeclaringClass();
                    classes_.add(0, curClass_);
                }
                Object obj_ = null;
                for (Class<?> c: classes_) {
                    if (obj_ == null) {
                        constr_ = c.getDeclaredConstructor();
                    } else {
                        constr_ = c.getDeclaredConstructor(obj_.getClass());
                    }
                    constr_.setAccessible(constr_.getAnnotation(RwXml.class)!=null);
                    if (obj_ == null) {
                        obj_ = ConverterMethod.newInstance(constr_);
                    } else {
                        obj_ = ConverterMethod.newInstance(constr_, obj_);
                    }
                }
                return obj_;
            }
            constr_ = class_.getDeclaredConstructor();
//            constr_.setAccessible(class_.getAnnotation(RwXml.class)!=null);
            constr_.setAccessible(constr_.getAnnotation(RwXml.class)!=null);
            return ConverterMethod.newInstance(constr_);
        } catch (NoSuchMethodException _0) {
            throw new NoSuchDeclaredMethodException(_0);
        }
    }
//    private static Method putMethod() {
//        Method method_;
//        try {
//            method_ = ListableEntries.class.getMethod(PUT, Object.class, Object.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//            method_ = null;
//        }
//        return method_;
//    }

//    private static Method addMethod() {
//        Method method_;
//        try {
//            method_ = SerializeXmlObject.LS_CLASS.getMethod(ADD, Object.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//            method_ = null;
//        }
//        return method_;
//    }

//    private static Method setMethod() {
//        Method method_;
//        try {
//            method_ = SerializeXmlObject.LS_CLASS.getMethod(SET, int.class, Object.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//            method_ = null;
//        }
//        return method_;
//    }

    @Override
    Element serialize(Document _doc) {
        Element node_ = createNode(_doc);
//        if (value instanceof EnumMap) {
//            node_.setAttribute(ENUM, ((EnumMap<?,?>)value).getLocalClass().getName());
//        }
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
        return node_;
    }
    @Override
    Element serializeWithoutRef(Document _doc) {
        Element node_ = createNode(_doc);
//        if (value instanceof EnumMap) {
//            node_.setAttribute(ENUM, ((EnumMap<?,?>)value).getLocalClass().getName());
//        }
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
        return node_;
    }

    private Element createNode(Document _doc) {
        String name_ = getValueClass().getName();
        Element node_;
        int indexDollar_ = name_.indexOf(ElementsSerial.SEP_INTERN);
        if (indexDollar_ != -1) {
            node_ = _doc.createElement(name_.substring(0, indexDollar_));
            node_.setAttribute(ElementsSerial.INTERN, name_.substring(indexDollar_));
        } else {
            node_ = _doc.createElement(name_);
        }
        return node_;
    }
//    @Override
//    Element serialize(Document _doc) {
//        Element node_ = super.serialize(_doc);
//        if (value instanceof EnumMap) {
//            node_.setAttribute(ENUM, ((EnumMap<?,?>)value).getLocalClass().getName());
//        }
//        return node_;
//    }
//    @Override
//    Element serializeWithoutRef(Document _doc) {
//        Element node_ = super.serializeWithoutRef(_doc);
//        if (value instanceof EnumMap) {
//            node_.setAttribute(ENUM, ((EnumMap<?,?>)value).getLocalClass().getName());
//        }
//        return node_;
//    }
//    @Override
//    Class<?> getValueClass() {
//        return value.getClass();
//    }

    @Override
    Object getValue() {
        return value;
    }

//    public ComparatorSerial getCmpSerial() {
//        return cmpSerial;
//    }

    @Override
    void appendElementSerial(String _xml, CustList<ElementsSerial> _elt) {
        indexesRef = new NumberMap<Integer,Long>();
        int i_ = CollectionsUtil.getFirstIndex();
        keys = new CustList<Object>();
        keysIndexesRef = new NumberMap<Integer,Long>();
        int iKey_ = CollectionsUtil.getFirstIndex();
        values = new CustList<Object>();
        valuesIndexesRef = new NumberMap<Integer,Long>();
        int iValue_ = CollectionsUtil.getFirstIndex();
        for (ElementsSerial e: _elt) {
//            if (e instanceof ComparatorSerial) {
//                continue;
//            }
            if (e.getClassName().equalsIgnoreCase(SerializeXmlObject.LS_CLASS)) {
                if (e instanceof TemplateSerial) {
                    if (((TemplateSerial)e).getRef() != null) {
                        indexesRef.put(i_, ((TemplateSerial)e).getRef());
                    }
                }
                CustList.add((CustList<?>)value, e.getValue());
//                ((Listable<Object>)value).add(e.getValue());
//                ADD_METHOD.invoke(value, e.getValue());
                i_++;
                continue;
            }
        }
        for (ElementsSerial e: _elt) {
//            if (e instanceof ComparatorSerial) {
//                cmpSerial = (ComparatorSerial) e;
//                continue;
//            }
            if (!e.getClassName().equalsIgnoreCase(SerializeXmlObject.MP_CLASS)) {
                continue;
            }
            if (!e.isKeyOfMap()) {
                if (e instanceof TemplateSerial) {
                    if (((TemplateSerial)e).getRef() != null) {
                        valuesIndexesRef.put(iValue_, ((TemplateSerial)e).getRef());
                    }
                }
                values.add(e.getValue());
                iValue_++;
            } else {
                if (e instanceof TemplateSerial) {
                    if (((TemplateSerial)e).getRef() != null) {
                        keysIndexesRef.put(iKey_, ((TemplateSerial)e).getRef());
                    }
                }
                keys.add(e.getValue());
                iKey_++;
            }
        }
        for (ElementsSerial e: _elt) {
//            if (e instanceof ComparatorSerial) {
//                continue;
//            }
            if (e.getClassName().equalsIgnoreCase(SerializeXmlObject.LS_CLASS)) {
                continue;
            }
            if (e.getClassName().equalsIgnoreCase(SerializeXmlObject.MP_CLASS)) {
                continue;
            }
            try {
                Class<?> class_ = ConstClasses.classAliasForObjectNameNotInit(e.getClassName());
                if (!class_.isInstance(value)) {
                    continue;
                }
                setField(e, class_, e.getValue());
            } catch (RuntimeException _0) {
                System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xml, e.getNode(), 0, EMPTY_STRING, TAB_WIDTH));
                throw _0;
            } catch (Error _0) {
                System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xml, e.getNode(), 0, EMPTY_STRING, TAB_WIDTH));
                throw _0;
            }
//            Field field_;
//            try {
//                field_ = class_.getDeclaredField(e.getField());
//            } catch (NoSuchFieldException _0) {
//                continue;
//            }
////            if (!SerializeXmlObject.isUpdateFinalFields()) {
////                if (Modifier.isFinal(field_.getModifiers())) {
////                    continue;
////                }
////            }
//            if (Modifier.isStatic(field_.getModifiers())) {
//                continue;
//            }
//            field_.setAccessible(class_.getAnnotation(RwXml.class)!=null || field_.getAnnotation(RwXml.class) != null);
//            ConverterMethod.setField(field_, value, e.getValue());
        }
        //Begin
        if (value instanceof ListableEntries<?,?>) {
            ListableEntries<?, ?> v_;
            v_ = (ListableEntries<?, ?>)value;
//            Listable<EntryCust<Object, Object>> list_;
//            list_ = ((ListableEntries<Object, Object>)value).entryList();
            int len_ = keys.size();
            if (len_ != values.size()) {
                System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xml, getNode(), 0, EMPTY_STRING, TAB_WIDTH));
            }
            for (int i = CollectionsUtil.getFirstIndex(); i< len_; i++) {
                //PUT_METHOD.invoke(value, keys.get(i),values.get(i));
//                ADD_METHOD.invoke(list_, new EntryCust<Object, Object>(keys.get(i),values.get(i)));
                AbsMap.addEntry(v_, keys.get(i),values.get(i));
//                list_.add(new EntryCust<Object, Object>(keys.get(i),values.get(i)));
            }
            keys.clear();
            values.clear();
        }
        //End
    }
    @Override
    void appendElementSerialWithoutRef(String _xml, CustList<ElementsSerial> _elt) {
        keys = new CustList<Object>();
        values = new CustList<Object>();
        for (ElementsSerial e: _elt) {
//            if (e instanceof ComparatorSerial) {
//                continue;
//            }
            if (e.getClassName().equalsIgnoreCase(SerializeXmlObject.LS_CLASS)) {
//                ((Listable<Object>)value).add(e.getValue());
                CustList.add((CustList<?>)value, e.getValue());
//                ADD_METHOD.invoke(value, e.getValue());
                continue;
            }
        }
        for (ElementsSerial e: _elt) {
//            if (e instanceof ComparatorSerial) {
//                cmpSerial = (ComparatorSerial) e;
//                continue;
//            }
            if (!e.getClassName().equalsIgnoreCase(SerializeXmlObject.MP_CLASS)) {
                continue;
            }
            if (!e.isKeyOfMap()) {
                values.add(e.getValue());
            } else {
                keys.add(e.getValue());
            }
        }
        for (ElementsSerial e: _elt) {
//            if (e instanceof ComparatorSerial) {
//                continue;
//            }
            if (e.getClassName().equalsIgnoreCase(SerializeXmlObject.LS_CLASS)) {
                continue;
            }
            if (e.getClassName().equalsIgnoreCase(SerializeXmlObject.MP_CLASS)) {
                continue;
            }
            try {
                Class<?> class_ = ConstClasses.classAliasForObjectNameNotInit(e.getClassName());
                if (!class_.isInstance(value)) {
                    continue;
                }
                setField(e, class_, e.getValue());
            } catch (RuntimeException _0) {
                System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xml, e.getNode(), 0, EMPTY_STRING, TAB_WIDTH));
                throw _0;
            } catch (Error _0) {
                System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xml, e.getNode(), 0, EMPTY_STRING, TAB_WIDTH));
                throw _0;
            }
        }
//        if (CollectionsUtil.isMap(value))
        if (value instanceof ListableEntries<?,?>) {
//            Object list_ = LIST_FIELD.get(value);
            ListableEntries<?, ?> v_;
            v_ = (ListableEntries<?, ?>)value;
//            Listable<EntryCust<Object, Object>> list_;
//            list_ = ((ListableEntries<Object, Object>)value).entryList();
            int len_ = keys.size();
            if (len_ != values.size()) {
                System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xml, getNode(), 0, EMPTY_STRING, TAB_WIDTH));
            }
            for (int i = CollectionsUtil.getFirstIndex(); i< len_; i++) {
                //PUT_METHOD.invoke(value, keys.get(i),values.get(i));
//                ADD_METHOD.invoke(list_, new EntryCust<Object, Object>(keys.get(i),values.get(i)));
                AbsMap.addEntry(v_, keys.get(i),values.get(i));
//                list_.add(new EntryCust<Object, Object>(keys.get(i),values.get(i)));
            }
            keys.clear();
            values.clear();
        }
//        if (cmpSerial != null) {
//            Field f_ = TreeSerializeXmlObject.MP_CLASS.getDeclaredField(COMPARATOR);
//            //The comparator of a treemap is only set here by reflection
//            f_.setAccessible(true);
//            f_.set(value, cmpSerial.getValue());
//        }
//        if (value instanceof SortableMap) {
//            int len_ = keys.size();
//            for (int i = Constants.getFirstIndex(); i< len_; i++) {
//                ((SortableMap<Object,Object>)value).put(keys.get(i),values.get(i));
//            }
//            keys.clear();
//            values.clear();
//        }
    }
    private void setField(ElementsSerial _e, Class<?> _class, Object _value) {
        Field field_;
        try {
            field_ = _class.getDeclaredField(_e.getField());
        } catch (NoSuchFieldException _0) {
            return;
        }
//            if (!SerializeXmlObject.isUpdateFinalFields()) {
//                if (Modifier.isFinal(field_.getModifiers())) {
//                    continue;
//                }
//            }
        if (Modifier.isStatic(field_.getModifiers())) {
            return;
        }
        field_.setAccessible(_class.getAnnotation(RwXml.class)!=null || field_.getAnnotation(RwXml.class) != null);
        ConverterMethod.setField(field_, value, _value);
    }
    @Override
    void setElementSerial(String _xml, ElementsSerial _e,ElementsSerial _newE) {
        if(value instanceof Listable<?>) {
            int len_ = ((Listable<?>)value).size();
            for (int i=CollectionsUtil.getFirstIndex();i<len_;i++) {
                if (!indexesRef.contains(i)) {
                    continue;
                }
                if (!Numbers.eq(indexesRef.getVal(i), ((TemplateSerial)_e).getRef())) {
                    continue;
                }
                CustList.set((Listable<?>)value, i, _newE.getValue());
//                ((Listable<Object>)value).set(i, _newE.getValue());
//                SET_METHOD.invoke(value, i, _newE.getValue());
            }
        }
        if(value instanceof ListableEntries<?,?>) {
            ListableEntries<?, ?> v_;
            v_ = (ListableEntries<?, ?>)value;
//            Listable<EntryCust<Object, Object>> list_;
//            list_ = ((ListableEntries<Object, Object>)value).entryList();
//            int len_ = keys.size() ;
            int len_ = v_.size();
            for (int i = CollectionsUtil.getFirstIndex(); i< len_;i++ ) {
                if (!keysIndexesRef.contains(i)) {
                    continue;
                }
                if (!Numbers.eq(keysIndexesRef.getVal(i), ((TemplateSerial)_e).getRef())) {
                    continue;
                }
                AbsMap.setGeneKey(v_, i, _newE.getValue());
//                list_.set(index, element);
//                keys.set(i, _newE.getValue());
            }
            for (int i = CollectionsUtil.getFirstIndex(); i < len_; i++) {
                if (!valuesIndexesRef.contains(i)) {
                    continue;
                }
                if (!Numbers.eq(valuesIndexesRef.getVal(i), ((TemplateSerial)_e).getRef())) {
                    continue;
                }
                AbsMap.setGeneValue(v_, i, _newE.getValue());
//                values.set(i, _newE.getValue());
            }
        }
        try {
            String className_ = _e.getClassName();
            if (StringList.quickEq(className_, SerializeXmlObject.LS_CLASS)) {
                return;
            }
            if (StringList.quickEq(className_, SerializeXmlObject.MP_CLASS)) {
                return;
            }
            Class<?> class_ = ConstClasses.classAliasForObjectNameNotInit(_e.getClassName());
            if (!class_.isInstance(value)) {
                return;
            }
            String fieldName_ = _e.getField();
            if (fieldName_ == null) {
                return;
            }
            setField(_e, class_, _newE.getValue());
        } catch (RuntimeException _0) {
            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xml, _e.getNode(), 0, EMPTY_STRING, TAB_WIDTH));
            throw _0;
        } catch (Error _0) {
            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xml, _e.getNode(), 0, EMPTY_STRING, TAB_WIDTH));
            throw _0;
        }
//        Field field_;
//        try {
//            field_ = class_.getDeclaredField(_e.getField());
//        } catch (NoSuchFieldException _0) {
//            return;
//        }
////        if (!SerializeXmlObject.isUpdateFinalFields()) {
////            if (Modifier.isFinal(field_.getModifiers())) {
////                return;
////            }
////        }
//        if (Modifier.isStatic(field_.getModifiers())) {
//            return;
//        }
//        field_.setAccessible(class_.getAnnotation(RwXml.class)!=null|| field_.getAnnotation(RwXml.class) != null);
//        ConverterMethod.setField(field_, value, _newE.getValue());
    }
//    void setComponents(String _xml) {
//        if (!(value instanceof ListableEntries<?, ?>)) {
//            if (value instanceof HasComparator<?>) {
//                ((HasComparator<?>)value).applyChanges();
//            }
//            return;
//        }
//        ListableEntries<?, ?> l_ = (ListableEntries<?, ?>) value;
//        int len_ = keys.size();
//        for (int i = CollectionsUtil.getFirstIndex(); i< len_; i++) {
//            AbsMap.put(l_, keys.get(i),values.get(i));
//        }
////        if (value instanceof HasComparator<?>) {
////            ((HasComparator<?>)value).applyChanges();
////        }
//    }
    boolean isMap() {
        return value instanceof ListableEntries<?, ?>;
    }
    boolean isCorrect() {
        ListableEntries<?, ?> l_;
        l_ = (ListableEntries<?, ?>)value;
        return l_.isCorrect();
    }
//    @Override
//    public void setComponents(String _xml) {
////        if (cmpSerial != null) {
//////            if (value instanceof SortableMap) {
//////                Field f_ = util.TreeSerializeXmlObject.MP_CLASS.getDeclaredField(COMPARATOR);
//////                f_.setAccessible(true);
//////                f_.set(value, cmpSerial.getValue());
//////            } else {
//////                Field f_ = TreeSerializeXmlObject.MP_CLASS.getDeclaredField(COMPARATOR);
//////                f_.setAccessible(true);
//////                f_.set(value, cmpSerial.getValue());
//////            }
////            try {
////                Field f_;
////                f_ = TreeSerializeXmlObject.MP_CLASS.getDeclaredField(COMPARATOR);
////                //The comparator of a treemap is only set here by reflection
////                f_.setAccessible(true);
////                ConverterMethod.setField(f_, value, cmpSerial.getValue());
////            } catch (NoSuchFieldException _0) {
////            }
//////            Field f_ = TreeSerializeXmlObject.MP_CLASS.getDeclaredField(COMPARATOR);
//////            f_.setAccessible(true);
//////            f_.set(value, cmpSerial.getValue());
////        }
//        int len_ = keys.size();
//        if (len_ != values.size()) {
//            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xml, getNode(), 0, EMPTY_STRING, TAB_WIDTH));
//        }
//        for (int i = CollectionsUtil.getFirstIndex(); i< len_; i++) {
////            PUT_METHOD.invoke(value, keys.get(i),values.get(i));
//            ((ListableEntries<Object,Object>)value).put(keys.get(i),values.get(i));
//        }
//        if (value instanceof HasComparator<?>) {
//            ((HasComparator<?>)value).applyChanges();
//        }
//    }
//    @Override
//    public boolean keysAllDifferent() {
//        for (Object o: keys) {
//            if (Collections.frequency(keys, o) > NO_DUPLICATE) {
//                return false;
//            }
//        }
//        return true;
//    }
//    @Override
//    public boolean mapIsEmpty() {
//        return keys.isEmpty();
//    }

}
