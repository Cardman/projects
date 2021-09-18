package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.expressionlanguage.structs.LambdaConstructorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecStdConstructorLambdaOperation extends ExecAbstractLambdaOperation {

    private final ConstructorId realId;
    private final StandardType standardType;
    private final StandardConstructor standardConstructor;

    public ExecStdConstructorLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ConstructorId _realId, StandardType _standardType, StandardConstructor _standardConstructor) {
        super(_opCont, _lamCont);
        realId = _realId;
        standardType = _standardType;
        standardConstructor = _standardConstructor;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        ExecFormattedRootBlock ownerType_ = formatVarType(_stack);
        String clArg_ = formatVarTypeRes(_stack);
        Argument res_ = new Argument(newLambda(getLambdaCommonContent(),previous_, ownerType_, realId, clArg_, standardType,standardConstructor));
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Struct newLambda(ExecLambdaCommonContent _common, Argument _previous, ExecFormattedRootBlock _ownerType, ConstructorId _realId,
                                   String _clArg, StandardType _standardType, StandardConstructor _standardConstructor) {
        ConstructorMetaInfo met_ = new ConstructorMetaInfo(_standardType, _standardConstructor, _common,_ownerType, _realId);
        return new LambdaConstructorStruct(met_,_previous,_common,_clArg);
    }

}
