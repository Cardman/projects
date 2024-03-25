package aiki.sml;

import aiki.db.DataBase;
import code.threads.AbstractFutureParam;
import code.threads.IntCallable;

public final class GamesPkMiniDb implements IntCallable<DataBase> {

    @Override
    public DataBase call() {
        return InitDbValid.initDb();
    }
}
