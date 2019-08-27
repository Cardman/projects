package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.stacks.TryBlockStack;
import code.util.CustList;
import code.util.IdMap;

public final class TryEval extends BracedStack implements Eval {


    private String label;
    private int labelOffset;

    public TryEval(OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String getRealLabel() {
        return label;
    }

    @Override
    public int getRealLabelOffset() {
        return getLabelOffset();
    }

    public int getLabelOffset() {
        return labelOffset;
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        buildEmptyEl(_cont);
    }

    @Override
    public void setAssignmentBeforeNextSibling(Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
        Block nextSibling_ = getNextSibling();
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        CustList<AbstractCatchEval> catchs_ = new CustList<AbstractCatchEval>();
        assBl_.getFieldsRootBefore().putAllMap(buildAssFieldsBefNextCatchFinally(this,_an, _anEl,catchs_));
        assBl_.getVariablesRootBefore().addAllElts(buildAssVarsBefNextCatchFinally(this,_an, _anEl,catchs_));
        assBl_.getMutableLoopRootBefore().addAllElts(buildAssMutableLoopBefNextCatchFinally(this,_an, _anEl,catchs_));
        id_.put(nextSibling_, assBl_);
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        super.setAssignmentAfter(_an, _anEl);
        Block nBlock_ = getNextSibling();
        if (!(nBlock_ instanceof AbstractCatchEval)) {
            if (!(nBlock_ instanceof FinallyEval)) {
                UnexpectedTagName un_ = new UnexpectedTagName();
                un_.setFileName(getFile().getFileName());
                un_.setIndexFile(getOffset().getOffsetTrim());
                _an.getClasses().addError(un_);
            }
        }
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        refLabel(_parts,label,labelOffset);
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        Block n_ = getNextSibling();
        TryBlockStack tryStack_ = new TryBlockStack();
        while (n_ instanceof AbstractCatchEval || n_ instanceof FinallyEval) {
            tryStack_.setLastBlock((BracedBlock) n_);
            n_ = n_.getNextSibling();
        }
        tryStack_.setCurrentBlock(this);
        tryStack_.setBlock(this);
        ip_.addBlock(tryStack_);
        ip_.getReadWrite().setBlock(getFirstChild());
    }

    @Override
    public void exitStack(ContextEl _context) {
        AbstractPageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        rw_.setBlock(getNextSibling());
    }

    @Override
    public void processToFinally(AbstractPageEl _ip, TryBlockStack _stack) {
        removeLocalVars(_ip);
        if (_stack.getLastBlock() instanceof FinallyEval) {
            _ip.clearCurrentEls();
            _ip.getReadWrite().setBlock(_stack.getLastBlock());
            _ip.setFinallyToProcess(true);
            return;
        }
        _ip.removeLastBlock();
    }
}
