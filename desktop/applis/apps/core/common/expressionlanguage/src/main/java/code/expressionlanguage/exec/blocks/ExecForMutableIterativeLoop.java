package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;
import code.util.StringList;

public final class ExecForMutableIterativeLoop extends ExecBracedBlock implements StackableBlock, WithNotEmptyEl {

    private final String label;

    private final String importedClassName;

    private final String importedClassIndexName;

    private final StringList variableNames;

    private final ExecOperationNodeListOff init;
    private final ExecOperationNodeListOff exp;
    private final ExecOperationNodeListOff step;

    public ExecForMutableIterativeLoop(String _label, String _importedClassName, String _importedClassIndexName, StringList _variableNames,
                                       ExecOperationNodeListOff _init, ExecOperationNodeListOff _exp, ExecOperationNodeListOff _step) {
        this.label = _label;
        init = _init;
        exp = _exp;
        step = _step;
        this.importedClassName = _importedClassName;
        this.importedClassIndexName = _importedClassIndexName;
        this.variableNames = _variableNames;
    }

    @Override
    public CustList<ExecOperationNode> getEl(ContextEl _context, int _indexProcess) {
        return ExecHelperBlocks.elFor(_indexProcess, init, exp, step);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processMutableFor(_cont, _stack, init, exp, label, variableNames, this);
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
        for (String v: variableNames) {
            _ip.getVars().removeKey(v);
            _ip.removeRefVar(v);
        }
    }

    public StringList getVariableNames() {
        return variableNames;
    }

    public ExecOperationNodeListOff getExp() {
        return exp;
    }

    public ExecOperationNodeListOff getStep() {
        return step;
    }
}
