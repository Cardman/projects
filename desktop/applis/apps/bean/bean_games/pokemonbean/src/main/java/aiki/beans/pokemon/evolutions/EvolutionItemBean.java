package aiki.beans.pokemon.evolutions;

import aiki.beans.*;
import aiki.db.*;
import aiki.fight.pokemon.evolution.*;
import code.util.*;

public class EvolutionItemBean extends EvolutionBean {
    private TranslatedKey item;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EvolutionItem evo_ = (EvolutionItem) getEvo();
        StringMap<String> translationsItems_;
        DataBase data_ = getDataBase();
        translationsItems_ = data_.getTranslatedItems().getVal(getLanguage());
        item = buildIt(data_,translationsItems_,evo_.getItem());
    }
    public String clickItem(int _index) {
        return tryRedirect(((EvolutionItemBean)getForms().getCurrentBeanEvo().get(_index)).item);
    }

    public String getItem() {
        return item.getTranslation();
    }
}