package aiki.map.pokemon;

import aiki.db.DataBase;
import aiki.map.characters.Rewardable;
import code.util.CustList;


public final class PokemonTeam implements Rewardable {

    private CustList<PkTrainer> team;

    private short reward;

    public void validate(DataBase _data) {
        for (PkTrainer p : team) {
            p.validateAsNpc(_data);
        }
        if (team.isEmpty()) {
            _data.setError(true);
        }
        if (reward <= 0) {
            _data.setError(true);
        }
    }

    public CustList<PkTrainer> getTeam() {
        return team;
    }

    public void setTeam(CustList<PkTrainer> _team) {
        team = _team;
    }

    @Override
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
