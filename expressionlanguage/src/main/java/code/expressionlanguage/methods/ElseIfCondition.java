package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.opers.util.AssignedBooleanVariables;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.stacks.IfBlockStack;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.NatTreeMap;
import code.util.ObjectMap;
import code.util.StringMap;

public final class ElseIfCondition extends Condition implements BlockCondition, IncrCurrentGroup, IncrNextGroup {

    public ElseIfCondition(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    public ElseIfCondition(ContextEl _importingPage, int _indexChild,
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
        Block prev_ = getPreviousSibling();
        boolean existIf_ = false;
        while (prev_ != null) {
            if (prev_ instanceof ElseIfCondition) {
                prev_ = prev_.getPreviousSibling();
                continue;
            }
            existIf_ = prev_ instanceof IfCondition;
            break;
        }
        if (!existIf_ || getFirstChild() == null) {
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
    boolean canBeIncrementedNextGroup() {
        return true;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        Block next_ = getNextSibling();
        return next_ instanceof ElseIfCondition || next_ instanceof ElseCondition;
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        if (canBeIncrementedCurGroup()) {
            return;
        }
        CustList<Block> prev_ = new CustList<Block>();
        Block pBlock_ = getPreviousSibling();
        while (!(pBlock_ instanceof IfCondition)) {
//            Block ch_ = pBlock_.getFirstChild();
//            while (ch_.getNextSibling() != null) {
//                ch_ = ch_.getNextSibling();
//            }
            prev_.add(pBlock_);
            pBlock_ = pBlock_.getPreviousSibling();
        }
//        Block chIf_ = pBlock_.getFirstChild();
//        while (chIf_.getNextSibling() != null) {
//            chIf_ = chIf_.getNextSibling();
//        }
        prev_.add(pBlock_);
//        Block ch_ = getFirstChild();
//        while (ch_.getNextSibling() != null) {
//            ch_ = ch_.getNextSibling();
//        }
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
            boolean assAfter_ = ab_.isAssignedAfter();
            boolean unassAfter_ = ab_.isUnassignedAfter();
            if (assAfter_) {
                for (Block p: prev_) {
                    if (!_anEl.canCompleteNormally(p)) {
                        continue;
                    }
                    AssignedVariables assLoc_ = id_.getVal(p);
                    ObjectMap<ClassField,Assignment> fieldsLoc_ = assLoc_.getFieldsRoot();
                    if (!fieldsLoc_.getVal(key_).isAssignedAfter()) {
                        assAfter_ = false;
                        break;
                    }
                }
            }
            if (unassAfter_) {
                for (Block p: prev_) {
                    if (!_anEl.canCompleteNormally(p)) {
                        continue;
                    }
                    AssignedVariables assLoc_ = id_.getVal(p);
                    ObjectMap<ClassField,Assignment> fieldsLoc_ = assLoc_.getFieldsRoot();
                    if (!fieldsLoc_.getVal(key_).isUnassignedAfter()) {
                        unassAfter_ = false;
                        break;
                    }
                }
            }
            boolean isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(boolType_, type_, _an);
            if (assAfter_ || _anEl.canCompleteNormally(this)) {
                assAfter_ = condBa_.isAssignedAfterWhenFalse();
            }
            if (unassAfter_ || _anEl.canCompleteNormally(this)) {
                unassAfter_ = condBa_.isUnassignedAfterWhenFalse();
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
                boolean assAfter_ = ab_.isAssignedAfter();
                boolean unassAfter_ = ab_.isUnassignedAfter();
                if (assAfter_) {
                    for (Block p: prev_) {
                        if (!_anEl.canCompleteNormally(p)) {
                            continue;
                        }
                        AssignedVariables assLoc_ = id_.getVal(p);
                        StringMap<Assignment> fieldsLoc_ = assLoc_.getVariablesRoot().get(index_);
                        if (!fieldsLoc_.getVal(key_).isAssignedAfter()) {
                            assAfter_ = false;
                            break;
                        }
                    }
                }
                if (unassAfter_) {
                    for (Block p: prev_) {
                        if (!_anEl.canCompleteNormally(p)) {
                            continue;
                        }
                        AssignedVariables assLoc_ = id_.getVal(p);
                        StringMap<Assignment> fieldsLoc_ = assLoc_.getVariablesRoot().get(index_);
                        if (!fieldsLoc_.getVal(key_).isUnassignedAfter()) {
                            unassAfter_ = false;
                            break;
                        }
                    }
                }
                boolean isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(boolType_, type_, _an);
                if (assAfter_ || _anEl.canCompleteNormally(this)) {
                    assAfter_ = condBa_.isAssignedAfterWhenFalse();
                }
                if (unassAfter_ || _anEl.canCompleteNormally(this)) {
                    unassAfter_ = condBa_.isUnassignedAfterWhenFalse();
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
        return TAG_ELSEIF;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        IfBlockStack if_ = (IfBlockStack) ip_.getLastStack();
        if_.setVisitedBlock(getIndexInGroup());
        if (!if_.isEntered()) {
            boolean assert_ = evaluateCondition(_cont);
            if (_cont.callsOrException()) {
                return;
            }
            if (assert_) {
                if_.setEntered(true);
                rw_.setBlock(getFirstChild());
                return;
            }
        }
        if (if_.getBlocks().last() == this) {
            ip_.removeLastBlock();
            processBlock(_cont);
            return;
        }
        rw_.setBlock(getNextSibling());
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
    @Override
    public void reach(Analyzable _an, AnalyzingEl _anEl) {
        Block p_ = getPreviousSibling();
        while (!(p_ instanceof IfCondition)) {
            p_ = p_.getPreviousSibling();
        }
        if (_anEl.isReachable(p_)) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }
}
