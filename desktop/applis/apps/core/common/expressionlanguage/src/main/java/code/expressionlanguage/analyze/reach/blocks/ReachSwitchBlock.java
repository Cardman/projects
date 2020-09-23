package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.exec.blocks.ExecBracedBlock;
import code.expressionlanguage.exec.blocks.ExecEnumSwitchBlock;
import code.expressionlanguage.exec.blocks.ExecInstanceSwitchBlock;
import code.expressionlanguage.exec.blocks.ExecStdSwitchBlock;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;

public final class ReachSwitchBlock extends ReachBracedBlock implements ReachBreakableBlock,ReachBuildableElMethod {
    private String label;
    private AnaClassArgumentMatching result;
    private int valueOffset;
    private OperationNode root;
    private boolean enumTest;
    private String instanceTest;

    protected ReachSwitchBlock(SwitchBlock _info) {
        super(_info);
        label = _info.getLabel();
        result = _info.getResult();
        valueOffset = _info.getValueOffset();
        root = _info.getRoot();
        instanceTest = _info.getInstanceTest();
        enumTest = _info.isEnumTest();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.setGlobalOffset(valueOffset);
        _page.setOffset(0);
        ReachBlock first_ = getFirstChild();
        boolean def_ = false;
        while (first_ != null) {
            ReachBlock elt_ = first_;
            if (elt_ instanceof ReachDefaultCondition) {
                def_ = true;
                first_ = first_.getNextSibling();
                continue;
            }
            first_ = first_.getNextSibling();
        }
        _page.getCoverage().putBlockOperationsSwitchs(getInfo(),def_);
        CustList<ExecOperationNode> op_ = ReachOperationUtil.tryCalculateAndSupply(root, _page);
        ExecBracedBlock exec_;
        if (!instanceTest.isEmpty()) {
            exec_ = new ExecInstanceSwitchBlock(getOffset(), label, valueOffset, op_);
        } else if (enumTest) {
            exec_ = new ExecEnumSwitchBlock(getOffset(), label, valueOffset, op_);
        } else {
            exec_ = new ExecStdSwitchBlock(getOffset(), label, valueOffset, op_);
        }
        exec_.setFile(_page.getBlockToWrite().getFile());
        _page.getBlockToWrite().appendChild(exec_);
        _page.getAnalysisAss().getReachMappingBracedMembers().put(this,exec_);
        _page.getCoverage().putBlockOperations(exec_,getInfo());
    }
    @Override
    public void abrupt(AnalyzingEl _anEl) {
        ReachBlock ch_ = getFirstChild();
        if (ch_ == null) {
            return;
        }
        boolean abrupt_ = true;
        boolean def_ = hasDefaultCase();
        if (!def_) {
            abrupt_ = false;
        } else if (!instanceTest.isEmpty()) {
            CustList<ReachBlock> group_ = new CustList<ReachBlock>();
            for (ReachBlock b: getDirectChildren(this)) {
                group_.add(b);
            }
            boolean canCmpNormally_ = false;
            for (ReachBlock b: group_) {
                if (_anEl.canCompleteNormally(b)) {
                    canCmpNormally_ = true;
                    break;
                }
            }
            if (canCmpNormally_) {
                abrupt_ = false;
            }
        } else {
            while (ch_.getNextSibling() != null) {
                ch_ = ch_.getNextSibling();
            }
            if (_anEl.canCompleteNormally(ch_)) {
                abrupt_ = false;
            }
        }
        IdMap<ReachBreakBlock, ReachBreakableBlock> breakables_;
        breakables_ = _anEl.getReachBreakables();
        for (EntryCust<ReachBreakBlock, ReachBreakableBlock> e: breakables_.entryList()) {
            if (e.getValue() == this && _anEl.isReachable(e.getKey())) {
                abrupt_ = false;
                break;
            }
        }
        if (abrupt_) {
            _anEl.completeAbruptGroup(this);
        }
    }

    private boolean hasDefaultCase() {
        ReachBlock ch_ = getFirstChild();
        boolean def_ = false;
        while (ch_.getNextSibling() != null) {
            if (ch_ instanceof ReachDefaultCondition) {
                def_ = true;
            }
            ch_ = ch_.getNextSibling();
        }
        if (ch_ instanceof ReachDefaultCondition) {
            def_ = true;
        }
        return def_;
    }


    public AnaClassArgumentMatching getResult() {
        return result;
    }

    public String getInstanceTest() {
        return instanceTest;
    }
}
