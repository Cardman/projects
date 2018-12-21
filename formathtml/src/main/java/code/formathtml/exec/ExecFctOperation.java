package code.formathtml.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.PrimitiveTypeUtil;
import code.expressionlanguage.Templates;
import code.expressionlanguage.calls.util.CustomFoundConstructor;
import code.expressionlanguage.calls.util.CustomFoundMethod;
import code.expressionlanguage.calls.util.CustomReflectMethod;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.FctOperation;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;

public final class ExecFctOperation extends ExecReflectableInvokingOperation {

    private String methodName;

    private ClassMethodId classMethodId;

    private boolean staticMethod;

    private boolean staticChoiceMethod;

    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;

    private int anc;

    public ExecFctOperation(FctOperation _fct) {
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
    public void quickCalculate(Analyzable _conf) {
        if (!_conf.isGearConst()) {
            return;
        }
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (ExecDynOperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        if (classMethodId == null) {
            return;
        }
        Argument previous_;
        previous_ = getPreviousArgument();
        Struct str_;
        if (!classMethodId.getConstraints().isStaticMethod()) {
            if (previous_ == null || previous_.isNull()) {
                return;
            }
            str_ = previous_.getStruct();
        } else if (previous_ != null) {
            str_ = previous_.getStruct();
        } else {
            str_ = NullStruct.NULL_VALUE;
        }
        String cl_ = classMethodId.getClassName();
        if (_conf.getClasses().isCustomType(cl_)) {
            return;
        }
        if (lastType == null) {
            return;
        }
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        CustList<Argument> firstArgs_ = quickListArguments(chidren_, naturalVararg_, lastType_, arguments_, _conf);
        if (firstArgs_ == null) {
            return;
        }
        ResultErrorStd res_ = LgNames.invokeStdMethod(_conf, classMethodId, str_, Argument.toArgArray(firstArgs_));
        if (res_.getResult() == null) {
            return;
        }
        Argument arg_ = Argument.createVoid();
        arg_.setStruct(res_.getResult());
        setSimpleArgumentAna(arg_, _conf);
    }
    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (ExecDynOperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument();
        } else {
            previous_ = _conf.getOperationPageEl().getGlobalArgument();
        }
        Argument argres_ = getArgument(previous_, arguments_, _conf);
        NotInitializedClass statusInit_ = _conf.getContextEl().getInitClass();
        if (statusInit_ != null) {
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContextEl());
            if (_conf.getContextEl().hasException()) {
                return;
            }
            argres_ = getArgument(previous_, arguments_, _conf);
        }
        CustomFoundConstructor ctor_ = _conf.getContextEl().getCallCtor();
        CustomFoundMethod method_ = _conf.getContextEl().getCallMethod();
        CustomReflectMethod ref_ = _conf.getContextEl().getReflectMethod();
        Argument res_;
        if (ctor_ != null) {
            res_ = ProcessMethod.instanceArgument(ctor_.getClassName(), ctor_.getCurrentObject(), ctor_.getId(), ctor_.getArguments(), _conf.getContextEl());
        } else if (method_ != null) {
            res_ = ProcessMethod.calculateArgument(method_.getGl(), method_.getClassName(), method_.getId(), method_.getArguments(), _conf.getContextEl());
        } else if (ref_ != null) {
            res_ = ProcessMethod.reflectArgument(ref_.getGl(), ref_.getArguments(), _conf.getContextEl(), ref_.getReflect(), ref_.isLambda());
        } else {
            res_ = argres_;
        }
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(res_, _conf);
    }
    Argument getArgument(Argument _previous, CustList<Argument> _arguments, ExecutableCode _conf) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
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
            prev_.setStruct(PrimitiveTypeUtil.getParent(anc, classNameFound_, _previous.getStruct(), _conf));
            if (_conf.getContextEl().hasExceptionOrFailInit()) {
                Argument a_ = new Argument();
                return a_;
            }
            if (prev_.getStruct() instanceof ArrayStruct) {
                int offLoc_ = -1;
                if (!chidren_.isEmpty()) {
                    offLoc_ = chidren_.last().getIndexInEl() + getIndexBegin();
                }
                firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments, _conf);
                return callPrepare(_conf, classNameFound_, methodId_, prev_, firstArgs_, offLoc_);
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

}
