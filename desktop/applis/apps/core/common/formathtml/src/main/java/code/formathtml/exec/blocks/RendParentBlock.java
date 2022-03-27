package code.formathtml.exec.blocks;

import code.formathtml.ImportingPage;
import code.util.CustList;

public abstract class RendParentBlock extends RendBlock implements RendParentBlockInt {

    private RendBlock firstChild;
    private final CustList<RendAbstractDeclareVariable> decl = new CustList<RendAbstractDeclareVariable>();

    @Override
    public final void appendChild(RendBlock _child) {
        _child.setParent(this);
        if (_child instanceof RendAbstractDeclareVariable) {
            decl.add((RendAbstractDeclareVariable)_child);
        }
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
        for (RendAbstractDeclareVariable s: decl) {
            s.removeLocalVars(_ip);
        }
    }

    public void removeAllVars(ImportingPage _ip) {
        removeLocalVars(_ip);
    }

}
