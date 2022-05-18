package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.formathtml.exec.RendStackCall;
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
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        if (getSettableParent() instanceof RendSafeDotOperation && getArgument(_nodes, getSettableParent().getFirstChild()).isNull()) {
            setQuickConvertSimpleArgument(new Argument(ExecClassArgumentMatching.convertFormatted(NullStruct.NULL_VALUE, _context, names,_rendStack)), _nodes, _context, _rendStack);
            return;
        }
        calculateAffect(_nodes, _context, _rendStack);
    }

    protected abstract void calculateAffect(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack);

    Argument calculateChSetting(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, ContextEl _context, RendStackCall _rendStackCall) {
        return calculateChSetting(getSettable(),_nodes,_right, _context,_rendStackCall);
    }
    static Argument calculateChSetting(RendDynOperationNode _set,
                                       IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Argument _right, ContextEl _context, RendStackCall _rendStackCall){
        Argument arg_ = null;
        if (_set instanceof RendStdRefVariableOperation) {
            arg_ = ((RendStdRefVariableOperation)_set).calculateSetting(_nodes, _right, _context, _rendStackCall);
        }
        if (_set instanceof RendSettableFieldOperation) {
            arg_ = ((RendSettableFieldOperation)_set).calculateSetting(_nodes, _right, _context, _rendStackCall);
        }
        if (_set instanceof RendCustArrWriteOperation) {
            arg_ = ((RendCustArrWriteOperation)_set).calculateSetting(_nodes, _right, _context, _rendStackCall);
        }
        if (_set instanceof RendCustArrOperation) {
            arg_ = ((RendCustArrOperation)_set).calculateSetting(_nodes, _right, _context, _rendStackCall);
        }
        if (_set instanceof RendArrOperation) {
            arg_ = ((RendArrOperation)_set).calculateSetting(_nodes, _right, _context, _rendStackCall);
        }
        if (_set instanceof RendSettableCallFctOperation) {
            arg_ = ((RendSettableCallFctOperation)_set).calculateSetting(_nodes, _right, _context, _rendStackCall);
        }
        return Argument.getNullableValue(arg_);
    }

    public void setup() {
        settable = tryGetSettable(this);
        settableParent = tryGetSettableParent(this);
        settableAnc = anc(this);
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
            root_ = ((RendNamedArgumentOperation)root_).getFirstChild();
        }
        return deepId(root_);
    }

    public static RendDynOperationNode deepId(RendDynOperationNode _root) {
        RendDynOperationNode root_ = _root;
        while (root_ instanceof RendIdOperation) {
            root_ = ((RendIdOperation)root_).getFirstChild();
        }
        return root_;
    }

    public static RendDynOperationNode getCastIdOp(RendAbstractAffectOperation _operation) {
        RendDynOperationNode root_ = getIdOp(_operation);
        if (root_ instanceof RendCastOperation) {
            root_ = ((RendCastOperation)root_).getFirstChild();
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

    protected static Argument firstArg(RendAbstractAffectOperation _current, IdMap<RendDynOperationNode, ArgumentsPair> _nodes) {
        ArgumentsPair pairSet_ = getArgumentPair(_nodes, _current.getSettable());
        return Argument.getNullableValue(pairSet_.getArgumentBeforeImpl());
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
