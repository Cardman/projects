package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.QuickOperation;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;


public abstract class RendQuickOperation extends RendMethodOperation implements RendCalculableOperation,RendCallable {

    private MethodAccessKind kind;
    private String className;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    private ImplicitMethods converter;
    public RendQuickOperation(QuickOperation _q, ContextEl _context) {
        super(_q);
        kind = ExecOperationNode.getKind(_q.getClassMethodId());
        className = ExecOperationNode.getType(_context,_q.getClassMethodId());
        named = ExecOperationNode.fetchFunction(_context,_q.getRootNumber(),_q.getMemberNumber());
        rootBlock = ExecOperationNode.fetchType(_context,_q.getRootNumber());
        converter = ExecOperationNode.fetchImplicits(_context,_q.getConverter(),_q.getRootNumberConv(),_q.getMemberNumberConv());
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
        RendDynOperationNode first_ = chidren_.first();
        if (named != null) {
            ArgumentsPair argumentPair_ = getArgumentPair(_nodes, first_);
            if (argumentPair_.isArgumentTest()){
                Argument f_ = getArgument(_nodes, first_);
                setQuickConvertSimpleArgument(f_, _conf,_nodes);
                return;
            }
            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
            Argument argres_ = processCall(this, this, Argument.createVoid(),_nodes, _conf, null);
            if (converter != null) {
                Argument res_ = tryConvert(converter.getRootBlock(),converter.get(0),converter.getOwnerClass(), argres_, _conf);
                if (res_ == null) {
                    return;
                }
                argres_ = res_;
            }
            setSimpleArgument(argres_,_conf,_nodes);
            return;
        }
        Argument f_ = getArgument(_nodes, first_);
        Struct abs_ = f_.getStruct();
        if (match(abs_)) {
            setQuickConvertSimpleArgument(f_, _conf,_nodes);
            return;
        }
        Argument a_ = getArgument(_nodes,chidren_.last());
        setSimpleArgument(a_, _conf,_nodes);
    }

    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right) {
        CustList<RendDynOperationNode> list_ = getChildrenNodes();
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, list_).getArguments();
        ExecInvokingOperation.checkParametersOperators(new AdvancedExiting(_conf),_conf.getContext(), rootBlock,named, first_, className, kind);
        return Argument.createVoid();
    }
    public abstract boolean match(Struct _struct);
}
