package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.DefaultExiting;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.ArrOperation;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.Struct;
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

    public ExecCustArrOperation(ArrOperation _arr, ContextEl _context, ExecNamedFunctionBlock _get, ExecNamedFunctionBlock _set, ExecRootBlock _rootBlock) {
        super(_arr);
        variable = _arr.isVariable();
        catString = _arr.isCatString();
        className = ExecOperationNode.getType(_context,_arr.getClassMethodId());
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
    public Argument calculateCompoundSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, String _op, Argument _right) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_;
        res_ = ExecNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, getResultClass());
        if (_conf.callsOrException()) {
            return Argument.createVoid();
        }
        return getArgument(previous_,_nodes, _conf,res_);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, String _op, boolean _post) {
        Argument previous_ = getPreviousArg(this, _nodes, _conf);
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        ClassArgumentMatching clArg_ = getResultClass();
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(left_, _conf, _op, clArg_);
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
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        LgNames stds_ = _conf.getStandards();
        CustList<Argument> firstArgs_;
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        classNameFound_ = className;
        Struct argPrev_ = _previous.getStruct();
        Argument prev_ = new Argument(ExecTemplates.getParent(anc, classNameFound_, argPrev_, _conf));
        if (_conf.callsOrException()) {
            return new Argument();
        }
        String base_ = StringExpUtil.getIdFromAllTypes(classNameFound_);
        ExecNamedFunctionBlock fct_;
        if (_right != null) {
            fct_ = set;
        } else {
            fct_ = get;
        }
        CustList<Argument> first_ = listNamedArguments(_nodes, chidren_).getArguments();
        if (staticChoiceMethod) {
            String argClassName_ = prev_.getObjectClassName(_conf);
            String fullClassNameFound_ = ExecTemplates.getSuperGeneric(argClassName_, base_, _conf);
            lastType_ = ExecTemplates.quickFormat(rootBlock,fullClassNameFound_, lastType_);
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, first_);
        } else {
            Struct previous_ = prev_.getStruct();
            ExecOverrideInfo polymorph_ = polymorph(_conf, previous_, rootBlock, fct_);
            fct_ = polymorph_.getOverridableBlock();
            String argClassName_ = stds_.getStructClassName(previous_, _conf);
            String fullClassNameFound_ = ExecTemplates.getSuperGeneric(argClassName_, base_, _conf);
            lastType_ = ExecTemplates.quickFormat(rootBlock,fullClassNameFound_, lastType_);
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, first_);
            classNameFound_ = polymorph_.getClassName();
        }
        return callPrepare(new DefaultExiting(_conf),_conf, classNameFound_,rootBlock, prev_, firstArgs_, _right,fct_, MethodAccessKind.INSTANCE, "");
    }

}
