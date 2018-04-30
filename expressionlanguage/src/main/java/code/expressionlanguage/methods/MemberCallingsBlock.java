package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.util.UnexpectedTagName;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.sml.Element;

public abstract class MemberCallingsBlock extends BracedBlock implements FunctionBlock {

    public MemberCallingsBlock(Element _el, ContextEl _importingPage,
            int _indexChild, BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    MemberCallingsBlock(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
    }

    @Override
    public void checkFctBlocksTree(ContextEl _cont) {
        if (!(getParent() instanceof RootBlock)) {
            PageEl page_ = _cont.getLastPage();
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setRc(getRowCol(0, getOffset().getOffsetTrim()));
            _cont.getClasses().getErrorsDet().add(un_);
        }
        PageEl page_ = _cont.getLastPage();
        page_.setGlobalOffset(getOffset().getOffsetTrim());
        page_.setOffset(0);
        if (getFirstChild() == null) {
            return;
        }
        Block en_ = this;
        while (true) {
            Block n_ = en_.getFirstChild();
            if (n_ != null) {
                tryCheckBlocksTree(n_, _cont);
                en_ = n_;
                continue;
            }
            n_ = en_.getNextSibling();
            if (n_ != null) {
                tryCheckBlocksTree(n_, _cont);
                en_ = n_;
                continue;
            }
            n_ = en_.getParent();
            if (n_ == this) {
                break;
            }
            Block next_ = n_.getNextSibling();
            boolean exitByBreak_ = false;
            while (next_ == null) {
                Block par_ = n_.getParent();
                if (par_ == this) {
                    exitByBreak_ = true;
                    break;
                }
                next_ = par_.getNextSibling();
                n_ = par_;
            }
            if (exitByBreak_) {
                break;
            }
            tryCheckBlocksTree(next_, _cont);
            en_ = next_;
        }
    }

    @Override
    public final void buildFctInstructions(ContextEl _cont) {
        PageEl page_ = _cont.getLastPage();
        page_.setGlobalOffset(getOffset().getOffsetTrim());
        page_.setOffset(0);
        if (getFirstChild() == null) {
            return;
        }
        Block en_ = this;
        while (true) {
            Block n_ = en_.getFirstChild();
            if (n_ != null) {
                tryBuildExpressionLanguage(n_, _cont);
                en_ = n_;
                continue;
            }
            n_ = en_.getNextSibling();
            if (n_ != null) {
                tryBuildExpressionLanguage(n_, _cont);
                en_ = n_;
                continue;
            }
            n_ = en_.getParent();
            if (n_ == this) {
                break;
            }
            en_.removeLocalVariablesFromParent(_cont);
            Block next_ = n_.getNextSibling();
            boolean exitByBreak_ = false;
            while (next_ == null) {
                Block par_ = n_.getParent();
                if (par_ == this) {
                    exitByBreak_ = true;
                    break;
                }
                n_.removeLocalVariablesFromParent(_cont);
                next_ = par_.getNextSibling();
                n_ = par_;
            }
            if (exitByBreak_) {
                break;
            }
            tryBuildExpressionLanguage(next_, _cont);
            en_ = next_;
        }
    }

    @Override
    public final void checkFctConstrCalls(ContextEl _cont) {
        PageEl page_ = _cont.getLastPage();
        page_.setGlobalOffset(getOffset().getOffsetTrim());
        page_.setOffset(0);
        if (getFirstChild() == null) {
            return;
        }
        Block en_ = this;
        while (true) {
            Block n_ = en_.getFirstChild();
            if (n_ != null) {
                tryCheckConstCall(n_, _cont);
                en_ = n_;
                continue;
            }
            n_ = en_.getNextSibling();
            if (n_ != null) {
                tryCheckConstCall(n_, _cont);
                en_ = n_;
                continue;
            }
            n_ = en_.getParent();
            if (n_ == this) {
                break;
            }
            Block next_ = n_.getNextSibling();
            boolean exitByBreak_ = false;
            while (next_ == null) {
                Block par_ = n_.getParent();
                if (par_ == this) {
                    exitByBreak_ = true;
                    break;
                }
                next_ = par_.getNextSibling();
                n_ = par_;
            }
            if (exitByBreak_) {
                break;
            }
            tryCheckConstCall(next_, _cont);
            en_ = next_;
        }
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return null;
    }
}
