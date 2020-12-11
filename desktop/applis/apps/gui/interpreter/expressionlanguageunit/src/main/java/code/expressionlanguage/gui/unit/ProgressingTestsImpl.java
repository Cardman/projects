package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.structs.Struct;
import code.gui.Clock;
import code.stream.StreamBinaryFile;
import code.stream.StreamFolderFile;
import code.stream.core.ContentTime;
import code.stream.core.StreamZipFile;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ProgressingTestsImpl implements ProgressingTests {
    private MainWindow mainWindow;
    private ExecutingOptions exec;

    public ProgressingTestsImpl(MainWindow _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public ExecutingOptions getExec() {
        return exec;
    }

    @Override
    public void init(ExecutingOptions _exec) {
        exec = _exec;
    }

    @Override
    public void showErrors(RunnableContextEl _ctx, ReportedMessages _reportedMessages, Options _opts, ExecutingOptions _exec, FileInfos _infos) {
        AbstractLogger logger_ = _infos.getLogger();
        String time_ = Clock.getDateTimeText("_", "_", "_");
        if (!_reportedMessages.isAllEmptyErrors()) {
            String folder_ = _exec.getOutput()+_exec.getLogFolder();
            String dtPart_ = time_+".txt";
            logger_.logErr(folder_,"_"+dtPart_, time_+":"+_reportedMessages.displayErrors(),_ctx);
            logger_.logErr(folder_,"_"+dtPart_, time_+":"+_reportedMessages.displayWarnings(),_ctx);
            logger_.logErr(folder_,"_"+dtPart_, time_+":"+_reportedMessages.displayStdErrors(),_ctx);
            logger_.logErr(folder_,"_"+dtPart_, time_+":"+_reportedMessages.displayMessageErrors(),_ctx);
        }
        if (!_reportedMessages.isEmptyWarnings()) {
            String folder_ = _exec.getOutput()+_exec.getLogFolder();
            String dtPart_ = time_+".txt";
            logger_.logErr(folder_,"_"+dtPart_, time_+":"+_reportedMessages.displayWarnings(),_ctx);
        }
        StringMap<ContentTime> out_ = new StringMap<ContentTime>();
        if (logger_ instanceof MemoryLogger) {
            String errFile_ = ((MemoryLogger) logger_).getErrFile();
            if (!errFile_.isEmpty()) {
                String errs_ = ((MemoryLogger) logger_).getErrs();
                out_.addEntry(errFile_,new ContentTime(StringUtil.encode(errs_),System.currentTimeMillis()));
            }
        }
        if (!out_.isEmpty()) {
            byte[] bytes_ = StreamZipFile.zipBinFiles(out_);
            StreamFolderFile.mkdirs(_exec.getOutputFolder());
            StreamBinaryFile.writeFile(_exec.getOutputFolder()+"/"+_exec.getOutputZip(),bytes_);
        }
    }

    @Override
    public void updateInfos(RunnableContextEl _ctx, Struct _infos, Struct _doneTests, Struct _method, Struct _count, LgNamesWithNewAliases _evolved) {
        mainWindow.showProgress(_ctx,_infos,_doneTests,_method,_count, _evolved);
    }

    @Override
    public void finish(RunnableContextEl _ctx, Struct _infos, LgNamesWithNewAliases _evolved) {
        mainWindow.finish(_infos, _evolved);
    }

    @Override
    public void setResults(RunnableContextEl _ctx, Argument _res, LgNamesWithNewAliases _evolved) {
        mainWindow.setResults(_ctx,_res, _evolved);
        ExecutingOptions executingOptions_ = _ctx.getExecutingOptions();
        AbstractLogger logger_ = _evolved.getInfos().getLogger();
        AbstractFileSystem fileSystem_ = _evolved.getInfos().getFileSystem();
        byte[] export_ = _evolved.getInfos().getReporter().export(executingOptions_, fileSystem_, logger_);
        if (export_ == null) {
            return;
        }
        StreamFolderFile.mkdirs(executingOptions_.getOutputFolder());
        StreamBinaryFile.writeFile(executingOptions_.getOutputFolder()+"/"+executingOptions_.getOutputZip(),export_);
    }
}
