package code.xml.components;

import code.util.CustList;
import code.util.StringList;

public final class Attr extends Node {

    private String name;

    //set for example by setting from the owner element
    private String value;

    //value parsed from string 
    private String escapedValue;

    private int index = CustList.INDEX_NOT_FOUND_ELT;

    protected Attr(Document _ownerDocument) {
        super(_ownerDocument);
    }

    protected int getIndex() {
        return index;
    }

    protected void setIndex(int _index) {
        index = _index;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String _value) {
        value = _value;
        escapedValue = DocumentBuilder.escape(_value, true);
    }

    public String getEscapedValue() {
        return escapedValue;
    }

    protected void setEscapedValue(String _escapedValue) {
        escapedValue = _escapedValue;
        value = DocumentBuilder.transformSpecialChars(_escapedValue);
    }

    @Override
    public NamedNodeMap getAttributes() {
        return null;
    }

    @Override
    public Node getNextSibling() {
        Node node_ = getParentNode();
        if (!(node_ instanceof Element)) {
            return null;
        }
        Element element_ = (Element) node_;
        NamedNodeMap attrs_ = element_.getAttributes();
        if (index + 1 >= attrs_.size()) {
            return null;
        }
        return attrs_.get(index + 1);
    }

    @Override
    public Node getPreviousSibling() {
        Node node_ = getParentNode();
        if (!(node_ instanceof Element)) {
            return null;
        }
        Element element_ = (Element) node_;
        NamedNodeMap attrs_ = element_.getAttributes();
        if (index == CustList.FIRST_INDEX) {
            return null;
        }
        return attrs_.get(index - 1);
    }

    @Override
    public Node getFirstChild() {
        return null;
    }

    @Override
    public Node getLastChild() {
        return null;
    }

    @Override
    public NodeList getChildNodes() {
        return new NodeList();
    }

    @Override
    public String getNodeName() {
        return getName();
    }

    @Override
    public String getNodeValue() {
        return getValue();
    }

    @Override
    public void appendChild(Node _newChild) {
    }

    @Override
    public void removeChild(Node _oldChild) {
    }

    @Override
    public void replaceChild(Node _newChild, Node _oldChild) {
    }

    @Override
    public void insertBefore(Node _newChild, Node _refChild) {
    }

    @Override
    public boolean hasChildNodes() {
        return false;
    }

    @Override
    public String getNamespace() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPrefix() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setPrefix(String _prefix) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean hasAttributes() {
        return false;
    }

    @Override
    public long compareDocumentPosition(Node _other) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getTextContent() {
        return getValue();
    }

    @Override
    public void setTextContent(String _textContent) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String lookupPrefix(String _namespace) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isDefaultNamespace(String _namespace) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String lookupNamespace(String _prefix) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEqualNode(Node _arg) {
        if (!(_arg instanceof Attr)) {
            return false;
        }
        Attr attr_ = (Attr) _arg;
        if (!StringList.quickEq(attr_.getName(), getName())) {
            return false;
        }
        if (!StringList.quickEq(attr_.getValue(), getValue())) {
            return false;
        }
        return true;
    }
}
