package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.util.BadConstructorCall;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.methods.util.UnexpectedTypeError;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.EnumerableStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.SwitchBlockStack;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class SwitchBlock extends BracedStack implements BreakableBlock {

    private final String value;
    private int valueOffset;

    private CustList<OperationNode> opValue;

    private boolean enumTest;

    public SwitchBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        value = _el.getAttribute(ATTRIBUTE_VALUE);
    }

    public SwitchBlock(ContextEl _importingPage, int _indexChild, BracedBlock _m, OffsetStringInfo _value, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        value = _value.getInfo();
        valueOffset = _value.getOffset();
    }

    public int getValueOffset() {
        return valueOffset;
    }

    @Override
    public NatTreeMap<String,String> getClassNames(ContextEl _context) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

    @Override
    public NatTreeMap<Integer,String> getClassNamesOffsets(ContextEl _context) {
        NatTreeMap<Integer,String> tr_ = new NatTreeMap<Integer,String>();
        return tr_;
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
    public void checkBlocksTree(ContextEl _cont) {
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
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
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
        for (EntryCust<ClassField, Assignment> e: parAss_.getFieldsRoot().entryList()) {
            Assignment ba_ = e.getValue();
            assBl_.getFieldsRootBefore().put(e.getKey(), ba_.assignBefore());
        }
        for (StringMap<Assignment> s: parAss_.getVariablesRoot()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment ba_ = e.getValue();
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
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        _cont.setRootAffect(false);
        opValue = ElUtil.getAnalyzedOperations(value, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        if (opValue.isEmpty()) {
            return;
        }
        if (opValue.last().isVoidArg(_cont)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, valueOffset));
            un_.setType(opValue.last().getResultClass().getName());
            _cont.getClasses().getErrorsDet().add(un_);
        }
        String exp_ = opValue.last().getResultClass().getName();
        if (!PrimitiveTypeUtil.isPrimitiveOrWrapper(exp_, _cont)) {
            if (!StringList.quickEq(exp_, _cont.getStandards().getAliasString())) {
                if (!(_cont.getClassBody(exp_) instanceof EnumBlock)) {
                    UnexpectedTypeError un_ = new UnexpectedTypeError();
                    un_.setFileName(getFile().getFileName());
                    un_.setRc(getRowCol(0, valueOffset));
                    un_.setType(opValue.last().getResultClass().getName());
                    _cont.getClasses().getErrorsDet().add(un_);
                } else {
                    enumTest = true;
                }
            }
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
    public void checkCallConstructor(ContextEl _cont) {
        AnalyzedPageEl p_ = _cont.getAnalyzing();
        p_.setGlobalOffset(valueOffset);
        for (OperationNode o: opValue) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                BadConstructorCall call_ = new BadConstructorCall();
                call_.setFileName(getFile().getFileName());
                call_.setRc(getRowCol(0, valueOffset));
                call_.setLocalOffset(getRowCol(o.getFullIndexInEl(), valueOffset));
                _cont.getClasses().getErrorsDet().add(call_);
            }
        }
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
                    _an.getClasses().getErrorsDet().add(un_);
                }
                def_ = true;
            } else {
                CaseCondition case_ = (CaseCondition) ch_;
                OperationNode root_ = case_.getOpValue().last();
                Argument arg_ = root_.getArgument();
                if (arg_ != null) {
                    knowns_.add(arg_.getStruct());
                    childrenKnowns_.add(case_);
                } else {
                    enums_.add(case_.getFieldId());
                    childrenFields_.add(case_);
                }
            }
            ch_ = ch_.getNextSibling();
        }
        if (ch_ instanceof DefaultCondition) {
            if (def_) {
                UnexpectedTagName un_ = new UnexpectedTagName();
                un_.setFileName(ch_.getFile().getFileName());
                un_.setRc(ch_.getRowCol(0, ch_.getOffset().getOffsetTrim()));
                _an.getClasses().getErrorsDet().add(un_);
            }
            def_ = true;
        } else {
            CaseCondition case_ = (CaseCondition) ch_;
            OperationNode root_ = case_.getOpValue().last();
            Argument arg_ = root_.getArgument();
            if (arg_ != null) {
                knowns_.add(arg_.getStruct());
                childrenKnowns_.add(case_);
            } else {
                enums_.add(case_.getFieldId());
                childrenFields_.add(case_);
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
                    _an.getClasses().getErrorsDet().add(un_);
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
                    _an.getClasses().getErrorsDet().add(un_);
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
        AssignedVariables assLast_ = id_.getVal(ch_);
        String boolType_ = _an.getStandards().getAliasBoolean();
        ObjectMap<ClassField,Assignment> after_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> afterVars_ = new CustList<StringMap<Assignment>>();
        for (EntryCust<ClassField,Assignment> e: assTar_.getFields().lastValue().entryList()) {
            boolean ass_ = true;
            boolean unass_ = true;
            if (!(def_ ||e.getValue().isAssignedAfter())){
                ass_ = false;
            }
            if (!(def_ || e.getValue().isUnassignedAfter())){
                unass_ = false;
            }
            if (!(!emptyEndCases_ || e.getValue().isAssignedAfter())){
                ass_ = false;
            }
            if (!(!emptyEndCases_ || e.getValue().isUnassignedAfter())){
                unass_ = false;
            }
            if (_anEl.canCompleteNormally(ch_)) {
                ObjectMap<ClassField, Assignment> l_;
                l_ = assLast_.getFieldsRoot();
                if (!l_.getVal(e.getKey()).isAssignedAfter()) {
                    ass_ = false;
                }
                if (!l_.getVal(e.getKey()).isUnassignedAfter()) {
                    unass_ = false;
                }
            }
            for (EntryCust<BreakBlock, BreakableBlock> f: _anEl.getBreakables().entryList()) {
                if (f.getValue() != this) {
                    continue;
                }
                AssignedVariables assBr_ = id_.getVal(f.getKey());
                ObjectMap<ClassField, Assignment> l_;
                l_ = assBr_.getFieldsRoot();
                if (!l_.getVal(e.getKey()).isAssignedAfter()) {
                    ass_ = false;
                }
                if (!l_.getVal(e.getKey()).isUnassignedAfter()) {
                    unass_ = false;
                }
            }
            ClassField key_ = e.getKey();
            String classNameDecl_ = key_.getClassName();
            ClassMetaInfo custClass_;
            custClass_ = _an.getClassMetaInfo(classNameDecl_);
            String type_ = custClass_.getFields().getVal(key_.getFieldName()).getType();
            boolean isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(boolType_, type_, _an);
            after_.put(key_, Assignment.assign(isBool_, ass_, unass_));
        }
        assTar_.getFieldsRoot().putAllMap(after_);
        for (StringMap<Assignment> s: assTar_.getVariables().lastValue()) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            int index_ = afterVars_.size();
            for (EntryCust<String,Assignment> e: s.entryList()) {
                boolean ass_ = true;
                boolean unass_ = true;
                if (!(def_ || e.getValue().isAssignedAfter())){
                    ass_ = false;
                }
                if (!(def_ || e.getValue().isUnassignedAfter())){
                    unass_ = false;
                }
                if (!(!emptyEndCases_ || e.getValue().isAssignedAfter())){
                    ass_ = false;
                }
                if (!(!emptyEndCases_ || e.getValue().isUnassignedAfter())){
                    unass_ = false;
                }
                if (_anEl.canCompleteNormally(ch_)) {
                    CustList<StringMap<Assignment>> l_;
                    l_ = assLast_.getVariablesRoot();
                    if (index_ < l_.size() && l_.get(index_).contains(e.getKey())) {
                        if (!l_.get(index_).getVal(e.getKey()).isAssignedAfter()) {
                            ass_ = false;
                        }
                        if (!l_.get(index_).getVal(e.getKey()).isUnassignedAfter()) {
                            unass_ = false;
                        }
                    }
                }
                for (EntryCust<BreakBlock, BreakableBlock> f: _anEl.getBreakables().entryList()) {
                    if (f.getValue() != this) {
                        continue;
                    }
                    AssignedVariables assBr_ = id_.getVal(f.getKey());
                    CustList<StringMap<Assignment>> l_;
                    l_ = assBr_.getVariablesRoot();
                    if (index_ >= l_.size()) {
                        continue;
                    }
                    if (!l_.get(index_).contains(e.getKey())) {
                        continue;
                    }
                    if (!l_.get(index_).getVal(e.getKey()).isAssignedAfter()) {
                        ass_ = false;
                    }
                    if (!l_.get(index_).getVal(e.getKey()).isUnassignedAfter()) {
                        unass_ = false;
                    }
                }
                String key_ = e.getKey();
                LocalVariable lc_ = _an.getLocalVariables().get(index_).getVal(key_);
                String type_ = lc_.getClassName();
                boolean isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(boolType_, type_, _an);
                sm_.put(key_, Assignment.assign(isBool_, ass_, unass_));
            }
            afterVars_.add(sm_);
        }
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().addAllElts(afterVars_);
    }

    @Override
    public String getTagName() {
        return TAG_SWITCH;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        if (!ip_.noBlock()) {
            RemovableVars bl_ = ip_.getLastStack();
            if (bl_.getBlock() == this) {
                ip_.removeLastBlock();
                processBlock(_cont);
                return;
            }
        }
        SwitchBlockStack if_ = new SwitchBlockStack();
        Block n_ = getFirstChild();
        while (n_ != null) {
            if_.getBlocks().add((BracedBlock)n_);
            n_ = n_.getNextSibling();
        }
        if_.setBlock(this);
        ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, CustList.FIRST_INDEX, false, CustList.FIRST_INDEX);
        ip_.setGlobalOffset(valueOffset);
        ip_.setOffset(0);
        Argument arg_ =  el_.calculateMember(_cont);
        if (_cont.callsOrException()) {
            return;
        }
        el_.setCurrentOper(null);
        ip_.clearCurrentEls();
        if_.setStruct(arg_.getStruct());
        Block def_ = null;
        boolean found_ = false;
        if (arg_.isNull()) {
            for (Block b: if_.getBlocks()) {
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
            for (Block b: if_.getBlocks()) {
                if (!(b instanceof CaseCondition)) {
                    def_ = b;
                    continue;
                }
                CaseCondition c_ = (CaseCondition) b;
                OperationNode op_ = c_.getOpValue().last();
                if (op_.getArgument() != null) {
                    continue;
                }
                if (!c_.getFieldId().eq(new ClassField(op_.getResultClass().getName(), en_.getName()))) {
                    continue;
                }
                found_ = true;
                rw_.setBlock(c_);
            }
        } else {
            for (Block b: if_.getBlocks()) {
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
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return getEl();
    }
}
