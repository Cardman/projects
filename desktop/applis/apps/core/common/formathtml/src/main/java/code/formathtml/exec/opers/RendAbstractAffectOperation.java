package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;
import code.util.StringList;

public abstract class RendAbstractAffectOperation extends RendMethodOperation implements RendCalculableOperation {

    private RendDynOperationNode settable;
    private RendMethodOperation settableParent;

    private final StringList names;
    protected RendAbstractAffectOperation(ExecOperationContent _content, StringList _names) {
        super(_content);
        names = _names;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        if (getSettableParent() instanceof RendSafeDotOperation && getArgument(_nodes, getSettableParent().getFirstChild()).isNull()) {
            setQuickConvertSimpleArgument(new Argument(ExecClassArgumentMatching.convert(NullStruct.NULL_VALUE, _context, names)), _nodes, _context, _rendStack);
            return;
        }
        calculateAffect(_nodes, _advStandards, _context, _rendStack);
    }

    protected abstract void calculateAffect(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack);
    public void setup() {
        settable = tryGetSettable(this);
        settableParent = tryGetSettableParent(this);
    }
    public static RendDynOperationNode tryGetSettable(RendMethodOperation _operation) {
        RendDynOperationNode root_ = getCastIdOp(_operation);
        return castDottedTo(root_);
    }
    public static RendMethodOperation tryGetSettableParent(RendMethodOperation _operation) {
        RendDynOperationNode root_ = getCastIdOp(_operation);
        return castParentTo(root_);
    }

    public static RendDynOperationNode getIdOp(RendMethodOperation _operation) {
        RendDynOperationNode root_ = _operation.getFirstChild();
        while (root_ instanceof RendIdOperation) {
            root_ = root_.getFirstChild();
        }
        return root_;
    }

    public static RendDynOperationNode getCastIdOp(RendMethodOperation _operation) {
        RendDynOperationNode root_ = getIdOp(_operation);
        if (root_ instanceof RendCastOperation) {
            root_ = root_.getFirstChild();
        }
        return root_;
    }
    public static RendMethodOperation castParentTo(RendDynOperationNode _root) {
        RendMethodOperation elt_;
        if (!(_root instanceof RendAbstractDotOperation)) {
            elt_ = getParentOrNull(_root);
        } else {
            elt_ = (RendMethodOperation) _root;
        }
        return elt_;
    }

    public static RendDynOperationNode castDottedTo(RendDynOperationNode _root) {
        RendDynOperationNode elt_;
        if (!(_root instanceof RendAbstractDotOperation)) {
            elt_ = _root;
        } else {
            elt_ = getLastNode((RendMethodOperation) _root);
        }
        return elt_;
    }

    protected StringList getNames() {
        return names;
    }

    public RendDynOperationNode getSettable() {
        return settable;
    }

    public RendMethodOperation getSettableParent() {
        return settableParent;
    }
}
