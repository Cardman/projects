package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.analyze.opers.AbstractCallFctOperation;
import code.expressionlanguage.analyze.opers.InvokingOperation;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ArgumentList;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecStaticFctOperation extends ExecInvokingOperation {

    private String methodName;

    private MethodAccessKind kind;
    private String className;

    private String lastType;

    private int naturalVararg;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    public ExecStaticFctOperation(InvokingOperation _inv, AbstractCallFctOperation _s, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock) {
        super(_inv);
        methodName = _s.getMethodName();
        kind = getKind(_s.getClassMethodId());
        className = ExecOperationNode.getType(_s.getClassMethodId());
        lastType = _s.getLastType();
        naturalVararg = _s.getNaturalVararg();
        named = _named;
        rootBlock = _rootBlock;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument res_ = getArgument(_nodes, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }
    Argument getArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        String classNameFound_ = ClassMethodId.formatType(className,_conf, kind);
        CustList<Argument> firstArgs_ = getArgs(_nodes, classNameFound_);
        if (ExecutingUtil.hasToExit(_conf,classNameFound_)) {
            return Argument.createVoid();
        }
        Argument prev_ = new Argument();
        return callPrepare(new DefaultExiting(_conf),_conf, classNameFound_,rootBlock, prev_, firstArgs_, null,getNamed(), kind, "");
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String classNameFound_) {
        String lastType_ = ClassMethodId.formatType(rootBlock,classNameFound_,lastType, kind);
        return fectchArgs(_nodes,lastType_,naturalVararg);
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
