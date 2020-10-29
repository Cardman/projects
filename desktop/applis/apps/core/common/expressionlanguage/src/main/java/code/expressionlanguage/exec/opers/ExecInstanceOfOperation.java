package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecTypeCheckContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecInstanceOfOperation extends ExecAbstractUnaryOperation {

    private ExecTypeCheckContent typeCheckContent;
    public ExecInstanceOfOperation(ExecOperationContent _opCont, ExecTypeCheckContent _typeCheckContent) {
        super(_opCont);
        typeCheckContent = _typeCheckContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ = getArgument( arguments_, _conf);
        setSimpleArgument(argres_, _conf, _nodes);
    }

    Argument getArgument(CustList<Argument> _arguments, ContextEl _conf) {
        setRelOffsetPossibleLastPage(typeCheckContent.getOffset(), _conf);
        Argument objArg_ = _arguments.first();
        if (objArg_.isNull()) {
            return new Argument(BooleanStruct.of(false));
        }
        String str_ = _conf.formatVarType(typeCheckContent.getClassName());
        boolean res_ = ExecTemplates.safeObject(str_, objArg_, _conf) == ErrorType.NOTHING;
        return new Argument(BooleanStruct.of(res_));
    }
}
