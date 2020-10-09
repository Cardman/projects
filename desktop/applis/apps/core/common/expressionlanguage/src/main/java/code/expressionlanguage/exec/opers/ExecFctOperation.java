package code.expressionlanguage.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecFctOperation extends ExecInvokingOperation {

    private ExecInstFctContent instFctContent;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;

    public ExecFctOperation(ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstFctContent _instFctContent) {
        super(_opCont, _intermediateDottedOperation);
        instFctContent = _instFctContent;
        named = _named;
        rootBlock = _rootBlock;
    }

    public ExecFctOperation(ExecClassArgumentMatching _res,
                            ClassMethodId _classMethodId,
                            int _child, int _order, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock) {
        super(_child,_res,_order,true);
        instFctContent = new ExecInstFctContent(_classMethodId);
        named = _named;
        rootBlock = _rootBlock;
    }
    @Override
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_,_nodes, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    Argument getArgument(Argument _previous, IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        int off_ = StringUtil.getFirstPrintableCharIndex(instFctContent.getMethodName());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String classNameFound_ = getClassName();
        Struct argPrev_ = _previous.getStruct();
        Argument prev_ = new Argument(ExecTemplates.getParent(instFctContent.getAnc(), classNameFound_, argPrev_, _conf));
        if (_conf.callsOrException()) {
            return new Argument();
        }
        Struct pr_ = prev_.getStruct();
        CustList<Argument> firstArgs_ = getArgs(_nodes, _conf, pr_);
        ExecOverrideInfo polymorph_ = polymorphOrSuper(instFctContent.isStaticChoiceMethod(),_conf,pr_,classNameFound_,rootBlock,getNamed());
        ExecNamedFunctionBlock fct_ = polymorph_.getOverridableBlock();
        ExecRootBlock type_ = polymorph_.getRootBlock();
        classNameFound_ = polymorph_.getClassName();
        return callPrepare(_conf.getExiting(),_conf, classNameFound_,type_, prev_, firstArgs_, null,fct_, MethodAccessKind.INSTANCE, "");
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct pr_) {
        return fetchFormattedArgs(_nodes,_conf,pr_,getClassName(),rootBlock, instFctContent.getLastType(), instFctContent.getNaturalVararg());
    }

    public ExecNamedFunctionBlock getNamed() {
        return named;
    }

    public String getClassName() {
        return instFctContent.getClassName();
    }

    public int getNaturalVararg() {
        return instFctContent.getNaturalVararg();
    }

}
