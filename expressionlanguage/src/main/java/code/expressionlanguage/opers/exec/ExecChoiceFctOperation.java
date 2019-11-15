package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.ChoiceFctOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecChoiceFctOperation extends ExecInvokingOperation implements NamedCalledOperation {

    private String methodName;

    private ClassMethodId classMethodId;
    private MethodId realId;

    private boolean staticMethod;

    private String lastType;

    private int naturalVararg;
    private int anc;

    private int delta;

    public ExecChoiceFctOperation(ChoiceFctOperation _choice) {
        super(_choice);
        methodName = _choice.getMethodName();
        classMethodId = _choice.getClassMethodId();
        realId = _choice.getRealId();
        staticMethod = _choice.isStaticMethod();
        lastType = _choice.getLastType();
        naturalVararg = _choice.getNaturalVararg();
        anc = _choice.getAnc();
        delta = _choice.getDelta();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_, arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }
    Argument getArgument(Argument _previous, CustList<Argument> _arguments, ExecutableCode _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        String cast_;
        cast_ = stds_.getAliasCastType();
        CustList<Argument> firstArgs_;
        MethodId methodId_;
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        Argument prev_ = new Argument();
        if (!staticMethod) {
            classNameFound_ = classMethodId.getClassName();
            prev_.setStruct(PrimitiveTypeUtil.getParent(anc, classNameFound_, _previous.getStruct(), _conf));
            if (_conf.getContextEl().hasExceptionOrFailInit()) {
                return new Argument();
            }
            String argClassName_ = prev_.getObjectClassName(_conf.getContextEl());
            classNameFound_ = Templates.quickFormat(argClassName_, classNameFound_, _conf);
            if (!Templates.isCorrectExecute(argClassName_, classNameFound_, _conf)) {
                setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                _conf.setException(new ErrorStruct(_conf, StringList.concat(argClassName_,RETURN_LINE,classNameFound_,RETURN_LINE),cast_));
                return new Argument();
            }
            String base_ = Templates.getIdFromAllTypes(classNameFound_);
            String fullClassNameFound_ = Templates.getFullTypeByBases(argClassName_, base_, _conf);
            lastType_ = Templates.quickFormat(fullClassNameFound_, lastType_, _conf);
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
            methodId_ = realId;
        } else {
            methodId_ = classMethodId.getConstraints();
            classNameFound_ = classMethodId.getClassName();
            classNameFound_ = classMethodId.formatType(classNameFound_,_conf);
            lastType_ = classMethodId.formatType(classNameFound_,lastType_,_conf);
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
            if (hasToExit(_conf, classNameFound_)) {
                return Argument.createVoid();
            }
        }
        return callPrepare(_conf, classNameFound_, methodId_, prev_, firstArgs_, null);
    }

    @Override
    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    @Override
    public int getDelta() {
        return delta;
    }
}
