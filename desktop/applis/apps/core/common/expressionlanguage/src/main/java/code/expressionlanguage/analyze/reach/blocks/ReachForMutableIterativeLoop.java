package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.ForMutableIterativeLoop;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.exec.blocks.ExecForMutableIterativeLoop;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;

public final class ReachForMutableIterativeLoop extends ReachBracedBlock implements ReachLoop {

    private ForMutableIterativeLoop meta;
    private String label;
    protected ReachForMutableIterativeLoop(ForMutableIterativeLoop _info) {
        super(_info);
        meta = _info;
        label = _info.getLabel();
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.setGlobalOffset(meta.getInitOffset());
        _page.setOffset(0);
        CustList<ExecOperationNode> init_;
        if (meta.getRootInit() == null) {
            init_ = new CustList<ExecOperationNode>();
        } else {
            init_ = ReachOperationUtil.tryCalculateAndSupply(meta.getRootInit(), _page);
        }
        _page.setGlobalOffset(meta.getExpressionOffset());
        _page.setOffset(0);
        CustList<ExecOperationNode> exp_;
        if (meta.getRootExp() == null) {
            exp_ = new CustList<ExecOperationNode>();
            meta.setAlwaysTrue(true);
        } else {
            exp_ = ReachOperationUtil.tryCalculateAndSupply(meta.getRootExp(), _page);
            ExecOperationNode l_ = exp_.last();
            meta.setArgument(l_.getArgument());
//            ElUtil.setImplicits(l_, _page, meta.getRootExp());
        }
        _page.getCoverage().putBlockOperationsConditions(getInfo());
        _page.setGlobalOffset(meta.getStepOffset());
        _page.setOffset(0);
        CustList<ExecOperationNode> step_;
        if (meta.getRootStep() == null) {
            step_ = new CustList<ExecOperationNode>();
        } else {
            step_ = ReachOperationUtil.tryCalculateAndSupply(meta.getRootStep(), _page);
        }
        ExecForMutableIterativeLoop exec_ = new ExecForMutableIterativeLoop(getOffset(),label, meta.getImportedClassName(), meta.getImportedClassIndexName(),
                meta.getVariableNames(), meta.getInitOffset(), meta.getExpressionOffset(), meta.getStepOffset(),
                init_,exp_,step_);
        exec_.setFile(_page.getBlockToWrite().getFile());
        _page.getBlockToWrite().appendChild(exec_);
        _page.getAnalysisAss().getReachMappingBracedMembers().put(this,exec_);
        _page.getCoverage().putBlockOperations(exec_,getInfo());
    }

    @Override
    public boolean accessibleCondition() {
        if (meta.isAlwaysTrue()) {
            return true;
        }
        Argument arg_ = meta.getArgument();
        return Argument.isNotFalseValue(arg_);
    }
    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        boolean abr_ = true;
        boolean proc_ = true;
        if (!meta.isAlwaysTrue()) {
            Argument arg_ = meta.getArgument();
            proc_ = Argument.isTrueValue(arg_);
        }
        if (_anEl.isReachable(this)) {
            if (!proc_) {
                abr_ = false;
            }
        }
        IdMap<ReachBreakBlock, ReachBreakableBlock> breakables_;
        breakables_ = _anEl.getReachBreakables();
        for (EntryCust<ReachBreakBlock, ReachBreakableBlock> e: breakables_.entryList()) {
            if (e.getValue() == this && _anEl.isReachable(e.getKey())) {
                abr_ = false;
                break;
            }
        }
        if (abr_) {
            _anEl.completeAbruptGroup(this);
        }
    }

}
