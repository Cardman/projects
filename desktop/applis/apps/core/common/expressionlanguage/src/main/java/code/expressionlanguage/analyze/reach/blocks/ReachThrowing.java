package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Throwing;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;
import code.expressionlanguage.exec.blocks.ExecThrowing;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ReachThrowing extends ReachAbruptBlock {
    private Throwing meta;
    private OperationNode root;
    protected ReachThrowing(Throwing _info) {
        super(_info);
        meta = _info;
        root = _info.getRoot();
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.setOffset(0);
        _page.setGlobalOffset(meta.getExpressionOffset());
        CustList<ExecOperationNode> op_ = ReachOperationUtil.tryCalculateAndSupply(root, _page);
        ExecThrowing exec_ = new ExecThrowing(getOffset(), meta.getExpressionOffset(),op_);
        exec_.setFile(_page.getBlockToWrite().getFile());
        _page.getBlockToWrite().appendChild(exec_);
        _page.getCoverage().putBlockOperations(exec_,getInfo());
    }
}
