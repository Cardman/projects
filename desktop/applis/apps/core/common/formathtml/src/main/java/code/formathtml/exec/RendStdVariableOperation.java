package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.MutableLoopVariableOperation;
import code.expressionlanguage.analyze.opers.VariableOperation;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.formathtml.Configuration;
import code.util.IdMap;

public final class RendStdVariableOperation  extends RendLeafOperation implements
        RendCalculableOperation,RendSettableElResult{

    private boolean variable;

    private boolean catString;

    private String variableName;

    private int off;
    private int deep;

    public RendStdVariableOperation(VariableOperation _v) {
        super(_v);
        variable = _v.getVariableContent().isVariable();
        catString = _v.getVariableContent().isCatString();
        variableName  = _v.getVariableContent().getVariableName();
        off = _v.getVariableContent().getOff();
        deep = _v.getVariableContent().getDeep();
    }
    public RendStdVariableOperation(int _indexChild, String _varName, ExecClassArgumentMatching _res, int _order) {
        super(_indexChild,_res,_order);
        variableName  = _varName;
        deep = -1;
    }
    public RendStdVariableOperation(MutableLoopVariableOperation _v) {
        super(_v);
        variable = _v.getVariableContent().isVariable();
        catString = _v.getVariableContent().isCatString();
        variableName  = _v.getVariableContent().getVariableName();
        off = _v.getVariableContent().getOff();
        deep = _v.getVariableContent().getDeep();
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

    @Override
    public boolean resultCanBeSet() {
        return variable;
    }
    Argument getCommonArgument(Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        PageEl ip_ = _conf.getPageEl();
        if (resultCanBeSet()) {
            return Argument.createVoid();
        }
        return ExecTemplates.getValue(_conf.getContext(),variableName,ip_,deep);
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right) {
        return getCommonSetting(_conf, _right);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        return getCommonCompoundSetting(_conf, store_, _op, _right, _cl, _cast);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post, Argument _store, byte _cast) {
        Struct store_;
        store_ = _store.getStruct();
        return getCommonSemiSetting(_conf, store_, _op, _post, _cast);
    }

    private Argument getCommonSetting(Configuration _conf, Argument _right) {
        PageEl ip_ = _conf.getPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        return ExecTemplates.setValue(_conf.getContext(),variableName,ip_,_right,deep);
    }
    private Argument getCommonCompoundSetting(Configuration _conf, Struct _store, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast) {
        PageEl ip_ = _conf.getPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        Argument left_ = new Argument(_store);
        Argument res_;
        res_ = RendNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, _cl.getNames(), _cast);
        ExecTemplates.setValue(_conf.getContext(), variableName, ip_, res_,deep);
        return res_;
    }
    private Argument getCommonSemiSetting(Configuration _conf, Struct _store, String _op, boolean _post, byte _cast) {
        PageEl ip_ = _conf.getPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+off, _conf);
        Argument left_ = new Argument(_store);
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        ExecTemplates.setValue(_conf.getContext(),variableName,ip_,res_,deep);
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
        ExecTemplates.setValue(_conf.getContext(),variableName,ip_,_right,deep);
    }
}
