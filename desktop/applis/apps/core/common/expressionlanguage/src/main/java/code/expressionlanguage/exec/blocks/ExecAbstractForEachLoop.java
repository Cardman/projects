package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public abstract class ExecAbstractForEachLoop extends ExecBracedBlock implements WithEl {

    private final String label;

    private final int separator;
    private final String importedClassIndexName;

    private final ExecVariableName variable;
    private final ExecOperationNodeListOff expression;

    protected ExecAbstractForEachLoop(String _label, String _importedClassIndexName, ExecVariableName _variableName, int _sep, ExecOperationNodeListOff _ex) {
        label = _label;
        this.importedClassIndexName = _importedClassIndexName;
        this.variable = _variableName;
        this.separator = _sep;
        expression = _ex;
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        v_.removeKey(variable.getName());
        _ip.removeRefVar(variable.getName());
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processForEach(_cont, _stack, label, expression, this);
    }

    protected String getImportedClassIndexName() {
        return importedClassIndexName;
    }

    protected abstract void checkIfNext(ContextEl _cont, LoopBlockStack _l, StackCall _stack);

    protected abstract LoopBlockStack newLoopBlockStack(ContextEl _cont, String _label, Struct _its, StackCall _stack);

    protected abstract Struct retrieveValue(ContextEl _conf, LoopBlockStack _l, StackCall _stack);

    protected abstract ConditionReturn hasNext(ContextEl _conf, LoopBlockStack _l, StackCall _stack);

    public int getSeparator() {
        return separator;
    }

    public ExecVariableName getVariable() {
        return variable;
    }

    protected ConditionReturn has(LoopBlockStack _l, StackCall _stack) {
        ConditionReturn c_ = ExecHelperBlocks.hasNext(_l);
        if (c_ == ConditionReturn.NO) {
            AbstractPageEl abs_ = _stack.getLastPage();
            abs_.globalOffset(getVariable().getOffset());
            if (ExecHelperBlocks.checkBp(_stack,abs_,this)) {
                return ConditionReturn.CALL_EX;
            }
        }
        return c_;
    }
    protected String getImportedClassName() {
        return variable.getType();
    }
}
