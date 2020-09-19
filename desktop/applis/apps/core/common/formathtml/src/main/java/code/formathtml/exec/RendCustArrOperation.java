package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.ExecOverrideInfo;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.ArrOperation;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.CustList;
import code.util.IdMap;

public final class RendCustArrOperation extends RendInvokingOperation implements RendCalculableOperation,RendSettableElResult,RendCallable {

    private boolean variable;

    private boolean catString;

    private ClassMethodId classMethodId;
    private String className;

    private String lastType;

    private int naturalVararg;

    private int anc;

    private boolean staticChoiceMethod;
    private ExecClassArgumentMatching previous;
    private ExecNamedFunctionBlock get;
    private ExecNamedFunctionBlock set;
    private ExecRootBlock rootBlock;

    public RendCustArrOperation(ArrOperation _arr, ExecNamedFunctionBlock _get, ExecNamedFunctionBlock _set, ExecRootBlock _rootBlock) {
        super(_arr);
        variable = _arr.isVariable();
        catString = _arr.isCatString();
        classMethodId = _arr.getClassMethodId();
        className = ExecOperationNode.getType(_arr.getClassMethodId());
        lastType = _arr.getLastType();
        naturalVararg = _arr.getNaturalVararg();
        anc = _arr.getAnc();
        staticChoiceMethod = _arr.isStaticChoiceMethod();
        previous = PrimitiveTypeUtil.toExec(_arr.getPreviousResultClass());
        get = _get;
        set = _set;
        rootBlock = _rootBlock;
    }
    public RendCustArrOperation(RendCustArrOperation _arr, int _indexChild, ExecClassArgumentMatching _res, int _order,
                                boolean _intermediate) {
        super(_indexChild,_res,_order, _intermediate);
        className = _arr.className;
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
            Argument a_ = new Argument(array_);
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
    public Argument calculateCompoundSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, Argument _right, ExecClassArgumentMatching _cl, byte _cast) {
        Argument a_ = getArgument(_nodes,this);
        Struct store_;
        store_ = a_.getStruct();
        Argument left_ = new Argument(store_);
        Argument res_;
        res_ = RendNumericOperation.calculateAffect(left_, _conf, _right, _op, catString, _cl.getNames(), _cast);
        if (_conf.getContext().hasException()) {
            return res_;
        }
        return processCalling(_nodes,_conf,res_);
    }

    @Override
    public Argument calculateSemiSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, String _op, boolean _post, Argument _stored, byte _cast) {
        Argument res_;
        res_ = ExecNumericOperation.calculateIncrDecr(_stored, _op, _cast);
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
        Argument previous_ = getPreviousArgument(_nodes,this);
        Argument argres_ = processCall(this, this, previous_,_nodes,  _conf, _right);
        setSimpleArgument(argres_,_conf,_nodes);
        return argres_;
    }

    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        CustList<Argument> firstArgs_;
        String lastType_ = lastType;
        int naturalVararg_ = naturalVararg;
        String classNameFound_;
        classNameFound_ = className;
        Struct argPrev_ = _previous.getStruct();
        ContextEl ctx_ = _conf.getContext();
        Argument prev_ = new Argument(ExecTemplates.getParent(anc, classNameFound_, argPrev_, ctx_));
        if (ctx_.hasException()) {
            return new Argument();
        }
        String base_ = StringExpUtil.getIdFromAllTypes(classNameFound_);
        ExecNamedFunctionBlock fct_;
        if (_right != null) {
            fct_ = set;
        } else {
            fct_ = get;
        }
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, chidren_).getArguments();
        Struct pr_ = prev_.getStruct();
        String cl_ = pr_.getClassName(ctx_);
        String clGen_ = ExecTemplates.getSuperGeneric(cl_, base_, ctx_);
        lastType_ = ExecTemplates.quickFormat(rootBlock, clGen_, lastType_);
        firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, first_);
        if (!staticChoiceMethod) {
            ExecOverrideInfo polymorph_ = ExecInvokingOperation.polymorph(ctx_, pr_, rootBlock, fct_);
            fct_ = polymorph_.getOverridableBlock();
            classNameFound_ = polymorph_.getClassName();
        }
        return ExecInvokingOperation.callPrepare(new AdvancedExiting(_conf), ctx_, classNameFound_,rootBlock, prev_, firstArgs_, _right,fct_, MethodAccessKind.INSTANCE, "");
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public ExecClassArgumentMatching getPrevious() {
        return previous;
    }

}
