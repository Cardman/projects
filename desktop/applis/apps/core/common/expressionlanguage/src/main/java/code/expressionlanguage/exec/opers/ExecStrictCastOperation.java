package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecTypeCheckContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecStrictCastOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    private final ExecTypeCheckContent typeCheckContent;
    public ExecStrictCastOperation(ExecOperationContent _opCont, ExecTypeCheckContent _typeCheckContent) {
        super(_opCont);
        typeCheckContent = _typeCheckContent;
    }


    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        setRelOffsetPossibleLastPage(typeCheckContent.getOffset(), _stack);
        Struct str_ = ExecHelper.getFirstArgument(arguments_).getStruct();
        String paramName_ = _stack.formatVarType(typeCheckContent.getClassName());
        ExecTemplates.checkQuick(paramName_,str_.getClassName(_conf),_conf,_stack);
        setQuickNoConvertSimpleArgument(new Argument(str_), _conf, _nodes, _stack);
    }

}
