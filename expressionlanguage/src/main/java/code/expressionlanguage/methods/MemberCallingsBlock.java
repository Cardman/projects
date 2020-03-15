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
import code.expressionlanguage.opers.util.MethodAccessKind;
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
            setAssignmentBeforeCall(_cont, anEl_);
            anEl_.reach(this);
            abrupt(_cont, anEl_);
            setAssignmentAfterCall(_cont, anEl_);
            return;
        }
        while (true) {
            _cont.getAnalyzing().setCurrentBlock(en_);
            addPossibleEmpty(en_);
            _cont.getCoverage().putBlockOperations(_cont,en_);
            if (en_ == this) {
                setAssignmentBeforeCall(_cont, anEl_);
                anEl_.reach(this);
            } else {
                en_.checkLabelReference(_cont, anEl_);
                en_.setAssignmentBefore(_cont, anEl_);
                en_.reach(_cont, anEl_);
            }
            processUnreachable(_cont, anEl_, en_);
            Block n_ = en_.getFirstChild();
            addParent(_cont, en_, parents_, parentsBreakables_, parentsContinuable_, parentsReturnable_, n_);
            if (en_ != anEl_.getRoot()) {
                tryBuildExpressionLanguage(en_, _cont);
            }
            if (n_ != null) {
                en_ = n_;
                continue;
            }
            en_.abrupt(_cont, anEl_);
            abrupGroup(anEl_, en_);
            en_.checkTree(_cont, anEl_);
            en_.setAssignmentAfter(_cont, anEl_);
            removeLabel(en_, labels_);
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
                par_.setAssignmentAfter(_cont, anEl_);
                parents_.removeLast();
                removeBreakablePar(parentsBreakables_, par_);
                removeContinuablePar(parentsContinuable_, par_);
                removeReturnablePar(parentsReturnable_, par_);
                page_.removeLocalVars();
                page_.removeVars();
                page_.removeMutableLoopVars();
                page_.removeCatchVars();
                removeLabel(par_, labels_);
                en_ = par_;
            }
        }
    }

    private static void addPossibleEmpty(Block _en) {
        if (_en instanceof BracedBlock && _en.getFirstChild() == null) {
            if (!(_en instanceof SwitchBlock) && !(_en instanceof DoWhileCondition)) {
                OffsetsBlock off_ = _en.getOffset();
                EmptyInstruction empty_ = new EmptyInstruction(off_);
                ((BracedBlock) _en).appendChild(empty_);
            }
        }
    }

    private static void addBreakablePar(Block _en, CustList<BreakableBlock> _parentsBreakables) {
        if (_en instanceof BreakableBlock) {
            _parentsBreakables.add((BreakableBlock) _en);
        }
    }

    private static void abrupGroup(AnalyzingEl _anEl, Block _en) {
        if (_en instanceof BracedBlock) {
            ((BracedBlock) _en).abruptGroup(_anEl);
        }
    }

    private static void removeLabel(Block _en, StringList _labels) {
        if (_en instanceof BreakableBlock && !((BreakableBlock) _en).getRealLabel().isEmpty()) {
            _labels.removeLast();
        }
    }

    private static void removeContinuablePar(CustList<Loop> _parentsContinuable, BracedBlock _par) {
        if (_par instanceof Loop) {
            _parentsContinuable.removeLast();
        }
    }

    public final void buildFctInstructionsReadOnly(ContextEl _cont) {
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
            anEl_.reach(this);
            abrupt(_cont, anEl_);
            setAssignmentAfterCallReadOnly(_cont, anEl_);
            return;
        }
        while (true) {
            _cont.getAnalyzing().setCurrentBlock(en_);
            addPossibleEmpty(en_);
            _cont.getCoverage().putBlockOperations(_cont,en_);
            if (en_ == this) {
                anEl_.reach(this);
            } else {
                en_.checkLabelReference(_cont, anEl_);
                en_.reach(_cont, anEl_);
            }
            processUnreachable(_cont, anEl_, en_);
            Block n_ = en_.getFirstChild();
            addParent(_cont, en_, parents_, parentsBreakables_, parentsContinuable_, parentsReturnable_, n_);
            if (en_ != anEl_.getRoot()) {
                tryBuildExpressionLanguageReadOnly(en_, _cont);
            }
            if (n_ != null) {
                en_ = n_;
                continue;
            }
            en_.abrupt(_cont, anEl_);
            abrupGroup(anEl_, en_);
            en_.checkTree(_cont, anEl_);
            removeLabel(en_, labels_);
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
                    setAssignmentAfterCallReadOnly(_cont, anEl_);
                    page_.removeLocalVars();
                    page_.removeVars();
                    page_.removeMutableLoopVars();
                    page_.removeCatchVars();
                    return;
                }
                if (par_ instanceof ForMutableIterativeLoop) {
                    ((ForMutableIterativeLoop)par_).buildIncrementPartReadOnly(_cont);
                }
                par_.checkTree(_cont, anEl_);
                parents_.removeLast();
                removeBreakablePar(parentsBreakables_, par_);
                removeContinuablePar(parentsContinuable_, par_);
                removeReturnablePar(parentsReturnable_, par_);
                page_.removeLocalVars();
                page_.removeVars();
                page_.removeMutableLoopVars();
                page_.removeCatchVars();
                removeLabel(par_, labels_);
                en_ = par_;
            }
        }
    }

    private static void addParent(ContextEl _cont, Block _en, CustList<BracedBlock> _parents,
                                  CustList<BreakableBlock> _parentsBreakables, CustList<Loop> _parentsContinuable,
                                  CustList<Eval> _parentsReturnable, Block _n) {
        if (_en instanceof BracedBlock && _n != null) {
            _cont.getAnalyzing().initLocalVars();
            _cont.getAnalyzing().initVars();
            _cont.getAnalyzing().initMutableLoopVars();
            _cont.getAnalyzing().initCatchVars();
            addBreakablePar(_en, _parentsBreakables);
            addContinuablePar(_en, _parentsContinuable);
            addReturnablePar(_en, _parentsReturnable);
            _parents.add((BracedBlock) _en);
        }
    }

    private void processUnreachable(ContextEl _cont, AnalyzingEl _anEl, Block _en) {
        if (!_anEl.isReachable(_en)) {
            //error
            DeadCodeMethod deadCode_ = new DeadCodeMethod();
            deadCode_.setFileName(getFile().getFileName());
            deadCode_.setIndexFile(_en.getOffset().getOffsetTrim());
            deadCode_.setId(getPseudoSignature(_cont));
            _cont.addError(deadCode_);
        }
    }

    private static void addContinuablePar(Block _en, CustList<Loop> _parentsContinuable) {
        if (_en instanceof Loop) {
            _parentsContinuable.add((Loop) _en);
        }
    }

    private static void addReturnablePar(Block _en, CustList<Eval> _parentsReturnable) {
        if (_en instanceof Eval && !(_en instanceof FinallyEval)) {
            Block ne_ = _en;
            boolean fin_ = false;
            while (ne_ instanceof Eval) {
                if (ne_ instanceof FinallyEval) {
                    fin_ = true;
                    break;
                }
                ne_ = ne_.getNextSibling();
            }
            if (fin_) {
                _parentsReturnable.add((Eval) _en);
            }
        }
    }

    private static void removeBreakablePar(CustList<BreakableBlock> _parentsBreakables, BracedBlock _par) {
        if (_par instanceof BreakableBlock) {
            _parentsBreakables.removeLast();
        }
    }

    private static void removeReturnablePar(CustList<Eval> _parentsReturnable, BracedBlock _par) {
        if (_par instanceof Eval && !(_par instanceof FinallyEval)) {
            Block ne_ = _par;
            boolean fin_ = false;
            while (ne_ instanceof Eval) {
                if (ne_ instanceof FinallyEval) {
                    fin_ = true;
                    break;
                }
                ne_ = ne_.getNextSibling();
            }
            if (fin_) {
                _parentsReturnable.removeLast();
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
    public abstract void setAssignmentAfterCallReadOnly(Analyzable _an, AnalyzingEl _anEl);
    @Override
    public void buildAnnotations(ContextEl _context) {
        annotationsOps = new CustList<CustList<ExecOperationNode>>();
        int len_ = annotationsIndexes.size();
        AnalyzedPageEl page_ = _context.getAnalyzing();
        for (int i = 0; i < len_; i++) {
            int begin_ = annotationsIndexes.get(i);
            page_.setGlobalOffset(begin_);
            page_.setOffset(0);
            Calculation c_ = Calculation.staticCalculation(MethodAccessKind.STATIC);
            annotationsOps.add(ElUtil.getAnalyzedOperationsReadOnly(annotations.get(i), _context, c_));
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
