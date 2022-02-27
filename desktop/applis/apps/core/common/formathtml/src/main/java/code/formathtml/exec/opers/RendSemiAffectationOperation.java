package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;

public abstract class RendSemiAffectationOperation extends RendAbstractAffectOperation {
    private final boolean post;
    private final ExecOperatorContent operatorContent;
    private final ImplicitMethods converterTo;

    protected RendSemiAffectationOperation(ExecOperationContent _content, ExecOperatorContent _operatorContent, boolean _post, ImplicitMethods _converterTo, StringList _names) {
        super(_content, _names);
        operatorContent = _operatorContent;
        post = _post;
        converterTo = _converterTo;
    }

    @Override
    protected void calculateAffect(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        calculateSpec(_nodes, _advStandards, _context, _rendStack);
    }

    public ImplicitMethods getConverterTo() {
        return converterTo;
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
