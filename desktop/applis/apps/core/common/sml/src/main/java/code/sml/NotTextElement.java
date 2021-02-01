package code.sml;

import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class NotTextElement extends FullNode implements Element {

    private static final String BEGIN_TAG = "<";

    private static final String END_LEAF = "/>";

    private static final String BEGIN_FOOTER = "</";

    private static final String END_TAG = ">";

    private static final String EMPTY_STRING = "";

    private String tagName;

    private NamedNodeMap attributes = new NamedNodeMap();
    private final ElementList childElements = new ElementList();
    protected NotTextElement(Document _ownerDocument) {
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
        return childElements;
    }

    @Override
    public void appendChild(Node _newChild) {
        ((Element)_newChild).setParentNode(this);
        childElements.add((Element) _newChild);
    }

    @Override
    public void removeChild(Node _oldChild) {
        int len_ = childElements.size();
        for (int i = 0; i < len_; i++) {
            Element e_ = childElements.get(i);
            if (e_ != _oldChild) {
                continue;
            }
            e_.setParentNode(null);
            childElements.remove(i);
            return;
        }
    }

    @Override
    public void replaceChild(Node _newChild, Node _oldChild) {
        int len_ = childElements.size();
        for (int i = 0; i < len_; i++) {
            Element e_ = childElements.get(i);
            if (e_ != _oldChild) {
                continue;
            }
            e_.setParentNode(null);
            ((MutableNode)_newChild).setParentNode(this);
            childElements.set(i, (Element) _newChild);
            return;
        }
    }

    @Override
    public void insertBefore(Node _newChild, Node _refChild) {
        int len_ = childElements.size();
        for (int i = 0; i < len_; i++) {
            Element e_ = childElements.get(i);
            if (e_ != _refChild) {
                continue;
            }
            ((MutableNode)_newChild).setParentNode(this);
            childElements.add(i, (Element) _newChild);
            return;
        }
    }

    @Override
    public void insertAfter(Node _newChild, Node _refChild) {
        int len_ = childElements.size();
        for (int i = 0; i < len_; i++) {
            Element e_ = childElements.get(i);
            if (e_ != _refChild) {
                continue;
            }
            ((MutableNode)_newChild).setParentNode(this);
            childElements.add(i + 1, (Element) _newChild);
            return;
        }
    }

    @Override
    public String export() {
        NotTextElement root_ = this;
        Node current_ = this;
        StringBuilder str_ = new StringBuilder();
        if (getFirstChild() == null) {
            appendInfos(str_, this);
            str_.append(END_LEAF);
            return str_.toString();
        }
        while (current_ != null) {
            NotTextElement elt_ = (NotTextElement) current_;
            appendInfos(str_, elt_);
            Node next_ = current_.getFirstChild();
            if (next_ != null) {
                str_.append(END_TAG);
                current_ = next_;
                continue;
            }
            str_.append(END_LEAF);
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

    private static void appendInfos(StringBuilder _str, NotTextElement _elt) {
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
        for (Node e: getElementsByTagName()) {
            elements_.add(e);
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
        if (getFirstChild() == null) {
            addIfMatch(_tagName, elements_, this);
            return elements_;
        }
        Element root_ = this;
        Node current_ = this;
        while (current_ != null) {
            Element elt_ = (Element) current_;
            addIfMatch(_tagName, elements_, elt_);
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
                if (parent_ == root_) {
                    current_ = null;
                    break;
                }
                current_ = parent_;
            }
        }
        return elements_;
    }

    private static void addIfMatch(String _tagName, ElementList _elemets, Element _elt) {
        if (StringUtil.quickEq(_elt.getTagName(), _tagName)) {
            _elemets.add(_elt);
        }
    }

    @Override
    public String getTextContent() {
        return "";
    }

    @Override
    public Node getNextSibling() {
        Element parentNode_ = getParentNode();
        if (!(parentNode_ instanceof NotTextElement)) {
            return null;
        }
        int index_ = -1;
        int size_ = ((NotTextElement) parentNode_).childElements.getLength();
        for (int i = 0; i < size_; i++) {
            if (((NotTextElement) parentNode_).childElements.item(i) == this && i + 1 < size_) {
                index_ = i + 1;
            }
        }
        if (!((NotTextElement) parentNode_).childElements.isValidIndex(index_)) {
            return null;
        }
        return ((NotTextElement) parentNode_).childElements.item(index_);
    }

    @Override
    public Node getPreviousSibling() {
        Element parentNode_ = getParentNode();
        if (!(parentNode_ instanceof NotTextElement)) {
            return null;
        }
        int index_ = -1;
        int size_ = ((NotTextElement) parentNode_).childElements.getLength();
        for (int i = 0; i < size_; i++) {
            if (((NotTextElement) parentNode_).childElements.item(i) == this && i > 0) {
                index_ = i - 1;
            }
        }
        if (!((NotTextElement) parentNode_).childElements.isValidIndex(index_)) {
            return null;
        }
        return ((NotTextElement) parentNode_).childElements.item(index_);
    }

    @Override
    public Node getFirstChild() {
        if (childElements.isEmpty()) {
            return null;
        }
        return childElements.first();
    }

    @Override
    public Node getLastChild() {
        if (childElements.isEmpty()) {
            return null;
        }
        return childElements.last();
    }

}
