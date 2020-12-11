package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.utilcompo.*;
import code.expressionlanguage.structs.Struct;
import code.gui.Clock;
import code.stream.StreamBinaryFile;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.stream.core.StreamZipFile;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ProgressingTestsImpl implements ProgressingTests {
    private MainWindow mainWindow;

    public ProgressingTestsImpl(MainWindow _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void showErrors(RunnableContextEl _ctx, ReportedMessages _reportedMessages, Options _opts, ExecutingOptions _exec) {
        if (!_reportedMessages.isAllEmptyErrors()) {
            String folder_ = _exec.getOutput()+_exec.getLogFolder();
            String time_ = Clock.getDateTimeText("_", "_", "_");
            String dtPart_ = time_+".txt";
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+_reportedMessages.displayErrors());
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+_reportedMessages.displayWarnings());
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+_reportedMessages.displayStdErrors());
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+_reportedMessages.displayMessageErrors());
        }
        if (!_reportedMessages.isEmptyWarnings()) {
            String folder_ = _exec.getOutput()+_exec.getLogFolder();
            String time_ = Clock.getDateTimeText("_", "_", "_");
            String dtPart_ = time_+".txt";
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+_reportedMessages.displayWarnings());
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
        StringMap<byte[]> out_ = new StringMap<byte[]>();
        if (logger_ instanceof MemoryLogger) {
            ConcurrentHashMap<String, FileStruct> logs_ = ((MemoryLogger) logger_).getLogs();
            for (Map.Entry<String, FileStruct> e: logs_.entrySet()) {
                String key_ = e.getKey();
                String toFile_ = StringUtil.concat(executingOptions_.getLogFolder(),"/",key_);
                out_.addEntry(toFile_,e.getValue().getContent());
            }
        }
        AbstractFileSystem fileSystem_ = _evolved.getInfos().getFileSystem();
        if (fileSystem_ instanceof MemoryFileSystem) {
            StringMap<byte[]> stringMap_ = ((MemoryFileSystem) fileSystem_).getRoot().exportAll();
            for (EntryCust<String, byte[]> e: stringMap_.entryList()) {
                byte[] bytes_ = e.getValue();
                if (bytes_ == null) {
                    continue;
                }
                String key_ = e.getKey();
                out_.addEntry(executingOptions_.getFiles()+"/"+key_,bytes_);
            }
        }
        if (!out_.isEmpty()) {
            byte[] bytes_ = StreamZipFile.zipBinFiles(out_);
            StreamFolderFile.mkdirs(executingOptions_.getOutputFolder());
            StreamBinaryFile.writeFile(executingOptions_.getOutputFolder()+"/"+executingOptions_.getOutputZip(),bytes_);
        }
    }
}
