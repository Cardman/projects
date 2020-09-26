package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;

public final class ReachReturnMethod extends ReachAbruptBlock {
    private OperationNode root;
    private int expressionOffset;

    protected ReachReturnMethod(ReturnMethod _info) {
        super(_info);
        expressionOffset = _info.getExpressionOffset();
        root = _info.getRoot();
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        if (root == null) {
            return;
        }
        _page.setGlobalOffset(expressionOffset);
        _page.setOffset(0);
        ReachOperationUtil.tryCalculate(root, _page);
    }

}
