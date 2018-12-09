package aiki.map.pokemon;

import aiki.DataBase;
import aiki.map.pokemon.enums.Gender;
import code.util.StringList;


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
    public void validate(DataBase _data, boolean _ref) {
        super.validate(_data, _ref);
        // if (hasJustBeenCreated()) {
        // _data.setError(true);

        // }
        for (String m : moves) {
            if (StringList.quickEq(m, _data.getDefaultMove())) {
                _data.setError(true);
                return;

            }
            if (!_data.getMoves().contains(m)) {
                _data.setError(true);
                return;

            }
        }
        if (moves.size() > _data.getNbMaxMoves()) {
            _data.setError(true);
            return;

        }
        if (moves.isEmpty()) {
            _data.setError(true);
            return;

        }
    }

    // public boolean isValid(DataBase _data) {
    // if(!super.isValid(_data)) {
    // return false;
    // }
    // if (hasJustBeenCreated()) {
    // return false;
    // }
    // for (String m: moves) {
    // if (StringList.eq(m, _data.getDefaultMove())) {
    // return false;
    // }
    // if (!_data.getMoves().contains(m)) {
    // return false;
    // }
    // }
    // if (moves.size() > _data.getNbMaxMoves()) {
    // return false;
    // }
    // return !moves.isEmpty();
    // }

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

    // @Override
    // public void beforeSave() {
    // // name = getName();
    // // level = getLevel();
    // // ability = getAbility();
    // // gender = getGender();
    // // item = getItem();
    // // moves = getMoves();
    // }
    //
    // @Override
    // public void afterLoad() {
    // }
}
