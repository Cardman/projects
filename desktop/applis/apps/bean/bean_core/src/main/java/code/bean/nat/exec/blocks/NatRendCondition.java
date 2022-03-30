package code.bean.nat.exec.blocks;

import code.formathtml.exec.blocks.RendOperationNodeListOff;
import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public abstract class NatRendCondition extends RendParentBlock implements NatRendWithEl {


    private final RendOperationNodeListOff condition;

    NatRendCondition(CustList<RendDynOperationNode> _op, int _offset) {
        condition = new RendOperationNodeListOff(_op,_offset);
    }

    RendOperationNodeListOff getCondition() {
        return condition;
    }

}
