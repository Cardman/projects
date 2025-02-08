package aiki.beans.pokemon.evolutions;

import aiki.beans.*;
import aiki.db.*;
import aiki.fight.pokemon.evolution.*;
import code.util.*;

public class EvolutionTeamBean extends EvolutionBean {
    private TranslatedKey other;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EvolutionTeam evo_ = (EvolutionTeam) getEvo();
        StringMap<String> translationsPokemon_;
        DataBase data_ = getDataBase();
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        other = buildPk(translationsPokemon_,evo_.getPokemon());
    }
    public String clickTeam(int _index) {
        return tryRedirect(((EvolutionTeamBean)getForms().getCurrentBeanEvo().get(_index)).other);
    }

    public String getOther() {
        return other.getTranslation();
    }
}