package code.expressionlanguage.methods;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.methods.exceptions.AlreadyDefinedVarException;
import code.expressionlanguage.methods.exceptions.BadConstructorCall;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.LoopVariable;
import code.sml.Element;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringMap;

public final class ForIterativeLoop extends BracedStack implements ForLoop {

    private final String className;

    private final String classIndexName;

    private final String variableName;

    private final String init;

    private final String expression;

    private final String step;

    private final boolean eq;

    private CustList<OperationNode> opInit;

    private CustList<OperationNode> opExp;

    private CustList<OperationNode> opStep;

    public ForIterativeLoop(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        className = _el.getAttribute(ATTRIBUTE_CLASS);
        variableName = _el.getAttribute(ATTRIBUTE_VAR);
        init = _el.getAttribute(ATTRIBUTE_INIT);
        expression = _el.getAttribute(ATTRIBUTE_EXPRESSION);
        step = _el.getAttribute(ATTRIBUTE_STEP);
        eq = _el.hasAttribute(ATTRIBUTE_EQ);
        String classIndex_ = _el.getAttribute(ATTRIBUTE_CLASS_INDEX);
        if (classIndex_.isEmpty()) {
            classIndex_ = _importingPage.getStandards().getAliasLong();
        }
        classIndexName = classIndex_;
        setAlwaysSkipped(true);
    }

