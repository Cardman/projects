package code.formathtml;

import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.BadImplicitCast;
import code.expressionlanguage.errors.custom.BadVariableName;
import code.expressionlanguage.errors.custom.DuplicateVariable;
import code.expressionlanguage.files.OffsetBooleanInfo;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.methods.FunctionBlock;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.variables.LoopVariable;
import code.formathtml.exec.RendDynOperationNode;
import code.formathtml.util.RendLoopBlockStack;
import code.formathtml.util.RendReadWrite;
import code.util.CustList;
import code.util.StringMap;

public final class RendForIterativeLoop extends RendParentBlock implements RendLoop, RendReducableOperations {

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

    private CustList<RendDynOperationNode> opInit;

    private CustList<RendDynOperationNode> opExp;

    private CustList<RendDynOperationNode> opStep;
    RendForIterativeLoop(Configuration _importingPage,
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
            classIndex_ = _importingPage.getStandards().getAliasPrimLong();
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

    @Override
    public void buildExpressionLanguage(Configuration _cont,RendDocumentBlock _doc) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(classIndexNameOffset);
        page_.setOffset(0);
        importedClassIndexName = _cont.resolveCorrectType(classIndexName);
        if (!PrimitiveTypeUtil.isPrimitiveOrWrapper(importedClassIndexName, _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(importedClassIndexName);
            mapping_.setParam(_cont.getStandards().getAliasLong());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_cont.getCurrentFileName());
            cast_.setIndexFile(classIndexNameOffset);
            _cont.getClasses().addError(cast_);
        }
        page_.setGlobalOffset(classNameOffset);
        page_.setOffset(0);
        importedClassName = _cont.resolveCorrectType(className);
        String cl_ = importedClassName;
        ClassArgumentMatching elementClass_ = new ClassArgumentMatching(cl_);
        if (!PrimitiveTypeUtil.isPureNumberClass(elementClass_, _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(elementClass_);
            mapping_.setParam(_cont.getStandards().getAliasLong());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_cont.getCurrentFileName());
            cast_.setIndexFile(classNameOffset);
            _cont.getClasses().addError(cast_);
        }
        page_.setGlobalOffset(variableNameOffset);
        page_.setOffset(0);
        if (_cont.getAnalyzing().containsVar(variableName)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableName);
            d_.setFileName(_cont.getCurrentFileName());
            d_.setIndexFile(variableNameOffset);
            _cont.getClasses().addError(d_);
        }
        if (_cont.getAnalyzing().containsMutableLoopVar(variableName)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableName);
            d_.setFileName(_cont.getCurrentFileName());
            d_.setIndexFile(variableNameOffset);
            _cont.getClasses().addError(d_);
        }
        if (!_cont.isValidSingleToken(variableName)) {
            BadVariableName b_ = new BadVariableName();
            b_.setFileName(_cont.getCurrentFileName());
            b_.setIndexFile(variableNameOffset);
            b_.setVarName(variableName);
            _cont.getClasses().addError(b_);
        }
        page_.setGlobalOffset(initOffset);
        page_.setOffset(0);
        boolean static_ = _doc.isStaticContext();
        opInit = ElRenderUtil.getAnalyzedOperations(init,0, _cont, Calculation.staticCalculation(static_));
        RendDynOperationNode initEl_ = opInit.last();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(elementClass_, initEl_.getResultClass(), _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(initEl_.getResultClass());
            mapping_.setParam(elementClass_);
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_cont.getCurrentFileName());
            cast_.setIndexFile(initOffset);
            _cont.getClasses().addError(cast_);
        }
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        opExp = ElRenderUtil.getAnalyzedOperations(expression,0, _cont, Calculation.staticCalculation(static_));
        RendDynOperationNode expressionEl_ = opExp.last();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(elementClass_, expressionEl_.getResultClass(), _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(expressionEl_.getResultClass());
            mapping_.setParam(elementClass_);
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_cont.getCurrentFileName());
            cast_.setIndexFile(expressionOffset);
            _cont.getClasses().addError(cast_);
        }
        page_.setGlobalOffset(stepOffset);
        page_.setOffset(0);
        opStep = ElRenderUtil.getAnalyzedOperations(step, 0,_cont, Calculation.staticCalculation(static_));
        RendDynOperationNode stepEl_ = opStep.last();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(elementClass_, stepEl_.getResultClass(), _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(stepEl_.getResultClass());
            mapping_.setParam(elementClass_);
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(_cont.getCurrentFileName());
            cast_.setIndexFile(stepOffset);
            _cont.getClasses().addError(cast_);
        }
        LoopVariable lv_ = new LoopVariable();
        lv_.setClassName(cl_);
        lv_.setIndexClassName(importedClassIndexName);
        _cont.getAnalyzing().putVar(variableName, lv_);
    }

    @Override
    public void reduce(Configuration _context) {
        RendDynOperationNode i_ = opInit.last();
        opInit = ElRenderUtil.getReducedNodes(i_);
        RendDynOperationNode e_ = opExp.last();
        opExp = ElRenderUtil.getReducedNodes(e_);
        RendDynOperationNode s_ = opStep.last();
        opStep = ElRenderUtil.getReducedNodes(s_);
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible();
        if (c_ != null && c_.getBlock() == this) {
            if (c_.isFinished()) {
                removeVarAndLoop(ip_);
                processBlock(_cont);
                return;
            }
            ip_.getRendReadWrite().setRead(getFirstChild());
            return;
        }
        processLoop(_cont);
        if (_cont.getContext().hasExceptionOrFailInit()) {
            return;
        }
        c_ = (RendLoopBlockStack) ip_.getRendLastStack();
        if (c_.isFinished()) {
            ip_.removeRendLastBlock();
            processBlock(_cont);
            return;
        }
        ip_.getRendReadWrite().setRead(getFirstChild());
    }

    void processLoop(Configuration _conf) {
        LgNames stds_ = _conf.getStandards();
        String null_ = stds_.getAliasNullPe();
        ImportingPage ip_ = _conf.getLastPage();
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String var_ = getVariableName();
        long nbMaxIterations_ = 0;
        long stepValue_ = 0;
        long fromValue_ = 0;

        boolean eq_ = isEq();
//        ip_.setGlobalOffset(initOffset);
        ip_.setOffset(0);
        Argument argFrom_ = ElRenderUtil.calculateReuse(opInit,_conf);
        if (_conf.getContext().hasExceptionOrFailInit()) {
            return;
        }
        if (argFrom_.isNull()) {
            _conf.setException(new ErrorStruct(_conf,null_));
            return;
        }
//        ip_.setGlobalOffset(expressionOffset);
        ip_.setOffset(0);
        Argument argTo_ = ElRenderUtil.calculateReuse(opExp,_conf);
        if (_conf.getContext().hasExceptionOrFailInit()) {
            return;
        }
        if (argTo_.isNull()) {
            _conf.setException(new ErrorStruct(_conf,null_));
            return;
        }
//        ip_.setGlobalOffset(stepOffset);
        ip_.setOffset(0);
        Argument argStep_ = ElRenderUtil.calculateReuse(opStep,_conf);
        if (_conf.getContext().hasExceptionOrFailInit()) {
            return;
        }
        if (argStep_.isNull()) {
            _conf.setException(new ErrorStruct(_conf,null_));
            return;
        }
        String prLong_ = stds_.getAliasPrimLong();
        fromValue_ = ((NumberStruct)PrimitiveTypeUtil.unwrapObject(prLong_, argFrom_.getStruct(), stds_)).longStruct();
        long toValue_ = ((NumberStruct)PrimitiveTypeUtil.unwrapObject(prLong_, argTo_.getStruct(), stds_)).longStruct();
        stepValue_ = ((NumberStruct)PrimitiveTypeUtil.unwrapObject(prLong_, argStep_.getStruct(), stds_)).longStruct();
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
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setFinished(finished_);
        l_.setBlock(this);
        l_.setMaxIteration(length_);
        ip_.addBlock(l_);
        if (finished_) {
            return;
        }
        LoopVariable lv_ = new LoopVariable();
        lv_.setClassName(importedClassName);
        lv_.setIndexClassName(importedClassIndexName);
        lv_.setStruct(PrimitiveTypeUtil.unwrapObject(importedClassName, new LongStruct(fromValue_), stds_));
        lv_.setStep(stepValue_);
        varsLoop_.put(var_, lv_);
    }

    @Override
    public void exitStack(Configuration _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void removeVarAndLoop(ImportingPage _ip) {
        super.removeVarAndLoop(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        String var_ = getVariableName();
        v_.removeKey(var_);
    }

    @Override
    public void processLastElementLoop(Configuration _conf) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        RendLoopBlockStack l_ = (RendLoopBlockStack) ip_.getRendLastStack();
        RendBlock forLoopLoc_ = l_.getBlock();
        rw_.setRead(forLoopLoc_);
        if (l_.hasNext()) {
            incrementLoop(_conf, l_, vars_);
            return;
        }
        l_.setFinished(true);
    }

    public void incrementLoop(Configuration _conf, RendLoopBlockStack _l,
                              StringMap<LoopVariable> _vars) {
        _l.setIndex(_l.getIndex() + 1);
//        _conf.getLastPage().setGlobalOffset(variableNameOffset);
        _conf.getLastPage().setOffset(0);
        String var_ = getVariableName();
        LoopVariable lv_ = _vars.getVal(var_);
        long o_ = ((NumberStruct) lv_.getStruct()).longStruct()+lv_.getStep();
        lv_.setStruct(PrimitiveTypeUtil.unwrapObject(importedClassName, new LongStruct(o_), _conf.getStandards()));
        lv_.setIndex(lv_.getIndex() + 1);
    }
}
