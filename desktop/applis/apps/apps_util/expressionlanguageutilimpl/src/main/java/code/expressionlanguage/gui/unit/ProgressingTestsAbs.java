package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.*;
import code.gui.CdmFactory;
import code.stream.BytesInfo;
import code.threads.AbstractAtomicBoolean;

public abstract class ProgressingTestsAbs implements ProgressingTests {
    private final TestableFrame mainWindow;
    private AbstractAtomicBoolean stop;
    private InterruptibleContextEl context;

    protected ProgressingTestsAbs(TestableFrame _mainWindow) {
        this.mainWindow = _mainWindow;
    }

    @Override
    public CdmFactory getFactory() {
        return mainWindow.getFactory();
    }

    @Override
    public void updateInfos(ContextEl _ctx, Struct _infos, LgNamesWithNewAliases _evolved) {
        mainWindow.showProgress(_ctx,_infos, _evolved);
    }

    @Override
    public void finish(ContextEl _ctx, Struct _infos, LgNamesWithNewAliases _evolved) {
        mainWindow.finish(_infos, _evolved);
    }

    protected BytesInfo exportedErrors(ReportedMessages _reportedMessages, ExecutingOptions _exec, FileInfos _infos) {
        String time_ = CommonExecution.getDateTimeText("_", "_", "_", mainWindow.getThreadFactory());
        MemoryReporter.buildError(_reportedMessages,_exec,_infos,time_);
        AbstractLogger logger_ = _infos.getLogger();
        return _infos.getReporter().exportErrs(_exec, logger_);
    }
    @Override
    public void showWarnings(ContextEl _ctx, ReportedMessages _reportedMessages, Options _opts, ExecutingOptions _exec, FileInfos _infos) {
        String time_ = CommonExecution.getDateTimeText("_", "_", "_", mainWindow.getThreadFactory());
        MemoryReporter.buildWarning(_reportedMessages,_exec,_infos,time_);
    }

    protected BytesInfo exportedResults(ContextEl _ctx, ExecutingOptions _ex,Argument _res, LgNamesWithNewAliases _evolved) {
        mainWindow.setResults(_ctx,_res, _evolved);
        AbstractLogger logger_ = _evolved.getExecContent().getInfos().getLogger();
        AbstractFileSystem fileSystem_ = _evolved.getExecContent().getInfos().getFileSystem();
        return _evolved.getExecContent().getInfos().getReporter().export(_ex, fileSystem_, logger_);
    }

    @Override
    public InterruptibleContextEl ctx() {
        return context;
    }

    @Override
    public void ctx(InterruptibleContextEl _ctx) {
        context = _ctx;
    }

    public AbstractAtomicBoolean getStop() {
        return stop;
    }

    public void setStop(AbstractAtomicBoolean _a) {
        this.stop = _a;
    }
}
