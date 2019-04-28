package code.sml;

import code.util.CustList;
import code.util.NatStringTreeMap;
import code.util.StringList;

public final class FullElement extends FullNode implements Element {

    private static final String BEGIN_TAG = "<";

    private static final String END_LEAF = "/>";

    private static final String BEGIN_FOOTER = "</";

    private static final String END_TAG = ">";

    private static final String EMPTY_STRING = "";

    private String tagName;

    private NamedNodeMap attributes = new NamedNodeMap();

    protected FullElement(Document _ownerDocument) {
        super(_ownerDocument);
    }

    @Override
    public String getTagName() {
        return tagName;
    }

    @Override
    public void setTagName(String _tagName) {
        tagName = _tagName;
    }

    @Override
    public String getAttribute(String _name) {
        for (Attr a: attributes) {
            if (StringList.quickEq(a.getName(), _name)) {
                return a.getValue();
            }
        }
        return EMPTY_STRING;
    }

    @Override
    public boolean hasAttribute(String _name) {
        for (Attr a: attributes) {
            if (StringList.quickEq(a.getName(), _name)) {
                return true;
            }
        }
        return false;
    }

    @Override
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

    @Override
    public void setAttribute(String _name, String _value) {
        for (Attr a: attributes) {
            if (StringList.quickEq(a.getName(), _name)) {
                a.setValue(_value);
                return;
            }
        }
        Attr attr_ = CoreDocument.createAttribute(_name);
        attr_.setValue(_value);
        attributes.add(attr_);
    }

    @Override
    public NamedNodeMap getAttributes() {
        return attributes;
    }

    @Override
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
    public ElementList getChildElements() {
        ElementList children_ = new ElementList();
        Node child_ = getFirstChild();
        while (child_ != null) {
            if (child_ instanceof FullElement) {
                children_.add((FullElement) child_);
            }
            child_ = child_.getNextSibling();
        }
        return children_;
    }

