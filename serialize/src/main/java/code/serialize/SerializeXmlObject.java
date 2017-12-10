package code.serialize;
import java.lang.reflect.Method;

import code.serialize.exceptions.BadAccessException;
import code.serialize.exceptions.ClassFoundException;
import code.serialize.exceptions.DuplicatesKeysException;
import code.serialize.exceptions.InexistingValueForEnum;
import code.serialize.exceptions.InvokingException;
import code.serialize.exceptions.NoAttributeForSerializable;
import code.serialize.exceptions.NoSuchDeclaredMethodException;
import code.serialize.exceptions.NoValueException;
import code.serialize.exceptions.RefException;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.exceptions.RuntimeClassNotFoundException;
import code.util.ints.ChangeableMap;
import code.util.ints.Viewable;
import code.xml.XmlParser;
import code.xml.components.Document;
import code.xml.components.Element;
import code.xml.components.Node;
import code.xml.components.NodeList;

public final class SerializeXmlObject {

    public static final String RESOURCES_FOLDER = "resources_stream/classes";

    static final String LS_CLASS = "a";
    static final String MP_CLASS = "m";

    private static final String SEPARATEUR = "/";
    private static final int TAB_WIDTH = 4;

    private static final String EMPTY_STRING = "";
    private static final String ALL = "*";

    private static final String BEGIN_ESC = "&#";

    private static final String END_ESC = ";";

    private static final short LIMIT_EMPTY_CHARS = 32;

    private static final short LIMIT_ASCII = 128;

    private static boolean _references_;
    private static boolean _copying_;
    private static boolean _checkReferences_;

    private SerializeXmlObject() {
    }

