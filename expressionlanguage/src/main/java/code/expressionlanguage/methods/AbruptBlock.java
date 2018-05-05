package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMetaInfo;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.variables.LocalVariable;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdList;
import code.util.IdMap;
import code.util.StringMap;

public abstract class AbruptBlock extends Leaf {

    public AbruptBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    AbruptBlock(ContextEl _importingPage, int _indexChild, BracedBlock _m,
            OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
    }

    @Override
    public final void abrupt(Analyzable _an, AnalyzingEl _anEl) {
        _anEl.completeAbrupt(this);
        _anEl.completeAbruptGroup(this);
        IdMap<BreakBlock, BreakableBlock> breakables_ = _anEl.getBreakables();
        IdMap<ContinueBlock, Loop> continuables_ = _anEl.getContinuables();
        IdMap<BreakBlock, IdMap<BreakableBlock, IdList<BracedBlock>>> breakablesAncestors_ = _anEl.getBreakablesAncestors();
        IdMap<ContinueBlock, IdMap<Loop, IdList<BracedBlock>>> continuablesAncestors_ = _anEl.getContinuablesAncestors();
        if (this instanceof BreakBlock) {
            BracedBlock par_ = getParent();
            IdList<BracedBlock> pars_ = new IdList<BracedBlock>();
            BracedBlock a_ = (BracedBlock) _anEl.getParentsBreakables().last();
            while (par_ != a_) {
                pars_.add(par_);
                par_ = par_.getParent();
            }
            IdMap<BreakableBlock, IdList<BracedBlock>> id_;
            id_ = new IdMap<BreakableBlock, IdList<BracedBlock>>();
            id_.put((BreakableBlock) a_, pars_);
            breakablesAncestors_.put((BreakBlock)this, id_);
            breakables_.put((BreakBlock)this, (BreakableBlock) a_);
        }
        if (this instanceof ContinueBlock) {
            BracedBlock par_ = getParent();
            IdList<BracedBlock> pars_ = new IdList<BracedBlock>();
            BracedBlock a_ = (BracedBlock) _anEl.getParentsContinuables().last();
            while (par_ != a_) {
                pars_.add(par_);
                par_ = par_.getParent();
            }
            IdMap<Loop, IdList<BracedBlock>> id_;
            id_ = new IdMap<Loop, IdList<BracedBlock>>();
            id_.put((Loop) a_, pars_);
            continuablesAncestors_.put((ContinueBlock)this, id_);
            continuables_.put((ContinueBlock)this, (Loop) a_);
        }
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        String boolType_ = _an.getStandards().getAliasBoolean();
        CustList<StringMap<Assignment>> list_ = new CustList<StringMap<Assignment>>();
        int index_ = 0;
        for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
            StringMap<Assignment> sm_ = new StringMap<Assignment>();
            for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                String key_ = e.getKey();
                LocalVariable lc_ = _an.getLocalVariables().get(index_).getVal(key_);
                String type_ = lc_.getClassName();
                boolean isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(boolType_, type_, _an);
                sm_.put(e.getKey(), e.getValue().assignAfter(isBool_));
            }
            list_.add(sm_);
            index_++;
        }
        vars_.getVariablesRoot().clear();
        vars_.getVariablesRoot().addAllElts(list_);
        for (EntryCust<ClassField,AssignmentBefore> e: vars_.getFieldsRootBefore().entryList()) {
            ClassField key_ = e.getKey();
            String classNameDecl_ = key_.getClassName();
            ClassMetaInfo custClass_;
            custClass_ = _an.getClassMetaInfo(classNameDecl_);
            String type_ = custClass_.getFields().getVal(key_.getFieldName()).getType();
            boolean isBool_ = PrimitiveTypeUtil.canBeUseAsArgument(boolType_, type_, _an);
            vars_.getFieldsRoot().put(key_, e.getValue().assignAfter(isBool_));
        }
//        for (StringMap<Assignment> e: vars_.getVariablesRoot().getReverse()) {
//            for (EntryCust<String, Assignment> f: e.entryList()) {
//                if (f.getValue() instanceof BooleanAssignment) {
//                    ((BooleanAssignment)f.getValue()).setAssignedAfterWhenFalse(true);
//                    ((BooleanAssignment)f.getValue()).setAssignedAfterWhenTrue(true);
//                    ((BooleanAssignment)f.getValue()).setUnassignedAfterWhenFalse(true);
//                    ((BooleanAssignment)f.getValue()).setUnassignedAfterWhenTrue(true);
//                } else {
//                    ((SimpleAssignment)f.getValue()).setAssignedAfter(true);
//                    ((SimpleAssignment)f.getValue()).setUnassignedAfter(true);
//                }
//            }
//        }
    }
}
