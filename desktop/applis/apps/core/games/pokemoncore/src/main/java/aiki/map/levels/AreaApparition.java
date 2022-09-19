package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.pokemon.WildPk;
import aiki.util.DataInfoChecker;
import code.maths.LgInt;
import code.maths.montecarlo.MonteCarloList;
import code.util.CustList;


public final class AreaApparition {

    static final byte ALWAYS_APPARITION = 1;

    private CustList<WildPk> wildPokemon;

    private short avgNbSteps;

    private byte multFight;

    private CustList<WildPk> wildPokemonFishing;

    private MonteCarloList<WildPk> wildPokemonRand;

    private MonteCarloList<WildPk> wildPokemonRandFishing;

    public void validate(DataBase _data) {
        DataInfoChecker.checkLower(ALWAYS_APPARITION,avgNbSteps,_data);
        DataInfoChecker.checkLower(1,multFight,_data);
        DataInfoChecker.checkGreater(DataBase.MAX_MULT_FIGHT,multFight,_data);
        for (WildPk p : wildPokemon) {
            p.validateAsNpc(_data);
        }
        if (wildPokemon.isEmpty()) {
            _data.setError(true);
        }
        for (WildPk p : wildPokemonFishing) {
            p.validateAsNpc(_data);
        }
    }

    public void initializeWildPokemon() {
        wildPokemonRand = random(wildPokemon, avgNbSteps);
        wildPokemonRandFishing = random(wildPokemonFishing, ALWAYS_APPARITION);
    }

    static MonteCarloList<WildPk> random(CustList<WildPk> _wildPokemon,
            int _avgNbSteps) {
        CustList<WildPk> wildPokemonCopy_ = distinct(_wildPokemon);
        MonteCarloList<WildPk> wildPokemonRand_ = new MonteCarloList<WildPk>();
        for (WildPk p : wildPokemonCopy_) {
            int count_ = 0;
            for (WildPk p2_ : _wildPokemon) {
                if (!WildPk.eq(p2_, p)) {
                    continue;
                }
                count_++;
            }
            wildPokemonRand_.addQuickEvent(p, new LgInt(count_));
        }
        if (_avgNbSteps > 1) {
            wildPokemonRand_.addQuickEvent(new WildPk(), new LgInt((_avgNbSteps - 1L)
                    * _wildPokemon.size()));
        }
        return wildPokemonRand_;
    }

    private static CustList<WildPk> distinct(CustList<WildPk> _wildPokemon) {
        CustList<WildPk> wildPokemonCopy_ = new CustList<WildPk>(_wildPokemon);
        int i_ = 0;
        while (i_ < wildPokemonCopy_.size()) {
            WildPk pk_ = wildPokemonCopy_.get(i_);
            int j_ = i_ + 1;
            while (j_ < wildPokemonCopy_.size()) {
                if (WildPk.eq(wildPokemonCopy_.get(j_), pk_)) {
                    wildPokemonCopy_.remove(j_);
                } else {
                    j_++;
                }
            }
            i_++;
        }
        return wildPokemonCopy_;
    }

    public boolean isVirtual() {
        return getMultFight() < 1;
    }

    public int getPokemonListLength(boolean _walking) {
        if (_walking) {
            return wildPokemon.size();
        }
        return wildPokemonFishing.size();
    }

    public WildPk getWildPokemon(int _index, boolean _walking) {
        if (_walking) {
            return getWildPokemon(_index);
        }
        return getPokemonFishing(_index);
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

    public short getAvgNbSteps() {
        return avgNbSteps;
    }

    public void setAvgNbSteps(short _avgNbSteps) {
        avgNbSteps = _avgNbSteps;
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

    public MonteCarloList<WildPk> getWildPokemonRand() {
        return wildPokemonRand;
    }

    public MonteCarloList<WildPk> getWildPokemonRandFishing() {
        return wildPokemonRandFishing;
    }

}
