package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentList;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.ArrOperation;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.util.CustList;
import code.util.IdMap;

public final class ExecCustArrOperation extends ExecInvokingOperation implements ExecSettableElResult {

    private boolean variable;

    private boolean catString;

    private String className;

    private String lastType;

    private int naturalVararg;

    private int anc;

    private boolean staticChoiceMethod;

    private ExecNamedFunctionBlock get;
    private ExecNamedFunctionBlock set;
    private ExecRootBlock rootBlock;

    public ExecCustArrOperation(ArrOperation _arr, ExecNamedFunctionBlock _get, ExecNamedFunctionBlock _set, ExecRootBlock _rootBlock) {
        super(_arr);
        variable = _arr.isVariable();
        catString = _arr.isCatString();
        className = ExecOperationNode.getType(_arr.getClassMethodId());
        lastType = _arr.getLastType();
        naturalVararg = _arr.getNaturalVararg();
        anc = _arr.getAnc();
        staticChoiceMethod = _arr.isStaticChoiceMethod();
        get = _get;
        set = _set;
        rootBlock = _rootBlock;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        if (resultCanBeSet()) {
            Struct array_;
            array_ = getPreviousArgument(_nodes,this).getStruct();
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
        Struct store_;
        store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_;
        res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, _cl.getNames(), _cast);
        if (_conf.callsOrException()) {
            return Argument.createVoid();
        }
        return getArgument(previous_,_nodes, _conf,res_);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, String _op, boolean _post, byte _cast) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _op, _cast);
        return getArgument(previous_,_nodes, _conf,res_);
    }

    @Override
    public boolean resultCanBeSet() {
        return variable;
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
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        Struct argPrev_ = _previous.getStruct();
        Argument prev_ = new Argument(ExecTemplates.getParent(anc, className, argPrev_, _conf));
        if (_conf.callsOrException()) {
            return new Argument();
        }
        ExecNamedFunctionBlock fct_;
        if (_right != null) {
            fct_ = set;
        } else {
            fct_ = get;
        }
        Struct pr_ = prev_.getStruct();
        CustList<Argument> firstArgs_ = getArgs(_nodes, _conf, pr_);
        ExecOverrideInfo polymorph_ = polymorphOrSuper(staticChoiceMethod,_conf,pr_,className,rootBlock,fct_);
        fct_ = polymorph_.getOverridableBlock();
        ExecRootBlock dest_ = polymorph_.getRootBlock();
        String classNameFound_ = polymorph_.getClassName();
        return callPrepare(new DefaultExiting(_conf),_conf, classNameFound_,dest_, prev_, firstArgs_, _right,fct_, MethodAccessKind.INSTANCE, "");
    }

    private CustList<Argument> getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, Struct _pr) {
        return fetchFormattedArgs(_nodes,_conf,_pr,className,rootBlock,lastType,naturalVararg);
    }

}
