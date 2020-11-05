package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecCastOperation;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecTypeCheckContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendCastOperation extends RendAbstractUnaryOperation {

    private ExecTypeCheckContent typeCheckContent;
    public RendCastOperation(ExecOperationContent _content, ExecTypeCheckContent _typeCheckContent) {
        super(_content);
        typeCheckContent = _typeCheckContent;
    }


    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument argres_ = getArgument(arguments_, _conf, _context);
        setSimpleArgument(argres_, _conf,_nodes, _context);
    }

    Argument getArgument(CustList<Argument> _arguments, Configuration _conf, ContextEl _context) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ typeCheckContent.getOffset(), _conf);
        Argument objArg_ = new Argument(ExecTemplates.getFirstArgument(_arguments).getStruct());
        String paramName_ = typeCheckContent.getClassName();
        ExecCastOperation.wrapFct(paramName_,false, _context, objArg_);
        ExecTemplates.checkObject(paramName_, objArg_, _context);
        return objArg_;
    }
}
