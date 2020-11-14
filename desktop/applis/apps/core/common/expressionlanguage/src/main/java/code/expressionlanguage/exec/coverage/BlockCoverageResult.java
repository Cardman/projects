package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;
import code.util.IdMap;

public final class BlockCoverageResult {
    private final CustList<AbstractCoverageResult> covers = new CustList<AbstractCoverageResult>();
    private final IdMap<ExecOperationNode,OperationNode> mapping = new IdMap<ExecOperationNode,OperationNode>();
    private final CustList<BlockCoverageResult> annotations = new CustList<BlockCoverageResult>();

    public CustList<AbstractCoverageResult> getCovers() {
        return covers;
    }

    public IdMap<ExecOperationNode, OperationNode> getMapping() {
        return mapping;
    }

    public CustList<BlockCoverageResult> getAnnotations() {
        return annotations;
    }
}
