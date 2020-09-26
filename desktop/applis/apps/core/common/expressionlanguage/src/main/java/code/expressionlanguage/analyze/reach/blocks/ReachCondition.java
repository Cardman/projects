package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Condition;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;

public abstract class ReachCondition extends ReachBracedBlock implements ReachBuildableElMethod {

    private Condition meta;
    private OperationNode root;
    private int conditionOffset;

    protected ReachCondition(Condition _info) {
        super(_info);
        meta = _info;
        root = _info.getRoot();
        conditionOffset = _info.getConditionOffset();
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.setGlobalOffset(conditionOffset);
        _page.setOffset(0);
        meta.setArgument(ReachOperationUtil.tryCalculate(root, _page));
//        CustList<ExecOperationNode> opCondition_ = ElUtil.getExecutableNodes(_page, root);
//        ExecCondition exec_ = newCondition(conditionOffset, opCondition_);
//        exec_.setFile(_page.getBlockToWrite().getFile());
//        _page.getBlockToWrite().appendChild(exec_);
//        _page.getAnalysisAss().getReachMappingBracedMembers().put(this,exec_);
//        _page.getCoverage().putBlockOperationsConditions(getInfo());
//        _page.getCoverage().putBlockOperations(exec_,getInfo());
    }

    public Argument getArgument() {
        return meta.getArgument();
    }
}
