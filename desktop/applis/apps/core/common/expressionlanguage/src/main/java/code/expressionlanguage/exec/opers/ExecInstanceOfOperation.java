package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecTypeCheckContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecInstanceOfOperation extends ExecAbstractUnaryOperation {

    private final ExecTypeCheckContent typeCheckContent;
    public ExecInstanceOfOperation(ExecOperationContent _opCont, ExecTypeCheckContent _typeCheckContent) {
        super(_opCont);
        typeCheckContent = _typeCheckContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        setRelOffsetPossibleLastPage(typeCheckContent.getOffset(), _stack);
        Argument objArg_ = ExecTemplates.getFirstArgument(arguments_);
        Argument argres_;
        if (objArg_.isNull()) {
            argres_ = new Argument(BooleanStruct.of(false));
        } else {
            String str_ = _stack.formatVarType(typeCheckContent.getClassName());
            boolean res_ = ExecTemplates.safeObject(str_, objArg_, _conf) == ErrorType.NOTHING;
            argres_ = new Argument(BooleanStruct.of(res_));
        }
        setSimpleArgument(argres_, _conf, _nodes, _stack);
    }

}
