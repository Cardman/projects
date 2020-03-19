package code.formathtml.exec;

import code.expressionlanguage.opers.LeafOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;

public abstract class RendLeafOperation extends RendDynOperationNode {

    RendLeafOperation(LeafOperation _l) {
        super(_l);
    }

    RendLeafOperation(int _indexChild, ClassArgumentMatching _res, int _order) {
        super(_indexChild,_res,_order);
    }
    @Override
    public RendDynOperationNode getFirstChild() {
        return null;
    }
}
