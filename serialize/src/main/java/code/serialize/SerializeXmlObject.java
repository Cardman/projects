package code.serialize;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import code.serialize.exceptions.BadAccessException;
import code.serialize.exceptions.BadObjectException;
import code.serialize.exceptions.ClassFoundException;
import code.serialize.exceptions.DuplicatesKeysException;
import code.serialize.exceptions.InexistingValueForEnum;
import code.serialize.exceptions.InvokingException;
import code.serialize.exceptions.NoAttributeForSerializable;
import code.serialize.exceptions.NoSuchDeclaredFieldException;
import code.serialize.exceptions.NoSuchDeclaredMethodException;
import code.serialize.exceptions.NoValueException;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.annot.RwXml;
import code.util.exceptions.RuntimeClassNotFoundException;
import code.util.ints.ChangeableMap;
import code.util.ints.Viewable;
import code.xml.XmlParser;

public final class SerializeXmlObject {

    public static final String RESOURCES_FOLDER = "resources_stream/classes";
    //TODO a const string instead
    static final String LS_CLASS = "a";
    static final String MP_CLASS = "m";
//    static final String LS_CLASS = CollectionsUtil.getListClass().getName();
//    static final String MP_CLASS = CollectionsUtil.getMapClass().getName();
    private static final String SEPARATEUR = "/";
    private static final int TAB_WIDTH = 4;
    private static final String NULL_OBJECT = "null object";
//    private static final String UNCHECKED = "unchecked";
    private static final String DOT = ".";
    private static final String LEFT_PAR = "(";
    private static final String COMMA = ",";
    private static final String RIGHT_PAR = ")";
    private static final String EMPTY_STRING = "";
    private static final String RETURN_TAB = "\n\t";
    private static final String ALL = "*";

    private static final String BEGIN_ESC = "&#";

    private static final String END_ESC = ";";

    private static final short LIMIT_EMPTY_CHARS = 32;

    private static final short LIMIT_ASCII = 128;

//    private static boolean _updateFinalFields_ = true;
    private static boolean _references_;
    private static boolean _copying_;
    private static boolean _checkReferences_;

    private SerializeXmlObject() {
    }

    /**@throws NoSuchDeclaredMethodException*/
    static Method getDeclaredXmlAccessibleMethod(Class<?> _class, String _name, Class<?>... _argsClass) {
        Method m_ = getDeclaredMethod(_class, _name, _argsClass);
        m_.setAccessible(m_.isAnnotationPresent(RwXml.class));
        return m_;
    }

    public static Method getDeclaredMethod(Class<?> _class, String _name, Class<?>... _argsClass) {
        Class<?> class_ = _class;
        StringList traces_ = new StringList();
        while (class_ != null) {
            boolean addToTrace_ = false;
            try {
                Method method_ = class_.getDeclaredMethod(_name, _argsClass);
                return method_;
            } catch (NoSuchMethodException _0) {
                if (class_.getSuperclass() == Object.class) {
                    addToTrace_ = true;
                }
            }
            if (addToTrace_) {
                String trace_ = _class.getName()+DOT+_name+LEFT_PAR;
                StringList classes_ = new StringList();
                for (Class<?> c: _argsClass) {
                    classes_.add(c.getName());
                }
                trace_ += classes_.join(COMMA);
                trace_ += RIGHT_PAR;
                traces_.add(trace_);
            }
            class_ = class_.getSuperclass();
        }
        throw new NoSuchDeclaredMethodException(traces_.join(RETURN_TAB));
    }

    /**@throws NoSuchDeclaredFieldException*/
    public static Field getDeclaredField(Class<?> _class, String _name) {
        Class<?> class_ = _class;
        StringList traces_ = new StringList();
        while (class_ != null) {
            try {
                Field field_ = class_.getDeclaredField(_name);
                return field_;
            } catch (NoSuchFieldException _0) {
                String trace_ = class_.getName()+DOT+_name;
                traces_.add(trace_);
            }
            class_ = class_.getSuperclass();
        }
        throw new NoSuchDeclaredFieldException(traces_.join(RETURN_TAB));
    }

