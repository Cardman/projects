package code.formathtml.util.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.structs.MessageStruct;
import code.formathtml.util.BeanLgNames;
import code.formathtml.util.DefaultBeanAliases;
import code.util.StringList;

public final class FctMessageGetArgs implements StdCaller {
    private final String aliasMessage;

    public FctMessageGetArgs(String _aliasMessage) {
        this.aliasMessage = _aliasMessage;
    }

    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        MessageStruct instance_ = DefaultBeanAliases.getMessageStruct(_instance, aliasMessage);
        StringList resArgs_ = instance_.getArgs();
        String arrStr_ = StringExpUtil.getPrettyArrayType(_cont.getStandards().getContent().getCharSeq().getAliasString());
        int len_ = resArgs_.size();
        ArrayStruct arr_ = new ArrayStruct(len_,arrStr_);
        for (int i = 0; i < len_; i++){
            arr_.set(i, BeanLgNames.wrapStd(resArgs_.get(i)));
        }
        return new ArgumentWrapper(arr_);
    }
}
