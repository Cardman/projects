package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
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
import code.expressionlanguage.opers.util.AssignedBooleanVariables;
import code.expressionlanguage.opers.util.Assignment;
import code.expressionlanguage.opers.util.BooleanAssignment;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.stds.LgNames;
import code.sml.Element;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringMap;

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
    public AssignedBooleanVariables buildNewAssignedVariable() {
        return new AssignedBooleanVariables();
    }
    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = getFunction();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(conditionOffset);
        page_.setOffset(0);
        _cont.setRootAffect(false);
        opCondition = ElUtil.getAnalyzedOperations(condition, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        if (opCondition.isEmpty()) {
            return;
        }
        OperationNode elCondition_ = opCondition.last();
        LgNames stds_ = _cont.getStandards();
        if (!elCondition_.getResultClass().matchClass(stds_.getAliasPrimBoolean())) {
            if (!elCondition_.getResultClass().matchClass(stds_.getAliasBoolean())) {
                UnexpectedTypeError un_ = new UnexpectedTypeError();
                un_.setFileName(getFile().getFileName());
                un_.setRc(getRowCol(0, conditionOffset));
                un_.setType(opCondition.last().getResultClass().getName());
                _cont.getClasses().getErrorsDet().add(un_);
                return;
            }
        }
        AssignedBooleanVariables res_ = (AssignedBooleanVariables) _cont.getAnalyzing().getAssignedVariables().getFinalVariables().getVal(this);
        for (EntryCust<ClassField,Assignment> e: res_.getFields().lastValue().entryList()) {
            res_.getFieldsRootAfter().put(e.getKey(), (BooleanAssignment) e.getValue());
        }
        for (StringMap<Assignment> s: res_.getVariables().lastValue()) {
            StringMap<BooleanAssignment> sm_;
            sm_ = new StringMap<BooleanAssignment>();
            for (EntryCust<String,Assignment> e: s.entryList()) {
                sm_.put(e.getKey(), (BooleanAssignment) e.getValue());
            }
            res_.getVariablesRootAfter().add(sm_);
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
        AnalyzedPageEl p_ = _cont.getAnalyzing();
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
        ExpressionLanguage exp_ = last_.getCurrentEl(_context,this, CustList.FIRST_INDEX, false, CustList.FIRST_INDEX);
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

    @Override
    public ExpressionLanguage getEl(ContextEl _context, boolean _native,
            int _indexProcess) {
        return getElCondition();
    }

}
