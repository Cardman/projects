package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecOperatorMethodLambdaOperation extends ExecAbstractLambdaOperation {

    private final ExecLambdaMethodContent lambdaMethodContent;

    public ExecOperatorMethodLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaMethodContent _lambdaMethodContent) {
        super(_opCont, _lamCont);
        lambdaMethodContent = _lambdaMethodContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        Argument res_ = new Argument(newLambda(previous_, getFoundClass(), getReturnFieldType(), getAncestor(), lambdaMethodContent.isPolymorph(), lambdaMethodContent.isAbstractMethod(), isShiftArgument(), isSafeInstance(), clArg_, getFileName(), lambdaMethodContent.getMethod().getConstraints(), lambdaMethodContent.getFunction()));
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Struct newLambda(Argument _previous, String _ownerType, String _returnFieldType,
                                   int _ancestor, boolean _polymorph, boolean _abstractMethod, boolean _shiftArgument, boolean _safeInstance,
                                   String _clArg, String _fileName, MethodId _constraints, ExecNamedFunctionBlock _operator) {
        LambdaMethodStruct l_ = new LambdaMethodStruct(_clArg, _ownerType, _polymorph, _shiftArgument, _ancestor, _abstractMethod);
        l_.setInstanceCall(_previous);
        l_.setSafeInstance(_safeInstance);
        l_.setMethodName(_constraints.getName());
        l_.setKind(MethodAccessKind.STATIC);
        String idCl_ = StringExpUtil.getIdFromAllTypes(_ownerType);
        MethodModifier met_ = MethodModifier.STATIC;
        MethodMetaInfo metaInfo_ = new MethodMetaInfo(_ownerType,AccessEnum.PUBLIC, idCl_, _constraints, met_, _returnFieldType, _constraints, idCl_);
        metaInfo_.setFileName(_fileName);
        metaInfo_.setCallee(_operator);
        metaInfo_.pair(null, _operator);
        l_.setMetaInfo(metaInfo_);
        return l_;
    }


}
