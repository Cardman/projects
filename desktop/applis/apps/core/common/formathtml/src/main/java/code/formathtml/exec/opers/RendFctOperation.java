package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendFctOperation extends RendInvokingOperation implements RendCalculableOperation,RendCallable {

    private ExecInstFctContent instFctContent;

    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    public RendFctOperation(ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ExecOperationContent _content, ExecInstFctContent _instFctContent, boolean _intermediateDottedOperation) {
        super(_content, _intermediateDottedOperation);
        instFctContent = _instFctContent;
        named = _named;
        rootBlock = _rootBlock;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument argres_ = processCall(this, this, previous_,_nodes, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        CustList<Argument> firstArgs_;
        String lastType_ = getLastType();
        int naturalVararg_ = getNaturalVararg();
        String classNameFound_;
        classNameFound_ = instFctContent.getClassName();
        ContextEl ctx_ = _conf.getContext();
        Argument prev_ = new Argument(ExecTemplates.getParent(getAnc(), classNameFound_, _previous.getStruct(), ctx_));
        if (ctx_.callsOrException()) {
            return new Argument();
        }
        String base_ = StringExpUtil.getIdFromAllTypes(classNameFound_);
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, chidren_).getArguments();
        Struct pr_ = prev_.getStruct();
        String cl_ = pr_.getClassName(ctx_);
        String clGen_ = ExecTemplates.getSuperGeneric(cl_, base_, ctx_);
        lastType_ = ExecTemplates.quickFormat(rootBlock, clGen_, lastType_);
        firstArgs_ = RendInvokingOperation.listArguments(chidren_, naturalVararg_, lastType_, first_);
        ExecOverrideInfo polymorph_ =  ExecInvokingOperation.polymorphOrSuper(isStaticChoiceMethod(),ctx_,pr_,classNameFound_,rootBlock,named);
        ExecNamedFunctionBlock fct_ = polymorph_.getOverridableBlock();
        ExecRootBlock type_ = polymorph_.getRootBlock();
        classNameFound_ = polymorph_.getClassName();
        return ExecInvokingOperation.callPrepare(ctx_.getExiting(), ctx_, classNameFound_,type_, prev_, firstArgs_, null,fct_, MethodAccessKind.INSTANCE,"");
    }

    public int getNaturalVararg() {
        return instFctContent.getNaturalVararg();
    }

    public int getAnc() {
        return instFctContent.getAnc();
    }

    public String getLastType() {
        return instFctContent.getLastType();
    }

    public String getMethodName() {
        return instFctContent.getMethodName();
    }

    public boolean isStaticChoiceMethod() {
        return instFctContent.isStaticChoiceMethod();
    }

}
