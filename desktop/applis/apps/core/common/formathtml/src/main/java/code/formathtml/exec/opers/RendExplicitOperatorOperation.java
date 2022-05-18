package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.opers.CompoundedOperator;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticFctContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendExplicitOperatorOperation extends RendSettableCallFctOperation implements RendCalculableOperation, CompoundedOperator {

    private final ExecTypeFunction pair;
    private final ExecStaticFctContent staticFctContent;
    private final ExecOperatorContent operatorContent;
    private final ImplicitMethods converter;

    public RendExplicitOperatorOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecStaticFctContent _staticFctContent, ExecTypeFunction _pair, ExecOperatorContent _offsetOper, ExecArrContent _arrContent, ImplicitMethods _converter) {
        super(_content, _intermediateDottedOperation, _arrContent);
        staticFctContent = _staticFctContent;
        pair = _pair;
        operatorContent = _offsetOper;
        converter = _converter;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        setRelOffsetPossibleLastPage(operatorContent.getOpOffset(), _rendStack);
        RendDynOperationNode first_ = getFirstNode(this);
        ArgumentsPair argumentPair_ = getArgumentPair(_nodes, first_);
        if (argumentPair_.isArgumentTest()){
            Argument f_ = getArgument(_nodes, first_);
            setQuickConvertSimpleArgument(f_, _nodes, _context, _rendStack);
            return;
        }
        ArgumentWrapper argres_ = checkParametersOperatorsFormatted(this, pair, staticFctContent, _nodes, _context, _rendStack);
        setWrapper(_nodes, argres_);
        RendQuickOperation.endCalculate(this,_nodes,_context,_rendStack,argres_.getValue(), converter);
    }

    static ArgumentWrapper checkParametersOperatorsFormatted(RendMethodOperation _curr, ExecTypeFunction _pair, ExecStaticFctContent _elt,IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        ExecFormattedRootBlock classNameFound_ = ExecFormattedRootBlock.formatType(_elt.getElts(), _rendStack);
        String lastType_ = ExecFormattedRootBlock.formatLastType(classNameFound_,_elt);
        ParamCheckerUtil.checkParametersOperatorsFormatted(_context.getExiting(), _context, _pair, ExecInvokingOperation.fectchArgs(lastType_, _elt.getNaturalVararg(), _context, _rendStack.getStackCall(), _curr.buildInfos(_nodes)), classNameFound_, _elt.getKind(), _rendStack.getStackCall());
        return RendDynOperationNode.processCall(Argument.createVoid(), _context, _rendStack);
    }

    @Override
    public String getOper() {
        return operatorContent.getOper();
    }
}
