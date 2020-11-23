package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendAffectationOperation extends RendMethodOperation implements RendCalculableOperation {

    private RendDynOperationNode settable;
    private RendMethodOperation settableParent;

    public RendAffectationOperation(ExecOperationContent _content) {
        super(_content);
    }

    public void setup() {
        settable = tryGetSettable(this);
        settableParent = tryGetSettableParent(this);
    }
    public static RendDynOperationNode tryGetSettable(RendMethodOperation _operation) {
        RendDynOperationNode root_ = getIdOp(_operation);
        return castDottedTo(root_);
    }
    public static RendMethodOperation tryGetSettableParent(RendMethodOperation _operation) {
        RendDynOperationNode root_ = getIdOp(_operation);
        return castParentTo(root_);
    }

    public static RendDynOperationNode getIdOp(RendMethodOperation _operation) {
        RendDynOperationNode root_ = _operation.getFirstChild();
        while (root_ instanceof RendIdOperation) {
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

    public RendDynOperationNode getSettable() {
        return settable;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        if (settableParent instanceof RendSafeDotOperation) {
            RendDynOperationNode left_ = settableParent.getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                leftArg_ = new Argument(ExecClassArgumentMatching.convert(NullStruct.NULL_VALUE,_context, getResultClass().getNames()));
                setQuickConvertSimpleArgument(leftArg_, _nodes, _context);
                return;
            }
        }
        RendDynOperationNode right_ = getLastNode(this);
        Argument rightArg_ = getArgument(_nodes,right_);
        Argument arg_ = calculateChSetting(settable,_nodes, _conf, rightArg_, _advStandards, _context);
        setSimpleArgument(arg_, _conf,_nodes, _context);
    }
    static Argument calculateChSetting(RendDynOperationNode _set,
                                       IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context){
        Argument arg_ = null;
        if (_set instanceof RendStdVariableOperation) {
            arg_ = ((RendStdVariableOperation)_set).calculateSetting(_nodes, _conf, _right, _advStandards, _context);
        }
        if (_set instanceof RendSettableFieldOperation) {
            arg_ = ((RendSettableFieldOperation)_set).calculateSetting(_nodes, _conf, _right, _advStandards, _context);
        }
        if (_set instanceof RendCustArrOperation) {
            arg_ = ((RendCustArrOperation)_set).calculateSetting(_nodes, _conf, _right, _advStandards, _context);
        }
        if (_set instanceof RendArrOperation) {
            arg_ = ((RendArrOperation)_set).calculateSetting(_nodes, _conf, _right, _advStandards, _context);
        }
        return Argument.getNullableValue(arg_);
    }
}
