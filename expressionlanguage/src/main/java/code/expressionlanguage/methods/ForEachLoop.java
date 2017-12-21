package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.Templates;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.methods.exceptions.AlreadyDefinedVarException;
import code.expressionlanguage.methods.exceptions.BadConstructorCall;
import code.expressionlanguage.methods.exceptions.BadLoopException;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.sml.Element;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class ForEachLoop extends BracedStack implements ForLoop {

    private final String className;

    private final String classIndexName;

    private final String variableName;

    private final String expression;

    private CustList<OperationNode> opList;

    private Boolean nativeCmp;

    public ForEachLoop(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        className = _el.getAttribute(ATTRIBUTE_CLASS);
        variableName = _el.getAttribute(ATTRIBUTE_VAR);
        expression = _el.getAttribute(ATTRIBUTE_EXPRESSION);
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

    public String getExpression() {
        return expression;
    }

    public ExpressionLanguage getEl() {
        return new ExpressionLanguage(opList);
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(EMPTY_STRING);
        page_.setOffset(0);
        if (getFirstChild() == null) {
            throw new BadLoopException(_cont.joinPages());
        }
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        if (!PrimitiveTypeUtil.isPrimitiveOrWrapper(classIndexName, _cont)) {
            throw new DynamicCastClassException(classIndexName+RETURN_LINE+_cont.joinPages());
        }
        if (_cont.getLastPage().getVars().contains(variableName)) {
            throw new AlreadyDefinedVarException(variableName+RETURN_LINE+_cont.joinPages());
        }
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        page_.setOffset(0);
        opList = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        OperationNode el_ = opList.last();
        if (el_.getResultClass().isArray()) {
            String compo_ = PrimitiveTypeUtil.getQuickComponentType(el_.getResultClass().getName());
            Mapping mapping_ = new Mapping();
            mapping_.setArg(compo_);
            mapping_.setParam(className);
            StringMap<StringList> vars_ = new StringMap<StringList>();
            if (!f_.isStaticContext()) {
                String globalClass_ = page_.getGlobalClass();
                String curClassBase_ = StringList.getAllTypes(globalClass_).first();
                for (TypeVar t: _cont.getClasses().getClassBody(curClassBase_).getParamTypes()) {
                    vars_.put(t.getName(), t.getConstraints());
                }
            }
            mapping_.setMapping(vars_);
            if (!Templates.isCorrect(mapping_, _cont)) {
                String str_ = el_.getResultClass().getName();
                throw new DynamicCastClassException(str_+RETURN_LINE+_cont.joinPages());
            }
        } else {
            String type_ = Templates.getFullTypeByStds(el_.getResultClass().getName(), _cont);
            if (type_ == null) {
                type_ = Templates.getFullTypeByBases(el_.getResultClass().getName(), PredefinedClasses.ITERABLE, _cont);
                nativeCmp = false;
            } else {
                nativeCmp = true;
            }
            if (type_ != null) {
                Mapping mapping_ = new Mapping();
                String paramArg_ = StringList.getAllTypes(type_).last();
                mapping_.setArg(paramArg_);
                mapping_.setParam(className);
                StringMap<StringList> vars_ = new StringMap<StringList>();
                if (!f_.isStaticContext()) {
                    String globalClass_ = page_.getGlobalClass();
                    String curClassBase_ = StringList.getAllTypes(globalClass_).first();
                    for (TypeVar t: _cont.getClasses().getClassBody(curClassBase_).getParamTypes()) {
                        vars_.put(t.getName(), t.getConstraints());
                    }
                }
                mapping_.setMapping(vars_);
                if (!Templates.isCorrect(mapping_, _cont)) {
                    String str_ = el_.getResultClass().getName();
                    throw new DynamicCastClassException(str_+RETURN_LINE+_cont.joinPages());
                }
            } else {
                String str_ = el_.getResultClass().getName();
                throw new DynamicCastClassException(str_+RETURN_LINE+_cont.joinPages());
            }
        }
        LoopVariable lv_ = new LoopVariable();
        lv_.setClassName(className);
        lv_.setIndexClassName(_cont.getStandards().getAliasLong());
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
        p_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        for (OperationNode o: opList) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                throw new BadConstructorCall(_cont.joinPages());
            }
        }
    }

    @Override
    public String getTagName() {
        return TAG_FOREACH;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible();
        if (c_ != null && c_.getBlock() == this) {
            if (c_.isEvaluatingKeepLoop()) {
                processLastElementLoop(_cont);
                return;
            }
            if (c_.isFinished()) {
                removeVarAndLoop(ip_);
                processBlock(_cont);
                return;
            }
            ip_.getReadWrite().setBlock(getFirstChild());
            return;
        }
        LgNames stds_ = _cont.getStandards();
        String null_ = stds_.getAliasNullPe();
        Struct its_ = processLoop(_cont);
        Struct iterStr_ = null;
        long length_ = CustList.INDEX_NOT_FOUND_ELT;
        boolean finished_ = false;
        OperationNode el_ = opList.last();
        if (el_.getResultClass().isArray()) {
            Struct[] it_ = (Struct[]) its_.getInstance();
            length_ = it_.length;
            if (length_ == CustList.SIZE_EMPTY) {
                finished_ = true;
            }
        } else {
            boolean native_ = nativeCmp;
            if (native_) {
                String next_ = stds_.getAliasSimpleIterator();
                ClassMethodId clMeth_ = new ClassMethodId(EMPTY_STRING, new MethodId(MethodModifier.NORMAL, next_, new StringList()));
                ResultErrorStd res_ = _cont.getStandards().getOtherResult(_cont, its_, clMeth_);
                if (res_.getError() != null) {
                    throw new InvokeException(new StdStruct(new CustomError(_cont.joinPages()),null_));
                }
                iterStr_ = res_.getResult();
            } else {
                String locName_ = _cont.getClasses().getIteratorVar(native_);
                LocalVariable locVar_ = new LocalVariable();
                locVar_.setClassName(its_.getClassName(_cont));
                locVar_.setStruct(its_);
                _cont.getLastPage().getLocalVars().put(locName_, locVar_);
                ExpressionLanguage dynTwo_ = _cont.getClasses().getEqIterator();
                ExpressionLanguage dyn_ = _cont.getLastPage().getCurrentEl(this, CustList.SECOND_INDEX, dynTwo_);
                iterStr_ = dyn_.calculateMember(_cont).getStruct();
                _cont.getLastPage().getLocalVars().removeKey(locName_);
                if (iterStr_.isNull()) {
                    throw new InvokeException(new StdStruct(new CustomError(_cont.joinPages()),null_));
                }
            }
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setIndex(-1);
        l_.setFinished(finished_);
        l_.setBlock(this);
        l_.setStructIterator(iterStr_);
        l_.setMaxIteration(length_);
        ip_.addBlock(l_);
        ip_.clearCurrentEls();
        l_.setEvaluatingKeepLoop(true);
        String indexClassName_;
        indexClassName_ = getClassIndexName();
        String className_;
        LoopVariable lv_ = new LoopVariable();
        className_ = getClassName();
        lv_.setIndex(-1);
        lv_.setClassName(className_);
        lv_.setIndexClassName(indexClassName_);
        lv_.setContainer(its_);
        lv_.setExtendedExpression(EMPTY_STRING);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String var_ = getVariableName();
        varsLoop_.put(var_, lv_);
        if (iterStr_ != null) {
            finished_ = !iteratorHasNext(_cont);
        }
        if (finished_) {
            removeVarAndLoop(ip_);
            _cont.getLastPage().clearCurrentEls();
            l_.setEvaluatingKeepLoop(false);
            processBlock(_cont);
            return;
        } else {
            StringMap<LoopVariable> vars_ = ip_.getVars();
            incrementLoop(_cont, l_, vars_);
            _cont.getLastPage().clearCurrentEls();
            l_.setEvaluatingKeepLoop(false);
            ip_.getReadWrite().setBlock(getFirstChild());
            return;
        }
    }

    Struct processLoop(ContextEl _conf) {
        PageEl ip_ = _conf.getLastPage();
        LgNames stds_ = _conf.getStandards();
        String null_ = stds_.getAliasNullPe();
        ip_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        ip_.setOffset(0);
        ExpressionLanguage el_ = ip_.getCurrentEl(this, CustList.FIRST_INDEX, getEl());
        Struct ito_ = el_.calculateMember(_conf).getStruct();
        if (ito_.isNull()) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
        return ito_;
        
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
        l_.setEvaluatingKeepLoop(true);
        boolean hasNext_;
        if (l_.getStructIterator() != null) {
            hasNext_ = iteratorHasNext(_conf);
        } else {
            hasNext_ = l_.hasNext();
        }
        
        if (hasNext_) {
            incrementLoop(_conf, l_, vars_);
        } else {
            l_.setFinished(true);
        }
        _conf.getLastPage().clearCurrentEls();
        l_.setEvaluatingKeepLoop(false);
    }

    private boolean iteratorHasNext(ContextEl _conf) {
        PageEl ip_ = _conf.getLastPage();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        Struct strIter_ = l_.getStructIterator();
        boolean native_ = nativeCmp;
        LgNames stds_ = _conf.getStandards();
        if (native_) {
            String next_ = stds_.getAliasHasNext();
            ClassMethodId clMeth_ = new ClassMethodId(EMPTY_STRING, new MethodId(MethodModifier.NORMAL, next_, new StringList()));
            ResultErrorStd res_ = _conf.getStandards().getOtherResult(_conf, strIter_, clMeth_);
            if (res_.getError() != null) {
                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
            }
            return (Boolean) res_.getResult().getInstance();
        }
        String locName_ = _conf.getClasses().getHasNextVar(native_);
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(strIter_.getClassName(_conf));
        locVar_.setStruct(strIter_);
        _conf.getLastPage().getLocalVars().put(locName_, locVar_);
        ExpressionLanguage dynTwo_ = _conf.getClasses().getEqHasNext();
        ExpressionLanguage dyn_ = _conf.getLastPage().getCurrentEl(this, CustList.FIRST_INDEX, dynTwo_);
        boolean hasNext_ = (Boolean) dyn_.calculateMember(_conf).getObject();
        _conf.getLastPage().getLocalVars().removeKey(locName_);
        return hasNext_;
    }

    @Override
    public void incrementLoop(ContextEl _conf, LoopBlockStack _l,
            StringMap<LoopVariable> _vars) {
        _l.setIndex(_l.getIndex() + 1);

        _conf.getLastPage().setProcessingAttribute(ATTRIBUTE_VAR);
        _conf.getLastPage().setOffset(0);
        String var_ = getVariableName();
        LgNames stds_ = _conf.getStandards();
        String null_ = stds_.getAliasNullPe();
        LoopVariable lv_ = _vars.getVal(var_);
        Struct iterator_ = _l.getStructIterator();
        Struct element_;
        OperationNode el_ = opList.last();
        if (!el_.getResultClass().isArray()) {
            boolean native_ = nativeCmp;
            if (native_) {
                String next_ = stds_.getAliasNext();
                ClassMethodId clMeth_ = new ClassMethodId(EMPTY_STRING, new MethodId(MethodModifier.NORMAL, next_, new StringList()));
                ResultErrorStd res_ = _conf.getStandards().getOtherResult(_conf, iterator_, clMeth_);
                element_ = res_.getResult();
                if (res_.getError() != null) {
                    throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),res_.getError()));
                }
            } else {
                String locName_ = _conf.getClasses().getNextVar(native_);
                LocalVariable locVar_ = new LocalVariable();
                locVar_.setClassName(iterator_.getClassName(_conf));
                locVar_.setStruct(iterator_);
                _conf.getLastPage().getLocalVars().put(locName_, locVar_);
                ExpressionLanguage dynTwo_ = _conf.getClasses().getEqNext();
                ExpressionLanguage dyn_ = _conf.getLastPage().getCurrentEl(this, CustList.SECOND_INDEX, dynTwo_);
                element_ = dyn_.calculateMember(_conf).getStruct();
            }
        } else {
            element_ = ((Struct[])lv_.getContainer().getInstance())[(int) _l.getIndex()];
        }
        if (PrimitiveTypeUtil.primitiveTypeNullObject(getClassName(), element_, _conf)) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
        lv_.setStruct(element_);
        lv_.setIndex(lv_.getIndex() + 1);
    }

}
