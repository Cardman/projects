package code.expressionlanguage.methods;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.PageEl;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.exceptions.InvokeException;
import code.expressionlanguage.methods.exceptions.BadCatchException;
import code.expressionlanguage.methods.exceptions.BadConstructorCall;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.OperationNode;
import code.expressionlanguage.opers.util.Struct;
import code.sml.Element;
import code.util.CustList;
import code.util.NatTreeMap;

public final class Throwing extends Leaf implements StackableBlock {

    private final String expression;

    private CustList<OperationNode> opThrow;

    public Throwing(Element _el, ContextEl _importingPage, int _indexChild,
            BracedBlock _m) {
        super(_el, _importingPage, _indexChild, _m);
        expression = _el.getAttribute(ATTRIBUTE_EXPRESSION);
        setExitable(true);
        setStoppable(true);
    }

    @Override
    public NatTreeMap<String,String> getClassNames() {
        NatTreeMap<String,String> tr_ = new NatTreeMap<String,String>();
        return tr_;
    }

    public String getExpression() {
        return expression;
    }

    public ExpressionLanguage getEl() {
        return new ExpressionLanguage(opThrow);
    }

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        PageEl page_ = _cont.getLastPage();
        page_.setProcessingAttribute(EMPTY_STRING);
        page_.setOffset(0);
        if (getNextSibling() != null) {
            throw new BadCatchException(_cont.joinPages());
        }
        page_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        opThrow = ElUtil.getAnalyzedOperations(expression, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        OperationNode el_ = opThrow.last();
        String param_ = Throwable.class.getName();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(param_, el_.getResultClass().getName(), _cont.getClasses())) {
            throw new BadCatchException(_cont.joinPages());
        }
    }

    @Override
    boolean canBeLastOfBlockGroup() {
        return false;
    }


    @Override
    public void checkCallConstructor(ContextEl _cont) {
        PageEl p_ = _cont.getLastPage();
        p_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        for (OperationNode o: opThrow) {
            if (o.isSuperThis()) {
                int off_ = o.getFullIndexInEl();
                p_.setOffset(off_);
                throw new BadConstructorCall(_cont.joinPages());
            }
        }
    }

    @Override
    public String getTagName() {
        return TAG_THROW;
    }

    @Override
    public void processEl(ContextEl _cont) {
        PageEl ip_ = _cont.getLastPage();
        ip_.setOffset(0);
        ip_.setProcessingAttribute(ATTRIBUTE_EXPRESSION);
        ExpressionLanguage el_ = ip_.getCurrentEl(this, CustList.FIRST_INDEX, getEl());
        Argument arg_ = el_.calculateMember(_cont);
        el_.setCurrentOper(null);
        ip_.clearCurrentEls();
        Struct o_ = arg_.getStruct();
        if (o_ == null || o_.isNull()) {
            o_ = new Struct(new NullPointerException());
        }
        throw new InvokeException(_cont.joinPages(), o_);
    }
}