    @Override
    public NatTreeMap<String,String> getClassNames(LgNames _stds) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        tr_.put(ATTRIBUTE_CLASS, className);
        tr_.put(ATTRIBUTE_CLASS_INDEX, classIndexName);
        return tr_;
    }

    @Override
    public String getClassIndexName() {
        return classIndexName;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
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
    public void checkBlocksTree(ContextEl _cont) {
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(ATTRIBUTE_CLASS_INDEX);
        page_.setOffset(0);
        if (!PrimitiveTypeUtil.isPrimitiveOrWrapper(classIndexName, _cont)) {
            throw new DynamicCastClassException(classIndexName+RETURN_LINE+_cont.joinPages());
        }
        page_.setProcessingAttribute(ATTRIBUTE_CLASS);
        page_.setOffset(0);
        ClassArgumentMatching elementClass_ = new ClassArgumentMatching(className);
        if (!PrimitiveTypeUtil.isPureNumberClass(elementClass_, _cont)) {
            throw new DynamicCastClassException(_cont.joinPages());
        }
        page_.setProcessingAttribute(ATTRIBUTE_VAR);
        page_.setOffset(0);
        if (_cont.getLastPage().getVars().contains(variableName)) {
            throw new AlreadyDefinedVarException(variableName+RETURN_LINE+_cont.joinPages());
        }
        page_.setProcessingAttribute(ATTRIBUTE_INIT);
        page_.setOffset(0);
        opInit = ElUtil.getAnalyzedOperations(init, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        OperationNode initEl_ = opInit.last();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(className, initEl_.getResultClass().getName(), _cont)) {
            String str_ = initEl_.getResultClass().getName();
            throw new DynamicCastClassException(str_+RETURN_LINE+_cont.joinPages());
        }
        page_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        page_.setOffset(0);
        opExp = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        OperationNode expressionEl_ = opExp.last();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(className, expressionEl_.getResultClass().getName(), _cont)) {
            String str_ = expressionEl_.getResultClass().getName();
            throw new DynamicCastClassException(str_+RETURN_LINE+_cont.joinPages());
        }
        page_.setProcessingAttribute(ATTRIBUTE_STEP);
        page_.setOffset(0);
        opStep = ElUtil.getAnalyzedOperations(step, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        OperationNode stepEl_ = opStep.last();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(className, stepEl_.getResultClass().getName(), _cont)) {
            String str_ = stepEl_.getResultClass().getName();
            throw new DynamicCastClassException(str_+RETURN_LINE+_cont.joinPages());
        }
        LoopVariable lv_ = new LoopVariable();
        lv_.setClassName(className);
        lv_.setIndexClassName(classIndexName);
        _cont.getLastPage().getVars().put(variableName, lv_);
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return false;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public void checkCallConstructor(ContextEl _cont) {
        PageEl p_ = _cont.getLastPage();
        p_.setProcessingAttribute(ATTRIBUTE_INIT);
        for (OperationNode o: opInit) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                throw new BadConstructorCall(_cont.joinPages());
            }
        }
        p_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        for (OperationNode o: opExp) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                throw new BadConstructorCall(_cont.joinPages());
            }
        }
        p_.setProcessingAttribute(ATTRIBUTE_STEP);
        for (OperationNode o: opStep) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                throw new BadConstructorCall(_cont.joinPages());
            }
        }
    }

    @Override
    public String getTagName() {
        return TAG_FOR;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible();
        if (c_ != null && c_.getBlock() == this) {
            if (c_.isFinished()) {
                removeVarAndLoop(ip_);
                processBlock(_cont);
                return;
            }
            ip_.getReadWrite().setBlock(getFirstChild());
            return;
        }
        processLoop(_cont);
        c_ = (LoopBlockStack) ip_.getLastStack();
        if (c_.isFinished()) {
            ip_.removeLastBlock();
            processBlock(_cont);
            return;
        } else {
            ip_.getReadWrite().setBlock(getFirstChild());
            return;
        }
    }

    void processLoop(ContextEl _conf) {
        LgNames stds_ = _conf.getStandards();
        String null_ = stds_.getAliasNullPe();
        PageEl ip_ = _conf.getLastPage();
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String var_ = getVariableName();
        long nbMaxIterations_ = 0;
        long stepValue_ = 0;
        long fromValue_ = 0;
        Object realFromValue_ = 0;

        boolean eq_ = isEq();
        ip_.setProcessingAttribute(ATTRIBUTE_INIT);
        ip_.setOffset(0);
        ExpressionLanguage from_ = ip_.getCurrentEl(this, CustList.FIRST_INDEX, getInitEl());
        Argument argFrom_ = from_.calculateMember(_conf);
        if (argFrom_.isNull()) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
        ip_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        ip_.setOffset(0);
        ExpressionLanguage to_ = ip_.getCurrentEl(this, CustList.SECOND_INDEX, getExpressionEl());
        Argument argTo_ = to_.calculateMember(_conf);
        if (argTo_.isNull()) {
            throw new InvokeException(new StdStruct(new CustomError(RETURN_LINE+_conf.joinPages()),null_));
        }
        ip_.setProcessingAttribute(ATTRIBUTE_STEP);
        ip_.setOffset(0);
        ExpressionLanguage step_ = ip_.getCurrentEl(this, CustList.SECOND_INDEX + 1, getStepEl());
        Argument argStep_ = step_.calculateMember(_conf);
        if (argStep_.isNull()) {
            throw new InvokeException(new StdStruct(new CustomError(RETURN_LINE+_conf.joinPages()),null_));
        }
        realFromValue_ = argFrom_.getObject();
        ip_.setCurrentBlock(null);
        ip_.clearCurrentEls();
        fromValue_ = (Long)PrimitiveTypeUtil.convert(stds_.getAliasPrimLong(), realFromValue_, _conf);
        long toValue_ = (Long)PrimitiveTypeUtil.convert(stds_.getAliasPrimLong(), argTo_.getObject(), _conf);
        stepValue_ = (Long)PrimitiveTypeUtil.convert(stds_.getAliasPrimLong(), argStep_.getObject(), _conf);
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
        long length_ = CustList.INDEX_NOT_FOUND_ELT;
        boolean finished_ = false;
        length_ = nbMaxIterations_;
        if (length_ == CustList.SIZE_EMPTY) {
            finished_ = true;
        }
        if (getFirstChild() == null) {
            finished_ = true;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setFinished(finished_);
        l_.setBlock(this);
        l_.setMaxIteration(length_);
        ip_.addBlock(l_);
        if (finished_) {
            return;
        }
        Object int_ = realFromValue_;
        String indexClassName_;
        indexClassName_ = getClassIndexName();
        String className_;
        LoopVariable lv_ = new LoopVariable();
        className_ = getClassName();
        lv_.setClassName(className_);
        lv_.setIndexClassName(indexClassName_);
        lv_.setElement((Number)PrimitiveTypeUtil.convert(className_, int_, _conf));
        lv_.setStep(stepValue_);
        lv_.setExtendedExpression(EMPTY_STRING);
        varsLoop_.put(var_, lv_);
    }

    @Override
    public void exitStack(ContextEl _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void removeVarAndLoop(PageEl _ip) {
        super.removeVarAndLoop(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        String var_ = getVariableName();
        v_.removeKey(var_);
    }

    @Override
    public void processLastElementLoop(ContextEl _conf) {
        PageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        Block forLoopLoc_ = l_.getBlock();
        rw_.setBlock(forLoopLoc_);
        if (l_.hasNext()) {
            incrementLoop(_conf, l_, vars_);
            return;
        }
        l_.setFinished(true);
    }

    @Override
    public void incrementLoop(ContextEl _conf, LoopBlockStack _l,
            StringMap<LoopVariable> _vars) {
        _l.setIndex(_l.getIndex() + 1);
        _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VAR);
        _conf.getLastPage().setOffset(0);
        String var_ = getVariableName();
        LoopVariable lv_ = _vars.getVal(var_);
        Number element_ = (Number) lv_.getStruct().getInstance();
        Number o_ = element_.longValue()+lv_.getStep();
        o_ = (Number) PrimitiveTypeUtil.convert(className, o_, _conf);
        lv_.setElement(o_);
        lv_.setIndex(lv_.getIndex() + 1);
    }
}
