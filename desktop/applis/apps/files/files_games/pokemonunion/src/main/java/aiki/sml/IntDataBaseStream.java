package aiki.sml;

import aiki.db.DataBase;
import aiki.facade.*;
import code.gui.initialize.AbstractProgramInfos;
import code.stream.core.ContentTime;
import code.threads.*;
import code.util.StringMap;

public interface IntDataBaseStream {

    StringMap<ContentTime> exportRom(AbstractProgramInfos _api, FacadeGame _f, LoadingGame _loadingGame);
    AbstractAtomicBooleanCore loadRomAndCheck(AbstractProgramInfos _api, AbstractFutureParam<DataBase> _db, FacadeGame _f, String _fileName, AbstractAtomicIntegerCoreAdd _p, AbstractAtomicBooleanCore _l);
}
