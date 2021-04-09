package code.formathtml.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;

public abstract class AnaRendParentBlock extends AnaRendBlock {

    private AnaRendBlock firstChild;
    AnaRendParentBlock(int _offset) {
        super(_offset);
    }

    public final void appendChild(AnaRendBlock _child) {
        _child.setParent(this);
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        AnaRendBlock child_ = firstChild;
        while (true) {
            AnaRendBlock sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                _child.setPreviousSibling(child_);
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }


    public void removeAllVars(AnalyzedPageEl _ip) {
        removeLocalVars(_ip);
    }

    public final void removeLocalVars(AnalyzedPageEl _ip) {
        for (AnaRendBlock s: getDirectChildren(this)) {
            if (s instanceof AnaRendDeclareVariable) {
                for (String v: ((AnaRendDeclareVariable)s).getVariableNames()) {
                    _ip.getInfosVars().removeKey(v);
                }
            }
        }
    }
    @Override
    public final AnaRendBlock getFirstChild() {
        return firstChild;
    }
}
