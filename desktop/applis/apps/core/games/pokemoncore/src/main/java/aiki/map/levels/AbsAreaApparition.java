package aiki.map.levels;

import aiki.db.DataBase;
import aiki.map.pokemon.WildPk;
import aiki.util.DataInfoChecker;
import code.maths.montecarlo.MonteCarloList;
import code.util.CustList;

public abstract class AbsAreaApparition {

    static final byte ALWAYS_APPARITION = 1;

    private CustList<CustList<WildPk>> wildPokemonList;

    private MonteCarloList<CustList<WildPk>> wildPokemonRand;
    private CustList<CustList<WildPk>> wildPokemonFishingList;

    private MonteCarloList<CustList<WildPk>> wildPokemonRandFishing;

    private short avgNbSteps;

    public void validate(DataBase _data) {
        DataInfoChecker.checkLower(ALWAYS_APPARITION,avgNbSteps,_data);
    }
    public int getPokemonListLength(boolean _walking) {
        if (_walking) {
            return wildPokemonList.size();
        }
        return wildPokemonFishingList.size();
    }

    public CustList<WildPk> getWildPokemon(int _index, boolean _walking) {
        if (_walking) {
            return wildPokemonList.get(_index);
        }
        return wildPokemonFishingList.get(_index);
    }
    public abstract void initializeWildPokemon();
    public abstract boolean isVirtual();
    public abstract CustList<WildPk> getWildPokemon();
    public abstract WildPk getWildPokemon(int _index);
    public abstract CustList<WildPk> getWildPokemonFishing();
    public abstract WildPk getPokemonFishing(int _index);
    public short getAvgNbSteps() {
        return avgNbSteps;
    }

    public void setAvgNbSteps(short _avgNbSteps) {
        avgNbSteps = _avgNbSteps;
    }

    public CustList<CustList<WildPk>> getWildPokemonList() {
        return wildPokemonList;
    }

    public void setWildPokemonList(CustList<CustList<WildPk>> _w) {
        this.wildPokemonList = _w;
    }

    public CustList<CustList<WildPk>> getWildPokemonFishingList() {
        return wildPokemonFishingList;
    }

    public void setWildPokemonFishingList(CustList<CustList<WildPk>> _w) {
        this.wildPokemonFishingList = _w;
    }

    public MonteCarloList<CustList<WildPk>> getWildPokemonRand() {
        return wildPokemonRand;
    }

    public void setWildPokemonRand(MonteCarloList<CustList<WildPk>> _w) {
        this.wildPokemonRand = _w;
    }

    public MonteCarloList<CustList<WildPk>> getWildPokemonRandFishing() {
        return wildPokemonRandFishing;
    }

    public void setWildPokemonRandFishing(MonteCarloList<CustList<WildPk>> _w) {
        this.wildPokemonRandFishing = _w;
    }
}
