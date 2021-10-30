package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.*;
import code.util.CustList;
import code.util.core.StringUtil;

public final class FctFileZippedText extends FctFileAbs {
    private final String aliasEntryText;

    public FctFileZippedText(CustAliases _custAliases, String _aliasEntryText) {
        super(_custAliases);
        this.aliasEntryText = _aliasEntryText;
    }

    @Override
    public ArgumentWrapper file(FileInfos _infos, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringStruct str_ = (StringStruct)_firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        byte[] bytes_ = _infos.getFileSystem().loadFile(str_.getInstance(), (RunnableContextEl) _cont);
        if (bytes_ == null) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        CustList<EntryBinaryStruct> arrList_ = ZipBinStructUtil.getEntryBinaryStructs(bytes_, (RunnableContextEl) _cont);
        if (arrList_ != null) {
            String arrType_ = StringExpUtil.getPrettyArrayType(aliasEntryText);
            int len_ = arrList_.size();
            ArrayStruct filesOut_ = new ArrayStruct(len_,arrType_);
            for (int i = 0; i < len_; i++) {
                EntryBinaryStruct fileBin_ = arrList_.get(i);
                ArrayStruct bin_ = fileBin_.getBinary();
                int contLen_ = bin_.getLength();
                byte[] prim_ = new byte[contLen_];
                for (int j = 0; j < contLen_; j++) {
                    prim_[j] = ((NumberStruct)bin_.get(j)).byteStruct();
                }
                String dec_ = StringUtil.decode(prim_);
                EntryTextStruct txt_;
                if (dec_ == null) {
                    txt_ = new EntryTextStruct(fileBin_.getName(), NullStruct.NULL_VALUE);
                } else {
                    txt_ = new EntryTextStruct(fileBin_.getName(), new StringStruct(dec_));
                }
                txt_.setLongTime(fileBin_.getLongTime());
                filesOut_.set(i, txt_);
            }
            return new ArgumentWrapper(filesOut_);
        }
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
}
