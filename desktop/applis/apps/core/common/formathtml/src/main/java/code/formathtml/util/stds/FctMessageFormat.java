package code.formathtml.util.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.MessageStruct;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DefaultBeanAliases;

public final class FctMessageFormat implements StdCaller {
    private final String aliasMessage;

    public FctMessageFormat(String _aliasMessage) {
        this.aliasMessage = _aliasMessage;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        MessageStruct instance_ = DefaultBeanAliases.getMessageStruct(_instance, aliasMessage);
        return new ArgumentWrapper(BeanLgNames.wrapStd(instance_.getMessage()));
    }
}
