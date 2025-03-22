package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.facade.dto.*;
import aiki.facade.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class SelectPokemonBean extends WithFilterBean {
    private IntBeanChgSubmit updateValues;
    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        setTitledBorder(file().getVal(MessagesDataSimulationLevelsimu.M_P_85_TITLE_SELECT_PK));
        formatMessageAnc(new BeanAnchorCstEvent(CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML),MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_CANCEL);
        initFormPk();
        initLine();
        updateValues = getBuilder().button(formatMessageRend(MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_OK));
        getUpdateValues().addEvt(new SelectPokemonBeanSearch(this));
        feedParents();
        new BeanDisplayListGrid<PokemonLine>(new BeanDisplayPokemonLineSimu(this)).displayGrid(this,getPokedex(),MessagesPkBean.POKEDEX,MessagesDataPokemonPokedex.M_P_82_POKEDEX,MessagesDataPokemonPokedex.M_P_82_IMAGE,MessagesDataPokemonPokedex.M_P_82_NAME,MessagesDataPokemonPokedex.M_P_82_TYPES,MessagesDataPokemonPokedex.M_P_82_EVOS);
    }

    public IntBeanChgSubmit getUpdateValues() {
        return updateValues;
    }

    public StringMap<String> file() {
        return file(MessagesPkBean.SIMU_LEVEL).getMapping();
    }
    @Override
    public void beforeDisplaying() {
        bools();
        setupPokedex(getForms().getValPokemonData(CST_POKEMON_SET));
    }

    public String search() {
        return search(SIMU_CST_POKEMON_NAME_EDIT, CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML, CommonBean.REN_ADD_WEB_HTML_SIMULATION_SELECTPOKEMON_HTML);
    }

//    public String clickLink(int _number) {
//        return putName(getPokedex().get(_number).getName());
//    }

    public String putName(String _name) {
        getForms().put(SIMU_CST_POKEMON_NAME_EDIT, _name);
        return CommonBean.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
    }

}