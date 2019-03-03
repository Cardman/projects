package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.FctOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecFctOperation extends ExecReflectableInvokingOperation {

    private String methodName;

    private ClassMethodId classMethodId;

    private boolean staticMethod;

    private boolean staticChoiceMethod;

    private String lastType;

    private int naturalVararg;

    private int anc;

    protected ExecFctOperation(FctOperation _fct) {
        super(_fct);
        methodName = _fct.getMethodName();
        classMethodId = _fct.getClassMethodId();
        staticMethod = _fct.isStaticMethod();
        staticChoiceMethod = _fct.isStaticChoiceMethod();
        lastType = _fct.getLastType();
        naturalVararg = _fct.getNaturalVararg();
        anc = _fct.getAnc();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_, arguments_, _conf);
        setSimpleArgument(res_, _conf, _nodes);
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        FctOperation.tryGetArg(this, _conf, classMethodId, naturalVararg, lastType);
    }
    Argument getArgument(Argument _previous, CustList<Argument> _arguments, ExecutableCode _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        int off_ = StringList.getFirstPrintableCharIndex(methodName);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off_, _conf);
        LgNames stds_ = _conf.getStandards();
        CustList<Argument> firstArgs_;
        MethodId methodId_ = classMethodId.getConstraints();
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        Argument prev_ = new Argument();
        if (!staticMethod) {
            classNameFound_ = classMethodId.getClassName();
            Struct argPrev_ = _previous.getStruct();
            if (argPrev_ instanceof ArrayStruct) {
                int offLoc_ = -1;
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                return callPrepare(_conf, classNameFound_, methodId_, _previous, firstArgs_, offLoc_);
            }
            prev_.setStruct(PrimitiveTypeUtil.getParent(anc, classNameFound_, argPrev_, _conf));
            if (_conf.getContextEl().hasExceptionOrFailInit()) {
                return new Argument();
            }
            String base_ = Templates.getIdFromAllTypes(classNameFound_);
            if (staticChoiceMethod) {
                String argClassName_ = prev_.getObjectClassName(_conf.getContextEl());
                String fullClassNameFound_ = Templates.getFullTypeByBases(argClassName_, base_, _conf);
                lastType_ = Templates.quickFormat(fullClassNameFound_, lastType_, _conf);
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                methodId_ = classMethodId.getConstraints();
            } else {
                Struct previous_ = prev_.getStruct();
                ContextEl context_ = _conf.getContextEl();
                ClassMethodId methodToCall_ = polymorph(context_, previous_, classMethodId);
                String argClassName_ = stds_.getStructClassName(previous_, context_);
                String fullClassNameFound_ = Templates.getFullTypeByBases(argClassName_, base_, _conf);
                lastType_ = Templates.quickFormat(fullClassNameFound_, lastType_, _conf);
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                methodId_ = methodToCall_.getConstraints();
                classNameFound_ = methodToCall_.getClassName();
            }
        } else {
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
            classNameFound_ = classMethodId.getClassName();
            if (hasToExit(_conf, classNameFound_)) {
                return Argument.createVoid();
            }
        }
        int offLoc_ = -1;
        if (!chidren_.isEmpty()) {
            offLoc_ = chidren_.last().getIndexInEl() + getIndexBegin();
        }
        return callPrepare(_conf, classNameFound_, methodId_, prev_, firstArgs_, offLoc_);
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }
}
