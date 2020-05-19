package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.calls.util.CallingState;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.ValuesOperation;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.IdMap;

public final class RendValuesOperation extends RendLeafOperation implements RendCalculableOperation {

    private String className;
    private int argOffset;

    public RendValuesOperation(ValuesOperation _v) {
        super(_v);
        className = _v.getClassName();
        argOffset = _v.getArgOffset();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        Argument argres_ = getCommonArgument(_conf);
        CallingState state_ = _conf.getContext().getCallingState();
        if (state_ instanceof NotInitializedClass) {
            NotInitializedClass statusInit_ = (NotInitializedClass) state_;
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContext());
            if (_conf.getContext().hasException()) {
                return;
            }
            argres_ = getCommonArgument(_conf);
        }
        Argument argRes_ = argres_;
        setSimpleArgument(argRes_, _conf,_nodes);
    }

    Argument getCommonArgument(Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+argOffset, _conf);
        return ExecInvokingOperation.getEnumValues(new AdvancedExiting(_conf),className, _conf.getContext());
    }

}
