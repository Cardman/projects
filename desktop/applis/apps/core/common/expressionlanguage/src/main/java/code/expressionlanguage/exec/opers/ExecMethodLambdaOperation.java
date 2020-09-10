package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.LambdaOperation;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.blocks.ExecAnnotableParametersBlock;
import code.expressionlanguage.exec.blocks.ExecAnonymousFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.structs.*;
import code.util.IdMap;

public final class ExecMethodLambdaOperation extends ExecAbstractLambdaOperation {

    private ClassMethodId method;
    private boolean polymorph;
    private boolean abstractMethod;
    private boolean directCast;
    private boolean expCast;
    private ExecAnnotableParametersBlock functionBlock;
    private ExecNamedFunctionBlock function;
    private ExecRootBlock declaring;

    public ExecMethodLambdaOperation(LambdaOperation _l, ContextEl _cont) {
        super(_l);
        method = _l.getMethod();
        polymorph = _l.isPolymorph();
        abstractMethod = _l.isAbstractMethod();
        directCast = _l.isDirectCast();
        expCast = _l.isExpCast();
        functionBlock = fetchFunction(_cont, _l.getRootNumber(), _l.getMemberNumber(), _l.getOperatorNumber());
        function = fetchFunction(_cont, _l.getRootNumber(), _l.getMemberNumber(), _l.getOperatorNumber());
        declaring = fetchType(_cont,_l.getRootNumber());
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getCommonArgument(previous_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getCommonArgument(Argument _previous, ContextEl _conf) {
        return new Argument(newLambda(_previous,_conf, getFoundClass(), method, getReturnFieldType(), getAncestor(), directCast, polymorph));
    }

    private Struct newLambda(Argument _previous, ContextEl _conf, String foundClass, ClassMethodId method, String returnFieldType, int ancestor, boolean directCast, boolean polymorph) {
        return newLambda(_previous, _conf, foundClass, method, returnFieldType, ancestor, directCast, polymorph, abstractMethod, expCast, isShiftArgument(), isSafeInstance(),
                getResultClass().getName(), _conf.getLastPage(), getFileName(),functionBlock,function,declaring);
    }

    public static Struct newLambda(Argument _previous, ContextEl _conf, String foundClass, ClassMethodId method, String returnFieldType,
                                   int ancestor, boolean directCast, boolean polymorph, boolean abstractMethod, boolean expCast, boolean shiftArgument, boolean safeInstance,
                                   String name, PageEl lastPage, String fileName,ExecAnnotableParametersBlock functionBlock, ExecNamedFunctionBlock function, ExecRootBlock _rootBlock) {
        String clArg_ = name;
        String ownerType_ = foundClass;
        ownerType_ = lastPage.formatVarType(ownerType_, _conf);
        clArg_ = lastPage.formatVarType(clArg_, _conf);
        MethodId id_ = method.getConstraints();
        LambdaMethodStruct l_ = new LambdaMethodStruct(clArg_, ownerType_, id_, polymorph, shiftArgument, ancestor, abstractMethod);
        l_.setInstanceCall(_previous);
        l_.setDirectCast(directCast);
        l_.setExpCast(expCast);
        l_.setSafeInstance(safeInstance);
        if (!(ownerType_.startsWith(PrimitiveTypeUtil.ARR_CLASS) && id_.getName().startsWith("[]"))) {
            MethodId fid_ = ExecutingUtil.tryFormatId(ownerType_, _conf, id_);
            String className_;
            if (l_.isStaticCall()) {
                className_ = ownerType_;
            } else {
                className_ = StringExpUtil.getIdFromAllTypes(ownerType_);
            }
            String from_ = className_;
            if (className_.startsWith("[")) {
                from_ = StringExpUtil.getPrettyArrayType(_conf.getStandards().getAliasObject());
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
            if (function instanceof ExecAnonymousFunctionBlock) {
                metaInfo_.setCache(new Cache(lastPage));
            }
            l_.setMetaInfo(metaInfo_);
        }
        return l_;
    }


}
