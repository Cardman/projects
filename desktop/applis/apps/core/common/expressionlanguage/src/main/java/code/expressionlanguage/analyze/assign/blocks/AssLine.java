package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Line;
import code.expressionlanguage.analyze.assign.opers.AssOperationNode;
import code.expressionlanguage.analyze.assign.opers.AssUtil;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.util.CustList;

public final class AssLine extends AssLeaf implements AssBuildableElMethod {
    private CustList<AssOperationNode> opList;
    private boolean callingThis;
    AssLine(boolean _completeNormally, boolean _completeNormallyGroup, Line _line) {
        super(_completeNormally,_completeNormallyGroup);
        opList = AssUtil.getExecutableNodes(_line.getRoot());
        callingThis = _line.isCallThis();
    }

    @Override
    public void buildExpressionLanguage(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssUtil.getSortedDescNodes(_a,opList.last(),this, callingThis, _page);
    }
}
