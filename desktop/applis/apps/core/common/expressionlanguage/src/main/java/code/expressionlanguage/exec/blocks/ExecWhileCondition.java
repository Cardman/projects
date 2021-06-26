package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.util.CustList;

public final class ExecWhileCondition extends ExecCondition implements StackableBlock {

    private final String label;
    public ExecWhileCondition(int _conditionOffset, String _label, CustList<ExecOperationNode> _opCondition) {
        super(_conditionOffset, _opCondition);
        label = _label;
    }

    public void processLastElementLoop(ContextEl _conf, LoopBlockStack _l, StackCall _stack) {
        ExecHelperBlocks.processLastElementLoopWhile(_conf, _l, _stack, this, getCondition());
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processWhile(_cont, _stack, label, this, getCondition());
    }


}
