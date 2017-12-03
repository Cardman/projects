package code.xml.components;

import code.util.CustList;
import code.util.StringList;

public final class Element extends ChangeableChild {

    private static final String EMPTY_STRING = "";

    private String tagName;

    private NamedNodeMap attributes = new NamedNodeMap();

    protected Element(Document _ownerDocument) {
        super(_ownerDocument);
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String _tagName) {
        tagName = _tagName;
    }

    public String getAttribute(String _name) {
        for (Attr a: attributes) {
            if (StringList.quickEq(a.getName(), _name)) {
                return a.getValue();
            }
        }
        return EMPTY_STRING;
    }

    public Attr getAttributeNode(String _name) {
        for (Attr a: attributes) {
            if (StringList.quickEq(a.getName(), _name)) {
                return a;
            }
        }
        return null;
    }
    
    public Attr getAttributeNodeNS(String _namespace, String _localName) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getAttributeNS(String _namespace, String _localName) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean hasAttribute(String _name) {
        for (Attr a: attributes) {
            if (StringList.quickEq(a.getName(), _name)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAttributeNS(String _namespace, String localName) {
        // TODO Auto-generated method stub
        return false;
    }

    public void removeAttribute(String _name) {
        int index_ = CustList.INDEX_NOT_FOUND_ELT;
        for (Attr a: attributes) {
            if (StringList.quickEq(a.getName(), _name)) {
                index_ = a.getIndex();
                break;
            }
        }
        if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
            return;
        }
        attributes.remove(index_);
        int len_ = attributes.size();
        for (int i = index_; i < len_; i++) {
            attributes.get(i).setIndex(i);
        }
    }
    public void removeAttributeNode(Attr _oldAttr) {
        int index_ = CustList.INDEX_NOT_FOUND_ELT;
        for (Attr a: attributes) {
            if (StringList.quickEq(a.getName(), _oldAttr.getName())) {
                index_ = a.getIndex();
                break;
            }
        }
        if (index_ == CustList.INDEX_NOT_FOUND_ELT) {
            return;
        }
        attributes.remove(index_);
        int len_ = attributes.size();
        for (int i = index_; i < len_; i++) {
            attributes.get(i).setIndex(i);
        }
    }
    public void removeAttributeNS(String _namespace, String localName) {
        
    }
    public void setAttribute(String _name, String _value) {
        for (Attr a: attributes) {
            if (StringList.quickEq(a.getName(), _name)) {
                a.setValue(_value);
                return;
            }
        }
        int index_ = attributes.size();
        Attr attr_ = getOwnerDocument().createAttribute(_name);
        attr_.setIndex(index_);
        attr_.setValue(_value);
    }
    protected void setEscapedAttribute(String _name, String _value) {
        for (Attr a: attributes) {
            if (StringList.quickEq(a.getName(), _name)) {
                a.setEscapedValue(_value);
                return;
            }
        }
        int index_ = attributes.size();
        Attr attr_ = getOwnerDocument().createAttribute(_name);
        attr_.setIndex(index_);
        attr_.setEscapedValue(_value);
    }
    public void setAttributeNode(Attr _newAttr) {
        for (Attr a: attributes) {
            if (StringList.quickEq(a.getName(), _newAttr.getName())) {
                a.setValue(_newAttr.getValue());
                return;
            }
        }
        int index_ = attributes.size();
        Attr attr_ = getOwnerDocument().createAttribute(_newAttr.getName());
        attr_.setIndex(index_);
        attr_.setValue(_newAttr.getValue());
    }
    public void setAttributeNodeNS(Attr newAttr) {
        
    }
    public void setAttributeNS(String _namespace, String qualifiedName, String value) {
        
    }

    @Override
    public NamedNodeMap getAttributes() {
        return attributes;
    }

    public void setAttributes(NamedNodeMap _attributes) {
        attributes = _attributes;
    }

    @Override
    public NodeList getChildNodes() {
        NodeList children_ = new NodeList();
        Node child_ = getFirstChild();
        while (child_ != null) {
            children_.add(child_);
            child_ = child_.getNextSibling();
        }
        return children_;
    }

    @Override
    public String getNodeName() {
        return getTagName();
    }

    @Override
    public String getNodeValue() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void appendChild(Node _newChild) {
        _newChild.setParentNode(this);
        if (getFirstChild() == null) {
            setFirstChild((ChangeableChild) _newChild);
            setLastChild((ChangeableChild) _newChild);
            return;
        }
        getLastChild().setNextSibling((ChangeableChild) _newChild);
        ((ChangeableChild) _newChild).setPreviousSibling(getLastChild());
        setLastChild((ChangeableChild) _newChild);
    }

    @Override
    public void removeChild(Node _oldChild) {
        ChangeableChild child_ = getFirstChild();
        while (child_ != null) {
            if (child_ == _oldChild) {
                ChangeableChild previous_ = child_.getPreviousSibling();
                ChangeableChild next_ = child_.getNextSibling();
                _oldChild.setParentNode(null);
                if (previous_ == null && next_ == null) {
                    setFirstChild(null);
                    setLastChild(null);
                    return;
                }
                if (next_ == null) {
                    //previous_ != null
                    previous_.setNextSibling(null);
                    setLastChild(previous_);
                    return;
                }
                if (previous_ == null) {
                    //next_ != null
                    next_.setPreviousSibling(null);
                    setFirstChild(next_);
                    return;
                }
                //previous_ != null && next_ != null
                previous_.setNextSibling(next_);
                next_.setPreviousSibling(previous_);
                return;
            }
            child_ = child_.getNextSibling();
        }
    }

    @Override
    public void replaceChild(Node _newChild, Node _oldChild) {
        ChangeableChild child_ = getFirstChild();
        while (child_ != null) {
            if (child_ == _oldChild) {
                ChangeableChild previous_ = child_.getPreviousSibling();
                ChangeableChild next_ = child_.getNextSibling();
                _oldChild.setParentNode(null);
                _newChild.setParentNode(this);
                if (previous_ == null && next_ == null) {
                    setFirstChild((ChangeableChild) _newChild);
                    setLastChild((ChangeableChild) _newChild);
                    return;
                }
                if (next_ == null) {
                    //previous_ != null
                    previous_.setNextSibling((ChangeableChild) _newChild);
                    ((ChangeableChild) _newChild).setPreviousSibling(previous_);
                    setLastChild((ChangeableChild) _newChild);
                    return;
                }
                if (previous_ == null) {
                    //next_ != null
                    next_.setPreviousSibling((ChangeableChild) _newChild);
                    ((ChangeableChild)_newChild).setNextSibling(next_);
                    setFirstChild((ChangeableChild) _newChild);
                    return;
                }
                //previous_ != null && next_ != null
                previous_.setNextSibling((ChangeableChild) _newChild);
                next_.setPreviousSibling((ChangeableChild) _newChild);
                ((ChangeableChild) _newChild).setNextSibling(next_);
                ((ChangeableChild) _newChild).setPreviousSibling(previous_);
                return;
            }
            child_ = child_.getNextSibling();
        }
    }

    @Override
    public void insertBefore(Node _newChild, Node _refChild) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean hasChildNodes() {
        return getFirstChild() != null;
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
        return !attributes.isEmpty();
    }

    @Override
    public long compareDocumentPosition(Node _other) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getTextContent() {
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub
        return false;
    }
}
