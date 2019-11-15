package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.ArrOperation;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class ExecCustArrOperation extends ExecInvokingOperation implements ExecSettableElResult {

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
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        if (resultCanBeSet()) {
            Struct array_;
            array_ = getPreviousArgument(_nodes,this).getStruct();
            Argument a_ = new Argument();
            a_.setStruct(array_);
            setQuickNoConvertSimpleArgument(a_, _conf, _nodes);
            return;
        }
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_, arguments_, _conf,null);
        setSimpleArgument(res_, _conf, _nodes);
    }
    @Override
    public Argument calculateSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        return getArgument(previous_, arguments_, _conf,_right);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, String _op, Argument _right) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        Argument left_ = new Argument();
        left_.setStruct(store_);
        ClassArgumentMatching clArg_ = getResultClass();
        Argument res_;
        res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, clArg_);
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            return Argument.createVoid();
        }
        return getArgument(previous_, arguments_, _conf,res_);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, String _op, boolean _post) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        Argument left_ = new Argument();
        left_.setStruct(store_);
        ClassArgumentMatching clArg_ = getResultClass();
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _conf, _op, clArg_);
        return getArgument(previous_, arguments_, _conf,res_);
    }

    @Override
    public boolean resultCanBeSet() {
        return variable;
    }

    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        return endCalculate(_conf, _nodes, false, null, _right);
    }

    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _post, Argument _stored, Argument _right) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        return getArgument(previous_, arguments_, _conf,_right);
    }

    Argument getArgument(Argument _previous, CustList<Argument> _arguments, ExecutableCode _conf, Argument _right) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
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
                cast_ = stds_.getAliasCastType();
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
            methodId_ = new MethodId(MethodAccessKind.INSTANCE,"[]=",methodId_.getParametersTypes(),methodId_.isVararg());
        }
        return callPrepare(_conf, classNameFound_, methodId_, prev_, firstArgs_, _right);
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }
}
