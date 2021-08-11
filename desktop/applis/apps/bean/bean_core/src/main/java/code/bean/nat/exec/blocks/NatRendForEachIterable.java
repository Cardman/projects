package code.bean.nat.exec.blocks;

import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class NatRendForEachIterable extends NatRendAbstractForEachLoop {
    public NatRendForEachIterable(String _variable, int _expressionOffset, String _label, CustList<RendDynOperationNode> _res) {
        super(_variable, _expressionOffset, _label, _res);
    }

}
