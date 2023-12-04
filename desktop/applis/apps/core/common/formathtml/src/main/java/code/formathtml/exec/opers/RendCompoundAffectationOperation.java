package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.CompoundedOperator;
import code.expressionlanguage.exec.opers.ExecCompoundAffectationOperation;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;
import code.util.StringList;

public abstract class RendCompoundAffectationOperation extends RendAbstractAffectOperation implements CompoundedOperator {

    private final ExecOperatorContent operatorContent;
    private final ImplicitMethods converter = new ImplicitMethods();
    private final boolean staticPostEltContent;

    protected RendCompoundAffectationOperation(ExecOperationContent _content, ExecOperatorContent _operatorContent, StringList _names, boolean _staticPostEltContent) {
        super(_content, _names);
        operatorContent = _operatorContent;
        staticPostEltContent = _staticPostEltContent;
    }

    static void process(RendCompoundAffectationOperation _curr, IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack, Argument _res) {
        Argument res_ = _res;
        ImplicitMethods converter_ = _curr.getConverter();
        if (!converter_.isEmpty()) {
            Argument conv_ = tryConvert(converter_, res_, _context, _rendStack);
            if (conv_ == null) {
                return;
            }
            res_ = conv_;
        }
        Argument leftArg_ = firstArg(_curr, _nodes);
        _curr.calculateChSetting(_nodes, res_, _context, _rendStack);
        Argument arg_ = getPrePost(_curr.isStaticPostEltContent(),leftArg_, res_);
//        Argument arg_ = endCalculate(_nodes,leftArg_, res_, _advStandards, _context, _rendStack,isStaticPostEltContent());
        _curr.setSimpleArgument(arg_, _nodes, _context, _rendStack);
    }

    static Argument getPrePost(boolean _post, Argument _stored, Argument _right) {
        Argument a_ = _right;
        if (_post) {
            a_ = _stored;
        }
        return a_;
    }

    @Override
    protected void calculateAffect(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        ArgumentsPair argumentPair_ = getArgumentPair(_nodes, getSettableAnc());
        Argument leftArg_ = Argument.getNullableValue(argumentPair_.getArgument());
        if (argumentPair_.isArgumentTest()){
            if (ExecCompoundAffectationOperation.sh(operatorContent)) {
                setSimpleArgument(leftArg_, _nodes, _context, _rendStack);
                return;
            }
            Argument arg_ = calculateChSetting(_nodes, leftArg_, _context, _rendStack);
            setSimpleArgument(arg_, _nodes, _context, _rendStack);
            return;
        }
        calculateSpec(_nodes, _context, _rendStack);
    }

    protected abstract void calculateSpec(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack);

    public boolean isStaticPostEltContent() {
        return staticPostEltContent;
    }

    protected ExecOperatorContent getOperatorContent() {
        return operatorContent;
    }

    public ImplicitMethods getConverter() {
        return converter;
    }

    @Override
    public String getOper() {
        return getOperatorContent().getOper();
    }
}
