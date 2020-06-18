package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.assign.opers.AssOperationNode;
import code.expressionlanguage.assign.opers.AssUtil;
import code.expressionlanguage.assign.util.AssignedVariablesBlock;
import code.expressionlanguage.exec.blocks.ExecLine;
import code.expressionlanguage.methods.Line;
import code.util.CustList;

public final class AssLine extends AssLeaf implements AssBuildableElMethod {
    private CustList<AssOperationNode> opList;
    AssLine(boolean _completeNormally, boolean _completeNormallyGroup, ExecLine _line) {
        super(_completeNormally,_completeNormallyGroup);
        opList = AssUtil.getExecutableNodes(_line.getExp());
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont, AssignedVariablesBlock _a) {
        AssUtil.getSortedDescNodes(_a,opList.last(),this,_cont);
    }
}
