package code.expressionlanguage.methods;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.Templates;
import code.expressionlanguage.errors.custom.DeadCodeMethod;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public abstract class MemberCallingsBlock extends BracedBlock implements FunctionBlock {

    MemberCallingsBlock(ContextEl _importingPage,
            BracedBlock _m, OffsetsBlock _offset) {
        super(_importingPage, _m, _offset);
    }

    @Override
    public final void buildFctInstructions(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        if (!(getParent() instanceof RootBlock) && !(this instanceof OperatorBlock)) {
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _cont.getClasses().addError(un_);
        }
        page_.setGlobalOffset(getOffset().getOffsetTrim());
        page_.setOffset(0);
        Block firstChild_ = getFirstChild();
        StringMap<StringList> vars_ = new StringMap<StringList>();
        if (!isStaticContext()) {
            String globalClass_ = page_.getGlobalClass();
            String curClassBase_ = Templates.getIdFromAllTypes(globalClass_);
            for (TypeVar t: _cont.getClasses().getClassBody(curClassBase_).getParamTypesMapValues()) {
                vars_.put(t.getName(), t.getConstraints());
            }
        }
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        AnalyzingEl anEl_ = new AnalyzingEl(mapping_);
        _cont.getAnalyzing().setAnalysisAss(anEl_);
        anEl_.setRoot(this);
        Block en_ = this;
        CustList<BracedBlock> parents_ = anEl_.getParents();
        CustList<BreakableBlock> parentsBreakables_ = anEl_.getParentsBreakables();
        CustList<Loop> parentsContinuable_ = anEl_.getParentsContinuables();
        CustList<Eval> parentsReturnable_ = anEl_.getParentsReturnables();
        StringList labels_ = anEl_.getLabels();
        if (firstChild_ == null) {
            setAssignmentBefore(_cont, anEl_);
            reach(_cont, anEl_);
            abrupt(_cont, anEl_);
            setAssignmentAfter(_cont, anEl_);
            return;
        }
        while (true) {
            _cont.getAnalyzing().setCurrentBlock(en_);
            en_.setAssignmentBefore(_cont, anEl_);
            en_.reach(_cont, anEl_);
            if (!anEl_.isReachable(en_)) {
                //error
                DeadCodeMethod deadCode_ = new DeadCodeMethod();
                deadCode_.setFileName(getFile().getFileName());
                deadCode_.setRc(en_.getRowCol(0, en_.getOffset().getOffsetTrim()));
                if (this instanceof Returnable) {
                    deadCode_.setId(((Returnable)this).getSignature());
                } else {
                    deadCode_.setId(EMPTY_STRING);
                }
                _cont.getClasses().addError(deadCode_);
            }
            Block n_ = en_.getFirstChild();
            if (n_ != null) {
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
                ((BracedBlock)en_).abruptGroup(_cont, anEl_);
            }
            en_.setAssignmentAfter(_cont, anEl_);
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
                par_.abruptGroup(_cont, anEl_);
                par_.setAssignmentAfter(_cont, anEl_);
                if (par_ == this) {
                    return;
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

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        return null;
    }
    @Override
    public void reach(Analyzable _an, AnalyzingEl _anEl) {
        Block prev_ = getPreviousSibling();
        BracedBlock br_ = getParent();
        if (prev_ == null || _anEl.getRoot() == this) {
            if (_anEl.getRoot() == this || _anEl.isReachable(br_)) {
                _anEl.reach(this);
            } else {
                _anEl.unreach(this);
            }
        } else {
            super.reach(_an, _anEl);
        }
    }
}
