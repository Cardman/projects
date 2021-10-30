package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.expressionlanguage.utilcompo.ZipBinStructUtil;

public final class FctFileZippedBin extends FctFileAbs {
    public FctFileZippedBin(CustAliases _custAliases) {
        super(_custAliases);
    }

    @Override
    public ArgumentWrapper file(FileInfos _infos, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        StringStruct str_ = (StringStruct)_firstArgs.getArgumentWrappers().get(0).getValue().getStruct();
        byte[] bytes_ = _infos.getFileSystem().loadFile(str_.getInstance(), (RunnableContextEl) _cont);
        if (bytes_ == null) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        String cont_ = _cont.getStandards().getContent().getPrimTypes().getAliasPrimByte();
        cont_ = StringExpUtil.getPrettyArrayType(cont_);
        int bLen_ = bytes_.length;
        ArrayStruct bs_ = new ArrayStruct(bLen_,cont_);
        for (int j = 0; j < bLen_; j++) {
            bs_.set(j, new ByteStruct(bytes_[j]));
        }
        return new ArgumentWrapper(ZipBinStructUtil.zippedBinaryFilesByteArray(bs_, (RunnableContextEl) _cont));
    }
}
