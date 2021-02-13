package code.sml;

import code.util.NatStringTreeMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class FullElement extends NavigableNode implements Element {

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
            if (StringUtil.quickEq(a.getName(), _name)) {
                return a.getValue();
            }
        }
        return EMPTY_STRING;
    }

    @Override
    public boolean hasAttribute(String _name) {
        for (Attr a: attributes) {
            if (StringUtil.quickEq(a.getName(), _name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeAttribute(String _name) {
        int index_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        boolean found_ = false;
        for (Attr a: attributes) {
            index_++;
            if (StringUtil.quickEq(a.getName(), _name)) {
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
            if (StringUtil.quickEq(a.getName(), _name)) {
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
    public void appendChild(Node _newChild) {
        ((NavigableNode)_newChild).setParentNode(this);
        if (getFirstChild() == null) {
            setFirstChild((NavigableNode)_newChild);
            setLastChild((NavigableNode)_newChild);
            return;
        }
        ((NavigableNode)getLastChild()).setNextSibling((NavigableNode)_newChild);
        ((NavigableNode)_newChild).setPreviousSibling((NavigableNode)getLastChild());
        setLastChild((NavigableNode)_newChild);
    }

    @Override
    public void removeChild(Node _oldChild) {
        Node child_ = getFirstChild();
        while (child_ != null) {
            if (child_ == _oldChild) {
                Node previous_ = child_.getPreviousSibling();
                Node next_ = child_.getNextSibling();
                ((FullNode)_oldChild).setParentNode(null);
                if (previous_ == null && next_ == null) {
                    setFirstChild(null);
                    setLastChild(null);
                    return;
                }
                if (next_ == null) {
                    //previous_ != null
                    ((NavigableNode)previous_).setNextSibling(null);
                    setLastChild((NavigableNode)previous_);
                    return;
                }
                if (previous_ == null) {
                    //next_ != null
                    ((NavigableNode)next_).setPreviousSibling(null);
                    setFirstChild((NavigableNode)next_);
                    return;
                }
                //previous_ != null && next_ != null
                ((NavigableNode)previous_).setNextSibling((NavigableNode)next_);
                ((NavigableNode)next_).setPreviousSibling((NavigableNode)previous_);
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
                ((FullNode)_oldChild).setParentNode(null);
                ((FullNode)_newChild).setParentNode(this);
                if (previous_ == null && next_ == null) {
                    setFirstChild((NavigableNode)_newChild);
                    setLastChild((NavigableNode)_newChild);
                    return;
                }
                if (next_ == null) {
                    //previous_ != null
                    ((NavigableNode)previous_).setNextSibling((NavigableNode)_newChild);
                    ((NavigableNode)_newChild).setPreviousSibling((NavigableNode)previous_);
                    setLastChild((NavigableNode)_newChild);
                    return;
                }
                if (previous_ == null) {
                    //next_ != null
                    ((NavigableNode)next_).setPreviousSibling((NavigableNode)_newChild);
                    ((NavigableNode)_newChild).setNextSibling((NavigableNode)next_);
                    setFirstChild((NavigableNode)_newChild);
                    return;
                }
                //previous_ != null && next_ != null
                ((NavigableNode)previous_).setNextSibling((NavigableNode)_newChild);
                ((NavigableNode)next_).setPreviousSibling((NavigableNode)_newChild);
                ((NavigableNode)_newChild).setNextSibling((NavigableNode)next_);
                ((NavigableNode)_newChild).setPreviousSibling((NavigableNode)previous_);
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
                ((FullNode)_newChild).setParentNode(this);
                if (previous_ == null) {
                    setFirstChild((NavigableNode)_newChild);
                    ((NavigableNode)_newChild).setNextSibling((NavigableNode)_refChild);
                    ((NavigableNode)_refChild).setPreviousSibling((NavigableNode)_newChild);
                    return;
                }
                ((NavigableNode)_refChild).setPreviousSibling((NavigableNode)_newChild);
                ((NavigableNode)previous_).setNextSibling((NavigableNode)_newChild);
                ((NavigableNode)_newChild).setNextSibling((NavigableNode)_refChild);
                ((NavigableNode)_newChild).setPreviousSibling((NavigableNode)previous_);
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
                ((FullNode)_newChild).setParentNode(this);
                if (next_ == null) {
                    setLastChild((NavigableNode) _newChild);
                    ((NavigableNode)_newChild).setPreviousSibling((NavigableNode)_refChild);
                    ((NavigableNode)_refChild).setNextSibling((NavigableNode)_newChild);
                    return;
                }
                ((NavigableNode)_refChild).setNextSibling((NavigableNode)_newChild);
                ((NavigableNode)next_).setPreviousSibling((NavigableNode)_newChild);
                ((NavigableNode)_newChild).setPreviousSibling((NavigableNode)_refChild);
                ((NavigableNode)_newChild).setNextSibling((NavigableNode)next_);
                return;
            }
            child_ = child_.getNextSibling();
        }
    }

    public String openTag() {
        FullElement root_ = this;
        Node current_ = this;
        StringBuilder str_ = new StringBuilder();
        if (getFirstChild() == null) {
            appendInfos(str_, this);
            str_.append(END_TAG);
            str_.append(BEGIN_FOOTER);
            str_.append(getTagName());
            str_.append(END_TAG);
            return str_.toString();
        }
        while (current_ != null) {
            appendHeader(current_, str_);
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

    private static void appendHeader(Node _current, StringBuilder _str) {
        if (_current instanceof FullElement) {
            FullElement elt_ = (FullElement) _current;
            appendInfos(_str, elt_);
        }
        if (_current instanceof Text) {
            Text txt_ = (Text) _current;
            _str.append(DocumentBuilder.escape(txt_.getData(), false));
        }
    }

    public String exportSorted() {
        FullElement root_ = this;
        Node current_ = this;
        StringBuilder str_ = new StringBuilder();
        if (getFirstChild() == null) {
            appendSortedInfos(str_, this);
            str_.append(END_LEAF);
            return str_.toString();
        }
        while (current_ != null) {
            appendSortedHeader(current_, str_);
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

    private static void appendSortedHeader(Node _current, StringBuilder _str) {
        if (_current instanceof FullElement) {
            FullElement elt_ = (FullElement) _current;
            appendSortedInfos(_str, elt_);
        }
        if (_current instanceof Text) {
            Text txt_ = (Text) _current;
            _str.append(DocumentBuilder.escape(txt_.getData(), false));
        }
    }

    private static void appendSortedInfos(StringBuilder _str, FullElement _elt) {
        _str.append(BEGIN_TAG);
        _str.append(_elt.getTagName());
        if (!_elt.attributes.isEmpty()) {
            NatStringTreeMap<String> m_ = new NatStringTreeMap<String>();
            for (Attr a : _elt.attributes) {
                m_.put(a.getName(),a.export());
            }
            for (String a : m_.values()) {
                _str.append(a);
            }
        }
    }

    @Override
    public String export() {
        FullElement root_ = this;
        Node current_ = this;
        StringBuilder str_ = new StringBuilder();
        if (getFirstChild() == null) {
            appendInfos(str_, this);
            str_.append(END_LEAF);
            return str_.toString();
        }
        while (current_ != null) {
            appendHeader(current_, str_);
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

    private static void appendInfos(StringBuilder _str, FullElement _elt) {
        _str.append(BEGIN_TAG);
        _str.append(_elt.getTagName());
        if (!_elt.attributes.isEmpty()) {
            for (Attr a : _elt.attributes) {
                _str.append(a.export());
            }
        }
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
        if (getFirstChild() == null) {
            elements_.add(this);
            return elements_;
        }
        Element root_ = this;
        Node current_ = this;
        while (current_ != null) {
            elements_.add(current_);
            Node next_ = current_.getFirstChild();
            if (next_ != null) {
                current_ = next_;
                continue;
            }
            current_ = next(current_,root_);
        }
        return elements_;
    }
    @Override
    public NodeList getElementsByTagName() {
        NodeList elements_ = new NodeList();
        if (getFirstChild() == null) {
            elements_.add(this);
            return elements_;
        }
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
            current_ = next(current_,root_);
        }
        return elements_;
    }

    @Override
    public ElementList getElementsByTagName(String _tagName) {
        ElementList elements_ = new ElementList();
        if (getFirstChild() == null) {
            addIfMatch(_tagName, elements_, this);
            return elements_;
        }
        Element root_ = this;
        Node current_ = this;
        while (current_ != null) {
            if (current_ instanceof Element) {
                Element elt_ = (Element) current_;
                addIfMatch(_tagName, elements_, elt_);
            }
            Node next_ = current_.getFirstChild();
            if (next_ != null) {
                current_ = next_;
                continue;
            }
            current_ = next(current_,root_);
        }
        return elements_;
    }

    private static void addIfMatch(String _tagName, ElementList _elements, Element _elt) {
        if (StringUtil.quickEq(_elt.getTagName(), _tagName)) {
            _elements.add(_elt);
        }
    }

    @Override
    public String getTextContent() {
        if (getFirstChild() == null) {
            return EMPTY_STRING;
        }
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
            current_ = next(current_,root_);
        }
        return str_.toString();
    }

}
