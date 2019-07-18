package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.AffectationOperation;

public final class RendAffectationOperation extends RendMethodOperation implements RendCalculableOperation {

    private RendSettableElResult settable;

    public RendAffectationOperation(AffectationOperation _a) {
        super(_a);
    }

    public void setup() {
        settable = tryGetSettable(this);
    }
    static RendSettableElResult tryGetSettable(RendMethodOperation _operation) {
        RendDynOperationNode root_ = _operation.getFirstChild();
        RendSettableElResult elt_;
        while (root_ instanceof RendIdOperation) {
            root_ = ((RendIdOperation)root_).getFirstChild();
        }
        if (!(root_ instanceof RendDotOperation)) {
            elt_ = castTo(root_);
        } else {
            RendDynOperationNode beforeLast_ = ((RendMethodOperation)root_).getChildrenNodes().last();
            elt_ = castTo(beforeLast_);
        }
        return elt_;
    }
    private static RendSettableElResult castTo(RendDynOperationNode _op) {
        if (_op instanceof RendSettableElResult) {
            return (RendSettableElResult) _op;
        }
        return null;
    }
    public RendSettableElResult getSettable() {
        return settable;
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        RendDynOperationNode right_ = getChildrenNodes().last();
        Argument rightArg_ = right_.getArgument();
        settable.calculateSetting(_conf, rightArg_);
        RendDynOperationNode op_ = (RendDynOperationNode)settable;
        setSimpleArgument(op_.getArgument(), _conf);
    }

}
