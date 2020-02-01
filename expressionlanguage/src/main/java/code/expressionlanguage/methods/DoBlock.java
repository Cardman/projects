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
import code.expressionlanguage.stacks.LoopBlockStack;
import code.util.CustList;
import code.util.IdMap;

public final class DoBlock extends BracedStack implements Loop {

    private String label;
    private int labelOffset;

    public DoBlock(OffsetStringInfo _label, OffsetsBlock _offset) {
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
    public void checkTree(Analyzable _an, AnalyzingEl _anEl) {
        Block nextSibling_ = getNextSibling();
        if (nextSibling_ == null) {
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            _an.getClasses().addError(un_);
            return;
        }
        if (!(nextSibling_ instanceof DoWhileCondition)) {
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(nextSibling_.getFile().getFileName());
            un_.setIndexFile(nextSibling_.getOffset().getOffsetTrim());
            _an.getClasses().addError(un_);
        }
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        refLabel(_parts,label,labelOffset);
    }

    @Override
    public void setAssignmentBeforeNextSibling(Analyzable _an, AnalyzingEl _anEl) {
        Block nextSibling_ = getNextSibling();
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        IdMap<Block, AssignedVariables> id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
        assBl_.getFieldsRootBefore().putAllMap(buildAssListFieldBeforeIncrPart(_an, _anEl));
        assBl_.getVariablesRootBefore().addAllElts(buildAssListLocVarBeforeIncrPart(_an, _anEl));
        assBl_.getMutableLoopRootBefore().addAllElts(buildAssListMutableLoopBeforeIncrPart(_an, _anEl));
        id_.put(nextSibling_, assBl_);
    }
    

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        buildEmptyEl(_cont);
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        LoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            ip_.processVisitedLoop(c_,this,getNextSibling(),_cont);
            return;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setBlock(this);
        ip_.addBlock(l_);
        rw_.setBlock(getFirstChild());
    }

    @Override
    public void exitStack(ContextEl _context) {
        processLastElementLoop(_context);
    }

    @Override
    public void processLastElementLoop(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        rw_.setBlock(getNextSibling());
    }

}
