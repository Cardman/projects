package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ElUtil;
import code.expressionlanguage.OffsetStringInfo;
import code.expressionlanguage.OffsetsBlock;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.errors.custom.UnexpectedTypeError;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.AssignedBooleanVariables;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;

public abstract class Condition extends BracedStack implements StackableBlockGroup, WithNotEmptyEl {

    private String condition;

    private int conditionOffset;

    private CustList<ExecOperationNode> opCondition;

    public Condition(ContextEl _importingPage,
            BracedBlock _m, OffsetStringInfo _condition, OffsetsBlock _offset) {
        super(_importingPage, _m, _offset);
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
        opCondition = ElUtil.getAnalyzedOperations(condition, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        ExecOperationNode elCondition_ = opCondition.last();
        LgNames stds_ = _cont.getStandards();
        if (!elCondition_.getResultClass().isBoolType(_cont)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(conditionOffset);
            un_.setType(opCondition.last().getResultClass());
            _cont.getClasses().addError(un_);
        }
        elCondition_.getResultClass().setUnwrapObject(stds_.getAliasPrimBoolean());
        buildConditions(_cont);
    }

    @Override
    public void reduce(ContextEl _context) {
        ExecOperationNode r_ = opCondition.last();
        opCondition = ElUtil.getReducedNodes(r_);
    }

    public final ExpressionLanguage getElCondition() {
        return new ExpressionLanguage(opCondition);
    }
    public ExecOperationNode getRoot() {
        return getOpCondition().last();
    }
    public CustList<ExecOperationNode> getOpCondition() {
        return opCondition;
    }

    public final String getCondition() {
        return condition;
    }

    final Boolean evaluateCondition(ContextEl _context) {
        AbstractPageEl last_ = _context.getLastPage();
        ExpressionLanguage exp_ = last_.getCurrentEl(_context,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
        last_.setOffset(0);
        last_.setGlobalOffset(conditionOffset);
        Argument arg_ = exp_.calculateMember(_context);
        if (_context.callsOrException()) {
            return null;
        }
        last_.clearCurrentEls();
        return ((BooleanStruct) arg_.getStruct()).getInstance();
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        return getElCondition();
    }

}
