package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.core.IndexConstants;

public final class ExecForMutableIterativeLoop extends ExecBracedBlock implements ExecLoop, WithNotEmptyEl {

    private String label;

    private final String importedClassName;

    private String importedClassIndexName;

    private final StringList variableNames;

    private int initOffset;

    private int expressionOffset;

    private int stepOffset;

    private CustList<ExecOperationNode> opInit;

    private CustList<ExecOperationNode> opExp;

    private CustList<ExecOperationNode> opStep;

    public ExecForMutableIterativeLoop(String _label, String _importedClassName, String _importedClassIndexName, StringList _variableNames,
                                       int _initOffset, int _expressionOffset, int _stepOffset,
                                       CustList<ExecOperationNode> _opInit, CustList<ExecOperationNode> _opExp, CustList<ExecOperationNode> _opStep, int _offsetTrim) {
        super(_offsetTrim);
        this.label = _label;
        this.importedClassName = _importedClassName;
        this.importedClassIndexName = _importedClassIndexName;
        this.variableNames = _variableNames;
        this.initOffset = _initOffset;
        this.expressionOffset = _expressionOffset;
        this.stepOffset = _stepOffset;
        this.opInit = _opInit;
        this.opExp = _opExp;
        this.opStep = _opStep;
    }

    @Override
    public void processLastElementLoop(ContextEl _conf, LoopBlockStack _l) {
        AbstractPageEl ip_ = _conf.getLastPage();
        _l.setEvaluatingKeepLoop(true);
        int index_ = 0;
        ip_.setOffset(0);
        ip_.setGlobalOffset(stepOffset);
        if (!opStep.isEmpty()) {
            ExpressionLanguage from_ = ip_.getCurrentEl(_conf,this, IndexConstants.FIRST_INDEX, 2);
            ExpressionLanguage.tryToCalculate(_conf,from_,0);
            if (_conf.callsOrException()) {
                return;
            }
            index_++;
        }
        if (ip_.sizeEl() <= index_) {
            for (String v : variableNames) {
                ExecTemplates.incrIndexLoop(_conf,v,ip_, -1);
            }
        }
        ConditionReturn keep_ = evaluateCondition(_conf, index_);
        if (keep_ == ConditionReturn.CALL_EX) {
            return;
        }
        if (keep_ == ConditionReturn.NO) {
            _l.setFinished(true);
        }
        _l.setEvaluatingKeepLoop(false);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        if (_indexProcess == 0) {
            return new ExpressionLanguage(opInit);
        }
        if (_indexProcess == 1) {
            return new ExpressionLanguage(opExp);
        }
        return new ExpressionLanguage(opStep);
    }

    @Override
    public void reduce(ContextEl _context) {
        if (!opInit.isEmpty()) {
            ExecOperationNode i_ = opInit.last();
            opInit = ExpressionLanguage.getReducedNodes(i_);
        }
        if (!opExp.isEmpty()) {
            ExecOperationNode e_ = opExp.last();
            opExp = ExpressionLanguage.getReducedNodes(e_);
        }
        if (!opStep.isEmpty()) {
            ExecOperationNode s_ = opStep.last();
            opStep = ExpressionLanguage.getReducedNodes(s_);
        }
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            ip_.processVisitedLoop(c_,this,this,_cont);
            return;
        }
        ip_.setGlobalOffset(initOffset);
        ip_.setOffset(0);
        int index_ = 0;
        if (ip_.isEmptyEl()) {
            String formatted_ = _cont.formatVarType(importedClassName);
            Struct struct_ = ExecClassArgumentMatching.defaultValue(formatted_, _cont);
            for (String v: variableNames) {
                LoopVariable lv_ = new LoopVariable();
                lv_.setIndexClassName(importedClassIndexName);
                ip_.getVars().put(v, lv_);
                ip_.putValueVar(v, LocalVariable.newLocalVariable(struct_,formatted_));
            }
        }
        if (!opInit.isEmpty()) {
            ExpressionLanguage from_ = ip_.getCurrentEl(_cont,this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
            ExpressionLanguage.tryToCalculate(_cont,from_,0);
            index_++;
        }
        ConditionReturn res_ = evaluateCondition(_cont, index_);
        if (res_ == ConditionReturn.CALL_EX) {
            return;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(label);
        l_.setExecBlock(this);
        l_.setExecLoop(this);
        l_.setCurrentVisitedBlock(this);
        boolean finished_ = res_ == ConditionReturn.NO;
        l_.setFinished(finished_);
        ip_.addBlock(l_);
        if (finished_) {
            processBlockAndRemove(_cont);
            return;
        }
        ip_.setBlock(getFirstChild());
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        for (String v: variableNames) {
            _ip.getVars().removeKey(v);
            _ip.getValueVars().removeKey(v);
        }
    }

    private ConditionReturn evaluateCondition(ContextEl _context, int _index) {
        if (_context.callsOrException()) {
            return ConditionReturn.CALL_EX;
        }
        AbstractPageEl last_ = _context.getLastPage();
        if (opExp.isEmpty()) {
            last_.clearCurrentEls();
            return ConditionReturn.YES;
        }
        ExpressionLanguage exp_ = last_.getCurrentEl(_context,this, _index, IndexConstants.SECOND_INDEX);
        last_.setOffset(0);
        last_.setGlobalOffset(expressionOffset);
        Argument arg_ = ExpressionLanguage.tryToCalculate(_context,exp_,0);
        if (_context.callsOrException()) {
            return ConditionReturn.CALL_EX;
        }
        last_.clearCurrentEls();
        _context.getCoverage().passConditions(_context, this, arg_,opExp.last());
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

}
