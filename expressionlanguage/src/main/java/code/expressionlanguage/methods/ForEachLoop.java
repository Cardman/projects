package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.Templates;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.methods.util.BadConstructorCall;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.DuplicateVariable;
import code.expressionlanguage.methods.util.EmptyTagName;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.sml.Element;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;

public final class ForEachLoop extends BracedStack implements ForLoop {

    private final String className;

    private int classNameOffset;

    private final String classIndexName;

    private int classIndexNameOffset;

    private final String variableName;

    private int variableNameOffset;

    private final String expression;

    private int expressionOffset;

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
            classIndex_ = _importingPage.getStandards().getAliasPrimLong();
        }
        classIndexName = classIndex_;
        setAlwaysSkipped(true);
    }

    public ForEachLoop(ContextEl _importingPage, int _indexChild,
            BracedBlock _m,
            OffsetStringInfo _className, OffsetStringInfo _variable,
            OffsetStringInfo _expression, OffsetStringInfo _classIndex, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        className = _className.getInfo();
        classNameOffset = _className.getOffset();
        variableName = _variable.getInfo();
        variableNameOffset = _variable.getOffset();
        expression = _expression.getInfo();
        expressionOffset = _expression.getOffset();
        String classIndex_ = _classIndex.getInfo();
        if (classIndex_.isEmpty()) {
            classIndex_ = _importingPage.getStandards().getAliasPrimLong();
        }
        classIndexName = classIndex_;
        classIndexNameOffset = _classIndex.getOffset();
        setAlwaysSkipped(true);
    }

    public int getClassNameOffset() {
        return classNameOffset;
    }

    public int getClassIndexNameOffset() {
        return classIndexNameOffset;
    }

    @Override
    public int getVariableNameOffset() {
        return variableNameOffset;
    }

    public int getExpressionOffset() {
        return expressionOffset;
    }

    @Override
    public NatTreeMap<String,String> getClassNames(ContextEl _context) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        tr_.put(ATTRIBUTE_CLASS, className);
        tr_.put(ATTRIBUTE_CLASS_INDEX, classIndexName);
        return tr_;
    }

    @Override
    public NatTreeMap<Integer,String> getClassNamesOffsets(ContextEl _context) {
        NatTreeMap<Integer,String> tr_ = new NatTreeMap<Integer,String>();
        tr_.put(classIndexNameOffset, classIndexName);
        tr_.put(classNameOffset, className);
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
        page_.setGlobalOffset(getOffset().getOffsetTrim());
        page_.setOffset(0);
        if (getFirstChild() == null) {
            EmptyTagName un_ = new EmptyTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _cont.getClasses().getErrorsDet().add(un_);
        }
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        if (!PrimitiveTypeUtil.isPrimitiveOrWrapper(classIndexName, _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(classIndexName);
            mapping_.setParam(_cont.getStandards().getAliasLong());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setRc(getRowCol(0, classIndexNameOffset));
            _cont.getClasses().getErrorsDet().add(cast_);
        }
        if (_cont.getLastPage().getVars().contains(variableName)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableName);
            d_.setFileName(getFile().getFileName());
            d_.setRc(getRowCol(0, variableNameOffset));
            _cont.getClasses().getErrorsDet().add(d_);
            return;
        }
        PageEl page_ = _cont.getLastPage();
        page_.setGlobalOffset(expressionOffset);
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
            if (!Templates.isGenericCorrect(mapping_, _cont)) {
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(getFile().getFileName());
                cast_.setRc(getRowCol(0, expressionOffset));
                _cont.getClasses().getErrorsDet().add(cast_);;
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
                if (!Templates.isGenericCorrect(mapping_, _cont)) {
                    BadImplicitCast cast_ = new BadImplicitCast();
                    cast_.setMapping(mapping_);
                    cast_.setFileName(getFile().getFileName());
                    cast_.setRc(getRowCol(0, expressionOffset));
                    _cont.getClasses().getErrorsDet().add(cast_);
                }
            } else {
                Mapping mapping_ = new Mapping();
                String paramArg_ = StringList.getAllTypes(type_).last();
                mapping_.setArg(paramArg_);
                mapping_.setParam(PrimitiveTypeUtil.getPrettyArrayType(className));
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(getFile().getFileName());
                cast_.setRc(getRowCol(0, expressionOffset));
                _cont.getClasses().getErrorsDet().add(cast_);
            }
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
        p_.setGlobalOffset(expressionOffset);
        for (OperationNode o: opList) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                BadConstructorCall call_ = new BadConstructorCall();
                call_.setFileName(getFile().getFileName());
                call_.setRc(getRowCol(0, expressionOffset));
                call_.setLocalOffset(getRowCol(o.getFullIndexInEl(), expressionOffset));
                _cont.getClasses().getErrorsDet().add(call_);
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
            length_ = LgNames.getLength(its_.getInstance());
            if (length_ == CustList.SIZE_EMPTY) {
                finished_ = true;
            }
        } else {
            boolean native_ = nativeCmp;
            String locName_ = _cont.getClasses().getIteratorVar(native_);
            LocalVariable locVar_ = new LocalVariable();
            locVar_.setClassName(stds_.getStructClassName(its_, _cont));
            locVar_.setStruct(its_);
            _cont.getLastPage().getLocalVars().put(locName_, locVar_);
            ExpressionLanguage dynTwo_ = _cont.getClasses().getEqIterator(native_);
            ExpressionLanguage dyn_ = _cont.getLastPage().getCurrentEl(this, CustList.SECOND_INDEX, dynTwo_);
            iterStr_ = dyn_.calculateMember(_cont).getStruct();
            _cont.getLastPage().getLocalVars().removeKey(locName_);
            if (iterStr_.isNull()) {
                throw new InvokeException(new StdStruct(new CustomError(_cont.joinPages()),null_));
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
        ip_.setGlobalOffset(expressionOffset);
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
        LgNames stds_ = _conf.getStandards();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        Struct strIter_ = l_.getStructIterator();
        boolean native_ = nativeCmp;
        String locName_ = _conf.getClasses().getHasNextVar(native_);
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getStructClassName(strIter_, _conf));
        locVar_.setStruct(strIter_);
        _conf.getLastPage().getLocalVars().put(locName_, locVar_);
        ExpressionLanguage dynTwo_ = _conf.getClasses().getEqHasNext(native_);
        ExpressionLanguage dyn_ = _conf.getLastPage().getCurrentEl(this, CustList.FIRST_INDEX, dynTwo_);
        boolean hasNext_ = (Boolean) dyn_.calculateMember(_conf).getObject();
        _conf.getLastPage().getLocalVars().removeKey(locName_);
        return hasNext_;
    }

    @Override
    public void incrementLoop(ContextEl _conf, LoopBlockStack _l,
            StringMap<LoopVariable> _vars) {
        _l.setIndex(_l.getIndex() + 1);

        _conf.getLastPage().setGlobalOffset(variableNameOffset);
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
            String locName_ = _conf.getClasses().getNextVar(native_);
            LocalVariable locVar_ = new LocalVariable();
            locVar_.setClassName(stds_.getStructClassName(iterator_, _conf));
            locVar_.setStruct(iterator_);
            _conf.getLastPage().getLocalVars().put(locName_, locVar_);
            ExpressionLanguage dynTwo_ = _conf.getClasses().getEqNext(native_);
            ExpressionLanguage dyn_ = _conf.getLastPage().getCurrentEl(this, CustList.SECOND_INDEX, dynTwo_);
            element_ = dyn_.calculateMember(_conf).getStruct();
        } else {
            element_ = LgNames.getElement(lv_.getContainer().getInstance(), (int) _l.getIndex(), _conf);
        }
        if (PrimitiveTypeUtil.primitiveTypeNullObject(getClassName(), element_, _conf)) {
            throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),null_));
        }
        if (!element_.isNull()) {
            String className_ = getClassName();
            className_ = _conf.getLastPage().formatVarType(className_, _conf);
            String argCl_ = stds_.getStructClassName(element_, _conf);
            Mapping mapping_ = new Mapping();
            mapping_.setArg(argCl_);
            mapping_.setParam(className_);
            if (!Templates.isCorrect(mapping_, _conf)) {
                String cast_ = stds_.getAliasCast();
                throw new InvokeException(new StdStruct(new CustomError(_conf.joinPages()),cast_));
            }
        }
        lv_.setStruct(element_);
        lv_.setIndex(lv_.getIndex() + 1);
    }

}
