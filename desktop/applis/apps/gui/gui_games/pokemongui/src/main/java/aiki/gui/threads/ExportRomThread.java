package aiki.gui.threads;

import aiki.facade.FacadeGame;
import aiki.sml.DocumentWriterAikiCoreUtil;
import aiki.sml.LoadingGame;
import code.stream.StreamBinaryFile;
import code.stream.StreamFolderFile;
import code.stream.core.ContentTime;
import code.stream.core.StreamZipFile;
import code.threads.AbstractThreadFactory;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

import java.io.File;

public final class ExportRomThread implements Runnable {
    private final FacadeGame facadeGame;
    private final LoadingGame loadingGame;
    private final AbstractThreadFactory threadFactory;

    public ExportRomThread(FacadeGame _facadeGame, LoadingGame _loadingGame, AbstractThreadFactory _threadFactory) {
        this.facadeGame = _facadeGame;
        this.loadingGame = _loadingGame;
        threadFactory = _threadFactory;
    }

    @Override
    public void run() {
        String export_ = loadingGame.getExport();
        if (!export_.isEmpty()) {
            String path_ = new File(export_).getAbsolutePath();
            path_ = StringUtil.replaceBackSlash(path_);
            StringMap<String> textFiles_ = DocumentWriterAikiCoreUtil.getTextFiles(facadeGame.getData());
            StringMap<ContentTime> meta_ = new StringMap<ContentTime>();
            for (EntryCust<String,String> e: textFiles_.entryList()) {
                meta_.addEntry(e.getKey(),new ContentTime(StringUtil.encode(e.getValue()),threadFactory.millis()));
            }
            StreamFolderFile.makeParent(path_);
            StreamBinaryFile.writeFile(path_,StreamZipFile.zipBinFiles(meta_));
        }
    }
}
