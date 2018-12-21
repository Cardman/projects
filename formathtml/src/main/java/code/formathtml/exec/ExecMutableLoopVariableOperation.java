package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.Templates;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.opers.MutableLoopVariableOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LoopVariable;

public final class ExecMutableLoopVariableOperation extends ExecVariableLeafOperation implements ExecSettableElResult {

    private boolean variable;

    private boolean catString;

    private String variableName = EMPTY_STRING;

    private int off;

    public ExecMutableLoopVariableOperation(MutableLoopVariableOperation _v) {
        super(_v);
        variable = _v.isVariable();
        catString = _v.isCatString();
        variableName  = _v.getVariableName();
        off = _v.getOff();
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        Argument arg_ = getCommonArgument(_conf);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        if (resultCanBeSet()) {
            setQuickSimpleArgument(arg_, _conf);
        } else {
            setSimpleArgument(arg_, _conf);
        }
    }

    @Override
    public boolean resultCanBeSet() {
        return variable;
    }
    Argument getCommonArgument(ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        PageEl ip_ = _conf.getOperationPageEl();
        if (resultCanBeSet()) {
            return Argument.createVoid();
        }
        LoopVariable locVar_ = ip_.getVars().getVal(variableName);
        Argument a_ = new Argument();
        a_.setStruct(locVar_.getStruct());
        return a_;
    }

    @Override
    public void calculateSetting(ExecutableCode _conf, Argument _right) {
        Argument arg_ = getCommonSetting(_conf, _right);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }

    @Override
    public void calculateCompoundSetting(ExecutableCode _conf, String _op,
            Argument _right) {
        Argument a_ = getArgument();
        Struct store_;
        store_ = a_.getStruct();
        Argument arg_ = getCommonCompoundSetting(_conf, store_, _op, _right);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }

    @Override
    public void calculateSemiSetting(ExecutableCode _conf, String _op,
            boolean _post) {
        Argument a_ = getArgument();
        Struct store_;
        store_ = a_.getStruct();
        Argument arg_ = getCommonSemiSetting(_conf, store_, _op, _post);
        if (_conf.getContextEl().hasException()) {
            return;
        }
        setSimpleArgument(arg_, _conf);
    }
    Argument getCommonSetting(ExecutableCode _conf, Argument _right) {
        PageEl ip_ = _conf.getOperationPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        LoopVariable locVar_ = ip_.getVars().getVal(variableName);
        Argument left_ = new Argument();
        String formattedClassVar_ = locVar_.getClassName();
        formattedClassVar_ = _conf.getOperationPageEl().formatVarType(formattedClassVar_, _conf);
        left_.setStruct(locVar_.getStruct());
        if (!Templates.checkObject(formattedClassVar_, _right, _conf)) {
            return Argument.createVoid();
        }
        locVar_.setStruct(_right.getStruct());
        return _right;
    }
    Argument getCommonCompoundSetting(ExecutableCode _conf, Struct _store, String _op, Argument _right) {
        PageEl ip_ = _conf.getOperationPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        LoopVariable locVar_ = ip_.getVars().getVal(variableName);
        Argument left_ = new Argument();
        String formattedClassVar_ = locVar_.getClassName();
        formattedClassVar_ = _conf.getOperationPageEl().formatVarType(formattedClassVar_, _conf);
        left_.setStruct(_store);
        ClassArgumentMatching cl_ = new ClassArgumentMatching(formattedClassVar_);
        Argument res_;
        res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, cl_);
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            return res_;
        }
        locVar_.setStruct(res_.getStruct());
        return res_;
    }
    Argument getCommonSemiSetting(ExecutableCode _conf, Struct _store, String _op, boolean _post) {
        PageEl ip_ = _conf.getOperationPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        LoopVariable locVar_ = ip_.getVars().getVal(variableName);
        Argument left_ = new Argument();
        String formattedClassVar_ = locVar_.getClassName();
        formattedClassVar_ = _conf.getOperationPageEl().formatVarType(formattedClassVar_, _conf);
        left_.setStruct(_store);
        ClassArgumentMatching cl_ = new ClassArgumentMatching(formattedClassVar_);
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _conf, _op, cl_);
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            return res_;
        }
        locVar_.setStruct(res_.getStruct());
        return ExecSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    public String getVariableName() {
        return variableName;
    }
    @Override
    public Argument endCalculate(ExecutableCode _conf, Argument _right) {
        return endCalculate(_conf, false, null, _right);
    }
    @Override
    public Argument endCalculate(ExecutableCode _conf, boolean _post,
            Argument _stored, Argument _right) {
        PageEl ip_ = _conf.getOperationPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        LoopVariable locVar_ = ip_.getVars().getVal(variableName);
        locVar_.setStruct(_right.getStruct());
        Argument out_ = ExecSemiAffectationOperation.getPrePost(_post, _stored, _right);
        setSimpleArgument(out_, _conf);
        return out_;
    }
}
