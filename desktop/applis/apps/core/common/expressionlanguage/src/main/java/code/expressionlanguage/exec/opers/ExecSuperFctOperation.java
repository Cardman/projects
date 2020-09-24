package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentList;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.SuperFctOperation;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecSuperFctOperation extends ExecInvokingOperation {

    private String methodName;

    private String className;

    private String lastType;

    private int naturalVararg;
    private int anc;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    public ExecSuperFctOperation(SuperFctOperation _s, ExecNamedFunctionBlock _named, ExecRootBlock _rootBloc) {
        super(_s);
        methodName = _s.getMethodName();
        className = ExecOperationNode.getType(_s.getClassMethodId());
        lastType = _s.getLastType();
        naturalVararg = _s.getNaturalVararg();
        anc = _s.getAnc();
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
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String classNameFound_ = getClassName();
        Argument prev_ = new Argument(ExecTemplates.getParent(anc, classNameFound_, _previous.getStruct(), _conf));
        if (_conf.callsOrException()) {
            return new Argument();
        }
        Struct pr_ = prev_.getStruct();
        CustList<Argument> firstArgs_ = getArgs(_nodes, _conf, pr_);
        return callPrepare(new DefaultExiting(_conf),_conf, classNameFound_,rootBlock, prev_, firstArgs_, null,getNamed(), MethodAccessKind.INSTANCE, "");
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct pr_) {
        return fetchFormattedArgs(_nodes,_conf,pr_,getClassName(),rootBlock,lastType,naturalVararg);
    }

    public String getClassName() {
        return className;
    }

    public ExecNamedFunctionBlock getNamed() {
        return named;
    }


    public int getNaturalVararg() {
        return naturalVararg;
    }

}
