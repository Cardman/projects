package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.PageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecVariableContent;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.formathtml.Configuration;
import code.formathtml.SimplePageEl;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendStdVariableOperation  extends RendLeafOperation implements
        RendCalculableOperation,RendSettableElResult{

    private ExecVariableContent variableContent;

    public RendStdVariableOperation(ExecOperationContent _content, ExecVariableContent _variableContent) {
        super(_content);
        variableContent = _variableContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        Argument arg_ = getCommonArgument(_conf, _context);
        if (resultCanBeSet()) {
            setQuickNoConvertSimpleArgument(arg_, _nodes, _context);
        } else {
            setSimpleArgument(arg_, _conf,_nodes, _context);
        }
    }

    @Override
    public boolean resultCanBeSet() {
        return variableContent.isVariable();
    }
    Argument getCommonArgument(Configuration _conf, ContextEl _context) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ variableContent.getOff(), _conf);
        SimplePageEl ip_ = _conf.getPageEl();
        if (resultCanBeSet()) {
            return Argument.createVoid();
        }
        return ExecTemplates.getValue(_context, variableContent.getVariableName(), variableContent.getDeep(), ip_.getCache(), ip_.getValueVars());
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        return getCommonSetting(_conf, _right, _context);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast, BeanLgNames _advStandards, ContextEl _context) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        return getCommonCompoundSetting(_conf, store_, _op, _right, _cl, _cast, _context);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post, Argument _store, byte _cast, BeanLgNames _advStandards, ContextEl _context) {
        Struct store_;
        store_ = _store.getStruct();
        return getCommonSemiSetting(_conf, store_, _op, _post, _cast, _context);
    }

    private Argument getCommonSetting(Configuration _conf, Argument _right, ContextEl _context) {
        SimplePageEl ip_ = _conf.getPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ variableContent.getOff(), _conf);
        return ExecTemplates.setValue(_context, variableContent.getVariableName(), _right, variableContent.getDeep(), ip_.getCache(), ip_.getValueVars());
    }
    private Argument getCommonCompoundSetting(Configuration _conf, Struct _store, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast, ContextEl _context) {
        SimplePageEl ip_ = _conf.getPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ variableContent.getOff(), _conf);
        Argument left_ = new Argument(_store);
        Argument res_;
        res_ = RendNumericOperation.calculateAffect(left_, _right, _op, variableContent.isCatString(), _cl.getNames(), _cast, _context);
        ExecTemplates.setValue(_context, variableContent.getVariableName(), res_, variableContent.getDeep(), ip_.getCache(), ip_.getValueVars());
        return res_;
    }
    private Argument getCommonSemiSetting(Configuration _conf, Struct _store, String _op, boolean _post, byte _cast, ContextEl _context) {
        SimplePageEl ip_ = _conf.getPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ variableContent.getOff(), _conf);
        Argument left_ = new Argument(_store);
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        ExecTemplates.setValue(_context, variableContent.getVariableName(), res_, variableContent.getDeep(), ip_.getCache(), ip_.getValueVars());
        return RendSemiAffectationOperation.getPrePost(_post, left_, res_);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        processVariable(_conf, _right, _context);
        return _right;
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, boolean _post, Argument _stored, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        processVariable(_conf, _right, _context);
        return RendSemiAffectationOperation.getPrePost(_post, _stored, _right);
    }

    private void processVariable(Configuration _conf, Argument _right, ContextEl _context) {
        SimplePageEl ip_ = _conf.getPageEl();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ variableContent.getOff(), _conf);
        ExecTemplates.setValue(_context, variableContent.getVariableName(), _right, variableContent.getDeep(), ip_.getCache(), ip_.getValueVars());
    }
}
