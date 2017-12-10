package code.xml.components;

import code.util.CustList;
import code.util.StringList;

public final class Element extends Node {

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

    public boolean hasAttribute(String _name) {
        for (Attr a: attributes) {
            if (StringList.quickEq(a.getName(), _name)) {
                return true;
            }
        }
        return false;
    }

    public void removeAttribute(String _name) {
        int index_ = CustList.INDEX_NOT_FOUND_ELT;
        boolean found_ = false;
        for (Attr a: attributes) {
            index_++;
            if (StringList.quickEq(a.getName(), _name)) {
                found_ = true;
                break;
            }
        }
        if (!found_) {
            return;
        }
        attributes.remove(index_);
    }
    public void removeAttributeNode(Attr _oldAttr) {
        int index_ = CustList.INDEX_NOT_FOUND_ELT;
        boolean found_ = false;
        for (Attr a: attributes) {
            index_++;
            if (StringList.quickEq(a.getName(), _oldAttr.getName())) {
                found_ = true;
                break;
            }
        }
        if (!found_) {
            return;
        }
        attributes.remove(index_);
    }
    public void setAttribute(String _name, String _value) {
        for (Attr a: attributes) {
            if (StringList.quickEq(a.getName(), _name)) {
                a.setValue(_value);
                return;
            }
        }
        Attr attr_ = getOwnerDocument().createAttribute(_name);
        attr_.setValue(_value);
        attributes.add(attr_);
    }
    protected void setEscapedAttribute(String _name, String _value) {
        for (Attr a: attributes) {
            if (StringList.quickEq(a.getName(), _name)) {
                a.setEscapedValue(_value);
                return;
            }
        }
        Attr attr_ = getOwnerDocument().createAttribute(_name);
        attr_.setEscapedValue(_value);
        attributes.add(attr_);
    }
    public void setAttributeNode(Attr _newAttr) {
        for (Attr a: attributes) {
            if (StringList.quickEq(a.getName(), _newAttr.getName())) {
                a.setValue(_newAttr.getValue());
                return;
            }
        }
        Attr attr_ = getOwnerDocument().createAttribute(_newAttr.getName());
        attr_.setValue(_newAttr.getValue());
        attributes.add(attr_);
    }

    @Override
    public NamedNodeMap getAttributes() {
        return attributes;
    }

