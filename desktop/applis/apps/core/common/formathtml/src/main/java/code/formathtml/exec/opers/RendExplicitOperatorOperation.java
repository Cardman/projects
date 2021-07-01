package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendExplicitOperatorOperation extends RendSettableCallFctOperation implements RendCalculableOperation {

    private final ExecTypeFunction pair;
    private final ExecStaticFctContent staticFctContent;
    private final int offsetOper;
    private final ExecFormattedRootBlock formattedType;

    public RendExplicitOperatorOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecStaticFctContent _staticFctContent, ExecTypeFunction _pair, int _offsetOper, ExecArrContent _arrContent) {
        super(_content, _intermediateDottedOperation, _arrContent);
        staticFctContent = _staticFctContent;
        pair = _pair;
        offsetOper = _offsetOper;
        formattedType = _staticFctContent.getFormattedType();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        setRelOffsetPossibleLastPage(offsetOper, _rendStack);
        ExecInvokingOperation.checkParametersOperatorsFormatted(_context.getExiting(), _context, pair, ExecInvokingOperation.fectchArgs(staticFctContent.getLastType(), staticFctContent.getNaturalVararg(), null, _context, _rendStack.getStackCall(), buildInfos(_nodes)), formattedType, staticFctContent.getKind(), _rendStack.getStackCall());
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(Argument.createVoid(), _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

}
