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
import code.expressionlanguage.opers.ConstantOperation;
import code.expressionlanguage.opers.DotOperation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.StaticAccessOperation;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stacks.SwitchBlockStack;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.StringMap;

public final class CaseCondition extends BracedStack implements StackableBlockGroup, IncrCurrentGroup, IncrNextGroup {

    private final String value;
    private CustList<OperationNode> opValue;
    private boolean possibleSkipNexts;

    private int valueOffset;

    public CaseCondition(Element _el, ContextEl _importingPage,
            int _indexChild, BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        value = _el.getAttribute(ATTRIBUTE_VALUE);
    }

    public CaseCondition(ContextEl _importingPage,
            int _indexChild, BracedBlock _m, OffsetStringInfo _value, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        value = _value.getInfo();
        valueOffset = _value.getOffset();
    }

    public int getValueOffset() {
        return valueOffset;
    }

    public String getValue() {
        return value;
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
    public ExpressionLanguage getValueEl() {
        return new ExpressionLanguage(opValue);
    }

    @Override
    boolean isAlwaysExitable() {
        return getFirstChild() == null || !isPossibleSkipNexts();
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        BracedBlock b_ = getParent();
        if (!(b_ instanceof SwitchBlock)) {
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _cont.getClasses().getErrorsDet().add(un_);
        }
    }

    boolean isPossibleSkipNexts() {
        return possibleSkipNexts;
    }

    void setPossibleSkipNexts(boolean _possibleSkipNexts) {
        possibleSkipNexts = _possibleSkipNexts;
    }

    @Override
    public void setAssignmentBeforeNextSibling(Analyzable _an, AnalyzingEl _anEl) {
        BracedBlock br_ = getParent();
         IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(br_);
        AssignedVariables prevAss_ = id_.getVal(this);
        Block nextSibling_ = getNextSibling();
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        for (EntryCust<ClassField, Assignment> e: parAss_.getFieldsRoot().entryList()) {
            Assignment ba_ = e.getValue();
            AssignmentBefore ab_ = new AssignmentBefore();
            if (ba_.isAssignedAfter() && prevAss_.getFieldsRoot().getVal(e.getKey()).isAssignedAfter()) {
                ab_.setAssignedBefore(true);
            }
            if (ba_.isUnassignedAfter() && prevAss_.getFieldsRoot().getVal(e.getKey()).isUnassignedAfter()) {
                ab_.setUnassignedBefore(true);
            }
            assBl_.getFieldsRootBefore().put(e.getKey(), ab_);
        }
        for (StringMap<Assignment> s: parAss_.getVariablesRoot()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            int index_ = assBl_.getVariablesRootBefore().size();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                Assignment ba_ = e.getValue();
                AssignmentBefore ab_ = new AssignmentBefore();
                if (ba_.isAssignedAfter() && prevAss_.getVariablesRoot().get(index_).getVal(e.getKey()).isAssignedAfter()) {
                    ab_.setAssignedBefore(true);
                }
                if (ba_.isUnassignedAfter() && prevAss_.getVariablesRoot().get(index_).getVal(e.getKey()).isUnassignedAfter()) {
                    ab_.setUnassignedBefore(true);
                }
                sm_.put(e.getKey(), ab_);
            }
            assBl_.getVariablesRootBefore().add(sm_);
        }
        assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(nextSibling_, assBl_);
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
        if (opValue.last().getArgument() == null) {
            OperationNode last_ = opValue.last();
            if (!(last_ instanceof ConstantOperation)) {
                if (!(last_ instanceof DotOperation)) {
                    UnexpectedTypeError un_ = new UnexpectedTypeError();
                    un_.setFileName(getFile().getFileName());
                    un_.setRc(getRowCol(0, valueOffset));
                    un_.setType(opValue.last().getResultClass().getName());
                    _cont.getClasses().getErrorsDet().add(un_);
                    return;
                }
                DotOperation d_ = (DotOperation) last_;
                if (!(d_.getFirstChild() instanceof StaticAccessOperation)) {
                    UnexpectedTypeError un_ = new UnexpectedTypeError();
                    un_.setFileName(getFile().getFileName());
                    un_.setRc(getRowCol(0, valueOffset));
                    un_.setType(opValue.last().getResultClass().getName());
                    _cont.getClasses().getErrorsDet().add(un_);
                    return;
                }
                if (!(d_.getFirstChild().getNextSibling() instanceof ConstantOperation)) {
                    UnexpectedTypeError un_ = new UnexpectedTypeError();
                    un_.setFileName(getFile().getFileName());
                    un_.setRc(getRowCol(0, valueOffset));
                    un_.setType(opValue.last().getResultClass().getName());
                    _cont.getClasses().getErrorsDet().add(un_);
                    return;
                }
                last_ = d_.getFirstChild().getNextSibling();
            }
            ConstantOperation cst_ = (ConstantOperation) last_;
            ClassField clField_ = cst_.getFieldId();
            if (clField_ == null) {
                UnexpectedTypeError un_ = new UnexpectedTypeError();
                un_.setFileName(getFile().getFileName());
                un_.setRc(getRowCol(0, valueOffset));
                un_.setType(opValue.last().getResultClass().getName());
                _cont.getClasses().getErrorsDet().add(un_);
                return;
            }
            if (!cst_.getFieldMetaInfo().isEnumField()) {
                UnexpectedTypeError un_ = new UnexpectedTypeError();
                un_.setFileName(getFile().getFileName());
                un_.setRc(getRowCol(0, valueOffset));
                un_.setType(opValue.last().getResultClass().getName());
                _cont.getClasses().getErrorsDet().add(un_);
                return;
            }
        }
        String resCase_ = opValue.last().getResultClass().getName();
        SwitchBlock sw_ = (SwitchBlock) getParent();
        String resSwitch_ = sw_.getOpValue().last().getResultClass().getName();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(resSwitch_, resCase_, _cont)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, valueOffset));
            un_.setType(opValue.last().getResultClass().getName());
            _cont.getClasses().getErrorsDet().add(un_);
        }
    }

    public ClassField getFieldId() {
        OperationNode last_ = opValue.last();
        if (!(last_ instanceof ConstantOperation)) {
            DotOperation d_ = (DotOperation) last_;
            last_ = d_.getFirstChild().getNextSibling();
            return ((ConstantOperation)last_).getFieldId();
        }
        return ((ConstantOperation)last_).getFieldId();
    }
    public CustList<OperationNode> getOpValue() {
        return opValue;
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return true;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return true;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
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
    public String getTagName() {
        return TAG_CASE;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        SwitchBlockStack sw_ = (SwitchBlockStack) ip_.getLastStack();
        Struct str_ = sw_.getStruct();
        Argument virtualArg_ = new Argument();
        virtualArg_.setStruct(str_);
        sw_.setVisitedBlock(getIndexInGroup());
        if (sw_.isEntered()) {
            if (!hasChildNodes()) {
                if (sw_.lastVisitedBlock() == this) {
                    sw_.setFinished(true);
                    rw_.setBlock(sw_.getBlock());
                    return;
                }
                rw_.setBlock(getNextSibling());
                return;
            }
            rw_.setBlock(getFirstChild());
            return;
        } else {
            ip_.setGlobalOffset(valueOffset);
            ip_.setOffset(0);
            if (hasChildNodes()) {
                sw_.setEntered(true);
            } else {
                if (sw_.lastVisitedBlock() != this) {
                    sw_.setEntered(true);
                    rw_.setBlock(getNextSibling());
                    return;
                } else {
                    sw_.setFinished(true);
                    rw_.setBlock(sw_.getBlock());
                    return;
                }
            }
            rw_.setBlock(getFirstChild());
            return;
        }
    }

    @Override
    public void exitStack(ContextEl _context) {
        PageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        SwitchBlockStack if_ = (SwitchBlockStack) ip_.getLastStack();
        if (if_.lastVisitedBlock() == this) {
            if_.setFinished(true);
            rw_.setBlock(if_.getBlock());
        } else {
            rw_.setBlock(getNextSibling());
        }
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return getValueEl();
    }
}
