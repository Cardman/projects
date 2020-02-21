package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.CallingState;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.SuperFctOperation;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendSuperFctOperation extends RendInvokingOperation implements RendCalculableOperation {

    private String methodName;

    private ClassMethodId classMethodId;

    private boolean staticMethod;

    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;
    private int anc;
    public RendSuperFctOperation(SuperFctOperation _s) {
        super(_s);
        methodName = _s.getMethodName();
        classMethodId = _s.getClassMethodId();
        staticMethod = _s.isStaticMethod();
        lastType = _s.getLastType();
        naturalVararg = _s.getNaturalVararg();
        anc = _s.getAnc();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument previous_ = getPreviousArg(this,_nodes,_conf);
        Argument argres_ = getArgument(previous_, arguments_, _conf);
        CallingState state_ = _conf.getContextEl().getCallingState();
        if (state_ instanceof NotInitializedClass) {
            NotInitializedClass statusInit_ = (NotInitializedClass) state_;
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContextEl());
            if (_conf.getContextEl().hasException()) {
                return;
            }
            argres_ = getArgument(previous_, arguments_, _conf);
        }
        processCall(_nodes,_conf,argres_);
    }

    Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf) {
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
            prev_.setStruct(PrimitiveTypeUtil.getParent(anc, classNameFound_, prev_.getStruct(), _conf));
            if (_conf.getContextEl().hasException()) {
                Argument a_ = new Argument();
                return a_;
            }
            String argClassName_ = prev_.getObjectClassName(_conf.getContextEl());
            String base_ = Templates.getIdFromAllTypes(classNameFound_);
            String fullClassNameFound_ = Templates.getFullTypeByBases(argClassName_, base_, _conf);
            lastType_ = Templates.quickFormat(fullClassNameFound_, lastType_, _conf);
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
            methodId_ = classMethodId.getConstraints();
        } else {
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
            classNameFound_ = classMethodId.getClassName();
            if (ExecInvokingOperation.hasToExit(_conf, classNameFound_)) {
                return Argument.createVoid();
            }
        }
        return ExecInvokingOperation.callPrepare(_conf, classNameFound_, methodId_, prev_, firstArgs_, null);
    }
}
