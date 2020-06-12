package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetBooleanInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.util.AbstractCoverageResult;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.types.ResolvingImportTypes;
import code.expressionlanguage.variables.LoopVariable;
import code.util.*;

public final class ForIterativeLoop extends BracedStack implements ForLoop {

    private String label;
    private int labelOffset;

    private final String className;
    private String importedClassName;
    private int classNameOffset;

    private final String classIndexName;
    private String importedClassIndexName;
    private int classIndexNameOffset;

    private final String variableName;
    private int variableNameOffset;

    private final String init;
    private int initOffset;

    private final String expression;
    private int expressionOffset;

    private final String step;
    private int stepOffset;

    private final boolean eq;
    private int eqOffset;

    private CustList<ExecOperationNode> opInit;

    private CustList<ExecOperationNode> opExp;

    private CustList<ExecOperationNode> opStep;

    public ForIterativeLoop(ContextEl _importingPage,
                            OffsetStringInfo _className, OffsetStringInfo _variable,
                            OffsetStringInfo _from,
                            OffsetStringInfo _to, OffsetBooleanInfo _eq, OffsetStringInfo _step, OffsetStringInfo _classIndex, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variable.getInfo();
        variableNameOffset = _variable.getOffset();
        init = _from.getInfo();
        initOffset = _from.getOffset();
        expression = _to.getInfo();
        expressionOffset = _to.getOffset();
        step = _step.getInfo();
        stepOffset = _step.getOffset();
        eq = _eq.isInfo();
        eqOffset = _eq.getOffset();
        String classIndex_ = _classIndex.getInfo();
        if (classIndex_.isEmpty()) {
            classIndex_ = _importingPage.getStandards().getAliasPrimInteger();
        }
        classIndexName = classIndex_;
        classIndexNameOffset = _classIndex.getOffset();
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return getLabelOffset();
    }
    public int getLabelOffset() {
        return labelOffset;
    }

    public int getClassNameOffset() {
        return classNameOffset;
    }

    public int getClassIndexNameOffset() {
        return classIndexNameOffset;
    }

    public int getVariableNameOffset() {
        return variableNameOffset;
    }

