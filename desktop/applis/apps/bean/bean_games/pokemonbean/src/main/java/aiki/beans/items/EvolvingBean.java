package aiki.beans.items;

import aiki.beans.*;
import aiki.facade.FacadeGame;
import code.util.*;

public abstract class EvolvingBean extends ItemBean{
    private CustList<TranslatedKey> pokemon;

    @Override
    public void build(FacadeGame _facade) {
        init(_facade);
        buildHeader();
        new BeanDisplayList<TranslatedKey>(new BeanDisplayTranslatedKey()).display(this,pokemon,fileLoc(),keyLoc());
    }

    protected abstract String keyLoc();

    protected abstract String fileLoc();

    public String getTrPokemon(int _index) {
        return pokemon.get(_index).getTranslation();
//        String type_ = pokemon.get(_index);
//        DataBase data_ = getDataBase();
//        StringMap<String> translatedPokemon_ = data_.getTranslatedPokemon().getVal(getLanguage());
//        return translatedPokemon_.getVal(type_);
    }
    public String clickPokemon(int _index) {
        return tryRedirect(pokemon.get(_index));
    }

    public CustList<TranslatedKey> getPokemon() {
        return pokemon;
    }

    public void pk(StringList _p) {
        _p.removeDuplicates();
//        _p.sortElts(DictionaryComparatorUtil.cmpPokemon(getDataBase(),getLanguage()));
        setPokemon(listTrStringsPk(_p,getFacade()));
    }
    public void setPokemon(CustList<TranslatedKey> _p) {
        this.pokemon = _p;
    }
}
