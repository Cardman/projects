package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class ExecSuperFctOperation extends ExecInvokingOperation {

    private ExecInstFctContent instFctContent;

    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    public ExecSuperFctOperation(ExecNamedFunctionBlock _named, ExecRootBlock _rootBloc, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInstFctContent _instFctContent) {
        super(_opCont, _intermediateDottedOperation);
        instFctContent = _instFctContent;
        named = _named;
        rootBlock = _rootBloc;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_,_nodes, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }
    Argument getArgument(Argument _previous, IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        int off_ = StringUtil.getFirstPrintableCharIndex(instFctContent.getMethodName());
        setRelOffsetPossibleLastPage(off_, _conf);
        String classNameFound_ = getClassName();
        Argument prev_ = new Argument(ExecTemplates.getParent(instFctContent.getAnc(), classNameFound_, _previous.getStruct(), _conf));
        if (_conf.callsOrException()) {
            return new Argument();
        }
        Struct pr_ = prev_.getStruct();
        CustList<Argument> firstArgs_ = getArgs(_nodes, _conf, pr_);
        return callPrepare(_conf.getExiting(), _conf, classNameFound_, rootBlock, prev_,null, firstArgs_, null, getNamed(), MethodAccessKind.INSTANCE, "");
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct _pr) {
        return fetchFormattedArgs(_nodes,_conf,_pr,getClassName(),rootBlock, instFctContent.getLastType(), instFctContent.getNaturalVararg());
    }

    public String getClassName() {
        return instFctContent.getClassName();
    }

    public ExecNamedFunctionBlock getNamed() {
        return named;
    }


    public int getNaturalVararg() {
        return instFctContent.getNaturalVararg();
    }

}
