package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.*;
import code.util.IdMap;

public final class ExecConstructorLambdaOperation extends ExecAbstractLambdaOperation {

    private ExecLambdaConstructorContent lambdaConstructorContent;

    public ExecConstructorLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaConstructorContent _lambdaConstructorContent) {
        super(_opCont, _lamCont);
        lambdaConstructorContent = _lambdaConstructorContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getCommonArgument(previous_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getCommonArgument(Argument _previous, ContextEl _conf) {
        return new Argument(newLambda(_previous,_conf, getFoundClass(), lambdaConstructorContent.getRealId(), getReturnFieldType()));
    }

    private Struct newLambda(Argument _previous, ContextEl _conf, String foundClass, ConstructorId realId, String returnFieldType) {
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        String ownerType_ = foundClass;
        ownerType_ = _conf.formatVarType(ownerType_);
        clArg_ = _conf.formatVarType(clArg_);
        return newLambda(_previous, _conf, ownerType_, realId, returnFieldType, isShiftArgument(), isSafeInstance(), clArg_, getFileName(), lambdaConstructorContent.getFunctionBlock(), lambdaConstructorContent.getRootBlock(), lambdaConstructorContent.getFunction());
    }

    public static Struct newLambda(Argument _previous, ContextEl _conf, String ownerType_, ConstructorId realId, String returnFieldType, boolean shiftArgument, boolean safeInstance, String clArg_, String fileName, ExecNamedFunctionBlock functionBlock, ExecRootBlock _rootBlock, ExecNamedFunctionBlock function) {
        LambdaConstructorStruct l_ = new LambdaConstructorStruct(clArg_, ownerType_, shiftArgument);
        l_.setInstanceCall(_previous);
        l_.setSafeInstance(safeInstance);
        if (!ownerType_.startsWith(ARR)) {
            String className_ = StringExpUtil.getIdFromAllTypes(ownerType_);
            ConstructorId fid_ = ExecutingUtil.tryFormatId(ownerType_, _conf, realId);
            ConstructorMetaInfo met_ = new ConstructorMetaInfo(ownerType_,AccessEnum.PUBLIC, realId, returnFieldType, fid_, className_);
            met_.setFileName(fileName);
            met_.setAnnotableBlock(functionBlock);
            met_.setCallee(function);
            met_.setDeclaring(_rootBlock);
            l_.setMetaInfo(met_);
        }
        return l_;
    }

}
