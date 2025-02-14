package aiki.beans.pokemon.evolutions;

import aiki.beans.*;
import aiki.db.*;
import aiki.fight.pokemon.evolution.*;
import code.util.*;

public class EvolutionStoneBean extends EvolutionBean {
    private TranslatedKey stone;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EvolutionStone evo_ = (EvolutionStone) getEvo();
        StringMap<String> translationsItems_;
        DataBase data_ = getDataBase();
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        stone = buildIt(data_,translationsItems_,evo_.getStone());
    }
    public String clickStone(int _index) {
        return tryRedirect(((EvolutionStoneBean)getForms().getCurrentBeanEvo().get(_index)).stone);
    }

    public TranslatedKey getStone() {
        return stone;
    }
}