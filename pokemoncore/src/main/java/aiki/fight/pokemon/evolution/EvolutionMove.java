package aiki.fight.pokemon.evolution;

import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;
import code.util.StringList;


public final class EvolutionMove extends Evolution {

    private String move;

    @Override
    public void validate(DataBase _dataBase, PokemonData _fPk) {
        if (StringList.quickEq(move, _dataBase.getDefaultMove())) {
            _dataBase.setError(true);
            return;

        }
        if (!StringList.contains(_fPk.getMoveTutors(), move)) {
            _dataBase.setError(true);
            return;

        }
    }

    public String getMove() {
        return move;
    }

    public void setMove(String _move) {
        move = _move;
    }
}
