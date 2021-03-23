package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;

public abstract class ExecAbstractForEachLoop extends ExecBracedBlock implements ExecLoop, WithNotEmptyEl,BuildingEl {

    private final String label;

    private final String importedClassName;

    private final String importedClassIndexName;

    private final String variableName;

    private final int variableNameOffset;

    private final int expressionOffset;

    private final CustList<ExecOperationNode> opList;

    protected ExecAbstractForEachLoop(String _label, String _importedClassName, String _importedClassIndexName, String _variableName, int _variableNameOffset, int _expressionOffset, CustList<ExecOperationNode> _opList, int _offsetTrim) {
        super(_offsetTrim);
        label = _label;
        this.importedClassName = _importedClassName;
        this.importedClassIndexName = _importedClassIndexName;
        this.variableName = _variableName;
        this.variableNameOffset = _variableNameOffset;
        this.expressionOffset = _expressionOffset;
        opList = _opList;
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
        AbstractPageEl ip_ = _stack.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            ip_.processVisitedLoop(c_,this,this,_cont, _stack);
            return;
        }
        Struct its_ = processLoop(_cont, _stack);
        if (_cont.callsOrException(_stack)) {
            return;
        }
        LoopBlockStack l_ = newLoopBlockStack(_cont,label,its_, _stack);
        if (l_ == null) {
            return;
        }
        ip_.addBlock(l_);
        ip_.clearCurrentEls();
        l_.setEvaluatingKeepLoop(true);
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        lv_.setIndexClassName(importedClassIndexName);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        varsLoop_.put(variableName, lv_);
        checkIfNext(_cont, l_, _stack);
    }

    private Struct processLoop(ContextEl _conf, StackCall _stackCall) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        ExpressionLanguage el_ = ip_.getCurrentEl(_conf, this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
        Argument arg_ = ExpressionLanguage.tryToCalculate(_conf,el_,0, _stackCall);
        if (_conf.callsOrException(_stackCall)) {
            return NullStruct.NULL_VALUE;
        }
        return arg_.getStruct();

    }
    @Override
    public void processLastElementLoop(ContextEl _conf, LoopBlockStack _l, StackCall _stack) {
        _l.setEvaluatingKeepLoop(true);
        ConditionReturn hasNext_ = hasNext(_conf,_l, _stack);
        if (hasNext_ == ConditionReturn.CALL_EX) {
            return;
        }
        incrOrFinish(_conf, hasNext_,_l, _stack);
    }
    protected void incrOrFinish(ContextEl _cont, ConditionReturn _hasNext, LoopBlockStack _l, StackCall _stackCall) {
        AbstractPageEl ip_ = _stackCall.getLastPage();
        if (_hasNext == ConditionReturn.NO) {
            ip_.clearCurrentEls();
            _cont.getCoverage().passLoop(this, new Argument(BooleanStruct.of(false)), _stackCall);
            _l.setEvaluatingKeepLoop(false);
            _l.setFinished(true);
            return;
        }
        _cont.getCoverage().passLoop(this, new Argument(BooleanStruct.of(true)), _stackCall);
        incrementLoop(_cont, _l, _stackCall);
    }
    private void incrementLoop(ContextEl _conf, LoopBlockStack _l, StackCall _stackCall) {
        _l.setIndex(_l.getIndex() + 1);
        AbstractPageEl abs_ = _stackCall.getLastPage();

        abs_.setGlobalOffset(variableNameOffset);
        abs_.setOffset(0);
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
        _l.setEvaluatingKeepLoop(false);
        abs_.setBlock(getFirstChild());
    }

    protected abstract void checkIfNext(ContextEl _cont, LoopBlockStack _l, StackCall _stack);

    protected abstract LoopBlockStack newLoopBlockStack(ContextEl _cont, String _label, Struct _its, StackCall _stack);

    protected abstract Argument retrieveValue(ContextEl _conf, LoopBlockStack _l, StackCall _stack);

    protected abstract ConditionReturn hasNext(ContextEl _conf, LoopBlockStack _l, StackCall _stack);

    protected CustList<ExecOperationNode> getOpList() {
        return opList;
    }

    protected String getVariableName() {
        return variableName;
    }

    protected String getImportedClassName() {
        return importedClassName;
    }
}
