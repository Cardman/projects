package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.exceptions.BadStaticException;
import code.sml.Element;
import code.util.NatTreeMap;

public final class InstanceBlock extends MemberCallingsBlock implements AloneBlock {

    public InstanceBlock(Element _el, ContextEl _importingPage, int _indexChild,
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
    public NatTreeMap<String, String> getClassNames(ContextEl _context) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

    @Override
    public boolean isStaticContext() {
        return false;
    }

    @Override
    public String getTagName() {
        return TAG_INSTANCE;
    }
}
