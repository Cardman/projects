package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.exec.blocks.ExecBlock;
import code.util.IdMap;

public final class SwitchCoverageResult {
    private final IdMap<ExecBlock,AbstractCoverageResult> children = new IdMap<ExecBlock, AbstractCoverageResult>();
    private final AbstractCoverageResult resultNoDef = new StandardCoverageResult();
    private boolean defCase;
    public AbstractCoverageResult noDefault() {
        if (defCase) {
            return null;
        }
        return resultNoDef;
    }

    public IdMap<ExecBlock, AbstractCoverageResult> getChildren() {
        return children;
    }

    public void setDefCase(boolean _defCase) {
        defCase = _defCase;
    }

    public AbstractCoverageResult getResultNoDef() {
        return resultNoDef;
    }
}
