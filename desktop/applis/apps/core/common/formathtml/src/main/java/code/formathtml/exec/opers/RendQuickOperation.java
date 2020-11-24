package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;


public abstract class RendQuickOperation extends RendMethodOperation implements RendCalculableOperation {

    private final ExecTypeFunction pair;
    private ExecStaticEltContent staticEltContent;
    private ImplicitMethods converter;
    public RendQuickOperation(ExecOperationContent _content, ExecStaticEltContent _staticEltContent, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ImplicitMethods _converter) {
        super(_content);
        staticEltContent = _staticEltContent;
        pair = new ExecTypeFunction(_rootBlock,_named);
        converter = _converter;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        RendDynOperationNode first_ = getFirstNode(this);
        if (pair.getFct() != null) {
            ArgumentsPair argumentPair_ = getArgumentPair(_nodes, first_);
            if (argumentPair_.isArgumentTest()){
                Argument f_ = getArgument(_nodes, first_);
                setQuickConvertSimpleArgument(f_, _nodes, _context);
                return;
            }
            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
            Argument argres_ = RendDynOperationNode.processCall(getArgument(_nodes, _context), _context);
            if (converter != null) {
                Argument res_ = tryConvert(converter.get(0),converter.getOwnerClass(), argres_, _context);
                if (res_ == null) {
                    return;
                }
                argres_ = res_;
            }
            setSimpleArgument(argres_,_conf,_nodes, _context);
            return;
        }
        Argument f_ = getArgument(_nodes, first_);
        Struct abs_ = f_.getStruct();
        if (match(abs_)) {
            setQuickConvertSimpleArgument(f_, _nodes, _context);
            return;
        }
        Argument a_ = getArgument(_nodes,getLastNode(this));
        setSimpleArgument(a_, _conf,_nodes, _context);
    }

    private Argument getArgument(IdMap<RendDynOperationNode, ArgumentsPair> _all, ContextEl _context) {
        RendInvokingOperation.checkParametersOperatorsFormatted(_context.getExiting(),_context, pair, _all,this, staticEltContent.getClassName(), staticEltContent.getKind());
        return Argument.createVoid();
    }

    public abstract boolean match(Struct _struct);
}
