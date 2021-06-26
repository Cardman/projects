package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.structs.BooleanStruct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public abstract class RendCondition extends RendParentBlock implements RendWithEl {


    private final RendOperationNodeListOff condition;

    RendCondition(CustList<RendDynOperationNode> _op, int _offset) {
        condition = new RendOperationNodeListOff(_op,_offset);
    }

    final ConditionReturn evaluateCondition(Configuration _context, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStackCall) {
        ImportingPage last_ = _rendStackCall.getLastPage();
        last_.setOffset(condition.getOffset());
        last_.setProcessingAttribute(_context.getRendKeyWords().getAttrCondition());
        Argument arg_ = RenderExpUtil.calculateReuse(condition.getList(), _stds, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }
}
