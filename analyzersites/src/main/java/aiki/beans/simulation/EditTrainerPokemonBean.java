package aiki.beans.simulation;
import code.bean.Accessible;
import code.util.CustList;
import code.util.EnumMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;
import aiki.DataBase;
import aiki.beans.CommonBean;
import aiki.beans.facade.comparators.ComparatorMoves;
import aiki.beans.facade.simulation.dto.SelectLineMove;
import aiki.beans.facade.simulation.enums.TeamCrud;
import aiki.comparators.ComparatorTrStringGender;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.MoveData;
import aiki.map.pokemon.enums.Gender;

public class EditTrainerPokemonBean extends CommonBean {

    @Accessible
    private boolean add;

    @Accessible
    private String namePk = DataBase.EMPTY_STRING;

    @Accessible
    private String ability = DataBase.EMPTY_STRING;

    @Accessible
    private Gender gender = Gender.NO_GENDER;

    @Accessible
    private short level;

    @Accessible
    private TreeMap<Gender,String> genders;

    @Accessible
    private String item = DataBase.EMPTY_STRING;

    @Accessible
    private CustList<SelectLineMove> moves = new CustList<SelectLineMove>();

    @Accessible
    private boolean allyPk;

    @Override
    public void beforeDisplaying() {
        add = getForms().getVal(ADDING_TRAINER_PK) == TeamCrud.ADD;

        namePk = (String) getForms().getVal(POKEMON_NAME_EDIT);
        item = (String) getForms().getVal(ITEM_EDIT);
        ability = (String) getForms().getVal(POKEMON_ABILITY_EDIT);
        gender = (Gender) getForms().getVal(POKEMON_GENDER_EDIT);
        level = (Short) getForms().getVal(POKEMON_LEVEL_EDIT);

        moves.clear();
        DataBase data_ = (DataBase) getDataBase();
        StringMap<String> translationsMoves_;
        translationsMoves_ = data_.getTranslatedMoves().getVal(getLanguage());
        StringMap<String> translationsTypes_;
        translationsTypes_ = data_.getTranslatedTypes().getVal(getLanguage());
        StringMap<String> translationsCategories_;
        translationsCategories_ = data_.getTranslatedCategories().getVal(getLanguage());
        for (String k: (StringList) getForms().getVal(POKEMON_MOVES_EDIT)) {
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
        EnumMap<Gender,String> translatedGenders_;
        translatedGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
        genders = new TreeMap<Gender, String>(new ComparatorTrStringGender(translatedGenders_));
        genders.putAllMap(translatedGenders_);
    }

    @Accessible
    private String cancel() {
        getForms().put(ADDING_TRAINER_PK, TeamCrud.NOTHING);
        return SIMULATION;
    }

    @Accessible
    private String chooseAbility() {
        getForms().put(ABILITIES_SET, new StringList());
        return POKEMON_EDIT;
    }

    @Accessible
    private String chooseItem() {
        getForms().put(IS_POKEMON_PLAYER_MOVES, false);
        getForms().put(ITEMS_SET_EDIT, new StringList());
        return POKEMON_EDIT;
    }

    @Accessible
    private String chooseName() {
        getForms().put(POKEMON_SET, new StringList());
        return POKEMON_EDIT;
    }

    @Accessible
    private String addMoves() {
        getForms().put(IS_POKEMON_PLAYER_MOVES, false);
        getForms().put(MOVES_SET, new StringList());
        return POKEMON_EDIT;
    }

    @Accessible
    private void deleteMoves() {
        StringList keptMoves_ = new StringList();
        for (SelectLineMove s: moves) {
            if (!s.isSelected()) {
                keptMoves_.add(s.getName());
            }
        }
        getForms().put(POKEMON_MOVES_EDIT, keptMoves_);
    }

    @Accessible
    private String validateTrainerPk() {
        DataBase data_ = (DataBase) getDataBase();
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
            selected_ = data_.getPokemon(namePk).getMovesBeforeLevel(level);
        }
        if (add) {
            getForms().put(POKEMON_FOE, !allyPk);
        }
        getForms().put(POKEMON_NAME_EDIT, namePk);
        getForms().put(POKEMON_LEVEL_EDIT, level);
        getForms().put(ITEM_EDIT, item);
        getForms().put(POKEMON_GENDER_EDIT, gender);
        getForms().put(POKEMON_MOVES_EDIT, selected_);
        getForms().put(POKEMON_ABILITY_EDIT, ability);
        return VALIDATE_TRAINER_PK;
    }

    @Accessible
    private String getTranslatedName() {
        if (namePk.isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
        DataBase data_ = (DataBase) getDataBase();
        return data_.translatePokemon(namePk);
    }

    @Accessible
    private String getTranslatedAbility() {
        if (ability.isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
        DataBase data_ = (DataBase) getDataBase();
        return data_.translateAbility(ability);
    }

    @Accessible
    private String getTranslatedItem() {
        if (item.isEmpty()) {
            return DataBase.EMPTY_STRING;
        }
        DataBase data_ = (DataBase) getDataBase();
        return data_.translateItem(item);
    }
}
