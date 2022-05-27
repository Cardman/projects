package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.reach.opers.ReachOperationUtil;

public final class ReachReturnMethod extends ReachAbruptBlock implements ReachBuildableElMethod {
    private final OperationNode root;
    private final int expressionOffset;

    public ReachReturnMethod(ReturnMethod _info) {
        super(_info);
        expressionOffset = _info.getExpressionOffset();
        root = _info.getRoot();
    }

    @Override
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        if (root == null) {
            return;
        }
        _page.setSumOffset(expressionOffset);
        _page.zeroOffset();
        ReachOperationUtil.tryCalculate(root, _page);
    }

}
