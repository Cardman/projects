package aiki.gui.threads;

import aiki.facade.FacadeGame;
import aiki.sml.DocumentWriterAikiCoreUtil;
import aiki.sml.LoadingGame;
import code.stream.core.ContentTime;
import code.stream.core.StreamZipFile;
import code.util.EntryCust;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class ExportRomThread implements Runnable {
    private final FacadeGame facadeGame;
    private final LoadingGame loadingGame;

    public ExportRomThread(FacadeGame _facadeGame, LoadingGame _loadingGame) {
        this.facadeGame = _facadeGame;
        this.loadingGame = _loadingGame;
    }

    @Override
    public void run() {
        if (loadingGame.isExport()) {
            StringMap<String> textFiles_ = DocumentWriterAikiCoreUtil.getTextFiles(facadeGame.getData());
            StringMap<ContentTime> meta_ = new StringMap<ContentTime>();
            for (EntryCust<String,String> e: textFiles_.entryList()) {
                meta_.addEntry(e.getKey(),new ContentTime(StringUtil.encode(e.getValue()),System.currentTimeMillis()));
            }
            StreamZipFile.zipBinFiles(meta_);
        }
    }
}
