package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendCustNumericOperation extends RendNumericOperation {

    private final ExecTypeFunction pair;
    private final ExecStaticEltContent staticEltContent;
    private final ExecFormattedRootBlock formattedType;

    public RendCustNumericOperation(ExecTypeFunction _pair, ExecOperationContent _content, int _opOffset, ExecStaticEltContent _staticEltContent) {
        super(_content, _opOffset);
        staticEltContent = _staticEltContent;
        pair = _pair;
        formattedType = _staticEltContent.getFormattedType();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+getOpOffset(), _rendStack);
        checkParametersOperatorsFormatted(_context.getExiting(), _context, pair, _nodes, formattedType, staticEltContent.getKind(), _rendStack);
        ArgumentWrapper argres_ = RendDynOperationNode.processCall(Argument.createVoid(), _context, _rendStack);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

}
