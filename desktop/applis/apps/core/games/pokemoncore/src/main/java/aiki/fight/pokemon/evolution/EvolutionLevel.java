package aiki.fight.pokemon.evolution;

import aiki.db.DataBase;


public abstract class EvolutionLevel extends Evolution {

    private long level;

    protected final boolean validateEvolutionLevel(DataBase _dataBase) {
        boolean res_ = level <= 0;
        if (level > _dataBase.getMaxLevel()) {
            res_ = true;
        }
        return res_;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long _level) {
        level = _level;
    }
}
