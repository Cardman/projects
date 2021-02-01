package code.sml;

public interface MutableNode extends Node {

    void setParentNode(Element _parentNode);
//    void setNextSibling(MutableNode _node);
//
//    void setPreviousSibling(MutableNode _node);
//
//    void setFirstChild(MutableNode _node);
//
//    void setLastChild(MutableNode _node);

    void appendChild(Node _newChild);

    void removeChild(Node _oldChild);
    void replaceChild(Node _newChild, Node _oldChild);
    void insertBefore(Node _newChild, Node _refChild);
    void insertAfter(Node _newChild, Node _refChild);
}
