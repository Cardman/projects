package aiki.beans.simulation;
import aiki.beans.CommonBean;
import aiki.beans.PokemonStandards;
import aiki.beans.facade.comparators.ComparatorMoves;
import aiki.beans.facade.comparators.ComparatorTrString;
import aiki.beans.facade.simulation.dto.SelectLineMove;
import aiki.beans.facade.simulation.enums.TeamCrud;
import aiki.db.DataBase;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.map.pokemon.enums.Gender;
import code.util.*;

public class EditTrainerPokemonBean extends CommonBean {
    private boolean add;
    private String namePk = DataBase.EMPTY_STRING;
    private String ability = DataBase.EMPTY_STRING;
    private String gender = Gender.NO_GENDER.name();
    private int level;
    private TreeMap<String,String> genders;
    private String item = DataBase.EMPTY_STRING;
    private final CustList<SelectLineMove> moves = new CustList<SelectLineMove>();
    private boolean allyPk;

    @Override
    public void beforeDisplaying() {
        add = getForms().getValTeamCrud(CST_ADDING_TRAINER_PK) == TeamCrud.ADD;

        namePk = getForms().getValStr(CST_POKEMON_NAME_EDIT);
        item = getForms().getValStr(CST_ITEM_EDIT);
        ability = getForms().getValStr(CST_POKEMON_ABILITY_EDIT);
        gender = getForms().getValGen(CST_POKEMON_GENDER_EDIT).name();
        level = getForms().getValInt(CST_POKEMON_LEVEL_EDIT);

        moves.clear();
        DataBase data_ = getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringMap<String> translationsCategories_;
        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        for (String k: getForms().getValList(CST_POKEMON_MOVES_EDIT)) {
            MoveData moveData_ = data_.getMoves().getVal(k);
            SelectLineMove line_ = new SelectLineMove();
            line_.setName(k);
            line_.setDisplayName(translationsMoves_.getVal(k));
            StringList types_ = new StringList();
            for (String t: moveData_.getTypes()) {
                types_.add(translationsTypes_.getVal(t));
            }
            line_.setTypes(types_);
            line_.setPp(moveData_.getPp());
            line_.setCategory(translationsCategories_.getVal(moveData_.getCategory()));
            line_.setDamageMove(moveData_ instanceof DamagingMoveData);
            if (line_.isDamageMove()) {
                DamagingMoveData damag_ = (DamagingMoveData) moveData_;
                line_.setDirect(damag_.isDirect());
            }
            line_.setPriority(moveData_.getPriority());
            line_.setSelected(false);
            moves.add(line_);
        }
        moves.sortElts(new ComparatorMoves());
//        Map<SelectedBoolean,String> translatedBooleans_;
//        translatedBooleans_ = data_.getTranslatedBooleans().getVal(getLanguage());
//        booleans = new TreeMap<new>(new ComparatorTrString<>(translatedBooleans_));
//        booleans.putAllMap(translatedBooleans_);
//        Map<String,String> translatedAbilities_;
//        translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
//        abilities = new TreeMap<new>(new ComparatorTrString<>(translatedAbilities_));
        AbsMap<Gender,String> translatedGenders_;
        translatedGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
        StringMap<String> translated_;
        translated_ = new StringMap<String>();
        for (EntryCust<Gender,String> s: translatedGenders_.entryList()) {
            translated_.addEntry(s.getKey().name(),s.getValue());
        }
        genders = new TreeMap<String, String>(new ComparatorTrString(translated_));
        genders.putAllMap(translated_);
    }
    public String cancel() {
        getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
        return CST_SIMULATION;
    }
    public String chooseAbility() {
        getForms().put(CST_ABILITIES_SET, new StringList());
        return CST_POKEMON_EDIT;
    }
    public String chooseItem() {
        getForms().put(CST_IS_POKEMON_PLAYER_MOVES, false);
        getForms().put(CST_ITEMS_SET_EDIT, new StringList());
        return CST_POKEMON_EDIT;
    }
    public String chooseName() {
        getForms().put(CST_POKEMON_SET, new StringList());
        return CST_POKEMON_EDIT;
    }
    public String addMoves() {
        getForms().put(CST_IS_POKEMON_PLAYER_MOVES, false);
        getForms().put(CST_MOVES_SET, new StringList());
        return CST_POKEMON_EDIT;
    }
    public void deleteMoves() {
        StringList keptMoves_ = new StringList();
        for (SelectLineMove s: moves) {
            if (!s.isSelected()) {
                keptMoves_.add(s.getName());
            }
        }
        getForms().put(CST_POKEMON_MOVES_EDIT, keptMoves_);
    }
    public String validateTrainerPk() {
        DataBase data_ = getDataBase();
        if (level <= data_.getMinLevel()) {
            level = (short) data_.getMinLevel();
        }
        if (level > data_.getMaxLevel()) {
            level = (short) data_.getMaxLevel();
        }
        if (ability.isEmpty()) {
            ability = data_.getMap().getFirstPokemon().getAbility();
        }
        if (namePk.isEmpty()) {
            namePk = data_.getMap().getFirstPokemon().getName();
        }
        StringList selected_ = new StringList();
        for (SelectLineMove s: moves) {
            selected_.add(s.getName());
        }
        if (selected_.isEmpty()) {
            selected_ = data_.getPokemon(namePk).getMovesBeforeLevel((short) level);
        }
        if (add) {
            getForms().put(CST_POKEMON_FOE, !allyPk);
        }
        getForms().put(CST_POKEMON_NAME_EDIT, namePk);
        getForms().put(CST_POKEMON_LEVEL_EDIT, level);
        getForms().put(CST_ITEM_EDIT, item);
        getForms().put(CST_POKEMON_GENDER_EDIT, PokemonStandards.getGenderByName(gender));
        getForms().put(CST_POKEMON_MOVES_EDIT, selected_);
        getForms().put(CST_POKEMON_ABILITY_EDIT, ability);
        return CST_VALIDATE_TRAINER_PK;
    }
    public String getTranslatedName() {
        if (namePk.isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
        DataBase data_ = getDataBase();
        return data_.translatePokemon(namePk);
    }
    public String getTranslatedAbility() {
        if (ability.isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
        DataBase data_ = getDataBase();
        return data_.translateAbility(ability);
    }
    public String getTranslatedItem() {
        if (item.isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
        DataBase data_ = getDataBase();
        return data_.translateItem(item);
    }

    public CustList<SelectLineMove> getMoves() {
        return moves;
    }

    public TreeMap<String,String> getGenders() {
        return genders;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String _gender) {
        gender = _gender;
    }

    public void setLevel(int _level) {
        level = _level;
    }

    public int getLevel() {
        return level;
    }

    public boolean getAdd() {
        return add;
    }

    public void setAllyPk(boolean _allyPk) {
        allyPk = _allyPk;
    }

    public boolean getAllyPk() {
        return allyPk;
    }
}