package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecCastOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.fwd.opers.ExecInvokingConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendInterfaceFctConstructor extends RendInvokingOperation implements RendCalculableOperation,RendCallable {
    private String className;

    private ExecInvokingConstructorContent invokingConstructorContent;

    private ExecRootBlock rootBlock;
    private ExecNamedFunctionBlock ctor;
    public RendInterfaceFctConstructor(ExecOperationContent _content, boolean _intermediateDottedOperation, ExecInvokingConstructorContent _invokingConstructorContent, String _className, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _ctor) {
        super(_content, _intermediateDottedOperation);
        invokingConstructorContent = _invokingConstructorContent;
        className = _className;
        rootBlock = _rootBlock;
        ctor = _ctor;

    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        if (getParent().getFirstChild().getNextSibling() == this) {
            //init and test
            int order_ = getParent().getFirstChild().getOrder();
            Argument lda_ = new Argument(Argument.getNullableValue(_nodes.getValue(order_).getArgument()).getStruct());
            ContextEl context_ = _conf.getContext();
            if (!ExecTemplates.checkObject(context_.getStandards().getContent().getReflect().getAliasFct(), lda_, context_)) {
                setSimpleArgument(Argument.createVoid(), _conf, _nodes);
                return;
            }
            String form_ = _conf.getPageEl().formatVarType(className, context_);
            Argument ref_ = new Argument(lda_.getStruct());
            ExecCastOperation.wrapFct(form_,true, context_, ref_);
            if (!ExecTemplates.checkObject(form_, ref_, context_)) {
                setSimpleArgument(Argument.createVoid(), _conf, _nodes);
                return;
            }
            _nodes.getValue(getParent().getFirstChild().getOrder()).setArgument(ref_);
            Argument argres_ = processCall(this, this, ref_,_nodes, _conf, null);
            setSimpleArgument(argres_,_conf,_nodes);
            return;
        }
        int order_ = getParent().getFirstChild().getOrder();
        Argument argres_ = processCall(this, this, Argument.getNullableValue(_nodes.getValue(order_).getArgument()),_nodes, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }
    Argument getArgument(IdMap<RendDynOperationNode, ArgumentsPair> _all,Argument _arguments, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ invokingConstructorContent.getOffsetOper(), _conf);
        CustList<Argument> firstArgs_;
        String superClass_ = _conf.getPageEl().formatVarType(invokingConstructorContent.getClassFromName(),_conf.getContext());
        String lastType_ = getLastType();
        lastType_ = ExecTemplates.quickFormat(rootBlock,superClass_, lastType_);
        int natvararg_ = getNaturalVararg();
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, chidren_).getArguments();
        firstArgs_ = listArguments(chidren_, natvararg_, lastType_, first_);
        ExecInvokingOperation.checkParametersCtors(_conf.getContext(), superClass_, rootBlock,ctor, _arguments, firstArgs_, InstancingStep.USING_SUPER);
        return Argument.createVoid();
    }

    public String getLastType() {
        return invokingConstructorContent.getLastType();
    }

    public int getNaturalVararg() {
        return invokingConstructorContent.getNaturalVararg();
    }

    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right) {
        return getArgument(_all,_previous,_conf);
    }
}
