package aiki.beans.pokemon.evolutions;

import aiki.beans.*;
import aiki.db.*;
import aiki.fight.pokemon.evolution.*;
import code.util.*;

public class EvolutionMoveBean extends EvolutionBean {
    private TranslatedKey move;

    @Override
    public void beforeDisplaying() {
        super.beforeDisplaying();
        EvolutionMove evo_ = (EvolutionMove) getEvo();
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        move = buildMv(translationsMoves_,evo_.getMove());
    }
    public String clickMove(int _index) {
        return tryRedirect(((EvolutionMoveBean)getForms().getCurrentBeanEvo().get(_index)).move);
    }

    public String getMove() {
        return move.getTranslation();
    }
}