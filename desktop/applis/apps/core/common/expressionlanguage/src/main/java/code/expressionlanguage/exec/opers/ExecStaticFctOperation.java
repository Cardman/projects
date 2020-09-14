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
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        CustList<Argument> firstArgs_;
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        Argument prev_ = new Argument();
        classNameFound_ = className;
        classNameFound_ = ClassMethodId.formatType(classNameFound_,_conf, kind);
        lastType_ = ClassMethodId.formatType(rootBlock,classNameFound_,lastType_, kind);
        CustList<Argument> first_ = listNamedArguments(_nodes, chidren_).getArguments();
        firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, first_);
        if (ExecutingUtil.hasToExit(_conf,classNameFound_)) {
            return Argument.createVoid();
        }
        return callPrepare(new DefaultExiting(_conf),_conf, classNameFound_,rootBlock, prev_, firstArgs_, null,getNamed(), kind, "");
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
