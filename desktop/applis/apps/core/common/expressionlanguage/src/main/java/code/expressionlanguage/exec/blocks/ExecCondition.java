package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.core.IndexConstants;

public abstract class ExecCondition extends ExecBracedBlock implements WithNotEmptyEl {


    private final int conditionOffset;

    private final CustList<ExecOperationNode> opCondition;

    ExecCondition(int _conditionOffset, CustList<ExecOperationNode> _opCondition, int _offsetTrim) {
        super(_offsetTrim);
        conditionOffset = _conditionOffset;
        opCondition = _opCondition;
    }

    public final ConditionReturn evaluateCondition(ContextEl _context, StackCall _stackCall) {
        AbstractPageEl last_ = _stackCall.getLastPage();
        ExpressionLanguage exp_ = last_.getCurrentEl(_context,this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
        last_.setOffset(0);
        last_.setGlobalOffset(conditionOffset);
        Argument arg_ = ExpressionLanguage.tryToCalculate(_context,exp_,0, _stackCall);
        if (_context.callsOrException(_stackCall)) {
            return ConditionReturn.CALL_EX;
        }
        last_.clearCurrentEls();
        _context.getCoverage().passConditions(this, arg_,opCondition.last(), _stackCall);
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
