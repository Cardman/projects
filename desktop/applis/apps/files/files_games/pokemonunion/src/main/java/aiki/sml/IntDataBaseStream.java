package aiki.sml;

import aiki.db.DataBase;
import aiki.facade.*;
import code.gui.initialize.AbstractProgramInfos;
import code.threads.*;

public interface IntDataBaseStream {

    AbstractAtomicBooleanCore loadRomAndCheck(AbstractProgramInfos _api, AbstractFutureParam<DataBase> _db, FacadeGame _f, String _fileName, AbstractAtomicIntegerCoreAdd _p, AbstractAtomicBooleanCore _l);
}
