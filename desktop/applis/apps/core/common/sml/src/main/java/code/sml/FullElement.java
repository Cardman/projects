package code.sml;

public final class FullElement extends NavigableNode implements Element {

    protected FullElement(Document _ownerDocument) {
        super(_ownerDocument);
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

    @Override
    public boolean hasChildNodes() {
        return getFirstChild() != null;
    }

    @Override
    public boolean hasAttributes() {
        return !getAttributes().isEmpty();
    }

}
