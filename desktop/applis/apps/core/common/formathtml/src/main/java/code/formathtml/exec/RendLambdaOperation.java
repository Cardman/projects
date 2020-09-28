package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.stds.StandardMethod;
import code.formathtml.Configuration;
import code.util.IdMap;

public final class RendLambdaOperation extends RendLeafOperation implements RendCalculableOperation,RendPossibleIntermediateDotted {

    private ExecLambdaCommonContent lambdaCommonContent;
    private ExecLambdaMethodContent lambdaMethodContent;
    private ExecLambdaConstructorContent lambdaConstructorContent;
    private ExecLambdaFieldContent lambdaFieldContent;
    private StandardMethod standardMethod;

    public RendLambdaOperation(ExecOperationContent _content, ExecLambdaCommonContent _lambdaCommonContent, ExecLambdaMethodContent _lambdaMethodContent, ExecLambdaConstructorContent _lambdaConstructorContent, ExecLambdaFieldContent _lambdaFieldContent, StandardMethod _standardMethod) {
        super(_content);
        lambdaCommonContent = _lambdaCommonContent;
        lambdaMethodContent = _lambdaMethodContent;
        lambdaConstructorContent = _lambdaConstructorContent;
        lambdaFieldContent = _lambdaFieldContent;
        standardMethod = _standardMethod;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument res_ = getCommonArgument(previous_, _conf);
        setSimpleArgument(res_, _conf,_nodes);
    }

    Argument getCommonArgument(Argument _previous, Configuration _conf) {
        String name_ = getResultClass().getSingleNameOrEmpty();
        PageEl pageEl_ = _conf.getPageEl();
        ContextEl context_ = _conf.getContext();
        if (standardMethod != null) {
            return new Argument(ExecStdMethodLambdaOperation.newLambda(_previous, context_, lambdaCommonContent.getFoundClass(),lambdaMethodContent.getMethod(), lambdaCommonContent.getReturnFieldType(),
                    lambdaCommonContent.isShiftArgument(), lambdaCommonContent.isSafeInstance(), name_, pageEl_, standardMethod));
        }
        if (lambdaMethodContent.getMethod() == null && lambdaConstructorContent.getRealId() == null) {
            return new Argument(ExecFieldLambdaOperation.newLambda(_previous, context_, lambdaCommonContent.getFoundClass(), lambdaCommonContent.getReturnFieldType(), lambdaFieldContent.getClassField(), lambdaCommonContent.getAncestor(),
                    lambdaFieldContent.isAffField(), lambdaFieldContent.isStaticField(), lambdaFieldContent.isFinalField(), lambdaCommonContent.isShiftArgument(), lambdaCommonContent.isSafeInstance(), name_, pageEl_, lambdaCommonContent.getFileName(),lambdaFieldContent.getRootBlock(),lambdaFieldContent.getInfoBlock()));
        }
        if (lambdaMethodContent.getMethod() == null) {
            return new Argument(ExecConstructorLambdaOperation.newLambda(_previous, context_, lambdaCommonContent.getFoundClass(), lambdaConstructorContent.getRealId(), lambdaCommonContent.getReturnFieldType(),
                    lambdaCommonContent.isShiftArgument(), lambdaCommonContent.isSafeInstance(), name_, pageEl_, lambdaCommonContent.getFileName(), lambdaConstructorContent.getFunctionBlock(),lambdaConstructorContent.getRootBlock(), lambdaConstructorContent.getFunction()));
        }
        return new Argument(ExecMethodLambdaOperation.newLambda(_previous, context_, lambdaCommonContent.getFoundClass(), lambdaMethodContent.getMethod(), lambdaCommonContent.getReturnFieldType(), lambdaCommonContent.getAncestor(),
                lambdaMethodContent.isDirectCast(), lambdaMethodContent.isPolymorph(), lambdaMethodContent.isAbstractMethod(), lambdaMethodContent.isExpCast(), lambdaCommonContent.isShiftArgument(), lambdaCommonContent.isSafeInstance(), name_, pageEl_, lambdaCommonContent.getFileName(), lambdaMethodContent.getFunctionBlock(), lambdaMethodContent.getFunction(), lambdaMethodContent.getDeclaring()));
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return lambdaCommonContent.isIntermediate();
    }

}
