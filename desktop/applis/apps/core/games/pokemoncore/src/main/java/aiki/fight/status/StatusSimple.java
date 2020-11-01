package aiki.fight.status;

import aiki.db.DataBase;

public final class StatusSimple extends Status {
    @Override
    public void validate(DataBase _data) {
        validateStatus(_data);
    }
}
