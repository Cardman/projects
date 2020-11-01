package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.tree.LevelArea;

public final class LevelRoad extends LevelWithWildPokemon {

    @Override
    public void validate(DataBase _data, LevelArea _level) {
        super.validate(_data, _level);
        validateLevelWithWildPokemon(_data, _level);
    }

}
