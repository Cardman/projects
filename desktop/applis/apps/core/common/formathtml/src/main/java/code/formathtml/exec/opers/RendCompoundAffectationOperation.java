package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.core.StringUtil;

public abstract class RendCompoundAffectationOperation extends RendMethodOperation implements RendCalculableOperation {

    private RendDynOperationNode settable;
    private RendMethodOperation settableParent;
    private final ExecOperatorContent operatorContent;
    private final ExecStaticEltContent staticEltContent;
    private final ImplicitMethods converter;

    protected RendCompoundAffectationOperation(ExecOperationContent _content, ExecOperatorContent _operatorContent, ExecStaticEltContent _staticEltContent, ImplicitMethods _converter) {
        super(_content);
        operatorContent = _operatorContent;
        staticEltContent = _staticEltContent;
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
        Argument leftArg_ = getArgument(_nodes,left_);
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
        calculateSpec(_nodes, _conf, _advStandards, _context, _stack, _rendStack);
    }

    protected static Argument endCalculateCh(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _res, RendDynOperationNode _settable, BeanLgNames _advStandards, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall) {
        Argument arg_ = null;
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
    protected abstract void calculateSpec(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack);

    protected ExecOperatorContent getOperatorContent() {
        return operatorContent;
    }

    protected ExecStaticEltContent getStaticEltContent() {
        return staticEltContent;
    }

    protected ImplicitMethods getConverter() {
        return converter;
    }

    public String getOper() {
        return operatorContent.getOper();
    }

    public RendDynOperationNode getSettable() {
        return settable;
    }
}
