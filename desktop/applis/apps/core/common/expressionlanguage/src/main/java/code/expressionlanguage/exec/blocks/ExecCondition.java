package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.core.IndexConstants;

public abstract class ExecCondition extends ExecBracedBlock implements WithNotEmptyEl {


    private int conditionOffset;

    private CustList<ExecOperationNode> opCondition;

    ExecCondition(int _conditionOffset, CustList<ExecOperationNode> _opCondition, int _offsetTrim) {
        super(_offsetTrim);
        conditionOffset = _conditionOffset;
        opCondition = _opCondition;
    }

    @Override
    public void reduce(ContextEl _context) {
        ExecOperationNode r_ = opCondition.last();
        opCondition = ExpressionLanguage.getReducedNodes(r_);
    }

    public final ConditionReturn evaluateCondition(ContextEl _context) {
        AbstractPageEl last_ = _context.getLastPage();
        ExpressionLanguage exp_ = last_.getCurrentEl(_context,this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
        last_.setOffset(0);
        last_.setGlobalOffset(conditionOffset);
        Argument arg_ = ExpressionLanguage.tryToCalculate(_context,exp_,0);
        if (_context.callsOrException()) {
            return ConditionReturn.CALL_EX;
        }
        last_.clearCurrentEls();
        _context.getCoverage().passConditions(_context, this, arg_,opCondition.last());
        if (BooleanStruct.isTrue(NumParsers.convertToBoolean(arg_.getStruct()))) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
                                    int _indexProcess) {
        return new ExpressionLanguage(opCondition);
    }

}
