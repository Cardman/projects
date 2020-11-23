package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInstFctContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCustArrOperation extends ExecInvokingOperation implements ExecSettableElResult {

    private final ExecArrContent arrContent;

    private final ExecInstFctContent instFctContent;

    private final ExecTypeFunction get;
    private final ExecTypeFunction set;

    public ExecCustArrOperation(ExecTypeFunction _get, ExecTypeFunction _set, ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecArrContent _arrContent, ExecInstFctContent _instFctContent) {
        super(_opCont, _intermediateDottedOperation);
        arrContent = _arrContent;
        instFctContent = _instFctContent;
        get = _get;
        set = _set;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        if (resultCanBeSet()) {
            Struct array_ = getPreviousArgument(_nodes,this).getStruct();
            Argument a_ = new Argument(array_);
            setQuickNoConvertSimpleArgument(a_, _conf, _nodes);
            return;
        }
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument res_ = getArgument(previous_,_nodes, _conf,null);
        setSimpleArgument(res_, _conf, _nodes);
    }
    @Override
    public Argument calculateSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right) {
        return endCalculateCommon(_conf, _nodes, _right);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, arrContent.isCatString(), _cl.getNames(), _cast);
        if (_conf.callsOrException()) {
            return Argument.createVoid();
        }
        return getArgument(previous_,_nodes, _conf,res_);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, String _op, boolean _post, byte _cast) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument a_ = getArgument(_nodes,this);
        Struct store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        return getArgument(previous_,_nodes, _conf,res_);
    }

    @Override
    public boolean resultCanBeSet() {
        return arrContent.isVariable();
    }

    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        return endCalculateCommon(_conf, _nodes, _right);
    }

    @Override
    public Argument endCalculate(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, boolean _post, Argument _stored, Argument _right) {
        return endCalculateCommon(_conf, _nodes, _right);
    }

    private Argument endCalculateCommon(ContextEl _conf, IdMap<ExecOperationNode, ArgumentsPair> _nodes, Argument _right) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        return getArgument(previous_,_nodes, _conf,_right);
    }

    private Argument getArgument(Argument _previous, IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Argument _right) {
        setRelativeOffsetPossibleLastPage(_conf);
        Struct argPrev_ = _previous.getStruct();
        Argument prev_ = new Argument(ExecTemplates.getParent(instFctContent.getAnc(), instFctContent.getClassName(), argPrev_, _conf));
        if (_conf.callsOrException()) {
            return new Argument();
        }
        ExecTypeFunction fct_;
        if (_right != null) {
            fct_ = set;
        } else {
            fct_ = get;
        }
        Struct pr_ = prev_.getStruct();
        ExecOverrideInfo polymorph_ = polymorphOrSuper(instFctContent.isStaticChoiceMethod(),_conf,pr_, instFctContent.getClassName(),fct_);
        fct_ = polymorph_.getPair();
        String classNameFound_ = polymorph_.getClassName();
        return callPrepare(_conf.getExiting(), _conf, classNameFound_, fct_, prev_,null, getArgs(fct_,_nodes, _conf, pr_), _right, MethodAccessKind.INSTANCE, "");
    }

    private CustList<Argument> getArgs(ExecTypeFunction _pair,IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct _pr) {
        return fetchFormattedArgs(_nodes,_conf,_pr, instFctContent.getClassName(),_pair.getType(), instFctContent.getLastType(), instFctContent.getNaturalVararg());
    }

}
