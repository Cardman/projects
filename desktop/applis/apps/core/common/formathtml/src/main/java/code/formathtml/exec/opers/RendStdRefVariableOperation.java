package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecVariableContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendStdRefVariableOperation extends RendLeafOperation implements
        RendCalculableOperation,RendSettableElResult{
    private final ExecVariableContent variableContent;
    private final boolean declare;
    public RendStdRefVariableOperation(ExecOperationContent _l, ExecVariableContent _variableContent, boolean _declare) {
        super(_l);
        variableContent = _variableContent;
        declare = _declare;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        if (resultCanBeSet()) {
            if (!declare) {
                ImportingPage ip_ = _conf.getLastPage();
                AbstractWrapper val_ = ip_.getRefParams().getVal(getVariableName());
                ArgumentsPair pair_ = getArgumentPair(_nodes, this);
                pair_.setWrapper(val_);
                setQuickNoConvertSimpleArgument(ExecTemplates.getWrapValue(_context,variableContent.getVariableName(),variableContent.getDeep(),ip_.getPageEl().getCache(), ip_.getPageEl().getRefParams()), _nodes, _context);
            } else {
                setQuickNoConvertSimpleArgument(new Argument(), _nodes, _context);
            }
        } else {
            ImportingPage ip_ = _conf.getLastPage();
            AbstractWrapper val_ = ip_.getRefParams().getVal(getVariableName());
            ArgumentsPair pair_ = getArgumentPair(_nodes, this);
            pair_.setWrapper(val_);
            setSimpleArgument(ExecTemplates.getWrapValue(_context,variableContent.getVariableName(),variableContent.getDeep(),ip_.getPageEl().getCache(), ip_.getPageEl().getRefParams()), _conf, _nodes, _context);
        }
    }
    public String getVariableName() {
        return variableContent.getVariableName();
    }

    public ExecVariableContent getVariableContent() {
        return variableContent;
    }


    @Override
    public boolean resultCanBeSet() {
        return variableContent.isVariable();
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        return trySetArgument(_conf,_nodes, _context, _right);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast, BeanLgNames _advStandards, ContextEl _context) {
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        Struct store_ = ExecTemplates.getWrapValue(_context,getVariableName(),variableContent.getDeep(),_conf.getLastPage().getPageEl().getCache(), _conf.getLastPage().getPageEl().getRefParams()).getStruct();
        return getCommonCompoundSetting(_conf,_nodes,_context,store_,_op,_right,_cl,_cast);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post, Argument _stored, byte _cast, BeanLgNames _advStandards, ContextEl _context) {
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        Struct store_ = ExecTemplates.getWrapValue(_context,getVariableName(),variableContent.getDeep(),_conf.getLastPage().getPageEl().getCache(), _conf.getLastPage().getPageEl().getRefParams()).getStruct();
        return getCommonSemiSetting(_conf,_nodes,_context,store_,_op,_post,_cast);
    }
    private Argument getCommonCompoundSetting(Configuration _config,IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct _store, String _op, Argument _right, ExecClassArgumentMatching _arg, byte _cast) {
        Argument left_ = new Argument(_store);
        Argument res_ = RendNumericOperation.calculateAffect(left_, _right, _op, variableContent.isCatString(), _arg.getNames(), _cast,_conf);
        return trySetArgument(_config,_nodes, _conf, res_);
    }

    private Argument getCommonSemiSetting(Configuration _config,IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct _store, String _op, boolean _post, byte _cast) {
        Argument left_ = new Argument(_store);
        Argument res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        trySetArgument(_config,_nodes, _conf, res_);
        return RendSemiAffectationOperation.getPrePost(_post, left_, res_);
    }
    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        return trySetArgument(_conf,_nodes, _context, _right);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, boolean _post, Argument _stored, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        trySetArgument(_conf,_nodes, _context, _right);
        return RendSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    private Argument trySetArgument(Configuration _config,IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _res) {
        ArgumentsPair pair_ = getArgumentPair(_nodes, this);
        return ExecTemplates.setWrapValue(_conf,variableContent.getVariableName(), _res,variableContent.getDeep(),_config.getLastPage().getPageEl().getCache(), _config.getLastPage().getPageEl().getRefParams());
    }

    public boolean isDeclare() {
        return declare;
    }
}
