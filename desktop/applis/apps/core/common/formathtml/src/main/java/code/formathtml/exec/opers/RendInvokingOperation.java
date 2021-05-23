package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.*;
import code.util.CustList;
import code.util.IdMap;

public abstract class RendInvokingOperation extends RendMethodOperation implements RendPossibleIntermediateDotted {
    private final boolean intermediate;

    public RendInvokingOperation(
            ExecOperationContent _content, boolean _intermediateDottedOperation) {
        super(_content);
        intermediate = _intermediateDottedOperation;
    }

    public static ArgumentListCall fectchArgs(String _lastType, int _naturalVararg, RendStackCall _rendStackCall, Argument _right, ContextEl _conf, StackCall _stack, CustList<ExecOperationInfo> _infos) {
        ArgumentListCall fetchArgs_ = ExecInvokingOperation.fectchArgs(_lastType, _naturalVararg, _right, _conf, _stack, _infos);
        ArgumentListCall list_ = _rendStackCall.getLastPage().getList();
        list_.getArgumentWrappers().clear();
        list_.getArgumentWrappers().addAllElts(fetchArgs_.getArgumentWrappers());
        return fetchArgs_;
    }

    @Override
    public final boolean isIntermediateDottedOperation() {
        return intermediate;
    }

}