    protected void setAttributes(NamedNodeMap _attributes) {
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
    public void appendChild(Node _newChild) {
        _newChild.setParentNode(this);
        if (getFirstChild() == null) {
            setFirstChild(_newChild);
            setLastChild(_newChild);
            return;
        }
        getLastChild().setNextSibling(_newChild);
        _newChild.setPreviousSibling(getLastChild());
        setLastChild(_newChild);
    }

    @Override
    public void removeChild(Node _oldChild) {
        Node child_ = getFirstChild();
        while (child_ != null) {
            if (child_ == _oldChild) {
                Node previous_ = child_.getPreviousSibling();
                Node next_ = child_.getNextSibling();
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
        Node child_ = getFirstChild();
        while (child_ != null) {
            if (child_ == _oldChild) {
                Node previous_ = child_.getPreviousSibling();
                Node next_ = child_.getNextSibling();
                _oldChild.setParentNode(null);
                _newChild.setParentNode(this);
                if (previous_ == null && next_ == null) {
                    setFirstChild(_newChild);
                    setLastChild(_newChild);
                    return;
                }
                if (next_ == null) {
                    //previous_ != null
                    previous_.setNextSibling(_newChild);
                    _newChild.setPreviousSibling(previous_);
                    setLastChild(_newChild);
                    return;
                }
                if (previous_ == null) {
                    //next_ != null
                    next_.setPreviousSibling(_newChild);
                    _newChild.setNextSibling(next_);
                    setFirstChild(_newChild);
                    return;
                }
                //previous_ != null && next_ != null
                previous_.setNextSibling(_newChild);
                next_.setPreviousSibling(_newChild);
                _newChild.setNextSibling(next_);
                _newChild.setPreviousSibling(previous_);
                return;
            }
            child_ = child_.getNextSibling();
        }
    }

    @Override
    public void insertBefore(Node _newChild, Node _refChild) {
        Node child_ = getFirstChild();
        while (child_ != null) {
            if (child_ == _refChild) {
                Node previous_ = _refChild.getPreviousSibling();
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

    @Override
    public void insertAfter(Node _newChild, Node _refChild) {
        Node child_ = getFirstChild();
        while (child_ != null) {
            if (child_ == _refChild) {
                Node next_ = _refChild.getNextSibling();
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

    public String openTag() {
        Element root_ = this;
        Node current_ = getFirstChild();
        StringBuilder str_ = new StringBuilder();
        str_.append(BEGIN_TAG+getTagName());
        if (!attributes.isEmpty()) {
            for (Attr a: attributes) {
                str_.append(a.export());
            }
        }
        if (current_ == null) {
            str_.append(END_TAG);
            str_.append(BEGIN_FOOTER);
            str_.append(getTagName());
            str_.append(END_TAG);
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
            Node next_ = current_.getFirstChild();
            if (next_ != null) {
                str_.append(END_TAG);
                current_ = next_;
                continue;
            }
            if (current_ instanceof Element) {
                str_.append(END_TAG);
                str_.append(BEGIN_FOOTER);
                str_.append(((Element) current_).getTagName());
                str_.append(END_TAG);
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
    public String export() {
        Element root_ = this;
        Node current_ = getFirstChild();
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
            Node next_ = current_.getFirstChild();
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
    public boolean hasAttributes() {
        return !attributes.isEmpty();
    }

    @Override
    public long compareDocumentPosition(Info _other) {
        // TODO Auto-generated method stub
        return 0;
    }

    public NodeList getElementsByTagName() {
        NodeList elements_ = new NodeList();
        Element root_ = this;
        elements_.add(root_);
        Node current_ = getFirstChild();
        while (true) {
            if (current_ == null) {
                break;
            }
            if (current_ instanceof Element) {
                Element elt_ = (Element) current_;
                elements_.add(elt_);
            }
            Node next_ = current_.getFirstChild();
            if (next_ != null) {
                current_ = next_;
                continue;
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
                if (par_ == root_) {
                    break;
                }
                next_ = par_.getNextSibling();
                parent_ = par_;
            }
            current_ = next_;
        }
        return elements_;
    }

    public NodeList getElementsByTagName(String _tagName) {
        NodeList elements_ = new NodeList();
        Element root_ = this;
        if (StringList.quickEq(getTagName(), _tagName)) {
            elements_.add(root_);
        }
        Node current_ = getFirstChild();
        while (true) {
            if (current_ == null) {
                break;
            }
            if (current_ instanceof Element) {
                Element elt_ = (Element) current_;
                if (StringList.quickEq(elt_.getTagName(), _tagName)) {
                    elements_.add(elt_);
                }
            }
            Node next_ = current_.getFirstChild();
            if (next_ != null) {
                current_ = next_;
                continue;
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
                if (par_ == root_) {
                    break;
                }
                next_ = par_.getNextSibling();
                parent_ = par_;
            }
            current_ = next_;
        }
        return elements_;
    }
    @Override
    public String getTextContent() {
        Element root_ = this;
        Node current_ = getFirstChild();
        StringBuilder str_ = new StringBuilder();
        while (true) {
            if (current_ == null) {
                break;
            }
            if (current_ instanceof Text) {
                Text txt_ = (Text) current_;
                str_.append(txt_.getTextContent());
            }
            Node next_ = current_.getFirstChild();
            if (next_ != null) {
                current_ = next_;
                continue;
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
    public boolean isEqualNode(Node _arg) {
        // TODO Auto-generated method stub
        return false;
    }
}