    public static String replaceClassFields(String _xmlFile,
            StringMap<String> _classes,
            StringMap<String> _fields) {
        try {
            Document doc_ = XmlParser.parseSax(_xmlFile);
            NodeList list_ = doc_.getElementsByTagName(ALL);
            int length_ = list_.getLength();
            for (int i = CustList.FIRST_INDEX; i < length_; i++) {
                Node node_ = list_.item(i);
                if (!(node_ instanceof Element)) {
                    continue;
                }
                Element elt_ = (Element) node_;
                for (String f: _fields.getKeys()) {
                    StringList l_ = StringList.splitStrings(f, SEPARATEUR);
                    if (!StringList.quickEq(elt_.getAttribute(ElementsSerial.CLASS),l_.first())) {
                        continue;
                    }
                    if (!StringList.quickEq(elt_.getAttribute(ElementsSerial.FIELD),l_.last())) {
                        continue;
                    }
                    elt_.setAttribute(ElementsSerial.FIELD, _fields.getVal(f));
                }
            }
            for (int i = CustList.FIRST_INDEX; i < length_; i++) {
                Node node_ = list_.item(i);
                if (!(node_ instanceof Element)) {
                    continue;
                }
                Element elt_ = (Element) node_;
                for (String f: _classes.getKeys()) {
                    if (StringList.quickEq(node_.getNodeName(),f)) {
                        doc_.renameNode(node_, node_.getNamespaceURI(), _classes.getVal(f));
                    }
                    if (!StringList.quickEq(elt_.getAttribute(ElementsSerial.CLASS),f)) {
                        continue;
                    }
                    elt_.setAttribute(ElementsSerial.CLASS, _classes.getVal(f));
                }
            }
            return XmlParser.toXml(doc_.getDocumentElement());
        } catch (RuntimeException _0) {
            return EMPTY_STRING;
        }
    }

    public static String replaceClass(String _xmlFile,
            StringMap<String> _classes) {
        try {
            Document doc_ = XmlParser.parseSax(_xmlFile);
            NodeList list_ = doc_.getElementsByTagName(ALL);
            int length_ = list_.getLength();
            for (int i = CustList.FIRST_INDEX; i < length_; i++) {
                Node node_ = list_.item(i);
                if (!(node_ instanceof Element)) {
                    continue;
                }
                for (String f: _classes.getKeys()) {
                    if (StringList.quickEq(node_.getNodeName(),f)) {
                        doc_.renameNode(node_, node_.getNamespaceURI(), _classes.getVal(f));
                    }
                }
            }
            return XmlParser.toXml(doc_.getDocumentElement());
        } catch (RuntimeException _0) {
            return EMPTY_STRING;
        }
    }

    public static Object newObjectFromXmlString(String _xmlString) {
        Object o_ = newObjectFromXmlStringOrNull(_xmlString);
        if (o_ != null) {
            return o_;
        }
        throw new BadObjectException(NULL_OBJECT);
    }

    public static Object newObjectFromXmlStringOrNull(String _xmlString) {
        //, boolean _acceptNull
        try {
            return fromXmlStringObject(_xmlString);
        } catch (RuntimeException _0) {
            throw new BadObjectException(_0);
        }
    }

