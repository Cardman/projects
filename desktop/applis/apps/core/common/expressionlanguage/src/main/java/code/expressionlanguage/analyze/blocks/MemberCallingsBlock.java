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

    public final void buildFctInstructionsReadOnly(ExecMemberCallingsBlock _mem, AnalyzedPageEl _page) {
        _page.setGlobalOffset(getOffset().getOffsetTrim());
        _page.setOffset(0);
        _page.setBlockToWrite(_mem);
        _page.setVariableIssue(false);
        Block firstChild_ = getFirstChild();
        _page.setExecDeclareVariable(null);
        _page.setCurrentBlock(this);
        _page.setCurrentFct(this);
        StringMap<StringList> vars_ = ContextUtil.getCurrentConstraints(_page);
        Mapping mapping_ = new Mapping();
        mapping_.setMapping(vars_);
        AnalyzingEl anEl_ = new AnalyzingEl(mapping_);
        anEl_.getMappingBracedMembers().put(this,_mem);
        _page.getCoverage().putBlockOperations(_mem,this);
        _page.setAnalysisAss(anEl_);
        anEl_.setRoot(this);
        Block en_ = this;
        CustList<BracedBlock> parents_ = anEl_.getParents();
        CustList<BreakableBlock> parentsBreakables_ = anEl_.getParentsBreakables();
        CustList<Loop> parentsContinuable_ = anEl_.getParentsContinuables();
        CustList<Eval> parentsReturnable_ = anEl_.getParentsReturnables();
        StringList labels_ = anEl_.getLabels();
        if (firstChild_ == null) {
            checkIndexes(en_, _page);
            anEl_.reach(this);
            abrupt(anEl_);
            setAssignmentAfterCallReadOnly(anEl_, _page);
            _page.getInfosVars().clear();
            return;
        }
        while (true) {
            _page.setCurrentBlock(en_);
            _page.setCurrentAnaBlock(en_);
            anEl_.putLabel(this);
            addPossibleEmpty(en_);
            _page.getCoverage().putBlockOperations(en_);
            if (en_ == this) {
                anEl_.reach(this);
            } else {
                en_.checkLabelReference(anEl_, _page);
                en_.reach(anEl_, _page);
            }
            processUnreachable(anEl_, en_, _page);
            checkIndexes(en_, _page);
            Block n_ = en_.getFirstChild();
            addParent(anEl_, en_, parents_, parentsBreakables_, parentsContinuable_, parentsReturnable_, n_);
            boolean visit_ = true;
            if (en_ != anEl_.getRoot()) {
                visit_ = tryBuildExpressionLanguageReadOnly(en_, _page);
            }
            if (visit_ && en_ instanceof BracedBlock&& n_ != null) {
                _page.setBlockToWrite(anEl_.getMappingBracedMembers().getVal((BracedBlock) en_));
            }
            if (visit_ && n_ != null) {
                en_ = n_;
                continue;
            }
            if (visit_) {
                en_.abrupt(anEl_);
                abrupGroup(anEl_, en_);
            }
            en_.checkTree(anEl_, _page);
            removeLabel(en_, labels_);
            while (true) {
                n_ = en_.getNextSibling();
                if (n_ != null) {
                    en_ = n_;
                    break;
                }
                BracedBlock par_;
                par_ = en_.getParent();
                _page.setCurrentBlock(par_);
                _page.setCurrentAnaBlock(par_);
                par_.abrupt(anEl_);
                par_.abruptGroup(anEl_);
                if (par_ == this) {
                    setAssignmentAfterCallReadOnly(anEl_, _page);
                    par_.removeAllVars(_page);
                    _page.getInfosVars().clear();
                    return;
                }
                par_.checkTree(anEl_, _page);
                parents_.removeLast();
                removeBreakablePar(parentsBreakables_, par_);
                removeContinuablePar(parentsContinuable_, par_);
                removeReturnablePar(parentsReturnable_, par_);
                par_.removeAllVars(_page);
                removeLabel(par_, labels_);
                _page.setBlockToWrite(_page.getBlockToWrite().getParent());
                en_ = par_;
            }
        }
    }

    private void checkIndexes(Block en_, AnalyzedPageEl _page) {
        for (int i:en_.getBadIndexes()) {
            FoundErrorInterpret b_ = new FoundErrorInterpret();
            b_.setFileName(getFile().getFileName());
            b_.setIndexFile(i);
            //underline index char
            b_.buildError(_page.getAnalysisMessages().getBadIndexInParser());
            _page.addLocError(b_);
            en_.setReachableError(true);
            en_.getErrorsBlock().add(b_.getBuiltError());
        }
    }

    private static void addParent(AnalyzingEl _anEl, Block _en, CustList<BracedBlock> _parents,
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

    private void processUnreachable(AnalyzingEl _anEl, Block _en, AnalyzedPageEl _page) {
        if (!(_en instanceof RootBlock)&&!_anEl.isReachable(_en)) {
            //error
            FoundErrorInterpret deadCode_ = new FoundErrorInterpret();
            deadCode_.setFileName(getFile().getFileName());
            deadCode_.setIndexFile(_en.getOffset().getOffsetTrim());
            //all header expression
            deadCode_.buildError(_page.getAnalysisMessages().getDeadCode(),
                    getPseudoSignature(_page));
            _page.addLocError(deadCode_);
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

    public String getPseudoSignature(AnalyzedPageEl _page) {
        return getSignature(_page);
    }

    public abstract void setAssignmentAfterCallReadOnly(AnalyzingEl _anEl, AnalyzedPageEl _page);

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
