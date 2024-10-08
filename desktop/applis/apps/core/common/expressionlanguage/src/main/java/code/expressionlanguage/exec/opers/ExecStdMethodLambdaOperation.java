package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecStdMethodLambdaOperation extends ExecAbstractLambdaOperation {

    private final ClassMethodId method;
    private final StandardMethod function;

    public ExecStdMethodLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ClassMethodId _method, StandardMethod _standardMethod) {
        super(_opCont, _lamCont);
        method = _method;
        function = _standardMethod;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Struct previous_ = getPreviousArg(this, _nodes, _stack.getLastPage());
        ExecFormattedRootBlock ownerType_ = formatVarType(_stack);
        String clArg_ = formatVarTypeRes(_stack);
        Struct res_ = newLambda(getLambdaCommonContent(),previous_, ownerType_, clArg_, function, method.getConstraints());
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Struct newLambda(ExecLambdaCommonContent _common, Struct _previous, ExecFormattedRootBlock _ownerType,
                                   String _clArg, StandardMethod _function, MethodId _constraints) {
        MethodMetaInfo metaInfo_ = new MethodMetaInfo(_common,_ownerType, _constraints, _function);
        return new LambdaMethodStruct(metaInfo_,_previous,_common,_constraints,_clArg, false);
    }


}
