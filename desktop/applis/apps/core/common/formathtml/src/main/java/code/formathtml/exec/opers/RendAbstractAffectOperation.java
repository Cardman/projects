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

    private RendDynOperationNode settableAnc;
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
        settableAnc = anc(this);
    }
    protected Argument endCalculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _stored, Argument _res, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStackCall, boolean _post) {
        Argument arg_ = null;
        RendDynOperationNode settable_ = getSettable();
        if (settable_ instanceof RendStdRefVariableOperation) {
            arg_ = ((RendStdRefVariableOperation)settable_).endCalculate(_nodes, _post, _stored, _res, _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendSettableFieldOperation) {
            arg_ = ((RendSettableFieldOperation)settable_).endCalculate(_nodes, _post, _stored, _res, _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendCustArrOperation) {
            arg_ = ((RendCustArrOperation)settable_).endCalculate(_nodes, _post, _stored, _res, _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendArrOperation) {
            arg_ = ((RendArrOperation)settable_).endCalculate(_nodes, _post, _stored, _res, _advStandards, _context, _rendStackCall);
        }
        if (settable_ instanceof RendSettableCallFctOperation) {
            arg_ = ((RendSettableCallFctOperation)settable_).endCalculate(_nodes, _post, _stored, _res, _advStandards, _context, _rendStackCall);
        }
        return Argument.getNullableValue(arg_);
    }

    public static RendDynOperationNode tryGetSettable(RendAbstractAffectOperation _operation) {
        RendDynOperationNode root_ = getCastIdOp(_operation);
        return castDottedTo(root_);
    }
    public static RendMethodOperation tryGetSettableParent(RendAbstractAffectOperation _operation) {
        RendDynOperationNode root_ = getCastIdOp(_operation);
        return castParentTo(root_);
    }

    public static RendDynOperationNode getIdOp(RendAbstractAffectOperation _operation) {
        RendDynOperationNode root_ = anc(_operation);
        if (root_ instanceof RendNamedArgumentOperation) {
            root_ = root_.getFirstChild();
        }
        return deepId(root_);
    }

    public static RendDynOperationNode deepId(RendDynOperationNode _root) {
        RendDynOperationNode root_ = _root;
        while (root_ instanceof RendIdOperation) {
            root_ = root_.getFirstChild();
        }
        return root_;
    }

    public static RendDynOperationNode getCastIdOp(RendAbstractAffectOperation _operation) {
        RendDynOperationNode root_ = getIdOp(_operation);
        if (root_ instanceof RendCastOperation) {
            root_ = root_.getFirstChild();
        }
        return deepId(root_);
    }

    private static RendDynOperationNode anc(RendAbstractAffectOperation _operation) {
        RendDynOperationNode root_ = _operation.getFirstChild();
        if (RendConstLeafOperation.isFilter(root_)) {
            root_ = root_.getNextSibling();
        }
        RendDynOperationNode next_ = root_;
        while (next_ != null) {
            if (!(next_ instanceof RendNamedArgumentOperation) || ((RendNamedArgumentOperation) next_).getIndex() == 0) {
                root_ = next_;
                next_ = null;
                continue;
            }
            next_ = next_.getNextSibling();
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

    public RendDynOperationNode getSettableAnc() {
        return settableAnc;
    }

    public RendDynOperationNode getSettable() {
        return settable;
    }

    public RendMethodOperation getSettableParent() {
        return settableParent;
    }
}
