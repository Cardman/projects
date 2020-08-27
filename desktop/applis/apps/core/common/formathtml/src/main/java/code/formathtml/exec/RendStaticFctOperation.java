package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.analyze.opers.AbstractCallFctOperation;
import code.expressionlanguage.analyze.opers.InvokingOperation;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendStaticFctOperation extends RendInvokingOperation implements RendCalculableOperation,RendCallable {
    private String methodName;

    private ClassMethodId classMethodId;

    private String lastType;

    private int naturalVararg;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    public RendStaticFctOperation(InvokingOperation _inv, AbstractCallFctOperation _s, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock) {
        super(_inv);
        methodName = _s.getMethodName();
        classMethodId = _s.getClassMethodId();
        lastType = _s.getLastType();
        naturalVararg = _s.getNaturalVararg();
        named = _named;
        rootBlock = _rootBlock;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument argres_ = processCall(this, this, previous_, arguments_, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    @Override
    public Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf, Argument _right) {
        return getArgument(_arguments,_conf);
    }
    Argument getArgument(CustList<Argument> _arguments, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        CustList<Argument> firstArgs_;
        MethodId methodId_ = classMethodId.getConstraints();
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        Argument prev_ = new Argument();
        classNameFound_ = classMethodId.getClassName();
        firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments);
        if (_conf.hasToExit(classNameFound_)) {
            return Argument.createVoid();
        }
        return ExecInvokingOperation.callPrepare(new AdvancedExiting(_conf),_conf.getContext(), classNameFound_,rootBlock, methodId_, prev_, firstArgs_, null,named);
    }
}
