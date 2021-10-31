package code.formathtml.util.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.Message;
import code.formathtml.structs.MessageStruct;

public final class FctMessageNew0 implements StdCaller {
    private final String aliasMessage;

    public FctMessageNew0(String _aliasMessage) {
        this.aliasMessage = _aliasMessage;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        return new ArgumentWrapper(MessageStruct.newInstance(Message.newStandardMessage(),aliasMessage));
    }
}
