package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.ExpressionLanguage;
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

public abstract class ExecAbstractForEachLoop extends ExecBracedBlock implements ExecLoop, WithNotEmptyEl {

    private String label;

    private String importedClassName;

    private String importedClassIndexName;

    private final String variableName;

    private int variableNameOffset;

    private int expressionOffset;

    private CustList<ExecOperationNode> opList;

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
        StringMap<LocalVariable> vInfo_ = _ip.getValueVars();
        vInfo_.removeKey(variableName);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            ip_.processVisitedLoop(c_,this,this,_cont);
            return;
        }
        Struct its_ = processLoop(_cont);
        if (_cont.callsOrException()) {
            return;
        }
        LoopBlockStack l_ = newLoopBlockStack(_cont,label,its_);
        if (l_ == null) {
            return;
        }
        ip_.addBlock(l_);
        ip_.clearCurrentEls();
        l_.setEvaluatingKeepLoop(true);
        String className_ = _cont.formatVarType(importedClassName);
        Struct struct_ = ExecClassArgumentMatching.defaultValue(className_, _cont);
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        lv_.setIndexClassName(importedClassIndexName);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        varsLoop_.put(variableName, lv_);
        ip_.putValueVar(variableName, LocalVariable.newLocalVariable(struct_,className_));
        checkIfNext(_cont, l_);
    }

    private Struct processLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        ExpressionLanguage el_ = ip_.getCurrentEl(_conf, this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
        Argument arg_ = ExpressionLanguage.tryToCalculate(_conf,el_,0);
        if (_conf.callsOrException()) {
            return NullStruct.NULL_VALUE;
        }
        return arg_.getStruct();

    }
    @Override
    public void processLastElementLoop(ContextEl _conf, LoopBlockStack _l) {
        _l.setEvaluatingKeepLoop(true);
        ConditionReturn hasNext_ = hasNext(_conf,_l);
        if (hasNext_ == ConditionReturn.CALL_EX) {
            return;
        }
        incrOrFinish(_conf, hasNext_,_l);
    }
    protected void incrOrFinish(ContextEl _cont, ConditionReturn _hasNext, LoopBlockStack _l) {
        AbstractPageEl ip_ = _cont.getLastPage();
        if (_hasNext == ConditionReturn.NO) {
            ip_.clearCurrentEls();
            _cont.getCoverage().passLoop(_cont, this, new Argument(BooleanStruct.of(false)));
            _l.setEvaluatingKeepLoop(false);
            _l.setFinished(true);
            return;
        }
        _cont.getCoverage().passLoop(_cont, this, new Argument(BooleanStruct.of(true)));
        incrementLoop(_cont, _l);
    }
    private void incrementLoop(ContextEl _conf, LoopBlockStack _l) {
        _l.setIndex(_l.getIndex() + 1);
        AbstractPageEl abs_ = _conf.getLastPage();

        abs_.setGlobalOffset(variableNameOffset);
        abs_.setOffset(0);
        Argument arg_ = retrieveValue(_conf,_l);
        if (_conf.callsOrException()) {
            return;
        }
        abs_.clearCurrentEls();
        ExecTemplates.setValue(_conf, variableName, arg_,-1, abs_.getCache(), abs_.getValueVars());
        ExecTemplates.incrIndexLoop(_conf, variableName, -1, abs_.getCache(), abs_.getVars());
        if (_conf.callsOrException()) {
            return;
        }
        _l.setEvaluatingKeepLoop(false);
        abs_.setBlock(getFirstChild());
    }

    protected abstract void checkIfNext(ContextEl _cont, LoopBlockStack _l);

    protected abstract LoopBlockStack newLoopBlockStack(ContextEl _cont, String _label, Struct _its);

    protected abstract Argument retrieveValue(ContextEl _conf, LoopBlockStack _l);

    protected abstract ConditionReturn hasNext(ContextEl _conf, LoopBlockStack _l);

    protected CustList<ExecOperationNode> getOpList() {
        return opList;
    }
}
