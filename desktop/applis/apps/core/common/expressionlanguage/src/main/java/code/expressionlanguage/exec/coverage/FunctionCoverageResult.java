package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.util.IdMap;

public final class FunctionCoverageResult {
    private final IdMap<Block,BooleanCoverageResult> coversConditions = new IdMap<Block,BooleanCoverageResult>();
    private final IdMap<Block,BlockCoverageResult> blocks = new IdMap<Block,BlockCoverageResult>();
    private final IdMap<Block,BooleanCoverageResult> coverLoops = new IdMap<Block,BooleanCoverageResult>();
    private final IdMap<Block,SwitchCoverageResult> coverSwitchs = new IdMap<Block,SwitchCoverageResult>();
    private final IdMap<Block,Boolean> catches = new IdMap<Block,Boolean>();
    private final IdMap<ExecBlock,Block> mappingBlocks = new IdMap<ExecBlock,Block>();
    private boolean called;

    public IdMap<Block, BooleanCoverageResult> getCoversConditions() {
        return coversConditions;
    }

    public IdMap<Block, BlockCoverageResult> getBlocks() {
        return blocks;
    }

    public IdMap<Block, BooleanCoverageResult> getCoverLoops() {
        return coverLoops;
    }

    public IdMap<Block, SwitchCoverageResult> getCoverSwitchs() {
        return coverSwitchs;
    }

    public IdMap<Block, Boolean> getCatches() {
        return catches;
    }

    public IdMap<ExecBlock, Block> getMappingBlocks() {
        return mappingBlocks;
    }

    public boolean isCalled() {
        return called;
    }

    public void setCalled(boolean _called) {
        this.called = _called;
    }
}
