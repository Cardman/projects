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
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.AssignmentBefore;
import code.expressionlanguage.opers.util.SimpleAssignment;
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


    Block(BracedBlock _m, OffsetsBlock _offset) {
        parent = _m;
        offset = _offset;
    }
    public final OffsetsBlock getOffset() {
        return offset;
    }
    protected void buildEmptyEl(Analyzable _cont) {
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
        for (StringMap<AssignmentBefore> s: ass_.getMutableLoopRootBefore()) {
            StringMap<SimpleAssignment> vars_ = new StringMap<SimpleAssignment>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                vars_.put(e.getKey(), e.getValue().assignAfterClassic());
            }
            ass_.getMutableLoopRoot().add(vars_);
        }
    }
    public void defaultAssignmentBefore(Analyzable _an, OperationNode _root) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        StringMap<AssignmentBefore> fields_;
        CustList<StringMap<AssignmentBefore>> variables_;
        CustList<StringMap<AssignmentBefore>> mutable_;
        fields_ = new StringMap<AssignmentBefore>();
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        mutable_ = new CustList<StringMap<AssignmentBefore>>();
        for (EntryCust<String,AssignmentBefore> e: vars_.getFieldsRootBefore().entryList()) {
            fields_.put(e.getKey(), e.getValue().copy());
        }
        vars_.getFieldsBefore().put(_root, fields_);
        for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                sm_.put(e.getKey(), e.getValue().copy());
            }
            variables_.add(sm_);
        }
        vars_.getVariablesBefore().put(_root, variables_);
        for (StringMap<AssignmentBefore> s: vars_.getMutableLoopRootBefore()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                sm_.put(e.getKey(), e.getValue().copy());
            }
            mutable_.add(sm_);
        }
        vars_.getMutableLoopBefore().put(_root, mutable_);
    }
    public void defaultAssignmentAfter(Analyzable _an, OperationNode _root) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        StringMap<Assignment> res_ = vars_.getLastFieldsOrEmpty();
        for (EntryCust<String,Assignment> e: res_.entryList()) {
            vars_.getFieldsRoot().put(e.getKey(), e.getValue().assignClassic());
        }
        if (_root instanceof CurrentInvokingConstructor) {
            for (EntryCust<String,SimpleAssignment> e: vars_.getFieldsRoot().entryList()) {
                SimpleAssignment a_ = e.getValue();
                a_.setAssignedAfter(true);
                a_.setUnassignedAfter(false);
            }
        }
        CustList<StringMap<Assignment>> varsRes_;
        varsRes_ = vars_.getLastVariablesOrEmpty();
        for (StringMap<Assignment> s: varsRes_) {
            StringMap<SimpleAssignment> sm_ = new StringMap<SimpleAssignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                sm_.put(e.getKey(), e.getValue().assignClassic());
            }
            vars_.getVariablesRoot().add(sm_);
        }
        CustList<StringMap<Assignment>> mutableRes_;
        mutableRes_ = vars_.getLastMutableLoopOrEmpty();
        for (StringMap<Assignment> s: mutableRes_) {
            StringMap<SimpleAssignment> sm_ = new StringMap<SimpleAssignment>();
            for (EntryCust<String, Assignment> e: s.entryList()) {
                sm_.put(e.getKey(), e.getValue().assignClassic());
            }
            vars_.getMutableLoopRoot().add(sm_);
        }
    }
    public void setAssignmentBeforeNextSibling(Analyzable _an, AnalyzingEl _anEl) {
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables prevAss_ = id_.getVal(this);
        Block nextSibling_ = getNextSibling();
        AssignedVariables assBl_ = nextSibling_.buildNewAssignedVariable();
        for (EntryCust<String, SimpleAssignment> e: prevAss_.getFieldsRoot().entryList()) {
            AssignmentBefore asBef_ = e.getValue().assignBefore();
            assBl_.getFieldsRootBefore().put(e.getKey(), asBef_);
        }
        for (StringMap<SimpleAssignment> s: prevAss_.getVariablesRoot()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, SimpleAssignment> e: s.entryList()) {
                AssignmentBefore asBef_ = e.getValue().assignBefore();
                sm_.put(e.getKey(), asBef_);
            }
            assBl_.getVariablesRootBefore().add(sm_);
        }
        for (StringMap<SimpleAssignment> s: prevAss_.getMutableLoopRoot()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String, SimpleAssignment> e: s.entryList()) {
                AssignmentBefore asBef_ = e.getValue().assignBefore();
                sm_.put(e.getKey(), asBef_);
            }
            assBl_.getMutableLoopRootBefore().add(sm_);
        }
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
                    if (!a.contains(v)) {
                        continue;
                    }
                    if (!a.getVal(v).isAssignedBefore()) {
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
        StringMap<AssignmentBefore> fields_;
        fields_ = new StringMap<AssignmentBefore>();
        for (EntryCust<String,AssignmentBefore> e: vars_.getFieldsRootBefore().entryList()) {
            AssignmentBefore ass_ = e.getValue();
            AssignmentBefore h_ = new AssignmentBefore();
            if (ass_.isAssignedBefore()) {
                h_.setAssignedBefore(true);
            } else {
                h_.setUnassignedBefore(true);
            }
            fields_.put(e.getKey(), h_);
        }
        return fields_;
    }
    protected CustList<StringMap<AssignmentBefore>> makeHypothesisVars(Analyzable _an) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        CustList<StringMap<AssignmentBefore>> variables_;
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<AssignmentBefore> s: vars_.getVariablesRootBefore()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                AssignmentBefore ass_ = e.getValue();
                AssignmentBefore h_ = new AssignmentBefore();
                if (ass_.isAssignedBefore()) {
                    h_.setAssignedBefore(true);
                } else {
                    h_.setUnassignedBefore(true);
                }
                sm_.put(e.getKey(), h_);
            }
            variables_.add(sm_);
        }
        return variables_;
    }
    protected CustList<StringMap<AssignmentBefore>> makeHypothesisMutableLoop(Analyzable _an) {
        AssignedVariables vars_ = _an.getAssignedVariables().getFinalVariables().getVal(this);
        CustList<StringMap<AssignmentBefore>> variables_;
        variables_ = new CustList<StringMap<AssignmentBefore>>();
        for (StringMap<AssignmentBefore> s: vars_.getMutableLoopRootBefore()) {
            StringMap<AssignmentBefore> sm_ = new StringMap<AssignmentBefore>();
            for (EntryCust<String,AssignmentBefore> e: s.entryList()) {
                AssignmentBefore ass_ = e.getValue();
                AssignmentBefore h_ = new AssignmentBefore();
                if (ass_.isAssignedBefore()) {
                    h_.setAssignedBefore(true);
                } else {
                    h_.setUnassignedBefore(true);
                }
                sm_.put(e.getKey(), h_);
            }
            variables_.add(sm_);
        }
        return variables_;
    }

    protected static void tryBuildExpressionLanguage(Block _block, ContextEl _cont) {
        if (_block instanceof WithEl) {
            ((WithEl)_block).buildExpressionLanguage(_cont);
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
            Block n_ = null;
            n_ = getNextSibling();
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
    public final RootBlock getRooted() {
        Block b_ = this;
        while (b_ != null) {
            if (b_ instanceof RootBlock) {
                return (RootBlock)b_;
            }
            b_ = b_.getParent();
        }
        return null;
    }
    public final AccessingImportingBlock getImporting() {
        Block b_ = this;
        while (b_ != null) {
            if (b_ instanceof AccessingImportingBlock) {
                return (AccessingImportingBlock)b_;
            }
            b_ = b_.getParent();
        }
        return null;
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
