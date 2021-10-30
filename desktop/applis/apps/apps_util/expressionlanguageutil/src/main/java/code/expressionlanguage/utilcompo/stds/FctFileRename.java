package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.util.CustList;

public final class FctFileRename extends FctFileAbs {
    public FctFileRename(CustAliases _custAliases) {
        super(_custAliases);
    }

    @Override
    public ArgumentWrapper file(FileInfos _infos, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        Struct oldName_ = argumentWrappers_.get(0).getValue().getStruct();
        Struct newName_ = argumentWrappers_.get(1).getValue().getStruct();
        if (!(newName_ instanceof StringStruct)) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, _cont.getStandards().getContent().getCoreNames().getAliasNullPe(), _stackCall)));
            return new ArgumentWrapper(NullStruct.NULL_VALUE);
        }
        String file_ = ((StringStruct)oldName_).getInstance();
        String dest_ = ((StringStruct)newName_).getInstance();
        return new ArgumentWrapper(BooleanStruct.of(_infos.getFileSystem().rename(file_,dest_, (RunnableContextEl) _cont)));
    }
}
