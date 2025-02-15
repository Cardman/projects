package aiki.beans.pokemon.evolutions;

import aiki.beans.*;
import aiki.fight.pokemon.evolution.*;

public class EvolutionItemBean extends EvolutionBean {
    private TranslatedKey item;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EvolutionItem evo_ = (EvolutionItem) getEvo();
        item = buildIt(getFacade(), evo_.getItem());
    }
    public String clickItem(int _index) {
        return tryRedirect(((EvolutionItemBean)getForms().getCurrentBeanEvo().get(_index)).item);
    }

    public TranslatedKey getItem() {
        return item;
    }
}