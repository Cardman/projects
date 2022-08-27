package aiki.map.pokemon;

import aiki.db.DataBase;
import aiki.map.pokemon.enums.Gender;
import code.util.StringList;
import code.util.core.StringUtil;


public final class PkTrainer extends Pokemon {

    private StringList moves;

    public PkTrainer() {
        setName(DataBase.EMPTY_STRING);
        setLevel((short) 1);
        setGender(Gender.NO_GENDER);
        setAbility(DataBase.EMPTY_STRING);
        setItem(DataBase.EMPTY_STRING);
    }

    public PkTrainer(Pokemon _pk, StringList _moves) {
        // super(_pk, _moves);
        setName(_pk.getName());
        setLevel(_pk.getLevel());
        setGender(_pk.getGender());
        setAbility(_pk.getAbility());
        setItem(_pk.getItem());
        moves = _moves;
    }

    @Override
    public void validateAsNpc(DataBase _data) {
        super.validateAsNpc(_data);
        for (String m : moves) {
            if (StringUtil.quickEq(m, _data.getDefaultMove())) {
                _data.setError(true);
            }
            if (!_data.getMoves().contains(m)) {
                _data.setError(true);
            }
        }
        if (moves.size() > _data.getNbMaxMoves()) {
            _data.setError(true);
        }
        if (moves.isEmpty()) {
            _data.setError(true);
        }
    }


    public StringList getMoves() {
        return moves;
    }

    public void setMoves(StringList _moves) {
        moves = _moves;
    }

}
