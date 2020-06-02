package aiki.beans.pokemon.evolutions;
import aiki.fight.pokemon.evolution.EvolutionLevel;


public class EvolutionLevelBean extends EvolutionBean {
    private short level;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EvolutionLevel evo_ = (EvolutionLevel) getEvo();
        level = evo_.getLevel();
    }

    public short getLevel() {
        return level;
    }
}