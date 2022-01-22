package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.opers.ExecRecordConstructorLambdaOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecLambdaCommonContent;
import code.expressionlanguage.fwd.opers.ExecNamedFieldContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendRecordConstructorLambdaOperation extends RendAbstractLambdaOperation {

    private final ExecRootBlock pair;
    private final CustList<ExecNamedFieldContent> namedFields;

    public RendRecordConstructorLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecRootBlock _pair, CustList<ExecNamedFieldContent> _namedFields) {
        super(_opCont, _lamCont);
        pair = _pair;
        namedFields = _namedFields;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        String clArg_ = formatVarTypeRes();
        ExecFormattedRootBlock ownerType_ = getFoundClass();
        Argument res_ = new Argument(ExecRecordConstructorLambdaOperation.newLambda(ownerType_, clArg_, pair, namedFields));
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }
}
