package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendArrayFieldOperation extends RendAbstractFieldOperation {

    public RendArrayFieldOperation(ExecOperationContent _content, ExecFieldOperationContent _fieldOperationContent) {
        super(_content, _fieldOperationContent);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        Struct inst_ = previous_.getStruct();
        Argument arg_;
        if (inst_ instanceof ArrayStruct) {
            setRelOffsetPossibleLastPage(getOff(), _rendStack);
            ArrayStruct arr_ = (ArrayStruct) inst_;
            arg_ = new Argument(new IntStruct(arr_.getLength()));
        } else {
            String npe_ = _context.getStandards().getContent().getCoreNames().getAliasNullPe();
            _rendStack.getStackCall().setCallingState(new CustomFoundExc(new ErrorStruct(_context, npe_, _rendStack.getStackCall())));
            arg_ = new Argument();
        }
        if (_context.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        setSimpleArgument(arg_, _nodes, _context, _rendStack);
    }

}
