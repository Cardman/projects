package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.LambdaConstructorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecConstructorLambdaOperation extends ExecAbstractLambdaOperation {

    public ExecConstructorLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont) {
        super(_opCont, _lamCont);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        ExecFormattedRootBlock ownerType_ = formatVarType(_stack);
        String clArg_ = formatVarTypeRes(_stack);
        Argument res_ = new Argument(newLambda(getLambdaCommonContent(),previous_, ownerType_, clArg_));
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Struct newLambda(ExecLambdaCommonContent _comm, Argument _previous, ExecFormattedRootBlock _ownerType,
                                   String _clArg) {
        return new LambdaConstructorStruct(_ownerType.getFormatted(),_previous,_comm,_clArg);
    }

}