    public static Object fromXmlStringObject(String _xmlString) {
//        if (!SecurityManagerUtil.canUseReflection()) {
////            InputSource is_ = new InputSource();
////            is_.setEncoding(StandardCharsets.UTF_8.name());
////            is_.setCharacterStream(new StringReader(_xmlString));
////            XMLDecoder decoder_ = new XMLDecoder(is_);
//            XMLDecoder decoder_ = new XMLDecoder(new ByteArrayInputStream(_xmlString.getBytes()));
//            Object obj_ = decoder_.readObject();
//            decoder_.close();
//            return obj_;
//        }
        Element root_ = XmlParser.documentElement(XmlParser.parseSax(_xmlString));
        try {
            ElementsSerial elt_ = createPrimitive(_xmlString, root_);
            if (elt_ != null) {
                return elt_.getValue();
            }
        } catch (RuntimeException _0) {
        }
        CustList<Node> currentNodesToBeRead_ = new CustList<Node>();
        currentNodesToBeRead_.add(root_);
        CustList<TemplateSerial> currentSerializableElements_ = new CustList<TemplateSerial>();
        ObjectSerial rootElement_;
//        CustList<ObjectSerial> obj_;
//        obj_ = new CustList<ObjectSerial>();
        CustList<ChangeableMap> cmp_;
        cmp_ = new CustList<ChangeableMap>();
        CustList<ObjectSerial> notEmptyMaps_ = new CustList<ObjectSerial>();
        if (_references_) {
            rootElement_ = new ObjectSerial(root_, false);
            currentSerializableElements_.add(rootElement_);
            CustList<Node> newNodesToBeRead_ = new CustList<Node>();
            CustList<TemplateSerial> newSerializableElements_ = new CustList<TemplateSerial>();
            CustList<TemplateSerial> serializableComposite_ = new CustList<TemplateSerial>();
            serializableComposite_.add(rootElement_);
            CustList<TemplateSerial> serializableIdComposite_ = new CustList<TemplateSerial>();
            if (rootElement_.getId() != null) {
                serializableIdComposite_.add(rootElement_);
            }
            SerialMap<CustList<TemplateSerial>> treeRefComposites_ = new SerialMap<CustList<TemplateSerial>>();
            SerialMap<CustList<TemplateSerial>> treeIdComposites_ = new SerialMap<CustList<TemplateSerial>>();
            CustList<TemplateSerial> serializableRefComposite_ = new CustList<TemplateSerial>();
            boolean modif_ = true;
            while (modif_) {
                modif_ = false;
                newSerializableElements_ = new CustList<TemplateSerial>();
                newNodesToBeRead_ = new CustList<Node>();
                int len_;
                len_ = currentNodesToBeRead_.size();
                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    Node currentNode_ = currentNodesToBeRead_.get(i);
                    TemplateSerial composite_ = currentSerializableElements_.get(i);
//                    boolean isTreeMap_ = false;
//                    boolean containsTree_ = false;
//                    for (Node nCh_: XmlParser.childrenNodes(currentNode_)) {
//                        containsTree_ = nCh_.getAttributes().getNamedItem(TemplateSerial.COMPARATOR) != null;
//                        if (containsTree_) {
//                            break;
//                        }
//                    }
//                    if (containsTree_) {
//                        isTreeMap_ = true;
//                    }
                    CustList<ElementsSerial> elt_ = new CustList<ElementsSerial>();
                    CustList<TemplateSerial> childRefComposites_ = new CustList<TemplateSerial>();
                    CustList<TemplateSerial> childIdComposites_ = new CustList<TemplateSerial>();
                    for (Element n : XmlParser.childrenElements(currentNode_)) {
                        try {
                            ArraySerial serial_ = new ArraySerial(n);
                            elt_.add(serial_);
                            if (serial_.getRef() == null) {
                                newSerializableElements_.add(serial_);
                                newNodesToBeRead_.add(n);
                                serializableComposite_.add(serial_);
                                if (serial_.getId() == null) {
                                    continue;
                                }
                                serializableIdComposite_.add(serial_);
                                childIdComposites_.add(serial_);
                            } else {
                                childRefComposites_.add(serial_);
                                serializableRefComposite_.add(serial_);
                            }
                            continue;
                        } catch (ClassFoundException _0) {
//                        } catch (NoAttributeForSerializable e_) {
//                            throw e_;
//                        } catch (Exception e_) {

                        } catch (RuntimeException _0) {
                            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xmlString, n, 0,EMPTY_STRING, TAB_WIDTH));
                            throw _0;
                        } catch (Error _0) {
                            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xmlString, n, 0,EMPTY_STRING, TAB_WIDTH));
                            throw _0;
                        }
                        ElementsSerial primitive_ = createPrimitive(_xmlString, n);
                        if (primitive_ != null) {
                            elt_.add(primitive_);
                            continue;
                        }
//                        try {
//                            ElementsSerial primitive_ = createPrimitive(n);
//                            if (primitive_ == null) {
//                                throw new NullSerialException();
//                            }
//                            elt_.add(primitive_);
//                            continue;
//                        } catch (NoAttributeForSerializable e_) {
//                            throw e_;
//                        } catch (InexistingValueForEnum e_) {
//                            throw e_;
//                        } catch (NumberFormatException e_) {
//                            throw e_;
//                        } catch (InvocationTargetException e_) {
//                            throw e_;
//                        } catch (InstantiationException e_) {
//                            throw e_;
//                        } catch (SecurityException e_) {
//                            throw e_;
//                        } catch (ClassNotFoundException e_) {
//                            throw e_;
//                        } catch (NullSerialException e_) {
//                        }
//                        ComparatorSerial cmp_ = getCmpSerial(n, isTreeMap_, composite_);
//                        if (cmp_ != null) {
//                            elt_.add(cmp_);
//                            if (cmp_.getRef() == null) {
//                                newSerializableElements_.add(cmp_);
//                                newNodesToBeRead_.add(n);
//                                serializableComposite_.add(cmp_);
//                                if (cmp_.getId() != null) {
//                                    serializableIdComposite_.add(cmp_);
//                                    childIdComposites_.add(cmp_);
//                                }
//                            } else {
//                                childRefComposites_.add(cmp_);
//                                serializableRefComposite_.add(cmp_);
//                            }
//                            continue;
//                        }
                        ObjectSerial serial_;
                        try {
                            serial_ = new ObjectSerial(n, true);
                        } catch (RuntimeException _0) {
                            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xmlString, n, 0,EMPTY_STRING, TAB_WIDTH));
                            throw _0;
                        } catch (Error _0) {
                            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xmlString, n, 0,EMPTY_STRING, TAB_WIDTH));
                            throw _0;
                        }
                        elt_.add(serial_);
                        if (serial_.getRef() == null) {
//                            obj_.add(serial_);
                            if (serial_.isMap()) {
                                notEmptyMaps_.add(serial_);
                            }
                            if (serial_.getValue() instanceof ChangeableMap) {
                                cmp_.add((ChangeableMap)serial_.getValue());
                            }
                            newSerializableElements_.add(serial_);
                            newNodesToBeRead_.add(n);
                            serializableComposite_.add(serial_);
                            if (serial_.getId() == null) {
                                continue;
                            }
                            serializableIdComposite_.add(serial_);
                            childIdComposites_.add(serial_);
                        } else {
                            childRefComposites_.add(serial_);
                            serializableRefComposite_.add(serial_);
                        }
                    }
