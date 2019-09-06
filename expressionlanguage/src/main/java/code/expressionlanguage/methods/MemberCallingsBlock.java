package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.errors.custom.DeadCodeMethod;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;

public abstract class MemberCallingsBlock extends BracedBlock implements FunctionBlock, AnnotableBlock {
    private StringList annotations = new StringList();
    private CustList<CustList<ExecOperationNode>> annotationsOps = new CustList<CustList<ExecOperationNode>>();
    private Ints annotationsIndexes = new Ints();

    MemberCallingsBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    public final void buildFctInstructions(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(getOffset().getOffsetTrim());
        page_.setOffset(0);
        Block firstChild_ = getFirstChild();
        StringMap<StringList> vars_ = _cont.getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        AnalyzingEl anEl_ = new AnalyzingEl(mapping_);
        _cont.getAnalyzing().setAnalysisAss(anEl_);
        _cont.getAnalyzing().setCurrentFct(this);
        anEl_.setRoot(this);
        Block en_ = this;
        CustList<BracedBlock> parents_ = anEl_.getParents();
        CustList<BreakableBlock> parentsBreakables_ = anEl_.getParentsBreakables();
        CustList<Loop> parentsContinuable_ = anEl_.getParentsContinuables();
        CustList<Eval> parentsReturnable_ = anEl_.getParentsReturnables();
        StringList labels_ = anEl_.getLabels();
        if (firstChild_ == null) {
            if (!_cont.getOptions().isReadOnly()) {
                setAssignmentBeforeCall(_cont, anEl_);
            }
            anEl_.reach(this);
            abrupt(_cont, anEl_);
            setAssignmentAfterCall(_cont, anEl_);
            return;
        }
        while (true) {
            _cont.getAnalyzing().setCurrentBlock(en_);
            if (en_ instanceof BracedBlock && en_.getFirstChild() == null) {
                if (!(en_ instanceof SwitchBlock) && !(en_ instanceof DoWhileCondition)) {
                    OffsetsBlock off_ = en_.getOffset();
                    EmptyInstruction empty_ = new EmptyInstruction(off_);
                    ((BracedBlock)en_).appendChild(empty_);
                }
            }
            _cont.getCoverage().putBlockOperations(_cont,en_);
            if (en_ == this) {
                if (!_cont.getOptions().isReadOnly()) {
                    setAssignmentBeforeCall(_cont, anEl_);
                }
                anEl_.reach(this);
            } else {
                en_.checkLabelReference(_cont, anEl_);
                if (!_cont.getOptions().isReadOnly()) {
                    en_.setAssignmentBefore(_cont, anEl_);
                }
                en_.reach(_cont, anEl_);
            }
            if (!anEl_.isReachable(en_)) {
                //error
                DeadCodeMethod deadCode_ = new DeadCodeMethod();
                deadCode_.setFileName(getFile().getFileName());
                deadCode_.setIndexFile(en_.getOffset().getOffsetTrim());
                deadCode_.setId(getPseudoSignature(_cont));
                _cont.getClasses().addError(deadCode_);
            }
            Block n_ = en_.getFirstChild();
            if (en_ instanceof BracedBlock && n_ != null) {
                _cont.getAnalyzing().initLocalVars();
                _cont.getAnalyzing().initVars();
                _cont.getAnalyzing().initMutableLoopVars();
                _cont.getAnalyzing().initCatchVars();
                if (en_ instanceof BreakableBlock) {
                    parentsBreakables_.add((BreakableBlock) en_);
                }
                if (en_ instanceof Loop) {
                    parentsContinuable_.add((Loop) en_);
                }
                if (en_ instanceof Eval && !(en_ instanceof FinallyEval)) {
                    Block ne_ = en_;
                    boolean fin_ = false;
                    while (ne_ instanceof Eval) {
                        if (ne_ instanceof FinallyEval) {
                            fin_ = true;
                            break;
                        }
                        ne_ = ne_.getNextSibling();
                    }
                    if (fin_) {
                        parentsReturnable_.add((Eval) en_);
                    }
                }
                parents_.add((BracedBlock) en_);
            }
            if (en_ != anEl_.getRoot()) {
                tryBuildExpressionLanguage(en_, _cont);
            }
            if (n_ != null) {
                en_ = n_;
                continue;
            }
            en_.abrupt(_cont, anEl_);
            if (en_ instanceof BracedBlock) {
                ((BracedBlock)en_).abruptGroup(anEl_);
            }
            en_.checkTree(_cont, anEl_);
            if (!_cont.getOptions().isReadOnly()) {
                en_.setAssignmentAfter(_cont, anEl_);
            }
            if (en_ instanceof BreakableBlock && !((BreakableBlock)en_).getRealLabel().isEmpty()) {
                labels_.removeLast();
            }
            while (true) {
                n_ = en_.getNextSibling();
                if (n_ != null) {
                    en_ = n_;
                    break;
                }
                BracedBlock par_;
                par_ = en_.getParent();
                _cont.getAnalyzing().setCurrentBlock(par_);
                par_.abrupt(_cont, anEl_);
                par_.abruptGroup(anEl_);
                if (par_ == this) {
                    setAssignmentAfterCall(_cont, anEl_);
                    page_.removeLocalVars();
                    page_.removeVars();
                    page_.removeMutableLoopVars();
                    page_.removeCatchVars();
                    return;
                }
                if (par_ instanceof ForMutableIterativeLoop) {
                    ((ForMutableIterativeLoop)par_).buildIncrementPart(_cont);
                }
                par_.checkTree(_cont, anEl_);
                if (!_cont.getOptions().isReadOnly()) {
                    par_.setAssignmentAfter(_cont, anEl_);
                }
                parents_.removeLast();
                if (par_ instanceof BreakableBlock) {
                    parentsBreakables_.removeLast();
                }
                if (par_ instanceof Loop) {
                    parentsContinuable_.removeLast();
                }
                if (par_ instanceof Eval && !(par_ instanceof FinallyEval)) {
                    Block ne_ = par_;
                    boolean fin_ = false;
                    while (ne_ instanceof Eval) {
                        if (ne_ instanceof FinallyEval) {
                            fin_ = true;
                            break;
                        }
                        ne_ = ne_.getNextSibling();
                    }
                    if (fin_) {
                        parentsReturnable_.removeLast();
                    }
                }
                page_.removeLocalVars();
                page_.removeVars();
                page_.removeMutableLoopVars();
                page_.removeCatchVars();
                if (par_ instanceof BreakableBlock && !((BreakableBlock)par_).getRealLabel().isEmpty()) {
                    labels_.removeLast();
                }
                en_ = par_;
            }
        }
    }

