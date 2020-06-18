package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.coverage.AbstractCoverageResult;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.WithNotEmptyEl;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.LongStruct;
import code.util.CustList;
import code.util.StringMap;

public final class ExecForIterativeLoop extends ExecBracedBlock implements ExecLoop, WithNotEmptyEl {

    private String label;
    private int labelOffset;

    private String importedClassName;

    private String importedClassIndexName;

    private final String variableName;
    private int variableNameOffset;

    private final String init;
    private int initOffset;

    private final String expression;
    private int expressionOffset;

    private final String step;
    private int stepOffset;

    private final boolean eq;

    private CustList<ExecOperationNode> opInit;

    private CustList<ExecOperationNode> opExp;

    private CustList<ExecOperationNode> opStep;

    public ExecForIterativeLoop(OffsetsBlock _offset, String label, int labelOffset, String importedClassName, String importedClassIndexName, String variableName, int variableNameOffset, String init, int initOffset, String expression, int expressionOffset, String step, int stepOffset, boolean eq, CustList<ExecOperationNode> opInit, CustList<ExecOperationNode> opExp, CustList<ExecOperationNode> opStep) {
        super(_offset);
        this.label = label;
        this.labelOffset = labelOffset;
        this.importedClassName = importedClassName;
        this.importedClassIndexName = importedClassIndexName;
        this.variableName = variableName;
        this.variableNameOffset = variableNameOffset;
        this.init = init;
        this.initOffset = initOffset;
        this.expression = expression;
        this.expressionOffset = expressionOffset;
        this.step = step;
        this.stepOffset = stepOffset;
        this.eq = eq;
        this.opInit = opInit;
        this.opExp = opExp;
        this.opStep = opStep;
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        AbstractCoverageResult result_ = _cont.getCoverage().getCoverLoops(this);
        String tag_;
        if (result_.isFullCovered()) {
            tag_ = "<span class=\"f\">";
        } else if (result_.isPartialCovered()) {
            tag_ = "<span class=\"p\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_,off_));
        tag_ = "</span>";
        _parts.add(new PartOffset(tag_,off_+ _cont.getKeyWords().getKeyWordIter().length()));
        tag_ = "<a name=\"m"+ variableNameOffset +"\">";
        _parts.add(new PartOffset(tag_,variableNameOffset));
        tag_ = "</a>";
        _parts.add(new PartOffset(tag_,variableNameOffset+variableName.length()));
        off_ = initOffset;
        int offsetEndBlock_ = off_ + init.length();
        ElUtil.buildCoverageReport(_cont,off_,this,opInit,offsetEndBlock_,_parts);
        off_ = expressionOffset;
        offsetEndBlock_ = off_ + expression.length();
        ElUtil.buildCoverageReport(_cont,off_,this,opExp,offsetEndBlock_,_parts);
        off_ = stepOffset;
        offsetEndBlock_ = off_ + step.length();
        ElUtil.buildCoverageReport(_cont,off_,this,opStep,offsetEndBlock_,_parts);
        _cont.getCoverage().getLoopVars().put(variableName,variableNameOffset);
        refLabel(_parts,label,labelOffset);
    }

    @Override
    public void processLastElementLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        if (l_.hasNext()) {
            incrementLoop(_conf, l_, vars_);
            _conf.getCoverage().passExecLoop(_conf, new Argument(BooleanStruct.of(true)));
            return;
        }
        l_.setFinished(true);
        _conf.getCoverage().passExecLoop(_conf, new Argument(BooleanStruct.of(false)));

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
        opInit = ElUtil.getReducedNodes(i_);
        ExecOperationNode e_ = opExp.last();
        opExp = ElUtil.getReducedNodes(e_);
        ExecOperationNode s_ = opStep.last();
        opStep = ElUtil.getReducedNodes(s_);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            ip_.processVisitedLoop(c_,this,this,_cont);
            return;
        }
        processLoop(_cont);
        if (_cont.callsOrException()) {
            return;
        }
        c_ = (LoopBlockStack) ip_.getLastStack();
        if (c_.isFinished()) {
            _cont.getCoverage().passExecLoop(_cont, new Argument(BooleanStruct.of(false)));
            processBlockAndRemove(_cont);
            return;
        }
        _cont.getCoverage().passExecLoop(_cont, new Argument(BooleanStruct.of(true)));
        ip_.getReadWrite().setBlock(getFirstChild());
    }
    void processLoop(ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
        String null_ = stds_.getAliasNullPe();
        AbstractPageEl ip_ = _conf.getLastPage();
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String var_ = getVariableName();
        long nbMaxIterations_ = 0;
        long stepValue_ = 0;
        long fromValue_ = 0;

        boolean eq_ = isEq();
        ip_.setGlobalOffset(initOffset);
        ip_.setOffset(0);
        ExpressionLanguage from_ = ip_.getCurrentEl(_conf,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
        Argument argFrom_ = ElUtil.tryToCalculate(_conf,from_,0);
        if (_conf.callsOrException()) {
            return;
        }
        if (argFrom_.isNull()) {
            _conf.setException(new ErrorStruct(_conf,null_));
            return;
        }
        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        ExpressionLanguage to_ = ip_.getCurrentEl(_conf,this, CustList.SECOND_INDEX, CustList.SECOND_INDEX);
        Argument argTo_ = ElUtil.tryToCalculate(_conf,to_,0);
        if (_conf.callsOrException()) {
            return;
        }
        if (argTo_.isNull()) {
            _conf.setException(new ErrorStruct(_conf,null_));
            return;
        }
        ip_.setGlobalOffset(stepOffset);
        ip_.setOffset(0);
        ExpressionLanguage step_ = ip_.getCurrentEl(_conf,this, CustList.SECOND_INDEX + 1, CustList.SECOND_INDEX + 1);
        Argument argStep_ = ElUtil.tryToCalculate(_conf,step_,0);
        if (_conf.callsOrException()) {
            return;
        }
        if (argStep_.isNull()) {
            _conf.setException(new ErrorStruct(_conf,null_));
            return;
        }
        ip_.clearCurrentEls();
        String prLong_ = stds_.getAliasPrimLong();
        fromValue_ = ClassArgumentMatching.convertToNumber(PrimitiveTypeUtil.unwrapObject(prLong_, argFrom_.getStruct(), stds_)).longStruct();
        long toValue_ = ClassArgumentMatching.convertToNumber(PrimitiveTypeUtil.unwrapObject(prLong_, argTo_.getStruct(), stds_)).longStruct();
        stepValue_ = ClassArgumentMatching.convertToNumber(PrimitiveTypeUtil.unwrapObject(prLong_, argStep_.getStruct(), stds_)).longStruct();
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
        l_.setCurrentVisitedBlock(this);
        l_.setMaxIteration(length_);
        ip_.addBlock(l_);
        if (finished_) {
            return;
        }
        LoopVariable lv_ = LoopVariable.newLoopVariable(PrimitiveTypeUtil.unwrapObject(importedClassName, new LongStruct(fromValue_), stds_),importedClassName);
        lv_.setIndexClassName(importedClassIndexName);
        lv_.setStep(stepValue_);
        varsLoop_.put(var_, lv_);
    }

    @Override
    public void exitStack(ContextEl _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        String var_ = getVariableName();
        v_.removeKey(var_);
    }

    private void incrementLoop(ContextEl _conf, LoopBlockStack _l,
                               StringMap<LoopVariable> _vars) {
        _l.setIndex(_l.getIndex() + 1);
        _conf.getLastPage().setGlobalOffset(variableNameOffset);
        _conf.getLastPage().setOffset(0);
        String var_ = getVariableName();
        LoopVariable lv_ = _vars.getVal(var_);
        long o_ = ClassArgumentMatching.convertToNumber(lv_.getStruct()).longStruct()+lv_.getStep();
        lv_.setStruct(PrimitiveTypeUtil.unwrapObject(importedClassName, new LongStruct(o_), _conf.getStandards()));
        lv_.setIndex(lv_.getIndex() + 1);
    }

    public String getVariableName() {
        return variableName;
    }

    public boolean isEq() {
        return eq;
    }
}
