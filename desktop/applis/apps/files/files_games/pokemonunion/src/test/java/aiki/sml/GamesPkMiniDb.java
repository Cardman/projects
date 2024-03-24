package aiki.sml;

import aiki.db.DataBase;
import code.threads.AbstractFutureParam;

public final class GamesPkMiniDb implements AbstractFutureParam<DataBase> {
    @Override
    public boolean cancel(boolean _mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean attendre() {
        return false;
    }

    @Override
    public DataBase attendreResultat() {
        return InitDbValid.initDb();
    }
}