    public String getPseudoSignature(Analyzable _an) {
        if (this instanceof ReturnableWithSignature) {
            return ((ReturnableWithSignature)this).getSignature(_an);
        }
        return EMPTY_STRING;
    }
    public abstract void setAssignmentBeforeCall(Analyzable _an, AnalyzingEl _anEl);
    public abstract void setAssignmentAfterCall(Analyzable _an, AnalyzingEl _anEl);
    @Override
    public void buildAnnotations(ContextEl _context) {
        annotationsOps = new CustList<CustList<ExecOperationNode>>();
        int len_ = annotationsIndexes.size();
        AnalyzedPageEl page_ = _context.getAnalyzing();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            page_.setGlobalOffset(begin_);
            page_.setOffset(0);
            Calculation c_ = Calculation.staticCalculation(true);
            annotationsOps.add(ElUtil.getAnalyzedOperations(annotations.get(i), _context, c_));
        }
    }

    protected void buildAnnotationsReport(ContextEl _cont, CustList<PartOffset> _parts) {
        int len_ = annotationsIndexes.size();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            int end_ = begin_ + annotations.get(i).length();
            ElUtil.buildCoverageReport(_cont,begin_,this,annotationsOps.get(i),end_,_parts,0,"",true);
        }
    }
    @Override
    public void reduce(ContextEl _context) {
        CustList<CustList<ExecOperationNode>> annotationsOps_;
        annotationsOps_ = new CustList<CustList<ExecOperationNode>>();
        for (CustList<ExecOperationNode> a: annotationsOps) {
            ExecOperationNode r_ = a.last();
            annotationsOps_.add(ElUtil.getReducedNodes(r_));
        }
        annotationsOps = annotationsOps_;
    }
    @Override
    public StringList getAnnotations() {
        return annotations;
    }
    @Override
    public CustList<CustList<ExecOperationNode>> getAnnotationsOps() {
        return annotationsOps;
    }
    @Override
    public Ints getAnnotationsIndexes() {
        return annotationsIndexes;
    }
}
