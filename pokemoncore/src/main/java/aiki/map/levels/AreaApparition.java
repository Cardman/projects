package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.pokemon.WildPk;
import code.maths.LgInt;
import code.maths.montecarlo.MonteCarloEq;
import code.util.CollCapacity;
import code.util.EqList;


public final class AreaApparition {

    static final byte ALWAYS_APPARITION = 1;

    private EqList<WildPk> wildPokemon;

    private short avgNbSteps;

    private byte multFight;

    private EqList<WildPk> wildPokemonFishing;

    private MonteCarloEq<WildPk> wildPokemonRand;

    private MonteCarloEq<WildPk> wildPokemonRandFishing;

    public void validate(DataBase _data) {
        if (avgNbSteps < ALWAYS_APPARITION) {
            _data.setError(true);
        }
        if (multFight < 1) {
            _data.setError(true);
        }
        if (multFight > DataBase.MAX_MULT_FIGHT) {
            _data.setError(true);
        }
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

    static MonteCarloEq<WildPk> random(EqList<WildPk> _wildPokemon,
            int _avgNbSteps) {
        EqList<WildPk> wildPokemonCopy_ = new EqList<WildPk>(_wildPokemon);
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
        MonteCarloEq<WildPk> wildPokemonRand_ = new MonteCarloEq<WildPk>(new CollCapacity(wildPokemonCopy_.size()));
        for (WildPk p : wildPokemonCopy_) {
            int count_ = 0;
            for (WildPk p2_ : _wildPokemon) {
                if (!WildPk.eq(p2_, p)) {
                    continue;
                }
                count_++;
            }
            wildPokemonRand_.addEvent(p, new LgInt(count_));
        }
        if (_avgNbSteps > 1) {
            wildPokemonRand_.addEvent(new WildPk(), new LgInt((_avgNbSteps - 1)
                    * _wildPokemon.size()));
        }
        return wildPokemonRand_;
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

    public EqList<WildPk> getWildPokemon() {
        return wildPokemon;
    }

    public WildPk getWildPokemon(int _index) {
        return wildPokemon.get(_index);
    }

    public void setWildPokemon(EqList<WildPk> _wildPokemon) {
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

    public EqList<WildPk> getWildPokemonFishing() {
        return wildPokemonFishing;
    }

    public WildPk getPokemonFishing(int _index) {
        return wildPokemonFishing.get(_index);
    }

    public void setWildPokemonFishing(EqList<WildPk> _wildPokemonFishing) {
        wildPokemonFishing = _wildPokemonFishing;
    }

    public MonteCarloEq<WildPk> getWildPokemonRand() {
        return wildPokemonRand;
    }

    public MonteCarloEq<WildPk> getWildPokemonRandFishing() {
        return wildPokemonRandFishing;
    }

}
