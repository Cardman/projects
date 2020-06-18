package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
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
import code.expressionlanguage.options.KeyWords;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;

public final class ExecForMutableIterativeLoop extends ExecBracedBlock implements ExecLoop, WithNotEmptyEl {

    private String label;
    private int labelOffset;

    private final String className;
    private int classNameOffset;

    private final String importedClassName;

    private String importedClassIndexName;

    private final StringList variableNames;

    private final String init;
    private int initOffset;

    private final String expression;
    private int expressionOffset;

    private final String step;
    private int stepOffset;

    private CustList<ExecOperationNode> opInit;

    private CustList<ExecOperationNode> opExp;

    private CustList<ExecOperationNode> opStep;

    private CustList<PartOffset> partOffsets;

    public ExecForMutableIterativeLoop(OffsetsBlock _offset, String label, int labelOffset, String className, int classNameOffset, String importedClassName, String importedClassIndexName, StringList variableNames, String init, int initOffset, String expression, int expressionOffset, String step, int stepOffset, CustList<ExecOperationNode> opInit, CustList<ExecOperationNode> opExp, CustList<ExecOperationNode> opStep, CustList<PartOffset> partOffsets) {
        super(_offset);
        this.label = label;
        this.labelOffset = labelOffset;
        this.className = className;
        this.classNameOffset = classNameOffset;
        this.importedClassName = importedClassName;
        this.importedClassIndexName = importedClassIndexName;
        this.variableNames = variableNames;
        this.init = init;
        this.initOffset = initOffset;
        this.expression = expression;
        this.expressionOffset = expressionOffset;
        this.step = step;
        this.stepOffset = stepOffset;
        this.opInit = opInit;
        this.opExp = opExp;
        this.opStep = opStep;
        this.partOffsets = partOffsets;
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        if (!opExp.isEmpty()) {
            AbstractCoverageResult result_ = _cont.getCoverage().getCovers(this).getVal(opExp.last());
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
            _parts.add(new PartOffset(tag_,off_+ _cont.getKeyWords().getKeyWordFor().length()));
        }
        KeyWords keyWords_ = _cont.getKeyWords();
        String keyWordVar_ = keyWords_.getKeyWordVar();
        if (StringList.quickEq(className.trim(), keyWordVar_)) {
            String tag_ = "<b title=\""+ElUtil.transform(importedClassName)+"\">";
            _parts.add(new PartOffset(tag_,classNameOffset));
            tag_ = "</b>";
            _parts.add(new PartOffset(tag_,classNameOffset+ _cont.getKeyWords().getKeyWordFor().length()));
        } else {
            _parts.addAllElts(partOffsets);
        }
        if (!opInit.isEmpty()) {
            _cont.getCoverage().setPossibleDeclareLoopVars(true);
            int off_ = initOffset;
            int offsetEndBlock_ = off_ + init.length();
            ElUtil.buildCoverageReport(_cont,off_,this,opInit,offsetEndBlock_,_parts);
            _cont.getCoverage().setPossibleDeclareLoopVars(false);
        }
        if (!opExp.isEmpty()) {
            int off_ = expressionOffset;
            int offsetEndBlock_ = off_ + expression.length();
            ElUtil.buildCoverageReport(_cont,off_,this,opExp,offsetEndBlock_,_parts);
        }
        if (!opStep.isEmpty()) {
            int off_ = stepOffset;
            int offsetEndBlock_ = off_ + step.length();
            ElUtil.buildCoverageReport(_cont,off_,this,opStep,offsetEndBlock_,_parts);
        }
        refLabel(_parts,label,labelOffset);
    }

