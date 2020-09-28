package code.formathtml;

public abstract class RendParentBlock extends RendBlock {

    private RendBlock firstChild;
    protected RendParentBlock(int _offsetTrim) {
        super(_offsetTrim);
    }

    public final void appendChild(RendBlock _child) {
        _child.setParent(this);
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        RendBlock child_ = firstChild;
        while (true) {
            RendBlock sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                _child.setPreviousSibling(child_);
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    @Override
    public final RendBlock getFirstChild() {
        return firstChild;
    }

    public final void removeLocalVars(ImportingPage _ip) {
        for (RendBlock s: getDirectChildren(this)) {
            if (s instanceof RendDeclareVariable) {
                for (String v: ((RendDeclareVariable)s).getVariableNames()) {
                    _ip.removeLocalVar(v);
                }
            }
        }
    }

    public void exitStack(Configuration _conf) {
        //overrides
    }

    public void removeAllVars(ImportingPage _ip) {
        removeLocalVars(_ip);
    }

}
