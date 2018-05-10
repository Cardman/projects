package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.ObjectMap;
import code.util.StringMap;

public abstract class BracedBlock extends Block implements BracedBlockInt {

    private Block firstChild;

    BracedBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _indexChild, _m);
    }

    BracedBlock(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetsBlock _offset) {
        super(_indexChild, _m, _offset);
    }

    public final void appendChild(Block _child) {
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        Block child_ = firstChild;
        while (true) {
            Block sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                _child.setPreviousSibling(child_);
                child_.setNextSibling(_child);
                child_.setupNextSiblingGroup();
                return;
            }
            child_ = sibling_;
        }
    }

    @Override
    public void abrupt(Analyzable _an, AnalyzingEl _anEl) {
        Block ch_ = getFirstChild();
        if (ch_ == null) {
            if (!_anEl.isReachable(this)) {
                _anEl.completeAbrupt(this);
                _anEl.completeAbruptGroup(this);
            }
            return;
        }
        while (ch_.getNextSibling() != null) {
            ch_ = ch_.getNextSibling();
        }
        if (!_anEl.canCompleteNormallyGroup(ch_)) {
            _anEl.completeAbrupt(this);
        }
    }
    public void setAssignmentBeforeChild(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        assBl_.getFieldsRootBefore().putAllMap(parAss_.getFieldsRootBefore());
        assBl_.getVariablesRootBefore().addAllElts(parAss_.getVariablesRootBefore());
        assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(firstChild_, assBl_);
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        Block ch_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        if (ch_ == null) {
            String boolType_ = _an.getStandards().getAliasBoolean();
            AssignedVariables ass_ = id_.getVal(this);
            ObjectMap<ClassField,AssignmentBefore> fields_ = ass_.getFieldsRootBefore();
            CustList<StringMap<AssignmentBefore>> variables_ = ass_.getVariablesRootBefore();
            ObjectMap<ClassField,Assignment> after_ = new ObjectMap<ClassField,Assignment>();
            CustList<StringMap<Assignment>> afterVars_ = new CustList<StringMap<Assignment>>();
            for (EntryCust<ClassField,AssignmentBefore> e: fields_.entryList()) {
                AssignmentBefore ab_ = e.getValue();
                ClassField key_ = e.getKey();
                String classNameDecl_ = key_.getClassName();
                ClassMetaInfo custClass_;
                custClass_ = _an.getClassMetaInfo(classNameDecl_);
                String type_ = custClass_.getFields().getVal(key_.getFieldName()).getType();
                boolean isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(boolType_, type_, _an);
                after_.put(key_, ab_.assignAfter(isBool_));
            }
            ass_.getFieldsRoot().putAllMap(after_);
            for (StringMap<AssignmentBefore> s: variables_) {
                StringMap<Assignment> sm_ = new StringMap<Assignment>();
                int index_ = afterVars_.size();
                for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                    AssignmentBefore ab_ = e.getValue();
                    String key_ = e.getKey();
                    LocalVariable lc_ = _an.getLocalVariables().get(index_).getVal(key_);
                    String type_ = lc_.getClassName();
                    boolean isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(boolType_, type_, _an);
                    sm_.put(key_, ab_.assignAfter(isBool_));
                }
                afterVars_.add(sm_);
            }
            ass_.getVariablesRoot().clear();
            ass_.getVariablesRoot().addAllElts(afterVars_);
            return;
        }
        if (!_anEl.canCompleteNormally(this)) {
            return;
        }
        while (ch_.getNextSibling() != null) {
            ch_ = ch_.getNextSibling();
        }
        AssignedVariables assTar_ = id_.getVal(this);
        AssignedVariables ass_ = id_.getVal(ch_);
        ObjectMap<ClassField,Assignment> fields_ = ass_.getFieldsRoot();
        CustList<StringMap<Assignment>> variables_ = ass_.getVariablesRoot();
        assTar_.getFieldsRoot().putAllMap(fields_);
        int count_ = ass_.getVariablesRootBefore().size();
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().addAllElts(variables_.mid(0, count_ - 1));
    }
    public void abruptGroup(Analyzable _an, AnalyzingEl _anEl) {
    }

    @Override
    public final Block getFirstChild() {
        return firstChild;
    }

    public final void removeLocalVars(PageEl _ip) {
        for (Block s: Classes.getDirectChildren(this)) {
            if (s instanceof InitVariable) {
                String var_ = ((InitVariable)s).getVariableName();
                _ip.removeLocalVar(var_);
            }
        }
    }

    @Override
    public void reach(Analyzable _an, AnalyzingEl _anEl) {
        Block prev_ = getPreviousSibling();
        BracedBlock br_ = getParent();
        if (prev_ == null) {
            if (_anEl.isReachable(br_)) {
                _anEl.reach(this);
            } else {
                _anEl.unreach(this);
            }
        } else {
            super.reach(_an, _anEl);
        }
    }

    public boolean accessibleCondition() {
        return true;
    }

    @Override
    public void removeVarAndLoop(PageEl _ip) {
        _ip.removeLastBlock();
    }
}
