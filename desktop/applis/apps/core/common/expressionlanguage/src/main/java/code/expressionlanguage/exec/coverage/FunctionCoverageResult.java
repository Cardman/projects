package code.expressionlanguage.exec.coverage;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.util.CustList;
import code.util.IdMap;

public final class FunctionCoverageResult {
    private final IdMap<ExecBlock,AbstractCoverageResult> coversConditions = new IdMap<ExecBlock,AbstractCoverageResult>();
    private final CustList<BlockCoverageResult> blocks = new CustList<BlockCoverageResult>();
    private final IdMap<ExecBlock,AbstractCoverageResult> coverLoops = new IdMap<ExecBlock,AbstractCoverageResult>();
    private final IdMap<ExecBlock,SwitchCoverageResult> coverSwitchs = new IdMap<ExecBlock,SwitchCoverageResult>();
    private final SwitchCoverageResult coverSwitchsMethod = new SwitchCoverageResult();
    private final IdMap<ExecBlock,CustList<AbstractCoverageResult>> catches = new IdMap<ExecBlock,CustList<AbstractCoverageResult>>();
    private final IdMap<ExecBlock, AbsBk> mappingBlocks = new IdMap<ExecBlock, AbsBk>();
    private final CustList<BlockCoverageResult> annotations = new CustList<BlockCoverageResult>();
    private final CustList<CustList<BlockCoverageResult>> annotationsParams = new CustList<CustList<BlockCoverageResult>>();
    private final CustList<BlockCoverageResult> annotationsSupp = new CustList<BlockCoverageResult>();
    private boolean called;

    public IdMap<ExecBlock, AbstractCoverageResult> getCoversConditions() {
        return coversConditions;
    }

    public CustList<BlockCoverageResult> getBlocks() {
        return blocks;
    }

    public IdMap<ExecBlock, AbstractCoverageResult> getCoverLoops() {
        return coverLoops;
    }

    public IdMap<ExecBlock, SwitchCoverageResult> getCoverSwitchs() {
        return coverSwitchs;
    }

    public SwitchCoverageResult getCoverSwitchsMethod() {
        return coverSwitchsMethod;
    }

    public IdMap<ExecBlock, CustList<AbstractCoverageResult>> getCatches() {
        return catches;
    }

    public IdMap<ExecBlock, AbsBk> getMappingBlocks() {
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

    public CustList<BlockCoverageResult> getAnnotationsSupp() {
        return annotationsSupp;
    }

    public CustList<CustList<BlockCoverageResult>> getAnnotationsParams() {
        return annotationsParams;
    }
}
