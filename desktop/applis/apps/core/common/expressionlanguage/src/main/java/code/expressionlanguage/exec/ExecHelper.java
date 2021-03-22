package code.expressionlanguage.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.opers.ExecMethodOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.structs.Struct;
import code.util.CollCapacity;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.IndexConstants;

public final class ExecHelper {
    private ExecHelper() {
    }

    public static void fwdWrapper(ArgumentsPair _to,ArgumentsPair _from) {
        _to.setWrapper(_from.getWrapper());
    }

    public static void fwdArg(ArgumentsPair _to,ArgumentsPair _from) {
        _to.setArgument(_from.getArgument());
    }
    public static Struct[] getObjects(Argument... _args) {
        int len_ = _args.length;
        Struct[] classes_ = new Struct[len_];
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            classes_[i] = _args[i].getStruct();
        }
        return classes_;
    }

    public static CustList<Argument> getArgs(Struct... _args) {
        int len_ = _args.length;
        CustList<Argument> classes_ = new CustList<Argument>(new CollCapacity(len_));
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            classes_.add(new Argument(_args[i]));
        }
        return classes_;
    }

    public static Argument getFirstArgument(CustList<Argument> _list) {
        return getArgument(_list,0);
    }

    public static Argument getLastArgument(CustList<Argument> _list) {
        return getArgument(_list,_list.size()-1);
    }

    public static Argument getArgument(CustList<Argument> _list, int _index) {
        if (_list.isValidIndex(_index)) {
            return Argument.getNullableValue(_list.get(_index));
        }
        return Argument.createVoid();
    }

    public static ExecMethodOperation getParentOrNull(ExecOperationNode _node) {
        if (_node == null) {
            return null;
        }
        return _node.getParent();
    }

    public static ExecOperationNode getMainNode(ExecOperationNode _node) {
        ExecMethodOperation parent_ = _node.getParent();
        return getFirstNode(parent_);
    }

    public static ExecOperationNode getFirstNode(ExecMethodOperation _parent) {
        if (_parent == null) {
            return null;
        }
        return getNode(_parent.getChildrenNodes(),0);
    }

    public static ExecOperationNode getLastNode(ExecMethodOperation _parent) {
        CustList<ExecOperationNode> childrenNodes_ = _parent.getChildrenNodes();
        return getNode(childrenNodes_,childrenNodes_.size()-1);
    }

    public static ExecOperationNode getNextNode(ExecOperationNode _node) {
        ExecMethodOperation par_ = _node.getParent();
        if (par_ == null) {
            return null;
        }
        return getNode(par_.getChildrenNodes(),_node.getIndexChild()+1);
    }

    public static ArgumentsPair getArgumentPair(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ExecOperationNode _node) {
        int order_ = getOrder(_node);
        return getArgumentPair(_nodes, order_);
    }

    public static ArgumentsPair getArgumentPair(IdMap<ExecOperationNode, ArgumentsPair> _nodes, int _order) {
        if (!_nodes.isValidIndex(_order)) {
            ArgumentsPair pair_ = new ArgumentsPair();
            pair_.setArgument(Argument.createVoid());
            return pair_;
        }
        return _nodes.getValue(_order);
    }

    public static int getOrder(ExecOperationNode _node) {
        if (_node == null) {
            return 0;
        }
        return _node.getOrder();
    }

    public static ExecOperationNode getNode(CustList<ExecOperationNode> _nodes, int _index) {
        if (_nodes.isValidIndex(_index)) {
            return _nodes.get(_index);
        }
        return null;
    }

    public static String getGenericTypeNameOrObject(ContextEl _ctx, String _id) {
        GeneType classBody_ = _ctx.getClassBody(_id);
        if (classBody_ != null) {
            return classBody_.getGenericString();
        }
        return _ctx.getStandards().getCoreNames().getAliasObject();
    }
}
