package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.util.CustList;

public abstract class ExecBracedBlock extends ExecBlock {

    private ExecBlock firstChild;
    private final CustList<ExecAbstractDeclareVariable> declares = new CustList<ExecAbstractDeclareVariable>();

    ExecBracedBlock() {
    }

    public final void appendChild(ExecBlock _child) {
        _child.setParent(this);
        if (_child instanceof ExecAbstractDeclareVariable) {
            declares.add((ExecAbstractDeclareVariable) _child);
        }
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        ExecBlock child_ = firstChild;
        while (true) {
            ExecBlock sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                _child.setPreviousSibling(child_);
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    public final void removeLocalVars(AbstractPageEl _ip) {
        for (ExecAbstractDeclareVariable s: declares) {
            s.removeLocalVars(_ip);
        }
    }

    public void removeAllVars(AbstractPageEl _ip) {
        removeLocalVars(_ip);
    }

    public ExecBlock getFirstChild() {
        return firstChild;
    }
}
