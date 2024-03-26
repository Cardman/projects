package aiki.gui.threads;

import aiki.facade.FacadeGame;
import aiki.sml.IntDataBaseStream;
import aiki.sml.LoadingGame;
import code.gui.initialize.AbstractProgramInfos;

public final class ExportRomThread implements Runnable {
    private final FacadeGame facadeGame;
    private final LoadingGame loadingGame;
    private final AbstractProgramInfos programInfos;
    private final IntDataBaseStream stream;

    public ExportRomThread(FacadeGame _facadeGame, LoadingGame _loadingGame, IntDataBaseStream _stream, AbstractProgramInfos _api) {
        this.facadeGame = _facadeGame;
        this.loadingGame = _loadingGame;
        programInfos = _api;
        stream = _stream;
    }

    @Override
    public void run() {
        stream.exportRom(programInfos,facadeGame,loadingGame);
    }
}
