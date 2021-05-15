package code.formathtml.exec.opers;
import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.*;
import code.formathtml.exec.*;
import code.util.CustList;
import code.util.IdMap;
import code.util.core.IndexConstants;

public abstract class RendInvokingOperation extends RendMethodOperation implements RendPossibleIntermediateDotted {
    private final boolean intermediate;

    public RendInvokingOperation(
            ExecOperationContent _content, boolean _intermediateDottedOperation) {
        super(_content);
        intermediate = _intermediateDottedOperation;
    }

    public ArgumentListCall fectchArgs(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, String _lastType, int _naturalVararg, RendStackCall _rendStackCall,Argument _right) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        RendArgumentList argumentList_ = listNamedArguments(_nodes, chidren_,_naturalVararg);
        ArgumentListCall fetchArgs_ = argumentList_.getArguments();
        fetchArgs_.setRight(_right);
        CustList<ArgumentWrapper> first_ = fetchArgs_.getArgumentWrappers();
        ExecInvokingOperation.listArguments(argumentList_.getNaturalVararg(), _lastType, first_);
        ArgumentListCall list_ = _rendStackCall.getLastPage().getList();
        list_.getArgumentWrappers().clear();
        list_.getArgumentWrappers().addAllElts(fetchArgs_.getArgumentWrappers());
        return fetchArgs_;
    }

    private static RendArgumentList listNamedArguments(IdMap<RendDynOperationNode, ArgumentsPair> _all, CustList<RendDynOperationNode> _children, int _naturalVararg) {
        RendArgumentList out_ = new RendArgumentList();
        out_.setNaturalVararg(_naturalVararg);
        CustList<ArgumentWrapper> wrappers_ = out_.getArguments().getArgumentWrappers();
        CustList<RendNamedArgumentOperation> named_ = new CustList<RendNamedArgumentOperation>();
        for (RendDynOperationNode c: _children) {
            if (RendConstLeafOperation.isFilter(c)) {
                continue;
            }
            ArgumentsPair calc_ = getArgumentPair(_all,c);
            if (c instanceof RendNamedArgumentOperation) {
                named_.add((RendNamedArgumentOperation)c);
            } else {
                addToWrappers(c,calc_,wrappers_);
            }
        }
        while (!named_.isEmpty()) {
            RendOperationIndexer indexer_ = new RendOperationIndexer(named_);
            int i_ = indexer_.getIndex();
            RendNamedArgumentOperation n_ = named_.get(i_);
            ArgumentsPair calc_ = getArgumentPair(_all,n_);
            addToWrappers(n_.getFirstChild(),calc_,wrappers_);
            named_.remove(i_);
        }
        return out_;
    }
    private static void addToWrappers(RendDynOperationNode _node,ArgumentsPair _pair,CustList<ArgumentWrapper> _wrappers){
        if (!(_node instanceof RendWrappOperation)) {
            _wrappers.add(new ArgumentWrapper(_pair.getArgument(),null));
        } else {
            _wrappers.add(new ArgumentWrapper(null,_pair.getWrapper()));
        }
    }

    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

}
