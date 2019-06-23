package code.sml;

public interface MutableNode extends Node {

    void setParentNode(Element _parentNode);
    void setNextSibling(MutableNode _node);

    void setPreviousSibling(MutableNode _node);

    void setFirstChild(MutableNode _node);

    void setLastChild(MutableNode _node);

    void appendChild(MutableNode _newChild);

    void removeChild(MutableNode _oldChild);
    void replaceChild(MutableNode _newChild, MutableNode _oldChild);
    void insertBefore(MutableNode _newChild, MutableNode _refChild);
    void insertAfter(MutableNode _newChild, MutableNode _refChild);
}
