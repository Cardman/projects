package code.sml;

import code.util.NatStringTreeMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public abstract class FullNode implements Node {

    private static final String BEGIN_TAG = "<";
    private static final String END_LEAF = "/>";

    private static final String BEGIN_FOOTER = "</";

    private static final String END_TAG = ">";
    private static final String EMPTY_STRING = "";
    private final Document ownerDocument;

    private Element parentNode;

    protected FullNode(Document _ownerDocument) {
        ownerDocument = _ownerDocument;
    }

    public String getAttribute(String _name) {
        for (Attr a: getAttributes()) {
            if (StringUtil.quickEq(a.getName(), _name)) {
                return a.getValue();
            }
        }
        return EMPTY_STRING;
    }

    public boolean hasAttribute(String _name) {
        for (Attr a: getAttributes()) {
            if (StringUtil.quickEq(a.getName(), _name)) {
                return true;
            }
        }
        return false;
    }

    public void removeAttribute(String _name) {
        int index_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        boolean found_ = false;
        for (Attr a: getAttributes()) {
            index_++;
            if (StringUtil.quickEq(a.getName(), _name)) {
                found_ = true;
                break;
            }
        }
        if (!found_) {
            return;
        }
        getAttributes().remove(index_);
    }

    public void setAttribute(String _name, String _value) {
        for (Attr a: getAttributes()) {
            if (StringUtil.quickEq(a.getName(), _name)) {
                a.setValue(_value);
                return;
            }
        }
        Attr attr_ = CoreDocument.createAttribute(_name);
        attr_.setValue(_value);
        getAttributes().add(attr_);
    }

    public NodeList getChildNodes() {
        NodeList children_ = new NodeList();
        Node child_ = getFirstChild();
        while (child_ != null) {
            children_.add(child_);
            child_ = child_.getNextSibling();
        }
        return children_;
    }

    public NodeList getDescNodes() {
        NodeList elements_ = new NodeList();
        if (getFirstChild() == null) {
            elements_.add(this);
            return elements_;
        }
        Node root_ = this;
        Node current_ = this;
        while (current_ != null) {
            elements_.add(current_);
            current_ = next(current_,root_);
        }
        return elements_;
    }
    public NodeList getElementsByTagName() {
        NodeList elements_ = new NodeList();
        if (getFirstChild() == null) {
            elements_.add(this);
            return elements_;
        }
        Node root_ = this;
        Node current_ = this;
        while (current_ != null) {
            if (current_ instanceof Element) {
                elements_.add(current_);
            }
            current_ = next(current_,root_);
        }
        return elements_;
    }

    public ElementList getElementsByTagName(String _tagName) {
        ElementList elements_ = new ElementList();
        if (getFirstChild() == null) {
            addIfMatch(_tagName, elements_, this);
            return elements_;
        }
        Node root_ = this;
        Node current_ = this;
        while (current_ != null) {
            addIfMatch(_tagName, elements_, current_);
            current_ = next(current_,root_);
        }
        return elements_;
    }

    private static void addIfMatch(String _tagName, ElementList _elements, Node _current) {
        if (_current instanceof Element) {
            Element elt_ = (Element) _current;
            addIfMatch(_tagName, _elements, elt_);
        }
    }

    private static void addIfMatch(String _tagName, ElementList _elements, Element _elt) {
        if (StringUtil.quickEq(_elt.getTagName(), _tagName)) {
            _elements.add(_elt);
        }
    }
    public String export() {
        Node root_ = this;
        Node current_ = this;
        StringBuilder str_ = new StringBuilder();
        if (getFirstChild() == null) {
            appendInfosGene(this, str_);
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
            appendEndLeaf(current_, str_);
            current_ = appendEndTag(current_,root_,str_);
        }
        return str_.toString();
    }

    private static void appendEndLeaf(Node _current, StringBuilder _str) {
        if (_current instanceof Element) {
            _str.append(END_LEAF);
        }
    }

    public String getTextContent() {
        if (getFirstChild() == null) {
            return EMPTY_STRING;
        }
        Node root_ = this;
        Node current_ = this;
        StringBuilder str_ = new StringBuilder();
        while (current_ != null) {
            if (current_ instanceof Text) {
                Text txt_ = (Text) current_;
                str_.append(txt_.getTextContent());
            }
            current_ = next(current_,root_);
        }
        return str_.toString();
    }

    public String openTag() {
        Node root_ = this;
        Node current_ = this;
        StringBuilder str_ = new StringBuilder();
        if (getFirstChild() == null) {
            appendInfos(str_, (Element) this);
            appendEndOpenedTag(current_,str_);
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
            appendEndOpenedTag(current_, str_);
            current_ = appendEndTag(current_,root_,str_);
        }
        return str_.toString();
    }
    private static void appendEndOpenedTag(Node _current, StringBuilder _str) {
        if (_current instanceof Element) {
            _str.append(END_TAG);
            _str.append(BEGIN_FOOTER);
            _str.append(((Element) _current).getTagName());
            _str.append(END_TAG);
        }
    }

    public String exportSorted() {
        Node root_ = this;
        Node current_ = this;
        StringBuilder str_ = new StringBuilder();
        if (getFirstChild() == null) {
            appendSortedInfos(str_, (Element) this);
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
            appendEndLeaf(current_, str_);
            current_ = appendEndTag(current_,root_,str_);
        }
        return str_.toString();
    }

    private static Node appendEndTag(Node _current,Node _root, StringBuilder _str) {
        Node current_ = _current;
        while (current_ != null) {
            Node next_ = current_.getNextSibling();
            if (next_ != null) {
                current_ = next_;
                break;
            }
            Element parent_ = current_.getParentNode();
            _str.append(BEGIN_FOOTER);
            _str.append(parent_.getTagName());
            _str.append(END_TAG);
            current_ = nextSib(_root, parent_);
        }
        return current_;
    }

    private static void appendSortedHeader(Node _current, StringBuilder _str) {
        if (_current instanceof Element) {
            Element elt_ = (Element) _current;
            appendSortedInfos(_str, elt_);
        }
        if (_current instanceof Text) {
            Text txt_ = (Text) _current;
            _str.append(DocumentBuilder.escape(txt_.getData(), false));
        }
    }

    private static void appendSortedInfos(StringBuilder _str, Element _elt) {
        _str.append(BEGIN_TAG);
        _str.append(_elt.getTagName());
        if (!_elt.getAttributes().isEmpty()) {
            NatStringTreeMap<String> m_ = new NatStringTreeMap<String>();
            for (Attr a : _elt.getAttributes()) {
                m_.put(a.getName(),a.export());
            }
            for (String a : m_.values()) {
                _str.append(a);
            }
        }
    }

    protected static void appendHeader(Node _current, StringBuilder _str) {
        appendInfosGene(_current, _str);
        if (_current instanceof Text) {
            Text txt_ = (Text) _current;
            _str.append(DocumentBuilder.escape(txt_.getData(), false));
        }
    }

    protected static void appendInfosGene(Node _current, StringBuilder _str) {
        if (_current instanceof Element) {
            Element elt_ = (Element) _current;
            appendInfos(_str, elt_);
        }
    }

    protected static void appendInfos(StringBuilder _str, Element _elt) {
        _str.append(BEGIN_TAG);
        _str.append(_elt.getTagName());
        if (!_elt.getAttributes().isEmpty()) {
            for (Attr a : _elt.getAttributes()) {
                _str.append(a.export());
            }
        }
    }
    protected static Node next(Node _current, Node _root) {
        Node ch_ = _current.getFirstChild();
        if (ch_ != null) {
            return ch_;
        }
        Node current_ = _current;
        while (current_ != null) {
            Node next_ = current_.getNextSibling();
            if (next_ != null) {
                current_ = next_;
                break;
            }
            Element parent_ = current_.getParentNode();
            current_ = nextSib(_root, parent_);
        }
        return current_;
    }

    private static Node nextSib(Node _root, Element _parent) {
        if (_parent == _root) {
            return null;
        }
        return _parent;
    }

    @Override
    public Document getOwnerDocument() {
        return ownerDocument;
    }
    @Override
    public Element getParentNode() {
        return parentNode;
    }
    public void setParentNode(Element _parentNode) {
        parentNode = _parentNode;
    }

}
