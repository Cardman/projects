package code.expressionlanguage.methods;
import org.w3c.dom.Element;

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
import code.util.CustList;
import code.util.NatTreeMap;

public final class SwitchBlock extends BracedStack implements BreakableBlock {

    private final String value;

    private CustList<OperationNode> opValue;
//    private ExpressionLanguage el;

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
//        return new ExpressionLanguage(value, _cont, true, new Calculation(StepCalculation.RIGHT));
        return new ExpressionLanguage(opValue);
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
        Block first_ = getFirstChild();
        while (first_ != null) {
            Block elt_ = first_;
            if (elt_ instanceof CaseCondition) {
//                exist_ = true;
                first_ = first_.getNextSibling();
                continue;
            }
            if (elt_ instanceof DefaultCondition) {
//                exist_ = true;
                first_ = first_.getNextSibling();
                continue;
            }
            PageEl page_ = _cont.getLastPage();
//            page_.setProcessingNode(getAssociateElement());
//            page_.setLookForAttrValue(true);
            page_.setProcessingAttribute(EMPTY_STRING);
            page_.setOffset(0);
//            page_.setLookForAttrValue(false);
            throw new BadSwitchException(_cont.joinPages());
        }
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
//        Block first_ = getFirstChild();
//        boolean exist_ = false;
        FunctionBlock f_ = getFunction();
        PageEl page_ = _cont.getLastPage();
//        page_.setProcessingNode(getAssociateElement());
        page_.setProcessingAttribute(ATTRIBUTE_VALUE);
        page_.setOffset(0);
//        page_.setLookForAttrValue(true);
//        opValue = ElUtil.getAnalyzedOperations(value, _cont, f_.isStaticContext());
        opValue = ElUtil.getAnalyzedOperations(value, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        if (opValue.last().isVoidArg()) {
            throw new VoidArgumentException(_cont.joinPages());
        }
//        new ExpressionLanguage(value, _cont, true, new Calculation(StepCalculation.RIGHT));
//        while (first_ != null) {
//            Block elt_ = first_;
//            if (elt_ instanceof CaseCondition) {
////                exist_ = true;
//                first_ = first_.getNextSibling();
//                continue;
//            }
//            if (elt_ instanceof DefaultCondition) {
////                exist_ = true;
//                first_ = first_.getNextSibling();
//                continue;
//            }
//            page_.setProcessingAttribute(EMPTY_STRING);
//            page_.setOffset(0);
//            page_.setLookForAttrValue(false);
//            throw new BadSwitchException(_cont.joinPages());
//        }
//        removeLocalVariablesFromParent();
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
//        return false;
        return ch_.last().canBeLastOfBlockGroup();
    }


    @Override
    public void checkCallConstructor(ContextEl _cont) {
        PageEl p_ = _cont.getLastPage();
//        p_.setProcessingNode(getAssociateElement());
        p_.setProcessingAttribute(ATTRIBUTE_VALUE);
//        p_.setLookForAttrValue(true);
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
//            BlockStack bl_ = l_.last();
            RemovableVars bl_ = ip_.getLastStack();
            if (bl_.getBlock() == this) {
//                l_.removeLast();
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
//        ExpressionLanguage el_ = ((SwitchBlock)en_).getEl(_conf);
        ExpressionLanguage el_;
        if (!ip_.getCurrentEls().isEmpty()) {
            el_ = ip_.getCurrentEls().last();
        } else {
            el_ = getEl();
            ip_.setCurrentBlock(this);
            ip_.setCurrentEls(new CustList<ExpressionLanguage>(el_));
        }
        ip_.setProcessingAttribute(ATTRIBUTE_VALUE);
        ip_.setOffset(0);
//        ip_.setLookForAttrValue(true);
        Argument arg_ =  el_.calculateMember(_cont);
        el_.setCurrentOper(null);
        ip_.getCurrentEls().clear();
//        if_.setValue(arg_.getObject());
        if_.setStruct(arg_.getStruct());
        if (if_.getBlocks().isEmpty()) {
            if_.setFinished(true);
            ip_.addBlock(if_);
//            l_.add(if_);
            return;
        }
        rw_.setBlock(getFirstChild());
        ip_.addBlock(if_);
    }
}
