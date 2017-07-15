package code.serialize;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

abstract class ElementsSerial {

    protected static final String EMPTY_STRING = "";
    protected static final String SEPARATOR = ";";
    protected static final String COMMA = ",";
    static final String CLASS = "class";
    static final String FIELD = "field";
    static final String KEY = "key";
    static final String INTERN = "intern";
    static final String SEP_INTERN = "$";
    private String className;

    private String field;

    private boolean keyOfMap;

    private Element node;

    ElementsSerial() {
    }

    ElementsSerial(Element _node) {
        node = _node;
    }

    Element getNode() {
        return node;
    }

    void setNode(Element _node) {
        node = _node;
    }

    Class<?> getValueClass() {
        Object o_ = getValue();
        if (o_ == null) {
            return null;
        }
        return o_.getClass();
    }

    abstract Object getValue();

//    abstract Class<?> getValueClass();

    abstract Element serialize(Document _doc);

    abstract Element serializeWithoutRef(Document _doc);

    static SerialList<TemplateSerial> findSerialisableInList(Object _o, SerialList<TemplateSerial> _allComposites) {
        SerialList<TemplateSerial> list_ = new SerialList<TemplateSerial>();
        for(TemplateSerial e: _allComposites) {
            if(!hasValue(e, _o)) {
                continue;
            }
            list_.add(e);
            break;
        }
        return list_;
    }

    static boolean hasValue(ElementsSerial _eltOne, Object _o) {
        return _eltOne.getValue() == _o;
    }

    static boolean sameValue(ElementsSerial _eltOne, ElementsSerial _eltTwo) {
        return _eltOne.getValue() == _eltTwo.getValue();
    }

    String getClassName() {
        return className;
    }

    void setClassName(String _className) {
        className = _className;
    }

    String getField() {
        return field;
    }

    void setField(String _field) {
        field = _field;
    }

    boolean isKeyOfMap() {
        return keyOfMap;
    }

    void setKeyOfMap(boolean _keyOfMap) {
        keyOfMap = _keyOfMap;
    }

}
