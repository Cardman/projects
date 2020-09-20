package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.Line;
import code.expressionlanguage.assign.opers.AssOperationNode;
import code.expressionlanguage.assign.opers.AssUtil;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.util.CustList;

public final class AssSimLine extends AssLeaf implements AssBuildableElMethod {
    private CustList<AssOperationNode> opList;
    AssSimLine(boolean _completeNormally, boolean _completeNormallyGroup, Line _line) {
        super(_completeNormally,_completeNormallyGroup);
        opList = AssUtil.getSimExecutableNodes(_line.getRoot());
    }

    @Override
    public void buildExpressionLanguage(AssignedVariablesBlock _a, AnalyzedPageEl _page) {
        AssUtil.getSimSortedDescNodes(_a,opList.last(),this, _page);
        AssBlock pre_ = getPreviousSibling();
        if (pre_ instanceof AssSimDeclareVariable) {
            for (String v: ((AssSimDeclareVariable)pre_).getAssignedVariables()) {
                _a.getVariables().put(v,true);
            }
        }
    }

}
