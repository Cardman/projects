package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Throwing;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;

public final class ReachThrowing extends ReachAbruptBlock implements ReachBuildableElMethod {
    private final Throwing meta;
    private final OperationNode root;
    public ReachThrowing(Throwing _info) {
        super(_info);
        meta = _info;
        root = _info.getRoot();
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        _page.zeroOffset();
        _page.setSumOffset(meta.getExpressionOffset());
        ReachOperationUtil.tryCalculate(root, _page);
    }
}
