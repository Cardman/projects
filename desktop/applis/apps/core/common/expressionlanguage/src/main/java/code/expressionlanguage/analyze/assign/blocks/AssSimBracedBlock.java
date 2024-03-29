package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.assign.opers.AssOperationNode;
import code.expressionlanguage.analyze.assign.opers.AssUtil;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.CustList;

public final class AssSimBracedBlock extends AssBracedBlock implements AssSimBuildableElMethod {

    private final CustList<AssOperationNode> ops;
    private final int offset;
    public AssSimBracedBlock(boolean _completeNormally, boolean _completeNormallyGroup, OperationNode _root, int _off) {
        super(_completeNormally,_completeNormallyGroup);
        ops = AssUtil.getSimExecutableNodes(_root);
        offset = _off;
    }

    @Override
    public void buildExpressionLanguage(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        if (ops.isEmpty()) {
            return;
        }
        _page.setSumOffset(offset);
        _page.zeroOffset();
        AssUtil.getSimSortedDescNodes(_a,ops.last(),this, _page);
    }
}