    @Override
    public void processLastElementLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        l_.setEvaluatingKeepLoop(true);
        int index_ = 0;
        ip_.setOffset(0);
        ip_.setGlobalOffset(stepOffset);
        if (!opStep.isEmpty()) {
            ExpressionLanguage from_ = ip_.getCurrentEl(_conf,this, CustList.FIRST_INDEX, 2);
            ElUtil.tryToCalculate(_conf,from_,0);
            if (_conf.callsOrException()) {
                return;
            }
            index_++;
        }
        if (ip_.sizeEl() <= index_) {
            for (String v : variableNames) {
                LoopVariable lv_ = ip_.getVars().getVal(v);
                lv_.setIndex(lv_.getIndex() + 1);
            }
        }
        ConditionReturn keep_ = evaluateCondition(_conf, index_);
        if (keep_ == ConditionReturn.CALL_EX) {
            return;
        }
        if (keep_ == ConditionReturn.NO) {
            l_.setFinished(true);
        }
        l_.setEvaluatingKeepLoop(false);
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

    @Override
    public void reduce(ContextEl _context) {
        if (!opInit.isEmpty()) {
            ExecOperationNode i_ = opInit.last();
            opInit = ElUtil.getReducedNodes(i_);
        }
        if (!opExp.isEmpty()) {
            ExecOperationNode e_ = opExp.last();
            opExp = ElUtil.getReducedNodes(e_);
        }
        if (!opStep.isEmpty()) {
            ExecOperationNode s_ = opStep.last();
            opStep = ElUtil.getReducedNodes(s_);
        }
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            ip_.processVisitedLoop(c_,this,this,_cont);
            return;
        }
        ip_.setGlobalOffset(initOffset);
        ip_.setOffset(0);
        int index_ = 0;
        if (ip_.isEmptyEl()) {
            String formatted_ = ip_.formatVarType(importedClassName, _cont);
            Struct struct_ = PrimitiveTypeUtil.defaultValue(formatted_, _cont);
            for (String v: variableNames) {
                LoopVariable lv_ = LoopVariable.newLoopVariable(struct_,formatted_);
                lv_.setIndexClassName(importedClassIndexName);
                ip_.getVars().put(v, lv_);
            }
        }
        if (!opInit.isEmpty()) {
            ExpressionLanguage from_ = ip_.getCurrentEl(_cont,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
            ElUtil.tryToCalculate(_cont,from_,0);
            if (_cont.callsOrException()) {
                return;
            }
            index_++;
        }
        ConditionReturn res_ = evaluateCondition(_cont, index_);
        if (res_ == ConditionReturn.CALL_EX) {
            return;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(label);
        l_.setExecBlock(this);
        l_.setCurrentVisitedBlock(this);
        boolean finished_ = res_ == ConditionReturn.NO;
        l_.setFinished(finished_);
        ip_.addBlock(l_);
        if (finished_) {
            processBlockAndRemove(_cont);
            return;
        }
        rw_.setBlock(getFirstChild());
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        for (String v: variableNames) {
            _ip.getVars().removeKey(v);
        }
    }

    private ConditionReturn evaluateCondition(ContextEl _context, int _index) {
        AbstractPageEl last_ = _context.getLastPage();
        if (opExp.isEmpty()) {
            last_.clearCurrentEls();
            return ConditionReturn.YES;
        }
        ExpressionLanguage exp_ = last_.getCurrentEl(_context,this, _index, CustList.SECOND_INDEX);
        last_.setOffset(0);
        last_.setGlobalOffset(expressionOffset);
        Argument arg_ = ElUtil.tryToCalculate(_context,exp_,0);
        if (_context.callsOrException()) {
            return ConditionReturn.CALL_EX;
        }
        last_.clearCurrentEls();
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }
    @Override
    public void exitStack(ContextEl _context) {
        processLastElementLoop(_context);
    }

    private ExpressionLanguage getInitEl() {
        return new ExpressionLanguage(opInit);
    }

    private ExpressionLanguage getExpressionEl() {
        return new ExpressionLanguage(opExp);
    }

    private ExpressionLanguage getStepEl() {
        return new ExpressionLanguage(opStep);
    }

}
