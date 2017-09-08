package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.methods.exceptions.BadConstructorCall;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;

public final class SemiAffectation extends Leaf implements StackableBlock {

    private static final String RIGHT_EL = "1b";

    private final String leftMember;

    private CustList<OperationNode> opLeft;

    private CustList<OperationNode> incr;

    private final String oper;

    SemiAffectation(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        leftMember = _el.getAttribute(ATTRIBUTE_LEFT);
        oper = _el.getAttribute(ATTRIBUTE_OPER);
    }

    public String getLeftMember() {
        return leftMember;
    }

    public String getOper() {
        return oper;
    }

    @Override
    public NatTreeMap<String,String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }
    public ExpressionLanguage getLeftEl() {
        return new ExpressionLanguage(opLeft);
    }

    public ExpressionLanguage getRightEl() {
        return new ExpressionLanguage(incr);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(ATTRIBUTE_LEFT);
        page_.setOffset(0);
        opLeft = ElUtil.getAnalyzedOperations(leftMember, _cont, new Calculation(f_.isStaticContext(), false, true));
        OperationNode leftEl_ = opLeft.last();
        ClassArgumentMatching clMatchLeft_ = leftEl_.getResultClass();
        if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_)) {
            throw new DynamicCastClassException(_cont.joinPages());
        }
        if (!StringList.quickEq(oper, INCR)) {
            if (!StringList.quickEq(oper, DECR)) {
                page_.setProcessingAttribute(ATTRIBUTE_OPER);
                page_.setOffset(0);
                throw new DynamicCastClassException(_cont.joinPages());
            }
        }
        incr = ElUtil.getAnalyzedOperations(RIGHT_EL, _cont, Calculation.staticCalculation(true));
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }


    @Override
    public void checkCallConstructor(ContextEl _cont) {
        PageEl p_ = _cont.getLastPage();
        p_.setProcessingAttribute(ATTRIBUTE_LEFT);
        for (OperationNode o: opLeft) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                throw new BadConstructorCall(_cont.joinPages());
            }
        }
    }

    @Override
    public String getTagName() {
        return TAG_AFFECT;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        String op_ = getOper();
        ip_.setProcessingAttribute(ATTRIBUTE_LEFT);
        ip_.setOffset(0);
        ExpressionLanguage elLeft_ = ip_.getCurrentEl(this, CustList.FIRST_INDEX, getLeftEl());
        elLeft_.affectLeftMember(_cont, op_);
        ip_.setProcessingAttribute(ATTRIBUTE_RIGHT);
        ip_.setOffset(0);
        ExpressionLanguage el_ = getRightEl();
        ip_.addCurrentEl(el_);
        el_.affectRightMember(_cont, op_);
        ip_.setProcessingAttribute(ATTRIBUTE_LEFT);
        ip_.setOffset(0);
        elLeft_.affectAllMember(_cont, op_);
        el_.setCurrentOper(null);
        ip_.clearCurrentEls();
        processBlock(_cont);
    }
}
