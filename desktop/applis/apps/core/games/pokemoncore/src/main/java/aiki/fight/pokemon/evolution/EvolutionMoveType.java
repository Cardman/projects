package aiki.fight.pokemon.evolution;

import aiki.db.DataBase;
import aiki.fight.moves.MoveData;
import aiki.fight.pokemon.PokemonData;
import code.util.StringList;


public final class EvolutionMoveType extends Evolution {

    private String type;

    @Override
    public void validate(DataBase _dataBase, PokemonData _fPk) {
        StringList types_ = new StringList();
        for (String m : _fPk.getMoveTutors()) {
            MoveData move_ = _dataBase.getMove(m);
            if (move_ == null) {
                continue;
            }
            types_.addAllElts(move_.getTypes());
        }
        if (!StringList.contains(types_, type)) {
            _dataBase.setError(true);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String _type) {
        type = _type;
    }
}
