package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.exec.blocks.ExecBlock;
import code.util.IdMap;

public final class SwitchCoverageResult {
    private final IdMap<ExecBlock,StandardCoverageResult> children = new IdMap<ExecBlock, StandardCoverageResult>();
    private final StandardCoverageResult resultNoDef = new StandardCoverageResult();
    private boolean defCase;
    public StandardCoverageResult noDefault() {
        if (defCase) {
            return null;
        }
        return resultNoDef;
    }

    public IdMap<ExecBlock, StandardCoverageResult> getChildren() {
        return children;
    }

    public void setDefCase(boolean _defCase) {
        defCase = _defCase;
    }

    public StandardCoverageResult getResultNoDef() {
        return resultNoDef;
    }
}
