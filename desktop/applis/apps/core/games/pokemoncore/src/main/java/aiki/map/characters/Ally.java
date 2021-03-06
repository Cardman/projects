package aiki.map.characters;

import aiki.db.DataBase;
import aiki.map.pokemon.PkTrainer;
import code.util.CustList;


public final class Ally {

    private CustList<PkTrainer> team;

    public void validate(DataBase _data) {
        for (PkTrainer p : team) {
            p.validateAsNpc(_data);
        }
        if (team.isEmpty()) {
            _data.setError(true);
        }
    }

    public CustList<PkTrainer> getTeam() {
        return team;
    }

    public void setTeam(CustList<PkTrainer> _team) {
        team = _team;
    }


}
