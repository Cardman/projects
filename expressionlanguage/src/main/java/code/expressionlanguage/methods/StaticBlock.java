package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.exceptions.BadStaticException;
import code.util.NatTreeMap;

public final class StaticBlock extends MemberCallingsBlock implements AloneBlock {

    public StaticBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    @Override
    public void checkFctBlocksTree(ContextEl _cont) {
        if (getFirstChild() == null) {
            PageEl page_ = _cont.getLastPage();
            page_.setProcessingAttribute(EMPTY_STRING);
            page_.setOffset(0);
            throw new BadStaticException(_cont.joinPages());
        }
        super.checkFctBlocksTree(_cont);
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
}
