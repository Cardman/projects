package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ExecForEachIterable extends ExecAbstractForEachLoop {
    public ExecForEachIterable(String _label, String _importedClassName, String _importedClassIndexName, String _variableName, int _variableNameOffset, int _expressionOffset, CustList<ExecOperationNode> _opList) {
        super(_label, _importedClassName, _importedClassIndexName, _variableName, _variableNameOffset, _expressionOffset, _opList);
    }

    @Override
    protected void checkIfNext(ContextEl _cont, LoopBlockStack _l, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        String className_ = _stack.formatVarType(getImportedClassName());
        Struct struct_ = ExecClassArgumentMatching.defaultValue(className_, _cont);
        ip_.putValueVar(getVariableName(), LocalVariable.newLocalVariable(struct_,className_));
        iteratorHasNext(_cont, _l, _stack);
    }

    @Override
    protected LoopBlockStack newLoopBlockStack(ContextEl _cont, String _label, Struct _its, StackCall _stack) {
        return ExecHelperBlocks.newLoopBlockStackIt(_cont, _label, _its, _stack, this);
    }

    @Override
    protected Argument retrieveValue(ContextEl _conf, LoopBlockStack _l, StackCall _stack) {
        return ExecHelperBlocks.retrieveValueIt(_conf, _l, _stack, this);
    }

    @Override
    protected ConditionReturn hasNext(ContextEl _conf, LoopBlockStack _l, StackCall _stack) {
        return iteratorHasNext(_conf, _l, _stack);
    }

    @Override
    public CustList<ExecOperationNode> getEl(ContextEl _context, int _indexProcess) {
        if (_indexProcess == 0) {
            return getOpList();
        }
        if (_indexProcess == 1) {
            return _context.getClasses().getExpsIteratorCust();
        }
        if (_indexProcess == 2) {
            return _context.getClasses().getExpsHasNextCust();
        }
        return _context.getClasses().getExpsNextCust();
    }
    private ConditionReturn iteratorHasNext(ContextEl _conf, LoopBlockStack _l, StackCall _stackCall) {
        String locName_ = _conf.getClasses().getHasNextVarCust();
        return ExecHelperBlocks.hasNext(_conf, _l, _stackCall, locName_, this);
    }

}
