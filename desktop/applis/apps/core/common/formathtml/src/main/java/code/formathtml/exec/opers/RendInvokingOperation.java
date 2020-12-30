package code.formathtml.exec.opers;
import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
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
        l_.getArguments().addAllElts(arguments_);
        ExecInvokingOperation.checkParametersOperatorsFormatted(_exit, _conf, _named, l_, _className, _kind, _stackCall);
    }
    public ArgumentListCall fectchArgs(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, String _lastType, int _naturalVararg, RendStackCall _rendStackCall) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        RendArgumentList argumentList_ = listNamedArguments(_nodes, chidren_);
        ArgumentListCall fetchArgs_ = argumentList_.getArguments();
        CustList<Argument> first_ = fetchArgs_.getArguments();
        CustList<RendDynOperationNode> filter_ = argumentList_.getFilter();
        CustList<Argument> res_ = listArguments(filter_, _naturalVararg, _lastType, first_);
        first_.clear();
        first_.addAllElts(res_);
        ArgumentListCall list_ = _rendStackCall.getLastPage().getList();
        list_.getArguments().clear();
        list_.getWrappers().clear();
        list_.getArguments().addAllElts(first_);
        list_.getWrappers().addAllElts(fetchArgs_.getWrappers());
        return fetchArgs_;
    }

    private static RendArgumentList listNamedArguments(IdMap<RendDynOperationNode, ArgumentsPair> _all, CustList<RendDynOperationNode> _children) {
        RendArgumentList out_ = new RendArgumentList();
        CustList<Argument> args_ = out_.getArguments().getArguments();
        CustList<AbstractWrapper> wrappers_ = out_.getArguments().getWrappers();
        CustList<RendDynOperationNode> filter_ = out_.getFilter();
        CustList<RendNamedArgumentOperation> named_ = new CustList<RendNamedArgumentOperation>();
        for (RendDynOperationNode c: _children) {
            if (c instanceof RendNamedArgumentOperation) {
                if (!(c.getFirstChild() instanceof RendWrappOperation)) {
                    named_.add((RendNamedArgumentOperation)c);
                    filter_.add(c);
                }
            } else if (!(c instanceof RendWrappOperation)){
                args_.add(getArgument(_all,c));
                filter_.add(c);
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
            args_.add(getArgument(_all,named_.get(i_)));
            named_.remove(i_);
        }
        for (RendDynOperationNode c: _children) {
            if (c instanceof RendNamedArgumentOperation) {
                if (c.getFirstChild() instanceof RendWrappOperation) {
                    named_.add((RendNamedArgumentOperation)c);
                }
            } else if (c instanceof RendWrappOperation){
                ArgumentsPair pair_ = getArgumentPair(_all, c);
                wrappers_.add(pair_.getWrapper());
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
            ArgumentsPair pair_ = getArgumentPair(_all, named_.get(i_).getFirstChild());
            wrappers_.add(pair_.getWrapper());
            named_.remove(i_);
        }
        return out_;
    }
    private static CustList<Argument> listArguments(CustList<RendDynOperationNode> _children, int _natVararg, String _lastType, CustList<Argument> _nodes) {
        if (_natVararg > -1) {
            CustList<Argument> firstArgs_ = new CustList<Argument>();
            CustList<Struct> optArgs_ = new CustList<Struct>();
            int lenCh_ = _children.size();
            int natVararg_ = _natVararg;
            for (int i = IndexConstants.FIRST_INDEX; i < lenCh_; i++) {
                if (RendConstLeafOperation.isFilter(_children.get(i))) {
                    natVararg_++;
                    continue;
                }
                Argument a_ = _nodes.get(i);
                if (i >= natVararg_) {
                    optArgs_.add(a_.getStruct());
                } else {
                    firstArgs_.add(a_);
                }
            }
            String clArr_ = StringExpUtil.getPrettyArrayType(_lastType);
            ArrayStruct str_ = NumParsers.setElements(optArgs_,clArr_);
            Argument argRem_ = new Argument(str_);
            firstArgs_.add(argRem_);
            return firstArgs_;
        }
        CustList<Argument> firstArgs_ = new CustList<Argument>();
        int lenCh_ = _children.size();
        for (int i = IndexConstants.FIRST_INDEX; i < lenCh_; i++) {
            if (RendConstLeafOperation.isFilter(_children.get(i))) {
                continue;
            }
            Argument a_ = _nodes.get(i);
            firstArgs_.add(a_);
        }
        return firstArgs_;
    }

    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

}
