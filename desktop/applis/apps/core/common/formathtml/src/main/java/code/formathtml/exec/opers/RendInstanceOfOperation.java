package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecTypeCheckContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendInstanceOfOperation extends RendAbstractUnaryOperation {

    private ExecTypeCheckContent typeCheckContent;
    public RendInstanceOfOperation(ExecOperationContent _content, ExecTypeCheckContent _typeCheckContent) {
        super(_content);
        typeCheckContent = _typeCheckContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf,_nodes);
    }

    Argument getArgument(CustList<Argument> _arguments, Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ typeCheckContent.getOffset(), _conf);
        Argument objArg_ = _arguments.first();
        if (objArg_.isNull()) {
            return new Argument(BooleanStruct.of(false));
        }
        PageEl page_ = _conf.getPageEl();
        String str_ = page_.formatVarType(typeCheckContent.getClassName(), _conf.getContext());
        boolean res_ = ExecTemplates.safeObject(str_, objArg_, _conf.getContext()) == ErrorType.NOTHING;
        return new Argument(BooleanStruct.of(res_));
    }
}
