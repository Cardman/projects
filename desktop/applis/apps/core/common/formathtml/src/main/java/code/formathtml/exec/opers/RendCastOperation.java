package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecCastOperation;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecTypeCheckContent;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendCastOperation extends RendAbstractUnaryOperation {

    private ExecTypeCheckContent typeCheckContent;
    public RendCastOperation(ExecOperationContent _content, ExecTypeCheckContent _typeCheckContent) {
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
        Argument objArg_ = new Argument(_arguments.first().getStruct());
        String paramName_ = _conf.getPageEl().formatVarType(typeCheckContent.getClassName(), _conf.getContext());
        ExecCastOperation.wrapFct(paramName_,false, _conf.getContext(), objArg_);
        ExecTemplates.checkObject(paramName_, objArg_, _conf.getContext());
        return objArg_;
    }
}
