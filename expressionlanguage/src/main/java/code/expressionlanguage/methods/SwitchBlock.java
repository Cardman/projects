package code.expressionlanguage.methods;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.exceptions.VoidArgumentException;
import code.expressionlanguage.methods.exceptions.BadConstructorCall;
import code.expressionlanguage.methods.exceptions.BadSwitchException;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.SwitchBlockStack;
import code.sml.Element;
import code.util.CustList;
import code.util.NatTreeMap;

public final class SwitchBlock extends BracedStack implements BreakableBlock {

    private final String value;

    private CustList<OperationNode> opValue;

    public SwitchBlock(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        value = _el.getAttribute(ATTRIBUTE_VALUE);
    }

    @Override
    public NatTreeMap<String,String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

    public String getValue() {
        return value;
    }

    public ExpressionLanguage getEl() {
        return new ExpressionLanguage(opValue);
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
        Block first_ = getFirstChild();
        while (first_ != null) {
            Block elt_ = first_;
            if (elt_ instanceof CaseCondition) {
                first_ = first_.getNextSibling();
                continue;
            }
            if (elt_ instanceof DefaultCondition) {
                first_ = first_.getNextSibling();
                continue;
            }
            PageEl page_ = _cont.getLastPage();
            page_.setProcessingAttribute(EMPTY_STRING);
            page_.setOffset(0);
            throw new BadSwitchException(_cont.joinPages());
        }
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(ATTRIBUTE_VALUE);
        page_.setOffset(0);
        opValue = ElUtil.getAnalyzedOperations(value, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        if (opValue.last().isVoidArg()) {
            throw new VoidArgumentException(_cont.joinPages());
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
        CustList<Block> ch_ = Classes.getDirectChildren(this);
        if (ch_.isEmpty()) {
            return false;
        }
        return ch_.last().canBeLastOfBlockGroup();
    }


    @Override
    public void checkCallConstructor(ContextEl _cont) {
        PageEl p_ = _cont.getLastPage();
        p_.setProcessingAttribute(ATTRIBUTE_VALUE);
        for (OperationNode o: opValue) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                throw new BadConstructorCall(_cont.joinPages());
            }
        }
    }

    @Override
    public String getTagName() {
        return TAG_SWITCH;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        if (!ip_.noBlock()) {
            RemovableVars bl_ = ip_.getLastStack();
            if (bl_.getBlock() == this) {
                ip_.removeLastBlock();
                processBlock(_cont);
                return;
            }
        }
        SwitchBlockStack if_ = new SwitchBlockStack();
        Block n_ = getFirstChild();
        while (n_ != null) {
            if_.getBlocks().add((BracedBlock)n_);
            n_ = n_.getNextSibling();
        }
        if_.setBlock(this);
        ExpressionLanguage el_ = ip_.getCurrentEl(this, CustList.FIRST_INDEX, getEl());
        ip_.setProcessingAttribute(ATTRIBUTE_VALUE);
        ip_.setOffset(0);
        Argument arg_ =  el_.calculateMember(_cont);
        el_.setCurrentOper(null);
        ip_.clearCurrentEls();
        if_.setStruct(arg_.getStruct());
        if (if_.getBlocks().isEmpty()) {
            if_.setFinished(true);
            ip_.addBlock(if_);
            return;
        }
        rw_.setBlock(getFirstChild());
        ip_.addBlock(if_);
    }
}
