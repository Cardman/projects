package code.expressionlanguage.exec.blocks;

import code.util.CustList;

public final class ExecListLastBkSw {
    private final CustList<ExecBracedBlock> children;
    private final ExecBracedBlock last;
    public ExecListLastBkSw(ExecBracedBlock _braced) {
        ExecBlock n_ = _braced.getFirstChild();
        children = new CustList<ExecBracedBlock>();
        ExecBracedBlock last_ = null;
        while (n_ instanceof ExecBracedBlock) {
            children.add((ExecBracedBlock)n_);
            last_= (ExecBracedBlock) n_;
            n_ = n_.getNextSibling();
        }
        last = last_;
    }

    public CustList<ExecBracedBlock> getChildren() {
        return children;
    }

    public ExecBracedBlock getLast() {
        return last;
    }
}