//                    boolean found_;
//                    found_ = false;
//                    for (Pair<TemplateSerial,List<TemplateSerial>> p: treeRefComposites_.getElements()) {
//                        if (p.getFirst().getValue() == composite_.getValue()) {
//                            p.setSecond(childRefComposites_);
//                            found_ = true;
//                            break;
//                        }
//                    }
//                    if (!found_) {
//                        treeRefComposites_.getElements().add(new Pair<>(composite_, childRefComposites_));
//                    }
                    treeRefComposites_.getElements().add(new TemplateSerialValue<CustList<TemplateSerial>>(composite_, childRefComposites_));
//                    found_ = false;
//                    for (Pair<TemplateSerial,List<TemplateSerial>> p: treeIdComposites_.getElements()) {
//                        if (p.getFirst().getValue() == composite_.getValue()) {
//                            p.setSecond(childIdComposites_);
//                            found_ = true;
//                            break;
//                        }
//                    }
//                    if (!found_) {
//                        treeIdComposites_.getElements().add(new Pair<>(composite_, childIdComposites_));
//                    }
                    treeIdComposites_.getElements().add(new TemplateSerialValue<CustList<TemplateSerial>>(composite_, childIdComposites_));
//                    treeRefComposites_.put(composite_, childRefComposites_);
//                    treeIdComposites_.put(composite_, childIdComposites_);
                    composite_.appendElementSerial(_xmlString, elt_);
//                    if (composite_ instanceof MayBeMap) {
//                        if (!((MayBeMap) composite_).mapIsEmpty()) {
//                            notEmptyMaps_.add((MayBeMap) composite_);
//                        }
//                    }
                }
                if (!newSerializableElements_.isEmpty()) {
                    currentNodesToBeRead_ = new CustList<Node>(newNodesToBeRead_);
                    currentSerializableElements_ = new CustList<TemplateSerial>(newSerializableElements_);
                    modif_ = true;
                }
            }
            for (TemplateSerial e : serializableRefComposite_) {
                CustList<TemplateSerial> ls_ = new CustList<TemplateSerial>();
                CustList<TemplateSerial> lsTwo_ = new CustList<TemplateSerial>();
                for (TemplateSerial eTwo_ : serializableIdComposite_) {
                    if (!Numbers.eq(e.getRef(), eTwo_.getId())) {
                        continue;
                    }
                    ls_.add(e);
                    lsTwo_.add(eTwo_);
                    break;
                }
                if (ls_.isEmpty()) {
                    continue;
                }
                TemplateSerial newId_ = lsTwo_.first();
                for (TemplateSerial eTwo_ : serializableComposite_) {
//                    List<TemplateSerial> elts_ = treeRefComposites_.getVal(eTwo_);
                    CustList<TemplateSerial> elts_ = new CustList<TemplateSerial>();
                    for (TemplateSerialValue<CustList<TemplateSerial>> p: treeRefComposites_.getElements()) {
                        if (ElementsSerial.sameValue(p.getTemplate(), eTwo_)) {
                            elts_ = p.getValue();
                            break;
                        }
                    }
                    boolean contained_ = false;
                    for (TemplateSerial eThree_ : elts_) {
                        if (!Numbers.eq(e.getRef(), eThree_.getRef())) {
                            continue;
                        }
                        contained_ = true;
                    }
                    if (!contained_) {
                        continue;
                    }
                    eTwo_.setElementSerial(_xmlString, e, newId_);
                    break;
                }
            }
            /*List<MayBeMap> filledMaps_ = new List<MayBeMap>();
            List<MayBeMap> fillableMaps_ = new List<MayBeMap>();
            for (MayBeMap m : notEmptyMaps_) {
                if (m.keysAllDifferent()) {
                    fillableMaps_.add(m);
                }
            }
            while (true) {
                for (MayBeMap m : fillableMaps_) {
                    m.setComponents(_xmlString);
                    filledMaps_.add(m);
                }
                fillableMaps_.clear();
                for (MayBeMap m : notEmptyMaps_) {
                    if (!m.keysAllDifferent()) {
                        continue;
                    }
                    if (containsPossibleMap(filledMaps_, m)) {
                        continue;
                    }
                    fillableMaps_.add(m);
                }
                if (fillableMaps_.isEmpty()) {
                    break;
                }
            }
            for (MayBeMap m : notEmptyMaps_) {
                if (!containsPossibleMap(filledMaps_, m)) {
                    m.setComponents(_xmlString);
                }
            }*/
