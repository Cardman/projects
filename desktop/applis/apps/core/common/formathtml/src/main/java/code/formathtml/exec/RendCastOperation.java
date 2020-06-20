package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.CastOperation;
import code.expressionlanguage.exec.opers.ExecCastOperation;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendCastOperation extends RendAbstractUnaryOperation {

    private String className;
    private int offset;
    public RendCastOperation(CastOperation _c) {
        super(_c);
        className = _c.getClassName();
        offset = _c.getOffset();
    }


    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf,_nodes);
    }

    Argument getArgument(CustList<Argument> _arguments, Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        Argument objArg_ = _arguments.first();
        String paramName_ = _conf.getPageEl().formatVarType(className, _conf.getContext());
        ExecCastOperation.wrapFct(paramName_,false,_arguments,_conf.getContext());
        ExecTemplates.checkObject(paramName_, objArg_, _conf.getContext());
        return objArg_;
    }
}
