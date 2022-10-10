package aiki.game.fight;

import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.PkTrainer;
import code.util.CustList;

public final class FreeTeamChoice {
    private final CustList<PkTrainer> allyTeam = new CustList<PkTrainer>();
    private final CustList<PkTrainer> foeTeam = new CustList<PkTrainer>();
    private int multiplicity;
    private int nbMaxActions;
    private EnvironmentType env;

    public CustList<PkTrainer> getAllyTeam() {
        return allyTeam;
    }

    public CustList<PkTrainer> getFoeTeam() {
        return foeTeam;
    }

    public int getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(int _m) {
        this.multiplicity = _m;
    }

    public int getNbMaxActions() {
        return nbMaxActions;
    }

    public void setNbMaxActions(int _n) {
        this.nbMaxActions = _n;
    }

    public EnvironmentType getEnv() {
        return env;
    }

    public void setEnv(EnvironmentType _e) {
        this.env = _e;
    }
}
