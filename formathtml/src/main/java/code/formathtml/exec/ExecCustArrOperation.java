package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.util.CustomFoundConstructor;
import code.expressionlanguage.calls.util.CustomFoundMethod;
import code.expressionlanguage.calls.util.CustomReflectMethod;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.opers.ArrOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;

public final class ExecCustArrOperation extends ExecInvokingOperation implements DirectExecCalculableOperation,ExecSettableElResult {

    private boolean variable;

    private boolean catString;

    private ClassMethodId classMethodId;

    private String lastType;

    private int naturalVararg;

    private int anc;

    private boolean staticChoiceMethod;

    public ExecCustArrOperation(ArrOperation _arr) {
        super(_arr);
        variable = _arr.isVariable();
        catString = _arr.isCatString();
        classMethodId = _arr.getClassMethodId();
        lastType = _arr.getLastType();
        naturalVararg = _arr.getNaturalVararg();
        anc = _arr.getAnc();
        staticChoiceMethod = _arr.isStaticChoiceMethod();
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        if (resultCanBeSet()) {
            Struct array_;
            array_ = getPreviousArgument().getStruct();
            Argument a_ = new Argument();
            a_.setStruct(array_);
            setQuickSimpleArgument(a_, _conf);
            return;
        }
        processCalling(_conf, null);
    }

    @Override
    public boolean resultCanBeSet() {
        return variable;
    }

    @Override
    public void calculateSetting(ExecutableCode _conf, Argument _right) {
        processCalling(_conf, _right);
    }

    @Override
    public void calculateCompoundSetting(ExecutableCode _conf, String _op, Argument _right) {
        Argument a_ = getArgument();
        Struct store_;
        store_ = a_.getStruct();
        Argument left_ = new Argument();
        left_.setStruct(store_);
        ClassArgumentMatching clArg_ = getResultClass();
        Argument res_;
        res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, clArg_);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        processCalling(_conf,res_);
    }

    @Override
    public void calculateSemiSetting(ExecutableCode _conf, String _op, boolean _post) {
        Argument a_ = getArgument();
        Struct store_;
        store_ = a_.getStruct();
        Argument left_ = new Argument();
        left_.setStruct(store_);
        ClassArgumentMatching clArg_ = getResultClass();
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _conf, _op, clArg_);
        processCalling(_conf,res_);
    }

    @Override
    public Argument endCalculate(ExecutableCode _conf, Argument _right) {
        return endCalculate(_conf,false,null,_right);
    }

    @Override
    public Argument endCalculate(ExecutableCode _conf, boolean _post, Argument _stored, Argument _right) {
        return processCalling(_conf,_right);
    }

    private Argument processCalling(ExecutableCode _conf, Argument _right) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (ExecDynOperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument previous_ = getPreviousArgument();
        Argument argres_ = getArgument(previous_, arguments_, _conf, _right);
        NotInitializedClass statusInit_ = _conf.getContextEl().getInitClass();
        if (statusInit_ != null) {
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContextEl());
            if (_conf.getContextEl().hasException()) {
                return Argument.createVoid();
            }
            argres_ = getArgument(previous_, arguments_, _conf, _right);
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
            return res_;
        }
        setSimpleArgument(res_, _conf);
        return res_;
    }

    Argument getArgument(Argument _previous, CustList<Argument> _arguments, ExecutableCode _conf, Argument _right) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        LgNames stds_ = _conf.getStandards();
        CustList<Argument> firstArgs_;
        MethodId methodId_;
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        Argument prev_ = new Argument();
        classNameFound_ = classMethodId.getClassName();
        Struct argPrev_ = _previous.getStruct();
        prev_.setStruct(PrimitiveTypeUtil.getParent(anc, classNameFound_, argPrev_, _conf));
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            return new Argument();
        }
        String base_ = Templates.getIdFromAllTypes(classNameFound_);
        if (staticChoiceMethod) {
            String argClassName_ = prev_.getObjectClassName(_conf.getContextEl());
            classNameFound_ = Templates.quickFormat(argClassName_, classNameFound_, _conf);
            if (!Templates.isCorrectExecute(argClassName_, classNameFound_, _conf)) {
                setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                String cast_;
                cast_ = stds_.getAliasCast();
                _conf.setException(new ErrorStruct(_conf, StringList.concat(argClassName_,RETURN_LINE,classNameFound_,RETURN_LINE),cast_));
                return new Argument();
            }
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
        if (_right != null) {
            methodId_ = new MethodId(false,"[]=",methodId_.getParametersTypes(),methodId_.isVararg());
        }
        return ExecInvokingOperation.callPrepare(_conf, classNameFound_, methodId_, prev_, firstArgs_, _right);
    }
}
