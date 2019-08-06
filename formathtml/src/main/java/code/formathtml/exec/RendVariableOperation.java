package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.calls.PageEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.VariableOperation;
import code.expressionlanguage.opers.exec.ExecNumericOperation;
import code.expressionlanguage.opers.exec.ExecVariableOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.variables.LocalVariable;
import code.formathtml.Configuration;
import code.util.IdMap;

public final class RendVariableOperation extends RendLeafOperation implements
        RendCalculableOperation,RendSettableElResult {

    private boolean variable;

    private boolean catString;

    private String variableName;

    private int off;

    public RendVariableOperation(VariableOperation _v) {
        super(_v);
        variable = _v.isVariable();
        catString = _v.isCatString();
        variableName  = _v.getVariableName();
        off = _v.getOff();
    }

    @Override
    public boolean resultCanBeSet() {
        return variable;
    }


    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument arg_ = getCommonArgument(_conf);
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(arg_, _conf,_nodes);
        } else {
            setSimpleArgument(arg_, _conf,_nodes);
        }
    }

    Argument getCommonArgument(Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        PageEl ip_ = _conf.getOperationPageEl();
        if (resultCanBeSet()) {
            return Argument.createVoid();
        }
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        Argument a_ = new Argument();
        a_.setStruct(locVar_.getStruct());
        return a_;
    }

    @Override
    public void calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right) {
        Argument arg_ = getCommonSetting(_conf, _right);
        setSimpleArgument(arg_, _conf,_nodes);
    }

    @Override
    public void calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        Argument arg_ = getCommonCompoundSetting(_conf, store_, _op, _right);
        setSimpleArgument(arg_, _conf,_nodes);
    }

    @Override
    public void calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        Argument arg_ = getCommonSemiSetting(_conf, store_, _op, _post);
        setSimpleArgument(arg_, _conf,_nodes);
    }

    Argument getCommonSetting(Configuration _conf, Argument _right) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        PageEl ip_ = _conf.getOperationPageEl();
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        return ExecVariableOperation.checkSet(_conf,locVar_,_right);
    }
    Argument getCommonCompoundSetting(Configuration _conf, Struct _store, String _op, Argument _right) {
        PageEl ip_ = _conf.getOperationPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        Argument left_ = new Argument();
        String formattedClassVar_ = locVar_.getClassName();
        formattedClassVar_ = _conf.getOperationPageEl().formatVarType(formattedClassVar_, _conf);
        left_.setStruct(_store);
        ClassArgumentMatching cl_ = new ClassArgumentMatching(formattedClassVar_);
        Argument res_;
        res_ = RendNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, cl_);
        ExecVariableOperation.setVar(_conf, locVar_, res_);
        return res_;
    }
    Argument getCommonSemiSetting(Configuration _conf, Struct _store, String _op, boolean _post) {
        PageEl ip_ = _conf.getOperationPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        Argument left_ = new Argument();
        String formattedClassVar_ = locVar_.getClassName();
        formattedClassVar_ = _conf.getOperationPageEl().formatVarType(formattedClassVar_, _conf);
        left_.setStruct(_store);
        ClassArgumentMatching cl_ = new ClassArgumentMatching(formattedClassVar_);
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _conf, _op, cl_);
        ExecVariableOperation.setVar(_conf, locVar_, res_);
        return RendSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right) {
        return endCalculate(_nodes,_conf, false, null, _right);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, boolean _post, Argument _stored, Argument _right) {
        PageEl ip_ = _conf.getOperationPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        locVar_.setStruct(_right.getStruct());
        Argument out_ = RendSemiAffectationOperation.getPrePost(_post, _stored, _right);
        setSimpleArgument(out_, _conf,_nodes);
        return out_;
    }
}
