package code.network;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.sml.IntDataBaseStream;
import aiki.sml.LoadingGame;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.core.ContentTime;
import code.threads.AbstractAtomicBooleanCore;
import code.threads.AbstractAtomicIntegerCoreAdd;
import code.threads.AbstractFutureParam;
import code.util.StringMap;

public final class MockDataBaseStreamNet implements IntDataBaseStream {
    @Override
    public StringMap<ContentTime> exportRom(AbstractProgramInfos _api, FacadeGame _f, LoadingGame _loadingGame) {
        return new StringMap<ContentTime>();
    }

    @Override
    public AbstractAtomicBooleanCore loadRomAndCheck(AbstractProgramInfos _api, AbstractFutureParam<DataBase> _db, FacadeGame _f, String _fileName, AbstractAtomicIntegerCoreAdd _p, AbstractAtomicBooleanCore _l) {
        _f.setData(ScenePanelMultiTest.sample());
        return _l;
    }
}
