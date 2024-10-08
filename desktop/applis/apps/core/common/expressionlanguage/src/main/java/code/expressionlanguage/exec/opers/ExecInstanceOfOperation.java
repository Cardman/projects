package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecTypeCheckContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecInstanceOfOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    private final ExecTypeCheckContent typeCheckContent;
    public ExecInstanceOfOperation(ExecOperationContent _opCont, ExecTypeCheckContent _typeCheckContent) {
        super(_opCont);
        typeCheckContent = _typeCheckContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        CustList<Struct> arguments_ = getArguments(_nodes, this);
        setRelOffsetPossibleLastPage(typeCheckContent.getOffset(), _stack);
        Struct objArg_ = ExecHelper.getFirstArgument(arguments_);
        Struct argres_;
        if (objArg_ == NullStruct.NULL_VALUE) {
            argres_ = BooleanStruct.of(false);
        } else {
            String str_ = _stack.formatVarType(typeCheckContent.getClassName());
            boolean res_ = ExecInherits.safeObject(str_, objArg_.getClassName(_conf), _conf) == ErrorType.NOTHING;
            argres_ = BooleanStruct.of(res_);
        }
        setSimpleArgument(argres_, _conf, _nodes, _stack);
    }

}