    @Override
    public void appendChild(MutableNode _newChild) {
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
    public void removeChild(MutableNode _oldChild) {
        MutableNode child_ = getFirstChild();
        while (child_ != null) {
            if (child_ == _oldChild) {
                MutableNode previous_ = child_.getPreviousSibling();
                MutableNode next_ = child_.getNextSibling();
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
    public void replaceChild(MutableNode _newChild, MutableNode _oldChild) {
        MutableNode child_ = getFirstChild();
        while (child_ != null) {
            if (child_ == _oldChild) {
                MutableNode previous_ = child_.getPreviousSibling();
                MutableNode next_ = child_.getNextSibling();
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
    public void insertBefore(MutableNode _newChild, MutableNode _refChild) {
        MutableNode child_ = getFirstChild();
        while (child_ != null) {
            if (child_ == _refChild) {
                MutableNode previous_ = _refChild.getPreviousSibling();
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
    public void insertAfter(MutableNode _newChild, MutableNode _refChild) {
        MutableNode child_ = getFirstChild();
        while (child_ != null) {
            if (child_ == _refChild) {
                MutableNode next_ = _refChild.getNextSibling();
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
        FullElement root_ = this;
        Node current_ = this;
        StringBuilder str_ = new StringBuilder();
        while (current_ != null) {
            if (current_ instanceof FullElement) {
                FullElement elt_ = (FullElement) current_;
                str_.append(BEGIN_TAG);
                str_.append(elt_.getTagName());
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
            if (current_ instanceof FullElement) {
                str_.append(END_TAG);
                str_.append(BEGIN_FOOTER);
                str_.append(((FullElement) current_).getTagName());
                str_.append(END_TAG);
            }
            while (true) {
                next_ = current_.getNextSibling();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                Element parent_ = current_.getParentNode();
                if (parent_ == null) {
                    current_ = null;
                    break;
                }
                str_.append(BEGIN_FOOTER);
                str_.append(parent_.getTagName());
                str_.append(END_TAG);
                if (parent_ == root_) {
                    current_ = null;
                    break;
                }
                current_ = parent_;
            }
        }
        return str_.toString();
    }
    public String exportSorted() {
        FullElement root_ = this;
        Node current_ = this;
        StringBuilder str_ = new StringBuilder();
        while (current_ != null) {
            if (current_ instanceof FullElement) {
                FullElement elt_ = (FullElement) current_;
                str_.append(BEGIN_TAG);
                str_.append(elt_.getTagName());
                if (!elt_.attributes.isEmpty()) {
                    NatStringTreeMap<String> m_ = new NatStringTreeMap<String>();
                    for (Attr a : elt_.attributes) {
                        m_.put(a.getName(),a.export());
                    }
                    for (String a : m_.values()) {
                        str_.append(a);
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
            if (current_ instanceof FullElement) {
                str_.append(END_LEAF);
            }
            while (true) {
                next_ = current_.getNextSibling();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                Element parent_ = current_.getParentNode();
                if (parent_ == null) {
                    current_ = null;
                    break;
                }
                str_.append(BEGIN_FOOTER);
                str_.append(parent_.getTagName());
                str_.append(END_TAG);
                if (parent_ == root_) {
                    current_ = null;
                    break;
                }
                current_ = parent_;
            }
        }
        return str_.toString();
    }
    @Override
    public String export() {
        FullElement root_ = this;
        Node current_ = this;
        StringBuilder str_ = new StringBuilder();
        while (current_ != null) {
            if (current_ instanceof FullElement) {
                FullElement elt_ = (FullElement) current_;
                str_.append(BEGIN_TAG);
                str_.append(elt_.getTagName());
                if (!elt_.attributes.isEmpty()) {
                    for (Attr a : elt_.attributes) {
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
            if (current_ instanceof FullElement) {
                str_.append(END_LEAF);
            }
            while (true) {
                next_ = current_.getNextSibling();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                Element parent_ = current_.getParentNode();
                if (parent_ == null) {
                    current_ = null;
                    break;
                }
                str_.append(BEGIN_FOOTER);
                str_.append(parent_.getTagName());
                str_.append(END_TAG);
                if (parent_ == root_) {
                    current_ = null;
                    break;
                }
                current_ = parent_;
            }
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
    public NodeList getDescNodes() {
        NodeList elements_ = new NodeList();
        Element root_ = this;
        Node current_ = this;
        while (current_ != null) {
            elements_.add(current_);
            Node next_ = current_.getFirstChild();
            if (next_ != null) {
                current_ = next_;
                continue;
            }
            while (true) {
                next_ = current_.getNextSibling();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                Element parent_ = current_.getParentNode();
                if (parent_ == null) {
                    current_ = null;
                    break;
                }
                if (parent_ == root_) {
                    current_ = null;
                    break;
                }
                current_ = parent_;
            }
        }
        return elements_;
    }
    @Override
    public NodeList getElementsByTagName() {
        NodeList elements_ = new NodeList();
        Element root_ = this;
        Node current_ = this;
        while (current_ != null) {
            if (current_ instanceof Element) {
                elements_.add(current_);
            }
            Node next_ = current_.getFirstChild();
            if (next_ != null) {
                current_ = next_;
                continue;
            }
            while (true) {
                next_ = current_.getNextSibling();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                Element parent_ = current_.getParentNode();
                if (parent_ == null) {
                    current_ = null;
                    break;
                }
                if (parent_ == root_) {
                    current_ = null;
                    break;
                }
                current_ = parent_;
            }
        }
        return elements_;
    }

    @Override
    public ElementList getElementsByTagName(String _tagName) {
        ElementList elements_ = new ElementList();
        Element root_ = this;
        Node current_ = this;
        while (current_ != null) {
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
            while (true) {
                next_ = current_.getNextSibling();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                Element parent_ = current_.getParentNode();
                if (parent_ == null) {
                    current_ = null;
                    break;
                }
                if (parent_ == root_) {
                    current_ = null;
                    break;
                }
                current_ = parent_;
            }
        }
        return elements_;
    }
    @Override
    public String getTextContent() {
        FullElement root_ = this;
        Node current_ = this;
        StringBuilder str_ = new StringBuilder();
        while (current_ != null) {
            if (current_ instanceof Text) {
                Text txt_ = (Text) current_;
                str_.append(txt_.getTextContent());
            }
            Node next_ = current_.getFirstChild();
            if (next_ != null) {
                current_ = next_;
                continue;
            }
            while (true) {
                next_ = current_.getNextSibling();
                if (next_ != null) {
                    current_ = next_;
                    break;
                }
                Element parent_ = current_.getParentNode();
                if (parent_ == null) {
                    current_ = null;
                    break;
                }
                if (parent_ == root_) {
                    current_ = null;
                    break;
                }
                current_ = parent_;
            }
        }
        return str_.toString();
    }

}
