package code.formathtml.exec;

import code.expressionlanguage.analyze.opers.LeafOperation;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;

public abstract class RendLeafOperation extends RendDynOperationNode {

    RendLeafOperation(LeafOperation _l) {
        super(_l);
    }

    RendLeafOperation(int _indexChild, ExecClassArgumentMatching _res, int _order) {
        super(_indexChild,_res,_order);
    }
    @Override
    public RendDynOperationNode getFirstChild() {
        return null;
    }
}
