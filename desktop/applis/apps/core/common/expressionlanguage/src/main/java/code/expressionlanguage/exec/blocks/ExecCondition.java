package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public abstract class ExecCondition extends ExecBracedBlock implements WithNotEmptyEl {


    private final ExecOperationNodeListOff condition;

    ExecCondition(int _conditionOffset, CustList<ExecOperationNode> _opCondition) {
        condition = new ExecOperationNodeListOff(_opCondition,_conditionOffset);
    }

    protected ExecOperationNodeListOff getCondition() {
        return condition;
    }

}
