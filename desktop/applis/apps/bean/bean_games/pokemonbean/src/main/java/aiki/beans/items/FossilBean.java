package aiki.beans.items;
import aiki.beans.*;
import aiki.facade.*;
import aiki.fight.items.*;
import code.scripts.pages.aiki.*;

public final class FossilBean extends ItemBean {
    private TranslatedKey pokemon;
    private long level;

    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        buildHeader();
        formatMessage(MessagesPkBean.IT_FOSSIL, MessagesDataItemsFossil.M_P_21_FOSSIL, Long.toString(level));
        formatMessageDir(pokemon);
    }
    @Override
    public void beforeDisplaying() {
        beforeDisplayingItem();
        Fossil item_ = (Fossil) getItem();
        pokemon = buildPk(getFacade(),item_.getPokemon());
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