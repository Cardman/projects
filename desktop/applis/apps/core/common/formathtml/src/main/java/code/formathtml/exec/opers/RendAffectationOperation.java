package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
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
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context, StackCall _stack, RendStackCall _rendStack) {
        if (settableParent instanceof RendSafeDotOperation) {
            RendDynOperationNode left_ = settableParent.getFirstChild();
            Argument leftArg_ = getArgument(_nodes,left_);
            if (leftArg_.isNull()) {
                leftArg_ = new Argument(ExecClassArgumentMatching.convert(NullStruct.NULL_VALUE,_context, getResultClass().getNames()));
                setQuickConvertSimpleArgument(leftArg_, _nodes, _context, _stack);
                return;
            }
        }
        RendDynOperationNode right_ = getLastNode(this);
        if (settable instanceof RendStdRefVariableOperation) {
            if (((RendStdRefVariableOperation)settable).isDeclare()){
                CustList<RendDynOperationNode> childrenNodes_ = getChildrenNodes();
                ArgumentsPair pairRight_ = getArgumentPair(_nodes, getNode(childrenNodes_,childrenNodes_.size()-1));
                _rendStack.getLastPage().getRefParams().put(((RendStdRefVariableOperation)settable).getVariableName(),pairRight_.getWrapper());
                setQuickNoConvertSimpleArgument(new Argument(), _nodes, _context, _stack);
                return;
            }
        }
        Argument rightArg_ = getArgument(_nodes,right_);
        Argument arg_ = calculateChSetting(settable,_nodes, _conf, rightArg_, _advStandards, _context, _stack, _rendStack);
        setSimpleArgument(arg_, _nodes, _context, _stack, _rendStack);
    }
    static Argument calculateChSetting(RendDynOperationNode _set,
                                       IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, Argument _right, BeanLgNames _advStandards, ContextEl _context, StackCall _stackCall, RendStackCall _rendStackCall){
        Argument arg_ = null;
        if (_set instanceof RendStdRefVariableOperation) {
            arg_ = ((RendStdRefVariableOperation)_set).calculateSetting(_nodes, _conf, _right, _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (_set instanceof RendSettableFieldOperation) {
            arg_ = ((RendSettableFieldOperation)_set).calculateSetting(_nodes, _conf, _right, _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (_set instanceof RendCustArrOperation) {
            arg_ = ((RendCustArrOperation)_set).calculateSetting(_nodes, _conf, _right, _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (_set instanceof RendArrOperation) {
            arg_ = ((RendArrOperation)_set).calculateSetting(_nodes, _conf, _right, _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (_set instanceof RendSettableCallFctOperation) {
            arg_ = ((RendSettableCallFctOperation)_set).calculateSetting(_nodes, _conf, _right, _advStandards, _context, _stackCall, _rendStackCall);
        }
        if (_set instanceof RendRefTernaryOperation) {
            arg_ = ((RendRefTernaryOperation)_set).calculateSetting(_nodes, _conf, _right, _advStandards, _context, _stackCall, _rendStackCall);
        }
        return Argument.getNullableValue(arg_);
    }
}
