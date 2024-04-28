package aiki.gui;

import aiki.facade.SexListInt;
import aiki.sml.IntConfPkStream;
import aiki.sml.LoadedGameConf;
import aiki.sml.LoadingGame;
import code.util.StringList;

public final class MockConfPkStream extends EquallableAikiGuiUtil implements IntConfPkStream {
    @Override
    public LoadedGameConf load(String _tmpFolder, StringList _args, SexListInt _sexListInt) {
        LoadedGameConf l_ = new LoadedGameConf();
        l_.setLoadingGame(new LoadingGame());
        return l_;
    }

    @Override
    public LoadingGame save(String _file, LoadingGame _conf) {
        assertEq(_conf.getLastRom(),_conf.getLastRom());
        return _conf;
    }
}