//            for (ObjectSerial o: obj_.getReverse()) {
//                o.setComponents(_xmlString);
//            }
            for (ChangeableMap c: cmp_.getReverse()) {
                c.applyChanges();
            }
            for (TemplateSerial t: serializableComposite_.getReverse()) {
                Object o_ = t.getValue();
                if (o_ instanceof XmlTransientable) {
                    ((XmlTransientable)o_).afterLoad();
                }
            }
            for (ObjectSerial p: notEmptyMaps_) {
                if (!p.isCorrect()) {
                    throw new DuplicatesKeysException();
                }
            }
        } else {
            rootElement_ = ObjectSerial.newSerial(root_, false);
            currentSerializableElements_.add(rootElement_);
            CustList<Node> newNodesToBeRead_ = new CustList<Node>();
            CustList<TemplateSerial> serializableComposite_ = new CustList<TemplateSerial>();
            CustList<TemplateSerial> newSerializableElements_ = new CustList<TemplateSerial>();
//            List<MayBeMap> notEmptyMaps_ = new List<MayBeMap>();
            boolean modif_ = true;
            while (modif_) {
                modif_ = false;
                newSerializableElements_ = new CustList<TemplateSerial>();
                newNodesToBeRead_ = new CustList<Node>();
                int len_;
                len_ = currentNodesToBeRead_.size();
                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    Node currentNode_ = currentNodesToBeRead_.get(i);
                    TemplateSerial composite_ = currentSerializableElements_.get(i);
//                    boolean isTreeMap_ = false;
//                    boolean containsTree_ = false;
//                    for (Node nCh_: XmlParser.childrenNodes(currentNode_)) {
//                        if (!(nCh_ instanceof Element)) {
//                            continue;
//                        }
//                        containsTree_ = nCh_.getAttributes().getNamedItem(TemplateSerial.COMPARATOR) != null;
//                        if (containsTree_) {
//                            break;
//                        }
//                    }
//                    if (containsTree_) {
//                        isTreeMap_ = true;
//                    }
                    CustList<ElementsSerial> elt_ = new CustList<ElementsSerial>();
                    for (Element n : XmlParser.childrenElements(currentNode_)) {
                        try {
                            ArraySerial serial_ = ArraySerial.newListSerial(n);
                            elt_.add(serial_);
                            newSerializableElements_.add(serial_);
                            newNodesToBeRead_.add(n);
                            continue;
//                        } catch (NoAttributeForSerializable e_) {
//                            throw e_;
                        } catch (ClassFoundException _0) {
                        } catch (RuntimeException _0) {
                            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xmlString, n, 0,EMPTY_STRING, TAB_WIDTH));
                            throw _0;
                        } catch (Error _0) {
                            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xmlString, n, 0,EMPTY_STRING, TAB_WIDTH));
                            throw _0;
                        }
                        ElementsSerial primitive_ = createPrimitive(_xmlString, n);
                        if (primitive_ != null) {
                            elt_.add(primitive_);
                            continue;
                        }
//                        try {
//                            ElementsSerial primitive_ = createPrimitive(n);
//                            if (primitive_ == null) {
//                                throw new NullSerialException();
//                            }
//                            elt_.add(primitive_);
//                            continue;
//                        } catch (NoAttributeForSerializable e_) {
//                            throw e_;
//                        } catch (InexistingValueForEnum e_) {
//                            throw e_;
//                        } catch (NumberFormatException e_) {
//                            throw e_;
//                        } catch (InvocationTargetException e_) {
//                            throw e_;
//                        } catch (InstantiationException e_) {
//                            throw e_;
//                        } catch (SecurityException e_) {
//                            throw e_;
//                        } catch (ClassNotFoundException e_) {
//                            throw e_;
//                        } catch (NullSerialException e_) {
//                        }
//                        ComparatorSerial cmp_ = getCmpSerial(n, isTreeMap_, composite_);
//                        if (cmp_ != null) {
//                            elt_.add(cmp_);
//                            newSerializableElements_.add(cmp_);
//                            newNodesToBeRead_.add(n);
//                            continue;
//                        }
                        ObjectSerial serial_;
                        try {
                            serial_ = ObjectSerial.newSerial(n, true);
                        } catch (RuntimeException _0) {
                            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xmlString, n, 0,EMPTY_STRING, TAB_WIDTH));
                            throw _0;
                        } catch (Error _0) {
                            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xmlString, n, 0,EMPTY_STRING, TAB_WIDTH));
                            throw _0;
                        }
//                        obj_.add(serial_);
                        if (serial_.getValue() instanceof ChangeableMap) {
                            cmp_.add((ChangeableMap)serial_.getValue());
                        }
                        if (serial_.isMap()) {
                            notEmptyMaps_.add(serial_);
                        }
                        elt_.add(serial_);
                        serializableComposite_.add(serial_);
                        newSerializableElements_.add(serial_);
                        newNodesToBeRead_.add(n);
                    }
                    composite_.appendElementSerialWithoutRef(_xmlString, elt_);
