package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.pokemon.WildPk;
import code.util.CustList;

public final class MultAreaApparition extends AbsAreaApparition {

    public MultAreaApparition() {
        setWildPokemonList(new CustList<CustList<WildPk>>());
        setWildPokemonFishingList(new CustList<CustList<WildPk>>());
    }
    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        for (WildPk p : getWildPokemon()) {
            p.validateAsNpc(_data);
        }
        if (getWildPokemon().isEmpty()) {
            _data.setError(true);
        }
        for (WildPk p : getWildPokemonFishing()) {
            p.validateAsNpc(_data);
        }
    }

    @Override
    public void initializeWildPokemon() {
        setWildPokemonRand(AreaApparition.randomList(getAvgNbSteps(), getWildPokemonList()));
        setWildPokemonRandFishing(AreaApparition.randomList(ALWAYS_APPARITION, getWildPokemonFishingList()));

    }

    @Override
    public boolean isVirtual() {
        return getWildPokemonList().isEmpty() && getWildPokemonFishingList().isEmpty();
    }

    @Override
    public CustList<WildPk> getWildPokemon() {
        return flat(getWildPokemonList());
    }

    @Override
    public WildPk getWildPokemon(int _index) {
        return getWildPokemon().get(_index);
    }

    @Override
    public CustList<WildPk> getWildPokemonFishing() {
        return flat(getWildPokemonFishingList());
    }

    @Override
    public WildPk getPokemonFishing(int _index) {
        return getWildPokemonFishing().get(_index);
    }

    private CustList<WildPk> flat(CustList<CustList<WildPk>> _lists) {
        CustList<WildPk> wp_ = new CustList<WildPk>();
        for (CustList<WildPk> l: _lists) {
            wp_.addAllElts(l);
        }
        return wp_;
    }

}
