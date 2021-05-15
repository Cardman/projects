package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;


public abstract class RendQuickOperation extends RendMethodOperation implements RendCalculableOperation {

    private final ExecTypeFunction pair;
    private final ExecStaticEltContent staticEltContent;
    private final ImplicitMethods converter;
    private final ExecFormattedRootBlock formattedType;

    public RendQuickOperation(ExecOperationContent _content, ExecStaticEltContent _staticEltContent, ExecTypeFunction _pair, ImplicitMethods _converter) {
        super(_content);
        staticEltContent = _staticEltContent;
        pair = _pair;
        converter = _converter;
        formattedType = _staticEltContent.getFormattedType();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        RendDynOperationNode first_ = getFirstNode(this);
        if (pair.getFct() != null) {
            ArgumentsPair argumentPair_ = getArgumentPair(_nodes, first_);
            if (argumentPair_.isArgumentTest()){
                Argument f_ = getArgument(_nodes, first_);
                setQuickConvertSimpleArgument(f_, _nodes, _context, _stack);
                return;
            }
            setRelativeOffsetPossibleLastPage(getIndexInEl(), _rendStack);
            checkParametersOperatorsFormatted(_context.getExiting(), _context, pair, _nodes, formattedType, staticEltContent.getKind(), _stack);
            Argument argres_ = RendDynOperationNode.processCall(Argument.createVoid(), _context, _stack).getValue();
            if (converter != null) {
                Argument res_ = tryConvert(converter.get(0),converter.getOwnerClass(), argres_, _context, _stack);
                if (res_ == null) {
                    return;
                }
                argres_ = res_;
            }
            setSimpleArgument(argres_, _nodes, _context, _stack, _rendStack);
            return;
        }
        Argument f_ = getArgument(_nodes, first_);
        Struct abs_ = f_.getStruct();
        if (match(abs_)) {
            setQuickConvertSimpleArgument(f_, _nodes, _context, _stack);
            return;
        }
        Argument a_ = getArgument(_nodes,getLastNode(this));
        setSimpleArgument(a_, _nodes, _context, _stack, _rendStack);
    }

    public abstract boolean match(Struct _struct);
}
