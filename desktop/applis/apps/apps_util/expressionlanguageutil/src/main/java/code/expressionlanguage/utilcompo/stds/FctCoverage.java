package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.EntryTextStruct;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.util.StringMap;

public final class FctCoverage implements StdCaller {
    private final String aliasEntryText;

    public FctCoverage(String _aliasEntryText) {
        this.aliasEntryText = _aliasEntryText;
    }
    @Override
    public ArgumentWrapper call(AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringMap<String> export_ = ExecFileBlock.export(_cont);
        int len_ = export_.size();
        String arrType_ = StringExpUtil.getPrettyArrayType(aliasEntryText);
        ArrayStruct arr_ = new ArrayStruct(len_,arrType_);
        for (int i = 0; i < len_; i++) {
            EntryTextStruct e_ = new EntryTextStruct(new StringStruct(export_.getKey(i)), new StringStruct(export_.getValue(i)));
            e_.setLongTime(new LongStruct(((RunnableContextEl)_cont).getCurrentThreadFactory().millis()));
            arr_.set(i, e_);
        }
        return new ArgumentWrapper(arr_);
    }
}
