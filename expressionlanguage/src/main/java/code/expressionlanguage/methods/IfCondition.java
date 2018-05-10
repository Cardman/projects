package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.util.EmptyTagName;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.AssignedBooleanVariables;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.stacks.IfBlockStack;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringMap;

public final class IfCondition extends Condition implements BlockCondition, IncrCurrentGroup {

    public IfCondition(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    public IfCondition(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetStringInfo _condition, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _condition, _offset);
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
    @Override
    public void checkBlocksTree(ContextEl _cont) {
        if (getFirstChild() == null) {
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            page_.setGlobalOffset(getOffset().getOffset());
            page_.setOffset(0);
            EmptyTagName un_ = new EmptyTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _cont.getClasses().getErrorsDet().add(un_);
        }
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        Block next_ = getNextSibling();
        return next_ instanceof ElseIfCondition || next_ instanceof ElseCondition;
    }

    @Override
    public void setAssignmentBeforeChild(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        AssignedBooleanVariables abv_ = (AssignedBooleanVariables) parAss_;
        for (EntryCust<ClassField, BooleanAssignment> e: abv_.getFieldsRootAfter().entryList()) {
            BooleanAssignment ba_ = e.getValue();
            AssignmentBefore ab_ = new AssignmentBefore();
            if (ba_.isAssignedAfterWhenTrue()) {
                ab_.setAssignedBefore(true);
            }
            if (ba_.isUnassignedAfterWhenTrue()) {
                ab_.setUnassignedBefore(true);
            }
            assBl_.getFieldsRootBefore().put(e.getKey(), ab_);
        }
        for (StringMap<BooleanAssignment> s: abv_.getVariablesRootAfter()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, BooleanAssignment> e: s.entryList()) {
                BooleanAssignment ba_ = e.getValue();
                AssignmentBefore ab_ = new AssignmentBefore();
                if (ba_.isAssignedAfterWhenTrue()) {
                    ab_.setAssignedBefore(true);
                }
                if (ba_.isUnassignedAfterWhenTrue()) {
                    ab_.setUnassignedBefore(true);
                }
                sm_.put(e.getKey(), ab_);
            }
            assBl_.getVariablesRootBefore().add(sm_);
        }
        assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(firstChild_, assBl_);
    }
    @Override
       public void setAssignmentBeforeNextSibling(Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables prevAss_ = id_.getVal(this);
        Block nextSibling_ = getNextSibling();
        AssignedBooleanVariables assBool_ = (AssignedBooleanVariables) prevAss_;
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        for (EntryCust<ClassField, BooleanAssignment> e: assBool_.getFieldsRootAfter().entryList()) {
            AssignmentBefore asBef_ = new AssignmentBefore();
            if (e.getValue().isAssignedAfterWhenFalse()) {
                asBef_.setAssignedBefore(true);
            }
            if (e.getValue().isUnassignedAfterWhenFalse()) {
                asBef_.setUnassignedBefore(true);
            }
            assBl_.getFieldsRootBefore().put(e.getKey(), asBef_);
        }
        for (StringMap<BooleanAssignment> s: assBool_.getVariablesRootAfter()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, BooleanAssignment> e: s.entryList()) {
                AssignmentBefore asBef_ = new AssignmentBefore();
                if (e.getValue().isAssignedAfterWhenFalse()) {
                    asBef_.setAssignedBefore(true);
                }
                if (e.getValue().isUnassignedAfterWhenFalse()) {
                    asBef_.setUnassignedBefore(true);
                }
                sm_.put(e.getKey(), asBef_);
            }
            assBl_.getVariablesRootBefore().add(sm_);
        }
        id_.put(nextSibling_, assBl_);
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        if (canBeIncrementedCurGroup()) {
            return;
        }
        Block ch_ = getFirstChild();
        while (ch_.getNextSibling() != null) {
            ch_ = ch_.getNextSibling();
        }
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedBooleanVariables assTar_ = (AssignedBooleanVariables) id_.getVal(this);
        ObjectMap<ClassField,BooleanAssignment> fieldsCond_ = assTar_.getFieldsRootAfter();
        CustList<StringMap<BooleanAssignment>> varsCond_ = assTar_.getVariablesRootAfter();
        AssignedVariables ass_ = id_.getVal(this);
        ObjectMap<ClassField,Assignment> fields_ = ass_.getFieldsRoot();
        CustList<StringMap<Assignment>> vars_ = ass_.getVariablesRoot();
        ObjectMap<ClassField,Assignment> after_ = new ObjectMap<ClassField,Assignment>();
        CustList<StringMap<Assignment>> afterVars_ = new CustList<StringMap<Assignment>>();
        String boolType_ = _an.getStandards().getAliasBoolean();
        for (EntryCust<ClassField,Assignment> e: fields_.entryList()) {
            Assignment ab_ = e.getValue();
            ClassField key_ = e.getKey();
            String classNameDecl_ = key_.getClassName();
            ClassMetaInfo custClass_;
            custClass_ = _an.getClassMetaInfo(classNameDecl_);
            String type_ = custClass_.getFields().getVal(key_.getFieldName()).getType();
            BooleanAssignment condBa_ = fieldsCond_.getVal(key_);
            boolean isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(boolType_, type_, _an);
            boolean assAfter_ = ab_.isAssignedAfter();
            boolean unassAfter_ = ab_.isUnassignedAfter();
            if (_anEl.canCompleteNormally(ch_)) {
                if (assAfter_) {
                    assAfter_ = condBa_.isAssignedAfterWhenFalse();
                }
                if (unassAfter_) {
                    unassAfter_ = condBa_.isUnassignedAfterWhenFalse();
                }
            }
            after_.put(key_, Assignment.assign(isBool_, assAfter_, unassAfter_));
        }
        assTar_.getFieldsRoot().putAllMap(after_);
        for (StringMap<Assignment> s: vars_) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            int index_ = afterVars_.size();
            for (EntryCust<String,Assignment> e: s.entryList()) {
                Assignment ab_ = e.getValue();
                String key_ = e.getKey();
                LocalVariable lc_ = _an.getLocalVariables().get(index_).getVal(key_);
                String type_ = lc_.getClassName();
                BooleanAssignment condBa_ = varsCond_.get(index_).getVal(key_);
                boolean isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(boolType_, type_, _an);
                boolean assAfter_ = ab_.isAssignedAfter();
                boolean unassAfter_ = ab_.isUnassignedAfter();
                if (_anEl.canCompleteNormally(ch_)) {
                    if (assAfter_) {
                        assAfter_ = condBa_.isAssignedAfterWhenFalse();
                    }
                    if (unassAfter_) {
                        unassAfter_ = condBa_.isUnassignedAfterWhenFalse();
                    }
                }
                sm_.put(key_, Assignment.assign(isBool_, assAfter_, unassAfter_));
            }
            afterVars_.add(sm_);
        }
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().addAllElts(afterVars_);
    }
    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public String getTagName() {
        return TAG_IF;
    }
    @Override
    public boolean accessibleCondition() {
        OperationNode op_ = getElCondition().getRoot();
        boolean accessible_ = false;
        Argument arg_ = op_.getArgument();
        if (op_.getArgument() == null) {
            accessible_ = true;
        } else if (!(arg_.getObject() instanceof Boolean)) {
            accessible_ = true;
        } else if ((Boolean)arg_.getObject()) {
            accessible_ = true;
        }
        return accessible_;
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
        IfBlockStack if_ = new IfBlockStack();
        if_.getBlocks().add(this);
        int index_ = getIndexGroup();
        Block n_ = getNextSibling();
        while (n_ != null) {
            if (n_.getIndexGroup() != index_) {
                break;
            }
            if_.getBlocks().add((BracedBlock)n_);
            n_ = n_.getNextSibling();
        }
        if_.setBlock(this);
        if_.setVisitedBlock(CustList.FIRST_INDEX);
        boolean assert_ = evaluateCondition(_cont);
        if (_cont.callsOrException()) {
            return;
        }
        if (assert_) {
            ip_.addBlock(if_);
            if_.setEntered(true);
            rw_.setBlock(getFirstChild());
        } else {
            ip_.addBlock(if_);
            if (if_.lastVisitedBlock() == this) {
                return;
            }
            rw_.setBlock(getNextSibling());
        }
    }

    @Override
    public void exitStack(ContextEl _context) {
        PageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        IfBlockStack if_ = (IfBlockStack) ip_.getLastStack();
        if (if_.lastVisitedBlock() == this) {
            rw_.setBlock(this);
        } else {
            rw_.setBlock(getNextSibling());
        }
    }
}
