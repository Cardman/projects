package aiki.beans.items;
import aiki.db.DataBase;
import aiki.fight.items.Fossil;
import code.util.StringMap;

public class FossilBean extends ItemBean {
    private String pokemon;
    private short level;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        Fossil item_ = (Fossil) getItem();
        pokemon = item_.getPokemon();
        level = item_.getLevel();
    }
    public String getTrPokemon() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translatedPokemon_.getVal(pokemon);
    }
    public String clickPokemon() {
        getForms().put(PK, pokemon);
        return POKEMON;
    }

    public short getLevel() {
        return level;
    }
}