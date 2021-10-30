package code.expressionlanguage.utilcompo.stds;

import code.expressionlanguage.AbstractExiting;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ArgumentWrapper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.*;
import code.util.core.StringUtil;

public final class FctThreadPrint0 extends FctThreadPrintAbs {
    public FctThreadPrint0(CustAliases _custAliases) {
        super(_custAliases);
    }

    @Override
    protected ArgumentWrapper pr(FileInfos _infos, AbstractExiting _exit, ContextEl _cont, Struct _instance, ArgumentListCall _firstArgs, StackCall _stackCall) {
        String stringAppFile_ = CustAliases.getStandarString(_cont, _firstArgs.getArgumentWrappers().get(0).getValue().getStruct());
        stringAppFile_ = StringUtil.concat(CustAliases.getDateTimeText(((RunnableContextEl)_cont).getCurrentThreadFactory()),":",stringAppFile_);
        log(_infos,stringAppFile_,(RunnableContextEl)_cont);
        return new ArgumentWrapper(NullStruct.NULL_VALUE);
    }
    public static void log(FileInfos _infos, String _content, RunnableContextEl _cont){
        ExecutingOptions executingOptions_ = _cont.getExecutingOptions();
        String folder_ = executingOptions_.getLogFolder();
        CustInitializer cust_ = _cont.getCustInit();
        String dtPart_ = cust_.getCurrentFileThread(_cont);
        _infos.getLogger().log(executingOptions_.getOutput()+folder_,dtPart_,_content+"\n",_cont);
    }
}
