package aiki.fight.status;

import aiki.db.DataBase;

public final class StatusBeginRoundSimple extends StatusBeginRound {

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        validateStatusBeginRound(_data);
    }

}
