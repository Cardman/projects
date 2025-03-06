package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.facade.dto.PokemonLine;
import aiki.beans.facade.simulation.dto.PokemonPlayerDto;
import aiki.beans.facade.simulation.enums.TeamCrud;
import aiki.beans.game.DifficultyBeanForm;
import aiki.comparators.DictionaryComparator;
import aiki.comparators.DictionaryComparatorUtil;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.pokemon.PokemonData;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import code.scripts.confs.PkScriptPages;
import code.scripts.pages.aiki.*;
import code.util.AbsMap;
import code.util.StringMap;

public final class AddPokemonBean extends WithFilterBean {
    private final CrudPkCommon common = new CrudPkCommon();
    private String namePk = DataBase.EMPTY_STRING;
    private IntBeanChgString ability = new BeanChgString();
//    private String gender = Gender.NO_GENDER.getGenderName();
//    private int level;
//    private DictionaryComparator<String,String> genders;
    private DictionaryComparator<String,String> abilities;

    public AddPokemonBean() {
        ability.setupValue(DataBase.EMPTY_STRING);
    }
    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        setTitledBorder(file().getVal(MessagesDataSimulationLevelsimu.M_P_85_TITLE_ADD_POKEMON));
        formatMessageAnc(new AddPokemonBeanCancel(this),MessagesPkBean.SIMU_LEVEL,MessagesDataSimulationLevelsimu.M_P_85_CANCEL);
        if (!namePk.isEmpty()) {
            initLine();
            formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_NAME_PK);
            formatMessageDir(namePk);
            feedParents();
            initLine();
            formatMessage(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ABILITY_PK);
            setAbility(DifficultyBeanForm.select(getBuilder().getGenInput(), this,abilities,getAbility().tryRet()));
            feedParents();
            common.initForm(this);
            getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_ADD_PK_PLAYER)).addEvt(new AddPokemonBeanAdd(this));
        }
        initFormPk();
        getBuilder().button(formatMessageRend(MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_SEARCH_PK_PLAYER)).addEvt(new AddPokemonBeanSearch(this));
        new BeanDisplayListGrid<PokemonLine>(new BeanDisplayPokemonLineSimuPlayer(this)).displayGrid(this,getPokedex(),MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_POKEDEX,MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataPokemonPokedex.M_P_82_NAME,MessagesDataPokemonPokedex.M_P_82_TYPES,MessagesDataPokemonPokedex.M_P_82_EVOS);
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.SIMU_LEVEL).getMapping();
    }
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
            common.updateGender();
        } else {
            namePk = "";
        }
        setupPokedex(getForms().getValPokemonData(CST_POKEMON_SET_SIMU));
    }
    public String add() {
        if (!getForms().contains(CST_PK_NAME)) {
            return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_ADDPOKEMON_HTML;
        }
        getForms().removeKey(CST_PK_NAME);
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
        pk_.setLevel(common.getLevel().valueLong());
        pk_.setAbility(ability.tryRet());
        pk_.setGender(PokemonStandards.getGenderByName(common.getGender().tryRet()));
        PokemonData pkData_ = data_.getPokemon(namePk);
        PokemonPlayerDto pkDto_ = new PokemonPlayerDto();
        pkDto_.setPokemon(pk_);
        pkDto_.setMoves(pkData_.getMovesAtLevel(common.getLevel().valueLong(), data_.getNbMaxMoves()));
        getForms().put(CST_POKEMON_ADDED, pkDto_);
        getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.ADD);
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
    public String cancel() {
        getForms().put(CST_ADDING_TRAINER_PK, TeamCrud.NOTHING);
        getForms().removeKey(CST_PK_NAME);
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SIMULATION_HTML;
    }
    public String search() {
        search(CST_POKEMON_SET_SIMU, CST_PK_NAME, "", "");
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_ADDPOKEMON_HTML;
//        StringList pokedex_ = pokedex();
//        getForms().put(CST_POKEMON_SET_SIMU, pokedex_);
//        if (pokedex_.size() == DataBase.ONE_POSSIBLE_CHOICE) {
//            getForms().put(CST_PK_NAME,pokedex_.first());
//        }
    }
    public String clickLink(int _number) {
        return putName(getPokedex().get(_number).getName());
    }

    public String putName(String _name) {
        getForms().put(CST_PK_NAME, _name);
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_ADDPOKEMON_HTML;
    }

    public String getNamePk() {
        return namePk;
    }

    public DictionaryComparator<String,String> getAbilities() {
        return abilities;
    }

    public IntBeanChgString getAbility() {
        return ability;
    }

    public void setAbility(IntBeanChgString _ability) {
        ability = _ability;
    }

    public CrudPkCommon getCommon() {
        return common;
    }

}