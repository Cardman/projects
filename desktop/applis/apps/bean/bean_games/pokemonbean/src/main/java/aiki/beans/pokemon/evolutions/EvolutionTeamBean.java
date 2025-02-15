package aiki.beans.pokemon.evolutions;

import aiki.beans.*;
import aiki.fight.pokemon.evolution.*;

public class EvolutionTeamBean extends EvolutionBean {
    private TranslatedKey other;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EvolutionTeam evo_ = (EvolutionTeam) getEvo();
        other = buildPk(getFacade(),evo_.getPokemon());
    }
    public String clickTeam(int _index) {
        return tryRedirect(((EvolutionTeamBean)getForms().getCurrentBeanEvo().get(_index)).other);
    }

    public TranslatedKey getOther() {
        return other;
    }
}