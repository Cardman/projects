package code.expressionlanguage.assign.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.assign.opers.AssOperationNode;
import code.expressionlanguage.assign.opers.AssUtil;
import code.expressionlanguage.assign.util.*;
import code.util.CustList;

public final class AssSimBracedBlock extends AssBracedBlock implements AssBuildableElMethod {

    private CustList<AssOperationNode> ops;
    AssSimBracedBlock(boolean _completeNormally, boolean _completeNormallyGroup, OperationNode _root) {
        super(_completeNormally,_completeNormallyGroup);
        ops = AssUtil.getSimExecutableNodes(_root);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont, AssignedVariablesBlock _a) {
        if (ops.isEmpty()) {
            return;
        }
        AssUtil.getSimSortedDescNodes(_a,ops.last(),this,_cont);
    }
}
