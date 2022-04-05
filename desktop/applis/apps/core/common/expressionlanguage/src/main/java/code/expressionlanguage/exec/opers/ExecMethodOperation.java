package code.expressionlanguage.exec.opers;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ParamCheckerUtil;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecStaticEltContent;
import code.util.CustList;
import code.util.IdMap;

public abstract class ExecMethodOperation extends ExecOperationNode {

    private final CustList<ExecOperationNode> childrenNodes = new CustList<ExecOperationNode>();

    protected ExecMethodOperation(ExecOperationContent _m) {
        super(_m);
    }

    protected ExecMethodOperation(int _indexChild, ExecClassArgumentMatching _res, int _order) {
        super(_indexChild,_res,_order);
    }

    public static ArgumentList listNamedArguments(CustList<ExecOperationInfo> _infos) {
        ArgumentList out_ = new ArgumentList();
        CustList<ArgumentWrapper> wrappers_ = out_.getArguments().getArgumentWrappers();
        CustList<ExecOperationInfo> infosNamed_ = new CustList<ExecOperationInfo>();
        for (ExecOperationInfo c: _infos) {
            if (c.isFilter()) {
                continue;
            }
            int index_ = c.getIndex();
            if (index_ > -1) {
                infosNamed_.add(c);
            } else {
                addToWrappers(c.isWrapper(),c.getPair(),wrappers_);
            }
        }
        while (!infosNamed_.isEmpty()) {
            ExecOperationIndexer indexer_ = new ExecOperationIndexer(infosNamed_);
            int i_ = indexer_.getIndex();
            ExecOperationInfo n_ = infosNamed_.get(i_);
            ArgumentsPair calc_ = n_.getPair();
            addToWrappers(n_.isWrapper(),calc_,wrappers_);
            infosNamed_.remove(i_);
        }
        return out_;
    }

    private static void addToWrappers(boolean _wrapper, ArgumentsPair _pair, CustList<ArgumentWrapper> _wrappers) {
        if (!_wrapper) {
            _wrappers.add(new ArgumentWrapper(_pair.getArgument(),null));
        } else {
            _wrappers.add(new ArgumentWrapper(_pair.getArgument(),_pair.getWrapper()));
        }
    }

    protected CustList<ExecOperationInfo> buildInfos(IdMap<ExecOperationNode, ArgumentsPair> _all) {
        return buildInfos(_all,getChildrenNodes());
    }
    protected static CustList<ExecOperationInfo> buildInfos(IdMap<ExecOperationNode, ArgumentsPair> _all, CustList<ExecOperationNode> _children) {
        CustList<ExecOperationInfo> infos_ = new CustList<ExecOperationInfo>();
        for (ExecOperationNode c: _children) {
            int index_ = -1;
            boolean wr_ = false;
            if (c instanceof ExecNamedArgumentOperation) {
                ExecOperationNode ch_ = c.getFirstChild();
                index_ = ((ExecNamedArgumentOperation)c).getIndex();
                wr_ = ch_ instanceof ExecWrappOperation;
            } else if (c instanceof ExecWrappOperation){
                wr_ = true;
            }
            ArgumentsPair calc_ = ExecHelper.getArgumentPair(_all,c);
            infos_.add(new ExecOperationInfo(ExecConstLeafOperation.isFilter(c), wr_,index_,calc_));
        }
        return infos_;
    }

    public void checkParametersOperators(AbstractExiting _exit, ContextEl _conf, ExecTypeFunction _named,
                                         IdMap<ExecOperationNode, ArgumentsPair> _nodes, ExecStaticEltContent _st, StackCall _stackCall) {
        ArgumentListCall l_ = listNamedArguments(buildInfos(_nodes)).getArguments();
        ParamCheckerUtil.checkParametersOperatorsFormatted(_exit, _conf, _named, l_, ExecFormattedRootBlock.formatType(_st, _stackCall), _st.getKind(), _stackCall);
    }

    public final void appendChild(ExecOperationNode _child) {
        _child.setParent(this);
        childrenNodes.add(_child);
    }

    public final CustList<ExecOperationNode> getChildrenNodes() {
        return childrenNodes;
    }

    @Override
    public final ExecOperationNode getFirstChild() {
        return ExecHelper.getFirstNode(this);
    }

}
