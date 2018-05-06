package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
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
import code.expressionlanguage.methods.util.BadConstructorCall;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.DuplicateVariable;
import code.expressionlanguage.methods.util.EmptyTagName;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.AssignedBooleanVariables;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stacks.LoopBlockStack;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.variables.LocalVariable;
import code.expressionlanguage.variables.LoopVariable;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
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
        AnalyzedPageEl page_ = _cont.getAnalyzing();
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
        if (_cont.getAnalyzing().getVars().contains(variableName)) {
            DuplicateVariable d_ = new DuplicateVariable();
            d_.setId(variableName);
            d_.setFileName(getFile().getFileName());
            d_.setRc(getRowCol(0, variableNameOffset));
            _cont.getClasses().getErrorsDet().add(d_);
            return;
        }
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(expressionOffset);
        page_.setOffset(0);
        _cont.setRootAffect(false);
        opList = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        if (opList.isEmpty()) {
            return;
        }
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
        _cont.getAnalyzing().getVars().put(variableName, lv_);
        AssignedBooleanVariables res_ = (AssignedBooleanVariables) _cont.getAnalyzing().getAssignedVariables().getFinalVariables().getVal(this);
        for (EntryCust<ClassField,Assignment> e: res_.getFields().lastValue().entryList()) {
            BooleanAssignment ba_ = new BooleanAssignment();
            ba_.setAssignedAfterWhenFalse(true);
            ba_.setUnassignedAfterWhenFalse(true);
            ba_.setAssignedAfterWhenTrue(e.getValue().isAssignedAfter());
            ba_.setUnassignedAfterWhenTrue(e.getValue().isUnassignedAfter());
            res_.getFieldsRootAfter().put(e.getKey(), ba_);
        }
        for (StringMap<Assignment> s: res_.getVariables().lastValue()) {
            StringMap<BooleanAssignment> sm_;
            sm_ = new StringMap<BooleanAssignment>();
            for (EntryCust<String,Assignment> e: s.entryList()) {
                BooleanAssignment ba_ = new BooleanAssignment();
                ba_.setAssignedAfterWhenFalse(true);
                ba_.setUnassignedAfterWhenFalse(true);
                ba_.setAssignedAfterWhenTrue(e.getValue().isAssignedAfter());
                ba_.setUnassignedAfterWhenTrue(e.getValue().isUnassignedAfter());
                sm_.put(e.getKey(), ba_);
            }
            res_.getVariablesRootAfter().add(sm_);
        }
    }
    @Override
    public void defaultAssignmentBefore(Analyzable _an, OperationNode _root) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        ObjectMap<ClassField,AssignmentBefore> fields_;
        CustList<StringMap<AssignmentBefore>> variables_;
        fields_ = new ObjectMap<ClassField,AssignmentBefore>();
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        for (EntryCust<ClassField,AssignmentBefore> e: vars_.getFieldsRootBefore().entryList()) {
            AssignmentBefore ab_ = new AssignmentBefore();
            if (e.getValue().isAssignedBefore()) {
                ab_.setAssignedBefore(true);
            } else {
                ab_.setUnassignedBefore(true);
            }
            fields_.put(e.getKey(), ab_);
        }
        vars_.getFieldsBefore().put(_root, fields_);
        for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                AssignmentBefore ab_ = new AssignmentBefore();
                if (e.getValue().isAssignedBefore()) {
                    ab_.setAssignedBefore(true);
                } else {
                    ab_.setUnassignedBefore(true);
                }
                sm_.put(e.getKey(), ab_);
            }
            variables_.add(sm_);
        }
        vars_.getVariablesBefore().put(_root, variables_);
    }

    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        Block last_ = firstChild_;
        while (last_.getNextSibling() != null) {
            last_ = last_.getNextSibling();
        }
        IdMap<Block, AssignedVariables> id_;
        id_ = _an.getAssignedVariables().getFinalVariables();
        IdMap<Block, AssignedVariables> allDesc_ = new IdMap<Block, AssignedVariables>();
        boolean add_ = false;
        for (EntryCust<Block, AssignedVariables> e: id_.entryList()) {
            if (e.getKey() == this) {
                add_ = true;
            }
            if (add_) {
                allDesc_.put(e.getKey(), e.getValue());
            }
        }
        AssignedBooleanVariables varsWhile_ = (AssignedBooleanVariables) allDesc_.firstValue();
        ObjectMap<ClassField,AssignmentBefore> fieldsHypot_;
        fieldsHypot_ = new ObjectMap<ClassField,AssignmentBefore>();
        CustList<StringMap<AssignmentBefore>> varsHypot_;
        varsHypot_ = new CustList<StringMap<AssignmentBefore>>();
        for (EntryCust<ClassField,AssignmentBefore> e: varsWhile_.getFieldsRootBefore().entryList()) {
            AssignmentBefore ab_ = new AssignmentBefore();
            if (e.getValue().isAssignedBefore()) {
                ab_.setAssignedBefore(true);
            } else {
                ab_.setUnassignedBefore(true);
            }
            fieldsHypot_.put(e.getKey(), ab_);
        }
        for (StringMap<AssignmentBefore> s: varsWhile_.getVariablesRootBefore()) {
            StringMap<AssignmentBefore> sm_;
            sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                AssignmentBefore ab_ = new AssignmentBefore();
                if (e.getValue().isAssignedBefore()) {
                    ab_.setAssignedBefore(true);
                } else {
                    ab_.setUnassignedBefore(true);
                }
                sm_.put(e.getKey(), ab_);
            }
            varsHypot_.add(sm_);
        }
        CustList<ContinueBlock> conts_ = new CustList<ContinueBlock>();
        for (EntryCust<ContinueBlock, Loop> e: _anEl.getContinuables().entryList()) {
            if (e.getValue() != this) {
                continue;
            }
            conts_.add(e.getKey());
            AssignedVariables vars_;
            vars_ = id_.getVal(e.getKey());
            for (EntryCust<ClassField,AssignmentBefore> f: vars_.getFieldsRootBefore().entryList()) {
                if (!f.getValue().isUnassignedBefore()) {
                    fieldsHypot_.getVal(f.getKey()).setUnassignedBefore(false);
                }
            }
            int index_ = 0;
            for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
                for (EntryCust<String,AssignmentBefore> f: s.entryList()) {
                    if (!f.getValue().isUnassignedBefore()) {
                        varsHypot_.get(index_).getVal(f.getKey()).setUnassignedBefore(false);
                    }
                }
                index_++;
            }
        }
        int index_ = 0;
        AssignedVariables vars_;
        vars_ = id_.getVal(last_);
        for (EntryCust<ClassField,Assignment> f: vars_.getFieldsRoot().entryList()) {
            if (!f.getValue().isUnassignedAfter()) {
                fieldsHypot_.getVal(f.getKey()).setUnassignedBefore(false);
            }
        }
        for (StringMap<Assignment> s: vars_.getVariablesRoot()) {
            if (index_ >= varsHypot_.size()) {
                continue;
            }
            for (EntryCust<String,Assignment> f: s.entryList()) {
                if (!f.getValue().isUnassignedAfter()) {
                    varsHypot_.get(index_).getVal(f.getKey()).setUnassignedBefore(false);
                }
            }
            index_++;
        }
        varsWhile_.getFieldsRootBefore().putAllMap(fieldsHypot_);
        varsWhile_.getVariablesRootBefore().clear();
        varsWhile_.getVariablesRootBefore().addAllElts(varsHypot_);
