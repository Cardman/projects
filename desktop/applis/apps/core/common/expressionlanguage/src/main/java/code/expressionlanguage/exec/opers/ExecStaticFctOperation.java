package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.analyze.opers.AbstractCallFctOperation;
import code.expressionlanguage.analyze.opers.InvokingOperation;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecStaticFctOperation extends ExecInvokingOperation {

    private String methodName;

    private ClassMethodId classMethodId;

    private String lastType;

    private int naturalVararg;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    public ExecStaticFctOperation(InvokingOperation _inv, AbstractCallFctOperation _s, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock) {
        super(_inv);
        methodName = _s.getMethodName();
        classMethodId = _s.getClassMethodId();
        lastType = _s.getLastType();
        naturalVararg = _s.getNaturalVararg();
        named = _named;
        rootBlock = _rootBlock;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument res_ = getArgument(arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }
    Argument getArgument(CustList<Argument> _arguments, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        CustList<Argument> firstArgs_;
        MethodId methodId_ = classMethodId.getConstraints();
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        Argument prev_ = new Argument();
        classNameFound_ = classMethodId.getClassName();
        classNameFound_ = classMethodId.formatType(classNameFound_,_conf);
        lastType_ = classMethodId.formatType(rootBlock,classNameFound_,lastType_);
        firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments);
        if (ExecutingUtil.hasToExit(_conf,classNameFound_)) {
            return Argument.createVoid();
        }
        return callPrepare(new DefaultExiting(_conf),_conf, classNameFound_,rootBlock, methodId_, prev_, firstArgs_, null,named);
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

}