//                    if (composite_ instanceof MayBeMap) {
//                        if (!((MayBeMap) composite_).mapIsEmpty()) {
//                            notEmptyMaps_.add((MayBeMap) composite_);
//                        }
//                    }
                }
                if (!newSerializableElements_.isEmpty()) {
                    currentNodesToBeRead_ = new CustList<Node>(newNodesToBeRead_);
                    currentSerializableElements_ = new CustList<TemplateSerial>(newSerializableElements_);
                    modif_ = true;
                }
            }
            /*List<MayBeMap> filledMaps_ = new List<MayBeMap>();
            List<MayBeMap> fillableMaps_ = new List<MayBeMap>();
            for (MayBeMap m : notEmptyMaps_) {
                if (m.keysAllDifferent()) {
                    fillableMaps_.add(m);
                }
            }
            while (true) {
                for (MayBeMap m : fillableMaps_) {
                    m.setComponents(_xmlString);
                    filledMaps_.add(m);
                }
                fillableMaps_.clear();
                for (MayBeMap m : notEmptyMaps_) {
                    if (!m.keysAllDifferent()) {
                        continue;
                    }
                    if (containsPossibleMap(filledMaps_, m)) {
                        continue;
                    }
                    fillableMaps_.add(m);
                }
                if (fillableMaps_.isEmpty()) {
                    break;
                }
            }
            for (MayBeMap m : notEmptyMaps_) {
                if (!containsPossibleMap(filledMaps_, m)) {
                    m.setComponents(_xmlString);
                }
            }*/
//            for (ObjectSerial o: obj_.getReverse()) {
//                o.setComponents(_xmlString);
//            }
            for (ChangeableMap c: cmp_.getReverse()) {
                c.applyChanges();
            }
            for (TemplateSerial t: serializableComposite_.getReverse()) {
                Object o_ = t.getValue();
                if (o_ instanceof XmlTransientable) {
                    ((XmlTransientable)o_).afterLoad();
                }
            }
            for (ObjectSerial p: notEmptyMaps_) {
                if (!p.isCorrect()) {
                    throw new DuplicatesKeysException();
                }
            }
        }
        return rootElement_.getValue();
    }

//    private static boolean containsPossibleMap(List<MayBeMap> _filledMaps,
//            MayBeMap _map) {
//        for (MayBeMap m: _filledMaps) {
//            if (ElementsSerial.sameValue((ElementsSerial)_map, (ElementsSerial)m)) {
//                return true;
//            }
//        }
//        return false;
//    }

    /**@throws NoAttributeForSerializable
    @throws InexistingValueForEnum
    @throws NumberFormatException
    @throws RuntimeClassNotFoundException
    @throws InvokingException
    @throws RuntimeClassNotFoundException*/
    private static ElementsSerial createPrimitive(String _xml, Element _node) {
        try {
            return new NullSerial(_node);
        } catch (ClassFoundException _0) {
        }
        try {
            return new NumberSerial(_node);
//        } catch (NoAttributeForSerializable e_) {
//            throw e_;
//        } catch (NumberFormatException e_) {
//            throw e_;
        } catch (ClassFoundException _0) {
        } catch (RuntimeException _0) {
            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xml, _node, 0, EMPTY_STRING, TAB_WIDTH));
            throw _0;
        }
        try {
            return new StringSerial(_node);
//        } catch (NoAttributeForSerializable e_) {
//            throw e_;
        } catch (ClassFoundException _0) {
        } catch (RuntimeException _0) {
            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xml, _node, 0, EMPTY_STRING, TAB_WIDTH));
            throw _0;
        }
        try {
            return new CharacterSerial(_node);
//        } catch (NoAttributeForSerializable e_) {
//            throw e_;
        } catch (ClassFoundException _0) {
        } catch (RuntimeException _0) {
            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xml, _node, 0, EMPTY_STRING, TAB_WIDTH));
            throw _0;
        }
        try {
            return new BooleanSerial(_node);
//        } catch (NoAttributeForSerializable e_) {
//            throw e_;
        } catch (ClassFoundException _0) {
        } catch (RuntimeException _0) {
            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xml, _node, 0, EMPTY_STRING, TAB_WIDTH));
            throw _0;
        }
        try {
            return new StringObjectSerial(_node);
//        } catch (SecurityException e) {
//            throw e;
//        } catch (InvocationTargetException e) {
//            throw e;
//        } catch (InstantiationException e) {
//            throw e;
//        } catch (ClassNotFoundException e) {
//            throw e;
        } catch (NoSuchDeclaredMethodException _0) {
        } catch (BadAccessException _0) {
            return null;
        } catch (ClassCastException _0) {
        } catch (NoValueException _0) {
        } catch (RuntimeException _0) {
            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xml, _node, 0, EMPTY_STRING, TAB_WIDTH));
            throw _0;
        } catch (Error _0) {
            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xml, _node, 0, EMPTY_STRING, TAB_WIDTH));
            throw _0;
        }
        try {
            return new EnumSerial(_node);
//        } catch (NoAttributeForSerializable e_) {
//            throw e_;
//        } catch (InexistingValueForEnum e_) {
//            throw e_;
//        } catch (ClassNotFoundException e_) {
//            throw e_;
        } catch (ClassFoundException _0) {
//        } catch (NullPointerException _0) {
//            _0.printStackTrace();
        } catch (RuntimeException _0) {
            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xml, _node, 0, EMPTY_STRING, TAB_WIDTH));
            throw _0;
        } catch (Error _0) {
            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xml, _node, 0, EMPTY_STRING, TAB_WIDTH));
            throw _0;
        }
        return null;
    }

