package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;

public final class ExecForIterativeLoop extends ExecBracedBlock implements ExecLoop, WithNotEmptyEl {

    private String label;

    private String importedClassName;

    private String importedClassIndexName;

    private final String variableName;
    private int variableNameOffset;

    private int initOffset;

    private int expressionOffset;

    private int stepOffset;

    private final boolean eq;

    private CustList<ExecOperationNode> opInit;

    private CustList<ExecOperationNode> opExp;

    private CustList<ExecOperationNode> opStep;

    public ExecForIterativeLoop(String _label, String _importedClassName, String _importedClassIndexName, String _variableName, int _variableNameOffset,
                                int _initOffset, int _expressionOffset, int _stepOffset, boolean _eq,
                                CustList<ExecOperationNode> _opInit, CustList<ExecOperationNode> _opExp, CustList<ExecOperationNode> _opStep, int _offsetTrim) {
        super(_offsetTrim);
        this.label = _label;
        this.importedClassName = _importedClassName;
        this.importedClassIndexName = _importedClassIndexName;
        this.variableName = _variableName;
        this.variableNameOffset = _variableNameOffset;
        this.initOffset = _initOffset;
        this.expressionOffset = _expressionOffset;
        this.stepOffset = _stepOffset;
        this.eq = _eq;
        this.opInit = _opInit;
        this.opExp = _opExp;
        this.opStep = _opStep;
    }

    @Override
    public void processLastElementLoop(ContextEl _conf, LoopBlockStack _l) {
        if (_l.hasNext()) {
            incrementLoop(_conf, _l);
            _conf.getCoverage().passLoop(_conf, this, new Argument(BooleanStruct.of(true)));
            return;
        }
        _l.setFinished(true);
        _conf.getCoverage().passLoop(_conf, this, new Argument(BooleanStruct.of(false)));

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
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            ip_.processVisitedLoop(c_,this,this,_cont);
            return;
        }
        LoopBlockStack l_ = processLoop(_cont);
        if (l_ == null) {
            return;
        }
        c_ = l_;
        if (c_.isFinished()) {
            _cont.getCoverage().passLoop(_cont, this, new Argument(BooleanStruct.of(false)));
            processBlockAndRemove(_cont);
            return;
        }
        _cont.getCoverage().passLoop(_cont, this, new Argument(BooleanStruct.of(true)));
        ip_.setBlock(getFirstChild());
    }
    private LoopBlockStack processLoop(ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
        String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
        AbstractPageEl ip_ = _conf.getLastPage();
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String var_ = getVariableName();

        boolean eq_ = isEq();
        ip_.setGlobalOffset(initOffset);
        ip_.setOffset(0);
        ExpressionLanguage from_ = ip_.getCurrentEl(_conf,this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
        Argument argFrom_ = ExpressionLanguage.tryToCalculate(_conf,from_,0);
        if (_conf.callsOrException()) {
            return null;
        }
        if (argFrom_.isNull()) {
            _conf.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_)));
            return null;
        }
        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        ExpressionLanguage to_ = ip_.getCurrentEl(_conf,this, IndexConstants.SECOND_INDEX, IndexConstants.SECOND_INDEX);
        Argument argTo_ = ExpressionLanguage.tryToCalculate(_conf,to_,0);
        if (_conf.callsOrException()) {
            return null;
        }
        if (argTo_.isNull()) {
            _conf.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_)));
            return null;
        }
        ip_.setGlobalOffset(stepOffset);
        ip_.setOffset(0);
        ExpressionLanguage step_ = ip_.getCurrentEl(_conf,this, IndexConstants.SECOND_INDEX + 1, IndexConstants.SECOND_INDEX + 1);
        Argument argStep_ = ExpressionLanguage.tryToCalculate(_conf,step_,0);
        if (_conf.callsOrException()) {
            return null;
        }
        if (argStep_.isNull()) {
            _conf.setCallingState(new CustomFoundExc(new ErrorStruct(_conf, null_)));
            return null;
        }
        ip_.clearCurrentEls();
        long fromValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argFrom_.getStruct())).longStruct();
        long toValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argTo_.getStruct())).longStruct();
        long stepValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argStep_.getStruct())).longStruct();
        long ratio_ = getIterCounts(stepValue_, fromValue_, eq_, toValue_);
        if (stepValue_ > 0) {
            if (fromValue_ > toValue_) {
                stepValue_ = -stepValue_;
            }
        } else if (stepValue_ < 0) {
            if (fromValue_ < toValue_) {
                stepValue_ = -stepValue_;
            }
        }
        boolean finished_ = false;
        if (ratio_ == IndexConstants.SIZE_EMPTY) {
            finished_ = true;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(label);
        l_.setFinished(finished_);
        l_.setExecBlock(this);
        l_.setExecLoop(this);
        l_.setCurrentVisitedBlock(this);
        l_.setMaxIteration(ratio_);
        l_.setStep(stepValue_);
        ip_.addBlock(l_);
        if (finished_) {
            return l_;
        }
        Struct struct_ = NumParsers.convertToInt(ClassArgumentMatching.getPrimitiveCast(importedClassName, _conf.getStandards().getPrimTypes()), new LongStruct(fromValue_));
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndexClassName(importedClassIndexName);
        varsLoop_.put(var_, lv_);
        ip_.putValueVar(var_, LocalVariable.newLocalVariable(struct_,importedClassName));
        return l_;
    }

    public static long getIterCounts(long _stepValue, long _fromValue, boolean _eq, long _toValue) {
        long ratio_ = 0;
        long absStep_ = Math.abs(_stepValue);
        if (absStep_ > 0) {
            long num_ = Math.max(_fromValue, _toValue)-Math.min(_fromValue, _toValue);
            ratio_ = num_/absStep_+1;
            if (!_eq &&num_%absStep_==0) {
                ratio_--;
            }
        }
        return ratio_;
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        String var_ = getVariableName();
        v_.removeKey(var_);
        _ip.removeLocalVar(var_);
    }

    private void incrementLoop(ContextEl _conf, LoopBlockStack _l) {
        _l.setIndex(_l.getIndex() + 1);
        _conf.getLastPage().setGlobalOffset(variableNameOffset);
        _conf.getLastPage().setOffset(0);
        String var_ = getVariableName();
        Argument struct_ = ExecTemplates.getValue(_conf,var_, -1, _conf.getLastPage().getCache(), _conf.getLastPage().getValueVars());
        long o_ = NumParsers.convertToNumber(struct_.getStruct()).longStruct()+_l.getStep();
        Struct element_ = NumParsers.convertToInt(ClassArgumentMatching.getPrimitiveCast(importedClassName, _conf.getStandards().getPrimTypes()), new LongStruct(o_));
        ExecTemplates.setValue(_conf,var_, new Argument(element_),-1, _conf.getLastPage().getCache(), _conf.getLastPage().getValueVars());
        ExecTemplates.incrIndexLoop(_conf,var_, -1, _conf.getLastPage().getCache(), _conf.getLastPage().getVars());
    }

    public String getVariableName() {
        return variableName;
    }

    public boolean isEq() {
        return eq;
    }

}
