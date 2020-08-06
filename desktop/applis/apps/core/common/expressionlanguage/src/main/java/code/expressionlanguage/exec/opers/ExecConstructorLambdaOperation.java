package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.LambdaOperation;
import code.expressionlanguage.common.AccessEnum;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.blocks.ExecAnnotableParametersBlock;
import code.expressionlanguage.exec.blocks.ExecMemberCallingsBlock;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.structs.*;
import code.util.IdMap;

public final class ExecConstructorLambdaOperation extends ExecAbstractLambdaOperation {

    private ConstructorId realId;
    private ExecAnnotableParametersBlock functionBlock;
    private ExecMemberCallingsBlock function;

    public ExecConstructorLambdaOperation(LambdaOperation _l, ContextEl _cont) {
        super(_l);
        realId = _l.getRealId();
        functionBlock = fetchFunction(_l, _cont);
        function = fetchFunction(_l, _cont);
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
        arg_.setStruct(newLambda(_previous,_conf, getFoundClass(), realId, getReturnFieldType()));
        return arg_;
    }

    private Struct newLambda(Argument _previous, ContextEl _conf, String foundClass, ConstructorId realId, String returnFieldType) {
        return newLambda(_previous, _conf, foundClass, realId, returnFieldType, isShiftArgument(), isSafeInstance(), getResultClass().getName(), _conf.getLastPage(), getFileName(),functionBlock,function);
    }

    public static Struct newLambda(Argument _previous, ContextEl _conf, String foundClass, ConstructorId realId, String returnFieldType, boolean shiftArgument, boolean safeInstance, String name, PageEl lastPage, String fileName,ExecAnnotableParametersBlock functionBlock, ExecMemberCallingsBlock function) {
        String clArg_ = name;
        String ownerType_ = foundClass;
        ownerType_ = lastPage.formatVarType(ownerType_, _conf);
        clArg_ = lastPage.formatVarType(clArg_, _conf);
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
            l_.setMetaInfo(met_);
        }
        return l_;
    }

}
