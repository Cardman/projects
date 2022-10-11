package aiki.beans.simulation;

import aiki.beans.PokemonStandards;
import aiki.beans.WithFilterBean;
import aiki.beans.facade.simulation.dto.PokemonPlayerDto;
import aiki.beans.facade.simulation.enums.TeamCrud;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.fight.pokemon.PokemonData;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.util.AbsMap;
import code.util.StringMap;

public class AddPokemonBean extends WithFilterBean {
    private final CrudPkCommon common = new CrudPkCommon();
    private String namePk = DataBase.EMPTY_STRING;
    private String ability = DataBase.EMPTY_STRING;
//    private String gender = Gender.NO_GENDER.getGenderName();
//    private int level;
//    private DictionaryComparator<String,String> genders;
    private DictionaryComparator<String,String> abilities;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = getDataBase();
        StringMap<String> translatedAbilities_;
        translatedAbilities_ = data_.getTranslatedAbilities().getVal(getLanguage());
        abilities = DictionaryComparatorUtil.buildAbilities(data_,getLanguage());
        AbsMap<Gender,String> translatedGenders_;
        translatedGenders_ = data_.getTranslatedGenders().getVal(getLanguage());
//        genders = DictionaryComparatorUtil.buildGenderStr(data_,getLanguage());
        common.init(data_,getLanguage());
        bools();
        if (getForms().contains(CST_PK_NAME)) {
            namePk = getForms().getValStr(CST_PK_NAME);
            PokemonData pkData_ = data_.getPokemon(namePk);
            for (String a: pkData_.getAbilities()) {
                abilities.put(a, translatedAbilities_.getVal(a));
            }
            for (Gender g: pkData_.getGenderRep().getPossibleGenders()) {
                common.getGenders().put(g.getGenderName(), translatedGenders_.getVal(g));
            }
        }
        setupPokedex(getForms().getValList(CST_POKEMON_SET_SIMU));
    }
    public String add() {
        if (!getForms().contains(CST_PK_NAME)) {
            return DataBase.EMPTY_STRING;
        }
        if (!common.getGenders().contains(common.getGender())) {
            return DataBase.EMPTY_STRING;
        }
        if (!abilities.contains(ability)) {
            return DataBase.EMPTY_STRING;
        }
        DataBase data_ = getDataBase();
        common.patchLevel(data_);
        /*if (level < data_.getMinLevel()) {
            level = (short) data_.getMinLevel();
        }
        if (level > data_.getMaxLevel()) {
            level = (short) data_.getMaxLevel();
        }*/
        WildPk pk_ = new WildPk();
        pk_.setName(namePk);
        pk_.setLevel((short) common.getLevel());
        pk_.setAbility(ability);
        pk_.setGender(PokemonStandards.getGenderByName(common.getGender()));
        PokemonData pkData_ = data_.getPokemon(namePk);
        PokemonPlayerDto pkDto_ = new PokemonPlayerDto();
        pkDto_.setPokemon(pk_);
        pkDto_.setMoves(pkData_.getMovesAtLevel((short) common.getLevel(), data_.getNbMaxMoves()));
        getForms().put(CST_POKEMON_ADDED, pkDto_);
        return CST_SIMULATION;
    }
    public String cancel() {
        getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
        return CST_SIMULATION;
    }
    public void search() {
        search(CST_POKEMON_SET_SIMU, CST_PK_NAME);
//        StringList pokedex_ = pokedex();
//        getForms().put(CST_POKEMON_SET_SIMU, pokedex_);
//        if (pokedex_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
//            getForms().put(CST_PK_NAME,pokedex_.first());
//        }
    }
    public void clickLink(int _number) {
        getForms().put(CST_PK_NAME, getPokedex().get(_number).getName());
    }

    public String getNamePk() {
        return namePk;
    }

    public DictionaryComparator<String,String> getAbilities() {
        return abilities;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String _ability) {
        ability = _ability;
    }

    public CrudPkCommon getCommon() {
        return common;
    }

    public DictionaryComparator<String,String> getGenders() {
        return getCommon().getGenders();
    }

    public String getGender() {
        return getCommon().getGender();
    }

    public void setGender(String _gender) {
        getCommon().setGender(_gender);
    }

    public void setLevel(int _level) {
        getCommon().setLevel(_level);
    }

    public int getLevel() {
        return getCommon().getLevel();
    }
}