package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.util.BadConstructorCall;
import code.expressionlanguage.methods.util.ExpLanguages;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.sml.Element;
import code.util.CustList;
import code.util.NatTreeMap;

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

    @Override
    public NatTreeMap<String,String> getClassNames(ContextEl _context) {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

    @Override
    public NatTreeMap<Integer,String> getClassNamesOffsets(ContextEl _context) {
        NatTreeMap<Integer,String> tr_ = new NatTreeMap<Integer,String>();
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
        ExpLanguages e_ = ElUtil.getAnalyzedAffectation(operOffset, leftMemberOffset, rightMemberOffset, leftMember, rightMember, oper, _cont, static_, static_);
        opLeft = e_.getLeft();
        opRight = e_.getRight();
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }

    @Override
    public void checkCallConstructor(ContextEl _cont) {
        PageEl p_ = _cont.getLastPage();
        p_.setGlobalOffset(leftMemberOffset);
        for (OperationNode o: opLeft) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                BadConstructorCall call_ = new BadConstructorCall();
                call_.setFileName(getFile().getFileName());
                call_.setRc(getRowCol(0, leftMemberOffset));
                call_.setLocalOffset(getRowCol(o.getFullIndexInEl(), leftMemberOffset));
                _cont.getClasses().getErrorsDet().add(call_);
            }
        }
        p_.setGlobalOffset(rightMemberOffset);
        for (OperationNode o: opRight) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                BadConstructorCall call_ = new BadConstructorCall();
                call_.setFileName(getFile().getFileName());
                call_.setRc(getRowCol(0, rightMemberOffset));
                call_.setLocalOffset(getRowCol(o.getFullIndexInEl(), rightMemberOffset));
                _cont.getClasses().getErrorsDet().add(call_);
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
        ip_.setGlobalOffset(leftMemberOffset);
        ip_.setOffset(0);
        String op_ = getOper();
        ExpressionLanguage elLeft_ = ip_.getCurrentEl(this, CustList.FIRST_INDEX, getLeftEl());
        elLeft_.affectLeftMember(_cont, op_);
        if (_cont.callsOrException()) {
            return;
        }
        ip_.setGlobalOffset(rightMemberOffset);
        ip_.setOffset(0);
        ExpressionLanguage el_ = ip_.getCurrentEl(this, CustList.SECOND_INDEX, getRightEl());
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
}