//        processFinalVars(_anEl, allDesc_, fields_, conts_);
        ObjectMap<ClassField,Assignment> fieldsAfter_;
        fieldsAfter_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> varsAfter_;
        varsAfter_ = new CustList<StringMap<Assignment>>();
        String boolType_ = _an.getStandards().getAliasBoolean();
        for (EntryCust<ClassField,BooleanAssignment> e: varsWhile_.getFieldsRootAfter().entryList()) {
            BooleanAssignment ba_ = e.getValue();
            boolean ass_ = true;
            boolean unass_ = true;
            if (!ba_.isAssignedAfterWhenFalse()) {
                ass_ = false;
            }
            if (!ba_.isUnassignedAfterWhenFalse()) {
                unass_ = false;
            }
            if (!varsWhile_.getFieldsRootBefore().getVal(e.getKey()).isAssignedBefore()) {
                ass_ = false;
            }
            if (!varsWhile_.getFieldsRootBefore().getVal(e.getKey()).isUnassignedBefore()) {
                unass_ = false;
            }
            for (EntryCust<BreakBlock, BreakableBlock> f: _anEl.getBreakables().entryList()) {
                if (f.getValue() != this) {
                    continue;
                }
                if (!id_.getVal(f.getKey()).getFieldsRootBefore().getVal(e.getKey()).isAssignedBefore()) {
                    ass_ = false;
                }
                if (!id_.getVal(f.getKey()).getFieldsRootBefore().getVal(e.getKey()).isUnassignedBefore()) {
                    unass_ = false;
                }
            }
            ClassField key_ = e.getKey();
            String classNameDecl_ = key_.getClassName();
            ClassMetaInfo custClass_;
            custClass_ = _an.getClassMetaInfo(classNameDecl_);
            String type_ = custClass_.getFields().getVal(key_.getFieldName()).getType();
            boolean isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(boolType_, type_, _an);
            fieldsAfter_.put(key_, Assignment.assign(isBool_, ass_, unass_));
        }
        varsWhile_.getFieldsRoot().putAllMap(fieldsAfter_);
        index_ = 0;
        for (StringMap<BooleanAssignment> s: varsWhile_.getVariablesRootAfter()) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String,BooleanAssignment> e: s.entryList()) {
                BooleanAssignment ba_ = e.getValue();
                boolean ass_ = true;
                boolean unass_ = true;
                if (!ba_.isAssignedAfterWhenFalse()) {
                    ass_ = false;
                }
                if (!ba_.isUnassignedAfterWhenFalse()) {
                    unass_ = false;
                }
                StringMap<AssignmentBefore> assThis_;
                assThis_ = varsWhile_.getVariablesRootBefore().get(index_);
                if (!assThis_.getVal(e.getKey()).isAssignedBefore()) {
                    ass_ = false;
                }
                if (!assThis_.getVal(e.getKey()).isUnassignedBefore()) {
                    unass_ = false;
                }
                for (EntryCust<BreakBlock, BreakableBlock> f: _anEl.getBreakables().entryList()) {
                    if (f.getValue() != this) {
                        continue;
                    }
                    StringMap<AssignmentBefore> assB_;
                    assB_ = id_.getVal(f.getKey()).getVariablesRootBefore().get(index_);
                    if (!assB_.contains(e.getKey())) {
                        continue;
                    }
                    if (!assB_.getVal(e.getKey()).isAssignedBefore()) {
                        ass_ = false;
                    }
                    if (!assB_.getVal(e.getKey()).isUnassignedBefore()) {
                        unass_ = false;
                    }
                }
                String key_ = e.getKey();
                LocalVariable lc_ = _an.getLocalVariables().get(index_).getVal(key_);
                String type_ = lc_.getClassName();
                boolean isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(boolType_, type_, _an);
                sm_.put(key_, Assignment.assign(isBool_, ass_, unass_));
            }
            varsAfter_.add(sm_);
            index_++;
        }
        varsWhile_.getVariablesRoot().clear();
        varsWhile_.getVariablesRoot().addAllElts(varsAfter_);
    }
    @Override
    protected AssignedBooleanVariables buildNewAssignedVariable() {
        return new AssignedBooleanVariables();
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
        AnalyzedPageEl p_ = _cont.getAnalyzing();
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
        if (_cont.callsOrException()) {
            return;
        }
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
            _cont.getLastPage().putLocalVar(locName_, locVar_);
            ExpressionLanguage dyn_ = _cont.getLastPage().getCurrentEl(_cont,this, CustList.SECOND_INDEX, native_,CustList.SECOND_INDEX);
            Argument arg_ = dyn_.calculateMember(_cont);
            if (_cont.callsOrException()) {
                return;
            }
            _cont.getLastPage().clearCurrentEls();
            iterStr_ = arg_.getStruct();
            _cont.getLastPage().removeLocalVar(locName_);
            if (iterStr_.isNull()) {
                _cont.setException(new StdStruct(new CustomError(_cont.joinPages()),null_));
                return;
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
            if (_cont.callsOrException()) {
                return;
            }
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
            if (_cont.callsOrException()) {
                return;
            }
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
        ExpressionLanguage el_ = ip_.getCurrentEl(_conf, this, CustList.FIRST_INDEX, false, CustList.FIRST_INDEX);
        Argument arg_ = el_.calculateMember(_conf);
        if (_conf.callsOrException()) {
            return NullStruct.NULL_VALUE;
        }
        Struct ito_ = arg_.getStruct();
        if (ito_.isNull()) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
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
            if (_conf.callsOrException()) {
                return;
            }
        } else {
            hasNext_ = l_.hasNext();
        }
        
        if (hasNext_) {
            incrementLoop(_conf, l_, vars_);
            if (_conf.callsOrException()) {
                if (_conf.calls()) {
                    return;
                }
                l_.setEvaluatingKeepLoop(false);
                return;
            }
        } else {
            _conf.getLastPage().clearCurrentEls();
            l_.setFinished(true);
        }
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
        _conf.getLastPage().putLocalVar(locName_, locVar_);
        ExpressionLanguage dyn_ = _conf.getLastPage().getCurrentEl(_conf,this, CustList.FIRST_INDEX, native_, 2);
        Argument arg_ = dyn_.calculateMember(_conf);
        if (_conf.callsOrException()) {
            return false;
        }
        boolean hasNext_ = (Boolean) arg_.getObject();
        _conf.getLastPage().removeLocalVar(locName_);
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
            _conf.getLastPage().putLocalVar(locName_, locVar_);
            ExpressionLanguage dyn_ = _conf.getLastPage().getCurrentEl(_conf,this, CustList.SECOND_INDEX, native_, 3);
            Argument arg_ = dyn_.calculateMember(_conf);
            if (_conf.callsOrException()) {
                return;
            }
            _conf.getLastPage().removeLocalVar(locName_);
            _conf.getLastPage().clearCurrentEls();
            element_ = arg_.getStruct();
        } else {
            element_ = LgNames.getElement(lv_.getContainer().getInstance(), (int) _l.getIndex(), _conf);
        }
        if (PrimitiveTypeUtil.primitiveTypeNullObject(getClassName(), element_, _conf)) {
            _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),null_));
            return;
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
                _conf.setException(new StdStruct(new CustomError(_conf.joinPages()),cast_));
                return;
            }
        }
        lv_.setStruct(element_);
        lv_.setIndex(lv_.getIndex() + 1);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        if (_indexProcess == 0) {
            return getEl();
        }
        if (_indexProcess == 1) {
            return _context.getClasses().getEqIterator(_native);
        }
        if (_indexProcess == 2) {
            return _context.getClasses().getEqHasNext(_native);
        }
        return _context.getClasses().getEqNext(_native);
    }

    @Override
    public void abruptGroup(Analyzable _an, AnalyzingEl _anEl) {
        if (!_anEl.isReachable(this)) {
            _anEl.completeAbrupt(this);
            _anEl.completeAbruptGroup(this);
        }
    }
}
