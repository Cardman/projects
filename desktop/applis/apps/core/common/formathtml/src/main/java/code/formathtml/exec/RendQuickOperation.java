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
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;


public abstract class RendQuickOperation extends RendMethodOperation implements RendCalculableOperation,RendCallable {

    private ClassMethodId classMethodId;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    private ImplicitMethods converter;
    public RendQuickOperation(QuickOperation _q, ContextEl _context) {
        super(_q);
        classMethodId = _q.getClassMethodId();
        named = ExecOperationNode.fetchFunction(_context,_q.getRootNumber(),_q.getMemberNumber());
        rootBlock = ExecOperationNode.fetchType(_context,_q.getRootNumber());
        converter = ExecOperationNode.fetchImplicits(_context,_q.getConverter());
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
        RendDynOperationNode first_ = chidren_.first();
        if (classMethodId != null) {
            ArgumentsPair argumentPair_ = getArgumentPair(_nodes, first_);
            if (argumentPair_.isArgumentTest()){
                Argument f_ = getArgument(_nodes, first_);
                setQuickConvertSimpleArgument(f_, _conf,_nodes);
                return;
            }
            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
            CustList<Argument> arguments_ = getArguments(_nodes,this);
            CustList<Argument> firstArgs_ = RendInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_);
            Argument argres_ = processCall(this, this, Argument.createVoid(), firstArgs_, _conf, null);
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
    public Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf, Argument _right) {
        ExecInvokingOperation.checkParametersOperators(new AdvancedExiting(_conf),_conf.getContext(),classMethodId,rootBlock,named,_previous,_arguments);
        return Argument.createVoid();
    }
    public abstract boolean match(Struct _struct);
}
