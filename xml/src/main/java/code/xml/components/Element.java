package code.xml.components;

import code.util.CustList;
import code.util.StringList;

public final class Element extends ChangeableChild {

    private static final String BEGIN_TAG = "<";

    private static final String END_LEAF = "/>";

    private static final String BEGIN_FOOTER = "</";

    private static final String END_TAG = ">";

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
    public void appendChild(ChangeableChild _newChild) {
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
    public void removeChild(ChangeableChild _oldChild) {
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
    public void replaceChild(ChangeableChild _newChild, ChangeableChild _oldChild) {
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
    public void insertBefore(ChangeableChild _newChild, ChangeableChild _refChild) {
        ChangeableChild child_ = getFirstChild();
        while (child_ != null) {
            if (child_ == _refChild) {
                ChangeableChild previous_ = _refChild.getPreviousSibling();
                _newChild.setParentNode(this);
                if (previous_ == null) {
                    setFirstChild(_newChild);
                    _newChild.setNextSibling(_refChild);
                    _refChild.setPreviousSibling(_newChild);
                    return;
                }
                _refChild.setPreviousSibling(_newChild);
                previous_.setNextSibling(_newChild);
                _newChild.setNextSibling(_refChild);
                _newChild.setPreviousSibling(previous_);
                return;
            }
            child_ = child_.getNextSibling();
        }
    }

    public void insertAfter(ChangeableChild _newChild, ChangeableChild _refChild) {
        ChangeableChild child_ = getFirstChild();
        while (child_ != null) {
            if (child_ == _refChild) {
                ChangeableChild next_ = _refChild.getNextSibling();
                _newChild.setParentNode(this);
                if (next_ == null) {
                    setLastChild(_newChild);
                    _newChild.setPreviousSibling(_refChild);
                    _refChild.setNextSibling(_newChild);
                    return;
                }
                _refChild.setNextSibling(_newChild);
                next_.setPreviousSibling(_newChild);
                _newChild.setPreviousSibling(_refChild);
                _newChild.setNextSibling(next_);
                return;
            }
            child_ = child_.getNextSibling();
        }
    }

    public String export() {
        Element root_ = this;
        ChangeableChild current_ = getFirstChild();
        StringBuilder str_ = new StringBuilder();
        str_.append(BEGIN_TAG+getTagName());
        if (!attributes.isEmpty()) {
            for (Attr a: attributes) {
                str_.append(a.export());
            }
        }
        if (current_ == null) {
            str_.append(END_LEAF);
        } else {
            str_.append(END_TAG);
        }
        while (true) {
            if (current_ == null) {
                break;
            }
            if (current_ instanceof Element) {
                Element elt_ = (Element) current_;
                str_.append(BEGIN_TAG+elt_.getTagName());
                if (!elt_.attributes.isEmpty()) {
                    for (Attr a: elt_.attributes) {
                        str_.append(a.export());
                    }
                }
            }
            if (current_ instanceof Text) {
                Text txt_ = (Text) current_;
                str_.append(DocumentBuilder.escape(txt_.getData(), false));
            }
            ChangeableChild next_ = current_.getFirstChild();
            if (next_ != null) {
                str_.append(END_TAG);
                current_ = next_;
                continue;
            }
            if (current_ instanceof Element) {
                str_.append(END_LEAF);
            }
            next_ = current_.getNextSibling();
            if (next_ != null) {
                current_ = next_;
                continue;
            }
            Element parent_ = current_.getParentNode();
            if (parent_ == null) {
                current_ = null;
                continue;
            }
            str_.append(BEGIN_FOOTER+parent_.getTagName()+END_TAG);
            if (parent_ == root_) {
                current_ = null;
                continue;
            }
            next_ = parent_.getNextSibling();
            while (next_ == null) {
                Element par_ = parent_.getParentNode();
                if (par_ == null) {
                    break;
                }
                str_.append(BEGIN_FOOTER+par_.getTagName()+END_TAG);
                if (par_ == root_) {
                    break;
                }
                next_ = par_.getNextSibling();
                parent_ = par_;
            }
            current_ = next_;
        }
        return str_.toString();
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
