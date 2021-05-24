package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;


public final class RendQuickCustOperation extends RendQuickOperation implements RendCalculableOperation {

    private final ExecTypeFunction pair;
    private final ExecStaticEltContent staticEltContent;
    private final ImplicitMethods converter;
    private final ExecFormattedRootBlock formattedType;

    public RendQuickCustOperation(ExecOperationContent _content, ExecStaticEltContent _staticEltContent, ExecTypeFunction _pair, ImplicitMethods _converter, boolean _absorbingValue) {
        super(_content, _absorbingValue);
        staticEltContent = _staticEltContent;
        pair = _pair;
        converter = _converter;
        formattedType = _staticEltContent.getFormattedType();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        RendDynOperationNode first_ = getFirstNode(this);
        ArgumentsPair argumentPair_ = getArgumentPair(_nodes, first_);
        if (argumentPair_.isArgumentTest()){
            Argument f_ = getArgument(_nodes, first_);
            setQuickConvertSimpleArgument(f_, _nodes, _context, _rendStack);
            return;
        }
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _rendStack);
        checkParametersOperatorsFormatted(_context.getExiting(), _context, pair, _nodes, formattedType, staticEltContent.getKind(), _rendStack);
        Argument argres_ = RendDynOperationNode.processCall(Argument.createVoid(), _context, _rendStack).getValue();
        if (converter != null) {
            Argument res_ = tryConvert(converter.get(0),converter.getOwnerClass(), argres_, _context, _rendStack);
            if (res_ == null) {
                return;
            }
            argres_ = res_;
        }
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }
}
