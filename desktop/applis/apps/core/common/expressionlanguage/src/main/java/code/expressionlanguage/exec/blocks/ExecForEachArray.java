package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.Struct;

public final class ExecForEachArray extends ExecAbstractForEachLoop {
    public ExecForEachArray(String _label, String _importedClassIndexName, ExecVariableName _variableName, int _sep, ExecOperationNodeListOff _ex) {
        super(_label, _importedClassIndexName, _variableName, _sep, _ex);
    }

    @Override
    protected void checkIfNext(ContextEl _cont, LoopBlockStack _l, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        String className_ = _stack.formatVarType(getImportedClassName());
        Struct struct_ = ExecClassArgumentMatching.defaultValue(className_, _cont);
        ip_.putValueVar(getVariable().getName(), LocalVariable.newLocalVariable(struct_,className_));
        ExecHelperBlocks.incrOrFinishLoop(this,_cont, hasNext(_cont,_l, _stack),_l, _stack);
    }

    @Override
    protected LoopBlockStack newLoopBlockStack(ContextEl _cont, String _label, Struct _its, StackCall _stack) {
        return ExecHelperBlocks.blockStackForArray(_cont, _label, _its, _stack, this);
    }

    @Override
    protected Struct retrieveValue(ContextEl _conf, LoopBlockStack _l, StackCall _stack) {
        if (ExecHelperBlocks.checkBp(_stack,_stack.getLastPage(),this)) {
            return null;
        }
        incr(_l);
        Struct container_ = _l.getContent().getContainer();
        LongStruct lg_ = new LongStruct(_l.getContent().getIndex());
        return ExecArrayTemplates.getElement(container_, lg_, _conf, _stack);
    }

    private void incr(LoopBlockStack _l) {
        _l.getContent().setIndex(_l.getContent().getIndex() + 1);
    }

    @Override
    protected ConditionReturn hasNext(ContextEl _conf, LoopBlockStack _l, StackCall _stack) {
        return has(_l, _stack);
    }

}
