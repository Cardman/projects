package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.LambdaOperation;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecStdMethodLambdaOperation extends ExecAbstractLambdaOperation {

    private ClassMethodId method;
    private StandardMethod function;

    public ExecStdMethodLambdaOperation(LambdaOperation _l) {
        super(_l);
        method = _l.getMethod();
        function = _l.getStandardMethod();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getCommonArgument(previous_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getCommonArgument(Argument _previous, ContextEl _conf) {
        Argument arg_ = new Argument();
        arg_.setStruct(newLambda(_previous,_conf, getFoundClass(), method, getReturnFieldType()));
        return arg_;
    }

    private Struct newLambda(Argument _previous, ContextEl _conf, String foundClass, ClassMethodId method, String returnFieldType) {
        return newLambda(_previous, _conf, foundClass, method, returnFieldType, isShiftArgument(), isSafeInstance(), getResultClass().getName(), _conf.getLastPage(), function);
    }

    public static Struct newLambda(Argument _previous, ContextEl _conf, String foundClass, ClassMethodId method, String returnFieldType, boolean shiftArgument, boolean safeInstance, String name, PageEl lastPage, StandardMethod function) {
        String clArg_ = name;
        String ownerType_ = foundClass;
        ownerType_ = lastPage.formatVarType(ownerType_, _conf);
        clArg_ = lastPage.formatVarType(clArg_, _conf);
        MethodId id_ = method.getConstraints();
        LambdaMethodStruct l_ = new LambdaMethodStruct(clArg_, ownerType_, id_, false, shiftArgument, 0, false);
        l_.setInstanceCall(_previous);
        l_.setSafeInstance(safeInstance);
        MethodId fid_ = ExecutingUtil.tryFormatId(ownerType_, _conf, id_);
        String className_;
        className_ = StringExpUtil.getIdFromAllTypes(ownerType_);
        String from_ = className_;
        String idCl_ = StringExpUtil.getIdFromAllTypes(ownerType_);
        String formCl_ = ExecutingUtil.tryFormatType(idCl_, ownerType_, _conf);
        MethodModifier met_ = function.getModifier();
        MethodMetaInfo metaInfo_ = new MethodMetaInfo(AccessEnum.PUBLIC, from_, id_, met_, returnFieldType, fid_, formCl_);
        metaInfo_.setStdCallee(function);
        l_.setMetaInfo(metaInfo_);
        return l_;
    }


}
