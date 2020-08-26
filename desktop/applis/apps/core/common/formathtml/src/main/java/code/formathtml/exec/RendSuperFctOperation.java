package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.SuperFctOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendSuperFctOperation extends RendInvokingOperation implements RendCalculableOperation,RendCallable {

    private String methodName;

    private ClassMethodId classMethodId;

    private boolean staticMethod;

    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;
    private int anc;
    private ExecNamedFunctionBlock named;
    public RendSuperFctOperation(SuperFctOperation _s, ExecNamedFunctionBlock _named) {
        super(_s);
        methodName = _s.getMethodName();
        classMethodId = _s.getClassMethodId();
        staticMethod = _s.isStaticMethod();
        lastType = _s.getLastType();
        naturalVararg = _s.getNaturalVararg();
        anc = _s.getAnc();
        named = _named;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument argres_ = processCall(this, this, previous_, arguments_, _conf, null);
        setSimpleArgument(argres_,_conf,_nodes);
    }

    public Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf, Argument _right) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        CustList<Argument> firstArgs_;
        MethodId methodId_ = classMethodId.getConstraints();
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        Argument prev_ = new Argument();
        if (!staticMethod) {
            prev_.setStruct(_previous.getStruct());
            classNameFound_ = classMethodId.getClassName();
            prev_.setStruct(ExecTemplates.getParent(anc, classNameFound_, prev_.getStruct(), _conf.getContext()));
            if (_conf.getContext().hasException()) {
                Argument a_ = new Argument();
                return a_;
            }
            String argClassName_ = prev_.getObjectClassName(_conf.getContext());
            String base_ = StringExpUtil.getIdFromAllTypes(classNameFound_);
            String fullClassNameFound_ = ExecTemplates.getSuperGeneric(argClassName_, base_, _conf.getContext());
            lastType_ = ExecTemplates.quickFormat(fullClassNameFound_, lastType_, _conf.getContext());
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments);
            methodId_ = classMethodId.getConstraints();
        } else {
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments);
            classNameFound_ = classMethodId.getClassName();
            if (_conf.hasToExit(classNameFound_)) {
                return Argument.createVoid();
            }
        }
        return ExecInvokingOperation.callPrepare(new AdvancedExiting(_conf),_conf.getContext(), classNameFound_, methodId_, prev_, firstArgs_, null,named);
    }
}
