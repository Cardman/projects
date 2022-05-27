package code.expressionlanguage.analyze.reach.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;

public abstract class ReachMemberCallingsBlock extends ReachBracedBlock implements FunctionBlock,ReturnableWithSignature {
    protected ReachMemberCallingsBlock(AbsBk _info) {
        super(_info);
    }

    public static ReachMemberCallingsBlock newReachBlocks(MemberCallingsBlock _list) {
        ReachMemberCallingsBlock m_ = ReachBlock.newReachBlock(_list);
        if (_list.getFirstChild() == null) {
            return m_;
        }
        AbsBk c_ = _list;
        ReachBlock reach_ = m_;
        while (c_ != null) {
            AbsBk f_ = c_.getFirstChild();
            if (!(c_ instanceof RootBlock)&&reach_ instanceof ReachBracedBlock&&f_ != null) {
                ReachBlock af_ = ReachBlock.newReachBlock(f_);
                ((ReachBracedBlock)reach_).appendChild(af_);
                reach_ = af_;
                c_ = f_;
                continue;
            }
            while (c_ != null) {
                AbsBk n_ = c_.getNextSibling();
                if (n_ != null) {
                    ReachBlock af_ = ReachBlock.newReachBlock(n_);
                    ReachBracedBlock par_ = reach_.getParent();
                    par_.appendChild(af_);
                    reach_ = af_;
                    c_ = n_;
                    break;
                }
                BracedBlock p_ = c_.getParent();
                if (p_ == _list) {
                    c_ = null;
                } else {
                    c_ = p_;
                    reach_ = reach_.getParent();
                }
            }
        }
        return m_;
    }

    public final void buildFctInstructionsReadOnly(AnalyzedPageEl _page, AnalyzingEl _anEl) {
        _page.setSumOffset(getOffset());
        _page.zeroOffset();
        ReachBlock firstChild_ = getFirstChild();
        _page.setCurrentBlock(getInfo());
        _page.setAnalysisAss(_anEl);
        ReachBlock en_ = this;
        if (firstChild_ == null) {
            _anEl.reach(this);
            abrupt(_anEl);
            after(_page, _anEl, this);
            _page.getInfosVars().clear();
            return;
        }
        while (true) {
            _page.setCurrentBlock(en_.getInfo());
            _anEl.putLabel(this);
            reachProcess(_page, _anEl, en_);
            processUnreachable(_anEl, en_, _page);
            ReachBlock n_ = en_.getFirstChild();
            addParent(_anEl, en_, n_);
            boolean visit_ = visit(_page, en_);
            if (visit_ && n_ != null) {
                en_ = n_;
                continue;
            }
            if (visit_) {
                en_.abrupt(_anEl);
                abrupGroup(_anEl, en_);
            }
            while (true) {
                n_ = en_.getNextSibling();
                if (n_ != null) {
                    en_ = n_;
                    break;
                }
                ReachBracedBlock par_;
                par_ = en_.getParent();
                _page.setCurrentBlock(par_.getInfo());
                par_.abrupt(_anEl);
                abrupGroup(_anEl,par_);
                if (par_ == this) {
                    after(_page, _anEl, this);
                    _page.getInfosVars().clear();
                    return;
                }
                en_ = par_;
            }
        }
    }

    private boolean visit(AnalyzedPageEl _page, ReachBlock _en) {
        boolean visit_ = true;
        if (_en != this) {
            visit_ = tryBuildExpressionLanguageReadOnly(_en, _page);
        }
        return visit_;
    }

    private void reachProcess(AnalyzedPageEl _page, AnalyzingEl _anEl, ReachBlock _en) {
        if (_en == this) {
            _anEl.reach(this);
        } else if (_en instanceof ReachCaseCondition){
            ((ReachCaseCondition) _en).reachCase(_anEl, _page);
        } else if (_en instanceof ReachCatchEval){
            ((ReachCatchEval) _en).reachCatch(_anEl, _page);
        } else {
            _en.reach(_anEl);
        }
    }

    private void after(AnalyzedPageEl _page, AnalyzingEl _anEl, ReachMemberCallingsBlock _current) {
        if (_current instanceof ReachMemberCallingsBlockSide) {
            ((ReachMemberCallingsBlockSide)_current).setAssignmentAfterCallReadOnly(_anEl, _page);
        }

    }

    protected static boolean tryBuildExpressionLanguageReadOnly(ReachBlock _block, AnalyzedPageEl _page) {
        if (isVisitable(_block)) {
            if (_block instanceof ReachBuildableElMethod) {
                ((ReachBuildableElMethod)_block).buildExpressionLanguageReadOnly(_page);
            }
            return true;
        }
        return false;
    }
    public static boolean isVisitable(ReachBlock _block) {
        return _block instanceof ReachBreakBlock|| _block instanceof ReachContinueBlock|| _block instanceof ReachDeclareVariable||_block instanceof ReachElseCondition||_block instanceof ReachEmptyInstruction||_block instanceof ReachTryEval||_block instanceof ReachAbstractCatchEval||_block instanceof ReachFinallyEval||_block instanceof ReachDoBlock||_block instanceof ReachDefaultCondition||_block instanceof ReachBuildableElMethod || _block instanceof ReachUnclassedBracedBlock;
    }

    private static void abrupGroup(AnalyzingEl _anEl, ReachBlock _en) {
        if (_en instanceof ReachAbruptGroup) {
            ((ReachAbruptGroup) _en).abruptGroup(_anEl);
        }
    }

    private static void addParent(AnalyzingEl _anEl, ReachBlock _en,
                                  ReachBlock _n) {
        if (_en instanceof ReachBracedBlock && _n != null && _en instanceof ReachBreakableBlock) {
            _anEl.putLabel(_en, ((ReachBreakableBlock) _en).getRealLabel());
        }
    }

    private void processUnreachable(AnalyzingEl _anEl, ReachBlock _en, AnalyzedPageEl _page) {
        if (!(_en instanceof ReachRootBlock)&&!_anEl.isReachable(_en)) {
            //error
            FoundErrorInterpret deadCode_ = new FoundErrorInterpret();
            deadCode_.setFile(getFile());
            deadCode_.setIndexFile(_en.getOffset());
            //all header expression
            deadCode_.buildError(_page.getAnalysisMessages().getDeadCode(),
                    getPseudoSignature(_page));
            _page.addLocError(deadCode_);
            _en.addErrorBlock(deadCode_.getBuiltError());
        }
    }

    public String getPseudoSignature(AnalyzedPageEl _page) {
        return getSignature(_page);
    }

}
