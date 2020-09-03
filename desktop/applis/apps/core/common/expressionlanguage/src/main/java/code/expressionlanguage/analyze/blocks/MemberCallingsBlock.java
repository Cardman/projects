package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.MappingLocalType;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public abstract class MemberCallingsBlock extends BracedBlock implements FunctionBlock,ReturnableWithSignature {

    private StringMap<MappingLocalType> mappings = new StringMap<MappingLocalType>();
    private CustList<RootBlock> reserved = new CustList<RootBlock>();
    private CustList<AnonymousTypeBlock> anonymous = new CustList<AnonymousTypeBlock>();
    private CustList<AnonymousFunctionBlock> anonymousFct = new CustList<AnonymousFunctionBlock>();
    private int numberFct;
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
        page_.setVariableIssue(false);
        Block firstChild_ = getFirstChild();
        page_.setExecDeclareVariable(null);
        page_.setCurrentBlock(this);
        _cont.getAnalyzing().setCurrentFct(this);
        StringMap<StringList> vars_ = ContextUtil.getCurrentConstraints(_cont);
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        AnalyzingEl anEl_ = new AnalyzingEl(mapping_);
        anEl_.getMappingBracedMembers().put(this,_mem);
        _cont.getCoverage().putBlockOperations(_cont,_mem,this);
        _cont.getAnalyzing().setAnalysisAss(anEl_);
        anEl_.setRoot(this);
        Block en_ = this;
        CustList<BracedBlock> parents_ = anEl_.getParents();
        CustList<BreakableBlock> parentsBreakables_ = anEl_.getParentsBreakables();
        CustList<Loop> parentsContinuable_ = anEl_.getParentsContinuables();
        CustList<Eval> parentsReturnable_ = anEl_.getParentsReturnables();
        StringList labels_ = anEl_.getLabels();
        if (firstChild_ == null) {
            checkIndexes(_cont, en_);
            anEl_.reach(this);
            abrupt(_cont, anEl_);
            setAssignmentAfterCallReadOnly(_cont, anEl_);
            page_.getInfosVars().clear();
            return;
        }
        while (true) {
            page_.setCurrentBlock(en_);
            page_.setCurrentAnaBlock(en_);
            anEl_.putLabel(this);
            addPossibleEmpty(en_);
            _cont.getCoverage().putBlockOperations(_cont,en_);
            if (en_ == this) {
                anEl_.reach(this);
            } else {
                en_.checkLabelReference(_cont, anEl_);
                en_.reach(_cont, anEl_);
            }
            processUnreachable(_cont, anEl_, en_);
            checkIndexes(_cont, en_);
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
                    par_.removeAllVars(page_);
                    page_.getInfosVars().clear();
                    return;
                }
                par_.checkTree(_cont, anEl_);
                parents_.removeLast();
                removeBreakablePar(parentsBreakables_, par_);
                removeContinuablePar(parentsContinuable_, par_);
                removeReturnablePar(parentsReturnable_, par_);
                par_.removeAllVars(page_);
                removeLabel(par_, labels_);
                page_.setBlockToWrite(page_.getBlockToWrite().getParent());
                en_ = par_;
            }
        }
    }

    private void checkIndexes(ContextEl _cont, Block en_) {
        for (int i:en_.getBadIndexes()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(i);
            //underline index char
            b_.buildError(_cont.getAnalysisMessages().getBadIndexInParser());
            _cont.addError(b_);
            en_.setReachableError(true);
            en_.getErrorsBlock().add(b_.getBuiltError());
        }
    }

    private static void addParent(ContextEl _cont, AnalyzingEl _anEl,Block _en, CustList<BracedBlock> _parents,
                                  CustList<BreakableBlock> _parentsBreakables, CustList<Loop> _parentsContinuable,
                                  CustList<Eval> _parentsReturnable, Block _n) {
        if (_en instanceof BracedBlock && _n != null) {
            if (_en instanceof BreakableBlock) {
                _anEl.putLabel(_en,((BreakableBlock)_en).getRealLabel());
            }
            addBreakablePar(_en, _parentsBreakables);
            addContinuablePar(_en, _parentsContinuable);
            addReturnablePar(_en, _parentsReturnable);
            _parents.add((BracedBlock) _en);
        }
    }

    private void processUnreachable(ContextEl _cont, AnalyzingEl _anEl, Block _en) {
        if (!(_en instanceof RootBlock)&&!_anEl.isReachable(_en)) {
            //error
            FoundErrorInterpret deadCode_ = new FoundErrorInterpret();
            deadCode_.setFileName(getFile().getFileName());
            deadCode_.setIndexFile(_en.getOffset().getOffsetTrim());
            //all header expression
            deadCode_.buildError(_cont.getAnalysisMessages().getDeadCode(),
                    getPseudoSignature(_cont));
            _cont.addError(deadCode_);
            _en.getErrorsBlock().add(deadCode_.getBuiltError());
            _en.setReachableError(true);
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
        return getSignature(_an);
    }

    public abstract void setAssignmentAfterCallReadOnly(ContextEl _an, AnalyzingEl _anEl);

    public abstract  MethodAccessKind getStaticContext();

    public StringMap<MappingLocalType> getMappings() {
        return mappings;
    }

    public CustList<RootBlock> getReserved() {
        return reserved;
    }

    public CustList<AnonymousTypeBlock> getAnonymous() {
        return anonymous;
    }

    public CustList<AnonymousFunctionBlock> getAnonymousFct() {
        return anonymousFct;
    }

    public int getNumberFct() {
        return numberFct;
    }

    public void setNumberFct(int numberFct) {
        this.numberFct = numberFct;
    }
}
