package aiki.fight.items;

import aiki.db.DataBase;
import aiki.util.DataInfoChecker;


public final class Fossil extends Item {

    private String pokemon;

    private long level;

    @Override
    public String getItemType() {
        return FOSSIL;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkStringListContains(_data.getPokedex().getKeys(),pokemon,_data);
        DataInfoChecker.checkPositive(level,_data);
    }

    public String getPokemon() {
        return pokemon;
    }

    public void setPokemon(String _pokemon) {
        pokemon = _pokemon;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long _level) {
        level = _level;
    }
}
