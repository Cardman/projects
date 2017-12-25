package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.exceptions.BadConstructorCall;
import code.expressionlanguage.methods.util.ExpLanguages;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.sml.Element;
import code.util.CustList;
import code.util.NatTreeMap;

public final class Affectation extends Leaf implements StackableBlock {

    private final String leftMember;

    private final String oper;

    private final String rightMember;

    private CustList<OperationNode> opLeft;

    private CustList<OperationNode> opRight;

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
    public NatTreeMap<String,String> getClassNames(ContextEl _context) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

    public ExpressionLanguage getLeftEl() {
        return new ExpressionLanguage(opLeft);
    }

    public ExpressionLanguage getRightEl() {
        return new ExpressionLanguage(opRight);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        boolean static_ = f_.isStaticContext();
        ExpLanguages e_ = ElUtil.analyzeAffect(ATTRIBUTE_OPER, ATTRIBUTE_LEFT, ATTRIBUTE_RIGHT, leftMember, rightMember, oper, _cont, static_, static_);
        ExpressionLanguage leftEl_ = e_.getLeft();
        opLeft = leftEl_.getOperations();
        ExpressionLanguage rightEl_ = e_.getRight();
        opRight = rightEl_.getOperations();
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
        ip_.setOffset(0);
        String op_ = getOper();
        ExpressionLanguage elLeft_ = ip_.getCurrentEl(this, CustList.FIRST_INDEX, getLeftEl());
        elLeft_.affectLeftMember(_cont, op_);
        ip_.setProcessingAttribute(ATTRIBUTE_RIGHT);
        ip_.setOffset(0);
        ExpressionLanguage el_ = ip_.getCurrentEl(this, CustList.SECOND_INDEX, getRightEl());
        el_.affectRightMember(_cont, op_);
        ip_.setProcessingAttribute(ATTRIBUTE_LEFT);
        ip_.setOffset(0);
        elLeft_.affectAllMember(_cont, op_);
        el_.setCurrentOper(null);
        ip_.clearCurrentEls();
        processBlock(_cont);
    }
}
