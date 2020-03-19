package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.AffectationOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.formathtml.Configuration;
import code.util.IdMap;

public final class RendAffectationOperation extends RendMethodOperation implements RendCalculableOperation {

    private RendSettableElResult settable;

    public RendAffectationOperation(AffectationOperation _a) {
        super(_a);
    }
    public RendAffectationOperation(int _indexChild, ClassArgumentMatching _res, int _order) {
        super(_indexChild,_res,_order);
    }

    public void setup() {
        settable = tryGetSettable(this);
    }
    public static RendSettableElResult tryGetSettable(RendMethodOperation _operation) {
        RendDynOperationNode root_ = getIdOp(_operation);
        RendSettableElResult elt_;
        elt_ = castDottedTo(root_);
        return elt_;
    }

    public static RendDynOperationNode getIdOp(RendMethodOperation _operation) {
        RendDynOperationNode root_ = _operation.getFirstChild();
        while (root_ instanceof RendIdOperation) {
            root_ = root_.getFirstChild();
        }
        return root_;
    }

    public static RendSettableElResult castDottedTo(RendDynOperationNode _root) {
        RendSettableElResult elt_;
        if (!(_root instanceof RendDotOperation)) {
            elt_ = castTo(_root);
        } else {
            RendDynOperationNode beforeLast_ = ((RendMethodOperation) _root).getChildrenNodes().last();
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
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        RendDynOperationNode right_ = getChildrenNodes().last();
        Argument rightArg_ = getArgument(_nodes,right_);
        Argument arg_ = settable.calculateSetting(_nodes, _conf, rightArg_);
        setSimpleArgument(arg_, _conf,_nodes);
    }
}
