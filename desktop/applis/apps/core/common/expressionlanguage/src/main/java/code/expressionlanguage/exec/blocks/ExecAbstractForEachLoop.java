package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;

public abstract class ExecAbstractForEachLoop extends ExecBracedBlock implements WithEl {

    private final String label;

    private final String importedClassName;

    private final String importedClassIndexName;

    private final String variableName;

    private final int variableNameOffset;

    private final ExecOperationNodeListOff expression;

    protected ExecAbstractForEachLoop(String _label, String _importedClassName, String _importedClassIndexName, String _variableName, int _variableNameOffset, int _expressionOffset, CustList<ExecOperationNode> _opList) {
        label = _label;
        this.importedClassName = _importedClassName;
        this.importedClassIndexName = _importedClassIndexName;
        this.variableName = _variableName;
        this.variableNameOffset = _variableNameOffset;
        expression = new ExecOperationNodeListOff(_opList,_expressionOffset);
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        v_.removeKey(variableName);
        _ip.removeRefVar(variableName);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.processForEach(_cont, _stack, label, expression, this);
    }

    protected String getImportedClassIndexName() {
        return importedClassIndexName;
    }

    public static void processLastElementLoop(ContextEl _conf, LoopBlockStack _l, StackCall _stack, ExecAbstractForEachLoop _loop) {
        _l.getContent().setEvaluatingKeepLoop(true);
        ConditionReturn hasNext_ = _loop.hasNext(_conf,_l, _stack);
        if (hasNext_ == ConditionReturn.CALL_EX) {
            return;
        }
        _loop.incrOrFinish(_conf, hasNext_,_l, _stack);
    }
    protected void incrOrFinish(ContextEl _cont, ConditionReturn _hasNext, LoopBlockStack _l, StackCall _stackCall) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        if (_hasNext == ConditionReturn.NO) {
            ip_.clearCurrentEls();
            _cont.getCoverage().passLoop(this, new Argument(BooleanStruct.of(false)), _stackCall);
            _l.getContent().setEvaluatingKeepLoop(false);
            _l.getContent().setFinished(true);
            return;
        }
        _cont.getCoverage().passLoop(this, new Argument(BooleanStruct.of(true)), _stackCall);
        incrementLoop(_cont, _l, _stackCall);
    }
    private void incrementLoop(ContextEl _conf, LoopBlockStack _l, StackCall _stackCall) {
        _l.getContent().setIndex(_l.getContent().getIndex() + 1);
        AbstractPageEl abs_ = _stackCall.getLastPage();

        abs_.globalOffset(variableNameOffset);
        Argument arg_ = retrieveValue(_conf,_l, _stackCall);
        if (_conf.callsOrException(_stackCall)) {
            return;
        }
        abs_.clearCurrentEls();
        ExecTemplates.setWrapValue(_conf, variableName, arg_,-1, abs_.getCache(), abs_.getRefParams(), _stackCall);
        ExecTemplates.incrIndexLoop(_conf, variableName, -1, abs_.getCache(), abs_.getVars(), _stackCall);
        if (_conf.callsOrException(_stackCall)) {
            return;
        }
        _l.getContent().setEvaluatingKeepLoop(false);
        abs_.setBlock(getFirstChild());
    }

    protected abstract void checkIfNext(ContextEl _cont, LoopBlockStack _l, StackCall _stack);

    protected abstract LoopBlockStack newLoopBlockStack(ContextEl _cont, String _label, Struct _its, StackCall _stack);

    protected abstract Argument retrieveValue(ContextEl _conf, LoopBlockStack _l, StackCall _stack);

    protected abstract ConditionReturn hasNext(ContextEl _conf, LoopBlockStack _l, StackCall _stack);

    protected String getVariableName() {
        return variableName;
    }

    protected String getImportedClassName() {
        return importedClassName;
    }
}
