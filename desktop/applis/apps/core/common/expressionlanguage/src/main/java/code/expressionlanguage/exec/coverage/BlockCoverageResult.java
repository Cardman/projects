package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.IdMap;

public final class BlockCoverageResult {
    private final IdMap<OperationNode,AbstractCoverageResult> covers = new IdMap<OperationNode, AbstractCoverageResult>();
    private final IdMap<ExecOperationNode,OperationNode> mapping = new IdMap<ExecOperationNode,OperationNode>();

    public IdMap<OperationNode, AbstractCoverageResult> getCovers() {
        return covers;
    }

    public IdMap<ExecOperationNode, OperationNode> getMapping() {
        return mapping;
    }
}
