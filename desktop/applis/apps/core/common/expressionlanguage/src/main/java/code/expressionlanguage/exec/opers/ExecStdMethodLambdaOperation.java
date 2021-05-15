package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
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
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        ExecFormattedRootBlock ownerType_ = getFoundClass();
        ownerType_ = _stack.formatVarType(ownerType_);
        clArg_ = _stack.formatVarType(clArg_);
        Argument res_ = new Argument(newLambda(getLambdaCommonContent(),previous_, _conf, ownerType_, clArg_, function, method.getConstraints()));
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Struct newLambda(ExecLambdaCommonContent _common, Argument _previous, ContextEl _conf, ExecFormattedRootBlock _ownerType,
                                   String _clArg, StandardMethod _function, MethodId _constraints) {
        String className_;
        className_ = StringExpUtil.getIdFromAllTypes(_ownerType.getFormatted());
        String from_ = className_;
        MethodModifier met_ = _function.getModifier();
        MethodMetaInfo metaInfo_ = new MethodMetaInfo(_conf,_common,_ownerType,from_, _constraints, met_,_function);
        return new LambdaMethodStruct(metaInfo_,_previous,_common,_constraints,_clArg, _ownerType.getFormatted());
    }


}
