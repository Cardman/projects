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
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendArrayFieldOperation extends RendAbstractFieldOperation {

    public RendArrayFieldOperation(ExecOperationContent _content, ExecFieldOperationContent _fieldOperationContent) {
        super(_content, _fieldOperationContent);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument arg_ = RendDynOperationNode.processCall(getArgument(previous_, _conf, _context), _context);
        if (_context.callsOrException()) {
            return;
        }
        setSimpleArgument(arg_, _conf,_nodes, _context);
    }

    public Argument getArgument(Argument _previous, Configuration _conf, ContextEl _context) {
        return getCommonArgument(_previous,_conf, _context);
    }
    Argument getCommonArgument(Argument _previous, Configuration _conf, ContextEl _ctx) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+getOff(), _conf);
        Struct inst_ = _previous.getStruct();
        if (inst_ instanceof ArrayStruct) {
            ArrayStruct arr_ = (ArrayStruct) inst_;
            return new Argument(new IntStruct(arr_.getLength()));
        }
        String npe_ = _ctx.getStandards().getContent().getCoreNames().getAliasNullPe();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        _ctx.setCallingState(new CustomFoundExc(new ErrorStruct(_ctx, npe_)));
        return new Argument();
    }
}
