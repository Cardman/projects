package code.expressionlanguage.methods;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.exceptions.BadStaticException;
import code.sml.Element;

public abstract class MemberCallingsBlock extends BracedBlock implements FunctionBlock {

    public MemberCallingsBlock(Element _el, ContextEl _importingPage,
            int _indexChild, BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    MemberCallingsBlock(ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_importingPage, _indexChild, _m);
    }

    @Override
    public void checkFctBlocksTree(ContextEl _cont) {
        if (!(getParent() instanceof RootBlock)) {
            PageEl page_ = _cont.getLastPage();
            page_.setProcessingAttribute(EMPTY_STRING);
            page_.setOffset(0);
            throw new BadStaticException(_cont.joinPages());
        }
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(EMPTY_STRING);
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
        page_.setProcessingAttribute(EMPTY_STRING);
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
        page_.setProcessingAttribute(EMPTY_STRING);
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

}
