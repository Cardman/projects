package aiki.map.pokemon;
import aiki.DataBase;
import aiki.exceptions.DataException;
import aiki.map.pokemon.enums.Gender;
import code.datacheck.CheckedData;
import code.util.StringList;
import code.util.annot.RwXml;

@RwXml
public final class PkTrainer extends Pokemon {

    /***/
    @CheckedData
    private String name;

    /***/
    @CheckedData
    private short level;

    /***/
    @CheckedData
    private Gender gender;

    /**non modifiable une fois affecte a l'objet.*/
    @CheckedData
    private String ability;

    /**si la chaine de caractere est vide alors le pokemon ne porte pas d'objet, sinon cette chaine vaut le nom de l'objet. */
    @CheckedData
    private String item;

    private StringList moves;

    public PkTrainer() {
        name = DataBase.EMPTY_STRING;
        level = 1;
        gender = Gender.NO_GENDER;
        ability = DataBase.EMPTY_STRING;
        item = DataBase.EMPTY_STRING;
    }

    public PkTrainer(Pokemon _pk,StringList _moves) {
//        super(_pk, _moves);
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
//        if (hasJustBeenCreated()) {
//            throw new DataException();
//        }
        for (String m: moves) {
            if (StringList.quickEq(m, _data.getDefaultMove())) {
                throw new DataException();
            }
            if (!_data.getMoves().contains(m)) {
                throw new DataException();
            }
        }
        if (moves.size() > _data.getNbMaxMoves()) {
            throw new DataException();
        }
        if (moves.isEmpty()) {
            throw new DataException();
        }
    }
//    public boolean isValid(DataBase _data) {
//        if(!super.isValid(_data)) {
//            return false;
//        }
//        if (hasJustBeenCreated()) {
//            return false;
//        }
//        for (String m: moves) {
//            if (StringList.eq(m, _data.getDefaultMove())) {
//                return false;
//            }
//            if (!_data.getMoves().contains(m)) {
//                return false;
//            }
//        }
//        if (moves.size() > _data.getNbMaxMoves()) {
//            return false;
//        }
//        return !moves.isEmpty();
//    }

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

//    @Override
//    public void beforeSave() {
////        name = getName();
////        level = getLevel();
////        ability = getAbility();
////        gender = getGender();
////        item = getItem();
////        moves = getMoves();
//    }
//
//    @Override
//    public void afterLoad() {
//    }
}
