package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecTypeCheckContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendInstanceOfOperation extends RendAbstractUnaryOperation {

    private final ExecTypeCheckContent typeCheckContent;
    public RendInstanceOfOperation(ExecOperationContent _content, ExecTypeCheckContent _typeCheckContent) {
        super(_content);
        typeCheckContent = _typeCheckContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument argres_ = getArgument(arguments_, _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _stack, _rendStack);
    }

    Argument getArgument(CustList<Argument> _arguments, ContextEl _context, RendStackCall _rendStackCall) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ typeCheckContent.getOffset(), _rendStackCall);
        Argument objArg_ = ExecTemplates.getFirstArgument(_arguments);
        if (objArg_.isNull()) {
            return new Argument(BooleanStruct.of(false));
        }
        String str_ = typeCheckContent.getClassName();
        boolean res_ = ExecTemplates.safeObject(str_, objArg_, _context) == ErrorType.NOTHING;
        return new Argument(BooleanStruct.of(res_));
    }
}
