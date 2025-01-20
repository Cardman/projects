package aiki.beans.items;
import aiki.db.DataBase;
import aiki.fight.items.Fossil;
import code.util.StringMap;

public class FossilBean extends ItemBean {
    private String pokemon;
    private long level;

    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
        Fossil item_ = (Fossil) getItem();
        pokemon = item_.getPokemon();
        level = item_.getLevel();
    }
    public String getTrPokemon() {
        DataBase data_ = getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translatedPokemon_.getVal(pokemon);
    }
    public String clickPokemon() {
        return tryRedirectPk(pokemon);
    }

    public long getLevel() {
        return level;
    }
}