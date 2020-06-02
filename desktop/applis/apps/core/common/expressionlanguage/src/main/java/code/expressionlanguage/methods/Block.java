package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.CurrentInvokingConstructor;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.*;
import code.util.*;

public abstract class Block implements AnalyzedBlock {
    public static final String OR_EQ = "|=";
    public static final String AND_EQ = "&=";
    public static final String OR_LOG_EQ = "||=";
    public static final String AND_LOG_EQ = "&&=";
    public static final String NULL_EQ = "??=";
    public static final String XOR_EQ = "^=";
    public static final String PLUS_EQ = "+=";
    public static final String INCR = "++";

    protected static final String VARARG = "...";

    protected static final String DOT = ".";

    protected static final String PAR_LEFT = "(";
    protected static final String PAR_RIGHT = ")";
    protected static final String EMPTY_STRING = "";

    private BracedBlock parent;

    private Block nextSibling;

    private Block previousSibling;

    private OffsetsBlock offset;


    Block(OffsetsBlock _offset) {
        offset = _offset;
    }
    protected final void setParent(BracedBlock _b) {
        parent = _b;
    }
    public final OffsetsBlock getOffset() {
        return offset;
    }
    protected void buildEmptyEl(ContextEl _cont) {
        AssignedVariablesBlock glAss_ = _cont.getAssignedVariables();
        AssignedVariables ass_ = glAss_.getFinalVariables().getVal(this);
        ass_.getFieldsRoot().putAllMap(AssignmentsUtil.assignAfterClassic(ass_.getFieldsRootBefore()));
        ass_.getVariablesRoot().addAllElts(AssignmentsUtil.assignAfterClassic(ass_.getVariablesRootBefore()));
        ass_.getMutableLoopRoot().addAllElts(AssignmentsUtil.assignAfterClassic(ass_.getMutableLoopRootBefore()));
    }
    public void defaultAssignmentBefore(ContextEl _an, OperationNode _root) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        vars_.getFieldsBefore().put(_root, AssignmentsUtil.copyBefore(vars_.getFieldsRootBefore()));
        vars_.getVariablesBefore().put(_root, AssignmentsUtil.copyBefore(vars_.getVariablesRootBefore()));
        vars_.getMutableLoopBefore().put(_root, AssignmentsUtil.copyBefore(vars_.getMutableLoopRootBefore()));
    }
    public void defaultAssignmentAfter(ContextEl _an, OperationNode _root) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        StringMap<Assignment> res_ = vars_.getLastFieldsOrEmpty();
        vars_.getFieldsRoot().putAllMap(AssignmentsUtil.assignClassic(res_));
        if (_root instanceof CurrentInvokingConstructor) {
            for (EntryCust<String,SimpleAssignment> e: vars_.getFieldsRoot().entryList()) {
                SimpleAssignment a_ = e.getValue();
                a_.setAssignedAfter(true);
                a_.setUnassignedAfter(false);
            }
        }
        CustList<StringMap<Assignment>> varsRes_;
        varsRes_ = vars_.getLastVariablesOrEmpty();
        vars_.getVariablesRoot().addAllElts(AssignmentsUtil.assignClassic(varsRes_));
        CustList<StringMap<Assignment>> mutableRes_;
        mutableRes_ = vars_.getLastMutableLoopOrEmpty();
        vars_.getMutableLoopRoot().addAllElts(AssignmentsUtil.assignClassic(mutableRes_));
    }
    public void setAssignmentBeforeNextSibling(ContextEl _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables prevAss_ = id_.getVal(this);
        Block nextSibling_ = getNextSibling();
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(prevAss_.getFieldsRoot()));
        assBl_.getVariablesRootBefore().addAllElts(AssignmentsUtil.assignSimpleBefore(prevAss_.getVariablesRoot()));
        assBl_.getMutableLoopRootBefore().addAllElts(AssignmentsUtil.assignSimpleBefore(prevAss_.getMutableLoopRoot()));
        id_.put(nextSibling_, assBl_);
    }
    public void setAssignmentBefore(ContextEl _an, AnalyzingEl _anEl) {
        BracedBlock br_ = getParent();
        Block prev_ = getPreviousSibling();
        if (prev_ == null) {
            br_.setAssignmentBeforeChild(_an, _anEl);
        } else {
            prev_.setAssignmentBeforeNextSibling(_an, _anEl);
        }
    }

    public void checkLabelReference(ContextEl _an, AnalyzingEl _anEl) {
        if (this instanceof BreakableBlock) {
            String label_ = ((BreakableBlock)this).getRealLabel();
            boolean wc_ = true;
            for (char c: label_.toCharArray()) {
                if (StringList.isDollarWordChar(c)) {
                    continue;
                }
                wc_ = false;
                break;
            }
            if (!wc_) {
                FoundErrorInterpret bad_ = new FoundErrorInterpret();
                bad_.setFileName(getFile().getFileName());
                bad_.setIndexFile(0);
                //label_ len
                bad_.buildError(_an.getAnalysisMessages().getBadLabel());
                _an.addError(bad_);
            } else if (!label_.isEmpty()){
                if (StringList.contains(_anEl.getLabels(), label_)) {
                    FoundErrorInterpret dup_ = new FoundErrorInterpret();
                    dup_.setFileName(getFile().getFileName());
                    dup_.setIndexFile(0);
                    //label_ len
                    dup_.buildError(_an.getAnalysisMessages().getDuplicatedLabel());
                    _an.addError(dup_);
                } else {
                    _anEl.getLabels().add(label_);
                }
            }
        }
    }

    protected AssignedVariables buildNewAssignedVariable() {
        return new AssignedVariables();
    }
    public void reach(ContextEl _an, AnalyzingEl _anEl) {
        Block prev_ = getPreviousSibling();
        if (_anEl.canCompleteNormallyGroup(prev_)) {
            _anEl.reach(this);
        } else {
            _anEl.unreach(this);
        }
    }

    public boolean accessibleCondition() {
        return true;
    }

    public boolean accessibleForNext() {
        return true;
    }

    public abstract void abrupt(ContextEl _an, AnalyzingEl _anEl);
    public abstract void setAssignmentAfter(ContextEl _an, AnalyzingEl _anEl);
    public abstract void checkTree(ContextEl _an, AnalyzingEl _anEl);
    protected StringMap<AssignmentBefore> makeHypothesisFields(ContextEl _an) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        return AssignmentsUtil.getHypoAssignmentBefore(vars_.getFieldsRootBefore());
    }
    protected CustList<StringMap<AssignmentBefore>> makeHypothesisVars(ContextEl _an) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        CustList<StringMap<AssignmentBefore>> variables_;
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
            variables_.add(AssignmentsUtil.getHypoAssignmentBefore(s));
        }
        return variables_;
    }
    protected CustList<StringMap<AssignmentBefore>> makeHypothesisMutableLoop(ContextEl _an) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        CustList<StringMap<AssignmentBefore>> variables_;
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<AssignmentBefore> s: vars_.getMutableLoopRootBefore()) {
            variables_.add(AssignmentsUtil.getHypoAssignmentBefore(s));
        }
        return variables_;
    }

    protected static boolean tryBuildExpressionLanguage(Block _block, ContextEl _cont) {
        if (_block instanceof BuildableElMethod) {
            ((BuildableElMethod)_block).buildExpressionLanguage(_cont);
            return true;
        }
        return processOther(_block, _cont);
    }

    protected static boolean tryBuildExpressionLanguageReadOnly(Block _block, ContextEl _cont) {
        if (_block instanceof BuildableElMethod) {
            ((BuildableElMethod)_block).buildExpressionLanguageReadOnly(_cont);
            return true;
        }
        return processOther(_block, _cont);
    }

    private static boolean processOther(Block _block, ContextEl _cont) {
        if (_block instanceof UnclassedBracedBlock) {
            return true;
        }
        FoundErrorInterpret un_ = new FoundErrorInterpret();
        un_.setFileName(_block.getFile().getFileName());
        un_.setIndexFile(_block.getOffset().getOffsetTrim());
        //defined len first key words
        un_.buildError(_cont.getAnalysisMessages().getUnexpectedBlockExp());
        _cont.addError(un_);
        return false;
    }

    public abstract void processReport(ContextEl _cont, CustList<PartOffset> _parts);
    public final void processBlockAndRemove(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ip_.removeLastBlock();
        processBlock(_conf);
    }
    public final void processBlock(ContextEl _conf) {
        Block n_ = getNextSibling();
        AbstractPageEl ip_ = _conf.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        if (n_ != null) {
            rw_.setBlock(n_);
            return;
        }
        BracedBlock par_ = getParent();
        if (par_ != ip_.getBlockRoot()) {
            if (par_ instanceof Loop) {
                par_.removeLocalVars(ip_);
            } else {
                par_.removeAllVars(ip_);
            }
            rw_.setBlock(par_);
            par_.exitStack(_conf);
            return;
        }
        ip_.setNullReadWrite();
    }

    public final FunctionBlock getFunction() {
        Block b_ = this;
        while (b_ != null) {
            if (b_ instanceof FunctionBlock) {
                return (FunctionBlock)b_;
            }
            b_ = b_.getParent();
        }
        return null;
    }
    public final FileBlock getFile() {
        Block b_ = this;
        while (!(b_ instanceof FileBlock)) {
            b_ = b_.getParent();
        }
        return (FileBlock) b_;
    }

    public final Block getPreviousSibling() {
        return previousSibling;
    }
    public abstract Block getFirstChild();

    public final Block getNextSibling() {
        return nextSibling;
    }
    final void setNextSibling(Block _nextSibling) {
        nextSibling = _nextSibling;
    }
    final void setPreviousSibling(Block _previousSibling) {
        previousSibling = _previousSibling;
    }

    public final BracedBlock getParent() {
        return parent;
    }
}
