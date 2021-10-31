package code.formathtml.util.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecArrayFieldOperation;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.MessageStruct;
import code.formathtml.util.DefaultBeanAliases;

public final class FctMessageSetArgs implements StdCaller {
    private final String aliasMessage;

    public FctMessageSetArgs(String _aliasMessage) {
        this.aliasMessage = _aliasMessage;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        MessageStruct instance_ = DefaultBeanAliases.getMessageStruct(_instance, aliasMessage);
        ArrayStruct array_ = ExecArrayFieldOperation.getArray(_firstArgs.getArgumentWrappers().get(0).getValue().getStruct(), _cont);
        int len_ = array_.getLength();
        String[] resArgs_ = new String[len_];
        for (int i = 0; i < len_; i++){
            Struct argInst_ = array_.get(i);
            if (argInst_ instanceof StringStruct) {
                resArgs_[i] = ((StringStruct)argInst_).getInstance();
            } else {
                resArgs_[i] = _cont.getStandards().getDisplayedStrings().getNullString();
            }
        }
        instance_.setArgs(resArgs_);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
