package code.formathtml;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.structs.BooleanStruct;
import code.formathtml.exec.RendDynOperationNode;
import code.util.CustList;

public abstract class RendCondition extends RendParentBlock implements RendWithEl, RendReducableOperations {


    private int conditionOffset;

    private CustList<RendDynOperationNode> opCondition;

    RendCondition(int _offsetTrim, CustList<RendDynOperationNode> _op, int _offset) {
        super(_offsetTrim);
        conditionOffset = _offset;
        opCondition = _op;
    }

    final ConditionReturn evaluateCondition(Configuration _context) {
        ImportingPage last_ = _context.getLastPage();
        last_.setOffset(conditionOffset);
        last_.setProcessingAttribute(_context.getRendKeyWords().getAttrCondition());
        Argument arg_ = RenderExpUtil.calculateReuse(opCondition,_context);
        if (_context.getContext().hasException()) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }
}
