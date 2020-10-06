package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.blocks.ExecAnonymousFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.structs.*;
import code.util.IdMap;

public final class ExecMethodLambdaOperation extends ExecAbstractLambdaOperation {

    private ExecLambdaMethodContent lambdaMethodContent;

    public ExecMethodLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaMethodContent _lambdaMethodContent) {
        super(_opCont, _lamCont);
        lambdaMethodContent = _lambdaMethodContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getCommonArgument(previous_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getCommonArgument(Argument _previous, ContextEl _conf) {
        return new Argument(newLambda(_previous,_conf, getFoundClass(), lambdaMethodContent.getMethod(), getReturnFieldType(), getAncestor(), lambdaMethodContent.isDirectCast(), lambdaMethodContent.isPolymorph()));
    }

    private Struct newLambda(Argument _previous, ContextEl _conf, String foundClass, ClassMethodId method, String returnFieldType, int ancestor, boolean directCast, boolean polymorph) {
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        String ownerType_ = foundClass;
        ownerType_ = _conf.formatVarType(ownerType_);
        clArg_ = _conf.formatVarType(clArg_);
        return newLambda(_previous, _conf, ownerType_, method, returnFieldType, ancestor, directCast, polymorph, lambdaMethodContent.isAbstractMethod(), lambdaMethodContent.isExpCast(), isShiftArgument(), isSafeInstance(), clArg_, getFileName(), lambdaMethodContent.getFunctionBlock(), lambdaMethodContent.getFunction(), lambdaMethodContent.getDeclaring());
    }

    public static Struct newLambda(Argument _previous, ContextEl _conf, String ownerType_, ClassMethodId method, String returnFieldType,
                                   int ancestor, boolean directCast, boolean polymorph, boolean abstractMethod, boolean expCast, boolean shiftArgument, boolean safeInstance,
                                   String clArg_, String fileName, ExecNamedFunctionBlock functionBlock, ExecNamedFunctionBlock function, ExecRootBlock _rootBlock) {
        MethodId id_ = method.getConstraints();
        LambdaMethodStruct l_ = new LambdaMethodStruct(clArg_, ownerType_, id_, polymorph, shiftArgument, ancestor, abstractMethod);
        l_.setInstanceCall(_previous);
        l_.setDirectCast(directCast);
        l_.setExpCast(expCast);
        l_.setSafeInstance(safeInstance);
        if (!(ownerType_.startsWith(PrimitiveTypeUtil.ARR_CLASS) && id_.getName().startsWith("[]"))) {
            MethodMetaInfo metaInfo_ = buildMeta(_conf, returnFieldType, expCast, fileName, functionBlock, function, _rootBlock, ownerType_, id_, l_);
            l_.setMetaInfo(metaInfo_);
        }
        return l_;
    }

    public static Struct newAnonymousLambda(Argument _previous, ContextEl _conf, String foundClass, ClassMethodId method, String returnFieldType,
                                   int ancestor, boolean directCast, boolean polymorph, boolean abstractMethod, boolean expCast, boolean shiftArgument, boolean safeInstance,
                                   String name, AbstractPageEl lastPage, String fileName,ExecAnonymousFunctionBlock functionBlock, ExecAnonymousFunctionBlock function, ExecRootBlock _rootBlock) {
        String clArg_ = name;
        String ownerType_ = foundClass;
        ownerType_ = _conf.formatVarType(ownerType_);
        clArg_ = _conf.formatVarType(clArg_);
        MethodId id_ = method.getConstraints();
        LambdaMethodStruct l_ = new LambdaMethodStruct(clArg_, ownerType_, id_, polymorph, shiftArgument, ancestor, abstractMethod);
        l_.setInstanceCall(_previous);
        l_.setDirectCast(directCast);
        l_.setExpCast(expCast);
        l_.setSafeInstance(safeInstance);
        MethodMetaInfo metaInfo_ = buildMeta(_conf, returnFieldType, expCast, fileName, functionBlock, function, _rootBlock, ownerType_, id_, l_);
        metaInfo_.setCache(new Cache(lastPage));
        l_.setMetaInfo(metaInfo_);
        return l_;
    }

    private static MethodMetaInfo buildMeta(ContextEl _conf, String returnFieldType, boolean expCast, String fileName, ExecNamedFunctionBlock functionBlock, ExecNamedFunctionBlock function, ExecRootBlock _rootBlock, String ownerType_, MethodId id_, LambdaMethodStruct l_) {
        MethodId fid_ = ExecutingUtil.tryFormatId(ownerType_, _conf, id_);
        String className_;
        if (l_.isStaticCall()) {
            className_ = ownerType_;
        } else {
            className_ = StringExpUtil.getIdFromAllTypes(ownerType_);
        }
        String from_ = className_;
        if (className_.startsWith("[")) {
            from_ = StringExpUtil.getPrettyArrayType(_conf.getStandards().getContent().getCoreNames().getAliasObject());
        }
        String idCl_ = StringExpUtil.getIdFromAllTypes(ownerType_);
        String formCl_ = ExecutingUtil.tryFormatType(idCl_, ownerType_, _conf);
        MethodModifier met_;
        if (l_.isAbstractMethod()) {
            met_ = MethodModifier.ABSTRACT;
        } else if (fid_.getKind() == MethodAccessKind.STATIC) {
            met_ = MethodModifier.STATIC;
        } else if (fid_.getKind() == MethodAccessKind.STATIC_CALL) {
            met_ = MethodModifier.STATIC_CALL;
        } else {
            met_ = MethodModifier.NORMAL;
        }
        MethodMetaInfo metaInfo_ = new MethodMetaInfo(ownerType_,AccessEnum.PUBLIC, from_, id_, met_, returnFieldType, fid_, formCl_);
        metaInfo_.setExpCast(expCast);
        metaInfo_.setFileName(fileName);
        metaInfo_.setAnnotableBlock(functionBlock);
        metaInfo_.setCallee(function);
        metaInfo_.setCalleeInv(function);
        metaInfo_.setDeclaring(_rootBlock);
        return metaInfo_;
    }


}
