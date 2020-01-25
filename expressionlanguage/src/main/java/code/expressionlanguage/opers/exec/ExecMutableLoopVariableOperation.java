package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.MutableLoopVariableOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LoopVariable;
import code.util.IdMap;

public final class ExecMutableLoopVariableOperation extends ExecLeafOperation implements AtomicExecCalculableOperation,ExecSettableElResult {

    private boolean variable;

    private boolean catString;

    private String variableName;

    private int off;

    public ExecMutableLoopVariableOperation(MutableLoopVariableOperation _v) {
        super(_v);
        variable = _v.isVariable();
        catString = _v.isCatString();
        variableName  = _v.getVariableName();
        off = _v.getOff();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        Argument arg_ = getCommonArgument(_conf);
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(arg_, _conf, _nodes);
        } else {
            setSimpleArgument(arg_, _conf, _nodes);
        }
    }

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
    public Argument calculateSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            Argument _right) {
        Argument arg_ = getCommonSetting(_conf, _right);
        return arg_;
    }

    @Override
    public Argument calculateCompoundSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, Argument _right) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        Argument arg_ = getCommonCompoundSetting(_conf, store_, _op, _right,getResultClass());
        return arg_;
    }

    @Override
    public Argument calculateSemiSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        Argument arg_ = getCommonSemiSetting(_conf, store_, _op, _post);
        return arg_;
    }

    private Argument getCommonSetting(ExecutableCode _conf, Argument _right) {
        PageEl ip_ = _conf.getOperationPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        LoopVariable locVar_ = ip_.getVars().getVal(variableName);
        return checkSet(_conf,locVar_,_right);
    }
    public static Argument checkSet(ExecutableCode _conf, LoopVariable _loc, Argument _right) {
        String formattedClassVar_ = _loc.getClassName();
        if (!Templates.checkObject(formattedClassVar_, _right, _conf)) {
            return Argument.createVoid();
        }
        _loc.setStruct(_right.getStruct());
        return _right;
    }
    private Argument getCommonCompoundSetting(ExecutableCode _conf, Struct _store, String _op, Argument _right, ClassArgumentMatching _arg) {
        PageEl ip_ = _conf.getOperationPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        LoopVariable locVar_ = ip_.getVars().getVal(variableName);
        Argument left_ = new Argument();
        left_.setStruct(_store);
        Argument res_;
        res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, _arg);
        setVar(_conf, locVar_, res_);
        return res_;
    }
    private Argument getCommonSemiSetting(ExecutableCode _conf, Struct _store, String _op, boolean _post) {
        PageEl ip_ = _conf.getOperationPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        LoopVariable locVar_ = ip_.getVars().getVal(variableName);
        Argument left_ = new Argument();
        String formattedClassVar_ = locVar_.getClassName();
        left_.setStruct(_store);
        ClassArgumentMatching cl_ = new ClassArgumentMatching(formattedClassVar_);
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _conf, _op, cl_);
        setVar(_conf, locVar_, res_);
        return ExecSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    public static void setVar(ExecutableCode _conf, LoopVariable _var,Argument _value) {
        if (_conf.getContextEl().hasExceptionOrFailInit()) {
            return;
        }
        checkSet(_conf,_var,_value);
    }
    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        return endCalculate(_conf, _nodes, false, null, _right);
    }

    @Override
    public Argument endCalculate(ContextEl _conf,
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _post,
            Argument _stored, Argument _right) {
        PageEl ip_ = _conf.getOperationPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        LoopVariable locVar_ = ip_.getVars().getVal(variableName);
        checkSet(_conf,locVar_,_right);
        Argument out_ = ExecSemiAffectationOperation.getPrePost(_post, _stored, _right);
        setSimpleArgument(out_, _conf, _nodes);
        return out_;
    }

    public String getVariableName() {
        return variableName;
    }

    public int getOff() {
        return off;
    }
}