    /**@throws NoSuchDeclaredMethodException*/
    public static Method getMethod(Class<?> _class, String _name, Class<?>... _argsClass) {
        try {
            Method method_ = _class.getMethod(_name, _argsClass);
            return method_;
        } catch (NoSuchMethodException _0) {
            //Normally no error because parameters are known when this method is used.
            return null;
        }
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
                    if (StringList.quickEq(((Element) node_).getTagName(),f)) {
                        doc_.renameNode(node_,_classes.getVal(f));
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
                    if (StringList.quickEq(((Element) node_).getTagName(),f)) {
                        doc_.renameNode(node_,  _classes.getVal(f));
                    }
                }
            }
            return XmlParser.toXml(doc_.getDocumentElement());
        } catch (RuntimeException _0) {
            return EMPTY_STRING;
        }
    }

    public static Object newObjectFromXmlString(String _xmlString) {
        try {
            return fromXmlStringObject(_xmlString);
        } catch (Throwable _0) {
            return null;
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
        CustList<Element> currentNodesToBeRead_ = new CustList<Element>();
        currentNodesToBeRead_.add(root_);
        CustList<TemplateSerial> currentSerializableElements_ = new CustList<TemplateSerial>();
        ObjectSerial rootElement_;
        CustList<ObjectSerial> cmp_;
        cmp_ = new CustList<ObjectSerial>();
        CustList<ObjectSerial> notEmptyMaps_ = new CustList<ObjectSerial>();
        if (_references_) {
            rootElement_ = ObjectSerial.newSerialWithId(root_, null, false);
            currentSerializableElements_.add(rootElement_);
            CustList<Element> newNodesToBeRead_ = new CustList<Element>();
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
                newNodesToBeRead_ = new CustList<Element>();
                int len_;
                len_ = currentNodesToBeRead_.size();
                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    Element currentNode_ = currentNodesToBeRead_.get(i);
                    TemplateSerial composite_ = currentSerializableElements_.get(i);
                    CustList<ElementsSerial> elt_ = new CustList<ElementsSerial>();
                    CustList<TemplateSerial> childRefComposites_ = new CustList<TemplateSerial>();
                    CustList<TemplateSerial> childIdComposites_ = new CustList<TemplateSerial>();
                    for (Element n : XmlParser.childrenElements(currentNode_)) {
                        try {
                            ArraySerial serial_ = new ArraySerial(n, composite_);
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
                        ObjectSerial serial_;
                        try {
                            serial_ = ObjectSerial.newSerialWithId(n, composite_, true);
                        } catch (RuntimeException _0) {
                            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xmlString, n, 0,EMPTY_STRING, TAB_WIDTH));
                            throw _0;
                        } catch (Error _0) {
                            System.err.println(XmlParser.getRowColOfNodeOrAttribute(_xmlString, n, 0,EMPTY_STRING, TAB_WIDTH));
                            throw _0;
                        }
                        elt_.add(serial_);
                        if (serial_.getRef() == null) {
                            if (serial_.isMap()) {
                                notEmptyMaps_.add(serial_);
                            }
                            if (ChangeableMap.class.isInstance(serial_.getValue())) {
                                cmp_.add(serial_);
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
                    treeRefComposites_.getElements().add(new TemplateSerialValue<CustList<TemplateSerial>>(composite_, childRefComposites_));
                    treeIdComposites_.getElements().add(new TemplateSerialValue<CustList<TemplateSerial>>(composite_, childIdComposites_));
                    composite_.appendElementSerial(_xmlString, elt_);
                }
                if (!newSerializableElements_.isEmpty()) {
                    currentNodesToBeRead_ = new CustList<Element>(newNodesToBeRead_);
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
                    throw new RefException();
                }
                TemplateSerial parent_ = e.getParent();
                TemplateSerial newId_ = lsTwo_.first();
                parent_.setElementSerial(_xmlString, e, newId_);
            }
            for (ObjectSerial c: cmp_.getReverse()) {
                Object o_ = c.getValue();
                ((ChangeableMap)o_).applyChanges();
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
            CustList<Element> newNodesToBeRead_ = new CustList<Element>();
            CustList<TemplateSerial> serializableComposite_ = new CustList<TemplateSerial>();
            CustList<TemplateSerial> newSerializableElements_ = new CustList<TemplateSerial>();
            boolean modif_ = true;
            while (modif_) {
                modif_ = false;
                newSerializableElements_ = new CustList<TemplateSerial>();
                newNodesToBeRead_ = new CustList<Element>();
                int len_;
                len_ = currentNodesToBeRead_.size();
                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    Element currentNode_ = currentNodesToBeRead_.get(i);
                    TemplateSerial composite_ = currentSerializableElements_.get(i);
                    CustList<ElementsSerial> elt_ = new CustList<ElementsSerial>();
                    for (Element n : XmlParser.childrenElements(currentNode_)) {
                        try {
                            ArraySerial serial_ = ArraySerial.newListSerial(n);
                            elt_.add(serial_);
                            newSerializableElements_.add(serial_);
                            newNodesToBeRead_.add(n);
                            continue;
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
                        if (ChangeableMap.class.isInstance(serial_.getValue())) {
                            cmp_.add(serial_);
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
                }
                if (!newSerializableElements_.isEmpty()) {
                    currentNodesToBeRead_ = new CustList<Element>(newNodesToBeRead_);
                    currentSerializableElements_ = new CustList<TemplateSerial>(newSerializableElements_);
                    modif_ = true;
                }
            }
            for (ObjectSerial c: cmp_.getReverse()) {
                Object o_ = c.getValue();
                ((ChangeableMap)o_).applyChanges();
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

    public static String toXmlString(Object _object) {
//        if (!SecurityManagerUtil.canUseReflection()) {
//            ByteArrayOutputStream baos_ = new ByteArrayOutputStream();
//            XMLEncoder xmlEncoder_ = new XMLEncoder(baos_);
//            xmlEncoder_.writeObject(_object);
//            xmlEncoder_.close();
//            return baos_.toString();
//        }
        try {
            Document source_ = getSource(_object);

            String xml_ = source_.export();
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
        }
    }

    private static Document getSource(Object _serialisable) {
//        Document document_ = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Document document_ = XmlParser.newXmlDocument();
        try {
            ElementsSerial primitive_;
            primitive_ = CurrentSerializableElements.createPrimitive(_serialisable);
            if (primitive_ != null) {
                Element elt_ = primitive_.serializeWithoutRef(document_);
                document_.appendChild(elt_);
                return document_;
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
        CustList<Element> currentNodesToBeCompleted_ = new CustList<Element>();
        currentNodesToBeCompleted_.add(firstNode_);
        ObjectSerial base_ = new ObjectSerial(_serialisable);
        CurrentSerializableElements currentThread_ = new CurrentSerializableElements(base_);
        if (_references_) {
            currentThread_.initializeObjects();
            CustList<TemplateSerial> currentSerializableElements_ = new CustList<TemplateSerial>();
            currentSerializableElements_.add(base_);
            CustList<Element> newNodesToBeCompleted_ = new CustList<Element>();
            CustList<TemplateSerial> newSerializableElements_ = new CustList<TemplateSerial>();
            boolean modif_ = true;
            while (modif_) {
                modif_ = false;
                newSerializableElements_ = new CustList<TemplateSerial>();
                newNodesToBeCompleted_ = new CustList<Element>();
                int len_;
                len_ = currentNodesToBeCompleted_.size();
                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    Element currentNode_ = currentNodesToBeCompleted_.get(i);
                    TemplateSerial currentSerializable_ = currentSerializableElements_.get(i);
                    CustList<ElementsSerial> elts_ = currentThread_.getComponentComposite(currentSerializable_);
                    for (ElementsSerial e : elts_) {
                        Element newNode_ = e.serialize(document_);
                        if (e instanceof TemplateSerial) {
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
                    currentNodesToBeCompleted_ = new CustList<Element>(newNodesToBeCompleted_);
                    currentSerializableElements_ = new CustList<TemplateSerial>(newSerializableElements_);
                    modif_ = true;
                }
            }
        } else {
            currentThread_.initializeObjectsWithoutIdRef();
            CustList<TemplateSerial> currentSerializableElements_ = new CustList<TemplateSerial>();
            currentSerializableElements_.add(base_);
            CustList<Element> newNodesToBeCompleted_ = new CustList<Element>();
            CustList<TemplateSerial> newSerializableElements_ = new CustList<TemplateSerial>();
            boolean modif_ = true;
            while (modif_) {
                modif_ = false;
                newSerializableElements_ = new CustList<TemplateSerial>();
                newNodesToBeCompleted_ = new CustList<Element>();
                int len_;
                len_ = currentNodesToBeCompleted_.size();
                for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                    Element currentNode_ = currentNodesToBeCompleted_.get(i);
                    TemplateSerial currentSerializable_ = currentSerializableElements_.get(i);
                    CustList<ElementsSerial> elts_ = currentThread_.getComponentComposite(currentSerializable_);
                    for (ElementsSerial e : elts_) {
                        Element newNode_ = e.serializeWithoutRef(document_);
                        if (e instanceof TemplateSerial) {
                            TemplateSerial t_ = (TemplateSerial) e;
                            newNodesToBeCompleted_.add(newNode_);
                            newSerializableElements_.add(t_);
                        }
                        currentNode_.appendChild(newNode_);
                    }
                }
                if (!newSerializableElements_.isEmpty()) {
                    currentNodesToBeCompleted_ = new CustList<Element>(newNodesToBeCompleted_);
                    currentSerializableElements_ = new CustList<TemplateSerial>(newSerializableElements_);
                    modif_ = true;
                }
            }
        }
        return document_;
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
