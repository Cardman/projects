package aiki.beans.pokemon.evolutions;

import aiki.beans.*;
import aiki.fight.pokemon.evolution.*;

public class EvolutionStoneBean extends EvolutionBean {
    private TranslatedKey stone;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EvolutionStone evo_ = (EvolutionStone) getEvo();
        stone = buildIt(getFacade(), evo_.getStone());
    }
    public String clickStone() {
        return tryRedirect(stone);
    }

    public TranslatedKey getStone() {
        return stone;
    }
}