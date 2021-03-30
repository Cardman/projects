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
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.*;
import code.formathtml.exec.RendArgumentList;
import code.formathtml.exec.RendStackCall;
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
    public static void checkParametersOperatorsFormatted(AbstractExiting _exit, ContextEl _conf, ExecTypeFunction _named,
                                                         IdMap<RendDynOperationNode, ArgumentsPair> _nodes, RendMethodOperation _meth, String _className, MethodAccessKind _kind, StackCall _stackCall) {
        CustList<Argument> arguments_ = getArguments(_nodes, _meth);
        ArgumentListCall l_ = new ArgumentListCall();
        l_.addAllArgs(arguments_);
        ExecInvokingOperation.checkParametersOperatorsFormatted(_exit, _conf, _named, l_, _className, _kind, _stackCall);
    }
    public ArgumentListCall fectchArgs(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, String _lastType, int _naturalVararg, RendStackCall _rendStackCall) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        RendArgumentList argumentList_ = listNamedArguments(_nodes, chidren_,_naturalVararg);
        ArgumentListCall fetchArgs_ = argumentList_.getArguments();
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
            } else if (!(c instanceof RendWrappOperation)){
                wrappers_.add(new ArgumentWrapper(calc_.getArgument(),null));
            } else {
                wrappers_.add(new ArgumentWrapper(null,calc_.getWrapper()));
            }
        }
        while (!named_.isEmpty()) {
            int minIndex_ = named_.first().getIndex();
            int size_ = named_.size();
            int i_ = 0;
            for (int i = 1; i < size_; i++) {
                int index_ = named_.get(i).getIndex();
                if (index_ < minIndex_) {
                    minIndex_ = index_;
                    i_ = i;
                }
            }
            RendNamedArgumentOperation n_ = named_.get(i_);
            ArgumentsPair calc_ = getArgumentPair(_all,n_);
            if (!(n_.getFirstChild() instanceof RendWrappOperation)) {
                wrappers_.add(new ArgumentWrapper(calc_.getArgument(),null));
            } else {
                wrappers_.add(new ArgumentWrapper(null,calc_.getWrapper()));
            }
            named_.remove(i_);
        }
        return out_;
    }

    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

}
