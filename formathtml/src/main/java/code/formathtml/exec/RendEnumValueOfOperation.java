package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.calls.util.CallingState;
import code.expressionlanguage.calls.util.NotInitializedClass;
import code.expressionlanguage.methods.ProcessMethod;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.EnumValueOfOperation;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.formathtml.Configuration;
import code.formathtml.util.AdvancedExiting;
import code.util.IdMap;

public final class RendEnumValueOfOperation extends RendAbstractUnaryOperation {

    private String className;
    private int argOffset;

    public RendEnumValueOfOperation(EnumValueOfOperation _e) {
        super(_e);
        className = _e.getClassName();
        argOffset = _e.getArgOffset();
    }


    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        RendDynOperationNode first_ = getFirstChild();
        Argument arg_ = getArgument(_nodes,first_);
        Argument argres_ = getCommonArgument(arg_, _conf);
        CallingState state_ = _conf.getContext().getCallingState();
        if (state_ instanceof NotInitializedClass) {
            NotInitializedClass statusInit_ = (NotInitializedClass) state_;
            ProcessMethod.initializeClass(statusInit_.getClassName(), _conf.getContext());
            if (_conf.getContext().hasException()) {
                return;
            }
            argres_ = getCommonArgument(arg_, _conf);
        }
        Argument argRes_ = argres_;
        setSimpleArgument(argRes_, _conf,_nodes);
    }

    Argument getCommonArgument(Argument _argument, Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+argOffset, _conf);
        return ExecInvokingOperation.getEnumValue(new AdvancedExiting(_conf),className, _argument, _conf.getContext());
    }

}
