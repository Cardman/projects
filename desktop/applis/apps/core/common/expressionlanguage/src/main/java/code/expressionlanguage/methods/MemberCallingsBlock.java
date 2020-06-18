package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.Mapping;
import code.expressionlanguage.opers.util.MethodAccessKind;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public abstract class MemberCallingsBlock extends BracedBlock implements FunctionBlock {

    MemberCallingsBlock(OffsetsBlock _offset) {
        super(_offset);
    }

    private static void addPossibleEmpty(Block _en) {
        if (_en instanceof BracedBlock && _en.getFirstChild() == null) {
            if (!(_en instanceof SwitchBlock) && !(_en instanceof DoWhileCondition) && (_en instanceof BuildableElMethod || _en instanceof UnclassedBracedBlock)) {
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

    public final void buildFctInstructionsReadOnly(ContextEl _cont, ExecMemberCallingsBlock _mem) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(getOffset().getOffsetTrim());
        page_.setOffset(0);
        page_.setBlockToWrite(_mem);
        Block firstChild_ = getFirstChild();
        page_.setExecDeclareVariable(null);
        StringMap<StringList> vars_ = _cont.getAnalyzing().getCurrentConstraints().getCurrentConstraints();
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        AnalyzingEl anEl_ = new AnalyzingEl(mapping_);
        anEl_.getMappingBracedMembers().put(this,_mem);
        anEl_.getMappingMembers().put(_mem,this);
        _cont.getCoverage().putBlockOperations(_cont,_mem,this);
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
            page_.setCurrentBlock(en_);
            page_.setCurrentAnaBlock(en_);
            anEl_.putLabel(this);
            anEl_.putFinal(this);
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
            addParent(_cont,anEl_, en_, parents_, parentsBreakables_, parentsContinuable_, parentsReturnable_, n_);
            boolean visit_ = true;
            if (en_ != anEl_.getRoot()) {
                visit_ = tryBuildExpressionLanguageReadOnly(en_, _cont);
            }
            if (visit_ && en_ instanceof BracedBlock&& n_ != null) {
                page_.setBlockToWrite(anEl_.getMappingBracedMembers().getVal((BracedBlock) en_));
            }
            if (visit_ && n_ != null) {
                en_ = n_;
                continue;
            }
            if (visit_) {
                en_.abrupt(_cont, anEl_);
                abrupGroup(anEl_, en_);
            }
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
                page_.setCurrentBlock(par_);
                page_.setCurrentAnaBlock(par_);
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
                page_.setBlockToWrite(page_.getBlockToWrite().getParent());
                en_ = par_;
            }
        }
    }

    private static void addParent(ContextEl _cont, AnalyzingEl _anEl,Block _en, CustList<BracedBlock> _parents,
                                  CustList<BreakableBlock> _parentsBreakables, CustList<Loop> _parentsContinuable,
                                  CustList<Eval> _parentsReturnable, Block _n) {
        if (_en instanceof BracedBlock && _n != null) {
            if (_en instanceof BreakableBlock) {
                _anEl.putLabel(_en,((BreakableBlock)_en).getRealLabel());
            }
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
            FoundErrorInterpret deadCode_ = new FoundErrorInterpret();
            deadCode_.setFileName(getFile().getFileName());
            deadCode_.setIndexFile(_en.getOffset().getOffsetTrim());
            //all header expression
            deadCode_.buildError(_cont.getAnalysisMessages().getDeadCode(),
                    getPseudoSignature(_cont));
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

    public String getPseudoSignature(ContextEl _an) {
        if (this instanceof ReturnableWithSignature) {
            return ((ReturnableWithSignature)this).getSignature(_an);
        }
        return EMPTY_STRING;
    }

    public abstract void setAssignmentAfterCallReadOnly(ContextEl _an, AnalyzingEl _anEl);

    public abstract  MethodAccessKind getStaticContext();

}
