package aiki.fight.pokemon.evolution;

import aiki.db.DataBase;
import aiki.fight.moves.MoveData;
import aiki.fight.pokemon.PokemonData;
import code.util.StringList;
import code.util.core.StringUtil;


public final class EvolutionMoveType extends Evolution {

    private String type;

    @Override
    public boolean validate(DataBase _dataBase, PokemonData _fPk) {
        boolean res_ = false;
        StringList types_ = new StringList();
        for (String m : _fPk.getMoveTutors()) {
            MoveData move_ = _dataBase.getMove(m);
            if (move_ == null) {
                continue;
            }
            types_.addAllElts(move_.getTypes());
        }
        if (!StringUtil.contains(types_, type)) {
            res_ = true;
        }
        return res_;
    }

    public String getType() {
        return type;
    }

    public void setType(String _type) {
        type = _type;
    }
}
