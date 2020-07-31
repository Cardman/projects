package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.inherits.ExecTemplates;
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

    public RendCustArrOperation(ArrOperation _arr) {
        super(_arr);
        variable = _arr.isVariable();
        catString = _arr.isCatString();
        classMethodId = _arr.getClassMethodId();
        lastType = _arr.getLastType();
        naturalVararg = _arr.getNaturalVararg();
        anc = _arr.getAnc();
        staticChoiceMethod = _arr.isStaticChoiceMethod();
        previous = _arr.getPreviousResultClass();
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
        if (staticChoiceMethod) {
            String argClassName_ = prev_.getObjectClassName(_conf.getContext());
            classNameFound_ = ExecTemplates.quickFormat(argClassName_, classNameFound_, _conf.getContext());
            if (!ExecTemplates.isCorrectExecute(argClassName_, classNameFound_, _conf.getContext())) {
                setRelativeOffsetPossibleLastPage(chidren_.last().getIndexInEl(), _conf);
                String cast_;
                cast_ = stds_.getAliasCastType();
                _conf.setException(new ErrorStruct(_conf.getContext(), StringList.concat(argClassName_,RETURN_LINE,classNameFound_,RETURN_LINE),cast_));
                return new Argument();
            }
            String fullClassNameFound_ = ExecTemplates.getSuperGeneric(argClassName_, base_, _conf.getContext());
            lastType_ = ExecTemplates.quickFormat(fullClassNameFound_, lastType_, _conf.getContext());
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments);
            methodId_ = classMethodId.getConstraints();
        } else {
            Struct previous_ = prev_.getStruct();
            ContextEl context_ = _conf.getContext();
            ClassMethodId methodToCall_ = ExecInvokingOperation.polymorph(context_, previous_, classMethodId);
            String argClassName_ = stds_.getStructClassName(previous_, context_);
            String fullClassNameFound_ = ExecTemplates.getSuperGeneric(argClassName_, base_, _conf.getContext());
            lastType_ = ExecTemplates.quickFormat(fullClassNameFound_, lastType_, _conf.getContext());
            firstArgs_ = listArguments(chidren_, naturalVararg_, lastType_, _arguments);
            methodId_ = methodToCall_.getConstraints();
            classNameFound_ = methodToCall_.getClassName();
        }
        if (_right != null) {
            methodId_ = new MethodId(MethodAccessKind.INSTANCE,"[]=",methodId_.getParametersTypes(),methodId_.isVararg());
        }
        return ExecInvokingOperation.callPrepare(new AdvancedExiting(_conf),_conf.getContext(), classNameFound_, methodId_, prev_, firstArgs_, _right);
    }

    public ClassMethodId getClassMethodId() {
        return classMethodId;
    }

    public ClassArgumentMatching getPrevious() {
        return previous;
    }

}
