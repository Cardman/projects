package code.expressionlanguage.methods;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ConditionReturn;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.errors.custom.UnexpectedTypeError;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.AssignedBooleanVariables;
import code.expressionlanguage.opers.util.AssignedVariables;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;

public abstract class Condition extends BracedStack implements WithNotEmptyEl, BuildableElMethod {

    private String condition;

    private int conditionOffset;

    private CustList<ExecOperationNode> opCondition;

    public Condition(OffsetStringInfo _condition, OffsetsBlock _offset) {
        super(_offset);
        condition = _condition.getInfo();
        conditionOffset = _condition.getOffset();
    }

    public int getConditionOffset() {
        return conditionOffset;
    }

    @Override
    protected AssignedVariables buildNewAssignedVariable() {
        return new AssignedBooleanVariables();
    }
    

    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = _cont.getAnalyzing().getCurrentFct();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(conditionOffset);
        page_.setOffset(0);
        opCondition = ElUtil.getAnalyzedOperations(condition, _cont, Calculation.staticCalculation(f_.getStaticContext()));
        processBoolean(_cont);
        buildConditions(_cont);
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        FunctionBlock f_ = _cont.getAnalyzing().getCurrentFct();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(conditionOffset);
        page_.setOffset(0);
        opCondition = ElUtil.getAnalyzedOperationsReadOnly(condition, _cont, Calculation.staticCalculation(f_.getStaticContext()));
        processBoolean(_cont);
    }

    private void processBoolean(ContextEl _cont) {
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
    }

    @Override
    public void reduce(ContextEl _context) {
        ExecOperationNode r_ = opCondition.last();
        opCondition = ElUtil.getReducedNodes(r_);
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        int off_ = getConditionOffset();
        int offsetEndBlock_ = off_ + getCondition().length();
        ElUtil.buildCoverageReport(_cont,off_,this,getOpCondition(),offsetEndBlock_,_parts);
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

    final ConditionReturn evaluateCondition(ContextEl _context) {
        AbstractPageEl last_ = _context.getLastPage();
        ExpressionLanguage exp_ = last_.getCurrentEl(_context,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
        last_.setOffset(0);
        last_.setGlobalOffset(conditionOffset);
        Argument arg_ = ElUtil.tryToCalculate(_context,exp_,0);
        if (_context.callsOrException()) {
            return ConditionReturn.CALL_EX;
        }
        last_.clearCurrentEls();
        if (((BooleanStruct) arg_.getStruct()).getInstance()) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        return getElCondition();
    }

}
