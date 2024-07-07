package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.pokemon.WildPk;
import aiki.util.DataInfoChecker;
import code.maths.LgInt;
import code.maths.montecarlo.MonteCarloList;
import code.util.CustList;


public final class AreaApparition extends AbsAreaApparition {

    private CustList<WildPk> wildPokemon;

    private byte multFight;

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

    static MonteCarloList<CustList<WildPk>> random(CustList<WildPk> _wildPokemon,
                                                   int _avgNbSteps, int _multFight) {
        CustList<CustList<WildPk>> wildPokemonCopy_ = prod(_wildPokemon,_multFight);
        return randomList(_avgNbSteps, wildPokemonCopy_);
    }

    static MonteCarloList<CustList<WildPk>> randomList(int _avgNbSteps, CustList<CustList<WildPk>> _wildPokemonCopy) {
        CustList<CustList<WildPk>> dis_ = distinct(_wildPokemonCopy);
        MonteCarloList<CustList<WildPk>> wildPokemonRand_ = new MonteCarloList<CustList<WildPk>>();
        for (CustList<WildPk> p : dis_) {
            int count_ = 0;
            for (CustList<WildPk> p2_ : _wildPokemonCopy) {
                if (!eqList(p2_, p)) {
                    continue;
                }
                count_++;
            }
            wildPokemonRand_.addQuickEvent(p, new LgInt(count_));
        }
        if (_avgNbSteps > 1) {
            wildPokemonRand_.addQuickEvent(new CustList<WildPk>(), new LgInt((_avgNbSteps - 1L)
                    * _wildPokemonCopy.size()));
        }
        return wildPokemonRand_;
    }

    private static CustList<CustList<WildPk>> distinct(CustList<CustList<WildPk>> _wildPokemonCopy) {
        CustList<CustList<WildPk>> wildPokemonCopy_ = new CustList<CustList<WildPk>>(_wildPokemonCopy);
        int i_ = 0;
        while (i_ < wildPokemonCopy_.size()) {
            CustList<WildPk> pk_ = wildPokemonCopy_.get(i_);
            int j_ = i_ + 1;
            while (j_ < wildPokemonCopy_.size()) {
                if (eqList(wildPokemonCopy_.get(j_), pk_)) {
                    wildPokemonCopy_.remove(j_);
                } else {
                    j_++;
                }
            }
            i_++;
        }
        return wildPokemonCopy_;
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

    public byte getMultFight() {
        return multFight;
    }

    public void setMultFight(byte _multFight) {
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
