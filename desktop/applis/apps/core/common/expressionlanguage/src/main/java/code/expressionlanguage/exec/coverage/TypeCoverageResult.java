package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.analyze.blocks.MemberCallingsBlock;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.util.CustList;
import code.util.IdMap;

public final class TypeCoverageResult {
    private final CustList<FunctionCoverageResult> functions = new CustList<FunctionCoverageResult>();
    private final IdMap<ExecBlock,MemberCallingsBlock> mappingBlocks = new IdMap<ExecBlock,MemberCallingsBlock>();
    private final IdMap<ExecBlock,Block> mappingFields = new IdMap<ExecBlock,Block>();
    private final CustList<BlockCoverageResult> fields = new CustList<BlockCoverageResult>();
    private final CustList<BlockCoverageResult> annotationsFields = new CustList<BlockCoverageResult>();
    private final CustList<BlockCoverageResult> annotations = new CustList<BlockCoverageResult>();

    public CustList<FunctionCoverageResult> getFunctions() {
        return functions;
    }

    public IdMap<ExecBlock, MemberCallingsBlock> getMappingBlocks() {
        return mappingBlocks;
    }

    public IdMap<ExecBlock, Block> getMappingFields() {
        return mappingFields;
    }

    public CustList<BlockCoverageResult> getFields() {
        return fields;
    }

    public CustList<BlockCoverageResult> getAnnotationsFields() {
        return annotationsFields;
    }

    public CustList<BlockCoverageResult> getAnnotations() {
        return annotations;
    }
}
