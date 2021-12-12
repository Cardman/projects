package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassArgumentMatching;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public abstract class ExecForIterativeLoop extends ExecBracedBlock implements WithEl {

    private final String label;

    private final String importedClassName;

    private final String importedClassIndexName;

    private final String variableName;

    private final ExecOperationNodeListOff init;
    private final ExecOperationNodeListOff exp;
    private final ExecOperationNodeListOff step;

    protected ExecForIterativeLoop(String _label, String _importedClassName, String _importedClassIndexName, String _variableName,
                                ExecOperationNodeListOff _init, ExecOperationNodeListOff _exp, ExecOperationNodeListOff _step) {
        this.label = _label;
        this.importedClassName = _importedClassName;
        this.importedClassIndexName = _importedClassIndexName;
        this.variableName = _variableName;
        init = _init;
        exp = _exp;
        step = _step;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processIterative(_cont, _stack, label, init, exp, step, this);
    }

    public String getImportedClassIndexName() {
        return importedClassIndexName;
    }

    public String getImportedClassName() {
        return importedClassName;
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        String var_ = getVariableName();
        v_.removeKey(var_);
        _ip.removeRefVar(var_);
    }

    public String getVariableName() {
        return variableName;
    }

}
