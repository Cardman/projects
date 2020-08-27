package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.ArrOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;
import code.util.StringList;

public final class RendCustArrOperation extends RendInvokingOperation implements RendCalculableOperation,RendSettableElResult,RendCallable {

    private boolean variable;

    private boolean catString;

    private ClassMethodId classMethodId;

    private String lastType;

    private int naturalVararg;

    private int anc;

    private boolean staticChoiceMethod;
    private ClassArgumentMatching previous;
    private ExecNamedFunctionBlock get;
    private ExecNamedFunctionBlock set;
    private ExecRootBlock rootBlock;

    public RendCustArrOperation(ArrOperation _arr, ExecNamedFunctionBlock _get, ExecNamedFunctionBlock _set, ExecRootBlock _rootBlock) {
        super(_arr);
        variable = _arr.isVariable();
        catString = _arr.isCatString();
        classMethodId = _arr.getClassMethodId();
        lastType = _arr.getLastType();
        naturalVararg = _arr.getNaturalVararg();
        anc = _arr.getAnc();
        staticChoiceMethod = _arr.isStaticChoiceMethod();
        previous = _arr.getPreviousResultClass();
        get = _get;
        set = _set;
        rootBlock = _rootBlock;
    }
    public RendCustArrOperation(RendCustArrOperation _arr,int _indexChild, ClassArgumentMatching _res, int _order,
                            boolean _intermediate, Argument _previousArgument) {
        super(_indexChild,_res,_order, _intermediate, _previousArgument);
        previous = _arr.previous;
        classMethodId = _arr.classMethodId;
        lastType = _arr.lastType;
        naturalVararg = _arr.naturalVararg;
        anc = _arr.anc;
        staticChoiceMethod = _arr.staticChoiceMethod;
        variable = true;
        get = _arr.get;
        set = _arr.set;
        rootBlock = _arr.rootBlock;
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        if (resultCanBeSet()) {
            Struct array_;
            array_ = getPreviousArgument(_nodes,this).getStruct();
            Argument a_ = new Argument();
            a_.setStruct(array_);
            setQuickNoConvertSimpleArgument(a_, _conf,_nodes);
            return;
        }
        processCalling(_nodes,_conf, null);
    }

    @Override
    public boolean resultCanBeSet() {
        return variable;
    }

    @Override
    public Argument calculateSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right) {
        return processCalling(_nodes,_conf, _right);
    }

    @Override
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        Argument left_ = new Argument();
        left_.setStruct(store_);
        ClassArgumentMatching clArg_ = getResultClass();
        Argument res_;
        res_ = RendNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, clArg_);
        if (_conf.getContext().hasException()) {
            return res_;
        }
        return processCalling(_nodes,_conf,res_);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post, Argument _stored) {
        ClassArgumentMatching clArg_ = getResultClass();
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(_stored, _conf.getContext(), _op, clArg_);
        Argument arg_ = processCalling(_nodes, _conf, res_);
        return RendSemiAffectationOperation.getPrePost(_post,_stored,arg_);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right) {
        return processCalling(_nodes, _conf, _right);
    }

    @Override
    public Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, boolean _post, Argument _stored, Argument _right) {
        processCalling(_nodes, _conf, _right);
        return RendSemiAffectationOperation.getPrePost(_post,_stored,_right);
    }

    private Argument processCalling(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument previous_ = getPreviousArgument(_nodes,this);
        Argument argres_ = processCall(this, this, previous_, arguments_, _conf, _right);
        setSimpleArgument(argres_,_conf,_nodes);
        return argres_;
    }

    public Argument getArgument(Argument _previous, CustList<Argument> _arguments, Configuration _conf, Argument _right) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        LgNames stds_ = _conf.getStandards();
        CustList<Argument> firstArgs_;
        MethodId methodId_;
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        Argument prev_ = new Argument();
        classNameFound_ = classMethodId.getClassName();
        Struct argPrev_ = _previous.getStruct();
        prev_.setStruct(ExecTemplates.getParent(anc, classNameFound_, argPrev_, _conf.getContext()));
        if (_conf.getContext().hasException()) {
            return new Argument();
        }
        String base_ = StringExpUtil.getIdFromAllTypes(classNameFound_);
        ExecNamedFunctionBlock fct_;
        if (_right != null) {
            fct_ = set;
        } else {
            fct_ = get;
        }
        if (staticChoiceMethod) {
            String argClassName_ = prev_.getObjectClassName(_conf.getContext());
            String fullClassNameFound_ = ExecTemplates.getSuperGeneric(argClassName_, base_, _conf.getContext());
            lastType_ = ExecTemplates.quickFormat(rootBlock,fullClassNameFound_, lastType_);
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments);
            methodId_ = classMethodId.getConstraints();
        } else {
            Struct previous_ = prev_.getStruct();
            ContextEl context_ = _conf.getContext();
            ExecOverrideInfo polymorph_ = ExecInvokingOperation.polymorph(context_, previous_, rootBlock, fct_);
            fct_ = polymorph_.getOverridableBlock();
            String argClassName_ = stds_.getStructClassName(previous_, context_);
            String fullClassNameFound_ = ExecTemplates.getSuperGeneric(argClassName_, base_, _conf.getContext());
            lastType_ = ExecTemplates.quickFormat(rootBlock,fullClassNameFound_, lastType_);
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments);
            methodId_ = classMethodId.getConstraints();
            classNameFound_ = polymorph_.getClassName();
        }
        if (_right != null) {
            methodId_ = new MethodId(MethodAccessKind.INSTANCE,"[]=",methodId_.getParametersTypes(),methodId_.isVararg());
        }
        return ExecInvokingOperation.callPrepare(new AdvancedExiting(_conf),_conf.getContext(), classNameFound_,rootBlock, methodId_, prev_, firstArgs_, _right,fct_);
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public ClassArgumentMatching getPrevious() {
        return previous;
    }

}
