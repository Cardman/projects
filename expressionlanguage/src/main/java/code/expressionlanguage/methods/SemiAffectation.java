package code.expressionlanguage.methods;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.Mapping;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.methods.util.BadConstructorCall;
import code.expressionlanguage.methods.util.BadImplicitCast;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.sml.Element;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;

public final class SemiAffectation extends Leaf implements StackableBlock {

    private static final String RIGHT_EL = "1b";
    
    private final String leftMember;

    private int leftMemberOffset;

    private CustList<OperationNode> opLeft;

    private CustList<OperationNode> incr;

    private final String oper;

    private int operOffset;

    SemiAffectation(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        leftMember = _el.getAttribute(ATTRIBUTE_LEFT);
        oper = _el.getAttribute(ATTRIBUTE_OPER);
    }

    public SemiAffectation(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetStringInfo _left, OffsetStringInfo _oper, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        leftMember = _left.getInfo();
        leftMemberOffset = _left.getOffset();
        oper = _oper.getInfo();
        operOffset = _oper.getOffset();
    }

    public int getLeftMemberOffset() {
        return leftMemberOffset;
    }

    public int getOperOffset() {
        return operOffset;
    }

    public String getLeftMember() {
        return leftMember;
    }

    public String getOper() {
        return oper;
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
        return new ExpressionLanguage(incr);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        PageEl page_ = _cont.getLastPage();
        page_.setGlobalOffset(leftMemberOffset);
        page_.setOffset(0);
        opLeft = ElUtil.getAnalyzedOperations(leftMember, _cont, new Calculation(false, f_.isStaticContext(), EMPTY_STRING, true));
        OperationNode leftEl_ = opLeft.last();
        ClassArgumentMatching clMatchLeft_ = leftEl_.getResultClass();
        if (!PrimitiveTypeUtil.isPureNumberClass(clMatchLeft_, _cont)) {
            Mapping mapping_ = new Mapping();
            mapping_.setArg(clMatchLeft_.getName());
            mapping_.setParam(_cont.getStandards().getAliasLong());
            BadImplicitCast cast_ = new BadImplicitCast();
            cast_.setMapping(mapping_);
            cast_.setFileName(getFile().getFileName());
            cast_.setRc(getRowCol(0, leftMemberOffset));
            _cont.getClasses().getErrorsDet().add(cast_);
        }
        if (!StringList.quickEq(oper, INCR)) {
            if (!StringList.quickEq(oper, DECR)) {
                page_.setGlobalOffset(operOffset);
                page_.setOffset(0);
                Mapping mapping_ = new Mapping();
                mapping_.setArg(clMatchLeft_.getName());
                mapping_.setParam(_cont.getStandards().getAliasLong());
                BadImplicitCast cast_ = new BadImplicitCast();
                cast_.setMapping(mapping_);
                cast_.setFileName(getFile().getFileName());
                cast_.setRc(getRowCol(0, operOffset));
                _cont.getClasses().getErrorsDet().add(cast_);
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
    }

    @Override
    public String getTagName() {
        return TAG_AFFECT;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        String op_ = getOper();
        ip_.setGlobalOffset(leftMemberOffset);
        ip_.setOffset(0);
        ExpressionLanguage elLeft_ = ip_.getCurrentEl(this, CustList.FIRST_INDEX, getLeftEl());
        elLeft_.affectLeftMember(_cont, op_);
        ip_.setGlobalOffset(operOffset);
        ip_.setOffset(0);
        ExpressionLanguage el_ = getRightEl();
        ip_.addCurrentEl(el_);
        el_.affectRightMember(_cont, op_);
        ip_.setGlobalOffset(leftMemberOffset);
        ip_.setOffset(0);
        elLeft_.affectAllMember(_cont, op_);
        el_.setCurrentOper(null);
        ip_.clearCurrentEls();
        processBlock(_cont);
    }
}
