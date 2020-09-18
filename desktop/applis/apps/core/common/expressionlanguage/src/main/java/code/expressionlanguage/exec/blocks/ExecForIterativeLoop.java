package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.files.OffsetsBlock;
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

    public ExecForIterativeLoop(OffsetsBlock _offset, String label, String importedClassName, String importedClassIndexName, String variableName, int variableNameOffset, int initOffset, int expressionOffset, int stepOffset, boolean eq, CustList<ExecOperationNode> opInit, CustList<ExecOperationNode> opExp, CustList<ExecOperationNode> opStep) {
        super(_offset);
        this.label = label;
        this.importedClassName = importedClassName;
        this.importedClassIndexName = importedClassIndexName;
        this.variableName = variableName;
        this.variableNameOffset = variableNameOffset;
        this.initOffset = initOffset;
        this.expressionOffset = expressionOffset;
        this.stepOffset = stepOffset;
        this.eq = eq;
        this.opInit = opInit;
        this.opExp = opExp;
        this.opStep = opStep;
    }

    @Override
    public void processLastElementLoop(ContextEl _conf, LoopBlockStack _l) {
        if (_l.hasNext()) {
            incrementLoop(_conf, _l);
            _conf.getCoverage().passLoop(_conf, new Argument(BooleanStruct.of(true)));
            return;
        }
        _l.setFinished(true);
        _conf.getCoverage().passLoop(_conf, new Argument(BooleanStruct.of(false)));

    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        if (_indexProcess == 0) {
            return getInitEl();
        }
        if (_indexProcess == 1) {
            return getExpressionEl();
        }
        return getStepEl();
    }

    public ExpressionLanguage getInitEl() {
        return new ExpressionLanguage(opInit);
    }

    public ExpressionLanguage getExpressionEl() {
        return new ExpressionLanguage(opExp);
    }

    public ExpressionLanguage getStepEl() {
        return new ExpressionLanguage(opStep);
    }

    @Override
    public void reduce(ContextEl _context) {
        ExecOperationNode i_ = opInit.last();
        opInit = ExpressionLanguage.getReducedNodes(i_);
        ExecOperationNode e_ = opExp.last();
        opExp = ExpressionLanguage.getReducedNodes(e_);
        ExecOperationNode s_ = opStep.last();
        opStep = ExpressionLanguage.getReducedNodes(s_);
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
            _cont.getCoverage().passLoop(_cont, new Argument(BooleanStruct.of(false)));
            processBlockAndRemove(_cont);
            return;
        }
        _cont.getCoverage().passLoop(_cont, new Argument(BooleanStruct.of(true)));
        ip_.getReadWrite().setBlock(getFirstChild());
    }
    private LoopBlockStack processLoop(ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
        String null_ = stds_.getAliasNullPe();
        AbstractPageEl ip_ = _conf.getLastPage();
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String var_ = getVariableName();
        long nbMaxIterations_ = 0;
        long stepValue_;
        long fromValue_;

        boolean eq_ = isEq();
        ip_.setGlobalOffset(initOffset);
        ip_.setOffset(0);
        ExpressionLanguage from_ = ip_.getCurrentEl(_conf,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
        Argument argFrom_ = ExpressionLanguage.tryToCalculate(_conf,from_,0);
        if (_conf.callsOrException()) {
            return null;
        }
        if (argFrom_.isNull()) {
            _conf.setException(new ErrorStruct(_conf,null_));
            return null;
        }
        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        ExpressionLanguage to_ = ip_.getCurrentEl(_conf,this, CustList.SECOND_INDEX, CustList.SECOND_INDEX);
        Argument argTo_ = ExpressionLanguage.tryToCalculate(_conf,to_,0);
        if (_conf.callsOrException()) {
            return null;
        }
        if (argTo_.isNull()) {
            _conf.setException(new ErrorStruct(_conf,null_));
            return null;
        }
        ip_.setGlobalOffset(stepOffset);
        ip_.setOffset(0);
        ExpressionLanguage step_ = ip_.getCurrentEl(_conf,this, CustList.SECOND_INDEX + 1, CustList.SECOND_INDEX + 1);
        Argument argStep_ = ExpressionLanguage.tryToCalculate(_conf,step_,0);
        if (_conf.callsOrException()) {
            return null;
        }
        if (argStep_.isNull()) {
            _conf.setException(new ErrorStruct(_conf,null_));
            return null;
        }
        ip_.clearCurrentEls();
        fromValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argFrom_.getStruct())).longStruct();
        long toValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argTo_.getStruct())).longStruct();
        stepValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argStep_.getStruct())).longStruct();
        if (stepValue_ > 0) {
            if (fromValue_ > toValue_) {
                stepValue_ = -stepValue_;
            }
        } else if (stepValue_ < 0) {
            if (fromValue_ < toValue_) {
                stepValue_ = -stepValue_;
            }
        }
        if (stepValue_ > 0) {
            long copyFrom_ = fromValue_;
            while (true) {
                if (copyFrom_ >= toValue_ && !eq_) {
                    break;
                }
                if (copyFrom_ > toValue_) {
                    break;
                }
                nbMaxIterations_++;
                copyFrom_ += stepValue_;
            }
        } else if (stepValue_ < 0) {
            long copyFrom_ = fromValue_;
            while (true) {
                if (copyFrom_ <= toValue_ && !eq_) {
                    break;
                }
                if (copyFrom_ < toValue_) {
                    break;
                }
                nbMaxIterations_++;
                copyFrom_ += stepValue_;
            }
        }
        long length_;
        boolean finished_ = false;
        length_ = nbMaxIterations_;
        if (length_ == CustList.SIZE_EMPTY) {
            finished_ = true;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(label);
        l_.setFinished(finished_);
        l_.setExecBlock(this);
        l_.setExecLoop(this);
        l_.setCurrentVisitedBlock(this);
        l_.setMaxIteration(length_);
        l_.setStep(stepValue_);
        ip_.addBlock(l_);
        if (finished_) {
            return l_;
        }
        Struct struct_ = NumParsers.convertToInt(ClassArgumentMatching.getPrimitiveCast(importedClassName, _conf.getStandards()), new LongStruct(fromValue_));
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndexClassName(importedClassIndexName);
        varsLoop_.put(var_, lv_);
        ip_.putValueVar(var_, LocalVariable.newLocalVariable(struct_,importedClassName));
        return l_;
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        StringMap<LocalVariable> vInfo_ = _ip.getValueVars();
        String var_ = getVariableName();
        v_.removeKey(var_);
        vInfo_.removeKey(var_);
    }

    private void incrementLoop(ContextEl _conf, LoopBlockStack _l) {
        _l.setIndex(_l.getIndex() + 1);
        _conf.getLastPage().setGlobalOffset(variableNameOffset);
        _conf.getLastPage().setOffset(0);
        String var_ = getVariableName();
        Argument struct_ = ExecTemplates.getValue(_conf,var_,_conf.getLastPage(),-1);
        long o_ = NumParsers.convertToNumber(struct_.getStruct()).longStruct()+_l.getStep();
        Struct element_ = NumParsers.convertToInt(ClassArgumentMatching.getPrimitiveCast(importedClassName, _conf.getStandards()), new LongStruct(o_));
        ExecTemplates.setValue(_conf,var_,_conf.getLastPage(),new Argument(element_),-1);
        ExecTemplates.incrIndexLoop(_conf,var_,_conf.getLastPage(), -1);
    }

    public String getVariableName() {
        return variableName;
    }

    public boolean isEq() {
        return eq;
    }

}
