package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.methods.exceptions.BadDefaultException;
import code.expressionlanguage.stacks.SwitchBlockStack;
import code.sml.Element;
import code.util.NatTreeMap;

public final class DefaultCondition extends BracedStack implements StackableBlockGroup, IncrNextGroup {

    public DefaultCondition(Element _el, ContextEl _importingPage,
            int _indexChild, BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
    }

    public DefaultCondition(ContextEl _importingPage,
            int _indexChild, BracedBlock _m) {
        super(_importingPage, _indexChild, _m);
    }

    @Override
    public NatTreeMap<String,String> getClassNames(ContextEl _context) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
        BracedBlock b_ = getParent();
        if (!(b_ instanceof SwitchBlock) || getNextSibling() != null) {
            PageEl page_ = _cont.getLastPage();
            page_.setProcessingAttribute(EMPTY_STRING);
            page_.setOffset(0);
            throw new BadDefaultException(_cont.joinPages());
        }
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return true;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return false;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return true;
    }

    @Override
    public void checkCallConstructor(ContextEl _cont) {
    }

    @Override
    public String getTagName() {
        return TAG_DEFAULT;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        SwitchBlockStack sw_ = (SwitchBlockStack) ip_.getLastStack();
        sw_.setVisitedBlock(getIndexInGroup());
        if (sw_.isEntered()) {
            if (!hasChildNodes()) {
                sw_.setFinished(true);
                rw_.setBlock(sw_.getBlock());
                return;
            }
            rw_.setBlock(getFirstChild());
            return;
        } else {
            if (hasChildNodes()) {
                sw_.setEntered(true);
            } else {
                sw_.setFinished(true);
                rw_.setBlock(sw_.getBlock());
                return;
            }
            rw_.setBlock(getFirstChild());
            return;
        }
    }

    @Override
    public void exitStack(ContextEl _context) {
        PageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        SwitchBlockStack if_ = (SwitchBlockStack) ip_.getLastStack();
        if_.setFinished(true);
        rw_.setBlock(if_.getBlock());
    }
}
