package code.expressionlanguage.exec.opers;

import code.expressionlanguage.analyze.opers.LeafOperation;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;

public abstract class ExecLeafOperation extends ExecOperationNode {

    ExecLeafOperation(LeafOperation _l) {
        super(_l);
    }

    ExecLeafOperation(int _indexChild, ExecClassArgumentMatching _res, int _order) {
        super(_indexChild,_res,_order);
    }
    @Override
    public final ExecOperationNode getFirstChild() {
        return null;
    }
}
