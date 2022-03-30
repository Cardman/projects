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
import code.util.IdMap;

public final class RendExplicitOperatorOperation extends RendSettableCallFctOperation implements RendCalculableOperation {

    private final ExecTypeFunction pair;
    private final ExecStaticFctContent staticFctContent;
    private final int offsetOper;

    public RendExplicitOperatorOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecStaticFctContent _staticFctContent, ExecTypeFunction _pair, int _offsetOper, ExecArrContent _arrContent) {
        super(_content, _intermediateDottedOperation, _arrContent);
        staticFctContent = _staticFctContent;
        pair = _pair;
        offsetOper = _offsetOper;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        setRelOffsetPossibleLastPage(offsetOper, _rendStack);
        ArgumentWrapper argres_ = checkParametersOperatorsFormatted(this, pair, staticFctContent, _nodes, _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

    static ArgumentWrapper checkParametersOperatorsFormatted(RendMethodOperation _curr, ExecTypeFunction _pair, ExecStaticFctContent _elt,IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        ExecFormattedRootBlock classNameFound_ = ExecFormattedRootBlock.formatType(_elt.getElts(), _rendStack);
        String lastType_ = ExecFormattedRootBlock.formatLastType(classNameFound_,_elt);
        ExecInvokingOperation.checkParametersOperatorsFormatted(_context.getExiting(), _context, _pair, ExecInvokingOperation.fectchArgs(lastType_, _elt.getNaturalVararg(), null, _context, _rendStack.getStackCall(), _curr.buildInfos(_nodes)), classNameFound_, _elt.getKind(), _rendStack.getStackCall());
        return RendDynOperationNode.processCall(Argument.createVoid(), _context, _rendStack);
    }

}
