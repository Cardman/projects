package code.expressionlanguage.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.opers.ExecMethodOperation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecHelper {
    private ExecHelper() {
    }

    public static void fwdWrapper(ArgumentsPair _to,ArgumentsPair _from) {
        _to.setWrapper(_from.getWrapper());
        setValue(_to.getWrapper(), _from.getArgument());
    }

    public static void setValue(AbstractWrapper _aw, Struct _v) {
        if (_aw != null) {
            _aw.setValue(_v);
        }
    }

    public static Struct getFirstArgument(CustList<Struct> _list) {
        return getArgument(_list,0);
    }

    public static Struct getLastArgument(CustList<Struct> _list) {
        return getArgument(_list,_list.size()-1);
    }

    public static ArgumentWrapper getFirstArgumentWrapper(CustList<ArgumentWrapper> _list) {
        return getArgumentWrapper(_list,0);
    }

    public static ArgumentWrapper getLastArgumentWrapper(CustList<ArgumentWrapper> _list) {
        return getArgumentWrapper(_list,_list.size()-1);
    }

    public static Struct getArgument(CustList<Struct> _list, int _index) {
        if (_list.isValidIndex(_index)) {
            return ArgumentListCall.getNull(_list.get(_index));
        }
        return NullStruct.NULL_VALUE;
    }

    public static ArgumentWrapper getArgumentWrapper(CustList<ArgumentWrapper> _list, int _index) {
        if (_list.isValidIndex(_index)) {
            return _list.get(_index);
        }
        return new ArgumentWrapper(NullStruct.NULL_VALUE,null);
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
            pair_.setArgument(NullStruct.NULL_VALUE);
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
