package code.expressionlanguage.analyze.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Line;
import code.expressionlanguage.analyze.assign.opers.AssOperationNode;
import code.expressionlanguage.analyze.assign.opers.AssUtil;
import code.expressionlanguage.analyze.assign.util.AssignedVariablesBlock;
import code.util.CustList;
import code.util.core.BoolVal;

public final class AssSimLine extends AssLeaf implements AssBuildableElMethod {
    private final int expressionOffset;
    private final CustList<AssOperationNode> opList;
    AssSimLine(boolean _completeNormally, boolean _completeNormallyGroup, Line _line) {
        super(_completeNormally,_completeNormallyGroup);
        opList = AssUtil.getSimExecutableNodes(_line.getRoot());
        expressionOffset = _line.getExpressionOffset();
    }

    @Override
    public void buildExpressionLanguage(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        _page.setGlobalOffset(expressionOffset);
        _page.zeroOffset();
        AssUtil.getSimSortedDescNodes(_a,opList.last(),this, _page);
        AssBlock pre_ = getPreviousSibling();
        if (pre_ instanceof AssSimDeclareVariable) {
            for (String v: ((AssSimDeclareVariable)pre_).getAssignedVariables()) {
                _a.getVariables().put(v, BoolVal.TRUE);
            }
        }
    }

}
