package code.expressionlanguage.fcts;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ByteStruct;
import code.expressionlanguage.structs.CharSequenceStruct;
import code.expressionlanguage.structs.Struct;
import code.util.core.StringUtil;

public final class FctCharSeqGetBytes implements StdCaller {
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CharSequenceStruct inst_ = (CharSequenceStruct) _instance;
        String bytePrim_ = _cont.getStandards().getContent().getPrimTypes().getAliasPrimByte();
        String seq_ = inst_.toStringInstance();
        byte[] list_ = StringUtil.encode(seq_);
        bytePrim_ = StringExpUtil.getPrettyArrayType(bytePrim_);
        int len_ = list_.length;
        ArrayStruct result_ = new ArrayStruct(len_, bytePrim_);
        for (int i = 0; i < len_; i++) {
            result_.set(i, new ByteStruct(list_[i]));
        }
        return new ArgumentWrapper(result_);
    }

}
