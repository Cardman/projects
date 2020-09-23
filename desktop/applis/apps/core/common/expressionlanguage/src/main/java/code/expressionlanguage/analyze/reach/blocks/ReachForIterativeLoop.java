package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnalyzingEl;
import code.expressionlanguage.analyze.blocks.ForIterativeLoop;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.exec.blocks.ExecForIterativeLoop;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public class ReachForIterativeLoop extends ReachBracedBlock implements ReachLoop {
    private ForIterativeLoop meta;
    private String label;
    protected ReachForIterativeLoop(ForIterativeLoop _info) {
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
        OperationNode rInit_ = meta.getRootInit();
        ReachOperationUtil.tryCalculate(rInit_, _page);
        _page.setGlobalOffset(meta.getExpressionOffset());
        _page.setOffset(0);
        OperationNode rExp_ = meta.getRootExp();
        ReachOperationUtil.tryCalculate(rExp_, _page);
        _page.setGlobalOffset(meta.getStepOffset());
        _page.setOffset(0);
        OperationNode rStep_ = meta.getRootStep();
        ReachOperationUtil.tryCalculate(rStep_, _page);
//        _page.getCoverage().putBlockOperationsLoops(getInfo());
//        CustList<ExecOperationNode> init_ = ElUtil.getExecutableNodes(_page, rInit_);
//        CustList<ExecOperationNode> exp_ = ElUtil.getExecutableNodes(_page, rExp_);
//        CustList<ExecOperationNode> step_ = ElUtil.getExecutableNodes(_page, rStep_);
//        ExecForIterativeLoop exec_ = new ExecForIterativeLoop(getOffset(),label, meta.getImportedClassName(),
//                meta.getImportedClassIndexName(), meta.getVariableName(), meta.getVariableNameOffset(), meta.getInitOffset(),
//                meta.getExpressionOffset(), meta.getStepOffset(), meta.isEq(),init_,exp_,step_);
//        exec_.setFile(_page.getBlockToWrite().getFile());
//        _page.getBlockToWrite().appendChild(exec_);
//        _page.getAnalysisAss().getReachMappingBracedMembers().put(this,exec_);
//        _page.getCoverage().putBlockOperations(exec_,getInfo());
    }

    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }
}
