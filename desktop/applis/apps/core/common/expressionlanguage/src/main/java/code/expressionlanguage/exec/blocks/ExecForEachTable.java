package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.util.StringMap;

public final class ExecForEachTable extends ExecBracedBlock implements WithEl {

    private final String label;

    private final ExecVariableName variableFirst;

    private final ExecVariableName variableSecond;

    private final int separator;

    private final String importedClassIndexName;

    private final ExecOperationNodeListOff expression;

    public ExecForEachTable(String _label, ExecVariableName _f, ExecVariableName _s, int _sep,String _importedClassIndexName,
                            ExecOperationNodeListOff _expression) {
        this.label = _label;
        this.variableFirst = _f;
        this.variableSecond = _s;
        this.separator = _sep;
        this.importedClassIndexName = _importedClassIndexName;
        expression = _expression;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processTable(_cont, _stack, label, variableFirst, variableSecond,expression, this);
    }

    public String getVariableNameFirst() {
        return variableFirst.getName();
    }

    public String getVariableNameSecond() {
        return variableSecond.getName();
    }

    public String getImportedClassIndexName() {
        return importedClassIndexName;
    }

    public ExecVariableName getVariableFirst() {
        return variableFirst;
    }

    public ExecVariableName getVariableSecond() {
        return variableSecond;
    }

    public int getSeparator() {
        return separator;
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        v_.removeKey(getVariableNameFirst());
        v_.removeKey(getVariableNameSecond());
        _ip.removeRefVar(getVariableNameFirst());
        _ip.removeRefVar(getVariableNameSecond());
    }

}
