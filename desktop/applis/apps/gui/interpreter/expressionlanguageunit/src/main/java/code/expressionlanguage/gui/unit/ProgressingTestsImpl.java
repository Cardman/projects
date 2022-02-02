package code.expressionlanguage.gui.unit;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.utilcompo.*;
import code.stream.AbstractFileCoreStream;
import code.stream.StreamBinaryFile;
import code.stream.StreamFolderFile;
import code.stream.core.TechStreams;

public final class ProgressingTestsImpl extends ProgressingTestsAbs {
    private final TechStreams streams;
    private final AbstractFileCoreStream fact;

    public ProgressingTestsImpl(TestableFrame _mainWindow, TechStreams _streams, AbstractFileCoreStream _fact) {
        super(_mainWindow);
        streams = _streams;
        fact = _fact;
    }


    @Override
    public void showErrors(ReportedMessages _reportedMessages, Options _opts, ExecutingOptions _exec, FileInfos _infos) {
        byte[] bytes_ = exportedErrors(_reportedMessages, _exec, _infos);
        tryExp(_exec, bytes_);
    }

    @Override
    public void setResults(RunnableContextEl _ctx, Argument _res, LgNamesWithNewAliases _evolved) {
        ExecutingOptions executingOptions_ = _ctx.getExecutingOptions();
        byte[] export_ = exportedResults(_ctx, _res, _evolved);
        tryExp(executingOptions_, export_);
    }

    private void tryExp(ExecutingOptions _exec, byte[] _bytes) {
        if (_bytes == null) {
            return;
        }
        StreamFolderFile.makeParent(_exec.getOutputFolder()+"/"+ _exec.getOutputZip(),fact);
        StreamBinaryFile.writeFile(_exec.getOutputFolder()+"/"+ _exec.getOutputZip(), _bytes,streams);
    }

    @Override
    public byte[] getExportedReport() {
        return new byte[0];
    }
}
