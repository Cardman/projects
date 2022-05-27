package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;

public final class ReachSwitchBlock extends ReachBracedBlock implements ReachBreakableBlock,ReachBuildableElMethod,ReachAnalyzedSwitch {
    private final String label;
    private final AnaClassArgumentMatching result;
    private final int valueOffset;
    private final OperationNode root;
    private final boolean instance;

    public ReachSwitchBlock(SwitchBlock _info) {
        super(_info);
        label = _info.getLabel();
        result = _info.getResult();
        valueOffset = _info.getValueOffset();
        root = _info.getRoot();
        instance = _info.isInstance();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.setSumOffset(valueOffset);
        _page.zeroOffset();
        ReachOperationUtil.tryCalculate(root, _page);
//        ReachBlock first_ = getFirstChild();
//        boolean def_ = false;
//        while (first_ != null) {
//            ReachBlock elt_ = first_;
//            if (elt_ instanceof ReachDefaultCondition) {
//                def_ = true;
//                first_ = first_.getNextSibling();
//                continue;
//            }
//            first_ = first_.getNextSibling();
//        }
//        _page.getCoverage().putBlockOperationsSwitchs(getInfo(),def_);
//        CustList<ExecOperationNode> op_ = ElUtil.getExecutableNodes(_page, root);
//        ExecBracedBlock exec_;
//        if (!instanceTest.isEmpty()) {
//            exec_ = new ExecInstanceSwitchBlock(getOffset(), label, valueOffset, op_);
//        } else if (enumTest) {
//            exec_ = new ExecEnumSwitchBlock(getOffset(), label, valueOffset, op_);
//        } else {
//            exec_ = new ExecStdSwitchBlock(getOffset(), label, valueOffset, op_);
//        }
//        exec_.setFile(_page.getBlockToWrite().getFile());
//        _page.getBlockToWrite().appendChild(exec_);
//        _page.getAnalysisAss().getReachMappingBracedMembers().put(this,exec_);
//        _page.getCoverage().putBlockOperations(exec_,getInfo());
    }
    @Override
    public void abrupt(AnalyzingEl _anEl) {
        boolean abrupt_ = abruptCore(this,this,_anEl);
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

    static boolean abruptCore(ReachBracedBlock _braced, ReachAnalyzedSwitch _ana,AnalyzingEl _anEl) {
        ReachBlock ch_ = _braced.getFirstChild();
        if (ch_ == null) {
            return false;
        }
        boolean abrupt_ = true;
        boolean def_ = hasDefaultCase(_braced);
        if (!def_) {
            abrupt_ = false;
        } else if (_ana.isInstance()) {
            CustList<ReachBlock> group_ = new CustList<ReachBlock>();
            for (ReachBlock b: getDirectChildren(_braced)) {
                group_.add(b);
            }
            boolean canCmpNormally_ = canCmpNormally(_anEl, group_);
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
        return abrupt_;
    }

    private static boolean canCmpNormally(AnalyzingEl _anEl, CustList<ReachBlock> _group) {
        boolean canCmpNormally_ = false;
        for (ReachBlock b: _group) {
            if (_anEl.canCompleteNormally(b)) {
                canCmpNormally_ = true;
                break;
            }
        }
        return canCmpNormally_;
    }

    private static boolean hasDefaultCase(ReachBracedBlock _braced) {
        ReachBlock ch_ = _braced.getFirstChild();
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

    @Override
    public boolean isInstance() {
        return instance;
    }

}
