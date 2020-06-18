package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.Condition;
import code.expressionlanguage.methods.WithNotEmptyEl;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;

public abstract class ExecCondition extends ExecBracedBlock implements WithNotEmptyEl {

    private String condition;

    private int conditionOffset;

    private CustList<ExecOperationNode> opCondition;

    ExecCondition(OffsetsBlock _offset, String _condition, int _conditionOffset, CustList<ExecOperationNode> _opCondition) {
        super(_offset);
        conditionOffset = _conditionOffset;
        condition = _condition;
        opCondition = _opCondition;
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
        if (BooleanStruct.isTrue(ClassArgumentMatching.convertToBoolean(arg_.getStruct()))) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
                                    int _indexProcess) {
        return getElCondition();
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

    public int getConditionOffset() {
        return conditionOffset;
    }

    public String getCondition() {
        return condition;
    }
}
