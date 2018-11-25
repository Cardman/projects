package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.Templates;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.methods.util.UnexpectedTypeError;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.SwitchBlockStack;
import code.expressionlanguage.structs.EnumerableStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public final class SwitchBlock extends BracedStack implements BreakableBlock {

    private String label;
    private int labelOffset;

    private final String value;
    private int valueOffset;

    private CustList<OperationNode> opValue;

    private boolean enumTest;

    public SwitchBlock(ContextEl _importingPage,BracedBlock _m, OffsetStringInfo _value, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_importingPage, _m, _offset);
        value = _value.getInfo();
        valueOffset = _value.getOffset();
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }
    
    @Override
    public String getRealLabel() {
        return label;
    }

    public String getLabel() {
        return label;
    }

    public int getLabelOffset() {
        return labelOffset;
    }

    public int getValueOffset() {
        return valueOffset;
    }

    public String getValue() {
        return value;
    }

    public ExpressionLanguage getEl() {
        return new ExpressionLanguage(opValue);
    }

    public CustList<OperationNode> getOpValue() {
        return opValue;
    }

    @Override
    public void setAssignmentBeforeChild(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        for (EntryCust<String, Assignment> e: parAss_.getFields().lastValue().entryList()) {
            Assignment ba_ = e.getValue();
            assBl_.getFieldsRootBefore().put(e.getKey(), ba_.assignBefore());
        }
        for (StringMap<Assignment> s: parAss_.getVariables().lastValue()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment ba_ = e.getValue();
                sm_.put(e.getKey(), ba_.assignBefore());
            }
            assBl_.getVariablesRootBefore().add(sm_);
        }
        assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        for (StringMap<Assignment> s: parAss_.getMutableLoop().lastValue()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment ba_ = e.getValue();
                sm_.put(e.getKey(), ba_.assignBefore());
            }
            assBl_.getMutableLoopRootBefore().add(sm_);
        }
        assBl_.getMutableLoopRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(firstChild_, assBl_);
    }
    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        opValue = ElUtil.getAnalyzedOperations(value, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        if (opValue.isEmpty()) {
            return;
        }
        if (opValue.last().isVoidArg(_cont)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, valueOffset));
            un_.setType(opValue.last().getResultClass());
            _cont.getClasses().addError(un_);
        }
        ClassArgumentMatching clArg_ = opValue.last().getResultClass();
        StringList names_ = clArg_.getNames();
        if (names_.size() != 1) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, valueOffset));
            un_.setType(opValue.last().getResultClass());
            _cont.getClasses().addError(un_);
        } else {
            String exp_ = names_.first();
            String id_ = Templates.getIdFromAllTypes(exp_);
            if (!PrimitiveTypeUtil.isPrimitiveOrWrapper(id_, _cont)) {
                if (!StringList.quickEq(id_, _cont.getStandards().getAliasString())) {
                    if (!(_cont.getClassBody(id_) instanceof EnumBlock)) {
                        UnexpectedTypeError un_ = new UnexpectedTypeError();
                        un_.setFileName(getFile().getFileName());
                        un_.setRc(getRowCol(0, valueOffset));
                        un_.setType(opValue.last().getResultClass());
                        _cont.getClasses().addError(un_);
                    } else {
                        enumTest = true;
                    }
                }
            }
        }
        Block first_ = getFirstChild();
        while (first_ != null) {
            Block elt_ = first_;
            if (elt_ instanceof CaseCondition) {
                first_ = first_.getNextSibling();
                continue;
            }
            if (elt_ instanceof DefaultCondition) {
                first_ = first_.getNextSibling();
                continue;
            }
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _cont.getClasses().addError(un_);
        }
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
        CustList<Block> ch_ = Classes.getDirectChildren(this);
        if (ch_.isEmpty()) {
            return false;
        }
        return ch_.last().canBeLastOfBlockGroup();
    }

    @Override
    public void abrupt(Analyzable _an, AnalyzingEl _anEl) {
        Block ch_ = getFirstChild();
        if (ch_ == null) {
            return;
        }
        boolean abrupt_ = true;
        boolean def_ = false;
        CustList<ClassField> enums_ = new CustList<ClassField>();
        CustList<Struct> knowns_ = new CustList<Struct>();
        CustList<CaseCondition> childrenKnowns_ = new CustList<CaseCondition>();
        CustList<CaseCondition> childrenFields_ = new CustList<CaseCondition>();
        while (ch_.getNextSibling() != null) {
            if (ch_ instanceof DefaultCondition) {
                if (def_) {
                    UnexpectedTagName un_ = new UnexpectedTagName();
                    un_.setFileName(ch_.getFile().getFileName());
                    un_.setRc(ch_.getRowCol(0, ch_.getOffset().getOffsetTrim()));
                    _an.getClasses().addError(un_);
                }
                def_ = true;
            } else if (ch_ instanceof CaseCondition){
                CaseCondition case_ = (CaseCondition) ch_;
                OperationNode root_ = case_.getOpValue().last();
                Argument arg_ = root_.getArgument();
                if (arg_ != null) {
                    knowns_.add(arg_.getStruct());
                    childrenKnowns_.add(case_);
                } else {
                    ClassField cl_ = case_.getFieldId();
                    if (cl_ != null) {
                        enums_.add(cl_);
                        childrenFields_.add(case_);
                    }
                }
            }
            ch_ = ch_.getNextSibling();
        }
        if (ch_ instanceof DefaultCondition) {
            if (def_) {
                UnexpectedTagName un_ = new UnexpectedTagName();
                un_.setFileName(ch_.getFile().getFileName());
                un_.setRc(ch_.getRowCol(0, ch_.getOffset().getOffsetTrim()));
                _an.getClasses().addError(un_);
            }
            def_ = true;
        } else if (ch_ instanceof CaseCondition) {
            CaseCondition case_ = (CaseCondition) ch_;
            OperationNode root_ = case_.getOpValue().last();
            Argument arg_ = root_.getArgument();
            if (arg_ != null) {
                knowns_.add(arg_.getStruct());
                childrenKnowns_.add(case_);
            } else {
                ClassField cl_ = case_.getFieldId();
                if (cl_ != null) {
                    enums_.add(cl_);
                    childrenFields_.add(case_);
                }
            }
        }
        int lenTab_ = knowns_.size();
        for (int i = 0; i < lenTab_; i++) {
            for (int j = i + 1; j < lenTab_; j++) {
                if (knowns_.get(i).sameReference(knowns_.get(j))) {
                    //error
                    CaseCondition locCh_ = childrenKnowns_.get(j);
                    UnexpectedTagName un_ = new UnexpectedTagName();
                    un_.setFileName(locCh_.getFile().getFileName());
                    un_.setRc(locCh_.getRowCol(locCh_.getValueOffset(), locCh_.getOffset().getOffsetTrim()));
                    _an.getClasses().addError(un_);
                }
            }
        }
        lenTab_ = enums_.size();
        for (int i = 0; i < lenTab_; i++) {
            for (int j = i + 1; j < lenTab_; j++) {
                if (enums_.get(i).eq(enums_.get(j))) {
                    //error
                    CaseCondition locCh_ = childrenFields_.get(j);
                    UnexpectedTagName un_ = new UnexpectedTagName();
                    un_.setFileName(locCh_.getFile().getFileName());
                    un_.setRc(locCh_.getRowCol(locCh_.getValueOffset(), locCh_.getOffset().getOffsetTrim()));
                    _an.getClasses().addError(un_);
                }
            }
        }
        if (_anEl.canCompleteNormally(ch_)) {
            abrupt_ = false;
        } else if (!def_) {
            abrupt_ = false;
        }
        IdMap<BreakBlock, BreakableBlock> breakables_;
        breakables_ = _anEl.getBreakables();
        for (EntryCust<BreakBlock, BreakableBlock> e: breakables_.entryList()) {
            if (e.getValue() == this && _anEl.isReachable(e.getKey())) {
                abrupt_ = false;
                break;
            }
        }
        if (abrupt_) {
            _anEl.completeAbrupt(this);
            _anEl.completeAbruptGroup(this);
        }
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        Block ch_ = getFirstChild();
        if (ch_ == null) {
            super.setAssignmentAfter(_an, _anEl);
            return;
        }
        boolean def_ = false;
        while (ch_.getNextSibling() != null) {
            if (ch_ instanceof DefaultCondition) {
                def_ = true;
            }
            ch_ = ch_.getNextSibling();
        }
        if (ch_ instanceof DefaultCondition) {
            def_ = true;
        }
        boolean emptyEndCases_ = ch_.getFirstChild() == null;
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables assTar_ = id_.getVal(this);
        StringMap<SimpleAssignment> after_ = new StringMap<SimpleAssignment>();
        CustList<StringMap<SimpleAssignment>> afterVars_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<StringMap<SimpleAssignment>> mutableVars_ = new CustList<StringMap<SimpleAssignment>>();
        after_ =buildAssFieldsAfterSwitch(def_, emptyEndCases_, ch_, _an, _anEl);
        assTar_.getFieldsRoot().putAllMap(after_);
        afterVars_ = buildAssVariablesAfterSwitch(def_, emptyEndCases_, ch_, _an, _anEl);
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().addAllElts(afterVars_);
        mutableVars_ = buildAssMutableLoopAfterSwitch(def_, emptyEndCases_, ch_, _an, _anEl);
        assTar_.getMutableLoopRoot().clear();
        assTar_.getMutableLoopRoot().addAllElts(mutableVars_);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        if (!ip_.noBlock()) {
            RemovableVars bl_ = ip_.getLastStack();
            if (bl_.getBlock() == this) {
                ip_.removeLastBlock();
                processBlock(_cont);
                return;
            }
        }
        ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
        ip_.setGlobalOffset(valueOffset);
        ip_.setOffset(0);
        Argument arg_ =  el_.calculateMember(_cont);
        if (_cont.callsOrException()) {
            return;
        }
        ip_.clearCurrentEls();
        SwitchBlockStack if_ = new SwitchBlockStack();
        Block n_ = getFirstChild();
        CustList<BracedBlock> children_;
        children_ = new CustList<BracedBlock>();
        while (n_ != null) {
            children_.add((BracedBlock)n_);
            if_.setLastVisitedBlock((BracedBlock) n_);
            n_ = n_.getNextSibling();
        }
        if_.setBlock(this);
        Block def_ = null;
        boolean found_ = false;
        if (arg_.isNull()) {
            for (Block b: children_) {
                if (!(b instanceof CaseCondition)) {
                    def_ = b;
                    continue;
                }
                CaseCondition c_ = (CaseCondition) b;
                Argument argRes_ = c_.getOpValue().last().getArgument();
                if (argRes_ == null) {
                    continue;
                }
                if (argRes_.isNull()) {
                    found_ = true;
                    rw_.setBlock(c_);
                }
            }
        } else if (enumTest) {
            EnumerableStruct en_ = (EnumerableStruct) arg_.getStruct();
            for (Block b: children_) {
                if (!(b instanceof CaseCondition)) {
                    def_ = b;
                    continue;
                }
                CaseCondition c_ = (CaseCondition) b;
                OperationNode op_ = c_.getOpValue().last();
                if (op_.getArgument() != null) {
                    continue;
                }
                if (!StringList.quickEq(c_.getFieldId().getFieldName(), en_.getName())) {
                    continue;
                }
                found_ = true;
                rw_.setBlock(c_);
            }
        } else {
            for (Block b: children_) {
                if (!(b instanceof CaseCondition)) {
                    def_ = b;
                    continue;
                }
                CaseCondition c_ = (CaseCondition) b;
                Argument argRes_ = c_.getOpValue().last().getArgument();
                if (!argRes_.getStruct().sameReference(arg_.getStruct())) {
                    continue;
                }
                found_ = true;
                rw_.setBlock(c_);
            }
        }
        if (!found_) {
            if (def_ != null) {
                rw_.setBlock(def_);
            } else {
                if_.setFinished(true);
            }
        }
        ip_.addBlock(if_);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        return getEl();
    }
}
