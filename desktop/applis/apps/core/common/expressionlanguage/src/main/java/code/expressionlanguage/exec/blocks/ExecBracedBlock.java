package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.util.CustList;

public abstract class ExecBracedBlock extends ExecBlock {

    private ExecBlock firstChild;
    private final CustList<ExecDeclareVariable> declares = new CustList<ExecDeclareVariable>();

    ExecBracedBlock(int _offsetTrim) {
        super(_offsetTrim);
    }

    static boolean isNextTryParts(ExecBlock _n) {
        return _n instanceof ExecAbstractCatchEval || _n instanceof ExecFinallyEval;
    }
    public static boolean isNextIfParts(ExecBlock _n) {
        return _n instanceof ExecElseIfCondition || _n instanceof ExecElseCondition;
    }
    public final void appendChild(ExecBlock _child) {
        _child.setParent(this);
        if (_child instanceof ExecDeclareVariable) {
            declares.add((ExecDeclareVariable) _child);
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
        for (ExecDeclareVariable s: declares) {
            for (String v: s.getVariableNames()) {
                _ip.removeLocalVar(v);
            }
        }
    }

    public void removeAllVars(AbstractPageEl _ip) {
        removeLocalVars(_ip);
    }

    @Override
    public ExecBlock getFirstChild() {
        return firstChild;
    }
}
