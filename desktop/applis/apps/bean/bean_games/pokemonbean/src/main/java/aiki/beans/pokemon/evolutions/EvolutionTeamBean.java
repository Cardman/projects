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
    public String clickTeam() {
        return tryRedirect(other);
    }

    public TranslatedKey getOther() {
        return other;
    }
}