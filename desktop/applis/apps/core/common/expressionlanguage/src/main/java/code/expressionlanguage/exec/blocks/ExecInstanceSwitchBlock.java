package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ExecInstanceSwitchBlock extends ExecAbstractSwitchBlock {
    public ExecInstanceSwitchBlock(String _instanceTest, String _label, int _valueOffset, CustList<ExecOperationNode> _opValue) {
        super(_instanceTest,_label, _valueOffset, _opValue);
    }

    @Override
    protected ExecBracedBlock lastVis(ExecListLastBkSw _if, ExecResultCase _res) {
        return tryLastVisited(_if, _res);
    }

}
