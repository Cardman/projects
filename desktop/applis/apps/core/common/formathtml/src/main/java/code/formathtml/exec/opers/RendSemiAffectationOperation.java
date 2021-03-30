package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.opers.ExecNumericOperation;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticPostEltContent;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendSemiAffectationOperation extends RendAbstractUnaryOperation {
    private final ExecTypeFunction pair;
    private RendDynOperationNode settable;
    private RendMethodOperation settableParent;
    private final ExecStaticPostEltContent staticPostEltContent;
    private final ExecOperatorContent operatorContent;
    private final ImplicitMethods converterFrom;
    private final ImplicitMethods converterTo;

    public RendSemiAffectationOperation(ExecOperationContent _content, ExecStaticPostEltContent _staticPostEltContent, ExecOperatorContent _operatorContent, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ImplicitMethods _converterFrom, ImplicitMethods _converterTo) {
        super(_content);
        staticPostEltContent = _staticPostEltContent;
        operatorContent = _operatorContent;
        pair = new ExecTypeFunction(_rootBlock,_named);
        converterFrom = _converterFrom;
        converterTo = _converterTo;
    }

    public void setup() {
        settable = RendAffectationOperation.tryGetSettable(this);
        settableParent = RendAffectationOperation.tryGetSettableParent(this);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        if (settableParent instanceof RendSafeDotOperation) {
            RendDynOperationNode left_ = settableParent.getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                leftArg_ = new Argument(ExecClassArgumentMatching.convert(NullStruct.NULL_VALUE, _context, getResultClass().getNames()));
                setQuickConvertSimpleArgument(leftArg_, _nodes, _context, _stack);
                return;
            }
        }
        if (pair.getFct() != null) {
            RendDynOperationNode left_ = getFirstNode(this);
            Argument stored_ = getArgument(_nodes,left_);
            RendInvokingOperation.checkParametersOperatorsFormatted(_context.getExiting(), _context, pair, _nodes, this, staticPostEltContent.getClassName(), staticPostEltContent.getKind(), _stack);
            Argument res_ = RendDynOperationNode.processCall(Argument.createVoid(), _context, _stack).getValue();
            res_ = endCalculate(_nodes, _conf, stored_, res_, settable, staticPostEltContent, _advStandards, _context, _stack, _rendStack);
            setSimpleArgument(res_, _nodes, _context, _stack, _rendStack);
            return;
        }
        RendDynOperationNode left_ = getFirstNode(this);
        Argument leftStore_ = getArgument(_nodes,left_);
        Argument stored_ = getNullArgument(_nodes, settable);
        Argument before_ = stored_;
        if (converterFrom != null) {
            Argument conv_ = tryConvert(converterFrom.get(0),converterFrom.getOwnerClass(), leftStore_, _context, _stack);
            stored_ = Argument.getNullableValue(conv_);
        }
        if (converterTo != null) {
            String tres_ = converterTo.get(0).getFct().getImportedParametersTypes().get(0);
            byte cast_ = ClassArgumentMatching.getPrimitiveCast(tres_, _context.getStandards().getPrimTypes());
            Argument res_ = ExecNumericOperation.calculateIncrDecr(stored_, operatorContent.getOper(), cast_);
            Argument conv_ = tryConvert(converterTo.get(0),converterTo.getOwnerClass(), res_, _context, _stack);
            if (conv_ == null) {
                return;
            }
            conv_ = RendAffectationOperation.calculateChSetting(settable,_nodes,_conf,conv_, _advStandards, _context, _stack, _rendStack);
            stored_ =  RendSemiAffectationOperation.getPrePost(staticPostEltContent.isPost(),before_,conv_);
            setSimpleArgument(stored_, _nodes, _context, _stack, _rendStack);
            return;
        }
        Argument arg_ = calculateSemiChSetting(_nodes, _conf, stored_, _advStandards, _context, _stack, _rendStack);
        setSimpleArgument(arg_, _nodes, _context, _stack, _rendStack);
    }

    private static Argument getNullArgument(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, RendDynOperationNode _settable) {
        return getArgument(_nodes, _settable);
    }

    private static Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _stored, Argument _res, RendDynOperationNode _settable, ExecStaticPostEltContent _staticPostEltContent, BeanLgNames _advStandards, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        Argument arg_ = null;
        if (_settable instanceof RendStdRefVariableOperation) {
            arg_ = ((RendStdRefVariableOperation)_settable).endCalculate(_nodes,_conf, _staticPostEltContent.isPost(), _stored, _res, _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (_settable instanceof RendSettableFieldOperation) {
            arg_ = ((RendSettableFieldOperation)_settable).endCalculate(_nodes,_conf, _staticPostEltContent.isPost(), _stored, _res, _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (_settable instanceof RendCustArrOperation) {
            arg_ = ((RendCustArrOperation)_settable).endCalculate(_nodes,_conf, _staticPostEltContent.isPost(), _stored, _res, _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (_settable instanceof RendArrOperation) {
            arg_ = ((RendArrOperation)_settable).endCalculate(_nodes,_conf, _staticPostEltContent.isPost(), _stored, _res, _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (_settable instanceof RendSettableCallFctOperation) {
            arg_ = ((RendSettableCallFctOperation)_settable).endCalculate(_nodes,_conf, _staticPostEltContent.isPost(), _stored, _res, _advStandards, _context, _stackCall, _rendStackCall);
        }
        return Argument.getNullableValue(arg_);
    }

    private Argument calculateSemiChSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _stored, BeanLgNames _advStandards, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        Argument arg_ = null;
        if (settable instanceof RendStdRefVariableOperation) {
            arg_ = ((RendStdRefVariableOperation)settable).calculateSemiSetting(_nodes, _conf, operatorContent.getOper(), staticPostEltContent.isPost(),_stored, getResultClass().getUnwrapObjectNb(), _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (settable instanceof RendSettableFieldOperation) {
            arg_ = ((RendSettableFieldOperation)settable).calculateSemiSetting(_nodes, _conf, operatorContent.getOper(), staticPostEltContent.isPost(),_stored, getResultClass().getUnwrapObjectNb(), _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (settable instanceof RendCustArrOperation) {
            arg_ = ((RendCustArrOperation)settable).calculateSemiSetting(_nodes, _conf, operatorContent.getOper(), staticPostEltContent.isPost(),_stored, getResultClass().getUnwrapObjectNb(), _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (settable instanceof RendArrOperation) {
            arg_ = ((RendArrOperation)settable).calculateSemiSetting(_nodes, _conf, operatorContent.getOper(), staticPostEltContent.isPost(),_stored, getResultClass().getUnwrapObjectNb(), _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (settable instanceof RendSettableCallFctOperation) {
            arg_ = ((RendSettableCallFctOperation)settable).calculateSemiSetting(_nodes, _conf, operatorContent.getOper(), staticPostEltContent.isPost(),_stored, getResultClass().getUnwrapObjectNb(), _advStandards, _context, _stackCall, _rendStackCall);
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

}
