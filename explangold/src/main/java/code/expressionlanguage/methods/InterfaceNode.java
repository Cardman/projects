package code.expressionlanguage.methods;

import code.util.CustList;


public final class InterfaceNode {

    private InterfaceNode firstChild;
    private InterfaceNode nextSibling;
    private InterfaceNode parent;

    private String interfaceName;

    private int indexChild = CustList.INDEX_NOT_FOUND_ELT;
    private int order = CustList.INDEX_NOT_FOUND_ELT;

    public InterfaceNode getFirstChild() {
        return firstChild;
    }

    public void setFirstChild(InterfaceNode _firstChild) {
        firstChild = _firstChild;
    }

    public InterfaceNode getNextSibling() {
        return nextSibling;
    }

    public void setNextSibling(InterfaceNode _nextSibling) {
        nextSibling = _nextSibling;
    }

    public InterfaceNode getParent() {
        return parent;
    }

    public void setParent(InterfaceNode _parent) {
        parent = _parent;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String _interfaceName) {
        interfaceName = _interfaceName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int _order) {
        order = _order;
    }

    public int getIndexChild() {
        return indexChild;
    }

    public void setIndexChild(int _indexChild) {
        indexChild = _indexChild;
    }
}
