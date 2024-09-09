package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.expressionlanguage.utilcompo.ZipBinStructUtil;
import code.util.CustList;

public final class FctFileZipBin extends FctFileAbs {
    public FctFileZipBin(CustAliases _custAliases) {
        super(_custAliases);
    }

    @Override
    public ArgumentWrapper file(FileInfos _infos, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        String fileName_ = ((StringStruct)argumentWrappers_.get(0).getValue()).getInstance();
        ArrayStruct struct_ = ZipBinStructUtil.zipBinFiles(argumentWrappers_.get(1).getValue(), (RunnableContextEl) _cont);
        int len_ = struct_.getLength();
        byte[] file_ = new byte[len_];
        for (int i = 0; i < len_; i++) {
            Struct byte_ = struct_.get(i);
            file_[i] = NumParsers.convertToNumber(byte_).byteStruct();
        }
        return new ArgumentWrapper(BooleanStruct.of(_infos.getFileSystem().writeFile(fileName_,file_, ((RunnableContextEl) _cont).getCurrentDir())));
    }
}
