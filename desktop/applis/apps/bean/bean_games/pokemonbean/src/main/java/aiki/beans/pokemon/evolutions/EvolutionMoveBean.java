package aiki.beans.pokemon.evolutions;

import aiki.beans.*;
import aiki.fight.pokemon.evolution.*;

public class EvolutionMoveBean extends EvolutionBean {
    private TranslatedKey move;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EvolutionMove evo_ = (EvolutionMove) getEvo();
        move = buildMv(getFacade(),evo_.getMove());
    }
    public String clickMove() {
        return tryRedirect(move);
    }

    public TranslatedKey getMove() {
        return move;
    }
}