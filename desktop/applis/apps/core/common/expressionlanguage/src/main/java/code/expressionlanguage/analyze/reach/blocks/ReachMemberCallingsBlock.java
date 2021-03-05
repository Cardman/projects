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
        ReachBlock ac_ = m_;
        while (c_ != null) {
            AbsBk f_ = c_.getFirstChild();
            if (!(c_ instanceof RootBlock)&&ac_ instanceof ReachBracedBlock&&f_ != null) {
                ReachBlock af_ = ReachBlock.newReachBlock(f_);
                ((ReachBracedBlock)ac_).appendChild(af_);
                ac_ = af_;
                c_ = f_;
                continue;
            }
            while (true) {
                AbsBk n_ = c_.getNextSibling();
                if (n_ != null) {
                    ReachBlock af_ = ReachBlock.newReachBlock(n_);
                    ReachBracedBlock par_ = ac_.getParent();
                    par_.appendChild(af_);
                    ac_ = af_;
                    c_ = n_;
                    break;
                }
                BracedBlock p_ = c_.getParent();
                if (p_ == _list) {
                    c_ = null;
                    break;
                }
                c_ = p_;
                ac_ = ac_.getParent();
            }
        }
        return m_;
    }

    public final void buildFctInstructionsReadOnly(AnalyzedPageEl _page, AnalyzingEl _anEl) {
        _page.setGlobalOffset(getOffset().getOffsetTrim());
        _page.setOffset(0);
        ReachBlock firstChild_ = getFirstChild();
        _page.setMerged(false);
        _page.setCurrentBlock(getInfo());
        _page.setAnalysisAss(_anEl);
        ReachBlock en_ = this;
        if (firstChild_ == null) {
            _anEl.reach(this);
            abrupt(_anEl);
            setAssignmentAfterCallReadOnly(_anEl, _page);
            _page.getInfosVars().clear();
            return;
        }
        while (true) {
            _page.setCurrentBlock(en_.getInfo());
            _anEl.putLabel(this);
            if (en_ == this) {
                _anEl.reach(this);
            } else {
                en_.reach(_anEl, _page);
            }
            processUnreachable(_anEl, en_, _page);
            ReachBlock n_ = en_.getFirstChild();
            addParent(_anEl, en_, n_);
            boolean visit_ = true;
            if (en_ != this) {
                visit_ = tryBuildExpressionLanguageReadOnly(en_, _page);
            }
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
                par_.abruptGroup(_anEl);
                if (par_ == this) {
                    setAssignmentAfterCallReadOnly(_anEl, _page);
                    _page.getInfosVars().clear();
                    return;
                }
                en_ = par_;
            }
        }
    }

    public abstract void setAssignmentAfterCallReadOnly(AnalyzingEl _anEl, AnalyzedPageEl _page);

    protected static boolean tryBuildExpressionLanguageReadOnly(ReachBlock _block, AnalyzedPageEl _page) {
        if (_block instanceof ReachBuildableElMethod) {
            ((ReachBuildableElMethod)_block).buildExpressionLanguageReadOnly(_page);
            return true;
        }
        return processOther(_block);
    }

    private static boolean processOther(ReachBlock _block) {
        return _block instanceof ReachUnclassedBracedBlock;
    }

    private static void abrupGroup(AnalyzingEl _anEl, ReachBlock _en) {
        if (_en instanceof ReachBracedBlock) {
            ((ReachBracedBlock) _en).abruptGroup(_anEl);
        }
    }

    private static void addParent(AnalyzingEl _anEl, ReachBlock _en,
                                  ReachBlock _n) {
        if (_en instanceof ReachBracedBlock && _n != null) {
            if (_en instanceof ReachBreakableBlock) {
                _anEl.putLabel(_en,((ReachBreakableBlock)_en).getRealLabel());
            }
        }
    }

    private void processUnreachable(AnalyzingEl _anEl, ReachBlock _en, AnalyzedPageEl _page) {
        if (!(_en instanceof ReachRootBlock)&&!_anEl.isReachable(_en)) {
            //error
            FoundErrorInterpret deadCode_ = new FoundErrorInterpret();
            deadCode_.setFileName(getFile().getFileName());
            deadCode_.setIndexFile(_en.getOffset().getOffsetTrim());
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
