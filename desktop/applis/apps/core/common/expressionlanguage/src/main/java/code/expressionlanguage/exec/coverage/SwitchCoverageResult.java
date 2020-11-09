package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.blocks.DefaultCondition;
import code.util.IdMap;

public final class SwitchCoverageResult {
    private final IdMap<Block,StandardCoverageResult> children = new IdMap<Block, StandardCoverageResult>();
    private final StandardCoverageResult resultNoDef = new StandardCoverageResult();
    public StandardCoverageResult noDefault() {
        for (Block b: children.getKeys()) {
            if (b instanceof DefaultCondition) {
                return null;
            }
        }
        return resultNoDef;
    }

    public IdMap<Block, StandardCoverageResult> getChildren() {
        return children;
    }

    public StandardCoverageResult getResultNoDef() {
        return resultNoDef;
    }
}
