package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.ChoiceFctOperation;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecChoiceFctOperation extends ExecInvokingOperation {

    private String methodName;

    private String className;

    private String lastType;

    private int naturalVararg;
    private int anc;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;

    public ExecChoiceFctOperation(ChoiceFctOperation _choice, ExecNamedFunctionBlock _named, ExecRootBlock _rootBloc) {
        super(_choice);
        methodName = _choice.getMethodName();
        className = ExecOperationNode.getType(_choice.getClassMethodId());
        lastType = _choice.getLastType();
        naturalVararg = _choice.getNaturalVararg();
        anc = _choice.getAnc();
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
        CustList<Argument> firstArgs_ = getArgs(_nodes, _conf, prev_.getStruct());
        return callPrepare(new DefaultExiting(_conf),_conf, classNameFound_,rootBlock, prev_, firstArgs_, null,getNamed(), MethodAccessKind.INSTANCE, "");
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct _pr) {
        return fetchFormattedArgs(_nodes, _conf, _pr, getClassName(), rootBlock, lastType, naturalVararg);
    }

    public ExecNamedFunctionBlock getNamed() {
        return named;
    }

    public String getClassName() {
        return className;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

}
