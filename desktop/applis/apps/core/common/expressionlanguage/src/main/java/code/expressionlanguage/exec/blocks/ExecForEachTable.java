package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.exec.Classes;
import code.util.CustList;
import code.util.StringMap;

public final class ExecForEachTable extends ExecBracedBlock implements StackableBlock, WithNotEmptyEl {

    private final String label;

    private final String importedClassNameFirst;

    private final String importedClassNameSecond;

    private final String importedClassIndexName;

    private final String variableNameFirst;

    private final String variableNameSecond;

    private final ExecOperationNodeListOff expression;

    public ExecForEachTable(String _label, String _importedClassNameFirst, String _importedClassNameSecond, String _importedClassIndexName,
                            String _variableNameFirst, String _variableNameSecond, ExecOperationNodeListOff _expression) {
        this.label = _label;
        this.importedClassNameFirst = _importedClassNameFirst;
        this.importedClassNameSecond = _importedClassNameSecond;
        this.importedClassIndexName = _importedClassIndexName;
        this.variableNameFirst = _variableNameFirst;
        this.variableNameSecond = _variableNameSecond;
        expression = _expression;
    }

    @Override
    public CustList<ExecOperationNode> getEl(ContextEl _context, int _indexProcess) {
        if (_indexProcess == 0) {
            return expression.getList();
        }
        Classes cls_ = _context.getClasses();
        if (_indexProcess == 1) {
            return cls_.getExpsIteratorTableCust();
        }
        if (_indexProcess == 2) {
            return cls_.getExpsHasNextPairCust();
        }
        if (_indexProcess == 3) {
            return cls_.getExpsNextPairCust();
        }
        if (_indexProcess == 4) {
            return cls_.getExpsFirstCust();
        }
        return cls_.getExpsSecondCust();
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processTable(_cont, _stack, label, variableNameFirst, variableNameSecond,expression, this);
    }

    public String getVariableNameFirst() {
        return variableNameFirst;
    }

    public String getVariableNameSecond() {
        return variableNameSecond;
    }

    public String getImportedClassNameFirst() {
        return importedClassNameFirst;
    }

    public String getImportedClassNameSecond() {
        return importedClassNameSecond;
    }

    public String getImportedClassIndexName() {
        return importedClassIndexName;
    }


    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        v_.removeKey(variableNameFirst);
        v_.removeKey(variableNameSecond);
        _ip.removeRefVar(variableNameFirst);
        _ip.removeRefVar(variableNameSecond);
    }

}
