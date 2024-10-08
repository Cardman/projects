package code.formathtml.exec.opers;

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
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendExplicitOperatorOperation extends RendSettableCallFctOperation implements RendCalculableOperation, CompoundedOperator {

    private final ExecTypeFunction pair;
    private final ExecStaticFctContent staticFctContent;
    private final ExecOperatorContent operatorContent;
    private final ImplicitMethods converter = new ImplicitMethods();

    public RendExplicitOperatorOperation(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecStaticFctContent _staticFctContent, ExecTypeFunction _pair, ExecOperatorContent _offsetOper, ExecArrContent _arrContent) {
        super(_content, _intermediateDottedOperation, _arrContent);
        staticFctContent = _staticFctContent;
        pair = _pair;
        operatorContent = _offsetOper;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        setRelOffsetPossibleLastPage(operatorContent.getOpOffset(), _rendStack);
        RendDynOperationNode first_ = getFirstNode(this);
        ArgumentsPair argumentPair_ = getArgumentPair(_nodes, first_);
        if (argumentPair_.isArgumentTest()){
            Struct f_ = getArgument(_nodes, first_);
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
        return RendDynOperationNode.processCall(NullStruct.NULL_VALUE, _context, _rendStack);
    }

    public ImplicitMethods getConverter() {
        return converter;
    }

    @Override
    public String getOper() {
        return operatorContent.getOper();
    }
}
