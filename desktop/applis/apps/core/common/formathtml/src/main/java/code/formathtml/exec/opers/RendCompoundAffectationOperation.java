package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;
import code.util.core.StringUtil;

public final class RendCompoundAffectationOperation extends RendMethodOperation implements RendCalculableOperation {

    private final ExecTypeFunction pair;
    private RendDynOperationNode settable;
    private RendMethodOperation settableParent;
    private final ExecOperatorContent operatorContent;
    private final ExecStaticEltContent staticEltContent;
    private final ImplicitMethods converter;

    public RendCompoundAffectationOperation(ExecOperationContent _content, ExecOperatorContent _operatorContent, ExecStaticEltContent _staticEltContent, ExecNamedFunctionBlock _named, ExecRootBlock _rootBlock, ImplicitMethods _converter) {
        super(_content);
        operatorContent = _operatorContent;
        staticEltContent = _staticEltContent;
        pair = new ExecTypeFunction(_rootBlock,_named);
        converter = _converter;
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
        RendDynOperationNode left_ = getFirstNode(this);
        RendDynOperationNode right_ = getLastNode(this);
        Argument leftArg_ = getArgument(_nodes,left_);
        Argument rightArg_ = getArgument(_nodes,right_);
        ArgumentsPair argumentPair_ = getArgumentPair(_nodes, left_);
        if (argumentPair_.isArgumentTest()){
            if (StringUtil.quickEq(operatorContent.getOper(), "&&&=") || StringUtil.quickEq(operatorContent.getOper(), "|||=")) {
                setSimpleArgument(leftArg_, _nodes, _context, _stack, _rendStack);
                return;
            }
            Argument arg_ = RendAffectationOperation.calculateChSetting(settable,_nodes, _conf, leftArg_, _advStandards, _context, _stack, _rendStack);
            setSimpleArgument(arg_, _nodes, _context, _stack, _rendStack);
            return;
        }
        if (pair.getFct() != null) {
            RendInvokingOperation.checkParametersOperatorsFormatted(_context.getExiting(), _context, pair, _nodes, this, staticEltContent.getClassName(), staticEltContent.getKind(), _stack);
            Argument res_ = RendDynOperationNode.processCall(Argument.createVoid(), _context, _stack).getValue();
            if (converter != null) {
                Argument conv_ = tryConvert(converter.get(0),converter.getOwnerClass(), res_, _context, _stack);
                if (conv_ == null) {
                    return;
                }
                res_ = conv_;
            }
            Argument arg_ = endCalculateCh(_nodes, _conf, res_, settable, _advStandards, _context, _stack, _rendStack);
            setSimpleArgument(arg_, _nodes, _context, _stack, _rendStack);
            return;
        }
        if (StringUtil.quickEq(operatorContent.getOper(), "???=")) {
            if (!leftArg_.isNull()) {
                setSimpleArgument(leftArg_, _nodes, _context, _stack, _rendStack);
                return;
            }
        }
        if (converter != null) {
            String tres_ = converter.get(0).getFct().getImportedParametersTypes().get(0);
            StringList argType_ = new StringList(tres_);
            byte cast_ = ClassArgumentMatching.getPrimitiveCast(tres_, _context.getStandards().getPrimTypes());
            Argument res_ = RendNumericOperation.calculateAffect(leftArg_, rightArg_, operatorContent.getOper(), false, argType_, cast_, _context, _stack);
            Argument conv_ = tryConvert(converter.get(0),converter.getOwnerClass(), res_, _context, _stack);
            if (conv_ == null) {
                return;
            }
            res_ = conv_;
            Argument arg_ = endCalculateCh(_nodes, _conf, res_, settable, _advStandards, _context, _stack, _rendStack);
            setSimpleArgument(arg_, _nodes, _context, _stack, _rendStack);
            return;
        }
        Argument arg_ = calculateCompoundChSetting(_nodes, _conf, rightArg_, _advStandards, _context, _stack, _rendStack);
        setSimpleArgument(arg_, _nodes, _context, _stack, _rendStack);
    }

    private static Argument endCalculateCh(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _res, RendDynOperationNode _settable, BeanLgNames _advStandards, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        Argument arg_ = null;
        if (_settable instanceof RendStdVariableOperation) {
            arg_ = ((RendStdVariableOperation)_settable).endCalculate(_nodes, _conf, _res, _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (_settable instanceof RendStdRefVariableOperation) {
            arg_ = ((RendStdRefVariableOperation)_settable).endCalculate(_nodes, _conf, _res, _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (_settable instanceof RendSettableFieldOperation) {
            arg_ = ((RendSettableFieldOperation)_settable).endCalculate(_nodes, _conf, _res, _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (_settable instanceof RendArrOperation) {
            arg_ = ((RendArrOperation)_settable).endCalculate(_nodes, _conf, _res, _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (_settable instanceof RendCustArrOperation) {
            arg_ = ((RendCustArrOperation)_settable).endCalculate(_nodes, _conf, _res, _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (_settable instanceof RendSettableCallFctOperation) {
            arg_ = ((RendSettableCallFctOperation)_settable).endCalculate(_nodes, _conf, _res, _advStandards, _context, _stackCall, _rendStackCall);
        }
        return Argument.getNullableValue(arg_);
    }

    private Argument calculateCompoundChSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _rightArg, BeanLgNames _advStandards, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        Argument arg_ = null;
        if (settable instanceof RendStdVariableOperation) {
            arg_ = ((RendStdVariableOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (settable instanceof RendStdRefVariableOperation) {
            arg_ = ((RendStdRefVariableOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (settable instanceof RendSettableFieldOperation) {
            arg_ = ((RendSettableFieldOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (settable instanceof RendArrOperation) {
            arg_ = ((RendArrOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (settable instanceof RendCustArrOperation) {
            arg_ = ((RendCustArrOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (settable instanceof RendSettableCallFctOperation) {
            arg_ = ((RendSettableCallFctOperation)settable).calculateCompoundSetting(_nodes, _conf, operatorContent.getOper(), _rightArg, getResultClass(), getResultClass().getUnwrapObjectNb(), _advStandards, _context, _stackCall, _rendStackCall);
        }
        return Argument.getNullableValue(arg_);
    }

    public String getOper() {
        return operatorContent.getOper();
    }

    public RendDynOperationNode getSettable() {
        return settable;
    }
}
