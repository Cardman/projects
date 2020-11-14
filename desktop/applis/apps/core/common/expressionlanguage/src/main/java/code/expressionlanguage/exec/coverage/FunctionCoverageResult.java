package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.analyze.blocks.Block;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.BoolVal;

public final class FunctionCoverageResult {
    private final IdMap<ExecBlock,BooleanCoverageResult> coversConditions = new IdMap<ExecBlock,BooleanCoverageResult>();
    private final IdMap<ExecBlock,BooleanCoverageResult> coversConditionsForMutable = new IdMap<ExecBlock,BooleanCoverageResult>();
    private final CustList<BlockCoverageResult> blocks = new CustList<BlockCoverageResult>();
    private final IdMap<ExecBlock,BooleanCoverageResult> coverLoops = new IdMap<ExecBlock,BooleanCoverageResult>();
    private final IdMap<ExecBlock,SwitchCoverageResult> coverSwitchs = new IdMap<ExecBlock,SwitchCoverageResult>();
    private final IdMap<ExecBlock,BoolVal> catches = new IdMap<ExecBlock,BoolVal>();
    private final IdMap<ExecBlock,Block> mappingBlocks = new IdMap<ExecBlock,Block>();
    private final CustList<BlockCoverageResult> annotations = new CustList<BlockCoverageResult>();
    private final CustList<CustList<BlockCoverageResult>> annotationsParams = new CustList<CustList<BlockCoverageResult>>();
    private boolean called;

    public IdMap<ExecBlock, BooleanCoverageResult> getCoversConditions() {
        return coversConditions;
    }

    public IdMap<ExecBlock, BooleanCoverageResult> getCoversConditionsForMutable() {
        return coversConditionsForMutable;
    }

    public CustList<BlockCoverageResult> getBlocks() {
        return blocks;
    }

    public IdMap<ExecBlock, BooleanCoverageResult> getCoverLoops() {
        return coverLoops;
    }

    public IdMap<ExecBlock, SwitchCoverageResult> getCoverSwitchs() {
        return coverSwitchs;
    }

    public IdMap<ExecBlock, BoolVal> getCatches() {
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

    public CustList<BlockCoverageResult> getAnnotations() {
        return annotations;
    }

    public CustList<CustList<BlockCoverageResult>> getAnnotationsParams() {
        return annotationsParams;
    }
}
