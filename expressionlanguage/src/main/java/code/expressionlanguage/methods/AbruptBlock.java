package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
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
    public void abrupt(Analyzable _an, AnalyzingEl _anEl) {
        _anEl.completeAbrupt(this);
        _anEl.completeAbruptGroup(this);
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        CustList<StringMap<SimpleAssignment>> list_ = new CustList<StringMap<SimpleAssignment>>();
        for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
            StringMap<SimpleAssignment> sm_ = new StringMap<SimpleAssignment>();
            for (EntryCust<String, AssignmentBefore> e: s.entryList()) {
                sm_.put(e.getKey(), e.getValue().assignAfterClassic());
            }
            list_.add(sm_);
        }
        vars_.getVariablesRoot().addAllElts(list_);
        for (EntryCust<ClassField,AssignmentBefore> e: vars_.getFieldsRootBefore().entryList()) {
            ClassField key_ = e.getKey();
            vars_.getFieldsRoot().put(key_, e.getValue().assignAfterClassic());
        }
    }
}
