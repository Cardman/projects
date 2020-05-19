package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.calls.util.*;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.exec.Operable;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public abstract class RendMethodOperation extends RendDynOperationNode {

    private RendDynOperationNode firstChild;

    public RendMethodOperation(Operable _m) {
        super(_m);
    }

    public RendMethodOperation(int _indexChild, ClassArgumentMatching _res, int _order) {
        super(_indexChild,_res,_order);
    }
    void processCall(IdMap<RendDynOperationNode,ArgumentsPair> _nodes, Configuration _conf, Argument _res) {
        CallingState callingState_ = _conf.getContext().getCallingState();
        Argument res_;
        if (callingState_ instanceof CustomFoundConstructor) {
            CustomFoundConstructor ctor_ = (CustomFoundConstructor)callingState_;
            res_ = ProcessMethod.instanceArgument(ctor_.getClassName(),ctor_.getType(), ctor_.getCurrentObject(), ctor_.getId(), ctor_.getArguments(), _conf.getContext());
        } else if (callingState_ instanceof CustomFoundMethod) {
            CustomFoundMethod method_ = (CustomFoundMethod) callingState_;
            res_ = ProcessMethod.calculateArgument(method_.getGl(), method_.getClassName(), method_.getId(), method_.getArguments(), _conf.getContext(),method_.getRight());
        } else if (callingState_ instanceof CustomReflectMethod) {
            CustomReflectMethod ref_ = (CustomReflectMethod) callingState_;
            res_ = ProcessMethod.reflectArgument(ref_.getGl(), ref_.getArguments(), _conf.getContext(), ref_.getReflect(), ref_.isLambda());
        } else if (callingState_ instanceof CustomFoundCast) {
            CustomFoundCast cast_ = (CustomFoundCast) callingState_;
            res_ = ProcessMethod.castArgument(cast_.getClassName(),cast_.getId(), cast_.getArguments(), _conf.getContext());
        } else {
            res_ = _res;
        }
        setSimpleArgument(res_, _conf,_nodes);
    }
    public final void appendChild(RendDynOperationNode _child) {
        _child.setParent(this);
        if (firstChild == null) {
            firstChild = _child;
            return;
        }
        RendDynOperationNode child_ = firstChild;
        while (true) {
            RendDynOperationNode sibling_ = child_.getNextSibling();
            if (sibling_ == null) {
                child_.setNextSibling(_child);
                return;
            }
            child_ = sibling_;
        }
    }

    public final CustList<RendDynOperationNode> getChildrenNodes() {
        CustList<RendDynOperationNode> list_ = new CustList<RendDynOperationNode>();
        RendDynOperationNode firstChild_ = getFirstChild();
        RendDynOperationNode elt_ = firstChild_;
        while (elt_ != null) {
            list_.add(elt_);
            elt_ = elt_.getNextSibling();
        }
        return list_;
    }

    public final RendDynOperationNode getFirstChild() {
        return firstChild;
    }

}
