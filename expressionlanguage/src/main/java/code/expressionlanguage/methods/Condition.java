package code.expressionlanguage.methods;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.util.BadConstructorCall;
import code.expressionlanguage.methods.util.UnexpectedTypeError;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.stds.LgNames;
import code.sml.Element;
import code.util.CustList;

public abstract class Condition extends BracedStack implements StackableBlockGroup {

    private String condition;

    private int conditionOffset;

    private CustList<OperationNode> opCondition;

    public Condition(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        condition = _el.getAttribute(ATTRIBUTE_CONDITION);
    }

    public Condition(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, OffsetStringInfo _condition, OffsetsBlock _offset) {
        super(_importingPage, _indexChild, _m, _offset);
        condition = _condition.getInfo();
        conditionOffset = _condition.getOffset();
    }

    public int getConditionOffset() {
        return conditionOffset;
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        PageEl page_ = _cont.getLastPage();
        page_.setGlobalOffset(conditionOffset);
        page_.setOffset(0);
        opCondition = ElUtil.getAnalyzedOperations(condition, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        OperationNode elCondition_ = opCondition.last();
        LgNames stds_ = _cont.getStandards();
        if (!elCondition_.getResultClass().matchClass(stds_.getAliasPrimBoolean())) {
            if (!elCondition_.getResultClass().matchClass(stds_.getAliasBoolean())) {
                UnexpectedTypeError un_ = new UnexpectedTypeError();
                un_.setFileName(getFile().getFileName());
                un_.setRc(getRowCol(0, conditionOffset));
                un_.setType(opCondition.last().getResultClass().getName());
                _cont.getClasses().getErrorsDet().add(un_);
            }
        }
    }

    public final ExpressionLanguage getElCondition() {
        return new ExpressionLanguage(opCondition);
    }

    public final String getCondition() {
        return condition;
    }

    @Override
    public final void checkCallConstructor(ContextEl _cont) {
        PageEl p_ = _cont.getLastPage();
        p_.setGlobalOffset(conditionOffset);
        for (OperationNode o: opCondition) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                BadConstructorCall call_ = new BadConstructorCall();
                call_.setFileName(getFile().getFileName());
                call_.setRc(getRowCol(0, conditionOffset));
                call_.setLocalOffset(getRowCol(o.getFullIndexInEl(), conditionOffset));
                _cont.getClasses().getErrorsDet().add(call_);
            }
        }
    }
    final boolean evaluateCondition(ContextEl _context) {
        PageEl last_ = _context.getLastPage();
        ExpressionLanguage exp_ = last_.getCurrentEl(this, CustList.FIRST_INDEX, getElCondition());
        last_.setOffset(0);
        last_.setGlobalOffset(conditionOffset);
        Argument arg_ = exp_.calculateMember(_context);
        if (_context.callsOrException()) {
            return false;
        }
        exp_.setCurrentOper(null);
        last_.clearCurrentEls();
        return (Boolean) arg_.getObject();
    }
}
