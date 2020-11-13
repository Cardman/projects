package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.util.CustList;
import code.util.IdMap;

public final class TypeCoverageResult {
    private final IdMap<Block,FunctionCoverageResult> functions = new IdMap<Block,FunctionCoverageResult>();
    private final IdMap<ExecBlock,Block> mappingBlocks = new IdMap<ExecBlock,Block>();
    private final IdMap<ExecBlock,Block> mappingFields = new IdMap<ExecBlock,Block>();
    private final IdMap<Block,BlockCoverageResult> fields = new IdMap<Block,BlockCoverageResult>();
    private final IdMap<Block,BlockCoverageResult> annotationsFields = new IdMap<Block,BlockCoverageResult>();
    private final CustList<BlockCoverageResult> annotations = new CustList<BlockCoverageResult>();

    public IdMap<Block, FunctionCoverageResult> getFunctions() {
        return functions;
    }

    public IdMap<ExecBlock, Block> getMappingBlocks() {
        return mappingBlocks;
    }

    public IdMap<ExecBlock, Block> getMappingFields() {
        return mappingFields;
    }

    public IdMap<Block, BlockCoverageResult> getFields() {
        return fields;
    }

    public IdMap<Block, BlockCoverageResult> getAnnotationsFields() {
        return annotationsFields;
    }

    public CustList<BlockCoverageResult> getAnnotations() {
        return annotations;
    }
}