    public int getInitOffset() {
        return initOffset;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    public int getStepOffset() {
        return stepOffset;
    }

    public int getEqOffset() {
        return eqOffset;
    }

    public String getClassIndexName() {
        return classIndexName;
    }

    public String getClassName() {
        return className;
    }

    public String getVariableName() {
        return variableName;
    }

    public String getInit() {
        return init;
    }

    public String getExpression() {
        return expression;
    }

    public String getStep() {
        return step;
    }

    public boolean isEq() {
        return eq;
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
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        processVariableNames(_cont);
        FunctionBlock f_ = _cont.getAnalyzing().getCurrentFct();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        String cl_ = importedClassName;
        ClassArgumentMatching elementClass_ = new ClassArgumentMatching(cl_);
        page_.setGlobalOffset(initOffset);
        page_.setOffset(0);
        MethodAccessKind static_ = f_.getStaticContext();
        opInit = ElUtil.getAnalyzedOperationsReadOnly(init, _cont, Calculation.staticCalculation(static_));
        ExecOperationNode initEl_ = opInit.last();
        checkType(_cont, elementClass_, initEl_, initOffset);
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        opExp = ElUtil.getAnalyzedOperationsReadOnly(expression, _cont, Calculation.staticCalculation(static_));
        ExecOperationNode expressionEl_ = opExp.last();
        checkType(_cont, elementClass_, expressionEl_, expressionOffset);
        page_.setGlobalOffset(stepOffset);
        page_.setOffset(0);
        opStep = ElUtil.getAnalyzedOperationsReadOnly(step, _cont, Calculation.staticCalculation(static_));
        ExecOperationNode stepEl_ = opStep.last();
        checkType(_cont, elementClass_, stepEl_, stepOffset);
        LoopVariable lv_ = new LoopVariable();
        lv_.setClassName(cl_);
        lv_.setIndexClassName(importedClassIndexName);
        _cont.getAnalyzing().putVar(variableName, lv_);
        _cont.getCoverage().putBlockOperationsLoops(_cont,this);
    }

    private void checkType(ContextEl _cont, ClassArgumentMatching _elementClass, ExecOperationNode _stepEl, int _offset) {
        Mapping m_ = new Mapping();
        m_.setArg(_stepEl.getResultClass());
        m_.setParam(_elementClass);
        if (!Templates.isCorrectOrNumbers(m_,_cont)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(_offset);
            //char before expression
            cast_.buildError(_cont.getAnalysisMessages().getBadImplicitCast(),
                    StringList.join(_elementClass.getNames(),"&"),
                    StringList.join(_stepEl.getResultClass().getNames(),"&"));
            _cont.addError(cast_);
        }
    }

    private void processVariableNames(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classIndexNameOffset);
        page_.setOffset(0);
        importedClassIndexName = ResolvingImportTypes.resolveCorrectType(_cont,classIndexName);
        if (!PrimitiveTypeUtil.isIntOrderClass(new ClassArgumentMatching(importedClassIndexName), _cont)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(classIndexNameOffset);
            //classIndexName len
            cast_.buildError(_cont.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassIndexName);
            _cont.addError(cast_);
        }
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        importedClassName = ResolvingImportTypes.resolveCorrectType(_cont,className);
        String cl_ = importedClassName;
        ClassArgumentMatching elementClass_ = new ClassArgumentMatching(cl_);
        if (!PrimitiveTypeUtil.isIntOrderClass(elementClass_, _cont)) {
            FoundErrorInterpret cast_ = new FoundErrorInterpret();
            cast_.setFileName(getFile().getFileName());
            cast_.setIndexFile(classNameOffset);
            //className len
            cast_.buildError(_cont.getAnalysisMessages().getNotPrimitiveWrapper(),
                    importedClassName);
            _cont.addError(cast_);
        }
        page_.setGlobalOffset(variableNameOffset);
        page_.setOffset(0);
        if (_cont.getAnalyzing().containsVar(variableName)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffset);
            //variable name len
            d_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableName);
            _cont.addError(d_);
        }
        if (_cont.getAnalyzing().containsMutableLoopVar(variableName)) {
            FoundErrorInterpret d_ = new FoundErrorInterpret();
            d_.setFileName(getFile().getFileName());
            d_.setIndexFile(variableNameOffset);
            //variable name len
            d_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableName);
            _cont.addError(d_);
        }
        if (!_cont.isValidSingleToken(variableName)) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(variableNameOffset);
            //variable name len
            b_.buildError(_cont.getAnalysisMessages().getBadVariableName(),
                    variableName);
            _cont.addError(b_);
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
        processLoop(_cont);
        if (_cont.callsOrException()) {
            return;
        }
        c_ = (LoopBlockStack) ip_.getLastStack();
        if (c_.isFinished()) {
            _cont.getCoverage().passLoop(_cont, new Argument(BooleanStruct.of(false)));
            processBlockAndRemove(_cont);
            return;
        }
        _cont.getCoverage().passLoop(_cont, new Argument(BooleanStruct.of(true)));
        ip_.getReadWrite().setBlock(getFirstChild());
    }
    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        AbstractCoverageResult result_ = _cont.getCoverage().getCoverLoops().getVal(this);
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
        l_.setFinished(finished_);
        l_.setBlock(this);
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

    @Override
    public void processLastElementLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        if (l_.hasNext()) {
            incrementLoop(_conf, l_, vars_);
            _conf.getCoverage().passLoop(_conf, new Argument(BooleanStruct.of(true)));
            return;
        }
        l_.setFinished(true);
        _conf.getCoverage().passLoop(_conf, new Argument(BooleanStruct.of(false)));
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

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        if (_indexProcess == 0) {
            return getInitEl();
        }
        if (_indexProcess == 1) {
            return getExpressionEl();
        }
        return getStepEl();
    }

    @Override
    public void abruptGroup(AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbruptGroup(this);
        }
    }

    public CustList<ExecOperationNode> getOpInit() {
        return opInit;
    }

    public CustList<ExecOperationNode> getOpExp() {
        return opExp;
    }

    public CustList<ExecOperationNode> getOpStep() {
        return opStep;
    }
}
