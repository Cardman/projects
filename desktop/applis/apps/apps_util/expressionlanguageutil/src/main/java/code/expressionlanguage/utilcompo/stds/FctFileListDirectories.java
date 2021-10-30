package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.util.StringList;

public final class FctFileListDirectories extends FctFileAbs {
    public FctFileListDirectories(CustAliases _custAliases) {
        super(_custAliases);
    }

    @Override
    public ArgumentWrapper file(FileInfos _infos, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        String file_ = ((StringStruct)_firstArgs.getArgumentWrappers().get(0).getValue().getStruct()).getInstance();
        StringList files_ = _infos.getFileSystem().getFolders(file_, (RunnableContextEl) _cont);
        if (files_ == null) {
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        int len_ = files_.size();
        ArrayStruct arr_ = new ArrayStruct(len_, StringExpUtil.getPrettyArrayType(_cont.getStandards().getContent().getCharSeq().getAliasString()));
        for (int i = 0; i < len_; i++) {
            arr_.set(i, new StringStruct(files_.get(i)));
        }
        return new ArgumentWrapper(arr_);
    }
}
