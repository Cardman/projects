package code.sml;

import code.util.CustList;
import code.util.StringList;

public final class NotTextElement implements Node, Element {

    private static final String BEGIN_TAG = "<";

    private static final String END_LEAF = "/>";

    private static final String BEGIN_FOOTER = "</";

    private static final String END_TAG = ">";

    private static final String EMPTY_STRING = "";

    private String tagName;

    private NamedNodeMap attributes = new NamedNodeMap();

    private Document ownerDocument;

    private Element parentNode;

    private ElementList childElements = new ElementList();

    protected NotTextElement(Document _ownerDocument) {
        ownerDocument = _ownerDocument;
    }

    @Override
    public Element getParentNode() {
        return parentNode;
    }
    public void setParentNode(Element _parentNode) {
        parentNode = _parentNode;
    }
    public Document getOwnerDocument() {
        return ownerDocument;
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
        Attr attr_ = CoreDocument.createAttribute(_name);
        attr_.setValue(_value);
        attributes.add(attr_);
    }
    public void setEscapedAttribute(String _name, String _value) {
        for (Attr a: attributes) {
            if (StringList.quickEq(a.getName(), _name)) {
                a.setEscapedValue(_value);
                return;
            }
        }
        Attr attr_ = CoreDocument.createAttribute(_name);
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
        Attr attr_ = CoreDocument.createAttribute(_newAttr.getName());
        attr_.setValue(_newAttr.getValue());
        attributes.add(attr_);
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
    public ElementList getChildElements() {
        return childElements;
    }

    @Override
    public void appendChild(Node _newChild) {
        _newChild.setParentNode(this);
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
            childElements.removeAt(i);
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
            _newChild.setParentNode(this);
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
            _newChild.setParentNode(this);
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
            _newChild.setParentNode(this);
            childElements.add(i + 1, (Element) _newChild);
            return;
        }
    }

    public String export() {
        NotTextElement root_ = this;
        Node current_ = getFirstChild();
        StringBuilder str_ = new StringBuilder();
        str_.append(BEGIN_TAG);
        str_.append(getTagName());
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
            NotTextElement elt_ = (NotTextElement) current_;
            str_.append(BEGIN_TAG);
            str_.append(elt_.getTagName());
            if (!elt_.attributes.isEmpty()) {
                for (Attr a: elt_.attributes) {
                    str_.append(a.export());
                }
            }
            Node next_ = current_.getFirstChild();
            if (next_ != null) {
                str_.append(END_TAG);
                current_ = next_;
                continue;
            }
            str_.append(END_LEAF);
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
            str_.append(BEGIN_FOOTER);
            str_.append(parent_.getTagName());
            str_.append(END_TAG);
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
                str_.append(BEGIN_FOOTER);
                str_.append(par_.getTagName());
                str_.append(END_TAG);
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
    public NodeList getDescNodes() {
        NodeList elements_ = new NodeList();
        NotTextElement root_ = this;
        elements_.add(root_);
        Node current_ = getFirstChild();
        while (true) {
            if (current_ == null) {
                break;
            }
            elements_.add(current_);
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
    public NodeList getElementsByTagName() {
        NodeList elements_ = new NodeList();
        NotTextElement root_ = this;
        elements_.add(root_);
        Node current_ = getFirstChild();
        while (true) {
            if (current_ == null) {
                break;
            }
            NotTextElement elt_ = (NotTextElement) current_;
            elements_.add(elt_);
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

    public ElementList getElementsByTagName(String _tagName) {
        ElementList elements_ = new ElementList();
        NotTextElement root_ = this;
        if (StringList.quickEq(getTagName(), _tagName)) {
            elements_.add(root_);
        }
        Node current_ = getFirstChild();
        while (true) {
            if (current_ == null) {
                break;
            }
            NotTextElement elt_ = (NotTextElement) current_;
            if (StringList.quickEq(elt_.getTagName(), _tagName)) {
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
    @Override
    public String getTextContent() {
        return "";
    }

    @Override
    public boolean isEqualNode(Node _arg) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Node getNextSibling() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Node getPreviousSibling() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Node getFirstChild() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Node getLastChild() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setNextSibling(Node _node) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setPreviousSibling(Node _node) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setFirstChild(Node _node) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setLastChild(Node _node) {
        // TODO Auto-generated method stub
        
    }
}
