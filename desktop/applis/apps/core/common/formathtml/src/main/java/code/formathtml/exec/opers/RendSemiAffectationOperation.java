package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticPostEltContent;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendSemiAffectationOperation extends RendAbstractUnaryOperation implements RendCallable {
    private RendDynOperationNode settable;
    private RendMethodOperation settableParent;
    private ExecStaticPostEltContent staticPostEltContent;
    private ExecOperatorContent operatorContent;
    private ExecNamedFunctionBlock named;
    private ExecRootBlock rootBlock;
    private ImplicitMethods converterFrom;
    private ImplicitMethods converterTo;

    public RendSemiAffectationOperation(ExecOperationContent _content, ExecStaticPostEltContent _staticPostEltContent, ExecOperatorContent _operatorContent, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ImplicitMethods _converterFrom, ImplicitMethods _converterTo) {
        super(_content);
        staticPostEltContent = _staticPostEltContent;
        operatorContent = _operatorContent;
        named = _named;
        rootBlock = _rootBlock;
        converterFrom = _converterFrom;
        converterTo = _converterTo;
    }

    public void setup() {
        settable = RendAffectationOperation.tryGetSettable(this);
        settableParent = RendAffectationOperation.tryGetSettableParent(this);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        if (settableParent instanceof RendSafeDotOperation) {
            RendDynOperationNode left_ = settableParent.getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                leftArg_ = new Argument(ExecClassArgumentMatching.convert(NullStruct.NULL_VALUE, _context, getResultClass().getNames()));
                setQuickConvertSimpleArgument(leftArg_, _nodes, _context);
                return;
            }
        }
        if (named != null) {
            CustList<RendDynOperationNode> list_ = getChildrenNodes();
            RendDynOperationNode left_ = list_.first();
            CustList<RendDynOperationNode> chidren_ = new CustList<RendDynOperationNode>();
            chidren_.add(left_);
            Argument stored_ = getArgument(_nodes,left_);
            Argument res_;
            res_ =  processCall(this,this, Argument.createVoid(),_nodes,_conf, null, _advStandards, _context);
            res_ = endCalculate(_nodes, _conf, stored_, res_, settable, staticPostEltContent, _advStandards, _context);
            setSimpleArgument(res_, _conf,_nodes, _context);
            return;
        }
        CustList<RendDynOperationNode> list_ = getChildrenNodes();
        RendDynOperationNode left_ = list_.first();
        Argument leftStore_ = getArgument(_nodes,left_);
        Argument stored_ = getNullArgument(_nodes, settable);
        Argument before_ = stored_;
        if (converterFrom != null) {
            Argument conv_ = tryConvert(converterFrom.getRootBlock(),converterFrom.get(0),converterFrom.getOwnerClass(), leftStore_, _context);
            stored_ = Argument.getNullableValue(conv_);
        }
        if (converterTo != null) {
            String tres_ = converterTo.get(0).getImportedParametersTypes().get(0);
            byte cast_ = ClassArgumentMatching.getPrimitiveCast(tres_, _context.getStandards().getPrimTypes());
            Argument res_;
            res_ = ExecNumericOperation.calculateIncrDecr(stored_, operatorContent.getOper(), cast_);
            Argument conv_ = tryConvert(converterTo.getRootBlock(),converterTo.get(0),converterTo.getOwnerClass(), res_, _context);
            if (conv_ == null) {
                return;
            }
            conv_ = RendAffectationOperation.calculateChSetting(settable,_nodes,_conf,conv_, _advStandards, _context);
            stored_ =  RendSemiAffectationOperation.getPrePost(staticPostEltContent.isPost(),before_,conv_);
            setSimpleArgument(stored_, _conf,_nodes, _context);
            return;
        }
        Argument arg_ = calculateSemiChSetting(_nodes, _conf, stored_, _advStandards, _context);
        setSimpleArgument(arg_, _conf,_nodes, _context);
    }

    private static Argument getNullArgument(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, RendDynOperationNode _settable) {
        Argument arg_ = null;
        if (_settable instanceof RendStdVariableOperation) {
            arg_ = getArgument(_nodes, _settable);
        }
        if (_settable instanceof RendSettableFieldOperation) {
            arg_ = getArgument(_nodes, _settable);
        }
        if (_settable instanceof RendCustArrOperation) {
            arg_ = getArgument(_nodes, _settable);
        }
        if (_settable instanceof RendArrOperation) {
            arg_ = getArgument(_nodes, _settable);
        }
        return Argument.getNullableValue(arg_);
    }

    private static Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _stored, Argument _res, RendDynOperationNode _settable, ExecStaticPostEltContent _staticPostEltContent, BeanLgNames _advStandards, ContextEl _context) {
        Argument arg_ = null;
        if (_settable instanceof RendStdVariableOperation) {
            arg_ = ((RendStdVariableOperation)_settable).endCalculate(_nodes,_conf, _staticPostEltContent.isPost(), _stored, _res, _advStandards, _context);
        }
        if (_settable instanceof RendSettableFieldOperation) {
            arg_ = ((RendSettableFieldOperation)_settable).endCalculate(_nodes,_conf, _staticPostEltContent.isPost(), _stored, _res, _advStandards, _context);
        }
        if (_settable instanceof RendCustArrOperation) {
            arg_ = ((RendCustArrOperation)_settable).endCalculate(_nodes,_conf, _staticPostEltContent.isPost(), _stored, _res, _advStandards, _context);
        }
        if (_settable instanceof RendArrOperation) {
            arg_ = ((RendArrOperation)_settable).endCalculate(_nodes,_conf, _staticPostEltContent.isPost(), _stored, _res, _advStandards, _context);
        }
        return Argument.getNullableValue(arg_);
    }

    private Argument calculateSemiChSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _stored, BeanLgNames _advStandards, ContextEl _context) {
        Argument arg_ = null;
        if (settable instanceof RendStdVariableOperation) {
            arg_ = ((RendStdVariableOperation)settable).calculateSemiSetting(_nodes, _conf, operatorContent.getOper(), staticPostEltContent.isPost(),_stored, getResultClass().getUnwrapObjectNb(), _advStandards, _context);
        }
        if (settable instanceof RendSettableFieldOperation) {
            arg_ = ((RendSettableFieldOperation)settable).calculateSemiSetting(_nodes, _conf, operatorContent.getOper(), staticPostEltContent.isPost(),_stored, getResultClass().getUnwrapObjectNb(), _advStandards, _context);
        }
        if (settable instanceof RendCustArrOperation) {
            arg_ = ((RendCustArrOperation)settable).calculateSemiSetting(_nodes, _conf, operatorContent.getOper(), staticPostEltContent.isPost(),_stored, getResultClass().getUnwrapObjectNb(), _advStandards, _context);
        }
        if (settable instanceof RendArrOperation) {
            arg_ = ((RendArrOperation)settable).calculateSemiSetting(_nodes, _conf, operatorContent.getOper(), staticPostEltContent.isPost(),_stored, getResultClass().getUnwrapObjectNb(), _advStandards, _context);
        }
        return Argument.getNullableValue(arg_);
    }

    static Argument getPrePost(boolean _post, Argument _stored, Argument _right) {
        Argument a_ = _right;
        if (_post) {
            a_ = _stored;
        }
        return a_;
    }

    @Override
    public Argument getArgument(Argument _previous, IdMap<RendDynOperationNode, ArgumentsPair> _all, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context) {
        CustList<RendDynOperationNode> list_ = getChildrenNodes();
        CustList<Argument> first_ = RendInvokingOperation.listNamedArguments(_all, list_).getArguments();
        ExecInvokingOperation.checkParametersOperators(_context.getExiting(),_context, rootBlock,named, first_, staticPostEltContent.getClassName());
        return Argument.createVoid();
    }
}
