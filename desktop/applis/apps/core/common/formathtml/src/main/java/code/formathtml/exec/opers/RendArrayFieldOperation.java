package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecFieldOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendArrayFieldOperation extends RendAbstractFieldOperation {

    public RendArrayFieldOperation(ExecOperationContent _content, ExecFieldOperationContent _fieldOperationContent) {
        super(_content, _fieldOperationContent);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        Argument arg_ = getArgument(previous_, _context, _stack, _rendStack);
        if (_context.callsOrException(_stack)) {
            return;
        }
        setSimpleArgument(arg_, _nodes, _context, _stack, _rendStack);
    }

    public Argument getArgument(Argument _previous, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        return getCommonArgument(_previous, _context, _stackCall, _rendStackCall);
    }
    Argument getCommonArgument(Argument _previous, ContextEl _ctx, StackCall _stackCall, RendStackCall _rendStackCall) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+getOff(), _rendStackCall);
        Struct inst_ = _previous.getStruct();
        if (inst_ instanceof ArrayStruct) {
            ArrayStruct arr_ = (ArrayStruct) inst_;
            return new Argument(new IntStruct(arr_.getLength()));
        }
        String npe_ = _ctx.getStandards().getContent().getCoreNames().getAliasNullPe();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _rendStackCall);
        _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_ctx, npe_, _stackCall)));
        return new Argument();
    }
}
