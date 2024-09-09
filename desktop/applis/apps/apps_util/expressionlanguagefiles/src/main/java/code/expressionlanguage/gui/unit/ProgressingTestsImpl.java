package code.expressionlanguage.gui.unit;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.options.Options;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.utilcompo.ExecutingOptions;
import code.expressionlanguage.utilcompo.FileInfos;
import code.expressionlanguage.utilcompo.LgNamesWithNewAliases;
import code.stream.AbstractFileCoreStream;
import code.stream.BytesInfo;
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
        BytesInfo bytes_ = exportedErrors(_reportedMessages, _exec, _infos);
        tryExp(_exec, bytes_, fact, streams);
    }

    @Override
    public void setResults(ContextEl _ctx, ExecutingOptions _ex, Struct _res, LgNamesWithNewAliases _evolved) {
        BytesInfo export_ = exportedResults(_ctx,_ex, _res, _evolved);
        tryExp(_ex, export_, fact, streams);
    }

    public static void tryExp(ExecutingOptions _exec, BytesInfo _bytes, AbstractFileCoreStream _fact, TechStreams _streams) {
        if (_bytes.isNul()) {
            return;
        }
        StreamFolderFile.makeParent(_exec.getOutputFolder()+"/"+ _exec.getOutputZip(), _fact);
        StreamBinaryFile.writeFile(_exec.getOutputFolder()+"/"+ _exec.getOutputZip(), _bytes.getBytes(), _streams);
    }

    @Override
    public BytesInfo getExportedReport() {
        return new BytesInfo(new byte[0],true);
    }
}
