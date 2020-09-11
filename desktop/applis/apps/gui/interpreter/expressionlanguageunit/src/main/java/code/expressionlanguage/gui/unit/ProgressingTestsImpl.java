package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.ProgressingTests;
import code.expressionlanguage.utilcompo.RunnableContextEl;
import code.expressionlanguage.structs.Struct;
import code.gui.Clock;
import code.stream.StreamTextFile;

public final class ProgressingTestsImpl implements ProgressingTests {
    private MainWindow mainWindow;

    public ProgressingTestsImpl(MainWindow _mainWindow) {
        mainWindow = _mainWindow;
    }

    @Override
    public void showErrors(RunnableContextEl _ctx, ReportedMessages _reportedMessages, Options _opts, ExecutingOptions _exec) {
        if (!_reportedMessages.isEmptyErrors()) {
            String folder_ = _exec.getLogFolder();
            String time_ = Clock.getDateTimeText("_", "_", "_");
            String dtPart_ = time_+".txt";
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+_reportedMessages.displayErrors());
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+_reportedMessages.displayWarnings());
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+_reportedMessages.displayStdErrors());
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+_reportedMessages.displayMessageErrors());
        }
        if (!_reportedMessages.isEmptyWarnings()) {
            String folder_ = _exec.getLogFolder();
            String time_ = Clock.getDateTimeText("_", "_", "_");
            String dtPart_ = time_+".txt";
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+_reportedMessages.displayWarnings());
        }
    }

    @Override
    public void updateInfos(RunnableContextEl _ctx, Struct _infos, Struct _doneTests, Struct _method, Struct _count) {
        mainWindow.showProgress(_ctx,_infos,_doneTests,_method,_count);
    }

    @Override
    public void finish(RunnableContextEl _ctx, Struct _infos) {
        mainWindow.finish(_ctx,_infos);
    }

    @Override
    public void setResults(RunnableContextEl _ctx, Argument _res) {
        mainWindow.setResults(_ctx,_res);
    }
}
