package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaMethodContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.LambdaMethodStruct;
import code.expressionlanguage.structs.MethodMetaInfo;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public final class ExecEnumMethodLambdaOperation extends ExecAbstractLambdaOperation {

    private final ExecLambdaMethodContent lambdaMethodContent;
    private final ExecRootBlock declaring;

    public ExecEnumMethodLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaMethodContent _lambdaMethodContent, ExecRootBlock _declaring) {
        super(_opCont, _lamCont);
        lambdaMethodContent = _lambdaMethodContent;
        declaring = _declaring;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument previous_ = getPreviousArg(this, _nodes, _stack);
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        Argument res_ = new Argument(newLambda(getLambdaCommonContent(),lambdaMethodContent,previous_,_conf, clArg_, declaring));
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    public static Struct newLambda(ExecLambdaCommonContent _common, ExecLambdaMethodContent _meth, Argument _previous,
                                   ContextEl _conf, String _clArg, ExecRootBlock _type) {
        String idCl_ = StringExpUtil.getIdFromAllTypes(_common.getFoundClass());
        MethodMetaInfo metaInfo_ = new MethodMetaInfo(_conf,_common,_common.getFoundClass(),idCl_, _meth.getMethod().getConstraints(), new ExecTypeFunction(_type,null));
        return new LambdaMethodStruct(metaInfo_,_previous,_common,_meth,_clArg, _common.getFoundClass());
    }


}
