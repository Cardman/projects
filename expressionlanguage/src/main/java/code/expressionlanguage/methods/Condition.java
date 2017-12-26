package code.expressionlanguage.methods;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.methods.exceptions.BadConditionExpressionException;
import code.expressionlanguage.methods.exceptions.BadConstructorCall;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.stds.LgNames;
import code.sml.Element;
import code.util.CustList;

public abstract class Condition extends BracedStack implements StackableBlockGroup {

    private String condition;

    private CustList<OperationNode> opCondition;

    public Condition(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        condition = _el.getAttribute(ATTRIBUTE_CONDITION);
    }

    public Condition(ContextEl _importingPage, int _indexChild,
            BracedBlock _m, String _condition) {
        super(_importingPage, _indexChild, _m);
        condition = _condition;
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(ATTRIBUTE_CONDITION);
        page_.setOffset(0);
        opCondition = ElUtil.getAnalyzedOperations(condition, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        OperationNode elCondition_ = opCondition.last();
        LgNames stds_ = _cont.getStandards();
        if (!elCondition_.getResultClass().matchClass(stds_.getAliasPrimBoolean())) {
            if (!elCondition_.getResultClass().matchClass(stds_.getAliasBoolean())) {
                throw new BadConditionExpressionException(_cont.joinPages());
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
        p_.setProcessingAttribute(ATTRIBUTE_CONDITION);
        for (OperationNode o: opCondition) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                throw new BadConstructorCall(_cont.joinPages());
            }
        }
    }
    final boolean evaluateCondition(ContextEl _context) {
        PageEl last_ = _context.getLastPage();
        ExpressionLanguage exp_ = last_.getCurrentEl(this, CustList.FIRST_INDEX, getElCondition());
        last_.setOffset(0);
        last_.setProcessingAttribute(ATTRIBUTE_CONDITION);
        Argument arg_ = exp_.calculateMember(_context);
        exp_.setCurrentOper(null);
        last_.clearCurrentEls();
        return (Boolean) arg_.getObject();
    }
}
