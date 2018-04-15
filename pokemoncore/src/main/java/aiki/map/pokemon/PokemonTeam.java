package aiki.map.pokemon;

import aiki.DataBase;
import code.util.CustList;
import code.util.annot.RwXml;

@RwXml
public final class PokemonTeam {

    private CustList<PkTrainer> team;

    private short reward;

    public void validate(DataBase _data) {
        for (PkTrainer p : team) {
            p.validate(_data, true);
            // if (!p.isValid(_data)) {
            // _data.setError(true);
            return;

            // }
        }
        if (team.isEmpty()) {
            _data.setError(true);
            return;

        }
        if (reward <= 0) {
            _data.setError(true);
            return;

        }
    }

    public CustList<PkTrainer> getTeam() {
        return team;
    }

    public void setTeam(CustList<PkTrainer> _team) {
        team = _team;
    }

    public short getReward() {
        return reward;
    }

    public void setReward(short _reward) {
        reward = _reward;
    }

    // @Override
    // public void beforeSave() {
    // // List<PokemonTrainer> l_ = new List<>();
    // // for (PokemonTrainer p: team) {
    // // l_.add(new PkTrainer(p, p.getMoves()));
    // // }
    // // team = l_;
    // }
    //
    // @Override
    // public void afterLoad() {
    // }
}
