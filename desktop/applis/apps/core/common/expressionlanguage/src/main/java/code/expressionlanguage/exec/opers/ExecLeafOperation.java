package code.expressionlanguage.exec.opers;

import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.fwd.opers.ExecOperationContent;

public abstract class ExecLeafOperation extends ExecOperationNode {

    ExecLeafOperation(ExecOperationContent _l) {
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
