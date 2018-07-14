package code.expressionlanguage.methods;

import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.sml.Element;
import code.util.EntryCust;
import code.util.StringMap;

public final class NullCatchEval extends AbstractCatchEval {

    public NullCatchEval(Element _el, ContextEl _importingPage,
            int _indexChild, BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    public NullCatchEval(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
    }

    @Override
    public void reach(Analyzable _an, AnalyzingEl _anEl) {
        Block p_ = getPreviousSibling();
        boolean reachCatch_ = true;
        while (!(p_ instanceof TryEval)) {
            if (p_ instanceof NullCatchEval) {
                reachCatch_ = false;
                break;
            }
            p_ = p_.getPreviousSibling();
        }
        if (reachCatch_) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        if (getFirstChild() == null) {
            AssignedVariablesBlock glAss_ = _cont.getAssignedVariables();
            AssignedVariables ass_ = glAss_.getFinalVariables().getVal(this);
            for (EntryCust<String,AssignmentBefore> e: ass_.getFieldsRootBefore().entryList()) {
                String key_ = e.getKey();
                ass_.getFieldsRoot().put(key_, e.getValue().assignAfterClassic());
            }
            for (StringMap<AssignmentBefore> s: ass_.getVariablesRootBefore()) {
                StringMap<SimpleAssignment> vars_ = new StringMap<SimpleAssignment>();
                for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                    vars_.put(e.getKey(), e.getValue().assignAfterClassic());
                }
                ass_.getVariablesRoot().add(vars_);
            }
        }
    }

    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        rw_.setBlock(this);
    }

}
