package code.expressionlanguage.methods;
import org.w3c.dom.Element;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.DynamicCastClassException;
import code.expressionlanguage.exceptions.PrimitiveTypeException;
import code.expressionlanguage.methods.exceptions.BadConstructorCall;
import code.expressionlanguage.methods.util.ExpLanguages;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;

public final class Affectation extends Leaf implements StackableBlock {

    private final String leftMember;

    private final String oper;

    private final String rightMember;

    private CustList<OperationNode> opLeft;

    private CustList<OperationNode> opRight;

//    private ExpressionLanguage rightEl;

    Affectation(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        leftMember = _el.getAttribute(ATTRIBUTE_LEFT);
        oper = _el.getAttribute(ATTRIBUTE_OPER);
        rightMember = _el.getAttribute(ATTRIBUTE_RIGHT);
    }

    public String getLeftMember() {
        return leftMember;
    }

    public String getOper() {
        return oper;
    }

    public String getRightMember() {
        return rightMember;
    }

    @Override
    public NatTreeMap<String,String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

//    public ExpLanguages getEls(ContextEl _cont) {
//        return ElUtil.analyzeAffect(leftMember, rightMember, oper, _cont, true);
//    }

    public ExpressionLanguage getLeftEl() {
//        ExpLanguages e_ = ElUtil.analyzeAffect(leftMember, rightMember, oper, _cont, true);
//        return e_.getLeft();
        return new ExpressionLanguage(opLeft);
    }

    public ExpressionLanguage getRightEl() {
//        ExpLanguages e_ = ElUtil.analyzeAffect(leftMember, rightMember, oper, _cont, true);
//        return e_.getRight();
        return new ExpressionLanguage(opRight);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        ExpLanguages e_ = ElUtil.analyzeAffect(leftMember, rightMember, oper, _cont, f_.isStaticContext());
        ExpressionLanguage leftEl_ = e_.getLeft();
        opLeft = leftEl_.getOperations();
        ClassArgumentMatching clMatchLeft_ = leftEl_.getClassArgumentMatching();
        ExpressionLanguage rightEl_ = e_.getRight();
        opRight = rightEl_.getOperations();
        ClassArgumentMatching clMatchRight_ = rightEl_.getClassArgumentMatching();
        PageEl page_ = _cont.getLastPage();
//        page_.setLookForAttrValue(true);
        page_.setOffset(0);
        page_.setProcessingAttribute(ATTRIBUTE_OPER);
        if (oper.length() == 2) {
            if (StringList.quickEq(oper, EQ_PLUS) || StringList.quickEq(oper, PLUS_EQ)) {
                if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_)) {
                    if (!clMatchLeft_.matchClass(String.class)) {
                        throw new DynamicCastClassException(_cont.joinPages());
                    }
                } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchRight_)) {
                    throw new DynamicCastClassException(_cont.joinPages());
                }
            } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_)) {
                throw new DynamicCastClassException(_cont.joinPages());
            } else if (!PrimitiveTypeUtil.isPureNumberClass(clMatchRight_)) {
                throw new DynamicCastClassException(_cont.joinPages());
            }
        } else {
            if (clMatchRight_.isVariable()) {
                if (!clMatchLeft_.isPrimitive()) {
                    return;
                }
                throw new PrimitiveTypeException(_cont.joinPages());
            }
            if (!PrimitiveTypeUtil.canBeUseAsArgument(clMatchLeft_.getName(), clMatchRight_.getName(), _cont.getClasses())) {
                throw new DynamicCastClassException(_cont.joinPages());
            }
        }
//        removeLocalVariablesFromParent();
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public void checkCallConstructor(ContextEl _cont) {
        PageEl p_ = _cont.getLastPage();
//        p_.setProcessingNode(getAssociateElement());
        p_.setProcessingAttribute(ATTRIBUTE_LEFT);
//        p_.setLookForAttrValue(true);
        for (OperationNode o: opLeft) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                throw new BadConstructorCall(_cont.joinPages());
            }
        }
        p_.setProcessingAttribute(ATTRIBUTE_RIGHT);
        for (OperationNode o: opRight) {
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
        ip_.setProcessingAttribute(ATTRIBUTE_LEFT);
//        ip_.setLookForAttrValue(true);
        ip_.setOffset(0);
        String op_ = getOper();
//        ExpLanguages e_ = a_.getEls(_conf);
        ExpressionLanguage el_;
        if (!ip_.getCurrentEls().isEmpty()) {
            el_ = ip_.getCurrentEls().first();
        } else {
            el_ = getLeftEl();
            ip_.setCurrentBlock(this);
            ip_.setCurrentEls(new CustList<ExpressionLanguage>(el_));
        }
        el_.affectLeftMember(_cont, op_);
        ip_.setProcessingAttribute(ATTRIBUTE_RIGHT);
//        ip_.setLookForAttrValue(true);
        ip_.setOffset(0);
        if (ip_.getCurrentEls().size() > 1) {
            el_ = ip_.getCurrentEls().last();
        } else {
            el_ = getRightEl();
            ip_.getCurrentEls().add(el_);
        }
        el_.affectRightMember(_cont, op_);
//        System.out.println(ip_.getRightArgument().getStruct().getInstance());
        ip_.setProcessingAttribute(ATTRIBUTE_LEFT);
//        ip_.setLookForAttrValue(true);
        ip_.setOffset(0);
        ip_.getCurrentEls().first().affectAllMember(_cont, op_);
//        ExpressionLanguage el_ = e_.getLeft();
//        ip_.setCurrentEl(el_);
//        el_.affectMember(_conf, e_.getRight(), op_);
        el_.setCurrentOper(null);
        ip_.getCurrentEls().clear();
//        System.out.println("AFFECT");
        processBlock(_cont);
    }
}
