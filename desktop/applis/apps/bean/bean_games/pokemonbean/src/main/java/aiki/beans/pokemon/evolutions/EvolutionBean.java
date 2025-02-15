package aiki.beans.pokemon.evolutions;

import aiki.beans.*;
import aiki.db.*;
import aiki.fight.pokemon.evolution.*;
import code.util.*;

public class EvolutionBean extends CommonBean {
    private String displayBase;
    private String base;

    private Evolution evo;
    private String displayName;
    private TranslatedKey name;
    private int index;

    @Override
    public void beforeDisplaying() {
        DataBase data_ = getDataBase();
        StringMap<String> translationsPokemon_;
        translationsPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
        evo = data_.getPokemon(getBase()).getEvolutions().getVal(name.getKey());
        displayBase = translationsPokemon_.getVal(getBase());
        displayName = name.getTranslation();
    }

    protected Evolution getEvo() {
        return evo;
    }

    public TranslatedKey getName() {
        return name;
    }

    public String clickEvo(int _index) {
        return tryRedirect(getForms().getCurrentBeanEvo().get(_index).name);
    }

    public String getBase() {
        return base;
    }

    public void setIndex(int _index) {
        index = _index;
    }

    public int getIndex() {
        return index;
    }

    public void setName(String _name) {
        name = buildPk(getFacade(),_name);
    }

    public void setBase(String _base) {
        base = _base;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDisplayBase() {
        return displayBase;
    }
}