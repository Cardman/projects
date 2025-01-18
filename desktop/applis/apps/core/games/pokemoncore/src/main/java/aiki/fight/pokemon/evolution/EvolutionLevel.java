package aiki.fight.pokemon.evolution;

import aiki.db.DataBase;


public abstract class EvolutionLevel extends Evolution {

    private int level;

    protected final boolean validateEvolutionLevel(DataBase _dataBase) {
        boolean res_ = level <= 0;
        if (level > _dataBase.getMaxLevel()) {
            res_ = true;
        }
        return res_;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int _level) {
        level = _level;
    }
}
