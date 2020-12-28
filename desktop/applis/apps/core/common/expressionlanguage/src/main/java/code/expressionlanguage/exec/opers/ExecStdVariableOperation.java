package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecVariableContent;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.util.IdMap;

public final class ExecStdVariableOperation extends ExecLeafOperation implements
        AtomicExecCalculableOperation,ExecSettableElResult {

    private final ExecVariableContent variableContent;

    public ExecStdVariableOperation(ExecOperationContent _opCont, ExecVariableContent _variableContent) {
        super(_opCont);
        variableContent = _variableContent;
    }

    public boolean resultCanBeSet() {
        return variableContent.isVariable();
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
    private Argument getCommonArgument(ContextEl _conf) {
        setRelOffsetPossibleLastPage(variableContent.getOff(), _conf);
        PageEl ip_ = _conf.getLastPage();
        if (resultCanBeSet()) {
            return Argument.createVoid();
        }
        return ExecTemplates.getWrapValue(_conf, variableContent.getVariableName(), variableContent.getDeep(), ip_.getCache(), ip_.getRefParams());
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
            String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        return getCommonCompoundSetting(_conf, store_, _op, _right,_cl, _cast);
    }

    @Override
    public Argument calculateSemiSetting(
            IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf,
            String _op, boolean _post, byte _cast) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        return getCommonSemiSetting(_conf, store_, _op, _post, _cast);
    }

    private Argument getCommonSetting(ContextEl _conf, Argument _right) {
        PageEl ip_ = _conf.getLastPage();
        return ExecTemplates.setWrapValue(_conf, variableContent.getVariableName(), _right, variableContent.getDeep(), ip_.getCache(), ip_.getRefParams());
    }

    private Argument getCommonCompoundSetting(ContextEl _conf, Struct _store, String _op, Argument _right, ExecClassArgumentMatching _arg, byte _cast) {
        PageEl ip_ = _conf.getLastPage();
        Argument left_ = new Argument(_store);
        Argument res_;
        res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, variableContent.isCatString(), _arg.getNames(), _cast);
        setVar(_conf, variableContent.getVariableName(), ip_, res_, variableContent.getDeep());
        return res_;
    }
    private Argument getCommonSemiSetting(ContextEl _conf, Struct _store, String _op, boolean _post, byte _cast) {
        PageEl ip_ = _conf.getLastPage();
        Argument left_ = new Argument(_store);
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        setVar(_conf, variableContent.getVariableName(),ip_, res_, variableContent.getDeep());
        return ExecSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    private static void setVar(ContextEl _conf, String _variableName, PageEl _var, Argument _value, int _deep) {
        ExecTemplates.setWrapValue(_conf,_variableName, _value,_deep, _var.getCache(), _var.getRefParams());
    }
    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        PageEl ip_ = _conf.getLastPage();
        ExecTemplates.setWrapValue(_conf, variableContent.getVariableName(), _right, variableContent.getDeep(), ip_.getCache(), ip_.getRefParams());
        return _right;
    }

    @Override
    public Argument endCalculate(ContextEl _conf,
                                 IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _post,
                                 Argument _stored, Argument _right) {
        PageEl ip_ = _conf.getLastPage();
        ExecTemplates.setWrapValue(_conf, variableContent.getVariableName(), _right, variableContent.getDeep(), ip_.getCache(), ip_.getRefParams());
        return ExecSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    public ExecVariableContent getVariableContent() {
        return variableContent;
    }
}
