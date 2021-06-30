package code.formathtml.exec.blocks;

import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public abstract class RendCondition extends RendParentBlock implements RendWithEl {


    private final RendOperationNodeListOff condition;

    RendCondition(CustList<RendDynOperationNode> _op, int _offset) {
        condition = new RendOperationNodeListOff(_op,_offset);
    }

    RendOperationNodeListOff getCondition() {
        return condition;
    }

}
