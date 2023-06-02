package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.util.StringMap;

public abstract class ExecForIterativeLoop extends ExecBracedBlock implements WithEl {

    private final String label;

    private final String importedClassIndexName;

    private final ExecVariableName variable;

    private final ExecOperationNodeListOff init;
    private final ExecOperationNodeListOff exp;
    private final ExecOperationNodeListOff step;

    protected ExecForIterativeLoop(String _label, String _importedClassIndexName, ExecVariableName _variable,
                                ExecOperationNodeListOff _init, ExecOperationNodeListOff _exp, ExecOperationNodeListOff _step) {
        this.label = _label;
        this.importedClassIndexName = _importedClassIndexName;
        this.variable = _variable;
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

    public ExecVariableName getVariable() {
        return variable;
    }

    public String getImportedClassName() {
        return getVariable().getType();
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
        return getVariable().getName();
    }

}
