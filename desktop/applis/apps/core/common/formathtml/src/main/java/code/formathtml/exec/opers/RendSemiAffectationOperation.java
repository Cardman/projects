package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;

public abstract class RendSemiAffectationOperation extends RendAbstractAffectOperation {
    private final boolean post;
    private final StringList names;
    private final ExecOperatorContent operatorContent;

    protected RendSemiAffectationOperation(ExecOperationContent _content, ExecOperatorContent _operatorContent, boolean _post, StringList _names) {
        super(_content);
        operatorContent = _operatorContent;
        post = _post;
        names = _names;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        if (getSettableParent() instanceof RendSafeDotOperation) {
            RendDynOperationNode left_ = getSettableParent().getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                leftArg_ = new Argument(ExecClassArgumentMatching.convert(NullStruct.NULL_VALUE, _context, names));
                setQuickConvertSimpleArgument(leftArg_, _nodes, _context, _rendStack);
                return;
            }
        }
        calculateSpec(_nodes, _advStandards, _context, _rendStack);
    }

    protected abstract void calculateSpec(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack);

    public boolean isPost() {
        return post;
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
