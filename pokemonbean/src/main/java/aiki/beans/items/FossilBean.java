package aiki.beans.items;
import code.bean.Accessible;
import code.util.StringMap;
import aiki.DataBase;
import aiki.fight.items.Fossil;

public class FossilBean extends ItemBean {

    @Accessible
    private String pokemon;

    @Accessible
    private short level;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        Fossil item_ = (Fossil) getItem();
        pokemon = item_.getPokemon();
        level = item_.getLevel();
    }

    @Accessible
    private String getTrPokemon() {
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        return translatedPokemon_.getVal(pokemon);
    }

    @Accessible
    private String clickPokemon() {
        getForms().put(PK, pokemon);
        return POKEMON;
    }
}
