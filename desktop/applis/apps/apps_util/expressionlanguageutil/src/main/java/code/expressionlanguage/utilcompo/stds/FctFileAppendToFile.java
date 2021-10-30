package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.CustAliases;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.util.CustList;

public final class FctFileAppendToFile extends FctFileAbs {
    public FctFileAppendToFile(CustAliases _custAliases) {
        super(_custAliases);
    }

    @Override
    public ArgumentWrapper file(FileInfos _infos, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        CustList<ArgumentWrapper> argumentWrappers_ = _firstArgs.getArgumentWrappers();
        String file_ = ((StringStruct)argumentWrappers_.get(0).getValue().getStruct()).getInstance();
        String txt_ = CustAliases.getStandarString(_cont,argumentWrappers_.get(1).getValue().getStruct());
        return new ArgumentWrapper(BooleanStruct.of(_infos.getFileSystem().logToFile(file_, txt_, (RunnableContextEl) _cont)));
    }
}
