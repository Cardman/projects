package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ConstType;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.CustomError;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.OperationsSequence;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.Templates;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.methods.util.DuplicateVariable;
import code.expressionlanguage.methods.util.EmptyTagName;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.methods.util.UnexpectedOperationAffect;
import code.expressionlanguage.opers.AffectationOperation;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.SemiAffectationOperation;
import code.expressionlanguage.opers.SettableAbstractFieldOperation;
import code.expressionlanguage.opers.SettableElResult;
import code.expressionlanguage.opers.VariableOperation;
import code.expressionlanguage.opers.util.AssignedBooleanVariables;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.FieldMetaInfo;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.SimpleAssignment;
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
    public void setAssignmentBeforeChild(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        for (EntryCust<ClassField, SimpleAssignment> e: parAss_.getFieldsRoot().entryList()) {
            SimpleAssignment ba_ = e.getValue();
            assBl_.getFieldsRootBefore().put(e.getKey(), ba_.assignBefore());
        }
        for (StringMap<SimpleAssignment> s: parAss_.getVariablesRoot()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, SimpleAssignment> e: s.entryList()) {
                SimpleAssignment ba_ = e.getValue();
                sm_.put(e.getKey(), ba_.assignBefore());
            }
            assBl_.getVariablesRootBefore().add(sm_);
        }
        assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(firstChild_, assBl_);
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
            fields_.put(e.getKey(), e.getValue().copy());
        }
        vars_.getFieldsBefore().put(_root, fields_);
        for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                sm_.put(e.getKey(), e.getValue().copy());
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
            fieldsHypot_.put(e.getKey(), e.getValue().copy());
        }
        for (StringMap<AssignmentBefore> s: varsWhile_.getVariablesRootBefore()) {
            StringMap<AssignmentBefore> sm_;
            sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                sm_.put(e.getKey(), e.getValue().copy());
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
        for (EntryCust<ClassField,SimpleAssignment> f: vars_.getFieldsRoot().entryList()) {
            if (!f.getValue().isUnassignedAfter()) {
                fieldsHypot_.getVal(f.getKey()).setUnassignedBefore(false);
            }
        }
        for (StringMap<SimpleAssignment> s: vars_.getVariablesRoot()) {
            if (index_ >= varsHypot_.size()) {
                continue;
            }
            for (EntryCust<String,SimpleAssignment> f: s.entryList()) {
                if (!f.getValue().isUnassignedAfter()) {
                    varsHypot_.get(index_).getVal(f.getKey()).setUnassignedBefore(false);
                }
            }
            index_++;
        }
        varsWhile_.getFieldsRootBefore().putAllMap(fieldsHypot_);
        varsWhile_.getVariablesRootBefore().clear();
        varsWhile_.getVariablesRootBefore().addAllElts(varsHypot_);
        processFinalFields(_an, _anEl, allDesc_, fieldsHypot_, conts_);
        processFinalVars(_an, _anEl, allDesc_, varsHypot_, conts_);
        ObjectMap<ClassField,SimpleAssignment> fieldsAfter_;
        fieldsAfter_ = new ObjectMap<ClassField,SimpleAssignment>();
        CustList<StringMap<SimpleAssignment>> varsAfter_;
        varsAfter_ = new CustList<StringMap<SimpleAssignment>>();
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
            fieldsAfter_.put(key_, Assignment.assignClassic(ass_, unass_));
        }
        varsWhile_.getFieldsRoot().putAllMap(fieldsAfter_);
        index_ = 0;
        for (StringMap<BooleanAssignment> s: varsWhile_.getVariablesRootAfter()) {
            StringMap<SimpleAssignment> sm_ = new StringMap<SimpleAssignment>();
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
                sm_.put(key_, Assignment.assignClassic(ass_, unass_));
            }
            varsAfter_.add(sm_);
            index_++;
        }
        varsWhile_.getVariablesRoot().clear();
        varsWhile_.getVariablesRoot().addAllElts(varsAfter_);
    }
    private void processFinalFields(Analyzable _an,AnalyzingEl _anEl,
            IdMap<Block, AssignedVariables> _allDesc,
            ObjectMap<ClassField, AssignmentBefore> _fields,
            CustList<ContinueBlock> _conts) {
        AssignedVariables vars_;
        for (EntryCust<ClassField,AssignmentBefore> e: _fields.entryList()) {
            if (e.getValue().isUnassignedBefore()) {
                continue;
            }
            if (e.getValue().isAssignedBefore()) {
                continue;
            }
            ClassField key_ = e.getKey();
            ClassMetaInfo cl_ = _an.getClassMetaInfo(key_.getClassName());
            FieldMetaInfo fm_ = cl_.getFields().getVal(key_.getFieldName());
            if (!fm_.isFinalField()) {
                continue;
            }
            for (EntryCust<Block, AssignedVariables> d: _allDesc.entryList()) {
                vars_ = d.getValue();
                Block next_ = d.getKey();
                boolean take_ = takeContinue(next_, vars_, _conts, _anEl);
                if (!take_) {
                    continue;
                }
                //next siblings of d
                processFinalFields(next_, _an, vars_, key_);
            }
        }
    }

    private void processFinalFields(Block _curBlock, Analyzable _an,AssignedVariables _vars, ClassField _field) {
        for (EntryCust<OperationNode, ObjectMap<ClassField,AssignmentBefore>> f: _vars.getFieldsBefore().entryList()) {
            if (!(f.getKey() instanceof AffectationOperation)) {
                if (!(f.getKey() instanceof SemiAffectationOperation)) {
                    continue;
                }
                //Error
                OperationNode cst_ = f.getKey();
                cst_.setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _an);
                UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                un_.setFileName(_an.getCurrentFileName());
                un_.setRc(_curBlock.getRowCol(_an.getOffset(),_curBlock.getOffset().getOffsetTrim()));
                _an.getClasses().getErrorsDet().add(un_);
                continue;
            }
            AffectationOperation aff_ = (AffectationOperation) f.getKey();
            SettableElResult set_ = aff_.getSettable();
            if (!(set_ instanceof SettableAbstractFieldOperation)) {
                continue;
            }
            SettableAbstractFieldOperation cst_ = (SettableAbstractFieldOperation) set_;
            if (!cst_.getFieldId().eq(_field)) {
                continue;
            }
            cst_.setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _an);
            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
            un_.setFileName(_an.getCurrentFileName());
            un_.setRc(_curBlock.getRowCol(_an.getOffset(),_curBlock.getOffset().getOffsetTrim()));
            _an.getClasses().getErrorsDet().add(un_);
        }
    }
    private void processFinalVars(Analyzable _an,AnalyzingEl _anEl,
            IdMap<Block, AssignedVariables> _allDesc,
            CustList<StringMap<AssignmentBefore>> _fields,
            CustList<ContinueBlock> _conts) {
        AssignedVariables vars_;
        int index_ = 0;
        for (StringMap<AssignmentBefore> s: _fields) {
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                if (e.getValue().isUnassignedBefore()) {
                    continue;
                }
                if (e.getValue().isAssignedBefore()) {
                    continue;
                }
                if (!_an.getLocalVariables().isValidIndex(index_)) {
                    continue;
                }
                String key_ = e.getKey();
                StringMap<LocalVariable> varLocs_;
                varLocs_ = _an.getLocalVariables().get(index_);
                LocalVariable varLoc_ = varLocs_.getVal(key_);
                if (!varLoc_.isFinalVariable()) {
                    continue;
                }
                for (EntryCust<Block, AssignedVariables> d: _allDesc.entryList()) {
                    vars_ = d.getValue();
                    Block next_ = d.getKey();
                    boolean take_ = takeContinue(next_, vars_, _conts, _anEl);
                    if (!take_) {
                        continue;
                    }
                    //next siblings of d
                    processFinalVars(next_, _an, vars_, key_);
                }
            }
            index_++;
        }
        
    }
    private void processFinalVars(Block _curBlock, Analyzable _an,AssignedVariables _vars, String _field) {
        for (EntryCust<OperationNode,CustList<StringMap<AssignmentBefore>>> f: _vars.getVariablesBefore().entryList()) {
            if (!(f.getKey() instanceof AffectationOperation)) {
                if (!(f.getKey() instanceof SemiAffectationOperation)) {
                    continue;
                }
                //Error
                OperationNode cst_ = f.getKey();
                cst_.setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _an);
                UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
                un_.setFileName(_an.getCurrentFileName());
                un_.setRc(_curBlock.getRowCol(_an.getOffset(),_curBlock.getOffset().getOffsetTrim()));
                _an.getClasses().getErrorsDet().add(un_);
                continue;
            }
            AffectationOperation aff_ = (AffectationOperation) f.getKey();
            SettableElResult set_ = aff_.getSettable();
            if (!(set_ instanceof VariableOperation)) {
                continue;
            }
            VariableOperation cst_ = (VariableOperation) set_;
            OperationsSequence op_ = cst_.getOperations();
            if (op_.getConstType() != ConstType.LOC_VAR) {
                continue;
            }
            String originalStr_ = op_.getValues().getValue(CustList.FIRST_INDEX);
            String str_ = originalStr_.trim();
            if (!StringList.quickEq(str_, _field)) {
                continue;
            }
            cst_.setRelativeOffsetPossibleAnalyzable(cst_.getIndexInEl(), _an);
            UnexpectedOperationAffect un_ = new UnexpectedOperationAffect();
            un_.setFileName(_an.getCurrentFileName());
            un_.setRc(_curBlock.getRowCol(_an.getOffset(),_curBlock.getOffset().getOffsetTrim()));
            _an.getClasses().getErrorsDet().add(un_);
        }
    }
    private boolean takeContinue(Block _b,AssignedVariables _ass, CustList<ContinueBlock> _conts, AnalyzingEl _anEl) {
        Block next_ = _b;
        boolean take_ = false;
        while (next_ != null) {
            if (next_ instanceof BracedBlock) {
                BracedBlock possAnc_ = (BracedBlock) next_;
                for (ContinueBlock c: _conts) {
                    if (_anEl.getContinuablesAncestors().getVal(c).getVal(this).containsObj(possAnc_)) {
                        take_ = true;
                        break;
                    }
                }
                if (take_) {
                    break;
                }
            }
            if (next_ instanceof ContinueBlock) {
                take_ = true;
                break;
            }
            next_ = next_.getNextSibling();
        }
        if (next_ == null && _b.getParent() == this) {
            take_ = true;
        }
        return take_;
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
            Boolean has_ = iteratorHasNext(_cont);
            if (has_ == null) {
                return;
            }
            finished_ = !has_;
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
            Boolean has_ = iteratorHasNext(_conf);
            if (has_ == null) {
                return;
            }
            hasNext_ = has_;
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

    private Boolean iteratorHasNext(ContextEl _conf) {
        PageEl ip_ = _conf.getLastPage();
        LgNames stds_ = _conf.getStandards();
        LoopBlockStack l_ = (LoopBlockStack) ip_.getLastStack();
        Struct strIter_ = l_.getStructIterator();
        String locName_ = _conf.getClasses().getHasNextVar(nativeCmp);
        LocalVariable locVar_ = new LocalVariable();
        locVar_.setClassName(stds_.getStructClassName(strIter_, _conf));
        locVar_.setStruct(strIter_);
        _conf.getLastPage().putLocalVar(locName_, locVar_);
        ExpressionLanguage dyn_ = _conf.getLastPage().getCurrentEl(_conf,this, CustList.FIRST_INDEX, nativeCmp, 2);
        Argument arg_ = dyn_.calculateMember(_conf);
        if (_conf.callsOrException()) {
            return null;
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
