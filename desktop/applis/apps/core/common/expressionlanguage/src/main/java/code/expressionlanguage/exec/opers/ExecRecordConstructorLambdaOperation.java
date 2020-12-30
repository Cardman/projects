package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.LambdaRecordConstructorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;
import code.util.StringMap;

public final class ExecRecordConstructorLambdaOperation extends ExecAbstractLambdaOperation {

    private final ExecRootBlock pair;
    private final StringMap<String> id;

    public ExecRecordConstructorLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecRootBlock _pair, StringMap<String> _id) {
        super(_opCont, _lamCont);
        pair = _pair;
        id = _id;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument res_ = getCommonArgument(_stack);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    Argument getCommonArgument(StackCall _stackCall) {
        return new Argument(newLambda(getFoundClass(), _stackCall));
    }

    private Struct newLambda(String _foundClass, StackCall _stackCall) {
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        String ownerType_ = _foundClass;
        ownerType_ = _stackCall.formatVarType(ownerType_);
        clArg_ = _stackCall.formatVarType(clArg_);
        return newLambda(ownerType_, clArg_, pair, id);
    }

    public static Struct newLambda(String _ownerType,
                                   String _clArg,
                                   ExecRootBlock _root, StringMap<String> _id) {
        return new LambdaRecordConstructorStruct(_root, _clArg, _ownerType, _id);
    }

}
