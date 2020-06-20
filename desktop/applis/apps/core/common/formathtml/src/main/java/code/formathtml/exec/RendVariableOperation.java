package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.VariableOperation;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.exec.opers.ExecVariableOperation;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;
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
    public RendVariableOperation(int _indexChild, String _varName, ClassArgumentMatching _res, int _order) {
        super(_indexChild,_res,_order);
        variableName  = _varName;
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
        PageEl ip_ = _conf.getPageEl();
        if (resultCanBeSet()) {
            return Argument.createVoid();
        }
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        Argument a_ = new Argument();
        a_.setStruct(locVar_.getStruct());
        return a_;
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right) {
        return getCommonSetting(_conf, _right);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        return getCommonCompoundSetting(_conf, store_, _op, _right);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post, Argument _store) {
        Struct store_;
        store_ = _store.getStruct();
        return getCommonSemiSetting(_conf, store_, _op, _post);
    }

    Argument getCommonSetting(Configuration _conf, Argument _right) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        PageEl ip_ = _conf.getPageEl();
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        return ExecVariableOperation.checkSet(_conf.getContext(),locVar_,_right);
    }
    Argument getCommonCompoundSetting(Configuration _conf, Struct _store, String _op, Argument _right) {
        PageEl ip_ = _conf.getPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        Argument left_ = new Argument();
        String formattedClassVar_ = locVar_.getClassName();
        formattedClassVar_ = _conf.getPageEl().formatVarType(formattedClassVar_, _conf.getContext());
        left_.setStruct(_store);
        ClassArgumentMatching cl_ = new ClassArgumentMatching(formattedClassVar_);
        Argument res_;
        res_ = RendNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, cl_);
        ExecVariableOperation.setVar(_conf.getContext(), locVar_, res_);
        return res_;
    }
    Argument getCommonSemiSetting(Configuration _conf, Struct _store, String _op, boolean _post) {
        PageEl ip_ = _conf.getPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        Argument left_ = new Argument();
        left_.setStruct(_store);
        ClassArgumentMatching cl_ = getResultClass();
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _conf.getContext(), _op, cl_);
        ExecVariableOperation.setVar(_conf.getContext(), locVar_, res_);
        return RendSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right) {
        processVariable(_conf, _right);
        return _right;
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, boolean _post, Argument _stored, Argument _right) {
        processVariable(_conf, _right);
        return RendSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    private void processVariable(Configuration _conf, Argument _right) {
        PageEl ip_ = _conf.getPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        LocalVariable locVar_ = ip_.getLocalVar(variableName);
        ExecVariableOperation.checkSet(_conf.getContext(), locVar_, _right);
    }
}
