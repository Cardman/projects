package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
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
    public void showErrors(RunnableContextEl _ctx, ExecutingOptions _exec) {
        if (!_ctx.getClasses().isEmptyErrors() || !_ctx.getClasses().isEmptyStdError()) {
            String folder_ = _exec.getLogFolder();
            String time_ = Clock.getDateTimeText("_", "_", "_");
            String dtPart_ = time_+".txt";
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+_ctx.getClasses().displayErrors());
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+_ctx.getClasses().displayWarnings());
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+_ctx.getClasses().displayStdErrors());
        }
        if (!_ctx.getClasses().isEmptyWarnings()) {
            String folder_ = _exec.getLogFolder();
            String time_ = Clock.getDateTimeText("_", "_", "_");
            String dtPart_ = time_+".txt";
            StreamTextFile.logToFile(folder_+"/_"+dtPart_, time_+":"+_ctx.getClasses().displayWarnings());
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
