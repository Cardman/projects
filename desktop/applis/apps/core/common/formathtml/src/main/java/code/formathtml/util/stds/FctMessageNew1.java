package code.formathtml.util.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.Message;
import code.formathtml.structs.MessageStruct;

public final class FctMessageNew1 implements StdCaller {
    private final String aliasMessage;

    public FctMessageNew1(String _aliasMessage) {
        this.aliasMessage = _aliasMessage;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        String value_ = NumParsers.getString(_firstArgs.getArgumentWrappers().get(0).getValue().getStruct()).getInstance();
        return new ArgumentWrapper(MessageStruct.newInstance(Message.newStandardMessage(value_),aliasMessage));
    }
}
