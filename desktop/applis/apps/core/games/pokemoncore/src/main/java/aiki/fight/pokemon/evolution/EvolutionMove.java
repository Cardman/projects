package aiki.fight.pokemon.evolution;

import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;
import code.util.core.StringUtil;


public final class EvolutionMove extends Evolution {

    private String move;

    @Override
    public boolean validate(DataBase _dataBase, PokemonData _fPk) {
        boolean res_ = StringUtil.quickEq(move, _dataBase.getDefMove());
        if (!StringUtil.contains(_fPk.getMoveTutors(), move)) {
            res_ = true;
        }
        return res_;
    }

    public String getMove() {
        return move;
    }

    public void setMove(String _move) {
        move = _move;
    }
}
