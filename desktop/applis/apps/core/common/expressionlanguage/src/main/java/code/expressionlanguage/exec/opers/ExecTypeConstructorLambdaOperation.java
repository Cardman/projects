package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.expressionlanguage.structs.LambdaConstructorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecTypeConstructorLambdaOperation extends ExecAbstractLambdaOperation {

    private final ExecLambdaConstructorContent lambdaConstructorContent;

    public ExecTypeConstructorLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaConstructorContent _lambdaConstructorContent) {
        super(_opCont, _lamCont);
        lambdaConstructorContent = _lambdaConstructorContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        ExecFormattedRootBlock ownerType_ = getFoundClass();
        ownerType_ = _stack.formatVarType(ownerType_);
        clArg_ = _stack.formatVarType(clArg_);
        Argument res_ = new Argument(newLambda(getLambdaCommonContent(),lambdaConstructorContent,previous_, ownerType_, clArg_));
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Struct newLambda(ExecLambdaCommonContent _common, ExecLambdaConstructorContent _ctor, Argument _previous, ExecFormattedRootBlock _ownerType,
                                   String _clArg) {
        ConstructorMetaInfo met_ = new ConstructorMetaInfo(_ctor.getPair(), _common,_ownerType, _ctor.getRealId());
        return new LambdaConstructorStruct(met_,_previous,_common,_clArg, _ownerType.getFormatted());
    }

}
