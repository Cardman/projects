package code.expressionlanguage.exec.blocks;

import code.util.IdList;

public final class ExecListLastBkSw {
    private final IdList<ExecBracedBlock> childrenNonDef;
    private final ExecBracedBlock firstNonDef;
    private final ExecBracedBlock firstDef;
    public ExecListLastBkSw(ExecBracedBlock _braced) {
        ExecBlock n_ = _braced.getFirstChild();
        childrenNonDef = new IdList<ExecBracedBlock>();
        ExecBracedBlock firstDef_ = null;
        ExecBracedBlock firstNonDef_ = null;
        while (n_ instanceof ExecBracedBlock) {
            if (firstNonDef_ == null && n_ instanceof ExecAbstractCaseCondition) {
                firstNonDef_= (ExecBracedBlock) n_;
            }
            if (firstDef_ == null && n_ instanceof ExecDefaultCondition) {
                firstDef_= (ExecBracedBlock) n_;
            }
            if (n_ instanceof ExecAbstractCaseCondition) {
                childrenNonDef.add((ExecBracedBlock) n_);
            }
            n_ = n_.getNextSibling();
        }
        firstNonDef = firstNonDef_;
        firstDef = firstDef_;
    }

    public ExecBracedBlock visit() {
        ExecBracedBlock f_ = getFirstNonDef();
        ExecBracedBlock d_ = getFirstDef();
        if (f_ != null) {
            return f_;
        } else {
            return d_;
        }
    }
    public ExecBracedBlock next(ExecBracedBlock _current) {
        int i_ = childrenNonDef.indexOfObj(_current)+1;
        if (childrenNonDef.isValidIndex(i_)) {
            return childrenNonDef.get(i_);
        }
        return null;
    }

    public ExecBracedBlock getFirstNonDef() {
        return firstNonDef;
    }

    public ExecBracedBlock getFirstDef() {
        return firstDef;
    }
}
