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
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        String ownerType_ = getFoundClass();
        ownerType_ = _stack.formatVarType(ownerType_);
        clArg_ = _stack.formatVarType(clArg_);
        Argument res_ = new Argument(newLambda(ownerType_, clArg_, pair, id));
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Struct newLambda(String _ownerType,
                                   String _clArg,
                                   ExecRootBlock _root, StringMap<String> _id) {
        return new LambdaRecordConstructorStruct(_root, _clArg, _ownerType, _id);
    }

}
