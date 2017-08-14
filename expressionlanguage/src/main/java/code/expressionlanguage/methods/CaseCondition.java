package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.ReadWrite;
import code.expressionlanguage.exceptions.VoidArgumentException;
import code.expressionlanguage.methods.exceptions.BadCaseException;
import code.expressionlanguage.methods.exceptions.BadConstructorCall;
import code.expressionlanguage.methods.util.EqualsEl;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stacks.SwitchBlockStack;
import code.expressionlanguage.variables.LocalVariable;
import code.util.CustList;
import code.util.NatTreeMap;

public final class CaseCondition extends BracedStack implements StackableBlockGroup, IncrCurrentGroup, IncrNextGroup {

    private final String value;
    private CustList<OperationNode> opValue;
    private boolean possibleSkipNexts;

//    private ExpressionLanguage valueEl;
    public CaseCondition(Element _el, ContextEl _importingPage,
            int _indexChild, BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        value = _el.getAttribute(ATTRIBUTE_VALUE);
    }

    public String getValue() {
        return value;
    }

    @Override
    public NatTreeMap<String,String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

    public ExpressionLanguage getValueEl() {
//        return new ExpressionLanguage(value, _cont, true, new Calculation(StepCalculation.RIGHT));
        return new ExpressionLanguage(opValue);
    }

    @Override
    boolean isAlwaysExitable() {
        return getFirstChild() == null || !isPossibleSkipNexts();
    }

    @Override
    public void checkBlocksTree(ContextEl _cont) {
        PageEl page_ = _cont.getLastPage();
//        page_.setProcessingNode(getAssociateElement());
        BracedBlock b_ = getParent();
        if (!(b_ instanceof SwitchBlock)) {
            page_.setProcessingAttribute(EMPTY_STRING);
//            page_.setLookForAttrValue(false);
            page_.setOffset(0);
            throw new BadCaseException(_cont.joinPages());
        }
    }

    boolean isPossibleSkipNexts() {
        return possibleSkipNexts;
    }

    void setPossibleSkipNexts(boolean _possibleSkipNexts) {
        possibleSkipNexts = _possibleSkipNexts;
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        PageEl page_ = _cont.getLastPage();
//        page_.setProcessingNode(getAssociateElement());
//        BracedBlock b_ = getParent();
//        if (!(b_ instanceof SwitchBlock)) {
//            page_.setProcessingAttribute(EMPTY_STRING);
//            page_.setLookForAttrValue(false);
//            page_.setOffset(0);
//            throw new BadCaseException(_cont.joinPages());
//        }
        page_.setProcessingAttribute(ATTRIBUTE_VALUE);
//        page_.setLookForAttrValue(true);
        page_.setOffset(0);
//        opValue = ElUtil.getAnalyzedOperations(value, _cont, f_.isStaticContext());
        opValue = ElUtil.getAnalyzedOperations(value, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        if (opValue.last().isVoidArg()) {
            throw new VoidArgumentException(_cont.joinPages());
        }
//        valueEl = new ExpressionLanguage(value, _cont, true, new Calculation(StepCalculation.RIGHT));
//        removeLocalVariablesFromParent();
    }

    @Override
    boolean canBeIncrementedNextGroup() {
        return true;
    }

    @Override
    boolean canBeIncrementedCurGroup() {
        return true;
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
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
        return TAG_CASE;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        SwitchBlockStack sw_ = (SwitchBlockStack) ip_.getLastStack();
        Struct str_ = sw_.getStruct();
        Argument virtualArg_ = new Argument();
        virtualArg_.setStruct(str_);
        sw_.setVisitedBlock(getIndexInGroup());
        if (sw_.isEntered()) {
            if (!hasChildNodes()) {
                if (sw_.lastVisitedBlock() == this) {
                    sw_.setFinished(true);
                    rw_.setBlock(sw_.getBlock());
                    return;
                }
                rw_.setBlock(getNextSibling());
                return;
            }
            rw_.setBlock(getFirstChild());
            return;
        } else {
            ip_.setProcessingAttribute(ATTRIBUTE_VALUE);
            ip_.setOffset(0);
            ExpressionLanguage el_;
            if (!ip_.getCurrentEls().isEmpty()) {
                el_ = ip_.getCurrentEls().last();
            } else {
                el_ = getValueEl();
                ip_.setCurrentBlock(this);
                ip_.setCurrentEls(new CustList<ExpressionLanguage>(el_));
            }
//            ExpressionLanguage el_ = ((CaseCondition)en_).getValueEl(_conf);
            Argument arg_ = el_.calculateMember(_cont);
            el_.setCurrentOper(null);
            ip_.getCurrentEls().clear();
            boolean enter_ = false;
            if (str_.isNull()) {
                if (arg_.getObject() == null) {
                    enter_ = true;
                }
            } else {
                Classes cl_ = _cont.getClasses();
                EqualsEl eq_ = cl_.getNatEqEl();
                String loc_ = eq_.getFirstArg();
                LocalVariable local_ = new LocalVariable();
                local_.setStruct(str_);
                local_.setClassName(str_.getClassName());
                ip_.getLocalVars().put(loc_, local_);
                String locSec_ = eq_.getSecondArg();
                local_ = new LocalVariable();
                local_.setStruct(arg_.getStruct());
                local_.setClassName(arg_.getStruct().getClassName());
                ip_.getLocalVars().put(locSec_, local_);
                Argument eqArg_ = cl_.getEqNatEl().calculateMember(_cont);
                boolean b_ = (Boolean) eqArg_.getObject();
                ip_.getLocalVars().removeKey(loc_);
                ip_.getLocalVars().removeKey(locSec_);
                if (b_) {
                    enter_ = true;
                }
            }
            if (enter_) {
                if (hasChildNodes()) {
                    sw_.setEntered(true);
                } else {
                    if (sw_.lastVisitedBlock() != this) {
                        sw_.setEntered(true);
                        rw_.setBlock(getNextSibling());
                        return;
                    } else {
                        sw_.setFinished(true);
                        rw_.setBlock(sw_.getBlock());
                        return;
                    }
                }
                rw_.setBlock(getFirstChild());
                return;
            }
            if (sw_.lastVisitedBlock() == this) {
                sw_.setFinished(true);
                rw_.setBlock(sw_.getBlock());
                return;
            }
            rw_.setBlock(getNextSibling());
            return;
        }
    }

    @Override
    public void exitStack(ContextEl _context) {
        PageEl ip_ = _context.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        SwitchBlockStack if_ = (SwitchBlockStack) ip_.getLastStack();
        if (if_.lastVisitedBlock() == this) {
            if_.setFinished(true);
            rw_.setBlock(if_.getBlock());
        } else {
//            if_.increment();
            rw_.setBlock(getNextSibling());
        }
    }
}
