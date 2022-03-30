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
import code.util.CustList;
import code.util.IdMap;

public final class RendRecordConstructorLambdaOperation extends RendAbstractLambdaOperation {

    private final ExecRootBlock pair;
    private final CustList<ExecNamedFieldContent> namedFields;
    private final CustList<ExecFormattedRootBlock> supInts;

    public RendRecordConstructorLambdaOperation(ExecOperationContent _opCont, ExecLambdaCommonContent _lamCont, ExecRootBlock _pair, CustList<ExecNamedFieldContent> _namedFields, CustList<ExecFormattedRootBlock> _supIntsList) {
        super(_opCont, _lamCont);
        pair = _pair;
        namedFields = _namedFields;
        supInts = _supIntsList;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this, _nodes, _rendStack);
        String clArg_ = formatVarTypeRes(_rendStack);
        ExecFormattedRootBlock ownerType_ = formatVarType(_rendStack);
        Argument res_ = new Argument(ExecRecordConstructorLambdaOperation.newLambda(getLambdaCommonContent(),previous_,ownerType_, clArg_, pair, namedFields, supInts));
        setSimpleArgument(res_, _nodes, _context, _rendStack);
    }
}
