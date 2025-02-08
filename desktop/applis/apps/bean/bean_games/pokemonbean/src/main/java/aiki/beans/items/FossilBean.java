package aiki.beans.items;
import aiki.beans.*;
import aiki.fight.items.*;

public class FossilBean extends ItemBean {
    private TranslatedKey pokemon;
    private long level;

    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
        Fossil item_ = (Fossil) getItem();
        pokemon = buildPk(getDataBase().getTranslatedPokemon().getVal(getLanguage()),item_.getPokemon());
        level = item_.getLevel();
    }
    public String getTrPokemon() {
        return pokemon.getTranslation();
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
//        return translatedPokemon_.getVal(pokemon);
    }
    public String clickPokemon() {
        return tryRedirect(pokemon);
    }

    public long getLevel() {
        return level;
    }
}