package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.*;
import code.util.CustList;

public final class ExecForEachArray extends ExecAbstractForEachLoop {
    public ExecForEachArray(String _label, String _importedClassName, String _importedClassIndexName, String _variableName, int _variableNameOffset, int _expressionOffset, CustList<ExecOperationNode> _opList) {
        super(_label, _importedClassName, _importedClassIndexName, _variableName, _variableNameOffset, _expressionOffset, _opList);
    }

    @Override
    protected void checkIfNext(ContextEl _cont, LoopBlockStack _l, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        String className_ = _stack.formatVarType(getImportedClassName());
        Struct struct_ = ExecClassArgumentMatching.defaultValue(className_, _cont);
        ip_.putValueVar(getVariableName(), LocalVariable.newLocalVariable(struct_,className_));
        incrOrFinish(_cont, hasNext(_cont,_l, _stack),_l, _stack);
    }

    @Override
    protected LoopBlockStack newLoopBlockStack(ContextEl _cont, String _label, Struct _its, StackCall _stack) {
        return ExecHelperBlocks.blockStackForArray(_cont, _label, _its, _stack, this);
    }

    @Override
    protected Argument retrieveValue(ContextEl _conf, LoopBlockStack _l, StackCall _stack) {
        Struct container_ = _l.getContent().getContainer();
        LongStruct lg_ = new LongStruct(_l.getContent().getIndex());
        return new Argument(ExecTemplates.getElement(container_, lg_, _conf, _stack));
    }

    @Override
    protected ConditionReturn hasNext(ContextEl _conf, LoopBlockStack _l, StackCall _stack) {
        return ExecHelperBlocks.hasNext(_l);
    }

    @Override
    public CustList<ExecOperationNode> getEl(ContextEl _context, int _indexProcess) {
        return getOpList();
    }
}
