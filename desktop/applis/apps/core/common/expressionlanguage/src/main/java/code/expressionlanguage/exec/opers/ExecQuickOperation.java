package code.expressionlanguage.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.QuickOperation;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;


public abstract class ExecQuickOperation extends ExecMethodOperation implements AtomicExecCalculableOperation,CallExecSimpleOperation {

    private ClassMethodId classMethodId;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    private ImplicitMethods converter;
    public ExecQuickOperation(QuickOperation _q, ContextEl _context) {
        super(_q);
        classMethodId = _q.getClassMethodId();
        named = fetchFunction(_context,_q.getRootNumber(),_q.getMemberNumber());
        rootBlock = fetchType(_context,_q.getRootNumber());
        converter = fetchImplicits(_context,_q.getConverter());
    }
    @Override
    public final void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode first_ = chidren_.first();
        if (classMethodId != null) {
            ArgumentsPair argumentPair_ = getArgumentPair(_nodes, first_);
            if (argumentPair_.isArgumentTest()){
                Argument f_ = getArgument(_nodes,first_);
                setQuickConvertSimpleArgument(f_, _conf, _nodes);
                return;
            }
            setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
            CustList<Argument> arguments_ = getArguments(_nodes, this);
            CustList<Argument> firstArgs_ = ExecInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_);
            ExecInvokingOperation.checkParametersOperators(new DefaultExiting(_conf),_conf, classMethodId,rootBlock, named,Argument.createVoid(), firstArgs_);
            return;
        }
        Argument f_ = getArgument(_nodes,first_);
        Struct abs_ = f_.getStruct();
        if (match(abs_)) {
            setQuickConvertSimpleArgument(f_, _conf, _nodes);
            return;
        }
        ExecOperationNode last_ = chidren_.last();
        setRelativeOffsetPossibleLastPage(last_.getIndexInEl(), _conf);
        Argument a_ = getArgument(_nodes,last_);
        setSimpleArgument(a_, _conf, _nodes);
    }

    @Override
    public void endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode first_ = chidren_.first();
        ArgumentsPair argumentPair_ = getArgumentPair(_nodes, first_);
        if (argumentPair_.isArgumentTest()){
            setSimpleArgument(_right,_conf,_nodes);
            return;
        }
        ArgumentsPair pair_ = getArgumentPair(_nodes,this);
        setRelativeOffsetPossibleLastPage(getIndexInEl(),_conf);
        ImplicitMethods implicits_ = pair_.getImplicitsCompound();
        int indexImplicit_ = pair_.getIndexImplicitCompound();
        if (implicits_.isValidIndex(indexImplicit_)) {
            pair_.setIndexImplicitCompound(processConverter(_conf, _right, implicits_, indexImplicit_));
            return;
        }
        setSimpleArgument(_right,_conf,_nodes);
    }

    public ImplicitMethods getConverter() {
        return converter;
    }

    public abstract boolean match(Struct _struct);
}
