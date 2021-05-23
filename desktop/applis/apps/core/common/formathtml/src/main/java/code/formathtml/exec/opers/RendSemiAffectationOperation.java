package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.fwd.opers.ExecStaticPostEltContent;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public abstract class RendSemiAffectationOperation extends RendAbstractUnaryOperation {
    private RendDynOperationNode settable;
    private RendMethodOperation settableParent;
    private final ExecStaticPostEltContent staticPostEltContent;
    private final ExecOperatorContent operatorContent;

    protected RendSemiAffectationOperation(ExecOperationContent _content, ExecStaticPostEltContent _staticPostEltContent, ExecOperatorContent _operatorContent) {
        super(_content);
        staticPostEltContent = _staticPostEltContent;
        operatorContent = _operatorContent;
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
        calculateSpec(_nodes, _conf, _advStandards, _context, _stack, _rendStack);
    }

    protected abstract void calculateSpec(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack);

    protected ExecStaticPostEltContent getStaticPostEltContent() {
        return staticPostEltContent;
    }

    protected RendDynOperationNode getSettable() {
        return settable;
    }

    protected ExecOperatorContent getOperatorContent() {
        return operatorContent;
    }

    static Argument getPrePost(boolean _post, Argument _stored, Argument _right) {
        Argument a_ = _right;
        if (_post) {
            a_ = _stored;
        }
        return a_;
    }

}
