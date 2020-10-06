package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.*;
import code.expressionlanguage.stds.StandardMethod;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
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
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument res_ = getCommonArgument(previous_, _context);
        setSimpleArgument(res_, _conf,_nodes, _context);
    }

    Argument getCommonArgument(Argument _previous, ContextEl _context) {
        String name_ = getResultClass().getSingleNameOrEmpty();
        if (standardMethod != null) {
            return new Argument(ExecStdMethodLambdaOperation.newLambda(_previous, _context, lambdaCommonContent.getFoundClass(),lambdaMethodContent.getMethod(), lambdaCommonContent.getReturnFieldType(),
                    lambdaCommonContent.isShiftArgument(), lambdaCommonContent.isSafeInstance(), name_, standardMethod));
        }
        if (lambdaMethodContent.getMethod() == null && lambdaConstructorContent.getRealId() == null) {
            return new Argument(ExecFieldLambdaOperation.newLambda(_previous, lambdaCommonContent.getFoundClass(), lambdaCommonContent.getReturnFieldType(), lambdaFieldContent.getClassField(), lambdaCommonContent.getAncestor(),
                    lambdaFieldContent.isAffField(), lambdaFieldContent.isStaticField(), lambdaFieldContent.isFinalField(), lambdaCommonContent.isShiftArgument(), lambdaCommonContent.isSafeInstance(), name_, lambdaCommonContent.getFileName(),lambdaFieldContent.getRootBlock(),lambdaFieldContent.getInfoBlock(), lambdaCommonContent.getReturnFieldType()));
        }
        if (lambdaMethodContent.getMethod() == null) {
            return new Argument(ExecConstructorLambdaOperation.newLambda(_previous, _context, lambdaCommonContent.getFoundClass(), lambdaConstructorContent.getRealId(), lambdaCommonContent.getReturnFieldType(),
                    lambdaCommonContent.isShiftArgument(), lambdaCommonContent.isSafeInstance(), name_, lambdaCommonContent.getFileName(), lambdaConstructorContent.getFunctionBlock(),lambdaConstructorContent.getRootBlock(), lambdaConstructorContent.getFunction()));
        }
        return new Argument(ExecMethodLambdaOperation.newLambda(_previous, _context, lambdaCommonContent.getFoundClass(), lambdaMethodContent.getMethod(), lambdaCommonContent.getReturnFieldType(), lambdaCommonContent.getAncestor(),
                lambdaMethodContent.isDirectCast(), lambdaMethodContent.isPolymorph(), lambdaMethodContent.isAbstractMethod(), lambdaMethodContent.isExpCast(), lambdaCommonContent.isShiftArgument(), lambdaCommonContent.isSafeInstance(), name_, lambdaCommonContent.getFileName(), lambdaMethodContent.getFunctionBlock(), lambdaMethodContent.getFunction(), lambdaMethodContent.getDeclaring()));
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return lambdaCommonContent.isIntermediate();
    }

}
