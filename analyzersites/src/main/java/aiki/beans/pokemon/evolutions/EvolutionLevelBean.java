package aiki.beans.pokemon.evolutions;
import code.bean.Accessible;
import aiki.fight.pokemon.evolution.EvolutionLevel;


public class EvolutionLevelBean extends EvolutionBean {

    @Accessible
    private short level;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EvolutionLevel evo_ = (EvolutionLevel) getEvo();
        level = evo_.getLevel();
    }
}
