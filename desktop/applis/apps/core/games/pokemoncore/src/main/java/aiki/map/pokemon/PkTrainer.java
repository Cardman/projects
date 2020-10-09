package aiki.map.pokemon;

import aiki.db.DataBase;
import aiki.map.pokemon.enums.Gender;
import code.util.StringList;
import code.util.core.StringUtil;


public final class PkTrainer extends Pokemon {

    /***/
    private String name;

    /***/
    private short level;

    /***/
    private Gender gender;

    /** non modifiable une fois affecte a l'objet. */
    private String ability;

    /**
     * si la chaine de caractere est vide alors le pokemon ne porte pas d'objet,
     * sinon cette chaine vaut le nom de l'objet.
     */
    private String item;

    private StringList moves;

    public PkTrainer() {
        name = DataBase.EMPTY_STRING;
        level = 1;
        gender = Gender.NO_GENDER;
        ability = DataBase.EMPTY_STRING;
        item = DataBase.EMPTY_STRING;
    }

    public PkTrainer(Pokemon _pk, StringList _moves) {
        // super(_pk, _moves);
        name = _pk.getName();
        level = _pk.getLevel();
        gender = _pk.getGender();
        ability = _pk.getAbility();
        item = _pk.getItem();
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String _name) {
        name = _name;
    }

    @Override
    public short getLevel() {
        return level;
    }

    @Override
    public void setLevel(short _level) {
        level = _level;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public void setGender(Gender _gender) {
        gender = _gender;
    }

    @Override
    public String getAbility() {
        return ability;
    }

    @Override
    public void setAbility(String _ability) {
        ability = _ability;
    }

    @Override
    public String getItem() {
        return item;
    }

    @Override
    public void setItem(String _item) {
        item = _item;
    }

    public StringList getMoves() {
        return moves;
    }

    public void setMoves(StringList _moves) {
        moves = _moves;
    }

}
