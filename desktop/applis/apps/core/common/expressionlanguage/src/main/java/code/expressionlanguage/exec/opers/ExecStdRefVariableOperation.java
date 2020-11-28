package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecVariableContent;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;

public class ExecStdRefVariableOperation extends ExecLeafOperation implements
        AtomicExecCalculableOperation,ExecSettableElResult {
    private final ExecVariableContent variableContent;
    private final boolean declare;
    public ExecStdRefVariableOperation(ExecOperationContent _l, ExecVariableContent _variableContent, boolean _declare) {
        super(_l);
        variableContent = _variableContent;
        declare = _declare;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        if (resultCanBeSet()) {
            if (!declare) {
                PageEl ip_ = _conf.getLastPage();
                AbstractWrapper val_ = ip_.getRefParams().getVal(getVariableName());
                ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes, this);
                pair_.setWrapper(val_);
                setQuickNoConvertSimpleArgument(new Argument(ExecTemplates.getValue(val_, _conf)), _conf, _nodes);
            } else {
                setQuickNoConvertSimpleArgument(new Argument(), _conf, _nodes);
            }
        } else {
            PageEl ip_ = _conf.getLastPage();
            AbstractWrapper val_ = ip_.getRefParams().getVal(getVariableName());
            ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes, this);
            pair_.setWrapper(val_);
            setSimpleArgument(new Argument(ExecTemplates.getValue(val_, _conf)), _conf, _nodes);
        }
    }

    public String getVariableName() {
        return variableContent.getVariableName();
    }

    public ExecVariableContent getVariableContent() {
        return variableContent;
    }

    @Override
    public Argument calculateSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right) {
        return trySetArgument(_nodes, _conf, _right);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast) {
        ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes, this);
        Struct store_ = ExecTemplates.getValue(pair_.getWrapper(), _conf);
        return getCommonCompoundSetting(_nodes,_conf,store_,_op,_right,_cl,_cast);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, String _op, boolean _post, byte _cast) {
        ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes, this);
        Struct store_ = ExecTemplates.getValue(pair_.getWrapper(), _conf);
        return getCommonSemiSetting(_nodes,_conf,store_,_op,_post,_cast);
    }
    private Argument getCommonCompoundSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct _store, String _op, Argument _right, ExecClassArgumentMatching _arg, byte _cast) {
        Argument left_ = new Argument(_store);
        Argument res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, variableContent.isCatString(), _arg.getNames(), _cast);
        return trySetArgument(_nodes, _conf, res_);
    }

    private Argument getCommonSemiSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct _store, String _op, boolean _post, byte _cast) {
        Argument left_ = new Argument(_store);
        Argument res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        trySetArgument(_nodes, _conf, res_);
        return ExecSemiAffectationOperation.getPrePost(_post, left_, res_);
    }
    @Override
    public boolean resultCanBeSet() {
        return variableContent.isVariable();
    }

    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        return trySetArgument(_nodes, _conf, _right);
    }

    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _post, Argument _stored, Argument _right) {
        trySetArgument(_nodes, _conf, _right);
        return ExecSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    private Argument trySetArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _res) {
        ArgumentsPair pair_ = ExecTemplates.getArgumentPair(_nodes, this);
        return ExecTemplates.trySetArgument(_conf, _res, pair_);
    }
    public boolean isDeclare() {
        return declare;
    }
}
