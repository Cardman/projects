package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.SimpleAssignment;
import code.expressionlanguage.stacks.SwitchBlockStack;
import code.sml.Element;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringMap;

public final class DefaultCondition extends SwitchPartBlock implements IncrNextGroup {

    public DefaultCondition(Element _el, ContextEl _importingPage,
            int _indexChild, BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    public DefaultCondition(ContextEl _importingPage,
            int _indexChild, BracedBlock _m, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
    }

    @Override
    public void setAssignmentBeforeNextSibling(Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        Block nextSibling_ = getNextSibling();
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        assBl_.getFieldsRootBefore().putAllMap(buildFieldsSwitchPart(_an, _anEl));
        assBl_.getVariablesRootBefore().addAllElts(buildVariablesSwitchPart(_an, _anEl));
        assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(nextSibling_, assBl_);
    }
    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        BracedBlock b_ = getParent();
        if (!(b_ instanceof SwitchBlock)) {
            AnalyzedPageEl page_ = _cont.getAnalyzing();
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _cont.getClasses().addError(un_);
        }
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
        return true;
    }

    @Override
    public String getTagName() {
        return TAG_DEFAULT;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        SwitchBlockStack sw_ = (SwitchBlockStack) ip_.getLastStack();
        sw_.setCurentVisitedBlock(this);
        if (sw_.isEntered()) {
            if (!hasChildNodes()) {
                if (sw_.getLastVisitedBlock() == this) {
                    sw_.setFinished(true);
                    rw_.setBlock(sw_.getBlock());
                    return;
                }
                rw_.setBlock(getNextSibling());
                return;
            }
            rw_.setBlock(getFirstChild());
            return;
        }
        ip_.setGlobalOffset(getOffset().getOffsetTrim());
        ip_.setOffset(0);
        if (hasChildNodes()) {
            sw_.setEntered(true);
        } else {
            if (sw_.getLastVisitedBlock() != this) {
                sw_.setEntered(true);
                rw_.setBlock(getNextSibling());
                return;
            }
            sw_.setFinished(true);
            rw_.setBlock(sw_.getBlock());
            return;
        }
        rw_.setBlock(getFirstChild());
        return;
    }

    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        SwitchBlockStack if_ = (SwitchBlockStack) ip_.getLastStack();
        if (if_.getLastVisitedBlock() == this) {
            if_.setFinished(true);
            rw_.setBlock(if_.getBlock());
        } else {
            rw_.setBlock(getNextSibling());
        }
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return null;
    }
}
