package aiki.fight.items;

import aiki.db.DataBase;


public final class Fossil extends Item {

    private static final String ITEM = "aiki.fight.items.Fossil";

    private String pokemon;

    private short level;

    @Override
    public String getItemType() {
        return ITEM;
    }

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        if (!_data.getPokedex().contains(pokemon)) {
            _data.setError(true);
        }
        if (level <= 0) {
            _data.setError(true);

        }
    }

    public String getPokemon() {
        return pokemon;
    }

    public void setPokemon(String _pokemon) {
        pokemon = _pokemon;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short _level) {
        level = _level;
    }
}
