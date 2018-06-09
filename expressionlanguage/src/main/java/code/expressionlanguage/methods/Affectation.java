package code.expressionlanguage.methods;
import code.expressionlanguage.AbstractPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.methods.util.ExpLanguages;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.sml.Element;
import code.util.CustList;

public final class Affectation extends Leaf implements StackableBlock {

    private final String leftMember;

    private int leftMemberOffset;

    private final String oper;

    private int operOffset;

    private final String rightMember;

    private int rightMemberOffset;

    private CustList<OperationNode> opLeft;

    private CustList<OperationNode> opRight;

    Affectation(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        leftMember = _el.getAttribute(ATTRIBUTE_LEFT);
        oper = _el.getAttribute(ATTRIBUTE_OPER);
        rightMember = _el.getAttribute(ATTRIBUTE_RIGHT);
    }

    public Affectation(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetStringInfo _left, OffsetStringInfo _op, OffsetStringInfo _right, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        leftMember = _left.getInfo();
        leftMemberOffset = _left.getOffset();
        oper = _op.getInfo();
        operOffset = _op.getOffset();
        rightMember = _right.getInfo();
        rightMemberOffset = _right.getOffset();
    }

    public int getLeftMemberOffset() {
        return leftMemberOffset;
    }

    public int getOperOffset() {
        return operOffset;
    }

    public int getRightMemberOffset() {
        return rightMemberOffset;
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
        ExpLanguages e_ = ElUtil.getAnalyzedAffectation(operOffset, leftMemberOffset, rightMemberOffset, leftMember, rightMember, oper, _cont, static_, static_);
        opLeft = e_.getLeft();
        opRight = e_.getRight();
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public String getTagName() {
        return TAG_AFFECT;
    }

    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ip_.setGlobalOffset(leftMemberOffset);
        ip_.setOffset(0);
        String op_ = getOper();
        ExpressionLanguage elLeft_ = ip_.getCurrentEl(_cont, this, CustList.FIRST_INDEX, false, CustList.FIRST_INDEX);
        elLeft_.affectLeftMember(_cont, op_);
        if (_cont.callsOrException()) {
            return;
        }
        ip_.setGlobalOffset(rightMemberOffset);
        ip_.setOffset(0);
        ExpressionLanguage el_ = ip_.getCurrentEl(_cont, this, CustList.SECOND_INDEX, false, CustList.SECOND_INDEX);
        el_.affectRightMember(_cont, op_);
        if (_cont.callsOrException()) {
            return;
        }
        ip_.setGlobalOffset(leftMemberOffset);
        ip_.setOffset(0);
        elLeft_.affectAllMember(_cont, op_);
        if (_cont.callsOrException()) {
            return;
        }
        el_.setCurrentOper(null);
        ip_.clearCurrentEls();
        processBlock(_cont);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return null;
    }
}
