package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.WithElPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.BadLabelName;
import code.expressionlanguage.errors.custom.DuplicateLabel;
import code.expressionlanguage.errors.custom.UnassignedInfered;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.methods.util.ParentStackBlock;
import code.expressionlanguage.opers.CurrentInvokingConstructor;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class Block {
    public static final String OR_EQ = "|=";
    public static final String AND_EQ = "&=";
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
    protected void buildEmptyEl(Analyzable _cont) {
        AssignedVariablesBlock glAss_ = _cont.getAssignedVariables();
        AssignedVariables ass_ = glAss_.getFinalVariables().getVal(this);
        ass_.getFieldsRoot().putAllMap(AssignmentsUtil.assignAfterClassic(ass_.getFieldsRootBefore()));
        ass_.getVariablesRoot().addAllElts(AssignmentsUtil.assignAfterClassic(ass_.getVariablesRootBefore()));
        ass_.getMutableLoopRoot().addAllElts(AssignmentsUtil.assignAfterClassic(ass_.getMutableLoopRootBefore()));
    }
    public void defaultAssignmentBefore(Analyzable _an, OperationNode _root) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        vars_.getFieldsBefore().put(_root, AssignmentsUtil.copyBefore(vars_.getFieldsRootBefore()));
        vars_.getVariablesBefore().put(_root, AssignmentsUtil.copyBefore(vars_.getVariablesRootBefore()));
        vars_.getMutableLoopBefore().put(_root, AssignmentsUtil.copyBefore(vars_.getMutableLoopRootBefore()));
    }
    public void defaultAssignmentAfter(Analyzable _an, OperationNode _root) {
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
    public void setAssignmentBeforeNextSibling(Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables prevAss_ = id_.getVal(this);
        Block nextSibling_ = getNextSibling();
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignSimpleBefore(prevAss_.getFieldsRoot()));
        assBl_.getVariablesRootBefore().addAllElts(AssignmentsUtil.assignSimpleBefore(prevAss_.getVariablesRoot()));
        assBl_.getMutableLoopRootBefore().addAllElts(AssignmentsUtil.assignSimpleBefore(prevAss_.getMutableLoopRoot()));
        id_.put(nextSibling_, assBl_);
    }
    public void setAssignmentBefore(Analyzable _an, AnalyzingEl _anEl) {
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
                BadLabelName bad_ = new BadLabelName();
                bad_.setName(label_);
                bad_.setFileName(getFile().getFileName());
                bad_.setIndexFile(0);
                _an.getClasses().addError(bad_);
            } else if (!label_.isEmpty()){
                if (_anEl.getLabels().containsStr(label_)) {
                    DuplicateLabel dup_ = new DuplicateLabel();
                    dup_.setId(label_);
                    dup_.setFileName(getFile().getFileName());
                    dup_.setIndexFile(0);
                    _an.getClasses().addError(dup_);
                } else {
                    _anEl.getLabels().add(label_);
                }
            }
        }
        BracedBlock br_ = getParent();
        Block prev_ = getPreviousSibling();
        if (prev_ == null) {
            br_.setAssignmentBeforeChild(_an, _anEl);
        } else {
            prev_.setAssignmentBeforeNextSibling(_an, _anEl);
        }
        if (this instanceof BracedBlock) {
            AssignedVariables v_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
            CustList<StringMap<AssignmentBefore>> as_ = v_.getVariablesRootBefore();
            for (String v: _an.getInfersLocalVars()) {
                for (StringMap<AssignmentBefore> a: as_) {
                    for (EntryCust<String,AssignmentBefore> f: a.entryList()) {
                        if (!StringList.quickEq(f.getKey(),v)) {
                            continue;
                        }
                        if (!f.getValue().isAssignedBefore()) {
                            //error
                            UnassignedInfered un_ = new UnassignedInfered(v);
                            un_.setFileName(getFile().getFileName());
                            un_.setIndexFile(getOffset().getOffsetTrim());
                            _an.getClasses().addError(un_);
                        }
                    }
                }
            }
        }
    }
    protected AssignedVariables buildNewAssignedVariable() {
        return new AssignedVariables();
    }
    public void reach(Analyzable _an, AnalyzingEl _anEl) {
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

    public abstract void abrupt(Analyzable _an, AnalyzingEl _anEl);
    public abstract void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl);
    protected StringMap<AssignmentBefore> makeHypothesisFields(Analyzable _an) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        return AssignmentsUtil.getHypoAssignmentBefore(vars_.getFieldsRootBefore());
    }
    protected CustList<StringMap<AssignmentBefore>> makeHypothesisVars(Analyzable _an) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        CustList<StringMap<AssignmentBefore>> variables_;
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
            variables_.add(AssignmentsUtil.getHypoAssignmentBefore(s));
        }
        return variables_;
    }
    protected CustList<StringMap<AssignmentBefore>> makeHypothesisMutableLoop(Analyzable _an) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        CustList<StringMap<AssignmentBefore>> variables_;
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<AssignmentBefore> s: vars_.getMutableLoopRootBefore()) {
            variables_.add(AssignmentsUtil.getHypoAssignmentBefore(s));
        }
        return variables_;
    }

    protected static void tryBuildExpressionLanguage(Block _block, ContextEl _cont) {
        if (_block instanceof BuildableElMethod) {
            ((BuildableElMethod)_block).buildExpressionLanguage(_cont);
            return;
        }
        UnexpectedTagName un_ = new UnexpectedTagName();
        un_.setFileName(_block.getFile().getFileName());
        un_.setIndexFile(_block.getOffset().getOffsetTrim());
        _cont.getClasses().addError(un_);
    }

    public final void processBlock(ContextEl _conf) {
        AbstractPageEl ip_ = _conf.getLastPage();
        ParentStackBlock parElt_ = ((WithElPageEl)ip_).getNextBlock(this);
        if (parElt_ == null) {
            ((WithElPageEl)ip_).postBlock(_conf);
            return;
        }
        BracedBlock par_ = parElt_.getElement();
        if (par_ == null) {
            ReadWrite rw_ = ip_.getReadWrite();
            Block n_ = getNextSibling();
            rw_.setBlock(n_);
            return;
        }
        par_.removeLocalVars(ip_);
        ((StackableBlockGroup)par_).exitStack(_conf);
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
