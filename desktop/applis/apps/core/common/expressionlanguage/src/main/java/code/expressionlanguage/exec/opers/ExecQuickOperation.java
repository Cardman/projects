package code.expressionlanguage.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;


public abstract class ExecQuickOperation extends ExecMethodOperation implements AtomicExecCalculableOperation,CallExecSimpleOperation {

    private ExecStaticEltContent staticEltContent;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    private ImplicitMethods converter;
    private int opOffset;
    protected ExecQuickOperation(ExecOperationContent _opCont, ExecStaticEltContent _staticEltContent, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ImplicitMethods _converter, int _opOffset) {
        super(_opCont);
        staticEltContent = _staticEltContent;
        named = _named;
        rootBlock = _rootBlock;
        converter = _converter;
        opOffset = _opOffset;
    }
    @Override
    public final void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                ContextEl _conf) {
        ExecOperationNode first_ = getFirstChild();
        if (named != null) {
            ArgumentsPair argumentPair_ = ExecTemplates.getArgumentPair(_nodes, first_);
            if (argumentPair_.isArgumentTest()){
                Argument f_ = getArgument(_nodes,first_);
                setQuickConvertSimpleArgument(f_, _conf, _nodes);
                return;
            }
            setRelativeOffsetPossibleLastPage(_conf);
            CustList<Argument> arguments_ = getArguments(_nodes, this);
            CustList<ExecOperationNode> chidren_ = getChildrenNodes();
            CustList<Argument> firstArgs_ = ExecInvokingOperation.listArguments(chidren_, -1, EMPTY_STRING, arguments_);
            ExecInvokingOperation.checkParametersOperators(_conf.getExiting(),_conf, rootBlock, named, firstArgs_, staticEltContent.getClassName(), staticEltContent.getKind());
            return;
        }
        Argument f_ = getArgument(_nodes,first_);
        Struct abs_ = f_.getStruct();
        if (match(abs_)) {
            setQuickConvertSimpleArgument(f_, _conf, _nodes);
            return;
        }
        setRelativeOffsetPossibleLastPage(opOffset, _conf);
        Argument a_ = getLastArgument(_nodes,this);
        setSimpleArgument(a_, _conf, _nodes);
    }

    @Override
    public void endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        ExecOperationNode first_ = getFirstChild();
        ArgumentsPair argumentPair_ = ExecTemplates.getArgumentPair(_nodes, first_);
        if (argumentPair_.isArgumentTest()){
            setSimpleArgument(_right,_conf,_nodes);
            return;
        }
        ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes,this);
        setRelativeOffsetPossibleLastPage(_conf);
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
