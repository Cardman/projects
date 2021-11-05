package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.CharSequenceStruct;
import code.expressionlanguage.structs.CharStruct;
import code.expressionlanguage.structs.Struct;

public final class FctCharSeqToCharArray implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        String aliasChar_ = _cont.getStandards().getContent().getPrimTypes().getAliasPrimChar();
        aliasChar_ = StringExpUtil.getPrettyArrayType(aliasChar_);
        CharSequenceStruct inst_ = (CharSequenceStruct) _instance;
        int len_ = inst_.length();
        ArrayStruct arr_ = new ArrayStruct(len_,aliasChar_);
        for (int i = 0; i < len_; i++) {
            arr_.set(i, new CharStruct(inst_.charAt(i)));
        }
        return new ArgumentWrapper(arr_);
    }

}
