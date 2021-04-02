package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.MetaInfoUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecLambdaFieldContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.exec.opers.*;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendFieldLambdaOperation extends RendAbstractLambdaOperation {


    private final ExecLambdaFieldContent lambdaFieldContent;

    public RendFieldLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecLambdaFieldContent _lambdaFieldContent) {
        super(_opCont, _lamCont);
        lambdaFieldContent = _lambdaFieldContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        String returnFieldType_ = getReturnFieldType();
        String clArg_ = getResultClass().getSingleNameOrEmpty();
        String ownerType_ = getFoundClass();
        String formatType_ = returnFieldType_;
        String idCl_ = StringExpUtil.getIdFromAllTypes(ownerType_);
        String formCl_ = MetaInfoUtil.tryFormatType(idCl_, ownerType_, _context);
        Argument res_ = new Argument(ExecFieldLambdaOperation.newLambda(previous_, ownerType_, returnFieldType_, lambdaFieldContent.getClassField(), getAncestor(), lambdaFieldContent.isAffField(), lambdaFieldContent.isStaticField(), lambdaFieldContent.isFinalField(), isShiftArgument(), isSafeInstance(), clArg_, getFileName(), lambdaFieldContent.getRootBlock(), lambdaFieldContent.getInfoBlock(), formatType_, formCl_));
        setSimpleArgument(res_, _nodes, _context, _stack, _rendStack);
    }
}
