package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.MutableLoopVariableOperation;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LoopVariable;
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
    private Argument getCommonArgument(ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        PageEl ip_ = _conf.getLastPage();
        if (resultCanBeSet()) {
            return Argument.createVoid();
        }
        return ExecTemplates.getValue(_conf,variableName,ip_);
    }

    @Override
    public Argument calculateSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            Argument _right) {
        return getCommonSetting(_conf, _right);
    }

    @Override
    public Argument calculateCompoundSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, Argument _right) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        return getCommonCompoundSetting(_conf, store_, _op, _right,getResultClass());
    }

    @Override
    public Argument calculateSemiSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        return getCommonSemiSetting(_conf, store_, _op, _post);
    }

    private Argument getCommonSetting(ContextEl _conf, Argument _right) {
        PageEl ip_ = _conf.getLastPage();
        return ExecTemplates.setValue(_conf,variableName,ip_,_right);
    }

    private Argument getCommonCompoundSetting(ContextEl _conf, Struct _store, String _op, Argument _right, ClassArgumentMatching _arg) {
        PageEl ip_ = _conf.getLastPage();
        Argument left_ = new Argument();
        left_.setStruct(_store);
        Argument res_;
        res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, _arg);
        setVar(_conf, variableName,ip_, res_);
        return res_;
    }
    private Argument getCommonSemiSetting(ContextEl _conf, Struct _store, String _op, boolean _post) {
        PageEl ip_ = _conf.getLastPage();
        Argument left_ = new Argument();
        left_.setStruct(_store);
        ClassArgumentMatching cl_ = getResultClass();
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _conf, _op, cl_);
        setVar(_conf, variableName,ip_, res_);
        return ExecSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    public static void setVar(ContextEl _conf, String _variableName,PageEl _var,Argument _value) {
        ExecTemplates.setValue(_conf,_variableName,_var,_value);
    }
    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        PageEl ip_ = _conf.getLastPage();
        ExecTemplates.setValue(_conf,variableName,ip_, _right);
        return _right;
    }

    @Override
    public Argument endCalculate(ContextEl _conf,
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _post,
            Argument _stored, Argument _right) {
        PageEl ip_ = _conf.getLastPage();
        ExecTemplates.setValue(_conf,variableName,ip_,_right);
        return ExecSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    public String getVariableName() {
        return variableName;
    }

}
