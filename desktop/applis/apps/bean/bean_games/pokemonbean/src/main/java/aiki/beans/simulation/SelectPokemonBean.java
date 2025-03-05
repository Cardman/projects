package aiki.beans.simulation;

import aiki.beans.*;
import aiki.beans.facade.dto.*;
import aiki.facade.*;
import code.scripts.confs.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class SelectPokemonBean extends WithFilterBean {
    private IntBeanChgSubmit updateValues;
    @Override
    public void build(FacadeGame _facade, StringMapObject _form) {
        init(_facade, _form);
        setTitledBorder(file().getVal(MessagesDataSimulationLevelsimu.M_P_85_TITLE_SELECT_PK));
        formatMessageAnc(new BeanAnchorCstEvent(PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML,this),MessagesPkBean.SIMU,MessagesDataSimulation.M_P_86_CANCEL);
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
    public static String cancel() {
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
    }
    public String search() {
        return search(CST_POKEMON_NAME_EDIT, PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML, PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_SELECTPOKEMON_HTML);
    }

    public String clickLink(int _number) {
        putName(getPokedex().get(_number).getName());
        return PkScriptPages.REN_ADD_WEB_HTML_SIMULATION_EDITPOKEMONTRAINER_HTML;
    }

    public void putName(String _name) {
        getForms().put(CST_POKEMON_NAME_EDIT, _name);
    }

}