package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.pokemon.MonteCarloWilPkList;
import aiki.map.pokemon.WildPk;
import aiki.util.DataInfoChecker;
import code.maths.LgInt;
import code.util.CustList;


public final class AreaApparition extends AbsAreaApparition {

    private CustList<WildPk> wildPokemon;

    private int multFight;

    private CustList<WildPk> wildPokemonFishing;

    @Override
    public void validate(DataBase _data) {
        super.validate(_data);
        DataInfoChecker.checkLower(1,multFight,_data);
        DataInfoChecker.checkGreater(DataBase.MAX_MULT_FIGHT,multFight,_data);
    }

    public void initializeWildPokemon() {
        setWildPokemonList(prod(wildPokemon, multFight));
        setWildPokemonRand(random(wildPokemon, getAvgNbSteps(), multFight));
        setWildPokemonFishingList(prod(wildPokemonFishing, multFight));
        setWildPokemonRandFishing(random(wildPokemonFishing, ALWAYS_APPARITION, multFight));
    }

    static MonteCarloWilPkList random(CustList<WildPk> _wildPokemon,
                                                   int _avgNbSteps, int _multFight) {
        CustList<CustList<WildPk>> wildPokemonCopy_ = prod(_wildPokemon,_multFight);
        return randomList(_avgNbSteps, wildPokemonCopy_);
    }

    static MonteCarloWilPkList randomList(int _avgNbSteps, CustList<CustList<WildPk>> _wildPokemonCopy) {
        MonteCarloWilPkList wildPokemonRandAll_ = new MonteCarloWilPkList();
        for (CustList<WildPk> p : _wildPokemonCopy) {
            wildPokemonRandAll_.addQuickEvent(p, LgInt.one());
        }
        MonteCarloWilPkList wildPokemonRand_ = new MonteCarloWilPkList();
        for (CustList<WildPk> p : wildPokemonRandAll_.eventsDiff()) {
            wildPokemonRand_.addQuickEvent(p, wildPokemonRandAll_.rate(p));
        }
        if (_avgNbSteps > 1) {
            wildPokemonRand_.addQuickEvent(new CustList<WildPk>(), new LgInt((_avgNbSteps - 1L)
                    * _wildPokemonCopy.size()));
        }
        return wildPokemonRand_;
    }

    private static CustList<CustList<WildPk>> prod(CustList<WildPk> _wildPokemon, int _multFight) {
        CustList<CustList<WildPk>> wildPokemonCopy_ = new CustList<CustList<WildPk>>();
        wildPokemonCopy_.add(new CustList<WildPk>());
        for (int i = 0; i < _multFight; i++) {
            wildPokemonCopy_ = _wildPokemon.cartesian(wildPokemonCopy_);
        }
        return wildPokemonCopy_;
    }

    public static boolean eqList(CustList<WildPk> _one, CustList<WildPk> _two) {
        int size_ = _two.size();
        if (_one.size() != size_) {
            return false;
        }
        boolean eq_ = true;
        for (int i = 0; i < size_; i++) {
            if (!WildPk.eq(_one.get(i), _two.get(i))) {
                eq_ = false;
                break;
            }
        }
        return eq_;
    }

    public boolean isVirtual() {
        return getMultFight() < 1;
    }

    public CustList<WildPk> getWildPokemon() {
        return wildPokemon;
    }

    public WildPk getWildPokemon(int _index) {
        return wildPokemon.get(_index);
    }

    public void setWildPokemon(CustList<WildPk> _wildPokemon) {
        wildPokemon = _wildPokemon;
    }

    public int getMultFight() {
        return multFight;
    }

    public void setMultFight(int _multFight) {
        multFight = _multFight;
    }

    public CustList<WildPk> getWildPokemonFishing() {
        return wildPokemonFishing;
    }

    public WildPk getPokemonFishing(int _index) {
        return wildPokemonFishing.get(_index);
    }

    public void setWildPokemonFishing(CustList<WildPk> _wildPokemonFishing) {
        wildPokemonFishing = _wildPokemonFishing;
    }
}
