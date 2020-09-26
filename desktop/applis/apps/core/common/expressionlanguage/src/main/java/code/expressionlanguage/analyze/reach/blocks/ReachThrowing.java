package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Throwing;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;

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
        ReachOperationUtil.tryCalculate(root, _page);
    }
}
