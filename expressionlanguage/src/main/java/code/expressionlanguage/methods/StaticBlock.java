package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.exceptions.BadStaticException;
import code.util.NatTreeMap;
import code.util.StringList;

public final class StaticBlock extends BracedBlock implements AloneBlock {

    public StaticBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
        if (!(getParent() instanceof RootedBlock) || getFirstChild() == null) {
            PageEl page_ = _cont.getLastPage();
//            page_.setProcessingNode(getAssociateElement());
            page_.setProcessingAttribute(EMPTY_STRING);
//            page_.setLookForAttrValue(false);
            page_.setOffset(0);
            throw new BadStaticException(_cont.joinPages());
        }
        PageEl page_ = _cont.getLastPage();
//        page_.setProcessingNode(getAssociateElement());
        page_.setProcessingAttribute(EMPTY_STRING);
//        page_.setLookForAttrValue(false);
        page_.setOffset(0);
//        if (getFirstChild() == null) {
//            return;
//        }
        Block en_ = this;
        while (true) {
            Block n_ = en_.getFirstChild();
            if (n_ != null) {
                tryCheckBlocksTree(n_, _cont);
                en_ = n_;
                continue;
            }
//            if (en_ == this) {
//                break;
//            }
            n_ = en_.getNextSibling();
            if (n_ != null) {
                tryCheckBlocksTree(n_, _cont);
                en_ = n_;
                continue;
            }
            n_ = en_.getParent();
            if (n_ == this) {
                tryCheckBlocksTree(en_, _cont);
                en_.removeLocalVariablesFromParent(_cont);
                break;
            }
            en_.removeLocalVariablesFromParent(_cont);
            Block next_ = n_.getNextSibling();
            boolean exitByBreak_ = false;
            while (next_ == null) {
                Block par_ = n_.getParent();
                if (par_ == this) {
                    exitByBreak_ = true;
                    tryCheckBlocksTree(n_, _cont);
                    n_.removeLocalVariablesFromParent(_cont);
                    break;
                }
                n_.removeLocalVariablesFromParent(_cont);
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
    public void buildInstructions(ContextEl _cont) {
        PageEl page_ = _cont.getLastPage();
//        page_.setProcessingNode(getAssociateElement());
        page_.setProcessingAttribute(EMPTY_STRING);
//        page_.setLookForAttrValue(false);
        page_.setOffset(0);
//        if (getFirstChild() == null) {
//            return;
//        }
        Block en_ = this;
        while (true) {
            Block n_ = en_.getFirstChild();
            if (n_ != null) {
                tryBuildExpressionLanguage(n_, _cont);
                en_ = n_;
                continue;
            }
//            if (en_ == this) {
//                break;
//            }
            n_ = en_.getNextSibling();
            if (n_ != null) {
                tryBuildExpressionLanguage(n_, _cont);
                en_ = n_;
                continue;
            }
            n_ = en_.getParent();
            if (n_ == this) {
                tryBuildExpressionLanguage(en_, _cont);
                en_.removeLocalVariablesFromParent(_cont);
                break;
            }
            en_.removeLocalVariablesFromParent(_cont);
            Block next_ = n_.getNextSibling();
            boolean exitByBreak_ = false;
            while (next_ == null) {
                Block par_ = n_.getParent();
                if (par_ == this) {
                    exitByBreak_ = true;
                    tryBuildExpressionLanguage(n_, _cont);
                    n_.removeLocalVariablesFromParent(_cont);
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
    public void checkConstrCalls(ContextEl _cont) {
        PageEl page_ = _cont.getLastPage();
//        page_.setProcessingNode(getAssociateElement());
        page_.setProcessingAttribute(EMPTY_STRING);
//        page_.setLookForAttrValue(false);
        page_.setOffset(0);
//        if (getFirstChild() == null) {
//            return;
//        }
        Block en_ = this;
        while (true) {
            Block n_ = en_.getFirstChild();
            if (n_ != null) {
                tryCheckConstCall(n_, _cont);
                en_ = n_;
                continue;
            }
//            if (en_ == this) {
//                break;
//            }
            n_ = en_.getNextSibling();
            if (n_ != null) {
                tryCheckConstCall(n_, _cont);
                en_ = n_;
                continue;
            }
            n_ = en_.getParent();
            if (n_ == this) {
                tryCheckConstCall(en_, _cont);
                en_.removeLocalVariablesFromParent(_cont);
                break;
            }
            en_.removeLocalVariablesFromParent(_cont);
            Block next_ = n_.getNextSibling();
            boolean exitByBreak_ = false;
            while (next_ == null) {
                Block par_ = n_.getParent();
                if (par_ == this) {
                    exitByBreak_ = true;
                    tryCheckConstCall(n_, _cont);
                    n_.removeLocalVariablesFromParent(_cont);
                    break;
                }
                n_.removeLocalVariablesFromParent(_cont);
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
    boolean canBeIncrementedNextGroup() {
        return false;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return false;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public NatTreeMap<String, String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

    @Override
    public boolean isStaticContext() {
        return true;
    }

    @Override
    public String getTagName() {
        return TAG_STATIC;
    }

    @Override
    public StringList getParametersNames() {
        return new StringList();
    }

    @Override
    public StringList getParametersTypes() {
        return new StringList();
    }

}