//    private static ComparatorSerial getCmpSerial(Node _node,
//            boolean _isTreeMap, TemplateSerial _composite) {
//        if (!_isTreeMap) {
//            return null;
//        }
//        NamedNodeMap attributes_ = _node.getAttributes();
////        if (attributes_ == null) {
////            return null;
////        }
//        if (attributes_.getNamedItem(TemplateSerial.COMPARATOR) == null) {
//            return null;
//        }
//        if (_composite instanceof MayBeMap) {
////            return ((MayBeMap) _composite).getCmpSerial();
//            return new ComparatorSerial(_node);
//        }
//        return null;
//    }

    public static String toXmlString(Object _object) {
//        if (!SecurityManagerUtil.canUseReflection()) {
//            ByteArrayOutputStream baos_ = new ByteArrayOutputStream();
//            XMLEncoder xmlEncoder_ = new XMLEncoder(baos_);
//            xmlEncoder_.writeObject(_object);
//            xmlEncoder_.close();
//            return baos_.toString();
//        }
        try {
            DOMSource source_ = getSource(_object);
            StringWriter writer_ = new StringWriter();

            boolean omit_ = XmlParser.isOmitDeclaration();
            if (XmlParser.isIndentXmlWhileWriting()) {
                XmlParser.setOmitDeclaration(true);
            }
            XmlParser.getTransformer().transform(source_, new StreamResult(writer_));
            if (XmlParser.isIndentXmlWhileWriting()) {
                XmlParser.setOmitDeclaration(omit_);
            }
            String xml_ = writer_.getBuffer().toString();
            StringBuilder escapedXml_ = new StringBuilder();
            for (char c: xml_.toCharArray()) {
                if (c >= LIMIT_ASCII || c < LIMIT_EMPTY_CHARS) {
                    escapedXml_.append(BEGIN_ESC);
                    escapedXml_.append((int)c);
                    escapedXml_.append(END_ESC);
                } else {
                    escapedXml_.append(c);
                }
            }
            String xmlString_ = escapedXml_.toString();
            if (XmlParser.isIndentXmlWhileWriting()) {
                return XmlParser.indentWithoutTextNode(xmlString_);
            }
//            XmlParser.getTransformer(XmlParser.isIndentXmlWhileWriting()).transform(source_, new StreamResult(writer_));
//            String xml_ = writer_.getBuffer().toString();
//            StringBuilder escapedXml_ = new StringBuilder();
//            for (char c: xml_.toCharArray()) {
//                if (c >= LIMIT_ASCII || c < LIMIT_EMPTY_CHARS) {
//                    escapedXml_.append(BEGIN_ESC);
//                    escapedXml_.append((int)c);
//                    escapedXml_.append(END_ESC);
//                } else {
//                    escapedXml_.append(c);
//                }
//            }
            return xmlString_;
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            return EMPTY_STRING;
        } catch (Error _0) {
            _0.printStackTrace();
            return EMPTY_STRING;
        } catch (TransformerException _0) {
            _0.printStackTrace();
            return EMPTY_STRING;
        }
    }

    private static DOMSource getSource(Object _serialisable) {
//        Document document_ = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Document document_ = XmlParser.newXmlDocument();
        try {
            ElementsSerial primitive_;
            primitive_ = CurrentSerializableElements.createPrimitive(_serialisable);
            if (primitive_ != null) {
                Element elt_ = primitive_.serializeWithoutRef(document_);
                document_.appendChild(elt_);
                return new DOMSource(document_);
            }
        } catch (RuntimeException _0) {
        }
        String mainObjectClassName_ = _serialisable.getClass().getName();
        Element firstNode_;
        int indexDollar_ = mainObjectClassName_.indexOf(ElementsSerial.SEP_INTERN);
        if (indexDollar_ != CustList.INDEX_NOT_FOUND_ELT) {
            firstNode_ = document_.createElement(mainObjectClassName_.substring(CustList.FIRST_INDEX, indexDollar_));
            firstNode_.setAttribute(ElementsSerial.INTERN, mainObjectClassName_.substring(indexDollar_));
        } else {
            firstNode_ = document_.createElement(mainObjectClassName_);
        }
        document_.appendChild(firstNode_);
        CustList<Node> currentNodesToBeCompleted_ = new CustList<Node>();
        currentNodesToBeCompleted_.add(firstNode_);
        ObjectSerial base_ = new ObjectSerial(_serialisable);
        CurrentSerializableElements currentThread_ = new CurrentSerializableElements(base_);
        if (_references_) {
            currentThread_.initializeObjects();
            CustList<TemplateSerial> currentSerializableElements_ = new CustList<TemplateSerial>();
            currentSerializableElements_.add(base_);
            CustList<Node> newNodesToBeCompleted_ = new CustList<Node>();
            CustList<TemplateSerial> newSerializableElements_ = new CustList<TemplateSerial>();
            boolean modif_ = true;
            while (modif_) {
                modif_ = false;
                newSerializableElements_ = new CustList<TemplateSerial>();
                newNodesToBeCompleted_ = new CustList<Node>();
                int len_;
                len_ = currentNodesToBeCompleted_.size();
                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    Node currentNode_ = currentNodesToBeCompleted_.get(i);
                    TemplateSerial currentSerializable_ = currentSerializableElements_.get(i);
                    CustList<ElementsSerial> elts_ = currentThread_.getComponentComposite(currentSerializable_);
                    for (ElementsSerial e : elts_) {
                        Node newNode_ = e.serialize(document_);
                        if (e instanceof TemplateSerial) {
                            //!(e instanceof EnumSerial)
                            TemplateSerial t_ = (TemplateSerial) e;
                            if (t_.getRef() == null) {
                                newNodesToBeCompleted_.add(newNode_);
                                newSerializableElements_.add(t_);
                            }
                        }
                        currentNode_.appendChild(newNode_);
                    }
                }
                if (!newSerializableElements_.isEmpty()) {
                    currentNodesToBeCompleted_ = new CustList<Node>(newNodesToBeCompleted_);
                    currentSerializableElements_ = new CustList<TemplateSerial>(newSerializableElements_);
                    modif_ = true;
                }
            }
        } else {
            currentThread_.initializeObjectsWithoutIdRef();
            CustList<TemplateSerial> currentSerializableElements_ = new CustList<TemplateSerial>();
            currentSerializableElements_.add(base_);
            CustList<Node> newNodesToBeCompleted_ = new CustList<Node>();
            CustList<TemplateSerial> newSerializableElements_ = new CustList<TemplateSerial>();
            boolean modif_ = true;
            while (modif_) {
                modif_ = false;
                newSerializableElements_ = new CustList<TemplateSerial>();
                newNodesToBeCompleted_ = new CustList<Node>();
                int len_;
                len_ = currentNodesToBeCompleted_.size();
                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    Node currentNode_ = currentNodesToBeCompleted_.get(i);
                    TemplateSerial currentSerializable_ = currentSerializableElements_.get(i);
                    CustList<ElementsSerial> elts_ = currentThread_.getComponentComposite(currentSerializable_);
                    for (ElementsSerial e : elts_) {
                        Node newNode_ = e.serializeWithoutRef(document_);
                        if (e instanceof TemplateSerial) {
                            //!(e instanceof EnumSerial)
                            TemplateSerial t_ = (TemplateSerial) e;
//                            if (t_.getRef() == null) {
//                                newNodesToBeCompleted_.add(newNode_);
//                                newSerializableElements_.add(t_);
//                            }
                            newNodesToBeCompleted_.add(newNode_);
                            newSerializableElements_.add(t_);
                        }
                        currentNode_.appendChild(newNode_);
                    }
                }
                if (!newSerializableElements_.isEmpty()) {
                    currentNodesToBeCompleted_ = new CustList<Node>(newNodesToBeCompleted_);
                    currentSerializableElements_ = new CustList<TemplateSerial>(newSerializableElements_);
                    modif_ = true;
                }
            }
        }
        return new DOMSource(document_);
    }

    public static void checkNullPointers(Object _object) {
        ObjectSerial base_ = new ObjectSerial(_object);
        CurrentSerializableElements currentThread_ = new CurrentSerializableElements(base_);
        currentThread_.checkNullPointersWithoutIdRef();
    }

    public static void setUnmodified(Viewable _object) {
        ObjectSerial base_ = new ObjectSerial(_object);
        CurrentSerializableElements currentThread_ = new CurrentSerializableElements(base_);
        currentThread_.setUnmodifiedWithoutIdRef();
    }

    public static boolean isModified(Viewable _object) {
        ObjectSerial base_ = new ObjectSerial(_object);
        CurrentSerializableElements currentThread_ = new CurrentSerializableElements(base_);
        return currentThread_.isModifiedWithoutIdRef();
    }

//    public static boolean isUpdateFinalFields() {
//        return _updateFinalFields_;
//    }

//    public static void setUpdateFinalFields(boolean _updateFinalFields) {
//        _updateFinalFields_ = _updateFinalFields;
//    }

    public static boolean isReferences() {
        return _references_;
    }

    public static void setReferences(boolean _references) {
        _references_ = _references;
    }

    public static boolean isCopying() {
        return _copying_;
    }

    public static void setCopying(boolean _copying) {
        _copying_ = _copying;
    }

    public static boolean isCheckReferences() {
        return _checkReferences_;
    }

    public static void setCheckReferences(boolean _checkReferences) {
        _checkReferences_ = _checkReferences;
    }
}
